package com.ateikon.service;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.util.Vector;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.ateikon.common.Costanti_comm;
import com.ateikon.common.Atk_sql;

import com.ateikon.util.Afs_ctrl;
import com.ateikon.util.Atk_ctrl;

import com.ateikon.function.F_alert;

public class Srv_alert extends Service {



    public Srv_alert (String[] args){

        super("Srv_alert", args);
    }




    /***
    *
    *   Impemento il Metodo Astratto
    **/


    public void elabora() throws Exception{

        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        // --- Forzo il LOOP a un minuto

        if (LOOP_TIME != 300000){
            
            debugger("*** ");
            debugger("*** --------------------------------------------------");
            debugger("*** Attenzione ! ");
            debugger("*** ");
            debugger("*** E' consigliabilte impostare LOOP_TIME = 5 minuti! ");
            debugger("***               Leo (26/10/2006) ");
            debugger("*** --------------------------------------------------");
            debugger("*** ");

        }



        Costanti_comm   costanti_comm   = new Costanti_comm();
        
        atk_sql.setProfilo((Atk_sql) costanti_comm  );


        
        Timestamp ldt_rif = atk_sql.of_getOggi();
        Timestamp ldt_rif_sched = atk_sql.of_getTimestamp();



        // --- Torvo la data Di oggi

        int sa = ldt_rif_sched.getYear()+1900;
        int mm = ldt_rif_sched.getMonth()+1;
        int gg = ldt_rif_sched.getDate();

        int hh = ldt_rif_sched.getHours();
        int mi = ldt_rif_sched.getMinutes();

        int week_day = ldt_rif_sched.getDay();



        // --- Imposto le date di riferimento per le varie scedulazioni

        // Sechdulazione giornaliera:
        
            
        Timestamp ldt_rif_day = null;
        ldt_rif_day = new Timestamp( sa-1900, mm-1, gg, 0,0,0,0 );

        // Sechdulazione settimanale: calcolo il primo giorno della settimana
        //                            (rappresentato nella variabile li_start)
        

        int li_start    = 1;       // Giorno della settimana di patenza Lunedì

        int li_delta_da = 0;
        int li_week_sa = sa;
        int li_week_mm = mm;
        int li_week_gg = gg;

        Timestamp ldt_rif_week = null;

        if (week_day >= li_start){
            li_delta_da = li_start - week_day;
        }else {
            li_delta_da = -1 * (li_start + (week_day+1));
        }

        if (li_delta_da < 0){
           
            Calendar gc = new GregorianCalendar();
            gc.set(sa, mm-1, gg);
        
            gc.add(Calendar.DATE, li_delta_da);

            li_week_sa = gc.get(gc.YEAR);
            li_week_mm = gc.get(gc.MONTH)+1;
            li_week_gg = gc.get(gc.DAY_OF_MONTH);
        }

        
        ldt_rif_week = new Timestamp( li_week_sa-1900, li_week_mm-1, li_week_gg, 0,0,0,0 );   // lunedì



        // Sechdulazione Mensile: calcolo il primo giorno del mese Corrente
        // 

        Timestamp ldt_rif_month = new Timestamp( sa-1900, mm-1, 1, 0,0,0,0 );   // lunedì
        


        // --- Variabili d'appoggio

        long        id = 0;
        String      cdsched_m = "";
        String      classname = "";
        Timestamp   dtsched_i = null;
        Timestamp   dtsched_f = null;


        l_query  = "";
        l_query += " select a.id                                   \n";
        l_query += "      , a.dtsched_i                            \n";
        l_query += "      , b.cdsched_m                            \n";
        l_query += "   from pgmr.atk_alert       a                 \n";
        l_query += "      , pgmr.atk_alert_sched b                 \n";
        l_query += "      , pgmr.atk_alert_class c                 \n";
        l_query += "  where a.id_sched = b.id                      \n";
        l_query += "    and a.id_class = c.id                      \n";
        l_query += "    and ( a.dtini  <= ? or a.dtini  is null )  \n";
        l_query += "    and ( a.dtfine >= ? or a.dtfine is null )  \n";
        l_query += "  order by a.id                                \n";

        pstm = atk_sql.m_connection.prepareStatement(l_query);

        ind = 1;
        pstm.setTimestamp(ind, ldt_rif); ind += 1;
        pstm.setTimestamp(ind, ldt_rif); ind += 1;

        rs = pstm.executeQuery();

        while (rs!=null && rs.next()){
            
            id = 0;
            cdsched_m = "";
            dtsched_i = null;

            if (rs.getObject("id"       )!=null)  id        = rs.getLong("id");
            if (rs.getObject("cdsched_m")!=null)  cdsched_m = rs.getString("cdsched_m");
            if (rs.getObject("dtsched_i")!=null)  dtsched_i = rs.getTimestamp("dtsched_i");


            if (cdsched_m.equals("NOW")){
            
                // Questo metodo Controlla che la scedulazione sia terminata
                of_startup(id);

            }else if (cdsched_m.equals("DAY")){
            
                if (dtsched_i == null){
                    
                    of_startup(id);

                }else {
                    if (dtsched_i.compareTo(ldt_rif_day) < 0){
                        of_startup(id);
                    }
                }


            }else if (cdsched_m.equals("WEEK")){

                if (dtsched_i == null){
                    
                    of_startup(id);

                }else {
                    if (dtsched_i.compareTo(ldt_rif_week) < 0){
                        of_startup(id);
                    }
                }

            }else if (cdsched_m.equals("MONTH")){

                if (dtsched_i == null){
                    
                    of_startup(id);

                }else {
                    if (dtsched_i.compareTo(ldt_rif_month) < 0){
                        of_startup(id);
                    }
                }
                
            }else {
                throw new Exception("ERROR: Tipo Schedulazione NON prevista!");
            }


        }   // FINE  WHILE 

        pstm.close();

        System.gc();

    }   // FINE ELABORA




