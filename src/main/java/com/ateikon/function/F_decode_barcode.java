package com.ateikon.function;



import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.PreparedStatement;


import com.ateikon.common.Atk_sql;
import com.ateikon.common.Costanti_comm; 
import com.ateikon.common.Mrp_arch_articoli;
import com.ateikon.common.Matr_anagrafica;

import com.ateikon.structure.Str_dec_barcode;


public class F_decode_barcode extends Atk_sql {

    public F_decode_barcode() {

        super();

        // vado nella web.keypool
    }



    public Str_dec_barcode exec_cdartm( String as_value ) throws Exception {
        
        int               ind     = 0;
        int               tot_rec = 0;
        ResultSet         rs      = null;
        PreparedStatement pstm    = null;

        as_value = as_value.trim();
        as_value = as_value.toUpperCase();
        

        Str_dec_barcode lstr = new Str_dec_barcode();

        lstr.trovato     = false;



        l_query   = "";
        l_query  += " select cdarti                  \n";
        l_query  += "   from pgmr.mrp_arch_articoli  \n";
        l_query  += "  where cdartm = ?              \n";
        l_query  += "    and cdazie = ?              \n";
    
        pstm = m_connection.prepareStatement( l_query ) ;
    
        ind = 1;
        pstm.setString (ind, as_value); ind += 1;
        pstm.setString (ind, cdazie); ind += 1;
    
        rs = pstm.executeQuery();
    
        if (rs !=null && rs.next() && rs.getObject(1)!=null){
            
            lstr.cdarti =  rs.getString(1);
            lstr.trovato = true;
    
        }
        pstm.close();

        if (!lstr.trovato){
            
            lstr.cdarti      = "";
            lstr.tkmatricola = 0;

        }else {
            of_lotto(lstr);
        }
        
        return lstr;

    }




