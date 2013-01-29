package fi.cebylfwk.manager;

import fi.cebylfwk.Resource;

import java.io.IOException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Base class for all resource manager classes.
 *
 * @author      Jari Lybeck
 * @version     %I%, %G%
 */

public abstract class ResourceManager {
    private HashMap<Object, Resource> resourceStore;
    private final Logger logger;

    protected ResourceManager() {
        logger = Logger.getLogger(this.getClass().getName());
        resourceStore = new HashMap<Object, Resource>();
    }

    protected void setResource(Object resourceKey, Resource res) {
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

    public int getResourceCount() {
        return this.resourceStore.size();
    }

    public void clear() {
        if (this.resourceStore != null && !this.resourceStore.isEmpty()) {
            while(!this.resourceStore.isEmpty()) {
                Iterator it = this.resourceStore.keySet().iterator();
                if (it.hasNext()) {
                    String key = (String)it.next();
                    this.removeResource(key);
                }
            }
            this.resourceStore.clear();
        }
    }

}
