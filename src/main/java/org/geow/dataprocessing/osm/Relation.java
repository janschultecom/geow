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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.geow.model.IOsmMember;
import org.geow.model.IOsmObject;
import org.geow.model.IOsmRelation;
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
 *         &lt;element ref="{}member" maxOccurs="unbounded"/>
 *         &lt;element ref="{}tag" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="changeset" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="timestamp" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *       &lt;attribute name="uid" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="user" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
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
    "member",
    "tag"
})
@XmlRootElement(name = "relation")
public class Relation implements IOsmObject, IOsmRelation {

    @XmlElement(required = true)
    protected List<Member> member;
    //protected List<Member> member;
    @XmlElement(required = true)
    protected List<Tag> tag;
    //protected List<Tag> tag;
    @XmlAttribute(name = "changeset", required = true)
    protected BigInteger changeset;
    @XmlAttribute(name = "id", required = true)
    protected BigInteger id;
    @XmlAttribute(name = "timestamp", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String timestamp;
    @XmlAttribute(name = "uid", required = true)
    protected BigInteger uid;
    @XmlAttribute(name = "user", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String user;
    @XmlAttribute(name = "version", required = true)
    protected BigInteger version;
    @XmlAttribute(name = "visible", required = true)
    protected boolean visible;

    /* (non-Javadoc)
	 * @see com.majatour.model.external.osm.IOsmRelation#getMember()
	 */
    @Override
	public <T extends IOsmMember> List<T> getMember() {
        if (member == null) {
            member = new ArrayList<Member>();
        }
        return (List<T>) this.member;
    }
    
    @Override
	public <T extends IOsmMember> void setMember(List<T> member) {
		this.member = (List<Member>) member;		
	}

    /* (non-Javadoc)
	 * @see com.majatour.model.external.osm.IOsmRelation#getTag()
	 */
    @Override
	public <T extends IOsmTag> List<T> getTag() {
        if (tag == null) {
            tag = new ArrayList<Tag>();
        }
        return (List<T>) this.tag;
    }
    
    @Override
	public <T extends IOsmTag> void setTag(List<T> tags) {
		this.tag = (List<Tag>) tags;
	}


    /* (non-Javadoc)
	 * @see com.majatour.model.external.osm.IOsmRelation#getChangeset()
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
	 * @see com.majatour.model.external.osm.IOsmRelation#getId()
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
	 * @see com.majatour.model.external.osm.IOsmRelation#getTimestamp()
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
	 * @see com.majatour.model.external.osm.IOsmRelation#getUid()
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
	 * @see com.majatour.model.external.osm.IOsmRelation#getUser()
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
	 * @see com.majatour.model.external.osm.IOsmRelation#getVersion()
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
	 * @see com.majatour.model.external.osm.IOsmRelation#isVisible()
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
		return "Relation [id=" + id + ", timestamp=" + timestamp + "]";
	}
	
}
