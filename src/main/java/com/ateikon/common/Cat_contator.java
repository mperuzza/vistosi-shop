package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Cat_contator extends com.ateikon.standard.Cat_contator {


    public Cat_contator() {

        super();
    }




    

    /***


    */

    public long getNprogr (String cdazie, String prname, String annoco ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

       
        long nprogr = 0;


        tot_rec = 0;

        l_query  = "";
        l_query += "  select nprogr                 \n";
        l_query += "    from pgmr.cat_contator      \n";
        l_query += "   where cdazie = '"+cdazie+"'  \n";
        l_query += "     and prname = '"+prname+"'  \n";
        l_query += "     and annoco = '"+annoco+"'  \n";


        pstm = m_connection.prepareStatement(l_query);

        ind = 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){
            
            tot_rec += 1;
            ind = 0;
            if (rs.getObject(++ind)!=null)  nprogr = rs.getLong(ind);
            
            pstm.close();

            nprogr += 1;

            
            l_query  = "";
            l_query += "  update pgmr.cat_contator     \n";
            l_query += "     set nprogr = ?            \n";
            l_query += "       , profil = ?            \n";
            l_query += "       , dtulag = ?            \n";
            l_query += "   where cdazie = '"+cdazie+"' \n";
            l_query += "     and prname = '"+prname+"' \n";
            l_query += "     and annoco = '"+annoco+"' \n";
           
            pstm = m_connection.prepareStatement(l_query);
    
            ind = 1;
    
            pstm.setLong      (ind++, nprogr);
            pstm.setString    (ind++, profil);
            pstm.setTimestamp (ind++, dtulag);

            tot_rec = pstm.executeUpdate();

            if (tot_rec == 1){
                m_connection.commit();
            }else {
                throw new Exception("Errore UPD cat_contator");
            }



        }else {
            pstm.close();
            
            nprogr = 10;

            com.ateikon.structure.Rec_cat_contator lstr = new com.ateikon.structure.Rec_cat_contator();
            
            this.cdazie = cdazie;

            lstr.cdazie = cdazie;
            lstr.prname = prname;
            lstr.annoco = annoco;
            lstr.nprogr = nprogr;

            tot_rec = execute(lstr);

            if (tot_rec == 1){
                m_connection.commit();
            }else {
                throw new Exception("Errore INS cat_contator");
            }

        }

        return nprogr ;

    }


}

