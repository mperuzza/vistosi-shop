package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


import com.ateikon.structure.Rec_cat_layout;

public class Cat_layout extends com.ateikon.standard.Cat_layout {


    public Cat_layout() {

        super();
    }

    /***


    */

    public long getDef( ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        l_query   = "";
		l_query  += " select min(id)          \n";
		l_query  += "   from pgmr.cat_layout  \n";

        long ll_ = sql_long(l_query);

        return ll_;
    }




    /***


    */

    public ResultSet getAll ( ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        l_query   = "";
		l_query  += " select *                \n";
		l_query  += "   from pgmr.cat_layout  \n";
		l_query  += " order by nrposi         \n";
		l_query  += "        , id             \n";

        pstm = setQuery( l_query );

        rs = pstm.executeQuery();

        return rs;
    }




    /***


    */

    public ResultSet getLayout ( String cdlayout_m ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        l_query   = "";
		l_query  += " select lay_art.*                             \n";
		l_query  += "   from pgmr.cat_layout          lay          \n";
		l_query  += "      , pgmr.cat_layout_arti     lay_art      \n";
		l_query  += "  where lay.cdlayout_m     = '"+cdlayout_m+"' \n";
		l_query  += "    and lay_art.id_layout  = lay.id           \n";
		l_query  += " order by lay_art.nrposi                      \n";

        pstm = setQuery( l_query );

        rs = pstm.executeQuery();

        return rs;
    }



    /***


    */

    public ResultSet getCdlayout_m( String cdlayout_m ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        l_query   = "";
		l_query  += " select lay.*                              \n";
		l_query  += "   from pgmr.cat_layout lay                \n";
		l_query  += "  where lay.cdlayout_m  = '"+cdlayout_m+"' \n";

        pstm = setQuery( l_query );

        rs = pstm.executeQuery();

        return rs;
    }



    /***


    */

    public int ctrl( ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        // controllo che ci siano Tutte le definizioni

        long id = 0;

        Rec_cat_layout lstr = null;

        String[] arr_cdlayout_m = new String[]{ "PROMO"
                                            , "TOP_TEN"
                                            , "RIGHT_MENU"
                                            , "SCHEDA_ART"
                                                };

        String[] arr_descr = new String[]{ "In Evidenza"
                                       , "Top Ten"
                                       , "Menu Sx"
                                       , "Scheda Articolo"
                                           };

        for (int i=0; arr_cdlayout_m!=null && i<arr_cdlayout_m.length; i++){
            
            l_query   = "";
    		l_query  += " select id                                   \n";
    		l_query  += "   from pgmr.cat_layout                      \n";
    		l_query  += "  where cdlayout_m = '"+arr_cdlayout_m[i]+"' \n";
    
            id = sql_long(l_query);
    
            if (id <= 0){
            
                lstr = new Rec_cat_layout();
    
                lstr.cdlayout_m = arr_cdlayout_m[i];
                lstr.descr      = arr_descr[i];
                lstr.nrposi     = (long)((i+1)*10);
    
                tot_rec += execute(lstr);
    
                if (tot_rec == 1){
                    m_connection.commit();
                }
            }
        }

        return tot_rec;
    }

}

