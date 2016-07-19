package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;



public class Bolla_posi extends Atk_sql {

    public Bolla_posi() {
        
        super();
    }



    public ResultSet getDettaglio( long tkboll
                                                        ) throws Exception {
                                                            
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        return getDettaglio( tkboll, 0);

    }



    public ResultSet getDettaglio( long tkboll
                                 , long tkposi_b
                                                        ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query  = "";
        l_query += "   select tkfatt               \n";
        l_query += "     from pgmr.bolla_test      \n";
        l_query += "    where tkboll = "+tkboll+"  \n";

        long tkfatt = sql_long(l_query);
       
        String tipodoc = "B";
        if (tkfatt > 0 && tkfatt == tkboll ){
            tipodoc = "F";
        }
       

        l_query   = "";
        l_query  += "   SELECT bolt.tkboll                         \n";
        l_query  += "        , bolt.dtboll                         \n";
        l_query  += "        , bolp.tkposi_b                       \n";
        l_query  += "        , bolp.cdazie                         \n";
        l_query  += "        , bolp.nrriga_b                       \n";
        l_query  += "        , bolp.nrriga_f                       \n";
        l_query  += "        , bolp.cdarti                         \n";
        l_query  += "        , bolp.dssart                         \n";
        l_query  += "        , bolp.nrquan                         \n";
        l_query  += "        , bolp.coimpo                         \n";
        l_query  += "        , bolp.sconto_1                       \n";
        l_query  += "        , bolp.sconto_2                       \n";
        l_query  += "        , bolp.sconto_3                       \n";
        l_query  += "        , bolp.sconto_4                       \n";
        l_query  += "        , bolp.impuninetto                    \n";
        l_query  += "        , bolp.importonettoriga               \n";
        l_query  += "        , arti.cdartm                         \n";
        l_query  += "        , arti.dsarti                         \n";
        l_query  += "     FROM pgmr.bolla_test          bolt       \n";
        l_query  += "        , pgmr.archclie            clie       \n";
        l_query  += "        , {oj pgmr.bolla_posi      bolp       \n";
        l_query  += "          left outer join pgmr.mrp_arch_articoli arti on bolp.cdarti = arti.cdarti  \n";
        l_query  += "                   }                   \n";
        if (tipodoc.equals("B")){
            l_query  += "    WHERE bolt.tkboll   = bolp.tkboll  \n";
        }else {
            l_query  += "    WHERE bolt.tkboll   = bolp.tkfatt  \n";
        }
		l_query  += "      and clie.tkclie = bolt.tkclie      \n";
		l_query  += "      and clie.cdazie = bolt.cdazie      \n";
        if (!s_cdcapoarea.equals("")){
		    l_query  += " and clie.cdagen in ("+par_ca_agenti(s_cdcapoarea)+") \n";
        }else if (!s_cdagen.equals("")){
            l_query  += " and clie.cdagen = '"+s_cdagen+"' \n";
        }else if (!s_tkclie.equals("")){
            l_query  += " and clie.tkclie = '"+s_tkclie+"' \n";
        }

        if (tkboll > 0){
        l_query  += "      and bolt.tkboll = ?       \n";
        }
        if (tkposi_b > 0){
        l_query  += "      and bolp.tkposi_b = ?     \n";
        }
        if (tipodoc.equals("B")){
            l_query  += " ORDER BY bolp.nrriga_b     \n";
        }else {
            l_query  += " ORDER BY bolp.nrriga_f     \n";
        }


      
        pstm = setQuery( l_query ) ;
        
        ind = 1;

        if (tkboll > 0){
            pstm.setLong(ind, tkboll); ind +=1;
        }
        if (tkposi_b > 0){
            pstm.setLong(ind, tkposi_b); ind +=1;
        }
        
        rs = pstm.executeQuery();

        return rs;


    }


    public ResultSet getTkboll(long tkboll) throws Exception {
        
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        return getDettaglio(tkboll);

    }










    public long tkposi_b = 0;



}
