
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
 *         &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="maxPrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="accommodation" type="{http://tas.sd.br/}accommodation" minOccurs="0"/>
 *         &lt;element name="numberOfGuests" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="citySource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cityDest" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="goingDay" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="goingMonth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="goingYear" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="isReturn" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="returnDay" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="returnMonth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="returnYear" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "quantity",
    "maxPrice",
    "accommodation",
    "numberOfGuests",
    "citySource",
    "cityDest",
    "goingDay",
    "goingMonth",
    "goingYear",
    "isReturn",
    "returnDay",
    "returnMonth",
    "returnYear"
})
public class PackageInterest {

    protected int quantity;
    protected float maxPrice;
    protected Accommodation accommodation;
    protected int numberOfGuests;
    protected String citySource;
    protected String cityDest;
    protected int goingDay;
    protected int goingMonth;
    protected int goingYear;
    protected boolean isReturn;
    protected int returnDay;
    protected int returnMonth;
    protected int returnYear;

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
     * Gets the value of the citySource property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCitySource() {
        return citySource;
    }

    /**
     * Sets the value of the citySource property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCitySource(String value) {
        this.citySource = value;
    }

    /**
     * Gets the value of the cityDest property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityDest() {
        return cityDest;
    }

    /**
     * Sets the value of the cityDest property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityDest(String value) {
        this.cityDest = value;
    }

    /**
     * Gets the value of the goingDay property.
     * 
     */
    public int getGoingDay() {
        return goingDay;
    }

    /**
     * Sets the value of the goingDay property.
     * 
     */
    public void setGoingDay(int value) {
        this.goingDay = value;
    }

    /**
     * Gets the value of the goingMonth property.
     * 
     */
    public int getGoingMonth() {
        return goingMonth;
    }

    /**
     * Sets the value of the goingMonth property.
     * 
     */
    public void setGoingMonth(int value) {
        this.goingMonth = value;
    }

    /**
     * Gets the value of the goingYear property.
     * 
     */
    public int getGoingYear() {
        return goingYear;
    }

    /**
     * Sets the value of the goingYear property.
     * 
     */
    public void setGoingYear(int value) {
        this.goingYear = value;
    }

    /**
     * Gets the value of the isReturn property.
     * 
     */
    public boolean isIsReturn() {
        return isReturn;
    }

    /**
     * Sets the value of the isReturn property.
     * 
     */
    public void setIsReturn(boolean value) {
        this.isReturn = value;
    }

    /**
     * Gets the value of the returnDay property.
     * 
     */
    public int getReturnDay() {
        return returnDay;
    }

    /**
     * Sets the value of the returnDay property.
     * 
     */
    public void setReturnDay(int value) {
        this.returnDay = value;
    }

    /**
     * Gets the value of the returnMonth property.
     * 
     */
    public int getReturnMonth() {
        return returnMonth;
    }

    /**
     * Sets the value of the returnMonth property.
     * 
     */
    public void setReturnMonth(int value) {
        this.returnMonth = value;
    }

    /**
     * Gets the value of the returnYear property.
     * 
     */
    public int getReturnYear() {
        return returnYear;
    }

    /**
     * Sets the value of the returnYear property.
     * 
     */
    public void setReturnYear(int value) {
        this.returnYear = value;
    }

}
