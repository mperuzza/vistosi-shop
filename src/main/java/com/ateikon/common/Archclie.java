package com.ateikon.common;

import com.ateikon.function.F_utente;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

public class Archclie extends Atk_sql {

    public Archclie() {

        super();
    }

    public ResultSet getCliente(String tkclie) throws Exception {

        return getKey(tkclie, "");
    }

    public ResultSet getKey_m(String cdclie_m) throws Exception {

        return getKey("", cdclie_m);
    }

    public ResultSet getKey(String tkclie, String cdclie_m) throws Exception {
        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        l_query = "";
        l_query += " select a.cdclie_m           \n";
        l_query += "      , a.tkclie             \n";
        l_query += "      , a.dsclie             \n";
        l_query += "      , a.cdlist             \n";
        l_query += "      , a.cdente             \n";
        l_query += "      , a.cdpagame           \n";
        l_query += "      , a.sconto1            \n";
        l_query += "      , a.sconto2            \n";
        l_query += "      , a.sconto3            \n";
        l_query += "      , a.sconto4            \n";
        l_query += "      , a.scrap1             \n";
        l_query += "      , a.scrap2             \n";
        l_query += "      , a.scocas             \n";
        l_query += "      , a.pgcodi             \n";
        l_query += "      , a.dpcodi             \n";
        l_query += "      , a.cddpag             \n";
        l_query += "      , a.ffinme             \n";
        l_query += "      , a.cdiva              \n";
        l_query += "      , a.cdagen             \n";
        l_query += "      , a.fnoord             \n";
        l_query += "      , a.cdcllist           \n";
        l_query += "      , b.cdente_m           \n";
        l_query += "      , b.ragcog             \n";
        l_query += "      , b.pariva             \n";
        l_query += "      , b.cdfisc             \n";
        l_query += "      , b.ragnom             \n";
        l_query += "      , b.cognom             \n";
        l_query += "      , b.sesso              \n";
        l_query += "      , mpag.dspagame        \n";
        l_query += "      , agen.dsagen          \n";
        l_query += "      , agen.cdagen_m        \n";
        l_query += "      , rmpo.dsrmpo          \n";
        l_query += "   from pgmr.archclie a \n";
        l_query += "             left outer join pgmr.mac_pagame mpag on  a.cdpagame = mpag.cdpagame   \n";
        l_query += "             left outer join pgmr.archagen   agen on  a.cdagen   = agen.cdagen     \n";
        l_query += "                                                  and a.cdazie   = agen.cdazie     \n";
        l_query += "             left outer join pgmr.resmport   rmpo on  a.cdrmpo   = rmpo.cdrmpo     \n";
        l_query += "                             \n";
        l_query += "      , pgmr.archenti b      \n";
        l_query += "  where a.cdente = b.cdente  \n";
        if (!cdclie_m.equals("")) {
            l_query += "    and a.cdclie_m = ?         \n";
        } else {
            l_query += "    and a.tkclie = ?         \n";
        }

        pstm = setQuery(l_query);

        ind = 1;
        if (!cdclie_m.equals("")) {
            pstm.setString(ind, cdclie_m);
            ind += 1;
        } else {
            pstm.setString(ind, tkclie);
            ind += 1;
        }

        rs = pstm.executeQuery();

        return rs;


    }

    public ResultSet search(String f_search, String cdagen, String cdclie_m, String pariva, boolean is_count, String order_by) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        if (order_by.equals("")) {
            order_by = "ente.ragcog";
        }


        f_search = par_like(f_search);
        pariva = par_like(pariva);

        if (is_count) {

            l_query = "";
            l_query += " select count(1) \n";

            order_by = "";
        } else {
            l_query = "";
            l_query += " select clie.cdclie_m                                                             \n";
            l_query += "      , clie.dsclie                                                               \n";
            l_query += "      , clie.cdlist                                                               \n";
            l_query += "      , clie.cdente                                                               \n";
            l_query += "      , clie.cdpagame                                                             \n";
            l_query += "      , clie.tkclie                                                               \n";
            l_query += "      , clie.fnoord                                                               \n";
            l_query += "      , clie.cdcllist                                                             \n";
            l_query += "      , ente.cdente_m                                                             \n";
            l_query += "      , ente.ragcog                                                               \n";
            l_query += "      , ente.pariva                                                               \n";
            l_query += "      , ente.cdfisc                                                               \n";
            l_query += "      , ente.ragnom                                                               \n";
            l_query += "      , ente.cognom                                                               \n";
            l_query += "      , ente.sesso                                                                \n";
            l_query += "      , unil.ntelef                                                               \n";
            l_query += "      , unil.nfaxsi                                                               \n";
            l_query += "      , unil.email                                                                \n";
            l_query += "      , unil.comune                                                               \n";
            l_query += "      , prov.cdprov_m                                                             \n";
            l_query += "      , enul.fseleg                                                               \n";
            l_query += "      , unil.indiri                                                               \n";
            l_query += "      , agen.cdagen_m                                                             \n";
            l_query += "      , agen.dsagen                                                               \n";

        }

