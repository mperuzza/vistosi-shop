/*
 * BaseWizardFormController.java
 *
 * Created on 14 marzo 2006, 12.12
 *
 * To change this template, choose Tools | Template Manager
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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 *
 * @author Emiliano
 */
public abstract class BaseWizardFormController extends AbstractWizardFormController{
    
    protected final transient Log log = LogFactory.getLog(getClass());
    
    
    /** Creates a new instance of BaseWizardFormController */
    public BaseWizardFormController() {
    }
    
    public void saveMessage(HttpServletRequest request, String msg) {
        List messages = (List) request.getSession().getAttribute("messages");

        if (messages == null) {
            messages = new ArrayList();
        }

        messages.add(msg);
        request.getSession().setAttribute("messages", messages);
    }
    
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
        return getText(msgKey, new Object[] { arg }, locale);
    }

    /**
     * Convenience method for getting a i18n key's value with arguments.
     *
     * @param msgKey
     * @param args
     * @param locale the current locale
     * @return
     */
    public String getText(String msgKey, Object[] args, Locale locale) {
        return getMessageSourceAccessor().getMessage(msgKey, args, locale);
    }


    /**
     * Set up a custom property editor for converting form inputs to real objects
     */
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) {
        
        NumberFormat nf = NumberFormat.getNumberInstance(request.getLocale());
        //nf.setGroupingUsed(true);
        //nf.setMinimumFractionDigits(2);
        //nf.setMaximumFractionDigits(2);
        binder.registerCustomEditor( Double.class, null, new CustomNumberEditor(Double.class, nf, true));
        

        binder.registerCustomEditor( Float.class, null, new CustomNumberEditor(Float.class, nf, true));

        /*
        NumberFormat nfc = NumberFormat.getNumberInstance(request.getLocale());
        nfc.setGroupingUsed(true);
        nfc.setMinimumFractionDigits(2);
        nfc.setMaximumFractionDigits(2);        
        binder.registerCustomEditor( OrdacqPosi.class, "importoriga", new CustomNumberEditor(Double.class, nfc, true));        
        */
        binder.registerCustomEditor(Integer.class, null,
                                    new CustomNumberEditor(Integer.class, null, true));
        NumberFormat nf2 = NumberFormat.getIntegerInstance(request.getLocale());
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
