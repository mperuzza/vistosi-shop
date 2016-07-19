package com.ateikon.structure;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class Str_rep_base extends Object {

    public Str_rep_base(){
 
        super();

    }


    public     String    order_by        = "";
    public     String    autoview        = "S";
    public     String    autorun         = "N";
    public     String    targetDettaglio = "f2";
    public     boolean   parametri       = true;
    public     String    tipostampa      = "";
    public     String    cdling          = "";
    public     String    group_by        = "";//avendramin20110720

    public     String    cdtipm          = "";
    public     String    cdclas_a        = "";
    public     String    cdartipo        = "";
    public     String    cdzcom          = "";

    // Cliente

    public     String    tkclie   = "";
    public     String    cdclie_m = "";
    public     String    ragcog   = "";

    // Articolo

    public     String    cdarti   = "";
    public     String    cdartm   = "";
    public     String    dsarti   = "";

    // Agente

    public     String    cdagen   = "";
    public     String    cdagen_m = "";
    public     String    dsagen   = "";







    /***


    */

    public void copy_into ( Str_rep_base astr) throws Exception {

       astr.cdling      = this.cdling;

       astr.tkclie      = this.tkclie; 
       astr.cdclie_m    = this.cdclie_m; 
       astr.ragcog      = this.ragcog; 
    
       astr.cdarti      = this.cdarti; 
       astr.cdartm      = this.cdartm; 
       astr.dsarti      = this.dsarti; 

       astr.cdagen      = this.cdagen; 
       astr.cdagen_m    = this.cdagen_m; 
       astr.dsagen      = this.dsagen; 

    }




    /***

    */

    public int ctrlCliente (com.ateikon.common.Atk_sql atk_sql ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        this.cdclie_m = this.cdclie_m.trim().toUpperCase();
        this.ragcog   = this.ragcog.trim().toUpperCase();


        String l_query = "";
        String l_sql = "";

        l_sql  = "";
        l_sql += "   from pgmr.archclie clie                       \n";
        l_sql += "      , pgmr.archenti ente                       \n";
        l_sql += "  where clie.cdente = ente.cdente                \n";
        //l_sql += "    and clie.tkclie = clie.tkclie_sost           \n"; avendramin20080107
        l_sql += "    and clie.cdazie = '"+atk_sql.cdazie+"'       \n";

        if (!this.tkclie.equals("") ){

            l_sql += "    and clie.tkclie= '"+this.tkclie+"'    \n";
            
        }else if (!this.cdclie_m.equals("") ){
            l_sql += "    and upper(clie.cdclie_m) = '"+this.cdclie_m+"'    \n";

        }else {
            
            String ls_ragcog   = atk_sql.par_like(this.ragcog);

            l_sql += "    and upper(ente.ragcog) like '"+ls_ragcog+"'   \n";

        }


        l_query = "";
        l_query += " select count(*) \n";
        l_query += l_sql;

        tot_rec = atk_sql.sql_int(l_query);

        if (tot_rec != 1){
            return tot_rec;
        }



        l_query = "";
        l_query += " select clie.tkclie     \n";
        l_query += "      , clie.cdclie_m   \n";
        l_query += "      , ente.ragcog     \n";
        l_query += l_sql;

        rs = atk_sql.sql_query(l_query);

        if (rs!=null && rs.next()){

            if (rs.getObject(1)!=null) this.tkclie   = rs.getString(1);
            if (rs.getObject(2)!=null) this.cdclie_m = rs.getString(2);
            if (rs.getObject(3)!=null) this.ragcog   = rs.getString(3);
        }


        return 1;

    }




    /***

    */

    public int ctrlArticolo(com.ateikon.common.Atk_sql atk_sql) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

	com.ateikon.common.Mrp_arch_articoli mrp_arch_articoli =  new com.ateikon.common.Mrp_arch_articoli();

	atk_sql.setProfilo((com.ateikon.common.Atk_sql) mrp_arch_articoli);

        this.cdartm = this.cdartm.trim().toUpperCase();
        this.dsarti = this.dsarti.trim().toUpperCase();


        String l_query = "";
        String l_sql = "";

        l_sql  = "";
        l_sql += "   from pgmr.mrp_arch_articoli arti \n";

        if (!this.cdarti.equals("") ){

            l_sql += "    where arti.cdarti= '"+this.cdarti+"'    \n";
            
        }else {
            
            String ls_dsarti   = atk_sql.par_like(this.dsarti);

            l_sql += "    where upper(arti.cdartm) = '"+this.cdartm+"'    \n";
            l_sql += "       or upper("+ mrp_arch_articoli.of_descr_lingua (this.cdling, "arti", "dsarti") +") like '"+ls_dsarti+"'   \n";

        }


        l_query = "";
        l_query += " select count(*) \n";
        l_query += l_sql;

        tot_rec = atk_sql.sql_int(l_query);

        if (tot_rec != 1){
            return tot_rec;
        }



        l_query = "";
        l_query += " select arti.cdarti   \n";
        l_query += "      , arti.cdartm   \n";
        l_query += "      , "+ mrp_arch_articoli.of_descr_lingua (this.cdling, "arti", "dsarti") +" as dsarti   \n";
        l_query += l_sql;

        rs = atk_sql.sql_query(l_query);

        if (rs!=null && rs.next()){

            if (rs.getObject(1)!=null) this.cdarti = rs.getString(1);
            if (rs.getObject(2)!=null) this.cdartm = rs.getString(2);
            if (rs.getObject(3)!=null) this.dsarti = rs.getString(3);
        }

	mrp_arch_articoli.close();

        return 1;

    }




    /***

    */

    public int ctrlAgente (com.ateikon.common.Atk_sql atk_sql ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        this.cdagen_m = this.cdagen_m.trim().toUpperCase();
        this.dsagen   = this.dsagen.trim().toUpperCase();


        String l_query = "";
        String l_sql = "";

        l_sql  = "";
        l_sql += "   from pgmr.archagen agen                       \n";
        l_sql += "  where agen.cdazie = '"+atk_sql.cdazie+"'       \n";

        if (!this.cdagen.equals("") ){

            l_sql += "    and agen.cdagen = '"+this.cdagen+"'    \n";
            
        }else {
            
            String ls_dsagen   = atk_sql.par_like(this.dsagen);

            l_sql += "    and (   upper(agen.cdagen_m) = '"+this.cdagen_m+"'    \n";
            l_sql += "         or upper(agen.dsagen) like '"+ls_dsagen+"'  )  \n";

        }


        l_query = "";
        l_query += " select count(*) \n";
        l_query += l_sql;

        tot_rec = atk_sql.sql_int(l_query);

        if (tot_rec != 1){
            return tot_rec;
        }



        l_query = "";
        l_query += " select agen.cdagen     \n";
        l_query += "      , agen.cdagen_m   \n";
        l_query += "      , agen.dsagen     \n";
        l_query += l_sql;

        rs = atk_sql.sql_query(l_query);

        if (rs!=null && rs.next()){

            if (rs.getObject(1)!=null) this.cdagen   = rs.getString(1);
            if (rs.getObject(2)!=null) this.cdagen_m = rs.getString(2);
            if (rs.getObject(3)!=null) this.dsagen   = rs.getString(3);
        }


        return 1;

    }






}
