/*

    questo servizio monitorizza la registrazione al portale e-progen
    di un nuovo utente




    data la web.utente verifico se ci sono 
    richieste di pwd pendenti (dtricpwd is not null)

    - se web.utente.email = pgmr.unitalocali.email : 
        * invio mail
        * dtricpwd = null
        * dtpwd = oggi


    - se web.utente.email <> pgmr.unitalocali.email : 
        * invio mail all'amministratore di sistema 
          richiedendo di allineare le mail web.utente.emial <-> web.unitalocali.email
          o di togliere il flag richiesta pwd
          o di forzare l'invio mail

    - pgmr.unitalocali.email non è valorizzato : 
        * invio mail all'amministratore di sistema 
          richiedendo di sistemare l'anagrafica utente del gestionale
          ricordando che è possibile inibire questa mail togliendo il 
          flag dall'amministrazione utente

*/


package com.ateikon.service;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.Connection;

import java.util.Vector;


import com.ateikon.common.Atk_sql;
import com.ateikon.common.Costanti_comm;
import com.ateikon.common.Utente;
import com.ateikon.common.Unitalocali;
import com.ateikon.common.Archclie;
import com.ateikon.common.Archagen;
import com.ateikon.common.Nazioni;
import com.ateikon.common.Province;
// import com.ateikon.common.archforn;
import com.ateikon.common.Archenti;
import com.ateikon.util.StringUtils;
import com.ateikon.util.Atk_ctrl;

public class Srv_reguser extends Service {


    public Srv_reguser (String[] args){

        super("Srv_reguser", args);


        

    }


    public Srv_reguser (Atk_sql atk_sql){

        super ();
        
        super.atk_sql = atk_sql; 

    }

    /***
    *
    *   Implemento il Metodo Astratto
    **/



