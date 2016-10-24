
public class SequentialSearch implements SearchAlgorithm{
	//need best and worst case
	//Total 5n+9
	//+8
	public boolean search(double[] array, double item) {
		
		//Total: 5n+1
		//+1
		for(double element : array) //+3 (; i < size; i++)
		{
			if(element == item)return true; //+2 (index and comparison)
		}
		return false;
		
	}

}
