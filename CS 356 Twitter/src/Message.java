
public class Message implements Comparable<Message>{

	public Message(int userId, int messageId, String message) {
		setUserId(userId);
		_messageId = messageId;
		setMessage(message);
		_publishTime = System.currentTimeMillis();
	}

	public String getMessageBody() {
		return _body;
	}

	public int getMessageId() {
		return _messageId;
	}

	public void setMessage(String _message) {
		this._body = _message;
	}

	public int getUserId() {
		return _userId;
	}

	public long getPublishTime(){
		return _publishTime;
	}

	@Override
	public String toString(){
		return UserGroupCont.getInstance().getUser(_userId).getUserName()
				+ " - " + _body;
	}

	private void setUserId(int _userId) {
		this._userId = _userId;
	}

	private int _userId;
	private int _messageId;
	private String _body;
	private long _publishTime;

	@Override
	public int compareTo(Message message) {

		if(_publishTime < message.getPublishTime()){
			return -1;
		}else if(_publishTime > message.getPublishTime()){
			return 1;
		}else{
			return 0;
		}
	}




}