        l_query += "   from {oj pgmr.archclie clie                                                    \n";
        l_query += "            LEFT OUTER JOIN  pgmr.archagen  agen on clie.cdagen = agen.cdagen}    \n";
        l_query += "      , pgmr.archenti ente                                                        \n";
        l_query += "      , pgmr.enteuniloc enul                                                      \n";
        l_query += "      , {oj pgmr.unitalocali unil                                                 \n";
        l_query += "            LEFT OUTER JOIN  pgmr.province prov on unil.cdprov = prov.cdprov}     \n";
        l_query += "  where clie.cdente = ente.cdente                                                 \n";
        l_query += "    and enul.cdente = ente.cdente                                                 \n";
        l_query += "    and enul.fseleg = 'S'                                                         \n";
        l_query += "    and unil.cdunil = enul.cdunil                                                 \n";

        if (!s_cdcapoarea.equals("")) {
            l_query += "  and clie.cdagen in (select iagen.cdagen from pgmr.archagen iagen where iagen.cdcapo = '" + s_cdcapoarea + "')  \n";
        }
        if (!s_cdagen.equals("")) {
            l_query += "  and clie.cdagen = '" + s_cdagen + "'  \n";
        }
        if (!s_tkclie.equals("")) {
            l_query += "  and clie.tkclie= '" + s_tkclie + "'  \n";
        }


        if (!cdagen.equals("")) {
            l_query += "  and clie.cdagen = '" + cdagen + "'  \n";
        }
        if (!cdclie_m.equals("")) {
            l_query += "  and clie.cdclie_m = '" + cdclie_m + "'  \n";

        }
        if (!f_search.equals("")) {

            l_query += "  and (  ente.ragcog like '" + f_search + "'  \n";
            l_query += "      or clie.cdclie_m like '" + f_search + "'  \n";
            l_query += "       ) \n";

        }
        if (!pariva.equals("")) {
            l_query += "  and ente.pariva like '" + pariva + "'  \n";
        }


        if (!order_by.equals("")) {
            l_query += "  order by " + order_by + "  \n";
        }



        pstm = setQuery_ric(l_query);


        ind = 1;

        rs = pstm.executeQuery();

