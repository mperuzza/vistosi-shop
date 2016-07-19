package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_ep_msgmod_par extends Object {

    public Rec_ep_msgmod_par() {

        super();
    }

    public Rec_ep_msgmod_par( com.ateikon.common.Ep_msgmod_par obj_common_from
                       , com.ateikon.common.Ep_msgmod_par obj_common_to   ) {

        super();

        if (obj_common_from !=null){
            this.obj_common_from = obj_common_from;
        }
        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public Rec_ep_msgmod_par( com.ateikon.common.Ep_msgmod_par obj_common_to   ) {

        super();

        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public com.ateikon.common.Ep_msgmod_par obj_common_from = null;
    public com.ateikon.common.Ep_msgmod_par obj_common_to   = null;


    public String seStesso = "Rec_ep_msgmod_par";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public long       tkmsgmod_par = 0; 
    public String     cdmsgmod_appl = ""; 
    public String     cdmsgmod_par = ""; 
    public String     dsmsgmod_par = ""; 
    public String     categoria_par = ""; 
    public String     cdazie = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 
    public String     fg_lower = ""; 
    public String     fg_upper = ""; 
    public String     fg_capitalize = ""; 
    public String     fg_ori_lower = ""; 
    public String     fg_ori_upper = ""; 
    public String     fg_ori_capitalize = ""; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.tkmsgmod_par = 0; 
        this.cdmsgmod_appl = ""; 
        this.cdmsgmod_par = ""; 
        this.dsmsgmod_par = ""; 
        this.categoria_par = ""; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 
        this.fg_lower = ""; 
        this.fg_upper = ""; 
        this.fg_capitalize = ""; 
        this.fg_ori_lower = ""; 
        this.fg_ori_upper = ""; 
        this.fg_ori_capitalize = ""; 

    }




    /****
        copy_from()
    **/ 

    public void copy_from(Rec_ep_msgmod_par astr) throws Exception{ 

        this.tkmsgmod_par = astr.tkmsgmod_par;
        this.cdmsgmod_appl = astr.cdmsgmod_appl;
        this.cdmsgmod_par = astr.cdmsgmod_par;
        this.dsmsgmod_par = astr.dsmsgmod_par;
        this.categoria_par = astr.categoria_par;
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
        this.fg_lower = astr.fg_lower;
        this.fg_upper = astr.fg_upper;
        this.fg_capitalize = astr.fg_capitalize;
        this.fg_ori_lower = astr.fg_ori_lower;
        this.fg_ori_upper = astr.fg_ori_upper;
        this.fg_ori_capitalize = astr.fg_ori_capitalize;

    }





    /****
        setResultSet: ep_msgmod_par
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkmsgmod_par")!=null){
            this.tkmsgmod_par = rs.getLong      ("tkmsgmod_par"); 
            if (obj_common_to   != null) obj_common_to.null_tkmsgmod_par = false;
            if (obj_common_from != null) obj_common_from.null_tkmsgmod_par = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tkmsgmod_par = true;
            if (obj_common_from != null) obj_common_from.null_tkmsgmod_par = true;
        }
        if (rs.getObject("cdmsgmod_appl")!=null) this.cdmsgmod_appl = rs.getString    ("cdmsgmod_appl"); 
        if (rs.getObject("cdmsgmod_par")!=null) this.cdmsgmod_par = rs.getString    ("cdmsgmod_par"); 
        if (rs.getObject("dsmsgmod_par")!=null) this.dsmsgmod_par = rs.getString    ("dsmsgmod_par"); 
        if (rs.getObject("categoria_par")!=null) this.categoria_par = rs.getString    ("categoria_par"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("fg_lower")!=null) this.fg_lower = rs.getString    ("fg_lower"); 
        if (rs.getObject("fg_upper")!=null) this.fg_upper = rs.getString    ("fg_upper"); 
        if (rs.getObject("fg_capitalize")!=null) this.fg_capitalize = rs.getString    ("fg_capitalize"); 
        if (rs.getObject("fg_ori_lower")!=null) this.fg_ori_lower = rs.getString    ("fg_ori_lower"); 
        if (rs.getObject("fg_ori_upper")!=null) this.fg_ori_upper = rs.getString    ("fg_ori_upper"); 
        if (rs.getObject("fg_ori_capitalize")!=null) this.fg_ori_capitalize = rs.getString    ("fg_ori_capitalize"); 

        return 1;
    }




    /****
        setResultSet_key: ep_msgmod_par
    **/ 


    public int setResultSet_key ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkmsgmod_par")!=null){
            this.tkmsgmod_par = rs.getLong      ("tkmsgmod_par"); 
            if (obj_common_to   != null) obj_common_to.null_tkmsgmod_par = false;
            if (obj_common_from != null) obj_common_from.null_tkmsgmod_par = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tkmsgmod_par = true;
            if (obj_common_from != null) obj_common_from.null_tkmsgmod_par = true;
        }

        return 1;
    }




    /****
        trim(): ep_msgmod_par
    **/ 


    public void trim( ) throws Exception {

        this.cdmsgmod_appl = this.cdmsgmod_appl.trim(); 
        this.cdmsgmod_par = this.cdmsgmod_par.trim(); 
        this.dsmsgmod_par = this.dsmsgmod_par.trim(); 
        this.categoria_par = this.categoria_par.trim(); 
        this.cdazie = this.cdazie.trim(); 
        this.cddipa = this.cddipa.trim(); 
        this.profil = this.profil.trim(); 
        this.fg_lower = this.fg_lower.trim(); 
        this.fg_upper = this.fg_upper.trim(); 
        this.fg_capitalize = this.fg_capitalize.trim(); 
        this.fg_ori_lower = this.fg_ori_lower.trim(); 
        this.fg_ori_upper = this.fg_ori_upper.trim(); 
        this.fg_ori_capitalize = this.fg_ori_capitalize.trim(); 

    }



    public void rtrim( ) throws Exception {

        this.cdmsgmod_appl = of_rtrim(this.cdmsgmod_appl); 
        this.cdmsgmod_par = of_rtrim(this.cdmsgmod_par); 
        this.dsmsgmod_par = of_rtrim(this.dsmsgmod_par); 
        this.categoria_par = of_rtrim(this.categoria_par); 
        this.cdazie = of_rtrim(this.cdazie); 
        this.cddipa = of_rtrim(this.cddipa); 
        this.profil = of_rtrim(this.profil); 
        this.fg_lower = of_rtrim(this.fg_lower); 
        this.fg_upper = of_rtrim(this.fg_upper); 
        this.fg_capitalize = of_rtrim(this.fg_capitalize); 
        this.fg_ori_lower = of_rtrim(this.fg_ori_lower); 
        this.fg_ori_upper = of_rtrim(this.fg_ori_upper); 
        this.fg_ori_capitalize = of_rtrim(this.fg_ori_capitalize); 

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
        ctrl_mindate(): ep_msgmod_par
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

        if (request.getParameterValues("tkmsgmod_par")!=null) arr_tkmsgmod_par = request.getParameterValues("tkmsgmod_par");
        if (request.getParameterValues("cdmsgmod_appl")!=null) arr_cdmsgmod_appl = request.getParameterValues("cdmsgmod_appl");
        if (request.getParameterValues("cdmsgmod_par")!=null) arr_cdmsgmod_par = request.getParameterValues("cdmsgmod_par");
        if (request.getParameterValues("dsmsgmod_par")!=null) arr_dsmsgmod_par = request.getParameterValues("dsmsgmod_par");
        if (request.getParameterValues("categoria_par")!=null) arr_categoria_par = request.getParameterValues("categoria_par");
        if (request.getParameterValues("cdazie")!=null) arr_cdazie = request.getParameterValues("cdazie");
        if (request.getParameterValues("cddipa")!=null) arr_cddipa = request.getParameterValues("cddipa");
        if (request.getParameterValues("profil")!=null) arr_profil = request.getParameterValues("profil");
        if (request.getParameterValues("dtinse")!=null) arr_dtinse = request.getParameterValues("dtinse");
        if (request.getParameterValues("dtulag")!=null) arr_dtulag = request.getParameterValues("dtulag");
        if (request.getParameterValues("fg_lower")!=null) arr_fg_lower = request.getParameterValues("fg_lower");
        if (request.getParameterValues("fg_upper")!=null) arr_fg_upper = request.getParameterValues("fg_upper");
        if (request.getParameterValues("fg_capitalize")!=null) arr_fg_capitalize = request.getParameterValues("fg_capitalize");
        if (request.getParameterValues("fg_ori_lower")!=null) arr_fg_ori_lower = request.getParameterValues("fg_ori_lower");
        if (request.getParameterValues("fg_ori_upper")!=null) arr_fg_ori_upper = request.getParameterValues("fg_ori_upper");
        if (request.getParameterValues("fg_ori_capitalize")!=null) arr_fg_ori_capitalize = request.getParameterValues("fg_ori_capitalize");

        return ;
    }

    public String[] arr_tkmsgmod_par = null; 
    public String[] arr_cdmsgmod_appl = null; 
    public String[] arr_cdmsgmod_par = null; 
    public String[] arr_dsmsgmod_par = null; 
    public String[] arr_categoria_par = null; 
    public String[] arr_cdazie = null; 
    public String[] arr_cddipa = null; 
    public String[] arr_profil = null; 
    public String[] arr_dtinse = null; 
    public String[] arr_dtulag = null; 
    public String[] arr_fg_lower = null; 
    public String[] arr_fg_upper = null; 
    public String[] arr_fg_capitalize = null; 
    public String[] arr_fg_ori_lower = null; 
    public String[] arr_fg_ori_upper = null; 
    public String[] arr_fg_ori_capitalize = null; 





}

