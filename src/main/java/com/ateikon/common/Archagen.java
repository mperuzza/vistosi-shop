package com.ateikon.common;

import com.ateikon.function.F_utente;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import java.util.Date;

public class Archagen extends Atk_sql {

    public Archagen() {

        super();
    }

    public ResultSet getCdagen(String cdagen) throws Exception {

        return getKey(cdagen);
    }

    public ResultSet getKey(String cdagen) throws Exception {
        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        l_query = "";
        l_query += " select *                \n";
        l_query += "   from pgmr.archagen    \n";
        l_query += "  where cdagen = ?       \n";
        l_query += "    and cdazie = ?       \n";

        pstm = setQuery(l_query);

        ind = 1;

        pstm.setString(ind, cdagen);
        ind += 1;
        pstm.setString(ind, cdazie);
        ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }

    public ResultSet getKey_m(String cdagen_m) throws Exception {
        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        l_query = "";
        l_query += " select *              \n";
        l_query += "   from pgmr.archagen  \n";
        l_query += "  where cdagen_m = ?   \n";
        l_query += "    and cdazie = ?     \n";

        pstm = setQuery(l_query);

        ind = 1;

        pstm.setString(ind, cdagen_m);
        ind += 1;
        pstm.setString(ind, cdazie);
        ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }

    public String getDsagen(String cdagen) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String dsagen = "";


        l_query = "";
        l_query += " select dsagen        \n";
        l_query += "   from pgmr.archagen \n";
        l_query += "  where cdagen = ?    \n";

        pstm = setQuery(l_query);

        ind = 1;
        pstm.setString(ind, cdagen);
        ind += 1;

        rs = pstm.executeQuery();

        if (rs != null && rs.next()) {

            if (rs.getObject("dsagen") != null) {
                dsagen = rs.getString("dsagen");
            }

        }

