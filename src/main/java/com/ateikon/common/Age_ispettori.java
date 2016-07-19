/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ateikon.common;

import com.ateikon.function.F_utente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author pdefranceschi
 */
public class Age_ispettori extends Atk_sql {

    public Age_ispettori() {
        
        super();
    }
    
    public ResultSet getCdispe(String cdispe) throws Exception {
        
        return  getKey(cdispe);
    }

    public ResultSet getKey(String cdispe) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select *                       \n";
		l_query  += "   from pgmr.age_ispettori      \n";
		l_query  += "  where cdispe = ?              \n";
		l_query  += "    and cdazie = ?              \n";

		pstm = setQuery( l_query ) ;

        ind = 1;

        pstm.setString(ind, cdispe); ind += 1;
        pstm.setString(ind, cdazie); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }
    public ResultSet getKey_m(String cdispe_m) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select *                       \n";
        l_query  += "   from pgmr.age_ispettori      \n";
        l_query  += "  where cdispe_m = ?            \n";
        l_query  += "    and cdazie = ?             \n";

        pstm = setQuery( l_query ) ;

        ind = 1;

        pstm.setString(ind, cdispe_m); ind += 1;
        pstm.setString(ind, cdazie); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }




    public String getDsispe(String cdispe) throws Exception {

        int               ind     = 0;
        int               tot_rec = 0;
        PreparedStatement pstm    = null;
        ResultSet         rs      = null;
        String            dsispe  = "";


        l_query   = "";
        l_query  += " select dsispe                  \n";
        l_query  += "   from pgmr.age_ispettori      \n";
        l_query  += "  where cdispe = ?              \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString( ind, cdispe); ind += 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            if (rs.getObject("dsispe")!=null ) dsispe = rs.getString("dsispe");

        }

        return dsispe;
    }

    public ResultSet getDropdown() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select cdispe                \n";
		l_query  += "      , dsispe                \n";
		l_query  += "   from pgmr.age_ispettori    \n";
		l_query  += "  where cdazie = '"+cdazie+"' \n";
		l_query  += "  order by dsispe             \n";

		pstm = setQuery( l_query ) ;

        ind = 1;

        rs = pstm.executeQuery();

        return rs;

    }

     /***


    */

    public ResultSet search ( String  cerca
                         , boolean is_count 
                         , String  order_by
                                                ) throws Exception {
        
        return search ( ""
                      , ""
                      , cerca
                      , is_count
                      , order_by
                                 );
        
    }
    
  
    /***


    */

    public ResultSet search ( String  cdispe_m
                         , String  dsispe
                         , String  cerca
                         , boolean is_count 
                         , String  order_by
                                                ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        cdispe_m = par_like(cdispe_m  );
        dsispe   = par_like(dsispe  );
        cerca    = par_like(cerca   );

        if (order_by.equals("")) order_by = "ispe.dsispe, ispe.cdispe_m";

        if (is_count) order_by = "";

        if (is_count){
            
            l_query   = "";
            l_query  += "   select count(1)  \n";
        }else {
            
            l_query   = "";
            l_query  += "   select ispe.cdispe                   \n";
            l_query  += "        , ispe.cdispe_m                 \n";
            l_query  += "        , ispe.dsispe                   \n";
        }


  
        l_query  += "     from pgmr.age_ispettori     ispe       \n";
        l_query  += "    where ispe.cdazie = '"+cdazie+"'      \n";

        if (!s_cdispe.equals("")){
            l_query  += "  and ispe.cdispe = '"+s_cdispe+"'  \n";
        }
        if (!s_cdcapoarea.equals("")){
            l_query  += "  and 1 = 2 \n";
        }

        if (!s_cdagen.equals("")){
            l_query  += "  and 1 = 2 \n";
        }

        if (!s_tkclie.equals("")){
            l_query  += "  and 1 = 2 \n";
        }
        if (!cdispe_m.equals("")){
            l_query  += "  and ispe.cdispe_m like '"+cdispe_m+"'  \n";
        }
        if (!dsispe.equals("")){
            l_query  += "  and upper(ispe.dsispe) like '"+dsispe+"'  \n";
        }


        if (!cerca.equals("")){
            l_query  += "  and (  ispe.cdispe_m  like '"+cerca+"'  \n";
            l_query  += "      or upper(ispe.dsispe) like '"+cerca+"'  \n";
            l_query  += "               )  \n";
        }

        if (!order_by.equals("")){
            l_query  += "   order by "+order_by+"  \n";
        }


        rs = sql_query_ric(l_query);


        return rs ;

    }


    public String of_getEmail (String cdispe, String cdfunzione_m, String cdutente_tpservizio_m) throws Exception {
      
      return (String) of_getAttributi ("email", cdispe, cdfunzione_m, cdutente_tpservizio_m);
    }

    public String of_getLingua (String cdispe, String cdfunzione_m, String cdutente_tpservizio_m) throws Exception {
      
      return (String) of_getAttributi ("cdling", cdispe, cdfunzione_m, cdutente_tpservizio_m);
    }

    public String of_getCell (String cdispe, String cdfunzione_m, String cdutente_tpservizio_m) throws Exception {
      
      return (String) of_getAttributi ("cell", cdispe, cdfunzione_m, cdutente_tpservizio_m);
    }

    /**
    Metodo che mi permette di recuperare degli attributi del ispettore secondo le logiche specifiche del cliente
    
    @param tpattributo
    @param cdispe
    @param cdfunzione_m
    @param cdutente_tpservizio_m
    @return
    @throws Exception 
    */
    public Object of_getAttributi (String tpattributo, String cdispe, String cdfunzione_m, String cdutente_tpservizio_m) throws Exception {
      int ind = 0;
      int tot_rec = 0;
      PreparedStatement pstm = null;
      ResultSet rs = null;

      F_utente f_utente = new F_utente();

      setProfilo((Atk_sql) f_utente);

      if (
             !tpattributo.equals("cdling")
          && !tpattributo.equals("email")
          && !tpattributo.equals("cell")
         ){
        throw new Exception("Attributo non gestito");
      }

      Object lo_ = null;

      String ls_cdling = "";
      String ls_email  = "";
      String ls_cell   = "";
      

      /**
      Recupero attributi da utente
      */

      long   ll_eute_tkutente = 0;
      String ls_eute_cdling = "";
      String ls_eute_email  = "";
      String ls_eute_cell   = "";

      
      l_query   = "";
      l_query  += " select eute.tkutente                               \n";
      l_query  += "      , eute.cdling                                 \n";
      l_query  += "      , eute.email                                  \n";
      l_query  += "      , eute.cellulare                              \n";
      l_query  += "   from pgmr.ep_utente      eute                    \n";
      l_query  += "  where eute.cdazie = '"+ cdazie +"'                \n";
      l_query  += "    and eute.cdispe = '"+ cdispe +"'                \n";

      rs = sql_query(l_query);

      if (rs!=null && rs.next()){
        ll_eute_tkutente = 0;
        ls_eute_cdling = "";
        ls_eute_email  = "";
        ls_eute_cell   = "";

        if (rs.getObject("tkutente")!= null)  ll_eute_tkutente  = rs.getLong  ("tkutente");
        if (rs.getObject("cdling"  )!= null)  ls_eute_cdling    = rs.getString("cdling"  );
        if (rs.getObject("email"   )!= null)  ls_eute_email     = rs.getString("email"   );
        if (rs.getObject("cellulare"    )!= null)  ls_eute_cell      = rs.getString("cellulare"    );

        if (!cdfunzione_m.equals("")){
          ls_eute_email    = f_utente.getEmailFunzione(ll_eute_tkutente, cdfunzione_m);
        } else if (!cdutente_tpservizio_m.equals("")){
          ls_eute_email    = f_utente.getEmailTiposervizio(ll_eute_tkutente, cdutente_tpservizio_m);
        }

      }



      ls_cdling   = ls_eute_cdling;
      ls_email    = ls_eute_email ;
      ls_cell     = ls_eute_cell  ;

      
      if (tpattributo.equals("cdling")) lo_ = ls_cdling;
      if (tpattributo.equals("email") ) lo_ = ls_email;
      if (tpattributo.equals("cell") ) lo_ = ls_cell;

      f_utente.close();
      
      return lo_;
    }
}
