package com.ateikon.function;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.SQLException;

import com.ateikon.common.Atk_sql;
import com.ateikon.common.Costanti_comm;
import com.ateikon.service.Srv_alert;

import java.io.FileInputStream;
import java.io.File; 			

public class F_alert extends Thread {
    

    public F_alert (){
        
        super();
    }



    protected long il_id_alert = 0;

    public void setId(long al_id) {
        il_id_alert = al_id;
    }


    protected Srv_alert padre = null;

    public void setPadre(Srv_alert ao_) {
        padre = ao_;
    }


    /***


    */

    public int connect( ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        atk_sql = new Atk_sql();

        atk_sql.dbConnection();
        atk_sql.cdazie = "01";
        atk_sql.cddipa = "0000";
        atk_sql.profil = "ALERT-"+il_id_alert;

        return 1;

    }





    /***


    */

    public int close_sched ( ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        l_query  = "";
        l_query += " update pgmr.atk_alert                   \n";
        l_query += "    set dtsched_f = "+atk_sql.sysdate+"  \n";
        l_query += " where id = "+il_id_alert+"                    \n";

        tot_rec = atk_sql.sql_update(l_query);

        if (tot_rec == 1){
            atk_sql.m_connection.commit();
        }else {
            throw new Exception("Errore UPD pgmr.atk_alert");
        }



        return tot_rec ;

    }





    public String getModel(String path) throws Exception                {
 
		FileInputStream 	fileInputStream = null;
		File 				file = null;
		byte[] 				arr_buffer = null;
		StringBuffer		buffer = new StringBuffer("");
        String              testo = "";


		file = new File(path);

		if (!file.exists( ) || file.isDirectory( )){
			throw new Exception ("Il Modello non è un File: >>"+path+"<< ");
		}

		// Carico il buffer
		fileInputStream = new FileInputStream(file);

		arr_buffer = new byte[new Long(file.length( )).intValue()];
		fileInputStream.read( arr_buffer );
		fileInputStream.close();

		testo = new String(arr_buffer);

        return testo;
    }







    public void debugger(String debug){
        System.err.println(debug);
    }

    public void debugger(){
        
        debugger("");
    }





    // Variabili 

        
    public Atk_sql  atk_sql = null;
    public String   l_query = "";


    // Costanti




}

