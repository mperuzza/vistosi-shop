package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Mrp_arch_stato extends Atk_sql {

    public Mrp_arch_stato() {

        super();
    }




    /****
        getAll: mrp_arch_stato
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.mrp_arch_stato  \n";
        if (is_farza_filtro_dipa.equals("S")){
            l_query  += "  where cddipa = '"+cddipa+"'  \n";
        }

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: mrp_arch_stato
    **/ 

    public ResultSet getKey( String     cdstato
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.mrp_arch_stato  \n";
        if (cdstato.equals("")){ 
            l_query  += "  where cdstato is null  \n";
        }else { 
            l_query  += "  where cdstato = ?  \n";
        } 

        pstm = setQuery( l_query ) ;

        ind = 1;
        if (cdstato.equals("")){ 
        }else { 
            pstm.setString    (ind, cdstato); ind += 1;
        } 

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: mrp_arch_stato
    **/ 


    public com.ateikon.structure.Rec_mrp_arch_stato  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_mrp_arch_stato lstr = new com.ateikon.structure.Rec_mrp_arch_stato();

        if (rs == null) return lstr;
        if (rs.getObject("cdstato")!=null) lstr.cdstato = rs.getString    ("cdstato"); 
        if (rs.getObject("cdstato_m")!=null) lstr.cdstato_m = rs.getString    ("cdstato_m"); 
        if (rs.getObject("dsstato")!=null) lstr.dsstato = rs.getString    ("dsstato"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("fesclu")!=null) lstr.fesclu = rs.getString    ("fesclu"); 
        if (rs.getObject("dsstatoweb")!=null) lstr.dsstatoweb = rs.getString    ("dsstatoweb"); 
        if (rs.getObject("fg_agg")!=null) lstr.fg_agg = rs.getString    ("fg_agg"); 
        if (rs.getObject("vist_fgrgb")!=null) lstr.vist_fgrgb = rs.getString    ("vist_fgrgb"); 
        if (rs.getObject("vist_rgb_r")!=null) lstr.vist_rgb_r = rs.getLong      ("vist_rgb_r"); 
        if (rs.getObject("vist_rgb_g")!=null) lstr.vist_rgb_g = rs.getLong      ("vist_rgb_g"); 
        if (rs.getObject("vist_rgb_b")!=null) lstr.vist_rgb_b = rs.getLong      ("vist_rgb_b"); 

        return lstr;
    }




    /****
        preupdate: mrp_arch_stato

        sovrascrivere per impostare i controlli da effetuare prima dell'execute
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_mrp_arch_stato astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }





    /****
        preupdate: mrp_arch_stato

        sovrascrivere per impostare i controlli da effetuare per sincro

        questo metodo ï¿½ stato introdotto per la sincro: in fase di export prima  
        di scrivere il file XML viene chiamato
    **/ 


    public int preupdate ( com.ateikon.structure.Rec_mrp_arch_stato astr, ResultSet rs) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;


        return 1;
    }



    /****
        execute: mrp_arch_stato
    **/ 


    public int execute ( com.ateikon.structure.Rec_mrp_arch_stato astr) throws Exception {
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
        l_query  += " select cdstato\n";
        l_query  += "   from pgmr.mrp_arch_stato  \n";
        if (astr.cdstato.equals("")){ 
            l_query  += "  where cdstato is null  \n";
        }else { 
            l_query  += "  where cdstato = ?  \n";
        } 


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (astr.cdstato.equals("")){ 
        }else { 
            pstm.setString    (ind, astr.cdstato); ind += 1;
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
            throw new Exception("Errore Agg. mrp_arch_stato! ");
        }
        */ 

        return tot_rec;
    }




    /****
        executeInsert: mrp_arch_stato
    **/ 


    public int executeInsert( com.ateikon.structure.Rec_mrp_arch_stato astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        if (ib_calcola_token){
            long ll_tk = getNew_token( );

            if (ll_tk <= 0){
                throw new Exception ("Error Keypool (mrp_arch_stato): "+ll_tk );
            }

            this.cdstato = of_token(ll_tk);
            astr.cdstato = this.cdstato;
        }else {
            this.cdstato = astr.cdstato;
        }



        l_query   = "";
        l_query  += " insert into pgmr.mrp_arch_stato ( \n";
        l_query  += "             cdstato   \n";
        l_query  += "           , cdstato_m   \n";
        l_query  += "           , dsstato   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "           , fesclu   \n";
        l_query  += "           , dsstatoweb   \n";
        l_query  += "           , fg_agg   \n";
        l_query  += "           , vist_fgrgb   \n";
        l_query  += "           , vist_rgb_r   \n";
        l_query  += "           , vist_rgb_g   \n";
        l_query  += "           , vist_rgb_b   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?,?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.cdstato_m.equals("")) astr.cdstato_m = null;
        if (astr.dsstato.equals("")) astr.dsstato = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;
        if (astr.fesclu.equals("")) astr.fesclu = null;
        if (astr.dsstatoweb.equals("")) astr.dsstatoweb = null;
        if (astr.fg_agg.equals("")) astr.fg_agg = null;
        if (astr.vist_fgrgb.equals("")) astr.vist_fgrgb = null;


        ind = 1;
        pstm.setString    (ind, astr.cdstato); ind += 1;
        pstm.setString    (ind, astr.cdstato_m); ind += 1;
        pstm.setString    (ind, astr.dsstato); ind += 1;
        pstm.setString    (ind, astr.cdazie); ind += 1;
        pstm.setString    (ind, astr.cddipa); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtinse); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;
        pstm.setString    (ind, astr.fesclu); ind += 1;
        pstm.setString    (ind, astr.dsstatoweb); ind += 1;
        pstm.setString    (ind, astr.fg_agg); ind += 1;
        pstm.setString    (ind, astr.vist_fgrgb); ind += 1;
        if (astr.vist_rgb_r == 0 && null_vist_rgb_r){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.vist_rgb_r); ind += 1;
        } 
        if (astr.vist_rgb_g == 0 && null_vist_rgb_g){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.vist_rgb_g); ind += 1;
        } 
        if (astr.vist_rgb_b == 0 && null_vist_rgb_b){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.vist_rgb_b); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        // --- Ripristino le Stringhe NULL

        if (astr.cdstato_m == null) astr.cdstato_m = "";
        if (astr.dsstato == null) astr.dsstato = "";
        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil == null) astr.profil = "";
        if (astr.fesclu == null) astr.fesclu = "";
        if (astr.dsstatoweb == null) astr.dsstatoweb = "";
        if (astr.fg_agg == null) astr.fg_agg = "";
        if (astr.vist_fgrgb == null) astr.vist_fgrgb = "";


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

        long ll_tk = f_tabkey.getTabkey( "mrp_arch_stato" );

        return ll_tk;

    }




    /****
        executeUpdate: mrp_arch_stato
    **/ 


    public int executeUpdate( com.ateikon.structure.Rec_mrp_arch_stato astr ) throws Exception { 
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.mrp_arch_stato  \n";
        l_query  += "         set cdstato_m = ?  \n";
        l_query  += "           , dsstato = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "           , fesclu = ?  \n";
        l_query  += "           , dsstatoweb = ?  \n";
        l_query  += "           , fg_agg = ?  \n";
        l_query  += "           , vist_fgrgb = ?  \n";
        l_query  += "           , vist_rgb_r = ?  \n";
        l_query  += "           , vist_rgb_g = ?  \n";
        l_query  += "           , vist_rgb_b = ?  \n";
        l_query  += "  where cdstato = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (astr.cdstato_m.equals("")) astr.cdstato_m = null;
        if (astr.dsstato.equals("")) astr.dsstato = null;
        if (astr.cdazie.equals("")) astr.cdazie = null;
        if (astr.cddipa.equals("")) astr.cddipa = null;
        if (astr.profil.equals("")) astr.profil = null;
        if (astr.fesclu.equals("")) astr.fesclu = null;
        if (astr.dsstatoweb.equals("")) astr.dsstatoweb = null;
        if (astr.fg_agg.equals("")) astr.fg_agg = null;
        if (astr.vist_fgrgb.equals("")) astr.vist_fgrgb = null;


        ind = 1;
        pstm.setString    (ind, astr.cdstato_m); ind += 1;
        pstm.setString    (ind, astr.dsstato); ind += 1;
        pstm.setString    (ind, astr.profil); ind += 1;
        pstm.setTimestamp (ind, astr.dtulag); ind += 1;
        pstm.setString    (ind, astr.fesclu); ind += 1;
        pstm.setString    (ind, astr.dsstatoweb); ind += 1;
        pstm.setString    (ind, astr.fg_agg); ind += 1;
        pstm.setString    (ind, astr.vist_fgrgb); ind += 1;
        if (astr.vist_rgb_r == 0 && null_vist_rgb_r){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.vist_rgb_r); ind += 1;
        } 
        if (astr.vist_rgb_g == 0 && null_vist_rgb_g){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.vist_rgb_g); ind += 1;
        } 
        if (astr.vist_rgb_b == 0 && null_vist_rgb_b){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, astr.vist_rgb_b); ind += 1;
        } 

        if (astr.cdstato.equals("")){ 
            pstm.setString    (ind, null); ind += 1;
        }else { 
            pstm.setString    (ind, astr.cdstato); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.cdstato = astr.cdstato; 

        // --- Ripristino le Stringhe NULL

        if (astr.cdstato_m == null) astr.cdstato_m = "";
        if (astr.dsstato == null) astr.dsstato = "";
        if (astr.cdazie == null) astr.cdazie = "";
        if (astr.cddipa == null) astr.cddipa = "";
        if (astr.profil == null) astr.profil = "";
        if (astr.fesclu == null) astr.fesclu = "";
        if (astr.dsstatoweb == null) astr.dsstatoweb = "";
        if (astr.fg_agg == null) astr.fg_agg = "";
        if (astr.vist_fgrgb == null) astr.vist_fgrgb = "";


        return tot_rec;

    }



    /****
        getKey: mrp_arch_stato
    **/ 

    public int deleteKey( String     cdstato
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " delete \n";
        l_query  += "   from pgmr.mrp_arch_stato  \n";
        if (cdstato.equals("")){ 
            l_query  += "  where cdstato is null  \n";
        }else { 
            l_query  += "  where cdstato = ?  \n";
        } 

        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        if (cdstato.equals("")){ 
        }else { 
            pstm.setString    (ind, cdstato); ind += 1;
        } 

        tot_rec = pstm.executeUpdate();
        pstm.close();

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public String     cdstato = ""; 






    // --- Impostando a false queste prop. il def. ï¿½ 0

    public boolean ib_calcola_token = true;
    public boolean ib_ctrl_mindate = false;
    public boolean null_vist_rgb_r = true;
    public boolean null_vist_rgb_g = true;
    public boolean null_vist_rgb_b = true;




    public String is_farza_filtro_dipa = "N";



}

