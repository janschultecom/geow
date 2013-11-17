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

import static org.geow.model.util.OsmExternalizer.writeDate;

import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.geow.model.IOsmElement;
import org.geow.model.IOsmMember;
import org.geow.model.IOsmNd;
import org.geow.model.IOsmNode;
import org.geow.model.IOsmRelation;
import org.geow.model.IOsmTag;
import org.geow.model.IOsmWay;


/**
 * Helper class for comparing osm elements in terms of equality.
 * @author Jan Schulte
 *
 */
public class OsmComparator {
	private static Logger logger = LogManager.getLogger(OsmComparator.class
			.getName());

	/**
	 * Checks whether the two nodes are equal. This method checks only if the properties are equal.
	 * Lightweight check for equality.
	 * @param n1 First node
	 * @param n2 Second node
	 * @return True if the nodes have the same properties
	 */
	public static boolean areNodesEqual(IOsmNode n1, IOsmNode n2) {
		if (n1 == null) {

			return false;
		}
		if (n2 == null) {
			return false;
		}

		boolean equal = arePropertiesEqual(n1, n2)
				&& areNodePropertiesEqual(n1, n2);

		if (!equal) {
			return false;
		}

		return equal;
	}

	/**
	 * Checks whether the two nodes are equal. This method checks if the properties are equal and that all tags are equal.
	 * @param n1 First node
	 * @param n2 Second node
	 * @return True if the nodes have the same properties and tags
	 */
	public static boolean areNodesDeepEqual(IOsmNode n1, IOsmNode n2) {
		if (n1 == null) {
			return false;
		}
		if (n2 == null) {
			return false;
		}

		boolean equal = areNodesEqual(n1, n2);
		if (!equal) {
			return false;
		}
		equal = areTagsDeepEqual(n1.<IOsmTag> getTag(), n2.<IOsmTag> getTag());

		return equal;
	}

	/**
	 * Checks whether the two ways are equal. This method checks only if the properties are equal.
	 * Lightweight check for equality.
	 * @param w1 First way
	 * @param w2 Second way
	 * @return True if the ways have the same properties
	 */
	public static boolean areWaysEqual(IOsmWay w1, IOsmWay w2) {
		if (w1 == null || w2 == null) {
			return false;
		}

		boolean equal = arePropertiesEqual(w1, w2);
		if (!equal) {
			return false;
		}
		return equal;
	}
	
	/**
	 * Checks whether the two ways are equal. This method checks if properties, tags and nds are equal.
	 * @param w1 First way
	 * @param w2 Second way
	 * @return True if the ways have the same properties, tags and nds.
	 */
	public static boolean areWaysDeepEqual(IOsmWay w1, IOsmWay w2) {
		if (w1 == null) {
			return false;
		}
		if (w2 == null) {
			return false;
		}

		boolean equal = areWaysEqual(w1, w2);
		if (!equal) {
			return false;
		}
		

		equal = areTagsEqual(w1.<IOsmTag> getTag(), w2.<IOsmTag> getTag());
		if (!equal) {
			return false;
		}
		
		equal = areNdsDeepEqual(w1.<IOsmNd> getNd(), w2.<IOsmNd> getNd());

		return equal;
	}

	/**
	 * Checks whether the two relations are equal. This method checks only if the properties are equal.
	 * Lightweight check for equality.
	 * @param r1 First relation
	 * @param r2 Second relation
	 * @return True if the relation have the same properties
	 */
	public static boolean areRelationsEqual(IOsmRelation r1, IOsmRelation r2) {
		if (r1 == null || r2 == null) {
			return false;
		}

		boolean equal = arePropertiesEqual(r1, r2);
		if (!equal) {
			return false;
		}
		
		return equal;
	}

