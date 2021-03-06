package fi.jasteroids.entities;

import fi.cebylfwk.component.Entity;
import fi.cebylfwk.graphics.GameImage;
import fi.cebylfwk.graphics.Image;
import fi.cebylfwk.graphics.Renderer;
import fi.cebylfwk.math.Point2D;
import fi.cebylfwk.math.Vector2D;
import fi.cebylfwk.shape.Shape2D;

import java.io.IOException;

import java.net.URL;

import java.nio.ByteBuffer;

/**
 * FullScreenImageEntity should be used to show fullscreen sized images.
 *
 * @author      Jari Lybeck
 * @version     %I%, %G%
 * @see         Entity
 */
public class FullScreenImageEntity implements Entity {
    /** Name of the entity. */
    private String name;
    
    /** Is entity active. */
    private boolean active;
    
    /** IS entity visible. */
    private boolean visible;
    
    /** Image which will be drawn. */
    private Image img;
    
    /** ZIndex. Larger the number more top this item will be drawn. */
    private int zIndex;
    
    public FullScreenImageEntity(URL imagePath) throws IOException {
        super();
        this.name = "FullScreenImageEntity";
        img = new GameImage(imagePath);
        
        active = true;
        visible = true;
    }

    public FullScreenImageEntity(String fileName) throws IOException {
        super();
        this.name = "FullScreenImageEntity";
        img = new GameImage(fileName);
        
        active = true;
        visible = true;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public String getID() {
        return name;
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
    public Shape2D getShape() {
        return null;
    }

    @Override
    public void render(Renderer renderer, long l) {
        renderer.drawFullScreenImage(this.img);
    }

    @Override
    public void update(long l) {
    }

    @Override
    public ByteBuffer getResourceData() {
        return null;
    }

    @Override
    public void release() {
        img.release();
        img = null;
    }

    @Override
    public void setRotation(double d) {
    }

    @Override
    public double getRotation() {
        return 0.0;
    }
}
