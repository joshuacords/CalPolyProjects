import java.util.ArrayList;
import java.util.TreeMap;


public class Order 
{
	public String cmd;
	public String username;
	public String itemName;
	TreeMap<String, ArrayList<String>> users;
	TreeMap<String, Integer> items;
	
	Order(TreeMap<String, ArrayList<String>> users,	TreeMap<String, Integer> items)
	{
		cmd = "";
		username = "";
		itemName = "";
		this.users = users;
		this.items = items;
	}
	
	public void exec()
	{
		
		switch(cmd)
		{
		case "add":add();
			break;
		case "view":view();
			break;
		case "checkout":checkout();
			break;
		case "delete":delete();
			break;
		default: System.out.println(cmd + " is an invalid order.");
				
		}
	}
	
	
	
	private void add()
	{
		System.out.println(cmd + " " + username + " " + itemName);
		if(users.containsKey(username))
		{
			users.get(username).add(itemName);
		} else {
			ArrayList<String> items = new ArrayList<String>();
			items.add(itemName);
			users.put(username, items);
		}
	}
	
	private void view()
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
	
	private void checkout()
	{
		if(users.containsKey(username))
		{
			ArrayList<String> s = users.get(username);
			int total = 0;
			view();
			for(String word : s)
			{
				total += items.get(word);
			}
			System.out.printf(" %s%s   $%5d\n", username, "'s total is:", total);
		}else{
			System.out.println(" No " + username +" found!");
		}
	}
	
	private void delete()
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

}
