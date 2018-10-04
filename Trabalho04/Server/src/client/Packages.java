
package client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for packages complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="packages">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="flightTicketGoing" type="{http://tas.sd.br/}flightTicket" minOccurs="0"/>
 *         &lt;element name="flightTicketReturn" type="{http://tas.sd.br/}flightTicket" minOccurs="0"/>
 *         &lt;element name="accommodation" type="{http://tas.sd.br/}accommodation" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "packages", propOrder = {
    "flightTicketGoing",
    "flightTicketReturn",
    "accommodation"
})
public class Packages {

    protected FlightTicket flightTicketGoing;
    protected FlightTicket flightTicketReturn;
    protected Accommodation accommodation;

    /**
     * Gets the value of the flightTicketGoing property.
     * 
     * @return
     *     possible object is
     *     {@link FlightTicket }
     *     
     */
    public FlightTicket getFlightTicketGoing() {
        return flightTicketGoing;
    }

    /**
     * Sets the value of the flightTicketGoing property.
     * 
     * @param value
     *     allowed object is
     *     {@link FlightTicket }
     *     
     */
    public void setFlightTicketGoing(FlightTicket value) {
        this.flightTicketGoing = value;
    }

    /**
     * Gets the value of the flightTicketReturn property.
     * 
     * @return
     *     possible object is
     *     {@link FlightTicket }
     *     
     */
    public FlightTicket getFlightTicketReturn() {
        return flightTicketReturn;
    }

    /**
     * Sets the value of the flightTicketReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link FlightTicket }
     *     
     */
    public void setFlightTicketReturn(FlightTicket value) {
        this.flightTicketReturn = value;
    }

    /**
     * Gets the value of the accommodation property.
     * 
     * @return
     *     possible object is
     *     {@link Accommodation }
     *     
     */
    public Accommodation getAccommodation() {
        return accommodation;
    }

    /**
     * Sets the value of the accommodation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Accommodation }
     *     
     */
    public void setAccommodation(Accommodation value) {
        this.accommodation = value;
    }

}
