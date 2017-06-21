package com.ateikon.common;




import java.sql.ResultSet;
import java.sql.PreparedStatement;



import com.ateikon.structure.Str_controllo_articolo;
import com.ateikon.structure.Str_controllo_doc_res_arti;
import com.ateikon.structure.Str_risorsa_anticipo;
import java.io.File;
import java.util.ArrayList;
import java.util.List;



public class Mrp_arch_articoli extends Atk_sql {

    public Mrp_arch_articoli() {

        super();

        tbl_primaria = "MRP_ARCH_ARTICOLI";
        
    }


    public ResultSet getKey(String cdarti) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet          rs = null; //avendramin20080102

        l_query   = "";
        l_query  += " select *                      \n";
        l_query  += "   from pgmr.mrp_arch_articoli \n";
        l_query  += "  where cdarti= ?              \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString(ind, cdarti); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }


    
    public ResultSet getKey(String cdarti, String cdling) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet          rs = null; //avendramin20080102

        l_query   = "";
        l_query  += " select *                      \n";
        l_query  += "      , "+ of_descr_lingua (cdling, "", "dsarti") +" as dsarti_lin   \n";
        l_query  += "   from pgmr.mrp_arch_articoli \n";
        l_query  += "  where cdarti= ?              \n";

        pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString(ind, cdarti); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }


    
    
    public ResultSet getKey_m(String cdartm, String cdling) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet          rs = null; //avendramin20080102


        l_query   = "";
       l_query  += " select *                                                   \n";
       l_query  += "      , "+ of_descr_lingua (cdling, "", "dsarti") +" as dsarti_lin   \n";
       l_query  += "   from pgmr.mrp_arch_articoli                              \n";
       l_query  += "  where cdartm = ?                                          \n";
       l_query  += "    and cdazie = ?                                          \n";
       l_query  += "  order by cdartm                                           \n";

       pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString(ind, cdartm); ind += 1;
        pstm.setString(ind, cdazie); ind += 1;

        rs = pstm.executeQuery();

        return rs;


    }

    public String getDs_m(String cdartm, String cdling) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet          rs = null; //avendramin20080102


       l_query   = "";
      	l_query  += " select "+ of_descr_lingua (cdling, "", "dsarti") +" as dsarti   \n";
      	l_query  += "   from pgmr.mrp_arch_articoli                              \n";
      	l_query  += "  where cdartm = '"+ cdartm +"'                             \n";
      	l_query  += "    and cdazie = '"+ cdazie +"'                             \n";

      	return sql_String(l_query);

    }


    public ResultSet search( Str_controllo_articolo astr
                           , boolean    is_count
                           , String     order_by
                                                    ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        Unimisura unimisura = new Unimisura();
        
        setProfilo((Atk_sql) unimisura);

        String ls_f_search = par_like(astr.f_search);
        String ls_cdartm = par_like(astr.cdartm);
        String ls_dsarti = par_like(astr.dsarti);
        String ls_tkclie = astr.tkclie;

        if (order_by.equals("")) order_by = "arti.dsarti ";

        if (is_count) order_by = "";

        if (is_count){

            l_query   = "";
            l_query  += "   select count(1)  \n";
        }else {

            l_query   = "";
            l_query  += " select arti.cdartm                                                                     \n";
            l_query  += "      , arti.cdarti                                                                     \n";
            l_query  += "      , "+ of_descr_lingua (astr.cdling, "arti", "dsarti") +" as dsarti                         \n";
            l_query  += "      , arti.cdalias                                                                    \n";
            l_query  += "      , arti.cdunim_1                                                                   \n";
            l_query  += "      , unim.cdunim_m                                                                   \n";
            l_query  += "      , "+ unimisura.of_descr_lingua(astr.cdling, "unim", "dsunim") +" as dsunim                                                                     \n";
            l_query  += "      , ( select sum (giac.qtagia)                                                      \n";
            l_query  += "            from pgmr.mrp_file_giacenza giac                                            \n";
            l_query  += "           where giac.cdarti = arti.cdarti   ) as tot_qtagia                            \n";
            l_query  += "      , ( select sum (giac.qtaov)                                                       \n";
            l_query  += "            from pgmr.mrp_file_giacenza giac                                            \n";
            l_query  += "           where giac.cdarti = arti.cdarti   ) as tot_qtaov                             \n";
            l_query  += "      , ( select sum (giac.qtaprel)                                                     \n";
            l_query  += "            from pgmr.mrp_file_giacenza giac                                            \n";
            l_query  += "           where giac.cdarti = arti.cdarti   ) as tot_qtaprel                           \n";
            l_query  += "      , ( select sum (giac.qtaoa)                                                       \n";
            l_query  += "            from pgmr.mrp_file_giacenza giac                                            \n";
            l_query  += "           where giac.cdarti = arti.cdarti   ) as tot_qtaoa                             \n";
            l_query  += "      , ( select sum (giac.qtafat)                                                      \n";
            l_query  += "            from pgmr.mrp_file_giacenza giac                                            \n";
            l_query  += "           where giac.cdarti = arti.cdarti   ) as tot_qtafat                            \n";
            l_query  += "      , ( select sum (giac.qtafat_t)                                                    \n";
            l_query  += "            from pgmr.mrp_file_giacenza giac                                            \n";
            l_query  += "           where giac.cdarti = arti.cdarti   ) as tot_qtafat_t                          \n";
            l_query  += "      , ( select sum (giac.qtadisp)                                                     \n";
            l_query  += "            from pgmr.mrp_file_giacenza giac                                            \n";
            l_query  += "           where giac.cdarti = arti.cdarti   ) as tot_qtadisp                           \n";
            l_query  += "      , ( select min (giac.dtprdisp)                                                    \n";
            l_query  += "            from pgmr.mrp_file_giacenza giac                                            \n";
            l_query  += "           where giac.cdarti = arti.cdarti   ) as min_dtprdisp                          \n";
        }

        if (is_oracle){
            l_query  += "   from pgmr.mrp_arch_articoli arti   \n";
            l_query  += "      , pgmr.unimisura         unim  \n";
            l_query  += "  where arti.cdazie = '"+cdazie+"'    \n";
            l_query  += "    and arti.cdunim_1 = unim.cdunim(+)  \n";
            //l_query  += "    and arti.fgweb  = 'S'                \n"; //avendramin20101217
        } else if (is_sybase || is_postgresql) {
            l_query  += "   from pgmr.mrp_arch_articoli arti   \n";
            l_query  += "         left outer join  pgmr.unimisura  unim on  arti.cdunim_1 = unim.cdunim   \n";
            l_query  += "  where arti.cdazie = '"+cdazie+"'         \n";
            //l_query  += "    and arti.fgweb  = 'S'                \n"; //avendramin20101217
        }

        //avendramin20101217 - I
        if (   !s_cdutente.equals("")
            || !s_cdispe.equals("")
            || s_fgadmin.equals("S")){

          //No filtro fgweb
        } else {
          l_query  += "    and arti.fgweb  = 'S'                \n"; 
        }
        //avendramin20101217 - F

        if (!s_tkclie.equals("")){
            l_query  += "    and arti.cdclas_a in (select in_artf.cdclas_a                             \n";
            l_query  += "                            from pgmr.vist_filtro_articoli in_artf            \n";
            l_query  += "                               , pgmr.archclie             in_clie            \n";
            l_query  += "                           where in_clie.tkclie   = '"+ s_tkclie +"'          \n";
            l_query  += "                             and ( in_artf.tkclie = in_clie.tkclie            \n";
            l_query  += "                                  or in_artf.cdlist = in_clie.cdlist )        \n";
            l_query  += "                                                                          )   \n";

        }
        
        if (!ls_tkclie.equals("")){
            l_query  += "    and arti.cdclas_a in (select in_artf.cdclas_a                             \n";
            l_query  += "                            from pgmr.vist_filtro_articoli in_artf            \n";
            l_query  += "                               , pgmr.archclie             in_clie            \n";
            l_query  += "                           where in_clie.tkclie   = '"+ ls_tkclie +"'          \n";
            l_query  += "                             and ( in_artf.tkclie = in_clie.tkclie            \n";
            l_query  += "                                  or in_artf.cdlist = in_clie.cdlist )        \n";
            l_query  += "                                                                          )   \n";

        }


        if (   !s_cdcapoarea.equals("")
            || !s_cdagen.equals("")
            ){

            l_query  += "    and arti.cdclas_a in (select distinct(in_artf.cdclas_a)                   \n";
            l_query  += "                            from pgmr.vist_filtro_articoli in_artf )          \n";

        }

        if (!ls_cdartm.equals("") || !ls_dsarti.equals(""))   {
            if (!ls_cdartm.equals("")){
                l_query  += "  and arti.cdartm like '"+ls_cdartm+"'  \n";
            }
            if (!ls_dsarti.equals("")){
                l_query  += "  and upper("+ of_descr_lingua (astr.cdling, "arti", "dsarti") +") like '"+ls_dsarti+"'  \n";
            }
        } else if (!astr.cdarti.equals(""))   {
            l_query  += " and arti.cdarti = '"+astr.cdarti+"'        \n";
        }

        if (!ls_f_search.equals("")) {
            l_query  += "   and (  upper("+ of_descr_lingua (astr.cdling, "arti", "dsarti") +") like '"+ls_f_search+"'   \n";
            l_query  += "       or arti.cdartm like '"+ls_f_search+"' ) \n";
        }
        if (!astr.cdrepa.equals("")) {
            l_query  += "   and arti.cdrepa = '"+astr.cdrepa+"' \n";
        }                
        if (!astr.cdstato.equals("")) {
            l_query  += "   and arti.cdstato = '"+astr.cdstato+"' \n";
        }
        if (!astr.cdtipm.equals("")) {
            l_query  += "   and arti.cdtipa = '"+astr.cdtipm+"' \n";
        }
        if (!astr.cdartipo.equals("")) {
            l_query  += "   and arti.cdartipo = '"+astr.cdartipo+"' \n";
        }
        if (!astr.cdvisttp.equals("")) {
            l_query  += "   and arti.cdvisttp = '"+astr.cdvisttp+"' \n";
        }
        if (!astr.cdvistccol.equals("")) {
            l_query  += "   and arti.cdvistccol = '"+astr.cdvistccol+"' \n";
        }
        if (!astr.dsvistccol.equals("")) {
            l_query  += "   and arti.cdvistccol in ( "+ par_vist_cp_collezione(astr.dsvistccol) +")   \n";
        }

        //avendramin20100430 - I
        if (astr.fgweb.equals("S")) {
           // Non visualizzo gli articoli di reparti non abilitati
           l_query  += "    and arti.cdrepa in (select in_repa.cdrepa                                 \n";
           l_query  += "                          from pgmr.mis_reparto in_repa                       \n";
           l_query  += "                         where in_repa.fgweb  = 'S'                           \n";
           l_query  += "                                                                          )   \n";
        }
        //avendramin20100430 - I
        
        if (astr.arr_cdclas_a.length > 0){
            l_query  += "    and arti.cdclas_a in (                             \n";
            for (int i = 0; i < astr.arr_cdclas_a.length; i++) {
                String cdclas_a = astr.arr_cdclas_a[i];
                if (i > 0){
                    l_query  += ", ";
                }
                l_query  += "'"+ cdclas_a +"'\n";
            }
            l_query  += "                         )                              \n";
        }

        if (!order_by.equals("")){
            l_query  += "   order by "+order_by+"  \n";
        }


        //System.out.println(">> \n"+ l_query + "\n <<");

         
        pstm = setQuery_ric( l_query );


        ind = 1;

        rs = pstm.executeQuery();

        
        unimisura.close();


        return rs;


    }
    
    public ResultSet of_getRisorseModelli ( Str_controllo_doc_res_arti     astr
                                          , boolean    is_count
                                          , String     order_by) throws Exception {
      int ind = 0;
      int tot_rec = 0;
      PreparedStatement pstm = null;
      ResultSet rs = null;
      
      String ls_dsarti = par_like(astr.dsarti);
      

      if (order_by.equals("")) order_by = "varti.cdvistfam, varti.cdvisttp, varti.cdvistv1, varti.cdvistv2, varti.cdvistv3, varti.cdvistelet, varti.vist_filedis, varti.vist_scheda_pdf ";

      if (is_count) order_by = "";

      if (is_count){

          l_query   = "";
            if (is_oracle){
                throw new Exception("DB non supportato");
            } else if (is_sybase) {
                l_query  += "   select count(distinct(varti.cdvistfam || varti.cdvisttp || varti.cdvistv1 || varti.cdvistv2 || varti.cdvistv3 || varti.cdvistelet || varti.vist_filedis || varti.vist_scheda_pdf))  \n";
            } else if (is_postgresql) {
                l_query  += "   select count(distinct(concat(varti.cdvistfam , varti.cdvisttp , varti.cdvistv1 , varti.cdvistv2 , varti.cdvistv3 , varti.cdvistelet , varti.vist_filedis , varti.vist_scheda_pdf)))  \n";
            }

          
      } else {

          l_query   = "";
          l_query  += "   select varti.cdvistfam                                                                                            \n";
          l_query  += "        , varti.cdvisttp                                                                                             \n";
          l_query  += "        , varti.cdvistv1                                                                                             \n";
          l_query  += "        , varti.cdvistv2                                                                                             \n";
          l_query  += "        , varti.cdvistv3                                                                                             \n";
          l_query  += "        , varti.cdvistelet                                                                                           \n";
          l_query  += "        , varti.cdclas_a                                                                                             \n";
          l_query  += "        , varti.vist_filedis                                                                                         \n";
          l_query  += "        , varti.vist_scheda_pdf                                                                                      \n";
          l_query  += "        , "+ of_descr_lingua ("vist_famiglia"         , astr.cdling, "vfam", "dsvistfam"     ) +" as dsvistfam      \n";
          l_query  += "        , "+ of_descr_lingua ("vist_var1"             , astr.cdling, "var1", "dsextvistv1"   ) +" as dsextvistv1    \n";
          l_query  += "        , "+ of_descr_lingua ("vist_var2"             , astr.cdling, "var2", "dsextvistv2"   ) +" as dsextvistv2    \n";
          l_query  += "        , "+ of_descr_lingua ("vist_var3"             , astr.cdling, "var3", "dsextvistv3"   ) +" as dsextvistv3    \n";
          l_query  += "        , "+ of_descr_lingua ("vist_elettrificazioni" , astr.cdling, "vele", "dsextvistelet" ) +" as dsextvistelet  \n";
          l_query  += "        , (case when varti.cdclas_a in ('UL', 'ULL', 'LOU') then 'U' else 'E' end) as fg_eur_usa                     \n";

      }

      if (is_oracle){
        throw new Exception("DB non supportato");
      } else if (is_sybase || is_postgresql) {
     
        l_query  += "     from pgmr.mrp_arch_articoli arti                                                          \n";
        l_query  += "        , pgmr.vist_articoli     varti                                                          \n";
        l_query  += "           left outer join  pgmr.vist_famiglia         vfam on varti.cdvistfam   = vfam.cdvistfam   \n";
        l_query  += "           left outer join  pgmr.vist_var1             var1 on varti.cdvistv1    = var1.cdvistv1    \n";
        l_query  += "           left outer join  pgmr.vist_var2             var2 on varti.cdvistv2    = var2.cdvistv2    \n";
        l_query  += "           left outer join  pgmr.vist_var3             var3 on varti.cdvistv3    = var3.cdvistv3    \n";
        l_query  += "           left outer join  pgmr.vist_elettrificazioni vele on varti.cdvistelet  = vele.cdvistelet  \n";
        l_query  += "        , pgmr.mis_reparto          repa                                                           \n";
        l_query  += "    where varti.cdarti = arti.cdarti                                                                \n";
        l_query  += "      and varti.cdrepa = repa.cdrepa                                                                \n";
        l_query  += "      and varti.cdvistfam is not null                                                               \n";
        l_query  += "      and varti.cdvisttp is not null                                                                \n";
        l_query  += "      and repa.cdrepa not in ('010', '018')                                                        \n"; //CABLATO
        l_query  += "      and varti.fgweb  = 'S'                                                                        \n"; 
        l_query  += "      and repa.fgweb  = 'S'                                                                        \n"; 
        
      } else {
        throw new Exception("DB non supportato");
      }

      if (!ls_dsarti.equals("")){
        l_query  += "      and varti.dsarti like '"+ ls_dsarti +"'                                                           \n"; 
      }
      if (!astr.cdvisttp.equals("")){
        l_query  += "      and varti.cdvisttp = '"+ astr.cdvisttp +"'                                                        \n"; 
      }
      if (!astr.cdvistfam.equals("")){
        l_query  += "      and varti.cdvistfam = '"+ astr.cdvistfam +"'                                                      \n"; 
      }
      if (astr.fgscarta_sp_con_v3.equals("S")){
        l_query  += "      and (varti.cdvisttp <> 'SP' or varti.cdvistv3 is null)                                                      \n"; // CABLATO
      }
      if (astr.fg_eur_usa.equals("E")){
        l_query  += "      and varti.cdclas_a NOT in ('UL', 'ULL', 'LOU')                                              \n"; // CABLATO
      } else  if (astr.fg_eur_usa.equals("U")){
        l_query  += "      and varti.cdclas_a in ('UL', 'ULL', 'LOU')                                                  \n"; // CABLATO
      }
//        System.out.println("astr.cdvistv1 "+ astr.cdvistv1);
//        System.out.println("astr.cdvistv2 "+ astr.cdvistv2);
//        System.out.println("astr.cdvistv3 "+ astr.cdvistv3);
      if (!astr.cdvistv1.equals("")){
        l_query  += "      and varti.cdvistv1 = '"+ astr.cdvistv1 +"'                                                        \n"; 
      }
      if (!astr.cdvistv2.equals("")){
        l_query  += "      and varti.cdvistv2 = '"+ astr.cdvistv2 +"'                                                        \n"; 
      }
      if (!astr.cdvistv3.equals("")){
        l_query  += "      and varti.cdvistv3 = '"+ astr.cdvistv3 +"'                                                        \n"; 
      }



      if (!is_count){
        l_query  += " group by varti.cdvistfam                                                                               \n";
        l_query  += "        , varti.cdvisttp                                                                                \n";
        l_query  += "        , varti.cdvistv1                                                                                \n";
        l_query  += "        , varti.cdvistv2                                                                                \n";
        l_query  += "        , varti.cdvistv3                                                                                \n";
        l_query  += "        , varti.cdvistelet                                                                              \n";
        l_query  += "        , varti.cdclas_a                                                                                \n";
        l_query  += "        , varti.vist_filedis                                                                            \n";
        l_query  += "        , varti.vist_scheda_pdf                                                                         \n";
        l_query  += "        , "+ of_descr_lingua ("vist_famiglia"         , astr.cdling, "vfam", "dsvistfam"     ) +"      \n";
        l_query  += "        , "+ of_descr_lingua ("vist_var1"             , astr.cdling, "var1", "dsextvistv1"   ) +"      \n";
        l_query  += "        , "+ of_descr_lingua ("vist_var2"             , astr.cdling, "var2", "dsextvistv2"   ) +"      \n";
        l_query  += "        , "+ of_descr_lingua ("vist_var3"             , astr.cdling, "var3", "dsextvistv3"   ) +"      \n";
        l_query  += "        , "+ of_descr_lingua ("vist_elettrificazioni" , astr.cdling, "vele", "dsextvistelet" ) +"      \n";
      }

      if (!order_by.equals("")){
          l_query  += "   order by "+order_by+"  \n";
      }


//      System.out.println(">> \n"+ l_query + "\n <<");


      pstm = setQuery_ric( l_query );


      ind = 1;

      rs = pstm.executeQuery();

      
      return rs;
    }
    
    
    public String of_relpath_resource( String cdclas_a
                                     , String tiporisorsa
                                     , String cdvistfam
                                     , String cdvisttp
                                     , String cdvistv1
                                     , String cdvistv2
                                     , String cdvistv3
                                     , String cdvistelet
                                     , String vist_filedis
                                     , String vist_scheda_pdf
    
                                     ) throws Exception {
      
       
      String slash = System.getProperty( "file.separator" );
      
      
      String path_techsheet = "fileresources"+ slash + DIR_TECHSHEET + slash;
      String path_modello = of_get_path_modello(slash);
      String nome_modello_3D = vist_filedis;
      String path_2D = path_modello + "2D"+ slash;
      String nome_modello_2D = vist_filedis;
      String path_imgcat = "fileresources"+ slash +"catimages"+ slash;
      String path_imgcat_hi = path_imgcat + "hi"+ slash;
      String nome_imgcat_hi = vist_filedis;
      String path_imgcat_lo = path_imgcat + "lo"+ slash;
      String nome_imgcat_lo = vist_filedis;
      String path_cert = "fileresources"+ slash +"cert"+ slash;
      String nome_cert = vist_filedis;
      String path_specsheet = "fileresources"+ slash +"specsheet"+ slash;
      
      
      if (tiporisorsa.equals(TECHSHEET)){
        
          if (!cdvistfam.equals("") && !cdvisttp.equals("")){

                l_query  = "";
                l_query  = "      select distinct vdex.arw_file_scheda_tec       \n";
                l_query += "        from pgmr.vist_articoli_datiextra  vdex      \n";
                l_query += "           , pgmr.vist_articoli      varti           \n";
                l_query += "           , pgmr.mis_reparto        repa            \n";
                l_query += "       where vdex.cdarti = varti.cdarti              \n";
                l_query += "         and varti.cdrepa = repa.cdrepa              \n";
                l_query += "         and vdex.arw_file_scheda_tec is not null    \n";
                l_query += "         and varti.cdvistfam is not null             \n";
                l_query += "         and varti.cdvisttp is not null              \n";
                l_query += "         and repa.cdrepa not in ('010', '018')       \n"; //CABLATO
                l_query += "         and varti.fgweb  = 'S'                      \n"; 
                l_query += "         and repa.fgweb  = 'S'                       \n"; 
                
                l_query += "         and varti.cdvistfam = '"+ cdvistfam +"'     \n";
                l_query += "         and varti.cdvisttp  = '"+ cdvisttp +"'      \n";
                
                if (!cdvistv1.equals("")){                      
                  l_query += "         and varti.cdvistv1  = '"+ cdvistv1 +"'      \n";
                } else {  
                  l_query += "         and varti.cdvistv1 is null    \n";
                }
                if (!cdvistv2.equals("")){                      
                  l_query += "         and varti.cdvistv2  = '"+ cdvistv2 +"'      \n";
                } else {  
                  l_query += "         and varti.cdvistv2 is null    \n";
                }
                if (!cdvistv3.equals("")){                      
                  l_query += "         and varti.cdvistv3  = '"+ cdvistv3 +"'      \n";
                } else {  
                  l_query += "         and varti.cdvistv3 is null    \n";
                }
                if (!cdvistelet.equals("")){                      
                  l_query += "         and varti.cdvistelet  = '"+ cdvistelet +"'  \n";
                } else {  
                  l_query += "         and varti.cdvistelet is null    \n";
                }
                
                l_query += " order by vdex.arw_file_scheda_tec           \n";

                String techsheet = sql_String(l_query);
                
                int idx_slash = techsheet.indexOf("\\");
                
                if (idx_slash >= 0){
                    techsheet = techsheet.substring(idx_slash + 1);
                }
                
                if (techsheet.equals("")) return "";

                return path_techsheet + techsheet;
          } else {
              return "";
          }  
        
      } else if (tiporisorsa.equals(MOD3D_IGS)){
        
        return of_relpath_resource_MOD3D_IGS(nome_modello_3D, cdclas_a, cdvistelet);
        
//      } else if (tiporisorsa.equals(MOD3D_EPRT)){
//        
//        String eprt = path_3D + nome_modello_3D + ".EPRT";
//        
//        if (nome_modello_3D.equals("")) return "";
//        
//        return eprt;
        
      } else if (tiporisorsa.equals(MOD3D_EASM)){
        
        return of_relpath_resource_MOD3D_EASM(nome_modello_3D, cdclas_a, cdvistelet);
        
      } else if (tiporisorsa.equals(MOD2D_DWG_PO)){

        String dwg_vers = "po"+ slash;     //POLLICI                
        
        String dwg = path_2D + dwg_vers + nome_modello_2D + ".dwg";
        
        if (nome_modello_2D.equals("")) return "";
        
        return dwg;         
        
      } else if (tiporisorsa.equals(MOD2D_DWG_CM)){
        
        String dwg_vers = "cm"+ slash;     //CENTIMETRI                    
        
        String dwg = path_2D + dwg_vers + nome_modello_2D + ".dwg"; 
        
        if (nome_modello_2D.equals("")) return "";  
        
        return dwg;               
        
      } else if (tiporisorsa.equals(IMAGEPROD_HI_MULTIF)){

        String imgcat_hi_zip = path_imgcat_hi + nome_imgcat_hi + ".zip";  
        
        if (nome_imgcat_hi.equals("")) return "";       
        
        return imgcat_hi_zip;               
        
      } else if (tiporisorsa.equals(IMAGEPROD_HI_SINGF)){

        String imgcat_hi_jpg = path_imgcat_hi + nome_imgcat_hi + ".jpg";  
        
        if (nome_imgcat_hi.equals("")) return "";                         

        return imgcat_hi_jpg;               
        
        
      } else if (tiporisorsa.equals(IMAGEPROD_LO_MULTIF)){
        
        String imgcat_lo_zip = path_imgcat_lo + nome_imgcat_lo + ".zip"; 
        
        if (nome_imgcat_lo.equals("")) return "";                         
        
        return imgcat_lo_zip;               
        
      } else if (tiporisorsa.equals(IMAGEPROD_LO_SINGF)){
        
        String imgcat_lo_jpg = path_imgcat_lo + nome_imgcat_lo + ".jpg"; 
        
        if (nome_imgcat_lo.equals("")) return "";                             
        
        return imgcat_lo_jpg;               
        
      } else if (tiporisorsa.equals(CERT)){
        
        String cert = path_cert + nome_cert + ".pdf"; 
        
        if (nome_cert.equals("")) return "";                         
        
        return cert;               
        
      } else if (tiporisorsa.equals(SPECSHEET_IT)){
        
        String path_specsheet_it = path_specsheet + "it"+ slash;
        String nome_specsheet_it = vist_scheda_pdf;

        String specsheet_it = path_specsheet_it + nome_specsheet_it; 
        
        if (nome_specsheet_it.equals("")) return "";                
      
        return specsheet_it;               
        
      } else if (tiporisorsa.equals(SPECSHEET_US)){
        
        String path_specsheet_us = path_specsheet + "us"+ slash;
        String nome_specsheet_us = vist_scheda_pdf;
        
        String specsheet_us = path_specsheet_us + nome_specsheet_us;
        
        if (nome_specsheet_us.equals("")) return "";                
 
        return specsheet_us;               
        
      } else {
        throw new Exception("Tipo risorsa NON gestito");
      }

    }

    private String of_get_path_modello(String slash) {
        return "fileresources"+ slash +"models"+ slash;
    }

    public String of_relpath_resource_MOD3D_EASM(String nome_modello_3D, String cdclas_a, String cdvistelet) {
        String path_3D = of_get_path_3D();
        String easm = path_3D + nome_modello_3D + ".EASM";
        if (nome_modello_3D.equals("")) {
            return "";
        }
        if (isListinoLed(cdclas_a)) {
            easm = path_3D + nome_modello_3D + cdvistelet + ".EASM";
        }
        return easm;
    }

    private String of_get_path_3D() {
        String slash = System.getProperty("file.separator");
        return of_get_path_modello(slash) + "3D" + slash;
    }

    private String of_get_path_MOD3DBLOCCHI_LED() {
        String slash = System.getProperty("file.separator");
        return of_get_path_modello(slash) + "3D" + slash + "blocchi_LED" + slash;
    }

    public String of_relpath_resource_MOD3D_IGS(String nome_modello_3D, String cdclas_a, String cdvistelet) {
        String path_3D = of_get_path_3D();
        String igs = path_3D + nome_modello_3D + ".zip";
        if (nome_modello_3D.equals("")) {
            return "";
        }
        if (isListinoLed(cdclas_a)) {
            igs = path_3D + nome_modello_3D + cdvistelet + ".zip";
        }
        return igs;
    }

    public String of_relpath_resource_MOD3DBLOCCHI_LED_EASM(String cdvistelet) throws Exception {
        String path_MOD3DBLOCCHI_LED = of_get_path_MOD3DBLOCCHI_LED();
        String easm = path_MOD3DBLOCCHI_LED + of_getCdblocco_led3d(cdvistelet) + ".EASM";
        return easm;
    }

    public String of_relpath_resource_MOD3DBLOCCHI_LED_IGS(String cdvistelet) throws Exception {
        String path_MOD3DBLOCCHI_LED = of_get_path_MOD3DBLOCCHI_LED();
        String igs = path_MOD3DBLOCCHI_LED + of_getCdblocco_led3d(cdvistelet) + ".zip";
        return igs;
    }

    public String of_getCdblocco_led3d (String cdvistelet) throws Exception{
        return sql_String("select cdblocco_led3d from pgmr.vist_elettrificazioni where cdvistelet = '"+ cdvistelet +"'");
    }

    private static boolean isListinoLed(String cdclas_a) {
        return cdclas_a.equals(CDCLAS_A_LL)
                || cdclas_a.equals(CDCLAS_A_ULL);
    }
    
    
    public String of_relpath_resource_specsheet(String cdclas_a, String rootpath, String prefixfilename, String lang, String cdartm, String cdvistelet) {
        String slash = System.getProperty("file.separator");
        
        String relativepath = "images"+ slash +"articoli"+ slash +"specsheetres"+ slash +"risorse"+ slash;
        
        String relpathDefault = "/specsheet/"+ cdartm; 
        
        List<String> possibleFilenameList = of_specsheet_possibleFilenameList(prefixfilename, cdclas_a, cdvistelet);
        
        for (String ls_prefixfilename : possibleFilenameList) {

            String pathPdf = rootpath + relativepath + ls_prefixfilename + ".pdf";
            String pathU3D = rootpath + relativepath + ls_prefixfilename + ".U3D";
            String pathXlsx = rootpath + relativepath + ls_prefixfilename + "_" + lang + ".xlsx";

            if (new File(pathPdf).exists()){
//              if (new File(pathU3D).exists()){
                if (new File(pathXlsx).exists()){
                    return relpathDefault;
                }
//              }
            }                              

        }
   
        return "";
    }

    public List<String> of_specsheet_possibleFilenameList(String prefixfilename, String cdclas_a, String cdvistelet) {
        List<String> possibleFilenameList = new ArrayList<String>();
        if (CDCLAS_A_UL.equals(cdclas_a)
                || CDCLAS_A_LOU.equals(cdclas_a)
                || CDCLAS_A_ULL.equals(cdclas_a)
                ) {
            possibleFilenameList.add(0, prefixfilename + cdvistelet + CDCLAS_A_UL);
        } else {
            possibleFilenameList.add(0, prefixfilename + cdvistelet);
        }
        return possibleFilenameList;
    }

    private List<String> of_specsheet_generica_possibleFilenameList(String prefixfilename, String cdclas_a, String cdvistelet) {
        List<String> possibleFilenameList = new ArrayList<String>();
        if (CDCLAS_A_UL.equals(cdclas_a)
                || CDCLAS_A_LOU.equals(cdclas_a)
                || CDCLAS_A_ULL.equals(cdclas_a)
                ) {
            possibleFilenameList.add(0, prefixfilename + CDCLAS_A_UL);
            possibleFilenameList.add(0, prefixfilename + cdvistelet + CDCLAS_A_UL);
        } else {
            possibleFilenameList.add(0, prefixfilename);
            possibleFilenameList.add(0, prefixfilename + cdvistelet);
        }
        return possibleFilenameList;
    }
    
    
    public boolean of_exist_resource_specsheet_generica(String cdclas_a, String rootpath, String prefixfilename, String lang, String cdvisttp, String cdvistfam, String cdvistv1, String cdvistv2, String cdvistv3, String cdvistelet) {
        String slash = System.getProperty("file.separator");
        
        String relativepath = "images"+ slash +"articoli"+ slash +"specsheetres"+ slash +"risorse"+ slash;
        
        List<String> possibleFilenameList = of_specsheet_generica_possibleFilenameList(prefixfilename, cdclas_a, cdvistelet);
        
        for (String ls_prefixfilename : possibleFilenameList) {
            
//            System.out.println("rootpath "+ rootpath);
//            System.out.println("relativepath "+ relativepath);
//            System.out.println("ls_prefixfilename "+ ls_prefixfilename);
//            System.out.println("lang "+ lang);

            String pathPdf = rootpath + relativepath + ls_prefixfilename + ".pdf";
            String pathXlsx = rootpath + relativepath + ls_prefixfilename + "_" + lang + ".xlsx";

//            System.out.println(""+ new File(pathPdf).exists());
//            System.out.println(""+ new File(pathXlsx).exists());
            
            if (new File(pathPdf).exists()){
                if (new File(pathXlsx).exists()){
                    return true;
                }
            }                              

        }
   
        return false;
    }

    public String of_relpath_resource_specsheet_generica(String cdclas_a, String cdvisttp, String cdvistfam, String cdvistv1, String cdvistv2, String cdvistv3, String cdvistelet) {
        return "fileresources/generic_specsheet/SG|"+ cdclas_a +"|"+ cdvisttp +"|"+ cdvistfam +"|"+ cdvistv1 +"|"+ cdvistv2 +"|"+ cdvistv3 +"|"+ cdvistelet;
    }

    public String of_getCdclas_a_from_relpath_resource_specsheet_generica(String url_risorse) {
        String ls_ = of_getResource_from_relpath_resource_specsheet_generica(url_risorse);
        String[] arr_ = ls_.split("\\|");
        return arr_[0];
    }

    public String of_getCdvisttp_from_relpath_resource_specsheet_generica(String url_risorse) {
        String ls_ = of_getResource_from_relpath_resource_specsheet_generica(url_risorse);
        String[] arr_ = ls_.split("\\|");
        return arr_[1];
    }

    public String of_getCdvistfam_from_relpath_resource_specsheet_generica(String url_risorse) {
        String ls_ = of_getResource_from_relpath_resource_specsheet_generica(url_risorse);
        String[] arr_ = ls_.split("\\|");
        return arr_[2];
    }

    public String of_getCdvistv1_from_relpath_resource_specsheet_generica(String url_risorse) {
        String ls_ = of_getResource_from_relpath_resource_specsheet_generica(url_risorse);
        String[] arr_ = ls_.split("\\|");
        return arr_[3];
    }

    public String of_getCdvistv2_from_relpath_resource_specsheet_generica(String url_risorse) {
        String ls_ = of_getResource_from_relpath_resource_specsheet_generica(url_risorse);
        String[] arr_ = ls_.split("\\|");
        return arr_[4];
    }

    public String of_getCdvistv3_from_relpath_resource_specsheet_generica(String url_risorse) {
        String ls_ = of_getResource_from_relpath_resource_specsheet_generica(url_risorse);
        String[] arr_ = ls_.split("\\|");
        return arr_[5];
    }

    public String of_getCdvistelet_from_relpath_resource_specsheet_generica(String url_risorse) {
        String ls_ = of_getResource_from_relpath_resource_specsheet_generica(url_risorse);
        String[] arr_ = ls_.split("\\|");
        return arr_[6];
    }

    private String of_getResource_from_relpath_resource_specsheet_generica(String url_risorse) {
        return url_risorse.substring(url_risorse.indexOf("/SG|") + 4);
    }
    
    
    public boolean of_exist_resource(String tiporisorsa, String relpath_resource) throws Exception{
        Ep_costanti ep_costanti = new Ep_costanti();
        setProfilo(ep_costanti);
        String  ep_site_siteroot = ep_costanti.getCostvalue("ep.site_siteroot"    );
        ep_costanti.close();
        
        if (relpath_resource.isEmpty()){
            return false;
        }
        
        if (tiporisorsa.equals(TECHSHEET)) {
            File f_techsheet = new File ( ep_site_siteroot + relpath_resource);
            return f_techsheet.exists();
        } else if (tiporisorsa.equals(MOD3D_IGS)) {
            File f_3D_igs = new File ( ep_site_siteroot + relpath_resource);
            return f_3D_igs.exists();
        } else if (tiporisorsa.equals(MOD3D_EASM)) {
            File f_3D_easm = new File ( ep_site_siteroot + relpath_resource);
            return f_3D_easm.exists();
        } else if (tiporisorsa.equals(MOD2D_DWG_PO) || tiporisorsa.equals(MOD2D_DWG_CM)) {
            File f_2D_dwg = new File ( ep_site_siteroot + relpath_resource);
            return f_2D_dwg.exists();
        } else if (tiporisorsa.equals(IMAGEPROD_HI_MULTIF)) {
            File f_imgcat_hi_zip = new File ( ep_site_siteroot + relpath_resource);
            return f_imgcat_hi_zip.exists();
        } else if (tiporisorsa.equals(IMAGEPROD_HI_SINGF)) {
            File f_imgcat_hi_jpg = new File ( ep_site_siteroot + relpath_resource);
            return f_imgcat_hi_jpg.exists();
        } else if (tiporisorsa.equals(IMAGEPROD_LO_MULTIF)) {
            File f_imgcat_lo_zip = new File ( ep_site_siteroot + relpath_resource);
            return f_imgcat_lo_zip.exists();
        } else if (tiporisorsa.equals(IMAGEPROD_LO_SINGF)) {
            File f_imgcat_lo_jpg = new File ( ep_site_siteroot + relpath_resource);
            return f_imgcat_lo_jpg.exists();
        } else if (tiporisorsa.equals(MOD3DBLOCCHI_LED_IGS)) {
            File f_3DBlocchi_LED_igs = new File ( ep_site_siteroot + relpath_resource);
            return f_3DBlocchi_LED_igs.exists();
        } else if (tiporisorsa.equals(MOD3DBLOCCHI_LED_EASM)) {
            File f_3DBlocchi_LED_easm = new File ( ep_site_siteroot + relpath_resource);
            return f_3DBlocchi_LED_easm.exists();
        } else {
            throw new Exception("Tipo risorsa NON gestito");
        }
    }
    
    
    
    
    public ResultSet getDropdownVetro( String cdclas_a, String cdvisttp, String cdvistfam, String cdvistv1, String cdvistv2, String cdvistv3) throws Exception {
        
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs      = null; 

        l_query   = "";
        if (is_oracle){
            throw new Exception("DB non supportato");
        } else if (is_sybase) {
            l_query  += "      select distinct 'COLV' || varti.cdvistcolv || '~' || 'FINV' || varti.cdvistfinv as cd                                                                                             \n";
            l_query  += "           , "+ of_descr_lingua("vist_colori_vetro", s_cdling, "colv", "dsextvistcolv") +" || ' ' || "+ of_descr_lingua("vist_finit_vetro", s_cdling, "finv", "dsextvistfinv") +" as ds \n";
            l_query  += "           , varti.cdvistcolv || varti.cdvistfinv as cdshop                                                                                                                             \n";
        } else if (is_postgresql) {
            l_query  += "      select distinct concat('COLV' , varti.cdvistcolv , '~' , 'FINV' , varti.cdvistfinv) as cd                                                                                             \n";
            l_query  += "           , concat("+ of_descr_lingua("vist_colori_vetro", s_cdling, "colv", "dsextvistcolv") +" , ' ' , "+ of_descr_lingua("vist_finit_vetro", s_cdling, "finv", "dsextvistfinv") +") as ds \n";
            l_query  += "           , concat(varti.cdvistcolv , varti.cdvistfinv) as cdshop                                                                                                                             \n";
        }
        l_query  += "        from pgmr.vist_articoli     varti                                                                                                                                               \n";
        l_query  += "             left outer join pgmr.vist_colori_vetro colv on varti.cdvistcolv = colv.cdvistcolv                                                                                          \n";
        l_query  += "             left outer join pgmr.vist_finit_vetro  finv on varti.cdvistfinv = finv.cdvistfinv                                                                                          \n";
        l_query  += "           , pgmr.mis_reparto       repa                                                                                                                                                \n";
        l_query  += "       where varti.cdrepa = repa.cdrepa                                                                                                                                                 \n";
        l_query  += "         and varti.fgweb = 'S'                                                                                                                                                          \n";
        l_query  += "         and repa.cdrepa not in ('010', '018')                                                                                                                                          \n";
        l_query  += "         and varti.cdclas_a = '"+ cdclas_a +"'                                                                                                                                          \n";
        l_query  += "         and varti.cdvisttp = '"+ cdvisttp +"'                                                                                                                                          \n";
        l_query  += "         and varti.cdvistfam = '"+ cdvistfam +"'                                                                                                                                        \n";
        if (!cdvistv1.isEmpty()){
            l_query  += "         and varti.cdvistv1 = '"+ cdvistv1 +"'                                                                                                                                          \n";
        } else {                                                 
            l_query  += "         and varti.cdvistv1 is null                                                                                                                                                     \n";
        }                                                 
        if (!cdvistv2.isEmpty()){                                                 
            l_query  += "         and varti.cdvistv2 = '"+ cdvistv2 +"'                                                                                                                                          \n";
        } else {                                                 
            l_query  += "         and varti.cdvistv2 is null                                                                                                                                                     \n";
        }                                                 
        if (!cdvistv3.isEmpty()){                                                 
            l_query  += "         and varti.cdvistv3 = '"+ cdvistv3 +"'                                                                                                                                          \n";
        } else {                                                 
            l_query  += "         and varti.cdvistv3 is null                                                                                                                                                     \n";
        }                                                 
        l_query  += "         and (varti.cdvistcolv is not null or varti.cdvistfinv is not null)                                                                                                             \n"; // controllo che sia presente ho il colore o la finitura del vetro
        l_query  += "    order by 2                                                                                                                                                                          \n";
        
//        System.out.println("\n\n"+ l_query);
		pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();


        return rs;
    }
    
    
    
    public ResultSet getDropdownMontatura( String cdclas_a, String cdvisttp, String cdvistfam, String cdvistv1, String cdvistv2, String cdvistv3) throws Exception {
        
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs      = null; 

        l_query   = "";
        l_query  += "      select distinct varti.cdvistfinm as cd                                                        \n";
        l_query  += "           , "+ of_descr_lingua("vist_finit_mont", s_cdling, "finm", "dsextvistfinm") +" as ds      \n";
        l_query  += "        from pgmr.vist_articoli     varti                                                           \n";
        l_query  += "           , pgmr.vist_finit_mont   finm                                                            \n";
        l_query  += "       where varti.cdvistfinm = finm.cdvistfinm                                                     \n";
        l_query  += "         and varti.fgweb = 'S'                                                                      \n";
        l_query  += "         and varti.cdclas_a = '"+ cdclas_a +"'                                                      \n";
        l_query  += "         and varti.cdvisttp = '"+ cdvisttp +"'                                                      \n";
        l_query  += "         and varti.cdvistfam = '"+ cdvistfam +"'                                                    \n";
        if (!cdvistv1.isEmpty()){
            l_query  += "         and varti.cdvistv1 = '"+ cdvistv1 +"'                                                      \n";
        } else {                                                 
            l_query  += "         and varti.cdvistv1 is null                                                                 \n";
        }                                                 
        if (!cdvistv2.isEmpty()){                                                 
            l_query  += "         and varti.cdvistv2 = '"+ cdvistv2 +"'                                                      \n";
        } else {                                                 
            l_query  += "         and varti.cdvistv2 is null                                                                 \n";
        }                                                 
        if (!cdvistv3.isEmpty()){                                                 
            l_query  += "         and varti.cdvistv3 = '"+ cdvistv3 +"'                                                      \n";
        } else {                                                 
            l_query  += "         and varti.cdvistv3 is null                                                                 \n";
        }                                                 
        l_query  += "    order by 2                                                                                      \n";
        
//        System.out.println("\n\n"+ l_query);
		pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();


        return rs;
    }
    
    
    
    public ResultSet searchArti( String cdclas_a
                                , String cdvisttp
                                , String cdvistfam
                                , String cdvistv1
                                , String cdvistv2
                                , String cdvistv3
                                , String cdvistelet
                                , String cdvistcolv
                                , String cdvistfinv
                                , String cdvistfinm
                                                    ) throws Exception {
        
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs      = null; 

        l_query   = "";
        l_query  += "      select varti.cdarti                                                                           \n";
        l_query  += "           , varti.cdartm                                                                           \n";
        l_query  += "           , varti.vist_filedis                                                                     \n";
        l_query  += "           , "+ of_descr_lingua ("vist_famiglia"         , s_cdling, "vfam", "dsvistfam"     ) +" as dsvistfam      \n";
        l_query  += "           , "+ of_descr_lingua ("vist_var1"             , s_cdling, "var1", "dsextvistv1"   ) +" as dsextvistv1    \n";
        l_query  += "           , "+ of_descr_lingua ("vist_var2"             , s_cdling, "var2", "dsextvistv2"   ) +" as dsextvistv2    \n";
        l_query  += "           , "+ of_descr_lingua ("vist_var3"             , s_cdling, "var3", "dsextvistv3"   ) +" as dsextvistv3    \n";
        l_query  += "           , "+ of_descr_lingua ("vist_elettrificazioni" , s_cdling, "vele", "dsextvistelet" ) +" as dsextvistelet  \n";
        l_query  += "        from pgmr.vist_articoli     varti                                                           \n";
        l_query  += "             left outer join  pgmr.vist_famiglia         vfam on varti.cdvistfam   = vfam.cdvistfam   \n";
        l_query  += "             left outer join  pgmr.vist_var1             var1 on varti.cdvistv1    = var1.cdvistv1    \n";
        l_query  += "             left outer join  pgmr.vist_var2             var2 on varti.cdvistv2    = var2.cdvistv2    \n";
        l_query  += "             left outer join  pgmr.vist_var3             var3 on varti.cdvistv3    = var3.cdvistv3    \n";
        l_query  += "             left outer join  pgmr.vist_elettrificazioni vele on varti.cdvistelet  = vele.cdvistelet  \n";
        l_query  += "       where varti.fgweb = 'S'                                                                      \n";
        l_query  += "         and varti.cdclas_a = '"+ cdclas_a +"'                                                      \n";
        l_query  += "         and varti.cdvisttp = '"+ cdvisttp +"'                                                      \n";
        l_query  += "         and varti.cdvistfam = '"+ cdvistfam +"'                                                    \n";
        if (!cdvistv1.isEmpty()){
            l_query  += "         and varti.cdvistv1 = '"+ cdvistv1 +"'                                                      \n";
        } else {                                                 
            l_query  += "         and varti.cdvistv1 is null                                                                 \n";
        }                                                 
        if (!cdvistv2.isEmpty()){                                                 
            l_query  += "         and varti.cdvistv2 = '"+ cdvistv2 +"'                                                      \n";
        } else {                                                 
            l_query  += "         and varti.cdvistv2 is null                                                                 \n";
        }                                                 
        if (!cdvistv3.isEmpty()){                                                 
            l_query  += "         and varti.cdvistv3 = '"+ cdvistv3 +"'                                                      \n";
        } else {                                                 
            l_query  += "         and varti.cdvistv3 is null                                                                 \n";
        }                                                 
        l_query  += "         and varti.cdvistelet = '"+ cdvistelet +"'                                                  \n";
        if (!cdvistcolv.isEmpty()){                                                 
            l_query  += "         and varti.cdvistcolv = '"+ cdvistcolv +"'                                                  \n";
        } else {                                                 
            l_query  += "         and varti.cdvistcolv is null                                                               \n";
        }                                                 
        if (!cdvistfinv.isEmpty()){                                                 
            l_query  += "         and varti.cdvistfinv = '"+ cdvistfinv +"'                                                  \n";
        } else {                                                 
            l_query  += "         and varti.cdvistfinv is null                                                               \n";
        }                                                 
        if (!cdvistfinm.isEmpty()){                                                 
            l_query  += "         and varti.cdvistfinm = '"+ cdvistfinm +"'                                                  \n";
        } else {                                                 
            l_query  += "         and varti.cdvistfinm is null                                                               \n";
        }                                                 
        l_query  += "    order by cdartm                                                                                 \n";
        
//        System.out.println("\n\n"+ l_query);
        
		pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();


        return rs;
    }
    
    
    
    
    public String of_getUrl_risorsa_esistente(String url_risorsa, String lang) throws Exception {

        if (url_risorsa.equals("")) {
            return "";
        }

        Costanti_comm costanti_comm = new Costanti_comm();
        Ep_costanti ep_costanti = new Ep_costanti();

        setProfilo((Atk_sql) costanti_comm);
        setProfilo((Atk_sql) ep_costanti);

        String siteroot = costanti_comm.getCostvalue("site.siteroot");
        String shopSiteroot = ep_costanti.getCostvalue("ep.shop_root");
        String slash = System.getProperty("file.separator");

        String ls_url_risorsa_esistente = "";

        String relpathfile = url_risorsa;

        //System.out.println(" relpathfile "+ relpathfile);
        relpathfile = relpathfile.replace("/", slash);
        relpathfile = relpathfile.replace("\\", slash);

        if (relpathfile.indexOf(slash) == 0) {
            relpathfile = relpathfile.substring(1, relpathfile.length());
        }

        int idx_ext = relpathfile.lastIndexOf(".");
        int idx_last_slash = relpathfile.lastIndexOf(slash);

        if (idx_ext < 0) {

            String cartella = "";

            if (idx_last_slash >= 0) {
                cartella = relpathfile.substring(0, idx_last_slash + 1);
            }
            String ls_filename_ipotetico = relpathfile.substring(idx_last_slash + 1, relpathfile.length());

            //System.out.println(" cartella "+ cartella);
            //System.out.println(" ls_filename_ipotetico "+ ls_filename_ipotetico);
            if (cartella.equals("specsheet"+ slash)){

                relpathfile = url_risorsa;
                ls_filename_ipotetico = relpathfile.replace("/specsheet/", "");
                ls_filename_ipotetico = ls_filename_ipotetico.replace("specsheet/", "");

                String l_query = "";

                l_query = "";
                l_query = "    select arti.vist_filedis                                 \n";
                l_query += "         , arti.cdclas_a                                     \n";
                l_query += "         , arti.cdvistelet                                   \n";
                l_query += "      from pgmr.mrp_arch_articoli   arti                     \n";
                l_query += "     where arti.cdartm  = '" + ls_filename_ipotetico + "'      \n";

                //String nome_modello = sql_String(l_query);
                ResultSet rs = sql_query(l_query);

                String nome_modello = "";
                String cdclas_a = "";
                String cdvistelet = "";

                if (rs != null && rs.next()) {

                    if (rs.getObject("vist_filedis") != null) {
                        nome_modello = rs.getString("vist_filedis");
                    }
                    if (rs.getObject("cdclas_a") != null) {
                        cdclas_a = rs.getString("cdclas_a");
                    }
                    if (rs.getObject("cdvistelet") != null) {
                        cdvistelet = rs.getString("cdvistelet");
                    }
                }

                return of_relpath_resource_specsheet(cdclas_a, shopSiteroot, nome_modello, lang, ls_filename_ipotetico, cdvistelet);

            } else {

                String[] arr_codici = ls_filename_ipotetico.split("\\|");

                if (arr_codici != null && arr_codici.length == 7) {
                    String cdfile = arr_codici[0];
                    String cdvisttp = arr_codici[1];
                    String cdvistfam = arr_codici[2];
                    String cdvistv1 = arr_codici[3];
                    String cdvistv2 = arr_codici[4];
                    String cdvistv3 = arr_codici[5];
                    String cdvistelet = arr_codici[6];

                    //        System.out.println(" cdfile "+ cdfile);
                    //        System.out.println(" cdvisttp "+ cdvisttp);
                    //        System.out.println(" cdvistfam "+ cdvistfam);
                    //        System.out.println(" cdvistv1 "+ cdvistv1);
                    //        System.out.println(" cdvistv2 "+ cdvistv2);
                    //        System.out.println(" cdvistv3 "+ cdvistv3);
                    //        System.out.println(" cdvistelet "+ cdvistelet);
                    if (cdfile.equals("IM")) {  // ISTR. MONTAGGIO

                        //          System.out.println("ISTR. MONTAGGIO");
                        if (!cdvisttp.equals("")
                                || !cdvistfam.equals("")
                                || !cdvistv1.equals("")
                                || !cdvistv2.equals("")
                                || !cdvistv3.equals("")
                                || !cdvistelet.equals("")) {

                            String l_query = "";
                            
                            l_query  = "";
                            l_query  = "      select distinct vdex.arw_file_scheda_tec       \n";
                            l_query += "        from pgmr.vist_articoli_datiextra  vdex      \n";
                            l_query += "           , pgmr.vist_articoli      varti           \n";
                            l_query += "           , pgmr.mis_reparto        repa            \n";
                            l_query += "       where vdex.cdarti = varti.cdarti              \n";
                            l_query += "         and varti.cdrepa = repa.cdrepa              \n";
                            l_query += "         and vdex.arw_file_scheda_tec is not null    \n";
                            l_query += "         and varti.cdvistfam is not null             \n";
                            l_query += "         and varti.cdvisttp is not null              \n";
                            l_query += "         and repa.cdrepa not in ('010', '018')       \n"; //CABLATO
                            l_query += "         and varti.fgweb  = 'S'                      \n"; 
                            l_query += "         and repa.fgweb  = 'S'                       \n"; 

                            l_query += "         and varti.cdvistfam = '"+ cdvistfam +"'     \n";
                            l_query += "         and varti.cdvisttp  = '"+ cdvisttp +"'      \n";

                            if (!cdvistv1.equals("")){                      
                              l_query += "         and varti.cdvistv1  = '"+ cdvistv1 +"'      \n";
                            } else {  
                              l_query += "         and varti.cdvistv1 is null    \n";
                            }
                            if (!cdvistv2.equals("")){                      
                              l_query += "         and varti.cdvistv2  = '"+ cdvistv2 +"'      \n";
                            } else {  
                              l_query += "         and varti.cdvistv2 is null    \n";
                            }
                            if (!cdvistv3.equals("")){                      
                              l_query += "         and varti.cdvistv3  = '"+ cdvistv3 +"'      \n";
                            } else {  
                              l_query += "         and varti.cdvistv3 is null    \n";
                            }
                            if (!cdvistelet.equals("")){                      
                              l_query += "         and varti.cdvistelet  = '"+ cdvistelet +"'  \n";
                            } else {  
                              l_query += "         and varti.cdvistelet is null    \n";
                            }

                            l_query += " order by vdex.arw_file_scheda_tec           \n";
                            
//                            System.out.println("l_query "+ l_query);

                            String filename = sql_String(l_query);

                            int idx_slash = filename.indexOf("\\");

                            if (idx_slash >= 0){
                                filename = filename.substring(idx_slash + 1);
                            }

                            if (!filename.equals("")) {
                                relpathfile = cartella + filename;
                            }

                        }
                    } else {
                        System.out.println("File richiesto non identificabile: " + url_risorsa);
                    }

                } else if (arr_codici != null && arr_codici.length == 8) {
                    String cdfile = arr_codici[0];
                    String cdclas_a = arr_codici[1];
                    String cdvisttp = arr_codici[2];
                    String cdvistfam = arr_codici[3];
                    String cdvistv1 = arr_codici[4];
                    String cdvistv2 = arr_codici[5];
                    String cdvistv3 = arr_codici[6];
                    String cdvistelet = arr_codici[7];

//                System.out.println(" cdfile "+ cdfile);
//                System.out.println(" cdclas_a "+ cdclas_a);
//                System.out.println(" cdvisttp "+ cdvisttp);
//                System.out.println(" cdvistfam "+ cdvistfam);
//                System.out.println(" cdvistv1 "+ cdvistv1);
//                System.out.println(" cdvistv2 "+ cdvistv2);
//                System.out.println(" cdvistv3 "+ cdvistv3);
//                System.out.println(" cdvistelet "+ cdvistelet);
                    if (cdfile.equals("SG")) {  // SPECSHEET GENERICA
//                    System.out.println("SPECSHEET GENERICA");

                        String l_query = "";

                        l_query = "";
                        l_query = "    select arti.vist_filedis                                 \n";
                        l_query += "      from pgmr.vist_articoli   arti                         \n";

                        l_query += "     where arti.cdvisttp  = '" + cdvisttp + "'        \n";
                        l_query += "       and arti.cdvistfam  = '" + cdvistfam + "'      \n";

                        if (!cdvistv1.equals("")) {
                            l_query += "       and arti.cdvistv1  = '" + cdvistv1 + "'      \n";
                        } else {
                            l_query += "       and arti.cdvistv1 is null    \n";
                        }
                        if (!cdvistv2.equals("")) {
                            l_query += "       and arti.cdvistv2  = '" + cdvistv2 + "'      \n";
                        } else {
                            l_query += "       and arti.cdvistv2 is null    \n";
                        }
                        if (!cdvistv3.equals("")) {
                            l_query += "       and arti.cdvistv3  = '" + cdvistv3 + "'      \n";
                        } else {
                            l_query += "       and arti.cdvistv3 is null    \n";
                        }
                        if (!cdvistelet.equals("")) {
                            l_query += "       and arti.cdvistelet  = '" + cdvistelet + "'  \n";
                        } else {
                            l_query += "       and arti.cdvistelet is null    \n";
                        }
                        
//                        System.out.println("l_query \n"+ l_query);

                        PreparedStatement pstm = setQuery(l_query);

                        ResultSet rs = pstm.executeQuery();

                        String nome_modello = "";

                        if (rs != null && rs.next()) {
                            nome_modello = "";

                            if (rs.getObject("vist_filedis") != null) {
                                nome_modello = rs.getString("vist_filedis");
                            }
                        }
                        
//                        System.out.println("nome_modello "+ nome_modello);
//                        System.out.println("lang "+ lang);

                        if (of_exist_resource_specsheet_generica(cdclas_a, shopSiteroot, nome_modello, lang, cdvisttp, cdvistfam, cdvistv1, cdvistv2, cdvistv3, cdvistelet)) {
                            return of_relpath_resource_specsheet_generica(cdclas_a, cdvisttp, cdvistfam, cdvistv1, cdvistv2, cdvistv3, cdvistelet);
                        }
                    }
                }

            }

        } // FINE . if (idx_ext

//    System.out.println(" relpathfile "+ relpathfile);
        File lobj_file = new File(siteroot + relpathfile);

//    System.out.println(siteroot + relpathfile);
        if (lobj_file.exists()) {
//      System.out.println("File trovato");
            ls_url_risorsa_esistente = relpathfile.replace("\\", "/");
        } else {
//      System.out.println("File NON trovato");
            ls_url_risorsa_esistente = "";
        }

        costanti_comm.close();
        ep_costanti.close();

        return ls_url_risorsa_esistente;

    }


    @Override
    public String of_suff_lingua(String cdling) throws Exception {
        return of_suff_lingua("mrp_arch_articoli", cdling);
    }

    @Override
    public String of_descr_lingua(String as_cdling, String as_alias, String as_campo_descr) throws Exception {
        return of_descr_lingua("mrp_arch_articoli", as_cdling, as_alias, as_campo_descr);
    }

    public Str_risorsa_anticipo of_getRisorsa_anticipo(String url_risorse, String tiporisorsa, String nome_modello, String cdvistelet, long tkc, String lang, String cdling_ep_lingua) throws Exception {
        Ep_costanti ep_costanti = new Ep_costanti();

        setProfilo((Atk_sql) ep_costanti);
        
        String  ep_shop_root  = ep_costanti.getCostvalue("ep.shop_root");
        String  ep_portal_url = ep_costanti.getCostvalue("ep.portal_url");
        ep_portal_url = of_cambiaURLLingua(ep_portal_url, cdling_ep_lingua);
        String ep_eprogen_url    = ep_costanti.getCostvalue("ep.eprogen_url");
        ep_eprogen_url = of_cambiaURLLingua(ep_eprogen_url, cdling_ep_lingua);

        if (ep_eprogen_url.equals("")) ep_eprogen_url = of_trasformaURL(ep_portal_url, "portal", "eprogen");

        
        if (tiporisorsa.equals(Mrp_arch_articoli.MOD3D_EASM)
            || tiporisorsa.equals(Mrp_arch_articoli.MOD3D_IGS)    
                                    ){

            String relpath_MOD3D = "";
            String relpath_MOD3DBLOCCHI_LED = "";
            String tiporisorsa_MOD3DBLOCCHI_LED = "";
            // controllare esistenza blocco 3d e esistenza modello base
            if (tiporisorsa.equals(MOD3D_EASM)){
                tiporisorsa_MOD3DBLOCCHI_LED = MOD3DBLOCCHI_LED_EASM;
                relpath_MOD3D = of_relpath_resource_MOD3D_EASM(nome_modello, "", "");
                relpath_MOD3DBLOCCHI_LED = of_relpath_resource_MOD3DBLOCCHI_LED_EASM(cdvistelet);
            } else {
                tiporisorsa_MOD3DBLOCCHI_LED = MOD3DBLOCCHI_LED_IGS;
                relpath_MOD3D = of_relpath_resource_MOD3D_IGS(nome_modello, "", "");
                relpath_MOD3DBLOCCHI_LED = of_relpath_resource_MOD3DBLOCCHI_LED_IGS(cdvistelet);
            }
            boolean exist_MOD3D = of_exist_resource(tiporisorsa, relpath_MOD3D);
            boolean exist_MOD3DBLOCCHI_LED = of_exist_resource(tiporisorsa_MOD3DBLOCCHI_LED, relpath_MOD3DBLOCCHI_LED);
            
             // Se vero invio notifica specifica con anticipazione link blocco 3d e esistenza modello base
            if (exist_MOD3D && exist_MOD3DBLOCCHI_LED){
                // path URL zip
                String file_req_1 = ep_portal_url + "download/"+ relpath_MOD3D + "?f="+ relpath_MOD3D +"&tkc="+tkc +"&lang="+ lang ;
                String file_req_2 = ep_portal_url + "download/"+ relpath_MOD3DBLOCCHI_LED + "?f="+ relpath_MOD3DBLOCCHI_LED +"&tkc="+tkc +"&lang="+ lang ;
                
                return new Str_risorsa_anticipo(TPRESANTICIPO_3DBLOCLED, ep_eprogen_url + "zip/download.zip?"+ "file_req="+ file_req_1 + "&" + "file_req="+ file_req_2);
            }
        } else if (tiporisorsa.equals(Mrp_arch_articoli.SPECSHEET_SPEC)){
            
            // Recupero codice articolo manuale 
            String prefix = "specsheet/";
            String cdartm = url_risorse.substring(url_risorse.lastIndexOf(prefix) + prefix.length());

//            System.out.println("cdartm: "+ cdartm);

            // Recupero dati articolo
            l_query  = "";
            l_query += "    select arti.cdclas_a                      \n";
            l_query += "         , arti.cdvisttp                      \n";
            l_query += "         , arti.cdvistfam                     \n";
            l_query += "         , arti.cdvistv1                      \n";
            l_query += "         , arti.cdvistv2                      \n";
            l_query += "         , arti.cdvistv3                      \n";
            l_query += "         , arti.cdvistelet                    \n";
            l_query += "      from pgmr.mrp_arch_articoli   arti      \n";
            l_query += "     where arti.cdartm  = ?                   \n";

            pstm = setQuery(l_query);

            int ind = 1;
            pstm.setString(ind++, cdartm);

            ResultSet rs_box = pstm.executeQuery();

            String ls_cdclas_a   = "";
            String ls_cdvisttp   = "";
            String ls_cdvistfam  = "";
            String ls_cdvistv1   = "";
            String ls_cdvistv2   = "";
            String ls_cdvistv3   = "";
            String ls_cdvistelet = "";

            if (rs_box != null && rs_box.next()){
                if (rs_box.getObject("cdclas_a"       )!= null) ls_cdclas_a         = rs_box.getString("cdclas_a"    );
                if (rs_box.getObject("cdvisttp"       )!= null) ls_cdvisttp         = rs_box.getString("cdvisttp"    );
                if (rs_box.getObject("cdvistfam"      )!= null) ls_cdvistfam        = rs_box.getString("cdvistfam"   );
                if (rs_box.getObject("cdvistv1"       )!= null) ls_cdvistv1         = rs_box.getString("cdvistv1"    );
                if (rs_box.getObject("cdvistv2"       )!= null) ls_cdvistv2         = rs_box.getString("cdvistv2"    );
                if (rs_box.getObject("cdvistv3"       )!= null) ls_cdvistv3         = rs_box.getString("cdvistv3"    );
                if (rs_box.getObject("cdvistelet"     )!= null) ls_cdvistelet       = rs_box.getString("cdvistelet"  );
            }
            
            if (!lang.equals(LANG_EN)){
                String specsheet_relpath = of_relpath_resource_specsheet(ls_cdclas_a, ep_shop_root, nome_modello, LANG_EN, cdartm, cdvistelet);

                if (!specsheet_relpath.equals("")) {
                    return new Str_risorsa_anticipo(TPRESANTICIPO_SSENEXIST, ep_portal_url +"download/" + cdartm + ".pdf?f=" + specsheet_relpath +"&tkc="+tkc +"&lang="+ LANG_EN);
                } else if (of_exist_resource_specsheet_generica(ls_cdclas_a, ep_shop_root, nome_modello, lang, ls_cdvisttp, ls_cdvistfam, ls_cdvistv1, ls_cdvistv2, ls_cdvistv3, ls_cdvistelet)){
                    specsheet_relpath = of_relpath_resource_specsheet_generica(ls_cdclas_a, ls_cdvisttp, ls_cdvistfam, ls_cdvistv1, ls_cdvistv2, ls_cdvistv3, ls_cdvistelet);
                    return new Str_risorsa_anticipo(TPRESANTICIPO_SSSGEXIST, ep_portal_url +"download/" + specsheet_relpath + ".pdf?f=" + specsheet_relpath +"&tkc="+tkc +"&lang="+ lang);
                } else if (of_exist_resource_specsheet_generica(ls_cdclas_a, ep_shop_root, nome_modello, LANG_EN, ls_cdvisttp, ls_cdvistfam, ls_cdvistv1, ls_cdvistv2, ls_cdvistv3, ls_cdvistelet)){
                    specsheet_relpath = of_relpath_resource_specsheet_generica(ls_cdclas_a, ls_cdvisttp, ls_cdvistfam, ls_cdvistv1, ls_cdvistv2, ls_cdvistv3, ls_cdvistelet);
                    return new Str_risorsa_anticipo(TPRESANTICIPO_SSSGENEXIST, ep_portal_url +"download/" + specsheet_relpath + ".pdf?f=" + specsheet_relpath +"&tkc="+tkc +"&lang="+ LANG_EN);
                }
            } else {
                if (of_exist_resource_specsheet_generica(ls_cdclas_a, ep_shop_root, nome_modello, LANG_EN, ls_cdvisttp, ls_cdvistfam, ls_cdvistv1, ls_cdvistv2, ls_cdvistv3, ls_cdvistelet)){
                    String specsheet_relpath = of_relpath_resource_specsheet_generica(ls_cdclas_a, ls_cdvisttp, ls_cdvistfam, ls_cdvistv1, ls_cdvistv2, ls_cdvistv3, cdvistelet);
                    return new Str_risorsa_anticipo(TPRESANTICIPO_SSSGEXIST, ep_portal_url +"download/" + specsheet_relpath + ".pdf?f=" + specsheet_relpath +"&tkc="+tkc +"&lang="+ LANG_EN);
                }
            }
        } else if (tiporisorsa.equals(Mrp_arch_articoli.SPECSHEET_GEN)){
            if (!lang.equals(LANG_EN)){
                String ls_cdclas_a   = of_getCdclas_a_from_relpath_resource_specsheet_generica  (url_risorse);
                String ls_cdvisttp   = of_getCdvisttp_from_relpath_resource_specsheet_generica  (url_risorse);
                String ls_cdvistfam  = of_getCdvistfam_from_relpath_resource_specsheet_generica (url_risorse);
                String ls_cdvistv1   = of_getCdvistv1_from_relpath_resource_specsheet_generica  (url_risorse);
                String ls_cdvistv2   = of_getCdvistv2_from_relpath_resource_specsheet_generica  (url_risorse);
                String ls_cdvistv3   = of_getCdvistv3_from_relpath_resource_specsheet_generica  (url_risorse);
                String ls_cdvistelet = of_getCdvistelet_from_relpath_resource_specsheet_generica(url_risorse);
                
                if (of_exist_resource_specsheet_generica(ls_cdclas_a, ep_shop_root, nome_modello, LANG_EN, ls_cdvisttp, ls_cdvistfam, ls_cdvistv1, ls_cdvistv2, ls_cdvistv3, ls_cdvistelet)){
                    String specsheet_relpath = of_relpath_resource_specsheet_generica(ls_cdclas_a, ls_cdvisttp, ls_cdvistfam, ls_cdvistv1, ls_cdvistv2, ls_cdvistv3, cdvistelet);
                    return new Str_risorsa_anticipo(TPRESANTICIPO_SGENEXIST, ep_portal_url +"download/" + specsheet_relpath + ".pdf?f=" + specsheet_relpath +"&tkc="+tkc +"&lang="+ LANG_EN);
                }
            }
        }
        
        return null;
        
    }
    

    public String cdarti       = "";
    public String tbl_primaria = "MRP_ARCH_ARTICOLI";
    
    
    public static String TECHSHEET = "TECHSHEET";
    public static String MOD3D_IGS = "MOD3D_IGS";
