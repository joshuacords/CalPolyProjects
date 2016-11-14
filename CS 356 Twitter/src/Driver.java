import java.awt.EventQueue;

public class Driver {

	public static void main(String[] args) {

		createSampleData();

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
	}

	private static void createSampleData(){
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

		allison.subscribeTo(user1Id);

		user1.createMessage("First message: great!");
		user1.createMessage("2nd Message: good");
		user1.createMessage("3rd message: ok");
	}

}
