package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import com.ateikon.function.F_iva_assofisc;
import com.ateikon.function.F_listino;
import com.ateikon.function.F_tabkey;

import com.ateikon.structure.Str_listino;
import com.ateikon.structure.Str_iva_assofisc;

public class Carrello extends Atk_sql {

    public Carrello() {
        
        super();
    }


    public ResultSet getCarrello(long tkcarrello) throws Exception {
        
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select *                     \n";
		l_query  += "   from web.carrello \n";
		l_query  += "  where tkcarrello = ?        \n";
		l_query  += "  order by tkcarrellop desc   \n";

		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setLong(ind, tkcarrello); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }




    
    public void ricalcolaPrezzi( long   tkcarrello
                               , String cdlist
                               , String s_ivato
                               , String s_cdiva
                               , String s_cdfisc
                                                    ) throws Exception {
        
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        PreparedStatement pstm_box = null;

        double tot_imp = 0;

        int li_ = 0;
        String cdiva  = "";
        String cdfisc = "";



        Kg_articolo kg_articolo = new Kg_articolo();

        setProfilo((Atk_sql)kg_articolo);


        String tkclie = "";
        double scocas = 0;


        l_query   = "";
		l_query  += " select b.tkclie                     \n";
		l_query  += "      , b.scocas                     \n";
		l_query  += "   from web.carrello_test a \n";
		l_query  += "      , pgmr.archclie          b \n";
		l_query  += "  where a.tkcarrello = ?             \n";
		l_query  += "    and b.tkclie = a.tkclie          \n";

		pstm = m_connection.prepareStatement ( l_query ) ;


        ind = 1;
        pstm.setLong(ind, tkcarrello); ind += 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){
            
            if (rs.getObject("tkclie")!=null) tkclie = rs.getString("tkclie");
            if (rs.getObject("scocas")!=null) scocas = rs.getDouble("scocas");

        }else {
            throw new Exception("Token Carrrello non tovato: -->"+tkcarrello);
        }

        pstm.close();


        // inizializzo

        F_listino      f_listino      = new F_listino();
        F_iva_assofisc f_iva_assofisc = new F_iva_assofisc();
        
        setProfilo((Atk_sql) f_listino);
        setProfilo((Atk_sql) f_iva_assofisc);
        

        Str_listino str_listino = null;


        long   tkcarrellop  = 0;
        String cdarti = "";
        double quantita     = 0;
        double importoriga  = 0;
        double importonetto = 0;
        double impuni       = 0;
        double impuninetto  = 0;

        // Reimposto Gli sconti di testata

        l_query   = "";
		l_query  += " update web.carrello_test  \n";
		l_query  += "    set scocas = ?       \n";
		l_query  += "      , ivato  = ?       \n";
    	l_query  += "  where tkcarrello  = ?       \n";

		pstm_box = m_connection.prepareStatement ( l_query ) ;

        ind = 1;
        pstm_box.setDouble(ind, scocas); ind += 1;
        pstm_box.setString(ind, s_ivato); ind += 1;
        pstm_box.setLong  (ind, tkcarrello); ind += 1;

        tot_rec = pstm_box.executeUpdate();

        m_connection.commit();




        // ricalcolo i prezzi delle righe


        l_query   = "";
		l_query  += " update web.carrello  \n";
		l_query  += "    set impuni       = ?       \n";
		l_query  += "      , impuninetto  = ?       \n";
		l_query  += "      , importoriga  = ?       \n";
		l_query  += "      , importonetto = ?       \n";
		l_query  += "      , sconto1      = ?       \n";
		l_query  += "      , sconto2      = ?       \n";
		l_query  += "      , sconto3      = ?       \n";
		l_query  += "      , sconto4      = ?       \n";
		l_query  += "      , scrap1       = ?       \n";
		l_query  += "      , scrap2       = ?       \n";
		l_query  += "      , cdiva        = ?       \n";
		l_query  += "      , cdfisc       = ?       \n";
    	l_query  += "  where tkcarrellop  = ?       \n";

