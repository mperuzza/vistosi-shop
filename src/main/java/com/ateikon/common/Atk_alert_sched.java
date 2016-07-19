package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;

import com.ateikon.structure.Rec_atk_alert_sched;

public class Atk_alert_sched extends com.ateikon.standard.Atk_alert_sched {


    public Atk_alert_sched() {

        super();
    }



    /****
        getAll
    **/ 

    public ResultSet getAll( ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.atk_alert_sched  \n";
        l_query  += "  order by id \n";

        pstm = setQuery( l_query ) ;

        ind = 1;

        rs = pstm.executeQuery();

        return rs;

    }




    /***
        
        Controllo ed eventualmente inserisco i nuovi record

    */

    public int ctrl ( ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        Rec_atk_alert_sched lstr = null;

        String[] arr_cdsched_m = new String[]{ "NOW"
                                             , "DAY"
                                             , "WEEK"
                                             , "MONTH"
                                                            };

        String[] arr_descr     = new String[]{ "Immediato"
                                             , "Giornaliero"
                                             , "Settimanale"
                                             , "Mensile"
                                                            };


        for (int i=0; arr_cdsched_m!=null &&  i<arr_cdsched_m.length; i++){
             
            lstr = new Rec_atk_alert_sched();

            lstr.cdsched_m = arr_cdsched_m[i];
            lstr.descr = arr_descr[i]; 

            tot_rec = 0;

            l_query  = "";
            l_query += " select id                               \n";
            l_query += "   from pgmr.atk_alert_sched             \n";
            l_query += "  where cdsched_m = '"+lstr.cdsched_m+"' \n";

            lstr.id = sql_long(l_query);

            tot_rec = execute(lstr);

            if (tot_rec == 1){
                
                m_connection.commit();
            }else {
                throw new Exception("Errore UPD/INS atk_alert_sched ( cdsched_m = "+lstr.cdsched_m+" )");
            }
        }

        return 1;

    }


}

