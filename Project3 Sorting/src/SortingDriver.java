import java.util.Scanner;


public class SortingDriver 
{

	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);
		SortTimer sortTimer = new SortTimer();
		SortAlgorithm s;
		int arrayPower, arraySize = 0;
		String choice, sortType;
		
		if(args.length>1)
		{
			choice = args[0];
			arrayPower = Integer.parseInt(args[1]);
		}
		else
		{
			System.out.println("What sort type? (s,i,h,q):");
			choice = scanner.next();
			
			System.out.println("10^x: What is x?");
			arrayPower = scanner.nextInt();
		}
	
			
		switch(choice)
		{
			case "s": s = new SelectionSort();
				sortType = "Selection";
				break;
			case "i": s = new InsertionSort();
				sortType = "Insertion";
				break;
			case "h": s = new HeapSort();
				sortType = "Heap";
				break;
			case "q": s = new QuickSort();
				sortType = "Quick";
				break;
			default:	System.out.println("Invalid: Input 's' for Selection sort, 'i' for Insertion sort, 'h' for Heap sort, 'q' for Quick sort\nAnd enter a power. Example: h 4");
				scanner.close();
				return;
		}
		
		System.out.println(sortType + " Sort");
		System.out.printf("%1s%13s%12s%10s\n","N","Microseconds","Comparisons","Moves");
		
		for(int j = 1; j <= arrayPower; j++)
		{
			long average[] = new long[3];	//stores averages as time, comparisons, moves
			
			for(int k = 0; k < 5; k++)	//averages 5 runs
			{
				arraySize = (int) Math.pow(10, j);
				double array[] = new double[arraySize];
				for(int i = 0; i < arraySize; i++) array[i] = Math.random();
				
				sortTimer.reset();
				s.sort(array, sortTimer);
				average[0] += sortTimer.getElapsedTime();
				average[1] += sortTimer.getComparisons();
				average[2] += sortTimer.getMoves();
			}
			average[0] /= 5;
			average[1] /= 5;
			average[2] /= 5;
			
			System.out.printf("%1d%13d%12d%10d\n",j,average[0]/1000,average[1],average[2]);
		}		
		scanner.close();
	}
	
	
	public static boolean verifyAscending(double[] array)
	{
		for(int i = 0; i < array.length-1; i++) if(array[i] > array[i+1]) return false;
		return true;
	}
	
	public static void print(double [] array)
	{
		for(int i = 0; i < array.length; i++)
		{
			System.out.println(array[i] + ", ");
		}
	}

}
