//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.10.19 at 06:14:38 PM COT 
//


package co.com.edutva.edutvav1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NaturalEnvironmentInformationTableType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NaturalEnvironmentInformationTableType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NaturalEnvironmentInformation" type="{urn:tva:metadata:extended:2012}NaturalEnvironmentInformationType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NaturalEnvironmentInformationTableType", namespace = "urn:tva:metadata:extended:2012", propOrder = {
    "naturalEnvironmentInformation"
})
public class NaturalEnvironmentInformationTableType {

    @XmlElement(name = "NaturalEnvironmentInformation")
    protected List<NaturalEnvironmentInformationType> naturalEnvironmentInformation;

    /**
     * Gets the value of the naturalEnvironmentInformation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the naturalEnvironmentInformation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNaturalEnvironmentInformation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NaturalEnvironmentInformationType }
     * 
     * 
     */
    public List<NaturalEnvironmentInformationType> getNaturalEnvironmentInformation() {
        if (naturalEnvironmentInformation == null) {
            naturalEnvironmentInformation = new ArrayList<NaturalEnvironmentInformationType>();
        }
        return this.naturalEnvironmentInformation;
    }

}
