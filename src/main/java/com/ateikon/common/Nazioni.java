package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

public class Nazioni extends Atk_sql {

    public Nazioni() {
        super();
    }

    public ResultSet getDropdown() throws Exception {
        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        l_query = "";
        l_query += " select cdnazi, dsnazi \n";
        l_query += "   from pgmr.nazioni   \n";
        l_query += "  order by dsnazi      \n";

        pstm = setQuery(l_query);

        ind = 1;

        rs = pstm.executeQuery();

        return rs;


    }

    public ResultSet getNazioni(String order_by) throws Exception {
        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        l_query = "";
        l_query += " select *                 \n";
        l_query += "   from pgmr.nazioni       \n";
        l_query += "  order by " + order_by + "   \n";

        pstm = setQuery(l_query);

        ind = 1;

        rs = pstm.executeQuery();

        return rs;


    }

    public String getDescr(String cdnazi) throws Exception {
        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String dsnazi = "";

        l_query = "";
        l_query += " select dsnazi       \n";
        l_query += "   from pgmr.nazioni     \n";
        l_query += "  where cdnazi = ?   \n";

        pstm = setQuery(l_query);

        ind = 1;
        pstm.setString(ind, cdnazi);
        ind += 1;

        rs = pstm.executeQuery();

        if (rs != null && rs.next()) {

            if (rs.getObject("dsnazi") != null) {
                dsnazi = rs.getString("dsnazi");
            }

        }

        return dsnazi;


    }

    /**
     * *
     *
     * getKey: cdnazi
    *
     */
    public ResultSet getKey(String cdnazi) throws Exception {
        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String dsnazi = "";

        l_query = "";
        l_query += " select *                      \n";
        l_query += "   from pgmr.nazioni           \n";
        l_query += "  where cdnazi = '" + cdnazi + "'  \n";

        rs = sql_query(l_query);


        return rs;


    }

    /**
     * **
     * getDs: nazioni
    *
     */
    public String getDs(String cdnazi) throws Exception {

        return getDs(cdnazi, s_cdling);

    }

    /**
     * **
     * getDs: nazioni
    *
     */
    public String getDs(String cdnazi, String cdling) throws Exception {
        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        l_query = "";
        l_query += " select " + of_descr_lingua(cdling, "nazi", "dsnazi") + " as dsnazi \n";
        l_query += "   from pgmr.nazioni nazi \n";
        l_query += "  where nazi.cdnazi = '" + cdnazi + "'  \n";

        return sql_String(l_query);
    }
}
