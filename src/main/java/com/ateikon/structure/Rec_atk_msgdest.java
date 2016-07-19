package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_atk_msgdest extends Object {

    public Rec_atk_msgdest() {

        super();
    }

    public Rec_atk_msgdest( com.ateikon.common.Atk_msgdest obj_common_from
                       , com.ateikon.common.Atk_msgdest obj_common_to   ) {

        super();

        if (obj_common_from !=null){
            this.obj_common_from = obj_common_from;
        }
        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public Rec_atk_msgdest( com.ateikon.common.Atk_msgdest obj_common_to   ) {

        super();

        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public com.ateikon.common.Atk_msgdest obj_common_from = null;
    public com.ateikon.common.Atk_msgdest obj_common_to   = null;


    public String seStesso = "Rec_atk_msgdest";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public long       tkmsg = 0; 
    public String     dest = ""; 
    public String     tipodest = ""; 
    public String     cdazie = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 
    public long       tkmlp = 0; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.tkmsg = 0; 
        this.dest = ""; 
        this.tipodest = ""; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 
        this.tkmlp = 0; 

    }




    /****
        copy_from()
    **/ 

    public void copy_from(Rec_atk_msgdest astr) throws Exception{ 

        this.tkmsg = astr.tkmsg;
        this.dest = astr.dest;
        this.tipodest = astr.tipodest;
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
        this.tkmlp = astr.tkmlp;

    }





    /****
        setResultSet: atk_msgdest
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkmsg")!=null){
            this.tkmsg = rs.getLong      ("tkmsg"); 
            if (obj_common_to   != null) obj_common_to.null_tkmsg = false;
            if (obj_common_from != null) obj_common_from.null_tkmsg = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tkmsg = true;
            if (obj_common_from != null) obj_common_from.null_tkmsg = true;
        }
        if (rs.getObject("dest")!=null) this.dest = rs.getString    ("dest"); 
        if (rs.getObject("tipodest")!=null) this.tipodest = rs.getString    ("tipodest"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("tkmlp")!=null){
            this.tkmlp = rs.getLong      ("tkmlp"); 
            if (obj_common_to   != null) obj_common_to.null_tkmlp = false;
            if (obj_common_from != null) obj_common_from.null_tkmlp = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tkmlp = true;
            if (obj_common_from != null) obj_common_from.null_tkmlp = true;
        }

        return 1;
    }




    /****
        setResultSet_key: atk_msgdest
    **/ 


    public int setResultSet_key ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkmsg")!=null){
            this.tkmsg = rs.getLong      ("tkmsg"); 
            if (obj_common_to   != null) obj_common_to.null_tkmsg = false;
            if (obj_common_from != null) obj_common_from.null_tkmsg = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tkmsg = true;
            if (obj_common_from != null) obj_common_from.null_tkmsg = true;
        }
        if (rs.getObject("dest")!=null) this.dest = rs.getString    ("dest"); 
        if (rs.getObject("tipodest")!=null) this.tipodest = rs.getString    ("tipodest"); 

        return 1;
    }




    /****
        trim(): atk_msgdest
    **/ 


    public void trim( ) throws Exception {

        this.dest = this.dest.trim(); 
        this.tipodest = this.tipodest.trim(); 
        this.cdazie = this.cdazie.trim(); 
        this.cddipa = this.cddipa.trim(); 
        this.profil = this.profil.trim(); 

    }



    public void rtrim( ) throws Exception {

        this.dest = of_rtrim(this.dest); 
        this.tipodest = of_rtrim(this.tipodest); 
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
        ctrl_mindate(): atk_msgdest
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

        if (request.getParameterValues("tkmsg")!=null) arr_tkmsg = request.getParameterValues("tkmsg");
        if (request.getParameterValues("dest")!=null) arr_dest = request.getParameterValues("dest");
        if (request.getParameterValues("tipodest")!=null) arr_tipodest = request.getParameterValues("tipodest");
        if (request.getParameterValues("cdazie")!=null) arr_cdazie = request.getParameterValues("cdazie");
        if (request.getParameterValues("cddipa")!=null) arr_cddipa = request.getParameterValues("cddipa");
        if (request.getParameterValues("profil")!=null) arr_profil = request.getParameterValues("profil");
        if (request.getParameterValues("dtinse")!=null) arr_dtinse = request.getParameterValues("dtinse");
        if (request.getParameterValues("dtulag")!=null) arr_dtulag = request.getParameterValues("dtulag");
        if (request.getParameterValues("tkmlp")!=null) arr_tkmlp = request.getParameterValues("tkmlp");

        return ;
    }

    public String[] arr_tkmsg = null; 
    public String[] arr_dest = null; 
    public String[] arr_tipodest = null; 
    public String[] arr_cdazie = null; 
    public String[] arr_cddipa = null; 
    public String[] arr_profil = null; 
    public String[] arr_dtinse = null; 
    public String[] arr_dtulag = null; 
    public String[] arr_tkmlp = null; 





}

