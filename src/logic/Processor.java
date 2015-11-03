package logic;

import java.util.Timer;

/*
 * Esta clase define el comportamiento del procesador 
 */

import javax.swing.JProgressBar;

import persistance.Settings;
import gui.MiddlePanel;

public class Processor {

	
	private int maxMemory;
	private int time;//Seconds
	private Process current;
	private int secondsCount;
	Timer timer;
	
	private static Processor processor;  
	private ProcessesList pList;
	JProgressBar progressBar;
	
	private final Thread th;
	
	public static Processor getProcessor(){
		if (processor == null) {
			processor = new Processor(); 
			return processor;
		} else {
			return processor;
		}
	}
	
	private Processor() {
		pList=ProcessesList.getProcessesList();
		th=new Thread(new Runnable() {
			
			@Override
			public void run() throws NullPointerException{
				while (Settings.getSettings().isStatus()) {
					if (current!=null) {
						ProcessesList.getProcessesList().getStartProcesses().add(current);
						Log.getLog().readyMessage(current);
						current=null;
					}else {
						MiddlePanel.getMiddlePanel().getCurrentProcess().setText("");
					}
					try {
						executeProcess();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					MiddlePanel.getMiddlePanel().getProgressBar().setMinimum(0);
					MiddlePanel.getMiddlePanel().getProgressBar().setValue(0);
					try {
						MiddlePanel.getMiddlePanel().getProgressBar().setMaximum(current.getTime());
					} catch (NullPointerException e) {
						// TODO: handle exception
					}
					
					while (secondsCount<time && current!=null) {
						MiddlePanel.getMiddlePanel().getCurrentProcess().setText("Proceso "+	current.getId()+ ", " +
								current.getName()+ ", " +
								current.getTime()+ " segundos, van " +
								current.getElapsedTime()+" segundos.");
						
						if (current.getBlockedTime()!=-1) {
							if (current.getBlockedTime()==0) {
								ProcessesList.getProcessesList().getBlockedProcesses().add(current);
								Log.getLog().blockedMessage(current);
								ProcessesList.getProcessesList().getBlockedProcesses().get(
										ProcessesList.getProcessesList().getBlockedProcesses().size()-1).getTh().start();
								current=null;
								break;
							}else {
								current.setBlockedTime(current.getBlockedTime()-1);
							}
							
						}
						
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						secondsCount++;
						
						MiddlePanel.getMiddlePanel().repaint();
						
						current.setElapsedTime(current.getElapsedTime()+1);
						MiddlePanel.getMiddlePanel().getProgressBar().setValue(current.getElapsedTime());
												
						if (current.getTime()==current.getElapsedTime()) {
							ProcessesList.getProcessesList().getEndedProcesses().add(current);
							Log.getLog().endedMessage(current);
							current=null;
							break;
						}
						

						
					}
				}
				
			}
		});
	}
	
	public void executeProcess() throws InterruptedException{
		try {
			if (pList.getStartProcesses().isEmpty()==false || pList.getBlockedProcesses().isEmpty()==false) {
				current=pList.getStartProcesses().get(0);
				Log.getLog().execMessage(current);
				pList.getStartProcesses().remove(0);
				secondsCount=0;
			} 	else {
				Thread.sleep(1000);
				Settings.getSettings().changeStatus();
			}
		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
		}
			
	}

	public Thread getTh() {
		return th;
	}


	public int getMaxMemory() {
		return maxMemory;
	}

	public void setMaxMemory(int maxMemory) {
		this.maxMemory = maxMemory;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
}
