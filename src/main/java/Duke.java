import user.UserInteraction;

public class Duke {
    /**
     * This is the main method that runs on execution.
     * 
     * @param args an array of string inputs
     */
    public static void main(String[] args) {
        // Start conversation with user
        new UserInteraction().intializeConversation();
    }
}
