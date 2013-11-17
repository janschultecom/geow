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
import java.util.List;

/**
 * Supertype for osm objects with properties and tags.
 * 
 * The standard osm properties are:
 * 
 * <ul>
 * 		<li><i>id</i><p>Id of the osm object</li>
 * 		<li><i>uid</i><p>Id of the user who changed or created the osm object</li>
 * 		<li><i>user</i><p>Name of the user who changed or created the osm object</li>
 * 		<li><i>timestamp</i><p>Timestamp of the creation or modification</li>
 * 		<li><i>version</i><p>Version of this osm object</li>
 * 		<li><i>changeset</i><p>Changeset this creation or modification belongs to</li>
 * 		<li><i>visible</i><p>True if this object should be visible</li>
 * </ul>
 * 
 * @author Jan Schulte
 *
 */
public interface IOsmElement extends IOsmObject{

	/**
	 * Gets the value of the tag property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list,
	 * not a snapshot. Therefore any modification you make to the
	 * returned list will be present inside the JAXB object.
	 * This is why there is not a <CODE>set</CODE> method for the tag property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * <pre>
	 *    getTag().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link Tag }
	 * 
	 * 
	 */
	public abstract <T extends IOsmTag> List<T> getTag();
	
	/**
	 * Sets the value of the tag property.
	 *  
	 * <p>
	 * 
	 * @param tags The new list of tags
	 * Objects of the following type(s) are allowed in the list
	 * {@link Tag }
	 * 
	 * 
	 */
	public abstract <T extends IOsmTag> void setTag(List<T> tags);

	/**
	 * Gets the value of the changeset property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link BigInteger }
	 *     
	 */
	public abstract BigInteger getChangeset();
	
	/**
	 * Sets the value of the changeset property.
	 * 
	 * @param changeset
	 *     possible object is
	 *     {@link BigInteger }
	 *     
	 */
	public abstract void setChangeset(BigInteger changeset);

	/**
	 * Gets the value of the id property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link BigInteger }
	 *     
	 */
	public abstract BigInteger getId();
	
	/**
	 * Sets the osm Id.
	 * 
	 * @param id
	 *     The new osm id
	 *     {@link BigInteger }
	 *     
	 */
	public abstract void setId(BigInteger id);

	/**
	 * Gets the value of the timestamp property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public abstract String getTimestamp();
	
	/**
	 * Sets the timestamp.
	 * 
	 * @param timestamp The new timestamp.
	 *     
	 */
	public abstract void setTimestamp(String timestamp);

	/**
	 * Gets the value of the uid property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link BigInteger }
	 *     
	 */
	public abstract BigInteger getUid();
	
	/**
	 * Sets the user id. 
	 * 
	 * @param uid The new uid
	 *     
	 */
	public abstract void setUid(BigInteger uid);

	/**
	 * Gets the value of the user property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public abstract String getUser();

	/**
	 * Sets the user name.
	 * 
	 * @param user The user of this osm object.
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public abstract void setUser(String user);
	
	/**
	 * Gets the value of the version property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link BigInteger }
	 *     
	 */
	public abstract BigInteger getVersion();
	
	/**
	 * Sets the version.
	 * 
	 * @param version
	 *     possible object is
	 *     {@link BigInteger }
	 *     
	 */
	public abstract void setVersion(BigInteger version);

	/**
	 * Gets the value of the visible property.
	 * 
	 */
	public abstract boolean isVisible();
	
	/**
	 * Sets the value of the visible property.
	 * 
	 */
	public abstract void setVisible(boolean visible);

}
