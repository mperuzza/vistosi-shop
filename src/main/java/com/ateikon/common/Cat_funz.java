package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Cat_funz extends com.ateikon.standard.Cat_funz {


    public Cat_funz() {

        super();
    }






    public String getCdfunzione_m( long tkfunzione
                                                    ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        l_query   = "";
        l_query  += " select cdfunzione_m                 \n";
        l_query  += "   from pgmr.cat_funz               \n";
        l_query  += "  where tkfunzione = "+tkfunzione+"  \n";
        
        String cdfunzione_m = sql_String(l_query);

        return cdfunzione_m;

    }








    public ResultSet getFunzioni( ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select *               \n";
        l_query  += "   from pgmr.cat_funz  \n";
        l_query  += "  order by tkfunzione \n";

        pstm = setQuery( l_query ) ;

        ind = 1;

        rs = pstm.executeQuery();


        return rs;

    }


    public ResultSet getDropdown( ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select tkfunzione      \n";
        l_query  += "      , dsfunzione      \n";
        l_query  += "   from pgmr.cat_funz  \n";
        l_query  += "  order by tkfunzione   \n";

        pstm = setQuery( l_query ) ;

        ind = 1;

        rs = pstm.executeQuery();


        return rs;

    }



    public int count( ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select count(1)        \n";
        l_query  += "   from pgmr.cat_funz  \n";

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;

        rs = pstm.executeQuery();


        if (rs!=null && rs.next() && rs.getObject(1)!=null){
            tot_rec = rs.getInt(1);
        }

        pstm.close();

        return tot_rec;

    }


    public int executeDelete( long tkfunzione) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;



        l_query   = "";
        l_query  += " delete                    \n";
        l_query  += "   from pgmr.cat_funz_user \n";
        l_query  += "  where tkfunzione = ?      \n";

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;

        pstm.setLong(ind,tkfunzione ); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;


        l_query   = "";
        l_query  += " delete                \n";
        l_query  += "   from pgmr.cat_funz \n";
        l_query  += " where tkfunzione = ?  \n";

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;

        pstm.setLong(ind,tkfunzione ); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        return tot_rec;

    }






}

