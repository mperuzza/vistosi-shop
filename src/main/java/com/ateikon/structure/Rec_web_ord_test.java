package com.ateikon.structure;

import java.sql.Timestamp;



public class Rec_web_ord_test extends Object {

    public Rec_web_ord_test() {

        super();
    }


    public String seStesso = "Rec_web_ord_test";

    public String rc          = "";
    public String message     = "";



    /****
        Properties 
    **/ 


    public long       tkordi = 0; 
    public String     cdtorve = ""; 
    public long       anno = 0; 
    public long       numord = 0; 
    public Timestamp  dtordi = null; 
    public Timestamp  dtfval = null; 
    public Timestamp  dtcons = null; 
    public long       tkoffe = 0; 
    public String     cdtofv = ""; 
    public long       annoof = 0; 
    public long       numoff = 0; 
    public String     cdrifo = ""; 
    public Timestamp  dtrior = null; 
    public String     cdnsri = ""; 
    public Timestamp  dtnsri = null; 
    public String     tkclie = ""; 
    public String     cdentc = ""; 
    public String     cdulic = ""; 
    public String     cdagen = ""; 
    public double     provv1_p = 0; 
    public double     provv1_v = 0; 
    public String     cdsuba = ""; 
    public double     provv2_p = 0; 
    public double     provv2_v = 0; 
    public String     tkproc1 = ""; 
    public String     cdentp1 = ""; 
    public String     tkproc2 = ""; 
    public String     cdentp2 = ""; 
    public String     cdtlis = ""; 
    public Timestamp  dtrlis = null; 
    public double     accred = 0; 
    public double     antici = 0; 
    public String     abibap = ""; 
    public String     cabbap = ""; 
    public String     vacodi = ""; 
    public String     cdclsc = ""; 
    public double     scrig1 = 0; 
    public double     scrig2 = 0; 
    public double     scrig3 = 0; 
    public double     scrig4 = 0; 
    public double     scrap1 = 0; 
    public double     scrap2 = 0; 
    public double     cotraq = 0; 
    public String     cdiva = ""; 
    public String     pgcodi = ""; 
    public String     dpcodi = ""; 
    public String     cddpag = ""; 
    public String     ffinme = ""; 
    public long       tgsca1 = 0; 
    public long       tgsca2 = 0; 
    public double     scocas = 0; 
    public String     cdtrgf = ""; 
    public String     cdtfat = ""; 
    public String     cdtdoc = ""; 
    public String     cdfisc = ""; 
    public String     cdrmpo = ""; 
    public String     cdtrme = ""; 
    public String     cdtaut = ""; 
    public String     fscagru = ""; 
    public String     cduldm = ""; 
    public String     cdentd = ""; 
    public String     ragcog_de = ""; 
    public String     indiri_de = ""; 
    public String     cap_de = ""; 
    public String     comune_de = ""; 
    public String     cdprov_de = ""; 
    public String     cdstat_de = ""; 
    public String     cdzone = ""; 
    public String     cdvett1 = ""; 
    public String     cdentv1 = ""; 
    public String     cdulv1 = ""; 
    public String     cdvett2 = ""; 
    public String     cdentv2 = ""; 
    public String     cdulv2 = ""; 
    public String     dsvett3 = ""; 
    public Timestamp  dtstco = null; 
    public String     fstcor = ""; 
    public String     fcorst = ""; 
    public String     fcouni = ""; 
    public String     fcator = ""; 
    public String     fposfm = ""; 
    public String     cdstab = ""; 
    public long       tkmaga = 0; 
    public String     cdling = ""; 
    public double     cambio = 0; 
    public String     cdazie = ""; 
    public String     nrcarta = ""; 
    public Timestamp  dtfivacarta = null; 
    public String     cdcarta = ""; 
    public String     vcmarca = ""; 
    public long       vcanno = 0; 
    public String     vcmodello = ""; 
    public String     vctpcar = ""; 
    public long       vcnporte = 0; 
    public String     cdlist = ""; 
    public String     ivato = ""; 
    public String     cdaut = ""; 
    public String     autcarta = ""; 
    public Timestamp  dtautcarta = null; 
    public String     tkvaglia = ""; 
    public double     imptrasp = 0; 
    public String     fannullato = ""; 
    public String     cdstato = ""; 
    public String     fmezzo = ""; 
    public String     funo = ""; 
    public String     cogn_p = ""; 
    public String     indirizzo_p = ""; 
    public String     localita_p = ""; 
    public String     cap_p = ""; 
    public String     provincia_p = ""; 
    public String     gior_ricmerci = ""; 
    public String     cddipa = ""; 
    public String     profil = ""; 
    public Timestamp  dtinse = null; 
    public Timestamp  dtulag = null; 
    public Timestamp  dtstpre = null; 
    public String     cdordi = ""; 
    public String     fmovmp = ""; 
    public String     cdtprov = ""; 
    public String     cdtdoc_prot = ""; 
    public String     dspag = ""; 
    public double     scval = 0; 
    public Timestamp  dtdeco = null; 
    public String     faspef = ""; 
    public double     speinc = 0; 
    public double     nraddimb = 0; 
    public double     addimb = 0; 
    public double     addtra = 0; 
    public double     nrpeso_l = 0; 
    public double     nrpeso_n = 0; 
    public double     nrtovo = 0; 
    public String     desbanc = ""; 
    public String     cdprel = ""; 
    public String     cdadde = ""; 
    public long       tmesc1 = 0; 
    public long       tmesc2 = 0; 
    public long       tgsc1m = 0; 
    public long       tgsc2m = 0; 
    public String     fgevaso = ""; 
    public String     cdtdoc_segue = ""; 
    public String     cdcatm = ""; 
    public double     provv1_p_var = 0; 
    public double     provv2_p_var = 0; 
    public double     provv1_v_var = 0; 
    public double     provv2_v_var = 0; 
    public String     cdpagame = ""; 
    public String     off_cortese_att = ""; 
    public String     off_persona_rif = ""; 
    public String     off_oggetto = ""; 
    public long       tkoffnote = 0; 
    public long       nrtoco = 0; 
    public String     cdaebe = ""; 
    public String     cdcllist = ""; 
    public String     cdclprc = ""; 
    public String     cdbanc = ""; 
    public double     totale_imp = 0; 
    public String     tkclie_fatt = ""; 
    public String     cdente_fatt = ""; 
    public String     cdunil_fatt = ""; 
    public Timestamp  dtlistval = null; 
    public Timestamp  dtcontrval = null; 
    public long       tkutente = 0; 
    public long       tkordigest = 0; 
    public String     cdordigest = ""; 
    public String     isscontiriga = ""; 
    public Timestamp  dtmailgest = null; 
    public String     cdpromo_m = ""; 
    public long       tksubutente = 0; 
    public long       tkrifoff = 0; 






