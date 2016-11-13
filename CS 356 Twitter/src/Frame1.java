import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

public class Frame1 {

	private JFrame frame;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				try {
//					Frame1 window = new Frame1();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public Frame1() {
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

		JLabel userIdLabel = new JLabel("User Id: ");
		userIdLabel.setBounds(157, 11, 112, 23);
		frame.getContentPane().add(userIdLabel);

		JButton btnNewButton = new JButton("Show Message");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//JOptionPane.showMessageDialog(label, "Hello");
				userIdLabel.setText("Hello from Label");
			}
		});
		btnNewButton.setBounds(321, 116, 121, 23);
		frame.getContentPane().add(btnNewButton);

		JList list_1 = new JList();
		list_1.setModel(new AbstractListModel() {
			String[] values = new String[] {"Already Loaded", "Values"};
			@Override
			public int getSize() {
				return values.length;
			}
			@Override
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list_1.setBounds(10, 11, 137, 240);
		frame.getContentPane().add(list_1);

		DefaultListModel DLM = new DefaultListModel();
		JButton btnLoadList = new JButton("Load List");
		btnLoadList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DLM.addElement("One");
				DLM.addElement("Two");
				DLM.addElement("Three");
				list_1.setModel(DLM);
			}
		});
		btnLoadList.setBounds(321, 82, 89, 23);
		frame.getContentPane().add(btnLoadList);

		JLabel groupIdLabel = new JLabel("Group Id: ");
		groupIdLabel.setBounds(157, 48, 112, 23);
		frame.getContentPane().add(groupIdLabel);

		JButton addUserButton = new JButton("Add User");
		addUserButton.setBounds(311, 11, 89, 23);
		frame.getContentPane().add(addUserButton);

		JButton addGroupButton = new JButton("Add Group");
		addGroupButton.setBounds(311, 48, 89, 23);
		frame.getContentPane().add(addGroupButton);

		JButton openUserViewButton = new JButton("Open User View");
		openUserViewButton.setBounds(157, 82, 143, 23);
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


	}
}
