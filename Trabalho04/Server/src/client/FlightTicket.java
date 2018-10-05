
package client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for flightTicket complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="flightTicket">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="source" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dest" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dateDay" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="dateMonth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="dateYear" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "flightTicket", propOrder = {
    "source",
    "dest",
    "dateDay",
    "dateMonth",
    "dateYear",
    "quantity",
    "price"
})
public class FlightTicket {

    protected String source;
    protected String dest;
    protected int dateDay;
    protected int dateMonth;
    protected int dateYear;
    protected int quantity;
    protected float price;

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSource(String value) {
        this.source = value;
    }

    /**
     * Gets the value of the dest property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDest() {
        return dest;
    }

    /**
     * Sets the value of the dest property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDest(String value) {
        this.dest = value;
    }

    /**
     * Gets the value of the dateDay property.
     * 
     */
    public int getDateDay() {
        return dateDay;
    }

    /**
     * Sets the value of the dateDay property.
     * 
     */
    public void setDateDay(int value) {
        this.dateDay = value;
    }

    /**
     * Gets the value of the dateMonth property.
     * 
     */
    public int getDateMonth() {
        return dateMonth;
    }

    /**
     * Sets the value of the dateMonth property.
     * 
     */
    public void setDateMonth(int value) {
        this.dateMonth = value;
    }

    /**
     * Gets the value of the dateYear property.
     * 
     */
    public int getDateYear() {
        return dateYear;
    }

    /**
     * Sets the value of the dateYear property.
     * 
     */
    public void setDateYear(int value) {
        this.dateYear = value;
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
     * Gets the value of the price property.
     * 
     */
    public float getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     */
    public void setPrice(float value) {
        this.price = value;
    }

}
