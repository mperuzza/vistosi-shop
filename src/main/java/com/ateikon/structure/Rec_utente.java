package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_utente extends Object {

    public Rec_utente() {

        super();
    }


    public String seStesso = "Rec_utente";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public String     cdutente = ""; 
    public String     dsutente = ""; 
    public String     pwd = ""; 
    public String     idutente = ""; 
    public String     cdagen = ""; 
    public String     cdcapo = ""; 
    public String     cdazie = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 
    public String     username = ""; 
    public String     password = ""; 
    public String     nome = ""; 
    public String     cognome = ""; 
    public String     ragsoc = ""; 
    public String     cdgput = ""; 
    public String     cdente = ""; 
    public String     tkclie = ""; 
    public String     tkforn = ""; 
    public String     indirizzo = ""; 
    public String     cap = ""; 
    public String     localita = ""; 
    public String     piva = ""; 
    public String     cfisc = ""; 
    public String     telefono = ""; 
    public String     fax = ""; 
    public String     email = ""; 
    public String     sitointernet = ""; 
    public String     cellulare = ""; 
    public String     cdnazi = ""; 
    public String     nota = ""; 
    public String     fgweb = ""; 
    public long       livello = 0; 
    public String     tipoblocco = ""; 
    public Timestamp  dtblocco = null; 
    public String     cdprov = ""; 
    public String     sesso = ""; 
    public Timestamp  dtnasc = null; 
    public String     pariva = ""; 
    public String     indiri = ""; 
    public String     comune = ""; 
    public String     pwd2 = ""; 
    public String     pwd3 = ""; 
    public Timestamp  dtpwd = null; 
    public Timestamp  dtpwd2 = null; 
    public Timestamp  dtpwd3 = null; 
    public Timestamp  dtulconn = null; 
    public Timestamp  dtwebsync = null; 
    public Timestamp  dtricpwd = null; 
    public String     cdutente_gest = ""; 
    public String     ftpusername = ""; 
    public String     ftppwd = ""; 
    public String     fgfmailpwd = ""; 
    public String     fgmailbatch = ""; 
    public Timestamp  dtmailbatch = null; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.cdutente = ""; 
        this.dsutente = ""; 
        this.pwd = ""; 
        this.idutente = ""; 
        this.cdagen = ""; 
        this.cdcapo = ""; 
        this.cdazie = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 
        this.username = ""; 
        this.password = ""; 
        this.nome = ""; 
        this.cognome = ""; 
        this.ragsoc = ""; 
        this.cdgput = ""; 
        this.cdente = ""; 
        this.tkclie = ""; 
        this.tkforn = ""; 
        this.indirizzo = ""; 
        this.cap = ""; 
        this.localita = ""; 
        this.piva = ""; 
        this.cfisc = ""; 
        this.telefono = ""; 
        this.fax = ""; 
        this.email = ""; 
        this.sitointernet = ""; 
        this.cellulare = ""; 
        this.cdnazi = ""; 
        this.nota = ""; 
        this.fgweb = ""; 
        this.livello = 0; 
        this.tipoblocco = ""; 
        this.dtblocco = null; 
        this.cdprov = ""; 
        this.sesso = ""; 
        this.dtnasc = null; 
        this.pariva = ""; 
        this.indiri = ""; 
        this.comune = ""; 
        this.pwd2 = ""; 
        this.pwd3 = ""; 
        this.dtpwd = null; 
        this.dtpwd2 = null; 
        this.dtpwd3 = null; 
        this.dtulconn = null; 
        this.dtwebsync = null; 
        this.dtricpwd = null; 
        this.cdutente_gest = ""; 
        this.ftpusername = ""; 
        this.ftppwd = ""; 
        this.fgfmailpwd = ""; 
        this.fgmailbatch = ""; 
        this.dtmailbatch = null; 

    }






    /****
        setResultSet: utente
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("cdutente")!=null) this.cdutente = rs.getString    ("cdutente"); 
        if (rs.getObject("dsutente")!=null) this.dsutente = rs.getString    ("dsutente"); 
        if (rs.getObject("pwd")!=null) this.pwd = rs.getString    ("pwd"); 
        if (rs.getObject("idutente")!=null) this.idutente = rs.getString    ("idutente"); 
        if (rs.getObject("cdagen")!=null) this.cdagen = rs.getString    ("cdagen"); 
        if (rs.getObject("cdcapo")!=null) this.cdcapo = rs.getString    ("cdcapo"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("username")!=null) this.username = rs.getString    ("username"); 
        if (rs.getObject("password")!=null) this.password = rs.getString    ("password"); 
        if (rs.getObject("nome")!=null) this.nome = rs.getString    ("nome"); 
        if (rs.getObject("cognome")!=null) this.cognome = rs.getString    ("cognome"); 
        if (rs.getObject("ragsoc")!=null) this.ragsoc = rs.getString    ("ragsoc"); 
        if (rs.getObject("cdgput")!=null) this.cdgput = rs.getString    ("cdgput"); 
        if (rs.getObject("cdente")!=null) this.cdente = rs.getString    ("cdente"); 
        if (rs.getObject("tkclie")!=null) this.tkclie = rs.getString    ("tkclie"); 
        if (rs.getObject("tkforn")!=null) this.tkforn = rs.getString    ("tkforn"); 
        if (rs.getObject("indirizzo")!=null) this.indirizzo = rs.getString    ("indirizzo"); 
        if (rs.getObject("cap")!=null) this.cap = rs.getString    ("cap"); 
        if (rs.getObject("localita")!=null) this.localita = rs.getString    ("localita"); 
        if (rs.getObject("piva")!=null) this.piva = rs.getString    ("piva"); 
        if (rs.getObject("cfisc")!=null) this.cfisc = rs.getString    ("cfisc"); 
        if (rs.getObject("telefono")!=null) this.telefono = rs.getString    ("telefono"); 
        if (rs.getObject("fax")!=null) this.fax = rs.getString    ("fax"); 
        if (rs.getObject("email")!=null) this.email = rs.getString    ("email"); 
        if (rs.getObject("sitointernet")!=null) this.sitointernet = rs.getString    ("sitointernet"); 
        if (rs.getObject("cellulare")!=null) this.cellulare = rs.getString    ("cellulare"); 
        if (rs.getObject("cdnazi")!=null) this.cdnazi = rs.getString    ("cdnazi"); 
        if (rs.getObject("nota")!=null) this.nota = rs.getString    ("nota"); 
        if (rs.getObject("fgweb")!=null) this.fgweb = rs.getString    ("fgweb"); 
        if (rs.getObject("livello")!=null) this.livello = rs.getLong      ("livello"); 
        if (rs.getObject("tipoblocco")!=null) this.tipoblocco = rs.getString    ("tipoblocco"); 
        if (rs.getObject("dtblocco")!=null) this.dtblocco = rs.getTimestamp ("dtblocco"); 
        if (rs.getObject("cdprov")!=null) this.cdprov = rs.getString    ("cdprov"); 
        if (rs.getObject("sesso")!=null) this.sesso = rs.getString    ("sesso"); 
        if (rs.getObject("dtnasc")!=null) this.dtnasc = rs.getTimestamp ("dtnasc"); 
        if (rs.getObject("pariva")!=null) this.pariva = rs.getString    ("pariva"); 
        if (rs.getObject("indiri")!=null) this.indiri = rs.getString    ("indiri"); 
        if (rs.getObject("comune")!=null) this.comune = rs.getString    ("comune"); 
        if (rs.getObject("pwd2")!=null) this.pwd2 = rs.getString    ("pwd2"); 
        if (rs.getObject("pwd3")!=null) this.pwd3 = rs.getString    ("pwd3"); 
        if (rs.getObject("dtpwd")!=null) this.dtpwd = rs.getTimestamp ("dtpwd"); 
        if (rs.getObject("dtpwd2")!=null) this.dtpwd2 = rs.getTimestamp ("dtpwd2"); 
        if (rs.getObject("dtpwd3")!=null) this.dtpwd3 = rs.getTimestamp ("dtpwd3"); 
        if (rs.getObject("dtulconn")!=null) this.dtulconn = rs.getTimestamp ("dtulconn"); 
        if (rs.getObject("dtwebsync")!=null) this.dtwebsync = rs.getTimestamp ("dtwebsync"); 
        if (rs.getObject("dtricpwd")!=null) this.dtricpwd = rs.getTimestamp ("dtricpwd"); 
        if (rs.getObject("cdutente_gest")!=null) this.cdutente_gest = rs.getString    ("cdutente_gest"); 
        if (rs.getObject("ftpusername")!=null) this.ftpusername = rs.getString    ("ftpusername"); 
        if (rs.getObject("ftppwd")!=null) this.ftppwd = rs.getString    ("ftppwd"); 
        if (rs.getObject("fgfmailpwd")!=null) this.fgfmailpwd = rs.getString    ("fgfmailpwd"); 
        if (rs.getObject("fgmailbatch")!=null) this.fgmailbatch = rs.getString    ("fgmailbatch"); 
        if (rs.getObject("dtmailbatch")!=null) this.dtmailbatch = rs.getTimestamp ("dtmailbatch"); 

        return 1;
    }





}

