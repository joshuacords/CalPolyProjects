
public class Message implements Comparable<Message>{

	public Message(int userId, int messageId, String message) {
		setUserId(userId);
		_messageId = messageId;
		setMessage(message);
		_publishTime = System.currentTimeMillis();
	}

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

	public String getMessageBody() {
		return _body;
	}

	public int getMessageId() {
		return _messageId;
	}

	public long getPublishTime(){
		return _publishTime;
	}

	public int getUserId() {
		return _userId;
	}

	public void setMessage(String _message) {
		this._body = _message;
	}

	@Override
	public String toString(){
		return UserGroupCont.getInstance().getUser(_userId).getUserName()
				+ " - " + _body;
	}

	private void setUserId(int _userId) {
		this._userId = _userId;
	}

	private String _body;
	private int _messageId;
	private long _publishTime;
	private int _userId;

}
