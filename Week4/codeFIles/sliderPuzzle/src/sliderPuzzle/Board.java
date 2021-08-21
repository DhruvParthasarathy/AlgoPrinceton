package sliderPuzzle;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.StdIn;

public class Board {
	
	private int dimension = 0; 
	
	private int[][] board;
	
//	private int[][] goalSet;

	/**
	 * Create a board from an n-by-n array of tiles, 
	 * where tiles[row][col] = tile at (row,col)
	 * @param tiles
	 */
	public Board(int[][] tiles) {
		
		dimension = tiles.length;
		
		// creating the private variable board
		board = new int[dimension][dimension];
		
		for(int i = 0; i < dimension; i++) {
			for( int j = 0; j < dimension; j++) {
				board[i][j] = tiles[i][j];
			}
		}
		
//		goalSet = this.getGoalSet();
	}

	/**
	 * Returns the string representation of this board
	 */
    public String toString() {
    	
    	String output = ""; 
    	output += dimension;
    	output += "\n";
    	
    	for(int i = 0 ; i < dimension; i ++) {
    		output += " ";
    		for ( int j = 0; j < dimension; j ++) {
    			output += board[i][j]+ "  ";
    		}
    		output += "\n";
    	}
		return output;
    	
    }
    

    /**
     * Returns board dimension n (square board)
     * @return
     */
    public int dimension() {
    	
		return dimension;
    	
    }
    
    /**
     * Returns the number of tiles out of place, we ignore the empty slot while calculating this
     * @return
     */
    public int hamming() {  	
    	
    	int[][] target = board;
    	
    	int hammingDistance = 0;
    	
    	// we iterate through the cells in each board and 
    	// increment the hammingDistance value for each incorrect position
    	for( int i = 0; i < dimension ; i ++) {
    		for ( int j = 0; j < dimension; j++) {
    			
    			if( target[i][j] != 0 &&  target[i][j] != ( dimension*i + j + 1) ) 
    			{
    				hammingDistance += 1;
    			}
    		}
    	}
    	
		return hammingDistance;
    	
    }
    
    
    /**
     * Sum of Manhattan distances between the tiles and the goal board
     * For each of the tiles in the board we find the distance of that from it's goal position
     * We ignore the empty slot while doing this calculation
     * The goal board is the solved board
     * @return
     */
    public int manhattan() {
    	
    	// we have a board and each cell of that board has an i,j index
    	// we also have the goal board
    	
    	// we can compare the i,j indices of both the given board and the goalBoard and 
    	// find the sum of i,j indices
    	
    	// One question arises here is do we find the manhattan distance of all the 
    	
    	int manhattanDistance = 0;
    	
    	for ( int i = 0; i < dimension; i++) {
    		for ( int j = 0; j < dimension; j++) {
    		 
    			if(board[i][j] != 0) 
    			{
    				manhattanDistance += this.getManhattan(board[i][j],i,j);
    			}
    		}
    	}
    	
    	
    	return manhattanDistance;
    }
    
    /**
     * Is this board the goal board ?
     * @return
     */
    public boolean isGoal() {
		return false;
    	
    }
	
    
    /**
     * Does this board equal y ?
     * 
     */
    public boolean equals(Object y) {
    	
    	// Fist check if the same object is being compared
    	if(this == y) return true;
    	
    	Board target = (Board) y;

    	// Check if the dimensions are the same
    	if(dimension != target.dimension()) return false ;
    	
    	for ( int i = 0 ; i < dimension; i ++) {
    		for ( int j = 0 ; j < dimension; j ++) {
    			
    			if(board[i][j] != target.board[i][j]) {
    				return false;
    			}
    		}
    	}
		
    	return true;
    	
    }
    
