package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Web_ord_test_note extends Atk_sql {

    public Web_ord_test_note() {

        super();
    }




    /****
        getAll: web_ord_test_note
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.web_ord_test_note  \n";

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: web_ord_test_note
    **/ 

    public ResultSet getKey( long       tkordi
                           , String     tiponota
                           , long       riga
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.web_ord_test_note  \n";
        l_query  += "  where tkordi = ? \n";
        l_query  += "    and tiponota = ? \n";
        l_query  += "    and riga = ? \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setLong      (ind, tkordi); ind += 1;
        pstm.setString    (ind, tiponota); ind += 1;
        pstm.setLong      (ind, riga); ind += 1;

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: web_ord_test_note
    **/ 


    public com.ateikon.structure.Rec_web_ord_test_note  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_web_ord_test_note lstr = new com.ateikon.structure.Rec_web_ord_test_note();

        if (rs == null) return lstr;
        if (rs.getObject("tkordi")!=null) lstr.tkordi = rs.getLong      ("tkordi"); 
        if (rs.getObject("tiponota")!=null) lstr.tiponota = rs.getString    ("tiponota"); 
        if (rs.getObject("riga")!=null) lstr.riga = rs.getLong      ("riga"); 
        if (rs.getObject("testo")!=null) lstr.testo = rs.getString    ("testo"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 

        return lstr;
    }




    /****
        execute: web_ord_test_note
    **/ 


    public int execute ( com.ateikon.structure.Rec_web_ord_test_note astr) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec =  execute ( astr.tkordi
                           , astr.tiponota
                           , astr.riga
                          , astr.testo
                           );

        return tot_rec;
    }




    /****
        execute: web_ord_test_note
    **/ 


    public int execute ( long       tkordi
                       , String     tiponota
                       , long       riga
                       , String     testo
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " select tkordi\n";
        l_query  += "      , tiponota\n";
        l_query  += "      , riga\n";
        l_query  += "   from pgmr.web_ord_test_note  \n";
        l_query  += "  where tkordi = ? \n";
        l_query  += "    and tiponota = ? \n";
        l_query  += "    and riga = ? \n";


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (tkordi == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkordi); ind += 1;
        } 
        pstm.setString    (ind, tiponota); ind += 1;
        if (riga == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, riga); ind += 1;
        } 

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            pstm.close();
            tot_rec = executeUpdate ( tkordi
                                    , tiponota
                                    , riga
                                    , testo
                                    );
        }else {

            pstm.close();
            tot_rec = executeInsert ( tkordi
                                    , tiponota
                                    , riga
                                    , testo
                                    );
        }

        if (tot_rec != 1){

            m_connection.rollback();
            throw new Exception("Errore Agg. web_ord_test_note! ");
        }

        return tot_rec;
    }




    /****
        executeInsert: web_ord_test_note
    **/ 


    public int executeInsert( long       tkordi
                            , String     tiponota
                            , long       riga
                            , String     testo
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;





        l_query   = "";
        l_query  += " insert into pgmr.web_ord_test_note ( \n";
        l_query  += "             tkordi   \n";
        l_query  += "           , tiponota   \n";
        l_query  += "           , riga   \n";
        l_query  += "           , testo   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (testo.equals("")) testo = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;


        ind = 1;
        if (tkordi == 0 && null_tkordi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkordi); ind += 1;
        } 
        pstm.setString    (ind, tiponota); ind += 1;
        if (riga == 0 && null_riga){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, riga); ind += 1;
        } 
        pstm.setString    (ind, testo); ind += 1;
        pstm.setString    (ind, cdazie); ind += 1;
        pstm.setString    (ind, cddipa); ind += 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtinse); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

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

        long ll_tk = f_tabkey.getTabkey( "web_ord_test_note" );

        return ll_tk;

    }




    /****
        executeUpdate: web_ord_test_note
    **/ 


    public int executeUpdate( long       tkordi
                            , String     tiponota
                            , long       riga
                            , String     testo
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.web_ord_test_note  \n";
        l_query  += "         set testo = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "  where tkordi = ? \n";
        l_query  += "    and tiponota = ? \n";
        l_query  += "    and riga = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (testo.equals("")) testo = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;


        ind = 1;
        pstm.setString    (ind, testo); ind += 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;

        pstm.setLong      (ind, tkordi); ind += 1;
        pstm.setString    (ind, tiponota); ind += 1;
        pstm.setLong      (ind, riga); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.tkordi = tkordi; 
        this.tiponota = tiponota; 
        this.riga = riga; 

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public long       tkordi = 0; 
    public String     tiponota = ""; 
    public long       riga = 0; 






    // --- Impostando a false queste prop. il def. è 0

    public boolean ib_calcola_token = true;
    public boolean null_tkordi = true;
    public boolean null_riga = true;







}

