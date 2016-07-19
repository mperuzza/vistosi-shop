package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Web_ord_iva extends com.ateikon.standard.Web_ord_iva {


    public Web_ord_iva() {

        super();

        null_aliquo = false;
        null_imponibile = false;
        null_iva = false;
    }


}

