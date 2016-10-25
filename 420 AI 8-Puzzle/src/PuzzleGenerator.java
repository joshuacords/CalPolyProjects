import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PuzzleGenerator {

	private int[][] _puzzle;
	private Heuristic _heuristic;


	PuzzleGenerator(){
		Scanner input = new Scanner(System.in);

		switch(menu(input)){
			case 1: enterPuzzle(input);
				break;
			case 2: generatePuzzle();
				break;
			case 3: fileTest(input);
		}

		input.close();
	}

	private void fileTest(Scanner input) {
		System.out.println("Enter file name: ");
		String fileName = input.nextLine();
		List<String> puzzles = new ArrayList<String>();
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = reader.readLine()) != null)
			{
				puzzles.add(line);
			}
			reader.close();
		}
		catch (Exception e)
		{
			System.err.format("Exception occurred trying to read '%s'.", fileName);
			e.printStackTrace();
		}

		_puzzle = new int[puzzles.size()][9];

		int i = 0;
		for(String puzzle : puzzles){
			 toArray(_puzzle[i++], puzzle);
		}

	}

	private void toArray(int[] puzzleArray, String puzzleString) {
		for (int i = 0; i < puzzleString.length(); i++) {
	        char c = puzzleString.charAt(i);
	        if (Character.isDigit(c)) {
	        	puzzleArray[i] = Character.getNumericValue(c);
	        }
	    }
	}

	private void generatePuzzle() {
		List<Integer> ints = new ArrayList<Integer>();
		for(int i = 0; i < 9; i++){
			ints.add(i);
		}
		Collections.shuffle(ints);

		_puzzle = new int[1][9];
		for(int i = 0; i < 9; i++){
			_puzzle[0][i] = ints.get(i);
		}

		if(!puzzleIsValid(_puzzle[0])){
			generatePuzzle();
		}

	}

	private boolean puzzleIsValid(int[] puzzle) {
		int inversions = 0;
		for(int i = 0; i < puzzle.length; i++){
			for(int j = i; j < puzzle.length; j++){
				if(puzzle[i] > puzzle[j]){
					inversions++;
				}
			}
		}
		return (inversions%2 == 0);
	}

	private void enterPuzzle(Scanner input) {

		_puzzle = new int[1][9];

		System.out.println("Enter puzzle in 3x3 format: ");
		for(int i = 0; i < 9; i++){
			_puzzle[0][i] = input.nextInt();
		}

		if(!puzzleIsValid(_puzzle[0])){
			System.out.println("Puzzle not valid");
			enterPuzzle(input);
		}
	}

	private int menu(Scanner input){
		int entry = 0;

		System.out.println("AStar Search Menu\nChoose Heuristic\n"
				+ "1. Misplaced Tiles\n2. Manhattan Distance");
		entry = input.nextInt();
		input.nextLine();

		if(entry == 1){
			_heuristic = new MisplacedTiles();
		} else {
			_heuristic = new ManhattanDistance();
		}

		System.out.println("1. Enter a puzzle\n2. Generate random puzzle");
		System.out.println("3. Test on file");

		entry = input.nextInt();
		input.nextLine();

		if(entry < 4 && entry > 0){
			return entry;
		}

		return menu(input);
	}

	public int[][] getPuzzle() {
		return _puzzle;
	}

	public Heuristic getHeuristic() {
		return _heuristic;
	}

}
