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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.geow.model.IOsmNd;
import org.geow.model.IOsmWay;
import org.geow.model.util.OsmComparator;



/**
 * Standard implementation of an osm way.
 * @author Jan Schulte
 *
 */
public class OsmWayImpl extends OsmElementImpl implements IOsmWay {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1070332300878363370L;
	
	private List<IOsmNd> nds = new ArrayList<IOsmNd>();
	
	public OsmWayImpl(BigInteger id) {
		super();
		this.id = id;
	}


	/**
	 * Constructs an empty way with an empty nd list. 
	 */
	public OsmWayImpl() {
		super();
	}

	/**
	 * Creates a new way from the given way. 
	 * @param way
	 */
	public OsmWayImpl(IOsmWay way){
		super(way);
		
		this.nds = new ArrayList<IOsmNd>(way.getNd());		
	}
	

	@Override
	public <T extends IOsmNd> List<T> getNd() {
		return (List<T>) nds;
	}

	@Override
	public <T extends IOsmNd> void setNd(List<T> nd) {
		this.nds = (List<IOsmNd>) nd;
	}
	
	@Override
	public String toString() {
		return "OsmWayImpl [id=" + id + ", timestamp=" + timestamp
				+ ", uid=" + uid + ", user=" + user + ", changeset=" + changeset + ", version=" + version
				+ ", visible=" + visible + ", tags=" + tags + ", nds=" + nds + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(id,tags.size(),nds.size());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OsmWayImpl other = (OsmWayImpl) obj;

		return OsmComparator.areWaysEqual(this, other);
	}

}
