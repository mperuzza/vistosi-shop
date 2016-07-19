package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Atk_msgfile extends com.ateikon.standard.Atk_msgfile {


    public Atk_msgfile() {

        super();

//        FGALLEGATO_SI = com.ateikon.afs.Afs_allegati.FGALLEGATO_SI;
//        FGALLEGATO_NO = com.ateikon.afs.Afs_allegati.FGALLEGATO_NO;
//        FGALLEGATO_MANUALE = com.ateikon.afs.Afs_allegati.FGALLEGATO_MANUALE;
//        FGALLEGATO_FORZA_NO = com.ateikon.afs.Afs_allegati.FGALLEGATO_FORZA_NO;


    }




    /***
     *
     * @param tkselecta
     * @return 1 OK; <> 1 ERROR
     * @throws Exception
     *
     * VIENE resettato il Flag Controllo Lotto
     * Viene resettato il flag Allegati ad eccezione delle righe in manuale
     * o dove � stato forzato No allegati
     */

    public int resetFgctrl(long tkmsg, long posi) throws Exception {
        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        l_query = "";
        l_query += " update pgmr.atk_msgfile                      \n";
        l_query += "    set fgctrl = "+FGCTRL_NON_CONTROLLATO+"   \n";
        l_query += "      , profil = '"+profil+"'                 \n";
        l_query += "      , dtulag = "+sysdate+"                  \n";
        l_query += "  where tkmsg = "+tkmsg+"                     \n";

        tot_rec = sql_update(l_query);



        l_query = "";
        l_query += " update pgmr.atk_msgfile                    \n";
        l_query += "    set fgallegato = '"+FGALLEGATO_NO+"'    \n";
        l_query += "      , profil = '"+profil+"'               \n";
        l_query += "      , dtulag = "+sysdate+"                \n";
        l_query += "  where tkmsg = "+tkmsg+"                   \n";
        l_query += "    and fgallegato not in ('"+FGALLEGATO_MANUALE+"', '"+FGALLEGATO_FORZA_NO+"') \n";

        tot_rec = sql_update(l_query);


        l_query = "";
        l_query += " update pgmr.atk_messaggio                  \n";
        l_query += "    set fgctrl = "+FGCTRL_NON_CONTROLLATO+" \n";
        l_query += "      , profil = '"+profil+"'               \n";
        l_query += "      , dtulag = "+sysdate+"                \n";
        l_query += "  where tkmsg = "+tkmsg+ "                  \n";

        tot_rec = sql_update(l_query);


        return 1;
    }



    /*
     *
     *
     * */


    public int setFgallegato(long tkmsg, long posi, String fgallegato) throws Exception {
        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        l_query = "";
        l_query += " update pgmr.atk_msgfile              \n";
        l_query += "    set fgallegato = '"+fgallegato+"' \n";
        l_query += "      , profil = '"+profil+"'         \n";
        l_query += "      , dtulag = "+sysdate+"          \n";
        l_query += "  where tkmsg = "+tkmsg+"             \n";
        l_query += "    and posi = "+posi+"               \n";

        tot_rec = sql_update(l_query);

        return tot_rec;
    }




    /***
     *
     * Ricordarsi setFgctrl_t
     */


    public int setFgctrl(long tkmsg, long posi, long fgctrl) throws Exception {
        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        l_query = "";
        l_query += " update pgmr.atk_msgfile              \n";
        l_query += "    set fgctrl = "+fgctrl+"           \n";
        l_query += "      , profil = '"+profil+"'         \n";
        l_query += "      , dtulag = "+sysdate+"          \n";
        l_query += "  where tkmsg = "+tkmsg+"             \n";
        l_query += "    and posi  = "+posi+"              \n";

        tot_rec = sql_update(l_query);

        if(tot_rec !=1 ){
            throw new Exception("Errore UPD atk_selecta_p ");
        }

        return 1;
    }

    public int setFgctrl_t(long tkmsg) throws Exception {
        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;


        l_query = "";
        l_query += " select min(fgctrl)       \n";
        l_query += "   from pgmr.atk_msgfile  \n";
        l_query += "  where tkmsg = "+tkmsg+" \n";

        long fgctrl = sql_long(l_query);



        l_query = "";
        l_query += " update pgmr.atk_messaggio    \n";
        l_query += "    set fgctrl = "+fgctrl+"   \n";
        l_query += "      , profil = '"+profil+"' \n";
        l_query += "      , dtulag = "+sysdate+"  \n";
        l_query += "  where tkmsg  = "+tkmsg+"    \n";

        tot_rec = sql_update(l_query);


        if(tot_rec != 1 ){
            throw new Exception("Errore UPD atk_selecta ");
        }

        return 1;
    }




    public static int FGCTRL_ERROR = -1;
    public static int FGCTRL_ERROR_ALLEGATI = -2;
    public static int FGCTRL_NON_CONTROLLATO = 0;
    public static int FGCTRL_OK = 1;

    public String FGALLEGATO_SI = "";
    public String FGALLEGATO_NO = "";
    public String FGALLEGATO_MANUALE  = "";
    public String FGALLEGATO_FORZA_NO = "";


}

