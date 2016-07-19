package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_atk_messaggio extends Object {

    public Rec_atk_messaggio() {

        super();
    }

    public Rec_atk_messaggio( com.ateikon.common.Atk_messaggio obj_common_from
                       , com.ateikon.common.Atk_messaggio obj_common_to   ) {

        super();

        if (obj_common_from !=null){
            this.obj_common_from = obj_common_from;
        }
        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public Rec_atk_messaggio( com.ateikon.common.Atk_messaggio obj_common_to   ) {

        super();

        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public com.ateikon.common.Atk_messaggio obj_common_from = null;
    public com.ateikon.common.Atk_messaggio obj_common_to   = null;


    public String seStesso = "Rec_atk_messaggio";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public long       tkmsg = 0; 
    public String     host = ""; 
    public String     username = ""; 
    public String     password = ""; 
    public String     oggetto = ""; 
    public String     mittente = ""; 
    public String     contenuto = ""; 
    public String     dest1 = ""; 
    public String     dest2 = ""; 
    public String     dest3 = ""; 
    public String     dest4 = ""; 
    public String     bcc1 = ""; 
    public String     bcc2 = ""; 
    public String     bcc3 = ""; 
    public String     bcc4 = ""; 
    public String     nomefile = ""; 
    public String     pathfile = ""; 
    public Timestamp  dtricsped = null; 
    public Timestamp  dtproc = null; 
    public String     nota = ""; 
    public long       id_object = 0; 
    public long       tkboll = 0; 
    public String     tpservizio = ""; 
    public String     cdazie = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 
    public Timestamp  dtsped = null; 
    public long       tkml = 0; 
    public long       tkordi = 0; 
    public long       tkordiacq = 0; 
    public long       tkfatt = 0; 
    public String     smtp_port = ""; 
    public long       tkbollacq = 0; 
    public long       tkfattacq = 0; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.tkmsg = 0; 
        this.host = ""; 
        this.username = ""; 
        this.password = ""; 
        this.oggetto = ""; 
        this.mittente = ""; 
        this.contenuto = ""; 
        this.dest1 = ""; 
        this.dest2 = ""; 
        this.dest3 = ""; 
        this.dest4 = ""; 
        this.bcc1 = ""; 
        this.bcc2 = ""; 
        this.bcc3 = ""; 
        this.bcc4 = ""; 
        this.nomefile = ""; 
        this.pathfile = ""; 
        this.dtricsped = null; 
        this.dtproc = null; 
        this.nota = ""; 
        this.id_object = 0; 
        this.tkboll = 0; 
        this.tpservizio = ""; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 
        this.dtsped = null; 
        this.tkml = 0; 
        this.tkordi = 0; 
        this.tkordiacq = 0; 
        this.tkfatt = 0; 
        this.smtp_port = ""; 
        this.tkbollacq = 0; 
        this.tkfattacq = 0; 

    }




    /****
        copy_from()
    **/ 

    public void copy_from(Rec_atk_messaggio astr) throws Exception{ 

        this.tkmsg = astr.tkmsg;
        this.host = astr.host;
        this.username = astr.username;
        this.password = astr.password;
        this.oggetto = astr.oggetto;
        this.mittente = astr.mittente;
        this.contenuto = astr.contenuto;
        this.dest1 = astr.dest1;
        this.dest2 = astr.dest2;
        this.dest3 = astr.dest3;
        this.dest4 = astr.dest4;
        this.bcc1 = astr.bcc1;
        this.bcc2 = astr.bcc2;
        this.bcc3 = astr.bcc3;
        this.bcc4 = astr.bcc4;
        this.nomefile = astr.nomefile;
        this.pathfile = astr.pathfile;
        if (astr.dtricsped == null){
           this.dtricsped = null;
        }else {
           this.dtricsped = (Timestamp ) astr.dtricsped.clone();
        }
        if (astr.dtproc == null){
           this.dtproc = null;
        }else {
           this.dtproc = (Timestamp ) astr.dtproc.clone();
        }
        this.nota = astr.nota;
        this.id_object = astr.id_object;
        this.tkboll = astr.tkboll;
        this.tpservizio = astr.tpservizio;
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
        if (astr.dtsped == null){
           this.dtsped = null;
        }else {
           this.dtsped = (Timestamp ) astr.dtsped.clone();
        }
        this.tkml = astr.tkml;
        this.tkordi = astr.tkordi;
        this.tkordiacq = astr.tkordiacq;
        this.tkfatt = astr.tkfatt;
        this.smtp_port = astr.smtp_port;
        this.tkbollacq = astr.tkbollacq;
        this.tkfattacq = astr.tkfattacq;

    }





    /****
        setResultSet: atk_messaggio
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
        if (rs.getObject("host")!=null) this.host = rs.getString    ("host"); 
        if (rs.getObject("username")!=null) this.username = rs.getString    ("username"); 
        if (rs.getObject("password")!=null) this.password = rs.getString    ("password"); 
        if (rs.getObject("oggetto")!=null) this.oggetto = rs.getString    ("oggetto"); 
        if (rs.getObject("mittente")!=null) this.mittente = rs.getString    ("mittente"); 
        if (rs.getObject("contenuto")!=null) this.contenuto = rs.getString    ("contenuto"); 
        if (rs.getObject("dest1")!=null) this.dest1 = rs.getString    ("dest1"); 
        if (rs.getObject("dest2")!=null) this.dest2 = rs.getString    ("dest2"); 
        if (rs.getObject("dest3")!=null) this.dest3 = rs.getString    ("dest3"); 
        if (rs.getObject("dest4")!=null) this.dest4 = rs.getString    ("dest4"); 
        if (rs.getObject("bcc1")!=null) this.bcc1 = rs.getString    ("bcc1"); 
        if (rs.getObject("bcc2")!=null) this.bcc2 = rs.getString    ("bcc2"); 
        if (rs.getObject("bcc3")!=null) this.bcc3 = rs.getString    ("bcc3"); 
        if (rs.getObject("bcc4")!=null) this.bcc4 = rs.getString    ("bcc4"); 
        if (rs.getObject("nomefile")!=null) this.nomefile = rs.getString    ("nomefile"); 
        if (rs.getObject("pathfile")!=null) this.pathfile = rs.getString    ("pathfile"); 
        if (rs.getObject("dtricsped")!=null) this.dtricsped = rs.getTimestamp ("dtricsped"); 
        if (rs.getObject("dtproc")!=null) this.dtproc = rs.getTimestamp ("dtproc"); 
        if (rs.getObject("nota")!=null) this.nota = rs.getString    ("nota"); 
        if (rs.getObject("id_object")!=null){
            this.id_object = rs.getLong      ("id_object"); 
            if (obj_common_to   != null) obj_common_to.null_id_object = false;
            if (obj_common_from != null) obj_common_from.null_id_object = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_id_object = true;
            if (obj_common_from != null) obj_common_from.null_id_object = true;
        }
        if (rs.getObject("tkboll")!=null){
            this.tkboll = rs.getLong      ("tkboll"); 
            if (obj_common_to   != null) obj_common_to.null_tkboll = false;
            if (obj_common_from != null) obj_common_from.null_tkboll = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tkboll = true;
            if (obj_common_from != null) obj_common_from.null_tkboll = true;
        }
        if (rs.getObject("tpservizio")!=null) this.tpservizio = rs.getString    ("tpservizio"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("dtsped")!=null) this.dtsped = rs.getTimestamp ("dtsped"); 
        if (rs.getObject("tkml")!=null){
            this.tkml = rs.getLong      ("tkml"); 
            if (obj_common_to   != null) obj_common_to.null_tkml = false;
            if (obj_common_from != null) obj_common_from.null_tkml = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tkml = true;
            if (obj_common_from != null) obj_common_from.null_tkml = true;
        }
        if (rs.getObject("tkordi")!=null){
            this.tkordi = rs.getLong      ("tkordi"); 
            if (obj_common_to   != null) obj_common_to.null_tkordi = false;
            if (obj_common_from != null) obj_common_from.null_tkordi = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tkordi = true;
            if (obj_common_from != null) obj_common_from.null_tkordi = true;
        }
        if (rs.getObject("tkordiacq")!=null){
            this.tkordiacq = rs.getLong      ("tkordiacq"); 
            if (obj_common_to   != null) obj_common_to.null_tkordiacq = false;
            if (obj_common_from != null) obj_common_from.null_tkordiacq = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tkordiacq = true;
            if (obj_common_from != null) obj_common_from.null_tkordiacq = true;
        }
        if (rs.getObject("tkfatt")!=null){
            this.tkfatt = rs.getLong      ("tkfatt"); 
            if (obj_common_to   != null) obj_common_to.null_tkfatt = false;
            if (obj_common_from != null) obj_common_from.null_tkfatt = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tkfatt = true;
            if (obj_common_from != null) obj_common_from.null_tkfatt = true;
        }
        if (rs.getObject("smtp_port")!=null) this.smtp_port = rs.getString    ("smtp_port"); 
        if (rs.getObject("tkbollacq")!=null){
            this.tkbollacq = rs.getLong      ("tkbollacq"); 
            if (obj_common_to   != null) obj_common_to.null_tkbollacq = false;
            if (obj_common_from != null) obj_common_from.null_tkbollacq = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tkbollacq = true;
            if (obj_common_from != null) obj_common_from.null_tkbollacq = true;
        }
        if (rs.getObject("tkfattacq")!=null){
            this.tkfattacq = rs.getLong      ("tkfattacq"); 
            if (obj_common_to   != null) obj_common_to.null_tkfattacq = false;
            if (obj_common_from != null) obj_common_from.null_tkfattacq = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tkfattacq = true;
            if (obj_common_from != null) obj_common_from.null_tkfattacq = true;
        }

        return 1;
    }




    /****
        setResultSet_key: atk_messaggio
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

        return 1;
    }




    /****
        trim(): atk_messaggio
    **/ 


    public void trim( ) throws Exception {

        this.host = this.host.trim(); 
        this.username = this.username.trim(); 
        this.password = this.password.trim(); 
        this.oggetto = this.oggetto.trim(); 
        this.mittente = this.mittente.trim(); 
        this.contenuto = this.contenuto.trim(); 
        this.dest1 = this.dest1.trim(); 
        this.dest2 = this.dest2.trim(); 
        this.dest3 = this.dest3.trim(); 
        this.dest4 = this.dest4.trim(); 
        this.bcc1 = this.bcc1.trim(); 
        this.bcc2 = this.bcc2.trim(); 
        this.bcc3 = this.bcc3.trim(); 
        this.bcc4 = this.bcc4.trim(); 
        this.nomefile = this.nomefile.trim(); 
        this.pathfile = this.pathfile.trim(); 
        this.nota = this.nota.trim(); 
        this.tpservizio = this.tpservizio.trim(); 
        this.cdazie = this.cdazie.trim(); 
        this.cddipa = this.cddipa.trim(); 
        this.profil = this.profil.trim(); 
        this.smtp_port = this.smtp_port.trim(); 

    }



    public void rtrim( ) throws Exception {

        this.host = of_rtrim(this.host); 
        this.username = of_rtrim(this.username); 
        this.password = of_rtrim(this.password); 
        this.oggetto = of_rtrim(this.oggetto); 
        this.mittente = of_rtrim(this.mittente); 
        this.contenuto = of_rtrim(this.contenuto); 
        this.dest1 = of_rtrim(this.dest1); 
        this.dest2 = of_rtrim(this.dest2); 
        this.dest3 = of_rtrim(this.dest3); 
        this.dest4 = of_rtrim(this.dest4); 
        this.bcc1 = of_rtrim(this.bcc1); 
        this.bcc2 = of_rtrim(this.bcc2); 
        this.bcc3 = of_rtrim(this.bcc3); 
        this.bcc4 = of_rtrim(this.bcc4); 
        this.nomefile = of_rtrim(this.nomefile); 
        this.pathfile = of_rtrim(this.pathfile); 
        this.nota = of_rtrim(this.nota); 
        this.tpservizio = of_rtrim(this.tpservizio); 
        this.cdazie = of_rtrim(this.cdazie); 
        this.cddipa = of_rtrim(this.cddipa); 
        this.profil = of_rtrim(this.profil); 
        this.smtp_port = of_rtrim(this.smtp_port); 

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
        ctrl_mindate(): atk_messaggio
    **/ 


    public void ctrl_mindate( ) throws Exception {

        int li_sa = 0; 

        li_sa = 0; 
        if (this.dtricsped != null){ 
            li_sa = this.dtricsped.getYear()+1900; 
            if (li_sa < 1900){ 
                this.dtricsped.setYear(0); 
                this.dtricsped.setMonth(0); 
                this.dtricsped.setDate(1); 
                this.dtricsped.setHours(0); 
                this.dtricsped.setMinutes(0); 
                this.dtricsped.setSeconds(0); 
                this.dtricsped.setNanos(0); 
            } 
            this.dtricsped.setNanos(0); 
            this.dtricsped.toString(); 
        } 
        li_sa = 0; 
        if (this.dtproc != null){ 
            li_sa = this.dtproc.getYear()+1900; 
            if (li_sa < 1900){ 
                this.dtproc.setYear(0); 
                this.dtproc.setMonth(0); 
                this.dtproc.setDate(1); 
                this.dtproc.setHours(0); 
                this.dtproc.setMinutes(0); 
                this.dtproc.setSeconds(0); 
                this.dtproc.setNanos(0); 
            } 
            this.dtproc.setNanos(0); 
            this.dtproc.toString(); 
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
        if (this.dtsped != null){ 
            li_sa = this.dtsped.getYear()+1900; 
            if (li_sa < 1900){ 
                this.dtsped.setYear(0); 
                this.dtsped.setMonth(0); 
                this.dtsped.setDate(1); 
                this.dtsped.setHours(0); 
                this.dtsped.setMinutes(0); 
                this.dtsped.setSeconds(0); 
                this.dtsped.setNanos(0); 
            } 
            this.dtsped.setNanos(0); 
            this.dtsped.toString(); 
        } 

    }




    /****
        requestValues(request):   
    **/ 

    public void requestValues(javax.servlet.http.HttpServletRequest request) throws Exception{ 

        if (request.getParameterValues("tkmsg")!=null) arr_tkmsg = request.getParameterValues("tkmsg");
        if (request.getParameterValues("host")!=null) arr_host = request.getParameterValues("host");
        if (request.getParameterValues("username")!=null) arr_username = request.getParameterValues("username");
        if (request.getParameterValues("password")!=null) arr_password = request.getParameterValues("password");
        if (request.getParameterValues("oggetto")!=null) arr_oggetto = request.getParameterValues("oggetto");
        if (request.getParameterValues("mittente")!=null) arr_mittente = request.getParameterValues("mittente");
        if (request.getParameterValues("contenuto")!=null) arr_contenuto = request.getParameterValues("contenuto");
        if (request.getParameterValues("dest1")!=null) arr_dest1 = request.getParameterValues("dest1");
        if (request.getParameterValues("dest2")!=null) arr_dest2 = request.getParameterValues("dest2");
        if (request.getParameterValues("dest3")!=null) arr_dest3 = request.getParameterValues("dest3");
        if (request.getParameterValues("dest4")!=null) arr_dest4 = request.getParameterValues("dest4");
        if (request.getParameterValues("bcc1")!=null) arr_bcc1 = request.getParameterValues("bcc1");
        if (request.getParameterValues("bcc2")!=null) arr_bcc2 = request.getParameterValues("bcc2");
        if (request.getParameterValues("bcc3")!=null) arr_bcc3 = request.getParameterValues("bcc3");
        if (request.getParameterValues("bcc4")!=null) arr_bcc4 = request.getParameterValues("bcc4");
        if (request.getParameterValues("nomefile")!=null) arr_nomefile = request.getParameterValues("nomefile");
        if (request.getParameterValues("pathfile")!=null) arr_pathfile = request.getParameterValues("pathfile");
        if (request.getParameterValues("dtricsped")!=null) arr_dtricsped = request.getParameterValues("dtricsped");
        if (request.getParameterValues("dtproc")!=null) arr_dtproc = request.getParameterValues("dtproc");
        if (request.getParameterValues("nota")!=null) arr_nota = request.getParameterValues("nota");
        if (request.getParameterValues("id_object")!=null) arr_id_object = request.getParameterValues("id_object");
        if (request.getParameterValues("tkboll")!=null) arr_tkboll = request.getParameterValues("tkboll");
        if (request.getParameterValues("tpservizio")!=null) arr_tpservizio = request.getParameterValues("tpservizio");
        if (request.getParameterValues("cdazie")!=null) arr_cdazie = request.getParameterValues("cdazie");
        if (request.getParameterValues("cddipa")!=null) arr_cddipa = request.getParameterValues("cddipa");
        if (request.getParameterValues("profil")!=null) arr_profil = request.getParameterValues("profil");
        if (request.getParameterValues("dtinse")!=null) arr_dtinse = request.getParameterValues("dtinse");
        if (request.getParameterValues("dtulag")!=null) arr_dtulag = request.getParameterValues("dtulag");
        if (request.getParameterValues("dtsped")!=null) arr_dtsped = request.getParameterValues("dtsped");
        if (request.getParameterValues("tkml")!=null) arr_tkml = request.getParameterValues("tkml");
        if (request.getParameterValues("tkordi")!=null) arr_tkordi = request.getParameterValues("tkordi");
        if (request.getParameterValues("tkordiacq")!=null) arr_tkordiacq = request.getParameterValues("tkordiacq");
        if (request.getParameterValues("tkfatt")!=null) arr_tkfatt = request.getParameterValues("tkfatt");
        if (request.getParameterValues("smtp_port")!=null) arr_smtp_port = request.getParameterValues("smtp_port");
        if (request.getParameterValues("tkbollacq")!=null) arr_tkbollacq = request.getParameterValues("tkbollacq");
        if (request.getParameterValues("tkfattacq")!=null) arr_tkfattacq = request.getParameterValues("tkfattacq");

        return ;
    }

    public String[] arr_tkmsg = null; 
    public String[] arr_host = null; 
    public String[] arr_username = null; 
    public String[] arr_password = null; 
    public String[] arr_oggetto = null; 
    public String[] arr_mittente = null; 
    public String[] arr_contenuto = null; 
    public String[] arr_dest1 = null; 
    public String[] arr_dest2 = null; 
    public String[] arr_dest3 = null; 
    public String[] arr_dest4 = null; 
    public String[] arr_bcc1 = null; 
    public String[] arr_bcc2 = null; 
    public String[] arr_bcc3 = null; 
    public String[] arr_bcc4 = null; 
    public String[] arr_nomefile = null; 
    public String[] arr_pathfile = null; 
    public String[] arr_dtricsped = null; 
    public String[] arr_dtproc = null; 
    public String[] arr_nota = null; 
    public String[] arr_id_object = null; 
    public String[] arr_tkboll = null; 
    public String[] arr_tpservizio = null; 
    public String[] arr_cdazie = null; 
    public String[] arr_cddipa = null; 
    public String[] arr_profil = null; 
    public String[] arr_dtinse = null; 
    public String[] arr_dtulag = null; 
    public String[] arr_dtsped = null; 
    public String[] arr_tkml = null; 
    public String[] arr_tkordi = null; 
    public String[] arr_tkordiacq = null; 
    public String[] arr_tkfatt = null; 
    public String[] arr_smtp_port = null; 
    public String[] arr_tkbollacq = null; 
    public String[] arr_tkfattacq = null; 





}

