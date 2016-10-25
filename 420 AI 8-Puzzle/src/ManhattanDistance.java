
public class ManhattanDistance implements Heuristic {

	@Override
	public int cost(MyState myState) {
		int[] stateArray = (int[]) myState.getBaseModel();
		int cost = 0;

		for(int i = 0; i < stateArray.length; i++){

			if(stateArray[i] != 0){
				//horizontal cost
				cost += Math.abs((stateArray[i] % 3) - (i % 3));

				//vertical cost
				cost += Math.abs(stateArray[i] / 3 - i / 3);
			}
		}
		return cost;
	}

}
