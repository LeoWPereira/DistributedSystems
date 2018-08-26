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
 * @brief
 * 
 *
 */
public class Resource 
{
    /**
     * @name    Status
     * @brief
     */
    public enum Status
    {
        FREE(new Integer('F')),
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
     * @brief
     */
    private int id;
    
    /**
     * @name    resourceStatus
     * @brief
     */
    private Status resourceStatus;

    /**
     * @name    Resource
     * @brief
     * @param	_id
     */
    public Resource(int		_id) 
    {
        this.id = _id;
        
        this.resourceStatus = Status.FREE;
        
        return;
    }

    /**
     * @name    getId
     * @brief   
     * @return
     */
    public int getId() 
    {
    	return this.id;
	}  

    /**
     * @name    getResourceStatus
     * @brief   
     * @return
     */
    public Status getResourceStatus()
    {
    	return this.resourceStatus;
    }

    /**
     * @name    setResourceStatus
     * @brief   
     */
    public void setResourceStatus(Status _resourceStatus)
    {
        this.resourceStatus = _resourceStatus;

        return;
    }
}
