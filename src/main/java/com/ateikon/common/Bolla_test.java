package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

public class Bolla_test extends Atk_sql {

    public Bolla_test() {

        super();
    }

    public ResultSet getKey(long tkboll) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        // verifico se � una bolla o una fattura

        tot_rec = 0;

        l_query = "";
        l_query += "   select tkfatt               \n";
        l_query += "     from pgmr.bolla_test      \n";
        l_query += "    where tkboll = " + tkboll + "  \n";

        long tkfatt = sql_long(l_query);

        String tipodoc = "B";
        if (tkfatt > 0 && tkfatt == tkboll) {
            tipodoc = "F";
        }


        l_query = "";
        l_query += "  SELECT bolt.tkboll                       \n";
        l_query += "       , bolt.cdtdoc                      \n";
        l_query += "       , bolt.dtboll                       \n";
        l_query += "       , bolt.cdagen                       \n";
        l_query += "       , bolt.cdazie                       \n";
        l_query += "       , bolt.cdstato                      \n";
        l_query += "       , bolt.cdpagame                     \n";
        l_query += "       , clie.cdclie_m                     \n";
        l_query += "       , ente.ragcog                       \n";
        l_query += "       , uloc_dm.indiri  as indiri_dm      \n";
        l_query += "       , uloc_dm.cap     as cap_dm         \n";
        l_query += "       , uloc_dm.comune  as comune_dm      \n";
        l_query += "       , uloc_dm.cdprov  as cdprov_dm      \n";
        l_query += "       , uloc_dm.ntelef  as ntelef_dm      \n";
        l_query += "       , uloc_dm.nfaxsi  as nfaxsi_dm      \n";
        l_query += "       , provdm.cdprov_m  as cdprov_m_dm   \n";
        l_query += "       , uloc_sl.indiri  as indiri_sl      \n";
        l_query += "       , uloc_sl.cap     as cap_sl         \n";
        l_query += "       , uloc_sl.comune  as comune_sl      \n";
        l_query += "       , uloc_sl.cdprov  as cdprov_sl      \n";
        l_query += "       , uloc_sl.ntelef  as ntelef_sl      \n";
        l_query += "       , uloc_sl.nfaxsi  as nfaxsi_sl      \n";
        l_query += "       , provsl.cdprov_m  as cdprov_m_sl   \n";
        l_query += "       , bolt.ragcog_de                    \n";
        l_query += "       , bolt.indiri_de                    \n";
        l_query += "       , bolt.cap_de                       \n";
        l_query += "       , bolt.comune_de                    \n";
        l_query += "       , bolt.cdprov_de                    \n";
        l_query += "       , provde.cdprov_m  as cdprov_m_de   \n";
        l_query += "       , bolt.tkclie                       \n";
        l_query += "       , bolt.cdboll                       \n";
        l_query += "       , clie.cdclie_m                     \n";
        l_query += "       , clie.cliacr                       \n";
        l_query += "       , clie.fnospes                      \n";
        l_query += "       , tdoc.cdtdoc_m                     \n";
        l_query += "       , tdoc.dstdoc                       \n";
        l_query += "       , forp.pgdesc                       \n";
        l_query += "       , dilp.dpdesc                       \n";
        l_query += "       , decp.dsdpag                       \n";
        l_query += "       , bolt.ffinme                       \n";
        l_query += "       , agen.cdagen                       \n";
        l_query += "       , agen.cdagen_m                     \n";
        l_query += "       , agen.dsagen                       \n";
        l_query += "       , bolt.tkfatt                       \n";
        l_query += "       , bolt.conpro                       \n";
        l_query += "       , bolt.dtprot                       \n";
        l_query += "   FROM pgmr.bolla_test         bolt                                                   \n";
        l_query += "        left outer join pgmr.tipo_documento   tdoc     on bolt.cdtdoc   = tdoc.cdtdoc      \n";
        l_query += "        left outer join pgmr.archclie         clie     on bolt.tkclie    = clie.tkclie     \n";
        l_query += "                                                      and bolt.cdazie    = clie.cdazie     \n";
        l_query += "        left outer join pgmr.archenti         ente     on bolt.cdente    = ente.cdente     \n";
        l_query += "        left outer join pgmr.archagen         agen     on clie.cdagen    = agen.cdagen     \n";
        l_query += "                                                      and clie.cdazie    = agen.cdazie     \n";
        l_query += "        left outer join  pgmr.unitalocali     uloc_dm  on bolt.cduldm    =  uloc_dm.cdunil \n";
        l_query += "        left outer join  pgmr.enteuniloc      enul     on bolt.cdente    =  enul.cdente    \n";
        l_query += "                                                      and 'S' = enul.fseleg                \n";
        l_query += "        left outer join  pgmr.unitalocali     uloc_sl  on enul.cdunil    =  uloc_sl.cdunil \n";
        l_query += "        left outer join  pgmr.province        provdm   on uloc_dm.cdprov =  provdm.cdprov  \n";
        l_query += "        left outer join  pgmr.province        provsl   on uloc_sl.cdprov =  provsl.cdprov  \n";
        l_query += "        left outer join  pgmr.province        provde   on bolt.cdprov_de =  provde.cdprov  \n";
        l_query += "        left outer join  pgmr.formpaga        forp     on bolt.pgcodi    =  forp.pgcodi    \n";
        l_query += "        left outer join  pgmr.dilapaga        dilp     on bolt.dpcodi    =  dilp.dpcodi    \n";
        l_query += "        left outer join  pgmr.decorpag        decp     on bolt.cddpag    =  decp.cddpag    \n";
        l_query += "   WHERE bolt.tkboll = ? \n";
        if (!s_cdcapoarea.equals("")) {
            l_query += " and clie.cdagen in (" + par_ca_agenti(s_cdcapoarea) + ") \n";
        } else if (!s_cdagen.equals("")) {
            l_query += " and clie.cdagen = '" + s_cdagen + "' \n";
        } else if (!s_tkclie.equals("")) {
            l_query += " and clie.tkclie = '" + s_tkclie + "' \n";
            l_query += " and clie.cdazie = '" + cdazie + "' \n";
        }

