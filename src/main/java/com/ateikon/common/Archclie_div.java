package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class Archclie_div extends Atk_sql {

    public Archclie_div() {
        
        super();
    }


    public boolean isDivisione(String tkclie, String cddivisione) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select *                      \n";
		l_query  += "   from pgmr.archclie_div  \n";
		l_query  += "  where tkclie = ?             \n";
		l_query  += "    and cddivisione = ?        \n";
                                                        
		pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setString(ind, tkclie); ind += 1;
        pstm.setString(ind, cddivisione); ind += 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){
            pstm.close();
            pstm = null;
            return true;
        }

        return false;


    }




}
