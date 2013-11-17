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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.geow.model.IOsmElement;
import org.geow.model.IOsmMember;
import org.geow.model.IOsmNd;
import org.geow.model.IOsmNode;
import org.geow.model.IOsmRelation;
import org.geow.model.IOsmTag;
import org.geow.model.IOsmWay;
import org.geow.model.impl.OsmMemberImpl;
import org.geow.model.impl.OsmNdImpl;
import org.geow.model.impl.OsmNodeImpl;
import org.geow.model.impl.OsmRelationImpl;
import org.geow.model.impl.OsmTagImpl;
import org.geow.model.impl.OsmWayImpl;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;


/**
 * Class for externalizing osm elements into binary representation. 
 * 
 * @author Jan Schulte
 *
 */
public class OsmExternalizer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7048848383730909179L;

	private static Logger logger = LogManager.getLogger(OsmExternalizer.class
			.getName());

	private IOsmTagMap tagMap;

	private static String NODE = "node";
	private static String WAY = "way";
	private static String RELATION = "relation";

	private static enum ELEMENT_TYPE { NODE, WAY, RELATION };
	
	private static final byte NODE_CODE = 0b00000000;
	private static final byte WAY_CODE = 0b00000001;
	private static final byte RELATION_CODE = 0b00000010;
	
	private static String INNER = "inner";
	private static String OUTER = "outer";

	private static final byte INNER_CODE = 0b00010000;
	private static final byte OUTER_CODE = 0b00100000;

    private static final DateTimeFormatter XML_DATE_INPUT_FORMAT =
        ISODateTimeFormat.dateTime();

    private static final DateTimeFormatter XML_DATE_OUTPUT_FORMAT =
        ISODateTimeFormat.dateTime().withZone(DateTimeZone.UTC);
    
    /**
     * Converts an long timestamp into an xml timestamp in format "2013-07-18T12:17:56Z".
     * @param timestamp The timestamp in long format
     * @return The timestamp as xml format
     */
    public static String readDate(long timestamp){
        DateTime dt = new DateTime(timestamp);
        return XML_DATE_OUTPUT_FORMAT.print(dt);
    }

    /**
     * Converts an xml time stamp to a long timestamp.
     * @param xmlTime The xml timestamp in format "2013-07-18T12:17:56Z"
     * @return The timestamp as a long timestamp
     */
    public static long writeDate(String xmlTime){
    	long millis = 0L;
    	try{
    		DateTime dateTime = XML_DATE_INPUT_FORMAT.parseDateTime(xmlTime);
    		millis = dateTime.getMillis();
    	}catch(IllegalArgumentException e){
    		millis = System.currentTimeMillis();
    	}
		return millis;
    }
    
    /**
     * Creates a new externalizer with a default tag dictionary. 
     * @see IOsmTagMap
     */
	public OsmExternalizer() {
		tagMap = new OsmTagMapImpl();
	}

	/**
	 * Creates a new externalizer with the given tag dictionary.
	 * @param tagMap The tag dictionary that shall be used when externalizing tags
	 */
	public OsmExternalizer(IOsmTagMap tagMap) {
		this.tagMap = tagMap;
	}
		
	/**
	 * Default method to serialize an osm element to a binary representation.	
	 * @param element The element to serialize
	 * @return A byte array with the binary representation
	 * @throws IOException
	 */
	public byte[] writeExternalElement(IOsmElement element) throws IOException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);

		if(element instanceof IOsmNode){
			writeExternalNode((IOsmNode) element, oos);
		}else if(element instanceof IOsmWay){
			writeExternalWay((IOsmWay) element, oos);
		}else if(element instanceof IOsmRelation){
			writeExternalRelation((IOsmRelation) element, oos);
		}else{
			oos.close();
			baos.close();
			throw new IOException("Element not a top level osm element, was "+element.getClass().getSimpleName());
		}
		oos.flush();

		byte[] serializedNode = baos.toByteArray();
		oos.close();
		baos.close();
		
		return serializedNode;
	}
	
	/**
	 * Default method for deserializing an osm element from a binary representation.
	 * @param bytes The bytes to deserialize
	 * @return An IOsmElement implementation of type IOsmNode, IOsmWay or IOsmRelation
	 * @throws IOException
	 */
	public IOsmElement readExternalElement(byte[] bytes) throws IOException{
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(
				bytes));
		
		IOsmElement element = readElementHeader(ois);
		if(element instanceof IOsmNode){
			element = readExternalNode(ois, (OsmNodeImpl) element);
		}else if(element instanceof IOsmWay){
			element = readExternalWay(ois, (OsmWayImpl) element);			
		}else if(element instanceof IOsmRelation){
			element = readExternalRelation(ois, (OsmRelationImpl) element);			
		}
		return element;
	}
	
	/**
	 * Serializes the node to the objectoutput.
	 * @param node The node to serialize
	 * @param out The objectoutput to which the node should be serialized
	 * @throws IOException
	 */
	public void writeExternalNode(IOsmNode node, ObjectOutput out)
			throws IOException {
		writeElementHeader(node,out);
		writeExternalProperties(node, out);
		out.writeDouble(node.getLon().doubleValue());
		out.writeDouble(node.getLat().doubleValue());
		writeExternalTags(node, out);
	}
		
	/**
	 * Deserializes a node from the objectinput.
	 * @param in The objectinput to deserialize the node from
	 * @return An implementation of IOsmNode
	 * @throws IOException
	 */
	public IOsmNode readExternalNode(ObjectInput in) throws IOException {
		OsmNodeImpl node = (OsmNodeImpl) readElementHeader(in);
		return readExternalNode(in, node);
	}
	
	/**
	 * Deserializes a node from the objectinput. This method takes a dummy node which is filled up with the data.
	 * @param in The objectinput to deserialize the node from
	 * @param node An (empty) node implementation to fill the data
	 * @return The node with the deserialized data
	 * @throws IOException
	 */
	public IOsmNode readExternalNode(ObjectInput in,IOsmNode node) throws IOException {
		readExternalProperties(in, node);
		node.setLon(BigDecimal.valueOf(in.readDouble()));
		node.setLat(BigDecimal.valueOf(in.readDouble()));
		readExternalTags(in, node);
		return node;
	}
	
	/** 
	 * Serializes the way to the objectoutput.
	 * @param way The way to serialize
	 * @param out The objectoutput to which the way should be serialized
	 * @throws IOException
	 */
	public void writeExternalWay(IOsmWay way, ObjectOutput out)
			throws IOException {		
		writeElementHeader(way,out);
		writeExternalProperties(way, out);
		writeExternalTags(way, out);
		int ndSize = 0;
		List<IOsmNd> nds = way.getNd();
		if (nds == null) {
			out.writeInt(ndSize);
		} else {
			ndSize = nds.size();
			out.writeInt(ndSize);
			for (IOsmNd nd : nds) {
				writeExternalNd(nd, out);
			}
		}
	}

	/**
	 * Deserializes a way from the objectinput.
	 * @param in The objectinput to deserialize the way from
	 * @return An implementation of IOsmWay
	 * @throws IOException
	 */
	public IOsmWay readExternalWay(ObjectInput in) throws IOException {
		OsmWayImpl way = (OsmWayImpl) readElementHeader(in);
		return readExternalWay(in, way);
	}
	
	/**
	 * Deserializes a way from the objectinput. This method takes a dummy way which is filled up with the data.
	 * @param in The objectinput to deserialize the way from
	 * @param way An (empty) way implementation to fill the data
	 * @return The way with the deserialized data
	 * @throws IOException
	 */
	public IOsmWay readExternalWay(ObjectInput in,IOsmWay way) throws IOException {
	
		readExternalProperties(in, way);
		readExternalTags(in, way);
		int ndSize = in.readInt();
		List<OsmNdImpl> nds = way.getNd();
		for (int i = 0; i < ndSize; i++) {
			nds.add(readExternalNd(in));
		}
		return way;
	}

	/** 
	 * Serializes the relation to the objectoutput.
	 * @param relation The relation to serialize
	 * @param out The objectoutput to which the relation should be serialized
	 * @throws IOException
	 */
	public void writeExternalRelation(IOsmRelation relation, ObjectOutput out)
			throws IOException {
		writeElementHeader(relation,out);
		writeExternalProperties(relation, out);
		writeExternalTags(relation, out);
		int ndSize = 0;
		List<IOsmMember> nds = relation.getMember();
		if (nds == null) {
			out.writeInt(ndSize);
		} else {
			ndSize = nds.size();
			out.writeInt(ndSize);
			for (IOsmMember nd : nds) {
				writeExternalMember(nd, out);
			}
		}
	}

	/**
	 * Deserializes a relation from the objectinput.
	 * @param in The objectinput to deserialize the relation from
	 * @return An implementation of IOsmRelation
	 * @throws IOException
	 */
	public IOsmRelation readExternalRelation(ObjectInput in) throws IOException {
		OsmRelationImpl relation = (OsmRelationImpl) readElementHeader(in);
		return readExternalRelation(in, relation);
	}
	
	/**
	 * Deserializes a relation from the objectinput. This method takes a dummy relation which is filled up with the data.
	 * @param in The objectinput to deserialize the relation from
	 * @param relation An (empty) relation implementation to fill the data
	 * @return The relation with the deserialized data
	 * @throws IOException
	 */
	public IOsmRelation readExternalRelation(ObjectInput in,IOsmRelation relation) throws IOException {

		readExternalProperties(in, relation);
		readExternalTags(in, relation);
		int ndSize = in.readInt();
		List<OsmMemberImpl> nds = relation.getMember();
		for (int i = 0; i < ndSize; i++) {
			nds.add(readExternalMember(in));
		}
		return relation;
	}
	
	/**
	 * Write meta information about the element to the objectoutput (e.g. the type of the element).
	 * @param element The element to serialize
	 * @param out The objectoutput to serialize to
	 * @throws IOException
	 */
	private void writeElementHeader(IOsmElement element, ObjectOutput out) throws IOException{
		if(element instanceof IOsmNode) {
			out.writeShort(ELEMENT_TYPE.NODE.ordinal());
		}else if(element instanceof IOsmWay){
			out.writeShort(ELEMENT_TYPE.WAY.ordinal());
		}else if(element instanceof IOsmRelation){
			out.writeShort(ELEMENT_TYPE.RELATION.ordinal());			
		}else{
			throw new IOException("Element should be of type "+ELEMENT_TYPE.values().toString()+", but was "+element.getClass().getSimpleName());
		}
	}
	
	/**
	 * Deserializes the header information from the objectinput and returns an IOsmElement implementation (IOsmNode, IOsmWay or IOsmRelation).
	 * 
	 * @param in The objectinput to deserialize from
	 * @return An implementation of IOsmNode, IOsmWay or IOsmRelation
	 * @throws IOException
	 */
	private IOsmElement readElementHeader(ObjectInput in) throws IOException {	
		int type = in.readShort();
		if(type > ELEMENT_TYPE.values().length){
			throw new IOException("Invalid type for IOsmElement, should be between 0 and "+ELEMENT_TYPE.values().length+", but was "+type);
		}
		
		ELEMENT_TYPE elementType = ELEMENT_TYPE.values()[type];
		IOsmElement element = null;
		switch(elementType){
			case NODE: 		element = new OsmNodeImpl();break;
			case WAY: 		element = new OsmWayImpl();break;
			case RELATION:	element = new OsmRelationImpl();break;
		} 
		return element;
	}

	/**
	 * Write the properties of the element to the objectoutput. 
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
	 * @param element The element of which the properties are taken to serialize
	 * @param out The objectoutput to serialize to
	 * @throws IOException
	 */
	private void writeExternalProperties(IOsmElement element, ObjectOutput out)
			throws IOException {
		out.writeLong(element.getId().longValue());
		
		String timestamp = element.getTimestamp();
		out.writeLong(writeDate(timestamp));
		
		BigInteger changeset = element.getChangeset();
		out.writeLong(changeset == null ? 0L : changeset.longValue());
		BigInteger uid = element.getUid();
		out.writeLong(uid == null ? 0L : uid.longValue());
		String user = element.getUser();
		out.writeUTF(user == null ? "default" : user);
		BigInteger version = element.getVersion();
		out.writeInt((int) (version == null ? 0L : version.longValue()));
		out.writeBoolean(element.isVisible());
	}

	/**
	 * Deserializes the properties from the objectouput and updates the element with the information.
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
	 * @param in The objectinput to deserialize from
	 * @param element The element to set the properties
	 * @throws IOException
	 */
	private void readExternalProperties(ObjectInput in, IOsmElement element)
			throws IOException {
		element.setId(BigInteger.valueOf(in.readLong()));
		element.setTimestamp(readDate(in.readLong()));
		
		element.setChangeset(BigInteger.valueOf(in.readLong()));
		element.setUid(BigInteger.valueOf(in.readLong()));
		element.setUser(in.readUTF());		
		element.setVersion(BigInteger.valueOf(in.readInt()));
		element.setVisible(in.readBoolean());
	}
	
	/**
	 * Writes the tags of the element to the objectoutput.
	 * @param element The element of which the properties are taken to serialize
	 * @param out The objectoutput to serialize to
	 * @throws IOException
	 */
	private void writeExternalTags(IOsmElement element, ObjectOutput out)
			throws IOException {
		List<IOsmTag> tags = element.getTag();
		int tagSize = 0;
		if (tags == null) {
			out.writeInt(tagSize);
		} else {
			tagSize = tags.size();
			out.writeInt(tagSize);
			for (IOsmTag tag : tags) {
				writeExternalTag(tag, out);
			}
		}
	}
	
	/**
	 * Deserializes the tags from the objectouput and updates the element with the tags.
	 * 
	 * @param in The objectinput to deserialize from
	 * @param element The element to set the tags
	 * @throws IOException
	 */
	private void readExternalTags(ObjectInput in, IOsmElement element)
			throws IOException {
		int tagSize = in.readInt();
		List<OsmTagImpl> tags = element.getTag();
		for (int i = 0; i < tagSize; i++) {
			tags.add(readExternalTag(in));
		}
	}

	/**
	 * Write the tag to the objectoutput.
	 * @param tag The tag to serialize
	 * @param out The objectouput to serialize to
	 * @throws IOException
	 */
	public void writeExternalTag(IOsmTag tag, ObjectOutput out)
			throws IOException {

		String key = tag.getK();

		boolean isKeyEncoded = tagMap.containsTagKey(key) ? true : false;

		out.writeBoolean(isKeyEncoded);

		if (isKeyEncoded) {
			out.writeShort(tagMap.getTagKey(key));
		} else {
			out.writeUTF(key);
		}

		String value = tag.getV();

		boolean isValueEncoded = tagMap.containsTagValue(value) ? true : false;

		out.writeBoolean(isValueEncoded);

		if (isValueEncoded) {
			out.writeShort(tagMap.getTagValue(value));
		} else {
			out.writeUTF(value);
		}
	}

	/**
	 * Deserializes the tag from the objectouput and returns the tag object.
	 * 
	 * @param in The objectinput to deserialize from
	 * @return The deserialized tag object. 
	 * @throws IOException
	 */
	public OsmTagImpl readExternalTag(ObjectInput in) throws IOException {

		boolean isKeyEncoded = in.readBoolean();
		String key = null;
		if (isKeyEncoded) {
			short keyInt = in.readShort();
			key = tagMap.containsTagKey(keyInt) ? tagMap.getTagKey(keyInt)
					: "no-value";
		} else {
			key = in.readUTF();
		}

		boolean isValueEncoded = in.readBoolean();
		String value = null;
		if (isValueEncoded) {
			short valueInt = in.readShort();
			value = tagMap.containsTagValue(valueInt) ? tagMap
				.getTagValue(valueInt) : "no-value";
		}else{
			value = in.readUTF();
		}

		return new OsmTagImpl(key, value);
	}

	/**
	 * Writes the nd to the objectoutput
	 * @param nd The nd to serialize
	 * @param out The objectoutput to serialize
	 * @throws IOException
	 */
	public void writeExternalNd(IOsmNd nd, ObjectOutput out) throws IOException {
		out.writeLong(nd.getRef().longValue());
	}

	/**
	 * Deserializes the nd from the objectouput and returns the nd object.
	 * 
	 * @param in The objectinput to deserialize from
	 * @return The deserialized nd object. 
	 * @throws IOException
	 */
	public OsmNdImpl readExternalNd(ObjectInput in) throws IOException {
		return new OsmNdImpl(in.readLong());
	}

	/**
	 * Write the member to the objectoutput.
	 * @param tag The member to serialize
	 * @param out The objectouput to serialize to
	 * @throws IOException
	 */
	public void writeExternalMember(IOsmMember member, ObjectOutput out)
			throws IOException {
		out.writeLong(member.getRef().longValue());
		String type = member.getType();
		byte typeCode = 0;
		if (type.equals(WAY)) {
			typeCode |= WAY_CODE;
		} else if (type.equals(RELATION)) {
			typeCode |= RELATION_CODE;
		} else {
			typeCode |= NODE_CODE;
		}
		String role = member.getRole();
		if (role.equals(INNER)) {
			typeCode |= INNER_CODE;
		} else {
			typeCode |= OUTER_CODE;
		}
		out.writeByte(typeCode);
	}


	/**
	 * Deserializes the member from the objectouput and returns the member object.
	 * 
	 * @param in The objectinput to deserialize from
	 * @return The deserialized nd object. 
	 * @throws IOException
	 */
	public OsmMemberImpl readExternalMember(ObjectInput in) throws IOException {
		long ref = in.readLong();
		byte typeCode = in.readByte();
		
		String type;
		if ((typeCode & WAY_CODE) == WAY_CODE) {
			type = WAY;
		} else if ((typeCode & RELATION_CODE) == RELATION_CODE) {
			type = RELATION;
		} else {
			type = NODE;
		}
		String role;
		if ((typeCode & INNER_CODE) == INNER_CODE) {
			role = INNER;
		} else {
			role = OUTER;
		}
		return new OsmMemberImpl(BigInteger.valueOf(ref), role, type);
	}
}
