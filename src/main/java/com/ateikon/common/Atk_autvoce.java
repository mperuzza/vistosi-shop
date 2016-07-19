package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class Atk_autvoce extends Atk_sql {

    public Atk_autvoce() {
        
        super();
    }


    public ResultSet getKey(String cdvoce, String cdutente) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select *                \n";
		l_query  += "   from web.atk_autvoce  \n";
		l_query  += "  where cdutente  = ?    \n";
		l_query  += "    and cdvoce  = ?      \n";

		pstm = setQuery( l_query ) ;

        ind = 1;

        pstm.setString(ind, cdutente); ind += 1;
        pstm.setString(ind, cdvoce); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }



    public int executeInsert(String cdvoce, String cdutente, String fgabilitato) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " insert into web.atk_autvoce(  \n";
		l_query  += "             cdvoce            \n";
		l_query  += "           , cdutente          \n";
		l_query  += "           , fgabilitato       \n";
		l_query  += "           , cdazie            \n";
		l_query  += "           , cddipa            \n";
		l_query  += "           , profil            \n";
		l_query  += "           , dtinse            \n";
		l_query  += "           , dtulag            \n";
		l_query  += "           )values (           \n";
		l_query  += "             ?,?,?,?,?         \n";
		l_query  += "           , ?,?,?             \n";
		l_query  += "           )                   \n";


		pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;

        pstm.setString   (ind, cdvoce  ); ind += 1;
        pstm.setString   (ind, cdutente); ind += 1;
        pstm.setString   (ind, fgabilitato); ind += 1;
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



    public int executeUpdate(String cdvoce, String cdutente, String fgabilitato) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;

        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " update web.atk_autvoce  \n";
		l_query  += "    set fgabilitato = ?  \n";
		l_query  += "      , profil      = ?  \n";
		l_query  += "      , dtulag      = ?  \n";
		l_query  += "  where cdvoce      = ?  \n";
		l_query  += "    and cdutente    = ?  \n";


		pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;

        pstm.setString   (ind, fgabilitato  ); ind += 1;
        pstm.setString   (ind, profil  ); ind += 1;
        pstm.setTimestamp(ind, dtulag  ); ind += 1;
        pstm.setString   (ind, cdvoce  ); ind += 1;
        pstm.setString   (ind, cdutente); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        return tot_rec;

    }

    public int setFgabilitato(String cdutente, String fgabilitato) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;

        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " update web.atk_autvoce  \n";
		l_query  += "    set fgabilitato = ?  \n";
		l_query  += "      , profil      = ?  \n";
		l_query  += "      , dtulag      = ?  \n";
		l_query  += "  where cdutente    = ?  \n";


		pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;

        pstm.setString   (ind, fgabilitato  ); ind += 1;
        pstm.setString   (ind, profil  ); ind += 1;
        pstm.setTimestamp(ind, dtulag  ); ind += 1;
        pstm.setString   (ind, cdutente); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        return tot_rec;

    }

}
