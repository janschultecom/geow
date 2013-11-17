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

import org.geow.model.IOsmTag;

import com.google.common.base.Objects;

public class OsmTagImpl implements IOsmTag {

	private String key = null;
	private String value = null;
	
	public OsmTagImpl(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public OsmTagImpl() {
		key = "";
		value = "";
	}

	@Override
	public String getK() {
		return this.key;
	}

	@Override
	public void setK(String key) {
		this.key = key;
	}

	@Override
	public String getV() {
		return this.value;
	}

	@Override
	public void setV(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(key, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IOsmTag other = (IOsmTag) obj;
		
		return Objects.equal(key, other.getK()) && Objects.equal(value, other.getV());
		
	}

	@Override
	public String toString() {
		return "[" + key + "," + value + "]";
	}
	

}
