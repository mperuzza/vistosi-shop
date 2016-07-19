package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_accessi extends Object {

    public Rec_accessi() {

        super();
    }


    public String seStesso = "Rec_accessi";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public Timestamp  dtaccesso = null; 
    public String     cdutente = ""; 
    public String     cdagen = ""; 
    public String     cdcapo = ""; 
    public String     tkclie = ""; 
    public String     foto = ""; 
    public String     sezione = ""; 
    public String     panorama = ""; 
    public String     url = ""; 
    public String     cdazie = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 
    public String     cdarti = ""; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.dtaccesso = null; 
        this.cdutente = ""; 
        this.cdagen = ""; 
        this.cdcapo = ""; 
        this.tkclie = ""; 
        this.foto = ""; 
        this.sezione = ""; 
        this.panorama = ""; 
        this.url = ""; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 
        this.cdarti = ""; 

    }






    /****
        setResultSet: accessi
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("dtaccesso")!=null) this.dtaccesso = rs.getTimestamp ("dtaccesso"); 
        if (rs.getObject("cdutente")!=null) this.cdutente = rs.getString    ("cdutente"); 
        if (rs.getObject("cdagen")!=null) this.cdagen = rs.getString    ("cdagen"); 
        if (rs.getObject("cdcapo")!=null) this.cdcapo = rs.getString    ("cdcapo"); 
        if (rs.getObject("tkclie")!=null) this.tkclie = rs.getString    ("tkclie"); 
        if (rs.getObject("foto")!=null) this.foto = rs.getString    ("foto"); 
        if (rs.getObject("sezione")!=null) this.sezione = rs.getString    ("sezione"); 
        if (rs.getObject("panorama")!=null) this.panorama = rs.getString    ("panorama"); 
        if (rs.getObject("url")!=null) this.url = rs.getString    ("url"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("cdarti")!=null) this.cdarti = rs.getString    ("cdarti"); 

        return 1;
    }





}

