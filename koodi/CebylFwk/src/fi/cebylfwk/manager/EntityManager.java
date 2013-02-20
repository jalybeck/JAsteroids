package fi.cebylfwk.manager;

import fi.cebylfwk.Collidible;
import fi.cebylfwk.Renderable;
import fi.cebylfwk.Updateable;
import fi.cebylfwk.component.Entity;
import fi.cebylfwk.graphics.Renderer;

import fi.cebylfwk.shape.Shape2D;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Manager which handles all the game entities.
 *
 * @author      Jari Lybeck
 * @version     %I%, %G%
 */
public class EntityManager extends ResourceManager<Entity> implements Renderable, Updateable {
    private Collection<Entity> removableElements;
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
        for(Entity e : this.getResourceList()) {   
            if(e.isVisible() && e.isActive()) {
                e.render(r,time);
            }
        }        
    }
    
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
    
    private boolean checkCollisionBetween(Entity a, Entity b) {
        Shape2D d = a.getShape();
        Shape2D e = b.getShape();
        
        return d.getBoundingObject().intersects(e.getBoundingObject());
    }
    
}
