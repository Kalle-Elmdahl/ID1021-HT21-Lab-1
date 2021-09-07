import java.util.Scanner;


public class Recursive {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String string = input.next();
        input.close();
        printCharactersReverse(string);
        System.out.println();
    }

    public static void printCharactersReverse(String str) {
        if(str.isEmpty()) return;
        printCharactersReverse(str.substring(1));
        System.out.print(str.charAt(0));
    }
}
