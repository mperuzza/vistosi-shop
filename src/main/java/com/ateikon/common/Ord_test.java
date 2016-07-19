package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class Ord_test extends Atk_sql {

    public Ord_test() {
        
        super();
    }




    public ResultSet getKey(long tkordi) throws Exception {
        
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;


        l_query   = "";
		l_query  += "  SELECT ord_test.tkordi                    \n";
		l_query  += "       , ord_test.anno                      \n";
		l_query  += "       , ord_test.numord                    \n";
		l_query  += "       , ord_test.cdtorve                   \n";
		l_query  += "       , ord_test.dtordi                    \n";
		l_query  += "       , ord_test.dtfval                    \n";
		l_query  += "       , ord_test.dtcons                    \n";
		l_query  += "       , ord_test.cdrifo                    \n";
		l_query  += "       , ord_test.dtrior                    \n";
		l_query  += "       , ord_test.cdnsri                    \n";
		l_query  += "       , ord_test.dtnsri                    \n";
		l_query  += "       , ord_test.cdagen                    \n";
		l_query  += "       , ord_test.cdazie                    \n";
		l_query  += "       , ord_test.cdstato                   \n";
		l_query  += "       , ord_test.cdpagame                  \n";
		l_query  += "       , archclie.cdclie_m                  \n";
		l_query  += "       , archenti.ragcog                    \n";
		l_query  += "       , uloc_dm.indiri  as indiri_dm       \n";
		l_query  += "       , uloc_dm.cap     as cap_dm          \n";
		l_query  += "       , uloc_dm.comune  as comune_dm       \n";
		l_query  += "       , uloc_dm.cdprov  as cdprov_dm       \n";
		l_query  += "       , uloc_dm.ntelef  as ntelef_dm       \n";
		l_query  += "       , uloc_dm.nfaxsi  as nfaxsi_dm       \n";
		l_query  += "       , provdm.cdprov_m  as cdprov_m_dm    \n";
		l_query  += "       , uloc_sl.indiri  as indiri_sl       \n";
		l_query  += "       , uloc_sl.cap     as cap_sl          \n";
		l_query  += "       , uloc_sl.comune  as comune_sl       \n";
		l_query  += "       , uloc_sl.cdprov  as cdprov_sl       \n";
		l_query  += "       , uloc_sl.ntelef  as ntelef_sl       \n";
		l_query  += "       , uloc_sl.nfaxsi  as nfaxsi_sl       \n";
		l_query  += "       , provsl.cdprov_m  as cdprov_m_sl    \n";
		l_query  += "       , ord_test.ragcog_de                 \n";
		l_query  += "       , ord_test.indiri_de                 \n";
		l_query  += "       , ord_test.cap_de                    \n";
		l_query  += "       , ord_test.comune_de                 \n";
		l_query  += "       , ord_test.cdprov_de                 \n";
		l_query  += "       , provde.cdprov_m  as cdprov_m_de    \n";
		l_query  += "       , ord_test.tkclie                    \n";
		l_query  += "       , ord_test.fgevaso                   \n";
		l_query  += "       , ord_test.cdordi                    \n";
		l_query  += "       , archclie.cdclie_m                  \n";
		l_query  += "       , archclie.cliacr                    \n";
		l_query  += "       , archclie.fnospes                   \n";
		l_query  += "       , torv.cdtorv_m                      \n";
		l_query  += "       , torv.dstorv                        \n";
		l_query  += "       , fp.pgdesc                          \n";
		l_query  += "       , dp.dpdesc                          \n";
		l_query  += "       , decp.dsdpag                        \n";
		l_query  += "       , ord_test.ffinme                    \n";
		l_query  += "       , agen.cdagen                        \n";
		l_query  += "       , agen.cdagen_m                      \n";
		l_query  += "       , agen.dsagen                        \n";
		l_query  += "   FROM {oj pgmr.ord_test         ord_test                                                     \n";
		l_query  += "        left outer join pgmr.ut_art_tipordve  torv     on ord_test.cdtorve   = torv.cdtorv     \n";
		l_query  += "                                                      and ord_test.cdazie    = torv.cdazie     \n";
		l_query  += "        left outer join pgmr.archclie         archclie on ord_test.tkclie    = archclie.tkclie \n";
		l_query  += "                                                      and ord_test.cdazie    = archclie.cdazie \n";
		l_query  += "        left outer join pgmr.archenti         archenti on ord_test.cdentc    = archenti.cdente \n";
		l_query  += "        left outer join pgmr.archagen         agen     on archclie.cdagen    = agen.cdagen     \n";
		l_query  += "                                                      and archclie.cdazie    = agen.cdazie     \n";
		l_query  += "        left outer join  pgmr.unitalocali     uloc_dm  on ord_test.cduldm    =  uloc_dm.cdunil \n";
		l_query  += "        left outer join  pgmr.enteuniloc      enul     on ord_test.cdentc    =  enul.cdente    \n";
		l_query  += "                                                      and 'S' = enul.fseleg                    \n";
		l_query  += "        left outer join  pgmr.unitalocali     uloc_sl  on enul.cdunil        =  uloc_sl.cdunil \n";
		l_query  += "        left outer join  pgmr.province        provdm   on uloc_dm.cdprov     =  provdm.cdprov  \n";
		l_query  += "        left outer join  pgmr.province        provsl   on uloc_sl.cdprov     =  provsl.cdprov  \n";
		l_query  += "        left outer join  pgmr.province        provde   on ord_test.cdprov_de =  provde.cdprov  \n";
		l_query  += "        left outer join  pgmr.formpaga        fp       on ord_test.pgcodi    =  fp.pgcodi      \n";
		l_query  += "        left outer join  pgmr.dilapaga        dp       on ord_test.dpcodi    =  dp.dpcodi      \n";
		l_query  += "        left outer join  pgmr.decorpag        decp     on ord_test.cddpag    =  decp.cddpag    \n";
		l_query  += "          }                                                                                    \n";
		l_query  += "   WHERE ord_test.tkordi = ?                     \n";
        if (!s_cdcapoarea.equals("")){
		    l_query  += " and archclie.cdagen in ("+par_ca_agenti(s_cdcapoarea)+") \n";
        }else if (!s_cdagen.equals("")){
            l_query  += " and archclie.cdagen = '"+s_cdagen+"' \n";
        }else if (!s_tkclie.equals("")){
            l_query  += " and archclie.tkclie = '"+s_tkclie+"' \n";
            l_query  += " and archclie.cdazie = '"+cdazie+"' \n";
        }

		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setLong( ind, tkordi); ind += 1;

        rs = pstm.executeQuery();


        return rs;





    }


    public ResultSet getKey_m(String cdordi) throws Exception {
        
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;


        l_query   = "";
		l_query  += "  SELECT ord_test.tkordi                 \n";
		l_query  += "    FROM pgmr.ord_test   ord_test        \n";
		l_query  += "   WHERE ord_test.cdordi = '"+cdordi+"'  \n";
		l_query  += "     and ord_test.cdazie = '"+cdazie+"'  \n";

        long tkordi = sql_long(l_query);

        return getKey(tkordi);

    }









    /***


    */

    public ResultSet search ( String    fgevaso
                            , long      anno
                            , String    cdordi
                            , String    cdrifo
                            , Timestamp dtordi_da
                            , Timestamp dtordi_a
                            , Timestamp dtcons_da
                            , Timestamp dtcons_a
                            , String    tkclie
                            , String    cdclie_m
                            , String    ragcog
                            , String    cdarti
                            , String    cdagen

                            , boolean is_count 
                            , String  order_by
                                                ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        if (!s_tkclie.equals("")) tkclie = s_tkclie;

        if (tkclie.equals("") && !cdclie_m.equals("")){
            
            l_query  = "";
            l_query += " select tkclie        \n";
            l_query += "   from pgmr.archclie \n";
            l_query += "  where cdclie_m = ?  \n";
            l_query += "    and cdazie   = ?  \n";
    
            pstm = m_connection.prepareStatement(l_query);
    
            ind = 1;
            pstm.setString(ind++, cdclie_m);
            pstm.setString(ind++, cdazie  );
    
            rs = pstm.executeQuery();
    
            if (rs!=null && rs.next()){
                
                tot_rec += 1;
                ind = 0;
                if (rs.getObject(++ind)!=null)  tkclie = rs.getString(ind);
    
            }
            pstm.close();
        }


        cdordi = par_like(cdordi);
        cdrifo = par_like(cdrifo);
        cdclie_m = par_like(cdclie_m);
        ragcog = par_like(ragcog);




        if (order_by.equals("")) order_by = "ordt.dtordi desc, ordt.cdordi ";
        if (is_count) order_by = "";

        if (is_count){
            
            l_query   = "";
            l_query  += "   select count(1)  \n";
            l_query  += "        , sum( ( select sum(iordp.importonettoriga)                             \n";
            l_query  += "                   from pgmr.ord_positito iordp                                 \n";
            l_query  += "                  where iordp.tkordi = ordt.tkordi                              \n";
            if (!cdarti.equals("")){
            l_query  += "                  and iordp.cdarti = '"+cdarti+"'                           \n";
            }
            l_query  += "                                                      ) ) as tot_impnetto       \n";

            if (fgevaso.equals("N")){
                
                l_query  += "        , sum( ( select sum(iordp.importonettoriga/iordp.qtatot * (iordp.qtatot - iordp.qtacons - iordp.qtcons_s))  \n";
                l_query  += "                   from pgmr.ord_positito iordp                                 \n";
                l_query  += "                  where iordp.tkordi = ordt.tkordi                              \n";
                l_query  += "                    and iordp.fgsaldo = 'N'                                     \n";
                l_query  += "                    and iordp.qtatot > 0                                        \n";
                
                if (!cdarti.equals("")){
                l_query  += "                  and iordp.cdarti = '"+cdarti+"'                           \n";
                }
                l_query  += "                                                      ) ) as tot_impnetto_non_evaso       \n";
            }



        }else {
            

            l_query   = "";
            l_query  += "  SELECT ordt.cdazie                                                     \n";
            l_query  += "       , ordt.tkclie                                                     \n";
            l_query  += "       , clie.cdclie_m                                                   \n";
            l_query  += "       , ente.ragcog                                                     \n";
            l_query  += "       , ordt.dtcons                                                     \n";
            l_query  += "       , ordt.dtordi                                                     \n";
            l_query  += "       , ordt.cdordi                                                     \n";
            l_query  += "       , ordt.cdrifo                                                     \n";
            l_query  += "       , ordt.tkordi                                                     \n";
            l_query  += "       , ordt.cdpagame                                                   \n";
            l_query  += "       , agen.cdagen                                                     \n";
            l_query  += "       , agen.cdagen_m                                                   \n";
            l_query  += "       , agen.dsagen                                                     \n";
            l_query  += "       , (select sum(iordp.importonettoriga)                             \n";
            l_query  += "            from pgmr.ord_positito iordp                                 \n";
            l_query  += "           where iordp.tkordi = ordt.tkordi                              \n";
            if (!cdarti.equals("")){
            l_query  += "                  and iordp.cdarti = '"+cdarti+"'                           \n";
            }
            l_query  += "                                             ) as totimportonettoriga    \n";

            if (fgevaso.equals("N")){
                
                l_query  += "       , (select sum(iordp.importonettoriga/iordp.qtatot * (iordp.qtatot - iordp.qtacons - iordp.qtcons_s))  \n";
                l_query  += "            from pgmr.ord_positito iordp                                   \n";
                l_query  += "           where iordp.tkordi = ordt.tkordi                                \n";
                l_query  += "             and iordp.fgsaldo = 'N'                                       \n";
                l_query  += "             and iordp.qtatot > 0                                          \n";
                if (!cdarti.equals("")){
                l_query  += "                  and iordp.cdarti = '"+cdarti+"'                           \n";
                }
                l_query  += "                                             ) as totimportonettoriga_non_evaso \n";

            }
            



        }   // FINE    if (is_count){



        l_query  += "    FROM pgmr.ord_test         ordt            \n";
        l_query  += "       , pgmr.ut_art_tipordve  tord            \n";
        l_query  += "       , pgmr.archenti         ente            \n";
        l_query  += "       , {oj pgmr.archclie     clie            \n";
        l_query  += "         left outer join pgmr.archagen agen on clie.cdagen = agen.cdagen \n";
        l_query  += "           }                                   \n";
        l_query  += "   WHERE ordt.tkclie   = clie.tkclie           \n";
        l_query  += "     and ordt.cdazie   = clie.cdazie           \n";
        l_query  += "     and ordt.cdtorve  = tord.cdtorv           \n";
        l_query  += "     and clie.cdente   = ente.cdente           \n";
        l_query  += "     and ordt.cdazie   = '"+cdazie+"'          \n";

        if (!s_cdcapoarea.equals("")){
		l_query  += "     and clie.cdagen in ("+par_ca_agenti(s_cdcapoarea)+") \n";
        }else if (!s_cdagen.equals("")){
        l_query  += "     and clie.cdagen = '"+s_cdagen+"' \n";
        }
        if (!cdagen.equals("")){
        l_query  += "     and clie.cdagen = '"+cdagen+"' \n";
        }

        if (!cdarti.equals("")){
            l_query  += "     and 0 < ( select count(iordp.tkposi)           \n";
            l_query  += "                 from pgmr.ord_positito iordp       \n";
            l_query  += "                where iordp.cdarti = '"+cdarti+"'   \n";
            l_query  += "                  )                                 \n";
            
        }

        if (fgevaso.equals("N")){

            l_query  += "     and 0 < ( select count(iordp.tkposi)                                   \n";
            l_query  += "                 from pgmr.ord_positito iordp                               \n";
            l_query  += "                where iordp.tkordi = ordt.tkordi                            \n";
            l_query  += "                  and ( iordp.qtatot - iordp.qtacons - iordp.qtcons_s) > 0 \n";
            l_query  += "                  and iordp.fgsaldo = 'N'                                   \n";
            if (!cdarti.equals("")){
            l_query  += "                  and iordp.cdarti = '"+cdarti+"'                           \n";
            }
            l_query  += "                  )                                                         \n";

        }else if (fgevaso.equals("S")){

            l_query  += "     and 0 = ( select count(iordp.tkposi)                                   \n";
            l_query  += "                 from pgmr.ord_positito iordp                               \n";
            l_query  += "                where iordp.tkordi = ordt.tkordi                            \n";
            l_query  += "                  and ( iordp.qtatot - iordp.qtacons - iordp.qtcons_s) > 0 \n";
            l_query  += "                  and iordp.fgsaldo = 'N'                                   \n";
            if (!cdarti.equals("")){
            l_query  += "                  and iordp.cdarti = '"+cdarti+"'                           \n";
            }
            l_query  += "                  )                                                         \n";

        }


        if (dtordi_da!=null){
            l_query  += " and ordt.dtordi >= ?  \n";
        }
        if (dtordi_a!=null){
            l_query  += " and ordt.dtordi <= ?  \n";
        }    
        if (dtcons_da!=null){
            l_query  += " and ordt.dtcons >= ?  \n";
        }
        if (dtcons_a!=null){
            l_query  += " and ordt.dtcons <= ?  \n";
        }
        if (!tkclie.equals("")){
            l_query  += " and clie.tkclie = '"+tkclie+"'        \n";
        }else {
            
            if (!cdclie_m.equals("")){                              
                l_query  += " and upper(clie.cdclie_m) like '"+cdclie_m+"'    \n";
            }                                                       
            if (!ragcog.equals("")){                                
                l_query  += " and upper(ente.ragcog) like '"+ragcog+"'        \n";
            }
        }
        if (!cdordi.equals("")){                                
            l_query  += " and ordt.cdordi like '"+cdordi+"'        \n";
        }
        if (!cdrifo.equals("")){                                
            l_query  += " and ordt.cdordi like '"+cdrifo+"'        \n";
        }
        if (anno > 0){
            l_query  += " and ordt.anno = "+anno+"  \n";
        }
        if (!order_by.equals("")){
            l_query  += " order by "+order_by+" \n";
        }

        pstm = setQuery_ric(l_query);

        ind = 1;
        if (dtordi_da!=null){
            pstm.setTimestamp(ind++, dtordi_da);
        }
        if (dtordi_a!=null){
            pstm.setTimestamp(ind++, dtordi_a);
        }
        if (dtcons_da!=null){
            pstm.setTimestamp(ind++, dtcons_da);
        }
        if (dtcons_a!=null){
            pstm.setTimestamp(ind++, dtcons_a);
        }

        rs = pstm.executeQuery();


        return rs ;

    }












    /***


    */

    public ResultSet search_posi ( String    fgevaso
                                 , long      anno
                                 , String    cdordi
                                 , String    cdrifo
                                 , Timestamp dtordi_da
                                 , Timestamp dtordi_a
                                 , Timestamp dtcons_da
                                 , Timestamp dtcons_a
                                 , String    tkclie
                                 , String    cdclie_m
                                 , String    ragcog
                                 , String    cdarti
                                 , String    cdagen
                                 
                                 , boolean is_count 
                                 , String  order_by
                                                        ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        if (!s_tkclie.equals("")) tkclie = s_tkclie;


        if (tkclie.equals("") && !cdclie_m.equals("")){
            
            l_query  = "";
            l_query += " select tkclie        \n";
            l_query += "   from pgmr.archclie \n";
            l_query += "  where cdclie_m = ?  \n";
            l_query += "    and cdazie   = ?  \n";
    
            pstm = m_connection.prepareStatement(l_query);
    
            ind = 1;
            pstm.setString(ind++, cdclie_m);
            pstm.setString(ind++, cdazie  );
    
            rs = pstm.executeQuery();
    
            if (rs!=null && rs.next()){
                
                tot_rec += 1;
                ind = 0;
                if (rs.getObject(++ind)!=null)  tkclie = rs.getString(ind);
    
            }
            pstm.close();
        }


        cdordi = par_like(cdordi);
        cdrifo = par_like(cdrifo);
        cdclie_m = par_like(cdclie_m);
        ragcog = par_like(ragcog);


        


        if (order_by.equals("")) order_by = "ordt.dtordi desc, ordt.cdordi, ordp.nrposi ";
        if (is_count) order_by = "";

        if (is_count){
            
            l_query   = "";
            l_query  += "   select count(1)                                             \n";
            l_query  += "        , sum(ordp.qtatot )            as tot_qtatot           \n";
            l_query  += "        , sum(ordp.qtacons)            as tot_qtacons          \n";
            l_query  += "        , sum(ordp.qtcons_s)           as tot_qtcons_s         \n";
            l_query  += "        , sum(ordp.importonettoriga)   as tot_importonettoriga \n";
            l_query  += "        , sum(ordp.importonettoriga/ordp.qtatot * (ordp.qtatot - ordp.qtacons - ordp.qtcons_s)) as tot_importonettoriga_non_evaso  \n";
            l_query  += "        , sum(ordp.importonettoriga/ordp.qtatot * ordp.qtcons_s) as tot_importonettoriga_saldo  \n";
            l_query  += "        , sum(ordp.importonettoriga/ordp.qtatot * ordp.qtacons) as tot_importonettoriga_spedita    \n";



        }else {
            

            l_query   = "";
            l_query  += "  SELECT ordt.cdazie              \n";
            l_query  += "       , ordt.tkclie              \n";
            l_query  += "       , clie.cdclie_m            \n";
            l_query  += "       , agen.cdagen              \n";
            l_query  += "       , agen.cdagen_m            \n";
            l_query  += "       , agen.dsagen              \n";
            l_query  += "       , ente.ragcog              \n";
            l_query  += "       , ordt.dtordi              \n";
            l_query  += "       , ordt.cdordi              \n";
            l_query  += "       , ordt.cdrifo              \n";
            l_query  += "       , ordt.tkordi              \n";
            l_query  += "       , ordp.tkposi              \n";
            l_query  += "       , ordp.nrposi              \n";
            l_query  += "       , ordp.cdarti              \n";
            l_query  += "       , ordp.cdvar               \n";
            l_query  += "       , arti.cdartm              \n";
            l_query  += "       , ordp.dtcons              \n";
            l_query  += "       , ordp.dssart              \n";
            l_query  += "       , ordp.qtatot              \n";
            l_query  += "       , ordp.qtacons             \n";
            l_query  += "       , ordp.qtcons_s            \n";
            l_query  += "       , ordp.impuni              \n";
            l_query  += "       , ordp.impuninetto         \n";
            l_query  += "       , ordp.importonettoriga    \n";
            l_query  += "       , ordp.scont1              \n";
            l_query  += "       , ordp.scont2              \n";
            l_query  += "       , ordp.scont3              \n";
            l_query  += "       , ordp.scont4              \n";
            l_query  += "       , unim.cdunim_m              \n";


        }   // FINE    if (is_count){



        l_query  += "     FROM pgmr.ord_test          ordt          \n";
        l_query  += "        , pgmr.ord_positito      ordp          \n";
        l_query  += "        , pgmr.ut_art_tipordve   tord          \n";
        l_query  += "        , pgmr.archenti          ente          \n";
        l_query  += "        , {oj pgmr.archclie     clie            \n";
        l_query  += "          left outer join pgmr.archagen agen on clie.cdagen = agen.cdagen \n";
        l_query  += "           }                                   \n";
        l_query  += "        , {oj pgmr.mrp_arch_articoli arti      \n";
        l_query  += "          left outer join pgmr.unimisura unim on arti.cdunim_1 = unim.cdunim \n";
        l_query  += "           }                                   \n";
        l_query  += "    WHERE ordt.tkclie  = clie.tkclie           \n";
        l_query  += "      and ordp.tkordi  = ordt.tkordi           \n";
        l_query  += "      and ordp.cdarti  = arti.cdarti           \n";
        l_query  += "      and ordt.cdtorve = tord.cdtorv           \n";
        l_query  += "      and clie.cdente  = ente.cdente           \n";
        l_query  += "      and ordt.cdazie  = '"+cdazie+"'          \n";
        l_query  += "      and ordp.qtatot  > 0                     \n";

        if (!s_cdcapoarea.equals("")){
		l_query  += "      and clie.cdagen in ("+par_ca_agenti(s_cdcapoarea)+") \n";
        }else if (!s_cdagen.equals("")){
        l_query  += "      and clie.cdagen = '"+s_cdagen+"' \n";
        }
        if (!cdagen.equals("")){
        l_query  += "      and clie.cdagen = '"+cdagen+"' \n";
        }

        if (fgevaso.equals("N")){

            l_query  += "  and ( ordp.qtatot - ordp.qtacons - ordp.qtcons_s) > 0 \n";
            l_query  += "  and ordp.fgsaldo = 'N'                                 \n";
            

        }else if (fgevaso.equals("S")){

            l_query  += "  and (  ( ordp.qtatot - ordp.qtacons - ordp.qtcons_s) <= 0   \n";
            l_query  += "      or ordp.fgsaldo = 'S'                                    \n";
            l_query  += "         )                                                     \n";

        }else if (fgevaso.equals("B")){
            
            // LSP - righe Bloccate
            l_query  += "  and ( ordp.qtatot - ordp.qtacons - ordp.qtcons_s) > 0 \n";
            l_query  += "  and ordp.fgsaldo = 'N'                                 \n";
        }


        if (dtordi_da!=null){
            l_query  += " and ordt.dtordi >= ?  \n";
        }
        if (dtordi_a!=null){
            l_query  += " and ordt.dtordi <= ?  \n";
        }    
        if (dtcons_da!=null){
            l_query  += " and ordp.dtcons >= ?  \n";
        }
        if (dtcons_a!=null){
            l_query  += " and ordp.dtcons <= ?  \n";
        }
        if (!tkclie.equals("")){
            l_query  += " and clie.tkclie = '"+tkclie+"'        \n";
        }else {
            
            if (!cdclie_m.equals("")){                              
                l_query  += " and upper(clie.cdclie_m) like '"+cdclie_m+"'    \n";
            }                                                       
            if (!ragcog.equals("")){                                
                l_query  += " and upper(ente.ragcog) like '"+ragcog+"'     \n";
            }
        }
        if (!cdordi.equals("")){                                
            l_query  += " and ordt.cdordi like '"+cdordi+"'     \n";
        }
        if (!cdrifo.equals("")){                                
            l_query  += " and ordt.cdordi like '"+cdrifo+"'     \n";
        }
        if (anno > 0){
            l_query  += " and ordt.anno = "+anno+"             \n";
        }
        if (!cdarti.equals("")){
            l_query  += " and ordp.cdarti = '"+cdarti+"'  \n";
        }

        if (!order_by.equals("")){
            l_query  += " order by "+order_by+" \n";
        }



        pstm = setQuery_ric(l_query);

        ind = 1;
        if (dtordi_da!=null){
            pstm.setTimestamp(ind++, dtordi_da);
        }
        if (dtordi_a!=null){
            pstm.setTimestamp(ind++, dtordi_a);
        }
        if (dtcons_da!=null){
            pstm.setTimestamp(ind++, dtcons_da);
        }
        if (dtcons_a!=null){
            pstm.setTimestamp(ind++, dtcons_a);
        }

        rs = pstm.executeQuery();


        return rs ;

    }


 
    public String getCdstato(String cdstato) throws Exception {
        

        if (cdstato.equals("IM")) return "IMMESSO"      ;
        if (cdstato.equals("PR")) return "PROPOSTO"     ;
        if (cdstato.equals("CO")) return "CONFERMATO"   ;
        if (cdstato.equals("RI")) return "RILASCIATO"   ;
        if (cdstato.equals("AT")) return "ATTIVO"       ;
        if (cdstato.equals("PD")) return "PROGRAMMATO"  ;
        if (cdstato.equals("PL")) return "PRELIEVO"     ;
        if (cdstato.equals("PK")) return "IMBALLO"      ;
        if (cdstato.equals("SP")) return "IN SPEDIZIONE";
        if (cdstato.equals("CH")) return "CHIUSO"       ;
        if (cdstato.equals("SO")) return "SOSPESO"      ;
        if (cdstato.equals("AN")) return "ANNULLATO"    ;

        return "";

    }







    /***


    */

    public ResultSet getOrdinato_agente ( int mese
                                     , int anno
                                     , Timestamp dtordi_da
                                     , Timestamp dtordi_a
                                     , String order_by
                                                    ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;




        if (order_by.equals("")) order_by = "agen.cdagen_m";

        String ls_periodo = "";

        if (mese>0){
            ls_periodo = ((mese<10)?"0"+mese:""+mese)+"/"+anno;
        }


        tot_rec = 0;

        l_query  = "";
        l_query += " select agen.cdagen                                      \n";
        l_query += "      , agen.cdagen_m                                    \n";
        l_query += "      , agen.dsagen                                      \n";
        l_query += "      , count(distinct ordt.tkclie)  as count_cli        \n";
        l_query += "      , count(distinct ordt.tkordi)  as count_ord        \n";
        l_query += "      , count(ordp.tkposi)           as count_rig        \n";
        l_query += "      , sum(ordp.importonettoriga)   as tot_imp          \n";
        l_query += "      , sum(ordp.nrpeso_n)           as tot_peso         \n";
        l_query += "      , sum ( decode ( ordp.nrpeso_n                     \n";
        l_query += "                     , 0   , 1                           \n";
        l_query += "                     , null, 1                           \n";
        l_query += "                     , 0                                 \n";
        l_query += "                         ))          as count_rig_nopeso \n";
        l_query += "   from pgmr.ord_test          ordt                      \n";
        l_query += "      , pgmr.ord_positito      ordp                      \n";
        l_query += "      , pgmr.mrp_arch_articoli arti                      \n";
        l_query += "      , pgmr.archagen          agen                      \n";
        l_query += "      , pgmr.archclie          clie                      \n";
        l_query += "  where ordt.tkordi    = ordp.tkordi                     \n";
        l_query += "    and clie.tkclie    = ordt.tkclie                     \n";
        l_query += "    and clie.cdagen    = agen.cdagen                     \n";
        l_query += "    and ordp.cdarti    = arti.cdarti                     \n";
        if (!ls_periodo.equals("")){
        l_query += "    and to_char(ordt.dtordi, 'mm/yyyy' ) = ?             \n";
        }else {
            if (anno > 0){
                l_query += "    and ordt.anno= ?              \n";
            }
        }
        if (dtordi_da!=null){
            l_query += "    and ordt.dtordi >= ?              \n";
        }
        if (dtordi_a!=null){
            l_query += "    and ordt.dtordi <= ?              \n";
        }
        if (!s_cdcapoarea.equals("")){
		l_query  += "   and clie.cdagen in ("+par_ca_agenti(s_cdcapoarea)+") \n";
        }else if (!s_cdagen.equals("")){
        l_query += "    and clie.cdagen = ?                \n";
        }
        l_query += "  group by agen.cdagen                    \n";
        l_query += "         , agen.cdagen_m                  \n";
        l_query += "         , agen.dsagen                    \n";
        l_query += "  order by "+order_by+"                   \n";

        pstm = setQuery(l_query);


        ind = 1;
        if (!ls_periodo.equals("")){
            pstm.setString(ind++, ls_periodo);
        }else {
            if (anno > 0){
                pstm.setInt(ind++, anno);
            }
        }
        if (dtordi_da!=null){
            pstm.setTimestamp(ind++, dtordi_da);
        }
        if (dtordi_a!=null){
            pstm.setTimestamp(ind++, dtordi_a);
        }

        if (!s_cdagen.equals("")){
            pstm.setString(ind++, s_cdagen);
        }

        rs = pstm.executeQuery();



        return rs;

    }



    /***


    */

    public ResultSet getResiduo_agente ( Timestamp dtcons_a, String order_by) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        if (order_by.equals("")) order_by = "anno DESC, agen.cdagen_m";

        tot_rec = 0;

        l_query  = "";
        l_query += " select to_char(ordt.dtordi, 'yyyy') as anno                 \n";
        l_query += "      , agen.cdagen                                          \n";
        l_query += "      , agen.cdagen_m                                        \n";
        l_query += "      , agen.dsagen                                          \n";
        l_query += "      , count(distinct ordt.tkclie)  as count_cli            \n";
        l_query += "      , count(distinct ordt.tkordi)  as count_ord            \n";
        l_query += "      , count(ordp.tkposi)           as count_rig            \n";
        l_query += "      , sum(ordp.importonettoriga/ordp.qtatot * (ordp.qtatot - ordp.qtacons - ordp.qtcons_s)) as tot_imp    \n";
        l_query += "      , sum(ordp.nrpeso_n/ordp.qtatot * (ordp.qtatot - ordp.qtacons - ordp.qtcons_s)) as tot_peso           \n";
        l_query += "      , sum ( decode ( ordp.nrpeso_n                         \n";
        l_query += "                     , 0   , 1                               \n";
        l_query += "                     , null, 1                               \n";
        l_query += "                     , 0                                     \n";
        l_query += "                         ))          as count_rig_nopeso     \n";
        l_query += "   from pgmr.ord_test          ordt                          \n";
        l_query += "      , pgmr.ord_positito      ordp                          \n";
        l_query += "      , pgmr.mrp_arch_articoli arti                          \n";
        l_query += "      , pgmr.archagen          agen                          \n";
        l_query += "      , pgmr.archclie          clie                          \n";
        l_query += "  where ordt.tkordi    = ordp.tkordi                         \n";
        l_query += "    and ordt.tkclie    = clie.tkclie                         \n";
        l_query += "    and clie.cdagen    = agen.cdagen      (+)                \n";
        l_query += "    and ordp.cdarti    = arti.cdarti                         \n";
        l_query += "    and (ordp.qtatot - ordp.qtacons - ordp.qtcons_s) > 0    \n";
        l_query += "    and ordp.fgsaldo = 'N'                                   \n";
        if (!s_cdcapoarea.equals("")){
		l_query  += "   and uloc_dm.cdagen in ("+par_ca_agenti(s_cdcapoarea)+") \n";
        }else if (!s_cdagen.equals("")){
        l_query += "    and uloc_dm.cdagen = ?                                   \n";
        }
        if (dtcons_a!=null){
            l_query += "    and ordp.dtcons <= ?                            \n";
        }
        l_query += "  group by to_char(ordt.dtordi, 'yyyy')                      \n";
        l_query += "         , agen.cdagen                                       \n";
        l_query += "         , agen.cdagen_m                                     \n";
        l_query += "         , agen.dsagen                                       \n";
        l_query += "  order by "+order_by+"\n";

        pstm = setQuery(l_query);

        ind = 1;
        if (!s_cdagen.equals("")){
        pstm.setString(ind++, s_cdagen);
        }
        if (dtcons_a!=null){
        pstm.setTimestamp(ind++, dtcons_a);
        }


        rs = pstm.executeQuery();



        return rs;

    }



    //Inizio -- avendramin20080520
    public ResultSet getArticoli_trattati   ( String tkclie
                                            , Timestamp dtrif 
                                            , String visu_andamento 
                                            , boolean is_count 
                                            , String  order_by ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        if (dtrif != null){
            dtrif = of_getOggi();
        }
        
        long mese = 0;
        long anno = 0;
        String periodo = "";
        String ls_condizione = "";

        String ls_where = "";
        
        if (visu_andamento.equals("S")){
            
            mese = dtrif.getMonth()+ 1;
            anno = dtrif.getYear()+ 1900;
            long mese_rif = (dtrif.getMonth()+ 1);
            mese_rif++; //aggiungo un mese, per permettere che venga visualizzati i dati del mese di riferimento
            long anno_rif = (dtrif.getYear()+ 1900);
            String periodo_rif = ""+ anno_rif + ((mese_rif<=9)?"0"+mese_rif:""+mese_rif);
            periodo = "";
            for (int i=0; i < 12; i++) {
                if (mese <= 0) {
                    mese = 12;
                    anno = anno - 1;
                }
                periodo = ((mese<=9)?"0"+mese:""+mese);
                periodo = anno + periodo;
                mese = mese - 1;
            }
            
            if (this.is_oracle){
                ls_where  = "      and to_char(t.dtordi,'YYYYMM') >= '"+ periodo + "'           \n";
                ls_where  += "     and to_char(t.dtordi,'YYYYMM') < '"+ periodo_rif + "'       \n";
            } else if (this.is_sybase){
                ls_where  = "      and t.dtordi >=  convert(DATETIME, '"+ periodo + "01')       \n";
                ls_where  += "     and t.dtordi <  convert(DATETIME, '"+ periodo_rif + "01')   \n";

            }    

        } else {
            
            ls_where  = "      and t.dtordi >= ("+sysdate+" - 365)                         \n";
        }   

        
        if (order_by.equals("")) order_by = "a.dsarti";
        if (is_count) order_by = "";

        if (is_count){
            
            l_query   = "";
            l_query  += "   select count(distinct a.cdarti)                        \n";
            l_query  += "        , sum(case when catramer.ind_stat_ven = 'P' then p.qtatot when catramer.ind_stat_ven = 'M' then (p.qtatot * -1) else p.qtatot end) as totqta                                             \n";
            l_query  += "        , sum(case when catramer.ind_stat_ven = 'P' then p.importonettoriga when catramer.ind_stat_ven = 'M' then (p.importonettoriga * -1) else p.importonettoriga end) as totimportonettoriga  \n";
                
        } else {   

            l_query   = "";
            l_query  += "   SELECT a.cdartm                                     \n";
            l_query  += "        , a.cdarti                                     \n";
            l_query  += "        , a.dsarti                                     \n";
            //l_query  += "        , um.cdunim_m                                  \n";
            if (visu_andamento.equals("S")){

                mese = dtrif.getMonth()+ 1;
                anno = dtrif.getYear()+ 1900;
                periodo = "";
                ls_condizione = "";

                for (int i=0; i < 12; i++) {
                    if (mese <= 0) {
                        mese = 12;
                        anno = anno - 1;
                    }
                    periodo = ((mese<=9)?"0"+mese:""+mese);
                    periodo = anno + periodo;
                    
                    if (this.is_oracle){
                        ls_condizione  = "to_char(t.dtordi,'YYYYMM') = '"+ periodo + "'\n";
                    } else if (this.is_sybase){
                        ls_condizione  = "( month(t.dtordi) = "+((mese<=9)?"0"+mese:""+mese) + " and year(t.dtordi) = "+(anno) + ")          \n";

                    }  
                    
                    l_query  += "        , sum(case when " + ls_condizione + " then (case when catramer.ind_stat_ven = 'P' then p.qtatot when catramer.ind_stat_ven = 'M' then (p.qtatot * -1) else p.qtatot end) else 0 end) as totqta" + (i + 1) + "                         \n";
                    l_query  += "        , sum(case when " + ls_condizione + " then (case when catramer.ind_stat_ven = 'P' then p.importonettoriga when catramer.ind_stat_ven = 'M' then (p.importonettoriga * -1) else p.importonettoriga end) else 0 end) as totimportonettoriga" + (i + 1) + "  \n";
                    mese = mese - 1;
                }

            } else {    
            
                l_query  += "        , max(p.tkposi)           as maxtkposi         \n";
                l_query  += "        , sum(case when catramer.ind_stat_ven = 'P' then p.qtatot when catramer.ind_stat_ven = 'M' then (p.qtatot * -1) else p.qtatot end) as totqta                                             \n";
                l_query  += "        , sum(case when catramer.ind_stat_ven = 'P' then p.importonettoriga when catramer.ind_stat_ven = 'M' then (p.importonettoriga * -1) else p.importonettoriga end) as totimportonettoriga  \n";
                l_query  += "        , (select iordp.importonettoriga                \n";
                l_query  += "             from pgmr.ord_positito iordp               \n";
                l_query  += "            where iordp.tkposi = maxtkposi              \n"; //Nota in oracle non funziona
                l_query  += "          ) as ultimoprezzo                             \n";
                
            }  // Fine -- if (visu_andamento.equals("S")){  
            
        }    
        
            l_query  += "     FROM pgmr.ord_test            t                                                                               \n";
            l_query  += "        , pgmr.mrp_arch_articoli  a                                                                                \n";
            l_query  += "        , pgmr.tipo_documento      tipodocumento                                                                   \n";
            l_query  += "        , {oj               pgmr.archclie clie                                                                     \n";
            l_query  += "                            LEFT OUTER JOIN  pgmr.archagen           agen ON  clie.cdagen = agen.cdagen            \n";
            l_query  += "          }                                                                                                        \n";
            l_query  += "        , {oj               pgmr.ord_positito p                                                                    \n";
            l_query  += "                            LEFT OUTER JOIN  pgmr.ut_art_catramer    catramer ON  p.cdcatm = catramer.cdcatm       \n";
            l_query  += "          }                                                                                                        \n";
            l_query  += "    WHERE t.cdazie = '"+this.cdazie+"'                                                                             \n";
            l_query  += ls_where;
            l_query  += "      and t.tkordi = p.tkordi                                                                                      \n";
            l_query  += "      and p.cdarti = a.cdarti                                                                                      \n";
            l_query  += "      and t.tkclie = clie.tkclie                                                                                   \n";
            l_query  += "      and tipodocumento.cdclas = 'V'                                                                               \n";
            l_query  += "      and t.cdtdoc  = tipodocumento.cdtdoc                                                                         \n";
            
        
        if (!s_cdcapoarea.equals("")){
            l_query  += "   and clie.cdagen in ("+par_ca_agenti(s_cdcapoarea)+") \n";
        }else if (!s_cdagen.equals("")){
            l_query += "    and clie.cdagen = '"+s_cdagen+"' \n";
        }else if (!s_tkclie.equals("")){
            l_query  += "  and t.tkclie = '"+s_tkclie+"'  \n";
        } 
        
        if (!tkclie.equals("")){
            l_query  += "  and t.tkclie = '"+tkclie+"'  \n";
        }

        if (is_count){
            
        } else {
            l_query  += " GROUP BY a.cdartm                                     \n";
            l_query  += "        , a.cdarti                                     \n";
            l_query  += "        , a.dsarti                                     \n";
            //l_query  += "        , um.cdunim_m                                  \n";
        }    

        
        pstm = setQuery_ric(l_query);


        rs = pstm.executeQuery();



        return rs;

    }







    


    public long tkordi = 0;


    public String[] dw_ordven_stato_ddw = new String[]{ "IM","IMMESSO"              // ,"0"
                                                      , "PR","PROPOSTO"             // ,"1"
                                                      , "CO","CONFERMATO"           // ,"2"
                                                      , "RI","RILASCIATO"           // ,"3"
                                                      , "AT","ATTIVO"               // ,"4"
                                                      , "PD","PROGRAMMATO"          // ,"5"
                                                      , "PL","PRELIEVO"             // ,"6"
                                                      , "PK","IMBALLO"              // ,"7"
                                                      , "SP","IN SPEDIZIONE"        // ,"8"
                                                      , "CH","CHIUSO"               // ,"9"
                                                      , "SO","SOSPESO"              // ,"10"
                                                      , "AN","ANNULLATO"            // ,"11"
                                                            };


}
