package com.knight.client;
import java.awt.GridLayout;
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
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.knight.client.gui.ChatWindow;
import com.knight.client.gui.ChatWindowOld;
import com.knight.client.gui.SteamToast;




public class KGMain extends JFrame implements Runnable, ActionListener {

	private static BufferedReader streamIn;
	private static PrintStream streamOut;
	public static boolean Running = true;
	public static Socket clientSocket;
	public static JFrame frm;
	public static Thread thread;
	public static String myIP;
	public static JPanel content;
	public static boolean madeThread = false;

	public static int port = 3214;

	public static JButton friendsListButton;
	public static JButton groupChatButton;

	public static ChatWindow groupChat;
	public static Hashtable<String, ChatWindow> chatTable = new Hashtable<String, ChatWindow>();
	public static HashMap<String, ChatWindow> chats = new HashMap(chatTable);

	public static void main(String[] args) {
		frm = new KGMain();
		//frm.getContentPane().setLayout(new GridLayout());//TODO: MAKE THEM LAYOUTS BE WORKIN
		frm.setLayout(new GridLayout());
		try {
			String myIP = Inet4Address.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		content = new JPanel();
		frm.setSize(400, 400);
		frm.setLocationRelativeTo(null);
		frm.setVisible(true);
		frm.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		frm.getContentPane().setVisible(true);
		frm.setTitle("Knight Gaming : " + myIP + ":" + port);
		//frm.setContentPane(content);
		//frm.add(content, BorderLayout.WEST);
	}

	public KGMain() {
		System.out.println("New KGMain object made");
		if(thread == null && !madeThread) {
			madeThread = true;
			thread = new Thread(new KGMain());
			thread.start();
			System.out.println("Created thread");
		}
	}

	@Override
	public void run() {
		try {
			clientSocket = new Socket(getIP(), port);
			streamIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			streamOut = new PrintStream(clientSocket.getOutputStream(), true);


			while(Running) {
				String line = streamIn.readLine();
				System.out.println(line);
				if(line != null) {
					if(line.startsWith("Username")){
						String name = Input.getString("Username: ") + "#/#" + Input.getString("Password: ");//System.getProperty("user.name");
						if(name != null) streamOut.println(name);
					}
					if(line.startsWith("invalidlogin")) {
						String name = Input.getString("Username: ") + "#/#" + Input.getString("Password: ");
						if(name != null) streamOut.println(name);
					}
					if(line.startsWith("authenticated")) {
						friendsListButton = new JButton("Friends");
						friendsListButton.setVisible(true);
						friendsListButton.addActionListener(this);
						groupChatButton = new JButton("Group Chat");
						groupChatButton.setVisible(true);
						groupChatButton.addActionListener(this);


						frm.getContentPane().add(groupChatButton);
						frm.getContentPane().add(friendsListButton);
						//frm.add(friendsListButton);
						//content.add(friendsListButton);
						frm.revalidate();
						System.out.println("Account Details Authenticated, Populating GUI");
					}
					if(line.startsWith("groupchat")) {
						String[] data = line.split("#/#");
						if(groupChat != null) {
							groupChat.addMessage(data[1]);
						}
					}
					if(line.startsWith("chat")) {
						String[] data = line.split("#/#");
						if(chats.containsKey(data[1])) {
							if(chats.get(data[1]) != null) {
								chats.get(data[1]).addMessage(data[2]);
							} else {
								SteamToast toast = new SteamToast(data[1] + ": " + data[2], new Callable() {
									public Object call() throws Exception {
										//KGMain.chats.get(data[1])data = new ChatWindow("Chat with " + data[1], data[1]);
										return null;
									}
								}, data[1]);
							}
						}
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