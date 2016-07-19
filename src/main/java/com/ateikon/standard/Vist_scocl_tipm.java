package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Vist_scocl_tipm extends Atk_sql {

    public Vist_scocl_tipm() {

        super();
    }




    /****
        getAll: vist_scocl_tipm
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.vist_scocl_tipm  \n";
        if (is_farza_filtro_dipa.equals("S")){
            l_query  += "  where cddipa = '"+cddipa+"'  \n";
        }

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: vist_scocl_tipm
    **/ 

    public ResultSet getKey( String     tkclie
                           , String     cdtipm
                           , Timestamp  dtinva
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.vist_scocl_tipm  \n";
        if (tkclie.equals("")){ 
            l_query  += "  where tkclie is null  \n";
        }else { 
            l_query  += "  where tkclie = ?  \n";
        } 
        if (cdtipm.equals("")){ 
            l_query  += "  and cdtipm is null  \n";
        }else { 
            l_query  += "  and cdtipm = ?  \n";
        } 
        if (dtinva == null){ 
            l_query  += "  and dtinva is null  \n";
        }else { 
            l_query  += "  and dtinva = ?  \n";
        } 

        pstm = setQuery( l_query ) ;

        ind = 1;
        if (tkclie.equals("")){ 
        }else { 
            pstm.setString    (ind, tkclie); ind += 1;
        } 
        if (cdtipm.equals("")){ 
        }else { 
            pstm.setString    (ind, cdtipm); ind += 1;
        } 
        if (dtinva == null){ 
        }else { 
            pstm.setTimestamp (ind, dtinva); ind += 1;
        } 

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: vist_scocl_tipm
    **/ 


    public com.ateikon.structure.Rec_vist_scocl_tipm  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_vist_scocl_tipm lstr = new com.ateikon.structure.Rec_vist_scocl_tipm();

        if (rs == null) return lstr;
        if (rs.getObject("tkclie")!=null) lstr.tkclie = rs.getString    ("tkclie"); 
        if (rs.getObject("cdtipm")!=null) lstr.cdtipm = rs.getString    ("cdtipm"); 
        if (rs.getObject("dtinva")!=null) lstr.dtinva = rs.getTimestamp ("dtinva"); 
        if (rs.getObject("dtfval")!=null) lstr.dtfval = rs.getTimestamp ("dtfval"); 
        if (rs.getObject("sconto1")!=null) lstr.sconto1 = rs.getDouble    ("sconto1"); 
        if (rs.getObject("sconto2")!=null) lstr.sconto2 = rs.getDouble    ("sconto2"); 
        if (rs.getObject("sconto3")!=null) lstr.sconto3 = rs.getDouble    ("sconto3"); 
        if (rs.getObject("sconto4")!=null) lstr.sconto4 = rs.getDouble    ("sconto4"); 
        if (rs.getObject("sconto5")!=null) lstr.sconto5 = rs.getDouble    ("sconto5"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("fg_agg")!=null) lstr.fg_agg = rs.getString    ("fg_agg"); 

        return lstr;
    }




    /****
        preupdate: vist_scocl_tipm

        sovrascrivere per impostare i controlli da effetuare prima dell'execute
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_vist_scocl_tipm astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }





    /****
        preupdate: vist_scocl_tipm

        sovrascrivere per impostare i controlli da effetuare per sincro

        questo metodo ï¿½ stato introdotto per la sincro: in fase di export prima  
        di scrivere il file XML viene chiamato
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_vist_scocl_tipm astr, ResultSet rs) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }



    /****
        execute: vist_scocl_tipm
    **/ 


    public int execute ( com.ateikon.structure.Rec_vist_scocl_tipm astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        // --- Controllo che il profilo sia valorizzato

        if (astr.cdazie.equals("")) astr.cdazie = cdazie;
        if (astr.cddipa.equals("")) astr.cddipa = cddipa;
        if (astr.profil.equals("")) astr.profil = profil;
        if (astr.dtinse == null) astr.dtinse = of_getTimestamp();
        if (astr.dtulag == null) astr.dtulag = of_getTimestamp();

        if (ib_ctrl_mindate){
            astr.ctrl_mindate();
        }

        preupdate(astr);

        l_query   = "";
        l_query  += " select tkclie\n";
        l_query  += "      , cdtipm\n";
        l_query  += "      , dtinva\n";
        l_query  += "   from pgmr.vist_scocl_tipm  \n";
        if (astr.tkclie.equals("")){ 
            l_query  += "  where tkclie is null  \n";
        }else { 
            l_query  += "  where tkclie = ?  \n";
        } 
        if (astr.cdtipm.equals("")){ 
            l_query  += "  and cdtipm is null  \n";
        }else { 
            l_query  += "  and cdtipm = ?  \n";
        } 
        if (astr.dtinva == null){ 
            l_query  += "  and dtinva is null  \n";
        }else { 
            l_query  += "  and dtinva = ?  \n";
        } 


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (astr.tkclie.equals("")){ 
        }else { 
            pstm.setString    (ind, astr.tkclie); ind += 1;
        } 
        if (astr.cdtipm.equals("")){ 
        }else { 
            pstm.setString    (ind, astr.cdtipm); ind += 1;
        } 
        if (astr.dtinva == null){ 
        }else { 
            pstm.setTimestamp (ind, astr.dtinva); ind += 1;
        } 

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            pstm.close();

            // --- aggiorno il profilo
            astr.dtulag = of_getTimestamp();
            astr.profil = profil;


            tot_rec = executeUpdate (astr);
        }else {

            pstm.close();
            tot_rec = executeInsert (astr);
        }

        /* lsp 200904 
        if (tot_rec != 1){

            m_connection.rollback();
            throw new Exception("Errore Agg. vist_scocl_tipm! ");
        }
        */ 

        return tot_rec;
    }




    /****
        executeInsert: vist_scocl_tipm
    **/ 


    public int executeInsert( com.ateikon.structure.Rec_vist_scocl_tipm astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;





        l_query   = "";
        l_query  += " insert into pgmr.vist_scocl_tipm ( \n";
        l_query  += "             tkclie   \n";
        l_query  += "           , cdtipm   \n";
        l_query  += "           , dtinva   \n";
        l_query  += "           , dtfval   \n";
        l_query  += "           , sconto1   \n";
        l_query  += "           , sconto2   \n";
        l_query  += "           , sconto3   \n";
        l_query  += "           , sconto4   \n";
        l_query  += "           , sconto5   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "           , fg_agg   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;
        if (astr.fg_agg.equals("")) astr.fg_agg = null;


        ind = 1;
        pstm.setString    (ind, astr.tkclie); ind += 1;
        pstm.setString    (ind, astr.cdtipm); ind += 1;
        pstm.setTimestamp (ind, astr.dtinva); ind += 1;
        pstm.setTimestamp (ind, astr.dtfval); ind += 1;
        if (astr.sconto1 == 0 && null_sconto1){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, astr.sconto1); ind += 1;
        } 
        if (astr.sconto2 == 0 && null_sconto2){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, astr.sconto2); ind += 1;
        } 
        if (astr.sconto3 == 0 && null_sconto3){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, astr.sconto3); ind += 1;
        } 
        if (astr.sconto4 == 0 && null_sconto4){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, astr.sconto4); ind += 1;
        } 
        if (astr.sconto5 == 0 && null_sconto5){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, astr.sconto5); ind += 1;
        } 
        pstm.setString    (ind, astr.cdazie); ind += 1;
        pstm.setString    (ind, astr.cddipa); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtinse); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;
        pstm.setString    (ind, astr.fg_agg); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        // --- Ripristino le Stringhe NULL

        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil == null) astr.profil = "";
        if (astr.fg_agg == null) astr.fg_agg = "";


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

        long ll_tk = f_tabkey.getTabkey( "vist_scocl_tipm" );

        return ll_tk;

    }




    /****
        executeUpdate: vist_scocl_tipm
    **/ 


    public int executeUpdate( com.ateikon.structure.Rec_vist_scocl_tipm astr ) throws Exception { 
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.vist_scocl_tipm  \n";
        l_query  += "         set dtfval = ?  \n";
        l_query  += "           , sconto1 = ?  \n";
        l_query  += "           , sconto2 = ?  \n";
        l_query  += "           , sconto3 = ?  \n";
        l_query  += "           , sconto4 = ?  \n";
        l_query  += "           , sconto5 = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "           , fg_agg = ?  \n";
        l_query  += "  where tkclie = ? \n";
        l_query  += "    and cdtipm = ? \n";
        l_query  += "    and dtinva = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;
        if (astr.fg_agg.equals("")) astr.fg_agg = null;


        ind = 1;
        pstm.setTimestamp (ind, astr.dtfval); ind += 1;
        if (astr.sconto1 == 0 && null_sconto1){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, astr.sconto1); ind += 1;
        } 
        if (astr.sconto2 == 0 && null_sconto2){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, astr.sconto2); ind += 1;
        } 
        if (astr.sconto3 == 0 && null_sconto3){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, astr.sconto3); ind += 1;
        } 
        if (astr.sconto4 == 0 && null_sconto4){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, astr.sconto4); ind += 1;
        } 
        if (astr.sconto5 == 0 && null_sconto5){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, astr.sconto5); ind += 1;
        } 
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;
        pstm.setString    (ind, astr.fg_agg); ind += 1;

        if (astr.tkclie.equals("")){ 
            pstm.setString    (ind, null); ind += 1;
        }else { 
            pstm.setString    (ind, astr.tkclie); ind += 1;
        } 
        if (astr.cdtipm.equals("")){ 
            pstm.setString    (ind, null); ind += 1;
        }else { 
            pstm.setString    (ind, astr.cdtipm); ind += 1;
        } 
        pstm.setTimestamp (ind, astr.dtinva); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.tkclie = astr.tkclie; 
        this.cdtipm = astr.cdtipm; 
        this.dtinva = astr.dtinva; 

        // --- Ripristino le Stringhe NULL

        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil == null) astr.profil = "";
        if (astr.fg_agg == null) astr.fg_agg = "";


        return tot_rec;

    }



    /****
        getKey: vist_scocl_tipm
    **/ 

    public int deleteKey( String     tkclie
                           , String     cdtipm
                           , Timestamp  dtinva
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " delete \n";
        l_query  += "   from pgmr.vist_scocl_tipm  \n";
        if (tkclie.equals("")){ 
            l_query  += "  where tkclie is null  \n";
        }else { 
            l_query  += "  where tkclie = ?  \n";
        } 
        if (cdtipm.equals("")){ 
            l_query  += "  and cdtipm is null  \n";
        }else { 
            l_query  += "  and cdtipm = ?  \n";
        } 
        if (dtinva == null){ 
            l_query  += "  and dtinva is null  \n";
        }else { 
            l_query  += "  and dtinva = ?  \n";
        } 

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (tkclie.equals("")){ 
        }else { 
            pstm.setString    (ind, tkclie); ind += 1;
        } 
        if (cdtipm.equals("")){ 
        }else { 
            pstm.setString    (ind, cdtipm); ind += 1;
        } 
        if (dtinva == null){ 
        }else { 
            pstm.setTimestamp (ind, dtinva); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();
        pstm.close();

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public String     tkclie = ""; 
    public String     cdtipm = ""; 
    public Timestamp  dtinva = null; 






    // --- Impostando a false queste prop. il def. ï¿½ 0

    public boolean ib_calcola_token = true;
    public boolean ib_ctrl_mindate = false;
    public boolean null_sconto1 = true;
    public boolean null_sconto2 = true;
    public boolean null_sconto3 = true;
    public boolean null_sconto4 = true;
    public boolean null_sconto5 = true;




    public String is_farza_filtro_dipa = "N";



}

