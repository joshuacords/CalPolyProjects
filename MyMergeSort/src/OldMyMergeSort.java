
public class OldMyMergeSort {
	
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
		fillArrayRand();
		save(array);
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
		
		if(!verifyAscending(this.array))
		{
			System.out.println("BROKEN\n" + problem);
			
			//print(array);
		}
		//this.array = new double[]{1,6,11,12,2,3,8,9};
		
//		for(int i = 0; i < 100000; i++)
//		{
//			fillArray();
//			//print(this.array);
//			
//			merge(0,4,4);
//			if(!verifyAscending(this.array))
//			{
//				System.out.println("BROKEN");
//				print(array);
//			}
//		}
		System.out.print("Worked!");
		
	}
	
	public void merge(int left, int right, int rightLength)
	{		
		int rightEnd = right + rightLength, leftOldIndex = -1, leftSpaceHolder;
		double temp;
		
		//check to see if items are in both lists
		while((left != right) && right < rightEnd)
		{
			//if the left list element is <
			if(array[left] <= array[right])
			{
				//if left is not in right index simply left++ else switch it
				if(leftOldIndex == -1)
				{
					left++;
					//System.out.print("LEFT w/o leftOldIndex");
				}
				else{
					//System.out.print("LEFT w/leftOldIndex");
					//while there are still left indexes on the right side
					leftSpaceHolder = leftOldIndex;
					while(left < right)
					{
						//put the 1st left element at the front and move all the left elements right one
						temp = array[left];
						//System.out.print("\nShift down array");
						//move elements in array down one
						for(int i = left; i > leftOldIndex; i--)
						{
							array[i] = array[i - 1];
						}
						//put the left in order at the front
						array[leftOldIndex++] = temp;
						left++;
					}
					
					//System.out.print("\nleft: " + left + " right: " + right + "leftOldIndex: " + leftOldIndex);
					//left is now immediately following the ordered part and leftOldIndex can be reset to 0
					left = leftSpaceHolder + 1;
					leftOldIndex = -1;
				}
			}
			else
			{
				//if no leftOldIndex just swap
				if(leftOldIndex == -1)
				{
					//System.out.print("RIGHT w/o leftOldIndex");
					temp = array[right];
					array[right] = array[left];
					array[left] = temp;
					//leftOldIndex set to left's second index
					leftOldIndex = left + 1;
					left = right++;
				}else{		//there is a leftOldIndex
					temp = array[right];
					array[right] = array[leftOldIndex];
					array[leftOldIndex] = temp;
					//System.out.print("RIGHT W/leftOldIndex");
					leftOldIndex++;
					right++;
				}
				
			}
			//print(array);
			save(array);
			
		}
		
		
	}
	
	public void fillArrayRand()
	{
		for(int i = 0; i < array.length; i++)
		{
			array[i] = (int)(Math.random()*10);
		}
	}
	
	public void fillArray()
	{
		for(int i = 0; i < 4; i++)
		{
			array[i] = Math.random() + i;
		}
		for(int i = 5; i < 8; i++)
		{
			array[i] = Math.random() + i;
		}
	}
	
	public void print(double [] array)
	{
		System.out.println();
		for(int i = 0; i < array.length; i++)
		{
			System.out.print(array[i] + ", ");
		}
		System.out.println();
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
