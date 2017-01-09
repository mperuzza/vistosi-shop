    /***
 
 
        NOTA: getTabkey
              nel programma originale in Power Builder il programma
              testa anche se il campo è un tipo valido;

              Sarebbe possibile farlo anche qui: occorre passare
              la query ...
 

    */



package com.ateikon.function;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import com.ateikon.common.Atk_sql;
import com.ateikon.common.Costanti_comm;
import com.ateikon.common.Archclie;
import com.ateikon.common.Lis_lisarticolo;
import com.ateikon.common.Scontoimb;
import com.ateikon.common.Vist_scocl_arti;


import com.ateikon.structure.Str_listino;
import com.ateikon.structure.Str_imptrasp;



public class F_listino extends Atk_sql {



    public F_listino() {

        super();
    }

    public Str_listino calcolaPrezzo( String as_cdlist
                                    , String tkclie
                                    , String cdarti
                                    , double qtatot
                                    , double scont1
                                    , double scont2
                                    , double scont3
                                    , double impuninetto
                                    , String fg_annulla_sconti
                                    , String fg_tpcalcimp
                                                        ) throws Exception{
                                                            
      
        Str_listino lstr = calcolaPrezzo(as_cdlist, tkclie,  cdarti, qtatot);

        if (fg_tpcalcimp.equals("S")){
        
            double[] arr_sc_ = null;

            if (lstr.scmax_1 > 0){
                arr_sc_ = of_ricalcola_sc(scont1, lstr.scmax_1, scont2);

                scont1 = arr_sc_[0];
                scont2 = arr_sc_[1];
            }
            if (lstr.scmax_2 > 0){
                arr_sc_ = of_ricalcola_sc(scont2, lstr.scmax_2, scont3);

                scont2 = arr_sc_[0];
                scont3 = arr_sc_[1];
            }

            // --- poichè lo sconto 3 genera provvigioni se non ho raggiunto il 
            //     max nello sconto 2 allora compenso
            
            if (scont3 > 0 && lstr.scmax_2 > scont2 && lstr.scmax_2 > 0){
                
                arr_sc_ = of_ricalcola_sc_inv(scont2, lstr.scmax_2, scont3);
                
                scont2 = arr_sc_[0];
                scont3 = arr_sc_[1];

            }

            lstr.sconto1 = scont1;
            lstr.sconto2 = scont2;
            lstr.sconto3 = scont3;

            // calcolo il prezzo netto in base ai nuovi sconti
            of_netto(lstr);

            return lstr;

        }else if (fg_tpcalcimp.equals("I")){
            
        }else {
            // restituisco il calcolo del listino
            return lstr;
        }



        //  è stato indicato un importo

        lstr.sconto1 = scont1;

        if (lstr.scmax_1 > 0){
            if (lstr.sconto1 > lstr.scmax_1) lstr.sconto1 = lstr.scmax_1;
        }
        if (lstr.scmax_2 > 0){
            lstr.sconto2 = lstr.scmax_2;
        }

        scont1 = 0;
        scont2 = 0;
        scont3 = 0;


        double ldb_IN0 = 0;
        double ldb_IN1 = 0;
        double ldb_IN2 = 0;
        double ldb_IN3 = 0;
        double ldb_IN4 = 0;

        lstr.impuninetto = lstr.impuni;
        
        ldb_IN0 = lstr.impuninetto;
        if (lstr.sconto1 > 0 ){
            lstr.impuninetto = lstr.impuninetto - (lstr.impuninetto * lstr.sconto1 / 100);
        }
        ldb_IN1 = lstr.impuninetto;
        if (lstr.sconto2 > 0 ){
            lstr.impuninetto = lstr.impuninetto - (lstr.impuninetto * lstr.sconto2 / 100);
        }
        ldb_IN2 = lstr.impuninetto;
        if (lstr.sconto3 > 0 ){
            lstr.impuninetto = lstr.impuninetto - (lstr.impuninetto * lstr.sconto3 / 100);
        }
        ldb_IN3 = lstr.impuninetto;
        if (lstr.sconto4 > 0 ){
            lstr.impuninetto = lstr.impuninetto - (lstr.impuninetto * lstr.sconto4 / 100);
        }
        ldb_IN4 = lstr.impuninetto;


        if (impuninetto > 0){
            if (impuninetto > lstr.impuninetto){
            
                if (impuninetto > ldb_IN0 ){
                    // prezzo maggiorato rispetto al listino
                    lstr.impuninetto = impuninetto;
                    lstr.sconto1 = 0;
                    lstr.sconto2 = 0;
                    lstr.sconto3 = 0;
                    lstr.sconto4 = 0;
                }else if (impuninetto > ldb_IN1 ){

                    // maggiorato rispetto allo sconto 1 (lo ricalcolo)
                    lstr.sconto1 = (ldb_IN0 - impuninetto) * 100 / ldb_IN0;
                    lstr.impuninetto = impuninetto;
                    lstr.sconto2 = 0;
                    lstr.sconto3 = 0;
                    lstr.sconto4 = 0;

                }else if (impuninetto > ldb_IN2 ){

                    // maggiorato rispetto allo sconto 2 (lo ricalcolo)
                    lstr.sconto2 = (ldb_IN1 - impuninetto) * 100 / ldb_IN1;
                    lstr.impuninetto = impuninetto;
                    lstr.sconto3 = 0;
                    lstr.sconto4 = 0;

                }else if (impuninetto > ldb_IN3 ){

                    // maggiorato rispetto allo sconto 3 (lo ricalcolo)
                    lstr.sconto3 = (ldb_IN2 - impuninetto) * 100 / ldb_IN2;
                    lstr.impuninetto = impuninetto;
                    lstr.sconto4 = 0;

                }else if (impuninetto > ldb_IN4 ){

                    // maggiorato rispetto allo sconto 4 (lo ricalcolo)
                    lstr.sconto4 = (ldb_IN3 - impuninetto) * 100 / ldb_IN3;
                    lstr.impuninetto = impuninetto;
                }


            }else if (impuninetto < lstr.impuninetto){
                
                // --- prezzo minorato rispetto al prezzo finale

                if (lstr.sconto4 > 0){
                    
                    // è minorato rispetto allo sconto 4 (lo ricalcolo come sc1+sc2+sc3+sc4);
                    lstr.sconto4 = (ldb_IN3 - impuninetto) * 100 / ldb_IN3;
                    lstr.impuninetto = impuninetto;

                }else if (lstr.sconto3 > 0){
                    
                    // è minorato rispetto allo sconto 3 (lo ricalcolo sc1+sc2+sc3)
                    lstr.sconto3 = (ldb_IN2 - impuninetto) * 100 / ldb_IN2;
                    lstr.impuninetto = impuninetto;
                    lstr.sconto4 = 0;
                    
                }else if (lstr.sconto2 > 0){
                    
                    // è minorato rispetto allo sconto 2 (lo ricalcolo come sc1+sc2+sc3)
                    lstr.sconto3 = (ldb_IN2 - impuninetto) * 100 / ldb_IN2;
                    lstr.impuninetto = impuninetto;
                    lstr.sconto4 = 0;

                }else if (lstr.sconto1 > 0){
                    // è minorato rispetto allo sconto 1 (lo ricalcolo come sc1+sc2)
                    lstr.sconto2 = (ldb_IN1 - impuninetto) * 100 / ldb_IN1;
                    lstr.impuninetto = impuninetto;
                    lstr.sconto3 = 0;
                    lstr.sconto4 = 0;
                }else {
                    // è minorato rispetto al prz. di listino (lo ricalcolo come sc1)
                    lstr.sconto1 = (ldb_IN0 - impuninetto) * 100 / ldb_IN0;
                    lstr.impuninetto = impuninetto;
                    lstr.sconto2 = 0;
                    lstr.sconto3 = 0;
                    lstr.sconto4 = 0;
                }
                
            }
        }


        if (lstr.scrap1 > 0 ){
            lstr.impuninetto = lstr.impuninetto - (lstr.impuninetto * lstr.scrap1 / 100);
        }
        if (lstr.scrap2 > 0 ){
            lstr.impuninetto = lstr.impuninetto - (lstr.impuninetto * lstr.scrap2 / 100);
        }

        com.ateikon.util.Atk_ctrl atk_ctrl = new com.ateikon.util.Atk_ctrl();
        
        lstr.descr_sconti = atk_ctrl.descrSconti(new double[]{ lstr.sconto1
                                                             , lstr.sconto2
                                                             , lstr.sconto3
                                                             , lstr.sconto4
                                                             , lstr.scrap1
                                                             , lstr.scrap2
                                                                           });

        return lstr;

    }


