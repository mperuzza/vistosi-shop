package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Ep_utente_recapito_tipo extends Atk_sql {

    public Ep_utente_recapito_tipo() {

        super();
    }




    /****
        getAll: ep_utente_recapito_tipo
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.ep_utente_recapito_tipo  \n";

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: ep_utente_recapito_tipo
    **/ 

    public ResultSet getKey( long       tkutente_rec_tipo
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.ep_utente_recapito_tipo  \n";
        if (tkutente_rec_tipo == 0 && null_tkutente_rec_tipo){ 
            l_query  += "  where tkutente_rec_tipo is null \n";
        }else { 
            l_query  += "  where tkutente_rec_tipo = ?  \n";
        } 

        pstm = setQuery( l_query ) ;

        ind = 1;
        if (tkutente_rec_tipo == 0 && null_tkutente_rec_tipo){ 
        }else { 
            pstm.setLong      (ind, tkutente_rec_tipo); ind += 1;
        } 

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: ep_utente_recapito_tipo
    **/ 


    public com.ateikon.structure.Rec_ep_utente_recapito_tipo  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_ep_utente_recapito_tipo lstr = new com.ateikon.structure.Rec_ep_utente_recapito_tipo();

        if (rs == null) return lstr;
        if (rs.getObject("tkutente_rec_tipo")!=null) lstr.tkutente_rec_tipo = rs.getLong      ("tkutente_rec_tipo"); 
        if (rs.getObject("cdutente_rec_tipo_m")!=null) lstr.cdutente_rec_tipo_m = rs.getString    ("cdutente_rec_tipo_m"); 
        if (rs.getObject("dsutente_rec_tipo")!=null) lstr.dsutente_rec_tipo = rs.getString    ("dsutente_rec_tipo"); 
        if (rs.getObject("nrposi")!=null) lstr.nrposi = rs.getLong      ("nrposi"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil_inse")!=null) lstr.profil_inse = rs.getString    ("profil_inse"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 

        return lstr;
    }




    /****
        preupdate: ep_utente_recapito_tipo

        sovrascrivere per impostare i controlli da effetuare prima dell'execute

        questo metodo è stato introdotto per la sincro: in fase di export prima  
        di scrivere il file XML viene chiamato
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_ep_utente_recapito_tipo astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        return 1;
    }



    /****
        execute: ep_utente_recapito_tipo
    **/ 


    public int execute ( com.ateikon.structure.Rec_ep_utente_recapito_tipo astr) throws Exception {
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
        l_query  += " select tkutente_rec_tipo\n";
        l_query  += "   from pgmr.ep_utente_recapito_tipo  \n";
        if (astr.tkutente_rec_tipo == 0 && null_tkutente_rec_tipo){ 
            l_query  += "  where tkutente_rec_tipo is null \n";
        }else { 
            l_query  += "  where tkutente_rec_tipo = ?  \n";
        } 


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (astr.tkutente_rec_tipo == 0 && null_tkutente_rec_tipo){ 
        }else { 
            pstm.setLong      (ind, astr.tkutente_rec_tipo); ind += 1;
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
            throw new Exception("Errore Agg. ep_utente_recapito_tipo! ");
        }
        */ 

        return tot_rec;
    }




    /****
        executeInsert: ep_utente_recapito_tipo
    **/ 


    public int executeInsert( com.ateikon.structure.Rec_ep_utente_recapito_tipo astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        if (ib_calcola_token){
            long ll_tk = getNew_token( );

            if (ll_tk <= 0){
                throw new Exception ("Error Keypool (ep_utente_recapito_tipo): "+ll_tk );
            }

            this.tkutente_rec_tipo = ll_tk;
            astr.tkutente_rec_tipo = this.tkutente_rec_tipo;
        }else {
            this.tkutente_rec_tipo = astr.tkutente_rec_tipo;
        }



        l_query   = "";
        l_query  += " insert into pgmr.ep_utente_recapito_tipo ( \n";
        l_query  += "             tkutente_rec_tipo   \n";
        l_query  += "           , cdutente_rec_tipo_m   \n";
        l_query  += "           , dsutente_rec_tipo   \n";
        l_query  += "           , nrposi   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil_inse   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.cdutente_rec_tipo_m.equals("")) astr.cdutente_rec_tipo_m = null;
        if (astr.dsutente_rec_tipo.equals("")) astr.dsutente_rec_tipo = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil_inse.equals("")) astr.profil_inse = null;
        if (astr.profil.equals("")) astr.profil = null;


        ind = 1;
        if (astr.tkutente_rec_tipo == 0 && null_tkutente_rec_tipo){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkutente_rec_tipo); ind += 1;
        } 
        pstm.setString    (ind, astr.cdutente_rec_tipo_m); ind += 1;
        pstm.setString    (ind, astr.dsutente_rec_tipo); ind += 1;
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

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        // --- Ripristino le Stringhe NULL

        if (astr.cdutente_rec_tipo_m == null) astr.cdutente_rec_tipo_m = "";
        if (astr.dsutente_rec_tipo == null) astr.dsutente_rec_tipo = "";
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

        long ll_tk = f_tabkey.getTabkey( "ep_utente_recapito_tipo" );

        return ll_tk;

    }




    /****
        executeUpdate: ep_utente_recapito_tipo
    **/ 


    public int executeUpdate( com.ateikon.structure.Rec_ep_utente_recapito_tipo astr ) throws Exception { 
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.ep_utente_recapito_tipo  \n";
        l_query  += "         set cdutente_rec_tipo_m = ?  \n";
        l_query  += "           , dsutente_rec_tipo = ?  \n";
        l_query  += "           , nrposi = ?  \n";
        l_query  += "           , profil_inse = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "  where tkutente_rec_tipo = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.cdutente_rec_tipo_m.equals("")) astr.cdutente_rec_tipo_m = null;
        if (astr.dsutente_rec_tipo.equals("")) astr.dsutente_rec_tipo = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil_inse.equals("")) astr.profil_inse = null;
        if (astr.profil.equals("")) astr.profil = null;


        ind = 1;
        pstm.setString    (ind, astr.cdutente_rec_tipo_m); ind += 1;
        pstm.setString    (ind, astr.dsutente_rec_tipo); ind += 1;
        if (astr.nrposi == 0 && null_nrposi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.nrposi); ind += 1;
        } 
        pstm.setString    (ind, astr.profil_inse); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;

        if (astr.tkutente_rec_tipo == 0 && null_tkutente_rec_tipo){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkutente_rec_tipo); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.tkutente_rec_tipo = astr.tkutente_rec_tipo; 

        // --- Ripristino le Stringhe NULL

        if (astr.cdutente_rec_tipo_m == null) astr.cdutente_rec_tipo_m = "";
        if (astr.dsutente_rec_tipo == null) astr.dsutente_rec_tipo = "";
        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil_inse == null) astr.profil_inse = "";
        if (astr.profil == null) astr.profil = "";


        return tot_rec;

    }



    /****
        getKey: ep_utente_recapito_tipo
    **/ 

    public int deleteKey( long       tkutente_rec_tipo
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " delete \n";
        l_query  += "   from pgmr.ep_utente_recapito_tipo  \n";
        if (tkutente_rec_tipo == 0 && null_tkutente_rec_tipo){ 
            l_query  += "  where tkutente_rec_tipo is null \n";
        }else { 
            l_query  += "  where tkutente_rec_tipo = ?  \n";
        } 

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (tkutente_rec_tipo == 0 && null_tkutente_rec_tipo){ 
        }else { 
            pstm.setLong      (ind, tkutente_rec_tipo); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();
        pstm.close();

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public long       tkutente_rec_tipo = 0; 






    // --- Impostando a false queste prop. il def. è 0

    public boolean ib_calcola_token = true;
    public boolean ib_ctrl_mindate = false;
    public boolean null_tkutente_rec_tipo = true;
    public boolean null_nrposi = true;







}

