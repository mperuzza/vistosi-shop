package com.ateikon.service;

import java.util.Properties;
import java.util.Date;

import javax.mail.Address;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.Socket;
import java.net.ServerSocket;
import java.net.SocketTimeoutException;

import java.sql.SQLException;
import com.ateikon.common.Atk_sql;
import com.ateikon.common.Costanti_comm;


public abstract class Service extends Thread {
    

    public Service (){
        
        super();
    }


    public Service (String serviceName, String[] args){
        
            
        super();

        // lettura dei parametri di connessione SQL

        serviceName = serviceName.toLowerCase();

        Costanti_comm costanti_comm = new Costanti_comm();
        atk_sql = new Atk_sql();

        try {
            
            atk_sql.dbConnection();

            atk_sql.cdazie = "01";
            atk_sql.cddipa = "0000";
            atk_sql.profil = serviceName;

            atk_sql.setProfilo((Atk_sql) costanti_comm);

            String  ls_port      = costanti_comm.getCostvalue(serviceName+"_port");
            String  ls_host      = costanti_comm.getCostvalue(serviceName+"_host");
            String  ls_loop_time = costanti_comm.getCostvalue(serviceName+"_loop_time");

            debugger("*** serviceName: "+serviceName);
            debugger("PORT      : "+ls_port);
            debugger("HOST      : "+ls_host);
            debugger("LOOP_TIME : "+ls_loop_time);

            try {

                PORT = Integer.parseInt(ls_port);
                
            }catch(Exception ex_port){
                PORT = 8010;
                debugger("\tdef: PORT      : "+PORT);
            }

            if (!ls_host.equals("")){
                HOST = ls_host;
            }else {
                HOST = "localhost";
                debugger("\tdef: HOST      : "+HOST);
            }

            try {

                LOOP_TIME = Integer.parseInt(ls_loop_time);
                
            }catch(Exception ex_port){
                LOOP_TIME = 0;
                debugger("\tdef: LOOP_TIME : "+LOOP_TIME);
            }

            CLIENTE =  costanti_comm.getCostvalue("cliente");

            costanti_comm.close();

            if (args[0].equals(START)) {
                
                // startup del servizio
    
                serverSocket = new ServerSocket(PORT);
                serverSocket.setSoTimeout(LOOP_TIME); 

                debugger("Startup OK");

                run();
            }


            if (args[0].equals(STOP)) {
                
                // apro una connessione client

                debugger("Stopping service ...");

                Socket              clientSocket = null;
                DataOutputStream    os = null;
                DataInputStream     is = null;

                clientSocket = new Socket(HOST, PORT);
                os = new DataOutputStream(clientSocket.getOutputStream());
                is = new DataInputStream(clientSocket.getInputStream());

                if (clientSocket != null && os != null && is != null) {
                    
                    os.writeBytes(args[0]+"\n"); 

                    String responseLine;

                    while ((responseLine = is.readLine()) != null) {
                      
                        if (responseLine.equals(OK)) {
                            break;
                        }
                    }

                    os.close();
                    is.close();
                    clientSocket.close();   

                }
    
            }



            if (args[0].equals(STATUS)) {
                
                debugger("Status service ...");

                this.RC_STATUS = "";

                Socket              clientSocket = null;
                DataOutputStream    os = null;
                DataInputStream     is = null;

                try {
                    
                    clientSocket = new Socket(HOST, PORT);
                    os = new DataOutputStream(clientSocket.getOutputStream());
                    is = new DataInputStream(clientSocket.getInputStream());
    

                    if (clientSocket != null && os != null && is != null) {
                        
                        this.RC_STATUS = RC_STATUS_ATTIVO;
                        
                        // PrintStream srv_os_status = new PrintStream( os );
                        // srv_os_status.println(STATUS);

                        is.close();
                        os.close();
                        clientSocket.close();   
    
                    }else {
                        
                        this.RC_STATUS = RC_STATUS_INATTIVO;
                    }

                }catch (java.net.ConnectException ex){
                    // connessione non Riuscita

                    // ex.printStackTrace();

                    this.RC_STATUS = RC_STATUS_INATTIVO;
                }


                debugger(" "+this.RC_STATUS);

            }



        }catch (Exception ex){
            
            debugger("*********************************");
            debugger("  Parametri Error:");
            debugger("*********************************");
            ex.printStackTrace();
            debugger("*********************************");

            System.gc();
        }

    }


