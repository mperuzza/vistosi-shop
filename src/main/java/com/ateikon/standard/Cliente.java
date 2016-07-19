package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Cliente extends Atk_sql {

    public Cliente() {

        super();
    }




    /****
        getKey: cliente
    **/ 

    public ResultSet getKey( String     tkclie
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from web.cliente  \n";
        l_query  += "  where tkclie = ? \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString    (ind, tkclie); ind += 1;

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: cliente
    **/ 


    public com.ateikon.structure.Rec_cliente  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_cliente lstr = new com.ateikon.structure.Rec_cliente();

        if (rs == null) return lstr;
        if (rs.getObject("tkclie")!=null) lstr.tkclie = rs.getString    ("tkclie"); 
        if (rs.getObject("cdclie_m")!=null) lstr.cdclie_m = rs.getString    ("cdclie_m"); 
        if (rs.getObject("ragcog")!=null) lstr.ragcog = rs.getString    ("ragcog"); 
        if (rs.getObject("indirizzo")!=null) lstr.indirizzo = rs.getString    ("indirizzo"); 
        if (rs.getObject("cap")!=null) lstr.cap = rs.getString    ("cap"); 
        if (rs.getObject("localita")!=null) lstr.localita = rs.getString    ("localita"); 
        if (rs.getObject("cdprov_m")!=null) lstr.cdprov_m = rs.getString    ("cdprov_m"); 
        if (rs.getObject("dsnazi")!=null) lstr.dsnazi = rs.getString    ("dsnazi"); 
        if (rs.getObject("piva")!=null) lstr.piva = rs.getString    ("piva"); 
        if (rs.getObject("cfisc")!=null) lstr.cfisc = rs.getString    ("cfisc"); 
        if (rs.getObject("cdpagame")!=null) lstr.cdpagame = rs.getString    ("cdpagame"); 
        if (rs.getObject("cdagen_m")!=null) lstr.cdagen_m = rs.getString    ("cdagen_m"); 
        if (rs.getObject("cdsitc_m")!=null) lstr.cdsitc_m = rs.getString    ("cdsitc_m"); 
        if (rs.getObject("fbloccato")!=null) lstr.fbloccato = rs.getString    ("fbloccato"); 
        if (rs.getObject("percaddtra")!=null) lstr.percaddtra = rs.getDouble    ("percaddtra"); 
        if (rs.getObject("cdtrme")!=null) lstr.cdtrme = rs.getString    ("cdtrme"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("cdpagame_m")!=null) lstr.cdpagame_m = rs.getString    ("cdpagame_m"); 
        if (rs.getObject("cdrmpo_m")!=null) lstr.cdrmpo_m = rs.getString    ("cdrmpo_m"); 
        if (rs.getObject("cdvett_m")!=null) lstr.cdvett_m = rs.getString    ("cdvett_m"); 
        if (rs.getObject("cdcllist_m")!=null) lstr.cdcllist_m = rs.getString    ("cdcllist_m"); 
        if (rs.getObject("cdagen")!=null) lstr.cdagen = rs.getString    ("cdagen"); 
        if (rs.getObject("abibap")!=null) lstr.abibap = rs.getString    ("abibap"); 
        if (rs.getObject("cabbap")!=null) lstr.cabbap = rs.getString    ("cabbap"); 
        if (rs.getObject("cdcapo")!=null) lstr.cdcapo = rs.getString    ("cdcapo"); 
        if (rs.getObject("cdfisc")!=null) lstr.cdfisc = rs.getString    ("cdfisc"); 
        if (rs.getObject("cdiva")!=null) lstr.cdiva = rs.getString    ("cdiva"); 
        if (rs.getObject("badesc")!=null) lstr.badesc = rs.getString    ("badesc"); 
        if (rs.getObject("telefono")!=null) lstr.telefono = rs.getString    ("telefono"); 
        if (rs.getObject("fax")!=null) lstr.fax = rs.getString    ("fax"); 
        if (rs.getObject("email")!=null) lstr.email = rs.getString    ("email"); 
        if (rs.getObject("sitointernet")!=null) lstr.sitointernet = rs.getString    ("sitointernet"); 
        if (rs.getObject("cellulare")!=null) lstr.cellulare = rs.getString    ("cellulare"); 
        if (rs.getObject("notagest")!=null) lstr.notagest = rs.getString    ("notagest"); 
        if (rs.getObject("fnewcli")!=null) lstr.fnewcli = rs.getString    ("fnewcli"); 
        if (rs.getObject("tkcliegest")!=null) lstr.tkcliegest = rs.getString    ("tkcliegest"); 
        if (rs.getObject("scocas")!=null) lstr.scocas = rs.getDouble    ("scocas"); 
        if (rs.getObject("sconto1")!=null) lstr.sconto1 = rs.getDouble    ("sconto1"); 
        if (rs.getObject("sconto2")!=null) lstr.sconto2 = rs.getDouble    ("sconto2"); 
        if (rs.getObject("sconto3")!=null) lstr.sconto3 = rs.getDouble    ("sconto3"); 
        if (rs.getObject("sconto4")!=null) lstr.sconto4 = rs.getDouble    ("sconto4"); 
        if (rs.getObject("cliacr")!=null) lstr.cliacr = rs.getString    ("cliacr"); 
        if (rs.getObject("nccorr")!=null) lstr.nccorr = rs.getString    ("nccorr"); 
        if (rs.getObject("cdente")!=null) lstr.cdente = rs.getString    ("cdente"); 
        if (rs.getObject("cdprov")!=null) lstr.cdprov = rs.getString    ("cdprov"); 
        if (rs.getObject("cdnazi")!=null) lstr.cdnazi = rs.getString    ("cdnazi"); 
        if (rs.getObject("cdrmpo")!=null) lstr.cdrmpo = rs.getString    ("cdrmpo"); 
        if (rs.getObject("cdclac")!=null) lstr.cdclac = rs.getString    ("cdclac"); 

        return lstr;
    }




    /****
        execute: cliente
    **/ 


    public int execute ( com.ateikon.structure.Rec_cliente astr) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec =  execute ( astr.tkclie
                          , astr.cdclie_m
                          , astr.ragcog
                          , astr.indirizzo
                          , astr.cap
                          , astr.localita
                          , astr.cdprov_m
                          , astr.dsnazi
                          , astr.piva
                          , astr.cfisc
                          , astr.cdpagame
                          , astr.cdagen_m
                          , astr.cdsitc_m
                          , astr.fbloccato
                          , astr.percaddtra
                          , astr.cdtrme
                          , astr.cdpagame_m
                          , astr.cdrmpo_m
                          , astr.cdvett_m
                          , astr.cdcllist_m
                          , astr.cdagen
                          , astr.abibap
                          , astr.cabbap
                          , astr.cdcapo
                          , astr.cdfisc
                          , astr.cdiva
                          , astr.badesc
                          , astr.telefono
                          , astr.fax
                          , astr.email
                          , astr.sitointernet
                          , astr.cellulare
                          , astr.notagest
                          , astr.fnewcli
                          , astr.tkcliegest
                          , astr.scocas
                          , astr.sconto1
                          , astr.sconto2
                          , astr.sconto3
                          , astr.sconto4
                          , astr.cliacr
                          , astr.nccorr
                          , astr.cdente
                          , astr.cdprov
                          , astr.cdnazi
                          , astr.cdrmpo
                          , astr.cdclac
                           );

        return tot_rec;
    }




    /****
        execute: cliente
    **/ 


    public int execute ( String     tkclie
                       , String     cdclie_m
                       , String     ragcog
                       , String     indirizzo
                       , String     cap
                       , String     localita
                       , String     cdprov_m
                       , String     dsnazi
                       , String     piva
                       , String     cfisc
                       , String     cdpagame
                       , String     cdagen_m
                       , String     cdsitc_m
                       , String     fbloccato
                       , double     percaddtra
                       , String     cdtrme
                       , String     cdpagame_m
                       , String     cdrmpo_m
                       , String     cdvett_m
                       , String     cdcllist_m
                       , String     cdagen
                       , String     abibap
                       , String     cabbap
                       , String     cdcapo
                       , String     cdfisc
                       , String     cdiva
                       , String     badesc
                       , String     telefono
                       , String     fax
                       , String     email
                       , String     sitointernet
                       , String     cellulare
                       , String     notagest
                       , String     fnewcli
                       , String     tkcliegest
                       , double     scocas
                       , double     sconto1
                       , double     sconto2
                       , double     sconto3
                       , double     sconto4
                       , String     cliacr
                       , String     nccorr
                       , String     cdente
                       , String     cdprov
                       , String     cdnazi
                       , String     cdrmpo
                       , String     cdclac
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " select tkclie\n";
        l_query  += "   from web.cliente  \n";
        l_query  += "  where tkclie = ? \n";


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setString    (ind, tkclie); ind += 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            pstm.close();
            tot_rec = executeUpdate ( tkclie
                                    , cdclie_m
                                    , ragcog
                                    , indirizzo
                                    , cap
                                    , localita
                                    , cdprov_m
                                    , dsnazi
                                    , piva
                                    , cfisc
                                    , cdpagame
                                    , cdagen_m
                                    , cdsitc_m
                                    , fbloccato
                                    , percaddtra
                                    , cdtrme
                                    , cdpagame_m
                                    , cdrmpo_m
                                    , cdvett_m
                                    , cdcllist_m
                                    , cdagen
                                    , abibap
                                    , cabbap
                                    , cdcapo
                                    , cdfisc
                                    , cdiva
                                    , badesc
                                    , telefono
                                    , fax
                                    , email
                                    , sitointernet
                                    , cellulare
                                    , notagest
                                    , fnewcli
                                    , tkcliegest
                                    , scocas
                                    , sconto1
                                    , sconto2
                                    , sconto3
                                    , sconto4
                                    , cliacr
                                    , nccorr
                                    , cdente
                                    , cdprov
                                    , cdnazi
                                    , cdrmpo
                                    , cdclac
                                    );
        }else {

            pstm.close();
            tot_rec = executeInsert ( tkclie
                                    , cdclie_m
                                    , ragcog
                                    , indirizzo
                                    , cap
                                    , localita
                                    , cdprov_m
                                    , dsnazi
                                    , piva
                                    , cfisc
                                    , cdpagame
                                    , cdagen_m
                                    , cdsitc_m
                                    , fbloccato
                                    , percaddtra
                                    , cdtrme
                                    , cdpagame_m
                                    , cdrmpo_m
                                    , cdvett_m
                                    , cdcllist_m
                                    , cdagen
                                    , abibap
                                    , cabbap
                                    , cdcapo
                                    , cdfisc
                                    , cdiva
                                    , badesc
                                    , telefono
                                    , fax
                                    , email
                                    , sitointernet
                                    , cellulare
                                    , notagest
                                    , fnewcli
                                    , tkcliegest
                                    , scocas
                                    , sconto1
                                    , sconto2
                                    , sconto3
                                    , sconto4
                                    , cliacr
                                    , nccorr
                                    , cdente
                                    , cdprov
                                    , cdnazi
                                    , cdrmpo
                                    , cdclac
                                    );
        }

        if (tot_rec != 1){

            m_connection.rollback();
            throw new Exception("Errore Agg. cliente! ");
        }

        return tot_rec;
    }




    /****
        executeInsert: cliente
    **/ 


    public int executeInsert( String     tkclie
                            , String     cdclie_m
                            , String     ragcog
                            , String     indirizzo
                            , String     cap
                            , String     localita
                            , String     cdprov_m
                            , String     dsnazi
                            , String     piva
                            , String     cfisc
                            , String     cdpagame
                            , String     cdagen_m
                            , String     cdsitc_m
                            , String     fbloccato
                            , double     percaddtra
                            , String     cdtrme
                            , String     cdpagame_m
                            , String     cdrmpo_m
                            , String     cdvett_m
                            , String     cdcllist_m
                            , String     cdagen
                            , String     abibap
                            , String     cabbap
                            , String     cdcapo
                            , String     cdfisc
                            , String     cdiva
                            , String     badesc
                            , String     telefono
                            , String     fax
                            , String     email
                            , String     sitointernet
                            , String     cellulare
                            , String     notagest
                            , String     fnewcli
                            , String     tkcliegest
                            , double     scocas
                            , double     sconto1
                            , double     sconto2
                            , double     sconto3
                            , double     sconto4
                            , String     cliacr
                            , String     nccorr
                            , String     cdente
                            , String     cdprov
                            , String     cdnazi
                            , String     cdrmpo
                            , String     cdclac
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        long ll_tk = getNew_token( );

        if (ll_tk <= 0){
            throw new Exception ("Error Keypool (cliente): "+ll_tk );
        }

        this.tkclie = of_token(ll_tk);
        tkclie = this.tkclie;



        l_query   = "";
        l_query  += " insert into web.cliente ( \n";
        l_query  += "             tkclie   \n";
        l_query  += "           , cdclie_m   \n";
        l_query  += "           , ragcog   \n";
        l_query  += "           , indirizzo   \n";
        l_query  += "           , cap   \n";
        l_query  += "           , localita   \n";
        l_query  += "           , cdprov_m   \n";
        l_query  += "           , dsnazi   \n";
        l_query  += "           , piva   \n";
        l_query  += "           , cfisc   \n";
        l_query  += "           , cdpagame   \n";
        l_query  += "           , cdagen_m   \n";
        l_query  += "           , cdsitc_m   \n";
        l_query  += "           , fbloccato   \n";
        l_query  += "           , percaddtra   \n";
        l_query  += "           , cdtrme   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "           , cdpagame_m   \n";
        l_query  += "           , cdrmpo_m   \n";
        l_query  += "           , cdvett_m   \n";
        l_query  += "           , cdcllist_m   \n";
        l_query  += "           , cdagen   \n";
        l_query  += "           , abibap   \n";
        l_query  += "           , cabbap   \n";
        l_query  += "           , cdcapo   \n";
        l_query  += "           , cdfisc   \n";
        l_query  += "           , cdiva   \n";
        l_query  += "           , badesc   \n";
        l_query  += "           , telefono   \n";
        l_query  += "           , fax   \n";
        l_query  += "           , email   \n";
        l_query  += "           , sitointernet   \n";
        l_query  += "           , cellulare   \n";
        l_query  += "           , notagest   \n";
        l_query  += "           , fnewcli   \n";
        l_query  += "           , tkcliegest   \n";
        l_query  += "           , scocas   \n";
        l_query  += "           , sconto1   \n";
        l_query  += "           , sconto2   \n";
        l_query  += "           , sconto3   \n";
        l_query  += "           , sconto4   \n";
        l_query  += "           , cliacr   \n";
        l_query  += "           , nccorr   \n";
        l_query  += "           , cdente   \n";
        l_query  += "           , cdprov   \n";
        l_query  += "           , cdnazi   \n";
        l_query  += "           , cdrmpo   \n";
        l_query  += "           , cdclac   \n";
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
        l_query  += "           , ?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cdclie_m.equals("")) cdclie_m = null;
        if (ragcog.equals("")) ragcog = null;
        if (indirizzo.equals("")) indirizzo = null;
        if (cap.equals("")) cap = null;
        if (localita.equals("")) localita = null;
        if (cdprov_m.equals("")) cdprov_m = null;
        if (dsnazi.equals("")) dsnazi = null;
        if (piva.equals("")) piva = null;
        if (cfisc.equals("")) cfisc = null;
        if (cdpagame.equals("")) cdpagame = null;
        if (cdagen_m.equals("")) cdagen_m = null;
        if (cdsitc_m.equals("")) cdsitc_m = null;
        if (fbloccato.equals("")) fbloccato = null;
        if (cdtrme.equals("")) cdtrme = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;
        if (cdpagame_m.equals("")) cdpagame_m = null;
        if (cdrmpo_m.equals("")) cdrmpo_m = null;
        if (cdvett_m.equals("")) cdvett_m = null;
        if (cdcllist_m.equals("")) cdcllist_m = null;
        if (cdagen.equals("")) cdagen = null;
        if (abibap.equals("")) abibap = null;
        if (cabbap.equals("")) cabbap = null;
        if (cdcapo.equals("")) cdcapo = null;
        if (cdfisc.equals("")) cdfisc = null;
        if (cdiva.equals("")) cdiva = null;
        if (badesc.equals("")) badesc = null;
        if (telefono.equals("")) telefono = null;
        if (fax.equals("")) fax = null;
        if (email.equals("")) email = null;
        if (sitointernet.equals("")) sitointernet = null;
        if (cellulare.equals("")) cellulare = null;
        if (notagest.equals("")) notagest = null;
        if (fnewcli.equals("")) fnewcli = null;
        if (tkcliegest.equals("")) tkcliegest = null;
        if (cliacr.equals("")) cliacr = null;
        if (nccorr.equals("")) nccorr = null;
        if (cdente.equals("")) cdente = null;
        if (cdprov.equals("")) cdprov = null;
        if (cdnazi.equals("")) cdnazi = null;
        if (cdrmpo.equals("")) cdrmpo = null;
        if (cdclac.equals("")) cdclac = null;


        ind = 1;
        pstm.setString    (ind, tkclie); ind += 1;
        pstm.setString    (ind, cdclie_m); ind += 1;
        pstm.setString    (ind, ragcog); ind += 1;
        pstm.setString    (ind, indirizzo); ind += 1;
        pstm.setString    (ind, cap); ind += 1;
        pstm.setString    (ind, localita); ind += 1;
        pstm.setString    (ind, cdprov_m); ind += 1;
        pstm.setString    (ind, dsnazi); ind += 1;
        pstm.setString    (ind, piva); ind += 1;
        pstm.setString    (ind, cfisc); ind += 1;
        pstm.setString    (ind, cdpagame); ind += 1;
        pstm.setString    (ind, cdagen_m); ind += 1;
        pstm.setString    (ind, cdsitc_m); ind += 1;
        pstm.setString    (ind, fbloccato); ind += 1;
        if (percaddtra == 0 && null_percaddtra){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, percaddtra); ind += 1;
        } 
        pstm.setString    (ind, cdtrme); ind += 1;
        pstm.setString    (ind, cdazie); ind += 1;
        pstm.setString    (ind, cddipa); ind += 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtinse); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;
        pstm.setString    (ind, cdpagame_m); ind += 1;
        pstm.setString    (ind, cdrmpo_m); ind += 1;
        pstm.setString    (ind, cdvett_m); ind += 1;
        pstm.setString    (ind, cdcllist_m); ind += 1;
        pstm.setString    (ind, cdagen); ind += 1;
        pstm.setString    (ind, abibap); ind += 1;
        pstm.setString    (ind, cabbap); ind += 1;
        pstm.setString    (ind, cdcapo); ind += 1;
        pstm.setString    (ind, cdfisc); ind += 1;
        pstm.setString    (ind, cdiva); ind += 1;
        pstm.setString    (ind, badesc); ind += 1;
        pstm.setString    (ind, telefono); ind += 1;
        pstm.setString    (ind, fax); ind += 1;
        pstm.setString    (ind, email); ind += 1;
        pstm.setString    (ind, sitointernet); ind += 1;
        pstm.setString    (ind, cellulare); ind += 1;
        pstm.setString    (ind, notagest); ind += 1;
        pstm.setString    (ind, fnewcli); ind += 1;
        pstm.setString    (ind, tkcliegest); ind += 1;
        if (scocas == 0 && null_scocas){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, scocas); ind += 1;
        } 
        if (sconto1 == 0 && null_sconto1){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, sconto1); ind += 1;
        } 
        if (sconto2 == 0 && null_sconto2){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, sconto2); ind += 1;
        } 
        if (sconto3 == 0 && null_sconto3){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, sconto3); ind += 1;
        } 
        if (sconto4 == 0 && null_sconto4){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, sconto4); ind += 1;
        } 
        pstm.setString    (ind, cliacr); ind += 1;
        pstm.setString    (ind, nccorr); ind += 1;
        pstm.setString    (ind, cdente); ind += 1;
        pstm.setString    (ind, cdprov); ind += 1;
        pstm.setString    (ind, cdnazi); ind += 1;
        pstm.setString    (ind, cdrmpo); ind += 1;
        pstm.setString    (ind, cdclac); ind += 1;

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

        long ll_tk = f_tabkey.getTabkey( "cliente" );

        return ll_tk;

    }




    /****
        executeUpdate: cliente
    **/ 


    public int executeUpdate( String     tkclie
                            , String     cdclie_m
                            , String     ragcog
                            , String     indirizzo
                            , String     cap
                            , String     localita
                            , String     cdprov_m
                            , String     dsnazi
                            , String     piva
                            , String     cfisc
                            , String     cdpagame
                            , String     cdagen_m
                            , String     cdsitc_m
                            , String     fbloccato
                            , double     percaddtra
                            , String     cdtrme
                            , String     cdpagame_m
                            , String     cdrmpo_m
                            , String     cdvett_m
                            , String     cdcllist_m
                            , String     cdagen
                            , String     abibap
                            , String     cabbap
                            , String     cdcapo
                            , String     cdfisc
                            , String     cdiva
                            , String     badesc
                            , String     telefono
                            , String     fax
                            , String     email
                            , String     sitointernet
                            , String     cellulare
                            , String     notagest
                            , String     fnewcli
                            , String     tkcliegest
                            , double     scocas
                            , double     sconto1
                            , double     sconto2
                            , double     sconto3
                            , double     sconto4
                            , String     cliacr
                            , String     nccorr
                            , String     cdente
                            , String     cdprov
                            , String     cdnazi
                            , String     cdrmpo
                            , String     cdclac
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update web.cliente  \n";
        l_query  += "         set cdclie_m = ?  \n";
        l_query  += "           , ragcog = ?  \n";
        l_query  += "           , indirizzo = ?  \n";
        l_query  += "           , cap = ?  \n";
        l_query  += "           , localita = ?  \n";
        l_query  += "           , cdprov_m = ?  \n";
        l_query  += "           , dsnazi = ?  \n";
        l_query  += "           , piva = ?  \n";
        l_query  += "           , cfisc = ?  \n";
        l_query  += "           , cdpagame = ?  \n";
        l_query  += "           , cdagen_m = ?  \n";
        l_query  += "           , cdsitc_m = ?  \n";
        l_query  += "           , fbloccato = ?  \n";
        l_query  += "           , percaddtra = ?  \n";
        l_query  += "           , cdtrme = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "           , cdpagame_m = ?  \n";
        l_query  += "           , cdrmpo_m = ?  \n";
        l_query  += "           , cdvett_m = ?  \n";
        l_query  += "           , cdcllist_m = ?  \n";
        l_query  += "           , cdagen = ?  \n";
        l_query  += "           , abibap = ?  \n";
        l_query  += "           , cabbap = ?  \n";
        l_query  += "           , cdcapo = ?  \n";
        l_query  += "           , cdfisc = ?  \n";
        l_query  += "           , cdiva = ?  \n";
        l_query  += "           , badesc = ?  \n";
        l_query  += "           , telefono = ?  \n";
        l_query  += "           , fax = ?  \n";
        l_query  += "           , email = ?  \n";
        l_query  += "           , sitointernet = ?  \n";
        l_query  += "           , cellulare = ?  \n";
        l_query  += "           , notagest = ?  \n";
        l_query  += "           , fnewcli = ?  \n";
        l_query  += "           , tkcliegest = ?  \n";
        l_query  += "           , scocas = ?  \n";
        l_query  += "           , sconto1 = ?  \n";
        l_query  += "           , sconto2 = ?  \n";
        l_query  += "           , sconto3 = ?  \n";
        l_query  += "           , sconto4 = ?  \n";
        l_query  += "           , cliacr = ?  \n";
        l_query  += "           , nccorr = ?  \n";
        l_query  += "           , cdente = ?  \n";
        l_query  += "           , cdprov = ?  \n";
        l_query  += "           , cdnazi = ?  \n";
        l_query  += "           , cdrmpo = ?  \n";
        l_query  += "           , cdclac = ?  \n";
        l_query  += "  where tkclie = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cdclie_m.equals("")) cdclie_m = null;
        if (ragcog.equals("")) ragcog = null;
        if (indirizzo.equals("")) indirizzo = null;
        if (cap.equals("")) cap = null;
        if (localita.equals("")) localita = null;
        if (cdprov_m.equals("")) cdprov_m = null;
        if (dsnazi.equals("")) dsnazi = null;
        if (piva.equals("")) piva = null;
        if (cfisc.equals("")) cfisc = null;
        if (cdpagame.equals("")) cdpagame = null;
        if (cdagen_m.equals("")) cdagen_m = null;
        if (cdsitc_m.equals("")) cdsitc_m = null;
        if (fbloccato.equals("")) fbloccato = null;
        if (cdtrme.equals("")) cdtrme = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;
        if (cdpagame_m.equals("")) cdpagame_m = null;
        if (cdrmpo_m.equals("")) cdrmpo_m = null;
        if (cdvett_m.equals("")) cdvett_m = null;
        if (cdcllist_m.equals("")) cdcllist_m = null;
        if (cdagen.equals("")) cdagen = null;
        if (abibap.equals("")) abibap = null;
        if (cabbap.equals("")) cabbap = null;
        if (cdcapo.equals("")) cdcapo = null;
        if (cdfisc.equals("")) cdfisc = null;
        if (cdiva.equals("")) cdiva = null;
        if (badesc.equals("")) badesc = null;
        if (telefono.equals("")) telefono = null;
        if (fax.equals("")) fax = null;
        if (email.equals("")) email = null;
        if (sitointernet.equals("")) sitointernet = null;
        if (cellulare.equals("")) cellulare = null;
        if (notagest.equals("")) notagest = null;
        if (fnewcli.equals("")) fnewcli = null;
        if (tkcliegest.equals("")) tkcliegest = null;
        if (cliacr.equals("")) cliacr = null;
        if (nccorr.equals("")) nccorr = null;
        if (cdente.equals("")) cdente = null;
        if (cdprov.equals("")) cdprov = null;
        if (cdnazi.equals("")) cdnazi = null;
        if (cdrmpo.equals("")) cdrmpo = null;
        if (cdclac.equals("")) cdclac = null;


        ind = 1;
        pstm.setString    (ind, cdclie_m); ind += 1;
        pstm.setString    (ind, ragcog); ind += 1;
        pstm.setString    (ind, indirizzo); ind += 1;
        pstm.setString    (ind, cap); ind += 1;
        pstm.setString    (ind, localita); ind += 1;
        pstm.setString    (ind, cdprov_m); ind += 1;
        pstm.setString    (ind, dsnazi); ind += 1;
        pstm.setString    (ind, piva); ind += 1;
        pstm.setString    (ind, cfisc); ind += 1;
        pstm.setString    (ind, cdpagame); ind += 1;
        pstm.setString    (ind, cdagen_m); ind += 1;
        pstm.setString    (ind, cdsitc_m); ind += 1;
        pstm.setString    (ind, fbloccato); ind += 1;
        if (percaddtra == 0 && null_percaddtra){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, percaddtra); ind += 1;
        } 
        pstm.setString    (ind, cdtrme); ind += 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;
        pstm.setString    (ind, cdpagame_m); ind += 1;
        pstm.setString    (ind, cdrmpo_m); ind += 1;
        pstm.setString    (ind, cdvett_m); ind += 1;
        pstm.setString    (ind, cdcllist_m); ind += 1;
        pstm.setString    (ind, cdagen); ind += 1;
        pstm.setString    (ind, abibap); ind += 1;
        pstm.setString    (ind, cabbap); ind += 1;
        pstm.setString    (ind, cdcapo); ind += 1;
        pstm.setString    (ind, cdfisc); ind += 1;
        pstm.setString    (ind, cdiva); ind += 1;
        pstm.setString    (ind, badesc); ind += 1;
        pstm.setString    (ind, telefono); ind += 1;
        pstm.setString    (ind, fax); ind += 1;
        pstm.setString    (ind, email); ind += 1;
        pstm.setString    (ind, sitointernet); ind += 1;
        pstm.setString    (ind, cellulare); ind += 1;
        pstm.setString    (ind, notagest); ind += 1;
        pstm.setString    (ind, fnewcli); ind += 1;
        pstm.setString    (ind, tkcliegest); ind += 1;
        if (scocas == 0 && null_scocas){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, scocas); ind += 1;
        } 
        if (sconto1 == 0 && null_sconto1){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, sconto1); ind += 1;
        } 
        if (sconto2 == 0 && null_sconto2){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, sconto2); ind += 1;
        } 
        if (sconto3 == 0 && null_sconto3){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, sconto3); ind += 1;
        } 
        if (sconto4 == 0 && null_sconto4){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, sconto4); ind += 1;
        } 
        pstm.setString    (ind, cliacr); ind += 1;
        pstm.setString    (ind, nccorr); ind += 1;
        pstm.setString    (ind, cdente); ind += 1;
        pstm.setString    (ind, cdprov); ind += 1;
        pstm.setString    (ind, cdnazi); ind += 1;
        pstm.setString    (ind, cdrmpo); ind += 1;
        pstm.setString    (ind, cdclac); ind += 1;

        pstm.setString    (ind, tkclie); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.tkclie = tkclie; 

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public String     tkclie = ""; 






    // --- Impostando a false queste prop. il def. è 0


    public boolean null_percaddtra = true;
    public boolean null_scocas = true;
    public boolean null_sconto1 = true;
    public boolean null_sconto2 = true;
    public boolean null_sconto3 = true;
    public boolean null_sconto4 = true;







}

