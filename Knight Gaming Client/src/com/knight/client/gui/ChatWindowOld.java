package com.knight.client.gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.knight.client.KGMain;




public class ChatWindowOld extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public TextArea chatArea;
	public TextArea input;
	public JButton send;

	public String friend;

	public ChatWindowOld(String title, String friend) {
		this.friend = friend;
		populate(title);
	}

	public ChatWindowOld(String title) {
		populate(title);
	}
	
	public void populate(String title) {
		this.setTitle(title);//dead
		this.setSize(300, 400);
		this.setVisible(true);
		input = new TextArea();
		input.setPreferredSize(new Dimension(375, 50));
		chatArea = new TextArea();
		send = new JButton("Send");
		this.getContentPane().setLayout(new BorderLayout(0, 10));//TODO: SEND BUTTON NOT APPEARING
		this.getContentPane().add(chatArea, BorderLayout.PAGE_START);
		this.getContentPane().add(input, BorderLayout.LINE_START);
		this.getContentPane().add(send, BorderLayout.LINE_END);
		this.revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == send) {
			if(friend == null) KGMain.sendPacket("groupchat#/#" + input.getText());
			else KGMain.sendPacket("chat#/#" + friend + "#/#" + input.getText());
			input.setText("");
		} else {
			//input.setText("");
		}
	}
	
	public void addMessage(String message) {
		String current = chatArea.getText();
		chatArea.setText(current + "\n" + message);
	}

}
