package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class Scontoimb extends Atk_sql {

    public Scontoimb() {
        
        super();


    }



    public String getFtiposconto( String cdartipo
                                , String cdcllist
                                                    ) throws Exception {
        
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        String ls_ = "";

        l_query   = "";
		l_query  += "  select ftiposconto         \n";
		l_query  += "    from pgmr.scontoimb  \n";
		l_query  += "   where cdtipa   = ?        \n";
		l_query  += "     and cdcllist = ?        \n";
		l_query  += "     and cdazie   = ?        \n";


		pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setString( ind, cdartipo ); ind += 1;
        pstm.setString( ind, cdcllist ); ind += 1;
        pstm.setString( ind, cdazie   ); ind += 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next() && rs.getObject(1)!=null){
            
            ls_ = rs.getString(1);
        }
        pstm.close();

        return ls_;


    }



}
