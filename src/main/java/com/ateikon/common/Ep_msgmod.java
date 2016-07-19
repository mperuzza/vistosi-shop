package com.ateikon.common;

import com.ateikon.structure.Rec_ep_msgmod;
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

public class Ep_msgmod extends com.ateikon.standard.Ep_msgmod {


    public Ep_msgmod() {

        super();
    }
    
    
    
    public ResultSet of_getModelli () throws Exception {
        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        l_query  = "";
        l_query += " select a.tkmsgmod                         \n";
        l_query += "      , b.cdmsgmod_appl                    \n";
        l_query += "      , b.dsmsgmod_appl                    \n";
        l_query += "      , c.dsmsgmod_var                     \n";
        l_query += "   from pgmr.ep_msgmod a                   \n";
        l_query += "      , pgmr.ep_msgmod_appl b              \n";
        l_query += "      , pgmr.ep_msgmod_var c               \n";
        l_query += "  where a.cdmsgmod_appl = b.cdmsgmod_appl  \n";
        l_query += "    and a.cdmsgmod_var = c.cdmsgmod_var    \n";
        l_query += "  order by b.nrposi                        \n";
        l_query += "         , c.nrposi                        \n";
        
        rs = sql_query(l_query);
        
        return rs;
        
    }

    
    public String  of_getDescr (long tkmsgmod) throws Exception {
        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;
 
        String dsmsgmod_appl = "";
        String dsmsgmod_var = "";
       
        l_query  = "";
        l_query += " select a.tkmsgmod                         \n";
        l_query += "      , b.dsmsgmod_appl                    \n";
        l_query += "      , c.dsmsgmod_var                     \n";
        l_query += "   from pgmr.ep_msgmod a                   \n";
        l_query += "      , pgmr.ep_msgmod_appl b              \n";
        l_query += "      , pgmr.ep_msgmod_var c               \n";
        l_query += "  where a.cdmsgmod_appl = b.cdmsgmod_appl  \n";
        l_query += "    and a.cdmsgmod_var = c.cdmsgmod_var    \n";
        l_query += "    and a.tkmsgmod = "+tkmsgmod+"          \n";
        
        pstm = m_connection.prepareStatement(l_query);
        
        rs = pstm.executeQuery();
        
        if (rs!=null && rs.next()){
            
            if (rs.getObject("dsmsgmod_appl") != null)  dsmsgmod_appl = rs.getString("dsmsgmod_appl");
            if (rs.getObject("dsmsgmod_var") != null)  dsmsgmod_var = rs.getString("dsmsgmod_var");
            
        }
        
        pstm.close();
        
        return dsmsgmod_appl+" / "+dsmsgmod_var;
        
    }
    
    
    
    @Override
    public int execute(Rec_ep_msgmod astr) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        l_query   = "";
        l_query  += " select tkmsgmod\n";
        l_query  += "   from pgmr.ep_msgmod  \n";
        l_query  += "  where cdmsgmod_appl = '"+astr.cdmsgmod_appl+"' \n";
        l_query  += "    and cdmsgmod_var = '"+astr.cdmsgmod_var+"' \n";

        astr.tkmsgmod = sql_long(l_query);
        
        
        return super.execute(astr);
    }
    
    
    
    
    
    
    


}

