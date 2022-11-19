/**
 * Copyright (c) 2007 IBM Corporation and others.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.validation.marker.tests;

import junit.framework.TestSuite;

/**
 * Test suite encapsulating all of the JUnit tests in this package.
 *
 * @author Christian W. Damus (cdamus)
 */
public final class AllTests extends TestSuite {
	/**
	 * Creates the test suite.
	 *
	 * @return the test suite
	 */
	public AllTests() {
		super("Test for org.eclipse.emf.validation.marker package"); //$NON-NLS-1$

		addTest(MarkerUtilTest.suite());
	}
}
