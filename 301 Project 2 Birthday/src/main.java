import java.util.*;

public class main {
	private static Random generator = new Random(System.currentTimeMillis());
	public static void main(String[] args) {
		Set<Integer> set = new HashSet<Integer>();
		int collisions = 0;	//number of collisions that happened in the 35
		int nextDate = 0;
		
		//test 1000x
		for(int i = 0; i<1000; i++)
		{
			set.clear();
			//35 people in class
			for(int j = 0; j < 35; j++)
			{
				nextDate = randomGen();
				//if collision, record and exit to the rest of the test
				if(set.contains(nextDate))
				{
					collisions++;
					break;
				}
				set.add(nextDate);
			}
		}
		System.out.println("The number of collisions: " + collisions + " out of 1000 tries.\nRoughly " + collisions*100.0/1000 + "% chance of 35 people sharing a birthdate.");
	}
	
	static int randomGen()
	{
		return (int) (generator.nextDouble()*364 + 1);
	}

}
