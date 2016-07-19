    /***
 
 
        NOTA: getTabkey
              nel programma originale in Power Builder il programma
              testa anche se il campo � un tipo valido;

              Sarebbe possibile farlo anche qui: occorre passare
              la query ...
 

    */


package com.ateikon.function;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import com.ateikon.common.Atk_sql;


public class F_tabkey extends Atk_sql {

    public F_tabkey() {

        super();

    }



    public long getTabkey( String as_tabella ) throws Exception{

 


        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        String cdprogr = "";

        as_tabella = as_tabella.toUpperCase();
        as_tabella = as_tabella.trim();

        l_query   = "";
        l_query  += " select *                  \n";
        l_query  += "   from pgmr.web_tabset    \n";
        l_query  += "  where tabella = ?        \n";

        pstm = m_connection.prepareStatement( l_query );

        ind = 1;
        pstm.setString(ind, as_tabella);   ind += 1;

        rs = pstm.executeQuery();


        if (rs !=null && rs.next()){
            if (rs.getObject("cdprogr")!=null) cdprogr = rs.getString("cdprogr");
            pstm.close();
            pstm = null;
        }else {
 
            pstm.close();
            pstm = null;

            throw new Exception("Tabella "+as_tabella+" NON Definita");
        }

            
        F_getkey f_getkey = new F_getkey();

        setProfilo((Atk_sql) f_getkey); 

        long ll_key = f_getkey.getKey(cddipa, cdprogr.toUpperCase());

        f_getkey.close();


        return ll_key;

    }

    public long getTabkey_gest( String as_tabella ) throws Exception{




        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        String cdprogr = "";

        as_tabella = as_tabella.toUpperCase();
        as_tabella = as_tabella.trim();

        l_query   = "";
        l_query  += " select *                  \n";
        l_query  += "   from pgmr.tabset    \n";
        l_query  += "  where tabella = ?        \n";

        pstm = m_connection.prepareStatement( l_query );

        ind = 1;
        pstm.setString(ind, as_tabella);   ind += 1;

        rs = pstm.executeQuery();


        if (rs !=null && rs.next()){
            if (rs.getObject("cdprogr")!=null) cdprogr = rs.getString("cdprogr");
            pstm.close();
            pstm = null;
        }else {

            pstm.close();
            pstm = null;

            throw new Exception("Tabella "+as_tabella+" NON Definita");
        }


        F_getkey f_getkey = new F_getkey();

        setProfilo((Atk_sql) f_getkey);

        long ll_key = f_getkey.getKey_gest(cddipa, cdprogr.toUpperCase());

        f_getkey.close();


        return ll_key;

    }



    // Properties



}
