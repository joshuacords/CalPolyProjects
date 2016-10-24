public class QuickSort implements SortAlgorithm
{
	//need best and worst case
	private double[] array;

	//Total +9
	public void sort(double[] array) 
	{
		// check for empty or null array
		//+2
		if (array ==null || array.length<=1){
			return;
		}
		//+1
		this.array = array;
		//+1
		quickSort(0, array.length-1);
		
	}

	//Total +11
	//+6
	public void quickSort(int left, int right) 
	{
		//+1
		if(left >= right ) return;
		//set pivot +1
		int pivot = right;
		//+1
		pivot = partition(left, right, pivot);
		//+1
		quickSort(left,pivot-1);
		//+1
		quickSort(pivot + 1,right);
	}
	
	//Total 8n+20
	//+11
	private int partition(int left, int right, int pivot)
	{
		//+1
		int pos = left;
		//Total 17n+1 
		//+1
		for(int i = left; i < right; i++)  //+3
		{
			//+5
			if(array[i] <= array[pivot])
			{
				//right is currently the pivot index
				//+9
				double temp = array[i]; 
		        array[i] = array[pos]; 
		        array[pos++] = temp; 
			}
		}
		//exchanges pivot from the end to the middle
		//+7
		double temp = array[pivot]; 
        array[pivot] = array[pos]; 
        array[pos] = temp; 
		return pos;
	}
	
	
} 


