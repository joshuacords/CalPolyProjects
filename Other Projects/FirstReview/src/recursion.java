
public class recursion {

	public void print(int i, int j)
	{
		if(i>=j)
		{
			System.out.println(j++);
			print(i,j);
		}
	}
}
