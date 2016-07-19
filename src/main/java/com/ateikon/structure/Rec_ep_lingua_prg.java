package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_ep_lingua_prg extends Object {

    public Rec_ep_lingua_prg() {

        super();
    }

    public Rec_ep_lingua_prg( com.ateikon.common.Ep_lingua_prg obj_common_from
                       , com.ateikon.common.Ep_lingua_prg obj_common_to   ) {

        super();

        if (obj_common_from !=null){
            this.obj_common_from = obj_common_from;
        }
        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public Rec_ep_lingua_prg( com.ateikon.common.Ep_lingua_prg obj_common_to   ) {

        super();

        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public com.ateikon.common.Ep_lingua_prg obj_common_from = null;
    public com.ateikon.common.Ep_lingua_prg obj_common_to   = null;


    public String seStesso = "Rec_ep_lingua_prg";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public long       tklingua_prg = 0; 
    public String     cdprogetto = ""; 
    public String     virtualhost = ""; 
    public String     contextpath = ""; 
    public String     cdazie = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 
    public String     webinfpath = ""; 
    public String     fgtraduci = ""; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.tklingua_prg = 0; 
        this.cdprogetto = ""; 
        this.virtualhost = ""; 
        this.contextpath = ""; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 
        this.webinfpath = ""; 
        this.fgtraduci = ""; 

    }




    /****
        copy_from()
    **/ 

    public void copy_from(Rec_ep_lingua_prg astr) throws Exception{ 

        this.tklingua_prg = astr.tklingua_prg;
        this.cdprogetto = astr.cdprogetto;
        this.virtualhost = astr.virtualhost;
        this.contextpath = astr.contextpath;
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
        this.webinfpath = astr.webinfpath;
        this.fgtraduci = astr.fgtraduci;

    }





    /****
        setResultSet: ep_lingua_prg
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tklingua_prg")!=null){
            this.tklingua_prg = rs.getLong      ("tklingua_prg"); 
            if (obj_common_to   != null) obj_common_to.null_tklingua_prg = false;
            if (obj_common_from != null) obj_common_from.null_tklingua_prg = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tklingua_prg = true;
            if (obj_common_from != null) obj_common_from.null_tklingua_prg = true;
        }
        if (rs.getObject("cdprogetto")!=null) this.cdprogetto = rs.getString    ("cdprogetto"); 
        if (rs.getObject("virtualhost")!=null) this.virtualhost = rs.getString    ("virtualhost"); 
        if (rs.getObject("contextpath")!=null) this.contextpath = rs.getString    ("contextpath"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("webinfpath")!=null) this.webinfpath = rs.getString    ("webinfpath"); 
        if (rs.getObject("fgtraduci")!=null) this.fgtraduci = rs.getString    ("fgtraduci"); 

        return 1;
    }




    /****
        setResultSet_key: ep_lingua_prg
    **/ 


    public int setResultSet_key ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tklingua_prg")!=null){
            this.tklingua_prg = rs.getLong      ("tklingua_prg"); 
            if (obj_common_to   != null) obj_common_to.null_tklingua_prg = false;
            if (obj_common_from != null) obj_common_from.null_tklingua_prg = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tklingua_prg = true;
            if (obj_common_from != null) obj_common_from.null_tklingua_prg = true;
        }

        return 1;
    }




    /****
        trim(): ep_lingua_prg
    **/ 


    public void trim( ) throws Exception {

        this.cdprogetto = this.cdprogetto.trim(); 
        this.virtualhost = this.virtualhost.trim(); 
        this.contextpath = this.contextpath.trim(); 
        this.cdazie = this.cdazie.trim(); 
        this.cddipa = this.cddipa.trim(); 
        this.profil = this.profil.trim(); 
        this.webinfpath = this.webinfpath.trim(); 
        this.fgtraduci = this.fgtraduci.trim(); 

    }



    public void rtrim( ) throws Exception {

        this.cdprogetto = of_rtrim(this.cdprogetto); 
        this.virtualhost = of_rtrim(this.virtualhost); 
        this.contextpath = of_rtrim(this.contextpath); 
        this.cdazie = of_rtrim(this.cdazie); 
        this.cddipa = of_rtrim(this.cddipa); 
        this.profil = of_rtrim(this.profil); 
        this.webinfpath = of_rtrim(this.webinfpath); 
        this.fgtraduci = of_rtrim(this.fgtraduci); 

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
        ctrl_mindate(): ep_lingua_prg
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

        if (request.getParameterValues("tklingua_prg")!=null) arr_tklingua_prg = request.getParameterValues("tklingua_prg");
        if (request.getParameterValues("cdprogetto")!=null) arr_cdprogetto = request.getParameterValues("cdprogetto");
        if (request.getParameterValues("virtualhost")!=null) arr_virtualhost = request.getParameterValues("virtualhost");
        if (request.getParameterValues("contextpath")!=null) arr_contextpath = request.getParameterValues("contextpath");
        if (request.getParameterValues("cdazie")!=null) arr_cdazie = request.getParameterValues("cdazie");
        if (request.getParameterValues("cddipa")!=null) arr_cddipa = request.getParameterValues("cddipa");
        if (request.getParameterValues("profil")!=null) arr_profil = request.getParameterValues("profil");
        if (request.getParameterValues("dtinse")!=null) arr_dtinse = request.getParameterValues("dtinse");
        if (request.getParameterValues("dtulag")!=null) arr_dtulag = request.getParameterValues("dtulag");
        if (request.getParameterValues("webinfpath")!=null) arr_webinfpath = request.getParameterValues("webinfpath");
        if (request.getParameterValues("fgtraduci")!=null) arr_fgtraduci = request.getParameterValues("fgtraduci");

        return ;
    }

    public String[] arr_tklingua_prg = null; 
    public String[] arr_cdprogetto = null; 
    public String[] arr_virtualhost = null; 
    public String[] arr_contextpath = null; 
    public String[] arr_cdazie = null; 
    public String[] arr_cddipa = null; 
    public String[] arr_profil = null; 
    public String[] arr_dtinse = null; 
    public String[] arr_dtulag = null; 
    public String[] arr_webinfpath = null; 
    public String[] arr_fgtraduci = null; 





}

