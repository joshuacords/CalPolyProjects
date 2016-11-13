import java.util.concurrent.ThreadLocalRandom;

public class MinConflicts implements LocalSearch {

	public MinConflicts() {}

	public MinConflicts(NQueensState state){
		_state = state;
		_nSize = state.getNSize();
	}

	@Override
	public NQueensState run(){
		return run(87);
	}

	public NQueensState run(int max){
		_max = max;
		boolean better = true;
		int randomColumn = 0;
		int maxMoves = 0;
		while(maxMoves < _max){
			randomColumn = ThreadLocalRandom.current().nextInt(0, _nSize);
			NQueensState nextState = _state.getBestPositionForColumn(randomColumn);
			better = nextState.attackingQueens() < _state.attackingQueens();
			_state = nextState;
			maxMoves++;
		}
		return _state;
	}

	@Override
	public void setState(NQueensState state){
		_state = state;
		_nSize = state.getNSize();
	}

	public int getMax(){
		return _max;
	}

	private NQueensState _state;
	private int _nSize;
	private int _max;
}