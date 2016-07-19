package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_atk_msgfile extends Object {

    public Rec_atk_msgfile() {

        super();
    }

    public Rec_atk_msgfile( com.ateikon.common.Atk_msgfile obj_common_from
                       , com.ateikon.common.Atk_msgfile obj_common_to   ) {

        super();

        if (obj_common_from !=null){
            this.obj_common_from = obj_common_from;
        }
        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public Rec_atk_msgfile( com.ateikon.common.Atk_msgfile obj_common_to   ) {

        super();

        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public com.ateikon.common.Atk_msgfile obj_common_from = null;
    public com.ateikon.common.Atk_msgfile obj_common_to   = null;


    public String seStesso = "Rec_atk_msgfile";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public long       tkmsg = 0; 
    public String     pathfile = ""; 
    public String     nomefile = ""; 
    public long       id_object = 0; 
    public String     cdazie = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 
    public long       posi = 0; 
    public String     fgconv_pdf = ""; 
    public String     rootpath = ""; 
    public String     filename = ""; 
    public String     pathconv_pdf = ""; 
    public String     relativepath = ""; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.tkmsg = 0; 
        this.pathfile = ""; 
        this.nomefile = ""; 
        this.id_object = 0; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 
        this.posi = 0; 
        this.fgconv_pdf = ""; 
        this.rootpath = ""; 
        this.filename = ""; 
        this.pathconv_pdf = ""; 
        this.relativepath = ""; 

    }




    /****
        copy_from()
    **/ 

    public void copy_from(Rec_atk_msgfile astr) throws Exception{ 

        this.tkmsg = astr.tkmsg;
        this.pathfile = astr.pathfile;
        this.nomefile = astr.nomefile;
        this.id_object = astr.id_object;
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
        this.posi = astr.posi;
        this.fgconv_pdf = astr.fgconv_pdf;
        this.rootpath = astr.rootpath;
        this.filename = astr.filename;
        this.pathconv_pdf = astr.pathconv_pdf;
        this.relativepath = astr.relativepath;

    }





    /****
        setResultSet: atk_msgfile
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
        if (rs.getObject("pathfile")!=null) this.pathfile = rs.getString    ("pathfile"); 
        if (rs.getObject("nomefile")!=null) this.nomefile = rs.getString    ("nomefile"); 
        if (rs.getObject("id_object")!=null){
            this.id_object = rs.getLong      ("id_object"); 
            if (obj_common_to   != null) obj_common_to.null_id_object = false;
            if (obj_common_from != null) obj_common_from.null_id_object = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_id_object = true;
            if (obj_common_from != null) obj_common_from.null_id_object = true;
        }
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("posi")!=null){
            this.posi = rs.getLong      ("posi"); 
            if (obj_common_to   != null) obj_common_to.null_posi = false;
            if (obj_common_from != null) obj_common_from.null_posi = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_posi = true;
            if (obj_common_from != null) obj_common_from.null_posi = true;
        }
        if (rs.getObject("fgconv_pdf")!=null) this.fgconv_pdf = rs.getString    ("fgconv_pdf"); 
        if (rs.getObject("rootpath")!=null) this.rootpath = rs.getString    ("rootpath"); 
        if (rs.getObject("filename")!=null) this.filename = rs.getString    ("filename"); 
        if (rs.getObject("pathconv_pdf")!=null) this.pathconv_pdf = rs.getString    ("pathconv_pdf"); 
        if (rs.getObject("relativepath")!=null) this.relativepath = rs.getString    ("relativepath"); 

        return 1;
    }




    /****
        setResultSet_key: atk_msgfile
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
        if (rs.getObject("pathfile")!=null) this.pathfile = rs.getString    ("pathfile"); 
        if (rs.getObject("nomefile")!=null) this.nomefile = rs.getString    ("nomefile"); 

        return 1;
    }




    /****
        trim(): atk_msgfile
    **/ 


    public void trim( ) throws Exception {

        this.pathfile = this.pathfile.trim(); 
        this.nomefile = this.nomefile.trim(); 
        this.cdazie = this.cdazie.trim(); 
        this.cddipa = this.cddipa.trim(); 
        this.profil = this.profil.trim(); 
        this.fgconv_pdf = this.fgconv_pdf.trim(); 
        this.rootpath = this.rootpath.trim(); 
        this.filename = this.filename.trim(); 
        this.pathconv_pdf = this.pathconv_pdf.trim(); 
        this.relativepath = this.relativepath.trim(); 

    }



    public void rtrim( ) throws Exception {

        this.pathfile = of_rtrim(this.pathfile); 
        this.nomefile = of_rtrim(this.nomefile); 
        this.cdazie = of_rtrim(this.cdazie); 
        this.cddipa = of_rtrim(this.cddipa); 
        this.profil = of_rtrim(this.profil); 
        this.fgconv_pdf = of_rtrim(this.fgconv_pdf); 
        this.rootpath = of_rtrim(this.rootpath); 
        this.filename = of_rtrim(this.filename); 
        this.pathconv_pdf = of_rtrim(this.pathconv_pdf); 
        this.relativepath = of_rtrim(this.relativepath); 

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
        ctrl_mindate(): atk_msgfile
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
        if (request.getParameterValues("pathfile")!=null) arr_pathfile = request.getParameterValues("pathfile");
        if (request.getParameterValues("nomefile")!=null) arr_nomefile = request.getParameterValues("nomefile");
        if (request.getParameterValues("id_object")!=null) arr_id_object = request.getParameterValues("id_object");
        if (request.getParameterValues("cdazie")!=null) arr_cdazie = request.getParameterValues("cdazie");
        if (request.getParameterValues("cddipa")!=null) arr_cddipa = request.getParameterValues("cddipa");
        if (request.getParameterValues("profil")!=null) arr_profil = request.getParameterValues("profil");
        if (request.getParameterValues("dtinse")!=null) arr_dtinse = request.getParameterValues("dtinse");
        if (request.getParameterValues("dtulag")!=null) arr_dtulag = request.getParameterValues("dtulag");
        if (request.getParameterValues("posi")!=null) arr_posi = request.getParameterValues("posi");
        if (request.getParameterValues("fgconv_pdf")!=null) arr_fgconv_pdf = request.getParameterValues("fgconv_pdf");
        if (request.getParameterValues("rootpath")!=null) arr_rootpath = request.getParameterValues("rootpath");
        if (request.getParameterValues("filename")!=null) arr_filename = request.getParameterValues("filename");
        if (request.getParameterValues("pathconv_pdf")!=null) arr_pathconv_pdf = request.getParameterValues("pathconv_pdf");
        if (request.getParameterValues("relativepath")!=null) arr_relativepath = request.getParameterValues("relativepath");

        return ;
    }

    public String[] arr_tkmsg = null; 
    public String[] arr_pathfile = null; 
    public String[] arr_nomefile = null; 
    public String[] arr_id_object = null; 
    public String[] arr_cdazie = null; 
    public String[] arr_cddipa = null; 
    public String[] arr_profil = null; 
    public String[] arr_dtinse = null; 
    public String[] arr_dtulag = null; 
    public String[] arr_posi = null; 
    public String[] arr_fgconv_pdf = null; 
    public String[] arr_rootpath = null; 
    public String[] arr_filename = null; 
    public String[] arr_pathconv_pdf = null; 
    public String[] arr_relativepath = null; 





}

