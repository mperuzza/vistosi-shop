package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class Kg_tipomapr_cat extends Atk_sql {

    public Kg_tipomapr_cat() {
        
        super();
    }



    public ResultSet getDropDown() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        l_query   = "";
		l_query  += "  select c.cdtipmc             \n";
		l_query  += "       , c.dstipmc             \n";
		l_query  += "    from pgmr.kg_tipomapr_cat c     \n";
		l_query  += "   order by c.dstipmc              \n";

		pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;


    }


    public int countTipomapr(String cdgptipm) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select count(*)               \n";
		l_query  += "   from pgmr.kg_tipomapr_cat   \n";
		l_query  += " where cdazie = '"+cdazie+"'   \n";
		l_query  += "   and fgabil = 'S' \n";
        if (!cdgptipm.equals("")){
		l_query  += " and cdgptipm = '"+cdgptipm+"' \n";
        }

		tot_rec = sql_int( l_query ) ;

        return tot_rec;

    }


    public ResultSet getTipomapr(String cdgptipm, String order_by, String cdling) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        String ls_suffisso = of_lingua("kg_tipomapr_cat",cdling);

        l_query   = "";
		l_query  += " select cdtipmc                 \n";
		l_query  += "      , dstipmc" + ls_suffisso + "  as dstipmc               \n";
		l_query  += "      , cdtipmc_m               \n";
		l_query  += "   from pgmr.kg_tipomapr_cat   \n";
		l_query  += " where cdazie = ?              \n";
		l_query  += "   and fgabil = 'S' \n";
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




    public ResultSet getCdtipmc(String cdtipmc) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select *                    \n";
		l_query  += "   from pgmr.kg_tipomapr_cat \n";
		l_query  += "  where cdtipmc = ?          \n";
        l_query  += "    and cdazie = ?           \n";

		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString( ind, cdtipmc ); ind += 1;
        pstm.setString( ind, cdazie  ); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }

    public String getDescr_m(String  cdtipmc_m) throws Exception {
        
        return getDescr("", cdtipmc_m);

    }

    public String getDescr(String  cdtipmc) throws Exception {
        
        return getDescr(cdtipmc, "");

    }

    public String getDescr(String cdtipmc, String  cdtipmc_m) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        String dstipmc  = "";

        l_query   = "";
		l_query  += " select dstipmc               \n";
		l_query  += "   from pgmr.kg_tipomapr_cat  \n";
        l_query  += "  where cdazie = ?            \n";
        if (!cdtipmc.equals("")){
            l_query  += "  and cdtipmc = ?         \n";
        }else {
		    l_query  += "  and cdtipmc_m = ?       \n";
        }

		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString( ind, cdazie); ind += 1;

        if (!cdtipmc.equals("")){
            pstm.setString( ind, cdtipmc ); ind += 1;
        }else {
            pstm.setString( ind, cdtipmc_m ); ind += 1;
        }

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            if (rs.getObject("dstipmc")!=null ) dstipmc = rs.getString("dstipmc");
        }

        return dstipmc;


    }






}
