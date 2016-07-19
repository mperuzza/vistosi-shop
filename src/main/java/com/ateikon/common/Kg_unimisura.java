package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class Kg_unimisura extends Atk_sql {

    public Kg_unimisura() {
        
        super();
    }


    public ResultSet getCdunim(String cdunim) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select *                      \n";
		l_query  += "   from pgmr.kg_unimisura  \n";
		l_query  += "  where cdunim = ?             \n";

		pstm = setQuery( l_query ) ;

        ind = 1;

        pstm.setString(ind, cdunim); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }


}
