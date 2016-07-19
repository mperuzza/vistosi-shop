package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class Mrp_arch_magazzi extends Atk_sql {

    public Mrp_arch_magazzi() {
        
        super();
    }




    public ResultSet getDropdown( ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += "  select tkmaga, dsmaga        \n";
        l_query  += "    from pgmr.mrp_arch_magazzi \n";
        l_query  += "   order by tkmaga             \n";
                                            

        pstm = setQuery( l_query ) ;

        ind = 1;

        rs = pstm.executeQuery();


        return rs;

    }



    public ResultSet getAll( ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += "  select *             \n";
        l_query  += "    from pgmr.mrp_arch_magazzi \n";
        l_query  += "   order by dsmaga     \n";
                                            

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
        l_query  += "   from pgmr.mrp_arch_magazzi  \n";

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        rs = pstm.executeQuery();

        if (rs!=null && rs.next() && rs.getObject(1)!=null){
            tot_rec = rs.getInt(1);
        }

        pstm.close();

        return tot_rec;

    }



    public int executeDelete( long tkmaga) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;



        l_query   = "";
        l_query  += " delete                    \n";
        l_query  += "   from pgmr.mrp_arch_magazzi \n";
        l_query  += "  where tkmaga = ?      \n";

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;

        pstm.setLong(ind, tkmaga ); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        return tot_rec;

    }






}
