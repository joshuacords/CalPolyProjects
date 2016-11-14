import java.awt.EventQueue;
import java.util.List;

public class Driver {

	public static void main(String[] args) {

		UserGroupCont control = UserGroupCont.getInstance();
		int rootId = 0;
		int user1Id = control.addUser(rootId, "Josh");
		int userGroup1 = control.addGroup(0, "Group 1");
		int userGroup2 = control.addGroup(userGroup1, "Group 2");
		User user1 = control.getUser(user1Id);

		int austinId = control.addUser(userGroup1, "Austin");
		int user3Id = control.addUser(userGroup1, "Allison");
		User allison = control.getUser(user3Id);

		int user4Id = control.addUser(userGroup2, "Loni");
		int user5Id = control.addUser(rootId, "Lisa");

		for(int i = 1; i < 8; i++)
		{
			//control.addUser(rootId, "User" + i);
		}
		//User user2 = control.getUser(user2Id);

		user1.createMessage("First message");
		user1.createMessage("2nd Message");
		user1.createMessage("3rd message");

		//user1.addSubscriber(user3Id);
		allison.subscribeTo(user1Id);

		user1.createMessage("4th message");

		//control.printTreeView();

		//List<String> displayList = control.getTreeView();
		List<UserGroupProxy> displayProxies = control.getTreeProxies();

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					AdminFrame window = new AdminFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

//		EventQueue.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				try {
//					UserMenu userMenu = new UserMenu(1);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}

}
