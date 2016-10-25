
public class Average {
	int[] _nodes = new int[30];
	int[] _count = new int[30];
	long[] _time = new long[30];

	public void put(int depth, int nodes, long time){
		_nodes[depth] += nodes;
		_count[depth]++;
		_time[depth] += time;
	}

	public int getAverageNodes(int depth){
		if(_count[depth] < 1){
			return -1;
		}
		return _nodes[depth]/_count[depth];
	}

	public long getAverageTime(int depth){
		if(_count[depth] < 1){
			return -1;
		}
		return _time[depth]/_count[depth];
	}
}
