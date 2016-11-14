import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class UserGroupCont {

	public int addUser(int groupId, String userName){
		User user = new User(_lastUserId, groupId, userName);
		_userList.put(_lastUserId, user);
		_groupList.get(groupId).addSubgroup(user);
		return _lastUserId++;
	}

	public int addGroup(int groupId, String groupName){
		Group group = new Group(_lastGroupId, groupName);
		_groupList.put(_lastGroupId, group);
		_groupList.get(groupId).addSubgroup(group);
		return _lastGroupId++;
	}

	public void attachMenuToUser(int userId, UserMenu userMenu){
		_userList.get(userId).attachMenu(userMenu);
	}

	public void detachMenuFromUser(int userId) {
		_userList.get(userId).detachMenu();
	}

	public void printTreeView(){
		System.out.println(_root.displayString(new StringBuilder(), 0));
	}

	public List<String> getTreeView(){

		StringBuilder sb = _root.displayString(new StringBuilder(), 0);
		String[] displayArray = sb.toString().split("\n");
		List<String> displayList = new LinkedList<String>();
		for(String line : displayArray){
			displayList.add(line);
		}

		return displayList;
	}

	public List<UserGroupProxy> getTreeProxies(){
		return _root.displayProxy(new LinkedList<UserGroupProxy>(), 0);
	}

	public User getUser(int userId){
		return _userList.get(userId);
	}

	public Group getGroup(int groupId){
		return _groupList.get(groupId);
	}

	public TotalsVisitor visitGroup(int groupId){
		TotalsVisitor visitor = new TotalsVisitor();
		Group group = _groupList.get(groupId);
		if(group != null){
			group.accept(visitor);
		}

		return visitor;
	}

	public TotalsVisitor visitUser(int userId){
		TotalsVisitor visitor = new TotalsVisitor();
		User user = _userList.get(userId);
		if(user != null){
			user.accept(visitor);
		}

		return visitor;
	}

	public static UserGroupCont getInstance(){
		if(_instance == null){
			_instance = new UserGroupCont();
		}
		return _instance;
	}

	private UserGroupCont(){
		_lastUserId = 0;
		_lastGroupId = 0;
		_userList = new HashMap<Integer,User>();
		_groupList = new HashMap<Integer,Group>();
		_root = new Group(_lastGroupId++, "Root");
		_groupList.put(_lastUserId, _root);
	}

	private Group _root;
	private HashMap<Integer,User> _userList;
	private HashMap<Integer,Group> _groupList;
	private int _lastUserId;
	private int _lastGroupId;
	private static UserGroupCont _instance;

}
