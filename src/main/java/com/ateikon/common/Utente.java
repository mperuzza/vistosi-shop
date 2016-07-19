package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Vector;

import com.ateikon.function.*;
import com.ateikon.util.StringUtils;
import com.ateikon.service.Srv_sendmail;

public class Utente extends com.ateikon.standard.Utente {

    public Utente() {
        
        super();
    }




    public ResultSet getUser_name(String username, String cdazie, String cddipa) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select cdutente        \n";
		l_query  += "      , dsutente        \n";
		l_query  += "      , email           \n";
		l_query  += "      , nome            \n";
		l_query  += "      , cognome         \n";
		l_query  += "      , cdagen          \n";
		l_query  += "      , tkclie          \n";
		l_query  += "      , tkforn          \n";
		l_query  += "      , livello         \n";
		l_query  += "      , cdutente_gest   \n";
		l_query  += "      , fgweb           \n";
		l_query  += "      , cdnazi          \n";
                                             
		l_query  += "   from web.utente      \n";
		l_query  += "  where username  = ?   \n";
		l_query  += "    and cdazie    = ?   \n";
		l_query  += "    and cddipa    = ?   \n";

		pstm = setQuery( l_query ) ;

        ind = 1;

        pstm.setString(ind, username); ind += 1;
        pstm.setString(ind, cdazie  ); ind += 1;
        pstm.setString(ind, cddipa  ); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }






    public ResultSet getKey(String cdutente) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select *              \n";
		l_query  += "   from web.utente     \n";
		l_query  += "  where cdutente  = ?  \n";

		pstm = setQuery( l_query ) ;

        ind = 1;

        pstm.setString(ind, cdutente); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }


    public int countTkclie(String tkclie) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select count(1)       \n";
		l_query  += "   from web.utente     \n";
		l_query  += "  where tkclie  = ?    \n";

        pstm = m_connection.prepareStatement( l_query );

        ind = 1;

        pstm.setString(ind, tkclie); ind += 1;

        rs = pstm.executeQuery();

        if (rs !=null && rs.next() && rs.getObject(1)!=null){
            
            tot_rec = rs.getInt(1);
        }
        pstm.close();
        pstm = null;

        return tot_rec;


    }



    public ResultSet getDtricpwd(String fgricpwd) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select *                         \n";
		l_query  += "   from web.utente       \n";
        if (fgricpwd.equals("S")){
		    l_query  += "  where dtricpwd is not null  \n";
        }else {
		    l_query  += "  where dtricpwd is null      \n";
        }
		l_query  += "   order by dtricpwd              \n";
        

		pstm = setQuery( l_query ) ;

        ind = 1;


        rs = pstm.executeQuery();

        return rs;


    }









    public int ins_scheda( String cdutente 
                         , String nome     
                         , String cognome  
                         , String dsutente 
                         , String username 
                         , String pwd      
                         , String cdgput   
                         , String idutente 
                         , String cdagen   
                         , String tkclie   
                         , String tkforn   
                         , int    livello
                         , String ragsoc
                         , String email
                         , String cdutente_gest
                         , String pariva
                                                    ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;


        F_tabkey f_tabkey = new F_tabkey();
        setProfilo((Atk_sql) f_tabkey );

        long  tkutente = f_tabkey.getTabkey( "utente" );

        if (tkutente<=0){
            throw new Exception ("Error Keypool: "+tkutente);
        }

        this.cdutente = ""+tkutente ;

        for (int i=this.cdutente.length(); i<10; i++){
            
            this.cdutente = "0"+this.cdutente;
        }

        cdutente = this.cdutente;



        String cdcapo = null;


        l_query   = "";
		l_query  += " insert into web.utente(   \n";
		l_query  += "             cdutente      \n";
		l_query  += "           , dsutente      \n";
		l_query  += "           , pwd           \n";
		l_query  += "           , nome          \n";
		l_query  += "           , cognome       \n";
		l_query  += "           , ragsoc        \n";
		l_query  += "           , cdgput        \n";
		l_query  += "           , idutente      \n";
		l_query  += "           , cdagen        \n";
		l_query  += "           , cdcapo        \n";
		l_query  += "           , username      \n";
		l_query  += "           , tkclie        \n";
		l_query  += "           , tkforn        \n";
		l_query  += "           , email         \n";
		l_query  += "           , livello       \n";
		l_query  += "           , cdazie        \n";
		l_query  += "           , cddipa        \n";
		l_query  += "           , profil        \n";
		l_query  += "           , dtinse        \n";
		l_query  += "           , dtulag        \n";
		l_query  += "           , cdutente_gest \n";
		l_query  += "           , pariva        \n";
        
		l_query  += "           )values (       \n";
		l_query  += "             ?,?,?,?,?     \n";
		l_query  += "           , ?,?,?,?,?     \n";
		l_query  += "           , ?,?,?,?,?     \n";
		l_query  += "           , ?,?,?,?,?     \n";
		l_query  += "           , ?,?         \n";
		l_query  += "           )               \n";


		pstm = m_connection.prepareStatement( l_query ) ;


        if (nome.equals("")    )  nome     = null;
        if (cognome.equals("") )  cognome  = null;
        if (dsutente.equals(""))  dsutente = null;
        if (username.equals(""))  username = null;
        if (pwd.equals("")     )  pwd      = null;
        if (cdgput.equals("")  )  cdgput   = null;
        if (idutente.equals(""))  idutente = null;
        if (cdagen.equals("")  )  cdagen   = null;
        if (tkclie.equals("")  )  tkclie   = null;
        if (tkforn.equals("")  )  tkforn   = null;
        if (ragsoc.equals("")  )  ragsoc   = null;
        if (email.equals("")   )  email    = null;
        if (cdutente_gest.equals("") ) cdutente_gest = null;
        if (pariva.equals("") )   pariva = null;
        
        
        
        ind = 1;

        pstm.setString   (ind, cdutente ); ind += 1;
        pstm.setString   (ind, dsutente ); ind += 1;
        pstm.setString   (ind, pwd      ); ind += 1;
        pstm.setString   (ind, nome     ); ind += 1;
        pstm.setString   (ind, cognome  ); ind += 1;
        pstm.setString   (ind, ragsoc   ); ind += 1;
        pstm.setString   (ind, cdgput   ); ind += 1;
        pstm.setString   (ind, idutente ); ind += 1;
        pstm.setString   (ind, cdagen   ); ind += 1;
        pstm.setString   (ind, cdcapo   ); ind += 1;
        pstm.setString   (ind, username ); ind += 1;
        pstm.setString   (ind, tkclie   ); ind += 1;
        pstm.setString   (ind, tkforn   ); ind += 1;
        pstm.setString   (ind, email    ); ind += 1;
        pstm.setInt      (ind, livello  ); ind += 1;

        pstm.setString   (ind, cdazie  ); ind += 1;
        pstm.setString   (ind, cddipa  ); ind += 1;
        pstm.setString   (ind, profil  ); ind += 1;
        pstm.setTimestamp(ind, dtinse  ); ind += 1;
        pstm.setTimestamp(ind, dtulag  ); ind += 1;
        pstm.setString   (ind, cdutente_gest); ind += 1;
        pstm.setString   (ind, pariva  ); ind += 1;
        

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        return tot_rec;

    }



    public int upd_scheda( String cdutente 
                         , String nome     
                         , String cognome  
                         , String dsutente 
                         , String username 
                         , String pwd      
                         , String cdgput   
                         , String idutente 
                         , String cdagen   
                         , String tkclie   
                         , String tkforn   
                         , int    livello
                         , String ragsoc
                         , String email
                         , String cdutente_gest
                         , String pariva
                                                    ) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;


        String cdcapo = null;


        l_query   = "";
		l_query  += " update web.utente         \n";
		l_query  += "    set dsutente = ?       \n";
		l_query  += "      , pwd      = ?       \n";
		l_query  += "      , nome     = ?       \n";
		l_query  += "      , cognome  = ?       \n";
		l_query  += "      , ragsoc   = ?       \n";
		l_query  += "      , cdgput   = ?       \n";
		l_query  += "      , idutente = ?       \n";
		l_query  += "      , cdagen   = ?       \n";
		l_query  += "      , cdcapo   = ?       \n";
		l_query  += "      , username = ?       \n";
		l_query  += "      , tkclie   = ?       \n";
		l_query  += "      , tkforn   = ?       \n";
		l_query  += "      , email    = ?       \n";
		l_query  += "      , livello  = ?       \n";
		l_query  += "      , profil   = ?       \n";
		l_query  += "      , dtulag   = ?       \n";
		l_query  += "      , cdutente_gest = ?  \n";
		l_query  += "      , pariva   = ?       \n";
		l_query  += "  where cdutente = ?       \n";


		pstm = m_connection.prepareStatement( l_query ) ;


        if (nome.equals("")          )  nome     = null;
        if (cognome.equals("")       )  cognome  = null;
        if (dsutente.equals("")      )  dsutente = null;
        if (username.equals("")      )  username = null;
        if (pwd.equals("")           )  pwd      = null;
        if (cdgput.equals("")        )  cdgput   = null;
        if (idutente.equals("")      )  idutente = null;
        if (cdagen.equals("")        )  cdagen   = null;
        if (tkclie.equals("")        )  tkclie   = null;
        if (tkforn.equals("")        )  tkforn   = null;
        if (ragsoc.equals("")        )  ragsoc   = null;
        if (email.equals("")         )  email    = null;
        if (cdutente_gest.equals("") )  cdutente_gest = null;
        if (pariva.equals("")        )  pariva   = null;
        

        
        ind = 1;

        pstm.setString   (ind, dsutente      ); ind += 1;
        pstm.setString   (ind, pwd           ); ind += 1;
        pstm.setString   (ind, nome          ); ind += 1;
        pstm.setString   (ind, cognome       ); ind += 1;
        pstm.setString   (ind, ragsoc        ); ind += 1;
        pstm.setString   (ind, cdgput        ); ind += 1;
        pstm.setString   (ind, idutente      ); ind += 1;
        pstm.setString   (ind, cdagen        ); ind += 1;
        pstm.setString   (ind, cdcapo        ); ind += 1;
        pstm.setString   (ind, username      ); ind += 1;
        pstm.setString   (ind, tkclie        ); ind += 1;
        pstm.setString   (ind, tkforn        ); ind += 1;
        pstm.setString   (ind, email         ); ind += 1;
        pstm.setInt      (ind, livello       ); ind += 1;
                                             
        pstm.setString   (ind, profil        ); ind += 1;
        pstm.setTimestamp(ind, dtulag        ); ind += 1;
        pstm.setString   (ind, cdutente_gest ); ind += 1;
        pstm.setString   (ind, pariva        ); ind += 1;
        
        pstm.setString   (ind, cdutente      ); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        return tot_rec;


    }



    public int executeDelete( String cdutente 
                                                ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;




        l_query   = "";
		l_query  += " delete from web.utente  \n";
		l_query  += "  where cdutente = ?     \n";

		pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setString   (ind, cdutente ); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        return tot_rec;


    }






    public int setDtwebsync( String cdutente    ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;




        l_query   = "";
		l_query  += " update web.utente    \n";
		l_query  += "    set dtwebsync = ? \n";
		l_query  += "  where cdutente  = ? \n";

		pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setTimestamp(ind, oggi     ); ind += 1;
        pstm.setString   (ind, cdutente ); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        return tot_rec;


    }


    public int setDtpwd( String cdutente, Timestamp dtpwd    ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;



        l_query   = "";
		l_query  += " update web.utente        \n";
		l_query  += "    set dtpwd = ?         \n";
		l_query  += "  where cdutente  = ?     \n";

		pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setTimestamp(ind, dtpwd     ); ind += 1;
        pstm.setString   (ind, cdutente ); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        return tot_rec;


    }


    public int setDtricpwd( String cdutente, Timestamp dtricpwd    ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;




        l_query   = "";
		l_query  += " update web.utente    \n";
		l_query  += "    set dtricpwd = ?     \n";
		l_query  += "  where cdutente  = ? \n";

		pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setTimestamp(ind, dtricpwd ); ind += 1;
        pstm.setString   (ind, cdutente ); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        return tot_rec;


    }

    public int setEmail( String cdutente, String email) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        if (email.equals("")) email = null;



        l_query   = "";
		l_query  += " update web.utente    \n";
		l_query  += "    set email = ?     \n";
		l_query  += "  where cdutente  = ? \n";

		pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setString   (ind, email     ); ind += 1;
        pstm.setString   (ind, cdutente ); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        return tot_rec;


    }


    public int setUsername( String cdutente, String username) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        if (username.equals("")) username = null;



        l_query   = "";
		l_query  += " update web.utente    \n";
		l_query  += "    set username = ?     \n";
		l_query  += "  where cdutente  = ? \n";

		pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setString   (ind, username     ); ind += 1;
        pstm.setString   (ind, cdutente ); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        return tot_rec;


    }





    public int setTkclie( String cdutente, String tkclie) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        if (tkclie.equals("")) tkclie = null;



        l_query   = "";
		l_query  += " update web.utente    \n";
		l_query  += "    set tkclie = ?     \n";
		l_query  += "  where cdutente  = ? \n";

		pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setString   (ind, tkclie   ); ind += 1;
        pstm.setString   (ind, cdutente ); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        return tot_rec;


    }



    public int setFgfmailpwd( String cdutente, String fgfmailpwd) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        if (fgfmailpwd.equals("")) fgfmailpwd = null;



        l_query   = "";
		l_query  += " update web.utente    \n";
		l_query  += "    set fgfmailpwd = ?     \n";
		l_query  += "  where cdutente  = ? \n";

		pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setString   (ind, fgfmailpwd   ); ind += 1;
        pstm.setString   (ind, cdutente ); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        return tot_rec;


    }


    public int blocca( String cdutente ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        String fgmail_to_user = "N";
        String pwd = "";

        return setPwd( cdutente, pwd, fgmail_to_user);

    }

    public int sblocca( String cdutente ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        String fgmail_to_user = "N";
        String pwd = StringUtils.randomString(8).toLowerCase();

        return setPwd( cdutente, pwd, fgmail_to_user);

    }





    public int setPwd( String cdutente, String pwd, String fgmail_to_user) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;



        String      pwd2 = "";
        Timestamp   dtpwd2 = null;
        String      pwd3 = "";
        Timestamp   dtpwd3 = null;
        String      email = "";


        l_query   = "";
		l_query  += " select pwd                   \n";
		l_query  += "      , dtpwd                 \n";
		l_query  += "      , pwd2                  \n";
		l_query  += "      , dtpwd2                \n";
		l_query  += "      , pwd3                  \n";
		l_query  += "      , dtpwd3                \n";
		l_query  += "      , email                 \n";
		l_query  += "   from web.utente   \n";
		l_query  += "  where cdutente  = ?         \n";

		pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setString   (ind, cdutente ); ind += 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){
            
            if (rs.getObject("pwd")!=null)  pwd2 = rs.getString("pwd");
            if (rs.getObject("pwd2")!=null) pwd3 = rs.getString("pwd2");
            if (rs.getObject("email")!=null) email = rs.getString("email");

            if (rs.getObject("dtpwd")!=null)  dtpwd2 = rs.getTimestamp("dtpwd");
            if (rs.getObject("dtpwd2")!=null) dtpwd3 = rs.getTimestamp("dtpwd2");
        }


        if (pwd.equals(pwd2)){
            
            // se la pwd è == alla precedente 
            return -1;
        }


        if (pwd.equals("")) pwd = null;
        if (pwd2.equals("")) pwd2 = null;
        if (pwd3.equals("")) pwd3 = null;


        pstm.close();
        pstm = null;


        l_query   = "";
		l_query  += " update web.utente    \n";
		l_query  += "    set pwd   = ?              \n";
		l_query  += "      , pwd2  = ?              \n";
		l_query  += "      , pwd3  = ?              \n";
		l_query  += "      , dtpwd   = null         \n";
		l_query  += "      , dtpwd2  = ?            \n";
		l_query  += "      , dtpwd3  = ?            \n";
		l_query  += "      , profil  = ?            \n";
		l_query  += "      , dtulag  = ?            \n";
		l_query  += "  where cdutente  = ?          \n";

		pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setString   (ind, pwd      ); ind += 1;
        pstm.setString   (ind, pwd2     ); ind += 1;
        pstm.setString   (ind, pwd3     ); ind += 1;
        pstm.setTimestamp(ind, dtpwd2   ); ind += 1;
        pstm.setTimestamp(ind, dtpwd3   ); ind += 1;
        pstm.setString   (ind, profil   ); ind += 1;
        pstm.setTimestamp(ind, oggi     ); ind += 1;

        pstm.setString   (ind, cdutente ); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;


        if (tot_rec > 0){
            m_connection.commit();
        }else {
            return tot_rec;
        }

        if (email.equals("")) fgmail_to_user = "N";

        if (fgmail_to_user.equals("S")){
            
            // invio la mail all'utente e setto fa data invio mail

            boolean lb_ = mail_chgPwd(cdutente);

            if (lb_){
                
                tot_rec = this.setDtpwd(cdutente, oggi);

                m_connection.commit();

            }


        }


        return 1;


    }












    public boolean mail_chgPwd(String cdutente) throws Exception{

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;
    
        String            sql = "";
        ResultSet         rs_box = null;




        Srv_sendmail srv_sendmail = new Srv_sendmail();
        Costanti_comm costanti_comm = new Costanti_comm();


        setProfilo((Atk_sql) srv_sendmail);
        setProfilo((Atk_sql) costanti_comm);


        String   path_modello = costanti_comm.getCostvalue ("modello_mail_chgpwd");
        String   url_site     = costanti_comm.getCostvalue ("url_e-progen");
        String   url_img      = costanti_comm.getCostvalue("mail_url_path");


        String username = "";
        String dsutente = "";
        String pwd      = "";
        String email    = "";
        String nome     = "";
        String cognome  = "";
        String pariva   = "";



        rs = this.getKey(cdutente);

        if (rs!=null && rs.next()){

            if (rs.getObject("username"  )!=null ) username   = rs.getString("username");
            if (rs.getObject("pwd"       )!=null ) pwd        = rs.getString("pwd");
            if (rs.getObject("dsutente"  )!=null ) dsutente   = rs.getString("dsutente");
            if (rs.getObject("email"     )!=null ) email      = rs.getString("email");
        }

        if (email.equals("")) {
            return false;
        }



        Vector attach = null;
        Vector attach_name = null;


        Vector  dest_TO = new Vector();

        dest_TO.add(email);


        String contenuto = srv_sendmail.getModel(path_modello);

        contenuto = StringUtils.replace(contenuto, "@nome@"     , nome);
        contenuto = StringUtils.replace(contenuto, "@cognome@"  , cognome);
        contenuto = StringUtils.replace(contenuto, "@username@" , username);
        contenuto = StringUtils.replace(contenuto, "@pwd@"      , pwd     );
        contenuto = StringUtils.replace(contenuto, "../img"    , url_site+"/img");
        


        String mittente  = "";  // imposto il mittente di default
        String oggetto   = "Login Utente";

        boolean lb_ = srv_sendmail.invia( mittente    
                                        , dest_TO     
                                        , null     
                                        , null
                                        , oggetto      
                                        , contenuto   
                                        , attach      
                                        , attach_name 
                                                          );


        return lb_;



    }




    public int updProfilo( String cdutente
                         , String nome
                         , String cognome
                         , String email
                                            ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        String dsutente = nome + " " + cognome;
        
        dsutente = dsutente.trim();


        l_query   = "";
		l_query  += " update web.utente    \n";
		l_query  += "    set nome    = ?     \n";
		l_query  += "      , cognome = ?     \n";
		l_query  += "      , email   = ?     \n";
        if (!dsutente.equals("")){
		l_query  += "      , dsutente   = ?     \n";
        }
		l_query  += "      , profil  = ?     \n";
		l_query  += "      , dtulag  = ?     \n";
		l_query  += "  where cdutente  = ? \n";


        if (nome.equals("")) nome    = null;
        if (nome.equals("")) cognome = null;
        if (nome.equals("")) email   = null;

		pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setString   (ind, nome     ); ind += 1;
        pstm.setString   (ind, cognome  ); ind += 1;
        pstm.setString   (ind, email    ); ind += 1;
        if (!dsutente.equals("")){
        pstm.setString   (ind, dsutente ); ind += 1;
        }
        pstm.setString   (ind, profil   ); ind += 1;
        pstm.setTimestamp(ind, oggi     ); ind += 1;

        pstm.setString   (ind, cdutente ); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        return tot_rec;


    }







    /***

        Registrazione Utenti



    public int registrazione(com.ateikon.structure.Rec_utente astr) throws Exception{
        
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        // imposto i default


        Costanti_comm costanti_comm = new Costanti_comm();
        
        setProfilo((Atk_sql) costanti_comm);

        String cat_publico = costanti_comm.getCostvalue("ep.cat_publico");
        String cliente     = costanti_comm.getCostvalue("ep.cliente");
        String reguser_nosend_pwd = costanti_comm.getCostvalue("reguser_nosend_pwd");



        super.cdutente = "";
        
        if (astr.email.equals("")) return 0;
        if (astr.pariva.equals("")) return 0;

        astr.fgweb = "N";
        astr.dsutente = astr.nome + " " + astr.cognome;
        if (astr.dsutente.length()>=80) astr.dsutente = astr.dsutente.substring(0,80);

        astr.cdgput = "N";
        astr.pwd = "";
        astr.username = "";
        astr.livello = 0;
        astr.dtricpwd = of_getTimestamp();





        if (reguser_nosend_pwd.equals("S")){
            
            // se reguser_nosend_pwd == S tratto tutte 
            // le registrazioni come nuove: il cliente 
            // non vuole che vengano inviati gli estremi di login 
            // all'utente del portale dopo la registrazione

        }else {
            

                // --- Verifico se Questo Utente si è Registrato
        
                if (cat_publico.equals("S")){
                    
                    l_query   = "";
            		l_query  += " select cdutente    \n";
            		l_query  += "      , dtpwd       \n";
            		l_query  += "   from web.utente  \n";
            		l_query  += "  where email  = ?  \n";
            
            		pstm = m_connection.prepareStatement( l_query ) ;
            
                    ind = 1;
                    pstm.setString   (ind, astr.email ); ind += 1;
        
                }else {
                    
                    l_query   = "";
            		l_query  += " select cdutente    \n";
            		l_query  += "      , dtpwd       \n";
            		l_query  += "   from web.utente  \n";
            		l_query  += "  where pariva = ?  \n";
            		l_query  += "    and email  = ?  \n";
            
            		pstm = m_connection.prepareStatement( l_query ) ;
            
                    ind = 1;
                    pstm.setString   (ind, astr.pariva); ind += 1;
                    pstm.setString   (ind, astr.email ); ind += 1;
                }
        
        
                rs = pstm.executeQuery();
        
                if (rs!=null && rs.next()){
        
                    Timestamp ldt_dtpwd = null;
        
                    astr.cdutente = rs.getString("cdutente");
        
                    super.cdutente = astr.cdutente;
        
                    if (rs.getObject("dtpwd")!= null) ldt_dtpwd = rs.getTimestamp("dtpwd");
        
                    pstm.close();
                    pstm = null;
        
                    boolean lb_send_mail = false;
        
                    if (ldt_dtpwd != null){
                     
                        lb_send_mail = true;
        
                    }else {
                        
                        if (cat_publico.equals("S")){
                                
                            lb_send_mail = true;    
        
                        }else {
                            
                            setDtricpwd(astr.cdutente, of_getTimestamp());
                        }
                    }
        
                    if (lb_send_mail){
                        // inoltro la mail
                        
                        boolean lb_ = mail_chgPwd(astr.cdutente);
            
                        if (lb_){
                            
                            tot_rec = setDtpwd(cdutente, of_getTimestamp());
            
                            m_connection.commit();
        
                        }else {
                            
                            return -10;
                        }
                    }
        
                    return 1;
                }
        
                    
                pstm.close();
                pstm = null;

        }   // if (!reguser_nosend_pwd.equals("S")){



        // --- prendo l'ultimo ente Inserito con questa P.Iva


        String ls_cdente = "";

        l_query   = "";
		l_query  += " select cdente                           \n";
		l_query  += "   from pgmr.archenti                    \n";
		l_query  += "  where pariva = ?                       \n";
		l_query  += "    and (dtfval is null or dtfval >= ?)  \n";
		l_query  += "   order by cdente desc                  \n";
        
        

		pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setString   (ind, astr.pariva); ind += 1;
        pstm.setTimestamp(ind, of_getTimestamp()); ind += 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){
            
            ls_cdente = rs.getString("cdente");
        }
        pstm.close();
        pstm = null;

        if (!ls_cdente.equals("")){
            
            astr.cdagen = "";
            astr.tkclie = "";
            astr.tkforn = "";


            // utente è del Gestionale 

            l_query   = "";
		    l_query  += "  select cdagen                   \n";
		    l_query  += "    from pgmr.archagen            \n";
		    l_query  += "   where cdente = '"+ls_cdente+"' \n";
		    l_query  += "   order by cdagen desc           \n";
            
            astr.cdagen = sql_String(l_query);

            if (!astr.cdagen.equals("") ){
            
                // ho individuato un agente
                
                astr.cdutente = "";
                astr.pwd      = StringUtils.randomString(8).toLowerCase();
                astr.dtricpwd = of_getTimestamp();
                astr.fgweb    = "N";
                
                tot_rec = execute(astr);
                
                return tot_rec;

            }else {


                l_query   = "";
                l_query  += " select tkclie                   \n";
                l_query  += "   from pgmr.archclie            \n";
                l_query  += "  where cdente = '"+ls_cdente+"' \n";
                l_query  += "  order by tkclie desc           \n";
                
                astr.tkclie = sql_String(l_query);
                
                astr.cdutente = "";
                astr.pwd      = StringUtils.randomString(8).toLowerCase();
                astr.dtricpwd = of_getTimestamp();
                astr.fgweb    = "N";
                
                tot_rec = execute(astr);
                
                return tot_rec;

           
            }
           

        }   // FINE if (!ls_cdente.equals("")){
        

        astr.cdutente = "";
        astr.pwd      = StringUtils.randomString(8).toLowerCase();
        astr.fgweb    = "S";



        if (reguser_nosend_pwd.equals("S")){
            
        
            astr.dtricpwd = of_getTimestamp();
            astr.username = getUtente_pubblico();
            astr.tkclie   = costanti_comm.getCostvalue("ep.tkclie_publico");

        }else {
            
            if(cat_publico.equals("S")){
                
                // calcolo lo username 
    
                astr.username =  getUtente_pubblico();
                astr.tkclie   =  costanti_comm.getCostvalue("ep.tkclie_publico");
    
            }else {
                
                astr.dtricpwd = of_getTimestamp();
            }
        }




        tot_rec = execute(astr);

        if (tot_rec == 1){
            
            m_connection.commit();


            com.ateikon.service.Srv_reguser srv_reg_user = new com.ateikon.service.Srv_reguser((Atk_sql) this);
            
            if (reguser_nosend_pwd.equals("S")){
                
                srv_reg_user.mail_new_user(this.cdutente);
            }else {
                
                if (!astr.username.equals("")){
                    
                    srv_reg_user.mail_benvenuto(this.cdutente);

                }else {
                    srv_reg_user.mail_new_user(this.cdutente);
                }
            }

        }

        return tot_rec;

    }   // FINE Registrazione


    */





    public int     count( String f_search
                        , String username
                        , String cdutente
                        , Timestamp f_dtinse_da
                        , Timestamp f_dtinse_a
                        , String f_fgtipo
                        , String f_fgblocco
                        , String f_fgcarr

                                                        ) throws Exception {
    
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        f_search = par_like(f_search);
        cdutente = par_like(cdutente);

        int li_ = 0;


        l_query   = "";
		l_query  += " select count(1)        \n";
		l_query  += "   from web.utente a    \n";
		l_query  += "  where 1=1             \n";
        if (!cdutente.equals("")){
		l_query  += "  and a.cdutente like '"+cdutente+"'  \n";
        }
        if (!username.equals("")){
		l_query  += "  and upper(a.username) = upper('"+username+"')  \n";
        }
        if (f_fgtipo.equals("C")){
		    l_query  += "  and a.tkclie is not null  \n";
		    
            Costanti_comm costanti_comm = new Costanti_comm();

            setProfilo((Atk_sql)costanti_comm);

            String tkclie_publico  = costanti_comm.getCostvalue("ep.tkclie_publico");
		    
            if (!tkclie_publico.equals("")){
		        l_query  += "  and a.tkclie <> '"+tkclie_publico+"'  \n";
            }
        }
        if (f_fgtipo.equals("A")){
		    l_query  += "  and a.cdagen is not null  \n";
        }
        if (f_fgtipo.equals("F")){
		    l_query  += "  and a.tkforn is not null  \n";
        }
        if (f_fgblocco.equals("S")){
		    l_query  += "  and a.dtpwd is null  \n";
        }else if (f_fgblocco.equals("N")){
		    l_query  += "  and a.dtpwd is not null  \n";
        }
        if (f_fgcarr.equals("S")){
		    l_query  += "  and ( select count(p.cdarti)             \n";
		    l_query  += "          from web.carrello_test t         \n";
		    l_query  += "             , web.carrello p              \n";
		    l_query  += "         where t.tkcarrello = p.tkcarrello \n";
		    l_query  += "           and t.cdutente = a.cdutente     \n";
		    l_query  += "        ) > 0  \n";
        }
        if (!f_search.equals("")){
		l_query  += "  and (  a.username like '"+f_search+"'  \n";
		l_query  += "      or a.dsutente like '"+f_search+"'  \n";
		l_query  += "      or a.nome     like '"+f_search+"'  \n";
		l_query  += "      or a.cognome  like '"+f_search+"'  \n";
		l_query  += "      or a.pariva   like '"+f_search+"'  \n";
		l_query  += "      or a.username like '"+f_search+"'  \n";
		l_query  += "       ) \n";
            
        }
        if (f_dtinse_da!= null){
		    l_query  += "  and a.dtinse >= ?  \n";
        }
        if (f_dtinse_a!= null){
		    l_query  += "  and a.dtinse <= ?  \n";
        }

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (f_dtinse_da!= null){
            pstm.setTimestamp(ind, f_dtinse_da); ind += 1;
        }
        if (f_dtinse_a!= null){
            pstm.setTimestamp(ind, f_dtinse_a); ind += 1;
        }

        rs = pstm.executeQuery();

        if (rs !=null && rs.next() && rs.getObject(1)!=null){
            
            li_ = rs.getInt(1);

        }
        pstm.close();


        return li_;


    }


    public ResultSet search( String f_search
                           , String username  
                           , String cdutente
                           , Timestamp f_dtinse_da
                           , Timestamp f_dtinse_a
                           , String f_fgtipo
                           , String f_fgblocco
                           , String f_fgcarr
                                                    ) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;


        
        f_search = par_like(f_search);
        cdutente = par_like(cdutente);



        l_query   = "";
		l_query  += " select a.cdutente      \n";
		l_query  += "      , a.dsutente      \n";
		l_query  += "      , a.email         \n";
		l_query  += "      , a.username      \n";
		l_query  += "      , a.pwd           \n";
		l_query  += "      , a.dtpwd         \n";
		l_query  += "      , a.tkclie        \n";
		l_query  += "      , a.cdagen        \n";
		l_query  += "      , a.tkforn        \n";
		l_query  += "      , ( select count(t.tkordi)                           \n";
		l_query  += "            from web.ord_test t                            \n";
		l_query  += "           where t.cdutente = a.cdutente                   \n";
		l_query  += "                                         ) as tot_ordi     \n";
		l_query  += "   from web.utente a    \n";
		l_query  += "  where 1=1             \n";
        if (!cdutente.equals("")){
		l_query  += "  and a.cdutente like '"+cdutente+"'  \n";
        }
        if (!username.equals("")){
		l_query  += "  and upper(a.username) = upper('"+username+"')  \n";
        }
        if (f_fgtipo.equals("C")){
		    l_query  += "  and a.tkclie is not null  \n";

		    Costanti_comm costanti_comm = new Costanti_comm();

            setProfilo((Atk_sql)costanti_comm);

            String tkclie_publico  = costanti_comm.getCostvalue("ep.tkclie_publico");
		    
            if (!tkclie_publico.equals("")){
		        l_query  += "  and a.tkclie <> '"+tkclie_publico+"'  \n";
            }
        }
        if (f_fgtipo.equals("A")){
		    l_query  += "  and a.cdagen is not null  \n";
        }
        if (f_fgtipo.equals("F")){
		    l_query  += "  and a.tkforn is not null  \n";
        }
        if (f_fgblocco.equals("S")){
		    l_query  += "  and a.dtpwd is null  \n";
        }else if (f_fgblocco.equals("N")){
		    l_query  += "  and a.dtpwd is not null  \n";
        }
        if (f_fgcarr.equals("S")){
		    l_query  += "  and ( select count(t.tkordi)         \n";
		    l_query  += "          from web.ord_test t          \n";
		    l_query  += "         where t.cdutente = a.cdutente \n";
		    l_query  += "        ) > 0  \n";
        }
        if (!f_search.equals("")){
		l_query  += "  and (  a.username like '"+f_search+"'  \n";
		l_query  += "      or a.dsutente like '"+f_search+"'  \n";
		l_query  += "      or a.nome     like '"+f_search+"'  \n";
		l_query  += "      or a.cognome  like '"+f_search+"'  \n";
		l_query  += "      or a.pariva   like '"+f_search+"'  \n";
		l_query  += "      or a.username like '"+f_search+"'  \n";
		l_query  += "       ) \n";
            
        }
        if (f_dtinse_da!= null){
		    l_query  += "  and a.dtinse >= ?  \n";
        }
        if (f_dtinse_a!= null){
		    l_query  += "  and a.dtinse <= ?  \n";
        }

		l_query  += "  order by a.dsutente  \n";


        pstm = setQuery_ric(l_query);


        ind = 1;
        if (f_dtinse_da!= null){
            pstm.setTimestamp(ind, f_dtinse_da); ind += 1;
        }
        if (f_dtinse_a!= null){
            pstm.setTimestamp(ind, f_dtinse_a); ind += 1;
        }

        rs = pstm.executeQuery();

        return rs;
    }








    public String getUtente_pubblico( ) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        l_query   = "";
        l_query  += " select nprogr                \n";
        l_query  += "   from pgmr.contator         \n";
        l_query  += "  where cdazie = '00'         \n";
        l_query  += "    and prname = 'WEBUTENTE'  \n";

        long nprogr = sql_long(l_query);

        nprogr += 1;

        l_query   = "";
        l_query  += " update pgmr.contator          \n";
        l_query  += "    set nprogr = "+nprogr+"    \n";
        l_query  += "  where cdazie = '00'          \n";
        l_query  += "    and prname = 'WEBUTENTE'   \n";

        tot_rec = sql_update(l_query);

        if (tot_rec != 1){
            
            m_connection.rollback();
            
            throw new Exception("Errore Update pgmr.contator [WEBUTENTE]");
        }

        m_connection.commit();

        String ls_user_name= ""+nprogr;

        int li_ = ls_user_name.length();

        for (int i=li_ ; i<4; i++){
        
            ls_user_name = "0"+ls_user_name;
        }

        ls_user_name = "pb"+ls_user_name;



        return ls_user_name;

    }




    public int updSede_legale( String cdutente
                             , String ragsoc
                             , String indiri
                             , String cap
                             , String comune
                             , String cdprov
                             , String cdnazi
                                            ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;

        PreparedStatement pstm = null;

        if (ragsoc.equals("")) ragsoc = null;
        if (indiri.equals("")) indiri = null;
        if (comune.equals("")) comune = null;
        if (cap.equals("")) cap = null;
        if (cdprov.equals("")) cdprov = null;
        if (cdnazi.equals("")) cdnazi = null;


        
        l_query   = "";
		l_query  += " update web.utente           \n";
		l_query  += "    set ragsoc    = ?        \n";
		l_query  += "      , indiri    = ?        \n";
        l_query  += "      , cap       = ?        \n";
        l_query  += "      , comune    = ?        \n";
        l_query  += "      , cdprov    = ?        \n";
        l_query  += "      , cdnazi    = ?        \n";
        l_query  += "      , profil    = ?        \n";
		l_query  += "      , dtulag    = ?        \n";
		l_query  += "  where cdutente  = ?        \n";

		pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setString   (ind, ragsoc ); ind += 1;
        pstm.setString   (ind, indiri ); ind += 1;
        pstm.setString   (ind, cap    ); ind += 1;
        pstm.setString   (ind, comune ); ind += 1;
        pstm.setString   (ind, cdprov ); ind += 1;
        pstm.setString   (ind, cdnazi ); ind += 1;    
        pstm.setString   (ind, profil ); ind += 1;
        pstm.setTimestamp(ind, oggi   ); ind += 1;

        pstm.setString   (ind, cdutente ); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        return tot_rec;


    }    
    


    public boolean isMail(String email) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        boolean lb_ = false;

        l_query   = "";
		l_query  += " select cdutente     \n";
		l_query  += "   from web.utente   \n";
		l_query  += "  where email = ?    \n";
        

		pstm = setQuery( l_query ) ;

        ind = 1;

        pstm.setString(ind, email); ind += 1;

        rs = pstm.executeQuery();

        if (rs !=null && rs.next()){
            
            lb_ = true;
        }
        pstm.close();

        return lb_;


    }




    public String getCdnazi(String cdutente ) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " select cdnazi from web.utente where cdutente = '"+cdutente+"' \n";

        String ls_ = sql_String(l_query);

        return ls_;

    }




    /***


    */

    public String ctrl_email (String cdutente) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        String email  = "";
        String tkclie = "";
        String cdagen = "";
        String tkforn = "";

        String cdente = "";

        tot_rec = 0;


        l_query  = "";
        l_query += " select email                      \n";
        l_query += "      , tkclie                     \n";
        l_query += "      , cdagen                     \n";
        l_query += "      , tkforn                     \n";
        l_query += "   from web.utente                 \n";
        l_query += "  where cdutente = '"+cdutente+"'  \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){
            
            tot_rec += 1;

            if (rs.getObject("email" )!=null)  email  = rs.getString("email");
            if (rs.getObject("tkclie")!=null)  tkclie = rs.getString("tkclie");
            if (rs.getObject("cdagen")!=null)  cdagen = rs.getString("cdagen");
            if (rs.getObject("tkforn")!=null)  tkforn = rs.getString("tkforn");


            pstm.close();
        }else {
            pstm.close();
            return "";
        }

        if (!email.equals("")){
        
            // è impostata nella tabella web.utente 

            return email;
        }


        email = getEmail_gestionale ( tkclie, cdagen, tkforn );

        if (!email.equals("")){
            
            // la imposto nella web.utente per non ripetere questo
            // controllo 
            
            l_query  = "";                              
            l_query += " update web.utente                 \n";
            l_query += "    set email = '"+email+"'        \n";
            l_query += "  where cdutente = '"+cdutente+"'  \n";

            tot_rec = sql_update(l_query);

            if (tot_rec!=1){
                throw new Exception("Errore UPD email web.utente");
            }
        }




        return email;

    }







    /***


    */

    public String getEmail_gestionale ( String tkclie, String cdagen, String tkforn) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        String email = "";
        String cdente = "";

        // trovo l'ente

        if (!tkclie.equals("")){
            
            Costanti_comm costanti_comm = new Costanti_comm();

            setProfilo((Atk_sql)costanti_comm);

            String tkclie_publico  = costanti_comm.getCostvalue("ep.tkclie_publico");

            if (!tkclie_publico.equals(tkclie)){
                
                l_query  = "";                              
                l_query += " select cdente                  \n";
                l_query += "   from pgmr.archclie           \n";
                l_query += "  where tkclie = '"+tkclie+"'   \n";
    
                cdente = sql_String(l_query);
            }
        }


        if (cdente.equals("") && !cdagen.equals("") ){
            
            l_query  = "";                              
            l_query += " select cdente                  \n";
            l_query += "   from pgmr.archagen           \n";
            l_query += "  where cdagen = '"+cdagen+"'   \n";

            cdente = sql_String(l_query);
        }


        if (cdente.equals("") && !tkforn.equals("") ){
            
            l_query  = "";                              
            l_query += " select cdente                  \n";
            l_query += "   from pgmr.archforn           \n";
            l_query += "  where tkforn = '"+tkforn+"'   \n";

            cdente = sql_String(l_query);

        }

        if (cdente.equals("")){
          
            // non posso verificare nella unità locale
            
            return "";
        }


        com.ateikon.util.Atk_ctrl atk_ctrl = new com.ateikon.util.Atk_ctrl();

        // *** verifico se è impostata la mail della 
        //     sede Legale 

        l_query  = "";                              
        l_query += " select ul.email                  \n";
        l_query += "   from pgmr.unitalocali ul       \n";
        l_query += "      , pgmr.enteuniloc eu        \n";
        l_query += "  where eu.cdunil = ul.cdunil     \n";
        l_query += "    and fseleg = 'S'              \n";
        l_query += "    and eu.cdente = '"+cdente+"'  \n";

        email = sql_String(l_query);

        // controllo la mail 

        email = atk_ctrl.ctrl_email(email);



        // --- Se la mail della sede Legale è Errrata premdo 
        //     prima buona

        if (email.equals("")){

            l_query  = "";                              
            l_query += " select ul.email                  \n";
            l_query += "   from pgmr.unitalocali ul       \n";
            l_query += "      , pgmr.enteuniloc eu        \n";
            l_query += "  where eu.cdunil = ul.cdunil     \n";
            l_query += "    and eu.cdente = '"+cdente+"'  \n";
    
            pstm = m_connection.prepareStatement(l_query);
    
            ind = 1;
    
            rs = pstm.executeQuery();
    
            while (rs!=null && rs.next() && email.equals("")){
                
                tot_rec += 1;
    
                if (rs.getObject("email" )!=null)  email  = rs.getString("email");
    
                email = atk_ctrl.ctrl_email(email);
    
            }
            pstm.close();

    
            
        }



        return email ;

    }



}
