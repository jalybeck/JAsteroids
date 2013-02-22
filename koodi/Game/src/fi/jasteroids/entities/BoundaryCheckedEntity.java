package fi.jasteroids.entities;

import fi.cebylfwk.Collidible;
import fi.cebylfwk.component.Entity;
import fi.cebylfwk.graphics.Color;
import fi.cebylfwk.graphics.Renderer;
import fi.cebylfwk.math.Point2D;
import fi.cebylfwk.math.Vector2D;
import fi.cebylfwk.shape.Rectangle;
import fi.cebylfwk.shape.Shape2D;

import java.nio.ByteBuffer;

import org.lwjgl.opengl.Display;
/**
 * BoundaryCheckedEntity is used to check game screen boundaries
 * and wrap entity over to other side of the screen.
 * All the JAsteroids game entities which should be wrapped over boundaries
 * should extend this class.
 * Also collision code is put here.
 *
 * @author      Jari Lybeck
 * @version     %I%, %G%
 * @see         Entity
 * @see         Collidible
 */
public abstract class BoundaryCheckedEntity implements Entity, Collidible {
    /** Color of the rectangle. */
    private Color rectangleColor;
    /** Has collision happened? */
    private boolean hasCollision;
    
    /** ZIndex. Larger the number more top this item will be drawn. */
    private int zIndex;
    
    public BoundaryCheckedEntity() {
        super();
        rectangleColor = new Color(1.0f, 0.0f, 0.0f,1.0f);
        hasCollision = false;
    }
    
    /**
     * Checks entity boundaries, so the entity will wrap over to other side of the screen.
     */
    private void checkBoundaries() {
        Point2D entityPos = this.getPosition();
        float entityHeight = ((Rectangle)this.getShape().getBoundingObject()).getHeight();
        float entityWidth = ((Rectangle)this.getShape().getBoundingObject()).getWidth();
        
        if (entityPos.getY() < 0 - entityHeight) {
            entityPos.set(new Point2D(entityPos.getX(),
                                       Display.getDisplayMode().getHeight() + entityHeight));
        } else if (entityPos.getY() > Display.getDisplayMode().getHeight() + entityHeight) {
            entityPos.set(new Point2D(entityPos.getX(), 0 - entityHeight));
        }

        if (entityPos.getX()  < 0 - entityWidth) {
            entityPos.set(new Point2D(Display.getDisplayMode().getWidth() + entityWidth,
                                       entityPos.getY()));
        } else if (entityPos.getX() > Display.getDisplayMode().getWidth() + entityWidth) {
            entityPos.set(new Point2D(0 - entityWidth, entityPos.getY()));
        }
        this.setPosition(entityPos);
    }
    
    @Override
    public void setActive(boolean b) {
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void setVisible(boolean b) {
    }

    @Override
    public boolean isVisible() {
        return false;
    }

    @Override
    public String getID() {
        return null;
    }

    @Override
    public void setPosition(Point2D point2D) {
    }

    @Override
    public Point2D getPosition() {
        return null;
    }

    @Override
    public void move(float x, float y) {
    }

    @Override
    public void scale(Point2D vector2D) {
    }

    @Override
    public Point2D getScale() {
        return null;
    }

    @Override
    public void setZIndex(int i) {
        zIndex = i;
    }

    @Override
    public int getZIndex() {
        return zIndex;
    }

    @Override
    public void reset() {
    }

    @Override
    public void onCollision(Entity entity) {
        hasCollision = true;
    }

    @Override
    public Shape2D getShape() {
        return null;
    }

    @Override
    public void render(Renderer renderer, long l) {
        //Rectangle r = (Rectangle)this.getShape().getBoundingObject();
        //renderer.drawLineRectangle((float)this.getPosition().getX(), (float)this.getPosition().getY(), r.getWidth(), r.getHeight(), rectangleColor);
    }

    @Override
    public void update(long l) {
        this.checkBoundaries();
        
        if(hasCollision) {
            rectangleColor.setRGBA(1.0f, 1.0f, 1.0f, 1.0f);
            hasCollision = false;
        } else {
            rectangleColor.setRGBA(1.0f,0.0f,0.0f, 1.0f);
        }
    }

    @Override
    public ByteBuffer getResourceData() {
        return null;
    }

    @Override
    public void release() {
    }
}
