package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Ep_msgmod extends Atk_sql {

    public Ep_msgmod() {

        super();
    }




    /****
        getAll: ep_msgmod
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.ep_msgmod  \n";
        if (is_farza_filtro_dipa.equals("S")){
            l_query  += "  where cddipa = '"+cddipa+"'  \n";
        }

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: ep_msgmod
    **/ 

    public ResultSet getKey( long       tkmsgmod
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.ep_msgmod  \n";
        if (tkmsgmod == 0 && null_tkmsgmod){ 
            l_query  += "  where tkmsgmod is null \n";
        }else { 
            l_query  += "  where tkmsgmod = ?  \n";
        } 

        pstm = setQuery( l_query ) ;

        ind = 1;
        if (tkmsgmod == 0 && null_tkmsgmod){ 
        }else { 
            pstm.setLong      (ind, tkmsgmod); ind += 1;
        } 

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: ep_msgmod
    **/ 


    public com.ateikon.structure.Rec_ep_msgmod  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_ep_msgmod lstr = new com.ateikon.structure.Rec_ep_msgmod();

        if (rs == null) return lstr;
        if (rs.getObject("tkmsgmod")!=null) lstr.tkmsgmod = rs.getLong      ("tkmsgmod"); 
        if (rs.getObject("cdmsgmod_appl")!=null) lstr.cdmsgmod_appl = rs.getString    ("cdmsgmod_appl"); 
        if (rs.getObject("cdmsgmod_var")!=null) lstr.cdmsgmod_var = rs.getString    ("cdmsgmod_var"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 

        return lstr;
    }




    /****
        preupdate: ep_msgmod

        sovrascrivere per impostare i controlli da effetuare prima dell'execute
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_ep_msgmod astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }





    /****
        preupdate: ep_msgmod

        sovrascrivere per impostare i controlli da effetuare per sincro

        questo metodo � stato introdotto per la sincro: in fase di export prima  
        di scrivere il file XML viene chiamato
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_ep_msgmod astr, ResultSet rs) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }



    /****
        execute: ep_msgmod
    **/ 


    public int execute ( com.ateikon.structure.Rec_ep_msgmod astr) throws Exception {
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
        l_query  += " select tkmsgmod\n";
        l_query  += "   from pgmr.ep_msgmod  \n";
        if (astr.tkmsgmod == 0 && null_tkmsgmod){ 
            l_query  += "  where tkmsgmod is null \n";
        }else { 
            l_query  += "  where tkmsgmod = ?  \n";
        } 


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (astr.tkmsgmod == 0 && null_tkmsgmod){ 
        }else { 
            pstm.setLong      (ind, astr.tkmsgmod); ind += 1;
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
            throw new Exception("Errore Agg. ep_msgmod! ");
        }
        */ 

        return tot_rec;
    }




    /****
        executeInsert: ep_msgmod
    **/ 


    public int executeInsert( com.ateikon.structure.Rec_ep_msgmod astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        if (ib_calcola_token){
            long ll_tk = getNew_token( );

            if (ll_tk <= 0){
                throw new Exception ("Error Keypool (ep_msgmod): "+ll_tk );
            }

            this.tkmsgmod = ll_tk;
            astr.tkmsgmod = this.tkmsgmod;
        }else {
            this.tkmsgmod = astr.tkmsgmod;
        }



        l_query   = "";
        l_query  += " insert into pgmr.ep_msgmod ( \n";
        l_query  += "             tkmsgmod   \n";
        l_query  += "           , cdmsgmod_appl   \n";
        l_query  += "           , cdmsgmod_var   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.cdmsgmod_appl.equals("")) astr.cdmsgmod_appl = null;
        if (astr.cdmsgmod_var.equals("")) astr.cdmsgmod_var = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;


        ind = 1;
        if (astr.tkmsgmod == 0 && null_tkmsgmod){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkmsgmod); ind += 1;
        } 
        pstm.setString    (ind, astr.cdmsgmod_appl); ind += 1;
        pstm.setString    (ind, astr.cdmsgmod_var); ind += 1;
        pstm.setString    (ind, astr.cdazie); ind += 1;
        pstm.setString    (ind, astr.cddipa); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtinse); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        // --- Ripristino le Stringhe NULL

        if (astr.cdmsgmod_appl == null) astr.cdmsgmod_appl = "";
        if (astr.cdmsgmod_var == null) astr.cdmsgmod_var = "";
        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil == null) astr.profil = "";


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

        long ll_tk = f_tabkey.getTabkey( "ep_msgmod" );

        return ll_tk;

    }




    /****
        executeUpdate: ep_msgmod
    **/ 


    public int executeUpdate( com.ateikon.structure.Rec_ep_msgmod astr ) throws Exception { 
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.ep_msgmod  \n";
        l_query  += "         set cdmsgmod_appl = ?  \n";
        l_query  += "           , cdmsgmod_var = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "  where tkmsgmod = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.cdmsgmod_appl.equals("")) astr.cdmsgmod_appl = null;
        if (astr.cdmsgmod_var.equals("")) astr.cdmsgmod_var = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;


        ind = 1;
        pstm.setString    (ind, astr.cdmsgmod_appl); ind += 1;
        pstm.setString    (ind, astr.cdmsgmod_var); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;

        if (astr.tkmsgmod == 0 && null_tkmsgmod){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkmsgmod); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.tkmsgmod = astr.tkmsgmod; 

        // --- Ripristino le Stringhe NULL

        if (astr.cdmsgmod_appl == null) astr.cdmsgmod_appl = "";
        if (astr.cdmsgmod_var == null) astr.cdmsgmod_var = "";
        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil == null) astr.profil = "";


        return tot_rec;

    }



    /****
        getKey: ep_msgmod
    **/ 

    public int deleteKey( long       tkmsgmod
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " delete \n";
        l_query  += "   from pgmr.ep_msgmod  \n";
        if (tkmsgmod == 0 && null_tkmsgmod){ 
            l_query  += "  where tkmsgmod is null \n";
        }else { 
            l_query  += "  where tkmsgmod = ?  \n";
        } 

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (tkmsgmod == 0 && null_tkmsgmod){ 
        }else { 
            pstm.setLong      (ind, tkmsgmod); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();
        pstm.close();

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public long       tkmsgmod = 0; 






    // --- Impostando a false queste prop. il def. � 0

    public boolean ib_calcola_token = true;
    public boolean ib_ctrl_mindate = false;
    public boolean null_tkmsgmod = true;




    public String is_farza_filtro_dipa = "N";



}

