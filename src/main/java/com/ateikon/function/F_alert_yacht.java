package com.ateikon.function;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.SQLException;

import com.ateikon.common.Atk_sql;
import com.ateikon.common.Costanti_comm;

import java.io.File;
import java.util.Vector;


import com.ateikon.common.Atk_alert_spool;
import com.ateikon.common.Atk_messaggio;


import com.ateikon.structure.Rec_atk_alert_yacht;
import com.ateikon.structure.Rec_atk_alert_spool;

import com.ateikon.util.StringUtils;


public class F_alert_yacht extends F_alert {
    

    public F_alert_yacht(){
        
        super();
    }



    /***
    *  run
    **/



    public void run (){

    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        try{


            connect();


            Rec_atk_alert_yacht lstr = null;


            // imposto la data di inizio ricerca

            l_query  = "";
            l_query += " select dtfiltro             \n";
            l_query += "   from pgmr.atk_alert       \n";
            l_query += "  where id = "+il_id_alert+" \n";

            Timestamp dtfiltro = atk_sql.sql_Timestamp(l_query);

            if (dtfiltro == null){
                dtfiltro = atk_sql.of_getTimestamp();
            }
             
            System.out.println("\t\tdtfiltro >>"+dtfiltro+"<<");

            l_query  = "";
            l_query += " select *                           \n";
            l_query += "   from pgmr.atk_alert_yacht        \n";
            l_query += "  where id_alert = "+il_id_alert+"  \n";

            
            System.out.println("\t\tlstr.id >>\n"+l_query+"\n<<");


            pstm = atk_sql.m_connection.prepareStatement(l_query);

            rs = pstm.executeQuery();

            while (rs != null && rs.next()){
                
                lstr = new Rec_atk_alert_yacht();

                lstr.setResultSet(rs);

                System.out.println("\t\tlstr.id >>"+lstr.id+"<<");


                // Eseguo la ricerca nuovi annunci e li loggo

                cerca_alert(lstr, dtfiltro);

                // Invio la mail con gli articoli trovati ai destinatari dell'alert

                sendMail( );

            }
            pstm.close();

            close_sched();

            // chiudo la Schedulazione


        }catch(SQLException sql_ex){
            
            if (atk_sql != null && atk_sql.m_connection != null){
                try { atk_sql.m_connection.rollback(); }catch(Exception ex_rollback) {}
            }

            debugger ("Errore SQL Data-base");
            sql_ex.printStackTrace();

            if (padre != null){
                padre.sendFatalError("Alert ID = "+il_id_alert+" \n\n"+sql_ex.toString());
            }

        }catch(Exception ex){

            if (atk_sql != null && atk_sql.m_connection != null){
                try { atk_sql.m_connection.rollback(); }catch(Exception ex_rollback) {}
            }

            debugger ("Exception");
            ex.printStackTrace();

            if (padre != null){
                padre.sendFatalError("Alert ID = "+il_id_alert+" \n\n"+ex.toString());
            }

        }finally{
            
            try {
                
                if (atk_sql != null && atk_sql.m_connection != null){
                    
                    atk_sql.m_connection.commit();
                    atk_sql.closeConnection();
                }

            }catch(Exception close_ex){
                
            }
        }


    }   // FINE run









    /***


    */

