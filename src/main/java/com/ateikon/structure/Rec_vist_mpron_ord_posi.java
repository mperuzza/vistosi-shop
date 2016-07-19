package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_vist_mpron_ord_posi extends Object {

    public Rec_vist_mpron_ord_posi() {

        super();
    }

    public Rec_vist_mpron_ord_posi( com.ateikon.common.Vist_mpron_ord_posi obj_common_from
                       , com.ateikon.common.Vist_mpron_ord_posi obj_common_to   ) {

        super();

        if (obj_common_from !=null){
            this.obj_common_from = obj_common_from;
        }
        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public Rec_vist_mpron_ord_posi( com.ateikon.common.Vist_mpron_ord_posi obj_common_to   ) {

        super();

        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public com.ateikon.common.Vist_mpron_ord_posi obj_common_from = null;
    public com.ateikon.common.Vist_mpron_ord_posi obj_common_to   = null;


    public String seStesso = "Rec_vist_mpron_ord_posi";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public long       tkmpronordp = 0; 
    public long       tkmpron = 0; 
    public long       tkposi = 0; 
    public double     importo = 0; 
    public String     cdazie = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 
    public double     importo_c = 0; 
    public double     nrpeso_l = 0; 
    public double     vlunlt = 0; 
    public double     qtatot = 0; 
    public double     impuni = 0; 
    public double     impuninetto = 0; 
    public Timestamp  dtdel = null; 
    public String     fgmerce_pronta = ""; 
    public Timestamp  dtcons = null; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.tkmpronordp = 0; 
        this.tkmpron = 0; 
        this.tkposi = 0; 
        this.importo = 0; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 
        this.importo_c = 0; 
        this.nrpeso_l = 0; 
        this.vlunlt = 0; 
        this.qtatot = 0; 
        this.impuni = 0; 
        this.impuninetto = 0; 
        this.dtdel = null; 
        this.fgmerce_pronta = ""; 
        this.dtcons = null; 

    }




    /****
        copy_from()
    **/ 

    public void copy_from(Rec_vist_mpron_ord_posi astr) throws Exception{ 

        this.tkmpronordp = astr.tkmpronordp;
        this.tkmpron = astr.tkmpron;
        this.tkposi = astr.tkposi;
        this.importo = astr.importo;
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
        this.importo_c = astr.importo_c;
        this.nrpeso_l = astr.nrpeso_l;
        this.vlunlt = astr.vlunlt;
        this.qtatot = astr.qtatot;
        this.impuni = astr.impuni;
        this.impuninetto = astr.impuninetto;
        if (astr.dtdel == null){
           this.dtdel = null;
        }else {
           this.dtdel = (Timestamp ) astr.dtdel.clone();
        }
        this.fgmerce_pronta = astr.fgmerce_pronta;
        if (astr.dtcons == null){
           this.dtcons = null;
        }else {
           this.dtcons = (Timestamp ) astr.dtcons.clone();
        }

    }





    /****
        setResultSet: vist_mpron_ord_posi
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkmpronordp")!=null){
            this.tkmpronordp = rs.getLong      ("tkmpronordp"); 
            if (obj_common_to   != null) obj_common_to.null_tkmpronordp = false;
            if (obj_common_from != null) obj_common_from.null_tkmpronordp = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tkmpronordp = true;
            if (obj_common_from != null) obj_common_from.null_tkmpronordp = true;
        }
        if (rs.getObject("tkmpron")!=null){
            this.tkmpron = rs.getLong      ("tkmpron"); 
            if (obj_common_to   != null) obj_common_to.null_tkmpron = false;
            if (obj_common_from != null) obj_common_from.null_tkmpron = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tkmpron = true;
            if (obj_common_from != null) obj_common_from.null_tkmpron = true;
        }
        if (rs.getObject("tkposi")!=null){
            this.tkposi = rs.getLong      ("tkposi"); 
            if (obj_common_to   != null) obj_common_to.null_tkposi = false;
            if (obj_common_from != null) obj_common_from.null_tkposi = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tkposi = true;
            if (obj_common_from != null) obj_common_from.null_tkposi = true;
        }
        if (rs.getObject("importo")!=null){
            this.importo = rs.getDouble    ("importo"); 
            if (obj_common_to   != null) obj_common_to.null_importo = false;
            if (obj_common_from != null) obj_common_from.null_importo = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_importo = true;
            if (obj_common_from != null) obj_common_from.null_importo = true;
        }
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("importo_c")!=null){
            this.importo_c = rs.getDouble    ("importo_c"); 
            if (obj_common_to   != null) obj_common_to.null_importo_c = false;
            if (obj_common_from != null) obj_common_from.null_importo_c = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_importo_c = true;
            if (obj_common_from != null) obj_common_from.null_importo_c = true;
        }
        if (rs.getObject("nrpeso_l")!=null){
            this.nrpeso_l = rs.getDouble    ("nrpeso_l"); 
            if (obj_common_to   != null) obj_common_to.null_nrpeso_l = false;
            if (obj_common_from != null) obj_common_from.null_nrpeso_l = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_nrpeso_l = true;
            if (obj_common_from != null) obj_common_from.null_nrpeso_l = true;
        }
        if (rs.getObject("vlunlt")!=null){
            this.vlunlt = rs.getDouble    ("vlunlt"); 
            if (obj_common_to   != null) obj_common_to.null_vlunlt = false;
            if (obj_common_from != null) obj_common_from.null_vlunlt = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_vlunlt = true;
            if (obj_common_from != null) obj_common_from.null_vlunlt = true;
        }
        if (rs.getObject("qtatot")!=null){
            this.qtatot = rs.getDouble    ("qtatot"); 
            if (obj_common_to   != null) obj_common_to.null_qtatot = false;
            if (obj_common_from != null) obj_common_from.null_qtatot = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_qtatot = true;
            if (obj_common_from != null) obj_common_from.null_qtatot = true;
        }
        if (rs.getObject("impuni")!=null){
            this.impuni = rs.getDouble    ("impuni"); 
            if (obj_common_to   != null) obj_common_to.null_impuni = false;
            if (obj_common_from != null) obj_common_from.null_impuni = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_impuni = true;
            if (obj_common_from != null) obj_common_from.null_impuni = true;
        }
        if (rs.getObject("impuninetto")!=null){
            this.impuninetto = rs.getDouble    ("impuninetto"); 
            if (obj_common_to   != null) obj_common_to.null_impuninetto = false;
            if (obj_common_from != null) obj_common_from.null_impuninetto = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_impuninetto = true;
            if (obj_common_from != null) obj_common_from.null_impuninetto = true;
        }
        if (rs.getObject("dtdel")!=null) this.dtdel = rs.getTimestamp ("dtdel"); 
        if (rs.getObject("fgmerce_pronta")!=null) this.fgmerce_pronta = rs.getString    ("fgmerce_pronta"); 
        if (rs.getObject("dtcons")!=null) this.dtcons = rs.getTimestamp ("dtcons"); 

        return 1;
    }




    /****
        setResultSet_key: vist_mpron_ord_posi
    **/ 


    public int setResultSet_key ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkmpronordp")!=null){
            this.tkmpronordp = rs.getLong      ("tkmpronordp"); 
            if (obj_common_to   != null) obj_common_to.null_tkmpronordp = false;
            if (obj_common_from != null) obj_common_from.null_tkmpronordp = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tkmpronordp = true;
            if (obj_common_from != null) obj_common_from.null_tkmpronordp = true;
        }

        return 1;
    }




    /****
        trim(): vist_mpron_ord_posi
    **/ 


    public void trim( ) throws Exception {

        this.cdazie = this.cdazie.trim(); 
        this.cddipa = this.cddipa.trim(); 
        this.profil = this.profil.trim(); 
        this.fgmerce_pronta = this.fgmerce_pronta.trim(); 

    }



    public void rtrim( ) throws Exception {

        this.cdazie = of_rtrim(this.cdazie); 
        this.cddipa = of_rtrim(this.cddipa); 
        this.profil = of_rtrim(this.profil); 
        this.fgmerce_pronta = of_rtrim(this.fgmerce_pronta); 

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
        ctrl_mindate(): vist_mpron_ord_posi
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
        li_sa = 0; 
        if (this.dtcons != null){ 
            li_sa = this.dtcons.getYear()+1900; 
            if (li_sa < 1900){ 
                this.dtcons.setYear(0); 
                this.dtcons.setMonth(0); 
                this.dtcons.setDate(1); 
                this.dtcons.setHours(0); 
                this.dtcons.setMinutes(0); 
                this.dtcons.setSeconds(0); 
                this.dtcons.setNanos(0); 
            } 
            this.dtcons.setNanos(0); 
            this.dtcons.toString(); 
        } 

    }




    /****
        requestValues(request):   
    **/ 

    public void requestValues(javax.servlet.http.HttpServletRequest request) throws Exception{ 

        if (request.getParameterValues("tkmpronordp")!=null) arr_tkmpronordp = request.getParameterValues("tkmpronordp");
        if (request.getParameterValues("tkmpron")!=null) arr_tkmpron = request.getParameterValues("tkmpron");
        if (request.getParameterValues("tkposi")!=null) arr_tkposi = request.getParameterValues("tkposi");
        if (request.getParameterValues("importo")!=null) arr_importo = request.getParameterValues("importo");
        if (request.getParameterValues("cdazie")!=null) arr_cdazie = request.getParameterValues("cdazie");
        if (request.getParameterValues("cddipa")!=null) arr_cddipa = request.getParameterValues("cddipa");
        if (request.getParameterValues("profil")!=null) arr_profil = request.getParameterValues("profil");
        if (request.getParameterValues("dtinse")!=null) arr_dtinse = request.getParameterValues("dtinse");
        if (request.getParameterValues("dtulag")!=null) arr_dtulag = request.getParameterValues("dtulag");
        if (request.getParameterValues("importo_c")!=null) arr_importo_c = request.getParameterValues("importo_c");
        if (request.getParameterValues("nrpeso_l")!=null) arr_nrpeso_l = request.getParameterValues("nrpeso_l");
        if (request.getParameterValues("vlunlt")!=null) arr_vlunlt = request.getParameterValues("vlunlt");
        if (request.getParameterValues("qtatot")!=null) arr_qtatot = request.getParameterValues("qtatot");
        if (request.getParameterValues("impuni")!=null) arr_impuni = request.getParameterValues("impuni");
        if (request.getParameterValues("impuninetto")!=null) arr_impuninetto = request.getParameterValues("impuninetto");
        if (request.getParameterValues("dtdel")!=null) arr_dtdel = request.getParameterValues("dtdel");
        if (request.getParameterValues("fgmerce_pronta")!=null) arr_fgmerce_pronta = request.getParameterValues("fgmerce_pronta");
        if (request.getParameterValues("dtcons")!=null) arr_dtcons = request.getParameterValues("dtcons");

        return ;
    }

    public String[] arr_tkmpronordp = null; 
    public String[] arr_tkmpron = null; 
    public String[] arr_tkposi = null; 
    public String[] arr_importo = null; 
    public String[] arr_cdazie = null; 
    public String[] arr_cddipa = null; 
    public String[] arr_profil = null; 
    public String[] arr_dtinse = null; 
    public String[] arr_dtulag = null; 
    public String[] arr_importo_c = null; 
    public String[] arr_nrpeso_l = null; 
    public String[] arr_vlunlt = null; 
    public String[] arr_qtatot = null; 
    public String[] arr_impuni = null; 
    public String[] arr_impuninetto = null; 
    public String[] arr_dtdel = null; 
    public String[] arr_fgmerce_pronta = null; 
    public String[] arr_dtcons = null; 





}

