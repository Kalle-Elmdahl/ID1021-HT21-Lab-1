import java.util.Iterator;
import java.util.NoSuchElementException;

public class Assignment {
    private static class Queue<Item> implements Iterable<Item> {
        private Node<Item> first;
        private Node<Item> last;
        private int n;

        private static class Node<Item> {
            private Item item;
            private Node<Item> next;
            private Node<Item> prev;
        }

        public Queue() {
            n = 0;
        }
        
        public boolean isEmpty() {
            return first == null;
        }

        public int size() {
            return n;
        }

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

        public Item dequeue() {
            if (isEmpty()) throw new NoSuchElementException("Cant remove when queue is empty");
            Item item = first.item;
            first = first.next;
            first.prev = last;
            last.next = first;
            n--;
            if (isEmpty()) last = null;   // to avoid loitering
            return item;
        }

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

        
        public Iterator<Item> iterator()  {
            return new LinkedIterator(first);  
        }

        private class LinkedIterator implements Iterator<Item> {
            private Node<Item> current;
            private int iterationCount;

            public LinkedIterator(Node<Item> first) {
                current = first;
                iterationCount = 0;
            }

            public boolean hasNext()  {
                return iterationCount != n;                     
            }

            public Item next() {
                Item item = current.item;
                current = current.next;
                iterationCount++;
                return item;
            }
        }
    }

    public static void main(String[] args) {
        Queue<Integer> numbers = new Queue<Integer>();
        numbers.enqueue(1);
        System.out.println(numbers);
        numbers.enqueue(2);
        System.out.println(numbers);
        numbers.enqueue(3);
        System.out.println(numbers);


        System.out.println(numbers.size());
        
        System.out.println(numbers.dequeue());
        System.out.println(numbers);
        System.out.println(numbers.dequeue());
        System.out.println(numbers);
        System.out.println(numbers.dequeue());
        System.out.println(numbers);
    }
}
