import client.AccommodationManager;
import client.TravelAgencyService;
import client.TravelAgencyServiceImplService;

public class UCClient2
{
   public static void main(String[] args) throws Exception
   {
	   TravelAgencyServiceImplService ucis = new TravelAgencyServiceImplService();
	   
	   TravelAgencyService uc = ucis.getTravelAgencyServiceImplPort();
      
	   System.out.println(uc.hello("Teste"));
	   
	   AccommodationManager list = uc.searchHotelByCity("Curitiba");
	   
	   System.out.println(list.getAccommodationList().get(0).getAccommodationName());
   }
}