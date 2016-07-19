package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Banca_sella_otp extends com.ateikon.standard.Banca_sella_otp {


    public Banca_sella_otp() {

        super();
    }




    /***
        Restituisce il token per questa tabella
    */


    public ResultSet getFiles( ) throws Exception{ 
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        l_query  = "";
        l_query += " select filename                 \n";
        l_query += "      , count(*)     as tot_otp  \n";
        l_query += "      , max(dtinse)  as dtinse   \n";
        l_query += "      , max(dtdel)   as dtdel    \n";
        l_query += "      , max(tkotp)   as tkotp    \n";
        l_query += "   from pgmr.banca_sella_otp     \n";
        l_query += "  group by filename              \n";
        l_query += "  order by dtinse DESC           \n";

        rs = sql_query(l_query);

        return rs;

    }





    /***
        Restituisce il token per questa tabella
    */


    public long getNew_token( ) throws Exception{ 
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        l_query  = "";
        l_query += " select max(tkotp)  \n";
        l_query += "   from pgmr.banca_sella_otp \n";


        long ll_tk = sql_long(l_query);

        ll_tk += 1;

        return ll_tk;

    }


    public boolean isFile(String filename ) throws Exception{ 
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        l_query  = "";
        l_query += " select count(tkotp)  \n";
        l_query += "   from pgmr.banca_sella_otp      \n";
        l_query += "  where filename = '"+filename+"' \n";


        long ll_ = sql_long(l_query);

        if (ll_ > 0){
            return true;
        }

        return false;

    }






    public int delete_file( String filename) throws Exception{ 
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        l_query  = "";
        l_query += " delete                           \n";
        l_query += "   from pgmr.banca_sella_otp      \n";
        l_query += "  where filename = '"+filename+"' \n";



        tot_rec = sql_update(l_query);


        return tot_rec;

    }


    public String getRic( ) throws Exception{ 
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        long   tkotp = 0;
        String otp = "";
        int    rc = 0;

        l_query  = "";
        l_query += " select tkotp                \n";
        l_query += "      , otp                  \n";
        l_query += "   from pgmr.banca_sella_otp \n";
        l_query += "  where dtdel is null        \n";
        l_query += "    and tipo_otp = 'RIC'     \n";
        l_query += "  order by tkotp             \n";

        pstm = m_connection.prepareStatement( l_query );

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){
            
            if (rs.getObject("tkotp")!=null)  tkotp = rs.getLong("tkotp");
            if (rs.getObject("otp"  )!=null)  otp   = rs.getString("otp");

        }

        pstm.close();

        if (otp.equals("") || tkotp <= 0){
            throw new Exception("O.T.P. NON trovato");
        }

        l_query  = "";
        l_query += " update pgmr.banca_sella_otp   \n";
        l_query += "    set dtdel = ?              \n";
        l_query += "      , profil = ?             \n";
        l_query += "      , dtulag = ?             \n";
        l_query += "  where tkotp = "+tkotp+"      \n";

        pstm = m_connection.prepareStatement( l_query );

        ind = 1;
        pstm.setTimestamp(ind, oggi  ); ind += 1;
        pstm.setString   (ind, profil); ind += 1;
        pstm.setTimestamp(ind, dtulag); ind += 1;
        
        
        tot_rec = pstm.executeUpdate();

        pstm.close();

        if (tot_rec != 1){
            
            m_connection.rollback();
            throw new Exception("banca_sella_otp Set dtdel ERROR ");
        }
        
        m_connection.commit();
        

        return otp;

    }




    public long getRis( String otp ) throws Exception{ 
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        long   tkotp = 0;
        int    rc = 0;

        l_query  = "";
        l_query += " select tkotp                \n";
        l_query += "   from pgmr.banca_sella_otp \n";
        l_query += "  where dtdel is null        \n";
        l_query += "    and tipo_otp = 'RIS'     \n";
        l_query += "    and otp = ?              \n";
        l_query += "  order by tkotp             \n";

        pstm = m_connection.prepareStatement( l_query );

        ind = 1;
        pstm.setString   (ind, otp); ind += 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){
            
            if (rs.getObject("tkotp")!=null)  tkotp = rs.getLong("tkotp");

        }

        pstm.close();

        if (tkotp <= 0){
            // throw new Exception("O.T.P. RIS NON trovato");
            return tkotp;
        }

        l_query  = "";
        l_query += " update pgmr.banca_sella_otp   \n";
        l_query += "    set dtdel = ?              \n";
        l_query += "      , profil = ?             \n";
        l_query += "      , dtulag = ?             \n";
        l_query += "  where tkotp = "+tkotp+"      \n";

        pstm = m_connection.prepareStatement( l_query );

        ind = 1;
        pstm.setTimestamp(ind, oggi  ); ind += 1;
        pstm.setString   (ind, profil); ind += 1;
        pstm.setTimestamp(ind, dtulag); ind += 1;
        
        
        tot_rec = pstm.executeUpdate();

        pstm.close();

        if (tot_rec != 1){
            
            m_connection.rollback();
            throw new Exception("banca_sella_otp Set dtdel ERROR ");
        }
        
        m_connection.commit();
        

        return tkotp;

    }







}