		pstm_box = m_connection.prepareStatement ( l_query ) ;







        l_query   = "";
		l_query  += " select *                     \n";
		l_query  += "   from web.carrello \n";
		l_query  += "  where tkcarrello = ?        \n";

		pstm = m_connection.prepareStatement ( l_query ) ;

        ind = 1;
        pstm.setLong(ind, tkcarrello); ind += 1;

        rs = pstm.executeQuery();
                                                   
        while (rs!=null && rs.next() ){
          
            str_listino = null;


            tkcarrellop = rs.getLong("tkcarrellop");

            if (rs.getObject("quantita")!=null) quantita = rs.getDouble("quantita");
            if (rs.getObject("cdarti")!=null) cdarti = rs.getString("cdarti");

            str_listino = f_listino.calcolaPrezzo(cdlist, tkclie, cdarti, quantita);


            double sconto1  = str_listino.sconto1;
            double sconto2  = str_listino.sconto2;
            double sconto3  = str_listino.sconto3;
            double sconto4  = str_listino.sconto4;
            double scrap1   = str_listino.scrap1 ;
            double scrap2   = str_listino.scrap2 ;
            scocas   = str_listino.scocas ;

            impuni      = str_listino.impuni;
            impuninetto = str_listino.impuninetto;

            importoriga  = quantita * impuni;
            importonetto = quantita * impuninetto;





            // IVA - ASSOFISCAL

            Str_iva_assofisc lstr_iva = new Str_iva_assofisc();

            lstr_iva.cdarti   = cdarti;
            lstr_iva.cdiva_t  = s_cdiva;
            lstr_iva.cdfisc_t = s_cdfisc;


            li_ = f_iva_assofisc.exec(lstr_iva);

    
            if (li_!= 0){

                throw new Exception("Errore Calcolo IVA.");
            }
    


            ind = 1;
            pstm_box.setDouble(ind, impuni          ); ind += 1;
            pstm_box.setDouble(ind, impuninetto     ); ind += 1;
            pstm_box.setDouble(ind, importoriga     ); ind += 1;
            pstm_box.setDouble(ind, importonetto    ); ind += 1;
            pstm_box.setDouble(ind, sconto1         ); ind += 1;
            pstm_box.setDouble(ind, sconto2         ); ind += 1;
            pstm_box.setDouble(ind, sconto3         ); ind += 1;
            pstm_box.setDouble(ind, sconto4         ); ind += 1;
            pstm_box.setDouble(ind, scrap1          ); ind += 1;
            pstm_box.setDouble(ind, scrap2          ); ind += 1;
            pstm_box.setString(ind, lstr_iva.cdiva  ); ind += 1;
            pstm_box.setString(ind, lstr_iva.cdfisc ); ind += 1;
            pstm_box.setLong  (ind, tkcarrellop     ); ind += 1;
    
            tot_rec = pstm_box.executeUpdate();

            if (tot_rec > 0){
            
                m_connection.commit();
                
            }else {
                throw new Exception("Errore agg. Prezzo riga Carrello : "+tkcarrello);
            }


        }
        pstm.close();




    }


    public double getImp_tototale(long tkcarrello) throws Exception {
        
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        double tot_imp = 0;


        l_query   = "";
		l_query  += " select sum(importonetto) as tot_imp  \n";
		l_query  += "   from web.carrello \n";
		l_query  += "  where tkcarrello = ?        \n";

		pstm = m_connection.prepareStatement ( l_query ) ;

        ind = 1;
        pstm.setLong(ind, tkcarrello); ind += 1;

        rs = pstm.executeQuery();
                                                   
        if (rs!=null && rs.next() && rs.getObject("tot_imp")!=null){
            tot_imp = rs.getDouble("tot_imp");
        }
        pstm.close();

        return tot_imp;


    }

    public double getQta_tototale(long tkcarrello) throws Exception {
        
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        double quantita = 0;


        l_query   = "";
		l_query  += " select sum(quantita) as quantita  \n";
		l_query  += "   from web.carrello \n";
		l_query  += "  where tkcarrello = ?        \n";

		pstm = m_connection.prepareStatement ( l_query ) ;

        ind = 1;
        pstm.setLong(ind, tkcarrello); ind += 1;

        rs = pstm.executeQuery();
                                                   
        if (rs!=null && rs.next() && rs.getObject("quantita")!=null){
            quantita = rs.getDouble("quantita");
        }
        pstm.close();

        return quantita;


    }

    public ResultSet getArticoli( long   tkcarrello ) throws Exception {
        
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;


        l_query   = "";
		l_query  += " select *                       \n";
		l_query  += "   from web.carrello   \n";
		l_query  += "  where tkcarrello  = ?         \n";
		l_query  += "    and quantita    > 0         \n";
		l_query  += "  order by tkcarrellop          \n";

		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setLong   (ind, tkcarrello); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }





    public ResultSet getArticolo( long   tkcarrello
                                , String cdarti     
                                , String cdcolint
                                                    ) throws Exception {
        
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select *                      \n";
        l_query  += "   from web.carrello  \n";
        l_query  += "  where tkcarrello  = ?        \n";
        l_query  += "    and cdarti      = ?        \n";
        l_query  += "    and cdcolint   = ?        \n";
        
        pstm = setQuery( l_query ) ;


        ind = 1;
        pstm.setLong   (ind, tkcarrello); ind += 1;
        pstm.setString (ind, cdarti      ); ind += 1;
        pstm.setString (ind, cdcolint    ); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }

    public int getQuantita ( long   tkcarrello
                           , String cdarti
                           , String cdcolint
                                                    ) throws Exception {
        
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;


        rs = getArticolo( tkcarrello, cdarti , cdcolint) ;

        if (rs != null && rs.next() && rs.getObject("quantita")!=null){
            
            return rs.getInt("quantita");

        }

        return 0;


    }


    public int       deleteArticolo( long   tkcarrello
                                   , String cdarti
                                   , String cdcolint
                                                            ) throws Exception {
        
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;


        l_query   = "";
        l_query  += " delete                        \n";
        l_query  += "   from web.carrello  \n";
        l_query  += "  where tkcarrello  = ?        \n";
        l_query  += "    and cdarti      = ?        \n";
        l_query  += "    and cdcolint    = ?        \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setLong   (ind, tkcarrello); ind += 1;
        pstm.setString (ind, cdarti      ); ind += 1;
        pstm.setString (ind, cdcolint    ); ind += 1;

        tot_rec = pstm.executeUpdate();

        ctrlCarrello( tkcarrello );

        return tot_rec;


    }


    public int       deletePosizione( long   tkcarrellop) throws Exception {
        
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select tkcarrello     \n";
        l_query  += "   from web.carrello  \n";
        l_query  += "  where tkcarrellop  = " + tkcarrellop + "        \n";
        
        long    tkcarrello = sql_long(l_query);

        l_query   = "";
        l_query  += " delete                        \n";
        l_query  += "   from web.carrello  \n";
        l_query  += "  where tkcarrellop  = ?        \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setLong   (ind, tkcarrellop); ind += 1;

        tot_rec = pstm.executeUpdate();

        if (tkcarrello > 0) ctrlCarrello( tkcarrello );

        return tot_rec;


    }


    public int ctrlCarrello( long   tkcarrello ) throws Exception {
        
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;


        // se non ci articoli nel carrello elimino il carrello

        l_query   = "";
		l_query  += " select count(*) as tot_rec    \n";
		l_query  += "   from web.carrello  \n";
		l_query  += "  where tkcarrello  = ?        \n";
		l_query  += "    and quantita > 0           \n";


		pstm = setQuery( l_query ) ;
        ind = 1;
        pstm.setLong   (ind, tkcarrello); ind += 1;

        rs = pstm.executeQuery();

        if (rs !=null && rs.next() && rs.getObject("tot_rec")!=null){
            
            int li_ = rs.getInt("tot_rec");

            if (li_ <= 0){
                Carrello_test carrello_test = new Carrello_test ();
                setProfilo((Atk_sql) carrello_test );

                tot_rec = carrello_test.executeDelete(tkcarrello);
            }

        }


        return tot_rec;


    }



    public double importoMinimo = 0;
    public double qtaMinima     = 0;

    public void getCondven( String tkclie, String fgnew ) throws Exception {

        
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        String              cdclac = "";


        this.importoMinimo = 0;
        this.qtaMinima     = 0;

        // vrifico se ho una condizione particolare 
        // per questo cliente

        tot_rec = 0;

        l_query   = "";
        l_query  += " select max(impmin) as impmin \n";
        l_query  += "      , max(qtamin) as qtamin \n";
        l_query  += "   from pgmr.condven          \n";
        l_query  += "  where tkclie = ?            \n";

        pstm = m_connection.prepareStatement(l_query);
        
        ind = 1;
        pstm.setString(ind, tkclie); ind += 1;

        rs = pstm.executeQuery();

        if (rs !=null && rs.next()  ){
            
            // Se l'importo è zero ==> questo cliente non ha minimo ordine
            if (rs.getObject("impmin")!=null) this.importoMinimo = rs.getDouble("impmin");
            if (rs.getObject("qtamin")!=null) this.qtaMinima     = rs.getDouble("qtamin");
                
            pstm.close();
            return;
        }

        pstm.close();






        /* ***
        Costanti_comm costanti_comm = new Costanti_comm();

        setProfilo((Atk_sql) costanti_comm);

        String cliente = costanti_comm.getCostvalue("ep.cliente");


        // Eccezione Blinki
        //           TOYX.IT DI ALESSANDRO DE BELLI
        //           ARCOBALOCCO DI FRANCO FIUMICELLI

        if (cliente.equals("DELONGHI") 
            &&  (  tkclie.equals("0000008218") 
                || tkclie.equals("0000008992")
                || tkclie.equals("0000009332")
                    )
                ){
            
            this.importoMinimo = 0;
            this.qtaMinima     = 0;
            
            return;
        }
        *** */

        // verifico se è il primo ordine web

        if (fgnew.equals("S")){
            

            // questo è un nuovo cliente fa il primo ordine web quindi 
            // verifico  se  ci  sono delle maggiorazioni per il primo  
            // acquisto WEB


            l_query   = "";
            l_query  += " select max(impmin) as impmin \n";
            l_query  += "      , max(qtamin) as qtamin \n";
            l_query  += "   from pgmr.condven      \n";
            l_query  += "  where fgnew = 'S'                           \n";
            l_query  += "    and cdclac  = ( select cdclac             \n";
            l_query  += "                      from pgmr.archclie  \n";
            l_query  += "                     where tkclie  = ?        \n";
            l_query  += "   )     \n";
    
            pstm = setQuery( l_query ) ;
            ind = 1;
            pstm.setString(ind, tkclie); ind += 1;
    
            rs = pstm.executeQuery();
    
            if (rs !=null && rs.next()  ){
                if (rs.getObject("impmin")!=null) this.importoMinimo = rs.getDouble("impmin");
                if (rs.getObject("qtamin")!=null) this.qtaMinima     = rs.getDouble("qtamin");
            }

            if (this.importoMinimo > 0 || this.qtaMinima > 0){
                return;
            }


            // Controllo delle condizioni di vendita di default
    
    
            l_query   = "";
            l_query  += " select max(impmin) as impmin \n";
            l_query  += "      , max(qtamin) as qtamin \n";
            l_query  += "   from pgmr.condven      \n";
            l_query  += "  where fgnew = 'S'           \n";
            l_query  += "    and cdclac  is null       \n";
            l_query  += "    and tkclie  is null       \n";
    
            pstm = setQuery( l_query ) ;
    
            rs = pstm.executeQuery();
    
            if (rs !=null && rs.next()  ){
                if (rs.getObject("impmin")!=null) this.importoMinimo = rs.getDouble("impmin");
                if (rs.getObject("qtamin")!=null) this.qtaMinima     = rs.getDouble("qtamin");
            }

            if (this.importoMinimo > 0 || this.qtaMinima > 0){
                return;
            }



        }   // FINE if (fgnew.equals("S")){




        // Controllo delle condizioni di vendita del carrello

        l_query   = "";
        l_query  += " select max(impmin) as impmin \n";
        l_query  += "      , max(qtamin) as qtamin \n";
        l_query  += "   from pgmr.condven      \n";
        l_query  += "  where fgnew   = 'N'                         \n";
        l_query  += "    and cdclac  = ( select cdclac             \n";
        l_query  += "                      from pgmr.archclie  \n";
        l_query  += "                     where tkclie  = ?        \n";
        l_query  += "   )     \n";

        pstm = setQuery( l_query ) ;
        ind = 1;
        pstm.setString(ind, tkclie); ind += 1;

        rs = pstm.executeQuery();

        if (rs !=null && rs.next()  ){
            if (rs.getObject("impmin")!=null) this.importoMinimo = rs.getDouble("impmin");
            if (rs.getObject("qtamin")!=null) this.qtaMinima     = rs.getDouble("qtamin");

        }

        if (this.importoMinimo > 0 || this.qtaMinima > 0){
            return;
        }


        // Controllo delle condizioni di vendita di default


        l_query   = "";
        l_query  += " select max(impmin) as impmin \n";
        l_query  += "      , max(qtamin) as qtamin \n";
        l_query  += "   from pgmr.condven      \n";
        l_query  += "  where fgnew = 'N'           \n";
        l_query  += "    and cdclac  is null       \n";
        l_query  += "    and tkclie  is null       \n";

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        if (rs !=null && rs.next()  ){
            if (rs.getObject("impmin")!=null) this.importoMinimo = rs.getDouble("impmin");
            if (rs.getObject("qtamin")!=null) this.qtaMinima     = rs.getDouble("qtamin");
        }


        if (this.importoMinimo > 0 || this.qtaMinima > 0){
            return;
        }

        return ;

    }






    public int  executeInsert   ( long   tkcarrello
                                , String cdarti
                                , String cdcolint
                                , int    quantita
                                , double impuni
                                , double impuninetto
                                , double sconto1
                                , double sconto2
                                , double sconto3
                                , double sconto4
                                , double scrap1
                                , double scrap2
                                , double importoriga
                                , double importonetto
                                , double qtaric_tr
                                , double qtaprel
                                , long   tkmaga_tr
                                , String cdunim
                                , String cdiva 
                                , String cdfisc
                                                        ) throws Exception {
        
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;


        if (quantita <= 0) return 0;

        F_tabkey f_tabkey = new F_tabkey();
        setProfilo((Atk_sql) f_tabkey );

        Atk_pargen atk_pargen = new Atk_pargen();
        setProfilo((Atk_sql) atk_pargen);

        this.tkcarrellop = 0;

        long      tkcarrellop = f_tabkey.getTabkey( "carrello" );
        this.tkcarrellop = tkcarrellop ;

        if (cdunim.equals("")) cdunim = null;


        l_query   = "";

		l_query  += " insert into web.carrello (   \n";
		l_query  += "             tkcarrello                \n";
		l_query  += "           , cdarti                    \n";
		l_query  += "           , cdcolint                  \n";
		l_query  += "           , quantita                  \n";
		l_query  += "           , impuni                    \n";
		
                l_query  += "           , impuninetto               \n";
		l_query  += "           , sconto1                   \n";
		l_query  += "           , sconto2                   \n";
		l_query  += "           , sconto3                   \n";
		l_query  += "           , sconto4                   \n";

                l_query  += "           , scrap1                    \n";
		l_query  += "           , scrap2                    \n";
		l_query  += "           , importoriga               \n";
		l_query  += "           , importonetto              \n";
		l_query  += "           , cdazie                    \n";

                l_query  += "           , cddipa                    \n";
		l_query  += "           , profil                    \n";
		l_query  += "           , dtinse                    \n";
		l_query  += "           , dtulag                    \n";
		l_query  += "           , qtaric_tr                 \n";

                l_query  += "           , qtaprel                   \n";
		l_query  += "           , tkmaga_tr                 \n";
		l_query  += "           , cdunim                    \n";
		l_query  += "           , tkcarrellop               \n";
		l_query  += "           , cdiva                     \n";

                l_query  += "           , cdfisc                    \n";

		l_query  += "           )values (                   \n";
		l_query  += "             ?,?,?,?,?                 \n";
		l_query  += "           , ?,?,?,?,?                 \n";
		l_query  += "           , ?,?,?,?,?                 \n";
		l_query  += "           , ?,?,?,?,?                 \n";
		l_query  += "           , ?,?,?,?,?                 \n";
		l_query  += "           , ?                         \n";
		l_query  += "           )                           \n";

		pstm = setQuery( l_query ) ;



        ind = 1;
        pstm.setLong  (ind, tkcarrello  ); ind += 1;
        pstm.setString(ind, cdarti      ); ind += 1;
        pstm.setString(ind, cdcolint    ); ind += 1;
        pstm.setInt   (ind, quantita    ); ind += 1;
        pstm.setDouble(ind, impuni      ); ind += 1;
        pstm.setDouble(ind, impuninetto      ); ind += 1;
        pstm.setDouble(ind, sconto1     ); ind += 1;
        pstm.setDouble(ind, sconto2     ); ind += 1;
        pstm.setDouble(ind, sconto3     ); ind += 1;
        pstm.setDouble(ind, sconto4     ); ind += 1;
        pstm.setDouble(ind, scrap1     ); ind += 1;
        pstm.setDouble(ind, scrap2     ); ind += 1;
        pstm.setDouble(ind, importoriga); ind += 1;
        pstm.setDouble(ind, importonetto); ind += 1;
        pstm.setString(ind, cdazie      ); ind += 1;
        pstm.setString(ind, cddipa      ); ind += 1;
        pstm.setString(ind, profil      ); ind += 1;
        pstm.setTimestamp(ind, dtinse      ); ind += 1;
        pstm.setTimestamp(ind, dtulag      ); ind += 1;
        pstm.setDouble(ind, qtaric_tr   ); ind += 1;
        pstm.setDouble(ind, qtaprel     ); ind += 1;
        pstm.setLong  (ind, tkmaga_tr   ); ind += 1;
        pstm.setString(ind, cdunim      ); ind += 1;
        pstm.setLong  (ind, tkcarrellop ); ind += 1;
        pstm.setString(ind, cdiva       ); ind += 1;
        pstm.setString(ind, cdfisc      ); ind += 1;

        tot_rec = pstm.executeUpdate();

        if (tot_rec > 0){
            
        }else {
            this.tkcarrellop = 0;
        }

        return tot_rec;


    }

    public int  executeUpdate   ( long   tkcarrellop
                                , long   tkcarrello
                                , String cdarti
                                , String cdcolint
                                , int    quantita
                                , double impuni
                                , double impuninetto
                                , double sconto1
                                , double sconto2
                                , double sconto3
                                , double sconto4
                                , double scrap1
                                , double scrap2
                                , double importoriga
                                , double importonetto
                                , double qtaric_tr
                                , double qtaprel
                                , long   tkmaga_tr
                                , String cdunim
                                , String cdiva
                                , String cdfisc
                                                        ) throws Exception {
        
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;


        if (cdunim.equals("")) cdunim = null;

        l_query   = "";

		l_query  += " update web.carrello   \n";
		l_query  += "    set tkcarrello  = ?         \n";
		l_query  += "      , cdarti      = ?         \n";
		l_query  += "      , cdcolint    = ?         \n";
		l_query  += "      , quantita    = ?         \n";
		l_query  += "      , impuni      = ?         \n";
		l_query  += "      , impuninetto = ?         \n";
		l_query  += "      , sconto1     = ?         \n";
		l_query  += "      , sconto2     = ?         \n";
		l_query  += "      , sconto3     = ?         \n";
		l_query  += "      , sconto4     = ?         \n";
		l_query  += "      , scrap1      = ?         \n";
		l_query  += "      , scrap2      = ?         \n";
		l_query  += "      , importoriga = ?        \n";
		l_query  += "      , importonetto = ?        \n";
		l_query  += "      , profil      = ?         \n";
		l_query  += "      , dtulag      = ?         \n";
		l_query  += "      , qtaric_tr   = ?         \n";
		l_query  += "      , qtaprel     = ?         \n";
		l_query  += "      , tkmaga_tr   = ?         \n";
		l_query  += "      , cdunim      = ?         \n";
		l_query  += "      , cdiva       = ?         \n";
		l_query  += "      , cdfisc      = ?         \n";
		l_query  += "  where tkcarrellop = ?         \n";

		pstm = setQuery( l_query ) ;


        ind = 1;
        pstm.setLong  (ind, tkcarrello  ); ind += 1;
        pstm.setString(ind, cdarti      ); ind += 1;
        pstm.setString(ind, cdcolint    ); ind += 1;
        pstm.setInt   (ind, quantita    ); ind += 1;
        pstm.setDouble(ind, impuni      ); ind += 1;
        pstm.setDouble(ind, impuninetto ); ind += 1;
        pstm.setDouble(ind, sconto1     ); ind += 1;
        pstm.setDouble(ind, sconto2     ); ind += 1;
        pstm.setDouble(ind, sconto3     ); ind += 1;
        pstm.setDouble(ind, sconto4     ); ind += 1;
        pstm.setDouble(ind, scrap1      ); ind += 1;
        pstm.setDouble(ind, scrap2      ); ind += 1;
        pstm.setDouble(ind, importoriga ); ind += 1;
        pstm.setDouble(ind, importonetto); ind += 1;
        pstm.setString(ind, profil      ); ind += 1;
        pstm.setTimestamp(ind, dtulag      ); ind += 1;
        pstm.setDouble(ind, qtaric_tr   ); ind += 1;
        pstm.setDouble(ind, qtaprel     ); ind += 1;
        pstm.setLong  (ind, tkmaga_tr   ); ind += 1;
        pstm.setString(ind, cdunim      ); ind += 1;
        pstm.setString(ind, cdiva       ); ind += 1;
        pstm.setString(ind, cdfisc      ); ind += 1;
        pstm.setLong  (ind, tkcarrellop ); ind += 1;

        tot_rec = pstm.executeUpdate();


        if (quantita <= 0 ){
            ctrlCarrello( tkcarrello );
        }

        return tot_rec;


    }



    public int setDtcons( long      tkcarrello
                        , String    cdstagione
                        , int       annostg
                        , Timestamp dtcons
                                                ) throws Exception{
    
        
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;


        l_query   = "";

		l_query  += " update web.carrello   \n";
		l_query  += "    set dtcons      = ?         \n";
		l_query  += "  where tkcarrello  = ?         \n";


		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setTimestamp(ind, dtcons   ); ind += 1;
        pstm.setLong  (ind, tkcarrello  ); ind += 1;

        tot_rec = pstm.executeUpdate();


        return tot_rec;
    }



    public boolean isScontoRiga( long      tkcarrello
                                                        ) throws Exception{
    
        
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;


        l_query   = "";

		l_query  += " select count(*)                     \n";
		l_query  += "   from web.carrello                 \n";
		l_query  += "  where tkcarrello = "+tkcarrello+"  \n";
		l_query  += "    and (   sconto1 > 0  \n";
		l_query  += "        or  sconto2 > 0  \n";
		l_query  += "        or  sconto3 > 0  \n";
		l_query  += "        or  sconto4 > 0  \n";
		l_query  += "        or  scrap1  > 0  \n";
		l_query  += "        or  scrap2  > 0  \n";
		l_query  += "           )             \n";

		tot_rec = sql_int( l_query ) ;

        if (tot_rec > 0){
            return true;
        }

        return false;
    }







    public long tkcarrellop  = 0;



}
