package com.ateikon.service;


import java.util.Vector;
import java.util.Properties;
import java.util.Date;

import javax.activation.FileDataSource;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Address;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;


import java.io.File;
import java.io.FileInputStream;

import com.ateikon.common.Atk_sql;
import com.ateikon.common.Costanti_comm;



public class Srv_sendmail extends Atk_sql implements java.io.Serializable {

    public Srv_sendmail() {
        super();
    }




    public boolean invia( String  mittente    
                        , String  as_dest_TO     
                        , String  oggetto      
                        , String  contenuto   
                                                        ) throws Exception {
    
        Session         session = null;
        
        Vector  dest_TO = new Vector();

        dest_TO.add(as_dest_TO);

        return invia( mittente    
                    , dest_TO     
                    , null
                    , null
                    , oggetto      
                    , contenuto   
                    , null      
                    , null
                                );

                                                        
    }




    public boolean invia( String  mittente    
                        , Vector  dest_TO     
                        , Vector  dest_CC     
                        , Vector  dest_BCC    
                        , String  oggetto      
                        , String  contenuto   
                        , Vector  attach      
                        , Vector  attach_name 
                                                        ) throws Exception {

        Properties      props = null;
        Session         session = null;
        Message         message = null;
        Transport       transport = null;
        MimeBodyPart    bodyPart = null ;

        DataSource      source = null;
        Multipart       multipart = null;

        String adress_app = "";


        Costanti_comm costanti_comm = new Costanti_comm();

        setProfilo((Atk_sql) costanti_comm);

        String smtp_host = costanti_comm.getCostvalue("mail_smtp_host");
        String username  = costanti_comm.getCostvalue("mail_smtp_user");
        String password  = costanti_comm.getCostvalue("mail_smtp_password");

        if (mittente.equals("")){
            mittente = costanti_comm.getCostvalue("mail_mittente");
        }



        props = System.getProperties();
        props.put("mail.smtp.host", smtp_host);

        if (!username.equals("") && !password.equals("")){
            props.put("mail.smtp.auth", "true");
        }else {
            props.put("mail.smtp.auth", "false");
        }
        
        session = Session.getDefaultInstance(props, null);
        message = new MimeMessage(session);

        adress_app = mittente;
        message.setFrom(new InternetAddress(mittente));
        message.setSentDate(new Date());
        message.setSubject(oggetto);

        Address[] dest = null;
        if (dest_TO !=null && dest_TO.size()>0 ){

            dest = new Address[dest_TO.size()];
            for(int i=0; i<dest_TO.size(); i++) {
                adress_app = (String)dest_TO.elementAt(i);
                dest[i] = new InternetAddress(adress_app);
            }

            message.setRecipients(Message.RecipientType.TO, dest);
        }
        dest = null;
        if (dest_CC !=null && dest_CC.size()>0 ){

            dest = new Address[dest_CC.size()];
            for(int i=0; i<dest_CC.size(); i++){
                adress_app = (String)dest_CC.elementAt(i);
                dest[i] = new InternetAddress(adress_app);
            }

            message.setRecipients(Message.RecipientType.CC, dest);
        }
        dest = null;
        if (dest_BCC !=null && dest_BCC.size()>0 ){

            dest = new Address[dest_BCC.size()];
            for(int i=0; i<dest_BCC.size(); i++){
                adress_app = (String)dest_BCC.elementAt(i);
                dest[i] = new InternetAddress(adress_app);
            }

            message.setRecipients(Message.RecipientType.BCC, dest);
        }

        multipart       = new MimeMultipart();

        // inserisco il contenuto del messaggio

        if (contenuto.indexOf("<html") >= 0){

            bodyPart = new MimeBodyPart();
            bodyPart.setDataHandler(new DataHandler(contenuto, "text/html; charset=iso-8859-1"));
            multipart.addBodyPart(bodyPart);

        }else {

            bodyPart = new MimeBodyPart();
            bodyPart.setText(contenuto);
            multipart.addBodyPart(bodyPart);
        }





        // inserisco gli Attach se esistono
        for (int i=0; attach!=null && i<attach.size(); i++){

            bodyPart = new MimeBodyPart();
            source = new FileDataSource((String )attach.elementAt(i));
            bodyPart.setDataHandler(new DataHandler(source));
            bodyPart.setFileName((String )attach_name.elementAt(i));

            multipart.addBodyPart(bodyPart);
        }


        message.setContent(multipart);
        message.saveChanges();

        transport = session.getTransport("smtp");
        transport.connect(smtp_host, username, password);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();


        return true;

    }




    public String getModel(String path) throws Exception                {
 
		FileInputStream 	fileInputStream = null;
		File 				file = null;
		byte[] 				arr_buffer = null;
		StringBuffer		buffer = new StringBuffer("");
        String              testo = "";


		file = new File(path);

		if (!file.exists( ) || file.isDirectory( )){
			throw new Exception ("Il Modello non è un File: >>"+path+"<< ");
		}

		// Carico il buffer
		fileInputStream = new FileInputStream(file);

		arr_buffer = new byte[new Long(file.length( )).intValue()];
		fileInputStream.read( arr_buffer );
		fileInputStream.close();

		testo = new String(arr_buffer);

        return testo;
    }





}
