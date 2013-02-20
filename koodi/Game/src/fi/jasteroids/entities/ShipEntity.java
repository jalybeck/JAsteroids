package fi.jasteroids.entities;

import fi.cebylfwk.UserControllable;
import fi.cebylfwk.component.Entity;
import fi.cebylfwk.graphics.GameImage;
import fi.cebylfwk.graphics.Image;
import fi.cebylfwk.graphics.Renderer;
import fi.cebylfwk.math.Point2D;
import fi.cebylfwk.math.Vector2D;
import fi.cebylfwk.shape.Quad;
import fi.cebylfwk.shape.Shape2D;

import java.io.IOException;

import java.net.URL;

import java.nio.ByteBuffer;

import org.lwjgl.opengl.Display;

/**
 * ShipEntity is player controllable ship.
 *
 * @author      Jari Lybeck
 * @version     %I%, %G%
 * @see         Entity, UserControllable
 */

public class ShipEntity extends BoundaryCheckedEntity implements  UserControllable {
    private final float thrust = 0.5f;
    private final float decay  = 0.99f;
    private final float maxSpeed = 7f;
    private final float turningSpeed = 4.0f;
    
    private String name;
    private boolean active;
    private boolean visible;
    private Quad shape;
    private Point2D position;
    private float rotation;
    private Vector2D curSpeed;
    private Vector2D direction;
    
    public ShipEntity(URL imagePath) throws IOException {
        super();
        name = "ShipEntity";
        shape = new Quad();
        shape.setImage(new GameImage(imagePath), true);
        position = new Point2D(0,0);
        rotation = 0.0f;
        direction = new Vector2D();
        curSpeed = new Vector2D();
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
        return this.visible;
    }

    @Override
    public String getID() {
        return null;
    }

    @Override
    public void setPosition(Point2D position) {
        this.position.set(position);
        shape.setPosition((float)position.getX(), (float)position.getY());
    }

    @Override
    public Point2D getPosition() {
        return this.position;
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
        if(entity instanceof AsteroidEntity) {
            super.onCollision(entity);
            setActive(false);
        }
    }

    @Override
    public Shape2D getShape() {
        return shape;
    }

    @Override
    public void render(Renderer renderer, long l) {
        
        
        shape.rotate(rotation);
        //shape.scale(1, 1);
        shape.render(renderer, l);
        super.render(renderer,l);
    }
    
    @Override
    public void update(long l) {

        double x = position.getX() + curSpeed.getX();
        double y = position.getY() - curSpeed.getY();
            
        position.setX(x);
        position.setY(y);
        
        curSpeed.mul(decay);
        
        super.update(l);
    }

    @Override
    public ByteBuffer getResourceData() {
        return null;
    }

    @Override
    public void release() {
        
    }

    @Override
    public boolean isCurrent() {
        return true;
    }

    @Override
    public void moveLeft(double d) {
        rotation -= d * turningSpeed;
    }

    @Override
    public void moveRight(double d) {
        rotation += d * turningSpeed;
    }

    @Override
    public void moveUp(double d) {
        direction.setDirectionByAngle(rotation);
        curSpeed.add(direction.mul(thrust));
        
        double speed = curSpeed.mag();
        if(speed > maxSpeed) {
            curSpeed.mul(maxSpeed / speed);
        }

    }

    @Override
    public void moveDown(double d) {
    }
    
    @Override
    public void setRotation(double d) {

    }

    @Override
    public double getRotation() {
        return rotation;
    }
    
    public float getCurSpeed() {
        return (float)this.curSpeed.mag();
    }
}
