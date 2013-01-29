package fi.cebylfwk.shape;

import fi.cebylfwk.graphics.Texture;

/**
 * All the game entities can use rectangle as 
 * their visible shape.
 *
 * @author      Jari Lybeck
 * @version     %I%, %G%
 */

public class Rectangle implements Shape2D {
    private float top,bottom;
    private float right,left;
    private float xPos, yPos;
    private float xScale, yScale;
    private float rotationAngle;
    private float width, height;
    private float r, g, b, a;
    
    private Texture texture;
    
    public Rectangle(float xPos, float yPos, float width, float height) {
        
        this.r = 1.0f;
        this.g = 1.0f;
        this.b = 1.0f;
        
        this.setBoundaries(xPos, yPos, width, height);
    }
    
    private void setBoundaries(float xPos, float yPos, float width, float height) {
        this.top = yPos-height;
        this.bottom = yPos+height;
        this.right = xPos+width;
        this.left = xPos-width;
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
    }
    
    private void scaleToTextureSize() {
        if(this.texture != null) {
            this.xScale = this.texture.getTextureImage().getWidth();
            this.yScale = this.texture.getTextureImage().getHeight();
        }
    }
    
    public float getHeight() {
        return this.height;
    }
    
    public float getWidth() {
        return this.width;
    }
    
    public boolean intersects(Rectangle rect1) {
        Rectangle rect2 = this;
        
        return !((rect1.top > rect2.bottom) || (rect1.bottom < rect2.top) ||
                 (rect1.left > rect2.right) || (rect1.right < rect2.left));
    }
    
    @Override
    public void setTexture(Texture t, boolean expandToTextureSize) {
        this.texture = t;
        if(expandToTextureSize) {
            this.scaleToTextureSize();
        }
    }

    @Override
    public void bindTexture() {
        if(this.texture != null) {
            texture.bind();
        }        
    }

    @Override
    public void setColor(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    @Override
    public void rotate(float angle) {
        this.rotationAngle = angle;
    }

    @Override
    public void setPosition(float x, float y) {
        this.xPos = x;
        this.yPos = y;
        
        this.setBoundaries(this.xPos, this.yPos, this.xScale, this.yScale);
    }

    @Override
    public void scale(float x, float y) {
        this.xScale = x;
        this.yScale = y;
        
        this.setBoundaries(this.xPos, this.yPos, this.xScale, this.yScale);        
    }

    @Override
    public void render(long time) {
        throw new RuntimeException("Not implemented!");
    }
    

}
