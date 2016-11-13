import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class AStar{

	private PriorityQueue<MyVertex> _frontier;
	private HashMap<String, MyVertex> _exploredStates;
	private int _generatedNodes = 0;

	public List<MyState> runAStar(MyState initialState, Heuristic heuristic){

		//set initial vars
		MyVertex vertex = new MyVertex(initialState, null, 0, heuristic);
		_generatedNodes++;

		if(initialState.isGoal()){
			return returnPath(vertex);
		}

		_frontier = new PriorityQueue<MyVertex>(15, new StateComparator());
		_exploredStates = new HashMap<String, MyVertex>();
		_frontier.add(vertex);


		while(!_frontier.isEmpty()){
			//choose best node from frontier
			vertex = _frontier.poll();

			_exploredStates.put(vertex.getHash(), vertex);

			for(MyVertex successor : vertex.generateSuccessors()){
				_generatedNodes++;

				if(successor.isGoal()){
					return returnPath(successor);
				}

				if(!_exploredStates.containsKey(successor.getHash())){
					_frontier.add(successor);
				} else if(_exploredStates.get(successor.getHash()).getGCost() >= successor.getGCost()){
					_frontier.add(successor);
				}
			}
		}

		return null;
	}

	private List<MyState> returnPath(MyVertex vertex){
		List<MyState> solutionPath = new ArrayList<MyState>();

		solutionPath.add(vertex.getState());

		while(vertex.getParent() != null){
			vertex = vertex.getParent();
			solutionPath.add(vertex.getState());
		}

		Collections.reverse(solutionPath);
		return solutionPath;
	}

	public int getGeneratedNodes() {
		return _generatedNodes;
	}

	public void resetGeneratedNodes() {
		_generatedNodes = 0;
	}
}

