
package client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for packageInterest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="packageInterest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="flightTicketTo" type="{http://tas.sd.br/}flightTicket" minOccurs="0"/>
 *         &lt;element name="flightTicketFrom" type="{http://tas.sd.br/}flightTicket" minOccurs="0"/>
 *         &lt;element name="returnTicket" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="maxPrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="accommodation" type="{http://tas.sd.br/}accommodation" minOccurs="0"/>
 *         &lt;element name="numberOfGuests" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="clientName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "packageInterest", propOrder = {
    "flightTicketTo",
    "flightTicketFrom",
    "returnTicket",
    "quantity",
    "maxPrice",
    "accommodation",
    "numberOfGuests",
    "clientName"
})
public class PackageInterest {

    protected FlightTicket flightTicketTo;
    protected FlightTicket flightTicketFrom;
    protected boolean returnTicket;
    protected int quantity;
    protected float maxPrice;
    protected Accommodation accommodation;
    protected int numberOfGuests;
    protected String clientName;

    /**
     * Gets the value of the flightTicketTo property.
     * 
     * @return
     *     possible object is
     *     {@link FlightTicket }
     *     
     */
    public FlightTicket getFlightTicketTo() {
        return flightTicketTo;
    }

    /**
     * Sets the value of the flightTicketTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link FlightTicket }
     *     
     */
    public void setFlightTicketTo(FlightTicket value) {
        this.flightTicketTo = value;
    }

    /**
     * Gets the value of the flightTicketFrom property.
     * 
     * @return
     *     possible object is
     *     {@link FlightTicket }
     *     
     */
    public FlightTicket getFlightTicketFrom() {
        return flightTicketFrom;
    }

    /**
     * Sets the value of the flightTicketFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link FlightTicket }
     *     
     */
    public void setFlightTicketFrom(FlightTicket value) {
        this.flightTicketFrom = value;
    }

    /**
     * Gets the value of the returnTicket property.
     * 
     */
    public boolean isReturnTicket() {
        return returnTicket;
    }

    /**
     * Sets the value of the returnTicket property.
     * 
     */
    public void setReturnTicket(boolean value) {
        this.returnTicket = value;
    }

    /**
     * Gets the value of the quantity property.
     * 
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     */
    public void setQuantity(int value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the maxPrice property.
     * 
     */
    public float getMaxPrice() {
        return maxPrice;
    }

    /**
     * Sets the value of the maxPrice property.
     * 
     */
    public void setMaxPrice(float value) {
        this.maxPrice = value;
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

    /**
     * Gets the value of the numberOfGuests property.
     * 
     */
    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    /**
     * Sets the value of the numberOfGuests property.
     * 
     */
    public void setNumberOfGuests(int value) {
        this.numberOfGuests = value;
    }

    /**
     * Gets the value of the clientName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * Sets the value of the clientName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientName(String value) {
        this.clientName = value;
    }

}
