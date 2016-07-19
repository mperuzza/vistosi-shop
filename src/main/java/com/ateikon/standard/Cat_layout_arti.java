package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Cat_layout_arti extends Atk_sql {

    public Cat_layout_arti() {

        super();
    }




    /****
        getAll: cat_layout_arti
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.cat_layout_arti  \n";

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: cat_layout_arti
    **/ 

    public ResultSet getKey( long       id
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.cat_layout_arti  \n";
        l_query  += "  where id = ? \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setLong      (ind, id); ind += 1;

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: cat_layout_arti
    **/ 


    public com.ateikon.structure.Rec_cat_layout_arti  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_cat_layout_arti lstr = new com.ateikon.structure.Rec_cat_layout_arti();

        if (rs == null) return lstr;
        if (rs.getObject("id")!=null) lstr.id = rs.getLong      ("id"); 
        if (rs.getObject("id_layout")!=null) lstr.id_layout = rs.getLong      ("id_layout"); 
        if (rs.getObject("cdarti")!=null) lstr.cdarti = rs.getString    ("cdarti"); 
        if (rs.getObject("img")!=null) lstr.img = rs.getString    ("img"); 
        if (rs.getObject("nrposi")!=null) lstr.nrposi = rs.getLong      ("nrposi"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtwebsync")!=null) lstr.dtwebsync = rs.getTimestamp ("dtwebsync"); 

        return lstr;
    }




    /****
        execute: cat_layout_arti
    **/ 


    public int execute ( com.ateikon.structure.Rec_cat_layout_arti astr) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec =  execute ( astr.id
                          , astr.id_layout
                          , astr.cdarti
                          , astr.img
                          , astr.nrposi
                          , astr.dtwebsync
                           );

        return tot_rec;
    }




    /****
        execute: cat_layout_arti
    **/ 


    public int execute ( long       id
                       , long       id_layout
                       , String     cdarti
                       , String     img
                       , long       nrposi
                       , Timestamp  dtwebsync
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " select id\n";
        l_query  += "   from pgmr.cat_layout_arti  \n";
        l_query  += "  where id = ? \n";


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (id == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, id); ind += 1;
        } 

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            pstm.close();
            tot_rec = executeUpdate ( id
                                    , id_layout
                                    , cdarti
                                    , img
                                    , nrposi
                                    , dtwebsync
                                    );
        }else {

            pstm.close();
            tot_rec = executeInsert ( id
                                    , id_layout
                                    , cdarti
                                    , img
                                    , nrposi
                                    , dtwebsync
                                    );
        }

        if (tot_rec != 1){

            m_connection.rollback();
            throw new Exception("Errore Agg. cat_layout_arti! ");
        }

        return tot_rec;
    }




    /****
        executeInsert: cat_layout_arti
    **/ 


    public int executeInsert( long       id
                            , long       id_layout
                            , String     cdarti
                            , String     img
                            , long       nrposi
                            , Timestamp  dtwebsync
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        if (ib_calcola_token){
            long ll_tk = getNew_token( );

            if (ll_tk <= 0){
                throw new Exception ("Error Keypool (cat_layout_arti): "+ll_tk );
            }

            this.id = ll_tk;
            id = this.id;
        }



        l_query   = "";
        l_query  += " insert into pgmr.cat_layout_arti ( \n";
        l_query  += "             id   \n";
        l_query  += "           , id_layout   \n";
        l_query  += "           , cdarti   \n";
        l_query  += "           , img   \n";
        l_query  += "           , nrposi   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtwebsync   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cdarti.equals("")) cdarti = null;
        if (img.equals("")) img = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;


        ind = 1;
        if (id == 0 && null_id){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, id); ind += 1;
        } 
        if (id_layout == 0 && null_id_layout){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, id_layout); ind += 1;
        } 
        pstm.setString    (ind, cdarti); ind += 1;
        pstm.setString    (ind, img); ind += 1;
        if (nrposi == 0 && null_nrposi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, nrposi); ind += 1;
        } 
        pstm.setString    (ind, cdazie); ind += 1;
        pstm.setString    (ind, cddipa); ind += 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;
        pstm.setTimestamp (ind, dtinse); ind += 1;
        pstm.setTimestamp (ind, dtwebsync); ind += 1;

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

        long ll_tk = f_tabkey.getTabkey( "cat_layout_arti" );

        return ll_tk;

    }




    /****
        executeUpdate: cat_layout_arti
    **/ 


    public int executeUpdate( long       id
                            , long       id_layout
                            , String     cdarti
                            , String     img
                            , long       nrposi
                            , Timestamp  dtwebsync
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.cat_layout_arti  \n";
        l_query  += "         set id_layout = ?  \n";
        l_query  += "           , cdarti = ?  \n";
        l_query  += "           , img = ?  \n";
        l_query  += "           , nrposi = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "           , dtwebsync = ?  \n";
        l_query  += "  where id = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cdarti.equals("")) cdarti = null;
        if (img.equals("")) img = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;


        ind = 1;
        if (id_layout == 0 && null_id_layout){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, id_layout); ind += 1;
        } 
        pstm.setString    (ind, cdarti); ind += 1;
        pstm.setString    (ind, img); ind += 1;
        if (nrposi == 0 && null_nrposi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, nrposi); ind += 1;
        } 
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;
        pstm.setTimestamp (ind, dtwebsync); ind += 1;

        pstm.setLong      (ind, id); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.id = id; 

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public long       id = 0; 






    // --- Impostando a false queste prop. il def. è 0

    public boolean ib_calcola_token = true;
    public boolean null_id = true;
    public boolean null_id_layout = true;
    public boolean null_nrposi = true;







}

