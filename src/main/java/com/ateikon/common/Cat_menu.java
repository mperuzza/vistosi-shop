package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Cat_menu extends com.ateikon.standard.Cat_menu {


    public Cat_menu() {

        super();
    }



    public ResultSet getMenu( ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select *                 \n";
        l_query  += "   from pgmr.cat_menu     \n";
        l_query  += "  order by nrposi, tkmenu \n";

        pstm = setQuery( l_query ) ;

        ind = 1;

        rs = pstm.executeQuery();


        return rs;

    }


    public ResultSet getDropdown( ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select tkmenu        \n";
        l_query  += "      , dsmenu        \n";
        l_query  += "   from pgmr.cat_menu \n";
        l_query  += "  order by nrposi     \n";
        l_query  += "         , tkmenu     \n";

        pstm = setQuery( l_query ) ;

        ind = 1;

        rs = pstm.executeQuery();


        return rs;

    }



    public int count( ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select count(1)       \n";
        l_query  += "   from pgmr.cat_menu  \n";

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;

        rs = pstm.executeQuery();


        if (rs!=null && rs.next() && rs.getObject(1)!=null){
            tot_rec = rs.getInt(1);
        }

        pstm.close();

        return tot_rec;

    }


    public int executeDelete( long tkmenu) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;



        l_query   = "";
        l_query  += " delete                    \n";
        l_query  += "   from pgmr.cat_menu_posi \n";
        l_query  += "  where tkmenu = ?         \n";

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;

        pstm.setLong(ind, tkmenu ); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;


        l_query   = "";
        l_query  += " delete                \n";
        l_query  += "   from pgmr.cat_menu  \n";
        l_query  += " where tkmenu = ?      \n";

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;

        pstm.setLong(ind, tkmenu ); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        return tot_rec;

    }




    /****
        execute: cat_menu
    **/ 


    public int riordina ( ) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        l_query   = "";
        l_query  += " select tkmenu             \n";
        l_query  += "   from pgmr.cat_menu      \n";
        l_query  += "  order by nrposi, tkmenu  \n";
        
        pstm = m_connection.prepareStatement( l_query ) ;

        pstm = setQuery( l_query ) ;

        ind = 1;

        rs = pstm.executeQuery();

        long nrposi = 0;

        while (rs!=null && rs.next()){
            
            long tkmenu = rs.getLong(1);
            nrposi += 10;

            l_query   = "";
            l_query  += " update pgmr.cat_menu           \n";
            l_query  += "    set nrposi = "+nrposi+"    \n";
            l_query  += "  where tkmenu  = "+tkmenu +"  \n";

            int tot_rec_pos = sql_update(l_query);

        }


        return 1;
    }






}

