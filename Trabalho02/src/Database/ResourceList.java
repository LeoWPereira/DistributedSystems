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
 * @brief	This simple class is responsible for some very basic methods
 * 			regarding the list of resources
 */
public class ResourceList 
{
    /**
     * @name    resourceList
     * @brief	Array of resources
     * 			The resource class is defined in 'Resource.java' file
     */
    private ArrayList<Resource> resourceList = new ArrayList<Resource>();

    /**
     * @name    ResourceList
     * @brief	Default Constructor
     */
    public ResourceList(int _qtyResources)
    {
        for(int i = 1; i <= _qtyResources; i++)
        {
            insertResource(i);
        }
        
    	return;
    }

    /**
     * @name    insertResource
     * @brief	Insert a new resource to the resource list
     * @param	_idResource	: The resource unique ID
     */
    public void insertResource(int	_idResource) 
    {
        this.resourceList.add(new Resource(_idResource));
        
        return;
    }

    /**
     * @name    removeResource
     * @brief	If the resource has been found, remove it
     * @param	_idResource	: The resource unique ID
     */
    public void removeResource(int	_idResource) 
    {
        Resource resource = findResourceById(_idResource);
        
        if(resource != null)
        {
        	this.resourceList.remove(resource);
        }
        
        System.out.println("Recurso com ID " + resource.getId() + " removido da lista de recursos.\n");

        return;
    }

    /**
     * @name    findResourceById
     * @brief	If the resource has been found, return it, otherwise, it returns null
     * @param	_idResource	: The resource unique ID
     * @return	Resource in case of resource found
     * 			null otherwise
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
     * @brief	If the resource has been found, return it, otherwise, it returns null
     * @param	_idResource	: The resource unique ID
     * @return	Status in case of resource found
     * 			null otherwise
     */
    public Status getResourceStatus(int	_idResource) 
    {
        Resource resource = findResourceById(_idResource);
        
        if(resource != null)
        {
            return resource.getResourceStatus();
        }
        
        return null;
    }

    /**
     * @name    setResourceStatus
     * @brief	Set the current status to the resource in the parameter
     * @param   _idResource	: The resource unique ID
     * @param	_status		: The status of the Resource (see the enumerator for more details)
     */
    public void setResourceStatus(int 				_idResource, 
    							  Resource.Status	_status) 
    {
        Resource resource = findResourceById(_idResource);
        
        if (resource != null) 
        {
            resource.setResourceStatus(_status);
        }
        
        return;
    }

    /**
     * @name    getResourceListSize
     * @brief	Default Getter
     */
    public int getResourceListSize() 
    {
        return this.resourceList.size();
    }
    
    /**
     * @name    listAvailableResources
     * @brief	Just print to the user the available resources in a fancy way
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
