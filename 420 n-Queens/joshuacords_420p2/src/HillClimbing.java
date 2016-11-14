
public class HillClimbing implements LocalSearch{

	public HillClimbing(){}

	public HillClimbing(NQueensState state){
		_state = state;
	}

	@Override
	public NQueensState run(){

		boolean better = true;
		while(better){
			NQueensState nextState = _state.getBestNeighboor();
			better = nextState.attackingQueens() < _state.attackingQueens();
			if(better){
				_state = nextState;
			}
		}

		return _state;
	}

	@Override
	public void setState(NQueensState state){
		_state = state;
	}

	private NQueensState _state;
}