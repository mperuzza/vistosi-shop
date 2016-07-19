package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class Kg_tipotaglia extends Atk_sql {

    public Kg_tipotaglia() {
        
        super();
    }


    public ResultSet getTipotaglia(  String order_by ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select *                       \n";
		l_query  += "   from pgmr.kg_tipotaglia  \n";
		l_query  += "  order by "+order_by+"         \n";

                                                        
		pstm = setQuery( l_query ) ;

        ind = 1;

        rs = pstm.executeQuery();


        return rs;

    }


    public String getDescr( String cdtptaglia ) throws Exception {
        
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        String dstptaglia = "";

        l_query   = "";
		l_query  += " select a.*                         \n";
		l_query  += "   from pgmr.kg_tipotaglia a    \n";
		l_query  += "  where a.cdtptaglia      = ?           \n";

                                                        
		pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setString(ind, cdtptaglia ); ind += 1;

        rs = pstm.executeQuery();

        if (rs != null && rs.next() && rs.getObject("dstptaglia")!=null){

            dstptaglia =  rs.getString("dstptaglia");

        }
        pstm.close();
        pstm = null;

        return dstptaglia;

    }



}
