package com.ateikon.function;



import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.PreparedStatement;

import com.ateikon.common.Atk_sql;

import com.ateikon.common.Cat_costanti;


public class F_getkey extends Atk_sql {

    public F_getkey() {

        super();
    }


    /*
        Dal WEB non aggiorno mai tabelle Gestionali
        quindi prelevo sempre dalla key_pools del WEB
    */


    public long getKey( String as_dipartimento
                      , String as_keyname
                                                ) throws Exception {
                                                    
        int               ind = 0;
        int               tot_rec = 0;
        ResultSet         rs = null;
        PreparedStatement pstm = null;


        Cat_costanti cat_costanti = new Cat_costanti();
        setProfilo((Atk_sql) cat_costanti); 
         
        String cat_agente_web    = cat_costanti.getCostvalue("agente_web");
        
        if (cat_agente_web.equals("")) {
            throw new Exception("Agente WEB per Keypools NON DEFINITO!");
        }


        return getKey_web(as_dipartimento, cat_agente_web, as_keyname);

    }


    /***

        Calcolo la keypool dalla webkeypool
    */



    public long getKey_web( String as_dipartimento
                          , String as_cdagen
                          , String as_keyname
                                                ) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        ResultSet         rs = null;
        PreparedStatement pstm = null;

        if (as_cdagen.equals("")){
            return -6;
        }

        if (!this.isKey_web(as_dipartimento, as_cdagen, as_keyname)) return -1;


        
        l_query   = "";
        l_query  += " UPDATE pgmr.web_keypools \n";
        l_query  += "    SET init1   = init1   \n";
        l_query  += "  WHERE keyname = ?       \n";
        l_query  += "    and cddipa  = ?       \n";
        l_query  += "    and cdagen  = ?       \n";
                                                   

        pstm = setQuery( l_query ) ;
        
        ind = 1;
        pstm.setString(ind, as_keyname     );   ind += 1;
        pstm.setString(ind, as_dipartimento);   ind += 1;
        pstm.setString(ind, as_cdagen      );   ind += 1;

        tot_rec = pstm.executeUpdate();
        
        l_query   = "";
        l_query  += " select init1             \n";
        l_query  += "      , fine1             \n";
        l_query  += "      , init2             \n";
        l_query  += "      , fine2             \n";
        l_query  += "   from pgmr.web_keypools \n";
        l_query  += "  where keyname = ?       \n";
        l_query  += "    and cddipa  = ?       \n";
        l_query  += "    and cdagen  = ?       \n";
        
        pstm = setQuery( l_query ) ;
        
        ind = 1;
        pstm.setString(ind, as_keyname);   ind += 1;
        pstm.setString(ind, as_dipartimento);   ind += 1;
        pstm.setString(ind, as_cdagen);   ind += 1;
        
        rs = pstm.executeQuery();
        
        long ll_inizio1 = 0;
        long ll_fine1   = 0;
        long ll_inizio2 = 0;
        long ll_fine2   = 0;
        
        if (rs != null && rs.next()){
        
            if (rs.getObject("init1")!=null){
                ll_inizio1 = rs.getLong("init1");
            }else {
                return -2;
            }
            if (rs.getObject("fine1")!=null){
                ll_fine1 = rs.getLong("fine1");
            }else {
                return -2;
            }
            if (rs.getObject("init2")!=null){
                ll_inizio2 = rs.getLong("init2");
            }else {
                return -2;
            }
            if (rs.getObject("fine2")!=null){
                ll_fine2 = rs.getLong("fine2");
            }else {
                return -2;
            }
        
        }
        
        long ll_key  = -1;
        long ll_key1 = ll_inizio1 + 1;
        long ll_key2 = ll_inizio2 + 1;
        
        if (ll_key1 > ll_fine1) ll_key1 = 0;
        if (ll_key2 > ll_fine2) ll_key2 = 0;
        
        
        // Per nessuna chiave disponibile ritorno un errore
        
        if (ll_key1 == 0 && ll_key2 == 0){
            return -3;
        }
        
        // Per tutte e due i valori validi cerco il valore, minore, disponibile per la chiave
        if (ll_key1 > 0 && ll_key2 > 0){
             
            if (ll_key1 < ll_key2){
                ll_key = ll_key1;
            }else {
                ll_key = ll_key2;
            }
        }
        
        // Ritorno la chiave valida se l'altra � esaurita
        if (ll_key1 > 0 && ll_key2 == 0){
            ll_key = ll_key1;
        }
        
