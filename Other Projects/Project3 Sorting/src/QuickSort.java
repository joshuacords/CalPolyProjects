public class QuickSort implements SortAlgorithm
{
	private double[] array;
	private SortTimer t;

	public void sort(double[] array, SortTimer t) 
	{
		// check for empty or null array
		if (array ==null || array.length==0){
			return;
		}
		this.array = array;
		this.t= t;
		quickSort(0, array.length-1);
	}

	public void quickSort(int left, int right) 
	{
		if(left >= right ) return;
		//set pivot
		int pivot = right;
		pivot = partition(left, right, pivot);
		quickSort(left,pivot-1);
		quickSort(pivot + 1,right);
	}
	
	private int partition(int left, int right, int pivot)
	{
		//exchange(array, pivot, right);	//Since pivot is right doesn't matter
		//pivot = right;
		
		int pos = left;
		for(int i = left; i < right; i++)
		{
			t.addComparison();
			if(array[i] <= array[pivot]) exchange(array, i, pos++);	//right is currently the pivot index
		}
		exchange(array,pivot,pos);	//exchanges pivot from the end to the middle
		return pos;
	}
	
	private void exchange(double[] array, int i, int j)
    { 
        double temp = array[i]; 
        array[i] = array[j]; 
        array[j] = temp; 
        t.addMoves(3);
    } 
	
	public void print(double [] array)
	{
		for(int i = 0; i < array.length; i++)
		{
			System.out.print(array[i] + ", ");
		}
		System.out.println();
	}
} 

