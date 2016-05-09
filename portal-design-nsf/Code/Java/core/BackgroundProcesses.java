package core;

import org.openntf.domino.xots.Tasklet;

import com.ibm.commons.util.io.json.JsonJavaObject;

@Tasklet(session = Tasklet.Session.CLONE)
public class BackgroundProcesses implements Runnable{
	private JsonJavaObject jsonData = null;
	private int processType = 0;
	
	public BackgroundProcesses(int tempProcessType, JsonJavaObject tempJsonData) {
		jsonData = tempJsonData;
		processType = tempProcessType;
	}
	
	public void run() {
		try {
			switch(processType) {
			case 1://Log Transaction
				Thread.sleep(7000);
				System.out.println("I should run 3rd = " + jsonData.get("value"));
				break;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
