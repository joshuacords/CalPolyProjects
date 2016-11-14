import java.util.LinkedList;
import java.util.List;

public class User extends Subject implements ISubscriber, IGroup, IVisitable {
	private int _userId;
	private int _groupId;
	private int _messageId;
	private String _userName;
	private LinkedList<Message> _userMessages;
	private LinkedList<Message> _newsFeed;
	private List<Integer> _subscribedTo;
	private UserMenu _userMenu;
	private UserGroupCont _control;

	public User(int userId, int groupId, String userName) {
		_control = UserGroupCont.getInstance();
		_userId = userId;
		setGroupId(groupId);
		setUserName(userName);
		_messageId = 0;

		_userMessages = new LinkedList<Message>();
		_newsFeed = new LinkedList<Message>();
		_subscribedTo = new LinkedList<Integer>();
	}

	public void createMessage(String messageBody) {
		Message message = new Message(_userId, _messageId++, messageBody);
		_userMessages.addFirst(message);
		_newsFeed.addFirst(message);
		notifySubscribers();
	}

	public void subscribeTo(int subjectId) {
		User user = _control.getUser(subjectId);
		if(user != null){
			user.addSubscriber(_userId);
			_subscribedTo.add(subjectId);
		}
	}

	public LinkedList<Message> getAllMessages() {
		return _userMessages;
	}

	public List<Message> getNewsFeedList() {
		return _newsFeed;
	}

	public String getNewsFeedString() {
		StringBuilder sb = new StringBuilder();
		for (Message message : _newsFeed) {
			sb.append(message + "\n");
		}
		return sb.toString();
	}

	public int getGroupId() {
		return _groupId;
	}

	public int getUserId() {
		return _userId;
	}

	public void setGroupId(int _groupId) {
		this._groupId = _groupId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String _userName) {
		this._userName = _userName;
	}

	// not sure I like this approach
	public Message getLastMessage() {
		return null;
	}

	@Override
	public void notify(Subject subject) {
		if (subject.getClass() == User.class) {

			LinkedList<Message> messages = ((User) subject).getAllMessages();

			if (!messages.isEmpty()) {

				if (!_newsFeed.isEmpty()) {
					int index = 0;

					// find how many new messages exist
					while (messages.size() > index &&
							(messages.get(index).compareTo(_newsFeed.getFirst()) > 0)) {
						index++;
					}

					// remove index that failed
					index--;

					// add them to _newsFeed
					for (; index >= 0; index--) {
						_newsFeed.addFirst(messages.get(index));
					}
				} else {
					for (Message message : messages) {
						_newsFeed.add(message);
					}
				}

				if (_userMenu != null) {
					_userMenu.updateNewsFeed();
				}

			}
		}

		// System.out.println("Notify for " + _userName + " triggered:");
		// for(Message message : _newsFeed){
		// System.out.println(message);
		// }
	}

	@Override
	public StringBuilder displayString(StringBuilder display, int level) {
		display.append(_userName + "\n");
		return display;
	}

	@Override
	public List<UserGroupProxy> displayProxy(List<UserGroupProxy> display,
			int level) {
		display.add(new UserGroupProxy(_userId, _userName, true, level));
		return display;
	}

	public void attachMenu(UserMenu userMenu) {
		_userMenu = userMenu;
	}

	public void detachMenu() {
		_userMenu = null;
	}

	public List<UserGroupProxy> getSubscribedTo() {
		List<UserGroupProxy> subscribedTo = new LinkedList<UserGroupProxy>();
		for (int userId : _subscribedTo) {
			subscribedTo.add(new UserGroupProxy(userId,
					_control.getUser(userId).getUserName(), true, 0));
		}
		return subscribedTo;
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
}
