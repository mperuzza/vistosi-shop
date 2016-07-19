package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_trasamez extends Object {

    public Rec_trasamez() {

        super();
    }


    public String seStesso = "Rec_trasamez";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public String     cdtrme = ""; 
    public String     cdazie = ""; 
    public String     cdtrme_m = ""; 
    public String     dstrme = ""; 
    public String     fmitte = ""; 
    public String     fdesti = ""; 
    public String     fvetto = ""; 
    public String     cdmtin = ""; 
    public String     dsingl = ""; 
    public String     dstede = ""; 
    public String     dsfran = ""; 
    public String     dsspag = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 
    public String     ftipve = ""; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.cdtrme = ""; 
        this.cdazie = ""; 
        this.cdtrme_m = ""; 
        this.dstrme = ""; 
        this.fmitte = ""; 
        this.fdesti = ""; 
        this.fvetto = ""; 
        this.cdmtin = ""; 
        this.dsingl = ""; 
        this.dstede = ""; 
        this.dsfran = ""; 
        this.dsspag = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 
        this.ftipve = ""; 

    }






    /****
        setResultSet: trasamez
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("cdtrme")!=null) this.cdtrme = rs.getString    ("cdtrme"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cdtrme_m")!=null) this.cdtrme_m = rs.getString    ("cdtrme_m"); 
        if (rs.getObject("dstrme")!=null) this.dstrme = rs.getString    ("dstrme"); 
        if (rs.getObject("fmitte")!=null) this.fmitte = rs.getString    ("fmitte"); 
        if (rs.getObject("fdesti")!=null) this.fdesti = rs.getString    ("fdesti"); 
        if (rs.getObject("fvetto")!=null) this.fvetto = rs.getString    ("fvetto"); 
        if (rs.getObject("cdmtin")!=null) this.cdmtin = rs.getString    ("cdmtin"); 
        if (rs.getObject("dsingl")!=null) this.dsingl = rs.getString    ("dsingl"); 
        if (rs.getObject("dstede")!=null) this.dstede = rs.getString    ("dstede"); 
        if (rs.getObject("dsfran")!=null) this.dsfran = rs.getString    ("dsfran"); 
        if (rs.getObject("dsspag")!=null) this.dsspag = rs.getString    ("dsspag"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("ftipve")!=null) this.ftipve = rs.getString    ("ftipve"); 

        return 1;
    }




    /****
        setResultSet_key: trasamez
    **/ 


    public int setResultSet_key ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("cdtrme")!=null) this.cdtrme = rs.getString    ("cdtrme"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 

        return 1;
    }




    /****
        trim(): trasamez
    **/ 


    public void trim( ) throws Exception {

        this.cdtrme = this.cdtrme.trim(); 
        this.cdazie = this.cdazie.trim(); 
        this.cdtrme_m = this.cdtrme_m.trim(); 
        this.dstrme = this.dstrme.trim(); 
        this.fmitte = this.fmitte.trim(); 
        this.fdesti = this.fdesti.trim(); 
        this.fvetto = this.fvetto.trim(); 
        this.cdmtin = this.cdmtin.trim(); 
        this.dsingl = this.dsingl.trim(); 
        this.dstede = this.dstede.trim(); 
        this.dsfran = this.dsfran.trim(); 
        this.dsspag = this.dsspag.trim(); 
        this.cddipa = this.cddipa.trim(); 
        this.profil = this.profil.trim(); 
        this.ftipve = this.ftipve.trim(); 

    }





}

