package com.ateikon.function;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.PreparedStatement;

import com.ateikon.common.Atk_sql;
import com.ateikon.common.Costanti_comm;

import com.ateikon.structure.Str_sotto_categorie;


public class F_sotto_categorie extends Atk_sql {



    public F_sotto_categorie() {

        super();
    }




    /***


    */

    public int count (String cdgptipm ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        Costanti_comm costanti_comm = new Costanti_comm();

        setProfilo((Atk_sql) costanti_comm);

        String ep_tipo_sttcat = costanti_comm.getCostvalue("ep.tipo_sttcat");

        int li_ = 0;


        if (ep_tipo_sttcat.equals("kg_tipomapr")){
            
            l_query   = "";
    		l_query  += " select count(*)               \n";
    		l_query  += "   from pgmr.kg_tipomapr       \n";
    		l_query  += " where cdazie = '"+cdazie+"'   \n";
    		l_query  += "   and fgfiltroweb = 'S'       \n";
            if (!cdgptipm.equals("")){
    		l_query  += " and cdgptipm = '"+cdgptipm+"' \n";
            }
        }else {
            
            l_query   = "";
    		l_query  += " select count(*)               \n";
    		l_query  += "   from pgmr.kg_tipomapr_cat   \n";
    		l_query  += " where cdazie = '"+cdazie+"'   \n";
    		l_query  += "   and fgabil = 'S' \n";
            if (!cdgptipm.equals("")){
    		l_query  += " and cdgptipm = '"+cdgptipm+"' \n";
            }
        }

		li_ = sql_int( l_query ) ;


        return li_;

    }




    /***


    */

    public Str_sotto_categorie get ( String cdgptipm 
                                   , String order_by
                                   , String cdling
                                                        ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        Str_sotto_categorie  lstr = new Str_sotto_categorie();

        Costanti_comm costanti_comm = new Costanti_comm();

        setProfilo((Atk_sql) costanti_comm);

        String ep_tipo_sttcat = costanti_comm.getCostvalue("ep.tipo_sttcat");


        // --- Count

        if (ep_tipo_sttcat.equals("kg_tipomapr")){

            l_query   = "";
    		l_query  += " select count(*)               \n";
    		l_query  += "   from pgmr.kg_tipomapr       \n";
    		l_query  += " where cdazie = '"+cdazie+"'   \n";
    		l_query  += "   and fgfiltroweb = 'S'       \n";
            if (!cdgptipm.equals("")){
    		l_query  += " and cdgptipm = '"+cdgptipm+"' \n";
            }
        

        }else {
            
            // defalut 

            l_query   = "";
    		l_query  += " select count(*)               \n";
    		l_query  += "   from pgmr.kg_tipomapr_cat   \n";
    		l_query  += " where cdazie = '"+cdazie+"'   \n";
    		l_query  += "   and fgabil = 'S' \n";
            if (!cdgptipm.equals("")){
    		l_query  += " and cdgptipm = '"+cdgptipm+"' \n";
            }
        }


		int tot_sttcat = sql_int( l_query ) ;


        // Imposto il totale delle Colonne / Righe

        if (lstr.colonne <= 0)  lstr.colonne = 2;

        lstr.righe = tot_sttcat / lstr.colonne;
        
        int mod_sttcat = tot_sttcat % lstr.colonne;
        
        if (mod_sttcat > 0){
            lstr.righe += 1;
        }
        
        if (lstr.righe<5) lstr.righe = 4;
        

        lstr.arr_cod   = new String[lstr.colonne][lstr.righe];
        lstr.arr_cod_m = new String[lstr.colonne][lstr.righe];
        lstr.arr_descr = new String[lstr.colonne][lstr.righe];


        // --- Popolo l'array

        String ls_suffisso = of_lingua("kg_tipomapr_cat", cdling);

        String ls_cod   = "";
        String ls_cod_m = "";
        String ls_descr = "";


        if (ep_tipo_sttcat.equals("kg_tipomapr")){
            

            l_query   = "";
    		l_query  += " select cdtipm      as cod                 \n";
    		l_query  += "      , cdtipm_m    as cod_m               \n";
    		l_query  += "      , dstipm" + ls_suffisso + " as descr \n";
    		l_query  += "   from pgmr.kg_tipomapr                   \n";
    		l_query  += " where cdazie = '"+cdazie+"'               \n";
    		l_query  += "   and fgfiltroweb = 'S'                   \n";
            if (!cdgptipm.equals("")){                              
    		l_query  += " and cdgptipm = '"+cdgptipm+"'             \n";
            }                                                       
    		l_query  += "  order by "+order_by+"                    \n";


        }else {
            // --- defalut

            l_query   = "";
    		l_query  += " select cdtipmc    as cod                        \n";
    		l_query  += "      , cdtipmc_m  as cod_m                      \n";
    		l_query  += "      , dstipmc" + ls_suffisso + "  as descr     \n";
    		l_query  += "   from pgmr.kg_tipomapr_cat                     \n";
    		l_query  += " where cdazie = '"+cdazie+"'                     \n";
    		l_query  += "   and fgabil = 'S'                              \n";
            if (!cdgptipm.equals("")){                                    
    		l_query  += " and cdgptipm = '"+cdgptipm+"'                   \n";
            }                                                             
    		l_query  += "  order by "+order_by+"                          \n";
        }
        
        

		pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        rs = pstm.executeQuery();

        for (int j=0; j<lstr.colonne; j++){
    
            for (int i=0; i<lstr.righe; i++){
        
                ls_cod   = "";
                ls_cod_m = "";
                ls_descr = "";
        
                if (rs!=null && rs.next()){
                    if (rs.getObject(1)!=null) ls_cod   = rs.getString(1);
                    if (rs.getObject(2)!=null) ls_cod_m = rs.getString(2);
                    if (rs.getObject(3)!=null) ls_descr = rs.getString(3);
                }
                lstr.arr_cod  [j][i] = ls_cod ;
                lstr.arr_cod_m[j][i] = ls_cod_m;
                lstr.arr_descr[j][i] = ls_descr;
            }

        }
        pstm.close();


        return lstr;

    }



}
