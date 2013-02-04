package fi.cebylfwk.graphics;

import java.awt.Color;
import java.awt.image.BufferedImage;

import java.io.IOException;

import java.lang.reflect.Field;

import java.net.URL;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class GameImageTest {
    private final String testDataPath="testdata/";
    private URL imageURL;
    private GameImage image;

    public GameImageTest() {
    }

    private Object getPrivateField( String name) throws NoSuchFieldException, IllegalAccessException {
        //Use reflection to access private field states
        Field field = image.getClass().getDeclaredField(name);
        field.setAccessible(true);
        return field.get(image);

    }

    @Before
    public void setUp() throws Exception {
        imageURL = this.getClass().getResource(testDataPath);
    }

    @After
    public void tearDown() throws Exception {
        imageURL = null;
        image = null;
    }

    @Test
    public void testImageLoadingRed16x16() {

        image = null;
        try {
            image = new GameImage(new URL(imageURL + "red16x16.png"));
        } catch (IOException e) {
            fail(e.getMessage());
        }

        assertEquals("width != 16", 16, image.getWidth());
        assertEquals("height != 16", 16, image.getHeight());
        //16*16*4(rgba) = 1024
        assertEquals("bytes != 1024", 1024, image.getBytes());

        BufferedImage img = null;

        try {
            img = (BufferedImage)getPrivateField("img");
            Color color = new Color(img.getRGB(0, 0));
            int r = color.getRed();
            int g = color.getGreen();
            int b = color.getBlue();
            assertEquals("red color component != 255", 255, r);
            assertEquals("green color component != 0", 0, g);
            assertEquals("blue color component != 0", 0, b);
        } catch (NoSuchFieldException e) {
            fail(e.getMessage());
        } catch (IllegalAccessException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testImageLoadingGreen32x16() {
        image = null;
        try {
            image = new GameImage(new URL(imageURL + "green32x16.png"));
        } catch (IOException e) {
            fail(e.getMessage());
        }

        assertEquals("width != 32", 32, image.getWidth());
        assertEquals("height != 16", 16, image.getHeight());
        //32*16*4(rgba) = 2048
        assertEquals("bytes != 2048", 2048, image.getBytes());

        BufferedImage img = null;

        try {
            img = (BufferedImage)getPrivateField("img");
            Color color = new Color(img.getRGB(0, 0));
            int r = color.getRed();
            int g = color.getGreen();
            int b = color.getBlue();
            assertEquals("red color component != 0", 0, r);
            assertEquals("green color component != 255", 255, g);
            assertEquals("blue color component != 0", 0, b);
        } catch (NoSuchFieldException e) {
            fail(e.getMessage());
        } catch (IllegalAccessException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testImageLoadingBlue16x32() {
        image = null;
        try {
            image = new GameImage(new URL(imageURL + "blue16x32.png"));
        } catch (IOException e) {
            fail(e.getMessage());
        }

        assertEquals("width != 16", 16, image.getWidth());
        assertEquals("height != 32", 32, image.getHeight());
        //16*32*4(rgba) = 2048
        assertEquals("bytes != 2048", 2048, image.getBytes());

        BufferedImage img = null;

        try {
            img = (BufferedImage)getPrivateField("img");
            Color color = new Color(img.getRGB(0, 0));
            int r = color.getRed();
            int g = color.getGreen();
            int b = color.getBlue();
            assertEquals("red color component != 0", 0, r);
            assertEquals("green color component != 0", 0, g);
            assertEquals("blue color component != 255", 255, b);
        } catch (NoSuchFieldException e) {
            fail(e.getMessage());
        } catch (IllegalAccessException e) {
            fail(e.getMessage());
        }
    }
}