        if (ll_key2 > 0 && ll_key1 == 0 ){
        	ll_key = ll_key2;
        }
        
        
        // Ritorno la chiave valida se l'altra � esaurita
        if (ll_key == ll_key1){
        
            l_query   = "";
            l_query  += " update pgmr.web_keypools \n";
            l_query  += "    set init1   = ?       \n";
            l_query  += "      , dtulag  = ?       \n";
            l_query  += "  where keyname = ?       \n";
            l_query  += "    and cddipa  = ?       \n";
            l_query  += "    and cdagen  = ?       \n";
        
        }else if (ll_key == ll_key2){
        
            l_query   = "";
            l_query  += " update pgmr.web_keypools \n";
            l_query  += "    set init2   = ?       \n";
            l_query  += "      , dtulag  = ?       \n";
            l_query  += "  where keyname = ?       \n";
            l_query  += "    and cddipa  = ?       \n";
            l_query  += "    and cdagen  = ?       \n";
                                                   
        }else {
            l_query = "";
            throw new Exception("Errore nella KeyPool");
        }
        
        
        // aggiorno la key-pool
        
        Timestamp oggi = new Timestamp(System.currentTimeMillis());
        
        pstm = setQuery( l_query ) ;
        
        ind = 1;
        pstm.setLong     (ind, ll_key         );   ind += 1;
        pstm.setTimestamp(ind, oggi           );   ind += 1;
        pstm.setString   (ind, as_keyname     );   ind += 1;
        pstm.setString   (ind, as_dipartimento);   ind += 1;
        pstm.setString   (ind, as_cdagen      );   ind += 1;
        
        tot_rec = pstm.executeUpdate();
        
        if (tot_rec > 0){
            m_connection.commit();
        }else {
            m_connection.rollback();
            return -5;
        }
        
