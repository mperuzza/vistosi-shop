package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_vist_mpron extends Object {

    public Rec_vist_mpron() {

        super();
    }

    public Rec_vist_mpron( com.ateikon.common.Vist_mpron obj_common_from
                       , com.ateikon.common.Vist_mpron obj_common_to   ) {

        super();

        if (obj_common_from !=null){
            this.obj_common_from = obj_common_from;
        }
        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public Rec_vist_mpron( com.ateikon.common.Vist_mpron obj_common_to   ) {

        super();

        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public com.ateikon.common.Vist_mpron obj_common_from = null;
    public com.ateikon.common.Vist_mpron obj_common_to   = null;


    public String seStesso = "Rec_vist_mpron";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public long       tkmpron = 0; 
    public String     tkclie = ""; 
    public long       tkmsg = 0; 
    public Timestamp  dtinvio = null; 
    public String     cdazie = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 
    public String     notapagame = ""; 
    public String     cdvett = ""; 
    public String     dsvett = ""; 
    public String     nota = ""; 
    public String     oggetto = ""; 
    public String     text_p1 = ""; 
    public String     text_p2 = ""; 
    public String     text_p3 = ""; 
    public String     text_p4 = ""; 
    public String     text_p5 = ""; 
    public String     text_p6 = ""; 
    public String     text_notapagame = ""; 
    public String     text_dsvett = ""; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.tkmpron = 0; 
        this.tkclie = ""; 
        this.tkmsg = 0; 
        this.dtinvio = null; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 
        this.notapagame = ""; 
        this.cdvett = ""; 
        this.dsvett = ""; 
        this.nota = ""; 
        this.oggetto = ""; 
        this.text_p1 = ""; 
        this.text_p2 = ""; 
        this.text_p3 = ""; 
        this.text_p4 = ""; 
        this.text_p5 = ""; 
        this.text_p6 = ""; 
        this.text_notapagame = ""; 
        this.text_dsvett = ""; 

    }




    /****
        copy_from()
    **/ 

    public void copy_from(Rec_vist_mpron astr) throws Exception{ 

        this.tkmpron = astr.tkmpron;
        this.tkclie = astr.tkclie;
        this.tkmsg = astr.tkmsg;
        if (astr.dtinvio == null){
           this.dtinvio = null;
        }else {
           this.dtinvio = (Timestamp ) astr.dtinvio.clone();
        }
        this.cdazie = astr.cdazie;
        this.cddipa = astr.cddipa;
        this.profil = astr.profil;
        if (astr.dtinse == null){
           this.dtinse = null;
        }else {
           this.dtinse = (Timestamp ) astr.dtinse.clone();
        }
        if (astr.dtulag == null){
           this.dtulag = null;
        }else {
           this.dtulag = (Timestamp ) astr.dtulag.clone();
        }
        this.notapagame = astr.notapagame;
        this.cdvett = astr.cdvett;
        this.dsvett = astr.dsvett;
        this.nota = astr.nota;
        this.oggetto = astr.oggetto;
        this.text_p1 = astr.text_p1;
        this.text_p2 = astr.text_p2;
        this.text_p3 = astr.text_p3;
        this.text_p4 = astr.text_p4;
        this.text_p5 = astr.text_p5;
        this.text_p6 = astr.text_p6;
        this.text_notapagame = astr.text_notapagame;
        this.text_dsvett = astr.text_dsvett;

    }





    /****
        setResultSet: vist_mpron
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkmpron")!=null){
            this.tkmpron = rs.getLong      ("tkmpron"); 
            if (obj_common_to   != null) obj_common_to.null_tkmpron = false;
            if (obj_common_from != null) obj_common_from.null_tkmpron = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tkmpron = true;
            if (obj_common_from != null) obj_common_from.null_tkmpron = true;
        }
        if (rs.getObject("tkclie")!=null) this.tkclie = rs.getString    ("tkclie"); 
        if (rs.getObject("tkmsg")!=null){
            this.tkmsg = rs.getLong      ("tkmsg"); 
            if (obj_common_to   != null) obj_common_to.null_tkmsg = false;
            if (obj_common_from != null) obj_common_from.null_tkmsg = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tkmsg = true;
            if (obj_common_from != null) obj_common_from.null_tkmsg = true;
        }
        if (rs.getObject("dtinvio")!=null) this.dtinvio = rs.getTimestamp ("dtinvio"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("notapagame")!=null) this.notapagame = rs.getString    ("notapagame"); 
        if (rs.getObject("cdvett")!=null) this.cdvett = rs.getString    ("cdvett"); 
        if (rs.getObject("dsvett")!=null) this.dsvett = rs.getString    ("dsvett"); 
        if (rs.getObject("nota")!=null) this.nota = rs.getString    ("nota"); 
        if (rs.getObject("oggetto")!=null) this.oggetto = rs.getString    ("oggetto"); 
        if (rs.getObject("text_p1")!=null) this.text_p1 = rs.getString    ("text_p1"); 
        if (rs.getObject("text_p2")!=null) this.text_p2 = rs.getString    ("text_p2"); 
        if (rs.getObject("text_p3")!=null) this.text_p3 = rs.getString    ("text_p3"); 
        if (rs.getObject("text_p4")!=null) this.text_p4 = rs.getString    ("text_p4"); 
        if (rs.getObject("text_p5")!=null) this.text_p5 = rs.getString    ("text_p5"); 
        if (rs.getObject("text_p6")!=null) this.text_p6 = rs.getString    ("text_p6"); 
        if (rs.getObject("text_notapagame")!=null) this.text_notapagame = rs.getString    ("text_notapagame"); 
        if (rs.getObject("text_dsvett")!=null) this.text_dsvett = rs.getString    ("text_dsvett"); 

        return 1;
    }




    /****
        setResultSet_key: vist_mpron
    **/ 


    public int setResultSet_key ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkmpron")!=null){
            this.tkmpron = rs.getLong      ("tkmpron"); 
            if (obj_common_to   != null) obj_common_to.null_tkmpron = false;
            if (obj_common_from != null) obj_common_from.null_tkmpron = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tkmpron = true;
            if (obj_common_from != null) obj_common_from.null_tkmpron = true;
        }

        return 1;
    }




    /****
        trim(): vist_mpron
    **/ 


    public void trim( ) throws Exception {

        this.tkclie = this.tkclie.trim(); 
        this.cdazie = this.cdazie.trim(); 
        this.cddipa = this.cddipa.trim(); 
        this.profil = this.profil.trim(); 
        this.notapagame = this.notapagame.trim(); 
        this.cdvett = this.cdvett.trim(); 
        this.dsvett = this.dsvett.trim(); 
        this.nota = this.nota.trim(); 
        this.oggetto = this.oggetto.trim(); 
        this.text_p1 = this.text_p1.trim(); 
        this.text_p2 = this.text_p2.trim(); 
        this.text_p3 = this.text_p3.trim(); 
        this.text_p4 = this.text_p4.trim(); 
        this.text_p5 = this.text_p5.trim(); 
        this.text_p6 = this.text_p6.trim(); 
        this.text_notapagame = this.text_notapagame.trim(); 
        this.text_dsvett = this.text_dsvett.trim(); 

    }



    public void rtrim( ) throws Exception {

        this.tkclie = of_rtrim(this.tkclie); 
        this.cdazie = of_rtrim(this.cdazie); 
        this.cddipa = of_rtrim(this.cddipa); 
        this.profil = of_rtrim(this.profil); 
        this.notapagame = of_rtrim(this.notapagame); 
        this.cdvett = of_rtrim(this.cdvett); 
        this.dsvett = of_rtrim(this.dsvett); 
        this.nota = of_rtrim(this.nota); 
        this.oggetto = of_rtrim(this.oggetto); 
        this.text_p1 = of_rtrim(this.text_p1); 
        this.text_p2 = of_rtrim(this.text_p2); 
        this.text_p3 = of_rtrim(this.text_p3); 
        this.text_p4 = of_rtrim(this.text_p4); 
        this.text_p5 = of_rtrim(this.text_p5); 
        this.text_p6 = of_rtrim(this.text_p6); 
        this.text_notapagame = of_rtrim(this.text_notapagame); 
        this.text_dsvett = of_rtrim(this.text_dsvett); 

    }


    public String of_rtrim( String as_ ) throws Exception {

        String ls_pref = "";
        int    pos     = 0; 
        int    len     = as_.length();

        while(pos < len && as_.charAt(pos) == ' '){
            ls_pref += " "; 
            pos += 1;
        }
        as_ = ls_pref + as_.trim();

        return as_;
    }




    /****
        ctrl_mindate(): vist_mpron
    **/ 


    public void ctrl_mindate( ) throws Exception {

        int li_sa = 0; 

        li_sa = 0; 
        if (this.dtinvio != null){ 
            li_sa = this.dtinvio.getYear()+1900; 
            if (li_sa < 1900){ 
                this.dtinvio.setYear(0); 
                this.dtinvio.setMonth(0); 
                this.dtinvio.setDate(1); 
                this.dtinvio.setHours(0); 
                this.dtinvio.setMinutes(0); 
                this.dtinvio.setSeconds(0); 
                this.dtinvio.setNanos(0); 
            } 
            this.dtinvio.setNanos(0); 
            this.dtinvio.toString(); 
        } 
        li_sa = 0; 
        if (this.dtinse != null){ 
            li_sa = this.dtinse.getYear()+1900; 
            if (li_sa < 1900){ 
                this.dtinse.setYear(0); 
                this.dtinse.setMonth(0); 
                this.dtinse.setDate(1); 
                this.dtinse.setHours(0); 
                this.dtinse.setMinutes(0); 
                this.dtinse.setSeconds(0); 
                this.dtinse.setNanos(0); 
            } 
            this.dtinse.setNanos(0); 
            this.dtinse.toString(); 
        } 
        li_sa = 0; 
        if (this.dtulag != null){ 
            li_sa = this.dtulag.getYear()+1900; 
            if (li_sa < 1900){ 
                this.dtulag.setYear(0); 
                this.dtulag.setMonth(0); 
                this.dtulag.setDate(1); 
                this.dtulag.setHours(0); 
                this.dtulag.setMinutes(0); 
                this.dtulag.setSeconds(0); 
                this.dtulag.setNanos(0); 
            } 
            this.dtulag.setNanos(0); 
            this.dtulag.toString(); 
        } 

    }




    /****
        requestValues(request):   
    **/ 

    public void requestValues(javax.servlet.http.HttpServletRequest request) throws Exception{ 

        if (request.getParameterValues("tkmpron")!=null) arr_tkmpron = request.getParameterValues("tkmpron");
        if (request.getParameterValues("tkclie")!=null) arr_tkclie = request.getParameterValues("tkclie");
        if (request.getParameterValues("tkmsg")!=null) arr_tkmsg = request.getParameterValues("tkmsg");
        if (request.getParameterValues("dtinvio")!=null) arr_dtinvio = request.getParameterValues("dtinvio");
        if (request.getParameterValues("cdazie")!=null) arr_cdazie = request.getParameterValues("cdazie");
        if (request.getParameterValues("cddipa")!=null) arr_cddipa = request.getParameterValues("cddipa");
        if (request.getParameterValues("profil")!=null) arr_profil = request.getParameterValues("profil");
        if (request.getParameterValues("dtinse")!=null) arr_dtinse = request.getParameterValues("dtinse");
        if (request.getParameterValues("dtulag")!=null) arr_dtulag = request.getParameterValues("dtulag");
        if (request.getParameterValues("notapagame")!=null) arr_notapagame = request.getParameterValues("notapagame");
        if (request.getParameterValues("cdvett")!=null) arr_cdvett = request.getParameterValues("cdvett");
        if (request.getParameterValues("dsvett")!=null) arr_dsvett = request.getParameterValues("dsvett");
        if (request.getParameterValues("nota")!=null) arr_nota = request.getParameterValues("nota");
        if (request.getParameterValues("oggetto")!=null) arr_oggetto = request.getParameterValues("oggetto");
        if (request.getParameterValues("text_p1")!=null) arr_text_p1 = request.getParameterValues("text_p1");
        if (request.getParameterValues("text_p2")!=null) arr_text_p2 = request.getParameterValues("text_p2");
        if (request.getParameterValues("text_p3")!=null) arr_text_p3 = request.getParameterValues("text_p3");
        if (request.getParameterValues("text_p4")!=null) arr_text_p4 = request.getParameterValues("text_p4");
        if (request.getParameterValues("text_p5")!=null) arr_text_p5 = request.getParameterValues("text_p5");
        if (request.getParameterValues("text_p6")!=null) arr_text_p6 = request.getParameterValues("text_p6");
        if (request.getParameterValues("text_notapagame")!=null) arr_text_notapagame = request.getParameterValues("text_notapagame");
        if (request.getParameterValues("text_dsvett")!=null) arr_text_dsvett = request.getParameterValues("text_dsvett");

        return ;
    }

    public String[] arr_tkmpron = null; 
    public String[] arr_tkclie = null; 
    public String[] arr_tkmsg = null; 
    public String[] arr_dtinvio = null; 
    public String[] arr_cdazie = null; 
    public String[] arr_cddipa = null; 
    public String[] arr_profil = null; 
    public String[] arr_dtinse = null; 
    public String[] arr_dtulag = null; 
    public String[] arr_notapagame = null; 
    public String[] arr_cdvett = null; 
    public String[] arr_dsvett = null; 
    public String[] arr_nota = null; 
    public String[] arr_oggetto = null; 
    public String[] arr_text_p1 = null; 
    public String[] arr_text_p2 = null; 
    public String[] arr_text_p3 = null; 
    public String[] arr_text_p4 = null; 
    public String[] arr_text_p5 = null; 
    public String[] arr_text_p6 = null; 
    public String[] arr_text_notapagame = null; 
    public String[] arr_text_dsvett = null; 





}

