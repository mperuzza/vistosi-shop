package com.ateikon.structure;

import java.sql.Timestamp;




public class Str_search_articoli extends Object {

    public Str_search_articoli() {

        super();
    }


    public String seStesso = "Str_search_articoli";

    public String rc          = "";
    public String message     = "";




    /****
        Properties 
    **/ 


    public  String  f_cdarti        = "";
    public  String  f_search        = "";
    public  String  f_cdrepa        = "";
    public  String  f_cdtipm        = "";
    public  String  f_cdclas_a      = "";
    public  String  f_cdclas_a_m    = "";
    public  String  f_cdartipo      = "";
    public  String  f_cdartprod     = "";
    public  String  f_cdmatricola_m = "";
    public  String  f_cata_ridotto  = "N";

    public  String  f_cdartm    = "";
    public  String  f_dsarti    = "";

    public  String  f_pacco_si_no = "";

    public  double  f_dimenamin = 0;
    public  double  f_dimenamax = 0;
    public  double  f_ncollia   = 0;
                    
    public  double  f_dimenbmin = 0;
    public  double  f_dimenbmax = 0;
    public  double  f_ncollib   = 0;
                    
    public  double  f_dimencmin = 0;
    public  double  f_dimencmax = 0;
    public  double  f_ncollic   = 0;

    public  double  f_giacmin   = 0;
   


    // --- Appoggio Request


    public  String  ls_dimenamin = "";
    public  String  ls_dimenamax = "";
    public  String  ls_ncollia   = "";
    public  String  ls_dimenbmin = "";
    public  String  ls_dimenbmax = "";
    public  String  ls_ncollib   = "";
    public  String  ls_dimencmin = "";
    public  String  ls_dimencmax = "";
    public  String  ls_ncollic   = "";
    public  String  ls_giacmin   = "";






    /***


    */

    public void copia (Str_search_articoli lstr ) throws Exception {


        f_cdarti        = lstr.f_cdarti      ;
        f_search        = lstr.f_search      ;
        f_cdrepa        = lstr.f_cdrepa      ;
        f_cdtipm        = lstr.f_cdtipm      ;
        f_cdclas_a      = lstr.f_cdclas_a    ;
        f_cdclas_a_m    = lstr.f_cdclas_a_m  ;
        f_cdartipo      = lstr.f_cdartipo    ;
        f_cdartprod     = lstr.f_cdartprod   ;
        f_cdmatricola_m = lstr.f_cdmatricola_m;
        f_cata_ridotto  = lstr.f_cata_ridotto;
        
        f_cdartm    = lstr.f_cdartm    ;
        f_dsarti    = lstr.f_dsarti    ;
        
        f_pacco_si_no = lstr.f_pacco_si_no;
        f_dimenamin = lstr.f_dimenamin ;
        f_dimenamax = lstr.f_dimenamax ;
        f_ncollia   = lstr.f_ncollia   ;
        
        f_dimenbmin = lstr.f_dimenbmin ;
        f_dimenbmax = lstr.f_dimenbmax ;
        f_ncollib   = lstr.f_ncollib   ;
        
        f_dimencmin = lstr.f_dimencmin ;
        f_dimencmax = lstr.f_dimencmax ;
        f_ncollic   = lstr.f_ncollic   ;
        
        f_giacmin   = lstr.f_giacmin   ;
        
        
        ls_dimenamin = lstr.ls_dimenamin;
        ls_dimenamax = lstr.ls_dimenamax;
        ls_ncollia   = lstr.ls_ncollia  ;
        ls_dimenbmin = lstr.ls_dimenbmin;
        ls_dimenbmax = lstr.ls_dimenbmax;
        ls_ncollib   = lstr.ls_ncollib  ;
        ls_dimencmin = lstr.ls_dimencmin;
        ls_dimencmax = lstr.ls_dimencmax;
        ls_ncollic   = lstr.ls_ncollic  ;
        ls_giacmin   = lstr.ls_giacmin  ;


        return ;

    }



    /***


    */

