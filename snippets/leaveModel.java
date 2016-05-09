package leave;

import java.io.Serializable;
import java.util.Date;

import core.FormHeaderModel;

public class LeaveModel implements Serializable{
	private static final long serialVersionUID = 1L;

	//PROPERTIES
	public FormHeaderModel header;
	public String leaveType;
	public Date leaveFrom;
	public Date leaveTo;
	
	//CONSTRUCTOR
	public LeaveModel() {
		header = new FormHeaderModel();
		leaveType = "";
		leaveFrom = null;
		leaveTo = null;
	}

	//GETTERS AND SETTERS
	public FormHeaderModel getHeader() {
		return header;
	}

	public void setHeader(FormHeaderModel header) {
		this.header = header;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public Date getLeaveFrom() {
		return leaveFrom;
	}

	public void setLeaveFrom(Date leaveFrom) {
		this.leaveFrom = leaveFrom;
	}

	public Date getLeaveTo() {
		return leaveTo;
	}

	public void setLeaveTo(Date leaveTo) {
		this.leaveTo = leaveTo;
	}
}
