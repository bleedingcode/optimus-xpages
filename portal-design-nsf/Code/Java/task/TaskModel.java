package task;

import java.io.Serializable;
import java.util.Date;

import core.FormHeaderModel;

public class TaskModel implements Serializable{
	private static final long serialVersionUID = 1L;

	//PROPERTIES
	public FormHeaderModel header;
	public String taskName;
	public Date dueDate;
	
	//CONSTRUCTOR
	public TaskModel() {
		header = new FormHeaderModel();
		taskName = "";
		dueDate = null;
	}

	//GETTERS AND SETTERS
	public FormHeaderModel getHeader() {
		return header;
	}

	public void setHeader(FormHeaderModel header) {
		this.header = header;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
}
