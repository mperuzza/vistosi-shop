/*
    Creato da: Vendramin Andrea
 *  in data: 10/01/2008
 *  label: avendramin20080110
*/
package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;





public class Mis_reparto extends Atk_sql {

    
    public Mis_reparto() {

        super();
    }


    public ResultSet getDropdown() throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select cdrepa, dsrepa      \n";
        l_query  += "   from pgmr.mis_reparto    \n";
        l_query  += "  order by dsrepa           \n";

        pstm = setQuery( l_query ) ;

        ind = 1;

        rs = pstm.executeQuery();

        return rs;


    }

    //Inizio -- avendramin20080117
    
    public ResultSet getCdrepa(String cdrepa) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select *                    \n";
        l_query  += "   from pgmr.mis_reparto     \n";
        l_query  += "  where cdrepa = ?           \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString( ind, cdrepa ); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }

    //Fine -- avendramin20080117
    


}
