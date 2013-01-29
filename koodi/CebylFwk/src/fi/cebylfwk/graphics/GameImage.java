package fi.cebylfwk.graphics;

import java.awt.image.DataBufferByte;

import java.io.IOException;

import java.net.URL;

import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

/**
 * Implementation of Image interface.
 * All the game objects can use this as 
 * their base image class.
 *
 * @author      Jari Lybeck
 * @version     %I%, %G%
 */
public class GameImage implements Image {
    private URL url;
    private java.awt.image.BufferedImage img;
    
    public GameImage(URL fileName) throws IOException {
        url = fileName;
        
        ImageIO.setUseCache(false);
        
        img = ImageIO.read(url);
    }

    public int getWidth() {
        return img.getWidth();
    }

    public int getHeight() {
        return img.getHeight();
    }

    public int getBytes() {
        return img.getData().getDataBuffer().getSize();
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Name: ").append(url.getFile()).append("\n");
        sb.append("Type: ").append(img.toString()).append("\n");
        sb.append("Bytes: ").append(img.getData().getDataBuffer().getSize()).append("\n");
        sb.append("colorType: ").append(img.getType()).append("\n");
        
        return sb.toString();
    }

    public ByteBuffer getResourceData() {
        ByteBuffer buf = ByteBuffer.allocateDirect(img.getData().getDataBuffer().getSize());
        buf.put(((DataBufferByte)img.getRaster().getDataBuffer()).getData());
        buf.rewind();
        return buf;
    }

    public void release() {
        this.img = null;
        this.url = null;
    }

    public ColorFormat getColorFormat() {
        switch(this.img.getType()) {
            case java.awt.image.BufferedImage.TYPE_3BYTE_BGR:
                return ColorFormat.BYTE_BGR;
            case java.awt.image.BufferedImage.TYPE_4BYTE_ABGR:
                return ColorFormat.BYTE_ABGR;
            case java.awt.image.BufferedImage.TYPE_4BYTE_ABGR_PRE:
                return ColorFormat.NOT_SUPPORTED;
            case java.awt.image.BufferedImage.TYPE_BYTE_BINARY:
                return ColorFormat.NOT_SUPPORTED;
            case java.awt.image.BufferedImage.TYPE_BYTE_GRAY:
                return ColorFormat.NOT_SUPPORTED;
            case java.awt.image.BufferedImage.TYPE_BYTE_INDEXED:
                return ColorFormat.NOT_SUPPORTED;
            case java.awt.image.BufferedImage.TYPE_CUSTOM:
                return ColorFormat.NOT_SUPPORTED;
            case java.awt.image.BufferedImage.TYPE_INT_ARGB:
                return ColorFormat.INT_ARGB;
            case java.awt.image.BufferedImage.TYPE_INT_ARGB_PRE:
                return ColorFormat.NOT_SUPPORTED;
            case java.awt.image.BufferedImage.TYPE_INT_BGR:
                return ColorFormat.INT_BGR;
            case java.awt.image.BufferedImage.TYPE_INT_RGB:
                return ColorFormat.INT_RGB;
            case java.awt.image.BufferedImage.TYPE_USHORT_555_RGB:
                return ColorFormat.NOT_SUPPORTED;
            case java.awt.image.BufferedImage.TYPE_USHORT_565_RGB:
                return ColorFormat.NOT_SUPPORTED;
            case java.awt.image.BufferedImage.TYPE_USHORT_GRAY:
                return ColorFormat.NOT_SUPPORTED;
            default:
                return ColorFormat.NOT_SUPPORTED;
        }
    }
}