    public String getHidden ( ) throws Exception {

        com.ateikon.util.HTML html = new com.ateikon.util.HTML();

        String ls_ = "";

        ls_ += "<input type=\"hidden\" name=\"f_cdarti\"        value=\""+html.text(f_cdarti       )+"\"/>";
        ls_ += "<input type=\"hidden\" name=\"f_search\"        value=\""+html.text(f_search       )+"\"/>";
        ls_ += "<input type=\"hidden\" name=\"f_cdrepa\"        value=\""+html.text(f_cdrepa       )+"\"/>";
        ls_ += "<input type=\"hidden\" name=\"f_cdtipm\"        value=\""+html.text(f_cdtipm       )+"\"/>";
        ls_ += "<input type=\"hidden\" name=\"f_cdclas_a\"      value=\""+html.text(f_cdclas_a     )+"\"/>";
        ls_ += "<input type=\"hidden\" name=\"f_cdclas_a_m\"    value=\""+html.text(f_cdclas_a_m   )+"\"/>";
        ls_ += "<input type=\"hidden\" name=\"f_cdartipo\"      value=\""+html.text(f_cdartipo     )+"\"/>";
        ls_ += "<input type=\"hidden\" name=\"f_cdartprod\"     value=\""+html.text(f_cdartprod    )+"\"/>";
        ls_ += "<input type=\"hidden\" name=\"f_cdmatricola_m\" value=\""+html.text(f_cdmatricola_m)+"\"/>";
        ls_ += "<input type=\"hidden\" name=\"f_cata_ridotto\"  value=\""+html.text(f_cata_ridotto )+"\"/>";


        ls_ += "<input type=\"hidden\" name=\"f_cdartm\" value=\""+html.text(f_cdartm)+"\"/>";
        ls_ += "<input type=\"hidden\" name=\"f_dsarti\" value=\""+html.text(f_dsarti)+"\"/>";

        ls_ += "<input type=\"hidden\" name=\"f_pacco_si_no\"   value=\""+html.text(f_pacco_si_no  )+"\"/>";

        ls_ += "<input type=\"hidden\" name=\"f_dimenamin\" value=\""+html.text(ls_dimenamin)+"\"/>";
        ls_ += "<input type=\"hidden\" name=\"f_dimenamax\" value=\""+html.text(ls_dimenamax)+"\"/>";
        ls_ += "<input type=\"hidden\" name=\"f_ncollia\"   value=\""+html.text(ls_ncollia  )+"\"/>";

        ls_ += "<input type=\"hidden\" name=\"f_dimenbmin\" value=\""+html.text(ls_dimenbmin)+"\"/>";
        ls_ += "<input type=\"hidden\" name=\"f_dimenbmax\" value=\""+html.text(ls_dimenbmax)+"\"/>";
        ls_ += "<input type=\"hidden\" name=\"f_ncollib\"   value=\""+html.text(ls_ncollib  )+"\"/>";

        ls_ += "<input type=\"hidden\" name=\"f_dimencmin\" value=\""+html.text(ls_dimencmin)+"\"/>";
        ls_ += "<input type=\"hidden\" name=\"f_dimencmax\" value=\""+html.text(ls_dimencmax)+"\"/>";
        ls_ += "<input type=\"hidden\" name=\"f_ncollic\"   value=\""+html.text(ls_ncollic  )+"\"/>";

        ls_ += "<input type=\"hidden\" name=\"f_giacmin\"   value=\""+html.text(ls_giacmin  )+"\"/>";
        
                                                                                             
                                                                                             
        return ls_ ;

    }



    /***


    */

