import java.util.Scanner;

//Ctrl + Shift + O to include libs
public class firstweek {

	public static void main(String[] args) {
		
		int numRecur = 0;
		if(args.length > 0)
		{
			numRecur = Integer.parseInt(args[0]);
		}
		else
		{
			System.out.println("Enter # of recursions: ");
			Scanner s = new Scanner(System.in);
			numRecur = s.nextInt();
			s.close();
		}
		
		//System.out.println(numRecur);
		recursion r = new recursion();
		r.print(numRecur, 0);
		

	}
	
	

}

