package leave;

import java.io.Serializable;

import javax.faces.context.FacesContext;

import org.openntf.domino.Database;
import org.openntf.domino.DateTime;
import org.openntf.domino.Document;
import org.openntf.domino.Session;
import org.openntf.domino.utils.Factory;
import org.openntf.domino.utils.Factory.SessionType;

import core.AppController;

public class LeaveController implements Serializable{
	private static final long serialVersionUID = 1L;

	//PROPERTIES
	public LeaveModel leave;
	public String facet = "view";
	
	//PUBLIC FUNCTIONS
	public void CreateDocument() {
		leave = new LeaveModel();
		leave.header.formName = "LeaveRequest";
		facet = "form";
	}

	public void CloseDocument() {
		leave = null;
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
				if (leave.header.isNewDoc) {
					doc = dataDb.createDocument();
					doc.replaceItemValue("Form", leave.header.formName);
				} else {
					doc = dataDb.getDocumentByUNID(leave.header.id);
				}

				if (doc != null) {
					doc.replaceItemValue("LeaveType", leave.leaveType);
					doc.replaceItemValue("LeaveFrom", leave.leaveFrom);
					doc.replaceItemValue("LeaveTo", leave.leaveTo);

					doc.save();

					// Finalise
					leave = null;
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
					leave = new LeaveModel();
					leave.header.id = docId;
					leave.header.isNewDoc = false;
					
					leave.leaveType = doc.getItemValueString("LeaveType");
					
					tempDate = ss.createDateTime(doc.getItemValueDateTimeArray("LeaveFrom").elementAt(0).toString());
					leave.leaveFrom = tempDate.toJavaDate() ;
					tempDate = ss.createDateTime(doc.getItemValueDateTimeArray("LeaveTo").elementAt(0).toString());
					leave.leaveTo = tempDate.toJavaDate() ;
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

	public LeaveModel getLeave() {
		return leave;
	}
}
