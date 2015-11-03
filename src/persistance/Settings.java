package persistance;

/*
 * Esta clase define una serie de estados del programa en general.
 */

public class Settings {
	
	private static Settings settings;
	
	private boolean status=false;;
	
	public boolean isStatus() {
		return status;
	}

	public void changeStatus() {
		if (status) {
			status=false;
			System.out.println("El programa ha terminado.");
		}else {
			status=true;
			System.out.println("El programa ha iniciado.");
		}
	}

	public static Settings getSettings(){
		if (settings==null) {
			settings=new Settings();
			return settings;
		} else {
			return settings;
		}
	}

}
