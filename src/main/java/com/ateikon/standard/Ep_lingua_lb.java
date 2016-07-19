package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Ep_lingua_lb extends Atk_sql {

    public Ep_lingua_lb() {

        super();
    }




    /****
        getAll: ep_lingua_lb
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.ep_lingua_lb  \n";
        if (is_farza_filtro_dipa.equals("S")){
            l_query  += "  where cddipa = '"+cddipa+"'  \n";
        }

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: ep_lingua_lb
    **/ 

    public ResultSet getKey( long       tklingua_lb
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.ep_lingua_lb  \n";
        if (tklingua_lb == 0 && null_tklingua_lb){ 
            l_query  += "  where tklingua_lb is null \n";
        }else { 
            l_query  += "  where tklingua_lb = ?  \n";
        } 

        pstm = setQuery( l_query ) ;

        ind = 1;
        if (tklingua_lb == 0 && null_tklingua_lb){ 
        }else { 
            pstm.setLong      (ind, tklingua_lb); ind += 1;
        } 

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: ep_lingua_lb
    **/ 


    public com.ateikon.structure.Rec_ep_lingua_lb  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_ep_lingua_lb lstr = new com.ateikon.structure.Rec_ep_lingua_lb();

        if (rs == null) return lstr;
        if (rs.getObject("tklingua_lb")!=null) lstr.tklingua_lb = rs.getLong      ("tklingua_lb"); 
        if (rs.getObject("cdling")!=null) lstr.cdling = rs.getString    ("cdling"); 
        if (rs.getObject("tklingua_url")!=null) lstr.tklingua_url = rs.getLong      ("tklingua_url"); 
        if (rs.getObject("cdcampo")!=null) lstr.cdcampo = rs.getString    ("cdcampo"); 
        if (rs.getObject("dscampo")!=null) lstr.dscampo = rs.getString    ("dscampo"); 
        if (rs.getObject("dscampo_defling")!=null) lstr.dscampo_defling = rs.getString    ("dscampo_defling"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 

        return lstr;
    }




    /****
        preupdate: ep_lingua_lb

        sovrascrivere per impostare i controlli da effetuare prima dell'execute
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_ep_lingua_lb astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }





    /****
        preupdate: ep_lingua_lb

        sovrascrivere per impostare i controlli da effetuare per sincro

        questo metodo ï¿½ stato introdotto per la sincro: in fase di export prima  
        di scrivere il file XML viene chiamato
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_ep_lingua_lb astr, ResultSet rs) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }



    /****
        execute: ep_lingua_lb
    **/ 


    public int execute ( com.ateikon.structure.Rec_ep_lingua_lb astr) throws Exception {
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
        l_query  += " select tklingua_lb\n";
        l_query  += "   from pgmr.ep_lingua_lb  \n";
        if (astr.tklingua_lb == 0 && null_tklingua_lb){ 
            l_query  += "  where tklingua_lb is null \n";
        }else { 
            l_query  += "  where tklingua_lb = ?  \n";
        } 
        

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (astr.tklingua_lb == 0 && null_tklingua_lb){ 
        }else { 
            pstm.setLong      (ind, astr.tklingua_lb); ind += 1;
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
            throw new Exception("Errore Agg. ep_lingua_lb! ");
        }
        */ 

        return tot_rec;
    }




    /****
        executeInsert: ep_lingua_lb
    **/ 


    public int executeInsert( com.ateikon.structure.Rec_ep_lingua_lb astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        if (ib_calcola_token){
            long ll_tk = getNew_token( );

            if (ll_tk <= 0){
                throw new Exception ("Error Keypool (ep_lingua_lb): "+ll_tk );
            }

            this.tklingua_lb = ll_tk;
            astr.tklingua_lb = this.tklingua_lb;
        }else {
            this.tklingua_lb = astr.tklingua_lb;
        }



        l_query   = "";
        l_query  += " insert into pgmr.ep_lingua_lb ( \n";
        l_query  += "             tklingua_lb   \n";
        l_query  += "           , cdling   \n";
        l_query  += "           , tklingua_url   \n";
        l_query  += "           , cdcampo   \n";
        l_query  += "           , dscampo   \n";
        l_query  += "           , dscampo_defling   \n";
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


        if (astr.cdling.equals("")) astr.cdling = null;
        if (astr.cdcampo.equals("")) astr.cdcampo = null;
        if (astr.dscampo.equals("")) astr.dscampo = null;
        if (astr.dscampo_defling.equals("")) astr.dscampo_defling = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;


        ind = 1;
        if (astr.tklingua_lb == 0 && null_tklingua_lb){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tklingua_lb); ind += 1;
        } 
        pstm.setString    (ind, astr.cdling); ind += 1;
        if (astr.tklingua_url == 0 && null_tklingua_url){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tklingua_url); ind += 1;
        } 
        pstm.setString    (ind, astr.cdcampo); ind += 1;
        pstm.setString    (ind, astr.dscampo); ind += 1;
        pstm.setString    (ind, astr.dscampo_defling); ind += 1;
        pstm.setString    (ind, astr.cdazie); ind += 1;
        pstm.setString    (ind, astr.cddipa); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtinse); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        // --- Ripristino le Stringhe NULL

        if (astr.cdling == null) astr.cdling = "";
        if (astr.cdcampo == null) astr.cdcampo = "";
        if (astr.dscampo == null) astr.dscampo = "";
        if (astr.dscampo_defling == null) astr.dscampo_defling = "";
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

        long ll_tk = f_tabkey.getTabkey( "ep_lingua_lb" );

        return ll_tk;

    }




    /****
        executeUpdate: ep_lingua_lb
    **/ 


    public int executeUpdate( com.ateikon.structure.Rec_ep_lingua_lb astr ) throws Exception { 
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.ep_lingua_lb  \n";
        l_query  += "         set cdling = ?  \n";
        l_query  += "           , tklingua_url = ?  \n";
        l_query  += "           , cdcampo = ?  \n";
        l_query  += "           , dscampo = ?  \n";
        l_query  += "           , dscampo_defling = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "  where tklingua_lb = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.cdling.equals("")) astr.cdling = null;
        if (astr.cdcampo.equals("")) astr.cdcampo = null;
        if (astr.dscampo.equals("")) astr.dscampo = null;
        if (astr.dscampo_defling.equals("")) astr.dscampo_defling = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;


        ind = 1;
        pstm.setString    (ind, astr.cdling); ind += 1;
        if (astr.tklingua_url == 0 && null_tklingua_url){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tklingua_url); ind += 1;
        } 
        pstm.setString    (ind, astr.cdcampo); ind += 1;
        pstm.setString    (ind, astr.dscampo); ind += 1;
        pstm.setString    (ind, astr.dscampo_defling); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;

        if (astr.tklingua_lb == 0 && null_tklingua_lb){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tklingua_lb); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.tklingua_lb = astr.tklingua_lb; 

        // --- Ripristino le Stringhe NULL

        if (astr.cdling == null) astr.cdling = "";
        if (astr.cdcampo == null) astr.cdcampo = "";
        if (astr.dscampo == null) astr.dscampo = "";
        if (astr.dscampo_defling == null) astr.dscampo_defling = "";
        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil == null) astr.profil = "";


        return tot_rec;

    }



    /****
        getKey: ep_lingua_lb
    **/ 

    public int deleteKey( long       tklingua_lb
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " delete \n";
        l_query  += "   from pgmr.ep_lingua_lb  \n";
        if (tklingua_lb == 0 && null_tklingua_lb){ 
            l_query  += "  where tklingua_lb is null \n";
        }else { 
            l_query  += "  where tklingua_lb = ?  \n";
        } 

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (tklingua_lb == 0 && null_tklingua_lb){ 
        }else { 
            pstm.setLong      (ind, tklingua_lb); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();
        pstm.close();

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public long       tklingua_lb = 0; 






    // --- Impostando a false queste prop. il def. ï¿½ 0

    public boolean ib_calcola_token = true;
    public boolean ib_ctrl_mindate = false;
    public boolean null_tklingua_lb = true;
    public boolean null_tklingua_url = true;




    public String is_farza_filtro_dipa = "N";



}

