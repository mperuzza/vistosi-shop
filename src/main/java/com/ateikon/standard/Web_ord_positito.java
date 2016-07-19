package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Web_ord_positito extends Atk_sql {

    public Web_ord_positito() {

        super();
    }




    /****
        getAll: web_ord_positito
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.web_ord_positito  \n";

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: web_ord_positito
    **/ 

    public ResultSet getKey( long       tkposi
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.web_ord_positito  \n";
        l_query  += "  where tkposi = ? \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setLong      (ind, tkposi); ind += 1;

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: web_ord_positito
    **/ 


    public com.ateikon.structure.Rec_web_ord_positito  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_web_ord_positito lstr = new com.ateikon.structure.Rec_web_ord_positito();

        if (rs == null) return lstr;
        if (rs.getObject("tkposi")!=null) lstr.tkposi = rs.getLong      ("tkposi"); 
        if (rs.getObject("tkordi")!=null) lstr.tkordi = rs.getLong      ("tkordi"); 
        if (rs.getObject("tkposi_off")!=null) lstr.tkposi_off = rs.getLong      ("tkposi_off"); 
        if (rs.getObject("ripoor")!=null) lstr.ripoor = rs.getString    ("ripoor"); 
        if (rs.getObject("nrposi")!=null) lstr.nrposi = rs.getLong      ("nrposi"); 
        if (rs.getObject("nrposi_v")!=null) lstr.nrposi_v = rs.getLong      ("nrposi_v"); 
        if (rs.getObject("cdarti")!=null) lstr.cdarti = rs.getString    ("cdarti"); 
        if (rs.getObject("dssart")!=null) lstr.dssart = rs.getString    ("dssart"); 
        if (rs.getObject("cdunim")!=null) lstr.cdunim = rs.getString    ("cdunim"); 
        if (rs.getObject("fatconv")!=null) lstr.fatconv = rs.getDouble    ("fatconv"); 
        if (rs.getObject("cdunim2")!=null) lstr.cdunim2 = rs.getString    ("cdunim2"); 
        if (rs.getObject("cdfisc")!=null) lstr.cdfisc = rs.getString    ("cdfisc"); 
        if (rs.getObject("cdiva")!=null) lstr.cdiva = rs.getString    ("cdiva"); 
        if (rs.getObject("qtatot")!=null) lstr.qtatot = rs.getDouble    ("qtatot"); 
        if (rs.getObject("impuni")!=null) lstr.impuni = rs.getDouble    ("impuni"); 
        if (rs.getObject("scont1")!=null) lstr.scont1 = rs.getDouble    ("scont1"); 
        if (rs.getObject("scont2")!=null) lstr.scont2 = rs.getDouble    ("scont2"); 
        if (rs.getObject("scont3")!=null) lstr.scont3 = rs.getDouble    ("scont3"); 
        if (rs.getObject("scont4")!=null) lstr.scont4 = rs.getDouble    ("scont4"); 
        if (rs.getObject("cdagen_1")!=null) lstr.cdagen_1 = rs.getString    ("cdagen_1"); 
        if (rs.getObject("provv1_p")!=null) lstr.provv1_p = rs.getDouble    ("provv1_p"); 
        if (rs.getObject("provv1_v")!=null) lstr.provv1_v = rs.getDouble    ("provv1_v"); 
        if (rs.getObject("cdagen_2")!=null) lstr.cdagen_2 = rs.getString    ("cdagen_2"); 
        if (rs.getObject("provv2_p")!=null) lstr.provv2_p = rs.getDouble    ("provv2_p"); 
        if (rs.getObject("provv2_v")!=null) lstr.provv2_v = rs.getDouble    ("provv2_v"); 
        if (rs.getObject("tkproc1")!=null) lstr.tkproc1 = rs.getString    ("tkproc1"); 
        if (rs.getObject("cdentp1")!=null) lstr.cdentp1 = rs.getString    ("cdentp1"); 
        if (rs.getObject("comp_proc1")!=null) lstr.comp_proc1 = rs.getDouble    ("comp_proc1"); 
        if (rs.getObject("prov_proc1")!=null) lstr.prov_proc1 = rs.getDouble    ("prov_proc1"); 
        if (rs.getObject("tkproc2")!=null) lstr.tkproc2 = rs.getString    ("tkproc2"); 
        if (rs.getObject("cdentp2")!=null) lstr.cdentp2 = rs.getString    ("cdentp2"); 
        if (rs.getObject("comp_proc2")!=null) lstr.comp_proc2 = rs.getDouble    ("comp_proc2"); 
        if (rs.getObject("prov_proc2")!=null) lstr.prov_proc2 = rs.getDouble    ("prov_proc2"); 
        if (rs.getObject("couimb")!=null) lstr.couimb = rs.getDouble    ("couimb"); 
        if (rs.getObject("peuimb")!=null) lstr.peuimb = rs.getDouble    ("peuimb"); 
        if (rs.getObject("peumer")!=null) lstr.peumer = rs.getDouble    ("peumer"); 
        if (rs.getObject("voumer")!=null) lstr.voumer = rs.getDouble    ("voumer"); 
        if (rs.getObject("impgru")!=null) lstr.impgru = rs.getDouble    ("impgru"); 
        if (rs.getObject("impposa")!=null) lstr.impposa = rs.getDouble    ("impposa"); 
        if (rs.getObject("impnolo")!=null) lstr.impnolo = rs.getDouble    ("impnolo"); 
        if (rs.getObject("nroper")!=null) lstr.nroper = rs.getLong      ("nroper"); 
        if (rs.getObject("rifriga")!=null) lstr.rifriga = rs.getString    ("rifriga"); 
        if (rs.getObject("npag")!=null) lstr.npag = rs.getString    ("npag"); 
        if (rs.getObject("qtaprel")!=null) lstr.qtaprel = rs.getDouble    ("qtaprel"); 
        if (rs.getObject("qtacons")!=null) lstr.qtacons = rs.getDouble    ("qtacons"); 
        if (rs.getObject("fgsaldo")!=null) lstr.fgsaldo = rs.getString    ("fgsaldo"); 
        if (rs.getObject("dtcons")!=null) lstr.dtcons = rs.getTimestamp ("dtcons"); 
        if (rs.getObject("cdlist")!=null) lstr.cdlist = rs.getString    ("cdlist"); 
        if (rs.getObject("cdclsc")!=null) lstr.cdclsc = rs.getString    ("cdclsc"); 
        if (rs.getObject("cdstato")!=null) lstr.cdstato = rs.getString    ("cdstato"); 
        if (rs.getObject("cdcoll")!=null) lstr.cdcoll = rs.getString    ("cdcoll"); 
        if (rs.getObject("tklisttag")!=null) lstr.tklisttag = rs.getLong      ("tklisttag"); 
        if (rs.getObject("cdtpsco")!=null) lstr.cdtpsco = rs.getString    ("cdtpsco"); 
        if (rs.getObject("notatag")!=null) lstr.notatag = rs.getString    ("notatag"); 
        if (rs.getObject("notaven")!=null) lstr.notaven = rs.getString    ("notaven"); 
        if (rs.getObject("cdtpar")!=null) lstr.cdtpar = rs.getString    ("cdtpar"); 
        if (rs.getObject("nrparl")!=null) lstr.nrparl = rs.getString    ("nrparl"); 
        if (rs.getObject("qtalav")!=null) lstr.qtalav = rs.getDouble    ("qtalav"); 
        if (rs.getObject("cdadde")!=null) lstr.cdadde = rs.getString    ("cdadde"); 
        if (rs.getObject("cdcatm")!=null) lstr.cdcatm = rs.getString    ("cdcatm"); 
        if (rs.getObject("tkmaga")!=null) lstr.tkmaga = rs.getLong      ("tkmaga"); 
        if (rs.getObject("cdkit")!=null) lstr.cdkit = rs.getString    ("cdkit"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cdprom")!=null) lstr.cdprom = rs.getString    ("cdprom"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("ncolli")!=null) lstr.ncolli = rs.getLong      ("ncolli"); 
        if (rs.getObject("pzcolli")!=null) lstr.pzcolli = rs.getLong      ("pzcolli"); 
        if (rs.getObject("dimena")!=null) lstr.dimena = rs.getDouble    ("dimena"); 
        if (rs.getObject("dimenb")!=null) lstr.dimenb = rs.getDouble    ("dimenb"); 
        if (rs.getObject("dimenc")!=null) lstr.dimenc = rs.getDouble    ("dimenc"); 
        if (rs.getObject("cdtins")!=null) lstr.cdtins = rs.getString    ("cdtins"); 
        if (rs.getObject("cdvar")!=null) lstr.cdvar = rs.getString    ("cdvar"); 
        if (rs.getObject("cdrifo")!=null) lstr.cdrifo = rs.getString    ("cdrifo"); 
        if (rs.getObject("fsped")!=null) lstr.fsped = rs.getString    ("fsped"); 
        if (rs.getObject("importonettoriga")!=null) lstr.importonettoriga = rs.getDouble    ("importonettoriga"); 
        if (rs.getObject("importoriga")!=null) lstr.importoriga = rs.getDouble    ("importoriga"); 
        if (rs.getObject("impuninetto")!=null) lstr.impuninetto = rs.getDouble    ("impuninetto"); 
        if (rs.getObject("scrap1")!=null) lstr.scrap1 = rs.getDouble    ("scrap1"); 
        if (rs.getObject("scrap2")!=null) lstr.scrap2 = rs.getDouble    ("scrap2"); 
        if (rs.getObject("qtcons_s")!=null) lstr.qtcons_s = rs.getDouble    ("qtcons_s"); 
        if (rs.getObject("scval")!=null) lstr.scval = rs.getDouble    ("scval"); 
        if (rs.getObject("fpzman")!=null) lstr.fpzman = rs.getString    ("fpzman"); 
        if (rs.getObject("scontirep")!=null) lstr.scontirep = rs.getLong      ("scontirep"); 
        if (rs.getObject("pzxcollo")!=null) lstr.pzxcollo = rs.getDouble    ("pzxcollo"); 
        if (rs.getObject("cdarti_imb")!=null) lstr.cdarti_imb = rs.getString    ("cdarti_imb"); 
        if (rs.getObject("flivprovv")!=null) lstr.flivprovv = rs.getString    ("flivprovv"); 
        if (rs.getObject("provv1_p_var")!=null) lstr.provv1_p_var = rs.getDouble    ("provv1_p_var"); 
        if (rs.getObject("provv2_p_var")!=null) lstr.provv2_p_var = rs.getDouble    ("provv2_p_var"); 
        if (rs.getObject("provv1_v_var")!=null) lstr.provv1_v_var = rs.getDouble    ("provv1_v_var"); 
        if (rs.getObject("provv2_v_var")!=null) lstr.provv2_v_var = rs.getDouble    ("provv2_v_var"); 
        if (rs.getObject("cdartprod_nc")!=null) lstr.cdartprod_nc = rs.getString    ("cdartprod_nc"); 
        if (rs.getObject("fnettosc")!=null) lstr.fnettosc = rs.getString    ("fnettosc"); 
        if (rs.getObject("tkforn")!=null) lstr.tkforn = rs.getString    ("tkforn"); 
        if (rs.getObject("impuni_acq")!=null) lstr.impuni_acq = rs.getDouble    ("impuni_acq"); 
        if (rs.getObject("sconto1_acq")!=null) lstr.sconto1_acq = rs.getDouble    ("sconto1_acq"); 
        if (rs.getObject("sconto2_acq")!=null) lstr.sconto2_acq = rs.getDouble    ("sconto2_acq"); 
        if (rs.getObject("fordacq")!=null) lstr.fordacq = rs.getString    ("fordacq"); 
        if (rs.getObject("nlisprel")!=null) lstr.nlisprel = rs.getLong      ("nlisprel"); 
        if (rs.getObject("tkposi_contr")!=null) lstr.tkposi_contr = rs.getLong      ("tkposi_contr"); 
        if (rs.getObject("tkordigest")!=null) lstr.tkordigest = rs.getLong      ("tkordigest"); 
        if (rs.getObject("tkposigest")!=null) lstr.tkposigest = rs.getLong      ("tkposigest"); 

        return lstr;
    }




    /****
        execute: web_ord_positito
    **/ 


    public int execute ( com.ateikon.structure.Rec_web_ord_positito astr) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec =  execute ( astr.tkposi
                          , astr.tkordi
                          , astr.tkposi_off
                          , astr.ripoor
                          , astr.nrposi
                          , astr.nrposi_v
                          , astr.cdarti
                          , astr.dssart
                          , astr.cdunim
                          , astr.fatconv
                          , astr.cdunim2
                          , astr.cdfisc
                          , astr.cdiva
                          , astr.qtatot
                          , astr.impuni
                          , astr.scont1
                          , astr.scont2
                          , astr.scont3
                          , astr.scont4
                          , astr.cdagen_1
                          , astr.provv1_p
                          , astr.provv1_v
                          , astr.cdagen_2
                          , astr.provv2_p
                          , astr.provv2_v
                          , astr.tkproc1
                          , astr.cdentp1
                          , astr.comp_proc1
                          , astr.prov_proc1
                          , astr.tkproc2
                          , astr.cdentp2
                          , astr.comp_proc2
                          , astr.prov_proc2
                          , astr.couimb
                          , astr.peuimb
                          , astr.peumer
                          , astr.voumer
                          , astr.impgru
                          , astr.impposa
                          , astr.impnolo
                          , astr.nroper
                          , astr.rifriga
                          , astr.npag
                          , astr.qtaprel
                          , astr.qtacons
                          , astr.fgsaldo
                          , astr.dtcons
                          , astr.cdlist
                          , astr.cdclsc
                          , astr.cdstato
                          , astr.cdcoll
                          , astr.tklisttag
                          , astr.cdtpsco
                          , astr.notatag
                          , astr.notaven
                          , astr.cdtpar
                          , astr.nrparl
                          , astr.qtalav
                          , astr.cdadde
                          , astr.cdcatm
                          , astr.tkmaga
                          , astr.cdkit
                          , astr.cdprom
                          , astr.ncolli
                          , astr.pzcolli
                          , astr.dimena
                          , astr.dimenb
                          , astr.dimenc
                          , astr.cdtins
                          , astr.cdvar
                          , astr.cdrifo
                          , astr.fsped
                          , astr.importonettoriga
                          , astr.importoriga
                          , astr.impuninetto
                          , astr.scrap1
                          , astr.scrap2
                          , astr.qtcons_s
                          , astr.scval
                          , astr.fpzman
                          , astr.scontirep
                          , astr.pzxcollo
                          , astr.cdarti_imb
                          , astr.flivprovv
                          , astr.provv1_p_var
                          , astr.provv2_p_var
                          , astr.provv1_v_var
                          , astr.provv2_v_var
                          , astr.cdartprod_nc
                          , astr.fnettosc
                          , astr.tkforn
                          , astr.impuni_acq
                          , astr.sconto1_acq
                          , astr.sconto2_acq
                          , astr.fordacq
                          , astr.nlisprel
                          , astr.tkposi_contr
                          , astr.tkordigest
                          , astr.tkposigest
                          , astr.cdartirif //EAR-20091012
                          , astr.fgpromo   //EAR-20111203
                           );

        return tot_rec;
    }




    /****
        execute: web_ord_positito
    **/ 


    public int execute ( long       tkposi
                       , long       tkordi
                       , long       tkposi_off
                       , String     ripoor
                       , long       nrposi
                       , long       nrposi_v
                       , String     cdarti
                       , String     dssart
                       , String     cdunim
                       , double     fatconv
                       , String     cdunim2
                       , String     cdfisc
                       , String     cdiva
                       , double     qtatot
                       , double     impuni
                       , double     scont1
                       , double     scont2
                       , double     scont3
                       , double     scont4
                       , String     cdagen_1
                       , double     provv1_p
                       , double     provv1_v
                       , String     cdagen_2
                       , double     provv2_p
                       , double     provv2_v
                       , String     tkproc1
                       , String     cdentp1
                       , double     comp_proc1
                       , double     prov_proc1
                       , String     tkproc2
                       , String     cdentp2
                       , double     comp_proc2
                       , double     prov_proc2
                       , double     couimb
                       , double     peuimb
                       , double     peumer
                       , double     voumer
                       , double     impgru
                       , double     impposa
                       , double     impnolo
                       , long       nroper
                       , String     rifriga
                       , String     npag
                       , double     qtaprel
                       , double     qtacons
                       , String     fgsaldo
                       , Timestamp  dtcons
                       , String     cdlist
                       , String     cdclsc
                       , String     cdstato
                       , String     cdcoll
                       , long       tklisttag
                       , String     cdtpsco
                       , String     notatag
                       , String     notaven
                       , String     cdtpar
                       , String     nrparl
                       , double     qtalav
                       , String     cdadde
                       , String     cdcatm
                       , long       tkmaga
                       , String     cdkit
                       , String     cdprom
                       , long       ncolli
                       , long       pzcolli
                       , double     dimena
                       , double     dimenb
                       , double     dimenc
                       , String     cdtins
                       , String     cdvar
                       , String     cdrifo
                       , String     fsped
                       , double     importonettoriga
                       , double     importoriga
                       , double     impuninetto
                       , double     scrap1
                       , double     scrap2
                       , double     qtcons_s
                       , double     scval
                       , String     fpzman
                       , long       scontirep
                       , double     pzxcollo
                       , String     cdarti_imb
                       , String     flivprovv
                       , double     provv1_p_var
                       , double     provv2_p_var
                       , double     provv1_v_var
                       , double     provv2_v_var
                       , String     cdartprod_nc
                       , String     fnettosc
                       , String     tkforn
                       , double     impuni_acq
                       , double     sconto1_acq
                       , double     sconto2_acq
                       , String     fordacq
                       , long       nlisprel
                       , long       tkposi_contr
                       , long       tkordigest
                       , long       tkposigest
                       , String     cdartirif //EAR-20091012
                       , String     fgpromo   //EAR-20111203
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " select tkposi\n";
        l_query  += "   from pgmr.web_ord_positito  \n";
        l_query  += "  where tkposi = ? \n";


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (tkposi == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkposi); ind += 1;
        } 

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            pstm.close();
            tot_rec = executeUpdate ( tkposi
                                    , tkordi
                                    , tkposi_off
                                    , ripoor
                                    , nrposi
                                    , nrposi_v
                                    , cdarti
                                    , dssart
                                    , cdunim
                                    , fatconv
                                    , cdunim2
                                    , cdfisc
                                    , cdiva
                                    , qtatot
                                    , impuni
                                    , scont1
                                    , scont2
                                    , scont3
                                    , scont4
                                    , cdagen_1
                                    , provv1_p
                                    , provv1_v
                                    , cdagen_2
                                    , provv2_p
                                    , provv2_v
                                    , tkproc1
                                    , cdentp1
                                    , comp_proc1
                                    , prov_proc1
                                    , tkproc2
                                    , cdentp2
                                    , comp_proc2
                                    , prov_proc2
                                    , couimb
                                    , peuimb
                                    , peumer
                                    , voumer
                                    , impgru
                                    , impposa
                                    , impnolo
                                    , nroper
                                    , rifriga
                                    , npag
                                    , qtaprel
                                    , qtacons
                                    , fgsaldo
                                    , dtcons
                                    , cdlist
                                    , cdclsc
                                    , cdstato
                                    , cdcoll
                                    , tklisttag
                                    , cdtpsco
                                    , notatag
                                    , notaven
                                    , cdtpar
                                    , nrparl
                                    , qtalav
                                    , cdadde
                                    , cdcatm
                                    , tkmaga
                                    , cdkit
                                    , cdprom
                                    , ncolli
                                    , pzcolli
                                    , dimena
                                    , dimenb
                                    , dimenc
                                    , cdtins
                                    , cdvar
                                    , cdrifo
                                    , fsped
                                    , importonettoriga
                                    , importoriga
                                    , impuninetto
                                    , scrap1
                                    , scrap2
                                    , qtcons_s
                                    , scval
                                    , fpzman
                                    , scontirep
                                    , pzxcollo
                                    , cdarti_imb
                                    , flivprovv
                                    , provv1_p_var
                                    , provv2_p_var
                                    , provv1_v_var
                                    , provv2_v_var
                                    , cdartprod_nc
                                    , fnettosc
                                    , tkforn
                                    , impuni_acq
                                    , sconto1_acq
                                    , sconto2_acq
                                    , fordacq
                                    , nlisprel
                                    , tkposi_contr
                                    , tkordigest
                                    , tkposigest
                                    , cdartirif //EAR-20091012
                                    , fgpromo   //EAR-20111203
                                    );
        }else {

            pstm.close();
            tot_rec = executeInsert ( tkposi
                                    , tkordi
                                    , tkposi_off
                                    , ripoor
                                    , nrposi
                                    , nrposi_v
                                    , cdarti
                                    , dssart
                                    , cdunim
                                    , fatconv
                                    , cdunim2
                                    , cdfisc
                                    , cdiva
                                    , qtatot
                                    , impuni
                                    , scont1
                                    , scont2
                                    , scont3
                                    , scont4
                                    , cdagen_1
                                    , provv1_p
                                    , provv1_v
                                    , cdagen_2
                                    , provv2_p
                                    , provv2_v
                                    , tkproc1
                                    , cdentp1
                                    , comp_proc1
                                    , prov_proc1
                                    , tkproc2
                                    , cdentp2
                                    , comp_proc2
                                    , prov_proc2
                                    , couimb
                                    , peuimb
                                    , peumer
                                    , voumer
                                    , impgru
                                    , impposa
                                    , impnolo
                                    , nroper
                                    , rifriga
                                    , npag
                                    , qtaprel
                                    , qtacons
                                    , fgsaldo
                                    , dtcons
                                    , cdlist
                                    , cdclsc
                                    , cdstato
                                    , cdcoll
                                    , tklisttag
                                    , cdtpsco
                                    , notatag
                                    , notaven
                                    , cdtpar
                                    , nrparl
                                    , qtalav
                                    , cdadde
                                    , cdcatm
                                    , tkmaga
                                    , cdkit
                                    , cdprom
                                    , ncolli
                                    , pzcolli
                                    , dimena
                                    , dimenb
                                    , dimenc
                                    , cdtins
                                    , cdvar
                                    , cdrifo
                                    , fsped
                                    , importonettoriga
                                    , importoriga
                                    , impuninetto
                                    , scrap1
                                    , scrap2
                                    , qtcons_s
                                    , scval
                                    , fpzman
                                    , scontirep
                                    , pzxcollo
                                    , cdarti_imb
                                    , flivprovv
                                    , provv1_p_var
                                    , provv2_p_var
                                    , provv1_v_var
                                    , provv2_v_var
                                    , cdartprod_nc
                                    , fnettosc
                                    , tkforn
                                    , impuni_acq
                                    , sconto1_acq
                                    , sconto2_acq
                                    , fordacq
                                    , nlisprel
                                    , tkposi_contr
                                    , tkordigest
                                    , tkposigest
                                    , cdartirif //EAR-20091012
                                    , fgpromo   //EAR-20111203
                                    );
        }

        if (tot_rec != 1){

            m_connection.rollback();
            throw new Exception("Errore Agg. web_ord_positito! ");
        }

        return tot_rec;
    }




    /****
        executeInsert: web_ord_positito
    **/ 


    public int executeInsert( long       tkposi
                            , long       tkordi
                            , long       tkposi_off
                            , String     ripoor
                            , long       nrposi
                            , long       nrposi_v
                            , String     cdarti
                            , String     dssart
                            , String     cdunim
                            , double     fatconv
                            , String     cdunim2
                            , String     cdfisc
                            , String     cdiva
                            , double     qtatot
                            , double     impuni
                            , double     scont1
                            , double     scont2
                            , double     scont3
                            , double     scont4
                            , String     cdagen_1
                            , double     provv1_p
                            , double     provv1_v
                            , String     cdagen_2
                            , double     provv2_p
                            , double     provv2_v
                            , String     tkproc1
                            , String     cdentp1
                            , double     comp_proc1
                            , double     prov_proc1
                            , String     tkproc2
                            , String     cdentp2
                            , double     comp_proc2
                            , double     prov_proc2
                            , double     couimb
                            , double     peuimb
                            , double     peumer
                            , double     voumer
                            , double     impgru
                            , double     impposa
                            , double     impnolo
                            , long       nroper
                            , String     rifriga
                            , String     npag
                            , double     qtaprel
                            , double     qtacons
                            , String     fgsaldo
                            , Timestamp  dtcons
                            , String     cdlist
                            , String     cdclsc
                            , String     cdstato
                            , String     cdcoll
                            , long       tklisttag
                            , String     cdtpsco
                            , String     notatag
                            , String     notaven
                            , String     cdtpar
                            , String     nrparl
                            , double     qtalav
                            , String     cdadde
                            , String     cdcatm
                            , long       tkmaga
                            , String     cdkit
                            , String     cdprom
                            , long       ncolli
                            , long       pzcolli
                            , double     dimena
                            , double     dimenb
                            , double     dimenc
                            , String     cdtins
                            , String     cdvar
                            , String     cdrifo
                            , String     fsped
                            , double     importonettoriga
                            , double     importoriga
                            , double     impuninetto
                            , double     scrap1
                            , double     scrap2
                            , double     qtcons_s
                            , double     scval
                            , String     fpzman
                            , long       scontirep
                            , double     pzxcollo
                            , String     cdarti_imb
                            , String     flivprovv
                            , double     provv1_p_var
                            , double     provv2_p_var
                            , double     provv1_v_var
                            , double     provv2_v_var
                            , String     cdartprod_nc
                            , String     fnettosc
                            , String     tkforn
                            , double     impuni_acq
                            , double     sconto1_acq
                            , double     sconto2_acq
                            , String     fordacq
                            , long       nlisprel
                            , long       tkposi_contr
                            , long       tkordigest
                            , long       tkposigest
                            , String     cdartirif //EAR-20091012
                            , String     fgpromo   //EAR-20111203
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        if (ib_calcola_token){
            long ll_tk = getNew_token( );

            if (ll_tk <= 0){
                throw new Exception ("Error Keypool (web_ord_positito): "+ll_tk );
            }

            this.tkposi = ll_tk;
            tkposi = this.tkposi;
        }



        l_query   = "";
        l_query  += " insert into pgmr.web_ord_positito ( \n";
        l_query  += "             tkposi   \n";
        l_query  += "           , tkordi   \n";
        l_query  += "           , tkposi_off   \n";
        l_query  += "           , ripoor   \n";
        l_query  += "           , nrposi   \n";
        l_query  += "           , nrposi_v   \n";
        l_query  += "           , cdarti   \n";
        l_query  += "           , dssart   \n";
        l_query  += "           , cdunim   \n";
        l_query  += "           , fatconv   \n";
        l_query  += "           , cdunim2   \n";
        l_query  += "           , cdfisc   \n";
        l_query  += "           , cdiva   \n";
        l_query  += "           , qtatot   \n";
        l_query  += "           , impuni   \n";
        l_query  += "           , scont1   \n";
        l_query  += "           , scont2   \n";
        l_query  += "           , scont3   \n";
        l_query  += "           , scont4   \n";
        l_query  += "           , cdagen_1   \n";
        l_query  += "           , provv1_p   \n";
        l_query  += "           , provv1_v   \n";
        l_query  += "           , cdagen_2   \n";
        l_query  += "           , provv2_p   \n";
        l_query  += "           , provv2_v   \n";
        l_query  += "           , tkproc1   \n";
        l_query  += "           , cdentp1   \n";
        l_query  += "           , comp_proc1   \n";
        l_query  += "           , prov_proc1   \n";
        l_query  += "           , tkproc2   \n";
        l_query  += "           , cdentp2   \n";
        l_query  += "           , comp_proc2   \n";
        l_query  += "           , prov_proc2   \n";
        l_query  += "           , couimb   \n";
        l_query  += "           , peuimb   \n";
        l_query  += "           , peumer   \n";
        l_query  += "           , voumer   \n";
        l_query  += "           , impgru   \n";
        l_query  += "           , impposa   \n";
        l_query  += "           , impnolo   \n";
        l_query  += "           , nroper   \n";
        l_query  += "           , rifriga   \n";
        l_query  += "           , npag   \n";
        l_query  += "           , qtaprel   \n";
        l_query  += "           , qtacons   \n";
        l_query  += "           , fgsaldo   \n";
        l_query  += "           , dtcons   \n";
        l_query  += "           , cdlist   \n";
        l_query  += "           , cdclsc   \n";
        l_query  += "           , cdstato   \n";
        l_query  += "           , cdcoll   \n";
        l_query  += "           , tklisttag   \n";
        l_query  += "           , cdtpsco   \n";
        l_query  += "           , notatag   \n";
        l_query  += "           , notaven   \n";
        l_query  += "           , cdtpar   \n";
        l_query  += "           , nrparl   \n";
        l_query  += "           , qtalav   \n";
        l_query  += "           , cdadde   \n";
        l_query  += "           , cdcatm   \n";
        l_query  += "           , tkmaga   \n";
        l_query  += "           , cdkit   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cdprom   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "           , ncolli   \n";
        l_query  += "           , pzcolli   \n";
        l_query  += "           , dimena   \n";
        l_query  += "           , dimenb   \n";
        l_query  += "           , dimenc   \n";
        l_query  += "           , cdtins   \n";
        l_query  += "           , cdvar   \n";
        l_query  += "           , cdrifo   \n";
        l_query  += "           , fsped   \n";
        l_query  += "           , importonettoriga   \n";
        l_query  += "           , importoriga   \n";
        l_query  += "           , impuninetto   \n";
        l_query  += "           , scrap1   \n";
        l_query  += "           , scrap2   \n";
        l_query  += "           , qtcons_s   \n";
        l_query  += "           , scval   \n";
        l_query  += "           , fpzman   \n";
        l_query  += "           , scontirep   \n";
        l_query  += "           , pzxcollo   \n";
        l_query  += "           , cdarti_imb   \n";
        l_query  += "           , flivprovv   \n";
        l_query  += "           , provv1_p_var   \n";
        l_query  += "           , provv2_p_var   \n";
        l_query  += "           , provv1_v_var   \n";
        l_query  += "           , provv2_v_var   \n";
        l_query  += "           , cdartprod_nc   \n";
        l_query  += "           , fnettosc   \n";
        l_query  += "           , tkforn   \n";
        l_query  += "           , impuni_acq   \n";
        l_query  += "           , sconto1_acq   \n";
        l_query  += "           , sconto2_acq   \n";
        l_query  += "           , fordacq   \n";
        l_query  += "           , nlisprel   \n";
        l_query  += "           , tkposi_contr   \n";
        l_query  += "           , tkordigest   \n";
        l_query  += "           , tkposigest   \n";
        l_query  += "           , cdartirif   \n";
        l_query  += "           , fgpromo   \n";
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
        l_query  += "           , ?          \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (ripoor.equals("")) ripoor = null;
        if (cdarti.equals("")) cdarti = null;
        if (dssart.equals("")) dssart = null;
        if (cdunim.equals("")) cdunim = null;
        if (cdunim2.equals("")) cdunim2 = null;
        if (cdfisc.equals("")) cdfisc = null;
        if (cdiva.equals("")) cdiva = null;
        if (cdagen_1.equals("")) cdagen_1 = null;
        if (cdagen_2.equals("")) cdagen_2 = null;
        if (tkproc1.equals("")) tkproc1 = null;
        if (cdentp1.equals("")) cdentp1 = null;
        if (tkproc2.equals("")) tkproc2 = null;
        if (cdentp2.equals("")) cdentp2 = null;
        if (rifriga.equals("")) rifriga = null;
        if (npag.equals("")) npag = null;
        if (fgsaldo.equals("")) fgsaldo = null;
        if (cdlist.equals("")) cdlist = null;
        if (cdclsc.equals("")) cdclsc = null;
        if (cdstato.equals("")) cdstato = null;
        if (cdcoll.equals("")) cdcoll = null;
        if (cdtpsco.equals("")) cdtpsco = null;
        if (notatag.equals("")) notatag = null;
        if (notaven.equals("")) notaven = null;
        if (cdtpar.equals("")) cdtpar = null;
        if (nrparl.equals("")) nrparl = null;
        if (cdadde.equals("")) cdadde = null;
        if (cdcatm.equals("")) cdcatm = null;
        if (cdkit.equals("")) cdkit = null;
        if (cdazie.equals("")) cdazie = null;
        if (cdprom.equals("")) cdprom = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;
        if (cdtins.equals("")) cdtins = null;
        if (cdvar.equals("")) cdvar = null;
        if (cdrifo.equals("")) cdrifo = null;
        if (fsped.equals("")) fsped = null;
        if (fpzman.equals("")) fpzman = null;
        if (cdarti_imb.equals("")) cdarti_imb = null;
        if (flivprovv.equals("")) flivprovv = null;
        if (cdartprod_nc.equals("")) cdartprod_nc = null;
        if (fnettosc.equals("")) fnettosc = null;
        if (tkforn.equals("")) tkforn = null;
        if (fordacq.equals("")) fordacq = null;

        if (cdartirif.equals("")) cdartirif = null;//EAR-20091012
        if (fgpromo.equals(""))   fgpromo = null;//EAR-20111203


        ind = 1;
        if (tkposi == 0 && null_tkposi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkposi); ind += 1;
        } 
        if (tkordi == 0 && null_tkordi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkordi); ind += 1;
        } 
        if (tkposi_off == 0 && null_tkposi_off){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkposi_off); ind += 1;
        } 
        pstm.setString    (ind, ripoor); ind += 1;
        if (nrposi == 0 && null_nrposi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, nrposi); ind += 1;
        } 
        if (nrposi_v == 0 && null_nrposi_v){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, nrposi_v); ind += 1;
        } 
        pstm.setString    (ind, cdarti); ind += 1;
        pstm.setString    (ind, dssart); ind += 1;
        pstm.setString    (ind, cdunim); ind += 1;
        if (fatconv == 0 && null_fatconv){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, fatconv); ind += 1;
        } 
        pstm.setString    (ind, cdunim2); ind += 1;
        pstm.setString    (ind, cdfisc); ind += 1;
        pstm.setString    (ind, cdiva); ind += 1;
        if (qtatot == 0 && null_qtatot){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, qtatot); ind += 1;
        } 
        if (impuni == 0 && null_impuni){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, impuni); ind += 1;
        } 
        if (scont1 == 0 && null_scont1){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, scont1); ind += 1;
        } 
        if (scont2 == 0 && null_scont2){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, scont2); ind += 1;
        } 
        if (scont3 == 0 && null_scont3){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, scont3); ind += 1;
        } 
        if (scont4 == 0 && null_scont4){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, scont4); ind += 1;
        } 
        pstm.setString    (ind, cdagen_1); ind += 1;
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
        pstm.setString    (ind, cdagen_2); ind += 1;
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
        if (comp_proc1 == 0 && null_comp_proc1){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, comp_proc1); ind += 1;
        } 
        if (prov_proc1 == 0 && null_prov_proc1){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, prov_proc1); ind += 1;
        } 
        pstm.setString    (ind, tkproc2); ind += 1;
        pstm.setString    (ind, cdentp2); ind += 1;
        if (comp_proc2 == 0 && null_comp_proc2){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, comp_proc2); ind += 1;
        } 
        if (prov_proc2 == 0 && null_prov_proc2){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, prov_proc2); ind += 1;
        } 
        if (couimb == 0 && null_couimb){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, couimb); ind += 1;
        } 
        if (peuimb == 0 && null_peuimb){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, peuimb); ind += 1;
        } 
        if (peumer == 0 && null_peumer){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, peumer); ind += 1;
        } 
        if (voumer == 0 && null_voumer){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, voumer); ind += 1;
        } 
        if (impgru == 0 && null_impgru){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, impgru); ind += 1;
        } 
        if (impposa == 0 && null_impposa){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, impposa); ind += 1;
        } 
        if (impnolo == 0 && null_impnolo){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, impnolo); ind += 1;
        } 
        if (nroper == 0 && null_nroper){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, nroper); ind += 1;
        } 
        pstm.setString    (ind, rifriga); ind += 1;
        pstm.setString    (ind, npag); ind += 1;
        if (qtaprel == 0 && null_qtaprel){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, qtaprel); ind += 1;
        } 
        if (qtacons == 0 && null_qtacons){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, qtacons); ind += 1;
        } 
        pstm.setString    (ind, fgsaldo); ind += 1;
        pstm.setTimestamp (ind, dtcons); ind += 1;
        pstm.setString    (ind, cdlist); ind += 1;
        pstm.setString    (ind, cdclsc); ind += 1;
        pstm.setString    (ind, cdstato); ind += 1;
        pstm.setString    (ind, cdcoll); ind += 1;
        if (tklisttag == 0 && null_tklisttag){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tklisttag); ind += 1;
        } 
        pstm.setString    (ind, cdtpsco); ind += 1;
        pstm.setString    (ind, notatag); ind += 1;
        pstm.setString    (ind, notaven); ind += 1;
        pstm.setString    (ind, cdtpar); ind += 1;
        pstm.setString    (ind, nrparl); ind += 1;
        if (qtalav == 0 && null_qtalav){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, qtalav); ind += 1;
        } 
        pstm.setString    (ind, cdadde); ind += 1;
        pstm.setString    (ind, cdcatm); ind += 1;
        if (tkmaga == 0 && null_tkmaga){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkmaga); ind += 1;
        } 
        pstm.setString    (ind, cdkit); ind += 1;
        pstm.setString    (ind, cdazie); ind += 1;
        pstm.setString    (ind, cdprom); ind += 1;
        pstm.setString    (ind, cddipa); ind += 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtinse); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;
        if (ncolli == 0 && null_ncolli){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, ncolli); ind += 1;
        } 
        if (pzcolli == 0 && null_pzcolli){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, pzcolli); ind += 1;
        } 
        if (dimena == 0 && null_dimena){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, dimena); ind += 1;
        } 
        if (dimenb == 0 && null_dimenb){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, dimenb); ind += 1;
        } 
        if (dimenc == 0 && null_dimenc){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, dimenc); ind += 1;
        } 
        pstm.setString    (ind, cdtins); ind += 1;
        pstm.setString    (ind, cdvar); ind += 1;
        pstm.setString    (ind, cdrifo); ind += 1;
        pstm.setString    (ind, fsped); ind += 1;
        if (importonettoriga == 0 && null_importonettoriga){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, importonettoriga); ind += 1;
        } 
        if (importoriga == 0 && null_importoriga){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, importoriga); ind += 1;
        } 
        if (impuninetto == 0 && null_impuninetto){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, impuninetto); ind += 1;
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
        if (qtcons_s == 0 && null_qtcons_s){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, qtcons_s); ind += 1;
        } 
        if (scval == 0 && null_scval){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, scval); ind += 1;
        } 
        pstm.setString    (ind, fpzman); ind += 1;
        if (scontirep == 0 && null_scontirep){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, scontirep); ind += 1;
        } 
        if (pzxcollo == 0 && null_pzxcollo){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, pzxcollo); ind += 1;
        } 
        pstm.setString    (ind, cdarti_imb); ind += 1;
        pstm.setString    (ind, flivprovv); ind += 1;
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
        pstm.setString    (ind, cdartprod_nc); ind += 1;
        pstm.setString    (ind, fnettosc); ind += 1;
        pstm.setString    (ind, tkforn); ind += 1;
        if (impuni_acq == 0 && null_impuni_acq){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, impuni_acq); ind += 1;
        } 
        if (sconto1_acq == 0 && null_sconto1_acq){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, sconto1_acq); ind += 1;
        } 
        if (sconto2_acq == 0 && null_sconto2_acq){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, sconto2_acq); ind += 1;
        } 
        pstm.setString    (ind, fordacq); ind += 1;
        if (nlisprel == 0 && null_nlisprel){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, nlisprel); ind += 1;
        } 
        if (tkposi_contr == 0 && null_tkposi_contr){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkposi_contr); ind += 1;
        } 
        if (tkordigest == 0 && null_tkordigest){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkordigest); ind += 1;
        } 
        if (tkposigest == 0 && null_tkposigest){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkposigest); ind += 1;
        }

        pstm.setString    (ind, cdartirif); ind += 1;//EAR 20091012
        pstm.setString    (ind, fgpromo); ind += 1;//EAR 20111203

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

        long ll_tk = f_tabkey.getTabkey( "web_ord_positito" );

        return ll_tk;

    }




    /****
        executeUpdate: web_ord_positito
    **/ 


    public int executeUpdate( long       tkposi
                            , long       tkordi
                            , long       tkposi_off
                            , String     ripoor
                            , long       nrposi
                            , long       nrposi_v
                            , String     cdarti
                            , String     dssart
                            , String     cdunim
                            , double     fatconv
                            , String     cdunim2
                            , String     cdfisc
                            , String     cdiva
                            , double     qtatot
                            , double     impuni
                            , double     scont1
                            , double     scont2
                            , double     scont3
                            , double     scont4
                            , String     cdagen_1
                            , double     provv1_p
                            , double     provv1_v
                            , String     cdagen_2
                            , double     provv2_p
                            , double     provv2_v
                            , String     tkproc1
                            , String     cdentp1
                            , double     comp_proc1
                            , double     prov_proc1
                            , String     tkproc2
                            , String     cdentp2
                            , double     comp_proc2
                            , double     prov_proc2
                            , double     couimb
                            , double     peuimb
                            , double     peumer
                            , double     voumer
                            , double     impgru
                            , double     impposa
                            , double     impnolo
                            , long       nroper
                            , String     rifriga
                            , String     npag
                            , double     qtaprel
                            , double     qtacons
                            , String     fgsaldo
                            , Timestamp  dtcons
                            , String     cdlist
                            , String     cdclsc
                            , String     cdstato
                            , String     cdcoll
                            , long       tklisttag
                            , String     cdtpsco
                            , String     notatag
                            , String     notaven
                            , String     cdtpar
                            , String     nrparl
                            , double     qtalav
                            , String     cdadde
                            , String     cdcatm
                            , long       tkmaga
                            , String     cdkit
                            , String     cdprom
                            , long       ncolli
                            , long       pzcolli
                            , double     dimena
                            , double     dimenb
                            , double     dimenc
                            , String     cdtins
                            , String     cdvar
                            , String     cdrifo
                            , String     fsped
                            , double     importonettoriga
                            , double     importoriga
                            , double     impuninetto
                            , double     scrap1
                            , double     scrap2
                            , double     qtcons_s
                            , double     scval
                            , String     fpzman
                            , long       scontirep
                            , double     pzxcollo
                            , String     cdarti_imb
                            , String     flivprovv
                            , double     provv1_p_var
                            , double     provv2_p_var
                            , double     provv1_v_var
                            , double     provv2_v_var
                            , String     cdartprod_nc
                            , String     fnettosc
                            , String     tkforn
                            , double     impuni_acq
                            , double     sconto1_acq
                            , double     sconto2_acq
                            , String     fordacq
                            , long       nlisprel
                            , long       tkposi_contr
                            , long       tkordigest
                            , long       tkposigest
                            , String     cdartirif //EAR-20091012
                            , String     fgpromo   //EAR-20111203
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.web_ord_positito  \n";
        l_query  += "         set tkordi = ?  \n";
        l_query  += "           , tkposi_off = ?  \n";
        l_query  += "           , ripoor = ?  \n";
        l_query  += "           , nrposi = ?  \n";
        l_query  += "           , nrposi_v = ?  \n";
        l_query  += "           , cdarti = ?  \n";
        l_query  += "           , dssart = ?  \n";
        l_query  += "           , cdunim = ?  \n";
        l_query  += "           , fatconv = ?  \n";
        l_query  += "           , cdunim2 = ?  \n";
        l_query  += "           , cdfisc = ?  \n";
        l_query  += "           , cdiva = ?  \n";
        l_query  += "           , qtatot = ?  \n";
        l_query  += "           , impuni = ?  \n";
        l_query  += "           , scont1 = ?  \n";
        l_query  += "           , scont2 = ?  \n";
        l_query  += "           , scont3 = ?  \n";
        l_query  += "           , scont4 = ?  \n";
        l_query  += "           , cdagen_1 = ?  \n";
        l_query  += "           , provv1_p = ?  \n";
        l_query  += "           , provv1_v = ?  \n";
        l_query  += "           , cdagen_2 = ?  \n";
        l_query  += "           , provv2_p = ?  \n";
        l_query  += "           , provv2_v = ?  \n";
        l_query  += "           , tkproc1 = ?  \n";
        l_query  += "           , cdentp1 = ?  \n";
        l_query  += "           , comp_proc1 = ?  \n";
        l_query  += "           , prov_proc1 = ?  \n";
        l_query  += "           , tkproc2 = ?  \n";
        l_query  += "           , cdentp2 = ?  \n";
        l_query  += "           , comp_proc2 = ?  \n";
        l_query  += "           , prov_proc2 = ?  \n";
        l_query  += "           , couimb = ?  \n";
        l_query  += "           , peuimb = ?  \n";
        l_query  += "           , peumer = ?  \n";
        l_query  += "           , voumer = ?  \n";
        l_query  += "           , impgru = ?  \n";
        l_query  += "           , impposa = ?  \n";
        l_query  += "           , impnolo = ?  \n";
        l_query  += "           , nroper = ?  \n";
        l_query  += "           , rifriga = ?  \n";
        l_query  += "           , npag = ?  \n";
        l_query  += "           , qtaprel = ?  \n";
        l_query  += "           , qtacons = ?  \n";
        l_query  += "           , fgsaldo = ?  \n";
        l_query  += "           , dtcons = ?  \n";
        l_query  += "           , cdlist = ?  \n";
        l_query  += "           , cdclsc = ?  \n";
        l_query  += "           , cdstato = ?  \n";
        l_query  += "           , cdcoll = ?  \n";
        l_query  += "           , tklisttag = ?  \n";
        l_query  += "           , cdtpsco = ?  \n";
        l_query  += "           , notatag = ?  \n";
        l_query  += "           , notaven = ?  \n";
        l_query  += "           , cdtpar = ?  \n";
        l_query  += "           , nrparl = ?  \n";
        l_query  += "           , qtalav = ?  \n";
        l_query  += "           , cdadde = ?  \n";
        l_query  += "           , cdcatm = ?  \n";
        l_query  += "           , tkmaga = ?  \n";
        l_query  += "           , cdkit = ?  \n";
        l_query  += "           , cdprom = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "           , ncolli = ?  \n";
        l_query  += "           , pzcolli = ?  \n";
        l_query  += "           , dimena = ?  \n";
        l_query  += "           , dimenb = ?  \n";
        l_query  += "           , dimenc = ?  \n";
        l_query  += "           , cdtins = ?  \n";
        l_query  += "           , cdvar = ?  \n";
        l_query  += "           , cdrifo = ?  \n";
        l_query  += "           , fsped = ?  \n";
        l_query  += "           , importonettoriga = ?  \n";
        l_query  += "           , importoriga = ?  \n";
        l_query  += "           , impuninetto = ?  \n";
        l_query  += "           , scrap1 = ?  \n";
        l_query  += "           , scrap2 = ?  \n";
        l_query  += "           , qtcons_s = ?  \n";
        l_query  += "           , scval = ?  \n";
        l_query  += "           , fpzman = ?  \n";
        l_query  += "           , scontirep = ?  \n";
        l_query  += "           , pzxcollo = ?  \n";
        l_query  += "           , cdarti_imb = ?  \n";
        l_query  += "           , flivprovv = ?  \n";
        l_query  += "           , provv1_p_var = ?  \n";
        l_query  += "           , provv2_p_var = ?  \n";
        l_query  += "           , provv1_v_var = ?  \n";
        l_query  += "           , provv2_v_var = ?  \n";
        l_query  += "           , cdartprod_nc = ?  \n";
        l_query  += "           , fnettosc = ?  \n";
        l_query  += "           , tkforn = ?  \n";
        l_query  += "           , impuni_acq = ?  \n";
        l_query  += "           , sconto1_acq = ?  \n";
        l_query  += "           , sconto2_acq = ?  \n";
        l_query  += "           , fordacq = ?  \n";
        l_query  += "           , nlisprel = ?  \n";
        l_query  += "           , tkposi_contr = ?  \n";
        l_query  += "           , tkordigest = ?  \n";
        l_query  += "           , tkposigest = ?  \n";
        l_query  += "           , cdartirif = ?  \n";//EAR-20091012
        l_query  += "           , fgpromo   = ?  \n";//EAR-20111203
        l_query  += "  where tkposi = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (ripoor.equals("")) ripoor = null;
        if (cdarti.equals("")) cdarti = null;
        if (dssart.equals("")) dssart = null;
        if (cdunim.equals("")) cdunim = null;
        if (cdunim2.equals("")) cdunim2 = null;
        if (cdfisc.equals("")) cdfisc = null;
        if (cdiva.equals("")) cdiva = null;
        if (cdagen_1.equals("")) cdagen_1 = null;
        if (cdagen_2.equals("")) cdagen_2 = null;
        if (tkproc1.equals("")) tkproc1 = null;
        if (cdentp1.equals("")) cdentp1 = null;
        if (tkproc2.equals("")) tkproc2 = null;
        if (cdentp2.equals("")) cdentp2 = null;
        if (rifriga.equals("")) rifriga = null;
        if (npag.equals("")) npag = null;
        if (fgsaldo.equals("")) fgsaldo = null;
        if (cdlist.equals("")) cdlist = null;
        if (cdclsc.equals("")) cdclsc = null;
        if (cdstato.equals("")) cdstato = null;
        if (cdcoll.equals("")) cdcoll = null;
        if (cdtpsco.equals("")) cdtpsco = null;
        if (notatag.equals("")) notatag = null;
        if (notaven.equals("")) notaven = null;
        if (cdtpar.equals("")) cdtpar = null;
        if (nrparl.equals("")) nrparl = null;
        if (cdadde.equals("")) cdadde = null;
        if (cdcatm.equals("")) cdcatm = null;
        if (cdkit.equals("")) cdkit = null;
        if (cdazie.equals("")) cdazie = null;
        if (cdprom.equals("")) cdprom = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;
        if (cdtins.equals("")) cdtins = null;
        if (cdvar.equals("")) cdvar = null;
        if (cdrifo.equals("")) cdrifo = null;
        if (fsped.equals("")) fsped = null;
        if (fpzman.equals("")) fpzman = null;
        if (cdarti_imb.equals("")) cdarti_imb = null;
        if (flivprovv.equals("")) flivprovv = null;
        if (cdartprod_nc.equals("")) cdartprod_nc = null;
        if (fnettosc.equals("")) fnettosc = null;
        if (tkforn.equals("")) tkforn = null;
        if (fordacq.equals("")) fordacq = null;
        if (cdartirif.equals("")) cdartirif = null;//EAR-20091012
        if (fgpromo.equals("")) fgpromo = null;//EAR-20111203


        ind = 1;
        if (tkordi == 0 && null_tkordi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkordi); ind += 1;
        } 
        if (tkposi_off == 0 && null_tkposi_off){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkposi_off); ind += 1;
        } 
        pstm.setString    (ind, ripoor); ind += 1;
        if (nrposi == 0 && null_nrposi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, nrposi); ind += 1;
        } 
        if (nrposi_v == 0 && null_nrposi_v){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, nrposi_v); ind += 1;
        } 
        pstm.setString    (ind, cdarti); ind += 1;
        pstm.setString    (ind, dssart); ind += 1;
        pstm.setString    (ind, cdunim); ind += 1;
        if (fatconv == 0 && null_fatconv){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, fatconv); ind += 1;
        } 
        pstm.setString    (ind, cdunim2); ind += 1;
        pstm.setString    (ind, cdfisc); ind += 1;
        pstm.setString    (ind, cdiva); ind += 1;
        if (qtatot == 0 && null_qtatot){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, qtatot); ind += 1;
        } 
        if (impuni == 0 && null_impuni){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, impuni); ind += 1;
        } 
        if (scont1 == 0 && null_scont1){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, scont1); ind += 1;
        } 
        if (scont2 == 0 && null_scont2){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, scont2); ind += 1;
        } 
        if (scont3 == 0 && null_scont3){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, scont3); ind += 1;
        } 
        if (scont4 == 0 && null_scont4){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, scont4); ind += 1;
        } 
        pstm.setString    (ind, cdagen_1); ind += 1;
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
        pstm.setString    (ind, cdagen_2); ind += 1;
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
        if (comp_proc1 == 0 && null_comp_proc1){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, comp_proc1); ind += 1;
        } 
        if (prov_proc1 == 0 && null_prov_proc1){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, prov_proc1); ind += 1;
        } 
        pstm.setString    (ind, tkproc2); ind += 1;
        pstm.setString    (ind, cdentp2); ind += 1;
        if (comp_proc2 == 0 && null_comp_proc2){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, comp_proc2); ind += 1;
        } 
        if (prov_proc2 == 0 && null_prov_proc2){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, prov_proc2); ind += 1;
        } 
        if (couimb == 0 && null_couimb){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, couimb); ind += 1;
        } 
        if (peuimb == 0 && null_peuimb){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, peuimb); ind += 1;
        } 
        if (peumer == 0 && null_peumer){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, peumer); ind += 1;
        } 
        if (voumer == 0 && null_voumer){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, voumer); ind += 1;
        } 
        if (impgru == 0 && null_impgru){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, impgru); ind += 1;
        } 
        if (impposa == 0 && null_impposa){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, impposa); ind += 1;
        } 
        if (impnolo == 0 && null_impnolo){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, impnolo); ind += 1;
        } 
        if (nroper == 0 && null_nroper){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, nroper); ind += 1;
        } 
        pstm.setString    (ind, rifriga); ind += 1;
        pstm.setString    (ind, npag); ind += 1;
        if (qtaprel == 0 && null_qtaprel){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, qtaprel); ind += 1;
        } 
        if (qtacons == 0 && null_qtacons){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, qtacons); ind += 1;
        } 
        pstm.setString    (ind, fgsaldo); ind += 1;
        pstm.setTimestamp (ind, dtcons); ind += 1;
        pstm.setString    (ind, cdlist); ind += 1;
        pstm.setString    (ind, cdclsc); ind += 1;
        pstm.setString    (ind, cdstato); ind += 1;
        pstm.setString    (ind, cdcoll); ind += 1;
        if (tklisttag == 0 && null_tklisttag){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tklisttag); ind += 1;
        } 
        pstm.setString    (ind, cdtpsco); ind += 1;
        pstm.setString    (ind, notatag); ind += 1;
        pstm.setString    (ind, notaven); ind += 1;
        pstm.setString    (ind, cdtpar); ind += 1;
        pstm.setString    (ind, nrparl); ind += 1;
        if (qtalav == 0 && null_qtalav){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, qtalav); ind += 1;
        } 
        pstm.setString    (ind, cdadde); ind += 1;
        pstm.setString    (ind, cdcatm); ind += 1;
        if (tkmaga == 0 && null_tkmaga){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkmaga); ind += 1;
        } 
        pstm.setString    (ind, cdkit); ind += 1;
        pstm.setString    (ind, cdprom); ind += 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;
        if (ncolli == 0 && null_ncolli){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, ncolli); ind += 1;
        } 
        if (pzcolli == 0 && null_pzcolli){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, pzcolli); ind += 1;
        } 
        if (dimena == 0 && null_dimena){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, dimena); ind += 1;
        } 
        if (dimenb == 0 && null_dimenb){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, dimenb); ind += 1;
        } 
        if (dimenc == 0 && null_dimenc){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, dimenc); ind += 1;
        } 
        pstm.setString    (ind, cdtins); ind += 1;
        pstm.setString    (ind, cdvar); ind += 1;
        pstm.setString    (ind, cdrifo); ind += 1;
        pstm.setString    (ind, fsped); ind += 1;
        if (importonettoriga == 0 && null_importonettoriga){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, importonettoriga); ind += 1;
        } 
        if (importoriga == 0 && null_importoriga){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, importoriga); ind += 1;
        } 
        if (impuninetto == 0 && null_impuninetto){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, impuninetto); ind += 1;
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
        if (qtcons_s == 0 && null_qtcons_s){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, qtcons_s); ind += 1;
        } 
        if (scval == 0 && null_scval){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, scval); ind += 1;
        } 
        pstm.setString    (ind, fpzman); ind += 1;
        if (scontirep == 0 && null_scontirep){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, scontirep); ind += 1;
        } 
        if (pzxcollo == 0 && null_pzxcollo){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, pzxcollo); ind += 1;
        } 
        pstm.setString    (ind, cdarti_imb); ind += 1;
        pstm.setString    (ind, flivprovv); ind += 1;
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
        pstm.setString    (ind, cdartprod_nc); ind += 1;
        pstm.setString    (ind, fnettosc); ind += 1;
        pstm.setString    (ind, tkforn); ind += 1;
        if (impuni_acq == 0 && null_impuni_acq){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, impuni_acq); ind += 1;
        } 
        if (sconto1_acq == 0 && null_sconto1_acq){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, sconto1_acq); ind += 1;
        } 
        if (sconto2_acq == 0 && null_sconto2_acq){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, sconto2_acq); ind += 1;
        } 
        pstm.setString    (ind, fordacq); ind += 1;
        if (nlisprel == 0 && null_nlisprel){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, nlisprel); ind += 1;
        } 
        if (tkposi_contr == 0 && null_tkposi_contr){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkposi_contr); ind += 1;
        } 
        if (tkordigest == 0 && null_tkordigest){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkordigest); ind += 1;
        } 
        if (tkposigest == 0 && null_tkposigest){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkposigest); ind += 1;
        } 

        pstm.setString    (ind, cdartirif); ind += 1;//EAR 20091012
        pstm.setString    (ind, fgpromo); ind += 1;//EAR 20111203

        pstm.setLong      (ind, tkposi); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.tkposi = tkposi; 

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public long       tkposi = 0; 






    // --- Impostando a false queste prop. il def. � 0

    public boolean ib_calcola_token = true;
    public boolean null_tkposi = true;
    public boolean null_tkordi = true;
    public boolean null_tkposi_off = true;
    public boolean null_nrposi = true;
    public boolean null_nrposi_v = true;
    public boolean null_fatconv = true;
    public boolean null_qtatot = true;
    public boolean null_impuni = true;
    public boolean null_scont1 = true;
    public boolean null_scont2 = true;
    public boolean null_scont3 = true;
    public boolean null_scont4 = true;
    public boolean null_provv1_p = true;
    public boolean null_provv1_v = true;
    public boolean null_provv2_p = true;
    public boolean null_provv2_v = true;
    public boolean null_comp_proc1 = true;
    public boolean null_prov_proc1 = true;
    public boolean null_comp_proc2 = true;
    public boolean null_prov_proc2 = true;
    public boolean null_couimb = true;
    public boolean null_peuimb = true;
    public boolean null_peumer = true;
    public boolean null_voumer = true;
    public boolean null_impgru = true;
    public boolean null_impposa = true;
    public boolean null_impnolo = true;
    public boolean null_nroper = true;
    public boolean null_qtaprel = true;
    public boolean null_qtacons = true;
    public boolean null_tklisttag = true;
    public boolean null_qtalav = true;
    public boolean null_tkmaga = true;
    public boolean null_ncolli = true;
    public boolean null_pzcolli = true;
    public boolean null_dimena = true;
    public boolean null_dimenb = true;
    public boolean null_dimenc = true;
    public boolean null_importonettoriga = true;
    public boolean null_importoriga = true;
    public boolean null_impuninetto = true;
    public boolean null_scrap1 = true;
    public boolean null_scrap2 = true;
    public boolean null_qtcons_s = true;
    public boolean null_scval = true;
    public boolean null_scontirep = true;
    public boolean null_pzxcollo = true;
    public boolean null_provv1_p_var = true;
    public boolean null_provv2_p_var = true;
    public boolean null_provv1_v_var = true;
    public boolean null_provv2_v_var = true;
    public boolean null_impuni_acq = true;
    public boolean null_sconto1_acq = true;
    public boolean null_sconto2_acq = true;
    public boolean null_nlisprel = true;
    public boolean null_tkposi_contr = true;
    public boolean null_tkordigest = true;
    public boolean null_tkposigest = true;







}

