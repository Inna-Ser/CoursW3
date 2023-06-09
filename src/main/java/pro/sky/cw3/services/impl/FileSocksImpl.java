package pro.sky.cw3.services.impl;

import org.springframework.beans.factory.annotation.Value;
import pro.sky.cw3.services.FileService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileSocksImpl implements FileService {
    @Value("scr/main/resours")
    private String dataFilePath;
    @Value("socks.json")
    private String dataFileName;

    @Override
    public boolean saveToFile(String json) {
        try {
            cleanDateFile();
            Files.writeString(Path.of(dataFilePath, dataFileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String readFromFile() {
        try {
            return Files.readString(Path.of(dataFilePath, dataFileName));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean cleanDateFile() {
        try {
            Path path = Path.of(dataFilePath, dataFileName);
            Files.deleteIfExists(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Path creatTempFile(String suffix) {
        try {
            return Files.createTempFile(Path.of(dataFilePath), "socks_tmp_file", suffix);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public File getDataFile() {
        return new File(dataFilePath + "/" + dataFileName);
    }
}

