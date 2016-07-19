package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class Archenti extends Atk_sql {

    public Archenti() {
        
        super();
    }


    public ResultSet getEnte(String tkclie, String tkforn, String cdagen) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        String codice  = "";
        String cdente  = "";


        if (!tkclie.equals("")){
            
            l_query   = "";
    		l_query  += " select cdente           \n";
    		l_query  += "   from pgmr.archclie a  \n";
    		l_query  += "  where tkclie = ?         \n";
            
            codice = tkclie;

        }else if (!tkforn.equals("")){
            
            l_query   = "";
    		l_query  += " select cdente           \n";
    		l_query  += "   from pgmr.archforn a  \n";
    		l_query  += "  where tkforn = ?         \n";

            codice = tkforn;

        }else if (!cdagen.equals("")){
            
            l_query   = "";
    		l_query  += " select cdente           \n";
    		l_query  += "   from pgmr.archagen a  \n";
    		l_query  += "  where cdagen = ?         \n";

            codice = cdagen;
        }


        if (codice.equals("")) return null;

        pstm = m_connection.prepareStatement( l_query );

        ind = 1;
        pstm.setString(ind, codice ); ind += 1;


        rs = pstm.executeQuery();


        if (rs !=null && rs.next() && rs.getObject(1)!=null){
            
            cdente = rs.getString(1);
        }

        
        pstm.close();
        pstm = null;



        l_query   = "";
		l_query  += " select *                  \n";
		l_query  += "   from pgmr.archenti  \n";
		l_query  += "  where cdente = ?         \n";

		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString(ind, cdente); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }



    public ResultSet getKey(String cdente) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;


        l_query   = "";
		l_query  += " select *                  \n";
		l_query  += "   from pgmr.archenti  \n";
		l_query  += "  where cdente = ?         \n";

		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString(ind, cdente); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }


}
