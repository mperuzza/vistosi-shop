package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Web_ord_posi_matr extends Atk_sql {

    public Web_ord_posi_matr() {

        super();
    }




    /****
        getAll: web_ord_posi_matr
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.web_ord_posi_matr  \n";

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: web_ord_posi_matr
    **/ 

    public ResultSet getKey( long       tkposi_matr
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.web_ord_posi_matr  \n";
        l_query  += "  where tkposi_matr = ? \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setLong      (ind, tkposi_matr); ind += 1;

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: web_ord_posi_matr
    **/ 


    public com.ateikon.structure.Rec_web_ord_posi_matr  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_web_ord_posi_matr lstr = new com.ateikon.structure.Rec_web_ord_posi_matr();

        if (rs == null) return lstr;
        if (rs.getObject("tkposi_matr")!=null) lstr.tkposi_matr = rs.getLong      ("tkposi_matr"); 
        if (rs.getObject("tkposi")!=null) lstr.tkposi = rs.getLong      ("tkposi"); 
        if (rs.getObject("tkordi")!=null) lstr.tkordi = rs.getLong      ("tkordi"); 
        if (rs.getObject("tkmatricola")!=null) lstr.tkmatricola = rs.getLong      ("tkmatricola"); 
        if (rs.getObject("tkmov_matr")!=null) lstr.tkmov_matr = rs.getLong      ("tkmov_matr"); 
        if (rs.getObject("qtatot")!=null) lstr.qtatot = rs.getDouble    ("qtatot"); 
        if (rs.getObject("impuni")!=null) lstr.impuni = rs.getDouble    ("impuni"); 
        if (rs.getObject("imptot")!=null) lstr.imptot = rs.getDouble    ("imptot"); 
        if (rs.getObject("qtacons")!=null) lstr.qtacons = rs.getDouble    ("qtacons"); 
        if (rs.getObject("qtacons_s")!=null) lstr.qtacons_s = rs.getDouble    ("qtacons_s"); 
        if (rs.getObject("tkmaga")!=null) lstr.tkmaga = rs.getLong      ("tkmaga"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 

        return lstr;
    }




    /****
        execute: web_ord_posi_matr
    **/ 


    public int execute ( com.ateikon.structure.Rec_web_ord_posi_matr astr) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec =  execute ( astr.tkposi_matr
                          , astr.tkposi
                          , astr.tkordi
                          , astr.tkmatricola
                          , astr.tkmov_matr
                          , astr.qtatot
                          , astr.impuni
                          , astr.imptot
                          , astr.qtacons
                          , astr.qtacons_s
                          , astr.tkmaga
                           );

        return tot_rec;
    }




    /****
        execute: web_ord_posi_matr
    **/ 


    public int execute ( long       tkposi_matr
                       , long       tkposi
                       , long       tkordi
                       , long       tkmatricola
                       , long       tkmov_matr
                       , double     qtatot
                       , double     impuni
                       , double     imptot
                       , double     qtacons
                       , double     qtacons_s
                       , long       tkmaga
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " select tkposi_matr\n";
        l_query  += "   from pgmr.web_ord_posi_matr  \n";
        l_query  += "  where tkposi_matr = ? \n";


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (tkposi_matr == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkposi_matr); ind += 1;
        } 

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            pstm.close();
            tot_rec = executeUpdate ( tkposi_matr
                                    , tkposi
                                    , tkordi
                                    , tkmatricola
                                    , tkmov_matr
                                    , qtatot
                                    , impuni
                                    , imptot
                                    , qtacons
                                    , qtacons_s
                                    , tkmaga
                                    );
        }else {

            pstm.close();
            tot_rec = executeInsert ( tkposi_matr
                                    , tkposi
                                    , tkordi
                                    , tkmatricola
                                    , tkmov_matr
                                    , qtatot
                                    , impuni
                                    , imptot
                                    , qtacons
                                    , qtacons_s
                                    , tkmaga
                                    );
        }

        if (tot_rec != 1){

            m_connection.rollback();
            throw new Exception("Errore Agg. web_ord_posi_matr! ");
        }

        return tot_rec;
    }




    /****
        executeInsert: web_ord_posi_matr
    **/ 


    public int executeInsert( long       tkposi_matr
                            , long       tkposi
                            , long       tkordi
                            , long       tkmatricola
                            , long       tkmov_matr
                            , double     qtatot
                            , double     impuni
                            , double     imptot
                            , double     qtacons
                            , double     qtacons_s
                            , long       tkmaga
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        if (ib_calcola_token){
            long ll_tk = getNew_token( );

            if (ll_tk <= 0){
                throw new Exception ("Error Keypool (web_ord_posi_matr): "+ll_tk );
            }

            this.tkposi_matr = ll_tk;
            tkposi_matr = this.tkposi_matr;
        }



        l_query   = "";
        l_query  += " insert into pgmr.web_ord_posi_matr ( \n";
        l_query  += "             tkposi_matr   \n";
        l_query  += "           , tkposi   \n";
        l_query  += "           , tkordi   \n";
        l_query  += "           , tkmatricola   \n";
        l_query  += "           , tkmov_matr   \n";
        l_query  += "           , qtatot   \n";
        l_query  += "           , impuni   \n";
        l_query  += "           , imptot   \n";
        l_query  += "           , qtacons   \n";
        l_query  += "           , qtacons_s   \n";
        l_query  += "           , tkmaga   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;


        ind = 1;
        if (tkposi_matr == 0 && null_tkposi_matr){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkposi_matr); ind += 1;
        } 
        if (tkposi == 0 && null_tkposi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkposi); ind += 1;
        } 
        if (tkordi == 0 && null_tkordi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkordi); ind += 1;
        } 
        if (tkmatricola == 0 && null_tkmatricola){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkmatricola); ind += 1;
        } 
        if (tkmov_matr == 0 && null_tkmov_matr){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkmov_matr); ind += 1;
        } 
        if (qtatot == 0 && null_qtatot){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, qtatot); ind += 1;
        } 
        if (impuni == 0 && null_impuni){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, impuni); ind += 1;
        } 
        if (imptot == 0 && null_imptot){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, imptot); ind += 1;
        } 
        if (qtacons == 0 && null_qtacons){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, qtacons); ind += 1;
        } 
        if (qtacons_s == 0 && null_qtacons_s){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, qtacons_s); ind += 1;
        } 
        if (tkmaga == 0 && null_tkmaga){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkmaga); ind += 1;
        } 
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

        long ll_tk = f_tabkey.getTabkey( "web_ord_posi_matr" );

        return ll_tk;

    }




    /****
        executeUpdate: web_ord_posi_matr
    **/ 


    public int executeUpdate( long       tkposi_matr
                            , long       tkposi
                            , long       tkordi
                            , long       tkmatricola
                            , long       tkmov_matr
                            , double     qtatot
                            , double     impuni
                            , double     imptot
                            , double     qtacons
                            , double     qtacons_s
                            , long       tkmaga
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.web_ord_posi_matr  \n";
        l_query  += "         set tkposi = ?  \n";
        l_query  += "           , tkordi = ?  \n";
        l_query  += "           , tkmatricola = ?  \n";
        l_query  += "           , tkmov_matr = ?  \n";
        l_query  += "           , qtatot = ?  \n";
        l_query  += "           , impuni = ?  \n";
        l_query  += "           , imptot = ?  \n";
        l_query  += "           , qtacons = ?  \n";
        l_query  += "           , qtacons_s = ?  \n";
        l_query  += "           , tkmaga = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "  where tkposi_matr = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;


        ind = 1;
        if (tkposi == 0 && null_tkposi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkposi); ind += 1;
        } 
        if (tkordi == 0 && null_tkordi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkordi); ind += 1;
        } 
        if (tkmatricola == 0 && null_tkmatricola){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkmatricola); ind += 1;
        } 
        if (tkmov_matr == 0 && null_tkmov_matr){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkmov_matr); ind += 1;
        } 
        if (qtatot == 0 && null_qtatot){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, qtatot); ind += 1;
        } 
        if (impuni == 0 && null_impuni){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, impuni); ind += 1;
        } 
        if (imptot == 0 && null_imptot){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, imptot); ind += 1;
        } 
        if (qtacons == 0 && null_qtacons){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, qtacons); ind += 1;
        } 
        if (qtacons_s == 0 && null_qtacons_s){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, qtacons_s); ind += 1;
        } 
        if (tkmaga == 0 && null_tkmaga){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkmaga); ind += 1;
        } 
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;

        pstm.setLong      (ind, tkposi_matr); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.tkposi_matr = tkposi_matr; 

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public long       tkposi_matr = 0; 






    // --- Impostando a false queste prop. il def. è 0

    public boolean ib_calcola_token = true;
    public boolean null_tkposi_matr = true;
    public boolean null_tkposi = true;
    public boolean null_tkordi = true;
    public boolean null_tkmatricola = true;
    public boolean null_tkmov_matr = true;
    public boolean null_qtatot = true;
    public boolean null_impuni = true;
    public boolean null_imptot = true;
    public boolean null_qtacons = true;
    public boolean null_qtacons_s = true;
    public boolean null_tkmaga = true;







}

