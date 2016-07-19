package com.ateikon.common;

import com.ateikon.function.F_gesttab_azienda;
import com.ateikon.structure.Str_ep_tabset;
import java.sql.ResultSet;
import java.sql.PreparedStatement;


public class Ep_utente_tpservizio extends com.ateikon.standard.Ep_utente_tpservizio {


    public Ep_utente_tpservizio() {

        super();
    }


    public String getDs ( long  tkutente_tpservizio ) throws Exception {

      int               ind = 0;
      int               tot_rec = 0;
      PreparedStatement pstm = null;
      ResultSet         rs = null;

      l_query   = "";
      l_query  += "   select "+ of_descr_lingua(s_cdling, "utps", "dsutente_tpservizio") +" as dsutente_tpservizio                                \n";
      l_query  += "     from pgmr.ep_utente_tpservizio     utps                      \n";
      l_query  += "    where utps.tkutente_tpservizio = "+ tkutente_tpservizio +"    \n";
      l_query  += par_azieDipa("ep_utente_tpservizio");

      return sql_String(l_query);


    }

    /****
        getKey: ep_utente_tpservizio
    **/

    public long getKey_m( String       cdutente_tpservizio_m
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        long ll_ = 0;

        l_query   = "";
        l_query  += " select utps.tkutente_tpservizio                                    \n";
        l_query  += "   from pgmr.ep_utente_tpservizio utps                              \n";
        l_query  += "  where utps.cdutente_tpservizio_m = '"+ cdutente_tpservizio_m +"'  \n";
        l_query  += par_azieDipa("ep_utente_tpservizio");

        ll_ = sql_long(l_query);

        return ll_;

    }



    /***


    */

    public ResultSet search ( String  cdling
                            , boolean is_count
                            , String  order_by
                                                   ) throws Exception {

    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        if (order_by.equals("")) order_by = "utps.nrposi";

        if (is_count) order_by = "";

        if (is_count){

            l_query   = "";
            l_query  += "   select count(1)  \n";
        }else {

            l_query   = "";
            l_query  += "   select utps.tkutente_tpservizio                                           \n";
            l_query  += "        , utps.cdutente_tpservizio_m                                         \n";
            l_query  += "        , "+ of_descr_lingua(cdling, "utps", "dsutente_tpservizio") +" as dsutente_tpservizio \n";
        }



	       if (is_oracle){
          throw new Exception("DB non supportato");
       	} else if (is_sybase){
          l_query  += "     from pgmr.ep_utente_tpservizio    utps                                    \n";
          l_query  += "    where 1 = 1                                                                \n";
          l_query  += par_azieDipa("ep_utente_tpservizio");
        } else {
          throw new Exception("DB non supportato");
        }

        if (!order_by.equals("")){
            l_query  += "   order by "+order_by+"  \n";
        }

        rs = sql_query_ric(l_query);


        return rs ;

    }


    public String par_azieDipa(String as_table) throws Exception{


        String ls_alias = "";

        as_table = as_table.trim().toLowerCase();

        if (as_table.equals("ep_utente_tpservizio")) ls_alias = "utps";

        return par_azieDipa(as_table, ls_alias);
    }

    public String par_azieDipa(String as_table, String as_alias) throws Exception{

        ResultSet         rs   = null;

        F_gesttab_azienda f_gesttab_azienda = new F_gesttab_azienda();

        setProfilo((Atk_sql) f_gesttab_azienda);

        as_table = as_table.trim().toLowerCase();

        boolean lb_fazienda      = false;
        boolean lb_fazienda_user = false;

        Str_ep_tabset lstr_ep_tabset = new Str_ep_tabset ();

        lstr_ep_tabset = f_gesttab_azienda.getEp_tabset(as_table);

        if (!lstr_ep_tabset.fazienda.equals("S")) lstr_ep_tabset.fazienda = "N";

        if (lstr_ep_tabset.fazienda.equals("S")){
            lb_fazienda = false;
        }else {
            lb_fazienda = true;
        }

        if (!lstr_ep_tabset.fazienda_user.equals("S")) lstr_ep_tabset.fazienda_user = "N";

        if (lstr_ep_tabset.fazienda_user.equals("S")){
            lb_fazienda_user = false;
        }else {
            lb_fazienda_user = true;
        }

        String ls_ = "";
        if (!as_alias.equals("")) as_alias = as_alias+".";


        if (lstr_ep_tabset.fconcrete.equals("S")){
            String ls_cdazie_concrete = "";
            String ls_cddipa_concrete = "";

            l_query  = "";
            l_query += " select cdazie                                                         \n";
            l_query += "      , cddipa                                                         \n";
            l_query += "   from pgmr.azienda                                                   \n";
            l_query += "  where cdazie in (select iuten.cdazie_comp                            \n";
            l_query += "                                from pgmr.ep_utente_azie iuten         \n";
            l_query += "                               where iuten.tkutente = "+s_tkutente+"   \n";
            l_query += "                                       )                               \n";

            rs = sql_query(l_query);

            ls_  += "   and ( 1 = 2   \n";

            while (rs!=null && rs.next()){
                if (rs.getObject("cdazie"     )!=null)  ls_cdazie_concrete      = rs.getString("cdazie"     );
                if (rs.getObject("cddipa"     )!=null)  ls_cddipa_concrete      = rs.getString("cddipa"     );

                ls_  += "         or (    "+as_alias+"cdazie = '"+ls_cdazie_concrete+"'   \n";
                ls_  += "             and "+as_alias+"cddipa = '"+ls_cddipa_concrete+"' )  \n";
            }
            ls_  += "   )   \n";

        }else if (lb_fazienda_user){

            //In caso abbia s_tkutente è un utente altrimenti è un servizio
            if (s_tkutente > 0){
               ls_  += "   and "+as_alias+"cdazie in (select iuten.cdazie_comp                 \n";
               ls_  += "                                from pgmr.ep_utente_azie iuten         \n";
               ls_  += "                               where iuten.tkutente = "+s_tkutente+"   \n";
               ls_  += "                                       )                               \n";
            }

        }else if (lb_fazienda){
            ls_  += "    and "+as_alias+"cdazie = '"+cdazie+"' \n";
        }


        // ---- Gestione sicurezza



        return ls_;
    }




}

