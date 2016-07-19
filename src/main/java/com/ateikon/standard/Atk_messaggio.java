package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Atk_messaggio extends Atk_sql {

    public Atk_messaggio() {

        super();
    }




    /****
        getAll: atk_messaggio
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.atk_messaggio  \n";

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: atk_messaggio
    **/ 

    public ResultSet getKey( long       tkmsg
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.atk_messaggio  \n";
        if (tkmsg == 0 && null_tkmsg){ 
            l_query  += "  where tkmsg is null \n";
        }else { 
            l_query  += "  where tkmsg = ?  \n";
        } 

        pstm = setQuery( l_query ) ;

        ind = 1;
        if (tkmsg == 0 && null_tkmsg){ 
        }else { 
            pstm.setLong      (ind, tkmsg); ind += 1;
        } 

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: atk_messaggio
    **/ 


    public com.ateikon.structure.Rec_atk_messaggio  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_atk_messaggio lstr = new com.ateikon.structure.Rec_atk_messaggio();

        if (rs == null) return lstr;
        if (rs.getObject("tkmsg")!=null) lstr.tkmsg = rs.getLong      ("tkmsg"); 
        if (rs.getObject("host")!=null) lstr.host = rs.getString    ("host"); 
        if (rs.getObject("username")!=null) lstr.username = rs.getString    ("username"); 
        if (rs.getObject("password")!=null) lstr.password = rs.getString    ("password"); 
        if (rs.getObject("oggetto")!=null) lstr.oggetto = rs.getString    ("oggetto"); 
        if (rs.getObject("mittente")!=null) lstr.mittente = rs.getString    ("mittente"); 
        if (rs.getObject("contenuto")!=null) lstr.contenuto = rs.getString    ("contenuto"); 
        if (rs.getObject("dest1")!=null) lstr.dest1 = rs.getString    ("dest1"); 
        if (rs.getObject("dest2")!=null) lstr.dest2 = rs.getString    ("dest2"); 
        if (rs.getObject("dest3")!=null) lstr.dest3 = rs.getString    ("dest3"); 
        if (rs.getObject("dest4")!=null) lstr.dest4 = rs.getString    ("dest4"); 
        if (rs.getObject("bcc1")!=null) lstr.bcc1 = rs.getString    ("bcc1"); 
        if (rs.getObject("bcc2")!=null) lstr.bcc2 = rs.getString    ("bcc2"); 
        if (rs.getObject("bcc3")!=null) lstr.bcc3 = rs.getString    ("bcc3"); 
        if (rs.getObject("bcc4")!=null) lstr.bcc4 = rs.getString    ("bcc4"); 
        if (rs.getObject("nomefile")!=null) lstr.nomefile = rs.getString    ("nomefile"); 
        if (rs.getObject("pathfile")!=null) lstr.pathfile = rs.getString    ("pathfile"); 
        if (rs.getObject("dtricsped")!=null) lstr.dtricsped = rs.getTimestamp ("dtricsped"); 
        if (rs.getObject("dtproc")!=null) lstr.dtproc = rs.getTimestamp ("dtproc"); 
        if (rs.getObject("nota")!=null) lstr.nota = rs.getString    ("nota"); 
        if (rs.getObject("id_object")!=null) lstr.id_object = rs.getLong      ("id_object"); 
        if (rs.getObject("tkboll")!=null) lstr.tkboll = rs.getLong      ("tkboll"); 
        if (rs.getObject("tpservizio")!=null) lstr.tpservizio = rs.getString    ("tpservizio"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("dtsped")!=null) lstr.dtsped = rs.getTimestamp ("dtsped"); 
        if (rs.getObject("tkml")!=null) lstr.tkml = rs.getLong      ("tkml"); 
        if (rs.getObject("tkordi")!=null) lstr.tkordi = rs.getLong      ("tkordi"); 
        if (rs.getObject("tkordiacq")!=null) lstr.tkordiacq = rs.getLong      ("tkordiacq"); 
        if (rs.getObject("tkfatt")!=null) lstr.tkfatt = rs.getLong      ("tkfatt"); 
        if (rs.getObject("smtp_port")!=null) lstr.smtp_port = rs.getString    ("smtp_port"); 
        if (rs.getObject("tkbollacq")!=null) lstr.tkbollacq = rs.getLong      ("tkbollacq"); 
        if (rs.getObject("tkfattacq")!=null) lstr.tkfattacq = rs.getLong      ("tkfattacq"); 

        return lstr;
    }




    /****
        preupdate: atk_messaggio

        sovrascrivere per impostare i controlli da effetuare prima dell'execute
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_atk_messaggio astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }





    /****
        preupdate: atk_messaggio

        sovrascrivere per impostare i controlli da effetuare per sincro

        questo metodo � stato introdotto per la sincro: in fase di export prima  
        di scrivere il file XML viene chiamato
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_atk_messaggio astr, ResultSet rs) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }



    /****
        execute: atk_messaggio
    **/ 


    public int execute ( com.ateikon.structure.Rec_atk_messaggio astr) throws Exception {
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
        l_query  += "   from pgmr.atk_messaggio  \n";
        if (astr.tkmsg == 0 && null_tkmsg){ 
            l_query  += "  where tkmsg is null \n";
        }else { 
            l_query  += "  where tkmsg = ?  \n";
        } 


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (astr.tkmsg == 0 && null_tkmsg){ 
        }else { 
            pstm.setLong      (ind, astr.tkmsg); ind += 1;
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
            throw new Exception("Errore Agg. atk_messaggio! ");
        }
        */ 

        return tot_rec;
    }




    /****
        executeInsert: atk_messaggio
    **/ 


    public int executeInsert( com.ateikon.structure.Rec_atk_messaggio astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        if (ib_calcola_token){
            long ll_tk = getNew_token( );

            if (ll_tk <= 0){
                throw new Exception ("Error Keypool (atk_messaggio): "+ll_tk );
            }

            this.tkmsg = ll_tk;
            astr.tkmsg = this.tkmsg;
        }else {
            this.tkmsg = astr.tkmsg;
        }



        l_query   = "";
        l_query  += " insert into pgmr.atk_messaggio ( \n";
        l_query  += "             tkmsg   \n";
        l_query  += "           , host   \n";
        l_query  += "           , username   \n";
        l_query  += "           , password   \n";
        l_query  += "           , oggetto   \n";
        l_query  += "           , mittente   \n";
        l_query  += "           , contenuto   \n";
        l_query  += "           , dest1   \n";
        l_query  += "           , dest2   \n";
        l_query  += "           , dest3   \n";
        l_query  += "           , dest4   \n";
        l_query  += "           , bcc1   \n";
        l_query  += "           , bcc2   \n";
        l_query  += "           , bcc3   \n";
        l_query  += "           , bcc4   \n";
        l_query  += "           , nomefile   \n";
        l_query  += "           , pathfile   \n";
        l_query  += "           , dtricsped   \n";
        l_query  += "           , dtproc   \n";
        l_query  += "           , nota   \n";
        l_query  += "           , id_object   \n";
        l_query  += "           , tkboll   \n";
        l_query  += "           , tpservizio   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "           , dtsped   \n";
        l_query  += "           , tkml   \n";
        l_query  += "           , tkordi   \n";
        l_query  += "           , tkordiacq   \n";
        l_query  += "           , tkfatt   \n";
        l_query  += "           , smtp_port   \n";
        l_query  += "           , tkbollacq   \n";
        l_query  += "           , tkfattacq   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.host.equals("")) astr.host = null;
        if (astr.username.equals("")) astr.username = null;
        if (astr.password.equals("")) astr.password = null;
        if (astr.oggetto.equals("")) astr.oggetto = null;
        if (astr.mittente.equals("")) astr.mittente = null;
        if (astr.contenuto.equals("")) astr.contenuto = null;
        if (astr.dest1.equals("")) astr.dest1 = null;
        if (astr.dest2.equals("")) astr.dest2 = null;
        if (astr.dest3.equals("")) astr.dest3 = null;
        if (astr.dest4.equals("")) astr.dest4 = null;
        if (astr.bcc1.equals("")) astr.bcc1 = null;
        if (astr.bcc2.equals("")) astr.bcc2 = null;
        if (astr.bcc3.equals("")) astr.bcc3 = null;
        if (astr.bcc4.equals("")) astr.bcc4 = null;
        if (astr.nomefile.equals("")) astr.nomefile = null;
        if (astr.pathfile.equals("")) astr.pathfile = null;
        if (astr.nota.equals("")) astr.nota = null;
        if (astr.tpservizio.equals("")) astr.tpservizio = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;
        if (astr.smtp_port.equals("")) astr.smtp_port = null;


        ind = 1;
        if (astr.tkmsg == 0 && null_tkmsg){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkmsg); ind += 1;
        } 
        pstm.setString    (ind, astr.host); ind += 1;
        pstm.setString    (ind, astr.username); ind += 1;
        pstm.setString    (ind, astr.password); ind += 1;
        pstm.setString    (ind, astr.oggetto); ind += 1;
        pstm.setString    (ind, astr.mittente); ind += 1;
        pstm.setString    (ind, astr.contenuto); ind += 1;
        pstm.setString    (ind, astr.dest1); ind += 1;
        pstm.setString    (ind, astr.dest2); ind += 1;
        pstm.setString    (ind, astr.dest3); ind += 1;
        pstm.setString    (ind, astr.dest4); ind += 1;
        pstm.setString    (ind, astr.bcc1); ind += 1;
        pstm.setString    (ind, astr.bcc2); ind += 1;
        pstm.setString    (ind, astr.bcc3); ind += 1;
        pstm.setString    (ind, astr.bcc4); ind += 1;
        pstm.setString    (ind, astr.nomefile); ind += 1;
        pstm.setString    (ind, astr.pathfile); ind += 1;
        pstm.setTimestamp (ind, astr.dtricsped); ind += 1;
        pstm.setTimestamp (ind, astr.dtproc); ind += 1;
        pstm.setString    (ind, astr.nota); ind += 1;
        if (astr.id_object == 0 && null_id_object){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.id_object); ind += 1;
        } 
        if (astr.tkboll == 0 && null_tkboll){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkboll); ind += 1;
        } 
        pstm.setString    (ind, astr.tpservizio); ind += 1;
        pstm.setString    (ind, astr.cdazie); ind += 1;
        pstm.setString    (ind, astr.cddipa); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtinse); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;
        pstm.setTimestamp (ind, astr.dtsped); ind += 1;
        if (astr.tkml == 0 && null_tkml){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkml); ind += 1;
        } 
        if (astr.tkordi == 0 && null_tkordi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkordi); ind += 1;
        } 
        if (astr.tkordiacq == 0 && null_tkordiacq){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkordiacq); ind += 1;
        } 
        if (astr.tkfatt == 0 && null_tkfatt){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkfatt); ind += 1;
        } 
        pstm.setString    (ind, astr.smtp_port); ind += 1;
        if (astr.tkbollacq == 0 && null_tkbollacq){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkbollacq); ind += 1;
        } 
        if (astr.tkfattacq == 0 && null_tkfattacq){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkfattacq); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        // --- Ripristino le Stringhe NULL

        if (astr.host == null) astr.host = "";
        if (astr.username == null) astr.username = "";
        if (astr.password == null) astr.password = "";
        if (astr.oggetto == null) astr.oggetto = "";
        if (astr.mittente == null) astr.mittente = "";
        if (astr.contenuto == null) astr.contenuto = "";
        if (astr.dest1 == null) astr.dest1 = "";
        if (astr.dest2 == null) astr.dest2 = "";
        if (astr.dest3 == null) astr.dest3 = "";
        if (astr.dest4 == null) astr.dest4 = "";
        if (astr.bcc1 == null) astr.bcc1 = "";
        if (astr.bcc2 == null) astr.bcc2 = "";
        if (astr.bcc3 == null) astr.bcc3 = "";
        if (astr.bcc4 == null) astr.bcc4 = "";
        if (astr.nomefile == null) astr.nomefile = "";
        if (astr.pathfile == null) astr.pathfile = "";
        if (astr.nota == null) astr.nota = "";
        if (astr.tpservizio == null) astr.tpservizio = "";
        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil == null) astr.profil = "";
        if (astr.smtp_port == null) astr.smtp_port = "";


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

        long ll_tk = f_tabkey.getTabkey( "atk_messaggio" );

        return ll_tk;

    }




    /****
        executeUpdate: atk_messaggio
    **/ 


    public int executeUpdate( com.ateikon.structure.Rec_atk_messaggio astr ) throws Exception { 
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.atk_messaggio  \n";
        l_query  += "         set host = ?  \n";
        l_query  += "           , username = ?  \n";
        l_query  += "           , password = ?  \n";
        l_query  += "           , oggetto = ?  \n";
        l_query  += "           , mittente = ?  \n";
        l_query  += "           , contenuto = ?  \n";
        l_query  += "           , dest1 = ?  \n";
        l_query  += "           , dest2 = ?  \n";
        l_query  += "           , dest3 = ?  \n";
        l_query  += "           , dest4 = ?  \n";
        l_query  += "           , bcc1 = ?  \n";
        l_query  += "           , bcc2 = ?  \n";
        l_query  += "           , bcc3 = ?  \n";
        l_query  += "           , bcc4 = ?  \n";
        l_query  += "           , nomefile = ?  \n";
        l_query  += "           , pathfile = ?  \n";
        l_query  += "           , dtricsped = ?  \n";
        l_query  += "           , dtproc = ?  \n";
        l_query  += "           , nota = ?  \n";
        l_query  += "           , id_object = ?  \n";
        l_query  += "           , tkboll = ?  \n";
        l_query  += "           , tpservizio = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "           , dtsped = ?  \n";
        l_query  += "           , tkml = ?  \n";
        l_query  += "           , tkordi = ?  \n";
        l_query  += "           , tkordiacq = ?  \n";
        l_query  += "           , tkfatt = ?  \n";
        l_query  += "           , smtp_port = ?  \n";
        l_query  += "           , tkbollacq = ?  \n";
        l_query  += "           , tkfattacq = ?  \n";
        l_query  += "  where tkmsg = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.host.equals("")) astr.host = null;
        if (astr.username.equals("")) astr.username = null;
        if (astr.password.equals("")) astr.password = null;
        if (astr.oggetto.equals("")) astr.oggetto = null;
        if (astr.mittente.equals("")) astr.mittente = null;
        if (astr.contenuto.equals("")) astr.contenuto = null;
        if (astr.dest1.equals("")) astr.dest1 = null;
        if (astr.dest2.equals("")) astr.dest2 = null;
        if (astr.dest3.equals("")) astr.dest3 = null;
        if (astr.dest4.equals("")) astr.dest4 = null;
        if (astr.bcc1.equals("")) astr.bcc1 = null;
        if (astr.bcc2.equals("")) astr.bcc2 = null;
        if (astr.bcc3.equals("")) astr.bcc3 = null;
        if (astr.bcc4.equals("")) astr.bcc4 = null;
        if (astr.nomefile.equals("")) astr.nomefile = null;
        if (astr.pathfile.equals("")) astr.pathfile = null;
        if (astr.nota.equals("")) astr.nota = null;
        if (astr.tpservizio.equals("")) astr.tpservizio = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;
        if (astr.smtp_port.equals("")) astr.smtp_port = null;


        ind = 1;
        pstm.setString    (ind, astr.host); ind += 1;
        pstm.setString    (ind, astr.username); ind += 1;
        pstm.setString    (ind, astr.password); ind += 1;
        pstm.setString    (ind, astr.oggetto); ind += 1;
        pstm.setString    (ind, astr.mittente); ind += 1;
        pstm.setString    (ind, astr.contenuto); ind += 1;
        pstm.setString    (ind, astr.dest1); ind += 1;
        pstm.setString    (ind, astr.dest2); ind += 1;
        pstm.setString    (ind, astr.dest3); ind += 1;
        pstm.setString    (ind, astr.dest4); ind += 1;
        pstm.setString    (ind, astr.bcc1); ind += 1;
        pstm.setString    (ind, astr.bcc2); ind += 1;
        pstm.setString    (ind, astr.bcc3); ind += 1;
        pstm.setString    (ind, astr.bcc4); ind += 1;
        pstm.setString    (ind, astr.nomefile); ind += 1;
        pstm.setString    (ind, astr.pathfile); ind += 1;
        pstm.setTimestamp (ind, astr.dtricsped); ind += 1;
        pstm.setTimestamp (ind, astr.dtproc); ind += 1;
        pstm.setString    (ind, astr.nota); ind += 1;
        if (astr.id_object == 0 && null_id_object){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.id_object); ind += 1;
        } 
        if (astr.tkboll == 0 && null_tkboll){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkboll); ind += 1;
        } 
        pstm.setString    (ind, astr.tpservizio); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;
        pstm.setTimestamp (ind, astr.dtsped); ind += 1;
        if (astr.tkml == 0 && null_tkml){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkml); ind += 1;
        } 
        if (astr.tkordi == 0 && null_tkordi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkordi); ind += 1;
        } 
        if (astr.tkordiacq == 0 && null_tkordiacq){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkordiacq); ind += 1;
        } 
        if (astr.tkfatt == 0 && null_tkfatt){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkfatt); ind += 1;
        } 
        pstm.setString    (ind, astr.smtp_port); ind += 1;
        if (astr.tkbollacq == 0 && null_tkbollacq){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkbollacq); ind += 1;
        } 
        if (astr.tkfattacq == 0 && null_tkfattacq){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkfattacq); ind += 1;
        } 

        if (astr.tkmsg == 0 && null_tkmsg){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkmsg); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.tkmsg = astr.tkmsg; 

        // --- Ripristino le Stringhe NULL

        if (astr.host == null) astr.host = "";
        if (astr.username == null) astr.username = "";
        if (astr.password == null) astr.password = "";
        if (astr.oggetto == null) astr.oggetto = "";
        if (astr.mittente == null) astr.mittente = "";
        if (astr.contenuto == null) astr.contenuto = "";
        if (astr.dest1 == null) astr.dest1 = "";
        if (astr.dest2 == null) astr.dest2 = "";
        if (astr.dest3 == null) astr.dest3 = "";
        if (astr.dest4 == null) astr.dest4 = "";
        if (astr.bcc1 == null) astr.bcc1 = "";
        if (astr.bcc2 == null) astr.bcc2 = "";
        if (astr.bcc3 == null) astr.bcc3 = "";
        if (astr.bcc4 == null) astr.bcc4 = "";
        if (astr.nomefile == null) astr.nomefile = "";
        if (astr.pathfile == null) astr.pathfile = "";
        if (astr.nota == null) astr.nota = "";
        if (astr.tpservizio == null) astr.tpservizio = "";
        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil == null) astr.profil = "";
        if (astr.smtp_port == null) astr.smtp_port = "";


        return tot_rec;

    }



    /****
        getKey: atk_messaggio
    **/ 

    public int deleteKey( long       tkmsg
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " delete \n";
        l_query  += "   from pgmr.atk_messaggio  \n";
        if (tkmsg == 0 && null_tkmsg){ 
            l_query  += "  where tkmsg is null \n";
        }else { 
            l_query  += "  where tkmsg = ?  \n";
        } 

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (tkmsg == 0 && null_tkmsg){ 
        }else { 
            pstm.setLong      (ind, tkmsg); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();
        pstm.close();

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public long       tkmsg = 0; 






    // --- Impostando a false queste prop. il def. � 0

    public boolean ib_calcola_token = true;
    public boolean ib_ctrl_mindate = false;
    public boolean null_tkmsg = true;
    public boolean null_id_object = true;
    public boolean null_tkboll = true;
    public boolean null_tkml = true;
    public boolean null_tkordi = true;
    public boolean null_tkordiacq = true;
    public boolean null_tkfatt = true;
    public boolean null_tkbollacq = true;
    public boolean null_tkfattacq = true;





}

