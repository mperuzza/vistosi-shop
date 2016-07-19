package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class Kg_tipomapr extends Atk_sql {

    public Kg_tipomapr() {
        
        super();
    }





    public int countTipomapr(String cdgptipm) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select count(*)               \n";
		l_query  += "   from pgmr.kg_tipomapr       \n";
		l_query  += " where cdazie = '"+cdazie+"'   \n";
		l_query  += "   and fgfiltroweb = 'S'       \n";
        if (!cdgptipm.equals("")){
		l_query  += " and cdgptipm = '"+cdgptipm+"' \n";
        }

		tot_rec = sql_int( l_query ) ;

        return tot_rec;

    }


    public ResultSet getTipomapr(String cdgptipm, String order_by) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select cdtipm                 \n";
		l_query  += "      , dstipm                 \n";
		l_query  += "      , cdtipm_m               \n";
		l_query  += "   from pgmr.kg_tipomapr   \n";
		l_query  += " where cdazie = ?              \n";
		l_query  += "   and fgfiltroweb = 'S'       \n";
        if (!cdgptipm.equals("")){
		l_query  += " and cdgptipm = ?              \n";
        }
		l_query  += "  order by "+order_by+"        \n";

		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString(ind, cdazie); ind += 1;

        if (!cdgptipm.equals("")){
            pstm.setString(ind, cdgptipm); ind += 1;
        }

        rs = pstm.executeQuery();

        return rs;


    }

    public ResultSet getIva_assofisc(String cdtipm) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " SELECT cdivav               \n";
        l_query  += "      , cdfiscv              \n";
        l_query  += "   FROM pgmr.kg_tipomapr \n";
        l_query  += "  WHERE cdtipm = ?           \n";
        l_query  += "    and cdazie = ?           \n";

		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString(ind, cdtipm); ind += 1;
        pstm.setString(ind, cdazie); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }



    public ResultSet getCdtipm(String cdtipm) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select *                    \n";
		l_query  += "   from pgmr.kg_tipomapr \n";
		l_query  += "  where cdtipm = ?           \n";
        l_query  += "    and cdazie = ?       \n";

		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString( ind, cdtipm ); ind += 1;
        pstm.setString( ind, cdazie ); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }

    public String getDescr_m(String  cdtipm_m) throws Exception {
        
        return getDescr("", cdtipm_m);

    }

    public String getDescr(String  cdtipm) throws Exception {
        
        return getDescr(cdtipm, "");

    }

    public String getDescr(String cdtipm, String  cdtipm_m) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        String dstipm  = "";

        l_query   = "";
		l_query  += " select dstipm         \n";
		l_query  += "   from pgmr.kg_tipomapr  \n";
        l_query  += "  where cdazie = ? \n";
        if (!cdtipm.equals("")){
            l_query  += "  and cdtipm = ? \n";
        }else {
		    l_query  += "  and cdtipm_m = ? \n";
        }

		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString( ind, cdazie); ind += 1;

        if (!cdtipm.equals("")){
            pstm.setString( ind, cdtipm ); ind += 1;
        }else {
            pstm.setString( ind, cdtipm_m ); ind += 1;
        }

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            if (rs.getObject("dstipm")!=null ) dstipm = rs.getString("dstipm");

        }

        return dstipm;


    }






}
