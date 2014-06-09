package com.knight.client.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

public class guiMain {

	private JFrame frame;
	private final JButton btnGroupChat = new JButton("Group Chat");
	private final JButton btnFriends = new JButton("Friends");
	private final JLabel lblKnightGaming = new JLabel("Knight Gaming");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guiMain window = new guiMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public guiMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		frame.getContentPane().add(btnGroupChat, BorderLayout.WEST);
		
		frame.getContentPane().add(btnFriends, BorderLayout.EAST);
		
		frame.getContentPane().add(lblKnightGaming, BorderLayout.NORTH);
	}

}
