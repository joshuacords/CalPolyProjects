
public class MisplacedTiles implements Heuristic{

	@Override
	public int cost(MyState myState) {
		int[] stateArray = (int[]) myState.getBaseModel();
		int cost = 0;

		for(int i = 0; i < stateArray.length; i++){
			if(stateArray[i] != 0 && stateArray[i] != i){
				cost++;
			}
		}
		return cost;
	}

}
