package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_web_ord_posi_matr extends Object {

    public Rec_web_ord_posi_matr() {

        super();
    }


    public String seStesso = "Rec_web_ord_posi_matr";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public long       tkposi_matr = 0; 
    public long       tkposi = 0; 
    public long       tkordi = 0; 
    public long       tkmatricola = 0; 
    public long       tkmov_matr = 0; 
    public double     qtatot = 0; 
    public double     impuni = 0; 
    public double     imptot = 0; 
    public double     qtacons = 0; 
    public double     qtacons_s = 0; 
    public long       tkmaga = 0; 
    public String     cdazie = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.tkposi_matr = 0; 
        this.tkposi = 0; 
        this.tkordi = 0; 
        this.tkmatricola = 0; 
        this.tkmov_matr = 0; 
        this.qtatot = 0; 
        this.impuni = 0; 
        this.imptot = 0; 
        this.qtacons = 0; 
        this.qtacons_s = 0; 
        this.tkmaga = 0; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 

    }






    /****
        setResultSet: web_ord_posi_matr
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkposi_matr")!=null) this.tkposi_matr = rs.getLong      ("tkposi_matr"); 
        if (rs.getObject("tkposi")!=null) this.tkposi = rs.getLong      ("tkposi"); 
        if (rs.getObject("tkordi")!=null) this.tkordi = rs.getLong      ("tkordi"); 
        if (rs.getObject("tkmatricola")!=null) this.tkmatricola = rs.getLong      ("tkmatricola"); 
        if (rs.getObject("tkmov_matr")!=null) this.tkmov_matr = rs.getLong      ("tkmov_matr"); 
        if (rs.getObject("qtatot")!=null) this.qtatot = rs.getDouble    ("qtatot"); 
        if (rs.getObject("impuni")!=null) this.impuni = rs.getDouble    ("impuni"); 
        if (rs.getObject("imptot")!=null) this.imptot = rs.getDouble    ("imptot"); 
        if (rs.getObject("qtacons")!=null) this.qtacons = rs.getDouble    ("qtacons"); 
        if (rs.getObject("qtacons_s")!=null) this.qtacons_s = rs.getDouble    ("qtacons_s"); 
        if (rs.getObject("tkmaga")!=null) this.tkmaga = rs.getLong      ("tkmaga"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 

        return 1;
    }




    /****
        setResultSet_key: web_ord_posi_matr
    **/ 


    public int setResultSet_key ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkposi_matr")!=null) this.tkposi_matr = rs.getLong      ("tkposi_matr"); 

        return 1;
    }




    /****
        trim(): web_ord_posi_matr
    **/ 


    public void trim( ) throws Exception {

        this.cdazie = this.cdazie.trim(); 
        this.cddipa = this.cddipa.trim(); 
        this.profil = this.profil.trim(); 

    }





}

