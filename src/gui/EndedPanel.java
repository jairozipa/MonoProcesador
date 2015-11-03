package gui;


import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import persistance.Settings;
import logic.ProcessesList;


/*
 * Clase encargada de mostrar los procesos que han finalizado su ejecución 
 */
public class EndedPanel extends JPanel{
	
	private JList<String> endedList;
	private DefaultListModel model=new DefaultListModel();
	private final Thread th;
	
	private static EndedPanel endedPanel;
	
	/**
	 * 
	 * @return panel
	 */
	public static EndedPanel getEndedPanel(){
		if (endedPanel==null) {
			endedPanel=new EndedPanel();
			return endedPanel;
		} else {
			return endedPanel;
		}
	}
	
	/**
	 * 
	 */
	private EndedPanel() {
		this.setLayout(new GridLayout());
		endedList= new JList<String>();
		endedList.setBorder(new TitledBorder("Procesos Finalizados"));
		endedList.setModel(model);
		
		th=new Thread(new Runnable() {
			
			@Override
			public void run() {
				ArrayList<logic.Process> arrayList;
				while (Settings.getSettings().isStatus()){
					model.clear();
					arrayList=ProcessesList.getProcessesList().getEndedProcesses();
					repaint();
					for (int i = 0; i < arrayList.size(); i++) {
						model.addElement(arrayList.get(i).toString());
					}
					
					try {
						Thread.sleep(300);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				
			}
		});
		
	
		this.add(endedList);
	}

	public Thread getTh() {
		return th;
	}
}
