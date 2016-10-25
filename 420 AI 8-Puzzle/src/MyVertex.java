import java.util.LinkedList;
import java.util.List;

public class MyVertex {
	private MyVertex _parent;
	private MyState _state;
	private int _gCost, _hCost, _fCost;
	private Heuristic _heuristic;

	public MyVertex(MyState state, MyVertex parent, int gCost, Heuristic heuristic)
	{
		_state = state;
		_parent = parent;
		_gCost = gCost;
		_heuristic = heuristic;

		_hCost = heuristic.cost(_state);
		_fCost = _gCost + _hCost;
	}

	public boolean isGoal(){
		return _state.isGoal();
	}

	public List<MyVertex> generateSuccessors(){
		List<MyVertex> list = new LinkedList<MyVertex>();

		for(MyState state : _state.generateSuccessors()){
			list.add(new MyVertex(state, this, _gCost + 1, _heuristic));
		}

		return list;
	}

	public String getHash(){
		return _state.getHash();
	}

	@Override
	public String toString()
	{
		return _state.toString();
	}

	public int getGCost() {
		return _gCost;
	}

	public int getFCost() {
		return _fCost;
	}

	public int getHCost() {
		return _hCost;
	}

	public MyVertex getParent(){
		return _parent;
	}

	public MyState getState() {
		return _state;
	}

}
