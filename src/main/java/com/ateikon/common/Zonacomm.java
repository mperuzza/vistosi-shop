package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import java.util.Date;

public class Zonacomm extends Atk_sql {

    public Zonacomm() {

        super();
    }

    public ResultSet getZonacomm() throws Exception {
        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        l_query = "";
        l_query += " select cdzcom             \n";
        l_query += "      , dszcom             \n";
        l_query += "      , cdzcom_m           \n";
        l_query += "   from pgmr.zonacomm      \n";
        l_query += " where cdazie = ?          \n";
        l_query += "  order by dszcom    \n";

        pstm = setQuery(l_query);

        ind = 1;
        pstm.setString(ind, cdazie);
        ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }

    public ResultSet getDropdown() throws Exception {
        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        l_query = "";
        l_query += " select z.cdzcom                              \n";
        l_query += "      , z.dszcom                              \n";
        l_query += "   from pgmr.zonacomm z                       \n";
        l_query += "  where z.cdazie = '" + cdazie + "'               \n";
        l_query += "  order by z.dszcom                           \n";

        pstm = setQuery(l_query);

        ind = 1;

        rs = pstm.executeQuery();

        return rs;


    }

    public String getDs(String cdzcom) throws Exception {

        return getDs(cdzcom, s_cdling);

    }

    public String getDs(String cdzcom, String cdling) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String dszcom = "";


        l_query = "";
        l_query += " select " + of_descr_lingua(cdling, "", "dszcom") + " as dszcom                  \n";
        l_query += "   from pgmr.zonacomm           \n";
        l_query += "  where cdzcom = ?              \n";
        l_query += "    and cdazie = ?              \n";

        pstm = setQuery(l_query);

        ind = 1;
        pstm.setString(ind, cdzcom);
        ind += 1;
        pstm.setString(ind, cdazie);
        ind += 1;

        rs = pstm.executeQuery();

        if (rs != null && rs.next()) {

            if (rs.getObject("dszcom") != null) {
                dszcom = rs.getString("dszcom");
            }

        }

        return dszcom;
    }
}
