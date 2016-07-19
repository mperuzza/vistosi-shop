package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Web_keypools extends Atk_sql {

    public Web_keypools() {

        super();
    }




    /****
        getAll: web_keypools
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.web_keypools  \n";

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: web_keypools
    **/ 

    public ResultSet getKey( String     keyname
                           , String     cddipa
                           , String     cdazie
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.web_keypools  \n";
        l_query  += "  where keyname = ? \n";
        l_query  += "    and cddipa = ? \n";
        l_query  += "    and cdazie = ? \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString    (ind, keyname); ind += 1;
        pstm.setString    (ind, cddipa); ind += 1;
        pstm.setString    (ind, cdazie); ind += 1;

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: web_keypools
    **/ 


    public com.ateikon.structure.Rec_web_keypools  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_web_keypools lstr = new com.ateikon.structure.Rec_web_keypools();

        if (rs == null) return lstr;
        if (rs.getObject("keyname")!=null) lstr.keyname = rs.getString    ("keyname"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("init1")!=null) lstr.init1 = rs.getLong      ("init1"); 
        if (rs.getObject("fine1")!=null) lstr.fine1 = rs.getLong      ("fine1"); 
        if (rs.getObject("init2")!=null) lstr.init2 = rs.getLong      ("init2"); 
        if (rs.getObject("fine2")!=null) lstr.fine2 = rs.getLong      ("fine2"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 

        return lstr;
    }




    /****
        execute: web_keypools
    **/ 


    public int execute ( com.ateikon.structure.Rec_web_keypools astr) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec =  execute ( astr.keyname
                          , astr.init1
                          , astr.fine1
                          , astr.init2
                          , astr.fine2
                           );

        return tot_rec;
    }




    /****
        execute: web_keypools
    **/ 


    public int execute ( String     keyname
                       , long       init1
                       , long       fine1
                       , long       init2
                       , long       fine2
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " select keyname\n";
        l_query  += "      , cddipa\n";
        l_query  += "      , cdazie\n";
        l_query  += "   from pgmr.web_keypools  \n";
        l_query  += "  where keyname = ? \n";
        l_query  += "    and cddipa = ? \n";
        l_query  += "    and cdazie = ? \n";


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setString    (ind, keyname); ind += 1;
        pstm.setString    (ind, cddipa); ind += 1;
        pstm.setString    (ind, cdazie); ind += 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            pstm.close();
            tot_rec = executeUpdate ( keyname
                                    , init1
                                    , fine1
                                    , init2
                                    , fine2
                                    );
        }else {

            pstm.close();
            tot_rec = executeInsert ( keyname
                                    , init1
                                    , fine1
                                    , init2
                                    , fine2
                                    );
        }

        if (tot_rec != 1){

            m_connection.rollback();
            throw new Exception("Errore Agg. web_keypools! ");
        }

        return tot_rec;
    }




    /****
        executeInsert: web_keypools
    **/ 


    public int executeInsert( String     keyname
                            , long       init1
                            , long       fine1
                            , long       init2
                            , long       fine2
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;





        l_query   = "";
        l_query  += " insert into pgmr.web_keypools ( \n";
        l_query  += "             keyname   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , init1   \n";
        l_query  += "           , fine1   \n";
        l_query  += "           , init2   \n";
        l_query  += "           , fine2   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (profil.equals("")) profil = null;


        ind = 1;
        pstm.setString    (ind, keyname); ind += 1;
        pstm.setString    (ind, cddipa); ind += 1;
        pstm.setString    (ind, cdazie); ind += 1;
        if (init1 == 0 && null_init1){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, init1); ind += 1;
        } 
        if (fine1 == 0 && null_fine1){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, fine1); ind += 1;
        } 
        if (init2 == 0 && null_init2){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, init2); ind += 1;
        } 
        if (fine2 == 0 && null_fine2){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, fine2); ind += 1;
        } 
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

        long ll_tk = f_tabkey.getTabkey( "web_keypools" );

        return ll_tk;

    }




    /****
        executeUpdate: web_keypools
    **/ 


    public int executeUpdate( String     keyname
                            , long       init1
                            , long       fine1
                            , long       init2
                            , long       fine2
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.web_keypools  \n";
        l_query  += "         set init1 = ?  \n";
        l_query  += "           , fine1 = ?  \n";
        l_query  += "           , init2 = ?  \n";
        l_query  += "           , fine2 = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "  where keyname = ? \n";
        l_query  += "    and cddipa = ? \n";
        l_query  += "    and cdazie = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (profil.equals("")) profil = null;


        ind = 1;
        if (init1 == 0 && null_init1){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, init1); ind += 1;
        } 
        if (fine1 == 0 && null_fine1){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, fine1); ind += 1;
        } 
        if (init2 == 0 && null_init2){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, init2); ind += 1;
        } 
        if (fine2 == 0 && null_fine2){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, fine2); ind += 1;
        } 
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;

        pstm.setString    (ind, keyname); ind += 1;
        pstm.setString    (ind, cddipa); ind += 1;
        pstm.setString    (ind, cdazie); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.keyname = keyname; 
        this.cddipa = cddipa; 
        this.cdazie = cdazie; 

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public String     keyname = ""; 






    // --- Impostando a false queste prop. il def. è 0

    public boolean ib_calcola_token = true;
    public boolean null_init1 = true;
    public boolean null_fine1 = true;
    public boolean null_init2 = true;
    public boolean null_fine2 = true;







}

