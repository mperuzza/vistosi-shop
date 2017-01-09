package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import com.ateikon.structure.Str_search_articoli;
import com.ateikon.structure.Str_risorsa_anticipo;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Mrp_arch_articoli extends Atk_sql {

    public Mrp_arch_articoli() {

        super();
    }

    public ResultSet search(Str_search_articoli astr, boolean is_count, String order_by) throws Exception {

        int ind = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        Costanti_comm costanti_comm = new Costanti_comm();

        setProfilo((Atk_sql) costanti_comm);

        String ls_lwm_fggiacmaga = "N";

        ls_lwm_fggiacmaga = costanti_comm.getCostvalue("lwm_fggiacmaga");

        if (!ls_lwm_fggiacmaga.equals("S")) {
            ls_lwm_fggiacmaga = "N";
        }

        long ll_tkmaga = 0;

        try {

            ll_tkmaga = Long.parseLong(costanti_comm.getCostvalue("tkmaga_def"));

        } catch (Exception ex) {
            ll_tkmaga = 0;
        }

        Str_search_articoli lstr = new Str_search_articoli();

        lstr.copia(astr);

        String ls_search_app = "";
        if (lstr.f_search.indexOf("+") >= 0) {
            ls_search_app = lstr.f_search;
            lstr.f_search = lstr.f_search.replace('+', ' ');
        }

        lstr.f_search = par_like(lstr.f_search);
        lstr.f_cdmatricola_m = par_like(lstr.f_cdmatricola_m);

        lstr.f_cdartprod = par_like(lstr.f_cdartprod);
        lstr.f_cdartm = par_like(lstr.f_cdartm);
        lstr.f_dsarti = par_like(lstr.f_dsarti);

        if (lstr.f_cdclas_a_m.indexOf("*") >= 0) {
            lstr.f_cdclas_a_m = par_like(lstr.f_cdclas_a_m);
        }

        if (order_by.equals("")) {
            order_by = "art.cdtipa, art.cdartm";
        }

        if (is_count) {
            l_query = "";
            l_query += " select count(art.cdarti) \n";

            order_by = "";

        } else {

            l_query = "";
            l_query += " select art.cdarti                        \n";
            l_query += "      , art.cdartm                        \n";
            l_query += "      , art.dsarti                        \n";
            l_query += "      , art.cdbarcode                     \n";
            l_query += "      , art.cdmarca                       \n";
            l_query += "      , art.cdartprod                     \n";
            l_query += "      , art.nrpeso_l                      \n";
            l_query += "      , art.vlunlt                        \n";
            l_query += "      , art.fpacco                        \n";
            l_query += "      , art.fpacco_art                    \n";
            l_query += "      , art.dimena                        \n";
            l_query += "      , art.dimenb                        \n";
            l_query += "      , art.dimenc                        \n";
            l_query += "      , mis.cdunim                        \n";
            l_query += "      , mis.cdunim_m                      \n";
            l_query += "      , mis.dsunim                        \n";
            l_query += "      , mar.dsmarca                       \n";
            l_query += "      , mar.cdmarca_m                     \n";
            l_query += "      , tpm.cdtipm                        \n";
            l_query += "      , tpm.cdtipm_m                      \n";
            l_query += "      , tpm.dstipm                        \n";
            l_query += "      , sta.cdstato                       \n";
            l_query += "      , sta.cdstato_m                     \n";
            l_query += "      , sta.dsstato                       \n";
            l_query += "      , rep.cdrepa                        \n";
            l_query += "      , rep.cdrepa_m                      \n";
            l_query += "      , rep.dsrepa                        \n";
        }

        if (is_oracle) {

            l_query += "   from pgmr.mrp_arch_articoli   art      \n";
            l_query += "      , pgmr.mis_marca           mar      \n";
            l_query += "      , pgmr.mis_reparto         rep      \n";
            l_query += "      , pgmr.mrp_arch_stato      sta      \n";
            l_query += "      , pgmr.tipomapr            tpm      \n";
            l_query += "      , pgmr.unimisura           mis      \n";
            l_query += "      , pgmr.mrp_arch_clasarti   cla      \n";
            l_query += "  where art.cdazie   = ?                  \n";
            l_query += "    and art.cdmarca  = mar.cdmarca  (+)   \n";
            l_query += "    and art.cdrepa   = rep.cdrepa   (+)   \n";
            l_query += "    and art.cdstato  = sta.cdstato  (+)   \n";
            l_query += "    and art.cdclas_a = cla.cdclas_a (+)   \n";

            l_query += "    and art.cdunim_1 = mis.cdunim   (+)   \n";
            l_query += "    and art.cdtipa   = tpm.cdtipm         \n";
            l_query += "    and sta.fesclu <> 'S'                 \n";

        } else if (is_sybase) {

            l_query += "  FROM {oj               pgmr.mrp_arch_articoli art                                  \n";
            l_query += "        LEFT OUTER JOIN  pgmr.tipomapr          tpm ON art.cdtipa     = tpm.cdtipm   \n";
            l_query += "        LEFT OUTER JOIN  pgmr.mis_marca         mar ON art.cdmarca    = mar.cdmarca  \n";
            l_query += "        LEFT OUTER JOIN  pgmr.mis_reparto       rep ON art.cdrepa     = rep.cdrepa   \n";
            l_query += "        LEFT OUTER JOIN  pgmr.mrp_arch_stato    sta ON art.cdstato    = sta.cdstato  \n";
            l_query += "        LEFT OUTER JOIN  pgmr.mrp_arch_clasarti cla ON art.cdclas_a   = cla.cdclas_a \n";
            l_query += "        LEFT OUTER JOIN  pgmr.unimisura         mis ON art.cdunim_1   = mis.cdunim   \n";
            l_query += "        }                                                                            \n";
            l_query += "  where art.cdazie = ?      \n";
            l_query += "    and sta.fesclu <> 'S'   \n";

        } else {
            throw new Exception("TIPO DB NON PREVISTO");
        }

        if (!s_tkclie.equals("")) {

            // se sono un cliente filtro per categorie
            l_query += "   and art.cdtipa in (select itipm.cdtipm                  \n";
            l_query += "                        from pgmr.cat_tipomapr_clie itipm  \n";
            l_query += "                       where itipm.tkclie = '" + s_tkclie + "' \n";
            l_query += "                         and itipm.cdazie = '" + cdazie + "'   \n";
            l_query += "                             )                             \n";
        }

        if (lstr.f_cdarti.equals("")) {
            // se non entro per chiave 
            l_query += "   and art.cdtipa not in (select itipm.cdtipm                  \n";
            l_query += "                            from pgmr.cat_tipomapr_escl itipm  \n";
            l_query += "                                 )                             \n";
        }

        if (!lstr.f_cdarti.equals("")) {
            l_query += "  and art.cdarti   = '" + lstr.f_cdarti + "' \n";
        }
        if (!lstr.f_cdrepa.equals("")) {
            l_query += "  and art.cdrepa   = '" + lstr.f_cdrepa + "' \n";
        }
        if (!lstr.f_cdtipm.equals("")) {
            l_query += "  and art.cdtipa   = '" + lstr.f_cdtipm + "' \n";
        }
        if (!lstr.f_cdclas_a.equals("")) {
            l_query += "  and art.cdclas_a   = '" + lstr.f_cdclas_a + "' \n";
        } else {
            if (lstr.f_cdclas_a_m.indexOf("%") >= 0) {
                if (!lstr.f_cdclas_a_m.equals("")) {
                    l_query += "  and cla.cdclas_a_m like '" + lstr.f_cdclas_a_m + "' \n";
                }
            } else {
                if (!lstr.f_cdclas_a_m.equals("")) {
                    l_query += "  and cla.cdclas_a_m = '" + lstr.f_cdclas_a_m + "' \n";
                }
            }
        }
        if (!lstr.f_cdartipo.equals("")) {
            l_query += "  and art.cdartipo = '" + lstr.f_cdartipo + "' \n";
        }
        if (!lstr.f_cdartprod.equals("")) {
            l_query += "  and art.cdartprod like '" + lstr.f_cdartprod + "' \n";
        }
        if (!lstr.f_cdartm.equals("")) {
            l_query += "  and art.cdartm like '" + lstr.f_cdartm + "' \n";
        }
        if (!lstr.f_dsarti.equals("")) {
            l_query += "  and art.dsarti like '" + lstr.f_dsarti + "' \n";
        }

        if (!lstr.f_search.equals("")) {

            l_query += " and (   upper(art.dsarti    ) like '" + lstr.f_search + "'  \n";

            String[] arr_ = ls_search_app.split("\\+");
            for (int i = 0; arr_ != null && i < arr_.length; i++) {
                arr_[i] = arr_[i].trim();
                if (arr_[i].equals("")) {
                    continue;
                }
                String ls_ = par_like(arr_[i]);
                l_query += "      or upper(art.dsarti ) like '" + ls_ + "'  \n";
            }
            l_query += "      )                                \n";

        }
        if (!lstr.f_cdmatricola_m.equals("")) {
            l_query += " and art.cdarti in (select distinct(matr.cdarti)                    \n";
            l_query += "                      from pgmr.matr_anagrafica matr                \n";
            l_query += "                     where cdmatricola_m like '" + lstr.f_cdmatricola_m + "' \n";
            l_query += "                       and cdazie = '" + cdazie + "'                    \n";
            l_query += "                             )                                      \n";
            l_query += " and art.fpacco_art <> 'S'  \n";
            l_query += " and art.fpacco = 'S'       \n";
        }
        if (lstr.f_pacco_si_no.equals("N")) {

            l_query += "      and art.fpacco = 'N'  \n";

            if (lstr.f_giacmin > 0) {

                l_query += " and ( CASE WHEN fpacco_art <> 'S'                   \n";
                l_query += "            THEN                                     \n";
                l_query += "               ( select sum(giac.qtagia)             \n";
                l_query += "                   from pgmr.mrp_file_giacenza giac  \n";
                l_query += "                  where giac.cdarti = art.cdarti     \n";
                l_query += "                    and giac.cdvar = 'STD'           \n";
                if (ls_lwm_fggiacmaga.equals("S")) {
                    l_query += "                    and giac.tkmaga = " + ll_tkmaga + "  \n";
                }
                l_query += "                    )                                \n";
                l_query += "            ELSE                                     \n";
                l_query += "               ( select max(mgiac.qtagia)            \n";
                l_query += "                   from pgmr.matr_giacenza mgiac     \n";
                l_query += "                  where mgiac.cdarti = art.cdarti    \n";
                l_query += "                    and mgiac.cdvar = 'STD'          \n";
                if (ls_lwm_fggiacmaga.equals("S")) {
                    l_query += "                    and mgiac.tkmaga = " + ll_tkmaga + " \n";
                }
                l_query += "                    )                                \n";
                l_query += "            END                                      \n";
                l_query += "                     ) >= ?                          \n";

            }
            if (lstr.f_dimenamin > 0) {
                l_query += "      and art.dimena >= ?  \n";
            }
            if (lstr.f_dimenamax > 0) {
                l_query += "      and art.dimena <= ?  \n";
            }
            if (lstr.f_dimenbmin > 0) {
                l_query += "      and art.dimenb >= ?  \n";
            }
            if (lstr.f_dimenbmax > 0) {
                l_query += "      and art.dimenb <= ?  \n";
            }
            if (lstr.f_dimencmin > 0) {
                l_query += "      and art.dimenc >= ?  \n";
            }
            if (lstr.f_dimencmax > 0) {
                l_query += "      and art.dimenc <= ?  \n";
            }

        } else if (lstr.f_pacco_si_no.equals("S")) {

            l_query += "      and art.fpacco = 'S'  \n";
            l_query += "      and art.fpacco_art <> 'S'  \n";

            if (lstr.f_giacmin > 0) {
                // trovo gli articoli dove ci c'� almeno un pacco con giacenza superiore 
                // a quella richiesta
                l_query += " AND \n";
                l_query += " ( select max(giac.qtagia)               \n";
                l_query += "     from pgmr.matr_giacenza giac        \n";
                l_query += "    where giac.cdarti = art.cdarti      \n";
                l_query += "      and giac.cdvar = 'STD'             \n";
                if (ls_lwm_fggiacmaga.equals("S")) {
                    l_query += "      and giac.tkmaga = " + ll_tkmaga + " \n";
                }
                l_query += " ) >= ? ";
            }

            l_query += " AND \n";
            l_query += " ( select count(anag.tkmatricola)     \n";
            l_query += "     from pgmr.matr_anagrafica anag   \n";
            l_query += "    where anag.cdarti = art.cdarti   \n";
            l_query += "      and anag.cdazie = '" + cdazie + "'  \n";
            if (lstr.f_dimenamin > 0) {
                l_query += "      and anag.dimenamin >= ?  \n";
            }
            if (lstr.f_dimenamax > 0) {
                l_query += "      and anag.dimenamax <= ?  \n";
            }
            if (lstr.f_dimenbmin > 0) {
                l_query += "      and anag.dimenbmin >= ?  \n";
            }
            if (lstr.f_dimenbmax > 0) {
                l_query += "      and anag.dimenbmax <= ?  \n";
            }
            if (lstr.f_dimencmin > 0) {
                l_query += "      and anag.dimencmin >= ?  \n";
            }
            if (lstr.f_dimencmax > 0) {
                l_query += "      and anag.dimencmax <= ?  \n";
            }
            l_query += " ) > 0 ";

        }

        if (!order_by.equals("")) {
            l_query += " order by " + order_by + "\n";
        }

        pstm = setQuery_ric(l_query);

        ind = 1;
        pstm.setString(ind, cdazie);
        ind += 1;
        if (lstr.f_pacco_si_no.equals("N")
                || lstr.f_pacco_si_no.equals("S")) {

            if (lstr.f_giacmin > 0) {
                pstm.setDouble(ind, lstr.f_giacmin);
                ind += 1;
            }

            if (lstr.f_dimenamin > 0) {
                pstm.setDouble(ind, lstr.f_dimenamin);
                ind += 1;
            }
            if (lstr.f_dimenamax > 0) {
                pstm.setDouble(ind, lstr.f_dimenamax);
                ind += 1;
            }
            if (lstr.f_dimenbmin > 0) {
                pstm.setDouble(ind, lstr.f_dimenbmin);
                ind += 1;
            }
            if (lstr.f_dimenbmax > 0) {
                pstm.setDouble(ind, lstr.f_dimenbmax);
                ind += 1;
            }
            if (lstr.f_dimencmin > 0) {
                pstm.setDouble(ind, lstr.f_dimencmin);
                ind += 1;
            }
            if (lstr.f_dimencmax > 0) {
                pstm.setDouble(ind, lstr.f_dimencmax);
                ind += 1;
            }

        }

        rs = pstm.executeQuery();

        return rs;

    }

    /**
     * **
     * getKey: mrp_arch_articoli
     *
     */
    public ResultSet getKey(String cdarti) throws Exception {
        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        l_query = "";
        l_query += " select art.cdarti              \n";
        l_query += "      , art.fpacco              \n";
        l_query += "      , art.fpaccodist          \n";
        l_query += "      , art.dsarti              \n";
        l_query += "      , art.cdbarcode           \n";
        l_query += "      , art.cdartm              \n";
        l_query += "      , art.dsarti   as dssart  \n";
        l_query += "      , art.dimena              \n";
        l_query += "      , art.dimenb              \n";
        l_query += "      , art.dimenc              \n";
        l_query += "      , art.cdunim_1 as cdunim  \n";
        l_query += "      , art.fpacco_art          \n";
        l_query += "      , uni.cdunim_m            \n";
        l_query += "      , uni.dsunim              \n";
        l_query += "      , uni.cdtins              \n";
        l_query += "      , dti.dstins              \n";
        l_query += "      , dti.fdimena             \n";
        l_query += "      , dti.fdimenb             \n";
        l_query += "      , dti.fdimenc             \n";
        l_query += "      , dti.fquantita           \n";
        l_query += "      , dti.fncolli             \n";
        l_query += "      , dti.fdivmis             \n";
        l_query += "      , st.cdstato_m            \n";
        l_query += "      , tipm.scmax_1            \n";
        l_query += "      , tipm.scmax_2            \n";

        if (is_oracle) {

            l_query += "   from pgmr.mrp_arch_articoli art     \n";
            l_query += "      , pgmr.unimisura         uni     \n";
            l_query += "      , pgmr.doc_tipo_insriga  dti     \n";
            l_query += "      , pgmr.mrp_arch_stato    st      \n";
            l_query += "      , pgmr.tipomapr          tipm    \n";
            l_query += "  where art.cdarti = ?                 \n";
            l_query += "    and art.cdunim_1 = uni.cdunim (+)  \n";
            l_query += "    and uni.cdtins   = dti.cdtins (+)  \n";
            l_query += "    and art.cdstato  = st.cdstato (+)  \n";
            l_query += "    and art.cdtipa   = tipm.cdtipm (+) \n";

        } else if (is_sybase) {

            l_query += "  FROM {oj               pgmr.mrp_arch_articoli art    \n";
            l_query += "        LEFT OUTER JOIN  pgmr.unimisura         uni  ON art.cdunim_1 = uni.cdunim  \n";
            l_query += "        LEFT OUTER JOIN  pgmr.doc_tipo_insriga  dti  ON uni.cdtins   = dti.cdtins  \n";
            l_query += "        LEFT OUTER JOIN  pgmr.mrp_arch_stato    st   ON art.cdstato  = st.cdstato  \n";
            l_query += "        LEFT OUTER JOIN  pgmr.tipomapr          tipm ON art.cdtipa   = tipm.cdtipm \n";
            l_query += "        }                                                                          \n";
            l_query += " where art.cdarti = ?                  \n";

        } else {
            throw new Exception("TIPO DB NON PREVISTO :: Lgm_pl_posi.java ");
        }

        pstm = setQuery(l_query);

        ind = 1;
        pstm.setString(ind, cdarti);
        ind += 1;

        rs = pstm.executeQuery();

        return rs;

    }

    /**
     * **
     * getKey_m: mrp_arch_articoli
     *
     */
    public ResultSet getKey_m(String cdartm) throws Exception {
        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        l_query = "";
        l_query += "  select cdarti                  \n";
        l_query += "    from pgmr.mrp_arch_articoli  \n";
        l_query += "   where cdartm = '" + cdartm + "'   \n";
        l_query += "     and cdazie = '" + cdazie + "'   \n";

        String cdarti = sql_String(l_query);

        return getKey(cdarti);

    }

    public ResultSet getArticolo(String f_cdarti) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        Str_search_articoli lstr = new Str_search_articoli();

        lstr.f_cdarti = f_cdarti;

        boolean is_count = false;
        String order_by = "";

        rs = search(lstr, is_count, order_by);

        return rs;

    }

    /**
     * *
     *
     *
     */
    public ResultSet getPacchi(String cdarti) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        l_query = "";
        l_query += " select matr.*                       \n";
        l_query += "      , ubic.dsubmatr                \n";
        l_query += "  FROM {oj               pgmr.matr_anagrafica matr                               \n";
        l_query += "        LEFT OUTER JOIN  pgmr.matr_ubicazione ubic ON matr.cdubmatr = ubic.cdubmatr \n";
        l_query += "        }                                                                                   \n";
        l_query += "  where matr.cdarti = ?              \n";
        l_query += "  order by matr.cdmatricola_m        \n";

        pstm = setQuery(l_query);

        ind = 1;
        pstm.setString(ind++, cdarti);

        rs = pstm.executeQuery();

        return rs;

    }

    /**
     * *
     *
     *
     */
    public void getMisure_pacco(double[] arr_, long tkmatricola) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        if (arr_ == null || arr_.length <= 0) {
            return;
        }

        int tot_ = arr_.length;

        for (int i = 0; i < tot_; i++) {
            arr_[i] = 0;
        }

        tot_rec = 0;

        l_query = "";
        l_query += " select distinct (dist.dimena)                \n";
        l_query += "   from pgmr.matr_anagrafica_dist dist        \n";
        l_query += "  where dist.tkmatricola = ?                  \n";
        l_query += "    and dist.fgsaldo = 'N'                    \n";
        l_query += "    and (dist.ncolli - dist.ncollicons) > 0   \n";
        l_query += "  order by dist.dimena desc                   \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;
        pstm.setLong(ind++, tkmatricola);

        rs = pstm.executeQuery();

        ind = 0;

        while (rs != null && rs.next() && ind < tot_) {

            double dimena = 0;

            if (rs.getObject(1) != null) {
                dimena = rs.getDouble(1);
            }

            arr_[ind] = dimena;

            ind += 1;
        }
        pstm.close();

        return;

    }

    /**
     * *
     *
     *
     */
    public String getDsubmatr(String cdarti, long tkmatricola) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String dsubmatr = "";

        if (tkmatricola > 0) {

            l_query = "";
            l_query += "  select ubic.dsubmatr                    \n";
            l_query += "    from pgmr.matr_anagrafica matr        \n";
            l_query += "       , pgmr.matr_ubicazione ubic        \n";
            l_query += "   where matr.cdubmatr = ubic.cdubmatr    \n";
            l_query += "     and matr.tkmatricola = ?             \n";

            pstm = setQuery(l_query);

            ind = 1;
            pstm.setLong(ind++, tkmatricola);

            rs = pstm.executeQuery();

        } else {

            l_query = "";
            l_query += "  select ubic.dsubmatr                    \n";
            l_query += "    from pgmr.matr_anagrafica matr        \n";
            l_query += "       , pgmr.matr_ubicazione ubic        \n";
            l_query += "   where matr.cdubmatr = ubic.cdubmatr    \n";
            l_query += "     and matr.cdarti = ?                  \n";
            l_query += "    order by matr.tkmatricola desc        \n";

            pstm = setQuery(l_query);

            ind = 1;
            pstm.setString(ind++, cdarti);

            rs = pstm.executeQuery();

        }

        if (rs != null && rs.next()) {
            if (rs.getObject(1) != null) {
                dsubmatr = rs.getString(1);
            }
        }

        return dsubmatr;

    }

    public ResultSet getKey(String cdarti, String cdling) throws Exception {
        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null; //avendramin20080102

        l_query = "";
        l_query += " select *                      \n";
        l_query += "      , " + of_descr_lingua(cdling, "", "dsarti") + " as dsarti_lin   \n";
        l_query += "   from pgmr.mrp_arch_articoli \n";
        l_query += "  where cdarti= ?              \n";

        pstm = setQuery(l_query);

        ind = 1;
        pstm.setString(ind, cdarti);
        ind += 1;

        rs = pstm.executeQuery();

        return rs;

    }

    public String of_relpath_resource_specsheet(String cdclas_a, String rootpath, String prefixfilename, String lang, String cdartm, String cdvistelet) {
        String slash = System.getProperty("file.separator");

        String relativepath = "images" + slash + "articoli" + slash + "specsheetres" + slash + "risorse" + slash;

        String relpathDefault = "/specsheet/" + cdartm;

        List<String> possibleFilenameList = new ArrayList<String>();
        possibleFilenameList.add(prefixfilename);

        if ("UL".equals(cdclas_a)) {
            possibleFilenameList.add(0, prefixfilename + "UL");
            possibleFilenameList.add(0, prefixfilename + cdvistelet + "UL");
        } else {
            possibleFilenameList.add(0, prefixfilename + cdvistelet);
        }

        for (String ls_prefixfilename : possibleFilenameList) {

            String pathPdf = rootpath + relativepath + ls_prefixfilename + ".pdf";
            String pathU3D = rootpath + relativepath + ls_prefixfilename + ".U3D";
            String pathXlsx = rootpath + relativepath + ls_prefixfilename + "_" + lang + ".xlsx";

            if (new File(pathPdf).exists()) {
//              if (new File(pathU3D).exists()){
                if (new File(pathXlsx).exists()) {
                    return relpathDefault;
                }
//              }
            }

        }

        return "";
    }

    public String of_relpath_resource(String cdclas_a,
             String tiporisorsa,
             String cdvistfam,
             String cdvisttp,
             String cdvistv1,
             String cdvistv2,
             String cdvistv3,
             String cdvistelet,
             String vist_filedis,
             String vist_scheda_pdf
    ) throws Exception {

        String slash = System.getProperty("file.separator");

        String path_techsheet = "fileresources" + slash + DIR_TECHSHEET + slash;
        String path_modello = of_get_path_modello(slash);
        String nome_modello_3D = vist_filedis;
        String path_2D = path_modello + "2D" + slash;
        String nome_modello_2D = vist_filedis;
        String path_imgcat = "fileresources" + slash + "catimages" + slash;
        String path_imgcat_hi = path_imgcat + "hi" + slash;
        String nome_imgcat_hi = vist_filedis;
        String path_imgcat_lo = path_imgcat + "lo" + slash;
        String nome_imgcat_lo = vist_filedis;
        String path_cert = "fileresources" + slash + "cert" + slash;
        String nome_cert = vist_filedis;
        String path_specsheet = "fileresources" + slash + "specsheet" + slash;

        if (tiporisorsa.equals(TECHSHEET)) {

            if (!cdvistfam.equals("") && !cdvisttp.equals("")) {

                l_query = "";
                l_query = "      select distinct vdex.arw_file_scheda_tec       \n";
                l_query += "        from pgmr.vist_articoli_datiextra  vdex      \n";
                l_query += "           , pgmr.vist_articoli      varti           \n";
                l_query += "           , pgmr.mis_reparto        repa            \n";
                l_query += "       where vdex.cdarti = varti.cdarti              \n";
                l_query += "         and varti.cdrepa = repa.cdrepa              \n";
                l_query += "         and vdex.arw_file_scheda_tec is not null    \n";
                l_query += "         and varti.cdvistfam is not null             \n";
                l_query += "         and varti.cdvisttp is not null              \n";
                l_query += "         and repa.cdrepa not in ('010', '018')       \n"; //CABLATO
                l_query += "         and varti.fgweb  = 'S'                      \n";
                l_query += "         and repa.fgweb  = 'S'                       \n";

                l_query += "         and varti.cdvistfam = '" + cdvistfam + "'     \n";
                l_query += "         and varti.cdvisttp  = '" + cdvisttp + "'      \n";

                if (!cdvistv1.equals("")) {
                    l_query += "         and varti.cdvistv1  = '" + cdvistv1 + "'      \n";
                } else {
                    l_query += "         and varti.cdvistv1 is null    \n";
                }
                if (!cdvistv2.equals("")) {
                    l_query += "         and varti.cdvistv2  = '" + cdvistv2 + "'      \n";
                } else {
                    l_query += "         and varti.cdvistv2 is null    \n";
                }
                if (!cdvistv3.equals("")) {
                    l_query += "         and varti.cdvistv3  = '" + cdvistv3 + "'      \n";
                } else {
                    l_query += "         and varti.cdvistv3 is null    \n";
                }
                if (!cdvistelet.equals("")) {
                    l_query += "         and varti.cdvistelet  = '" + cdvistelet + "'  \n";
                } else {
                    l_query += "         and varti.cdvistelet is null    \n";
                }

                l_query += " order by vdex.arw_file_scheda_tec           \n";

                String techsheet = sql_String(l_query);

                int idx_slash = techsheet.indexOf("\\");

                if (idx_slash >= 0) {
                    techsheet = techsheet.substring(idx_slash + 1);
                }

                if (techsheet.equals("")) {
                    return "";
                }

                return path_techsheet + techsheet;
            } else {
                return "";
            }

        } else if (tiporisorsa.equals(MOD3D_IGS)) {

            return of_relpath_resource_MOD3D_IGS(nome_modello_3D, cdclas_a, cdvistelet);

//      } else if (tiporisorsa.equals(MOD3D_EPRT)){
//        
//        String eprt = path_3D + nome_modello_3D + ".EPRT";
//        
//        if (nome_modello_3D.equals("")) return "";
//        
//        return eprt;
        } else if (tiporisorsa.equals(MOD3D_EASM)) {

            return of_relpath_resource_MOD3D_EASM(nome_modello_3D, cdclas_a, cdvistelet);

        } else if (tiporisorsa.equals(MOD2D_DWG_PO)) {

            String dwg_vers = "po" + slash;     //POLLICI                

            String dwg = path_2D + dwg_vers + nome_modello_2D + ".dwg";

            if (nome_modello_2D.equals("")) {
                return "";
            }

            return dwg;

        } else if (tiporisorsa.equals(MOD2D_DWG_CM)) {

            String dwg_vers = "cm" + slash;     //CENTIMETRI                    

            String dwg = path_2D + dwg_vers + nome_modello_2D + ".dwg";

            if (nome_modello_2D.equals("")) {
                return "";
            }

            return dwg;

        } else if (tiporisorsa.equals(IMAGEPROD_HI_MULTIF)) {

            String imgcat_hi_zip = path_imgcat_hi + nome_imgcat_hi + ".zip";

            if (nome_imgcat_hi.equals("")) {
                return "";
            }

            return imgcat_hi_zip;

        } else if (tiporisorsa.equals(IMAGEPROD_HI_SINGF)) {

            String imgcat_hi_jpg = path_imgcat_hi + nome_imgcat_hi + ".jpg";

            if (nome_imgcat_hi.equals("")) {
                return "";
            }

            return imgcat_hi_jpg;

        } else if (tiporisorsa.equals(IMAGEPROD_LO_MULTIF)) {

            String imgcat_lo_zip = path_imgcat_lo + nome_imgcat_lo + ".zip";

            if (nome_imgcat_lo.equals("")) {
                return "";
            }

            return imgcat_lo_zip;

        } else if (tiporisorsa.equals(IMAGEPROD_LO_SINGF)) {

            String imgcat_lo_jpg = path_imgcat_lo + nome_imgcat_lo + ".jpg";

            if (nome_imgcat_lo.equals("")) {
                return "";
            }

            return imgcat_lo_jpg;

        } else if (tiporisorsa.equals(CERT)) {

            String cert = path_cert + nome_cert + ".pdf";

            if (nome_cert.equals("")) {
                return "";
            }

            return cert;

        } else if (tiporisorsa.equals(SPECSHEET_IT)) {

            String path_specsheet_it = path_specsheet + "it" + slash;
            String nome_specsheet_it = vist_scheda_pdf;

            String specsheet_it = path_specsheet_it + nome_specsheet_it;

            if (nome_specsheet_it.equals("")) {
                return "";
            }

            return specsheet_it;

        } else if (tiporisorsa.equals(SPECSHEET_US)) {

            String path_specsheet_us = path_specsheet + "us" + slash;
            String nome_specsheet_us = vist_scheda_pdf;

            String specsheet_us = path_specsheet_us + nome_specsheet_us;

            if (nome_specsheet_us.equals("")) {
                return "";
            }

            return specsheet_us;

        } else {
            throw new Exception("Tipo risorsa NON gestito");
        }

    }

    private String of_get_path_modello(String slash) {
        return "fileresources" + slash + "models" + slash;
    }

    public String of_relpath_resource_MOD3D_EASM(String nome_modello_3D, String cdclas_a, String cdvistelet) {
        String path_3D = of_get_path_3D();
        String easm = path_3D + nome_modello_3D + ".EASM";
        if (nome_modello_3D.equals("")) {
            return "";
        }
        if (isListinoLed(cdclas_a)) {
            easm = path_3D + nome_modello_3D + cdvistelet + ".EASM";
        }
        return easm;
    }

    private String of_get_path_3D() {
        String slash = System.getProperty("file.separator");
        return of_get_path_modello(slash) + "3D" + slash;
    }

    private String of_get_path_3DBLOCCHI_LED() {
        String slash = System.getProperty("file.separator");
        return of_get_path_modello(slash) + "3D" + slash + "blocchi_LED" + slash;
    }

    public String of_relpath_resource_MOD3D_IGS(String nome_modello_3D, String cdclas_a, String cdvistelet) {
        String path_3D = of_get_path_3D();
        String igs = path_3D + nome_modello_3D + ".zip";
        if (nome_modello_3D.equals("")) {
            return "";
        }
        if (isListinoLed(cdclas_a)) {
            igs = path_3D + nome_modello_3D + cdvistelet + ".zip";
        }
        return igs;
    }

    private static boolean isListinoLed(String cdclas_a) {
        return cdclas_a.equals(CDCLAS_A_LL)
                || cdclas_a.equals(CDCLAS_A_ULL);
    }
    
    public String of_getUrl_risorsa_esistente(String url_risorsa, String lang) throws Exception {

      if (1 == 1) throw new Exception("ATTENZIONE!!! questo metodo può essere richiamato solo in E-progen");
      
      return "";

    }
    
    
    public Str_risorsa_anticipo of_getRisorsa_anticipo(String url_risorse, String tiporisorsa, String nome_modello, String cdvistelet, long tkc, String lang, String cdling_ep_lingua) throws Exception {
    
      if (1 == 1) throw new Exception("ATTENZIONE!!! questo metodo può essere richiamato solo in E-progen");
      
      return null;

    }
    
    
    public static String TECHSHEET = "TECHSHEET";
    public static String MOD3D_IGS = "MOD3D_IGS";
