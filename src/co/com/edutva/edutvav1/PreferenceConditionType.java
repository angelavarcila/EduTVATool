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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PreferenceConditionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PreferenceConditionType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:tva:mpeg7:2008}DType">
 *       &lt;sequence>
 *         &lt;element name="Place" type="{urn:tva:mpeg7:2008}PlaceType" minOccurs="0"/>
 *         &lt;element name="Time" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{urn:tva:mpeg7:2008}TimeType">
 *                 &lt;attribute name="recurrence" default="none">
 *                   &lt;simpleType>
 *                     &lt;union>
 *                       &lt;simpleType>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *                           &lt;enumeration value="none"/>
 *                           &lt;enumeration value="daily"/>
 *                           &lt;enumeration value="weekly"/>
 *                           &lt;enumeration value="monthly"/>
 *                           &lt;enumeration value="annually"/>
 *                         &lt;/restriction>
 *                       &lt;/simpleType>
 *                       &lt;simpleType>
 *                         &lt;restriction base="{urn:tva:mpeg7:2008}termReferenceType">
 *                         &lt;/restriction>
 *                       &lt;/simpleType>
 *                     &lt;/union>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="numOfRecurrences" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PreferenceConditionType", namespace = "urn:tva:mpeg7:2008", propOrder = {
    "place",
    "time"
})
@XmlSeeAlso({
    co.com.edutva.edutvav1.BrowsingPreferencesType.PreferenceCondition.class
})
public class PreferenceConditionType
    extends DType
{

    @XmlElement(name = "Place")
    protected PlaceType place;
    @XmlElement(name = "Time")
    protected List<PreferenceConditionType.Time> time;

    /**
     * Gets the value of the place property.
     * 
     * @return
     *     possible object is
     *     {@link PlaceType }
     *     
     */
    public PlaceType getPlace() {
        return place;
    }

    /**
     * Sets the value of the place property.
     * 
     * @param value
     *     allowed object is
     *     {@link PlaceType }
     *     
     */
    public void setPlace(PlaceType value) {
        this.place = value;
    }

    /**
     * Gets the value of the time property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the time property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTime().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PreferenceConditionType.Time }
     * 
     * 
     */
    public List<PreferenceConditionType.Time> getTime() {
        if (time == null) {
            time = new ArrayList<PreferenceConditionType.Time>();
        }
        return this.time;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{urn:tva:mpeg7:2008}TimeType">
     *       &lt;attribute name="recurrence" default="none">
     *         &lt;simpleType>
     *           &lt;union>
     *             &lt;simpleType>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
     *                 &lt;enumeration value="none"/>
     *                 &lt;enumeration value="daily"/>
     *                 &lt;enumeration value="weekly"/>
     *                 &lt;enumeration value="monthly"/>
     *                 &lt;enumeration value="annually"/>
     *               &lt;/restriction>
     *             &lt;/simpleType>
     *             &lt;simpleType>
     *               &lt;restriction base="{urn:tva:mpeg7:2008}termReferenceType">
     *               &lt;/restriction>
     *             &lt;/simpleType>
     *           &lt;/union>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="numOfRecurrences" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Time
        extends TimeType
    {

        @XmlAttribute(name = "recurrence")
        protected List<String> recurrence;
        @XmlAttribute(name = "numOfRecurrences")
        @XmlSchemaType(name = "positiveInteger")
        protected BigInteger numOfRecurrences;

        /**
         * Gets the value of the recurrence property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the recurrence property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRecurrence().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getRecurrence() {
            if (recurrence == null) {
                recurrence = new ArrayList<String>();
            }
            return this.recurrence;
        }

        /**
         * Gets the value of the numOfRecurrences property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getNumOfRecurrences() {
            return numOfRecurrences;
        }

        /**
         * Sets the value of the numOfRecurrences property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setNumOfRecurrences(BigInteger value) {
            this.numOfRecurrences = value;
        }

    }

}