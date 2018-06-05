//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.10.19 at 06:14:38 PM COT 
//


package co.com.edutva.edutvav1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RMPI-MBAndMType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RMPI-MBAndMType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AncillaryRMPI" type="{urn:tva:rmpi:2012}AncillaryRMPIType"/>
 *         &lt;element name="ExtendRights" type="{urn:tva:rmpi:2012}ExtendRightsType"/>
 *         &lt;element name="ReceivingDomainRights" type="{urn:tva:rmpi:2012}ReceivingDomainRightsType"/>
 *         &lt;element name="AnyDomainRights" type="{urn:tva:rmpi:2012}AnyDomainRightsType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RMPI-MBAndMType", namespace = "urn:tva:rmpi:2012", propOrder = {
    "ancillaryRMPI",
    "extendRights",
    "receivingDomainRights",
    "anyDomainRights"
})
@XmlSeeAlso({
    RMPIDescriptionType.class
})
public class RMPIMBAndMType {

    @XmlElement(name = "AncillaryRMPI", required = true)
    protected AncillaryRMPIType ancillaryRMPI;
    @XmlElement(name = "ExtendRights", required = true)
    protected ExtendRightsType extendRights;
    @XmlElement(name = "ReceivingDomainRights", required = true)
    protected ReceivingDomainRightsType receivingDomainRights;
    @XmlElement(name = "AnyDomainRights", required = true)
    protected AnyDomainRightsType anyDomainRights;

    /**
     * Gets the value of the ancillaryRMPI property.
     * 
     * @return
     *     possible object is
     *     {@link AncillaryRMPIType }
     *     
     */
    public AncillaryRMPIType getAncillaryRMPI() {
        return ancillaryRMPI;
    }

    /**
     * Sets the value of the ancillaryRMPI property.
     * 
     * @param value
     *     allowed object is
     *     {@link AncillaryRMPIType }
     *     
     */
    public void setAncillaryRMPI(AncillaryRMPIType value) {
        this.ancillaryRMPI = value;
    }

    /**
     * Gets the value of the extendRights property.
     * 
     * @return
     *     possible object is
     *     {@link ExtendRightsType }
     *     
     */
    public ExtendRightsType getExtendRights() {
        return extendRights;
    }

    /**
     * Sets the value of the extendRights property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtendRightsType }
     *     
     */
    public void setExtendRights(ExtendRightsType value) {
        this.extendRights = value;
    }

    /**
     * Gets the value of the receivingDomainRights property.
     * 
     * @return
     *     possible object is
     *     {@link ReceivingDomainRightsType }
     *     
     */
    public ReceivingDomainRightsType getReceivingDomainRights() {
        return receivingDomainRights;
    }

    /**
     * Sets the value of the receivingDomainRights property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReceivingDomainRightsType }
     *     
     */
    public void setReceivingDomainRights(ReceivingDomainRightsType value) {
        this.receivingDomainRights = value;
    }

    /**
     * Gets the value of the anyDomainRights property.
     * 
     * @return
     *     possible object is
     *     {@link AnyDomainRightsType }
     *     
     */
    public AnyDomainRightsType getAnyDomainRights() {
        return anyDomainRights;
    }

    /**
     * Sets the value of the anyDomainRights property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyDomainRightsType }
     *     
     */
    public void setAnyDomainRights(AnyDomainRightsType value) {
        this.anyDomainRights = value;
    }

}
