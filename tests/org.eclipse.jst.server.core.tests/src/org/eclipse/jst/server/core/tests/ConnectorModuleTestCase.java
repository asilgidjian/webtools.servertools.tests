/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - Initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.server.core.tests;

import org.eclipse.jst.server.core.IConnectorModule;
import org.eclipse.jst.server.core.tests.impl.TestConnectorModule;
import junit.framework.Test;
import junit.framework.TestCase;

public class ConnectorModuleTestCase extends TestCase {
	protected static IConnectorModule module;
	
	public static Test suite() {
		return new OrderedTestSuite(ConnectorModuleTestCase.class, "ConnectorModuleTestCase");
	}

	public void test00Create() {
		module = new TestConnectorModule();
	}
	
	public void test01SpecVersion() {
		module.getJ2EESpecificationVersion();
	}
	
	public void test02Location() {
		module.getLocation();
	}
	
	public void test03Binary() {
		module.isBinary();
	}
	
	public void test04Classpath() {
		module.getClasspath();
	}
}