    public Str_listino calcolaPrezzo( String as_cdlist
                                    , String tkclie
                                    , String cdarti
                                    , double qtatot
                                                        ) throws Exception{
        
        int               ind     = 0;
        int               tot_rec = 0;
        PreparedStatement pstm    = null;
        ResultSet         rs      = null;

        numFormat = java.text.NumberFormat.getInstance(java.util.Locale.ITALY);
        numFormat.setGroupingUsed( true );
        numFormat.setMaximumFractionDigits( 2 );
        numFormat.setMinimumFractionDigits( 0 );
    
        przFormat = java.text.NumberFormat.getInstance(java.util.Locale.ITALY);
        przFormat.setGroupingUsed( true );
        przFormat.setMaximumFractionDigits( 2 );
        przFormat.setMinimumFractionDigits( 2 );

        Str_listino lstr = new Str_listino();

        lstr.valido = false;
        lstr.tkclie = tkclie;
        lstr.cdarti = cdarti;

        String cdtipm = "";
        String cdvistccol = "";

        // --- Listino

        String cdlist_clie = "";

        l_query  = "";
        l_query += "  select cdlist             \n";
        l_query += "       , cdcllist           \n";
        l_query += "       , scocas             \n";
        l_query += "       , scrap1             \n";
        l_query += "       , scrap2             \n";
        l_query += "    from pgmr.archclie clie \n";
        l_query += "   where clie.cdazie = ?    \n";
        l_query += "     and clie.tkclie = ?    \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;
        pstm.setString(ind++, cdazie);
        pstm.setString(ind++, tkclie);

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){
            if (rs.getObject("cdlist")!=null)    cdlist_clie    = rs.getString("cdlist");
            if (rs.getObject("cdcllist")!=null)  lstr.cdcllist  = rs.getString("cdcllist");
            if (rs.getObject("scocas")!=null)    lstr.scocas    = rs.getDouble("scocas");
            if (rs.getObject("scrap1")!=null)    lstr.scrap1  = rs.getDouble("scrap1");
            if (rs.getObject("scrap2")!=null)    lstr.scrap2  = rs.getDouble("scrap2");
        }
        pstm.close();

