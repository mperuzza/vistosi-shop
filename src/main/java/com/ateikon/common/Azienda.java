package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import java.util.Date;

public class Azienda extends Atk_sql {

    public Azienda() {
        
        super();
    }



    public ResultSet getKey(String cdazie) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select *            \n";
		l_query  += "   from pgmr.azienda \n";
		l_query  += "  where cdazie = ?   \n";

		pstm = setQuery( l_query ) ;

        ind = 1;

        pstm.setString(ind, cdazie); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }


}
