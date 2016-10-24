import java.util.Scanner;


public class main {

	public static void main(String args[]) {
		int mpg;
		int mil;
		
		
		
		System.out.println("How many gallons have you used? (enter miles and mpg)");
		Scanner scanner = new Scanner(System.in);
		mil = scanner.nextInt();
		mpg = scanner.nextInt();
		int gal = mil / mpg;
		System.out.print("I have used ");
		System.out.print(gal);
		System.out.print(" gallons.");
	}

}


//int age;
//int newAge;
//Scanner scanner = new Scanner(System.in);
//
//age = 18;
//System.out.println("How old are you?");
//
//age = scanner.nextInt();
//newAge = age + 1;
//System.out.print("You will be ");
//System.out.print(newAge);
//System.out.print(" next year!");