    public void elabora() throws Exception{

        int               ind     = 0;
        int               tot_rec = 0;
        PreparedStatement pstm    = null;
        ResultSet         rs      = null;

        String            sql = "";
        ResultSet         rs_box = null;


        Utente      utente      = new Utente();
        Archclie    archclie    = new Archclie();
        Archagen    archagen    = new Archagen();
        // Archforn    archforn    = new Archforn();
        Archenti    archenti    = new Archenti();
        Unitalocali unitalocali = new Unitalocali();


        atk_sql.setProfilo((Atk_sql) utente);
        atk_sql.setProfilo((Atk_sql) archclie);
        atk_sql.setProfilo((Atk_sql) archenti);
        atk_sql.setProfilo((Atk_sql) archagen);
        // atk_sql.setProfilo((Atk_sql) archforn);
        atk_sql.setProfilo((Atk_sql) unitalocali);


        String     fgricpwd = "S";

        String     cdutente  = "";
        String     tkclie    = "";
        String     tkforn    = "";
        String     cdagen    = "";
        String     email     = "";
        String     pariva    = "";
        String     fgweb     = "";
        Timestamp  dtpwd     = null;
        String     username  = "";
        String     ragsoc    = "";
        String     nome      = "";
        String     cognome   = "";

        String     dsutente   = "";
        String     fgfmailpwd = "";

        String     ragcog = "";
        String     ragnom = "";
        String     cognom = "";
        String     sesso  = "";
        Timestamp  dtnasc = null;

        String     indiri = "";
        String     cap    = "";
        String     comune = "";
        String     cdprov = "";
        String     cdnazi = "";
        String     ntelef = "";
        String     nfaxsi = "";

        String     cdente        = "";
        String     gest_email    = "";
        String     gest_pariva   = "";
        String     gest_dsutente = "";
        String     gest_username = "";


        // --- variabili d'appoggio

        String   ls_tipoutente  = "";
        String   sql_codice_m   = "";
        String   sql_descr      = "";
        String   token          = "";
        String   prefix         = "";
        boolean  mail_inviata   = false;


        // --- 

        int     count_cli = 0;        
        int     is_user_web = 0;        
        Vector  vec_tkclie = null;
        String  tkclie_app = "";







        rs = utente.getDtricpwd(fgricpwd);


        while (rs!=null && rs.next()){
            
            tkclie   = "";
            tkforn   = "";
            cdagen   = "";

            username = "";
            dsutente = "";
            ragsoc   = "";
            nome     = "";
            cognome  = "";

            pariva = "";
            email  = "";
            ragcog = "";
            ragnom = "";
            cognom = "";
            sesso  = "";
            dtnasc = null;

            indiri = "";
            cap    = "";
            comune = "";
            cdprov = "";
            cdnazi = "";
            ntelef = "";
            nfaxsi = "";
            fgfmailpwd  = "";


            gest_email    = "";
            gest_pariva   = "";
            gest_dsutente = "";
            gest_username = "";

            dtpwd = null;



            fgweb = "";
            ls_tipoutente = "";
            sql_codice_m   = "";
            sql_descr      = "";
            token = "";
            prefix = "";

            cdente = "";
            cdutente = "";

            mail_inviata = false;

            if (rs.getObject("cdutente"  )!=null ) cdutente   = rs.getString("cdutente");
            if (rs.getObject("dsutente"  )!=null ) dsutente   = rs.getString("dsutente");
            
            if (rs.getObject("fgweb"     )!=null ) fgweb      = rs.getString("fgweb");
            if (rs.getObject("tkclie"    )!=null ) tkclie     = rs.getString("tkclie");
            if (rs.getObject("tkforn"    )!=null ) tkforn     = rs.getString("tkforn");
            if (rs.getObject("cdagen"    )!=null ) cdagen     = rs.getString("cdagen");
            if (rs.getObject("pariva"    )!=null ) pariva     = rs.getString("pariva");
            if (rs.getObject("email"     )!=null ) email      = rs.getString("email");
            if (rs.getObject("dtpwd"     )!=null ) dtpwd      = rs.getTimestamp("dtpwd");
            if (rs.getObject("fgfmailpwd")!=null ) fgfmailpwd = rs.getString("fgfmailpwd");
            if (rs.getObject("username"  )!=null ) username   = rs.getString("username");

            if (rs.getObject("ragsoc"  )!=null ) ragsoc  = rs.getString("ragsoc");
            if (rs.getObject("nome"    )!=null ) nome    = rs.getString("nome");
            if (rs.getObject("cognome" )!=null ) cognome = rs.getString("cognome");

            email = email.trim();
            email = email.toLowerCase();

            if (dtpwd != null && !email.equals("")){
                
                // la mail è stata inviata in precedenza sono autorizzato 
                // ad inoltrarla

                this.mail_benvenuto(cdutente);
                mail_inviata = true;

                continue;
            }




            
            // fgweb in linea di principio viene valorizzato 
            // in base dalla registrazione di un nuovo cliente dal web; 
            // nel web viene richiesto:
            //      - "Sei un cliente [dell'azienda]? Clicca qui per richiedere Utente/password"
            //      - "Registrati per diventare un nostro cliente"


            if (!fgweb.equals("S")) fgweb = "N";

            if (!tkclie.equals("") 
            ||  !tkforn.equals("") 
            ||  !cdagen.equals("") 
                ){
                
                // forzo la casistica gestionale 
                // per la gestione dell'invio mail

                fgweb = "N";
            }


            if (tkclie.equals("") 
              &&  tkforn.equals("") 
                &&  cdagen.equals("") 
                 &&  !pariva.equals("") 
                ){
                
                // tento la ricerca del cliente gestionale 

                fgweb = "S";
            }

            
            /*
                se è una registrazione WEB verifico se è stato inserito 
                l'utente nel gestionale in base alla partita iva:

                - pariva NON trovata:
                    * invio segnalazione nuovo utente all'amministrazione del sistema

                - pariva trovata --> cdente:
                    - Verifico se ci sono clienti non associati ad utenti

                        * non ci sono clienti disponibili ... segnalazione nuovo utente all'amministrazione 

                        * ci sono clienti disponibili 
                            se ne trovo 1 associo
                            se + di uno segnalo l'associazione del cliente manuale

            */

            if (fgweb.equals("S")){
                

                vec_tkclie = new Vector();
                is_user_web = 0;        
                count_cli = 0;        


                rs_box = archclie.getPariva(pariva);

                while (rs_box!=null && rs_box.next()){
                
                    is_user_web = 0;
                    
                    
                    cdente = rs_box.getString("cdente");
                    tkclie_app = rs_box.getString("tkclie");
    
                    count_cli += 1;

                    is_user_web = utente.countTkclie(tkclie_app);
                    
                    if (is_user_web > 0){
                        // utente web
                    }else {
                       vec_tkclie.add(tkclie_app) ;
                    }
                    
                }
                
                if (count_cli == 0){
                    
                    this.mail_new_user(cdutente);
                    mail_inviata = true;

                }else if (count_cli == 1){
                
                    tkclie = tkclie_app;

                    this.setCliente(cdutente, tkclie);
                    fgweb = "N";

                }else {
                   
                    if (vec_tkclie.size() == 0){
                        
                        this.mail_new_user(cdutente);
                        mail_inviata = true;


                    }else if (vec_tkclie.size() == 1){

                        tkclie = (String)vec_tkclie.elementAt(0);
                        this.setCliente(cdutente, tkclie);
                        fgweb = "N";
                        
                    }else {
                        
                        this.mail_troppi_clienti(cdutente, vec_tkclie);
                        mail_inviata = true;
                    }
                }

                
                archclie.close();


                if (fgweb.equals("N") && tkclie.equals("")){
                    
                    System.err.println("");
                    System.err.println("");
                    System.err.println("------------------------------------------------");
                    System.err.println("    Gest. Registrazione nuovo Cliente WEB");
                    System.err.println("------------------------------------------------");
                    System.err.println("");
                    System.err.println("cdutente "+cdutente);
                    System.err.println("         Stato fgweb non previsto ");
                    System.err.println("------------------------------------------------");
                    System.err.println("");

                    throw new Exception("Stato fgweb non previsto! Controllare il log");
                }

            }   // fine if (fgweb.equals("S")){



            /*
                NON è una registrazione WEB. quindi è un cliente/agente/fornitore del 
                gestionale ed è stato identificato come tale
                
                qualifico l'utente 

                    - cliente 
                    - agente 
                    - fornitore

                ricavo l'ente --> unitalocali --> email gest.

                se l'email gest. == email web
                
                    - invio mail con estremi 
                    - imposto la dtpwd
                    - imposto a null la dtripwd

                se l'email gest. != email web
                    
                    - se dtpwd is not null implica che ho spedito la mail con la mail web
                      quindi iniltro la mail con gli estremi
                    - se la dtpwd is null 
                    
                        se il fgfmailpwd = "S"
                            * invio mail con estremi 
                            * imposto la dtpwd
                            * imposto a null la dtripwd

                        se il fgfmailpwd = "N"
                            invio mail all'amministratore richiedendo di 
                            sistemare i dati:
                              * allinea la mail del web a quella del gestionale
                              * allinea la mail della sede Legale a quella del web
                              * toglie il check richiesta pwd
                              * forza l'invio mail con la mail del web

            */



            if (fgweb.equals("N")){
                
                ls_tipoutente = "";
                sql_codice_m  = "";
                sql_descr     = "";
                

                if (!tkclie.equals("")){
                    
                    ls_tipoutente = this.CLIENTE;

                }else if (!tkforn.equals("")){
                    
                    ls_tipoutente = this.FORNITORE;
                
                }else if (!cdagen.equals("")){
                    
                    ls_tipoutente = this.AGENTE;

                }else {
                    
                    System.err.println("");
                    System.err.println("");
                    System.err.println("------------------------------------------------");
                    System.err.println("    Gest. Registrazione Cliente Gestionale");
                    System.err.println("------------------------------------------------");
                    System.err.println("");
                    System.err.println("cdutente "+cdutente);
                    System.err.println("         Tipo Utente non Previsto");
                    System.err.println("------------------------------------------------");
                    System.err.println("");

                    throw new Exception("Tipo Utente non Previsto! Controllare il log");
                        
                }


                if(ls_tipoutente.equals(this.CLIENTE)){
                    
                    rs_box = archclie.getCliente(tkclie);

                    sql_codice_m = "cdclie_m";
                    sql_descr    = "dsclie";
                    token = tkclie;
                    prefix = "cl";

                    
                }else if(ls_tipoutente.equals(this.FORNITORE)){
                    
                    /*
                    rs_box = archforn.getCdagen(tkclie);

                    sql_codice_m = "cdclie_m";
                    sql_descr    = "dsclie";
                    token = tkforn;
                    prefix = "fo";
                    */
                    System.err.println("Gestione Fornitore non implementato");

                }else if(ls_tipoutente.equals(this.AGENTE)){
                    
                    rs_box = archagen.getCdagen(cdagen);

                    sql_codice_m = "cdagen_m";
                    sql_descr    = "dsagen";
                    prefix = "ag";

                    token = cdagen;
                }


                if(rs_box!=null && rs_box.next()) {
                    
                   
                    cdente = rs_box.getString("cdente");

                    if (rs_box.getObject(sql_codice_m)!=null) gest_username = prefix + rs_box.getString(sql_codice_m);
                    if (rs_box.getObject(sql_descr   )!=null) gest_dsutente = rs_box.getString(sql_descr);
                }

                archagen.close();
                archclie.close();
                // archforn.close();

                // --- ENTI

                rs_box = archenti.getKey(cdente);


                if(rs_box!=null && rs_box.next()) {
                        
                    if (rs_box.getObject("pariva")!=null) gest_pariva = rs_box.getString("pariva");
                    if (rs_box.getObject("ragcog")!=null) ragcog = rs_box.getString("ragcog");
                    if (rs_box.getObject("ragnom")!=null) ragnom = rs_box.getString("ragnom");
                    if (rs_box.getObject("cognom")!=null) cognom = rs_box.getString("cognom");
                    if (rs_box.getObject("sesso" )!=null) sesso  = rs_box.getString("sesso" );
                    if (rs_box.getObject("dtnasc")!=null) dtnasc = rs_box.getTimestamp("dtnasc");

                } 
                archenti.close();


                // --- UNITALOCALI

                rs_box = unitalocali.getSede_legale(cdente);


                if(rs_box!=null && rs_box.next()) {
                    
                    if (rs_box.getObject("indiri")!=null) indiri     = rs_box.getString("indiri");
                    if (rs_box.getObject("cap")!=null)    cap        = rs_box.getString("cap");
                    if (rs_box.getObject("comune")!=null) comune     = rs_box.getString("comune");
                    if (rs_box.getObject("cdprov")!=null) cdprov     = rs_box.getString("cdprov");
                    if (rs_box.getObject("cdnazi")!=null) cdnazi     = rs_box.getString("cdnazi");
                    if (rs_box.getObject("ntelef")!=null) ntelef     = rs_box.getString("ntelef");
                    if (rs_box.getObject("nfaxsi")!=null) nfaxsi     = rs_box.getString("nfaxsi");
                    if (rs_box.getObject("email")!=null)  gest_email = rs_box.getString("email");
                }
                    
                unitalocali.close();

                gest_email = gest_email.trim();
                gest_email = gest_email.toLowerCase();
                
                if (email.equals(""))    email    = gest_email;
                if (username.equals("")) username = gest_username;

                tot_rec = utente.setEmail   (cdutente, email);
                tot_rec = utente.setUsername(cdutente, username);

                utente.m_connection.commit();


                if (gest_email.equals(email)){
                    
                    this.mail_benvenuto(cdutente);
                    mail_inviata = true;
                    
                }
                
                if (!gest_email.equals(email)){
                    
                    if (fgfmailpwd.equals("S")){
                        
                        this.mail_benvenuto(cdutente);
                        mail_inviata = true;
                    }else {
                        
                        this.mail_allinea(cdutente);
                        mail_inviata = true;
                    }
                }






            }   // fine if (fgweb.equals("N")){



            if (!mail_inviata){
                
                System.err.println("");
                System.err.println("");
                System.err.println("------------------------------------------------");
                System.err.println("    Gest. Registrazione Cliente Gestionale");
                System.err.println("------------------------------------------------");
                System.err.println("");
                System.err.println("cdutente "+cdutente);
                System.err.println("         Casistica non prevista: nessuna mail inviata");
                System.err.println("------------------------------------------------");
                System.err.println("");

                throw new Exception("Casistica non prevista: nessuna mail inviata! Controllare il log");
            }



        } // FINE while rs  




        return;


    }