    public Str_dec_barcode exec( String as_value ) throws Exception {

        int               ind     = 0;
        int               tot_rec = 0;
        ResultSet         rs      = null;
        PreparedStatement pstm    = null;

        as_value = as_value.trim();
        as_value = as_value.toUpperCase();



        Str_dec_barcode lstr = new Str_dec_barcode();

        lstr.trovato     = false;

        Costanti_comm costanti_comm = new Costanti_comm();

        setProfilo((Atk_sql) costanti_comm);

        String  lwm_ricbarcode = costanti_comm.getCostvalue("lwm_ricbarcode");

        if (lwm_ricbarcode.equals("")) lwm_ricbarcode = "ABCDEF";

        int len = lwm_ricbarcode.length();

        // System.out.print("Ricerca Barcode >>"+as_value+"<< - ");

        for (int i=0; i<len; i++){

                // System.out.print(" / "+lwm_ricbarcode.charAt(i));

                if (lwm_ricbarcode.charAt(i) == 'A'){

                        l_query   = "";
                        l_query  += " select cdarti                  \n";
                        l_query  += "   from pgmr.mrp_arch_articoli  \n";
                        l_query  += "  where cdbarcode = ?           \n";
                
                        pstm = m_connection.prepareStatement( l_query ) ;
                
                        ind = 1;
                        pstm.setString (ind, as_value); ind += 1;
                
                        rs = pstm.executeQuery();
                
                        if (rs !=null && rs.next() && rs.getObject(1)!=null){
                            
                            lstr.cdarti =  rs.getString(1);
                            lstr.trovato = true;
                        }
                        pstm.close();
                
                    
                }else if (lwm_ricbarcode.charAt(i) == 'B'){
                    
                        l_query   = "";
                        l_query  += " select tkmatricola           \n";
                        l_query  += "      , cdarti                \n";
                        l_query  += "   from pgmr.matr_anagrafica  \n";
                        l_query  += "  where cdmatricola_m = ?     \n";
                
                        pstm = m_connection.prepareStatement( l_query ) ;
                
                        ind = 1;
                        pstm.setString (ind, as_value); ind += 1;
                
                        rs = pstm.executeQuery();
                
                        if (rs !=null && rs.next() && rs.getObject(1)!=null){
                            
                            lstr.tkmatricola =  rs.getLong(1);
                            lstr.cdarti      =  rs.getString(2);
                            lstr.trovato = true;



                
                        }
                        pstm.close();

                }else if (lwm_ricbarcode.charAt(i) == 'C'){

                        l_query   = "";
                        l_query  += " select tkmatricola           \n";
                        l_query  += "      , cdarti                \n";
                        l_query  += "   from pgmr.matr_anagrafica  \n";
                        l_query  += "  where cdmatrprod = ?        \n";
                
                        pstm = m_connection.prepareStatement( l_query ) ;
                
                        ind = 1;
                        pstm.setString (ind, as_value); ind += 1;
                
                        rs = pstm.executeQuery();
                
                        if (rs !=null && rs.next() && rs.getObject(1)!=null){
                            
                            lstr.tkmatricola =  rs.getLong(1);
                            lstr.cdarti      =  rs.getString(2);
                            lstr.trovato = true;
                
                        }
                        pstm.close();

                }else if (lwm_ricbarcode.charAt(i) == 'D'){
                    
                        l_query   = "";
                        l_query  += " select cdarti            \n";
                        l_query  += "   from pgmr.tgl_barcode  \n";
                        l_query  += "  where cdbarcode = ?     \n";
                
                        pstm = m_connection.prepareStatement( l_query ) ;
                
                        ind = 1;
                        pstm.setString (ind, as_value); ind += 1;
                
                        rs = pstm.executeQuery();
                
                        if (rs !=null && rs.next() && rs.getObject(1)!=null){
                            
                            lstr.cdarti =  rs.getString(1);
                            lstr.trovato = true;
                
                        }
                        pstm.close();

                }else if (lwm_ricbarcode.charAt(i) == 'E'){
                    
                        l_query   = "";
                        l_query  += " select cdarti                  \n";
                        l_query  += "   from pgmr.mrp_arch_articoli  \n";
                        l_query  += "  where cdartm = ?              \n";
                        l_query  += "    and cdazie = ?              \n";
                
                        pstm = m_connection.prepareStatement( l_query ) ;
                
                        ind = 1;
                        pstm.setString (ind, as_value); ind += 1;
                        pstm.setString (ind, cdazie); ind += 1;
                
                        rs = pstm.executeQuery();
                
                        if (rs !=null && rs.next() && rs.getObject(1)!=null){
                            
                            lstr.cdarti =  rs.getString(1);
                            lstr.trovato = true;
                
                        }
                        pstm.close();



                }else if (lwm_ricbarcode.charAt(i) == 'F'){

                        l_query   = "";
                        l_query  += " select cdarti                  \n";
                        l_query  += "   from pgmr.mrp_arch_articoli  \n";
                        l_query  += "  where cdartprod = ?           \n";
                        l_query  += "    and cdazie = ?              \n";
                
                        pstm = m_connection.prepareStatement( l_query ) ;
                
                        ind = 1;
                        pstm.setString (ind, as_value); ind += 1;
                        pstm.setString (ind, cdazie); ind += 1;
                
                        rs = pstm.executeQuery();
                
                        if (rs !=null && rs.next() && rs.getObject(1)!=null){
                            
                            lstr.cdarti =  rs.getString(1);
                            lstr.trovato = true;
                
                        }
                        pstm.close();

                }else {
                    throw new Exception("Opzione NON prevista!");
                }
                
                if (lstr.trovato){
                    break;
                }

        }

        
        // System.out.println();


        if (!lstr.trovato){
            
            lstr.cdarti      = "";
            lstr.tkmatricola = 0;

        }else {
            of_lotto(lstr);
        }
        
        return lstr;


    }





    /*****

        of_lotto: inposto i campi delle matricola
    **/


    public int of_articolo(Str_dec_barcode astr) throws Exception{
        
        int               ind     = 0;
        int               tot_rec = 0;
        ResultSet         rs      = null;
        PreparedStatement pstm    = null;

        Mrp_arch_articoli mrp_arch_articoli = new Mrp_arch_articoli();

        setProfilo((Atk_sql) mrp_arch_articoli );

        rs = mrp_arch_articoli.getKey(astr.cdarti);

        if (rs!=null && rs.next()){
            
            astr.setArticolo(rs);

            tot_rec = 1;

            
            // System.out.println("Cerco il pacco per l'articolo ");

            if (astr.fpacco_art.equals("S") && astr.fpacco.equals("S")){
                // --- cerco l'unico pacco e lo imposto

                l_query   = "";
                l_query  += " select tkmatricola                \n";
                l_query  += "   from pgmr.matr_anagrafica       \n";
                l_query  += "  where cdarti = '"+astr.cdarti+"' \n";
                
                astr.tkmatricola = sql_long(l_query);

                // System.out.println("Cerco il pacco per l'articolo astr.tkmatricola = "+astr.tkmatricola );

            }


        }else {
            
            rs = null;

            astr.setArticolo(rs);

            tot_rec = 0;
        }
        mrp_arch_articoli.close();


        return tot_rec;

    }





