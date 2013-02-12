package fi.jasteroids.entities;

import fi.cebylfwk.component.Entity;
import fi.cebylfwk.graphics.Renderer;
import fi.cebylfwk.math.Point2D;
import fi.cebylfwk.math.Vector2D;
import fi.cebylfwk.shape.Shape2D;

import java.nio.ByteBuffer;

import org.lwjgl.opengl.Display;
/**
 * BoundaryCheckedEntity is used to check game screen boundaries
 * and wrap entity over to other side of the screen.
 * All the JAsteroids game entities which should be wrapped over boundaries
 * should extend this class.
 *
 * @author      Jari Lybeck
 * @version     %I%, %G%
 * @see         Entity
 */
public abstract class BoundaryCheckedEntity implements Entity {
    public BoundaryCheckedEntity() {
        super();
    }
    private void checkBoundaries() {
        Point2D entityPos = this.getPosition();
        float entityHeight = this.getShape().getBoundingRectangle().getHeight();
        float entityWidth = this.getShape().getBoundingRectangle().getWidth();
        
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
    public void move(Vector2D vector2D) {
    }

    @Override
    public void scale(Vector2D vector2D) {
    }

    @Override
    public Vector2D getScale() {
        return null;
    }

    @Override
    public void setZIndex(int i) {
    }

    @Override
    public int getZIndex() {
        return 0;
    }

    @Override
    public void reset() {
    }

    @Override
    public void onCollision(Entity entity) {
    }

    @Override
    public Shape2D getShape() {
        return null;
    }

    @Override
    public void render(Renderer renderer, long l) {
    }

    @Override
    public void update(long l) {
        this.checkBoundaries();
    }

    @Override
    public ByteBuffer getResourceData() {
        return null;
    }

    @Override
    public void release() {
    }
}
