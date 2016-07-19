package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class Kg_valuta extends Atk_sql {

    public Kg_valuta() {
        
        super();
    }


    public ResultSet getVacodi( String vacodi) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select *                                       \n";
		l_query  += "   from pgmr.kg_valuta                      \n";
		l_query  += "  where vacodi = ?                              \n";

		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString   (ind, vacodi ); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }




}
