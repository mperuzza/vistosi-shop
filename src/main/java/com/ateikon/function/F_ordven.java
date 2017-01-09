package com.ateikon.function;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.PreparedStatement;

import com.ateikon.common.Atk_sql;
import com.ateikon.common.Costanti_comm;
import com.ateikon.common.Cat_costanti;







import com.ateikon.common.Web_ord_test;
import com.ateikon.common.Web_ord_positito;
import com.ateikon.common.Web_ord_posi_matr;
import com.ateikon.common.Assofiscal;
import com.ateikon.common.Ep_costanti;
import com.ateikon.common.Ep_lingua;
import com.ateikon.common.Ep_utente;
import com.ateikon.common.Mrp_arch_stato;
import com.ateikon.internet.eprogen.web.security.ShopUser;




import com.ateikon.user_object.Brule_tipoins_riga;


import com.ateikon.structure.Rec_web_ord_test;
import com.ateikon.structure.Rec_web_ord_positito;
import com.ateikon.structure.Rec_web_ord_iva;
import com.ateikon.structure.Rec_web_ord_posi_matr;
import com.ateikon.structure.Str_listino;
import com.ateikon.structure.Str_iva_assofisc;
import com.ateikon.structure.Str_imptrasp;
import com.ateikon.structure.Str_ordven_tot;
import com.ateikon.structure.Str_ordven_cond;
import com.ateikon.structure.Str_dest_merce;
import com.ateikon.structure.Str_dec_barcode;
import com.ateikon.structure.Str_html;
import com.ateikon.structure.Str_msgmod;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;
import org.springframework.security.context.SecurityContextHolder;



public class F_ordven extends Atk_sql {
    
    public static String fileProperties = "";
    
    String modello_mail = "";
    String varianti_mail = "";
    String vetro_mail = "";
    String montatura_mail = "";
    String elet_mail = "";
    String pesolordo_mail = "";
    String pesonetto_mail = "";
    String volume_mail = "";
    String codicearticolo_mail = "";
    String quantita_mail = "";
    String prezzolistino_mail = "";
    String sconto_mail = "";
    String prezzo_mail = "";
    String unitario_mail = "";
    String prezzototalenetto_mail = "";
    String datadisponibilita_mail = "";
    
    String ricezione_ordine_mail = "";
    String ricezione_offerta_mail = "";
    String riepilogo_ordine_mail = "";
    String rif_ordine_mail = "";
    String data_ordine_mail = "";
    String ordine_inviato_mail = "";
    String agente_ordine_mail = "";
    String cliente_ordine_mail = "";
    String destinazione_ordine_mail = "";
    String legenda_ordine_mail = "";
    String esaurimento_scorte_mail = "";
    String novita_ordine_mail = "";
    String offerte_ordine_mail = "";
    String totale_ordine_mail = "";
    String iva_esclusa_mail = "";
    String spese_trasporto_mail = "";
    String nota_ordine_mail = "";
    String nota_pagamento_mail = "";
    String prox_disp_mail = "";
    String oggetto_mail = "";

    public F_ordven() {

        super();
    }

    public long nuovo(String cdstato) throws Exception {
        return nuovo(-1, "", cdstato);
    }

    /**
     * *
     *
     *
     */
    public long nuovo(long tkutente, String tkclie) throws Exception {
        return nuovo(tkutente, tkclie, "PR");
    }

    /**
     * *
     *
     *
     */
    public long nuovo(long tkutente, String tkclie, String cdstato) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;



        F_tabkey f_tabkey = new F_tabkey();
        F_getkey f_getkey = new F_getkey();

        Costanti_comm costanti_comm = new Costanti_comm();
        Cat_costanti cat_costanti = new Cat_costanti();
        Web_ord_test web_ord_test = new Web_ord_test();


        setProfilo((Atk_sql) f_tabkey);
        setProfilo((Atk_sql) f_getkey);
        setProfilo((Atk_sql) costanti_comm);
        setProfilo((Atk_sql) cat_costanti);
        setProfilo((Atk_sql) web_ord_test);

        Rec_web_ord_test lstr = new Rec_web_ord_test();

        lstr.tkutente = tkutente;
        lstr.tkclie = tkclie;

        //EAR 20110707
        lstr.tksubutente = tkutente;


        String fnoord = "";


        String ls_cdtorve_def = cat_costanti.getCostvalue("cdtord_ve");


        if (ls_cdtorve_def.equals("")) {
            ls_cdtorve_def = costanti_comm.getCostvalue("cdtord_ve");
        }

        tot_rec = 0;

        l_query = "";
        l_query += " SELECT tkmaga                         \n";
        l_query += "      , ftipev                         \n";
        l_query += "      , cdcatm                         \n";
        l_query += "      , cdtdoc                         \n";
        l_query += "   FROM pgmr.ut_art_tipordve           \n";
        l_query += "  WHERE cdtorv = '" + ls_cdtorve_def + "'  \n";

        //System.out.println(l_query);
        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        if (rs != null && rs.next()) {

            lstr.cdtorve = ls_cdtorve_def;
            if (rs.getObject("cdcatm") != null) {
                lstr.cdcatm = rs.getString("cdcatm");
            }
            if (rs.getObject("ftipev") != null) {
                lstr.fcouni = rs.getString("ftipev");
            }
            if (rs.getObject("cdtdoc") != null) {
                lstr.cdtdoc_prot = rs.getString("cdtdoc");
            }
            if (rs.getObject("tkmaga") != null) {
                lstr.tkmaga = rs.getLong("tkmaga");
            }
        }
        pstm.close();

        tot_rec = 0;

        l_query = "";
        l_query += " select scdtdoc                               \n";
        l_query += "   from pgmr.tipo_doc_par                     \n";
        l_query += "  where cdtdoc = '" + lstr.cdtdoc_prot + "' \n";

        //System.out.println(l_query);
        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        if (rs != null && rs.next()) {
            if (rs.getObject("scdtdoc") != null) {
                lstr.cdtdoc = rs.getString("scdtdoc");
            }
            if (rs.getObject("scdtdoc") != null) {
                lstr.cdtdoc_segue = rs.getString("scdtdoc");
            }
        }
        pstm.close();


        tot_rec = 0;

        if (tkclie != null && !"".equals(tkclie)) {

            l_query = "";
            l_query += " select clie.cdagen         \n";
            l_query += "      , clie.cdpagame       \n";
            l_query += "      , clie.cdlist         \n";
            l_query += "      , clie.pgcodi         \n";
            l_query += "      , clie.cddpag         \n";
            l_query += "      , clie.ffinme         \n";
            l_query += "      , clie.cdiva          \n";
            l_query += "      , clie.cdfisc         \n";
            l_query += "      , clie.fnoord         \n";
            l_query += "      , clie.cdente         \n";
            l_query += "      , clie.scrap1         \n";
            l_query += "      , clie.scrap2         \n";
            l_query += "      , clie.sconto1        \n";
            l_query += "      , clie.sconto2        \n";
            l_query += "      , clie.sconto3        \n";
            l_query += "      , clie.sconto4        \n";
            l_query += "      , clie.scocas         \n";
            l_query += "      , clie.scval          \n";
            l_query += "      , clie.cdtrme         \n";
            l_query += "   from pgmr.archclie clie  \n";
            l_query += "  where clie.tkclie = ?     \n";
            l_query += "    and clie.cdazie = ?     \n";

            //System.out.println(l_query);
            pstm = m_connection.prepareStatement(l_query);

            ind = 1;
            pstm.setString(ind++, tkclie);
            pstm.setString(ind++, cdazie);

            rs = pstm.executeQuery();

            if (rs != null && rs.next()) {

                tot_rec += 1;
                ind = 0;
                if (rs.getObject("cdagen") != null) {
                    lstr.cdagen = rs.getString("cdagen");
                }
                if (rs.getObject("cdpagame") != null) {
                    lstr.cdpagame = rs.getString("cdpagame");
                }
                if (rs.getObject("cdlist") != null) {
                    lstr.cdlist = rs.getString("cdlist");
                }
                if (rs.getObject("pgcodi") != null) {
                    lstr.pgcodi = rs.getString("pgcodi");
                }
                if (rs.getObject("cddpag") != null) {
                    lstr.cddpag = rs.getString("cddpag");
                }
                if (rs.getObject("ffinme") != null) {
                    lstr.ffinme = rs.getString("ffinme");
                }
                if (rs.getObject("cdiva") != null) {
                    lstr.cdiva = rs.getString("cdiva");
                }
                if (rs.getObject("cdfisc") != null) {
                    lstr.cdfisc = rs.getString("cdfisc");
                }
                if (rs.getObject("fnoord") != null) {
                    fnoord = rs.getString("fnoord");
                }
                if (rs.getObject("cdente") != null) {
                    lstr.cdentc = rs.getString("cdente");
                }
                if (rs.getObject("scrap1") != null) {
                    lstr.scrap1 = rs.getDouble("scrap1");
                }
                if (rs.getObject("scrap2") != null) {
                    lstr.scrap2 = rs.getDouble("scrap2");
                }
                if (rs.getObject("sconto1") != null) {
                    lstr.scrig1 = rs.getDouble("sconto1");
                }
                if (rs.getObject("sconto2") != null) {
                    lstr.scrig2 = rs.getDouble("sconto2");
                }
                if (rs.getObject("sconto3") != null) {
                    lstr.scrig3 = rs.getDouble("sconto3");
                }
                if (rs.getObject("sconto4") != null) {
                    lstr.scrig4 = rs.getDouble("sconto4");
                }
                if (rs.getObject("scval") != null) {
                    lstr.scval = rs.getDouble("scval");
                }
                if (rs.getObject("cdtrme") != null) {
                    lstr.cdtrme = rs.getString("cdtrme");
                }

                // --- Lo sconto Cassa Viene applicato solo se acquisto con carta di credito
                // if (rs.getObject("scocas")!=null)  scocas = rs.getDouble("scocas");
                pstm.close();
            } else {
                pstm.close();
                throw new Exception("Impossibile inserire ordini senza cliente");
            }


            if (!s_cdagen.equals("")) {
                lstr.cdagen = s_cdagen;
            }


            if (fnoord.equals("S")) {
                throw new Exception("Ordini bloccati per questo cliente !!!");
            }


            // imposto il listino

            tot_rec = 0;

            l_query = "";
            l_query += "  select ivato             \n";
            l_query += "       , vacodi            \n";
            l_query += "    from pgmr.lis_listino  \n";
            l_query += "   where cdlist = ?        \n";
            l_query += "     and cdazie = ?        \n";
            //System.out.println(l_query);
            pstm = m_connection.prepareStatement(l_query);

            ind = 1;
            pstm.setString(ind++, lstr.cdlist);
            pstm.setString(ind++, cdazie);

            rs = pstm.executeQuery();

            if (rs != null && rs.next()) {

                tot_rec += 1;
                ind = 0;
                if (rs.getObject("ivato") != null) {
                    lstr.ivato = rs.getString("ivato");
                }
                if (rs.getObject("vacodi") != null) {
                    lstr.vacodi = rs.getString("vacodi");
                }

            }
            pstm.close();


            // --- trovo la sede legale

            String cdulsl = "";

            tot_rec = 0;

            l_query = "";
            l_query += "  select unil.cdunil                          \n";
            l_query += "    from pgmr.enteuniloc   ente_unil          \n";
            l_query += "       , pgmr.unitalocali  unil               \n";
            l_query += "   where unil.cdunil = ente_unil.cdunil       \n";
            l_query += "     and ente_unil.fseleg = 'S'               \n";
            l_query += "     and ente_unil.cdente = ?                 \n";
            //System.out.println(l_query);
            pstm = m_connection.prepareStatement(l_query);

            ind = 1;
            pstm.setString(ind++, lstr.cdentc);

            rs = pstm.executeQuery();

            if (rs != null && rs.next()) {

                tot_rec += 1;
                ind = 0;
                if (rs.getObject("cdunil") != null) {
                    cdulsl = rs.getString("cdunil");
                }
            }
            pstm.close();




            // --- Destinazione Merce

            lstr.cduldm = cdulsl;

            tot_rec = 0;
            if ("PN".equals(cdstato)) {
                //per subutenti va impostata la cdunil della subutente_uloc

                l_query = "";
                l_query = "SELECT cdunil "
                        + "  FROM pgmr.ep_utente, pgmr.ep_subutente_uloc "
                        + " WHERE ep_utente.tksubutente = ep_subutente_uloc.tksubutente "
                        + "   AND ep_utente.tkutente = ? ";

                //System.out.println(l_query);
                pstm = m_connection.prepareStatement(l_query);

                ind = 1;
                pstm.setLong(ind++, tkutente);

            } else {

                String cdusul_dm = costanti_comm.getCostvalue("cdusul_dm");



                l_query = "";
                l_query += "  select unil.cdunil                          \n";
                l_query += "    from pgmr.enteuniloc   ente_unil          \n";
                l_query += "       , pgmr.unitalocali  unil               \n";
                l_query += "   where unil.cdunil = ente_unil.cdunil       \n";
                l_query += "     and ente_unil.cdusul = ?                 \n";
                l_query += "     and ente_unil.fspref = 'S'               \n";
                l_query += "     and ente_unil.cdente = ?                 \n";
                l_query += "   order by ente_unil.fspref desc, unil.cdunil                       \n";
                //System.out.println(l_query);
                pstm = m_connection.prepareStatement(l_query);

                ind = 1;

                pstm.setString(ind++, cdusul_dm);
                pstm.setString(ind++, lstr.cdentc);

            }

            rs = pstm.executeQuery();

            if (rs != null && rs.next()) {

                tot_rec += 1;
                ind = 0;
                if (rs.getObject("cdunil") != null) {
                    lstr.cduldm = rs.getString("cdunil");
                }
            }
            pstm.close();


            String cdcapo = "";

            l_query = "";
            l_query += "   select cdcapo                      \n";
            l_query += "     from pgmr.archagen               \n";
            l_query += "    where cdagen = '" + lstr.cdagen + "'  \n";
            l_query += "      and cdazie = '" + cdazie + "'       \n";
            //System.out.println(l_query);
            cdcapo = sql_String(l_query);

        }



        web_ord_test.ib_calcola_token = false;

        lstr.tkordi = f_tabkey.getTabkey("web_ord_test");

        //System.out.println("lstr.tkordi: " + lstr.tkordi);

        lstr.dtordi = of_getOggi();

        lstr.anno = lstr.dtordi.getYear() + 1900;
        lstr.fannullato = "N";
        lstr.cambio = 1;
        lstr.cdstato = cdstato;
        lstr.imptrasp = 0;

        if (lstr.tkordi < 10) {
            lstr.cdordi = "0000" + lstr.tkordi;
        } else if (lstr.tkordi < 100) {
            lstr.cdordi = "000" + lstr.tkordi;
        } else if (lstr.tkordi < 1000) {
            lstr.cdordi = "00" + lstr.tkordi;
        } else if (lstr.tkordi < 10000) {
            lstr.cdordi = "0" + lstr.tkordi;
        } else {
            lstr.cdordi = "" + lstr.tkordi;
        }

        lstr.cdordi = cdstato + "/" + lstr.cdordi;

        tot_rec = web_ord_test.execute(lstr);

        if (tot_rec <= 0) {
            throw new Exception("Errore INS web_ord_test!");
        }


        return lstr.tkordi;

    }

    /**
     * *
     *
     *
     */
    public int confermaOrdine(long tkordi) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        long tkordi_dup = 0;



        String ls_cdstato = "";


        tot_rec = 0;

        l_query = "";
        l_query += " select cdstato             \n";
        l_query += "   from pgmr.web_ord_test   \n";
        l_query += "  where tkordi = " + tkordi + " \n";

        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        if (rs != null && rs.next()) {
            tot_rec += 1;
            if (rs.getObject("cdstato") != null) {
                ls_cdstato = rs.getString("cdstato");
            }
        }
        pstm.close();

        if (!ls_cdstato.equals("PR")) {
            if (ls_cdstato.equals("CO")) {
                return 0;
            } else {
                return -1;
            }
        }

        //pdefranceschi20120313 - I

        l_query = "";
        l_query += " select count(*)               \n";
        l_query += "   from pgmr.web_ord_positito  \n";
        l_query += "  where tkordi = " + tkordi + "    \n";
        l_query += "  and fgpromo = 'S'            \n";


        int promo = sql_int(l_query);


        l_query = "";
        l_query += " select count(*)               \n";
        l_query += "   from pgmr.web_ord_positito  \n";
        l_query += "  where tkordi = " + tkordi + "    \n";
        l_query += "  and (fgpromo != 'S' or fgpromo is null)    \n";


        int nopromo = sql_int(l_query);


        if (promo > 0) {

            if (nopromo > 0) {

                //duplico testata carrello
                tkordi_dup = duplicaOrdine(tkordi);

                // --- Cambio tkordi offerte
                l_query = "";
                l_query += " update pgmr.web_ord_positito     \n";
                l_query += "    set tkordi = " + tkordi_dup + "   \n";
                l_query += "  where tkordi = " + tkordi + "       \n";
                l_query += "  and fgpromo = 'S'               \n";

                pstm = m_connection.prepareStatement(l_query);

                tot_rec = pstm.executeUpdate();


                //update tkrifoff testata offerte

                l_query = "";
                l_query += " update pgmr.web_ord_test        \n";
                l_query += "    set tkrifoff = " + tkordi + "       \n";
                l_query += "  where tkordi = " + tkordi_dup + "  \n";

                pstm = m_connection.prepareStatement(l_query);

                tot_rec = pstm.executeUpdate();
                
                //MPERUZZA 20130129 copio le note dell'ordine nel nuovo ordine, se presenti
                String annotazione_cli = getAnnotazione(tkordi, TIPONOTA_CLIENTE);
                String annotazione_int = getAnnotazione(tkordi, TIPONOTA_INTERNA);
                
                if(org.apache.commons.lang.StringUtils.isNotBlank(annotazione_cli)) {
                    setAnnotazione(tkordi_dup, TIPONOTA_CLIENTE, annotazione_cli);
                }
                
                if(org.apache.commons.lang.StringUtils.isNotBlank(annotazione_int)) {
                    setAnnotazione(tkordi_dup, TIPONOTA_INTERNA, annotazione_int);
                }
                
                //MPERUZZA 20130211 copio il codice promozione, se presente
                
                l_query = "";
                l_query += " select cdpromo_m from pgmr.web_ord_test        ";
                l_query += "    where tkordi = " + tkordi + "                      ";

                pstm = m_connection.prepareStatement(l_query);

                rs = pstm.executeQuery();
                
                String cdpromo_m = "";

                while (rs != null && rs.next()) {
                    if (rs.getObject("cdpromo_m") != null)       cdpromo_m = rs.getString("cdpromo_m");
                }
                pstm.close();
                
                if(!"".equals(cdpromo_m)) {
                    l_query = "";
                    l_query += " update pgmr.web_ord_test                   \n";
                    l_query += "    set cdpromo_m = '" + cdpromo_m + "'     \n";
                    l_query += "  where tkordi = " + tkordi_dup + "         \n";

                    pstm = m_connection.prepareStatement(l_query);

                    tot_rec = pstm.executeUpdate();
                }
                //fine update codice promozione
                

            } else {

                //update tkrifoff testata offerte

                l_query = "";
                l_query += " update pgmr.web_ord_test        \n";
                l_query += "    set tkrifoff = " + tkordi + "       \n";
                l_query += "  where tkordi = " + tkordi + "  \n";

                pstm = m_connection.prepareStatement(l_query);

                tot_rec = pstm.executeUpdate();

            }

        }

        //pdefranceschi20120313 - F


        Cat_costanti cat_costanti = new Cat_costanti();

        F_getkey f_getkey = new F_getkey();

        setProfilo((Atk_sql) cat_costanti);
        setProfilo((Atk_sql) f_getkey);


        String ls_agente_web = cat_costanti.getCostvalue("agente_web");

        long numord = f_getkey.getKey(cddipa, "NUMORD");


        Timestamp dtordi = of_getOggi();

        int anno = dtordi.getYear() + 1900;

        String cdordi = "";


        if (numord < 10) {
            cdordi = "0000" + numord;
        } else if (numord < 100) {
            cdordi = "000" + numord;
        } else if (numord < 1000) {
            cdordi = "00" + numord;
        } else if (numord < 10000) {
            cdordi = "0" + numord;
        } else {
            cdordi = "" + numord;
        }

        cdordi = ls_agente_web + "/" + anno + "/" + cdordi;



        // --- Cambio lo stato dell'ordine

        l_query = "";
        l_query += " update pgmr.web_ord_test   \n";
        l_query += "    set cdstato = ?         \n";
        l_query += "      , numord  = ?         \n";
        l_query += "      , dtordi  = ?         \n";
        l_query += "      , cdordi  = ?         \n";
        l_query += "      , anno    = ?         \n";
        l_query += "      , profil  = ?         \n";
        l_query += "      , dtulag  = ?         \n";
        l_query += "  where tkordi  = ?         \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;
        pstm.setString(ind++, "CO");
        pstm.setLong(ind++, numord);
        pstm.setTimestamp(ind++, dtordi);
        pstm.setString(ind++, cdordi);
        pstm.setInt(ind++, anno);
        pstm.setString(ind++, profil);
        pstm.setTimestamp(ind++, oggi);
        pstm.setLong(ind++, tkordi);


        tot_rec = pstm.executeUpdate();
        pstm.close();

        //pdefranceschi20120313 - I
        if (tkordi_dup > 0) {
            numord = f_getkey.getKey(cddipa, "NUMORD");

            cdordi = "";


            if (numord < 10) {
                cdordi = "0000" + numord;
            } else if (numord < 100) {
                cdordi = "000" + numord;
            } else if (numord < 1000) {
                cdordi = "00" + numord;
            } else if (numord < 10000) {
                cdordi = "0" + numord;
            } else {
                cdordi = "" + numord;
            }

            cdordi = ls_agente_web + "/" + anno + "/" + cdordi;


            // --- Cambio lo stato dell'ordine

            l_query = "";
            l_query += " update pgmr.web_ord_test   \n";
            l_query += "    set cdstato = ?         \n";
            l_query += "      , numord  = ?         \n";
            l_query += "      , dtordi  = ?         \n";
            l_query += "      , cdordi  = ?         \n";
            l_query += "      , anno    = ?         \n";
            l_query += "      , profil  = ?         \n";
            l_query += "      , dtulag  = ?         \n";
            l_query += "  where tkordi  = ?         \n";

            pstm = m_connection.prepareStatement(l_query);

            ind = 1;
            pstm.setString(ind++, "CO");
            pstm.setLong(ind++, numord);
            pstm.setTimestamp(ind++, dtordi);
            pstm.setString(ind++, cdordi);
            pstm.setInt(ind++, anno);
            pstm.setString(ind++, profil);
            pstm.setTimestamp(ind++, oggi);
            pstm.setLong(ind++, tkordi_dup);


            tot_rec = pstm.executeUpdate();
            pstm.close();

        }

        //pdefranceschi20120313 - F


        return 1;

    }

    /**
     * *
     *
     *
     */
    public int confermaOrdineSubutente(long tkordi) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        long tkordi_dup = 0;



        String ls_cdstato = "";
        String ls_tkclie = "";

        tot_rec = 0;

        l_query = "";
        l_query += " select cdstato,            \n";
        l_query += "        tkclie              \n";
        l_query += "   from pgmr.web_ord_test   \n";
        l_query += "  where tkordi = " + tkordi + " \n";

        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        if (rs != null && rs.next()) {
            tot_rec += 1;
            if (rs.getObject("cdstato") != null) {
                ls_cdstato = rs.getString("cdstato");
            }
            if (rs.getObject("tkclie") != null) {
                ls_tkclie = rs.getString("tkclie");
            }
        }
        pstm.close();

        if (!ls_cdstato.equals("PN")) {
            if (ls_cdstato.equals("CO")) {
                return 0;
            } else {
                return -1;
            }
        }


        //pdefranceschi20120313 - I

        l_query = "";
        l_query += " select count(*)               \n";
        l_query += "   from pgmr.web_ord_positito  \n";
        l_query += "  where tkordi = " + tkordi + "    \n";
        l_query += "  and fgpromo = 'S'            \n";


        int promo = sql_int(l_query);

        l_query = "";
        l_query += " select count(*)               \n";
        l_query += "   from pgmr.web_ord_positito  \n";
        l_query += "  where tkordi = " + tkordi + "    \n";
        l_query += "  and (fgpromo != 'S' or fgpromo is null)   \n";


        int nopromo = sql_int(l_query);


        if (promo > 0) {

            if (nopromo > 0) {

                //duplico testata carrello
                tkordi_dup = duplicaOrdine(tkordi);

                // --- Cambio tkordi offerte
                l_query = "";
                l_query += " update pgmr.web_ord_positito     \n";
                l_query += "    set tkordi = " + tkordi_dup + "   \n";
                l_query += "  where tkordi = " + tkordi + "       \n";
                l_query += "  and fgpromo = 'S'               \n";

                pstm = m_connection.prepareStatement(l_query);

                tot_rec = pstm.executeUpdate();


                //update tkrifoff testata offerte
                l_query = "";
                l_query += " update pgmr.web_ord_test        \n";
                l_query += "    set tkrifoff = " + tkordi + "       \n";
                l_query += "  where tkordi = " + tkordi_dup + "  \n";

                pstm = m_connection.prepareStatement(l_query);

                tot_rec = pstm.executeUpdate();

            } else {

                //update tkrifoff testata offerte
                l_query = "";
                l_query += " update pgmr.web_ord_test        \n";
                l_query += "    set tkrifoff = " + tkordi + "       \n";
                l_query += "  where tkordi = " + tkordi + "  \n";

                pstm = m_connection.prepareStatement(l_query);

                tot_rec = pstm.executeUpdate();

            }

        }

        //pdefranceschi20120313 - F


        Cat_costanti cat_costanti = new Cat_costanti();

        F_getkey f_getkey = new F_getkey();

        setProfilo((Atk_sql) cat_costanti);
        setProfilo((Atk_sql) f_getkey);


        String ls_agente_web = cat_costanti.getCostvalue("agente_web");

        long numord = f_getkey.getKey(cddipa, "NUMORD");


        Timestamp dtordi = of_getOggi();

        int anno = dtordi.getYear() + 1900;



