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
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import org.geow.model.IOsm;


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
 *         &lt;element ref="{}bounds"/>
 *         &lt;element ref="{}node" maxOccurs="unbounded"/>
 *         &lt;element ref="{}way" maxOccurs="unbounded"/>
 *         &lt;element ref="{}relation" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="generator" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "bounds",
    "node",
    "way",
    "relation"
})
@XmlRootElement(name = "osm")
public class Osm implements IOsm {

    @XmlElement(required = true)
    protected Bounds bounds;
    @XmlElement(required = true)
    protected List<Node> node;
    @XmlElement(required = true)
    protected List<Way> way;
    @XmlElement(required = true)
    protected List<Relation> relation;
    @XmlAttribute(name = "generator", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String generator;
    @XmlAttribute(name = "version", required = true)
    protected BigDecimal version;

    /* (non-Javadoc)
	 * @see com.majatour.model.external.osm.IOsm#getBounds()
	 */
    @Override
	public Bounds getBounds() {
        return bounds;
    }

    /**
     * Sets the value of the bounds property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bounds }
     *     
     */
    public void setBounds(Bounds value) {
        this.bounds = value;
    }

    /* (non-Javadoc)
	 * @see com.majatour.model.external.osm.IOsm#getNode()
	 */
    @Override
	public List<Node> getNode() {
        if (node == null) {
            node = new ArrayList<Node>();
        }
        return this.node;
    }

    /* (non-Javadoc)
	 * @see com.majatour.model.external.osm.IOsm#getWay()
	 */
    @Override
	public List<Way> getWay() {
        if (way == null) {
            way = new ArrayList<Way>();
        }
        return this.way;
    }

    /* (non-Javadoc)
	 * @see com.majatour.model.external.osm.IOsm#getRelation()
	 */
    @Override
	public List<Relation> getRelation() {
        if (relation == null) {
            relation = new ArrayList<Relation>();
        }
        return this.relation;
    }

    /* (non-Javadoc)
	 * @see com.majatour.model.external.osm.IOsm#getGenerator()
	 */
    @Override
	public String getGenerator() {
        return generator;
    }

    /**
     * Sets the value of the generator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGenerator(String value) {
        this.generator = value;
    }

    /* (non-Javadoc)
	 * @see com.majatour.model.external.osm.IOsm#getVersion()
	 */
    @Override
	public BigDecimal getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVersion(BigDecimal value) {
        this.version = value;
    }

}
