
public class InsertionSort implements SortAlgorithm {

	//Total: 11n^2 + 9n + 6 Worse    or    16n + 6 Best
	//+5
	public void sort(double[] array) 
	{
		//Total: n(11n + 9) + 1
		//+1
		for(int i=1; i < array.length; i++)	//+4 Total: n(11n + 9)
		{
			//save value to be inserted
			//Total: 11n + 5
			//+2
			double tempInsert = array[i];
			int j;
			
			//shift down until correct spot is found
			//+1
			for(j = i; j > 0 && tempInsert < array[j-1]; j--)	//+7 Total: 11n
			{
				//+4
				array[j] = array[j-1];
				//System.out.print(j + ", ");
				
			}
			//insert saved element
			//+2
			array[j] = tempInsert;
		}	
	}
}
