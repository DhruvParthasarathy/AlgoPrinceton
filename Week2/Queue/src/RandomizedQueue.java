import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;


public class RandomizedQueue<Item> implements Iterable<Item> {

	private int numItems=0;
	private Item[] randomQueue;
	private int currentIndex;
	
	
    // construct an empty randomized queue
    public RandomizedQueue() {
    	randomQueue = (Item[]) new Object[1];
    	currentIndex=0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
    	return numItems == 0 ;
    }

    // return the number of items on the randomized queue
    public int size() {
    	return numItems;
    }

    // add the item
    public void enqueue(Item item) {
    	
    	if(item == null) {
    		throw new IllegalArgumentException();
    	}
    	// If current element is null add item to current element
    	if(numItems==0) {
    		randomQueue[currentIndex] = item;
    		numItems+=1;
    	}
    	else {
        	// Else if not check if next element is present if not expand the array
    		if(currentIndex+1 == randomQueue.length) {
    			//Expand the array
    			expand();
    		}
    		
    		// Add item to next spot
    		addElemToNext(item);
    	}

		
    }
    
    private void expand() {
    	// Create a new array of double the size of the original array
    	Item[] tempArray = (Item[]) new Object[randomQueue.length*2];
    	int dropIndex = 0;
    	
    	// Fill in the items of the old array in this new array
    	for(int i = 0; i < randomQueue.length; i++) {
    		
    		// Ensure that the null elements are not carried over
    		if(randomQueue[i]!=null) {
    			tempArray[dropIndex] = randomQueue[i];
    			dropIndex+=1;
    		}
    	}
    	
    	// Assign this temp array to the original array
    	currentIndex = dropIndex-1;
    	randomQueue = tempArray;
    	
    }
    
    private void addElemToNext(Item item) {
		randomQueue[currentIndex+1] = item;
		currentIndex+=1;
		numItems+=1; 
    }

    // remove and return a random item
    public Item dequeue() {
    	if(numItems==0) {
    		throw new NoSuchElementException();
    	}
    	Item randItem = null;
    	int randNum = 0;
    	
    	while(randItem==null) {
    		randNum = StdRandom.uniform(0, randomQueue.length);
    		if(randomQueue[randNum] != null) {
    			randItem =  randomQueue[randNum];
    		}
    	}
    	randomQueue[randNum] = null;
    	numItems-=1;
    	
    	if(numItems < (randomQueue.length)/2 ) {
        	collapseArray();
    	}

  
    	return randItem;
    	
    }

    
    private void collapseArray() {
    	// Create a new array of half the size of the original array
    	Item[] tempArray = (Item[]) new Object[randomQueue.length/2];
    	int dropIndex = 0;
    	
    	// Fill in the items of the old array in this new array
    	for(int i = 0; i < randomQueue.length; i++) {
    		
    		// Ensure that the null elements are not carried over
    		if(randomQueue[i]!=null) {
    			tempArray[dropIndex] = randomQueue[i];
    			dropIndex+=1;
    		}
    	}
    	
    	// Assign this temp array to the original array
    	if(dropIndex==0) {
    		currentIndex = dropIndex;
    	} else   	currentIndex = dropIndex-1;
    	randomQueue = tempArray;
    	
    }
        
    // return a random item (but do not remove it)
    public Item sample() {
    	// Pick a random index from array - Write a function to return a random integer based on array size 
    	// Check if value at that index is not null
    		// return that value
    	if(numItems==0) {
    		throw new NoSuchElementException();
    	}
    	Item sampleItem = null;
    	int randNum = 0;
    	while(sampleItem==null) {
    		randNum = StdRandom.uniform(0, randomQueue.length);
    		if(randomQueue[randNum] != null) {
    			sampleItem =  randomQueue[randNum];
    		}
    	}
    	return sampleItem;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
    	return new RandIterator();
    }

    private class RandIterator implements Iterator<Item> {

     	private Item[] shuffledArray = randomQueue;
    	private int iteratorIndex = 0;
    	private int discovered = 0;
    	
    	private RandIterator() {
    		StdRandom.shuffle(shuffledArray);
    	}
    	
    	
		@Override
		public boolean hasNext() {
			if((numItems!=0) & (discovered < numItems)) {
				return true;
			}
			return false;
		}

		@Override
		public Item next() {
			
			if(!hasNext()) {
				throw new NoSuchElementException();
			} else {
				Item currentItem = null;
				
				while(currentItem == null) {
					currentItem = shuffledArray[iteratorIndex++];
					
				}
				discovered+=1;
				return currentItem;
			}

		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
    	
    }
    // unit testing (required)
    public static void main(String[] args) {
    	RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
    	
		System.out.println("\nTesting if isEmpty functionality works");
		if(rq.isEmpty()) {
			System.out.println("\nYes the queue is empty now\n");
		} else System.out.println("\nIs empty is throwing false even when the queue is empty\n");
		
		System.out.println("\nTesting if size functionality works");
		if(rq.size() == 0) {
			System.out.println("\nYes the queue is empty now\n");
		} else System.out.println("\nIs size is giving a value even when the queue is empty\n");
		
    	rq.enqueue(1);
    	rq.enqueue(2);
		System.out.println("\nRemoving an elem " + rq.dequeue());
		System.out.println("\nRemoving an elem " + rq.dequeue());    	
    	rq.enqueue(3);
    	rq.enqueue(4);
    	rq.enqueue(5);
    	rq.enqueue(6);
    	rq.enqueue(7);
    	rq.enqueue(8);
    	rq.enqueue(9);
    	
    	
    	Iterator<Integer> rit = rq.iterator();
    	System.out.println("\n");
    	while(rit.hasNext()) {
    		System.out.println(rit.next());
    	}
    	
		System.out.println("\nTesting if size functionality works");
		if(rq.size() == 7) {
			System.out.println("\nYes the queue size is equal to 7 now\n");
		} else System.out.println("\nIs size is giving a wrong value even when the queue size is 7\n");   
		
		System.out.println("\n");
		System.out.println("\nRemoving an elem " + rq.dequeue());
		System.out.println("\nRemoving an elem " + rq.dequeue());
		System.out.println("\nPrinting after removal\n");
		
    	Iterator<Integer> rit2 = rq.iterator();
    	System.out.println("\n");
    	while(rit2.hasNext()) {
    		System.out.println(rit2.next());
    	}
    	
		System.out.println("\nTesting if size functionality works");
		if(rq.size() == 5) {
			System.out.println("\nYes the queue size is equal to 5 now\n");
		} else System.out.println("\nIs size is giving a wrong value even when the queue size is 5\n");      	
    	
		
        RandomizedQueue<Integer> rq1 = new RandomizedQueue<Integer>();
        rq1.enqueue(2);
        rq1.enqueue(4);
        rq1.enqueue(2);
        rq1.sample();      	
    }

}