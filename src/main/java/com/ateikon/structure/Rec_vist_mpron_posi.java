package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_vist_mpron_posi extends Object {

    public Rec_vist_mpron_posi() {

        super();
    }

    public Rec_vist_mpron_posi( com.ateikon.common.Vist_mpron_posi obj_common_from
                       , com.ateikon.common.Vist_mpron_posi obj_common_to   ) {

        super();

        if (obj_common_from !=null){
            this.obj_common_from = obj_common_from;
        }
        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public Rec_vist_mpron_posi( com.ateikon.common.Vist_mpron_posi obj_common_to   ) {

        super();

        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public com.ateikon.common.Vist_mpron_posi obj_common_from = null;
    public com.ateikon.common.Vist_mpron_posi obj_common_to   = null;


    public String seStesso = "Rec_vist_mpron_posi";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public long       tkmpronp = 0; 
    public long       tkmpron = 0; 
    public Timestamp  dtdoc = null; 
    public String     nrdoc = ""; 
    public double     importo = 0; 
    public String     nota = ""; 
    public String     vacodi = ""; 
    public String     cdazie = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 
    public Timestamp  dtdel = null; 
    public long       nrposi = 0; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.tkmpronp = 0; 
        this.tkmpron = 0; 
        this.dtdoc = null; 
        this.nrdoc = ""; 
        this.importo = 0; 
        this.nota = ""; 
        this.vacodi = ""; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 
        this.dtdel = null; 
        this.nrposi = 0; 

    }




    /****
        copy_from()
    **/ 

    public void copy_from(Rec_vist_mpron_posi astr) throws Exception{ 

        this.tkmpronp = astr.tkmpronp;
        this.tkmpron = astr.tkmpron;
        if (astr.dtdoc == null){
           this.dtdoc = null;
        }else {
           this.dtdoc = (Timestamp ) astr.dtdoc.clone();
        }
        this.nrdoc = astr.nrdoc;
        this.importo = astr.importo;
        this.nota = astr.nota;
        this.vacodi = astr.vacodi;
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
        if (astr.dtdel == null){
           this.dtdel = null;
        }else {
           this.dtdel = (Timestamp ) astr.dtdel.clone();
        }
        this.nrposi = astr.nrposi;

    }





    /****
        setResultSet: vist_mpron_posi
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkmpronp")!=null){
            this.tkmpronp = rs.getLong      ("tkmpronp"); 
            if (obj_common_to   != null) obj_common_to.null_tkmpronp = false;
            if (obj_common_from != null) obj_common_from.null_tkmpronp = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tkmpronp = true;
            if (obj_common_from != null) obj_common_from.null_tkmpronp = true;
        }
        if (rs.getObject("tkmpron")!=null){
            this.tkmpron = rs.getLong      ("tkmpron"); 
            if (obj_common_to   != null) obj_common_to.null_tkmpron = false;
            if (obj_common_from != null) obj_common_from.null_tkmpron = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tkmpron = true;
            if (obj_common_from != null) obj_common_from.null_tkmpron = true;
        }
        if (rs.getObject("dtdoc")!=null) this.dtdoc = rs.getTimestamp ("dtdoc"); 
        if (rs.getObject("nrdoc")!=null) this.nrdoc = rs.getString    ("nrdoc"); 
        if (rs.getObject("importo")!=null){
            this.importo = rs.getDouble    ("importo"); 
            if (obj_common_to   != null) obj_common_to.null_importo = false;
            if (obj_common_from != null) obj_common_from.null_importo = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_importo = true;
            if (obj_common_from != null) obj_common_from.null_importo = true;
        }
        if (rs.getObject("nota")!=null) this.nota = rs.getString    ("nota"); 
        if (rs.getObject("vacodi")!=null) this.vacodi = rs.getString    ("vacodi"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("dtdel")!=null) this.dtdel = rs.getTimestamp ("dtdel"); 
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
        setResultSet_key: vist_mpron_posi
    **/ 


    public int setResultSet_key ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkmpronp")!=null){
            this.tkmpronp = rs.getLong      ("tkmpronp"); 
            if (obj_common_to   != null) obj_common_to.null_tkmpronp = false;
            if (obj_common_from != null) obj_common_from.null_tkmpronp = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tkmpronp = true;
            if (obj_common_from != null) obj_common_from.null_tkmpronp = true;
        }

        return 1;
    }




    /****
        trim(): vist_mpron_posi
    **/ 


    public void trim( ) throws Exception {

        this.nrdoc = this.nrdoc.trim(); 
        this.nota = this.nota.trim(); 
        this.vacodi = this.vacodi.trim(); 
        this.cdazie = this.cdazie.trim(); 
        this.cddipa = this.cddipa.trim(); 
        this.profil = this.profil.trim(); 

    }



    public void rtrim( ) throws Exception {

        this.nrdoc = of_rtrim(this.nrdoc); 
        this.nota = of_rtrim(this.nota); 
        this.vacodi = of_rtrim(this.vacodi); 
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
        ctrl_mindate(): vist_mpron_posi
    **/ 


    public void ctrl_mindate( ) throws Exception {

        int li_sa = 0; 

        li_sa = 0; 
        if (this.dtdoc != null){ 
            li_sa = this.dtdoc.getYear()+1900; 
            if (li_sa < 1900){ 
                this.dtdoc.setYear(0); 
                this.dtdoc.setMonth(0); 
                this.dtdoc.setDate(1); 
                this.dtdoc.setHours(0); 
                this.dtdoc.setMinutes(0); 
                this.dtdoc.setSeconds(0); 
                this.dtdoc.setNanos(0); 
            } 
            this.dtdoc.setNanos(0); 
            this.dtdoc.toString(); 
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
        li_sa = 0; 
        if (this.dtdel != null){ 
            li_sa = this.dtdel.getYear()+1900; 
            if (li_sa < 1900){ 
                this.dtdel.setYear(0); 
                this.dtdel.setMonth(0); 
                this.dtdel.setDate(1); 
                this.dtdel.setHours(0); 
                this.dtdel.setMinutes(0); 
                this.dtdel.setSeconds(0); 
                this.dtdel.setNanos(0); 
            } 
            this.dtdel.setNanos(0); 
            this.dtdel.toString(); 
        } 

    }




    /****
        requestValues(request):   
    **/ 

    public void requestValues(javax.servlet.http.HttpServletRequest request) throws Exception{ 

        if (request.getParameterValues("tkmpronp")!=null) arr_tkmpronp = request.getParameterValues("tkmpronp");
        if (request.getParameterValues("tkmpron")!=null) arr_tkmpron = request.getParameterValues("tkmpron");
        if (request.getParameterValues("dtdoc")!=null) arr_dtdoc = request.getParameterValues("dtdoc");
        if (request.getParameterValues("nrdoc")!=null) arr_nrdoc = request.getParameterValues("nrdoc");
        if (request.getParameterValues("importo")!=null) arr_importo = request.getParameterValues("importo");
        if (request.getParameterValues("nota")!=null) arr_nota = request.getParameterValues("nota");
        if (request.getParameterValues("vacodi")!=null) arr_vacodi = request.getParameterValues("vacodi");
        if (request.getParameterValues("cdazie")!=null) arr_cdazie = request.getParameterValues("cdazie");
        if (request.getParameterValues("cddipa")!=null) arr_cddipa = request.getParameterValues("cddipa");
        if (request.getParameterValues("profil")!=null) arr_profil = request.getParameterValues("profil");
        if (request.getParameterValues("dtinse")!=null) arr_dtinse = request.getParameterValues("dtinse");
        if (request.getParameterValues("dtulag")!=null) arr_dtulag = request.getParameterValues("dtulag");
        if (request.getParameterValues("dtdel")!=null) arr_dtdel = request.getParameterValues("dtdel");
        if (request.getParameterValues("nrposi")!=null) arr_nrposi = request.getParameterValues("nrposi");

        return ;
    }

    public String[] arr_tkmpronp = null; 
    public String[] arr_tkmpron = null; 
    public String[] arr_dtdoc = null; 
    public String[] arr_nrdoc = null; 
    public String[] arr_importo = null; 
    public String[] arr_nota = null; 
    public String[] arr_vacodi = null; 
    public String[] arr_cdazie = null; 
    public String[] arr_cddipa = null; 
    public String[] arr_profil = null; 
    public String[] arr_dtinse = null; 
    public String[] arr_dtulag = null; 
    public String[] arr_dtdel = null; 
    public String[] arr_nrposi = null; 





}

