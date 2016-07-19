/*
 * MailEngine.java
 *
 * Created on 3 novembre 2005, 14.36
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.ateikon.internet.generic.service;

import java.io.File;
import java.util.Date;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.apache.velocity.tools.generic.DateTool;
import org.apache.velocity.tools.generic.NumberTool;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 *
 * @author Emiliano
 */
public class MailEngine {
    
    private Log log = LogFactory.getLog(this.getClass());
    
    private MailSender mailSender;
    private VelocityEngine velocityEngine;
    
    private String defFrom;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    /**
     * Send a simple message based on a Velocity template.
     * @param msg
     * @param templateName
     * @param model
     */
    public void sendMessage(SimpleMailMessage msg, String templateName,
                            Map model) throws MailException{
        String result = null;

        try {
            result =
                VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
                                                            templateName, model);
        } catch (VelocityException e) {
            e.printStackTrace();
        }

        msg.setText(result);
        send(msg);
    }
    
    public void sendHtmlMessage(String[] emailTOAddresses,
                                String[] emailCCAddresses,
                                String emailFROM,
                                String subject,
                                String templateName,
                                Map model,
                                String bcc) throws MailException, MessagingException{
            String result = null;
            model.put("number", new NumberTool());
            model.put("date", new DateTool());
            try {
                result =
                    VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
                                                                templateName, model);
            } catch (VelocityException e) {
                e.printStackTrace();
            }  
            MimeMessage message =
                ((JavaMailSenderImpl) mailSender).createMimeMessage(); 
            //message.setSubject(subject, "utf-8");
            MimeMessageHelper helper = new MimeMessageHelper(message, "iso-8859-1");
            helper.setFrom(emailFROM);
            if(bcc!=null){
                helper.setBcc(bcc);
            }
            helper.setText(result, true);
            helper.setTo(emailTOAddresses);
            if(emailCCAddresses!=null && emailCCAddresses.length >0){
                helper.setCc(emailCCAddresses);
            }
            helper.setSubject(subject);
            helper.setSentDate(new Date());
            ((JavaMailSenderImpl) mailSender).send(message);
            
    }
    
    public void sendHtmlMessage(String[] emailTOAddresses,
                                String emailFROM,
                                String subject,
                                String templateName,
                                Map model,
                                String bcc) throws MailException, MessagingException{
        
        sendHtmlMessage(emailTOAddresses, null, emailFROM, subject, templateName, model, bcc);
    }    
    
    public void sendHtmlMessage(String[] emailTOAddresses,
                                String emailFROM,
                                String subject,
                                String templateName,
                                Map model) throws MailException, MessagingException{ 
        
        sendHtmlMessage(emailTOAddresses, null, emailFROM, subject, templateName, model, null);
        
    }   
    
    public void sendHtmlMessage(String[] emailTOAddresses,
                                String[] emailCCAddresses,
                                String emailFROM,
                                String subject,
                                String templateName,
                                Map model,
                                ByteArrayResource resource,
                                String attachmentName) throws MailException, MessagingException{
            String result = null;
            model.put("number", new NumberTool());
            model.put("date", new DateTool());
            try {
                result =
                    VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
                                                                templateName, model);
            } catch (VelocityException e) {
                e.printStackTrace();
            }  
            MimeMessage message =
                ((JavaMailSenderImpl) mailSender).createMimeMessage();            
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(emailFROM);
            helper.setText(result, true);
            helper.setTo(emailTOAddresses);
            if(emailCCAddresses!=null && emailCCAddresses.length >0){
                helper.setCc(emailCCAddresses);
            }
            helper.setSubject(subject);
            helper.setSentDate(new Date());
            
            helper.addAttachment(attachmentName, resource);
            
            ((JavaMailSenderImpl) mailSender).send(message);
            
    }    
    
    public void sendHtmlMessage(String[] emailTOAddresses,
                                String[] emailCCAddresses,            
                                String emailFROM,
                                String subject,
                                String templateName,
                                Map model,
                                String bcc,
                                String attachmentName,
                                File file) throws MailException, MessagingException{
            String result = null;
            model.put("number", new NumberTool());
            model.put("date", new DateTool());
            try {
                result =
                    VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
                                                                templateName, model);
            } catch (VelocityException e) {
                e.printStackTrace();
            }  
            MimeMessage message =
                ((JavaMailSenderImpl) mailSender).createMimeMessage();            
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(emailFROM);
            helper.setText(result, true);
            helper.setTo(emailTOAddresses);
            if(emailCCAddresses!=null && emailCCAddresses.length >0){
                helper.setCc(emailCCAddresses);
            }            
            if(bcc!=null){
                helper.setBcc(bcc);
            }            
            helper.setSubject(subject);
            helper.setSentDate(new Date());
            
            helper.addAttachment(attachmentName, file);
            
            ((JavaMailSenderImpl) mailSender).send(message);
            
    }    
    
    public void sendHtmlMessage(String[] emailTOAddresses,
                                String emailFROM,
                                String subject,
                                String templateName,
                                Map model,
                                String bcc,
                                String attachmentName,
                                File file) throws MailException, MessagingException{
        sendHtmlMessage(emailTOAddresses, null, emailFROM, subject, templateName, model, bcc, attachmentName, file);
    }    
    
    public void sendHtmlMessage(String[] emailTOAddresses,
                                String emailFROM,
                                String subject,
                                String templateName,
                                Map model,
                                String attachmentName,
                                File file) throws MailException, MessagingException{
            
        sendHtmlMessage(emailTOAddresses, null, emailFROM, subject, templateName, model, null, attachmentName, file);
            
    }    

    /**
     * Send a simple message with pre-populated values.
     * @param msg
     */
    public void send(SimpleMailMessage msg) {
        //try {
            mailSender.send(msg);
        /*} catch (MailException ex) {
            //log it and go on
            log.error(ex.getMessage());
        }*/
    }

    /**
     * Convenience method for sending messages with attachments.
     * 
     * @param emailAddresses
     * @param resource
     * @param bodyText
     * @param subject
     * @param attachmentName
     * @throws MessagingException
     * @author Ben Gill
     */
    public void sendMessage(String[] emailAddresses,
                            ClassPathResource resource, String bodyText,
                            String subject, String attachmentName)
    throws MessagingException {
        MimeMessage message =
            ((JavaMailSenderImpl) mailSender).createMimeMessage();

        // use the true flag to indicate you need a multipart message
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(emailAddresses);
        helper.setText(bodyText);
        helper.setSubject(subject);

        helper.addAttachment(attachmentName, resource);

        ((JavaMailSenderImpl) mailSender).send(message);
    }
    
    
    public void sendMessage(String[] emailAddresses,
                            ByteArrayResource resource, String bodyText,
                            String subject, String attachmentName)
    throws MessagingException {
        MimeMessage message =
            ((JavaMailSenderImpl) mailSender).createMimeMessage();

        // use the true flag to indicate you need a multipart message
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(emailAddresses);
        helper.setFrom(defFrom);
        helper.setText(bodyText);
        helper.setSubject(subject);

        helper.addAttachment(attachmentName, resource);

        ((JavaMailSenderImpl) mailSender).send(message);
    }    

    public String getDefFrom() {
        return defFrom;
    }

    public void setDefFrom(String defFrom) {
        this.defFrom = defFrom;
    }
}
