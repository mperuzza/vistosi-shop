package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_cat_funz extends Object {

    public Rec_cat_funz() {

        super();
    }


    public String seStesso = "Rec_cat_funz";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public long       tkfunzione = 0; 
    public String     cdfunzione_m = ""; 
    public String     dsfunzione = ""; 
    public String     fgall_users = ""; 
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

        this.tkfunzione = 0; 
        this.cdfunzione_m = ""; 
        this.dsfunzione = ""; 
        this.fgall_users = ""; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.profil_inse = ""; 
        this.dtinse = null; 
        this.dtulag = null; 

    }






    /****
        setResultSet: cat_funz
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkfunzione")!=null) this.tkfunzione = rs.getLong      ("tkfunzione"); 
        if (rs.getObject("cdfunzione_m")!=null) this.cdfunzione_m = rs.getString    ("cdfunzione_m"); 
        if (rs.getObject("dsfunzione")!=null) this.dsfunzione = rs.getString    ("dsfunzione"); 
        if (rs.getObject("fgall_users")!=null) this.fgall_users = rs.getString    ("fgall_users"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("profil_inse")!=null) this.profil_inse = rs.getString    ("profil_inse"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 

        return 1;
    }




    /****
        setResultSet_key: cat_funz
    **/ 


    public int setResultSet_key ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkfunzione")!=null) this.tkfunzione = rs.getLong      ("tkfunzione"); 

        return 1;
    }




    /****
        trim(): cat_funz
    **/ 


    public void trim( ) throws Exception {

        this.cdfunzione_m = this.cdfunzione_m.trim(); 
        this.dsfunzione = this.dsfunzione.trim(); 
        this.fgall_users = this.fgall_users.trim(); 
        this.cdazie = this.cdazie.trim(); 
        this.cddipa = this.cddipa.trim(); 
        this.profil = this.profil.trim(); 
        this.profil_inse = this.profil_inse.trim(); 

    }





}

