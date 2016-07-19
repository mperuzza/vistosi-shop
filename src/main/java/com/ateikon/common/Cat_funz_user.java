package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Cat_funz_user extends com.ateikon.standard.Cat_funz_user {


    public Cat_funz_user() {

        super();
    }




    /****
        executeUpdate: elwm_funz_user
    **/ 


    public int deleteFunzione( long       tkfunzione
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " delete from  pgmr.cat_funz_user  \n";
        l_query  += "  where tkfunzione = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setLong      (ind, tkfunzione); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        return tot_rec;

    }



    /****
        executeUpdate: elwm_funz_user
    **/ 


    public int deleteUser( long tkutente
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " delete from  pgmr.cat_funz_user  \n";
        l_query  += "  where tkutente = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setLong      (ind, tkutente); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        return tot_rec;

    }



}

