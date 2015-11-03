package test;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.DefaultListCellRenderer.UIResource;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import logic.ProcessesList;
import gui.Window;

public class Main {

	/**
	 * @param args
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	
	/*
	 * Clase principal que contiene el metodo main
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		UIManager.put("nimbusOrange", new Color(0,170,255));
		Window w=new Window();
		ProcessesList.getProcessesList().addProcess(1, "p1", 8, 3, 5);
		w.setVisible(true);
		


	}

}
