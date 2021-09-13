/**
 * @author Kalle Elmdahl 21/09/07 (updated 21/09/09)
 * The code is used to see if parentheses of different types are matched in a string
 * The code is for an assignment from the KTH-course ID1020
 * The code is based on examples from https://algs4.cs.princeton.edu/home/
 */
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class is used for checking if a String has balanced parentheses
 */
public class BalanceParentheses {

    /**
     * This class is a basic generalized Stack
     */
    private static class Stack<Item> implements Iterable<Item> {
        private Node first;
        private int size;

        private class Node {
            public Item item;
            public Node next;
        }

        public Stack() {
            size = 0;
        }
    
        /**
         * Adds one item to the stack.
         * @param item the item added
         */
        void push(Item item) {            
            Node oldFirst = first;

            first = new Node();
            first.item = item;
            first.next = oldFirst;
            size++;
        }

        /**
         * Removes an item from the stack
         * @return the removed item
         */
        Item pop() {
            if(first == null) throw new NoSuchElementException("List is empty");
            Item item = first.item;
            first = first.next;
            size--;
            return item;
        }

        /**
         * Know if the stack is empty.
         * @return {@code true} if the stack is empty else {@code false}.
         */
        boolean isEmpty() {
            return size == 0;
        }

        /**
         * Get the number of items currently in the stack.
         * @return the size.
         */
        int size() {
            return size;
        }

        /**
         * Generates a s tring representation of the stack
         * @return the string representation
         */
        public String toString() {
            StringBuilder s = new StringBuilder();
            Node node = first;
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
            public Item next() {
                Item item = current.item;
                current = current.next; 
                return item;
            }
        }

        /*
         * For stack testing see iterativeStack in the "2" folder
         */
    }
    /**
     * Know if string has a balanced amount of matching parentheses for example ({}[])
     * @param s a string of parentheses, accepted types are "([{"
     * @return if the string is balanced, "{()[]}" -> {@code true} "({)}" -> {@code false}
     */
    public static boolean isBalancedParentheses(String s) {
        Stack<Character> stack = new Stack<Character>();
        for(int i = 0; i < s.length(); i++)
            if("({[".indexOf(s.charAt(i)) != -1) 
                stack.push(s.charAt(i));
            else if(stack.isEmpty() || !"(){}[]".contains("" + stack.pop() + s.charAt(i))) 
                return false;
        
        return stack.isEmpty();
    }

    /**
     * Main function used for testing the class
     * @param args arguments from program execution
     */
    public static void main(String[] args) {
        System.out.print("\n Enter a string of parentheses (enter quit to exit) \n$ ");
        Scanner input = new Scanner(System.in);
        while(true) {
            String s = input.nextLine();
            if(s.contains("quit")) break;

            // true ({[[({})()()()]{[(([]))]([[[]]])}]()()}[[]])
            // false ({[[({})()()()]{[(([]))]([[]]])}]()()}[[]])

            boolean result = isBalancedParentheses(s);
            System.out.println("Result: " + result);
        }
        input.close();
    }
}