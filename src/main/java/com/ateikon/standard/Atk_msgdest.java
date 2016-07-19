package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Atk_msgdest extends Atk_sql {

    public Atk_msgdest() {

        super();
    }




    /****
        getAll: atk_msgdest
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.atk_msgdest  \n";

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: atk_msgdest
    **/ 

    public ResultSet getKey( long       tkmsg
                           , String     dest
                           , String     tipodest
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.atk_msgdest  \n";
        if (tkmsg == 0 && null_tkmsg){ 
            l_query  += "  where tkmsg is null \n";
        }else { 
            l_query  += "  where tkmsg = ?  \n";
        } 
        if (dest.equals("")){ 
            l_query  += "  and dest is null  \n";
        }else { 
            l_query  += "  and dest = ?  \n";
        } 
        if (tipodest.equals("")){ 
            l_query  += "  and tipodest is null  \n";
        }else { 
            l_query  += "  and tipodest = ?  \n";
        } 

        pstm = setQuery( l_query ) ;

        ind = 1;
        if (tkmsg == 0 && null_tkmsg){ 
        }else { 
            pstm.setLong      (ind, tkmsg); ind += 1;
        } 
        if (dest.equals("")){ 
        }else { 
            pstm.setString    (ind, dest); ind += 1;
        } 
        if (tipodest.equals("")){ 
        }else { 
            pstm.setString    (ind, tipodest); ind += 1;
        } 

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: atk_msgdest
    **/ 


    public com.ateikon.structure.Rec_atk_msgdest  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_atk_msgdest lstr = new com.ateikon.structure.Rec_atk_msgdest();

        if (rs == null) return lstr;
        if (rs.getObject("tkmsg")!=null) lstr.tkmsg = rs.getLong      ("tkmsg"); 
        if (rs.getObject("dest")!=null) lstr.dest = rs.getString    ("dest"); 
        if (rs.getObject("tipodest")!=null) lstr.tipodest = rs.getString    ("tipodest"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("tkmlp")!=null) lstr.tkmlp = rs.getLong      ("tkmlp"); 

        return lstr;
    }




    /****
        preupdate: atk_msgdest

        sovrascrivere per impostare i controlli da effetuare prima dell'execute
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_atk_msgdest astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }





    /****
        preupdate: atk_msgdest

        sovrascrivere per impostare i controlli da effetuare per sincro

        questo metodo � stato introdotto per la sincro: in fase di export prima  
        di scrivere il file XML viene chiamato
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_atk_msgdest astr, ResultSet rs) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }



    /****
        execute: atk_msgdest
    **/ 


    public int execute ( com.ateikon.structure.Rec_atk_msgdest astr) throws Exception {
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
        l_query  += " select tkmsg\n";
        l_query  += "      , dest\n";
        l_query  += "      , tipodest\n";
        l_query  += "   from pgmr.atk_msgdest  \n";
        if (astr.tkmsg == 0 && null_tkmsg){ 
            l_query  += "  where tkmsg is null \n";
        }else { 
            l_query  += "  where tkmsg = ?  \n";
        } 
        if (astr.dest.equals("")){ 
            l_query  += "  and dest is null  \n";
        }else { 
            l_query  += "  and dest = ?  \n";
        } 
        if (astr.tipodest.equals("")){ 
            l_query  += "  and tipodest is null  \n";
        }else { 
            l_query  += "  and tipodest = ?  \n";
        } 


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (astr.tkmsg == 0 && null_tkmsg){ 
        }else { 
            pstm.setLong      (ind, astr.tkmsg); ind += 1;
        } 
        if (astr.dest.equals("")){ 
        }else { 
            pstm.setString    (ind, astr.dest); ind += 1;
        } 
        if (astr.tipodest.equals("")){ 
        }else { 
            pstm.setString    (ind, astr.tipodest); ind += 1;
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
            throw new Exception("Errore Agg. atk_msgdest! ");
        }
        */ 

        return tot_rec;
    }




    /****
        executeInsert: atk_msgdest
    **/ 


    public int executeInsert( com.ateikon.structure.Rec_atk_msgdest astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;





        l_query   = "";
        l_query  += " insert into pgmr.atk_msgdest ( \n";
        l_query  += "             tkmsg   \n";
        l_query  += "           , dest   \n";
        l_query  += "           , tipodest   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "           , tkmlp   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;


        ind = 1;
        if (astr.tkmsg == 0 && null_tkmsg){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkmsg); ind += 1;
        } 
        pstm.setString    (ind, astr.dest); ind += 1;
        pstm.setString    (ind, astr.tipodest); ind += 1;
        pstm.setString    (ind, astr.cdazie); ind += 1;
        pstm.setString    (ind, astr.cddipa); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtinse); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;
        if (astr.tkmlp == 0 && null_tkmlp){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkmlp); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        // --- Ripristino le Stringhe NULL

        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil == null) astr.profil = "";


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

        long ll_tk = f_tabkey.getTabkey( "atk_msgdest" );

        return ll_tk;

    }




    /****
        executeUpdate: atk_msgdest
    **/ 


    public int executeUpdate( com.ateikon.structure.Rec_atk_msgdest astr ) throws Exception { 
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.atk_msgdest  \n";
        l_query  += "         set profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "           , tkmlp = ?  \n";
        l_query  += "  where tkmsg = ? \n";
        l_query  += "    and dest = ? \n";
        l_query  += "    and tipodest = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;


        ind = 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;
        if (astr.tkmlp == 0 && null_tkmlp){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkmlp); ind += 1;
        } 

        if (astr.tkmsg == 0 && null_tkmsg){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkmsg); ind += 1;
        } 
        if (astr.dest.equals("")){ 
            pstm.setString    (ind, null); ind += 1;
        }else { 
            pstm.setString    (ind, astr.dest); ind += 1;
        } 
        if (astr.tipodest.equals("")){ 
            pstm.setString    (ind, null); ind += 1;
        }else { 
            pstm.setString    (ind, astr.tipodest); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.tkmsg = astr.tkmsg; 
        this.dest = astr.dest; 
        this.tipodest = astr.tipodest; 

        // --- Ripristino le Stringhe NULL

        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil == null) astr.profil = "";


        return tot_rec;

    }



    /****
        getKey: atk_msgdest
    **/ 

    public int deleteKey( long       tkmsg
                           , String     dest
                           , String     tipodest
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " delete \n";
        l_query  += "   from pgmr.atk_msgdest  \n";
        if (tkmsg == 0 && null_tkmsg){ 
            l_query  += "  where tkmsg is null \n";
        }else { 
            l_query  += "  where tkmsg = ?  \n";
        } 
        if (dest.equals("")){ 
            l_query  += "  and dest is null  \n";
        }else { 
            l_query  += "  and dest = ?  \n";
        } 
        if (tipodest.equals("")){ 
            l_query  += "  and tipodest is null  \n";
        }else { 
            l_query  += "  and tipodest = ?  \n";
        } 

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (tkmsg == 0 && null_tkmsg){ 
        }else { 
            pstm.setLong      (ind, tkmsg); ind += 1;
        } 
        if (dest.equals("")){ 
        }else { 
            pstm.setString    (ind, dest); ind += 1;
        } 
        if (tipodest.equals("")){ 
        }else { 
            pstm.setString    (ind, tipodest); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();
        pstm.close();

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public long       tkmsg = 0; 
    public String     dest = ""; 
    public String     tipodest = ""; 






    // --- Impostando a false queste prop. il def. � 0

    public boolean ib_calcola_token = true;
    public boolean ib_ctrl_mindate = false;
    public boolean null_tkmsg = true;
    public boolean null_tkmlp = true;





}

