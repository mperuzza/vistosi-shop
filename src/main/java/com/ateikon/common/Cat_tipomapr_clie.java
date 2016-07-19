package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Cat_tipomapr_clie extends com.ateikon.standard.Cat_tipomapr_clie {


    public Cat_tipomapr_clie() {

        super();
    }

    /***


    */

    public int delete_all ( String tkclie) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query  = "";
        l_query += " delete                        \n";
        l_query += "   from pgmr.cat_tipomapr_clie \n";
        l_query += "  where tkclie = '"+tkclie+"'  \n";
        l_query += "    and cdazie = '"+cdazie+"'  \n";
        

        pstm = m_connection.prepareStatement(l_query);

        tot_rec = pstm.executeUpdate();

        pstm.close();

        return tot_rec;

    }





    /***


    */

    public ResultSet getRiepilogo (String  order_by  ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        if (order_by.equals("")){
            order_by = "ente.ragcog, tipm.cdtipm_m " ;
        }

        l_query  = "";
        l_query += " select clie.tkclie                        \n";
        l_query += "      , clie.cdclie_m                      \n";
        l_query += "      , ente.ragcog                        \n";
        l_query += "      , tipm.cdtipm                        \n";
        l_query += "      , tipm.cdtipm_m                      \n";
        l_query += "      , tipm.dstipm                        \n";
        l_query += "      , cltm.dtinse                        \n";
        l_query += "      , uten.tkutente                      \n";
        l_query += "   from pgmr.archclie clie                 \n";
        l_query += "      , pgmr.archenti ente                 \n";
        l_query += "      , pgmr.tipomapr tipm                 \n";
        l_query += "      , pgmr.cat_tipomapr_clie cltm        \n";
        l_query += "      , pgmr.cat_utente uten               \n";
        l_query += "  where cltm.cdazie = clie.cdazie          \n";
        l_query += "    and cltm.tkclie = clie.tkclie          \n";
        l_query += "    and clie.cdazie = '"+cdazie+"'         \n";
        l_query += "    and clie.cdente = ente.cdente          \n";
        l_query += "    and tipm.cdtipm = cltm.cdtipm          \n";
        l_query += "    and uten.cdazie = clie.cdazie          \n";
        l_query += "    and uten.tkclie = clie.tkclie          \n";
        if (!s_cdcapoarea.equals("")){
		l_query  += "  and clie.cdagen in (select iagen.cdagen from pgmr.archagen iagen where iagen.cdcapo = '"+s_cdcapoarea+"')  \n";
        }
        if (!s_cdagen.equals("")){
		l_query  += "  and clie.cdagen = '"+s_cdagen+"'  \n";
        }
        if (!s_tkclie.equals("")){
		l_query  += "  and clie.tkclie= '"+s_tkclie+"'  \n";
        }
  
        if (!order_by.equals("")){
            l_query  += "  order by "+order_by+"  \n";    
        }

        pstm = setQuery_ric(l_query);


        ind = 1;

        rs = pstm.executeQuery();

        return rs;

    }










}

