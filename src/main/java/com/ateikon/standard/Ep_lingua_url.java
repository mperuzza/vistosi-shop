package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Ep_lingua_url extends Atk_sql {

    public Ep_lingua_url() {

        super();
    }




    /****
        getAll: ep_lingua_url
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.ep_lingua_url  \n";
        if (is_farza_filtro_dipa.equals("S")){
            l_query  += "  where cddipa = '"+cddipa+"'  \n";
        }

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: ep_lingua_url
    **/ 

    public ResultSet getKey( long       tklingua_url
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.ep_lingua_url  \n";
        if (tklingua_url == 0 && null_tklingua_url){ 
            l_query  += "  where tklingua_url is null \n";
        }else { 
            l_query  += "  where tklingua_url = ?  \n";
        } 

        pstm = setQuery( l_query ) ;

        ind = 1;
        if (tklingua_url == 0 && null_tklingua_url){ 
        }else { 
            pstm.setLong      (ind, tklingua_url); ind += 1;
        } 

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: ep_lingua_url
    **/ 


    public com.ateikon.structure.Rec_ep_lingua_url  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_ep_lingua_url lstr = new com.ateikon.structure.Rec_ep_lingua_url();

        if (rs == null) return lstr;
        if (rs.getObject("tklingua_url")!=null) lstr.tklingua_url = rs.getLong      ("tklingua_url"); 
        if (rs.getObject("cdprogetto")!=null) lstr.cdprogetto = rs.getString    ("cdprogetto"); 
        if (rs.getObject("relativepath")!=null) lstr.relativepath = rs.getString    ("relativepath"); 
        if (rs.getObject("filename")!=null) lstr.filename = rs.getString    ("filename"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("fgtraduci")!=null) lstr.fgtraduci = rs.getString    ("fgtraduci"); 

        return lstr;
    }




    /****
        preupdate: ep_lingua_url

        sovrascrivere per impostare i controlli da effetuare prima dell'execute
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_ep_lingua_url astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }





    /****
        preupdate: ep_lingua_url

        sovrascrivere per impostare i controlli da effetuare per sincro

        questo metodo ï¿½ stato introdotto per la sincro: in fase di export prima  
        di scrivere il file XML viene chiamato
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_ep_lingua_url astr, ResultSet rs) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }



    /****
        execute: ep_lingua_url
    **/ 


    public int execute ( com.ateikon.structure.Rec_ep_lingua_url astr) throws Exception {
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
        l_query  += " select tklingua_url\n";
        l_query  += "   from pgmr.ep_lingua_url  \n";
        if (astr.tklingua_url == 0 && null_tklingua_url){ 
            l_query  += "  where tklingua_url is null \n";
        }else { 
            l_query  += "  where tklingua_url = ?  \n";
        } 


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (astr.tklingua_url == 0 && null_tklingua_url){ 
        }else { 
            pstm.setLong      (ind, astr.tklingua_url); ind += 1;
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
            throw new Exception("Errore Agg. ep_lingua_url! ");
        }
        */ 

        return tot_rec;
    }




    /****
        executeInsert: ep_lingua_url
    **/ 


    public int executeInsert( com.ateikon.structure.Rec_ep_lingua_url astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        if (ib_calcola_token){
            long ll_tk = getNew_token( );

            if (ll_tk <= 0){
                throw new Exception ("Error Keypool (ep_lingua_url): "+ll_tk );
            }

            this.tklingua_url = ll_tk;
            astr.tklingua_url = this.tklingua_url;
        }else {
            this.tklingua_url = astr.tklingua_url;
        }



        l_query   = "";
        l_query  += " insert into pgmr.ep_lingua_url ( \n";
        l_query  += "             tklingua_url   \n";
        l_query  += "           , cdprogetto   \n";
        l_query  += "           , relativepath   \n";
        l_query  += "           , filename   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "           , fgtraduci   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.cdprogetto.equals("")) astr.cdprogetto = null;
        if (astr.relativepath.equals("")) astr.relativepath = null;
        if (astr.filename.equals("")) astr.filename = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;
        if (astr.fgtraduci.equals("")) astr.fgtraduci = null;


        ind = 1;
        if (astr.tklingua_url == 0 && null_tklingua_url){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tklingua_url); ind += 1;
        } 
        pstm.setString    (ind, astr.cdprogetto); ind += 1;
        pstm.setString    (ind, astr.relativepath); ind += 1;
        pstm.setString    (ind, astr.filename); ind += 1;
        pstm.setString    (ind, astr.cdazie); ind += 1;
        pstm.setString    (ind, astr.cddipa); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtinse); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;
        pstm.setString    (ind, astr.fgtraduci); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        // --- Ripristino le Stringhe NULL

        if (astr.cdprogetto == null) astr.cdprogetto = "";
        if (astr.relativepath == null) astr.relativepath = "";
        if (astr.filename == null) astr.filename = "";
        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil == null) astr.profil = "";
        if (astr.fgtraduci == null) astr.fgtraduci = "";


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

        long ll_tk = f_tabkey.getTabkey( "ep_lingua_url" );

        return ll_tk;

    }




    /****
        executeUpdate: ep_lingua_url
    **/ 


    public int executeUpdate( com.ateikon.structure.Rec_ep_lingua_url astr ) throws Exception { 
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.ep_lingua_url  \n";
        l_query  += "         set cdprogetto = ?  \n";
        l_query  += "           , relativepath = ?  \n";
        l_query  += "           , filename = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "           , fgtraduci = ?  \n";
        l_query  += "  where tklingua_url = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.cdprogetto.equals("")) astr.cdprogetto = null;
        if (astr.relativepath.equals("")) astr.relativepath = null;
        if (astr.filename.equals("")) astr.filename = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;
        if (astr.fgtraduci.equals("")) astr.fgtraduci = null;


        ind = 1;
        pstm.setString    (ind, astr.cdprogetto); ind += 1;
        pstm.setString    (ind, astr.relativepath); ind += 1;
        pstm.setString    (ind, astr.filename); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;
        pstm.setString    (ind, astr.fgtraduci); ind += 1;

        if (astr.tklingua_url == 0 && null_tklingua_url){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tklingua_url); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.tklingua_url = astr.tklingua_url; 

        // --- Ripristino le Stringhe NULL

        if (astr.cdprogetto == null) astr.cdprogetto = "";
        if (astr.relativepath == null) astr.relativepath = "";
        if (astr.filename == null) astr.filename = "";
        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil == null) astr.profil = "";
        if (astr.fgtraduci == null) astr.fgtraduci = "";


        return tot_rec;

    }



    /****
        getKey: ep_lingua_url
    **/ 

    public int deleteKey( long       tklingua_url
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " delete \n";
        l_query  += "   from pgmr.ep_lingua_url  \n";
        if (tklingua_url == 0 && null_tklingua_url){ 
            l_query  += "  where tklingua_url is null \n";
        }else { 
            l_query  += "  where tklingua_url = ?  \n";
        } 

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (tklingua_url == 0 && null_tklingua_url){ 
        }else { 
            pstm.setLong      (ind, tklingua_url); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();
        pstm.close();

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public long       tklingua_url = 0; 






    // --- Impostando a false queste prop. il def. ï¿½ 0

    public boolean ib_calcola_token = true;
    public boolean ib_ctrl_mindate = false;
    public boolean null_tklingua_url = true;




    public String is_farza_filtro_dipa = "N";



}

