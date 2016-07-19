package com.ateikon.common;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import com.ateikon.common.Atk_sql;
import com.ateikon.function.F_gesttab_azienda;
import com.ateikon.structure.Rec_archrubrica_tipo;
import com.ateikon.structure.Str_ep_tabset;


public class Archrubrica_tipo extends com.ateikon.standard.Archrubrica_tipo {


    public Archrubrica_tipo() {

        super();

        null_nrposi = false;
    }


    /***


    */

    public int init ( ) throws Exception {

    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        Rec_archrubrica_tipo lstr = new Rec_archrubrica_tipo();


        lstr = new Rec_archrubrica_tipo();
        lstr.tkrubr_tipo   = 0;
        lstr.cdrubr_tipo_m = "CANT";
        lstr.dsrubr_tipo   = "Cantiere";
        lstr.dsrubr_tipo_en   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_de   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_fr   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_es   = lstr.dsrubr_tipo;
        lstr.fgabil           = "N";
        lstr.nrposi           = 0;
        lstr.fgdef            = "N";
        lstr.fggest           = "N";
        tot_rec = execute(lstr);

        lstr = new Rec_archrubrica_tipo();
        lstr.tkrubr_tipo   = 0;
        lstr.cdrubr_tipo_m = "TECN";
        lstr.dsrubr_tipo   = "Tecnico";
        lstr.dsrubr_tipo_en   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_de   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_fr   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_es   = lstr.dsrubr_tipo;
        lstr.fgabil           = "N";
        lstr.nrposi           = 0;
        lstr.fgdef            = "N";
        lstr.fggest           = "N";
        tot_rec = execute(lstr);

        lstr = new Rec_archrubrica_tipo();
        lstr.tkrubr_tipo   = 0;
        lstr.cdrubr_tipo_m = "STECN";
        lstr.dsrubr_tipo   = "Studio Tecnico";
        lstr.dsrubr_tipo_en   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_de   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_fr   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_es   = lstr.dsrubr_tipo;
        lstr.fgabil           = "N";
        lstr.nrposi           = 0;
        lstr.fgdef            = "N";
        lstr.fggest           = "N";
        tot_rec = execute(lstr);

        lstr = new Rec_archrubrica_tipo();
        lstr.tkrubr_tipo   = 0;
        lstr.cdrubr_tipo_m = "COMU";
        lstr.dsrubr_tipo   = "Comune";
        lstr.dsrubr_tipo_en   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_de   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_fr   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_es   = lstr.dsrubr_tipo;
        lstr.fgabil           = "N";
        lstr.nrposi           = 0;
        lstr.fgdef            = "N";
        lstr.fggest           = "N";
        tot_rec = execute(lstr);

        lstr = new Rec_archrubrica_tipo();
        lstr.tkrubr_tipo   = 0;
        lstr.cdrubr_tipo_m = "IMPR_ESE";
        lstr.dsrubr_tipo   = "Impresa Esecutrice";
        lstr.dsrubr_tipo_en   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_de   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_fr   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_es   = lstr.dsrubr_tipo;
        lstr.fgabil           = "N";
        lstr.nrposi           = 0;
        lstr.fgdef            = "N";
        lstr.fggest           = "N";
        tot_rec = execute(lstr);

        lstr = new Rec_archrubrica_tipo();
        lstr.tkrubr_tipo   = 0;
        lstr.cdrubr_tipo_m = "IMPR_COM";
        lstr.dsrubr_tipo   = "Impresa Committente";
        lstr.dsrubr_tipo_en   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_de   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_fr   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_es   = lstr.dsrubr_tipo;
        lstr.fgabil           = "N";
        lstr.nrposi           = 0;
        lstr.fgdef            = "N";
        lstr.fggest           = "N";
        tot_rec = execute(lstr);

        lstr = new Rec_archrubrica_tipo();
        lstr.tkrubr_tipo   = 0;
        lstr.cdrubr_tipo_m = "CLIEP";
        lstr.dsrubr_tipo   = "Cliente Potenziale";
        lstr.dsrubr_tipo_en   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_de   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_fr   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_es   = lstr.dsrubr_tipo;
        lstr.fgabil           = "N";
        lstr.nrposi           = 0;
        lstr.fgdef            = "N";
        lstr.fggest           = "N";
        tot_rec = execute(lstr);

        lstr = new Rec_archrubrica_tipo();
        lstr.tkrubr_tipo   = 0;
        lstr.cdrubr_tipo_m = "TITOL";
        lstr.dsrubr_tipo   = "Titolare";
        lstr.dsrubr_tipo_en   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_de   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_fr   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_es   = lstr.dsrubr_tipo;
        lstr.fgabil           = "N";
        lstr.nrposi           = 0;
        lstr.fgdef            = "N";
        lstr.fggest           = "N";
        tot_rec = execute(lstr);

        lstr = new Rec_archrubrica_tipo();
        lstr.tkrubr_tipo   = 0;
        lstr.cdrubr_tipo_m = "RESPAMM";
        lstr.dsrubr_tipo   = "Responsabile Amministrativo";
        lstr.dsrubr_tipo_en   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_de   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_fr   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_es   = lstr.dsrubr_tipo;
        lstr.fgabil           = "N";
        lstr.nrposi           = 0;
        lstr.fgdef            = "N";
        lstr.fggest           = "N";
        tot_rec = execute(lstr);

        lstr = new Rec_archrubrica_tipo();
        lstr.tkrubr_tipo   = 0;
        lstr.cdrubr_tipo_m = "RESPACQ";
        lstr.dsrubr_tipo   = "Responsabile Acquisti";
        lstr.dsrubr_tipo_en   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_de   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_fr   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_es   = lstr.dsrubr_tipo;
        lstr.fgabil           = "N";
        lstr.nrposi           = 0;
        lstr.fgdef            = "N";
        lstr.fggest           = "N";
        tot_rec = execute(lstr);

        lstr = new Rec_archrubrica_tipo();

        lstr.tkrubr_tipo   = 0;
        lstr.cdrubr_tipo_m = "AMMDEL";
        lstr.dsrubr_tipo   = "Amministratore Delegato";
        lstr.dsrubr_tipo_en   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_de   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_fr   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_es   = lstr.dsrubr_tipo;
        lstr.fgabil           = "N";
        lstr.nrposi           = 0;
        lstr.fgdef            = "N";
        lstr.fggest           = "N";
        tot_rec = execute(lstr);

        lstr = new Rec_archrubrica_tipo();
        lstr.tkrubr_tipo   = 0;
        lstr.cdrubr_tipo_m = "RESPVEN";
        lstr.dsrubr_tipo   = "Responsabile Vendite";
        lstr.dsrubr_tipo_en   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_de   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_fr   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_es   = lstr.dsrubr_tipo;
        lstr.fgabil           = "N";
        lstr.nrposi           = 0;
        lstr.fgdef            = "N";
        lstr.fggest           = "N";
        tot_rec = execute(lstr);

        lstr = new Rec_archrubrica_tipo();
        lstr.tkrubr_tipo   = 0;
        lstr.cdrubr_tipo_m = "RESPMAG";
        lstr.dsrubr_tipo   = "Responsabile Magazzino";
        lstr.dsrubr_tipo_en   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_de   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_fr   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_es   = lstr.dsrubr_tipo;
        lstr.fgabil           = "N";
        lstr.nrposi           = 0;
        lstr.fgdef            = "N";
        lstr.fggest           = "N";
        tot_rec = execute(lstr);

        lstr = new Rec_archrubrica_tipo();
        lstr.tkrubr_tipo   = 0;
        lstr.cdrubr_tipo_m = "GEOM";
        lstr.dsrubr_tipo   = "Geometra";
        lstr.dsrubr_tipo_en   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_de   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_fr   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_es   = lstr.dsrubr_tipo;
        lstr.fgabil           = "N";
        lstr.nrposi           = 0;
        lstr.fgdef            = "N";
        lstr.fggest           = "N";
        tot_rec = execute(lstr);

        lstr = new Rec_archrubrica_tipo();
        lstr.tkrubr_tipo   = 0;
        lstr.cdrubr_tipo_m = "CLIEGEFF";
        lstr.dsrubr_tipo   = "Cliente Gestionale Effettivo";
        lstr.dsrubr_tipo_en   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_de   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_fr   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_es   = lstr.dsrubr_tipo;
        lstr.fgabil           = "N";
        lstr.nrposi           = 0;
        lstr.fgdef            = "N";
        lstr.fggest           = "S";
        tot_rec = execute(lstr);

        lstr = new Rec_archrubrica_tipo();
        lstr.tkrubr_tipo   = 0;
        lstr.cdrubr_tipo_m = "CLIEGPOT";
        lstr.dsrubr_tipo   = "Cliente Gestionale Potenziale";
        lstr.dsrubr_tipo_en   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_de   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_fr   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_es   = lstr.dsrubr_tipo;
        lstr.fgabil           = "N";
        lstr.nrposi           = 0;
        lstr.fgdef            = "N";
        lstr.fggest           = "S";
        tot_rec = execute(lstr);

        lstr = new Rec_archrubrica_tipo();
        lstr.tkrubr_tipo   = 0;
        lstr.cdrubr_tipo_m = "CANTG";
        lstr.dsrubr_tipo   = "Cantiere Gestionale";
        lstr.dsrubr_tipo_en   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_de   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_fr   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_es   = lstr.dsrubr_tipo;
        lstr.fgabil           = "N";
        lstr.nrposi           = 0;
        lstr.fgdef            = "N";
        lstr.fggest           = "S";
        tot_rec = execute(lstr);

        lstr = new Rec_archrubrica_tipo();
        lstr.tkrubr_tipo   = 0;
        lstr.cdrubr_tipo_m = "RESPLEG";
        lstr.dsrubr_tipo   = "Responsabile Legale";
        lstr.dsrubr_tipo_en   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_de   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_fr   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_es   = lstr.dsrubr_tipo;
        lstr.fgabil           = "N";
        lstr.nrposi           = 0;
        lstr.fgdef            = "N";
        lstr.fggest           = "N";
        tot_rec = execute(lstr);

        lstr = new Rec_archrubrica_tipo();
        lstr.tkrubr_tipo   = 0;
        lstr.cdrubr_tipo_m = "FIDEIUS";
        lstr.dsrubr_tipo   = "Fideiussore";
        lstr.dsrubr_tipo_en   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_de   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_fr   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_es   = lstr.dsrubr_tipo;
        lstr.fgabil           = "N";
        lstr.nrposi           = 0;
        lstr.fgdef            = "N";
        lstr.fggest           = "N";
        tot_rec = execute(lstr);

        lstr = new Rec_archrubrica_tipo();
        lstr.tkrubr_tipo   = 0;
        lstr.cdrubr_tipo_m = "ASSOCIAZ";
        lstr.dsrubr_tipo   = "Associazione";
        lstr.dsrubr_tipo_en   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_de   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_fr   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_es   = lstr.dsrubr_tipo;
        lstr.fgabil           = "N";
        lstr.nrposi           = 0;
        lstr.fgdef            = "N";
        lstr.fggest           = "N";
        tot_rec = execute(lstr);

        lstr = new Rec_archrubrica_tipo();
        lstr.tkrubr_tipo   = 0;
        lstr.cdrubr_tipo_m = "RESPEDP";
        lstr.dsrubr_tipo   = "Responsabile EDP";
        lstr.dsrubr_tipo_en   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_de   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_fr   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_es   = lstr.dsrubr_tipo;
        lstr.fgabil           = "N";
        lstr.nrposi           = 0;
        lstr.fgdef            = "N";
        lstr.fggest           = "N";
        tot_rec = execute(lstr);

        //avendramin20110330 - I
        lstr = new Rec_archrubrica_tipo();
        lstr.tkrubr_tipo   = 0;
        lstr.cdrubr_tipo_m = "PROSPECT";
        lstr.dsrubr_tipo   = "Prospect";
        lstr.dsrubr_tipo_en   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_de   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_fr   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_es   = lstr.dsrubr_tipo;
        lstr.fgabil           = "N";
        lstr.nrposi           = 0;
        lstr.fgdef            = "N";
        lstr.fggest           = "N";
        tot_rec = execute(lstr);
        //avendramin20110330 - F


        lstr = new Rec_archrubrica_tipo();
        lstr.tkrubr_tipo   = 0;
        lstr.cdrubr_tipo_m = "OTHER";
        lstr.dsrubr_tipo   = "Altro";
        lstr.dsrubr_tipo_en   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_de   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_fr   = lstr.dsrubr_tipo;
        lstr.dsrubr_tipo_es   = lstr.dsrubr_tipo;
        lstr.fgabil           = "N";
        lstr.nrposi           = 99999;
        lstr.fgdef            = "N";
        lstr.fggest           = "N";
        tot_rec = execute(lstr);


        return 1;

    }



