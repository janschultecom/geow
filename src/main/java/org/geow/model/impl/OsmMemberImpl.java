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
import java.util.Objects;

import org.geow.model.IOsmMember;


/**
 * Default implementation of IOsmMember.
 * 
 * @author Jan Schulte
 *
 */
public class OsmMemberImpl implements IOsmMember{

	private BigInteger ref;
	private String role;
	private String type;
	
	public OsmMemberImpl(){
		this.ref = BigInteger.ZERO;
		this.role = "";
		this.type = "";
	}
	
	public OsmMemberImpl(BigInteger ref) {
		super();
		this.ref = ref;
	}
	
	public OsmMemberImpl(long ref) {
		this.ref = BigInteger.valueOf(ref);
	}
	
	public OsmMemberImpl(BigInteger ref, String role, String type) {
		super();
		this.ref = ref;
		this.role = role;
		this.type = type;
	}
	
	@Override
	public BigInteger getRef() {
		return ref;
	}
	
	@Override
	public void setRef(BigInteger ref) {
		this.ref = ref;		
	}

	@Override
	public String getRole() {
		return role;
	}

	@Override
	public String getType() {
		return type;
	}
	
	@Override
	public int hashCode(){
		return Objects.hash(ref,role,type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OsmMemberImpl other = (OsmMemberImpl) obj;
		return Objects.equals(ref, other.getRef()) && Objects.equals(role, other.getRole()) && Objects.equals(type, other.getType());
	}

	@Override
	public String toString() {
		return "[" + ref + ","+role+","+type+"]";
	}

	
}
