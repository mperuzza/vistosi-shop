package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class Kg_reparto extends Atk_sql {

    public Kg_reparto() {
        
        super();
    }


    public ResultSet getReparti(String order_by) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select *                     \n";
		l_query  += "   from pgmr.kg_reparto  \n";
		l_query  += "  order by "+order_by+"       \n";

		pstm = setQuery( l_query ) ;

        ind = 1;

        rs = pstm.executeQuery();

        return rs;


    }

    public ResultSet getRep_div( String cddivisione
                               , String cdstagione
                               , int    annostg
                               , String order_by
                                                    ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select a.cdrepa                         \n";
		l_query  += "      , a.cdrepa_m                      \n";
		l_query  += "      , a.dsrepa                        \n";
		l_query  += "   from pgmr.kg_reparto         a   \n";
		l_query  += "      , pgmr.kg_articolo        b   \n";
		l_query  += "      , pgmr.kg_articolo_serie  c   \n";
		l_query  += "  where b.cdarti = c.cdarti             \n";
		l_query  += "    and c.cddivisione = ?               \n";
		l_query  += "    and c.cdstagione  = ?               \n";
		l_query  += "    and c.annostg     = ?               \n";
		l_query  += "    and a.cdrepa = b.cdrepa             \n";
		l_query  += "  group by a.cdrepa                     \n";
		l_query  += "         , a.cdrepa_m                   \n";
		l_query  += "         , a.dsrepa                     \n";
		l_query  += "  order by "+order_by+"                 \n";

		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString( ind, cddivisione ); ind += 1;
        pstm.setString( ind, cdstagione  ); ind += 1;
        pstm.setInt   ( ind, annostg     ); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }


    public ResultSet getCdrepa(String cdrepa) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select *                    \n";
		l_query  += "   from pgmr.kg_reparto  \n";
		l_query  += "  where cdrepa = ?           \n";

		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString( ind, cdrepa ); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }


    public String getDescr_m(String  cdrepa_m) throws Exception {
        
        return getDescr("", cdrepa_m);

    }

    public String getDescr(String  cdrepa) throws Exception {
        
        return getDescr(cdrepa, "");

    }

    public String getDescr(String cdrepa, String  cdrepa_m) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        String dsrepa  = "";

        l_query   = "";
		l_query  += " select dsrepa         \n";
		l_query  += "   from pgmr.kg_reparto  \n";
        if (!cdrepa.equals("")){
            l_query  += "  where cdrepa = ? \n";
        }else {
		    l_query  += "  where cdrepa_m = ? \n";
        }

		pstm = setQuery( l_query ) ;

        ind = 1;
        if (!cdrepa.equals("")){
            pstm.setString( ind, cdrepa ); ind += 1;
        }else {
            pstm.setString( ind, cdrepa_m ); ind += 1;
        }

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            if (rs.getObject("dsrepa")!=null ) dsrepa = rs.getString("dsrepa");

        }

        return dsrepa;


    }






}