//    public static String MOD3D_EPRT = "MOD3D_EPRT";
    public static String MOD3D_EASM = "MOD3D_EASM";
    public static String MOD2D_DWG_PO = "MOD2D_DWG_PO";
    public static String MOD2D_DWG_CM = "MOD2D_DWG_CM";
    public static String IMAGEPROD_HI_MULTIF = "IMAGEPROD_HI_MULTIF";
    public static String IMAGEPROD_HI_SINGF = "IMAGEPROD_HI_SINGF";
    public static String IMAGEPROD_LO_MULTIF = "IMAGEPROD_LO_MULTIF";
    public static String IMAGEPROD_LO_SINGF = "IMAGEPROD_LO_SINGF";
    public static String CERT = "CERT";
    public static String SPECSHEET_IT = "SPECSHEET_IT";
    public static String SPECSHEET_US = "SPECSHEET_US";
    public static String MOD3DBLOCCHI_LED_IGS = "MOD3DBLOCCHI_LED_IGS";
    public static String MOD3DBLOCCHI_LED_EASM = "MOD3DBLOCCHI_LED_EASM";
    public static String SPECSHEET_GEN = "SPECSHEET_GEN";
    public static String SPECSHEET_SPEC = "SPECSHEET_SPEC";
    
    public static String TPRESANTICIPO_3DBLOCLED = "3DBLOCLED";
    public static String TPRESANTICIPO_SSENEXIST = "SSENEXIST";
    public static String TPRESANTICIPO_SGENEXIST = "SGENEXIST";
    public static String TPRESANTICIPO_SSSGEXIST = "SSSGEXIST";
    public static String TPRESANTICIPO_SSSGENEXIST = "SSSGENEXIST";


    
    private static final String CDCLAS_A_LL = "LL";
    private static final String CDCLAS_A_ULL = "ULL";
    private static final String CDCLAS_A_UL = "UL";
    private static final String CDCLAS_A_LOU = "LOU";
    
    private static final String LANG_EN = "en";

}

