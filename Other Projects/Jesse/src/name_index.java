//package name_index;
/* Jessica Kruppa  */

import java.io.*;
import java.util.*;

public class name_index
{   	 
	public static void main(String[] args) throws IOException
	{
    	String option = "0";
    	String anotherName;
    	String retrieve;
   	 
    	//attempting this menu thing...
    	System.out.print("Enter '1' to add a name to the index, enter '2' to retrieve names: ");
    	option = gatherInput();
   	 
    	while (option.equals("1"))
    	{
        	writePortion aName = new writePortion();
        	System.out.print("Would you like to enter another name (y/n)?: ");
        	anotherName = gatherInput();
        	if (anotherName.equalsIgnoreCase("y") || anotherName.equalsIgnoreCase("yes"))
        	{
            	option = "1";
        	}
        	else if (anotherName.equalsIgnoreCase("n") || anotherName.equalsIgnoreCase("no"))
        	{
            	option = "0";
        	}
    	}
   
    	while (option.equals("2"))
    	{   	 
        	readPortion aRead = new readPortion();
        	System.out.print("Would you like to retrieve names from your index?: ");
        	anotherName = gatherInput();
        	if (anotherName.equalsIgnoreCase("y") || anotherName.equalsIgnoreCase("yes"))
        	{
            	option = "2";
        	}
        	else if (anotherName.equalsIgnoreCase("n") || anotherName.equalsIgnoreCase("no"))
        	{
            	option = "0";
            	System.out.println("Thank you, come again!");
        	}
    	}
	}
    
	public static String gatherInput()
	{
    	Scanner scan = new Scanner(System.in);
    	String user_input = scan.nextLine();
    	//codecheck
    	//System.out.println(user_input);
    	return user_input;
	}    
}
