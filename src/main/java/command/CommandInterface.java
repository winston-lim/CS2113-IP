package command;

import java.io.IOException;
import exception.InsufficientArgumentsException;
import exception.TaskNotFoundException;
import exception.InvalidTimeFormatException;

interface CommandInterface {
    boolean executeCommand() throws TaskNotFoundException, IOException,
            InsufficientArgumentsException, InvalidTimeFormatException;
}
