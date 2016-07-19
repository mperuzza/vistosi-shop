package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;

import com.ateikon.structure.Rec_atk_alert_class;



public class Atk_alert_class extends com.ateikon.standard.Atk_alert_class {


    public Atk_alert_class() {

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
        l_query  += "   from pgmr.atk_alert_class  \n";
        l_query  += "  order by classname \n";

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


        Costanti_comm costanti_comm = new Costanti_comm();

        setProfilo((Atk_sql)costanti_comm );

        String siteRoot            = costanti_comm.getCostvalue("ep.siteRoot");
        String cliente             = costanti_comm.getCostvalue("ep.cliente");

        String slash = System.getProperty( "file.separator" );

        Rec_atk_alert_class lstr = null;



        // * Da impostare 

        String[] arr_classname = new String[]{ "com.ateikon.function.F_alert_test"
                                             , "com.ateikon.function.F_alert_yacht"
                                               };

        String[] arr_cdclass_m = new String[]{ "TEST"
                                             , "MEDIAYACHT"
                                               };

        String[] arr_modello_def  = new String[]{ ""
                                             , "alert_mediayacht"
                                               };

        // * Fine


        for (int i=0; arr_classname!=null &&  i<arr_classname.length; i++){
             
            lstr = new Rec_atk_alert_class();

            lstr.classname = arr_classname[i];
            lstr.cdclass_m = arr_cdclass_m[i];
            lstr.fgabil    = "N";

            tot_rec = 0;

            l_query  = "";
            l_query += " select id                               \n";
            l_query += "      , fgabil                           \n";
            l_query += "      , path_mail                        \n";
            l_query += "      , oggetto                          \n";
            l_query += "   from pgmr.atk_alert_class             \n";
            l_query += "  where classname = '"+lstr.classname+"' \n";

            pstm = m_connection.prepareStatement(l_query);

            rs = pstm.executeQuery();

            if (rs!=null && rs.next()){
                
                lstr.id = rs.getLong("id");
                if (rs.getObject("fgabil"   )!=null)  lstr.fgabil    = rs.getString("fgabil"   );
                if (rs.getObject("path_mail")!=null)  lstr.path_mail = rs.getString("path_mail");
                if (rs.getObject("oggetto"  )!=null)  lstr.oggetto   = rs.getString("oggetto"  );
            }else{
                
                // imposto il path di Default

                if (!arr_modello_def[i].equals("")){
                    lstr.path_mail = siteRoot + "mail_"+cliente.toLowerCase() + slash + arr_modello_def[i]+".html"; 
                }

            }

            tot_rec = execute(lstr);


        }


        l_query  = "";
        l_query += " update pgmr.atk_alert_class \n";
        l_query += "    set id = 1               \n";
        l_query += "  where cdclass_m = 'TEST'   \n";

        tot_rec = sql_update(l_query);
        
        if (tot_rec == 1){
            m_connection.commit();
        }else {
            throw new Exception("Errore UPD/INS atk_alert_class ( classname = "+lstr.classname+" )");
        }


        return 1;

    }



}