    public void setCliente(String cdutente, String tkclie) throws Exception{
        
        int               ind     = 0;
        int               tot_rec = 0;
        PreparedStatement pstm    = null;
        ResultSet         rs      = null;

        Utente utente = new Utente();


        atk_sql.setProfilo((Atk_sql) utente);


        tot_rec = utente.setTkclie(cdutente, tkclie);

        if (tot_rec > 0){
            
            utente.m_connection.commit();
            
        }else {
            throw new Exception("setCliente cdutente "+cdutente+" Errato!");
        }
        

        
    }







    public void mail_benvenuto(String cdutente) throws Exception{
        
        int               ind     = 0;
        int               tot_rec = 0;
        PreparedStatement pstm    = null;
        ResultSet         rs      = null;

        String            sql = "";
        ResultSet         rs_box = null;




        Srv_sendmail srv_sendmail = new Srv_sendmail();
        Costanti_comm costanti_comm = new Costanti_comm();

        Utente utente = new Utente();



        atk_sql.setProfilo((Atk_sql) srv_sendmail);
        atk_sql.setProfilo((Atk_sql) costanti_comm);

        atk_sql.setProfilo((Atk_sql) utente);


        String path_modello = costanti_comm.getCostvalue("reguser_mod_benvenuto");
        String url_site     = costanti_comm.getCostvalue("url_e-progen"         );
        String url_img      = costanti_comm.getCostvalue("mail_url_path"        );
        String[] cdutente_mail_bcc = new String[3];
        cdutente_mail_bcc[0] = costanti_comm.getCostvalue("reguser_mail_to_1");
        cdutente_mail_bcc[1] = costanti_comm.getCostvalue("reguser_mail_to_2");
        cdutente_mail_bcc[2] = costanti_comm.getCostvalue("reguser_mail_to_3");







        String username = "";
        String dsutente = "";
        String pwd      = "";
        String email    = "";
        String tkclie   = "";
        String tkforn   = "";
        String cdagen   = "";




        rs = utente.getKey(cdutente);

        if (rs!=null && rs.next()){

            if (rs.getObject("username"  )!=null ) username   = rs.getString("username");
            if (rs.getObject("pwd"       )!=null ) pwd        = rs.getString("pwd");
            if (rs.getObject("dsutente"  )!=null ) dsutente   = rs.getString("dsutente");
            if (rs.getObject("email"     )!=null ) email      = rs.getString("email");
            if (rs.getObject("tkclie"    )!=null ) tkclie    = rs.getString("tkclie");
            if (rs.getObject("tkforn"    )!=null ) tkforn    = rs.getString("tkforn");
            if (rs.getObject("cdagen"    )!=null ) cdagen    = rs.getString("cdagen");
        }

        boolean lb_error = false;
        String  message  = "";


        if (username.equals("")){
            
            message = "Username NON impostato \n";
            lb_error = true;

        }

        if (email.equals("")){
            
            message = "Email non Impostata \n";
            lb_error = true;
        }

        if (tkclie.equals("") && tkforn.equals("") && cdagen.equals("")){
            
            message = "Questo utente non è Cliente ne Fornitore, ne Agente !!! \n";
            lb_error = true;
        }


        
        
        if (lb_error){
        
            String ls_cliente = costanti_comm.getCostvalue("cliente");
            
            String contenuto_err = "ERRORE!!!\n";
            String oggetto_err   = "Mail Benvenuto non Inviata - "+ls_cliente;

            Vector  dest_TO_err = new Vector();
            dest_TO_err.add("atkim@ateikon.com");

            contenuto_err += "Mail di benvenuto per l'utente cdutente = "+cdutente+" \n";
            contenuto_err += "NON è stata inviata per il seguente motivo:  \n";
            contenuto_err += "\n";
            contenuto_err += message;
            contenuto_err += "\n";
            contenuto_err += "\n";
            contenuto_err += "dati web.utente: \n";
            contenuto_err += "cdutente *"+cdutente+"* \n";
            contenuto_err += "dsutente *"+dsutente+"* \n";
            contenuto_err += "email    *"+email+"* \n";
            contenuto_err += "\n";
            contenuto_err += "\n";
            contenuto_err += "Controllare la procedura srv_reguser per il Cliente: "+ls_cliente+" \n";
            contenuto_err += "\n";
            contenuto_err += "\n";


            srv_sendmail.invia( ""    
                              , dest_TO_err     
                              , null     
                              , null
                              , oggetto_err      
                              , contenuto_err   
                              , null      
                              , null 
                                                );


            return;

        }   // FINE if (lb_error){


        


        Vector attach = null;
        Vector attach_name = null;


        Vector  dest_TO = new Vector();



        if (!email.equals("")){
            
            dest_TO.add(email);

        }


        String ls_email_bcc = "";
        Vector dest_BCC = new Vector();

        for (int i=0; cdutente_mail_bcc!=null && i<cdutente_mail_bcc.length; i++){
            
            ls_email_bcc = ""; 

            rs = utente.getKey(cdutente_mail_bcc[i]);

            if (rs!=null && rs.next() && rs.getObject("email")!=null){
                    
                ls_email_bcc = rs.getString("email");

                ls_email_bcc = ls_email_bcc.trim();
                ls_email_bcc = ls_email_bcc.toLowerCase();

                if (!ls_email_bcc.equals("")){
                    
                    dest_BCC.add(ls_email_bcc);
                }
            }
            utente.close();

        }






        String contenuto = srv_sendmail.getModel(path_modello);

        contenuto = StringUtils.replace(contenuto, "@username@"    , username);
        contenuto = StringUtils.replace(contenuto, "@dsutente@"    , dsutente);
        contenuto = StringUtils.replace(contenuto, "@pwd@"         , pwd     );
        contenuto = StringUtils.replace(contenuto, "@url_e-progen@", url_site);
        contenuto = StringUtils.replace(contenuto, "../img"        , url_site+"/img");


        String mittente  = "";  // imposto il mittente di default
        String oggetto = costanti_comm.getCostvalue("reguser_oggetto_benvenuto");

        if (oggetto.equals("")) oggetto = "Estremi Utente";

        boolean lb_ = srv_sendmail.invia( mittente    
                                        , dest_TO     
                                        , null     
                                        , dest_BCC
                                        , oggetto      
                                        , contenuto   
                                        , attach      
                                        , attach_name 
                                                          );


        if (lb_){
            
            // annullo la richiesta invio mail

            tot_rec = utente.setDtricpwd(cdutente, null);

            if (tot_rec > 0){
                
                utente.m_connection.commit();
                
            }else {
                throw new Exception("setDtricpwd cdutente "+cdutente+" Errato!");
            }


            // imposto la data invio mail 

            tot_rec = utente.setDtpwd(cdutente, oggi);

            if (tot_rec > 0){
                
                utente.m_connection.commit();
                
            }else {
                throw new Exception("setDtpwd cdutente "+cdutente+" Errato!");
            }


            // annullo un'eventuale forzatura di invio mail

            tot_rec = utente.setFgfmailpwd(cdutente, "");
            
            if (tot_rec > 0){
                
                utente.m_connection.commit();
                
            }else {
                throw new Exception("setDtpwd cdutente "+cdutente+" Errato!");
            }



        }




    }






