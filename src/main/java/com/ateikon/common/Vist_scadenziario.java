/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ateikon.common;

import com.ateikon.structure.Str_controllo_scadenziario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 *
 * @author pdefranceschi
 */
public class Vist_scadenziario extends com.ateikon.common.Atk_sql {

    public Vist_scadenziario() {
        
        super();
    }
    
    public ResultSet getScadenziario( Str_controllo_scadenziario astr
                                           , boolean is_count
                                           , String order_by
                                                               ) throws Exception {

         return getScadenziario( astr
                               , is_count
                               , order_by
                               , ""
                               , false
                               );

    }
    
    public ResultSet getScadenziario( Str_controllo_scadenziario astr
                                           , boolean is_count
                                           , String order_by
                                           , boolean fg_view_rate_ori
                                                               ) throws Exception {

         return getScadenziario( astr
                               , is_count
                               , order_by
                               , ""
                               , fg_view_rate_ori
                               );

    }

    public ResultSet getScadenziario( Str_controllo_scadenziario astr
                                           , boolean is_count
                                           , String order_by
                                           , String group_by
                                                               ) throws Exception {

         return getScadenziario( astr
                               , is_count
                               , order_by
                               , group_by
                               , false
                               );

    }

    public ResultSet getScadenziario( Str_controllo_scadenziario astr
                                           , boolean is_count
                                           , String order_by
                                           , String group_by
                                           , boolean fg_view_rate_ori
                                                               ) throws Exception {
        /**********************/
        //     NOTA:
        //     modificare questa classe nel portale implica un allineamento della stessa classe in eprogen
        //     e viceversa modificare questa classe nel eprogen implica un allineamento della stessa classe nel portale
        //
        /*********************/



        int               ind     = 0;
        int               tot_rec = 0;
        PreparedStatement pstm    = null;
        ResultSet         rs      = null;

        Valueste valueste = new Valueste();
        Formpaga formpaga = new Formpaga();
        
        setProfilo((Atk_sql) formpaga);
        setProfilo((Atk_sql) valueste);

        String ls_tkclie = astr.tkclie;

        if (!s_tkclie.equals("")) ls_tkclie = s_tkclie;

        if (ls_tkclie.equals("") && !astr.cdclie_m.equals("")){

            l_query  = "";
            l_query += " select tkclie        \n";
            l_query += "   from pgmr.archclie \n";
            l_query += "  where cdclie_m = ?  \n";
            l_query += "    and cdazie   = ?  \n";

            pstm = m_connection.prepareStatement(l_query);

            ind = 1;
            pstm.setString(ind++, astr.cdclie_m);
            pstm.setString(ind++, cdazie  );

            rs = pstm.executeQuery();

            if (rs!=null && rs.next()){

                tot_rec += 1;
                ind = 0;
                if (rs.getObject(++ind)!=null)  ls_tkclie = rs.getString(ind);

            }
            pstm.close();
        }

        if (astr.cdagen.equals("") && !astr.cdagen_m.equals("")){

            l_query  = "";
            l_query += " select cdagen        \n";
            l_query += "   from pgmr.archagen \n";
            l_query += "  where cdagen_m = ?  \n";
            l_query += "    and cdazie   = ?  \n";

            pstm = m_connection.prepareStatement(l_query);

            ind = 1;
            pstm.setString(ind++, astr.cdagen_m);
            pstm.setString(ind++, cdazie  );

            rs = pstm.executeQuery();

            if (rs!=null && rs.next()){

                tot_rec += 1;
                ind = 0;
                if (rs.getObject(++ind)!=null)  astr.cdagen = rs.getString(ind);

            }
            pstm.close();
        }

        String ls_cdclie_m = par_like(astr.cdclie_m);
        String ls_ragcog = par_like(astr.ragcog);
        String ls_cdagen_m = par_like(astr.cdagen_m);
        String ls_dsagen = par_like(astr.dsagen);
        String ls_cdprov   = par_like(astr.cdprov   );
        String ls_cap      = par_like(astr.cap      );
        String ls_comune   = par_like(astr.comune   );

        if (order_by.equals("")) order_by = "ente.ragcog";
        if (is_count) order_by = "";
        if (!group_by.equals("")) is_count = true;

        
        if (is_count){

            l_query   = "";
            l_query  += "   select count(*)  \n";
            l_query  += "         , sum (vist_scadenziario.imp_dare)     as tot_importo_rata                                              \n";
            l_query  += "         , sum ((case when (formpaga.tpgtpg = 'B' and ( vist_scadenziario.dtscad + cast(formpaga.pgrisc as integer)) >= "+sysdate+" )                                                                                                 \n";
            l_query  += "                 then                                                                                                 \n";
            l_query  += "                  0                                                                            \n";
            l_query  += "                 else                                                                                                 \n";
            l_query  += "                  vist_scadenziario.imp_avere                                                                         \n";
            l_query  += "                 end))     as tot_pagamento                                                    \n";
            l_query  += "         , sum (vist_scadenziario.imp_dare_c)     as tot_importo_rata_c                                              \n";
            l_query  += "         , sum ((case when (formpaga.tpgtpg = 'B' and ( vist_scadenziario.dtscad + cast(formpaga.pgrisc as integer)) >= "+sysdate+" )                                                                                                 \n";
            l_query  += "                 then                                                                                                 \n";
            l_query  += "                  0                                                                            \n";
            l_query  += "                 else                                                                                                 \n";
            l_query  += "                  vist_scadenziario.imp_avere_c                                                                         \n";
            l_query  += "                 end))     as tot_pagamento_c                                                    \n";
            if (!group_by.equals("")){
               l_query  += "         , " + group_by + "                                                    \n";
            }

        }else {

            l_query   = "";
            l_query  += "    select vist_scadenziario.*                                                                               \n";
            l_query  += "         , vist_scadenziario.imp_dare           as importo_rata                                              \n";
            l_query  += "         , (case when (formpaga.tpgtpg = 'B' and ( vist_scadenziario.dtscad + cast(formpaga.pgrisc as integer)) >= "+sysdate+" )                                                                                                 \n";
            l_query  += "            then                                                                                                 \n";
            l_query  += "             0                                                                            \n";
            l_query  += "            else                                                                                                 \n";
            l_query  += "             vist_scadenziario.imp_avere                                                                         \n";
            l_query  += "            end)  as pagamento                                                                                                \n";
            l_query  += "         , vist_scadenziario.imp_dare_c           as importo_rata_c                                              \n";
            l_query  += "         , (case when (formpaga.tpgtpg = 'B' and ( vist_scadenziario.dtscad + cast(formpaga.pgrisc as integer)) >= "+sysdate+" )                                                                                                 \n";
            l_query  += "            then                                                                                                 \n";
            l_query  += "             0                                                                            \n";
            l_query  += "            else                                                                                                 \n";
            l_query  += "             vist_scadenziario.imp_avere_c                                                                         \n";
            l_query  += "            end)  as pagamento_c                                                                                                \n";
            l_query  += "         , vist_scadenziario.tkclie       as cd                                                              \n";
            l_query  += "         , clie.cdclie_m            as cd_m                                                                  \n";
            l_query  += "         , ente.ragcog              as ds                                                                    \n";
            l_query  += "         , clie.cdagen                                                                                       \n";
            l_query  += "         , agen.cdagen_m                                                                                     \n";
            l_query  += "         , agen.dsagen                                                                                       \n";
            l_query  += "         , "+ formpaga.of_descr_lingua(astr.cdling, "formpaga", "pgdesc") +" as pgdesc                                                                                   \n";
            l_query  += "         , formpaga.pgrisc                                                                                   \n";
            l_query  += "         , vist_scadenziario.dtscad                                                                          \n";
            l_query  += "         , "+ valueste.of_descr_lingua(astr.cdling, "vest", "vadesc") +"  as vadesc                                                                                                \n";
            l_query  += "         , ( vist_scadenziario.dtscad + cast(formpaga.pgrisc as integer)) as dtscad_gg_risc                    \n";
            l_query  += "         , clie.cdresp                                                                                         \n";
            //l_query  += "         , azie.cdazie                                                                                       \n";
            //l_query  += "         , azie.tarags                                                                                       \n";
            //l_query  += "         , dipa.cddipa                                                                                       \n";
            //l_query  += "         , dipa.dsdipa                                                                                       \n";
            //l_query  += "         , clac.cdclac                                                                                       \n";
            //l_query  += "         , clac.dsclac                                                                                       \n";
        }


        if (is_oracle){
           l_query  += "      from pgmr.vist_scadenziario  vist_scadenziario                                                         \n";
           l_query  += "         , pgmr.archenti           ente                                                                      \n";
           l_query  += "         , pgmr.archclie           clie                                                                      \n";
           l_query  += "         , pgmr.archagen           agen                                                                      \n";
           l_query  += "         , pgmr.formpaga           formpaga                                                                  \n";
           //l_query  += "         , pgmr.azienda            azie                                                                      \n";
           //l_query  += "         , pgmr.dipartimenti       dipa                                                                      \n";
           //l_query  += "         , pgmr.clieclass          clac                                                                      \n";
           l_query  += "         , pgmr.unitalocali       ulsl                                                  \n";
           l_query  += "         , pgmr.enteuniloc        elsl                                                  \n";
           l_query  += "         , pgmr.nazioni           nasl                                                  \n";
           l_query  += "         , pgmr.valueste          vest                                                  \n";
           l_query  += "     where vist_scadenziario.tkclie       = clie.tkclie                                                      \n";
           l_query  += "       and clie.cdagen                    = agen.cdagen  (+)                                                 \n";                                                        
           l_query  += "       and vist_scadenziario.pgcodi       = formpaga.pgcodi (+)                                              \n";
           //l_query  += "       and vist_scadenziario.cdazie       = azie.cdazie                                                      \n";
           //l_query  += "       and vist_scadenziario.cddipa       = dipa.cddipa                                                      \n";
           //l_query  += "       and clie.cdclac                    = clac.cdclac                                                      \n";
           l_query  += "       and clie.cdente                    = ente.cdente                                                      \n";
           l_query  += "       and ente.cdente = elsl.cdente (+)                                                \n";
           l_query  += "       and elsl.cdunil = ulsl.cdunil (+)                                                \n";
           l_query  += "       and elsl.fseleg = 'S'                                                            \n";
           l_query  += "       and ulsl.cdnazi   = nasl.cdnazi (+)                                              \n";
           l_query  += "       and vist_scadenziario.vacodi_dare       = vest.vacodi (+)                        \n";
           if(!fg_view_rate_ori && !astr.fgall_scad.equals("S")){ //avendramin20110511
               l_query  += "       and ((vist_scadenziario.imp_dare - vist_scadenziario.imp_avere) <> 0                                   \n";
               l_query  += "             or (formpaga.tpgtpg = 'B' and ( vist_scadenziario.dtscad + formpaga.pgrisc) >= "+sysdate+" ))    \n";
           }
           l_query  += "       and vist_scadenziario.fg_valid = 'S'                                                            \n";
        } else if (is_sybase) {
           l_query  += "      from                                                                              \n";
           //l_query  += "         , pgmr.azienda           azie                                                                       \n";
           //l_query  += "         , pgmr.dipartimenti      dipa                                                                       \n";
           //l_query  += "         , pgmr.clieclass         clac                                                                       \n";
           l_query  += "           {oj               pgmr.vist_scadenziario vist_scadenziario                                        \n";
           l_query  += "            left outer join  pgmr.formpaga  formpaga  on  vist_scadenziario.pgcodi = formpaga.pgcodi         \n";
           l_query  += "            left outer join  pgmr.valueste  vest      on  vist_scadenziario.vacodi_dare = vest.vacodi        \n";
           l_query  += "           }                                                                                                 \n";
           l_query  += "         , {oj               pgmr.archclie    clie                                                           \n";
           l_query  += "            left outer join  pgmr.archagen    agen  on  clie.cdagen  = agen.cdagen                           \n";
           l_query  += "           }                                                                                                 \n";
           l_query  += "         , {oj               pgmr.archenti    ente                                                           \n";
           l_query  += "              left outer join  pgmr.enteuniloc  elsl  on  ente.cdente = elsl.cdente                               \n";
           l_query  += "              left outer join  pgmr.unitalocali ulsl  on  elsl.cdunil = ulsl.cdunil                            \n";
           l_query  += "                   left outer join  pgmr.nazioni     nasl  on  ulsl.cdnazi = nasl.cdnazi                            \n";
           l_query  += "           }                                                                                                 \n";
           l_query  += "     where vist_scadenziario.tkclie       = clie.tkclie                                                      \n";
           //l_query  += "       and vist_scadenziario.cdazie       = azie.cdazie                                                      \n";
           //l_query  += "       and vist_scadenziario.cddipa       = dipa.cddipa                                                      \n";
           //l_query  += "       and clie.cdclac                    = clac.cdclac                                                      \n";
           l_query  += "       and clie.cdente                    = ente.cdente                                                      \n";
           l_query  += "       and elsl.fseleg = 'S'                                                            \n";
           if(!fg_view_rate_ori && !astr.fgall_scad.equals("S")){ //avendramin20110511
               l_query  += "       and ((vist_scadenziario.imp_dare - vist_scadenziario.imp_avere) <> 0                                   \n";
               l_query  += "             or (formpaga.tpgtpg = 'B' and ( vist_scadenziario.dtscad + cast(formpaga.pgrisc as integer)) >= "+sysdate+" ) and vist_scadenziario.fgvalid_riba = 'S')              \n";
           }
           l_query  += "       and vist_scadenziario.fg_valid = 'S'                                                            \n";
        } else {
            throw new Exception("DB non supportato");
        }

        if (!s_cdresp.equals("")){
            l_query  += " and clie.cdresp = '"+s_cdresp+"' \n";
        }

        if (!astr.cdresp.equals("")){
            l_query  += " and clie.cdresp = '"+astr.cdresp+"'   \n";
        }

        if (!s_cdcapoarea.equals("")){
		        l_query  += " and clie.cdagen in ("+par_ca_agenti(s_cdcapoarea)+")\n";
        }else if (!s_cdagen.equals("")){
            l_query  += "  and (clie.cdagen = '"+s_cdagen+"' or clie.cdsuba = '"+s_cdagen+"')  \n";
        }

        if (!s_cdispe.equals("")){
            l_query  += " and clie.cdispe = '"+s_cdispe+"' \n";
        }
        
        if (!astr.cdispe.equals("")){
            l_query  += " and clie.cdispe = '"+astr.cdispe+"'   \n";
        }
        //Fine -- avendramin20080429
        if (!astr.cdagen.equals("")){
            l_query  += "     and (clie.cdagen = '"+astr.cdagen+"' or clie.cdsuba = '"+astr.cdagen+"') \n";
        } else {

            if (!ls_cdagen_m.equals("")){
                l_query  += " and upper(agen.cdagen_m) like '"+ls_cdagen_m+"'    \n";
            }
            if (!ls_dsagen.equals("")){
                l_query  += " and upper(agen.dsagen) like '"+ls_dsagen+"'        \n";
            }
        }

        if (!ls_tkclie.equals("")){
            l_query  += " and clie.tkclie = '"+ls_tkclie+"'       \n";
        }else {

            if (!ls_cdclie_m.equals("")){
                l_query  += " and upper(clie.cdclie_m) like '"+ls_cdclie_m+"'        \n";
            }
            if (!ls_ragcog.equals("")){
                l_query  += "  and (upper(ente.ragcog) like '"+ls_ragcog+"'  \n";
                l_query  += "       or upper(clie.cliacr) like '"+ls_ragcog+"')  \n";
            }
        }

			     //avendramin20110701 - I
			     if (s_arr_cdunil != null && s_arr_cdunil.length > 0){
          l_query  += "   and 1 = 2      \n";
			     }
			     //avendramin20110701 - F

        if (astr.dtscad_da != null){
            l_query  += " and vist_scadenziario.dtscad >= ?        \n";
        }
        if (astr.dtscad_a != null){
            l_query  += " and vist_scadenziario.dtscad <= ?        \n";
        }
        if (astr.fginsoluto.equals("S")){
            l_query  += " and trim(upper(vist_scadenziario.pgcodi)) = 'I'        \n";
        }
        if (astr.fgscaduto.equals("S")){
            if (is_oracle){
               l_query  += " and (trim(upper(vist_scadenziario.pgcodi)) = 'I' or (vist_scadenziario.dtscad + formpaga.pgrisc) < "+sysdate+")        \n";
            } else if (is_sybase) {
               l_query  += " and (trim(upper(vist_scadenziario.pgcodi))= 'I' or (vist_scadenziario.dtscad + cast(formpaga.pgrisc as integer)) < "+sysdate+")        \n";
            } else {
                throw new Exception("DB non supportato");
            }
        }
        if (astr.fgscaduto.equals("A")){ //RPI9.0621 Solo a scadere senza i gia' scaduti
            if (is_oracle){
               l_query  += " and (trim(upper(vist_scadenziario.pgcodi)) <> 'I' and (vist_scadenziario.dtscad + formpaga.pgrisc) >= "+sysdate+")        \n";
            } else if (is_sybase) {
               l_query  += " and (trim(upper(vist_scadenziario.pgcodi))<> 'I' and (vist_scadenziario.dtscad + cast(formpaga.pgrisc as integer)) >= "+sysdate+")        \n";
            } else {
                throw new Exception("DB non supportato");
            }
        }

        if (astr.fgscaduto.equals("DA1GG")){
            if (is_oracle){
               l_query  += " and (trim(upper(vist_scadenziario.pgcodi)) = 'I' or (vist_scadenziario.dtscad + formpaga.pgrisc) < ?)        \n";
            } else if (is_sybase) {
               l_query  += " and (trim(upper(vist_scadenziario.pgcodi))= 'I' or (vist_scadenziario.dtscad + cast(formpaga.pgrisc as integer)) < ?)        \n";
            } else {
                throw new Exception("DB non supportato");
            }
        }
        if (astr.fgscaduto.equals("DA2GGLAV")){
            if (is_sybase) {
               l_query  += " and (trim(upper(vist_scadenziario.pgcodi))= 'I' or vist_scadenziario.dtscadda2gglav <= ?)        \n";
            } else {
                throw new Exception("DB non supportato");
            }
        }


        if (!astr.cdzcom.equals("")){
            l_query  += "      and clie.cdzcom = '"+astr.cdzcom+"'               \n";
        }
        if (!astr.cdnazi.equals("")){
            l_query  += "      and ulsl.cdnazi = '"+astr.cdnazi+"'               \n";
        }
        if (!ls_cdprov.equals("")){
            l_query  += "      and ulsl.cdprov like '"+ls_cdprov+"'               \n";
        }
        if (!ls_cap.equals("")){
            l_query  += "      and ulsl.cap like '"+ls_cap+"'               \n";
        }
        if (!ls_comune.equals("")){
            l_query  += "      and ulsl.comune like '"+ls_comune+"'               \n";
        }
        if (astr.fgnocliebloc.equals("S")){
            l_query  += "      and clie.fnoord <> 'S'               \n";
        }
        if (!astr.pgcodi.equals("")){
            l_query  += "      and formpaga.pgcodi = '"+astr.pgcodi+"'            \n";
        }
        if (astr.fgnotifscad.equals("S")){
            l_query  += "      and formpaga.fgnotifscad = 'S'               \n";
        } else if (astr.fgnotifscad.equals("N")){
            l_query  += "      and formpaga.fgnotifscad = 'N'               \n";
        }

        if (astr.fgdtinc.equals("S")){
            l_query  += " and vist_scadenziario.dtinc is not null     \n";
        } else if (astr.fgdtinc.equals("N")){
            l_query  += " and vist_scadenziario.dtinc is null     \n";
        }
        
        if (!astr.descr_prot_iva.equals("")){
            l_query  += " and vist_scadenziario.descr_prot_iva = '"+astr.descr_prot_iva+"'   \n";
        }
        
        if (astr.dtprot != null){
            l_query  += " and vist_scadenziario.dtprot = ?        \n";
        }

        if (astr.conpro != 0){
            l_query  += " and vist_scadenziario.conpro = ?        \n";
        }
        
        if(fg_view_rate_ori){
             l_query  += " and (vist_scadenziario.nrriga_ori = 0 OR vist_scadenziario.nrriga_ori is null)   \n"; //avendramin20110527 -- trasformato da nrriga_ori = null a nrriga_ori is null
        }

        //avendramin20110523 - I
        if (astr.dtprot_da != null){
            l_query  += " and vist_scadenziario.dtprot >= ?        \n";
        }

        if (astr.dtprot_a != null){
            l_query  += " and vist_scadenziario.dtprot <= ?        \n";
        }
        //avendramin20110523 - F

        //avendramin20110523 - I
	       if (astr.fgno_accrediti.equals("S")){
            l_query  += " and (    vist_scadenziario.cdsegno is null or vist_scadenziario.cdsegno <> 'A'        \n";
            l_query  += "     )                                            \n";
        }
        //avendramin20110523 - F

        //avendramin20110527 - I
	       if (astr.tkscad > 0){
            l_query  += " and vist_scadenziario.tkscad = "+ astr.tkscad +"       \n";
        }
	       if (astr.nrrata > 0){
            l_query  += " and vist_scadenziario.nrrata = "+ astr.nrrata +"       \n";
        }
	       if (astr.nrriga_ori > 0){
            l_query  += " and vist_scadenziario.nrriga_ori = "+ astr.nrriga_ori +"       \n";
        }
        //avendramin20110527 - F

	       if (astr.fg_solo_scadenze_negative.equals("S")){
            if (is_oracle){
                throw new Exception("DB non supportato");
            } else if (is_sybase) {
                l_query  += " and ((importo_rata - pagamento) < 0 )       \n";
            } else {
                throw new Exception("DB non supportato");
            }
        }
           
        if (astr.fg_null_cdresp.equals("S")){
            l_query  += " and clie.cdresp is null                                      \n";
        }   
        
        if (!group_by.equals("")){
            l_query  += " group by "+ of_normalize_group_by(group_by) +" \n";
        }
        if (!order_by.equals("")){
            l_query  += " order by "+order_by+" \n";
        }
        
        //System.out.println("\n"+ l_query +"\n");

        pstm = setQuery_ric(l_query);

        ind = 1;
        if (astr.dtscad_da != null){
            pstm.setTimestamp(ind, astr.dtscad_da); ind += 1;
        }
        if (astr.dtscad_a != null){
            pstm.setTimestamp(ind, astr.dtscad_a); ind += 1;
        }
        if (astr.fgscaduto.equals("DA1GG")){
            pstm.setTimestamp(ind, of_getOggi()); ind += 1;
        }
        if (astr.fgscaduto.equals("DA2GGLAV")){
            pstm.setTimestamp(ind, of_getOggi()); ind += 1;
        }
        if (astr.dtprot != null){
            pstm.setTimestamp(ind, astr.dtprot); ind += 1;
        }
        if (astr.conpro != 0){
            pstm.setLong(ind, astr.conpro); ind += 1;
        }
        //avendramin20110523 - I
        if (astr.dtprot_da != null){
            pstm.setTimestamp(ind, astr.dtprot_da); ind += 1;
        }
        if (astr.dtprot_a != null){
            pstm.setTimestamp(ind, astr.dtprot_a); ind += 1;
        }
        //avendramin20110523 - F

        rs = pstm.executeQuery();

        
        valueste.close();
        formpaga.close();

        return rs;
    }


    public String of_normalize_group_by (String group_by){

      String ls_group_by = "";
      String app_group_by = group_by;

      while (!app_group_by.trim().equals("") && app_group_by.indexOf("as ") >= 0 ){
        
        int idx_as_f    = app_group_by.indexOf("as ") + 3;

        app_group_by = app_group_by.substring(idx_as_f);
        
        int idx_comma = app_group_by.indexOf(",");

        if (!ls_group_by.equals("")) ls_group_by += ", ";
        if (idx_comma >= 0){
          ls_group_by += app_group_by.substring(0, idx_comma);
          app_group_by = app_group_by.substring(idx_comma); 
        } else {
          ls_group_by += app_group_by;
          app_group_by = "";
        }

        
      }
      
      return ls_group_by;
    }
}
