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
    private final float baseShotSpeed = 4f;
    private final float shotStartPositionOffset = 40f;
    private final float shotAliveTimeInSeconds = 2.0f;
    
    private String name;
    private boolean active;
    private boolean visible;
    private Quad shape;
    private Point2D position;
    private float rotation;
    private Vector2D direction;
    private float currentShotSpeed;
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