    public void mail_new_user(String cdutente) throws Exception{
        
        int               ind     = 0;
        int               tot_rec = 0;
        PreparedStatement pstm    = null;
        ResultSet         rs      = null;

        String            sql = "";
        ResultSet         rs_box = null;



        Srv_sendmail srv_sendmail = new Srv_sendmail();
        Costanti_comm costanti_comm = new Costanti_comm();

        Utente utente = new Utente();
        Province province = new Province();
        Nazioni nazioni = new Nazioni();

        Atk_ctrl atk_ctrl = new Atk_ctrl();


        atk_sql.setProfilo((Atk_sql) srv_sendmail);
        atk_sql.setProfilo((Atk_sql) costanti_comm);

        atk_sql.setProfilo((Atk_sql) utente);
        atk_sql.setProfilo((Atk_sql) province);
        atk_sql.setProfilo((Atk_sql) nazioni);


        String   path_modello = costanti_comm.getCostvalue ("reguser_mod_newuser");
        String   url_site     = costanti_comm.getCostvalue ("url_e-progen");
        String   url_reguser  = costanti_comm.getCostvalue ("url_registrazione");
        String[] cdutente_mail_to = new String[3];
        cdutente_mail_to[0] = costanti_comm.getCostvalue("reguser_mail_to_1");
        cdutente_mail_to[1] = costanti_comm.getCostvalue("reguser_mail_to_2");
        cdutente_mail_to[2] = costanti_comm.getCostvalue("reguser_mail_to_3");
        String url_img        = costanti_comm.getCostvalue("mail_url_path");

        url_reguser += "?cdutente="+cdutente;





        String username     = "";
        String dsutente     = "";
        String pwd          = "";
                            
        String nome         = "";
        String cognome      = "";
        String ragsoc       = "";
        String pariva       = "";
        String cfisc        = "";
        String indiri       = "";
        String cap          = "";
        String comune       = "";
        String cdprov       = "";
        String cdprov_m     = "";
        String cdnazi       = "";
        String dsnazi       = "";
        String email        = "";
        String telefono     = "";
        String fax          = "";
        String cellulare    = "";
        String sitointernet = "";
        Timestamp dtinse    = null;
        String ls_dtinse    = "";



        rs = utente.getKey(cdutente);

        if (rs!=null && rs.next()){

            if (rs.getObject("username"  )!=null ) username   = rs.getString("username");
            if (rs.getObject("pwd"       )!=null ) pwd        = rs.getString("pwd");
            if (rs.getObject("dsutente"  )!=null ) dsutente   = rs.getString("dsutente");

            if (rs.getObject("nome"      )!=null ) nome       = rs.getString("nome");
            if (rs.getObject("cognome"   )!=null ) cognome    = rs.getString("cognome");
            
            if (rs.getObject("ragsoc"         )!=null ) ragsoc       = rs.getString("ragsoc"      );
            if (rs.getObject("pariva"         )!=null ) pariva       = rs.getString("pariva"      );
            if (rs.getObject("cfisc"          )!=null ) cfisc        = rs.getString("cfisc"       );
            if (rs.getObject("indiri"         )!=null ) indiri       = rs.getString("indiri"      );
            if (rs.getObject("cap"            )!=null ) cap          = rs.getString("cap"         );
            if (rs.getObject("comune"         )!=null ) comune       = rs.getString("comune"      );
            if (rs.getObject("cdprov"         )!=null ) cdprov       = rs.getString("cdprov"      );
            if (rs.getObject("cdnazi"         )!=null ) cdnazi       = rs.getString("cdnazi"      );
            if (rs.getObject("email"          )!=null ) email        = rs.getString("email"       );
            if (rs.getObject("telefono"       )!=null ) telefono     = rs.getString("telefono"    );
            if (rs.getObject("fax"            )!=null ) fax          = rs.getString("fax"         );
            if (rs.getObject("cellulare"      )!=null ) cellulare    = rs.getString("cellulare"   );
            if (rs.getObject("sitointernet"   )!=null ) sitointernet = rs.getString("sitointernet");
            if (rs.getObject("dtinse"   )!=null ) dtinse = rs.getTimestamp("dtinse");

            ls_dtinse = atk_ctrl.getDatetime(dtinse);

            cdprov_m = province.getDescr_m(cdprov);
            dsnazi = nazioni.getDescr(cdnazi);



            pwd = "********";
        }



        Vector attach = null;
        Vector attach_name = null;


        String  ls_email_to = "";
        Vector  dest_TO = new Vector();

        for (int i=0; cdutente_mail_to!=null && i<cdutente_mail_to.length; i++){
            
            ls_email_to = ""; 

            rs = utente.getKey(cdutente_mail_to[i]);

            if (rs!=null && rs.next() && rs.getObject("email")!=null){
                    
                ls_email_to = rs.getString("email");

                ls_email_to = ls_email_to.trim();
                ls_email_to = ls_email_to.toLowerCase();

                if (!ls_email_to.equals("")){
                    
                    dest_TO.add(ls_email_to);
                }
            }
            utente.close();

        }

        if (dest_TO.size() <=0){
            throw new Exception("Impostare gli utenti che gestiscono le registrazioni.");
        }





        String contenuto = srv_sendmail.getModel(path_modello);
        
        contenuto = StringUtils.replace(contenuto, "@cdutente@"    , cdutente);
        contenuto = StringUtils.replace(contenuto, "@username@"    , username);
        contenuto = StringUtils.replace(contenuto, "@dsutente@"    , dsutente);
        contenuto = StringUtils.replace(contenuto, "@pwd@"         , pwd     );
        contenuto = StringUtils.replace(contenuto, "@url_e-progen@", url_site);
        contenuto = StringUtils.replace(contenuto, "@url_reguser@" , url_reguser);
        contenuto = StringUtils.replace(contenuto, "../img"        , url_site+"/img");
        

        contenuto = StringUtils.replace(contenuto, "@ragsoc@"      , ragsoc      );
        contenuto = StringUtils.replace(contenuto, "@pariva@"      , pariva      );
        contenuto = StringUtils.replace(contenuto, "@cfisc@"       , cfisc       );
        contenuto = StringUtils.replace(contenuto, "@indiri@"      , indiri      );
        contenuto = StringUtils.replace(contenuto, "@cap@"         , cap         );
        contenuto = StringUtils.replace(contenuto, "@comune@"      , comune      );
        contenuto = StringUtils.replace(contenuto, "@cdprov_m@"    , cdprov_m    );
        contenuto = StringUtils.replace(contenuto, "@dsnazi@"      , dsnazi      );
        contenuto = StringUtils.replace(contenuto, "@email@"       , email       );
        contenuto = StringUtils.replace(contenuto, "@telefono@"    , telefono    );
        contenuto = StringUtils.replace(contenuto, "@fax@"         , fax         );
        contenuto = StringUtils.replace(contenuto, "@cellulare@"   , cellulare   );
        contenuto = StringUtils.replace(contenuto, "@sitointernet@", sitointernet);
        contenuto = StringUtils.replace(contenuto, "@dtinse@"      , ls_dtinse   );




        String mittente  = "";  // imposto il mittente di default
        String oggetto   = "Servizio utenti";

        boolean lb_ = srv_sendmail.invia( mittente    
                                        , dest_TO     
                                        , null     
                                        , null
                                        , oggetto      
                                        , contenuto   
                                        , attach      
                                        , attach_name 
                                                          );


        if (lb_){
            

        }




    }