        if (as_cdlist.equals("")){
            lstr.cdlist = cdlist_clie;
        }else {
            lstr.cdlist = as_cdlist;
        }                                                
                                                         
                                                         
        // --- dati dall'articolo

        l_query  = "";
        l_query += "  select arti.cdclsc_v                                                   \n";
        l_query += "       , arti.cdvistccol                                                 \n";
        l_query += "       , tipm.scmax_1                                                    \n";
        l_query += "       , tipm.scmax_2                                                    \n";
        l_query += "       , tipm.cdtipm                                                     \n";
        l_query += "    from pgmr.mrp_arch_articoli arti                                     \n";
        l_query += "         left outer join pgmr.tipomapr tipm on arti.cdtipa = tipm.cdtipm \n";
        l_query += "   where arti.cdazie = ?                                                 \n";
        l_query += "     and arti.cdarti = ?                                                 \n";
                                                         
        pstm = m_connection.prepareStatement(l_query);

        ind = 1;
        pstm.setString(ind++, cdazie);
        pstm.setString(ind++, cdarti);

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){
            if (rs.getObject("cdclsc_v")!=null)    lstr.cdclsc_v  = rs.getString("cdclsc_v");
            if (rs.getObject("cdtipm")!=null)      cdtipm         = rs.getString("cdtipm");
            if (rs.getObject("cdvistccol")!=null)  cdvistccol     = rs.getString("cdvistccol");
            if (rs.getObject("scmax_1" )!=null)    lstr.scmax_1   = rs.getDouble("scmax_1");
            if (rs.getObject("scmax_2" )!=null)    lstr.scmax_2   = rs.getDouble("scmax_2");
        }
        pstm.close();

        
        
        String ls_cdlist_sost = "";

        l_query  = "";
        l_query += " select cdlist_sost        \n";
        l_query += "      , vacodi             \n";
        l_query += "   from pgmr.lis_listino   \n";
        l_query += "  where cdlist = ?         \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;
        pstm.setString(ind++, lstr.cdlist);

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){
            
            if (rs.getObject(1)!=null)  ls_cdlist_sost = rs.getString(1);
            if (rs.getObject(2)!=null)  lstr.vacodi    = rs.getString(2);

        }
        pstm.close();




        if (!lstr.valido){
            
                
                // --- verifico se trovo un prezzo particolare per cliente
        
        
        
                l_query  = "";
                l_query += "  SELECT prezzo                           \n";
                l_query += "       , psc1                             \n";
                l_query += "       , psc2                             \n";
                l_query += "       , psc3                             \n";
                l_query += "       , psc4                             \n";
                l_query += "       , psc5                             \n";
                l_query += "       , psctrasp                         \n";
                l_query += "       , psccassa                         \n";
                l_query += "    FROM pgmr.cliearticolo                \n";
                l_query += "   WHERE cdarti  =  ?                     \n";
                l_query += "     and tkclie  =  ?                     \n";
                l_query += "     and vacodi  =  ?                     \n";
                l_query += "     and dtival  <= ?                     \n";
                l_query += "     and (dtfval >= ? OR dtfval IS NULL)  \n";
        
                pstm = m_connection.prepareStatement(l_query);
        
                ind = 1;
                pstm.setString   (ind++, cdarti     );
                pstm.setString   (ind++, tkclie     );
                pstm.setString   (ind++, lstr.vacodi);
                pstm.setTimestamp(ind++, oggi       );
                pstm.setTimestamp(ind++, oggi       );
        
                rs = pstm.executeQuery();
        
                if (rs!=null && rs.next()){
                    
                    tot_rec += 1;
                    ind = 0;
                    if (rs.getObject("prezzo")!=null)  lstr.impuni  = rs.getDouble("prezzo");
                    if (rs.getObject("psc1"  )!=null)  lstr.sconto1 = rs.getDouble("psc1"  );
                    if (rs.getObject("psc2"  )!=null)  lstr.sconto2 = rs.getDouble("psc2"  );
                    if (rs.getObject("psc3"  )!=null)  lstr.sconto3 = rs.getDouble("psc3"  );
                    if (rs.getObject("psc4"  )!=null)  lstr.sconto4 = rs.getDouble("psc4"  );
                    
                    lstr.descr_tipoconto = "cliearticolo";
                    lstr.valido = true;
                }
                pstm.close();
        }

        if (!lstr.valido){

        
                // calcolo il prezzo di listino

                Lis_lisarticolo lis_lisarticolo = new Lis_lisarticolo();
                setProfilo((Atk_sql) lis_lisarticolo);
        
                rs = lis_lisarticolo.getPrezzo( cdarti, lstr.cdlist );
                
                
                if (rs != null && rs.next()){
                
                    lstr.impuni = rs.getDouble("prezzo");
                }
                lis_lisarticolo.close();
        
                if (lstr.impuni == 0 && !ls_cdlist_sost.equals("")){
                    
                    rs = lis_lisarticolo.getPrezzo( cdarti, ls_cdlist_sost );
                    
                    if (rs != null && rs.next()){
                    
                        lstr.impuni = rs.getDouble("prezzo");
                    }
                    lis_lisarticolo.close();
        
                    if (lstr.impuni > 0){
                        lstr.cdlist = ls_cdlist_sost;
                    }
                }

                if (!lstr.valido){
                    
                        l_query  = "";
                        l_query += " SELECT psc1, psc2, psc3, psc4, psc5       \n";
                        l_query += "   FROM pgmr.clie_classe_sco               \n";
                        l_query += "  WHERE cdclsc  =  ?                       \n";
                        l_query += "    and tkclie  =  ?                       \n";
                        l_query += "    and dtival  <= ?                       \n";
                        l_query += "    and (dtfval >= ? OR dtfval IS NULL)    \n";
                
                        pstm = m_connection.prepareStatement(l_query);
                
                        ind = 1;
                        pstm.setString(ind++, lstr.cdclsc_v);
                        pstm.setString(ind++, tkclie);
                        pstm.setTimestamp(ind++, oggi);
                        pstm.setTimestamp(ind++, oggi);
                
                        rs = pstm.executeQuery();
                
                        if (rs!=null && rs.next()){
                            
                            if (rs.getObject("psc1"  )!=null)  lstr.sconto1 = rs.getDouble("psc1"  );
                            if (rs.getObject("psc2"  )!=null)  lstr.sconto2 = rs.getDouble("psc2"  );
                            if (rs.getObject("psc3"  )!=null)  lstr.sconto3 = rs.getDouble("psc3"  );
                            if (rs.getObject("psc4"  )!=null)  lstr.sconto4 = rs.getDouble("psc4"  );
                            
                            lstr.descr_tipoconto = "clie_classe_sco";
                            lstr.valido = true;
                        }
                        pstm.close();


                }   // FINE lstr.valido


                if (!lstr.valido){
                    
                        l_query  = "";
                        l_query += " SELECT psc1, psc2, psc3, psc4, psc5     \n";
                        l_query += "   FROM pgmr.clieclasec_classe_sco       \n";
                        l_query += "  WHERE cdclsc   =  ?                    \n";
                        l_query += "    and cdcllist =  ?                    \n";
                        l_query += "    and dtival   <= ?                    \n";
                        l_query += "    and (dtfval  >= ? OR dtfval IS NULL) \n";
                
                        pstm = m_connection.prepareStatement(l_query);
                
                        ind = 1;
                        pstm.setString(ind++, lstr.cdclsc_v);
                        pstm.setString(ind++, lstr.cdcllist);
                        pstm.setTimestamp(ind++, oggi);         
                        pstm.setTimestamp(ind++, oggi);         
                
                        rs = pstm.executeQuery();
                
                        if (rs!=null && rs.next()){
                            
                            if (rs.getObject("psc1"  )!=null)  lstr.sconto1 = rs.getDouble("psc1"  );
                            if (rs.getObject("psc2"  )!=null)  lstr.sconto2 = rs.getDouble("psc2"  );
                            if (rs.getObject("psc3"  )!=null)  lstr.sconto3 = rs.getDouble("psc3"  );
                            if (rs.getObject("psc4"  )!=null)  lstr.sconto4 = rs.getDouble("psc4"  );
                            
                            lstr.descr_tipoconto = "clieclasec_classe_sco";
                            lstr.valido = true;
                        }
                        pstm.close();


                }   // FINE lstr.valido


                //TODO aggiunta check sconto sulle ulteriori tabelle nuove specifiche
                if (!lstr.valido){
                    
//                        boolean scPartExists = false;
                        // sconto articolo
                        l_query  = "";
                        l_query += " SELECT sconto1, sconto2, sconto3, sconto4, sconto5     \n";
                        l_query += "   FROM pgmr.vist_scocl_arti                            \n";
                        l_query += "  WHERE tkclie   =  ?                                   \n";
                        l_query += "    and cdarti =  ?                                     \n";
                        l_query  += "   and dtinva = ( select max(dtinva)               \n";
                        l_query  += "                     from pgmr.vist_scocl_arti     \n";
                        l_query  += "                    where tkclie = ?               \n";
                        l_query  += "                      and cdarti = ?               \n";
                        l_query  += "                      and dtinva <= ? )            \n";
                
                        pstm = m_connection.prepareStatement(l_query);
                
                        ind = 1;
                        pstm.setString(ind++, tkclie);
                        pstm.setString(ind++, cdarti);
                        pstm.setString(ind++, tkclie);
                        pstm.setString(ind++, cdarti);
                        pstm.setTimestamp(ind++, oggi);         
                
                        rs = pstm.executeQuery();
                
                        if (rs!=null && rs.next()){
                            
                            if (rs.getObject("sconto1"  )!=null)  lstr.sconto1 = rs.getDouble("sconto1"  );
                            if (rs.getObject("sconto2"  )!=null)  lstr.sconto2 = rs.getDouble("sconto2"  );
                            if (rs.getObject("sconto3"  )!=null)  lstr.sconto3 = rs.getDouble("sconto3"  );
                            if (rs.getObject("sconto4"  )!=null)  lstr.sconto4 = rs.getDouble("sconto4"  );
                            
                            lstr.descr_tipoconto = "vist_scocl_arti";
                            lstr.valido = true;
//                            scPartExists = true;
                        }
                        pstm.close();
                }  
                
                if (!lstr.valido){
                            
                            // sconto gruppo merceologico
                            l_query  = "";
                            l_query += " SELECT sconto1, sconto2, sconto3, sconto4, sconto5     \n";
                            l_query += "   FROM pgmr.vist_scocl_tipm                            \n";
                            l_query += "  WHERE tkclie   =  ?                                   \n";
                            l_query += "    and cdtipm =  ?                                     \n";
                            l_query  += "   and dtinva = ( select max(dtinva)               \n";
                            l_query  += "                     from pgmr.vist_scocl_tipm     \n";
                            l_query  += "                    where tkclie = ?               \n";
                            l_query  += "                      and cdtipm = ?               \n";
                            l_query  += "                      and dtinva <= ? )            \n";                            

                            pstm = m_connection.prepareStatement(l_query);

                            ind = 1;
                            pstm.setString(ind++, tkclie);
                            pstm.setString(ind++, cdtipm);
                            pstm.setString(ind++, tkclie);
                            pstm.setString(ind++, cdtipm);
                            pstm.setTimestamp(ind++, oggi);         

                            rs = pstm.executeQuery();

                            if (rs!=null && rs.next()){

                                if (rs.getObject("sconto1"  )!=null)  lstr.sconto1 = rs.getDouble("sconto1"  );
                                if (rs.getObject("sconto2"  )!=null)  lstr.sconto2 = rs.getDouble("sconto2"  );
                                if (rs.getObject("sconto3"  )!=null)  lstr.sconto3 = rs.getDouble("sconto3"  );
                                if (rs.getObject("sconto4"  )!=null)  lstr.sconto4 = rs.getDouble("sconto4"  );

                                lstr.descr_tipoconto = "vist_scocl_tipm";
                                lstr.valido = true;
                            }
                            pstm.close();                            
                            
                }
                        
                if (!lstr.valido){

                        // sconto collezione
                        l_query  = "";
                        l_query += " SELECT sconto1, sconto2, sconto3, sconto4, sconto5     \n";
                        l_query += "   FROM pgmr.vist_scocl_cp_col                          \n";
                        l_query += "  WHERE tkclie   =  ?                                   \n";
                        l_query += "    and cdvistccol =  ?                                 \n";
                        l_query  += "   and dtinva = ( select max(dtinva)                 \n";
                        l_query  += "                     from pgmr.vist_scocl_cp_col     \n";
                        l_query  += "                    where tkclie = ?                 \n";
                        l_query  += "                      and cdvistccol = ?             \n";
                        l_query  += "                      and dtinva <= ? )              \n";                           

                        pstm = m_connection.prepareStatement(l_query);

                        ind = 1;
                        pstm.setString(ind++, tkclie);
                        pstm.setString(ind++, cdvistccol);
                        pstm.setString(ind++, tkclie);
                        pstm.setString(ind++, cdvistccol);
                        pstm.setTimestamp(ind++, oggi);         

                        rs = pstm.executeQuery();

                        if (rs!=null && rs.next()){

                            if (rs.getObject("sconto1"  )!=null)  lstr.sconto1 = rs.getDouble("sconto1"  );
                            if (rs.getObject("sconto2"  )!=null)  lstr.sconto2 = rs.getDouble("sconto2"  );
                            if (rs.getObject("sconto3"  )!=null)  lstr.sconto3 = rs.getDouble("sconto3"  );
                            if (rs.getObject("sconto4"  )!=null)  lstr.sconto4 = rs.getDouble("sconto4"  );

                            lstr.descr_tipoconto = "vist_scocl_cp_col";
                            lstr.valido = true;
                        }
                        pstm.close();                            

                    }


//                }   // FINE lstr.valido
                
                if (!lstr.valido){
                        // imposto Gli sconti cliente
                
                        Archclie archclie = new Archclie(); 
                
                        setProfilo((Atk_sql) archclie);
                
                
                        rs = archclie.getCliente(tkclie);
                
                        if (rs!=null && rs.next()){
                
                            if (rs.getObject("sconto1")!=null)   lstr.sconto1 = rs.getDouble("sconto1");
                            if (rs.getObject("sconto2")!=null)   lstr.sconto2 = rs.getDouble("sconto2");
                            if (rs.getObject("sconto3")!=null)   lstr.sconto3 = rs.getDouble("sconto3");
                            if (rs.getObject("sconto4")!=null)   lstr.sconto4 = rs.getDouble("sconto4");

                            lstr.descr_tipoconto = "archclie";
                            lstr.valido = true;
                        }
                
                        archclie.close();
                    

                }   // FINE lstr.valido       
                
                // --- Verifico se gli sconti cliente in base al listino che ho in canna
                //     o all'articolo devono essere azzerati


                String ls_fescl_condv = getFescl_condv(lstr.cdlist, cdarti);


                if (ls_fescl_condv.equals("S")){
                    lstr.sconto1 = 0;
                    lstr.sconto2 = 0;
                    lstr.sconto3 = 0;
                    lstr.sconto4 = 0;
                    lstr.scrap1  = 0;
                    lstr.scrap2  = 0;
                }                
                

        }   // FINE if (!lstr.valido){


        if (lstr.sconto1 > lstr.scmax_1) lstr.scmax_1 = lstr.sconto1;
        if (lstr.sconto2 > lstr.scmax_2) lstr.scmax_2 = lstr.sconto2;

        if (lstr.scmax_1 <= 0) lstr.scmax_1 = 100; // posso dare lo sconto massimo in assoluto
        if (lstr.scmax_2 <= 0) lstr.scmax_2 = 100; // posso dare lo sconto massimo in assoluto


        of_netto (lstr);


        // lstr.print();



        return lstr;

    }






    /***


    */

    public void of_netto (Str_listino astr) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        // calcolo impuni netto

        astr.impuninetto = astr.impuni;
        
                
        if (astr.sconto1 > 0 ){
            astr.impuninetto = astr.impuninetto - (astr.impuninetto * astr.sconto1 / 100);
        }
        if (astr.sconto2 > 0 ){
            astr.impuninetto = astr.impuninetto - (astr.impuninetto * astr.sconto2 / 100);
        }
        if (astr.sconto3 > 0 ){
            astr.impuninetto = astr.impuninetto - (astr.impuninetto * astr.sconto3 / 100);
        }
        if (astr.sconto4 > 0 ){
            astr.impuninetto = astr.impuninetto - (astr.impuninetto * astr.sconto4 / 100);
        }

        if (astr.scrap1 > 0 ){
            astr.impuninetto = astr.impuninetto - (astr.impuninetto * astr.scrap1 / 100);
        }
        if (astr.scrap2 > 0 ){
            astr.impuninetto = astr.impuninetto - (astr.impuninetto * astr.scrap2 / 100);
        }

        com.ateikon.util.Atk_ctrl atk_ctrl = new com.ateikon.util.Atk_ctrl();
        
        astr.descr_sconti = atk_ctrl.descrSconti(new double[]{ astr.sconto1
                                                             , astr.sconto2
                                                             , astr.sconto3
                                                             , astr.sconto4
                                                             , astr.scrap1
                                                             , astr.scrap2
                                                                           });




        return  ;

    }



    /***

    */

    public double[] of_ricalcola_sc (double sc_, double scmax, double sc_2 ) throws Exception {

        if (scmax <= 0){
            return new double[]{sc_, sc_2};
        }

        if (sc_ <= scmax){
            return new double[]{sc_, sc_2};
        }

        double ldb_imp = 100d;

        double ldb_imp_sc = ldb_imp * (100-sc_) / 100;      // importo con lo sconto originale
        ldb_imp_sc = ldb_imp_sc * (100-sc_2) / 100;     

        double ldb_imp_scmax = ldb_imp * (100-scmax) / 100; // importo con lo sconto minorato fino al scmax
        double sc_new = (ldb_imp_scmax - ldb_imp_sc) * 100 / ldb_imp_scmax; 

        return new double[]{scmax, sc_new};

    }

    /***

    */

    public double[] of_ricalcola_sc_inv (double sc_1, double scmax, double sc_2 ) throws Exception {

        if (sc_2 <= 0){
            return new double[]{sc_1, sc_2};
        }

        if (sc_1 >= scmax){
            return new double[]{sc_1, sc_2};
        }
        if (scmax <= 0){
            return new double[]{sc_1, sc_2};
        }



        double ldb_imp = 100d;
        double ldb_imp_scmax = ldb_imp * (100-scmax) / 100; 

        double ldb_imp_sc1 = ldb_imp * (100-sc_1) / 100;      
        double ldb_imp_sc2 = ldb_imp_sc1 * (100-sc_2) / 100;      

        if (ldb_imp_sc2==ldb_imp_scmax){
            return new double[]{scmax, 0d};
        }

        if (ldb_imp_sc2 > ldb_imp_scmax){
            // ricalcolo lo sconto porto tutto sullo sconto1

            sc_1 = (ldb_imp - ldb_imp_sc2) * 100 / ldb_imp;
            return new double[]{sc_1, 0d};
        }

        if (ldb_imp_sc2 < ldb_imp_scmax){
            sc_2 = (ldb_imp_scmax - ldb_imp_sc2) * 100 / ldb_imp_scmax;
            return new double[]{scmax, sc_2};
        }


        return new double[]{sc_1, sc_2};

    }



    /***


    */

    public String getFescl_condv( String cdlist
                                , String cdarti 
                                                    ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        String ls_fescl_condv = "N";

        l_query  = "";
        l_query += " select fescl_condv       \n";
        l_query += "   from pgmr.lis_listino  \n";
        l_query += "  where cdlist = ?        \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;
        pstm.setString(ind++, cdlist);

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){
            
            if (rs.getObject(1)!=null)  ls_fescl_condv = rs.getString(1);

        }
        pstm.close();

        
        if (!ls_fescl_condv.equals("S")){
                
            l_query  = "";
            l_query += " select fescl_condv             \n";
            l_query += "   from pgmr.mrp_arch_articoli  \n";
            l_query += "  where cdarti = ?              \n";
            l_query += "    and cdazie = ?              \n";
    
            pstm = m_connection.prepareStatement(l_query);
    
            ind = 1;
            pstm.setString(ind++, cdarti);
            pstm.setString(ind++, cdazie);
    
            rs = pstm.executeQuery();
    
            if (rs!=null && rs.next()){
                
                if (rs.getObject(1)!=null)  ls_fescl_condv = rs.getString(1);
    
            }
            pstm.close();
        }



        return ls_fescl_condv ;

    }



    /***

        In base alla nazione o alla provincia calcolo le spese di strasporto per
        il carrello
    */

    public int imptrasp ( Str_imptrasp astr  ) throws Exception {
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        Costanti_comm costanti_comm = new Costanti_comm();

        setProfilo((Atk_sql) costanti_comm);




        astr.cdnazi = "";
        astr.cdprov = "";

        tot_rec = 0;

        String cdstat_de = "";
        String cdprov_de = "";
        String cduldm = "";
        String tkclie = "";
        String fatrac = "";
        String fatraf = "";
        String fatrad = "";
        String cdclai = "";

        double addtra   = 0;
        double nraddtra = 0;


        l_query  = "";
        l_query += "  select ordt.cdstat_de                \n";
        l_query += "       , ordt.cdprov_de                \n";
        l_query += "       , ordt.cduldm                   \n";
        l_query += "       , ordt.tkclie                   \n";
        l_query += "       , clie.fatrac                   \n";
        l_query += "       , clie.fatraf                   \n";
        l_query += "       , clie.fatrad                   \n";
        l_query += "       , clie.addtra                   \n";
        l_query += "       , clie.nraddtra                 \n";
        l_query += "    from web.ord_test  ordt            \n";
        l_query += "       , pgmr.archclie clie            \n";
        l_query += "   where ordt.tkordi = "+astr.tkordi+" \n";
        l_query += "     and ordt.tkclie = clie.tkclie     \n";
        l_query += "     and clie.cdazie = '"+cdazie+"'    \n";
        
        pstm = m_connection.prepareStatement(l_query);

        ind = 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){
            
            tot_rec += 1;

            if (rs.getObject("cdstat_de")!=null)  cdstat_de   = rs.getString("cdstat_de"); 
            if (rs.getObject("cdprov_de")!=null)  cdprov_de   = rs.getString("cdprov_de"); 
            if (rs.getObject("cduldm"   )!=null)  cduldm      = rs.getString("cduldm"   ); 
            if (rs.getObject("tkclie"   )!=null)  tkclie      = rs.getString("tkclie"   ); 
            if (rs.getObject("fatrac"   )!=null)  fatrac      = rs.getString("fatrac"   );
            if (rs.getObject("fatraf"   )!=null)  fatraf      = rs.getString("fatraf"   );
            if (rs.getObject("fatrad"   )!=null)  fatrad      = rs.getString("fatrad"   );
            if (rs.getObject("cdclai"   )!=null)  cdclai      = rs.getString("cdclai"   );

            if (rs.getObject("addtra"   )!=null)  addtra      = rs.getDouble("addtra"   );
            if (rs.getObject("nraddtra" )!=null)  nraddtra    = rs.getDouble("nraddtra" );

            astr.cdnazi = cdstat_de;
            astr.cdprov = cdprov_de;


        }

        pstm.close();



        double importonetto = 0;
        double imptrasp     = 0;

        l_query  = "";
        l_query += " select sum(importonettoriga)    \n";
        l_query += "   from web.ord_positito         \n";
        l_query += "  where tkordi = "+astr.tkordi+"      \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){
            
            tot_rec += 1;
            if (rs.getObject(1)!=null)  importonetto = rs.getDouble(1);
        }
        pstm.close();

        




        if (fatrac.equals("N") && fatraf.equals("N") && fatrad.equals("N")){
            
            // spese di trasporto non definite per questo cliente
            // applico l'agoritmo web.


                        if (astr.cdnazi.equals("")  || astr.cdprov.equals("")){
                
                            // cerco dalla unità locali
                
                            l_query  = "";
                            l_query += "  select cdnazi                \n";
                            l_query += "       , cdprov                \n";
                            l_query += "    from pgmr.unitalocali      \n";
                            l_query += "   where cdunil = '"+cduldm+"' \n";
                            
                            pstm = m_connection.prepareStatement(l_query);
                    
                            ind = 1;
                    
                            rs = pstm.executeQuery();
                    
                            if (rs!=null && rs.next()){
                                
                                tot_rec += 1;
                                if (rs.getObject(1)!=null)  astr.cdnazi = rs.getString(1);
                                if (rs.getObject(2)!=null)  astr.cdprov = rs.getString(2);
                            }
                            pstm.close();
                        }
                
                
                        // Verifico se c'è un defaul generale 

                        /*
                        astr.importo_min = Double.parseDouble(costanti_comm.getCostvalue("imptrasp_min"));
                        astr.importo_max = Double.parseDouble(costanti_comm.getCostvalue("imptrasp_max"));
                        */


                        l_query  = "";
                        l_query += "  select perc                   \n";
                        l_query += "       , importo_min            \n";
                        l_query += "       , importo_max            \n";
                        l_query += "    from pgmr.cat_imptrasp      \n";
                        l_query += "   where cdnazi is null         \n";
                        l_query += "     and cdprov is null         \n";
                        
                
                        pstm = m_connection.prepareStatement(l_query);
                
                        ind = 1;
                
                        rs = pstm.executeQuery();
                
                        if (rs!=null && rs.next()){
                            
                            tot_rec += 1;
                            if (rs.getObject(1)!=null)  astr.perc        = rs.getDouble(1);
                            if (rs.getObject(2)!=null)  astr.importo_min = rs.getDouble(2);
                            if (rs.getObject(3)!=null)  astr.importo_max = rs.getDouble(3);
                        }
                        pstm.close();
                
                        // Verifico se sono impostate a livello di nazione 
                
                        l_query  = "";
                        l_query += "  select perc                       \n";
                        l_query += "       , importo_min                \n";
                        l_query += "       , importo_max                \n";
                        l_query += "    from pgmr.cat_imptrasp          \n";
                        l_query += "   where cdnazi = '"+astr.cdnazi+"' \n";
                        l_query += "     and cdprov is null             \n";
                        
                
                        pstm = m_connection.prepareStatement(l_query);
                
                        ind = 1;
                
                        rs = pstm.executeQuery();
                
                        if (rs!=null && rs.next()){
                            
                            tot_rec += 1;
                            if (rs.getObject(1)!=null)  astr.perc        = rs.getDouble(1);
                            if (rs.getObject(2)!=null)  astr.importo_min = rs.getDouble(2);
                            if (rs.getObject(3)!=null)  astr.importo_max = rs.getDouble(3);
                        }
                        pstm.close();
                
                        // Verifico se sono impostate a livello di provincia
                
                        l_query  = "";
                        l_query += "  select perc                       \n";
                        l_query += "       , importo_min                \n";
                        l_query += "       , importo_max            \n";
                        l_query += "    from pgmr.cat_imptrasp          \n";
                        l_query += "   where cdnazi is null             \n";
                        l_query += "     and cdprov = '"+astr.cdprov+"' \n";
                        
                
                        pstm = m_connection.prepareStatement(l_query);
                
                        ind = 1;
                
                        rs = pstm.executeQuery();
                
                        if (rs!=null && rs.next()){
                            
                            tot_rec += 1;
                            if (rs.getObject(1)!=null)  astr.perc        = rs.getDouble(1);
                            if (rs.getObject(2)!=null)  astr.importo_min = rs.getDouble(2);
                            if (rs.getObject(3)!=null)  astr.importo_max = rs.getDouble(3);
                        }
                        pstm.close();
                
                
                
                        // Calcolo l'importo percentuale 
                
                        astr.tipo_calc = astr.TIPO_CALC_PERC;
                       
                        imptrasp = importonetto / 100 * astr.perc; 
                
                
                        if (imptrasp < astr.importo_min){
                            
                            imptrasp = astr.importo_min;
                            astr.tipo_calc = astr.TIPO_CALC_IMPORTO_MIN;
                        }
                
                        if (astr.importo_max > 0 && imptrasp > astr.importo_max){
                           
                            imptrasp = astr.importo_max;
                            astr.tipo_calc = astr.TIPO_CALC_IMPORTO_MAX;
                            
                        }
                
                
                        astr.imptrasp = imptrasp;
                
                        if (astr.tipo_calc == astr.TIPO_CALC_IMPORTO_MIN){
                            astr.perc = 0;
                            astr.importo_max = 0;
                        }else if (astr.tipo_calc == astr.TIPO_CALC_IMPORTO_MAX){
                            astr.perc = 0;
                            astr.importo_min = 0;
                        }else {
                            astr.importo_min = 0;
                            astr.importo_max = 0;
                        }
                

        }else { //  if (fatrac.equals("N") && fatraf.equals("N") && fatrad.equals("N")){
            
                        imptrasp = 0;

                        double cst_imptrasp_min = Double.parseDouble(costanti_comm.getCostvalue("imptrasp_min"));
                        double cst_imptrasp_max = Double.parseDouble(costanti_comm.getCostvalue("imptrasp_max"));

                        if (fatrac.equals("S")){
                        
                            imptrasp = importonetto / 100 * addtra; 
                            
                        }else if (fatraf.equals("S")){
                            
                            imptrasp = nraddtra; 

                        }else if (fatrad.equals("S")){
                            
                            // Calcolo l'importo in base ai radiali

                        }



                        if (fatrac.equals("S") || fatrad.equals("S")){
                            
                            if (imptrasp < cst_imptrasp_min){
                                imptrasp = cst_imptrasp_min;
                            }
                    
                            if (cst_imptrasp_max > 0 && imptrasp > cst_imptrasp_max){
                                imptrasp = cst_imptrasp_max;
                            }
                        }

                        astr.imptrasp = imptrasp;


        }   // FINE  if (fatrac.equals("N") && fatraf.equals("N") && fatrad.equals("N")){



        return 1;
    }











    // Intergaccia



    java.text.NumberFormat numFormat = null;
    java.text.NumberFormat przFormat = null;


}
