package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Vist_mpron_ord_posi extends Atk_sql {

    public Vist_mpron_ord_posi() {

        super();
    }




    /****
        getAll: vist_mpron_ord_posi
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.vist_mpron_ord_posi  \n";
        if (is_farza_filtro_dipa.equals("S")){
            l_query  += "  where cddipa = '"+cddipa+"'  \n";
        }

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: vist_mpron_ord_posi
    **/ 

    public ResultSet getKey( long       tkmpronordp
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.vist_mpron_ord_posi  \n";
        if (tkmpronordp == 0 && null_tkmpronordp){ 
            l_query  += "  where tkmpronordp is null \n";
        }else { 
            l_query  += "  where tkmpronordp = ?  \n";
        } 

        pstm = setQuery( l_query ) ;

        ind = 1;
        if (tkmpronordp == 0 && null_tkmpronordp){ 
        }else { 
            pstm.setLong      (ind, tkmpronordp); ind += 1;
        } 

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: vist_mpron_ord_posi
    **/ 


    public com.ateikon.structure.Rec_vist_mpron_ord_posi  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_vist_mpron_ord_posi lstr = new com.ateikon.structure.Rec_vist_mpron_ord_posi();

        if (rs == null) return lstr;
        if (rs.getObject("tkmpronordp")!=null) lstr.tkmpronordp = rs.getLong      ("tkmpronordp"); 
        if (rs.getObject("tkmpron")!=null) lstr.tkmpron = rs.getLong      ("tkmpron"); 
        if (rs.getObject("tkposi")!=null) lstr.tkposi = rs.getLong      ("tkposi"); 
        if (rs.getObject("importo")!=null) lstr.importo = rs.getDouble    ("importo"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("importo_c")!=null) lstr.importo_c = rs.getDouble    ("importo_c"); 
        if (rs.getObject("nrpeso_l")!=null) lstr.nrpeso_l = rs.getDouble    ("nrpeso_l"); 
        if (rs.getObject("vlunlt")!=null) lstr.vlunlt = rs.getDouble    ("vlunlt"); 
        if (rs.getObject("qtatot")!=null) lstr.qtatot = rs.getDouble    ("qtatot"); 
        if (rs.getObject("impuni")!=null) lstr.impuni = rs.getDouble    ("impuni"); 
        if (rs.getObject("impuninetto")!=null) lstr.impuninetto = rs.getDouble    ("impuninetto"); 
        if (rs.getObject("dtdel")!=null) lstr.dtdel = rs.getTimestamp ("dtdel"); 
        if (rs.getObject("fgmerce_pronta")!=null) lstr.fgmerce_pronta = rs.getString    ("fgmerce_pronta"); 
        if (rs.getObject("dtcons")!=null) lstr.dtcons = rs.getTimestamp ("dtcons"); 

        return lstr;
    }




    /****
        preupdate: vist_mpron_ord_posi

        sovrascrivere per impostare i controlli da effetuare prima dell'execute
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_vist_mpron_ord_posi astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }





    /****
        preupdate: vist_mpron_ord_posi

        sovrascrivere per impostare i controlli da effetuare per sincro

        questo metodo ï¿½ stato introdotto per la sincro: in fase di export prima  
        di scrivere il file XML viene chiamato
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_vist_mpron_ord_posi astr, ResultSet rs) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }



    /****
        execute: vist_mpron_ord_posi
    **/ 


    public int execute ( com.ateikon.structure.Rec_vist_mpron_ord_posi astr) throws Exception {
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
        l_query  += " select tkmpronordp\n";
        l_query  += "   from pgmr.vist_mpron_ord_posi  \n";
        if (astr.tkmpronordp == 0 && null_tkmpronordp){ 
            l_query  += "  where tkmpronordp is null \n";
        }else { 
            l_query  += "  where tkmpronordp = ?  \n";
        } 


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (astr.tkmpronordp == 0 && null_tkmpronordp){ 
        }else { 
            pstm.setLong      (ind, astr.tkmpronordp); ind += 1;
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
            throw new Exception("Errore Agg. vist_mpron_ord_posi! ");
        }
        */ 

        return tot_rec;
    }




    /****
        executeInsert: vist_mpron_ord_posi
    **/ 


    public int executeInsert( com.ateikon.structure.Rec_vist_mpron_ord_posi astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        if (ib_calcola_token){
            long ll_tk = getNew_token( );

            if (ll_tk <= 0){
                throw new Exception ("Error Keypool (vist_mpron_ord_posi): "+ll_tk );
            }

            this.tkmpronordp = ll_tk;
            astr.tkmpronordp = this.tkmpronordp;
        }else {
            this.tkmpronordp = astr.tkmpronordp;
        }



        l_query   = "";
        l_query  += " insert into pgmr.vist_mpron_ord_posi ( \n";
        l_query  += "             tkmpronordp   \n";
        l_query  += "           , tkmpron   \n";
        l_query  += "           , tkposi   \n";
        l_query  += "           , importo   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "           , importo_c   \n";
        l_query  += "           , nrpeso_l   \n";
        l_query  += "           , vlunlt   \n";
        l_query  += "           , qtatot   \n";
        l_query  += "           , impuni   \n";
        l_query  += "           , impuninetto   \n";
        l_query  += "           , dtdel   \n";
        l_query  += "           , fgmerce_pronta   \n";
        l_query  += "           , dtcons   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;
        if (astr.fgmerce_pronta.equals("")) astr.fgmerce_pronta = null;


        ind = 1;
        if (astr.tkmpronordp == 0 && null_tkmpronordp){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkmpronordp); ind += 1;
        } 
        if (astr.tkmpron == 0 && null_tkmpron){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkmpron); ind += 1;
        } 
        if (astr.tkposi == 0 && null_tkposi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkposi); ind += 1;
        } 
        if (astr.importo == 0 && null_importo){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, astr.importo); ind += 1;
        } 
        pstm.setString    (ind, astr.cdazie); ind += 1;
        pstm.setString    (ind, astr.cddipa); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtinse); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;
        if (astr.importo_c == 0 && null_importo_c){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, astr.importo_c); ind += 1;
        } 
        if (astr.nrpeso_l == 0 && null_nrpeso_l){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, astr.nrpeso_l); ind += 1;
        } 
        if (astr.vlunlt == 0 && null_vlunlt){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, astr.vlunlt); ind += 1;
        } 
        if (astr.qtatot == 0 && null_qtatot){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, astr.qtatot); ind += 1;
        } 
        if (astr.impuni == 0 && null_impuni){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, astr.impuni); ind += 1;
        } 
        if (astr.impuninetto == 0 && null_impuninetto){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, astr.impuninetto); ind += 1;
        } 
        pstm.setTimestamp (ind, astr.dtdel); ind += 1;
        pstm.setString    (ind, astr.fgmerce_pronta); ind += 1;
        pstm.setTimestamp (ind, astr.dtcons); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        // --- Ripristino le Stringhe NULL

        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil == null) astr.profil = "";
        if (astr.fgmerce_pronta == null) astr.fgmerce_pronta = "";


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

        long ll_tk = f_tabkey.getTabkey( "vist_mpron_ord_posi" );

        return ll_tk;

    }




    /****
        executeUpdate: vist_mpron_ord_posi
    **/ 


    public int executeUpdate( com.ateikon.structure.Rec_vist_mpron_ord_posi astr ) throws Exception { 
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.vist_mpron_ord_posi  \n";
        l_query  += "         set tkmpron = ?  \n";
        l_query  += "           , tkposi = ?  \n";
        l_query  += "           , importo = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "           , importo_c = ?  \n";
        l_query  += "           , nrpeso_l = ?  \n";
        l_query  += "           , vlunlt = ?  \n";
        l_query  += "           , qtatot = ?  \n";
        l_query  += "           , impuni = ?  \n";
        l_query  += "           , impuninetto = ?  \n";
        l_query  += "           , dtdel = ?  \n";
        l_query  += "           , fgmerce_pronta = ?  \n";
        l_query  += "           , dtcons = ?  \n";
        l_query  += "  where tkmpronordp = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;
        if (astr.fgmerce_pronta.equals("")) astr.fgmerce_pronta = null;


        ind = 1;
        if (astr.tkmpron == 0 && null_tkmpron){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkmpron); ind += 1;
        } 
        if (astr.tkposi == 0 && null_tkposi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkposi); ind += 1;
        } 
        if (astr.importo == 0 && null_importo){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, astr.importo); ind += 1;
        } 
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;
        if (astr.importo_c == 0 && null_importo_c){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, astr.importo_c); ind += 1;
        } 
        if (astr.nrpeso_l == 0 && null_nrpeso_l){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, astr.nrpeso_l); ind += 1;
        } 
        if (astr.vlunlt == 0 && null_vlunlt){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, astr.vlunlt); ind += 1;
        } 
        if (astr.qtatot == 0 && null_qtatot){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, astr.qtatot); ind += 1;
        } 
        if (astr.impuni == 0 && null_impuni){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, astr.impuni); ind += 1;
        } 
        if (astr.impuninetto == 0 && null_impuninetto){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, astr.impuninetto); ind += 1;
        } 
        pstm.setTimestamp (ind, astr.dtdel); ind += 1;
        pstm.setString    (ind, astr.fgmerce_pronta); ind += 1;
        pstm.setTimestamp (ind, astr.dtcons); ind += 1;

        if (astr.tkmpronordp == 0 && null_tkmpronordp){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkmpronordp); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.tkmpronordp = astr.tkmpronordp; 

        // --- Ripristino le Stringhe NULL

        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil == null) astr.profil = "";
        if (astr.fgmerce_pronta == null) astr.fgmerce_pronta = "";


        return tot_rec;

    }



    /****
        getKey: vist_mpron_ord_posi
    **/ 

    public int deleteKey( long       tkmpronordp
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " delete \n";
        l_query  += "   from pgmr.vist_mpron_ord_posi  \n";
        if (tkmpronordp == 0 && null_tkmpronordp){ 
            l_query  += "  where tkmpronordp is null \n";
        }else { 
            l_query  += "  where tkmpronordp = ?  \n";
        } 

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (tkmpronordp == 0 && null_tkmpronordp){ 
        }else { 
            pstm.setLong      (ind, tkmpronordp); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();
        pstm.close();

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public long       tkmpronordp = 0; 






    // --- Impostando a false queste prop. il def. ï¿½ 0

    public boolean ib_calcola_token = true;
    public boolean ib_ctrl_mindate = false;
    public boolean null_tkmpronordp = true;
    public boolean null_tkmpron = true;
    public boolean null_tkposi = true;
    public boolean null_importo = true;
    public boolean null_importo_c = true;
    public boolean null_nrpeso_l = true;
    public boolean null_vlunlt = true;
    public boolean null_qtatot = true;
    public boolean null_impuni = true;
    public boolean null_impuninetto = true;




    public String is_farza_filtro_dipa = "N";



}