    /**
     * All neighboring boards
     * @return
     */
    public Iterable<Board> neighbours(){
    	
    	// We need to return an iterable object - we are going to use a list for the same
    	List<Board> neighbours = new ArrayList<Board>();
    	
    	// We already have the board as a class object, so we need to find what all could be the
    	// various neighbours of the board based on where the empty slot is
    	
    	int[] emptyPosition = this.getEmptySlot();
    	
    	int ei = emptyPosition[0]; // i index of empty slot
    	int ej = emptyPosition[1]; // j index of empty slot
    	
    	// Now that we have the empty slot of the board let's see what could be the neighbours of the board
    	// To become a neighbour the empty slot can be moved one position to the left, right , top or bottom 
    	
    	// One way to check for empty slots  ====================
    	
    	// The empty slot can be in the following positions
    	
    	// Empty slot inside the board ( can have 4 neighbors )
    	
    	// Empty slot along the edges of the board ( can have 3 neighbors )
    	
    	// Empty slot in one of the corners of the board ( can only have 2 neighbors )
    	
    	
    	// Another way to check for empty slots  =======================

		
    	int[][] neighbourLayout = this.cloneBoardArray();
    	
    	// Is there one cell above the given position ?
    	
    	if ( ei - 1 >= 0  ) {
    		
    		// The neighbor will be the board where the slot has been 
    		// exchanged with the cell above
    		// cell to exchange is (ei -1 , ej)
    		
    		
    		// We swap the cell above the slot with the empty slot
    		
    		// make the empty slot the value of the above slot 
    		neighbourLayout[ei][ej] = neighbourLayout[ei-1][ej];
    		
    		// make the above slot empty
    		neighbourLayout[ei-1][ej] = 0;
    		
    		// Create a new board with this layout and add it to the list of neighbors
    		neighbours.add( new Board(neighbourLayout));
    		
    		// resetting the array
    		neighbourLayout[ei][ej] = 0;
    		neighbourLayout[ei-1][ej] = board[ei-1][ej];
    		
    	}
    	
    	// Is there one cell below the given position ?
    	
    	if( ei + 1 < dimension) {
    		
//    		int[][] neighbourLayout = board.clone();
    		neighbourLayout[ei][ej] = neighbourLayout[ei +1][ej];
    		neighbourLayout[ei +1][ej] = 0;
    		neighbours.add( new Board(neighbourLayout));
    		neighbourLayout[ei][ej] = 0;
    		neighbourLayout[ei +1][ej] = board[ei +1][ej];
    		
    		
    	}
    	
    	// Is there one cell to the left of the given positions ? 
    	if( ej - 1 <= 0) {
    		
//    		int[][] neighbourLayout = board.clone();
    		neighbourLayout[ei][ej] = neighbourLayout[ei][ej -1];
    		neighbourLayout[ei][ej -1] = 0;
    		neighbours.add( new Board(neighbourLayout));
    		neighbourLayout[ei][ej] = 0;
    		neighbourLayout[ei][ej -1] = board[ei][ej -1];
    	}
    	
    	
    	// Is there one cell to the right of the given position ?
    	if( ej + 1 <= dimension -1 ) {
    		
//    		int[][] neighbourLayout = board.clone();
    		neighbourLayout[ei][ej] = neighbourLayout[ei][ej + 1];
    		neighbourLayout[ei][ej + 1] = 0;
    		neighbours.add( new Board(neighbourLayout));
    		neighbourLayout[ei][ej] = 0;
    		neighbourLayout[ei][ej + 1] = board[ei][ej + 1];
    	}
    	
		return neighbours;
    	
    }
    
    /**
     * A board that is obtained by exchanging any pair of tiles
     * @return
     */
    public Board twin() {
		return null;
    	
    }
    

	
//	private int[][] getGoalSet() {
//		
//    	// We need information about the goal set
//    	int[][] goalSet = new int[dimension][dimension];
//    	
//    	// Populating the values for the goal set
//    	for(int i = 0 ; i < dimension; i ++) {
//    		for (int j = 0; j < dimension ; j ++) {
//    			
//    			if(i == dimension-1 && j == dimension-1) {
//    				goalSet[i][j] = 0;
//    			}
//    			else {
//    				goalSet[i][j] = dimension*i + j + 1;
//    			}
//    		}
//    	}
//    	
//    	return goalSet;
//	}