//        if (numord < 10){           cdordi = "0000"+numord;
//        }else if (numord < 100){    cdordi = "000"+numord;
//        }else if (numord < 1000){   cdordi = "00"+numord;
//        }else if (numord < 10000){  cdordi = "0"+numord;
//        }else {
//            cdordi = ""+numord;
//        }

        String cdordi = "";
        if (tkordi < 10) {
            cdordi = "0000" + tkordi;
        } else if (tkordi < 100) {
            cdordi = "000" + tkordi;
        } else if (tkordi < 1000) {
            cdordi = "00" + tkordi;
        } else if (tkordi < 10000) {
            cdordi = "0" + tkordi;
        } else {
            cdordi = "" + tkordi;
        }
        cdordi = "PR/" + cdordi;


        //recupero dati cliente dalla ep_utente
        Ep_utente ep_utente = new Ep_utente();
        setProfilo((Atk_sql) ep_utente);

        rs = ep_utente.getUserByTkclie(ls_tkclie);

        long ls_tkutente = 0;

        if (rs != null && rs.next()) {

            if (rs.getObject("tkutente") != null) {
                ls_tkutente = rs.getLong("tkutente");
            }

        }


        // --- Cambio lo stato dell'ordine
        l_query = "";
        l_query += " update pgmr.web_ord_test   \n";
        l_query += "    set cdstato = ?         \n";
        //l_query += "      , numord  = ?         \n";
        //l_query += "      , dtordi  = ?         \n";
        l_query += "      , cdordi  = ?         \n";
        //l_query += "      , anno    = ?         \n";
        l_query += "      , profil  = ?         \n";
        l_query += "      , dtulag  = ?         \n";
        l_query += "      , tkutente  = ?         \n";
        l_query += "  where tkordi  = ?         \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;
        pstm.setString(ind++, "PR");
        //pstm.setLong     (ind++, numord);
        //pstm.setTimestamp(ind++, dtordi);
        pstm.setString(ind++, cdordi);
        //pstm.setInt      (ind++, anno  );
        pstm.setString(ind++, profil);
        pstm.setTimestamp(ind++, oggi);
        pstm.setLong(ind++, ls_tkutente);
        pstm.setLong(ind++, tkordi);


        tot_rec = pstm.executeUpdate();
        pstm.close();

        //pdefranceschi20120313 - I
        if (tkordi_dup > 0) {

            numord = f_getkey.getKey(cddipa, "NUMORD");

            cdordi = "";

            if (tkordi < 10) {
                cdordi = "0000" + tkordi;
            } else if (tkordi < 100) {
                cdordi = "000" + tkordi;
            } else if (tkordi < 1000) {
                cdordi = "00" + tkordi;
            } else if (tkordi < 10000) {
                cdordi = "0" + tkordi;
            } else {
                cdordi = "" + tkordi;
            }
            cdordi = "PR/" + cdordi;



            // --- Cambio lo stato dell'ordine
            l_query = "";
            l_query += " update pgmr.web_ord_test   \n";
            l_query += "    set cdstato = ?         \n";
            //l_query += "      , numord  = ?         \n";
            //l_query += "      , dtordi  = ?         \n";
            l_query += "      , cdordi  = ?         \n";
            //l_query += "      , anno    = ?         \n";
            l_query += "      , profil  = ?         \n";
            l_query += "      , dtulag  = ?         \n";
            l_query += "      , tkutente  = ?         \n";
            l_query += "  where tkordi  = ?         \n";

            pstm = m_connection.prepareStatement(l_query);

            ind = 1;
            pstm.setString(ind++, "PR");
            //pstm.setLong     (ind++, numord);
            //pstm.setTimestamp(ind++, dtordi);
            pstm.setString(ind++, cdordi);
            //pstm.setInt      (ind++, anno  );
            pstm.setString(ind++, profil);
            pstm.setTimestamp(ind++, oggi);
            pstm.setLong(ind++, ls_tkutente);
            pstm.setLong(ind++, tkordi_dup);


            tot_rec = pstm.executeUpdate();
            pstm.close();

        }

        //pdefranceschi20120313 - F


        return 1;

    }

    /**
     * *
     *
     *
     */
    public int setDest_merce(long tkordi, Str_dest_merce astr) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;


        if (astr.cduldm.equals("")) {

            l_query = "";
            l_query += " select cduldm               \n";
            l_query += "   from pgmr.web_ord_test    \n";
            l_query += "  where tkordi = " + tkordi + "  \n";

            astr.cduldm = sql_String(l_query);

        }

        String cduldm = astr.cduldm;
        String ragcog_de = astr.ragcog_de;
        String indiri_de = astr.indiri_de;
        String cap_de = astr.cap_de;
        String comune_de = astr.comune_de;
        String cdprov_de = astr.cdprov_de;
        String cdstat_de = astr.cdstat_de;



        if (cduldm.equals("")) {
            cduldm = null;
        }
        if (ragcog_de.equals("")) {
            ragcog_de = null;
        }
        if (indiri_de.equals("")) {
            indiri_de = null;
        }
        if (cap_de.equals("")) {
            cap_de = null;
        }
        if (comune_de.equals("")) {
            comune_de = null;
        }
        if (cdprov_de.equals("")) {
            cdprov_de = null;
        }
        if (cdstat_de.equals("")) {
            cdstat_de = null;
        }


        tot_rec = 0;

        l_query = "";
        l_query += " update pgmr.web_ord_test \n";
        l_query += "    set cduldm    = ?     \n";
        l_query += "      , ragcog_de = ?     \n";
        l_query += "      , indiri_de = ?     \n";
        l_query += "      , cap_de    = ?     \n";
        l_query += "      , comune_de = ?     \n";
        l_query += "      , cdprov_de = ?     \n";
        l_query += "      , cdstat_de = ?     \n";
        l_query += "      , dtulag    = ?     \n";
        l_query += "      , profil    = ?     \n";
        l_query += "  where tkordi    = ?     \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;
        pstm.setString(ind++, cduldm);
        pstm.setString(ind++, ragcog_de);
        pstm.setString(ind++, indiri_de);
        pstm.setString(ind++, cap_de);
        pstm.setString(ind++, comune_de);
        pstm.setString(ind++, cdprov_de);
        pstm.setString(ind++, cdstat_de);
        pstm.setTimestamp(ind++, dtulag);
        pstm.setString(ind++, profil);
        pstm.setLong(ind++, tkordi);

        tot_rec = pstm.executeUpdate();
        pstm.close();
        pstm = null;

        if (tot_rec != 1) {
            throw new Exception("Errore UPD pgmr.web_ord_test");
        }

        return 1;

    }

    /**
     * *
     * Gestione note Ordine
     */
    public int setAnnotazione(long tkordi, String tiponota, String annotazione) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        l_query = "";
        l_query += " delete                            \n";
        l_query += "   from pgmr.web_ord_test_note     \n";
        l_query += "  where tkordi   = " + tkordi + "      \n";
        l_query += "    and tiponota = '" + tiponota + "'  \n";


        tot_rec = sql_update(l_query);


        int li_ = 0;
        int nrposi = 0;
        String ls_cont = "";
        int len_rec = 500;


        li_ = annotazione.length();

        while (li_ > 0) {

            ls_cont = "";
            nrposi += 10;

            if (li_ > len_rec) {

                ls_cont = annotazione.substring(0, len_rec);
                annotazione = annotazione.substring(len_rec);
            } else {

                ls_cont = annotazione;
                annotazione = "";
            }

            li_ = annotazione.length();


            l_query = "";
            l_query += " insert into pgmr.web_ord_test_note (\n";
            l_query += "        tkordi      \n";
            l_query += "      , tiponota    \n";
            l_query += "      , riga        \n";
            l_query += "      , testo       \n";
            l_query += "      , cdazie      \n";
            l_query += "      , cddipa      \n";
            l_query += "      , profil      \n";
            l_query += "      , dtinse      \n";
            l_query += "      , dtulag      \n";
            l_query += "      )values (     \n";
            l_query += "        ?,?,?,?,?   \n";
            l_query += "      , ?,?,?,?     \n";
            l_query += "      )             \n";

            pstm = m_connection.prepareStatement(l_query);

            ind = 1;
            pstm.setLong(ind, tkordi);
            ind += 1;
            pstm.setString(ind, tiponota);
            ind += 1;
            pstm.setLong(ind, nrposi);
            ind += 1;
            pstm.setString(ind, ls_cont);
            ind += 1;
            pstm.setString(ind, cdazie);
            ind += 1;
            pstm.setString(ind, cddipa);
            ind += 1;
            pstm.setString(ind, profil);
            ind += 1;
            pstm.setTimestamp(ind, oggi);
            ind += 1;
            pstm.setTimestamp(ind, oggi);
            ind += 1;

            tot_rec = pstm.executeUpdate();
            pstm.close();

            if (tot_rec != 1) {
                throw new Exception("Err. INS pgmr.atk_msgcont");
            }


        }


        return 1;

    }

    public String getAnnotazione(long tkordi, String tiponota) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;


        String ls_testo = "";

        l_query = "";
        l_query += " select riga                        \n";
        l_query += "      , testo                       \n";
        l_query += "   from pgmr.web_ord_test_note      \n";
        l_query += "  where tkordi   = " + tkordi + "       \n";
        l_query += "    and tiponota = '" + tiponota + "'   \n";
        l_query += "  order by riga                     \n";

        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        while (rs != null && rs.next()) {
            if (rs.getObject("testo") != null) {
                ls_testo += rs.getString("testo");
            }

        }
        pstm.close();

        return ls_testo;

    }

    /**
     * *
     * Gestione note Ordine
     */
    public int setAnnotazione_posi(long tkposi, String tiponota, String annotazione, long tkordi) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        l_query = "";
        l_query += " delete                            \n";
        l_query += "   from pgmr.web_ord_posi_note     \n";
        l_query += "  where tkposi   = " + tkposi + "      \n";
        l_query += "    and tiponota = '" + tiponota + "'  \n";


        tot_rec = sql_update(l_query);


        int li_ = 0;
        int nrposi = 0;
        String ls_cont = "";
        int len_rec = 500;


        li_ = annotazione.length();

        while (li_ > 0) {

            ls_cont = "";
            nrposi += 10;

            if (li_ > len_rec) {

                ls_cont = annotazione.substring(0, len_rec);
                annotazione = annotazione.substring(len_rec);
            } else {

                ls_cont = annotazione;
                annotazione = "";
            }

            li_ = annotazione.length();


            l_query = "";
            l_query += " insert into pgmr.web_ord_posi_note (\n";
            l_query += "        tkposi      \n";
            l_query += "      , tiponota    \n";
            l_query += "      , riga        \n";
            l_query += "      , testo       \n";
            l_query += "      , tkordi      \n";
            l_query += "      , cdazie      \n";
            l_query += "      , cddipa      \n";
            l_query += "      , profil      \n";
            l_query += "      , dtinse      \n";
            l_query += "      , dtulag      \n";
            l_query += "      )values (     \n";
            l_query += "        ?,?,?,?,?   \n";
            l_query += "      , ?,?,?,?,?   \n";
            l_query += "      )             \n";

            pstm = m_connection.prepareStatement(l_query);

            ind = 1;
            pstm.setLong(ind, tkposi);
            ind += 1;
            pstm.setString(ind, tiponota);
            ind += 1;
            pstm.setLong(ind, nrposi);
            ind += 1;
            pstm.setString(ind, ls_cont);
            ind += 1;
            pstm.setLong(ind, tkordi);
            ind += 1;
            pstm.setString(ind, cdazie);
            ind += 1;
            pstm.setString(ind, cddipa);
            ind += 1;
            pstm.setString(ind, profil);
            ind += 1;
            pstm.setTimestamp(ind, oggi);
            ind += 1;
            pstm.setTimestamp(ind, oggi);
            ind += 1;

            tot_rec = pstm.executeUpdate();
            pstm.close();

            if (tot_rec != 1) {
                throw new Exception("Err. INS pgmr.atk_msgcont");
            }


        }


        return 1;

    }

    public String getAnnotazione_posi(long tkposi, String tiponota) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;


        String ls_testo = "";

        l_query = "";
        l_query += " select riga                        \n";
        l_query += "      , testo                       \n";
        l_query += "   from pgmr.web_ord_posi_note      \n";
        l_query += "  where tkposi   = " + tkposi + "       \n";
        l_query += "    and tiponota = '" + tiponota + "'   \n";
        l_query += "  order by riga                     \n";

        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        while (rs != null && rs.next()) {
            if (rs.getObject("testo") != null) {
                ls_testo += rs.getString("testo");
            }

        }
        pstm.close();

        return ls_testo;

    }

    /*
     * Ritorno immagine per ricambio nella mail
     * MPERUZZA 20121108
     */
    public String getDatiRicambio(String cdarti, String cdarti_ric) throws SQLException {

        PreparedStatement pstm = null;
        ResultSet rs = null;

        String img_ricambio = "";

        l_query = "";
        l_query += " select imgv_filename                      \n";
        l_query += "   from pgmr.vist_articoli_ricambi      \n";
        l_query += "  where cdarti   = " + cdarti + "       \n";
        l_query += "    and cdarti_ric = '" + cdarti_ric + "'   \n";

        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        while (rs != null && rs.next()) {
            if (rs.getObject("imgv_filename") != null) {
                img_ricambio += rs.getString("imgv_filename");
            }

        }
        pstm.close();

        return img_ricambio;
    }

    /**
     * *
     * Gestione Pagamento con carta di credito NON Gestito
     *
     */
    public int setCdautoriz(long tkordi, String cdautoriz, long tkotp) throws Exception {


        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;



        l_query = "";
        l_query += " update pgmr.web_ord_test   \n";
        l_query += "    set cdautoriz = ?       \n";
        l_query += "      , tkotp     = ?       \n";
        l_query += "      , profil    = ?       \n";
        l_query += "      , dtulag    = ?       \n";
        l_query += "  where tkordi    = ?       \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;
        pstm.setString(ind, cdautoriz);
        ind += 1;
        pstm.setLong(ind, tkotp);
        ind += 1;
        pstm.setString(ind, profil);
        ind += 1;
        pstm.setTimestamp(ind, dtulag);
        ind += 1;
        pstm.setLong(ind, tkordi);
        ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();

        return tot_rec;
    }

    public long aggiungi_articolo(long tkordi, String cdarti, String cdtins, long ncolli, double dimena, double dimenb, double dimenc, double qtatot, double scont1, double scont2, double scont3, double adb_impuninetto, String fg_annulla_sconti, String fg_tpcalcimp, long tkmatricola) throws Exception {

        return aggiungi_articolo(tkordi, cdarti, cdtins, ncolli, dimena, dimenb, dimenc, qtatot, scont1, scont2, scont3, adb_impuninetto, fg_annulla_sconti, fg_tpcalcimp, tkmatricola, null, null);
    }

    /**
     * *
     *
     *
     */
    public long aggiungi_articolo(long tkordi, String cdarti, String cdtins, long ncolli, double dimena, double dimenb, double dimenc, double qtatot, double scont1, double scont2, double scont3, double adb_impuninetto, String fg_annulla_sconti, String fg_tpcalcimp, long tkmatricola, String cdartirif, String fgpromo) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;


        l_query = "";
        l_query += " select cdstato              \n";
        l_query += "   from pgmr.web_ord_test    \n";
        l_query += "  where tkordi = " + tkordi + "  \n";

        String ls_cdstato = sql_String(l_query);
        //System.out.println(l_query);
        if (!ls_cdstato.equals("PR") && !"PN".equals(ls_cdstato) && !"WL".equals(ls_cdstato)) {

            throw new Exception("Stato ordine <> da Provvisorio (stato = " + ls_cdstato + ")");
        }

        //System.out.println("ordine trovato");

        if (qtatot == 0) {
            qtatot = 1;
        }



        String ls_cdlist = "";
        String ls_tkclie = "";
        String cdiva_t = "";
        String cdfisc_t = "";
        long tkmaga = 0;

        //EAR 20130731 prende sempre il listino del cliente
        l_query = "";
        l_query += " select archclie.cdlist             \n";
        l_query += "      , web_ord_test.tkclie                      \n";
        l_query += "      , web_ord_test.cdiva                       \n";
        l_query += "      , web_ord_test.cdfisc                      \n";
        l_query += "      , web_ord_test.tkmaga                      \n";
        l_query += "   from pgmr.web_ord_test,          \n";
        l_query += "        pgmr.archclie               \n";
        l_query += "  where web_ord_test.tkclie = archclie.tkclie         \n";
        l_query += "   and  tkordi = ?         \n";

        //System.out.println(l_query);
        pstm = m_connection.prepareStatement(l_query);

        ind = 1;
        pstm.setLong(ind++, tkordi);

        rs = pstm.executeQuery();

        if (rs != null && rs.next()) {
            if (rs.getObject("cdlist") != null) {
                ls_cdlist = rs.getString("cdlist");
            }
            if (rs.getObject("tkclie") != null) {
                ls_tkclie = rs.getString("tkclie");
            }
            if (rs.getObject("cdiva") != null) {
                cdiva_t = rs.getString("cdiva");
            }
            if (rs.getObject("cdfisc") != null) {
                cdfisc_t = rs.getString("cdfisc");
            }
            if (rs.getObject("tkmaga") != null) {
                tkmaga = rs.getLong("tkmaga");
            }

        }
        pstm.close();


        long tkposi = 0;
        long ncolli_posi = 0;
        double qtatot_posi = 0;

        tot_rec = 0;

        if (tkmatricola <= 0) {

            l_query = "";
            l_query += " select posi.tkposi                                            \n";
            l_query += "      , posi.qtatot                                            \n";
            l_query += "      , posi.ncolli                                            \n";
            l_query += "   from pgmr.web_ord_positito posi                             \n";
            l_query += "  where posi.tkordi = ?                                        \n";
            l_query += "    and posi.cdarti = ?                                        \n";
            l_query += "    and posi.dimena = ?                                        \n";
            l_query += "    and posi.dimenb = ?                                        \n";
            l_query += "    and posi.dimenc = ?                                        \n";
            l_query += "    and posi.tkposi not in( select matr.tkposi                 \n";
            l_query += "                              from pgmr.web_ord_posi_matr matr \n";
            l_query += "                             where matr.tkordi = posi.tkordi   \n";
            l_query += "                                    )                          \n";
        } else {

            l_query = "";
            l_query += " select posi.tkposi                     \n";
            l_query += "      , posi.qtatot                     \n";
            l_query += "      , posi.ncolli                     \n";
            l_query += "   from pgmr.web_ord_positito  posi     \n";
            l_query += "      , pgmr.web_ord_posi_matr matr     \n";
            l_query += "  where posi.tkordi = matr.tkordi       \n";
            l_query += "    and posi.tkposi = matr.tkposi       \n";
            l_query += "    and posi.tkordi = ?                 \n";
            l_query += "    and posi.cdarti = ?                 \n";
            l_query += "    and matr.tkmatricola = ?            \n";

        }
        //System.out.println(l_query);
        pstm = m_connection.prepareStatement(l_query);

        ind = 1;
        pstm.setLong(ind++, tkordi);
        pstm.setString(ind++, cdarti);
        if (tkmatricola <= 0) {

            pstm.setDouble(ind++, dimena);
            pstm.setDouble(ind++, dimenb);
            pstm.setDouble(ind++, dimenc);
        } else {
            pstm.setLong(ind++, tkmatricola);
        }

        rs = pstm.executeQuery();

        if (rs != null && rs.next()) {

            tot_rec += 1;
            ind = 0;
            if (rs.getObject("tkposi") != null) {
                tkposi = rs.getLong("tkposi");
            }
            if (rs.getObject("ncolli") != null) {
                ncolli_posi = rs.getLong("ncolli");
            }
            if (rs.getObject("qtatot") != null) {
                qtatot_posi = rs.getDouble("qtatot");
            }
        }
        pstm.close();


        if (tkposi <= 0) {
            //System.out.println("call nuovo articolo");
            tkposi = nuovo_articolo(tkordi, cdarti, dimena, dimenb, dimenc, cdartirif, fgpromo);
        }

        if (tkposi < 0) {
            throw new Exception("Errore INS pgmr.web_ord_positito");
        }

        // --- Calcolo l'iva
        //System.out.println("calc iva");
        F_iva_assofisc f_iva_assofisc = new F_iva_assofisc();

        setProfilo((Atk_sql) f_iva_assofisc);

        Str_iva_assofisc lstr_iva = new Str_iva_assofisc();

        lstr_iva.cdarti = cdarti;
        lstr_iva.cdiva_t = cdiva_t;
        lstr_iva.cdfisc_t = cdfisc_t;

        tot_rec = f_iva_assofisc.exec(lstr_iva);

        if (tot_rec != 0) {

            throw new Exception("Errore Calcolo IVA.");
        }



        // --- Calcolo il prezzo
        //System.out.println("calc prz");
        Str_listino lstr_lis = null;

        F_listino f_listino = new F_listino();

        setProfilo((Atk_sql) f_listino);


        lstr_lis = f_listino.calcolaPrezzo(ls_cdlist, ls_tkclie, cdarti, qtatot, scont1, scont2, scont3, adb_impuninetto, fg_annulla_sconti, fg_tpcalcimp);


        double importoriga = qtatot * lstr_lis.impuni;
        double importonettoriga = qtatot * lstr_lis.impuninetto;

        if (cdtins.equals("")) {
            cdtins = null;
        }


        l_query = "";
        l_query += "  update pgmr.web_ord_positito   \n";
        l_query += "     set qtatot           = ?    \n";
        l_query += "       , ncolli           = ?    \n";
        l_query += "       , impuni           = ?    \n";
        l_query += "       , impuninetto      = ?    \n";
        l_query += "       , scont1           = ?    \n";
        l_query += "       , scont2           = ?    \n";
        l_query += "       , scont3           = ?    \n";
        l_query += "       , scont4           = ?    \n";
        l_query += "       , scrap1           = ?    \n";
        l_query += "       , scrap2           = ?    \n";
        l_query += "       , importoriga      = ?    \n";
        l_query += "       , importonettoriga = ?    \n";
        l_query += "       , fg_annulla_sconti = ?   \n";
        l_query += "       , fg_tpcalcimp     = ?    \n";
        l_query += "       , cdiva  = ?              \n";
        l_query += "       , cdfisc = ?              \n";
        l_query += "       , cdtins = ?              \n";
        l_query += "       , profil = ?              \n";
        l_query += "       , dtulag = ?              \n";
        l_query += "   where tkposi = ?              \n";
        //System.out.println(l_query);
        pstm = m_connection.prepareStatement(l_query);
        //System.out.println("qtatot: " + qtatot);
        ind = 1;
        pstm.setDouble(ind++, qtatot);
        pstm.setLong(ind++, ncolli);
        pstm.setDouble(ind++, lstr_lis.impuni);
        pstm.setDouble(ind++, lstr_lis.impuninetto);
        pstm.setDouble(ind++, lstr_lis.sconto1);
        pstm.setDouble(ind++, lstr_lis.sconto2);
        pstm.setDouble(ind++, lstr_lis.sconto3);
        pstm.setDouble(ind++, lstr_lis.sconto4);
        pstm.setDouble(ind++, lstr_lis.scrap1);
        pstm.setDouble(ind++, lstr_lis.scrap2);
        pstm.setDouble(ind++, importoriga);
        pstm.setDouble(ind++, importonettoriga);
        pstm.setString(ind++, fg_annulla_sconti);
        pstm.setString(ind++, fg_tpcalcimp);
        pstm.setString(ind++, lstr_iva.cdiva);
        pstm.setString(ind++, lstr_iva.cdfisc);
        pstm.setString(ind++, cdtins);
        pstm.setString(ind++, profil);
        pstm.setTimestamp(ind++, dtulag);

        pstm.setLong(ind++, tkposi);

        tot_rec = pstm.executeUpdate();
        pstm.close();

        if (tot_rec != 1) {
            throw new Exception("Errore UPD pgmr.web_ord_positito tkposi = " + tkposi + " ");
        }


        if (tkmatricola > 0) {

            Web_ord_posi_matr web_ord_posi_matr = new Web_ord_posi_matr();

            setProfilo((Atk_sql) web_ord_posi_matr);

            Rec_web_ord_posi_matr lstr = new Rec_web_ord_posi_matr();

            lstr.tkordi = tkordi;
            lstr.tkposi = tkposi;
            lstr.tkmatricola = tkmatricola;
            lstr.qtatot = qtatot;

            tot_rec = web_ord_posi_matr.execute(lstr);

            if (tot_rec != 1) {
                throw new Exception("Errore UPD pgmr.web_ord_posi_matr tkmatricola = " + tkmatricola + " ");
            }


        }




        // --- Poich� ho modificato un ordine

        tot_rec = ordina_righe(tkordi);

        tot_rec = calcolo_spese_trasporto(tkordi);

        if (tot_rec != 1) {
            throw new Exception("Errore UPD calcolo Spese Trasporto rc: " + tot_rec + " ");
        }

        tot_rec = calcola_iva(tkordi);

        if (tot_rec != 1) {
            throw new Exception("Errore Calcolo IVA --- rc: " + tot_rec + " ");
        }

        /*
         tot_rec = calcola_promozione(tkordi);

         if (tot_rec != 1){
         throw new Exception("Errore Calcolo Promozione --- rc: "+tot_rec+" ");
         }
         */


        return tkposi;

    }

    /**
     * *
     *
     *
     */
    public int aggiorna_riga(long tkordi, long tkposi, long ncolli, double dimena, double dimenb, double dimenc) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;


        l_query = "";
        l_query += " select cdstato              \n";
        l_query += "   from pgmr.web_ord_test    \n";
        l_query += "  where tkordi = " + tkordi + "  \n";

        String ls_cdstato = sql_String(l_query);

        if (!ls_cdstato.equals("PR") && !"PN".equals(ls_cdstato)) {

            throw new Exception("Stato ordine <> da Provvisorio (stato = " + ls_cdstato + ")");
        }


        l_query = "";
        l_query += " select count(*)                  \n";
        l_query += "   from pgmr.web_ord_posi_matr    \n";
        l_query += "  where tkordi = " + tkordi + "       \n";
        l_query += "    and tkposi = " + tkposi + "       \n";

        int li_matr = sql_int(l_query);

        if (li_matr > 0) {

            throw new Exception("I pacchi possono essere Ordinati Integri");
        }



        String cdarti = "";
        String cdtins = "";
        double impuni = 0;
        double impuninetto = 0;
        double qtatot = 0;

        l_query = "";
        l_query += " select cdarti                   \n";
        l_query += "      , cdtins                   \n";
        l_query += "      , impuninetto              \n";
        l_query += "      , impuni                   \n";
        l_query += "   from pgmr.web_ord_positito    \n";
        l_query += "  where tkposi = " + tkposi + "      \n";

        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        if (rs != null && rs.next()) {
            if (rs.getObject("cdarti") != null) {
                cdarti = rs.getString("cdarti");
            }
            if (rs.getObject("cdtins") != null) {
                cdtins = rs.getString("cdtins");
            }
            if (rs.getObject("impuninetto") != null) {
                impuninetto = rs.getDouble("impuninetto");
            }
            if (rs.getObject("impuni") != null) {
                impuni = rs.getDouble("impuni");
            }
        }


        // --- ricalcolo la quantit�


        F_decode_barcode f_decode_barcode = new F_decode_barcode();
        Brule_tipoins_riga brule_tipoins_riga = new Brule_tipoins_riga();

        setProfilo((Atk_sql) f_decode_barcode);
        setProfilo((Atk_sql) brule_tipoins_riga);


        Str_dec_barcode lstr = new Str_dec_barcode();


        lstr.cdarti = cdarti;
        tot_rec = f_decode_barcode.of_articolo(lstr);

        tot_rec = f_decode_barcode.of_lotto(lstr);

        f_decode_barcode.of_flag_calcolo(lstr);

        lstr.dimena = dimena;
        lstr.dimenb = dimenb;
        lstr.dimenc = dimenc;
        lstr.ncolli = ncolli;
        lstr.quantita = 0;

        tot_rec = brule_tipoins_riga.of_calcola_quantita(lstr);


        if (tot_rec == 1) {
            qtatot = lstr.quantita;
        } else {
            throw new Exception("Errore nel Calcolo della QTA");
        }


        if (qtatot == 0) {
            qtatot = 1;
        }


        double importoriga = qtatot * impuni;
        double importonettoriga = qtatot * impuninetto;



        l_query = "";
        l_query += "  update pgmr.web_ord_positito   \n";
        l_query += "     set qtatot           = ?    \n";
        l_query += "       , ncolli           = ?    \n";
        l_query += "       , importoriga      = ?    \n";
        l_query += "       , importonettoriga = ?    \n";
        l_query += "       , dimena = ?              \n";
        l_query += "       , dimenb = ?              \n";
        l_query += "       , dimenc = ?              \n";
        l_query += "       , profil = ?              \n";
        l_query += "       , dtulag = ?              \n";
        l_query += "   where tkposi = ?              \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;
        pstm.setDouble(ind++, qtatot);
        pstm.setLong(ind++, ncolli);
        pstm.setDouble(ind++, importoriga);
        pstm.setDouble(ind++, importonettoriga);
        pstm.setDouble(ind++, dimena);
        pstm.setDouble(ind++, dimenb);
        pstm.setDouble(ind++, dimenc);
        pstm.setString(ind++, profil);
        pstm.setTimestamp(ind++, dtulag);

        pstm.setLong(ind++, tkposi);

        tot_rec = pstm.executeUpdate();
        pstm.close();

        if (tot_rec != 1) {
            throw new Exception("Errore UPD pgmr.web_ord_positito tkposi = " + tkposi + " ");
        }


        // --- Poich� ho modificato un ordine

        // tot_rec = ordina_righe(tkordi);

        tot_rec = calcolo_spese_trasporto(tkordi);

        if (tot_rec != 1) {
            throw new Exception("Errore UPD calcolo Spese Trasporto rc: " + tot_rec + " ");
        }

        tot_rec = calcola_iva(tkordi);

        if (tot_rec != 1) {
            throw new Exception("Errore Calcolo IVA --- rc: " + tot_rec + " ");
        }

        /*
         tot_rec = calcola_promozione(tkordi);

         if (tot_rec != 1){
         throw new Exception("Errore Calcolo Promozione --- rc: "+tot_rec+" ");
         }
         */


        return 1;

    }

    /**
     * *
     *
     *
     */
    public int cancella_carrello(long tkordi) throws Exception {


        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;


        tot_rec = cancella_righe(tkordi);


        l_query = "";
        l_query += " delete                      \n";
        l_query += "   from pgmr.web_ord_iva     \n";
        l_query += "  where tkordi = " + tkordi + "  \n";

        pstm = m_connection.prepareStatement(l_query);

        tot_rec = pstm.executeUpdate();
        pstm.close();


        l_query = "";
        l_query += " delete                      \n";
        l_query += "   from pgmr.web_ord_test    \n";
        l_query += "  where tkordi = " + tkordi + "  \n";

        pstm = m_connection.prepareStatement(l_query);

        tot_rec = pstm.executeUpdate();
        pstm.close();

        if (tot_rec != 1) {
            throw new Exception("Errore DEL pgmr.web_ord_test (tkordi = " + tkordi + ") ");
        }

        return 1;
    }

    /**
     * *
     *
     *
     */
    public int cancella_riga(long tkordi, long tkposi) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        l_query = "";
        l_query += " select cdstato              \n";
        l_query += "   from pgmr.web_ord_test    \n";
        l_query += "  where tkordi = " + tkordi + "  \n";

        String ls_cdstato = sql_String(l_query);

        if (!ls_cdstato.equals("PR") && !"PN".equals(ls_cdstato) && !"WL".equals(ls_cdstato)) {

            throw new Exception("Stato ordine <> da Provvisorio (stato = " + ls_cdstato + ")");
        }

        l_query = "";
        l_query += " update pgmr.web_import_ordven \n";
        l_query += "    set tkposi = null          \n";
        l_query += "      , profil = '" + profil + "'  \n";
        l_query += "      , dtulag = " + sysdate + "   \n";
        l_query += "  where tkposi = " + tkposi + "    \n";

        tot_rec = sql_update(l_query);

        l_query = "";
        l_query += " delete                            \n";
        l_query += "   from pgmr.web_ord_posi_matr     \n";
        l_query += "  where tkordi = " + tkordi + "        \n";
        l_query += "    and tkposi = " + tkposi + "        \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;
        tot_rec = pstm.executeUpdate();
        pstm.close();


        l_query = "";
        l_query += " delete                         \n";
        l_query += "   from pgmr.web_ord_positito   \n";
        l_query += "  where tkordi = " + tkordi + "     \n";
        l_query += "    and tkposi = " + tkposi + "     \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;
        tot_rec = pstm.executeUpdate();
        pstm.close();

        if (tot_rec != 1) {
            throw new Exception("Errore DEL pgmr.web_ord_positito (tkposi = " + tkposi + ") ");
        }



        // --- Poich� ho modificato un ordine

        tot_rec = ordina_righe(tkordi);

        tot_rec = calcolo_spese_trasporto(tkordi);

        if (tot_rec != 1) {
            throw new Exception("Errore UPD calcolo Spese Trasporto rc: " + tot_rec + " ");
        }

        tot_rec = calcola_iva(tkordi);

        if (tot_rec != 1) {
            throw new Exception("Errore Calcolo IVA --- rc: " + tot_rec + " ");
        }

        /*
         tot_rec = calcola_promozione(tkordi);

         if (tot_rec != 1){
         throw new Exception("Errore Calcolo Promozione --- rc: "+tot_rec+" ");
         }
         */


        return 1;

    }

    /**
     * *
     *
     *
     */
    public int cancella_righe(long tkordi) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;


        l_query = "";
        l_query += " select cdstato              \n";
        l_query += "   from pgmr.web_ord_test    \n";
        l_query += "  where tkordi = " + tkordi + "  \n";

        String ls_cdstato = sql_String(l_query);

        if (!ls_cdstato.equals("PR") && !"PN".equals(ls_cdstato)) {

            throw new Exception("Stato ordine <> da Provvisorio (stato = " + ls_cdstato + ")");
        }


        l_query = "";
        l_query += " update pgmr.web_import_ordven                    \n";
        l_query += "    set tkposi = null                             \n";
        l_query += "      , profil = '" + profil + "'                     \n";
        l_query += "      , dtulag = " + sysdate + "                      \n";
        l_query += "  where tkposi in ( select tkposi                 \n";
        l_query += "                      from pgmr.web_ord_positito  \n";
        l_query += "                     where tkordi = " + tkordi + "    \n";
        l_query += "                         )                        \n";

        tot_rec = sql_update(l_query);



        l_query = "";
        l_query += " delete                         \n";
        l_query += "   from pgmr.web_ord_posi_matr  \n";
        l_query += "  where tkordi = " + tkordi + "     \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;
        tot_rec = pstm.executeUpdate();
        pstm.close();



        l_query = "";
        l_query += " delete                       \n";
        l_query += "   from pgmr.web_ord_positito \n";
        l_query += "  where tkordi = " + tkordi + "   \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;
        tot_rec = pstm.executeUpdate();
        pstm.close();



        tot_rec = calcolo_spese_trasporto(tkordi);

        if (tot_rec != 1) {
            throw new Exception("Errore UPD calcolo Spese Trasporto rc: " + tot_rec + " ");
        }

        tot_rec = calcola_iva(tkordi);

        if (tot_rec != 1) {
            throw new Exception("Errore Calcolo IVA --- rc: " + tot_rec + " ");
        }

        /*
         tot_rec = calcola_promozione(tkordi);

         if (tot_rec != 1){
         throw new Exception("Errore Calcolo Promozione --- rc: "+tot_rec+" ");
         }
         */


        return 1;

    }

    private long nuovo_articolo(long tkordi, String cdarti, double dimena, double dimenb, double dimenc) throws Exception {

        return nuovo_articolo(tkordi, cdarti, dimena, dimenb, dimenc, null, null);
    }

    /**
     * *
     *
     *
     */
    private long nuovo_articolo(long tkordi, String cdarti, double dimena, double dimenb, double dimenc, String cdartirif, String fgpromo) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;


        Web_ord_positito web_ord_positito = new Web_ord_positito();

        setProfilo((Atk_sql) web_ord_positito);

        Rec_web_ord_positito lstr = new Rec_web_ord_positito();

        lstr.tkordi = tkordi;
        lstr.cdarti = cdarti;
        lstr.dimena = dimena;
        lstr.dimenb = dimenb;
        lstr.dimenc = dimenc;
        if (cdartirif != null) {
            lstr.cdartirif = cdartirif;//EAR-20091012
        }
        if (fgpromo != null) {
            lstr.fgpromo = fgpromo;//EAR-20111203
        }

        tot_rec = 0;


        l_query = "";
        l_query += " select cdartm                   \n";
        l_query += "      , dsarti                   \n";
        l_query += "      , cdunim_1                 \n";
        l_query += "   from pgmr.mrp_arch_articoli   \n";
        l_query += "  where cdarti = ?               \n";
        l_query += "    and cdazie = ?               \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;
        pstm.setString(ind++, cdarti);
        pstm.setString(ind++, cdazie);

        rs = pstm.executeQuery();

        if (rs != null && rs.next()) {

            tot_rec += 1;
            ind = 0;
            if (rs.getObject("dsarti") != null) {
                lstr.dssart = rs.getString("dsarti");
            }
            if (rs.getObject("cdunim_1") != null) {
                lstr.cdunim = rs.getString("cdunim_1");
            }

        }
        pstm.close();

        lstr.fgsaldo = "N";
        lstr.nrposi = 9999;

        lstr.cdiva = "";
        lstr.cdfisc = "";
        lstr.cdagen_1 = "";


        l_query = "";
        l_query += "  select cdiva              \n";
        l_query += "       , cdfisc             \n";
        l_query += "       , cdagen             \n";
        l_query += "    from pgmr.web_ord_test  \n";
        l_query += "   where tkordi = ?         \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;
        pstm.setLong(ind++, tkordi);

        rs = pstm.executeQuery();

        if (rs != null && rs.next()) {

            tot_rec += 1;
            ind = 0;
            if (rs.getObject("cdiva") != null) {
                lstr.cdiva = rs.getString("cdiva");
            }
            if (rs.getObject("cdfisc") != null) {
                lstr.cdfisc = rs.getString("cdfisc");
            }
            if (rs.getObject("cdagen") != null) {
                lstr.cdagen_1 = rs.getString("cdagen");
            }

        }
        pstm.close();



        tot_rec = web_ord_positito.execute(lstr);

        if (tot_rec != 1) {
            throw new Exception("Errore INS web_ord_positito");
        }

        lstr.tkposi = web_ord_positito.tkposi;

        return lstr.tkposi;

    }

    /**
     * *
     *
     *
     */
    public int ordina_righe(long tkordi) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        PreparedStatement pstm_box = null;

        long nrposi = 0;



        l_query = "";
        l_query += "  select tkposi                \n";
        l_query += "       , nrposi                \n";
        l_query += "    from pgmr.web_ord_positito \n";
        l_query += "   where tkordi = " + tkordi + "   \n";
        l_query += "   order by tkposi             \n";

        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        while (rs != null && rs.next()) {

            long tkposi = 0;
            long nrposi_old = 0;

            if (rs.getObject(1) != null) {
                tkposi = rs.getLong(1);
            }
            if (rs.getObject(2) != null) {
                nrposi_old = rs.getLong(2);
            }

            nrposi += 10;

            if (nrposi_old != nrposi) {

                tot_rec = 0;

                l_query = "";
                l_query += " update pgmr.web_ord_positito  \n";
                l_query += "    set nrposi   = ?           \n";
                l_query += "      , nrposi_v = ?           \n";
                l_query += "      , profil   = ?           \n";
                l_query += "      , dtulag   = ?           \n";
                l_query += "  where tkposi   = ?           \n";

                pstm_box = m_connection.prepareStatement(l_query);

                ind = 1;
                pstm_box.setLong(ind++, nrposi);
                pstm_box.setLong(ind++, nrposi);
                pstm_box.setString(ind++, profil);
                pstm_box.setTimestamp(ind++, dtulag);
                pstm_box.setLong(ind++, tkposi);

                tot_rec = pstm_box.executeUpdate();
                pstm_box.close();

                if (tot_rec != 1) {
                    throw new Exception("Error UPD nrposi pgmr.web_ord_positito");
                }

            }

        }
        pstm.close();


        // verifico se ci sono sconti di riga

        Web_ord_test web_ord_test = new Web_ord_test();

        setProfilo((Atk_sql) web_ord_test);

        boolean lb_ = web_ord_test.isScontoRiga(tkordi);

        String ls_isscontiriga = "N";

        if (lb_) {
            ls_isscontiriga = "S";
        }

        tot_rec = 0;

        l_query = "";
        l_query += " update pgmr.web_ord_test  \n";
        l_query += "    set isscontiriga = '" + ls_isscontiriga + "' \n";
        l_query += "  where tkordi = " + tkordi + " \n";

        tot_rec = sql_update(l_query);



        return 1;

    }

    /**
     * *
     *
     *
     */
    public boolean isScontoRiga(long tkordi) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;


        l_query = "";
        l_query += " select isscontiriga        \n";
        l_query += "   from pgmr.web_ord_test   \n";
        l_query += "  where tkordi = " + tkordi + " \n";

        String ls_ = sql_String(l_query);

        if (ls_.equals("S")) {
            return true;
        }

        return false;
    }

    /**
     * *
     *
     *
     */
    public int calcolo_spese_trasporto(long tkordi) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;





        Costanti_comm costanti_comm = new Costanti_comm();

        setProfilo((Atk_sql) costanti_comm);


        Str_imptrasp lstr_trasp = new Str_imptrasp();

        lstr_trasp.tkordi = tkordi;



        double importonetto = 0;
        double imptrasp = 0;
        double qtatot = 0;

        l_query = "";
        l_query += " select sum(importonettoriga)           \n";
        l_query += "      , sum(qtatot)                     \n";
        l_query += "   from pgmr.web_ord_positito           \n";
        l_query += "  where tkordi = " + lstr_trasp.tkordi + "  \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;

        rs = pstm.executeQuery();

        if (rs != null && rs.next()) {

            tot_rec += 1;
            if (rs.getObject(1) != null) {
                importonetto = rs.getDouble(1);
            }
            if (rs.getObject(2) != null) {
                qtatot = rs.getDouble(2);
            }
        }
        pstm.close();


        if (qtatot == 0) {

            // non c'� niente da spedire!
            lstr_trasp.imptrasp = 0;
            lstr_trasp.cdvett1 = "";

            tot_rec = setImptrasp(tkordi, lstr_trasp.imptrasp, lstr_trasp.cdvett1);

            return 1;
        }



        // --- calcolo le spese di trasporto standard


        lstr_trasp.cdnazi = "";
        lstr_trasp.cdprov = "";

        tot_rec = 0;

        String cdstat_de = "";
        String cdprov_de = "";
        String comune_de = "";
        String cap_de = "";
        String cduldm = "";
        String tkclie = "";
        String fatrac = "";
        String fatraf = "";
        String fatrad = "";
        String cdclai = "";

        double addtra = 0;
        double nraddtra = 0;


        l_query = "";
        l_query += "  select ordt.cdstat_de                      \n";
        l_query += "       , ordt.cdprov_de                      \n";
        l_query += "       , ordt.comune_de                      \n";
        l_query += "       , ordt.cap_de                         \n";
        l_query += "       , ordt.cduldm                         \n";
        l_query += "       , ordt.tkclie                         \n";
        l_query += "       , clie.fatrac                         \n";
        l_query += "       , clie.fatraf                         \n";
        l_query += "       , clie.fatrad                         \n";
        l_query += "       , clie.addtra                         \n";
        l_query += "       , clie.nraddtra                       \n";
        l_query += "       , clie.cdclai                         \n";
        l_query += "    from pgmr.web_ord_test  ordt             \n";
        l_query += "       , pgmr.archclie clie                  \n";
        l_query += "   where ordt.tkordi = " + lstr_trasp.tkordi + " \n";
        l_query += "     and ordt.tkclie = clie.tkclie           \n";
        l_query += "     and clie.cdazie = '" + cdazie + "'          \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;

        rs = pstm.executeQuery();

        if (rs != null && rs.next()) {

            tot_rec += 1;

            if (rs.getObject("cdstat_de") != null) {
                cdstat_de = rs.getString("cdstat_de");
            }
            if (rs.getObject("cdprov_de") != null) {
                cdprov_de = rs.getString("cdprov_de");
            }
            if (rs.getObject("comune_de") != null) {
                comune_de = rs.getString("comune_de");
            }
            if (rs.getObject("cap_de") != null) {
                cap_de = rs.getString("cap_de");
            }
            if (rs.getObject("cduldm") != null) {
                cduldm = rs.getString("cduldm");
            }
            if (rs.getObject("tkclie") != null) {
                tkclie = rs.getString("tkclie");
            }
            if (rs.getObject("fatrac") != null) {
                fatrac = rs.getString("fatrac");
            }
            if (rs.getObject("fatraf") != null) {
                fatraf = rs.getString("fatraf");
            }
            if (rs.getObject("fatrad") != null) {
                fatrad = rs.getString("fatrad");
            }
            if (rs.getObject("cdclai") != null) {
                cdclai = rs.getString("cdclai");
            }

            if (rs.getObject("addtra") != null) {
                addtra = rs.getDouble("addtra");
            }
            if (rs.getObject("nraddtra") != null) {
                nraddtra = rs.getDouble("nraddtra");
            }

            pstm.close();

            lstr_trasp.cdnazi = cdstat_de;
            lstr_trasp.cdprov = cdprov_de;
            lstr_trasp.comune = comune_de;
            lstr_trasp.cap = cap_de;



            if (lstr_trasp.cdnazi.equals("")
                    || lstr_trasp.cdprov.equals("")
                    || lstr_trasp.comune.equals("")
                    || lstr_trasp.cap.equals("")) {

                // cerco dalla unit� locali

                l_query = "";
                l_query += "  select cdnazi                \n";
                l_query += "       , cdprov                \n";
                l_query += "       , comune                \n";
                l_query += "       , cap                   \n";
                l_query += "    from pgmr.unitalocali      \n";
                l_query += "   where cdunil = '" + cduldm + "' \n";

                pstm = m_connection.prepareStatement(l_query);

                ind = 1;

                rs = pstm.executeQuery();

                if (rs != null && rs.next()) {

                    tot_rec += 1;
                    if (rs.getObject(1) != null) {
                        lstr_trasp.cdnazi = rs.getString(1);
                    }
                    if (rs.getObject(2) != null) {
                        lstr_trasp.cdprov = rs.getString(2);
                    }
                    if (rs.getObject(3) != null) {
                        lstr_trasp.comune = rs.getString(3);
                    }
                    if (rs.getObject(4) != null) {
                        lstr_trasp.cap = rs.getString(4);
                    }
                }
                pstm.close();
            }


        } else {
            pstm.close();
        }




        double ld_imptrasp = calcolo_spese_trasporto_jmel(lstr_trasp.tkordi, importonetto, qtatot, lstr_trasp);

        if (ld_imptrasp == -100) {

            ld_imptrasp = calcolo_spese_trasporto_web(lstr_trasp.tkordi, importonetto, qtatot, lstr_trasp);
        }

        tot_rec = setImptrasp(tkordi, lstr_trasp.imptrasp, lstr_trasp.cdvett1);

        return 1;

    }

    public double calcolo_spese_trasporto_web(long tkordi, double importonetto, double qtatot, Str_imptrasp astr_trasp) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;


        // spese di trasporto non definite per questo cliente
        // applico l'agoritmo web.


        // Verifico se c'� un defaul generale 

        l_query = "";
        l_query += "  select perc                   \n";
        l_query += "       , importo_min            \n";
        l_query += "       , importo_max            \n";
        l_query += "    from pgmr.cat_imptrasp      \n";
        l_query += "   where cdnazi is null         \n";
        l_query += "     and cdprov is null         \n";


        pstm = m_connection.prepareStatement(l_query);

        ind = 1;

        rs = pstm.executeQuery();

        if (rs != null && rs.next()) {

            tot_rec += 1;
            if (rs.getObject(1) != null) {
                astr_trasp.perc = rs.getDouble(1);
            }
            if (rs.getObject(2) != null) {
                astr_trasp.importo_min = rs.getDouble(2);
            }
            if (rs.getObject(3) != null) {
                astr_trasp.importo_max = rs.getDouble(3);
            }
        }
        pstm.close();

        // Verifico se sono impostate a livello di nazione 

        l_query = "";
        l_query += "  select perc                             \n";
        l_query += "       , importo_min                      \n";
        l_query += "       , importo_max                      \n";
        l_query += "    from pgmr.cat_imptrasp                \n";
        l_query += "   where cdnazi = '" + astr_trasp.cdnazi + "' \n";
        l_query += "     and cdprov is null                   \n";


        pstm = m_connection.prepareStatement(l_query);

        ind = 1;

        rs = pstm.executeQuery();

        if (rs != null && rs.next()) {

            tot_rec += 1;
            if (rs.getObject(1) != null) {
                astr_trasp.perc = rs.getDouble(1);
            }
            if (rs.getObject(2) != null) {
                astr_trasp.importo_min = rs.getDouble(2);
            }
            if (rs.getObject(3) != null) {
                astr_trasp.importo_max = rs.getDouble(3);
            }
        }
        pstm.close();

        // Verifico se sono impostate a livello di provincia

        l_query = "";
        l_query += "  select perc                             \n";
        l_query += "       , importo_min                      \n";
        l_query += "       , importo_max                      \n";
        l_query += "    from pgmr.cat_imptrasp                \n";
        l_query += "   where cdnazi is null                   \n";
        l_query += "     and cdprov = '" + astr_trasp.cdprov + "' \n";


        pstm = m_connection.prepareStatement(l_query);

        ind = 1;

        rs = pstm.executeQuery();

        if (rs != null && rs.next()) {

            tot_rec += 1;
            if (rs.getObject(1) != null) {
                astr_trasp.perc = rs.getDouble(1);
            }
            if (rs.getObject(2) != null) {
                astr_trasp.importo_min = rs.getDouble(2);
            }
            if (rs.getObject(3) != null) {
                astr_trasp.importo_max = rs.getDouble(3);
            }
        }
        pstm.close();



        // Calcolo l'importo percentuale 

        astr_trasp.tipo_calc = astr_trasp.TIPO_CALC_PERC;

        double imptrasp = importonetto / 100 * astr_trasp.perc;


        if (imptrasp < astr_trasp.importo_min) {

            imptrasp = astr_trasp.importo_min;
            astr_trasp.tipo_calc = astr_trasp.TIPO_CALC_IMPORTO_MIN;
        }

        if (astr_trasp.importo_max > 0 && imptrasp > astr_trasp.importo_max) {

            imptrasp = astr_trasp.importo_max;
            astr_trasp.tipo_calc = astr_trasp.TIPO_CALC_IMPORTO_MAX;

        }


        astr_trasp.imptrasp = imptrasp;

        if (astr_trasp.tipo_calc == astr_trasp.TIPO_CALC_IMPORTO_MIN) {
            astr_trasp.perc = 0;
            astr_trasp.importo_max = 0;
        } else if (astr_trasp.tipo_calc == astr_trasp.TIPO_CALC_IMPORTO_MAX) {
            astr_trasp.perc = 0;
            astr_trasp.importo_min = 0;
        } else {
            astr_trasp.importo_min = 0;
            astr_trasp.importo_max = 0;
        }





        return astr_trasp.imptrasp;

    }

    /**
     * *
     * rc.: >= 0 ---> OK rc.: >= -100 ---> Spese di trasporto non trovate quindi
     * calcolo quelle web
     */
    public double calcolo_spese_trasporto_jmel(long tkordi, double importonetto, double qtatot, Str_imptrasp astr_trasp) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String ls_tkclie = "";
        String ls_cdtrme = "";
        String ls_pgcodi = "";


        tot_rec = 0;

        l_query = "";
        l_query += " select tkclie              \n";
        l_query += "      , cdtrme              \n";
        l_query += "      , pgcodi              \n";
        l_query += "   from pgmr.web_ord_test   \n";
        l_query += "  where tkordi = " + tkordi + " \n";

        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        if (rs != null && rs.next()) {
            if (rs.getObject("tkclie") != null) {
                ls_tkclie = rs.getString("tkclie");
            }
            if (rs.getObject("cdtrme") != null) {
                ls_cdtrme = rs.getString("cdtrme");
            }
            if (rs.getObject("pgcodi") != null) {
                ls_pgcodi = rs.getString("pgcodi");
            }

        }
        pstm.close();

        String ls_fdesti = "N";

        l_query = "";
        l_query += "  SELECT fdesti                    \n";
        l_query += "    FROM pgmr.trasamez             \n";
        l_query += "   WHERE cdtrme = '" + ls_cdtrme + "'  \n";

        ls_fdesti = sql_String(l_query);

        if (!ls_fdesti.equals("S")) {
            ls_fdesti = "N";
        }


        if (ls_fdesti.equals("S")) {

            astr_trasp.imptrasp = 0;

            return astr_trasp.imptrasp;
        }

        l_query = "";
        l_query += "  SELECT cdclac                    \n";
        l_query += "    FROM pgmr.archclie             \n";
        l_query += "   WHERE tkclie = '" + ls_tkclie + "'  \n";

        String ls_cdclac = sql_String(l_query);

        if (!ls_cdclac.equals("") && !ls_pgcodi.equals("")) {

            String ls_cdvett = "";

            tot_rec = 0;

            l_query = "";
            l_query += " SELECT imptrasp                                            \n";
            l_query += "      , cdvett                                              \n";
            l_query += "   FROM pgmr.mis_spese_trasporto                            \n";
            l_query += "  WHERE cdclac = '" + ls_cdclac + "'                            \n";
            l_query += "    AND pgcodi = '" + ls_pgcodi + "'                            \n";
            l_query += "    AND cdazie = '" + cdazie + "'                               \n";
            l_query += "    AND importo = (  SELECT MIN(ispt.importo)               \n";
            l_query += "                       FROM pgmr.mis_spese_trasporto  ispt  \n";
            l_query += "                      WHERE ispt.cdclac = '" + ls_cdclac + "'   \n";
            l_query += "                        AND ispt.pgcodi = '" + ls_pgcodi + "'   \n";
            l_query += "                        AND ispt.cdazie = '" + cdazie + "'      \n";
            l_query += "                           )                                \n";

            pstm = m_connection.prepareStatement(l_query);

            rs = pstm.executeQuery();

            if (rs != null && rs.next()) {

                tot_rec += 1;
                ind = 0;
                if (rs.getObject("imptrasp") != null) {
                    astr_trasp.imptrasp = rs.getDouble("imptrasp");
                }
                if (rs.getObject("cdvett") != null) {
                    astr_trasp.cdvett1 = rs.getString("cdvett");
                }

            }
            pstm.close();

        }   // FINE if (!ls_cdclac.equals("") && not !ls_pgcodi.equals("")){


        return astr_trasp.imptrasp;

    }

    /**
     * *
     *
     *
     */
    public int setImptrasp(long tkordi, double imptrasp, String cdvett1) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        if (cdvett1.equals("")) {
            cdvett1 = null;
        }


        l_query = "";
        l_query += " update pgmr.web_ord_test   \n";
        l_query += "    set imptrasp = ?   \n";
        l_query += "      , cdvett1  = ?   \n";
        l_query += "      , profil   = ?   \n";
        l_query += "      , dtulag   = ?   \n";
        l_query += "  where tkordi   = ?   \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;
        pstm.setDouble(ind++, imptrasp);
        pstm.setString(ind++, cdvett1);
        pstm.setString(ind++, profil);
        pstm.setTimestamp(ind++, dtulag);
        pstm.setLong(ind++, tkordi);

        tot_rec = pstm.executeUpdate();
        pstm.close();

        if (tot_rec != 1) {
            throw new Exception("Error UPD imptrasp pgmr.web_ord_test");
        }



        return 1;

    }

    /**
     * *
     *
     *
     */
    public int calcola_iva(long tkordi) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;



        Rec_web_ord_iva lstr_iva = null;

        F_iva f_iva = new F_iva();

        setProfilo((Atk_sql) f_iva);



        // --- resetto Tutti i Valori


        f_iva.reset(tkordi);


        double imptrasp = 0;
        double scocas = 0;
        double scval = 0;
        double tot_importo = 0;


        tot_rec = 0;

        l_query = "";
        l_query += " select test.tkordi                     \n";
        l_query += "      , test.imptrasp                   \n";
        l_query += "      , test.scocas                     \n";
        l_query += "      , test.scval                      \n";
        l_query += "      , posi.tkposi                     \n";
        l_query += "      , posi.cdarti                     \n";
        l_query += "      , posi.cdiva                      \n";
        l_query += "      , posi.cdfisc                     \n";
        l_query += "      , posi.importonettoriga           \n";
        l_query += "   from pgmr.web_ord_test       test    \n";
        l_query += "      , pgmr.web_ord_positito   posi    \n";
        l_query += "  where posi.tkordi = test.tkordi       \n";
        l_query += "    and test.tkordi = " + tkordi + "        \n";
        l_query += "  order by posi.nrposi                  \n";

        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        while (rs != null && rs.next()) {

            String cdarti = "";
            String cdiva = "";
            String cdfisc = "";
            double importonettoriga = 0;

            if (rs.getObject("imptrasp") != null) {
                imptrasp = rs.getDouble("imptrasp");
            }
            if (rs.getObject("scocas") != null) {
                scocas = rs.getDouble("scocas");
            }
            if (rs.getObject("scval") != null) {
                scval = rs.getDouble("scval");
            }

            if (rs.getObject("cdarti") != null) {
                cdarti = rs.getString("cdarti");
            }
            if (rs.getObject("cdiva") != null) {
                cdiva = rs.getString("cdiva");
            }
            if (rs.getObject("cdfisc") != null) {
                cdfisc = rs.getString("cdfisc");
            }
            if (rs.getObject("importonettoriga") != null) {
                importonettoriga = rs.getDouble("importonettoriga");
            }

            tot_importo += importonettoriga;


            lstr_iva = new Rec_web_ord_iva();

            lstr_iva.tkordi = tkordi;
            lstr_iva.cdiva = cdiva;
            lstr_iva.imponibile = importonettoriga;


            tot_rec = f_iva.add(lstr_iva);

            if (tot_rec != 1) {

                throw new Exception("Errore Calcolo IVA (cdarti = " + cdarti + ")");
            }
        }
        pstm.close();


        if (imptrasp > 0) {

            // calcolo l'iva sulle Spese di trasporto
            //               aggiungo le spese di trasporto 
            //               sull'aliquota pi� alta del carrello

            lstr_iva = new Rec_web_ord_iva();

            lstr_iva.tkordi = tkordi;
            lstr_iva.cdiva = "";
            lstr_iva.imponibile = imptrasp;

            tot_rec = f_iva.add(lstr_iva);

            if (tot_rec != 1) {

                throw new Exception("Errore Calcolo IVA (imptrasp = " + imptrasp + ")");
            }
        }


        if (scval > 0) {

            // Tolgo lo sconto : elimino l'importo dalla aliquota + 
            //                   bassa (condizione favorevole per il
            //                   fisco )

            lstr_iva = new Rec_web_ord_iva();

            lstr_iva.tkordi = tkordi;
            lstr_iva.cdiva = "";
            lstr_iva.imponibile = -1 * scval;

            tot_rec = f_iva.add(lstr_iva);

            if (tot_rec != 1) {

                throw new Exception("Errore Calcolo IVA (scval = " + scval + ")");
            }
        }


        if (scocas > 0) {

            // Tolgo lo sconto : elimino l'importo dalla aliquota + 
            //                   bassa (condizione favorevole per il
            //                   fisco )

            double sconto_cassa = tot_importo * scocas / 100;

            lstr_iva = new Rec_web_ord_iva();

            lstr_iva.tkordi = tkordi;
            lstr_iva.cdiva = "";
            lstr_iva.imponibile = -1 * sconto_cassa;

            tot_rec = f_iva.add(lstr_iva);

            if (tot_rec != 1) {

                throw new Exception("Errore Calcolo IVA (sconto_cassa = " + sconto_cassa + ")");
            }
        }


        // --- elimino eventuali record non valorizzati

        f_iva.clean(tkordi);


        return 1;

    }

    public long getDef_provvisorio(String cdstato) throws Exception {

        long tkordi = nuovo(cdstato);

        if (tkordi <= 0) {
            throw new Exception("Carrello DEF. NON trovato!");
        }

        return tkordi;
    }

    /**
     * *
     *
     *
     */
    public long getDef_provvisorio(long tkutente, String tkclie) throws Exception {

        return getDef_provvisorio(tkutente, tkclie, "PR");
    }

    /**
     * *
     *
     *
     */
    public long getDef_provvisorio(long tkutente, String tkclie, String cdstato) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        long tkordi = 0;

        tot_rec = 0;

        l_query = "";
        l_query += " select max(tkordi)             \n";
        l_query += "   from pgmr.web_ord_test       \n";
        l_query += "  where tkclie  = '" + tkclie + "'  \n";
        l_query += "    and tkutente = " + tkutente + " \n";
        l_query += "    and cdstato = '" + cdstato + "'          \n";
        if ("PR".equals(cdstato)) {
            l_query += "    and ( tkutente = tksubutente OR tksubutente is null )   \n";
        }

        //System.out.println(l_query);
        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        if (rs != null && rs.next()) {
            if (rs.getObject(++ind) != null) {
                tkordi = rs.getLong(ind);
            }
        }
        pstm.close();
        //System.out.println("tkordi def prov found: " +tkordi);
        if (tkordi <= 0) {
            tkordi = nuovo(tkutente, tkclie, cdstato);
        }

        if (tkordi <= 0) {
            throw new Exception("Carrello DEF. NON trovato!");
        }


        return tkordi;

    }

    /**
     * *
     *
     *
     */
    public long getCarrello(long tkutente, String tkclie) throws Exception {

        return getCarrello(tkutente, tkclie, "PR");
    }

    /**
     * *
     *
     *
     */
    public long getCarrello(long tkutente, String tkclie, String cdstato) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        long tkordi = 0;



        l_query = "";
        l_query += " select max(tkordi)               \n";
        l_query += "   from pgmr.web_ord_test         \n";
        l_query += "  where tkclie  = '" + tkclie + "'    \n";
        l_query += "    and tkutente = " + tkutente + "   \n";
        l_query += "    and cdstato = '" + cdstato + "'   \n";
        if ("PR".equals(cdstato)) {
            l_query += "    and ( tkutente = tksubutente OR tksubutente is null )   \n";
        }

        //System.out.println(l_query);
        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        if (rs != null && rs.next()) {
            if (rs.getObject(++ind) != null) {
                tkordi = rs.getLong(ind);
            }
        }
        pstm.close();

        return tkordi;

    }

    /**
     * *
     *
     *
     */
    public int getNref_carello(long tkordi) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        l_query = "";
        l_query += "  select count(*)               \n";
        l_query += "    from pgmr.web_ord_positito  \n";
        l_query += "   where tkordi = " + tkordi + "    \n";

        tot_rec = sql_int(l_query);

        return tot_rec;

    }

    /**
     * *
     *
     *
     */
