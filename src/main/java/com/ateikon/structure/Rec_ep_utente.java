package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_ep_utente extends Object {

    public Rec_ep_utente() {

        super();
    }

    public Rec_ep_utente( com.ateikon.common.Ep_utente obj_common_from
                       , com.ateikon.common.Ep_utente obj_common_to   ) {

        super();

        if (obj_common_from !=null){
            this.obj_common_from = obj_common_from;
        }
        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public Rec_ep_utente( com.ateikon.common.Ep_utente obj_common_to   ) {

        super();

        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public com.ateikon.common.Ep_utente obj_common_from = null;
    public com.ateikon.common.Ep_utente obj_common_to   = null;


    public String seStesso = "Rec_ep_utente";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public long       tkutente = 0; 
    public String     user_name = ""; 
    public String     password = ""; 
    public String     dsutente = ""; 
    public String     tkclie = ""; 
    public String     tkforn = ""; 
    public String     cdente = ""; 
    public String     cdagen = ""; 
    public String     cdutente = ""; 
    public String     fgadmin = ""; 
    public String     cdazie = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 
    public String     email = ""; 
    public String     cdispe = ""; 
    public String     cdling = ""; 
    public String     cellulare = ""; 
    public String     nfaxsi = ""; 
    public String     ftp_user_name = ""; 
    public String     ftp_password = ""; 
    public String     ftp_password_back = ""; 
    public String     ftp_fgabil = ""; 
    public String     ftp_fgabil_back = ""; 
    public String     fgabil = ""; 
    public long       tktputente = 0; 
    public long       livello = 0; 
    public long       tksubutente = 0; 
    public Timestamp  dtsendpwd = null; 
    public Timestamp  dtabil = null; 
    public String     cdresp = ""; 
    public Timestamp  dtblocco = null; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.tkutente = 0; 
        this.user_name = ""; 
        this.password = ""; 
        this.dsutente = ""; 
        this.tkclie = ""; 
        this.tkforn = ""; 
        this.cdente = ""; 
        this.cdagen = ""; 
        this.cdutente = ""; 
        this.fgadmin = ""; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 
        this.email = ""; 
        this.cdispe = ""; 
        this.cdling = ""; 
        this.cellulare = ""; 
        this.nfaxsi = ""; 
        this.ftp_user_name = ""; 
        this.ftp_password = ""; 
        this.ftp_password_back = ""; 
        this.ftp_fgabil = ""; 
        this.ftp_fgabil_back = ""; 
        this.fgabil = ""; 
        this.tktputente = 0; 
        this.livello = 0; 
        this.tksubutente = 0; 
        this.dtsendpwd = null; 
        this.dtabil = null; 
        this.cdresp = ""; 
        this.dtblocco = null; 

    }




    /****
        copy_from()
    **/ 

    public void copy_from(Rec_ep_utente astr) throws Exception{ 

        this.tkutente = astr.tkutente;
        this.user_name = astr.user_name;
        this.password = astr.password;
        this.dsutente = astr.dsutente;
        this.tkclie = astr.tkclie;
        this.tkforn = astr.tkforn;
        this.cdente = astr.cdente;
        this.cdagen = astr.cdagen;
        this.cdutente = astr.cdutente;
        this.fgadmin = astr.fgadmin;
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
        this.email = astr.email;
        this.cdispe = astr.cdispe;
        this.cdling = astr.cdling;
        this.cellulare = astr.cellulare;
        this.nfaxsi = astr.nfaxsi;
        this.ftp_user_name = astr.ftp_user_name;
        this.ftp_password = astr.ftp_password;
        this.ftp_password_back = astr.ftp_password_back;
        this.ftp_fgabil = astr.ftp_fgabil;
        this.ftp_fgabil_back = astr.ftp_fgabil_back;
        this.fgabil = astr.fgabil;
        this.tktputente = astr.tktputente;
        this.livello = astr.livello;
        this.tksubutente = astr.tksubutente;
        if (astr.dtsendpwd == null){
           this.dtsendpwd = null;
        }else {
           this.dtsendpwd = (Timestamp ) astr.dtsendpwd.clone();
        }
        if (astr.dtabil == null){
           this.dtabil = null;
        }else {
           this.dtabil = (Timestamp ) astr.dtabil.clone();
        }
        this.cdresp = astr.cdresp;
        if (astr.dtblocco == null){
           this.dtblocco = null;
        }else {
           this.dtblocco = (Timestamp ) astr.dtblocco.clone();
        }

    }





    /****
        setResultSet: ep_utente
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkutente")!=null){
            this.tkutente = rs.getLong      ("tkutente"); 
            if (obj_common_to   != null) obj_common_to.null_tkutente = false;
            if (obj_common_from != null) obj_common_from.null_tkutente = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tkutente = true;
            if (obj_common_from != null) obj_common_from.null_tkutente = true;
        }
        if (rs.getObject("user_name")!=null) this.user_name = rs.getString    ("user_name"); 
        if (rs.getObject("password")!=null) this.password = rs.getString    ("password"); 
        if (rs.getObject("dsutente")!=null) this.dsutente = rs.getString    ("dsutente"); 
        if (rs.getObject("tkclie")!=null) this.tkclie = rs.getString    ("tkclie"); 
        if (rs.getObject("tkforn")!=null) this.tkforn = rs.getString    ("tkforn"); 
        if (rs.getObject("cdente")!=null) this.cdente = rs.getString    ("cdente"); 
        if (rs.getObject("cdagen")!=null) this.cdagen = rs.getString    ("cdagen"); 
        if (rs.getObject("cdutente")!=null) this.cdutente = rs.getString    ("cdutente"); 
        if (rs.getObject("fgadmin")!=null) this.fgadmin = rs.getString    ("fgadmin"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("email")!=null) this.email = rs.getString    ("email"); 
        if (rs.getObject("cdispe")!=null) this.cdispe = rs.getString    ("cdispe"); 
        if (rs.getObject("cdling")!=null) this.cdling = rs.getString    ("cdling"); 
        if (rs.getObject("cellulare")!=null) this.cellulare = rs.getString    ("cellulare"); 
        if (rs.getObject("nfaxsi")!=null) this.nfaxsi = rs.getString    ("nfaxsi"); 
        if (rs.getObject("ftp_user_name")!=null) this.ftp_user_name = rs.getString    ("ftp_user_name"); 
        if (rs.getObject("ftp_password")!=null) this.ftp_password = rs.getString    ("ftp_password"); 
        if (rs.getObject("ftp_password_back")!=null) this.ftp_password_back = rs.getString    ("ftp_password_back"); 
        if (rs.getObject("ftp_fgabil")!=null) this.ftp_fgabil = rs.getString    ("ftp_fgabil"); 
        if (rs.getObject("ftp_fgabil_back")!=null) this.ftp_fgabil_back = rs.getString    ("ftp_fgabil_back"); 
        if (rs.getObject("fgabil")!=null) this.fgabil = rs.getString    ("fgabil"); 
        if (rs.getObject("tktputente")!=null){
            this.tktputente = rs.getLong      ("tktputente"); 
            if (obj_common_to   != null) obj_common_to.null_tktputente = false;
            if (obj_common_from != null) obj_common_from.null_tktputente = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tktputente = true;
            if (obj_common_from != null) obj_common_from.null_tktputente = true;
        }
        if (rs.getObject("livello")!=null){
            this.livello = rs.getLong      ("livello"); 
            if (obj_common_to   != null) obj_common_to.null_livello = false;
            if (obj_common_from != null) obj_common_from.null_livello = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_livello = true;
            if (obj_common_from != null) obj_common_from.null_livello = true;
        }
        if (rs.getObject("tksubutente")!=null){
            this.tksubutente = rs.getLong      ("tksubutente"); 
            if (obj_common_to   != null) obj_common_to.null_tksubutente = false;
            if (obj_common_from != null) obj_common_from.null_tksubutente = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tksubutente = true;
            if (obj_common_from != null) obj_common_from.null_tksubutente = true;
        }
        if (rs.getObject("dtsendpwd")!=null) this.dtsendpwd = rs.getTimestamp ("dtsendpwd"); 
        if (rs.getObject("dtabil")!=null) this.dtabil = rs.getTimestamp ("dtabil"); 
        if (rs.getObject("cdresp")!=null) this.cdresp = rs.getString    ("cdresp"); 
        if (rs.getObject("dtblocco")!=null) this.dtblocco = rs.getTimestamp ("dtblocco"); 

        return 1;
    }




    /****
        setResultSet_key: ep_utente
    **/ 


    public int setResultSet_key ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkutente")!=null){
            this.tkutente = rs.getLong      ("tkutente"); 
            if (obj_common_to   != null) obj_common_to.null_tkutente = false;
            if (obj_common_from != null) obj_common_from.null_tkutente = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tkutente = true;
            if (obj_common_from != null) obj_common_from.null_tkutente = true;
        }

        return 1;
    }




    /****
        trim(): ep_utente
    **/ 


    public void trim( ) throws Exception {

        this.user_name = this.user_name.trim(); 
        this.password = this.password.trim(); 
        this.dsutente = this.dsutente.trim(); 
        this.tkclie = this.tkclie.trim(); 
        this.tkforn = this.tkforn.trim(); 
        this.cdente = this.cdente.trim(); 
        this.cdagen = this.cdagen.trim(); 
        this.cdutente = this.cdutente.trim(); 
        this.fgadmin = this.fgadmin.trim(); 
        this.cdazie = this.cdazie.trim(); 
        this.cddipa = this.cddipa.trim(); 
        this.profil = this.profil.trim(); 
        this.email = this.email.trim(); 
        this.cdispe = this.cdispe.trim(); 
        this.cdling = this.cdling.trim(); 
        this.cellulare = this.cellulare.trim(); 
        this.nfaxsi = this.nfaxsi.trim(); 
        this.ftp_user_name = this.ftp_user_name.trim(); 
        this.ftp_password = this.ftp_password.trim(); 
        this.ftp_password_back = this.ftp_password_back.trim(); 
        this.ftp_fgabil = this.ftp_fgabil.trim(); 
        this.ftp_fgabil_back = this.ftp_fgabil_back.trim(); 
        this.fgabil = this.fgabil.trim(); 
        this.cdresp = this.cdresp.trim(); 

    }



    public void rtrim( ) throws Exception {

        this.user_name = of_rtrim(this.user_name); 
        this.password = of_rtrim(this.password); 
        this.dsutente = of_rtrim(this.dsutente); 
        this.tkclie = of_rtrim(this.tkclie); 
        this.tkforn = of_rtrim(this.tkforn); 
        this.cdente = of_rtrim(this.cdente); 
        this.cdagen = of_rtrim(this.cdagen); 
        this.cdutente = of_rtrim(this.cdutente); 
        this.fgadmin = of_rtrim(this.fgadmin); 
        this.cdazie = of_rtrim(this.cdazie); 
        this.cddipa = of_rtrim(this.cddipa); 
        this.profil = of_rtrim(this.profil); 
        this.email = of_rtrim(this.email); 
        this.cdispe = of_rtrim(this.cdispe); 
        this.cdling = of_rtrim(this.cdling); 
        this.cellulare = of_rtrim(this.cellulare); 
        this.nfaxsi = of_rtrim(this.nfaxsi); 
        this.ftp_user_name = of_rtrim(this.ftp_user_name); 
        this.ftp_password = of_rtrim(this.ftp_password); 
        this.ftp_password_back = of_rtrim(this.ftp_password_back); 
        this.ftp_fgabil = of_rtrim(this.ftp_fgabil); 
        this.ftp_fgabil_back = of_rtrim(this.ftp_fgabil_back); 
        this.fgabil = of_rtrim(this.fgabil); 
        this.cdresp = of_rtrim(this.cdresp); 

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
        ctrl_mindate(): ep_utente
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
        li_sa = 0; 
        if (this.dtsendpwd != null){ 
            li_sa = this.dtsendpwd.getYear()+1900; 
            if (li_sa < 1900){ 
                this.dtsendpwd.setYear(0); 
                this.dtsendpwd.setMonth(0); 
                this.dtsendpwd.setDate(1); 
                this.dtsendpwd.setHours(0); 
                this.dtsendpwd.setMinutes(0); 
                this.dtsendpwd.setSeconds(0); 
                this.dtsendpwd.setNanos(0); 
            } 
            this.dtsendpwd.setNanos(0); 
            this.dtsendpwd.toString(); 
        } 
        li_sa = 0; 
        if (this.dtabil != null){ 
            li_sa = this.dtabil.getYear()+1900; 
            if (li_sa < 1900){ 
                this.dtabil.setYear(0); 
                this.dtabil.setMonth(0); 
                this.dtabil.setDate(1); 
                this.dtabil.setHours(0); 
                this.dtabil.setMinutes(0); 
                this.dtabil.setSeconds(0); 
                this.dtabil.setNanos(0); 
            } 
            this.dtabil.setNanos(0); 
            this.dtabil.toString(); 
        } 
        li_sa = 0; 
        if (this.dtblocco != null){ 
            li_sa = this.dtblocco.getYear()+1900; 
            if (li_sa < 1900){ 
                this.dtblocco.setYear(0); 
                this.dtblocco.setMonth(0); 
                this.dtblocco.setDate(1); 
                this.dtblocco.setHours(0); 
                this.dtblocco.setMinutes(0); 
                this.dtblocco.setSeconds(0); 
                this.dtblocco.setNanos(0); 
            } 
            this.dtblocco.setNanos(0); 
            this.dtblocco.toString(); 
        } 

    }




    /****
        requestValues(request):   
    **/ 

    public void requestValues(javax.servlet.http.HttpServletRequest request) throws Exception{ 

        if (request.getParameterValues("tkutente")!=null) arr_tkutente = request.getParameterValues("tkutente");
        if (request.getParameterValues("user_name")!=null) arr_user_name = request.getParameterValues("user_name");
        if (request.getParameterValues("password")!=null) arr_password = request.getParameterValues("password");
        if (request.getParameterValues("dsutente")!=null) arr_dsutente = request.getParameterValues("dsutente");
        if (request.getParameterValues("tkclie")!=null) arr_tkclie = request.getParameterValues("tkclie");
        if (request.getParameterValues("tkforn")!=null) arr_tkforn = request.getParameterValues("tkforn");
        if (request.getParameterValues("cdente")!=null) arr_cdente = request.getParameterValues("cdente");
        if (request.getParameterValues("cdagen")!=null) arr_cdagen = request.getParameterValues("cdagen");
        if (request.getParameterValues("cdutente")!=null) arr_cdutente = request.getParameterValues("cdutente");
        if (request.getParameterValues("fgadmin")!=null) arr_fgadmin = request.getParameterValues("fgadmin");
        if (request.getParameterValues("cdazie")!=null) arr_cdazie = request.getParameterValues("cdazie");
        if (request.getParameterValues("cddipa")!=null) arr_cddipa = request.getParameterValues("cddipa");
        if (request.getParameterValues("profil")!=null) arr_profil = request.getParameterValues("profil");
        if (request.getParameterValues("dtinse")!=null) arr_dtinse = request.getParameterValues("dtinse");
        if (request.getParameterValues("dtulag")!=null) arr_dtulag = request.getParameterValues("dtulag");
        if (request.getParameterValues("email")!=null) arr_email = request.getParameterValues("email");
        if (request.getParameterValues("cdispe")!=null) arr_cdispe = request.getParameterValues("cdispe");
        if (request.getParameterValues("cdling")!=null) arr_cdling = request.getParameterValues("cdling");
        if (request.getParameterValues("cellulare")!=null) arr_cellulare = request.getParameterValues("cellulare");
        if (request.getParameterValues("nfaxsi")!=null) arr_nfaxsi = request.getParameterValues("nfaxsi");
        if (request.getParameterValues("ftp_user_name")!=null) arr_ftp_user_name = request.getParameterValues("ftp_user_name");
        if (request.getParameterValues("ftp_password")!=null) arr_ftp_password = request.getParameterValues("ftp_password");
        if (request.getParameterValues("ftp_password_back")!=null) arr_ftp_password_back = request.getParameterValues("ftp_password_back");
        if (request.getParameterValues("ftp_fgabil")!=null) arr_ftp_fgabil = request.getParameterValues("ftp_fgabil");
        if (request.getParameterValues("ftp_fgabil_back")!=null) arr_ftp_fgabil_back = request.getParameterValues("ftp_fgabil_back");
        if (request.getParameterValues("fgabil")!=null) arr_fgabil = request.getParameterValues("fgabil");
        if (request.getParameterValues("tktputente")!=null) arr_tktputente = request.getParameterValues("tktputente");
        if (request.getParameterValues("livello")!=null) arr_livello = request.getParameterValues("livello");
        if (request.getParameterValues("tksubutente")!=null) arr_tksubutente = request.getParameterValues("tksubutente");
        if (request.getParameterValues("dtsendpwd")!=null) arr_dtsendpwd = request.getParameterValues("dtsendpwd");
        if (request.getParameterValues("dtabil")!=null) arr_dtabil = request.getParameterValues("dtabil");
        if (request.getParameterValues("cdresp")!=null) arr_cdresp = request.getParameterValues("cdresp");
        if (request.getParameterValues("dtblocco")!=null) arr_dtblocco = request.getParameterValues("dtblocco");

        return ;
    }

    public String[] arr_tkutente = null; 
    public String[] arr_user_name = null; 
    public String[] arr_password = null; 
    public String[] arr_dsutente = null; 
    public String[] arr_tkclie = null; 
    public String[] arr_tkforn = null; 
    public String[] arr_cdente = null; 
    public String[] arr_cdagen = null; 
    public String[] arr_cdutente = null; 
    public String[] arr_fgadmin = null; 
    public String[] arr_cdazie = null; 
    public String[] arr_cddipa = null; 
    public String[] arr_profil = null; 
    public String[] arr_dtinse = null; 
    public String[] arr_dtulag = null; 
    public String[] arr_email = null; 
    public String[] arr_cdispe = null; 
    public String[] arr_cdling = null; 
    public String[] arr_cellulare = null; 
    public String[] arr_nfaxsi = null; 
    public String[] arr_ftp_user_name = null; 
    public String[] arr_ftp_password = null; 
    public String[] arr_ftp_password_back = null; 
    public String[] arr_ftp_fgabil = null; 
    public String[] arr_ftp_fgabil_back = null; 
    public String[] arr_fgabil = null; 
    public String[] arr_tktputente = null; 
    public String[] arr_livello = null; 
    public String[] arr_tksubutente = null; 
    public String[] arr_dtsendpwd = null; 
    public String[] arr_dtabil = null; 
    public String[] arr_cdresp = null; 
    public String[] arr_dtblocco = null; 





}

