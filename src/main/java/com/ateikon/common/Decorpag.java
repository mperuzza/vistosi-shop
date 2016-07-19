package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class Decorpag extends Atk_sql {

    public Decorpag() {
        
        super();
    }


    public String getDescr(String cdvalue, String cdling) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        String descr = "";

        l_query   = "";
		l_query  += " select *                    \n";
		l_query  += "   from pgmr.decorpag  \n";
		l_query  += "  where cddpag = ?         \n";

		pstm =  m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setString(ind, cdvalue); ind += 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next() && rs.getObject("dsdpag")!=null){
            
            descr = rs.getString("dsdpag");
            
        }
        pstm.close();

        return descr;


    }





}
