package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Atk_alert_yacht extends Atk_sql {

    public Atk_alert_yacht() {

        super();
    }




    /****
        getKey: atk_alert_yacht
    **/ 

    public ResultSet getKey( long       id
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.atk_alert_yacht  \n";
        l_query  += "  where id = ? \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setLong      (ind, id); ind += 1;

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: atk_alert_yacht
    **/ 


    public com.ateikon.structure.Rec_atk_alert_yacht  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_atk_alert_yacht lstr = new com.ateikon.structure.Rec_atk_alert_yacht();

        if (rs == null) return lstr;
        if (rs.getObject("id")!=null) lstr.id = rs.getLong      ("id"); 
        if (rs.getObject("id_alert")!=null) lstr.id_alert = rs.getLong      ("id_alert"); 
        if (rs.getObject("fg_tipo")!=null) lstr.fg_tipo = rs.getString    ("fg_tipo"); 
        if (rs.getObject("tkcantiere")!=null) lstr.tkcantiere = rs.getLong      ("tkcantiere"); 
        if (rs.getObject("tkmodello")!=null) lstr.tkmodello = rs.getLong      ("tkmodello"); 
        if (rs.getObject("prezzo_da")!=null) lstr.prezzo_da = rs.getDouble    ("prezzo_da"); 
        if (rs.getObject("prezzo_a")!=null) lstr.prezzo_a = rs.getDouble    ("prezzo_a"); 
        if (rs.getObject("anno_costruzione_barca")!=null) lstr.anno_costruzione_barca = rs.getLong      ("anno_costruzione_barca"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 

        return lstr;
    }




    /****
        execute: atk_alert_yacht
    **/ 


    public int execute ( com.ateikon.structure.Rec_atk_alert_yacht astr) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec =  execute ( astr.id
                          , astr.id_alert
                          , astr.fg_tipo
                          , astr.tkcantiere
                          , astr.tkmodello
                          , astr.prezzo_da
                          , astr.prezzo_a
                          , astr.anno_costruzione_barca
                           );

        return tot_rec;
    }




    /****
        execute: atk_alert_yacht
    **/ 


    public int execute ( long       id
                       , long       id_alert
                       , String     fg_tipo
                       , long       tkcantiere
                       , long       tkmodello
                       , double     prezzo_da
                       , double     prezzo_a
                       , long       anno_costruzione_barca
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " select id\n";
        l_query  += "   from pgmr.atk_alert_yacht  \n";
        l_query  += "  where id = ? \n";


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (id == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, id); ind += 1;
        } 

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            pstm.close();
            tot_rec = executeUpdate ( id
                                    , id_alert
                                    , fg_tipo
                                    , tkcantiere
                                    , tkmodello
                                    , prezzo_da
                                    , prezzo_a
                                    , anno_costruzione_barca
                                    );
        }else {

            pstm.close();
            tot_rec = executeInsert ( id
                                    , id_alert
                                    , fg_tipo
                                    , tkcantiere
                                    , tkmodello
                                    , prezzo_da
                                    , prezzo_a
                                    , anno_costruzione_barca
                                    );
        }

        if (tot_rec != 1){

            m_connection.rollback();
            throw new Exception("Errore Agg. atk_alert_yacht! ");
        }

        return tot_rec;
    }




    /****
        executeInsert: atk_alert_yacht
    **/ 


    public int executeInsert( long       id
                            , long       id_alert
                            , String     fg_tipo
                            , long       tkcantiere
                            , long       tkmodello
                            , double     prezzo_da
                            , double     prezzo_a
                            , long       anno_costruzione_barca
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        long ll_tk = getNew_token( );

        if (ll_tk <= 0){
            throw new Exception ("Error Keypool (atk_alert_yacht): "+ll_tk );
        }

        this.id = ll_tk;
        id = this.id;



        l_query   = "";
        l_query  += " insert into pgmr.atk_alert_yacht ( \n";
        l_query  += "             id   \n";
        l_query  += "           , id_alert   \n";
        l_query  += "           , fg_tipo   \n";
        l_query  += "           , tkcantiere   \n";
        l_query  += "           , tkmodello   \n";
        l_query  += "           , prezzo_da   \n";
        l_query  += "           , prezzo_a   \n";
        l_query  += "           , anno_costruzione_barca   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (fg_tipo.equals("")) fg_tipo = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;


        ind = 1;
        if (id == 0 && null_id){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, id); ind += 1;
        } 
        if (id_alert == 0 && null_id_alert){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, id_alert); ind += 1;
        } 
        pstm.setString    (ind, fg_tipo); ind += 1;
        if (tkcantiere == 0 && null_tkcantiere){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkcantiere); ind += 1;
        } 
        if (tkmodello == 0 && null_tkmodello){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkmodello); ind += 1;
        } 
        if (prezzo_da == 0 && null_prezzo_da){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, prezzo_da); ind += 1;
        } 
        if (prezzo_a == 0 && null_prezzo_a){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, prezzo_a); ind += 1;
        } 
        if (anno_costruzione_barca == 0 && null_anno_costruzione_barca){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, anno_costruzione_barca); ind += 1;
        } 
        pstm.setString    (ind, cdazie); ind += 1;
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

        long ll_tk = f_tabkey.getTabkey( "atk_alert_yacht" );

        return ll_tk;

    }




    /****
        executeUpdate: atk_alert_yacht
    **/ 


    public int executeUpdate( long       id
                            , long       id_alert
                            , String     fg_tipo
                            , long       tkcantiere
                            , long       tkmodello
                            , double     prezzo_da
                            , double     prezzo_a
                            , long       anno_costruzione_barca
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.atk_alert_yacht  \n";
        l_query  += "         set id_alert = ?  \n";
        l_query  += "           , fg_tipo = ?  \n";
        l_query  += "           , tkcantiere = ?  \n";
        l_query  += "           , tkmodello = ?  \n";
        l_query  += "           , prezzo_da = ?  \n";
        l_query  += "           , prezzo_a = ?  \n";
        l_query  += "           , anno_costruzione_barca = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "  where id = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (fg_tipo.equals("")) fg_tipo = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;


        ind = 1;
        if (id_alert == 0 && null_id_alert){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, id_alert); ind += 1;
        } 
        pstm.setString    (ind, fg_tipo); ind += 1;
        if (tkcantiere == 0 && null_tkcantiere){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkcantiere); ind += 1;
        } 
        if (tkmodello == 0 && null_tkmodello){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkmodello); ind += 1;
        } 
        if (prezzo_da == 0 && null_prezzo_da){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, prezzo_da); ind += 1;
        } 
        if (prezzo_a == 0 && null_prezzo_a){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, prezzo_a); ind += 1;
        } 
        if (anno_costruzione_barca == 0 && null_anno_costruzione_barca){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, anno_costruzione_barca); ind += 1;
        } 
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;

        pstm.setLong      (ind, id); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.id = id; 

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public long       id = 0; 






    // --- Impostando a false queste prop. il def. è 0


    public boolean null_id = true;
    public boolean null_id_alert = true;
    public boolean null_tkcantiere = true;
    public boolean null_tkmodello = true;
    public boolean null_prezzo_da = true;
    public boolean null_prezzo_a = true;
    public boolean null_anno_costruzione_barca = true;







}

