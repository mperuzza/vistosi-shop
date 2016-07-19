package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


import com.ateikon.function.F_iva_assofisc;

import com.ateikon.structure.Rec_carrello_iva;



public class Carrello_iva extends com.ateikon.standard.Carrello_iva {


    public Carrello_iva() {

        super();
    }






    /***


    */

    public int reset (long tkcarrello ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec = 0;

        l_query  = "";
        l_query += " update web.carrello_iva              \n";
        l_query += "    set imponibile   = null           \n";
        l_query += "      , iva          = null           \n";
        l_query += "  where tkcarrello   = "+tkcarrello+" \n";

        tot_rec = sql_update(l_query);

        return tot_rec;

    }





    /***


    */

    public int add (Rec_carrello_iva astr) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;
        
        F_iva_assofisc f_iva_assofisc = new F_iva_assofisc();

        setProfilo((Atk_sql) f_iva_assofisc);


        // trovo l'aliquota 

        if (!astr.cdiva.equals("")){

            l_query  = "";
            l_query += " select aliquo                   \n";
            l_query += "   from pgmr.codiciva            \n";
            l_query += "  where cdiva = '"+astr.cdiva+"' \n";

            astr.aliquo = sql_double(l_query);
        }


        // Trovo l'aliquota in base al carrello

        if (astr.cdiva.equals("")){

            // prendo l'aliquota  massima o minima in base 
            // al segno dell'imponibile (sconto | spesa accessoria)

            if (astr.imponibile > 0){

                tot_rec = 0;

                l_query  = "";
                l_query += " select cdiva                                               \n";
                l_query += "      , aliquo                                              \n";
                l_query += "   from web.carrello_iva                                    \n";
                l_query += "  where tkcarrello = "+astr.tkcarrello+"                    \n";
                l_query += "    and imponibile is not null                              \n";
                l_query += "    and aliquo = ( select max(aliquo)                       \n";
                l_query += "                     from web.carrello_iva                  \n";
                l_query += "                    where tkcarrello = "+astr.tkcarrello+"  \n";
                l_query += "                      and imponibile > 0                    \n";
                l_query += "                    )                                       \n";
                l_query += "  order by cdiva                                            \n";

            }else {
                
                l_query  = "";
                l_query += " select cdiva                                               \n";
                l_query += "      , aliquo                                              \n";
                l_query += "   from web.carrello_iva                                    \n";
                l_query += "  where tkcarrello = "+astr.tkcarrello+"                    \n";
                l_query += "    and imponibile is not null                              \n";
                l_query += "    and aliquo = ( select min(aliquo)                       \n";
                l_query += "                     from web.carrello_iva                  \n";
                l_query += "                    where tkcarrello = "+astr.tkcarrello+"  \n";
                l_query += "                      and imponibile > 0                    \n";
                l_query += "                    )                                       \n";
                l_query += "  order by cdiva                                            \n";
            }


            pstm = m_connection.prepareStatement(l_query);

            ind = 1;

            rs = pstm.executeQuery();

            if (rs!=null && rs.next()){
                
                tot_rec += 1;
                if (rs.getObject(1)!=null)  astr.cdiva = rs.getString(1);
                if (rs.getObject(2)!=null)  astr.aliquo = rs.getDouble(2);

            }
            pstm.close();

        }


        // Trovo l'aliquota di default

        if (astr.cdiva.equals("")){
            astr.cdiva = "ESENTE";
            astr.aliquo = 0;
        }


        long    id  = 0;
        double  imponibile_prec = 0;

        tot_rec = 0;


        l_query  = "";
        l_query += " select id                                \n";
        l_query += "      , imponibile                        \n";
        l_query += "   from web.carrello_iva                  \n";
        l_query += "  where tkcarrello = "+astr.tkcarrello+"  \n";
        l_query += "    and cdiva = '"+astr.cdiva+"'          \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){
            
            tot_rec += 1;
            if (rs.getObject(1)!=null)  id              = rs.getLong(1);
            if (rs.getObject(2)!=null)  imponibile_prec = rs.getDouble(2);

        }
        pstm.close();

        astr.id = id;
        astr.imponibile = imponibile_prec + astr.imponibile;

        double ld_iva =  (astr.imponibile) * astr.aliquo / 100;
        ld_iva  = f_iva_assofisc.round( ld_iva );

        astr.iva = ld_iva;

        double imponibile_delta = 0;

        if (astr.imponibile < 0){
            
            imponibile_delta = astr.imponibile;
            
            astr.imponibile = 0;
            astr.iva  = 0;

        }

        tot_rec = execute(astr);

        if (tot_rec != 1){
            throw new Exception ("Errore AGG web.carrello_iva ");
        }

        if (imponibile_delta < 0){
            
            tot_rec = 0;

            l_query  = "";
            l_query += " select count (id)                       \n";
            l_query += "   from web.carrello_iva                 \n";
            l_query += "  where imponibile > 0                   \n";
            l_query += "    and tkcarrello = "+astr.tkcarrello+" \n";

            int li_ = sql_int(l_query);

            if (li_ > 0){
                Rec_carrello_iva lstr = new Rec_carrello_iva();
    
                lstr.tkcarrello = astr.tkcarrello;
                lstr.imponibile = imponibile_delta;

                add(lstr);
            }

        }



        return 1;

    }




    /***


    */

    public int clean(long tkcarrello ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec = 0;

        l_query  = "";
        l_query += " delete from web.carrello_iva         \n";
        l_query += "  where tkcarrello   = "+tkcarrello+" \n";
        l_query += "    and imponibile   is null           \n";

        tot_rec = sql_update(l_query);

        return tot_rec;

    }




    /***


    */

    public double getTot_imponibile(long tkcarrello ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec = 0;
        
        l_query  = "";
        l_query += " select sum(imponibile)                 \n";
        l_query += "   from web.carrello_iva                \n";
        l_query += "  where tkcarrello   = "+tkcarrello+"   \n";

        double ldb_ = sql_double(l_query);

        return ldb_;

    }



    public double getTot_iva(long tkcarrello ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec = 0;
        
        l_query  = "";
        l_query += " select sum(iva)                        \n";
        l_query += "   from web.carrello_iva                \n";
        l_query += "  where tkcarrello   = "+tkcarrello+"   \n";

        double ldb_ = sql_double(l_query);

        return ldb_;

    }








}