	/**
	 * We have the value and the x and y coordinates of that point. 
	 *   We know that at each x and y coordinate the value must be dimension*x + y + 1
	 *   Value = dimensions*x + y + 1 or `value - 1 = dim*x + y`  --- this seems to follow the formula dividend = divisor * quotient + remainder .
	 *   So to get the value of the divisor ( x ) , we can do value / dimension.
	 *   To get the value of y we can do (value -1 ) % n
	 * 
	 * @param value
	 * @param x
	 * @param y
	 * @return
	 */
	private int getManhattan(int value, int i, int j) {

		
		
		// the actual row of the element 
		
		int x = (value - 1) / dimension;
		
		int y = (value - 1) % dimension ;
		
		// to find the Manhattan distance , we can take the absolute of the distances between these coordinates
		
	
		return Math.abs(i - x) + Math.abs(j - y);
	}
	
	/**
	 * This method returns the indices of the empty slot in the board
	 * @return
	 */
	private int[] getEmptySlot() {
		
		int[] slots = new int[2];
		
		for(int i = 0 ; i < dimension; i ++) {
			for ( int j = 0 ; j < dimension; j ++) {
				if( board[i][j] == 0 ){
					slots[0] = i;
					slots[1] = j;
				}
			}
		}
			
		
		return slots;
		
	}
	
	/**
	 * We use this method to return a cloned board object where the reference to the old board is not present
	 * @return
	 */
	private Board cloneBoard() {
		return null;
	}
	
	/**
	 * We use this method to return a cloned board's data where the reference to the old board is not present
	 * @return
	 */
	private int[][] cloneBoardArray() {
		
		int[][] clonedData = new int[dimension][dimension];
		
		for (int i = 0 ; i < dimension; i ++) {
			for ( int j = 0 ; j < dimension; j ++) {
				clonedData[i][j] = board[i][j];
			}
		}
		return clonedData;
	}
	
    /**
     * Unit testing the various methods of the board
     * @param args
     */
	public static void main(String[] args) {

		System.out.println("Enter the board numbers");
		int n = StdIn.readInt();
		int[][] input = new int[n][n];
		
		for(int i = 0; i < n; i ++) {
			for (int j = 0; j < n; j++) {
				input[i][j] = StdIn.readInt();
			}
		}
		
		Board board = new Board(input);
		
		System.out.println(board.toString());
		
//		System.out.println("Hamming distance of the board: " + board.hamming());
//		
//		System.out.println("Manhattan distance of the board: " + board.manhattan());
		
		
		
		
		// Testing equals functionality ============================
		
//		int[][] boardContent1 = new int[4][4]; // board with non goal structure
//		int[][] boardContent2 = new int[4][4]; // board with goal structure
//		int[][] boardContent3 = new int[4][4]; // board with not goal structure
//		
//		
//		for(int i = 0; i < 4; i ++) {
//			for (int j = 0; j < 4; j ++) {
//				
//				boardContent1[j][i] = 4*i + j + 1;
//				boardContent2[i][j] = 4*i + j + 1;
//				boardContent3[j][i] = 4*i + j + 1;
//			}
//		}
//		
//		int[][] boardContent4 = new int[5][5]; // goal board with different dimension
//		
//		for(int i = 0; i < 5; i ++) {
//			for (int j = 0; j < 5; j ++) {
//				boardContent4[i][j] = 5*i + j + 1;
//				
//			}
//		}
//		
//		Board board1 = new Board(boardContent1);
//		
//		Board board2 = new Board(boardContent2);
//		Board board3 = new Board(boardContent3);
//		
//		Board board4 = new Board(boardContent4);
//		
//		System.out.println("Expect false: Are boards 1 and 2 equal ?  " +  board1.equals(board2));
//		System.out.println("Expect true: Are boards 1 and 3 equal ?  " +  board1.equals(board3));
//		System.out.println("Expect false: Are boards 2 and 4 equal ?  " +  board2.equals(board4));
		

		// Testing neighbors ======================================
		
		List<Board> neighbors = (List<Board>) board.neighbours();
		
		for( Board neighbor : neighbors) {
			System.out.println("Hi neighbor !! \n");
			System.out.println(neighbor.toString());
		}
		
	}
	
}
