
public class InsertionSort implements SortAlgorithm {

	public void sort(double[] array, SortTimer t) 
	{
		for(int i=1; i < array.length; i++)
		{
			//save value to be inserted
			double tempInsert = array[i];
			int j;
			
			//shift down until correct spot is found
			for(j = i; j > 0 && tempInsert < array[j-1]; j--)
			{
				array[j] = array[j-1];
				//System.out.print(j + ", ");
				t.addComparison();
				t.addMove();
			}
			//insert saved element
			array[j] = tempInsert;
			t.addMove();
			//print(array);
		}
		
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
