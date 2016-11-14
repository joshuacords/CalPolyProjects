
public class Driver {

	public static void main(String[] args) {
		MyMergeSort m = new MyMergeSort();
		int arraySize =  256;   //(int) Math.pow(2, j);
		double array[] = new double[arraySize];
		for(int i = 0; i < arraySize; i++) array[i] = Math.random();
		for(int i = 0; i < 100000; i++)	m.sort(array);

		//if(verifyAscending(array)) System.out.println("It Worked");
		//else System.out.println("It didn't work");
		
		//print(array);
		
	}
	
	public static boolean verifyAscending(double[] array)
	{
		for(int i = 0; i < array.length-1; i++) if(array[i] > array[i+1]) return false;
		return true;
	}
	
	public static void print(double [] array)
	{
		System.out.println("\nSorting Driver: ");
		for(int i = 0; i < array.length; i++)
		{
			System.out.print(array[i] + ", ");
		}
	}

}
