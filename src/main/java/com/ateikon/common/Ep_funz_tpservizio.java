package com.ateikon.common;

import com.ateikon.function.F_gesttab_azienda;
import com.ateikon.structure.Str_ep_tabset;
import java.sql.ResultSet;
import java.sql.PreparedStatement;


public class Ep_funz_tpservizio extends com.ateikon.standard.Ep_funz_tpservizio {


    public Ep_funz_tpservizio() {

        super();
    }

    /***


    */

    public ResultSet search ( String  cdfunzione_m
                            , boolean is_count
                            , String  order_by
                                                   ) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        if (order_by.equals("")) order_by = "efts.tkutente_tpservizio";

        if (is_count) order_by = "";

        if (is_count){

            l_query   = "";
            l_query  += "   select count(1)  \n";
        }else {

            l_query   = "";
            l_query  += "   select efts.*                        \n";
            l_query  += "        , utps.cdutente_tpservizio_m    \n";
        }



        if (is_oracle){
          throw new Exception("DB non supportato");
        } else if (is_sybase){
          l_query  += "   from pgmr.ep_funz_tpservizio      efts                                      \n";
          l_query  += "      , pgmr.ep_funz                 efun                                      \n";
          l_query  += "      , pgmr.ep_utente_tpservizio    utps                                      \n";
          l_query  += "  where efts.tkfunzione = efun.tkfunzione                                      \n";
          l_query  += "    and efts.tkutente_tpservizio = utps.tkutente_tpservizio                    \n";
          l_query  += par_azieDipa("ep_funz_tpservizio");
        } else {
          throw new Exception("DB non supportato");
        }

        if (!cdfunzione_m.equals("")){
          l_query  += "      and efun.cdfunzione_m = '"+ cdfunzione_m +"'                                \n";
        }

        if (!order_by.equals("")){
            l_query  += "   order by "+order_by+"  \n";
        }

        //System.out.println("\n"+ l_query);

        rs = sql_query_ric(l_query);


        return rs ;

    }



    public String par_azieDipa(String as_table) throws Exception{


        String ls_alias = "";

        as_table = as_table.trim().toLowerCase();

        if (as_table.equals("ep_funz_tpservizio")) ls_alias = "efts";

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

