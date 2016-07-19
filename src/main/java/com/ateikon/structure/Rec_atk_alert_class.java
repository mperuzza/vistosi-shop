package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_atk_alert_class extends Object {

    public Rec_atk_alert_class() {

        super();
    }


    public String seStesso = "Rec_atk_alert_class";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public long       id = 0; 
    public String     classname = ""; 
    public String     cdazie = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 
    public String     cdclass_m = ""; 
    public String     fgabil = ""; 
    public String     path_mail = ""; 
    public String     oggetto = ""; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.id = 0; 
        this.classname = ""; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 
        this.cdclass_m = ""; 
        this.fgabil = ""; 
        this.path_mail = ""; 
        this.oggetto = ""; 

    }






    /****
        setResultSet: atk_alert_class
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("id")!=null) this.id = rs.getLong      ("id"); 
        if (rs.getObject("classname")!=null) this.classname = rs.getString    ("classname"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("cdclass_m")!=null) this.cdclass_m = rs.getString    ("cdclass_m"); 
        if (rs.getObject("fgabil")!=null) this.fgabil = rs.getString    ("fgabil"); 
        if (rs.getObject("path_mail")!=null) this.path_mail = rs.getString    ("path_mail"); 
        if (rs.getObject("oggetto")!=null) this.oggetto = rs.getString    ("oggetto"); 

        return 1;
    }





}

