package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Atk_alert_dest extends Atk_sql {

    public Atk_alert_dest() {

        super();
    }




    /****
        getKey: atk_alert_dest
    **/ 

    public ResultSet getKey( long       id
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.atk_alert_dest  \n";
        l_query  += "  where id = ? \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setLong      (ind, id); ind += 1;

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: atk_alert_dest
    **/ 


    public com.ateikon.structure.Rec_atk_alert_dest  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_atk_alert_dest lstr = new com.ateikon.structure.Rec_atk_alert_dest();

        if (rs == null) return lstr;
        if (rs.getObject("id")!=null) lstr.id = rs.getLong      ("id"); 
        if (rs.getObject("id_alert")!=null) lstr.id_alert = rs.getLong      ("id_alert"); 
        if (rs.getObject("cdutente")!=null) lstr.cdutente = rs.getString    ("cdutente"); 
        if (rs.getObject("email")!=null) lstr.email = rs.getString    ("email"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 

        return lstr;
    }




    /****
        execute: atk_alert_dest
    **/ 


    public int execute ( com.ateikon.structure.Rec_atk_alert_dest astr) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec =  execute ( astr.id
                          , astr.id_alert
                          , astr.cdutente
                          , astr.email
                           );

        return tot_rec;
    }




    /****
        execute: atk_alert_dest
    **/ 


    public int execute ( long       id
                       , long       id_alert
                       , String     cdutente
                       , String     email
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " select id\n";
        l_query  += "   from pgmr.atk_alert_dest  \n";
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
                                    , cdutente
                                    , email
                                    );
        }else {

            pstm.close();
            tot_rec = executeInsert ( id
                                    , id_alert
                                    , cdutente
                                    , email
                                    );
        }

        if (tot_rec != 1){

            m_connection.rollback();
            throw new Exception("Errore Agg. atk_alert_dest! ");
        }

        return tot_rec;
    }




    /****
        executeInsert: atk_alert_dest
    **/ 


    public int executeInsert( long       id
                            , long       id_alert
                            , String     cdutente
                            , String     email
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        long ll_tk = getNew_token( );

        if (ll_tk <= 0){
            throw new Exception ("Error Keypool (atk_alert_dest): "+ll_tk );
        }

        this.id = ll_tk;
        id = this.id;



        l_query   = "";
        l_query  += " insert into pgmr.atk_alert_dest ( \n";
        l_query  += "             id   \n";
        l_query  += "           , id_alert   \n";
        l_query  += "           , cdutente   \n";
        l_query  += "           , email   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cdutente.equals("")) cdutente = null;
        if (email.equals("")) email = null;
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
        pstm.setString    (ind, cdutente); ind += 1;
        pstm.setString    (ind, email); ind += 1;
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

        long ll_tk = f_tabkey.getTabkey( "atk_alert_dest" );

        return ll_tk;

    }




    /****
        executeUpdate: atk_alert_dest
    **/ 


    public int executeUpdate( long       id
                            , long       id_alert
                            , String     cdutente
                            , String     email
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.atk_alert_dest  \n";
        l_query  += "         set id_alert = ?  \n";
        l_query  += "           , cdutente = ?  \n";
        l_query  += "           , email = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "  where id = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cdutente.equals("")) cdutente = null;
        if (email.equals("")) email = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;


        ind = 1;
        if (id_alert == 0 && null_id_alert){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, id_alert); ind += 1;
        } 
        pstm.setString    (ind, cdutente); ind += 1;
        pstm.setString    (ind, email); ind += 1;
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







}