	/**
	 * Checks whether the two relations are equal. This method checks if properties, tags and members are equal.
	 * @param r1 First relation
	 * @param r2 Second relation
	 * @return True if the ways have the same properties, tags and members.
	 */
	public static boolean areRelationsDeepEqual(IOsmRelation r1, IOsmRelation r2) {
		if (r1 == null) {
			return false;
		}
		if (r2 == null) {
			return false;
		}

		boolean equal = areRelationsEqual(r1, r2);
		if (!equal) {
			return false;
		}
		
		equal = areTagsEqual(r1.<IOsmTag> getTag(), r2.<IOsmTag> getTag());
		if (!equal) {
			return false;
		}
		
		equal = areMembersDeepEqual(r1.<IOsmMember> getMember(),
				r2.<IOsmMember> getMember());

		return equal;
	}

	
	/**
	 * Null-safe check of whether the two tag lists are equal in terms of size. 
	 * Lightweight check for equality.
	 * @param t1 First tag list
	 * @param t2 Second tag list
	 * @return True if the lists are of the same size
	 */
	public static boolean areTagsEqual(List<IOsmTag> t1, List<IOsmTag> t2) {
		boolean equal = true;
		if ((t1 == null && t2 != null) || (t1 != null && t2 == null)) {
			equal = false;
		} else if (t1 != null && t2 != null) {
			if (t1.size() != t2.size()) {
				logger.trace("Tag sizes differ {}", t1.size(), t2.size());
				equal = false;
			}

		}
		return equal;
	}
	/**
	 * Checks whether the two tag lists are equal. This method checks that all tags are the same in both lists (ignoring order).
	 * @param t1 First tag list
	 * @param t2 Second tag list
	 * @return True if the tag lists are exactly the same (however ignoring the order)
	 */
	public static boolean areTagsDeepEqual(List<IOsmTag> t1, List<IOsmTag> t2) {
		boolean equal = true;
		if ((t1 == null && t2 != null) || (t1 != null && t2 == null)) {
			equal = false;
		} else if (t1 != null && t2 != null) {
			if (t1.size() == t2.size()) {
				for (IOsmTag t : t1) {
					if (!t2.contains(t)) {
						equal = false;
					}
				}
			} else {
				logger.trace("Tag sizes differ {}", t1.size(), t2.size());
				equal = false;
			}

		}
		return equal;
	}

	/**
	 * Null-safe check of whether the two nd lists are equal in terms of size. 
	 * Lightweight check for equality.
	 * @param nd1 First nd list
	 * @param nd2 Second nd list
	 * @return True if the lists are of the same size
	 */
	public static boolean areNdsEqual(List<IOsmNd> nd1, List<IOsmNd> nd2) {
		boolean equal = true;
		if ((nd1 == null && nd2 != null) || (nd1 != null && nd2 == null)) {
			equal = false;
		} else if (nd1 != null && nd2 != null) {
			if (nd1.size() != nd2.size()) {
				logger.trace("Nd sizes differ {}", nd1.size(), nd2.size());
				equal = false;
			}

		}
		return equal;
	}
	
	/**
	 * Checks whether the two nd lists are equal. This method checks that all nds are the same in both lists (ignoring order).
	 * @param nd1 First nd list
	 * @param nd2 Second nd list
	 * @return True if the nd lists are exactly the same (however ignoring the order)
	 */
	public static boolean areNdsDeepEqual(List<IOsmNd> nd1, List<IOsmNd> nd2) {
		boolean equal = true;
		if ((nd1 == null && nd2 != null) || (nd1 != null && nd2 == null)) {
			equal = false;
		} else if (nd1 != null && nd2 != null) {
			if (nd1.size() == nd2.size()) {
				for (IOsmNd nd : nd1) {
					if (!nd2.contains(nd)) {
						equal = false;
					}
				}
			} else {
				logger.trace("Nd sizes differ {}", nd1.size(), nd2.size());
				equal = false;
			}

		}
		return equal;
	}

	/**
	 * Null-safe check of whether the two member lists are equal in terms of size. 
	 * Lightweight check for equality.
	 * @param members1 First member list
	 * @param members2 Second member list
	 * @return True if the lists are of the same size
	 */
	public static boolean areMembersEqual(List<IOsmMember> members1,
			List<IOsmMember> members2) {
		boolean equal = true;
		if ((members1 == null && members2 != null)
				|| (members1 != null && members2 == null)) {
			equal = false;
		} else if (members1 != null && members2 != null) {
			if (members1.size() != members2.size()) {
				logger.trace("Member sizes differ {}", members1.size(),
						members2.size());
				equal = false;
			}

		}
		return equal;
	}
	
	/**
	 * Checks whether the two member lists are equal. This method checks that all members are the same in both lists (ignoring order).
	 * @param members1 First member list
	 * @param members2 Second member list
	 * @return True if the member lists are exactly the same (however ignoring the order)
	 */
	public static boolean areMembersDeepEqual(List<IOsmMember> members1,
			List<IOsmMember> members2) {
		boolean equal = true;
		if ((members1 == null && members2 != null)
				|| (members1 != null && members2 == null)) {
			equal = false;
		} else if (members1 != null && members2 != null) {
			if (members1.size() == members2.size()) {
				for (IOsmMember member : members1) {
					if (!members2.contains(member)) {
						equal = false;
					}
				}
			} else {
				logger.trace("Member sizes differ {}", members1.size(),
						members2.size());
				equal = false;
			}

		}
		return equal;
	}
	
