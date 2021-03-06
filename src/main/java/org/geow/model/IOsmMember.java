/*******************************************************************************
 * Copyright 2013 Jan Schulte
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package org.geow.model;

import java.math.BigInteger;

/**
 * Member of a relation. See {@link http://wiki.openstreetmap.org/wiki/Relation} for more information. 
 *  
 * 
 * @author Jan Schulte
 *
 */
public interface IOsmMember extends IOsmObject{

	/**
	 * Gets the value of the ref property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link BigInteger }
	 *     
	 */
	public abstract BigInteger getRef();
	
	/**
	 * Sets the ref.
	 * 
	 * @param ref
	 *     possible object is
	 *     {@link BigInteger }
	 *     
	 */
	public void setRef(BigInteger ref);

	/**
	 * Gets the value of the role property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public abstract String getRole();

	/**
	 * Gets the value of the type property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public abstract String getType();

}
