package poc.raviraj.gwtapp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class GwtApp implements EntryPoint {
	
	public static MainContainerLayout CONTAINER = new MainContainerLayout();

	public void onModuleLoad() {
		
		RootLayoutPanel rootLayoutPanel = RootLayoutPanel.get();
		rootLayoutPanel.add(CONTAINER);
		
	}
}
