import java.util.LinkedList;
import java.util.List;

public class TotalsVisitor implements IVisitor {

	public TotalsVisitor(){
		_positiveWords = new LinkedList<String>();
		_positiveWords.add("good");
		_positiveWords.add("excellent");
		_positiveWords.add("great");
		_positiveWords.add("exciting");
		_positiveWords.add("thrilled");
	}

	public int getGroupTotal(){
		return _groupTotal;
	}

	public int getMessageTotal(){
		return _messageTotal;
	}

	public float getPositiveMessagePercent(){
		return (float)_positiveMessageTotal/(float)_messageTotal*100.0f;
	}

	public int getUserTotal(){
		return _userTotal;
	}

	public boolean isPositive(String string){
		for(String word : _positiveWords){
			if(string.contains(word)){
				return true;
			}
		}
		return false;
	}

	@Override
	public void visit(Group group) {
		_userTotal = 0;
		_groupTotal = 0;
		for(IGroup iGroup : group.getSubGroups()){
			if(iGroup.getClass() == User.class){
				_userTotal++;
			}else{
				_groupTotal++;
			}
		}
	}

	@Override
	public void visit(User user) {
		_messageTotal = 0;
		_positiveMessageTotal = 0;
		List<Message> userMessages = user.getAllMessages();
		if(userMessages != null){
			_messageTotal += userMessages.size();
			for(Message message : userMessages){
				if(isPositive(message.getMessageBody()))
				{
					_positiveMessageTotal++;
				}
			}
		}
	}

	private int _groupTotal = 0;
	private int _messageTotal = 0;
	private int _positiveMessageTotal = 0;
	private List<String> _positiveWords;
	private int _userTotal = 0;

}
