package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_msg_tpcomgen extends Object {

    public Rec_msg_tpcomgen() {

        super();
    }

    public Rec_msg_tpcomgen( com.ateikon.common.Msg_tpcomgen obj_common_from
                       , com.ateikon.common.Msg_tpcomgen obj_common_to   ) {

        super();

        if (obj_common_from !=null){
            this.obj_common_from = obj_common_from;
        }
        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public Rec_msg_tpcomgen( com.ateikon.common.Msg_tpcomgen obj_common_to   ) {

        super();

        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public com.ateikon.common.Msg_tpcomgen obj_common_from = null;
    public com.ateikon.common.Msg_tpcomgen obj_common_to   = null;


    public String seStesso = "Rec_msg_tpcomgen";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public long       tktpcomgen = 0; 
    public String     dstpcomgen = ""; 
    public String     cdazie = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 
    public String     cdtpcomgen_m = ""; 
    public String     dstpcomgen_en = ""; 
    public String     dstpcomgen_de = ""; 
    public String     dstpcomgen_fr = ""; 
    public String     dstpcomgen_es = ""; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.tktpcomgen = 0; 
        this.dstpcomgen = ""; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 
        this.cdtpcomgen_m = ""; 
        this.dstpcomgen_en = ""; 
        this.dstpcomgen_de = ""; 
        this.dstpcomgen_fr = ""; 
        this.dstpcomgen_es = ""; 

    }




    /****
        copy_from()
    **/ 

    public void copy_from(Rec_msg_tpcomgen astr) throws Exception{ 

        this.tktpcomgen = astr.tktpcomgen;
        this.dstpcomgen = astr.dstpcomgen;
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
        this.cdtpcomgen_m = astr.cdtpcomgen_m;
        this.dstpcomgen_en = astr.dstpcomgen_en;
        this.dstpcomgen_de = astr.dstpcomgen_de;
        this.dstpcomgen_fr = astr.dstpcomgen_fr;
        this.dstpcomgen_es = astr.dstpcomgen_es;

    }





    /****
        setResultSet: msg_tpcomgen
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tktpcomgen")!=null){
            this.tktpcomgen = rs.getLong      ("tktpcomgen"); 
            if (obj_common_to   != null) obj_common_to.null_tktpcomgen = false;
            if (obj_common_from != null) obj_common_from.null_tktpcomgen = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tktpcomgen = true;
            if (obj_common_from != null) obj_common_from.null_tktpcomgen = true;
        }
        if (rs.getObject("dstpcomgen")!=null) this.dstpcomgen = rs.getString    ("dstpcomgen"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("cdtpcomgen_m")!=null) this.cdtpcomgen_m = rs.getString    ("cdtpcomgen_m"); 
        if (rs.getObject("dstpcomgen_en")!=null) this.dstpcomgen_en = rs.getString    ("dstpcomgen_en"); 
        if (rs.getObject("dstpcomgen_de")!=null) this.dstpcomgen_de = rs.getString    ("dstpcomgen_de"); 
        if (rs.getObject("dstpcomgen_fr")!=null) this.dstpcomgen_fr = rs.getString    ("dstpcomgen_fr"); 
        if (rs.getObject("dstpcomgen_es")!=null) this.dstpcomgen_es = rs.getString    ("dstpcomgen_es"); 

        return 1;
    }




    /****
        setResultSet_key: msg_tpcomgen
    **/ 


    public int setResultSet_key ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tktpcomgen")!=null){
            this.tktpcomgen = rs.getLong      ("tktpcomgen"); 
            if (obj_common_to   != null) obj_common_to.null_tktpcomgen = false;
            if (obj_common_from != null) obj_common_from.null_tktpcomgen = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tktpcomgen = true;
            if (obj_common_from != null) obj_common_from.null_tktpcomgen = true;
        }

        return 1;
    }




    /****
        trim(): msg_tpcomgen
    **/ 


    public void trim( ) throws Exception {

        this.dstpcomgen = this.dstpcomgen.trim(); 
        this.cdazie = this.cdazie.trim(); 
        this.cddipa = this.cddipa.trim(); 
        this.profil = this.profil.trim(); 
        this.cdtpcomgen_m = this.cdtpcomgen_m.trim(); 
        this.dstpcomgen_en = this.dstpcomgen_en.trim(); 
        this.dstpcomgen_de = this.dstpcomgen_de.trim(); 
        this.dstpcomgen_fr = this.dstpcomgen_fr.trim(); 
        this.dstpcomgen_es = this.dstpcomgen_es.trim(); 

    }



    public void rtrim( ) throws Exception {

        this.dstpcomgen = of_rtrim(this.dstpcomgen); 
        this.cdazie = of_rtrim(this.cdazie); 
        this.cddipa = of_rtrim(this.cddipa); 
        this.profil = of_rtrim(this.profil); 
        this.cdtpcomgen_m = of_rtrim(this.cdtpcomgen_m); 
        this.dstpcomgen_en = of_rtrim(this.dstpcomgen_en); 
        this.dstpcomgen_de = of_rtrim(this.dstpcomgen_de); 
        this.dstpcomgen_fr = of_rtrim(this.dstpcomgen_fr); 
        this.dstpcomgen_es = of_rtrim(this.dstpcomgen_es); 

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
        ctrl_mindate(): msg_tpcomgen
    **/ 


    public void ctrl_mindate( ) throws Exception {

        int li_sa = 0; 

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

        if (request.getParameterValues("tktpcomgen")!=null) arr_tktpcomgen = request.getParameterValues("tktpcomgen");
        if (request.getParameterValues("dstpcomgen")!=null) arr_dstpcomgen = request.getParameterValues("dstpcomgen");
        if (request.getParameterValues("cdazie")!=null) arr_cdazie = request.getParameterValues("cdazie");
        if (request.getParameterValues("cddipa")!=null) arr_cddipa = request.getParameterValues("cddipa");
        if (request.getParameterValues("profil")!=null) arr_profil = request.getParameterValues("profil");
        if (request.getParameterValues("dtinse")!=null) arr_dtinse = request.getParameterValues("dtinse");
        if (request.getParameterValues("dtulag")!=null) arr_dtulag = request.getParameterValues("dtulag");
        if (request.getParameterValues("cdtpcomgen_m")!=null) arr_cdtpcomgen_m = request.getParameterValues("cdtpcomgen_m");
        if (request.getParameterValues("dstpcomgen_en")!=null) arr_dstpcomgen_en = request.getParameterValues("dstpcomgen_en");
        if (request.getParameterValues("dstpcomgen_de")!=null) arr_dstpcomgen_de = request.getParameterValues("dstpcomgen_de");
        if (request.getParameterValues("dstpcomgen_fr")!=null) arr_dstpcomgen_fr = request.getParameterValues("dstpcomgen_fr");
        if (request.getParameterValues("dstpcomgen_es")!=null) arr_dstpcomgen_es = request.getParameterValues("dstpcomgen_es");

        return ;
    }

    public String[] arr_tktpcomgen = null; 
    public String[] arr_dstpcomgen = null; 
    public String[] arr_cdazie = null; 
    public String[] arr_cddipa = null; 
    public String[] arr_profil = null; 
    public String[] arr_dtinse = null; 
    public String[] arr_dtulag = null; 
    public String[] arr_cdtpcomgen_m = null; 
    public String[] arr_dstpcomgen_en = null; 
    public String[] arr_dstpcomgen_de = null; 
    public String[] arr_dstpcomgen_fr = null; 
    public String[] arr_dstpcomgen_es = null; 





}

