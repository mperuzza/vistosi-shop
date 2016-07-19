package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import com.ateikon.function.*;

public class Carrello_test extends Atk_sql {

    public Carrello_test() {
        
        super();
    }


    public ResultSet getCarrello_test(String cdutente, String tkclie) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select *                          \n";
		l_query  += "   from web.carrello_test \n";
		l_query  += "  where cdutente = ?               \n";
		l_query  += "    and tkclie   = ?               \n";
		l_query  += "  order by tkcarrello              \n";

		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString(ind, cdutente); ind += 1;
        pstm.setString(ind, tkclie); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }






    public double getQuantita(String cdutente, String tkclie, String cdarti) throws Exception {
        
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        double            totqta = 0;

        l_query   = "";
		l_query  += " select sum(p.quantita) as totqta                    \n";
		l_query  += "   from web.carrello_test t, \n";
		l_query  += "        web.carrello p \n";
		l_query  += "  where t.tkcarrello = p.tkcarrello \n";
		l_query  += "    and t.cdutente = ?          \n";
		l_query  += "    and t.tkclie = ?          \n";
		l_query  += "    and p.cdarti = ?          \n";

		pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setString(ind, cdutente);  ind += 1;
        pstm.setString(ind, tkclie);    ind += 1;
        pstm.setString(ind, cdarti);    ind += 1;

        rs = pstm.executeQuery();

        if (rs != null && rs.next() && rs.getObject("totqta") != null) {
            totqta = rs.getDouble("totqta");
        }
        
        pstm.close();
        
        pstm=null;
        
        return totqta;


    }


    public int countArticoli(String cdutente, String tkclie) throws Exception {
        
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;
        
        double            totqta = 0;
        
        l_query   = "";
		l_query  += " select count(p.cdarti) as tot_rec   \n";
		l_query  += "   from web.carrello_test t,         \n";
		l_query  += "        web.carrello p               \n";
		l_query  += "  where t.tkcarrello = p.tkcarrello  \n";
		l_query  += "    and t.cdutente = ?               \n";
		l_query  += "    and t.tkclie = ?                 \n";

		pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setString(ind, cdutente);  ind += 1;
        pstm.setString(ind, tkclie);    ind += 1;

        rs = pstm.executeQuery();

        if (rs != null && rs.next() && rs.getObject("tot_rec") != null) {
            tot_rec = rs.getInt("tot_rec");
        }
        
        pstm.close();
        
        pstm = null;
        
        return tot_rec;


    }



    public String getCliente(String cdutente) throws Exception {
        
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        String            tkclie = "";

        l_query   = "";
		l_query  += " select tkclie as tkclie                    \n";
		l_query  += "   from web.carrello_test t, \n";
		l_query  += "  where t.cdutente = ?          \n";

		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString(ind, cdutente);  ind += 1;

        rs = pstm.executeQuery();

        if (rs != null && rs.next() && rs.getObject("tkclie") != null) {
            tkclie = rs.getString("tkclie");
        }
        
        return tkclie;


    }
    
    public long      setCarrello( String cdutente
                                , String tkclie
                                , String cdstato
                                , String cdtorve_m
                                , double imppag         // Caparra
                                , String cdente
                                , double scocas
                                , String ivato
                                                        ) throws Exception {
        
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        this.tkcarrello = 0;


        F_tabkey f_tabkey = new F_tabkey();
        setProfilo((Atk_sql) f_tabkey );

        Atk_pargen atk_pargen = new Atk_pargen();
        setProfilo((Atk_sql) atk_pargen);

        long      tkcarrello = f_tabkey.getTabkey( "carrello_test" );

        this.tkcarrello = tkcarrello;

        if (this.tkcarrello <= 0 ){
        
            System.err.println(f_tabkey.l_query);

            throw new Exception("Token Carrello Test Errata "+this.tkcarrello);
        }



        Timestamp dtcarrello = oggi;

        if (cdstato.equals("")){
            cdstato  = atk_pargen.getCostvalue("K_STATO_WEB_CAR");
        }
        if (cdtorve_m.equals("")){
            cdtorve_m = atk_pargen.getCostvalue("K_CDTORVE_WEB");
        }


        l_query   = "";

		l_query  += " insert into web.carrello_test ( \n";
		l_query  += "             tkcarrello              \n";
		l_query  += "           , cdutente                \n";
		l_query  += "           , tkclie                  \n";
		l_query  += "           , dtcarrello              \n";
		l_query  += "           , cdstato                 \n";
		l_query  += "           , cdazie                  \n";
		l_query  += "           , cddipa                  \n";
		l_query  += "           , profil                  \n";
		l_query  += "           , dtinse                  \n";
		l_query  += "           , dtulag                  \n";
		l_query  += "           , cdtorve_m               \n";
		l_query  += "           , imppag                  \n";
		l_query  += "           , cdente                  \n";
		l_query  += "           , scocas                  \n";
		l_query  += "           , ivato                   \n";
		l_query  += "           )values (                 \n";
		l_query  += "             ?,?,?,?,?               \n";
		l_query  += "           , ?,?,?,?,?               \n";
		l_query  += "           , ?,?,?,?,?               \n";
		l_query  += "           )                         \n";

		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setLong     (ind, tkcarrello ); ind += 1;
        pstm.setString   (ind, cdutente   ); ind += 1;
        pstm.setString   (ind, tkclie     ); ind += 1;
        pstm.setTimestamp(ind, dtcarrello ); ind += 1;
        pstm.setString   (ind, cdstato    ); ind += 1;
        pstm.setString   (ind, cdazie     ); ind += 1;
        pstm.setString   (ind, cddipa     ); ind += 1;
        pstm.setString   (ind, profil     ); ind += 1;
        pstm.setTimestamp(ind, dtinse     ); ind += 1;
        pstm.setTimestamp(ind, dtulag     ); ind += 1;
        pstm.setString   (ind, cdtorve_m  ); ind += 1;
        pstm.setDouble   (ind, imppag     ); ind += 1;
        pstm.setString   (ind, cdente     ); ind += 1;
        pstm.setDouble   (ind, scocas     ); ind += 1;
        pstm.setString   (ind, ivato      ); ind += 1;
                         
        tot_rec = pstm.executeUpdate();

        if (tot_rec <= 0){
            tkcarrello = 0;
        }

        return tkcarrello;


    }



    public int executeDelete  ( long tkcarrello ) throws Exception {
        
        
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;


        l_query   = "";
		l_query  += " delete from web.carrello_iva where tkcarrello = ? \n";

		pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setLong  (ind, tkcarrello ); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        
        
        l_query   = "";
		l_query  += " delete from web.carrello where tkcarrello = ? \n";

		pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setLong  (ind, tkcarrello ); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();



        l_query   = "";
		l_query  += " delete from web.carrello_test where tkcarrello = ? \n";

		pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        tot_rec = 0;

        pstm.setLong  (ind, tkcarrello ); ind += 1;

        tot_rec = pstm.executeUpdate();

        if (tot_rec > 0){
            pstm.close();
            return tot_rec;
        }

        pstm.close();

        return 0;


    }







    /***


    */

    public int setPromozione (long tkcarrello, String cdpromo ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        cdpromo = cdpromo.toUpperCase();

        cdpromo = cdpromo.trim().toUpperCase();

        if (cdpromo.equals("")){
            
            tot_rec = 0;

            l_query  = "";
            l_query += " update web.carrello_test \n";
            l_query += "    set cdpromo = null    \n";
            l_query += "      , profil  = ?       \n";
            l_query += "      , dtulag  = ?       \n";
            l_query += "  where tkcarrello = ?    \n";

            pstm = m_connection.prepareStatement(l_query);

            ind = 1;

            pstm.setString   (ind, profil    ); ind += 1;
            pstm.setTimestamp(ind, oggi      ); ind += 1;
            pstm.setLong     (ind, tkcarrello); ind += 1;

            tot_rec = pstm.executeUpdate();

            pstm.close();

        }else {
            
            // ricavo la partita iva del carrello corrente

            if (cdpromo.equals("PER2CA")
            ||  cdpromo.equals("PER2FA")
            ||  cdpromo.equals("PER2MA")
                ){
            


            }else {
                // Promozione NON Valida
                return -2;
            }



            String pariva = "";

            tot_rec = 0;

            l_query  = "";
            l_query += " select ente.pariva                 \n";
            l_query += "   from pgmr.archclie clie          \n";
            l_query += "      ,  pgmr.archenti ente         \n";
            l_query += "      , web.carrello_test test      \n";
            l_query += "  where ente.cdente = clie.cdente   \n";
            l_query += "    and test.tkclie = clie.tkclie   \n";
            l_query += "    and test.tkcarrello = ?         \n";

            pstm = m_connection.prepareStatement(l_query);

            ind = 1;

            pstm.setLong (ind, tkcarrello); ind += 1;

            rs = pstm.executeQuery();

            if (rs!=null && rs.next()){
                
                if (rs.getObject("pariva")!=null)  pariva = rs.getString("pariva");
            }
            pstm.close();

            
            // Trovo gli ordini con queta promozione già attiva
            
            int  tot_ordi  = 0;

            l_query  = "";
            l_query += " select count(ordt.tkordi)                             \n";
            l_query += "   from web.ord_test  ordt                             \n";
            l_query += "      , pgmr.archclie clie                             \n";
            l_query += "      , pgmr.archenti ente                             \n";
            l_query += "  where ordt.cdpromo in ('PER2CA', 'PER2FA', 'PER2MA') \n";
            l_query += "    and ordt.tkclie  = clie.tkclie                     \n";
            l_query += "    and clie.cdente  = ente.cdente                     \n";
            l_query += "    and ente.pariva  = ?                               \n";

            pstm = m_connection.prepareStatement(l_query);

            ind = 1;

            // pstm.setString   (ind, cdpromo); ind += 1;
            pstm.setString   (ind, pariva ); ind += 1;

            rs = pstm.executeQuery();

            if (rs!=null && rs.next()){
                
                if (rs.getObject(1)!=null)  tot_ordi = rs.getInt(1);
            }
            pstm.close();

            if (tot_ordi != 0){
               
                // Promozione già utilizzata
                return -1;
            }

            tot_rec = 0;

            l_query  = "";
            l_query += " update web.carrello_test \n";
            l_query += "    set cdpromo = ?       \n";
            l_query += "      , profil  = ?       \n";
            l_query += "      , dtulag  = ?       \n";
            l_query += "  where tkcarrello = ?    \n";

            pstm = m_connection.prepareStatement(l_query);

            ind = 1;
            pstm.setString   (ind, cdpromo   ); ind += 1;
            pstm.setString   (ind, profil    ); ind += 1;
            pstm.setTimestamp(ind, oggi      ); ind += 1;
            pstm.setLong     (ind, tkcarrello); ind += 1;

            tot_rec = pstm.executeUpdate();

            pstm.close();


        }   // FINE if cdpromo = ""


        return tot_rec;

    }




    public long tkcarrello = 0;

}
