package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_atk_alert_spool extends Object {

    public Rec_atk_alert_spool() {

        super();
    }


    public String seStesso = "Rec_atk_alert_spool";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public long       id = 0; 
    public long       id_alert = 0; 
    public long       id_alert_yacht = 0; 
    public String     cdarti = ""; 
    public long       tksend_alert = 0; 
    public String     cdazie = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.id = 0; 
        this.id_alert = 0; 
        this.id_alert_yacht = 0; 
        this.cdarti = ""; 
        this.tksend_alert = 0; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 

    }






    /****
        setResultSet: atk_alert_spool
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("id")!=null) this.id = rs.getLong      ("id"); 
        if (rs.getObject("id_alert")!=null) this.id_alert = rs.getLong      ("id_alert"); 
        if (rs.getObject("id_alert_yacht")!=null) this.id_alert_yacht = rs.getLong      ("id_alert_yacht"); 
        if (rs.getObject("cdarti")!=null) this.cdarti = rs.getString    ("cdarti"); 
        if (rs.getObject("tksend_alert")!=null) this.tksend_alert = rs.getLong      ("tksend_alert"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 

        return 1;
    }





}

