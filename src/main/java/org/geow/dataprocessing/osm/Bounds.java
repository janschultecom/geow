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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.geow.model.IOsmBounds;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="maxlat" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="maxlon" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="minlat" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="minlon" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "bounds")
public class Bounds implements IOsmBounds {

    @XmlAttribute(name = "maxlat", required = true)
    protected BigDecimal maxlat;
    @XmlAttribute(name = "maxlon", required = true)
    protected BigDecimal maxlon;
    @XmlAttribute(name = "minlat", required = true)
    protected BigDecimal minlat;
    @XmlAttribute(name = "minlon", required = true)
    protected BigDecimal minlon;

    
    @Override
	public BigDecimal getMaxlat() {
        return maxlat;
    }


    @Override
    public void setMaxlat(BigDecimal value) {
        this.maxlat = value;
    }

    @Override
	public BigDecimal getMaxlon() {
        return maxlon;
    }

    @Override
    public void setMaxlon(BigDecimal value) {
        this.maxlon = value;
    }

    
    @Override
	public BigDecimal getMinlat() {
        return minlat;
    }

    @Override
    public void setMinlat(BigDecimal value) {
        this.minlat = value;
    }

    @Override
	public BigDecimal getMinlon() {
        return minlon;
    }

    @Override
    public void setMinlon(BigDecimal value) {
        this.minlon = value;
    }

	@Override
	public String toString() {
		return "Bounds [maxlat=" + maxlat + ", maxlon=" + maxlon + ", minlat="
				+ minlat + ", minlon=" + minlon + "]";
	}

	

}
