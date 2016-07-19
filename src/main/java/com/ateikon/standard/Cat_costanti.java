package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Cat_costanti extends Atk_sql {

    public Cat_costanti() {

        super();
    }




    /****
        getAll: cat_costanti
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.cat_costanti  \n";

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: cat_costanti
    **/ 

    public ResultSet getKey( long       tkcost
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.cat_costanti  \n";
        l_query  += "  where tkcost = ? \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setLong      (ind, tkcost); ind += 1;

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: cat_costanti
    **/ 


    public com.ateikon.structure.Rec_cat_costanti  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_cat_costanti lstr = new com.ateikon.structure.Rec_cat_costanti();

        if (rs == null) return lstr;
        if (rs.getObject("tkcost")!=null) lstr.tkcost = rs.getLong      ("tkcost"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cdappl")!=null) lstr.cdappl = rs.getString    ("cdappl"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("costname")!=null) lstr.costname = rs.getString    ("costname"); 
        if (rs.getObject("costlabel")!=null) lstr.costlabel = rs.getString    ("costlabel"); 
        if (rs.getObject("costvalue")!=null) lstr.costvalue = rs.getString    ("costvalue"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("cdagen")!=null) lstr.cdagen = rs.getString    ("cdagen"); 

        return lstr;
    }




    /****
        execute: cat_costanti
    **/ 


    public int execute ( com.ateikon.structure.Rec_cat_costanti astr) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec =  execute ( astr.tkcost
                          , astr.cdappl
                          , astr.costname
                          , astr.costlabel
                          , astr.costvalue
                          , astr.cdagen
                           );

        return tot_rec;
    }




    /****
        execute: cat_costanti
    **/ 


    public int execute ( long       tkcost
                       , String     cdappl
                       , String     costname
                       , String     costlabel
                       , String     costvalue
                       , String     cdagen
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " select tkcost\n";
        l_query  += "   from pgmr.cat_costanti  \n";
        l_query  += "  where tkcost = ? \n";


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (tkcost == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkcost); ind += 1;
        } 

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            pstm.close();
            tot_rec = executeUpdate ( tkcost
                                    , cdappl
                                    , costname
                                    , costlabel
                                    , costvalue
                                    , cdagen
                                    );
        }else {

            pstm.close();
            tot_rec = executeInsert ( tkcost
                                    , cdappl
                                    , costname
                                    , costlabel
                                    , costvalue
                                    , cdagen
                                    );
        }

        if (tot_rec != 1){

            m_connection.rollback();
            throw new Exception("Errore Agg. cat_costanti! ");
        }

        return tot_rec;
    }




    /****
        executeInsert: cat_costanti
    **/ 


    public int executeInsert( long       tkcost
                            , String     cdappl
                            , String     costname
                            , String     costlabel
                            , String     costvalue
                            , String     cdagen
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        if (ib_calcola_token){
            long ll_tk = getNew_token( );

            if (ll_tk <= 0){
                throw new Exception ("Error Keypool (cat_costanti): "+ll_tk );
            }

            this.tkcost = ll_tk;
            tkcost = this.tkcost;
        }



        l_query   = "";
        l_query  += " insert into pgmr.cat_costanti ( \n";
        l_query  += "             tkcost   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cdappl   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , costname   \n";
        l_query  += "           , costlabel   \n";
        l_query  += "           , costvalue   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "           , cdagen   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cdazie.equals("")) cdazie = null;
        if (cdappl.equals("")) cdappl = null;
        if (profil.equals("")) profil = null;
        if (costname.equals("")) costname = null;
        if (costlabel.equals("")) costlabel = null;
        if (costvalue.equals("")) costvalue = null;
        if (cddipa.equals("")) cddipa = null;
        if (cdagen.equals("")) cdagen = null;


        ind = 1;
        if (tkcost == 0 && null_tkcost){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkcost); ind += 1;
        } 
        pstm.setString    (ind, cdazie); ind += 1;
        pstm.setString    (ind, cdappl); ind += 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setString    (ind, costname); ind += 1;
        pstm.setString    (ind, costlabel); ind += 1;
        pstm.setString    (ind, costvalue); ind += 1;
        pstm.setString    (ind, cddipa); ind += 1;
        pstm.setTimestamp (ind, dtinse); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;
        pstm.setString    (ind, cdagen); ind += 1;

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

        long ll_tk = f_tabkey.getTabkey( "cat_costanti" );

        return ll_tk;

    }




    /****
        executeUpdate: cat_costanti
    **/ 


    public int executeUpdate( long       tkcost
                            , String     cdappl
                            , String     costname
                            , String     costlabel
                            , String     costvalue
                            , String     cdagen
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.cat_costanti  \n";
        l_query  += "         set cdappl = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , costname = ?  \n";
        l_query  += "           , costlabel = ?  \n";
        l_query  += "           , costvalue = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "           , cdagen = ?  \n";
        l_query  += "  where tkcost = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cdazie.equals("")) cdazie = null;
        if (cdappl.equals("")) cdappl = null;
        if (profil.equals("")) profil = null;
        if (costname.equals("")) costname = null;
        if (costlabel.equals("")) costlabel = null;
        if (costvalue.equals("")) costvalue = null;
        if (cddipa.equals("")) cddipa = null;
        if (cdagen.equals("")) cdagen = null;


        ind = 1;
        pstm.setString    (ind, cdappl); ind += 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setString    (ind, costname); ind += 1;
        pstm.setString    (ind, costlabel); ind += 1;
        pstm.setString    (ind, costvalue); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;
        pstm.setString    (ind, cdagen); ind += 1;

        pstm.setLong      (ind, tkcost); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.tkcost = tkcost; 

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public long       tkcost = 0; 






    // --- Impostando a false queste prop. il def. è 0

    public boolean ib_calcola_token = true;
    public boolean null_tkcost = true;







}

