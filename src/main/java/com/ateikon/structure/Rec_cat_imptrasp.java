package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_cat_imptrasp extends Object {

    public Rec_cat_imptrasp() {

        super();
    }


    public String seStesso = "Rec_cat_imptrasp";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public long       id = 0; 
    public String     cdnazi = ""; 
    public String     cdprov = ""; 
    public double     perc = 0; 
    public double     importo_min = 0; 
    public String     cdazie = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public Timestamp  dtulag = null; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtwebsync = null; 
    public double     importo_max = 0; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.id = 0; 
        this.cdnazi = ""; 
        this.cdprov = ""; 
        this.perc = 0; 
        this.importo_min = 0; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtulag = null; 
        this.dtinse = null; 
        this.dtwebsync = null; 
        this.importo_max = 0; 

    }






    /****
        setResultSet: cat_imptrasp
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("id")!=null) this.id = rs.getLong      ("id"); 
        if (rs.getObject("cdnazi")!=null) this.cdnazi = rs.getString    ("cdnazi"); 
        if (rs.getObject("cdprov")!=null) this.cdprov = rs.getString    ("cdprov"); 
        if (rs.getObject("perc")!=null) this.perc = rs.getDouble    ("perc"); 
        if (rs.getObject("importo_min")!=null) this.importo_min = rs.getDouble    ("importo_min"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtwebsync")!=null) this.dtwebsync = rs.getTimestamp ("dtwebsync"); 
        if (rs.getObject("importo_max")!=null) this.importo_max = rs.getDouble    ("importo_max"); 

        return 1;
    }




    /****
        setResultSet_key: cat_imptrasp
    **/ 


    public int setResultSet_key ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("id")!=null) this.id = rs.getLong      ("id"); 

        return 1;
    }




    /****
        trim(): cat_imptrasp
    **/ 


    public void trim( ) throws Exception {

        this.cdnazi = this.cdnazi.trim(); 
        this.cdprov = this.cdprov.trim(); 
        this.cdazie = this.cdazie.trim(); 
        this.cddipa = this.cddipa.trim(); 
        this.profil = this.profil.trim(); 

    }





}

