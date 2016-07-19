package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class Kg_colori extends Atk_sql {

    public Kg_colori() {
        
        super();
    }


 
    public String getDescr(String cdcolint) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        String dscolint  = "";

        l_query   = "";
        l_query  += " select dscolint \n";
        l_query  += "   from pgmr.kg_colori \n";
        l_query  += "  where cdcolint = ? \n";

	pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString( ind, cdcolint); ind += 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            if (rs.getObject("dscolint")!=null ) dscolint = rs.getString("dscolint");

        }

        return dscolint;


    }






}
