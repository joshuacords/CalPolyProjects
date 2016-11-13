import java.util.List;

public interface MyState {
	public boolean isGoal();
	public String getHash();
	@Override
	public String toString();
	public Object getBaseModel();
	public List<MyState> generateSuccessors();
}
