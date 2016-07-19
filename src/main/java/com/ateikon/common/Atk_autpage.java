package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class Atk_autpage extends Atk_sql {

    public Atk_autpage() {
        
        super();
    }


    public ResultSet getKey(String cdvoce) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select *                \n";
		l_query  += "   from web.atk_autpage  \n";
		l_query  += "  where cdvoce  = ?      \n";

		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString(ind, cdvoce); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }

    public ResultSet getResultSet(String order_by) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        if (order_by.equals("")) order_by = "cdvoce";

        l_query   = "";
		l_query  += " select *                \n";
		l_query  += "   from web.atk_autpage  \n";
		l_query  += "  order by "+order_by+"  \n";

		pstm = setQuery( l_query ) ;

        ind = 1;

        rs = pstm.executeQuery();

        return rs;


    }



    public int executeInsert( String cdvoce
                            , String dsvoce
                            , String fgclie
                            , String fgage
                            , String fgforn
                                                    ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;

        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " insert into web.atk_autpage(  \n";
		l_query  += "             cdvoce            \n";
		l_query  += "           , dsvoce            \n";
		l_query  += "           , fgclie            \n";
		l_query  += "           , fgage             \n";
		l_query  += "           , fgforn            \n";
                                                    
		l_query  += "           , cdazie            \n";
		l_query  += "           , cddipa            \n";
		l_query  += "           , profil            \n";
		l_query  += "           , dtinse            \n";
		l_query  += "           , dtulag            \n";
		l_query  += "           )values (           \n";
		l_query  += "             ?,?,?,?,?         \n";
		l_query  += "           , ?,?,?,?,?         \n";
		l_query  += "           )                   \n";


		pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;

        pstm.setString   (ind, cdvoce  ); ind += 1;
        pstm.setString   (ind, dsvoce  ); ind += 1;
        pstm.setString   (ind, fgclie  ); ind += 1;
        pstm.setString   (ind, fgage   ); ind += 1;
        pstm.setString   (ind, fgforn  ); ind += 1;

        pstm.setString   (ind, cdazie  ); ind += 1;
        pstm.setString   (ind, cddipa  ); ind += 1;
        pstm.setString   (ind, profil  ); ind += 1;
        pstm.setTimestamp(ind, dtinse  ); ind += 1;
        pstm.setTimestamp(ind, dtulag  ); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        return tot_rec;

    }



    public int executeUpdate( String cdvoce
                            , String dsvoce
                            , String fgclie
                            , String fgage
                            , String fgforn
                                                    ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;

        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " update web.atk_autpage  \n";
		l_query  += "    set fgclie     = ?  \n";
		l_query  += "      , fgage      = ?  \n";
		l_query  += "      , fgforn     = ?  \n";
		l_query  += "      , dsvoce     = ?  \n";
		l_query  += "      , profil     = ?  \n";
		l_query  += "      , dtulag     = ?  \n";
		l_query  += "  where cdvoce     = ?  \n";


		pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;

        pstm.setString   (ind, fgclie ); ind += 1;
        pstm.setString   (ind, fgage  ); ind += 1;
        pstm.setString   (ind, fgforn ); ind += 1;
        pstm.setString   (ind, dsvoce ); ind += 1;
        pstm.setString   (ind, profil ); ind += 1;
        pstm.setTimestamp(ind, dtulag ); ind += 1;
        pstm.setString   (ind, cdvoce ); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        return tot_rec;

    }



    public int executeDelete( String cdvoce
                                                    ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;

        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " delete from web.atk_autpage where cdvoce = ?  \n";


		pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;

        pstm.setString   (ind, cdvoce  ); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        return tot_rec;

    }





}
