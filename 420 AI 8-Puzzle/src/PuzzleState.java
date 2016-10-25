import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PuzzleState implements MyState{

	final int[] _goalState = {0,1,2,3,4,5,6,7,8};
	int[] _stateArray = new int[9];

	public PuzzleState(int[] stateArray){
		if(stateArray.length == 9){
			_stateArray = Arrays.copyOf(stateArray, 9);
		}
	}

	@Override
	public boolean isGoal() {
		for(int i = 0; i < _stateArray.length; i++){
			if(_stateArray[i] != _goalState[i]){
				return false;
			}
		}
		return true;
	}

	@Override
	public String getHash() {
		StringBuilder sb = new StringBuilder();
		for(int i : _stateArray){
			sb.append(i);
		}
		return sb.toString();
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < _stateArray.length; i++){
			sb.append(_stateArray[i] + " ");
			if((i+1)%3 == 0){
				sb.append("\n");
			}
		}
		return sb.toString();
	}

	@Override
	public Object getBaseModel() {
		return Arrays.copyOf(_stateArray, 9);
	}

	@Override
	public List<MyState> generateSuccessors(){

		List<MyState> successors = new LinkedList<MyState>();
		for(int i = 0; i < _stateArray.length; i++){

			//find 0
			if(_stateArray[i] == 0){

				//generate horizontal states
				if(i%3 != 0){
					successors.add(new PuzzleState(createSwap(_stateArray,i,i-1)));
				}
				if(i%3 != 2) {
					successors.add(new PuzzleState(createSwap(_stateArray,i,i+1)));
				}

				//generate vertical states
				if(i/3 != 0){
					successors.add(new PuzzleState(createSwap(_stateArray,i,i-3)));
				}
				if(i/3 != 2) {
					successors.add(new PuzzleState(createSwap(_stateArray,i,i+3)));
				}

				break;
			}
		}

		return successors;
	}

	private int[] createSwap(int[] array, int index1, int index2){
		int[] newArray = Arrays.copyOf(array, array.length);
		int temp = newArray[index1];
		newArray[index1] = newArray[index2];
		newArray[index2] = temp;

		return newArray;
	}

}
