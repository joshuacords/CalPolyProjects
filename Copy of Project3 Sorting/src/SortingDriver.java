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
		
		System.out.println("What sort type? (s,i,h,q):");
		choice = scanner.next();
		
		System.out.println("10^x: What is x?");
		arrayPower = scanner.nextInt();
			
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
			default:	System.out.println("Invalid");
				scanner.close();
				return;
		}
		
		System.out.println(sortType + "Sort");
		System.out.printf("%1s%13s%12s%10s\n","N","Microseconds","Comparisons","Moves");
		
		for(int j = 1; j <= arrayPower; j++)
		{
			long average[] = new long[3];	//stores averages as time, comparisons, moves
			
			for(int k = 0; k < 5; k++)
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

/*for(int j = 1; j <= arrayPower; j++)
		{
			arraySize = (int) Math.pow(10, j);
			double array[] = new double[arraySize];
			double array2[] = new double[arraySize];
			double array3[] = new double[arraySize];
			double array4[] = new double[arraySize];
			//double array4[] = {1,7,4,6,5,8};
			for(int i = 0; i < arraySize; i++) array4[i] = array3[i] = array2[i] = array[i] = Math.random();
			
			//Selection sort
			SelectionSort sSort = new SelectionSort();
			sortTimer.reset();
			sSort.sort(array, sortTimer);
			time = sortTimer.getElapsedTime();
			
			System.out.println("Selection Sort 10^" + j);
			//print(array);
			if(verifyAscending(array)) System.out.println("Sorted in " + time/1000 + " microseconds!");
			else System.out.println("Not Sorted in " + time/1000 + " microseconds!");
			
			//Insertion sort
			InsertionSort iSort = new InsertionSort();
			sortTimer.reset();
			iSort.sort(array2, sortTimer);
			time = sortTimer.getElapsedTime();
			
			System.out.println("Insertion Sort 10^" + j);
			//print(array2);
			if(verifyAscending(array2)) System.out.println("Sorted in " + time/1000 + " microseconds!");
			else System.out.println("Not Sorted in " + time/1000 + " microseconds!");
			
			//Pretty output: printf("%8d%12d%12d%12d\n",n,time,comp,move);
			
			//Heap sort
			HeapSort hSort = new HeapSort();
			sortTimer.reset();
			hSort.sort(array3, sortTimer);
			time = sortTimer.getElapsedTime();
			
			System.out.println("Heap Sort 10^" + j);
			//print(array2);
			if(verifyAscending(array3)) System.out.println("Sorted in " + time/1000 + " microseconds!");
			else System.out.println("Not Sorted in " + time/1000 + " microseconds!");
			
			//Quick sort
			QuickSort qSort = new QuickSort();
			sortTimer.reset();
			qSort.sort(array4, sortTimer);
			time = sortTimer.getElapsedTime();
			
			System.out.println("Quick Sort 10^" + j);
			//print(array2);
			if(verifyAscending(array4)) System.out.println("Sorted in " + time/1000 + " microseconds!");
			else System.out.println("Not Sorted in " + time/1000 + " microseconds!");
			
		
			System.out.println();
		}
		*/
