import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner ss = new Scanner(System.in);

        // print initial greeting
        System.out.println("------------------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("------------------------------");

        // get user input
        String command = ss.nextLine();
        while (!command.equals("exit")) {
            System.out.println("    ------------------------------");
            System.out.println("    " + command);
            System.out.println("    ------------------------------");
            command = ss.nextLine();
        }

        // print final greeting
        System.out.println("------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("------------------------------");
        ss.close();
    }
}
