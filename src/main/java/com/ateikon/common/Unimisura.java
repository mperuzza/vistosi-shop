package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class Unimisura extends Atk_sql {

    public Unimisura() {
        
        super();
            
    }


    public ResultSet getUnimisura(String order_by) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select *                 \n";
		l_query  += "   from pgmr.unimisura       \n";
		l_query  += "  order by "+order_by+"   \n";

		pstm = setQuery( l_query ) ;

        ind = 1;

        rs = pstm.executeQuery();

        return rs;


    }

    public ResultSet getDescr(String cdunim_m) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        String dsunim = "";

        l_query   = "";
		l_query  += " select *       \n";
		l_query  += "   from pgmr.unimisura     \n";
		l_query  += "  where cdunim_m = ?   \n";

		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString( ind, cdunim_m ); ind += 1;

        rs = pstm.executeQuery();


        return rs;


    }

    public ResultSet getCdunim(String cdunim) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        String dsunim = "";

        l_query   = "";
		l_query  += " select *       \n";
		l_query  += "   from pgmr.unimisura     \n";
		l_query  += "  where cdunim = ?   \n";

		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString( ind, cdunim ); ind += 1;

        rs = pstm.executeQuery();


        return rs;


    }

}
