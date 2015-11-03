package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import persistance.Settings;
import logic.ProcessesList;
import logic.Processor;

/*
 * Esta clase se encarga de crear los componentes graficos y las aaciones de los componentes al momento en que 
 * se cargan los procesos. define un listado de procesos que han sido creados. 
 */
public class StartPanel extends JPanel{
	
	
	private static final long serialVersionUID = 1L;

	private int id=0;
	
	private JList<String> startList;
	private DefaultListModel model=new DefaultListModel();
	private JPanel addForm;
	private JLabel lblImagen;
	private ImageIcon image;
	private JButton startSim;
	private Process tempProcess;
	private ProcessesList tmp;
	
	private JLabel frequencyLabel;
	private JLabel maxMemLabel;
	private JLabel pTimeLabel;
	private JTextField frequencyField;
	private JTextField maxMemField;
	private JTextField pTimeField;
	private JButton start;
	
	private static StartPanel startPanel;
	
	public static StartPanel getStartPanel(){
		if (startPanel==null) {
			startPanel=new StartPanel();
			return startPanel;
		} else {
			return startPanel;
		}
	}
	
	private StartPanel() {
		this.setLayout(new GridLayout(2,1));
		tmp=ProcessesList.getProcessesList();
		addForm=new JPanel();
		addForm.setLayout(new BorderLayout());
		addForm.setBorder(BorderFactory.createTitledBorder("Panel Iniciar"));
		startList= new JList<String>();
		startList.setBorder(new TitledBorder("Procesos Listos para Ejecución"));
		startList.setModel(model);
		image = new ImageIcon(getClass().getResource("/persistance/Processor.jpg"));
		lblImagen = new JLabel();
		lblImagen.setIcon(image);
		lblImagen.setHorizontalAlignment(JLabel.CENTER);
		
		final Thread th=new Thread(new Runnable() {
			
			@Override
			public void run() {
				ArrayList<logic.Process> arrayList;
				while (Settings.getSettings().isStatus()){
					model.clear();
					arrayList=ProcessesList.getProcessesList().getStartProcesses();
					repaint();
					for (int i = 0; i < arrayList.size(); i++) {
						model.addElement(arrayList.get(i).toString());
					}
					
					try {
						Thread.sleep(300);
					} catch (Exception e) {
					}
				}
				
			}
		});
		

		frequencyLabel=new JLabel("Frecuencia");
		maxMemLabel=new JLabel("Memoria");
		pTimeLabel=new JLabel("Tiempo Ejecución");
		frequencyField=new JTextField();
		maxMemField=new JTextField();
		pTimeField=new JTextField();
		start=new JButton("Iniciar");
		
		

		startSim=new JButton("Iniciar Simulación");
		
		startSim.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Settings.getSettings().changeStatus();
					
					Processor.getProcessor().setMaxMemory(100);
					Processor.getProcessor().setTime(100);
					
					startSim.setEnabled(false);
					th.start();
					MiddlePanel.getMiddlePanel().getTh().start();
					EndedPanel.getEndedPanel().getTh().start();
					Processor.getProcessor().getTh().start();
					
					
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(new JOptionPane(), "Ingrese valores numericos solamente.");
				}


			}
		});
		
	
		addForm.add(startSim,BorderLayout.SOUTH);
		addForm.add(lblImagen, BorderLayout.CENTER);
		this.add(addForm);
		this.add(startList);
		
	}


}
