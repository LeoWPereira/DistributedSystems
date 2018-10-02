
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

    private final static QName _RegisterHotelInterestResponse_QNAME = new QName("http://tas.sd.br/", "registerHotelInterestResponse");
    private final static QName _HelloResponse_QNAME = new QName("http://tas.sd.br/", "helloResponse");
    private final static QName _NotifyTicketsInterestsResponse_QNAME = new QName("http://tas.sd.br/", "notifyTicketsInterestsResponse");
    private final static QName _RegisterPassageInterestResponse_QNAME = new QName("http://tas.sd.br/", "registerPassageInterestResponse");
    private final static QName _GetTicketInterestListResponse_QNAME = new QName("http://tas.sd.br/", "getTicketInterestListResponse");
    private final static QName _RegisterHotelInterest_QNAME = new QName("http://tas.sd.br/", "registerHotelInterest");
    private final static QName _SearchPassagesResponse_QNAME = new QName("http://tas.sd.br/", "searchPassagesResponse");
    private final static QName _GetUserCount_QNAME = new QName("http://tas.sd.br/", "getUserCount");
    private final static QName _BuyPassage_QNAME = new QName("http://tas.sd.br/", "buyPassage");
    private final static QName _GetTicketInterestList_QNAME = new QName("http://tas.sd.br/", "getTicketInterestList");
    private final static QName _BuyPassageResponse_QNAME = new QName("http://tas.sd.br/", "buyPassageResponse");
    private final static QName _GetAccommodationInterestList_QNAME = new QName("http://tas.sd.br/", "getAccommodationInterestList");
    private final static QName _Hello_QNAME = new QName("http://tas.sd.br/", "hello");
    private final static QName _GetUserCountResponse_QNAME = new QName("http://tas.sd.br/", "getUserCountResponse");
    private final static QName _SearchHotelByNameResponse_QNAME = new QName("http://tas.sd.br/", "searchHotelByNameResponse");
    private final static QName _ReserveHotel_QNAME = new QName("http://tas.sd.br/", "reserveHotel");
    private final static QName _SearchHotelByName_QNAME = new QName("http://tas.sd.br/", "searchHotelByName");
    private final static QName _SearchPassages_QNAME = new QName("http://tas.sd.br/", "searchPassages");
    private final static QName _SearchHotelByCityResponse_QNAME = new QName("http://tas.sd.br/", "searchHotelByCityResponse");
    private final static QName _NotifyTicketsInterests_QNAME = new QName("http://tas.sd.br/", "notifyTicketsInterests");
    private final static QName _RegisterPassageInterest_QNAME = new QName("http://tas.sd.br/", "registerPassageInterest");
    private final static QName _GetAccommodationInterestListResponse_QNAME = new QName("http://tas.sd.br/", "getAccommodationInterestListResponse");
    private final static QName _ReserveHotelResponse_QNAME = new QName("http://tas.sd.br/", "reserveHotelResponse");
    private final static QName _SearchHotelByCity_QNAME = new QName("http://tas.sd.br/", "searchHotelByCity");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NotifyTicketsInterests }
     * 
     */
    public NotifyTicketsInterests createNotifyTicketsInterests() {
        return new NotifyTicketsInterests();
    }

    /**
     * Create an instance of {@link RegisterPassageInterest }
     * 
     */
    public RegisterPassageInterest createRegisterPassageInterest() {
        return new RegisterPassageInterest();
    }

    /**
     * Create an instance of {@link GetAccommodationInterestListResponse }
     * 
     */
    public GetAccommodationInterestListResponse createGetAccommodationInterestListResponse() {
        return new GetAccommodationInterestListResponse();
    }

    /**
     * Create an instance of {@link ReserveHotelResponse }
     * 
     */
    public ReserveHotelResponse createReserveHotelResponse() {
        return new ReserveHotelResponse();
    }

    /**
     * Create an instance of {@link GetUserCountResponse }
     * 
     */
    public GetUserCountResponse createGetUserCountResponse() {
        return new GetUserCountResponse();
    }

    /**
     * Create an instance of {@link SearchHotelByNameResponse }
     * 
     */
    public SearchHotelByNameResponse createSearchHotelByNameResponse() {
        return new SearchHotelByNameResponse();
    }

    /**
     * Create an instance of {@link ReserveHotel }
     * 
     */
    public ReserveHotel createReserveHotel() {
        return new ReserveHotel();
    }

    /**
     * Create an instance of {@link SearchHotelByName }
     * 
     */
    public SearchHotelByName createSearchHotelByName() {
        return new SearchHotelByName();
    }

    /**
     * Create an instance of {@link SearchPassages }
     * 
     */
    public SearchPassages createSearchPassages() {
        return new SearchPassages();
    }

    /**
     * Create an instance of {@link SearchHotelByCityResponse }
     * 
     */
    public SearchHotelByCityResponse createSearchHotelByCityResponse() {
        return new SearchHotelByCityResponse();
    }

    /**
     * Create an instance of {@link SearchHotelByCity }
     * 
     */
    public SearchHotelByCity createSearchHotelByCity() {
        return new SearchHotelByCity();
    }

    /**
     * Create an instance of {@link GetTicketInterestListResponse }
     * 
     */
    public GetTicketInterestListResponse createGetTicketInterestListResponse() {
        return new GetTicketInterestListResponse();
    }

    /**
     * Create an instance of {@link RegisterHotelInterest }
     * 
     */
    public RegisterHotelInterest createRegisterHotelInterest() {
        return new RegisterHotelInterest();
    }

    /**
     * Create an instance of {@link SearchPassagesResponse }
     * 
     */
    public SearchPassagesResponse createSearchPassagesResponse() {
        return new SearchPassagesResponse();
    }

    /**
     * Create an instance of {@link GetUserCount }
     * 
     */
    public GetUserCount createGetUserCount() {
        return new GetUserCount();
    }

    /**
     * Create an instance of {@link RegisterHotelInterestResponse }
     * 
     */
    public RegisterHotelInterestResponse createRegisterHotelInterestResponse() {
        return new RegisterHotelInterestResponse();
    }

    /**
     * Create an instance of {@link HelloResponse }
     * 
     */
    public HelloResponse createHelloResponse() {
        return new HelloResponse();
    }

    /**
     * Create an instance of {@link NotifyTicketsInterestsResponse }
     * 
     */
    public NotifyTicketsInterestsResponse createNotifyTicketsInterestsResponse() {
        return new NotifyTicketsInterestsResponse();
    }

    /**
     * Create an instance of {@link RegisterPassageInterestResponse }
     * 
     */
    public RegisterPassageInterestResponse createRegisterPassageInterestResponse() {
        return new RegisterPassageInterestResponse();
    }

    /**
     * Create an instance of {@link Hello }
     * 
     */
    public Hello createHello() {
        return new Hello();
    }

    /**
     * Create an instance of {@link BuyPassage }
     * 
     */
    public BuyPassage createBuyPassage() {
        return new BuyPassage();
    }

    /**
     * Create an instance of {@link GetTicketInterestList }
     * 
     */
    public GetTicketInterestList createGetTicketInterestList() {
        return new GetTicketInterestList();
    }

    /**
     * Create an instance of {@link BuyPassageResponse }
     * 
     */
    public BuyPassageResponse createBuyPassageResponse() {
        return new BuyPassageResponse();
    }

    /**
     * Create an instance of {@link GetAccommodationInterestList }
     * 
     */
    public GetAccommodationInterestList createGetAccommodationInterestList() {
        return new GetAccommodationInterestList();
    }

    /**
     * Create an instance of {@link Date }
     * 
     */
    public Date createDate() {
        return new Date();
    }

    /**
     * Create an instance of {@link FlightTicketManager }
     * 
     */
    public FlightTicketManager createFlightTicketManager() {
        return new FlightTicketManager();
    }

    /**
     * Create an instance of {@link FlightTicket }
     * 
     */
    public FlightTicket createFlightTicket() {
        return new FlightTicket();
    }

    /**
     * Create an instance of {@link AccommodationManager }
     * 
     */
    public AccommodationManager createAccommodationManager() {
        return new AccommodationManager();
    }

    /**
     * Create an instance of {@link AccommodationInterest }
     * 
     */
    public AccommodationInterest createAccommodationInterest() {
        return new AccommodationInterest();
    }

    /**
     * Create an instance of {@link Accommodation }
     * 
     */
    public Accommodation createAccommodation() {
        return new Accommodation();
    }

    /**
     * Create an instance of {@link FlightTicketInterest }
     * 
     */
    public FlightTicketInterest createFlightTicketInterest() {
        return new FlightTicketInterest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterHotelInterestResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "registerHotelInterestResponse")
    public JAXBElement<RegisterHotelInterestResponse> createRegisterHotelInterestResponse(RegisterHotelInterestResponse value) {
        return new JAXBElement<RegisterHotelInterestResponse>(_RegisterHotelInterestResponse_QNAME, RegisterHotelInterestResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "helloResponse")
    public JAXBElement<HelloResponse> createHelloResponse(HelloResponse value) {
        return new JAXBElement<HelloResponse>(_HelloResponse_QNAME, HelloResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NotifyTicketsInterestsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "notifyTicketsInterestsResponse")
    public JAXBElement<NotifyTicketsInterestsResponse> createNotifyTicketsInterestsResponse(NotifyTicketsInterestsResponse value) {
        return new JAXBElement<NotifyTicketsInterestsResponse>(_NotifyTicketsInterestsResponse_QNAME, NotifyTicketsInterestsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterPassageInterestResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "registerPassageInterestResponse")
    public JAXBElement<RegisterPassageInterestResponse> createRegisterPassageInterestResponse(RegisterPassageInterestResponse value) {
        return new JAXBElement<RegisterPassageInterestResponse>(_RegisterPassageInterestResponse_QNAME, RegisterPassageInterestResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTicketInterestListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "getTicketInterestListResponse")
    public JAXBElement<GetTicketInterestListResponse> createGetTicketInterestListResponse(GetTicketInterestListResponse value) {
        return new JAXBElement<GetTicketInterestListResponse>(_GetTicketInterestListResponse_QNAME, GetTicketInterestListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterHotelInterest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "registerHotelInterest")
    public JAXBElement<RegisterHotelInterest> createRegisterHotelInterest(RegisterHotelInterest value) {
        return new JAXBElement<RegisterHotelInterest>(_RegisterHotelInterest_QNAME, RegisterHotelInterest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchPassagesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "searchPassagesResponse")
    public JAXBElement<SearchPassagesResponse> createSearchPassagesResponse(SearchPassagesResponse value) {
        return new JAXBElement<SearchPassagesResponse>(_SearchPassagesResponse_QNAME, SearchPassagesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserCount }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "getUserCount")
    public JAXBElement<GetUserCount> createGetUserCount(GetUserCount value) {
        return new JAXBElement<GetUserCount>(_GetUserCount_QNAME, GetUserCount.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuyPassage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "buyPassage")
    public JAXBElement<BuyPassage> createBuyPassage(BuyPassage value) {
        return new JAXBElement<BuyPassage>(_BuyPassage_QNAME, BuyPassage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTicketInterestList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "getTicketInterestList")
    public JAXBElement<GetTicketInterestList> createGetTicketInterestList(GetTicketInterestList value) {
        return new JAXBElement<GetTicketInterestList>(_GetTicketInterestList_QNAME, GetTicketInterestList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuyPassageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "buyPassageResponse")
    public JAXBElement<BuyPassageResponse> createBuyPassageResponse(BuyPassageResponse value) {
        return new JAXBElement<BuyPassageResponse>(_BuyPassageResponse_QNAME, BuyPassageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAccommodationInterestList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "getAccommodationInterestList")
    public JAXBElement<GetAccommodationInterestList> createGetAccommodationInterestList(GetAccommodationInterestList value) {
        return new JAXBElement<GetAccommodationInterestList>(_GetAccommodationInterestList_QNAME, GetAccommodationInterestList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Hello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "hello")
    public JAXBElement<Hello> createHello(Hello value) {
        return new JAXBElement<Hello>(_Hello_QNAME, Hello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserCountResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "getUserCountResponse")
    public JAXBElement<GetUserCountResponse> createGetUserCountResponse(GetUserCountResponse value) {
        return new JAXBElement<GetUserCountResponse>(_GetUserCountResponse_QNAME, GetUserCountResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchHotelByNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "searchHotelByNameResponse")
    public JAXBElement<SearchHotelByNameResponse> createSearchHotelByNameResponse(SearchHotelByNameResponse value) {
        return new JAXBElement<SearchHotelByNameResponse>(_SearchHotelByNameResponse_QNAME, SearchHotelByNameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReserveHotel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "reserveHotel")
    public JAXBElement<ReserveHotel> createReserveHotel(ReserveHotel value) {
        return new JAXBElement<ReserveHotel>(_ReserveHotel_QNAME, ReserveHotel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchHotelByName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "searchHotelByName")
    public JAXBElement<SearchHotelByName> createSearchHotelByName(SearchHotelByName value) {
        return new JAXBElement<SearchHotelByName>(_SearchHotelByName_QNAME, SearchHotelByName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchPassages }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "searchPassages")
    public JAXBElement<SearchPassages> createSearchPassages(SearchPassages value) {
        return new JAXBElement<SearchPassages>(_SearchPassages_QNAME, SearchPassages.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchHotelByCityResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "searchHotelByCityResponse")
    public JAXBElement<SearchHotelByCityResponse> createSearchHotelByCityResponse(SearchHotelByCityResponse value) {
        return new JAXBElement<SearchHotelByCityResponse>(_SearchHotelByCityResponse_QNAME, SearchHotelByCityResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NotifyTicketsInterests }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "notifyTicketsInterests")
    public JAXBElement<NotifyTicketsInterests> createNotifyTicketsInterests(NotifyTicketsInterests value) {
        return new JAXBElement<NotifyTicketsInterests>(_NotifyTicketsInterests_QNAME, NotifyTicketsInterests.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterPassageInterest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "registerPassageInterest")
    public JAXBElement<RegisterPassageInterest> createRegisterPassageInterest(RegisterPassageInterest value) {
        return new JAXBElement<RegisterPassageInterest>(_RegisterPassageInterest_QNAME, RegisterPassageInterest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAccommodationInterestListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "getAccommodationInterestListResponse")
    public JAXBElement<GetAccommodationInterestListResponse> createGetAccommodationInterestListResponse(GetAccommodationInterestListResponse value) {
        return new JAXBElement<GetAccommodationInterestListResponse>(_GetAccommodationInterestListResponse_QNAME, GetAccommodationInterestListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReserveHotelResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "reserveHotelResponse")
    public JAXBElement<ReserveHotelResponse> createReserveHotelResponse(ReserveHotelResponse value) {
        return new JAXBElement<ReserveHotelResponse>(_ReserveHotelResponse_QNAME, ReserveHotelResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchHotelByCity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "searchHotelByCity")
    public JAXBElement<SearchHotelByCity> createSearchHotelByCity(SearchHotelByCity value) {
        return new JAXBElement<SearchHotelByCity>(_SearchHotelByCity_QNAME, SearchHotelByCity.class, null, value);
    }

}
