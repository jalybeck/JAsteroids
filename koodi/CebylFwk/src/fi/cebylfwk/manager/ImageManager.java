package fi.cebylfwk.manager;

import fi.cebylfwk.graphics.GameImage;

import fi.cebylfwk.graphics.Image;

import java.io.IOException;

import java.net.URL;

/**
 * Manager for all the image files.
 * Getter methods can be added here later 
 * depending on concrete Image implementations.
 *
 * @author      Jari Lybeck
 * @version     %I%, %G%
 */

public class ImageManager extends ResourceManager<Image> {
    
    public ImageManager() {
        super();
    }
    
    public Image getGameImage(URL fileName) throws IOException {
        Image img = (Image)this.getResource(fileName);
        
        if(img  == null ) {
            img = new GameImage(fileName);
            this.setResource(fileName, img);
        }
        
        return img;
    }
}
