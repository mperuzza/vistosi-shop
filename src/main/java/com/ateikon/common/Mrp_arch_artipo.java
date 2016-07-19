package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;





public class Mrp_arch_artipo extends Atk_sql {

    
    public Mrp_arch_artipo() {

        super();
    }


    public ResultSet getDropdown() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select cdartipo, dsartipo      \n";
        l_query  += "   from pgmr.mrp_arch_artipo    \n";
        l_query  += "  order by dsartipo             \n";

        pstm = setQuery( l_query ) ;

        ind = 1;

        rs = pstm.executeQuery();

        return rs;


    }



}
