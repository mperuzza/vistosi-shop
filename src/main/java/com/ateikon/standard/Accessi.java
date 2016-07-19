package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Accessi extends Atk_sql {

    public Accessi() {

        super();
    }




    /****
        getKey: accessi
    **/ 

    public ResultSet getKey( Timestamp  dtaccesso
                           , String     cdutente
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from web.accessi  \n";
        l_query  += "  where dtaccesso = ? \n";
        l_query  += "    and cdutente = ? \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setTimestamp (ind, dtaccesso); ind += 1;
        pstm.setString    (ind, cdutente); ind += 1;

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: accessi
    **/ 


    public com.ateikon.structure.Rec_accessi  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_accessi lstr = new com.ateikon.structure.Rec_accessi();

        if (rs == null) return lstr;
        if (rs.getObject("dtaccesso")!=null) lstr.dtaccesso = rs.getTimestamp ("dtaccesso"); 
        if (rs.getObject("cdutente")!=null) lstr.cdutente = rs.getString    ("cdutente"); 
        if (rs.getObject("cdagen")!=null) lstr.cdagen = rs.getString    ("cdagen"); 
        if (rs.getObject("cdcapo")!=null) lstr.cdcapo = rs.getString    ("cdcapo"); 
        if (rs.getObject("tkclie")!=null) lstr.tkclie = rs.getString    ("tkclie"); 
        if (rs.getObject("foto")!=null) lstr.foto = rs.getString    ("foto"); 
        if (rs.getObject("sezione")!=null) lstr.sezione = rs.getString    ("sezione"); 
        if (rs.getObject("panorama")!=null) lstr.panorama = rs.getString    ("panorama"); 
        if (rs.getObject("url")!=null) lstr.url = rs.getString    ("url"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("cdarti")!=null) lstr.cdarti = rs.getString    ("cdarti"); 

        return lstr;
    }




    /****
        execute: accessi
    **/ 


    public int execute ( com.ateikon.structure.Rec_accessi astr) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec =  execute ( astr.dtaccesso
                           , astr.cdutente
                          , astr.cdagen
                          , astr.cdcapo
                          , astr.tkclie
                          , astr.foto
                          , astr.sezione
                          , astr.panorama
                          , astr.url
                          , astr.cdarti
                           );

        return tot_rec;
    }




    /****
        execute: accessi
    **/ 


    public int execute ( Timestamp  dtaccesso
                       , String     cdutente
                       , String     cdagen
                       , String     cdcapo
                       , String     tkclie
                       , String     foto
                       , String     sezione
                       , String     panorama
                       , String     url
                       , String     cdarti
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " select dtaccesso\n";
        l_query  += "      , cdutente\n";
        l_query  += "   from web.accessi  \n";
        l_query  += "  where dtaccesso = ? \n";
        l_query  += "    and cdutente = ? \n";


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setTimestamp (ind, dtaccesso); ind += 1;
        pstm.setString    (ind, cdutente); ind += 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            pstm.close();
            tot_rec = executeUpdate ( dtaccesso
                                    , cdutente
                                    , cdagen
                                    , cdcapo
                                    , tkclie
                                    , foto
                                    , sezione
                                    , panorama
                                    , url
                                    , cdarti
                                    );
        }else {

            pstm.close();
            tot_rec = executeInsert ( dtaccesso
                                    , cdutente
                                    , cdagen
                                    , cdcapo
                                    , tkclie
                                    , foto
                                    , sezione
                                    , panorama
                                    , url
                                    , cdarti
                                    );
        }

        if (tot_rec != 1){

            m_connection.rollback();
            throw new Exception("Errore Agg. accessi! ");
        }

        return tot_rec;
    }




    /****
        executeInsert: accessi
    **/ 


    public int executeInsert( Timestamp  dtaccesso
                            , String     cdutente
                            , String     cdagen
                            , String     cdcapo
                            , String     tkclie
                            , String     foto
                            , String     sezione
                            , String     panorama
                            , String     url
                            , String     cdarti
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;





        l_query   = "";
        l_query  += " insert into web.accessi ( \n";
        l_query  += "             dtaccesso   \n";
        l_query  += "           , cdutente   \n";
        l_query  += "           , cdagen   \n";
        l_query  += "           , cdcapo   \n";
        l_query  += "           , tkclie   \n";
        l_query  += "           , foto   \n";
        l_query  += "           , sezione   \n";
        l_query  += "           , panorama   \n";
        l_query  += "           , url   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "           , cdarti   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cdagen.equals("")) cdagen = null;
        if (cdcapo.equals("")) cdcapo = null;
        if (tkclie.equals("")) tkclie = null;
        if (foto.equals("")) foto = null;
        if (sezione.equals("")) sezione = null;
        if (panorama.equals("")) panorama = null;
        if (url.equals("")) url = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;
        if (cdarti.equals("")) cdarti = null;


        ind = 1;
        pstm.setTimestamp (ind, dtaccesso); ind += 1;
        pstm.setString    (ind, cdutente); ind += 1;
        pstm.setString    (ind, cdagen); ind += 1;
        pstm.setString    (ind, cdcapo); ind += 1;
        pstm.setString    (ind, tkclie); ind += 1;
        pstm.setString    (ind, foto); ind += 1;
        pstm.setString    (ind, sezione); ind += 1;
        pstm.setString    (ind, panorama); ind += 1;
        pstm.setString    (ind, url); ind += 1;
        pstm.setString    (ind, cdazie); ind += 1;
        pstm.setString    (ind, cddipa); ind += 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtinse); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;
        pstm.setString    (ind, cdarti); ind += 1;

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

        long ll_tk = f_tabkey.getTabkey( "accessi" );

        return ll_tk;

    }




    /****
        executeUpdate: accessi
    **/ 


    public int executeUpdate( Timestamp  dtaccesso
                            , String     cdutente
                            , String     cdagen
                            , String     cdcapo
                            , String     tkclie
                            , String     foto
                            , String     sezione
                            , String     panorama
                            , String     url
                            , String     cdarti
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update web.accessi  \n";
        l_query  += "         set cdagen = ?  \n";
        l_query  += "           , cdcapo = ?  \n";
        l_query  += "           , tkclie = ?  \n";
        l_query  += "           , foto = ?  \n";
        l_query  += "           , sezione = ?  \n";
        l_query  += "           , panorama = ?  \n";
        l_query  += "           , url = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "           , cdarti = ?  \n";
        l_query  += "  where dtaccesso = ? \n";
        l_query  += "    and cdutente = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cdagen.equals("")) cdagen = null;
        if (cdcapo.equals("")) cdcapo = null;
        if (tkclie.equals("")) tkclie = null;
        if (foto.equals("")) foto = null;
        if (sezione.equals("")) sezione = null;
        if (panorama.equals("")) panorama = null;
        if (url.equals("")) url = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;
        if (cdarti.equals("")) cdarti = null;


        ind = 1;
        pstm.setString    (ind, cdagen); ind += 1;
        pstm.setString    (ind, cdcapo); ind += 1;
        pstm.setString    (ind, tkclie); ind += 1;
        pstm.setString    (ind, foto); ind += 1;
        pstm.setString    (ind, sezione); ind += 1;
        pstm.setString    (ind, panorama); ind += 1;
        pstm.setString    (ind, url); ind += 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;
        pstm.setString    (ind, cdarti); ind += 1;

        pstm.setTimestamp (ind, dtaccesso); ind += 1;
        pstm.setString    (ind, cdutente); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.dtaccesso = dtaccesso; 
        this.cdutente = cdutente; 

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public Timestamp  dtaccesso = null; 
    public String     cdutente = ""; 






    // --- Impostando a false queste prop. il def. è 0









}

