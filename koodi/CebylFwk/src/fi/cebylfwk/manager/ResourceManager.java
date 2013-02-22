package fi.cebylfwk.manager;

import fi.cebylfwk.Resource;

import java.io.IOException;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/**
 * Base class for all resource manager classes.
 *
 * @author      Jari Lybeck
 * @version     %I%, %G%
 */

public abstract class ResourceManager<T extends Resource> {
    /**
     * Hashmap which holds all the resources.
     */
    private HashMap<Object, T> resourceStore;
    
    /**
     * Java logger interface.
     */
    private final Logger logger;

    protected ResourceManager() {
        logger = Logger.getLogger(this.getClass().getName());
        resourceStore = new HashMap<Object, T>();
    }

    protected void setResource(Object resourceKey, T res) {
        logger.finest("Creating new resource! " + resourceKey);
        logger.finest("-" + res.toString());
        if (resourceStore.containsKey(resourceKey)) {
            this.removeResource(resourceKey);
        }

        resourceStore.put(resourceKey, res);
    }

    protected Resource getResource(Object resourceKey) throws IOException {
        if (resourceStore.containsKey(resourceKey)) {
            logger.finest("Fetching resource from cache! " + resourceKey);
            return resourceStore.get(resourceKey);
        }

        return null;
    }

    protected void removeResource(Object resourceKey) {
        if (resourceStore.containsKey(resourceKey)) {
            logger.finest("Removing resource from cache! " + resourceKey);
            Resource oldRes = resourceStore.remove(resourceKey);
            if (oldRes != null)
                oldRes.release();
        }
    }

    protected List<T> getResourceList() {
        return new ArrayList<T>(resourceStore.values());
    }

    public int getResourceCount() {
        return resourceStore.size();
    }
    
    /**
     * Removes all the resources from the internal hashmap.
     * Also calls the resources release methods.
     * 
     * @see Resource#release()
     */
    public void clear() {
        if (resourceStore != null && !resourceStore.isEmpty()) {
            while(!resourceStore.isEmpty()) {
                Iterator it = resourceStore.keySet().iterator();
                if (it.hasNext()) {
                    Object key = it.next();
                    removeResource(key);
                }
            }
            resourceStore.clear();
        }
    }

}