    public String ctrl( javax.servlet.http.HttpServletRequest request 
                                ) throws Exception {
        
        message = "";
        rc = "0";

        com.ateikon.util.Atk_ctrl atk_ctrl = new com.ateikon.util.Atk_ctrl();

        if (request.getParameter("f_search"   ) !=null) f_search     = request.getParameter("f_search"   );
        if (request.getParameter("f_cdrepa"   ) !=null) f_cdrepa     = request.getParameter("f_cdrepa"   );
        if (request.getParameter("f_cdtipm"   ) !=null) f_cdtipm     = request.getParameter("f_cdtipm"   );
        if (request.getParameter("f_cdclas_a" ) !=null) f_cdclas_a   = request.getParameter("f_cdclas_a" );
        if (request.getParameter("f_cdclas_a_m" ) !=null) f_cdclas_a_m   = request.getParameter("f_cdclas_a_m" );
        if (request.getParameter("f_cdartipo" ) !=null) f_cdartipo   = request.getParameter("f_cdartipo" );
        if (request.getParameter("f_cdartprod"  ) !=null) f_cdartprod    = request.getParameter("f_cdartprod"  );
        if (request.getParameter("f_cdmatricola_m"  ) !=null) f_cdmatricola_m    = request.getParameter("f_cdmatricola_m"  );
    

        if (request.getParameter("f_cdartm") !=null) f_cdartm = request.getParameter("f_cdartm");
        if (request.getParameter("f_dsarti") !=null) f_dsarti = request.getParameter("f_dsarti");
        
        if (request.getParameter("f_pacco_si_no") !=null) f_pacco_si_no = request.getParameter("f_pacco_si_no");
        

        if (request.getParameter("f_dimenamin" ) !=null) ls_dimenamin  = request.getParameter("f_dimenamin" );
        if (request.getParameter("f_dimenamax" ) !=null) ls_dimenamax  = request.getParameter("f_dimenamax" );
        if (request.getParameter("f_ncollia"   ) !=null) ls_ncollia    = request.getParameter("f_ncollia"   );
        
        if (request.getParameter("f_dimenbmin" ) !=null) ls_dimenbmin  = request.getParameter("f_dimenbmin" );
        if (request.getParameter("f_dimenbmax" ) !=null) ls_dimenbmax  = request.getParameter("f_dimenbmax" );
        if (request.getParameter("f_ncollib"   ) !=null) ls_ncollib    = request.getParameter("f_ncollib"   );
        
        if (request.getParameter("f_dimencmin" ) !=null) ls_dimencmin  = request.getParameter("f_dimencmin" );
        if (request.getParameter("f_dimencmax" ) !=null) ls_dimencmax  = request.getParameter("f_dimencmax" );
        if (request.getParameter("f_ncollic"   ) !=null) ls_ncollic    = request.getParameter("f_ncollic"   );
        if (request.getParameter("f_giacmin"   ) !=null) ls_giacmin    = request.getParameter("f_giacmin"   );
        
        ls_dimenamin  = ls_dimenamin.trim();
        ls_dimenamax  = ls_dimenamax.trim();
        ls_ncollia    = ls_ncollia.trim();
    
        ls_dimenbmin  = ls_dimenbmin.trim();
        ls_dimenbmax  = ls_dimenbmax.trim();
        ls_ncollib    = ls_ncollib.trim();
    
        ls_dimencmin  = ls_dimencmin.trim();
        ls_dimencmax  = ls_dimencmax.trim();
        ls_ncollic    = ls_ncollic.trim();
    
        ls_giacmin    = ls_giacmin.trim();
         
    
        if (ls_dimenamin.equals(""))   ls_dimenamin = "0";
        if (ls_dimenamax.equals(""))   ls_dimenamax = "0";
        if (ls_ncollia.equals("")  )   ls_ncollia   = "0";
        
        if (ls_dimenbmin.equals(""))   ls_dimenbmin = "0";
        if (ls_dimenbmax.equals(""))   ls_dimenbmax = "0";
        if (ls_ncollib.equals("")  )   ls_ncollib   = "0";
    
        if (ls_dimencmin.equals(""))   ls_dimencmin = "0";
        if (ls_dimencmax.equals(""))   ls_dimencmax = "0";
        if (ls_ncollic.equals("")  )   ls_ncollic   = "0";
    
        if (ls_giacmin.equals("")  )   ls_giacmin   = "0";
    
    
    
    
        f_dimenamin = atk_ctrl.of_strToDouble( ls_dimenamin, "Lung. DA" );
        if (!atk_ctrl.rc.equals("0")) message += atk_ctrl.message;
    
        f_dimenamax = atk_ctrl.of_strToDouble( ls_dimenamax, "Lung. A" );
        if (!atk_ctrl.rc.equals("0")) message += atk_ctrl.message;
    
        f_ncollia   = atk_ctrl.of_strToDouble( ls_ncollia  , "Pezzi (Larg.)" );
        if (!atk_ctrl.rc.equals("0")) message += atk_ctrl.message;
    
        //-----------------------------------------------------------------------
        f_dimenbmin = atk_ctrl.of_strToDouble( ls_dimenbmin, "Larg. DA" );
        if (!atk_ctrl.rc.equals("0")) message += atk_ctrl.message;
    
        f_dimenbmax = atk_ctrl.of_strToDouble( ls_dimenbmax, "Larg. A" );
        if (!atk_ctrl.rc.equals("0")) message += atk_ctrl.message;
    
        f_ncollib   = atk_ctrl.of_strToDouble( ls_ncollib  , "Pezzi (Larg.)" );
        if (!atk_ctrl.rc.equals("0")) message += atk_ctrl.message;
    
        //-----------------------------------------------------------------------
        f_dimencmin = atk_ctrl.of_strToDouble( ls_dimencmin, "Spess. DA" );
        if (!atk_ctrl.rc.equals("0")) message += atk_ctrl.message;
    
        f_dimencmax = atk_ctrl.of_strToDouble( ls_dimencmax, "Spess. A" );
        if (!atk_ctrl.rc.equals("0")) message += atk_ctrl.message;
    
        f_ncollic   = atk_ctrl.of_strToDouble( ls_ncollic  , "Pezzi (Spess.)" );
        if (!atk_ctrl.rc.equals("0")) message += atk_ctrl.message;
    
    
    
        f_giacmin = atk_ctrl.of_strToDouble( ls_giacmin, "Giac. Min" );
    
        if (!atk_ctrl.rc.equals("0")){
            message += atk_ctrl.message;
        }

        if (!message.equals("")){
            rc = "-1";
        }


        return message ;

    }











}


