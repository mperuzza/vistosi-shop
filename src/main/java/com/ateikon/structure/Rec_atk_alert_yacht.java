package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_atk_alert_yacht extends Object {

    public Rec_atk_alert_yacht() {

        super();
    }


    public String seStesso = "Rec_atk_alert_yacht";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public long       id = 0; 
    public long       id_alert = 0; 
    public String     fg_tipo = ""; 
    public long       tkcantiere = 0; 
    public long       tkmodello = 0; 
    public double     prezzo_da = 0; 
    public double     prezzo_a = 0; 
    public long       anno_costruzione_barca = 0; 
    public String     cdazie = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.id = 0; 
        this.id_alert = 0; 
        this.fg_tipo = ""; 
        this.tkcantiere = 0; 
        this.tkmodello = 0; 
        this.prezzo_da = 0; 
        this.prezzo_a = 0; 
        this.anno_costruzione_barca = 0; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 

    }






    /****
        setResultSet: atk_alert_yacht
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("id")!=null) this.id = rs.getLong      ("id"); 
        if (rs.getObject("id_alert")!=null) this.id_alert = rs.getLong      ("id_alert"); 
        if (rs.getObject("fg_tipo")!=null) this.fg_tipo = rs.getString    ("fg_tipo"); 
        if (rs.getObject("tkcantiere")!=null) this.tkcantiere = rs.getLong      ("tkcantiere"); 
        if (rs.getObject("tkmodello")!=null) this.tkmodello = rs.getLong      ("tkmodello"); 
        if (rs.getObject("prezzo_da")!=null) this.prezzo_da = rs.getDouble    ("prezzo_da"); 
        if (rs.getObject("prezzo_a")!=null) this.prezzo_a = rs.getDouble    ("prezzo_a"); 
        if (rs.getObject("anno_costruzione_barca")!=null) this.anno_costruzione_barca = rs.getLong      ("anno_costruzione_barca"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 

        return 1;
    }





}

