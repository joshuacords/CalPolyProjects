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
		//print(array);
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

/*
 System.out.println();
		print(array);
		System.out.println("Pivot= " + array[pivot] + " Left=" + array[left] + " Right=" + array[right]);
		for(int i = left; i <= right; i++)
		{
			System.out.print(array[i] + ", ");
		}
		System.out.println();
		
		if(pivot-left > right-pivot) 
		{
		//	quickSort(pivot + 1,right);
		//	quickSort(left,pivot-1);
		}
		//else
		{
			quickSort(left,pivot-1);
			quickSort(pivot + 1,right);
		}	
		
		
		
		
 * 
 * public class QuickSort  {
 
private double[] numbers;
private int number;
private SortTimer t;

public void sort(double[] values, SortTimer t) 
{
	// check for empty or null array
	if (values ==null || values.length==0){
		return;
	}
	this.numbers = values;
	this.t= t;
	number = values.length;
	basicQuickSort(values, 0, number);
}

public void basicQuickSort(double arr[], int beginIdx, int len) 
{
    if ( len <= 1 )
    {
    	//t.addComparison();
    	return;
    }
    
    final int endIdx = beginIdx+len-1;

    // Pivot selection
    final int pivotPos = beginIdx+len/2;
    final double pivot = arr[pivotPos];
    exchange(arr, pivotPos, endIdx);

    // partitioning
    int p = beginIdx;
    for(int i = beginIdx; i != endIdx; i++) 
    {
         if ( arr[i] <= pivot ) 
         {
             exchange(arr, i, p++);
             //t.addComparison();
         }
    }
    exchange(arr, p, endIdx);

    // recursive call
    basicQuickSort(arr, beginIdx, p-beginIdx);
    basicQuickSort(arr, p+1,  endIdx-p);
}

private void exchange(double[] array, int i, int j)
{ 
    double temp = array[i]; 
    array[i] = array[j]; 
    array[j] = temp; 
    //t.addMove();
} 
} */