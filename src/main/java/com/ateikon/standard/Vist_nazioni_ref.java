package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Vist_nazioni_ref extends Atk_sql {

    public Vist_nazioni_ref() {

        super();
    }




    /****
        getAll: vist_nazioni_ref
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.vist_nazioni_ref  \n";

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: vist_nazioni_ref
    **/ 

    public ResultSet getKey( long       tknaziref
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.vist_nazioni_ref  \n";
        if (tknaziref == 0 && null_tknaziref){ 
            l_query  += "  where tknaziref is null \n";
        }else { 
            l_query  += "  where tknaziref = ?  \n";
        } 

        pstm = setQuery( l_query ) ;

        ind = 1;
        if (tknaziref == 0 && null_tknaziref){ 
        }else { 
            pstm.setLong      (ind, tknaziref); ind += 1;
        } 

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: vist_nazioni_ref
    **/ 


    public com.ateikon.structure.Rec_vist_nazioni_ref  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_vist_nazioni_ref lstr = new com.ateikon.structure.Rec_vist_nazioni_ref();

        if (rs == null) return lstr;
        if (rs.getObject("tknaziref")!=null) lstr.tknaziref = rs.getLong      ("tknaziref"); 
        if (rs.getObject("cdnazi")!=null) lstr.cdnazi = rs.getString    ("cdnazi"); 
        if (rs.getObject("tkutente")!=null) lstr.tkutente = rs.getLong      ("tkutente"); 
        if (rs.getObject("cdruolo")!=null) lstr.cdruolo = rs.getString    ("cdruolo"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("fg_agg")!=null) lstr.fg_agg = rs.getString    ("fg_agg"); 

        return lstr;
    }




    /****
        preupdate: vist_nazioni_ref

        sovrascrivere per impostare i controlli da effetuare prima dell'execute

        questo metodo è stato introdotto per la sincro: in fase di export prima  
        di scrivere il file XML viene chiamato
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_vist_nazioni_ref astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        return 1;
    }



    /****
        execute: vist_nazioni_ref
    **/ 


    public int execute ( com.ateikon.structure.Rec_vist_nazioni_ref astr) throws Exception {
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
        l_query  += " select tknaziref\n";
        l_query  += "   from pgmr.vist_nazioni_ref  \n";
        if (astr.tknaziref == 0 && null_tknaziref){ 
            l_query  += "  where tknaziref is null \n";
        }else { 
            l_query  += "  where tknaziref = ?  \n";
        } 


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (astr.tknaziref == 0 && null_tknaziref){ 
        }else { 
            pstm.setLong      (ind, astr.tknaziref); ind += 1;
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
            throw new Exception("Errore Agg. vist_nazioni_ref! ");
        }
        */ 

        return tot_rec;
    }




    /****
        executeInsert: vist_nazioni_ref
    **/ 


    public int executeInsert( com.ateikon.structure.Rec_vist_nazioni_ref astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        if (ib_calcola_token){
            long ll_tk = getNew_token( );

            if (ll_tk <= 0){
                throw new Exception ("Error Keypool (vist_nazioni_ref): "+ll_tk );
            }

            this.tknaziref = ll_tk;
            astr.tknaziref = this.tknaziref;
        }else {
            this.tknaziref = astr.tknaziref;
        }



        l_query   = "";
        l_query  += " insert into pgmr.vist_nazioni_ref ( \n";
        l_query  += "             tknaziref   \n";
        l_query  += "           , cdnazi   \n";
        l_query  += "           , tkutente   \n";
        l_query  += "           , cdruolo   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "           , fg_agg   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.cdnazi.equals("")) astr.cdnazi = null;
        if (astr.cdruolo.equals("")) astr.cdruolo = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;
        if (astr.fg_agg.equals("")) astr.fg_agg = null;


        ind = 1;
        if (astr.tknaziref == 0 && null_tknaziref){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tknaziref); ind += 1;
        } 
        pstm.setString    (ind, astr.cdnazi); ind += 1;
        if (astr.tkutente == 0 && null_tkutente){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkutente); ind += 1;
        } 
        pstm.setString    (ind, astr.cdruolo); ind += 1;
        pstm.setString    (ind, astr.cdazie); ind += 1;
        pstm.setString    (ind, astr.cddipa); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtinse); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;
        pstm.setString    (ind, astr.fg_agg); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        // --- Ripristino le Stringhe NULL

        if (astr.cdnazi == null) astr.cdnazi = "";
        if (astr.cdruolo == null) astr.cdruolo = "";
        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil == null) astr.profil = "";
        if (astr.fg_agg == null) astr.fg_agg = "";


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

        long ll_tk = f_tabkey.getTabkey( "vist_nazioni_ref" );

        return ll_tk;

    }




    /****
        executeUpdate: vist_nazioni_ref
    **/ 


    public int executeUpdate( com.ateikon.structure.Rec_vist_nazioni_ref astr ) throws Exception { 
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.vist_nazioni_ref  \n";
        l_query  += "         set cdnazi = ?  \n";
        l_query  += "           , tkutente = ?  \n";
        l_query  += "           , cdruolo = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "           , fg_agg = ?  \n";
        l_query  += "  where tknaziref = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.cdnazi.equals("")) astr.cdnazi = null;
        if (astr.cdruolo.equals("")) astr.cdruolo = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;
        if (astr.fg_agg.equals("")) astr.fg_agg = null;


        ind = 1;
        pstm.setString    (ind, astr.cdnazi); ind += 1;
        if (astr.tkutente == 0 && null_tkutente){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkutente); ind += 1;
        } 
        pstm.setString    (ind, astr.cdruolo); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;
        pstm.setString    (ind, astr.fg_agg); ind += 1;

        if (astr.tknaziref == 0 && null_tknaziref){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tknaziref); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.tknaziref = astr.tknaziref; 

        // --- Ripristino le Stringhe NULL

        if (astr.cdnazi == null) astr.cdnazi = "";
        if (astr.cdruolo == null) astr.cdruolo = "";
        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil == null) astr.profil = "";
        if (astr.fg_agg == null) astr.fg_agg = "";


        return tot_rec;

    }



    /****
        getKey: vist_nazioni_ref
    **/ 

    public int deleteKey( long       tknaziref
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " delete \n";
        l_query  += "   from pgmr.vist_nazioni_ref  \n";
        if (tknaziref == 0 && null_tknaziref){ 
            l_query  += "  where tknaziref is null \n";
        }else { 
            l_query  += "  where tknaziref = ?  \n";
        } 

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (tknaziref == 0 && null_tknaziref){ 
        }else { 
            pstm.setLong      (ind, tknaziref); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();
        pstm.close();

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public long       tknaziref = 0; 






    // --- Impostando a false queste prop. il def. è 0

    public boolean ib_calcola_token = true;
    public boolean ib_ctrl_mindate = false;
    public boolean null_tknaziref = true;
    public boolean null_tkutente = true;







}

