package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Vist_mpron_posi extends Atk_sql {

    public Vist_mpron_posi() {

        super();
    }




    /****
        getAll: vist_mpron_posi
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.vist_mpron_posi  \n";
        if (is_farza_filtro_dipa.equals("S")){
            l_query  += "  where cddipa = '"+cddipa+"'  \n";
        }

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: vist_mpron_posi
    **/ 

    public ResultSet getKey( long       tkmpronp
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.vist_mpron_posi  \n";
        if (tkmpronp == 0 && null_tkmpronp){ 
            l_query  += "  where tkmpronp is null \n";
        }else { 
            l_query  += "  where tkmpronp = ?  \n";
        } 

        pstm = setQuery( l_query ) ;

        ind = 1;
        if (tkmpronp == 0 && null_tkmpronp){ 
        }else { 
            pstm.setLong      (ind, tkmpronp); ind += 1;
        } 

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: vist_mpron_posi
    **/ 


    public com.ateikon.structure.Rec_vist_mpron_posi  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_vist_mpron_posi lstr = new com.ateikon.structure.Rec_vist_mpron_posi();

        if (rs == null) return lstr;
        if (rs.getObject("tkmpronp")!=null) lstr.tkmpronp = rs.getLong      ("tkmpronp"); 
        if (rs.getObject("tkmpron")!=null) lstr.tkmpron = rs.getLong      ("tkmpron"); 
        if (rs.getObject("dtdoc")!=null) lstr.dtdoc = rs.getTimestamp ("dtdoc"); 
        if (rs.getObject("nrdoc")!=null) lstr.nrdoc = rs.getString    ("nrdoc"); 
        if (rs.getObject("importo")!=null) lstr.importo = rs.getDouble    ("importo"); 
        if (rs.getObject("nota")!=null) lstr.nota = rs.getString    ("nota"); 
        if (rs.getObject("vacodi")!=null) lstr.vacodi = rs.getString    ("vacodi"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("dtdel")!=null) lstr.dtdel = rs.getTimestamp ("dtdel"); 
        if (rs.getObject("nrposi")!=null) lstr.nrposi = rs.getLong      ("nrposi"); 

        return lstr;
    }




    /****
        preupdate: vist_mpron_posi

        sovrascrivere per impostare i controlli da effetuare prima dell'execute
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_vist_mpron_posi astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }





    /****
        preupdate: vist_mpron_posi

        sovrascrivere per impostare i controlli da effetuare per sincro

        questo metodo ï¿½ stato introdotto per la sincro: in fase di export prima  
        di scrivere il file XML viene chiamato
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_vist_mpron_posi astr, ResultSet rs) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }



    /****
        execute: vist_mpron_posi
    **/ 


    public int execute ( com.ateikon.structure.Rec_vist_mpron_posi astr) throws Exception {
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
        l_query  += " select tkmpronp\n";
        l_query  += "   from pgmr.vist_mpron_posi  \n";
        if (astr.tkmpronp == 0 && null_tkmpronp){ 
            l_query  += "  where tkmpronp is null \n";
        }else { 
            l_query  += "  where tkmpronp = ?  \n";
        } 


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (astr.tkmpronp == 0 && null_tkmpronp){ 
        }else { 
            pstm.setLong      (ind, astr.tkmpronp); ind += 1;
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
            throw new Exception("Errore Agg. vist_mpron_posi! ");
        }
        */ 

        return tot_rec;
    }




    /****
        executeInsert: vist_mpron_posi
    **/ 


    public int executeInsert( com.ateikon.structure.Rec_vist_mpron_posi astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        if (ib_calcola_token){
            long ll_tk = getNew_token( );

            if (ll_tk <= 0){
                throw new Exception ("Error Keypool (vist_mpron_posi): "+ll_tk );
            }

            this.tkmpronp = ll_tk;
            astr.tkmpronp = this.tkmpronp;
        }else {
            this.tkmpronp = astr.tkmpronp;
        }



        l_query   = "";
        l_query  += " insert into pgmr.vist_mpron_posi ( \n";
        l_query  += "             tkmpronp   \n";
        l_query  += "           , tkmpron   \n";
        l_query  += "           , dtdoc   \n";
        l_query  += "           , nrdoc   \n";
        l_query  += "           , importo   \n";
        l_query  += "           , nota   \n";
        l_query  += "           , vacodi   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "           , dtdel   \n";
        l_query  += "           , nrposi   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.nrdoc.equals("")) astr.nrdoc = null;
        if (astr.nota.equals("")) astr.nota = null;
        if (astr.vacodi.equals("")) astr.vacodi = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;


        ind = 1;
        if (astr.tkmpronp == 0 && null_tkmpronp){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkmpronp); ind += 1;
        } 
        if (astr.tkmpron == 0 && null_tkmpron){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkmpron); ind += 1;
        } 
        pstm.setTimestamp (ind, astr.dtdoc); ind += 1;
        pstm.setString    (ind, astr.nrdoc); ind += 1;
        if (astr.importo == 0 && null_importo){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, astr.importo); ind += 1;
        } 
        pstm.setString    (ind, astr.nota); ind += 1;
        pstm.setString    (ind, astr.vacodi); ind += 1;
        pstm.setString    (ind, astr.cdazie); ind += 1;
        pstm.setString    (ind, astr.cddipa); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtinse); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;
        pstm.setTimestamp (ind, astr.dtdel); ind += 1;
        if (astr.nrposi == 0 && null_nrposi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.nrposi); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        // --- Ripristino le Stringhe NULL

        if (astr.nrdoc == null) astr.nrdoc = "";
        if (astr.nota == null) astr.nota = "";
        if (astr.vacodi == null) astr.vacodi = "";
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

        long ll_tk = f_tabkey.getTabkey( "vist_mpron_posi" );

        return ll_tk;

    }




    /****
        executeUpdate: vist_mpron_posi
    **/ 


    public int executeUpdate( com.ateikon.structure.Rec_vist_mpron_posi astr ) throws Exception { 
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.vist_mpron_posi  \n";
        l_query  += "         set tkmpron = ?  \n";
        l_query  += "           , dtdoc = ?  \n";
        l_query  += "           , nrdoc = ?  \n";
        l_query  += "           , importo = ?  \n";
        l_query  += "           , nota = ?  \n";
        l_query  += "           , vacodi = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "           , dtdel = ?  \n";
        l_query  += "           , nrposi = ?  \n";
        l_query  += "  where tkmpronp = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.nrdoc.equals("")) astr.nrdoc = null;
        if (astr.nota.equals("")) astr.nota = null;
        if (astr.vacodi.equals("")) astr.vacodi = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;


        ind = 1;
        if (astr.tkmpron == 0 && null_tkmpron){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkmpron); ind += 1;
        } 
        pstm.setTimestamp (ind, astr.dtdoc); ind += 1;
        pstm.setString    (ind, astr.nrdoc); ind += 1;
        if (astr.importo == 0 && null_importo){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, astr.importo); ind += 1;
        } 
        pstm.setString    (ind, astr.nota); ind += 1;
        pstm.setString    (ind, astr.vacodi); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;
        pstm.setTimestamp (ind, astr.dtdel); ind += 1;
        if (astr.nrposi == 0 && null_nrposi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.nrposi); ind += 1;
        } 

        if (astr.tkmpronp == 0 && null_tkmpronp){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkmpronp); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.tkmpronp = astr.tkmpronp; 

        // --- Ripristino le Stringhe NULL

        if (astr.nrdoc == null) astr.nrdoc = "";
        if (astr.nota == null) astr.nota = "";
        if (astr.vacodi == null) astr.vacodi = "";
        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil == null) astr.profil = "";


        return tot_rec;

    }



    /****
        getKey: vist_mpron_posi
    **/ 

    public int deleteKey( long       tkmpronp
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " delete \n";
        l_query  += "   from pgmr.vist_mpron_posi  \n";
        if (tkmpronp == 0 && null_tkmpronp){ 
            l_query  += "  where tkmpronp is null \n";
        }else { 
            l_query  += "  where tkmpronp = ?  \n";
        } 

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (tkmpronp == 0 && null_tkmpronp){ 
        }else { 
            pstm.setLong      (ind, tkmpronp); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();
        pstm.close();

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public long       tkmpronp = 0; 






    // --- Impostando a false queste prop. il def. ï¿½ 0

    public boolean ib_calcola_token = true;
    public boolean ib_ctrl_mindate = false;
    public boolean null_tkmpronp = true;
    public boolean null_tkmpron = true;
    public boolean null_importo = true;
    public boolean null_nrposi = true;




    public String is_farza_filtro_dipa = "N";



}

