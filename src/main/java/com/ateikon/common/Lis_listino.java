package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class Lis_listino extends Atk_sql {

    public Lis_listino() {
        
        super();
    }


    public ResultSet getCdlist( String cdlist ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select vacodi                              \n";
		l_query  += "      , ivato                               \n";
		l_query  += "   from pgmr.lis_listino                    \n";
		l_query  += "  where cdlist = ?                          \n";
		l_query  += "    and cdazie = ?                          \n";
		l_query  += "    and dtinva = ( select max(dtinva)       \n";
		l_query  += "                     from pgmr.lis_listino  \n";
		l_query  += "                    where cdlist = ?        \n";
		l_query  += "                      and cdazie = ?        \n";
		l_query  += "                      and dtinva <= ? )     \n";

		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString   (ind, cdlist); ind += 1;
        pstm.setString   (ind, cdazie); ind += 1;

        pstm.setString   (ind, cdlist); ind += 1;
        pstm.setString   (ind, cdazie); ind += 1;
        pstm.setTimestamp(ind, oggi); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }




    public ResultSet getVacodi( String cdlist ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select b.vadesc as dsvacodi   \n";
		l_query  += "      , b.vacodi               \n";
		l_query  += "      , b.vacodi_m             \n";
		l_query  += "   from pgmr.lis_listino a     \n";
		l_query  += "      , pgmr.valueste   b      \n";
		l_query  += "  where a.vacodi = b.vacodi    \n";
		l_query  += "    and a.cdlist = ?           \n";
		l_query  += "    and a.cdazie = ?           \n";

		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString   (ind, cdlist); ind += 1;
        pstm.setString   (ind, cdazie); ind += 1;


        rs = pstm.executeQuery();


        return rs;


    }



    public ResultSet getListini( ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select a.cdlist            \n";
		l_query  += "      , a.dslist            \n";
		l_query  += "   from pgmr.lis_listino a  \n";
		l_query  += "  where a.cdazie = ?        \n";
		l_query  += "  order by a.dslist         \n";

		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString   (ind, cdazie); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }



    /***


    */

    public String getDslist (String cdlist ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        String ls_dslist = "-";

        l_query   = "";
		l_query  += " select a.dslist            \n";
		l_query  += "   from pgmr.lis_listino a  \n";
		l_query  += "  where a.cdlist = ?        \n";

		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString   (ind, cdlist); ind += 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){
            if (rs.getObject("dslist")!=null)  ls_dslist = rs.getString("dslist");
        }


        return ls_dslist ;

    }







}
