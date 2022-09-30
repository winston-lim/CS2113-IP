import command.Command;
import exception.ExceptionManager;
import parser.Parser;
import task.TaskManager;
import user.UserInteraction;

public class Duke {
    /**
     * This is the main method that runs on execution.
     * 
     * @param args an array of string inputs
     */
    public static void main(String[] args) {
        UserInteraction.intializeConversation();
        TaskManager taskManager = new TaskManager();
        String input = UserInteraction.getUserInput();
        Command command;
        boolean isLastCommand = false;

        // maintain conversation
        while (true) {
            try {
                command = Parser.createCommand(taskManager, input);
                isLastCommand = command.executeCommand();
            } catch (Exception e) {
                ExceptionManager.handleException(e);
            } finally {
                if (isLastCommand) {
                    break;
                }
                input = UserInteraction.getUserInput();
            }
        }

        UserInteraction.exitConversation();
    }
}