    /****
        init (): Inizializza la struttrra  
    **/ 

    public void init() throws Exception{ 

        this.tkordi = 0; 
        this.cdtorve = ""; 
        this.anno = 0; 
        this.numord = 0; 
        this.dtordi = null; 
        this.dtfval = null; 
        this.dtcons = null; 
        this.tkoffe = 0; 
        this.cdtofv = ""; 
        this.annoof = 0; 
        this.numoff = 0; 
        this.cdrifo = ""; 
        this.dtrior = null; 
        this.cdnsri = ""; 
        this.dtnsri = null; 
        this.tkclie = ""; 
        this.cdentc = ""; 
        this.cdulic = ""; 
        this.cdagen = ""; 
        this.provv1_p = 0; 
        this.provv1_v = 0; 
        this.cdsuba = ""; 
        this.provv2_p = 0; 
        this.provv2_v = 0; 
        this.tkproc1 = ""; 
        this.cdentp1 = ""; 
        this.tkproc2 = ""; 
        this.cdentp2 = ""; 
        this.cdtlis = ""; 
        this.dtrlis = null; 
        this.accred = 0; 
        this.antici = 0; 
        this.abibap = ""; 
        this.cabbap = ""; 
        this.vacodi = ""; 
        this.cdclsc = ""; 
        this.scrig1 = 0; 
        this.scrig2 = 0; 
        this.scrig3 = 0; 
        this.scrig4 = 0; 
        this.scrap1 = 0; 
        this.scrap2 = 0; 
        this.cotraq = 0; 
        this.cdiva = ""; 
        this.pgcodi = ""; 
        this.dpcodi = ""; 
        this.cddpag = ""; 
        this.ffinme = ""; 
        this.tgsca1 = 0; 
        this.tgsca2 = 0; 
        this.scocas = 0; 
        this.cdtrgf = ""; 
        this.cdtfat = ""; 
        this.cdtdoc = ""; 
        this.cdfisc = ""; 
        this.cdrmpo = ""; 
        this.cdtrme = ""; 
        this.cdtaut = ""; 
        this.fscagru = ""; 
        this.cduldm = ""; 
        this.cdentd = ""; 
        this.ragcog_de = ""; 
        this.indiri_de = ""; 
        this.cap_de = ""; 
        this.comune_de = ""; 
        this.cdprov_de = ""; 
        this.cdstat_de = ""; 
        this.cdzone = ""; 
        this.cdvett1 = ""; 
        this.cdentv1 = ""; 
        this.cdulv1 = ""; 
        this.cdvett2 = ""; 
        this.cdentv2 = ""; 
        this.cdulv2 = ""; 
        this.dsvett3 = ""; 
        this.dtstco = null; 
        this.fstcor = ""; 
        this.fcorst = ""; 
        this.fcouni = ""; 
        this.fcator = ""; 
        this.fposfm = ""; 
        this.cdstab = ""; 
        this.tkmaga = 0; 
        this.cdling = ""; 
        this.cambio = 0; 
        this.cdazie = ""; 
        this.nrcarta = ""; 
        this.dtfivacarta = null; 
        this.cdcarta = ""; 
        this.vcmarca = ""; 
        this.vcanno = 0; 
        this.vcmodello = ""; 
        this.vctpcar = ""; 
        this.vcnporte = 0; 
        this.cdlist = ""; 
        this.ivato = ""; 
        this.cdaut = ""; 
        this.autcarta = ""; 
        this.dtautcarta = null; 
        this.tkvaglia = ""; 
        this.imptrasp = 0; 
        this.fannullato = ""; 
        this.cdstato = ""; 
        this.fmezzo = ""; 
        this.funo = ""; 
        this.cogn_p = ""; 
        this.indirizzo_p = ""; 
        this.localita_p = ""; 
        this.cap_p = ""; 
        this.provincia_p = ""; 
        this.gior_ricmerci = ""; 
        this.cddipa = ""; 
        this.profil = ""; 
        this.dtinse = null; 
        this.dtulag = null; 
        this.dtstpre = null; 
        this.cdordi = ""; 
        this.fmovmp = ""; 
        this.cdtprov = ""; 
        this.cdtdoc_prot = ""; 
        this.dspag = ""; 
        this.scval = 0; 
        this.dtdeco = null; 
        this.faspef = ""; 
        this.speinc = 0; 
        this.nraddimb = 0; 
        this.addimb = 0; 
        this.addtra = 0; 
        this.nrpeso_l = 0; 
        this.nrpeso_n = 0; 
        this.nrtovo = 0; 
        this.desbanc = ""; 
        this.cdprel = ""; 
        this.cdadde = ""; 
        this.tmesc1 = 0; 
        this.tmesc2 = 0; 
        this.tgsc1m = 0; 
        this.tgsc2m = 0; 
        this.fgevaso = ""; 
        this.cdtdoc_segue = ""; 
        this.cdcatm = ""; 
        this.provv1_p_var = 0; 
        this.provv2_p_var = 0; 
        this.provv1_v_var = 0; 
        this.provv2_v_var = 0; 
        this.cdpagame = ""; 
        this.off_cortese_att = ""; 
        this.off_persona_rif = ""; 
        this.off_oggetto = ""; 
        this.tkoffnote = 0; 
        this.nrtoco = 0; 
        this.cdaebe = ""; 
        this.cdcllist = ""; 
        this.cdclprc = ""; 
        this.cdbanc = ""; 
        this.totale_imp = 0; 
        this.tkclie_fatt = ""; 
        this.cdente_fatt = ""; 
        this.cdunil_fatt = ""; 
        this.dtlistval = null; 
        this.dtcontrval = null; 
        this.tkutente = 0; 
        this.tkordigest = 0; 
        this.cdordigest = ""; 
        this.isscontiriga = ""; 
        this.dtmailgest = null; 
        this.cdpromo_m = ""; 
        this.tksubutente = 0; 
        this.tkrifoff = 0; 

    }






