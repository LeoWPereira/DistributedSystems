/**
 * https://www.javaworld.com/article/3209654/java-language/web-services-in-java-se-part-1-tools-overview.html
 * https://www.javaworld.com/article/3215966/java-language/web-services-in-java-se-part-2-creating-soap-web-services.html
 */

package ca.javajeff.uc;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface UC
{
   @WebMethod double c2f(double degrees);
   @WebMethod double cm2in(double cm);
   @WebMethod double f2c(double degrees);
   @WebMethod double in2cm(double in);
}