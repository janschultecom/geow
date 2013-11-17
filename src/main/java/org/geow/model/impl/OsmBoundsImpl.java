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
package org.geow.model.impl;

import java.math.BigDecimal;

import org.geow.model.IOsmBounds;


/**
 * Default implementation of IOsmBounds.
 * 
 * @author Jan Schulte
 *
 */
public class OsmBoundsImpl implements IOsmBounds {

	private BigDecimal maxLat;
	private BigDecimal minLon;
	private BigDecimal maxLon;
	private BigDecimal minLat;

	/**
	 * Creates an object with default maximum bounds [-180,90,180,-90].
	 */
	public OsmBoundsImpl(){
		this.minLon = new BigDecimal(-180.0);
		this.maxLat = new BigDecimal(90.0);
		this.maxLon = new BigDecimal(180.0);
		this.minLat = new BigDecimal(-90.0);
	}
	
	@Override
	public BigDecimal getMaxlat() {
		return maxLat;
	}

	@Override
	public BigDecimal getMaxlon() {
		return maxLon;
	}

	@Override
	public BigDecimal getMinlat() {
		return minLat;
	}

	@Override
	public BigDecimal getMinlon() {
		return minLon;
	}

	@Override
	public void setMaxlat(BigDecimal maxLat) {
		this.maxLat = maxLat;
	}

	@Override
	public void setMaxlon(BigDecimal maxLon) {
		this.maxLon = maxLon;
	}

	@Override
	public void setMinlat(BigDecimal minLat) {
		this.minLat = minLat;
	}

	@Override
	public void setMinlon(BigDecimal minLon) {
		this.minLon = minLon;
	}

}
