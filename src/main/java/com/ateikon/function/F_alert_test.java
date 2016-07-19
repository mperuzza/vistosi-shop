package com.ateikon.function;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.SQLException;

import com.ateikon.common.Atk_sql;
import com.ateikon.common.Costanti_comm;


public class F_alert_test extends F_alert {
    

    public F_alert_test(){
        
        super();
    }



    /***
    *  run
    **/



    public void run (){

    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        try{

            System.out.println("xxx >>"+il_id_alert+" INIZIO <<");

            connect();

            // --- [... Elaborazione ...]

            System.out.println("    >>"+il_id_alert+" Elaborazione ... <<");

            if (il_id_alert == 1){
                l_query  = "";
                l_query += " select errore from pgmr.prova_errore \n";
        
                pstm = atk_sql.m_connection.prepareStatement(l_query);
        
                ind = 1;
                rs = pstm.executeQuery();
            }

            System.out.println("    >>"+il_id_alert+" ... fine Elaborazione <<");


            close_sched();

            // chiudo la Schedulazione



        }catch(SQLException sql_ex){
            
            debugger ("Errore SQL Data-base");
            sql_ex.printStackTrace();

            if (padre != null){
                padre.sendFatalError("Alert ID = "+il_id_alert+" \n\n"+sql_ex.toString());
            }

        }catch(Exception ex){
            
            debugger ("Exception");
            ex.printStackTrace();

            if (padre != null){
                padre.sendFatalError("Alert ID = "+il_id_alert+" \n\n"+ex.toString());
            }

        }finally{
            
            try {
                
                atk_sql.closeConnection();

            }catch(Exception close_ex){
                
            }

            System.out.println("    >>"+il_id_alert+" FINE  <<");

        }





    }








}

