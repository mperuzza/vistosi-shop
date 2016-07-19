package com.ateikon.common;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class Unitalocali extends Atk_sql {

    public Unitalocali() {
        
        super();

    }



    public ResultSet getSede_legale(String cdente) throws Exception {
        
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        this.setCostanti();


        l_query   = "";
		l_query  += "  select a.cdusul                    \n";
		l_query  += "       , b.cdunil                    \n";
		l_query  += "       , b.cdunil_m                  \n";
		l_query  += "       , b.dsunil                    \n";
		l_query  += "       , b.indiri                    \n";
		l_query  += "       , b.cap                       \n";
		l_query  += "       , b.comune                    \n";
		l_query  += "       , b.cdprov                    \n";
		l_query  += "       , b.cdnazi                    \n";
		l_query  += "       , b.ntelef                    \n";
		l_query  += "       , b.nfaxsi                    \n";
		l_query  += "       , b.email                     \n";
		l_query  += "    from pgmr.enteuniloc    a    \n";
		l_query  += "       , pgmr.unitalocali   b    \n";
		l_query  += "   where a.cdunil = b.cdunil         \n";
		l_query  += "     and a.cdente = ?                \n";
		l_query  += "     and a.fseleg = 'S'              \n";
		l_query  += "   order by b.dsunil                 \n";


		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString( ind, cdente ); ind += 1;

        rs = pstm.executeQuery();


        return rs;


    }






    public ResultSet getDest_merce(String cdente) throws Exception {
        
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        this.setCostanti();


        l_query   = "";
		l_query  += "  select a.cdusul                    \n";
		l_query  += "       , b.cdunil                    \n";
		l_query  += "       , b.cdunil_m                  \n";
		l_query  += "       , b.dsunil                    \n";
		l_query  += "       , b.indiri                    \n";
		l_query  += "       , b.cap                       \n";
		l_query  += "       , b.comune                    \n";
		l_query  += "       , b.cdprov                    \n";
		l_query  += "       , b.cdnazi                    \n";
		l_query  += "       , b.ntelef                    \n";
		l_query  += "       , b.nfaxsi                    \n";
		l_query  += "       , b.email                     \n";
		l_query  += "    from pgmr.enteuniloc    a    \n";
		l_query  += "       , pgmr.unitalocali   b    \n";
		l_query  += "   where a.cdunil = b.cdunil         \n";
		l_query  += "     and a.cdente = ?                \n";
		l_query  += "     and a.cdusul = ?                \n";
		l_query  += "     and a.fseleg <> 'S'             \n";
		l_query  += "   order by b.dsunil                 \n";


		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString( ind, cdente ); ind += 1;
        pstm.setString( ind, K_CDUSUL_DM ); ind += 1;

        rs = pstm.executeQuery();


        return rs;


    }




    /***


    */

    public int setCdunil ( String cdunil
                         , com.ateikon.structure.Str_dest_merce astr
                                        ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        tot_rec = -1;
        
        l_query  = "";                                          
        l_query += "  select uloc.indiri         as indiri_dm                                                      \n";
        l_query += "       , uloc.cap            as cap_dm                                                         \n";
        l_query += "       , uloc.comune         as comune_dm                                                      \n";
        l_query += "       , uloc.cdprov         as cdprov_dm                                                      \n";
        l_query += "       , uloc.cdnazi         as cdnazi_dm                                                      \n";
        l_query += "       , prov_dm.cdprov_m    as cdprov_m_dm                                                    \n";
        l_query += "       , prov_dm.dsprov      as dsprov_dm                                                      \n";
        l_query += "       , nazi_dm.cdnazi_m    as cdnazi_m_dm                                                    \n";
        l_query += "       , nazi_dm.dsnazi      as dsnazi_dm                                                      \n";
        l_query += "    from { oj  pgmr.unitalocali   uloc                                                         \n";
        l_query += "           left outer join pgmr.nazioni       nazi_dm on uloc.cdnazi    = nazi_dm.cdnazi       \n";
        l_query += "           left outer join pgmr.province      prov_dm on uloc.cdprov    = prov_dm.cdprov       \n";
        l_query += "           }                                                                                   \n";
        l_query += "   where uloc.cdunil = '"+cdunil+"'                                                            \n";

        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){
        
            tot_rec = 1;

            astr.cduldm        = cdunil;
    
            astr.indiri_dm     = "";
            astr.cap_dm        = "";
            astr.comune_dm     = "";
            astr.cdprov_dm     = "";
            astr.cdnazi_dm     = "";
    
            astr.cdprov_m_dm   = "";
            astr.dsprov_dm     = "";
            astr.cdnazi_m_dm   = "";
            astr.dsnazi_dm     = "";


            if (rs.getObject("indiri_dm"  )!=null) astr.indiri_dm   = rs.getString("indiri_dm"  );
            if (rs.getObject("cap_dm"     )!=null) astr.cap_dm      = rs.getString("cap_dm"     );
            if (rs.getObject("comune_dm"  )!=null) astr.comune_dm   = rs.getString("comune_dm"  );
            if (rs.getObject("cdprov_dm"  )!=null) astr.cdprov_dm   = rs.getString("cdprov_dm"  );
            if (rs.getObject("cdnazi_dm"  )!=null) astr.cdnazi_dm   = rs.getString("cdnazi_dm"  );
            if (rs.getObject("cdprov_m_dm")!=null) astr.cdprov_m_dm = rs.getString("cdprov_m_dm");
            if (rs.getObject("dsprov_dm"  )!=null) astr.dsprov_dm   = rs.getString("dsprov_dm"  );
            if (rs.getObject("cdnazi_m_dm")!=null) astr.cdnazi_m_dm = rs.getString("cdnazi_m_dm");
            if (rs.getObject("dsnazi_dm"  )!=null) astr.dsnazi_dm   = rs.getString("dsnazi_dm"  );

        }
        pstm.close();




        return tot_rec;

    }




    /***


    */

    public String getDescr_uloc ( String cdunil
                                        ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        String ls_descr = "";

        
        l_query  = "";                                          
        l_query += "  select uloc.indiri      \n";
        l_query += "       , uloc.cap         \n";
        l_query += "       , uloc.comune      \n";
        l_query += "       , uloc.cdprov      \n";
        l_query += "       , uloc.cdnazi      \n";
        l_query += "       , uloc.ntelef      \n";
        l_query += "       , uloc.nfaxsi      \n";
        l_query += "       , prov.cdprov_m    \n";
        l_query += "       , prov.dsprov      \n";
        l_query += "       , nazi.cdnazi_m    \n";
        l_query += "       , nazi.dsnazi      \n";
        l_query += "    from { oj  pgmr.unitalocali   uloc                                             \n";
        l_query += "           left outer join pgmr.nazioni       nazi on uloc.cdnazi    = nazi.cdnazi \n";
        l_query += "           left outer join pgmr.province      prov on uloc.cdprov    = prov.cdprov \n";
        l_query += "           }                                                                       \n";
        l_query += "   where uloc.cdunil = '"+cdunil+"'                                                \n";

        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){
        
            String indiri     = "";
            String cap        = "";
            String comune     = "";
            String cdprov     = "";
            String cdnazi     = "";
    
            String cdprov_m   = "";
            String dsprov     = "";
            String cdnazi_m   = "";
            String dsnazi     = "";
            String ntelef     = "";
            String nfaxsi     = "";


            if (rs.getObject("indiri"  )!=null) indiri   = rs.getString("indiri"  );
            if (rs.getObject("cap"     )!=null) cap      = rs.getString("cap"     );
            if (rs.getObject("comune"  )!=null) comune   = rs.getString("comune"  );
            if (rs.getObject("cdprov"  )!=null) cdprov   = rs.getString("cdprov"  );
            if (rs.getObject("cdnazi"  )!=null) cdnazi   = rs.getString("cdnazi"  );
            if (rs.getObject("cdprov_m")!=null) cdprov_m = rs.getString("cdprov_m");
            if (rs.getObject("dsprov"  )!=null) dsprov   = rs.getString("dsprov"  );
            if (rs.getObject("cdnazi_m")!=null) cdnazi_m = rs.getString("cdnazi_m");
            if (rs.getObject("dsnazi"  )!=null) dsnazi   = rs.getString("dsnazi"  );
            if (rs.getObject("ntelef"  )!=null) ntelef   = rs.getString("ntelef"  );
            if (rs.getObject("nfaxsi"  )!=null) nfaxsi   = rs.getString("nfaxsi"  );

            ls_descr += indiri+"\n";
            ls_descr += cap+" "+comune+((!cdprov_m.equals(""))?" ("+cdprov_m+")":"")+"\n";
            if (!ntelef.equals("") || !nfaxsi.equals("")){
                ls_descr += ((!ntelef.equals(""))?"Tel."+ntelef:"")+((!nfaxsi.equals(""))?" Fax."+nfaxsi:"")+"\n";
            }
        }
        pstm.close();

        return ls_descr;

    }




    public ResultSet getDest_conf_ordi(String cdente) throws Exception {
        
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;
        
        
        this.setCostanti();


        l_query   = "";
		l_query  += "  select a.cdusul                    \n";
		l_query  += "       , b.cdunil                    \n";
		l_query  += "       , b.cdunil_m                  \n";
		l_query  += "       , b.dsunil                    \n";
		l_query  += "       , b.indiri                    \n";
		l_query  += "       , b.cap                       \n";
		l_query  += "       , b.comune                    \n";
		l_query  += "       , b.cdprov                    \n";
		l_query  += "       , b.cdnazi                    \n";
		l_query  += "       , b.ntelef                    \n";
		l_query  += "       , b.nfaxsi                    \n";
		l_query  += "       , b.email                     \n";
		l_query  += "    from pgmr.enteuniloc    a    \n";
		l_query  += "       , pgmr.unitalocali   b    \n";
		l_query  += "   where a.cdunil = b.cdunil         \n";
		l_query  += "     and a.cdente = ?                \n";
		l_query  += "     and a.cdusul = ?                \n";
		l_query  += "     and a.fseleg <> 'S'             \n";
		l_query  += "   order by b.dsunil                 \n";


		pstm = setQuery( l_query ) ;

        ind = 1;
        pstm.setString( ind, cdente ); ind += 1;
        pstm.setString( ind, K_CDUSUL_IC ); ind += 1;

        rs = pstm.executeQuery();


        return rs;


    }





    public void setCostanti() throws Exception {

        // imposto le costanti dal web

        Atk_pargen atk_pargen = new Atk_pargen();

        setProfilo((Atk_sql)atk_pargen);

        
        if (this.K_CDUSUL_OK.equals("OK")) return;
        
        //   Qualificazione Unita Locale = Invio Conferma Ordine

        this.K_CDUSUL_IC = atk_pargen.getCostvalue("K_CDUSUL_IC");
        

        //  Qualificazione Unita Locale = Destinazione Merce

        this.K_CDUSUL_DM = atk_pargen.getCostvalue("K_CDUSUL_DM");

        // Qualificazione Unita Locale = Sede Legale

        this.K_CDUSUL_SL = atk_pargen.getCostvalue("K_CDUSUL_SL");

        this.K_CDUSUL_OK = "OK";


    }




    public String K_CDUSUL_IC = "";
    public String K_CDUSUL_DM = "";
    public String K_CDUSUL_SL = "";


    private String K_CDUSUL_OK = "";


}
