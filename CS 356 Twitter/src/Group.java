import java.util.LinkedList;
import java.util.List;

public class Group implements IGroup, IVisitable {

	public Group(int groupId, String groupName){
		_groupId = groupId;
		_groupName = groupName;
		_subGroups = new LinkedList<IGroup>();
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}

	public void addSubgroup(IGroup group){
		_subGroups.add(group);
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

	public int getGroupId(){
		return _groupId;
	}

	public String getGroupName(){
		return _groupName;
	}

	public List<IGroup> getSubGroups(){
		return _subGroups;
	}

	private int _groupId;
	private String _groupName;
	private List<IGroup> _subGroups;

}