    /***


    */

    public int of_startup ( long id ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        String      cdsched_m = "";
        String      classname = "";
        Timestamp   dtsched_i = null;
        Timestamp   dtsched_f = null;

        debugger("Go --> "+id);


        l_query  = "";
        l_query += " select a.id                    \n";
        l_query += "      , a.id_class              \n";
        l_query += "      , a.id_sched              \n";
        l_query += "      , a.dtsched_i             \n";
        l_query += "      , a.dtsched_f             \n";
        l_query += "      , b.cdsched_m             \n";
        l_query += "      , c.classname             \n";
        l_query += "   from pgmr.atk_alert       a  \n";
        l_query += "      , pgmr.atk_alert_sched b  \n";
        l_query += "      , pgmr.atk_alert_class c  \n";
        l_query += "  where a.id_sched = b.id       \n";
        l_query += "    and a.id_class = c.id       \n";
        l_query += "    and a.id = ?                \n";

        pstm = atk_sql.m_connection.prepareStatement(l_query);

        ind = 1;
        pstm.setLong(ind, id); ind += 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){
            cdsched_m = "";
            classname = "";
            dtsched_i = null;
            dtsched_f = null;

            if (rs.getObject("cdsched_m")!=null)  cdsched_m = rs.getString("cdsched_m");
            if (rs.getObject("classname")!=null)  classname = rs.getString("classname");
            if (rs.getObject("dtsched_i")!=null)  dtsched_i = rs.getTimestamp("dtsched_i");
            if (rs.getObject("dtsched_f")!=null)  dtsched_f = rs.getTimestamp("dtsched_f");
          
        }
        pstm.close();


        // --- Controllo se posso Inizializzare il servizio

        if (dtsched_i != null){
            
            if (dtsched_f == null){
                

                // se sono passate 4 ore dall'ultima esecuzione 
                // resetto ... 

                int sa = dtsched_i.getYear()+1900;
                int mm = dtsched_i.getMonth()+1;
                int gg = dtsched_i.getDate();
                int hh = dtsched_i.getHours();
                int mi = dtsched_i.getMinutes();

                debugger("data INI: "+gg+"/"+mm+"/"+sa+"/  "+hh+":"+mi);



                Calendar gc = new GregorianCalendar();
                gc.set(sa, mm-1, gg, hh, mi, 0);
        
                gc.add(Calendar.HOUR_OF_DAY, 4);

                sa = gc.get(gc.YEAR);
                mm = gc.get(gc.MONTH)+1;
                gg = gc.get(gc.DAY_OF_MONTH);
                hh = gc.get(gc.HOUR_OF_DAY);
                mi = gc.get(gc.MINUTE);

                
                debugger("data +4 : "+gg+"/"+mm+"/"+sa+"/  "+hh+":"+mi);


                Timestamp ldt_piu_4 = new Timestamp( sa-1900, mm-1, gg, hh, mi,0,0 );   // lunedì

                if (atk_sql.of_getTimestamp().compareTo(ldt_piu_4) <= 0){
                    
                    debugger("Alert "+id+" è in esecuzione dalle "+dtsched_i);
                    return 0;
                }else {
                    
                    sendFatalError("Alert "+id+"in esecuzione dalle "+dtsched_i+ "  \n\nResetto Lo stato e lo rieseguo");

                    debugger("Alert "+id+" è in esecuzione dalle "+dtsched_i+ " --- Ritento");

                }
            }
        }




        l_query  = "";
        l_query += " update pgmr.atk_alert                   \n";
        l_query += "    set dtsched_i = "+atk_sql.sysdate+"  \n";
        l_query += "      , dtsched_f = null                 \n";
        l_query += " where id = "+id+"                       \n";

        tot_rec = atk_sql.sql_update(l_query);


        if (tot_rec == 1){
            atk_sql.m_connection.commit();
        }else {
            throw new Exception("Errore UPD pgmr.atk_alert");
        }


        Class		xClass = null;
        Object		xObj   = null;
        F_alert     xAlert = null;

        
        try {

            xClass = Class.forName(classname);
            xObj   = xClass.newInstance();
            xAlert = (F_alert) xObj;
    
            xAlert.setId(id);
            xAlert.setPadre(this);
        
            xAlert.run();
            
        }catch (Exception ex_alert){
           
            debugger("Alert "+id+" Terminato con Errore ");

            debugger("----------------------");
            debugger("");
            ex_alert.printStackTrace();
            debugger("");
            debugger("----------------------");

            sendFatalError("Alert "+id+" \n\n"+ex_alert.toString());

            // --- Chiudo la scedulazione 

            l_query  = "";
            l_query += " update pgmr.atk_alert                   \n";
            l_query += "    set dtsched_f = "+atk_sql.sysdate+"  \n";
            l_query += " where id = "+id+"                       \n";
    
            tot_rec = atk_sql.sql_update(l_query);
    
            if (tot_rec == 1){
                atk_sql.m_connection.commit();
            }else {
                throw new Exception("Errore UPD pgmr.atk_alert");
            }

        }


        return 1 ;

    }






    /***
    *
    *   Main
    **/



    public static void main(String[] args){

        if (args==null || args.length != 1){
            System.out.println("");
            System.out.println("");
            System.out.println("Argomenti validi: start|stop");
            System.out.println("");
            System.out.println("");

            System.gc();
            System.exit(0);
        }
        args[0] = args[0].toLowerCase();

        Srv_alert obj_srv = new Srv_alert(args);

        System.gc();
        System.exit(0);
    }



    //  Properties



    public String l_query  = "";




}