    /*****

        of_lotto: inposto i campi delle matricola
    **/


    public int of_lotto(Str_dec_barcode astr) throws Exception{
        
        int               ind     = 0;
        int               tot_rec = 0;
        ResultSet         rs      = null;
        PreparedStatement pstm    = null;


        Matr_anagrafica matr_anagrafica = new Matr_anagrafica();

        setProfilo((Atk_sql) matr_anagrafica);

        if (astr.tkmatricola > 0){
            rs = matr_anagrafica.getKey(astr.tkmatricola);
        }

        if (rs !=null && rs.next()){
        
            astr.setMatricola( rs );
            
            tot_rec = 1;

        }else {
            rs = null;

            astr.setMatricola( rs );

            astr.tkmatricola = 0;

            tot_rec = 0;
        }
        matr_anagrafica.close();




        return tot_rec;
    }


    /*****

        of_flag_calcolo: inposto i flag per il calcolo della qua 
    **/


    public int of_flag_calcolo(Str_dec_barcode astr) throws Exception{
        
        int               ind     = 0;
        int               tot_rec = 0;
        ResultSet         rs      = null;
        PreparedStatement pstm    = null;

        String cdunim = "";
        String cdtins = "";

        astr.fdimena     = "N";
        astr.fdimenb     = "N";
        astr.fdimenc     = "N";
        astr.fquantita   = "S";
        astr.fncolli     = "S";
        astr.fdivmis     = 1;


        cdunim = astr.getCdunim();
        cdtins = astr.getCdtins();


        if (!cdtins.equals("")){
            
            l_query  = "";
            l_query += " select dti.dstins                 \n";
            l_query += "      , dti.fdimena                \n";
            l_query += "      , dti.fdimenb                \n";
            l_query += "      , dti.fdimenc                \n";
            l_query += "      , dti.fquantita              \n";
            l_query += "      , dti.fncolli                \n";
            l_query += "      , dti.fdivmis                \n";
            l_query += "   from pgmr.doc_tipo_insriga  dti \n";
            l_query += " where dti.cdtins = ?              \n";
             

            pstm = m_connection.prepareStatement( l_query ) ;
    
            ind = 1;
            pstm.setString    (ind, cdtins); ind += 1;
    
            rs = pstm.executeQuery();
    


        }else {
            
            l_query  = "";
            l_query += " select dti.dstins              \n";
            l_query += "      , dti.fdimena             \n";
            l_query += "      , dti.fdimenb             \n";
            l_query += "      , dti.fdimenc             \n";
            l_query += "      , dti.fquantita           \n";
            l_query += "      , dti.fncolli             \n";
            l_query += "      , dti.fdivmis             \n";
            l_query += "   from pgmr.doc_tipo_insriga  dti \n";
            l_query += "      , pgmr.unimisura         uni \n";
            l_query += " where dti.cdtins = uni.cdtins     \n";
            l_query += " and uni.cdunim = ?                \n";


            pstm = m_connection.prepareStatement( l_query ) ;
    
            ind = 1;
            pstm.setString    (ind, cdunim); ind += 1;
    
            rs = pstm.executeQuery();


        }



        if (rs!=null && rs.next()){
            
            if (rs.getObject("fdimena"   )!=null ) astr.fdimena     = rs.getString("fdimena"   ); ind += 1;
            if (rs.getObject("fdimenb"   )!=null ) astr.fdimenb     = rs.getString("fdimenb"   ); ind += 1;
            if (rs.getObject("fdimenc"   )!=null ) astr.fdimenc     = rs.getString("fdimenc"   ); ind += 1;
            if (rs.getObject("fquantita" )!=null ) astr.fquantita   = rs.getString("fquantita" ); ind += 1;
            if (rs.getObject("fncolli"   )!=null ) astr.fncolli     = rs.getString("fncolli"   ); ind += 1;
            if (rs.getObject("fdivmis"   )!=null ) astr.fdivmis     = rs.getInt   ("fdivmis"   ); ind += 1;

        }


        pstm.close();




        return tot_rec;
    }




}
