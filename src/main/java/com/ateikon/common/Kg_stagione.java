package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class Kg_stagione extends Atk_sql {

    public Kg_stagione() {
        
        super();
    }


    public ResultSet getStagioni(String order_by) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select *                     \n";
		l_query  += "   from pgmr.kg_stagione  \n";
		l_query  += "  order by "+order_by+"       \n";

		pstm = setQuery( l_query ) ;

        ind = 1;

        rs = pstm.executeQuery();

        return rs;


    }

    public ResultSet getStagioni_div(String cddivisione, String order_by) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;


        l_query   = "";
		l_query  += " select a.cdstagione                    \n";
		l_query  += "      , a.cdstagione_m                  \n";
		l_query  += "      , a.dsstagione                    \n";
		l_query  += "      , c.annostg                       \n";
		l_query  += "   from pgmr.kg_stagione        a   \n";
		l_query  += "      , pgmr.kg_articolo        b   \n";
		l_query  += "      , pgmr.kg_articolo_serie  c   \n";
		l_query  += "  where b.cdarti = c.cdarti             \n";
		l_query  += "    and c.cddivisione = ?               \n";
		l_query  += "    and c.cdstagione = a.cdstagione     \n";
		l_query  += "  group by a.cdstagione                 \n";
		l_query  += "         , a.cdstagione_m               \n";
		l_query  += "         , a.dsstagione                 \n";
		l_query  += "         , c.annostg                    \n";
		l_query  += "  order by "+order_by+"                 \n";



		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString( ind, cddivisione ); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }


    public ResultSet getCdstagione(String cdstagione) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select *                     \n";
		l_query  += "   from pgmr.kg_stagione  \n";
		l_query  += "  where cdstagione = ?        \n";

		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString( ind, cdstagione ); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }


    public String getDescr_m(String  cdstagione_m) throws Exception {
        
        return getDescr("", cdstagione_m);

    }

    public String getDescr(String  cdstagione) throws Exception {
        
        return getDescr(cdstagione, "");

    }

    public String getDescr(String cdstagione, String  cdstagione_m) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        String dsstagione = "";

        l_query   = "";
		l_query  += " select dsstagione         \n";
		l_query  += "   from pgmr.kg_stagione  \n";
        if (!cdstagione.equals("")){
            l_query  += "  where cdstagione = ? \n";
        }else {
		    l_query  += "  where cdstagione_m = ? \n";
        }

		pstm = setQuery( l_query ) ;

        ind = 1;
        if (!cdstagione.equals("")){
            pstm.setString( ind, cdstagione ); ind += 1;
        }else {
            pstm.setString( ind, cdstagione_m ); ind += 1;
        }

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            if (rs.getObject("dsstagione")!=null ) dsstagione = rs.getString("dsstagione");

        }

        return dsstagione;


    }






}
