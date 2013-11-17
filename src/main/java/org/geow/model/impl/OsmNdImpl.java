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

import org.geow.model.IOsmNd;


public class OsmNdImpl implements IOsmNd {

	private BigInteger ref;

	public OsmNdImpl(){
		this.ref = BigInteger.ZERO;
	}
	
	public OsmNdImpl(BigInteger ref) {
		super();
		this.ref = ref;
	}

	public OsmNdImpl(long ref) {
		this.ref = BigInteger.valueOf(ref);
	}

	@Override
	public BigInteger getRef() {
		return this.ref;
	}

	public void setRef(BigInteger ref) {
		this.ref = ref;
	}

	@Override
	public int hashCode() {
		return ref.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OsmNdImpl other = (OsmNdImpl) obj;
		return Objects.equals(ref, other.getRef());
	}

	@Override
	public String toString() {
		return "[" + ref + "]";
	}

}
