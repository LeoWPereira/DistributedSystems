/**
 ******************************************************************************
 * @file    ResourceList.java
 * @author  Leonardo Winter Pereira
 * @author  Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    16 de ago de 2018
 * @brief
 ******************************************************************************
 */

package Database;

import java.util.ArrayList;

import Database.Resource.Status;

/**
 * @name    ResourceList
 * @brief
 * 
 *
 */
public class ResourceList 
{
    /**
     * @name    resourceList
     * @brief
     */
    private ArrayList<Resource> resourceList = new ArrayList<Resource>();

    /**
     * @name    ResourceList
     * @brief
     */
    public ResourceList(int _qtyResources)
    {
        // Add resources to this list
        for(int i = 1; i <= _qtyResources; i++)
        {
            insertResource(i);
        }
        
    	return;
    }

    /**
     * @name    insertResource
     * @param	_idResource
     * @brief
     */
    public void insertResource(int	_idResource) 
    {
        this.resourceList.add(new Resource(_idResource));
        
        return;
    }

    /**
     * @name    removeResource
     * @brief
     * @param	_idResource
     */
    public void removeResource(int	_idResource) 
    {
        Resource resource = findResourceById(_idResource);
        
        this.resourceList.remove(resource);
        
        System.out.println("Recurso com ID " + resource.getId() + " removido da lista de recursos.\n");

        return;
    }

    /**
     * @name    findResourceById
     * @brief
     * @param	_idResource
     */
    public Resource findResourceById(int	_idResource) 
    {
        for(int i = 0; i < this.resourceList.size(); i++) 
        {
            if((this.resourceList.get(i).getId() == _idResource)) 
            {
                return this.resourceList.get(i);
            }
        }
        
        return null;
    }
    
     /**
     * @name    getResourceStatus
     * @brief
     * @param	_idResource
     */
    public Status getResourceStatus(int	_idResource) 
    {
        Resource resource = findResourceById(_idResource);
        
        if (resource != null) 
        {
            return resource.getResourceStatus();
        }
        
        return null;
    }

    /**
     * @name    setResourceStatus
     * @brief
     * @param   _idResource
     */
    public void setResourceStatus(int _idResource, Resource.Status _status) 
    {
        Resource resource = findResourceById(_idResource);
        
        if (resource != null) 
        {
            resource.setResourceStatus(_status);
        }
        
        return;
    }

    /**
     * @name    getResourceStatus
     * @brief
     * @param   _idResource
     */
    public int getResquestTimestamp(int _idResource) 
    {
        Resource resource = findResourceById(_idResource);
        
        if (resource != null) 
        {
            return resource.getRequestTimestamp();
        }
        
        return 0;
    }

    /**
     * @name    setResourceStatus
     * @brief
     * @param   _idResource
     */
    public void setRequestTimestamp(int _idResource, int _timestamp) 
    {
		Resource resource = findResourceById(_idResource);
		        
        if (resource != null) 
        {
        	resource.setRequestTimestamp(_timestamp);
        }
        
        return;
    }

    /**
     * @name    getResourceListSize
     * @brief
     */
    public int getResourceListSize() 
    {
        return this.resourceList.size();
    }
    
    /**
     * @name    listAvailableResources
     * @brief
     */
    public void listAvailableResources()
    {
    	System.out.println("\n********Recursos Disponíveis********");
    	
    	for(Resource resource : resourceList)
    	{
    		System.out.println("    - Recurso " + resource.getId());
    	}
    	
    	System.out.println("************************************\n");
    	
    	return;
    }
}
