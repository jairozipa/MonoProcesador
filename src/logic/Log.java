package logic;

import java.util.Date;

/*
 * En esta clase se define la forma en que se guardara lo que pasa con cada proceso.
 * guarda el estado y el proceso
 */

import javax.swing.JTextArea;

public class Log extends JTextArea{
	
	private static Log log;
	Date sysdate;
	
	public static Log getLog(){
		if (log==null) {
			log=new Log();
			return log;
		} else {
			return log;
		}
	}
	
	private Log(){
		
		
	}
	
	public void readyMessage(Process p) {
		sysdate=new Date();
		this.setText(this.getText()+"\n"+sysdate.toString()+" El proceso con ID "+p.getId()+" está " +State.LISTO);
	}
	
	public void blockedMessage(Process p) {
		sysdate=new Date();
		this.setText(this.getText()+"\n"+sysdate.toString()+" El proceso con ID "+p.getId()+" está " +State.BLOQUEADO);
	}
	public void execMessage(Process p) {
		sysdate=new Date();
		this.setText(this.getText()+"\n"+sysdate.toString()+" El proceso con ID "+p.getId()+" está " +State.EN_EJECUCION);
	}
	
	public void endedMessage(Process p) {
		sysdate=new Date();
		this.setText(this.getText()+"\n"+sysdate.toString()+" El proceso con ID "+p.getId()+" está " +State.FINALIZADO);
	}

}
