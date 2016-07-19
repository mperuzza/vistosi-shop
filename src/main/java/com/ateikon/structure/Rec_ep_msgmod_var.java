package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_ep_msgmod_var extends Object {

    public Rec_ep_msgmod_var() {

        super();
    }

    public Rec_ep_msgmod_var( com.ateikon.common.Ep_msgmod_var obj_common_from
                       , com.ateikon.common.Ep_msgmod_var obj_common_to   ) {

        super();

        if (obj_common_from !=null){
            this.obj_common_from = obj_common_from;
        }
        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public Rec_ep_msgmod_var( com.ateikon.common.Ep_msgmod_var obj_common_to   ) {

        super();

        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public com.ateikon.common.Ep_msgmod_var obj_common_from = null;
    public com.ateikon.common.Ep_msgmod_var obj_common_to   = null;


    public String seStesso = "Rec_ep_msgmod_var";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public String     cdmsgmod_var = ""; 
    public String     dsmsgmod_var = ""; 
    public String     cdazie = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 
    public long       nrposi = 0; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.cdmsgmod_var = ""; 
        this.dsmsgmod_var = ""; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 
        this.nrposi = 0; 

    }




    /****
        copy_from()
    **/ 

    public void copy_from(Rec_ep_msgmod_var astr) throws Exception{ 

        this.cdmsgmod_var = astr.cdmsgmod_var;
        this.dsmsgmod_var = astr.dsmsgmod_var;
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
        this.nrposi = astr.nrposi;

    }





    /****
        setResultSet: ep_msgmod_var
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("cdmsgmod_var")!=null) this.cdmsgmod_var = rs.getString    ("cdmsgmod_var"); 
        if (rs.getObject("dsmsgmod_var")!=null) this.dsmsgmod_var = rs.getString    ("dsmsgmod_var"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 
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
        setResultSet_key: ep_msgmod_var
    **/ 


    public int setResultSet_key ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("cdmsgmod_var")!=null) this.cdmsgmod_var = rs.getString    ("cdmsgmod_var"); 

        return 1;
    }




    /****
        trim(): ep_msgmod_var
    **/ 


    public void trim( ) throws Exception {

        this.cdmsgmod_var = this.cdmsgmod_var.trim(); 
        this.dsmsgmod_var = this.dsmsgmod_var.trim(); 
        this.cdazie = this.cdazie.trim(); 
        this.cddipa = this.cddipa.trim(); 
        this.profil = this.profil.trim(); 

    }



    public void rtrim( ) throws Exception {

        this.cdmsgmod_var = of_rtrim(this.cdmsgmod_var); 
        this.dsmsgmod_var = of_rtrim(this.dsmsgmod_var); 
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
        ctrl_mindate(): ep_msgmod_var
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

        if (request.getParameterValues("cdmsgmod_var")!=null) arr_cdmsgmod_var = request.getParameterValues("cdmsgmod_var");
        if (request.getParameterValues("dsmsgmod_var")!=null) arr_dsmsgmod_var = request.getParameterValues("dsmsgmod_var");
        if (request.getParameterValues("cdazie")!=null) arr_cdazie = request.getParameterValues("cdazie");
        if (request.getParameterValues("cddipa")!=null) arr_cddipa = request.getParameterValues("cddipa");
        if (request.getParameterValues("profil")!=null) arr_profil = request.getParameterValues("profil");
        if (request.getParameterValues("dtinse")!=null) arr_dtinse = request.getParameterValues("dtinse");
        if (request.getParameterValues("dtulag")!=null) arr_dtulag = request.getParameterValues("dtulag");
        if (request.getParameterValues("nrposi")!=null) arr_nrposi = request.getParameterValues("nrposi");

        return ;
    }

    public String[] arr_cdmsgmod_var = null; 
    public String[] arr_dsmsgmod_var = null; 
    public String[] arr_cdazie = null; 
    public String[] arr_cddipa = null; 
    public String[] arr_profil = null; 
    public String[] arr_dtinse = null; 
    public String[] arr_dtulag = null; 
    public String[] arr_nrposi = null; 





}

