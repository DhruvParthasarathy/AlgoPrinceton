import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	private Node head;
	private Node tail;
	private int numNodes = 0;

	private class Node {

		private Node next = null;
		private Item data = null;

	}

	// construct an empty deque
	public Deque() {
		head = null;
		tail = null;
	}

	// is the deque empty?
	public boolean isEmpty() {

		return numNodes == 0;
	}

	// return the number of items on the deque
	public int size() {

		return numNodes;
	}

	// add the item to the front
	public void addFirst(Item item) {

		if(item == null) {
			throw new IllegalArgumentException();
		}
		Node newNode = new Node();
		newNode.data = item;
		if (numNodes==0) {
			head = newNode;
			tail = head;
		}
		else {
			newNode.next = head;
			head = newNode;
		}
		numNodes += 1;


	}

	// add the item to the back
	public void addLast(Item item) {

		if(item == null) {
			throw new IllegalArgumentException();
		}
		
		Node newNode = new Node();
		newNode.data = item;
		if(numNodes == 0 ) {
			tail = newNode;
			head=tail;
		} else {
			tail.next = newNode;
			tail = newNode;
		}

		numNodes += 1;

	}

	// remove and return the item from the front
	public Item removeFirst() {
		if(numNodes == 0 ) {
			throw new NoSuchElementException();
		}		
		Node currentNode = head;
		head = head.next;
		numNodes -= 1;		
		return currentNode.data;
	}

	// remove and return the item from the back
	public Item removeLast() {
		if(numNodes == 0 ) {
			throw new NoSuchElementException();
		}
		Item returnValue = null;
		Node currentNode = head;
		
		if(numNodes == 1) {
			returnValue = currentNode.data;
			head = null;
			tail = null;
		} else {
			if(currentNode.next.next==null) {
				returnValue = currentNode.next.data;
				tail = currentNode;
			} else {
				while (currentNode.next.next != null) {
					returnValue = currentNode.next.next.data;
					currentNode = currentNode.next;
					tail = currentNode;
				}
			}


			tail.next = null;
		}

		numNodes -= 1;
		return returnValue;

	}
	
	private class dqIterator implements Iterator<Item> {

		private Node startPoint = head;
		
		@Override
		public boolean hasNext() {
			return startPoint != null;
		}

		@Override
		public Item next() {
			
			if(!hasNext()) {
				throw new NoSuchElementException();
			 } else {
				 Node returnNode = startPoint;
				 startPoint = startPoint.next;
				 return returnNode.data;
			 }
			
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}

	@Override
	public Iterator<Item> iterator() {
		return new dqIterator();
	}

	// unit testing (required)
	public static void main(String[] args) {
		Deque<Integer> queue = new Deque<Integer>();
		
		System.out.println("\nTesting if isEmpty functionality works");
		if(queue.isEmpty()) {
			System.out.println("\nYes the queue is empty now\n");
		} else System.out.println("\nIs empty is throwing false even when the queue is empty\n");
		
		
		System.out.println("\nTesting if size functionality works");
		if(queue.size() == 0) {
			System.out.println("\nYes the queue is empty now\n");
		} else System.out.println("\nIs size is giving a value even when the queue is empty\n");
		
		System.out.println("\nAdding 4 to first");
		queue.addFirst(4);
		
		System.out.println("\nAdding 5 to first");
		queue.addFirst(5);		
		
		System.out.println("\nRemoving from last " + queue.removeLast());
		
		System.out.println("\nAdding 2 to first");
		queue.addFirst(2);
		System.out.println("\nAdding 3 to first");
		queue.addFirst(3);
		System.out.println("\nAdding 4 to first");
		queue.addFirst(4);
		System.out.println("\nAdding 50 to first");
		queue.addFirst(50);
		System.out.println("\nAdding 2 to last");
		queue.addLast(2);
		System.out.println("\nAdding 3 to last");
		queue.addLast(3);
		System.out.println("\nAdding 4 to last");
		queue.addLast(4);
		System.out.println("\nAdding 15 to last");
		queue.addLast(15);
		
		
		queue.printFunc();
		
		System.out.println("\nTesting if size functionality works");
		if(queue.size() == 9) {
			System.out.println("\nYes the queue size is equal to 9 now\n");
		} else System.out.println("\nIs size is giving a wrong value even when the queue size is 9\n");
		
		
		System.out.println("\n");
		System.out.println("\nRemoving from first " + queue.removeFirst());
		System.out.println("\nRemoving from first " + queue.removeFirst());
		System.out.println("\nRemoving from last " + queue.removeLast());
		System.out.println("\nRemoving from last " + queue.removeLast());
		queue.printAfterRemoval();
		
		Iterator<Integer> queueIterator = queue.iterator();
		System.out.println("\nIterating through elements to test iterator");
		int iteratedValue=0;
		while(queueIterator.hasNext()) {
			iteratedValue = queueIterator.next();
			System.out.println("\n"+ iteratedValue);
			if(iteratedValue == 1) {
				queueIterator.remove();
			}
		}
		
		System.out.println("\nTesting Iterator remove");
		
		queueIterator.remove();
	}

	private void printAfterRemoval() {
		Node currentNode = head;
		System.out.println("\n");
		while (currentNode != null) {
			System.out.println(currentNode.data);
			currentNode = currentNode.next;
		}

		System.out.println("\nNumber of nodes = " + numNodes);
		System.out.println("\nHead Node = " + head.data);
		System.out.println("\nTail Node = " + tail.data);

	}

	private void printFunc() {
		Node currentNode = head;
		while (currentNode != null) {
			System.out.println(currentNode.data);
			currentNode = currentNode.next;
		}

		System.out.println("\nNumber of nodes = " + numNodes);
		System.out.println("\nHead Node = " + head.data);
		System.out.println("\nTail Node = " + tail.data);
	}

}
