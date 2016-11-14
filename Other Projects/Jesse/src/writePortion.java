/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* @author Jessica Kruppa*/

import java.io.*;
import java.util.*;

//import static name_index.name_index.gatherInput;



public class writePortion
{
	writePortion() throws IOException
	{
	File file = new File ("name_list.txt");
	BufferedWriter bw = new BufferedWriter(new FileWriter(file.getName(),true));
	String lastName;
	String firstName;
//	String anotherName = "y";
    
	//while (anotherName.equalsIgnoreCase("y") || anotherName.equalsIgnoreCase("yes"))
	//{
    	//Enter last name
    	System.out.print("Enter the person's last name: ");
    	lastName = gatherInput();

    	//Enter first name, initial
    	System.out.print("Enter the person's first name and middle initial (optional): ");
    	firstName = gatherInput();
    	//write the last and first name to the file, separated by a comma
    	bw.write(lastName + ", " + firstName);
    	bw.newLine();

    	System.out.println("Thank you, you have added " + lastName + ", " + firstName + " to your name index.");
    
//    	System.out.print("Would you like to enter another name (y/n)?: "); //IgnoreCase, and allow Yes/No
//    	anotherName = gatherInput();
    	bw.close();
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


