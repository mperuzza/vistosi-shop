package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import com.ateikon.util.Atk_ctrl;

import com.ateikon.function.F_tabkey;


public class Archforn extends Atk_sql {

    public Archforn() {
        
        super();
    }



    /***


    */

    public ResultSet search ( String  cdclie_m
                         , String  ragcog
                         , String  cdagen_m
                         , String  dsagen
                         , String  cerca
                         , boolean is_count 
                         , String  order_by
                                                ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        return rs ;

    }



}
