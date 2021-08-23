import java.util.Deque;
import java.util.LinkedList;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
	
	private SearchNode node;
	
	private final static class SearchNode implements Comparable<SearchNode> {

		public final Board board;
		public final SearchNode previous;
		public final int moves;
		public final int priority;
		
		// Constructor
		public SearchNode(Board board, SearchNode previous) {
			
			this.board = board;
			this.previous = previous;
			
			if( previous != null) {
				this.moves  = previous.moves + 1;
			}
			else {
				this.moves = 0;
			}
			
			this.priority = this.board.manhattan()  + this.moves;
			
		}
		
		@Override
		public int compareTo(SearchNode that) {
			
			return Integer.compare(this.priority, that.priority);
		}
		
	}

	
	// Constructor
    // find a solution to the initial board (using the A* algorithm)
	// Once this function is run, the solved board would be set to the node variable of this solver class instance
    public Solver(Board initial) {
    	
    	if(initial == null) {
    		throw new IllegalArgumentException("Null passed as parameter");
    	}
    	
    	// We create two minimum oriented priority queues 
    	// ( the node with minimum priority is at the top of the binary heap 
    	//   and the priority values of the nodes increases as we descend down the heap)
    	MinPQ<SearchNode> pqOriginal = new MinPQ<SearchNode>();
    	MinPQ<SearchNode> pqTwin = new MinPQ<SearchNode>();
    	
    	// To stick to the proof that when we use a board with a swapped value as that of the 
    	// initial board, one of the boards will be unsolvable
    	pqOriginal.insert(new SearchNode(initial, null));
    	pqTwin.insert(new SearchNode(initial.twin(), null));
    	
    	while(true) {
    		
    		// We start of by popping out the node with the minimum priority
    		SearchNode originalNode = pqOriginal.delMin();
    		SearchNode twinNode = pqTwin.delMin();
    		
    		// If the original board is the goal board it means it is solvable
    		if( originalNode.board.isGoal()) {
    			this.node = originalNode;
    		} 
    		// Else let's see if the twin node is solvable, 
    		// this would mean that the original board is unsolvable
    		else if (twinNode.board.isGoal()) {
    			this.node = null;
    			return;
    		}
    		
    		// We still don't know if the board is solvable or not
    		
    		// We follow the steps mentioned in the A* algorithm
    		
    		// We insert the various possible neighbors of the board 
    		
    		for ( Board neighbor : originalNode.board.neighbors()) {
    			
    			// Optimization step
    			// If the originalNode has a previous node
    			// and if the neighbor is equal to the previous node, 
    			// then we do not add it
    			if(originalNode.previous != null 
    					&& originalNode.previous.board.equals(neighbor)) {
    				continue;
    			}
    			pqOriginal.insert(new SearchNode(neighbor, originalNode));
    			
    		}
    		
    		// Similarly for the twinNode
    		for(Board neighbor: twinNode.board.neighbors()) {
    			if(twinNode.previous != null && twinNode.previous.board.equals(neighbor)) {
    				continue;
    			}
    			pqTwin.insert(new SearchNode(neighbor, twinNode));
    		}
    	}

    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
    	
    	// If the dimension is odd then the puzzle instance is solvable 
    	//   if the number of inversions is even in the input state
    	
    	// If dimension is even, the board is solvable if
    	//   Blank is on an even row counting from bottom & odd number of inversions
    	//   Blank is on n odd row from bottom and number of inversion are event
    	
    	// Else unsolvable
    	
    	// Since we don't have access to the board array, we use a different approach to find if the board is solvable or not 
    	
    	// We use the proof that when the A* algorithm is run on a board and it's twin, only one of the board will be solvable.
    	// If the twin board ends up being solvable the given board will be unsolvable
    	
    	
    	// If this node has not been set to null it means this board is still solvable
    	// It has not yet been identified that it will be unsolvable
    	
    	return this.node != null;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
    	if(!this.isSolvable()) {
    		return -1;
    	}
    	return node.moves;
    }

    
    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution(){
    	if(!this.isSolvable()) {
    		return null;
    	}
    	
    	Deque<Board> solution = new LinkedList<Board>();
    	
    	SearchNode solutionNode = node;
    	
    	//by the time we call this function, the constructor 'solver' would have run
    	// the A* algorithm and would have set the goalBoard as the node
    	// So by accessing the previous element of the node we can traverse to get the list 
    	// of boards from the start
    	
    	// Let's check if we can add the steps in the right order while we trace back
    	while(solutionNode != null) {
    		solution.addFirst(solutionNode.board);
    		solutionNode = solutionNode.previous;
    	}
    	
    	// Here either we return a null value or an iterable solution object
    	// Which has the list of board states from the start to the solved state
		return solution;
    	
    }

    // test client (see below) 
    public static void main(String[] args) {
    	
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board.toString());
        }
    	
    }
}
