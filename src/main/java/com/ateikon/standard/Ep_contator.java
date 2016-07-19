package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Ep_contator extends Atk_sql {

    public Ep_contator() {

        super();
    }




    /****
        getAll: ep_contator
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.ep_contator  \n";

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: ep_contator
    **/ 

    public ResultSet getKey( String     cdazie
                           , String     prname
                           , String     annoco
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.ep_contator  \n";
        l_query  += "  where cdazie = ? \n";
        l_query  += "    and prname = ? \n";
        l_query  += "    and annoco = ? \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString    (ind, cdazie); ind += 1;
        pstm.setString    (ind, prname); ind += 1;
        pstm.setString    (ind, annoco); ind += 1;

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: ep_contator
    **/ 


    public com.ateikon.structure.Rec_ep_contator  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_ep_contator lstr = new com.ateikon.structure.Rec_ep_contator();

        if (rs == null) return lstr;
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("prname")!=null) lstr.prname = rs.getString    ("prname"); 
        if (rs.getObject("annoco")!=null) lstr.annoco = rs.getString    ("annoco"); 
        if (rs.getObject("nprogr")!=null) lstr.nprogr = rs.getLong      ("nprogr"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 

        return lstr;
    }




    /****
        execute: ep_contator
    **/ 


    public int execute ( com.ateikon.structure.Rec_ep_contator astr) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec =  execute ( astr.prname
                           , astr.annoco
                          , astr.nprogr
                           );

        return tot_rec;
    }




    /****
        execute: ep_contator
    **/ 


    public int execute ( String     prname
                       , String     annoco
                       , long       nprogr
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " select cdazie\n";
        l_query  += "      , prname\n";
        l_query  += "      , annoco\n";
        l_query  += "   from pgmr.ep_contator  \n";
        l_query  += "  where cdazie = ? \n";
        l_query  += "    and prname = ? \n";
        l_query  += "    and annoco = ? \n";


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setString    (ind, cdazie); ind += 1;
        pstm.setString    (ind, prname); ind += 1;
        pstm.setString    (ind, annoco); ind += 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            pstm.close();
            tot_rec = executeUpdate ( prname
                                    , annoco
                                    , nprogr
                                    );
        }else {

            pstm.close();
            tot_rec = executeInsert ( prname
                                    , annoco
                                    , nprogr
                                    );
        }

        if (tot_rec != 1){

            m_connection.rollback();
            throw new Exception("Errore Agg. ep_contator! ");
        }

        return tot_rec;
    }




    /****
        executeInsert: ep_contator
    **/ 


    public int executeInsert( String     prname
                            , String     annoco
                            , long       nprogr
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;





        l_query   = "";
        l_query  += " insert into pgmr.ep_contator ( \n";
        l_query  += "             cdazie   \n";
        l_query  += "           , prname   \n";
        l_query  += "           , annoco   \n";
        l_query  += "           , nprogr   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (profil.equals("")) profil = null;


        ind = 1;
        pstm.setString    (ind, cdazie); ind += 1;
        pstm.setString    (ind, prname); ind += 1;
        pstm.setString    (ind, annoco); ind += 1;
        if (nprogr == 0 && null_nprogr){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, nprogr); ind += 1;
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

        long ll_tk = f_tabkey.getTabkey( "ep_contator" );

        return ll_tk;

    }




    /****
        executeUpdate: ep_contator
    **/ 


    public int executeUpdate( String     prname
                            , String     annoco
                            , long       nprogr
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.ep_contator  \n";
        l_query  += "         set nprogr = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "  where cdazie = ? \n";
        l_query  += "    and prname = ? \n";
        l_query  += "    and annoco = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (profil.equals("")) profil = null;


        ind = 1;
        if (nprogr == 0 && null_nprogr){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, nprogr); ind += 1;
        } 
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;

        pstm.setString    (ind, cdazie); ind += 1;
        pstm.setString    (ind, prname); ind += 1;
        pstm.setString    (ind, annoco); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.cdazie = cdazie; 
        this.prname = prname; 
        this.annoco = annoco; 

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public String     prname = ""; 
    public String     annoco = ""; 






    // --- Impostando a false queste prop. il def. è 0

    public boolean ib_calcola_token = true;
    public boolean null_nprogr = true;







}

