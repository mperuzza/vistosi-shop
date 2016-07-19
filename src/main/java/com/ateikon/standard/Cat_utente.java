package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Cat_utente extends Atk_sql {

    public Cat_utente() {

        super();
    }




    /****
        getAll: cat_utente
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.cat_utente  \n";

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: cat_utente
    **/ 

    public ResultSet getKey( long       tkutente
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.cat_utente  \n";
        l_query  += "  where tkutente = ? \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setLong      (ind, tkutente); ind += 1;

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: cat_utente
    **/ 


    public com.ateikon.structure.Rec_cat_utente  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_cat_utente lstr = new com.ateikon.structure.Rec_cat_utente();

        if (rs == null) return lstr;
        if (rs.getObject("tkutente")!=null) lstr.tkutente = rs.getLong      ("tkutente"); 
        if (rs.getObject("user_name")!=null) lstr.user_name = rs.getString    ("user_name"); 
        if (rs.getObject("password")!=null) lstr.password = rs.getString    ("password"); 
        if (rs.getObject("dsutente")!=null) lstr.dsutente = rs.getString    ("dsutente"); 
        if (rs.getObject("tkclie")!=null) lstr.tkclie = rs.getString    ("tkclie"); 
        if (rs.getObject("tkforn")!=null) lstr.tkforn = rs.getString    ("tkforn"); 
        if (rs.getObject("cdente")!=null) lstr.cdente = rs.getString    ("cdente"); 
        if (rs.getObject("cdagen")!=null) lstr.cdagen = rs.getString    ("cdagen"); 
        if (rs.getObject("cdutente")!=null) lstr.cdutente = rs.getString    ("cdutente"); 
        if (rs.getObject("fgadmin")!=null) lstr.fgadmin = rs.getString    ("fgadmin"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("email")!=null) lstr.email = rs.getString    ("email"); 

        return lstr;
    }




    /****
        execute: cat_utente
    **/ 


    public int execute ( com.ateikon.structure.Rec_cat_utente astr) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec =  execute ( astr.tkutente
                          , astr.user_name
                          , astr.password
                          , astr.dsutente
                          , astr.tkclie
                          , astr.tkforn
                          , astr.cdente
                          , astr.cdagen
                          , astr.cdutente
                          , astr.fgadmin
                          , astr.email
                           );

        return tot_rec;
    }




    /****
        execute: cat_utente
    **/ 


    public int execute ( long       tkutente
                       , String     user_name
                       , String     password
                       , String     dsutente
                       , String     tkclie
                       , String     tkforn
                       , String     cdente
                       , String     cdagen
                       , String     cdutente
                       , String     fgadmin
                       , String     email
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " select tkutente\n";
        l_query  += "   from pgmr.cat_utente  \n";
        l_query  += "  where tkutente = ? \n";


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (tkutente == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkutente); ind += 1;
        } 

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            pstm.close();
            tot_rec = executeUpdate ( tkutente
                                    , user_name
                                    , password
                                    , dsutente
                                    , tkclie
                                    , tkforn
                                    , cdente
                                    , cdagen
                                    , cdutente
                                    , fgadmin
                                    , email
                                    );
        }else {

            pstm.close();
            tot_rec = executeInsert ( tkutente
                                    , user_name
                                    , password
                                    , dsutente
                                    , tkclie
                                    , tkforn
                                    , cdente
                                    , cdagen
                                    , cdutente
                                    , fgadmin
                                    , email
                                    );
        }

        if (tot_rec != 1){

            m_connection.rollback();
            throw new Exception("Errore Agg. cat_utente! ");
        }

        return tot_rec;
    }




    /****
        executeInsert: cat_utente
    **/ 


    public int executeInsert( long       tkutente
                            , String     user_name
                            , String     password
                            , String     dsutente
                            , String     tkclie
                            , String     tkforn
                            , String     cdente
                            , String     cdagen
                            , String     cdutente
                            , String     fgadmin
                            , String     email
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        if (ib_calcola_token){
            long ll_tk = getNew_token( );

            if (ll_tk <= 0){
                throw new Exception ("Error Keypool (cat_utente): "+ll_tk );
            }

            this.tkutente = ll_tk;
            tkutente = this.tkutente;
        }



        l_query   = "";
        l_query  += " insert into pgmr.cat_utente ( \n";
        l_query  += "             tkutente   \n";
        l_query  += "           , user_name   \n";
        l_query  += "           , password   \n";
        l_query  += "           , dsutente   \n";
        l_query  += "           , tkclie   \n";
        l_query  += "           , tkforn   \n";
        l_query  += "           , cdente   \n";
        l_query  += "           , cdagen   \n";
        l_query  += "           , cdutente   \n";
        l_query  += "           , fgadmin   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "           , email   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (user_name.equals("")) user_name = null;
        if (password.equals("")) password = null;
        if (dsutente.equals("")) dsutente = null;
        if (tkclie.equals("")) tkclie = null;
        if (tkforn.equals("")) tkforn = null;
        if (cdente.equals("")) cdente = null;
        if (cdagen.equals("")) cdagen = null;
        if (cdutente.equals("")) cdutente = null;
        if (fgadmin.equals("")) fgadmin = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;
        if (email.equals("")) email = null;


        ind = 1;
        if (tkutente == 0 && null_tkutente){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkutente); ind += 1;
        } 
        pstm.setString    (ind, user_name); ind += 1;
        pstm.setString    (ind, password); ind += 1;
        pstm.setString    (ind, dsutente); ind += 1;
        pstm.setString    (ind, tkclie); ind += 1;
        pstm.setString    (ind, tkforn); ind += 1;
        pstm.setString    (ind, cdente); ind += 1;
        pstm.setString    (ind, cdagen); ind += 1;
        pstm.setString    (ind, cdutente); ind += 1;
        pstm.setString    (ind, fgadmin); ind += 1;
        pstm.setString    (ind, cdazie); ind += 1;
        pstm.setString    (ind, cddipa); ind += 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtinse); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;
        pstm.setString    (ind, email); ind += 1;

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

        long ll_tk = f_tabkey.getTabkey( "cat_utente" );

        return ll_tk;

    }




    /****
        executeUpdate: cat_utente
    **/ 


    public int executeUpdate( long       tkutente
                            , String     user_name
                            , String     password
                            , String     dsutente
                            , String     tkclie
                            , String     tkforn
                            , String     cdente
                            , String     cdagen
                            , String     cdutente
                            , String     fgadmin
                            , String     email
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.cat_utente  \n";
        l_query  += "         set user_name = ?  \n";
        l_query  += "           , password = ?  \n";
        l_query  += "           , dsutente = ?  \n";
        l_query  += "           , tkclie = ?  \n";
        l_query  += "           , tkforn = ?  \n";
        l_query  += "           , cdente = ?  \n";
        l_query  += "           , cdagen = ?  \n";
        l_query  += "           , cdutente = ?  \n";
        l_query  += "           , fgadmin = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "           , email = ?  \n";
        l_query  += "  where tkutente = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (user_name.equals("")) user_name = null;
        if (password.equals("")) password = null;
        if (dsutente.equals("")) dsutente = null;
        if (tkclie.equals("")) tkclie = null;
        if (tkforn.equals("")) tkforn = null;
        if (cdente.equals("")) cdente = null;
        if (cdagen.equals("")) cdagen = null;
        if (cdutente.equals("")) cdutente = null;
        if (fgadmin.equals("")) fgadmin = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;
        if (email.equals("")) email = null;


        ind = 1;
        pstm.setString    (ind, user_name); ind += 1;
        pstm.setString    (ind, password); ind += 1;
        pstm.setString    (ind, dsutente); ind += 1;
        pstm.setString    (ind, tkclie); ind += 1;
        pstm.setString    (ind, tkforn); ind += 1;
        pstm.setString    (ind, cdente); ind += 1;
        pstm.setString    (ind, cdagen); ind += 1;
        pstm.setString    (ind, cdutente); ind += 1;
        pstm.setString    (ind, fgadmin); ind += 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;
        pstm.setString    (ind, email); ind += 1;

        pstm.setLong      (ind, tkutente); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.tkutente = tkutente; 

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public long       tkutente = 0; 






    // --- Impostando a false queste prop. il def. è 0

    public boolean ib_calcola_token = true;
    public boolean null_tkutente = true;







}

