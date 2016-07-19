package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Mrp_arch_magazzi extends Atk_sql {

    public Mrp_arch_magazzi() {

        super();
    }




    /****
        getKey: mrp_arch_magazzi
    **/ 

    public ResultSet getKey( long       tkmaga
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.mrp_arch_magazzi  \n";
        l_query  += "  where tkmaga = ? \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setLong      (ind, tkmaga); ind += 1;

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        execute: mrp_arch_magazzi
    **/ 


    public int execute ( com.ateikon.structure.Rec_mrp_arch_magazzi astr) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        return execute ( astr.tkmaga
                       , astr.dsmaga
                        );


    }




    /****
        execute: mrp_arch_magazzi
    **/ 


    public int execute ( long       tkmaga
                       , String     dsmaga
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " select tkmaga\n";
        l_query  += "   from pgmr.mrp_arch_magazzi  \n";
        l_query  += "  where tkmaga = ? \n";


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setLong      (ind, tkmaga); ind += 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            pstm.close();
            tot_rec = executeUpdate ( tkmaga
                                    , dsmaga
                                    );
        }else {

            pstm.close();
            tot_rec = executeInsert ( tkmaga
                                    , dsmaga
                                    );
        }

        if (tot_rec != 1){

            m_connection.rollback();
            throw new Exception("Errore Agg. mrp_arch_magazzi! ");
        }

        return tot_rec;
    }




    /****
        executeInsert: mrp_arch_magazzi
    **/ 


    public int executeInsert( long       tkmaga
                            , String     dsmaga
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        F_tabkey f_tabkey = new F_tabkey();
        setProfilo((Atk_sql) f_tabkey );

        long ll_tk = f_tabkey.getTabkey( "mrp_arch_magazzi" );

        if (ll_tk <= 0){
            throw new Exception ("Error Keypool (mrp_arch_magazzi): "+ll_tk );
        }

        this.tkmaga = ll_tk;
        tkmaga = this.tkmaga;



        l_query   = "";
        l_query  += " insert into pgmr.mrp_arch_magazzi ( \n";
        l_query  += "             tkmaga   \n";
        l_query  += "           , dsmaga   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (dsmaga.equals("")) dsmaga = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;


        ind = 1;
        pstm.setLong      (ind, tkmaga); ind += 1;
        pstm.setString    (ind, dsmaga); ind += 1;
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




    /****
        executeUpdate: mrp_arch_magazzi
    **/ 


    public int executeUpdate( long       tkmaga
                            , String     dsmaga
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.mrp_arch_magazzi  \n";
        l_query  += "         set dsmaga = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "  where tkmaga = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (dsmaga.equals("")) dsmaga = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;


        ind = 1;
        pstm.setString    (ind, dsmaga); ind += 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;
        pstm.setLong      (ind, tkmaga); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public long       tkmaga = 0; 







}

