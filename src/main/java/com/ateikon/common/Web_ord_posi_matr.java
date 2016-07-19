package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Web_ord_posi_matr extends com.ateikon.standard.Web_ord_posi_matr {


    public Web_ord_posi_matr() {

        super();
        null_qtatot = false;
        null_impuni = false;
        null_imptot = false;
        null_qtacons = false;
        null_qtacons_s = false;
    }





    /****
        execute: web_ord_posi_matr
    **/ 


    public int execute ( com.ateikon.structure.Rec_web_ord_posi_matr astr) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query  = "";
        l_query += " select tkposi_matr                         \n";
        l_query += "   from pgmr.web_ord_posi_matr              \n";
        l_query += "  where tkordi = "+astr.tkordi+"            \n";
        l_query += "    and tkposi = "+astr.tkposi+"            \n";
        l_query += "    and tkmatricola = "+astr.tkmatricola+"  \n";

        astr.tkposi_matr = sql_long(l_query);

        tot_rec = super.execute(astr); 

        return tot_rec;

    }






}

