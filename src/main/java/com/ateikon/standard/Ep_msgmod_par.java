package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Ep_msgmod_par extends Atk_sql {

    public Ep_msgmod_par() {

        super();
    }




    /****
        getAll: ep_msgmod_par
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.ep_msgmod_par  \n";
        if (is_farza_filtro_dipa.equals("S")){
            l_query  += "  where cddipa = '"+cddipa+"'  \n";
        }

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: ep_msgmod_par
    **/ 

    public ResultSet getKey( long       tkmsgmod_par
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.ep_msgmod_par  \n";
        if (tkmsgmod_par == 0 && null_tkmsgmod_par){ 
            l_query  += "  where tkmsgmod_par is null \n";
        }else { 
            l_query  += "  where tkmsgmod_par = ?  \n";
        } 

        pstm = setQuery( l_query ) ;

        ind = 1;
        if (tkmsgmod_par == 0 && null_tkmsgmod_par){ 
        }else { 
            pstm.setLong      (ind, tkmsgmod_par); ind += 1;
        } 

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: ep_msgmod_par
    **/ 


    public com.ateikon.structure.Rec_ep_msgmod_par  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_ep_msgmod_par lstr = new com.ateikon.structure.Rec_ep_msgmod_par();

        if (rs == null) return lstr;
        if (rs.getObject("tkmsgmod_par")!=null) lstr.tkmsgmod_par = rs.getLong      ("tkmsgmod_par"); 
        if (rs.getObject("cdmsgmod_appl")!=null) lstr.cdmsgmod_appl = rs.getString    ("cdmsgmod_appl"); 
        if (rs.getObject("cdmsgmod_par")!=null) lstr.cdmsgmod_par = rs.getString    ("cdmsgmod_par"); 
        if (rs.getObject("dsmsgmod_par")!=null) lstr.dsmsgmod_par = rs.getString    ("dsmsgmod_par"); 
        if (rs.getObject("categoria_par")!=null) lstr.categoria_par = rs.getString    ("categoria_par"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("fg_lower")!=null) lstr.fg_lower = rs.getString    ("fg_lower"); 
        if (rs.getObject("fg_upper")!=null) lstr.fg_upper = rs.getString    ("fg_upper"); 
        if (rs.getObject("fg_capitalize")!=null) lstr.fg_capitalize = rs.getString    ("fg_capitalize"); 
        if (rs.getObject("fg_ori_lower")!=null) lstr.fg_ori_lower = rs.getString    ("fg_ori_lower"); 
        if (rs.getObject("fg_ori_upper")!=null) lstr.fg_ori_upper = rs.getString    ("fg_ori_upper"); 
        if (rs.getObject("fg_ori_capitalize")!=null) lstr.fg_ori_capitalize = rs.getString    ("fg_ori_capitalize"); 

        return lstr;
    }




    /****
        preupdate: ep_msgmod_par

        sovrascrivere per impostare i controlli da effetuare prima dell'execute
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_ep_msgmod_par astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }





    /****
        preupdate: ep_msgmod_par

        sovrascrivere per impostare i controlli da effetuare per sincro

        questo metodo � stato introdotto per la sincro: in fase di export prima  
        di scrivere il file XML viene chiamato
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_ep_msgmod_par astr, ResultSet rs) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }



    /****
        execute: ep_msgmod_par
    **/ 


    public int execute ( com.ateikon.structure.Rec_ep_msgmod_par astr) throws Exception {
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
        l_query  += " select tkmsgmod_par\n";
        l_query  += "   from pgmr.ep_msgmod_par  \n";
        if (astr.tkmsgmod_par == 0 && null_tkmsgmod_par){ 
            l_query  += "  where tkmsgmod_par is null \n";
        }else { 
            l_query  += "  where tkmsgmod_par = ?  \n";
        } 


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (astr.tkmsgmod_par == 0 && null_tkmsgmod_par){ 
        }else { 
            pstm.setLong      (ind, astr.tkmsgmod_par); ind += 1;
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
            throw new Exception("Errore Agg. ep_msgmod_par! ");
        }
        */ 

        return tot_rec;
    }




    /****
        executeInsert: ep_msgmod_par
    **/ 


    public int executeInsert( com.ateikon.structure.Rec_ep_msgmod_par astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        if (ib_calcola_token){
            long ll_tk = getNew_token( );

            if (ll_tk <= 0){
                throw new Exception ("Error Keypool (ep_msgmod_par): "+ll_tk );
            }

            this.tkmsgmod_par = ll_tk;
            astr.tkmsgmod_par = this.tkmsgmod_par;
        }else {
            this.tkmsgmod_par = astr.tkmsgmod_par;
        }



        l_query   = "";
        l_query  += " insert into pgmr.ep_msgmod_par ( \n";
        l_query  += "             tkmsgmod_par   \n";
        l_query  += "           , cdmsgmod_appl   \n";
        l_query  += "           , cdmsgmod_par   \n";
        l_query  += "           , dsmsgmod_par   \n";
        l_query  += "           , categoria_par   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "           , fg_lower   \n";
        l_query  += "           , fg_upper   \n";
        l_query  += "           , fg_capitalize   \n";
        l_query  += "           , fg_ori_lower   \n";
        l_query  += "           , fg_ori_upper   \n";
        l_query  += "           , fg_ori_capitalize   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.cdmsgmod_appl.equals("")) astr.cdmsgmod_appl = null;
        if (astr.cdmsgmod_par.equals("")) astr.cdmsgmod_par = null;
        if (astr.dsmsgmod_par.equals("")) astr.dsmsgmod_par = null;
        if (astr.categoria_par.equals("")) astr.categoria_par = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;
        if (astr.fg_lower.equals("")) astr.fg_lower = null;
        if (astr.fg_upper.equals("")) astr.fg_upper = null;
        if (astr.fg_capitalize.equals("")) astr.fg_capitalize = null;
        if (astr.fg_ori_lower.equals("")) astr.fg_ori_lower = null;
        if (astr.fg_ori_upper.equals("")) astr.fg_ori_upper = null;
        if (astr.fg_ori_capitalize.equals("")) astr.fg_ori_capitalize = null;


        ind = 1;
        if (astr.tkmsgmod_par == 0 && null_tkmsgmod_par){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkmsgmod_par); ind += 1;
        } 
        pstm.setString    (ind, astr.cdmsgmod_appl); ind += 1;
        pstm.setString    (ind, astr.cdmsgmod_par); ind += 1;
        pstm.setString    (ind, astr.dsmsgmod_par); ind += 1;
        pstm.setString    (ind, astr.categoria_par); ind += 1;
        pstm.setString    (ind, astr.cdazie); ind += 1;
        pstm.setString    (ind, astr.cddipa); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtinse); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;
        pstm.setString    (ind, astr.fg_lower); ind += 1;
        pstm.setString    (ind, astr.fg_upper); ind += 1;
        pstm.setString    (ind, astr.fg_capitalize); ind += 1;
        pstm.setString    (ind, astr.fg_ori_lower); ind += 1;
        pstm.setString    (ind, astr.fg_ori_upper); ind += 1;
        pstm.setString    (ind, astr.fg_ori_capitalize); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        // --- Ripristino le Stringhe NULL

        if (astr.cdmsgmod_appl == null) astr.cdmsgmod_appl = "";
        if (astr.cdmsgmod_par == null) astr.cdmsgmod_par = "";
        if (astr.dsmsgmod_par == null) astr.dsmsgmod_par = "";
        if (astr.categoria_par == null) astr.categoria_par = "";
        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil == null) astr.profil = "";
        if (astr.fg_lower == null) astr.fg_lower = "";
        if (astr.fg_upper == null) astr.fg_upper = "";
        if (astr.fg_capitalize == null) astr.fg_capitalize = "";
        if (astr.fg_ori_lower == null) astr.fg_ori_lower = "";
        if (astr.fg_ori_upper == null) astr.fg_ori_upper = "";
        if (astr.fg_ori_capitalize == null) astr.fg_ori_capitalize = "";


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

        long ll_tk = f_tabkey.getTabkey( "ep_msgmod_par" );

        return ll_tk;

    }




    /****
        executeUpdate: ep_msgmod_par
    **/ 


    public int executeUpdate( com.ateikon.structure.Rec_ep_msgmod_par astr ) throws Exception { 
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.ep_msgmod_par  \n";
        l_query  += "         set cdmsgmod_appl = ?  \n";
        l_query  += "           , cdmsgmod_par = ?  \n";
        l_query  += "           , dsmsgmod_par = ?  \n";
        l_query  += "           , categoria_par = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "           , fg_lower = ?  \n";
        l_query  += "           , fg_upper = ?  \n";
        l_query  += "           , fg_capitalize = ?  \n";
        l_query  += "           , fg_ori_lower = ?  \n";
        l_query  += "           , fg_ori_upper = ?  \n";
        l_query  += "           , fg_ori_capitalize = ?  \n";
        l_query  += "  where tkmsgmod_par = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.cdmsgmod_appl.equals("")) astr.cdmsgmod_appl = null;
        if (astr.cdmsgmod_par.equals("")) astr.cdmsgmod_par = null;
        if (astr.dsmsgmod_par.equals("")) astr.dsmsgmod_par = null;
        if (astr.categoria_par.equals("")) astr.categoria_par = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;
        if (astr.fg_lower.equals("")) astr.fg_lower = null;
        if (astr.fg_upper.equals("")) astr.fg_upper = null;
        if (astr.fg_capitalize.equals("")) astr.fg_capitalize = null;
        if (astr.fg_ori_lower.equals("")) astr.fg_ori_lower = null;
        if (astr.fg_ori_upper.equals("")) astr.fg_ori_upper = null;
        if (astr.fg_ori_capitalize.equals("")) astr.fg_ori_capitalize = null;


        ind = 1;
        pstm.setString    (ind, astr.cdmsgmod_appl); ind += 1;
        pstm.setString    (ind, astr.cdmsgmod_par); ind += 1;
        pstm.setString    (ind, astr.dsmsgmod_par); ind += 1;
        pstm.setString    (ind, astr.categoria_par); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;
        pstm.setString    (ind, astr.fg_lower); ind += 1;
        pstm.setString    (ind, astr.fg_upper); ind += 1;
        pstm.setString    (ind, astr.fg_capitalize); ind += 1;
        pstm.setString    (ind, astr.fg_ori_lower); ind += 1;
        pstm.setString    (ind, astr.fg_ori_upper); ind += 1;
        pstm.setString    (ind, astr.fg_ori_capitalize); ind += 1;

        if (astr.tkmsgmod_par == 0 && null_tkmsgmod_par){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkmsgmod_par); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.tkmsgmod_par = astr.tkmsgmod_par; 

        // --- Ripristino le Stringhe NULL

        if (astr.cdmsgmod_appl == null) astr.cdmsgmod_appl = "";
        if (astr.cdmsgmod_par == null) astr.cdmsgmod_par = "";
        if (astr.dsmsgmod_par == null) astr.dsmsgmod_par = "";
        if (astr.categoria_par == null) astr.categoria_par = "";
        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil == null) astr.profil = "";
        if (astr.fg_lower == null) astr.fg_lower = "";
        if (astr.fg_upper == null) astr.fg_upper = "";
        if (astr.fg_capitalize == null) astr.fg_capitalize = "";
        if (astr.fg_ori_lower == null) astr.fg_ori_lower = "";
        if (astr.fg_ori_upper == null) astr.fg_ori_upper = "";
        if (astr.fg_ori_capitalize == null) astr.fg_ori_capitalize = "";


        return tot_rec;

    }



    /****
        getKey: ep_msgmod_par
    **/ 

    public int deleteKey( long       tkmsgmod_par
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " delete \n";
        l_query  += "   from pgmr.ep_msgmod_par  \n";
        if (tkmsgmod_par == 0 && null_tkmsgmod_par){ 
            l_query  += "  where tkmsgmod_par is null \n";
        }else { 
            l_query  += "  where tkmsgmod_par = ?  \n";
        } 

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (tkmsgmod_par == 0 && null_tkmsgmod_par){ 
        }else { 
            pstm.setLong      (ind, tkmsgmod_par); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();
        pstm.close();

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public long       tkmsgmod_par = 0; 






    // --- Impostando a false queste prop. il def. � 0

    public boolean ib_calcola_token = true;
    public boolean ib_ctrl_mindate = false;
    public boolean null_tkmsgmod_par = true;




    public String is_farza_filtro_dipa = "N";



}

