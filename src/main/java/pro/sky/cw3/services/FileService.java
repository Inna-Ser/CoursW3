package pro.sky.cw3.services;

import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Path;

@Service
public interface FileService {

    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanDateFile();

    Path creatTempFile(String suffix);

    File getDataFile();
}
