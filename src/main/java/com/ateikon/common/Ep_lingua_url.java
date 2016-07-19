package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Ep_lingua_url extends com.ateikon.standard.Ep_lingua_url {


    public Ep_lingua_url() {

        super();
    }


    public ResultSet getAll ( String cdprogetto ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        l_query  = "";
        l_query += " select *                              \n";
        l_query += "   from pgmr.ep_lingua_url             \n";
        l_query += "  where cdprogetto = '"+cdprogetto+"' \n";
        l_query += "  order by relativepath                \n";
        l_query += "         , filename                    \n";

        rs = sql_query(l_query);

        return rs;

    }


    public int execute ( com.ateikon.structure.Rec_ep_lingua_url astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        if (astr.tklingua_url <= 0){
                
            l_query   = "";
            l_query  += " select tklingua_url       \n";
            l_query  += "   from pgmr.ep_lingua_url \n";
            l_query  += "  where cdprogetto = ?     \n";
            if (!astr.relativepath.equals("")){
                l_query  += "    and relativepath = ?   \n";
            }else {
                l_query  += "    and relativepath is null   \n";
            }
            if (!astr.filename.equals("")){
                l_query  += "    and filename = ?   \n";
            }else {
                l_query  += "    and filename is null   \n";
            }

            pstm = m_connection.prepareStatement( l_query ) ;
            
            ind = 1;
            pstm.setString (ind++, astr.cdprogetto  );
            if (!astr.relativepath.equals("")){
                pstm.setString (ind++, astr.relativepath);
            }
            if (!astr.filename.equals("")){
                pstm.setString (ind++, astr.filename    );
            }
            
            rs = pstm.executeQuery();
            
            if (rs!=null && rs.next()){
                astr.tklingua_url = rs.getLong(1);
            }            
            pstm.close();

        }

        if (astr.fgtraduci.equals("") && astr.tklingua_url > 0){
            
            l_query   = "";
            l_query  += " select fgtraduci                            \n";
            l_query  += "   from pgmr.ep_lingua_url                   \n";
            l_query  += "  where tklingua_url = "+astr.tklingua_url+" \n";
            
            astr.fgtraduci = sql_String(l_query);

            if (!astr.fgtraduci.equals("N")) astr.fgtraduci = "S";

        }

        return super.execute(astr);
    }



    /****
        getKey: ep_lingua_url
    **/ 

    public int deleteKey( long       tklingua_url
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;


        l_query   = "";
        l_query  += " delete \n";
        l_query  += "   from pgmr.ep_lingua_lb  \n";
        l_query  += "  where tklingua_url = "+tklingua_url+" \n";

        tot_rec = sql_update(l_query);


        l_query   = "";
        l_query  += " delete \n";
        l_query  += "   from pgmr.ep_lingua_url  \n";
        l_query  += "  where tklingua_url = "+tklingua_url+" \n";

        tot_rec = sql_update(l_query);


        return tot_rec;

    }


    /****
        getKey: ep_lingua_url
    **/ 

    public int setFgtraduci( long   tklingua_url
                           , String fgtraduci
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " update pgmr.ep_lingua_url  \n";
        l_query  += "    set fgtraduci = '"+fgtraduci+"' \n";
        l_query  += "      , profil = '"+profil+"' \n";
        l_query  += "      , dtulag = "+sysdate+" \n";
        l_query  += "  where tklingua_url = "+tklingua_url+" \n";

        tot_rec = sql_update(l_query);


        return tot_rec;

    }






    //avendramin20110712 - I
    /***


    */

    public ResultSet getKey ( String cdprogetto, String relativepath, String filename) throws Exception {

    	   int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " select *                  \n";
        l_query  += "   from pgmr.ep_lingua_url \n";
        l_query  += "  where cdprogetto   = ?   \n";
        if (!relativepath.equals("")){
          l_query  += "    and relativepath = ?   \n";
        } else {
          l_query  += "    and relativepath is null   \n";
        }
        if (!filename.equals("")){
          l_query  += "    and filename     = ?   \n";
        } else {
          l_query  += "    and filename is null   \n";
        }

        pstm = setQuery(l_query);

        ind = 1;
        pstm.setString(ind++, cdprogetto    );
        if (!relativepath.equals("")){
          pstm.setString(ind++, relativepath  );
        }
        if (!filename.equals("")){
          pstm.setString(ind++, filename      );
        }

        rs = pstm.executeQuery();

        return rs;

    }
    //avendramin20110712 - F


}

