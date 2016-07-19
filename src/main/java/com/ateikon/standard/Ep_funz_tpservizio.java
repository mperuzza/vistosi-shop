package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Ep_funz_tpservizio extends Atk_sql {

    public Ep_funz_tpservizio() {

        super();
    }




    /****
        getAll: ep_funz_tpservizio
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.ep_funz_tpservizio  \n";

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: ep_funz_tpservizio
    **/ 

    public ResultSet getKey( long       tkfunzione
                           , long       tkutente_tpservizio
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.ep_funz_tpservizio  \n";
        if (tkfunzione == 0 && null_tkfunzione){ 
            l_query  += "  where tkfunzione is null \n";
        }else { 
            l_query  += "  where tkfunzione = ?  \n";
        } 
        if (tkutente_tpservizio == 0 && null_tkutente_tpservizio){ 
            l_query  += "  and tkutente_tpservizio is null \n";
        }else { 
            l_query  += "  and tkutente_tpservizio = ?  \n";
        } 

        pstm = setQuery( l_query ) ;

        ind = 1;
        if (tkfunzione == 0 && null_tkfunzione){ 
        }else { 
            pstm.setLong      (ind, tkfunzione); ind += 1;
        } 
        if (tkutente_tpservizio == 0 && null_tkutente_tpservizio){ 
        }else { 
            pstm.setLong      (ind, tkutente_tpservizio); ind += 1;
        } 

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: ep_funz_tpservizio
    **/ 


    public com.ateikon.structure.Rec_ep_funz_tpservizio  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_ep_funz_tpservizio lstr = new com.ateikon.structure.Rec_ep_funz_tpservizio();

        if (rs == null) return lstr;
        if (rs.getObject("tkfunzione")!=null) lstr.tkfunzione = rs.getLong      ("tkfunzione"); 
        if (rs.getObject("tkutente_tpservizio")!=null) lstr.tkutente_tpservizio = rs.getLong      ("tkutente_tpservizio"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil_inse")!=null) lstr.profil_inse = rs.getString    ("profil_inse"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 

        return lstr;
    }




    /****
        preupdate: ep_funz_tpservizio

        sovrascrivere per impostare i controlli da effetuare prima dell'execute

        questo metodo è stato introdotto per la sincro: in fase di export prima  
        di scrivere il file XML viene chiamato
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_ep_funz_tpservizio astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        return 1;
    }



    /****
        execute: ep_funz_tpservizio
    **/ 


    public int execute ( com.ateikon.structure.Rec_ep_funz_tpservizio astr) throws Exception {
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
        l_query  += " select tkfunzione\n";
        l_query  += "      , tkutente_tpservizio\n";
        l_query  += "   from pgmr.ep_funz_tpservizio  \n";
        if (astr.tkfunzione == 0 && null_tkfunzione){ 
            l_query  += "  where tkfunzione is null \n";
        }else { 
            l_query  += "  where tkfunzione = ?  \n";
        } 
        if (astr.tkutente_tpservizio == 0 && null_tkutente_tpservizio){ 
            l_query  += "  and tkutente_tpservizio is null \n";
        }else { 
            l_query  += "  and tkutente_tpservizio = ?  \n";
        } 


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (astr.tkfunzione == 0 && null_tkfunzione){ 
        }else { 
            pstm.setLong      (ind, astr.tkfunzione); ind += 1;
        } 
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
            throw new Exception("Errore Agg. ep_funz_tpservizio! ");
        }
        */ 

        return tot_rec;
    }




    /****
        executeInsert: ep_funz_tpservizio
    **/ 


    public int executeInsert( com.ateikon.structure.Rec_ep_funz_tpservizio astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;





        l_query   = "";
        l_query  += " insert into pgmr.ep_funz_tpservizio ( \n";
        l_query  += "             tkfunzione   \n";
        l_query  += "           , tkutente_tpservizio   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil_inse   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil_inse.equals("")) astr.profil_inse = null;
        if (astr.profil.equals("")) astr.profil = null;


        ind = 1;
        if (astr.tkfunzione == 0 && null_tkfunzione){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkfunzione); ind += 1;
        } 
        if (astr.tkutente_tpservizio == 0 && null_tkutente_tpservizio){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkutente_tpservizio); ind += 1;
        } 
        pstm.setString    (ind, astr.cdazie); ind += 1;
        pstm.setString    (ind, astr.cddipa); ind += 1;
        pstm.setString    (ind, astr.profil_inse); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtinse); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        // --- Ripristino le Stringhe NULL

        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil_inse == null) astr.profil_inse = "";
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

        long ll_tk = f_tabkey.getTabkey( "ep_funz_tpservizio" );

        return ll_tk;

    }




    /****
        executeUpdate: ep_funz_tpservizio
    **/ 


    public int executeUpdate( com.ateikon.structure.Rec_ep_funz_tpservizio astr ) throws Exception { 
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.ep_funz_tpservizio  \n";
        l_query  += "         set profil_inse = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "  where tkfunzione = ? \n";
        l_query  += "    and tkutente_tpservizio = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil_inse.equals("")) astr.profil_inse = null;
        if (astr.profil.equals("")) astr.profil = null;


        ind = 1;
        pstm.setString    (ind, astr.profil_inse); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;

        if (astr.tkfunzione == 0 && null_tkfunzione){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkfunzione); ind += 1;
        } 
        if (astr.tkutente_tpservizio == 0 && null_tkutente_tpservizio){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkutente_tpservizio); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.tkfunzione = astr.tkfunzione; 
        this.tkutente_tpservizio = astr.tkutente_tpservizio; 

        // --- Ripristino le Stringhe NULL

        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil_inse == null) astr.profil_inse = "";
        if (astr.profil == null) astr.profil = "";


        return tot_rec;

    }



    /****
        getKey: ep_funz_tpservizio
    **/ 

    public int deleteKey( long       tkfunzione
                           , long       tkutente_tpservizio
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " delete \n";
        l_query  += "   from pgmr.ep_funz_tpservizio  \n";
        if (tkfunzione == 0 && null_tkfunzione){ 
            l_query  += "  where tkfunzione is null \n";
        }else { 
            l_query  += "  where tkfunzione = ?  \n";
        } 
        if (tkutente_tpservizio == 0 && null_tkutente_tpservizio){ 
            l_query  += "  and tkutente_tpservizio is null \n";
        }else { 
            l_query  += "  and tkutente_tpservizio = ?  \n";
        } 

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (tkfunzione == 0 && null_tkfunzione){ 
        }else { 
            pstm.setLong      (ind, tkfunzione); ind += 1;
        } 
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


    public long       tkfunzione = 0; 
    public long       tkutente_tpservizio = 0; 






    // --- Impostando a false queste prop. il def. è 0

    public boolean ib_calcola_token = true;
    public boolean ib_ctrl_mindate = false;
    public boolean null_tkfunzione = true;
    public boolean null_tkutente_tpservizio = true;







}

