package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Mrp_arch_stato extends com.ateikon.standard.Mrp_arch_stato {


    public Mrp_arch_stato() {

        super();
    }
    
    
    public ResultSet getList() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select *                       \n";
        l_query  += "   from pgmr.mrp_arch_stato     \n";
        l_query  += "   where vist_fgrgb = 'S'       \n";
        l_query  += "  order by dsstato              \n";

        pstm = setQuery( l_query ) ;

        ind = 1;

        rs = pstm.executeQuery();

        return rs;

    }
    
    
    public ResultSet getArtList(String cdarti, boolean is_count) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;
        
        
        String order_by = "mrp_arch_stato.vist_fgrgb desc";
        
        if (is_count) order_by = "";

        if (is_count){
            
            l_query   = "";
            l_query  += "   select count(*) as tot  \n";
        }else {
        
            l_query   = "";
            l_query  += "SELECT distinct(mrp_arch_stato.cdstato),\n"; 
            l_query  += "     mrp_arch_stato.vist_fgrgb, \n";                           
            l_query  += "     mrp_arch_stato.vist_rgb_r, \n";
            l_query  += "     mrp_arch_stato.vist_rgb_g, \n";
            l_query  += "     mrp_arch_stato.vist_rgb_b \n";
        }
        
        l_query  += " FROM pgmr.mrp_arch_articoli, \n";
        l_query  += "     pgmr.mis_reparto, \n"; 
        l_query  += "     pgmr.mrp_arch_stato \n";
        l_query  += " WHERE mrp_arch_articoli.cdrepa = mis_reparto.cdrepa \n";
        l_query  += "  AND mrp_arch_articoli.cdstato = mrp_arch_stato.cdstato \n";
        l_query  += "  AND mrp_arch_articoli.cdarti = ? \n";    
        
        if (!order_by.equals("")){
            l_query  += "   order by "+order_by+"  \n";
        }
        
        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString(ind, cdarti); ind += 1;

        rs = pstm.executeQuery();

        return rs;
    }



}

