package com.ateikon.common;

import com.ateikon.structure.Rec_vist_log_notif;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class Vist_log_notif extends com.ateikon.standard.Vist_log_notif {


    public Vist_log_notif() {

        super();
    }


    /***
        Log notifica

    */

    public int log_notifica ( long tkmsg
                            , long tkutente_to
                            , long nrnotif
                            , String tpnotifica
                                                  ) throws Exception{

       	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        Rec_vist_log_notif lstr = new Rec_vist_log_notif();

        lstr.dtlog_notif  = new Timestamp(System.currentTimeMillis());
        lstr.tpnotif      = tpnotifica ;
        lstr.nrnotif      = nrnotif;
        lstr.tkutente_to  = tkutente_to;
        lstr.tkmsg        = tkmsg;

        lstr.anno         = lstr.dtlog_notif.getYear()+1900;
        lstr.mese         = lstr.dtlog_notif.getMonth()+1;
        lstr.giorno       = lstr.dtlog_notif.getDate();
        lstr.ora          = lstr.dtlog_notif.getHours();

        tot_rec = execute(lstr);



        return 1;

    }

}

