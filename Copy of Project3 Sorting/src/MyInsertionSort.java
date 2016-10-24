
public class MyInsertionSort implements SortAlgorithm {
	public void sort(double[] array, SortTimer t)
	{
		int j;
		double tempInsert;
		//for each element in the array starting with the second
		for(int i = 1; i < array.length; i++)
		{
			j = i-1;			
			//compare current value from i-1 to 0, j will be position the i element will be inserted
			for(; j > 0; j--)
			{
				System.out.println("array.size="+ array.length + " i=" + i +" j="+ j + " array[" + i + "]=" + array[i] + " array["+j+"]=" + array[j]);
				if(array[i]>array[j]) break;
			}
			
			if(i!=j)	//if swap needs to be made
			{
				System.out.println("swap ");
				tempInsert = array[i];
				//shift array right
				for(int k = i; k > j; k--)
				{
					array[k] = array[k-1];
				}
				//insert value into its correct position
				array[j] = tempInsert;
			}			
		}
	}		
}
