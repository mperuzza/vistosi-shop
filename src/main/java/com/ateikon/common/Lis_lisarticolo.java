package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class Lis_lisarticolo extends Atk_sql {

    public Lis_lisarticolo() {
        
        super();
    }


    public ResultSet getLis_articolo(String cdarti, String cdlist ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select *                                          \n";
		l_query  += "   from pgmr.lis_lisarticolo                   \n";
		l_query  += "  where cdarti = ?                                 \n";
		l_query  += "    and cdlist = ?                                 \n";
		l_query  += "    and cdazie = ?                                 \n";
		l_query  += "    and dtinva = ( select max(dtinva)              \n";
		l_query  += "                     from pgmr.lis_lisarticolo \n";
		l_query  += "                    where cdarti = ?               \n";
		l_query  += "                      and cdlist = ?               \n";
		l_query  += "                      and cdazie = ?               \n";
		l_query  += "                      and dtinva <= ? )            \n";

		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString   (ind, cdarti); ind += 1;
        pstm.setString   (ind, cdlist); ind += 1;
        pstm.setString   (ind, cdazie); ind += 1;

        pstm.setString   (ind, cdarti); ind += 1;
        pstm.setString   (ind, cdlist); ind += 1;
        pstm.setString   (ind, cdazie); ind += 1;
        pstm.setTimestamp(ind, oggi); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }


    public ResultSet getPrezzo(String cdarti, String cdlist ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select prezzo                                     \n";
		l_query  += "   from pgmr.lis_lisarticolo                   \n";
		l_query  += "  where cdarti = ?                                 \n";
		l_query  += "    and cdlist = ?                                 \n";
		l_query  += "    and cdazie = ?                                 \n";
		l_query  += "    and dtinva = ( select max(dtinva)              \n";
		l_query  += "                     from pgmr.lis_lisarticolo \n";
		l_query  += "                    where cdarti = ?               \n";
		l_query  += "                      and cdlist = ?               \n";
		l_query  += "                      and cdazie = ?               \n";
		l_query  += "                      and dtinva <= ? )            \n";

		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString   (ind, cdarti); ind += 1;
        pstm.setString   (ind, cdlist); ind += 1;
        pstm.setString   (ind, cdazie); ind += 1;

        pstm.setString   (ind, cdarti); ind += 1;
        pstm.setString   (ind, cdlist); ind += 1;
        pstm.setString   (ind, cdazie); ind += 1;
        pstm.setTimestamp(ind, oggi); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }




}
