package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import com.ateikon.structure.Rec_vist_oridati;


public class Vist_oridati extends com.ateikon.standard.Vist_oridati {


    public Vist_oridati() {

        super();
    }


    
    public String of_getDefaultOridati() throws Exception {
        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        l_query  = "";
        l_query += " select oridati                \n";
        l_query += "   from pgmr.vist_oridati      \n";
        l_query += "  where fgdef = 'S'            \n";
        
        return sql_String(l_query);
    }    
    


    public String getDs(String oridati, String cdling) throws Exception {

        int               ind     = 0;
        int               tot_rec = 0;
        PreparedStatement pstm    = null;
        ResultSet         rs      = null;
        String            dsoridati    = "";


        l_query   = "";
        l_query  += " select "+ of_descr_lingua(cdling, "", "dsoridati") +" as dsoridati                  \n";
        l_query  += "   from pgmr.vist_oridati           \n";
        l_query  += "  where oridati = ?              \n";
        l_query  += "    and cdazie = ?              \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString( ind, oridati); ind += 1;
        pstm.setString( ind, cdazie); ind += 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            if (rs.getObject("dsoridati")!=null ) dsoridati = rs.getString("dsoridati");

        }

        return dsoridati;
    }
    
    
    
    public static final String CDTPORIDATI_EVENTO = "EVENTO";
    public static final String CDTPORIDATI_FIERA = "FIERA";

}

