package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;



public class Ord_positito extends Atk_sql {

    public Ord_positito() {
        
        super();
    }



    public ResultSet getDettaglio( long tkordi
                                                        ) throws Exception {
                                                            
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        return getDettaglio ( tkordi, 0);

    }



    public ResultSet getDettaglio( long tkordi
                                 , long tkposi
                                                        ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;


        l_query   = "";



        l_query  += "   SELECT ordt.tkordi                         \n";
        l_query  += "        , ordt.numord                         \n";
        l_query  += "        , ordt.dtordi                         \n";
        l_query  += "        , ordp.tkposi                         \n";
        l_query  += "        , ordp.cdazie                         \n";
        l_query  += "        , ordp.nrposi_v                       \n";
        l_query  += "        , ordp.cdarti                         \n";
        l_query  += "        , ordp.dtcons                         \n";
        l_query  += "        , ordp.dssart                         \n";
        l_query  += "        , ordp.qtatot                         \n";
        l_query  += "        , ordp.qtacons                        \n";
        l_query  += "        , ordp.qtcons_s                       \n";
        l_query  += "        , ordp.impuni                         \n";
        l_query  += "        , ordp.impuninetto                    \n";
        l_query  += "        , ordp.importonettoriga               \n";
        l_query  += "        , ordp.fgsaldo                        \n";
        l_query  += "        , arti.cdartm                         \n";
        l_query  += "        , arti.dsarti                         \n";
        l_query  += "     FROM pgmr.ord_test            ordt                     \n";
        l_query  += "        , pgmr.archclie            clie                     \n";
        l_query  += "        , {oj pgmr.ord_positito    ordp                     \n";
        l_query  += "          left outer join pgmr.mrp_arch_articoli arti on ordp.cdarti = arti.cdarti  \n";
        l_query  += "                   }                     \n";
        l_query  += "    WHERE ordt.tkordi   = ordp.tkordi    \n";
		l_query  += "      and clie.tkclie = ordt.tkclie      \n";
		l_query  += "      and clie.cdazie = ordt.cdazie      \n";

        if (!s_cdcapoarea.equals("")){
		    l_query  += " and clie.cdagen in ("+par_ca_agenti(s_cdcapoarea)+") \n";
        }else if (!s_cdagen.equals("")){
            l_query  += " and clie.cdagen = '"+s_cdagen+"' \n";
        }else if (!s_tkclie.equals("")){
            l_query  += " and clie.tkclie = '"+s_tkclie+"' \n";
        }

        if (tkordi > 0){
        l_query  += "      and ordt.tkordi = ?                                   \n";
        }
        if (tkposi > 0){
        l_query  += "      and ordp.tkposi = ?                                   \n";
        }
        l_query  += " ORDER BY ordp.nrposi_v                                     \n";

      
        pstm = setQuery( l_query ) ;
        
        ind = 1;

        if (tkordi > 0){
            pstm.setLong(ind, tkordi); ind +=1;
        }
        if (tkposi > 0){
            pstm.setLong(ind, tkposi); ind +=1;
        }
        
        rs = pstm.executeQuery();

        return rs;


    }


    public ResultSet getTkordi(long tkordi) throws Exception {
        
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        return getDettaglio(tkordi);


    }










    public long tkposi = 0;



}
