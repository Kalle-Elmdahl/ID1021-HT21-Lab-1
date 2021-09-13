/**
 * @author Kalle Elmdahl 21/09/07 (updated 21/09/09)
 * This code is an implmentation of a queue abstract data type
 * The code is for an assignment from the KTH-course ID1020
 * The code is based on examples from https://algs4.cs.princeton.edu/home/
 */
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The queue is a double linked generic circular FIFO-queue
 */
public class GenericFIFOQueue<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int n;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> prev;
    }

    /**
     * initializes the Queue class
     */
    public GenericFIFOQueue() {
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
        Node<Item> oldLast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = first;
        last.prev = oldLast;
        if (isEmpty()) {
            first = last;
            first.next = last;
            first.prev = last;
        } else {
            first.prev = last;
            oldLast.next = last;
        }
        n++;
    }

    /**
     * Removes an item from the queue
     * @return the removed item
     */
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Cant remove when queue is empty");
        Item item = first.item;
        first = first.next;
        first.prev = last;
        last.next = first;
        n--;
        if (isEmpty()) last = null;
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
            s.append("\n[Node " + i + ": " + node);
            s.append("\nPrev: " + node.prev);
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
    public Iterator<Item> iterator()  {
        return new LinkedIterator(first);  
    }

    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;
        private int iterationCount;

        /**
         * Initializes the iterator
         * @param first first node in the linked list
         */
        public LinkedIterator(Node<Item> first) {
            current = first;
            iterationCount = 0;
        }

        /**
         * Know if the iterator is done or not
         * @return {@code true} if there is another item
         */
        public boolean hasNext()  {
            return iterationCount != n;                     
        }

        /**
         * Get the next item in the list
         * @return the item
         */
        public Item next() {
            Item item = current.item;
            current = current.next;
            iterationCount++;
            return item;
        }
    }

    /**
     * Main function used for testing the class
     * @param args arguments from program execution
     */
    public static void main(String[] args) {
        System.out.print("\nEnter a command: \nenqueue - add item to the list \ndequeue - remove item from the list \nsize - get number of elements in list\nisEmpty - know if the stack is empty\nquit - exit the program\n");
        Scanner input = new Scanner(System.in);
        GenericFIFOQueue<Double> testingStack = new GenericFIFOQueue<Double>();
        outerwhile:
        while(true) {
            System.out.print("$ ");
            String cmd = input.next();

            switch(cmd) {
                case "enqueue":
                    System.out.println("What number do you want to add? ");
                    testingStack.enqueue(input.nextDouble());
                    break;
                case "dequeue":
                    double poppedNumber = testingStack.dequeue();
                    System.out.println("[dequeue]: You received the number: " + poppedNumber);
                    break;
                case "size":
                    double size = testingStack.size();
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
