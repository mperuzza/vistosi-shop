/*
 * JSTLFunctions.java
 *
 * Created on 31 ottobre 2006, 10.13
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.ateikon.internet.eprogen.web.functions;

import java.io.File;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author emi
 */
public class JSTLFunctions {
    
    public static String localeDescription(String dslocale, String dsdefault){
        if(StringUtils.isBlank(dslocale))
            return dsdefault;
        else
            return dslocale;            
    }

    public static String formatHtmlDescription(String text, String separator, String wrapperTag){

        String output = "";
        String[] splittedString = StringUtils.split(text, separator);

        try{
            for(int i=0; i<splittedString.length; i++){
                output += "<" + wrapperTag + ">" + StringUtils.trimToEmpty(splittedString[i]) + separator + "</" + wrapperTag + ">";
            }
        }catch(Exception e){
            output = text;
        }

        return output;

    }


    public static String abbreviateString(String text, int maxWidth){
        return StringUtils.abbreviate(text, maxWidth);
    }

    //file utils
    public static Date lastModified(File f){
        return new Date(f.lastModified());
    }

    public static long fileSize(File f){
        return f.length();
    }

    public static boolean check(javax.servlet.ServletContext application, String dir, String file) {

        System.out.println(dir);
        System.out.println(file);

        String path = application.getRealPath(dir);
        System.out.println(path);
        String sep = System.getProperty("file.separator");
        //debug.writeToLog(path + sep + file , date);
        File f = new File( path + sep + file );
        return f.exists();
    }
    
    public static String capitalizeFirst(String text){
        return StringUtils.capitalize(text);
    }    
}
