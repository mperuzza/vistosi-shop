package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Ep_msgmod_var extends Atk_sql {

    public Ep_msgmod_var() {

        super();
    }




    /****
        getAll: ep_msgmod_var
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.ep_msgmod_var  \n";
        if (is_farza_filtro_dipa.equals("S")){
            l_query  += "  where cddipa = '"+cddipa+"'  \n";
        }

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: ep_msgmod_var
    **/ 

    public ResultSet getKey( String     cdmsgmod_var
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.ep_msgmod_var  \n";
        if (cdmsgmod_var.equals("")){ 
            l_query  += "  where cdmsgmod_var is null  \n";
        }else { 
            l_query  += "  where cdmsgmod_var = ?  \n";
        } 

        pstm = setQuery( l_query ) ;

        ind = 1;
        if (cdmsgmod_var.equals("")){ 
        }else { 
            pstm.setString    (ind, cdmsgmod_var); ind += 1;
        } 

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: ep_msgmod_var
    **/ 


    public com.ateikon.structure.Rec_ep_msgmod_var  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_ep_msgmod_var lstr = new com.ateikon.structure.Rec_ep_msgmod_var();

        if (rs == null) return lstr;
        if (rs.getObject("cdmsgmod_var")!=null) lstr.cdmsgmod_var = rs.getString    ("cdmsgmod_var"); 
        if (rs.getObject("dsmsgmod_var")!=null) lstr.dsmsgmod_var = rs.getString    ("dsmsgmod_var"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("nrposi")!=null) lstr.nrposi = rs.getLong      ("nrposi"); 

        return lstr;
    }




    /****
        preupdate: ep_msgmod_var

        sovrascrivere per impostare i controlli da effetuare prima dell'execute
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_ep_msgmod_var astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }





    /****
        preupdate: ep_msgmod_var

        sovrascrivere per impostare i controlli da effetuare per sincro

        questo metodo � stato introdotto per la sincro: in fase di export prima  
        di scrivere il file XML viene chiamato
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_ep_msgmod_var astr, ResultSet rs) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }



    /****
        execute: ep_msgmod_var
    **/ 


    public int execute ( com.ateikon.structure.Rec_ep_msgmod_var astr) throws Exception {
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
        l_query  += " select cdmsgmod_var\n";
        l_query  += "   from pgmr.ep_msgmod_var  \n";
        if (astr.cdmsgmod_var.equals("")){ 
            l_query  += "  where cdmsgmod_var is null  \n";
        }else { 
            l_query  += "  where cdmsgmod_var = ?  \n";
        } 


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (astr.cdmsgmod_var.equals("")){ 
        }else { 
            pstm.setString    (ind, astr.cdmsgmod_var); ind += 1;
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
            throw new Exception("Errore Agg. ep_msgmod_var! ");
        }
        */ 

        return tot_rec;
    }




    /****
        executeInsert: ep_msgmod_var
    **/ 


    public int executeInsert( com.ateikon.structure.Rec_ep_msgmod_var astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        if (ib_calcola_token){
            long ll_tk = getNew_token( );

            if (ll_tk <= 0){
                throw new Exception ("Error Keypool (ep_msgmod_var): "+ll_tk );
            }

            this.cdmsgmod_var = of_token(ll_tk);
            astr.cdmsgmod_var = this.cdmsgmod_var;
        }else {
            this.cdmsgmod_var = astr.cdmsgmod_var;
        }



        l_query   = "";
        l_query  += " insert into pgmr.ep_msgmod_var ( \n";
        l_query  += "             cdmsgmod_var   \n";
        l_query  += "           , dsmsgmod_var   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "           , nrposi   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.dsmsgmod_var.equals("")) astr.dsmsgmod_var = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;


        ind = 1;
        pstm.setString    (ind, astr.cdmsgmod_var); ind += 1;
        pstm.setString    (ind, astr.dsmsgmod_var); ind += 1;
        pstm.setString    (ind, astr.cdazie); ind += 1;
        pstm.setString    (ind, astr.cddipa); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtinse); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;
        if (astr.nrposi == 0 && null_nrposi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.nrposi); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        // --- Ripristino le Stringhe NULL

        if (astr.dsmsgmod_var == null) astr.dsmsgmod_var = "";
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

        long ll_tk = f_tabkey.getTabkey( "ep_msgmod_var" );

        return ll_tk;

    }




    /****
        executeUpdate: ep_msgmod_var
    **/ 


    public int executeUpdate( com.ateikon.structure.Rec_ep_msgmod_var astr ) throws Exception { 
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.ep_msgmod_var  \n";
        l_query  += "         set dsmsgmod_var = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "           , nrposi = ?  \n";
        l_query  += "  where cdmsgmod_var = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.dsmsgmod_var.equals("")) astr.dsmsgmod_var = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;


        ind = 1;
        pstm.setString    (ind, astr.dsmsgmod_var); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;
        if (astr.nrposi == 0 && null_nrposi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.nrposi); ind += 1;
        } 

        if (astr.cdmsgmod_var.equals("")){ 
            pstm.setString    (ind, null); ind += 1;
        }else { 
            pstm.setString    (ind, astr.cdmsgmod_var); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.cdmsgmod_var = astr.cdmsgmod_var; 

        // --- Ripristino le Stringhe NULL

        if (astr.dsmsgmod_var == null) astr.dsmsgmod_var = "";
        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil == null) astr.profil = "";


        return tot_rec;

    }



    /****
        getKey: ep_msgmod_var
    **/ 

    public int deleteKey( String     cdmsgmod_var
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " delete \n";
        l_query  += "   from pgmr.ep_msgmod_var  \n";
        if (cdmsgmod_var.equals("")){ 
            l_query  += "  where cdmsgmod_var is null  \n";
        }else { 
            l_query  += "  where cdmsgmod_var = ?  \n";
        } 

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (cdmsgmod_var.equals("")){ 
        }else { 
            pstm.setString    (ind, cdmsgmod_var); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();
        pstm.close();

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public String     cdmsgmod_var = ""; 






    // --- Impostando a false queste prop. il def. � 0

    public boolean ib_calcola_token = true;
    public boolean ib_ctrl_mindate = false;
    public boolean null_nrposi = true;




    public String is_farza_filtro_dipa = "N";



}

