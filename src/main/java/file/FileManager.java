package file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class FileManager implements FileManagerInterface {
    private final File file;

    public FileManager(String filePath) {
        this.file = new File(filePath);
    }

    public File readFromFile() {
        return this.file;
    }

    public void writeToFile(String fileContent) throws IOException {
        FileWriter fw = new FileWriter(this.file);
        fw.write(fileContent);
        fw.close();
    }
}
