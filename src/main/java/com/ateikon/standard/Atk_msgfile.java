package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Atk_msgfile extends Atk_sql {

    public Atk_msgfile() {

        super();
    }




    /****
        getAll: atk_msgfile
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.atk_msgfile  \n";

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: atk_msgfile
    **/ 

    public ResultSet getKey( long       tkmsg
                           , String     pathfile
                           , String     nomefile
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.atk_msgfile  \n";
        if (tkmsg == 0 && null_tkmsg){ 
            l_query  += "  where tkmsg is null \n";
        }else { 
            l_query  += "  where tkmsg = ?  \n";
        } 
        if (pathfile.equals("")){ 
            l_query  += "  and pathfile is null  \n";
        }else { 
            l_query  += "  and pathfile = ?  \n";
        } 
        if (nomefile.equals("")){ 
            l_query  += "  and nomefile is null  \n";
        }else { 
            l_query  += "  and nomefile = ?  \n";
        } 

        pstm = setQuery( l_query ) ;

        ind = 1;
        if (tkmsg == 0 && null_tkmsg){ 
        }else { 
            pstm.setLong      (ind, tkmsg); ind += 1;
        } 
        if (pathfile.equals("")){ 
        }else { 
            pstm.setString    (ind, pathfile); ind += 1;
        } 
        if (nomefile.equals("")){ 
        }else { 
            pstm.setString    (ind, nomefile); ind += 1;
        } 

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: atk_msgfile
    **/ 


    public com.ateikon.structure.Rec_atk_msgfile  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_atk_msgfile lstr = new com.ateikon.structure.Rec_atk_msgfile();

        if (rs == null) return lstr;
        if (rs.getObject("tkmsg")!=null) lstr.tkmsg = rs.getLong      ("tkmsg"); 
        if (rs.getObject("pathfile")!=null) lstr.pathfile = rs.getString    ("pathfile"); 
        if (rs.getObject("nomefile")!=null) lstr.nomefile = rs.getString    ("nomefile"); 
        if (rs.getObject("id_object")!=null) lstr.id_object = rs.getLong      ("id_object"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("posi")!=null) lstr.posi = rs.getLong      ("posi"); 
        if (rs.getObject("fgconv_pdf")!=null) lstr.fgconv_pdf = rs.getString    ("fgconv_pdf"); 
        if (rs.getObject("rootpath")!=null) lstr.rootpath = rs.getString    ("rootpath"); 
        if (rs.getObject("filename")!=null) lstr.filename = rs.getString    ("filename"); 
        if (rs.getObject("pathconv_pdf")!=null) lstr.pathconv_pdf = rs.getString    ("pathconv_pdf"); 
        if (rs.getObject("relativepath")!=null) lstr.relativepath = rs.getString    ("relativepath"); 

        return lstr;
    }




    /****
        preupdate: atk_msgfile

        sovrascrivere per impostare i controlli da effetuare prima dell'execute
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_atk_msgfile astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }





    /****
        preupdate: atk_msgfile

        sovrascrivere per impostare i controlli da effetuare per sincro

        questo metodo � stato introdotto per la sincro: in fase di export prima  
        di scrivere il file XML viene chiamato
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_atk_msgfile astr, ResultSet rs) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }



    /****
        execute: atk_msgfile
    **/ 


    public int execute ( com.ateikon.structure.Rec_atk_msgfile astr) throws Exception {
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
        l_query  += "      , pathfile\n";
        l_query  += "      , nomefile\n";
        l_query  += "   from pgmr.atk_msgfile  \n";
        if (astr.tkmsg == 0 && null_tkmsg){ 
            l_query  += "  where tkmsg is null \n";
        }else { 
            l_query  += "  where tkmsg = ?  \n";
        } 
        if (astr.pathfile.equals("")){ 
            l_query  += "  and pathfile is null  \n";
        }else { 
            l_query  += "  and pathfile = ?  \n";
        } 
        if (astr.nomefile.equals("")){ 
            l_query  += "  and nomefile is null  \n";
        }else { 
            l_query  += "  and nomefile = ?  \n";
        } 


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (astr.tkmsg == 0 && null_tkmsg){ 
        }else { 
            pstm.setLong      (ind, astr.tkmsg); ind += 1;
        } 
        if (astr.pathfile.equals("")){ 
        }else { 
            pstm.setString    (ind, astr.pathfile); ind += 1;
        } 
        if (astr.nomefile.equals("")){ 
        }else { 
            pstm.setString    (ind, astr.nomefile); ind += 1;
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
            throw new Exception("Errore Agg. atk_msgfile! ");
        }
        */ 

        return tot_rec;
    }




    /****
        executeInsert: atk_msgfile
    **/ 


    public int executeInsert( com.ateikon.structure.Rec_atk_msgfile astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;





        l_query   = "";
        l_query  += " insert into pgmr.atk_msgfile ( \n";
        l_query  += "             tkmsg   \n";
        l_query  += "           , pathfile   \n";
        l_query  += "           , nomefile   \n";
        l_query  += "           , id_object   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "           , posi   \n";
        l_query  += "           , fgconv_pdf   \n";
        l_query  += "           , rootpath   \n";
        l_query  += "           , filename   \n";
        l_query  += "           , pathconv_pdf   \n";
        l_query  += "           , relativepath   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;
        if (astr.fgconv_pdf.equals("")) astr.fgconv_pdf = null;
        if (astr.rootpath.equals("")) astr.rootpath = null;
        if (astr.filename.equals("")) astr.filename = null;
        if (astr.pathconv_pdf.equals("")) astr.pathconv_pdf = null;
        if (astr.relativepath.equals("")) astr.relativepath = null;


        ind = 1;
        if (astr.tkmsg == 0 && null_tkmsg){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkmsg); ind += 1;
        } 
        pstm.setString    (ind, astr.pathfile); ind += 1;
        pstm.setString    (ind, astr.nomefile); ind += 1;
        if (astr.id_object == 0 && null_id_object){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.id_object); ind += 1;
        } 
        pstm.setString    (ind, astr.cdazie); ind += 1;
        pstm.setString    (ind, astr.cddipa); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtinse); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;
        if (astr.posi == 0 && null_posi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.posi); ind += 1;
        } 
        pstm.setString    (ind, astr.fgconv_pdf); ind += 1;
        pstm.setString    (ind, astr.rootpath); ind += 1;
        pstm.setString    (ind, astr.filename); ind += 1;
        pstm.setString    (ind, astr.pathconv_pdf); ind += 1;
        pstm.setString    (ind, astr.relativepath); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        // --- Ripristino le Stringhe NULL

        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil == null) astr.profil = "";
        if (astr.fgconv_pdf == null) astr.fgconv_pdf = "";
        if (astr.rootpath == null) astr.rootpath = "";
        if (astr.filename == null) astr.filename = "";
        if (astr.pathconv_pdf == null) astr.pathconv_pdf = "";
        if (astr.relativepath == null) astr.relativepath = "";


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

        long ll_tk = f_tabkey.getTabkey( "atk_msgfile" );

        return ll_tk;

    }




    /****
        executeUpdate: atk_msgfile
    **/ 


    public int executeUpdate( com.ateikon.structure.Rec_atk_msgfile astr ) throws Exception { 
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.atk_msgfile  \n";
        l_query  += "         set id_object = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "           , posi = ?  \n";
        l_query  += "           , fgconv_pdf = ?  \n";
        l_query  += "           , rootpath = ?  \n";
        l_query  += "           , filename = ?  \n";
        l_query  += "           , pathconv_pdf = ?  \n";
        l_query  += "           , relativepath = ?  \n";
        l_query  += "  where tkmsg = ? \n";
        l_query  += "    and pathfile = ? \n";
        l_query  += "    and nomefile = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;
        if (astr.fgconv_pdf.equals("")) astr.fgconv_pdf = null;
        if (astr.rootpath.equals("")) astr.rootpath = null;
        if (astr.filename.equals("")) astr.filename = null;
        if (astr.pathconv_pdf.equals("")) astr.pathconv_pdf = null;
        if (astr.relativepath.equals("")) astr.relativepath = null;


        ind = 1;
        if (astr.id_object == 0 && null_id_object){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.id_object); ind += 1;
        } 
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;
        if (astr.posi == 0 && null_posi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.posi); ind += 1;
        } 
        pstm.setString    (ind, astr.fgconv_pdf); ind += 1;
        pstm.setString    (ind, astr.rootpath); ind += 1;
        pstm.setString    (ind, astr.filename); ind += 1;
        pstm.setString    (ind, astr.pathconv_pdf); ind += 1;
        pstm.setString    (ind, astr.relativepath); ind += 1;

        if (astr.tkmsg == 0 && null_tkmsg){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkmsg); ind += 1;
        } 
        if (astr.pathfile.equals("")){ 
            pstm.setString    (ind, null); ind += 1;
        }else { 
            pstm.setString    (ind, astr.pathfile); ind += 1;
        } 
        if (astr.nomefile.equals("")){ 
            pstm.setString    (ind, null); ind += 1;
        }else { 
            pstm.setString    (ind, astr.nomefile); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.tkmsg = astr.tkmsg; 
        this.pathfile = astr.pathfile; 
        this.nomefile = astr.nomefile; 

        // --- Ripristino le Stringhe NULL

        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil == null) astr.profil = "";
        if (astr.fgconv_pdf == null) astr.fgconv_pdf = "";
        if (astr.rootpath == null) astr.rootpath = "";
        if (astr.filename == null) astr.filename = "";
        if (astr.pathconv_pdf == null) astr.pathconv_pdf = "";
        if (astr.relativepath == null) astr.relativepath = "";


        return tot_rec;

    }



    /****
        getKey: atk_msgfile
    **/ 

    public int deleteKey( long       tkmsg
                           , String     pathfile
                           , String     nomefile
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " delete \n";
        l_query  += "   from pgmr.atk_msgfile  \n";
        if (tkmsg == 0 && null_tkmsg){ 
            l_query  += "  where tkmsg is null \n";
        }else { 
            l_query  += "  where tkmsg = ?  \n";
        } 
        if (pathfile.equals("")){ 
            l_query  += "  and pathfile is null  \n";
        }else { 
            l_query  += "  and pathfile = ?  \n";
        } 
        if (nomefile.equals("")){ 
            l_query  += "  and nomefile is null  \n";
        }else { 
            l_query  += "  and nomefile = ?  \n";
        } 

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (tkmsg == 0 && null_tkmsg){ 
        }else { 
            pstm.setLong      (ind, tkmsg); ind += 1;
        } 
        if (pathfile.equals("")){ 
        }else { 
            pstm.setString    (ind, pathfile); ind += 1;
        } 
        if (nomefile.equals("")){ 
        }else { 
            pstm.setString    (ind, nomefile); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();
        pstm.close();

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public long       tkmsg = 0; 
    public String     pathfile = ""; 
    public String     nomefile = ""; 






    // --- Impostando a false queste prop. il def. � 0

    public boolean ib_calcola_token = true;
    public boolean ib_ctrl_mindate = false;
    public boolean null_tkmsg = true;
    public boolean null_id_object = true;
    public boolean null_posi = true;





}

