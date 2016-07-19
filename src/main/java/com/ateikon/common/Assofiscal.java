package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class Assofiscal extends Atk_sql {

    public Assofiscal() {
        
        super();
    }



    public ResultSet getTafasc(String cdfisc) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select tafasc            \n";
		l_query  += "      , dsfisc            \n";
		l_query  += "   from pgmr.assofiscal   \n";
		l_query  += " where cdfisc = ?         \n";

		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString(ind, cdfisc); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }

    public ResultSet getCdfisc(String cdfisc) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select *                   \n";
		l_query  += "   from pgmr.assofiscal \n";
		l_query  += " where cdfisc = ?           \n";

		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString(ind, cdfisc); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }





    public String getDescr_m(String  cdfisc_m) throws Exception {
        
        return getDescr("", cdfisc_m);

    }

    public String getDescr(String  cdfisc) throws Exception {
        
        return getDescr(cdfisc, "");

    }

    public String getDescr(String cdfisc, String  cdfisc_m) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        String descr  = "";

        l_query   = "";
		l_query  += " select dsfisc                \n";
		l_query  += "   from pgmr.assofiscal   \n";
        l_query  += "  where 1=1                   \n";
        if (!cdfisc.equals("")){
            l_query  += "  and cdfisc = ?          \n";
        }else {
		    l_query  += "  and cdfisc_m = ?        \n";
        }

		pstm = setQuery( l_query ) ;

        ind = 1;

        if (!cdfisc.equals("")){
            pstm.setString( ind, cdfisc ); ind += 1;
        }else {
            pstm.setString( ind, cdfisc_m ); ind += 1;
        }

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            if (rs.getObject(1)!=null ) descr = rs.getString(1);

        }

        return descr;


    }






}
