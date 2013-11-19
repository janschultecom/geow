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

import org.apache.commons.collections.ListUtils;
import org.geow.model.IOsmElement;
import org.geow.model.IOsmTag;
import org.geow.model.impl.OsmTagImpl;

/**
 * A filter using a whitelist. All elements are rejected that don't have any tag
 * from the whitelist. Put it in other words, if the element has at least one
 * element from the whitelist, it gets accepted.
 * 
 * @author Jan
 * 
 */
public class WhiteListFilter implements IFilter {

	protected List<IOsmTag> whiteList;
	protected List<IOsmTag> blackList;

	public WhiteListFilter(String[][] WHITE_LIST) {
		super();
		whiteList = new ArrayList<IOsmTag>();
		for (String[] whiteItem : WHITE_LIST) {
			String key = whiteItem[0];
			String value = whiteItem[1];
			whiteList.add(new OsmTagImpl(key, value));
		}
	}

	@Override
	public boolean accept(IOsmElement element) {
		List<IOsmTag> tags = element.getTag();
		List whiteListIntersection = ListUtils.intersection(tags, whiteList);
		if (whiteListIntersection.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
}