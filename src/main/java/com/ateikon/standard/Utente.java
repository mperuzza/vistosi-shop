package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Utente extends Atk_sql {

    public Utente() {

        super();
    }




    /****
        getKey: utente
    **/ 

    public ResultSet getKey( String     cdutente
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from web.utente  \n";
        l_query  += "  where cdutente = ? \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString    (ind, cdutente); ind += 1;

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: utente
    **/ 


    public com.ateikon.structure.Rec_utente  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_utente lstr = new com.ateikon.structure.Rec_utente();

        if (rs == null) return lstr;
        if (rs.getObject("cdutente")!=null) lstr.cdutente = rs.getString    ("cdutente"); 
        if (rs.getObject("dsutente")!=null) lstr.dsutente = rs.getString    ("dsutente"); 
        if (rs.getObject("pwd")!=null) lstr.pwd = rs.getString    ("pwd"); 
        if (rs.getObject("idutente")!=null) lstr.idutente = rs.getString    ("idutente"); 
        if (rs.getObject("cdagen")!=null) lstr.cdagen = rs.getString    ("cdagen"); 
        if (rs.getObject("cdcapo")!=null) lstr.cdcapo = rs.getString    ("cdcapo"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("username")!=null) lstr.username = rs.getString    ("username"); 
        if (rs.getObject("password")!=null) lstr.password = rs.getString    ("password"); 
        if (rs.getObject("nome")!=null) lstr.nome = rs.getString    ("nome"); 
        if (rs.getObject("cognome")!=null) lstr.cognome = rs.getString    ("cognome"); 
        if (rs.getObject("ragsoc")!=null) lstr.ragsoc = rs.getString    ("ragsoc"); 
        if (rs.getObject("cdgput")!=null) lstr.cdgput = rs.getString    ("cdgput"); 
        if (rs.getObject("cdente")!=null) lstr.cdente = rs.getString    ("cdente"); 
        if (rs.getObject("tkclie")!=null) lstr.tkclie = rs.getString    ("tkclie"); 
        if (rs.getObject("tkforn")!=null) lstr.tkforn = rs.getString    ("tkforn"); 
        if (rs.getObject("indirizzo")!=null) lstr.indirizzo = rs.getString    ("indirizzo"); 
        if (rs.getObject("cap")!=null) lstr.cap = rs.getString    ("cap"); 
        if (rs.getObject("localita")!=null) lstr.localita = rs.getString    ("localita"); 
        if (rs.getObject("piva")!=null) lstr.piva = rs.getString    ("piva"); 
        if (rs.getObject("cfisc")!=null) lstr.cfisc = rs.getString    ("cfisc"); 
        if (rs.getObject("telefono")!=null) lstr.telefono = rs.getString    ("telefono"); 
        if (rs.getObject("fax")!=null) lstr.fax = rs.getString    ("fax"); 
        if (rs.getObject("email")!=null) lstr.email = rs.getString    ("email"); 
        if (rs.getObject("sitointernet")!=null) lstr.sitointernet = rs.getString    ("sitointernet"); 
        if (rs.getObject("cellulare")!=null) lstr.cellulare = rs.getString    ("cellulare"); 
        if (rs.getObject("cdnazi")!=null) lstr.cdnazi = rs.getString    ("cdnazi"); 
        if (rs.getObject("nota")!=null) lstr.nota = rs.getString    ("nota"); 
        if (rs.getObject("fgweb")!=null) lstr.fgweb = rs.getString    ("fgweb"); 
        if (rs.getObject("livello")!=null) lstr.livello = rs.getLong      ("livello"); 
        if (rs.getObject("tipoblocco")!=null) lstr.tipoblocco = rs.getString    ("tipoblocco"); 
        if (rs.getObject("dtblocco")!=null) lstr.dtblocco = rs.getTimestamp ("dtblocco"); 
        if (rs.getObject("cdprov")!=null) lstr.cdprov = rs.getString    ("cdprov"); 
        if (rs.getObject("sesso")!=null) lstr.sesso = rs.getString    ("sesso"); 
        if (rs.getObject("dtnasc")!=null) lstr.dtnasc = rs.getTimestamp ("dtnasc"); 
        if (rs.getObject("pariva")!=null) lstr.pariva = rs.getString    ("pariva"); 
        if (rs.getObject("indiri")!=null) lstr.indiri = rs.getString    ("indiri"); 
        if (rs.getObject("comune")!=null) lstr.comune = rs.getString    ("comune"); 
        if (rs.getObject("pwd2")!=null) lstr.pwd2 = rs.getString    ("pwd2"); 
        if (rs.getObject("pwd3")!=null) lstr.pwd3 = rs.getString    ("pwd3"); 
        if (rs.getObject("dtpwd")!=null) lstr.dtpwd = rs.getTimestamp ("dtpwd"); 
        if (rs.getObject("dtpwd2")!=null) lstr.dtpwd2 = rs.getTimestamp ("dtpwd2"); 
        if (rs.getObject("dtpwd3")!=null) lstr.dtpwd3 = rs.getTimestamp ("dtpwd3"); 
        if (rs.getObject("dtulconn")!=null) lstr.dtulconn = rs.getTimestamp ("dtulconn"); 
        if (rs.getObject("dtwebsync")!=null) lstr.dtwebsync = rs.getTimestamp ("dtwebsync"); 
        if (rs.getObject("dtricpwd")!=null) lstr.dtricpwd = rs.getTimestamp ("dtricpwd"); 
        if (rs.getObject("cdutente_gest")!=null) lstr.cdutente_gest = rs.getString    ("cdutente_gest"); 
        if (rs.getObject("ftpusername")!=null) lstr.ftpusername = rs.getString    ("ftpusername"); 
        if (rs.getObject("ftppwd")!=null) lstr.ftppwd = rs.getString    ("ftppwd"); 
        if (rs.getObject("fgfmailpwd")!=null) lstr.fgfmailpwd = rs.getString    ("fgfmailpwd"); 
        if (rs.getObject("fgmailbatch")!=null) lstr.fgmailbatch = rs.getString    ("fgmailbatch"); 
        if (rs.getObject("dtmailbatch")!=null) lstr.dtmailbatch = rs.getTimestamp ("dtmailbatch"); 

        return lstr;
    }




    /****
        execute: utente
    **/ 


    public int execute ( com.ateikon.structure.Rec_utente astr) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec =  execute ( astr.cdutente
                          , astr.dsutente
                          , astr.pwd
                          , astr.idutente
                          , astr.cdagen
                          , astr.cdcapo
                          , astr.username
                          , astr.password
                          , astr.nome
                          , astr.cognome
                          , astr.ragsoc
                          , astr.cdgput
                          , astr.cdente
                          , astr.tkclie
                          , astr.tkforn
                          , astr.indirizzo
                          , astr.cap
                          , astr.localita
                          , astr.piva
                          , astr.cfisc
                          , astr.telefono
                          , astr.fax
                          , astr.email
                          , astr.sitointernet
                          , astr.cellulare
                          , astr.cdnazi
                          , astr.nota
                          , astr.fgweb
                          , astr.livello
                          , astr.tipoblocco
                          , astr.dtblocco
                          , astr.cdprov
                          , astr.sesso
                          , astr.dtnasc
                          , astr.pariva
                          , astr.indiri
                          , astr.comune
                          , astr.pwd2
                          , astr.pwd3
                          , astr.dtpwd
                          , astr.dtpwd2
                          , astr.dtpwd3
                          , astr.dtulconn
                          , astr.dtwebsync
                          , astr.dtricpwd
                          , astr.cdutente_gest
                          , astr.ftpusername
                          , astr.ftppwd
                          , astr.fgfmailpwd
                          , astr.fgmailbatch
                          , astr.dtmailbatch
                           );

        return tot_rec;
    }




    /****
        execute: utente
    **/ 


    public int execute ( String     cdutente
                       , String     dsutente
                       , String     pwd
                       , String     idutente
                       , String     cdagen
                       , String     cdcapo
                       , String     username
                       , String     password
                       , String     nome
                       , String     cognome
                       , String     ragsoc
                       , String     cdgput
                       , String     cdente
                       , String     tkclie
                       , String     tkforn
                       , String     indirizzo
                       , String     cap
                       , String     localita
                       , String     piva
                       , String     cfisc
                       , String     telefono
                       , String     fax
                       , String     email
                       , String     sitointernet
                       , String     cellulare
                       , String     cdnazi
                       , String     nota
                       , String     fgweb
                       , long       livello
                       , String     tipoblocco
                       , Timestamp  dtblocco
                       , String     cdprov
                       , String     sesso
                       , Timestamp  dtnasc
                       , String     pariva
                       , String     indiri
                       , String     comune
                       , String     pwd2
                       , String     pwd3
                       , Timestamp  dtpwd
                       , Timestamp  dtpwd2
                       , Timestamp  dtpwd3
                       , Timestamp  dtulconn
                       , Timestamp  dtwebsync
                       , Timestamp  dtricpwd
                       , String     cdutente_gest
                       , String     ftpusername
                       , String     ftppwd
                       , String     fgfmailpwd
                       , String     fgmailbatch
                       , Timestamp  dtmailbatch
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " select cdutente\n";
        l_query  += "   from web.utente  \n";
        l_query  += "  where cdutente = ? \n";


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setString    (ind, cdutente); ind += 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            pstm.close();
            tot_rec = executeUpdate ( cdutente
                                    , dsutente
                                    , pwd
                                    , idutente
                                    , cdagen
                                    , cdcapo
                                    , username
                                    , password
                                    , nome
                                    , cognome
                                    , ragsoc
                                    , cdgput
                                    , cdente
                                    , tkclie
                                    , tkforn
                                    , indirizzo
                                    , cap
                                    , localita
                                    , piva
                                    , cfisc
                                    , telefono
                                    , fax
                                    , email
                                    , sitointernet
                                    , cellulare
                                    , cdnazi
                                    , nota
                                    , fgweb
                                    , livello
                                    , tipoblocco
                                    , dtblocco
                                    , cdprov
                                    , sesso
                                    , dtnasc
                                    , pariva
                                    , indiri
                                    , comune
                                    , pwd2
                                    , pwd3
                                    , dtpwd
                                    , dtpwd2
                                    , dtpwd3
                                    , dtulconn
                                    , dtwebsync
                                    , dtricpwd
                                    , cdutente_gest
                                    , ftpusername
                                    , ftppwd
                                    , fgfmailpwd
                                    , fgmailbatch
                                    , dtmailbatch
                                    );
        }else {

            pstm.close();
            tot_rec = executeInsert ( cdutente
                                    , dsutente
                                    , pwd
                                    , idutente
                                    , cdagen
                                    , cdcapo
                                    , username
                                    , password
                                    , nome
                                    , cognome
                                    , ragsoc
                                    , cdgput
                                    , cdente
                                    , tkclie
                                    , tkforn
                                    , indirizzo
                                    , cap
                                    , localita
                                    , piva
                                    , cfisc
                                    , telefono
                                    , fax
                                    , email
                                    , sitointernet
                                    , cellulare
                                    , cdnazi
                                    , nota
                                    , fgweb
                                    , livello
                                    , tipoblocco
                                    , dtblocco
                                    , cdprov
                                    , sesso
                                    , dtnasc
                                    , pariva
                                    , indiri
                                    , comune
                                    , pwd2
                                    , pwd3
                                    , dtpwd
                                    , dtpwd2
                                    , dtpwd3
                                    , dtulconn
                                    , dtwebsync
                                    , dtricpwd
                                    , cdutente_gest
                                    , ftpusername
                                    , ftppwd
                                    , fgfmailpwd
                                    , fgmailbatch
                                    , dtmailbatch
                                    );
        }

        if (tot_rec != 1){

            m_connection.rollback();
            throw new Exception("Errore Agg. utente! ");
        }

        return tot_rec;
    }




    /****
        executeInsert: utente
    **/ 


    public int executeInsert( String     cdutente
                            , String     dsutente
                            , String     pwd
                            , String     idutente
                            , String     cdagen
                            , String     cdcapo
                            , String     username
                            , String     password
                            , String     nome
                            , String     cognome
                            , String     ragsoc
                            , String     cdgput
                            , String     cdente
                            , String     tkclie
                            , String     tkforn
                            , String     indirizzo
                            , String     cap
                            , String     localita
                            , String     piva
                            , String     cfisc
                            , String     telefono
                            , String     fax
                            , String     email
                            , String     sitointernet
                            , String     cellulare
                            , String     cdnazi
                            , String     nota
                            , String     fgweb
                            , long       livello
                            , String     tipoblocco
                            , Timestamp  dtblocco
                            , String     cdprov
                            , String     sesso
                            , Timestamp  dtnasc
                            , String     pariva
                            , String     indiri
                            , String     comune
                            , String     pwd2
                            , String     pwd3
                            , Timestamp  dtpwd
                            , Timestamp  dtpwd2
                            , Timestamp  dtpwd3
                            , Timestamp  dtulconn
                            , Timestamp  dtwebsync
                            , Timestamp  dtricpwd
                            , String     cdutente_gest
                            , String     ftpusername
                            , String     ftppwd
                            , String     fgfmailpwd
                            , String     fgmailbatch
                            , Timestamp  dtmailbatch
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        long ll_tk = getNew_token( );

        if (ll_tk <= 0){
            throw new Exception ("Error Keypool (utente): "+ll_tk );
        }

        this.cdutente = of_token(ll_tk);
        cdutente = this.cdutente;



        l_query   = "";
        l_query  += " insert into web.utente ( \n";
        l_query  += "             cdutente   \n";
        l_query  += "           , dsutente   \n";
        l_query  += "           , pwd   \n";
        l_query  += "           , idutente   \n";
        l_query  += "           , cdagen   \n";
        l_query  += "           , cdcapo   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "           , username   \n";
        l_query  += "           , password   \n";
        l_query  += "           , nome   \n";
        l_query  += "           , cognome   \n";
        l_query  += "           , ragsoc   \n";
        l_query  += "           , cdgput   \n";
        l_query  += "           , cdente   \n";
        l_query  += "           , tkclie   \n";
        l_query  += "           , tkforn   \n";
        l_query  += "           , indirizzo   \n";
        l_query  += "           , cap   \n";
        l_query  += "           , localita   \n";
        l_query  += "           , piva   \n";
        l_query  += "           , cfisc   \n";
        l_query  += "           , telefono   \n";
        l_query  += "           , fax   \n";
        l_query  += "           , email   \n";
        l_query  += "           , sitointernet   \n";
        l_query  += "           , cellulare   \n";
        l_query  += "           , cdnazi   \n";
        l_query  += "           , nota   \n";
        l_query  += "           , fgweb   \n";
        l_query  += "           , livello   \n";
        l_query  += "           , tipoblocco   \n";
        l_query  += "           , dtblocco   \n";
        l_query  += "           , cdprov   \n";
        l_query  += "           , sesso   \n";
        l_query  += "           , dtnasc   \n";
        l_query  += "           , pariva   \n";
        l_query  += "           , indiri   \n";
        l_query  += "           , comune   \n";
        l_query  += "           , pwd2   \n";
        l_query  += "           , pwd3   \n";
        l_query  += "           , dtpwd   \n";
        l_query  += "           , dtpwd2   \n";
        l_query  += "           , dtpwd3   \n";
        l_query  += "           , dtulconn   \n";
        l_query  += "           , dtwebsync   \n";
        l_query  += "           , dtricpwd   \n";
        l_query  += "           , cdutente_gest   \n";
        l_query  += "           , ftpusername   \n";
        l_query  += "           , ftppwd   \n";
        l_query  += "           , fgfmailpwd   \n";
        l_query  += "           , fgmailbatch   \n";
        l_query  += "           , dtmailbatch   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (dsutente.equals("")) dsutente = null;
        if (pwd.equals("")) pwd = null;
        if (idutente.equals("")) idutente = null;
        if (cdagen.equals("")) cdagen = null;
        if (cdcapo.equals("")) cdcapo = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;
        if (username.equals("")) username = null;
        if (password.equals("")) password = null;
        if (nome.equals("")) nome = null;
        if (cognome.equals("")) cognome = null;
        if (ragsoc.equals("")) ragsoc = null;
        if (cdgput.equals("")) cdgput = null;
        if (cdente.equals("")) cdente = null;
        if (tkclie.equals("")) tkclie = null;
        if (tkforn.equals("")) tkforn = null;
        if (indirizzo.equals("")) indirizzo = null;
        if (cap.equals("")) cap = null;
        if (localita.equals("")) localita = null;
        if (piva.equals("")) piva = null;
        if (cfisc.equals("")) cfisc = null;
        if (telefono.equals("")) telefono = null;
        if (fax.equals("")) fax = null;
        if (email.equals("")) email = null;
        if (sitointernet.equals("")) sitointernet = null;
        if (cellulare.equals("")) cellulare = null;
        if (cdnazi.equals("")) cdnazi = null;
        if (nota.equals("")) nota = null;
        if (fgweb.equals("")) fgweb = null;
        if (tipoblocco.equals("")) tipoblocco = null;
        if (cdprov.equals("")) cdprov = null;
        if (sesso.equals("")) sesso = null;
        if (pariva.equals("")) pariva = null;
        if (indiri.equals("")) indiri = null;
        if (comune.equals("")) comune = null;
        if (pwd2.equals("")) pwd2 = null;
        if (pwd3.equals("")) pwd3 = null;
        if (cdutente_gest.equals("")) cdutente_gest = null;
        if (ftpusername.equals("")) ftpusername = null;
        if (ftppwd.equals("")) ftppwd = null;
        if (fgfmailpwd.equals("")) fgfmailpwd = null;
        if (fgmailbatch.equals("")) fgmailbatch = null;


        ind = 1;
        pstm.setString    (ind, cdutente); ind += 1;
        pstm.setString    (ind, dsutente); ind += 1;
        pstm.setString    (ind, pwd); ind += 1;
        pstm.setString    (ind, idutente); ind += 1;
        pstm.setString    (ind, cdagen); ind += 1;
        pstm.setString    (ind, cdcapo); ind += 1;
        pstm.setString    (ind, cdazie); ind += 1;
        pstm.setString    (ind, cddipa); ind += 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtinse); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;
        pstm.setString    (ind, username); ind += 1;
        pstm.setString    (ind, password); ind += 1;
        pstm.setString    (ind, nome); ind += 1;
        pstm.setString    (ind, cognome); ind += 1;
        pstm.setString    (ind, ragsoc); ind += 1;
        pstm.setString    (ind, cdgput); ind += 1;
        pstm.setString    (ind, cdente); ind += 1;
        pstm.setString    (ind, tkclie); ind += 1;
        pstm.setString    (ind, tkforn); ind += 1;
        pstm.setString    (ind, indirizzo); ind += 1;
        pstm.setString    (ind, cap); ind += 1;
        pstm.setString    (ind, localita); ind += 1;
        pstm.setString    (ind, piva); ind += 1;
        pstm.setString    (ind, cfisc); ind += 1;
        pstm.setString    (ind, telefono); ind += 1;
        pstm.setString    (ind, fax); ind += 1;
        pstm.setString    (ind, email); ind += 1;
        pstm.setString    (ind, sitointernet); ind += 1;
        pstm.setString    (ind, cellulare); ind += 1;
        pstm.setString    (ind, cdnazi); ind += 1;
        pstm.setString    (ind, nota); ind += 1;
        pstm.setString    (ind, fgweb); ind += 1;
        if (livello == 0 && null_livello){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, livello); ind += 1;
        } 
        pstm.setString    (ind, tipoblocco); ind += 1;
        pstm.setTimestamp (ind, dtblocco); ind += 1;
        pstm.setString    (ind, cdprov); ind += 1;
        pstm.setString    (ind, sesso); ind += 1;
        pstm.setTimestamp (ind, dtnasc); ind += 1;
        pstm.setString    (ind, pariva); ind += 1;
        pstm.setString    (ind, indiri); ind += 1;
        pstm.setString    (ind, comune); ind += 1;
        pstm.setString    (ind, pwd2); ind += 1;
        pstm.setString    (ind, pwd3); ind += 1;
        pstm.setTimestamp (ind, dtpwd); ind += 1;
        pstm.setTimestamp (ind, dtpwd2); ind += 1;
        pstm.setTimestamp (ind, dtpwd3); ind += 1;
        pstm.setTimestamp (ind, dtulconn); ind += 1;
        pstm.setTimestamp (ind, dtwebsync); ind += 1;
        pstm.setTimestamp (ind, dtricpwd); ind += 1;
        pstm.setString    (ind, cdutente_gest); ind += 1;
        pstm.setString    (ind, ftpusername); ind += 1;
        pstm.setString    (ind, ftppwd); ind += 1;
        pstm.setString    (ind, fgfmailpwd); ind += 1;
        pstm.setString    (ind, fgmailbatch); ind += 1;
        pstm.setTimestamp (ind, dtmailbatch); ind += 1;

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

        long ll_tk = f_tabkey.getTabkey( "utente" );

        return ll_tk;

    }




    /****
        executeUpdate: utente
    **/ 


    public int executeUpdate( String     cdutente
                            , String     dsutente
                            , String     pwd
                            , String     idutente
                            , String     cdagen
                            , String     cdcapo
                            , String     username
                            , String     password
                            , String     nome
                            , String     cognome
                            , String     ragsoc
                            , String     cdgput
                            , String     cdente
                            , String     tkclie
                            , String     tkforn
                            , String     indirizzo
                            , String     cap
                            , String     localita
                            , String     piva
                            , String     cfisc
                            , String     telefono
                            , String     fax
                            , String     email
                            , String     sitointernet
                            , String     cellulare
                            , String     cdnazi
                            , String     nota
                            , String     fgweb
                            , long       livello
                            , String     tipoblocco
                            , Timestamp  dtblocco
                            , String     cdprov
                            , String     sesso
                            , Timestamp  dtnasc
                            , String     pariva
                            , String     indiri
                            , String     comune
                            , String     pwd2
                            , String     pwd3
                            , Timestamp  dtpwd
                            , Timestamp  dtpwd2
                            , Timestamp  dtpwd3
                            , Timestamp  dtulconn
                            , Timestamp  dtwebsync
                            , Timestamp  dtricpwd
                            , String     cdutente_gest
                            , String     ftpusername
                            , String     ftppwd
                            , String     fgfmailpwd
                            , String     fgmailbatch
                            , Timestamp  dtmailbatch
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update web.utente  \n";
        l_query  += "         set dsutente = ?  \n";
        l_query  += "           , pwd = ?  \n";
        l_query  += "           , idutente = ?  \n";
        l_query  += "           , cdagen = ?  \n";
        l_query  += "           , cdcapo = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "           , username = ?  \n";
        l_query  += "           , password = ?  \n";
        l_query  += "           , nome = ?  \n";
        l_query  += "           , cognome = ?  \n";
        l_query  += "           , ragsoc = ?  \n";
        l_query  += "           , cdgput = ?  \n";
        l_query  += "           , cdente = ?  \n";
        l_query  += "           , tkclie = ?  \n";
        l_query  += "           , tkforn = ?  \n";
        l_query  += "           , indirizzo = ?  \n";
        l_query  += "           , cap = ?  \n";
        l_query  += "           , localita = ?  \n";
        l_query  += "           , piva = ?  \n";
        l_query  += "           , cfisc = ?  \n";
        l_query  += "           , telefono = ?  \n";
        l_query  += "           , fax = ?  \n";
        l_query  += "           , email = ?  \n";
        l_query  += "           , sitointernet = ?  \n";
        l_query  += "           , cellulare = ?  \n";
        l_query  += "           , cdnazi = ?  \n";
        l_query  += "           , nota = ?  \n";
        l_query  += "           , fgweb = ?  \n";
        l_query  += "           , livello = ?  \n";
        l_query  += "           , tipoblocco = ?  \n";
        l_query  += "           , dtblocco = ?  \n";
        l_query  += "           , cdprov = ?  \n";
        l_query  += "           , sesso = ?  \n";
        l_query  += "           , dtnasc = ?  \n";
        l_query  += "           , pariva = ?  \n";
        l_query  += "           , indiri = ?  \n";
        l_query  += "           , comune = ?  \n";
        l_query  += "           , pwd2 = ?  \n";
        l_query  += "           , pwd3 = ?  \n";
        l_query  += "           , dtpwd = ?  \n";
        l_query  += "           , dtpwd2 = ?  \n";
        l_query  += "           , dtpwd3 = ?  \n";
        l_query  += "           , dtulconn = ?  \n";
        l_query  += "           , dtwebsync = ?  \n";
        l_query  += "           , dtricpwd = ?  \n";
        l_query  += "           , cdutente_gest = ?  \n";
        l_query  += "           , ftpusername = ?  \n";
        l_query  += "           , ftppwd = ?  \n";
        l_query  += "           , fgfmailpwd = ?  \n";
        l_query  += "           , fgmailbatch = ?  \n";
        l_query  += "           , dtmailbatch = ?  \n";
        l_query  += "  where cdutente = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (dsutente.equals("")) dsutente = null;
        if (pwd.equals("")) pwd = null;
        if (idutente.equals("")) idutente = null;
        if (cdagen.equals("")) cdagen = null;
        if (cdcapo.equals("")) cdcapo = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;
        if (username.equals("")) username = null;
        if (password.equals("")) password = null;
        if (nome.equals("")) nome = null;
        if (cognome.equals("")) cognome = null;
        if (ragsoc.equals("")) ragsoc = null;
        if (cdgput.equals("")) cdgput = null;
        if (cdente.equals("")) cdente = null;
        if (tkclie.equals("")) tkclie = null;
        if (tkforn.equals("")) tkforn = null;
        if (indirizzo.equals("")) indirizzo = null;
        if (cap.equals("")) cap = null;
        if (localita.equals("")) localita = null;
        if (piva.equals("")) piva = null;
        if (cfisc.equals("")) cfisc = null;
        if (telefono.equals("")) telefono = null;
        if (fax.equals("")) fax = null;
        if (email.equals("")) email = null;
        if (sitointernet.equals("")) sitointernet = null;
        if (cellulare.equals("")) cellulare = null;
        if (cdnazi.equals("")) cdnazi = null;
        if (nota.equals("")) nota = null;
        if (fgweb.equals("")) fgweb = null;
        if (tipoblocco.equals("")) tipoblocco = null;
        if (cdprov.equals("")) cdprov = null;
        if (sesso.equals("")) sesso = null;
        if (pariva.equals("")) pariva = null;
        if (indiri.equals("")) indiri = null;
        if (comune.equals("")) comune = null;
        if (pwd2.equals("")) pwd2 = null;
        if (pwd3.equals("")) pwd3 = null;
        if (cdutente_gest.equals("")) cdutente_gest = null;
        if (ftpusername.equals("")) ftpusername = null;
        if (ftppwd.equals("")) ftppwd = null;
        if (fgfmailpwd.equals("")) fgfmailpwd = null;
        if (fgmailbatch.equals("")) fgmailbatch = null;


        ind = 1;
        pstm.setString    (ind, dsutente); ind += 1;
        pstm.setString    (ind, pwd); ind += 1;
        pstm.setString    (ind, idutente); ind += 1;
        pstm.setString    (ind, cdagen); ind += 1;
        pstm.setString    (ind, cdcapo); ind += 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;
        pstm.setString    (ind, username); ind += 1;
        pstm.setString    (ind, password); ind += 1;
        pstm.setString    (ind, nome); ind += 1;
        pstm.setString    (ind, cognome); ind += 1;
        pstm.setString    (ind, ragsoc); ind += 1;
        pstm.setString    (ind, cdgput); ind += 1;
        pstm.setString    (ind, cdente); ind += 1;
        pstm.setString    (ind, tkclie); ind += 1;
        pstm.setString    (ind, tkforn); ind += 1;
        pstm.setString    (ind, indirizzo); ind += 1;
        pstm.setString    (ind, cap); ind += 1;
        pstm.setString    (ind, localita); ind += 1;
        pstm.setString    (ind, piva); ind += 1;
        pstm.setString    (ind, cfisc); ind += 1;
        pstm.setString    (ind, telefono); ind += 1;
        pstm.setString    (ind, fax); ind += 1;
        pstm.setString    (ind, email); ind += 1;
        pstm.setString    (ind, sitointernet); ind += 1;
        pstm.setString    (ind, cellulare); ind += 1;
        pstm.setString    (ind, cdnazi); ind += 1;
        pstm.setString    (ind, nota); ind += 1;
        pstm.setString    (ind, fgweb); ind += 1;
        if (livello == 0 && null_livello){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, livello); ind += 1;
        } 
        pstm.setString    (ind, tipoblocco); ind += 1;
        pstm.setTimestamp (ind, dtblocco); ind += 1;
        pstm.setString    (ind, cdprov); ind += 1;
        pstm.setString    (ind, sesso); ind += 1;
        pstm.setTimestamp (ind, dtnasc); ind += 1;
        pstm.setString    (ind, pariva); ind += 1;
        pstm.setString    (ind, indiri); ind += 1;
        pstm.setString    (ind, comune); ind += 1;
        pstm.setString    (ind, pwd2); ind += 1;
        pstm.setString    (ind, pwd3); ind += 1;
        pstm.setTimestamp (ind, dtpwd); ind += 1;
        pstm.setTimestamp (ind, dtpwd2); ind += 1;
        pstm.setTimestamp (ind, dtpwd3); ind += 1;
        pstm.setTimestamp (ind, dtulconn); ind += 1;
        pstm.setTimestamp (ind, dtwebsync); ind += 1;
        pstm.setTimestamp (ind, dtricpwd); ind += 1;
        pstm.setString    (ind, cdutente_gest); ind += 1;
        pstm.setString    (ind, ftpusername); ind += 1;
        pstm.setString    (ind, ftppwd); ind += 1;
        pstm.setString    (ind, fgfmailpwd); ind += 1;
        pstm.setString    (ind, fgmailbatch); ind += 1;
        pstm.setTimestamp (ind, dtmailbatch); ind += 1;

        pstm.setString    (ind, cdutente); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.cdutente = cdutente; 

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public String     cdutente = ""; 






    // --- Impostando a false queste prop. il def. è 0


    public boolean null_livello = true;







}

