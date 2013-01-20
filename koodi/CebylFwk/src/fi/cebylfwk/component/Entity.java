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
    public void setActive(boolean flag);
    public boolean isActive();
    
    public void setVisible(boolean flag);
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
    
    public void onCollision(Entity e);
    
    public Shape2D getShape();
}
