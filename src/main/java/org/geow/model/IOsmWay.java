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

import java.util.List;

public interface IOsmWay extends IOsmElement{

	/**
	 * Gets the list of node references.
	 * @return The list of node references
	 * {@link Nd } 
	 * 
	 */
	public abstract <T extends IOsmNd> List<T> getNd();
	
	/**
	 * Sets the list of node references. 
	 * @param nd The list of node references
	 * {@link Nd }
	 */
	public abstract <T extends IOsmNd> void setNd(List<T> nd);

}
