
package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;





public class Regioita extends Atk_sql {

    
    public Regioita() {

        super();
    }



    public ResultSet getKey(String cdregi) throws Exception {

        int               ind     = 0;
        int               tot_rec = 0;
        PreparedStatement pstm    = null;
        ResultSet         rs      = null;


        l_query   = "";
        l_query  += " select *                     \n";
        l_query  += "   from pgmr.regioita \n";
        l_query  += "  where cdregi = ?              \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString( ind, cdregi); ind += 1;

        rs = pstm.executeQuery();


        return rs;
    }


    public ResultSet getDescr() throws Exception {

        int               ind     = 0;
        int               tot_rec = 0;
        PreparedStatement pstm    = null;
        ResultSet         rs      = null;


        l_query   = "";
        l_query  += " select *                  \n";
        l_query  += "   from pgmr.regioita \n";
        l_query  += "  order by dsregi         \n";

        pstm = setQuery( l_query ) ;

        ind = 1;

        rs = pstm.executeQuery();


        return rs;
    }











}
