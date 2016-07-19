package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_cliente extends Object {

    public Rec_cliente() {

        super();
    }


    public String seStesso = "Rec_cliente";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public String     tkclie = ""; 
    public String     cdclie_m = ""; 
    public String     ragcog = ""; 
    public String     indirizzo = ""; 
    public String     cap = ""; 
    public String     localita = ""; 
    public String     cdprov_m = ""; 
    public String     dsnazi = ""; 
    public String     piva = ""; 
    public String     cfisc = ""; 
    public String     cdpagame = ""; 
    public String     cdagen_m = ""; 
    public String     cdsitc_m = ""; 
    public String     fbloccato = ""; 
    public double     percaddtra = 0; 
    public String     cdtrme = ""; 
    public String     cdazie = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 
    public String     cdpagame_m = ""; 
    public String     cdrmpo_m = ""; 
    public String     cdvett_m = ""; 
    public String     cdcllist_m = ""; 
    public String     cdagen = ""; 
    public String     abibap = ""; 
    public String     cabbap = ""; 
    public String     cdcapo = ""; 
    public String     cdfisc = ""; 
    public String     cdiva = ""; 
    public String     badesc = ""; 
    public String     telefono = ""; 
    public String     fax = ""; 
    public String     email = ""; 
    public String     sitointernet = ""; 
    public String     cellulare = ""; 
    public String     notagest = ""; 
    public String     fnewcli = ""; 
    public String     tkcliegest = ""; 
    public double     scocas = 0; 
    public double     sconto1 = 0; 
    public double     sconto2 = 0; 
    public double     sconto3 = 0; 
    public double     sconto4 = 0; 
    public String     cliacr = ""; 
    public String     nccorr = ""; 
    public String     cdente = ""; 
    public String     cdprov = ""; 
    public String     cdnazi = ""; 
    public String     cdrmpo = ""; 
    public String     cdclac = ""; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.tkclie = ""; 
        this.cdclie_m = ""; 
        this.ragcog = ""; 
        this.indirizzo = ""; 
        this.cap = ""; 
        this.localita = ""; 
        this.cdprov_m = ""; 
        this.dsnazi = ""; 
        this.piva = ""; 
        this.cfisc = ""; 
        this.cdpagame = ""; 
        this.cdagen_m = ""; 
        this.cdsitc_m = ""; 
        this.fbloccato = ""; 
        this.percaddtra = 0; 
        this.cdtrme = ""; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 
        this.cdpagame_m = ""; 
        this.cdrmpo_m = ""; 
        this.cdvett_m = ""; 
        this.cdcllist_m = ""; 
        this.cdagen = ""; 
        this.abibap = ""; 
        this.cabbap = ""; 
        this.cdcapo = ""; 
        this.cdfisc = ""; 
        this.cdiva = ""; 
        this.badesc = ""; 
        this.telefono = ""; 
        this.fax = ""; 
        this.email = ""; 
        this.sitointernet = ""; 
        this.cellulare = ""; 
        this.notagest = ""; 
        this.fnewcli = ""; 
        this.tkcliegest = ""; 
        this.scocas = 0; 
        this.sconto1 = 0; 
        this.sconto2 = 0; 
        this.sconto3 = 0; 
        this.sconto4 = 0; 
        this.cliacr = ""; 
        this.nccorr = ""; 
        this.cdente = ""; 
        this.cdprov = ""; 
        this.cdnazi = ""; 
        this.cdrmpo = ""; 
        this.cdclac = ""; 

    }






    /****
        setResultSet: cliente
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkclie")!=null) this.tkclie = rs.getString    ("tkclie"); 
        if (rs.getObject("cdclie_m")!=null) this.cdclie_m = rs.getString    ("cdclie_m"); 
        if (rs.getObject("ragcog")!=null) this.ragcog = rs.getString    ("ragcog"); 
        if (rs.getObject("indirizzo")!=null) this.indirizzo = rs.getString    ("indirizzo"); 
        if (rs.getObject("cap")!=null) this.cap = rs.getString    ("cap"); 
        if (rs.getObject("localita")!=null) this.localita = rs.getString    ("localita"); 
        if (rs.getObject("cdprov_m")!=null) this.cdprov_m = rs.getString    ("cdprov_m"); 
        if (rs.getObject("dsnazi")!=null) this.dsnazi = rs.getString    ("dsnazi"); 
        if (rs.getObject("piva")!=null) this.piva = rs.getString    ("piva"); 
        if (rs.getObject("cfisc")!=null) this.cfisc = rs.getString    ("cfisc"); 
        if (rs.getObject("cdpagame")!=null) this.cdpagame = rs.getString    ("cdpagame"); 
        if (rs.getObject("cdagen_m")!=null) this.cdagen_m = rs.getString    ("cdagen_m"); 
        if (rs.getObject("cdsitc_m")!=null) this.cdsitc_m = rs.getString    ("cdsitc_m"); 
        if (rs.getObject("fbloccato")!=null) this.fbloccato = rs.getString    ("fbloccato"); 
        if (rs.getObject("percaddtra")!=null) this.percaddtra = rs.getDouble    ("percaddtra"); 
        if (rs.getObject("cdtrme")!=null) this.cdtrme = rs.getString    ("cdtrme"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("cdpagame_m")!=null) this.cdpagame_m = rs.getString    ("cdpagame_m"); 
        if (rs.getObject("cdrmpo_m")!=null) this.cdrmpo_m = rs.getString    ("cdrmpo_m"); 
        if (rs.getObject("cdvett_m")!=null) this.cdvett_m = rs.getString    ("cdvett_m"); 
        if (rs.getObject("cdcllist_m")!=null) this.cdcllist_m = rs.getString    ("cdcllist_m"); 
        if (rs.getObject("cdagen")!=null) this.cdagen = rs.getString    ("cdagen"); 
        if (rs.getObject("abibap")!=null) this.abibap = rs.getString    ("abibap"); 
        if (rs.getObject("cabbap")!=null) this.cabbap = rs.getString    ("cabbap"); 
        if (rs.getObject("cdcapo")!=null) this.cdcapo = rs.getString    ("cdcapo"); 
        if (rs.getObject("cdfisc")!=null) this.cdfisc = rs.getString    ("cdfisc"); 
        if (rs.getObject("cdiva")!=null) this.cdiva = rs.getString    ("cdiva"); 
        if (rs.getObject("badesc")!=null) this.badesc = rs.getString    ("badesc"); 
        if (rs.getObject("telefono")!=null) this.telefono = rs.getString    ("telefono"); 
        if (rs.getObject("fax")!=null) this.fax = rs.getString    ("fax"); 
        if (rs.getObject("email")!=null) this.email = rs.getString    ("email"); 
        if (rs.getObject("sitointernet")!=null) this.sitointernet = rs.getString    ("sitointernet"); 
        if (rs.getObject("cellulare")!=null) this.cellulare = rs.getString    ("cellulare"); 
        if (rs.getObject("notagest")!=null) this.notagest = rs.getString    ("notagest"); 
        if (rs.getObject("fnewcli")!=null) this.fnewcli = rs.getString    ("fnewcli"); 
        if (rs.getObject("tkcliegest")!=null) this.tkcliegest = rs.getString    ("tkcliegest"); 
        if (rs.getObject("scocas")!=null) this.scocas = rs.getDouble    ("scocas"); 
        if (rs.getObject("sconto1")!=null) this.sconto1 = rs.getDouble    ("sconto1"); 
        if (rs.getObject("sconto2")!=null) this.sconto2 = rs.getDouble    ("sconto2"); 
        if (rs.getObject("sconto3")!=null) this.sconto3 = rs.getDouble    ("sconto3"); 
        if (rs.getObject("sconto4")!=null) this.sconto4 = rs.getDouble    ("sconto4"); 
        if (rs.getObject("cliacr")!=null) this.cliacr = rs.getString    ("cliacr"); 
        if (rs.getObject("nccorr")!=null) this.nccorr = rs.getString    ("nccorr"); 
        if (rs.getObject("cdente")!=null) this.cdente = rs.getString    ("cdente"); 
        if (rs.getObject("cdprov")!=null) this.cdprov = rs.getString    ("cdprov"); 
        if (rs.getObject("cdnazi")!=null) this.cdnazi = rs.getString    ("cdnazi"); 
        if (rs.getObject("cdrmpo")!=null) this.cdrmpo = rs.getString    ("cdrmpo"); 
        if (rs.getObject("cdclac")!=null) this.cdclac = rs.getString    ("cdclac"); 

        return 1;
    }





}

