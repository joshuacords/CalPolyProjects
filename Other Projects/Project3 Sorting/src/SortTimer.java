 

public class SortTimer {
	
	private long time, comparisons, moves;
	public SortTimer()
	{
		moves = comparisons = 0;
		time = System.nanoTime();
	}
	
	public void reset()
	{
		moves = comparisons = 0;
		time = System.nanoTime();
	}
	
	public void addComparison()
	{
		comparisons++;
	}
	
	public void addComparisons(int n)
	{
		comparisons += n;
	}
	
	public void addMove()
	{
		moves++;
	}
	
	public void addMoves(int n)
	{
		moves += n;
	}
	
	public long getElapsedTime()
	{
		return System.nanoTime() -  time;
	}
	
	public long getComparisons()
	{
		return comparisons;
	}
	
	public long getMoves()
	{
		return moves;
	}
}
