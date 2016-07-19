package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_ep_utente_recapito_tipo extends Object {

    public Rec_ep_utente_recapito_tipo() {

        super();
    }

    public Rec_ep_utente_recapito_tipo( com.ateikon.common.Ep_utente_recapito_tipo obj_common_from
                       , com.ateikon.common.Ep_utente_recapito_tipo obj_common_to   ) {

        super();

        if (obj_common_from !=null){
            this.obj_common_from = obj_common_from;
        }
        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public Rec_ep_utente_recapito_tipo( com.ateikon.common.Ep_utente_recapito_tipo obj_common_to   ) {

        super();

        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public com.ateikon.common.Ep_utente_recapito_tipo obj_common_from = null;
    public com.ateikon.common.Ep_utente_recapito_tipo obj_common_to   = null;


    public String seStesso = "Rec_ep_utente_recapito_tipo";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public long       tkutente_rec_tipo = 0; 
    public String     cdutente_rec_tipo_m = ""; 
    public String     dsutente_rec_tipo = ""; 
    public long       nrposi = 0; 
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

        this.tkutente_rec_tipo = 0; 
        this.cdutente_rec_tipo_m = ""; 
        this.dsutente_rec_tipo = ""; 
        this.nrposi = 0; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil_inse = ""; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 

    }






    /****
        setResultSet: ep_utente_recapito_tipo
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkutente_rec_tipo")!=null){
            this.tkutente_rec_tipo = rs.getLong      ("tkutente_rec_tipo"); 
            if (obj_common_to   != null) obj_common_to.null_tkutente_rec_tipo = false;
            if (obj_common_from != null) obj_common_from.null_tkutente_rec_tipo = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tkutente_rec_tipo = true;
            if (obj_common_from != null) obj_common_from.null_tkutente_rec_tipo = true;
        }
        if (rs.getObject("cdutente_rec_tipo_m")!=null) this.cdutente_rec_tipo_m = rs.getString    ("cdutente_rec_tipo_m"); 
        if (rs.getObject("dsutente_rec_tipo")!=null) this.dsutente_rec_tipo = rs.getString    ("dsutente_rec_tipo"); 
        if (rs.getObject("nrposi")!=null){
            this.nrposi = rs.getLong      ("nrposi"); 
            if (obj_common_to   != null) obj_common_to.null_nrposi = false;
            if (obj_common_from != null) obj_common_from.null_nrposi = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_nrposi = true;
            if (obj_common_from != null) obj_common_from.null_nrposi = true;
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
        setResultSet_key: ep_utente_recapito_tipo
    **/ 


    public int setResultSet_key ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkutente_rec_tipo")!=null){
            this.tkutente_rec_tipo = rs.getLong      ("tkutente_rec_tipo"); 
            if (obj_common_to   != null) obj_common_to.null_tkutente_rec_tipo = false;
            if (obj_common_from != null) obj_common_from.null_tkutente_rec_tipo = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tkutente_rec_tipo = true;
            if (obj_common_from != null) obj_common_from.null_tkutente_rec_tipo = true;
        }

        return 1;
    }




    /****
        trim(): ep_utente_recapito_tipo
    **/ 


    public void trim( ) throws Exception {

        this.cdutente_rec_tipo_m = this.cdutente_rec_tipo_m.trim(); 
        this.dsutente_rec_tipo = this.dsutente_rec_tipo.trim(); 
        this.cdazie = this.cdazie.trim(); 
        this.cddipa = this.cddipa.trim(); 
        this.profil_inse = this.profil_inse.trim(); 
        this.profil = this.profil.trim(); 

    }




    /****
        ctrl_mindate(): ep_utente_recapito_tipo
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

