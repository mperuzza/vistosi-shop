package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_web_tabset extends Object {

    public Rec_web_tabset() {

        super();
    }


    public String seStesso = "Rec_web_tabset";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public String     tabella = ""; 
    public String     cdprogr = ""; 
    public String     cdazie = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.tabella = ""; 
        this.cdprogr = ""; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 

    }






    /****
        setResultSet: web_tabset
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tabella")!=null) this.tabella = rs.getString    ("tabella"); 
        if (rs.getObject("cdprogr")!=null) this.cdprogr = rs.getString    ("cdprogr"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 

        return 1;
    }




    /****
        setResultSet_key: web_tabset
    **/ 


    public int setResultSet_key ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tabella")!=null) this.tabella = rs.getString    ("tabella"); 

        return 1;
    }




    /****
        trim(): web_tabset
    **/ 


    public void trim( ) throws Exception {

        this.tabella = this.tabella.trim(); 
        this.cdprogr = this.cdprogr.trim(); 
        this.cdazie = this.cdazie.trim(); 
        this.cddipa = this.cddipa.trim(); 
        this.profil = this.profil.trim(); 

    }





}

