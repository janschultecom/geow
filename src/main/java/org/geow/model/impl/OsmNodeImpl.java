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
import java.math.BigInteger;
import java.util.Objects;

import org.geow.model.IOsmNode;
import org.geow.model.util.OsmComparator;


/**
 * Standard implementation of an osm node. 
 * @author Jan Schulte
 *
 */
public class OsmNodeImpl extends OsmElementImpl implements IOsmNode{

	
	private BigDecimal lat = BigDecimal.ZERO;
	private BigDecimal lon = BigDecimal.ZERO;
	
	public OsmNodeImpl() {
		super();
	}
	
	public OsmNodeImpl(BigInteger id){
		super();
		this.id = id;
	}
	
	@Override
	public BigDecimal getLat() {
		return lat;
	}

	@Override
	public BigDecimal getLon() {
		return lon;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public void setLon(BigDecimal lon) {
		this.lon = lon;
	}

	@Override
	public String toString() {
		return "OsmNodeImpl [id=" + id + ", lat=" + lat + ", lon=" + lon
				+ ", timestamp=" + timestamp + ", changeset=" + changeset
				+ ", tags=\"" + tags + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id,lon,lat);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OsmNodeImpl other = (OsmNodeImpl) obj;

		return OsmComparator.areNodesEqual(this, other);
	}

}
