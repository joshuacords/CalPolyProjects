import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {

		LocalSearch localSearch1 = new HillClimbing();
		MinConflicts localSearch2 = new MinConflicts();
		GenerateRandomNQueens gq = new GenerateRandomNQueens();

		Average avg1 = new Average();
		Average avg2 = new Average();
		MyTimer timer = new MyTimer();
		long nanoSeconds = 0;
		List<NQueensState> winningStates = new LinkedList<NQueensState>();


		Scanner input = new Scanner(System.in);

		System.out.println("Enter size n: ");
		int nSize = input.nextInt();

		System.out.println("Enter number of iterations: ");
		int iterations = input.nextInt();

		System.out.println("Enter max moves: ");
		int max = input.nextInt();

		input.close();

		for(int i = 0; i < iterations; i++){
			NQueensState initialState = new NQueensState(gq.randomState(nSize));
			localSearch1.setState(initialState);
			localSearch2.setState(initialState);

			timer.reset();
			NQueensState finalState1 = localSearch1.run();
			nanoSeconds = timer.getElapsedTime();

			avg1.put((finalState1.attackingQueens()),  nanoSeconds);

			timer.reset();
			NQueensState finalState2 = localSearch2.run(max);
			nanoSeconds = timer.getElapsedTime();

			avg2.put((finalState2.attackingQueens()),  nanoSeconds);

			if((finalState1.attackingQueens()) == 0){
				winningStates.add(finalState1);
			}
			if((finalState2.attackingQueens()) == 0){
				winningStates.add(finalState1);
			}

		}

		for(NQueensState qs : winningStates){
			System.out.println(qs);
		}

		System.out.println("Results for " + localSearch1.getClass().toString());
		for(int i = 0 ; i < 10; i++){
			if(avg1.getNumAttackingQueens(i) > 0){
				System.out.println(i + " attacks: " + avg1.getNumAttackingQueens(i)
					+ "\tPercent of all: " + avg1.getPercentAttackingQueens(i)*100 + "%");
			}
		}
		System.out.println("\nAvg time (micro) = " + avg1.getAverageTime()/1000);

		System.out.println("Results for " + localSearch2.getClass().toString() + " Max Moves: " + max);
		for(int i = 0 ; i < 10; i++){
			if(avg2.getNumAttackingQueens(i) > 0){
				System.out.println(i + " attacks: " + avg2.getNumAttackingQueens(i)
					+ "\tPercent of all: " + avg2.getPercentAttackingQueens(i)*100 + "%");
			}
		}
		System.out.println("\nAvg time (micro) = " + avg2.getAverageTime()/1000);

	}
}