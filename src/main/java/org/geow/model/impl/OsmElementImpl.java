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

import org.geow.model.IOsmElement;
import org.geow.model.IOsmTag;


/**
 * Default implementation of IOsmElement.
 * 
 * @author Jan Schulte
 *
 */
public abstract class OsmElementImpl implements IOsmElement{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4587269158177049421L;
	
	protected List<IOsmTag> tags = new ArrayList<IOsmTag>();
	protected BigInteger id = BigInteger.ZERO;
	protected BigInteger uid = BigInteger.ZERO;
	protected String user = "";
	protected String timestamp = "";
	protected BigInteger version = BigInteger.ZERO;
	protected BigInteger changeset = BigInteger.ZERO;
	protected boolean visible = true;

	/**
	 * Default constructor with properties set to 0 and empty tag list.
	 */
	public OsmElementImpl() {
		super();
	}

	/**
	 * Creates a new osm element from the given element. 
	 * @param element
	 */
	public OsmElementImpl(IOsmElement element) {

		this.setId(element.getId());
		
		
		this.setTimestamp(element.getTimestamp());
		this.setChangeset(element.getChangeset());
		this.setUid(element.getUid());
		this.setUser(element.getUser());
		this.setVersion(element.getVersion());
		this.setVisible(element.isVisible());
		
		this.tags = new ArrayList<IOsmTag>(element.getTag());
	}

	@Override
	public <T extends IOsmTag> List<T> getTag() {		
		return ((List<T>) tags);
	}

	@Override
	public BigInteger getChangeset() {
		return changeset;
	}

	@Override
	public BigInteger getId() {
		return id;
	}

	@Override
	public String getTimestamp() {
		return timestamp;
	}

	@Override
	public BigInteger getUid() {
		return uid;
	}

	@Override
	public String getUser() {
		return user;
	}

	@Override
	public BigInteger getVersion() {
		return version;
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	@Override
	public void setChangeset(BigInteger changeset) {
		this.changeset = changeset;
	}

	@Override
	public void setId(BigInteger id) {
		this.id = id;
	}

	@Override
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public void setUid(BigInteger uid) {
		this.uid = uid;
	}

	@Override
	public void setUser(String user) {
		this.user = user;
	}

	@Override
	public void setVersion(BigInteger version) {
		this.version = version;
	}

	@Override
	public void setVisible(boolean visible) {
		this.visible = visible;
	}


	@Override
	public <T extends IOsmTag> void setTag(List<T> tags) {
		this.tags = (List<IOsmTag>) tags;
	}


}
