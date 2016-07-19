package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_tabkey;


public class Cat_layout_arti extends com.ateikon.standard.Cat_layout_arti {


    public Cat_layout_arti() {

        super();
    }



    /***


    */

    public ResultSet getArticolo ( String cdarti ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        l_query   = "";
		l_query  += " select art.cdartm                    \n";
		l_query  += "      , art.dsarti                    \n";
		l_query  += "      , art.cdartprod                 \n";
		l_query  += "   from pgmr.mrp_arch_articoli    art \n";
		l_query  += "  where art.cdarti = '"+cdarti+"'     \n";

        pstm = setQuery( l_query );

        rs = pstm.executeQuery();

        return rs;
    }



    /***


    */

    public ResultSet getCdartm ( String cdartm ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        l_query   = "";
		l_query  += " select art.cdartm                    \n";
		l_query  += "      , art.dsarti                    \n";
		l_query  += "      , art.cdartprod                 \n";
		l_query  += "      , art.cdarti                    \n";
		l_query  += "      , art.fgabil                    \n";
		l_query  += "   from pgmr.mrp_arch_articoli    art \n";
		l_query  += "  where art.cdartm = ?                \n";
		l_query  += "    and art.cdazie = ?                \n";

        pstm = setQuery( l_query );


        ind = 1;
        pstm.setString(ind, cdartm);        ind += 1;
        pstm.setString(ind, cdazie);        ind += 1;

        rs = pstm.executeQuery();

        return rs;
    }





    /***

        
    */

    public int delete( long id ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        l_query   = "";
		l_query  += " delete                       \n";
		l_query  += "   from pgmr.cat_layout_arti  \n";
		l_query  += "  where id = '"+id+"'         \n";

        tot_rec = sql_update(l_query);

        return tot_rec;
    }




    /***


    */

    public int resetNrposi ( long id_layout ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;
        PreparedStatement pstm_box = null;
        ResultSet         rs_box   = null;

        tot_rec = 0;

        l_query  = "";
        l_query += " update pgmr.cat_layout_arti \n";
        l_query += "    set nrposi = ?           \n";
        l_query += "  where id = ?               \n";

        pstm_box = m_connection.prepareStatement(l_query);



        l_query  = "";
        l_query += " select id                        \n";
        l_query += "   from pgmr.cat_layout_arti      \n";
        l_query += "  where id_layout = "+id_layout+" \n";
        l_query += "  order by nrposi                 \n";
        l_query += "         , id                     \n";

        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        long id = 0;
        long nrposi = 0;

        while (rs!=null && rs.next()){
            

            id = rs.getLong(1);
            nrposi += 10;

            pstm_box.setLong(1, nrposi);
            pstm_box.setLong(2, id    );

            tot_rec = pstm_box.executeUpdate();

            if(tot_rec != 1){
                throw new Exception("Errore UPD cat_layout_arti ");
            }

        }
        pstm.close();


        return 1 ;

    }








    /***


    */

    public ResultSet getFoto_articolo (long id_layout, String cdarti ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        l_query  = "";
        l_query += " select *                           \n";
        l_query += "   from pgmr.cat_layout_arti        \n";
        l_query += "  where id_layout = "+id_layout+"   \n";
        l_query += "    and cdarti    = '"+cdarti+"'    \n";

        rs = sql_query(l_query);

        return rs;

    }



    /***


    */

    public boolean exists (long id_layout, String cdarti ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        boolean lb_ = false ;

        
        tot_rec = 0;

        l_query  = "";
        l_query += " select count(*)                    \n";
        l_query += "   from pgmr.cat_layout_arti        \n";
        l_query += "  where id_layout = "+id_layout+"   \n";
        l_query += "    and cdarti    = '"+cdarti+"'    \n";

        tot_rec = sql_int(l_query);

        if (tot_rec >0) lb_ = true;


        return lb_;

    }


    /***


    */

    public int setImg( long id, String as_img) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;
        PreparedStatement pstm_box = null;
        ResultSet         rs_box   = null;

        tot_rec = 0;

        l_query  = "";
        l_query += " update pgmr.cat_layout_arti \n";
        l_query += "    set img = ?              \n";
        l_query += "      , profil = ?           \n";
        l_query += "      , dtulag = ?           \n";
        l_query += "  where id = ?               \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;
        pstm.setString   (ind, as_img); ind += 1;
        pstm.setString   (ind, profil); ind += 1;
        pstm.setTimestamp(ind, of_getTimestamp()); ind += 1;
        pstm.setLong     (ind, id    );ind += 1;

        tot_rec = pstm.executeUpdate();
        pstm.close();

        if(tot_rec != 1){
            throw new Exception("Errore UPD cat_layout_arti ");
        }

        return 1 ;

    }


}

