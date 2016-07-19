package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Cat_menu_posi extends Atk_sql {

    public Cat_menu_posi() {

        super();
    }




    /****
        getAll: cat_menu_posi
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.cat_menu_posi  \n";

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: cat_menu_posi
    **/ 

    public ResultSet getKey( long       tkmenu_posi
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.cat_menu_posi  \n";
        l_query  += "  where tkmenu_posi = ? \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setLong      (ind, tkmenu_posi); ind += 1;

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: cat_menu_posi
    **/ 


    public com.ateikon.structure.Rec_cat_menu_posi  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_cat_menu_posi lstr = new com.ateikon.structure.Rec_cat_menu_posi();

        if (rs == null) return lstr;
        if (rs.getObject("tkmenu_posi")!=null) lstr.tkmenu_posi = rs.getLong      ("tkmenu_posi"); 
        if (rs.getObject("tkmenu")!=null) lstr.tkmenu = rs.getLong      ("tkmenu"); 
        if (rs.getObject("nrposi")!=null) lstr.nrposi = rs.getLong      ("nrposi"); 
        if (rs.getObject("dsmenu_posi")!=null) lstr.dsmenu_posi = rs.getString    ("dsmenu_posi"); 
        if (rs.getObject("link_menu")!=null) lstr.link_menu = rs.getString    ("link_menu"); 
        if (rs.getObject("tkfunzione")!=null) lstr.tkfunzione = rs.getLong      ("tkfunzione"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("profil_inse")!=null) lstr.profil_inse = rs.getString    ("profil_inse"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 

        return lstr;
    }




    /****
        execute: cat_menu_posi
    **/ 


    public int execute ( com.ateikon.structure.Rec_cat_menu_posi astr) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec =  execute ( astr.tkmenu_posi
                          , astr.tkmenu
                          , astr.nrposi
                          , astr.dsmenu_posi
                          , astr.link_menu
                          , astr.tkfunzione
                          , astr.profil_inse
                           );

        return tot_rec;
    }




    /****
        execute: cat_menu_posi
    **/ 


    public int execute ( long       tkmenu_posi
                       , long       tkmenu
                       , long       nrposi
                       , String     dsmenu_posi
                       , String     link_menu
                       , long       tkfunzione
                       , String     profil_inse
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " select tkmenu_posi\n";
        l_query  += "   from pgmr.cat_menu_posi  \n";
        l_query  += "  where tkmenu_posi = ? \n";


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (tkmenu_posi == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkmenu_posi); ind += 1;
        } 

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            pstm.close();
            tot_rec = executeUpdate ( tkmenu_posi
                                    , tkmenu
                                    , nrposi
                                    , dsmenu_posi
                                    , link_menu
                                    , tkfunzione
                                    , profil_inse
                                    );
        }else {

            pstm.close();
            tot_rec = executeInsert ( tkmenu_posi
                                    , tkmenu
                                    , nrposi
                                    , dsmenu_posi
                                    , link_menu
                                    , tkfunzione
                                    , profil_inse
                                    );
        }

        if (tot_rec != 1){

            m_connection.rollback();
            throw new Exception("Errore Agg. cat_menu_posi! ");
        }

        return tot_rec;
    }




    /****
        executeInsert: cat_menu_posi
    **/ 


    public int executeInsert( long       tkmenu_posi
                            , long       tkmenu
                            , long       nrposi
                            , String     dsmenu_posi
                            , String     link_menu
                            , long       tkfunzione
                            , String     profil_inse
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        if (ib_calcola_token){
            long ll_tk = getNew_token( );

            if (ll_tk <= 0){
                throw new Exception ("Error Keypool (cat_menu_posi): "+ll_tk );
            }

            this.tkmenu_posi = ll_tk;
            tkmenu_posi = this.tkmenu_posi;
        }



        l_query   = "";
        l_query  += " insert into pgmr.cat_menu_posi ( \n";
        l_query  += "             tkmenu_posi   \n";
        l_query  += "           , tkmenu   \n";
        l_query  += "           , nrposi   \n";
        l_query  += "           , dsmenu_posi   \n";
        l_query  += "           , link_menu   \n";
        l_query  += "           , tkfunzione   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , profil_inse   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (dsmenu_posi.equals("")) dsmenu_posi = null;
        if (link_menu.equals("")) link_menu = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;
        if (profil_inse.equals("")) profil_inse = null;


        ind = 1;
        if (tkmenu_posi == 0 && null_tkmenu_posi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkmenu_posi); ind += 1;
        } 
        if (tkmenu == 0 && null_tkmenu){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkmenu); ind += 1;
        } 
        if (nrposi == 0 && null_nrposi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, nrposi); ind += 1;
        } 
        pstm.setString    (ind, dsmenu_posi); ind += 1;
        pstm.setString    (ind, link_menu); ind += 1;
        if (tkfunzione == 0 && null_tkfunzione){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkfunzione); ind += 1;
        } 
        pstm.setString    (ind, cdazie); ind += 1;
        pstm.setString    (ind, cddipa); ind += 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setString    (ind, profil_inse); ind += 1;
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

        long ll_tk = f_tabkey.getTabkey( "cat_menu_posi" );

        return ll_tk;

    }




    /****
        executeUpdate: cat_menu_posi
    **/ 


    public int executeUpdate( long       tkmenu_posi
                            , long       tkmenu
                            , long       nrposi
                            , String     dsmenu_posi
                            , String     link_menu
                            , long       tkfunzione
                            , String     profil_inse
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.cat_menu_posi  \n";
        l_query  += "         set tkmenu = ?  \n";
        l_query  += "           , nrposi = ?  \n";
        l_query  += "           , dsmenu_posi = ?  \n";
        l_query  += "           , link_menu = ?  \n";
        l_query  += "           , tkfunzione = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , profil_inse = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "  where tkmenu_posi = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (dsmenu_posi.equals("")) dsmenu_posi = null;
        if (link_menu.equals("")) link_menu = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;
        if (profil_inse.equals("")) profil_inse = null;


        ind = 1;
        if (tkmenu == 0 && null_tkmenu){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkmenu); ind += 1;
        } 
        if (nrposi == 0 && null_nrposi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, nrposi); ind += 1;
        } 
        pstm.setString    (ind, dsmenu_posi); ind += 1;
        pstm.setString    (ind, link_menu); ind += 1;
        if (tkfunzione == 0 && null_tkfunzione){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkfunzione); ind += 1;
        } 
        pstm.setString    (ind, profil); ind += 1;
        pstm.setString    (ind, profil_inse); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;

        pstm.setLong      (ind, tkmenu_posi); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.tkmenu_posi = tkmenu_posi; 

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public long       tkmenu_posi = 0; 






    // --- Impostando a false queste prop. il def. è 0

    public boolean ib_calcola_token = true;
    public boolean null_tkmenu_posi = true;
    public boolean null_tkmenu = true;
    public boolean null_nrposi = true;
    public boolean null_tkfunzione = true;







}

