package fi.cebylfwk;

import fi.cebylfwk.component.Entity;

/**
 * Entities which wants to have collision enabled,
 * should implement this.
 *
 * @author      Jari Lybeck
 * @version     %I%, %G%
 */
public interface Collidible {
    /**
     * Collision check of the entities.
     * There should be logic what happens when
     * entity given as parameter collides with this entity.
     * 
     * @param e Entity which collides with this one.
     */
    public void onCollision(Entity e);
}
