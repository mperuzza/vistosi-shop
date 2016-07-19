package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Web_ord_iva extends Atk_sql {

    public Web_ord_iva() {

        super();
    }




    /****
        getAll: web_ord_iva
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.web_ord_iva  \n";

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: web_ord_iva
    **/ 

    public ResultSet getKey( long       id
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.web_ord_iva  \n";
        l_query  += "  where id = ? \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setLong      (ind, id); ind += 1;

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: web_ord_iva
    **/ 


    public com.ateikon.structure.Rec_web_ord_iva  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_web_ord_iva lstr = new com.ateikon.structure.Rec_web_ord_iva();

        if (rs == null) return lstr;
        if (rs.getObject("id")!=null) lstr.id = rs.getLong      ("id"); 
        if (rs.getObject("tkordi")!=null) lstr.tkordi = rs.getLong      ("tkordi"); 
        if (rs.getObject("cdiva")!=null) lstr.cdiva = rs.getString    ("cdiva"); 
        if (rs.getObject("aliquo")!=null) lstr.aliquo = rs.getDouble    ("aliquo"); 
        if (rs.getObject("imponibile")!=null) lstr.imponibile = rs.getDouble    ("imponibile"); 
        if (rs.getObject("iva")!=null) lstr.iva = rs.getDouble    ("iva"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 

        return lstr;
    }




    /****
        execute: web_ord_iva
    **/ 


    public int execute ( com.ateikon.structure.Rec_web_ord_iva astr) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec =  execute ( astr.id
                          , astr.tkordi
                          , astr.cdiva
                          , astr.aliquo
                          , astr.imponibile
                          , astr.iva
                           );

        return tot_rec;
    }




    /****
        execute: web_ord_iva
    **/ 


    public int execute ( long       id
                       , long       tkordi
                       , String     cdiva
                       , double     aliquo
                       , double     imponibile
                       , double     iva
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " select id\n";
        l_query  += "   from pgmr.web_ord_iva  \n";
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
                                    , tkordi
                                    , cdiva
                                    , aliquo
                                    , imponibile
                                    , iva
                                    );
        }else {

            pstm.close();
            tot_rec = executeInsert ( id
                                    , tkordi
                                    , cdiva
                                    , aliquo
                                    , imponibile
                                    , iva
                                    );
        }

        if (tot_rec != 1){

            m_connection.rollback();
            throw new Exception("Errore Agg. web_ord_iva! ");
        }

        return tot_rec;
    }




    /****
        executeInsert: web_ord_iva
    **/ 


    public int executeInsert( long       id
                            , long       tkordi
                            , String     cdiva
                            , double     aliquo
                            , double     imponibile
                            , double     iva
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        if (ib_calcola_token){
            long ll_tk = getNew_token( );

            if (ll_tk <= 0){
                throw new Exception ("Error Keypool (web_ord_iva): "+ll_tk );
            }

            this.id = ll_tk;
            id = this.id;
        }



        l_query   = "";
        l_query  += " insert into pgmr.web_ord_iva ( \n";
        l_query  += "             id   \n";
        l_query  += "           , tkordi   \n";
        l_query  += "           , cdiva   \n";
        l_query  += "           , aliquo   \n";
        l_query  += "           , imponibile   \n";
        l_query  += "           , iva   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cdiva.equals("")) cdiva = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;


        ind = 1;
        if (id == 0 && null_id){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, id); ind += 1;
        } 
        if (tkordi == 0 && null_tkordi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkordi); ind += 1;
        } 
        pstm.setString    (ind, cdiva); ind += 1;
        if (aliquo == 0 && null_aliquo){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, aliquo); ind += 1;
        } 
        if (imponibile == 0 && null_imponibile){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, imponibile); ind += 1;
        } 
        if (iva == 0 && null_iva){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, iva); ind += 1;
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

        long ll_tk = f_tabkey.getTabkey( "web_ord_iva" );

        return ll_tk;

    }




    /****
        executeUpdate: web_ord_iva
    **/ 


    public int executeUpdate( long       id
                            , long       tkordi
                            , String     cdiva
                            , double     aliquo
                            , double     imponibile
                            , double     iva
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.web_ord_iva  \n";
        l_query  += "         set tkordi = ?  \n";
        l_query  += "           , cdiva = ?  \n";
        l_query  += "           , aliquo = ?  \n";
        l_query  += "           , imponibile = ?  \n";
        l_query  += "           , iva = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "  where id = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cdiva.equals("")) cdiva = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;


        ind = 1;
        if (tkordi == 0 && null_tkordi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkordi); ind += 1;
        } 
        pstm.setString    (ind, cdiva); ind += 1;
        if (aliquo == 0 && null_aliquo){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, aliquo); ind += 1;
        } 
        if (imponibile == 0 && null_imponibile){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, imponibile); ind += 1;
        } 
        if (iva == 0 && null_iva){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, iva); ind += 1;
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

    public boolean ib_calcola_token = true;
    public boolean null_id = true;
    public boolean null_tkordi = true;
    public boolean null_aliquo = true;
    public boolean null_imponibile = true;
    public boolean null_iva = true;







}

