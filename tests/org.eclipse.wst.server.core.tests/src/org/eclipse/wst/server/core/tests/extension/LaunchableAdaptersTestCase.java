/**********************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
�*
 * Contributors:
 *    IBM - Initial API and implementation
 **********************************************************************/
package org.eclipse.wst.server.core.tests.extension;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.wst.server.core.ILaunchableAdapter;
import org.eclipse.wst.server.core.ServerCore;

public class LaunchableAdaptersTestCase extends TestCase {
	public static Test suite() {
		return new TestSuite(LaunchableAdaptersTestCase.class, "LaunchableAdaptersTestCase");
	}

	public void testLaunchableAdaptersExtension() throws Exception {
		ILaunchableAdapter[] la = ServerCore.getLaunchableAdapters();
		if (la != null) {
			int size = la.length;
			for (int i = 0; i < size; i++)
				System.out.println(la[i].getId());
		}
	}
}