    public int cerca_alert (Rec_atk_alert_yacht astr, Timestamp dtfiltro ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


    
        String      cdarti = "";
        Timestamp   dtulag = null;
        int         tot_arti = 0;


        Atk_alert_spool atk_alert_spool = new Atk_alert_spool();

        atk_sql.setProfilo((Atk_sql) atk_alert_spool);

        Rec_atk_alert_spool lstr_spool = null;




        l_query  = "";
        l_query += " select cdarti             \n";
        l_query += "      , dtulag             \n";
        l_query += "   from pgmr.kg_articolo   \n";
        l_query  += "  where fgabil   = 'S'                \n";  //avendramin20080321
        l_query += "    and dtulag >= ?        \n";
        if (!astr.fg_tipo.equals("")){
            l_query += "  and fg_tipo = ?      \n";
        }
        if (astr.tkcantiere > 0 ){
            l_query += "  and tkcantiere = ?   \n";
        }
        if (astr.tkmodello > 0 ){
            l_query += "  and tkmodello = ?    \n";
        }
        if (astr.prezzo_da > 0 ){
            l_query += "  and przbaseven >= ?   \n";
        }
        if (astr.prezzo_a > 0 ){
            l_query += "  and przbaseven <= ?    \n";
        }
        if (astr.anno_costruzione_barca > 0 ){
            l_query += "  and anno_costruzione_barca = ? \n";
        }
        l_query += "  order by dtulag          \n";

        
        System.out.println("\t\tl_query >>\n"+l_query+"\n<<");
        
        System.out.println("\t\t astr.fg_tipo                >>"+astr.fg_tipo               +"<<");
        System.out.println("\t\t astr.tkcantiere             >>"+astr.tkcantiere            +"<<");
        System.out.println("\t\t astr.tkmodello              >>"+astr.tkmodello             +"<<");
        System.out.println("\t\t astr.prezzo_da              >>"+astr.prezzo_da             +"<<");
        System.out.println("\t\t astr.prezzo_a               >>"+astr.prezzo_a              +"<<");
        System.out.println("\t\t astr.anno_costruzione_barca >>"+astr.anno_costruzione_barca+"<<");



        pstm = atk_sql.m_connection.prepareStatement(l_query);

        ind = 1;

        pstm.setTimestamp(ind, dtfiltro); ind += 1;

        if (!astr.fg_tipo.equals("")){
            pstm.setString   (ind, astr.fg_tipo); ind += 1;
        }
        if (astr.tkcantiere > 0 ){
            pstm.setLong     (ind, astr.tkcantiere); ind += 1;
        }
        if (astr.tkmodello > 0 ){
            pstm.setLong     (ind, astr.tkmodello); ind += 1;
        }
        if (astr.prezzo_da > 0 ){
            pstm.setDouble   (ind, astr.prezzo_da); ind += 1;
        }
        if (astr.prezzo_a > 0 ){
            pstm.setDouble   (ind, astr.prezzo_a); ind += 1;
        }
        if (astr.anno_costruzione_barca > 0 ){
            pstm.setLong     (ind, astr.anno_costruzione_barca); ind += 1;
        }


        rs = pstm.executeQuery();

        while (rs != null && rs.next()){
            
            cdarti = "";
            dtulag = null;

            if (rs.getObject("cdarti")!=null)  cdarti = rs.getString("cdarti");
            if (rs.getObject("dtulag")!=null)  dtulag = rs.getTimestamp("dtulag");

            System.out.println("\t\t\t spool cdarti >>"+cdarti+"<< ("+dtulag+")");


            lstr_spool = new Rec_atk_alert_spool();

            lstr_spool.id             = 0; 
            lstr_spool.id_alert       = il_id_alert; 
            lstr_spool.id_alert_yacht = astr.id; 
            lstr_spool.cdarti         = cdarti;
            lstr_spool.tksend_alert   = 0; 

            tot_rec = atk_alert_spool.add_arti(lstr_spool);

            tot_arti += 1;
        }

        pstm.close();
            
        // Aggiorno la data Filtro
        // se non ho trovato Articoli la imposto ad oggi

        tot_rec = 0;
        
        l_query  = "";
        l_query += " update pgmr.atk_alert  \n";
        l_query += "    set dtfiltro = ?    \n";
        l_query += "      , profil   = ?    \n";
        l_query += "      , dtulag   = ?    \n";
        l_query += "  where id = ?          \n";
        
        pstm = atk_sql.m_connection.prepareStatement(l_query);

        ind = 1;
        pstm.setTimestamp(ind, atk_sql.of_getOggi()     ); ind += 1;
        pstm.setString   (ind, atk_sql.profil           ); ind += 1;
        pstm.setTimestamp(ind, atk_sql.of_getTimestamp()); ind += 1;
        pstm.setLong     (ind, il_id_alert              ); ind += 1;

        tot_rec = pstm.executeUpdate();
        
        pstm.close();
        

        atk_sql.m_connection.commit();

        return tot_arti;

    }









    /***
        Invio la mail ai destinatari
        

    */

