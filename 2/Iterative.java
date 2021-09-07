import java.util.Iterator;
import java.util.Scanner;

public class Iterative {
    private static class Stack<Item> implements Iterable<Item> {
        private Node first;

        private class Node {
            public Item item;
            public Node next;
        }
        
        void push(Item item) {            
            Node oldFirst = first;

            first = new Node();
            first.item = item;
            first.next = oldFirst;
        }

        Item pop() {
            if(first == null) return null;
            Item item = first.item;
            first = first.next;
            return item;
        }

        boolean isEmpty() {
            return first == null;
        }

        int size() {
            if(first == null) return 0;
            int size = 1;
            Node n = first;
            while(n.next != null) {
                n = n.next;
                size++;
            }
            return size;
        }

        public Iterator<Item> iterator() {
            return new LinkedIterator(first);
        }

        private class LinkedIterator implements Iterator<Item> {
            private Node current;

            public LinkedIterator(Node first) {
                current = first;
            }

            public boolean hasNext() {
                return current != null;
            }

            public Item next() {
                Item item = current.item;
                current = current.next; 
                return item;
            }
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String string = input.next();
        input.close();
        
        Stack<Character> stack = new Stack<Character>();
        for(int i = 0; i < string.length(); i++) {
            stack.push(string.charAt(i));
        }

        for(Character c : stack) {
            System.out.print(c);
        }
        System.out.println();

    }
}