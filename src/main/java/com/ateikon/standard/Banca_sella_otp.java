package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Banca_sella_otp extends Atk_sql {

    public Banca_sella_otp() {

        super();
    }




    /****
        getKey: banca_sella_otp
    **/ 

    public ResultSet getKey( long       tkotp
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.banca_sella_otp  \n";
        l_query  += "  where tkotp = ? \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setLong      (ind, tkotp); ind += 1;

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: banca_sella_otp
    **/ 


    public com.ateikon.structure.Rec_banca_sella_otp  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_banca_sella_otp lstr = new com.ateikon.structure.Rec_banca_sella_otp();

        if (rs == null) return lstr;
        if (rs.getObject("tkotp")!=null) lstr.tkotp = rs.getLong      ("tkotp"); 
        if (rs.getObject("otp")!=null) lstr.otp = rs.getString    ("otp"); 
        if (rs.getObject("tipo_otp")!=null) lstr.tipo_otp = rs.getString    ("tipo_otp"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("dtdel")!=null) lstr.dtdel = rs.getTimestamp ("dtdel"); 
        if (rs.getObject("filename")!=null) lstr.filename = rs.getString    ("filename"); 

        return lstr;
    }




    /****
        execute: banca_sella_otp
    **/ 


    public int execute ( com.ateikon.structure.Rec_banca_sella_otp astr) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec =  execute ( astr.tkotp
                          , astr.otp
                          , astr.tipo_otp
                          , astr.dtdel
                          , astr.filename
                           );

        return tot_rec;
    }




    /****
        execute: banca_sella_otp
    **/ 


    public int execute ( long       tkotp
                       , String     otp
                       , String     tipo_otp
                       , Timestamp  dtdel
                       , String     filename
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " select tkotp\n";
        l_query  += "   from pgmr.banca_sella_otp  \n";
        l_query  += "  where tkotp = ? \n";


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setLong      (ind, tkotp); ind += 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            pstm.close();
            tot_rec = executeUpdate ( tkotp
                                    , otp
                                    , tipo_otp
                                    , dtdel
                                    , filename
                                    );
        }else {

            pstm.close();
            tot_rec = executeInsert ( tkotp
                                    , otp
                                    , tipo_otp
                                    , dtdel
                                    , filename
                                    );
        }

        if (tot_rec != 1){

            m_connection.rollback();
            throw new Exception("Errore Agg. banca_sella_otp! ");
        }

        return tot_rec;
    }




    /****
        executeInsert: banca_sella_otp
    **/ 


    public int executeInsert( long       tkotp
                            , String     otp
                            , String     tipo_otp
                            , Timestamp  dtdel
                            , String     filename
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        long ll_tk = getNew_token( );

        if (ll_tk <= 0){
            throw new Exception ("Error Keypool (banca_sella_otp): "+ll_tk );
        }

        this.tkotp = ll_tk;
        tkotp = this.tkotp;



        l_query   = "";
        l_query  += " insert into pgmr.banca_sella_otp ( \n";
        l_query  += "             tkotp   \n";
        l_query  += "           , otp   \n";
        l_query  += "           , tipo_otp   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "           , dtdel   \n";
        l_query  += "           , filename   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (otp.equals("")) otp = null;
        if (tipo_otp.equals("")) tipo_otp = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;
        if (filename.equals("")) filename = null;


        ind = 1;
        pstm.setLong      (ind, tkotp); ind += 1;
        pstm.setString    (ind, otp); ind += 1;
        pstm.setString    (ind, tipo_otp); ind += 1;
        pstm.setString    (ind, cdazie); ind += 1;
        pstm.setString    (ind, cddipa); ind += 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtinse); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;
        pstm.setTimestamp (ind, dtdel); ind += 1;
        pstm.setString    (ind, filename); ind += 1;

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

        long ll_tk = f_tabkey.getTabkey( "banca_sella_otp" );

        return ll_tk;

    }




    /****
        executeUpdate: banca_sella_otp
    **/ 


    public int executeUpdate( long       tkotp
                            , String     otp
                            , String     tipo_otp
                            , Timestamp  dtdel
                            , String     filename
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.banca_sella_otp  \n";
        l_query  += "         set otp = ?  \n";
        l_query  += "           , tipo_otp = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "           , dtdel = ?  \n";
        l_query  += "           , filename = ?  \n";
        l_query  += "  where tkotp = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (otp.equals("")) otp = null;
        if (tipo_otp.equals("")) tipo_otp = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;
        if (filename.equals("")) filename = null;


        ind = 1;
        pstm.setString    (ind, otp); ind += 1;
        pstm.setString    (ind, tipo_otp); ind += 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;
        pstm.setTimestamp (ind, dtdel); ind += 1;
        pstm.setString    (ind, filename); ind += 1;
        pstm.setLong      (ind, tkotp); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.tkotp = tkotp; 

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public long       tkotp = 0; 







}