//    public double getQuantita_in_carrello (long tkutente, String tkclie,  String cdarti) throws Exception {
//        
//         return getQuantita_in_carrello(tkutente, tkclie, cdarti, "PR");
//    }
    /**
     * *
     *
     *
     */
    //public double getQuantita_in_carrello (long tkutente, String tkclie,  String cdarti, String cdstato) throws Exception {
    public double getQuantita_in_carrello(String cdarti, long tkordi) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;


        double ldb_qta = 0;


        l_query = "";
        l_query += " select ordp.tkordi                  \n";
        l_query += "      , sum(ordp.qtatot)             \n";
        l_query += "   from pgmr.web_ord_test      ordt  \n";
        l_query += "      , pgmr.web_ord_positito  ordp  \n";
        l_query += "  where ordt.tkordi   = ordp.tkordi  \n";
        l_query += "    and ordt.tkordi   = ?            \n";
        //l_query += "    and ordt.cdstato  = ?            \n";
        //l_query += "    and ordt.tkclie   = ?            \n";
        //l_query += "    and ordt.tkutente = ?            \n";
        l_query += "    and ordp.cdarti   = ?            \n";
        l_query += "  group by ordp.tkordi               \n";
        l_query += "  order by ordp.tkordi desc          \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;
        //pstm.setString(ind++, cdstato);
        //pstm.setString(ind++, tkclie);
        //pstm.setLong  (ind++, tkutente);
        pstm.setLong(ind++, tkordi);
        pstm.setString(ind++, cdarti);

        rs = pstm.executeQuery();

        if (rs != null && rs.next()) {

            tot_rec += 1;
            ind = 0;
            if (rs.getObject(2) != null) {
                ldb_qta = rs.getDouble(2);
            }

        }
        pstm.close();



        return ldb_qta;

    }

    /**
     * *
     *
     *
     */
    public com.ateikon.structure.Str_dest_merce getDestinazione_merce(long tkordi) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        tot_rec = 0;






        com.ateikon.structure.Str_dest_merce lstr = new com.ateikon.structure.Str_dest_merce();

        l_query = "";
        l_query += "  select test.tkordi                            \n";
        l_query += "       , test.cdstato                           \n";
        l_query += "       , test.cduldm                            \n";
        l_query += "       , test.tkclie                            \n";
        l_query += "       , ente.ragcog                            \n";
        l_query += "       , ulsl.cdunil         as cdulsl          \n";
        l_query += "       , ulsl.indiri         as indiri_sl       \n";
        l_query += "       , ulsl.cap            as cap_sl          \n";
        l_query += "       , ulsl.comune         as comune_sl       \n";
        l_query += "       , ulsl.cdprov         as cdprov_sl       \n";
        l_query += "       , ulsl.cdnazi         as cdnazi_sl       \n";
        l_query += "       , nazi_sl.cdnazi_m    as cdnazi_m_sl     \n";
        l_query += "       , nazi_sl.dsnazi      as dsnazi_sl       \n";
        l_query += "       , prov_sl.cdprov_m    as cdprov_m_sl     \n";
        l_query += "       , prov_sl.dsprov      as dsprov_sl       \n";
        if (is_sybase) {

            l_query += "  from pgmr.web_ord_test  test                                                           \n";
            l_query += "     , pgmr.archenti      ente                                                           \n";
            l_query += "     , pgmr.enteuniloc    enul                                                           \n";
            l_query += "     ,  { oj              pgmr.unitalocali   ulsl                                        \n";
            l_query += "          left outer join pgmr.nazioni       nazi_sl on ulsl.cdnazi    = nazi_sl.cdnazi  \n";
            l_query += "          left outer join pgmr.province      prov_sl on ulsl.cdprov    = prov_sl.cdprov  \n";
            l_query += "          }                                                                              \n";
            l_query += " where test.tkordi  =  " + tkordi + "                                                        \n";
            l_query += "   and test.cdentc  = ente.cdente                                                        \n";
            l_query += "   and ente.cdente  = enul.cdente                                                        \n";
            l_query += "   and enul.fseleg  = 'S'                                                                \n";
            l_query += "   and enul.cdunil = ulsl.cdunil                                                         \n";
        } else if (is_postgresql) {

            l_query += "  from pgmr.web_ord_test  test                                                           \n";
            l_query += "     , pgmr.archenti      ente                                                           \n";
            l_query += "     , pgmr.enteuniloc    enul                                                           \n";
            l_query += "     , pgmr.unitalocali   ulsl                                                           \n";
            l_query += "          left outer join pgmr.nazioni       nazi_sl on ulsl.cdnazi    = nazi_sl.cdnazi  \n";
            l_query += "          left outer join pgmr.province      prov_sl on ulsl.cdprov    = prov_sl.cdprov  \n";
            l_query += " where test.tkordi  =  " + tkordi + "                                                        \n";
            l_query += "   and test.cdentc  = ente.cdente                                                        \n";
            l_query += "   and ente.cdente  = enul.cdente                                                        \n";
            l_query += "   and enul.fseleg  = 'S'                                                                \n";
            l_query += "   and enul.cdunil = ulsl.cdunil                                                         \n";

        } else {
            throw new Exception("DB NON Previsto !!");
        }

        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        if (rs != null && rs.next()) {

            if (rs.getObject("ragcog") != null) {
                lstr.ragcog_sl = rs.getString("ragcog");
            }
            if (rs.getObject("cdulsl") != null) {
                lstr.cdulsl = rs.getString("cdulsl");
            }
            if (rs.getObject("indiri_sl") != null) {
                lstr.indiri_sl = rs.getString("indiri_sl");
            }
            if (rs.getObject("cap_sl") != null) {
                lstr.cap_sl = rs.getString("cap_sl");
            }
            if (rs.getObject("comune_sl") != null) {
                lstr.comune_sl = rs.getString("comune_sl");
            }
            if (rs.getObject("cdprov_sl") != null) {
                lstr.cdprov_sl = rs.getString("cdprov_sl");
            }
            if (rs.getObject("cdnazi_sl") != null) {
                lstr.cdnazi_sl = rs.getString("cdnazi_sl");
            }
            if (rs.getObject("cdprov_m_sl") != null) {
                lstr.cdprov_m_sl = rs.getString("cdprov_m_sl");
            }
            if (rs.getObject("dsprov_sl") != null) {
                lstr.dsprov_sl = rs.getString("dsprov_sl");
            }
            if (rs.getObject("cdnazi_m_sl") != null) {
                lstr.cdnazi_m_sl = rs.getString("cdnazi_m_sl");
            }
            if (rs.getObject("dsnazi_sl") != null) {
                lstr.dsnazi_sl = rs.getString("dsnazi_sl");
            }

        }
        pstm.close();


        l_query = "";
        l_query += "  select test.tkordi                            \n";
        l_query += "       , test.cdstato                           \n";
        l_query += "       , test.cduldm                            \n";
        l_query += "       , test.tkclie                            \n";
        l_query += "       , uldm.dsunil         as dsunil_dm       \n";
        l_query += "       , uldm.indiri         as indiri_dm       \n";
        l_query += "       , uldm.cap            as cap_dm          \n";
        l_query += "       , uldm.comune         as comune_dm       \n";
        l_query += "       , uldm.cdprov         as cdprov_dm       \n";
        l_query += "       , uldm.cdnazi         as cdnazi_dm       \n";
        l_query += "       , nazi_dm.cdnazi_m    as cdnazi_m_dm     \n";
        l_query += "       , nazi_dm.dsnazi      as dsnazi_dm       \n";
        l_query += "       , prov_dm.cdprov_m    as cdprov_m_dm     \n";
        l_query += "       , prov_dm.dsprov      as dsprov_dm       \n";
        l_query += "       , test.ragcog_de                         \n";
        l_query += "       , test.indiri_de                         \n";
        l_query += "       , test.cap_de                            \n";
        l_query += "       , test.comune_de                         \n";
        l_query += "       , test.cdprov_de                         \n";
        l_query += "       , test.cdstat_de                         \n";
        l_query += "       , nazi_de.cdnazi_m     as cdnazi_m_de    \n";
        l_query += "       , nazi_de.dsnazi       as dsnazi_de      \n";
        l_query += "       , prov_de.cdprov_m     as cdprov_m_de    \n";
        l_query += "       , prov_de.dsprov       as dsprov_de      \n";
        if (is_sybase) {

            l_query += "    from { oj pgmr.web_ord_test      test                                                      \n";
            l_query += "              left outer join pgmr.nazioni       nazi_de on test.cdstat_de = nazi_de.cdnazi    \n";
            l_query += "              left outer join pgmr.province      prov_de on test.cdprov_de = prov_de.cdprov    \n";
            l_query += "              left outer join pgmr.unitalocali   uldm    on test.cduldm    = uldm.cdunil       \n";
            l_query += "              left outer join pgmr.nazioni       nazi_dm on uldm.cdnazi    = nazi_dm.cdnazi    \n";
            l_query += "              left outer join pgmr.province      prov_dm on uldm.cdprov    = prov_dm.cdprov    \n";
            l_query += "              }                                                                                \n";
            l_query += "   where test.tkordi = " + tkordi + "                                                              \n";

        } else if (is_postgresql) {

            l_query += "    from pgmr.web_ord_test      test                                                      \n";
            l_query += "              left outer join pgmr.nazioni       nazi_de on test.cdstat_de = nazi_de.cdnazi    \n";
            l_query += "              left outer join pgmr.province      prov_de on test.cdprov_de = prov_de.cdprov    \n";
            l_query += "              left outer join pgmr.unitalocali   uldm    on test.cduldm    = uldm.cdunil       \n";
            l_query += "              left outer join pgmr.nazioni       nazi_dm on uldm.cdnazi    = nazi_dm.cdnazi    \n";
            l_query += "              left outer join pgmr.province      prov_dm on uldm.cdprov    = prov_dm.cdprov    \n";
            l_query += "   where test.tkordi = " + tkordi + "                                                              \n";

        } else {
            throw new Exception("DB NON Previsto !!");
        }

        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        if (rs != null && rs.next()) {

            if (rs.getObject("cduldm") != null) {
                lstr.cduldm = rs.getString("cduldm");
            }
            if (rs.getObject("dsunil_dm") != null) {
                lstr.dsunil_dm = rs.getString("dsunil_dm");
            }
            if (rs.getObject("indiri_dm") != null) {
                lstr.indiri_dm = rs.getString("indiri_dm");
            }
            if (rs.getObject("cap_dm") != null) {
                lstr.cap_dm = rs.getString("cap_dm");
            }
            if (rs.getObject("comune_dm") != null) {
                lstr.comune_dm = rs.getString("comune_dm");
            }
            if (rs.getObject("cdprov_dm") != null) {
                lstr.cdprov_dm = rs.getString("cdprov_dm");
            }
            if (rs.getObject("cdnazi_dm") != null) {
                lstr.cdnazi_dm = rs.getString("cdnazi_dm");
            }
            if (rs.getObject("cdprov_m_dm") != null) {
                lstr.cdprov_m_dm = rs.getString("cdprov_m_dm");
            }
            if (rs.getObject("dsprov_dm") != null) {
                lstr.dsprov_dm = rs.getString("dsprov_dm");
            }
            if (rs.getObject("cdnazi_m_dm") != null) {
                lstr.cdnazi_m_dm = rs.getString("cdnazi_m_dm");
            }
            if (rs.getObject("dsnazi_dm") != null) {
                lstr.dsnazi_dm = rs.getString("dsnazi_dm");
            }

            if (rs.getObject("ragcog_de") != null) {
                lstr.ragcog_de = rs.getString("ragcog_de");
            }
            if (rs.getObject("indiri_de") != null) {
                lstr.indiri_de = rs.getString("indiri_de");
            }
            if (rs.getObject("cap_de") != null) {
                lstr.cap_de = rs.getString("cap_de");
            }
            if (rs.getObject("comune_de") != null) {
                lstr.comune_de = rs.getString("comune_de");
            }
            if (rs.getObject("cdprov_de") != null) {
                lstr.cdprov_de = rs.getString("cdprov_de");
            }
            if (rs.getObject("cdstat_de") != null) {
                lstr.cdstat_de = rs.getString("cdstat_de");
            }
            if (rs.getObject("cdprov_m_de") != null) {
                lstr.cdprov_m_de = rs.getString("cdprov_m_de");
            }
            if (rs.getObject("dsprov_de") != null) {
                lstr.dsprov_de = rs.getString("dsprov_de");
            }
            if (rs.getObject("cdnazi_m_de") != null) {
                lstr.cdnazi_m_de = rs.getString("cdnazi_m_de");
            }
            if (rs.getObject("dsnazi_de") != null) {
                lstr.dsnazi_de = rs.getString("dsnazi_de");
            }

        }
        pstm.close();




        return lstr;

    }

    /**
     * *
     *
     *
     */
    public Str_ordven_tot getTototali(long tkordi) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        Str_ordven_tot lstr = new Str_ordven_tot();


        l_query = "";
        l_query += " select sum(importonettoriga)    \n";
        l_query += "   from pgmr.web_ord_positito    \n";
        l_query += "  where tkordi = " + tkordi + "      \n";


        lstr.tot_importonettoriga = sql_double(l_query);


        tot_rec = 0;

        l_query = "";
        l_query += " select imptrasp             \n";
        l_query += "      , scval                \n";
        l_query += "      , scocas               \n";
        l_query += "   from pgmr.web_ord_test    \n";
        l_query += "  where tkordi = ?           \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;
        pstm.setLong(ind++, tkordi);

        rs = pstm.executeQuery();

        if (rs != null && rs.next()) {

            if (rs.getObject("imptrasp") != null) {
                lstr.imptrasp = rs.getDouble("imptrasp");
            }
            if (rs.getObject("scval") != null) {
                lstr.scval = rs.getDouble("scval");
            }
            if (rs.getObject("scocas") != null) {
                lstr.scocas = rs.getDouble("scocas");
            }
        }
        pstm.close();


        l_query = "";
        l_query += " select sum(iva)             \n";
        l_query += "   from pgmr.web_ord_iva     \n";
        l_query += "  where tkordi = " + tkordi + "  \n";

        lstr.tot_iva = sql_double(l_query);

        lstr.tot_imponibile = lstr.tot_importonettoriga;

        // --- Applico lo sconto cassa
        lstr.tot_imponibile = lstr.tot_imponibile * (100 - lstr.scocas) / 100;

        // --- Applico lo sconto promozione
        lstr.tot_imponibile = lstr.tot_imponibile - lstr.scval;

        // --- Aggiungo le spese di trasporto
        lstr.tot_imponibile = lstr.tot_imponibile + lstr.imptrasp;


        lstr.tot_ivato = lstr.tot_imponibile + lstr.tot_iva;

        return lstr;

    }

    /**
     * *
     *
     * cliente ==> DELONGHI
     *
     * // Eccezione Blinki // TOYX.IT DI ALESSANDRO DE BELLI // ARCOBALOCCO DI
     * FRANCO FIUMICELLI
     *
     * this.impmin = 0; this.qtamin = 0;
     */
    public Str_ordven_cond getCondven(String tkclie) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        Str_ordven_cond lstr = new Str_ordven_cond();



        // --------------------------------------------
        // ---      CONDIZIONI DI VENDITA STD       ---
        // --------------------------------------------

        // vrifico se ho una condizione particolare 
        // per questo cliente

        tot_rec = 0;

        l_query = "";
        l_query += " select impmin              \n";
        l_query += "      , qtamin              \n";
        l_query += "   from pgmr.condven        \n";
        l_query += "  where tkclie = ?          \n";
        l_query += "  order by tkcondven desc   \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;
        pstm.setString(ind, tkclie);
        ind += 1;

        rs = pstm.executeQuery();

        if (rs != null && rs.next()) {

            // Se l'importo � zero ==> questo cliente non ha minimo ordine
            if (rs.getObject("impmin") != null) {
                lstr.impmin = rs.getDouble("impmin");
            }
            if (rs.getObject("qtamin") != null) {
                lstr.qtamin = rs.getDouble("qtamin");
            }

            pstm.close();

            return lstr;
        }

        pstm.close();




        // --- trovo la classe di scont del cliente

        l_query = "";
        l_query += " select cdclac                \n";
        l_query += "   from pgmr.archclie         \n";
        l_query += "  where tkclie = '" + tkclie + "' \n";

        String cdclac = sql_String(l_query);




        String ls_fgweb = "N";
        int li_tot_ordven = 0;

        if (ls_fgweb.equals("S") && li_tot_ordven <= 0) {


            // questo � un nuovo cliente e fa il primo ordine web quindi 
            // verifico  se  ci  sono delle maggiorazioni per il primo  
            // acquisto WEB


            l_query = "";
            l_query += " select impmin             \n";
            l_query += "      , qtamin             \n";
            l_query += "   from pgmr.condven       \n";
            l_query += "  where fgnew  = ?         \n";
            l_query += "    and cdclac = ?         \n";
            l_query += "  order by tkcondven desc  \n";

            pstm = m_connection.prepareStatement(l_query);
            ind = 1;
            pstm.setString(ind, "S");
            ind += 1;
            pstm.setString(ind, cdclac);
            ind += 1;

            rs = pstm.executeQuery();

            if (rs != null && rs.next()) {
                if (rs.getObject("impmin") != null) {
                    lstr.impmin = rs.getDouble("impmin");
                }
                if (rs.getObject("qtamin") != null) {
                    lstr.qtamin = rs.getDouble("qtamin");
                }

                pstm.close();

                return lstr;
            }
            pstm.close();


            // Controllo delle condizioni di vendita di default

            l_query = "";
            l_query += " select impmin             \n";
            l_query += "      , qtamin             \n";
            l_query += "   from pgmr.condven       \n";
            l_query += "  where fgnew = 'S'        \n";
            l_query += "    and cdclac  is null    \n";
            l_query += "    and tkclie  is null    \n";
            l_query += "  order by tkcondven desc  \n";

            pstm = m_connection.prepareStatement(l_query);

            rs = pstm.executeQuery();

            if (rs != null && rs.next()) {
                if (rs.getObject("impmin") != null) {
                    lstr.impmin = rs.getDouble("impmin");
                }
                if (rs.getObject("qtamin") != null) {
                    lstr.qtamin = rs.getDouble("qtamin");
                }

                pstm.close();

                return lstr;
            }
            pstm.close();

        }   // FINE if (fgnew.equals("S")){




        // Controllo delle condizioni di vendita del carrello

        l_query = "";
        l_query += " select impmin             \n";
        l_query += "      , qtamin             \n";
        l_query += "   from pgmr.condven       \n";
        l_query += "  where fgnew   = ?        \n";
        l_query += "    and cdclac  = ?        \n";
        l_query += "  order by tkcondven desc  \n";

        pstm = m_connection.prepareStatement(l_query);
        ind = 1;
        pstm.setString(ind, "N");
        ind += 1;
        pstm.setString(ind, cdclac);
        ind += 1;

        rs = pstm.executeQuery();

        if (rs != null && rs.next()) {
            if (rs.getObject("impmin") != null) {
                lstr.impmin = rs.getDouble("impmin");
            }
            if (rs.getObject("qtamin") != null) {
                lstr.qtamin = rs.getDouble("qtamin");
            }

            pstm.close();

            return lstr;
        }

        pstm.close();


        // Controllo delle condizioni di vendita di default


        l_query = "";
        l_query += " select impmin                \n";
        l_query += "      , qtamin                \n";
        l_query += "   from pgmr.condven          \n";
        l_query += "  where fgnew = 'N'           \n";
        l_query += "    and cdclac  is null       \n";
        l_query += "    and tkclie  is null       \n";
        l_query += "  order by tkcondven desc     \n";

        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        if (rs != null && rs.next()) {
            if (rs.getObject("impmin") != null) {
                lstr.impmin = rs.getDouble("impmin");
            }
            if (rs.getObject("qtamin") != null) {
                lstr.qtamin = rs.getDouble("qtamin");
            }

            pstm.close();

            return lstr;

        }
        pstm.close();


        return lstr;

    }

    public int sendMail(long tkordi) throws Exception {
        return sendMail(tkordi, "N");
    }

    public int sendMainMail(long tkordi) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        long tkoff = 0;

        l_query = "";
        l_query += " select tkrifoff                \n";
        l_query += "   from pgmr.web_ord_test       \n";
        l_query += "  where tkordi  = '" + tkordi + "'  \n";

        //System.out.println(l_query);
        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        if (rs != null && rs.next()) {
            if (rs.getObject("tkrifoff") != null) {
                tkoff = rs.getLong("tkrifoff");
            }
        }

        if (tkoff > 0) {
            return 1;
        } else {
            return sendMail(tkordi, "N");
        }

    }

    public int sendMailOfferte(long tkordi) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        long tkoff = 0;

        l_query = "";
        l_query += " select tkordi                  \n";
        l_query += "   from pgmr.web_ord_test       \n";
        l_query += "  where tkrifoff  = '" + tkordi + "'  \n";

        //System.out.println(l_query);
        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        if (rs != null && rs.next()) {
            if (rs.getObject("tkordi") != null) {
                tkoff = rs.getLong("tkordi");
            }

            return sendMail(tkoff, "S");
        } else {
            return 1;
        }
    }

    /**
     * Nuovo metodo MPERUZZA
     */
    public int sendMail(long tkordi, String fgpromo) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        F_msgmod          f_msgmod          = new F_msgmod         ();
        F_eprogen_replace f_eprogen_replace = new F_eprogen_replace();
        Cat_costanti cat_costanti = new Cat_costanti();
        F_sender f_sender = new F_sender();
        Ep_lingua ep_lingua = new Ep_lingua();
        com.ateikon.common.Atk_messaggio atk_messaggio = new com.ateikon.common.Atk_messaggio();
        Vector attach = null;
        Vector attach_name = null;
        
        
        setProfilo((Atk_sql) f_msgmod         );
        setProfilo((Atk_sql) ep_lingua        );
        setProfilo((Atk_sql) f_sender         );
        setProfilo((Atk_sql) f_eprogen_replace);
        setProfilo((Atk_sql) cat_costanti);
        setProfilo((Atk_sql) atk_messaggio);
        com.voxbiblia.jresolver.Resolver resolver = f_sender.createMXResolver();
        System.out.println("send mail start");
        Str_msgmod lstr_msgmod = null;
        
        //imposto la lingua DEL CLIENTE
        String ls_lingua = "";
        
        l_query = " select ep_lingua.cdling from pgmr.ep_utente,      "
                + " pgmr.ep_lingua, pgmr.web_ord_test                   "
                + " where web_ord_test.tkclie = ep_utente.tkclie        "
                + " and ep_utente.cdling = ep_lingua.cdling_portale     "
                + " and web_ord_test.tkordi = " + tkordi + "            ";
        
        ls_lingua = sql_String(l_query);
        
        if("".equals(ls_lingua)) {
            ls_lingua = "I";
        }
        /*
         * MPERUZZA 20130204
         * TODO modifica provvisoria in attesa della gestione lingue francese, tedesca, spagnolo.
         */
