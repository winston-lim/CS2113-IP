package file;

import java.io.File;
import java.io.IOException;

public interface FileManagerInterface {
    File readFromFile();

    void writeToFile(String fileContent) throws IOException;
}
