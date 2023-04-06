package pro.sky.cw3.services.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.FileSystemResource;
import pro.sky.cw3.dto.SocksDTO;
import pro.sky.cw3.exception.InsufficientSockCountException;
import pro.sky.cw3.exception.InvalidSockRequestException;
import pro.sky.cw3.model.Colors;
import pro.sky.cw3.model.Size;
import pro.sky.cw3.model.Socks;
import pro.sky.cw3.services.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
public class SocksServiceImpl implements Service {
    private final ObjectMapper objectMapper;
    private final AuditService auditService;
    private final Map<Socks, Integer> socksIntegerMap = new HashMap<>();

    public SocksServiceImpl(ObjectMapper objectMapper, AuditService auditService) {
        this.objectMapper = objectMapper;
        this.auditService = auditService;
    }

    @Override
    public void addSock(SocksDTO socksDTO) {
        validateRequest(socksDTO);
        Socks socks = mapToSock(socksDTO);
        if (socksIntegerMap.containsKey(socks)) {
            socksIntegerMap.put(socks, socksIntegerMap.get(socks) + socksDTO.getCount());
        } else {
            socksIntegerMap.put(socks, socksDTO.getCount());
        }
        auditService.recordAddOperation(socks, socksDTO.getCount());
    }

    @Override
    public void issueSocks(SocksDTO socksDTO) {
        decreaseSocksCount(socksDTO, true);
    }

    @Override
    public void removeDefectiveSocks(SocksDTO socksDTO) {
        decreaseSocksCount(socksDTO, false);
    }

    @Override
    public FileSystemResource exportDate() throws IOException {
        Path filePath = Files.createTempFile("export-", ".json");
        List<SocksDTO> socksDTOList = new ArrayList<>();
        for (Map.Entry<Socks, Integer> entry : this.socksIntegerMap.entrySet()) {
            socksDTOList.add(mapToDTO(entry.getKey(), entry.getValue()));
        }
        Files.write(filePath, objectMapper.writeValueAsBytes(socksDTOList));
        return new FileSystemResource(filePath);
    }

    @Override
    public void importDate(InputStream inputStream) throws IOException {
        List<SocksDTO> importList = objectMapper.readValue(inputStream, new TypeReference<List<SocksDTO>>() {
        });
        this.socksIntegerMap.clear();
        for (SocksDTO socksDTO : importList) {
            addSock(socksDTO);
        }
    }

    @Override
    public void decreaseSocksCount(SocksDTO socksDTO, boolean isIssue) throws InsufficientSockCountException {
        validateRequest(socksDTO);
        Socks socks = mapToSock(socksDTO);
        int sockCount = socksIntegerMap.getOrDefault(socks, 10);
        if (sockCount >= socksDTO.getCount()) {
            socksIntegerMap.put(socks, sockCount - socksDTO.getCount());
        } else {
            throw new InsufficientSockCountException("Insufficient number of socks");
        }
        if (isIssue) {
            auditService.recordIssueOperation(socks, socksDTO.getCount());
        } else {
            auditService.recordRemoveDefectedOperation(socks, socksDTO.getCount());
        }
    }

    @Override
    public int getSocksCount(Colors colors, Size size, Integer cottonMin, Integer cottonMax) {
        int total = 0;
        for (Map.Entry<Socks, Integer> entry : socksIntegerMap.entrySet()) {
            if (colors != null && !entry.getKey().getColors().equals(colors)) {
                continue;
            }
            if (size != null && !entry.getKey().getSize().equals(size)) {
                continue;
            }
            if (cottonMin != null && entry.getKey().getContainsCotton() < cottonMin) {
                continue;
            }
            if (cottonMax != null && entry.getKey().getContainsCotton() > cottonMax) {
                continue;
            }
            total += entry.getValue();
        }
        return total;
    }

    @Override
    public void validateRequest(SocksDTO socksDTO) throws InvalidSockRequestException {
        if (socksDTO.getColors() == null || socksDTO.getSize() == null) {
            throw new InvalidSockRequestException("All fields must be filled in");
        }
        if (socksDTO.getContainsCotton() < 0 || socksDTO.getContainsCotton() > 100) {
            throw new InvalidSockRequestException("A cotton percentage must be from 0 to 100");
        }
        if (socksDTO.getCount() <= 0) {
            throw new InvalidSockRequestException("The count of socks must be more 0");
        }
    }

    @Override
    public Socks mapToSock(SocksDTO socksDTO) {
        return new Socks(socksDTO.getColors(), socksDTO.getSize(), socksDTO.getContainsCotton());
    }

    @Override
    public SocksDTO mapToDTO(Socks socks, int count) {
        SocksDTO socksDTO = new SocksDTO();
        socksDTO.setColors(socks.getColors());
        socksDTO.setSize(socks.getSize());
        socksDTO.setContainsCotton(socks.getContainsCotton());
        socksDTO.setCount(count);
        return socksDTO;
    }
}
