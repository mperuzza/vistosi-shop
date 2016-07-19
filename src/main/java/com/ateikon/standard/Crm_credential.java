package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Crm_credential extends Atk_sql {

    public Crm_credential() {

        super();
    }




    /****
        getAll: crm_credential
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.crm_credential  \n";
        if (is_farza_filtro_dipa.equals("S")){
            l_query  += "  where cddipa = '"+cddipa+"'  \n";
        }

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: crm_credential
    **/ 

    public ResultSet getKey( long       tkcredential
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.crm_credential  \n";
        if (tkcredential == 0 && null_tkcredential){ 
            l_query  += "  where tkcredential is null \n";
        }else { 
            l_query  += "  where tkcredential = ?  \n";
        } 

        pstm = setQuery( l_query ) ;

        ind = 1;
        if (tkcredential == 0 && null_tkcredential){ 
        }else { 
            pstm.setLong      (ind, tkcredential); ind += 1;
        } 

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: crm_credential
    **/ 


    public com.ateikon.structure.Rec_crm_credential  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_crm_credential lstr = new com.ateikon.structure.Rec_crm_credential();

        if (rs == null) return lstr;
        if (rs.getObject("tkcredential")!=null) lstr.tkcredential = rs.getLong      ("tkcredential"); 
        if (rs.getObject("keycode")!=null) lstr.keycode = rs.getString    ("keycode"); 
        if (rs.getObject("dtival")!=null) lstr.dtival = rs.getTimestamp ("dtival"); 
        if (rs.getObject("dtfval")!=null) lstr.dtfval = rs.getTimestamp ("dtfval"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("tkutente")!=null) lstr.tkutente = rs.getLong      ("tkutente"); 

        return lstr;
    }




    /****
        preupdate: crm_credential

        sovrascrivere per impostare i controlli da effetuare prima dell'execute
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_crm_credential astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }





    /****
        preupdate: crm_credential

        sovrascrivere per impostare i controlli da effetuare per sincro

        questo metodo ï¿½ stato introdotto per la sincro: in fase di export prima  
        di scrivere il file XML viene chiamato
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_crm_credential astr, ResultSet rs) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }



    /****
        execute: crm_credential
    **/ 


    public int execute ( com.ateikon.structure.Rec_crm_credential astr) throws Exception {
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
        l_query  += " select tkcredential\n";
        l_query  += "   from pgmr.crm_credential  \n";
        if (astr.tkcredential == 0 && null_tkcredential){ 
            l_query  += "  where tkcredential is null \n";
        }else { 
            l_query  += "  where tkcredential = ?  \n";
        } 


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (astr.tkcredential == 0 && null_tkcredential){ 
        }else { 
            pstm.setLong      (ind, astr.tkcredential); ind += 1;
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
            throw new Exception("Errore Agg. crm_credential! ");
        }
        */ 

        return tot_rec;
    }




    /****
        executeInsert: crm_credential
    **/ 


    public int executeInsert( com.ateikon.structure.Rec_crm_credential astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        if (ib_calcola_token){
            long ll_tk = getNew_token( );

            if (ll_tk <= 0){
                throw new Exception ("Error Keypool (crm_credential): "+ll_tk );
            }

            this.tkcredential = ll_tk;
            astr.tkcredential = this.tkcredential;
        }else {
            this.tkcredential = astr.tkcredential;
        }



        l_query   = "";
        l_query  += " insert into pgmr.crm_credential ( \n";
        l_query  += "             tkcredential   \n";
        l_query  += "           , keycode   \n";
        l_query  += "           , dtival   \n";
        l_query  += "           , dtfval   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "           , tkutente   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.keycode.equals("")) astr.keycode = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;


        ind = 1;
        if (astr.tkcredential == 0 && null_tkcredential){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkcredential); ind += 1;
        } 
        pstm.setString    (ind, astr.keycode); ind += 1;
        pstm.setTimestamp (ind, astr.dtival); ind += 1;
        pstm.setTimestamp (ind, astr.dtfval); ind += 1;
        pstm.setString    (ind, astr.cdazie); ind += 1;
        pstm.setString    (ind, astr.cddipa); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtinse); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;
        if (astr.tkutente == 0 && null_tkutente){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkutente); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        // --- Ripristino le Stringhe NULL

        if (astr.keycode == null) astr.keycode = "";
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

        long ll_tk = f_tabkey.getTabkey( "crm_credential" );

        return ll_tk;

    }




    /****
        executeUpdate: crm_credential
    **/ 


    public int executeUpdate( com.ateikon.structure.Rec_crm_credential astr ) throws Exception { 
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.crm_credential  \n";
        l_query  += "         set keycode = ?  \n";
        l_query  += "           , dtival = ?  \n";
        l_query  += "           , dtfval = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "           , tkutente = ?  \n";
        l_query  += "  where tkcredential = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.keycode.equals("")) astr.keycode = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;


        ind = 1;
        pstm.setString    (ind, astr.keycode); ind += 1;
        pstm.setTimestamp (ind, astr.dtival); ind += 1;
        pstm.setTimestamp (ind, astr.dtfval); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;
        if (astr.tkutente == 0 && null_tkutente){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkutente); ind += 1;
        } 

        if (astr.tkcredential == 0 && null_tkcredential){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.tkcredential); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.tkcredential = astr.tkcredential; 

        // --- Ripristino le Stringhe NULL

        if (astr.keycode == null) astr.keycode = "";
        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil == null) astr.profil = "";


        return tot_rec;

    }



    /****
        getKey: crm_credential
    **/ 

    public int deleteKey( long       tkcredential
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " delete \n";
        l_query  += "   from pgmr.crm_credential  \n";
        if (tkcredential == 0 && null_tkcredential){ 
            l_query  += "  where tkcredential is null \n";
        }else { 
            l_query  += "  where tkcredential = ?  \n";
        } 

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (tkcredential == 0 && null_tkcredential){ 
        }else { 
            pstm.setLong      (ind, tkcredential); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();
        pstm.close();

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public long       tkcredential = 0; 






    // --- Impostando a false queste prop. il def. ï¿½ 0

    public boolean ib_calcola_token = true;
    public boolean ib_ctrl_mindate = false;
    public boolean null_tkcredential = true;
    public boolean null_tkutente = true;




    public String is_farza_filtro_dipa = "N";



}

