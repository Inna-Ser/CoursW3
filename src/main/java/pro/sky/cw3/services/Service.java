package pro.sky.cw3.services;

import org.springframework.core.io.FileSystemResource;
import pro.sky.cw3.dto.SocksDTO;
import pro.sky.cw3.exception.InsufficientSockCountException;
import pro.sky.cw3.exception.InvalidSockRequestException;
import pro.sky.cw3.model.Colors;
import pro.sky.cw3.model.Size;
import pro.sky.cw3.model.Socks;

import java.io.IOException;
import java.io.InputStream;

public interface Service {
    void addSock(SocksDTO socksDTO);

    void issueSocks(SocksDTO socksDTO);

    void removeDefectiveSocks(SocksDTO socksDTO);

    FileSystemResource exportDate() throws IOException;

    void importDate(InputStream inputStream) throws IOException;

    void decreaseSocksCount(SocksDTO socksDTO, boolean isIssue) throws InsufficientSockCountException;

    int getSocksCount(Colors colors, Size size, Integer cottonMin, Integer cottonMax);

    void validateRequest(SocksDTO socksDTO) throws InvalidSockRequestException;

    Socks mapToSock(SocksDTO socksDTO);

    SocksDTO mapToDTO(Socks socks, int count);
}
