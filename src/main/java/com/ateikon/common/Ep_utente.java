package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Ep_utente extends com.ateikon.standard.Ep_utente {


    public Ep_utente() {

        super();
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


  
        l_query  += "     from pgmr.ep_utente    uten          \n";
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


    */

    public int delete (long tkutente ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec = 0;

        l_query  = "";
        l_query += " delete                          \n";
        l_query += "   from pgmr.ep_funz_user        \n";
        l_query += "  where tkutente = "+tkutente+"  \n";

        tot_rec = sql_update(l_query);



        l_query  = "";
        l_query += " delete                          \n";
        l_query += "   from pgmr.ep_utente           \n";
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

        Ep_contator ep_contator = new Ep_contator();

        setProfilo((Atk_sql)ep_contator );
                                                   
        long nprogr = ep_contator.getNprogr ("00", "TKUTENTE", "AAAA" ) ;


        return nprogr;


    }

    //avendramin20080718 >
    /***


    */

    public ResultSet getUser (String user_name ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        user_name = user_name.trim().toUpperCase();
        
        l_query  = "";
        l_query += " select *                           \n";
        l_query += "   from pgmr.ep_utente              \n";
        l_query += "  where user_name = '"+user_name+"'  \n";


        rs = sql_query(l_query);


        return rs ;

    }
    //avendramin20080718 <

    /***
        EAR - 20091012 - I

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
        l_query += "   from pgmr.ep_utente    \n";
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

    //EAR - 20091012 - F

    
    //EAR 20010707
    /***


    */

    public ResultSet getUserByTkclie (String tkclie ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        
        l_query  = "";
        l_query += " select *                           \n";
        l_query += "   from pgmr.ep_utente              \n";
        l_query += "  where tkclie = '"+tkclie+"'  \n";


        rs = sql_query(l_query);


        return rs ;

    }    

}

