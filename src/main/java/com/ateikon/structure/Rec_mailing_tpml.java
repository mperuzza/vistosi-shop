package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_mailing_tpml extends Object {

    public Rec_mailing_tpml() {

        super();
    }


    public String seStesso = "Rec_mailing_tpml";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public String     cdtpml = ""; 
    public String     dstpml = ""; 
    public String     cdazie = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 
    public long       posi = 0; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.cdtpml = ""; 
        this.dstpml = ""; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 
        this.posi = 0; 

    }






    /****
        setResultSet: mailing_tpml
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("cdtpml")!=null) this.cdtpml = rs.getString    ("cdtpml"); 
        if (rs.getObject("dstpml")!=null) this.dstpml = rs.getString    ("dstpml"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("posi")!=null) this.posi = rs.getLong      ("posi"); 

        return 1;
    }





}

