import java.util.List;

public interface IGroup {
	public StringBuilder displayString(StringBuilder display, int level);
	public List<UserGroupProxy> displayProxy(List<UserGroupProxy> display, int level);
}