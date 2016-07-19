package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Ep_lingua_prg extends Atk_sql {

    public Ep_lingua_prg() {

        super();
    }




    /****
        getAll: ep_lingua_prg
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.ep_lingua_prg  \n";
        if (is_farza_filtro_dipa.equals("S")){
            l_query  += "  where cddipa = '"+cddipa+"'  \n";
        }

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: ep_lingua_prg
    **/ 

    public ResultSet getKey( long       tklingua_prg
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.ep_lingua_prg  \n";
        if (tklingua_prg == 0 && null_tklingua_prg){ 
            l_query  += "  where tklingua_prg is null \n";
        }else { 
            l_query  += "  where tklingua_prg = ?  \n";
        } 

        pstm = setQuery( l_query ) ;

        ind = 1;
        if (tklingua_prg == 0 && null_tklingua_prg){ 
        }else { 
            pstm.setLong      (ind, tklingua_prg); ind += 1;
        } 

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: ep_lingua_prg
    **/ 


    public com.ateikon.structure.Rec_ep_lingua_prg  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_ep_lingua_prg lstr = new com.ateikon.structure.Rec_ep_lingua_prg();

        if (rs == null) return lstr;
        if (rs.getObject("tklingua_prg")!=null) lstr.tklingua_prg = rs.getLong      ("tklingua_prg"); 
        if (rs.getObject("cdprogetto")!=null) lstr.cdprogetto = rs.getString    ("cdprogetto"); 
        if (rs.getObject("virtualhost")!=null) lstr.virtualhost = rs.getString    ("virtualhost"); 
        if (rs.getObject("contextpath")!=null) lstr.contextpath = rs.getString    ("contextpath"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("webinfpath")!=null) lstr.webinfpath = rs.getString    ("webinfpath"); 
        if (rs.getObject("fgtraduci")!=null) lstr.fgtraduci = rs.getString    ("fgtraduci"); 

        return lstr;
    }




    /****
        preupdate: ep_lingua_prg

        sovrascrivere per impostare i controlli da effetuare prima dell'execute
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_ep_lingua_prg astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }





    /****
        preupdate: ep_lingua_prg

        sovrascrivere per impostare i controlli da effetuare per sincro

        questo metodo ï¿½ stato introdotto per la sincro: in fase di export prima  
        di scrivere il file XML viene chiamato
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_ep_lingua_prg astr, ResultSet rs) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }



    /****
        execute: ep_lingua_prg
    **/ 


    public int execute ( com.ateikon.structure.Rec_ep_lingua_prg astr) throws Exception {
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
        l_query  += " select tklingua_prg\n";
        l_query  += "   from pgmr.ep_lingua_prg  \n";
        if (astr.tklingua_prg == 0 && null_tklingua_prg){ 
            l_query  += "  where tklingua_prg is null \n";
        }else { 
            l_query  += "  where tklingua_prg = ?  \n";
        } 


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (astr.tklingua_prg == 0 && null_tklingua_prg){ 
        }else { 
            pstm.setLong      (ind, astr.tklingua_prg); ind += 1;
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
            throw new Exception("Errore Agg. ep_lingua_prg! ");
        }
        */ 

        return tot_rec;
    }




    /****
        executeInsert: ep_lingua_prg
    **/ 


    public int executeInsert( com.ateikon.structure.Rec_ep_lingua_prg astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        if (ib_calcola_token){
            long ll_tk = getNew_token( );

            if (ll_tk <= 0){
                throw new Exception ("Error Keypool (ep_lingua_prg): "+ll_tk );
            }

            this.tklingua_prg = ll_tk;
            astr.tklingua_prg = this.tklingua_prg;
        }else {
            this.tklingua_prg = astr.tklingua_prg;
        }



        l_query   = "";
        l_query  += " insert into pgmr.ep_lingua_prg ( \n";
        l_query  += "             tklingua_prg   \n";
        l_query  += "           , cdprogetto   \n";
        l_query  += "           , virtualhost   \n";
        l_query  += "           , contextpath   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "           , webinfpath   \n";
        l_query  += "           , fgtraduci   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.cdprogetto.equals("")) astr.cdprogetto = null;
        if (astr.virtualhost.equals("")) astr.virtualhost = null;
        if (astr.contextpath.equals("")) astr.contextpath = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;
        if (astr.webinfpath.equals("")) astr.webinfpath = null;
        if (astr.fgtraduci.equals("")) astr.fgtraduci = null;


        ind = 1;
        if (astr.tklingua_prg == 0 && null_tklingua_prg){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tklingua_prg); ind += 1;
        } 
        pstm.setString    (ind, astr.cdprogetto); ind += 1;
        pstm.setString    (ind, astr.virtualhost); ind += 1;
        pstm.setString    (ind, astr.contextpath); ind += 1;
        pstm.setString    (ind, astr.cdazie); ind += 1;
        pstm.setString    (ind, astr.cddipa); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtinse); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;
        pstm.setString    (ind, astr.webinfpath); ind += 1;
        pstm.setString    (ind, astr.fgtraduci); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        // --- Ripristino le Stringhe NULL

        if (astr.cdprogetto == null) astr.cdprogetto = "";
        if (astr.virtualhost == null) astr.virtualhost = "";
        if (astr.contextpath == null) astr.contextpath = "";
        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil == null) astr.profil = "";
        if (astr.webinfpath == null) astr.webinfpath = "";
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

        long ll_tk = f_tabkey.getTabkey( "ep_lingua_prg" );

        return ll_tk;

    }




    /****
        executeUpdate: ep_lingua_prg
    **/ 


    public int executeUpdate( com.ateikon.structure.Rec_ep_lingua_prg astr ) throws Exception { 
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.ep_lingua_prg  \n";
        l_query  += "         set cdprogetto = ?  \n";
        l_query  += "           , virtualhost = ?  \n";
        l_query  += "           , contextpath = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "           , webinfpath = ?  \n";
        l_query  += "           , fgtraduci = ?  \n";
        l_query  += "  where tklingua_prg = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.cdprogetto.equals("")) astr.cdprogetto = null;
        if (astr.virtualhost.equals("")) astr.virtualhost = null;
        if (astr.contextpath.equals("")) astr.contextpath = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;
        if (astr.webinfpath.equals("")) astr.webinfpath = null;
        if (astr.fgtraduci.equals("")) astr.fgtraduci = null;


        ind = 1;
        pstm.setString    (ind, astr.cdprogetto); ind += 1;
        pstm.setString    (ind, astr.virtualhost); ind += 1;
        pstm.setString    (ind, astr.contextpath); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;
        pstm.setString    (ind, astr.webinfpath); ind += 1;
        pstm.setString    (ind, astr.fgtraduci); ind += 1;

        if (astr.tklingua_prg == 0 && null_tklingua_prg){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tklingua_prg); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.tklingua_prg = astr.tklingua_prg; 

        // --- Ripristino le Stringhe NULL

        if (astr.cdprogetto == null) astr.cdprogetto = "";
        if (astr.virtualhost == null) astr.virtualhost = "";
        if (astr.contextpath == null) astr.contextpath = "";
        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil == null) astr.profil = "";
        if (astr.webinfpath == null) astr.webinfpath = "";
        if (astr.fgtraduci == null) astr.fgtraduci = "";


        return tot_rec;

    }



    /****
        getKey: ep_lingua_prg
    **/ 

    public int deleteKey( long       tklingua_prg
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " delete \n";
        l_query  += "   from pgmr.ep_lingua_prg  \n";
        if (tklingua_prg == 0 && null_tklingua_prg){ 
            l_query  += "  where tklingua_prg is null \n";
        }else { 
            l_query  += "  where tklingua_prg = ?  \n";
        } 

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (tklingua_prg == 0 && null_tklingua_prg){ 
        }else { 
            pstm.setLong      (ind, tklingua_prg); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();
        pstm.close();

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public long       tklingua_prg = 0; 






    // --- Impostando a false queste prop. il def. ï¿½ 0

    public boolean ib_calcola_token = true;
    public boolean ib_ctrl_mindate = false;
    public boolean null_tklingua_prg = true;




    public String is_farza_filtro_dipa = "N";



}