    /****
        setResultSet: web_ord_test
    **/ 


    public int setResultSet ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkordi")!=null) this.tkordi = rs.getLong      ("tkordi"); 
        if (rs.getObject("cdtorve")!=null) this.cdtorve = rs.getString    ("cdtorve"); 
        if (rs.getObject("anno")!=null) this.anno = rs.getLong      ("anno"); 
        if (rs.getObject("numord")!=null) this.numord = rs.getLong      ("numord"); 
        if (rs.getObject("dtordi")!=null) this.dtordi = rs.getTimestamp ("dtordi"); 
        if (rs.getObject("dtfval")!=null) this.dtfval = rs.getTimestamp ("dtfval"); 
        if (rs.getObject("dtcons")!=null) this.dtcons = rs.getTimestamp ("dtcons"); 
        if (rs.getObject("tkoffe")!=null) this.tkoffe = rs.getLong      ("tkoffe"); 
        if (rs.getObject("cdtofv")!=null) this.cdtofv = rs.getString    ("cdtofv"); 
        if (rs.getObject("annoof")!=null) this.annoof = rs.getLong      ("annoof"); 
        if (rs.getObject("numoff")!=null) this.numoff = rs.getLong      ("numoff"); 
        if (rs.getObject("cdrifo")!=null) this.cdrifo = rs.getString    ("cdrifo"); 
        if (rs.getObject("dtrior")!=null) this.dtrior = rs.getTimestamp ("dtrior"); 
        if (rs.getObject("cdnsri")!=null) this.cdnsri = rs.getString    ("cdnsri"); 
        if (rs.getObject("dtnsri")!=null) this.dtnsri = rs.getTimestamp ("dtnsri"); 
        if (rs.getObject("tkclie")!=null) this.tkclie = rs.getString    ("tkclie"); 
        if (rs.getObject("cdentc")!=null) this.cdentc = rs.getString    ("cdentc"); 
        if (rs.getObject("cdulic")!=null) this.cdulic = rs.getString    ("cdulic"); 
        if (rs.getObject("cdagen")!=null) this.cdagen = rs.getString    ("cdagen"); 
        if (rs.getObject("provv1_p")!=null) this.provv1_p = rs.getDouble    ("provv1_p"); 
        if (rs.getObject("provv1_v")!=null) this.provv1_v = rs.getDouble    ("provv1_v"); 
        if (rs.getObject("cdsuba")!=null) this.cdsuba = rs.getString    ("cdsuba"); 
        if (rs.getObject("provv2_p")!=null) this.provv2_p = rs.getDouble    ("provv2_p"); 
        if (rs.getObject("provv2_v")!=null) this.provv2_v = rs.getDouble    ("provv2_v"); 
        if (rs.getObject("tkproc1")!=null) this.tkproc1 = rs.getString    ("tkproc1"); 
        if (rs.getObject("cdentp1")!=null) this.cdentp1 = rs.getString    ("cdentp1"); 
        if (rs.getObject("tkproc2")!=null) this.tkproc2 = rs.getString    ("tkproc2"); 
        if (rs.getObject("cdentp2")!=null) this.cdentp2 = rs.getString    ("cdentp2"); 
        if (rs.getObject("cdtlis")!=null) this.cdtlis = rs.getString    ("cdtlis"); 
        if (rs.getObject("dtrlis")!=null) this.dtrlis = rs.getTimestamp ("dtrlis"); 
        if (rs.getObject("accred")!=null) this.accred = rs.getDouble    ("accred"); 
        if (rs.getObject("antici")!=null) this.antici = rs.getDouble    ("antici"); 
        if (rs.getObject("abibap")!=null) this.abibap = rs.getString    ("abibap"); 
        if (rs.getObject("cabbap")!=null) this.cabbap = rs.getString    ("cabbap"); 
        if (rs.getObject("vacodi")!=null) this.vacodi = rs.getString    ("vacodi"); 
        if (rs.getObject("cdclsc")!=null) this.cdclsc = rs.getString    ("cdclsc"); 
        if (rs.getObject("scrig1")!=null) this.scrig1 = rs.getDouble    ("scrig1"); 
        if (rs.getObject("scrig2")!=null) this.scrig2 = rs.getDouble    ("scrig2"); 
        if (rs.getObject("scrig3")!=null) this.scrig3 = rs.getDouble    ("scrig3"); 
        if (rs.getObject("scrig4")!=null) this.scrig4 = rs.getDouble    ("scrig4"); 
        if (rs.getObject("scrap1")!=null) this.scrap1 = rs.getDouble    ("scrap1"); 
        if (rs.getObject("scrap2")!=null) this.scrap2 = rs.getDouble    ("scrap2"); 
        if (rs.getObject("cotraq")!=null) this.cotraq = rs.getDouble    ("cotraq"); 
        if (rs.getObject("cdiva")!=null) this.cdiva = rs.getString    ("cdiva"); 
        if (rs.getObject("pgcodi")!=null) this.pgcodi = rs.getString    ("pgcodi"); 
        if (rs.getObject("dpcodi")!=null) this.dpcodi = rs.getString    ("dpcodi"); 
        if (rs.getObject("cddpag")!=null) this.cddpag = rs.getString    ("cddpag"); 
        if (rs.getObject("ffinme")!=null) this.ffinme = rs.getString    ("ffinme"); 
        if (rs.getObject("tgsca1")!=null) this.tgsca1 = rs.getLong      ("tgsca1"); 
        if (rs.getObject("tgsca2")!=null) this.tgsca2 = rs.getLong      ("tgsca2"); 
        if (rs.getObject("scocas")!=null) this.scocas = rs.getDouble    ("scocas"); 
        if (rs.getObject("cdtrgf")!=null) this.cdtrgf = rs.getString    ("cdtrgf"); 
        if (rs.getObject("cdtfat")!=null) this.cdtfat = rs.getString    ("cdtfat"); 
        if (rs.getObject("cdtdoc")!=null) this.cdtdoc = rs.getString    ("cdtdoc"); 
        if (rs.getObject("cdfisc")!=null) this.cdfisc = rs.getString    ("cdfisc"); 
        if (rs.getObject("cdrmpo")!=null) this.cdrmpo = rs.getString    ("cdrmpo"); 
        if (rs.getObject("cdtrme")!=null) this.cdtrme = rs.getString    ("cdtrme"); 
        if (rs.getObject("cdtaut")!=null) this.cdtaut = rs.getString    ("cdtaut"); 
        if (rs.getObject("fscagru")!=null) this.fscagru = rs.getString    ("fscagru"); 
        if (rs.getObject("cduldm")!=null) this.cduldm = rs.getString    ("cduldm"); 
        if (rs.getObject("cdentd")!=null) this.cdentd = rs.getString    ("cdentd"); 
        if (rs.getObject("ragcog_de")!=null) this.ragcog_de = rs.getString    ("ragcog_de"); 
        if (rs.getObject("indiri_de")!=null) this.indiri_de = rs.getString    ("indiri_de"); 
        if (rs.getObject("cap_de")!=null) this.cap_de = rs.getString    ("cap_de"); 
        if (rs.getObject("comune_de")!=null) this.comune_de = rs.getString    ("comune_de"); 
        if (rs.getObject("cdprov_de")!=null) this.cdprov_de = rs.getString    ("cdprov_de"); 
        if (rs.getObject("cdstat_de")!=null) this.cdstat_de = rs.getString    ("cdstat_de"); 
        if (rs.getObject("cdzone")!=null) this.cdzone = rs.getString    ("cdzone"); 
        if (rs.getObject("cdvett1")!=null) this.cdvett1 = rs.getString    ("cdvett1"); 
        if (rs.getObject("cdentv1")!=null) this.cdentv1 = rs.getString    ("cdentv1"); 
        if (rs.getObject("cdulv1")!=null) this.cdulv1 = rs.getString    ("cdulv1"); 
        if (rs.getObject("cdvett2")!=null) this.cdvett2 = rs.getString    ("cdvett2"); 
        if (rs.getObject("cdentv2")!=null) this.cdentv2 = rs.getString    ("cdentv2"); 
        if (rs.getObject("cdulv2")!=null) this.cdulv2 = rs.getString    ("cdulv2"); 
        if (rs.getObject("dsvett3")!=null) this.dsvett3 = rs.getString    ("dsvett3"); 
        if (rs.getObject("dtstco")!=null) this.dtstco = rs.getTimestamp ("dtstco"); 
        if (rs.getObject("fstcor")!=null) this.fstcor = rs.getString    ("fstcor"); 
        if (rs.getObject("fcorst")!=null) this.fcorst = rs.getString    ("fcorst"); 
        if (rs.getObject("fcouni")!=null) this.fcouni = rs.getString    ("fcouni"); 
        if (rs.getObject("fcator")!=null) this.fcator = rs.getString    ("fcator"); 
        if (rs.getObject("fposfm")!=null) this.fposfm = rs.getString    ("fposfm"); 
        if (rs.getObject("cdstab")!=null) this.cdstab = rs.getString    ("cdstab"); 
        if (rs.getObject("tkmaga")!=null) this.tkmaga = rs.getLong      ("tkmaga"); 
        if (rs.getObject("cdling")!=null) this.cdling = rs.getString    ("cdling"); 
        if (rs.getObject("cambio")!=null) this.cambio = rs.getDouble    ("cambio"); 
        if (rs.getObject("cdazie")!=null) this.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("nrcarta")!=null) this.nrcarta = rs.getString    ("nrcarta"); 
        if (rs.getObject("dtfivacarta")!=null) this.dtfivacarta = rs.getTimestamp ("dtfivacarta"); 
        if (rs.getObject("cdcarta")!=null) this.cdcarta = rs.getString    ("cdcarta"); 
        if (rs.getObject("vcmarca")!=null) this.vcmarca = rs.getString    ("vcmarca"); 
        if (rs.getObject("vcanno")!=null) this.vcanno = rs.getLong      ("vcanno"); 
        if (rs.getObject("vcmodello")!=null) this.vcmodello = rs.getString    ("vcmodello"); 
        if (rs.getObject("vctpcar")!=null) this.vctpcar = rs.getString    ("vctpcar"); 
        if (rs.getObject("vcnporte")!=null) this.vcnporte = rs.getLong      ("vcnporte"); 
        if (rs.getObject("cdlist")!=null) this.cdlist = rs.getString    ("cdlist"); 
        if (rs.getObject("ivato")!=null) this.ivato = rs.getString    ("ivato"); 
        if (rs.getObject("cdaut")!=null) this.cdaut = rs.getString    ("cdaut"); 
        if (rs.getObject("autcarta")!=null) this.autcarta = rs.getString    ("autcarta"); 
        if (rs.getObject("dtautcarta")!=null) this.dtautcarta = rs.getTimestamp ("dtautcarta"); 
        if (rs.getObject("tkvaglia")!=null) this.tkvaglia = rs.getString    ("tkvaglia"); 
        if (rs.getObject("imptrasp")!=null) this.imptrasp = rs.getDouble    ("imptrasp"); 
        if (rs.getObject("fannullato")!=null) this.fannullato = rs.getString    ("fannullato"); 
        if (rs.getObject("cdstato")!=null) this.cdstato = rs.getString    ("cdstato"); 
        if (rs.getObject("fmezzo")!=null) this.fmezzo = rs.getString    ("fmezzo"); 
        if (rs.getObject("funo")!=null) this.funo = rs.getString    ("funo"); 
        if (rs.getObject("cogn_p")!=null) this.cogn_p = rs.getString    ("cogn_p"); 
        if (rs.getObject("indirizzo_p")!=null) this.indirizzo_p = rs.getString    ("indirizzo_p"); 
        if (rs.getObject("localita_p")!=null) this.localita_p = rs.getString    ("localita_p"); 
        if (rs.getObject("cap_p")!=null) this.cap_p = rs.getString    ("cap_p"); 
        if (rs.getObject("provincia_p")!=null) this.provincia_p = rs.getString    ("provincia_p"); 
        if (rs.getObject("gior_ricmerci")!=null) this.gior_ricmerci = rs.getString    ("gior_ricmerci"); 
        if (rs.getObject("cddipa")!=null) this.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) this.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) this.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) this.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("dtstpre")!=null) this.dtstpre = rs.getTimestamp ("dtstpre"); 
        if (rs.getObject("cdordi")!=null) this.cdordi = rs.getString    ("cdordi"); 
        if (rs.getObject("fmovmp")!=null) this.fmovmp = rs.getString    ("fmovmp"); 
        if (rs.getObject("cdtprov")!=null) this.cdtprov = rs.getString    ("cdtprov"); 
        if (rs.getObject("cdtdoc_prot")!=null) this.cdtdoc_prot = rs.getString    ("cdtdoc_prot"); 
        if (rs.getObject("dspag")!=null) this.dspag = rs.getString    ("dspag"); 
        if (rs.getObject("scval")!=null) this.scval = rs.getDouble    ("scval"); 
        if (rs.getObject("dtdeco")!=null) this.dtdeco = rs.getTimestamp ("dtdeco"); 
        if (rs.getObject("faspef")!=null) this.faspef = rs.getString    ("faspef"); 
        if (rs.getObject("speinc")!=null) this.speinc = rs.getDouble    ("speinc"); 
        if (rs.getObject("nraddimb")!=null) this.nraddimb = rs.getDouble    ("nraddimb"); 
        if (rs.getObject("addimb")!=null) this.addimb = rs.getDouble    ("addimb"); 
        if (rs.getObject("addtra")!=null) this.addtra = rs.getDouble    ("addtra"); 
        if (rs.getObject("nrpeso_l")!=null) this.nrpeso_l = rs.getDouble    ("nrpeso_l"); 
        if (rs.getObject("nrpeso_n")!=null) this.nrpeso_n = rs.getDouble    ("nrpeso_n"); 
        if (rs.getObject("nrtovo")!=null) this.nrtovo = rs.getDouble    ("nrtovo"); 
        if (rs.getObject("desbanc")!=null) this.desbanc = rs.getString    ("desbanc"); 
        if (rs.getObject("cdprel")!=null) this.cdprel = rs.getString    ("cdprel"); 
        if (rs.getObject("cdadde")!=null) this.cdadde = rs.getString    ("cdadde"); 
        if (rs.getObject("tmesc1")!=null) this.tmesc1 = rs.getLong      ("tmesc1"); 
        if (rs.getObject("tmesc2")!=null) this.tmesc2 = rs.getLong      ("tmesc2"); 
        if (rs.getObject("tgsc1m")!=null) this.tgsc1m = rs.getLong      ("tgsc1m"); 
        if (rs.getObject("tgsc2m")!=null) this.tgsc2m = rs.getLong      ("tgsc2m"); 
        if (rs.getObject("fgevaso")!=null) this.fgevaso = rs.getString    ("fgevaso"); 
        if (rs.getObject("cdtdoc_segue")!=null) this.cdtdoc_segue = rs.getString    ("cdtdoc_segue"); 
        if (rs.getObject("cdcatm")!=null) this.cdcatm = rs.getString    ("cdcatm"); 
        if (rs.getObject("provv1_p_var")!=null) this.provv1_p_var = rs.getDouble    ("provv1_p_var"); 
        if (rs.getObject("provv2_p_var")!=null) this.provv2_p_var = rs.getDouble    ("provv2_p_var"); 
        if (rs.getObject("provv1_v_var")!=null) this.provv1_v_var = rs.getDouble    ("provv1_v_var"); 
        if (rs.getObject("provv2_v_var")!=null) this.provv2_v_var = rs.getDouble    ("provv2_v_var"); 
        if (rs.getObject("cdpagame")!=null) this.cdpagame = rs.getString    ("cdpagame"); 
        if (rs.getObject("off_cortese_att")!=null) this.off_cortese_att = rs.getString    ("off_cortese_att"); 
        if (rs.getObject("off_persona_rif")!=null) this.off_persona_rif = rs.getString    ("off_persona_rif"); 
        if (rs.getObject("off_oggetto")!=null) this.off_oggetto = rs.getString    ("off_oggetto"); 
        if (rs.getObject("tkoffnote")!=null) this.tkoffnote = rs.getLong      ("tkoffnote"); 
        if (rs.getObject("nrtoco")!=null) this.nrtoco = rs.getLong      ("nrtoco"); 
        if (rs.getObject("cdaebe")!=null) this.cdaebe = rs.getString    ("cdaebe"); 
        if (rs.getObject("cdcllist")!=null) this.cdcllist = rs.getString    ("cdcllist"); 
        if (rs.getObject("cdclprc")!=null) this.cdclprc = rs.getString    ("cdclprc"); 
        if (rs.getObject("cdbanc")!=null) this.cdbanc = rs.getString    ("cdbanc"); 
        if (rs.getObject("totale_imp")!=null) this.totale_imp = rs.getDouble    ("totale_imp"); 
        if (rs.getObject("tkclie_fatt")!=null) this.tkclie_fatt = rs.getString    ("tkclie_fatt"); 
        if (rs.getObject("cdente_fatt")!=null) this.cdente_fatt = rs.getString    ("cdente_fatt"); 
        if (rs.getObject("cdunil_fatt")!=null) this.cdunil_fatt = rs.getString    ("cdunil_fatt"); 
        if (rs.getObject("dtlistval")!=null) this.dtlistval = rs.getTimestamp ("dtlistval"); 
        if (rs.getObject("dtcontrval")!=null) this.dtcontrval = rs.getTimestamp ("dtcontrval"); 
        if (rs.getObject("tkutente")!=null) this.tkutente = rs.getLong      ("tkutente"); 
        if (rs.getObject("tkordigest")!=null) this.tkordigest = rs.getLong      ("tkordigest"); 
        if (rs.getObject("cdordigest")!=null) this.cdordigest = rs.getString    ("cdordigest"); 
        //EAR 20110707
        if (rs.getObject("tksubutente")!=null) this.tksubutente = rs.getLong      ("tksubutente"); 
        if (rs.getObject("isscontiriga")!=null) this.isscontiriga = rs.getString    ("isscontiriga"); 
        if (rs.getObject("dtmailgest")!=null) this.dtmailgest = rs.getTimestamp ("dtmailgest"); 
        if (rs.getObject("cdpromo_m")!=null) this.cdpromo_m = rs.getString    ("cdpromo_m"); 
        if (rs.getObject("tkrifoff")!=null) this.tkrifoff = rs.getLong      ("tkrifoff"); 

        return 1;
    }




    /****
        setResultSet_key: web_ord_test
    **/ 


    public int setResultSet_key ( java.sql.ResultSet rs) throws Exception {


        this.init();

        if (rs == null) return 0;
        if (rs.getObject("tkordi")!=null) this.tkordi = rs.getLong      ("tkordi"); 

        return 1;
    }




    /****
        trim(): web_ord_test
    **/ 


    public void trim( ) throws Exception {

        this.cdtorve = this.cdtorve.trim(); 
        this.cdtofv = this.cdtofv.trim(); 
        this.cdrifo = this.cdrifo.trim(); 
        this.cdnsri = this.cdnsri.trim(); 
        this.tkclie = this.tkclie.trim(); 
        this.cdentc = this.cdentc.trim(); 
        this.cdulic = this.cdulic.trim(); 
        this.cdagen = this.cdagen.trim(); 
        this.cdsuba = this.cdsuba.trim(); 
        this.tkproc1 = this.tkproc1.trim(); 
        this.cdentp1 = this.cdentp1.trim(); 
        this.tkproc2 = this.tkproc2.trim(); 
        this.cdentp2 = this.cdentp2.trim(); 
        this.cdtlis = this.cdtlis.trim(); 
        this.abibap = this.abibap.trim(); 
        this.cabbap = this.cabbap.trim(); 
        this.vacodi = this.vacodi.trim(); 
        this.cdclsc = this.cdclsc.trim(); 
        this.cdiva = this.cdiva.trim(); 
        this.pgcodi = this.pgcodi.trim(); 
        this.dpcodi = this.dpcodi.trim(); 
        this.cddpag = this.cddpag.trim(); 
        this.ffinme = this.ffinme.trim(); 
        this.cdtrgf = this.cdtrgf.trim(); 
        this.cdtfat = this.cdtfat.trim(); 
        this.cdtdoc = this.cdtdoc.trim(); 
        this.cdfisc = this.cdfisc.trim(); 
        this.cdrmpo = this.cdrmpo.trim(); 
        this.cdtrme = this.cdtrme.trim(); 
        this.cdtaut = this.cdtaut.trim(); 
        this.fscagru = this.fscagru.trim(); 
        this.cduldm = this.cduldm.trim(); 
        this.cdentd = this.cdentd.trim(); 
        this.ragcog_de = this.ragcog_de.trim(); 
        this.indiri_de = this.indiri_de.trim(); 
        this.cap_de = this.cap_de.trim(); 
        this.comune_de = this.comune_de.trim(); 
        this.cdprov_de = this.cdprov_de.trim(); 
        this.cdstat_de = this.cdstat_de.trim(); 
        this.cdzone = this.cdzone.trim(); 
        this.cdvett1 = this.cdvett1.trim(); 
        this.cdentv1 = this.cdentv1.trim(); 
        this.cdulv1 = this.cdulv1.trim(); 
        this.cdvett2 = this.cdvett2.trim(); 
        this.cdentv2 = this.cdentv2.trim(); 
        this.cdulv2 = this.cdulv2.trim(); 
        this.dsvett3 = this.dsvett3.trim(); 
        this.fstcor = this.fstcor.trim(); 
        this.fcorst = this.fcorst.trim(); 
        this.fcouni = this.fcouni.trim(); 
        this.fcator = this.fcator.trim(); 
        this.fposfm = this.fposfm.trim(); 
        this.cdstab = this.cdstab.trim(); 
        this.cdling = this.cdling.trim(); 
        this.cdazie = this.cdazie.trim(); 
        this.nrcarta = this.nrcarta.trim(); 
        this.cdcarta = this.cdcarta.trim(); 
        this.vcmarca = this.vcmarca.trim(); 
        this.vcmodello = this.vcmodello.trim(); 
        this.vctpcar = this.vctpcar.trim(); 
        this.cdlist = this.cdlist.trim(); 
        this.ivato = this.ivato.trim(); 
        this.cdaut = this.cdaut.trim(); 
        this.autcarta = this.autcarta.trim(); 
        this.tkvaglia = this.tkvaglia.trim(); 
        this.fannullato = this.fannullato.trim(); 
        this.cdstato = this.cdstato.trim(); 
        this.fmezzo = this.fmezzo.trim(); 
        this.funo = this.funo.trim(); 
        this.cogn_p = this.cogn_p.trim(); 
        this.indirizzo_p = this.indirizzo_p.trim(); 
        this.localita_p = this.localita_p.trim(); 
        this.cap_p = this.cap_p.trim(); 
        this.provincia_p = this.provincia_p.trim(); 
        this.gior_ricmerci = this.gior_ricmerci.trim(); 
        this.cddipa = this.cddipa.trim(); 
        this.profil = this.profil.trim(); 
        this.cdordi = this.cdordi.trim(); 
        this.fmovmp = this.fmovmp.trim(); 
        this.cdtprov = this.cdtprov.trim(); 
        this.cdtdoc_prot = this.cdtdoc_prot.trim(); 
        this.dspag = this.dspag.trim(); 
        this.faspef = this.faspef.trim(); 
        this.desbanc = this.desbanc.trim(); 
        this.cdprel = this.cdprel.trim(); 
        this.cdadde = this.cdadde.trim(); 
        this.fgevaso = this.fgevaso.trim(); 
        this.cdtdoc_segue = this.cdtdoc_segue.trim(); 
        this.cdcatm = this.cdcatm.trim(); 
        this.cdpagame = this.cdpagame.trim(); 
        this.off_cortese_att = this.off_cortese_att.trim(); 
        this.off_persona_rif = this.off_persona_rif.trim(); 
        this.off_oggetto = this.off_oggetto.trim(); 
        this.cdaebe = this.cdaebe.trim(); 
        this.cdcllist = this.cdcllist.trim(); 
        this.cdclprc = this.cdclprc.trim(); 
        this.cdbanc = this.cdbanc.trim(); 
        this.tkclie_fatt = this.tkclie_fatt.trim(); 
        this.cdente_fatt = this.cdente_fatt.trim(); 
        this.cdunil_fatt = this.cdunil_fatt.trim(); 
        this.cdordigest = this.cdordigest.trim(); 
        this.isscontiriga = this.isscontiriga.trim(); 
		this.cdpromo_m = this.cdpromo_m.trim(); 

    }





}

