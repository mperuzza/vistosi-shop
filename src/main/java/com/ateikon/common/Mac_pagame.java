package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class Mac_pagame extends Atk_sql {

    public Mac_pagame() {
        
        super();
    }


    public ResultSet getCdpagame(String cdpagame) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select *                    \n";
		l_query  += "   from pgmr.mac_pagame  \n";
		l_query  += "  where cdpagame = ?         \n";

		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString(ind, cdpagame); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }



    public String getDspagame(String cdpagame) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        
        
        l_query   = "";
		l_query  += " select dspagame                       \n";
		l_query  += "   from pgmr.mac_pagame              \n";
		l_query  += "  where cdpagame = '"+cdpagame+"'    \n";

		String ls_ = sql_String(l_query);

        return ls_;


    }



}
