
public class BinarySearch implements SearchAlgorithm{

	//need best and worst case
	//Total: 10n + 11
	//+8
	public boolean search(double[] array, double item) {
		//+3
		int low = 0, high = array.length -1, mid;
		
		//Total: 10n
		while(low <= high)  //+1
		{
			mid = (low + high)/2; //+3
			if(array[mid] > item) high = mid - 1;	//+2 +2
			else if(array[mid] < item) low = mid + 1; //+2 +2
			else return true; //mid is array index
			
		}
		return false;
	}
}

