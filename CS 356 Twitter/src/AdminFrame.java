import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AdminFrame extends JPanel{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JList<UserGroupProxy> treeView;
	private DefaultListModel userGroupList;
	private JLabel userIdLabel;
	private JLabel groupIdLabel;
	private UserGroupCont control;
	private JTextField newUserTextField;
	private JTextField newGroupTextField;

	/**
	 * Create the application.
	 */
	public AdminFrame() {
		control = UserGroupCont.getInstance();
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		userIdLabel = new JLabel("User Id: ");
		userIdLabel.setBounds(141, 11, 112, 23);
		frame.getContentPane().add(userIdLabel);

		groupIdLabel = new JLabel("Group Id: ");
		groupIdLabel.setBounds(141, 48, 112, 23);
		frame.getContentPane().add(groupIdLabel);

		userGroupList = new DefaultListModel<UserGroupProxy>();
		updateTreeView();
		ListCellRenderer renderer = new MyCellRenderer();

		treeView = new JList<UserGroupProxy>(userGroupList);
		treeView.setBounds(10, 11, 121, 240);
		treeView.setLayoutOrientation(JList.VERTICAL);
		treeView.setCellRenderer(renderer);
		frame.getContentPane().add(treeView);

//		JScrollPane pane = new JScrollPane(jList);
//		add(pane, BorderLayout.NORTH);

		ListSelectionModel listSelectionModel = treeView.getSelectionModel();
		listSelectionModel.addListSelectionListener(new ListListener());

		newUserTextField = new JTextField();
		newUserTextField.setBounds(319, 83, 105, 20);
		frame.getContentPane().add(newUserTextField);
		newUserTextField.setColumns(10);

		newGroupTextField = new JTextField();
		newGroupTextField.setColumns(10);
		newGroupTextField.setBounds(319, 151, 105, 20);
		frame.getContentPane().add(newGroupTextField);

		JButton addUserButton = new JButton("Add User");
		addUserButton.setBounds(319, 117, 105, 23);
		addUserButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String userName = newUserTextField.getText();
				UserGroupProxy proxy = treeView.getSelectedValue();
				int groupId = 0;

				if(proxy != null){
					if(proxy.isUser()){
						groupId = control.getUser(proxy.getId()).getGroupId();
					} else {
						groupId = proxy.getId();
					}
				}

				newUserTextField.setText("");
				control.addUser(groupId, userName);
				updateTreeView();
			}
		});
		frame.getContentPane().add(addUserButton);

		JButton addGroupButton = new JButton("Add Group");
		addGroupButton.setBounds(319, 185, 105, 23);
		addGroupButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String groupName = newGroupTextField.getText();
				UserGroupProxy proxy = treeView.getSelectedValue();
				int groupId = 0;

				if(proxy != null){
					if(proxy.isUser()){
						groupId = control.getUser(proxy.getId()).getGroupId();
					} else {
						groupId = proxy.getId();
					}
				}

				newGroupTextField.setText("");
				control.addGroup(groupId, groupName);
				updateTreeView();
			}
		});
		frame.getContentPane().add(addGroupButton);

		JButton openUserViewButton = new JButton("Open User View");
		openUserViewButton.setBounds(141, 82, 168, 23);
		openUserViewButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			UserGroupProxy proxy = treeView.getSelectedValue();
			if(proxy.isUser()){
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						try {
							UserMenu userMenu = new UserMenu(proxy.getId());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		}
		});
		frame.getContentPane().add(openUserViewButton);

		JButton showUserTotalButton = new JButton("Show User Total");
		showUserTotalButton.setBounds(141, 116, 168, 23);
		frame.getContentPane().add(showUserTotalButton);

		JButton showGroupTotalButton = new JButton("Show Group Total");
		showGroupTotalButton.setBounds(141, 150, 168, 23);
		frame.getContentPane().add(showGroupTotalButton);

		JButton showMessagesTotalButton = new JButton("Show Messages Total");
		showMessagesTotalButton.setBounds(141, 185, 168, 23);
		frame.getContentPane().add(showMessagesTotalButton);

		JButton showPositivePercentButton = new JButton("Show Positive Percent");
		showPositivePercentButton.setBounds(141, 219, 168, 23);
		frame.getContentPane().add(showPositivePercentButton);



//		JButton btnLoadList = new JButton("Load List");
//		btnLoadList.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				DLM.addElement("4");
//				DLM.addElement("5");
//				DLM.addElement("6");
//			}
//		});
//		btnLoadList.setBounds(321, 82, 89, 23);
//		frame.getContentPane().add(btnLoadList);

//		JButton btnNewButton = new JButton("Show Message");
//		btnNewButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				int selectedIndex = jList.getSelectedIndex();
//				UserGroupProxy proxy = (UserGroupProxy) DLM.get(selectedIndex);
//				if(proxy.isUser()){
//					userIdLabel.setText("User Id: " + proxy.getId());
//					groupIdLabel.setText("Group Id: -");
//				} else {
//					userIdLabel.setText("User Id: -");
//					groupIdLabel.setText("Group Id: " + proxy.getId());
//				}
//			}
//		});
//		btnNewButton.setBounds(321, 116, 121, 23);
//		frame.getContentPane().add(btnNewButton);

	}

	public void updateTreeView() {
		List<UserGroupProxy> displayProxies = control.getTreeProxies();
		userGroupList.removeAllElements();
		for(UserGroupProxy element : displayProxies){
			userGroupList.addElement(element);
		}
	}

	class ListListener implements ListSelectionListener {
	    @Override
		public void valueChanged(ListSelectionEvent e) {
	        ListSelectionModel lsm = (ListSelectionModel)e.getSource();

	        if (!lsm.isSelectionEmpty()){
	            int selectedIndex = e.getFirstIndex();
				UserGroupProxy proxy = (UserGroupProxy) userGroupList.get(selectedIndex);
				if(proxy.isUser()){
					userIdLabel.setText("User Id: " + proxy.getId());
					groupIdLabel.setText("Group Id: -");
				} else {
					userIdLabel.setText("User Id: -");
					groupIdLabel.setText("Group Id: " + proxy.getId());
				}
	        }
	    }
	}
}
