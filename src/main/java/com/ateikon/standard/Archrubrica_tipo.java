package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Archrubrica_tipo extends Atk_sql {

    public Archrubrica_tipo() {

        super();
    }




    /****
        getAll: archrubrica_tipo
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.archrubrica_tipo  \n";

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: archrubrica_tipo
    **/ 

    public ResultSet getKey( long       tkrubr_tipo
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.archrubrica_tipo  \n";
        if (tkrubr_tipo == 0 && null_tkrubr_tipo){ 
            l_query  += "  where tkrubr_tipo is null \n";
        }else { 
            l_query  += "  where tkrubr_tipo = ?  \n";
        } 

        pstm = setQuery( l_query ) ;

        ind = 1;
        if (tkrubr_tipo == 0 && null_tkrubr_tipo){ 
        }else { 
            pstm.setLong      (ind, tkrubr_tipo); ind += 1;
        } 

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: archrubrica_tipo
    **/ 


    public com.ateikon.structure.Rec_archrubrica_tipo  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_archrubrica_tipo lstr = new com.ateikon.structure.Rec_archrubrica_tipo();

        if (rs == null) return lstr;
        if (rs.getObject("tkrubr_tipo")!=null) lstr.tkrubr_tipo = rs.getLong      ("tkrubr_tipo"); 
        if (rs.getObject("cdrubr_tipo_m")!=null) lstr.cdrubr_tipo_m = rs.getString    ("cdrubr_tipo_m"); 
        if (rs.getObject("dsrubr_tipo")!=null) lstr.dsrubr_tipo = rs.getString    ("dsrubr_tipo"); 
        if (rs.getObject("fgabil")!=null) lstr.fgabil = rs.getString    ("fgabil"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("nrposi")!=null) lstr.nrposi = rs.getLong      ("nrposi"); 
        if (rs.getObject("fgdef")!=null) lstr.fgdef = rs.getString    ("fgdef"); 
        if (rs.getObject("fggest")!=null) lstr.fggest = rs.getString    ("fggest"); 
        if (rs.getObject("tkrif_name")!=null) lstr.tkrif_name = rs.getString    ("tkrif_name"); 
        if (rs.getObject("fgofferta")!=null) lstr.fgofferta = rs.getString    ("fgofferta"); 
        if (rs.getObject("fgtrattativa")!=null) lstr.fgtrattativa = rs.getString    ("fgtrattativa"); 
        if (rs.getObject("dsrubr_tipo_en")!=null) lstr.dsrubr_tipo_en = rs.getString    ("dsrubr_tipo_en"); 
        if (rs.getObject("dsrubr_tipo_de")!=null) lstr.dsrubr_tipo_de = rs.getString    ("dsrubr_tipo_de"); 
        if (rs.getObject("dsrubr_tipo_fr")!=null) lstr.dsrubr_tipo_fr = rs.getString    ("dsrubr_tipo_fr"); 
        if (rs.getObject("dsrubr_tipo_es")!=null) lstr.dsrubr_tipo_es = rs.getString    ("dsrubr_tipo_es"); 

        return lstr;
    }




    /****
        preupdate: archrubrica_tipo

        sovrascrivere per impostare i controlli da effetuare prima dell'execute
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_archrubrica_tipo astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }





    /****
        preupdate: archrubrica_tipo

        sovrascrivere per impostare i controlli da effetuare per sincro

        questo metodo � stato introdotto per la sincro: in fase di export prima  
        di scrivere il file XML viene chiamato
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_archrubrica_tipo astr, ResultSet rs) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }



    /****
        execute: archrubrica_tipo
    **/ 


    public int execute ( com.ateikon.structure.Rec_archrubrica_tipo astr) throws Exception {
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
        l_query  += " select tkrubr_tipo\n";
        l_query  += "   from pgmr.archrubrica_tipo  \n";
        if (astr.tkrubr_tipo == 0 && null_tkrubr_tipo){ 
            l_query  += "  where tkrubr_tipo is null \n";
        }else { 
            l_query  += "  where tkrubr_tipo = ?  \n";
        } 


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (astr.tkrubr_tipo == 0 && null_tkrubr_tipo){ 
        }else { 
            pstm.setLong      (ind, astr.tkrubr_tipo); ind += 1;
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
            throw new Exception("Errore Agg. archrubrica_tipo! ");
        }
        */ 

        return tot_rec;
    }




    /****
        executeInsert: archrubrica_tipo
    **/ 


    public int executeInsert( com.ateikon.structure.Rec_archrubrica_tipo astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        if (ib_calcola_token){
            long ll_tk = getNew_token( );

            if (ll_tk <= 0){
                throw new Exception ("Error Keypool (archrubrica_tipo): "+ll_tk );
            }

            this.tkrubr_tipo = ll_tk;
            astr.tkrubr_tipo = this.tkrubr_tipo;
        }else {
            this.tkrubr_tipo = astr.tkrubr_tipo;
        }



        l_query   = "";
        l_query  += " insert into pgmr.archrubrica_tipo ( \n";
        l_query  += "             tkrubr_tipo   \n";
        l_query  += "           , cdrubr_tipo_m   \n";
        l_query  += "           , dsrubr_tipo   \n";
        l_query  += "           , fgabil   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "           , nrposi   \n";
        l_query  += "           , fgdef   \n";
        l_query  += "           , fggest   \n";
        l_query  += "           , tkrif_name   \n";
        l_query  += "           , fgofferta   \n";
        l_query  += "           , fgtrattativa   \n";
        l_query  += "           , dsrubr_tipo_en   \n";
        l_query  += "           , dsrubr_tipo_de   \n";
        l_query  += "           , dsrubr_tipo_fr   \n";
        l_query  += "           , dsrubr_tipo_es   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.cdrubr_tipo_m.equals("")) astr.cdrubr_tipo_m = null;
        if (astr.dsrubr_tipo.equals("")) astr.dsrubr_tipo = null;
        if (astr.fgabil.equals("")) astr.fgabil = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;
        if (astr.fgdef.equals("")) astr.fgdef = null;
        if (astr.fggest.equals("")) astr.fggest = null;
        if (astr.tkrif_name.equals("")) astr.tkrif_name = null;
        if (astr.fgofferta.equals("")) astr.fgofferta = null;
        if (astr.fgtrattativa.equals("")) astr.fgtrattativa = null;
        if (astr.dsrubr_tipo_en.equals("")) astr.dsrubr_tipo_en = null;
        if (astr.dsrubr_tipo_de.equals("")) astr.dsrubr_tipo_de = null;
        if (astr.dsrubr_tipo_fr.equals("")) astr.dsrubr_tipo_fr = null;
        if (astr.dsrubr_tipo_es.equals("")) astr.dsrubr_tipo_es = null;


        ind = 1;
        if (astr.tkrubr_tipo == 0 && null_tkrubr_tipo){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkrubr_tipo); ind += 1;
        } 
        pstm.setString    (ind, astr.cdrubr_tipo_m); ind += 1;
        pstm.setString    (ind, astr.dsrubr_tipo); ind += 1;
        pstm.setString    (ind, astr.fgabil); ind += 1;
        pstm.setString    (ind, astr.cdazie); ind += 1;
        pstm.setString    (ind, astr.cddipa); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtinse); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;
        if (astr.nrposi == 0 && null_nrposi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.nrposi); ind += 1;
        } 
        pstm.setString    (ind, astr.fgdef); ind += 1;
        pstm.setString    (ind, astr.fggest); ind += 1;
        pstm.setString    (ind, astr.tkrif_name); ind += 1;
        pstm.setString    (ind, astr.fgofferta); ind += 1;
        pstm.setString    (ind, astr.fgtrattativa); ind += 1;
        pstm.setString    (ind, astr.dsrubr_tipo_en); ind += 1;
        pstm.setString    (ind, astr.dsrubr_tipo_de); ind += 1;
        pstm.setString    (ind, astr.dsrubr_tipo_fr); ind += 1;
        pstm.setString    (ind, astr.dsrubr_tipo_es); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        // --- Ripristino le Stringhe NULL

        if (astr.cdrubr_tipo_m == null) astr.cdrubr_tipo_m = "";
        if (astr.dsrubr_tipo == null) astr.dsrubr_tipo = "";
        if (astr.fgabil == null) astr.fgabil = "";
        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil == null) astr.profil = "";
        if (astr.fgdef == null) astr.fgdef = "";
        if (astr.fggest == null) astr.fggest = "";
        if (astr.tkrif_name == null) astr.tkrif_name = "";
        if (astr.fgofferta == null) astr.fgofferta = "";
        if (astr.fgtrattativa == null) astr.fgtrattativa = "";
        if (astr.dsrubr_tipo_en == null) astr.dsrubr_tipo_en = "";
        if (astr.dsrubr_tipo_de == null) astr.dsrubr_tipo_de = "";
        if (astr.dsrubr_tipo_fr == null) astr.dsrubr_tipo_fr = "";
        if (astr.dsrubr_tipo_es == null) astr.dsrubr_tipo_es = "";


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

        long ll_tk = f_tabkey.getTabkey( "archrubrica_tipo" );

        return ll_tk;

    }




    /****
        executeUpdate: archrubrica_tipo
    **/ 


    public int executeUpdate( com.ateikon.structure.Rec_archrubrica_tipo astr ) throws Exception { 
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.archrubrica_tipo  \n";
        l_query  += "         set cdrubr_tipo_m = ?  \n";
        l_query  += "           , dsrubr_tipo = ?  \n";
        l_query  += "           , fgabil = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "           , nrposi = ?  \n";
        l_query  += "           , fgdef = ?  \n";
        l_query  += "           , fggest = ?  \n";
        l_query  += "           , tkrif_name = ?  \n";
        l_query  += "           , fgofferta = ?  \n";
        l_query  += "           , fgtrattativa = ?  \n";
        l_query  += "           , dsrubr_tipo_en = ?  \n";
        l_query  += "           , dsrubr_tipo_de = ?  \n";
        l_query  += "           , dsrubr_tipo_fr = ?  \n";
        l_query  += "           , dsrubr_tipo_es = ?  \n";
        l_query  += "  where tkrubr_tipo = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.cdrubr_tipo_m.equals("")) astr.cdrubr_tipo_m = null;
        if (astr.dsrubr_tipo.equals("")) astr.dsrubr_tipo = null;
        if (astr.fgabil.equals("")) astr.fgabil = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;
        if (astr.fgdef.equals("")) astr.fgdef = null;
        if (astr.fggest.equals("")) astr.fggest = null;
        if (astr.tkrif_name.equals("")) astr.tkrif_name = null;
        if (astr.fgofferta.equals("")) astr.fgofferta = null;
        if (astr.fgtrattativa.equals("")) astr.fgtrattativa = null;
        if (astr.dsrubr_tipo_en.equals("")) astr.dsrubr_tipo_en = null;
        if (astr.dsrubr_tipo_de.equals("")) astr.dsrubr_tipo_de = null;
        if (astr.dsrubr_tipo_fr.equals("")) astr.dsrubr_tipo_fr = null;
        if (astr.dsrubr_tipo_es.equals("")) astr.dsrubr_tipo_es = null;


        ind = 1;
        pstm.setString    (ind, astr.cdrubr_tipo_m); ind += 1;
        pstm.setString    (ind, astr.dsrubr_tipo); ind += 1;
        pstm.setString    (ind, astr.fgabil); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;
        if (astr.nrposi == 0 && null_nrposi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.nrposi); ind += 1;
        } 
        pstm.setString    (ind, astr.fgdef); ind += 1;
        pstm.setString    (ind, astr.fggest); ind += 1;
        pstm.setString    (ind, astr.tkrif_name); ind += 1;
        pstm.setString    (ind, astr.fgofferta); ind += 1;
        pstm.setString    (ind, astr.fgtrattativa); ind += 1;
        pstm.setString    (ind, astr.dsrubr_tipo_en); ind += 1;
        pstm.setString    (ind, astr.dsrubr_tipo_de); ind += 1;
        pstm.setString    (ind, astr.dsrubr_tipo_fr); ind += 1;
        pstm.setString    (ind, astr.dsrubr_tipo_es); ind += 1;

        if (astr.tkrubr_tipo == 0 && null_tkrubr_tipo){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkrubr_tipo); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.tkrubr_tipo = astr.tkrubr_tipo; 

        // --- Ripristino le Stringhe NULL

        if (astr.cdrubr_tipo_m == null) astr.cdrubr_tipo_m = "";
        if (astr.dsrubr_tipo == null) astr.dsrubr_tipo = "";
        if (astr.fgabil == null) astr.fgabil = "";
        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil == null) astr.profil = "";
        if (astr.fgdef == null) astr.fgdef = "";
        if (astr.fggest == null) astr.fggest = "";
        if (astr.tkrif_name == null) astr.tkrif_name = "";
        if (astr.fgofferta == null) astr.fgofferta = "";
        if (astr.fgtrattativa == null) astr.fgtrattativa = "";
        if (astr.dsrubr_tipo_en == null) astr.dsrubr_tipo_en = "";
        if (astr.dsrubr_tipo_de == null) astr.dsrubr_tipo_de = "";
        if (astr.dsrubr_tipo_fr == null) astr.dsrubr_tipo_fr = "";
        if (astr.dsrubr_tipo_es == null) astr.dsrubr_tipo_es = "";


        return tot_rec;

    }



    /****
        getKey: archrubrica_tipo
    **/ 

    public int deleteKey( long       tkrubr_tipo
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " delete \n";
        l_query  += "   from pgmr.archrubrica_tipo  \n";
        if (tkrubr_tipo == 0 && null_tkrubr_tipo){ 
            l_query  += "  where tkrubr_tipo is null \n";
        }else { 
            l_query  += "  where tkrubr_tipo = ?  \n";
        } 

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (tkrubr_tipo == 0 && null_tkrubr_tipo){ 
        }else { 
            pstm.setLong      (ind, tkrubr_tipo); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();
        pstm.close();

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public long       tkrubr_tipo = 0; 






    // --- Impostando a false queste prop. il def. � 0

    public boolean ib_calcola_token = true;
    public boolean ib_ctrl_mindate = false;
    public boolean null_tkrubr_tipo = true;
    public boolean null_nrposi = true;





}