    public int sendMail ( ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        Atk_alert_spool atk_alert_spool = new Atk_alert_spool();
        Costanti_comm   costanti_comm   = new Costanti_comm();
        Atk_messaggio   atk_messaggio   = new Atk_messaggio();

        atk_sql.setProfilo((Atk_sql) atk_alert_spool);
        atk_sql.setProfilo((Atk_sql) costanti_comm  );
        atk_sql.setProfilo((Atk_sql) atk_messaggio  );
        
        System.out.println();
        System.out.println();
        System.out.println("\tF_alert_yacht.sendmail >>"+"INI"+"<<");

        // verifico se ci sono nuovi articoli 

        l_query  = "";
        l_query += " select count(*)                   \n";
        l_query += "   from pgmr.atk_alert_spool       \n";
        l_query += "  where id_alert = "+il_id_alert+" \n";
        l_query += "    and tksend_alert is null       \n";
        
        long tot_arti = atk_sql.sql_long(l_query);


        System.out.println("\tF_alert_yacht.sendmail >>\n"+l_query+"\n<<");
        System.out.println("\tF_alert_yacht.sendmail tot_arti >>"+tot_arti+"<<");


        if (tot_arti <= 0){
            return 0;
        }





        
        // Trovo il modelllo

        String ls_path_mail = "";
        String ls_oggetto   = "";
        String ls_cdutente  = "";


        l_query  = "";
        l_query += " select b.path_mail            \n";
        l_query += "      , b.oggetto              \n";
        l_query += "      , a.cdutente             \n";
        l_query += "   from pgmr.atk_alert a       \n";
        l_query += "      , pgmr.atk_alert_class b \n";
        l_query += "  where a.id_class = b.id      \n";
        l_query += "    and a.id = "+il_id_alert+" \n";
        
        System.out.println("\tF_alert_yacht.sendmail >>\n"+l_query+"\n<<");

        pstm = atk_sql.m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){
            
            if (rs.getObject("path_mail")!=null)  ls_path_mail = rs.getString("path_mail");
            if (rs.getObject("oggetto"  )!=null)  ls_oggetto   = rs.getString("oggetto");
            if (rs.getObject("cdutente" )!=null)  ls_cdutente  = rs.getString("cdutente");
        }
        pstm.close();

        if (ls_oggetto.equals(""))  ls_oggetto = "Media Yacht News";

        
        File file_mail = new File(ls_path_mail);

        if (!file_mail.exists()){

            padre.sendFatalError("Alert ID = "+il_id_alert+" \n\nMod. Mail NON Trovato:\n\n path: >>"+ls_path_mail+"<<");
            return 0;
        }

        String ls_modello = getModel(ls_path_mail);

        if (ls_modello.equals("")){
            padre.sendFatalError("Alert ID = "+il_id_alert+" \n\nMod. Mail VUOTO:\n\n path: >>"+ls_path_mail+"<<");
            return 0;
        }



        // --- Carico i destinatari

        
        String ep_alert_bcc = costanti_comm.getCostvalue("ep.alert_bcc");

        String ls_email       = "";
        String ls_mail_utente = "";

        Vector vec_email_to = new Vector();
         
        l_query   = "";
        l_query  += " select ale.email                    \n";
        l_query  += "      , ute.email    as mail_utente  \n";

        if (atk_sql.is_oracle){

            l_query  += "    from pgmr.atk_alert_dest ale         \n";
    		l_query  += "       , web.utente          ute         \n";
    		l_query  += "   where ale.cdutente = ute.cdutente(+)  \n";
    		l_query  += "     and ale.id_alert = "+il_id_alert+"  \n";

        }else if (atk_sql.is_sybase){
            
            l_query  += "    from {oj              pgmr.atk_alert_dest ale                                \n";
    		l_query  += "          left outer join web.utente          ute on ale.cdutente = ute.cdutente \n";
    		l_query  += "          }                                                                      \n";
    		l_query  += "   where ale.id_alert = "+il_id_alert+"                                          \n";

        }else {
            atk_sql.m_connection.rollback();
            throw new Exception("TIPO DB NON PREVISTO");
        }

        pstm = atk_sql.m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        while (rs!=null && rs.next()){
            
            ls_email       = "";
            ls_mail_utente = "";

            if (rs.getObject("email")!=null)  ls_email = rs.getString("email");
            if (rs.getObject("mail_utente")!=null)  ls_mail_utente = rs.getString("mail_utente");

            if (ls_email.equals("")){
                ls_email = ls_mail_utente;
            }

            if (!vec_email_to.contains(ls_email)){
                vec_email_to.add(ls_email);
            }
        }

