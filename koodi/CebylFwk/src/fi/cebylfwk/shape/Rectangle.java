package fi.cebylfwk.shape;

import fi.cebylfwk.Renderable;
import fi.cebylfwk.graphics.Renderer;

/**
 * Rectangle object is used as bounding object
 * to check against shape collisions.
 *
 * @author      Jari Lybeck
 * @version     %I%, %G%
 */

public class Rectangle implements BoundingObject<Rectangle> {
    /** Top and bottom extents of the rectangle. */
    private float top,bottom;
    /** Right and left extents of the rectangle. */
    private float right,left;
    /** Rectangles center position on screen. */
    private float xPos, yPos;
    /** Width and height of the rectangle. */
    private float width, height;
    
    public Rectangle(float xPos, float yPos, float width, float height) {
        
        this.setBoundaries(xPos, yPos, width, height);
    }
    
    @Override
    public boolean intersects(Rectangle rect1) {
        Rectangle rect2 = this;
        
        return !((rect1.top > rect2.bottom) || (rect1.bottom < rect2.top) ||
                 (rect1.left > rect2.right) || (rect1.right < rect2.left));
    }
    
    public void setBoundaries(float xPos, float yPos, float width, float height) {
        this.top = yPos-height;
        this.bottom = yPos+height;
        this.right = xPos+width;
        this.left = xPos-width;
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
    }
    
    public float getHeight() {
        return this.height;
    }
    
    public float getWidth() {
        return this.width;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Top: ").append(this.top).append("\n");
        sb.append("Left: ").append(this.left).append("\n");
        sb.append("Bottom: ").append(this.bottom).append("\n");
        sb.append("Right: ").append(this.right).append("\n");
        
        return sb.toString();
    }
}

