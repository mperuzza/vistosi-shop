package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_cat_menu extends Object {

    public Rec_cat_menu() {

        super();
    }


    public String seStesso = "Rec_cat_menu";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public long       tkmenu = 0; 
    public String     cdmenu_m = ""; 
    public String     dsmenu = ""; 
    public String     cdazie = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public String     profil_inse = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 
    public String     fgabil = ""; 
    public long       nrposi = 0; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.tkmenu = 0; 
        this.cdmenu_m = ""; 
        this.dsmenu = ""; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.profil_inse = ""; 
        this.dtinse = null; 
        this.dtulag = null; 
        this.fgabil = ""; 
        this.nrposi = 0; 

    }






    /****
        setResultSet: cat_menu
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkmenu")!=null) this.tkmenu = rs.getLong      ("tkmenu"); 
        if (rs.getObject("cdmenu_m")!=null) this.cdmenu_m = rs.getString    ("cdmenu_m"); 
        if (rs.getObject("dsmenu")!=null) this.dsmenu = rs.getString    ("dsmenu"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("profil_inse")!=null) this.profil_inse = rs.getString    ("profil_inse"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("fgabil")!=null) this.fgabil = rs.getString    ("fgabil"); 
        if (rs.getObject("nrposi")!=null) this.nrposi = rs.getLong      ("nrposi"); 

        return 1;
    }




    /****
        setResultSet_key: cat_menu
    **/ 


    public int setResultSet_key ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkmenu")!=null) this.tkmenu = rs.getLong      ("tkmenu"); 

        return 1;
    }




    /****
        trim(): cat_menu
    **/ 


    public void trim( ) throws Exception {

        this.cdmenu_m = this.cdmenu_m.trim(); 
        this.dsmenu = this.dsmenu.trim(); 
        this.cdazie = this.cdazie.trim(); 
        this.cddipa = this.cddipa.trim(); 
        this.profil = this.profil.trim(); 
        this.profil_inse = this.profil_inse.trim(); 
        this.fgabil = this.fgabil.trim(); 

    }





}

