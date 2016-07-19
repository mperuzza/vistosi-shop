package com.ateikon.function;



import java.sql.ResultSet;
import java.sql.PreparedStatement;

import com.ateikon.common.Atk_sql;
import com.ateikon.structure.Str_ep_tabset;


public class F_gesttab_azienda extends Atk_sql {

    public F_gesttab_azienda() {

        super();

        // vado nella web.keypool

        
    }



    public boolean isAziendale ( String tabella ) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        ResultSet         rs = null;
        PreparedStatement pstm = null;


        boolean lb_ = false;
        String  fazienda = "S";


        l_query   = "";
        l_query  += " SELECT pgmr.tabset.fazienda    \n";
        l_query  += "   FROM pgmr.tabset             \n";
        l_query  += "  where pgmr.tabset.tabella = ? \n";
       
        
        pstm = m_connection.prepareStatement( l_query ) ;
        
        ind = 1;
        pstm.setString(ind, tabella     );   ind += 1;
        
        rs = pstm.executeQuery();
        
        if (rs!=null && rs.next() && rs.getObject(1)!=null){
         
            fazienda = rs.getString(1);
        }
        pstm.close();

        if (fazienda.equals("S")){
            
            lb_ = false;
        }else {
            lb_ = true;
        }

        return lb_;


    }




    /***


    */

    public Str_ep_tabset getEp_tabset (String tabella ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        tabella = tabella.trim().toUpperCase();

        Str_ep_tabset lstr = new Str_ep_tabset();



        l_query  = "";
        l_query += " select fazienda       \n";
        l_query += "      , fazienda_user  \n";
        l_query += "      , fconcrete      \n";
        l_query += "   from pgmr.ep_tabset \n";
        l_query += "  where tabella  = ?   \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;
        pstm.setString(ind++, tabella);

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){
            
            if (rs.getObject("fazienda"     )!=null)  lstr.fazienda      = rs.getString("fazienda"     );
            if (rs.getObject("fazienda_user")!=null)  lstr.fazienda_user = rs.getString("fazienda_user");
            if (rs.getObject("fconcrete"    )!=null)  lstr.fconcrete     = rs.getString("fconcrete"    );

        }

        pstm.close();


        return lstr ;

    }





 



}

