package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Cat_tipomapr_escl extends Atk_sql {

    public Cat_tipomapr_escl() {

        super();
    }




    /****
        getAll: cat_tipomapr_escl
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.cat_tipomapr_escl  \n";

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: cat_tipomapr_escl
    **/ 

    public ResultSet getKey( String     cdtipm
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.cat_tipomapr_escl  \n";
        l_query  += "  where cdtipm = ? \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString    (ind, cdtipm); ind += 1;

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: cat_tipomapr_escl
    **/ 


    public com.ateikon.structure.Rec_cat_tipomapr_escl  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_cat_tipomapr_escl lstr = new com.ateikon.structure.Rec_cat_tipomapr_escl();

        if (rs == null) return lstr;
        if (rs.getObject("cdtipm")!=null) lstr.cdtipm = rs.getString    ("cdtipm"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 

        return lstr;
    }




    /****
        execute: cat_tipomapr_escl
    **/ 


    public int execute ( com.ateikon.structure.Rec_cat_tipomapr_escl astr) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec =  execute ( astr.cdtipm
                           );

        return tot_rec;
    }




    /****
        execute: cat_tipomapr_escl
    **/ 


    public int execute ( String     cdtipm
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " select cdtipm\n";
        l_query  += "   from pgmr.cat_tipomapr_escl  \n";
        l_query  += "  where cdtipm = ? \n";


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setString    (ind, cdtipm); ind += 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            pstm.close();
            tot_rec = executeUpdate ( cdtipm
                                    );
        }else {

            pstm.close();
            tot_rec = executeInsert ( cdtipm
                                    );
        }

        if (tot_rec != 1){

            m_connection.rollback();
            throw new Exception("Errore Agg. cat_tipomapr_escl! ");
        }

        return tot_rec;
    }




    /****
        executeInsert: cat_tipomapr_escl
    **/ 


    public int executeInsert( String     cdtipm
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        if (ib_calcola_token){
            long ll_tk = getNew_token( );

            if (ll_tk <= 0){
                throw new Exception ("Error Keypool (cat_tipomapr_escl): "+ll_tk );
            }

            this.cdtipm = of_token(ll_tk);
            cdtipm = this.cdtipm;
        }



        l_query   = "";
        l_query  += " insert into pgmr.cat_tipomapr_escl ( \n";
        l_query  += "             cdtipm   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;


        ind = 1;
        pstm.setString    (ind, cdtipm); ind += 1;
        pstm.setString    (ind, cdazie); ind += 1;
        pstm.setString    (ind, cddipa); ind += 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtinse); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

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

        long ll_tk = f_tabkey.getTabkey( "cat_tipomapr_escl" );

        return ll_tk;

    }




    /****
        executeUpdate: cat_tipomapr_escl
    **/ 


    public int executeUpdate( String     cdtipm
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.cat_tipomapr_escl  \n";
        l_query  += "         set profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "  where cdtipm = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;


        ind = 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;

        pstm.setString    (ind, cdtipm); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.cdtipm = cdtipm; 

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public String     cdtipm = ""; 






    // --- Impostando a false queste prop. il def. è 0

    public boolean ib_calcola_token = true;







}

