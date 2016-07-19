package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Cat_funz_user extends Atk_sql {

    public Cat_funz_user() {

        super();
    }




    /****
        getAll: cat_funz_user
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.cat_funz_user  \n";

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: cat_funz_user
    **/ 

    public ResultSet getKey( long       tkfunzione
                           , long       tkutente
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.cat_funz_user  \n";
        l_query  += "  where tkfunzione = ? \n";
        l_query  += "    and tkutente = ? \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setLong      (ind, tkfunzione); ind += 1;
        pstm.setLong      (ind, tkutente); ind += 1;

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: cat_funz_user
    **/ 


    public com.ateikon.structure.Rec_cat_funz_user  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_cat_funz_user lstr = new com.ateikon.structure.Rec_cat_funz_user();

        if (rs == null) return lstr;
        if (rs.getObject("tkfunzione")!=null) lstr.tkfunzione = rs.getLong      ("tkfunzione"); 
        if (rs.getObject("tkutente")!=null) lstr.tkutente = rs.getLong      ("tkutente"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("profil_inse")!=null) lstr.profil_inse = rs.getString    ("profil_inse"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 

        return lstr;
    }




    /****
        execute: cat_funz_user
    **/ 


    public int execute ( com.ateikon.structure.Rec_cat_funz_user astr) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec =  execute ( astr.tkfunzione
                           , astr.tkutente
                          , astr.profil_inse
                           );

        return tot_rec;
    }




    /****
        execute: cat_funz_user
    **/ 


    public int execute ( long       tkfunzione
                       , long       tkutente
                       , String     profil_inse
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " select tkfunzione\n";
        l_query  += "      , tkutente\n";
        l_query  += "   from pgmr.cat_funz_user  \n";
        l_query  += "  where tkfunzione = ? \n";
        l_query  += "    and tkutente = ? \n";


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (tkfunzione == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkfunzione); ind += 1;
        } 
        if (tkutente == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkutente); ind += 1;
        } 

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            pstm.close();
            tot_rec = executeUpdate ( tkfunzione
                                    , tkutente
                                    , profil_inse
                                    );
        }else {

            pstm.close();
            tot_rec = executeInsert ( tkfunzione
                                    , tkutente
                                    , profil_inse
                                    );
        }

        if (tot_rec != 1){

            m_connection.rollback();
            throw new Exception("Errore Agg. cat_funz_user! ");
        }

        return tot_rec;
    }




    /****
        executeInsert: cat_funz_user
    **/ 


    public int executeInsert( long       tkfunzione
                            , long       tkutente
                            , String     profil_inse
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;





        l_query   = "";
        l_query  += " insert into pgmr.cat_funz_user ( \n";
        l_query  += "             tkfunzione   \n";
        l_query  += "           , tkutente   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , profil_inse   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;
        if (profil_inse.equals("")) profil_inse = null;


        ind = 1;
        if (tkfunzione == 0 && null_tkfunzione){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkfunzione); ind += 1;
        } 
        if (tkutente == 0 && null_tkutente){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkutente); ind += 1;
        } 
        pstm.setString    (ind, cdazie); ind += 1;
        pstm.setString    (ind, cddipa); ind += 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setString    (ind, profil_inse); ind += 1;
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

        long ll_tk = f_tabkey.getTabkey( "cat_funz_user" );

        return ll_tk;

    }




    /****
        executeUpdate: cat_funz_user
    **/ 


    public int executeUpdate( long       tkfunzione
                            , long       tkutente
                            , String     profil_inse
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.cat_funz_user  \n";
        l_query  += "         set profil = ?  \n";
        l_query  += "           , profil_inse = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "  where tkfunzione = ? \n";
        l_query  += "    and tkutente = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;
        if (profil_inse.equals("")) profil_inse = null;


        ind = 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setString    (ind, profil_inse); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;

        pstm.setLong      (ind, tkfunzione); ind += 1;
        pstm.setLong      (ind, tkutente); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.tkfunzione = tkfunzione; 
        this.tkutente = tkutente; 

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public long       tkfunzione = 0; 
    public long       tkutente = 0; 






    // --- Impostando a false queste prop. il def. è 0

    public boolean ib_calcola_token = true;
    public boolean null_tkfunzione = true;
    public boolean null_tkutente = true;







}

