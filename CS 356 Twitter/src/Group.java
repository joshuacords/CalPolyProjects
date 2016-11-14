import java.util.LinkedList;
import java.util.List;

public class Group implements IGroup {

	public Group(int groupId, String groupName){
		_groupId = groupId;
		_groupName = groupName;
		_subGroups = new LinkedList<IGroup>();
	}
	@Override
	public StringBuilder displayString(StringBuilder display, int level) {

		display.append(_groupName + "\n");

		if(!_subGroups.isEmpty()){
			level++;
			for(IGroup group : _subGroups){

				display = addBuffer(display, level);
				group.displayString(display, level);

			}
			level--;
		}

		return display;
	}

	@Override
	public List<UserGroupProxy> displayProxy(List<UserGroupProxy> display,
			int level) {

		display.add(new UserGroupProxy(_groupId, _groupName, false, level));

		if(!_subGroups.isEmpty()){
			level++;
			for(IGroup group : _subGroups){
				group.displayProxy(display, level);
			}
			level--;
		}

		return display;
	}

	private StringBuilder addBuffer(StringBuilder display, int level){
		for(int i = 0; i < level; i++){
			display.append("  ");
		}

		return display;
	}

	public String getGroupName(){
		return _groupName;
	}

	public void addSubgroup(IGroup group){
		_subGroups.add(group);
	}

	public int getGroupId(){
		return _groupId;
	}


	private int _groupId;
	private String _groupName;
	private List<IGroup> _subGroups;

}
