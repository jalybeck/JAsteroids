package fi.cebylfwk.manager;

import fi.cebylfwk.Collidible;
import fi.cebylfwk.Renderable;
import fi.cebylfwk.Updateable;
import fi.cebylfwk.component.Entity;
import fi.cebylfwk.graphics.Renderer;

import fi.cebylfwk.shape.Shape2D;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Manager which handles all the game entities.
 *
 * @author      Jari Lybeck
 * @version     %I%, %G%
 */
public class EntityManager extends ResourceManager<Entity> implements Renderable, Updateable {
    /**
     * Entities which should be removed after each frame.
     */
    private Collection<Entity> removableElements;
    
    /**
     * Colliding entities are hold here.
     */
    private List<Collidible> collidibles;
    
    public EntityManager() {
        super();
        removableElements = new ArrayList<Entity>();
        collidibles = new ArrayList<Collidible>();
    }
    
    public void addEntity(Entity e) {
        if(e instanceof Collidible) {
            collidibles.add((Collidible)e);
        }
        this.setResource(e, e);
    }
    
    /**
     * Updates all entities and checks collision against eachothers.
     * @param time
     */
    public void update(long time) {
        for(Entity e : this.getResourceList()) {
            if(!e.isActive()) {
               removableElements.add(e);
               continue;
            }
            
            if(e instanceof Collidible) {
                checkCollisionAgainstAllEntities((Collidible)e);
            }
            
            e.update(time);
        }
        
        if (this.getResourceList().removeAll(this.removableElements) && collidibles.removeAll(this.removableElements)) {
            this.removableElements.clear();
        }
        
    }
    
    /**
     * Renders all visible and active entities to screen.
     * 
     * @param r
     * @param time
     */
    public void render(Renderer r, long time) {
        //sortEntitiesByZIndex();
        for(Entity e : this.getResourceList()) {   
            if(e.isVisible() && e.isActive()) {
                e.render(r,time);
            }
        }        
    }
    
    /**
     * Checks collision against all entities.
     * 
     * @param e Collidible object which is checked against.
     */
    private void checkCollisionAgainstAllEntities(Collidible e) {
        for (Collidible d : collidibles) {
            Entity collEntity = (Entity) d;
            if (!collEntity.isActive() || e == collEntity || d == this) {
                continue;
            }

            if (checkCollisionBetween((Entity)e, collEntity)) {
                e.onCollision(collEntity);
            }
        }        
    }
    
    /**
     * Checks collision between two entities.
     * 
     * @param a
     * @param b
     * @return
     */
    private boolean checkCollisionBetween(Entity a, Entity b) {
        Shape2D d = a.getShape();
        Shape2D e = b.getShape();
        
        return d.getBoundingObject().intersects(e.getBoundingObject());
    }
    
    /**
     *  Sorts entities by zindex property.
     */
    private void sortEntitiesByZIndex() {
            Collections.sort(this.getResourceList(), new Comparator<Entity>() {

                public int compare(Entity o1, Entity o2) {
                    int val = 0;
                    if(o1.getZIndex() > o2.getZIndex())
                        val = -1;
                    else if(o1.getZIndex() < o2.getZIndex()) 
                        val = 1;
                    return val;
                }
        });
    }
    
}