        pstm.close();

        if (vec_email_to.size() == 0){
            
            padre.sendFatalError("Alert ID = "+il_id_alert+" \n\nDestinatario NON Trovato:\n\n ");
            return 0;
        }




        // --- Prenoto il Token dell'alert

        long tksend_alert = atk_alert_spool.getTksend_alert();
        
        atk_alert_spool.m_connection.commit();


        // Compongo il contenuto

        String ls_lista_articoli = "";


        l_query  = "";
        l_query += "  select arti.cdarti                                                                 \n";
        l_query += "       , arti.cdartm                                                                 \n";
        l_query += "       , arti.dsarti                                                                 \n";
        l_query += "       , arti.fg_tipo                                                                \n";
        l_query += "       , mod.dsit                         as dsmodello                               \n";
        l_query += "       , can.dsit                         as dscantiere                              \n";
        l_query += "       , arti.anno_costruzione_barca      as anno                                    \n";
        l_query += "       , arti.przbaseven                  as prezzo                                  \n";
        l_query += "    from {oj              pgmr.kg_articolo arti                                      \n";
        l_query += "          left outer join pgmr.kg_cantiere can on arti.tkcantiere = can.tkcantiere   \n";
        l_query += "          left outer join pgmr.kg_modello mod on arti.tkmodello = mod.tkmodello      \n";
        l_query += "          }                                                                          \n";
        l_query += "       , pgmr.atk_alert_spool ale                                                    \n";
        l_query += "   where ale.cdarti = arti.cdarti                                                    \n";
        l_query += "     and ale.id_alert = "+il_id_alert+"                                              \n";
        l_query += "     and ale.tksend_alert is null                                                    \n";
        l_query += "     and arti.fgabil = 'S'                                                           \n";//avendramin20080321

        pstm = atk_sql.m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            ls_lista_articoli = "<table class=\"report\">";
            ls_lista_articoli += "<tr>";
            ls_lista_articoli += "<th>Modello</th>";
            ls_lista_articoli += "<th>Costruttore</th>";
            ls_lista_articoli += "<th>Anno</th>";
            ls_lista_articoli += "<th>Tipo</th>";
            ls_lista_articoli += "<th>Prezzo</th>";
            ls_lista_articoli += "</tr>";

            String fg_tipo    = "";
            String dsmodello  = "";
            String dscantiere = "";
            int    anno       = 0;
            double prezzo     = 0;

            java.text.NumberFormat przFormat = java.text.NumberFormat.getInstance(java.util.Locale.ITALY);
            przFormat.setGroupingUsed( false );
            przFormat.setMaximumFractionDigits( 2 );
            przFormat.setMinimumFractionDigits( 2 );


            do {
                
                dsmodello  = "";
                dscantiere = "";
                anno       = 0;
                prezzo     = 0;
                fg_tipo    = "";

                
                if (rs.getObject("fg_tipo")!=null)  fg_tipo = rs.getString("fg_tipo");
                if (rs.getObject("dsmodello")!=null)  dsmodello = rs.getString("dsmodello");
                if (rs.getObject("dscantiere")!=null)  dscantiere = rs.getString("dscantiere");
                if (rs.getObject("anno")!=null)  anno = rs.getInt("anno");
                if (rs.getObject("prezzo")!=null)  prezzo = rs.getDouble("prezzo");

                ls_lista_articoli += "<tr>";
                ls_lista_articoli += "<td>"+dsmodello+"</td>";
                ls_lista_articoli += "<td>"+dscantiere+"</td>";
                ls_lista_articoli += "<td>"+anno+"</td>";
                ls_lista_articoli += "<td>"+fg_tipo+"</td>";
                ls_lista_articoli += "<td>"+przFormat.format(prezzo)+"</td>";
                ls_lista_articoli += "</tr>";

            }while (rs.next());

