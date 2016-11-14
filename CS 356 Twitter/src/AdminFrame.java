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
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AdminFrame extends JPanel{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JList<UserGroupProxy> jList;
	private DefaultListModel userGroupList;
	private JLabel userIdLabel;
	private JLabel groupIdLabel;
	private UserGroupCont control;

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
		userIdLabel.setBounds(157, 11, 112, 23);
		frame.getContentPane().add(userIdLabel);

		groupIdLabel = new JLabel("Group Id: ");
		groupIdLabel.setBounds(157, 48, 112, 23);
		frame.getContentPane().add(groupIdLabel);

		userGroupList = new DefaultListModel<UserGroupProxy>();
		setTreeView();
		jList = new JList<UserGroupProxy>(userGroupList);
		jList.setBounds(10, 11, 137, 240);
		jList.setLayoutOrientation(JList.VERTICAL);
		frame.getContentPane().add(jList);

//		JScrollPane pane = new JScrollPane(jList);
//		add(pane, BorderLayout.NORTH);

		ListSelectionModel listSelectionModel = jList.getSelectionModel();
		listSelectionModel.addListSelectionListener(new ListListener());

		JButton addUserButton = new JButton("Add User");
		addUserButton.setBounds(311, 11, 113, 23);
		frame.getContentPane().add(addUserButton);

		JButton addGroupButton = new JButton("Add Group");
		addGroupButton.setBounds(311, 48, 113, 23);
		frame.getContentPane().add(addGroupButton);

		JButton openUserViewButton = new JButton("Open User View");
		openUserViewButton.setBounds(157, 82, 143, 23);
		openUserViewButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			UserGroupProxy proxy = jList.getSelectedValue();
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
		showUserTotalButton.setBounds(157, 116, 143, 23);
		frame.getContentPane().add(showUserTotalButton);

		JButton showGroupTotalButton = new JButton("Show Group Total");
		showGroupTotalButton.setBounds(157, 150, 143, 23);
		frame.getContentPane().add(showGroupTotalButton);

		JButton showMessagesTotalButton = new JButton("Show Messages Total");
		showMessagesTotalButton.setBounds(157, 185, 143, 23);
		frame.getContentPane().add(showMessagesTotalButton);

		JButton showPositivePercentButton = new JButton("Show Positive Percent");
		showPositivePercentButton.setBounds(157, 219, 143, 23);
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

	public void setTreeView() {
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

	        if (lsm.isSelectionEmpty()) {
	        	System.out.println(" <none>");
	        } else {
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
