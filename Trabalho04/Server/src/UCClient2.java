import client.UC;
import client.UCImplService;

public class UCClient2
{
   public static void main(String[] args) throws Exception
   {
      UCImplService ucis = new UCImplService();
      UC uc = ucis.getUCImplPort();
      System.out.printf("DC to DF: 37 DC = %f DF%n", uc.c2F(37.0));
      System.out.printf("CM to IN: 10 CM = %f IN%n", uc.cm2In(10));
      System.out.printf("DF to DC: 212 DF = %f DC%n", uc.f2C(212.0));
      System.out.printf("IN to CM: 10 IN = %f CM%n", uc.in2Cm(10));
   }
}