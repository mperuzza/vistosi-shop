package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Web_import_ordven extends Atk_sql {

    public Web_import_ordven() {

        super();
    }




    /****
        getKey: web_import_ordven
    **/ 

    public ResultSet getKey( long       tkimp
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.web_import_ordven  \n";
        l_query  += "  where tkimp = ? \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setLong      (ind, tkimp); ind += 1;

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        execute: web_import_ordven
    **/ 


    public int execute ( com.ateikon.structure.Rec_web_import_ordven astr) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        return execute ( astr.tkimp
                       , astr.cdarti
                       , astr.quantita
                       , astr.tkcarrellop
                       , astr.tkposi
                       , astr.rif_ord
                       , astr.rif_riga_ord
                       , astr.rif_cdartm
                       , astr.rif_descr
                       , astr.dtwebsync
                       , astr.rif_barcode
                       , astr.cdutente
                        );


    }




    /****
        execute: web_import_ordven
    **/ 


    public int execute ( long       tkimp
                       , String     cdarti
                       , double     quantita
                       , long       tkcarrellop
                       , long       tkposi
                       , String     rif_ord
                       , String     rif_riga_ord
                       , String     rif_cdartm
                       , String     rif_descr
                       , Timestamp  dtwebsync
                       , String     rif_barcode
                       , String     cdutente
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " select tkimp\n";
        l_query  += "   from pgmr.web_import_ordven  \n";
        l_query  += "  where tkimp = ? \n";


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setLong      (ind, tkimp); ind += 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            pstm.close();
            tot_rec = executeUpdate ( tkimp
                                    , cdarti
                                    , quantita
                                    , tkcarrellop
                                    , tkposi
                                    , rif_ord
                                    , rif_riga_ord
                                    , rif_cdartm
                                    , rif_descr
                                    , dtwebsync
                                    , rif_barcode
                                    , cdutente
                                    );
        }else {

            pstm.close();
            tot_rec = executeInsert ( tkimp
                                    , cdarti
                                    , quantita
                                    , tkcarrellop
                                    , tkposi
                                    , rif_ord
                                    , rif_riga_ord
                                    , rif_cdartm
                                    , rif_descr
                                    , dtwebsync
                                    , rif_barcode
                                    , cdutente
                                    );
        }

        if (tot_rec != 1){

            m_connection.rollback();
            throw new Exception("Errore Agg. web_import_ordven! ");
        }

        return tot_rec;
    }




    /****
        executeInsert: web_import_ordven
    **/ 


    public int executeInsert( long       tkimp
                            , String     cdarti
                            , double     quantita
                            , long       tkcarrellop
                            , long       tkposi
                            , String     rif_ord
                            , String     rif_riga_ord
                            , String     rif_cdartm
                            , String     rif_descr
                            , Timestamp  dtwebsync
                            , String     rif_barcode
                            , String     cdutente
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        F_tabkey f_tabkey = new F_tabkey();
        setProfilo((Atk_sql) f_tabkey );

        long ll_tk = f_tabkey.getTabkey( "web_import_ordven" );

        if (ll_tk <= 0){
            throw new Exception ("Error Keypool (web_import_ordven): "+ll_tk );
        }

        this.tkimp = ll_tk;
        tkimp = this.tkimp;



        l_query   = "";
        l_query  += " insert into pgmr.web_import_ordven ( \n";
        l_query  += "             tkimp   \n";
        l_query  += "           , cdarti   \n";
        l_query  += "           , quantita   \n";
        l_query  += "           , tkcarrellop   \n";
        l_query  += "           , tkposi   \n";
        l_query  += "           , rif_ord   \n";
        l_query  += "           , rif_riga_ord   \n";
        l_query  += "           , rif_cdartm   \n";
        l_query  += "           , rif_descr   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "           , dtwebsync   \n";
        l_query  += "           , rif_barcode   \n";
        l_query  += "           , cdutente   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cdarti.equals("")) cdarti = null;
        if (rif_ord.equals("")) rif_ord = null;
        if (rif_riga_ord.equals("")) rif_riga_ord = null;
        if (rif_cdartm.equals("")) rif_cdartm = null;
        if (rif_descr.equals("")) rif_descr = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;
        if (rif_barcode.equals("")) rif_barcode = null;
        if (cdutente.equals("")) cdutente = null;


        ind = 1;
        pstm.setLong      (ind, tkimp); ind += 1;
        pstm.setString    (ind, cdarti); ind += 1;
        if (quantita == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, quantita); ind += 1;
        } 
        if (tkcarrellop == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkcarrellop); ind += 1;
        } 
        if (tkposi == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkposi); ind += 1;
        } 
        pstm.setString    (ind, rif_ord); ind += 1;
        pstm.setString    (ind, rif_riga_ord); ind += 1;
        pstm.setString    (ind, rif_cdartm); ind += 1;
        pstm.setString    (ind, rif_descr); ind += 1;
        pstm.setString    (ind, cdazie); ind += 1;
        pstm.setString    (ind, cddipa); ind += 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtinse); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;
        pstm.setTimestamp (ind, dtwebsync); ind += 1;
        pstm.setString    (ind, rif_barcode); ind += 1;
        pstm.setString    (ind, cdutente); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        return tot_rec;

    }




    /****
        executeUpdate: web_import_ordven
    **/ 


    public int executeUpdate( long       tkimp
                            , String     cdarti
                            , double     quantita
                            , long       tkcarrellop
                            , long       tkposi
                            , String     rif_ord
                            , String     rif_riga_ord
                            , String     rif_cdartm
                            , String     rif_descr
                            , Timestamp  dtwebsync
                            , String     rif_barcode
                            , String     cdutente
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.web_import_ordven  \n";
        l_query  += "         set cdarti = ?  \n";
        l_query  += "           , quantita = ?  \n";
        l_query  += "           , tkcarrellop = ?  \n";
        l_query  += "           , tkposi = ?  \n";
        l_query  += "           , rif_ord = ?  \n";
        l_query  += "           , rif_riga_ord = ?  \n";
        l_query  += "           , rif_cdartm = ?  \n";
        l_query  += "           , rif_descr = ?  \n";
        l_query  += "           , cdazie = ?  \n";
        l_query  += "           , cddipa = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtinse = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "           , dtwebsync = ?  \n";
        l_query  += "           , rif_barcode = ?  \n";
        l_query  += "           , cdutente = ?  \n";
        l_query  += "  where tkimp = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cdarti.equals("")) cdarti = null;
        if (rif_ord.equals("")) rif_ord = null;
        if (rif_riga_ord.equals("")) rif_riga_ord = null;
        if (rif_cdartm.equals("")) rif_cdartm = null;
        if (rif_descr.equals("")) rif_descr = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;
        if (rif_barcode.equals("")) rif_barcode = null;
        if (cdutente.equals("")) cdutente = null;


        ind = 1;
        pstm.setString    (ind, cdarti); ind += 1;
        if (quantita == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, quantita); ind += 1;
        } 
        if (tkcarrellop == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkcarrellop); ind += 1;
        } 
        if (tkposi == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkposi); ind += 1;
        } 
        pstm.setString    (ind, rif_ord); ind += 1;
        pstm.setString    (ind, rif_riga_ord); ind += 1;
        pstm.setString    (ind, rif_cdartm); ind += 1;
        pstm.setString    (ind, rif_descr); ind += 1;
        pstm.setString    (ind, cdazie); ind += 1;
        pstm.setString    (ind, cddipa); ind += 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtinse); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;
        pstm.setTimestamp (ind, dtwebsync); ind += 1;
        pstm.setString    (ind, rif_barcode); ind += 1;
        pstm.setString    (ind, cdutente); ind += 1;
        pstm.setLong      (ind, tkimp); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public long       tkimp = 0; 







}

