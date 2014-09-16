package com.knight.client.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.knight.client.Input;
import com.knight.client.KGMain;

public class Login extends JFrame {

	private JPanel contentPane;
	private final JPanel inputPanel = new JPanel();
	private final JPanel usernamePanel = new JPanel();
	private final JLabel usernameLabel = new JLabel("Username");
	//private final JTextField inputPassword = new JTextField();
	private final JPasswordField inputPassword = new JPasswordField();
	private final JTextField inputUsername = new JTextField();
	private final JPanel passwordPanel = new JPanel();
	private final JLabel passwordLabel = new JLabel("Password");
	private final JPanel panel = new JPanel();
	private final JButton btnLogin = new JButton("Login");
	private final JButton btnRegister = new JButton("Register");

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		inputUsername.setColumns(10);
		initGUI();
	}
	private void initGUI() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 315, 119);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		contentPane.add(inputPanel, BorderLayout.NORTH);
		inputPanel.setLayout(new BorderLayout(0, 0));

		inputPanel.add(usernamePanel, BorderLayout.NORTH);
		usernamePanel.setLayout(new BorderLayout(0, 0));

		usernamePanel.add(usernameLabel, BorderLayout.WEST);

		usernamePanel.add(inputUsername, BorderLayout.CENTER);

		inputPanel.add(passwordPanel, BorderLayout.SOUTH);
		passwordPanel.setLayout(new BorderLayout(0, 0));

		passwordPanel.add(passwordLabel, BorderLayout.WEST);
		inputPassword.setColumns(10);
		
		inputPassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});

		passwordPanel.add(inputPassword, BorderLayout.CENTER);

		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login();
			}
		});

		panel.add(btnLogin, BorderLayout.WEST);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = "register#/#" + Input.getString("Username: ") + "#/#" + Input.getString("Password: ");
				KGMain.streamOut.println(name);
			}
		});

		panel.add(btnRegister, BorderLayout.EAST);
	}
	
	void login() {
		if(inputUsername.getText() != "" && inputPassword.getText() != "") {
			String out = "login" + "#/#" + inputUsername.getText() + "#/#" + inputPassword.getText();
			KGMain.streamOut.println(out);
			System.out.println("Signing in: " + out);
			KGMain.name = inputUsername.getText();
		}
	}

}
