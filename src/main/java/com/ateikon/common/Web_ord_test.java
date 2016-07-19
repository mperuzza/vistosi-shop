package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Web_ord_test extends com.ateikon.standard.Web_ord_test {


    public Web_ord_test() {

        super();

        null_provv1_p = false;
        null_provv1_v = false;
        null_provv2_p = false;
        null_provv2_v = false;
        null_scrig1 = false;
        null_scrig2 = false;
        null_scrig3 = false;
        null_scrig4 = false;
        null_scrap1 = false;
        null_scrap2 = false;
        null_tgsca1 = false;
        null_tgsca2 = false;
        null_scocas = false;
        null_imptrasp = false;
        null_scval = false;
        null_speinc = false;
        null_nraddimb = false;
        null_addimb = false;
        null_addtra = false;
        null_nrpeso_l = false;
        null_nrpeso_n = false;
        null_nrtovo = false;
        null_tmesc1 = false;
        null_tmesc2 = false;
        null_tgsc1m = false;
        null_tgsc2m = false;
        null_provv1_p_var = false;
        null_provv2_p_var = false;
        null_provv1_v_var = false;
        null_provv2_v_var = false;
        null_nrtoco = false;
        null_totale_imp = false;

    }



    public boolean isScontoRiga( long tkordi ) throws Exception{
    
        
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;


        l_query   = "";
		l_query  += " select count(*)               \n";
		l_query  += "   from pgmr.web_ord_positito  \n";
		l_query  += "  where tkordi = "+tkordi+"    \n";
		l_query  += "    and (   scont1 > 0         \n";
		l_query  += "        or  scont2 > 0         \n";
		l_query  += "        or  scont3 > 0         \n";
		l_query  += "        or  scont4 > 0         \n";
		l_query  += "        or  scrap1 > 0         \n";
		l_query  += "        or  scrap2 > 0         \n";
		l_query  += "           )                   \n";

		tot_rec = sql_int( l_query ) ;

        if (tot_rec > 0){
            
            // se anche una sola riga non deve visualizzare gli sconti
            // non li faccio vedere per nessuna riga
      
            l_query   = "";
    		l_query  += " select count(*)                \n";
    		l_query  += "   from pgmr.web_ord_positito   \n";
    		l_query  += "  where tkordi = "+tkordi+"     \n";
		    l_query  += "    and fg_annulla_sconti = 'S' \n";

		    tot_rec = sql_int( l_query ) ;

            if (tot_rec > 0){
                return false;
            }

            return true;
        }

        return false;
    }




    public int count( long tkordi ) throws Exception{
    
        
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;


        l_query   = "";

		l_query  += " select count(*)               \n";
		l_query  += "   from pgmr.web_ord_positito  \n";
		l_query  += "  where tkordi = "+tkordi+"    \n";

		tot_rec = sql_int( l_query ) ;

        return tot_rec;
    }




    public ResultSet search( Timestamp r_dtordi_da
                           , Timestamp r_dtordi_a
                           , String    r_cdagen_m
                           , String    r_dsagen
                           , String    r_cdclie_m
                           , String    r_ragcog
                           , boolean   is_count 
                           , String    order_by
                                                ) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        if (order_by.equals("")) order_by = "ordt.dtordi desc, ordt.cdordi";

        
        r_cdagen_m = par_like(r_cdagen_m);
        r_dsagen = par_like(r_dsagen);
        r_cdclie_m = par_like(r_cdclie_m);
        r_ragcog = par_like(r_ragcog);

        if (is_count){
            
            l_query   = "";
    		l_query  += " select count(1) \n";

            order_by = "";
        }else {
            l_query   = "";
    		l_query  += " select ordt.tkordi                                      \n";
    		l_query  += "      , ordt.cdordi                                      \n";
    		l_query  += "      , ordt.dtordi                                      \n";
    		l_query  += "      , ordt.cdstato                                     \n";
    		l_query  += "      , ordt.tkclie                                      \n";
    		l_query  += "      , ordt.cdagen                                      \n";
    		l_query  += "      , ordt.tkordigest                                  \n";
    		l_query  += "      , ordt.cdordigest                                  \n";
    		l_query  += "      , clie.cdclie_m                                    \n";
    		l_query  += "      , ente.ragcog                                      \n";
    		l_query  += "      , agen.cdagen_m                                    \n";
    		l_query  += "      , agen.dsagen                                      \n";
    		l_query  += "      , ( select sum(iordp.importonettoriga)             \n";
    		l_query  += "            from pgmr.web_ord_positito iordp             \n";
    		l_query  += "           where iordp.tkordi = ordt.tkordi ) as totale  \n";
        }

		l_query  += "   from {oj pgmr.web_ord_test ordt                                                \n";
		l_query  += "            LEFT OUTER JOIN  pgmr.archagen  agen on  ordt.cdagen = agen.cdagen    \n";
		l_query  += "                                                 and ordt.cdazie = agen.cdazie    \n";
		l_query  += "            LEFT OUTER JOIN  pgmr.archclie  clie on  ordt.tkclie = clie.tkclie    \n";
		l_query  += "                                                 and ordt.cdazie = clie.cdazie    \n";
		l_query  += "            LEFT OUTER JOIN  pgmr.archenti  ente on  clie.cdente = ente.cdente    \n";
		l_query  += "            }                                                                     \n";
		l_query  += "  where ordt.cdazie = '"+cdazie+"'                                                \n";

        if (!s_cdagen.equals("")){
		l_query  += "  and ordt.cdagen = '"+s_cdagen+"'  \n";
        }
        if (!s_tkclie.equals("")){
		l_query  += "  and ordt.tkclie = '"+s_tkclie+"'  \n";
        }


        if (!r_cdagen_m.equals("")){
		l_query  += "  and upper(agen.cdagen_m) like '"+r_cdagen_m+"'  \n";
        }
        if (!r_dsagen.equals("")){
		l_query  += "  and upper(agen.dsagen) like '"+r_dsagen+"'  \n";
        }
        if (!r_cdclie_m.equals("")){
		l_query  += "  and upper(clie.cdclie_m) like '"+r_cdclie_m+"'  \n";
        }
        if (!r_ragcog.equals("")){
		l_query  += "  and upper(ente.ragcog) like '"+r_ragcog+"'  \n";
        }
        if (r_dtordi_da != null){
		l_query  += "  and ordt.dtordi >= ?  \n";
        }
        if (r_dtordi_a != null){
		l_query  += "  and ordt.dtordi <= ?  \n";
        }
        if (!order_by.equals("")){
            l_query  += "  order by "+order_by+"  \n";    
        }
		

        pstm = setQuery_ric(l_query);

        ind = 1;
        if (r_dtordi_da != null){
		    pstm.setTimestamp(ind++, r_dtordi_da);
        }
        if (r_dtordi_a != null){
		    pstm.setTimestamp(ind++, r_dtordi_a);
        }

        rs = pstm.executeQuery();

        return rs;
    }





    /***


    */

    public ResultSet getOrdven (long tkordigest ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec = 0;

        l_query  = "";
        l_query += "  select ordt.tkordi        \n";
        l_query += "       , ordt.cdordi        \n";
        l_query += "       , ordt.dtordi        \n";
        l_query += "    from pgmr.ord_test ordt \n";
        l_query += "   where ordt.tkordi = ?    \n";


        pstm = setQuery(l_query);

        ind = 1;
        pstm.setLong(ind++, tkordigest);

        rs = pstm.executeQuery();


        return rs;

    }




    /***


    */

    public ResultSet getBolla (long tkordigest ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec = 0;

        l_query  = "";
        l_query += "  select bolt.tkboll                                           \n";
        l_query += "       , bolt.cdboll                                           \n";
        l_query += "       , bolt.dtboll                                           \n";
        l_query += "    from pgmr.bolla_test bolt                                  \n";
        l_query += "       , pgmr.bolla_posi bolp                                  \n";
        l_query += "   where bolt.tkboll = bolp.tkboll                             \n";
        l_query += "     and (bolt.tkfatt is null or bolt.tkfatt <> bolt.tkboll )  \n";
        l_query += "     and bolp.tkposi in (select tkposi                         \n";
        l_query += "                           from pgmr.ord_positito iordp        \n";
        l_query += "                          where iordp.tkordi = ?               \n";
        l_query += "                                  )                            \n";


        pstm = setQuery(l_query);

        ind = 1;
        pstm.setLong(ind++, tkordigest);

        rs = pstm.executeQuery();


        return rs;

    }

    /***


    */

    public ResultSet getFattura (long tkordigest ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec = 0;

        l_query  = "";
        l_query += "  select bolt.tkboll                                    \n";
        l_query += "       , bolt.conpro                                    \n";
        l_query += "       , bolt.dtprot                                    \n";
        l_query += "    from pgmr.bolla_test bolt                           \n";
        l_query += "       , pgmr.bolla_posi bolp                           \n";
        l_query += "   where bolt.tkfatt = bolp.tkfatt                      \n";
        l_query += "     and bolt.tkfatt = bolt.tkboll                      \n";
        l_query += "     and bolp.tkposi in (select tkposi                  \n";
        l_query += "                           from pgmr.ord_positito iordp \n";
        l_query += "                          where iordp.tkordi = ?        \n";
        l_query += "                                  )                     \n";


        pstm = setQuery(l_query);

        ind = 1;
        pstm.setLong(ind++, tkordigest);

        rs = pstm.executeQuery();


        return rs;

    }
    
    public long gettkOff (long tkordi ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;
        long              tkordi_off = 0;


        tot_rec = 0;

        l_query  = "";
        l_query += " select tkordi                  \n";
        l_query += "   from pgmr.web_ord_test       \n";
        l_query += "  where tkrifoff  = ?  \n";       

        pstm = setQuery(l_query);

        ind = 1;
        pstm.setLong(ind++, tkordi);

        rs = pstm.executeQuery();
        
        
        if (rs!=null && rs.next()){
             if (rs.getObject("tkordi")!=null)  tkordi_off = rs.getLong("tkordi");
        }

        return tkordi_off;

    }

}

