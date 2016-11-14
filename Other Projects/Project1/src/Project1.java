import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;


public class Project1 
{

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
		
		
		//create trees for users and items
		TreeMap<String, Integer> items = new TreeMap<String, Integer>();
		TreeMap<String, ArrayList<String>> users = new TreeMap<String, ArrayList<String>>();
				
		//populate items tree
		readItems(items,itemsFileName);
		
		//printAll(items);

		//execute orders
		translateOrders(users, items, ordersFileName);

	}
	
	
	private static void translateOrders(TreeMap<String, ArrayList<String>> users, TreeMap<String, Integer> items, String fileName)
	{
		Scanner orderFile;
		Order order = new Order(users, items);
		
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
		String cmd = "", arg1 = "", arg2 = "";
		
		while(orderFile.hasNext())
		{
			if(cmd.equals(""))
			{
				cmd = orderFile.next();				
			}
			if(orderFile.hasNext()) arg1 = orderFile.next();
			if(orderFile.hasNext()) arg2 = orderFile.next();
			
			
			order.cmd = cmd;
			cmd = "";
			order.username = arg1;
			arg1 = "";
			
			//Handle the difference between 2 arg orders and 3 arg orders
			if(arg2.equals("add")||arg2.equals("view")||arg2.equals("checkout")||arg2.equals("delete"))
			{
				cmd = arg2;
				order.itemName = "";
			} else {
				order.itemName = arg2;
			}
			arg2 = "";
			
			order.exec();
		}
		
		orderFile.close();
	}
	
	private static void readItems(TreeMap<String, Integer> items, String fileName)
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
			price = Integer.parseInt(change.replaceAll("[^a-zA-Z0-9]+",""));
			items.put(word,  price);
		}
		
		itemFile.close();
	}
	
	/* Debugging & Testing
	private static void printAll(TreeMap<String, Integer> items)
	{
		System.out.println("----------------------");
		
		for(String word: items.keySet())
		{
			System.out.printf("%10s%15d\n", word, items.get(word));
		}
		System.out.println("----------------------");
	}
	*/
}

