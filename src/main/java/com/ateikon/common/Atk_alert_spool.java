package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;

import com.ateikon.structure.Rec_atk_alert_spool;


public class Atk_alert_spool extends com.ateikon.standard.Atk_alert_spool {


    public Atk_alert_spool() {

        super();
    }





    /***

        Inserisce un <b>Nuovo</b> articolo nelllo 
        spool

    */

    public int add_arti(Rec_atk_alert_spool astr ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec = 0;

        l_query  = "";
        l_query += " select id                              \n";
        l_query += "   from pgmr.atk_alert_spool            \n";
        l_query += "  where id_alert = "+astr.id_alert+"    \n";
        l_query += "    and cdarti   = '"+astr.cdarti  +"'  \n";

        astr.id = sql_long(l_query);
        
        if (astr.id > 0){
            
            return 1;
        }

        tot_rec = execute(astr);

        return tot_rec;

    }






    /***


    */

    public long getTksend_alert ( ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        long nprogr = 0;


        l_query   = "";
        l_query  += " select nprogr                 \n";
        l_query  += "   from pgmr.contator          \n";
        l_query  += "  where cdazie = '00'          \n";
        l_query  += "    and prname = 'SEND_ALERT'  \n";

        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){
            
            if (rs.getObject("nprogr")!=null)  nprogr = rs.getLong("nprogr");

            pstm.close();

        }else {
            
            pstm.close();

            nprogr = 500;

            l_query   = "";
            l_query  += " insert into pgmr.contator (   \n";
            l_query  += "       cdazie                  \n";
            l_query  += "     , prname                  \n";
            l_query  += "     , annoco                  \n";
            l_query  += "     , nprogr                  \n";
            l_query  += "     , dtinse                  \n";
            l_query  += "     , dtulag                  \n";
            l_query  += "     , cddipa                  \n";
            l_query  += "     , profil                  \n";
            l_query  += " )values (                     \n";
            l_query  += "       '00'                    \n";
            l_query  += "     , 'SEND_ALERT'            \n";
            l_query  += "     , 'AAAA'                  \n";
            l_query  += "     , "+nprogr+"              \n";
            l_query  += "     , "+sysdate+"             \n";
            l_query  += "     , "+sysdate+"             \n";
            l_query  += "     , '0000'                  \n";
            l_query  += "     , 'SRV-ALERT'             \n";
            l_query  += " )                             \n";

            tot_rec = sql_update(l_query);

            if (tot_rec != 1){
                
                m_connection.rollback();
                
                throw new Exception("Errore INSERT pgmr.contator SEND_ALERT");
            }

            return nprogr;
            
        }


        nprogr += 1;

        if (nprogr <= 0){
            
            throw new Exception("Errore pgmr.contator nprogr NON VALIDO (nprogr = "+nprogr+")");
        }


        l_query   = "";
        l_query  += " update pgmr.contator          \n";
        l_query  += "    set nprogr = "+nprogr+"    \n";
        l_query  += "  where cdazie = '00'          \n";
        l_query  += "    and prname = 'SEND_ALERT'  \n";

        tot_rec = sql_update(l_query);

        if (tot_rec != 1){
            
            m_connection.rollback();
            
            throw new Exception("Errore Update pgmr.contator");
        }

        m_connection.commit();

        return nprogr;

    }





}

