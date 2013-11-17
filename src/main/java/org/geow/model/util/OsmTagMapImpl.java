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

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;

/**
 * Default implementation of the tag dictionary. 
 * The dictionary is implemented as a property file. 
 * For the keys check osm.featuremap.keys
 * For the values check osm.featuremap.values
 * @author Jan Schulte
 *
 */
public class OsmTagMapImpl implements IOsmTagMap {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5572506536639742281L;
	
	private static PropertiesConfiguration referenceKeyConfig;
	private static PropertiesConfiguration referenceValueConfig;

	private final BiMap<Short, String> referenceKeyMap;
	private final BiMap<String, Short> inverseReferenceKeyMap;
	private final BiMap<Short, String> referenceValueMap;
	private final BiMap<String, Short> inverseReferenceValueMap;
	
	static {
		try {
			referenceKeyConfig = new PropertiesConfiguration("osm.featuremap.keys");
		} catch (ConfigurationException e) {
			referenceKeyConfig = new PropertiesConfiguration();
		}
		try {
			referenceValueConfig = new PropertiesConfiguration("osm.featuremap.values");
		} catch (ConfigurationException e) {
			referenceValueConfig = new PropertiesConfiguration();
		}
	}

	/**
	 * Creates a new tag map using the default dictionary. 
	 */
	public OsmTagMapImpl() {
		
		Iterator<String> keyIt = referenceKeyConfig.getKeys();
		ImmutableBiMap.Builder<Short, String> builder = new ImmutableBiMap.Builder<Short, String>();
		while (keyIt.hasNext()) {
			String key = keyIt.next();
			if(!key.equals("")){
				short index = referenceKeyConfig.getShort(key);
				builder.put(index, key);
			}
		}
		referenceKeyMap = builder.build(); 
		inverseReferenceKeyMap = referenceKeyMap.inverse();

		
				
		Iterator<String> valueIt = referenceValueConfig.getKeys();
		builder = new ImmutableBiMap.Builder<Short, String>();
		while (valueIt.hasNext()) {
			String value = valueIt.next();
			if(!value.equals("")){
				short index = referenceValueConfig.getShort(value);
				builder.put(index, value);
			}
		}
		referenceValueMap = builder.build(); 
		inverseReferenceValueMap = referenceValueMap.inverse();
	}

	@Override
	public boolean containsTagKey(String key) {
		return inverseReferenceKeyMap.containsKey(key);
	}
	
	@Override
	public boolean containsTagKey(short key) {
		return referenceKeyMap.containsKey(key);
	}

	@Override
	public boolean containsTagValue(String value) {
		return inverseReferenceValueMap.containsKey(value);
	}

	@Override
	public boolean containsTagValue(short value) {
		return referenceValueMap.containsKey(value);
	}

	@Override
	public String getTagKey(short key) {
		return referenceKeyMap.get(key);
	}

	@Override
	public short getTagKey(String key){
		return inverseReferenceKeyMap.get(key);
	}
	
	@Override
	public String getTagValue(short value) {
		return referenceValueMap.get(value);
	}

	@Override
	public short getTagValue(String value){
		return inverseReferenceValueMap.get(value);
	}

	/**
	 * Returns a set of all keys within this dictionary.
	 * @return A set of index to key mappings
	 */
	public Set<Entry<Short, String>> getTagKeys(){
		return referenceKeyMap.entrySet();
	}
	
	/**
	 * Returns a set of all values within this dictionary.
	 * @return A set of index to value mappings
	 */
	public Set<Entry<Short, String>> getTagValues(){
		return referenceValueMap.entrySet();
	}

}
