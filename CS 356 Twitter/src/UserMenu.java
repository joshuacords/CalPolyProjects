import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class UserMenu extends JPanel {

	/**
	 * Create the frame.
	 */
	public UserMenu(int userId) {
		_control = UserGroupCont.getInstance();
		_userId = userId;
		_userName = _control.getUser(userId).getUserName();
		_control.attachMenuToUser(userId, this);
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Creates all buttons and fields.
	 */
	private void initialize(){
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 429);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				_control.detachMenuFromUser(_userId);
			}
		});

		frame.setContentPane(new JPanel());
		frame.getContentPane().setLayout(null);

		newsFeedPane = new JTextPane();
		newsFeedPane.setBounds(10, 32, 414, 127);
		newsFeedPane.setText(_control.getUser(_userId).getNewsFeedString());
		newsFeedPane.setEditable(false);
		frame.getContentPane().add(newsFeedPane);

		JLabel lblNewsfeed = new JLabel();
		lblNewsfeed.setBounds(10, 11, 125, 14);
		lblNewsfeed.setText(_userName + "'s Newsfeed");
		frame.getContentPane().add(lblNewsfeed);

		tweetTextField = new JTextField();
		tweetTextField.setBounds(10, 189, 298, 20);
		frame.getContentPane().add(tweetTextField);
		tweetTextField.setColumns(10);

		JLabel lblTweet = new JLabel("Tweet");
		lblTweet.setBounds(10, 170, 125, 14);
		frame.getContentPane().add(lblTweet);

		JButton btnNewButton = new JButton("Post Tweet");
		btnNewButton.setBounds(318, 188, 106, 23);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String messageBody = tweetTextField.getText();
				tweetTextField.setText("");
				_control.getUser(_userId).createMessage(messageBody);
				updateNewsFeed();
			}
		});
		frame.getContentPane().add(btnNewButton);

		subscribedToModel = new DefaultListModel<UserGroupProxy>();
		updateSubscribedTo();
		JList<UserGroupProxy> subscribedToList = new JList<UserGroupProxy>(subscribedToModel);
		subscribedToList.setBounds(10, 241, 178, 140);
		frame.getContentPane().add(subscribedToList);

		JLabel lblCurrentlyFollowing = new JLabel("Currently Following");
		lblCurrentlyFollowing.setBounds(10, 220, 125, 14);
		frame.getContentPane().add(lblCurrentlyFollowing);

		subscribeToTextField = new JTextField();
		subscribeToTextField.setBounds(222, 241, 86, 20);
		frame.getContentPane().add(subscribeToTextField);
		subscribeToTextField.setColumns(10);

		JButton btnFollowUser = new JButton("Follow User");
		btnFollowUser.setBounds(318, 240, 106, 23);
		btnFollowUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int subscribeToId = Integer.parseInt(subscribeToTextField.getText());
				subscribeToTextField.setText("");
				if(subscribeToId != _userId){
					_control.getUser(_userId).subscribeTo(subscribeToId);
					updateSubscribedTo();
				}
			}
		});
		frame.getContentPane().add(btnFollowUser);

		JLabel lblUserId = new JLabel("Enter User Id");
		lblUserId.setBounds(222, 220, 125, 14);
		frame.getContentPane().add(lblUserId);
	}

	public void updateNewsFeed() {
		newsFeedPane.setText(_control.getUser(_userId).getNewsFeedString());
	}

	public void updateSubscribedTo() {
		List<UserGroupProxy> displayProxies = _control.getUser(_userId).getSubscribedTo();
		subscribedToModel.removeAllElements();
		for(UserGroupProxy element : displayProxies){
			subscribedToModel.addElement(element);
		}
	}

	private static final long serialVersionUID = 2L;

	private UserGroupCont _control;
	private int _userId;
	private String _userName;

	private JFrame frame;
	private JTextPane newsFeedPane;
	private DefaultListModel<UserGroupProxy> subscribedToModel;
	private JTextField subscribeToTextField;
	private JTextField tweetTextField;
}
