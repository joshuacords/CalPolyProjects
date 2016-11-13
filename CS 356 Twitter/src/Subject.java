import java.util.LinkedList;
import java.util.List;

public abstract class Subject {
	public void addSubscriber(int subscriberId){
		_subscribers.add(subscriberId);
	}

	public void notifySubscribers(){
		for(int userId : _subscribers){
			UserGroupCont.getInstance().getUser(userId).notify(this);
		}
	}

	public void removeSubscriber(int subscriberId){
		_subscribers.remove(subscriberId);
	}

	private List<Integer> _subscribers = new LinkedList<Integer>();
}
