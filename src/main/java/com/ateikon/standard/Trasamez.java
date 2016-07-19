package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Trasamez extends Atk_sql {

    public Trasamez() {

        super();
    }




    /****
        getAll: trasamez
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.trasamez  \n";

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: trasamez
    **/ 

    public ResultSet getKey( String     cdtrme
                           , String     cdazie
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.trasamez  \n";
        l_query  += "  where cdtrme = ? \n";
        l_query  += "    and cdazie = ? \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString    (ind, cdtrme); ind += 1;
        pstm.setString    (ind, cdazie); ind += 1;

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: trasamez
    **/ 


    public com.ateikon.structure.Rec_trasamez  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_trasamez lstr = new com.ateikon.structure.Rec_trasamez();

        if (rs == null) return lstr;
        if (rs.getObject("cdtrme")!=null) lstr.cdtrme = rs.getString    ("cdtrme"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cdtrme_m")!=null) lstr.cdtrme_m = rs.getString    ("cdtrme_m"); 
        if (rs.getObject("dstrme")!=null) lstr.dstrme = rs.getString    ("dstrme"); 
        if (rs.getObject("fmitte")!=null) lstr.fmitte = rs.getString    ("fmitte"); 
        if (rs.getObject("fdesti")!=null) lstr.fdesti = rs.getString    ("fdesti"); 
        if (rs.getObject("fvetto")!=null) lstr.fvetto = rs.getString    ("fvetto"); 
        if (rs.getObject("cdmtin")!=null) lstr.cdmtin = rs.getString    ("cdmtin"); 
        if (rs.getObject("dsingl")!=null) lstr.dsingl = rs.getString    ("dsingl"); 
        if (rs.getObject("dstede")!=null) lstr.dstede = rs.getString    ("dstede"); 
        if (rs.getObject("dsfran")!=null) lstr.dsfran = rs.getString    ("dsfran"); 
        if (rs.getObject("dsspag")!=null) lstr.dsspag = rs.getString    ("dsspag"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("ftipve")!=null) lstr.ftipve = rs.getString    ("ftipve"); 

        return lstr;
    }




    /****
        execute: trasamez
    **/ 


    public int execute ( com.ateikon.structure.Rec_trasamez astr) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec =  execute ( astr.cdtrme
                          , astr.cdtrme_m
                          , astr.dstrme
                          , astr.fmitte
                          , astr.fdesti
                          , astr.fvetto
                          , astr.cdmtin
                          , astr.dsingl
                          , astr.dstede
                          , astr.dsfran
                          , astr.dsspag
                          , astr.ftipve
                           );

        return tot_rec;
    }




    /****
        execute: trasamez
    **/ 


    public int execute ( String     cdtrme
                       , String     cdtrme_m
                       , String     dstrme
                       , String     fmitte
                       , String     fdesti
                       , String     fvetto
                       , String     cdmtin
                       , String     dsingl
                       , String     dstede
                       , String     dsfran
                       , String     dsspag
                       , String     ftipve
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " select cdtrme\n";
        l_query  += "      , cdazie\n";
        l_query  += "   from pgmr.trasamez  \n";
        l_query  += "  where cdtrme = ? \n";
        l_query  += "    and cdazie = ? \n";


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setString    (ind, cdtrme); ind += 1;
        pstm.setString    (ind, cdazie); ind += 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            pstm.close();
            tot_rec = executeUpdate ( cdtrme
                                    , cdtrme_m
                                    , dstrme
                                    , fmitte
                                    , fdesti
                                    , fvetto
                                    , cdmtin
                                    , dsingl
                                    , dstede
                                    , dsfran
                                    , dsspag
                                    , ftipve
                                    );
        }else {

            pstm.close();
            tot_rec = executeInsert ( cdtrme
                                    , cdtrme_m
                                    , dstrme
                                    , fmitte
                                    , fdesti
                                    , fvetto
                                    , cdmtin
                                    , dsingl
                                    , dstede
                                    , dsfran
                                    , dsspag
                                    , ftipve
                                    );
        }

        if (tot_rec != 1){

            m_connection.rollback();
            throw new Exception("Errore Agg. trasamez! ");
        }

        return tot_rec;
    }




    /****
        executeInsert: trasamez
    **/ 


    public int executeInsert( String     cdtrme
                            , String     cdtrme_m
                            , String     dstrme
                            , String     fmitte
                            , String     fdesti
                            , String     fvetto
                            , String     cdmtin
                            , String     dsingl
                            , String     dstede
                            , String     dsfran
                            , String     dsspag
                            , String     ftipve
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;





        l_query   = "";
        l_query  += " insert into pgmr.trasamez ( \n";
        l_query  += "             cdtrme   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cdtrme_m   \n";
        l_query  += "           , dstrme   \n";
        l_query  += "           , fmitte   \n";
        l_query  += "           , fdesti   \n";
        l_query  += "           , fvetto   \n";
        l_query  += "           , cdmtin   \n";
        l_query  += "           , dsingl   \n";
        l_query  += "           , dstede   \n";
        l_query  += "           , dsfran   \n";
        l_query  += "           , dsspag   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "           , ftipve   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cdtrme_m.equals("")) cdtrme_m = null;
        if (dstrme.equals("")) dstrme = null;
        if (fmitte.equals("")) fmitte = null;
        if (fdesti.equals("")) fdesti = null;
        if (fvetto.equals("")) fvetto = null;
        if (cdmtin.equals("")) cdmtin = null;
        if (dsingl.equals("")) dsingl = null;
        if (dstede.equals("")) dstede = null;
        if (dsfran.equals("")) dsfran = null;
        if (dsspag.equals("")) dsspag = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;
        if (ftipve.equals("")) ftipve = null;


        ind = 1;
        pstm.setString    (ind, cdtrme); ind += 1;
        pstm.setString    (ind, cdazie); ind += 1;
        pstm.setString    (ind, cdtrme_m); ind += 1;
        pstm.setString    (ind, dstrme); ind += 1;
        pstm.setString    (ind, fmitte); ind += 1;
        pstm.setString    (ind, fdesti); ind += 1;
        pstm.setString    (ind, fvetto); ind += 1;
        pstm.setString    (ind, cdmtin); ind += 1;
        pstm.setString    (ind, dsingl); ind += 1;
        pstm.setString    (ind, dstede); ind += 1;
        pstm.setString    (ind, dsfran); ind += 1;
        pstm.setString    (ind, dsspag); ind += 1;
        pstm.setString    (ind, cddipa); ind += 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtinse); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;
        pstm.setString    (ind, ftipve); ind += 1;

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

        long ll_tk = f_tabkey.getTabkey( "trasamez" );

        return ll_tk;

    }




    /****
        executeUpdate: trasamez
    **/ 


    public int executeUpdate( String     cdtrme
                            , String     cdtrme_m
                            , String     dstrme
                            , String     fmitte
                            , String     fdesti
                            , String     fvetto
                            , String     cdmtin
                            , String     dsingl
                            , String     dstede
                            , String     dsfran
                            , String     dsspag
                            , String     ftipve
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.trasamez  \n";
        l_query  += "         set cdtrme_m = ?  \n";
        l_query  += "           , dstrme = ?  \n";
        l_query  += "           , fmitte = ?  \n";
        l_query  += "           , fdesti = ?  \n";
        l_query  += "           , fvetto = ?  \n";
        l_query  += "           , cdmtin = ?  \n";
        l_query  += "           , dsingl = ?  \n";
        l_query  += "           , dstede = ?  \n";
        l_query  += "           , dsfran = ?  \n";
        l_query  += "           , dsspag = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "           , ftipve = ?  \n";
        l_query  += "  where cdtrme = ? \n";
        l_query  += "    and cdazie = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cdtrme_m.equals("")) cdtrme_m = null;
        if (dstrme.equals("")) dstrme = null;
        if (fmitte.equals("")) fmitte = null;
        if (fdesti.equals("")) fdesti = null;
        if (fvetto.equals("")) fvetto = null;
        if (cdmtin.equals("")) cdmtin = null;
        if (dsingl.equals("")) dsingl = null;
        if (dstede.equals("")) dstede = null;
        if (dsfran.equals("")) dsfran = null;
        if (dsspag.equals("")) dsspag = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;
        if (ftipve.equals("")) ftipve = null;


        ind = 1;
        pstm.setString    (ind, cdtrme_m); ind += 1;
        pstm.setString    (ind, dstrme); ind += 1;
        pstm.setString    (ind, fmitte); ind += 1;
        pstm.setString    (ind, fdesti); ind += 1;
        pstm.setString    (ind, fvetto); ind += 1;
        pstm.setString    (ind, cdmtin); ind += 1;
        pstm.setString    (ind, dsingl); ind += 1;
        pstm.setString    (ind, dstede); ind += 1;
        pstm.setString    (ind, dsfran); ind += 1;
        pstm.setString    (ind, dsspag); ind += 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;
        pstm.setString    (ind, ftipve); ind += 1;

        pstm.setString    (ind, cdtrme); ind += 1;
        pstm.setString    (ind, cdazie); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.cdtrme = cdtrme; 
        this.cdazie = cdazie; 

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public String     cdtrme = ""; 






    // --- Impostando a false queste prop. il def. è 0

    public boolean ib_calcola_token = true;







}

