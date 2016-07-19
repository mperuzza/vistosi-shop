package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


/**********************/
//     NOTA:
//     modificare questa classe nel portale implica un allineamento della stessa classe in eprogen
//     e viceversa modificare questa classe nel eprogen implica un allineamento della stessa classe nel portale
//
/*********************/

public class Ep_msgmod_file extends com.ateikon.standard.Ep_msgmod_file {


    public Ep_msgmod_file() {

        super();
    }

    
    
    public ResultSet of_getFile(long tkmsgmod, String cdling) throws Exception {
        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.ep_msgmod_file  \n";
        l_query  += "  where tkmsgmod = ?  \n";
        l_query  += "    and cdling   = ?  \n";

        pstm = setQuery( l_query ) ;
        
        ind = 1;
        pstm.setLong      (ind, tkmsgmod); ind += 1;
        pstm.setString    (ind, cdling  ); ind += 1;
        
        rs = pstm.executeQuery();
        
        
        return rs;
    }

    

    public int of_delete (long tkmsgmod, String cdling) throws Exception {
        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        
        l_query   = "";
        l_query  += " delete \n";
        l_query  += "   from pgmr.ep_msgmod_file  \n";
        l_query  += "  where tkmsgmod = "+tkmsgmod+"  \n";
        l_query  += "    and cdling   = '"+cdling+"'  \n";

        tot_rec = sql_update(l_query);
        
        return 1;
    }
    
    
}

