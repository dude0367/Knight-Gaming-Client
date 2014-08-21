package com.knight.client.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JButton;

import com.knight.client.KGMain;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChatWindow extends JFrame {

	private JPanel contentPane;
	private final JTextArea chatArea = new JTextArea();
	private final JPanel inputPanel = new JPanel();
	private final JTextField input = new JTextField();
	private final JButton buttonSend = new JButton("Send");
	
	public String friend;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatWindow frame = new ChatWindow();
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
	public ChatWindow(String title, String friend) {
		this.friend = friend;
		setTitle(title);
		input.setColumns(10);
		initGUI();
	}
	
	/**
	 * @wbp.parser.constructor
	 */

	public ChatWindow(String title) {
		input.setColumns(10);
		setTitle(title);
		initGUI();
	}
	
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{432, 0};
		gbl_contentPane.rowHeights = new int[]{22, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		GridBagConstraints gbc_chatArea = new GridBagConstraints();
		gbc_chatArea.insets = new Insets(0, 0, 5, 0);
		gbc_chatArea.fill = GridBagConstraints.BOTH;
		gbc_chatArea.gridx = 0;
		gbc_chatArea.gridy = 0;
		contentPane.add(chatArea, gbc_chatArea);
		
		GridBagConstraints gbc_inputPanel = new GridBagConstraints();
		gbc_inputPanel.anchor = GridBagConstraints.SOUTH;
		gbc_inputPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputPanel.gridx = 0;
		gbc_inputPanel.gridy = 1;
		contentPane.add(inputPanel, gbc_inputPanel);
		GridBagLayout gbl_inputPanel = new GridBagLayout();
		gbl_inputPanel.columnWidths = new int[]{0, 0};
		gbl_inputPanel.rowHeights = new int[]{0, 0, 0};
		gbl_inputPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_inputPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		inputPanel.setLayout(gbl_inputPanel);
		
		GridBagConstraints gbc_input = new GridBagConstraints();
		gbc_input.fill = GridBagConstraints.HORIZONTAL;
		gbc_input.insets = new Insets(0, 0, 5, 0);
		gbc_input.gridx = 0;
		gbc_input.gridy = 0;
		inputPanel.add(input, gbc_input);
		
		GridBagConstraints gbc_buttonSend = new GridBagConstraints();
		gbc_buttonSend.anchor = GridBagConstraints.NORTH;
		gbc_buttonSend.gridx = 0;
		gbc_buttonSend.gridy = 1;
		buttonSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(friend == null) KGMain.sendPacket("groupchat#/#" + input.getText());
				else KGMain.sendPacket("chat#/#" + friend + "#/#" + input.getText());
				input.setText("");
			}
		});
		inputPanel.add(buttonSend, gbc_buttonSend);
		this.setVisible(true);
	}
	
	public void addMessage(String message) {
		String current = chatArea.getText();
		chatArea.setText(current + "\n" + message);
	}

}
