package gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logic.ProcessesList;

/*
 * Esta clase define la interfaz principal del programa.
 */
public class Window extends JFrame {
	private StartPanel startPanel;
	private MiddlePanel middlePanel;
	private EndedPanel endedPanel;
	
	public Window(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1100, 600);
		this.setTitle("Procesos");
		this.setLocationRelativeTo(null);
		this.setLayout(new GridLayout(1,3));
		this.setResizable(true);
		
		
		
		startPanel=StartPanel.getStartPanel();
		middlePanel=MiddlePanel.getMiddlePanel();
		endedPanel=EndedPanel.getEndedPanel();
		

		this.add(startPanel);
		this.add(middlePanel);
		this.add(endedPanel);
	}
	

}
