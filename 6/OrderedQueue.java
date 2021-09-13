/**
 * @author Kalle Elmdahl 21/09/07 (updated 21/09/13)
 * This code is an implmentation of an ordered queue
 * The code is for an assignment from the KTH-course ID1020
 * The code is based on examples from https://algs4.cs.princeton.edu/home/
 */
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The A queue implmentation where all elements in the queue are sorted in ascending order.
 */
public class OrderedQueue implements Iterable<Integer>  {
    private Node first;
    private int n;

    private static class Node {
        private Integer item;
        private Node next;
    }

    /**
     * initializes the OrderedQueue class
     */
    public OrderedQueue() {
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
    public void enqueue(Integer item) {
        Node target = first;
        Node newNode = new Node();
        newNode.item = item;
        if(isEmpty())
            first = newNode;
        else if(first.item > item) {
            newNode.next = first;
            first = newNode;
        } else {
            while(target.next != null && target.next.item < item)
                target = target.next;
            
            newNode.next = target.next;
            target.next = newNode;
        }
        
        n++;
    }

    /**
     * Removes an item from the queue
     * @return the removed item
     */
    public Integer dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Cant remove from empty list");
        Integer item = first.item;
        first = first.next;
        n--;
        return item;
    }

    /**
     * Generates a s tring representation of the stack
     * @return the string representation
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        Node node = first;
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
    public Iterator<Integer> iterator() {
        return new LinkedIterator(first);  
    }
    
    private class LinkedIterator implements Iterator<Integer> {
        private Node current;

        /**
         * Initializes the iterator
         * @param first first node in the linked list
         */
        public LinkedIterator(Node first) {
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
        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            Integer item = current.item;
            current = current.next; 
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
        OrderedQueue testingqueue = new OrderedQueue();
        outerwhile:
        while(true) {
            System.out.print("$ ");
            String cmd = input.next();

            switch(cmd) {
                case "enqueue":
                    System.out.println("What number do you want to add? ");
                    testingqueue.enqueue(input.nextInt());
                    break;
                case "dequeue":
                    int returnValue = testingqueue.dequeue();
                    System.out.println("Received value: " + returnValue);
                    break;
                case "size":
                    double size = testingqueue.size();
                    System.out.println("[size]: You received the number: " + size);
                    break;
                    case "isEmpty":
                    boolean isEmpty = testingqueue.isEmpty();
                    System.out.println("[isEmpty]: You received the result: " + isEmpty);
                    break;
                case "quit": break outerwhile;
                default:
                    System.out.println("Unknown command");
                    break;
            }
            System.out.println("\n\nThe queue now looks like this:");
            System.out.println(testingqueue);
        }
        input.close();
    }
}
