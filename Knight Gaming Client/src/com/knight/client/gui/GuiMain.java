package com.knight.client.gui;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.knight.client.KGMain;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GuiMain {

	public JFrame frmKnightGaming;
	private final JButton btnGroupChat = new JButton("Group Chat");
	private final JButton btnFriends = new JButton("Friends");
	private final JPanel panelSocial = new JPanel();
	private final JPanel panelGames = new JPanel();
	private final JButton btnList = new JButton("Games");
	private final JPanel panelActions = new JPanel();
	private final JButton btnExit = new JButton("Exit");

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiMain window = new GuiMain();
					window.frmKnightGaming.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public GuiMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmKnightGaming = new JFrame();
		frmKnightGaming.setTitle("Knight Gaming");
		frmKnightGaming.setBounds(100, 100, 353, 150);
		frmKnightGaming.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmKnightGaming.getContentPane().setLayout(new BorderLayout(0, 0));
		
		frmKnightGaming.getContentPane().add(panelSocial, BorderLayout.SOUTH);
		btnGroupChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Opening group chat");
				KGMain.groupChat = new ChatWindow("Group Chat");
			}
		});
		panelSocial.add(btnGroupChat);
		btnFriends.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(KGMain.guiFriends == null) {
					KGMain.guiFriends = new GuiFriends();
				} else {
					KGMain.guiFriends.setVisible(true);
				}
			}
		});
		panelSocial.add(btnFriends);
		
		frmKnightGaming.getContentPane().add(panelGames, BorderLayout.CENTER);
		
		panelGames.add(btnList);
		frmKnightGaming.getContentPane().add(panelActions, BorderLayout.EAST);
		
		panelActions.add(btnExit);
	}

}
