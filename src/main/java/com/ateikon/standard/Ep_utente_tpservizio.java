package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Ep_utente_tpservizio extends Atk_sql {

    public Ep_utente_tpservizio() {

        super();
    }




    /****
        getAll: ep_utente_tpservizio
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.ep_utente_tpservizio  \n";

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: ep_utente_tpservizio
    **/ 

    public ResultSet getKey( long       tkutente_tpservizio
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.ep_utente_tpservizio  \n";
        if (tkutente_tpservizio == 0 && null_tkutente_tpservizio){ 
            l_query  += "  where tkutente_tpservizio is null \n";
        }else { 
            l_query  += "  where tkutente_tpservizio = ?  \n";
        } 

        pstm = setQuery( l_query ) ;

        ind = 1;
        if (tkutente_tpservizio == 0 && null_tkutente_tpservizio){ 
        }else { 
            pstm.setLong      (ind, tkutente_tpservizio); ind += 1;
        } 

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: ep_utente_tpservizio
    **/ 


    public com.ateikon.structure.Rec_ep_utente_tpservizio  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_ep_utente_tpservizio lstr = new com.ateikon.structure.Rec_ep_utente_tpservizio();

        if (rs == null) return lstr;
        if (rs.getObject("tkutente_tpservizio")!=null) lstr.tkutente_tpservizio = rs.getLong      ("tkutente_tpservizio"); 
        if (rs.getObject("cdutente_tpservizio_m")!=null) lstr.cdutente_tpservizio_m = rs.getString    ("cdutente_tpservizio_m"); 
        if (rs.getObject("dsutente_tpservizio")!=null) lstr.dsutente_tpservizio = rs.getString    ("dsutente_tpservizio"); 
        if (rs.getObject("nrposi")!=null) lstr.nrposi = rs.getLong      ("nrposi"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil_inse")!=null) lstr.profil_inse = rs.getString    ("profil_inse"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("dsutente_tpservizio_en")!=null) lstr.dsutente_tpservizio_en = rs.getString    ("dsutente_tpservizio_en"); 
        if (rs.getObject("dsutente_tpservizio_de")!=null) lstr.dsutente_tpservizio_de = rs.getString    ("dsutente_tpservizio_de"); 
        if (rs.getObject("dsutente_tpservizio_fr")!=null) lstr.dsutente_tpservizio_fr = rs.getString    ("dsutente_tpservizio_fr"); 
        if (rs.getObject("dsutente_tpservizio_es")!=null) lstr.dsutente_tpservizio_es = rs.getString    ("dsutente_tpservizio_es"); 

        return lstr;
    }




    /****
        preupdate: ep_utente_tpservizio

        sovrascrivere per impostare i controlli da effetuare prima dell'execute

        questo metodo è stato introdotto per la sincro: in fase di export prima  
        di scrivere il file XML viene chiamato
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_ep_utente_tpservizio astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        return 1;
    }



    /****
        execute: ep_utente_tpservizio
    **/ 


    public int execute ( com.ateikon.structure.Rec_ep_utente_tpservizio astr) throws Exception {
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
        l_query  += " select tkutente_tpservizio\n";
        l_query  += "   from pgmr.ep_utente_tpservizio  \n";
        if (astr.tkutente_tpservizio == 0 && null_tkutente_tpservizio){ 
            l_query  += "  where tkutente_tpservizio is null \n";
        }else { 
            l_query  += "  where tkutente_tpservizio = ?  \n";
        } 


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (astr.tkutente_tpservizio == 0 && null_tkutente_tpservizio){ 
        }else { 
            pstm.setLong      (ind, astr.tkutente_tpservizio); ind += 1;
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
            throw new Exception("Errore Agg. ep_utente_tpservizio! ");
        }
        */ 

        return tot_rec;
    }




    /****
        executeInsert: ep_utente_tpservizio
    **/ 


    public int executeInsert( com.ateikon.structure.Rec_ep_utente_tpservizio astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        if (ib_calcola_token){
            long ll_tk = getNew_token( );

            if (ll_tk <= 0){
                throw new Exception ("Error Keypool (ep_utente_tpservizio): "+ll_tk );
            }

            this.tkutente_tpservizio = ll_tk;
            astr.tkutente_tpservizio = this.tkutente_tpservizio;
        }else {
            this.tkutente_tpservizio = astr.tkutente_tpservizio;
        }



        l_query   = "";
        l_query  += " insert into pgmr.ep_utente_tpservizio ( \n";
        l_query  += "             tkutente_tpservizio   \n";
        l_query  += "           , cdutente_tpservizio_m   \n";
        l_query  += "           , dsutente_tpservizio   \n";
        l_query  += "           , nrposi   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil_inse   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "           , dsutente_tpservizio_en   \n";
        l_query  += "           , dsutente_tpservizio_de   \n";
        l_query  += "           , dsutente_tpservizio_fr   \n";
        l_query  += "           , dsutente_tpservizio_es   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.cdutente_tpservizio_m.equals("")) astr.cdutente_tpservizio_m = null;
        if (astr.dsutente_tpservizio.equals("")) astr.dsutente_tpservizio = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil_inse.equals("")) astr.profil_inse = null;
        if (astr.profil.equals("")) astr.profil = null;
        if (astr.dsutente_tpservizio_en.equals("")) astr.dsutente_tpservizio_en = null;
        if (astr.dsutente_tpservizio_de.equals("")) astr.dsutente_tpservizio_de = null;
        if (astr.dsutente_tpservizio_fr.equals("")) astr.dsutente_tpservizio_fr = null;
        if (astr.dsutente_tpservizio_es.equals("")) astr.dsutente_tpservizio_es = null;


        ind = 1;
        if (astr.tkutente_tpservizio == 0 && null_tkutente_tpservizio){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkutente_tpservizio); ind += 1;
        } 
        pstm.setString    (ind, astr.cdutente_tpservizio_m); ind += 1;
        pstm.setString    (ind, astr.dsutente_tpservizio); ind += 1;
        if (astr.nrposi == 0 && null_nrposi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.nrposi); ind += 1;
        } 
        pstm.setString    (ind, astr.cdazie); ind += 1;
        pstm.setString    (ind, astr.cddipa); ind += 1;
        pstm.setString    (ind, astr.profil_inse); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtinse); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;
        pstm.setString    (ind, astr.dsutente_tpservizio_en); ind += 1;
        pstm.setString    (ind, astr.dsutente_tpservizio_de); ind += 1;
        pstm.setString    (ind, astr.dsutente_tpservizio_fr); ind += 1;
        pstm.setString    (ind, astr.dsutente_tpservizio_es); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        // --- Ripristino le Stringhe NULL

        if (astr.cdutente_tpservizio_m == null) astr.cdutente_tpservizio_m = "";
        if (astr.dsutente_tpservizio == null) astr.dsutente_tpservizio = "";
        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil_inse == null) astr.profil_inse = "";
        if (astr.profil == null) astr.profil = "";
        if (astr.dsutente_tpservizio_en == null) astr.dsutente_tpservizio_en = "";
        if (astr.dsutente_tpservizio_de == null) astr.dsutente_tpservizio_de = "";
        if (astr.dsutente_tpservizio_fr == null) astr.dsutente_tpservizio_fr = "";
        if (astr.dsutente_tpservizio_es == null) astr.dsutente_tpservizio_es = "";


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

        long ll_tk = f_tabkey.getTabkey( "ep_utente_tpservizio" );

        return ll_tk;

    }




    /****
        executeUpdate: ep_utente_tpservizio
    **/ 


    public int executeUpdate( com.ateikon.structure.Rec_ep_utente_tpservizio astr ) throws Exception { 
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.ep_utente_tpservizio  \n";
        l_query  += "         set cdutente_tpservizio_m = ?  \n";
        l_query  += "           , dsutente_tpservizio = ?  \n";
        l_query  += "           , nrposi = ?  \n";
        l_query  += "           , profil_inse = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "           , dsutente_tpservizio_en = ?  \n";
        l_query  += "           , dsutente_tpservizio_de = ?  \n";
        l_query  += "           , dsutente_tpservizio_fr = ?  \n";
        l_query  += "           , dsutente_tpservizio_es = ?  \n";
        l_query  += "  where tkutente_tpservizio = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.cdutente_tpservizio_m.equals("")) astr.cdutente_tpservizio_m = null;
        if (astr.dsutente_tpservizio.equals("")) astr.dsutente_tpservizio = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil_inse.equals("")) astr.profil_inse = null;
        if (astr.profil.equals("")) astr.profil = null;
        if (astr.dsutente_tpservizio_en.equals("")) astr.dsutente_tpservizio_en = null;
        if (astr.dsutente_tpservizio_de.equals("")) astr.dsutente_tpservizio_de = null;
        if (astr.dsutente_tpservizio_fr.equals("")) astr.dsutente_tpservizio_fr = null;
        if (astr.dsutente_tpservizio_es.equals("")) astr.dsutente_tpservizio_es = null;


        ind = 1;
        pstm.setString    (ind, astr.cdutente_tpservizio_m); ind += 1;
        pstm.setString    (ind, astr.dsutente_tpservizio); ind += 1;
        if (astr.nrposi == 0 && null_nrposi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.nrposi); ind += 1;
        } 
        pstm.setString    (ind, astr.profil_inse); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;
        pstm.setString    (ind, astr.dsutente_tpservizio_en); ind += 1;
        pstm.setString    (ind, astr.dsutente_tpservizio_de); ind += 1;
        pstm.setString    (ind, astr.dsutente_tpservizio_fr); ind += 1;
        pstm.setString    (ind, astr.dsutente_tpservizio_es); ind += 1;

        if (astr.tkutente_tpservizio == 0 && null_tkutente_tpservizio){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkutente_tpservizio); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.tkutente_tpservizio = astr.tkutente_tpservizio; 

        // --- Ripristino le Stringhe NULL

        if (astr.cdutente_tpservizio_m == null) astr.cdutente_tpservizio_m = "";
        if (astr.dsutente_tpservizio == null) astr.dsutente_tpservizio = "";
        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil_inse == null) astr.profil_inse = "";
        if (astr.profil == null) astr.profil = "";
        if (astr.dsutente_tpservizio_en == null) astr.dsutente_tpservizio_en = "";
        if (astr.dsutente_tpservizio_de == null) astr.dsutente_tpservizio_de = "";
        if (astr.dsutente_tpservizio_fr == null) astr.dsutente_tpservizio_fr = "";
        if (astr.dsutente_tpservizio_es == null) astr.dsutente_tpservizio_es = "";


        return tot_rec;

    }



    /****
        getKey: ep_utente_tpservizio
    **/ 

    public int deleteKey( long       tkutente_tpservizio
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " delete \n";
        l_query  += "   from pgmr.ep_utente_tpservizio  \n";
        if (tkutente_tpservizio == 0 && null_tkutente_tpservizio){ 
            l_query  += "  where tkutente_tpservizio is null \n";
        }else { 
            l_query  += "  where tkutente_tpservizio = ?  \n";
        } 

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (tkutente_tpservizio == 0 && null_tkutente_tpservizio){ 
        }else { 
            pstm.setLong      (ind, tkutente_tpservizio); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();
        pstm.close();

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public long       tkutente_tpservizio = 0; 






    // --- Impostando a false queste prop. il def. è 0

    public boolean ib_calcola_token = true;
    public boolean ib_ctrl_mindate = false;
    public boolean null_tkutente_tpservizio = true;
    public boolean null_nrposi = true;







}

