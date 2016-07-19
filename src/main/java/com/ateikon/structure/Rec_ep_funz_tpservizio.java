package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_ep_funz_tpservizio extends Object {

    public Rec_ep_funz_tpservizio() {

        super();
    }

    public Rec_ep_funz_tpservizio( com.ateikon.common.Ep_funz_tpservizio obj_common_from
                       , com.ateikon.common.Ep_funz_tpservizio obj_common_to   ) {

        super();

        if (obj_common_from !=null){
            this.obj_common_from = obj_common_from;
        }
        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public Rec_ep_funz_tpservizio( com.ateikon.common.Ep_funz_tpservizio obj_common_to   ) {

        super();

        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public com.ateikon.common.Ep_funz_tpservizio obj_common_from = null;
    public com.ateikon.common.Ep_funz_tpservizio obj_common_to   = null;


    public String seStesso = "Rec_ep_funz_tpservizio";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public long       tkfunzione = 0; 
    public long       tkutente_tpservizio = 0; 
    public String     cdazie = ""; 
    public String     cddipa = ""; 
    public String     profil_inse = ""; 
    public String     profil = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.tkfunzione = 0; 
        this.tkutente_tpservizio = 0; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil_inse = ""; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 

    }






    /****
        setResultSet: ep_funz_tpservizio
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkfunzione")!=null){
            this.tkfunzione = rs.getLong      ("tkfunzione"); 
            if (obj_common_to   != null) obj_common_to.null_tkfunzione = false;
            if (obj_common_from != null) obj_common_from.null_tkfunzione = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tkfunzione = true;
            if (obj_common_from != null) obj_common_from.null_tkfunzione = true;
        }
        if (rs.getObject("tkutente_tpservizio")!=null){
            this.tkutente_tpservizio = rs.getLong      ("tkutente_tpservizio"); 
            if (obj_common_to   != null) obj_common_to.null_tkutente_tpservizio = false;
            if (obj_common_from != null) obj_common_from.null_tkutente_tpservizio = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tkutente_tpservizio = true;
            if (obj_common_from != null) obj_common_from.null_tkutente_tpservizio = true;
        }
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil_inse")!=null) this.profil_inse = rs.getString    ("profil_inse"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 

        return 1;
    }




    /****
        setResultSet_key: ep_funz_tpservizio
    **/ 


    public int setResultSet_key ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkfunzione")!=null){
            this.tkfunzione = rs.getLong      ("tkfunzione"); 
            if (obj_common_to   != null) obj_common_to.null_tkfunzione = false;
            if (obj_common_from != null) obj_common_from.null_tkfunzione = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tkfunzione = true;
            if (obj_common_from != null) obj_common_from.null_tkfunzione = true;
        }
        if (rs.getObject("tkutente_tpservizio")!=null){
            this.tkutente_tpservizio = rs.getLong      ("tkutente_tpservizio"); 
            if (obj_common_to   != null) obj_common_to.null_tkutente_tpservizio = false;
            if (obj_common_from != null) obj_common_from.null_tkutente_tpservizio = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tkutente_tpservizio = true;
            if (obj_common_from != null) obj_common_from.null_tkutente_tpservizio = true;
        }

        return 1;
    }




    /****
        trim(): ep_funz_tpservizio
    **/ 


    public void trim( ) throws Exception {

        this.cdazie = this.cdazie.trim(); 
        this.cddipa = this.cddipa.trim(); 
        this.profil_inse = this.profil_inse.trim(); 
        this.profil = this.profil.trim(); 

    }




    /****
        ctrl_mindate(): ep_funz_tpservizio
    **/ 


    public void ctrl_mindate( ) throws Exception {

        int li_sa = 0; 

        li_sa = 0; 
        if (this.dtinse != null){ 
            li_sa = this.dtinse.getYear()+1900; 
            if (li_sa < 1900){ 
                this.dtinse.setYear(0); 
                this.dtinse.setMonth(0); 
                this.dtinse.setDate(1); 
                this.dtinse.setHours(0); 
                this.dtinse.setMinutes(0); 
                this.dtinse.setSeconds(0); 
                this.dtinse.setNanos(0); 
            } 
            this.dtinse.setNanos(0); 
            this.dtinse.toString(); 
        } 
        li_sa = 0; 
        if (this.dtulag != null){ 
            li_sa = this.dtulag.getYear()+1900; 
            if (li_sa < 1900){ 
                this.dtulag.setYear(0); 
                this.dtulag.setMonth(0); 
                this.dtulag.setDate(1); 
                this.dtulag.setHours(0); 
                this.dtulag.setMinutes(0); 
                this.dtulag.setSeconds(0); 
                this.dtulag.setNanos(0); 
            } 
            this.dtulag.setNanos(0); 
            this.dtulag.toString(); 
        } 

    }





}

