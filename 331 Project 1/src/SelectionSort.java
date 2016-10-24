
public class SelectionSort implements SortAlgorithm {
	//Total: n(9n + 16) + 7 = 9n^2 + 16n + 7
	//+5
	public void sort(double[] array)
	{
		int max;
		double temp;
		//Total: n(9n + 16) + 2
		//+2
		for(int i = array.length - 1; i > 0; i--)	//+3 Total: (9n + 16)(n)
		{
			
			//+2
			max = i;
			//Total:9n
			for(int j = i; j >= 0; j--)	//+3 find the next max item
			{
				if(array[j] > array[max]) max = j; //+6
			}
			//max is found, swap with i
			//+11
			temp = array[i];
			array[i] = array[max];
			array[max] = temp;
		}
	}
}
