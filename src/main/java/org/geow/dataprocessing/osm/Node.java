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
package org.geow.dataprocessing.osm;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.geow.model.IOsmNode;
import org.geow.model.IOsmObject;
import org.geow.model.IOsmTag;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}tag" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="changeset" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="lat" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="lon" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="timestamp" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *       &lt;attribute name="uid" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="user" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="visible" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "tag"
})
@XmlRootElement(name = "node")
public class Node implements IOsmObject, IOsmNode {

    protected List<Tag> tag;
    @XmlAttribute(name = "changeset", required = true)
    protected BigInteger changeset;
    @XmlAttribute(name = "id", required = true)
    protected BigInteger id;
    @XmlAttribute(name = "lat", required = true)
    protected BigDecimal lat;
    @XmlAttribute(name = "lon", required = true)
    protected BigDecimal lon;
    @XmlAttribute(name = "timestamp", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String timestamp;
    @XmlAttribute(name = "uid", required = true)
    protected BigInteger uid;
    @XmlAttribute(name = "user", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String user;
    @XmlAttribute(name = "version", required = true)
    protected BigInteger version;
    @XmlAttribute(name = "visible", required = true)
    protected boolean visible;

    /* (non-Javadoc)
	 * @see com.majatour.model.external.osm.IOsmNode#getTag()
	 */
    @Override
	public <T extends IOsmTag> List<T> getTag() {
        if (tag == null) {
            tag = new ArrayList<Tag>();
        }
        return (List<T>) this.tag;
    }

    @Override
    public <T extends IOsmTag> void setTag(List<T> tag){
    	this.tag = (List<Tag>) tag;
    }
    
    /* (non-Javadoc)
	 * @see com.majatour.model.external.osm.IOsmNode#getChangeset()
	 */
    @Override
	public BigInteger getChangeset() {
        return changeset;
    }

    /**
     * Sets the value of the changeset property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    @Override
    public void setChangeset(BigInteger value) {
        this.changeset = value;
    }

    /* (non-Javadoc)
	 * @see com.majatour.model.external.osm.IOsmNode#getId()
	 */
    @Override
	public BigInteger getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    @Override
    public void setId(BigInteger value) {
        this.id = value;
    }

    /* (non-Javadoc)
	 * @see com.majatour.model.external.osm.IOsmNode#getLat()
	 */
    @Override
	public BigDecimal getLat() {
        return lat;
    }

    /**
     * Sets the value of the lat property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    @Override
    public void setLat(BigDecimal value) {
        this.lat = value;
    }

    /* (non-Javadoc)
	 * @see com.majatour.model.external.osm.IOsmNode#getLon()
	 */
    @Override
	public BigDecimal getLon() {
        return lon;
    }

    /**
     * Sets the value of the lon property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    @Override
    public void setLon(BigDecimal value) {
        this.lon = value;
    }

    /* (non-Javadoc)
	 * @see com.majatour.model.external.osm.IOsmNode#getTimestamp()
	 */
    @Override
	public String getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the value of the timestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Override
    public void setTimestamp(String value) {
        this.timestamp = value;
    }

    /* (non-Javadoc)
	 * @see com.majatour.model.external.osm.IOsmNode#getUid()
	 */
    @Override
	public BigInteger getUid() {
        return uid;
    }

    /**
     * Sets the value of the uid property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    @Override
    public void setUid(BigInteger value) {
        this.uid = value;
    }

    /* (non-Javadoc)
	 * @see com.majatour.model.external.osm.IOsmNode#getUser()
	 */
    @Override
	public String getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Override
    public void setUser(String value) {
        this.user = value;
    }

    /* (non-Javadoc)
	 * @see com.majatour.model.external.osm.IOsmNode#getVersion()
	 */
    @Override
	public BigInteger getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    @Override
    public void setVersion(BigInteger value) {
        this.version = value;
    }

    /* (non-Javadoc)
	 * @see com.majatour.model.external.osm.IOsmNode#isVisible()
	 */
    @Override
	public boolean isVisible() {
        return visible;
    }

    /**
     * Sets the value of the visible property.
     * 
     */
    @Override
    public void setVisible(boolean value) {
        this.visible = value;
    }

	@Override
	public String toString() {
		return "Node [id=" + id + ", lat=" + lat + ", lon=" + lon
				+ ", timestamp=" + timestamp + "]";
	}

}
