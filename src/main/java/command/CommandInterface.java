package command;

import java.io.IOException;
import exception.TaskNotFoundException;

interface CommandInterface {
    boolean executeCommand() throws TaskNotFoundException, IOException;
}
