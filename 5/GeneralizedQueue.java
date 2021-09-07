import java.util.Iterator;
import java.util.NoSuchElementException;

public class GeneralizedQueue {
    public static class Queue<Item> implements Iterable<Item> {
        private Node<Item> first;    // beginning of queue
        private Node<Item> last;     // end of queue
        private int n;               // number of elements on queue
    
        // helper linked list class
        private static class Node<Item> {
            private Item item;
            private Node<Item> next;
        }
        
        public Queue() {
            first = null;
            last  = null;
            n = 0;
        }
        
        public boolean isEmpty() {
            return first == null;
        }

        public int size() {
            return n;
        }
    
        public void enqueue(Item item) {
            Node<Item> oldlast = last;
            last = new Node<Item>();
            last.item = item;
            last.next = null;
            if (isEmpty()) first = last;
            else           oldlast.next = last;
            n++;
        }
    
        public Item dequeue() {
            if (isEmpty()) throw new NoSuchElementException("Queue underflow");
            Item item = first.item;
            first = first.next;
            n--;
            if (isEmpty()) last = null;
            return item;
        }

        public Item remove(int target) {
            target = n - target;
            Item item;
            if(target == 0) {
                item = first.item;
                first = first.next;
            } else {
                Node<Item> node = first;
                for(int i = 1; i < target; i++) node = node.next;
    
                item = node.next.item;
                node.next = node.next.next;
            }
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
    
        /**
         * Returns an iterator that iterates over the items in this queue in FIFO order.
         *
         * @return an iterator that iterates over the items in this queue in FIFO order
         */
        public Iterator<Item> iterator() {
            return new LinkedIterator(first);  
        }
    
        // an iterator, doesn't implement remove() since it's optional
        private class LinkedIterator implements Iterator<Item> {
            private Node<Item> current;
    
            public LinkedIterator(Node<Item> first) {
                current = first;
            }
    
            public boolean hasNext() { 
                return current != null;                     
            }
            public void remove() { 
                throw new UnsupportedOperationException();  
            }
    
            public Item next() {
                if (!hasNext()) throw new NoSuchElementException();
                Item item = current.item;
                current = current.next; 
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
        numbers.enqueue(4);
        numbers.enqueue(5);
        numbers.enqueue(6);
        numbers.enqueue(7);
        System.out.println(numbers);
        
        numbers.remove(7);
        System.out.println(numbers);

/* 
        System.out.println(numbers.size());
        
        System.out.println(numbers.dequeue());
        System.out.println(numbers);
        System.out.println(numbers.dequeue());
        System.out.println(numbers);
        System.out.println(numbers.dequeue());
        System.out.println(numbers); */
    }
}