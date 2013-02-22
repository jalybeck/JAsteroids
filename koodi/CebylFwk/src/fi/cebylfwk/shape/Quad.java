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
    /** Image used for quad rendering. */
    private Image img;
    /** Quads rotation angle in degrees. */
    private float rotationAngle;
    /** Quads 2D position. */
    private Point2D pos;
    /** Quads scaling. */
    private Point2D scale;
    /** Color of the quad. */
    private Color color;
    
    private Rectangle boundingRectangle;
    
    public Quad() {
        img = null;
        rotationAngle = 0;
        pos = new Point2D(0,0);
        scale = new Point2D(0, 0);
        
        color = new Color(1,1,1,1);
        
        boundingRectangle = new Rectangle((float)this.pos.getX(), (float)this.pos.getY(), (float)this.scale.getX() * 0.8f,
                    (float)this.scale.getY()* 0.8f);
    }
    
    /**
     *  Scales quad to be same size as image assigned to this quad.
     */
    private void scaleToImageSize() {
        if(this.img != null) {
            this.scale(1,1);
        }
    }
    /**
     * Sets rotation angle of object.
     * 
     * @param angle in degrees
     */
    public void rotate(float angle) {
        this.rotationAngle = angle;
    }

    public void setPosition(float x, float y) {
        this.pos.setX(x);
        this.pos.setY(y);
        
        // TODO: calculate xScale and yScale according to rotated object
        this.boundingRectangle.setBoundaries((float)this.pos.getX(), (float)this.pos.getY(), (float)this.scale.getX()* 0.8f,
                    (float)this.scale.getY()* 0.8f);
    }

    public void scale(float x, float y) {
        this.scale.setX(this.img.getWidth() * x);
        this.scale.setY(this.img.getHeight() * y);

        // TODO: calculate xScale and yScale according to rotated object
        this.boundingRectangle.setBoundaries((float)this.pos.getX(), (float)this.pos.getY(), (float)this.scale.getX()* 0.8f,
                    (float)this.scale.getY()* 0.8f);        
    }

    public void setColor(float r, float g, float b, float a) {
        this.color.setRGBA(r, g, b, a);
    }
    
    public Rectangle getBoundingObject() {
        return this.boundingRectangle;
    }

    @Override
    public void render(Renderer r,long time) {
        r.drawImage((float)pos.getX(), (float)pos.getY(), (float)scale.getX(), (float)scale.getY(), rotationAngle,this.img);
        //r.drawImage((float)pos.getX(), (float)pos.getY(), rotationAngle,this.img);
    }

    @Override
    public void setImage(Image img, boolean expandToImageSize) {
        this.img = img;
        if(expandToImageSize) {
            this.scaleToImageSize();
        }        
    }
}
