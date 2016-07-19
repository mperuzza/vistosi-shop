package com.ateikon.service;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.Connection;

import java.util.Vector;


import com.ateikon.common.Atk_sql;
import com.ateikon.common.Costanti_comm;
import com.ateikon.util.StringUtils;
import com.ateikon.util.Atk_ctrl;

import com.ateikon.common.Lis_lisarticolo;
import com.ateikon.common.Mrp_file_giacenza;



public class Srv_delo_exp extends Service {


    public Srv_delo_exp (String[] args){

        super("Srv_delo_exp", args);

    }



    /***
    *
    *   Implemento il Metodo Astratto
    **/



    public void elabora() throws Exception{

        int               ind     = 0;
        int               tot_rec = 0;
        PreparedStatement pstm    = null;
        ResultSet         rs      = null;

        String            sql = "";
        ResultSet         rs_box = null;


        Atk_ctrl atk_ctrl = new Atk_ctrl();


        
        Lis_lisarticolo lis_lisarticolo = new Lis_lisarticolo();
        Mrp_file_giacenza mrp_file_giacenza = new Mrp_file_giacenza();

        atk_sql.setProfilo((Atk_sql) lis_lisarticolo);
        atk_sql.setProfilo((Atk_sql) mrp_file_giacenza);
        



        String ls_rec = "";

        String CDLIST_VEN = "0000000021";

        String l_query = "";

                     
        java.text.NumberFormat qtaFormat = java.text.NumberFormat.getInstance(java.util.Locale.ITALY);
        qtaFormat.setGroupingUsed( false );
        qtaFormat.setMaximumFractionDigits( 3 );
        qtaFormat.setMinimumFractionDigits( 0 );
    
        java.text.NumberFormat przFormat = java.text.NumberFormat.getInstance(java.util.Locale.ITALY);
        przFormat.setGroupingUsed( false );
        przFormat.setMaximumFractionDigits( 2 );
        przFormat.setMinimumFractionDigits( 2 );

        String ls_tkclie = "";

        if (!elab_for_user.equals("")){
        
            l_query  = "";
            l_query += " select tkclie                                 \n";
            l_query += "   from web.utente                             \n";
            l_query += "  where lower(username) = '"+elab_for_user+"'  \n";
            l_query += "    and cdazie = '01'                          \n";
            
            ls_tkclie = atk_sql.sql_String(l_query);
        }


        l_query  = "";
        l_query += " select count(*)                    \n";
        l_query += "   from pgmr.mrp_arch_articoli art  \n";
        l_query += "      , pgmr.kg_articolo kg         \n";
        l_query += "  where art.cdarti = kg.cdarti      \n";
        l_query += "    and art.cdazie = '01'           \n";
        if (!tpelab.equals("all")){
        l_query += "    and art.fgabil = 'S'            \n";//avendramin20080321
        }


        int tot_art = atk_sql.sql_int(l_query);
        int count_rec = 0;



        l_query  = "";
        l_query += " select art.cdarti                  \n";
        l_query += "      , art.cdbarcode               \n";
        l_query += "      , art.cdartprod               \n";
        l_query += "      , art.dsarti                  \n";
        l_query += "      , art.fgabil                  \n";
        l_query += "      , kg.qtaov                    \n";
        l_query += "      , kg.qtaoa                    \n";
        l_query += "      , kg.dtconsoa                 \n";


        if (ls_tkclie.equals("")){
            
            l_query += "      , null as cdacli              \n";
            l_query += "   from pgmr.mrp_arch_articoli art  \n";
            l_query += "      , pgmr.kg_articolo kg         \n";
            l_query += "  where art.cdarti = kg.cdarti      \n";
            l_query += "    and art.cdazie = '01'           \n";
            if (!tpelab.equals("all")){
            l_query += "    and art.fgabil = 'S'            \n";//avendramin20080321
            }
        }else {
            
            l_query += "     , rifa.cdacli                 \n";
            l_query += "  from {oj               pgmr.mrp_arch_articoli art                                      \n";
            l_query += "        left outer join  pgmr.ut_art_rifartclie rifa  ON  art.cdarti   = rifa.cdarti     \n";
            l_query += "                                                      AND '"+ls_tkclie+"' = rifa.tkclie  \n";
            l_query += "              }                                                                          \n";
            l_query += "     , pgmr.kg_articolo kg                                                               \n";
            l_query += " where art.cdarti = kg.cdarti                                                            \n";
            l_query += "   and art.cdazie = '01'                                                                 \n";
            if (!tpelab.equals("all")){
            l_query += "    and art.fgabil = 'S'            \n";//avendramin20080321
            }
        }


        rs = atk_sql.sql_query(l_query);

        while (rs!=null && rs.next()){

    
            String    cdarti     = "";
            String    cdbarcode  = "";
            String    cdartprod  = "";
            String    dsarti     = "";
            String    fgabil     = "";
            double    przven     = 0;
            double    qtadisp    = 0;       
            double    qtagia     = 0;
            double    qtaov      = 0;
            double    qtaoa      = 0;
            Timestamp dtconsoa   = null;
            String    cdacli     = ""; 
            
            ls_rec = "";
            


            if ((count_rec%1000) == 0 ){
                System.err.println();
                System.err.println("Rec elab.: "+count_rec+" / "+tot_art);
                System.err.println();
            }else {
                System.err.print(".");
            }
            count_rec += 1;

            if (rs.getObject("cdarti")!=null)  cdarti = rs.getString("cdarti");
            if (rs.getObject("cdbarcode")!=null)  cdbarcode = rs.getString("cdbarcode");
            if (rs.getObject("cdartprod")!=null)  cdartprod = rs.getString("cdartprod");
            if (rs.getObject("dsarti")!=null)  dsarti = rs.getString("dsarti");
            if (rs.getObject("fgabil")!=null)  fgabil = rs.getString("fgabil");

            if (rs.getObject("qtaov")!=null)  qtaov = rs.getDouble("qtaov");
            if (rs.getObject("qtaoa")!=null)  qtaoa = rs.getDouble("qtaoa");

            if (rs.getObject("dtconsoa")!=null)  dtconsoa = rs.getTimestamp("dtconsoa");
            if (rs.getObject("cdacli")!=null)  cdacli = rs.getString("cdacli");
               
            
            rs_box = lis_lisarticolo.getPrezzo(cdarti, CDLIST_VEN);
            if (rs_box!=null && rs_box.next()){
                if (rs_box.getObject("prezzo")!=null) przven = rs_box.getDouble("prezzo");
            }

            rs_box = mrp_file_giacenza.getGiacenza(cdarti);
            if (rs_box!=null && rs_box.next()){
                if (rs_box.getObject("qtagia")!=null) qtagia = rs_box.getDouble("qtagia");
            }
            
            if (przven <= 0){
                if (tpelab.equals("all")){
                    fgabil = "N";
                }else {
                    System.err.println(cdarti+" - scartato : prezzo nullo");
                    continue;
                }
            }

            qtadisp = qtagia - qtaov;

            dsarti    = StringUtils.replace(dsarti, "\t","    ");
            cdartprod = StringUtils.replace(cdartprod, "\t","    ");
            

            if (qtadisp < 0){
                qtadisp = 0;
            }

            if (!fgabil.equals("S")){
                qtadisp  = 0;
                qtaoa    = 0;
                dtconsoa = null;
            }

            ls_rec += cdbarcode;
            ls_rec += "\t"+przFormat.format(przven);   
            ls_rec += "\t"+qtaFormat.format(qtadisp);   
            ls_rec += "\t"+0;
            ls_rec += "\t"+qtaFormat.format(qtaoa);
            ls_rec += "\t"+atk_ctrl.getDate(dtconsoa);
            ls_rec += "\t"+cdarti;
            ls_rec += "\t"+cdartprod;
            ls_rec += "\t"+dsarti;
            if (!ls_tkclie.equals("")){
            ls_rec += "\t"+cdacli;
            }
            
            println(ls_rec);


        }






        return;


    }




    

