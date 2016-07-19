package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Cat_tipomapr_escl extends com.ateikon.standard.Cat_tipomapr_escl {


    public Cat_tipomapr_escl() {

        super();
        ib_calcola_token = false;
    }





    /***


    */

    public int delete_all ( ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query  = "";
        l_query += " delete                        \n";
        l_query += "   from pgmr.cat_tipomapr_escl \n";

        pstm = m_connection.prepareStatement(l_query);

        tot_rec = pstm.executeUpdate();

        pstm.close();

        return tot_rec;

    }


}

