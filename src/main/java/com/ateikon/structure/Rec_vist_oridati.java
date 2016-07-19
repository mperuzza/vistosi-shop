package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_vist_oridati extends Object {

    public Rec_vist_oridati() {

        super();
    }

    public Rec_vist_oridati( com.ateikon.common.Vist_oridati obj_common_from
                       , com.ateikon.common.Vist_oridati obj_common_to   ) {

        super();

        if (obj_common_from !=null){
            this.obj_common_from = obj_common_from;
        }
        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public Rec_vist_oridati( com.ateikon.common.Vist_oridati obj_common_to   ) {

        super();

        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public com.ateikon.common.Vist_oridati obj_common_from = null;
    public com.ateikon.common.Vist_oridati obj_common_to   = null;


    public String seStesso = "Rec_vist_oridati";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public String     oridati = ""; 
    public String     oridati_m = ""; 
    public String     dsoridati = ""; 
    public String     dsoridati_en = ""; 
    public String     dsoridati_de = ""; 
    public String     dsoridati_fr = ""; 
    public String     dsoridati_es = ""; 
    public String     cdazie = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 
    public String     dsoridati_ru = ""; 
    public String     fgdef = ""; 
    public String     cdtporidati = ""; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.oridati = ""; 
        this.oridati_m = ""; 
        this.dsoridati = ""; 
        this.dsoridati_en = ""; 
        this.dsoridati_de = ""; 
        this.dsoridati_fr = ""; 
        this.dsoridati_es = ""; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 
        this.dsoridati_ru = ""; 
        this.fgdef = ""; 
        this.cdtporidati = ""; 

    }




    /****
        copy_from()
    **/ 

    public void copy_from(Rec_vist_oridati astr) throws Exception{ 

        this.oridati = astr.oridati;
        this.oridati_m = astr.oridati_m;
        this.dsoridati = astr.dsoridati;
        this.dsoridati_en = astr.dsoridati_en;
        this.dsoridati_de = astr.dsoridati_de;
        this.dsoridati_fr = astr.dsoridati_fr;
        this.dsoridati_es = astr.dsoridati_es;
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
        this.dsoridati_ru = astr.dsoridati_ru;
        this.fgdef = astr.fgdef;
        this.cdtporidati = astr.cdtporidati;

    }





    /****
        setResultSet: vist_oridati
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("oridati")!=null) this.oridati = rs.getString    ("oridati"); 
        if (rs.getObject("oridati_m")!=null) this.oridati_m = rs.getString    ("oridati_m"); 
        if (rs.getObject("dsoridati")!=null) this.dsoridati = rs.getString    ("dsoridati"); 
        if (rs.getObject("dsoridati_en")!=null) this.dsoridati_en = rs.getString    ("dsoridati_en"); 
        if (rs.getObject("dsoridati_de")!=null) this.dsoridati_de = rs.getString    ("dsoridati_de"); 
        if (rs.getObject("dsoridati_fr")!=null) this.dsoridati_fr = rs.getString    ("dsoridati_fr"); 
        if (rs.getObject("dsoridati_es")!=null) this.dsoridati_es = rs.getString    ("dsoridati_es"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("dsoridati_ru")!=null) this.dsoridati_ru = rs.getString    ("dsoridati_ru"); 
        if (rs.getObject("fgdef")!=null) this.fgdef = rs.getString    ("fgdef"); 
        if (rs.getObject("cdtporidati")!=null) this.cdtporidati = rs.getString    ("cdtporidati"); 

        return 1;
    }




    /****
        setResultSet_key: vist_oridati
    **/ 


    public int setResultSet_key ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("oridati")!=null) this.oridati = rs.getString    ("oridati"); 

        return 1;
    }




    /****
        trim(): vist_oridati
    **/ 


    public void trim( ) throws Exception {

        this.oridati = this.oridati.trim(); 
        this.oridati_m = this.oridati_m.trim(); 
        this.dsoridati = this.dsoridati.trim(); 
        this.dsoridati_en = this.dsoridati_en.trim(); 
        this.dsoridati_de = this.dsoridati_de.trim(); 
        this.dsoridati_fr = this.dsoridati_fr.trim(); 
        this.dsoridati_es = this.dsoridati_es.trim(); 
        this.cdazie = this.cdazie.trim(); 
        this.cddipa = this.cddipa.trim(); 
        this.profil = this.profil.trim(); 
        this.dsoridati_ru = this.dsoridati_ru.trim(); 
        this.fgdef = this.fgdef.trim(); 
        this.cdtporidati = this.cdtporidati.trim(); 

    }



    public void rtrim( ) throws Exception {

        this.oridati = of_rtrim(this.oridati); 
        this.oridati_m = of_rtrim(this.oridati_m); 
        this.dsoridati = of_rtrim(this.dsoridati); 
        this.dsoridati_en = of_rtrim(this.dsoridati_en); 
        this.dsoridati_de = of_rtrim(this.dsoridati_de); 
        this.dsoridati_fr = of_rtrim(this.dsoridati_fr); 
        this.dsoridati_es = of_rtrim(this.dsoridati_es); 
        this.cdazie = of_rtrim(this.cdazie); 
        this.cddipa = of_rtrim(this.cddipa); 
        this.profil = of_rtrim(this.profil); 
        this.dsoridati_ru = of_rtrim(this.dsoridati_ru); 
        this.fgdef = of_rtrim(this.fgdef); 
        this.cdtporidati = of_rtrim(this.cdtporidati); 

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
        ctrl_mindate(): vist_oridati
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

        if (request.getParameterValues("oridati")!=null) arr_oridati = request.getParameterValues("oridati");
        if (request.getParameterValues("oridati_m")!=null) arr_oridati_m = request.getParameterValues("oridati_m");
        if (request.getParameterValues("dsoridati")!=null) arr_dsoridati = request.getParameterValues("dsoridati");
        if (request.getParameterValues("dsoridati_en")!=null) arr_dsoridati_en = request.getParameterValues("dsoridati_en");
        if (request.getParameterValues("dsoridati_de")!=null) arr_dsoridati_de = request.getParameterValues("dsoridati_de");
        if (request.getParameterValues("dsoridati_fr")!=null) arr_dsoridati_fr = request.getParameterValues("dsoridati_fr");
        if (request.getParameterValues("dsoridati_es")!=null) arr_dsoridati_es = request.getParameterValues("dsoridati_es");
        if (request.getParameterValues("cdazie")!=null) arr_cdazie = request.getParameterValues("cdazie");
        if (request.getParameterValues("cddipa")!=null) arr_cddipa = request.getParameterValues("cddipa");
        if (request.getParameterValues("profil")!=null) arr_profil = request.getParameterValues("profil");
        if (request.getParameterValues("dtinse")!=null) arr_dtinse = request.getParameterValues("dtinse");
        if (request.getParameterValues("dtulag")!=null) arr_dtulag = request.getParameterValues("dtulag");
        if (request.getParameterValues("dsoridati_ru")!=null) arr_dsoridati_ru = request.getParameterValues("dsoridati_ru");
        if (request.getParameterValues("fgdef")!=null) arr_fgdef = request.getParameterValues("fgdef");
        if (request.getParameterValues("cdtporidati")!=null) arr_cdtporidati = request.getParameterValues("cdtporidati");

        return ;
    }

    public String[] arr_oridati = null; 
    public String[] arr_oridati_m = null; 
    public String[] arr_dsoridati = null; 
    public String[] arr_dsoridati_en = null; 
    public String[] arr_dsoridati_de = null; 
    public String[] arr_dsoridati_fr = null; 
    public String[] arr_dsoridati_es = null; 
    public String[] arr_cdazie = null; 
    public String[] arr_cddipa = null; 
    public String[] arr_profil = null; 
    public String[] arr_dtinse = null; 
    public String[] arr_dtulag = null; 
    public String[] arr_dsoridati_ru = null; 
    public String[] arr_fgdef = null; 
    public String[] arr_cdtporidati = null; 





}

