public class PercolationStats {
	
	private double[] observations;
	private double mean;
	private double stddev;
	private double lo_bound=-1.96;
	private double hi_bound=1.96;
	private Percolation[] grids;
	
	public PercolationStats(int N, int T){
		grids = new Percolation[T];
		observations = new double[T];
		
		int size=N*N;
		
		for (int i=0; i<T; i++){
			grids[i] = new Percolation(N);
			double counter=0.0;
			
			while (!( grids[i].percolates() )){
				int k = StdRandom.uniform(1,N+1);
				int l= StdRandom.uniform(1,N+1);
				if (!grids[i].isOpen(k,l)){
					grids[i].open(k, l);
					counter++;
			}
			observations[i] = counter/size;			
			}
			//System.out.println("grid[" + i + "] percolated on the " + counter + "th node");
			//System.out.println("expected value: " + observations[i]);
		}
		System.out.println("mean: " + mean());
		System.out.println("stddev: " + stddev());
		System.out.println("95% confidence interval: " + confidenceLo() + ", " + confidenceHi());			
	}    // perform T independent computational experiments on an N-by-N grid

	public double mean(){ 
		mean = StdStats.mean(observations);
		return mean;
	}// sample mean of percolation threshold

	public double stddev(){
		stddev = StdStats.stddev(observations);
		return stddev;
	}// sample standard deviation of percolation threshold

	public double confidenceLo(){
		return mean+stddev*lo_bound;
	}// returns lower bound of the 95% confidence interval

	public double confidenceHi(){
		return mean+stddev*hi_bound;	
	}// returns upper bound of the 95% confidence interval
	
	public static void main(String[] args){
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
//		int N = 5;
//		int T = 20;
//		if (!(N<=0 || T<= 0))
		double begining = System.currentTimeMillis();
		PercolationStats tests = new PercolationStats(N,T);
		double end = System.currentTimeMillis();
		double time_elapsed = (end-begining)/1000.0;
		System.out.println("Time elapsed: " + time_elapsed);
		
	}// test client, described below
}
