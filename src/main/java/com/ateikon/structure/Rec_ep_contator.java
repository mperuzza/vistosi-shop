package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_ep_contator extends Object {

    public Rec_ep_contator() {

        super();
    }


    public String seStesso = "Rec_ep_contator";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public String     cdazie = ""; 
    public String     prname = ""; 
    public String     annoco = ""; 
    public long       nprogr = 0; 
    public String     profil = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.cdazie = ""; 
        this.prname = ""; 
        this.annoco = ""; 
        this.nprogr = 0; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 

    }






    /****
        setResultSet: ep_contator
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("prname")!=null) this.prname = rs.getString    ("prname"); 
        if (rs.getObject("annoco")!=null) this.annoco = rs.getString    ("annoco"); 
        if (rs.getObject("nprogr")!=null) this.nprogr = rs.getLong      ("nprogr"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 

        return 1;
    }




    /****
        setResultSet_key: ep_contator
    **/ 


    public int setResultSet_key ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("prname")!=null) this.prname = rs.getString    ("prname"); 
        if (rs.getObject("annoco")!=null) this.annoco = rs.getString    ("annoco"); 

        return 1;
    }





}

