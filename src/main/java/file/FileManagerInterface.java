package file;

import java.io.IOException;
import java.util.List;
import exception.InvalidDataException;
import task.Task;

public interface FileManagerInterface {
    List<Task> getTasks() throws InvalidDataException;

    void saveTask(Task task) throws IOException;
}
