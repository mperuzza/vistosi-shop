
package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;





public class Clieclass extends Atk_sql {

    
    public Clieclass() {

        super();
    }



    public ResultSet getKey(String cdclac) throws Exception {

        int               ind     = 0;
        int               tot_rec = 0;
        PreparedStatement pstm    = null;
        ResultSet         rs      = null;


        l_query   = "";
        l_query  += " select *                     \n";
        l_query  += "   from pgmr.clieclass \n";
        l_query  += "  where cdclac = ?              \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString( ind, cdclac); ind += 1;

        rs = pstm.executeQuery();


        return rs;
    }


    public ResultSet getDescr() throws Exception {

        int               ind     = 0;
        int               tot_rec = 0;
        PreparedStatement pstm    = null;
        ResultSet         rs      = null;


        l_query   = "";
        l_query  += " select *                     \n";
        l_query  += "   from pgmr.clieclass        \n";
        l_query  += "  where cdazie = '"+cdazie+"' \n";
        l_query  += "  order by dsclac             \n";

        pstm = setQuery( l_query ) ;

        ind = 1;

        rs = pstm.executeQuery();


        return rs;
    }











}
