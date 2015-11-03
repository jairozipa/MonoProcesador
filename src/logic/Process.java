package logic;

/*
 * Esta clase define las caracteristicas de un proceso
 */

public class Process {

/*
 * Atributos de la clase logic
 */

	private int id;
	private String name;
	private int time; //In seconds 
	private State state;
	private int elapsedTime;
	private int blockedTime;
	private int blockedDuration;
	private Thread th;
/*
 * Constructor publico de la clase Process
 */	
	public Process(int id, String name,  int time) {
		this.id=id;
		this.name=name;
		
		this.time=time;		
		this.elapsedTime=0;
		this.blockedTime=-1;
		this.blockedDuration=-1;
		
		Log.getLog().readyMessage(this);
		
	}
	
	public Process(int id, String name ,int time, int blockedTime, int blockedDuration) {
		this.id=id;
		this.name=name;
		this.time=time;		
		this.elapsedTime=0;
		this.blockedTime=blockedTime;
		this.blockedDuration=blockedDuration;
		Log.getLog().readyMessage(this);
		th=new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (getBlockedDuration()>0) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					setBlockedDuration(getBlockedDuration()-1);
				}
				blockedToReady();
			}
		});
	}
	
	public void blockedToReady(){
		blockedTime=-1;
		Log.getLog().readyMessage(this);
		ProcessesList.getProcessesList().getStartProcesses().add(this);
		ProcessesList.getProcessesList().getBlockedProcesses().remove(this);
		
	}
	
	

	@Override
	public String toString() {
		return "Proceso con ID: " + id + ", " + name + ", "
				+  time + " segundos";
	}
	
	public String blockedToString() {
		return "Proceso con ID: " + id + ", " + name + ", "
				+blockedDuration + " segundos";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public int getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(int elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	public int getBlockedTime() {
		return blockedTime;
	}

	public void setBlockedTime(int blockedTime) {
		this.blockedTime = blockedTime;
	}

	public int getBlockedDuration() {
		return blockedDuration;
	}

	public void setBlockedDuration(int blockedDuration) {
		this.blockedDuration = blockedDuration;
	}

	public Thread getTh() {
		return th;
	}
	
	
}
