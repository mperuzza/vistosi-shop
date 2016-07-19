package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_vist_nazioni_ref extends Object {

    public Rec_vist_nazioni_ref() {

        super();
    }

    public Rec_vist_nazioni_ref( com.ateikon.common.Vist_nazioni_ref obj_common_from
                       , com.ateikon.common.Vist_nazioni_ref obj_common_to   ) {

        super();

        if (obj_common_from !=null){
            this.obj_common_from = obj_common_from;
        }
        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public Rec_vist_nazioni_ref( com.ateikon.common.Vist_nazioni_ref obj_common_to   ) {

        super();

        if (obj_common_to != null){
            this.obj_common_to = obj_common_to;
        }
    }
    public com.ateikon.common.Vist_nazioni_ref obj_common_from = null;
    public com.ateikon.common.Vist_nazioni_ref obj_common_to   = null;


    public String seStesso = "Rec_vist_nazioni_ref";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public long       tknaziref = 0; 
    public String     cdnazi = ""; 
    public long       tkutente = 0; 
    public String     cdruolo = ""; 
    public String     cdazie = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 
    public String     fg_agg = ""; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.tknaziref = 0; 
        this.cdnazi = ""; 
        this.tkutente = 0; 
        this.cdruolo = ""; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 
        this.fg_agg = ""; 

    }






    /****
        setResultSet: vist_nazioni_ref
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tknaziref")!=null){
            this.tknaziref = rs.getLong      ("tknaziref"); 
            if (obj_common_to   != null) obj_common_to.null_tknaziref = false;
            if (obj_common_from != null) obj_common_from.null_tknaziref = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tknaziref = true;
            if (obj_common_from != null) obj_common_from.null_tknaziref = true;
        }
        if (rs.getObject("cdnazi")!=null) this.cdnazi = rs.getString    ("cdnazi"); 
        if (rs.getObject("tkutente")!=null){
            this.tkutente = rs.getLong      ("tkutente"); 
            if (obj_common_to   != null) obj_common_to.null_tkutente = false;
            if (obj_common_from != null) obj_common_from.null_tkutente = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tkutente = true;
            if (obj_common_from != null) obj_common_from.null_tkutente = true;
        }
        if (rs.getObject("cdruolo")!=null) this.cdruolo = rs.getString    ("cdruolo"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("fg_agg")!=null) this.fg_agg = rs.getString    ("fg_agg"); 

        return 1;
    }




    /****
        setResultSet_key: vist_nazioni_ref
    **/ 


    public int setResultSet_key ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tknaziref")!=null){
            this.tknaziref = rs.getLong      ("tknaziref"); 
            if (obj_common_to   != null) obj_common_to.null_tknaziref = false;
            if (obj_common_from != null) obj_common_from.null_tknaziref = false;
        }else {
            if (obj_common_to   != null) obj_common_to.null_tknaziref = true;
            if (obj_common_from != null) obj_common_from.null_tknaziref = true;
        }

        return 1;
    }




    /****
        trim(): vist_nazioni_ref
    **/ 


    public void trim( ) throws Exception {

        this.cdnazi = this.cdnazi.trim(); 
        this.cdruolo = this.cdruolo.trim(); 
        this.cdazie = this.cdazie.trim(); 
        this.cddipa = this.cddipa.trim(); 
        this.profil = this.profil.trim(); 
        this.fg_agg = this.fg_agg.trim(); 

    }




    /****
        ctrl_mindate(): vist_nazioni_ref
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

