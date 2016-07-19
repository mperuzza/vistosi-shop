package com.ateikon.common;

import com.ateikon.structure.Rec_ep_msgmod_par;
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

public class Ep_msgmod_par extends com.ateikon.standard.Ep_msgmod_par {


    public Ep_msgmod_par() {

        super();
    }

    @Override
    public int execute(Rec_ep_msgmod_par astr) throws Exception {

        l_query   = "";
        l_query  += " select tkmsgmod_par                \n";
        l_query  += "   from pgmr.ep_msgmod_par          \n";
        l_query  += "  where cdmsgmod_par = '"+astr.cdmsgmod_par+"'   \n";
        l_query  += "    and cdmsgmod_appl = '"+astr.cdmsgmod_appl+"' \n";
        
        astr.tkmsgmod_par = sql_long(l_query);
        
        return super.execute(astr);
    }

    
    /**

    Dato il modello restituisce i parametri
    
    @param tkmsgmod
    @return
    @throws Exception 
    */
    public ResultSet of_getParametri (long tkmsgmod) throws Exception {
      int ind = 0;
      int tot_rec = 0;
      PreparedStatement pstm = null;
      ResultSet rs = null;
      
      String  l_query = "";
      
      
      l_query  = "";
      l_query  = "     select mpar.tkmsgmod_par                         \n";
      l_query += "          , mpar.cdmsgmod_par                         \n";
      l_query += "          , mpar.dsmsgmod_par                         \n";
      l_query += "          , mpar.categoria_par                        \n";
      l_query += "       from pgmr.ep_msgmod_par   mpar                 \n";
      l_query += "          , pgmr.ep_msgmod       mmod                 \n";
      l_query += "      where mpar.cdmsgmod_appl = mmod.cdmsgmod_appl   \n";
      l_query += "        and mmod.tkmsgmod      = ?                    \n";
      l_query += "   order by mpar.categoria_par, mpar.cdmsgmod_par     \n";

      pstm = setQuery(l_query);
      
      ind = 1;
      pstm.setLong(ind++, tkmsgmod);
      
      rs = pstm.executeQuery();

      if (rs==null) System.out.println("E' nullo");
      
      return rs;
    }
    
    

}

