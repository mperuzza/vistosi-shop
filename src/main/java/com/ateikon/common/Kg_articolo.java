package com.ateikon.common;




import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import com.ateikon.util.StringUtils;
import com.ateikon.structure.Str_atkimg_parametri;




public class Kg_articolo extends Atk_sql {

    public Kg_articolo() {
        
        super();
    }




    public int     countArticoli  ( String cdmarca
                                  , String cdgptipm
                                  , String cdsttcat
                                  , String f_search
                                  , String f_barcode
                                  , String f_cdartm
                                  , String f_importazione
                                                        ) throws Exception {
    
        //.
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;
        
        Costanti_comm costanti_comm = new Costanti_comm();
        setProfilo((Atk_sql) costanti_comm);
        String ep_tipo_sttcat = costanti_comm.getCostvalue("ep.tipo_sttcat");


        f_search = par_like(f_search );
        f_barcode = f_barcode.trim();


        l_query   = "";
		l_query  += " select count(1) as tot_rec               \n";
		l_query  += "   from pgmr.mrp_arch_articoli   art      \n";
		l_query  += "  where art.fgabil   = 'S'                \n";
		l_query  += "    and art.fgfoto   = 'S'                \n";
		l_query  += "    and art.cdstato_disp = 'N'            \n";
		if (!cdmarca.equals("")){                              
		l_query  += "    and art.cdmarca  = '"+cdmarca+"'      \n";
        }                                                      
        if (!cdgptipm.equals("")){                             
		l_query  += "    and art.cdgptipm = '"+cdgptipm+"'     \n";
        }                                                      
        if (!cdsttcat.equals("")){                               
            if (ep_tipo_sttcat.equals("kg_tipomapr")){
		        l_query  += "  and art.cdtipa = '"+cdsttcat+"'       \n";
            }else {
		        l_query  += "  and art.cdtipmc = '"+cdsttcat+"'     \n";
            }
        }                                                      
        if (!f_barcode.equals("")){                        
		    l_query  += "  and art.cdbarcode = '"+f_barcode+"' \n";
        }      
        if (f_importazione.equals("S")){
		    l_query  += "  and art.fgestero = 'S'              \n";
        }else if (f_importazione.equals("N")){                 
		    l_query  += "  and art.fgestero = 'N'              \n";
        }     
        if (!f_cdartm.equals("")){
		    l_query  += "  and art.cdartm = '"+f_cdartm+"'     \n";
        }

        if (!f_search.equals("")){
            
            f_search = par_like(f_search.trim());

		    l_query  += " and (   upper(art.dsarti    ) like '"+f_search+"'  \n";
		    l_query  += "      or upper(art.cdartm    ) like '"+f_search+"'  \n";
		    l_query  += "      or upper(art.cdartprod ) like '"+f_search+"'  \n";
		    l_query  += "      )                                          \n";
            
        }



        pstm = m_connection.prepareStatement( l_query );

        ind = 1;
        // pstm.setString(ind, cdazie); ind += 1;


        rs = pstm.executeQuery();

        if (rs !=null && rs.next() && rs.getObject("tot_rec")!=null){
            
            tot_rec = rs.getInt("tot_rec");
        }
        pstm.close();
        pstm = null;

        return tot_rec;

    }




