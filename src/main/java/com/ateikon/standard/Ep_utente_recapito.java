package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Ep_utente_recapito extends Atk_sql {

    public Ep_utente_recapito() {

        super();
    }




    /****
        getAll: ep_utente_recapito
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.ep_utente_recapito  \n";

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: ep_utente_recapito
    **/ 

    public ResultSet getKey( long       tkutente_rec
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.ep_utente_recapito  \n";
        if (tkutente_rec == 0 && null_tkutente_rec){ 
            l_query  += "  where tkutente_rec is null \n";
        }else { 
            l_query  += "  where tkutente_rec = ?  \n";
        } 

        pstm = setQuery( l_query ) ;

        ind = 1;
        if (tkutente_rec == 0 && null_tkutente_rec){ 
        }else { 
            pstm.setLong      (ind, tkutente_rec); ind += 1;
        } 

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: ep_utente_recapito
    **/ 


    public com.ateikon.structure.Rec_ep_utente_recapito  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_ep_utente_recapito lstr = new com.ateikon.structure.Rec_ep_utente_recapito();

        if (rs == null) return lstr;
        if (rs.getObject("tkutente_rec")!=null) lstr.tkutente_rec = rs.getLong      ("tkutente_rec"); 
        if (rs.getObject("tkutente")!=null) lstr.tkutente = rs.getLong      ("tkutente"); 
        if (rs.getObject("tkutente_rec_tipo")!=null) lstr.tkutente_rec_tipo = rs.getLong      ("tkutente_rec_tipo"); 
        if (rs.getObject("tkutente_tpservizio")!=null) lstr.tkutente_tpservizio = rs.getLong      ("tkutente_tpservizio"); 
        if (rs.getObject("fgabil")!=null) lstr.fgabil = rs.getString    ("fgabil"); 
        if (rs.getObject("recapito")!=null) lstr.recapito = rs.getString    ("recapito"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil_inse")!=null) lstr.profil_inse = rs.getString    ("profil_inse"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 

        return lstr;
    }




    /****
        preupdate: ep_utente_recapito

        sovrascrivere per impostare i controlli da effetuare prima dell'execute

        questo metodo è stato introdotto per la sincro: in fase di export prima  
        di scrivere il file XML viene chiamato
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_ep_utente_recapito astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        return 1;
    }



    /****
        execute: ep_utente_recapito
    **/ 


    public int execute ( com.ateikon.structure.Rec_ep_utente_recapito astr) throws Exception {
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
        l_query  += " select tkutente_rec\n";
        l_query  += "   from pgmr.ep_utente_recapito  \n";
        if (astr.tkutente_rec == 0 && null_tkutente_rec){ 
            l_query  += "  where tkutente_rec is null \n";
        }else { 
            l_query  += "  where tkutente_rec = ?  \n";
        } 


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (astr.tkutente_rec == 0 && null_tkutente_rec){ 
        }else { 
            pstm.setLong      (ind, astr.tkutente_rec); ind += 1;
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
            throw new Exception("Errore Agg. ep_utente_recapito! ");
        }
        */ 

        return tot_rec;
    }




    /****
        executeInsert: ep_utente_recapito
    **/ 


    public int executeInsert( com.ateikon.structure.Rec_ep_utente_recapito astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        if (ib_calcola_token){
            long ll_tk = getNew_token( );

            if (ll_tk <= 0){
                throw new Exception ("Error Keypool (ep_utente_recapito): "+ll_tk );
            }

            this.tkutente_rec = ll_tk;
            astr.tkutente_rec = this.tkutente_rec;
        }else {
            this.tkutente_rec = astr.tkutente_rec;
        }



        l_query   = "";
        l_query  += " insert into pgmr.ep_utente_recapito ( \n";
        l_query  += "             tkutente_rec   \n";
        l_query  += "           , tkutente   \n";
        l_query  += "           , tkutente_rec_tipo   \n";
        l_query  += "           , tkutente_tpservizio   \n";
        l_query  += "           , fgabil   \n";
        l_query  += "           , recapito   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil_inse   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.fgabil.equals("")) astr.fgabil = null;
        if (astr.recapito.equals("")) astr.recapito = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil_inse.equals("")) astr.profil_inse = null;
        if (astr.profil.equals("")) astr.profil = null;


        ind = 1;
        if (astr.tkutente_rec == 0 && null_tkutente_rec){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkutente_rec); ind += 1;
        } 
        if (astr.tkutente == 0 && null_tkutente){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkutente); ind += 1;
        } 
        if (astr.tkutente_rec_tipo == 0 && null_tkutente_rec_tipo){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkutente_rec_tipo); ind += 1;
        } 
        if (astr.tkutente_tpservizio == 0 && null_tkutente_tpservizio){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkutente_tpservizio); ind += 1;
        } 
        pstm.setString    (ind, astr.fgabil); ind += 1;
        pstm.setString    (ind, astr.recapito); ind += 1;
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

        if (astr.fgabil == null) astr.fgabil = "";
        if (astr.recapito == null) astr.recapito = "";
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

        long ll_tk = f_tabkey.getTabkey( "ep_utente_recapito" );

        return ll_tk;

    }




    /****
        executeUpdate: ep_utente_recapito
    **/ 


    public int executeUpdate( com.ateikon.structure.Rec_ep_utente_recapito astr ) throws Exception { 
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.ep_utente_recapito  \n";
        l_query  += "         set tkutente = ?  \n";
        l_query  += "           , tkutente_rec_tipo = ?  \n";
        l_query  += "           , tkutente_tpservizio = ?  \n";
        l_query  += "           , fgabil = ?  \n";
        l_query  += "           , recapito = ?  \n";
        l_query  += "           , profil_inse = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "  where tkutente_rec = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.fgabil.equals("")) astr.fgabil = null;
        if (astr.recapito.equals("")) astr.recapito = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil_inse.equals("")) astr.profil_inse = null;
        if (astr.profil.equals("")) astr.profil = null;


        ind = 1;
        if (astr.tkutente == 0 && null_tkutente){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkutente); ind += 1;
        } 
        if (astr.tkutente_rec_tipo == 0 && null_tkutente_rec_tipo){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkutente_rec_tipo); ind += 1;
        } 
        if (astr.tkutente_tpservizio == 0 && null_tkutente_tpservizio){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkutente_tpservizio); ind += 1;
        } 
        pstm.setString    (ind, astr.fgabil); ind += 1;
        pstm.setString    (ind, astr.recapito); ind += 1;
        pstm.setString    (ind, astr.profil_inse); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;

        if (astr.tkutente_rec == 0 && null_tkutente_rec){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkutente_rec); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.tkutente_rec = astr.tkutente_rec; 

        // --- Ripristino le Stringhe NULL

        if (astr.fgabil == null) astr.fgabil = "";
        if (astr.recapito == null) astr.recapito = "";
        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil_inse == null) astr.profil_inse = "";
        if (astr.profil == null) astr.profil = "";


        return tot_rec;

    }



    /****
        getKey: ep_utente_recapito
    **/ 

    public int deleteKey( long       tkutente_rec
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " delete \n";
        l_query  += "   from pgmr.ep_utente_recapito  \n";
        if (tkutente_rec == 0 && null_tkutente_rec){ 
            l_query  += "  where tkutente_rec is null \n";
        }else { 
            l_query  += "  where tkutente_rec = ?  \n";
        } 

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (tkutente_rec == 0 && null_tkutente_rec){ 
        }else { 
            pstm.setLong      (ind, tkutente_rec); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();
        pstm.close();

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public long       tkutente_rec = 0; 






    // --- Impostando a false queste prop. il def. è 0

    public boolean ib_calcola_token = true;
    public boolean ib_ctrl_mindate = false;
    public boolean null_tkutente_rec = true;
    public boolean null_tkutente = true;
    public boolean null_tkutente_rec_tipo = true;
    public boolean null_tkutente_tpservizio = true;







}