        pstm = setQuery(l_query);

        ind = 1;
        pstm.setLong(ind, tkboll);
        ind += 1;

        rs = pstm.executeQuery();


        return rs;





    }

    public ResultSet getKey_m(String cdboll) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;


        l_query = "";
        l_query += "  SELECT bolt.tkboll                 \n";
        l_query += "    FROM pgmr.bolla_test   bolt      \n";
        l_query += "   WHERE bolt.cdboll = '" + cdboll + "'  \n";
        l_query += "     and bolt.cdazie = '" + cdazie + "'  \n";

        long tkboll = sql_long(l_query);

        return getKey(tkboll);

    }

    /**
     * *
     *
     */
    public ResultSet search(com.ateikon.structure.Str_controllo_docven astr, boolean is_count, String order_by) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;


        if (!s_tkclie.equals("")) {
            astr.tkclie = s_tkclie;
        }

        // imposto le costanti
        astr.ctrlTipodoc();

        if (astr.tipodoc.equals(astr.TIPODOC_BOLL)) {
            astr.nrdocu = par_like(astr.nrdocu);
        }


        if (order_by.equals("")) {
            order_by = "bolt." + astr.dtname + " desc, bolt." + astr.nrname + " ";
        }
        if (is_count) {
            order_by = "";
        }

        if (is_count) {

            l_query = "";
            l_query += "   select count(1)  \n";
            l_query += "        , sum( ( select sum(ibolp.importonettoriga)               \n";
            l_query += "                   from pgmr.bolla_posi ibolp                     \n";
            l_query += "                  where ibolp." + astr.joinp + " = bolt.tkboll        \n";
            if (!astr.cdarti.equals("")) {
                l_query += "                  and ibolp.cdarti = '" + astr.cdarti + "'            \n";
            }
            l_query += "                                      ) ) as tot_importonettoriga \n";

            l_query += "        , sum( ( select sum(ibolp.nrquan)                         \n";
            l_query += "                   from pgmr.bolla_posi ibolp                     \n";
            l_query += "                  where ibolp." + astr.joinp + " = bolt.tkboll        \n";
            if (!astr.cdarti.equals("")) {
                l_query += "                  and ibolp.cdarti = '" + astr.cdarti + "'            \n";
            }
            l_query += "                                      ) ) as tot_nrquan           \n";


        } else {


            l_query = "";
            l_query += "  SELECT bolt.cdazie                                       \n";
            l_query += "       , bolt.tkclie                                       \n";
            l_query += "       , clie.cdclie_m                                     \n";
            l_query += "       , ente.ragcog                                       \n";
            l_query += "       , bolt." + astr.dtname + "                              \n";
            l_query += "       , bolt." + astr.nrname + "                              \n";
            l_query += "       , bolt.tkboll                                       \n";
            l_query += "       , bolt.tkfatt                                       \n";
            l_query += "       , bolt.cdpagame                                     \n";
            l_query += "       , agen.cdagen                                       \n";
            l_query += "       , agen.cdagen_m                                     \n";
            l_query += "       , agen.dsagen                                       \n";
            l_query += "       , (select sum(ibolp.importonettoriga)               \n";
            l_query += "            from pgmr.bolla_posi ibolp                     \n";
            l_query += "           where ibolp." + astr.joinp + " = bolt.tkboll        \n";
            if (!astr.cdarti.equals("")) {
                l_query += "                  and ibolp.cdarti = '" + astr.cdarti + "'     \n";
            }
            l_query += "                                             ) as importonettoriga \n";
            l_query += "       , (select sum(ibolp.nrquan)                                 \n";
            l_query += "            from pgmr.bolla_posi ibolp                             \n";
            l_query += "           where ibolp." + astr.joinp + " = bolt.tkboll                \n";
            if (!astr.cdarti.equals("")) {
                l_query += "                  and ibolp.cdarti = '" + astr.cdarti + "'             \n";
            }
            l_query += "                                             ) as nrquan           \n";

        }   // FINE    if (is_count){



        l_query += "    FROM pgmr.bolla_test         bolt  \n";
        l_query += "       , pgmr.tipo_documento     tdoc  \n";
        l_query += "       , pgmr.archenti           ente  \n";
        l_query += "       , pgmr.archclie       clie  \n";
        l_query += "         left outer join pgmr.archagen agen on clie.cdagen = agen.cdagen \n";
        if (astr.tipodoc.equals(astr.TIPODOC_BOLL)) {
            l_query += "   WHERE (bolt.tkfatt is null or bolt.tkfatt <> bolt.tkboll) \n";
        } else {
            l_query += "   WHERE bolt.tkfatt   = bolt.tkboll           \n";
        }
        l_query += "     and bolt.tkclie   = clie.tkclie           \n";
        l_query += "     and bolt.cdazie   = clie.cdazie           \n";
        l_query += "     and bolt.cdtdoc   = tdoc.cdtdoc           \n";
        l_query += "     and clie.cdente   = ente.cdente           \n";
        l_query += "     and bolt.cdazie   = '" + cdazie + "'          \n";
        l_query += "     and tdoc.cdclas   = 'V'                   \n";

        if (!s_cdcapoarea.equals("")) {
            l_query += "     and clie.cdagen in (" + par_ca_agenti(s_cdcapoarea) + ") \n";
        } else if (!s_cdagen.equals("")) {
            l_query += "     and clie.cdagen = '" + s_cdagen + "' \n";
        }
        if (!astr.cdagen.equals("")) {
            l_query += "     and clie.cdagen = '" + astr.cdagen + "' \n";
        }

        if (astr.tkposi > 0) {
            l_query += "     and 0 < ( select count(ibolp.tkposi_b)              \n";
            l_query += "                 from pgmr.bolla_posi ibolp              \n";
            l_query += "                where ibolp.tkposi = " + astr.tkposi + "     \n";
            l_query += "                  and ibolp." + astr.joinp + " = bolt.tkboll   \n";
            l_query += "                  )                                      \n";
        }
        if (!astr.cdarti.equals("")) {
            l_query += "     and 0 < ( select count(ibolp.tkposi_b)            \n";
            l_query += "                 from pgmr.bolla_posi ibolp            \n";
            l_query += "                where ibolp.cdarti = '" + astr.cdarti + "' \n";
            l_query += "                  and ibolp." + astr.joinp + " = bolt.tkboll \n";
            l_query += "                  )                                    \n";

        }
        if (astr.dtdocu_da != null) {
            l_query += " and bolt." + astr.dtname + " >= ?  \n";
        }
        if (astr.dtdocu_a != null) {
            l_query += " and bolt." + astr.dtname + " <= ?  \n";
        }
        if (!astr.tkclie.equals("")) {
            l_query += " and clie.tkclie = '" + astr.tkclie + "'        \n";
        }
        if (!astr.nrdocu.equals("")) {
            if (astr.tipodoc.equals(astr.TIPODOC_BOLL)) {
                l_query += " and bolt.cdboll like '" + astr.nrdocu + "'        \n";
            } else {
                l_query += " and bolt.conpro = " + astr.nrdocu + "        \n";
            }
        }
        if (astr.anno > 0) {
            l_query += " and year(bolt." + astr.dtname + ") = " + astr.anno + "  \n";
        }
        if (!order_by.equals("")) {
            l_query += " order by " + order_by + " \n";
        }

        pstm = setQuery_ric(l_query);

        ind = 1;
        if (astr.dtdocu_da != null) {
            pstm.setTimestamp(ind++, astr.dtdocu_da);
        }
        if (astr.dtdocu_a != null) {
            pstm.setTimestamp(ind++, astr.dtdocu_a);
        }

        rs = pstm.executeQuery();


        return rs;

    }

    /**
     * *
     *
     */
    public ResultSet search_posi(com.ateikon.structure.Str_controllo_docven astr, boolean is_count, String order_by) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        if (!s_tkclie.equals("")) {
            astr.tkclie = s_tkclie;
        }


        // imposto le costanti
        astr.ctrlTipodoc();


        if (astr.tipodoc.equals(astr.TIPODOC_BOLL)) {
            astr.nrdocu = par_like(astr.nrdocu);
        }

        if (order_by.equals("")) {
            order_by = "bolt." + astr.dtname + " desc, bolt." + astr.nrname + ", bolp." + astr.rgname + " ";
        }
        if (is_count) {
            order_by = "";
        }

        if (is_count) {

            l_query = "";
            l_query += "   select count(1)                                             \n";
            l_query += "        , sum(bolp.nrquan )            as tot_nrquan           \n";
            l_query += "        , sum(bolp.importonettoriga)   as tot_importonettoriga \n";

        } else {


            l_query = "";
            l_query += "  SELECT bolt.cdazie              \n";
            l_query += "       , bolt.tkclie              \n";
            l_query += "       , clie.cdclie_m            \n";
            l_query += "       , agen.cdagen              \n";
            l_query += "       , agen.cdagen_m            \n";
            l_query += "       , agen.dsagen              \n";
            l_query += "       , ente.ragcog              \n";
            l_query += "       , bolt." + astr.dtname + "       \n";
            l_query += "       , bolt." + astr.nrname + "       \n";
            l_query += "       , bolt.tkboll              \n";
            l_query += "       , bolt.tkfatt              \n";
            l_query += "       , bolp.tkposi_b            \n";
            l_query += "       , bolp." + astr.rgname + "       \n";
            l_query += "       , bolp.cdarti              \n";
            l_query += "       , arti.cdartm              \n";
            l_query += "       , bolp.dssart              \n";
            l_query += "       , bolp.nrquan              \n";
            l_query += "       , bolp.coimpo              \n";
            l_query += "       , bolp.impuninetto         \n";
            l_query += "       , bolp.importonettoriga    \n";
            l_query += "       , bolp.sconto_1            \n";
            l_query += "       , bolp.sconto_2            \n";
            l_query += "       , bolp.sconto_3            \n";
            l_query += "       , bolp.sconto_4            \n";
            l_query += "       , unim.cdunim_m            \n";
            l_query += "       , zcom.cdzcom              \n";
            l_query += "       , zcom.cdzcom_m            \n";
            l_query += "       , zcom.dszcom              \n";
            l_query += "       , tipm.cdtipm              \n";
            l_query += "       , tipm.cdtipm_m            \n";
            l_query += "       , tipm.dstipm              \n";
            l_query += "       , artip.cdartipo           \n";
            l_query += "       , artip.cdartipo_m         \n";
            l_query += "       , artip.dsartipo           \n";
            l_query += "       , arcla.cdclas_a           \n";
            l_query += "       , arcla.cdclas_a_m         \n";
            l_query += "       , arcla.dsclas_a           \n";


        }   // FINE    if (is_count){



        l_query += "     FROM pgmr.bolla_test        bolt          \n";
        l_query += "        , pgmr.bolla_posi        bolp          \n";
        l_query += "        , pgmr.tipo_documento    tdoc          \n";
        l_query += "        , pgmr.archenti          ente          \n";
        l_query += "        , pgmr.archclie      clie            \n";
        l_query += "          left outer join pgmr.archagen agen on clie.cdagen = agen.cdagen \n";
        l_query += "          left outer join pgmr.zonacomm zcom on clie.cdzcom = zcom.cdzcom \n";
        l_query += "                                            and clie.cdazie = zcom.cdazie \n";
        l_query += "        , pgmr.mrp_arch_articoli arti      \n";
        l_query += "          left outer join pgmr.unimisura         unim  on arti.cdunim_1 = unim.cdunim    \n";
        l_query += "          left outer join pgmr.tipomapr          tipm  on arti.cdtipa   = tipm.cdtipm    \n";
        l_query += "          left outer join pgmr.mrp_arch_artipo   artip on arti.cdartipo = artip.cdartipo \n";
        l_query += "          left outer join pgmr.mrp_arch_clasarti arcla on arti.cdclas_a = arcla.cdclas_a \n";
        if (astr.tipodoc.equals(astr.TIPODOC_BOLL)) {
            l_query += "   WHERE (bolt.tkfatt is null or bolt.tkfatt <> bolt.tkboll) \n";
        } else {
            l_query += "   WHERE bolt.tkfatt   = bolt.tkboll           \n";
        }
        l_query += "      and bolt.tkclie  = clie.tkclie           \n";
        l_query += "      and bolp." + astr.joinp + "  = bolt.tkboll     \n";
        l_query += "      and bolp.cdarti  = arti.cdarti           \n";
        l_query += "      and bolt.cdtdoc  = tdoc.cdtdoc           \n";
        l_query += "      and clie.cdente  = ente.cdente           \n";
        l_query += "      and bolt.cdazie  = '" + cdazie + "'          \n";
        l_query += "      and tdoc.cdclas  = 'V'                   \n";
        l_query += "      and bolp.nrquan  > 0                     \n";

        if (!s_cdcapoarea.equals("")) {
            l_query += "      and clie.cdagen in (" + par_ca_agenti(s_cdcapoarea) + ") \n";
        } else if (!s_cdagen.equals("")) {
            l_query += "      and clie.cdagen = '" + s_cdagen + "' \n";
        }
        if (!astr.cdagen.equals("")) {
            l_query += "      and clie.cdagen = '" + astr.cdagen + "' \n";
        }

        if (astr.dtdocu_da != null) {
            l_query += " and bolt." + astr.dtname + " >= ?  \n";
        }
        if (astr.dtdocu_a != null) {
            l_query += " and bolt." + astr.dtname + " <= ?  \n";
        }
        if (!astr.tkclie.equals("")) {
            l_query += " and clie.tkclie = '" + astr.tkclie + "'        \n";
        }
        if (!astr.nrdocu.equals("")) {
            if (astr.tipodoc.equals(astr.TIPODOC_BOLL)) {
                l_query += " and bolt.cdboll like '" + astr.nrdocu + "'        \n";
            } else {
                l_query += " and bolt.conpro = " + astr.nrdocu + "        \n";
            }
        }
        if (astr.anno > 0) {
            l_query += " and year(bolt." + astr.dtname + ") = " + astr.anno + "             \n";
        }
        if (!astr.cdarti.equals("")) {
            l_query += " and bolp.cdarti = '" + astr.cdarti + "'  \n";
        }
        if (astr.tkposi > 0) {
            l_query += " and bolp.tkposi = " + astr.tkposi + "  \n";
        }
        if (!astr.cdtipm.equals("")) {
            l_query += " and arti.cdtipa = '" + astr.cdtipm + "'  \n";
        }
        if (!astr.cdclas_a.equals("")) {
            l_query += " and arti.cdclas_a = '" + astr.cdclas_a + "'  \n";
        }
        if (!astr.cdartipo.equals("")) {
            l_query += " and arti.cdartipo = '" + astr.cdartipo + "'  \n";
        }
        if (!astr.cdzcom.equals("")) {
            l_query += " and clie.cdzcom = '" + astr.cdzcom + "'  \n";
        }

        if (!order_by.equals("")) {
            l_query += " order by " + order_by + " \n";
        }



        pstm = setQuery_ric(l_query);

        ind = 1;
        if (astr.dtdocu_da != null) {
            pstm.setTimestamp(ind++, astr.dtdocu_da);
        }
        if (astr.dtdocu_a != null) {
            pstm.setTimestamp(ind++, astr.dtdocu_a);
        }

        rs = pstm.executeQuery();


        return rs;

    }

    // avendramin 20080520
    /**
     * *
     *
     */
    public ResultSet getStat_docven(com.ateikon.structure.Str_controllo_docven astr, String tipostatistica, boolean is_count, String order_by) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        if (!s_tkclie.equals("")) {
            astr.tkclie = s_tkclie;
        }


        // imposto le costanti
        astr.ctrlTipodoc();


        if (astr.tipodoc.equals(astr.TIPODOC_BOLL)) {
            astr.nrdocu = par_like(astr.nrdocu);
        }

        if (tipostatistica.equals("A")) {
            if (order_by.equals("")) {
                order_by = "agen.cdagen_m ";
            }
        } else if (tipostatistica.equals("C")) {
            if (order_by.equals("")) {
                order_by = "clie.cdclie_m ";
            }
        }


        if (is_count) {
            order_by = "";
        }

        if (is_count) {

            if (tipostatistica.equals("A")) {
                l_query = "";
                l_query += "   select count(distinct clie.cdagen)                        \n";
            } else if (tipostatistica.equals("C")) {
                l_query = "";
                l_query += "   select count(distinct clie.tkclie)                        \n";
            } else {
                throw new Exception("tipostatistica non Prevista!");
            }

        } else {

            if (tipostatistica.equals("A")) {
                l_query = "";
                l_query += "   select clie.cdagen         \n";
                l_query += "        , agen.cdagen_m       \n";
                l_query += "        , agen.dsagen         \n";
            } else if (tipostatistica.equals("C")) {
                l_query = "";
                l_query += "   select clie.tkclie         \n";
                l_query += "        , clie.cdclie_m       \n";
                l_query += "        , ente.ragcog         \n";

            } else {
                throw new Exception("tipostatistica non Prevista!");
            }
            l_query += "        , sum(bolp.nrquan )            as tot_nrquan           \n";
            l_query += "        , sum(bolp.importonettoriga)   as tot_importonettoriga \n";

        } //Fine -- if (is_count){      


        l_query += "     FROM pgmr.bolla_test        bolt          \n";
        l_query += "        , pgmr.bolla_posi        bolp          \n";
        l_query += "        , pgmr.tipo_documento    tdoc          \n";
        l_query += "        , pgmr.archenti          ente          \n";
        l_query += "        , pgmr.archclie      clie            \n";
        l_query += "          left outer join pgmr.archagen agen on clie.cdagen = agen.cdagen \n";
        l_query += "          left outer join pgmr.zonacomm zcom on clie.cdzcom = zcom.cdzcom \n";
        l_query += "                                            and clie.cdazie = zcom.cdazie \n";
        l_query += "        , pgmr.mrp_arch_articoli arti      \n";
        l_query += "          left outer join pgmr.unimisura         unim  on arti.cdunim_1 = unim.cdunim    \n";
        l_query += "          left outer join pgmr.tipomapr          tipm  on arti.cdtipa   = tipm.cdtipm    \n";
        l_query += "          left outer join pgmr.mrp_arch_artipo   artip on arti.cdartipo = artip.cdartipo \n";
        l_query += "          left outer join pgmr.mrp_arch_clasarti arcla on arti.cdclas_a = arcla.cdclas_a \n";
        if (astr.tipodoc.equals(astr.TIPODOC_BOLL)) {
            l_query += "   WHERE (bolt.tkfatt is null or bolt.tkfatt <> bolt.tkboll) \n";
        } else {
            l_query += "   WHERE bolt.tkfatt   = bolt.tkboll           \n";
        }
        l_query += "      and bolt.cdazie  = '" + cdazie + "'          \n";
        l_query += "      and bolp." + astr.joinp + "  = bolt.tkboll     \n";
        l_query += "      and bolt.tkclie  = clie.tkclie           \n";
        l_query += "      and bolp.nrquan  > 0                     \n";
        l_query += "      and tdoc.cdclas  = 'V'                   \n";
        l_query += "      and bolt.cdtdoc  = tdoc.cdtdoc           \n";
        l_query += "      and bolp.cdarti  = arti.cdarti           \n";
        l_query += "      and clie.cdente  = ente.cdente           \n";

        if (!s_cdcapoarea.equals("")) {
            l_query += "      and clie.cdagen in (" + par_ca_agenti(s_cdcapoarea) + ") \n";
        } else if (!s_cdagen.equals("")) {
            l_query += "      and clie.cdagen = '" + s_cdagen + "' \n";
        }
        if (!astr.cdagen.equals("")) {
            l_query += "      and clie.cdagen = '" + astr.cdagen + "' \n";
        }

        if (astr.dtdocu_da != null) {
            l_query += " and bolt." + astr.dtname + " >= ?  \n";
        }
        if (astr.dtdocu_a != null) {
            l_query += " and bolt." + astr.dtname + " <= ?  \n";
        }
        if (!astr.tkclie.equals("")) {
            l_query += " and clie.tkclie = '" + astr.tkclie + "'        \n";
        }
        if (!astr.nrdocu.equals("")) {
            if (astr.tipodoc.equals(astr.TIPODOC_BOLL)) {
                l_query += " and bolt.cdboll like '" + astr.nrdocu + "'        \n";
            } else {
                l_query += " and bolt.conpro = " + astr.nrdocu + "        \n";
            }
        }
        if (astr.anno > 0) {
            l_query += " and year(bolt." + astr.dtname + ") = " + astr.anno + "             \n";
        }
        if (!astr.cdarti.equals("")) {
            l_query += " and bolp.cdarti = '" + astr.cdarti + "'  \n";
        }
        if (astr.tkposi > 0) {
            l_query += " and bolp.tkposi = " + astr.tkposi + "  \n";
        }
        if (!astr.cdtipm.equals("")) {
            l_query += " and arti.cdtipa = '" + astr.cdtipm + "'  \n";
        }
        if (!astr.cdclas_a.equals("")) {
            l_query += " and arti.cdclas_a = '" + astr.cdclas_a + "'  \n";
        }
        if (!astr.cdartipo.equals("")) {
            l_query += " and arti.cdartipo = '" + astr.cdartipo + "'  \n";
        }
        if (!astr.cdzcom.equals("")) {
            l_query += " and clie.cdzcom = '" + astr.cdzcom + "'  \n";
        }

        if (is_count) {
        } else {
            if (tipostatistica.equals("A")) {
                l_query += "   group by clie.cdagen         \n";
                l_query += "        , agen.cdagen_m       \n";
                l_query += "        , agen.dsagen         \n";
            } else if (tipostatistica.equals("C")) {
                l_query += "   group by clie.tkclie         \n";
                l_query += "        , clie.cdclie_m       \n";
                l_query += "        , ente.ragcog         \n";
            }
        }
        if (!order_by.equals("")) {
            l_query += " order by " + order_by + " \n";
        }



        System.out.println(l_query);

        pstm = setQuery_ric(l_query);

        ind = 1;
        if (astr.dtdocu_da != null) {
            pstm.setTimestamp(ind++, astr.dtdocu_da);
        }
        if (astr.dtdocu_a != null) {
            pstm.setTimestamp(ind++, astr.dtdocu_a);
        }

        rs = pstm.executeQuery();


        return rs;

    }

    public ResultSet getArticoli_trattati(String tkclie, Timestamp dtrif, String visu_andamento, boolean is_count, String order_by, String fgtipodoc) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        if (dtrif == null) {
            dtrif = of_getOggi();
        }

        long mese = 0;
        long anno = 0;
        String periodo = "";
        String ls_condizione = "";

        String ls_where = "";

        String dtname = "";

        if (fgtipodoc.equals("B")) {
            dtname = "dtboll";
        } else if (fgtipodoc.equals("F")) {
            dtname = "dtprot";
        } else {
            throw new Exception("fgtipodoc NON previsto!");
        }

        if (visu_andamento.equals("S")) {

            mese = dtrif.getMonth() + 1;
            anno = dtrif.getYear() + 1900;
            long mese_rif = (dtrif.getMonth() + 1);
            mese_rif++; //aggiungo un mese, per permettere che venga visualizzati i dati del mese di riferimento
            long anno_rif = (dtrif.getYear() + 1900);
            if (mese_rif > 12) {
                mese_rif = 1;
                anno_rif++;
            }
            String periodo_rif = "" + anno_rif + ((mese_rif <= 9) ? "0" + mese_rif : "" + mese_rif);
            periodo = "";
            for (int i = 0; i < 12; i++) {
                if (mese <= 0) {
                    mese = 12;
                    anno = anno - 1;
                }
                periodo = ((mese <= 9) ? "0" + mese : "" + mese);
                periodo = anno + periodo;
                mese = mese - 1;
            }

            if (this.is_oracle) {
                ls_where = "      and to_char(t." + dtname + ",'YYYYMM') >= '" + periodo + "'           \n";
                ls_where += "     and to_char(t." + dtname + ",'YYYYMM') < '" + periodo_rif + "'       \n";
            } else if (this.is_sybase) {
                ls_where = "      and t." + dtname + " >=  convert(DATETIME, '" + periodo + "01')       \n";
                ls_where += "     and t." + dtname + " <  convert(DATETIME, '" + periodo_rif + "01')   \n";

            }

        } else {

            ls_where = "      and t." + dtname + " >= (" + sysdate + " - 365)                         \n";
        }


        if (order_by.equals("")) {
            order_by = "a.dsarti";
        }
        if (is_count) {
            order_by = "";
        }

        if (is_count) {

            l_query = "";
            l_query += "   select count(distinct a.cdarti)                        \n";
            l_query += "        , sum(case when catramer.ind_stat_ven = 'P' then p.nrquan when catramer.ind_stat_ven = 'M' then (p.nrquan * -1) else p.nrquan end) as totqta                                             \n";
            l_query += "        , sum(case when catramer.ind_stat_ven = 'P' then p.importonettoriga when catramer.ind_stat_ven = 'M' then (p.importonettoriga * -1) else p.importonettoriga end) as totimportonettoriga  \n";
        } else {

            l_query = "";
            l_query += "   SELECT a.cdartm                                     \n";
            l_query += "        , a.cdarti                                     \n";
            l_query += "        , a.dsarti                                     \n";
            if (visu_andamento.equals("S")) {

                mese = dtrif.getMonth() + 1;
                anno = dtrif.getYear() + 1900;
                periodo = "";
                ls_condizione = "";

                for (int i = 0; i < 12; i++) {
                    if (mese <= 0) {
                        mese = 12;
                        anno = anno - 1;
                    }
                    periodo = ((mese <= 9) ? "0" + mese : "" + mese);
                    periodo = anno + periodo;

                    if (this.is_oracle) {
                        ls_condizione = "to_char(t." + dtname + ",'YYYYMM') = '" + periodo + "'\n";
                    } else if (this.is_sybase) {
                        ls_condizione = "( month(t." + dtname + ") = " + ((mese <= 9) ? "0" + mese : "" + mese) + " and year(t." + dtname + ") = " + (anno) + ")          \n";

                    }

                    l_query += "        , sum(case when " + ls_condizione + " then (case when catramer.ind_stat_ven = 'P' then p.nrquan when catramer.ind_stat_ven = 'M' then (p.nrquan * -1) else p.nrquan end) else 0 end) as totqta" + (i + 1) + "                         \n";
                    l_query += "        , sum(case when " + ls_condizione + " then (case when catramer.ind_stat_ven = 'P' then p.importonettoriga when catramer.ind_stat_ven = 'M' then (p.importonettoriga * -1) else p.importonettoriga end) else 0 end) as totimportonettoriga" + (i + 1) + "  \n";
                    mese = mese - 1;
                }

            } else {

                l_query += "        , max(p.tkposi_b)           as maxtkposi         \n";
                l_query += "        , sum(case when catramer.ind_stat_ven = 'P' then p.nrquan when catramer.ind_stat_ven = 'M' then (p.nrquan * -1) else p.nrquan end) as totqta                                             \n";
                l_query += "        , sum(case when catramer.ind_stat_ven = 'P' then p.importonettoriga when catramer.ind_stat_ven = 'M' then (p.importonettoriga * -1) else p.importonettoriga end) as totimportonettoriga  \n";
                l_query += "        , (select ibolp.importonettoriga                \n";
                l_query += "             from pgmr.bolla_posi ibolp               \n";
                l_query += "            where ibolp.tkposi_b = maxtkposi              \n"; //Nota in oracle non funziona
                l_query += "          ) as ultimoprezzo                             \n";

            }  // Fine -- if (visu_andamento.equals("S")){    

        }

        l_query += "     FROM pgmr.bolla_test  t                                                                                       \n";
        l_query += "        , pgmr.mrp_arch_articoli  a                                                                                \n";
        l_query += "        , pgmr.tipo_documento      tipodocumento                                                                   \n";
        l_query += "        , pgmr.archclie clie                                                                     \n";
        l_query += "                            LEFT OUTER JOIN  pgmr.archagen           agen ON  clie.cdagen = agen.cdagen            \n";
        l_query += "        , pgmr.bolla_posi p                                                                    \n";
        l_query += "                            LEFT OUTER JOIN  pgmr.ut_art_catramer    catramer ON  p.cdcatm = catramer.cdcatm       \n";
        l_query += "    WHERE t.cdazie = '" + this.cdazie + "'                                                                             \n";
        l_query += ls_where;
        if (fgtipodoc.equals("B")) {
            l_query += "      and t.tkboll = p.tkboll               \n";
        } else if (fgtipodoc.equals("F")) {
            l_query += "      and t.tkboll = p.tkfatt               \n";
        } else {
            throw new Exception("fgtipodoc NON previsto!");
        }
        if (fgtipodoc.equals("B")) {
            l_query += "       and (   t.tkfatt is null          \n";
            l_query += "            or t.tkfatt <> t.tkboll )    \n";

        } else if (fgtipodoc.equals("F")) {

            l_query += "       and t.tkfatt = t.tkboll           \n";
        } else {
            throw new Exception("fgtipodoc NON previsto!");
        }
        l_query += "      and p.cdarti   = a.cdarti             \n";
        l_query += "      and t.tkclie = clie.tkclie            \n";
        l_query += "      and tipodocumento.cdclas = 'V'        \n";
        l_query += "      and t.cdtdoc  = tipodocumento.cdtdoc        \n";

        if (!s_cdcapoarea.equals("")) {
            l_query += "   and clie.cdagen in (" + par_ca_agenti(s_cdcapoarea) + ") \n";
        } else if (!s_cdagen.equals("")) {
            l_query += "    and clie.cdagen = '" + s_cdagen + "'                \n";
        } else if (!s_tkclie.equals("")) {
            l_query += "  and t.tkclie = '" + s_tkclie + "'  \n";
        }

        if (!tkclie.equals("")) {
            l_query += "  and t.tkclie = '" + tkclie + "'  \n";
        }

        if (is_count) {
        } else {
            l_query += " GROUP BY a.cdartm                                     \n";
            l_query += "        , a.cdarti                                     \n";
            l_query += "        , a.dsarti                                     \n";
        }

        System.out.println(l_query);

        pstm = setQuery_ric(l_query);

        rs = pstm.executeQuery();



        return rs;

    }

    public ResultSet getKey_fatt(long tkfatt) throws Exception {

        if (1 == 1) {
            throw new Exception("ATTENZIONE!!! questo metodo può essere richiamato solo in E-progen");
        }

        return null;

    }

    
    public long tkboll = 0;
}