    /***

        Articoli a catalogo

            Dtulacq: in import dati viene valorizzata se la giac. è 
                     nulla, e l'articolo non è stato riordinato da 
                     due anni

    **/



    
    public ResultSet getArticoli  ( String cdmarca
                                  , String cdgptipm
                                  , String cdsttcat
                                  , String f_search
                                  , String f_barcode
                                  , String f_cdartm
                                  , String f_importazione
                                  , String order_by
                                  , String cdling
                                                        ) throws Exception {
                                                            
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        Costanti_comm costanti_comm = new Costanti_comm();
        setProfilo((Atk_sql) costanti_comm);
        String ep_tipo_sttcat = costanti_comm.getCostvalue("ep.tipo_sttcat");


        f_search = par_like(f_search );
        f_barcode = f_barcode.trim();

        if (order_by.equals("")) order_by = "d.dsgptipm, dssttcat, b.dsmarca, art.cdartm ";

        String ls_suffisso = of_lingua("kg_articolo",cdling);
        String ls_suf_gcat = of_lingua("kg_gptipomapr",cdling);
        
        String ls_suff_cat = "";
        if (ep_tipo_sttcat.equals("kg_tipomapr")){
            ls_suff_cat = of_lingua("kg_tipomapr",cdling);
        }else {
            ls_suff_cat = of_lingua("kg_tipomapr_cat",cdling);
        }


        l_query   = "";
		l_query  += " select art.cdarti                        \n";
		l_query  += "      , art.cdartm                        \n";
        if (cdling.equals("I")) {
            l_query  += "      , art.dsarti                        \n";
        } else {
            l_query  += "      , a.ds" + ls_suffisso + " as dsarti  \n";
        }
		l_query  += "      , art.cdbarcode                                  \n";
		l_query  += "      , art.cdmarca                                    \n";
		l_query  += "      , art.przbase    as przbaseven                   \n";
		l_query  += "      , art.cdartprod                                  \n";
		l_query  += "      , art.nrpeso_l                                   \n";
		l_query  += "      , art.vlunlt                                     \n";
		l_query  += "      , a.imballo                                      \n";
		l_query  += "      , a.ppc                                          \n";
		l_query  += "      , a.ppm                                          \n";
		l_query  += "      , art.fgestero                                   \n";
		l_query  += "      , b.dsmarca                                      \n";
		l_query  += "      , b.cdmarca_m                                    \n";
        if (ep_tipo_sttcat.equals("kg_tipomapr")){
    		l_query  += "      , c.cdtipm                     as cdsttcat   \n";
    		l_query  += "      , c.cdtipm_m                   as cdsttcat_m \n";
    		l_query  += "      , c.dstipm" + ls_suff_cat + "  as dssttcat   \n";
        }else {
    		l_query  += "      , e.cdtipmc                    as cdsttcat   \n";
    		l_query  += "      , e.cdtipmc_m                  as cdsttcat_m \n";
    		l_query  += "      , e.dstipmc" + ls_suff_cat + " as dssttcat   \n";
        }
		l_query  += "      , d.cdgptipm                      \n";
		l_query  += "      , d.cdgptipm_m                    \n";
		l_query  += "      , d.dsgptipm" + ls_suf_gcat + "  as dsgptipm   \n";
		l_query  += "      , f.dsfamiglia                                   \n";
		l_query  += "      , g.dsnota                                       \n";
		l_query  += "      , a.qtaoa                           \n";
		l_query  += "      , a.qtaov                           \n";

        if (is_oracle){
    
    		l_query  += "   from pgmr.mrp_arch_articoli   art      \n";
    		l_query  += "      , pgmr.kg_articolo         a        \n";
    		l_query  += "      , pgmr.mis_marca           b        \n";
            if (ep_tipo_sttcat.equals("kg_tipomapr")){
                l_query  += "      , pgmr.kg_tipomapr         c        \n";
            }else {
    		    l_query  += "      , pgmr.kg_tipomapr_cat     e        \n";
            }
    		l_query  += "      , pgmr.kg_gptipomapr       d        \n";
    		l_query  += "      , pgmr.kg_famiglia         f        \n";
    		l_query  += "      , pgmr.kg_nota             g        \n";
    		l_query  += "  where art.fgabil = 'S'                  \n";
    		l_query  += "    and art.fgfoto = 'S'                  \n";
    		l_query  += "    and art.cdstato_disp = 'N'            \n";
    		l_query  += "    and art.cdarti   = a.cdarti   (+)     \n";
    		l_query  += "    and art.cdazie   = a.cdazie   (+)     \n";
    		l_query  += "    and art.cdmarca  = b.cdmarca  (+)     \n";
            if (ep_tipo_sttcat.equals("kg_tipomapr")){
		        l_query  += "    and art.cdtipa   = c.cdtipm   (+)     \n";
            }else {
                l_query  += "    and art.cdtipmc  = e.cdtipmc  (+)     \n";
            }
    		l_query  += "    and art.cdgptipm = d.cdgptipm (+)     \n";
            l_query  += "    and art.cdfamiglia = f.cdfamiglia (+) \n";
            l_query  += "    and art.cdnota     = g.cdnota(+)      \n";
        

        }else if (is_sybase){
            
            l_query += "  FROM {oj               pgmr.mrp_arch_articoli art                                \n";
            l_query += "        LEFT OUTER JOIN  pgmr.kg_articolo       a ON  art.cdarti    = a.cdarti     \n";
            l_query += "                                                  AND art.cdazie    = a.cdazie     \n";
            l_query += "        LEFT OUTER JOIN  pgmr.mis_marca         b ON art.cdmarca    = b.cdmarca    \n";
            if (ep_tipo_sttcat.equals("kg_tipomapr")){
                l_query += "        LEFT OUTER JOIN  pgmr.kg_tipomapr       c ON art.cdtipa     = c.cdtipm     \n";
            }else {
                l_query += "        LEFT OUTER JOIN  pgmr.kg_tipomapr_cat   e ON art.cdtipmc    = e.cdtipmc    \n";
            } 
            l_query += "        LEFT OUTER JOIN  pgmr.kg_gptipomapr     d ON art.cdgptipm   = d.cdgptipm   \n";
            l_query += "        LEFT OUTER JOIN  pgmr.kg_famiglia       f ON art.cdfamiglia = f.cdfamiglia \n";
            l_query += "        LEFT OUTER JOIN  pgmr.kg_nota           g ON art.cdnota     = g.cdnota     \n";
            l_query += "        }                                                                          \n";
            l_query += " where art.fgabil = 'S'                \n";
            l_query += "   and art.fgfoto = 'S'                \n";
            l_query += "   and art.cdstato_disp = 'N'          \n";

        }else {
            throw new Exception("TIPO DB NON PREVISTO");
        }


		if (!cdmarca.equals("")){
		    l_query  += "    and art.cdmarca = '"+cdmarca+"'   \n";
        }                                                      
        if (!cdgptipm.equals("")){                             
		    l_query  += "    and art.cdgptipm = '"+cdgptipm+"' \n";
        }       
        
        if (!cdsttcat.equals("")){                               
            if (ep_tipo_sttcat.equals("kg_tipomapr")){
		        l_query  += "  and art.cdtipa = '"+cdsttcat+"'       \n";
            }else {
		        l_query  += "  and art.cdtipmc = '"+cdsttcat+"'     \n";
            }
        }                                                      
        if (!f_barcode.equals("")){                            
		    l_query  += "  and art.cdbarcode = '"+f_barcode+"' \n";
        }                                                      
        if (f_importazione.equals("S")){
		    l_query  += "  and art.fgestero = 'S'              \n";
        }else if (f_importazione.equals("N")){
		    l_query  += "  and art.fgestero = 'N'              \n";
        }
        if (!f_cdartm.equals("")){
		    l_query  += "  and art.cdartm = '"+f_cdartm+"'     \n";
        }
        if (!f_search.equals("")){
            
    
            f_search = par_like(f_search.trim());

		    l_query  += " and (   upper(art.dsarti   ) like '"+f_search+"'  \n";
		    l_query  += "      or upper(art.cdartm   ) like '"+f_search+"'  \n";
		    l_query  += "      or upper(art.cdartprod) like '"+f_search+"'  \n";
		    l_query  += "      )                                          \n";
            
        }

		l_query  += " order by "+ order_by +" \n";



        pstm = setQuery_ric( l_query );


        ind = 1;
        

        rs = pstm.executeQuery();



        return rs;


    }




