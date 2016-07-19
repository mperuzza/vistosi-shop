package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Web_ord_test_note extends com.ateikon.standard.Web_ord_test_note {


    public Web_ord_test_note() {

        super();
    }



    // se modifico queste devo modificare anche in f_ordven

    public String TIPONOTA_INTERNA = "INTERNA";
    public String TIPONOTA_CLIENTE = "CLIENTE";

}