    public int execute ( com.ateikon.structure.Rec_archrubrica_tipo astr) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        l_query  = "";
        l_query += " select tkrubr_tipo       \n";
        l_query += "      , fgabil             \n";
        l_query += "      , nrposi             \n";
        l_query += "      , fgdef              \n";
        l_query += "      , tkrif_name              \n";
        l_query += "      , dsrubr_tipo              \n";
        l_query += "      , dsrubr_tipo_en              \n";
        l_query += "      , dsrubr_tipo_de              \n";
        l_query += "      , dsrubr_tipo_fr              \n";
        l_query += "      , dsrubr_tipo_es              \n";
        l_query += "      , fgofferta              \n";
        l_query += "      , fgtrattativa              \n";
        l_query += "   from pgmr.archrubrica_tipo   \n";
        l_query += "  where cdrubr_tipo_m = ? \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;
        pstm.setString(ind++, astr.cdrubr_tipo_m);

        rs = pstm.executeQuery();

        if (rs!=null && rs.next()){

            if (rs.getObject("tkrubr_tipo")!=null)  astr.tkrubr_tipo = rs.getLong("tkrubr_tipo");
            if (rs.getObject("fgabil")!=null)  astr.fgabil = rs.getString("fgabil");
            if (rs.getObject("nrposi")!=null)  astr.nrposi = rs.getLong  ("nrposi");
            if (rs.getObject("fgdef" )!=null)  astr.fgdef  = rs.getString("fgdef" );
            if (rs.getObject("tkrif_name" )!=null)  astr.tkrif_name  = rs.getString("tkrif_name" );
            if (rs.getObject("dsrubr_tipo"  )!=null)  astr.dsrubr_tipo   = rs.getString("dsrubr_tipo"  );
            if (rs.getObject("dsrubr_tipo_en"  )!=null)  astr.dsrubr_tipo_en   = rs.getString("dsrubr_tipo_en"  );
            if (rs.getObject("dsrubr_tipo_de"  )!=null)  astr.dsrubr_tipo_de   = rs.getString("dsrubr_tipo_de"  );
            if (rs.getObject("dsrubr_tipo_fr"  )!=null)  astr.dsrubr_tipo_fr   = rs.getString("dsrubr_tipo_fr"  );
            if (rs.getObject("dsrubr_tipo_es"  )!=null)  astr.dsrubr_tipo_es   = rs.getString("dsrubr_tipo_es"  );
            if (rs.getObject("fgofferta"    )!=null)  astr.fgofferta     = rs.getString("fgofferta"    );
            if (rs.getObject("fgtrattativa" )!=null)  astr.fgtrattativa  = rs.getString("fgtrattativa" );

        }
        pstm.close();


