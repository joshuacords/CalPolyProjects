import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class Shop 
{
	//create trees for users and items
	static MySplayTreeMap<String, Integer> items = new MySplayTreeMap<String, Integer>();
	static MySplayTreeMap<String, ArrayList<String>> users = new MySplayTreeMap<String, ArrayList<String>>();
			

	public static void main(String[] args) 
	{

		String itemsFileName, ordersFileName;
		if(args.length>1)
		{
			itemsFileName = args[0];
			ordersFileName = args[1];
		}
		else
		{
			itemsFileName = "items.txt";
			ordersFileName = "orders.txt";
		}
		
				
		//populate items tree
		readItems(items,itemsFileName);
		
		//execute orders
		translateOrders(users, items, ordersFileName);
		
		//Testing functions
		//users.inorder();
		//System.out.println(users);
		//users.keySet();
		//System.out.println(users);
		System.out.println("Height: " + users.height());

	}
	
	
	private static void translateOrders(MySplayTreeMap<String, ArrayList<String>> users, MySplayTreeMap<String, Integer> items, String fileName)
	{
		Scanner orderFile;
		try
		{
			orderFile = new Scanner(new BufferedReader(new FileReader(fileName)));
		}
		catch(FileNotFoundException e)
		{
			System.err.println(e);
			return;
		}
		
		//cmd = "add", "drop" etc.
		//arg1 = username
		//arg2 = itemName - sometimes not present from file
		String cmd = "", arg1 = "", arg2 = "", line = "";
		String [] temp;
		
		while(orderFile.hasNextLine()){
			line = orderFile.nextLine();
			arg2 = "";
			temp = line.split(" ");
			cmd = temp[0];
			arg1 = temp[1];
			if (temp.length >2)  arg2 = temp[2];
	        switch(cmd)
	        {
	        case "add":add(cmd,arg1,arg2);
	            break;
	        case "view":view(cmd,arg1);
	            break;
	        case "checkout":checkout(cmd,arg1);
	            break;
	        case "delete":delete(cmd,arg1,arg2);
	            break;
	        default: System.out.println(cmd + " is an invalid order.");
	                
	        }

		}
				
		orderFile.close();
	}
	
	public static void add(String cmd, String username, String itemName)
	{
		System.out.println(cmd + " " + username + " " + itemName);
		if(users.containsKey(username))
		{
			if(users.get(username).contains(itemName)) System.out.println(" " + itemName + " already in cart - not added");
			else users.get(username).add(itemName);
		} else {
			ArrayList<String> items = new ArrayList<String>();
			items.add(itemName);
			users.put(username, items);
		}
	}
	
	public static void view(String cmd, String username)
	{
		System.out.println(cmd + " " + username);
		if(users.containsKey(username))
		{
			ArrayList<String> s = users.get(username);
			System.out.println(" " + username + " has ordered:");
			for(String word : s)
			{
				System.out.printf("  %10s   $%4d\n", word, items.get(word));
			}
		}else{
			System.out.println(" No " + username +" found!");
		}
		
	}
	
	public static void checkout(String cmd, String username)
	{
		if(users.containsKey(username))
		{
			ArrayList<String> s = users.get(username);
			int total = 0;
			view(cmd, username);
			for(String word : s)
			{
				total += items.get(word);
			}
			System.out.printf(" %s%s   $%5d\n", username, "'s total is:", total);
		}else{
			System.out.println(" No " + username +" found!");
		}
	}
	
	public static void delete(String cmd, String username, String itemName)
	{
		System.out.println(cmd + " " + username + " " + itemName);
		if(users.containsKey(username))
		{
			ArrayList<String> s = users.get(username);
			if(s.remove(itemName))
			{
				System.out.println(" " + itemName + " has been removed from " + username + "'s cart.");
			}
		}else{
			System.out.println(" No " + username +" found!");
		}
	}
	
	private static void readItems(MySplayTreeMap<String, Integer> items, String fileName)
	{
		Scanner itemFile;
		String word;
		String change;
		Integer price;
		
		try
		{
			itemFile = new Scanner(new BufferedReader(new FileReader(fileName)));
		}
		catch(FileNotFoundException e)
		{
			System.err.println(e);
			return;
		}
		
		while(itemFile.hasNext())
		{
			word = itemFile.next();
			change = itemFile.next();			
			price = Integer.parseInt(change.replaceAll("[^0-9]+",""));
			items.put(word,  price);
		}
		
		itemFile.close();
	}
	
}

