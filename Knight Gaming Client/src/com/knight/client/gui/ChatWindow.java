package com.knight.client.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.knight.client.KGMain;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;

public class ChatWindow extends JFrame {

	private JPanel contentPane;
	private final JTextArea chatArea = new JTextArea();
	private final JPanel inputPanel = new JPanel();
	private final JTextField input = new JTextField();
	private final JButton buttonSend = new JButton("Send");
	public String friend;
	private final JScrollPane scrollPane = new JScrollPane();

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(chatArea);
		chatArea.setWrapStyleWord(true);
		chatArea.setLineWrap(true);
		chatArea.setEditable(false);
		this.setVisible(true);
				contentPane.add(inputPanel, BorderLayout.SOUTH);
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
						input.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								sendMessage();
							}
						});
						
								GridBagConstraints gbc_buttonSend = new GridBagConstraints();
								gbc_buttonSend.anchor = GridBagConstraints.NORTH;
								gbc_buttonSend.gridx = 0;
								gbc_buttonSend.gridy = 1;
								buttonSend.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent arg0) {
										sendMessage();
									}
								});
								inputPanel.add(buttonSend, gbc_buttonSend);
	}

	public void addMessage(String message) {
		String current = chatArea.getText();
		if(friend == null) chatArea.setText(current + message + "\n");
		else chatArea.setText(current + friend + ": " + message + "\n");
		chatArea.setCaretPosition(chatArea.getText().length());
	}
	
	
	public void sendMessage() {
		if(input.getText() == "") return;
		String message = input.getText();
		if(friend == null) KGMain.sendPacket("groupchat#/#" + input.getText());
		else {
			KGMain.sendPacket("chat#/#" + friend + "#/#" + input.getText());
			String current = chatArea.getText();
			chatArea.setText(current + KGMain.name + ": " + message + "\n");
		}
		input.setText("");
	}

}
