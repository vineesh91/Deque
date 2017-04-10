import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] array;
	private int size;
	
	public RandomizedQueue(){
		array = (Item[]) new Object[1];
		size = 0;
	}	
	
	private class IteratorClass implements Iterator<Item>{

		private int i = size;
		public boolean hasNext() {
			return i > 0;
		}

		public Item next() {
			return array[--i];
		}
		
		public void remove() {
			throw new java.util.NoSuchElementException();
		}
		
	}
	
	// for test UI
		private void displayOptions() {
			StdOut.println(" \n*************************** \nEnter your option (1-6)");
			StdOut.println(" 1. Add to the queue \n 2. Display Size \n 3. Display Queue");
			StdOut.println(" 4. Remove from queue \n 5. Sample data \n 6. Exit \n");
			StdOut.println("*****************************************************");
		}
	
	private void resize (int newDimension) {
		Item[] arrayCopy = (Item[]) new Object[newDimension];
		for (int i = 0; i < size; i++){
			arrayCopy[i] = array[i];
		}
		array = arrayCopy;
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
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	public void enqueue(Item item){
		checkInputItem(item);
		if (size == array.length) {
			resize (2 * size);
		}
		array[size++] = item;
	}
	
	public Item dequeue() {
		checkRemove();
		int N = StdRandom.uniform(size - 1);
		Item item = array[N];
		while (N < size - 1) {
			array[N] = array[N+1];
			N++;
		}
		size--;		
		return item;
	}
	
	public Item sample() {
		int N = StdRandom.uniform(size - 1);
		return array[N];
	}
	
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new IteratorClass();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomizedQueue<String> req = new RandomizedQueue<String>();
		int option = 0;
		while (option == 0) {
			req.displayOptions();
			switch(StdIn.readLine()) {
			case "1" : StdOut.println("Enter item to be added");
			           req.enqueue(StdIn.readLine()); 
			           break;
			case "2" : StdOut.println("Size of the queue " + req.size());	
			           break;
			case "3" : Iterator<String> itr = req.iterator();
			           StdOut.println("Randomized queue Elements:\n");
			           while (itr.hasNext()) {
				          StdOut.print(itr.next()+ " ");
			           }
			           break;
			case "4" : StdOut.println("Item removed "+ req.dequeue());
			           break;
			case "5" : StdOut.println("Sample data "+req.sample());
	                   break; 
			case "6" : option = 1;
                       break;
			default  : StdOut.println("Wrong option");
                       break;           
			}
		}
	}

	

}