//    public static String MOD3D_EPRT = "MOD3D_EPRT";
    public static String MOD3D_EASM = "MOD3D_EASM";
    public static String MOD2D_DWG_PO = "MOD2D_DWG_PO";
    public static String MOD2D_DWG_CM = "MOD2D_DWG_CM";
    public static String IMAGEPROD_HI_MULTIF = "IMAGEPROD_HI_MULTIF";
    public static String IMAGEPROD_HI_SINGF = "IMAGEPROD_HI_SINGF";
    public static String IMAGEPROD_LO_MULTIF = "IMAGEPROD_LO_MULTIF";
    public static String IMAGEPROD_LO_SINGF = "IMAGEPROD_LO_SINGF";
    public static String CERT = "CERT";
    public static String SPECSHEET_IT = "SPECSHEET_IT";
    public static String SPECSHEET_US = "SPECSHEET_US";
    public static String MOD3DBLOCCHI_LED_IGS = "MOD3DBLOCCHI_LED_IGS";
    public static String MOD3DBLOCCHI_LED_EASM = "MOD3DBLOCCHI_LED_EASM";
    public static String SPECSHEET_GEN = "SPECSHEET_GEN";
    public static String SPECSHEET_SPEC = "SPECSHEET_SPEC";

    public static String TPRESANTICIPO_3DBLOCLED = "3DBLOCLED";
    public static String TPRESANTICIPO_SSENEXIST = "SSENEXIST";
    public static String TPRESANTICIPO_SGENEXIST = "SGENEXIST";
    public static String TPRESANTICIPO_SSSGEXIST = "SSSGEXIST";
    public static String TPRESANTICIPO_SSSGENEXIST = "SSSGENEXIST";    

    private static final String CDCLAS_A_LL = "LL";
    private static final String CDCLAS_A_ULL = "ULL";
    private static final String CDCLAS_A_UL = "UL";
    private static final String CDCLAS_A_LOU = "LOU";

}
