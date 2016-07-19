package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import com.ateikon.util.StringParsToHTML;

public class Atk_lingua extends Atk_sql  {

    public Atk_lingua() {

        super();
    }







    /***


    */

    public int count ( ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        l_query   = "";
		l_query  += "  select count(*)         \n";
		l_query  += "    from pgmr.atk_lingua  \n";

        tot_rec = sql_int(l_query);

        return tot_rec;

    }


    public String cdling = "";

 
    public ResultSet getDropDown() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        l_query   = "";
		l_query  += "  select l.cdling              \n";
		l_query  += "       , l.dsling              \n";
		l_query  += "    from pgmr.atk_lingua l     \n";
		l_query  += "   order by l.posi           \n";

		pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();

        return rs;


    }
    
    public String getCdlingDef () throws Exception {
        int               ind     = 0;
        int               tot_rec = 0;
        PreparedStatement pstm    = null;
        ResultSet         rs      = null;



        l_query  = "";
        l_query += " select cdling           \n";
        l_query += "   from pgmr.atk_lingua  \n";
        l_query += "  where fgdef = 'S'      \n";

        return sql_String(l_query);
    }

    public String getDefault() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        l_query   = "";
        l_query  += "  select l.cdling              \n";
        l_query  += "    from pgmr.atk_lingua l     \n";
        l_query  += "   where fgdef = 'S'       \n";

        String ls_ = sql_String(l_query);

        return ls_;


    }

    public String getFgdef(String cdling) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        l_query   = "";
        l_query  += "  select l.fgdef \n";
        l_query  += "    from pgmr.atk_lingua l     \n";
        l_query  += "   where l.cdling = '" + cdling + "'       \n";

        String ls_ = sql_String(l_query);

        return ls_;


    }
    // Properties


}
