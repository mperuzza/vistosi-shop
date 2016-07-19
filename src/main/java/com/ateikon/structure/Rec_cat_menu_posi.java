package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_cat_menu_posi extends Object {

    public Rec_cat_menu_posi() {

        super();
    }


    public String seStesso = "Rec_cat_menu_posi";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public long       tkmenu_posi = 0; 
    public long       tkmenu = 0; 
    public long       nrposi = 0; 
    public String     dsmenu_posi = ""; 
    public String     link_menu = ""; 
    public long       tkfunzione = 0; 
    public String     cdazie = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public String     profil_inse = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.tkmenu_posi = 0; 
        this.tkmenu = 0; 
        this.nrposi = 0; 
        this.dsmenu_posi = ""; 
        this.link_menu = ""; 
        this.tkfunzione = 0; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.profil_inse = ""; 
        this.dtinse = null; 
        this.dtulag = null; 

    }






    /****
        setResultSet: cat_menu_posi
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkmenu_posi")!=null) this.tkmenu_posi = rs.getLong      ("tkmenu_posi"); 
        if (rs.getObject("tkmenu")!=null) this.tkmenu = rs.getLong      ("tkmenu"); 
        if (rs.getObject("nrposi")!=null) this.nrposi = rs.getLong      ("nrposi"); 
        if (rs.getObject("dsmenu_posi")!=null) this.dsmenu_posi = rs.getString    ("dsmenu_posi"); 
        if (rs.getObject("link_menu")!=null) this.link_menu = rs.getString    ("link_menu"); 
        if (rs.getObject("tkfunzione")!=null) this.tkfunzione = rs.getLong      ("tkfunzione"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("profil_inse")!=null) this.profil_inse = rs.getString    ("profil_inse"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 

        return 1;
    }




    /****
        setResultSet_key: cat_menu_posi
    **/ 


    public int setResultSet_key ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkmenu_posi")!=null) this.tkmenu_posi = rs.getLong      ("tkmenu_posi"); 

        return 1;
    }




    /****
        trim(): cat_menu_posi
    **/ 


    public void trim( ) throws Exception {

        this.dsmenu_posi = this.dsmenu_posi.trim(); 
        this.link_menu = this.link_menu.trim(); 
        this.cdazie = this.cdazie.trim(); 
        this.cddipa = this.cddipa.trim(); 
        this.profil = this.profil.trim(); 
        this.profil_inse = this.profil_inse.trim(); 

    }





}

