package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_ep_lingua_lb extends Object {

    public Rec_ep_lingua_lb() {

        super();
    }

    public Rec_ep_lingua_lb( com.ateikon.common.Ep_lingua_lb obj_common_from
                       , com.ateikon.common.Ep_lingua_lb obj_common_to   ) {

        super();

        if (obj_common_from !=null){
            this.obj_common_from = obj_common_from;
        }
        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public Rec_ep_lingua_lb( com.ateikon.common.Ep_lingua_lb obj_common_to   ) {

        super();

        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public com.ateikon.common.Ep_lingua_lb obj_common_from = null;
    public com.ateikon.common.Ep_lingua_lb obj_common_to   = null;


    public String seStesso = "Rec_ep_lingua_lb";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public long       tklingua_lb = 0; 
    public String     cdling = ""; 
    public long       tklingua_url = 0; 
    public String     cdcampo = ""; 
    public String     dscampo = ""; 
    public String     dscampo_defling = ""; 
    public String     cdazie = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.tklingua_lb = 0; 
        this.cdling = ""; 
        this.tklingua_url = 0; 
        this.cdcampo = ""; 
        this.dscampo = ""; 
        this.dscampo_defling = ""; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 

    }




    /****
        copy_from()
    **/ 

    public void copy_from(Rec_ep_lingua_lb astr) throws Exception{ 

        this.tklingua_lb = astr.tklingua_lb;
        this.cdling = astr.cdling;
        this.tklingua_url = astr.tklingua_url;
        this.cdcampo = astr.cdcampo;
        this.dscampo = astr.dscampo;
        this.dscampo_defling = astr.dscampo_defling;
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

    }





    /****
        setResultSet: ep_lingua_lb
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tklingua_lb")!=null){
            this.tklingua_lb = rs.getLong      ("tklingua_lb"); 
            if (obj_common_to   != null) obj_common_to.null_tklingua_lb = false;
            if (obj_common_from != null) obj_common_from.null_tklingua_lb = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tklingua_lb = true;
            if (obj_common_from != null) obj_common_from.null_tklingua_lb = true;
        }
        if (rs.getObject("cdling")!=null) this.cdling = rs.getString    ("cdling"); 
        if (rs.getObject("tklingua_url")!=null){
            this.tklingua_url = rs.getLong      ("tklingua_url"); 
            if (obj_common_to   != null) obj_common_to.null_tklingua_url = false;
            if (obj_common_from != null) obj_common_from.null_tklingua_url = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tklingua_url = true;
            if (obj_common_from != null) obj_common_from.null_tklingua_url = true;
        }
        if (rs.getObject("cdcampo")!=null) this.cdcampo = rs.getString    ("cdcampo"); 
        if (rs.getObject("dscampo")!=null) this.dscampo = rs.getString    ("dscampo"); 
        if (rs.getObject("dscampo_defling")!=null) this.dscampo_defling = rs.getString    ("dscampo_defling"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 

        return 1;
    }




    /****
        setResultSet_key: ep_lingua_lb
    **/ 


    public int setResultSet_key ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tklingua_lb")!=null){
            this.tklingua_lb = rs.getLong      ("tklingua_lb"); 
            if (obj_common_to   != null) obj_common_to.null_tklingua_lb = false;
            if (obj_common_from != null) obj_common_from.null_tklingua_lb = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tklingua_lb = true;
            if (obj_common_from != null) obj_common_from.null_tklingua_lb = true;
        }

        return 1;
    }




    /****
        trim(): ep_lingua_lb
    **/ 


    public void trim( ) throws Exception {

        this.cdling = this.cdling.trim(); 
        this.cdcampo = this.cdcampo.trim(); 
        this.dscampo = this.dscampo.trim(); 
        this.dscampo_defling = this.dscampo_defling.trim(); 
        this.cdazie = this.cdazie.trim(); 
        this.cddipa = this.cddipa.trim(); 
        this.profil = this.profil.trim(); 

    }



    public void rtrim( ) throws Exception {

        this.cdling = of_rtrim(this.cdling); 
        this.cdcampo = of_rtrim(this.cdcampo); 
        this.dscampo = of_rtrim(this.dscampo); 
        this.dscampo_defling = of_rtrim(this.dscampo_defling); 
        this.cdazie = of_rtrim(this.cdazie); 
        this.cddipa = of_rtrim(this.cddipa); 
        this.profil = of_rtrim(this.profil); 

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
        ctrl_mindate(): ep_lingua_lb
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

        if (request.getParameterValues("tklingua_lb")!=null) arr_tklingua_lb = request.getParameterValues("tklingua_lb");
        if (request.getParameterValues("cdling")!=null) arr_cdling = request.getParameterValues("cdling");
        if (request.getParameterValues("tklingua_url")!=null) arr_tklingua_url = request.getParameterValues("tklingua_url");
        if (request.getParameterValues("cdcampo")!=null) arr_cdcampo = request.getParameterValues("cdcampo");
        if (request.getParameterValues("dscampo")!=null) arr_dscampo = request.getParameterValues("dscampo");
        if (request.getParameterValues("dscampo_defling")!=null) arr_dscampo_defling = request.getParameterValues("dscampo_defling");
        if (request.getParameterValues("cdazie")!=null) arr_cdazie = request.getParameterValues("cdazie");
        if (request.getParameterValues("cddipa")!=null) arr_cddipa = request.getParameterValues("cddipa");
        if (request.getParameterValues("profil")!=null) arr_profil = request.getParameterValues("profil");
        if (request.getParameterValues("dtinse")!=null) arr_dtinse = request.getParameterValues("dtinse");
        if (request.getParameterValues("dtulag")!=null) arr_dtulag = request.getParameterValues("dtulag");

        return ;
    }

    public String[] arr_tklingua_lb = null; 
    public String[] arr_cdling = null; 
    public String[] arr_tklingua_url = null; 
    public String[] arr_cdcampo = null; 
    public String[] arr_dscampo = null; 
    public String[] arr_dscampo_defling = null; 
    public String[] arr_cdazie = null; 
    public String[] arr_cddipa = null; 
    public String[] arr_profil = null; 
    public String[] arr_dtinse = null; 
    public String[] arr_dtulag = null; 





}

