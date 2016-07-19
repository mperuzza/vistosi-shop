package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Condven extends com.ateikon.standard.Condven {


    public Condven() {

        super();
    }










    /***


    */

    public ResultSet getCondizioni ( String cdclac
                                   , String tkclie
                                   , String fgnew
                                                    ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        
        tot_rec = 0;


        if (!cdclac.equals("")){
            
            l_query  = "";
            l_query += " select impmin                 \n";
            l_query += "      , qtamin                 \n";
            l_query += "      , tkcondven              \n";
            l_query += "   from pgmr.condven           \n";
            l_query += "  where cdclac = '"+cdclac+"'  \n";
            l_query += "    and tkclie is null         \n";
            l_query += "    and fgnew  = '"+fgnew+"'   \n";

        }else if (!tkclie.equals("")){

            l_query  = "";
            l_query += " select impmin                 \n";
            l_query += "      , qtamin                 \n";
            l_query += "      , tkcondven              \n";
            l_query += "   from pgmr.condven           \n";
            l_query += "  where tkclie = '"+tkclie+"'  \n";
            l_query += "    and cdclac is null         \n";
            l_query += "    and fgnew  = '"+fgnew+"'   \n";

        }else {
            
            l_query  = "";
            l_query += " select impmin                 \n";
            l_query += "      , qtamin                 \n";
            l_query += "      , tkcondven              \n";
            l_query += "   from pgmr.condven           \n";
            l_query += "  where tkclie is null         \n";
            l_query += "    and cdclac is null         \n";
            l_query += "    and fgnew  = '"+fgnew+"'   \n";
        }


        rs = sql_query(l_query);

        return rs ;

    }




    /***


    */

    public ResultSet getClienti_ins( ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        
        tot_rec = 0;

        l_query  = "";
        l_query += " select a.tkclie              \n";
        l_query += "      , a.cdclie_m            \n";
        l_query += "      , b.ragcog              \n";
        l_query += "   from pgmr.archclie a       \n";
        l_query += "      , pgmr.archenti b       \n";
        l_query += "      , pgmr.condven  c       \n";
        l_query += "  where a.cdente = b.cdente   \n";
        l_query += "    and a.tkclie = c.tkclie   \n";
        l_query += "    and c.cdclac = null       \n";
        l_query += "  order by ragcog             \n";

        rs = sql_query(l_query);

        return rs ;

    }



    /***


    */

    public ResultSet getClienti( String filtro
                                                    ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        filtro = par_like(filtro);

        
        tot_rec = 0;

        l_query  = "";
        l_query += " select a.tkclie                                     \n";
        l_query += "      , a.cdclie_m                                   \n";
        l_query += "      , b.ragcog                                     \n";
        l_query += "   from pgmr.archclie a                              \n";
        l_query += "      , pgmr.archenti b                              \n";
        l_query += "  where a.cdente = b.cdente                          \n";
        l_query += "    and a.tkclie not in (select tkclie               \n";
        l_query += "                           from pgmr.condvend        \n";
        l_query += "                          where tkclie is not null   \n";
        l_query += "                            and cdclac is null       \n";
        l_query += "                             )                       \n";
        l_query += "    and ( ragcog like '"+filtro+"'                   \n";
        l_query += "          or cdclie_m like '"+filtro+"' )            \n";
        l_query += "  order by ragcog                                    \n";

        rs = sql_query_ric(l_query);

        return rs ;

    }



    /***


    */

    public int getCount( String filtro
                                       ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        filtro = par_like(filtro);

        
        tot_rec = 0;

        l_query  = "";
        l_query += " select count(*)                                     \n";
        l_query += "   from pgmr.archclie a                              \n";
        l_query += "      , pgmr.archenti b                              \n";
        l_query += "  where a.cdente = b.cdente                          \n";
        l_query += "    and a.tkclie not in (select tkclie               \n";
        l_query += "                           from pgmr.condvend        \n";
        l_query += "                          where tkclie is not null   \n";
        l_query += "                            and cdclac is null       \n";
        l_query += "                             )                       \n";
        l_query += "    and ( ragcog like '"+filtro+"'                   \n";
        l_query += "          or cdclie_m like '"+filtro+"' )            \n";

        int li_ = sql_int(l_query);

        return li_;
    }





    /***


    */

    public int delete ( long tkcondven) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec = 0;

        l_query  = "";
        l_query += " delete from pgmr.condven where tkcondven = "+tkcondven+" \n";

        tot_rec = sql_update(l_query);


        return tot_rec;

    }



}

