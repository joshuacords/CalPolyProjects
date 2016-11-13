import java.util.concurrent.ThreadLocalRandom;

public class GenerateRandomNQueens {
	public GenerateRandomNQueens(){
	}

	public int[] randomState(int n){
		int[] state = new int[n];

		for(int i = 0; i < n; i++){
			state[i] = ThreadLocalRandom.current().nextInt(0, n);
		}
		return state;
	}

}
