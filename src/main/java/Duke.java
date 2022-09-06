import java.util.List;

public class Duke {
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK_TASK = "mark";
    private static final String COMMAND_UNMARK_TASK = "unmark";
    private static final int COMMAND_INDEX = 0;
    private static final int COMMAND_TOKEN_SIZE = 1;
    private static final int ARGS_INDEX = 1;
    private static TaskManager taskManager;

    /**
     * This is the main method that runs on execution.
     * 
     * @param args an array of string inputs
     */
    public static void main(String[] args) {
        // intialize variables
        taskManager = new TaskManager();

        ConversationManager.intializeConversation();

        // try to get user input
        List<String[]> inputs = ConversationManager.getUserInput();

        // maintain conversation
        while (!inputs.get(COMMAND_INDEX)[COMMAND_TOKEN_SIZE - 1].equals("bye")) {
            try {
                String command = inputs.get(COMMAND_INDEX)[COMMAND_TOKEN_SIZE - 1];
                String[] userArgs = inputs.get(ARGS_INDEX);

                handleCommand(command, userArgs);
            } catch (Exception e) {
                ExceptionManager.handleException(e);
            } finally {
                inputs = ConversationManager.getUserInput();
            }
        }

        ConversationManager.exitConversation();
    }


    /**
     * Handles a command given from user.
     * 
     * @param command any string
     * @param args an array of strings of any length
     */
    public static void handleCommand(String command, String[] args)
            throws InsufficentArgumentsException, CommandNotFoundException, TaskNotFoundException {
        switch (command) {
        case COMMAND_LIST:
            taskManager.listTasks();
            break;
        case COMMAND_MARK_TASK:
            taskManager.markTask(args);
            break;
        case COMMAND_UNMARK_TASK:
            taskManager.unmarkTask(args);
            break;
        default:
            taskManager.addTask(command, args);
        }
    }

}
