package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Atk_msgcont extends Atk_sql {

    public Atk_msgcont() {

        super();
    }




    /****
        getAll: atk_msgcont
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.atk_msgcont  \n";

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: atk_msgcont
    **/ 

    public ResultSet getKey( long       tkmsg
                           , long       nrposi
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.atk_msgcont  \n";
        if (tkmsg == 0 && null_tkmsg){ 
            l_query  += "  where tkmsg is null \n";
        }else { 
            l_query  += "  where tkmsg = ?  \n";
        } 
        if (nrposi == 0 && null_nrposi){ 
            l_query  += "  and nrposi is null \n";
        }else { 
            l_query  += "  and nrposi = ?  \n";
        } 

        pstm = setQuery( l_query ) ;

        ind = 1;
        if (tkmsg == 0 && null_tkmsg){ 
        }else { 
            pstm.setLong      (ind, tkmsg); ind += 1;
        } 
        if (nrposi == 0 && null_nrposi){ 
        }else { 
            pstm.setLong      (ind, nrposi); ind += 1;
        } 

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: atk_msgcont
    **/ 


    public com.ateikon.structure.Rec_atk_msgcont  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_atk_msgcont lstr = new com.ateikon.structure.Rec_atk_msgcont();

        if (rs == null) return lstr;
        if (rs.getObject("tkmsg")!=null) lstr.tkmsg = rs.getLong      ("tkmsg"); 
        if (rs.getObject("nrposi")!=null) lstr.nrposi = rs.getLong      ("nrposi"); 
        if (rs.getObject("contenuto")!=null) lstr.contenuto = rs.getString    ("contenuto"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 

        return lstr;
    }




    /****
        preupdate: atk_msgcont

        sovrascrivere per impostare i controlli da effetuare prima dell'execute
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_atk_msgcont astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }





    /****
        preupdate: atk_msgcont

        sovrascrivere per impostare i controlli da effetuare per sincro

        questo metodo � stato introdotto per la sincro: in fase di export prima  
        di scrivere il file XML viene chiamato
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_atk_msgcont astr, ResultSet rs) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }



    /****
        execute: atk_msgcont
    **/ 


    public int execute ( com.ateikon.structure.Rec_atk_msgcont astr) throws Exception {
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
        l_query  += " select tkmsg\n";
        l_query  += "      , nrposi\n";
        l_query  += "   from pgmr.atk_msgcont  \n";
        if (astr.tkmsg == 0 && null_tkmsg){ 
            l_query  += "  where tkmsg is null \n";
        }else { 
            l_query  += "  where tkmsg = ?  \n";
        } 
        if (astr.nrposi == 0 && null_nrposi){ 
            l_query  += "  and nrposi is null \n";
        }else { 
            l_query  += "  and nrposi = ?  \n";
        } 


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (astr.tkmsg == 0 && null_tkmsg){ 
        }else { 
            pstm.setLong      (ind, astr.tkmsg); ind += 1;
        } 
        if (astr.nrposi == 0 && null_nrposi){ 
        }else { 
            pstm.setLong      (ind, astr.nrposi); ind += 1;
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
            throw new Exception("Errore Agg. atk_msgcont! ");
        }
        */ 

        return tot_rec;
    }




    /****
        executeInsert: atk_msgcont
    **/ 


    public int executeInsert( com.ateikon.structure.Rec_atk_msgcont astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;





        l_query   = "";
        l_query  += " insert into pgmr.atk_msgcont ( \n";
        l_query  += "             tkmsg   \n";
        l_query  += "           , nrposi   \n";
        l_query  += "           , contenuto   \n";
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


        if (astr.contenuto.equals("")) astr.contenuto = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;


        ind = 1;
        if (astr.tkmsg == 0 && null_tkmsg){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkmsg); ind += 1;
        } 
        if (astr.nrposi == 0 && null_nrposi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.nrposi); ind += 1;
        } 
        pstm.setString    (ind, astr.contenuto); ind += 1;
        pstm.setString    (ind, astr.cdazie); ind += 1;
        pstm.setString    (ind, astr.cddipa); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtinse); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        // --- Ripristino le Stringhe NULL

        if (astr.contenuto == null) astr.contenuto = "";
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

        long ll_tk = f_tabkey.getTabkey( "atk_msgcont" );

        return ll_tk;

    }




    /****
        executeUpdate: atk_msgcont
    **/ 


    public int executeUpdate( com.ateikon.structure.Rec_atk_msgcont astr ) throws Exception { 
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.atk_msgcont  \n";
        l_query  += "         set contenuto = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "  where tkmsg = ? \n";
        l_query  += "    and nrposi = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.contenuto.equals("")) astr.contenuto = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;


        ind = 1;
        pstm.setString    (ind, astr.contenuto); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;

        if (astr.tkmsg == 0 && null_tkmsg){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkmsg); ind += 1;
        } 
        if (astr.nrposi == 0 && null_nrposi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.nrposi); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.tkmsg = astr.tkmsg; 
        this.nrposi = astr.nrposi; 

        // --- Ripristino le Stringhe NULL

        if (astr.contenuto == null) astr.contenuto = "";
        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil == null) astr.profil = "";


        return tot_rec;

    }



    /****
        getKey: atk_msgcont
    **/ 

    public int deleteKey( long       tkmsg
                           , long       nrposi
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " delete \n";
        l_query  += "   from pgmr.atk_msgcont  \n";
        if (tkmsg == 0 && null_tkmsg){ 
            l_query  += "  where tkmsg is null \n";
        }else { 
            l_query  += "  where tkmsg = ?  \n";
        } 
        if (nrposi == 0 && null_nrposi){ 
            l_query  += "  and nrposi is null \n";
        }else { 
            l_query  += "  and nrposi = ?  \n";
        } 

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (tkmsg == 0 && null_tkmsg){ 
        }else { 
            pstm.setLong      (ind, tkmsg); ind += 1;
        } 
        if (nrposi == 0 && null_nrposi){ 
        }else { 
            pstm.setLong      (ind, nrposi); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();
        pstm.close();

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public long       tkmsg = 0; 
    public long       nrposi = 0; 






    // --- Impostando a false queste prop. il def. � 0

    public boolean ib_calcola_token = true;
    public boolean ib_ctrl_mindate = false;
    public boolean null_tkmsg = true;
    public boolean null_nrposi = true;





}

