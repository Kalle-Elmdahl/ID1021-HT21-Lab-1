/**
 * @author Kalle Elmdahl 21/09/07 (updated 21/09/09)
 * The code is used for reversing strings.
 * The code is for an assignment from the KTH-course ID1020
 */
import java.util.Scanner;

/**
 * A class used for printing strings reversed
 */
public class Recursive {
    /**
     * Prints a string in reverse to {@code System.out.print}
     * @param str the string to be reversed
     */
    public static void printCharactersReverse(String s) {
        if(s.isEmpty()) return;
        printCharactersReverse(s.substring(1));
        System.out.print(s.charAt(0));
    }

    /**
     * Main function used for testing the class
     * @param args arguments from program execution
     */
    public static void main(String[] args) {
        System.out.print("\nHello please enter a command: \nrs - reverse a string\nquit - exit the program\n\n");
        Scanner input = new Scanner(System.in);
        outerwhile:
        while(true) {
            System.out.print("$ ");
            String cmd = input.next();

            switch(cmd) {
                case "rs":
                    printCharactersReverse(input.next());
                    break;
                case "quit": break outerwhile;
                default:
                    System.out.println("Unknown command");
                    break;
            }
            System.out.println();
        }
        input.close();
    }

}
