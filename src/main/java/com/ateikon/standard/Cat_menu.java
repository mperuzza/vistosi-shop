package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Cat_menu extends Atk_sql {

    public Cat_menu() {

        super();
    }




    /****
        getAll: cat_menu
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.cat_menu  \n";

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: cat_menu
    **/ 

    public ResultSet getKey( long       tkmenu
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.cat_menu  \n";
        l_query  += "  where tkmenu = ? \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setLong      (ind, tkmenu); ind += 1;

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: cat_menu
    **/ 


    public com.ateikon.structure.Rec_cat_menu  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_cat_menu lstr = new com.ateikon.structure.Rec_cat_menu();

        if (rs == null) return lstr;
        if (rs.getObject("tkmenu")!=null) lstr.tkmenu = rs.getLong      ("tkmenu"); 
        if (rs.getObject("cdmenu_m")!=null) lstr.cdmenu_m = rs.getString    ("cdmenu_m"); 
        if (rs.getObject("dsmenu")!=null) lstr.dsmenu = rs.getString    ("dsmenu"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("profil_inse")!=null) lstr.profil_inse = rs.getString    ("profil_inse"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("fgabil")!=null) lstr.fgabil = rs.getString    ("fgabil"); 
        if (rs.getObject("nrposi")!=null) lstr.nrposi = rs.getLong      ("nrposi"); 

        return lstr;
    }




    /****
        execute: cat_menu
    **/ 


    public int execute ( com.ateikon.structure.Rec_cat_menu astr) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec =  execute ( astr.tkmenu
                          , astr.cdmenu_m
                          , astr.dsmenu
                          , astr.profil_inse
                          , astr.fgabil
                          , astr.nrposi
                           );

        return tot_rec;
    }




    /****
        execute: cat_menu
    **/ 


    public int execute ( long       tkmenu
                       , String     cdmenu_m
                       , String     dsmenu
                       , String     profil_inse
                       , String     fgabil
                       , long       nrposi
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " select tkmenu\n";
        l_query  += "   from pgmr.cat_menu  \n";
        l_query  += "  where tkmenu = ? \n";


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (tkmenu == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkmenu); ind += 1;
        } 

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            pstm.close();
            tot_rec = executeUpdate ( tkmenu
                                    , cdmenu_m
                                    , dsmenu
                                    , profil_inse
                                    , fgabil
                                    , nrposi
                                    );
        }else {

            pstm.close();
            tot_rec = executeInsert ( tkmenu
                                    , cdmenu_m
                                    , dsmenu
                                    , profil_inse
                                    , fgabil
                                    , nrposi
                                    );
        }

        if (tot_rec != 1){

            m_connection.rollback();
            throw new Exception("Errore Agg. cat_menu! ");
        }

        return tot_rec;
    }




    /****
        executeInsert: cat_menu
    **/ 


    public int executeInsert( long       tkmenu
                            , String     cdmenu_m
                            , String     dsmenu
                            , String     profil_inse
                            , String     fgabil
                            , long       nrposi
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        if (ib_calcola_token){
            long ll_tk = getNew_token( );

            if (ll_tk <= 0){
                throw new Exception ("Error Keypool (cat_menu): "+ll_tk );
            }

            this.tkmenu = ll_tk;
            tkmenu = this.tkmenu;
        }



        l_query   = "";
        l_query  += " insert into pgmr.cat_menu ( \n";
        l_query  += "             tkmenu   \n";
        l_query  += "           , cdmenu_m   \n";
        l_query  += "           , dsmenu   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , profil_inse   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "           , fgabil   \n";
        l_query  += "           , nrposi   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cdmenu_m.equals("")) cdmenu_m = null;
        if (dsmenu.equals("")) dsmenu = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;
        if (profil_inse.equals("")) profil_inse = null;
        if (fgabil.equals("")) fgabil = null;


        ind = 1;
        if (tkmenu == 0 && null_tkmenu){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkmenu); ind += 1;
        } 
        pstm.setString    (ind, cdmenu_m); ind += 1;
        pstm.setString    (ind, dsmenu); ind += 1;
        pstm.setString    (ind, cdazie); ind += 1;
        pstm.setString    (ind, cddipa); ind += 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setString    (ind, profil_inse); ind += 1;
        pstm.setTimestamp (ind, dtinse); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;
        pstm.setString    (ind, fgabil); ind += 1;
        if (nrposi == 0 && null_nrposi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, nrposi); ind += 1;
        } 

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

        long ll_tk = f_tabkey.getTabkey( "cat_menu" );

        return ll_tk;

    }




    /****
        executeUpdate: cat_menu
    **/ 


    public int executeUpdate( long       tkmenu
                            , String     cdmenu_m
                            , String     dsmenu
                            , String     profil_inse
                            , String     fgabil
                            , long       nrposi
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.cat_menu  \n";
        l_query  += "         set cdmenu_m = ?  \n";
        l_query  += "           , dsmenu = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , profil_inse = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "           , fgabil = ?  \n";
        l_query  += "           , nrposi = ?  \n";
        l_query  += "  where tkmenu = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cdmenu_m.equals("")) cdmenu_m = null;
        if (dsmenu.equals("")) dsmenu = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;
        if (profil_inse.equals("")) profil_inse = null;
        if (fgabil.equals("")) fgabil = null;


        ind = 1;
        pstm.setString    (ind, cdmenu_m); ind += 1;
        pstm.setString    (ind, dsmenu); ind += 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setString    (ind, profil_inse); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;
        pstm.setString    (ind, fgabil); ind += 1;
        if (nrposi == 0 && null_nrposi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, nrposi); ind += 1;
        } 

        pstm.setLong      (ind, tkmenu); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.tkmenu = tkmenu; 

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public long       tkmenu = 0; 






    // --- Impostando a false queste prop. il def. è 0

    public boolean ib_calcola_token = true;
    public boolean null_tkmenu = true;
    public boolean null_nrposi = true;







}

