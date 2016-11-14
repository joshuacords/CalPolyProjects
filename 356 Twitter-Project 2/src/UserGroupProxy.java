
public class UserGroupProxy {
	private int _id;
	private boolean _isUser;
	private int _level;
	private String _name;

	public UserGroupProxy(int id, String name, boolean isUser, int level){
		_isUser = isUser;
		_name = name;
		_id = id;
		_level = level;
	}

	public boolean isUser(){
		return _isUser;
	}

	public int getId(){
		return _id;
	}

	public int getLevel(){
		return _level;
	}

	@Override
	public String toString(){
		StringBuilder display = addBuffer(new StringBuilder(), _level);
		display.append(_name);
		return display.toString();
	}

	private StringBuilder addBuffer(StringBuilder display, int level){
		for(int i = 0; i < level; i++){
			display.append("  ");
		}

		return display;
	}
}
