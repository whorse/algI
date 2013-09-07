/**
Dependencies WeightedQuickUnionUF.java
**/

public class Percolation {
	private int gridsize;
	private boolean[] a;
	private WeightedQuickUnionUF grid;
	
	public Percolation(int N){ // create N-by-N grid, with all sites blocked
		gridsize=N;
		grid = new WeightedQuickUnionUF(N*N);
		a = new boolean[N*N];
		for (int i=0; i<N*N; i++)
			a[i]=false;
	}
	public void open(int i, int j){
		int index = (i-1)*gridsize+j-1;
		int tmp;
		if (!isOpen(i,j)){
			a[index]=true;
			// check left
			if (j-1 >0)
				if (isOpen(i, j-1)){
					tmp=(i-1)*gridsize+j-2;
					grid.union(index, tmp);
				}
			// check right
			if (j+1 <= gridsize)
				if (isOpen(i, j+1)){
					tmp=(i-1)*gridsize+j;
					grid.union(index, tmp);
				}
			// check up
			if (i-1 >0)
				if (isOpen(i-1,j)){
					tmp=(i-2)*gridsize+j-1;
					grid.union(index, tmp);
				}
			// check down
			if (i+1 <= gridsize)
				if (isOpen(i+1,j)){
					tmp=i*gridsize+j-1;
					grid.union(index, tmp);
				}				
			int root = grid.find(index);
		//	System.out.println("just opened ("+ i + ", " + j + ") root is: " + root);
		}
	}	// open site (row i, column j) if it is not already
	public boolean isOpen(int i, int j){
		int index = (i-1)*gridsize+j-1;
		//System.out.println("neighbor ("+ i + ", " + j + ") is" + a[index] );
		return a[index];
	}// is site (row i, column j) open?
	public boolean isFull(int i, int j){
		int index = (i-1)*gridsize+j-1;
		for (int k=0; k<gridsize; k++){
			if (grid.connected(index,k))
				return true;
		}
		return false;
	}    // is site (row i, column j) full?
	public boolean percolates(){
		for (int k=1; k<=gridsize; k++){
			if (isFull(gridsize,k)) // k+1 becuase we're starting from 1 not 0
				return true;
		}
		return false;
	}            // does the system percolate?**/
	
}
