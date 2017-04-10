import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
    private Node first; // stores the first element of the linked list
    private Node last; // stores the last element of the linked list
    private int size; // store the deque size

    // constructs an empty Deque
    public Deque() {
		first = null;
		last = null;
		size = 0;
	}

	// the node class for linked list implementation
	private class Node {
		private Item item; // linked list content of generic type
		private Node next; // pointer to the next node in the list
		private Node previous; // pointer to previous node in the list
	}

	// for test UI
	private void displayOptions() {
		StdOut.println(" \n*************************** \nEnter your option (1-7)");
		StdOut.println(" 1. Add to the front \n 2. Add to the End \n 3. Display Size \n 4. Display Queue");
		StdOut.println(" 5. Remove from front \n 6. Remove from end \n 7. Exit \n");
		StdOut.println("*****************************************************");
	}
	// class to implement iterator
	private class IteratorClass implements Iterator<Item> {
		private Node current = first;

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}

		public Item next() {
			if (current == null) {
				throw new java.util.NoSuchElementException();
			}
			Item item = current.item;
			current = current.next;
			return item;
		}
	}

	// to check the input
	private void checkInputItem(Item item) {
		if (item == null)
			throw new java.lang.NullPointerException();
	}

	// to check if removing from an empty deque
	private void checkRemove() {
		if (size == 0) {
			throw new java.util.NoSuchElementException();
		}
	}

	// to check if deque is empty
	public boolean isEmpty() {
		return size == 0;
	}

	// number of items in the deque
	public int size() {
		return size;
	}

	// to add the front of deque
	public void addFirst(Item item) {
		checkInputItem(item);
		if (first == null && last == null) {
			first = new Node();
			first.item = item;
			first.next = null;
			first.previous = null;
			last = first;
			size++;
		}
		else {
			Node previousFirst = first;
			first = new Node();
			first.item = item;
			first.next = previousFirst;
			if (previousFirst == last) {
				last.previous = first;
			}
			size++;
		}
	}

	// to add to the end
	public void addLast(Item item) {
		if (first == null && last == null) {
			first = new Node();
			first.item = item;
			first.next = null;
			first.previous = null;
			last = first;
			size++;
		}
		else {
			checkInputItem(item);
			Node previousLast = last;
			last = new Node();
			last.item = item;
			last.previous = previousLast;	
			if (previousLast == first) {
				first.next = last;
			}
			size++;
		}	
	}

	// to remove from front
	public Item removeFirst() {
		checkRemove();
		Item item = first.item;
		first = first.next;
		size--;
		return item;
	}

	// to remove from end
	public Item removeLast() {
		checkRemove();
		Item item = last.item;
		last = last.previous;
		size--;
		return item;
	}

	public Iterator<Item> iterator() {
		return new IteratorClass();
	}

	public static void main(String[] args) {
		Deque<String> deq = new Deque<String>();
		int option = 0;
		while (option == 0) {
			deq.displayOptions();
			switch(StdIn.readLine()) {
			case "1" : StdOut.println("Enter item to be added");
			           deq.addFirst(StdIn.readLine()); 
			           break;
			case "2" : StdOut.println("Enter item to be added");
			           deq.addLast(StdIn.readLine()); 
			           break;
			case "3" : StdOut.println("Size of the deque " + deq.size());	
			           break;
			case "4" : Iterator<String> itr = deq.iterator();
			           StdOut.println("Deque Elements:\n");
			           while (itr.hasNext()) {
				          StdOut.print(itr.next()+ " ");
			           }
			           break;
			case "5" : StdOut.println("Item removed "+ deq.removeFirst());
			           break;
			case "6" : StdOut.println(deq.removeLast());
	                   break; 
			case "7" : option = 1;
                       break;
			default  : StdOut.println("Wrong option");
                       break;           
			}
		}
	}
}
