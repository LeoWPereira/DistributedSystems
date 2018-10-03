
package client;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "TravelAgencyServiceImplService", targetNamespace = "http://tas.sd.br/", wsdlLocation = "http://localhost:9901/TravelAgencyService?wsdl")
public class TravelAgencyServiceImplService
    extends Service
{

    private final static URL TRAVELAGENCYSERVICEIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException TRAVELAGENCYSERVICEIMPLSERVICE_EXCEPTION;
    private final static QName TRAVELAGENCYSERVICEIMPLSERVICE_QNAME = new QName("http://tas.sd.br/", "TravelAgencyServiceImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:9901/TravelAgencyService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        TRAVELAGENCYSERVICEIMPLSERVICE_WSDL_LOCATION = url;
        TRAVELAGENCYSERVICEIMPLSERVICE_EXCEPTION = e;
    }

    public TravelAgencyServiceImplService() {
        super(__getWsdlLocation(), TRAVELAGENCYSERVICEIMPLSERVICE_QNAME);
    }

    public TravelAgencyServiceImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), TRAVELAGENCYSERVICEIMPLSERVICE_QNAME, features);
    }

    public TravelAgencyServiceImplService(URL wsdlLocation) {
        super(wsdlLocation, TRAVELAGENCYSERVICEIMPLSERVICE_QNAME);
    }

    public TravelAgencyServiceImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, TRAVELAGENCYSERVICEIMPLSERVICE_QNAME, features);
    }

    public TravelAgencyServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public TravelAgencyServiceImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns TravelAgencyService
     */
    @WebEndpoint(name = "TravelAgencyServiceImplPort")
    public TravelAgencyService getTravelAgencyServiceImplPort() {
        return super.getPort(new QName("http://tas.sd.br/", "TravelAgencyServiceImplPort"), TravelAgencyService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns TravelAgencyService
     */
    @WebEndpoint(name = "TravelAgencyServiceImplPort")
    public TravelAgencyService getTravelAgencyServiceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://tas.sd.br/", "TravelAgencyServiceImplPort"), TravelAgencyService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (TRAVELAGENCYSERVICEIMPLSERVICE_EXCEPTION!= null) {
            throw TRAVELAGENCYSERVICEIMPLSERVICE_EXCEPTION;
        }
        return TRAVELAGENCYSERVICEIMPLSERVICE_WSDL_LOCATION;
    }

}