        tot_rec = super.execute(astr);

        if (tot_rec == 1){
            m_connection.commit();
        } else {
            m_connection.rollback();
            throw new Exception("Errore Agg. archrubrica_tipo! ");
        }

        return tot_rec ;

    }


    /***


    */

    public int setFgabil (long tkeplog_tipo, String fgabil ) throws Exception {

    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;


        l_query  = "";
        l_query += " update pgmr.archrubrica_tipo  \n";
        l_query += "    set fgabil = ?        \n";
        l_query += "      , profil = ?        \n";
        l_query += "      , dtulag = ?        \n";
        l_query += "  where tkrubr_tipo = ?  \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;
        pstm.setString   (ind++, fgabil);
        pstm.setString   (ind++, profil);
        pstm.setTimestamp(ind++, oggi  );
        pstm.setLong     (ind++, tkrubr_tipo);

        tot_rec = pstm.executeUpdate();
        pstm.close();

        return tot_rec ;

    }


    public ResultSet getDropdown( String cdling
                                , boolean is_count
                                , String order_by
                                ) throws Exception {

           return getDropdown(  ""
                              , cdling
                              , is_count
                              , order_by
                             );
    }

    public ResultSet getDropdown( String fggest
                                , String cdling
                                , boolean is_count
                                , String order_by
                                ) throws Exception {

        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs      = null;

        if (order_by.equals("")) order_by = "rtip.nrposi, "+ of_descr_lingua(cdling, "rtip", "dsrubr_tipo") +" ";

        if (is_count) order_by = "";

        if (is_count){
           l_query   = "";
           l_query  += " select count (*)   \n";
        } else {
           l_query   = "";
           l_query  += " select rtip.tkrubr_tipo, "+ of_descr_lingua(cdling, "rtip", "dsrubr_tipo") +" as dsrubr_tipo   \n";
        }
        l_query  += "   from pgmr.archrubrica_tipo rtip    \n";
        l_query  += "  where rtip.fgabil = 'S'              \n";
        if (fggest.equals("S")){
           l_query  += "    and rtip.fggest = 'S'              \n";
        } else if (fggest.equals("N")){
           l_query  += "    and rtip.fggest = 'N'              \n";
        }
        l_query  += par_azieDipa("archrubrica_tipo");

        if (!order_by.equals("")){
            l_query  += "   order by "+order_by+"  \n";
        }

        //System.out.println(l_query);

        pstm = setQuery( l_query ) ;

        rs = pstm.executeQuery();


        return rs;
    }


    /****
        getCdrubr_tipo_m: archrubrica_tipo
    **/

    public String getCdrubr_tipo_m( long       tkrubr_tipo
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select rtip.cdrubr_tipo_m \n";
        l_query  += "   from pgmr.archrubrica_tipo rtip \n";
        l_query  += "  where rtip.tkrubr_tipo = "+ tkrubr_tipo +"  \n";
        l_query  += par_azieDipa("archrubrica_tipo");

        return sql_String(l_query);
    }

    /****
        getDsrubr_tipo: archrubrica_tipo
    **/

    public String getDsrubr_tipo( long       tkrubr_tipo
                                           ) throws Exception {

        return getDsrubr_tipo(tkrubr_tipo, "");
    }

    /****
        getDsrubr_tipo: archrubrica_tipo
    **/

    public String getDsrubr_tipo( long       tkrubr_tipo
                                , String     cdling
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select "+ of_descr_lingua(cdling, "rtip", "dsrubr_tipo") +" \n";
        l_query  += "   from pgmr.archrubrica_tipo rtip \n";
        l_query  += "  where rtip.tkrubr_tipo = "+ tkrubr_tipo +"  \n";
        l_query  += par_azieDipa("archrubrica_tipo");

        return sql_String(l_query);
    }

    /****
        getDefault: archrubrica_tipo
    **/

    public long getDefault(  ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        long ll_ = 0;

        l_query   = "";
        l_query   = " select rtip.tkrubr_tipo                          \n";
        l_query  += "   from pgmr.archrubrica_tipo rtip                \n";
        l_query  += "  where rtip.fgabil = 'S'                         \n";
        l_query  += "    and rtip.fgdef  = 'S'                         \n";
        l_query  += "    and rtip.fggest = 'N'                         \n";
        l_query  += par_azieDipa("archrubrica_tipo");

        ll_ = sql_long(l_query);

        //System.out.println("ll_ "+ ll_);

        return ll_;
    }

    /****
        getKey: archrubrica_tipo
    **/

    public long getKey_m( String       cdrubr_tipo_m
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        long ll_ = 0;

        l_query   = "";
        l_query  += " select rtip.tkrubr_tipo                               \n";
        l_query  += "   from pgmr.archrubrica_tipo rtip                   \n";
        l_query  += "  where rtip.cdrubr_tipo_m = '"+ cdrubr_tipo_m +"'  \n";
        l_query  += par_azieDipa("archrubrica_tipo");

        ll_ = sql_long(l_query);

        return ll_;

    }

    /****
        isGestionale: archrubrica_tipo
    **/

    public String isGestionale( long       tkrubr_tipo
                                           ) throws Exception {
        int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs = null;

        l_query   = "";
        l_query  += " select rtip.fggest \n";
        l_query  += "   from pgmr.archrubrica_tipo rtip \n";
        l_query  += "  where rtip.tkrubr_tipo = "+ tkrubr_tipo +"  \n";

        return sql_String(l_query);
    }

    public String par_azieDipa(String as_table) throws Exception{


        String ls_alias = "";

        as_table = as_table.trim().toLowerCase();

        if (as_table.equals("archrubrica_tipo")) ls_alias = "rtip";

        return par_azieDipa(as_table, ls_alias);
    }

    public String par_azieDipa(String as_table, String as_alias) throws Exception{

        ResultSet         rs   = null;

        F_gesttab_azienda f_gesttab_azienda = new F_gesttab_azienda();

        setProfilo((Atk_sql) f_gesttab_azienda);


        as_table = as_table.trim().toLowerCase();

        boolean lb_fazienda      = false;
        boolean lb_fazienda_user = false;

        Str_ep_tabset lstr_ep_tabset = new Str_ep_tabset ();

        lstr_ep_tabset = f_gesttab_azienda.getEp_tabset(as_table);

        if (!lstr_ep_tabset.fazienda.equals("S")) lstr_ep_tabset.fazienda = "N";

        if (lstr_ep_tabset.fazienda.equals("S")){
            lb_fazienda = false;
        }else {
            lb_fazienda = true;
        }

        if (!lstr_ep_tabset.fazienda_user.equals("S")) lstr_ep_tabset.fazienda_user = "N";

        if (lstr_ep_tabset.fazienda_user.equals("S")){
            lb_fazienda_user = false;
        }else {
            lb_fazienda_user = true;
        }

        String ls_ = "";
        if (!as_alias.equals("")) as_alias = as_alias+".";

        String ls_cddipa_concrete = "";

        if (lstr_ep_tabset.fconcrete.equals("S")){

            l_query  = "";
            l_query += " select cddipa                   \n";
            l_query += "   from pgmr.azienda             \n";
            l_query += "  where cdazie = '"+cdazie+"'  \n";

            ls_cddipa_concrete = sql_String(l_query);

        }




        if (lstr_ep_tabset.fconcrete.equals("S")){

            ls_  += "   and "+as_alias+"cdazie = '"+cdazie+"'                 \n";
            ls_  += "   and "+as_alias+"cddipa = '"+ls_cddipa_concrete+"'   \n";

        }else if (lb_fazienda_user){

            //In caso abbia s_tkutente è un utente altrimenti è un servizio
            if (s_tkutente > 0){
               ls_  += "   and "+as_alias+"cdazie in (select iuten.cdazie_comp                 \n";
               ls_  += "                                from pgmr.ep_utente_azie iuten         \n";
               ls_  += "                               where iuten.tkutente = "+s_tkutente+"   \n";
               ls_  += "                                       )                               \n";
            }

        }else if (lb_fazienda){
            ls_  += "    and "+as_alias+"cdazie = '"+cdazie+"' \n";
        }


        // ---- Gestione sicurezza



        return ls_;
    }

}

