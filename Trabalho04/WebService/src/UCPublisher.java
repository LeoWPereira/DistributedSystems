

import javax.xml.ws.Endpoint;

import ca.javajeff.uc.UCImpl;

public class UCPublisher
{
   public static void main(String[] args)
   {
      Endpoint.publish("http://localhost:9901/UC", new UCImpl());
   }
}