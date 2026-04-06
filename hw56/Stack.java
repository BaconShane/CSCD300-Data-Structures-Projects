import java.util.EmptyStackException;

public class Stack {

    private Node head;
    private int size;

    public class Node {
        Object data;
        Node next;

        private Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public Stack() {
        this.head = null;
        this.size = 0;
    }

    //Checks if Stack is Empty
    public boolean isEmpty() {
        return size == 0;
    }

    //Returns the size of the stack
    public int size() {
        return this.size;
    }

    //Adds an object to the Stack
    public void push(Object data) {
        Node newNode = new Node(data, null);
        if (this.head == null) {
            this.head = newNode;
        } else {
            newNode.next = this.head;
            this.head = newNode;
        }
        this.size++;
    }

    //Returns and removes the object at the top of the Stack
    public Object pop() throws EmptyStackException {
        if (this.head == null) {
            throw new EmptyStackException();
        }
        Object data = this.head.data;
        this.head = this.head.next;
        this.size--;
        return data;
    }

    //Returns the object at the top of the Stack
    public Object peek() throws EmptyStackException {
        if (this.head == null) {
            throw new EmptyStackException();
        }
        return this.head.data;
    }
}
