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
import com.ateikon.common.Archenti;
import com.ateikon.util.StringUtils;
import com.ateikon.util.Atk_ctrl;


public class Srv_mailing_batch extends Service {


    public Srv_mailing_batch (String[] args){

        super("srv_mailing_batch", args);


        

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
        PreparedStatement pstm_box    = null;
        ResultSet         rs_box = null;

        Atk_ctrl atk_ctrl = new Atk_ctrl();
        Srv_sendmail srv_sendmail = new Srv_sendmail();
        Costanti_comm costanti_comm = new Costanti_comm();

        atk_sql.setProfilo((Atk_sql) srv_sendmail);
        atk_sql.setProfilo((Atk_sql) costanti_comm);


        String mittente     = "";
        Vector dest_TO      = new Vector();
        Vector dest_BCC     = new Vector();
        String oggetto      = "";
        String contenuto    = "";
        Vector attach       = null;
        Vector attach_name  = null;
                     

        dest_BCC.add("atkim@ateikon.com");


        String path_modello = costanti_comm.getCostvalue("srv_mailing_batch_mod");
        String srv_mailing_batch_test = costanti_comm.getCostvalue("srv_mailing_batch_test");

        
        String   url_site       = costanti_comm.getCostvalue ("url_e-progen");
        String   url_img        = costanti_comm.getCostvalue("mail_url_path");
        String   mail_http_res  = costanti_comm.getCostvalue ("mail_http_res");

        
        oggetto = costanti_comm.getCostvalue("srv_mailing_batch_oggetto");

        String contenuto_modello = srv_sendmail.getModel(path_modello);

        contenuto_modello = StringUtils.replace(contenuto_modello, "\"img", "\""+mail_http_res+"/img");
        contenuto_modello = StringUtils.replace(contenuto_modello, "\"js/", "\""+mail_http_res+"/js/");
        contenuto_modello = StringUtils.replace(contenuto_modello, "../img", mail_http_res+"/img");



        String     cdutente  = "";
        String     tkclie    = "";
        String     email     = "";
        String     dsutente  = "";
        String     cognome   = "";
        String     nome      = "";
        String     username  = "";
        String     pwd       = "";
        String     ragcog    = "";
        Timestamp  dtfval    = null;


        String l_query = "";

        l_query  = "";
        l_query += " select dtpwd                   \n";
        l_query += "      , dsutente                \n";
        l_query += "      , nome                    \n";
        l_query += "      , cognome                 \n";
        l_query += "      , cdutente                \n";
        l_query += "      , email                   \n";
        l_query += "      , tkclie                  \n";
        l_query += "      , cdagen                  \n";
        l_query += "      , pwd                     \n";
        l_query += "      , username                \n";
        l_query += "   from web.utente              \n";
        l_query += "  where fgmailbatch = 'S'       \n";
        l_query += "  order by tkclie               \n";

        rs = atk_sql.sql_query(l_query);

        while (rs!=null && rs.next()){
            
            cdutente  = "";
            tkclie    = "";
            email     = "";
            dsutente  = "";
            cognome   = "";
            nome      = "";
            username  = "";
            pwd       = "";
            dest_TO   = new Vector();
            ragcog    = "";
            dtfval    = null;

            if (rs.getObject("cdutente"  )!=null) cdutente   = rs.getString("cdutente");
            if (rs.getObject("tkclie"  )!=null) tkclie   = rs.getString("tkclie");
            if (rs.getObject("email"   )!=null) email    = rs.getString("email");
            if (rs.getObject("dsutente")!=null) dsutente = rs.getString("dsutente");
            if (rs.getObject("cognome")!=null) cognome = rs.getString("cognome");
            if (rs.getObject("nome")!=null) nome = rs.getString("nome");
            if (rs.getObject("pwd"     )!=null) pwd      = rs.getString("pwd");
            if (rs.getObject("username")!=null) username = rs.getString("username");

            

            pwd = atk_ctrl.decode (pwd);


            if (email.equals("")){
                
                
                System.out.println(cdutente+" -- Email non Valorizzata ");
                continue;
            }

            l_query  = "";
            l_query += " select a.ragcog              \n";
            l_query += "      , a.dtfval              \n";
            l_query += "   from pgmr.archenti a       \n";
            l_query += "      , pgmr.archclie b       \n";
            l_query += "  where b.cdente = a.cdente   \n";
            l_query += "    and tkclie = '"+tkclie+"' \n";

            rs_box = atk_sql.sql_query(l_query);

            if (rs_box != null && rs_box.next()){
                
                if (rs_box.getObject("ragcog")!=null)  ragcog = rs_box.getString("ragcog");
                if (rs_box.getObject("dtfval")!=null)  dtfval = rs_box.getTimestamp("dtfval");

                if (dtfval!=null && dtfval.compareTo(oggi)<0){
                    System.out.println("Data fine rapporto "+dtfval);
                    continue;
                }
            }else {
                
                System.out.println(cdutente+" -- Cliente non trovato");
                continue;
            }


            if (dsutente.equals("")){
                
                dsutente = nome + " " +cognome;
            }

            contenuto = contenuto_modello;

            contenuto = StringUtils.replace(contenuto, "@dsutente@", dsutente);
            contenuto = StringUtils.replace(contenuto, "@username@", username);
            contenuto = StringUtils.replace(contenuto, "@pwd@", pwd);


            if (srv_mailing_batch_test.equals("N")){
                System.out.println(email);
                dest_TO.add(email);
            }else {
                
                System.out.println("Test "+email);
            }


            boolean lb_ = srv_sendmail.invia( mittente    
                                            , dest_TO     
                                            , null     
                                            , dest_BCC
                                            , oggetto      
                                            , contenuto   
                                            , attach      
                                            , attach_name 
                                                              );



            l_query  = "";
            l_query += " update web.utente                             \n";
            l_query += "    set fgmailbatch = 'I'                      \n";
            l_query += "      , dtmailbatch = "+atk_sql.sysdate+"      \n";
            l_query += "  where cdutente = '"+cdutente+"'              \n";

            tot_rec = atk_sql.sql_update(l_query);

            if (tot_rec == 1){
                
                atk_sql.m_connection.commit();
            }
            
            
            
            



        } // FINE while rs  




        return;


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

        Srv_mailing_batch obj = new Srv_mailing_batch(args);

        System.gc();
        System.exit(0);
    }



    // *** Properties



    public static       Timestamp  oggi       = new Timestamp(System.currentTimeMillis());



}
