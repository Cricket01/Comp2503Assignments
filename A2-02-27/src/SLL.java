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
	 * 
	 * @return int number of elements in the list.
	 */
	public int size() {
		return size;
	}

	/**
	 * Empty the list.
	 */
	public void emptyList() {
		head = null;
		tail = null;
		size = 0;
	}

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

	public void addInNaturalOrder(Node<T> curNode) {

		if (curNode.getData().compareTo(head.getData()) <= 0) {
			addHead(curNode);
		} else {
			Node<T> currentNode = head;
			while (currentNode.getNext() != null && curNode.getData().compareTo(currentNode.getNext().getData()) > 0) {
				currentNode = currentNode.getNext();
			}
			//TODO: code duplication 
			if (currentNode.getNext() == null) {
				addTail(curNode);
			} else {
				curNode.setNext(currentNode.getNext());
				currentNode.setNext(curNode);
			}
		}
	}

	public void addInComparatorOrder(Node<T> curNode) {
		if (comparator.compare(curNode.getData(), head.getData()) <= 0) {
			addHead(curNode);
		} else {
			Node<T> currentNode = head;
			while(currentNode.getNext() != null && comparator.compare(curNode.getData(),currentNode.getNext().getData()) > 0) {
				currentNode = currentNode.getNext();
			}
			//TODO: code duplication 
			if (currentNode.getNext() == null) {
				addTail(curNode);
			} else {
				curNode.setNext(currentNode.getNext());
				currentNode.setNext(curNode);
			}			
		}
	}

	public boolean isEmpty() {
		return head == null && tail == null;
	}

	// Private methods

	/*
	 * Add a new Node to the head of the list.
	 */
	public void addHead(Node<T> n) {
		if (head == null) {
			head = n;
			tail = n;
			n.setNext(null);
		} else {
			n.setNext(head);
			head = n;
		}
	}

	public void addTail(Node<T> n) {
		if (tail == null) {
			head = n;
			tail = n;
			n.setNext(null);
		} else {
			tail.setNext(n);
			tail = n;
			n.setNext(null);
		}
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