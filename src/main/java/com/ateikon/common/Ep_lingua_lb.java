package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Ep_lingua_lb extends com.ateikon.standard.Ep_lingua_lb {


    public Ep_lingua_lb() {

        super();
    }





    /****
        execute: ep_lingua_lb
    **/ 


    public int execute ( com.ateikon.structure.Rec_ep_lingua_lb astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        l_query   = "";
        l_query  += " select tklingua_lb        \n";
        l_query  += "   from pgmr.ep_lingua_lb  \n";
        l_query  += "  where cdling       = ?   \n";
        l_query  += "    and tklingua_url = ?   \n";
        l_query  += "    and cdcampo      = ?   \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;
        pstm.setString(ind++, astr.cdling       );
        pstm.setLong  (ind++, astr.tklingua_url );
        pstm.setString(ind++, astr.cdcampo      );

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){
            
            if (rs.getObject(1)!=null)  astr.tklingua_lb = rs.getLong(1);

        }
        pstm.close();

        return super.execute(astr);
    }




    /***


    */

    public String getDscampo (String cdling, long tklingua_url, String cdcampo ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        String ls_dscampo = "";

        l_query   = "";
        l_query  += " select dscampo            \n";
        l_query  += "   from pgmr.ep_lingua_lb  \n";
        l_query  += "  where cdling       = ?   \n";
        l_query  += "    and tklingua_url = ?   \n";
        l_query  += "    and cdcampo      = ?   \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;
        pstm.setString(ind++, cdling       );
        pstm.setLong  (ind++, tklingua_url );
        pstm.setString(ind++, cdcampo      );

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){
            
            if (rs.getObject(1)!=null)  ls_dscampo = rs.getString(1);

        }
        pstm.close();


        return ls_dscampo ;

    }



    /***

        rc = -1 NON tradotta
              1 Tradotta

    */

    public int isTranslated (String cdling, long tklingua_url, String cdcampo ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        l_query   = "";
        l_query  += " select count(1)                               \n";
        l_query  += "   from pgmr.ep_lingua_url epurl               \n";
        l_query  += "      , pgmr.ep_lingua_prg epprg               \n";
        l_query  += "  where epurl.cdprogetto = epprg.cdprogetto    \n";
        l_query  += "    and epprg.fgtraduci = 'S'                  \n";
        l_query  += "    and epurl.tklingua_url = "+tklingua_url+"  \n";

        tot_rec = sql_int(l_query);

        if (tot_rec == 0){
            return 1;
        }


        l_query   = "";
        l_query  += " select fgtraduci                       \n";
        l_query  += "   from pgmr.ep_lingua_url              \n";
        l_query  += "  where tklingua_url = "+tklingua_url+" \n";

        String fgtraduci = sql_String(l_query);

        if (fgtraduci.equals("N")){
            return 1;
        }



        l_query   = "";
        l_query  += " select cdling          \n";
        l_query  += "   from pgmr.ep_lingua  \n";
        l_query  += "  where fgdef = 'S'     \n";

        String cdling_def = sql_String(l_query);

        String ls_dscampo_def = "";

        l_query   = "";
        l_query  += " select dscampo            \n";
        l_query  += "   from pgmr.ep_lingua_lb  \n";
        l_query  += "  where cdling       = ?   \n";
        l_query  += "    and tklingua_url = ?   \n";
        l_query  += "    and cdcampo      = ?   \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;
        pstm.setString(ind++, cdling_def   );
        pstm.setLong  (ind++, tklingua_url );
        pstm.setString(ind++, cdcampo      );

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){
            
            if (rs.getObject(1)!=null)  ls_dscampo_def = rs.getString(1);

        }
        pstm.close();


        String ls_dscampo = "";
        String ls_dscampo_defling = "";

        l_query   = "";
        l_query  += " select dscampo            \n";
        l_query  += "      , dscampo_defling    \n";
        l_query  += "   from pgmr.ep_lingua_lb  \n";
        l_query  += "  where cdling       = ?   \n";
        l_query  += "    and tklingua_url = ?   \n";
        l_query  += "    and cdcampo      = ?   \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;
        pstm.setString(ind++, cdling       );
        pstm.setLong  (ind++, tklingua_url );
        pstm.setString(ind++, cdcampo      );

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){
            
            if (rs.getObject("dscampo")!=null)  ls_dscampo = rs.getString("dscampo");
            if (rs.getObject("dscampo_defling")!=null)  ls_dscampo_defling = rs.getString("dscampo_defling");

        }
        pstm.close();

        if (!ls_dscampo_defling.equals(ls_dscampo_def)){
            return -1;
        }

        return 1;

    }





    /***


    */

    public String getDscampo_defling ( String cdling, long tklingua_url, String cdcampo) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        String ls_dscampo_defling = "";

        l_query   = "";
        l_query  += " select dscampo_defling    \n";
        l_query  += "   from pgmr.ep_lingua_lb  \n";
        l_query  += "  where cdling       = ?   \n";
        l_query  += "    and tklingua_url = ?   \n";
        l_query  += "    and cdcampo      = ?   \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;
        pstm.setString(ind++, cdling       );
        pstm.setLong  (ind++, tklingua_url );
        pstm.setString(ind++, cdcampo      );

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){
            
            if (rs.getObject("dscampo_defling")!=null)  ls_dscampo_defling = rs.getString("dscampo_defling");

        }
        pstm.close();


        return ls_dscampo_defling  ;

    }

    //avendramin20110712 - I
    /***


    */

    public ResultSet getKey (String cdling, long tklingua_url, String cdcampo ) throws Exception {

       	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " select *                  \n";
        l_query  += "   from pgmr.ep_lingua_lb  \n";
        l_query  += "  where cdling       = ?   \n";
        l_query  += "    and tklingua_url = ?   \n";
        l_query  += "    and cdcampo      = ?   \n";

        pstm = setQuery(l_query);

        ind = 1;
        pstm.setString(ind++, cdling       );
        pstm.setLong  (ind++, tklingua_url );
        pstm.setString(ind++, cdcampo      );

        rs = pstm.executeQuery();

        return rs ;

    }
    //avendramin20110712 - F



}

