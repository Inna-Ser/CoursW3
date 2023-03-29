package pro.sky.cw3.services.impl;

import org.springframework.stereotype.Service;
import pro.sky.cw3.services.FileService;

import java.io.File;
import java.nio.file.Path;


public class FileSericeImpl implements FileService {
    @Override
    public boolean saveToFile(String json) {
        return false;
    }

    @Override
    public String readFromFile() {
        return null;
    }

    @Override
    public boolean cleanDateFile() {
        return false;
    }

    @Override
    public Path creatTempFile(String suffix) {
        return null;
    }

    @Override
    public File getDataFile() {
        return null;
    }
}
