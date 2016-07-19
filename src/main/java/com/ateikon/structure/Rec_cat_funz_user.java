package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_cat_funz_user extends Object {

    public Rec_cat_funz_user() {

        super();
    }


    public String seStesso = "Rec_cat_funz_user";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public long       tkfunzione = 0; 
    public long       tkutente = 0; 
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
        this.tkutente = 0; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.profil_inse = ""; 
        this.dtinse = null; 
        this.dtulag = null; 

    }






    /****
        setResultSet: cat_funz_user
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkfunzione")!=null) this.tkfunzione = rs.getLong      ("tkfunzione"); 
        if (rs.getObject("tkutente")!=null) this.tkutente = rs.getLong      ("tkutente"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("profil_inse")!=null) this.profil_inse = rs.getString    ("profil_inse"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 

        return 1;
    }




    /****
        setResultSet_key: cat_funz_user
    **/ 


    public int setResultSet_key ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkfunzione")!=null) this.tkfunzione = rs.getLong      ("tkfunzione"); 
        if (rs.getObject("tkutente")!=null) this.tkutente = rs.getLong      ("tkutente"); 

        return 1;
    }




    /****
        trim(): cat_funz_user
    **/ 


    public void trim( ) throws Exception {

        this.cdazie = this.cdazie.trim(); 
        this.cddipa = this.cddipa.trim(); 
        this.profil = this.profil.trim(); 
        this.profil_inse = this.profil_inse.trim(); 

    }





}

