package com.knight.client.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import com.knight.client.Input;
import com.knight.client.KGMain;

public class GuiFriends extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JScrollPane scrollPane = new JScrollPane();
	private final JButton btnAddFriend = new JButton("Add Friend");
	public DefaultListModel<String> listModel = new DefaultListModel<String>();
	public final JList<String> listFriends = new JList<String>(listModel);
	//public ArrayList<ChatWindow> chats = new ArrayList<ChatWindow>();

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiFriends frame = new GuiFriends();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public GuiFriends() {
		initGUI();
		setVisible(true);
	}
	private void initGUI() {
		setTitle("Friends List");
		setBounds(100, 100, 218, 384);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		contentPane.add(scrollPane, BorderLayout.CENTER);
		listFriends.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listFriends.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList<?> list = (JList<?>)evt.getSource();
				if (evt.getClickCount() == 2) {
					//int index = list.locationToIndex(evt.getPoint());//TODO: HANDLE DOUBLE CLICK HERE
					String name = getSelectedName();
					System.out.println("Friendslist selected: " + name);
					if(KGMain.chats.containsKey(name)) {
						KGMain.chats.get(name).setVisible(true);
					} else {
						ChatWindow window = new ChatWindow("Chat with " + name, name);
						KGMain.chats.put(name, window);
					}
					
				} else if (evt.getClickCount() == 3) {   // Triple-click
					//int index = list.locationToIndex(evt.getPoint());

				}
			}
		});

		scrollPane.setViewportView(listFriends);
		btnAddFriend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String newFriend = Input.getString("What's their name?");
				if(newFriend != "")	KGMain.sendPacket("addfriend#/#" + newFriend);
			}
		});

		contentPane.add(btnAddFriend, BorderLayout.SOUTH);
	}
	
	String getSelectedName() {
		return listFriends.getSelectedValue().toString().split(" ")[0];
	}
	
	/*String getNameFromIndex(int index) {
		String out = "";
		out = listFriends.get
		return out;
	}*/

}
