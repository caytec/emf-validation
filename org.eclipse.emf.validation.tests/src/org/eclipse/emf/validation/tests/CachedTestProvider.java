/**
 * <copyright>
 *
 * Copyright (c) 2003, 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id$
 */

package org.eclipse.emf.validation.tests;

import org.eclipse.emf.validation.service.AbstractConstraintProvider;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import java.util.Collection;

/**
 * A simple provider which is instrumented to count the number of hits that the
 * constraint service has made on it, to verify that caching works correctly.
 * 
 * @author Christian W. Damus (cdamus)
 */
public class CachedTestProvider extends AbstractConstraintProvider {
	private static CachedTestProvider instance;
	
	private final java.util.Map hits = new java.util.HashMap();
	
	/**
	 * Initializes me and remembers me for static access.
	 * 
	 * @see #getInstance
	 */
	public CachedTestProvider() {
		super();
		
		instance = this;
	}
	
	// redefines the inherited method
	public Collection getBatchConstraints(EObject eObject,
			Collection constraints) {
		// I don't actually need to provide any constraints in order to
		//   register a hit.  The cache will remember that there are no
		//   constraints!
		
		registerHit(eObject.eClass());
		
		return constraints;
	}
	
	/**
	 * Registers a hit on me (i.e., a miss on the cache) for this EMF type.
	 * 
	 * @param clazz the EMF type
	 */
	private final void registerHit(EClass clazz) {
		hits.put(clazz, new Integer(getHitCount(clazz) + 1));
	}
	
	/**
	 * Obtains the test instance.
	 * 
	 * @return the test instance
	 */
	public static CachedTestProvider getInstance() {
		return instance;
	}
	
	/**
	 * Obtains the number of times that I have been "hit" for constraints for
	 * the specified EMF class.
	 *
	 * @param clazz an EMF class 
	 * @return the number of times that I have provided constraints
	 */
	public int getHitCount(EClass clazz) {
		Integer result = (Integer)hits.get(clazz);
		
		return (result == null) ? 0 : result.intValue();
	}
}
