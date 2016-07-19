package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Vist_mpron_ord_posi extends com.ateikon.standard.Vist_mpron_ord_posi {


    public Vist_mpron_ord_posi() {

        super();

        null_importo = false;
        null_importo_c = false;
        null_nrpeso_l = false;
        null_vlunlt = false;
        null_qtatot = false;
        null_impuni = false;
        null_impuninetto = false;
        
    }


    public ResultSet of_search (long tkmpron, String cdling) throws Exception {
      int ind = 0;
      int tot_rec = 0;
      PreparedStatement pstm = null;
      ResultSet rs = null;

      String  l_query = "";
      
      l_query  = "";
      l_query  = "   select ordt.tkordi                                                                                          \n";
      l_query += "         , ordt.cdordi                                                                                         \n";
      l_query += "         , ordt.dtordi                                                                                         \n";
      l_query += "         , ordt.cdrifo                                                                                         \n";
      l_query += "         , ordp.dssart                                                                                         \n";
      l_query += "         , arti.cdartm                                                                                         \n";
      l_query += "         , "+ of_descr_lingua ("mrp_arch_articoli", cdling, "arti", "dsarti") +" as dsarti                     \n";
      l_query += "         , ordt.notapagame                                                                                     \n";
      l_query += "         , vett.cdvett                                                                                         \n";
      l_query += "         , vett.dsvett                                                                                         \n";
      l_query += "         , ordp.fgpron_cons                                                                                    \n";
      l_query += "         , ordp.fgnd_cons                                                                                      \n";
      l_query += "         , ordt.vacodi                                                                                         \n";
      l_query += "         , "+ of_descr_lingua (cdling, "valu", "vadesc") +" as vadesc                                          \n";
      l_query += "         , mpov.tkmpronordp                                                                                                                                                 \n";
      l_query += "         , mpov.fgmerce_pronta as is_merce_pronta                                                                                                                           \n";
      l_query += "         , mpov.importo                                                                                                                                                     \n";
      l_query += "         , mpov.importo_c                                                                                                                                                   \n";
      l_query += "         , mpov.nrpeso_l                                                                                                                                                    \n";
      l_query += "         , mpov.vlunlt                                                                                                                                                      \n";
      l_query += "         , mpov.impuni                                                                                                                                                      \n";
      l_query += "         , mpov.impuninetto                                                                                                                                                 \n";
      l_query += "         , mpov.qtatot                                                                                                                                                      \n";
      l_query += "         , mpov.dtcons                                                                                         \n";
      l_query += "         , (select sum (in_mpov.importo)                                                                                                                                    \n";
      l_query += "              from pgmr.vist_mpron_ord_posi in_mpov                                                                                                                         \n";
      l_query += "             where in_mpov.dtdel is null                                                                                                                                    \n";
      l_query += "               and in_mpov.tkmpron = mpov.tkmpron)    as tot_importo                                                                                                        \n";
      l_query += "         , (select sum (in_mpov.importo_c)                                                                                                                                  \n";
      l_query += "              from pgmr.vist_mpron_ord_posi in_mpov                                                                                                                         \n";
      l_query += "             where in_mpov.dtdel is null                                                                                                                                    \n";
      l_query += "               and in_mpov.tkmpron = mpov.tkmpron)    as tot_importo_c                                                                                                      \n";
      l_query += "         , (select sum (in_mpov.nrpeso_l)                                                                                                                                   \n";
      l_query += "              from pgmr.vist_mpron_ord_posi in_mpov                                                                                                                         \n";
      l_query += "             where in_mpov.dtdel is null                                                                                                                                    \n";
      l_query += "               and in_mpov.tkmpron = mpov.tkmpron)    as tot_nrpeso_l                                                                                                       \n";
      l_query += "         , (select sum (in_mpov.vlunlt)                                                                                                                                     \n";
      l_query += "              from pgmr.vist_mpron_ord_posi in_mpov                                                                                                                         \n";
      l_query += "             where in_mpov.dtdel is null                                                                                                                                    \n";
      l_query += "               and in_mpov.tkmpron = mpov.tkmpron)    as tot_vlunlt                                                                                                         \n";
      l_query += "         , (select sum (in_mpov.impuninetto)                                                                                                                                \n";
      l_query += "              from pgmr.vist_mpron_ord_posi in_mpov                                                                                                                         \n";
      l_query += "             where in_mpov.dtdel is null                                                                                                                                    \n";
      l_query += "               and in_mpov.tkmpron = mpov.tkmpron)    as tot_impuninetto                                                                                                    \n";
      l_query += "         , (select sum (in_mpov.qtatot)                                                                                                                                     \n";
      l_query += "              from pgmr.vist_mpron_ord_posi in_mpov                                                                                                                         \n";
      l_query += "             where in_mpov.dtdel is null                                                                                                                                    \n";
      l_query += "               and in_mpov.tkmpron = mpov.tkmpron)    as tot_qtatot                                                                                                         \n";
      l_query += "      from pgmr.vist_mpron_ord_posi           mpov                                                                                                                          \n";
      l_query += "         , {oj              pgmr.ord_positito        ordp                                                                                                                   \n";
      l_query += "            left outer join pgmr.ord_test            ordt on  ordp.tkordi  = ordt.tkordi                                                                                    \n";
      l_query += "                left outer join pgmr.vettotra            vett on  ordt.cdvett1 = vett.cdvett                                                                                \n";
      l_query += "                left outer join pgmr.valueste            valu on  ordt.vacodi  = valu.vacodi                                                                                \n";
      l_query += "            left outer join pgmr.mrp_arch_articoli   arti on  ordp.cdarti  = arti.cdarti                                                                                    \n";
      l_query += "           }                                                                                                                                                                \n";
      l_query += "     where ordp.cdazie = '"+ cdazie +"'                                                                                                                                     \n";
      l_query += "       and ordp.tkposi = mpov.tkposi                                                                                                                                        \n";
      l_query += "       and mpov.dtdel is null                                                                                                                                               \n";
      l_query += "       and mpov.tkmpron = ?                                                                                                                                                 \n";
      l_query += "  order by ordt.tkordi, ordp.nrposi                                                                                                                                         \n";
      
      pstm = setQuery(l_query);

      ind = 1;

      pstm.setLong(ind++, tkmpron);

      rs = pstm.executeQuery();
      
      return rs;
    }

    
    public int delete( long tkmpronordp ) throws Exception {
      int               ind = 0;
      int               tot_rec = 0;
      PreparedStatement pstm = null;
      ResultSet         rs = null;

      Timestamp now = of_getTimestamp();
      
      l_query   = "";
      l_query  += " update pgmr.vist_mpron_ord_posi    \n";
      l_query  += "    set dtdel = ?                   \n";
      l_query  += "  where tkmpronordp = ?             \n";

      pstm = m_connection.prepareStatement( l_query ) ;

      ind = 1;
      pstm.setTimestamp (ind, now); ind += 1;
      pstm.setLong      (ind, tkmpronordp); ind += 1;

      tot_rec = pstm.executeUpdate();
      pstm.close();

      return tot_rec;

    }


}