        return ll_key;
    }


 
    public boolean isKey_web ( String as_dipartimento
                             , String as_cdagen
                             , String as_keyname
                                                        ) throws Exception{
 
        int               tot_rec = 0;
        ResultSet         rs = null;
        PreparedStatement pstm = null;



        l_query   = "";
        l_query  += " select count(*) as tot_rec \n";
        l_query  += "   from pgmr.web_keypools       \n";
        l_query  += "  where keyname = ?         \n";
        l_query  += "    and cddipa  = ?         \n";
        l_query  += "    and cdagen  = ?         \n";

        pstm = m_connection.prepareStatement( l_query );


        int ind = 1;
        pstm.setString(ind, as_keyname     );   ind += 1;
        pstm.setString(ind, as_dipartimento);   ind += 1;
        pstm.setString(ind, as_cdagen      );   ind += 1;

        rs = pstm.executeQuery();

        if (rs != null && rs.next() && rs.getObject("tot_rec")!=null){
 
            tot_rec = rs.getInt("tot_rec");

        }

        pstm.close();
        pstm = null;

        if (tot_rec > 0) return true;
 
 
        return false;
 
    }

    /***
        Retrieve della Keypool da pgmr.keypool
    */



    public long getKey_gest( String as_dipartimento
                           , String as_keyname
                                                ) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        ResultSet         rs = null;
        PreparedStatement pstm = null;

        // System.out.println("as_dipartimento "+as_dipartimento);
        // System.out.println("as_keyname      "+as_keyname     );

        if (!this.isKey_gest(as_dipartimento, as_keyname)) return -1;


       // Lock del Record

       l_query   = "";
       l_query  += " UPDATE pgmr.keypools   \n";
       l_query  += "    SET init1   = init1 \n";
       l_query  += "  WHERE keyname = ?     \n";
       l_query  += "    and cddipa  = ?     \n";


       pstm = setQuery( l_query ) ;

       ind = 1;
       pstm.setString(ind, as_keyname     );   ind += 1;
       pstm.setString(ind, as_dipartimento);   ind += 1;

       tot_rec = pstm.executeUpdate();

       l_query   = "";
       l_query  += " select init1         \n";
       l_query  += "      , fine1         \n";
       l_query  += "      , init2         \n";
       l_query  += "      , fine2         \n";
       l_query  += "   from pgmr.keypools \n";
       l_query  += "  where keyname = ?   \n";
       l_query  += "    and cddipa  = ?   \n";

       pstm = setQuery( l_query ) ;

       ind = 1;
       pstm.setString(ind, as_keyname);   ind += 1;
       pstm.setString(ind, as_dipartimento);   ind += 1;

       rs = pstm.executeQuery();

       long ll_inizio1 = 0;
       long ll_fine1   = 0;
       long ll_inizio2 = 0;
       long ll_fine2   = 0;

       if (rs != null && rs.next()){

           if (rs.getObject("init1")!=null){
               ll_inizio1 = rs.getLong("init1");
           }else {
               return -2;
           }
           if (rs.getObject("fine1")!=null){
               ll_fine1 = rs.getLong("fine1");
           }else {
               return -2;
           }
           if (rs.getObject("init2")!=null){
               ll_inizio2 = rs.getLong("init2");
           }else {
               return -2;
           }
           if (rs.getObject("fine2")!=null){
               ll_fine2 = rs.getLong("fine2");
           }else {
               return -2;
           }

       }

       long ll_key  = -1;
       long ll_key1 = ll_inizio1 + 1;
       long ll_key2 = ll_inizio2 + 1;

       if (ll_key1 > ll_fine1) ll_key1 = 0;
       if (ll_key2 > ll_fine2) ll_key2 = 0;


       // Per nessuna chiave disponibile ritorno un errore

       if (ll_key1 == 0 && ll_key2 == 0){
            return -3;
       }

       // Per tutte e due i valori validi cerco il valore, minore, disponibile per la chiave
       if (ll_key1 > 0 && ll_key2 > 0){
            if (ll_key1 < ll_key2){
                ll_key = ll_key1;
            }else {
                ll_key = ll_key2;
            }
       }

       // Ritorno la chiave valida se l'altra è esaurita
       if (ll_key1 > 0 && ll_key2 == 0){
           ll_key = ll_key1;
       }

       if (ll_key2 > 0 && ll_key1 == 0 ){
       	ll_key = ll_key2;
       }


       // Ritorno la chiave valida se l'altra è esaurita
       if (ll_key == ll_key1){

           l_query   = "";
           l_query  += " update pgmr.keypools \n";
           l_query  += "    set init1   = ?   \n";
           l_query  += "      , dtulag  = ?     \n";
           l_query  += "  where keyname = ?   \n";
           l_query  += "    and cddipa  = ?   \n";

       }else if (ll_key == ll_key2){

           l_query   = "";
           l_query  += " update pgmr.keypools \n";
           l_query  += "    set init2   = ?   \n";
           l_query  += "      , dtulag  = ?     \n";
           l_query  += "  where keyname = ?   \n";
           l_query  += "    and cddipa  = ?   \n";

       }else {
           l_query = "";
           throw new Exception("Errore nella KeyPool");
       }


       // aggiorno la key-pool

       Timestamp oggi = new Timestamp(System.currentTimeMillis());

       pstm = setQuery( l_query ) ;

       ind = 1;
       pstm.setLong     (ind, ll_key         );   ind += 1;
       pstm.setTimestamp(ind, oggi           );   ind += 1;
       pstm.setString   (ind, as_keyname     );   ind += 1;
       pstm.setString   (ind, as_dipartimento);   ind += 1;

       tot_rec = pstm.executeUpdate();

	   if (tot_rec > 0){
           m_connection.commit();
       }else {
           m_connection.rollback();
           return -5;
       }

       // System.err.println("Get Key: "+ll_key);
       // System.err.println("         keyname      >"+as_keyname+"<");
       // System.err.println("         dipartimento >"+as_dipartimento+"<");

       return ll_key;


    }


    public boolean isKey_gest ( String as_dipartimento
                              , String as_keyname
                                                        ) throws Exception{

        int               tot_rec = 0;
        ResultSet         rs = null;
        PreparedStatement pstm = null;



        l_query   = "";
        l_query  += " select count(*) as tot_rec \n";
        l_query  += "   from pgmr.keypools       \n";
        l_query  += "  where keyname = ?         \n";
        l_query  += "    and cddipa  = ?         \n";

        pstm = m_connection.prepareStatement( l_query );


        int ind = 1;
        pstm.setString(ind, as_keyname     );   ind += 1;
        pstm.setString(ind, as_dipartimento);   ind += 1;

        rs = pstm.executeQuery();

        if (rs != null && rs.next() && rs.getObject("tot_rec")!=null){

            tot_rec = rs.getInt("tot_rec");

        }

        pstm.close();
        pstm = null;

        if (tot_rec > 0) return true;


        return false;

    }

}
