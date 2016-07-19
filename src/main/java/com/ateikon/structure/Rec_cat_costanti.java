package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_cat_costanti extends Object {

    public Rec_cat_costanti() {

        super();
    }


    public String seStesso = "Rec_cat_costanti";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public long       tkcost = 0; 
    public String     cdazie = ""; 
    public String     cdappl = ""; 
    public String     profil = ""; 
    public String     costname = ""; 
    public String     costlabel = ""; 
    public String     costvalue = ""; 
    public String     cddipa = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 
    public String     cdagen = ""; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.tkcost = 0; 
        this.cdazie = ""; 
        this.cdappl = ""; 
        this.profil = ""; 
        this.costname = ""; 
        this.costlabel = ""; 
        this.costvalue = ""; 
        this.cddipa = ""; 
        this.dtinse = null; 
        this.dtulag = null; 
        this.cdagen = ""; 

    }






    /****
        setResultSet: cat_costanti
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkcost")!=null) this.tkcost = rs.getLong      ("tkcost"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cdappl")!=null) this.cdappl = rs.getString    ("cdappl"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("costname")!=null) this.costname = rs.getString    ("costname"); 
        if (rs.getObject("costlabel")!=null) this.costlabel = rs.getString    ("costlabel"); 
        if (rs.getObject("costvalue")!=null) this.costvalue = rs.getString    ("costvalue"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("cdagen")!=null) this.cdagen = rs.getString    ("cdagen"); 

        return 1;
    }




    /****
        setResultSet_key: cat_costanti
    **/ 


    public int setResultSet_key ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkcost")!=null) this.tkcost = rs.getLong      ("tkcost"); 

        return 1;
    }




    /****
        trim(): cat_costanti
    **/ 


    public void trim( ) throws Exception {

        this.cdazie = this.cdazie.trim(); 
        this.cdappl = this.cdappl.trim(); 
        this.profil = this.profil.trim(); 
        this.costname = this.costname.trim(); 
        this.costlabel = this.costlabel.trim(); 
        this.costvalue = this.costvalue.trim(); 
        this.cddipa = this.cddipa.trim(); 
        this.cdagen = this.cdagen.trim(); 

    }





}

