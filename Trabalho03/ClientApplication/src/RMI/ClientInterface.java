/**
 ******************************************************************************
 * @file    ClientInterface.java
 * @author  Leonardo Winter Pereira
 * @author  Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    19 de set de 2018
 * @brief
 ******************************************************************************
 */

package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @name    ClientInterface
 * @brief   
 */
public interface ClientInterface extends Remote
{
    public void echo(String value)throws RemoteException;
}
