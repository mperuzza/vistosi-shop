package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Ep_lingua extends Atk_sql {

    public Ep_lingua() {

        super();
    }




    /****
        getAll: ep_lingua
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.ep_lingua  \n";
        if (is_farza_filtro_dipa.equals("S")){
            l_query  += "  where cddipa = '"+cddipa+"'  \n";
        }

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: ep_lingua
    **/ 

    public ResultSet getKey( String     cdling
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.ep_lingua  \n";
        if (cdling.equals("")){ 
            l_query  += "  where cdling is null  \n";
        }else { 
            l_query  += "  where cdling = ?  \n";
        } 

        pstm = setQuery( l_query ) ;

        ind = 1;
        if (cdling.equals("")){ 
        }else { 
            pstm.setString    (ind, cdling); ind += 1;
        } 

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: ep_lingua
    **/ 


    public com.ateikon.structure.Rec_ep_lingua  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_ep_lingua lstr = new com.ateikon.structure.Rec_ep_lingua();

        if (rs == null) return lstr;
        if (rs.getObject("cdling")!=null) lstr.cdling = rs.getString    ("cdling"); 
        if (rs.getObject("dsling")!=null) lstr.dsling = rs.getString    ("dsling"); 
        if (rs.getObject("dsling_en")!=null) lstr.dsling_en = rs.getString    ("dsling_en"); 
        if (rs.getObject("dsling_de")!=null) lstr.dsling_de = rs.getString    ("dsling_de"); 
        if (rs.getObject("dsling_fr")!=null) lstr.dsling_fr = rs.getString    ("dsling_fr"); 
        if (rs.getObject("dsling_es")!=null) lstr.dsling_es = rs.getString    ("dsling_es"); 
        if (rs.getObject("cdiso639")!=null) lstr.cdiso639 = rs.getString    ("cdiso639"); 
        if (rs.getObject("fgdef")!=null) lstr.fgdef = rs.getString    ("fgdef"); 
        if (rs.getObject("posi")!=null) lstr.posi = rs.getLong      ("posi"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 

        return lstr;
    }




    /****
        preupdate: ep_lingua

        sovrascrivere per impostare i controlli da effetuare prima dell'execute
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_ep_lingua astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }





    /****
        preupdate: ep_lingua

        sovrascrivere per impostare i controlli da effetuare per sincro

        questo metodo ï¿½ stato introdotto per la sincro: in fase di export prima  
        di scrivere il file XML viene chiamato
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_ep_lingua astr, ResultSet rs) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }



    /****
        execute: ep_lingua
    **/ 


    public int execute ( com.ateikon.structure.Rec_ep_lingua astr) throws Exception {
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
        l_query  += " select cdling\n";
        l_query  += "   from pgmr.ep_lingua  \n";
        if (astr.cdling.equals("")){ 
            l_query  += "  where cdling is null  \n";
        }else { 
            l_query  += "  where cdling = ?  \n";
        } 


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (astr.cdling.equals("")){ 
        }else { 
            pstm.setString    (ind, astr.cdling); ind += 1;
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
            throw new Exception("Errore Agg. ep_lingua! ");
        }
        */ 

        return tot_rec;
    }




    /****
        executeInsert: ep_lingua
    **/ 


    public int executeInsert( com.ateikon.structure.Rec_ep_lingua astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        if (ib_calcola_token){
            long ll_tk = getNew_token( );

            if (ll_tk <= 0){
                throw new Exception ("Error Keypool (ep_lingua): "+ll_tk );
            }

            this.cdling = of_token(ll_tk);
            astr.cdling = this.cdling;
        }else {
            this.cdling = astr.cdling;
        }



        l_query   = "";
        l_query  += " insert into pgmr.ep_lingua ( \n";
        l_query  += "             cdling   \n";
        l_query  += "           , dsling   \n";
        l_query  += "           , dsling_en   \n";
        l_query  += "           , dsling_de   \n";
        l_query  += "           , dsling_fr   \n";
        l_query  += "           , dsling_es   \n";
        l_query  += "           , cdiso639   \n";
        l_query  += "           , fgdef   \n";
        l_query  += "           , posi   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.dsling.equals("")) astr.dsling = null;
        if (astr.dsling_en.equals("")) astr.dsling_en = null;
        if (astr.dsling_de.equals("")) astr.dsling_de = null;
        if (astr.dsling_fr.equals("")) astr.dsling_fr = null;
        if (astr.dsling_es.equals("")) astr.dsling_es = null;
        if (astr.cdiso639.equals("")) astr.cdiso639 = null;
        if (astr.fgdef.equals("")) astr.fgdef = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;


        ind = 1;
        pstm.setString    (ind, astr.cdling); ind += 1;
        pstm.setString    (ind, astr.dsling); ind += 1;
        pstm.setString    (ind, astr.dsling_en); ind += 1;
        pstm.setString    (ind, astr.dsling_de); ind += 1;
        pstm.setString    (ind, astr.dsling_fr); ind += 1;
        pstm.setString    (ind, astr.dsling_es); ind += 1;
        pstm.setString    (ind, astr.cdiso639); ind += 1;
        pstm.setString    (ind, astr.fgdef); ind += 1;
        if (astr.posi == 0 && null_posi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.posi); ind += 1;
        } 
        pstm.setString    (ind, astr.cdazie); ind += 1;
        pstm.setString    (ind, astr.cddipa); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtinse); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        // --- Ripristino le Stringhe NULL

        if (astr.dsling == null) astr.dsling = "";
        if (astr.dsling_en == null) astr.dsling_en = "";
        if (astr.dsling_de == null) astr.dsling_de = "";
        if (astr.dsling_fr == null) astr.dsling_fr = "";
        if (astr.dsling_es == null) astr.dsling_es = "";
        if (astr.cdiso639 == null) astr.cdiso639 = "";
        if (astr.fgdef == null) astr.fgdef = "";
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

        long ll_tk = f_tabkey.getTabkey( "ep_lingua" );

        return ll_tk;

    }




    /****
        executeUpdate: ep_lingua
    **/ 


    public int executeUpdate( com.ateikon.structure.Rec_ep_lingua astr ) throws Exception { 
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.ep_lingua  \n";
        l_query  += "         set dsling = ?  \n";
        l_query  += "           , dsling_en = ?  \n";
        l_query  += "           , dsling_de = ?  \n";
        l_query  += "           , dsling_fr = ?  \n";
        l_query  += "           , dsling_es = ?  \n";
        l_query  += "           , cdiso639 = ?  \n";
        l_query  += "           , fgdef = ?  \n";
        l_query  += "           , posi = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "  where cdling = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.dsling.equals("")) astr.dsling = null;
        if (astr.dsling_en.equals("")) astr.dsling_en = null;
        if (astr.dsling_de.equals("")) astr.dsling_de = null;
        if (astr.dsling_fr.equals("")) astr.dsling_fr = null;
        if (astr.dsling_es.equals("")) astr.dsling_es = null;
        if (astr.cdiso639.equals("")) astr.cdiso639 = null;
        if (astr.fgdef.equals("")) astr.fgdef = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;


        ind = 1;
        pstm.setString    (ind, astr.dsling); ind += 1;
        pstm.setString    (ind, astr.dsling_en); ind += 1;
        pstm.setString    (ind, astr.dsling_de); ind += 1;
        pstm.setString    (ind, astr.dsling_fr); ind += 1;
        pstm.setString    (ind, astr.dsling_es); ind += 1;
        pstm.setString    (ind, astr.cdiso639); ind += 1;
        pstm.setString    (ind, astr.fgdef); ind += 1;
        if (astr.posi == 0 && null_posi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.posi); ind += 1;
        } 
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;

        if (astr.cdling.equals("")){ 
            pstm.setString    (ind, null); ind += 1;
        }else { 
            pstm.setString    (ind, astr.cdling); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.cdling = astr.cdling; 

        // --- Ripristino le Stringhe NULL

        if (astr.dsling == null) astr.dsling = "";
        if (astr.dsling_en == null) astr.dsling_en = "";
        if (astr.dsling_de == null) astr.dsling_de = "";
        if (astr.dsling_fr == null) astr.dsling_fr = "";
        if (astr.dsling_es == null) astr.dsling_es = "";
        if (astr.cdiso639 == null) astr.cdiso639 = "";
        if (astr.fgdef == null) astr.fgdef = "";
        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil == null) astr.profil = "";


        return tot_rec;

    }



    /****
        getKey: ep_lingua
    **/ 

    public int deleteKey( String     cdling
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " delete \n";
        l_query  += "   from pgmr.ep_lingua  \n";
        if (cdling.equals("")){ 
            l_query  += "  where cdling is null  \n";
        }else { 
            l_query  += "  where cdling = ?  \n";
        } 

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (cdling.equals("")){ 
        }else { 
            pstm.setString    (ind, cdling); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();
        pstm.close();

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public String     cdling = ""; 






    // --- Impostando a false queste prop. il def. ï¿½ 0

    public boolean ib_calcola_token = true;
    public boolean ib_ctrl_mindate = false;
    public boolean null_posi = true;




    public String is_farza_filtro_dipa = "N";



}

