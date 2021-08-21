import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
   public static void main(String[] args) {
	   
	   int numElem =  Integer.parseInt(args[0]);
	   
	   RandomizedQueue<String> rq = new RandomizedQueue<String>();
	   
	   while(StdIn.readString() != null) {
		   rq.enqueue(StdIn.readString());
	   }
	   
	   if(rq.size() == 0) {
		   throw new NoSuchElementException();
	   }
	   
	   Iterator<String> rqi = rq.iterator();
	   
	   int counter = 0;
	   while(rqi.hasNext()& (counter <= numElem) ) {
		   System.out.println(rqi.next());
	   }
   }
}