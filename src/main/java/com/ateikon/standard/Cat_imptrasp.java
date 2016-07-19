package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Cat_imptrasp extends Atk_sql {

    public Cat_imptrasp() {

        super();
    }




    /****
        getAll: cat_imptrasp
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.cat_imptrasp  \n";

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: cat_imptrasp
    **/ 

    public ResultSet getKey( long       id
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.cat_imptrasp  \n";
        l_query  += "  where id = ? \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setLong      (ind, id); ind += 1;

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: cat_imptrasp
    **/ 


    public com.ateikon.structure.Rec_cat_imptrasp  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_cat_imptrasp lstr = new com.ateikon.structure.Rec_cat_imptrasp();

        if (rs == null) return lstr;
        if (rs.getObject("id")!=null) lstr.id = rs.getLong      ("id"); 
        if (rs.getObject("cdnazi")!=null) lstr.cdnazi = rs.getString    ("cdnazi"); 
        if (rs.getObject("cdprov")!=null) lstr.cdprov = rs.getString    ("cdprov"); 
        if (rs.getObject("perc")!=null) lstr.perc = rs.getDouble    ("perc"); 
        if (rs.getObject("importo_min")!=null) lstr.importo_min = rs.getDouble    ("importo_min"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtwebsync")!=null) lstr.dtwebsync = rs.getTimestamp ("dtwebsync"); 
        if (rs.getObject("importo_max")!=null) lstr.importo_max = rs.getDouble    ("importo_max"); 

        return lstr;
    }




    /****
        execute: cat_imptrasp
    **/ 


    public int execute ( com.ateikon.structure.Rec_cat_imptrasp astr) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec =  execute ( astr.id
                          , astr.cdnazi
                          , astr.cdprov
                          , astr.perc
                          , astr.importo_min
                          , astr.dtwebsync
                          , astr.importo_max
                           );

        return tot_rec;
    }




    /****
        execute: cat_imptrasp
    **/ 


    public int execute ( long       id
                       , String     cdnazi
                       , String     cdprov
                       , double     perc
                       , double     importo_min
                       , Timestamp  dtwebsync
                       , double     importo_max
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " select id\n";
        l_query  += "   from pgmr.cat_imptrasp  \n";
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
                                    , cdnazi
                                    , cdprov
                                    , perc
                                    , importo_min
                                    , dtwebsync
                                    , importo_max
                                    );
        }else {

            pstm.close();
            tot_rec = executeInsert ( id
                                    , cdnazi
                                    , cdprov
                                    , perc
                                    , importo_min
                                    , dtwebsync
                                    , importo_max
                                    );
        }

        if (tot_rec != 1){

            m_connection.rollback();
            throw new Exception("Errore Agg. cat_imptrasp! ");
        }

        return tot_rec;
    }




    /****
        executeInsert: cat_imptrasp
    **/ 


    public int executeInsert( long       id
                            , String     cdnazi
                            , String     cdprov
                            , double     perc
                            , double     importo_min
                            , Timestamp  dtwebsync
                            , double     importo_max
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        if (ib_calcola_token){
            long ll_tk = getNew_token( );

            if (ll_tk <= 0){
                throw new Exception ("Error Keypool (cat_imptrasp): "+ll_tk );
            }

            this.id = ll_tk;
            id = this.id;
        }



        l_query   = "";
        l_query  += " insert into pgmr.cat_imptrasp ( \n";
        l_query  += "             id   \n";
        l_query  += "           , cdnazi   \n";
        l_query  += "           , cdprov   \n";
        l_query  += "           , perc   \n";
        l_query  += "           , importo_min   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtwebsync   \n";
        l_query  += "           , importo_max   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cdnazi.equals("")) cdnazi = null;
        if (cdprov.equals("")) cdprov = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;


        ind = 1;
        if (id == 0 && null_id){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, id); ind += 1;
        } 
        pstm.setString    (ind, cdnazi); ind += 1;
        pstm.setString    (ind, cdprov); ind += 1;
        if (perc == 0 && null_perc){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, perc); ind += 1;
        } 
        if (importo_min == 0 && null_importo_min){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, importo_min); ind += 1;
        } 
        pstm.setString    (ind, cdazie); ind += 1;
        pstm.setString    (ind, cddipa); ind += 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;
        pstm.setTimestamp (ind, dtinse); ind += 1;
        pstm.setTimestamp (ind, dtwebsync); ind += 1;
        if (importo_max == 0 && null_importo_max){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, importo_max); ind += 1;
        } 

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

        long ll_tk = f_tabkey.getTabkey( "cat_imptrasp" );

        return ll_tk;

    }




    /****
        executeUpdate: cat_imptrasp
    **/ 


    public int executeUpdate( long       id
                            , String     cdnazi
                            , String     cdprov
                            , double     perc
                            , double     importo_min
                            , Timestamp  dtwebsync
                            , double     importo_max
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.cat_imptrasp  \n";
        l_query  += "         set cdnazi = ?  \n";
        l_query  += "           , cdprov = ?  \n";
        l_query  += "           , perc = ?  \n";
        l_query  += "           , importo_min = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "           , dtwebsync = ?  \n";
        l_query  += "           , importo_max = ?  \n";
        l_query  += "  where id = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cdnazi.equals("")) cdnazi = null;
        if (cdprov.equals("")) cdprov = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;


        ind = 1;
        pstm.setString    (ind, cdnazi); ind += 1;
        pstm.setString    (ind, cdprov); ind += 1;
        if (perc == 0 && null_perc){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, perc); ind += 1;
        } 
        if (importo_min == 0 && null_importo_min){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, importo_min); ind += 1;
        } 
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;
        pstm.setTimestamp (ind, dtwebsync); ind += 1;
        if (importo_max == 0 && null_importo_max){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, importo_max); ind += 1;
        } 

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
    public boolean null_perc = true;
    public boolean null_importo_min = true;
    public boolean null_importo_max = true;







}

