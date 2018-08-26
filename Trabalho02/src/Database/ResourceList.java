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
    public ResourceList()
    {
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
        
        System.out.println("\nRecurso adicionado com ID " + _idResource);
        
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
     * @name    getResourceListSize
     * @brief
     */
    public int getResourceListSize() 
    {
        return this.resourceList.size();
    }
}
