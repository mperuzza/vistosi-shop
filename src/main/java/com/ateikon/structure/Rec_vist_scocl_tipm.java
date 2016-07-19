package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_vist_scocl_tipm extends Object {

    public Rec_vist_scocl_tipm() {

        super();
    }

    public Rec_vist_scocl_tipm( com.ateikon.common.Vist_scocl_tipm obj_common_from
                       , com.ateikon.common.Vist_scocl_tipm obj_common_to   ) {

        super();

        if (obj_common_from !=null){
            this.obj_common_from = obj_common_from;
        }
        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public Rec_vist_scocl_tipm( com.ateikon.common.Vist_scocl_tipm obj_common_to   ) {

        super();

        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public com.ateikon.common.Vist_scocl_tipm obj_common_from = null;
    public com.ateikon.common.Vist_scocl_tipm obj_common_to   = null;


    public String seStesso = "Rec_vist_scocl_tipm";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public String     tkclie = ""; 
    public String     cdtipm = ""; 
    public Timestamp  dtinva = null; 
    public Timestamp  dtfval = null; 
    public double     sconto1 = 0; 
    public double     sconto2 = 0; 
    public double     sconto3 = 0; 
    public double     sconto4 = 0; 
    public double     sconto5 = 0; 
    public String     cdazie = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 
    public String     fg_agg = ""; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.tkclie = ""; 
        this.cdtipm = ""; 
        this.dtinva = null; 
        this.dtfval = null; 
        this.sconto1 = 0; 
        this.sconto2 = 0; 
        this.sconto3 = 0; 
        this.sconto4 = 0; 
        this.sconto5 = 0; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 
        this.fg_agg = ""; 

    }




    /****
        copy_from()
    **/ 

    public void copy_from(Rec_vist_scocl_tipm astr) throws Exception{ 

        this.tkclie = astr.tkclie;
        this.cdtipm = astr.cdtipm;
        if (astr.dtinva == null){
           this.dtinva = null;
        }else {
           this.dtinva = (Timestamp ) astr.dtinva.clone();
        }
        if (astr.dtfval == null){
           this.dtfval = null;
        }else {
           this.dtfval = (Timestamp ) astr.dtfval.clone();
        }
        this.sconto1 = astr.sconto1;
        this.sconto2 = astr.sconto2;
        this.sconto3 = astr.sconto3;
        this.sconto4 = astr.sconto4;
        this.sconto5 = astr.sconto5;
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
        this.fg_agg = astr.fg_agg;

    }





    /****
        setResultSet: vist_scocl_tipm
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkclie")!=null) this.tkclie = rs.getString    ("tkclie"); 
        if (rs.getObject("cdtipm")!=null) this.cdtipm = rs.getString    ("cdtipm"); 
        if (rs.getObject("dtinva")!=null) this.dtinva = rs.getTimestamp ("dtinva"); 
        if (rs.getObject("dtfval")!=null) this.dtfval = rs.getTimestamp ("dtfval"); 
        if (rs.getObject("sconto1")!=null){
            this.sconto1 = rs.getDouble    ("sconto1"); 
            if (obj_common_to   != null) obj_common_to.null_sconto1 = false;
            if (obj_common_from != null) obj_common_from.null_sconto1 = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_sconto1 = true;
            if (obj_common_from != null) obj_common_from.null_sconto1 = true;
        }
        if (rs.getObject("sconto2")!=null){
            this.sconto2 = rs.getDouble    ("sconto2"); 
            if (obj_common_to   != null) obj_common_to.null_sconto2 = false;
            if (obj_common_from != null) obj_common_from.null_sconto2 = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_sconto2 = true;
            if (obj_common_from != null) obj_common_from.null_sconto2 = true;
        }
        if (rs.getObject("sconto3")!=null){
            this.sconto3 = rs.getDouble    ("sconto3"); 
            if (obj_common_to   != null) obj_common_to.null_sconto3 = false;
            if (obj_common_from != null) obj_common_from.null_sconto3 = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_sconto3 = true;
            if (obj_common_from != null) obj_common_from.null_sconto3 = true;
        }
        if (rs.getObject("sconto4")!=null){
            this.sconto4 = rs.getDouble    ("sconto4"); 
            if (obj_common_to   != null) obj_common_to.null_sconto4 = false;
            if (obj_common_from != null) obj_common_from.null_sconto4 = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_sconto4 = true;
            if (obj_common_from != null) obj_common_from.null_sconto4 = true;
        }
        if (rs.getObject("sconto5")!=null){
            this.sconto5 = rs.getDouble    ("sconto5"); 
            if (obj_common_to   != null) obj_common_to.null_sconto5 = false;
            if (obj_common_from != null) obj_common_from.null_sconto5 = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_sconto5 = true;
            if (obj_common_from != null) obj_common_from.null_sconto5 = true;
        }
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("fg_agg")!=null) this.fg_agg = rs.getString    ("fg_agg"); 

        return 1;
    }




    /****
        setResultSet_key: vist_scocl_tipm
    **/ 


    public int setResultSet_key ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkclie")!=null) this.tkclie = rs.getString    ("tkclie"); 
        if (rs.getObject("cdtipm")!=null) this.cdtipm = rs.getString    ("cdtipm"); 
        if (rs.getObject("dtinva")!=null) this.dtinva = rs.getTimestamp ("dtinva"); 

        return 1;
    }




    /****
        trim(): vist_scocl_tipm
    **/ 


    public void trim( ) throws Exception {

        this.tkclie = this.tkclie.trim(); 
        this.cdtipm = this.cdtipm.trim(); 
        this.cdazie = this.cdazie.trim(); 
        this.cddipa = this.cddipa.trim(); 
        this.profil = this.profil.trim(); 
        this.fg_agg = this.fg_agg.trim(); 

    }



    public void rtrim( ) throws Exception {

        this.tkclie = of_rtrim(this.tkclie); 
        this.cdtipm = of_rtrim(this.cdtipm); 
        this.cdazie = of_rtrim(this.cdazie); 
        this.cddipa = of_rtrim(this.cddipa); 
        this.profil = of_rtrim(this.profil); 
        this.fg_agg = of_rtrim(this.fg_agg); 

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
        ctrl_mindate(): vist_scocl_tipm
    **/ 


    public void ctrl_mindate( ) throws Exception {

        int li_sa = 0; 

        li_sa = 0; 
        if (this.dtinva != null){ 
            li_sa = this.dtinva.getYear()+1900; 
            if (li_sa < 1900){ 
                this.dtinva.setYear(0); 
                this.dtinva.setMonth(0); 
                this.dtinva.setDate(1); 
                this.dtinva.setHours(0); 
                this.dtinva.setMinutes(0); 
                this.dtinva.setSeconds(0); 
                this.dtinva.setNanos(0); 
            } 
            this.dtinva.setNanos(0); 
            this.dtinva.toString(); 
        } 
        li_sa = 0; 
        if (this.dtfval != null){ 
            li_sa = this.dtfval.getYear()+1900; 
            if (li_sa < 1900){ 
                this.dtfval.setYear(0); 
                this.dtfval.setMonth(0); 
                this.dtfval.setDate(1); 
                this.dtfval.setHours(0); 
                this.dtfval.setMinutes(0); 
                this.dtfval.setSeconds(0); 
                this.dtfval.setNanos(0); 
            } 
            this.dtfval.setNanos(0); 
            this.dtfval.toString(); 
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

        if (request.getParameterValues("tkclie")!=null) arr_tkclie = request.getParameterValues("tkclie");
        if (request.getParameterValues("cdtipm")!=null) arr_cdtipm = request.getParameterValues("cdtipm");
        if (request.getParameterValues("dtinva")!=null) arr_dtinva = request.getParameterValues("dtinva");
        if (request.getParameterValues("dtfval")!=null) arr_dtfval = request.getParameterValues("dtfval");
        if (request.getParameterValues("sconto1")!=null) arr_sconto1 = request.getParameterValues("sconto1");
        if (request.getParameterValues("sconto2")!=null) arr_sconto2 = request.getParameterValues("sconto2");
        if (request.getParameterValues("sconto3")!=null) arr_sconto3 = request.getParameterValues("sconto3");
        if (request.getParameterValues("sconto4")!=null) arr_sconto4 = request.getParameterValues("sconto4");
        if (request.getParameterValues("sconto5")!=null) arr_sconto5 = request.getParameterValues("sconto5");
        if (request.getParameterValues("cdazie")!=null) arr_cdazie = request.getParameterValues("cdazie");
        if (request.getParameterValues("cddipa")!=null) arr_cddipa = request.getParameterValues("cddipa");
        if (request.getParameterValues("profil")!=null) arr_profil = request.getParameterValues("profil");
        if (request.getParameterValues("dtinse")!=null) arr_dtinse = request.getParameterValues("dtinse");
        if (request.getParameterValues("dtulag")!=null) arr_dtulag = request.getParameterValues("dtulag");
        if (request.getParameterValues("fg_agg")!=null) arr_fg_agg = request.getParameterValues("fg_agg");

        return ;
    }

    public String[] arr_tkclie = null; 
    public String[] arr_cdtipm = null; 
    public String[] arr_dtinva = null; 
    public String[] arr_dtfval = null; 
    public String[] arr_sconto1 = null; 
    public String[] arr_sconto2 = null; 
    public String[] arr_sconto3 = null; 
    public String[] arr_sconto4 = null; 
    public String[] arr_sconto5 = null; 
    public String[] arr_cdazie = null; 
    public String[] arr_cddipa = null; 
    public String[] arr_profil = null; 
    public String[] arr_dtinse = null; 
    public String[] arr_dtulag = null; 
    public String[] arr_fg_agg = null; 





}

