/*
 * MailSender.java
 *
 * Created on 3 novembre 2005, 12.49
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.ateikon.internet.generic.service;

import com.ateikon.internet.eprogen.domain.logic.BaseManager;
import com.ateikon.internet.generic.domain.Costante;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 *
 * @author Emiliano
 */
public class MailSender extends JavaMailSenderImpl{
    
    /** Logger for this class and subclasses */
    private Log log = LogFactory.getLog(this.getClass());
    
    private BaseManager eprogenManager;
    
    public void setEprogenManager(BaseManager eprogenManager) {
        this.eprogenManager = eprogenManager;
    }
    
    /** Creates a new instance of MailSender */
    public MailSender() {
    }
    
    private void init(){
        log.debug("*** Inizializzazione MailSender ***");
        //check se il valore dell'smtp host � stato settato nel file di config
        if(StringUtils.isBlank(this.getHost())){
            //se non � stato settato lo leggo dal db
            String c = this.eprogenManager.getCostvalue("mail_smtp_host");
            if(c!=null) {
                this.setHost(c);
                String username = this.eprogenManager.getCostvalue("mail_smtp_user");
                String password = this.eprogenManager.getCostvalue("mail_smtp_password");
                
                if(username!=null && password!=null){
                    this.setUsername(username);
                    this.setPassword(password);
                }
            }
            else{
                this.setHost("xx.smtp.ateikon.com");
                log.error("Attenzione SMTP NON IMPOSTATO!");
            }
        }
    }
    
}
