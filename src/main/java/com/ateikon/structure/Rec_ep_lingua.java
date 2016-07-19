package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_ep_lingua extends Object {

    public Rec_ep_lingua() {

        super();
    }

    public Rec_ep_lingua( com.ateikon.common.Ep_lingua obj_common_from
                       , com.ateikon.common.Ep_lingua obj_common_to   ) {

        super();

        if (obj_common_from !=null){
            this.obj_common_from = obj_common_from;
        }
        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public Rec_ep_lingua( com.ateikon.common.Ep_lingua obj_common_to   ) {

        super();

        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public com.ateikon.common.Ep_lingua obj_common_from = null;
    public com.ateikon.common.Ep_lingua obj_common_to   = null;


    public String seStesso = "Rec_ep_lingua";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public String     cdling = ""; 
    public String     dsling = ""; 
    public String     dsling_en = ""; 
    public String     dsling_de = ""; 
    public String     dsling_fr = ""; 
    public String     dsling_es = ""; 
    public String     cdiso639 = ""; 
    public String     fgdef = ""; 
    public long       posi = 0; 
    public String     cdazie = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.cdling = ""; 
        this.dsling = ""; 
        this.dsling_en = ""; 
        this.dsling_de = ""; 
        this.dsling_fr = ""; 
        this.dsling_es = ""; 
        this.cdiso639 = ""; 
        this.fgdef = ""; 
        this.posi = 0; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 

    }




    /****
        copy_from()
    **/ 

    public void copy_from(Rec_ep_lingua astr) throws Exception{ 

        this.cdling = astr.cdling;
        this.dsling = astr.dsling;
        this.dsling_en = astr.dsling_en;
        this.dsling_de = astr.dsling_de;
        this.dsling_fr = astr.dsling_fr;
        this.dsling_es = astr.dsling_es;
        this.cdiso639 = astr.cdiso639;
        this.fgdef = astr.fgdef;
        this.posi = astr.posi;
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
        setResultSet: ep_lingua
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("cdling")!=null) this.cdling = rs.getString    ("cdling"); 
        if (rs.getObject("dsling")!=null) this.dsling = rs.getString    ("dsling"); 
        if (rs.getObject("dsling_en")!=null) this.dsling_en = rs.getString    ("dsling_en"); 
        if (rs.getObject("dsling_de")!=null) this.dsling_de = rs.getString    ("dsling_de"); 
        if (rs.getObject("dsling_fr")!=null) this.dsling_fr = rs.getString    ("dsling_fr"); 
        if (rs.getObject("dsling_es")!=null) this.dsling_es = rs.getString    ("dsling_es"); 
        if (rs.getObject("cdiso639")!=null) this.cdiso639 = rs.getString    ("cdiso639"); 
        if (rs.getObject("fgdef")!=null) this.fgdef = rs.getString    ("fgdef"); 
        if (rs.getObject("posi")!=null){
            this.posi = rs.getLong      ("posi"); 
            if (obj_common_to   != null) obj_common_to.null_posi = false;
            if (obj_common_from != null) obj_common_from.null_posi = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_posi = true;
            if (obj_common_from != null) obj_common_from.null_posi = true;
        }
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 

        return 1;
    }




    /****
        setResultSet_key: ep_lingua
    **/ 


    public int setResultSet_key ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("cdling")!=null) this.cdling = rs.getString    ("cdling"); 

        return 1;
    }




    /****
        trim(): ep_lingua
    **/ 


    public void trim( ) throws Exception {

        this.cdling = this.cdling.trim(); 
        this.dsling = this.dsling.trim(); 
        this.dsling_en = this.dsling_en.trim(); 
        this.dsling_de = this.dsling_de.trim(); 
        this.dsling_fr = this.dsling_fr.trim(); 
        this.dsling_es = this.dsling_es.trim(); 
        this.cdiso639 = this.cdiso639.trim(); 
        this.fgdef = this.fgdef.trim(); 
        this.cdazie = this.cdazie.trim(); 
        this.cddipa = this.cddipa.trim(); 
        this.profil = this.profil.trim(); 

    }



    public void rtrim( ) throws Exception {

        this.cdling = of_rtrim(this.cdling); 
        this.dsling = of_rtrim(this.dsling); 
        this.dsling_en = of_rtrim(this.dsling_en); 
        this.dsling_de = of_rtrim(this.dsling_de); 
        this.dsling_fr = of_rtrim(this.dsling_fr); 
        this.dsling_es = of_rtrim(this.dsling_es); 
        this.cdiso639 = of_rtrim(this.cdiso639); 
        this.fgdef = of_rtrim(this.fgdef); 
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
        ctrl_mindate(): ep_lingua
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

        if (request.getParameterValues("cdling")!=null) arr_cdling = request.getParameterValues("cdling");
        if (request.getParameterValues("dsling")!=null) arr_dsling = request.getParameterValues("dsling");
        if (request.getParameterValues("dsling_en")!=null) arr_dsling_en = request.getParameterValues("dsling_en");
        if (request.getParameterValues("dsling_de")!=null) arr_dsling_de = request.getParameterValues("dsling_de");
        if (request.getParameterValues("dsling_fr")!=null) arr_dsling_fr = request.getParameterValues("dsling_fr");
        if (request.getParameterValues("dsling_es")!=null) arr_dsling_es = request.getParameterValues("dsling_es");
        if (request.getParameterValues("cdiso639")!=null) arr_cdiso639 = request.getParameterValues("cdiso639");
        if (request.getParameterValues("fgdef")!=null) arr_fgdef = request.getParameterValues("fgdef");
        if (request.getParameterValues("posi")!=null) arr_posi = request.getParameterValues("posi");
        if (request.getParameterValues("cdazie")!=null) arr_cdazie = request.getParameterValues("cdazie");
        if (request.getParameterValues("cddipa")!=null) arr_cddipa = request.getParameterValues("cddipa");
        if (request.getParameterValues("profil")!=null) arr_profil = request.getParameterValues("profil");
        if (request.getParameterValues("dtinse")!=null) arr_dtinse = request.getParameterValues("dtinse");
        if (request.getParameterValues("dtulag")!=null) arr_dtulag = request.getParameterValues("dtulag");

        return ;
    }

    public String[] arr_cdling = null; 
    public String[] arr_dsling = null; 
    public String[] arr_dsling_en = null; 
    public String[] arr_dsling_de = null; 
    public String[] arr_dsling_fr = null; 
    public String[] arr_dsling_es = null; 
    public String[] arr_cdiso639 = null; 
    public String[] arr_fgdef = null; 
    public String[] arr_posi = null; 
    public String[] arr_cdazie = null; 
    public String[] arr_cddipa = null; 
    public String[] arr_profil = null; 
    public String[] arr_dtinse = null; 
    public String[] arr_dtulag = null; 





}

