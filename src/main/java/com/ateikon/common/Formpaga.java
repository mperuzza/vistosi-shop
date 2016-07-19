package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class Formpaga extends Atk_sql {

    public Formpaga() {
        
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
		l_query  += "   from pgmr.formpaga  \n";
		l_query  += "  where pgcodi = ?         \n";

		pstm =  m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setString(ind, cdvalue); ind += 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next() && rs.getObject("pgdesc")!=null){
            
            descr = rs.getString("pgdesc");
            
        }
        pstm.close();

        return descr;


    }





}
