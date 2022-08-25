import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    static final String LINE_SEPERATOR = "------------------------------";
    static final String RESPONSE_INDENTATION = "    ";

    public static void main(String[] args) {
        // intialize variables
        Scanner ss = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        printIntialGreeting();

        // get user input
        String input = ss.nextLine();

        // maintain conversation
        while (!input.equals("bye")) {
            String[] inputs = input.split(" ");
            List<String> outputs = new ArrayList<String>();
            if (inputs[0].equals("list")) { // list inputs
                List<Task> recordedTasks = taskManager.getRecordedTasks();
                if (recordedTasks.size() == 0)
                    outputs.add("No recorded tasks");
                for (int i = 0; i < taskManager.getTaskCount(); ++i) {
                    outputs.add((i + 1) + ". " + "[" + recordedTasks.get(i).getStatusIcon() + "] "
                            + recordedTasks.get(i).getDescription());
                }
            } else if (inputs[0].equals("mark")) {
                if (inputs.length == 1) {
                    outputs.add("Error: Task number is required");
                } else {
                    outputs.add(taskManager.markTask(Integer.parseInt(inputs[1])));
                }
            } else if (inputs[0].equals("unmark")) {
                if (inputs.length == 1) {
                    outputs.add("Error: Task number is required");
                } else {
                    outputs.add(taskManager.unmarkTask(Integer.parseInt(inputs[1])));
                }
            } else { // add inputs to record
                if (inputs[0].length() != 0) {
                    outputs.add(taskManager.addTask(inputs[0]));
                }
            }
            printResponse(outputs);
            outputs.clear();
            input = ss.nextLine();
        }

        ss.close();

        printFinalGreeting();
    }

    public static void printIntialGreeting() {
        System.out.println(LINE_SEPERATOR);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(LINE_SEPERATOR);
    }

    public static void printFinalGreeting() {
        System.out.println(LINE_SEPERATOR);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE_SEPERATOR);
    }

    public static void printResponse(List<String> responses) {
        if (responses.size() != 0) {
            System.out.println(RESPONSE_INDENTATION + LINE_SEPERATOR);
            for (int i = 0; i < responses.size(); ++i) {
                System.out.println(RESPONSE_INDENTATION + responses.get(i));
            }
            System.out.println(RESPONSE_INDENTATION + LINE_SEPERATOR);
        }
    }

}
