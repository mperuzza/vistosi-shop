package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import com.ateikon.function.*;

public class Kg_articolo_foto extends Atk_sql {

    public Kg_articolo_foto() {
        
        super();
    }


    public ResultSet getFoto(String cdarti, String cdtpfoto) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select foto                      \n";
		l_query  += "   from pgmr.kg_articolo_foto \n";
		l_query  += "  where cdarti = ?                \n";
		l_query  += "    and cdtpfoto = ?              \n";
		l_query  += "  order by nrposi                 \n";
		l_query  += "         , tkfoto desc            \n";

		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString(ind, cdarti); ind += 1;
        pstm.setString(ind, cdtpfoto); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }

    public int countFoto(String cdarti, String cdtpfoto) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select count(1)                  \n";
		l_query  += "   from pgmr.kg_articolo_foto \n";
		l_query  += "  where cdarti = ?                \n";
		l_query  += "    and cdtpfoto = ?              \n";

		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString(ind, cdarti); ind += 1;
        pstm.setString(ind, cdtpfoto); ind += 1;

        rs = pstm.executeQuery();

        if (rs != null && rs.next() && rs.getObject(1)!=null){
            return rs.getInt(1);
        }

        return 0;


    }

    
}
