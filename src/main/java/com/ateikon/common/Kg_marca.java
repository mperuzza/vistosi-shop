package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class Kg_marca extends Atk_sql {

    public Kg_marca() {
        
        super();
    }


    public ResultSet getMarca(String order_by) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select *                     \n";
		l_query  += "   from pgmr.kg_marca  \n";
		l_query  += "  order by "+order_by+"       \n";

		pstm = setQuery( l_query ) ;

        ind = 1;

        rs = pstm.executeQuery();

        return rs;


    }

    public ResultSet getMarca_div( String cddivisione
                                 , String cdstagione
                                 , int    annostg
                                 , String order_by
                                                            ) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select a.cdmarca                       \n";
		l_query  += "      , a.cdmarca_m                     \n";
		l_query  += "      , a.dsmarca                       \n";
		l_query  += "   from pgmr.kg_marca           a   \n";
		l_query  += "      , pgmr.kg_articolo        b   \n";
		l_query  += "      , pgmr.kg_articolo_serie  c   \n";
		l_query  += "  where b.cdarti = c.cdarti             \n";
		l_query  += "    and c.cddivisione = ?               \n";
		l_query  += "    and c.cdstagione  = ?               \n";
		l_query  += "    and c.annostg     = ?               \n";
		l_query  += "    and c.cdmarca = a.cdmarca           \n";
		l_query  += "  group by a.cdmarca                    \n";
		l_query  += "         , a.cdmarca_m                  \n";
		l_query  += "         , a.dsmarca                    \n";
		l_query  += "  order by "+order_by+"                 \n";

		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString( ind, cddivisione ); ind += 1;
        pstm.setString( ind, cdstagione  ); ind += 1;
        pstm.setInt   ( ind, annostg     ); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }

    public ResultSet getCdmarca(String cdmarca) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select *                     \n";
		l_query  += "   from pgmr.kg_marca  \n";
		l_query  += "  where cdmarca = ?       \n";

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
		l_query  += "   from pgmr.kg_marca  \n";
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