    public ResultSet getArticolo  ( String cdarti ) throws Exception {
    
        //.
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;
        
        Costanti_comm costanti_comm = new Costanti_comm();
        setProfilo((Atk_sql) costanti_comm);
        String ep_tipo_sttcat = costanti_comm.getCostvalue("ep.tipo_sttcat");
        

        l_query   = "";
		l_query  += " select art.cdarti                        \n";
		l_query  += "      , art.cdartm                        \n";
		l_query  += "      , art.dsarti                        \n";
		l_query  += "      , art.cdbarcode                     \n";
		l_query  += "      , art.cdmarca                       \n";
		l_query  += "      , art.przbase    as przbaseven      \n";
		l_query  += "      , art.cdartprod                     \n";
		l_query  += "      , art.nrpeso_l                      \n";
		l_query  += "      , art.vlunlt                        \n";
		l_query  += "      , art.cdrepa                        \n";
		l_query  += "      , art.cdunim_1  as cdunim           \n";
		l_query  += "      , art.cdunim_2                      \n";
		l_query  += "      , art.cdunim_2                      \n";
		l_query  += "      , a.imballo                         \n";
		l_query  += "      , a.ppc                             \n";
		l_query  += "      , a.ppm                             \n";
		l_query  += "      , a.qtaoa                           \n";
		l_query  += "      , a.qtaov                           \n";
		l_query  += "      , art.fgestero                      \n";
		l_query  += "      , b.dsmarca                         \n";
		l_query  += "      , b.cdmarca_m                       \n";
        if (ep_tipo_sttcat.equals("kg_tipomapr")){
    		l_query  += "      , c.cdtipm       as cdsttcat    \n";
    		l_query  += "      , c.cdtipm_m     as cdsttcat_m  \n";
    		l_query  += "      , c.dstipm       as dssttcat    \n";
        }else {                                                
    		l_query  += "      , e.cdtipmc      as cdsttcat    \n";
    		l_query  += "      , e.cdtipmc_m    as cdsttcat_m  \n";
    		l_query  += "      , e.dstipmc      as dssttcat    \n";
        }
		l_query  += "      , d.cdgptipm                        \n";
		l_query  += "      , d.dsgptipm                        \n";
		l_query  += "      , d.cdgptipm_m                      \n";
		l_query  += "      , f.dsfamiglia                      \n";
		l_query  += "      , g.dsnota                          \n";

        if (is_oracle){
    
    		l_query  += "   from pgmr.mrp_arch_articoli   art      \n";
    		l_query  += "      , pgmr.kg_articolo         a        \n";
    		l_query  += "      , pgmr.mis_marca           b        \n";
            if (ep_tipo_sttcat.equals("kg_tipomapr")){
    		    l_query  += "      , pgmr.kg_tipomapr         c        \n";
            }else {
    		    l_query  += "      , pgmr.kg_tipomapr_cat     e        \n";
            }
    		l_query  += "      , pgmr.kg_gptipomapr       d        \n";
    		l_query  += "      , pgmr.kg_famiglia         f        \n";
    		l_query  += "      , pgmr.kg_nota             g        \n";
            l_query  += "  where art.cdazie = '"+cdazie+"'         \n";
    		l_query  += "    and art.cdarti   = a.cdarti   (+)     \n";
    		l_query  += "    and art.cdazie   = a.cdazie   (+)     \n";
    		l_query  += "    and art.cdmarca  = b.cdmarca  (+)     \n";
            if (ep_tipo_sttcat.equals("kg_tipomapr")){
                l_query  += "    and art.cdtipa   = c.cdtipm   (+)     \n";
            }else {
                l_query  += "    and art.cdtipmc  = e.cdtipmc  (+)     \n";
            }
    		l_query  += "    and art.cdgptipm = d.cdgptipm (+)     \n";
            l_query  += "    and art.cdfamiglia = f.cdfamiglia (+) \n";
            l_query  += "    and art.cdnota     = g.cdnota     (+) \n";
            l_query  += "    and art.cdarti = '"+cdarti+"'         \n";

        }else if (is_sybase){
            
            l_query += "  FROM {oj               pgmr.mrp_arch_articoli art                                 \n";
            l_query += "        LEFT OUTER JOIN  pgmr.kg_articolo       a ON  art.cdarti   = a.cdarti       \n";
            l_query += "                                                  AND art.cdazie   = a.cdazie       \n";
            l_query += "        LEFT OUTER JOIN  pgmr.mis_marca         b ON art.cdmarca   = b.cdmarca      \n";
            if (ep_tipo_sttcat.equals("kg_tipomapr")){
            l_query += "        LEFT OUTER JOIN  pgmr.kg_tipomapr       c ON art.cdtipa    = c.cdtipm       \n";
            }else {
            l_query += "        LEFT OUTER JOIN  pgmr.kg_tipomapr_cat   e ON art.cdtipmc   = e.cdtipmc      \n";
            }
            l_query += "        LEFT OUTER JOIN  pgmr.kg_gptipomapr     d ON art.cdgptipm  = d.cdgptipm     \n";
            l_query += "        LEFT OUTER JOIN  pgmr.kg_famiglia       f ON art.cdfamiglia = f.cdfamiglia  \n";
            l_query += "        LEFT OUTER JOIN  pgmr.kg_nota           g ON art.cdnota     = g.cdnota      \n";
            l_query += "        }                                                                           \n";
            l_query += " where art.cdazie = '"+cdazie+"' \n";
            l_query += "   and art.cdarti = '"+cdarti+"' \n";

        }else {
            throw new Exception("TIPO DB NON PREVISTO");
        }

        pstm = setQuery( l_query );

        ind = 1;

        rs = pstm.executeQuery();

        return rs;

    }





