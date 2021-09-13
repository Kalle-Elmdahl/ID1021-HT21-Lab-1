/**
 * @author Kalle Elmdahl 21/09/07 (updated 21/09/09)
 * The code is used for reversing strings. But the class can be used for any Stack application
 * The code is for an assignment from the KTH-course ID1020
 * The code is based on examples from https://algs4.cs.princeton.edu/home/
 */
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class is a basic generalized Stack
 */
public class IterativeStack<Item> implements Iterable<Item> {
    private Node<Item> first;
    private int size;

    private static class Node<Item> {
        public Item item;
        public Node<Item> next;
    }
    
    /**
     * Adds one item to the stack.
     * @param item the item added
     */
    public void push(Item item) {            
        Node<Item> oldFirst = first;

        first = new Node<Item>();
        first.item = item;
        first.next = oldFirst;
        size++;
    }

    /**
     * Removes an item from the stack
     * @return the removed item
     */
    public Item pop() {
        if(first == null) throw new NoSuchElementException("Cannot pop from empty stack");
        
        Item item = first.item;
        first = first.next;

        size--;
        return item;
    }

    /**
     * Know if the stack is empty.
     * @return {@code true} if the stack is empty else {@code false}.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Get the number of items currently in the stack.
     * @return the size.
     */
    public int size() {
        return size;
    }

    /**
     * Generates a s tring representation of the stack
     * @return the string representation
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        Node<Item> node = first;
        for(int i = 0; i < size; i++) {
            s.append("\n[Node " + i + ": " + node);
            s.append("\nValue: " + node.item);
            s.append("\nNext: " + node.next + "],\n");
            node = node.next;
        }
        return s.toString();
    }

    /**
     * Get an instance of an iterator class.
     * @return the iterator.
     */
    public Iterator<Item> iterator() {
        return new LinkedIterator(first);
    }

    /**
     * Iterator class based on the standardized iterator {@see https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html}
     */
    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        /**
         * Initializes the iterator
         * @param first first node in the linked list
         */
        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        /**
         * Know if the iterator is done or not
         * @return {@code true} if there is another item
         */
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Get the next item in the list
         * @return the item
         */
        public Item next() {
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }

    /**
     * Reverses a string a prints it to system.out
     * @param s the string to be reversed
     */
    public static void printStringReverse(String s) {
        IterativeStack<Character> stack = new IterativeStack<Character>();
        for(int i = 0; i < s.length(); i++) {
            stack.push(s.charAt(i));
        }

        for(Character c : stack) System.out.print(c);
    }

    /**
     * Main function used for testing the class
     * @param args arguments from program execution
     */
    public static void main(String[] args) {
        System.out.print("\nHello please enter a command: \npush - add item to the stack \npop - remove item from the stack \nsize - get number of elements in stack\nisEmpty - know if the stack is empty\nrs - reverse a string\nquit - exit the program\n\nYour choice: ");
        Scanner input = new Scanner(System.in);
        IterativeStack<Double> testingStack = new IterativeStack<Double>();
        outerwhile:
        while(true) {
            String cmd = input.next();

            switch(cmd) {
                case "push":
                    System.out.println("What number do you want to add? ");
                    testingStack.push(input.nextDouble());
                    break;
                case "pop":
                    double poppedNumber = testingStack.pop();
                    System.out.println("[pop]: You received the number: " + poppedNumber);
                    break;
                case "size":
                    double size = testingStack.size();
                    System.out.println("[size]: You received the number: " + size);
                    break;
                case "isEmpty":
                    boolean isEmpty = testingStack.isEmpty();
                    System.out.println("[isEmpty]: You received the result: " + isEmpty);
                    break;
                case "rs":
                    printStringReverse(input.next());
                    break;
                case "quit": break outerwhile;
                default:
                    System.out.println("Unknown command");
                    break;
            }
            System.out.println("\n\nThe stack now looks like this:");
            System.out.println(testingStack);
        }
        input.close();
    }
}