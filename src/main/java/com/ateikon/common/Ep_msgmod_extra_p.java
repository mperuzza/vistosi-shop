package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Ep_msgmod_extra_p extends com.ateikon.standard.Ep_msgmod_extra_p {


    public Ep_msgmod_extra_p() {

        super();
    }

    /**
     * Carica il contenuto della mail.<br/> 
     * 
     * Il contenuto contenuto &egrave; privo di intestazione e pi&egrave; di pagina; questi devono essere applicati solo 
     * in fase di invio mail fichiamando il metodo of_getHTML
     * 
     * @param tkmsgmod
     * @param cdling
     * @return
     * @throws Exception 
     */

    public String of_getContenuto (long tkmsgmod_ex, String cdling) throws Exception {

    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;
        
        Ep_lingua ep_lingua = new Ep_lingua();
        
        setProfilo((Atk_sql) ep_lingua);

        String ls_ = "";

        tot_rec = 0;

        l_query  = "";
        l_query += " select contenuto                     \n";
        l_query += "   from pgmr.ep_msgmod_extra_p        \n";
        l_query += "  where tkmsgmod_ex = "+tkmsgmod_ex+" \n";
        l_query += "    and cdling = '"+cdling+"'         \n";
        l_query += "  order by nrposi                     \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;

        rs = pstm.executeQuery();

        while (rs!=null && rs.next() && rs.getObject(1)!=null){

            tot_rec += 1;
            ls_ += rs.getString(1);

        }
        pstm.close();
        
        String cdling_def = ep_lingua.getCdling_def();
        
        if (ls_.equals("") && !cdling.equals(cdling_def)){
            ls_ = of_getContenuto (tkmsgmod_ex, cdling_def);
        }

        ep_lingua.close();

        return ls_;

    }


    public int of_setContenuto (long tkmsgmod_ex, String contenuto, String cdling ) throws Exception {

    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        int len_rec = 900;

        // cancello tutto il contenuto

        tot_rec = 0;

        l_query  = "";
        l_query += " delete from pgmr.ep_msgmod_extra_p where tkmsgmod_ex = "+tkmsgmod_ex+" and cdling = '"+ cdling +"' \n";

        pstm = m_connection.prepareStatement(l_query);

        tot_rec = pstm.executeUpdate();

        pstm.close();


        int    li_     = 0;
        int    nrposi  = 0;
        String ls_cont = "";

        li_ = contenuto.length();

        while (li_ > 0){

            ls_cont  = "";
            nrposi  += 10;

            if (li_ > len_rec){

                ls_cont   = contenuto.substring(0, len_rec);
                contenuto = contenuto.substring(len_rec);
            }else {

                ls_cont   = contenuto;
                contenuto = "";
            }

            li_ = contenuto.length();


            l_query  = "";
            l_query += " insert into pgmr.ep_msgmod_extra_p ( \n";
            l_query += "        tkmsgmod_ex                \n";
            l_query += "      , cdling                     \n";
            l_query += "      , nrposi                     \n";
            l_query += "      , contenuto                  \n";
            l_query += "      , cdazie                     \n";
            l_query += "      , cddipa                     \n";
            l_query += "      , profil                     \n";
            l_query += "      , dtinse                     \n";
            l_query += "      , dtulag                     \n";
            l_query += "      )values (                    \n";
            l_query += "        ?,?,?,?,?                  \n";
            l_query += "      , ?,?,?,?                    \n";
            l_query += "      )                            \n";

            pstm = m_connection.prepareStatement(l_query);

            ind = 1;
            pstm.setLong     (ind, tkmsgmod_ex );    ind += 1;
            pstm.setString   (ind, cdling   );    ind += 1;
            pstm.setLong     (ind, nrposi   );    ind += 1;
            pstm.setString   (ind, ls_cont  );    ind += 1;
            pstm.setString   (ind, cdazie   );    ind += 1;
            pstm.setString   (ind, cddipa   );    ind += 1;
            pstm.setString   (ind, profil   );    ind += 1;
            pstm.setTimestamp(ind, oggi     );    ind += 1;
            pstm.setTimestamp(ind, oggi     );    ind += 1;

            tot_rec = pstm.executeUpdate();
            pstm.close();

            if (tot_rec != 1){
                throw new Exception("Err. INS pgmr.ep_msgmod_extra_p");
            }


        }



        return 1;

    }


}

