package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_archrubrica_tipo extends Object {

    public Rec_archrubrica_tipo() {

        super();
    }

    public Rec_archrubrica_tipo( com.ateikon.common.Archrubrica_tipo obj_common_from
                       , com.ateikon.common.Archrubrica_tipo obj_common_to   ) {

        super();

        if (obj_common_from !=null){
            this.obj_common_from = obj_common_from;
        }
        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public Rec_archrubrica_tipo( com.ateikon.common.Archrubrica_tipo obj_common_to   ) {

        super();

        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public com.ateikon.common.Archrubrica_tipo obj_common_from = null;
    public com.ateikon.common.Archrubrica_tipo obj_common_to   = null;


    public String seStesso = "Rec_archrubrica_tipo";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public long       tkrubr_tipo = 0; 
    public String     cdrubr_tipo_m = ""; 
    public String     dsrubr_tipo = ""; 
    public String     fgabil = ""; 
    public String     cdazie = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 
    public long       nrposi = 0; 
    public String     fgdef = ""; 
    public String     fggest = ""; 
    public String     tkrif_name = ""; 
    public String     fgofferta = ""; 
    public String     fgtrattativa = ""; 
    public String     dsrubr_tipo_en = ""; 
    public String     dsrubr_tipo_de = ""; 
    public String     dsrubr_tipo_fr = ""; 
    public String     dsrubr_tipo_es = ""; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.tkrubr_tipo = 0; 
        this.cdrubr_tipo_m = ""; 
        this.dsrubr_tipo = ""; 
        this.fgabil = ""; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 
        this.nrposi = 0; 
        this.fgdef = ""; 
        this.fggest = ""; 
        this.tkrif_name = ""; 
        this.fgofferta = ""; 
        this.fgtrattativa = ""; 
        this.dsrubr_tipo_en = ""; 
        this.dsrubr_tipo_de = ""; 
        this.dsrubr_tipo_fr = ""; 
        this.dsrubr_tipo_es = ""; 

    }




    /****
        copy_from()
    **/ 

    public void copy_from(Rec_archrubrica_tipo astr) throws Exception{ 

        this.tkrubr_tipo = astr.tkrubr_tipo;
        this.cdrubr_tipo_m = astr.cdrubr_tipo_m;
        this.dsrubr_tipo = astr.dsrubr_tipo;
        this.fgabil = astr.fgabil;
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
        this.fgdef = astr.fgdef;
        this.fggest = astr.fggest;
        this.tkrif_name = astr.tkrif_name;
        this.fgofferta = astr.fgofferta;
        this.fgtrattativa = astr.fgtrattativa;
        this.dsrubr_tipo_en = astr.dsrubr_tipo_en;
        this.dsrubr_tipo_de = astr.dsrubr_tipo_de;
        this.dsrubr_tipo_fr = astr.dsrubr_tipo_fr;
        this.dsrubr_tipo_es = astr.dsrubr_tipo_es;

    }





    /****
        setResultSet: archrubrica_tipo
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkrubr_tipo")!=null){
            this.tkrubr_tipo = rs.getLong      ("tkrubr_tipo"); 
            if (obj_common_to   != null) obj_common_to.null_tkrubr_tipo = false;
            if (obj_common_from != null) obj_common_from.null_tkrubr_tipo = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tkrubr_tipo = true;
            if (obj_common_from != null) obj_common_from.null_tkrubr_tipo = true;
        }
        if (rs.getObject("cdrubr_tipo_m")!=null) this.cdrubr_tipo_m = rs.getString    ("cdrubr_tipo_m"); 
        if (rs.getObject("dsrubr_tipo")!=null) this.dsrubr_tipo = rs.getString    ("dsrubr_tipo"); 
        if (rs.getObject("fgabil")!=null) this.fgabil = rs.getString    ("fgabil"); 
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
        if (rs.getObject("fgdef")!=null) this.fgdef = rs.getString    ("fgdef"); 
        if (rs.getObject("fggest")!=null) this.fggest = rs.getString    ("fggest"); 
        if (rs.getObject("tkrif_name")!=null) this.tkrif_name = rs.getString    ("tkrif_name"); 
        if (rs.getObject("fgofferta")!=null) this.fgofferta = rs.getString    ("fgofferta"); 
        if (rs.getObject("fgtrattativa")!=null) this.fgtrattativa = rs.getString    ("fgtrattativa"); 
        if (rs.getObject("dsrubr_tipo_en")!=null) this.dsrubr_tipo_en = rs.getString    ("dsrubr_tipo_en"); 
        if (rs.getObject("dsrubr_tipo_de")!=null) this.dsrubr_tipo_de = rs.getString    ("dsrubr_tipo_de"); 
        if (rs.getObject("dsrubr_tipo_fr")!=null) this.dsrubr_tipo_fr = rs.getString    ("dsrubr_tipo_fr"); 
        if (rs.getObject("dsrubr_tipo_es")!=null) this.dsrubr_tipo_es = rs.getString    ("dsrubr_tipo_es"); 

        return 1;
    }




    /****
        setResultSet_key: archrubrica_tipo
    **/ 


    public int setResultSet_key ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkrubr_tipo")!=null){
            this.tkrubr_tipo = rs.getLong      ("tkrubr_tipo"); 
            if (obj_common_to   != null) obj_common_to.null_tkrubr_tipo = false;
            if (obj_common_from != null) obj_common_from.null_tkrubr_tipo = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tkrubr_tipo = true;
            if (obj_common_from != null) obj_common_from.null_tkrubr_tipo = true;
        }

        return 1;
    }




    /****
        trim(): archrubrica_tipo
    **/ 


    public void trim( ) throws Exception {

        this.cdrubr_tipo_m = this.cdrubr_tipo_m.trim(); 
        this.dsrubr_tipo = this.dsrubr_tipo.trim(); 
        this.fgabil = this.fgabil.trim(); 
        this.cdazie = this.cdazie.trim(); 
        this.cddipa = this.cddipa.trim(); 
        this.profil = this.profil.trim(); 
        this.fgdef = this.fgdef.trim(); 
        this.fggest = this.fggest.trim(); 
        this.tkrif_name = this.tkrif_name.trim(); 
        this.fgofferta = this.fgofferta.trim(); 
        this.fgtrattativa = this.fgtrattativa.trim(); 
        this.dsrubr_tipo_en = this.dsrubr_tipo_en.trim(); 
        this.dsrubr_tipo_de = this.dsrubr_tipo_de.trim(); 
        this.dsrubr_tipo_fr = this.dsrubr_tipo_fr.trim(); 
        this.dsrubr_tipo_es = this.dsrubr_tipo_es.trim(); 

    }



    public void rtrim( ) throws Exception {

        this.cdrubr_tipo_m = of_rtrim(this.cdrubr_tipo_m); 
        this.dsrubr_tipo = of_rtrim(this.dsrubr_tipo); 
        this.fgabil = of_rtrim(this.fgabil); 
        this.cdazie = of_rtrim(this.cdazie); 
        this.cddipa = of_rtrim(this.cddipa); 
        this.profil = of_rtrim(this.profil); 
        this.fgdef = of_rtrim(this.fgdef); 
        this.fggest = of_rtrim(this.fggest); 
        this.tkrif_name = of_rtrim(this.tkrif_name); 
        this.fgofferta = of_rtrim(this.fgofferta); 
        this.fgtrattativa = of_rtrim(this.fgtrattativa); 
        this.dsrubr_tipo_en = of_rtrim(this.dsrubr_tipo_en); 
        this.dsrubr_tipo_de = of_rtrim(this.dsrubr_tipo_de); 
        this.dsrubr_tipo_fr = of_rtrim(this.dsrubr_tipo_fr); 
        this.dsrubr_tipo_es = of_rtrim(this.dsrubr_tipo_es); 

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
        ctrl_mindate(): archrubrica_tipo
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

        if (request.getParameterValues("tkrubr_tipo")!=null) arr_tkrubr_tipo = request.getParameterValues("tkrubr_tipo");
        if (request.getParameterValues("cdrubr_tipo_m")!=null) arr_cdrubr_tipo_m = request.getParameterValues("cdrubr_tipo_m");
        if (request.getParameterValues("dsrubr_tipo")!=null) arr_dsrubr_tipo = request.getParameterValues("dsrubr_tipo");
        if (request.getParameterValues("fgabil")!=null) arr_fgabil = request.getParameterValues("fgabil");
        if (request.getParameterValues("cdazie")!=null) arr_cdazie = request.getParameterValues("cdazie");
        if (request.getParameterValues("cddipa")!=null) arr_cddipa = request.getParameterValues("cddipa");
        if (request.getParameterValues("profil")!=null) arr_profil = request.getParameterValues("profil");
        if (request.getParameterValues("dtinse")!=null) arr_dtinse = request.getParameterValues("dtinse");
        if (request.getParameterValues("dtulag")!=null) arr_dtulag = request.getParameterValues("dtulag");
        if (request.getParameterValues("nrposi")!=null) arr_nrposi = request.getParameterValues("nrposi");
        if (request.getParameterValues("fgdef")!=null) arr_fgdef = request.getParameterValues("fgdef");
        if (request.getParameterValues("fggest")!=null) arr_fggest = request.getParameterValues("fggest");
        if (request.getParameterValues("tkrif_name")!=null) arr_tkrif_name = request.getParameterValues("tkrif_name");
        if (request.getParameterValues("fgofferta")!=null) arr_fgofferta = request.getParameterValues("fgofferta");
        if (request.getParameterValues("fgtrattativa")!=null) arr_fgtrattativa = request.getParameterValues("fgtrattativa");
        if (request.getParameterValues("dsrubr_tipo_en")!=null) arr_dsrubr_tipo_en = request.getParameterValues("dsrubr_tipo_en");
        if (request.getParameterValues("dsrubr_tipo_de")!=null) arr_dsrubr_tipo_de = request.getParameterValues("dsrubr_tipo_de");
        if (request.getParameterValues("dsrubr_tipo_fr")!=null) arr_dsrubr_tipo_fr = request.getParameterValues("dsrubr_tipo_fr");
        if (request.getParameterValues("dsrubr_tipo_es")!=null) arr_dsrubr_tipo_es = request.getParameterValues("dsrubr_tipo_es");

        return ;
    }

    public String[] arr_tkrubr_tipo = null; 
    public String[] arr_cdrubr_tipo_m = null; 
    public String[] arr_dsrubr_tipo = null; 
    public String[] arr_fgabil = null; 
    public String[] arr_cdazie = null; 
    public String[] arr_cddipa = null; 
    public String[] arr_profil = null; 
    public String[] arr_dtinse = null; 
    public String[] arr_dtulag = null; 
    public String[] arr_nrposi = null; 
    public String[] arr_fgdef = null; 
    public String[] arr_fggest = null; 
    public String[] arr_tkrif_name = null; 
    public String[] arr_fgofferta = null; 
    public String[] arr_fgtrattativa = null; 
    public String[] arr_dsrubr_tipo_en = null; 
    public String[] arr_dsrubr_tipo_de = null; 
    public String[] arr_dsrubr_tipo_fr = null; 
    public String[] arr_dsrubr_tipo_es = null; 





}

