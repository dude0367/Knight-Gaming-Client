package com.knight.client;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.Callable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.Position;

import com.knight.client.gui.ChatWindow;
import com.knight.client.gui.GuiFriends;
import com.knight.client.gui.GuiMain;
import com.knight.client.gui.Login;
import com.knight.client.gui.SteamToast;




public class KGMain extends JFrame implements Runnable, ActionListener {

	private static BufferedReader streamIn;
	public static PrintStream streamOut;
	public static boolean Running = true;
	public static Socket clientSocket;
	public static JFrame guiMainMenu;
	public static GuiMain guiMain = new GuiMain();
	public static GuiFriends guiFriends = null;
	public static Thread thread;
	public static String myIP;
	public static JPanel content;
	public static String name;
	public static boolean madeThread = false;

	public static int port = 3214;

	public static JButton friendsListButton;
	public static JButton groupChatButton;

	public static ChatWindow groupChat;
	public static Login login;
	public static Hashtable<String, ChatWindow> chatTable = new Hashtable<String, ChatWindow>();
	public static HashMap<String, ChatWindow> chats = new HashMap<String, ChatWindow>(chatTable);

	public static void main(String[] args) {
		guiMainMenu = guiMain.frmKnightGaming;//new guiMain();
		if(thread == null && !madeThread) {
			madeThread = true;
			thread = new Thread(new KGMain());
			thread.start();
			System.out.println("Created thread");
		}
		//frm.getContentPane().setLayout(new GridLayout());//TODO: MAKE THEM LAYOUTS BE WORKIN
		//frm.setLayout(new GridLayout());
		try {
			String myIP = Inet4Address.getLocalHost().getHostAddress();
			myIP = myIP + "";
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		content = new JPanel();
		/*frm.setSize(400, 400);
		frm.setLocationRelativeTo(null);
		//frm.setVisible(true);
		frm.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		frm.getContentPane().setVisible(true);
		frm.setTitle("Knight Gaming : " + myIP + ":" + port);*/
		login = new Login();
		login.setVisible(true);
		//frm.setContentPane(content);
		//frm.add(content, BorderLayout.WEST);
	}

	public KGMain() {
		System.out.println("New KGMain object made");

	}

	@Override
	public void run() {
		try {
			clientSocket = new Socket(getIP(), port);
			streamIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			streamOut = new PrintStream(clientSocket.getOutputStream(), true);


			while(Running) {
				String line = streamIn.readLine();
				System.out.println("Message from server: " + line);
				if(line != null) {
					if(line.startsWith("Username")){
						//String name = Input.getString("Username: ") + "#/#" + Input.getString("Password: ");//System.getProperty("user.name");
						//if(name != null) streamOut.println(name);
					}
					if(line.startsWith("invalidlogin")) {
						//String name = Input.getString("Username: ") + "#/#" + Input.getString("Password: ");
						//Input.getBoolean("Incorrect Login");
						JOptionPane.showMessageDialog(login, "Invalid Login");
						//if(name != null) streamOut.println(name);
					}
					if(line.startsWith("authenticated")) {
						friendsListButton = new JButton("Friends");
						friendsListButton.setVisible(true);
						friendsListButton.addActionListener(this);
						groupChatButton = new JButton("Group Chat");
						groupChatButton.setVisible(true);
						groupChatButton.addActionListener(this);


						guiMainMenu.setVisible(true);
						login.setVisible(false);

						if(guiFriends == null) guiFriends = new GuiFriends();
						/*frm.getContentPane().add(groupChatButton);
						frm.getContentPane().add(friendsListButton);
						//frm.add(friendsListButton);
						//content.add(friendsListButton);
						frm.revalidate();*/
						System.out.println("Account Details Authenticated, Populating GUI");
					}
					if(line.startsWith("groupchat")) {
						String[] data = line.split("#/#");
						if(groupChat != null) {
							groupChat.addMessage(data[1]);
						}
					}
					if(line.startsWith("chat")) {
						final String[] data = line.split("#/#");
						if(chats.containsKey(data[1])) {
							if(chats.get(data[1]) != null) {
								chats.get(data[1]).addMessage(data[2]);
								chats.get(data[1]).setVisible(true);
							}
						}else {
							SteamToast toast = new SteamToast(data[1] + ": " + data[2], new Callable() {
								public Object call() throws Exception {
									//KGMain.chats.get(data[1])data = new ChatWindow("Chat with " + data[1], data[1]);
									//ChatWindow window = new ChatWindow(data[1], data[1]);
									//window.addMessage(data[2]);
									//KGMain.chats.put(data[1], window);
									return null;
								}
							}, data[1]);
						}
					}
					if(line.startsWith("usernametaken")) {
						JOptionPane.showMessageDialog(login, "Username already taken");
					}
					if(line.startsWith("friendupdate")) {
						if(guiFriends == null) {
							guiFriends = new GuiFriends();
							guiFriends.setVisible(true);
						}
						String[] data = line.split("#/#");
						int pos = -1;
						for(int i = 0; i < guiFriends.listModel.getSize(); i++) {
							if(guiFriends.listModel.get(i).startsWith(data[1])) {
								pos = i;
								break;
							}
						}
						if(pos != -1) {
							guiFriends.listModel.remove(pos);
							guiFriends.listModel.add(pos, data[1] + " [" + data[2] + "]");
						} else {
							guiFriends.listModel.addElement(data[1] + " [" + data[2] + "]");
						}
						guiFriends.listFriends.revalidate();
					}
				}
			}
		}
		catch(Exception e) {
			System.out.println("Could not connect");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			run();
		}
	}

	public static void sendPacket(String data) {
		streamOut.flush();
		streamOut.println(data);
	}

	public static String getIP() {
		BufferedReader br = null;
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader("\\" + "\\fileserver1\\prhs-students$\\2016\\16nknight\\ip.txt"));
			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
				return sCurrentLine;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return "127.0.0.1";
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println("Action performed");
		if(e.getSource() == friendsListButton) {
			System.out.println("Opening friends");
		}
		if(e.getSource() == groupChatButton) {
			System.out.println("Opening group chat");
			//if(groupChat == null) {
			groupChat = new ChatWindow("Group Chat");
			//}
		}
	}
}