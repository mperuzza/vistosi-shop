package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Cat_utente extends com.ateikon.standard.Cat_utente {


    public Cat_utente() {

        super();
    }





    /***


    */

    public ResultSet getUser_name ( String user_name 
                                  , String cdazie
                                  , String cddipa
                                                      ) throws Exception {
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        tot_rec = 0;

        l_query  = "";
        l_query += " select *                  \n";
        l_query += "   from pgmr.cat_utente    \n";
        l_query += "  where user_name = ?      \n";
        l_query += "    and cdazie    = ?      \n";
        l_query += "    and cddipa    = ?      \n";

        pstm = setQuery(l_query);

        ind = 1;
        pstm.setString(ind++, user_name);
        pstm.setString(ind++, cdazie);
        pstm.setString(ind++, cddipa);

        rs = pstm.executeQuery();

        return rs ;

    }


    /***


    */

    public ResultSet search ( String  cerca
                         , String  fgadmin
                         , boolean is_count 
                         , String  order_by
                                                ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        cerca    = par_like(cerca   );

        if (order_by.equals("")) order_by = "uten.dsutente";

        if (is_count) order_by = "";

        if (is_count){
            
            l_query   = "";
            l_query  += "   select count(1)  \n";
        }else {
            
            l_query   = "";
            l_query  += "   select uten.* \n";
        }


  
        l_query  += "     from pgmr.cat_utente    uten          \n";
        l_query  += "    where uten.cdazie = '"+cdazie+"'      \n";


        if (!cerca.equals("")){
            l_query  += "  and (  upper(uten.user_name) like '"+cerca+"'  \n";
            l_query  += "      or upper(uten.dsutente ) like '"+cerca+"'  \n";
            l_query  += "               )  \n";
        }
        if (!fgadmin.equals("")){
            l_query  += "  and uten.fgadmin =  '"+fgadmin+"'  \n";
        }

        if (!order_by.equals("")){
            l_query  += "   order by "+order_by+"  \n";
        }


        rs = sql_query_ric(l_query);


        return rs ;

    }




    /***
        restituisce il primo utente per quella entità
    */

    public long getUesr_of ( String tkclie
                           , String cdagen
                                                ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        long ll_tkutente = 0;

        tot_rec = 0;

        l_query  = "";
        l_query += " select tkutente        \n";
        l_query += "   from pgmr.cat_utente \n";
        l_query += "  where cdazie = ?      \n";
        l_query += "    and cddipa = ?      \n";
        if (!tkclie.equals("")){
            l_query += "    and tkclie = ?  \n";
        }
        if (!cdagen.equals("")){
            l_query += "    and cdagen = ?  \n";
        }
        l_query += "  order by tkutente     \n";

        pstm = setQuery(l_query);

        ind = 1;
        pstm.setString(ind++, cdazie);
        pstm.setString(ind++, cddipa);
        if (!tkclie.equals("")){
            pstm.setString(ind++, tkclie);
        }
        if (!cdagen.equals("")){
            pstm.setString(ind++, cdagen);
        }

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){
            
            if (rs.getObject(1)!=null)  ll_tkutente = rs.getLong(1);

        }


        return ll_tkutente ;

    }






    /***


    */

    public int delete (long tkutente ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec = 0;

        l_query  = "";
        l_query += " delete                          \n";
        l_query += "   from pgmr.cat_funz_user       \n";
        l_query += "  where tkutente = "+tkutente+"  \n";

        tot_rec = sql_update(l_query);



        l_query  = "";
        l_query += " delete                          \n";
        l_query += "   from pgmr.cat_utente          \n";
        l_query += "  where tkutente = "+tkutente+"  \n";

        tot_rec = sql_update(l_query);

        return tot_rec;

    }





    /***
        --- Sovrascrivo
    */


    public long getNew_token( ) throws Exception{ 
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        Cat_contator cat_contator = new Cat_contator();

        setProfilo((Atk_sql)cat_contator );
                                                   
        long nprogr = cat_contator.getNprogr ("00", "TKUTENTE", "AAAA" ) ;


        return nprogr;


    }





}

