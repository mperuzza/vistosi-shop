package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Trasamez extends com.ateikon.standard.Trasamez {


    public Trasamez() {

        super();
    }



    /****
        getAll: trasamez
    **/ 

    public ResultSet getDropdown() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select cdtrme                 \n";
        l_query  += "      , dstrme                 \n";
        l_query  += "   from pgmr.trasamez          \n";
        l_query  += "  where cdazie = '"+cdazie+"'  \n";
        l_query  += "  order by dstrme              \n";

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getAll: trasamez
    **/ 

    public String getDestinatario() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select cdtrme                 \n";
        l_query  += "   from pgmr.trasamez          \n";
        l_query  += "  where cdazie = '"+cdazie+"'  \n";
        l_query  += "    and fdesti = 'S'           \n";
        l_query  += "  order by cdtrme desc         \n";

        String ls_ = sql_String( l_query ) ;

        return ls_;

    }




}

