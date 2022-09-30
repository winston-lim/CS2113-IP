package file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class FileManager implements FileManagerInterface {
    // Properties
    private final File file;

    public FileManager(String filePath) {
        this.file = new File(filePath);
    }


    /**
     * Returns target file, which may not exist.
     * 
     * @return File
     */
    public File readFromFile() {
        return this.file;
    }


    /**
     * Writes to target file, which will overwrite its contents.
     * 
     * @param fileContent the new contents used to overwrite.
     * @throws IOException thrown when saving to local storage fails
     */
    public void writeToFile(String fileContent) throws IOException {
        FileWriter fw = new FileWriter(this.file);
        fw.write(fileContent);
        fw.close();
    }
}
