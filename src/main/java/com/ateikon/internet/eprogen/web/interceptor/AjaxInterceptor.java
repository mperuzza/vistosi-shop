/*
 * AjaxInterceptor.java
 * 
 * Created on 13-lug-2007, 12.41.02
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ateikon.internet.eprogen.web.interceptor;

import java.util.Enumeration;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author emi
 */
public class AjaxInterceptor extends HandlerInterceptorAdapter{
    
    protected final transient Log log = LogFactory.getLog(getClass());

    public AjaxInterceptor() {
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handle, ModelAndView modelAndView) throws Exception {
        boolean isAjax = "XMLHttpRequest".equals(request.getHeader("x-requested-with"))
                        || "XMLHttpRequest".equals(request.getAttribute("XMLHttpRequest"))
                        || (request.getParameter("ajax")!=null);
        /*Enumeration headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()) {
          String headerName = (String)headerNames.nextElement();
          log.debug(headerName);
          log.debug(request.getHeader(headerName));
        }
        */
        //log.debug(modelAndView.getModel());
        log.debug("ajax interceptor: " +isAjax);

        if(isAjax) {
           
            response.setHeader( "Pragma", "no-cache" );
            response.addHeader( "Cache-Control", "must-revalidate" );
            response.addHeader( "Cache-Control", "no-cache" );
            response.addHeader( "Cache-Control", "no-store" );
            response.setDateHeader("Expires", 0); 

            if(modelAndView!=null){modelAndView.setViewName(modelAndView.getViewName());}
        }
    }

}
