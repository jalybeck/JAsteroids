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
     * @return true|false
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
     * @return true|false
     */
    public boolean isVisible();
    
    /**
     * Unique ID of the entity.
     * 
     * @return ID string
     */
    public String getID();
    
    /**
     * Sets position of entity in Carteasian coordinates.
     * 
     * @param pos
     */
    public void setPosition(Point2D pos);

    /**
     * Gets position of entity.
     * 
     * @return Point2D position
     */
    public Point2D getPosition();
    
    /**
     * Moves entity 
     * 
     * @param x How many pixels in x direction
     * @param y How many pixels in y direction
     */
    public void move(float x, float y);
    
    /**
     * Scales entity.
     * 
     * @param scale
     */
    public void scale(Point2D scale);
    
    /**
     * Gets entity scaling factor.
     * 
     * @return Point2D
     */
    public Point2D getScale();
    
    /**
     * Sets rotation angle of entity.
     * 
     * @param angle of rotation in degrees.
     */
    public void setRotation(double angle);

    /**
     * Gets angle of rotation.
     * 
     * @return angle of rotation in degrees.
     */
    public double getRotation();
    
    /**
     * Sets Z index of entity.
     * Larger zindex entities are drawn on top.
     * 
     * @param index
     */
    public void setZIndex(int index);
    public int getZIndex();
    
    /**
     * Resets entity to like it was constructed first time.
     */
    public void reset();
    
    /**
     * Gets entity shape.
     * 
     * @return Shape2D
     */
    public Shape2D getShape();
}
