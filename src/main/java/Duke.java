import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        // intialize variables
        Scanner ss = new Scanner(System.in);
        List<String> recordedCommands = new ArrayList<String>();

        // print initial greeting
        System.out.println("------------------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("------------------------------");

        // get user input
        String command = ss.nextLine();
        while (!command.equals("bye")) {
            if (command.equals("list")) { // list commands
                System.out.println("    ------------------------------");
                for (int i = 0; i < recordedCommands.size(); ++i) {
                    System.out.println("    " + (i + 1) + ". " + recordedCommands.get(i));
                }
                System.out.println("    ------------------------------");
            } else { // add commands to record
                recordedCommands.add(command);
                System.out.println("    ------------------------------");
                System.out.println("    added " + command);
                System.out.println("    ------------------------------");
            }
            command = ss.nextLine();
        }

        // print final greeting
        System.out.println("------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("------------------------------");
    }
}
