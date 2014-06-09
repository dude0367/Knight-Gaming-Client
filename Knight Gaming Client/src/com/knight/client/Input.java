package com.knight.client;
import javax.swing.JOptionPane;

public class Input
{
	public static byte getByte(String paramString)
	{
		String str = JOptionPane.showInputDialog(paramString);
		return Byte.parseByte(str);
	}

	public static short getShort(String paramString)
	{
		String str = JOptionPane.showInputDialog(paramString);
		return Short.parseShort(str);
	}

	public static int getInt(String paramString)
	{
		String str = JOptionPane.showInputDialog(paramString);
		return Integer.parseInt(str);
	}

	public static long getLong(String paramString)
	{
		String str = JOptionPane.showInputDialog(paramString);
		return Long.parseLong(str);
	}

	public static float getFloat(String paramString)
	{
		String str = JOptionPane.showInputDialog(paramString);
		return Float.parseFloat(str);
	}

	public static double getDouble(String paramString)
	{
		String str = JOptionPane.showInputDialog(paramString);
		return Double.parseDouble(str);
	}

	public static boolean getBoolean(String paramString)
	{
		String str = JOptionPane.showInputDialog(paramString);
		return Boolean.parseBoolean(str);
	}

	public static char getChar(String paramString)
	{
		String str = JOptionPane.showInputDialog(paramString);
		return str.charAt(0);
	}

	public static String getString(String paramString)
	{
		String str = JOptionPane.showInputDialog(paramString);
		return str;
	}
}