        return dsagen;
    }

    public ResultSet getDropdown() throws Exception {
        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        l_query = "";
        l_query += " select agen.cdagen                  \n";
        l_query += "      , agen.dsagen                  \n";
        l_query += "   from pgmr.archagen  agen          \n";
        l_query += "  where agen.cdazie = '" + cdazie + "'   \n";
        if (!s_cdcapoarea.equals("")) {
            l_query += "  and agen.cdcapo = '" + s_cdcapoarea + "'  \n";
        }
        l_query += "  order by agen.dsagen               \n";


        pstm = setQuery(l_query);

        ind = 1;

        rs = pstm.executeQuery();

        return rs;


    }

    /**
     * *
     *
     *
     */
    public ResultSet search(String cdagen_m, String dsagen, String cerca, boolean is_count, String order_by) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        cdagen_m = par_like(cdagen_m);
        dsagen = par_like(dsagen);
        cerca = par_like(cerca);

        if (order_by.equals("")) {
            order_by = "agen.dsagen, agen.cdagen_m";
        }

        if (is_count) {
            order_by = "";
        }

        if (is_count) {

            l_query = "";
            l_query += "   select count(1)  \n";
        } else {

            l_query = "";
            l_query += "   select agen.cdagen                   \n";
            l_query += "        , agen.cdagen_m                 \n";
            l_query += "        , agen.dsagen                   \n";
        }



        l_query += "     from pgmr.archagen     agen          \n";
        l_query += "    where agen.cdazie = '" + cdazie + "'      \n";
        if (!s_cdcapoarea.equals("")) {
            l_query += "  and agen.cdcapo = '" + s_cdcapoarea + "'  \n";
        }
        if (!s_cdagen.equals("")) {
            l_query += "  and agen.cdagen = '" + s_cdagen + "'  \n";
        }
        if (!cdagen_m.equals("")) {
            l_query += "  and agen.cdagen_m like '" + cdagen_m + "'  \n";
        }
        if (!dsagen.equals("")) {
            l_query += "  and upper(agen.dsagen) like '" + dsagen + "'  \n";
        }


        if (!cerca.equals("")) {
            l_query += "  and (  agen.cdagen_m  like '" + cerca + "'  \n";
            l_query += "      or upper(agen.dsagen) like '" + cerca + "'  \n";
            l_query += "               )  \n";
        }

        if (!order_by.equals("")) {
            l_query += "   order by " + order_by + "  \n";
        }


        rs = sql_query_ric(l_query);


        return rs;

    }

    // ***
    // ***  ***  Gestione CAPI AEREA  ***
    // ***
    public boolean isCapoArea(String cdagen) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        // tutti i capiarea

        l_query = "";
        l_query += " select cdagen                      \n";
        l_query += "      , cdagen_m                    \n";
        l_query += "      , dsagen                      \n";
        l_query += "   from pgmr.archagen               \n";
        l_query += "  where cdagen = cdcapo         \n";
        l_query += "    and cdazie = '" + cdazie + "'       \n";
        l_query += "    and cdcapo = '" + cdagen + "'   \n";

        String ls_cdagen = sql_String(l_query);

        if (!ls_cdagen.equals("")) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * *
     *
     *
     */
    public ResultSet getCapoArea(String cdagen) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        if (!cdagen.equals("")) {

            l_query = "";
            l_query += "   select cdagen                                              \n";
            l_query += "        , cdagen_m                                            \n";
            l_query += "        , dsagen                                              \n";
            l_query += "     from pgmr.archagen                                       \n";
            l_query += "    where cdagen = cdcapo                                 \n";
            l_query += "      and cdazie = '" + cdazie + "'                               \n";
            l_query += "      and cdcapo = ( select iagen.cdcapo              \n";
            l_query += "                           from pgmr.archagen iagen           \n";
            l_query += "                          where iagen.cdagen = '" + cdagen + "'   \n";
            l_query += "                            and iagen.cdazie = '" + cdazie + "'   \n";
            l_query += "                                       )                      \n";
            l_query += "    order by cdagen_m                                         \n";

        } else {

            // tutti i capiarea

            l_query = "";
            l_query += " select cdagen                  \n";
            l_query += "      , cdagen_m                \n";
            l_query += "      , dsagen                  \n";
            l_query += "   from pgmr.archagen           \n";
            l_query += "  where cdagen = cdcapo     \n";
            l_query += "    and cdazie = '" + cdazie + "'   \n";
            l_query += "  order by cdagen_m             \n";
        }



        rs = sql_query(l_query);

        return rs;

    }

    /**
     * *
     *
     *
     */
    public ResultSet getCA_agenti(String cdcapo) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;


        l_query = "";
        l_query += " select cdagen                        \n";
        l_query += "      , cdagen_m                      \n";
        l_query += "      , dsagen                        \n";
        l_query += "      , cdcapo                    \n";
        l_query += "   from pgmr.archagen                 \n";
        l_query += "  where cdcapo = '" + cdcapo + "' \n";
        l_query += "    and cdazie     = '" + cdazie + "'     \n";
        l_query += "  order by cdagen_m                   \n";

        rs = sql_query(l_query);

        return rs;


 
    }
    
    public String of_getEmail (String cdagen, String cdfunzione_m, String cdutente_tpservizio_m) throws Exception {
      
      return (String) of_getAttributi ("email", cdagen, cdfunzione_m, cdutente_tpservizio_m);
    }

    public String of_getLingua (String cdagen, String cdfunzione_m, String cdutente_tpservizio_m) throws Exception {
      
      return (String) of_getAttributi ("cdling", cdagen, cdfunzione_m, cdutente_tpservizio_m);
    }

    public String of_getCellulare (String cdagen, String cdfunzione_m, String cdutente_tpservizio_m) throws Exception {
      
      return (String) of_getAttributi ("cellulare", cdagen, cdfunzione_m, cdutente_tpservizio_m);
    }

    /**
    Metodo che mi permette di recuperare degli attributi del ispettore secondo le logiche specifiche del cliente
    
    @param tpattributo
    @param cdagen
    @param cdfunzione_m
    @param cdutente_tpservizio_m
    @return
    @throws Exception 
    */
    public Object of_getAttributi (String tpattributo, String cdagen, String cdfunzione_m, String cdutente_tpservizio_m) throws Exception {
      int ind = 0;
      int tot_rec = 0;
      PreparedStatement pstm = null;
      ResultSet rs = null;

      F_utente f_utente = new F_utente();

      setProfilo((Atk_sql) f_utente);

      if (
             !tpattributo.equals("cdling")
          && !tpattributo.equals("email")
          && !tpattributo.equals("cellulare")
         ){
        throw new Exception("Attributo non gestito");
      }

      Object lo_ = null;

      String ls_cdling = "";
      String ls_email  = "";
      String ls_cellulare  = "";
      

      /**
      Recupero attributi da utente
      */

      long   ll_eute_tkutente = 0;
      String ls_eute_cdling = "";
      String ls_eute_email  = "";
      String ls_eute_cellulare  = "";

      
      l_query   = "";
      l_query  += " select eute.tkutente                               \n";
      l_query  += "      , eute.cdling                                 \n";
      l_query  += "      , eute.email                                  \n";
      l_query  += "      , eute.cellulare                              \n";
      l_query  += "   from pgmr.ep_utente      eute                    \n";
      l_query  += "  where eute.cdazie = '"+ cdazie +"'                \n";
      l_query  += "    and eute.cdagen = '"+ cdagen +"'                \n";

      rs = sql_query(l_query);

      if (rs!=null && rs.next()){
        ll_eute_tkutente = 0;
        ls_eute_cdling = "";
        ls_eute_email  = "";
        ls_eute_cellulare  = "";

        if (rs.getObject("tkutente")!= null)  ll_eute_tkutente  = rs.getLong  ("tkutente");
        if (rs.getObject("cdling"  )!= null)  ls_eute_cdling    = rs.getString("cdling"  );
        if (rs.getObject("email"   )!= null)  ls_eute_email     = rs.getString("email"   );
        if (rs.getObject("cellulare"   )!= null)  ls_eute_cellulare     = rs.getString("cellulare"   );

        if (!cdfunzione_m.equals("")){
          ls_eute_email    = f_utente.getEmailFunzione(ll_eute_tkutente, cdfunzione_m);
        } else if (!cdutente_tpservizio_m.equals("")){
          ls_eute_email    = f_utente.getEmailTiposervizio(ll_eute_tkutente, cdutente_tpservizio_m);
        }

      }



      ls_cdling   = ls_eute_cdling;
      ls_email    = ls_eute_email ;
      ls_cellulare    = ls_eute_cellulare ;

      
      if (tpattributo.equals("cdling")) lo_ = ls_cdling;
      if (tpattributo.equals("email") ) lo_ = ls_email;
      if (tpattributo.equals("cellulare") ) lo_ = ls_cellulare;

      f_utente.close();
      
      return lo_;
    }
}
