package task;

import java.io.Serializable;

import javax.faces.context.FacesContext;

import org.openntf.domino.Database;
import org.openntf.domino.DateTime;
import org.openntf.domino.Document;
import org.openntf.domino.Session;
import org.openntf.domino.utils.Factory;
import org.openntf.domino.utils.Factory.SessionType;

import core.AppController;

public class TaskController implements Serializable{
	private static final long serialVersionUID = 1L;

	//PROPERTIES
	public TaskModel task;
	public String facet = "view";
	
	//PUBLIC FUNCTIONS
	public void CreateDocument() {
		task = new TaskModel();
		task.header.formName = "Task";
		facet = "form";
	}

	public void CloseDocument() {
		task = null;
		facet = "view";
	}
	
	public void SaveDocument() {
		Session ss = Factory.getSession(SessionType.CURRENT);
		FacesContext context = FacesContext.getCurrentInstance();
		AppController appCon = (AppController) context.getApplication()
				.getVariableResolver().resolveVariable(context, "App");

		Database db = null;
		Database dataDb = null;
		Document doc = null;
		
		try {
			db = ss.getCurrentDatabase();
			dataDb = ss.getDatabase(db.getServer(),
					appCon.appModel.dataPath);

			if (dataDb.isOpen()) {
				// Check if Doc is new
				if (task.header.isNewDoc) {
					doc = dataDb.createDocument();
					doc.replaceItemValue("Form", task.header.formName);
				} else {
					doc = dataDb.getDocumentByUNID(task.header.id);
				}

				if (doc != null) {
					doc.replaceItemValue("TaskName", task.taskName);
					doc.replaceItemValue("DueDate", task.dueDate);

					doc.save();

					// Finalise
					task = null;
					facet = "view";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void OpenDocument(String docId) {
		Session ss = Factory.getSession(SessionType.SIGNER);
		FacesContext context = FacesContext.getCurrentInstance();
		AppController appCon = (AppController) context.getApplication()
				.getVariableResolver().resolveVariable(context, "App");

		Database db = null;
		Database dataDb = null;
		Document doc = null;
		DateTime tempDate = null;
		
		try {
			db = ss.getCurrentDatabase();
			dataDb = ss.getDatabase(db.getServer(),
					appCon.appModel.dataPath);

			if (dataDb.isOpen()) {
				doc = dataDb.getDocumentByUNID(docId);

				if (doc != null) {
					task = new TaskModel();
					task.header.id = docId;
					task.header.isNewDoc = false;
					
					task.taskName = doc.getItemValueString("TaskName");
					
					tempDate = ss.createDateTime(doc.getItemValueDateTimeArray("DueDate").elementAt(0).toString());
					task.dueDate = tempDate.toJavaDate() ;
				}
			}
			
			//Finalise
			facet = "form";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	//GETTERS AND SETTERS
	public String getFacet() {
		return facet;
	}

	public TaskModel getTask() {
		return task;
	}
}
