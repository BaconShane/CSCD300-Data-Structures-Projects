public class LinkedList {

    private class Node {
        private Object data;   //Assume data implemented Comparable
        private Node next, prev;

        private Node(Object data, Node pref, Node next)
        {
            this.data = data;
            this.prev = pref;
            this.next = next;
        }
    }

    private Node head;
    private int size;

    public LinkedList() {
        this.head = new Node(null, null, null );
        this.head.next = this.head;
        this.head.prev = this.head;
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.head == this.head.next;
    }

    public int getSize() {
        return this.size;
    }

    public void removeFirst() {
        if (this.isEmpty()) {
            return;
        }
        this.head.next = this.head.next.next;
        this.head.next.prev = this.head;
        this.size--;
    }

    public void addFirst(Object data) {
        Node nn = new Node(data, this.head, this.head.next);
        this.head.next.prev = nn;
        this.head.next = nn;
        this.size ++;
    }

    public void addLast(Object data) {
        Node nn = new Node(data, this.head.prev, this.head);
        this.head.prev.next = nn;
        this.head.prev = nn;
        this.size++;
    }

    public void InsertionSort() {
        Node lastSorted, sortedWalker;
        Comparable firstUnsortedData;

        for (lastSorted = this.head.next; lastSorted != this.head.prev; lastSorted = lastSorted.next) {
            firstUnsortedData = (Comparable)lastSorted.next.data;

            for (sortedWalker = lastSorted; sortedWalker != head && ((Comparable)sortedWalker.data).compareTo(firstUnsortedData) > 0; sortedWalker = sortedWalker.prev) {
                sortedWalker.next.data = sortedWalker.data;
            }
            sortedWalker.next.data = firstUnsortedData;
        }
    }

    public LinkedList merge(LinkedList A, LinkedList B) {
        LinkedList S = new LinkedList();
        while (!A.isEmpty() && !B.isEmpty()) {
            Object fa = A.head.next.data; //return the first piece of data element in A, but not delete
            Object fb = B.head.next.data;
            if ((int)fa < (int)fb) {
                A.removeFirst(); //A becomes shorter
                S.addLast(fa); // supposed to have O(1) time complexity
            } else {
                B.removeFirst(); //B becomes shorter
                S.addLast(fb); // supposed to have O(1) time complexity
            }
        }
        while(!A.isEmpty()) {
            Object fa = A.head.next.data;
            S.addLast(fa); // supposed to have O(1) time complexity
            A.removeFirst();
        }
        while (!B.isEmpty()) {
            Object fb = B.head.next.data;
            S.addLast(fb); // supposed to have O(1) time complexity
            B.removeFirst();
        }
        return S;
    }

    public void MergeSort() {
        //Step 1: enqueue all data as linkedLists in queue
        Queue queue = new Queue();
        for (Node cur = this.head.next; cur != this.head; cur = cur.next) {
            LinkedList elem = new LinkedList();
            elem.addFirst(cur.data);
            queue.enqueue(elem);
        }

        //Step 2: Use the merge method to sort the queue elements
        while (queue.size() > 1) {
            LinkedList sublist1 = queue.dequeue();
            LinkedList sublist2 = queue.dequeue();
            LinkedList temp = merge(sublist1, sublist2);
            queue.enqueue(temp);
        }

        //Step 3: Dequeue the sorted list and set the original list equal to the new one
        LinkedList temp = queue.dequeue();
        this.head = temp.head;
    }

    public boolean isSorted() {
        boolean sorted = true;
        for (Node cur = this.head.next; cur != this.head.prev; cur = cur.next) {
            if ((int)cur.data > (int)cur.next.data) {
                sorted = false;
                break;
            }
        }
        return sorted;
    }

    @Override
    public String toString() {
        String result = "{";
        for (Node node = this.head.next; node != this.head; node = node.next) {
            if(node.next != this.head)
                result += node.data + "->";
            else
                result += node.data;
        }
        return result + "}";
    }
}
