package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Ep_msgmod_file extends Atk_sql {

    public Ep_msgmod_file() {

        super();
    }




    /****
        getAll: ep_msgmod_file
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.ep_msgmod_file  \n";
        if (is_farza_filtro_dipa.equals("S")){
            l_query  += "  where cddipa = '"+cddipa+"'  \n";
        }

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: ep_msgmod_file
    **/ 

    public ResultSet getKey( long       tkmsgmod
                           , String     cdling
                           , long       nrposi
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.ep_msgmod_file  \n";
        if (tkmsgmod == 0 && null_tkmsgmod){ 
            l_query  += "  where tkmsgmod is null \n";
        }else { 
            l_query  += "  where tkmsgmod = ?  \n";
        } 
        if (cdling.equals("")){ 
            l_query  += "  and cdling is null  \n";
        }else { 
            l_query  += "  and cdling = ?  \n";
        } 
        if (nrposi == 0 && null_nrposi){ 
            l_query  += "  and nrposi is null \n";
        }else { 
            l_query  += "  and nrposi = ?  \n";
        } 

        pstm = setQuery( l_query ) ;

        ind = 1;
        if (tkmsgmod == 0 && null_tkmsgmod){ 
        }else { 
            pstm.setLong      (ind, tkmsgmod); ind += 1;
        } 
        if (cdling.equals("")){ 
        }else { 
            pstm.setString    (ind, cdling); ind += 1;
        } 
        if (nrposi == 0 && null_nrposi){ 
        }else { 
            pstm.setLong      (ind, nrposi); ind += 1;
        } 

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: ep_msgmod_file
    **/ 


    public com.ateikon.structure.Rec_ep_msgmod_file  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_ep_msgmod_file lstr = new com.ateikon.structure.Rec_ep_msgmod_file();

        if (rs == null) return lstr;
        if (rs.getObject("tkmsgmod")!=null) lstr.tkmsgmod = rs.getLong      ("tkmsgmod"); 
        if (rs.getObject("cdling")!=null) lstr.cdling = rs.getString    ("cdling"); 
        if (rs.getObject("nrposi")!=null) lstr.nrposi = rs.getLong      ("nrposi"); 
        if (rs.getObject("rootpath")!=null) lstr.rootpath = rs.getString    ("rootpath"); 
        if (rs.getObject("relativepath")!=null) lstr.relativepath = rs.getString    ("relativepath"); 
        if (rs.getObject("filename")!=null) lstr.filename = rs.getString    ("filename"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 

        return lstr;
    }




    /****
        preupdate: ep_msgmod_file

        sovrascrivere per impostare i controlli da effetuare prima dell'execute
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_ep_msgmod_file astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }





    /****
        preupdate: ep_msgmod_file

        sovrascrivere per impostare i controlli da effetuare per sincro

        questo metodo � stato introdotto per la sincro: in fase di export prima  
        di scrivere il file XML viene chiamato
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_ep_msgmod_file astr, ResultSet rs) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }



    /****
        execute: ep_msgmod_file
    **/ 


    public int execute ( com.ateikon.structure.Rec_ep_msgmod_file astr) throws Exception {
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
        l_query  += " select tkmsgmod\n";
        l_query  += "      , cdling\n";
        l_query  += "      , nrposi\n";
        l_query  += "   from pgmr.ep_msgmod_file  \n";
        if (astr.tkmsgmod == 0 && null_tkmsgmod){ 
            l_query  += "  where tkmsgmod is null \n";
        }else { 
            l_query  += "  where tkmsgmod = ?  \n";
        } 
        if (astr.cdling.equals("")){ 
            l_query  += "  and cdling is null  \n";
        }else { 
            l_query  += "  and cdling = ?  \n";
        } 
        if (astr.nrposi == 0 && null_nrposi){ 
            l_query  += "  and nrposi is null \n";
        }else { 
            l_query  += "  and nrposi = ?  \n";
        } 


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (astr.tkmsgmod == 0 && null_tkmsgmod){ 
        }else { 
            pstm.setLong      (ind, astr.tkmsgmod); ind += 1;
        } 
        if (astr.cdling.equals("")){ 
        }else { 
            pstm.setString    (ind, astr.cdling); ind += 1;
        } 
        if (astr.nrposi == 0 && null_nrposi){ 
        }else { 
            pstm.setLong      (ind, astr.nrposi); ind += 1;
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
            throw new Exception("Errore Agg. ep_msgmod_file! ");
        }
        */ 

        return tot_rec;
    }




    /****
        executeInsert: ep_msgmod_file
    **/ 


    public int executeInsert( com.ateikon.structure.Rec_ep_msgmod_file astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;





        l_query   = "";
        l_query  += " insert into pgmr.ep_msgmod_file ( \n";
        l_query  += "             tkmsgmod   \n";
        l_query  += "           , cdling   \n";
        l_query  += "           , nrposi   \n";
        l_query  += "           , rootpath   \n";
        l_query  += "           , relativepath   \n";
        l_query  += "           , filename   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.rootpath.equals("")) astr.rootpath = null;
        if (astr.relativepath.equals("")) astr.relativepath = null;
        if (astr.filename.equals("")) astr.filename = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;


        ind = 1;
        if (astr.tkmsgmod == 0 && null_tkmsgmod){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkmsgmod); ind += 1;
        } 
        pstm.setString    (ind, astr.cdling); ind += 1;
        if (astr.nrposi == 0 && null_nrposi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.nrposi); ind += 1;
        } 
        pstm.setString    (ind, astr.rootpath); ind += 1;
        pstm.setString    (ind, astr.relativepath); ind += 1;
        pstm.setString    (ind, astr.filename); ind += 1;
        pstm.setString    (ind, astr.cdazie); ind += 1;
        pstm.setString    (ind, astr.cddipa); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtinse); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        // --- Ripristino le Stringhe NULL

        if (astr.rootpath == null) astr.rootpath = "";
        if (astr.relativepath == null) astr.relativepath = "";
        if (astr.filename == null) astr.filename = "";
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

        long ll_tk = f_tabkey.getTabkey( "ep_msgmod_file" );

        return ll_tk;

    }




    /****
        executeUpdate: ep_msgmod_file
    **/ 


    public int executeUpdate( com.ateikon.structure.Rec_ep_msgmod_file astr ) throws Exception { 
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.ep_msgmod_file  \n";
        l_query  += "         set rootpath = ?  \n";
        l_query  += "           , relativepath = ?  \n";
        l_query  += "           , filename = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "  where tkmsgmod = ? \n";
        l_query  += "    and cdling = ? \n";
        l_query  += "    and nrposi = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.rootpath.equals("")) astr.rootpath = null;
        if (astr.relativepath.equals("")) astr.relativepath = null;
        if (astr.filename.equals("")) astr.filename = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;


        ind = 1;
        pstm.setString    (ind, astr.rootpath); ind += 1;
        pstm.setString    (ind, astr.relativepath); ind += 1;
        pstm.setString    (ind, astr.filename); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;

        if (astr.tkmsgmod == 0 && null_tkmsgmod){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkmsgmod); ind += 1;
        } 
        if (astr.cdling.equals("")){ 
            pstm.setString    (ind, null); ind += 1;
        }else { 
            pstm.setString    (ind, astr.cdling); ind += 1;
        } 
        if (astr.nrposi == 0 && null_nrposi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.nrposi); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.tkmsgmod = astr.tkmsgmod; 
        this.cdling = astr.cdling; 
        this.nrposi = astr.nrposi; 

        // --- Ripristino le Stringhe NULL

        if (astr.rootpath == null) astr.rootpath = "";
        if (astr.relativepath == null) astr.relativepath = "";
        if (astr.filename == null) astr.filename = "";
        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil == null) astr.profil = "";


        return tot_rec;

    }



    /****
        getKey: ep_msgmod_file
    **/ 

    public int deleteKey( long       tkmsgmod
                           , String     cdling
                           , long       nrposi
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " delete \n";
        l_query  += "   from pgmr.ep_msgmod_file  \n";
        if (tkmsgmod == 0 && null_tkmsgmod){ 
            l_query  += "  where tkmsgmod is null \n";
        }else { 
            l_query  += "  where tkmsgmod = ?  \n";
        } 
        if (cdling.equals("")){ 
            l_query  += "  and cdling is null  \n";
        }else { 
            l_query  += "  and cdling = ?  \n";
        } 
        if (nrposi == 0 && null_nrposi){ 
            l_query  += "  and nrposi is null \n";
        }else { 
            l_query  += "  and nrposi = ?  \n";
        } 

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (tkmsgmod == 0 && null_tkmsgmod){ 
        }else { 
            pstm.setLong      (ind, tkmsgmod); ind += 1;
        } 
        if (cdling.equals("")){ 
        }else { 
            pstm.setString    (ind, cdling); ind += 1;
        } 
        if (nrposi == 0 && null_nrposi){ 
        }else { 
            pstm.setLong      (ind, nrposi); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();
        pstm.close();

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public long       tkmsgmod = 0; 
    public String     cdling = ""; 
    public long       nrposi = 0; 






    // --- Impostando a false queste prop. il def. � 0

    public boolean ib_calcola_token = true;
    public boolean ib_ctrl_mindate = false;
    public boolean null_tkmsgmod = true;
    public boolean null_nrposi = true;




    public String is_farza_filtro_dipa = "N";



}

