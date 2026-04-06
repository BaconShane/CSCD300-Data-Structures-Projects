import java.util.EmptyStackException;

public class Queue {

    private Node head;
    private Node tail;
    private int size;

    public class Node {
        LinkedList data;
        Node next;

        private Node(LinkedList data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public Queue() {
        this.head = null;
        this.size = 0;
    }

    //Checks if Queue is Empty
    public boolean isEmpty() {
        return size == 0;
    }

    //Returns the size of the queue
    public int size() {
        return this.size;
    }

    public Object front() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        return this.head.data;
    }

    public void enqueue(LinkedList data) {
        if (this.isEmpty()) {
            this.head = new Node(data, null);
            this.tail = this.head;
        } else {
            this.tail.next = new Node(data, null);
            this.tail = this.tail.next;
        }
        this.size++;
    }

    public LinkedList dequeue() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        LinkedList temp = this.head.data;

        if (this.head.next == null) {
            this.head = null;
        } else {
            this.head = this.head.next;
        }

        this.size--;
        return temp;
    }
}
