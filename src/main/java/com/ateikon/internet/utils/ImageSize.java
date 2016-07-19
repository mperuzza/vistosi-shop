/*
 * ImageSize.java
 *
 * Created on 30 settembre 2002, 16.47
 */

package com.ateikon.internet.utils;

import java.beans.*;

import javax.imageio.ImageIO;
import java.io.InputStream;
import java.awt.image.BufferedImage;

import java.net.URL;

/**
 *
 * @author  Emiliano Armellin
 */
public class ImageSize extends Object implements java.io.Serializable {
    
    /** Creates new ImageSize */
    public ImageSize() {

    }
    
    // lettura da url 
    public static int[] getImageSize(java.lang.String imageUrl) {
        try{
            URL url = new URL(imageUrl);
            //Image image = Toolkit.getDefaultToolkit().getImage(imageUrl); //versione per jdk <1.4
            BufferedImage image = ImageIO.read(url);
            return new int[] {image.getWidth(), image.getHeight()};
            //return new int[] {image.getWidth(null), image.getHeight(null)};
        }catch(Exception e){
            return new int[] {0, 0};
        }
    }  
    
    // lettura da input stream (per es. un file)
    public static int[] getImageSize(InputStream is) {
        try{
            BufferedImage image = ImageIO.read(is);
            return new int[] {image.getWidth(), image.getHeight()};
            //return new int[] {image.getWidth(null), image.getHeight(null)};
        }catch(Exception e){
            return new int[] {0, 0};
        }
    }      
    
}
