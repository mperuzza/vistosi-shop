package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;
import com.ateikon.structure.Rec_web_import_ordven;


public class Web_import_ordven extends com.ateikon.standard.Web_import_ordven {


    public Web_import_ordven() {

        super();
    }




    /****
        getPos_ordine: 
    **/ 

    public long getPos_ordine( String rif_ord
                             , String rif_riga_ord
                             , Rec_web_import_ordven astr
                                                    ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        long ll_ = 0;

        l_query   = "";
        l_query  += " select tkimp                   \n";
        l_query  += "      , tkposi                  \n";
        l_query  += "      , cdarti                  \n";
        l_query  += "   from pgmr.web_import_ordven  \n";
        l_query  += "  where rif_ord = ?             \n";
        l_query  += "    and rif_riga_ord = ?        \n";

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setString(ind, rif_ord); ind += 1;
        pstm.setString(ind, rif_riga_ord); ind += 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next() ){
           
            ll_ = rs.getLong("tkimp");

            astr.tkimp = rs.getLong("tkimp");;
            if (rs.getObject("tkposi")!=null) astr.tkposi = rs.getLong("tkposi");
            if (rs.getObject("cdarti")!=null) astr.cdarti = rs.getString("cdarti");

        }

        pstm.close();

        return ll_;

    }




    /****
        getPos_ordine: 
    **/ 

    public ResultSet getImported( String cdutente
                                                    ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        long ll_ = 0;

        l_query   = "";
        l_query  += " select *                       \n";
        l_query  += "   from pgmr.web_import_ordven  \n";
        l_query  += "  where cdutente = ?            \n";
        l_query  += "    and tkposi is null          \n";
        l_query  += "   order by rif_ord             \n";
        l_query  += "          , rif_riga_ord        \n";
        l_query  += "          , tkimp               \n";
        
        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString(ind, cdutente); ind += 1;

        rs = pstm.executeQuery();



        return rs;

    }


    /****
        getPos_ordine: 
    **/ 

    public ResultSet getOrdinate( String cdutente
                                                    ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        long ll_ = 0;

        l_query   = "";
        l_query  += " select *                       \n";
        l_query  += "   from pgmr.web_import_ordven  \n";
        l_query  += "  where cdutente = ?            \n";
        l_query  += "    and fgordina = 'S'          \n";
        l_query  += "    and tkposi is null          \n";
        l_query  += "  order by rif_ord              \n";
        l_query  += "         , rif_riga_ord         \n";
        l_query  += "         , tkimp                \n";
        
        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString(ind, cdutente); ind += 1;

        rs = pstm.executeQuery();



        return rs;

    }








    /****
        getPos_ordine: 
    **/ 

    public int setCdarti( long tkimp
                             , String cdarti
                                                    ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        long ll_ = 0;

        l_query   = "";
        l_query  += " update pgmr.web_import_ordven  \n";
        l_query  += "    set cdarti = ?              \n";
        l_query  += "  where tkimp  = ?              \n";

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setString(ind, cdarti); ind += 1;
        pstm.setLong  (ind, tkimp); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();

        return tot_rec;

    }




    /****
        getPos_ordine: 
    **/ 

    public int setFgordina( long tkimp
                                                    ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        long ll_ = 0;

        l_query   = "";
        l_query  += " update pgmr.web_import_ordven  \n";
        l_query  += "    set fgordina = ?              \n";
        l_query  += "  where tkimp  = ?              \n";

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setString(ind, "S"); ind += 1;
        pstm.setLong  (ind, tkimp); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();

        return tot_rec;

    }

    /****
        getPos_ordine: 
    **/ 

    public int resetFgordina( String cdutente
                                                    ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        long ll_ = 0;

        l_query   = "";
        l_query  += " update pgmr.web_import_ordven  \n";
        l_query  += "    set fgordina = ?              \n";
        l_query  += "  where cdutente  = ?              \n";

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setString(ind, "N"); ind += 1;
        pstm.setString(ind, cdutente); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();

        return tot_rec;

    }



    /****
        delete: 
    **/ 

    public int executeDelete( long tkimp
                                                    ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        long ll_ = 0;

        l_query   = "";
        l_query  += " delete from pgmr.web_import_ordven  \n";
        l_query  += "  where tkimp  = ?              \n";

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setLong  (ind, tkimp); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();

        return tot_rec;

    }




    /****
        : 
    **/ 

    public int countOrdinate( String cdutente
                                                    ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        int li_ = 0;

        l_query   = "";
        l_query  += " select count(*)                   \n";
        l_query  += "   from pgmr.web_import_ordven  \n";
        l_query  += "  where cdutente = ?             \n";
        l_query  += "    and fgordina = 'S'        \n";

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setString(ind, cdutente); ind += 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next() && rs.getObject(1)!=null){
            li_ = rs.getInt(1);
        }

        pstm.close();

        return li_;

    }



    /****
        getPos_ordine: 
    **/ 

    public int setTkposi( long tkimp
                        , long tkordi
                        , long tkposi
                                                    ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        long ll_ = 0;

        l_query   = "";
        l_query  += " update pgmr.web_import_ordven  \n";
        l_query  += "    set tkposi = ?              \n";
        l_query  += "      , fgordina = 'N'          \n";
        l_query  += "  where tkimp  = ?              \n";

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setLong  (ind, tkposi); ind += 1;
        pstm.setLong  (ind, tkimp); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();

        return tot_rec;

    }













    /****
        : 
    **/ 

    public int isArticoli_imp( String cdutente
                                                    ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        int li_ = 0;

        l_query   = "";
        l_query  += " select count(*)                \n";
        l_query  += "   from pgmr.web_import_ordven  \n";
        l_query  += "  where cdutente = ?            \n";
        l_query  += "    and tkposi is null          \n";

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setString(ind, cdutente); ind += 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next() && rs.getObject(1)!=null){
            li_ = rs.getInt(1);
        }

        pstm.close();

        return li_;

    }








    public double getQta_tototale( String cdutente ) throws Exception {
        
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        double quantita = 0;


        l_query   = "";
        l_query  += " select sum(quantita)           \n";
        l_query  += "   from pgmr.web_import_ordven  \n";
        l_query  += "  where cdutente = ?            \n";
        l_query  += "    and fgordina = 'S'          \n";
        l_query  += "    and tkposi is null          \n";




		pstm = m_connection.prepareStatement ( l_query ) ;

        ind = 1;
        pstm.setString(ind, cdutente); ind += 1;

        rs = pstm.executeQuery();
                                                   
        if (rs!=null && rs.next() && rs.getObject("quantita")!=null){
            quantita = rs.getDouble("quantita");
        }
        pstm.close();

        return quantita;


    }






}

