public class ExceptionManager {
    public static final String DEFAULT_ERROR_MESSAGE = "something went wrong";

    public static void handleException(Exception e) {
        String errorMessage = DEFAULT_ERROR_MESSAGE;
        if (e instanceof IndexOutOfBoundsException) {
            errorMessage = "invalid arguments";
        }
        if (e instanceof CommandNotFoundException) {
            errorMessage = "invalid command";
        }
        if (e instanceof InsufficentArgumentsException) {
            errorMessage = "insufficient arguments";
        }
        if (e instanceof TaskNotFoundException) {
            errorMessage = "task specified was not found";
        }
        ConversationManager.printErrorResponse(errorMessage);
    }
}