//        if(!"I".equals(ls_lingua)) {
//            ls_lingua = "E";
//        }
        
        lstr_msgmod = f_msgmod.of_retrieve("OV_RICEZ", "GENERICA", ls_lingua);
        //Replace dei dati nel modello
        lstr_msgmod = (Str_msgmod) f_eprogen_replace.of_replace_msgmod("OV_RICEZ", tkordi, lstr_msgmod);
        
        //Invio notifica
        tot_rec = f_msgmod.of_send_mail(lstr_msgmod, resolver);
        
        long tkmsg = lstr_msgmod.tkmsg;
        
         
        if(tot_rec > 0 ) {

            tot_rec = sql_update("update pgmr.atk_messaggio set tkordi_web = "+ tkordi +" where tkmsg = "+ tkmsg);

            if(tot_rec > 0 ) {
                if (fgpromo.equals("S")) {
                    attach = new Vector();
                    attach_name = new Vector();

                    ShopUser user = (ShopUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    boolean isSpecList = user.getIsSpecList();

                    String siteRoot = cat_costanti.getCostvalue("siteroot");
                    String sep = System.getProperty("file.separator");
                    String file_name = "";

                    if (isSpecList) {
                        file_name = "CONDIZ_VENDITA_2012_ART_OFFERTA_USA.pdf";
                    } else {
                        file_name = "CONDIZ_VENDITA_2012_ART_OFFERTA_EURO.pdf";
                    }

                    String path = siteRoot + "offerte" + sep;

                    File f = new File(path + file_name);
                    if (f.exists()) {
                        attach.add(f);
                        attach_name.add(file_name);
                    }

                    tot_rec = atk_messaggio.setAttach(tkmsg, path, file_name, 0, "N");

                }      
                
            }
            
        }
        
        if(tot_rec > 0 ) {
            m_connection.commit();
        } else {
            m_connection.rollback(); 
        }
        
        cat_costanti.close();
        atk_messaggio.close();

        return tot_rec;
    }

    public String getTD(String as_) throws Exception {

        return getTD(as_, "");
    }

    public String getTD(String as_, String as_align) throws Exception {

        com.ateikon.util.HTML html = new com.ateikon.util.HTML();

        String ls_ = "";
        String ls_align = "";

        if (!as_align.equals("")) {

            ls_align = "align=\"" + as_align + "\"";
        }

        ls_ += "<td nowrap " + ls_align + ">";
        ls_ += html.text(as_);
        ls_ += "</td>";



        return ls_;
    }

    /**
     * *
     *
     *
     */
    public String html_posizioni(long tkordi, boolean lb_link, String fgpromo) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ResultSet resultSet = null;

        com.ateikon.util.HTML html = new com.ateikon.util.HTML();
        com.ateikon.util.Atk_ctrl atk_ctrl = new com.ateikon.util.Atk_ctrl();
        com.ateikon.structure.Str_html str_html = null;
        com.ateikon.common.Mrp_arch_stato mrp_arch_stato = new Mrp_arch_stato();

        Costanti_comm costanti_comm = new Costanti_comm();
        Ep_costanti ep_costanti = new Ep_costanti();

        setProfilo((Atk_sql) costanti_comm);
        setProfilo((Atk_sql) mrp_arch_stato);
        setProfilo((Atk_sql) ep_costanti);


        String lwm_orddimentav = costanti_comm.getCostvalue("lwm_orddimentav");


        String ls_lingua = "";
        
        l_query = " select ep_lingua.cdiso639 from pgmr.ep_utente,      "
                + " pgmr.ep_lingua, pgmr.web_ord_test                   "
                + " where web_ord_test.tkclie = ep_utente.tkclie        "
                + " and ep_utente.cdling = ep_lingua.cdling_portale     "
                + " and web_ord_test.tkordi = " + tkordi + "            ";
        
        ls_lingua = sql_String(l_query);


        java.text.NumberFormat numFormat = java.text.NumberFormat.getInstance(java.util.Locale.ITALY);
        numFormat.setGroupingUsed(false);
        numFormat.setMaximumFractionDigits(2);
        numFormat.setMinimumFractionDigits(0);

        java.text.NumberFormat qtaFormat = java.text.NumberFormat.getInstance(java.util.Locale.ITALY);
        qtaFormat.setGroupingUsed(true);
        qtaFormat.setMaximumFractionDigits(0);
        qtaFormat.setMinimumFractionDigits(0);


        java.text.NumberFormat przFormat = java.text.NumberFormat.getInstance(java.util.Locale.ITALY);
        przFormat.setGroupingUsed(true);
        przFormat.setMaximumFractionDigits(2);
        przFormat.setMinimumFractionDigits(2);

        java.text.NumberFormat dimFormat = java.text.NumberFormat.getInstance(java.util.Locale.ITALY);
        dimFormat.setGroupingUsed(true);
        dimFormat.setMaximumFractionDigits(3);
        dimFormat.setMinimumFractionDigits(0);

        String ls_ = "";

        String cdordi = "";
        String tkclie = "";
        double scocas = 0;
        String isscontiriga = "";
        String cdfisc = "";
        long tkmaga = 0;


        l_query = "";
        l_query += " select tkordi                  \n";
        l_query += "      , cdstato                 \n";
        l_query += "      , cdordi                  \n";
        l_query += "      , numord                  \n";
        l_query += "      , isscontiriga            \n";
        l_query += "      , scocas                  \n";
        l_query += "      , tkclie                  \n";
        l_query += "      , cdfisc                  \n";
        l_query += "      , tkmaga                  \n";
        l_query += "   from pgmr.web_ord_test       \n";
        l_query += "   where tkordi = ?             \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;
        pstm.setLong(ind++, tkordi);

        rs = pstm.executeQuery();

        if (rs != null && rs.next()) {

            if (rs.getObject("cdordi") != null) {
                cdordi = rs.getString("cdordi");
            }
            if (rs.getObject("scocas") != null) {
                scocas = rs.getDouble("scocas");
            }
            if (rs.getObject("isscontiriga") != null) {
                isscontiriga = rs.getString("isscontiriga");
            }
            if (rs.getObject("tkclie") != null) {
                tkclie = rs.getString("tkclie");
            }
            if (rs.getObject("cdfisc") != null) {
                cdfisc = rs.getString("cdfisc");
            }
            if (rs.getObject("tkmaga") != null) {
                tkmaga = rs.getLong("tkmaga");
            }
        }
        pstm.close();


        
        // --- Verifico se ci sono righe senza pacco

        l_query = "";
        l_query += " select art.cdarti                                                                  \n";
        l_query += "      , art.cdartm                                                                  \n";
        l_query += "      , art.dsarti                                                                  \n";
        l_query += "      , art.cdbarcode                                                               \n";
        l_query += "      , art.nrpeso_l                                                                \n";
        l_query += "      , art.nrpeso_n                                                                \n";
        l_query += "      , art.vlunlt                                                                  \n";
        //EAR - 20091015-I ---- aggiunta dati per vistosi
        l_query += "      , tip.cdvisttp                                                                \n";
        l_query += "      , tip.cdvisttp_m                                                              \n";
        l_query += "      , tip.dsvisttp                                                                \n";
        l_query += "      , fam.cdvistfam                                                               \n";
        l_query += "      , fam.cdvistfam_m                                                             \n";
        l_query += "      , fam.dsvistfam                                                               \n";
        l_query += "      , fam.dsvistfam_eng                                                           \n";
        l_query += "      , art.cdvistv1                                                                \n";
        l_query += "      , v1.dsvistv1                                                                 \n";
        l_query += "      , v1.dsextvistv1                                                              \n";
        l_query += "      , v1.dsextvistv1_eng                                                          \n";
        l_query += "      , art.cdvistv2                                                                \n";
        l_query += "      , v2.dsvistv2                                                                 \n";
        l_query += "      , v2.dsextvistv2                                                              \n";
        l_query += "      , v2.dsextvistv2_eng                                                          \n";
        l_query += "      , art.cdvistv3                                                                \n";
        l_query += "      , v3.dsvistv3                                                                 \n";
        l_query += "      , v3.dsextvistv3                                                              \n";
        l_query += "      , v3.dsextvistv3_eng                                                          \n";
        l_query += "      , colv.cdvistcolv                                                             \n";
        l_query += "      , colv.dsvistcolv                                                             \n";
        l_query += "      , colv.dsextvistcolv                                                          \n";
        l_query += "      , colv.dsextvistcolv_eng                                                      \n";
        l_query += "      , art.cdvistfinm                                                              \n";
        l_query += "      , finv.cdvistfinv                                                             \n";
        l_query += "      , finv.dsvistfinv                                                             \n";
        l_query += "      , finv.dsextvistfinv                                                          \n";
        l_query += "      , finv.dsextvistfinv_eng                                                      \n";
        l_query += "      , finm.dsvistfinm                                                             \n";
        l_query += "      , finm.dsextvistfinm                                                          \n";
        l_query += "      , finm.dsextvistfinm_eng                                                      \n";
        l_query += "      , elet.dsvistelet                                                             \n";
        //MPERUZZA - 20121012
        l_query += "      , elet.cdvistelet                                                             \n";
        l_query += "      , art.fgweb                                                                   \n";
        l_query += "      , art.vist_filedis                                                            \n";
        l_query += "      , elet.dsextvistelet                                                          \n";
        l_query += "      , elet.dsextvistelet_eng                                                      \n";
        l_query += "      , art.cdrepa                                                                  \n";
        //EAR - 20091015-F
        l_query += "      , op.tkposi                                                                   \n";
        l_query += "      , op.dssart                                                                   \n";
        l_query += "      , op.dimena                                                                   \n";
        l_query += "      , op.dimenb                                                                   \n";
        l_query += "      , op.dimenc                                                                   \n";
        l_query += "      , op.ncolli                                                                   \n";
        l_query += "      , op.qtatot                                                                   \n";
        l_query += "      , op.impuni                                                                   \n";
        l_query += "      , op.impuninetto                                                              \n";
        l_query += "      , op.importonettoriga                                                         \n";
        l_query += "      , op.scont1                                                                   \n";
        l_query += "      , op.scont2                                                                   \n";
        l_query += "      , op.scont3                                                                   \n";
        l_query += "      , op.scont4                                                                   \n";
        l_query += "      , op.scrap1                                                                   \n";
        l_query += "      , op.scrap2                                                                   \n";
        l_query += "      , op.nrposi                                                                   \n";
        l_query += "      , op.fg_annulla_sconti                                                        \n";
        l_query += "      , op.cdunim                                                                   \n";
        l_query += "      , op.cdtins                                                                   \n";
        l_query += "      , op.cdartirif                                                                \n";
        l_query += "      , mis.dsunim                                                                  \n";
        l_query += "      , mis.cdunim_m                                                                \n";
        l_query += "      , giac.dtprdisp                                                               \n";
        l_query += "      , giac.qtadisp                                                                \n";
        l_query += "   from pgmr.web_ord_positito  op                                                   \n";
        l_query += "       , pgmr.mrp_arch_articoli art                                  \n";
        l_query += "        LEFT OUTER JOIN  pgmr.unimisura         mis ON art.cdunim_1   = mis.cdunim   \n";
        //EAR - 20091015-I ---- aggiunta dati per vistosi
        l_query += "        LEFT OUTER JOIN  pgmr.vist_tipi         tip ON art.cdvisttp   = tip.cdvisttp                \n";
        l_query += "        LEFT OUTER JOIN  pgmr.vist_famiglia     fam ON art.cdvistfam  = fam.cdvistfam               \n";
        l_query += "        LEFT OUTER JOIN  pgmr.vist_var1         v1  ON art.cdvistv1   = v1.cdvistv1                 \n";
        l_query += "        LEFT OUTER JOIN  pgmr.vist_var2         v2  ON art.cdvistv2   = v2.cdvistv2                 \n";
        l_query += "        LEFT OUTER JOIN  pgmr.vist_var3         v3  ON art.cdvistv3   = v3.cdvistv3                 \n";
        l_query += "        LEFT OUTER JOIN  pgmr.vist_colori_vetro  colv ON art.cdvistcolv   = colv.cdvistcolv          \n";
        l_query += "        LEFT OUTER JOIN  pgmr.vist_finit_vetro  finv ON art.cdvistfinv   = finv.cdvistfinv          \n";
        l_query += "        LEFT OUTER JOIN  pgmr.vist_finit_mont   finm ON art.cdvistfinm   = finm.cdvistfinm          \n";
        l_query += "        LEFT OUTER JOIN  pgmr.vist_elettrificazioni  elet ON art.cdvistelet   = elet.cdvistelet     \n";
        l_query += "        LEFT OUTER JOIN  pgmr.mrp_file_giacenza giac ON art.cdarti = giac.cdarti  AND  giac.cdvar = 'STD'  AND  giac.tkmaga =   " + tkmaga + "    \n";
        //EAR - 20091015-F
        l_query += "  where op.tkordi = " + tkordi + "                                                      \n";
        l_query += "    and art.cdarti = op.cdarti                                                      \n";
        //l_query  += "    and giac.tkmaga =   "+tkmaga+"                                                  \n"; EAR 20110307 - filtro spostato nella left join
        //l_query  += "    and giac.cdvar = 'STD'                                                           \n";
        l_query += "    and art.cdarti = op.cdarti                                                      \n";
        l_query += "    and op.tkposi not in( select matr.tkposi                                        \n";
        l_query += "                            from pgmr.web_ord_posi_matr matr                        \n";
        l_query += "                           where matr.tkordi = " + tkordi + "                           \n";
        l_query += "                             )                                                      \n";
        l_query += "  order by op.nrposi                                                                \n";

        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();



        if (rs != null && rs.next()) {

            int tot_cols = 0;

            //ls_ += "<h2>Articoli</h2>";

            ls_ += "<table border=\"0\" cellpadding=\"4\" cellspacing=\"0\" class=\"dettaglio\">";
            ls_ += "<tr>";
            if (lb_link) {
                ls_ += "<th>&nbsp;</th>";
                ls_ += "<th>&nbsp;</th>";
            }
            //ls_ += "<th>&nbsp;</th>";
            //ls_ += "<th class=\"al\" valign=\"top\">Codice</th>";
            //ls_ += "<th class=\"al\" valign=\"top\">Articolo</th>";     tot_cols ++;
            //EAR - 20091015-I ---- aggiunta dati per vistosi
            ls_ += "<th align=\"left\" valign=\"top\"><span class=\"al\">"+modello_mail+"</span></th>";
            tot_cols++;
            ls_ += "<th width=\"80\" class=\"al\" valign=\"top\">"+varianti_mail+"</th>";
            tot_cols++;
            ls_ += "<th class=\"al\" valign=\"top\">"+vetro_mail+"</th>";
            tot_cols++;
            ls_ += "<th class=\"al\" valign=\"top\">"+montatura_mail+"</th>";
            tot_cols++;
            ls_ += "<th class=\"al\" valign=\"top\">"+elet_mail+"</th>";
            tot_cols++;
            ls_ += "<th width=\"60\" class=\"al\" valign=\"top\">"+pesolordo_mail+"</th>";
            tot_cols++;
            ls_ += "<th width=\"60\" class=\"al\" valign=\"top\">"+pesonetto_mail+"</th>";
            tot_cols++;
            ls_ += "<th class=\"al\" valign=\"top\">"+volume_mail+"</th>";
            tot_cols++;
            ls_ += "<th class=\"al\" valign=\"top\">"+codicearticolo_mail+"</th>";
            tot_cols++;
            //EAR - 20091015-F
            //ls_ += "<th class=\"al\" valign=\"top\">U.M.</th>";         tot_cols ++;

            for (int i = 0; i < 5; i++) {

                if (lwm_orddimentav.indexOf("A") == i) {
                    ls_ += "<th class=\"ar\" valign=\"top\">Lung</th>";
                    tot_cols++;
                } else if (lwm_orddimentav.indexOf("B") == i) {
                    ls_ += "<th class=\"ar\" valign=\"top\">Larg</th>";
                    tot_cols++;
                } else if (lwm_orddimentav.indexOf("C") == i) {
                    ls_ += "<th class=\"ar\" valign=\"top\">Spess</th>";
                    tot_cols++;
                } else if (lwm_orddimentav.indexOf("D") == i) {
                    ls_ += "<th class=\"ar\" valign=\"top\">Colli</th>";
                    tot_cols++;
                } else if (lwm_orddimentav.indexOf("Q") == i) {
                    ls_ += "<th class=\"ar\" valign=\"top\">Quantit&agrave;</th>";
                    tot_cols++;
                }

            }   // FINE for (int i=0; i<3; i++){
            if (isscontiriga.equals("S")) {
                ls_ += "<th class=\"ar\" valign=\"top\">"+prezzolistino_mail+"</th>";
                tot_cols++;
                ls_ += "<th class=\"ar\" valign=\"top\">"+sconto_mail+"<br/>%</th>";
                tot_cols++;
            }
            ls_ += "<th class=\"ar\" valign=\"top\">"+prezzo_mail+"<br/>"+unitario_mail+"</th>";
            tot_cols++;
            ls_ += "<th class=\"ar\" valign=\"top\">"+prezzototalenetto_mail+"</th>";
            tot_cols++;
            ls_ += "<th class=\"ar\" valign=\"top\">"+datadisponibilita_mail+"</th>";
            tot_cols++;
            ls_ += "</tr>";

            
            do {

                long tkposi = 0;
                String cdarti = "";
                String cdartm = "";
                String dsarti = "";
                String dssart = "";
                String dsunim = "";
                String cdunim = "";
                String cdtins = "";
                double dimena = 0;
                double dimenb = 0;
                double dimenc = 0;
                double ncolli = 0;
                double qtatot = 0;
                double impuni = 0;
                double impuninetto = 0;
                double importonettoriga = 0;
                double scont1 = 0;
                double scont2 = 0;
                double scont3 = 0;
                double scont4 = 0;
                double scrap1 = 0;
                double scrap2 = 0;
                double nrpeso_l = 0;
                double nrpeso_n = 0;
                double vlunlt = 0;
                String fg_annulla_sconti = "";
                //EAR - 20091015-I ---- aggiunta dati per vistosi
                String cdvisttp = "";
                String cdvisttp_m = "";
                String dsvisttp = "";
                String cdvistfam = "";
                String cdvistfam_m = "";
                String dsvistfam = "";
                String dsvistfam_eng = "";
                String cdvistv1 = "";
                String dsvistv1 = "";
                String dsextvistv1 = "";
                String dsextvistv1_eng = "";
                String cdvistv2 = "";
                String dsvistv2 = "";
                String dsextvistv2 = "";
                String dsextvistv2_eng = "";
                String cdvistv3 = "";
                String dsvistv3 = "";
                String dsextvistv3 = "";
                String dsextvistv3_eng = "";
                String cdvistcolv = "";
                String dsvistcolv = "";
                String dsextvistcolv = "";
                String dsextvistcolv_eng = "";
                String cdvistfinv = "";
                String dsvistfinv = "";
                String dsextvistfinv = "";
                String dsextvistfinv_eng = "";
                String cdvistfinm = "";
                String dsvistfinm = "";
                String dsextvistfinm = "";
                String dsextvistfinm_eng = "";
                String dsvistelet = "";
                //MPERUZZA 20121012
                String cdvistelet = "";
                String fgweb = "";
                String vist_filedis = "";
                String dsextvistelet = "";
                String dsextvistelet_eng = "";
                String cdrepa = "";
                //EAR - 20091015-F
                //EAR - 20100709-I
                String cdartirif = "";
                //EAR - 20100709-F
                //EAR - 20110120
                Timestamp dtprdisp = null;
                double qtadisp = 0;



                if (rs.getObject("tkposi") != null) {
                    tkposi = rs.getLong("tkposi");
                }
                if (rs.getObject("cdarti") != null) {
                    cdarti = rs.getString("cdarti");
                }
                if (rs.getObject("cdartm") != null) {
                    cdartm = rs.getString("cdartm");
                }
                if (rs.getObject("dsarti") != null) {
                    dsarti = rs.getString("dsarti");
                }
                if (rs.getObject("dssart") != null) {
                    dssart = rs.getString("dssart");
                }
                if (rs.getObject("dsunim") != null) {
                    dsunim = rs.getString("dsunim");
                }
                if (rs.getObject("dimena") != null) {
                    dimena = rs.getDouble("dimena");
                }
                if (rs.getObject("dimenb") != null) {
                    dimenb = rs.getDouble("dimenb");
                }
                if (rs.getObject("dimenc") != null) {
                    dimenc = rs.getDouble("dimenc");
                }
                if (rs.getObject("ncolli") != null) {
                    ncolli = rs.getDouble("ncolli");
                }
                if (rs.getObject("qtatot") != null) {
                    qtatot = rs.getDouble("qtatot");
                }
                if (rs.getObject("impuni") != null) {
                    impuni = rs.getDouble("impuni");
                }
                if (rs.getObject("impuninetto") != null) {
                    impuninetto = rs.getDouble("impuninetto");
                }
                if (rs.getObject("importonettoriga") != null) {
                    importonettoriga = rs.getDouble("importonettoriga");
                }
                if (rs.getObject("scont1") != null) {
                    scont1 = rs.getDouble("scont1");
                }
                if (rs.getObject("scont2") != null) {
                    scont2 = rs.getDouble("scont2");
                }
                if (rs.getObject("scont3") != null) {
                    scont3 = rs.getDouble("scont3");
                }
                if (rs.getObject("scont4") != null) {
                    scont4 = rs.getDouble("scont4");
                }
                if (rs.getObject("scrap1") != null) {
                    scrap1 = rs.getDouble("scrap1");
                }
                if (rs.getObject("scrap2") != null) {
                    scrap2 = rs.getDouble("scrap2");
                }
                if (rs.getObject("nrpeso_l") != null) {
                    nrpeso_l = rs.getDouble("nrpeso_l");
                }
                if (rs.getObject("nrpeso_n") != null) {
                    nrpeso_n = rs.getDouble("nrpeso_n");
                }
                if (rs.getObject("vlunlt") != null) {
                    vlunlt = rs.getDouble("vlunlt");
                }
                if (rs.getObject("fg_annulla_sconti") != null) {
                    fg_annulla_sconti = rs.getString("fg_annulla_sconti");
                }
                if (rs.getObject("cdunim") != null) {
                    cdunim = rs.getString("cdunim");
                }
                if (rs.getObject("cdtins") != null) {
                    cdtins = rs.getString("cdtins");
                }
                //EAR - 20091015-I ---- aggiunta dati per vistosi
                if (rs.getObject("cdvisttp") != null) {
                    cdvisttp = rs.getString("cdvisttp");
                }
                if (rs.getObject("cdvisttp_m") != null) {
                    cdvisttp_m = rs.getString("cdvisttp_m");
                }
                if (rs.getObject("dsvisttp") != null) {
                    dsvisttp = rs.getString("dsvisttp");
                }
                if (rs.getObject("cdvistfam") != null) {
                    cdvistfam = rs.getString("cdvistfam");
                }
                if (rs.getObject("cdvistfam_m") != null) {
                    cdvistfam_m = rs.getString("cdvistfam_m");
                }
                if (rs.getObject("dsvistfam") != null) {
                    dsvistfam = rs.getString("dsvistfam");
                }
                if (rs.getObject("dsvistfam_eng") != null) {
                    dsvistfam_eng = rs.getString("dsvistfam_eng");
                }
                if (rs.getObject("cdvistv1") != null) {
                    cdvistv1 = rs.getString("cdvistv1");
                }
                if (rs.getObject("dsvistv1") != null) {
                    dsvistv1 = rs.getString("dsvistv1");
                }
                if (rs.getObject("dsextvistv1") != null) {
                    dsextvistv1 = rs.getString("dsextvistv1");
                }
                if (rs.getObject("dsextvistv1_eng") != null) {
                    dsextvistv1_eng = rs.getString("dsextvistv1_eng");
                }
                if (rs.getObject("cdvistv2") != null) {
                    cdvistv2 = rs.getString("cdvistv2");
                }
                if (rs.getObject("dsvistv2") != null) {
                    dsvistv2 = rs.getString("dsvistv2");
                }
                if (rs.getObject("dsextvistv2") != null) {
                    dsextvistv2 = rs.getString("dsextvistv2");
                }
                if (rs.getObject("dsextvistv2_eng") != null) {
                    dsextvistv2_eng = rs.getString("dsextvistv2_eng");
                }
                if (rs.getObject("cdvistv3") != null) {
                    cdvistv3 = rs.getString("cdvistv3");
                }
                if (rs.getObject("dsvistv3") != null) {
                    dsvistv3 = rs.getString("dsvistv3");
                }
                if (rs.getObject("dsextvistv3") != null) {
                    dsextvistv3 = rs.getString("dsextvistv3");
                }
                if (rs.getObject("dsextvistv3_eng") != null) {
                    dsextvistv3_eng = rs.getString("dsextvistv3_eng");
                }
                if (rs.getObject("cdvistcolv") != null) {
                    cdvistcolv = rs.getString("cdvistcolv");
                }
                if (rs.getObject("dsvistcolv") != null) {
                    dsvistcolv = rs.getString("dsvistcolv");
                }
                if (rs.getObject("dsextvistcolv") != null) {
                    dsextvistcolv = rs.getString("dsextvistcolv");
                }
                if (rs.getObject("dsextvistcolv_eng") != null) {
                    dsextvistcolv_eng = rs.getString("dsextvistcolv_eng");
                }
                if (rs.getObject("cdvistfinv") != null) {
                    cdvistfinv = rs.getString("cdvistfinv");
                }
                if (rs.getObject("dsvistfinv") != null) {
                    dsvistfinv = rs.getString("dsvistfinv");
                }
                if (rs.getObject("dsextvistfinv") != null) {
                    dsextvistfinv = rs.getString("dsextvistfinv");
                }
                if (rs.getObject("dsextvistfinv_eng") != null) {
                    dsextvistfinv_eng = rs.getString("dsextvistfinv_eng");
                }
                if (rs.getObject("cdvistfinm") != null) {
                    cdvistfinm = rs.getString("cdvistfinm");
                }
                if (rs.getObject("dsvistfinm") != null) {
                    dsvistfinm = rs.getString("dsvistfinm");
                }
                if (rs.getObject("dsextvistfinm") != null) {
                    dsextvistfinm = rs.getString("dsextvistfinm");
                }
                if (rs.getObject("dsextvistfinm_eng") != null) {
                    dsextvistfinm_eng = rs.getString("dsextvistfinm_eng");
                }
                if (rs.getObject("dsvistelet") != null) {
                    dsvistelet = rs.getString("dsvistelet");
                }
                if (rs.getObject("dsextvistelet") != null) {
                    dsextvistelet = rs.getString("dsextvistelet");
                }
                if (rs.getObject("dsextvistelet_eng") != null) {
                    dsextvistelet_eng = rs.getString("dsextvistelet_eng");
                }
                //EAR - 20091015-F
                //EAR - 20100709-I
                if (rs.getObject("cdartirif") != null) {
                    cdartirif = rs.getString("cdartirif");
                }
                //EAR - 20100709-F
                //EAR - 20110120
                if (rs.getObject("dtprdisp") != null) {
                    dtprdisp = rs.getTimestamp("dtprdisp");
                }
                if (rs.getObject("qtadisp") != null) {
                    qtadisp = rs.getDouble("qtadisp");
                }
                //MPERUZZA - 20121015
                if (rs.getObject("fgweb") != null) {
                    fgweb = rs.getString("fgweb");
                }
                if (rs.getObject("vist_filedis") != null) {
                    vist_filedis = rs.getString("vist_filedis");
                }
                if (rs.getObject("cdvistelet") != null) {
                    cdvistelet = rs.getString("cdvistelet");
                }
                if (rs.getObject("dsextvistelet") != null) {
                    dsextvistelet = rs.getString("dsextvistelet");
                }
                if (rs.getObject("cdrepa") != null) {
                    cdrepa = rs.getString("cdrepa");
                }

                F_decode_barcode f_decode_barcode = new F_decode_barcode();

                setProfilo((Atk_sql) f_decode_barcode);

                Str_dec_barcode lstr_arti = new Str_dec_barcode();



                String ls_css_colli = "in in_num";
                String ls_cssa = "in in_num";
                String ls_cssb = "in in_num";
                String ls_cssc = "in in_num";

                String ls_ro_colli = "";
                String ls_ro_qta = "";
                String ls_roa = "";
                String ls_rob = "";
                String ls_roc = "";



                if (lb_link) {
                    lstr_arti.cdarti = cdarti;
                    lstr_arti.cdunim = cdunim;
                    lstr_arti.cdtins = cdtins;

                    f_decode_barcode.of_flag_calcolo(lstr_arti);

                    if (!lstr_arti.fdimena.equals("S")) {
                        ls_roa = "readOnly";
                    }
                    if (!lstr_arti.fdimenb.equals("S")) {
                        ls_rob = "readOnly";
                    }
                    if (!lstr_arti.fdimenc.equals("S")) {
                        ls_roc = "readOnly";
                    }

                    if (!lstr_arti.fdimena.equals("S")) {
                        ls_cssa = "in in_num in_ro";
                    }
                    if (!lstr_arti.fdimenb.equals("S")) {
                        ls_cssb = "in in_num in_ro";
                    }
                    if (!lstr_arti.fdimenc.equals("S")) {
                        ls_cssc = "in in_num in_ro";
                    }
                }


                String ls_sconto = "";
                //MPERUZZA 20121015
                String techsheet = "";

                String[] cdrepa_escl_List = new String[]{"010", "018"};

                ls_sconto = atk_ctrl.descrSconti(new double[]{scont1, scont2, scont3, scont4, scrap1, scrap2});

                String annotazione_posi_int = getAnnotazione_posi(tkposi, TIPONOTA_INTERNA);
                String annotazione_posi_cli = getAnnotazione_posi(tkposi, TIPONOTA_CLIENTE);

                String ls_query = "";
                //MPERUZZA
                ls_query = "select distinct pathschtec                                   ";
                ls_query += "from pgmr.vist_articoli_img,                                ";
                ls_query += "pgmr.mrp_arch_articoli,                                     ";
                ls_query += "pgmr.mis_reparto                                            ";
                ls_query += "where vist_articoli_img.cdarti = mrp_arch_articoli.cdarti   ";
                ls_query += "and mrp_arch_articoli.cdrepa = mis_reparto.cdrepa           ";
                if (!cdvisttp.equals("")) {
                    ls_query += " and mrp_arch_articoli.cdvisttp = '" + cdvisttp + "'      ";
                }
                if (!cdvistfam.equals("")) {
                    ls_query += " and mrp_arch_articoli.cdvistfam = '" + cdvistfam + "'    ";
                }
                if (!cdvistv1.equals("")) {
                    ls_query += " and mrp_arch_articoli.cdvistv1 =  '" + cdvistv1 + "'     ";
                }
                if (cdvistv1.equals("")) {
                    ls_query += " and mrp_arch_articoli.cdvistv1 is null                 ";
                }
                if (!cdvistv2.equals("")) {
                    ls_query += " and mrp_arch_articoli.cdvistv2 =  '" + cdvistv2 + "'     ";
                }
                if (cdvistv2.equals("")) {
                    ls_query += " and mrp_arch_articoli.cdvistv2 is null                 ";
                }
                if (!cdvistv3.equals("")) {
                    ls_query += " and mrp_arch_articoli.cdvistv3 =  '" + cdvistv3 + "'     ";
                }
                if (cdvistv3.equals("")) {
                    ls_query += " and mrp_arch_articoli.cdvistv3 is null                 ";
                }
                if (!cdvistelet.equals("")) {
                    ls_query += " and mrp_arch_articoli.cdvistelet =  '" + cdvistelet + "'  ";
                }
                if (!fgweb.equals("")) {
                    ls_query += " and mrp_arch_articoli.fgweb =  '" + fgweb + "'          ";
                    ls_query += " and mis_reparto.fgweb =  '" + fgweb + "'                ";
                }
                if (!cdrepa.equals("")) {
                    ls_query += " and mis_reparto.cdrepa not in                          ";
                    ls_query += " (                                                      ";
                    for (int i = 0; i < cdrepa_escl_List.length; i++) {
                        if (i == 0) {
                            ls_query += " '" + cdrepa_escl_List[i] + "'                    ";
                        } else {
                            ls_query += " , '" + cdrepa_escl_List[i] + "'                   ";
                        }
                    }
                    ls_query += " )                                                      ";

                }
                ls_query += " and pathschtec is not null                                 ";
                ls_query += " order by pathschtec                                        ";
                pstm = m_connection.prepareStatement(ls_query);

                resultSet = pstm.executeQuery();
                if (resultSet.next()) {
                    techsheet = resultSet.getString(1);
                }
                //if (org.apache.commons.lang.StringUtils.isNotEmpty(pstm)) {

                //}

                String path_to_techsheet = "";
                String path_techsheet = "fileresources/assembling_instructions/";
                if (org.apache.commons.lang.StringUtils.isNotEmpty(techsheet)) {
                    try {
                        path_to_techsheet = path_techsheet + techsheet;
                        File f = new File(path_to_techsheet);

                    } catch (Exception ex) {
                    }
                }
                String path_modello = "fileresources/models";
                String path_3D = path_modello + "/3D/";
                String nome_modello = vist_filedis;
                //igs
                String igs = path_3D + nome_modello + ".zip";

                //eprt
                String eprt = path_3D + nome_modello + ".EPRT";

                //easm
                String easm = path_3D + nome_modello + ".EASM";

                String path_2D = path_modello + "/2D/";
                //dwg cm
                String dwg_vers = "cm/";
                String dwg = path_2D + dwg_vers + nome_modello + ".dwg";

                ls_ += "<tr class=\"riga\">";
                if (lb_link) {
                    ls_ += "<td nowrap><a href=\"javaScript:atk_carr_delete(" + tkposi + ")\"><img src=\"img/b_cancella.gif\" /></a></td>";
                    ls_ += "<td nowrap><a href=\"javaScript:atk_nota('" + tkposi + "')\"><img src=\"img/ico_nota.gif\" /></a></td>";
                    ls_ += "<td nowrap>";
                    ls_ += "<input type=\"hidden\" name=\"tkposi\" value=\"" + tkposi + "\"/>";

                    ls_ += "<a href=\"javaScript:atk_scheda_edit(" + tkposi + ", '" + cdarti + "')\">" + html.text(cdartm) + "</a>";
                    ls_ += "</td>";
                } else {
                    /* 
                     * MPERUZZA 20121108 
                     * modifica per immagini ricambi
                     */
                    if (org.apache.commons.lang.StringUtils.isBlank(cdartirif)) {
                        ls_ += "<td class=\"r1\" nowrap><img src=\"http://www.vistosi.it/shop/static/images/articoli/disegnitecnici/thumb/" + org.apache.commons.lang.StringUtils.rightPad(org.apache.commons.lang.StringUtils.trimToEmpty(cdvistfam), 5)
                                + cdvisttp_m
                                + "  " + org.apache.commons.lang.StringUtils.rightPad(org.apache.commons.lang.StringUtils.trimToEmpty(cdvistv1), 3)
                                + org.apache.commons.lang.StringUtils.rightPad(org.apache.commons.lang.StringUtils.trimToEmpty(cdvistv2), 1)
                                + org.apache.commons.lang.StringUtils.rightPad(org.apache.commons.lang.StringUtils.trimToEmpty(cdvistv3), 2)
                                + "-.jpg\"/>"
                                //+"<br/>" +html.text(dsvistfam) + " " + html.text(cdvisttp)
                                //+ (!"".equals(cdartirif)?"<br/>ricambio":"") + "</strong>"
                                + "</td>";
                    } else {
                        String imageric = getDatiRicambio(cdartirif, cdarti);
                        ls_ += "<td class=\"r1\" nowrap><img src=\"http://www.vistosi.it/shop/static/images/articoli/disegnitecnici/thumb/" + imageric + "\"/>"
                                //+"<br/>" +html.text(dsvistfam) + " " + html.text(cdvisttp)
                                //+ (!"".equals(cdartirif)?"<br/>ricambio":"") + "</strong>"
                                + "</td>";
                    }
                    ls_ += "<td >&nbsp;</td>";  //variante
                    ls_ += "<td ><img src=\"http://www.vistosi.it/shop/images/articoli/vetro/" + cdvistfam_m
                            + "_" + cdvisttp + "_" + cdvistv1 + "_" + cdvistcolv + cdvistfinv
                            + ".jpg\"/></td>";
                    //MPERUZZA 20121108
                    if (org.apache.commons.lang.StringUtils.isNotBlank(cdvistfinm)) {
                        ls_ += "<td ><img src=\"http://www.vistosi.it/shop/static/images/articoli/montature/" + cdvistfinm + ".jpg\"/></td>"; //finitura montatura
                    } else {
                        ls_ += "<td >&nbsp;</td>"; //senza img finitura montatura
                    }
                    //FINE
                    ls_ += "<td >&nbsp;</td>";  //elet
                    ls_ += "<td colspan=\"3\" >";

                    ls_ += "<div style=\"width:150px; padding:0px; margin:0px; border:0px none;\">";

                    String ep_site_siteroot = "C:\\ateikon\\sorgenti\\shop2012\\build\\web\\";
//                    String ep_site_siteroot = ep_costanti.getCostvalue("ep.site_siteroot");

                    File pdf = new File(ep_site_siteroot + path_to_techsheet);
                    if (pdf.exists()) {
                        ls_ += "<a target=\"_blank\" href=\"http://www.vistosi.it/download/?f=" + path_to_techsheet + " \" class=\"\"  border=\"0\"><img src=\"http://www.vistosi.it/shop/static/images/pdf-icon.gif\" border=\"0\" style=\"margin-right:2px;\"></a>";
                    }
                    File cad = new File(ep_site_siteroot + dwg);
                    if (cad.exists()) {
                        ls_ += "<a target=\"_blank\" href=\"http://www.vistosi.it/download/?f=" + dwg + " \" class=\"\"  border=\"0\"><img src=\"http://www.vistosi.it/shop/static/images/dwg-icon.gif\" border=\"0\" style=\"margin-right:2px;\"></a>";
                    }
                    File down_1 = new File(ep_site_siteroot + easm);
                    if (down_1.exists()) {
                        ls_ += "<a target=\"_blank\" href=\"http://www.vistosi.it/download/?f=" + easm + " \" class=\"\"  border=\"0\"><img src=\"http://www.vistosi.it/shop/static/images/easm-icon.gif\" border=\"0\" style=\"margin-right:2px;\"></a>";
                    }
                    File down_2 = new File(ep_site_siteroot + igs);
                    if (down_2.exists()) {
                        ls_ += "<a target=\"_blank\" href=\"http://www.vistosi.it/download/?f=" + igs + " \" class=\"\" border=\"0\"><img src=\"http://www.vistosi.it/shop/static/images/iges-icon.gif\" border=\"0\" style=\"margin-right:2px;\"></a>";
                    }
                    File down_3 = new File(ep_site_siteroot + eprt);
                    if (down_3.exists()) {
                        ls_ += "<a target=\"_blank\" href=\"http://www.vistosi.it/download/?f=" + eprt + " \" class=\"\" border=\"0\"><img src=\"http://www.vistosi.it/shop/static/images/eprt-icon.gif\" border=\"0\" style=\"margin-right:2px;\"></a>";
                    }
                    ls_ += "</div>";

                    ls_ += "</td>";  //peso lordo
                    //ls_ += "<td >&nbsp;</td>";  //peso netto
                    ls_ += "<td >&nbsp;</td>";  //volume
                    ls_ += "<td colspan=\"6\" nowrap>";     //nota di riga


                    if (!annotazione_posi_cli.equals("")) {
                        ls_ += "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"nota-cli\">";
                        ls_ += "<tr>";
                        ls_ += "<td valign=\"top\"><p>Nota per Cliente: " + annotazione_posi_cli + "</p></td>";
                        ls_ += "</tr>";
                        ls_ += "</table>";
                    }

                    ls_ += "</td></tr>";
                    ls_ += "<tr class=\"dettaglio2\">";

                    //ls_ += "<td class=\"al\" nowrap>"+html.text(cdartm)+"</td>";
                }
                //ls_ += "<td class=\"al\" nowrap>"+html.text(dsarti)+"</td>";
                //EAR - 20091015-I ---- aggiunta dati per vistosi
                if(ls_lingua.equals("en")) {
                    ls_ += "<td valign=\"bottom\" nowrap><strong>" + html.text(dsvistfam_eng) + " " + html.text(cdvisttp) + (!"".equals(cdartirif) ? "<br/>ricambio" : "") + "</strong></td>";
                    ls_ += "<td valign=\"bottom\" class=\"al\" nowrap>" + (org.apache.commons.lang.StringUtils.isNotBlank(dsextvistv1_eng) ? html.text(dsextvistv1_eng) : cdvistv1) + " " + (org.apache.commons.lang.StringUtils.isNotBlank(dsextvistv2_eng) ? html.text(dsextvistv2_eng) : cdvistv2) + " " + (org.apache.commons.lang.StringUtils.isNotBlank(dsextvistv3_eng) ? html.text(dsextvistv3_eng) : cdvistv3) + "</td>";
                    ls_ += "<td valign=\"bottom\" class=\"al\" nowrap>" + html.text(dsextvistcolv_eng) + " " + html.text(dsextvistfinv_eng) + "</td>";
                    ls_ += "<td valign=\"bottom\" class=\"al\" nowrap>" + html.text(dsextvistfinm_eng) + "</td>";
                    ls_ += "<td valign=\"bottom\" class=\"al\" nowrap>" + html.text(dsextvistelet_eng) + "</td>";
                    ls_ += "<td align=\"right\">" + przFormat.format(nrpeso_l) + "</td>";      //peso lordo
                    ls_ += "<td align=\"right\">" + przFormat.format(nrpeso_n) + "</td>";      //peso netto
                    ls_ += "<td>" + przFormat.format(vlunlt) + "</td>";     //volume
                } else {
                    ls_ += "<td valign=\"bottom\" nowrap><strong>" + html.text(dsvistfam) + " " + html.text(cdvisttp) + (!"".equals(cdartirif) ? "<br/>ricambio" : "") + "</strong></td>";
                    ls_ += "<td valign=\"bottom\" class=\"al\" nowrap>" + (org.apache.commons.lang.StringUtils.isNotBlank(dsextvistv1) ? html.text(dsextvistv1) : cdvistv1) + " " + (org.apache.commons.lang.StringUtils.isNotBlank(dsextvistv2) ? html.text(dsextvistv2) : cdvistv2) + " " + (org.apache.commons.lang.StringUtils.isNotBlank(dsextvistv3) ? html.text(dsextvistv3) : cdvistv3) + "</td>";
                    ls_ += "<td valign=\"bottom\" class=\"al\" nowrap>" + html.text(dsextvistcolv) + " " + html.text(dsextvistfinv) + "</td>";
                    ls_ += "<td valign=\"bottom\" class=\"al\" nowrap>" + html.text(dsextvistfinm) + "</td>";
                    ls_ += "<td valign=\"bottom\" class=\"al\" nowrap>" + html.text(dsextvistelet) + "</td>";
                    ls_ += "<td align=\"right\">" + przFormat.format(nrpeso_l) + "</td>";      //peso lordo
                    ls_ += "<td align=\"right\">" + przFormat.format(nrpeso_n) + "</td>";      //peso netto
                    ls_ += "<td>" + przFormat.format(vlunlt) + "</td>";     //volume  
                }


                if (fgpromo.equals("S")) {

                    ls_ += "<td align=\"right\" nowrap><span nowrap class=\"cart-stato\" style=\"background-color: rgb(51,153,51);\">" + html.text(cdartm) + "</span></td>";  //cdartm

                } else {

                    int count = 0;

                    ResultSet rs_stati_art = mrp_arch_stato.getArtList(cdarti, true);
                    if (rs_stati_art != null && rs_stati_art.next()) {
                        if (rs_stati_art.getObject("tot") != null) {
                            count = rs_stati_art.getInt("tot");
                        }
                    }

                    rs_stati_art = mrp_arch_stato.getArtList(cdarti, false);


                    if (rs_stati_art != null && count == 1 && rs_stati_art.next()) {

                        String vist_fgrgb = "";
                        String vist_rgb_r = "";
                        String vist_rgb_g = "";
                        String vist_rgb_b = "";

                        if (rs_stati_art.getObject("vist_fgrgb") != null) {
                            vist_fgrgb = rs_stati_art.getString("vist_fgrgb");
                        }
                        if (rs_stati_art.getObject("vist_rgb_r") != null) {
                            vist_rgb_r = rs_stati_art.getString("vist_rgb_r");
                        }
                        if (rs_stati_art.getObject("vist_rgb_g") != null) {
                            vist_rgb_g = rs_stati_art.getString("vist_rgb_g");
                        }
                        if (rs_stati_art.getObject("vist_rgb_b") != null) {
                            vist_rgb_b = rs_stati_art.getString("vist_rgb_b");
                        }


                        if (vist_fgrgb.equals("S")) {
                            ls_ += "<td align=\"right\" nowrap><span nowrap class=\"cart-stato\" style=\"background-color: rgb(" + vist_rgb_r + "," + vist_rgb_g + "," + vist_rgb_b + ");\">" + html.text(cdartm) + "</span></td>";  //cdartm
                        } else {
                            ls_ += "<td align=\"right\" nowrap>" + html.text(cdartm) + "</td>";     //cdartm
                        }
                    } else {
                        ls_ += "<td align=\"right\" nowrap>" + html.text(cdartm) + "</td>";     //cdartm
                    }
                }


                //EAR - 20091015-F
                //ls_ += "<td class=\"al\" nowrap>"+html.text(dsunim)+"</td>";

                for (int i = 0; i < 5; i++) {

                    if (lwm_orddimentav.indexOf("A") == i) {
                        if (lb_link) {

                            str_html = new Str_html();

                            str_html.name = "dimena";
                            str_html.value = dimFormat.format(dimena);
                            str_html.css = ls_cssa;
                            str_html.size = "5";
                            str_html.maxlength = "10";


                            ls_ += "<td nowrap align=\"right\">";
                            ls_ += html.getText_box(str_html);
                            ls_ += "</td>";
                        } else {
                            ls_ += "<td nowrap align=\"right\">" + dimFormat.format(dimena) + "</td>";
                        }

                    } else if (lwm_orddimentav.indexOf("B") == i) {
                        if (lb_link) {
                            str_html = new Str_html();

                            str_html.name = "dimenb";
                            str_html.value = dimFormat.format(dimenb);
                            str_html.css = ls_cssb;
                            str_html.size = "5";
                            str_html.maxlength = "10";


                            ls_ += "<td nowrap align=\"right\">";
                            ls_ += html.getText_box(str_html);
                            ls_ += "</td>";

                        } else {
                            ls_ += "<td nowrap align=\"right\">" + dimFormat.format(dimenb) + "</td>";
                        }
                    } else if (lwm_orddimentav.indexOf("C") == i) {
                        if (lb_link) {
                            str_html = new Str_html();

                            str_html.name = "dimenc";
                            str_html.value = dimFormat.format(dimenc);
                            str_html.css = ls_cssc;
                            str_html.size = "5";
                            str_html.maxlength = "10";


                            ls_ += "<td nowrap align=\"right\">";
                            ls_ += html.getText_box(str_html);
                            ls_ += "</td>";

                        } else {
                            ls_ += "<td nowrap align=\"right\">" + dimFormat.format(dimenc) + "</td>";
                        }
                    } else if (lwm_orddimentav.indexOf("D") == i) {
                        if (lb_link) {
                            str_html = new Str_html();

                            str_html.name = "ncolli";
                            str_html.value = numFormat.format(ncolli);
                            str_html.css = ls_css_colli;
                            str_html.size = "5";
                            str_html.maxlength = "10";

                            ls_ += "<td nowrap align=\"right\">";
                            ls_ += html.getText_box(str_html);
                            ls_ += "</td>";

                        } else {
                            ls_ += "<td nowrap align=\"right\">" + numFormat.format(ncolli) + "</td>";
                        }
                    } else if (lwm_orddimentav.indexOf("Q") == i) {
                        ls_ += "<td width=\"30\" valign=\"bottom\" nowrap align=\"right\" style=\"font-size:14px;\"><strong>" + qtaFormat.format(qtatot) + "</strong></td>";
                    }

                }   // FINE for (int i=0; i<3; i++){
                if (isscontiriga.equals("S")) {
                    ls_ += "<td valign=\"bottom\" nowrap align=\"right\" style=\"font-size:14px;\">" + przFormat.format(impuni) + "</td>";
                    ls_ += "<td valign=\"bottom\" nowrap align=\"right\" style=\"font-size:14px;\">" + ls_sconto + "</td>";
                }
                ls_ += "<td valign=\"bottom\" nowrap align=\"right\" style=\"font-size:14px;\"><strong>" + przFormat.format(impuninetto) + "</strong></td>";
                ls_ += "<td valign=\"bottom\" nowrap align=\"right\" style=\"font-size:14px;\">" + przFormat.format(importonettoriga) + "</td>";


                double disp = Math.floor(qtadisp);

                if (disp > 0) {
                    ls_ += "<td valign=\"bottom\" nowrap align=\"right\"><strong>"+prox_disp_mail+"</strong></td>";
                } else {
                    if (dtprdisp != null) {
                        ls_ += "<td valign=\"bottom\" nowrap align=\"center\"><strong>" + atk_ctrl.getDate(dtprdisp) + "</strong></td>";
                    } else {
                        ls_ += "<td valign=\"bottom\" nowrap align=\"center\"><strong>&nbsp;</strong></td>";
                    }
                }

                ls_ += "</tr>";

//                if (!annotazione_posi_cli.equals("")){
//
//                    ls_ += "<tr>";
//                        if (lb_link){
//                            ls_ += "<td>&nbsp;</td>";
//                            ls_ += "<td>&nbsp;</td>";
//                        }
//                        ls_ += "<td nowrap>Nota:</td>";
//                        ls_ += "<td colspan=\""+tot_cols+"\">"+annotazione_posi_cli+"</td>";
//                    ls_ += "</tr>";
//                }
                if (!annotazione_posi_int.equals("")) {

                    ls_ += "<tr>";
                    if (lb_link) {
                        ls_ += "<td>&nbsp;</td>";
                        ls_ += "<td>&nbsp;</td>";
                    }
                    ls_ += "<td nowrap>Nota Int.:</td>";
                    ls_ += "<td colspan=\"" + tot_cols + "\">" + annotazione_posi_int + "</td>";
                    ls_ += "</tr>";
                }


            } while (rs != null && rs.next());

            ls_ += "</table>";
            //ls_ += "<br/>";

        }   // FINE if 
        pstm.close();



        // --- Verifico se ci sono righe senza pacco

        l_query = "";
        l_query += " select art.cdarti                                      \n";
        l_query += "      , art.cdartm                                      \n";
        l_query += "      , art.dsarti                                      \n";
        l_query += "      , art.cdbarcode                                   \n";
        l_query += "      , op.tkposi                                       \n";
        l_query += "      , op.dssart                                       \n";
        l_query += "      , op.dimena                                       \n";
        l_query += "      , op.dimenb                                       \n";
        l_query += "      , op.dimenc                                       \n";
        l_query += "      , op.ncolli                                       \n";
        l_query += "      , matr.qtatot                                     \n";
        l_query += "      , op.impuni                                       \n";
        l_query += "      , op.impuninetto                                  \n";
        l_query += "      , op.importonettoriga                             \n";
        l_query += "      , op.fg_annulla_sconti                            \n";
        l_query += "      , op.scont1                                       \n";
        l_query += "      , op.scont2                                       \n";
        l_query += "      , op.scont3                                       \n";
        l_query += "      , op.scont4                                       \n";
        l_query += "      , op.scrap1                                       \n";
        l_query += "      , op.scrap2                                       \n";
        l_query += "      , op.nrposi                                       \n";
        l_query += "      , mis.dsunim                                      \n";
        l_query += "      , mis.cdunim_m                                    \n";
        l_query += "      , anag.cdmatricola_m                              \n";
        l_query += "   from pgmr.web_ord_positito  op                       \n";
        l_query += "      , pgmr.web_ord_posi_matr matr                     \n";
        l_query += "      , pgmr.matr_anagrafica   anag                     \n";
        l_query += "       , pgmr.mrp_arch_articoli art                                  \n";
        l_query += "        LEFT OUTER JOIN  pgmr.unimisura         mis ON art.cdunim_1   = mis.cdunim   \n";
        l_query += "  where op.tkordi  = " + tkordi + "                         \n";
        l_query += "    and art.cdarti = op.cdarti                          \n";
        l_query += "    and op.tkposi  = matr.tkposi                        \n";
        l_query += "    and op.tkordi  = matr.tkordi                        \n";
        l_query += "    and matr.tkmatricola = anag.tkmatricola             \n";
        l_query += "  order by op.nrposi                                    \n";

        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        if (rs != null && rs.next()) {

            int tot_cols = 0;

            ls_ += "<h2>Pacchi</h2>";
            ls_ += "<table class=\"report\">";
            ls_ += "<tr>";
            if (lb_link) {
                ls_ += "<th>&nbsp;</th>";
                ls_ += "<th>&nbsp;</th>";
            }
            ls_ += "<th>Codice</th>";
            ls_ += "<th>Descrizione</th>";
            tot_cols++;
            //ls_ += "<th>U.M.</th>";     tot_cols ++;

            for (int i = 0; i < 5; i++) {

                if (lwm_orddimentav.indexOf("A") == i) {
                    //    ls_ += "<th>Lung</th>";     tot_cols ++;
                } else if (lwm_orddimentav.indexOf("B") == i) {
                    //    ls_ += "<th>Larg</th>";     tot_cols ++;
                } else if (lwm_orddimentav.indexOf("C") == i) {
                    //    ls_ += "<th>Spess</th>";     tot_cols ++;
                } else if (lwm_orddimentav.indexOf("D") == i) {
                    ls_ += "<th class=\"ar\">Colli</th>";
                    tot_cols++;
                } else if (lwm_orddimentav.indexOf("Q") == i) {
                    ls_ += "<th class=\"ar\">Quantit&agrave;</th>";
                    tot_cols++;
                }

            }   // FINE for (int i=0; i<3; i++){
            if (isscontiriga.equals("S")) {
                ls_ += "<th class=\"ar\">Prezzo<br/>Listino</th>";
                tot_cols++;
                ls_ += "<th class=\"ar\">Sconto %</th>";
                tot_cols++;
            }
            ls_ += "<th class=\"ar\">Prezzo<br/>Unitario</th>";
            tot_cols++;
            ls_ += "<th class=\"ar\">Importo<br/>Totale</th>";
            tot_cols++;
            ls_ += "</tr>";

            do {

                long tkposi = 0;
                String cdarti = "";
                String cdartm = "";
                String dsarti = "";
                String dssart = "";
                String dsunim = "";
                String cdmatricola_m = "";
                double dimena = 0;
                double dimenb = 0;
                double dimenc = 0;
                double ncolli = 0;
                double qtatot = 0;
                double impuni = 0;
                double impuninetto = 0;
                double importonettoriga = 0;
                double scont1 = 0;
                double scont2 = 0;
                double scont3 = 0;
                double scont4 = 0;
                double scrap1 = 0;
                double scrap2 = 0;

                if (rs.getObject("tkposi") != null) {
                    tkposi = rs.getLong("tkposi");
                }
                if (rs.getObject("cdarti") != null) {
                    cdarti = rs.getString("cdarti");
                }
                if (rs.getObject("cdmatricola_m") != null) {
                    cdmatricola_m = rs.getString("cdmatricola_m");
                }
                if (rs.getObject("cdartm") != null) {
                    cdartm = rs.getString("cdartm");
                }
                if (rs.getObject("dsarti") != null) {
                    dsarti = rs.getString("dsarti");
                }
                if (rs.getObject("dssart") != null) {
                    dssart = rs.getString("dssart");
                }
                if (rs.getObject("dsunim") != null) {
                    dsunim = rs.getString("dsunim");
                }
                if (rs.getObject("dimena") != null) {
                    dimena = rs.getDouble("dimena");
                }
                if (rs.getObject("dimenb") != null) {
                    dimenb = rs.getDouble("dimenb");
                }
                if (rs.getObject("dimenc") != null) {
                    dimenc = rs.getDouble("dimenc");
                }
                if (rs.getObject("ncolli") != null) {
                    ncolli = rs.getDouble("ncolli");
                }
                if (rs.getObject("qtatot") != null) {
                    qtatot = rs.getDouble("qtatot");
                }
                if (rs.getObject("impuni") != null) {
                    impuni = rs.getDouble("impuni");
                }
                if (rs.getObject("impuninetto") != null) {
                    impuninetto = rs.getDouble("impuninetto");
                }
                if (rs.getObject("importonettoriga") != null) {
                    importonettoriga = rs.getDouble("importonettoriga");
                }
                if (rs.getObject("scont1") != null) {
                    scont1 = rs.getDouble("scont1");
                }
                if (rs.getObject("scont2") != null) {
                    scont2 = rs.getDouble("scont2");
                }
                if (rs.getObject("scont3") != null) {
                    scont3 = rs.getDouble("scont3");
                }
                if (rs.getObject("scont4") != null) {
                    scont4 = rs.getDouble("scont4");
                }
                if (rs.getObject("scrap1") != null) {
                    scrap1 = rs.getDouble("scrap1");
                }
                if (rs.getObject("scrap2") != null) {
                    scrap2 = rs.getDouble("scrap2");
                }

                String annotazione_posi_int = getAnnotazione_posi(tkposi, TIPONOTA_INTERNA);
                String annotazione_posi_cli = getAnnotazione_posi(tkposi, TIPONOTA_CLIENTE);

                String ls_sconto = "";

                ls_sconto = atk_ctrl.descrSconti(new double[]{scont1, scont2, scont3, scont4, scrap1, scrap2});

                ls_ += "<tr>";
                if (lb_link) {
                    ls_ += "<td nowrap><a href=\"javaScript:atk_carr_delete(" + tkposi + ")\"><img src=\"img/b_cancella.gif\" /></a></td>";
                    ls_ += "<td nowrap><a href=\"javaScript:atk_nota('" + tkposi + "')\"><img src=\"img/ico_nota.gif\" /></a></td>";
                    ls_ += "<td nowrap><a href=\"javaScript:atk_scheda('" + cdarti + "')\">" + html.text(cdmatricola_m) + "</a></td>";
                } else {
                    ls_ += "<td nowrap>" + html.text(cdmatricola_m) + "</td>";
                }
                
                ls_ += "<td nowrap>" + html.text(dsarti) + "</td>";
                //ls_ += "<td nowrap>"+html.text(dsunim)+"</td>";

                for (int i = 0; i < 5; i++) {

                    if (lwm_orddimentav.indexOf("A") == i) {
                        //    ls_ += "<td nowrap align=\"right\">"+dimFormat.format(dimena)+"</td>";
                    } else if (lwm_orddimentav.indexOf("B") == i) {
                        //    ls_ += "<td nowrap align=\"right\">"+dimFormat.format(dimenb)+"</td>";
                    } else if (lwm_orddimentav.indexOf("C") == i) {
                        //    ls_ += "<td nowrap align=\"right\">"+dimFormat.format(dimenc)+"</td>";
                    } else if (lwm_orddimentav.indexOf("D") == i) {
                        ls_ += "<td nowrap align=\"right\">" + numFormat.format(ncolli) + "</td>";
                    } else if (lwm_orddimentav.indexOf("Q") == i) {
                        ls_ += "<td nowrap align=\"right\">" + qtaFormat.format(qtatot) + "</td>";
                    }

                }   // FINE for (int i=0; i<3; i++){
                if (isscontiriga.equals("S")) {
                    ls_ += "<td nowrap align=\"right\">" + przFormat.format(impuni) + "</td>";
                    ls_ += "<td nowrap align=\"right\">" + ls_sconto + "</td>";
                }
                ls_ += "<td nowrap align=\"right\">" + przFormat.format(impuninetto) + "</td>";
                ls_ += "<td nowrap align=\"right\">" + przFormat.format(importonettoriga) + "</td>";
                ls_ += "</tr>";


                if (!annotazione_posi_cli.equals("")) {

                    ls_ += "<tr>";
                    if (lb_link) {
                        ls_ += "<td>&nbsp;</td>";
                        ls_ += "<td>&nbsp;</td>";
                    }
                    ls_ += "<td nowrap>Nota per Cli.:</td>";
                    ls_ += "<td colspan=\"" + tot_cols + "\">" + annotazione_posi_cli + "</td>";
                    ls_ += "</tr>";
                }
                if (!annotazione_posi_int.equals("")) {

                    ls_ += "<tr>";
                    if (lb_link) {
                        ls_ += "<td>&nbsp;</td>";
                        ls_ += "<td>&nbsp;</td>";
                    }
                    ls_ += "<td nowrap>Nota Int.:</td>";
                    ls_ += "<td colspan=\"" + tot_cols + "\">" + annotazione_posi_int + "</td>";
                    ls_ += "</tr>";
                }

            } while (rs != null && rs.next());

            ls_ += "</table>";
            //ls_ += "<br/>";

        }

        ls_ += "<br/>";
        ls_ += "<br/>";

        // impost i totali

        Str_ordven_tot lstr_tot = getTototali(tkordi);
        Str_ordven_cond lstr_cond = getCondven(tkclie);


        String tafasc = "S";
        String dsfisc = "";

        Assofiscal assofiscal = new Assofiscal();

        setProfilo((Atk_sql) assofiscal);

        rs = assofiscal.getTafasc(cdfisc);

        if (rs != null && rs.next()) {

            if (rs.getObject("tafasc") != null) {
                tafasc = rs.getString("tafasc");
            }
            if (rs.getObject("dsfisc") != null) {
                dsfisc = rs.getString("dsfisc");
            }
        }



        ls_ += "<table width=\"359\" class=\"parametri\">";
        ls_ += "<tr>";
        ls_ += "<th width=\"140\" align=\"left\" valign=\"top\" nowrap>"+totale_ordine_mail+"<br/>"+iva_esclusa_mail+":</th>";
        //ls_ += "<td class=\"prezzo\" align=\"right\"></td>";
        ls_ += "<td valign=\"top\" style=\"font-size:16px;font-weight:bold;\" align=\"right\">" + przFormat.format(lstr_tot.tot_importonettoriga) + " &euro;</td>";
        ls_ += "</tr>";
        if (lstr_tot.tot_importonettoriga >= lstr_cond.impmin) {

            if (lstr_tot.scval > 0) {
                ls_ += "<tr>";
                ls_ += "<th width=\"140\" align=\"left\" valign=\"top\" nowrap>Sconto Promozione:</th>";
                //ls_ += "<td class=\"prezzo\" align=\"right\">&nbsp;</td>";
                ls_ += "<td class=\"prezzo\" valign=\"top\" align=\"right\">" + przFormat.format(lstr_tot.scval) + "</td>";
                ls_ += "</tr>";
            }

            if (lstr_tot.imptrasp > 0) {

                ls_ += "<tr>";
                ls_ += "<th width=\"140\" align=\"left\" valign=\"top\" nowrap>"+spese_trasporto_mail+":</th>";
                //ls_ += "<td class=\"prezzo\" align=\"right\"></td>";
                ls_ += "<td class=\"prezzo\" valign=\"top\" align=\"right\">" + przFormat.format(lstr_tot.imptrasp) + " &euro;</td>";
                ls_ += "</tr>";
            } else {
                ls_ += "<tr>";
                ls_ += "<th width=\"140\" align=\"left\" valign=\"top\" nowrap>"+spese_trasporto_mail+":</th>";
                //ls_ += "<td class=\"nota\" align=\"right\">&nbsp;</td>";
                ls_ += "<td class=\"nota\" align=\"right\">"+nota_ordine_mail+" (2)</td>";
                ls_ += "</tr>";

            }
            if (lstr_tot.scocas > 0) {
                ls_ += "<tr>";
                ls_ += "<th width=\"140\" align=\"left\" valign=\"top\" nowrap>Sconto Cassa:</th>";
                //ls_ += "<td class=\"prezzo\" align=\"right\">&nbsp;</td>";
                ls_ += "<td class=\"prezzo\" align=\"right\">" + numFormat.format(lstr_tot.scocas) + " (Vedi note ***)</td>";
                ls_ += "</tr>";
            }

            // --- Vedi Mail del 16/06/2008

            /*

             ls_ += "<tr>";
             ls_ += "<td colspan=\"3\" style=\"border-top: 1px solid #000;\"><img src=\"img/null.gif\"/></td>";
             ls_ += "</tr>";

             ls_ += "<tr>";
             ls_ += "<th>Totale Imponibile:</th>";
             ls_ += "<td class=\"prezzo\" align=\"right\">&euro;</td>";
             ls_ += "<td class=\"prezzo\" align=\"right\">"+przFormat.format( lstr_tot.tot_imponibile )+"</td>";
             ls_ += "</tr>";


             if (tafasc.equals("S")){
             ls_ += "<tr>";
             ls_ += "<th>Tot. I.V.A.:</th>";
             ls_ += "<td class=\"prezzo\" align=\"right\">&euro;</td>";
             ls_ += "<td class=\"prezzo\" align=\"right\">"+przFormat.format( lstr_tot.tot_iva )+"</td>";
             ls_ += "</tr>";
             ls_ += "<tr>";
             ls_ += "<td colspan=\"3\" style=\"border-top: 1px solid #000;\"><img src=\"img/null.gif\"/></td>";
             ls_ += "</tr>";
             ls_ += "<tr>";
             ls_ += "<th>Totale Ordine IVA Compresa:</th>";
             ls_ += "<td class=\"prezzo\" align=\"right\">&euro;</td>";
             ls_ += "<td class=\"prezzo\" align=\"right\">"+przFormat.format( lstr_tot.tot_ivato )+"</td>";
             ls_ += "</tr>";

             }   // FINE if (s_tafasc.equals("")){
             */

            // --- FINE Vedi Mail del 16/06/2008

        }   // FINE if (lstr_tot.tot_importonettoriga >= lstr_cond.impmin) {
        //ls_ += "</table>";
        //ls_ += "<br/>";
        //ls_ += "<br/>";
        //ls_ += "<br/>";
        if (lstr_tot.tot_importonettoriga < lstr_cond.impmin) {
            ls_ += "<tr>";
            ls_ += "<td class=\"nota\" colspan=\"2\">(Importo minimo ordine di &euro; " + przFormat.format(lstr_cond.impmin) + " non raggiunto.)</td>";
            ls_ += "</tr>";
        }
