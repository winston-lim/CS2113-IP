package console;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public abstract class Console {
    private static final String RESPONSE_INDENTATION = "    ";
    private static final String LINE_SEPERATOR = "------------------------------";
    private static final String LOGO =
            ",.   ,   ,.         .         \n" + "`|  /|  / . ,-. ,-. |- ,-. ,-. \n"
                    + " | / | /  | | | `-. |  | | | | \n" + " `'  `'   ' ' ' `-' `' `-' ' ' ";
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Gets user input from IO.
     * 
     * @return A list containing 2 String[] elements - the first contains command(s) while the
     *         second contains arguments(s)
     */
    public static final List<String[]> getUserInput() {
        String[] inputs = sc.nextLine().split(" ");
        String[] command = Arrays.copyOfRange(inputs, 0, 1);
        String[] args = Arrays.copyOfRange(inputs, 1, inputs.length);
        return List.of(command, args);
    }

    /**
     * Prints an intial greeting.
     */
    public static final void printGreeting() {
        System.out.println(LOGO);
        System.out.println(LINE_SEPERATOR);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(LINE_SEPERATOR);
    }

    /**
     * Prints a response with proper indentation and separators between lines. Note that
     * RESPONSE_INDENTATION and LINE_SEPARATOR are class-level constants defined above
     * 
     * @param messages a list of string
     */
    public static final void printNormalResponse(String... messages) {
        if (messages.length != 0) {
            System.out.println(RESPONSE_INDENTATION + LINE_SEPERATOR);
            for (int i = 0; i < messages.length; ++i) {
                System.out.println(RESPONSE_INDENTATION + messages[i]);
            }
            System.out.println(RESPONSE_INDENTATION + LINE_SEPERATOR);
        }
    }

    /**
     * Prints a success response with proper indentation and separators between lines. Note that
     * RESPONSE_INDENTATION and LINE_SEPARATOR are class-level constants defined above
     * 
     * @param successMessages a list of strings
     */
    public static final void printSuccessResponse(String... successMessages) {
        if (successMessages.length != 0) {
            System.out.println(RESPONSE_INDENTATION + LINE_SEPERATOR);
            for (int i = 0; i < successMessages.length; ++i) {
                System.out.println(RESPONSE_INDENTATION + "Success: " + successMessages[i]);
            }
            System.out.println(RESPONSE_INDENTATION + LINE_SEPERATOR);
        }
    }

    /**
     * Prints a error response with proper indentation and separators between lines. Note that
     * RESPONSE_INDENTATION and LINE_SEPARATOR are class-level constants defined above
     * 
     * @param errorMessages a list of string responses
     */
    public static final void printErrorResponse(String... errorMessages) {
        if (errorMessages.length != 0) {
            System.out.println(RESPONSE_INDENTATION + LINE_SEPERATOR);
            for (int i = 0; i < errorMessages.length; ++i) {
                System.out.println(RESPONSE_INDENTATION + "Error: " + errorMessages[i]);
            }
            System.out.println(RESPONSE_INDENTATION + LINE_SEPERATOR);
        }
    }
}
