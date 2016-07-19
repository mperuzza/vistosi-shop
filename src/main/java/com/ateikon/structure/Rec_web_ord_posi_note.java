package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_web_ord_posi_note extends Object {

    public Rec_web_ord_posi_note() {

        super();
    }


    public String seStesso = "Rec_web_ord_posi_note";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public long       tkposi = 0; 
    public String     tiponota = ""; 
    public long       riga = 0; 
    public String     testo = ""; 
    public long       tkordi = 0; 
    public String     cdazie = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.tkposi = 0; 
        this.tiponota = ""; 
        this.riga = 0; 
        this.testo = ""; 
        this.tkordi = 0; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 

    }






    /****
        setResultSet: web_ord_posi_note
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkposi")!=null) this.tkposi = rs.getLong      ("tkposi"); 
        if (rs.getObject("tiponota")!=null) this.tiponota = rs.getString    ("tiponota"); 
        if (rs.getObject("riga")!=null) this.riga = rs.getLong      ("riga"); 
        if (rs.getObject("testo")!=null) this.testo = rs.getString    ("testo"); 
        if (rs.getObject("tkordi")!=null) this.tkordi = rs.getLong      ("tkordi"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 

        return 1;
    }




    /****
        setResultSet_key: web_ord_posi_note
    **/ 


    public int setResultSet_key ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkposi")!=null) this.tkposi = rs.getLong      ("tkposi"); 
        if (rs.getObject("tiponota")!=null) this.tiponota = rs.getString    ("tiponota"); 
        if (rs.getObject("riga")!=null) this.riga = rs.getLong      ("riga"); 

        return 1;
    }




    /****
        trim(): web_ord_posi_note
    **/ 


    public void trim( ) throws Exception {

        this.tiponota = this.tiponota.trim(); 
        this.testo = this.testo.trim(); 
        this.cdazie = this.cdazie.trim(); 
        this.cddipa = this.cddipa.trim(); 
        this.profil = this.profil.trim(); 

    }





}

