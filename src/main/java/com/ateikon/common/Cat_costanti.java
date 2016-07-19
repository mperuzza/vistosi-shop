package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;





public class Cat_costanti extends com.ateikon.standard.Cat_costanti {

    public Cat_costanti() {

        super();
    }



    public String getCostvalue(String costname) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        return getCostvalue(cdazie, cdappl, cddipa, profil, cdagen_appl, costname);

    }
    public String getCostvalue( String cdazie   
                              , String cdappl   
                              , String cddipa   
                              , String profil
                              , String cdagen_appl
                              , String costname
                                                ) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        String costvalue = "";
                                             
        cdagen_appl = cdagen_appl.trim().toLowerCase();
        costname = costname.trim().toLowerCase();

        l_query   = "";
        l_query  += " SELECT tkcost                            \n";
        l_query  += "      , costvalue                         \n";
        l_query  += "   FROM pgmr.cat_costanti                 \n";
        l_query  += "  WHERE ( cdazie = ? OR  cdazie is null ) \n";
        l_query  += "    AND ( cdappl = ? OR  cdappl is null ) \n";
        l_query  += "    AND ( cddipa = ? OR  cddipa is null ) \n";
        l_query  += "    AND ( profil = ? OR  profil is null ) \n";
        l_query  += "    AND cdagen   = ?                      \n";
        l_query  += "    AND costname = ?                      \n";
        l_query  += "  ORDER BY tkcost desc                    \n";

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setString( ind, cdazie   ); ind += 1;
        pstm.setString( ind, cdappl   ); ind += 1;
        pstm.setString( ind, cddipa   ); ind += 1;
        pstm.setString( ind, profil   ); ind += 1;
        pstm.setString( ind, cdagen_appl ); ind += 1;
        pstm.setString( ind, costname ); ind += 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next() && rs.getObject(2)!=null ){

            costvalue = rs.getString(2);

        }
        pstm.close();
        pstm = null;

        return costvalue;


    }








    public String getCostvalue(long tkcost) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        String costvalue = "";

        l_query   = "";
        l_query  += " select costvalue          \n";
        l_query  += "   from pgmr.cat_costanti \n";
        l_query  += "  where tkcost = ?        \n";

        pstm = m_connection.prepareStatement( l_query ) ;

        pstm.setLong( ind, tkcost ); ind += 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            if (rs.getObject(1)!=null ) costvalue = rs.getString(1);

            pstm.close();
            pstm = null;
        }else {
            pstm.close();
            pstm = null;
            throw new Exception("Costante tkcost "+tkcost+" NON trovata!" );
        }

        return costvalue;

    }












 /*
    public int    setCostvalue( long   tkcost
                              , String costname
                              , String costvalue
                              , String costlabel
                              , String cdazie
                              , String cdappl
                              , String cddipa
                              , String profil
                              , String cdagen_appl
                                                ) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        // Verifico se esiste questa costante

        cdagen_appl = cdagen_appl.trim().toLowerCase();
        costname = costname.trim().toLowerCase();

        if (cdazie.equals("")) cdazie = null;
        if (cdappl.equals("")) cdappl = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;
        if (cdagen_appl.equals("")) cdagen_appl = null;

        if (costname.equals("") ) costname  = null;
        if (costvalue.equals("")) costvalue = null;
        if (costlabel.equals("")) costlabel = null;
        
        if (tkcost > 0){
                
            l_query   = "";
            l_query  += " update pgmr.cat_costanti  \n";
            l_query  += "    set costname  = ?       \n";
            l_query  += "      , costvalue = ?       \n";
            l_query  += "      , costlabel = ?       \n";
            l_query  += "      , cdazie    = ?       \n";
            l_query  += "      , cdappl    = ?       \n";
            l_query  += "      , cddipa    = ?       \n";
            l_query  += "      , profil    = ?       \n";
            l_query  += "      , cdagen    = ?       \n";
            l_query  += "  where tkcost    = ?       \n";
            
            
            pstm = m_connection.prepareStatement( l_query ) ;
    
            ind = 1;
            pstm.setString( ind, costname ); ind += 1;
            pstm.setString( ind, costvalue); ind += 1;
            pstm.setString( ind, costlabel); ind += 1;
            pstm.setString( ind, cdazie   ); ind += 1;
            pstm.setString( ind, cdappl   ); ind += 1;
            pstm.setString( ind, cddipa   ); ind += 1;
            pstm.setString( ind, profil   ); ind += 1;
            pstm.setString( ind, cdagen_appl); ind += 1;

            pstm.setLong  ( ind, tkcost ); ind += 1;
    
            tot_rec = pstm.executeUpdate();

            pstm.close();
            pstm = null;

        }else {
            
            if (costvalue != null){
                
                // Insert
    
                tkcost = getNew_token();
                
    
                l_query   = "";
                l_query  += " insert into pgmr.cat_costanti(  \n";
                l_query  += "             tkcost               \n";
                l_query  += "           , costname             \n";
                l_query  += "           , costvalue            \n";
                l_query  += "           , costlabel            \n";
                l_query  += "           , cdazie               \n";
                l_query  += "           , cdappl               \n";
                l_query  += "           , cddipa               \n";
                l_query  += "           , profil               \n";
                l_query  += "           , cdagen               \n";
                l_query  += "           )values(               \n";
                l_query  += "             ?,?,?,?,?            \n";
                l_query  += "           , ?,?,?,?              \n";
                l_query  += "           )                      \n";
    
                pstm = m_connection.prepareStatement( l_query ) ;
        
                ind = 1;
                pstm.setLong  ( ind, tkcost ); ind += 1;
    
                pstm.setString( ind, costname ); ind += 1;
                pstm.setString( ind, costvalue); ind += 1;
                pstm.setString( ind, costlabel); ind += 1;
                pstm.setString( ind, cdazie   ); ind += 1;
                pstm.setString( ind, cdappl   ); ind += 1;
                pstm.setString( ind, cddipa   ); ind += 1;
                pstm.setString( ind, profil   ); ind += 1;
                pstm.setString( ind, cdagen_appl); ind += 1;
    
        
                tot_rec = pstm.executeUpdate();
    
                pstm.close();
                pstm = null;

                
            }   // FINE if (costvalue != null){
        
            
        }


        m_connection.commit();


        return tot_rec;

    }

*/


    /***


    */

    public int delete ( long tkcost ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        l_query   = "";
        l_query  += " delete from pgmr.cat_costanti  \n";
        l_query  += "  where tkcost    = ?       \n";
        
        
        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setLong  ( ind, tkcost ); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        return tot_rec ;

    }






    /***


    */

    public long getTkcost(String costname) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        return getTkcost(cdazie, cdappl, cddipa, profil, cdagen_appl, costname);

    }


    public long  getTkcost ( String cdazie
                           , String cdappl
                           , String cddipa
                           , String profil
                           , String cdagen_appl
                           , String costname
                                        ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        long tkcost = 0;

        cdagen_appl = cdagen_appl.trim().toLowerCase();
        costname = costname.trim().toLowerCase();

        l_query   = "";
        l_query  += " SELECT tkcost                            \n";
        l_query  += "   FROM pgmr.cat_costanti                 \n";
        l_query  += "  WHERE ( cdazie = ? OR  cdazie is null ) \n";
        l_query  += "    AND ( cdappl = ? OR  cdappl is null ) \n";
        l_query  += "    AND ( cddipa = ? OR  cddipa is null ) \n";
        l_query  += "    AND ( profil = ? OR  profil is null ) \n";
        l_query  += "    AND cdagen   = ?                      \n";
        l_query  += "    AND costname = ?                      \n";
        l_query  += "  ORDER BY tkcost desc                    \n";

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setString( ind, cdazie     ); ind += 1;
        pstm.setString( ind, cdappl     ); ind += 1;
        pstm.setString( ind, cddipa     ); ind += 1;
        pstm.setString( ind, profil     ); ind += 1;
        pstm.setString( ind, cdagen_appl); ind += 1;
        pstm.setString( ind, costname   ); ind += 1;


        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            tkcost = rs.getLong(1);

        }
        pstm.close();
        pstm = null;



        return tkcost ;

    }



    public long getNew_token( ) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        Cat_contator cat_contator = new Cat_contator();

        setProfilo((Atk_sql)cat_contator );

        long nprogr = cat_contator.getNprogr ("00", "TKCOST", "AAAA" ) ;


        return nprogr;

    }





    public long tkcost = 0;



}
