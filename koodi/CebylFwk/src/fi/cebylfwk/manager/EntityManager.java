package fi.cebylfwk.manager;

import fi.cebylfwk.Renderable;
import fi.cebylfwk.Updateable;
import fi.cebylfwk.component.Entity;

public class EntityManager extends ResourceManager<Entity> implements Renderable, Updateable {
    public EntityManager() {
        super();
    }
    
    public void addEntity(Entity e) {
        this.setResource(e, e);
    }
    
    public void update(long time) {
        for(Entity e : this.getResourceList()) {
            e.update(time);
        }
    }
    
    public void render(long time) {
        for(Entity e : this.getResourceList()) {
            if(e.isVisible() && e.isActive()) {
                e.render(time);
            }
        }        
    }
    
}