    public ResultSet getArticoli(Str_atkimg_parametri str_parametri) throws Exception {
                                                            
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        Costanti_comm costanti_comm = new Costanti_comm();
        setProfilo((Atk_sql) costanti_comm);
        String ep_tipo_sttcat = costanti_comm.getCostvalue("ep.tipo_sttcat");


        String ls_suffisso = of_lingua("kg_articolo", str_parametri.cdling);
        String ls_suf_gcat = of_lingua("kg_gptipomapr", str_parametri.cdling);

        String ls_suff_cat = "";
        if (ep_tipo_sttcat.equals("kg_tipomapr")){
            ls_suff_cat = of_lingua("kg_tipomapr",str_parametri.cdling);
        }else {
            ls_suff_cat = of_lingua("kg_tipomapr_cat",str_parametri.cdling);
        }



        l_query   = "";
		l_query  += " select art.cdarti                        \n";
		l_query  += "      , art.cdartm                        \n";
                if (str_parametri.cdling.equals("I")) {
                    l_query  += "      , art.dsarti                        \n";
                } else {
                    l_query  += "      , a.ds" + ls_suffisso + " as dsarti  \n";
                }
		l_query  += "      , art.cdbarcode                     \n";
		l_query  += "      , art.cdmarca                       \n";
		l_query  += "      , art.przbase    as przbaseven      \n";
		l_query  += "      , art.cdartprod                     \n";
		l_query  += "      , art.nrpeso_l                      \n";
		l_query  += "      , art.vlunlt                        \n";
		l_query  += "      , a.imballo                         \n";
		l_query  += "      , a.ppc                             \n";
		l_query  += "      , a.ppm                             \n";
		l_query  += "      , art.fgestero                      \n";
		l_query  += "      , b.dsmarca                         \n";
		l_query  += "      , b.cdmarca_m                       \n";
        if (ep_tipo_sttcat.equals("kg_tipomapr")){
    		l_query  += "      , c.cdtipm                     as cdsttcat   \n";
    		l_query  += "      , c.cdtipm_m                   as cdsttcat_m \n";
    		l_query  += "      , c.dstipm" + ls_suff_cat + "  as dssttcat   \n";
        }else {
    		l_query  += "      , e.cdtipmc                    as cdsttcat   \n";
    		l_query  += "      , e.cdtipmc_m                  as cdsttcat_m \n";
    		l_query  += "      , e.dstipmc" + ls_suff_cat + " as dssttcat   \n";
        }
		l_query  += "      , d.cdgptipm                                     \n";
		l_query  += "      , d.dsgptipm" + ls_suf_gcat + "  as dsgptipm     \n";
		l_query  += "      , d.cdgptipm_m                                   \n";
		l_query  += "      , f.dsfamiglia                                   \n";
		l_query  += "      , g.dsnota                                       \n";

        if (is_oracle){
    
    		l_query  += "   from pgmr.mrp_arch_articoli   art      \n";
    		l_query  += "      , pgmr.kg_articolo         a        \n";
    		l_query  += "      , pgmr.mis_marca           b        \n";
    		if (ep_tipo_sttcat.equals("kg_tipomapr")){
    		    l_query  += "      , pgmr.kg_tipomapr         c        \n";
            }else {
    		    l_query  += "      , pgmr.kg_tipomapr_cat     e        \n";
            }
            
    		l_query  += "      , pgmr.kg_gptipomapr       d        \n";
    		l_query  += "      , pgmr.kg_famiglia         f        \n";
    		l_query  += "      , pgmr.kg_nota             g        \n";
    		l_query  += "  where art.fgabil = 'S'                  \n";
    		l_query  += "    and art.cdarti   = a.cdarti   (+)     \n";
    		l_query  += "    and art.cdazie   = a.cdazie   (+)     \n";
    		l_query  += "    and art.cdmarca  = b.cdmarca  (+)     \n";
    		if (ep_tipo_sttcat.equals("kg_tipomapr")){
                l_query  += "    and art.cdtipa   = c.cdtipm   (+)     \n";
            }else {
                l_query  += "    and art.cdtipmc  = e.cdtipmc  (+)     \n";
            }
    		l_query  += "    and art.cdgptipm = d.cdgptipm (+)     \n";
            l_query  += "    and art.cdfamiglia = f.cdfamiglia (+) \n";
            l_query  += "    and art.cdnota     = g.cdnota(+)      \n";
        

        }else if (is_sybase){
            
            l_query += "  FROM {oj               pgmr.mrp_arch_articoli art                                          \n";
            l_query += "        LEFT OUTER JOIN  pgmr.kg_articolo       a ON  art.cdarti    = a.cdarti     \n";
            l_query += "                                                  and art.cdazie    = a.cdazie     \n";
            l_query += "        LEFT OUTER JOIN  pgmr.mis_marca         b ON art.cdmarca    = b.cdmarca    \n";
    		if (ep_tipo_sttcat.equals("kg_tipomapr")){
            l_query += "        LEFT OUTER JOIN  pgmr.kg_tipomapr       c ON art.cdtipa     = c.cdtipm     \n";
            }else {
            l_query += "        LEFT OUTER JOIN  pgmr.kg_tipomapr_cat   e ON art.cdtipmc    = e.cdtipmc    \n";
            }
            l_query += "        LEFT OUTER JOIN  pgmr.kg_gptipomapr     d ON art.cdgptipm   = d.cdgptipm   \n";
            l_query += "        LEFT OUTER JOIN  pgmr.kg_famiglia       f ON art.cdfamiglia = f.cdfamiglia \n";
            l_query += "        LEFT OUTER JOIN  pgmr.kg_nota           g ON art.cdnota     = g.cdnota     \n";
            l_query += "        }                                                                          \n";
            l_query += " where art.fgabil = 'S'                \n";

        }else {
            throw new Exception("TIPO DB NON PREVISTO");
        }
        
        if (str_parametri.cdtipmc != null && !str_parametri.cdtipmc.equals("")){
            l_query  += "  and a.cdtipmc = '" + str_parametri.cdtipmc + "' \n";
        }
        if (str_parametri.fgfoto){
            l_query  += "  and a.cdarti IN (select foto.cdarti from pgmr.kg_articolo_foto foto where  foto.cdarti = a.cdarti) \n";
        }
                
        l_query  += " order by art.cdartm    \n";

        

        pstm = setQuery_ric( l_query );


        ind = 1;
        

        rs = pstm.executeQuery();


        return rs;


    }










