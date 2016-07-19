package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Vist_mpron_posi extends com.ateikon.standard.Vist_mpron_posi {


    public Vist_mpron_posi() {

        super();

        ib_calcola_token = false;
        null_importo = false;
    }


    public int delete( long tkmpronp ) throws Exception {
      int               ind = 0;
      int               tot_rec = 0;
      PreparedStatement pstm = null;
      ResultSet         rs = null;

      Timestamp now = of_getTimestamp();
      
      l_query   = "";
      l_query  += " update pgmr.vist_mpron_posi     \n";
      l_query  += "    set dtdel = ?                \n";
      l_query  += "  where tkmpronp = ?             \n";

      pstm = m_connection.prepareStatement( l_query ) ;

      ind = 1;
      pstm.setTimestamp (ind, now); ind += 1;
      pstm.setLong      (ind, tkmpronp); ind += 1;

      tot_rec = pstm.executeUpdate();
      pstm.close();

      return tot_rec;

    }

    public ResultSet of_searchRighe_scadenze (long tkmpron) throws Exception {

      return of_searchRighe_scadenze(tkmpron , s_cdling);
      
    }

    public ResultSet of_searchRighe_scadenze (long tkmpron, String cdling ) throws Exception {
      int ind = 0;
      int tot_rec = 0;
      PreparedStatement pstm = null;
      ResultSet rs = null;

      Valueste  valueste = new Valueste();

      setProfilo((Atk_sql) valueste);

      String  l_query = "";
      
      
      l_query  = "";
      l_query  = "   select mppo.tkmpronp                                                                       \n";
      l_query += "        , mppo.dtdoc                                                                          \n";
      l_query += "        , mppo.nrdoc                                                                          \n";
      l_query += "        , mppo.importo                                                                        \n";
      l_query += "        , mppo.vacodi                                                                         \n";
      l_query += "        , mppo.nota                                                                           \n";
      l_query += "        , valu.cambeuro                                                                       \n";
      l_query += "        , "+ valueste.of_descr_lingua (cdling, "valu", "vadesc") +" as vadesc                 \n";
      l_query += "     from { oj pgmr.vist_mpron_posi mppo                                                      \n";
      l_query += "               left outer join pgmr.valueste valu on  mppo.vacodi = valu.vacodi               \n";
      l_query += "          }                                                                                   \n";
      l_query += "    where mppo.tkmpron = ?                                                                    \n";
      l_query += "      and mppo.dtdel is null                                                                  \n";
      l_query += "      and mppo.cdazie = '"+ cdazie +"'                                                        \n";
      l_query += " order by mppo.nrposi                                                                         \n";
 
      pstm = setQuery(l_query);

      ind = 1;

      pstm.setLong(ind++, tkmpron);

      rs = pstm.executeQuery();

      valueste.close();
      
      return rs;
    }
}

