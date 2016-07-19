package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_mrp_arch_stato extends Object {

    public Rec_mrp_arch_stato() {

        super();
    }

    public Rec_mrp_arch_stato( com.ateikon.common.Mrp_arch_stato obj_common_from
                       , com.ateikon.common.Mrp_arch_stato obj_common_to   ) {

        super();

        if (obj_common_from !=null){
            this.obj_common_from = obj_common_from;
        }
        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public Rec_mrp_arch_stato( com.ateikon.common.Mrp_arch_stato obj_common_to   ) {

        super();

        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public com.ateikon.common.Mrp_arch_stato obj_common_from = null;
    public com.ateikon.common.Mrp_arch_stato obj_common_to   = null;


    public String seStesso = "Rec_mrp_arch_stato";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public String     cdstato = ""; 
    public String     cdstato_m = ""; 
    public String     dsstato = ""; 
    public String     dsstato_en = ""; 
    public String     cdazie = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 
    public String     fesclu = ""; 
    public String     dsstatoweb = ""; 
    public String     fg_agg = ""; 
    public String     vist_fgrgb = ""; 
    public long       vist_rgb_r = 0; 
    public long       vist_rgb_g = 0; 
    public long       vist_rgb_b = 0; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.cdstato = ""; 
        this.cdstato_m = ""; 
        this.dsstato = ""; 
        this.dsstato_en = ""; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 
        this.fesclu = ""; 
        this.dsstatoweb = ""; 
        this.fg_agg = ""; 
        this.vist_fgrgb = ""; 
        this.vist_rgb_r = 0; 
        this.vist_rgb_g = 0; 
        this.vist_rgb_b = 0; 

    }




    /****
        copy_from()
    **/ 

    public void copy_from(Rec_mrp_arch_stato astr) throws Exception{ 

        this.cdstato = astr.cdstato;
        this.cdstato_m = astr.cdstato_m;
        this.dsstato = astr.dsstato;
        this.dsstato_en = astr.dsstato_en;
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
        this.fesclu = astr.fesclu;
        this.dsstatoweb = astr.dsstatoweb;
        this.fg_agg = astr.fg_agg;
        this.vist_fgrgb = astr.vist_fgrgb;
        this.vist_rgb_r = astr.vist_rgb_r;
        this.vist_rgb_g = astr.vist_rgb_g;
        this.vist_rgb_b = astr.vist_rgb_b;

    }





    /****
        setResultSet: mrp_arch_stato
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("cdstato")!=null) this.cdstato = rs.getString    ("cdstato"); 
        if (rs.getObject("cdstato_m")!=null) this.cdstato_m = rs.getString    ("cdstato_m"); 
        if (rs.getObject("dsstato")!=null) this.dsstato = rs.getString    ("dsstato"); 
        if (rs.getObject("dsstato_en")!=null) this.dsstato_en = rs.getString    ("dsstato_en"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("fesclu")!=null) this.fesclu = rs.getString    ("fesclu"); 
        if (rs.getObject("dsstatoweb")!=null) this.dsstatoweb = rs.getString    ("dsstatoweb"); 
        if (rs.getObject("fg_agg")!=null) this.fg_agg = rs.getString    ("fg_agg"); 
        if (rs.getObject("vist_fgrgb")!=null) this.vist_fgrgb = rs.getString    ("vist_fgrgb"); 
        if (rs.getObject("vist_rgb_r")!=null){
            this.vist_rgb_r = rs.getLong      ("vist_rgb_r"); 
            if (obj_common_to   != null) obj_common_to.null_vist_rgb_r = false;
            if (obj_common_from != null) obj_common_from.null_vist_rgb_r = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_vist_rgb_r = true;
            if (obj_common_from != null) obj_common_from.null_vist_rgb_r = true;
        }
        if (rs.getObject("vist_rgb_g")!=null){
            this.vist_rgb_g = rs.getLong      ("vist_rgb_g"); 
            if (obj_common_to   != null) obj_common_to.null_vist_rgb_g = false;
            if (obj_common_from != null) obj_common_from.null_vist_rgb_g = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_vist_rgb_g = true;
            if (obj_common_from != null) obj_common_from.null_vist_rgb_g = true;
        }
        if (rs.getObject("vist_rgb_b")!=null){
            this.vist_rgb_b = rs.getLong      ("vist_rgb_b"); 
            if (obj_common_to   != null) obj_common_to.null_vist_rgb_b = false;
            if (obj_common_from != null) obj_common_from.null_vist_rgb_b = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_vist_rgb_b = true;
            if (obj_common_from != null) obj_common_from.null_vist_rgb_b = true;
        }

        return 1;
    }




    /****
        setResultSet_key: mrp_arch_stato
    **/ 


    public int setResultSet_key ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("cdstato")!=null) this.cdstato = rs.getString    ("cdstato"); 

        return 1;
    }




    /****
        trim(): mrp_arch_stato
    **/ 


    public void trim( ) throws Exception {

        this.cdstato = this.cdstato.trim(); 
        this.cdstato_m = this.cdstato_m.trim(); 
        this.dsstato = this.dsstato.trim(); 
        this.dsstato_en = this.dsstato_en.trim(); 
        this.cdazie = this.cdazie.trim(); 
        this.cddipa = this.cddipa.trim(); 
        this.profil = this.profil.trim(); 
        this.fesclu = this.fesclu.trim(); 
        this.dsstatoweb = this.dsstatoweb.trim(); 
        this.fg_agg = this.fg_agg.trim(); 
        this.vist_fgrgb = this.vist_fgrgb.trim(); 

    }



    public void rtrim( ) throws Exception {

        this.cdstato = of_rtrim(this.cdstato); 
        this.cdstato_m = of_rtrim(this.cdstato_m); 
        this.dsstato = of_rtrim(this.dsstato); 
        this.dsstato_en = of_rtrim(this.dsstato_en); 
        this.cdazie = of_rtrim(this.cdazie); 
        this.cddipa = of_rtrim(this.cddipa); 
        this.profil = of_rtrim(this.profil); 
        this.fesclu = of_rtrim(this.fesclu); 
        this.dsstatoweb = of_rtrim(this.dsstatoweb); 
        this.fg_agg = of_rtrim(this.fg_agg); 
        this.vist_fgrgb = of_rtrim(this.vist_fgrgb); 

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
        ctrl_mindate(): mrp_arch_stato
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

        if (request.getParameterValues("cdstato")!=null) arr_cdstato = request.getParameterValues("cdstato");
        if (request.getParameterValues("cdstato_m")!=null) arr_cdstato_m = request.getParameterValues("cdstato_m");
        if (request.getParameterValues("dsstato")!=null) arr_dsstato = request.getParameterValues("dsstato");
        if (request.getParameterValues("dsstato_en")!=null) arr_dsstato_en = request.getParameterValues("dsstato_en");
        if (request.getParameterValues("cdazie")!=null) arr_cdazie = request.getParameterValues("cdazie");
        if (request.getParameterValues("cddipa")!=null) arr_cddipa = request.getParameterValues("cddipa");
        if (request.getParameterValues("profil")!=null) arr_profil = request.getParameterValues("profil");
        if (request.getParameterValues("dtinse")!=null) arr_dtinse = request.getParameterValues("dtinse");
        if (request.getParameterValues("dtulag")!=null) arr_dtulag = request.getParameterValues("dtulag");
        if (request.getParameterValues("fesclu")!=null) arr_fesclu = request.getParameterValues("fesclu");
        if (request.getParameterValues("dsstatoweb")!=null) arr_dsstatoweb = request.getParameterValues("dsstatoweb");
        if (request.getParameterValues("fg_agg")!=null) arr_fg_agg = request.getParameterValues("fg_agg");
        if (request.getParameterValues("vist_fgrgb")!=null) arr_vist_fgrgb = request.getParameterValues("vist_fgrgb");
        if (request.getParameterValues("vist_rgb_r")!=null) arr_vist_rgb_r = request.getParameterValues("vist_rgb_r");
        if (request.getParameterValues("vist_rgb_g")!=null) arr_vist_rgb_g = request.getParameterValues("vist_rgb_g");
        if (request.getParameterValues("vist_rgb_b")!=null) arr_vist_rgb_b = request.getParameterValues("vist_rgb_b");

        return ;
    }

    public String[] arr_cdstato = null; 
    public String[] arr_cdstato_m = null; 
    public String[] arr_dsstato = null; 
    public String[] arr_dsstato_en = null; 
    public String[] arr_cdazie = null; 
    public String[] arr_cddipa = null; 
    public String[] arr_profil = null; 
    public String[] arr_dtinse = null; 
    public String[] arr_dtulag = null; 
    public String[] arr_fesclu = null; 
    public String[] arr_dsstatoweb = null; 
    public String[] arr_fg_agg = null; 
    public String[] arr_vist_fgrgb = null; 
    public String[] arr_vist_rgb_r = null; 
    public String[] arr_vist_rgb_g = null; 
    public String[] arr_vist_rgb_b = null; 





}

