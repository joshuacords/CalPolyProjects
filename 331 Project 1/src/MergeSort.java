

public class MergeSort implements SortAlgorithm {

	private double[] array;
	private double[] temp;
	private int arraySize;
	@Override
	public void sort(double[] array) {

		if (array ==null || array.length==0){
			return;
		}
		arraySize = array.length;
		this.array = array;
		temp = new double[arraySize];
		mergeSort(0,arraySize - 1);

		
	}
	
	//      +1       +4       +1
	private void mergeSort(int left, int right)
	{
		
		if(left < right)
		{
			
			int middle = (left + right)/2;
			
//			if((left + right)%2 == 1)	//Check if odd or even for left and right sizes
//			{
//				//    +1    +1     +1    +1
//				right = new double[size/2 + 1];
//			} else {
//				//    +1    +1     +1
//				right = new double[size/2];
//			}
			
			mergeSort(left, middle);
			mergeSort(middle+1,right);
			merge(left,middle,right);
		}
	}
	
	private void merge(int left, int middle, int right)
	{
//		System.out.println("Left: " + left + " Middle: " + middle + " Right: " + right);
//		System.out.print("Before: ");
//		print();
		for(int i = left; i < right; i++)
		{
			temp[i] = array[i];
		}
		
		int leftTemp = left;
		int rightTemp = middle + 1;
		int leftIndex = left;
		
		while(leftTemp <= middle && rightTemp <= right)
		{
			
			if(temp[leftTemp] <= temp[rightTemp])
			{
				array[leftIndex] = temp[leftTemp++];
			} else {
				array[leftIndex] = temp[rightTemp++];
			}
			leftIndex++;
		}
		
		while(leftTemp <= middle)
		{
			array[leftIndex++] = temp[leftTemp++];
		}
//		System.out.print("After:  ");
//		print();
		
	}
	private void print()
	{
		for(int i = 0; i < array.length; i++)
		{
			System.out.print(array[i] + ", ");
		}
		System.out.println();
	}
}
