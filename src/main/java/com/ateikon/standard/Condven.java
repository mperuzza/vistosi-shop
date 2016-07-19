package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Condven extends Atk_sql {

    public Condven() {

        super();
    }




    /****
        getAll: condven
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.condven  \n";

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: condven
    **/ 

    public ResultSet getKey( long       tkcondven
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.condven  \n";
        l_query  += "  where tkcondven = ? \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setLong      (ind, tkcondven); ind += 1;

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: condven
    **/ 


    public com.ateikon.structure.Rec_condven  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_condven lstr = new com.ateikon.structure.Rec_condven();

        if (rs == null) return lstr;
        if (rs.getObject("tkcondven")!=null) lstr.tkcondven = rs.getLong      ("tkcondven"); 
        if (rs.getObject("cdclac")!=null) lstr.cdclac = rs.getString    ("cdclac"); 
        if (rs.getObject("impmin")!=null) lstr.impmin = rs.getDouble    ("impmin"); 
        if (rs.getObject("qtamin")!=null) lstr.qtamin = rs.getDouble    ("qtamin"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("fgnew")!=null) lstr.fgnew = rs.getString    ("fgnew"); 
        if (rs.getObject("tkclie")!=null) lstr.tkclie = rs.getString    ("tkclie"); 

        return lstr;
    }




    /****
        execute: condven
    **/ 


    public int execute ( com.ateikon.structure.Rec_condven astr) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec =  execute ( astr.tkcondven
                          , astr.cdclac
                          , astr.impmin
                          , astr.qtamin
                          , astr.fgnew
                          , astr.tkclie
                           );

        return tot_rec;
    }




    /****
        execute: condven
    **/ 


    public int execute ( long       tkcondven
                       , String     cdclac
                       , double     impmin
                       , double     qtamin
                       , String     fgnew
                       , String     tkclie
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " select tkcondven\n";
        l_query  += "   from pgmr.condven  \n";
        l_query  += "  where tkcondven = ? \n";


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (tkcondven == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkcondven); ind += 1;
        } 

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            pstm.close();
            tot_rec = executeUpdate ( tkcondven
                                    , cdclac
                                    , impmin
                                    , qtamin
                                    , fgnew
                                    , tkclie
                                    );
        }else {

            pstm.close();
            tot_rec = executeInsert ( tkcondven
                                    , cdclac
                                    , impmin
                                    , qtamin
                                    , fgnew
                                    , tkclie
                                    );
        }

        if (tot_rec != 1){

            m_connection.rollback();
            throw new Exception("Errore Agg. condven! ");
        }

        return tot_rec;
    }




    /****
        executeInsert: condven
    **/ 


    public int executeInsert( long       tkcondven
                            , String     cdclac
                            , double     impmin
                            , double     qtamin
                            , String     fgnew
                            , String     tkclie
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        if (ib_calcola_token){
            long ll_tk = getNew_token( );

            if (ll_tk <= 0){
                throw new Exception ("Error Keypool (condven): "+ll_tk );
            }

            this.tkcondven = ll_tk;
            tkcondven = this.tkcondven;
        }



        l_query   = "";
        l_query  += " insert into pgmr.condven ( \n";
        l_query  += "             tkcondven   \n";
        l_query  += "           , cdclac   \n";
        l_query  += "           , impmin   \n";
        l_query  += "           , qtamin   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "           , fgnew   \n";
        l_query  += "           , tkclie   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cdclac.equals("")) cdclac = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;
        if (fgnew.equals("")) fgnew = null;
        if (tkclie.equals("")) tkclie = null;


        ind = 1;
        if (tkcondven == 0 && null_tkcondven){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkcondven); ind += 1;
        } 
        pstm.setString    (ind, cdclac); ind += 1;
        if (impmin == 0 && null_impmin){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, impmin); ind += 1;
        } 
        if (qtamin == 0 && null_qtamin){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, qtamin); ind += 1;
        } 
        pstm.setString    (ind, cdazie); ind += 1;
        pstm.setString    (ind, cddipa); ind += 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtinse); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;
        pstm.setString    (ind, fgnew); ind += 1;
        pstm.setString    (ind, tkclie); ind += 1;

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

        long ll_tk = f_tabkey.getTabkey( "condven" );

        return ll_tk;

    }




    /****
        executeUpdate: condven
    **/ 


    public int executeUpdate( long       tkcondven
                            , String     cdclac
                            , double     impmin
                            , double     qtamin
                            , String     fgnew
                            , String     tkclie
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.condven  \n";
        l_query  += "         set cdclac = ?  \n";
        l_query  += "           , impmin = ?  \n";
        l_query  += "           , qtamin = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "           , fgnew = ?  \n";
        l_query  += "           , tkclie = ?  \n";
        l_query  += "  where tkcondven = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cdclac.equals("")) cdclac = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;
        if (fgnew.equals("")) fgnew = null;
        if (tkclie.equals("")) tkclie = null;


        ind = 1;
        pstm.setString    (ind, cdclac); ind += 1;
        if (impmin == 0 && null_impmin){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, impmin); ind += 1;
        } 
        if (qtamin == 0 && null_qtamin){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, qtamin); ind += 1;
        } 
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;
        pstm.setString    (ind, fgnew); ind += 1;
        pstm.setString    (ind, tkclie); ind += 1;

        pstm.setLong      (ind, tkcondven); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.tkcondven = tkcondven; 

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public long       tkcondven = 0; 






    // --- Impostando a false queste prop. il def. è 0

    public boolean ib_calcola_token = true;
    public boolean null_tkcondven = true;
    public boolean null_impmin = true;
    public boolean null_qtamin = true;







}

