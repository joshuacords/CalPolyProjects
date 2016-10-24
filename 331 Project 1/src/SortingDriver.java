import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class SortingDriver 
{
	static String cases;
	static String choice;
	static String sortType;
	static int arrayPower;
	static SortTimer sortTimer;
	static String fileName = "src/Sorting_Results.txt";
	static FileWriter fWriter;
	static PrintWriter outputFile;
	static int arraySize;
	static double array[];
	
	
	public static void main(String[] args) 
	{
		
		Scanner scanner = new Scanner(System.in);
		
		if(args.length>1)
		{
			choice = args[0];
			arrayPower = Integer.parseInt(args[1]);
			cases = args[2];
		}
		else
		{
			System.out.println("What sort type? (s,i,m,n,q) or search type? (e,b):");
			choice = scanner.next();
			
			System.out.println("2^x: What is x?");
			arrayPower = scanner.nextInt();
			
			System.out.println("Select Case: b,a,w");
			cases = scanner.next();
		}
	
			
		switch(choice)
		{
			case "s": 
			case "i": 
			case "n": 
			case "m": 
			case "q": sort();
				break;
			case "e": 
			case "b": search();
				break;
			default:	System.out.println("Invalid: Input 's' for Selection sort, 'i' for Insertion sort, 'm' for Merge sort, 'n' for Merge sort (in place non-recursive) 'q' for Quick sort, 'e' for Sequential, 'b' for Binary\nAnd enter a power. Example: h 4");
				scanner.close();
				return;
		}
		scanner.close();
		
	}
	
	public static void sort()
	{
		
		sortTimer = new SortTimer();
		SortAlgorithm sort;
		
		switch(choice)
		{
			case "s": sort = new SelectionSort();
				sortType = "Selection";
				break;
			case "i": sort = new InsertionSort();
				sortType = "Insertion";
				break;
			case "n": sort = new MyMergeSortInPlace();
				sortType = "Merge In Place";
				break;
			case "m": sort = new MergeSort();
				sortType = "Merge";
				break;
			case "q": sort = new QuickSort();
				sortType = "Quick";
				break;
			default:	System.out.println("Invalid: Input 's' for Selection sort, 'i' for Insertion sort, 'm' for Merge sort, 'n' for Merge sort (in place, non-recursive) 'q' for Quick sort, 'e' for Sequential, 'b' for Binary\nAnd enter a power. Example: h 4");
				return;
		}
		
		System.out.println(sortType + " Sort");
		System.out.printf("%2s%7s%13s\n","2^","N","Microseconds");
		try {
			fWriter = new FileWriter(fileName, true);
			outputFile = new PrintWriter(fWriter);
			outputFile.append(sortType + " Sort\n");
			outputFile.append("2^," + "N," + "Microseconds, , , , , , , , , , " + sortType + " Sort\n");
			
			for(int j = 8; j <= arrayPower; j++)
			{
				long average[] = new long[2];	//stores averages as time, comparisons, moves
				outputFile.append(j + ", " + (int)Math.pow(2, j) + ", ");
				for(int k = 0; k < 10; k++)	//averages 10 runs
				{
					arraySize = (int) Math.pow(2, j);
					array = new double[arraySize];
					
					switch(cases)
					{
						case "b":  for(int i = 0; i < arraySize; i++) array[i] = 1;
							break;
						case "w":  for(int i = 0; i < arraySize; i++) array[i] = arraySize - i;
							break;
						case "a":
						default:   for(int i = 0; i < arraySize; i++) array[i] = Math.random();
						
					}
					
					sortTimer.reset();
					sort.sort(array);
//					if(verifyAscending(array))
//					{
//						System.out.println("Sorted Correctly");
//					}else{
//						print(array);
//					}
					average[0] = sortTimer.getElapsedTime();
					average[1] += average[0];
					
					outputFile.append(average[0] + ", ");
					
					
					
				}
				
				average[1] /= 10;
				outputFile.append(average[1] + "\n");
				
				
				System.out.printf("%2d%7d%13d\n",j,(int)Math.pow(2, j),average[0]/1000);
			}
			outputFile.append("\n\n");
			outputFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void search()
	{
		sortTimer = new SortTimer();
		SearchAlgorithm search;
		boolean found = false;
		double number;
		
		switch(choice)
		{
			case "e": search = new SequentialSearch();
				sortType = "Sequential";
				break;
			case "b": search = new BinarySearch();
				sortType = "Binary";
				break;
			default:	System.out.println("Invalid: Input 's' for Selection sort, 'i' for Insertion sort, 'm' for Merge sort, 'n' for Merge sort (in place non-recursive), 'q' for Quick sort, 'e' for Sequential, 'b' for Binary\nAnd enter a power. Example: h 4");
				return;
		}
		
		System.out.println(sortType + " Search");
		System.out.printf("%2s%8s%13s\n","2^","N","Microseconds");
		try {
			fWriter = new FileWriter(fileName, true);
			outputFile = new PrintWriter(fWriter);
			outputFile.append(sortType + " Search\n");
			outputFile.append("2^," + "N," + "Microseconds, , , , , , , , , , " + sortType + " Search\n");
			
			for(int j = 8; j <= arrayPower; j++)
			{
				long average[] = new long[2];	//stores averages as time, comparisons, moves
				outputFile.append(j + ", " + (int)Math.pow(2, j) + ", ");
				for(int k = 0; k < 10; k++)	//averages 10 runs
				{
					arraySize = (int) Math.pow(2, j);
					double array[] = new double[arraySize];
					switch(cases)
					{
						case "b":  for(int i = 0; i < arraySize; i++) array[i] = 0;
							number = 0;
							break;
						case "w":  for(int i = 0; i < arraySize; i++) array[i] = 0;
							number = -1;
							break;
						case "a":
						default:   for(int i = 0; i < arraySize; i++) array[i] = Math.random();
						number = array[(int)(Math.random()*arraySize)];
						
					}
					
					
					sortTimer.reset();
					found = search.search(array,number);
					average[0] = sortTimer.getElapsedTime();
					average[1] += average[0];
					
					outputFile.append(average[0] + ", ");
					//if(found)System.out.println(found);
					
					
				}
				
				average[1] /= 10;
				outputFile.append(average[1] + "\n");
				
				
				System.out.printf("%2d%8d%13d\n",j,(int)Math.pow(2, j),average[0]/1000);
			}
			outputFile.append("\n\n");
			outputFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static boolean verifyAscending(double[] array)
	{
		for(int i = 0; i < array.length-1; i++) if(array[i] > array[i+1]) return false;
		return true;
	}
	
	public static void print(double [] array)
	{
		System.out.println("\nSorting Driver: ");
		for(int i = 0; i < array.length; i++)
		{
			System.out.print(array[i] + ", ");
		}
	}

}
