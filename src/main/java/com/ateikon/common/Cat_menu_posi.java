package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Cat_menu_posi extends com.ateikon.standard.Cat_menu_posi {


    public Cat_menu_posi() {

        super();
    }



    public ResultSet getMenu( long tkmenu ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select *                      \n";
        l_query  += "   from pgmr.cat_menu_posi     \n";
        l_query  += "  where tkmenu = ?             \n";
        l_query  += "  order by nrposi, tkmenu_posi \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setLong(ind, tkmenu); ind += 1;

        rs = pstm.executeQuery();


        return rs;

    }

    public ResultSet getMenu_abil( ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += "  select b.dsmenu_posi           \n";
        l_query  += "       , b.link_menu             \n";
        l_query  += "       , b.tkfunzione            \n";
        l_query  += "       , b.nrposi as nrposi_posi \n";
        l_query  += "       , a.tkmenu                \n";
        l_query  += "       , a.cdmenu_m              \n";
        l_query  += "       , a.dsmenu                \n";
        l_query  += "       , a.nrposi as nrposi_test \n";
        l_query  += "    from pgmr.cat_menu       a    \n";
        l_query  += "       , pgmr.cat_menu_posi  b    \n";
        l_query  += "   where a.tkmenu = b.tkmenu     \n";
        l_query  += "     and a.fgabil = 'S'          \n";
        l_query  += "   order by nrposi_test          \n";
        l_query  += "          , a.tkmenu             \n";
        l_query  += "          , nrposi_posi          \n";
        l_query  += "          , b.tkmenu_posi        \n";


        pstm = setQuery( l_query ) ;

        ind = 1;

        rs = pstm.executeQuery();


        return rs;

    }




    public int count( long tkmenu) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select count(1)             \n";
        l_query  += "   from pgmr.cat_menu_posi    \n";
        l_query  += "   where tkmenu = ?          \n";

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setLong(ind, tkmenu); ind += 1;

        rs = pstm.executeQuery();


        if (rs!=null && rs.next() && rs.getObject(1)!=null){
            tot_rec = rs.getInt(1);
        }

        pstm.close();

        return tot_rec;

    }


    public int executeDelete( long tkmenu_posi) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;



        l_query   = "";
        l_query  += " delete                      \n";
        l_query  += "   from pgmr.cat_menu_posi    \n";
        l_query  += "  where tkmenu_posi = ?      \n";

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;

        pstm.setLong(ind, tkmenu_posi ); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        return tot_rec;

    }






    /****
        execute: cat_menu
    **/ 


    public int riordina (long tkmenu ) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        l_query   = "";
        l_query  += " select tkmenu_posi         \n";
        l_query  += "   from pgmr.cat_menu_posi   \n";
        l_query  += "  where tkmenu = "+tkmenu+" \n";
        l_query  += "  order by nrposi           \n";
        l_query  += "         , tkmenu_posi      \n";
        
        pstm = m_connection.prepareStatement( l_query ) ;

        pstm = setQuery( l_query ) ;

        ind = 1;

        rs = pstm.executeQuery();

        long nrposi = 0;

        while (rs!=null && rs.next()){
            
            long tkmenu_posi = rs.getLong(1);
            nrposi += 10;

            l_query   = "";
            l_query  += " update pgmr.cat_menu_posi                \n";
            l_query  += "    set nrposi = "+nrposi+"              \n";
            l_query  += "  where tkmenu_posi  = "+tkmenu_posi +"  \n";

            int tot_rec_pos = sql_update(l_query);

        }


        return 1;
    }





}

