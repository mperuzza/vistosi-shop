package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Cat_tipomapr_clie extends Atk_sql {

    public Cat_tipomapr_clie() {

        super();
    }




    /****
        getAll: cat_tipomapr_clie
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.cat_tipomapr_clie  \n";

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: cat_tipomapr_clie
    **/ 

    public ResultSet getKey( String     tkclie
                           , String     cdazie
                           , String     cdtipm
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.cat_tipomapr_clie  \n";
        l_query  += "  where tkclie = ? \n";
        l_query  += "    and cdazie = ? \n";
        l_query  += "    and cdtipm = ? \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString    (ind, tkclie); ind += 1;
        pstm.setString    (ind, cdazie); ind += 1;
        pstm.setString    (ind, cdtipm); ind += 1;

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: cat_tipomapr_clie
    **/ 


    public com.ateikon.structure.Rec_cat_tipomapr_clie  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_cat_tipomapr_clie lstr = new com.ateikon.structure.Rec_cat_tipomapr_clie();

        if (rs == null) return lstr;
        if (rs.getObject("tkclie")!=null) lstr.tkclie = rs.getString    ("tkclie"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cdtipm")!=null) lstr.cdtipm = rs.getString    ("cdtipm"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 

        return lstr;
    }




    /****
        execute: cat_tipomapr_clie
    **/ 


    public int execute ( com.ateikon.structure.Rec_cat_tipomapr_clie astr) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec =  execute ( astr.tkclie
                           , astr.cdtipm
                           );

        return tot_rec;
    }




    /****
        execute: cat_tipomapr_clie
    **/ 


    public int execute ( String     tkclie
                       , String     cdtipm
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " select tkclie\n";
        l_query  += "      , cdazie\n";
        l_query  += "      , cdtipm\n";
        l_query  += "   from pgmr.cat_tipomapr_clie  \n";
        l_query  += "  where tkclie = ? \n";
        l_query  += "    and cdazie = ? \n";
        l_query  += "    and cdtipm = ? \n";


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setString    (ind, tkclie); ind += 1;
        pstm.setString    (ind, cdazie); ind += 1;
        pstm.setString    (ind, cdtipm); ind += 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            pstm.close();
            tot_rec = executeUpdate ( tkclie
                                    , cdtipm
                                    );
        }else {

            pstm.close();
            tot_rec = executeInsert ( tkclie
                                    , cdtipm
                                    );
        }

        if (tot_rec != 1){

            m_connection.rollback();
            throw new Exception("Errore Agg. cat_tipomapr_clie! ");
        }

        return tot_rec;
    }




    /****
        executeInsert: cat_tipomapr_clie
    **/ 


    public int executeInsert( String     tkclie
                            , String     cdtipm
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;





        l_query   = "";
        l_query  += " insert into pgmr.cat_tipomapr_clie ( \n";
        l_query  += "             tkclie   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cdtipm   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;


        ind = 1;
        pstm.setString    (ind, tkclie); ind += 1;
        pstm.setString    (ind, cdazie); ind += 1;
        pstm.setString    (ind, cdtipm); ind += 1;
        pstm.setString    (ind, cddipa); ind += 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtinse); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        return tot_rec;

    }




    /***
        Restituisce il token per questa tabella
    */


    public long getNew_token( ) throws Exception{ 
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        F_tabkey f_tabkey = new F_tabkey();
        setProfilo((Atk_sql) f_tabkey );

        long ll_tk = f_tabkey.getTabkey( "cat_tipomapr_clie" );

        return ll_tk;

    }




    /****
        executeUpdate: cat_tipomapr_clie
    **/ 


    public int executeUpdate( String     tkclie
                            , String     cdtipm
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.cat_tipomapr_clie  \n";
        l_query  += "         set profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "  where tkclie = ? \n";
        l_query  += "    and cdazie = ? \n";
        l_query  += "    and cdtipm = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;


        ind = 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;

        pstm.setString    (ind, tkclie); ind += 1;
        pstm.setString    (ind, cdazie); ind += 1;
        pstm.setString    (ind, cdtipm); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.tkclie = tkclie; 
        this.cdazie = cdazie; 
        this.cdtipm = cdtipm; 

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public String     tkclie = ""; 
    public String     cdtipm = ""; 






    // --- Impostando a false queste prop. il def. è 0

    public boolean ib_calcola_token = true;







}

