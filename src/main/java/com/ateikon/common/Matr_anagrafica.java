package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Matr_anagrafica extends com.ateikon.standard.Matr_anagrafica {

    public Matr_anagrafica() {

        super();
    }




    /***

        questo Metodo verifica se un articolo 
        è impegnato in unordine

    */


    public ResultSet getImpegnata( long tkmatricola
                                 , long tkordi
                                                    ) throws Exception {
                                                        
                                            
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " SELECT test.cdordi                 \n";
        l_query  += "      , test.numord                 \n";
        l_query  += "      , test.dtordi                 \n";
        l_query  += "   FROM pgmr.ord_test      test     \n";
        l_query  += "      , pgmr.ord_positito  posi     \n";
        l_query  += "      , pgmr.ord_posi_matr matr     \n";
        l_query  += "  WHERE posi.tkordi  = test.tkordi  \n";
        l_query  += "    and posi.tkposi  = matr.tkposi  \n";
        l_query  += "    AND posi.fgsaldo = 'N'          \n";
        l_query  += "    AND matr.tkmatricola = ?        \n";
        l_query  += "    AND test.tkordi     <> ?        \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setLong (ind, tkmatricola ); ind += 1;
        pstm.setLong (ind, tkordi      ); ind += 1;


        rs = pstm.executeQuery();

        return rs;

    }



    public ResultSet getImpegnata( long tkmatricola
                                                    ) throws Exception {
                                                        
                                            
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " SELECT test.cdordi                 \n";
        l_query  += "      , test.numord                 \n";
        l_query  += "      , test.dtordi                 \n";
        l_query  += "   FROM pgmr.ord_test      test     \n";
        l_query  += "      , pgmr.ord_positito  posi     \n";
        l_query  += "      , pgmr.ord_posi_matr matr     \n";
        l_query  += "  WHERE posi.tkordi  = test.tkordi  \n";
        l_query  += "    and posi.tkposi  = matr.tkposi  \n";
        l_query  += "    AND posi.fgsaldo = 'N'          \n";
        l_query  += "    AND matr.tkmatricola = ?        \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setLong (ind, tkmatricola ); ind += 1;


        rs = pstm.executeQuery();

        return rs;

    }


    




    public ResultSet getKey_m( String cdmatricola_m

                                           ) throws Exception {
                                            
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        return getKey_m( cdmatricola_m, "");

   }




    /****
        getKey: matr_anagrafica
    **/ 

    public ResultSet getKey_m( String cdmatricola_m
                             , String cdarti

                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.matr_anagrafica  \n";
        l_query  += "  where cdmatricola_m = ? \n";
        if (!cdarti.equals("")){
        l_query  += "    and cdarti = ? \n";
        }

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString (ind, cdmatricola_m ); ind += 1;
        if (!cdarti.equals("")){
            pstm.setString (ind, cdarti ); ind += 1;
        }


        rs = pstm.executeQuery();

        return rs;

    }



    /****
        Properties KEY
    **/ 









}