//        if (lstr_tot.imptrasp <= 0){
//            ls_ += "<tr>";
//            ls_ += "<td class=\"nota\" colspan=\"2\">** Spese di trasporto addebitate in fattura se previsto.</td>";
//            ls_ += "</tr>";
//        }
        if (lstr_tot.scocas > 0) {
            ls_ += "<tr>";
            ls_ += "<td class=\"nota\" colspan=\"2\">*** Sconto Cassa in caso di pagamento alla Consegna.</td>";
            ls_ += "</tr>";
        }
//        ls_ += "<tr>";
//        ls_ += "<td class=\"nota\" colspan=\"2\">Contributo RAEE addebitato in fattura se previsto.</td>";
//        ls_ += "</tr>";

        ls_ += "</table>";
        //ls_ += "<br/>";

        ep_costanti.close();
        costanti_comm.close();
        mrp_arch_stato.close();

        return ls_;

    }

    /**
     * *
     *
     *
     */
    public String html_riferimento_ordi(long tkordi) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        com.ateikon.util.Atk_ctrl atk_ctrl = new com.ateikon.util.Atk_ctrl();
        com.ateikon.util.HTML html = new com.ateikon.util.HTML();

        Costanti_comm costanti_comm = new Costanti_comm();

        setProfilo((Atk_sql) costanti_comm);


        java.text.NumberFormat numFormat = java.text.NumberFormat.getInstance(java.util.Locale.ITALY);
        numFormat.setGroupingUsed(false);
        numFormat.setMaximumFractionDigits(2);
        numFormat.setMinimumFractionDigits(0);

        java.text.NumberFormat qtaFormat = java.text.NumberFormat.getInstance(java.util.Locale.ITALY);
        qtaFormat.setGroupingUsed(true);
        qtaFormat.setMaximumFractionDigits(0);
        qtaFormat.setMinimumFractionDigits(0); //EAR 20091214

        java.text.NumberFormat przFormat = java.text.NumberFormat.getInstance(java.util.Locale.ITALY);
        przFormat.setGroupingUsed(false);
        przFormat.setMaximumFractionDigits(2);
        przFormat.setMinimumFractionDigits(2);

        java.text.NumberFormat dimFormat = java.text.NumberFormat.getInstance(java.util.Locale.ITALY);
        dimFormat.setGroupingUsed(true);
        dimFormat.setMaximumFractionDigits(3);
        dimFormat.setMinimumFractionDigits(0);

        String ls_ = "";

        String cdordi = "";
        Timestamp dtordi = null;
        Timestamp dtcons = null;
        long numord = 0;
        long tkutente = 0;
        long tksubutente = 0;
        String tkclie = "";
        String cdente = "";
        String ragcog_de = "";
        String cdagen = "";
        String cdpromo_m = ""; //EAR - 20091015 aggiunta per Vistosi


        l_query = "";
        l_query += " select ordt.cdordi                   \n";
        l_query += "      , ordt.dtordi                   \n";
        l_query += "      , ordt.dtcons                   \n";
        l_query += "      , ordt.numord                   \n";
        l_query += "      , ordt.tkutente                 \n";
        l_query += "      , ordt.tkclie                   \n";
        l_query += "      , ordt.cdentc                   \n";
        l_query += "      , ordt.ragcog_de                \n";
        l_query += "      , ordt.cdagen                   \n";
        l_query += "      , ordt.cdpromo_m                \n"; //EAR - 20091015 aggiunta per Vistosi
        l_query += "      , ordt.tksubutente              \n"; //EAR - 20110921 aggiunta per Vistosi gestione mail notifica subutenti
        l_query += "   from pgmr.web_ord_test  ordt       \n";
        l_query += "  where ordt.tkordi   = " + tkordi + "    \n";

        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        if (rs != null && rs.next()) {


            if (rs.getObject("cdordi") != null) {
                cdordi = rs.getString("cdordi");
            }
            if (rs.getObject("dtordi") != null) {
                dtordi = rs.getTimestamp("dtordi");
            }
            if (rs.getObject("dtcons") != null) {
                dtcons = rs.getTimestamp("dtcons");
            }
            if (rs.getObject("numord") != null) {
                numord = rs.getLong("numord");
            }
            if (rs.getObject("tkutente") != null) {
                tkutente = rs.getLong("tkutente");
            }
            if (rs.getObject("tkclie") != null) {
                tkclie = rs.getString("tkclie");
            }
            if (rs.getObject("cdentc") != null) {
                cdente = rs.getString("cdentc");
            }
            if (rs.getObject("ragcog_de") != null) {
                ragcog_de = rs.getString("ragcog_de");
            }
            if (rs.getObject("cdagen") != null) {
                cdagen = rs.getString("cdagen");
            }
            if (rs.getObject("cdpromo_m") != null) {
                cdpromo_m = rs.getString("cdpromo_m");
            }
            if (rs.getObject("tksubutente") != null) {
                tksubutente = rs.getLong("tksubutente");
            }

        } else {
            return "";
        }
        pstm.close();
        pstm = null;
        
        String annotazione_int = getAnnotazione(tkordi, TIPONOTA_INTERNA);
        String annotazione_cli = getAnnotazione(tkordi, TIPONOTA_CLIENTE);


        // --- Destinazione merce

        String ls_dsdest_merce = "";

        com.ateikon.structure.Str_dest_merce lstr_dest = getDestinazione_merce(tkordi);

        /*System.out.println("lstr_dest.ragcog_de: " + lstr_dest.ragcog_de);
         System.out.println("lstr_dest.cduldm: " + lstr_dest.cduldm);
         System.out.println("lstr_dest.cdulsl: " + lstr_dest.cdulsl);*/
        if (!lstr_dest.ragcog_de.equals("")) {

            ls_dsdest_merce = "";
            ls_dsdest_merce += html.text(lstr_dest.ragcog_de) + "<br/>";
            ls_dsdest_merce += html.text(lstr_dest.indiri_de) + "<br/>";
            ls_dsdest_merce += html.text(lstr_dest.cap_de) + " " + html.text(lstr_dest.comune_de) + "(" + lstr_dest.cdprov_de + ")" + "<br/>"; //EAR - 20091014 - Vistosi non usa la tabella provincie
            ls_dsdest_merce += html.text(lstr_dest.dsnazi_de) + "<br/>";

        } else if (!lstr_dest.cduldm.equals("")) {

            ls_dsdest_merce = "";
            ls_dsdest_merce += html.text(lstr_dest.ragcog_sl) + "<br/>";
            ls_dsdest_merce += html.text(lstr_dest.indiri_dm) + "<br/>";
            ls_dsdest_merce += html.text(lstr_dest.cap_dm) + " " + html.text(lstr_dest.comune_dm) + "(" + lstr_dest.cdprov_dm + ")" + "<br/>"; //EAR - 20091014 - Vistosi non usa la tabella provincie
            ls_dsdest_merce += html.text(lstr_dest.dsnazi_dm) + "<br/>";


        } else if (!lstr_dest.cdulsl.equals("")) {

            ls_dsdest_merce = "";
            ls_dsdest_merce += html.text(lstr_dest.ragcog_sl) + "<br/>";
            ls_dsdest_merce += html.text(lstr_dest.indiri_sl) + "<br/>";
            ls_dsdest_merce += html.text(lstr_dest.cap_sl) + " " + html.text(lstr_dest.comune_sl) + "(" + lstr_dest.cdprov_sl + ")" + "<br/>"; //EAR - 20091014 - Vistosi non usa la tabella provincie
            ls_dsdest_merce += html.text(lstr_dest.dsnazi_sl) + "<br/>";
        }

        String cdagen_m = "";
        String dsagen = "";

        if (!cdagen.equals("")) {

            l_query = "";
            l_query += "  select agen.dsagen                 \n";
            l_query += "       , agen.cdagen_m               \n";
            l_query += "    from pgmr.archagen agen          \n";
            l_query += "   where agen.cdazie = '" + cdazie + "'  \n";
            l_query += "     and agen.cdagen = '" + cdagen + "'  \n";

            pstm = m_connection.prepareStatement(l_query);

            rs = pstm.executeQuery();

            if (rs != null && rs.next()) {

                if (rs.getObject("cdagen_m") != null) {
                    cdagen_m = rs.getString("cdagen_m");
                }
                if (rs.getObject("dsagen") != null) {
                    dsagen = rs.getString("dsagen");
                }
            }
            pstm.close();
        }




        String ragcog = "";
        String cdclie_m = "";
        String abibap = "";
        String cabbap = "";
        String bappef = "";
        String notapagame = "";
        String fgriba = "";



        if (!tkclie.equals("")) {

            l_query = "";
            l_query += "  select ente.ragcog                 \n";
            l_query += "       , clie.cdclie_m               \n";
            l_query += "       , clie.abibap                 \n";
            l_query += "       , clie.cabbap                 \n";
            l_query += "       , clie.bappef                 \n";
            l_query += "       , clie.notapagame             \n";
            l_query += "    from pgmr.archclie clie          \n";
            l_query += "       , pgmr.archenti ente          \n";
            l_query += "   where ente.cdente = clie.cdente   \n";
            l_query += "     and clie.cdazie = '" + cdazie + "'  \n";
            l_query += "     and clie.tkclie = '" + tkclie + "'  \n";

            pstm = m_connection.prepareStatement(l_query);

            rs = pstm.executeQuery();

            if (rs != null && rs.next()) {

                if (rs.getObject("ragcog") != null) {
                    ragcog = rs.getString("ragcog");
                }
                if (rs.getObject("cdclie_m") != null) {
                    cdclie_m = rs.getString("cdclie_m");
                }
                if (rs.getObject("abibap") != null) {
                    abibap = rs.getString("abibap");
                }
                if (rs.getObject("cabbap") != null) {
                    cabbap = rs.getString("cabbap");
                }
                if (rs.getObject("bappef") != null) {
                    bappef = rs.getString("bappef");
                }
                if (rs.getObject("notapagame") != null) {
                    notapagame = rs.getString("notapagame");
                }
            }
            pstm.close();

        }

        //notapagame = "note di prova";

        if (!notapagame.equals("")) {
            fgriba = "N";
        } else {
            fgriba = "S";
        }




        String dsutente = "";

        l_query = "";
        l_query += " SELECT ute.tkutente                \n";
        l_query += "      , ute.dsutente                \n";
        l_query += "      , ute.cdagen                  \n";
        l_query += "      , ute.tkclie                  \n";
        l_query += "      , ute.email                   \n";
        l_query += "   FROM pgmr.cat_utente ute         \n";
        l_query += "  where ute.tkutente = " + tkutente + " \n";

        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        if (rs != null && rs.next()) {

            if (rs.getObject("dsutente") != null) {
                dsutente = rs.getString("dsutente");
            }
            if (rs.getObject("dsutente") != null) {
                dsutente = rs.getString("dsutente");
            }
        }
        pstm.close();

        //EAR - 20110921 - aggiunta riferimento subutente se presente
        String dssubutente = "";

        if (tksubutente > 0) {

            l_query = "";
            l_query += " SELECT ute.tkutente                \n";
            l_query += "      , ute.dsutente                \n";
            l_query += "      , ute.email                   \n";
            l_query += "   FROM pgmr.ep_utente ute         \n";
            l_query += "  where ute.tkutente = " + tksubutente + " \n";

            pstm = m_connection.prepareStatement(l_query);

            rs = pstm.executeQuery();

            if (rs != null && rs.next()) {

                if (rs.getObject("dsutente") != null) {
                    dssubutente = rs.getString("dsutente");
                }
            }
            pstm.close();

        }


        ls_ += "<h4>"+riepilogo_ordine_mail+":</h4>";
        ls_ += "<table width=\"359\" cellpadding=\"0\" cellspacing=\"0\" class=\"parametri\">";
        ls_ += "<tr>";
        ls_ += "<th width=\"130\" align=\"left\" valign=\"top\" nowrap>"+rif_ordine_mail+":</th>";
        ls_ += "<td valign=\"top\">" + cdordi + "</td>";
        ls_ += "</tr>";
        ls_ += "<tr>";
        ls_ += "<th width=\"130\" align=\"left\" valign=\"top\" nowrap>"+data_ordine_mail+":</th>";
        ls_ += "<td valign=\"top\">" + atk_ctrl.getDate(dtordi) + "</td>";
        ls_ += "</tr>";
