


public class OldMergeSort implements SortAlgorithm {

	private double[] array;
	private int arraySize;
	@Override
	public void sort(double[] array) {

		if (array ==null || array.length==0){
			return;
		}
		arraySize = array.length;
		this.array = array;
		this.array = mergeSort(this.array);
//		for(int i = 0; i < this.array.length; i++)
//		{
//			array[i] = this.array[i];
//		}
		
	}
	
	//      +1       +4       +1
	private double[] mergeSort(double[] array)
	{
		//  +1   +1
		int size = array.length;
		//System.out.println("Array size: " + size);
		
		
		//+1    +1
		if(size <= 1) return array;
		
		//     +1     +1    +1
		double[] left = new double[size/2];
		//     +1
		double[] right;
		//     +1  +1   +1
		int middle = size/2;
		// +1 +1   +1
		if(size%2 == 1)	//Check if odd or even for left and right sizes
		{
			//    +1    +1     +1    +1
			right = new double[size/2 + 1];
		} else {
			//    +1    +1     +1
			right = new double[size/2];
		}
		
		//System.out.println("Middle : " + middle + "\nLeft size : " + left.length);
		//     +1 (once)  +1     +2    = 6n + 1
		for(int i = 0; i < middle; i++)
		{
			//System.out.println("Left add element " + i);
			// +1   +1   +1
			left[i] = array[i];
		}
		
		//System.out.println("Right size : " + right.length);
		
		for(int i = middle; i < size; i++)
		{
			//int rightElement = i - middle;
			//System.out.println("Right add element " + rightElement);
			right[i-middle] = array[i];
		}
		left = mergeSort(left);
		right = mergeSort(right);
		return merge(left,right);
	}
	
	private double[] merge(double[] left, double[] right)
	{
		double[] result;
		if(left.length + right.length != arraySize)
		{
			result = new double[left.length + right.length];
		} else {
			result = array;
		}
		int leftIndex, rightIndex, resultIndex;
		leftIndex = rightIndex = resultIndex = 0;
		//System.out.println("\nLeft size: " + left.length + " Right size: " + right.length + " Result size: " + result.length + " LeftIndex: " + leftIndex + " RightIndex: " + rightIndex + " ResultIndex: " + resultIndex);
		//System.out.print("Left: ");
		//print(left);
		//System.out.print("Right: ");
		//print(right);
		while(leftIndex < left.length|| rightIndex < right.length)
		{
			
			if(leftIndex < left.length && rightIndex < right.length)
			{
				if(left[leftIndex] <= right[rightIndex])
				{
					//System.out.println("Compare: " + left[leftIndex] + " <= " + right[rightIndex]);
					result[resultIndex++] = left[leftIndex++];
				}
				else
				{
					//System.out.println("Compare: " + left[leftIndex] + " > " + right[rightIndex]);
					result[resultIndex++] = right[rightIndex++];
				}
			}
			else if(leftIndex < left.length)
			{
				//System.out.println("Only left remaining");
				result[resultIndex++] = left[leftIndex++];
			}
			else
			{
				//System.out.println("Only right remaining");
				result[resultIndex++] = right[rightIndex++];
			}
		}
		//print(result);
		return result;
	}
//	private void print(double [] array)
//	{
//		for(int i = 0; i < array.length; i++)
//		{
//			System.out.print(array[i] + ", ");
//		}
//		System.out.println();
//	}
}

