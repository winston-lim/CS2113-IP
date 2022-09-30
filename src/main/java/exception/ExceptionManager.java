package exception;

import java.io.IOException;

import user.UserInteraction;

public class ExceptionManager {
    // Constants
    public static final String DEFAULT_ERROR_MESSAGE = "something went wrong";

    /**
     * Handles known exceptions by printing a corresponding message to the user.
     * 
     * @param e Exceptions thrown from the main program
     */
    public static void handleException(Exception e) {
        String errorMessage = DEFAULT_ERROR_MESSAGE;
        if (e instanceof IndexOutOfBoundsException || e instanceof ArrayIndexOutOfBoundsException) {
            errorMessage = "invalid arguments";
        }
        if (e instanceof CommandNotFoundException) {
            errorMessage = "invalid command";
        }
        if (e instanceof InsufficientArgumentsException) {
            errorMessage = "insufficient arguments";
        }
        if (e instanceof TaskNotFoundException) {
            errorMessage = "task specified was not found";
        }
        if (e instanceof IOException) {
            errorMessage = "failed to read from or write to file";
        }
        if (e instanceof InvalidFileDataException) {
            errorMessage = "invalid file data";
        }
        if (e instanceof InvalidTimeFormatException) {
            errorMessage = "invalid time format";
        }
        UserInteraction.printErrorResponse(errorMessage);
    }
}
