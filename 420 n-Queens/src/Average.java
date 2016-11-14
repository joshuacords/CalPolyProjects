
public class Average {
	private int[] _queens = new int[50];
	private long _totalQueens = 0;
	private long _time = 0;

	public void put(int attacks, long time){
		_queens[attacks]++;
		_totalQueens++;
		_time += time;
	}

	public int getNumAttackingQueens(int attacks){
		return _queens[attacks];
	}

	public double getPercentAttackingQueens(int attacks){
		return (double)_queens[attacks]/(double)_totalQueens;
	}

	public long getAverageTime(){
		if(_totalQueens < 1){
			return -1;
		}
		return _time/_totalQueens;
	}
}
