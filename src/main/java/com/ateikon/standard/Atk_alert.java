package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Atk_alert extends Atk_sql {

    public Atk_alert() {

        super();
    }




    /****
        getKey: atk_alert
    **/ 

    public ResultSet getKey( long       id
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.atk_alert  \n";
        l_query  += "  where id = ? \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setLong      (ind, id); ind += 1;

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: atk_alert
    **/ 


    public com.ateikon.structure.Rec_atk_alert  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_atk_alert lstr = new com.ateikon.structure.Rec_atk_alert();

        if (rs == null) return lstr;
        if (rs.getObject("id")!=null) lstr.id = rs.getLong      ("id"); 
        if (rs.getObject("cdalert_m")!=null) lstr.cdalert_m = rs.getString    ("cdalert_m"); 
        if (rs.getObject("descr")!=null) lstr.descr = rs.getString    ("descr"); 
        if (rs.getObject("cdutente")!=null) lstr.cdutente = rs.getString    ("cdutente"); 
        if (rs.getObject("dtfiltro")!=null) lstr.dtfiltro = rs.getTimestamp ("dtfiltro"); 
        if (rs.getObject("dtalert")!=null) lstr.dtalert = rs.getTimestamp ("dtalert"); 
        if (rs.getObject("dtini")!=null) lstr.dtini = rs.getTimestamp ("dtini"); 
        if (rs.getObject("dtfine")!=null) lstr.dtfine = rs.getTimestamp ("dtfine"); 
        if (rs.getObject("id_class")!=null) lstr.id_class = rs.getLong      ("id_class"); 
        if (rs.getObject("id_sched")!=null) lstr.id_sched = rs.getLong      ("id_sched"); 
        if (rs.getObject("dtsched_i")!=null) lstr.dtsched_i = rs.getTimestamp ("dtsched_i"); 
        if (rs.getObject("dtsched_f")!=null) lstr.dtsched_f = rs.getTimestamp ("dtsched_f"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 

        return lstr;
    }




    /****
        execute: atk_alert
    **/ 


    public int execute ( com.ateikon.structure.Rec_atk_alert astr) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec =  execute ( astr.id
                          , astr.cdalert_m
                          , astr.descr
                          , astr.cdutente
                          , astr.dtfiltro
                          , astr.dtalert
                          , astr.dtini
                          , astr.dtfine
                          , astr.id_class
                          , astr.id_sched
                          , astr.dtsched_i
                          , astr.dtsched_f
                           );

        return tot_rec;
    }




    /****
        execute: atk_alert
    **/ 


    public int execute ( long       id
                       , String     cdalert_m
                       , String     descr
                       , String     cdutente
                       , Timestamp  dtfiltro
                       , Timestamp  dtalert
                       , Timestamp  dtini
                       , Timestamp  dtfine
                       , long       id_class
                       , long       id_sched
                       , Timestamp  dtsched_i
                       , Timestamp  dtsched_f
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " select id\n";
        l_query  += "   from pgmr.atk_alert  \n";
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
                                    , cdalert_m
                                    , descr
                                    , cdutente
                                    , dtfiltro
                                    , dtalert
                                    , dtini
                                    , dtfine
                                    , id_class
                                    , id_sched
                                    , dtsched_i
                                    , dtsched_f
                                    );
        }else {

            pstm.close();
            tot_rec = executeInsert ( id
                                    , cdalert_m
                                    , descr
                                    , cdutente
                                    , dtfiltro
                                    , dtalert
                                    , dtini
                                    , dtfine
                                    , id_class
                                    , id_sched
                                    , dtsched_i
                                    , dtsched_f
                                    );
        }

        if (tot_rec != 1){

            m_connection.rollback();
            throw new Exception("Errore Agg. atk_alert! ");
        }

        return tot_rec;
    }




    /****
        executeInsert: atk_alert
    **/ 


    public int executeInsert( long       id
                            , String     cdalert_m
                            , String     descr
                            , String     cdutente
                            , Timestamp  dtfiltro
                            , Timestamp  dtalert
                            , Timestamp  dtini
                            , Timestamp  dtfine
                            , long       id_class
                            , long       id_sched
                            , Timestamp  dtsched_i
                            , Timestamp  dtsched_f
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        long ll_tk = getNew_token( );

        if (ll_tk <= 0){
            throw new Exception ("Error Keypool (atk_alert): "+ll_tk );
        }

        this.id = ll_tk;
        id = this.id;



        l_query   = "";
        l_query  += " insert into pgmr.atk_alert ( \n";
        l_query  += "             id   \n";
        l_query  += "           , cdalert_m   \n";
        l_query  += "           , descr   \n";
        l_query  += "           , cdutente   \n";
        l_query  += "           , dtfiltro   \n";
        l_query  += "           , dtalert   \n";
        l_query  += "           , dtini   \n";
        l_query  += "           , dtfine   \n";
        l_query  += "           , id_class   \n";
        l_query  += "           , id_sched   \n";
        l_query  += "           , dtsched_i   \n";
        l_query  += "           , dtsched_f   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cdalert_m.equals("")) cdalert_m = null;
        if (descr.equals("")) descr = null;
        if (cdutente.equals("")) cdutente = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;


        ind = 1;
        if (id == 0 && null_id){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, id); ind += 1;
        } 
        pstm.setString    (ind, cdalert_m); ind += 1;
        pstm.setString    (ind, descr); ind += 1;
        pstm.setString    (ind, cdutente); ind += 1;
        pstm.setTimestamp (ind, dtfiltro); ind += 1;
        pstm.setTimestamp (ind, dtalert); ind += 1;
        pstm.setTimestamp (ind, dtini); ind += 1;
        pstm.setTimestamp (ind, dtfine); ind += 1;
        if (id_class == 0 && null_id_class){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, id_class); ind += 1;
        } 
        if (id_sched == 0 && null_id_sched){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, id_sched); ind += 1;
        } 
        pstm.setTimestamp (ind, dtsched_i); ind += 1;
        pstm.setTimestamp (ind, dtsched_f); ind += 1;
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

        long ll_tk = f_tabkey.getTabkey( "atk_alert" );

        return ll_tk;

    }




    /****
        executeUpdate: atk_alert
    **/ 


    public int executeUpdate( long       id
                            , String     cdalert_m
                            , String     descr
                            , String     cdutente
                            , Timestamp  dtfiltro
                            , Timestamp  dtalert
                            , Timestamp  dtini
                            , Timestamp  dtfine
                            , long       id_class
                            , long       id_sched
                            , Timestamp  dtsched_i
                            , Timestamp  dtsched_f
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.atk_alert  \n";
        l_query  += "         set cdalert_m = ?  \n";
        l_query  += "           , descr = ?  \n";
        l_query  += "           , cdutente = ?  \n";
        l_query  += "           , dtfiltro = ?  \n";
        l_query  += "           , dtalert = ?  \n";
        l_query  += "           , dtini = ?  \n";
        l_query  += "           , dtfine = ?  \n";
        l_query  += "           , id_class = ?  \n";
        l_query  += "           , id_sched = ?  \n";
        l_query  += "           , dtsched_i = ?  \n";
        l_query  += "           , dtsched_f = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "  where id = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cdalert_m.equals("")) cdalert_m = null;
        if (descr.equals("")) descr = null;
        if (cdutente.equals("")) cdutente = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;


        ind = 1;
        pstm.setString    (ind, cdalert_m); ind += 1;
        pstm.setString    (ind, descr); ind += 1;
        pstm.setString    (ind, cdutente); ind += 1;
        pstm.setTimestamp (ind, dtfiltro); ind += 1;
        pstm.setTimestamp (ind, dtalert); ind += 1;
        pstm.setTimestamp (ind, dtini); ind += 1;
        pstm.setTimestamp (ind, dtfine); ind += 1;
        if (id_class == 0 && null_id_class){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, id_class); ind += 1;
        } 
        if (id_sched == 0 && null_id_sched){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, id_sched); ind += 1;
        } 
        pstm.setTimestamp (ind, dtsched_i); ind += 1;
        pstm.setTimestamp (ind, dtsched_f); ind += 1;
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
    public boolean null_id_class = true;
    public boolean null_id_sched = true;







}