    /***
    *  run
    **/



    public void run (){

        String rc_sk = ""; 

        String sql_err = "";
        String message = "";

        java.util.Date oggi_date = null;
        
        
        oggi_date = new java.util.Date(System.currentTimeMillis( ));
        int oggi_sa = oggi_date.getYear( ) + 1900;
        int oggi_mm = oggi_date.getMonth( ) + 1;
        int oggi_gg = oggi_date.getDate( );
        int oggi_hh = oggi_date.getHours( );
        int oggi_mi = oggi_date.getMinutes( );
        int oggi_ss = oggi_date.getSeconds( );

        String adesso = oggi_gg
                      + "/" + oggi_mm
                      + "/" + oggi_sa
                      + " " + oggi_hh
                      + ":" + oggi_mi
                      + ":" + oggi_ss
                      ;

        debugger();
        debugger("*** Inizio elaborazione "+ adesso);
        debugger();
        

        try{

            do {


                try {
                    
                    if (atk_sql.m_connection == null){
                        
                        atk_sql.dbConnection();

                        debugger( "... Connessione DB OK");
                        message = "";

                    }
                    
                    this.elabora();
                    
                    System.gc();


                }catch (SQLException sql_ex){
                    
                    // --- 13/04/2006: in caso di Errore non blocco il 
                    //                 servizio:
                    //                 - invio l'e-mail
                    //                 - stoppo il servizio per un'ora

                    try {
                        
                        atk_sql.closeConnection();
                        
                    }catch(Exception ex_dbclose){}

                    sql_ex.printStackTrace();

                    sendFatalError( sql_ex.toString() );

                    if (LOOP_TIME > 0){
                        
                        debugger();
                        debugger();
                        debugger();
                        debugger();
                        debugger("Attendo ... 1 ora");

                        sleep(3600000);

                        try {
                            
                            atk_sql.dbConnection();
                            
                        }catch(Exception ex_dbconn){}


                        
                    }

                }     // Catch

                // il metodo accept aspetta LOOP_TIME 

                if (LOOP_TIME == 0){
                    
                    rc_sk = STOP;

                }else {
                    
                    rc_sk = readSocket();
                }


            }while(!rc_sk.equals(STOP));

            closeSocket();


        }catch (Exception e){


            sendFatalError(e.toString());


            e.printStackTrace();
            System.gc();

        }finally{


            oggi_date = new java.util.Date(System.currentTimeMillis( ));

            oggi_sa = oggi_date.getYear( ) + 1900;
            oggi_mm = oggi_date.getMonth( ) + 1;
            oggi_gg = oggi_date.getDate( );
            oggi_hh = oggi_date.getHours( );
            oggi_mi = oggi_date.getMinutes( );
            oggi_ss = oggi_date.getSeconds( );

            adesso = oggi_gg
                   + "/" + oggi_mm
                   + "/" + oggi_sa
                   + " " + oggi_hh
                   + ":" + oggi_mi
                   + ":" + oggi_ss
                   ;

            debugger("*** Fine elaborazione "+ adesso);
            debugger();

        }




    }





    public String readSocket() throws Exception {
    
        String line = "";

        // Create a socket object from the ServerSocket to listen and accept 
        // connections.
        // Open input and output streams
        
        try {

            clientSocket = serverSocket.accept();
    
            srv_is = new DataInputStream(clientSocket.getInputStream());
            srv_os = new PrintStream(clientSocket.getOutputStream());
    
            line = srv_is.readLine();

            if (line == null) line = "";

            srv_os.println(OK);

            srv_is.close();
            srv_os.close();
            clientSocket.close();


        }catch(SocketTimeoutException ex){
            
        }


        return line;
  
    }

