 import java.util.Comparator;
import java.util.Iterator;

public class SLL<T extends Comparable<T>> implements Iterable<T>
{
    private Node<T> head;
    private Node<T> tail;
    private int size = 0;
    Comparator<T> comparator = null;

    public SLL()
    {
        head = null;
        tail = null;
        size = 0;
    }

    public SLL(Comparator<T> comparator)
    {

    }

    // Public Methods

    /** Return the number of elements in the list.
     *  @return int number of elements  in the list.
     */
    public int size()
    {
        return size;
    }

    /** Empty the list.
     */
    public void emptyList()
    {
        head = null;
        tail = null;
        size = 0;
    }

    public void addInOrder()
    {

    }

    /** Return the ith element of the list.
     *  @param i the element to return
     *  @return the ith element, null if there isnt one.
     */
    public T get( int i)
    {
        Node<T> curr = head;
        int j = 0;
        while ( curr != null && j < i)
        {
            curr = curr.getNext();
            j++;
        }
        if ( curr != null)
            return curr.getData();
        else
            return null;
    }


    // Private methods

    /* Add a new Node to the head of the list.
     */
    private void addHead( Node<T> n)
    {
        if (head == null) // empty list
        {
            head = n;
            tail = n;
        }
        else
        {
            n.setNext(head);
            head = n;
        }
        size++;
    }

    /* Delete the node at the head of the list and return a pointer to it.
     */
    private Node<T> delHead()
    {
        Node<T> n = null;
        if ( head != null)
        {
            n = head;
            if ( head == tail)
                tail = head.getNext();
            head = head.getNext();
            size--;
        }
        return n;
    }



    //Idea from Pedro & Nahuel to move Node in SLL
    private static class Node<T extends Comparable<T>>
    {

        private T data;
        private Node<T> next;

        /**
         * Constructor for objects of class Node
         */
        public Node(T d)
        {
            data = d;
            next = null;
        }

        public T getData() { return data; }
        public void setData(T o) { data = o; }

        public Node<T> getNext() { return next; }
        public void setNext(Node<T> n) { next = n; }
        public String toString()
        {
            return "Node: " + getData().toString();
        }
    }

    //Idea from Jordan Pratt to use Iterator
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>()
        {
            private Node<T> curr = head;

            @Override
            public boolean hasNext()
            {
               return curr != null;
            }

            @Override
            public T next()
            {
            		T afterCurr = curr.getData();
                	curr = curr.getNext();
                	return afterCurr;
            }
        };

    }
}


