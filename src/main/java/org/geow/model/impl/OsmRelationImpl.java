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

import org.geow.model.IOsmMember;
import org.geow.model.IOsmNd;
import org.geow.model.IOsmRelation;
import org.geow.model.util.OsmComparator;



/**
 * Standard implementation of an osm relation.
 * @author Jan Schulte
 *
 */
public class OsmRelationImpl extends OsmElementImpl implements IOsmRelation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7667790068365217523L;
	
	private List<IOsmMember> members = new ArrayList<IOsmMember>();
	
	/**
	 * Default constructor with an empty member list and zeroes as properties. 
	 */
	public OsmRelationImpl() {
		super();
	}
	
	/**
	 * Creates a new relation with the given id and an empty memberlist.
	 * @param id
	 */
	public OsmRelationImpl(BigInteger id) {
		super();
		this.id = id;
	}	
	
	
	/**
	 * Creates a new relation from the given relation.
	 * @param rel
	 */
	public OsmRelationImpl(IOsmRelation rel){
		super(rel);
		this.members = new ArrayList<IOsmMember>(rel.getMember());		
	}
	

	@Override
	public <T extends IOsmMember> List<T> getMember() {
		return (List<T>) members;
	}
	
	@Override
	public <T extends IOsmMember> void setMember(List<T> member) {
		this.members = (List<IOsmMember>) member;
	}
	
	@Override
	public String toString() {
		return "OsmRelationImpl [id=" + id + ", timestamp=" + timestamp
				+ ", uid=" + uid + ", user=" + user + ", changeset=" + changeset + ", version=" + version
				+ ", visible=" + visible + ", tags=" + tags +  ", members=" + members + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(id,tags.size(),members.size());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OsmRelationImpl other = (OsmRelationImpl) obj;

		return OsmComparator.areRelationsEqual(this, other);
	}
	
}
