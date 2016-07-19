package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;





public class Ep_costanti extends Atk_sql {

    public Ep_costanti() {

        super();
    }



    public String getCostvalue(String costname) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        return getCostvalue(cdazie, cdappl, cddipa, profil, costname);

    }
    public String getCostvalue( String cdazie   
                              , String cdappl   
                              , String cddipa   
                              , String profil
                              , String costname
                                                ) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        String costvalue = "";

        costname = costname.trim().toLowerCase();

        l_query   = "";
        l_query  += " SELECT tkcost                            \n";
        l_query  += "      , costvalue                         \n";
        l_query  += "   FROM pgmr.ep_costanti                  \n";
        l_query  += "  WHERE ( cdazie = ? OR  cdazie is null ) \n";
        l_query  += "    AND ( cdappl = ? OR  cdappl is null ) \n";
        l_query  += "    AND ( cddipa = ? OR  cddipa is null ) \n";
        l_query  += "    AND ( profil = ? OR  profil is null ) \n";
        l_query  += "    AND trim(lower(costname)) = ?         \n";
        l_query  += "  ORDER BY tkcost desc                    \n";

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setString( ind, cdazie   ); ind += 1;
        pstm.setString( ind, cdappl   ); ind += 1;
        pstm.setString( ind, cddipa   ); ind += 1;
        pstm.setString( ind, profil   ); ind += 1;
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
        l_query  += "   from pgmr.ep_costanti \n";
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







    public ResultSet getCostanti( String cdazie   
                                , String cdappl   
                                , String cddipa   
                                , String profil
                                                ) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " select *                  \n";
        l_query  += "   from pgmr.ep_costanti \n";
        l_query  += "  where 1=1                \n";
        if (!cdazie.equals("")){
        l_query  += "    and cdazie = '"+cdazie+"' \n";
        }
        if (!cdappl.equals("")){
        l_query  += "    and cdappl = '"+cdappl+"' \n";
        }
        if (!cddipa.equals("")){
        l_query  += "    and cddipa = '"+cddipa+"' \n";
        }
        if (!profil.equals("")){
        l_query  += "    and profil = '"+profil+"' \n";
        }
        l_query  += "  order by costlabel       \n";

        pstm = setQuery( l_query ) ;

        ind = 1;

        rs = pstm.executeQuery();


        return rs;

    }






    public ResultSet getCostante(String costname) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        l_query   = "";
        l_query  += " select *                  \n";
        l_query  += "   from pgmr.ep_costanti \n";
        l_query  += "  where costname = ?       \n";
        l_query  += "  order by cdazie          \n";
        l_query  += "         , cdappl          \n";
        l_query  += "         , cddipa          \n";
        l_query  += "         , profil          \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString( ind, costname          ); ind += 1;

        rs = pstm.executeQuery();


        return rs;

    }




    public int    setCostvalue( String costname
                              , String costvalue
                              , String costlabel
                              , String cdazie
                              , String cdappl
                              , String cddipa
                              , String profil
                                                ) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        long tkcost = 0;



        // Verifico se esiste questa costante

        costname = costname.trim().toLowerCase();

        if (costname.equals("")) return 0;


        l_query   = "";
        l_query  += " select tkcost                    \n";
        l_query  += "   from pgmr.ep_costanti        \n";
        l_query  += "  where trim(lower(costname)) = '"+costname+"' \n";
        if (!cdazie.equals("")) l_query  += "    and cdazie = '"+cdazie+"' \n";
        else                    l_query  += "    and cdazie is null        \n";
        if (!cdappl.equals("")) l_query  += "    and cdappl = '"+cdappl+"' \n";
        else                    l_query  += "    and cdappl is null        \n";
        if (!cddipa.equals("")) l_query  += "    and cddipa = '"+cddipa+"' \n";
        else                    l_query  += "    and cddipa is null        \n";
        if (!profil.equals("")) l_query  += "    and profil = '"+profil+"' \n";
        else                    l_query  += "    and profil is null        \n";

        tkcost = sql_long (l_query);

        if (cdazie.equals("")) cdazie = null;
        if (cdappl.equals("")) cdappl = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;

        if (costname.equals("") ) costname  = null;
        if (costvalue.equals("")) costvalue = null;
        if (costlabel.equals("")) costlabel = null;
        
        if (tkcost > 0){
                
            // Update 

            if (costvalue == null){
                
                l_query   = "";
                l_query  += " delete from pgmr.ep_costanti  \n";
                l_query  += "  where tkcost    = ?       \n";
                
                
                pstm = m_connection.prepareStatement( l_query ) ;
        
                ind = 1;
                pstm.setLong  ( ind, tkcost ); ind += 1;
        
                tot_rec = pstm.executeUpdate();
    
                pstm.close();
                pstm = null;

            }else {
                
                l_query   = "";
                l_query  += " update pgmr.ep_costanti  \n";
                l_query  += "    set costname  = ?       \n";
                l_query  += "      , costvalue = ?       \n";
                l_query  += "      , costlabel = ?       \n";
                l_query  += "      , cdazie    = ?       \n";
                l_query  += "      , cdappl    = ?       \n";
                l_query  += "      , cddipa    = ?       \n";
                l_query  += "      , profil    = ?       \n";
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
    
                pstm.setLong  ( ind, tkcost ); ind += 1;
        
                tot_rec = pstm.executeUpdate();
    
                pstm.close();
                pstm = null;
            }

            

        }else {
            
            if (costvalue != null){
                
                // Insert
    
                tkcost = getNew_token();
    
                l_query   = "";
                l_query  += " insert into pgmr.ep_costanti(  \n";
                l_query  += "             tkcost               \n";
                l_query  += "           , costname             \n";
                l_query  += "           , costvalue            \n";
                l_query  += "           , costlabel            \n";
                l_query  += "           , cdazie               \n";
                l_query  += "           , cdappl               \n";
                l_query  += "           , cddipa               \n";
                l_query  += "           , profil               \n";
                l_query  += "           )values(               \n";
                l_query  += "             ?,?,?,?,?            \n";
                l_query  += "           , ?,?,?                \n";
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
    
        
                tot_rec = pstm.executeUpdate();
    
                pstm.close();
                pstm = null;
                
            }   // FINE if (costvalue != null){
        
            
        }


        m_connection.commit();


        return tot_rec;

    }










    public long getNew_token( ) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        Ep_contator ep_contator = new Ep_contator();

        setProfilo((Atk_sql)ep_contator );

        long nprogr = ep_contator. getNprogr ("00", "TKCOST", "AAAA" ) ;


        return nprogr;

    }





    public long tkcost = 0;



}
