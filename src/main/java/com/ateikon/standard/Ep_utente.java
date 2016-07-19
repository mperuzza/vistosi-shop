package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Ep_utente extends Atk_sql {

    public Ep_utente() {

        super();
    }




    /****
        getAll: ep_utente
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.ep_utente  \n";
        if (is_farza_filtro_dipa.equals("S")){
            l_query  += "  where cddipa = '"+cddipa+"'  \n";
        }

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: ep_utente
    **/ 

    public ResultSet getKey( long       tkutente
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.ep_utente  \n";
        if (tkutente == 0 && null_tkutente){ 
            l_query  += "  where tkutente is null \n";
        }else { 
            l_query  += "  where tkutente = ?  \n";
        } 

        pstm = setQuery( l_query ) ;

        ind = 1;
        if (tkutente == 0 && null_tkutente){ 
        }else { 
            pstm.setLong      (ind, tkutente); ind += 1;
        } 

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: ep_utente
    **/ 


    public com.ateikon.structure.Rec_ep_utente  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_ep_utente lstr = new com.ateikon.structure.Rec_ep_utente();

        if (rs == null) return lstr;
        if (rs.getObject("tkutente")!=null) lstr.tkutente = rs.getLong      ("tkutente"); 
        if (rs.getObject("user_name")!=null) lstr.user_name = rs.getString    ("user_name"); 
        if (rs.getObject("password")!=null) lstr.password = rs.getString    ("password"); 
        if (rs.getObject("dsutente")!=null) lstr.dsutente = rs.getString    ("dsutente"); 
        if (rs.getObject("tkclie")!=null) lstr.tkclie = rs.getString    ("tkclie"); 
        if (rs.getObject("tkforn")!=null) lstr.tkforn = rs.getString    ("tkforn"); 
        if (rs.getObject("cdente")!=null) lstr.cdente = rs.getString    ("cdente"); 
        if (rs.getObject("cdagen")!=null) lstr.cdagen = rs.getString    ("cdagen"); 
        if (rs.getObject("cdutente")!=null) lstr.cdutente = rs.getString    ("cdutente"); 
        if (rs.getObject("fgadmin")!=null) lstr.fgadmin = rs.getString    ("fgadmin"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("email")!=null) lstr.email = rs.getString    ("email"); 
        if (rs.getObject("cdispe")!=null) lstr.cdispe = rs.getString    ("cdispe"); 
        if (rs.getObject("cdling")!=null) lstr.cdling = rs.getString    ("cdling"); 
        if (rs.getObject("cellulare")!=null) lstr.cellulare = rs.getString    ("cellulare"); 
        if (rs.getObject("nfaxsi")!=null) lstr.nfaxsi = rs.getString    ("nfaxsi"); 
        if (rs.getObject("ftp_user_name")!=null) lstr.ftp_user_name = rs.getString    ("ftp_user_name"); 
        if (rs.getObject("ftp_password")!=null) lstr.ftp_password = rs.getString    ("ftp_password"); 
        if (rs.getObject("ftp_password_back")!=null) lstr.ftp_password_back = rs.getString    ("ftp_password_back"); 
        if (rs.getObject("ftp_fgabil")!=null) lstr.ftp_fgabil = rs.getString    ("ftp_fgabil"); 
        if (rs.getObject("ftp_fgabil_back")!=null) lstr.ftp_fgabil_back = rs.getString    ("ftp_fgabil_back"); 
        if (rs.getObject("fgabil")!=null) lstr.fgabil = rs.getString    ("fgabil"); 
        if (rs.getObject("tktputente")!=null) lstr.tktputente = rs.getLong      ("tktputente"); 
        if (rs.getObject("livello")!=null) lstr.livello = rs.getLong      ("livello"); 
        if (rs.getObject("tksubutente")!=null) lstr.tksubutente = rs.getLong      ("tksubutente"); 
        if (rs.getObject("dtsendpwd")!=null) lstr.dtsendpwd = rs.getTimestamp ("dtsendpwd"); 
        if (rs.getObject("dtabil")!=null) lstr.dtabil = rs.getTimestamp ("dtabil"); 
        if (rs.getObject("cdresp")!=null) lstr.cdresp = rs.getString    ("cdresp"); 
        if (rs.getObject("dtblocco")!=null) lstr.dtblocco = rs.getTimestamp ("dtblocco"); 

        return lstr;
    }




    /****
        preupdate: ep_utente

        sovrascrivere per impostare i controlli da effetuare prima dell'execute
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_ep_utente astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }





    /****
        preupdate: ep_utente

        sovrascrivere per impostare i controlli da effetuare per sincro

        questo metodo ï¿½ stato introdotto per la sincro: in fase di export prima  
        di scrivere il file XML viene chiamato
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_ep_utente astr, ResultSet rs) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }



    /****
        execute: ep_utente
    **/ 


    public int execute ( com.ateikon.structure.Rec_ep_utente astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        // --- Controllo che il profilo sia valorizzato

        if (astr.cdazie.equals("")) astr.cdazie = cdazie;
        if (astr.cddipa.equals("")) astr.cddipa = cddipa;
        if (astr.profil.equals("")) astr.profil = profil;
        if (astr.dtinse == null) astr.dtinse = of_getTimestamp();
        if (astr.dtulag == null) astr.dtulag = of_getTimestamp();

        if (ib_ctrl_mindate){
            astr.ctrl_mindate();
        }

        preupdate(astr);

        l_query   = "";
        l_query  += " select tkutente\n";
        l_query  += "   from pgmr.ep_utente  \n";
        if (astr.tkutente == 0 && null_tkutente){ 
            l_query  += "  where tkutente is null \n";
        }else { 
            l_query  += "  where tkutente = ?  \n";
        } 


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (astr.tkutente == 0 && null_tkutente){ 
        }else { 
            pstm.setLong      (ind, astr.tkutente); ind += 1;
        } 

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            pstm.close();

            // --- aggiorno il profilo
            astr.dtulag = of_getTimestamp();
            astr.profil = profil;


            tot_rec = executeUpdate (astr);
        }else {

            pstm.close();
            tot_rec = executeInsert (astr);
        }

        /* lsp 200904 
        if (tot_rec != 1){

            m_connection.rollback();
            throw new Exception("Errore Agg. ep_utente! ");
        }
        */ 

        return tot_rec;
    }




    /****
        executeInsert: ep_utente
    **/ 


    public int executeInsert( com.ateikon.structure.Rec_ep_utente astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        if (ib_calcola_token){
            long ll_tk = getNew_token( );

            if (ll_tk <= 0){
                throw new Exception ("Error Keypool (ep_utente): "+ll_tk );
            }

            this.tkutente = ll_tk;
            astr.tkutente = this.tkutente;
        }else {
            this.tkutente = astr.tkutente;
        }



        l_query   = "";
        l_query  += " insert into pgmr.ep_utente ( \n";
        l_query  += "             tkutente   \n";
        l_query  += "           , user_name   \n";
        l_query  += "           , password   \n";
        l_query  += "           , dsutente   \n";
        l_query  += "           , tkclie   \n";
        l_query  += "           , tkforn   \n";
        l_query  += "           , cdente   \n";
        l_query  += "           , cdagen   \n";
        l_query  += "           , cdutente   \n";
        l_query  += "           , fgadmin   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "           , email   \n";
        l_query  += "           , cdispe   \n";
        l_query  += "           , cdling   \n";
        l_query  += "           , cellulare   \n";
        l_query  += "           , nfaxsi   \n";
        l_query  += "           , ftp_user_name   \n";
        l_query  += "           , ftp_password   \n";
        l_query  += "           , ftp_password_back   \n";
        l_query  += "           , ftp_fgabil   \n";
        l_query  += "           , ftp_fgabil_back   \n";
        l_query  += "           , fgabil   \n";
        l_query  += "           , tktputente   \n";
        l_query  += "           , livello   \n";
        l_query  += "           , tksubutente   \n";
        l_query  += "           , dtsendpwd   \n";
        l_query  += "           , dtabil   \n";
        l_query  += "           , cdresp   \n";
        l_query  += "           , dtblocco   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.user_name.equals("")) astr.user_name = null;
        if (astr.password.equals("")) astr.password = null;
        if (astr.dsutente.equals("")) astr.dsutente = null;
        if (astr.tkclie.equals("")) astr.tkclie = null;
        if (astr.tkforn.equals("")) astr.tkforn = null;
        if (astr.cdente.equals("")) astr.cdente = null;
        if (astr.cdagen.equals("")) astr.cdagen = null;
        if (astr.cdutente.equals("")) astr.cdutente = null;
        if (astr.fgadmin.equals("")) astr.fgadmin = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;
        if (astr.email.equals("")) astr.email = null;
        if (astr.cdispe.equals("")) astr.cdispe = null;
        if (astr.cdling.equals("")) astr.cdling = null;
        if (astr.cellulare.equals("")) astr.cellulare = null;
        if (astr.nfaxsi.equals("")) astr.nfaxsi = null;
        if (astr.ftp_user_name.equals("")) astr.ftp_user_name = null;
        if (astr.ftp_password.equals("")) astr.ftp_password = null;
        if (astr.ftp_password_back.equals("")) astr.ftp_password_back = null;
        if (astr.ftp_fgabil.equals("")) astr.ftp_fgabil = null;
        if (astr.ftp_fgabil_back.equals("")) astr.ftp_fgabil_back = null;
        if (astr.fgabil.equals("")) astr.fgabil = null;
        if (astr.cdresp.equals("")) astr.cdresp = null;


        ind = 1;
        if (astr.tkutente == 0 && null_tkutente){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkutente); ind += 1;
        } 
        pstm.setString    (ind, astr.user_name); ind += 1;
        pstm.setString    (ind, astr.password); ind += 1;
        pstm.setString    (ind, astr.dsutente); ind += 1;
        pstm.setString    (ind, astr.tkclie); ind += 1;
        pstm.setString    (ind, astr.tkforn); ind += 1;
        pstm.setString    (ind, astr.cdente); ind += 1;
        pstm.setString    (ind, astr.cdagen); ind += 1;
        pstm.setString    (ind, astr.cdutente); ind += 1;
        pstm.setString    (ind, astr.fgadmin); ind += 1;
        pstm.setString    (ind, astr.cdazie); ind += 1;
        pstm.setString    (ind, astr.cddipa); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtinse); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;
        pstm.setString    (ind, astr.email); ind += 1;
        pstm.setString    (ind, astr.cdispe); ind += 1;
        pstm.setString    (ind, astr.cdling); ind += 1;
        pstm.setString    (ind, astr.cellulare); ind += 1;
        pstm.setString    (ind, astr.nfaxsi); ind += 1;
        pstm.setString    (ind, astr.ftp_user_name); ind += 1;
        pstm.setString    (ind, astr.ftp_password); ind += 1;
        pstm.setString    (ind, astr.ftp_password_back); ind += 1;
        pstm.setString    (ind, astr.ftp_fgabil); ind += 1;
        pstm.setString    (ind, astr.ftp_fgabil_back); ind += 1;
        pstm.setString    (ind, astr.fgabil); ind += 1;
        if (astr.tktputente == 0 && null_tktputente){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tktputente); ind += 1;
        } 
        if (astr.livello == 0 && null_livello){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.livello); ind += 1;
        } 
        if (astr.tksubutente == 0 && null_tksubutente){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tksubutente); ind += 1;
        } 
        pstm.setTimestamp (ind, astr.dtsendpwd); ind += 1;
        pstm.setTimestamp (ind, astr.dtabil); ind += 1;
        pstm.setString    (ind, astr.cdresp); ind += 1;
        pstm.setTimestamp (ind, astr.dtblocco); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        // --- Ripristino le Stringhe NULL

        if (astr.user_name == null) astr.user_name = "";
        if (astr.password == null) astr.password = "";
        if (astr.dsutente == null) astr.dsutente = "";
        if (astr.tkclie == null) astr.tkclie = "";
        if (astr.tkforn == null) astr.tkforn = "";
        if (astr.cdente == null) astr.cdente = "";
        if (astr.cdagen == null) astr.cdagen = "";
        if (astr.cdutente == null) astr.cdutente = "";
        if (astr.fgadmin == null) astr.fgadmin = "";
        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil == null) astr.profil = "";
        if (astr.email == null) astr.email = "";
        if (astr.cdispe == null) astr.cdispe = "";
        if (astr.cdling == null) astr.cdling = "";
        if (astr.cellulare == null) astr.cellulare = "";
        if (astr.nfaxsi == null) astr.nfaxsi = "";
        if (astr.ftp_user_name == null) astr.ftp_user_name = "";
        if (astr.ftp_password == null) astr.ftp_password = "";
        if (astr.ftp_password_back == null) astr.ftp_password_back = "";
        if (astr.ftp_fgabil == null) astr.ftp_fgabil = "";
        if (astr.ftp_fgabil_back == null) astr.ftp_fgabil_back = "";
        if (astr.fgabil == null) astr.fgabil = "";
        if (astr.cdresp == null) astr.cdresp = "";


        return tot_rec;

    }




    /***
        Restituisce il token per questa tabella
    */


    public long getNew_token( ) throws Exception{ 
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        F_tabkey f_tabkey = new F_tabkey();
        setProfilo((Atk_sql) f_tabkey );

        long ll_tk = f_tabkey.getTabkey( "ep_utente" );

        return ll_tk;

    }




    /****
        executeUpdate: ep_utente
    **/ 


    public int executeUpdate( com.ateikon.structure.Rec_ep_utente astr ) throws Exception { 
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.ep_utente  \n";
        l_query  += "         set user_name = ?  \n";
        l_query  += "           , password = ?  \n";
        l_query  += "           , dsutente = ?  \n";
        l_query  += "           , tkclie = ?  \n";
        l_query  += "           , tkforn = ?  \n";
        l_query  += "           , cdente = ?  \n";
        l_query  += "           , cdagen = ?  \n";
        l_query  += "           , cdutente = ?  \n";
        l_query  += "           , fgadmin = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "           , email = ?  \n";
        l_query  += "           , cdispe = ?  \n";
        l_query  += "           , cdling = ?  \n";
        l_query  += "           , cellulare = ?  \n";
        l_query  += "           , nfaxsi = ?  \n";
        l_query  += "           , ftp_user_name = ?  \n";
        l_query  += "           , ftp_password = ?  \n";
        l_query  += "           , ftp_password_back = ?  \n";
        l_query  += "           , ftp_fgabil = ?  \n";
        l_query  += "           , ftp_fgabil_back = ?  \n";
        l_query  += "           , fgabil = ?  \n";
        l_query  += "           , tktputente = ?  \n";
        l_query  += "           , livello = ?  \n";
        l_query  += "           , tksubutente = ?  \n";
        l_query  += "           , dtsendpwd = ?  \n";
        l_query  += "           , dtabil = ?  \n";
        l_query  += "           , cdresp = ?  \n";
        l_query  += "           , dtblocco = ?  \n";
        l_query  += "  where tkutente = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.user_name.equals("")) astr.user_name = null;
        if (astr.password.equals("")) astr.password = null;
        if (astr.dsutente.equals("")) astr.dsutente = null;
        if (astr.tkclie.equals("")) astr.tkclie = null;
        if (astr.tkforn.equals("")) astr.tkforn = null;
        if (astr.cdente.equals("")) astr.cdente = null;
        if (astr.cdagen.equals("")) astr.cdagen = null;
        if (astr.cdutente.equals("")) astr.cdutente = null;
        if (astr.fgadmin.equals("")) astr.fgadmin = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;
        if (astr.email.equals("")) astr.email = null;
        if (astr.cdispe.equals("")) astr.cdispe = null;
        if (astr.cdling.equals("")) astr.cdling = null;
        if (astr.cellulare.equals("")) astr.cellulare = null;
        if (astr.nfaxsi.equals("")) astr.nfaxsi = null;
        if (astr.ftp_user_name.equals("")) astr.ftp_user_name = null;
        if (astr.ftp_password.equals("")) astr.ftp_password = null;
        if (astr.ftp_password_back.equals("")) astr.ftp_password_back = null;
        if (astr.ftp_fgabil.equals("")) astr.ftp_fgabil = null;
        if (astr.ftp_fgabil_back.equals("")) astr.ftp_fgabil_back = null;
        if (astr.fgabil.equals("")) astr.fgabil = null;
        if (astr.cdresp.equals("")) astr.cdresp = null;


        ind = 1;
        pstm.setString    (ind, astr.user_name); ind += 1;
        pstm.setString    (ind, astr.password); ind += 1;
        pstm.setString    (ind, astr.dsutente); ind += 1;
        pstm.setString    (ind, astr.tkclie); ind += 1;
        pstm.setString    (ind, astr.tkforn); ind += 1;
        pstm.setString    (ind, astr.cdente); ind += 1;
        pstm.setString    (ind, astr.cdagen); ind += 1;
        pstm.setString    (ind, astr.cdutente); ind += 1;
        pstm.setString    (ind, astr.fgadmin); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;
        pstm.setString    (ind, astr.email); ind += 1;
        pstm.setString    (ind, astr.cdispe); ind += 1;
        pstm.setString    (ind, astr.cdling); ind += 1;
        pstm.setString    (ind, astr.cellulare); ind += 1;
        pstm.setString    (ind, astr.nfaxsi); ind += 1;
        pstm.setString    (ind, astr.ftp_user_name); ind += 1;
        pstm.setString    (ind, astr.ftp_password); ind += 1;
        pstm.setString    (ind, astr.ftp_password_back); ind += 1;
        pstm.setString    (ind, astr.ftp_fgabil); ind += 1;
        pstm.setString    (ind, astr.ftp_fgabil_back); ind += 1;
        pstm.setString    (ind, astr.fgabil); ind += 1;
        if (astr.tktputente == 0 && null_tktputente){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tktputente); ind += 1;
        } 
        if (astr.livello == 0 && null_livello){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.livello); ind += 1;
        } 
        if (astr.tksubutente == 0 && null_tksubutente){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tksubutente); ind += 1;
        } 
        pstm.setTimestamp (ind, astr.dtsendpwd); ind += 1;
        pstm.setTimestamp (ind, astr.dtabil); ind += 1;
        pstm.setString    (ind, astr.cdresp); ind += 1;
        pstm.setTimestamp (ind, astr.dtblocco); ind += 1;

        if (astr.tkutente == 0 && null_tkutente){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkutente); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.tkutente = astr.tkutente; 

        // --- Ripristino le Stringhe NULL

        if (astr.user_name == null) astr.user_name = "";
        if (astr.password == null) astr.password = "";
        if (astr.dsutente == null) astr.dsutente = "";
        if (astr.tkclie == null) astr.tkclie = "";
        if (astr.tkforn == null) astr.tkforn = "";
        if (astr.cdente == null) astr.cdente = "";
        if (astr.cdagen == null) astr.cdagen = "";
        if (astr.cdutente == null) astr.cdutente = "";
        if (astr.fgadmin == null) astr.fgadmin = "";
        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil == null) astr.profil = "";
        if (astr.email == null) astr.email = "";
        if (astr.cdispe == null) astr.cdispe = "";
        if (astr.cdling == null) astr.cdling = "";
        if (astr.cellulare == null) astr.cellulare = "";
        if (astr.nfaxsi == null) astr.nfaxsi = "";
        if (astr.ftp_user_name == null) astr.ftp_user_name = "";
        if (astr.ftp_password == null) astr.ftp_password = "";
        if (astr.ftp_password_back == null) astr.ftp_password_back = "";
        if (astr.ftp_fgabil == null) astr.ftp_fgabil = "";
        if (astr.ftp_fgabil_back == null) astr.ftp_fgabil_back = "";
        if (astr.fgabil == null) astr.fgabil = "";
        if (astr.cdresp == null) astr.cdresp = "";


        return tot_rec;

    }



    /****
        getKey: ep_utente
    **/ 

    public int deleteKey( long       tkutente
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " delete \n";
        l_query  += "   from pgmr.ep_utente  \n";
        if (tkutente == 0 && null_tkutente){ 
            l_query  += "  where tkutente is null \n";
        }else { 
            l_query  += "  where tkutente = ?  \n";
        } 

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (tkutente == 0 && null_tkutente){ 
        }else { 
            pstm.setLong      (ind, tkutente); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();
        pstm.close();

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public long       tkutente = 0; 






    // --- Impostando a false queste prop. il def. ï¿½ 0

    public boolean ib_calcola_token = true;
    public boolean ib_ctrl_mindate = false;
    public boolean null_tkutente = true;
    public boolean null_tktputente = true;
    public boolean null_livello = true;
    public boolean null_tksubutente = true;




    public String is_farza_filtro_dipa = "N";



}

