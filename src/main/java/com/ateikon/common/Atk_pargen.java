/*
 * Articolo.java
 *
 * Created on 6 agosto 2004, 15.23
 */

package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;



public class Atk_pargen extends Atk_sql {

    /** Creates a new instance of Articolo */
    public Atk_pargen() {

        super();

    }



    public String getCostvalue(String parametro) throws Exception {

        return getCostvalue(cdazie, parametro);

    }
    public String getCostvalue(String cdazie, String parametro) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        String costvalue = "";

        l_query   = "";
        l_query  += " select valore                  \n";
        l_query  += "   from web.atk_pargen \n";
        l_query  += "  where 1=1                \n";
        if (!cdazie.equals("")){
        l_query  += "    and cdazie = ?         \n";
        }
        if (!parametro.equals("")){
        l_query  += "    and upper(parametro) = upper(?)       \n";
        }

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (!cdazie.equals("")){
        pstm.setString( ind, cdazie          ); ind += 1;
        }
        if (!parametro.equals("")){
        pstm.setString( ind, parametro       ); ind += 1;
        }

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            if (rs.getObject("valore")!=null ) costvalue = rs.getString("valore");

        }
        pstm.close();
        pstm = null;

        return costvalue;
    }








}