            ls_lista_articoli += "</table>";
            ls_lista_articoli += "<br/>";
            ls_lista_articoli += "<br/><span class=\"nota\">Tipo: (V) vela, (M) motore.</span>";
            ls_lista_articoli += "<br/>";
            ls_lista_articoli += "<br/>";

        }


        String   mail_http_res     = costanti_comm.getCostvalue ("mail_http_res");

        String ls_contenuto = ls_modello;

        ls_contenuto = StringUtils.replace(ls_contenuto, "\"img", "\""+mail_http_res+"/img");
        ls_contenuto = StringUtils.replace(ls_contenuto, "\"js/", "\""+mail_http_res+"/js/");
        ls_contenuto = StringUtils.replace(ls_contenuto, "../img", mail_http_res+"/img");
        ls_contenuto = StringUtils.replace(ls_contenuto, "@url_e-progen@", mail_http_res);

        ls_contenuto = StringUtils.replace(ls_contenuto, "@tot_arti@", ""+tot_arti);
        ls_contenuto = StringUtils.replace(ls_contenuto, "@lista_arti@", ls_lista_articoli);

        String ls_url_contator = "";
        ls_url_contator += mail_http_res + "/servlet/Contator";
        ls_url_contator += "?id_alert="+il_id_alert;
        ls_url_contator += "&id_servizio=1";
        ls_url_contator += "&evento=1";
        ls_url_contator += "&cdutente="+ls_cdutente;
        

        ls_contenuto = StringUtils.replace(ls_contenuto, "@url_contator@", ls_url_contator);
        
        // Invio della mail

        long        tkmsg        = 0;
        String      host         = "";
        String      user_name_smtp    = "";
        String      password     = "";
        String      oggetto      = ls_oggetto;
        String      mittente     = "";
        String      contenuto    = ls_contenuto;
        String      fgbloccamail = "N";
        long        id_object_t  = 0;
        Timestamp   dtricsped    = atk_sql.of_getTimestamp();
        long        tkml         = 0;

        String   pathfile     = "";
        String   nomefile     = "";
        long     id_object    = 0;
        String   fgconv_pdf   = "N";



        tot_rec = atk_messaggio.executeInsert( tkmsg    
                                             , host     
                                             , user_name_smtp 
                                             , password 
                                             , oggetto  
                                             , mittente 
                                             , contenuto
                                             , fgbloccamail
                                             , id_object_t
                                             , dtricsped
                                             , tkml
                                                              );
                                                  


        if (tot_rec <= 0){
            
            atk_sql.m_connection.rollback();
            throw new Exception("Errore Insert atk_messaggio !");
        }

        tkmsg =  atk_messaggio.tkmsg;

        if (!ep_alert_bcc.equals("")){
            
            tot_rec = atk_messaggio.setDest( tkmsg
                                           , ""                //  dest_to
                                           , ""                //  dest_cc
                                           , ep_alert_bcc      //  dest_bcc
                                                                        );
    
            if (tot_rec <= 0){
                
                atk_sql.m_connection.rollback();
                throw new Exception("Errore Insert destinatario!");
            }
        }

        for (int i=0;  i < vec_email_to.size(); i++){
            
            tot_rec = atk_messaggio.setDest( tkmsg
                                           , (String)vec_email_to.elementAt(i)      //  dest_to
                                           , ""                                     //  dest_cc
                                           , ep_alert_bcc                           //  dest_bcc
                                                                        );
        }

        // Aggiorno con il Token 


        l_query  = "";
        l_query += " update pgmr.atk_alert_spool            \n";
        l_query += "    set tksend_alert = "+tksend_alert+" \n";
        l_query += "  where id_alert = "+il_id_alert+"      \n";
        l_query += "    and tksend_alert is null            \n";

        tot_rec = atk_sql.sql_update(l_query);

        if (tot_rec <= 0){
            
            atk_sql.m_connection.rollback();
            throw new Exception("Errore Update atk_alert_spool set  tksend_alert !");
        }



        l_query  = "";
        l_query += " update pgmr.atk_messaggio              \n";
        l_query += "    set tksend_alert = "+tksend_alert+" \n";
        l_query += "  where tkmsg = "+tkmsg+"               \n";

        tot_rec = atk_sql.sql_update(l_query);

        if (tot_rec <= 0){
            
            atk_sql.m_connection.rollback();
            throw new Exception("Errore Update atk_messaggio set  tksend_alert tkmsg = "+tkmsg+" !");
        }


        atk_sql.m_connection.commit();







        return 1 ;

    }




}

