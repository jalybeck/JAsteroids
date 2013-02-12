package fi.cebylfwk.component;

import fi.cebylfwk.Renderable;
import fi.cebylfwk.Resource;
import fi.cebylfwk.Updateable;
import fi.cebylfwk.math.Point2D;
import fi.cebylfwk.math.Vector2D;
import fi.cebylfwk.shape.Shape2D;

/**
 * Representation of the game object.
 * Every game object must implement this interface.
 * All entities will be registered to entitymanager.
 *
 * @author      Jari Lybeck
 * @version     %I%, %G%
 */
public interface Entity extends Renderable, Updateable, Resource {
    /**
     * Sets entity active state.
     * This can be used, if the entity is no longer part of the game.
     * 
     * @param flag
     */
    public void setActive(boolean flag);
    
    /**
     * Checks, if the entity is still active.
     * 
     * @return
     */
    public boolean isActive();
    
    /**
     * Sets entity visible state.
     * 
     * @param flag
     */
    public void setVisible(boolean flag);
    
    /**
     * If entity should be rendered.
     * 
     * @return
     */
    public boolean isVisible();
    
    public String getID();
    
    public void setPosition(Point2D pos);
    public Point2D getPosition();
    
    public void move(Vector2D addVec);
    
    public void scale(Vector2D scale);
    public Vector2D getScale();
    
    public void setZIndex(int index);
    public int getZIndex();
    
    public void reset();
    
    /**
     * Collision check of the entities.
     * There should be logic what happens when
     * entity given as parameter collides with this entity.
     * 
     * @param e Entity which collides with this one.
     */
    public void onCollision(Entity e);
    
    public Shape2D getShape();
}
