package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Matr_anagrafica extends Atk_sql {

    public Matr_anagrafica() {

        super();
    }




    /****
        getKey: matr_anagrafica
    **/ 

    public ResultSet getKey( long       tkmatricola
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.matr_anagrafica  \n";
        l_query  += "  where tkmatricola = ? \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setLong      (ind, tkmatricola); ind += 1;

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        execute: matr_anagrafica
    **/ 


    public int execute ( com.ateikon.structure.Rec_matr_anagrafica astr) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        return execute ( astr.tkmatricola
                       , astr.cdmatricola_m
                       , astr.cdmatrprod
                       , astr.dsmatricola
                       , astr.dsmatrforn
                       , astr.annomatr
                       , astr.cdarti
                       , astr.cdtmatr
                       , astr.cdtusura
                       , astr.cdpropr
                       , astr.cdstmatr
                       , astr.note
                       , astr.dtcrematr
                       , astr.dtscadmatr
                       , astr.priorita
                       , astr.tkforn
                       , astr.dtstato
                       , astr.cdunim
                       , astr.cdtins
                       , astr.cdubmatr
                       , astr.fpaccodist
                       , astr.fmisvar
                       , astr.dimenamin
                       , astr.dimenamax
                       , astr.dimenbmin
                       , astr.dimenbmax
                       , astr.dimencmin
                       , astr.dimencmax
                       , astr.cdunim_acq
                       , astr.qtaincr
                       , astr.cdtins_acq
                        );


    }




    /****
        execute: matr_anagrafica
    **/ 


    public int execute ( long       tkmatricola
                       , String     cdmatricola_m
                       , String     cdmatrprod
                       , String     dsmatricola
                       , String     dsmatrforn
                       , String     annomatr
                       , String     cdarti
                       , String     cdtmatr
                       , String     cdtusura
                       , String     cdpropr
                       , String     cdstmatr
                       , String     note
                       , Timestamp  dtcrematr
                       , Timestamp  dtscadmatr
                       , long       priorita
                       , String     tkforn
                       , Timestamp  dtstato
                       , String     cdunim
                       , String     cdtins
                       , String     cdubmatr
                       , String     fpaccodist
                       , String     fmisvar
                       , double     dimenamin
                       , double     dimenamax
                       , double     dimenbmin
                       , double     dimenbmax
                       , double     dimencmin
                       , double     dimencmax
                       , String     cdunim_acq
                       , double     qtaincr
                       , String     cdtins_acq
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " select tkmatricola\n";
        l_query  += "   from pgmr.matr_anagrafica  \n";
        l_query  += "  where tkmatricola = ? \n";


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setLong      (ind, tkmatricola); ind += 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            pstm.close();
            tot_rec = executeUpdate ( tkmatricola
                                    , cdmatricola_m
                                    , cdmatrprod
                                    , dsmatricola
                                    , dsmatrforn
                                    , annomatr
                                    , cdarti
                                    , cdtmatr
                                    , cdtusura
                                    , cdpropr
                                    , cdstmatr
                                    , note
                                    , dtcrematr
                                    , dtscadmatr
                                    , priorita
                                    , tkforn
                                    , dtstato
                                    , cdunim
                                    , cdtins
                                    , cdubmatr
                                    , fpaccodist
                                    , fmisvar
                                    , dimenamin
                                    , dimenamax
                                    , dimenbmin
                                    , dimenbmax
                                    , dimencmin
                                    , dimencmax
                                    , cdunim_acq
                                    , qtaincr
                                    , cdtins_acq
                                    );
        }else {

            pstm.close();
            tot_rec = executeInsert ( tkmatricola
                                    , cdmatricola_m
                                    , cdmatrprod
                                    , dsmatricola
                                    , dsmatrforn
                                    , annomatr
                                    , cdarti
                                    , cdtmatr
                                    , cdtusura
                                    , cdpropr
                                    , cdstmatr
                                    , note
                                    , dtcrematr
                                    , dtscadmatr
                                    , priorita
                                    , tkforn
                                    , dtstato
                                    , cdunim
                                    , cdtins
                                    , cdubmatr
                                    , fpaccodist
                                    , fmisvar
                                    , dimenamin
                                    , dimenamax
                                    , dimenbmin
                                    , dimenbmax
                                    , dimencmin
                                    , dimencmax
                                    , cdunim_acq
                                    , qtaincr
                                    , cdtins_acq
                                    );
        }

        if (tot_rec != 1){

            m_connection.rollback();
            throw new Exception("Errore Agg. matr_anagrafica! ");
        }

        return tot_rec;
    }




    /****
        executeInsert: matr_anagrafica
    **/ 


    public int executeInsert( long       tkmatricola
                            , String     cdmatricola_m
                            , String     cdmatrprod
                            , String     dsmatricola
                            , String     dsmatrforn
                            , String     annomatr
                            , String     cdarti
                            , String     cdtmatr
                            , String     cdtusura
                            , String     cdpropr
                            , String     cdstmatr
                            , String     note
                            , Timestamp  dtcrematr
                            , Timestamp  dtscadmatr
                            , long       priorita
                            , String     tkforn
                            , Timestamp  dtstato
                            , String     cdunim
                            , String     cdtins
                            , String     cdubmatr
                            , String     fpaccodist
                            , String     fmisvar
                            , double     dimenamin
                            , double     dimenamax
                            , double     dimenbmin
                            , double     dimenbmax
                            , double     dimencmin
                            , double     dimencmax
                            , String     cdunim_acq
                            , double     qtaincr
                            , String     cdtins_acq
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        F_tabkey f_tabkey = new F_tabkey();
        setProfilo((Atk_sql) f_tabkey );

        long ll_tk = f_tabkey.getTabkey( "matr_anagrafica" );

        if (ll_tk <= 0){
            throw new Exception ("Error Keypool (matr_anagrafica): "+ll_tk );
        }

        this.tkmatricola = ll_tk;
        tkmatricola = this.tkmatricola;



        l_query   = "";
        l_query  += " insert into pgmr.matr_anagrafica ( \n";
        l_query  += "             tkmatricola   \n";
        l_query  += "           , cdmatricola_m   \n";
        l_query  += "           , cdmatrprod   \n";
        l_query  += "           , dsmatricola   \n";
        l_query  += "           , dsmatrforn   \n";
        l_query  += "           , annomatr   \n";
        l_query  += "           , cdarti   \n";
        l_query  += "           , cdtmatr   \n";
        l_query  += "           , cdtusura   \n";
        l_query  += "           , cdpropr   \n";
        l_query  += "           , cdstmatr   \n";
        l_query  += "           , note   \n";
        l_query  += "           , dtcrematr   \n";
        l_query  += "           , dtscadmatr   \n";
        l_query  += "           , priorita   \n";
        l_query  += "           , tkforn   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "           , dtstato   \n";
        l_query  += "           , cdunim   \n";
        l_query  += "           , cdtins   \n";
        l_query  += "           , cdubmatr   \n";
        l_query  += "           , fpaccodist   \n";
        l_query  += "           , fmisvar   \n";
        l_query  += "           , dimenamin   \n";
        l_query  += "           , dimenamax   \n";
        l_query  += "           , dimenbmin   \n";
        l_query  += "           , dimenbmax   \n";
        l_query  += "           , dimencmin   \n";
        l_query  += "           , dimencmax   \n";
        l_query  += "           , cdunim_acq   \n";
        l_query  += "           , qtaincr   \n";
        l_query  += "           , cdtins_acq   \n";
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


        if (cdmatricola_m.equals("")) cdmatricola_m = null;
        if (cdmatrprod.equals("")) cdmatrprod = null;
        if (dsmatricola.equals("")) dsmatricola = null;
        if (dsmatrforn.equals("")) dsmatrforn = null;
        if (annomatr.equals("")) annomatr = null;
        if (cdarti.equals("")) cdarti = null;
        if (cdtmatr.equals("")) cdtmatr = null;
        if (cdtusura.equals("")) cdtusura = null;
        if (cdpropr.equals("")) cdpropr = null;
        if (cdstmatr.equals("")) cdstmatr = null;
        if (note.equals("")) note = null;
        if (tkforn.equals("")) tkforn = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;
        if (cdunim.equals("")) cdunim = null;
        if (cdtins.equals("")) cdtins = null;
        if (cdubmatr.equals("")) cdubmatr = null;
        if (fpaccodist.equals("")) fpaccodist = null;
        if (fmisvar.equals("")) fmisvar = null;
        if (cdunim_acq.equals("")) cdunim_acq = null;
        if (cdtins_acq.equals("")) cdtins_acq = null;


        ind = 1;
        pstm.setLong      (ind, tkmatricola); ind += 1;
        pstm.setString    (ind, cdmatricola_m); ind += 1;
        pstm.setString    (ind, cdmatrprod); ind += 1;
        pstm.setString    (ind, dsmatricola); ind += 1;
        pstm.setString    (ind, dsmatrforn); ind += 1;
        pstm.setString    (ind, annomatr); ind += 1;
        pstm.setString    (ind, cdarti); ind += 1;
        pstm.setString    (ind, cdtmatr); ind += 1;
        pstm.setString    (ind, cdtusura); ind += 1;
        pstm.setString    (ind, cdpropr); ind += 1;
        pstm.setString    (ind, cdstmatr); ind += 1;
        pstm.setString    (ind, note); ind += 1;
        pstm.setTimestamp (ind, dtcrematr); ind += 1;
        pstm.setTimestamp (ind, dtscadmatr); ind += 1;
        if (priorita == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, priorita); ind += 1;
        } 
        pstm.setString    (ind, tkforn); ind += 1;
        pstm.setString    (ind, cdazie); ind += 1;
        pstm.setString    (ind, cddipa); ind += 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtinse); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;
        pstm.setTimestamp (ind, dtstato); ind += 1;
        pstm.setString    (ind, cdunim); ind += 1;
        pstm.setString    (ind, cdtins); ind += 1;
        pstm.setString    (ind, cdubmatr); ind += 1;
        pstm.setString    (ind, fpaccodist); ind += 1;
        pstm.setString    (ind, fmisvar); ind += 1;
        if (dimenamin == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, dimenamin); ind += 1;
        } 
        if (dimenamax == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, dimenamax); ind += 1;
        } 
        if (dimenbmin == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, dimenbmin); ind += 1;
        } 
        if (dimenbmax == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, dimenbmax); ind += 1;
        } 
        if (dimencmin == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, dimencmin); ind += 1;
        } 
        if (dimencmax == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, dimencmax); ind += 1;
        } 
        pstm.setString    (ind, cdunim_acq); ind += 1;
        if (qtaincr == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, qtaincr); ind += 1;
        } 
        pstm.setString    (ind, cdtins_acq); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        return tot_rec;

    }




    /****
        executeUpdate: matr_anagrafica
    **/ 


    public int executeUpdate( long       tkmatricola
                            , String     cdmatricola_m
                            , String     cdmatrprod
                            , String     dsmatricola
                            , String     dsmatrforn
                            , String     annomatr
                            , String     cdarti
                            , String     cdtmatr
                            , String     cdtusura
                            , String     cdpropr
                            , String     cdstmatr
                            , String     note
                            , Timestamp  dtcrematr
                            , Timestamp  dtscadmatr
                            , long       priorita
                            , String     tkforn
                            , Timestamp  dtstato
                            , String     cdunim
                            , String     cdtins
                            , String     cdubmatr
                            , String     fpaccodist
                            , String     fmisvar
                            , double     dimenamin
                            , double     dimenamax
                            , double     dimenbmin
                            , double     dimenbmax
                            , double     dimencmin
                            , double     dimencmax
                            , String     cdunim_acq
                            , double     qtaincr
                            , String     cdtins_acq
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.matr_anagrafica  \n";
        l_query  += "         set cdmatricola_m = ?  \n";
        l_query  += "           , cdmatrprod = ?  \n";
        l_query  += "           , dsmatricola = ?  \n";
        l_query  += "           , dsmatrforn = ?  \n";
        l_query  += "           , annomatr = ?  \n";
        l_query  += "           , cdarti = ?  \n";
        l_query  += "           , cdtmatr = ?  \n";
        l_query  += "           , cdtusura = ?  \n";
        l_query  += "           , cdpropr = ?  \n";
        l_query  += "           , cdstmatr = ?  \n";
        l_query  += "           , note = ?  \n";
        l_query  += "           , dtcrematr = ?  \n";
        l_query  += "           , dtscadmatr = ?  \n";
        l_query  += "           , priorita = ?  \n";
        l_query  += "           , tkforn = ?  \n";
        l_query  += "           , cdazie = ?  \n";
        l_query  += "           , cddipa = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtinse = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "           , dtstato = ?  \n";
        l_query  += "           , cdunim = ?  \n";
        l_query  += "           , cdtins = ?  \n";
        l_query  += "           , cdubmatr = ?  \n";
        l_query  += "           , fpaccodist = ?  \n";
        l_query  += "           , fmisvar = ?  \n";
        l_query  += "           , dimenamin = ?  \n";
        l_query  += "           , dimenamax = ?  \n";
        l_query  += "           , dimenbmin = ?  \n";
        l_query  += "           , dimenbmax = ?  \n";
        l_query  += "           , dimencmin = ?  \n";
        l_query  += "           , dimencmax = ?  \n";
        l_query  += "           , cdunim_acq = ?  \n";
        l_query  += "           , qtaincr = ?  \n";
        l_query  += "           , cdtins_acq = ?  \n";
        l_query  += "  where tkmatricola = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cdmatricola_m.equals("")) cdmatricola_m = null;
        if (cdmatrprod.equals("")) cdmatrprod = null;
        if (dsmatricola.equals("")) dsmatricola = null;
        if (dsmatrforn.equals("")) dsmatrforn = null;
        if (annomatr.equals("")) annomatr = null;
        if (cdarti.equals("")) cdarti = null;
        if (cdtmatr.equals("")) cdtmatr = null;
        if (cdtusura.equals("")) cdtusura = null;
        if (cdpropr.equals("")) cdpropr = null;
        if (cdstmatr.equals("")) cdstmatr = null;
        if (note.equals("")) note = null;
        if (tkforn.equals("")) tkforn = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;
        if (cdunim.equals("")) cdunim = null;
        if (cdtins.equals("")) cdtins = null;
        if (cdubmatr.equals("")) cdubmatr = null;
        if (fpaccodist.equals("")) fpaccodist = null;
        if (fmisvar.equals("")) fmisvar = null;
        if (cdunim_acq.equals("")) cdunim_acq = null;
        if (cdtins_acq.equals("")) cdtins_acq = null;


        ind = 1;
        pstm.setString    (ind, cdmatricola_m); ind += 1;
        pstm.setString    (ind, cdmatrprod); ind += 1;
        pstm.setString    (ind, dsmatricola); ind += 1;
        pstm.setString    (ind, dsmatrforn); ind += 1;
        pstm.setString    (ind, annomatr); ind += 1;
        pstm.setString    (ind, cdarti); ind += 1;
        pstm.setString    (ind, cdtmatr); ind += 1;
        pstm.setString    (ind, cdtusura); ind += 1;
        pstm.setString    (ind, cdpropr); ind += 1;
        pstm.setString    (ind, cdstmatr); ind += 1;
        pstm.setString    (ind, note); ind += 1;
        pstm.setTimestamp (ind, dtcrematr); ind += 1;
        pstm.setTimestamp (ind, dtscadmatr); ind += 1;
        if (priorita == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, priorita); ind += 1;
        } 
        pstm.setString    (ind, tkforn); ind += 1;
        pstm.setString    (ind, cdazie); ind += 1;
        pstm.setString    (ind, cddipa); ind += 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtinse); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;
        pstm.setTimestamp (ind, dtstato); ind += 1;
        pstm.setString    (ind, cdunim); ind += 1;
        pstm.setString    (ind, cdtins); ind += 1;
        pstm.setString    (ind, cdubmatr); ind += 1;
        pstm.setString    (ind, fpaccodist); ind += 1;
        pstm.setString    (ind, fmisvar); ind += 1;
        if (dimenamin == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, dimenamin); ind += 1;
        } 
        if (dimenamax == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, dimenamax); ind += 1;
        } 
        if (dimenbmin == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, dimenbmin); ind += 1;
        } 
        if (dimenbmax == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, dimenbmax); ind += 1;
        } 
        if (dimencmin == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, dimencmin); ind += 1;
        } 
        if (dimencmax == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, dimencmax); ind += 1;
        } 
        pstm.setString    (ind, cdunim_acq); ind += 1;
        if (qtaincr == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, qtaincr); ind += 1;
        } 
        pstm.setString    (ind, cdtins_acq); ind += 1;
        pstm.setLong      (ind, tkmatricola); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public long       tkmatricola = 0; 







}

