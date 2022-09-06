import java.util.List;

class UserInteraction implements UserInteractionInterface {
    private static final int COMMAND_INDEX = 0;
    private static final int COMMAND_TOKEN_SIZE = 1;
    private static final int ARGS_INDEX = 1;

    /**
     * Prints a greeting and handles user inputs.
     */
    @Override
    public final void intializeConversation() {
        Console.printGreeting();

        List<String[]> inputs = Console.getUserInput();
        String command = inputs.get(COMMAND_INDEX)[COMMAND_TOKEN_SIZE - 1];

        // maintain conversation
        while (!command.equals("bye")) {
            try {
                String[] userArgs = inputs.get(ARGS_INDEX);
                new Command(command, userArgs).handleCommand();
            } catch (Exception e) {
                ExceptionManager.handleException(e);
            } finally {
                inputs = Console.getUserInput();
                command = inputs.get(COMMAND_INDEX)[COMMAND_TOKEN_SIZE - 1];
            }
        }

        exitConversation();
    }

    /**
     * Prints a farewell greeting and closes Scanner.
     */
    public final void exitConversation() {
        Console.printNormalResponse("Bye. Hope to see you again soon!");
    }

}