    /***


    */




    public int     countLayout_arti(String cdlayout_m ) throws Exception {
    
        //.
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;
        

        l_query   = "";
		l_query  += " select count(1) as tot_rec                    \n";
		l_query  += "   from pgmr.cat_layout          lay           \n";
		l_query  += "      , pgmr.cat_layout_arti     lay_art       \n";
		l_query  += "      , pgmr.mrp_arch_articoli   art           \n";
		l_query  += "  where lay.cdlayout_m     = '"+cdlayout_m+"'  \n";
		l_query  += "    and lay_art.id_layout  = lay.id            \n";
		l_query  += "    and lay_art.cdarti     = art.cdarti        \n";

        pstm = m_connection.prepareStatement( l_query );

        ind = 1;

        rs = pstm.executeQuery();

        if (rs !=null && rs.next() && rs.getObject("tot_rec")!=null){
            
            tot_rec = rs.getInt("tot_rec");
        }
        pstm.close();
        pstm = null;

        return tot_rec;

    }




    
    public ResultSet getLayout_arti(String cdlayout_m, String cdling) throws Exception {
                                                            
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        Costanti_comm costanti_comm = new Costanti_comm();
        setProfilo((Atk_sql) costanti_comm);
        String ep_tipo_sttcat = costanti_comm.getCostvalue("ep.tipo_sttcat");

        String ls_suffisso = of_lingua("kg_articolo", cdling);
        String ls_suf_gcat = of_lingua("kg_gptipomapr",cdling);
        
        String ls_suff_cat = "";
        if (ep_tipo_sttcat.equals("kg_tipomapr")){
            ls_suff_cat = of_lingua("kg_tipomapr",cdling);
        }else {
            ls_suff_cat = of_lingua("kg_tipomapr_cat",cdling);
        }


        l_query   = "";
		l_query  += " select art.cdarti                        \n";
		l_query  += "      , art.cdartm                        \n";
        if (cdling.equals("I")) {
            l_query  += "      , art.dsarti                        \n";
        } else {
            l_query  += "      , a.ds" + ls_suffisso + " as dsarti  \n";
        }
		l_query  += "      , art.cdbarcode                     \n";
		l_query  += "      , art.cdmarca                       \n";
		l_query  += "      , art.przbase    as przbaseven      \n";
		l_query  += "      , art.cdartprod                     \n";
		l_query  += "      , art.nrpeso_l                      \n";
		l_query  += "      , art.vlunlt                        \n";
		l_query  += "      , a.imballo                         \n";
		l_query  += "      , a.ppc                             \n";
		l_query  += "      , a.ppm                             \n";
		l_query  += "      , art.fgestero                      \n";
		l_query  += "      , b.dsmarca                         \n";
		l_query  += "      , b.cdmarca_m                       \n";
        if (ep_tipo_sttcat.equals("kg_tipomapr")){
    		l_query  += "      , c.cdtipm                     as cdsttcat   \n";
    		l_query  += "      , c.cdtipm_m                   as cdsttcat_m \n";
    		l_query  += "      , c.dstipm" + ls_suff_cat + "  as dssttcat   \n";
        }else {
    		l_query  += "      , e.cdtipmc                    as cdsttcat   \n";
    		l_query  += "      , e.cdtipmc_m                  as cdsttcat_m \n";
    		l_query  += "      , e.dstipmc" + ls_suff_cat + " as dssttcat   \n";
        }
		l_query  += "      , d.cdgptipm                        \n";
		l_query  += "      , d.dsgptipm" + ls_suf_gcat + "  as dsgptipm                        \n";
		l_query  += "      , d.cdgptipm_m                      \n";
		l_query  += "      , f.dsfamiglia                      \n";
		l_query  += "      , g.dsnota                          \n";
		l_query  += "      , lay_art.img                       \n";

        if (is_oracle){
    
    		l_query  += "   from pgmr.cat_layout          lay           \n";
    		l_query  += "      , pgmr.cat_layout_arti     lay_art       \n";
    		l_query  += "      , pgmr.mrp_arch_articoli   art           \n";
    		l_query  += "      , pgmr.kg_articolo         a             \n";
    		l_query  += "      , pgmr.mis_marca           b             \n";
    		if (ep_tipo_sttcat.equals("kg_tipomapr")){
    		    l_query  += "      , pgmr.kg_tipomapr         c         \n";
            }else {
    		    l_query  += "      , pgmr.kg_tipomapr_cat     e         \n";
            }
    		l_query  += "      , pgmr.kg_gptipomapr       d             \n";
    		l_query  += "      , pgmr.kg_famiglia         f             \n";
    		l_query  += "      , pgmr.kg_nota             g             \n";
    		l_query  += "  where lay.cdlayout_m     = '"+cdlayout_m+"'  \n";
    		l_query  += "    and lay_art.id_layout  = lay.id            \n";
    		l_query  += "    and lay_art.cdarti     = art.cdarti        \n";
    		l_query  += "    and art.fgabil = 'S'                       \n";
    		l_query  += "    and art.cdarti   = a.cdarti   (+)          \n";
    		l_query  += "    and art.cdazie   = a.cdazie   (+)          \n";
    		l_query  += "    and art.cdmarca  = b.cdmarca  (+)          \n";
    		if (ep_tipo_sttcat.equals("kg_tipomapr")){
                l_query  += "    and art.cdtipa   = c.cdtipm   (+)      \n";
            }else {
                l_query  += "    and art.cdtipmc  = e.cdtipmc  (+)      \n";
            }
    		l_query  += "    and art.cdgptipm = d.cdgptipm (+)          \n";
            l_query  += "    and art.cdfamiglia = f.cdfamiglia (+)      \n";
            l_query  += "    and art.cdnota     = g.cdnota(+)           \n";
        

        }else if (is_sybase){
    		l_query += "  from pgmr.cat_layout          lay                                                \n";
    		l_query += "     , pgmr.cat_layout_arti     lay_art                                            \n";
            l_query += "     , {oj               pgmr.mrp_arch_articoli art                                \n";
            l_query += "        LEFT OUTER JOIN  pgmr.kg_articolo       a ON  art.cdarti    = a.cdarti     \n";
            l_query += "                                                  and art.cdazie    = a.cdazie     \n";
            l_query += "        LEFT OUTER JOIN  pgmr.mis_marca         b ON art.cdmarca    = b.cdmarca    \n";
    		if (ep_tipo_sttcat.equals("kg_tipomapr")){
                l_query += "        LEFT OUTER JOIN  pgmr.kg_tipomapr       c ON art.cdtipa     = c.cdtipm     \n";
            }else {
                l_query += "        LEFT OUTER JOIN  pgmr.kg_tipomapr_cat   e ON art.cdtipmc    = e.cdtipmc    \n";
            }
            l_query += "        LEFT OUTER JOIN  pgmr.kg_gptipomapr     d ON art.cdgptipm   = d.cdgptipm   \n";
            l_query += "        LEFT OUTER JOIN  pgmr.kg_famiglia       f ON art.cdfamiglia = f.cdfamiglia \n";
            l_query += "        LEFT OUTER JOIN  pgmr.kg_nota           g ON art.cdnota     = g.cdnota     \n";
            l_query += "        }                                                                          \n";
    		l_query += "  where lay.cdlayout_m     = '"+cdlayout_m+"'  \n";
    		l_query += "    and lay_art.id_layout  = lay.id            \n";
    		l_query += "    and lay_art.cdarti     = art.cdarti        \n";
    		l_query += "    and art.fgabil = 'S'                       \n";

        }else {
            throw new Exception("TIPO DB NON PREVISTO");
        }
		l_query  += " order by lay_art.nrposi                       \n";
		l_query  += "        , art.cdartm                           \n";

        
        pstm = setQuery_ric( l_query );

        ind = 1;
        

        rs = pstm.executeQuery();


        return rs;


    }




    public String getCdartm( String cdarti ) throws Exception {
    
        //.
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;
        
        l_query   = "";
		l_query  += " select cdartm                 \n";
		l_query  += "   from pgmr.mrp_arch_articoli \n";
		l_query  += "  where cdarti = '"+cdarti+"'  \n";

        String ls_ = sql_String( l_query );

        return ls_;

    }


    public ResultSet getColori(String cdarti, String cdling) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;


        l_query   = "";
        l_query  += " select c.cdcolint   \n";
        l_query  += "      , c.dscolint   \n";
        l_query  += "   from pgmr.kg_artcol a       \n";
        l_query  += "      , pgmr.kg_colori c       \n";
        l_query  += " where a.cdcolint = c.cdcolint              \n";
        l_query  += "   and a.cdarti = ? \n";
        l_query  += "order by c.dscolint \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString(ind, cdarti); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }




}

