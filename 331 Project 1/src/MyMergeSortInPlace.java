
public class MyMergeSortInPlace implements SortAlgorithm{
	
	private int arraySize;
	private int powerMax;
	private double[] array;
	private StringBuilder problem;
	public void sort(double[] array) {
		this.array = array;
		arraySize = array.length;
		powerMax = (int) (Math.log(arraySize)/Math.log(2));
		int step = 0;
		problem = new StringBuilder();
		//System.out.println("PowerMax: " + powerMax);
		//fillArrayRand();
		//save(array);
		//print(array);
		//for each grouping of 2^i < arraySize 
		for(int powerSize = 0; powerSize < powerMax; powerSize++)
		{
			step = (int)Math.pow(2, powerSize);
			//each grouping size of 2^i
			//for(int i = 0; i < Math.pow(2, powerMax - powerSize);i++)
			for(int i = 0; i < arraySize;i+=2*step)	
			{
				//System.out.println("Step: " + step + "  i=" + i + " i+step: " + (i+step));
				merge(i,(i+step),step);
				
			}	
		}
		
//		if(!verifyAscending(this.array))
//		{
//			System.out.println("BROKEN\n" + problem);
//			
//			//print(array);
//		}
		
		//System.out.print("Worked!");
		
	}
	
	public void merge(int left, int right, int rightLength)
	{		
		
		int rightEnd = right + rightLength;
		double temp;
		
		//check to see if items are in both lists
		while((left != right) && right < rightEnd)
		{
			//if the left list element is <
			if(array[left] <= array[right])
			{
				//if left simply left++
				left++;
			}
			else
			{
				//else shift left items down
				
				temp = array[right];
				
				for(int i = right; i > left; i--)
				{
					array[i] = array[i - 1];
				}
				
				array[left] = temp;
				left++;
				right++;
				
			}
			//print(array);
			//save(array);
			
		}
		
		
	}
	
	public void fillArrayRand()
	{
		for(int i = 0; i < array.length; i++)
		{
			array[i] = (int)(Math.random()*10);
		}
	}
	
	
	public void save(double [] array)
	{
		problem.append("\n");
		for(int i = 0; i < array.length; i++)
		{
			problem.append(array[i] + ", ");
		}
		problem.append("\n");
	}
	
	public static boolean verifyAscending(double[] array)
	{
		for(int i = 0; i < array.length-1; i++) if(array[i] > array[i+1]) return false;
		return true;
	}

}
