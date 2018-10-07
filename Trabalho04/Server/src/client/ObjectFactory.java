
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

    private final static QName _RegisterHotelInterestByHotelResponse_QNAME = new QName("http://tas.sd.br/", "registerHotelInterestByHotelResponse");
    private final static QName _RegisterPassageInterestResponse_QNAME = new QName("http://tas.sd.br/", "registerPassageInterestResponse");
    private final static QName _LoadDBPassagesResponse_QNAME = new QName("http://tas.sd.br/", "loadDBPassagesResponse");
    private final static QName _SearchPackagesResponse_QNAME = new QName("http://tas.sd.br/", "searchPackagesResponse");
    private final static QName _SearchPackages_QNAME = new QName("http://tas.sd.br/", "searchPackages");
    private final static QName _BuyPassage_QNAME = new QName("http://tas.sd.br/", "buyPassage");
    private final static QName _GetPackageInterestListResponse_QNAME = new QName("http://tas.sd.br/", "getPackageInterestListResponse");
    private final static QName _GetTicketInterestList_QNAME = new QName("http://tas.sd.br/", "getTicketInterestList");
    private final static QName _BuyPassageResponse_QNAME = new QName("http://tas.sd.br/", "buyPassageResponse");
    private final static QName _BuyPackageResponse_QNAME = new QName("http://tas.sd.br/", "buyPackageResponse");
    private final static QName _SearchHotelByNameResponse_QNAME = new QName("http://tas.sd.br/", "searchHotelByNameResponse");
    private final static QName _ReserveHotel_QNAME = new QName("http://tas.sd.br/", "reserveHotel");
    private final static QName _SearchHotelByName_QNAME = new QName("http://tas.sd.br/", "searchHotelByName");
    private final static QName _SearchPassages_QNAME = new QName("http://tas.sd.br/", "searchPassages");
    private final static QName _SearchHotelByCityResponse_QNAME = new QName("http://tas.sd.br/", "searchHotelByCityResponse");
    private final static QName _GetAccommodationInterestListResponse_QNAME = new QName("http://tas.sd.br/", "getAccommodationInterestListResponse");
    private final static QName _ReserveHotelResponse_QNAME = new QName("http://tas.sd.br/", "reserveHotelResponse");
    private final static QName _LoadDBPassages_QNAME = new QName("http://tas.sd.br/", "loadDBPassages");
    private final static QName _LoadDBHotelsResponse_QNAME = new QName("http://tas.sd.br/", "loadDBHotelsResponse");
    private final static QName _InsertHotelEntryResponse_QNAME = new QName("http://tas.sd.br/", "insertHotelEntryResponse");
    private final static QName _RegisterHotelInterestResponse_QNAME = new QName("http://tas.sd.br/", "registerHotelInterestResponse");
    private final static QName _GetPackageInterestList_QNAME = new QName("http://tas.sd.br/", "getPackageInterestList");
    private final static QName _RegisterHotelInterestByHotel_QNAME = new QName("http://tas.sd.br/", "registerHotelInterestByHotel");
    private final static QName _BuyPackage_QNAME = new QName("http://tas.sd.br/", "buyPackage");
    private final static QName _GetTicketInterestListResponse_QNAME = new QName("http://tas.sd.br/", "getTicketInterestListResponse");
    private final static QName _RegisterHotelInterest_QNAME = new QName("http://tas.sd.br/", "registerHotelInterest");
    private final static QName _SearchPassagesResponse_QNAME = new QName("http://tas.sd.br/", "searchPassagesResponse");
    private final static QName _InsertPassageEntry_QNAME = new QName("http://tas.sd.br/", "insertPassageEntry");
    private final static QName _GetAccommodationInterestList_QNAME = new QName("http://tas.sd.br/", "getAccommodationInterestList");
    private final static QName _InsertHotelEntry_QNAME = new QName("http://tas.sd.br/", "insertHotelEntry");
    private final static QName _RegisterPackageInterest_QNAME = new QName("http://tas.sd.br/", "registerPackageInterest");
    private final static QName _LoadDBHotels_QNAME = new QName("http://tas.sd.br/", "loadDBHotels");
    private final static QName _RegisterPassageInterest_QNAME = new QName("http://tas.sd.br/", "registerPassageInterest");
    private final static QName _RegisterHotelInterestByCityResponse_QNAME = new QName("http://tas.sd.br/", "registerHotelInterestByCityResponse");
    private final static QName _RegisterPackageInterestResponse_QNAME = new QName("http://tas.sd.br/", "registerPackageInterestResponse");
    private final static QName _InsertPassageEntryResponse_QNAME = new QName("http://tas.sd.br/", "insertPassageEntryResponse");
    private final static QName _RegisterHotelInterestByCity_QNAME = new QName("http://tas.sd.br/", "registerHotelInterestByCity");
    private final static QName _SearchHotelByCity_QNAME = new QName("http://tas.sd.br/", "searchHotelByCity");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RegisterPassageInterest }
     * 
     */
    public RegisterPassageInterest createRegisterPassageInterest() {
        return new RegisterPassageInterest();
    }

    /**
     * Create an instance of {@link LoadDBHotels }
     * 
     */
    public LoadDBHotels createLoadDBHotels() {
        return new LoadDBHotels();
    }

    /**
     * Create an instance of {@link SearchHotelByCity }
     * 
     */
    public SearchHotelByCity createSearchHotelByCity() {
        return new SearchHotelByCity();
    }

    /**
     * Create an instance of {@link RegisterHotelInterestByCityResponse }
     * 
     */
    public RegisterHotelInterestByCityResponse createRegisterHotelInterestByCityResponse() {
        return new RegisterHotelInterestByCityResponse();
    }

    /**
     * Create an instance of {@link RegisterPackageInterestResponse }
     * 
     */
    public RegisterPackageInterestResponse createRegisterPackageInterestResponse() {
        return new RegisterPackageInterestResponse();
    }

    /**
     * Create an instance of {@link InsertPassageEntryResponse }
     * 
     */
    public InsertPassageEntryResponse createInsertPassageEntryResponse() {
        return new InsertPassageEntryResponse();
    }

    /**
     * Create an instance of {@link RegisterHotelInterestByCity }
     * 
     */
    public RegisterHotelInterestByCity createRegisterHotelInterestByCity() {
        return new RegisterHotelInterestByCity();
    }

    /**
     * Create an instance of {@link BuyPackage }
     * 
     */
    public BuyPackage createBuyPackage() {
        return new BuyPackage();
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
     * Create an instance of {@link RegisterHotelInterestResponse }
     * 
     */
    public RegisterHotelInterestResponse createRegisterHotelInterestResponse() {
        return new RegisterHotelInterestResponse();
    }

    /**
     * Create an instance of {@link GetPackageInterestList }
     * 
     */
    public GetPackageInterestList createGetPackageInterestList() {
        return new GetPackageInterestList();
    }

    /**
     * Create an instance of {@link RegisterHotelInterestByHotel }
     * 
     */
    public RegisterHotelInterestByHotel createRegisterHotelInterestByHotel() {
        return new RegisterHotelInterestByHotel();
    }

    /**
     * Create an instance of {@link RegisterPackageInterest }
     * 
     */
    public RegisterPackageInterest createRegisterPackageInterest() {
        return new RegisterPackageInterest();
    }

    /**
     * Create an instance of {@link InsertPassageEntry }
     * 
     */
    public InsertPassageEntry createInsertPassageEntry() {
        return new InsertPassageEntry();
    }

    /**
     * Create an instance of {@link GetAccommodationInterestList }
     * 
     */
    public GetAccommodationInterestList createGetAccommodationInterestList() {
        return new GetAccommodationInterestList();
    }

    /**
     * Create an instance of {@link InsertHotelEntry }
     * 
     */
    public InsertHotelEntry createInsertHotelEntry() {
        return new InsertHotelEntry();
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
     * Create an instance of {@link LoadDBHotelsResponse }
     * 
     */
    public LoadDBHotelsResponse createLoadDBHotelsResponse() {
        return new LoadDBHotelsResponse();
    }

    /**
     * Create an instance of {@link InsertHotelEntryResponse }
     * 
     */
    public InsertHotelEntryResponse createInsertHotelEntryResponse() {
        return new InsertHotelEntryResponse();
    }

    /**
     * Create an instance of {@link LoadDBPassages }
     * 
     */
    public LoadDBPassages createLoadDBPassages() {
        return new LoadDBPassages();
    }

    /**
     * Create an instance of {@link LoadDBPassagesResponse }
     * 
     */
    public LoadDBPassagesResponse createLoadDBPassagesResponse() {
        return new LoadDBPassagesResponse();
    }

    /**
     * Create an instance of {@link SearchPackagesResponse }
     * 
     */
    public SearchPackagesResponse createSearchPackagesResponse() {
        return new SearchPackagesResponse();
    }

    /**
     * Create an instance of {@link RegisterHotelInterestByHotelResponse }
     * 
     */
    public RegisterHotelInterestByHotelResponse createRegisterHotelInterestByHotelResponse() {
        return new RegisterHotelInterestByHotelResponse();
    }

    /**
     * Create an instance of {@link RegisterPassageInterestResponse }
     * 
     */
    public RegisterPassageInterestResponse createRegisterPassageInterestResponse() {
        return new RegisterPassageInterestResponse();
    }

    /**
     * Create an instance of {@link BuyPackageResponse }
     * 
     */
    public BuyPackageResponse createBuyPackageResponse() {
        return new BuyPackageResponse();
    }

    /**
     * Create an instance of {@link SearchPackages }
     * 
     */
    public SearchPackages createSearchPackages() {
        return new SearchPackages();
    }

    /**
     * Create an instance of {@link BuyPassage }
     * 
     */
    public BuyPassage createBuyPassage() {
        return new BuyPassage();
    }

    /**
     * Create an instance of {@link GetPackageInterestListResponse }
     * 
     */
    public GetPackageInterestListResponse createGetPackageInterestListResponse() {
        return new GetPackageInterestListResponse();
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
     * Create an instance of {@link FlightTicketManager }
     * 
     */
    public FlightTicketManager createFlightTicketManager() {
        return new FlightTicketManager();
    }

    /**
     * Create an instance of {@link PackageInterest }
     * 
     */
    public PackageInterest createPackageInterest() {
        return new PackageInterest();
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
     * Create an instance of {@link Packages }
     * 
     */
    public Packages createPackages() {
        return new Packages();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterHotelInterestByHotelResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "registerHotelInterestByHotelResponse")
    public JAXBElement<RegisterHotelInterestByHotelResponse> createRegisterHotelInterestByHotelResponse(RegisterHotelInterestByHotelResponse value) {
        return new JAXBElement<RegisterHotelInterestByHotelResponse>(_RegisterHotelInterestByHotelResponse_QNAME, RegisterHotelInterestByHotelResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link LoadDBPassagesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "loadDBPassagesResponse")
    public JAXBElement<LoadDBPassagesResponse> createLoadDBPassagesResponse(LoadDBPassagesResponse value) {
        return new JAXBElement<LoadDBPassagesResponse>(_LoadDBPassagesResponse_QNAME, LoadDBPassagesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchPackagesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "searchPackagesResponse")
    public JAXBElement<SearchPackagesResponse> createSearchPackagesResponse(SearchPackagesResponse value) {
        return new JAXBElement<SearchPackagesResponse>(_SearchPackagesResponse_QNAME, SearchPackagesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchPackages }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "searchPackages")
    public JAXBElement<SearchPackages> createSearchPackages(SearchPackages value) {
        return new JAXBElement<SearchPackages>(_SearchPackages_QNAME, SearchPackages.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPackageInterestListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "getPackageInterestListResponse")
    public JAXBElement<GetPackageInterestListResponse> createGetPackageInterestListResponse(GetPackageInterestListResponse value) {
        return new JAXBElement<GetPackageInterestListResponse>(_GetPackageInterestListResponse_QNAME, GetPackageInterestListResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link BuyPackageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "buyPackageResponse")
    public JAXBElement<BuyPackageResponse> createBuyPackageResponse(BuyPackageResponse value) {
        return new JAXBElement<BuyPackageResponse>(_BuyPackageResponse_QNAME, BuyPackageResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link LoadDBPassages }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "loadDBPassages")
    public JAXBElement<LoadDBPassages> createLoadDBPassages(LoadDBPassages value) {
        return new JAXBElement<LoadDBPassages>(_LoadDBPassages_QNAME, LoadDBPassages.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoadDBHotelsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "loadDBHotelsResponse")
    public JAXBElement<LoadDBHotelsResponse> createLoadDBHotelsResponse(LoadDBHotelsResponse value) {
        return new JAXBElement<LoadDBHotelsResponse>(_LoadDBHotelsResponse_QNAME, LoadDBHotelsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertHotelEntryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "insertHotelEntryResponse")
    public JAXBElement<InsertHotelEntryResponse> createInsertHotelEntryResponse(InsertHotelEntryResponse value) {
        return new JAXBElement<InsertHotelEntryResponse>(_InsertHotelEntryResponse_QNAME, InsertHotelEntryResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPackageInterestList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "getPackageInterestList")
    public JAXBElement<GetPackageInterestList> createGetPackageInterestList(GetPackageInterestList value) {
        return new JAXBElement<GetPackageInterestList>(_GetPackageInterestList_QNAME, GetPackageInterestList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterHotelInterestByHotel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "registerHotelInterestByHotel")
    public JAXBElement<RegisterHotelInterestByHotel> createRegisterHotelInterestByHotel(RegisterHotelInterestByHotel value) {
        return new JAXBElement<RegisterHotelInterestByHotel>(_RegisterHotelInterestByHotel_QNAME, RegisterHotelInterestByHotel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuyPackage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "buyPackage")
    public JAXBElement<BuyPackage> createBuyPackage(BuyPackage value) {
        return new JAXBElement<BuyPackage>(_BuyPackage_QNAME, BuyPackage.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertPassageEntry }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "insertPassageEntry")
    public JAXBElement<InsertPassageEntry> createInsertPassageEntry(InsertPassageEntry value) {
        return new JAXBElement<InsertPassageEntry>(_InsertPassageEntry_QNAME, InsertPassageEntry.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertHotelEntry }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "insertHotelEntry")
    public JAXBElement<InsertHotelEntry> createInsertHotelEntry(InsertHotelEntry value) {
        return new JAXBElement<InsertHotelEntry>(_InsertHotelEntry_QNAME, InsertHotelEntry.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterPackageInterest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "registerPackageInterest")
    public JAXBElement<RegisterPackageInterest> createRegisterPackageInterest(RegisterPackageInterest value) {
        return new JAXBElement<RegisterPackageInterest>(_RegisterPackageInterest_QNAME, RegisterPackageInterest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoadDBHotels }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "loadDBHotels")
    public JAXBElement<LoadDBHotels> createLoadDBHotels(LoadDBHotels value) {
        return new JAXBElement<LoadDBHotels>(_LoadDBHotels_QNAME, LoadDBHotels.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterHotelInterestByCityResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "registerHotelInterestByCityResponse")
    public JAXBElement<RegisterHotelInterestByCityResponse> createRegisterHotelInterestByCityResponse(RegisterHotelInterestByCityResponse value) {
        return new JAXBElement<RegisterHotelInterestByCityResponse>(_RegisterHotelInterestByCityResponse_QNAME, RegisterHotelInterestByCityResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterPackageInterestResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "registerPackageInterestResponse")
    public JAXBElement<RegisterPackageInterestResponse> createRegisterPackageInterestResponse(RegisterPackageInterestResponse value) {
        return new JAXBElement<RegisterPackageInterestResponse>(_RegisterPackageInterestResponse_QNAME, RegisterPackageInterestResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertPassageEntryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "insertPassageEntryResponse")
    public JAXBElement<InsertPassageEntryResponse> createInsertPassageEntryResponse(InsertPassageEntryResponse value) {
        return new JAXBElement<InsertPassageEntryResponse>(_InsertPassageEntryResponse_QNAME, InsertPassageEntryResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterHotelInterestByCity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tas.sd.br/", name = "registerHotelInterestByCity")
    public JAXBElement<RegisterHotelInterestByCity> createRegisterHotelInterestByCity(RegisterHotelInterestByCity value) {
        return new JAXBElement<RegisterHotelInterestByCity>(_RegisterHotelInterestByCity_QNAME, RegisterHotelInterestByCity.class, null, value);
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
