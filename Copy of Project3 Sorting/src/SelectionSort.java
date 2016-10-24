
public class SelectionSort implements SortAlgorithm {
	public void sort(double[] array, SortTimer t)
	{
		int min;
		double temp;
		for(int i = array.length - 1; i > 0; i--)
		{
			min = i;
			for(int j = i; j >= 0; j--)	//find the next min item
			{
				if(array[j] > array[min]) min = j;
				t.addComparison();
			}
			//min is found, swap with i
			temp = array[i];
			array[i] = array[min];
			array[min] = temp;
			t.addMoves(3);
		}
	}
}
