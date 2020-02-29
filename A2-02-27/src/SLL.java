import java.util.Comparator;
import java.util.Iterator;

public class SLL<T extends Comparable<T>> implements Iterable<T> {
	private Node<T> head;
	private Node<T> tail;
	private int size = 0;
	Comparator<T> comparator = null;

	public SLL() {
		head = null;
		tail = null;
		size = 0;
	}

	public SLL(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	// Public Methods

	/**
	 * Return the number of elements in the list.
	 * @return int number of elements in the list.
	 */
	public int size() {
		return size;
	}

	/**
	 * Adds data to linked list depending on order.
	 * Adds natural order if no comparator is provided.
	 * @param data is what will be added to node
	 */
	public void addInOrder(T data) {

		Node<T> curNode = new Node<T>(data);
		if (head == null) {
			addHead(curNode);
		} else if (comparator != null) {
			addInComparatorOrder(curNode);
		} else {
			addInNaturalOrder(curNode);
		}
		size ++;

	}


	// addInOrder helper methods

	/**
	 * Will add the Node in to the linked list depending on the natural order.
	 * @param curNode the Node to be added into the list.
	 */
	private void addInNaturalOrder(Node<T> curNode) {

		if (curNode.getData().compareTo(head.getData()) <= 0) {
			addHead(curNode);
		} else {
			Node<T> currentNode = head;
			while (currentNode.getNext() != null && curNode.getData().compareTo(currentNode.getNext().getData()) > 0) {
				currentNode = currentNode.getNext();
			}

			if (currentNode.getNext() == null) {
				addTail(curNode);
			} else {
				curNode.setNext(currentNode.getNext());
				currentNode.setNext(curNode);
			}
		}
	}

	/**
	 * Will add the NOde int on the linked list depending on the comparator order.
	 * @param curNode the node to be added into the list.
	 */
	private void addInComparatorOrder(Node<T> curNode) {
		if (comparator.compare(curNode.getData(), head.getData()) <= 0) {
			addHead(curNode);
		} else {
			Node<T> currentNode = head;
			while(currentNode.getNext() != null && comparator.compare(curNode.getData(),currentNode.getNext().getData()) > 0) {
				currentNode = currentNode.getNext();
			}

			if (currentNode.getNext() == null) {
				addTail(curNode);
			} else {
				curNode.setNext(currentNode.getNext());
				currentNode.setNext(curNode);
			}			
		}
	}


	/**
	 * Will tell if the list is empty.
	 * @return true if if the list is empty
	 */
	public boolean isEmpty() {
		return head == null && tail == null;
	}


	/** Return the ith element of the list.
	 *  @param index the element to return
	 *  @return the ith element, null if there isnt one.
	 */
	public T get( int index)
	{
		Node<T> curr = head;
		int j = 0;
		while ( curr != null && j < index)
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

	/**
	 * Add a new Node to the head of the list.
	 * @param node the node to be added to the head.
	 */
	public void addHead(Node<T> node) {
		if (head == null) {
			tail = node;
			node.setNext(null);
		} else {
			node.setNext(head);
		}
		head = node;
	}

	/**
	 * Add a new Node to the tail of the list.
	 * @param node the node to be added to the tail.
	 */
	public void addTail(Node<T> node) {
		if (tail == null) {
			head = node;
		} else {
			tail.setNext(node);
		}
		tail = node;
		node.setNext(null);
	}

	// Idea from Pedro & Nahuel to move Node in SLL
	private static class Node<T extends Comparable<T>> {
		private T data;
		private Node<T> next;

		/**
		 * Constructor for objects of class Node
		 */
		public Node(T d) {
			data = d;
			next = null;
		}

		public T getData() {
			return data;
		}

		public void setData(T o) {
			data = o;
		}

		public Node<T> getNext() {
			return next;
		}

		public void setNext(Node<T> n) {
			next = n;
		}

		public String toString() {
			return "Node: " + getData().toString();
		}
	}

	// Idea from Jordan Pratt to use Iterator and help from Pedro

	/**
	 * Allows the use of for each loop in A2 to iterate through the linked list
	 */
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private Node<T> curr = head;

			@Override
			public boolean hasNext() {
				return curr != null;
			}

			@Override
			public T next() {
				T afterCurr = curr.getData();
				curr = curr.getNext();
				return afterCurr;
			}
		};

	}
}