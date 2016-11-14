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

		int user2Id = control.addUser(userGroup1, "Austin");
		int user3Id = control.addUser(userGroup1, "Allison");
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

		user1.addSubscriber(user2Id);



		//control.printTreeView();

		//List<String> displayList = control.getTreeView();
		List<UserGroupProxy> displayProxies = control.getTreeProxies();

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					AdminFrame window = new AdminFrame();
					//window.setTreeView(displayList);
					window.setTreeView(displayProxies);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
