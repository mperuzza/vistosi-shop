package com.ateikon.user_object;




import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.PreparedStatement;


import com.ateikon.common.Atk_sql;
import com.ateikon.common.Costanti_comm; 
import com.ateikon.common.Mrp_arch_articoli;
import com.ateikon.common.Matr_anagrafica;

import com.ateikon.structure.Str_dec_barcode;


public class Brule_tipoins_riga extends Atk_sql {

    public Brule_tipoins_riga() {

        super();

        // vado nella web.keypool
    }



    public int of_calcola_quantita( Str_dec_barcode astr ) throws Exception {

        int               ind     = 0;
        int               tot_rec = 0;
        ResultSet         rs      = null;
        PreparedStatement pstm    = null;


        /*
        System.out.println("=========================");
        System.out.println("=== of_calcola_quantita");
        System.out.println("=========================");


        System.out.println("astr.fdivmis   = "+astr.fdivmis);
        System.out.println("astr.fdimena   = "+astr.fdimena);
        System.out.println("astr.fdimenb   = "+astr.fdimenb);
        System.out.println("astr.fdimenc   = "+astr.fdimenc);
        System.out.println();
        System.out.println("astr.fncolli   = "+astr.fncolli);
        System.out.println("astr.fquantita = "+astr.fquantita);
        System.out.println();
        System.out.println("astr.dimena    = "+astr.dimena);
        System.out.println("astr.dimenb    = "+astr.dimenb);
        System.out.println("astr.dimenc    = "+astr.dimenc);
        System.out.println("astr.quantita  = "+astr.quantita);
        System.out.println("astr.ncolli    = "+astr.ncolli);
        System.out.println("---");
        */
        

        long    ll_divisore = 1;

        // calcolo la qunatita per tipo inserimento

        for (int i=0; i<astr.fdivmis; i++){
            
            ll_divisore = ll_divisore * 10;
        }

        if (ll_divisore > 0 ){
        }else{
            ll_divisore = 1;
        }

        astr.quantita = 1;

        // solo quantita
        if (astr.fdimena   == "N" && astr.fdimenb == "N" 
        &&  astr.fdimenc   == "N" && astr.fncolli == "N" 
        &&  astr.fquantita == "S" 
        ){
            astr.ncolli = 1;
            // astr.quantita = astr.quantita;
        }

        // System.out.println("ll_divisore  = "+ll_divisore);

        if (astr.fdimena.equals("S")){ 
            astr.quantita = astr.quantita * (astr.dimena / ll_divisore);
        }
        // System.out.println("A astr.quantita  = "+astr.quantita);

        
        if (astr.fdimenb.equals("S")){ 
            astr.quantita = astr.quantita * (astr.dimenb / ll_divisore);
        }
        // System.out.println("B astr.quantita  = "+astr.quantita);



        if (astr.fdimenc.equals("S")){ 
            astr.quantita = astr.quantita * (astr.dimenc / ll_divisore);
        }

        boolean lb_misvar = false;

        if (astr.fmisvar.equals("A")
        ||  astr.fmisvar.equals("B")
        ||  astr.fmisvar.equals("C")
            ){
            lb_misvar = true;
        }


        if (!lb_misvar && astr.fncolli.equals("S")){ 
            astr.quantita = astr.quantita * astr.ncolli;
        }


        astr.quantita = (double)java.lang.Math.round(astr.quantita * 1000)/1000;


        // astr.quantita = ROUND(astr.quantita, 3)
        // System.out.println("===================================");
        
        return 1;

    }






}
