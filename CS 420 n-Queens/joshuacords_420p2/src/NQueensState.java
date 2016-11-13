import java.util.Arrays;

public class NQueensState {

	public NQueensState(int n){
		_nSize = n;
		_state = new int[n];
		generateAttacksArray();
		for(int i = 0; i < n; i++){
			_state[i] = 0;
		}
	}

	public NQueensState(int[] startState){
		_nSize = startState.length;
		_state = new int[startState.length];
		generateAttacksArray();
		if(startState.length <= _nSize){
			for(int i = 0; i < _nSize; i++){
				_state[i] = startState[i];
			}
		} else {
			for(int i = 0; i < _nSize; i++){
				_state[i] = 0;
			}
		}
	}

	public NQueensState getBestNeighboor(){

		int[] bestState = Arrays.copyOf(_state, _nSize);
		int[] tempState;
		//go through each column
		for(int j = 0; j < _nSize; j++){

			//go through each row in column
			for(int i = 0; i < _nSize; i++){
				tempState = Arrays.copyOf(_state, _nSize);
				tempState[i] = j;
				if(attackingQueens(tempState) < attackingQueens(bestState)){
					bestState = tempState;
				}
			}
		}
		return new NQueensState(bestState);
	}

	public NQueensState getBestPositionForColumn(int column){

		int[] bestState = Arrays.copyOf(_state, _nSize);
		int[] tempState;

		//go through each row in column
		for(int i = 0; i < _nSize; i++){
			tempState = Arrays.copyOf(_state, _nSize);
			tempState[column] = i;
			if(attackingQueens(tempState) < attackingQueens(bestState)){
				bestState = tempState;
			}
		}
		return new NQueensState(bestState);
	}

	public int getNSize(){
		return _nSize;
	}

	public int[] getState(){
		return _state;
	}

	public int attackingQueens(){
		return attackingQueens(_state);
	}

	public void printArray(int[] array){
		System.out.println("Printing array:");
		for(int i = 0; i < array.length; i++){
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("State\n");

		for(int j = 0; j < _nSize; j++){

			for(int i = 0; i < _nSize; i++){
				if(_state[i] == j){
					sb.append("X ");
				} else {
					sb.append("- ");
				}
			}
			sb.append("\n");
		}

		return sb.toString();
	}

	private int attackingQueens(int[] currentState){
		int attacks = 0;
		attacks += rowAttacks(currentState);
		attacks += diagonalAttacks(currentState);

		return attacks;
	}



	private int diagonalAttacks(int[] currentState) {
		int attacks = 0;

		//for each diagonal down
		//for each row
		int start = -_nSize + 2;
		int end = _nSize - 2;
		int attackers = 0;

		for(int j = start; j < end ; j++){

			attackers = 0;
			for(int i = 0; i < _nSize; i++){

				//break if diagonal is off board
				if((i+j) > _nSize){
					break;
				}

				if(currentState[i] == (i + j)){
					attackers++;
				}
			}
			//determine number of attacks based on attackers
			attacks += _attacks[attackers];
		}

		//for each diagonal up
		start = 1;
		end = _nSize*2 - 2;
		for(int j = start; j < end ; j++){

			attackers = 0;
			for(int i = 0; i < _nSize; i++){

				//break if diagonal is off board
				if((j-i) < 0){
					break;
				}

				if(currentState[i] == (j - i)){
					attackers++;
				}
			}
			//determine number of attacks based on attackers
			attacks += _attacks[attackers];
		}

		return attacks;
	}

	private void generateAttacksArray(){
		_attacks = new int[_nSize + 1];

		_attacks[0] = 0;

		for(int i = 1; i < _nSize + 1; i++){
			_attacks[i] = _attacks[i-1] + (i-1);
		}
	}

	private int rowAttacks(int[] currentState){
		int attacks = 0;

		//for each row
		for(int j = 0; j < _nSize; j++){

			//for each column
			int attackers = 0;
			for(int i = 0; i < _nSize; i++){
				if(currentState[i] == j){
					attackers++;
				}
			}

			//determine number of attacks based on attackers
			attacks += _attacks[attackers];
		}
		return attacks;
	}

	private int _nSize;
	private int [] _state;
	private int [] _attacks;
}
