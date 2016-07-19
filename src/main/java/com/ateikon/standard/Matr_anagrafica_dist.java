package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Matr_anagrafica_dist extends Atk_sql {

    public Matr_anagrafica_dist() {

        super();
    }




    /****
        getKey: matr_anagrafica_dist
    **/ 

    public ResultSet getKey( long       tkmatrdist
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.matr_anagrafica_dist  \n";
        l_query  += "  where tkmatrdist = ? \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setLong      (ind, tkmatrdist); ind += 1;

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        execute: matr_anagrafica_dist
    **/ 


    public int execute ( com.ateikon.structure.Rec_matr_anagrafica_dist astr) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        return execute ( astr.tkmatrdist
                       , astr.tkmatricola
                       , astr.cdarti
                       , astr.cdmatrdist
                       , astr.nrriga
                       , astr.dimena_acq
                       , astr.dimenb_acq
                       , astr.dimenc_acq
                       , astr.ncolli_acq
                       , astr.qtatot_acq
                       , astr.dimena
                       , astr.dimenb
                       , astr.dimenc
                       , astr.ncolli
                       , astr.qtatot
                       , astr.qtacons
                       , astr.ncollicons
                       , astr.fgsaldo
                       , astr.note
                        );


    }




    /****
        execute: matr_anagrafica_dist
    **/ 


    public int execute ( long       tkmatrdist
                       , long       tkmatricola
                       , String     cdarti
                       , String     cdmatrdist
                       , long       nrriga
                       , double     dimena_acq
                       , double     dimenb_acq
                       , double     dimenc_acq
                       , long       ncolli_acq
                       , double     qtatot_acq
                       , double     dimena
                       , double     dimenb
                       , double     dimenc
                       , long       ncolli
                       , double     qtatot
                       , double     qtacons
                       , long       ncollicons
                       , String     fgsaldo
                       , String     note
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " select tkmatrdist\n";
        l_query  += "   from pgmr.matr_anagrafica_dist  \n";
        l_query  += "  where tkmatrdist = ? \n";


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setLong      (ind, tkmatrdist); ind += 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            pstm.close();
            tot_rec = executeUpdate ( tkmatrdist
                                    , tkmatricola
                                    , cdarti
                                    , cdmatrdist
                                    , nrriga
                                    , dimena_acq
                                    , dimenb_acq
                                    , dimenc_acq
                                    , ncolli_acq
                                    , qtatot_acq
                                    , dimena
                                    , dimenb
                                    , dimenc
                                    , ncolli
                                    , qtatot
                                    , qtacons
                                    , ncollicons
                                    , fgsaldo
                                    , note
                                    );
        }else {

            pstm.close();
            tot_rec = executeInsert ( tkmatrdist
                                    , tkmatricola
                                    , cdarti
                                    , cdmatrdist
                                    , nrriga
                                    , dimena_acq
                                    , dimenb_acq
                                    , dimenc_acq
                                    , ncolli_acq
                                    , qtatot_acq
                                    , dimena
                                    , dimenb
                                    , dimenc
                                    , ncolli
                                    , qtatot
                                    , qtacons
                                    , ncollicons
                                    , fgsaldo
                                    , note
                                    );
        }

        if (tot_rec != 1){

            m_connection.rollback();
            throw new Exception("Errore Agg. matr_anagrafica_dist! ");
        }

        return tot_rec;
    }




    /****
        executeInsert: matr_anagrafica_dist
    **/ 


    public int executeInsert( long       tkmatrdist
                            , long       tkmatricola
                            , String     cdarti
                            , String     cdmatrdist
                            , long       nrriga
                            , double     dimena_acq
                            , double     dimenb_acq
                            , double     dimenc_acq
                            , long       ncolli_acq
                            , double     qtatot_acq
                            , double     dimena
                            , double     dimenb
                            , double     dimenc
                            , long       ncolli
                            , double     qtatot
                            , double     qtacons
                            , long       ncollicons
                            , String     fgsaldo
                            , String     note
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        F_tabkey f_tabkey = new F_tabkey();
        setProfilo((Atk_sql) f_tabkey );

        long ll_tk = f_tabkey.getTabkey( "matr_anagrafica_dist" );

        if (ll_tk <= 0){
            throw new Exception ("Error Keypool (matr_anagrafica_dist): "+ll_tk );
        }

        this.tkmatrdist = ll_tk;
        tkmatrdist = this.tkmatrdist;



        l_query   = "";
        l_query  += " insert into pgmr.matr_anagrafica_dist ( \n";
        l_query  += "             tkmatrdist   \n";
        l_query  += "           , tkmatricola   \n";
        l_query  += "           , cdarti   \n";
        l_query  += "           , cdmatrdist   \n";
        l_query  += "           , nrriga   \n";
        l_query  += "           , dimena_acq   \n";
        l_query  += "           , dimenb_acq   \n";
        l_query  += "           , dimenc_acq   \n";
        l_query  += "           , ncolli_acq   \n";
        l_query  += "           , qtatot_acq   \n";
        l_query  += "           , dimena   \n";
        l_query  += "           , dimenb   \n";
        l_query  += "           , dimenc   \n";
        l_query  += "           , ncolli   \n";
        l_query  += "           , qtatot   \n";
        l_query  += "           , qtacons   \n";
        l_query  += "           , ncollicons   \n";
        l_query  += "           , fgsaldo   \n";
        l_query  += "           , note   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cdarti.equals("")) cdarti = null;
        if (cdmatrdist.equals("")) cdmatrdist = null;
        if (fgsaldo.equals("")) fgsaldo = null;
        if (note.equals("")) note = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;


        ind = 1;
        pstm.setLong      (ind, tkmatrdist); ind += 1;
        if (tkmatricola == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkmatricola); ind += 1;
        } 
        pstm.setString    (ind, cdarti); ind += 1;
        pstm.setString    (ind, cdmatrdist); ind += 1;
        if (nrriga == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, nrriga); ind += 1;
        } 
        if (dimena_acq == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, dimena_acq); ind += 1;
        } 
        if (dimenb_acq == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, dimenb_acq); ind += 1;
        } 
        if (dimenc_acq == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, dimenc_acq); ind += 1;
        } 
        if (ncolli_acq == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, ncolli_acq); ind += 1;
        } 
        if (qtatot_acq == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, qtatot_acq); ind += 1;
        } 
        if (dimena == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, dimena); ind += 1;
        } 
        if (dimenb == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, dimenb); ind += 1;
        } 
        if (dimenc == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, dimenc); ind += 1;
        } 
        if (ncolli == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, ncolli); ind += 1;
        } 
        if (qtatot == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, qtatot); ind += 1;
        } 
        if (qtacons == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, qtacons); ind += 1;
        } 
        if (ncollicons == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, ncollicons); ind += 1;
        } 
        pstm.setString    (ind, fgsaldo); ind += 1;
        pstm.setString    (ind, note); ind += 1;
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




    /****
        executeUpdate: matr_anagrafica_dist
    **/ 


    public int executeUpdate( long       tkmatrdist
                            , long       tkmatricola
                            , String     cdarti
                            , String     cdmatrdist
                            , long       nrriga
                            , double     dimena_acq
                            , double     dimenb_acq
                            , double     dimenc_acq
                            , long       ncolli_acq
                            , double     qtatot_acq
                            , double     dimena
                            , double     dimenb
                            , double     dimenc
                            , long       ncolli
                            , double     qtatot
                            , double     qtacons
                            , long       ncollicons
                            , String     fgsaldo
                            , String     note
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.matr_anagrafica_dist  \n";
        l_query  += "         set tkmatricola = ?  \n";
        l_query  += "           , cdarti = ?  \n";
        l_query  += "           , cdmatrdist = ?  \n";
        l_query  += "           , nrriga = ?  \n";
        l_query  += "           , dimena_acq = ?  \n";
        l_query  += "           , dimenb_acq = ?  \n";
        l_query  += "           , dimenc_acq = ?  \n";
        l_query  += "           , ncolli_acq = ?  \n";
        l_query  += "           , qtatot_acq = ?  \n";
        l_query  += "           , dimena = ?  \n";
        l_query  += "           , dimenb = ?  \n";
        l_query  += "           , dimenc = ?  \n";
        l_query  += "           , ncolli = ?  \n";
        l_query  += "           , qtatot = ?  \n";
        l_query  += "           , qtacons = ?  \n";
        l_query  += "           , ncollicons = ?  \n";
        l_query  += "           , fgsaldo = ?  \n";
        l_query  += "           , note = ?  \n";
        l_query  += "           , cdazie = ?  \n";
        l_query  += "           , cddipa = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtinse = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "  where tkmatrdist = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cdarti.equals("")) cdarti = null;
        if (cdmatrdist.equals("")) cdmatrdist = null;
        if (fgsaldo.equals("")) fgsaldo = null;
        if (note.equals("")) note = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;


        ind = 1;
        if (tkmatricola == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, tkmatricola); ind += 1;
        } 
        pstm.setString    (ind, cdarti); ind += 1;
        pstm.setString    (ind, cdmatrdist); ind += 1;
        if (nrriga == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, nrriga); ind += 1;
        } 
        if (dimena_acq == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, dimena_acq); ind += 1;
        } 
        if (dimenb_acq == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, dimenb_acq); ind += 1;
        } 
        if (dimenc_acq == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, dimenc_acq); ind += 1;
        } 
        if (ncolli_acq == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, ncolli_acq); ind += 1;
        } 
        if (qtatot_acq == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, qtatot_acq); ind += 1;
        } 
        if (dimena == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, dimena); ind += 1;
        } 
        if (dimenb == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, dimenb); ind += 1;
        } 
        if (dimenc == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, dimenc); ind += 1;
        } 
        if (ncolli == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, ncolli); ind += 1;
        } 
        if (qtatot == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, qtatot); ind += 1;
        } 
        if (qtacons == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setDouble    (ind, qtacons); ind += 1;
        } 
        if (ncollicons == 0){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, ncollicons); ind += 1;
        } 
        pstm.setString    (ind, fgsaldo); ind += 1;
        pstm.setString    (ind, note); ind += 1;
        pstm.setString    (ind, cdazie); ind += 1;
        pstm.setString    (ind, cddipa); ind += 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtinse); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;
        pstm.setLong      (ind, tkmatrdist); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public long       tkmatrdist = 0; 







}

