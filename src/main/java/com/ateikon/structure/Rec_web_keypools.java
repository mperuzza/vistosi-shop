package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_web_keypools extends Object {

    public Rec_web_keypools() {

        super();
    }


    public String seStesso = "Rec_web_keypools";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public String     keyname = ""; 
    public String     cddipa = ""; 
    public String     cdazie = ""; 
    public long       init1 = 0; 
    public long       fine1 = 0; 
    public long       init2 = 0; 
    public long       fine2 = 0; 
    public String     profil = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.keyname = ""; 
        this.cddipa = ""; 
        this.cdazie = ""; 
        this.init1 = 0; 
        this.fine1 = 0; 
        this.init2 = 0; 
        this.fine2 = 0; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 

    }






    /****
        setResultSet: web_keypools
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("keyname")!=null) this.keyname = rs.getString    ("keyname"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("init1")!=null) this.init1 = rs.getLong      ("init1"); 
        if (rs.getObject("fine1")!=null) this.fine1 = rs.getLong      ("fine1"); 
        if (rs.getObject("init2")!=null) this.init2 = rs.getLong      ("init2"); 
        if (rs.getObject("fine2")!=null) this.fine2 = rs.getLong      ("fine2"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 

        return 1;
    }




    /****
        setResultSet_key: web_keypools
    **/ 


    public int setResultSet_key ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("keyname")!=null) this.keyname = rs.getString    ("keyname"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 

        return 1;
    }




    /****
        trim(): web_keypools
    **/ 


    public void trim( ) throws Exception {

        this.keyname = this.keyname.trim(); 
        this.cddipa = this.cddipa.trim(); 
        this.cdazie = this.cdazie.trim(); 
        this.profil = this.profil.trim(); 

    }





}