    /***


    */

    public int println ( String as_) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        as_ += "\r\n";

        if (out != null) {
            out.print(as_);
        }else {
            System.out.print(as_);
        }

        return  1;

    }





    public static void main(String[] args){

        if (args==null || args.length < 2){
            System.err.println("");
            System.err.println("");
            System.err.println("Argomenti validi:");
            System.err.println("-  start | stop");
            System.err.println("-  all   | valid");
            System.err.println("-  user_web:[User Name]");
            System.err.println("");
            System.err.println("Parametri Inseriti");
            for (int i=0; i<args.length; i++){
                
                System.err.println(">> "+args[i]);
            }

            System.gc();
            System.exit(0);
        }
        args[0] = args[0].toLowerCase();

        tpelab = args[1];
 
                
        for (int i=2; i<args.length; i++){
                
            String ls_parm = args[i].trim().toLowerCase();
                                 
            if (ls_parm.indexOf("user_web:")==0 && ls_parm.length()> ("user_web:".length())){
                elab_for_user = ls_parm.substring( ("user_web:".length()) );
            }
        }

        Srv_delo_exp obj = new Srv_delo_exp(args);

        System.gc();
        System.exit(0);
    }



    // *** Properties



    public static       Timestamp  oggi       = new Timestamp(System.currentTimeMillis());


    javax.servlet.jsp.JspWriter out = null;


    public static String tpelab = "";
    public static String elab_for_user = "";
    


}
