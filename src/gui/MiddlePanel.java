package gui;


import java.awt.Color;
import java.awt.GridLayout;

import java.util.ArrayList;

import javax.swing.DefaultListModel;


import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JProgressBar;
import javax.swing.JScrollPane;

import javax.swing.border.TitledBorder;

import persistance.Settings;
import logic.Log;
import logic.ProcessesList;

/*
 * Clase encargada de mostrar los datos y estados de cada proceso que se esta ejecutando, 
 * muestra el progreso de un proceso al ejecutarse,
 * muestra el listado de procesos que se encuentran bloqueados y el tiempo de bloqueo
 */
public class MiddlePanel extends JPanel{
	
	
	private JPanel processPanel;
	private JLabel currentProcess;
	private JList<String> blockedList;
	private DefaultListModel model=new DefaultListModel();
	private Log logList;
	private JScrollPane jsp;
	private final Thread th;
	private JProgressBar progressBar;
	
//	String path = "/persistance/Processor.jpg";  
//	URL url = this.getClass().getResource(path);  
//	ImageIcon icon = new ImageIcon(url);  
	
	public JProgressBar getProgressBar() {
		return progressBar;
	}



	public void setProgressBar(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}



	private static MiddlePanel middlePanel;
	
	/**
	 * 
	 * @return
	 */
	public static MiddlePanel getMiddlePanel(){
		if (middlePanel==null) {
			middlePanel=new MiddlePanel();
			return middlePanel;
		} else {
			return middlePanel;
		}
	}	
	

	
	private MiddlePanel() {
		this.setLayout(new GridLayout(3,1,10,10));
		currentProcess=new JLabel();
		currentProcess.setBorder(new TitledBorder("Procesador"));
//		currentProcess.setIcon(icon);
		
		processPanel=new JPanel();
		processPanel.setLayout(new GridLayout(2,1));
		
		logList= Log.getLog();

		logList.setEditable(false);
		
		jsp=new JScrollPane(logList);
		jsp.setBorder(new TitledBorder("Log"));
		blockedList= new JList<String>();
		blockedList.setBorder(new TitledBorder("Procesos Bloqueados"));
		blockedList.setModel(model);
		
		
		progressBar=new JProgressBar();
		progressBar.setForeground(new Color(0,170,255));
		progressBar.setStringPainted(true);
		
		processPanel.setLayout(new GridLayout(2,1,80,80));
		
		processPanel.add(currentProcess);
		processPanel.add(progressBar);
		
		th=new Thread(new Runnable() {
			
			@Override
			public void run() {
				ArrayList<logic.Process> arrayList;
				while (Settings.getSettings().isStatus()){
					model.clear();
					arrayList=ProcessesList.getProcessesList().getBlockedProcesses();
					repaint();
					for (int i = 0; i < arrayList.size(); i++) {
						model.addElement(arrayList.get(i).blockedToString());
					}
					
					try {
						Thread.sleep(300);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				JOptionPane.showMessageDialog(new JOptionPane(), "La simulación ha finalizado con normalidad. En el Log puede encontrar más detalles.");
				
			}
		});
		
		this.add(processPanel);
		this.add(blockedList);
		this.add(jsp);
	}



	public Thread getTh() {
		return th;
	}



	public JLabel getCurrentProcess() {
		return currentProcess;
	}



	public void setCurrentProcess(JLabel currentProcess) {
		this.currentProcess = currentProcess;
	}
	
}
