package logic;

import gui.StartPanel;

/*
 * En esta clase se define la forma de almacenar los procesos
 */

import java.util.ArrayList;

public class ProcessesList{

	private ArrayList<Process> startProcesses;
	private ArrayList<Process> endedProcesses;
	private ArrayList<Process> blockedProcesses;
	private static ProcessesList processesList;
	
	
	public static ProcessesList getProcessesList(){
		if (processesList==null) {
			processesList=new ProcessesList();
			return processesList;
		} else {
			return processesList;
		}
	}
	
	private ProcessesList() {
		startProcesses=new ArrayList<Process>();
		endedProcesses=new ArrayList<Process>();
		blockedProcesses=new ArrayList<Process>();
		
	}
	
	public ArrayList<Process> getBlockedProcesses() {
		return blockedProcesses;
	}
	public void setBlockedProcesses(ArrayList<Process> blockedProcesses) {
		this.blockedProcesses = blockedProcesses;
	}
	public ArrayList<Process> getStartProcesses() {
		return startProcesses;
	}
	public void setStartProcesses(ArrayList<Process> startProcesses) {
		this.startProcesses = startProcesses;
	}
	public ArrayList<Process> getEndedProcesses() {
		return endedProcesses;
	}
	public void setEndedProcesses(ArrayList<Process> endedProcesses) {
		this.endedProcesses = endedProcesses;
	}
	
	public void addProcess(int id, String name, int time){
		startProcesses.add(new Process(id, name, time));		
	}
	
	public void addProcess(int id, String name, int time, int blockedTime, int blockedDuration){
		startProcesses.add(new Process(id, name,time, blockedTime, blockedDuration));		
	}
	
}
