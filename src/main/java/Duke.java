import java.util.List;

public class Duke {
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
        while (!inputs.get(0)[0].equals("bye")) {
            String command = inputs.get(0)[0];
            String[] userArgs = inputs.get(1);

            handleCommand(command, userArgs);

            inputs = ConversationManager.getUserInput();
        }

        ConversationManager.exitConversation();
    }


    /**
     * Handles a command given from user.
     * 
     * @param command any string
     * @param args an array of strings of any length
     */
    public static void handleCommand(String command, String[] args) {
        switch (command) {
        case "list":
            taskManager.listTasks();
            break;
        case "mark":
            taskManager.markTask(args);
            break;
        case "unmark":
            taskManager.unmarkTask(args);
            break;
        default:
            taskManager.addTask(command, args);
        }
    }

}
