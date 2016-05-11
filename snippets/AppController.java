package core;

import java.io.Serializable;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import com.ibm.xsp.extlib.component.dynamiccontent.UIDynamicContent;

public class AppController implements Serializable{
	private static final long serialVersionUID = 1L;

	//PROPERTIES
	public AppModel appModel;
	public String defaultApp = "leave";
	
	//PUBLIC FUNCTIONS
	public void InitApp(String tempKey) {
		String key = defaultApp;
		
		try {			
			//Check if Default Init
			if(tempKey != null) {
				key = tempKey;
			}
			
			_ResetControllers();
			
			//Init Relevant properties and controllers based on key
			appModel = new AppModel();
			appModel.appName = key;
			
			if(key.equals("leave")) {
				appModel.dataPath = "demo/ox2/portaldata.nsf";
			}else if(key.equals("tasks")) {
				appModel.dataPath = "demo/ox2/portaldata.nsf";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void SwitchApp(String menuId) {
		try {
			InitApp(menuId);
			_SwitchDynamicContent("dynamicCoreBody", menuId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//PRIVATE FUNCTIONS
	private void _ResetControllers() {
	}
	
	public static boolean _SwitchDynamicContent(String id, String value) {
		UIComponent component = _FindComponent(FacesContext.getCurrentInstance().getViewRoot(), id);
		UIDynamicContent dynamicData = (UIDynamicContent) component;
		
		dynamicData.show(value);
	
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public static UIComponent _FindComponent(UIComponent topComponent,
			String compId) {
		
		if (compId == null)
			throw new NullPointerException(
					"Component identifier cannot be null");
		
		if (compId.equals(topComponent.getId()))
			return topComponent;

		if (topComponent.getChildCount() > 0) {
			List<UIComponent> childComponents = topComponent.getChildren();
			
			for (UIComponent currChildComponent : childComponents) {
				UIComponent foundComponent = _FindComponent(currChildComponent,
						compId);
				if (foundComponent != null)
					return foundComponent;
			}
		}
		return null;
	}	
	
	//GETTERS AND SETTERS
	public AppModel getAppModel() {
		return appModel;
	}

	public String getDefaultApp() {
		return defaultApp;
	}
}
