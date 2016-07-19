package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_web_ord_positito extends Object {

    public Rec_web_ord_positito() {

        super();
    }


    public String seStesso = "Rec_web_ord_positito";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public long       tkposi = 0; 
    public long       tkordi = 0; 
    public long       tkposi_off = 0; 
    public String     ripoor = ""; 
    public long       nrposi = 0; 
    public long       nrposi_v = 0; 
    public String     cdarti = ""; 
    public String     dssart = ""; 
    public String     cdunim = ""; 
    public double     fatconv = 0; 
    public String     cdunim2 = ""; 
    public String     cdfisc = ""; 
    public String     cdiva = ""; 
    public double     qtatot = 0; 
    public double     impuni = 0; 
    public double     scont1 = 0; 
    public double     scont2 = 0; 
    public double     scont3 = 0; 
    public double     scont4 = 0; 
    public String     cdagen_1 = ""; 
    public double     provv1_p = 0; 
    public double     provv1_v = 0; 
    public String     cdagen_2 = ""; 
    public double     provv2_p = 0; 
    public double     provv2_v = 0; 
    public String     tkproc1 = ""; 
    public String     cdentp1 = ""; 
    public double     comp_proc1 = 0; 
    public double     prov_proc1 = 0; 
    public String     tkproc2 = ""; 
    public String     cdentp2 = ""; 
    public double     comp_proc2 = 0; 
    public double     prov_proc2 = 0; 
    public double     couimb = 0; 
    public double     peuimb = 0; 
    public double     peumer = 0; 
    public double     voumer = 0; 
    public double     impgru = 0; 
    public double     impposa = 0; 
    public double     impnolo = 0; 
    public long       nroper = 0; 
    public String     rifriga = ""; 
    public String     npag = ""; 
    public double     qtaprel = 0; 
    public double     qtacons = 0; 
    public String     fgsaldo = ""; 
    public Timestamp  dtcons = null; 
    public String     cdlist = ""; 
    public String     cdclsc = ""; 
    public String     cdstato = ""; 
    public String     cdcoll = ""; 
    public long       tklisttag = 0; 
    public String     cdtpsco = ""; 
    public String     notatag = ""; 
    public String     notaven = ""; 
    public String     cdtpar = ""; 
    public String     nrparl = ""; 
    public double     qtalav = 0; 
    public String     cdadde = ""; 
    public String     cdcatm = ""; 
    public long       tkmaga = 0; 
    public String     cdkit = ""; 
    public String     cdazie = ""; 
    public String     cdprom = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 
    public long       ncolli = 0; 
    public long       pzcolli = 0; 
    public double     dimena = 0; 
    public double     dimenb = 0; 
    public double     dimenc = 0; 
    public String     cdtins = ""; 
    public String     cdvar = ""; 
    public String     cdrifo = ""; 
    public String     fsped = ""; 
    public double     importonettoriga = 0; 
    public double     importoriga = 0; 
    public double     impuninetto = 0; 
    public double     scrap1 = 0; 
    public double     scrap2 = 0; 
    public double     qtcons_s = 0; 
    public double     scval = 0; 
    public String     fpzman = ""; 
    public long       scontirep = 0; 
    public double     pzxcollo = 0; 
    public String     cdarti_imb = ""; 
    public String     flivprovv = ""; 
    public double     provv1_p_var = 0; 
    public double     provv2_p_var = 0; 
    public double     provv1_v_var = 0; 
    public double     provv2_v_var = 0; 
    public String     cdartprod_nc = ""; 
    public String     fnettosc = ""; 
    public String     tkforn = ""; 
    public double     impuni_acq = 0; 
    public double     sconto1_acq = 0; 
    public double     sconto2_acq = 0; 
    public String     fordacq = ""; 
    public long       nlisprel = 0; 
    public long       tkposi_contr = 0; 
    public long       tkordigest = 0; 
    public long       tkposigest = 0; 

    public String     cdartirif = ""; //EAR-20091012
    public String     fgpromo   = ""; //EAR-20111203





    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.tkposi = 0; 
        this.tkordi = 0; 
        this.tkposi_off = 0; 
        this.ripoor = ""; 
        this.nrposi = 0; 
        this.nrposi_v = 0; 
        this.cdarti = ""; 
        this.dssart = ""; 
        this.cdunim = ""; 
        this.fatconv = 0; 
        this.cdunim2 = ""; 
        this.cdfisc = ""; 
        this.cdiva = ""; 
        this.qtatot = 0; 
        this.impuni = 0; 
        this.scont1 = 0; 
        this.scont2 = 0; 
        this.scont3 = 0; 
        this.scont4 = 0; 
        this.cdagen_1 = ""; 
        this.provv1_p = 0; 
        this.provv1_v = 0; 
        this.cdagen_2 = ""; 
        this.provv2_p = 0; 
        this.provv2_v = 0; 
        this.tkproc1 = ""; 
        this.cdentp1 = ""; 
        this.comp_proc1 = 0; 
        this.prov_proc1 = 0; 
        this.tkproc2 = ""; 
        this.cdentp2 = ""; 
        this.comp_proc2 = 0; 
        this.prov_proc2 = 0; 
        this.couimb = 0; 
        this.peuimb = 0; 
        this.peumer = 0; 
        this.voumer = 0; 
        this.impgru = 0; 
        this.impposa = 0; 
        this.impnolo = 0; 
        this.nroper = 0; 
        this.rifriga = ""; 
        this.npag = ""; 
        this.qtaprel = 0; 
        this.qtacons = 0; 
        this.fgsaldo = ""; 
        this.dtcons = null; 
        this.cdlist = ""; 
        this.cdclsc = ""; 
        this.cdstato = ""; 
        this.cdcoll = ""; 
        this.tklisttag = 0; 
        this.cdtpsco = ""; 
        this.notatag = ""; 
        this.notaven = ""; 
        this.cdtpar = ""; 
        this.nrparl = ""; 
        this.qtalav = 0; 
        this.cdadde = ""; 
        this.cdcatm = ""; 
        this.tkmaga = 0; 
        this.cdkit = ""; 
        this.cdazie = ""; 
        this.cdprom = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 
        this.ncolli = 0; 
        this.pzcolli = 0; 
        this.dimena = 0; 
        this.dimenb = 0; 
        this.dimenc = 0; 
        this.cdtins = ""; 
        this.cdvar = ""; 
        this.cdrifo = ""; 
        this.fsped = ""; 
        this.importonettoriga = 0; 
        this.importoriga = 0; 
        this.impuninetto = 0; 
        this.scrap1 = 0; 
        this.scrap2 = 0; 
        this.qtcons_s = 0; 
        this.scval = 0; 
        this.fpzman = ""; 
        this.scontirep = 0; 
        this.pzxcollo = 0; 
        this.cdarti_imb = ""; 
        this.flivprovv = ""; 
        this.provv1_p_var = 0; 
        this.provv2_p_var = 0; 
        this.provv1_v_var = 0; 
        this.provv2_v_var = 0; 
        this.cdartprod_nc = ""; 
        this.fnettosc = ""; 
        this.tkforn = ""; 
        this.impuni_acq = 0; 
        this.sconto1_acq = 0; 
        this.sconto2_acq = 0; 
        this.fordacq = ""; 
        this.nlisprel = 0; 
        this.tkposi_contr = 0; 
        this.tkordigest = 0; 
        this.tkposigest = 0;

        this.cdartirif = ""; //EAR-20091012
        this.fgpromo   = ""; //EAR-20111203

    }






    /****
        setResultSet: web_ord_positito
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkposi")!=null) this.tkposi = rs.getLong      ("tkposi"); 
        if (rs.getObject("tkordi")!=null) this.tkordi = rs.getLong      ("tkordi"); 
        if (rs.getObject("tkposi_off")!=null) this.tkposi_off = rs.getLong      ("tkposi_off"); 
        if (rs.getObject("ripoor")!=null) this.ripoor = rs.getString    ("ripoor"); 
        if (rs.getObject("nrposi")!=null) this.nrposi = rs.getLong      ("nrposi"); 
        if (rs.getObject("nrposi_v")!=null) this.nrposi_v = rs.getLong      ("nrposi_v"); 
        if (rs.getObject("cdarti")!=null) this.cdarti = rs.getString    ("cdarti"); 
        if (rs.getObject("dssart")!=null) this.dssart = rs.getString    ("dssart"); 
        if (rs.getObject("cdunim")!=null) this.cdunim = rs.getString    ("cdunim"); 
        if (rs.getObject("fatconv")!=null) this.fatconv = rs.getDouble    ("fatconv"); 
        if (rs.getObject("cdunim2")!=null) this.cdunim2 = rs.getString    ("cdunim2"); 
        if (rs.getObject("cdfisc")!=null) this.cdfisc = rs.getString    ("cdfisc"); 
        if (rs.getObject("cdiva")!=null) this.cdiva = rs.getString    ("cdiva"); 
        if (rs.getObject("qtatot")!=null) this.qtatot = rs.getDouble    ("qtatot"); 
        if (rs.getObject("impuni")!=null) this.impuni = rs.getDouble    ("impuni"); 
        if (rs.getObject("scont1")!=null) this.scont1 = rs.getDouble    ("scont1"); 
        if (rs.getObject("scont2")!=null) this.scont2 = rs.getDouble    ("scont2"); 
        if (rs.getObject("scont3")!=null) this.scont3 = rs.getDouble    ("scont3"); 
        if (rs.getObject("scont4")!=null) this.scont4 = rs.getDouble    ("scont4"); 
        if (rs.getObject("cdagen_1")!=null) this.cdagen_1 = rs.getString    ("cdagen_1"); 
        if (rs.getObject("provv1_p")!=null) this.provv1_p = rs.getDouble    ("provv1_p"); 
        if (rs.getObject("provv1_v")!=null) this.provv1_v = rs.getDouble    ("provv1_v"); 
        if (rs.getObject("cdagen_2")!=null) this.cdagen_2 = rs.getString    ("cdagen_2"); 
        if (rs.getObject("provv2_p")!=null) this.provv2_p = rs.getDouble    ("provv2_p"); 
        if (rs.getObject("provv2_v")!=null) this.provv2_v = rs.getDouble    ("provv2_v"); 
        if (rs.getObject("tkproc1")!=null) this.tkproc1 = rs.getString    ("tkproc1"); 
        if (rs.getObject("cdentp1")!=null) this.cdentp1 = rs.getString    ("cdentp1"); 
        if (rs.getObject("comp_proc1")!=null) this.comp_proc1 = rs.getDouble    ("comp_proc1"); 
        if (rs.getObject("prov_proc1")!=null) this.prov_proc1 = rs.getDouble    ("prov_proc1"); 
        if (rs.getObject("tkproc2")!=null) this.tkproc2 = rs.getString    ("tkproc2"); 
        if (rs.getObject("cdentp2")!=null) this.cdentp2 = rs.getString    ("cdentp2"); 
        if (rs.getObject("comp_proc2")!=null) this.comp_proc2 = rs.getDouble    ("comp_proc2"); 
        if (rs.getObject("prov_proc2")!=null) this.prov_proc2 = rs.getDouble    ("prov_proc2"); 
        if (rs.getObject("couimb")!=null) this.couimb = rs.getDouble    ("couimb"); 
        if (rs.getObject("peuimb")!=null) this.peuimb = rs.getDouble    ("peuimb"); 
        if (rs.getObject("peumer")!=null) this.peumer = rs.getDouble    ("peumer"); 
        if (rs.getObject("voumer")!=null) this.voumer = rs.getDouble    ("voumer"); 
        if (rs.getObject("impgru")!=null) this.impgru = rs.getDouble    ("impgru"); 
        if (rs.getObject("impposa")!=null) this.impposa = rs.getDouble    ("impposa"); 
        if (rs.getObject("impnolo")!=null) this.impnolo = rs.getDouble    ("impnolo"); 
        if (rs.getObject("nroper")!=null) this.nroper = rs.getLong      ("nroper"); 
        if (rs.getObject("rifriga")!=null) this.rifriga = rs.getString    ("rifriga"); 
        if (rs.getObject("npag")!=null) this.npag = rs.getString    ("npag"); 
        if (rs.getObject("qtaprel")!=null) this.qtaprel = rs.getDouble    ("qtaprel"); 
        if (rs.getObject("qtacons")!=null) this.qtacons = rs.getDouble    ("qtacons"); 
        if (rs.getObject("fgsaldo")!=null) this.fgsaldo = rs.getString    ("fgsaldo"); 
        if (rs.getObject("dtcons")!=null) this.dtcons = rs.getTimestamp ("dtcons"); 
        if (rs.getObject("cdlist")!=null) this.cdlist = rs.getString    ("cdlist"); 
        if (rs.getObject("cdclsc")!=null) this.cdclsc = rs.getString    ("cdclsc"); 
        if (rs.getObject("cdstato")!=null) this.cdstato = rs.getString    ("cdstato"); 
        if (rs.getObject("cdcoll")!=null) this.cdcoll = rs.getString    ("cdcoll"); 
        if (rs.getObject("tklisttag")!=null) this.tklisttag = rs.getLong      ("tklisttag"); 
        if (rs.getObject("cdtpsco")!=null) this.cdtpsco = rs.getString    ("cdtpsco"); 
        if (rs.getObject("notatag")!=null) this.notatag = rs.getString    ("notatag"); 
        if (rs.getObject("notaven")!=null) this.notaven = rs.getString    ("notaven"); 
        if (rs.getObject("cdtpar")!=null) this.cdtpar = rs.getString    ("cdtpar"); 
        if (rs.getObject("nrparl")!=null) this.nrparl = rs.getString    ("nrparl"); 
        if (rs.getObject("qtalav")!=null) this.qtalav = rs.getDouble    ("qtalav"); 
        if (rs.getObject("cdadde")!=null) this.cdadde = rs.getString    ("cdadde"); 
        if (rs.getObject("cdcatm")!=null) this.cdcatm = rs.getString    ("cdcatm"); 
        if (rs.getObject("tkmaga")!=null) this.tkmaga = rs.getLong      ("tkmaga"); 
        if (rs.getObject("cdkit")!=null) this.cdkit = rs.getString    ("cdkit"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cdprom")!=null) this.cdprom = rs.getString    ("cdprom"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("ncolli")!=null) this.ncolli = rs.getLong      ("ncolli"); 
        if (rs.getObject("pzcolli")!=null) this.pzcolli = rs.getLong      ("pzcolli"); 
        if (rs.getObject("dimena")!=null) this.dimena = rs.getDouble    ("dimena"); 
        if (rs.getObject("dimenb")!=null) this.dimenb = rs.getDouble    ("dimenb"); 
        if (rs.getObject("dimenc")!=null) this.dimenc = rs.getDouble    ("dimenc"); 
        if (rs.getObject("cdtins")!=null) this.cdtins = rs.getString    ("cdtins"); 
        if (rs.getObject("cdvar")!=null) this.cdvar = rs.getString    ("cdvar"); 
        if (rs.getObject("cdrifo")!=null) this.cdrifo = rs.getString    ("cdrifo"); 
        if (rs.getObject("fsped")!=null) this.fsped = rs.getString    ("fsped"); 
        if (rs.getObject("importonettoriga")!=null) this.importonettoriga = rs.getDouble    ("importonettoriga"); 
        if (rs.getObject("importoriga")!=null) this.importoriga = rs.getDouble    ("importoriga"); 
        if (rs.getObject("impuninetto")!=null) this.impuninetto = rs.getDouble    ("impuninetto"); 
        if (rs.getObject("scrap1")!=null) this.scrap1 = rs.getDouble    ("scrap1"); 
        if (rs.getObject("scrap2")!=null) this.scrap2 = rs.getDouble    ("scrap2"); 
        if (rs.getObject("qtcons_s")!=null) this.qtcons_s = rs.getDouble    ("qtcons_s"); 
        if (rs.getObject("scval")!=null) this.scval = rs.getDouble    ("scval"); 
        if (rs.getObject("fpzman")!=null) this.fpzman = rs.getString    ("fpzman"); 
        if (rs.getObject("scontirep")!=null) this.scontirep = rs.getLong      ("scontirep"); 
        if (rs.getObject("pzxcollo")!=null) this.pzxcollo = rs.getDouble    ("pzxcollo"); 
        if (rs.getObject("cdarti_imb")!=null) this.cdarti_imb = rs.getString    ("cdarti_imb"); 
        if (rs.getObject("flivprovv")!=null) this.flivprovv = rs.getString    ("flivprovv"); 
        if (rs.getObject("provv1_p_var")!=null) this.provv1_p_var = rs.getDouble    ("provv1_p_var"); 
        if (rs.getObject("provv2_p_var")!=null) this.provv2_p_var = rs.getDouble    ("provv2_p_var"); 
        if (rs.getObject("provv1_v_var")!=null) this.provv1_v_var = rs.getDouble    ("provv1_v_var"); 
        if (rs.getObject("provv2_v_var")!=null) this.provv2_v_var = rs.getDouble    ("provv2_v_var"); 
        if (rs.getObject("cdartprod_nc")!=null) this.cdartprod_nc = rs.getString    ("cdartprod_nc"); 
        if (rs.getObject("fnettosc")!=null) this.fnettosc = rs.getString    ("fnettosc"); 
        if (rs.getObject("tkforn")!=null) this.tkforn = rs.getString    ("tkforn"); 
        if (rs.getObject("impuni_acq")!=null) this.impuni_acq = rs.getDouble    ("impuni_acq"); 
        if (rs.getObject("sconto1_acq")!=null) this.sconto1_acq = rs.getDouble    ("sconto1_acq"); 
        if (rs.getObject("sconto2_acq")!=null) this.sconto2_acq = rs.getDouble    ("sconto2_acq"); 
        if (rs.getObject("fordacq")!=null) this.fordacq = rs.getString    ("fordacq"); 
        if (rs.getObject("nlisprel")!=null) this.nlisprel = rs.getLong      ("nlisprel"); 
        if (rs.getObject("tkposi_contr")!=null) this.tkposi_contr = rs.getLong      ("tkposi_contr"); 
        if (rs.getObject("tkordigest")!=null) this.tkordigest = rs.getLong      ("tkordigest"); 
        if (rs.getObject("tkposigest")!=null) this.tkposigest = rs.getLong      ("tkposigest");

        if (rs.getObject("cdartirif")!=null) this.cdartirif = rs.getString    ("cdartirif"); //EAR-20091012
        if (rs.getObject("fgpromo")!=null) this.fgpromo     = rs.getString    ("fgpromo"); //EAR-20111203

        return 1;
    }




    /****
        setResultSet_key: web_ord_positito
    **/ 


    public int setResultSet_key ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkposi")!=null) this.tkposi = rs.getLong      ("tkposi"); 

        return 1;
    }




    /****
        trim(): web_ord_positito
    **/ 


    public void trim( ) throws Exception {

        this.ripoor = this.ripoor.trim(); 
        this.cdarti = this.cdarti.trim(); 
        this.dssart = this.dssart.trim(); 
        this.cdunim = this.cdunim.trim(); 
        this.cdunim2 = this.cdunim2.trim(); 
        this.cdfisc = this.cdfisc.trim(); 
        this.cdiva = this.cdiva.trim(); 
        this.cdagen_1 = this.cdagen_1.trim(); 
        this.cdagen_2 = this.cdagen_2.trim(); 
        this.tkproc1 = this.tkproc1.trim(); 
        this.cdentp1 = this.cdentp1.trim(); 
        this.tkproc2 = this.tkproc2.trim(); 
        this.cdentp2 = this.cdentp2.trim(); 
        this.rifriga = this.rifriga.trim(); 
        this.npag = this.npag.trim(); 
        this.fgsaldo = this.fgsaldo.trim(); 
        this.cdlist = this.cdlist.trim(); 
        this.cdclsc = this.cdclsc.trim(); 
        this.cdstato = this.cdstato.trim(); 
        this.cdcoll = this.cdcoll.trim(); 
        this.cdtpsco = this.cdtpsco.trim(); 
        this.notatag = this.notatag.trim(); 
        this.notaven = this.notaven.trim(); 
        this.cdtpar = this.cdtpar.trim(); 
        this.nrparl = this.nrparl.trim(); 
        this.cdadde = this.cdadde.trim(); 
        this.cdcatm = this.cdcatm.trim(); 
        this.cdkit = this.cdkit.trim(); 
        this.cdazie = this.cdazie.trim(); 
        this.cdprom = this.cdprom.trim(); 
        this.cddipa = this.cddipa.trim(); 
        this.profil = this.profil.trim(); 
        this.cdtins = this.cdtins.trim(); 
        this.cdvar = this.cdvar.trim(); 
        this.cdrifo = this.cdrifo.trim(); 
        this.fsped = this.fsped.trim(); 
        this.fpzman = this.fpzman.trim(); 
        this.cdarti_imb = this.cdarti_imb.trim(); 
        this.flivprovv = this.flivprovv.trim(); 
        this.cdartprod_nc = this.cdartprod_nc.trim(); 
        this.fnettosc = this.fnettosc.trim(); 
        this.tkforn = this.tkforn.trim(); 
        this.fordacq = this.fordacq.trim();

        this.cdartirif = this.cdartirif.trim();//EAR-20091012
        this.fgpromo   = this.fgpromo.trim();//EAR-20111203

    }





}

