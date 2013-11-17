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
package org.geow.model.util;

import java.io.Serializable;

/**
 * Dictionary interface in order to map tag keys and values to an index. The primary use is to map tag keys and names to memory efficient indexes in order
 * to reduce the memory footprint of the osm elment. 
 * @author Jan Schulte
 *
 */
public interface IOsmTagMap extends Serializable {

	/**
	 * Checks whether the key is in the dictionary or not. 
	 * @param key The key to lookup in the dictionary
	 * @return True if the key is in the dictionary, false otherwise
	 */
	public boolean containsTagKey(String key);
	
	/**
	 * Checks whether the index of the key is in the dictionary or not.
	 * @param key The index of the key to lookup in the dictionary
	 * @return True if the key index is in the dictionary, false otherwise
	 */
	public boolean containsTagKey(short key);
	
	/**
	 * Checks whether the value is in the dictionary or not. 
	 * @param key The value to lookup in the dictionary
	 * @return True if the value is in the dictionary, false otherwise
	 */
	public boolean containsTagValue(String value);
	
	/**
	 * Checks whether the index of the value is in the dictionary or not.
	 * @param key The index of the value to lookup in the dictionary
	 * @return True if the value index is in the dictionary, false otherwise
	 */
	public boolean containsTagValue(short value);

	/**
	 * Looks up the key in the dictionary in order to return the key as text. 
	 * @param key The index of the key to look up
	 * @return The key as text or null if there is no key for the index
	 */
	public String getTagKey(short key);

	/**
	 * Looks up the index for the given key.
	 * @param key The key to get the index for
	 * @return The index or null if there is no such key in the dictionary
	 */
	public short getTagKey(String key);
	
	/**
	 * Looks up the value in the dictionary in order to return the value as text. 
	 * @param value The index of the key to look up
	 * @return The value as text or null if there is no value for the index
	 */
	public String getTagValue(short value);
	
	/**
	 * Looks up the index for the given value.
	 * @param value The value to get the index for
	 * @return The index or null if there is no such value in the dictionary
	 */
	public short getTagValue(String value);
	
}