    public void mail_troppi_clienti(String cdutente, Vector vec_tkclie) throws Exception{
        
        int               ind     = 0;
        int               tot_rec = 0;
        PreparedStatement pstm    = null;
        ResultSet         rs      = null;

        String            sql = "";
        ResultSet         rs_box = null;




        Srv_sendmail srv_sendmail = new Srv_sendmail();
        Costanti_comm costanti_comm = new Costanti_comm();

        Utente utente = new Utente();
        Archclie archclie = new Archclie();



        atk_sql.setProfilo((Atk_sql) srv_sendmail);
        atk_sql.setProfilo((Atk_sql) costanti_comm);

        atk_sql.setProfilo((Atk_sql) utente);
        atk_sql.setProfilo((Atk_sql) archclie);



        String   path_modello = costanti_comm.getCostvalue ("reguser_mod_too_tkclie");
        String   url_site     = costanti_comm.getCostvalue ("url_e-progen");
        String   url_reguser  = costanti_comm.getCostvalue ("url_registrazione");
        String[] cdutente_mail_to = new String[3];
        cdutente_mail_to[0] = costanti_comm.getCostvalue("reguser_mail_to_1");
        cdutente_mail_to[1] = costanti_comm.getCostvalue("reguser_mail_to_2");
        cdutente_mail_to[2] = costanti_comm.getCostvalue("reguser_mail_to_3");
        String   url_img      = costanti_comm.getCostvalue("mail_url_path");

        url_reguser += "?cdutente="+cdutente;


        String username = "";
        String dsutente = "";
        String pwd      = "";
        String email    = "";
        String nome     = "";
        String cognome  = "";
        String pariva   = "";
        String dsclie   = "";



        rs = utente.getKey(cdutente);

        if (rs!=null && rs.next()){

            if (rs.getObject("username"  )!=null ) username   = rs.getString("username");
            if (rs.getObject("pwd"       )!=null ) pwd        = rs.getString("pwd");
            if (rs.getObject("dsutente"  )!=null ) dsutente   = rs.getString("dsutente");
            if (rs.getObject("email"     )!=null ) email      = rs.getString("email");
            if (rs.getObject("nome"      )!=null ) nome       = rs.getString("nome");
            if (rs.getObject("cognome"   )!=null ) cognome    = rs.getString("cognome");
            if (rs.getObject("pariva"    )!=null ) pariva     = rs.getString("pariva");

            pwd = "********";
        }

        String tkclie = "";
        String ragcog = "";
        String cdclie_m = "";

        String lista_clienti = "";

        lista_clienti += "<table>";
        lista_clienti += "<tr>";
        lista_clienti += "<th>Codice</th>";
        lista_clienti += "<th>Cliente</th>";
        lista_clienti += "<th>Ragione Sociale</th>";
        lista_clienti += "</tr>";


        for (int i=0; i<vec_tkclie.size(); i++){
            
            tkclie = (String)vec_tkclie.elementAt(i);

            rs = archclie.getCliente(tkclie);

            if (rs!=null && rs.next()){
                
                dsclie = "";
                
                cdclie_m = rs.getString("cdclie_m");
                ragcog   = rs.getString("ragcog");
                if (rs.getObject("dsclie")!=null ) dsclie = rs.getString("dsclie");

                lista_clienti += "<tr>";
                lista_clienti += "<td nowrap>&nbsp;"+cdclie_m+"&nbsp;</td>";
                lista_clienti += "<td nowrap>&nbsp;"+dsclie+"&nbsp;</td>";
                lista_clienti += "<td nowrap>&nbsp;"+ragcog+"&nbsp;</td>";
                lista_clienti += "</tr>";
            }

        }

        lista_clienti += "</table>";




        Vector attach = null;
        Vector attach_name = null;


        String  ls_email_to = "";
        Vector  dest_TO = new Vector();

        for (int i=0; cdutente_mail_to!=null && i<cdutente_mail_to.length; i++){
            
            ls_email_to = ""; 

            rs = utente.getKey(cdutente_mail_to[i]);

            if (rs!=null && rs.next() && rs.getObject("email")!=null){
                    
                ls_email_to = rs.getString("email");

                ls_email_to = ls_email_to.trim();
                ls_email_to = ls_email_to.toLowerCase();

                if (!ls_email_to.equals("")){
                    
                    dest_TO.add(ls_email_to);
                }
            }
            utente.close();

        }

        if (dest_TO.size() <=0){
            throw new Exception("Impostare gli utneti che gestiscono le registrazioni.");
        }

        String contenuto = srv_sendmail.getModel(path_modello);

        contenuto = StringUtils.replace(contenuto, "@username@"     , username);
        contenuto = StringUtils.replace(contenuto, "@dsutente@"     , dsutente);
        contenuto = StringUtils.replace(contenuto, "@nome@"         , nome);
        contenuto = StringUtils.replace(contenuto, "@cognome@"      , cognome);
        contenuto = StringUtils.replace(contenuto, "@pwd@"          , pwd     );
        contenuto = StringUtils.replace(contenuto, "@url_e-progen@" , url_site);
        contenuto = StringUtils.replace(contenuto, "@email@"        , email);
        contenuto = StringUtils.replace(contenuto, "@url_reguser@"  , url_reguser);
        contenuto = StringUtils.replace(contenuto, "@pariva@"       , pariva);
        contenuto = StringUtils.replace(contenuto, "@lista_clienti@", lista_clienti);
        contenuto = StringUtils.replace(contenuto, "../img"        , url_site+"/img");

        

        String mittente  = "";  // imposto il mittente di default
        String oggetto   = "Servizio utenti";

        boolean lb_ = srv_sendmail.invia( mittente    
                                        , dest_TO     
                                        , null     
                                        , null
                                        , oggetto      
                                        , contenuto   
                                        , attach      
                                        , attach_name 
                                                          );


        if (lb_){
            

        }




    }




