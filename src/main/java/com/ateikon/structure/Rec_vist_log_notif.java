package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_vist_log_notif extends Object {

    public Rec_vist_log_notif() {

        super();
    }

    public Rec_vist_log_notif( com.ateikon.common.Vist_log_notif obj_common_from
                       , com.ateikon.common.Vist_log_notif obj_common_to   ) {

        super();

        if (obj_common_from !=null){
            this.obj_common_from = obj_common_from;
        }
        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public Rec_vist_log_notif( com.ateikon.common.Vist_log_notif obj_common_to   ) {

        super();

        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public com.ateikon.common.Vist_log_notif obj_common_from = null;
    public com.ateikon.common.Vist_log_notif obj_common_to   = null;


    public String seStesso = "Rec_vist_log_notif";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public long       tklog_notif = 0; 
    public Timestamp  dtlog_notif = null; 
    public String     tpnotif = ""; 
    public long       nrnotif = 0; 
    public long       anno = 0; 
    public long       mese = 0; 
    public long       giorno = 0; 
    public long       ora = 0; 
    public long       tkmsg = 0; 
    public long       tkutente_to = 0; 
    public String     cdazie = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.tklog_notif = 0; 
        this.dtlog_notif = null; 
        this.tpnotif = ""; 
        this.nrnotif = 0; 
        this.anno = 0; 
        this.mese = 0; 
        this.giorno = 0; 
        this.ora = 0; 
        this.tkmsg = 0; 
        this.tkutente_to = 0; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 

    }






    /****
        setResultSet: vist_log_notif
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tklog_notif")!=null){
            this.tklog_notif = rs.getLong      ("tklog_notif"); 
            if (obj_common_to   != null) obj_common_to.null_tklog_notif = false;
            if (obj_common_from != null) obj_common_from.null_tklog_notif = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tklog_notif = true;
            if (obj_common_from != null) obj_common_from.null_tklog_notif = true;
        }
        if (rs.getObject("dtlog_notif")!=null) this.dtlog_notif = rs.getTimestamp ("dtlog_notif"); 
        if (rs.getObject("tpnotif")!=null) this.tpnotif = rs.getString    ("tpnotif"); 
        if (rs.getObject("nrnotif")!=null){
            this.nrnotif = rs.getLong      ("nrnotif"); 
            if (obj_common_to   != null) obj_common_to.null_nrnotif = false;
            if (obj_common_from != null) obj_common_from.null_nrnotif = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_nrnotif = true;
            if (obj_common_from != null) obj_common_from.null_nrnotif = true;
        }
        if (rs.getObject("anno")!=null){
            this.anno = rs.getLong      ("anno"); 
            if (obj_common_to   != null) obj_common_to.null_anno = false;
            if (obj_common_from != null) obj_common_from.null_anno = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_anno = true;
            if (obj_common_from != null) obj_common_from.null_anno = true;
        }
        if (rs.getObject("mese")!=null){
            this.mese = rs.getLong      ("mese"); 
            if (obj_common_to   != null) obj_common_to.null_mese = false;
            if (obj_common_from != null) obj_common_from.null_mese = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_mese = true;
            if (obj_common_from != null) obj_common_from.null_mese = true;
        }
        if (rs.getObject("giorno")!=null){
            this.giorno = rs.getLong      ("giorno"); 
            if (obj_common_to   != null) obj_common_to.null_giorno = false;
            if (obj_common_from != null) obj_common_from.null_giorno = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_giorno = true;
            if (obj_common_from != null) obj_common_from.null_giorno = true;
        }
        if (rs.getObject("ora")!=null){
            this.ora = rs.getLong      ("ora"); 
            if (obj_common_to   != null) obj_common_to.null_ora = false;
            if (obj_common_from != null) obj_common_from.null_ora = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_ora = true;
            if (obj_common_from != null) obj_common_from.null_ora = true;
        }
        if (rs.getObject("tkmsg")!=null){
            this.tkmsg = rs.getLong      ("tkmsg"); 
            if (obj_common_to   != null) obj_common_to.null_tkmsg = false;
            if (obj_common_from != null) obj_common_from.null_tkmsg = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tkmsg = true;
            if (obj_common_from != null) obj_common_from.null_tkmsg = true;
        }
        if (rs.getObject("tkutente_to")!=null){
            this.tkutente_to = rs.getLong      ("tkutente_to"); 
            if (obj_common_to   != null) obj_common_to.null_tkutente_to = false;
            if (obj_common_from != null) obj_common_from.null_tkutente_to = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tkutente_to = true;
            if (obj_common_from != null) obj_common_from.null_tkutente_to = true;
        }
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 

        return 1;
    }




    /****
        setResultSet_key: vist_log_notif
    **/ 


    public int setResultSet_key ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tklog_notif")!=null){
            this.tklog_notif = rs.getLong      ("tklog_notif"); 
            if (obj_common_to   != null) obj_common_to.null_tklog_notif = false;
            if (obj_common_from != null) obj_common_from.null_tklog_notif = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tklog_notif = true;
            if (obj_common_from != null) obj_common_from.null_tklog_notif = true;
        }

        return 1;
    }




    /****
        trim(): vist_log_notif
    **/ 


    public void trim( ) throws Exception {

        this.tpnotif = this.tpnotif.trim(); 
        this.cdazie = this.cdazie.trim(); 
        this.cddipa = this.cddipa.trim(); 
        this.profil = this.profil.trim(); 

    }




    /****
        ctrl_mindate(): vist_log_notif
    **/ 


    public void ctrl_mindate( ) throws Exception {

        int li_sa = 0; 

        li_sa = 0; 
        if (this.dtlog_notif != null){ 
            li_sa = this.dtlog_notif.getYear()+1900; 
            if (li_sa < 1900){ 
                this.dtlog_notif.setYear(0); 
                this.dtlog_notif.setMonth(0); 
                this.dtlog_notif.setDate(1); 
                this.dtlog_notif.setHours(0); 
                this.dtlog_notif.setMinutes(0); 
                this.dtlog_notif.setSeconds(0); 
                this.dtlog_notif.setNanos(0); 
            } 
            this.dtlog_notif.setNanos(0); 
            this.dtlog_notif.toString(); 
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





}

