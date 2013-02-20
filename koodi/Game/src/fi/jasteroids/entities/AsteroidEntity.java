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

public class AsteroidEntity extends BoundaryCheckedEntity {    
    private String name;
    private boolean active;
    private boolean visible;
    private Quad shape;
    private Point2D position;
    private float rotation;
    private Vector2D direction;
    private float currentSpeed;
    private float rotationSpeed;
    private Point2D scale;
    
    public AsteroidEntity(URL imagePath) throws IOException {
        super();
        name = "AsteroidEntity";
        shape = new Quad();
        shape.setImage(new GameImage(imagePath), true);
        position = new Point2D(0,0);
        rotation = 0.0f;
        direction = new Vector2D();
        scale = new Point2D();
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
        shape.scale((float)scale.getX(), (float)scale.getY());
        shape.render(renderer, deltaTime);
        super.render(renderer,deltaTime);
    }
    
    @Override
    public void update(long deltaTime) {
        
        double x = position.getX() + direction.getX();
        double y = position.getY() - direction.getY();
            
        position.setX(x);
        position.setY(y);
        
        rotation += rotationSpeed;
        
        
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
        direction.mul(currentSpeed);
    }

    @Override
    public double getRotation() {
        return this.rotation;
    }
    
    @Override
    public Shape2D getShape() {
        return shape;
    }
    
    @Override
    public void onCollision(Entity entity) {
        //Asteroid entities should not collide with each otherss
        if(!(entity instanceof AsteroidEntity)) 
            super.onCollision(entity);
    }
    
    @Override
    public void scale(Point2D scale) {
        this.scale.set(scale);
    }
    
    @Override
    public Point2D getScale() {
        return scale;
    }
    
    public void setMoveSpeed(float speed) {
        this.currentSpeed =  speed;
    }
    
    public void setRotationSpeed(float speed) {
        this.rotationSpeed =  speed;
    }    
}
