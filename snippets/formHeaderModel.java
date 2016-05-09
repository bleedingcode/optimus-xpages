package core;

import java.io.Serializable;

public class FormHeaderModel implements Serializable{
	private static final long serialVersionUID = 1L;

	//PROPERTIES
	public String id;
	public String formName;
	public boolean isNewDoc;
	public boolean readOnly;
	
	//CONSTRUCTOR
	public FormHeaderModel() {
		id = "";
		formName = "";
		isNewDoc = true;
		readOnly = false;
	}

	//GETTERS AND SETTERS
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isNewDoc() {
		return isNewDoc;
	}

	public void setNewDoc(boolean isNewDoc) {
		this.isNewDoc = isNewDoc;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

}
