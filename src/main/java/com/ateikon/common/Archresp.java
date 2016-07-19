package com.ateikon.common;


import com.ateikon.function.F_utente;
import java.sql.ResultSet;
import java.sql.PreparedStatement;


public class Archresp extends Atk_sql {

    public Archresp() {
        
        super();
    }


    public ResultSet getCdresp(String cdresp) throws Exception {
        
        return  getKey(cdresp);
    }

    public ResultSet getKey(String cdresp) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;   //avendramin20071228

        l_query   = "";
		l_query  += " select *                      \n";
		l_query  += "   from pgmr.archresp          \n"; //avendramin20071228
		l_query  += "  where cdresp = ?             \n";
		l_query  += "    and cdazie = ?             \n";

		pstm = setQuery( l_query ) ;

        ind = 1;

        pstm.setString(ind, cdresp); ind += 1;
        pstm.setString(ind, cdazie); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }
    public ResultSet getKey_m(String cdresp_m) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;   //avendramin20071228

        l_query   = "";
		l_query  += " select *                      \n";
		l_query  += "   from pgmr.archresp      \n";    //avendramin20071228
		l_query  += "  where cdresp_m = ?             \n";
		l_query  += "    and cdazie = ?             \n";

		pstm = setQuery( l_query ) ;

        ind = 1;

        pstm.setString(ind, cdresp_m); ind += 1;
        pstm.setString(ind, cdazie); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }

    public ResultSet getDescr() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;    //avendramin20071228

        l_query   = "";
		l_query  += " select *                      \n";
		l_query  += "   from pgmr.archresp      \n";       //avendramin20071228
		l_query  += "  where cdazie = ?             \n";
		l_query  += "  order by dsresp              \n";

		pstm = setQuery( l_query ) ;

        ind = 1;

        pstm.setString(ind, cdazie); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }


  public String getDsresp(String cdresp) throws Exception {

        int               ind     = 0;
        int               tot_rec = 0;
        PreparedStatement pstm    = null;
        ResultSet         rs      = null;
        String            dsresp    = "";


        l_query   = "";
        l_query  += " select dsresp                  \n";
        l_query  += "   from pgmr.archresp      \n";       //avendramin20071228
        l_query  += "  where cdresp = ?              \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString( ind, cdresp); ind += 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            if (rs.getObject("dsresp")!=null ) dsresp = rs.getString("dsresp");

        }

        return dsresp;
    }

    public ResultSet getDropdown() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;    //avendramin20071228

        l_query   = "";
		l_query  += " select r.cdresp       \n";
		l_query  += "      , r.dsresp       \n";
		l_query  += "   from pgmr.archresp r                       \n";    //avendramin20071228
		l_query  += "  where r.cdazie = '"+cdazie+"'               \n";
		l_query  += "  order by r.dsresp        \n";

		pstm = setQuery( l_query ) ;

        ind = 1;

        rs = pstm.executeQuery();

        return rs;


    }

    public String of_getEmail (String cdresp, String cdfunzione_m, String cdutente_tpservizio_m) throws Exception {
      
      return (String) of_getAttributi ("email", cdresp, cdfunzione_m, cdutente_tpservizio_m);
    }

    public String of_getLingua (String cdresp, String cdfunzione_m, String cdutente_tpservizio_m) throws Exception {
      
      return (String) of_getAttributi ("cdling", cdresp, cdfunzione_m, cdutente_tpservizio_m);
    }

    public String of_getCell (String cdresp, String cdfunzione_m, String cdutente_tpservizio_m) throws Exception {
      
      return (String) of_getAttributi ("cell", cdresp, cdfunzione_m, cdutente_tpservizio_m);
    }

    /**
    Metodo che mi permette di recuperare degli attributi del ispettore secondo le logiche specifiche del cliente
    
    @param tpattributo
    @param cdresp
    @param cdfunzione_m
    @param cdutente_tpservizio_m
    @return
    @throws Exception 
    */
    public Object of_getAttributi (String tpattributo, String cdresp, String cdfunzione_m, String cdutente_tpservizio_m) throws Exception {
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
      l_query  += "    and eute.cdresp = '"+ cdresp +"'                \n";

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
      ls_cell     = ls_eute_cell ;

      
      if (tpattributo.equals("cdling")) lo_ = ls_cdling;
      if (tpattributo.equals("email") ) lo_ = ls_email;
      if (tpattributo.equals("cell") ) lo_ = ls_cell;

      f_utente.close();
      
      return lo_;
    }
}
