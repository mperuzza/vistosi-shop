/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ateikon.internet.eprogen.web.spring;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

/**
 *
 * @author emi
 */
public class BaseAnnotationController extends WebApplicationObjectSupport{
    
    /**
     * Convenience method for getting a i18n key's value.  Calling
     * getMessageSourceAccessor() is used because the RequestContext variable
     * is not set in unit tests b/c there's no DispatchServlet Request.
     *
     * @param msgKey
     * @param locale the current locale
     * @return
     */
    public String getText(String msgKey, Locale locale) {
        return getMessageSourceAccessor().getMessage(msgKey, locale);
    }
    
    /**
     * Convenient method for getting a i18n key's value with a single
     * string argument.
     *
     * @param msgKey
     * @param arg
     * @param locale the current locale
     * @return
     */
    public String getText(String msgKey, String arg, Locale locale) {
        return getText(msgKey, new Object[] { arg }, "", locale);
    }

    /**
     * Convenience method for getting a i18n key's value with arguments.
     *
     * @param msgKey
     * @param args
     * @param locale the current locale
     * @return
     */
    public String getText(String msgKey, Object[] args, String defaultMessage, Locale locale) {
        return getMessageSourceAccessor().getMessage(msgKey, args, defaultMessage, locale);
    }     

    
    public void saveMessage(HttpServletRequest request, String msg) {
        List messages = (List) request.getSession().getAttribute("messages");

        if (messages == null) {
            messages = new ArrayList();
        }

        messages.add(msg);
        request.getSession().setAttribute("messages", messages);
    }
    
    public void saveErrorMsg(HttpServletRequest request, String msg) {
        List messages = (List) request.getSession().getAttribute("errmessages");

        if (messages == null) {
            messages = new ArrayList();
        }

        messages.add(msg);
        request.getSession().setAttribute("errmessages", messages);
    }    
    
    @InitBinder
    public void initBinder(HttpServletRequest request, WebDataBinder binder) {
        NumberFormat nf = NumberFormat.getNumberInstance(request.getLocale());
        binder.registerCustomEditor( Double.class, null, new CustomNumberEditor(Double.class, nf, true));
        binder.registerCustomEditor( Float.class, null, new CustomNumberEditor(Float.class, nf, true));
        
        binder.registerCustomEditor(Integer.class, null,
                                    new CustomNumberEditor(Integer.class, null, true));
        
        binder.registerCustomEditor(Short.class, null,
                                    new CustomNumberEditor(Short.class, null, true));
        
        NumberFormat nf2 = NumberFormat.getIntegerInstance();
        nf2.setGroupingUsed(false);
        binder.registerCustomEditor(Long.class, null,
                                    new CustomNumberEditor(Long.class, nf2, true));
        binder.registerCustomEditor(byte[].class,
                                    new ByteArrayMultipartFileEditor());
        SimpleDateFormat dateFormat = 
            new SimpleDateFormat(getText("date.format", request.getLocale()));
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, null, 
                                    new CustomDateEditor(dateFormat, true));
    }
    
}
