package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_cat_layout_arti extends Object {

    public Rec_cat_layout_arti() {

        super();
    }


    public String seStesso = "Rec_cat_layout_arti";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public long       id = 0; 
    public long       id_layout = 0; 
    public String     cdarti = ""; 
    public String     img = ""; 
    public long       nrposi = 0; 
    public String     cdazie = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public Timestamp  dtulag = null; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtwebsync = null; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.id = 0; 
        this.id_layout = 0; 
        this.cdarti = ""; 
        this.img = ""; 
        this.nrposi = 0; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtulag = null; 
        this.dtinse = null; 
        this.dtwebsync = null; 

    }






    /****
        setResultSet: cat_layout_arti
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("id")!=null) this.id = rs.getLong      ("id"); 
        if (rs.getObject("id_layout")!=null) this.id_layout = rs.getLong      ("id_layout"); 
        if (rs.getObject("cdarti")!=null) this.cdarti = rs.getString    ("cdarti"); 
        if (rs.getObject("img")!=null) this.img = rs.getString    ("img"); 
        if (rs.getObject("nrposi")!=null) this.nrposi = rs.getLong      ("nrposi"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtwebsync")!=null) this.dtwebsync = rs.getTimestamp ("dtwebsync"); 

        return 1;
    }




    /****
        setResultSet_key: cat_layout_arti
    **/ 


    public int setResultSet_key ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("id")!=null) this.id = rs.getLong      ("id"); 

        return 1;
    }




    /****
        trim(): cat_layout_arti
    **/ 


    public void trim( ) throws Exception {

        this.cdarti = this.cdarti.trim(); 
        this.img = this.img.trim(); 
        this.cdazie = this.cdazie.trim(); 
        this.cddipa = this.cddipa.trim(); 
        this.profil = this.profil.trim(); 

    }





}