	/**
	 * Checks whether the properties of the two osm nodes are the same. Elements compared:
	 * 
	 * <ul>
	 * <li>lon</li>
	 * <li>lat</li>
	 * </ul>
	 * 
	 * @param n1 The first node
	 * @param n2 The second node
	 * @return True if the node properties for both nodes are the same
	 */
	public static boolean areNodePropertiesEqual(IOsmNode n1, IOsmNode n2) {
		boolean equal = Objects.equals(n1.getLon(), n2.getLon())
				&& Objects.equals(n1.getLat(), n2.getLat());

		if (!Objects.equals(n1.getLon(), n2.getLon())) {
			logger.trace("Lons differ {} {}", n1.getLon(), n2.getLon());
		}

		if (!Objects.equals(n1.getLat(), n2.getLat())) {
			logger.trace("Lats differ {} {}", n1.getLat(), n2.getLat());
		}
		return equal;
	}

	/**
	 * Checks whether the properties of the two osm elements are the same. Elements compared:
	 * 
	 * <ul>
	 * <li>id</li>
	 * <li>timestamp</li>
	 * <li>changeset</li>
	 * <li>uid</li>
	 * <li>user</li>
	 * <li>version</li>
	 * <li>visible</li>
	 * </ul>
	 * 
	 * @param e1 The first element
	 * @param e2 The second element
	 * @return True if the properties of both elements are the same
	 */
	public static boolean arePropertiesEqual(IOsmElement e1, IOsmElement e2) {
		boolean equal = Objects.equals(e1.getId(), e2.getId())
				&& areTimestampsEqual(e1, e2)
				&& Objects.equals(e1.getChangeset(), e2.getChangeset())
				&& Objects.equals(e1.getUid(), e2.getUid())
				&& Objects.equals(e1.getUser(), e2.getUser())
				&& Objects.equals(e1.getVersion(), e2.getVersion())
				&& Objects.equals(e1.isVisible(), e2.isVisible());

		if (logger.isTraceEnabled()) {
			if (!Objects.equals(e1.getId(), e2.getId())) {
				logger.trace("Ids differ {} {}", e1.getId(), e2.getId());
			}

			if (!Objects.equals(e1.getTimestamp(), e2.getTimestamp())) {
				logger.trace("Timestamp differ {} {}", e1.getTimestamp(),
						e2.getTimestamp());
			}

			if (!Objects.equals(e1.getChangeset(), e2.getChangeset())) {
				logger.trace("Changesets differ {} {}", e1.getChangeset(),
						e2.getChangeset());
			}

			if (!Objects.equals(e1.getUid(), e2.getUid())) {
				logger.trace("Uids differ {} {}", e1.getUid(), e2.getUid());
			}

			if (!Objects.equals(e1.getUser(), e2.getUser())) {
				logger.trace("Users differ {} {}", e1.getUser(), e2.getUser());
			}

			if (!Objects.equals(e1.getVersion(), e2.getVersion())) {
				logger.trace("Versions differ {} {}", e1.getVersion(),
						e2.getVersion());
			}

			if (!Objects.equals(e1.isVisible(), e2.isVisible())) {
				logger.trace("Visibles differ {} {}", e1.isVisible(),
						e2.isVisible());
			}
		}
		return equal;
	}

	/**
	 * Null-safe check whether the timestamps of the two elements are the same.
	 * @param e1 The first element
	 * @param e2 The second element
	 * @return True if both timestamps are the same
	 */
	public static boolean areTimestampsEqual(IOsmElement e1, IOsmElement e2) {
		if( e1 == null){
			return false;
		}
		
		if( e2 == null){
			return false;
		}
		return Objects.equals(writeDate(e1.getTimestamp()),writeDate(e2.getTimestamp()));
	}

	/**
	 * Null-safe check whether the tags are the same.  
	 * @param t1 The first tag
	 * @param t2 The second tag
	 * @return True if and only if both key and values are equal 
	 */
	public static boolean areTagsEqual(IOsmTag t1, IOsmTag t2) {
		if (t1 == null) {
			return false;
		}
		if (t2 == null) {
			return false;
		}
		boolean equal = Objects.equals(t1.getK(), t2.getK())
				&& Objects.equals(t1.getV(), t2.getV());
		return equal;
	}

	/**
	 * Checks whether the member refers to a way. 
	 * @param member The member to check
	 * @return True if the member refers to a way
	 */
	public static boolean memberIsWay(IOsmMember member) {
		if (member.getType().equals("way")) {
			return true;
		} else {
			return false;
		}

	}
	
	/**
	 * Checks whether the member refers to a relation. 
	 * @param member The member to check
	 * @return True if the member refers to a relation
	 */
	public static boolean memberIsRelation(IOsmMember member) {
		if (member.getType().equals("relation")) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checks whether the member refers to a node. 
	 * @param member The member to check
	 * @return True if the member refers to a node
	 */
	public static boolean memberIsNode(IOsmMember member) {
		if (member.getType().equals("node")) {
			return true;
		} else {
			return false;
		}
	}

}
