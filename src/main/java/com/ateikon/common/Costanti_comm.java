package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class Costanti_comm extends Atk_sql {

    public Costanti_comm() {

        super();
    }



    public String getCostvalue(String costname) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        return getCostvalue(cdazie, cdappl, cddipa, profil, costname);

    }
    public String getCostvalue( String cdazie   
                              , String cdappl   
                              , String cddipa   
                              , String profil
                              , String costname
                                                ) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        String costvalue = "";

        costname = costname.trim().toLowerCase();

        l_query   = "";
        l_query  += " SELECT tkcost                            \n";
        l_query  += "      , costvalue                         \n";
        l_query  += "   FROM pgmr.costanti_comm                \n";
        l_query  += "  WHERE ( cdazie = ? OR  cdazie is null ) \n";
        l_query  += "    AND ( cdappl = ? OR  cdappl is null ) \n";
        l_query  += "    AND ( cddipa = ? OR  cddipa is null ) \n";
        l_query  += "    AND ( profil = ? OR  profil is null ) \n";
        l_query  += "    AND trim(lower(costname)) = ?         \n";
        l_query  += "  ORDER BY tkcost desc                    \n";

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setString( ind, cdazie   ); ind += 1;
        pstm.setString( ind, cdappl   ); ind += 1;
        pstm.setString( ind, cddipa   ); ind += 1;
        pstm.setString( ind, profil   ); ind += 1;
        pstm.setString( ind, costname ); ind += 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next() && rs.getObject(2)!=null ){

            costvalue = rs.getString(2);

        }
        pstm.close();
        pstm = null;

        return costvalue;


    }




}
