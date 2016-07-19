/*
 * CheckFileExist.java
 *
 * Created on 2 luglio 2002, 10.52
 */

package com.ateikon.internet.utils;

import java.beans.*;
import java.io.File;

/**
 *
 * @author  administrator
 */
public class CheckFileExist extends Object implements java.io.Serializable {
    
    //debug
    private Debug debug;
    private java.util.Date date = new java.util.Date();
    
    /** Creates new CheckFileExist */
    public CheckFileExist() {
        this.debug = Debug.getInstance();
    }
    
    public boolean check(javax.servlet.ServletContext application, String dir, String file) {
        
        String path = application.getRealPath(dir);
        String sep = System.getProperty("file.separator");
        //debug.writeToLog(path + sep + file , date);
        File f = new File( path + sep + file );
        return f.exists();
    }    
    
}
