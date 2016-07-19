package com.ateikon.standard;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Web_tabset extends Atk_sql {

    public Web_tabset() {

        super();
    }




    /****
        getAll: web_tabset
    **/ 

    public ResultSet getAll() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.web_tabset  \n";

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;

    }



    /****
        getKey: web_tabset
    **/ 

    public ResultSet getKey( String     tabella
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select * \n";
        l_query  += "   from pgmr.web_tabset  \n";
        l_query  += "  where tabella = ? \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString    (ind, tabella); ind += 1;

        rs = pstm.executeQuery();

        return rs;

    }




    /****
        setRec: web_tabset
    **/ 


    public com.ateikon.structure.Rec_web_tabset  setRec ( ResultSet rs) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;

        com.ateikon.structure.Rec_web_tabset lstr = new com.ateikon.structure.Rec_web_tabset();

        if (rs == null) return lstr;
        if (rs.getObject("tabella")!=null) lstr.tabella = rs.getString    ("tabella"); 
        if (rs.getObject("cdprogr")!=null) lstr.cdprogr = rs.getString    ("cdprogr"); 
        if (rs.getObject("cdazie")!=null) lstr.cdazie = rs.getString    ("cdazie"); 
        if (rs.getObject("cddipa")!=null) lstr.cddipa = rs.getString    ("cddipa"); 
        if (rs.getObject("profil")!=null) lstr.profil = rs.getString    ("profil"); 
        if (rs.getObject("dtinse")!=null) lstr.dtinse = rs.getTimestamp ("dtinse"); 
        if (rs.getObject("dtulag")!=null) lstr.dtulag = rs.getTimestamp ("dtulag"); 

        return lstr;
    }




    /****
        execute: web_tabset
    **/ 


    public int execute ( com.ateikon.structure.Rec_web_tabset astr) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec =  execute ( astr.tabella
                          , astr.cdprogr
                           );

        return tot_rec;
    }




    /****
        execute: web_tabset
    **/ 


    public int execute ( String     tabella
                       , String     cdprogr
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " select tabella\n";
        l_query  += "   from pgmr.web_tabset  \n";
        l_query  += "  where tabella = ? \n";


        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setString    (ind, tabella); ind += 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            pstm.close();
            tot_rec = executeUpdate ( tabella
                                    , cdprogr
                                    );
        }else {

            pstm.close();
            tot_rec = executeInsert ( tabella
                                    , cdprogr
                                    );
        }

        if (tot_rec != 1){

            m_connection.rollback();
            throw new Exception("Errore Agg. web_tabset! ");
        }

        return tot_rec;
    }




    /****
        executeInsert: web_tabset
    **/ 


    public int executeInsert( String     tabella
                            , String     cdprogr
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;



        if (ib_calcola_token){
            long ll_tk = getNew_token( );

            if (ll_tk <= 0){
                throw new Exception ("Error Keypool (web_tabset): "+ll_tk );
            }

            this.tabella = of_token(ll_tk);
            tabella = this.tabella;
        }



        l_query   = "";
        l_query  += " insert into pgmr.web_tabset ( \n";
        l_query  += "             tabella   \n";
        l_query  += "           , cdprogr   \n";
        l_query  += "           , cdazie   \n";
        l_query  += "           , cddipa   \n";
        l_query  += "           , profil   \n";
        l_query  += "           , dtinse   \n";
        l_query  += "           , dtulag   \n";
        l_query  += "            )values ( \n";
        l_query  += "             ?,?,?,?,?  \n";
        l_query  += "           , ?,?  \n";
        l_query  += "            ) \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cdprogr.equals("")) cdprogr = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;


        ind = 1;
        pstm.setString    (ind, tabella); ind += 1;
        pstm.setString    (ind, cdprogr); ind += 1;
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

        long ll_tk = f_tabkey.getTabkey( "web_tabset" );

        return ll_tk;

    }




    /****
        executeUpdate: web_tabset
    **/ 


    public int executeUpdate( String     tabella
                            , String     cdprogr
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " update pgmr.web_tabset  \n";
        l_query  += "         set cdprogr = ?  \n";
        l_query  += "           , profil = ?  \n";
        l_query  += "           , dtulag = ?  \n";
        l_query  += "  where tabella = ? \n";

        pstm = m_connection.prepareStatement( l_query ) ;


        if (cdprogr.equals("")) cdprogr = null;
        if (cdazie.equals("")) cdazie = null;
        if (cddipa.equals("")) cddipa = null;
        if (profil.equals("")) profil = null;


        ind = 1;
        pstm.setString    (ind, cdprogr); ind += 1;
        pstm.setString    (ind, profil); ind += 1;
        pstm.setTimestamp (ind, dtulag); ind += 1;

        pstm.setString    (ind, tabella); ind += 1;

        tot_rec = pstm.executeUpdate();

        pstm.close();
        pstm = null;

        this.tabella = tabella; 

        return tot_rec;

    }




    /****
        Properties KEY
    **/ 


    public String     tabella = ""; 






    // --- Impostando a false queste prop. il def. è 0

    public boolean ib_calcola_token = true;







}

