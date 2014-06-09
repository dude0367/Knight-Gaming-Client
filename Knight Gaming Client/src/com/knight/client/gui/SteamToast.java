package com.knight.client.gui;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.Callable;

import javax.swing.JFrame;

import com.knight.client.KGMain;




public class SteamToast extends JFrame implements Runnable, MouseListener {
	
	public Frame frm;
	Thread thread;
	public int width = 200;
	public int height = 0;
	public int maxHeight = 100;
	public int xPos;
	public int yPos;
	SteamToast toast;
	public boolean showing = true;
	public Label label;
	Callable clickFunc;
	String name;
	
	public SteamToast(String text, Callable func) {
		initi(text, func);		
	}
	
	public SteamToast(String text, Callable func, String name) {
		initi(text, func);
	}
	
	private void initi(String text, Callable func) {
		frm = this;
		((JFrame) frm).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frm.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frm.setUndecorated(true);
		((JFrame)frm).getContentPane().setBackground(new Color(0x222222));
		frm.setVisible(true);
		xPos = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width - width;
		yPos = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height - 30;
		frm.setLocation(xPos, yPos);
		frm.setBounds(xPos, yPos, width, height);
		frm.setAlwaysOnTop(true);
		
		label = new Label(text);
		label.setForeground(Color.WHITE);
		this.clickFunc = func;
		label.addMouseListener(this);
		frm.addMouseListener(this);
		((JFrame)frm).getContentPane().addMouseListener(this);
		
		((JFrame)frm).getContentPane().add(label);
		thread = new Thread(new SteamToast(this));
		thread.start();
	}
	
	public SteamToast(SteamToast toast) {
		this.toast = toast;
	}

	@Override
	public void run() {
		while(toast.showing) {
			long time = System.currentTimeMillis();
			if(toast.height < toast.maxHeight) toast.height++;
			toast.frm.setBounds(toast.xPos, toast.yPos - toast.height, toast.width, toast.height);
			if(toast.height < toast.maxHeight) toast.height++;
			while(System.currentTimeMillis() - time < 1000.0 / 40.0) {
				try {
					Thread.sleep(0);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		label.setForeground(Color.YELLOW);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		label.setForeground(Color.WHITE);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		showing = false;
		frm.setVisible(false);
		try {
			clickFunc.call();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if(name != null) {
			//KGMain.chats.get(name) = new ChatWindow(name);
			KGMain.chats.put(name, new ChatWindow("Chat with " + name, name));
			KGMain.chats.get(name).addMessage(this.getTitle());
		}
	}

}
