
package net.myrts.gcp.document;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the net.myrts.gcp.document package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GateDocument_QNAME = new QName("", "GateDocument");
    private final static QName _AnnotationTypeFeature_QNAME = new QName("", "Feature");
    private final static QName _TextWithNodesTypeNode_QNAME = new QName("", "Node");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: net.myrts.gcp.document
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GateDocumentType }
     * 
     */
    public GateDocumentType createGateDocumentType() {
        return new GateDocumentType();
    }

    /**
     * Create an instance of {@link FeatureType }
     * 
     */
    public FeatureType createFeatureType() {
        return new FeatureType();
    }

    /**
     * Create an instance of {@link ValueType }
     * 
     */
    public ValueType createValueType() {
        return new ValueType();
    }

    /**
     * Create an instance of {@link TextWithNodesType }
     * 
     */
    public TextWithNodesType createTextWithNodesType() {
        return new TextWithNodesType();
    }

    /**
     * Create an instance of {@link NodeType }
     * 
     */
    public NodeType createNodeType() {
        return new NodeType();
    }

    /**
     * Create an instance of {@link NameType }
     * 
     */
    public NameType createNameType() {
        return new NameType();
    }

    /**
     * Create an instance of {@link GateDocumentFeaturesType }
     * 
     */
    public GateDocumentFeaturesType createGateDocumentFeaturesType() {
        return new GateDocumentFeaturesType();
    }

    /**
     * Create an instance of {@link AnnotationSetType }
     * 
     */
    public AnnotationSetType createAnnotationSetType() {
        return new AnnotationSetType();
    }

    /**
     * Create an instance of {@link AnnotationType }
     * 
     */
    public AnnotationType createAnnotationType() {
        return new AnnotationType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GateDocumentType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "GateDocument")
    public JAXBElement<GateDocumentType> createGateDocument(GateDocumentType value) {
        return new JAXBElement<GateDocumentType>(_GateDocument_QNAME, GateDocumentType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FeatureType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Feature", scope = AnnotationType.class)
    public JAXBElement<FeatureType> createAnnotationTypeFeature(FeatureType value) {
        return new JAXBElement<FeatureType>(_AnnotationTypeFeature_QNAME, FeatureType.class, AnnotationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NodeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Node", scope = TextWithNodesType.class)
    public JAXBElement<NodeType> createTextWithNodesTypeNode(NodeType value) {
        return new JAXBElement<NodeType>(_TextWithNodesTypeNode_QNAME, NodeType.class, TextWithNodesType.class, value);
    }

}
