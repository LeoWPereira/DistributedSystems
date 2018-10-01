
package client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the client package. 
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

    private final static QName _In2Cm_QNAME = new QName("http://uc.javajeff.ca/", "in2cm");
    private final static QName _Cm2InResponse_QNAME = new QName("http://uc.javajeff.ca/", "cm2inResponse");
    private final static QName _Cm2In_QNAME = new QName("http://uc.javajeff.ca/", "cm2in");
    private final static QName _F2CResponse_QNAME = new QName("http://uc.javajeff.ca/", "f2cResponse");
    private final static QName _C2F_QNAME = new QName("http://uc.javajeff.ca/", "c2f");
    private final static QName _C2FResponse_QNAME = new QName("http://uc.javajeff.ca/", "c2fResponse");
    private final static QName _F2C_QNAME = new QName("http://uc.javajeff.ca/", "f2c");
    private final static QName _In2CmResponse_QNAME = new QName("http://uc.javajeff.ca/", "in2cmResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link In2CmResponse }
     * 
     */
    public In2CmResponse createIn2CmResponse() {
        return new In2CmResponse();
    }

    /**
     * Create an instance of {@link C2F }
     * 
     */
    public C2F createC2F() {
        return new C2F();
    }

    /**
     * Create an instance of {@link C2FResponse }
     * 
     */
    public C2FResponse createC2FResponse() {
        return new C2FResponse();
    }

    /**
     * Create an instance of {@link F2C }
     * 
     */
    public F2C createF2C() {
        return new F2C();
    }

    /**
     * Create an instance of {@link F2CResponse }
     * 
     */
    public F2CResponse createF2CResponse() {
        return new F2CResponse();
    }

    /**
     * Create an instance of {@link Cm2In }
     * 
     */
    public Cm2In createCm2In() {
        return new Cm2In();
    }

    /**
     * Create an instance of {@link Cm2InResponse }
     * 
     */
    public Cm2InResponse createCm2InResponse() {
        return new Cm2InResponse();
    }

    /**
     * Create an instance of {@link In2Cm }
     * 
     */
    public In2Cm createIn2Cm() {
        return new In2Cm();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link In2Cm }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://uc.javajeff.ca/", name = "in2cm")
    public JAXBElement<In2Cm> createIn2Cm(In2Cm value) {
        return new JAXBElement<In2Cm>(_In2Cm_QNAME, In2Cm.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Cm2InResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://uc.javajeff.ca/", name = "cm2inResponse")
    public JAXBElement<Cm2InResponse> createCm2InResponse(Cm2InResponse value) {
        return new JAXBElement<Cm2InResponse>(_Cm2InResponse_QNAME, Cm2InResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Cm2In }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://uc.javajeff.ca/", name = "cm2in")
    public JAXBElement<Cm2In> createCm2In(Cm2In value) {
        return new JAXBElement<Cm2In>(_Cm2In_QNAME, Cm2In.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link F2CResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://uc.javajeff.ca/", name = "f2cResponse")
    public JAXBElement<F2CResponse> createF2CResponse(F2CResponse value) {
        return new JAXBElement<F2CResponse>(_F2CResponse_QNAME, F2CResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link C2F }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://uc.javajeff.ca/", name = "c2f")
    public JAXBElement<C2F> createC2F(C2F value) {
        return new JAXBElement<C2F>(_C2F_QNAME, C2F.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link C2FResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://uc.javajeff.ca/", name = "c2fResponse")
    public JAXBElement<C2FResponse> createC2FResponse(C2FResponse value) {
        return new JAXBElement<C2FResponse>(_C2FResponse_QNAME, C2FResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link F2C }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://uc.javajeff.ca/", name = "f2c")
    public JAXBElement<F2C> createF2C(F2C value) {
        return new JAXBElement<F2C>(_F2C_QNAME, F2C.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link In2CmResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://uc.javajeff.ca/", name = "in2cmResponse")
    public JAXBElement<In2CmResponse> createIn2CmResponse(In2CmResponse value) {
        return new JAXBElement<In2CmResponse>(_In2CmResponse_QNAME, In2CmResponse.class, null, value);
    }

}
