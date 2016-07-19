/*
 * AtkPropertyPlaceholderConfigurer.java
 * 
 * Created on 14-ott-2007, 23.39.43
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ateikon.internet.generic.service;

import java.util.Properties;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 *
 * @author emi
 */
public class AtkPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer{
    
    /** Logger for this class and subclasses */        
    private Log log = LogFactory.getLog(this.getClass());        

    @Override
    protected String convertPropertyValue(String arg0) {
        log.debug("prop:" +arg0);
        return super.convertPropertyValue(arg0);
    }

    @Override
    protected void convertProperties(Properties props) {
        log.debug(props);
        if("".equals(props.getProperty("jdbc.username"))){
            if(StringUtils.contains(props.getProperty("jdbc.url"),":sybase:"))
                props.setProperty("jdbc.username", "dba");
            if(StringUtils.contains(props.getProperty("jdbc.url"),":oracle:"))
                props.setProperty("jdbc.username", "pgmr");
        }
        if("".equals(props.getProperty("jdbc.password"))){
            if(StringUtils.contains(props.getProperty("jdbc.url"),":sybase:"))
                props.setProperty("jdbc.password", "sql");
            if(StringUtils.contains(props.getProperty("jdbc.url"),":oracle:"))
                props.setProperty("jdbc.password", "admin");
        }        
        super.convertProperties(props);
    }
    
    

}
