//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.10.19 at 06:14:38 PM COT 
//


package co.com.edutva.edutvav1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GenreType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GenreType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:tva:metadata:2012}ControlledTermType">
 *       &lt;attribute name="type" default="main">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="main"/>
 *             &lt;enumeration value="secondary"/>
 *             &lt;enumeration value="other"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="metadataOriginIDRef" type="{urn:tva:metadata:2012}TVAIDRefType" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GenreType", namespace = "urn:tva:metadata:2012")
public class GenreType
    extends ControlledTermType
{

    @XmlAttribute(name = "type")
    protected String type;
    @XmlAttribute(name = "metadataOriginIDRef")
    protected String metadataOriginIDRef;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        if (type == null) {
            return "main";
        } else {
            return type;
        }
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the metadataOriginIDRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMetadataOriginIDRef() {
        return metadataOriginIDRef;
    }

    /**
     * Sets the value of the metadataOriginIDRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMetadataOriginIDRef(String value) {
        this.metadataOriginIDRef = value;
    }

}
