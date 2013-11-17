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

import java.util.ArrayList;
import java.util.List;

import org.geow.model.IOsmTag;
import org.geow.model.impl.OsmTagImpl;

/**
 * A filter using a whitelist and a blacklist. 
 * @author Jan
 *
 */
public abstract class WhiteBlackListFilter implements IFilter{

	protected List<IOsmTag> whiteList;
	protected List<IOsmTag> blackList;

	public WhiteBlackListFilter(String[][] WHITE_LIST, String[][] BLACK_LIST) {
		super();
		whiteList = new ArrayList<IOsmTag>();
		for (String[] whiteItem : WHITE_LIST) {
			String key = whiteItem[0];
			String value = whiteItem[1];
			whiteList.add(new OsmTagImpl(key, value));
		}

		blackList = new ArrayList<IOsmTag>();
		for (String[] blackItem : BLACK_LIST) {
			String key = blackItem[0];
			String value = blackItem[1];
			blackList.add(new OsmTagImpl(key, value));
		}
	}

}