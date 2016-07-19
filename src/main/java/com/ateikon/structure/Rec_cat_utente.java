package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_cat_utente extends Object {

    public Rec_cat_utente() {

        super();
    }


    public String seStesso = "Rec_cat_utente";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public long       tkutente = 0; 
    public String     user_name = ""; 
    public String     password = ""; 
    public String     dsutente = ""; 
    public String     tkclie = ""; 
    public String     tkforn = ""; 
    public String     cdente = ""; 
    public String     cdagen = ""; 
    public String     cdutente = ""; 
    public String     fgadmin = ""; 
    public String     cdazie = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 
    public String     email = ""; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.tkutente = 0; 
        this.user_name = ""; 
        this.password = ""; 
        this.dsutente = ""; 
        this.tkclie = ""; 
        this.tkforn = ""; 
        this.cdente = ""; 
        this.cdagen = ""; 
        this.cdutente = ""; 
        this.fgadmin = ""; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 
        this.email = ""; 

    }






    /****
        setResultSet: cat_utente
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkutente")!=null) this.tkutente = rs.getLong      ("tkutente"); 
        if (rs.getObject("user_name")!=null) this.user_name = rs.getString    ("user_name"); 
        if (rs.getObject("password")!=null) this.password = rs.getString    ("password"); 
        if (rs.getObject("dsutente")!=null) this.dsutente = rs.getString    ("dsutente"); 
        if (rs.getObject("tkclie")!=null) this.tkclie = rs.getString    ("tkclie"); 
        if (rs.getObject("tkforn")!=null) this.tkforn = rs.getString    ("tkforn"); 
        if (rs.getObject("cdente")!=null) this.cdente = rs.getString    ("cdente"); 
        if (rs.getObject("cdagen")!=null) this.cdagen = rs.getString    ("cdagen"); 
        if (rs.getObject("cdutente")!=null) this.cdutente = rs.getString    ("cdutente"); 
        if (rs.getObject("fgadmin")!=null) this.fgadmin = rs.getString    ("fgadmin"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("email")!=null) this.email = rs.getString    ("email"); 

        return 1;
    }




    /****
        setResultSet_key: cat_utente
    **/ 


    public int setResultSet_key ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkutente")!=null) this.tkutente = rs.getLong      ("tkutente"); 

        return 1;
    }




    /****
        trim(): cat_utente
    **/ 


    public void trim( ) throws Exception {

        this.user_name = this.user_name.trim(); 
        this.password = this.password.trim(); 
        this.dsutente = this.dsutente.trim(); 
        this.tkclie = this.tkclie.trim(); 
        this.tkforn = this.tkforn.trim(); 
        this.cdente = this.cdente.trim(); 
        this.cdagen = this.cdagen.trim(); 
        this.cdutente = this.cdutente.trim(); 
        this.fgadmin = this.fgadmin.trim(); 
        this.cdazie = this.cdazie.trim(); 
        this.cddipa = this.cddipa.trim(); 
        this.profil = this.profil.trim(); 
        this.email = this.email.trim(); 

    }





}

