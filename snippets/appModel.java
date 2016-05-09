package core;

import java.io.Serializable;

public class AppModel implements Serializable{
	private static final long serialVersionUID = 1L;

	//PROPERTIES
	public String dataPath;
	public String appName;
	
	//CONSTRUCTOR
	public AppModel() {
		dataPath = "";
		appName = "";
	}

	//GETTERS AND SETTERS
	public String getDataPath() {
		return dataPath;
	}

	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

}
