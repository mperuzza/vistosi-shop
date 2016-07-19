package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_condven extends Object {

    public Rec_condven() {

        super();
    }


    public String seStesso = "Rec_condven";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public long       tkcondven = 0; 
    public String     cdclac = ""; 
    public double     impmin = 0; 
    public double     qtamin = 0; 
    public String     cdazie = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 
    public String     fgnew = ""; 
    public String     tkclie = ""; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.tkcondven = 0; 
        this.cdclac = ""; 
        this.impmin = 0; 
        this.qtamin = 0; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 
        this.fgnew = ""; 
        this.tkclie = ""; 

    }






    /****
        setResultSet: condven
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkcondven")!=null) this.tkcondven = rs.getLong      ("tkcondven"); 
        if (rs.getObject("cdclac")!=null) this.cdclac = rs.getString    ("cdclac"); 
        if (rs.getObject("impmin")!=null) this.impmin = rs.getDouble    ("impmin"); 
        if (rs.getObject("qtamin")!=null) this.qtamin = rs.getDouble    ("qtamin"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("fgnew")!=null) this.fgnew = rs.getString    ("fgnew"); 
        if (rs.getObject("tkclie")!=null) this.tkclie = rs.getString    ("tkclie"); 

        return 1;
    }




    /****
        setResultSet_key: condven
    **/ 


    public int setResultSet_key ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkcondven")!=null) this.tkcondven = rs.getLong      ("tkcondven"); 

        return 1;
    }





}

