package fi.jasteroids.entities;

import fi.cebylfwk.component.Entity;
import fi.cebylfwk.graphics.GameImage;
import fi.cebylfwk.graphics.Renderer;
import fi.cebylfwk.math.Point2D;
import fi.cebylfwk.math.Vector2D;
import fi.cebylfwk.shape.Quad;

import fi.cebylfwk.shape.Shape2D;

import java.io.IOException;

import java.net.URL;

/**
 * ShotEntity presents shot which player ship shoots.
 *
 * @author      Jari Lybeck
 * @version     %I%, %G%
 * @see         BoundaryCheckedEntity
 */
public class ShotEntity extends BoundaryCheckedEntity {
    /** Base shot speed. */
    private final float baseShotSpeed = 4f;
    
    /** Shot start position offset. */
    private final float shotStartPositionOffset = 40f;
    
    /** Shot alive time in seconds. */
    private final float shotAliveTimeInSeconds = 2.0f;
    
    /** Name of the entity. */
    private String name;
    
    /** Is entity active. */
    private boolean active;
    
    /** IS entity visible. */
    private boolean visible;
    
    /** Visible shape of the entity. */
    private Quad shape;
    
    /** Position of the entity. */
    private Point2D position;
    
    /** Rotation angle in degrees on xy plane. */
    private float rotation;
    
    /** Direction vector of the entity. */
    private Vector2D direction;
    
    /** Current shot speed. */
    private float currentShotSpeed;

    /** Cumulative delta in nanoseconds used for shot delay. */
    private long cumulativeDelta;
    
    public ShotEntity(URL imagePath) throws IOException {
        super();
        name = "ShotEntity";
        shape = new Quad();
        shape.setImage(new GameImage(imagePath), true);
        position = new Point2D(0,0);
        rotation = 0.0f;
        direction = new Vector2D();
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
    public void render(Renderer renderer, long deltaTime) {
        shape.rotate(rotation);
        shape.render(renderer, deltaTime);
        super.render(renderer,deltaTime);
    }
    
    @Override
    public void update(long deltaTime) {
        if(cumulativeDelta / 1000000000.0f > shotAliveTimeInSeconds) {
            this.setActive(false);
            return;
        }
        cumulativeDelta += deltaTime;
        
        double x = position.getX() + direction.getX();
        double y = position.getY() - direction.getY();
            
        position.setX(x);
        position.setY(y);
        
        
        super.update(deltaTime);
        
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
    public void setRotation(double rot) {
        this.rotation = (float)rot;
        direction.setDirectionByAngle(rot);
        
        //We multiply the direction vector with shotStartPositionOffset constant,
        //so the bullet will start a little bit ahead of the player ship.
        this.position.addTo(shotStartPositionOffset*direction.getX(), -shotStartPositionOffset*direction.getY());
        direction.mul(currentShotSpeed);
    }

    @Override
    public double getRotation() {
        return this.rotation;
    }
    
    @Override
    public Shape2D getShape() {
        return shape;
    }
    
    public void setShotSpeed(float speed) {
        this.currentShotSpeed =  speed + baseShotSpeed;
    }
    
    @Override
    public void onCollision(Entity entity) {
        if(entity instanceof AsteroidEntity) {
            entity.setActive(false);
            this.setActive(false);
            super.onCollision(entity);    
        }
    }
    
}
