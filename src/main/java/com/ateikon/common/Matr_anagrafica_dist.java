package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.common.Costanti_comm;
import com.ateikon.function.F_tabkey;

import com.ateikon.structure.Str_dec_barcode;

public class Matr_anagrafica_dist extends com.ateikon.standard.Matr_anagrafica_dist {

    public Matr_anagrafica_dist() {

        super();
    }



    public ResultSet getTkmatricola(long tkmatricola)  throws Exception{
        
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;


        Costanti_comm costanti_comm = new Costanti_comm();

        setProfilo((Atk_sql)costanti_comm);


        String ls_lwm_ordlistatav = costanti_comm.getCostvalue("lwm_ordlistatav");


        l_query   = "";
        l_query  += " select *                          \n";
        l_query  += "   from pgmr.matr_anagrafica_dist  \n";
        l_query  += "  where tkmatricola = ?            \n";
        l_query += "     and (ncolli - ncollicons) > 0  \n";
        l_query += "     and fgsaldo = 'N'              \n";
        if (ls_lwm_ordlistatav.equals("1")){
            l_query  += "  order by nrriga desc         \n";
        }else {
            l_query  += "  order by dimena, nrriga      \n";
        }

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setLong      (ind, tkmatricola); ind += 1;

        rs = pstm.executeQuery();

        return rs;
    }



    public long count(long tkmatricola)  throws Exception{
        
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        long ll_ = 0;


        l_query   = "";
        l_query  += " select count(1)                   \n";
        l_query  += "   from pgmr.matr_anagrafica_dist  \n";
        l_query  += "  where tkmatricola = ?            \n";
        l_query += "     and (ncolli - ncollicons) > 0  \n";
        l_query += "     and fgsaldo = 'N'              \n";

        pstm = m_connection.prepareStatement( l_query  );

        ind = 1;
        pstm.setLong      (ind, tkmatricola); ind += 1;

        rs = pstm.executeQuery();

        if (rs!=null && rs.next() && rs.getObject(1)!=null){
          
            ll_ = rs.getLong(1);
        }

        pstm.close();

        return ll_;
    }




    public int of_ctrl_pacco(Str_dec_barcode astr) throws Exception{
        


        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        message = "";

        if (!astr.fpaccodist.equals("S")){
            
            astr.tkmatrdist = 0;
            return 1;
        }



        Costanti_comm costanti_comm = new Costanti_comm();

        setProfilo((Atk_sql)costanti_comm);

        String ls_lwm_ordlistatav = costanti_comm.getCostvalue("lwm_ordlistatav");



        long ll_tkmatrdist = 0;

        long   ll_ncolli = 0;
        long   ll_ncollicons = 0;
        long   ll_ncolli_res = 0;
        double ldb_qtatot  = 0;
        double ldb_qtacons = 0;
        double ldb_qta_res = 0;

        double ldb_dimen = 0;

        // controllo in base al calcolo se esiste una distinta per 
        // questo articolo


        l_query  = "";                                  
        l_query += " select tkmatrdist                 \n";
        l_query += "      , ncolli                     \n";
        l_query += "      , ncollicons                 \n";
        l_query += "      , qtatot                     \n";
        l_query += "      , qtacons                    \n";
        l_query += "      , dimena                     \n";
        l_query += "      , dimenb                     \n";
        l_query += "      , dimenc                     \n";
        l_query += "   from pgmr.matr_anagrafica_dist  \n";
        l_query += "  where tkmatricola = ?            \n";
        l_query += "    and cdarti = ?                 \n";
        l_query += "    and (ncolli - ncollicons) > 0  \n";
        l_query += "    and fgsaldo = 'N'              \n";

        if (astr.fdimena.equals("S") && !astr.fmisvar.equals("A"))  l_query += "    and dimena = ? \n";
        if (astr.fdimenb.equals("S") && !astr.fmisvar.equals("B"))  l_query += "    and dimenb = ? \n";
        if (astr.fdimenc.equals("S") && !astr.fmisvar.equals("B"))  l_query += "    and dimenc = ? \n";

        if (ls_lwm_ordlistatav.equals("1")){
            l_query  += "  order by nrriga desc             \n";
        }else {
            l_query  += "  order by dimena, dimenb, dimenc  \n";
        }
        
                                                      
        pstm = m_connection.prepareStatement( l_query ) ;

        ind = 1;
        pstm.setLong  (ind, astr.tkmatricola );  ind += 1;
        pstm.setString(ind, astr.cdarti);  ind += 1;

        if (astr.fdimena.equals("S")) pstm.setDouble(ind, astr.dimena); ind += 1;
        if (astr.fdimenb.equals("S")) pstm.setDouble(ind, astr.dimenb); ind += 1;
        if (astr.fdimenc.equals("S")) pstm.setDouble(ind, astr.dimenc); ind += 1;
        

        rs = pstm.executeQuery();


        if  (rs!=null && rs.next() ){
           
            do {

                ll_tkmatrdist    = rs.getLong("tkmatrdist");
    
                if (rs.getObject("ncolli"    )!=null) ll_ncolli = rs.getLong("ncolli"); 
                if (rs.getObject("ncollicons")!=null) ll_ncollicons = rs.getLong("ncollicons"); 
    
                if (rs.getObject("qtatot" )!=null) ldb_qtatot = rs.getDouble("qtatot"); 
                if (rs.getObject("qtacons")!=null) ldb_qtacons = rs.getDouble("qtacons"); 
    
                ll_ncolli_res = ll_ncolli  - ll_ncollicons;
                ldb_qta_res   = ldb_qtatot - ldb_qtacons;
    
                if (ll_ncolli_res > 0 && ldb_qta_res > 0){
                    
                    if (ll_ncolli_res > astr.ncolli){
                        
                        message = "N.Colli Eccessivo";
    
                    }
                }else {
                    
                    message = "";
                    break;
                }

            }while (rs!=null && rs.next() );


            
        }else {
            
            ll_tkmatrdist = 0;
            message = "Tavola NON trovata.";
            return 0;
        }

        pstm.close();
        pstm = null;

        if (ll_tkmatrdist > 0){
            
            astr.tkmatrdist = ll_tkmatrdist;
        }else {
            
            if (message.equals("")){
                message = "Tavola NON trovata.";
            }
            return 0;
        }


        return 1;

    }



    public String message = "";


}