    public void mail_allinea(String cdutente) throws Exception{
        
        int               ind     = 0;
        int               tot_rec = 0;
        PreparedStatement pstm    = null;
        ResultSet         rs      = null;

        String            sql = "";
        ResultSet         rs_box = null;




        Srv_sendmail srv_sendmail = new Srv_sendmail();
        Costanti_comm costanti_comm = new Costanti_comm();

        Utente utente = new Utente();



        atk_sql.setProfilo((Atk_sql) srv_sendmail);
        atk_sql.setProfilo((Atk_sql) costanti_comm);

        atk_sql.setProfilo((Atk_sql) utente);


        String   path_modello = costanti_comm.getCostvalue ("reguser_mod_allinea");
        String   url_site     = costanti_comm.getCostvalue ("url_e-progen");
        String   url_reguser  = costanti_comm.getCostvalue ("url_registrazione");
        String[] cdutente_mail_to = new String[3];
        cdutente_mail_to[0] = costanti_comm.getCostvalue("reguser_mail_to_1");
        cdutente_mail_to[1] = costanti_comm.getCostvalue("reguser_mail_to_2");
        cdutente_mail_to[2] = costanti_comm.getCostvalue("reguser_mail_to_3");
        String   url_img      = costanti_comm.getCostvalue("mail_url_path");

        url_reguser += "?cdutente="+cdutente;

        String username = "";
        String dsutente = "";
        String pwd      = "";
        String email    = "";
        String nome     = "";
        String cognome  = "";
        String pariva   = "";



        rs = utente.getKey(cdutente);

        if (rs!=null && rs.next()){

            if (rs.getObject("username"  )!=null ) username   = rs.getString("username");
            if (rs.getObject("pwd"       )!=null ) pwd        = rs.getString("pwd");
            if (rs.getObject("dsutente"  )!=null ) dsutente   = rs.getString("dsutente");
            if (rs.getObject("email"     )!=null ) email      = rs.getString("email");
            if (rs.getObject("nome"      )!=null ) nome       = rs.getString("nome");
            if (rs.getObject("cognome"   )!=null ) cognome    = rs.getString("cognome");
            if (rs.getObject("pariva"   )!=null ) pariva     = rs.getString("pariva");

            pwd = "********";
        }



        Vector attach = null;
        Vector attach_name = null;


        String  ls_email_to = "";
        Vector  dest_TO = new Vector();

        for (int i=0; cdutente_mail_to!=null && i<cdutente_mail_to.length; i++){
            
            ls_email_to = ""; 

            rs = utente.getKey(cdutente_mail_to[i]);

            if (rs!=null && rs.next() && rs.getObject("email")!=null){
                    
                ls_email_to = rs.getString("email");

                ls_email_to = ls_email_to.trim();
                ls_email_to = ls_email_to.toLowerCase();

                if (!ls_email_to.equals("")){
                    
                    dest_TO.add(ls_email_to);
                }
            }
            utente.close();

        }

        if (dest_TO.size() <=0){
            throw new Exception("Impostare gli utneti che gestiscono le registrazioni.");
        }

        String contenuto = srv_sendmail.getModel(path_modello);

        contenuto = StringUtils.replace(contenuto, "@username@"    , username);
        contenuto = StringUtils.replace(contenuto, "@dsutente@"    , dsutente);
        contenuto = StringUtils.replace(contenuto, "@nome@"        , nome);
        contenuto = StringUtils.replace(contenuto, "@cognome@"     , cognome);
        contenuto = StringUtils.replace(contenuto, "@pwd@"         , pwd     );
        contenuto = StringUtils.replace(contenuto, "@url_e-progen@", url_site);
        contenuto = StringUtils.replace(contenuto, "@email@"       , email);
        contenuto = StringUtils.replace(contenuto, "@url_reguser@" , url_reguser);
        contenuto = StringUtils.replace(contenuto, "@pariva@"      , pariva);
        contenuto = StringUtils.replace(contenuto, "../img"        , url_site+"/img");
        


        String mittente  = "";  // imposto il mittente di default
        String oggetto   = "Servizio utenti";

        boolean lb_ = srv_sendmail.invia( mittente    
                                        , dest_TO     
                                        , null     
                                        , null
                                        , oggetto      
                                        , contenuto   
                                        , attach      
                                        , attach_name 
                                                          );


        if (lb_){
            

        }




    }






    public static void main(String[] args){

        if (args==null || args.length != 1){
            System.out.println("");
            System.out.println("");
            System.out.println("Argomenti validi: start|stop");
            System.out.println("");
            System.out.println("");

            System.gc();
            System.exit(0);
        }
        args[0] = args[0].toLowerCase();

        Srv_reguser srv_reguser = new Srv_reguser(args);

        System.gc();
        System.exit(0);
    }



    // *** Properties


    public static final String CLIENTE   = "Cliente";
    public static final String FORNITORE = "Fornitore";
    public static final String AGENTE    = "Agente";


    public static       Timestamp  oggi       = new Timestamp(System.currentTimeMillis());



}
