package org.eclipse.wst.server.ui.tests.dialog;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.IRuntimeType;
import org.eclipse.wst.server.core.IServer;
import org.eclipse.wst.server.core.IServerType;
import org.eclipse.wst.server.core.IServerWorkingCopy;
import org.eclipse.wst.server.core.ServerCore;
import org.eclipse.wst.server.ui.internal.DeleteServerDialog;
import org.junit.BeforeClass;
import org.junit.Test;

public class DeleteOptionsTest {
	
	private final static String ID = "org.eclipse.wst.server.preview.server";
	private static IServer server;
	private static IRuntime runtime;
	private List<Button> buttons = new ArrayList<Button>();
	
	@BeforeClass
	public static void setUp() throws Exception {
		// Finds the preview server type
		IServerType[] sTypes = ServerCore.getServerTypes();
		IServerType serverType = null;
		for (int i = 0; i < sTypes.length; i++) {
			IServerType sType = sTypes[i];
			if (ID.equals(sType.getId()))
				serverType = sType;
		}
		assertNotNull("Could not find " + ID + " server type", serverType);
		//Finds the preview server runtime type
		IRuntimeType runtimeType = serverType.getRuntimeType();
		assertNotNull("Could not find runtime type for the preview server type", runtimeType);
		runtime = runtimeType.createRuntime(ID+".Preview.Runtime",null);
		assertNotNull("Could not create runtime",runtime);
		//Create a new server instance from the type
		IServerWorkingCopy swc = serverType.createServer(ID+".Preview.Server", null, runtime, null);
		assertNotNull("Could not create server",swc);
		//Save the server
		server = swc.save(false,null);
	}
	
	@Test
	public void testDialog() {
		DeleteServerDialog dsd = new DeleteServerDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), 
				new IServer[] {server}, new IFolder[0]);
		dsd.setBlockOnOpen(false);
		dsd.open();
		Shell shell = dsd.getShell();
		Control[] children = shell.getChildren();
		findButtons(children);
		checkButtonExistence("testAdditional", true);
		checkButtonExistence("testWrongType", false);
		checkButtonExistence("testEnablement", true);
		checkButtonExistence("testEnablementFails", false);
		dsd.close();
		
	}

	private void checkButtonExistence(String buttonText, boolean exists) {
		boolean found = false;
		for (Button button : buttons) {
			if (button.getText().equals(buttonText)) {
				found = true;
				break;
			}
		}
		if (exists) {
			assertTrue("Button with label " + buttonText + " is not found!", found);
		} else {
			assertFalse("Found button with label " + buttonText + " but there should be no such!", found);
		}
		
	}

	private void findButtons(Control[] children) {
		for (Control control : children) {
			if (control instanceof Composite) {
				findButtons(((Composite)control).getChildren());
			} else if (control instanceof Button) {
				buttons.add((Button)control);
			}
		}
		
	}


}
