import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularLinkedList {
    public static class Stack<Item> implements Iterable<Item> {
        private Node<Item> first;
        private Node<Item> last;
        private int n;
    
        private static class Node<Item> {
            private Item item;
            private Node<Item> next;
        }

        public Stack() {
            first = null;
            n = 0;
        }
    
        public boolean isEmpty() {
            return first == null || last == null;
        }
    
        public int size() {
            return n;
        }

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
    
        public Item removeFromStart() {
            if (isEmpty()) throw new NoSuchElementException("Stack underflow");
            Item item = first.item;
            first = first.next;
            last.next = first;
            n--;
            return item;
        }

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

        public Item removeFromEnd() {
            Node<Item> node = first;
            while(node.next != last) node = node.next;
            Item item = last.item;
            node.next = first;
            last = node;

            n--;
            return item;
        }
        
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
           
        public Iterator<Item> iterator() {
            return new LinkedIterator(first);
        }
    
        private class LinkedIterator implements Iterator<Item> {
            private Node<Item> current;
            private int iterationCount;
    
            public LinkedIterator(Node<Item> first) {
                current = first;
                iterationCount = 0;
            }
    
            public boolean hasNext() {
                return iterationCount != n;  
            }
    
            public Item next() {
                if (!hasNext()) throw new NoSuchElementException();
                Item item = current.item;
                current = current.next; 
                iterationCount++;
                return item;
            }
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();

        stack.addToEnd(1);
        System.out.println(stack);
        stack.addToStart(2);
        System.out.println(stack);
        stack.addToEnd(4);
        System.out.println(stack);
        
        stack.removeFromStart();
        System.out.println(stack);
        stack.removeFromEnd();
        System.out.println(stack);
    }
}
