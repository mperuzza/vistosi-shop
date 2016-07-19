package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_atk_alert extends Object {

    public Rec_atk_alert() {

        super();
    }


    public String seStesso = "Rec_atk_alert";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public long       id = 0; 
    public String     cdalert_m = ""; 
    public String     descr = ""; 
    public String     cdutente = ""; 
    public Timestamp  dtfiltro = null; 
    public Timestamp  dtalert = null; 
    public Timestamp  dtini = null; 
    public Timestamp  dtfine = null; 
    public long       id_class = 0; 
    public long       id_sched = 0; 
    public Timestamp  dtsched_i = null; 
    public Timestamp  dtsched_f = null; 
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
        this.cdalert_m = ""; 
        this.descr = ""; 
        this.cdutente = ""; 
        this.dtfiltro = null; 
        this.dtalert = null; 
        this.dtini = null; 
        this.dtfine = null; 
        this.id_class = 0; 
        this.id_sched = 0; 
        this.dtsched_i = null; 
        this.dtsched_f = null; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 

    }






    /****
        setResultSet: atk_alert
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("id")!=null) this.id = rs.getLong      ("id"); 
        if (rs.getObject("cdalert_m")!=null) this.cdalert_m = rs.getString    ("cdalert_m"); 
        if (rs.getObject("descr")!=null) this.descr = rs.getString    ("descr"); 
        if (rs.getObject("cdutente")!=null) this.cdutente = rs.getString    ("cdutente"); 
        if (rs.getObject("dtfiltro")!=null) this.dtfiltro = rs.getTimestamp ("dtfiltro"); 
        if (rs.getObject("dtalert")!=null) this.dtalert = rs.getTimestamp ("dtalert"); 
        if (rs.getObject("dtini")!=null) this.dtini = rs.getTimestamp ("dtini"); 
        if (rs.getObject("dtfine")!=null) this.dtfine = rs.getTimestamp ("dtfine"); 
        if (rs.getObject("id_class")!=null) this.id_class = rs.getLong      ("id_class"); 
        if (rs.getObject("id_sched")!=null) this.id_sched = rs.getLong      ("id_sched"); 
        if (rs.getObject("dtsched_i")!=null) this.dtsched_i = rs.getTimestamp ("dtsched_i"); 
        if (rs.getObject("dtsched_f")!=null) this.dtsched_f = rs.getTimestamp ("dtsched_f"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 

        return 1;
    }





}