//        ls_ += "<tr valign=\"top\">";
//        ls_ += "<th nowrap>Data Consegna:</th>";
//        ls_ += "<td nowrap>"+atk_ctrl.getDate(dtcons)+"</td>";
//        ls_ += "</tr>";

        if (!dssubutente.equals("")) {

            ls_ += "<tr>";
            ls_ += "<th width=\"130\" align=\"left\" valign=\"top\" nowrap>"+ordine_inviato_mail+":</th>";
            ls_ += "<td valign=\"top\">" + html.text(dssubutente) + "</td>";
            ls_ += "</tr>";
        }
        if (!cdagen.equals("")) {

            ls_ += "<tr>";
            ls_ += "<th width=\"130\" align=\"left\" valign=\"top\" nowrap>"+agente_ordine_mail+":</th>";
            ls_ += "<td valign=\"top\">" + cdagen_m + " - " + html.text(dsagen) + "</td>";
            ls_ += "</tr>";
        }
        if (!tkclie.equals("")) {

            ls_ += "<tr>";
            ls_ += "<th width=\"130\" align=\"left\" valign=\"top\" nowrap>"+cliente_ordine_mail+":</th>";
            ls_ += "<td valign=\"top\">" + cdclie_m + " - " + html.text(ragcog) + "</td>";
            ls_ += "</tr>";
        }
