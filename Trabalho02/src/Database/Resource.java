/**
 ******************************************************************************
 * @file    Resource.java
 * @author  Leonardo Winter Pereira
 * @author  Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    16 de ago de 2018
 * @brief
 ******************************************************************************
 */

package Database;

/**
 * @name    Resource
 * @brief	Really simple class responsible the determine
 * 			the available statuses options and the status for each
 * 			available resource.
 */
public class Resource 
{
    /**
     * @name    Status
     * @brief	Enumerator responsible to determine
     * 			the available status options for resources.
     * 			This is a fancy way of using constant values inside the project
     */
    public enum Status
    {
        RELEASED(new Integer('R')),
        HELD(new Integer('H')),
    	WANTED(new Integer('W'));
        
        private final byte byteValue;
        
        private Status(Integer _integerValue)
        {
            this.byteValue = _integerValue.byteValue();
            
            return;
        }
        
        public byte getByteValue()
        {
            return this.byteValue;
        }
    }

    /**
     * @name    id
     * @brief	The resource ID
     */
    private int id;
    
    /**
     * @name    resourceStatus
     * @brief	The current status of the resource.
     * 			See the Status enumerator for available options
     */
    private Status resourceStatus;

    /**
     * @name    requestTimestamp
     * @brief
     */
    private int requestTimestamp;

    /**
     * @name    Resource
     * @brief	Class constructor
     * @param	_id	: id of the resource to be configured
     */
    public Resource(int		_id) 
    {
        this.id = _id;
        
        this.resourceStatus = Status.RELEASED;

        this.requestTimestamp = 0;
        
        return;
    }

    /**
     * @name    getId
     * @brief   Default getter
     */
    public int getId() 
    {
    	return this.id;
	}  

    /**
     * @name    getResourceStatus
     * @brief   Default getter
     */
    public Status getResourceStatus()
    {
    	return this.resourceStatus;
    }

    /**
     * @name    setResourceStatus
     * @brief   Default setter
     * @param	_resourceStatus
     */
    public void setResourceStatus(Status _resourceStatus)
    {
        this.resourceStatus = _resourceStatus;

        return;
    }

    /**
     * @name    getRequestTimestamp
     * @brief   
     */
    public int getRequestTimestamp()
    {
        return this.requestTimestamp;
    }

    /**
     * @name    setRequestTimestamp
     * @brief   
     */
    public void setRequestTimestamp(int _requestTimestamp)
    {
        this.requestTimestamp = _requestTimestamp;

        return;
    }
}
