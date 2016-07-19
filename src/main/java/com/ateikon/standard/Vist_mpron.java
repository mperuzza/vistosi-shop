package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Vist_mpron extends Atk_sql {

    public Vist_mpron() {

        super();
    }




    /****
        getAll: vist_mpron
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.vist_mpron  \n";
        if (is_farza_filtro_dipa.equals("S")){
            l_query  += "  where cddipa = '"+cddipa+"'  \n";
        }

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: vist_mpron
    **/ 

    public ResultSet getKey( long       tkmpron
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.vist_mpron  \n";
        if (tkmpron == 0 && null_tkmpron){ 
            l_query  += "  where tkmpron is null \n";
        }else { 
            l_query  += "  where tkmpron = ?  \n";
        } 

        pstm = setQuery( l_query ) ;

        ind = 1;
        if (tkmpron == 0 && null_tkmpron){ 
        }else { 
            pstm.setLong      (ind, tkmpron); ind += 1;
        } 

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: vist_mpron
    **/ 


    public com.ateikon.structure.Rec_vist_mpron  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_vist_mpron lstr = new com.ateikon.structure.Rec_vist_mpron();

        if (rs == null) return lstr;
        if (rs.getObject("tkmpron")!=null) lstr.tkmpron = rs.getLong      ("tkmpron"); 
        if (rs.getObject("tkclie")!=null) lstr.tkclie = rs.getString    ("tkclie"); 
        if (rs.getObject("tkmsg")!=null) lstr.tkmsg = rs.getLong      ("tkmsg"); 
        if (rs.getObject("dtinvio")!=null) lstr.dtinvio = rs.getTimestamp ("dtinvio"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("notapagame")!=null) lstr.notapagame = rs.getString    ("notapagame"); 
        if (rs.getObject("cdvett")!=null) lstr.cdvett = rs.getString    ("cdvett"); 
        if (rs.getObject("dsvett")!=null) lstr.dsvett = rs.getString    ("dsvett"); 
        if (rs.getObject("nota")!=null) lstr.nota = rs.getString    ("nota"); 
        if (rs.getObject("oggetto")!=null) lstr.oggetto = rs.getString    ("oggetto"); 
        if (rs.getObject("text_p1")!=null) lstr.text_p1 = rs.getString    ("text_p1"); 
        if (rs.getObject("text_p2")!=null) lstr.text_p2 = rs.getString    ("text_p2"); 
        if (rs.getObject("text_p3")!=null) lstr.text_p3 = rs.getString    ("text_p3"); 
        if (rs.getObject("text_p4")!=null) lstr.text_p4 = rs.getString    ("text_p4"); 
        if (rs.getObject("text_p5")!=null) lstr.text_p5 = rs.getString    ("text_p5"); 
        if (rs.getObject("text_p6")!=null) lstr.text_p6 = rs.getString    ("text_p6"); 
        if (rs.getObject("text_notapagame")!=null) lstr.text_notapagame = rs.getString    ("text_notapagame"); 
        if (rs.getObject("text_dsvett")!=null) lstr.text_dsvett = rs.getString    ("text_dsvett"); 

        return lstr;
    }




    /****
        preupdate: vist_mpron

        sovrascrivere per impostare i controlli da effetuare prima dell'execute
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_vist_mpron astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }





    /****
        preupdate: vist_mpron

        sovrascrivere per impostare i controlli da effetuare per sincro

        questo metodo ï¿½ stato introdotto per la sincro: in fase di export prima  
        di scrivere il file XML viene chiamato
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_vist_mpron astr, ResultSet rs) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }



    /****
        execute: vist_mpron
    **/ 


    public int execute ( com.ateikon.structure.Rec_vist_mpron astr) throws Exception {
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
        l_query  += " select tkmpron\n";
        l_query  += "   from pgmr.vist_mpron  \n";
        if (astr.tkmpron == 0 && null_tkmpron){ 
            l_query  += "  where tkmpron is null \n";
        }else { 
            l_query  += "  where tkmpron = ?  \n";
        } 


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (astr.tkmpron == 0 && null_tkmpron){ 
        }else { 
            pstm.setLong      (ind, astr.tkmpron); ind += 1;
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
            throw new Exception("Errore Agg. vist_mpron! ");
        }
        */ 

        return tot_rec;
    }




    /****
        executeInsert: vist_mpron
    **/ 


    public int executeInsert( com.ateikon.structure.Rec_vist_mpron astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        if (ib_calcola_token){
            long ll_tk = getNew_token( );

            if (ll_tk <= 0){
                throw new Exception ("Error Keypool (vist_mpron): "+ll_tk );
            }

            this.tkmpron = ll_tk;
            astr.tkmpron = this.tkmpron;
        }else {
            this.tkmpron = astr.tkmpron;
        }



        l_query   = "";
        l_query  += " insert into pgmr.vist_mpron ( \n";
        l_query  += "             tkmpron   \n";
        l_query  += "           , tkclie   \n";
        l_query  += "           , tkmsg   \n";
        l_query  += "           , dtinvio   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "           , notapagame   \n";
        l_query  += "           , cdvett   \n";
        l_query  += "           , dsvett   \n";
        l_query  += "           , nota   \n";
        l_query  += "           , oggetto   \n";
        l_query  += "           , text_p1   \n";
        l_query  += "           , text_p2   \n";
        l_query  += "           , text_p3   \n";
        l_query  += "           , text_p4   \n";
        l_query  += "           , text_p5   \n";
        l_query  += "           , text_p6   \n";
        l_query  += "           , text_notapagame   \n";
        l_query  += "           , text_dsvett   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.tkclie.equals("")) astr.tkclie = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;
        if (astr.notapagame.equals("")) astr.notapagame = null;
        if (astr.cdvett.equals("")) astr.cdvett = null;
        if (astr.dsvett.equals("")) astr.dsvett = null;
        if (astr.nota.equals("")) astr.nota = null;
        if (astr.oggetto.equals("")) astr.oggetto = null;
        if (astr.text_p1.equals("")) astr.text_p1 = null;
        if (astr.text_p2.equals("")) astr.text_p2 = null;
        if (astr.text_p3.equals("")) astr.text_p3 = null;
        if (astr.text_p4.equals("")) astr.text_p4 = null;
        if (astr.text_p5.equals("")) astr.text_p5 = null;
        if (astr.text_p6.equals("")) astr.text_p6 = null;
        if (astr.text_notapagame.equals("")) astr.text_notapagame = null;
        if (astr.text_dsvett.equals("")) astr.text_dsvett = null;


        ind = 1;
        if (astr.tkmpron == 0 && null_tkmpron){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkmpron); ind += 1;
        } 
        pstm.setString    (ind, astr.tkclie); ind += 1;
        if (astr.tkmsg == 0 && null_tkmsg){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkmsg); ind += 1;
        } 
        pstm.setTimestamp (ind, astr.dtinvio); ind += 1;
        pstm.setString    (ind, astr.cdazie); ind += 1;
        pstm.setString    (ind, astr.cddipa); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtinse); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;
        pstm.setString    (ind, astr.notapagame); ind += 1;
        pstm.setString    (ind, astr.cdvett); ind += 1;
        pstm.setString    (ind, astr.dsvett); ind += 1;
        pstm.setString    (ind, astr.nota); ind += 1;
        pstm.setString    (ind, astr.oggetto); ind += 1;
        pstm.setString    (ind, astr.text_p1); ind += 1;
        pstm.setString    (ind, astr.text_p2); ind += 1;
        pstm.setString    (ind, astr.text_p3); ind += 1;
        pstm.setString    (ind, astr.text_p4); ind += 1;
        pstm.setString    (ind, astr.text_p5); ind += 1;
        pstm.setString    (ind, astr.text_p6); ind += 1;
        pstm.setString    (ind, astr.text_notapagame); ind += 1;
        pstm.setString    (ind, astr.text_dsvett); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        // --- Ripristino le Stringhe NULL

        if (astr.tkclie == null) astr.tkclie = "";
        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil == null) astr.profil = "";
        if (astr.notapagame == null) astr.notapagame = "";
        if (astr.cdvett == null) astr.cdvett = "";
        if (astr.dsvett == null) astr.dsvett = "";
        if (astr.nota == null) astr.nota = "";
        if (astr.oggetto == null) astr.oggetto = "";
        if (astr.text_p1 == null) astr.text_p1 = "";
        if (astr.text_p2 == null) astr.text_p2 = "";
        if (astr.text_p3 == null) astr.text_p3 = "";
        if (astr.text_p4 == null) astr.text_p4 = "";
        if (astr.text_p5 == null) astr.text_p5 = "";
        if (astr.text_p6 == null) astr.text_p6 = "";
        if (astr.text_notapagame == null) astr.text_notapagame = "";
        if (astr.text_dsvett == null) astr.text_dsvett = "";


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

        long ll_tk = f_tabkey.getTabkey( "vist_mpron" );

        return ll_tk;

    }




    /****
        executeUpdate: vist_mpron
    **/ 


    public int executeUpdate( com.ateikon.structure.Rec_vist_mpron astr ) throws Exception { 
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.vist_mpron  \n";
        l_query  += "         set tkclie = ?  \n";
        l_query  += "           , tkmsg = ?  \n";
        l_query  += "           , dtinvio = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "           , notapagame = ?  \n";
        l_query  += "           , cdvett = ?  \n";
        l_query  += "           , dsvett = ?  \n";
        l_query  += "           , nota = ?  \n";
        l_query  += "           , oggetto = ?  \n";
        l_query  += "           , text_p1 = ?  \n";
        l_query  += "           , text_p2 = ?  \n";
        l_query  += "           , text_p3 = ?  \n";
        l_query  += "           , text_p4 = ?  \n";
        l_query  += "           , text_p5 = ?  \n";
        l_query  += "           , text_p6 = ?  \n";
        l_query  += "           , text_notapagame = ?  \n";
        l_query  += "           , text_dsvett = ?  \n";
        l_query  += "  where tkmpron = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.tkclie.equals("")) astr.tkclie = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;
        if (astr.notapagame.equals("")) astr.notapagame = null;
        if (astr.cdvett.equals("")) astr.cdvett = null;
        if (astr.dsvett.equals("")) astr.dsvett = null;
        if (astr.nota.equals("")) astr.nota = null;
        if (astr.oggetto.equals("")) astr.oggetto = null;
        if (astr.text_p1.equals("")) astr.text_p1 = null;
        if (astr.text_p2.equals("")) astr.text_p2 = null;
        if (astr.text_p3.equals("")) astr.text_p3 = null;
        if (astr.text_p4.equals("")) astr.text_p4 = null;
        if (astr.text_p5.equals("")) astr.text_p5 = null;
        if (astr.text_p6.equals("")) astr.text_p6 = null;
        if (astr.text_notapagame.equals("")) astr.text_notapagame = null;
        if (astr.text_dsvett.equals("")) astr.text_dsvett = null;


        ind = 1;
        pstm.setString    (ind, astr.tkclie); ind += 1;
        if (astr.tkmsg == 0 && null_tkmsg){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkmsg); ind += 1;
        } 
        pstm.setTimestamp (ind, astr.dtinvio); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;
        pstm.setString    (ind, astr.notapagame); ind += 1;
        pstm.setString    (ind, astr.cdvett); ind += 1;
        pstm.setString    (ind, astr.dsvett); ind += 1;
        pstm.setString    (ind, astr.nota); ind += 1;
        pstm.setString    (ind, astr.oggetto); ind += 1;
        pstm.setString    (ind, astr.text_p1); ind += 1;
        pstm.setString    (ind, astr.text_p2); ind += 1;
        pstm.setString    (ind, astr.text_p3); ind += 1;
        pstm.setString    (ind, astr.text_p4); ind += 1;
        pstm.setString    (ind, astr.text_p5); ind += 1;
        pstm.setString    (ind, astr.text_p6); ind += 1;
        pstm.setString    (ind, astr.text_notapagame); ind += 1;
        pstm.setString    (ind, astr.text_dsvett); ind += 1;

        if (astr.tkmpron == 0 && null_tkmpron){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkmpron); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.tkmpron = astr.tkmpron; 

        // --- Ripristino le Stringhe NULL

        if (astr.tkclie == null) astr.tkclie = "";
        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil == null) astr.profil = "";
        if (astr.notapagame == null) astr.notapagame = "";
        if (astr.cdvett == null) astr.cdvett = "";
        if (astr.dsvett == null) astr.dsvett = "";
        if (astr.nota == null) astr.nota = "";
        if (astr.oggetto == null) astr.oggetto = "";
        if (astr.text_p1 == null) astr.text_p1 = "";
        if (astr.text_p2 == null) astr.text_p2 = "";
        if (astr.text_p3 == null) astr.text_p3 = "";
        if (astr.text_p4 == null) astr.text_p4 = "";
        if (astr.text_p5 == null) astr.text_p5 = "";
        if (astr.text_p6 == null) astr.text_p6 = "";
        if (astr.text_notapagame == null) astr.text_notapagame = "";
        if (astr.text_dsvett == null) astr.text_dsvett = "";


        return tot_rec;

    }



    /****
        getKey: vist_mpron
    **/ 

    public int deleteKey( long       tkmpron
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " delete \n";
        l_query  += "   from pgmr.vist_mpron  \n";
        if (tkmpron == 0 && null_tkmpron){ 
            l_query  += "  where tkmpron is null \n";
        }else { 
            l_query  += "  where tkmpron = ?  \n";
        } 

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (tkmpron == 0 && null_tkmpron){ 
        }else { 
            pstm.setLong      (ind, tkmpron); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();
        pstm.close();

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public long       tkmpron = 0; 






    // --- Impostando a false queste prop. il def. ï¿½ 0

    public boolean ib_calcola_token = true;
    public boolean ib_ctrl_mindate = false;
    public boolean null_tkmpron = true;
    public boolean null_tkmsg = true;




    public String is_farza_filtro_dipa = "N";



}

