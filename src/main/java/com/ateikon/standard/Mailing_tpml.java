package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Mailing_tpml extends Atk_sql {

    public Mailing_tpml() {

        super();
    }




    /****
        getKey: mailing_tpml
    **/ 

    public ResultSet getKey( String     cdtpml
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.mailing_tpml  \n";
        l_query  += "  where cdtpml = ? \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString    (ind, cdtpml); ind += 1;

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: mailing_tpml
    **/ 


    public com.ateikon.structure.Rec_mailing_tpml  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_mailing_tpml lstr = new com.ateikon.structure.Rec_mailing_tpml();

        if (rs == null) return lstr;
        if (rs.getObject("cdtpml")!=null) lstr.cdtpml = rs.getString    ("cdtpml"); 
        if (rs.getObject("dstpml")!=null) lstr.dstpml = rs.getString    ("dstpml"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 
        if (rs.getObject("posi")!=null) lstr.posi = rs.getLong      ("posi"); 

        return lstr;
    }




    /****
        execute: mailing_tpml
    **/ 


    public int execute ( com.ateikon.structure.Rec_mailing_tpml astr) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec =  execute ( astr.cdtpml
                          , astr.dstpml
                          , astr.posi
                           );

        return tot_rec;
    }




    /****
        execute: mailing_tpml
    **/ 


    public int execute ( String     cdtpml
                       , String     dstpml
                       , long       posi
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " select cdtpml\n";
        l_query  += "   from pgmr.mailing_tpml  \n";
        l_query  += "  where cdtpml = ? \n";


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setString    (ind, cdtpml); ind += 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            pstm.close();
            tot_rec = executeUpdate ( cdtpml
                                    , dstpml
                                    , posi
                                    );
        }else {

            pstm.close();
            tot_rec = executeInsert ( cdtpml
                                    , dstpml
                                    , posi
                                    );
        }

        if (tot_rec != 1){

            m_connection.rollback();
            throw new Exception("Errore Agg. mailing_tpml! ");
        }

        return tot_rec;
    }




    /****
        executeInsert: mailing_tpml
    **/ 


    public int executeInsert( String     cdtpml
                            , String     dstpml
                            , long       posi
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        long ll_tk = getNew_token( );

        if (ll_tk <= 0){
            throw new Exception ("Error Keypool (mailing_tpml): "+ll_tk );
        }

        this.cdtpml = of_token(ll_tk);
        cdtpml = this.cdtpml;



        l_query   = "";
        l_query  += " insert into pgmr.mailing_tpml ( \n";
        l_query  += "             cdtpml   \n";
        l_query  += "           , dstpml   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "           , posi   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (dstpml.equals("")) dstpml = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;


        ind = 1;
        pstm.setString    (ind, cdtpml); ind += 1;
        pstm.setString    (ind, dstpml); ind += 1;
        pstm.setString    (ind, cdazie); ind += 1;
        pstm.setString    (ind, cddipa); ind += 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtinse); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;
        if (posi == 0 && null_posi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, posi); ind += 1;
        } 

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

        long ll_tk = f_tabkey.getTabkey( "mailing_tpml" );

        return ll_tk;

    }




    /****
        executeUpdate: mailing_tpml
    **/ 


    public int executeUpdate( String     cdtpml
                            , String     dstpml
                            , long       posi
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.mailing_tpml  \n";
        l_query  += "         set dstpml = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "           , posi = ?  \n";
        l_query  += "  where cdtpml = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (dstpml.equals("")) dstpml = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;


        ind = 1;
        pstm.setString    (ind, dstpml); ind += 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;
        if (posi == 0 && null_posi){ 
            pstm.setNull(ind, java.sql.Types.NUMERIC ); ind += 1;
        }else { 
            pstm.setLong      (ind, posi); ind += 1;
        } 

        pstm.setString    (ind, cdtpml); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.cdtpml = cdtpml; 

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public String     cdtpml = ""; 






    // --- Impostando a false queste prop. il def. è 0


    public boolean null_posi = true;







}