        return rs;
    }

    public ResultSet getPariva(String pariva) throws Exception {
        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        l_query = "";
        l_query += " select a.tkclie             \n";
        l_query += "      , a.cdclie_m           \n";
        l_query += "      , a.dsclie             \n";
        l_query += "      , a.cdlist             \n";
        l_query += "      , a.cdente             \n";
        l_query += "      , a.cdpagame           \n";
        l_query += "      , a.sconto1            \n";
        l_query += "      , a.sconto2            \n";
        l_query += "      , a.sconto3            \n";
        l_query += "      , a.sconto4            \n";
        l_query += "      , a.scrap1             \n";
        l_query += "      , a.scrap2             \n";
        l_query += "      , a.scocas             \n";
        l_query += "      , a.pgcodi             \n";
        l_query += "      , a.dpcodi             \n";
        l_query += "      , a.cddpag             \n";
        l_query += "      , a.ffinme             \n";
        l_query += "      , a.cdiva              \n";
        l_query += "      , a.cdcllist           \n";
        l_query += "      , b.cdente_m           \n";
        l_query += "      , b.ragcog             \n";
        l_query += "      , b.pariva             \n";
        l_query += "      , b.cdfisc             \n";
        l_query += "      , b.ragnom             \n";
        l_query += "      , b.cognom             \n";
        l_query += "      , b.sesso              \n";
        l_query += "   from pgmr.archclie a  \n";
        l_query += "      , pgmr.archenti b  \n";
        l_query += "  where a.cdente = b.cdente  \n";
        l_query += "    and b.pariva = ?         \n";
        l_query += "    and a.cdazie = ?         \n";

        pstm = setQuery(l_query);

        ind = 1;
        pstm.setString(ind, pariva);
        ind += 1;
        pstm.setString(ind, cdazie);
        ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }

    public String getDescr_m(String cdclie_m) throws Exception {

        return getDescr("", cdclie_m);

    }

    public String getDescr(String tkclie) throws Exception {

        return getDescr(tkclie, "");

    }

    public String getDescr(String tkclie, String cdclie_m) throws Exception {
        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;

        String dsdivisione = "";

        l_query = "";
        l_query += " select b.ragcog                        \n";
        l_query += "   from pgmr.archclie a                 \n";
        l_query += "      , pgmr.archenti b                 \n";
        l_query += "  where a.cdente = b.cdente             \n";
        if (!tkclie.equals("")) {
            l_query += "  where a.tkclie = '" + tkclie + "'     \n";
        } else {
            l_query += "  where a.cdclie_m = '" + cdclie_m + "' \n";
        }

        String ls_ = sql_String(l_query);

        return ls_;


    }

    /**
     * *
     *
     *
     */
    public String getFnoord(String tkclie) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;


        tot_rec = 0;


        String fnoord = "";
        String cdlist = "";


        l_query = "";
        l_query += " select fnoord                   \n";
        l_query += "      , cdlist                   \n";
        l_query += "   from pgmr.archclie            \n";
        l_query += "  where tkclie = '" + tkclie + "'    \n";
        l_query += "    and cdazie = '" + cdazie + "'    \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;

        rs = pstm.executeQuery();

        if (rs != null && rs.next()) {

            tot_rec += 1;

            if (rs.getObject("fnoord") != null) {
                fnoord = rs.getString("fnoord");
            }
            if (rs.getObject("cdlist") != null) {
                cdlist = rs.getString("cdlist");
            }

        }
        pstm.close();


        if (fnoord.equals("S")) {

            return fnoord;
        }

        if (cdlist.equals("")) {

            return "S";
        }



        return "N";

    }

    /**
     * *
     *
     *
     */
    public ResultSet getUnitalocali(String tkclie, String fseleg) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        tot_rec = 0;

        l_query = "";
        l_query += " select uloc.cdunil_m                                         \n";
        l_query += "      , uloc.indiri                                           \n";
        l_query += "      , uloc.indiri                                           \n";
        l_query += "      , uloc.cap                                              \n";
        l_query += "      , uloc.comune                                           \n";
        l_query += "      , uloc.cdprov                                           \n";
        l_query += "      , prov.cdprov_m                                         \n";
        l_query += "      , prov.dsprov                                           \n";
        l_query += "      , uloc.cdnazi                                           \n";
        l_query += "      , nazi.dsnazi                                           \n";
        l_query += "      , uloc.email                                            \n";
        l_query += "      , uloc.wwwpage                                          \n";
        l_query += "      , uloc.ntelef                                           \n";
        l_query += "      , uloc.nfaxsi                                           \n";
        l_query += "      , enul.fseleg                                           \n";
        l_query += "      , uloc.cdagen                                           \n";
        l_query += "      , agen.cdagen_m                                         \n";
        l_query += "      , agen.dsagen                                           \n";
        l_query += "      , (select max (iusul.dsusul)                            \n";
        l_query += "           from pgmr.enteuniloc        ienul                  \n";
        l_query += "              , pgmr.usiuniloc         iusul                  \n";
        l_query += "          where ienul.cdunil = uloc.cdunil                    \n";
        l_query += "            and ienul.cdusul = iusul.cdusul     ) as dsusul   \n";
        l_query += "   from {oj pgmr.archclie clie                                                      \n";
        l_query += "            LEFT OUTER JOIN  pgmr.archagen      agen on clie.cdagen = agen.cdagen   \n";
        l_query += "                                                     and clie.cdazie = agen.cdazie  \n";
        l_query += "            LEFT OUTER JOIN  pgmr.archenti      ente on clie.cdente = ente.cdente   \n";
        l_query += "            LEFT OUTER JOIN  pgmr.enteuniloc    enul on ente.cdente = enul.cdente   \n";
        l_query += "            LEFT OUTER JOIN  pgmr.unitalocali   uloc on enul.cdunil = uloc.cdunil   \n";
        l_query += "            LEFT OUTER JOIN  pgmr.province      prov on uloc.cdprov = prov.cdprov   \n";
        l_query += "            LEFT OUTER JOIN  pgmr.nazioni       nazi on uloc.cdnazi = nazi.cdnazi   \n";
        l_query += "                }                       \n";
        l_query += "  where clie.cdazie = ?                 \n";
        l_query += "    and clie.tkclie = ?                 \n";

        if (!s_cdagen.equals("")) {
            l_query += "    and clie.cdagen = '" + s_cdagen + "'     \n";
        }
        if (!fseleg.equals("")) {
            l_query += "    and enul.fseleg = ?                  \n";
        }
        l_query += "  order by enul.fseleg desc              \n";
        l_query += "         , uloc.cdunil_m                 \n";

        pstm = setQuery(l_query);

        ind = 1;
        pstm.setString(ind++, cdazie);
        pstm.setString(ind++, tkclie);
        if (!fseleg.equals("")) {
            pstm.setString(ind++, fseleg);
        }

        rs = pstm.executeQuery();

        return rs;

    }

    public String of_getEmail(String tkclie, String cdfunzione_m, String cdutente_tpservizio_m) throws Exception {

        return (String) of_getAttributi("email", tkclie, cdfunzione_m, cdutente_tpservizio_m);
    }

    public String of_getLingua(String tkclie, String cdfunzione_m, String cdutente_tpservizio_m) throws Exception {

        return (String) of_getAttributi("cdling", tkclie, cdfunzione_m, cdutente_tpservizio_m);
    }

    public String of_getUser_name(String tkclie) throws Exception {

        return (String) of_getAttributi("user_name", tkclie, "", "");
    }

    /**
     * Metodo che mi permette di recuperare degli attributi del cliente secondo
     * le logiche specifiche del cliente
     *
     * @param tpattributo
     * @param tkclie
     * @param cdfunzione_m
     * @param cdutente_tpservizio_m
     * @return
     * @throws Exception
     */
    public Object of_getAttributi(String tpattributo, String tkclie, String cdfunzione_m, String cdutente_tpservizio_m) throws Exception {
        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        F_utente f_utente = new F_utente();

        setProfilo((Atk_sql) f_utente);

        if (!tpattributo.equals("cdling")
                && !tpattributo.equals("email")
                && !tpattributo.equals("user_name")) {
            throw new Exception("Attributo non gestito");
        }

        Object lo_ = null;

        String ls_cdling = "";
        String ls_email = "";
        String ls_user_name = "";


        /**
         * Recupero attributi da cliente
         */
        String ls_clie_cdling = "";
        String ls_clie_email = "";


        l_query = "";
        l_query += " select clie.cdling                                 \n";
        l_query += "      , uloc.email                                  \n";
        l_query += "   from pgmr.archclie       clie                    \n";
        l_query += "      , pgmr.archenti       ente                    \n";
        l_query += "      , pgmr.enteuniloc     eulo                    \n";
        l_query += "      , pgmr.unitalocali    uloc                    \n";
        l_query += "  where clie.cdazie = '" + cdazie + "'                \n";
        l_query += "    and clie.tkclie = '" + tkclie + "'                \n";
        l_query += "    and clie.cdente     = ente.cdente               \n";
        l_query += "    and ente.cdente     = eulo.cdente               \n";
        l_query += "    and eulo.cdunil     = uloc.cdunil               \n";
        l_query += "    and eulo.fseleg     = 'S'                       \n";

        rs = sql_query(l_query);

        if (rs != null && rs.next()) {
            ls_clie_cdling = "";
            ls_clie_email = "";

            if (rs.getObject("cdling") != null) {
                ls_clie_cdling = rs.getString("cdling");
            }
            if (rs.getObject("email") != null) {
                ls_clie_email = rs.getString("email");
            }

        }

        /**
         * Recupero attributi da utente
         */
        long ll_eute_tkutente = 0;
        String ls_eute_cdling = "";
        String ls_eute_email = "";
        String ls_eute_user_name = "";


        l_query = "";
        l_query += " select eute.tkutente                               \n";
        l_query += "      , eute.cdling                                 \n";
        l_query += "      , eute.email                                  \n";
        l_query += "      , eute.user_name                              \n";
        l_query += "   from pgmr.ep_utente      eute                    \n";
        l_query += "  where eute.cdazie = '" + cdazie + "'                \n";
        l_query += "    and eute.tkclie = '" + tkclie + "'                \n";

        rs = sql_query(l_query);

        if (rs != null && rs.next()) {
            ll_eute_tkutente = 0;
            ls_eute_cdling = "";
            ls_eute_email = "";
            ls_eute_user_name = "";

            if (rs.getObject("tkutente") != null) {
                ll_eute_tkutente = rs.getLong("tkutente");
            }
            if (rs.getObject("cdling") != null) {
                ls_eute_cdling = rs.getString("cdling");
            }
            if (rs.getObject("email") != null) {
                ls_eute_email = rs.getString("email");
            }
            if (rs.getObject("user_name") != null) {
                ls_eute_user_name = rs.getString("user_name");
            }

            if (!cdfunzione_m.equals("")) {
                ls_eute_email = f_utente.getEmailFunzione(ll_eute_tkutente, cdfunzione_m);
            } else if (!cdutente_tpservizio_m.equals("")) {
                ls_eute_email = f_utente.getEmailTiposervizio(ll_eute_tkutente, cdutente_tpservizio_m);
            }

        }

        if (ll_eute_tkutente > 0) {
            ls_cdling = ls_eute_cdling;
            ls_email = ls_eute_email;
        } else {
            ls_cdling = ls_clie_cdling;
            ls_email = ls_clie_email;
        }
        ls_user_name = ls_eute_user_name;


        if (tpattributo.equals("cdling")) {
            lo_ = ls_cdling;
        }
        if (tpattributo.equals("email")) {
            lo_ = ls_email;
        }
        if (tpattributo.equals("user_name")) {
            lo_ = ls_user_name;
        }

        f_utente.close();

        return lo_;
    }
}
