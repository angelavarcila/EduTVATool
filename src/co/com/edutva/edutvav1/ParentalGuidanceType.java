//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.10.19 at 06:14:38 PM COT 
//


package co.com.edutva.edutvav1;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ParentalGuidanceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ParentalGuidanceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="ParentalRating" type="{urn:tva:mpeg7:2008}ControlledTermUseType"/>
 *           &lt;element name="MinimumAge" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/>
 *         &lt;/choice>
 *         &lt;element name="Region" type="{urn:tva:mpeg7:2008}regionCode" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ParentalGuidanceType", namespace = "urn:tva:mpeg7:2008", propOrder = {
    "parentalRating",
    "minimumAge",
    "region"
})
@XmlSeeAlso({
    TVAParentalGuidanceType.class,
    co.com.edutva.edutvav1.ClassificationPreferencesType.ParentalGuidance.class
})
public class ParentalGuidanceType {

    @XmlElement(name = "ParentalRating")
    protected ControlledTermUseType parentalRating;
    @XmlElement(name = "MinimumAge")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger minimumAge;
    @XmlElement(name = "Region")
    protected List<String> region;

    /**
     * Gets the value of the parentalRating property.
     * 
     * @return
     *     possible object is
     *     {@link ControlledTermUseType }
     *     
     */
    public ControlledTermUseType getParentalRating() {
        return parentalRating;
    }

    /**
     * Sets the value of the parentalRating property.
     * 
     * @param value
     *     allowed object is
     *     {@link ControlledTermUseType }
     *     
     */
    public void setParentalRating(ControlledTermUseType value) {
        this.parentalRating = value;
    }

    /**
     * Gets the value of the minimumAge property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMinimumAge() {
        return minimumAge;
    }

    /**
     * Sets the value of the minimumAge property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMinimumAge(BigInteger value) {
        this.minimumAge = value;
    }

    /**
     * Gets the value of the region property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the region property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRegion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getRegion() {
        if (region == null) {
            region = new ArrayList<String>();
        }
        return this.region;
    }

}