//        if (!bappef.equals("")){
//
//            ls_ += "<tr>";
//            ls_ += "<th width=\"130\" align=\"left\" valign=\"top\" nowrap>Banca di appoggio:</th>";
//            
//            if (!fgriba.equals("S")){
//                
//                ls_ += "<td valign=\"top\">vedi note pagamento";
//                ls_ += "</td>";
//
//            }else{
//                ls_ += "<td valign=\"top\">"+html.text(bappef);
//                if (!abibap.equals("")){
//                    ls_ += "<br>ABI: "+html.text(abibap);
//                }
//                if (!cabbap.equals("")){
//                    ls_ += "<br>CAB: "+html.text(cabbap);
//                }
//                ls_ += "</td>";
//            }
//            ls_ += "</tr>";
//        }
        if (!ls_dsdest_merce.equals("")) {

            ls_ += "<tr>";
            ls_ += "<th width=\"130\" align=\"left\" valign=\"top\" nowrap>"+destinazione_ordine_mail+":</th>";
            ls_ += "<td valign=\"top\">" + ls_dsdest_merce + "</td>";
            ls_ += "</tr>";
        }
        if (!annotazione_cli.equals("")) {

            ls_ += "<tr>";
            ls_ += "<th width=\"130\" align=\"left\" valign=\"top\" nowrap>Nota per Cliente:</th>";
            ls_ += "<td valign=\"top\">" + html.text(annotazione_cli) + "</td>";
            ls_ += "</tr>";
        }
