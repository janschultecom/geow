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

import java.math.BigDecimal;

/**
 * Defines the geographic boundary of the osm container. I.e. all elements of the osm container should be within this bounds. 
 * 
 * @see IOsm
 * 
 * @author Jan Schulte
 *
 */
public interface IOsmBounds extends IOsmObject{

	/**
	 * Gets the value of the maxlat property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link BigDecimal }
	 *     
	 */
	public abstract BigDecimal getMaxlat();

	/**
	 * Sets the value of the maxlat property.
	 * 
	 * @param maxLat
	 *     possible object is
	 *     {@link BigDecimal }
	 *     
	 */
	public abstract void setMaxlat(BigDecimal maxLat);
	
	/**
	 * Gets the value of the maxlon property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link BigDecimal }
	 *     
	 */
	public abstract BigDecimal getMaxlon();
	
	/**
	 * Gets the value of the maxlon property.
	 * 
	 * @param maxLon
	 *     possible object is
	 *     {@link BigDecimal }
	 *     
	 */
	public abstract void setMaxlon(BigDecimal maxLon);

	/**
	 * Gets the value of the minlat property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link BigDecimal }
	 *     
	 */
	public abstract BigDecimal getMinlat();
	
	/**
	 * Sets the value of the minlat property.
	 * 
	 * @param minLat
	 *     possible object is
	 *     {@link BigDecimal }
	 *     
	 */
	public abstract void setMinlat(BigDecimal minLat);

	/**
	 * Gets the value of the minlon property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link BigDecimal }
	 *     
	 */
	public abstract BigDecimal getMinlon();
	
	/**
	 * Gets the value of the minlon property.
	 * 
	 * @param minLon
	 *     possible object is
	 *     {@link BigDecimal }
	 *     
	 */
	public abstract void setMinlon(BigDecimal minLon);

}
