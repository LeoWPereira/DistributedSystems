/**
 ******************************************************************************
 * @file    TravelAgencyServicePublisher.java
 * @author  Leonardo Winter Pereira
 * @author  Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    01 de out de 2018
 * @brief
 ******************************************************************************
 */

import javax.xml.ws.Endpoint;

import br.sd.tas.TravelAgencyServiceImpl;

public class TravelAgencyServicePublisher
{
   public static void main(String[] args)
   {
      Endpoint.publish("http://localhost:9901/TravelAgencyService", new TravelAgencyServiceImpl());
   }
}