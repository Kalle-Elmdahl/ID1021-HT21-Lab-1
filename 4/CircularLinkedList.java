/**
 * @author Kalle Elmdahl 21/09/07 (updated 21/09/11)
 * The code implements the circular linked list ADT
 * The code is for an assignment from the KTH-course ID1020
 * The code is based on examples from https://algs4.cs.princeton.edu/home/
 */
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * A generic iterable circular linked list
 */
public class CircularLinkedList<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int n;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    /**
     * Initiates the circular linked list
     */
    public CircularLinkedList() {
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
     * Adds one item to the start of the list.
     * @param item the item added
     */
    public void addToStart(Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        if(isEmpty()) {
            last = first;
            first.next = last;
        } else
            last.next = first;

        n++;
    }

    /**
     * Removes one item to from start of the list.
     * @return The item removed
     */
    public Item removeFromStart() {
        if (isEmpty()) throw new NoSuchElementException("Cant remove from empty queue");
        Item item = first.item;
        first = first.next;
        last.next = first;
        n--;
        return item;
    }

    /**
     * Adds one item to the end of the list.
     * @param item the item added
     */
    public void addToEnd(Item item) {
        Node<Item> oldLast = last;
        last = new Node<Item>();
        last.item = item;
        if(isEmpty())
            first = last;
        else
            oldLast.next = last;

        last.next = first;
        n++;
    }

    /**
     * Removes one item to from end of the list.
     * @return The item removed
     */
    public Item removeFromEnd() {
        if (isEmpty()) throw new NoSuchElementException("Cant remove from empty queue");
        Node<Item> node = first;
        while(node.next != last) node = node.next;
        Item item = last.item;
        node.next = first;
        last = node;

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
            s.append("\n[Node " + i + ": " + node);
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
        public boolean hasNext() {
            return iterationCount != n;  
        }

        /**
         * Get the next item in the list
         * @return the item
         */
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
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
        System.out.print("\nEnter a command: \naddToStart - add item to the start of list \naddToEnd - add item to the end of list\nremoveFromStart - remove item from the start of the list \nnremoveFromEnd - remove item from the end of the list \nsize - get number of elements in list\nisEmpty - know if the stack is empty\nquit - exit the program\n");
        Scanner input = new Scanner(System.in);
        CircularLinkedList<Double> testingStack = new CircularLinkedList<Double>();
        outerwhile:
        while(true) {
            System.out.print("$ ");
            String cmd = input.next();

            switch(cmd) {
                case "addToStart":
                    System.out.println("What number do you want to add? ");
                    testingStack.addToStart(input.nextDouble());
                    break;
                case "addToEnd":
                    System.out.println("What number do you want to add? ");
                    testingStack.addToEnd(input.nextDouble());
                    break;
                case "removeFromStart":
                    double start = testingStack.removeFromStart();
                    System.out.println("[dequeue]: You received the number: " + start);
                    break;
                case "removeFromEnd":
                    double end = testingStack.removeFromEnd();
                    System.out.println("[dequeue]: You received the number: " + end);
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
