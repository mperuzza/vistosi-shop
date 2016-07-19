package com.ateikon.function;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import com.ateikon.common.Atk_sql;

import com.ateikon.structure.Str_iva_assofisc;


public class F_iva_assofisc extends Atk_sql {



    public F_iva_assofisc() {

        super();
    }



    /***


        In questa fase non considero l'iva del 
        cliente !!!

        Da sistemare.

    */


    public int exec( Str_iva_assofisc astr
                                                        ) throws Exception{
        
        int               ind     = 0;
        int               tot_rec = 0;
        PreparedStatement pstm    = null;
        ResultSet         rs      = null;


        l_query   = "";
		l_query  += " select cdiva                       \n";
		l_query  += "      , cdfisc                      \n";
		l_query  += "      , ivato                       \n";
		l_query  += "   from pgmr.mrp_arch_articoli      \n";
		l_query  += "  where cdarti = ?                  \n";

        pstm = m_connection.prepareStatement( l_query );


        ind = 1;
        pstm.setString(ind, astr.cdarti); ind += 1;

        rs = pstm.executeQuery();
        
        if (rs!=null && rs.next()){
            
            if (rs.getObject("cdiva" )!=null) astr.cdiva  = rs.getString("cdiva");
            if (rs.getObject("cdfisc")!=null) astr.cdfisc = rs.getString("cdfisc");
        }

        pstm.close();


        l_query   = "";
		l_query  += " select aliquo                         \n";
		l_query  += "   from pgmr.codiciva                  \n";
		l_query  += "  where cdiva  = '"+astr.cdiva+"'      \n";
		l_query  += "    and cdazie = '"+cdazie+"'          \n";

        astr.aliquo = sql_double( l_query );



        return 0;

    }





    /***

        Il calcolo dell'IVA viene arrotondata sempre per 
        eccesso.
    */


    public double round(double importo) throws Exception{
        
        int               ind     = 0;
        int               tot_rec = 0;
        PreparedStatement pstm    = null;
        ResultSet         rs      = null;


        importo = Math.ceil(importo * 100);
        
        importo = importo / 100;

        return importo;
    }



}
