package fi.cebylfwk.shape;

import fi.cebylfwk.graphics.Color;
import fi.cebylfwk.graphics.Image;
import fi.cebylfwk.graphics.Renderer;
import fi.cebylfwk.math.Point2D;

/**
 * Base object which entities can use
 * to visual quad shaped items.
 * 
 * @author      Jari Lybeck
 * @version     %I%, %G%
 */
public class Quad implements Shape2D {
    private Image img;
    private float rotationAngle;
    private Point2D pos;
    private Point2D scale;
    private Color color;
    
    private Rectangle boundingRectangle;
    
    public Quad() {
        img = null;
        rotationAngle = 0;
        pos = new Point2D(0,0);
        scale = new Point2D(50, 50);
        
        color = new Color(1,1,1,1);
        
        boundingRectangle = new Rectangle((float)this.pos.getX(), (float)this.pos.getY(), (float)this.scale.getX() * 0.8f,
                    (float)this.scale.getY() * 0.8f);
    }
    
    
    private void scaleToImageSize() {
        if(this.img != null) {
            this.scale.setX(this.img.getWidth());
            this.scale.setY(this.img.getHeight());
        }
    }

    public void rotate(float angle) {
        this.rotationAngle = angle;
    }

    public void setPosition(float x, float y) {
        this.pos.setX(x);
        this.pos.setY(y);
        
        // TODO: calculate xScale and yScale according to rotated object
        this.boundingRectangle.setBoundaries((float)this.pos.getX(), (float)this.pos.getY(), (float)this.scale.getX() * 0.8f,
                    (float)this.scale.getY() * 0.8f);
    }

    public void scale(float x, float y) {
        this.scale.setX(x);
        this.scale.setY(y);

        // TODO: calculate xScale and yScale according to rotated object
        this.boundingRectangle.setBoundaries((float)this.pos.getX(), (float)this.pos.getY(), (float)this.scale.getX() * 0.8f,
                    (float)this.scale.getY() * 0.8f);        
    }

    public void setColor(float r, float g, float b, float a) {
        this.color.setRGBA(r, g, b, a);
    }
    
    public Rectangle getBoundingRectangle() {
        return this.boundingRectangle;
    }

    @Override
    public void render(Renderer r,long time) {
        r.drawImage((float)pos.getX(), (float)pos.getY(),rotationAngle,this.img);
    }

    @Override
    public void setImage(Image img, boolean expandToImageSize) {
        this.img = img;
        if(expandToImageSize) {
            this.scaleToImageSize();
        }        
    }
}
