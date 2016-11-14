//package name_index;

/* Jessica Kruppa  */
import java.io.*;
import java.util.*;

public class readPortion
{

	readPortion() throws FileNotFoundException, IOException
	{
    	//calling the file, I think? Program doesn't work without it.
    	BufferedReader br = new BufferedReader(new FileReader("name_list.txt"));
    	String line = br.readLine();
    	String searchWord;
    	int count = 0;
   	 
    	//what are we searching for?
    	System.out.print("Enter a string of characters in which to search by or enter \"all names\" for a complete list: ");
    	searchWord = gatherInput();
   	 
    	System.out.println("Search Results include: ");
   	 
    	//making an arraylist out of the file
    	ArrayList<String> list = new ArrayList<String>();
    	Scanner inFile = new Scanner(new File("name_list.txt"));
    	while(inFile.hasNextLine())
    	{
        	list.add(inFile.nextLine());
    	}
    	Collections.sort(list);
    	
    	//conducting the if/then for "all names" or "search"
    	if(searchWord.equalsIgnoreCase("all names"))
    	{
        	for(String temp: list)
        	{
            	System.out.println(temp);
        	}
    	}
    	// else if the user entered string, search up each line... WHY YOU BREAK?!
//    	else if (line.toLowerCase().contains(searchWord.toLowerCase()))
    	else
    	{
        	//while ((line = br.readLine()) != null && count < list.size())
       	// {
        	//count++;
    		//for each string in the sorted list
    		for(String temp: list)
    		{
    			//print it out if it matches the search term
    			if(temp.toLowerCase().contains(searchWord.toLowerCase()))
    			{
    				System.out.println(temp);
    			}
    		}
    		
        	//}
        	br.close();
    	}
    

    	//we're done printing stuff from the file.
    	System.out.println("End!");
	} 
	
    //method outside of main, gathering user input with a scanner
    public static String gatherInput()
	{
    	Scanner scan = new Scanner(System.in);
    	String user_input = scan.nextLine();
    	return user_input;
	}
}
