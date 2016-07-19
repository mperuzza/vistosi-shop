package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class Mis_marca extends Atk_sql {

    public Mis_marca() {
        
        super();
    }


    public ResultSet getMarca(String order_by) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select *                     \n";
		l_query  += "   from pgmr.mis_marca  \n";
		l_query  += "  order by "+order_by+"       \n";

		pstm = setQuery( l_query ) ;

        ind = 1;

        rs = pstm.executeQuery();

        return rs;


    }

    public ResultSet getMarca_of_gptipomapr(String cdgptipm, String order_by) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;



        l_query   = "";
		l_query  += " select *                                                 \n";
		l_query  += "   from pgmr.mis_marca                                \n";
		l_query  += "  where fgfiltroweb = 'S'                                 \n";
		l_query  += "    and cdmarca in (                                      \n";
		l_query  += "                     select distinct(cdmarca)             \n";
		l_query  += "                       from pgmr.kg_articolo      b   \n";
		l_query  += "                          , pgmr.kg_tipomapr_cat  c   \n";
		l_query  += "                       where b.cdtipmc  = c.cdtipmc       \n";
		l_query  += "                         and c.cdgptipm = ?               \n";
		l_query  += "                                                          \n";
		l_query  += "                     )                                    \n";
		l_query  += "  order by "+order_by+"                                   \n";



		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString(ind, cdgptipm); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }


    public ResultSet getCdmarca(String cdmarca) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select cdmarca            \n";
		l_query  += "      , cdmarca_m          \n";
		l_query  += "      , dsmarca            \n";
		l_query  += "   from pgmr.mis_marca \n";
		l_query  += "  where cdmarca = ?        \n";

		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString( ind, cdmarca ); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }

    public String getDescr_m(String  cdmarca_m) throws Exception {
        
        return getDescr("", cdmarca_m);

    }

    public String getDescr(String  cdmarca) throws Exception {
        
        return getDescr(cdmarca, "");

    }

    public String getDescr(String cdmarca, String  cdmarca_m) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        String dsmarca  = "";

        l_query   = "";
		l_query  += " select dsmarca         \n";
		l_query  += "   from pgmr.mis_marca  \n";
        if (!cdmarca.equals("")){
            l_query  += "  where cdmarca = ? \n";
        }else {
		    l_query  += "  where cdmarca_m = ? \n";
        }

		pstm = setQuery( l_query ) ;

        ind = 1;
        if (!cdmarca.equals("")){
            pstm.setString( ind, cdmarca ); ind += 1;
        }else {
            pstm.setString( ind, cdmarca_m ); ind += 1;
        }

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            if (rs.getObject("dsmarca")!=null ) dsmarca = rs.getString("dsmarca");

        }

        return dsmarca;


    }






}
