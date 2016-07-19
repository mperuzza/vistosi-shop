package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;

import com.ateikon.common.Ep_lingua_prg;

import com.ateikon.structure.Rec_ep_lingua;
import com.ateikon.structure.Rec_ep_lingua_prg;

import com.ateikon.util.StringUtils;



public class Ep_lingua extends com.ateikon.standard.Ep_lingua {


    public Ep_lingua() {

        super();

        ib_calcola_token = false;
    }






    /***


    */

    public ResultSet getAll ( ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        tot_rec = 0;

        l_query  = "";
        l_query += " select *               \n";
        l_query += "   from pgmr.ep_lingua  \n";
        l_query += "  order by posi         \n";
        
        rs = sql_query(l_query);



        return rs;

    }


    /***


    */

    public ResultSet getDropdown ( ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        tot_rec = 0;

        l_query  = "";
        l_query += " select cdling, dsling  \n";
        l_query += "   from pgmr.ep_lingua  \n";
        l_query += "  order by posi         \n";
        
        rs = sql_query(l_query);



        return rs;

    }


    /***


    */

    public String getCdling_def ( ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " select cdling          \n";
        l_query  += "   from pgmr.ep_lingua  \n";
        l_query  += "   where fgdef = 'S'    \n";
        
        String ls_cdling = sql_String(l_query);

        return ls_cdling ;

    }

    public String getDsling( String cdling) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query   = "";
        l_query  += " select dsling          \n";
        l_query  += "   from pgmr.ep_lingua  \n";
        l_query  += "   where cdling = '"+cdling+"'    \n";
        
        String ls_dsling = sql_String(l_query);

        return ls_dsling ;

    }


    /****
        getArray: ep_lingua
    **/ 

    public String[] getArray(String as_field, String fgdef) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select count(*)        \n";
        l_query  += "   from pgmr.ep_lingua  \n";
        if (!fgdef.equals("")){
            l_query  += "   where fgdef = '"+fgdef+"' \n";
        }

        tot_rec = sql_int(l_query);

        String[] arr_ = null;
        
        if (tot_rec > 0){
                    
                arr_ = new String[tot_rec];
        
                l_query   = "";
                l_query  += " select "+as_field+"    \n";
                l_query  += "   from pgmr.ep_lingua  \n";
                if (!fgdef.equals("")){
                    l_query  += "   where fgdef = '"+fgdef+"' \n";
                }
                l_query  += "  order by fgdef desc   \n";   // --- prima la lingua di default
                l_query  += "         , posi         \n";
                l_query  += "         , cdling       \n";
        
                pstm = m_connection.prepareStatement(l_query);
        
                rs = pstm.executeQuery();
        
                ind = 0;
                while(rs!=null && rs.next()){
                    arr_[ind] = "";
                    if (rs.getObject(1)!=null)  arr_[ind] = rs.getString(1);    

                    ind += 1;
                }
                pstm.close();

        }
        
        

        return arr_;

    }








    /***


    */

    public int init ( ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        Rec_ep_lingua lstr_arr = new Rec_ep_lingua();

        lstr_arr.arr_cdling    = new String[]{"I", "E", "D", "F", "S"};
        lstr_arr.arr_fgdef     = new String[]{"S", "N", "N", "N", "N"};
        lstr_arr.arr_cdiso639  = new String[]{"it","en","de","fr","es"};
        lstr_arr.arr_dsling    = new String[]{"Italiano"    ,"Inglese" ,"Tedesco" ,"Francese"   ,"Spagnolo"};
        lstr_arr.arr_dsling_en = new String[]{"Italian"     ,"English" ,"German"  ,"French"     ,"Spanish"};
        lstr_arr.arr_dsling_de = new String[]{"Italienisch" ,"Englisch","Deutsch" ,"Französisch","Spanisch"};
        lstr_arr.arr_dsling_fr = new String[]{"Italien"     ,"Anglais" ,"Allemand","Français"   ,"Espagnol"};
        lstr_arr.arr_dsling_es = new String[]{"Italiano"    ,"Inglés"  ,"Alemán"  ,"Francés"    ,"Español"};
        
        
        for(int i=0; lstr_arr.arr_cdling!=null && i<lstr_arr.arr_cdling.length; i++){
        
            if (iskey(lstr_arr.arr_cdling[i])) continue;


            Rec_ep_lingua lstr = new Rec_ep_lingua();

            lstr.cdling    = lstr_arr.arr_cdling    [i];
            lstr.fgdef     = lstr_arr.arr_fgdef     [i];
            lstr.cdiso639  = lstr_arr.arr_cdiso639  [i];
            lstr.dsling    = lstr_arr.arr_dsling    [i];
            lstr.dsling_en = lstr_arr.arr_dsling_en [i];
            lstr.dsling_de = lstr_arr.arr_dsling_de [i];
            lstr.dsling_fr = lstr_arr.arr_dsling_fr [i];
            lstr.dsling_es = lstr_arr.arr_dsling_es [i];
            lstr.posi      = (long)(i+1);

            int li_tot_rec = execute(lstr); 

            if (li_tot_rec  == 1){
                tot_rec += 1;
            }else {
                throw new Exception("Errore INS/UPD Lingua");
            }


        }


        return tot_rec ;

    }


    

    /***


    */

    public int chg_key (String cdlilng, String cdlilng_new ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        if (iskey(cdlilng_new)) return -1;

        l_query  = "";
        l_query += " update pgmr.ep_lingua             \n";
        l_query += "    set cdling = '"+cdlilng_new+"' \n";
        l_query += "      , profil = '"+profil+"'      \n";
        l_query += "      , dtulag =  "+sysdate+"      \n";
        l_query += "  where cdling = '"+cdlilng+"'     \n";

        tot_rec = sql_update(l_query);
                       
        return tot_rec ;

    }



    public boolean iskey (String cdlilng ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query  = "";
        l_query += " select count(*)               \n";
        l_query += "   from pgmr.ep_lingua         \n";
        l_query += "  where cdling = '"+cdlilng+"' \n";

        int is_ = sql_int(l_query);

        if (is_ > 0) return true;

        return false;

    }







}

