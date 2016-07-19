package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class Kg_gptipomapr extends Atk_sql {

    public Kg_gptipomapr() {
        
        super();
    }






    public int countGptipomapr( ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";                          
		l_query  += " select count(*)            \n";
		l_query  += "   from pgmr.kg_gptipomapr  \n";
		l_query  += "  where fgfiltroweb = 'S'   \n";

		tot_rec = sql_int( l_query ) ;

        return tot_rec;
    }






    public ResultSet getGptipomapr(String cdling, String order_by) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        String ls_suffisso = of_lingua("kg_gptipomapr",cdling);


        l_query   = "";
		l_query  += " select cdgptipm                               \n";
		l_query  += "      , dsgptipm"+ls_suffisso+" as dsgptipm    \n";
		l_query  += "      , cdgptipm_m                             \n";
		l_query  += "      , nrposi                                 \n";
		l_query  += "      , fgfiltroweb                            \n";
		l_query  += "   from pgmr.kg_gptipomapr                     \n";
		l_query  += "  where fgfiltroweb = 'S'                      \n";
		l_query  += "  order by "+order_by+"                        \n";

		pstm = setQuery( l_query ) ;

        ind = 1;

        rs = pstm.executeQuery();

        return rs;


    }



    public ResultSet getCdgptipm(String cdgptipm) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
		l_query  += " select *                    \n";
		l_query  += "   from pgmr.kg_gptipomapr \n";
		l_query  += "  where cdgptipm = ?           \n";

		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString( ind, cdgptipm ); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }

    public String getDescr_m(String  cdgptipm_m, String cdling) throws Exception {
        
        return getDescr("", cdgptipm_m, cdling);

    }

    public String getDescr(String  cdgptipm, String cdling) throws Exception {
        
        return getDescr(cdgptipm, "", cdling);

    }

    public String getDescr(String cdgptipm, String  cdgptipm_m, String cdling) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        String dsgptipm  = "";
        String ls_suffisso = of_lingua("kg_gptipomapr",cdling);
        
        l_query   = "";
		l_query  += " select dsgptipm" + ls_suffisso + " as dsgptipm         \n";
		l_query  += "   from pgmr.kg_gptipomapr  \n";
        if (!cdgptipm.equals("")){
            l_query  += "  where cdgptipm = ? \n";
        }else {
		    l_query  += "  where cdgptipm_m = ? \n";
        }

		pstm = setQuery( l_query ) ;

        ind = 1;
        if (!cdgptipm.equals("")){
            pstm.setString( ind, cdgptipm ); ind += 1;
        }else {
            pstm.setString( ind, cdgptipm_m ); ind += 1;
        }

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            if (rs.getObject("dsgptipm")!=null ) dsgptipm = rs.getString("dsgptipm");

        }

       
        return dsgptipm;


    }






}