//        if (!annotazione_int.equals("")){
//
//            ls_ += "<tr valign=\"top\">";
//            ls_ += "<th nowrap>Nota Interna:</th>";
//            ls_ += "<td nowrap style=\"border: 1px solid #ccc;\">"+html.text(annotazione_int)+"</td>";
//            ls_ += "</tr>";
//        }
        if (!cdpromo_m.equals("")) {

            ls_ += "<tr>";
            ls_ += "<th width=\"130\" align=\"left\" valign=\"top\" nowrap>Codice prenotazione:</th>";
            ls_ += "<td valign=\"top\">" + html.text(cdpromo_m) + "</td>";
            ls_ += "</tr>";
        }
        ls_ += "</table>";
        ls_ += "<br/>";

        return ls_;

    }

    /**
     * *
     *
     *
     */
    public ResultSet getPosizione(long tkordi, String cdarti, String fgpacco) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        if (tkordi <= 0) {
            return null;
        }


        if (!fgpacco.equals("S")) {

            l_query = "";
            l_query += " select ordp.tkposi                                            \n";
            l_query += "      , ordp.qtatot                                            \n";
            l_query += "      , ordp.ncolli                                            \n";
            l_query += "      , ordp.dimena                                            \n";
            l_query += "      , ordp.dimenb                                            \n";
            l_query += "      , ordp.dimenc                                            \n";
            l_query += "      , ordp.importonettoriga                                  \n";
            l_query += "      , ordp.nrposi                                            \n";
            l_query += "      , ordp.scont1                                            \n";
            l_query += "      , ordp.scont2                                            \n";
            l_query += "      , ordp.scont3                                            \n";
            l_query += "      , ordp.scont4                                            \n";
            l_query += "      , ordp.impuninetto                                       \n";
            l_query += "      , ordp.fg_annulla_sconti                                 \n";
            l_query += "   from pgmr.web_ord_positito  ordp                            \n";
            l_query += "  where ordp.tkordi = ?                                        \n";
            l_query += "    and ordp.cdarti = ?                                        \n";
            l_query += "    and ordp.tkposi not in( select matr.tkposi                 \n";
            l_query += "                              from pgmr.web_ord_posi_matr matr \n";
            l_query += "                             where matr.tkordi = ?             \n";
            l_query += "                                    )                          \n";
            l_query += "  order by ordp.nrposi                                         \n";

        } else {

            l_query = "";
            l_query += " select ordp.tkposi                         \n";
            l_query += "      , ordp.ncolli                         \n";
            l_query += "      , ordp.qtatot                         \n";
            l_query += "      , ordp.nrposi                         \n";
            l_query += "      , ordp.importonettoriga               \n";
            l_query += "      , ordp.scont1                         \n";
            l_query += "      , ordp.scont2                         \n";
            l_query += "      , ordp.scont3                         \n";
            l_query += "      , ordp.scont4                         \n";
            l_query += "      , ordp.impuninetto                    \n";
            l_query += "      , ordp.fg_annulla_sconti              \n";
            l_query += "      , matr.tkmatricola                    \n";
            l_query += "      , anag.cdmatricola_m                  \n";
            l_query += "      , anag.cdmatrprod                     \n";
            l_query += "      , anag.fpaccodist                     \n";
            l_query += "   from pgmr.web_ord_positito  ordp         \n";
            l_query += "      , pgmr.web_ord_posi_matr matr         \n";
            l_query += "      , pgmr.matr_anagrafica   anag         \n";
            l_query += "  where matr.tkordi = ordp.tkordi           \n";
            l_query += "    and matr.tkposi = ordp.tkposi           \n";
            l_query += "    and matr.tkmatricola = anag.tkmatricola \n";
            l_query += "    and ordp.tkordi = ?                     \n";
            l_query += "    and ordp.cdarti = ?                     \n";
            l_query += " order by ordp.nrposi                       \n";

        }

        pstm = setQuery(l_query);

        ind = 1;
        pstm.setLong(ind++, tkordi);
        pstm.setString(ind++, cdarti);
        if (!fgpacco.equals("S")) {
            pstm.setLong(ind++, tkordi);
        }

        rs = pstm.executeQuery();

        return rs;

    }

    public int setDtcons(long tkordi, Timestamp dtcons) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        if (dtcons == null) {
            dtcons = of_getOggi();
        }

        l_query = "";
        l_query += " update pgmr.web_ord_test \n";
        l_query += "  set dtcons = ?          \n";
        l_query += "  where tkordi = ?        \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;
        pstm.setTimestamp(ind++, dtcons);
        pstm.setLong(ind++, tkordi);

        tot_rec = pstm.executeUpdate();

        pstm.close();

        return tot_rec;

    }

    public long duplicaOrdine(long tkordi_from) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String cdstato = "";

        F_tabkey f_tabkey = new F_tabkey();
        Web_ord_test web_ord_test = new Web_ord_test();

        setProfilo((Atk_sql) f_tabkey);
        setProfilo((Atk_sql) web_ord_test);

        Rec_web_ord_test lstr = new Rec_web_ord_test();


        l_query = "";
        l_query += " select *             \n";
        l_query += "   from pgmr.web_ord_test   \n";
        l_query += "  where tkordi = " + tkordi_from + " \n";

        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        if (rs != null && rs.next()) {

            lstr.setResultSet(rs);

            web_ord_test.ib_calcola_token = false;

            lstr.tkordi = f_tabkey.getTabkey("web_ord_test");


            if (lstr.tkordi < 10) {
                lstr.cdordi = "0000" + lstr.tkordi;
            } else if (lstr.tkordi < 100) {
                lstr.cdordi = "000" + lstr.tkordi;
            } else if (lstr.tkordi < 1000) {
                lstr.cdordi = "00" + lstr.tkordi;
            } else if (lstr.tkordi < 10000) {
                lstr.cdordi = "0" + lstr.tkordi;
            } else {
                lstr.cdordi = "" + lstr.tkordi;
            }

            lstr.cdordi = lstr.cdstato + "/" + lstr.cdordi;


            tot_rec = web_ord_test.execute(lstr);

            if (tot_rec <= 0) {
                throw new Exception("Errore INS web_ord_test - duplica!");
            }
        }

        return lstr.tkordi;

    }
    // Vedi Web_ord_test_note
    public String TIPONOTA_INTERNA = "INTERNA";
    public String TIPONOTA_CLIENTE = "CLIENTE";
    
    /*
     * MPERUZZA 20121130
     * lettura file properties
     * 
     */
    
    public static String getPropertiesString(String pKey) {
        String myReturn="";
        Properties props = new Properties();

        try {
            props.load(new FileInputStream(fileProperties));
            myReturn = props.getProperty(pKey);
        } catch(IOException e) {
              // utility.showSimpleMessage("ERRORE NELLA LETTURA DEL FILE CONFIG.PRPERTIES NELLA CHIAVE: " + pKey + "\nERRORE: " + e.getMessage());
        }
        return myReturn ;

    }
}
