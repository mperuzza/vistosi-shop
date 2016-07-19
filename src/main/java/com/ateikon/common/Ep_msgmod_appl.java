package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


/**********************/
//     NOTA:
//     modificare questa classe nel portale implica un allineamento della stessa classe in eprogen
//     e viceversa modificare questa classe nel eprogen implica un allineamento della stessa classe nel portale
//
/*********************/

public class Ep_msgmod_appl extends com.ateikon.standard.Ep_msgmod_appl {


    public Ep_msgmod_appl() {

        super();
        ib_calcola_token = false;
    }


}

