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

public class ImageEntity implements Entity {
    private String name;
    private boolean active;
    private boolean visible;
    private Image img;
    private Point2D scale;
    private Point2D position;
    private Point2D txOffset;
    private Point2D tyOffset;
    private int zIndex;

    public ImageEntity(URL imagePath) throws IOException {
        super();
        img = new GameImage(imagePath);

        initialize();
    }

    public ImageEntity(Image img) throws IOException {
        super();
        this.img = img;
        initialize();
    }
    
    public ImageEntity(ImageEntity e) throws IOException {
        super();
        this.img = e.img;
        initialize();
    }
    
    private void initialize() {
        this.name = "ImageEntity";

        active = true;
        visible = true;

        scale = new Point2D(img.getWidth() / 2, img.getHeight() / 2);
        position = new Point2D(img.getWidth() / 2, img.getHeight() / 2);
        
        txOffset = new Point2D(0, 1);
        tyOffset = new Point2D(0, 1);
    }
    
    public int getWidth() {
        return img.getWidth();
    }
    
    public int getHeight() {
        return img.getHeight();
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
        this.position = position;
    }

    @Override
    public Point2D getPosition() {
        return position;
    }

    @Override
    public void move(float x, float y) {
        this.position.addTo(x, y);
    }

    @Override
    public void scale(Point2D scale) {
        this.scale.set(scale);
    }

    @Override
    public Point2D getScale() {
        return scale;
    }

    @Override
    public void setRotation(double d) {
    }

    @Override
    public double getRotation() {
        return 0.0;
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
        renderer.drawImage((float)position.getX(), (float)position.getY(), (float)scale.getX(), (float)scale.getY(), 0,
                           (float)txOffset.getX(), (float)txOffset.getY(), (float)tyOffset.getX(),
                           (float)tyOffset.getY(), img);
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
    }
    
    /**
     * Set x texture coordinate start and end values.
     * 
     * @param start between 0.0f - 1.0f
     * @param end   between 0.0f - 1.0f
     */
    public void setTextureX(float start, float end) {
        this.txOffset.setX(start);
        this.txOffset.setY(end);
    }
    
    /**
     * Set y texture coordinate start and end values.
     * 
     * @param start between 0.0f - 1.0f
     * @param end   between 0.0f - 1.0f
     */   
    public void setTextureY(float start, float end) {
        this.tyOffset.setX(start);
        this.tyOffset.setY(end);
    }
    
    public void setImage(Image img) {
        this.img = img;
    }
    
    public Image getImage() {
        return this.img;
    }
}
