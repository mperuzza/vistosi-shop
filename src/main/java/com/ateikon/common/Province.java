package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class Province extends Atk_sql {

    public Province() {
        super();
    }



    public ResultSet getKey(String cdprov) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select cdprov                   \n";
		l_query  += "      , cdprov_m                 \n";
		l_query  += "      , dsprov                   \n";
		l_query  += "   from pgmr.province            \n";
		l_query  += "  where cdprov = '"+cdprov+"'  \n";

		pstm = setQuery( l_query ) ;

        ind = 1;

        rs = pstm.executeQuery();

        return rs;


    }
    
    
    public ResultSet getDropdown() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select cdprov, dsprov  \n";
		l_query  += "   from pgmr.province   \n";
		l_query  += "  order by dsprov       \n";

		pstm = setQuery( l_query ) ;

        ind = 1;

        rs = pstm.executeQuery();

        return rs;


    }
    
    public ResultSet getProvince(String order_by) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select *                 \n";
		l_query  += "   from pgmr.province \n";
		l_query  += "  order by "+order_by+"   \n";

		pstm = setQuery( l_query ) ;

        ind = 1;

        rs = pstm.executeQuery();

        return rs;


    }

    public String getDescr(String cdprov) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        String dsprov = "";

        l_query   = "";
		l_query  += " select dsprov       \n";
		l_query  += "   from pgmr.province \n";
		l_query  += "  where cdprov = ?   \n";

		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString( ind, cdprov ); ind += 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            if (rs.getObject("dsprov")!=null ) dsprov = rs.getString("dsprov");

        }

        return dsprov;


    }



    public String getDescr_m(String cdprov) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        String cdprov_m = "";

        l_query   = "";
		l_query  += " select cdprov_m       \n";
		l_query  += "   from pgmr.province \n";
		l_query  += "  where cdprov = ?   \n";

		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString( ind, cdprov ); ind += 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            if (rs.getObject("cdprov_m")!=null ) cdprov_m = rs.getString("cdprov_m");

        }

        return cdprov_m;


    }



}
