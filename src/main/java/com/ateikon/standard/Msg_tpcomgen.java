package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Msg_tpcomgen extends Atk_sql {

    public Msg_tpcomgen() {

        super();
    }




    /****
        getAll: msg_tpcomgen
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.msg_tpcomgen  \n";
        if (is_farza_filtro_dipa.equals("S")){
            l_query  += "  where cddipa = '"+cddipa+"'  \n";
        }

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: msg_tpcomgen
    **/ 

    public ResultSet getKey( long       tktpcomgen
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.msg_tpcomgen  \n";
        if (tktpcomgen == 0 && null_tktpcomgen){ 
            l_query  += "  where tktpcomgen is null \n";
        }else { 
            l_query  += "  where tktpcomgen = ?  \n";
        } 

        pstm = setQuery( l_query ) ;

        ind = 1;
        if (tktpcomgen == 0 && null_tktpcomgen){ 
        }else { 
            pstm.setLong      (ind, tktpcomgen); ind += 1;
        } 

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: msg_tpcomgen
    **/ 


    public com.ateikon.structure.Rec_msg_tpcomgen  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_msg_tpcomgen lstr = new com.ateikon.structure.Rec_msg_tpcomgen();

        if (rs == null) return lstr;
        if (rs.getObject("tktpcomgen")!=null) lstr.tktpcomgen = rs.getLong      ("tktpcomgen"); 
        if (rs.getObject("dstpcomgen")!=null) lstr.dstpcomgen = rs.getString    ("dstpcomgen"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("cdtpcomgen_m")!=null) lstr.cdtpcomgen_m = rs.getString    ("cdtpcomgen_m"); 
        if (rs.getObject("dstpcomgen_en")!=null) lstr.dstpcomgen_en = rs.getString    ("dstpcomgen_en"); 
        if (rs.getObject("dstpcomgen_de")!=null) lstr.dstpcomgen_de = rs.getString    ("dstpcomgen_de"); 
        if (rs.getObject("dstpcomgen_fr")!=null) lstr.dstpcomgen_fr = rs.getString    ("dstpcomgen_fr"); 
        if (rs.getObject("dstpcomgen_es")!=null) lstr.dstpcomgen_es = rs.getString    ("dstpcomgen_es"); 

        return lstr;
    }




    /****
        preupdate: msg_tpcomgen

        sovrascrivere per impostare i controlli da effetuare prima dell'execute
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_msg_tpcomgen astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }





    /****
        preupdate: msg_tpcomgen

        sovrascrivere per impostare i controlli da effetuare per sincro

        questo metodo � stato introdotto per la sincro: in fase di export prima  
        di scrivere il file XML viene chiamato
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_msg_tpcomgen astr, ResultSet rs) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }



    /****
        execute: msg_tpcomgen
    **/ 


    public int execute ( com.ateikon.structure.Rec_msg_tpcomgen astr) throws Exception {
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
        l_query  += " select tktpcomgen\n";
        l_query  += "   from pgmr.msg_tpcomgen  \n";
        if (astr.tktpcomgen == 0 && null_tktpcomgen){ 
            l_query  += "  where tktpcomgen is null \n";
        }else { 
            l_query  += "  where tktpcomgen = ?  \n";
        } 


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (astr.tktpcomgen == 0 && null_tktpcomgen){ 
        }else { 
            pstm.setLong      (ind, astr.tktpcomgen); ind += 1;
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
            throw new Exception("Errore Agg. msg_tpcomgen! ");
        }
        */ 

        return tot_rec;
    }




    /****
        executeInsert: msg_tpcomgen
    **/ 


    public int executeInsert( com.ateikon.structure.Rec_msg_tpcomgen astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        if (ib_calcola_token){
            long ll_tk = getNew_token( );

            if (ll_tk <= 0){
                throw new Exception ("Error Keypool (msg_tpcomgen): "+ll_tk );
            }

            this.tktpcomgen = ll_tk;
            astr.tktpcomgen = this.tktpcomgen;
        }else {
            this.tktpcomgen = astr.tktpcomgen;
        }



        l_query   = "";
        l_query  += " insert into pgmr.msg_tpcomgen ( \n";
        l_query  += "             tktpcomgen   \n";
        l_query  += "           , dstpcomgen   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "           , cdtpcomgen_m   \n";
        l_query  += "           , dstpcomgen_en   \n";
        l_query  += "           , dstpcomgen_de   \n";
        l_query  += "           , dstpcomgen_fr   \n";
        l_query  += "           , dstpcomgen_es   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.dstpcomgen.equals("")) astr.dstpcomgen = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;
        if (astr.cdtpcomgen_m.equals("")) astr.cdtpcomgen_m = null;
        if (astr.dstpcomgen_en.equals("")) astr.dstpcomgen_en = null;
        if (astr.dstpcomgen_de.equals("")) astr.dstpcomgen_de = null;
        if (astr.dstpcomgen_fr.equals("")) astr.dstpcomgen_fr = null;
        if (astr.dstpcomgen_es.equals("")) astr.dstpcomgen_es = null;


        ind = 1;
        if (astr.tktpcomgen == 0 && null_tktpcomgen){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tktpcomgen); ind += 1;
        } 
        pstm.setString    (ind, astr.dstpcomgen); ind += 1;
        pstm.setString    (ind, astr.cdazie); ind += 1;
        pstm.setString    (ind, astr.cddipa); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtinse); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;
        pstm.setString    (ind, astr.cdtpcomgen_m); ind += 1;
        pstm.setString    (ind, astr.dstpcomgen_en); ind += 1;
        pstm.setString    (ind, astr.dstpcomgen_de); ind += 1;
        pstm.setString    (ind, astr.dstpcomgen_fr); ind += 1;
        pstm.setString    (ind, astr.dstpcomgen_es); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        // --- Ripristino le Stringhe NULL

        if (astr.dstpcomgen == null) astr.dstpcomgen = "";
        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil == null) astr.profil = "";
        if (astr.cdtpcomgen_m == null) astr.cdtpcomgen_m = "";
        if (astr.dstpcomgen_en == null) astr.dstpcomgen_en = "";
        if (astr.dstpcomgen_de == null) astr.dstpcomgen_de = "";
        if (astr.dstpcomgen_fr == null) astr.dstpcomgen_fr = "";
        if (astr.dstpcomgen_es == null) astr.dstpcomgen_es = "";


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

        long ll_tk = f_tabkey.getTabkey( "msg_tpcomgen" );

        return ll_tk;

    }




    /****
        executeUpdate: msg_tpcomgen
    **/ 


    public int executeUpdate( com.ateikon.structure.Rec_msg_tpcomgen astr ) throws Exception { 
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.msg_tpcomgen  \n";
        l_query  += "         set dstpcomgen = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "           , cdtpcomgen_m = ?  \n";
        l_query  += "           , dstpcomgen_en = ?  \n";
        l_query  += "           , dstpcomgen_de = ?  \n";
        l_query  += "           , dstpcomgen_fr = ?  \n";
        l_query  += "           , dstpcomgen_es = ?  \n";
        l_query  += "  where tktpcomgen = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.dstpcomgen.equals("")) astr.dstpcomgen = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;
        if (astr.cdtpcomgen_m.equals("")) astr.cdtpcomgen_m = null;
        if (astr.dstpcomgen_en.equals("")) astr.dstpcomgen_en = null;
        if (astr.dstpcomgen_de.equals("")) astr.dstpcomgen_de = null;
        if (astr.dstpcomgen_fr.equals("")) astr.dstpcomgen_fr = null;
        if (astr.dstpcomgen_es.equals("")) astr.dstpcomgen_es = null;


        ind = 1;
        pstm.setString    (ind, astr.dstpcomgen); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;
        pstm.setString    (ind, astr.cdtpcomgen_m); ind += 1;
        pstm.setString    (ind, astr.dstpcomgen_en); ind += 1;
        pstm.setString    (ind, astr.dstpcomgen_de); ind += 1;
        pstm.setString    (ind, astr.dstpcomgen_fr); ind += 1;
        pstm.setString    (ind, astr.dstpcomgen_es); ind += 1;

        if (astr.tktpcomgen == 0 && null_tktpcomgen){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tktpcomgen); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.tktpcomgen = astr.tktpcomgen; 

        // --- Ripristino le Stringhe NULL

        if (astr.dstpcomgen == null) astr.dstpcomgen = "";
        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil == null) astr.profil = "";
        if (astr.cdtpcomgen_m == null) astr.cdtpcomgen_m = "";
        if (astr.dstpcomgen_en == null) astr.dstpcomgen_en = "";
        if (astr.dstpcomgen_de == null) astr.dstpcomgen_de = "";
        if (astr.dstpcomgen_fr == null) astr.dstpcomgen_fr = "";
        if (astr.dstpcomgen_es == null) astr.dstpcomgen_es = "";


        return tot_rec;

    }



    /****
        getKey: msg_tpcomgen
    **/ 

    public int deleteKey( long       tktpcomgen
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " delete \n";
        l_query  += "   from pgmr.msg_tpcomgen  \n";
        if (tktpcomgen == 0 && null_tktpcomgen){ 
            l_query  += "  where tktpcomgen is null \n";
        }else { 
            l_query  += "  where tktpcomgen = ?  \n";
        } 

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (tktpcomgen == 0 && null_tktpcomgen){ 
        }else { 
            pstm.setLong      (ind, tktpcomgen); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();
        pstm.close();

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public long       tktpcomgen = 0; 






    // --- Impostando a false queste prop. il def. � 0

    public boolean ib_calcola_token = true;
    public boolean ib_ctrl_mindate = false;
    public boolean null_tktpcomgen = true;




    public String is_farza_filtro_dipa = "N";



}

