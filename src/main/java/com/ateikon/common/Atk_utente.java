package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;

import com.ateikon.function.*;

public class Atk_utente extends Atk_sql {

    public Atk_utente() {
        
        super();
    }





    public ResultSet getKey(String cdutente) throws Exception {
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
		l_query  += " select *               \n";
		l_query  += "   from pgmr.atk_utente \n";
		l_query  += "  where cdutente  = ?   \n";

		pstm = setQuery( l_query ) ;

        ind = 1;

        pstm.setString(ind, cdutente); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }

    public ResultSet getKey_m(String cdutente_m) throws Exception {
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
		l_query  += " select *               \n";
		l_query  += "   from pgmr.atk_utente \n";
		l_query  += "  where cdutente_m  = ?   \n";

		pstm = setQuery( l_query ) ;

        ind = 1;

        pstm.setString(ind, cdutente_m); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }



    public int executeInsert( String cdutente 
                            , String cdutente_m
                            , String dsutente
                                                ) throws Exception {
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        F_tabkey f_tabkey = new F_tabkey();
        setProfilo((Atk_sql) f_tabkey );

        long  tkutente = f_tabkey.getTabkey( "atk_utente" );

        if (tkutente<=0){
            throw new Exception ("Error Keypool: "+tkutente);
        }

        this.cdutente = ""+tkutente ;

        for (int i=this.cdutente.length(); i<10; i++){
            
            this.cdutente = "0"+this.cdutente;
        }

        cdutente = this.cdutente;


        l_query   = "";
		l_query  += " insert into pgmr.atk_utente(  \n";
		l_query  += "             cdutente          \n";
		l_query  += "           , cdutente_m        \n";
		l_query  += "           , dsutente          \n";
		l_query  += "           , cdazie            \n";
		l_query  += "           , cddipa            \n";
		l_query  += "           , profil            \n";
		l_query  += "           , dtinse            \n";
		l_query  += "           , dtulag            \n";
        
		l_query  += "           )values (           \n";
		l_query  += "             ?,?,?,?,?         \n";
		l_query  += "           , ?,?,?             \n";
		l_query  += "           )                   \n";


		pstm = m_connection.prepareStatement( l_query ) ;


        if (dsutente.equals(""))  dsutente = null;
        
        ind = 1;

        pstm.setString   (ind, cdutente   ); ind += 1;
        pstm.setString   (ind, cdutente_m ); ind += 1;
        pstm.setString   (ind, dsutente ); ind += 1;

        pstm.setString   (ind, cdazie  ); ind += 1;
        pstm.setString   (ind, cddipa  ); ind += 1;
        pstm.setString   (ind, profil  ); ind += 1;
        pstm.setTimestamp(ind, dtinse  ); ind += 1;
        pstm.setTimestamp(ind, dtulag  ); ind += 1;
        

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        return tot_rec;

    }



    public int executeUpdate( String cdutente 
                            , String cdutente_m     
                            , String dsutente
                                            ) throws Exception {
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        String cdcapo = null;


        l_query   = "";
		l_query  += " update pgmr.atk_utente \n";
		l_query  += "    set cdutente_m = ?  \n";
		l_query  += "      , dsutente   = ?  \n";
		l_query  += "      , profil     = ?  \n";
		l_query  += "      , dtulag     = ?  \n";
		l_query  += "  where cdutente   = ?  \n";


		pstm = m_connection.prepareStatement( l_query ) ;


        if (dsutente.equals(""))  dsutente = null;

        
        ind = 1;

        pstm.setString   (ind, cdutente_m); ind += 1;
        pstm.setString   (ind, dsutente  ); ind += 1;

        pstm.setString   (ind, profil    ); ind += 1;
        pstm.setTimestamp(ind, dtulag    ); ind += 1;
        
        pstm.setString   (ind, cdutente ); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        return tot_rec;


    }



    public int executeDelete( String cdutente 
                                                ) throws Exception {
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;




        l_query   = "";
		l_query  += " delete from pgmr.atk_utente  \n";
		l_query  += "  where cdutente = ?          \n";

		pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setString   (ind, cdutente ); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        return tot_rec;


    }





    /***


    */

    public ResultSet search ( String  cdutente_m
                            , String  dsutente
                            , String  cdazie
                            , String  cddipa
                            , String  cerca
                            , boolean is_count 
                            , String  order_by
                                                   ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        cdutente_m = par_like(cdutente_m  );
        dsutente   = par_like(dsutente  );
        cerca    = par_like(cerca   );

        if (order_by.equals("")) order_by = "uten.dsutente, uten.cdutente_m";

        if (is_count) order_by = "";

        if (is_count){
            
            l_query   = "";
            l_query  += "   select count(1)  \n";
        }else {
            
            l_query   = "";
            l_query  += "   select uten.cdutente     \n";
            l_query  += "        , uten.cdutente_m   \n";
            l_query  += "        , uten.dsutente     \n";
            l_query  += "        , uten.livello      \n";
        }


  
        l_query  += "     from pgmr.atk_utente     uten    \n";
        l_query  += "    where uten.cdazie = '"+cdazie+"'  \n";
        l_query  += "      and uten.cddipa = '"+cddipa+"'  \n";

        if (!cdutente_m.equals("")){
            
            l_query  += "  and uten.cdutente_m like '"+cdutente_m+"'  \n";
        }
        if (!dsutente.equals("")){
            l_query  += "  and upper(uten.dsutente) like '"+dsutente+"'  \n";
        }


        if (!cerca.equals("")){
            l_query  += "  and (  uten.cdutente_m  like '"+cerca+"'  \n";
            l_query  += "      or upper(uten.dsutente) like '"+cerca+"'  \n";
            l_query  += "               )  \n";
        }

        if (!order_by.equals("")){
            l_query  += "   order by "+order_by+"  \n";
        }


        rs = sql_query_ric(l_query);


        return rs ;

    }






    public String cdutente = "";


}
