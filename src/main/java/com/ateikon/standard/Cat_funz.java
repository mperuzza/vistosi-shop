package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Cat_funz extends Atk_sql {

    public Cat_funz() {

        super();
    }




    /****
        getAll: cat_funz
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.cat_funz  \n";

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: cat_funz
    **/ 

    public ResultSet getKey( long       tkfunzione
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.cat_funz  \n";
        l_query  += "  where tkfunzione = ? \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setLong      (ind, tkfunzione); ind += 1;

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: cat_funz
    **/ 


    public com.ateikon.structure.Rec_cat_funz  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_cat_funz lstr = new com.ateikon.structure.Rec_cat_funz();

        if (rs == null) return lstr;
        if (rs.getObject("tkfunzione")!=null) lstr.tkfunzione = rs.getLong      ("tkfunzione"); 
        if (rs.getObject("cdfunzione_m")!=null) lstr.cdfunzione_m = rs.getString    ("cdfunzione_m"); 
        if (rs.getObject("dsfunzione")!=null) lstr.dsfunzione = rs.getString    ("dsfunzione"); 
        if (rs.getObject("fgall_users")!=null) lstr.fgall_users = rs.getString    ("fgall_users"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("profil_inse")!=null) lstr.profil_inse = rs.getString    ("profil_inse"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 

        return lstr;
    }




    /****
        execute: cat_funz
    **/ 


    public int execute ( com.ateikon.structure.Rec_cat_funz astr) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec =  execute ( astr.tkfunzione
                          , astr.cdfunzione_m
                          , astr.dsfunzione
                          , astr.fgall_users
                          , astr.profil_inse
                           );

        return tot_rec;
    }




    /****
        execute: cat_funz
    **/ 


    public int execute ( long       tkfunzione
                       , String     cdfunzione_m
                       , String     dsfunzione
                       , String     fgall_users
                       , String     profil_inse
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " select tkfunzione\n";
        l_query  += "   from pgmr.cat_funz  \n";
        l_query  += "  where tkfunzione = ? \n";


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (tkfunzione == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkfunzione); ind += 1;
        } 

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            pstm.close();
            tot_rec = executeUpdate ( tkfunzione
                                    , cdfunzione_m
                                    , dsfunzione
                                    , fgall_users
                                    , profil_inse
                                    );
        }else {

            pstm.close();
            tot_rec = executeInsert ( tkfunzione
                                    , cdfunzione_m
                                    , dsfunzione
                                    , fgall_users
                                    , profil_inse
                                    );
        }

        if (tot_rec != 1){

            m_connection.rollback();
            throw new Exception("Errore Agg. cat_funz! ");
        }

        return tot_rec;
    }




    /****
        executeInsert: cat_funz
    **/ 


    public int executeInsert( long       tkfunzione
                            , String     cdfunzione_m
                            , String     dsfunzione
                            , String     fgall_users
                            , String     profil_inse
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        if (ib_calcola_token){
            long ll_tk = getNew_token( );

            if (ll_tk <= 0){
                throw new Exception ("Error Keypool (cat_funz): "+ll_tk );
            }

            this.tkfunzione = ll_tk;
            tkfunzione = this.tkfunzione;
        }



        l_query   = "";
        l_query  += " insert into pgmr.cat_funz ( \n";
        l_query  += "             tkfunzione   \n";
        l_query  += "           , cdfunzione_m   \n";
        l_query  += "           , dsfunzione   \n";
        l_query  += "           , fgall_users   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , profil_inse   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cdfunzione_m.equals("")) cdfunzione_m = null;
        if (dsfunzione.equals("")) dsfunzione = null;
        if (fgall_users.equals("")) fgall_users = null;
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
        pstm.setString    (ind, cdfunzione_m); ind += 1;
        pstm.setString    (ind, dsfunzione); ind += 1;
        pstm.setString    (ind, fgall_users); ind += 1;
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

        long ll_tk = f_tabkey.getTabkey( "cat_funz" );

        return ll_tk;

    }




    /****
        executeUpdate: cat_funz
    **/ 


    public int executeUpdate( long       tkfunzione
                            , String     cdfunzione_m
                            , String     dsfunzione
                            , String     fgall_users
                            , String     profil_inse
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.cat_funz  \n";
        l_query  += "         set cdfunzione_m = ?  \n";
        l_query  += "           , dsfunzione = ?  \n";
        l_query  += "           , fgall_users = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , profil_inse = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "  where tkfunzione = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cdfunzione_m.equals("")) cdfunzione_m = null;
        if (dsfunzione.equals("")) dsfunzione = null;
        if (fgall_users.equals("")) fgall_users = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;
        if (profil_inse.equals("")) profil_inse = null;


        ind = 1;
        pstm.setString    (ind, cdfunzione_m); ind += 1;
        pstm.setString    (ind, dsfunzione); ind += 1;
        pstm.setString    (ind, fgall_users); ind += 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setString    (ind, profil_inse); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;

        pstm.setLong      (ind, tkfunzione); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.tkfunzione = tkfunzione; 

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public long       tkfunzione = 0; 






    // --- Impostando a false queste prop. il def. è 0

    public boolean ib_calcola_token = true;
    public boolean null_tkfunzione = true;







}

