import java.util.List;

public class Driver {

	public static void main(String[] args) {

		AStar aStar = new AStar();
		PuzzleGenerator generator = new PuzzleGenerator();
		Average avg = new Average();
		MyTimer timer = new MyTimer();
		long nanoSeconds = 0;

		for(int[] baseState : generator.getPuzzle()){
			MyState initialState = new PuzzleState(baseState);

			timer.reset();;
			List<MyState> solutionPath = aStar.runAStar(initialState, generator.getHeuristic());
			nanoSeconds = timer.getElapsedTime();

			avg.put((solutionPath.size() - 1), aStar.getGeneratedNodes(), nanoSeconds);
//			System.out.println("Solution length: " + (solutionPath.size() - 1));
//			System.out.println(aStar.getGeneratedNodes() + " Nodes generated");
//			System.out.println(nanoSeconds + " Time in Nanoseconds");


			for(MyState state: solutionPath){
				//System.out.println(state);
			}
			aStar.resetGeneratedNodes();
		}

		System.out.println("Results for " + generator.getHeuristic().getClass());
		for(int i = 1 ; i < 30; i++){
			if(avg.getAverageNodes(i) > 0){
				System.out.println("Avg nodes for depth " + i + " = " + avg.getAverageNodes(i) + "\t Avg time (nano) = " + avg.getAverageTime(i));
			}
		}

	}

}
