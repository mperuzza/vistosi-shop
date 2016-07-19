package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Vist_oridati extends Atk_sql {

    public Vist_oridati() {

        super();
    }




    /****
        getAll: vist_oridati
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.vist_oridati  \n";
        if (is_farza_filtro_dipa.equals("S")){
            l_query  += "  where cddipa = '"+cddipa+"'  \n";
        }

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: vist_oridati
    **/ 

    public ResultSet getKey( String     oridati
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.vist_oridati  \n";
        if (oridati.equals("")){ 
            l_query  += "  where oridati is null  \n";
        }else { 
            l_query  += "  where oridati = ?  \n";
        } 

        pstm = setQuery( l_query ) ;

        ind = 1;
        if (oridati.equals("")){ 
        }else { 
            pstm.setString    (ind, oridati); ind += 1;
        } 

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: vist_oridati
    **/ 


    public com.ateikon.structure.Rec_vist_oridati  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_vist_oridati lstr = new com.ateikon.structure.Rec_vist_oridati();

        if (rs == null) return lstr;
        if (rs.getObject("oridati")!=null) lstr.oridati = rs.getString    ("oridati"); 
        if (rs.getObject("oridati_m")!=null) lstr.oridati_m = rs.getString    ("oridati_m"); 
        if (rs.getObject("dsoridati")!=null) lstr.dsoridati = rs.getString    ("dsoridati"); 
        if (rs.getObject("dsoridati_en")!=null) lstr.dsoridati_en = rs.getString    ("dsoridati_en"); 
        if (rs.getObject("dsoridati_de")!=null) lstr.dsoridati_de = rs.getString    ("dsoridati_de"); 
        if (rs.getObject("dsoridati_fr")!=null) lstr.dsoridati_fr = rs.getString    ("dsoridati_fr"); 
        if (rs.getObject("dsoridati_es")!=null) lstr.dsoridati_es = rs.getString    ("dsoridati_es"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("dsoridati_ru")!=null) lstr.dsoridati_ru = rs.getString    ("dsoridati_ru"); 
        if (rs.getObject("fgdef")!=null) lstr.fgdef = rs.getString    ("fgdef"); 
        if (rs.getObject("cdtporidati")!=null) lstr.cdtporidati = rs.getString    ("cdtporidati"); 

        return lstr;
    }




    /****
        preupdate: vist_oridati

        sovrascrivere per impostare i controlli da effetuare prima dell'execute
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_vist_oridati astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }





    /****
        preupdate: vist_oridati

        sovrascrivere per impostare i controlli da effetuare per sincro

        questo metodo ï¿½ stato introdotto per la sincro: in fase di export prima  
        di scrivere il file XML viene chiamato
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_vist_oridati astr, ResultSet rs) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }



    /****
        execute: vist_oridati
    **/ 


    public int execute ( com.ateikon.structure.Rec_vist_oridati astr) throws Exception {
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
        l_query  += " select oridati\n";
        l_query  += "   from pgmr.vist_oridati  \n";
        if (astr.oridati.equals("")){ 
            l_query  += "  where oridati is null  \n";
        }else { 
            l_query  += "  where oridati = ?  \n";
        } 


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (astr.oridati.equals("")){ 
        }else { 
            pstm.setString    (ind, astr.oridati); ind += 1;
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
            throw new Exception("Errore Agg. vist_oridati! ");
        }
        */ 

        return tot_rec;
    }




    /****
        executeInsert: vist_oridati
    **/ 


    public int executeInsert( com.ateikon.structure.Rec_vist_oridati astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        if (ib_calcola_token){
            long ll_tk = getNew_token( );

            if (ll_tk <= 0){
                throw new Exception ("Error Keypool (vist_oridati): "+ll_tk );
            }

            this.oridati = of_token(ll_tk);
            astr.oridati = this.oridati;
        }else {
            this.oridati = astr.oridati;
        }



        l_query   = "";
        l_query  += " insert into pgmr.vist_oridati ( \n";
        l_query  += "             oridati   \n";
        l_query  += "           , oridati_m   \n";
        l_query  += "           , dsoridati   \n";
        l_query  += "           , dsoridati_en   \n";
        l_query  += "           , dsoridati_de   \n";
        l_query  += "           , dsoridati_fr   \n";
        l_query  += "           , dsoridati_es   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "           , dsoridati_ru   \n";
        l_query  += "           , fgdef   \n";
        l_query  += "           , cdtporidati   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.oridati_m.equals("")) astr.oridati_m = null;
        if (astr.dsoridati.equals("")) astr.dsoridati = null;
        if (astr.dsoridati_en.equals("")) astr.dsoridati_en = null;
        if (astr.dsoridati_de.equals("")) astr.dsoridati_de = null;
        if (astr.dsoridati_fr.equals("")) astr.dsoridati_fr = null;
        if (astr.dsoridati_es.equals("")) astr.dsoridati_es = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;
        if (astr.dsoridati_ru.equals("")) astr.dsoridati_ru = null;
        if (astr.fgdef.equals("")) astr.fgdef = null;
        if (astr.cdtporidati.equals("")) astr.cdtporidati = null;


        ind = 1;
        pstm.setString    (ind, astr.oridati); ind += 1;
        pstm.setString    (ind, astr.oridati_m); ind += 1;
        pstm.setString    (ind, astr.dsoridati); ind += 1;
        pstm.setString    (ind, astr.dsoridati_en); ind += 1;
        pstm.setString    (ind, astr.dsoridati_de); ind += 1;
        pstm.setString    (ind, astr.dsoridati_fr); ind += 1;
        pstm.setString    (ind, astr.dsoridati_es); ind += 1;
        pstm.setString    (ind, astr.cdazie); ind += 1;
        pstm.setString    (ind, astr.cddipa); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtinse); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;
        pstm.setString    (ind, astr.dsoridati_ru); ind += 1;
        pstm.setString    (ind, astr.fgdef); ind += 1;
        pstm.setString    (ind, astr.cdtporidati); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        // --- Ripristino le Stringhe NULL

        if (astr.oridati_m == null) astr.oridati_m = "";
        if (astr.dsoridati == null) astr.dsoridati = "";
        if (astr.dsoridati_en == null) astr.dsoridati_en = "";
        if (astr.dsoridati_de == null) astr.dsoridati_de = "";
        if (astr.dsoridati_fr == null) astr.dsoridati_fr = "";
        if (astr.dsoridati_es == null) astr.dsoridati_es = "";
        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil == null) astr.profil = "";
        if (astr.dsoridati_ru == null) astr.dsoridati_ru = "";
        if (astr.fgdef == null) astr.fgdef = "";
        if (astr.cdtporidati == null) astr.cdtporidati = "";


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

        long ll_tk = f_tabkey.getTabkey( "vist_oridati" );

        return ll_tk;

    }




    /****
        executeUpdate: vist_oridati
    **/ 


    public int executeUpdate( com.ateikon.structure.Rec_vist_oridati astr ) throws Exception { 
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.vist_oridati  \n";
        l_query  += "         set oridati_m = ?  \n";
        l_query  += "           , dsoridati = ?  \n";
        l_query  += "           , dsoridati_en = ?  \n";
        l_query  += "           , dsoridati_de = ?  \n";
        l_query  += "           , dsoridati_fr = ?  \n";
        l_query  += "           , dsoridati_es = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "           , dsoridati_ru = ?  \n";
        l_query  += "           , fgdef = ?  \n";
        l_query  += "           , cdtporidati = ?  \n";
        l_query  += "  where oridati = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.oridati_m.equals("")) astr.oridati_m = null;
        if (astr.dsoridati.equals("")) astr.dsoridati = null;
        if (astr.dsoridati_en.equals("")) astr.dsoridati_en = null;
        if (astr.dsoridati_de.equals("")) astr.dsoridati_de = null;
        if (astr.dsoridati_fr.equals("")) astr.dsoridati_fr = null;
        if (astr.dsoridati_es.equals("")) astr.dsoridati_es = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;
        if (astr.dsoridati_ru.equals("")) astr.dsoridati_ru = null;
        if (astr.fgdef.equals("")) astr.fgdef = null;
        if (astr.cdtporidati.equals("")) astr.cdtporidati = null;


        ind = 1;
        pstm.setString    (ind, astr.oridati_m); ind += 1;
        pstm.setString    (ind, astr.dsoridati); ind += 1;
        pstm.setString    (ind, astr.dsoridati_en); ind += 1;
        pstm.setString    (ind, astr.dsoridati_de); ind += 1;
        pstm.setString    (ind, astr.dsoridati_fr); ind += 1;
        pstm.setString    (ind, astr.dsoridati_es); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;
        pstm.setString    (ind, astr.dsoridati_ru); ind += 1;
        pstm.setString    (ind, astr.fgdef); ind += 1;
        pstm.setString    (ind, astr.cdtporidati); ind += 1;

        if (astr.oridati.equals("")){ 
            pstm.setString    (ind, null); ind += 1;
        }else { 
            pstm.setString    (ind, astr.oridati); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.oridati = astr.oridati; 

        // --- Ripristino le Stringhe NULL

        if (astr.oridati_m == null) astr.oridati_m = "";
        if (astr.dsoridati == null) astr.dsoridati = "";
        if (astr.dsoridati_en == null) astr.dsoridati_en = "";
        if (astr.dsoridati_de == null) astr.dsoridati_de = "";
        if (astr.dsoridati_fr == null) astr.dsoridati_fr = "";
        if (astr.dsoridati_es == null) astr.dsoridati_es = "";
        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil == null) astr.profil = "";
        if (astr.dsoridati_ru == null) astr.dsoridati_ru = "";
        if (astr.fgdef == null) astr.fgdef = "";
        if (astr.cdtporidati == null) astr.cdtporidati = "";


        return tot_rec;

    }



    /****
        getKey: vist_oridati
    **/ 

    public int deleteKey( String     oridati
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " delete \n";
        l_query  += "   from pgmr.vist_oridati  \n";
        if (oridati.equals("")){ 
            l_query  += "  where oridati is null  \n";
        }else { 
            l_query  += "  where oridati = ?  \n";
        } 

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (oridati.equals("")){ 
        }else { 
            pstm.setString    (ind, oridati); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();
        pstm.close();

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public String     oridati = ""; 






    // --- Impostando a false queste prop. il def. ï¿½ 0

    public boolean ib_calcola_token = true;
    public boolean ib_ctrl_mindate = false;




    public String is_farza_filtro_dipa = "N";



}

