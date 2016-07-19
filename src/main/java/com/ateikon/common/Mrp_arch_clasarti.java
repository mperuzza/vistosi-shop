/*
    Creato da: Vendramin Andrea
 *  in data: 10/01/2008
 *  label: avendramin20080110
*/
package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;





public class Mrp_arch_clasarti extends Atk_sql {

    
    public Mrp_arch_clasarti() {

        super();
    }


    public ResultSet getDropdown() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select cdclas_a, dsclas_a        \n";
        l_query  += "   from pgmr.mrp_arch_clasarti    \n";
        l_query  += "  order by dsclas_a               \n";

        pstm = setQuery( l_query ) ;

        ind = 1;

        rs = pstm.executeQuery();

        return rs;


    }






    public ResultSet  search ( String   cdclas_a_m  
                             , boolean  is_count
                             , String   order_by
                                                  ) throws Exception {
        
        int               ind = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;
        

        cdclas_a_m  = par_like(cdclas_a_m );

        
        if (order_by.equals("")) order_by = "cdclas_a_m"; 


        if (is_count) {
            l_query   = "";
            l_query  += " select count(cdclas_a) \n";

            order_by = "";
            
        }else {
            
            l_query   = "";
            l_query  += " select cdclas_a    \n";
            l_query  += "      , cdclas_a_m  \n";
            l_query  += "      , dsclas_a    \n";
        }

        
        l_query  += "   from pgmr.mrp_arch_clasarti   \n";
        l_query  += "   where cdclas_a in (select distinct(art.cdclas_a) from pgmr.mrp_arch_articoli art where art.cdazie = '"+cdazie+"')  \n";
        


        if (!cdclas_a_m.equals("")){
            l_query  += " and upper(cdclas_a_m ) like '"+cdclas_a_m+"'  \n";
            
        }
        
        if (!order_by.equals("")) l_query  += " order by "+order_by+ "\n"; 
        


        pstm = setQuery_ric( l_query );

        ind = 1;
        // pstm.setString(ind, cdazie); ind += 1;
        
        rs = pstm.executeQuery();
        
        return rs;

    }







}