    public void closeSocket() throws Exception {
    
        // close Connessione al DB

        try {
            
            atk_sql.closeConnection();

        }catch(Exception ex){
            
        }

        serverSocket.close();
        
    }







    public void debugger(String debug){
        System.err.println(debug);
    }

    public void debugger(){
        
        debugger("");
    }


    public void debugger(Process proc ) throws Exception {

        BufferedReader in  = new BufferedReader(new InputStreamReader(proc.getInputStream()),5000);
        String a = new String("");
        int ind = 0;
        while (( a = in.readLine()) != null){
            debugger(a);
            ind++;
        }
        in.close();

        BufferedReader b_err  = new BufferedReader(new InputStreamReader(proc.getErrorStream()),5000);
        a = new String("");
        ind = 0;
        while (( a = b_err.readLine()) != null){
            debugger(a);
            ind++;
        }
        b_err.close();


    }


    public String getStatus() throws Exception {
        
        if (this.RC_STATUS.equals("")){
            throw new Exception("Status NON impostato - passare il parametro \"status\"");
        }
    
        return this.RC_STATUS;
    }





    public void sendFatalError(String errore) {
        
        Properties      props = null;
        Session         session = null;
        Message         message = null;
        Transport       transport = null;
        MimeBodyPart    bodyPart = null ;
        Multipart       multipart = null;

        try {
            
            props = new java.util.Properties();

            props.load(getClass().getResourceAsStream( "/ateikon_sql.properties" ));

            String mittente    = props.getProperty("fatalError.mail.mittente");
            String dest_to     = props.getProperty("fatalError.mail.dest_to"  );
            String smtp_host   = props.getProperty("fatalError.mail.smtp"  );

            String oggetto = CLIENTE+": ATTENZIONE Errore NON PREVISTO Afs_service.java!";

            String contenuto = "";

            contenuto += "HOST: "+HOST+"\n";
            contenuto += "PORT: "+PORT+"\n";
            contenuto += "\n\n\n"+errore;

            // ---

            props = null;


            props = System.getProperties();
            props.put("mail.smtp.host", smtp_host);
            props.put("mail.smtp.auth", "false");


            session = Session.getDefaultInstance(props, null);
            message = new MimeMessage(session);
    
            
            message.setFrom(new InternetAddress(mittente));
            message.setSentDate(new Date());
            message.setSubject(oggetto);

            Address[] dest = null;
            dest = new Address[1];
            dest[0] = new InternetAddress(dest_to);

            message.setRecipients(Message.RecipientType.TO, dest);

            multipart       = new MimeMultipart();
            
            bodyPart = new MimeBodyPart();
            bodyPart.setText(contenuto);
            multipart.addBodyPart(bodyPart);

            message.setContent(multipart);
            message.saveChanges();
    
            transport = session.getTransport("smtp");
            transport.connect(smtp_host, "", "");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();


        }catch (Exception ex){

            debugger(" ");
            debugger(" ");
            debugger(" ");
            debugger(" ");
            debugger("*********************************");
            debugger("  Parametri Error:");
            debugger("*********************************");
            
            ex.printStackTrace();
        
            debugger("*********************************");

            


        }

    }



    /**

        ABSTRACT
    */

    public abstract void elabora() throws Exception ;





    // Variabili 

        
    public Atk_sql  atk_sql = null;
    public int      PORT = 0;
    public int      LOOP_TIME = 0;
    public String   HOST = "";

    public DataInputStream srv_is = null;
    public PrintStream     srv_os = null;
     

    
    public ServerSocket serverSocket = null;
    public Socket       clientSocket = null;

    public String       CLIENTE      = "";


    // Costanti

    public static String START = "start";
    public static String STOP  = "stop";
    public static String STATUS = "status";
    public static String OK    = "OK";

    public String RC_STATUS = "";

    public static String RC_STATUS_ATTIVO    = "Attivo";
    public static String RC_STATUS_INATTIVO  = "NON Attivo";
    



    public String ORA_ERR_CONN = "17002";
    public String SYB_ERR_CONN = "JZ006";










}

