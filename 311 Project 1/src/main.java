

public class main {

	public static void main(String[] args) {
		UniversalFA u = new UniversalFA();
		u.load("M2.txt");
		System.out.println(u);
		//Manually enter test strings to inputs[]
		//Test strings should be separated by a single comma and a single space
		//An empty string, or lambda, should have no input
		//The following example begins with the empty string, followed by other test strings
		//ex: ", 100, 011, 10abc1, 0, 1, 0101011, 11010, 0001, 1110"
		String inputs []= {", 1, 000, 101, 111, 01001, 1011011, 1011000, 01010, 1010101110"};
		String testStrings[] = inputs[0].split(", ");
		
		for(String testString : testStrings)
		{
			char testChar[] = testString.toCharArray();
			if(u.acceptString(testChar))
			{
				//System.out.println("\"" + testString + "\"\t Accepted");
				System.out.printf("%-20s %-20s\n", testString, "Accepted");
			} else {
				//System.out.println("\"" + testString + "\"\t Rejected");
				System.out.printf("%-20s %-20s\n", testString, "Rejected");
			}
		}
	}

}


//
//
//public class main {
//
//	public static void main(String[] args) {
//		UniversalFA u = new UniversalFA();
//		u.load("text.txt");
//		System.out.println(u);
//		String inputs []= {", 100, 011, 10abc1, 0, 1, 0101011, 11010, 0001, 1110"};
//		String testStrings[] = inputs[0].split(", ");
////		String testString = "abaab";
//		
//		for(String testString : testStrings)
//		{
//			char testChar[] = testString.toCharArray();
//			if(u.acceptString(testChar))
//			{
//				System.out.println("\"" + testString + "\"\t Accepted");
//			} else {
//				System.out.println("\"" + testString + "\"\t Rejected");
//			}
//		}
//	}
//
//}
