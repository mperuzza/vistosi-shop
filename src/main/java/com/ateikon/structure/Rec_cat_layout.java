package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_cat_layout extends Object {

    public Rec_cat_layout() {

        super();
    }


    public String seStesso = "Rec_cat_layout";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public long       id = 0; 
    public String     cdlayout_m = ""; 
    public String     descr = ""; 
    public String     cdazie = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public Timestamp  dtulag = null; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtwebsync = null; 
    public long       nrposi = 0; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.id = 0; 
        this.cdlayout_m = ""; 
        this.descr = ""; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtulag = null; 
        this.dtinse = null; 
        this.dtwebsync = null; 
        this.nrposi = 0; 

    }






    /****
        setResultSet: cat_layout
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("id")!=null) this.id = rs.getLong      ("id"); 
        if (rs.getObject("cdlayout_m")!=null) this.cdlayout_m = rs.getString    ("cdlayout_m"); 
        if (rs.getObject("descr")!=null) this.descr = rs.getString    ("descr"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtwebsync")!=null) this.dtwebsync = rs.getTimestamp ("dtwebsync"); 
        if (rs.getObject("nrposi")!=null) this.nrposi = rs.getLong      ("nrposi"); 

        return 1;
    }




    /****
        setResultSet_key: cat_layout
    **/ 


    public int setResultSet_key ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("id")!=null) this.id = rs.getLong      ("id"); 

        return 1;
    }




    /****
        trim(): cat_layout
    **/ 


    public void trim( ) throws Exception {

        this.cdlayout_m = this.cdlayout_m.trim(); 
        this.descr = this.descr.trim(); 
        this.cdazie = this.cdazie.trim(); 
        this.cddipa = this.cddipa.trim(); 
        this.profil = this.profil.trim(); 

    }





}

