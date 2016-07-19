package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_cat_tipomapr_escl extends Object {

    public Rec_cat_tipomapr_escl() {

        super();
    }


    public String seStesso = "Rec_cat_tipomapr_escl";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public String     cdtipm = ""; 
    public String     cdazie = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.cdtipm = ""; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 

    }






    /****
        setResultSet: cat_tipomapr_escl
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("cdtipm")!=null) this.cdtipm = rs.getString    ("cdtipm"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 

        return 1;
    }




    /****
        setResultSet_key: cat_tipomapr_escl
    **/ 


    public int setResultSet_key ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("cdtipm")!=null) this.cdtipm = rs.getString    ("cdtipm"); 

        return 1;
    }




    /****
        trim(): cat_tipomapr_escl
    **/ 


    public void trim( ) throws Exception {

        this.cdtipm = this.cdtipm.trim(); 
        this.cdazie = this.cdazie.trim(); 
        this.cddipa = this.cddipa.trim(); 
        this.profil = this.profil.trim(); 

    }





}

