/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateikon.internet.eprogen.web.utils;

import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;

/**
 *
 * @author emiliano
 */
public class ValidationUtils {
    
    //private static final Pattern EMAIL_PATTERN = Pattern.compile(".+@.+\\.[a-z]+");//"^\\w+([_\\.-]\\w+)*@(\\w+([_\\.-]\\w+)*)");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$", Pattern.CASE_INSENSITIVE);
    
    public static boolean  validateEmail(String email)
    {
        if (StringUtils.countMatches(email, "@")!=1 || 
            !EMAIL_PATTERN.matcher(email).matches())
        {
            return false;
        }
        
        return true;
    }      
    
}
