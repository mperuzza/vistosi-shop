package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_ep_msgmod_cont extends Object {

    public Rec_ep_msgmod_cont() {

        super();
    }

    public Rec_ep_msgmod_cont( com.ateikon.common.Ep_msgmod_cont obj_common_from
                       , com.ateikon.common.Ep_msgmod_cont obj_common_to   ) {

        super();

        if (obj_common_from !=null){
            this.obj_common_from = obj_common_from;
        }
        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public Rec_ep_msgmod_cont( com.ateikon.common.Ep_msgmod_cont obj_common_to   ) {

        super();

        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public com.ateikon.common.Ep_msgmod_cont obj_common_from = null;
    public com.ateikon.common.Ep_msgmod_cont obj_common_to   = null;


    public String seStesso = "Rec_ep_msgmod_cont";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public long       tkmsgmod = 0; 
    public String     cdling = ""; 
    public long       nrposi = 0; 
    public String     contenuto = ""; 
    public String     cdazie = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.tkmsgmod = 0; 
        this.cdling = ""; 
        this.nrposi = 0; 
        this.contenuto = ""; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 

    }




    /****
        copy_from()
    **/ 

    public void copy_from(Rec_ep_msgmod_cont astr) throws Exception{ 

        this.tkmsgmod = astr.tkmsgmod;
        this.cdling = astr.cdling;
        this.nrposi = astr.nrposi;
        this.contenuto = astr.contenuto;
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
        setResultSet: ep_msgmod_cont
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkmsgmod")!=null){
            this.tkmsgmod = rs.getLong      ("tkmsgmod"); 
            if (obj_common_to   != null) obj_common_to.null_tkmsgmod = false;
            if (obj_common_from != null) obj_common_from.null_tkmsgmod = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tkmsgmod = true;
            if (obj_common_from != null) obj_common_from.null_tkmsgmod = true;
        }
        if (rs.getObject("cdling")!=null) this.cdling = rs.getString    ("cdling"); 
        if (rs.getObject("nrposi")!=null){
            this.nrposi = rs.getLong      ("nrposi"); 
            if (obj_common_to   != null) obj_common_to.null_nrposi = false;
            if (obj_common_from != null) obj_common_from.null_nrposi = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_nrposi = true;
            if (obj_common_from != null) obj_common_from.null_nrposi = true;
        }
        if (rs.getObject("contenuto")!=null) this.contenuto = rs.getString    ("contenuto"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 

        return 1;
    }




    /****
        setResultSet_key: ep_msgmod_cont
    **/ 


    public int setResultSet_key ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkmsgmod")!=null){
            this.tkmsgmod = rs.getLong      ("tkmsgmod"); 
            if (obj_common_to   != null) obj_common_to.null_tkmsgmod = false;
            if (obj_common_from != null) obj_common_from.null_tkmsgmod = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tkmsgmod = true;
            if (obj_common_from != null) obj_common_from.null_tkmsgmod = true;
        }
        if (rs.getObject("cdling")!=null) this.cdling = rs.getString    ("cdling"); 
        if (rs.getObject("nrposi")!=null){
            this.nrposi = rs.getLong      ("nrposi"); 
            if (obj_common_to   != null) obj_common_to.null_nrposi = false;
            if (obj_common_from != null) obj_common_from.null_nrposi = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_nrposi = true;
            if (obj_common_from != null) obj_common_from.null_nrposi = true;
        }

        return 1;
    }




    /****
        trim(): ep_msgmod_cont
    **/ 


    public void trim( ) throws Exception {

        this.cdling = this.cdling.trim(); 
        this.contenuto = this.contenuto.trim(); 
        this.cdazie = this.cdazie.trim(); 
        this.cddipa = this.cddipa.trim(); 
        this.profil = this.profil.trim(); 

    }



    public void rtrim( ) throws Exception {

        this.cdling = of_rtrim(this.cdling); 
        this.contenuto = of_rtrim(this.contenuto); 
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
        ctrl_mindate(): ep_msgmod_cont
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

        if (request.getParameterValues("tkmsgmod")!=null) arr_tkmsgmod = request.getParameterValues("tkmsgmod");
        if (request.getParameterValues("cdling")!=null) arr_cdling = request.getParameterValues("cdling");
        if (request.getParameterValues("nrposi")!=null) arr_nrposi = request.getParameterValues("nrposi");
        if (request.getParameterValues("contenuto")!=null) arr_contenuto = request.getParameterValues("contenuto");
        if (request.getParameterValues("cdazie")!=null) arr_cdazie = request.getParameterValues("cdazie");
        if (request.getParameterValues("cddipa")!=null) arr_cddipa = request.getParameterValues("cddipa");
        if (request.getParameterValues("profil")!=null) arr_profil = request.getParameterValues("profil");
        if (request.getParameterValues("dtinse")!=null) arr_dtinse = request.getParameterValues("dtinse");
        if (request.getParameterValues("dtulag")!=null) arr_dtulag = request.getParameterValues("dtulag");

        return ;
    }

    public String[] arr_tkmsgmod = null; 
    public String[] arr_cdling = null; 
    public String[] arr_nrposi = null; 
    public String[] arr_contenuto = null; 
    public String[] arr_cdazie = null; 
    public String[] arr_cddipa = null; 
    public String[] arr_profil = null; 
    public String[] arr_dtinse = null; 
    public String[] arr_dtulag = null; 





}

