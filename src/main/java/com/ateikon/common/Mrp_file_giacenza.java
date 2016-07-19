package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class Mrp_file_giacenza extends Atk_sql {

    public Mrp_file_giacenza() {
        
        super();
    }


    public ResultSet getGiacenza(String cdarti) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        String descr = "";

        l_query   = "";
		l_query  += " select sum(qtagia) as qtagia \n";
		l_query  += "      , sum(qtaoa)  as qtaoa  \n";
		l_query  += "      , sum(qtaov)  as qtaov  \n";
		l_query  += "   from pgmr.mrp_file_giacenza     \n";
		l_query  += "  where cdarti = ?            \n";
		l_query  += "    and cdazie = ?            \n";

		pstm =  setQuery( l_query ) ;

        ind = 1;
        pstm.setString(ind, cdarti ); ind += 1;
        pstm.setString(ind, cdazie ); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }





}
