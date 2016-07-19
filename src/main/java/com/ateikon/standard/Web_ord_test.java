package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Web_ord_test extends Atk_sql {

    public Web_ord_test() {

        super();
    }




    /****
        getAll: web_ord_test
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.web_ord_test  \n";

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: web_ord_test
    **/ 

    public ResultSet getKey( long       tkordi
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.web_ord_test  \n";
        l_query  += "  where tkordi = ? \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setLong      (ind, tkordi); ind += 1;

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: web_ord_test
    **/ 


    public com.ateikon.structure.Rec_web_ord_test  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_web_ord_test lstr = new com.ateikon.structure.Rec_web_ord_test();

        if (rs == null) return lstr;
        if (rs.getObject("tkordi")!=null) lstr.tkordi = rs.getLong      ("tkordi"); 
        if (rs.getObject("cdtorve")!=null) lstr.cdtorve = rs.getString    ("cdtorve"); 
        if (rs.getObject("anno")!=null) lstr.anno = rs.getLong      ("anno"); 
        if (rs.getObject("numord")!=null) lstr.numord = rs.getLong      ("numord"); 
        if (rs.getObject("dtordi")!=null) lstr.dtordi = rs.getTimestamp ("dtordi"); 
        if (rs.getObject("dtfval")!=null) lstr.dtfval = rs.getTimestamp ("dtfval"); 
        if (rs.getObject("dtcons")!=null) lstr.dtcons = rs.getTimestamp ("dtcons"); 
        if (rs.getObject("tkoffe")!=null) lstr.tkoffe = rs.getLong      ("tkoffe"); 
        if (rs.getObject("cdtofv")!=null) lstr.cdtofv = rs.getString    ("cdtofv"); 
        if (rs.getObject("annoof")!=null) lstr.annoof = rs.getLong      ("annoof"); 
        if (rs.getObject("numoff")!=null) lstr.numoff = rs.getLong      ("numoff"); 
        if (rs.getObject("cdrifo")!=null) lstr.cdrifo = rs.getString    ("cdrifo"); 
        if (rs.getObject("dtrior")!=null) lstr.dtrior = rs.getTimestamp ("dtrior"); 
        if (rs.getObject("cdnsri")!=null) lstr.cdnsri = rs.getString    ("cdnsri"); 
        if (rs.getObject("dtnsri")!=null) lstr.dtnsri = rs.getTimestamp ("dtnsri"); 
        if (rs.getObject("tkclie")!=null) lstr.tkclie = rs.getString    ("tkclie"); 
        if (rs.getObject("cdentc")!=null) lstr.cdentc = rs.getString    ("cdentc"); 
        if (rs.getObject("cdulic")!=null) lstr.cdulic = rs.getString    ("cdulic"); 
        if (rs.getObject("cdagen")!=null) lstr.cdagen = rs.getString    ("cdagen"); 
        if (rs.getObject("provv1_p")!=null) lstr.provv1_p = rs.getDouble    ("provv1_p"); 
        if (rs.getObject("provv1_v")!=null) lstr.provv1_v = rs.getDouble    ("provv1_v"); 
        if (rs.getObject("cdsuba")!=null) lstr.cdsuba = rs.getString    ("cdsuba"); 
        if (rs.getObject("provv2_p")!=null) lstr.provv2_p = rs.getDouble    ("provv2_p"); 
        if (rs.getObject("provv2_v")!=null) lstr.provv2_v = rs.getDouble    ("provv2_v"); 
        if (rs.getObject("tkproc1")!=null) lstr.tkproc1 = rs.getString    ("tkproc1"); 
        if (rs.getObject("cdentp1")!=null) lstr.cdentp1 = rs.getString    ("cdentp1"); 
        if (rs.getObject("tkproc2")!=null) lstr.tkproc2 = rs.getString    ("tkproc2"); 
        if (rs.getObject("cdentp2")!=null) lstr.cdentp2 = rs.getString    ("cdentp2"); 
        if (rs.getObject("cdtlis")!=null) lstr.cdtlis = rs.getString    ("cdtlis"); 
        if (rs.getObject("dtrlis")!=null) lstr.dtrlis = rs.getTimestamp ("dtrlis"); 
        if (rs.getObject("accred")!=null) lstr.accred = rs.getDouble    ("accred"); 
        if (rs.getObject("antici")!=null) lstr.antici = rs.getDouble    ("antici"); 
        if (rs.getObject("abibap")!=null) lstr.abibap = rs.getString    ("abibap"); 
        if (rs.getObject("cabbap")!=null) lstr.cabbap = rs.getString    ("cabbap"); 
        if (rs.getObject("vacodi")!=null) lstr.vacodi = rs.getString    ("vacodi"); 
        if (rs.getObject("cdclsc")!=null) lstr.cdclsc = rs.getString    ("cdclsc"); 
        if (rs.getObject("scrig1")!=null) lstr.scrig1 = rs.getDouble    ("scrig1"); 
        if (rs.getObject("scrig2")!=null) lstr.scrig2 = rs.getDouble    ("scrig2"); 
        if (rs.getObject("scrig3")!=null) lstr.scrig3 = rs.getDouble    ("scrig3"); 
        if (rs.getObject("scrig4")!=null) lstr.scrig4 = rs.getDouble    ("scrig4"); 
        if (rs.getObject("scrap1")!=null) lstr.scrap1 = rs.getDouble    ("scrap1"); 
        if (rs.getObject("scrap2")!=null) lstr.scrap2 = rs.getDouble    ("scrap2"); 
        if (rs.getObject("cotraq")!=null) lstr.cotraq = rs.getDouble    ("cotraq"); 
        if (rs.getObject("cdiva")!=null) lstr.cdiva = rs.getString    ("cdiva"); 
        if (rs.getObject("pgcodi")!=null) lstr.pgcodi = rs.getString    ("pgcodi"); 
        if (rs.getObject("dpcodi")!=null) lstr.dpcodi = rs.getString    ("dpcodi"); 
        if (rs.getObject("cddpag")!=null) lstr.cddpag = rs.getString    ("cddpag"); 
        if (rs.getObject("ffinme")!=null) lstr.ffinme = rs.getString    ("ffinme"); 
        if (rs.getObject("tgsca1")!=null) lstr.tgsca1 = rs.getLong      ("tgsca1"); 
        if (rs.getObject("tgsca2")!=null) lstr.tgsca2 = rs.getLong      ("tgsca2"); 
        if (rs.getObject("scocas")!=null) lstr.scocas = rs.getDouble    ("scocas"); 
        if (rs.getObject("cdtrgf")!=null) lstr.cdtrgf = rs.getString    ("cdtrgf"); 
        if (rs.getObject("cdtfat")!=null) lstr.cdtfat = rs.getString    ("cdtfat"); 
        if (rs.getObject("cdtdoc")!=null) lstr.cdtdoc = rs.getString    ("cdtdoc"); 
        if (rs.getObject("cdfisc")!=null) lstr.cdfisc = rs.getString    ("cdfisc"); 
        if (rs.getObject("cdrmpo")!=null) lstr.cdrmpo = rs.getString    ("cdrmpo"); 
        if (rs.getObject("cdtrme")!=null) lstr.cdtrme = rs.getString    ("cdtrme"); 
        if (rs.getObject("cdtaut")!=null) lstr.cdtaut = rs.getString    ("cdtaut"); 
        if (rs.getObject("fscagru")!=null) lstr.fscagru = rs.getString    ("fscagru"); 
        if (rs.getObject("cduldm")!=null) lstr.cduldm = rs.getString    ("cduldm"); 
        if (rs.getObject("cdentd")!=null) lstr.cdentd = rs.getString    ("cdentd"); 
        if (rs.getObject("ragcog_de")!=null) lstr.ragcog_de = rs.getString    ("ragcog_de"); 
        if (rs.getObject("indiri_de")!=null) lstr.indiri_de = rs.getString    ("indiri_de"); 
        if (rs.getObject("cap_de")!=null) lstr.cap_de = rs.getString    ("cap_de"); 
        if (rs.getObject("comune_de")!=null) lstr.comune_de = rs.getString    ("comune_de"); 
        if (rs.getObject("cdprov_de")!=null) lstr.cdprov_de = rs.getString    ("cdprov_de"); 
        if (rs.getObject("cdstat_de")!=null) lstr.cdstat_de = rs.getString    ("cdstat_de"); 
        if (rs.getObject("cdzone")!=null) lstr.cdzone = rs.getString    ("cdzone"); 
        if (rs.getObject("cdvett1")!=null) lstr.cdvett1 = rs.getString    ("cdvett1"); 
        if (rs.getObject("cdentv1")!=null) lstr.cdentv1 = rs.getString    ("cdentv1"); 
        if (rs.getObject("cdulv1")!=null) lstr.cdulv1 = rs.getString    ("cdulv1"); 
        if (rs.getObject("cdvett2")!=null) lstr.cdvett2 = rs.getString    ("cdvett2"); 
        if (rs.getObject("cdentv2")!=null) lstr.cdentv2 = rs.getString    ("cdentv2"); 
        if (rs.getObject("cdulv2")!=null) lstr.cdulv2 = rs.getString    ("cdulv2"); 
        if (rs.getObject("dsvett3")!=null) lstr.dsvett3 = rs.getString    ("dsvett3"); 
        if (rs.getObject("dtstco")!=null) lstr.dtstco = rs.getTimestamp ("dtstco"); 
        if (rs.getObject("fstcor")!=null) lstr.fstcor = rs.getString    ("fstcor"); 
        if (rs.getObject("fcorst")!=null) lstr.fcorst = rs.getString    ("fcorst"); 
        if (rs.getObject("fcouni")!=null) lstr.fcouni = rs.getString    ("fcouni"); 
        if (rs.getObject("fcator")!=null) lstr.fcator = rs.getString    ("fcator"); 
        if (rs.getObject("fposfm")!=null) lstr.fposfm = rs.getString    ("fposfm"); 
        if (rs.getObject("cdstab")!=null) lstr.cdstab = rs.getString    ("cdstab"); 
        if (rs.getObject("tkmaga")!=null) lstr.tkmaga = rs.getLong      ("tkmaga"); 
        if (rs.getObject("cdling")!=null) lstr.cdling = rs.getString    ("cdling"); 
        if (rs.getObject("cambio")!=null) lstr.cambio = rs.getDouble    ("cambio"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("nrcarta")!=null) lstr.nrcarta = rs.getString    ("nrcarta"); 
        if (rs.getObject("dtfivacarta")!=null) lstr.dtfivacarta = rs.getTimestamp ("dtfivacarta"); 
        if (rs.getObject("cdcarta")!=null) lstr.cdcarta = rs.getString    ("cdcarta"); 
        if (rs.getObject("vcmarca")!=null) lstr.vcmarca = rs.getString    ("vcmarca"); 
        if (rs.getObject("vcanno")!=null) lstr.vcanno = rs.getLong      ("vcanno"); 
        if (rs.getObject("vcmodello")!=null) lstr.vcmodello = rs.getString    ("vcmodello"); 
        if (rs.getObject("vctpcar")!=null) lstr.vctpcar = rs.getString    ("vctpcar"); 
        if (rs.getObject("vcnporte")!=null) lstr.vcnporte = rs.getLong      ("vcnporte"); 
        if (rs.getObject("cdlist")!=null) lstr.cdlist = rs.getString    ("cdlist"); 
        if (rs.getObject("ivato")!=null) lstr.ivato = rs.getString    ("ivato"); 
        if (rs.getObject("cdaut")!=null) lstr.cdaut = rs.getString    ("cdaut"); 
        if (rs.getObject("autcarta")!=null) lstr.autcarta = rs.getString    ("autcarta"); 
        if (rs.getObject("dtautcarta")!=null) lstr.dtautcarta = rs.getTimestamp ("dtautcarta"); 
        if (rs.getObject("tkvaglia")!=null) lstr.tkvaglia = rs.getString    ("tkvaglia"); 
        if (rs.getObject("imptrasp")!=null) lstr.imptrasp = rs.getDouble    ("imptrasp"); 
        if (rs.getObject("fannullato")!=null) lstr.fannullato = rs.getString    ("fannullato"); 
        if (rs.getObject("cdstato")!=null) lstr.cdstato = rs.getString    ("cdstato"); 
        if (rs.getObject("fmezzo")!=null) lstr.fmezzo = rs.getString    ("fmezzo"); 
        if (rs.getObject("funo")!=null) lstr.funo = rs.getString    ("funo"); 
        if (rs.getObject("cogn_p")!=null) lstr.cogn_p = rs.getString    ("cogn_p"); 
        if (rs.getObject("indirizzo_p")!=null) lstr.indirizzo_p = rs.getString    ("indirizzo_p"); 
        if (rs.getObject("localita_p")!=null) lstr.localita_p = rs.getString    ("localita_p"); 
        if (rs.getObject("cap_p")!=null) lstr.cap_p = rs.getString    ("cap_p"); 
        if (rs.getObject("provincia_p")!=null) lstr.provincia_p = rs.getString    ("provincia_p"); 
        if (rs.getObject("gior_ricmerci")!=null) lstr.gior_ricmerci = rs.getString    ("gior_ricmerci"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("dtstpre")!=null) lstr.dtstpre = rs.getTimestamp ("dtstpre"); 
        if (rs.getObject("cdordi")!=null) lstr.cdordi = rs.getString    ("cdordi"); 
        if (rs.getObject("fmovmp")!=null) lstr.fmovmp = rs.getString    ("fmovmp"); 
        if (rs.getObject("cdtprov")!=null) lstr.cdtprov = rs.getString    ("cdtprov"); 
        if (rs.getObject("cdtdoc_prot")!=null) lstr.cdtdoc_prot = rs.getString    ("cdtdoc_prot"); 
        if (rs.getObject("dspag")!=null) lstr.dspag = rs.getString    ("dspag"); 
        if (rs.getObject("scval")!=null) lstr.scval = rs.getDouble    ("scval"); 
        if (rs.getObject("dtdeco")!=null) lstr.dtdeco = rs.getTimestamp ("dtdeco"); 
        if (rs.getObject("faspef")!=null) lstr.faspef = rs.getString    ("faspef"); 
        if (rs.getObject("speinc")!=null) lstr.speinc = rs.getDouble    ("speinc"); 
        if (rs.getObject("nraddimb")!=null) lstr.nraddimb = rs.getDouble    ("nraddimb"); 
        if (rs.getObject("addimb")!=null) lstr.addimb = rs.getDouble    ("addimb"); 
        if (rs.getObject("addtra")!=null) lstr.addtra = rs.getDouble    ("addtra"); 
        if (rs.getObject("nrpeso_l")!=null) lstr.nrpeso_l = rs.getDouble    ("nrpeso_l"); 
        if (rs.getObject("nrpeso_n")!=null) lstr.nrpeso_n = rs.getDouble    ("nrpeso_n"); 
        if (rs.getObject("nrtovo")!=null) lstr.nrtovo = rs.getDouble    ("nrtovo"); 
        if (rs.getObject("desbanc")!=null) lstr.desbanc = rs.getString    ("desbanc"); 
        if (rs.getObject("cdprel")!=null) lstr.cdprel = rs.getString    ("cdprel"); 
        if (rs.getObject("cdadde")!=null) lstr.cdadde = rs.getString    ("cdadde"); 
        if (rs.getObject("tmesc1")!=null) lstr.tmesc1 = rs.getLong      ("tmesc1"); 
        if (rs.getObject("tmesc2")!=null) lstr.tmesc2 = rs.getLong      ("tmesc2"); 
        if (rs.getObject("tgsc1m")!=null) lstr.tgsc1m = rs.getLong      ("tgsc1m"); 
        if (rs.getObject("tgsc2m")!=null) lstr.tgsc2m = rs.getLong      ("tgsc2m"); 
        if (rs.getObject("fgevaso")!=null) lstr.fgevaso = rs.getString    ("fgevaso"); 
        if (rs.getObject("cdtdoc_segue")!=null) lstr.cdtdoc_segue = rs.getString    ("cdtdoc_segue"); 
        if (rs.getObject("cdcatm")!=null) lstr.cdcatm = rs.getString    ("cdcatm"); 
        if (rs.getObject("provv1_p_var")!=null) lstr.provv1_p_var = rs.getDouble    ("provv1_p_var"); 
        if (rs.getObject("provv2_p_var")!=null) lstr.provv2_p_var = rs.getDouble    ("provv2_p_var"); 
        if (rs.getObject("provv1_v_var")!=null) lstr.provv1_v_var = rs.getDouble    ("provv1_v_var"); 
        if (rs.getObject("provv2_v_var")!=null) lstr.provv2_v_var = rs.getDouble    ("provv2_v_var"); 
        if (rs.getObject("cdpagame")!=null) lstr.cdpagame = rs.getString    ("cdpagame"); 
        if (rs.getObject("off_cortese_att")!=null) lstr.off_cortese_att = rs.getString    ("off_cortese_att"); 
        if (rs.getObject("off_persona_rif")!=null) lstr.off_persona_rif = rs.getString    ("off_persona_rif"); 
        if (rs.getObject("off_oggetto")!=null) lstr.off_oggetto = rs.getString    ("off_oggetto"); 
        if (rs.getObject("tkoffnote")!=null) lstr.tkoffnote = rs.getLong      ("tkoffnote"); 
        if (rs.getObject("nrtoco")!=null) lstr.nrtoco = rs.getLong      ("nrtoco"); 
        if (rs.getObject("cdaebe")!=null) lstr.cdaebe = rs.getString    ("cdaebe"); 
        if (rs.getObject("cdcllist")!=null) lstr.cdcllist = rs.getString    ("cdcllist"); 
        if (rs.getObject("cdclprc")!=null) lstr.cdclprc = rs.getString    ("cdclprc"); 
        if (rs.getObject("cdbanc")!=null) lstr.cdbanc = rs.getString    ("cdbanc"); 
        if (rs.getObject("totale_imp")!=null) lstr.totale_imp = rs.getDouble    ("totale_imp"); 
        if (rs.getObject("tkclie_fatt")!=null) lstr.tkclie_fatt = rs.getString    ("tkclie_fatt"); 
        if (rs.getObject("cdente_fatt")!=null) lstr.cdente_fatt = rs.getString    ("cdente_fatt"); 
        if (rs.getObject("cdunil_fatt")!=null) lstr.cdunil_fatt = rs.getString    ("cdunil_fatt"); 
        if (rs.getObject("dtlistval")!=null) lstr.dtlistval = rs.getTimestamp ("dtlistval"); 
        if (rs.getObject("dtcontrval")!=null) lstr.dtcontrval = rs.getTimestamp ("dtcontrval"); 
        if (rs.getObject("tkutente")!=null) lstr.tkutente = rs.getLong      ("tkutente"); 
        if (rs.getObject("tkordigest")!=null) lstr.tkordigest = rs.getLong      ("tkordigest"); 
        if (rs.getObject("cdordigest")!=null) lstr.cdordigest = rs.getString    ("cdordigest"); 
        //EAR 20110707
        if (rs.getObject("tksubutente")!=null) lstr.tksubutente = rs.getLong      ("tksubutente"); 
        if (rs.getObject("isscontiriga")!=null) lstr.isscontiriga = rs.getString    ("isscontiriga"); 
        if (rs.getObject("dtmailgest")!=null) lstr.dtmailgest = rs.getTimestamp ("dtmailgest"); 
        if (rs.getObject("cdpromo_m")!=null) lstr.cdpromo_m = rs.getString    ("cdpromo_m"); 
        if (rs.getObject("tkrifoff")!=null) lstr.tkrifoff = rs.getLong      ("tkrifoff"); 

        return lstr;
    }




    /****
        execute: web_ord_test
    **/ 


    public int execute ( com.ateikon.structure.Rec_web_ord_test astr) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec =  execute ( astr.tkordi
                          , astr.cdtorve
                          , astr.anno
                          , astr.numord
                          , astr.dtordi
                          , astr.dtfval
                          , astr.dtcons
                          , astr.tkoffe
                          , astr.cdtofv
                          , astr.annoof
                          , astr.numoff
                          , astr.cdrifo
                          , astr.dtrior
                          , astr.cdnsri
                          , astr.dtnsri
                          , astr.tkclie
                          , astr.cdentc
                          , astr.cdulic
                          , astr.cdagen
                          , astr.provv1_p
                          , astr.provv1_v
                          , astr.cdsuba
                          , astr.provv2_p
                          , astr.provv2_v
                          , astr.tkproc1
                          , astr.cdentp1
                          , astr.tkproc2
                          , astr.cdentp2
                          , astr.cdtlis
                          , astr.dtrlis
                          , astr.accred
                          , astr.antici
                          , astr.abibap
                          , astr.cabbap
                          , astr.vacodi
                          , astr.cdclsc
                          , astr.scrig1
                          , astr.scrig2
                          , astr.scrig3
                          , astr.scrig4
                          , astr.scrap1
                          , astr.scrap2
                          , astr.cotraq
                          , astr.cdiva
                          , astr.pgcodi
                          , astr.dpcodi
                          , astr.cddpag
                          , astr.ffinme
                          , astr.tgsca1
                          , astr.tgsca2
                          , astr.scocas
                          , astr.cdtrgf
                          , astr.cdtfat
                          , astr.cdtdoc
                          , astr.cdfisc
                          , astr.cdrmpo
                          , astr.cdtrme
                          , astr.cdtaut
                          , astr.fscagru
                          , astr.cduldm
                          , astr.cdentd
                          , astr.ragcog_de
                          , astr.indiri_de
                          , astr.cap_de
                          , astr.comune_de
                          , astr.cdprov_de
                          , astr.cdstat_de
                          , astr.cdzone
                          , astr.cdvett1
                          , astr.cdentv1
                          , astr.cdulv1
                          , astr.cdvett2
                          , astr.cdentv2
                          , astr.cdulv2
                          , astr.dsvett3
                          , astr.dtstco
                          , astr.fstcor
                          , astr.fcorst
                          , astr.fcouni
                          , astr.fcator
                          , astr.fposfm
                          , astr.cdstab
                          , astr.tkmaga
                          , astr.cdling
                          , astr.cambio
                          , astr.nrcarta
                          , astr.dtfivacarta
                          , astr.cdcarta
                          , astr.vcmarca
                          , astr.vcanno
                          , astr.vcmodello
                          , astr.vctpcar
                          , astr.vcnporte
                          , astr.cdlist
                          , astr.ivato
                          , astr.cdaut
                          , astr.autcarta
                          , astr.dtautcarta
                          , astr.tkvaglia
                          , astr.imptrasp
                          , astr.fannullato
                          , astr.cdstato
                          , astr.fmezzo
                          , astr.funo
                          , astr.cogn_p
                          , astr.indirizzo_p
                          , astr.localita_p
                          , astr.cap_p
                          , astr.provincia_p
                          , astr.gior_ricmerci
                          , astr.dtstpre
                          , astr.cdordi
                          , astr.fmovmp
                          , astr.cdtprov
                          , astr.cdtdoc_prot
                          , astr.dspag
                          , astr.scval
                          , astr.dtdeco
                          , astr.faspef
                          , astr.speinc
                          , astr.nraddimb
                          , astr.addimb
                          , astr.addtra
                          , astr.nrpeso_l
                          , astr.nrpeso_n
                          , astr.nrtovo
                          , astr.desbanc
                          , astr.cdprel
                          , astr.cdadde
                          , astr.tmesc1
                          , astr.tmesc2
                          , astr.tgsc1m
                          , astr.tgsc2m
                          , astr.fgevaso
                          , astr.cdtdoc_segue
                          , astr.cdcatm
                          , astr.provv1_p_var
                          , astr.provv2_p_var
                          , astr.provv1_v_var
                          , astr.provv2_v_var
                          , astr.cdpagame
                          , astr.off_cortese_att
                          , astr.off_persona_rif
                          , astr.off_oggetto
                          , astr.tkoffnote
                          , astr.nrtoco
                          , astr.cdaebe
                          , astr.cdcllist
                          , astr.cdclprc
                          , astr.cdbanc
                          , astr.totale_imp
                          , astr.tkclie_fatt
                          , astr.cdente_fatt
                          , astr.cdunil_fatt
                          , astr.dtlistval
                          , astr.dtcontrval
                          , astr.tkutente
                          , astr.tkordigest
                          , astr.cdordigest
                          , astr.tksubutente //EAR 20110707
                          , astr.isscontiriga
                           );

        return tot_rec;
    }




    /****
        execute: web_ord_test
    **/ 


    public int execute ( long       tkordi
                       , String     cdtorve
                       , long       anno
                       , long       numord
                       , Timestamp  dtordi
                       , Timestamp  dtfval
                       , Timestamp  dtcons
                       , long       tkoffe
                       , String     cdtofv
                       , long       annoof
                       , long       numoff
                       , String     cdrifo
                       , Timestamp  dtrior
                       , String     cdnsri
                       , Timestamp  dtnsri
                       , String     tkclie
                       , String     cdentc
                       , String     cdulic
                       , String     cdagen
                       , double     provv1_p
                       , double     provv1_v
                       , String     cdsuba
                       , double     provv2_p
                       , double     provv2_v
                       , String     tkproc1
                       , String     cdentp1
                       , String     tkproc2
                       , String     cdentp2
                       , String     cdtlis
                       , Timestamp  dtrlis
                       , double     accred
                       , double     antici
                       , String     abibap
                       , String     cabbap
                       , String     vacodi
                       , String     cdclsc
                       , double     scrig1
                       , double     scrig2
                       , double     scrig3
                       , double     scrig4
                       , double     scrap1
                       , double     scrap2
                       , double     cotraq
                       , String     cdiva
                       , String     pgcodi
                       , String     dpcodi
                       , String     cddpag
                       , String     ffinme
                       , long       tgsca1
                       , long       tgsca2
                       , double     scocas
                       , String     cdtrgf
                       , String     cdtfat
                       , String     cdtdoc
                       , String     cdfisc
                       , String     cdrmpo
                       , String     cdtrme
                       , String     cdtaut
                       , String     fscagru
                       , String     cduldm
                       , String     cdentd
                       , String     ragcog_de
                       , String     indiri_de
                       , String     cap_de
                       , String     comune_de
                       , String     cdprov_de
                       , String     cdstat_de
                       , String     cdzone
                       , String     cdvett1
                       , String     cdentv1
                       , String     cdulv1
                       , String     cdvett2
                       , String     cdentv2
                       , String     cdulv2
                       , String     dsvett3
                       , Timestamp  dtstco
                       , String     fstcor
                       , String     fcorst
                       , String     fcouni
                       , String     fcator
                       , String     fposfm
                       , String     cdstab
                       , long       tkmaga
                       , String     cdling
                       , double     cambio
                       , String     nrcarta
                       , Timestamp  dtfivacarta
                       , String     cdcarta
                       , String     vcmarca
                       , long       vcanno
                       , String     vcmodello
                       , String     vctpcar
                       , long       vcnporte
                       , String     cdlist
                       , String     ivato
                       , String     cdaut
                       , String     autcarta
                       , Timestamp  dtautcarta
                       , String     tkvaglia
                       , double     imptrasp
                       , String     fannullato
                       , String     cdstato
                       , String     fmezzo
                       , String     funo
                       , String     cogn_p
                       , String     indirizzo_p
                       , String     localita_p
                       , String     cap_p
                       , String     provincia_p
                       , String     gior_ricmerci
                       , Timestamp  dtstpre
                       , String     cdordi
                       , String     fmovmp
                       , String     cdtprov
                       , String     cdtdoc_prot
                       , String     dspag
                       , double     scval
                       , Timestamp  dtdeco
                       , String     faspef
                       , double     speinc
                       , double     nraddimb
                       , double     addimb
                       , double     addtra
                       , double     nrpeso_l
                       , double     nrpeso_n
                       , double     nrtovo
                       , String     desbanc
                       , String     cdprel
                       , String     cdadde
                       , long       tmesc1
                       , long       tmesc2
                       , long       tgsc1m
                       , long       tgsc2m
                       , String     fgevaso
                       , String     cdtdoc_segue
                       , String     cdcatm
                       , double     provv1_p_var
                       , double     provv2_p_var
                       , double     provv1_v_var
                       , double     provv2_v_var
                       , String     cdpagame
                       , String     off_cortese_att
                       , String     off_persona_rif
                       , String     off_oggetto
                       , long       tkoffnote
                       , long       nrtoco
                       , String     cdaebe
                       , String     cdcllist
                       , String     cdclprc
                       , String     cdbanc
                       , double     totale_imp
                       , String     tkclie_fatt
                       , String     cdente_fatt
                       , String     cdunil_fatt
                       , Timestamp  dtlistval
                       , Timestamp  dtcontrval
                       , long       tkutente
                       , long       tkordigest
                       , String     cdordigest
                       , long       tksubutente  //EAR 20110707
                       , String     isscontiriga
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " select tkordi\n";
        l_query  += "   from pgmr.web_ord_test  \n";
        l_query  += "  where tkordi = ? \n";


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (tkordi == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkordi); ind += 1;
        } 

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            pstm.close();
            tot_rec = executeUpdate ( tkordi
                                    , cdtorve
                                    , anno
                                    , numord
                                    , dtordi
                                    , dtfval
                                    , dtcons
                                    , tkoffe
                                    , cdtofv
                                    , annoof
                                    , numoff
                                    , cdrifo
                                    , dtrior
                                    , cdnsri
                                    , dtnsri
                                    , tkclie
                                    , cdentc
                                    , cdulic
                                    , cdagen
                                    , provv1_p
                                    , provv1_v
                                    , cdsuba
                                    , provv2_p
                                    , provv2_v
                                    , tkproc1
                                    , cdentp1
                                    , tkproc2
                                    , cdentp2
                                    , cdtlis
                                    , dtrlis
                                    , accred
                                    , antici
                                    , abibap
                                    , cabbap
                                    , vacodi
                                    , cdclsc
                                    , scrig1
                                    , scrig2
                                    , scrig3
                                    , scrig4
                                    , scrap1
                                    , scrap2
                                    , cotraq
                                    , cdiva
                                    , pgcodi
                                    , dpcodi
                                    , cddpag
                                    , ffinme
                                    , tgsca1
                                    , tgsca2
                                    , scocas
                                    , cdtrgf
                                    , cdtfat
                                    , cdtdoc
                                    , cdfisc
                                    , cdrmpo
                                    , cdtrme
                                    , cdtaut
                                    , fscagru
                                    , cduldm
                                    , cdentd
                                    , ragcog_de
                                    , indiri_de
                                    , cap_de
                                    , comune_de
                                    , cdprov_de
                                    , cdstat_de
                                    , cdzone
                                    , cdvett1
                                    , cdentv1
                                    , cdulv1
                                    , cdvett2
                                    , cdentv2
                                    , cdulv2
                                    , dsvett3
                                    , dtstco
                                    , fstcor
                                    , fcorst
                                    , fcouni
                                    , fcator
                                    , fposfm
                                    , cdstab
                                    , tkmaga
                                    , cdling
                                    , cambio
                                    , nrcarta
                                    , dtfivacarta
                                    , cdcarta
                                    , vcmarca
                                    , vcanno
                                    , vcmodello
                                    , vctpcar
                                    , vcnporte
                                    , cdlist
                                    , ivato
                                    , cdaut
                                    , autcarta
                                    , dtautcarta
                                    , tkvaglia
                                    , imptrasp
                                    , fannullato
                                    , cdstato
                                    , fmezzo
                                    , funo
                                    , cogn_p
                                    , indirizzo_p
                                    , localita_p
                                    , cap_p
                                    , provincia_p
                                    , gior_ricmerci
                                    , dtstpre
                                    , cdordi
                                    , fmovmp
                                    , cdtprov
                                    , cdtdoc_prot
                                    , dspag
                                    , scval
                                    , dtdeco
                                    , faspef
                                    , speinc
                                    , nraddimb
                                    , addimb
                                    , addtra
                                    , nrpeso_l
                                    , nrpeso_n
                                    , nrtovo
                                    , desbanc
                                    , cdprel
                                    , cdadde
                                    , tmesc1
                                    , tmesc2
                                    , tgsc1m
                                    , tgsc2m
                                    , fgevaso
                                    , cdtdoc_segue
                                    , cdcatm
                                    , provv1_p_var
                                    , provv2_p_var
                                    , provv1_v_var
                                    , provv2_v_var
                                    , cdpagame
                                    , off_cortese_att
                                    , off_persona_rif
                                    , off_oggetto
                                    , tkoffnote
                                    , nrtoco
                                    , cdaebe
                                    , cdcllist
                                    , cdclprc
                                    , cdbanc
                                    , totale_imp
                                    , tkclie_fatt
                                    , cdente_fatt
                                    , cdunil_fatt
                                    , dtlistval
                                    , dtcontrval
                                    , tkutente
                                    , tkordigest
                                    , cdordigest
                                    , tksubutente   //EAR20110707
                                    , isscontiriga
                                    );
        }else {

            pstm.close();
            tot_rec = executeInsert ( tkordi
                                    , cdtorve
                                    , anno
                                    , numord
                                    , dtordi
                                    , dtfval
                                    , dtcons
                                    , tkoffe
                                    , cdtofv
                                    , annoof
                                    , numoff
                                    , cdrifo
                                    , dtrior
                                    , cdnsri
                                    , dtnsri
                                    , tkclie
                                    , cdentc
                                    , cdulic
                                    , cdagen
                                    , provv1_p
                                    , provv1_v
                                    , cdsuba
                                    , provv2_p
                                    , provv2_v
                                    , tkproc1
                                    , cdentp1
                                    , tkproc2
                                    , cdentp2
                                    , cdtlis
                                    , dtrlis
                                    , accred
                                    , antici
                                    , abibap
                                    , cabbap
                                    , vacodi
                                    , cdclsc
                                    , scrig1
                                    , scrig2
                                    , scrig3
                                    , scrig4
                                    , scrap1
                                    , scrap2
                                    , cotraq
                                    , cdiva
                                    , pgcodi
                                    , dpcodi
                                    , cddpag
                                    , ffinme
                                    , tgsca1
                                    , tgsca2
                                    , scocas
                                    , cdtrgf
                                    , cdtfat
                                    , cdtdoc
                                    , cdfisc
                                    , cdrmpo
                                    , cdtrme
                                    , cdtaut
                                    , fscagru
                                    , cduldm
                                    , cdentd
                                    , ragcog_de
                                    , indiri_de
                                    , cap_de
                                    , comune_de
                                    , cdprov_de
                                    , cdstat_de
                                    , cdzone
                                    , cdvett1
                                    , cdentv1
                                    , cdulv1
                                    , cdvett2
                                    , cdentv2
                                    , cdulv2
                                    , dsvett3
                                    , dtstco
                                    , fstcor
                                    , fcorst
                                    , fcouni
                                    , fcator
                                    , fposfm
                                    , cdstab
                                    , tkmaga
                                    , cdling
                                    , cambio
                                    , nrcarta
                                    , dtfivacarta
                                    , cdcarta
                                    , vcmarca
                                    , vcanno
                                    , vcmodello
                                    , vctpcar
                                    , vcnporte
                                    , cdlist
                                    , ivato
                                    , cdaut
                                    , autcarta
                                    , dtautcarta
                                    , tkvaglia
                                    , imptrasp
                                    , fannullato
                                    , cdstato
                                    , fmezzo
                                    , funo
                                    , cogn_p
                                    , indirizzo_p
                                    , localita_p
                                    , cap_p
                                    , provincia_p
                                    , gior_ricmerci
                                    , dtstpre
                                    , cdordi
                                    , fmovmp
                                    , cdtprov
                                    , cdtdoc_prot
                                    , dspag
                                    , scval
                                    , dtdeco
                                    , faspef
                                    , speinc
                                    , nraddimb
                                    , addimb
                                    , addtra
                                    , nrpeso_l
                                    , nrpeso_n
                                    , nrtovo
                                    , desbanc
                                    , cdprel
                                    , cdadde
                                    , tmesc1
                                    , tmesc2
                                    , tgsc1m
                                    , tgsc2m
                                    , fgevaso
                                    , cdtdoc_segue
                                    , cdcatm
                                    , provv1_p_var
                                    , provv2_p_var
                                    , provv1_v_var
                                    , provv2_v_var
                                    , cdpagame
                                    , off_cortese_att
                                    , off_persona_rif
                                    , off_oggetto
                                    , tkoffnote
                                    , nrtoco
                                    , cdaebe
                                    , cdcllist
                                    , cdclprc
                                    , cdbanc
                                    , totale_imp
                                    , tkclie_fatt
                                    , cdente_fatt
                                    , cdunil_fatt
                                    , dtlistval
                                    , dtcontrval
                                    , tkutente
                                    , tkordigest
                                    , cdordigest
                                    , tksubutente  //EAR 20110707
                                    , isscontiriga
                                    );
        }

        if (tot_rec != 1){

            m_connection.rollback();
            throw new Exception("Errore Agg. web_ord_test! ");
        }

        return tot_rec;
    }




    /****
        executeInsert: web_ord_test
    **/ 


    public int executeInsert( long       tkordi
                            , String     cdtorve
                            , long       anno
                            , long       numord
                            , Timestamp  dtordi
                            , Timestamp  dtfval
                            , Timestamp  dtcons
                            , long       tkoffe
                            , String     cdtofv
                            , long       annoof
                            , long       numoff
                            , String     cdrifo
                            , Timestamp  dtrior
                            , String     cdnsri
                            , Timestamp  dtnsri
                            , String     tkclie
                            , String     cdentc
                            , String     cdulic
                            , String     cdagen
                            , double     provv1_p
                            , double     provv1_v
                            , String     cdsuba
                            , double     provv2_p
                            , double     provv2_v
                            , String     tkproc1
                            , String     cdentp1
                            , String     tkproc2
                            , String     cdentp2
                            , String     cdtlis
                            , Timestamp  dtrlis
                            , double     accred
                            , double     antici
                            , String     abibap
                            , String     cabbap
                            , String     vacodi
                            , String     cdclsc
                            , double     scrig1
                            , double     scrig2
                            , double     scrig3
                            , double     scrig4
                            , double     scrap1
                            , double     scrap2
                            , double     cotraq
                            , String     cdiva
                            , String     pgcodi
                            , String     dpcodi
                            , String     cddpag
                            , String     ffinme
                            , long       tgsca1
                            , long       tgsca2
                            , double     scocas
                            , String     cdtrgf
                            , String     cdtfat
                            , String     cdtdoc
                            , String     cdfisc
                            , String     cdrmpo
                            , String     cdtrme
                            , String     cdtaut
                            , String     fscagru
                            , String     cduldm
                            , String     cdentd
                            , String     ragcog_de
                            , String     indiri_de
                            , String     cap_de
                            , String     comune_de
                            , String     cdprov_de
                            , String     cdstat_de
                            , String     cdzone
                            , String     cdvett1
                            , String     cdentv1
                            , String     cdulv1
                            , String     cdvett2
                            , String     cdentv2
                            , String     cdulv2
                            , String     dsvett3
                            , Timestamp  dtstco
                            , String     fstcor
                            , String     fcorst
                            , String     fcouni
                            , String     fcator
                            , String     fposfm
                            , String     cdstab
                            , long       tkmaga
                            , String     cdling
                            , double     cambio
                            , String     nrcarta
                            , Timestamp  dtfivacarta
                            , String     cdcarta
                            , String     vcmarca
                            , long       vcanno
                            , String     vcmodello
                            , String     vctpcar
                            , long       vcnporte
                            , String     cdlist
                            , String     ivato
                            , String     cdaut
                            , String     autcarta
                            , Timestamp  dtautcarta
                            , String     tkvaglia
                            , double     imptrasp
                            , String     fannullato
                            , String     cdstato
                            , String     fmezzo
                            , String     funo
                            , String     cogn_p
                            , String     indirizzo_p
                            , String     localita_p
                            , String     cap_p
                            , String     provincia_p
                            , String     gior_ricmerci
                            , Timestamp  dtstpre
                            , String     cdordi
                            , String     fmovmp
                            , String     cdtprov
                            , String     cdtdoc_prot
                            , String     dspag
                            , double     scval
                            , Timestamp  dtdeco
                            , String     faspef
                            , double     speinc
                            , double     nraddimb
                            , double     addimb
                            , double     addtra
                            , double     nrpeso_l
                            , double     nrpeso_n
                            , double     nrtovo
                            , String     desbanc
                            , String     cdprel
                            , String     cdadde
                            , long       tmesc1
                            , long       tmesc2
                            , long       tgsc1m
                            , long       tgsc2m
                            , String     fgevaso
                            , String     cdtdoc_segue
                            , String     cdcatm
                            , double     provv1_p_var
                            , double     provv2_p_var
                            , double     provv1_v_var
                            , double     provv2_v_var
                            , String     cdpagame
                            , String     off_cortese_att
                            , String     off_persona_rif
                            , String     off_oggetto
                            , long       tkoffnote
                            , long       nrtoco
                            , String     cdaebe
                            , String     cdcllist
                            , String     cdclprc
                            , String     cdbanc
                            , double     totale_imp
                            , String     tkclie_fatt
                            , String     cdente_fatt
                            , String     cdunil_fatt
                            , Timestamp  dtlistval
                            , Timestamp  dtcontrval
                            , long       tkutente
                            , long       tkordigest
                            , String     cdordigest
                            , long       tksubutente  //EAR 20110707
                            , String     isscontiriga
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        if (ib_calcola_token){
            long ll_tk = getNew_token( );

            if (ll_tk <= 0){
                throw new Exception ("Error Keypool (web_ord_test): "+ll_tk );
            }

            this.tkordi = ll_tk;
            tkordi = this.tkordi;
        }



        l_query   = "";
        l_query  += " insert into pgmr.web_ord_test ( \n";
        l_query  += "             tkordi   \n";
        l_query  += "           , cdtorve   \n";
        l_query  += "           , anno   \n";
        l_query  += "           , numord   \n";
        l_query  += "           , dtordi   \n";
        l_query  += "           , dtfval   \n";
        l_query  += "           , dtcons   \n";
        l_query  += "           , tkoffe   \n";
        l_query  += "           , cdtofv   \n";
        l_query  += "           , annoof   \n";
        l_query  += "           , numoff   \n";
        l_query  += "           , cdrifo   \n";
        l_query  += "           , dtrior   \n";
        l_query  += "           , cdnsri   \n";
        l_query  += "           , dtnsri   \n";
        l_query  += "           , tkclie   \n";
        l_query  += "           , cdentc   \n";
        l_query  += "           , cdulic   \n";
        l_query  += "           , cdagen   \n";
        l_query  += "           , provv1_p   \n";
        l_query  += "           , provv1_v   \n";
        l_query  += "           , cdsuba   \n";
        l_query  += "           , provv2_p   \n";
        l_query  += "           , provv2_v   \n";
        l_query  += "           , tkproc1   \n";
        l_query  += "           , cdentp1   \n";
        l_query  += "           , tkproc2   \n";
        l_query  += "           , cdentp2   \n";
        l_query  += "           , cdtlis   \n";
        l_query  += "           , dtrlis   \n";
        l_query  += "           , accred   \n";
        l_query  += "           , antici   \n";
        l_query  += "           , abibap   \n";
        l_query  += "           , cabbap   \n";
        l_query  += "           , vacodi   \n";
        l_query  += "           , cdclsc   \n";
        l_query  += "           , scrig1   \n";
        l_query  += "           , scrig2   \n";
        l_query  += "           , scrig3   \n";
        l_query  += "           , scrig4   \n";
        l_query  += "           , scrap1   \n";
        l_query  += "           , scrap2   \n";
        l_query  += "           , cotraq   \n";
        l_query  += "           , cdiva   \n";
        l_query  += "           , pgcodi   \n";
        l_query  += "           , dpcodi   \n";
        l_query  += "           , cddpag   \n";
        l_query  += "           , ffinme   \n";
        l_query  += "           , tgsca1   \n";
        l_query  += "           , tgsca2   \n";
        l_query  += "           , scocas   \n";
        l_query  += "           , cdtrgf   \n";
        l_query  += "           , cdtfat   \n";
        l_query  += "           , cdtdoc   \n";
        l_query  += "           , cdfisc   \n";
        l_query  += "           , cdrmpo   \n";
        l_query  += "           , cdtrme   \n";
        l_query  += "           , cdtaut   \n";
        l_query  += "           , fscagru   \n";
        l_query  += "           , cduldm   \n";
        l_query  += "           , cdentd   \n";
        l_query  += "           , ragcog_de   \n";
        l_query  += "           , indiri_de   \n";
        l_query  += "           , cap_de   \n";
        l_query  += "           , comune_de   \n";
        l_query  += "           , cdprov_de   \n";
        l_query  += "           , cdstat_de   \n";
        l_query  += "           , cdzone   \n";
        l_query  += "           , cdvett1   \n";
        l_query  += "           , cdentv1   \n";
        l_query  += "           , cdulv1   \n";
        l_query  += "           , cdvett2   \n";
        l_query  += "           , cdentv2   \n";
        l_query  += "           , cdulv2   \n";
        l_query  += "           , dsvett3   \n";
        l_query  += "           , dtstco   \n";
        l_query  += "           , fstcor   \n";
        l_query  += "           , fcorst   \n";
        l_query  += "           , fcouni   \n";
        l_query  += "           , fcator   \n";
        l_query  += "           , fposfm   \n";
        l_query  += "           , cdstab   \n";
        l_query  += "           , tkmaga   \n";
        l_query  += "           , cdling   \n";
        l_query  += "           , cambio   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , nrcarta   \n";
        l_query  += "           , dtfivacarta   \n";
        l_query  += "           , cdcarta   \n";
        l_query  += "           , vcmarca   \n";
        l_query  += "           , vcanno   \n";
        l_query  += "           , vcmodello   \n";
        l_query  += "           , vctpcar   \n";
        l_query  += "           , vcnporte   \n";
        l_query  += "           , cdlist   \n";
        l_query  += "           , ivato   \n";
        l_query  += "           , cdaut   \n";
        l_query  += "           , autcarta   \n";
        l_query  += "           , dtautcarta   \n";
        l_query  += "           , tkvaglia   \n";
        l_query  += "           , imptrasp   \n";
        l_query  += "           , fannullato   \n";
        l_query  += "           , cdstato   \n";
        l_query  += "           , fmezzo   \n";
        l_query  += "           , funo   \n";
        l_query  += "           , cogn_p   \n";
        l_query  += "           , indirizzo_p   \n";
        l_query  += "           , localita_p   \n";
        l_query  += "           , cap_p   \n";
        l_query  += "           , provincia_p   \n";
        l_query  += "           , gior_ricmerci   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "           , dtstpre   \n";
        l_query  += "           , cdordi   \n";
        l_query  += "           , fmovmp   \n";
        l_query  += "           , cdtprov   \n";
        l_query  += "           , cdtdoc_prot   \n";
        l_query  += "           , dspag   \n";
        l_query  += "           , scval   \n";
        l_query  += "           , dtdeco   \n";
        l_query  += "           , faspef   \n";
        l_query  += "           , speinc   \n";
        l_query  += "           , nraddimb   \n";
        l_query  += "           , addimb   \n";
        l_query  += "           , addtra   \n";
        l_query  += "           , nrpeso_l   \n";
        l_query  += "           , nrpeso_n   \n";
        l_query  += "           , nrtovo   \n";
        l_query  += "           , desbanc   \n";
        l_query  += "           , cdprel   \n";
        l_query  += "           , cdadde   \n";
        l_query  += "           , tmesc1   \n";
        l_query  += "           , tmesc2   \n";
        l_query  += "           , tgsc1m   \n";
        l_query  += "           , tgsc2m   \n";
        l_query  += "           , fgevaso   \n";
        l_query  += "           , cdtdoc_segue   \n";
        l_query  += "           , cdcatm   \n";
        l_query  += "           , provv1_p_var   \n";
        l_query  += "           , provv2_p_var   \n";
        l_query  += "           , provv1_v_var   \n";
        l_query  += "           , provv2_v_var   \n";
        l_query  += "           , cdpagame   \n";
        l_query  += "           , off_cortese_att   \n";
        l_query  += "           , off_persona_rif   \n";
        l_query  += "           , off_oggetto   \n";
        l_query  += "           , tkoffnote   \n";
        l_query  += "           , nrtoco   \n";
        l_query  += "           , cdaebe   \n";
        l_query  += "           , cdcllist   \n";
        l_query  += "           , cdclprc   \n";
        l_query  += "           , cdbanc   \n";
        l_query  += "           , totale_imp   \n";
        l_query  += "           , tkclie_fatt   \n";
        l_query  += "           , cdente_fatt   \n";
        l_query  += "           , cdunil_fatt   \n";
        l_query  += "           , dtlistval   \n";
        l_query  += "           , dtcontrval   \n";
        l_query  += "           , tkutente   \n";
        l_query  += "           , tkordigest   \n";
        l_query  += "           , cdordigest   \n";
        l_query  += "           , tksubutente   \n";  //EAR 20110707
        l_query  += "           , isscontiriga   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?,?  \n";  //EAR 20110707
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cdtorve.equals("")) cdtorve = null;
        if (cdtofv.equals("")) cdtofv = null;
        if (cdrifo.equals("")) cdrifo = null;
        if (cdnsri.equals("")) cdnsri = null;
        if (tkclie.equals("")) tkclie = null;
        if (cdentc.equals("")) cdentc = null;
        if (cdulic.equals("")) cdulic = null;
        if (cdagen.equals("")) cdagen = null;
        if (cdsuba.equals("")) cdsuba = null;
        if (tkproc1.equals("")) tkproc1 = null;
        if (cdentp1.equals("")) cdentp1 = null;
        if (tkproc2.equals("")) tkproc2 = null;
        if (cdentp2.equals("")) cdentp2 = null;
        if (cdtlis.equals("")) cdtlis = null;
        if (abibap.equals("")) abibap = null;
        if (cabbap.equals("")) cabbap = null;
        if (vacodi.equals("")) vacodi = null;
        if (cdclsc.equals("")) cdclsc = null;
        if (cdiva.equals("")) cdiva = null;
        if (pgcodi.equals("")) pgcodi = null;
        if (dpcodi.equals("")) dpcodi = null;
        if (cddpag.equals("")) cddpag = null;
        if (ffinme.equals("")) ffinme = null;
        if (cdtrgf.equals("")) cdtrgf = null;
        if (cdtfat.equals("")) cdtfat = null;
        if (cdtdoc.equals("")) cdtdoc = null;
        if (cdfisc.equals("")) cdfisc = null;
        if (cdrmpo.equals("")) cdrmpo = null;
        if (cdtrme.equals("")) cdtrme = null;
        if (cdtaut.equals("")) cdtaut = null;
        if (fscagru.equals("")) fscagru = null;
        if (cduldm.equals("")) cduldm = null;
        if (cdentd.equals("")) cdentd = null;
        if (ragcog_de.equals("")) ragcog_de = null;
        if (indiri_de.equals("")) indiri_de = null;
        if (cap_de.equals("")) cap_de = null;
        if (comune_de.equals("")) comune_de = null;
        if (cdprov_de.equals("")) cdprov_de = null;
        if (cdstat_de.equals("")) cdstat_de = null;
        if (cdzone.equals("")) cdzone = null;
        if (cdvett1.equals("")) cdvett1 = null;
        if (cdentv1.equals("")) cdentv1 = null;
        if (cdulv1.equals("")) cdulv1 = null;
        if (cdvett2.equals("")) cdvett2 = null;
        if (cdentv2.equals("")) cdentv2 = null;
        if (cdulv2.equals("")) cdulv2 = null;
        if (dsvett3.equals("")) dsvett3 = null;
        if (fstcor.equals("")) fstcor = null;
        if (fcorst.equals("")) fcorst = null;
        if (fcouni.equals("")) fcouni = null;
        if (fcator.equals("")) fcator = null;
        if (fposfm.equals("")) fposfm = null;
        if (cdstab.equals("")) cdstab = null;
        if (cdling.equals("")) cdling = null;
        if (cdazie.equals("")) cdazie = null;
        if (nrcarta.equals("")) nrcarta = null;
        if (cdcarta.equals("")) cdcarta = null;
        if (vcmarca.equals("")) vcmarca = null;
        if (vcmodello.equals("")) vcmodello = null;
        if (vctpcar.equals("")) vctpcar = null;
        if (cdlist.equals("")) cdlist = null;
        if (ivato.equals("")) ivato = null;
        if (cdaut.equals("")) cdaut = null;
        if (autcarta.equals("")) autcarta = null;
        if (tkvaglia.equals("")) tkvaglia = null;
        if (fannullato.equals("")) fannullato = null;
        if (cdstato.equals("")) cdstato = null;
        if (fmezzo.equals("")) fmezzo = null;
        if (funo.equals("")) funo = null;
        if (cogn_p.equals("")) cogn_p = null;
        if (indirizzo_p.equals("")) indirizzo_p = null;
        if (localita_p.equals("")) localita_p = null;
        if (cap_p.equals("")) cap_p = null;
        if (provincia_p.equals("")) provincia_p = null;
        if (gior_ricmerci.equals("")) gior_ricmerci = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;
        if (cdordi.equals("")) cdordi = null;
        if (fmovmp.equals("")) fmovmp = null;
        if (cdtprov.equals("")) cdtprov = null;
        if (cdtdoc_prot.equals("")) cdtdoc_prot = null;
        if (dspag.equals("")) dspag = null;
        if (faspef.equals("")) faspef = null;
        if (desbanc.equals("")) desbanc = null;
        if (cdprel.equals("")) cdprel = null;
        if (cdadde.equals("")) cdadde = null;
        if (fgevaso.equals("")) fgevaso = null;
        if (cdtdoc_segue.equals("")) cdtdoc_segue = null;
        if (cdcatm.equals("")) cdcatm = null;
        if (cdpagame.equals("")) cdpagame = null;
        if (off_cortese_att.equals("")) off_cortese_att = null;
        if (off_persona_rif.equals("")) off_persona_rif = null;
        if (off_oggetto.equals("")) off_oggetto = null;
        if (cdaebe.equals("")) cdaebe = null;
        if (cdcllist.equals("")) cdcllist = null;
        if (cdclprc.equals("")) cdclprc = null;
        if (cdbanc.equals("")) cdbanc = null;
        if (tkclie_fatt.equals("")) tkclie_fatt = null;
        if (cdente_fatt.equals("")) cdente_fatt = null;
        if (cdunil_fatt.equals("")) cdunil_fatt = null;
        if (cdordigest.equals("")) cdordigest = null;
        if (isscontiriga.equals("")) isscontiriga = null;


        ind = 1;
        if (tkordi == 0 && null_tkordi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkordi); ind += 1;
        } 
        pstm.setString    (ind, cdtorve); ind += 1;
        if (anno == 0 && null_anno){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, anno); ind += 1;
        } 
        if (numord == 0 && null_numord){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, numord); ind += 1;
        } 
        pstm.setTimestamp (ind, dtordi); ind += 1;
        pstm.setTimestamp (ind, dtfval); ind += 1;
        pstm.setTimestamp (ind, dtcons); ind += 1;
        if (tkoffe == 0 && null_tkoffe){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkoffe); ind += 1;
        } 
        pstm.setString    (ind, cdtofv); ind += 1;
        if (annoof == 0 && null_annoof){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, annoof); ind += 1;
        } 
        if (numoff == 0 && null_numoff){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, numoff); ind += 1;
        } 
        pstm.setString    (ind, cdrifo); ind += 1;
        pstm.setTimestamp (ind, dtrior); ind += 1;
        pstm.setString    (ind, cdnsri); ind += 1;
        pstm.setTimestamp (ind, dtnsri); ind += 1;
        pstm.setString    (ind, tkclie); ind += 1;
        pstm.setString    (ind, cdentc); ind += 1;
        pstm.setString    (ind, cdulic); ind += 1;
        pstm.setString    (ind, cdagen); ind += 1;
        if (provv1_p == 0 && null_provv1_p){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, provv1_p); ind += 1;
        } 
        if (provv1_v == 0 && null_provv1_v){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, provv1_v); ind += 1;
        } 
        pstm.setString    (ind, cdsuba); ind += 1;
        if (provv2_p == 0 && null_provv2_p){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, provv2_p); ind += 1;
        } 
        if (provv2_v == 0 && null_provv2_v){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, provv2_v); ind += 1;
        } 
        pstm.setString    (ind, tkproc1); ind += 1;
        pstm.setString    (ind, cdentp1); ind += 1;
        pstm.setString    (ind, tkproc2); ind += 1;
        pstm.setString    (ind, cdentp2); ind += 1;
        pstm.setString    (ind, cdtlis); ind += 1;
        pstm.setTimestamp (ind, dtrlis); ind += 1;
        if (accred == 0 && null_accred){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, accred); ind += 1;
        } 
        if (antici == 0 && null_antici){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, antici); ind += 1;
        } 
        pstm.setString    (ind, abibap); ind += 1;
        pstm.setString    (ind, cabbap); ind += 1;
        pstm.setString    (ind, vacodi); ind += 1;
        pstm.setString    (ind, cdclsc); ind += 1;
        if (scrig1 == 0 && null_scrig1){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, scrig1); ind += 1;
        } 
        if (scrig2 == 0 && null_scrig2){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, scrig2); ind += 1;
        } 
        if (scrig3 == 0 && null_scrig3){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, scrig3); ind += 1;
        } 
        if (scrig4 == 0 && null_scrig4){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, scrig4); ind += 1;
        } 
        if (scrap1 == 0 && null_scrap1){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, scrap1); ind += 1;
        } 
        if (scrap2 == 0 && null_scrap2){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, scrap2); ind += 1;
        } 
        if (cotraq == 0 && null_cotraq){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, cotraq); ind += 1;
        } 
        pstm.setString    (ind, cdiva); ind += 1;
        pstm.setString    (ind, pgcodi); ind += 1;
        pstm.setString    (ind, dpcodi); ind += 1;
        pstm.setString    (ind, cddpag); ind += 1;
        pstm.setString    (ind, ffinme); ind += 1;
        if (tgsca1 == 0 && null_tgsca1){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tgsca1); ind += 1;
        } 
        if (tgsca2 == 0 && null_tgsca2){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tgsca2); ind += 1;
        } 
        if (scocas == 0 && null_scocas){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, scocas); ind += 1;
        } 
        pstm.setString    (ind, cdtrgf); ind += 1;
        pstm.setString    (ind, cdtfat); ind += 1;
        pstm.setString    (ind, cdtdoc); ind += 1;
        pstm.setString    (ind, cdfisc); ind += 1;
        pstm.setString    (ind, cdrmpo); ind += 1;
        pstm.setString    (ind, cdtrme); ind += 1;
        pstm.setString    (ind, cdtaut); ind += 1;
        pstm.setString    (ind, fscagru); ind += 1;
        pstm.setString    (ind, cduldm); ind += 1;
        pstm.setString    (ind, cdentd); ind += 1;
        pstm.setString    (ind, ragcog_de); ind += 1;
        pstm.setString    (ind, indiri_de); ind += 1;
        pstm.setString    (ind, cap_de); ind += 1;
        pstm.setString    (ind, comune_de); ind += 1;
        pstm.setString    (ind, cdprov_de); ind += 1;
        pstm.setString    (ind, cdstat_de); ind += 1;
        pstm.setString    (ind, cdzone); ind += 1;
        pstm.setString    (ind, cdvett1); ind += 1;
        pstm.setString    (ind, cdentv1); ind += 1;
        pstm.setString    (ind, cdulv1); ind += 1;
        pstm.setString    (ind, cdvett2); ind += 1;
        pstm.setString    (ind, cdentv2); ind += 1;
        pstm.setString    (ind, cdulv2); ind += 1;
        pstm.setString    (ind, dsvett3); ind += 1;
        pstm.setTimestamp (ind, dtstco); ind += 1;
        pstm.setString    (ind, fstcor); ind += 1;
        pstm.setString    (ind, fcorst); ind += 1;
        pstm.setString    (ind, fcouni); ind += 1;
        pstm.setString    (ind, fcator); ind += 1;
        pstm.setString    (ind, fposfm); ind += 1;
        pstm.setString    (ind, cdstab); ind += 1;
        if (tkmaga == 0 && null_tkmaga){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkmaga); ind += 1;
        } 
        pstm.setString    (ind, cdling); ind += 1;
        if (cambio == 0 && null_cambio){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, cambio); ind += 1;
        } 
        pstm.setString    (ind, cdazie); ind += 1;
        pstm.setString    (ind, nrcarta); ind += 1;
        pstm.setTimestamp (ind, dtfivacarta); ind += 1;
        pstm.setString    (ind, cdcarta); ind += 1;
        pstm.setString    (ind, vcmarca); ind += 1;
        if (vcanno == 0 && null_vcanno){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, vcanno); ind += 1;
        } 
        pstm.setString    (ind, vcmodello); ind += 1;
        pstm.setString    (ind, vctpcar); ind += 1;
        if (vcnporte == 0 && null_vcnporte){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, vcnporte); ind += 1;
        } 
        pstm.setString    (ind, cdlist); ind += 1;
        pstm.setString    (ind, ivato); ind += 1;
        pstm.setString    (ind, cdaut); ind += 1;
        pstm.setString    (ind, autcarta); ind += 1;
        pstm.setTimestamp (ind, dtautcarta); ind += 1;
        pstm.setString    (ind, tkvaglia); ind += 1;
        if (imptrasp == 0 && null_imptrasp){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, imptrasp); ind += 1;
        } 
        pstm.setString    (ind, fannullato); ind += 1;
        pstm.setString    (ind, cdstato); ind += 1;
        pstm.setString    (ind, fmezzo); ind += 1;
        pstm.setString    (ind, funo); ind += 1;
        pstm.setString    (ind, cogn_p); ind += 1;
        pstm.setString    (ind, indirizzo_p); ind += 1;
        pstm.setString    (ind, localita_p); ind += 1;
        pstm.setString    (ind, cap_p); ind += 1;
        pstm.setString    (ind, provincia_p); ind += 1;
        pstm.setString    (ind, gior_ricmerci); ind += 1;
        pstm.setString    (ind, cddipa); ind += 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtinse); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;
        pstm.setTimestamp (ind, dtstpre); ind += 1;
        pstm.setString    (ind, cdordi); ind += 1;
        pstm.setString    (ind, fmovmp); ind += 1;
        pstm.setString    (ind, cdtprov); ind += 1;
        pstm.setString    (ind, cdtdoc_prot); ind += 1;
        pstm.setString    (ind, dspag); ind += 1;
        if (scval == 0 && null_scval){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, scval); ind += 1;
        } 
        pstm.setTimestamp (ind, dtdeco); ind += 1;
        pstm.setString    (ind, faspef); ind += 1;
        if (speinc == 0 && null_speinc){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, speinc); ind += 1;
        } 
        if (nraddimb == 0 && null_nraddimb){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, nraddimb); ind += 1;
        } 
        if (addimb == 0 && null_addimb){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, addimb); ind += 1;
        } 
        if (addtra == 0 && null_addtra){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, addtra); ind += 1;
        } 
        if (nrpeso_l == 0 && null_nrpeso_l){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, nrpeso_l); ind += 1;
        } 
        if (nrpeso_n == 0 && null_nrpeso_n){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, nrpeso_n); ind += 1;
        } 
        if (nrtovo == 0 && null_nrtovo){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, nrtovo); ind += 1;
        } 
        pstm.setString    (ind, desbanc); ind += 1;
        pstm.setString    (ind, cdprel); ind += 1;
        pstm.setString    (ind, cdadde); ind += 1;
        if (tmesc1 == 0 && null_tmesc1){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tmesc1); ind += 1;
        } 
        if (tmesc2 == 0 && null_tmesc2){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tmesc2); ind += 1;
        } 
        if (tgsc1m == 0 && null_tgsc1m){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tgsc1m); ind += 1;
        } 
        if (tgsc2m == 0 && null_tgsc2m){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tgsc2m); ind += 1;
        } 
        pstm.setString    (ind, fgevaso); ind += 1;
        pstm.setString    (ind, cdtdoc_segue); ind += 1;
        pstm.setString    (ind, cdcatm); ind += 1;
        if (provv1_p_var == 0 && null_provv1_p_var){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, provv1_p_var); ind += 1;
        } 
        if (provv2_p_var == 0 && null_provv2_p_var){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, provv2_p_var); ind += 1;
        } 
        if (provv1_v_var == 0 && null_provv1_v_var){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, provv1_v_var); ind += 1;
        } 
        if (provv2_v_var == 0 && null_provv2_v_var){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, provv2_v_var); ind += 1;
        } 
        pstm.setString    (ind, cdpagame); ind += 1;
        pstm.setString    (ind, off_cortese_att); ind += 1;
        pstm.setString    (ind, off_persona_rif); ind += 1;
        pstm.setString    (ind, off_oggetto); ind += 1;
        if (tkoffnote == 0 && null_tkoffnote){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkoffnote); ind += 1;
        } 
        if (nrtoco == 0 && null_nrtoco){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, nrtoco); ind += 1;
        } 
        pstm.setString    (ind, cdaebe); ind += 1;
        pstm.setString    (ind, cdcllist); ind += 1;
        pstm.setString    (ind, cdclprc); ind += 1;
        pstm.setString    (ind, cdbanc); ind += 1;
        if (totale_imp == 0 && null_totale_imp){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, totale_imp); ind += 1;
        } 
        pstm.setString    (ind, tkclie_fatt); ind += 1;
        pstm.setString    (ind, cdente_fatt); ind += 1;
        pstm.setString    (ind, cdunil_fatt); ind += 1;
        pstm.setTimestamp (ind, dtlistval); ind += 1;
        pstm.setTimestamp (ind, dtcontrval); ind += 1;
        if (tkutente == 0 && null_tkutente){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkutente); ind += 1;
        } 
        if (tkordigest == 0 && null_tkordigest){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkordigest); ind += 1;
        } 
        pstm.setString    (ind, cdordigest); ind += 1;
        //EAR 20110707
        if (tksubutente == 0 && null_tksubutente){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tksubutente); ind += 1;
        }         
        pstm.setString    (ind, isscontiriga); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        return tot_rec;

    }




    /***
        Restituisce il token per questa tabella
    */


    public long getNew_token( ) throws Exception{ 
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        F_tabkey f_tabkey = new F_tabkey();
        setProfilo((Atk_sql) f_tabkey );

        long ll_tk = f_tabkey.getTabkey( "web_ord_test" );

        return ll_tk;

    }




    /****
        executeUpdate: web_ord_test
    **/ 


    public int executeUpdate( long       tkordi
                            , String     cdtorve
                            , long       anno
                            , long       numord
                            , Timestamp  dtordi
                            , Timestamp  dtfval
                            , Timestamp  dtcons
                            , long       tkoffe
                            , String     cdtofv
                            , long       annoof
                            , long       numoff
                            , String     cdrifo
                            , Timestamp  dtrior
                            , String     cdnsri
                            , Timestamp  dtnsri
                            , String     tkclie
                            , String     cdentc
                            , String     cdulic
                            , String     cdagen
                            , double     provv1_p
                            , double     provv1_v
                            , String     cdsuba
                            , double     provv2_p
                            , double     provv2_v
                            , String     tkproc1
                            , String     cdentp1
                            , String     tkproc2
                            , String     cdentp2
                            , String     cdtlis
                            , Timestamp  dtrlis
                            , double     accred
                            , double     antici
                            , String     abibap
                            , String     cabbap
                            , String     vacodi
                            , String     cdclsc
                            , double     scrig1
                            , double     scrig2
                            , double     scrig3
                            , double     scrig4
                            , double     scrap1
                            , double     scrap2
                            , double     cotraq
                            , String     cdiva
                            , String     pgcodi
                            , String     dpcodi
                            , String     cddpag
                            , String     ffinme
                            , long       tgsca1
                            , long       tgsca2
                            , double     scocas
                            , String     cdtrgf
                            , String     cdtfat
                            , String     cdtdoc
                            , String     cdfisc
                            , String     cdrmpo
                            , String     cdtrme
                            , String     cdtaut
                            , String     fscagru
                            , String     cduldm
                            , String     cdentd
                            , String     ragcog_de
                            , String     indiri_de
                            , String     cap_de
                            , String     comune_de
                            , String     cdprov_de
                            , String     cdstat_de
                            , String     cdzone
                            , String     cdvett1
                            , String     cdentv1
                            , String     cdulv1
                            , String     cdvett2
                            , String     cdentv2
                            , String     cdulv2
                            , String     dsvett3
                            , Timestamp  dtstco
                            , String     fstcor
                            , String     fcorst
                            , String     fcouni
                            , String     fcator
                            , String     fposfm
                            , String     cdstab
                            , long       tkmaga
                            , String     cdling
                            , double     cambio
                            , String     nrcarta
                            , Timestamp  dtfivacarta
                            , String     cdcarta
                            , String     vcmarca
                            , long       vcanno
                            , String     vcmodello
                            , String     vctpcar
                            , long       vcnporte
                            , String     cdlist
                            , String     ivato
                            , String     cdaut
                            , String     autcarta
                            , Timestamp  dtautcarta
                            , String     tkvaglia
                            , double     imptrasp
                            , String     fannullato
                            , String     cdstato
                            , String     fmezzo
                            , String     funo
                            , String     cogn_p
                            , String     indirizzo_p
                            , String     localita_p
                            , String     cap_p
                            , String     provincia_p
                            , String     gior_ricmerci
                            , Timestamp  dtstpre
                            , String     cdordi
                            , String     fmovmp
                            , String     cdtprov
                            , String     cdtdoc_prot
                            , String     dspag
                            , double     scval
                            , Timestamp  dtdeco
                            , String     faspef
                            , double     speinc
                            , double     nraddimb
                            , double     addimb
                            , double     addtra
                            , double     nrpeso_l
                            , double     nrpeso_n
                            , double     nrtovo
                            , String     desbanc
                            , String     cdprel
                            , String     cdadde
                            , long       tmesc1
                            , long       tmesc2
                            , long       tgsc1m
                            , long       tgsc2m
                            , String     fgevaso
                            , String     cdtdoc_segue
                            , String     cdcatm
                            , double     provv1_p_var
                            , double     provv2_p_var
                            , double     provv1_v_var
                            , double     provv2_v_var
                            , String     cdpagame
                            , String     off_cortese_att
                            , String     off_persona_rif
                            , String     off_oggetto
                            , long       tkoffnote
                            , long       nrtoco
                            , String     cdaebe
                            , String     cdcllist
                            , String     cdclprc
                            , String     cdbanc
                            , double     totale_imp
                            , String     tkclie_fatt
                            , String     cdente_fatt
                            , String     cdunil_fatt
                            , Timestamp  dtlistval
                            , Timestamp  dtcontrval
                            , long       tkutente
                            , long       tkordigest
                            , String     cdordigest
                            , long       tksubutente  //EAR 20110707
                            , String     isscontiriga
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.web_ord_test  \n";
        l_query  += "         set cdtorve = ?  \n";
        l_query  += "           , anno = ?  \n";
        l_query  += "           , numord = ?  \n";
        l_query  += "           , dtordi = ?  \n";
        l_query  += "           , dtfval = ?  \n";
        l_query  += "           , dtcons = ?  \n";
        l_query  += "           , tkoffe = ?  \n";
        l_query  += "           , cdtofv = ?  \n";
        l_query  += "           , annoof = ?  \n";
        l_query  += "           , numoff = ?  \n";
        l_query  += "           , cdrifo = ?  \n";
        l_query  += "           , dtrior = ?  \n";
        l_query  += "           , cdnsri = ?  \n";
        l_query  += "           , dtnsri = ?  \n";
        l_query  += "           , tkclie = ?  \n";
        l_query  += "           , cdentc = ?  \n";
        l_query  += "           , cdulic = ?  \n";
        l_query  += "           , cdagen = ?  \n";
        l_query  += "           , provv1_p = ?  \n";
        l_query  += "           , provv1_v = ?  \n";
        l_query  += "           , cdsuba = ?  \n";
        l_query  += "           , provv2_p = ?  \n";
        l_query  += "           , provv2_v = ?  \n";
        l_query  += "           , tkproc1 = ?  \n";
        l_query  += "           , cdentp1 = ?  \n";
        l_query  += "           , tkproc2 = ?  \n";
        l_query  += "           , cdentp2 = ?  \n";
        l_query  += "           , cdtlis = ?  \n";
        l_query  += "           , dtrlis = ?  \n";
        l_query  += "           , accred = ?  \n";
        l_query  += "           , antici = ?  \n";
        l_query  += "           , abibap = ?  \n";
        l_query  += "           , cabbap = ?  \n";
        l_query  += "           , vacodi = ?  \n";
        l_query  += "           , cdclsc = ?  \n";
        l_query  += "           , scrig1 = ?  \n";
        l_query  += "           , scrig2 = ?  \n";
        l_query  += "           , scrig3 = ?  \n";
        l_query  += "           , scrig4 = ?  \n";
        l_query  += "           , scrap1 = ?  \n";
        l_query  += "           , scrap2 = ?  \n";
        l_query  += "           , cotraq = ?  \n";
        l_query  += "           , cdiva = ?  \n";
        l_query  += "           , pgcodi = ?  \n";
        l_query  += "           , dpcodi = ?  \n";
        l_query  += "           , cddpag = ?  \n";
        l_query  += "           , ffinme = ?  \n";
        l_query  += "           , tgsca1 = ?  \n";
        l_query  += "           , tgsca2 = ?  \n";
        l_query  += "           , scocas = ?  \n";
        l_query  += "           , cdtrgf = ?  \n";
        l_query  += "           , cdtfat = ?  \n";
        l_query  += "           , cdtdoc = ?  \n";
        l_query  += "           , cdfisc = ?  \n";
        l_query  += "           , cdrmpo = ?  \n";
        l_query  += "           , cdtrme = ?  \n";
        l_query  += "           , cdtaut = ?  \n";
        l_query  += "           , fscagru = ?  \n";
        l_query  += "           , cduldm = ?  \n";
        l_query  += "           , cdentd = ?  \n";
        l_query  += "           , ragcog_de = ?  \n";
        l_query  += "           , indiri_de = ?  \n";
        l_query  += "           , cap_de = ?  \n";
        l_query  += "           , comune_de = ?  \n";
        l_query  += "           , cdprov_de = ?  \n";
        l_query  += "           , cdstat_de = ?  \n";
        l_query  += "           , cdzone = ?  \n";
        l_query  += "           , cdvett1 = ?  \n";
        l_query  += "           , cdentv1 = ?  \n";
        l_query  += "           , cdulv1 = ?  \n";
        l_query  += "           , cdvett2 = ?  \n";
        l_query  += "           , cdentv2 = ?  \n";
        l_query  += "           , cdulv2 = ?  \n";
        l_query  += "           , dsvett3 = ?  \n";
        l_query  += "           , dtstco = ?  \n";
        l_query  += "           , fstcor = ?  \n";
        l_query  += "           , fcorst = ?  \n";
        l_query  += "           , fcouni = ?  \n";
        l_query  += "           , fcator = ?  \n";
        l_query  += "           , fposfm = ?  \n";
        l_query  += "           , cdstab = ?  \n";
        l_query  += "           , tkmaga = ?  \n";
        l_query  += "           , cdling = ?  \n";
        l_query  += "           , cambio = ?  \n";
        l_query  += "           , nrcarta = ?  \n";
        l_query  += "           , dtfivacarta = ?  \n";
        l_query  += "           , cdcarta = ?  \n";
        l_query  += "           , vcmarca = ?  \n";
        l_query  += "           , vcanno = ?  \n";
        l_query  += "           , vcmodello = ?  \n";
        l_query  += "           , vctpcar = ?  \n";
        l_query  += "           , vcnporte = ?  \n";
        l_query  += "           , cdlist = ?  \n";
        l_query  += "           , ivato = ?  \n";
        l_query  += "           , cdaut = ?  \n";
        l_query  += "           , autcarta = ?  \n";
        l_query  += "           , dtautcarta = ?  \n";
        l_query  += "           , tkvaglia = ?  \n";
        l_query  += "           , imptrasp = ?  \n";
        l_query  += "           , fannullato = ?  \n";
        l_query  += "           , cdstato = ?  \n";
        l_query  += "           , fmezzo = ?  \n";
        l_query  += "           , funo = ?  \n";
        l_query  += "           , cogn_p = ?  \n";
        l_query  += "           , indirizzo_p = ?  \n";
        l_query  += "           , localita_p = ?  \n";
        l_query  += "           , cap_p = ?  \n";
        l_query  += "           , provincia_p = ?  \n";
        l_query  += "           , gior_ricmerci = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "           , dtstpre = ?  \n";
        l_query  += "           , cdordi = ?  \n";
        l_query  += "           , fmovmp = ?  \n";
        l_query  += "           , cdtprov = ?  \n";
        l_query  += "           , cdtdoc_prot = ?  \n";
        l_query  += "           , dspag = ?  \n";
        l_query  += "           , scval = ?  \n";
        l_query  += "           , dtdeco = ?  \n";
        l_query  += "           , faspef = ?  \n";
        l_query  += "           , speinc = ?  \n";
        l_query  += "           , nraddimb = ?  \n";
        l_query  += "           , addimb = ?  \n";
        l_query  += "           , addtra = ?  \n";
        l_query  += "           , nrpeso_l = ?  \n";
        l_query  += "           , nrpeso_n = ?  \n";
        l_query  += "           , nrtovo = ?  \n";
        l_query  += "           , desbanc = ?  \n";
        l_query  += "           , cdprel = ?  \n";
        l_query  += "           , cdadde = ?  \n";
        l_query  += "           , tmesc1 = ?  \n";
        l_query  += "           , tmesc2 = ?  \n";
        l_query  += "           , tgsc1m = ?  \n";
        l_query  += "           , tgsc2m = ?  \n";
        l_query  += "           , fgevaso = ?  \n";
        l_query  += "           , cdtdoc_segue = ?  \n";
        l_query  += "           , cdcatm = ?  \n";
        l_query  += "           , provv1_p_var = ?  \n";
        l_query  += "           , provv2_p_var = ?  \n";
        l_query  += "           , provv1_v_var = ?  \n";
        l_query  += "           , provv2_v_var = ?  \n";
        l_query  += "           , cdpagame = ?  \n";
        l_query  += "           , off_cortese_att = ?  \n";
        l_query  += "           , off_persona_rif = ?  \n";
        l_query  += "           , off_oggetto = ?  \n";
        l_query  += "           , tkoffnote = ?  \n";
        l_query  += "           , nrtoco = ?  \n";
        l_query  += "           , cdaebe = ?  \n";
        l_query  += "           , cdcllist = ?  \n";
        l_query  += "           , cdclprc = ?  \n";
        l_query  += "           , cdbanc = ?  \n";
        l_query  += "           , totale_imp = ?  \n";
        l_query  += "           , tkclie_fatt = ?  \n";
        l_query  += "           , cdente_fatt = ?  \n";
        l_query  += "           , cdunil_fatt = ?  \n";
        l_query  += "           , dtlistval = ?  \n";
        l_query  += "           , dtcontrval = ?  \n";
        l_query  += "           , tkutente = ?  \n";
        l_query  += "           , tkordigest = ?  \n";
        l_query  += "           , cdordigest = ?  \n";
        l_query  += "           , tksubutente = ?  \n";  //EAR 20110707
        l_query  += "           , isscontiriga = ?   \n";
        l_query  += "  where tkordi = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cdtorve.equals("")) cdtorve = null;
        if (cdtofv.equals("")) cdtofv = null;
        if (cdrifo.equals("")) cdrifo = null;
        if (cdnsri.equals("")) cdnsri = null;
        if (tkclie.equals("")) tkclie = null;
        if (cdentc.equals("")) cdentc = null;
        if (cdulic.equals("")) cdulic = null;
        if (cdagen.equals("")) cdagen = null;
        if (cdsuba.equals("")) cdsuba = null;
        if (tkproc1.equals("")) tkproc1 = null;
        if (cdentp1.equals("")) cdentp1 = null;
        if (tkproc2.equals("")) tkproc2 = null;
        if (cdentp2.equals("")) cdentp2 = null;
        if (cdtlis.equals("")) cdtlis = null;
        if (abibap.equals("")) abibap = null;
        if (cabbap.equals("")) cabbap = null;
        if (vacodi.equals("")) vacodi = null;
        if (cdclsc.equals("")) cdclsc = null;
        if (cdiva.equals("")) cdiva = null;
        if (pgcodi.equals("")) pgcodi = null;
        if (dpcodi.equals("")) dpcodi = null;
        if (cddpag.equals("")) cddpag = null;
        if (ffinme.equals("")) ffinme = null;
        if (cdtrgf.equals("")) cdtrgf = null;
        if (cdtfat.equals("")) cdtfat = null;
        if (cdtdoc.equals("")) cdtdoc = null;
        if (cdfisc.equals("")) cdfisc = null;
        if (cdrmpo.equals("")) cdrmpo = null;
        if (cdtrme.equals("")) cdtrme = null;
        if (cdtaut.equals("")) cdtaut = null;
        if (fscagru.equals("")) fscagru = null;
        if (cduldm.equals("")) cduldm = null;
        if (cdentd.equals("")) cdentd = null;
        if (ragcog_de.equals("")) ragcog_de = null;
        if (indiri_de.equals("")) indiri_de = null;
        if (cap_de.equals("")) cap_de = null;
        if (comune_de.equals("")) comune_de = null;
        if (cdprov_de.equals("")) cdprov_de = null;
        if (cdstat_de.equals("")) cdstat_de = null;
        if (cdzone.equals("")) cdzone = null;
        if (cdvett1.equals("")) cdvett1 = null;
        if (cdentv1.equals("")) cdentv1 = null;
        if (cdulv1.equals("")) cdulv1 = null;
        if (cdvett2.equals("")) cdvett2 = null;
        if (cdentv2.equals("")) cdentv2 = null;
        if (cdulv2.equals("")) cdulv2 = null;
        if (dsvett3.equals("")) dsvett3 = null;
        if (fstcor.equals("")) fstcor = null;
        if (fcorst.equals("")) fcorst = null;
        if (fcouni.equals("")) fcouni = null;
        if (fcator.equals("")) fcator = null;
        if (fposfm.equals("")) fposfm = null;
        if (cdstab.equals("")) cdstab = null;
        if (cdling.equals("")) cdling = null;
        if (cdazie.equals("")) cdazie = null;
        if (nrcarta.equals("")) nrcarta = null;
        if (cdcarta.equals("")) cdcarta = null;
        if (vcmarca.equals("")) vcmarca = null;
        if (vcmodello.equals("")) vcmodello = null;
        if (vctpcar.equals("")) vctpcar = null;
        if (cdlist.equals("")) cdlist = null;
        if (ivato.equals("")) ivato = null;
        if (cdaut.equals("")) cdaut = null;
        if (autcarta.equals("")) autcarta = null;
        if (tkvaglia.equals("")) tkvaglia = null;
        if (fannullato.equals("")) fannullato = null;
        if (cdstato.equals("")) cdstato = null;
        if (fmezzo.equals("")) fmezzo = null;
        if (funo.equals("")) funo = null;
        if (cogn_p.equals("")) cogn_p = null;
        if (indirizzo_p.equals("")) indirizzo_p = null;
        if (localita_p.equals("")) localita_p = null;
        if (cap_p.equals("")) cap_p = null;
        if (provincia_p.equals("")) provincia_p = null;
        if (gior_ricmerci.equals("")) gior_ricmerci = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;
        if (cdordi.equals("")) cdordi = null;
        if (fmovmp.equals("")) fmovmp = null;
        if (cdtprov.equals("")) cdtprov = null;
        if (cdtdoc_prot.equals("")) cdtdoc_prot = null;
        if (dspag.equals("")) dspag = null;
        if (faspef.equals("")) faspef = null;
        if (desbanc.equals("")) desbanc = null;
        if (cdprel.equals("")) cdprel = null;
        if (cdadde.equals("")) cdadde = null;
        if (fgevaso.equals("")) fgevaso = null;
        if (cdtdoc_segue.equals("")) cdtdoc_segue = null;
        if (cdcatm.equals("")) cdcatm = null;
        if (cdpagame.equals("")) cdpagame = null;
        if (off_cortese_att.equals("")) off_cortese_att = null;
        if (off_persona_rif.equals("")) off_persona_rif = null;
        if (off_oggetto.equals("")) off_oggetto = null;
        if (cdaebe.equals("")) cdaebe = null;
        if (cdcllist.equals("")) cdcllist = null;
        if (cdclprc.equals("")) cdclprc = null;
        if (cdbanc.equals("")) cdbanc = null;
        if (tkclie_fatt.equals("")) tkclie_fatt = null;
        if (cdente_fatt.equals("")) cdente_fatt = null;
        if (cdunil_fatt.equals("")) cdunil_fatt = null;
        if (cdordigest.equals("")) cdordigest = null;
        if (isscontiriga.equals("")) isscontiriga = null;


        ind = 1;
        pstm.setString    (ind, cdtorve); ind += 1;
        if (anno == 0 && null_anno){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, anno); ind += 1;
        } 
        if (numord == 0 && null_numord){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, numord); ind += 1;
        } 
        pstm.setTimestamp (ind, dtordi); ind += 1;
        pstm.setTimestamp (ind, dtfval); ind += 1;
        pstm.setTimestamp (ind, dtcons); ind += 1;
        if (tkoffe == 0 && null_tkoffe){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkoffe); ind += 1;
        } 
        pstm.setString    (ind, cdtofv); ind += 1;
        if (annoof == 0 && null_annoof){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, annoof); ind += 1;
        } 
        if (numoff == 0 && null_numoff){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, numoff); ind += 1;
        } 
        pstm.setString    (ind, cdrifo); ind += 1;
        pstm.setTimestamp (ind, dtrior); ind += 1;
        pstm.setString    (ind, cdnsri); ind += 1;
        pstm.setTimestamp (ind, dtnsri); ind += 1;
        pstm.setString    (ind, tkclie); ind += 1;
        pstm.setString    (ind, cdentc); ind += 1;
        pstm.setString    (ind, cdulic); ind += 1;
        pstm.setString    (ind, cdagen); ind += 1;
        if (provv1_p == 0 && null_provv1_p){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, provv1_p); ind += 1;
        } 
        if (provv1_v == 0 && null_provv1_v){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, provv1_v); ind += 1;
        } 
        pstm.setString    (ind, cdsuba); ind += 1;
        if (provv2_p == 0 && null_provv2_p){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, provv2_p); ind += 1;
        } 
        if (provv2_v == 0 && null_provv2_v){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, provv2_v); ind += 1;
        } 
        pstm.setString    (ind, tkproc1); ind += 1;
        pstm.setString    (ind, cdentp1); ind += 1;
        pstm.setString    (ind, tkproc2); ind += 1;
        pstm.setString    (ind, cdentp2); ind += 1;
        pstm.setString    (ind, cdtlis); ind += 1;
        pstm.setTimestamp (ind, dtrlis); ind += 1;
        if (accred == 0 && null_accred){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, accred); ind += 1;
        } 
        if (antici == 0 && null_antici){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, antici); ind += 1;
        } 
        pstm.setString    (ind, abibap); ind += 1;
        pstm.setString    (ind, cabbap); ind += 1;
        pstm.setString    (ind, vacodi); ind += 1;
        pstm.setString    (ind, cdclsc); ind += 1;
        if (scrig1 == 0 && null_scrig1){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, scrig1); ind += 1;
        } 
        if (scrig2 == 0 && null_scrig2){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, scrig2); ind += 1;
        } 
        if (scrig3 == 0 && null_scrig3){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, scrig3); ind += 1;
        } 
        if (scrig4 == 0 && null_scrig4){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, scrig4); ind += 1;
        } 
        if (scrap1 == 0 && null_scrap1){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, scrap1); ind += 1;
        } 
        if (scrap2 == 0 && null_scrap2){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, scrap2); ind += 1;
        } 
        if (cotraq == 0 && null_cotraq){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, cotraq); ind += 1;
        } 
        pstm.setString    (ind, cdiva); ind += 1;
        pstm.setString    (ind, pgcodi); ind += 1;
        pstm.setString    (ind, dpcodi); ind += 1;
        pstm.setString    (ind, cddpag); ind += 1;
        pstm.setString    (ind, ffinme); ind += 1;
        if (tgsca1 == 0 && null_tgsca1){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tgsca1); ind += 1;
        } 
        if (tgsca2 == 0 && null_tgsca2){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tgsca2); ind += 1;
        } 
        if (scocas == 0 && null_scocas){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, scocas); ind += 1;
        } 
        pstm.setString    (ind, cdtrgf); ind += 1;
        pstm.setString    (ind, cdtfat); ind += 1;
        pstm.setString    (ind, cdtdoc); ind += 1;
        pstm.setString    (ind, cdfisc); ind += 1;
        pstm.setString    (ind, cdrmpo); ind += 1;
        pstm.setString    (ind, cdtrme); ind += 1;
        pstm.setString    (ind, cdtaut); ind += 1;
        pstm.setString    (ind, fscagru); ind += 1;
        pstm.setString    (ind, cduldm); ind += 1;
        pstm.setString    (ind, cdentd); ind += 1;
        pstm.setString    (ind, ragcog_de); ind += 1;
        pstm.setString    (ind, indiri_de); ind += 1;
        pstm.setString    (ind, cap_de); ind += 1;
        pstm.setString    (ind, comune_de); ind += 1;
        pstm.setString    (ind, cdprov_de); ind += 1;
        pstm.setString    (ind, cdstat_de); ind += 1;
        pstm.setString    (ind, cdzone); ind += 1;
        pstm.setString    (ind, cdvett1); ind += 1;
        pstm.setString    (ind, cdentv1); ind += 1;
        pstm.setString    (ind, cdulv1); ind += 1;
        pstm.setString    (ind, cdvett2); ind += 1;
        pstm.setString    (ind, cdentv2); ind += 1;
        pstm.setString    (ind, cdulv2); ind += 1;
        pstm.setString    (ind, dsvett3); ind += 1;
        pstm.setTimestamp (ind, dtstco); ind += 1;
        pstm.setString    (ind, fstcor); ind += 1;
        pstm.setString    (ind, fcorst); ind += 1;
        pstm.setString    (ind, fcouni); ind += 1;
        pstm.setString    (ind, fcator); ind += 1;
        pstm.setString    (ind, fposfm); ind += 1;
        pstm.setString    (ind, cdstab); ind += 1;
        if (tkmaga == 0 && null_tkmaga){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkmaga); ind += 1;
        } 
        pstm.setString    (ind, cdling); ind += 1;
        if (cambio == 0 && null_cambio){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, cambio); ind += 1;
        } 
        pstm.setString    (ind, nrcarta); ind += 1;
        pstm.setTimestamp (ind, dtfivacarta); ind += 1;
        pstm.setString    (ind, cdcarta); ind += 1;
        pstm.setString    (ind, vcmarca); ind += 1;
        if (vcanno == 0 && null_vcanno){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, vcanno); ind += 1;
        } 
        pstm.setString    (ind, vcmodello); ind += 1;
        pstm.setString    (ind, vctpcar); ind += 1;
        if (vcnporte == 0 && null_vcnporte){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, vcnporte); ind += 1;
        } 
        pstm.setString    (ind, cdlist); ind += 1;
        pstm.setString    (ind, ivato); ind += 1;
        pstm.setString    (ind, cdaut); ind += 1;
        pstm.setString    (ind, autcarta); ind += 1;
        pstm.setTimestamp (ind, dtautcarta); ind += 1;
        pstm.setString    (ind, tkvaglia); ind += 1;
        if (imptrasp == 0 && null_imptrasp){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, imptrasp); ind += 1;
        } 
        pstm.setString    (ind, fannullato); ind += 1;
        pstm.setString    (ind, cdstato); ind += 1;
        pstm.setString    (ind, fmezzo); ind += 1;
        pstm.setString    (ind, funo); ind += 1;
        pstm.setString    (ind, cogn_p); ind += 1;
        pstm.setString    (ind, indirizzo_p); ind += 1;
        pstm.setString    (ind, localita_p); ind += 1;
        pstm.setString    (ind, cap_p); ind += 1;
        pstm.setString    (ind, provincia_p); ind += 1;
        pstm.setString    (ind, gior_ricmerci); ind += 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;
        pstm.setTimestamp (ind, dtstpre); ind += 1;
        pstm.setString    (ind, cdordi); ind += 1;
        pstm.setString    (ind, fmovmp); ind += 1;
        pstm.setString    (ind, cdtprov); ind += 1;
        pstm.setString    (ind, cdtdoc_prot); ind += 1;
        pstm.setString    (ind, dspag); ind += 1;
        if (scval == 0 && null_scval){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, scval); ind += 1;
        } 
        pstm.setTimestamp (ind, dtdeco); ind += 1;
        pstm.setString    (ind, faspef); ind += 1;
        if (speinc == 0 && null_speinc){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, speinc); ind += 1;
        } 
        if (nraddimb == 0 && null_nraddimb){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, nraddimb); ind += 1;
        } 
        if (addimb == 0 && null_addimb){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, addimb); ind += 1;
        } 
        if (addtra == 0 && null_addtra){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, addtra); ind += 1;
        } 
        if (nrpeso_l == 0 && null_nrpeso_l){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, nrpeso_l); ind += 1;
        } 
        if (nrpeso_n == 0 && null_nrpeso_n){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, nrpeso_n); ind += 1;
        } 
        if (nrtovo == 0 && null_nrtovo){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, nrtovo); ind += 1;
        } 
        pstm.setString    (ind, desbanc); ind += 1;
        pstm.setString    (ind, cdprel); ind += 1;
        pstm.setString    (ind, cdadde); ind += 1;
        if (tmesc1 == 0 && null_tmesc1){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tmesc1); ind += 1;
        } 
        if (tmesc2 == 0 && null_tmesc2){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tmesc2); ind += 1;
        } 
        if (tgsc1m == 0 && null_tgsc1m){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tgsc1m); ind += 1;
        } 
        if (tgsc2m == 0 && null_tgsc2m){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tgsc2m); ind += 1;
        } 
        pstm.setString    (ind, fgevaso); ind += 1;
        pstm.setString    (ind, cdtdoc_segue); ind += 1;
        pstm.setString    (ind, cdcatm); ind += 1;
        if (provv1_p_var == 0 && null_provv1_p_var){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, provv1_p_var); ind += 1;
        } 
        if (provv2_p_var == 0 && null_provv2_p_var){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, provv2_p_var); ind += 1;
        } 
        if (provv1_v_var == 0 && null_provv1_v_var){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, provv1_v_var); ind += 1;
        } 
        if (provv2_v_var == 0 && null_provv2_v_var){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, provv2_v_var); ind += 1;
        } 
        pstm.setString    (ind, cdpagame); ind += 1;
        pstm.setString    (ind, off_cortese_att); ind += 1;
        pstm.setString    (ind, off_persona_rif); ind += 1;
        pstm.setString    (ind, off_oggetto); ind += 1;
        if (tkoffnote == 0 && null_tkoffnote){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkoffnote); ind += 1;
        } 
        if (nrtoco == 0 && null_nrtoco){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, nrtoco); ind += 1;
        } 
        pstm.setString    (ind, cdaebe); ind += 1;
        pstm.setString    (ind, cdcllist); ind += 1;
        pstm.setString    (ind, cdclprc); ind += 1;
        pstm.setString    (ind, cdbanc); ind += 1;
        if (totale_imp == 0 && null_totale_imp){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, totale_imp); ind += 1;
        } 
        pstm.setString    (ind, tkclie_fatt); ind += 1;
        pstm.setString    (ind, cdente_fatt); ind += 1;
        pstm.setString    (ind, cdunil_fatt); ind += 1;
        pstm.setTimestamp (ind, dtlistval); ind += 1;
        pstm.setTimestamp (ind, dtcontrval); ind += 1;
        if (tkutente == 0 && null_tkutente){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkutente); ind += 1;
        } 
        if (tkordigest == 0 && null_tkordigest){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkordigest); ind += 1;
        } 
        pstm.setString    (ind, cdordigest); ind += 1;
        //EAR 20110707
        if (tksubutente == 0 && null_tksubutente){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tksubutente); ind += 1;
        }     
        
        pstm.setString    (ind, isscontiriga); ind += 1;

        pstm.setLong      (ind, tkordi); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.tkordi = tkordi; 

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public long       tkordi = 0; 






    // --- Impostando a false queste prop. il def. � 0

    public boolean ib_calcola_token = true;
    public boolean null_tkordi = true;
    public boolean null_anno = true;
    public boolean null_numord = true;
    public boolean null_tkoffe = true;
    public boolean null_annoof = true;
    public boolean null_numoff = true;
    public boolean null_provv1_p = true;
    public boolean null_provv1_v = true;
    public boolean null_provv2_p = true;
    public boolean null_provv2_v = true;
    public boolean null_accred = true;
    public boolean null_antici = true;
    public boolean null_scrig1 = true;
    public boolean null_scrig2 = true;
    public boolean null_scrig3 = true;
    public boolean null_scrig4 = true;
    public boolean null_scrap1 = true;
    public boolean null_scrap2 = true;
    public boolean null_cotraq = true;
    public boolean null_tgsca1 = true;
    public boolean null_tgsca2 = true;
    public boolean null_scocas = true;
    public boolean null_tkmaga = true;
    public boolean null_cambio = true;
    public boolean null_vcanno = true;
    public boolean null_vcnporte = true;
    public boolean null_imptrasp = true;
    public boolean null_scval = true;
    public boolean null_speinc = true;
    public boolean null_nraddimb = true;
    public boolean null_addimb = true;
    public boolean null_addtra = true;
    public boolean null_nrpeso_l = true;
    public boolean null_nrpeso_n = true;
    public boolean null_nrtovo = true;
    public boolean null_tmesc1 = true;
    public boolean null_tmesc2 = true;
    public boolean null_tgsc1m = true;
    public boolean null_tgsc2m = true;
    public boolean null_provv1_p_var = true;
    public boolean null_provv2_p_var = true;
    public boolean null_provv1_v_var = true;
    public boolean null_provv2_v_var = true;
    public boolean null_tkoffnote = true;
    public boolean null_nrtoco = true;
    public boolean null_totale_imp = true;
    public boolean null_tkutente = true;
    public boolean null_tkordigest = true;
    public boolean null_tksubutente = true;  //EAR 20110707







}

