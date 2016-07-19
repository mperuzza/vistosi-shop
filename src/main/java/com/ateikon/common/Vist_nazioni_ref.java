package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Vist_nazioni_ref extends com.ateikon.standard.Vist_nazioni_ref {


    public Vist_nazioni_ref() {

        super();
    }

    public String getDsutente_responsabile_vendite(String cdnazi) throws Exception{
      int               ind = 0;
      int               tot_rec = 0;
      PreparedStatement pstm = null;
      ResultSet         rs = null;


      String ls_ = "";

      l_query   = "";
      l_query  += "  select eute.dsutente                          \n";
      l_query  += "    from pgmr.vist_nazioni_ref  naziref         \n";
      l_query  += "       , pgmr.ep_utente eute                    \n";
      l_query  += "   where naziref.tkutente = eute.tkutente       \n";
      l_query  += "     and naziref.cdnazi = '"+ cdnazi +"'        \n";
      l_query  += "     and naziref.cdruolo  = 'RESPV'             \n";
      l_query  += "     and naziref.cdazie = '"+ cdazie +"'        \n";

      ls_ = sql_String(l_query);

      return ls_;
    }


    public String getDsutente_area_manager(String cdnazi) throws Exception{
      int               ind = 0;
      int               tot_rec = 0;
      PreparedStatement pstm = null;
      ResultSet         rs = null;


      String ls_ = "";

      l_query   = "";
      l_query  += "  select eute.dsutente                          \n";
      l_query  += "    from pgmr.vist_nazioni_ref  naziref         \n";
      l_query  += "       , pgmr.ep_utente eute                    \n";
      l_query  += "   where naziref.tkutente = eute.tkutente       \n";
      l_query  += "     and naziref.cdnazi = '"+ cdnazi +"'        \n";
      l_query  += "     and naziref.cdruolo  = 'AREAM'             \n";
      l_query  += "     and naziref.cdazie = '"+ cdazie +"'        \n";

      ls_ = sql_String(l_query);

      return ls_;
    }

    public long getTkutente_responsabile_vendite(String cdnazi) throws Exception{
      int               ind = 0;
      int               tot_rec = 0;
      PreparedStatement pstm = null;
      ResultSet         rs = null;


      long ll_ = 0;

      l_query   = "";
      l_query  += "  select eute.tkutente                          \n";
      l_query  += "    from pgmr.vist_nazioni_ref  naziref         \n";
      l_query  += "       , pgmr.ep_utente eute                    \n";
      l_query  += "   where naziref.tkutente = eute.tkutente       \n";
      l_query  += "     and naziref.cdnazi = '"+ cdnazi +"'        \n";
      l_query  += "     and naziref.cdruolo  = 'RESPV'             \n";
      l_query  += "     and naziref.cdazie = '"+ cdazie +"'        \n";

      ll_ = sql_long(l_query);

      return ll_;
    }


    public long getTkutente_area_manager(String cdnazi) throws Exception{
      int               ind = 0;
      int               tot_rec = 0;
      PreparedStatement pstm = null;
      ResultSet         rs = null;


      long ll_ = 0;

      l_query   = "";
      l_query  += "  select eute.tkutente                          \n";
      l_query  += "    from pgmr.vist_nazioni_ref  naziref         \n";
      l_query  += "       , pgmr.ep_utente eute                    \n";
      l_query  += "   where naziref.tkutente = eute.tkutente       \n";
      l_query  += "     and naziref.cdnazi = '"+ cdnazi +"'        \n";
      l_query  += "     and naziref.cdruolo  = 'AREAM'             \n";
      l_query  += "     and naziref.cdazie = '"+ cdazie +"'        \n";

      ll_ = sql_long(l_query);

      return ll_;
    }

    public String getCdutente_responsabile_vendite(String cdnazi) throws Exception{
      int               ind = 0;
      int               tot_rec = 0;
      PreparedStatement pstm = null;
      ResultSet         rs = null;


      String ls_ = "";

      l_query   = "";
      l_query  += "  select eute.cdutente                          \n";
      l_query  += "    from pgmr.vist_nazioni_ref  naziref         \n";
      l_query  += "       , pgmr.ep_utente eute                    \n";
      l_query  += "   where naziref.tkutente = eute.tkutente       \n";
      l_query  += "     and naziref.cdnazi = '"+ cdnazi +"'        \n";
      l_query  += "     and eute.cdutente is not null              \n";
      l_query  += "     and naziref.cdazie = '"+ cdazie +"'        \n";

      ls_ = sql_String(l_query);

      return ls_;
    }


    public String getCdispe_area_manager(String cdnazi) throws Exception{
      int               ind = 0;
      int               tot_rec = 0;
      PreparedStatement pstm = null;
      ResultSet         rs = null;


      String ls_ = "";

      l_query   = "";
      l_query  += "  select eute.cdispe                            \n";
      l_query  += "    from pgmr.vist_nazioni_ref  naziref         \n";
      l_query  += "       , pgmr.ep_utente eute                    \n";
      l_query  += "   where naziref.tkutente = eute.tkutente       \n";
      l_query  += "     and naziref.cdnazi = '"+ cdnazi +"'        \n";
      l_query  += "     and eute.cdispe is not null                \n";
      l_query  += "     and naziref.cdazie = '"+ cdazie +"'        \n";

      ls_ = sql_String(l_query);

      return ls_;
    }



    public ResultSet getDropdown_responsabile_vendite() throws Exception {

      int               ind = 0;
      int               tot_rec = 0;
      PreparedStatement pstm = null;
      ResultSet         rs      = null;

      l_query   = "";
      l_query  += "   select eute.tkutente                          \n";
      l_query  += "        , eute.dsutente                          \n";
      l_query  += "     from pgmr.vist_nazioni_ref  naziref         \n";
      l_query  += "        , pgmr.ep_utente eute                    \n";
      l_query  += "    where naziref.tkutente = eute.tkutente       \n";
      l_query  += "      and naziref.cdruolo  = 'RESPV'             \n";
      l_query  += "      and naziref.cdazie = '"+ cdazie +"'        \n";
      l_query  += " group by eute.tkutente                          \n";
      l_query  += "        , eute.dsutente                          \n";
      l_query  += " order by eute.dsutente                          \n";

      pstm = setQuery( l_query ) ;

      rs = pstm.executeQuery();


      return rs;
    }



    public ResultSet getDropdown_area_manager() throws Exception {

      int               ind = 0;
      int               tot_rec = 0;
      PreparedStatement pstm = null;
      ResultSet         rs      = null;

      l_query   = "";
      l_query  += "   select eute.tkutente                          \n";
      l_query  += "        , eute.dsutente                          \n";
      l_query  += "     from pgmr.vist_nazioni_ref  naziref         \n";
      l_query  += "        , pgmr.ep_utente eute                    \n";
      l_query  += "    where naziref.tkutente = eute.tkutente       \n";
      l_query  += "      and naziref.cdruolo  = 'AREAM'             \n";
      l_query  += "      and naziref.cdazie = '"+ cdazie +"'        \n";
      l_query  += " group by eute.tkutente                          \n";
      l_query  += "        , eute.dsutente                          \n";
      l_query  += " order by eute.dsutente                          \n";

      pstm = setQuery( l_query ) ;

      rs = pstm.executeQuery();


      return rs;
    }

    public String getCdresp_responsabile_vendite(String cdnazi) throws Exception{
      int               ind = 0;
      int               tot_rec = 0;
      PreparedStatement pstm = null;
      ResultSet         rs = null;


      String ls_ = "";

      l_query   = "";
      l_query  += "  select eute.cdresp                            \n";
      l_query  += "    from pgmr.vist_nazioni_ref  naziref         \n";
      l_query  += "       , pgmr.ep_utente eute                    \n";
      l_query  += "   where naziref.tkutente = eute.tkutente       \n";
      l_query  += "     and naziref.cdnazi = '"+ cdnazi +"'        \n";
      l_query  += "     and eute.cdutente is not null              \n";
      l_query  += "     and naziref.cdazie = '"+ cdazie +"'        \n";

      ls_ = sql_String(l_query);

      return ls_;
    }


}

