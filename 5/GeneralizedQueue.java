/**
 * @author Kalle Elmdahl 21/09/07 (updated 21/09/11)
 * This code is an implmentation of a queue abstract data type
 * The code is for an assignment from the KTH-course ID1020
 * The code is based on examples from https://algs4.cs.princeton.edu/home/
 */
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * A generlized queue with the ability to remove an element with an index
 */
public class GeneralizedQueue<Item> implements Iterable<Item> {
    private Node<Item> first;
    private int n;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    /**
     * Initiates the class
     */
    public GeneralizedQueue() {
        first = null;
        n = 0;
    }

    /**
     * Know if the stack is empty.
     * @return {@code true} if the stack is empty else {@code false}.
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Get the number of items currently in the list.
     * @return the size.
     */
    public int size() {
        return n;
    }

    /**
     * Adds one item to the queue.
     * @param item the item added
     */
    public void enqueue(Item item) {
        Node<Item> oldFirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldFirst;
        n++;
    }

    /**
     * Removes an item from the queue at an index
     * @return the removed item
     */
    public Item remove(int target) {
        if(target < 1 || target > n) throw new IndexOutOfBoundsException("Invalid index");
        Item item;
        Node<Item> node = first;
        if(target == 1) {
            item = first.item;
            first = first.next;
        } else {
            for(int i = 2; i < target; i++) node = node.next;
            item = node.next.item;
            node.next = node.next.next;
        }
        n--;
        return item;
    }

    /**
     * Generates a s tring representation of the stack
     * @return the string representation
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        Node<Item> node = first;

        for(int i = 0; i < n; i++) {
            s.append("\n[Node " + (i + 1) + ": " + node);
            s.append("\nValue: " + node.item);
            s.append("\nNext: " + node.next + "],\n");
            node = node.next;
        }
        return s.toString();
    } 

    /**
     * Get an instance of an iterator class. based on the standardized iterator {@see https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html}
     * @return the iterator.
     */
    public Iterator<Item> iterator() {
        return new LinkedIterator(first);  
    }
    
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
     * Main function used for testing the class
     * @param args arguments from program execution
     */
    public static void main(String[] args) {
        System.out.print("\nEnter a command: \nenqueue - add item to the list \nremove - remove item from the list at index (most recent = 1) \nsize - get number of elements in list\nisEmpty - know if the stack is empty\nquit - exit the program\n");
        Scanner input = new Scanner(System.in);
        GeneralizedQueue<Double> testingStack = new GeneralizedQueue<Double>();
        outerwhile:
        while(true) {
            System.out.print("$ ");
            String cmd = input.next();

            switch(cmd) {
                case "enqueue":
                    System.out.println("What number do you want to add? ");
                    testingStack.enqueue(input.nextDouble());
                    break;
                case "remove":
                    System.out.println("At what index do you want to remove");
                    Double returnValue = testingStack.remove(input.nextInt());
                    System.out.println("Received value: " + returnValue);
                    break;
                case "size":
                    int size = testingStack.size();
                    System.out.println("[size]: You received the number: " + size);
                    break;
                    case "isEmpty":
                    boolean isEmpty = testingStack.isEmpty();
                    System.out.println("[isEmpty]: You received the result: " + isEmpty);
                    break;
                case "quit": break outerwhile;
                default:
                    System.out.println("Unknown command");
                    break;
            }
            System.out.println("\n\nThe queue now looks like this:");
            System.out.println(testingStack);
        }
        input.close();
    }
}