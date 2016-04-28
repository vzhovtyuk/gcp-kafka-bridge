
package net.myrts.gcp.document;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for GateDocumentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GateDocumentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GateDocumentFeatures" type="{}GateDocumentFeaturesType"/>
 *         &lt;element name="TextWithNodes" type="{}TextWithNodesType"/>
 *         &lt;element name="AnnotationSet" type="{}AnnotationSetType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GateDocumentType", propOrder = {
    "gateDocumentFeatures",
    "textWithNodes",
    "annotationSet"
})
@XmlRootElement
public class GateDocumentType {

    @XmlElement(name = "GateDocumentFeatures", required = true)
    protected GateDocumentFeaturesType gateDocumentFeatures;
    @XmlElement(name = "TextWithNodes", required = true)
    protected TextWithNodesType textWithNodes;
    @XmlElement(name = "AnnotationSet", required = true)
    protected AnnotationSetType annotationSet;
    @XmlAttribute(name = "version")
    protected String version;

    /**
     * Gets the value of the gateDocumentFeatures property.
     * 
     * @return
     *     possible object is
     *     {@link GateDocumentFeaturesType }
     *     
     */
    public GateDocumentFeaturesType getGateDocumentFeatures() {
        return gateDocumentFeatures;
    }

    /**
     * Sets the value of the gateDocumentFeatures property.
     * 
     * @param value
     *     allowed object is
     *     {@link GateDocumentFeaturesType }
     *     
     */
    public void setGateDocumentFeatures(GateDocumentFeaturesType value) {
        this.gateDocumentFeatures = value;
    }

    /**
     * Gets the value of the textWithNodes property.
     * 
     * @return
     *     possible object is
     *     {@link TextWithNodesType }
     *     
     */
    public TextWithNodesType getTextWithNodes() {
        return textWithNodes;
    }

    /**
     * Sets the value of the textWithNodes property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextWithNodesType }
     *     
     */
    public void setTextWithNodes(TextWithNodesType value) {
        this.textWithNodes = value;
    }

    /**
     * Gets the value of the annotationSet property.
     * 
     * @return
     *     possible object is
     *     {@link AnnotationSetType }
     *     
     */
    public AnnotationSetType getAnnotationSet() {
        return annotationSet;
    }

    /**
     * Sets the value of the annotationSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnnotationSetType }
     *     
     */
    public void setAnnotationSet(AnnotationSetType value) {
        this.annotationSet = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

}
