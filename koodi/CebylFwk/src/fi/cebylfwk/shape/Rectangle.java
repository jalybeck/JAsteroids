package fi.cebylfwk.shape;

/**
 * Rectangle object is used as bounding object
 * to check against shape collisions.
 * 
 * @author      Jari Lybeck
 * @version     %I%, %G%
 */

public class Rectangle {
    private float top,bottom;
    private float right,left;
    private float xPos, yPos;
    private float width, height;
    
    public Rectangle(float xPos, float yPos, float width, float height) {
        
        this.setBoundaries(xPos, yPos, width, height);
    }
    
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

