package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_carrello_iva extends Object {

    public Rec_carrello_iva() {

        super();
    }


    public String seStesso = "Rec_carrello_iva";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public long       id = 0; 
    public long       tkcarrello = 0; 
    public String     cdiva = ""; 
    public double     aliquo = 0; 
    public double     imponibile = 0; 
    public double     iva = 0; 
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
        this.tkcarrello = 0; 
        this.cdiva = ""; 
        this.aliquo = 0; 
        this.imponibile = 0; 
        this.iva = 0; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 

    }






    /****
        setResultSet: carrello_iva
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("id")!=null) this.id = rs.getLong      ("id"); 
        if (rs.getObject("tkcarrello")!=null) this.tkcarrello = rs.getLong      ("tkcarrello"); 
        if (rs.getObject("cdiva")!=null) this.cdiva = rs.getString    ("cdiva"); 
        if (rs.getObject("aliquo")!=null) this.aliquo = rs.getDouble    ("aliquo"); 
        if (rs.getObject("imponibile")!=null) this.imponibile = rs.getDouble    ("imponibile"); 
        if (rs.getObject("iva")!=null) this.iva = rs.getDouble    ("iva"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 

        return 1;
    }





}

