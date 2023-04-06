package pro.sky.cw3.controllers;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.cw3.dto.SocksDTO;
import pro.sky.cw3.exception.InsufficientSockCountException;
import pro.sky.cw3.model.Colors;
import pro.sky.cw3.model.Size;
import pro.sky.cw3.services.impl.SocksServiceImpl;

import java.io.IOException;

@RestController
@RequestMapping("/files")
public class FileController {
    private final SocksServiceImpl socksService;

    public FileController(SocksServiceImpl socksService) {
        this.socksService = socksService;
    }

    @ExceptionHandler(InsufficientSockCountException.class)
    public ResponseEntity<String> handleInvalidException(InsufficientSockCountException invalidSockRequestException) {
        return ResponseEntity.badRequest().body(invalidSockRequestException.getMessage());
    }

    @PostMapping
    public void addSocks(@RequestBody SocksDTO socksDTO) {
        socksService.addSock(socksDTO);
    }

    @PutMapping
    public void issueSocks(@RequestBody SocksDTO socksDTO) {
        socksService.issueSocks(socksDTO);
    }

    @GetMapping
    public int getSocksCount(@RequestParam(required = false, name = "color") Colors colors,
                             @RequestParam(required = false, name = "size") Size size,
                             @RequestParam(required = false, name = "cottonMin") Integer cottonMin,
                             @RequestParam(required = false, name = "cottonMax") Integer cottonMax) {
        return socksService.getSocksCount(colors, size, cottonMin, cottonMax);
    }

    @DeleteMapping
    public void removeDefectieSocks(@RequestBody SocksDTO socksDTO) {
        socksService.removeDefectiveSocks(socksDTO);
    }

    @GetMapping("/export")
    public FileSystemResource exportData() throws IOException {
        return socksService.exportDate();
    }

    @PostMapping(name = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> importData(@RequestParam("file") MultipartFile file) throws IOException {
            socksService.importDate(file.getInputStream());
            return ResponseEntity.accepted().build();
    }
}
