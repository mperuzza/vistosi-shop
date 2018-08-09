package com.ateikon.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

import java.util.Vector;

public class Atk_sql extends Object implements java.io.Serializable {

    public Atk_sql() {

        super();
    }

    public void dbConnection() throws Exception {


        java.util.Properties props = new java.util.Properties();

        String user = "";
        String password = "";


        this.m_connection = null;


        try {

            props.load(getClass().getResourceAsStream("/ateikon_sql.properties"));

            this.url = props.getProperty("jdbc.url");
            this.cdappl = props.getProperty("cdappl");
            this.cdagen_appl = props.getProperty("cdagen_appl");


        } catch (Exception e) {

            System.err.println("");
            System.err.println("");
            System.err.println("================================================================================");
            System.err.println("    DB. Connection");
            System.err.println("================================================================================");
            System.err.println("");
            System.err.println("Error in properties file: /ateikon_sql.properties:");
            System.err.println("");
            System.err.println("");
            System.err.println("");
            System.err.println("");

            throw e;

        }


        if (cdagen_appl.equals("")) {
            cdagen_appl = "WEB";
        }

        //*** Imposto il Driver name in base all'URL

        this.is_sybase = false;
        this.is_oracle = false;
        this.is_sqlserver = false;
        this.is_postgresql = false;


        // imposto il Driver

        if (this.url.indexOf("oracle") >= 0) {

            this.driverName = "oracle.jdbc.driver.OracleDriver";

            this.is_oracle = true;

        } else if (this.url.indexOf("sybase") >= 0) {

            this.driverName = "com.sybase.jdbc3.jdbc.SybDriver";

            this.is_sybase = true;
            
        }else if (this.url.indexOf("sqlserver")>=0){
            
            this.driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

            this.is_sqlserver = true;

        }else if (this.url.indexOf("postgresql")>=0){

            this.driverName = "org.postgresql.Driver";

            this.is_postgresql = true;    

        } else {

            System.err.println("");
            System.err.println("");
            System.err.println("================================================================================");
            System.err.println("    DB. Connection");
            System.err.println("================================================================================");
            System.err.println("");
            System.err.println("Error jdbc.url NON riconosciuta: ");
            System.err.println("");
            System.err.println("      " + this.url);
            System.err.println("");
            System.err.println("");

            throw new Exception("Tipo DB non previsto " + this.url);
        }

        try {

            user = props.getProperty("jdbc.username");
            password = props.getProperty("jdbc.password");

        } catch (Exception e) {

            user = "";
            password = "";
        }

        // se la password non � specificata 
        // imposto quella di default

        if (user.equals("")) {
            user = "pgmr";
            password = "admin";
        }



        if (this.is_sybase) {

            this.sysdate = "current timestamp";
            this.dummy_table = "dummy";

        } else if (this.is_oracle) {

            this.sysdate = "sysdate";
            this.dummy_table = "dual";

        }else if ( this.is_sqlserver ){

            this.sysdate = "current_timestamp";
            this.dummy_table = ""; //NON ha la possibilitï¿½ di gestire una dummy_table, eventualmente bisogna creare una tabella di appoggio

        }else if ( this.is_postgresql ){

            this.sysdate = "current_timestamp";
            this.dummy_table = "";//Nota per gestire questo PostgreSQL non usa la keyword FROM (ES. select '1')
        }


        Class.forName(driverName);
        m_connection = DriverManager.getConnection(url, user, password);

        m_connection.setAutoCommit(false);


        m_connection.setAutoCommit( false );
        m_connection.setTransactionIsolation( Connection.TRANSACTION_READ_COMMITTED );

    }

    public void closeConnection() throws Exception {

        if (m_connection != null) {
            m_connection.close();
        }

        m_connection = null;
    }

    public PreparedStatement setQuery(String as_sql) throws Exception {

        int idx = vec_sql.indexOf(as_sql);


        if (idx < 0) {

            PreparedStatement pstm = m_connection.prepareStatement(as_sql);

            vec_sql.add(as_sql);
            vec_pstm.add(pstm);

            return pstm;

        } else {

            return (PreparedStatement) vec_pstm.elementAt(idx);

        }

    }

    public PreparedStatement setQuery_ric(String as_sql) throws Exception {

        int idx = vec_sql.indexOf(as_sql);


        if (idx < 0) {
            String ls_ = "";

            PreparedStatement pstm = m_connection.prepareStatement(as_sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            vec_sql.add(as_sql);
            vec_pstm.add(pstm);

            if ( this.is_oracle ){
                pstm.setFetchSize(50);
            }

            return pstm;

        } else {

            return (PreparedStatement) vec_pstm.elementAt(idx);

        }

    }

    public void close() throws Exception {


        PreparedStatement pstm = null;

        int tot_idx = vec_pstm.size();

        for (int i = 0; i < tot_idx; i++) {


            pstm = (PreparedStatement) vec_pstm.elementAt(i);

            pstm.close();
            pstm = null;



        }

        vec_pstm = null;
        vec_sql = null;

        vec_pstm = new Vector();
        vec_sql = new Vector();




    }

    /**
     * **
     *
     * Questo metodo setta il profilo di una classe che eredita Atk_sql
     *
     */
    // con questo metodo imposto la connessione e i 
    // parametri iniziali delle classi che utilizzano la stessa connessione
    public void setProfilo(Atk_sql atk_sql) throws Exception {

        // 
        // le connessioni vengono settate solo se non 
        // sono a null; se la connessione � <> NULL
        // significa che ho aperto un'altra connessione 
        // es. vedi keypool
        // 

        if (atk_sql.m_connection == null) {

            atk_sql.m_connection = this.m_connection;

            atk_sql.driverName = this.driverName;
            atk_sql.url = this.url;
            atk_sql.cdappl = this.cdappl;
            atk_sql.cdagen_appl = this.cdagen_appl;


            atk_sql.sysdate = this.sysdate;
            atk_sql.dummy_table = this.dummy_table;

            atk_sql.is_sybase = this.is_sybase;
            atk_sql.is_oracle = this.is_oracle;
            atk_sql.is_sqlserver  = this.is_sqlserver   ;        
            atk_sql.is_postgresql     = this.is_postgresql   ;
        }


        atk_sql.cdazie = this.cdazie;
        atk_sql.cddipa = this.cddipa;
        atk_sql.profil = this.profil;
        atk_sql.oggi = new Timestamp(System.currentTimeMillis());
        atk_sql.dtinse = new Timestamp(System.currentTimeMillis());
        atk_sql.dtulag = new Timestamp(System.currentTimeMillis());

        atk_sql.s_cdente = this.s_cdente;
        atk_sql.s_cdagen = this.s_cdagen;
        atk_sql.s_cdcapoarea = this.s_cdcapoarea;
        atk_sql.s_cdispe = this.s_cdispe; //avendramin20080428 
        atk_sql.s_tkclie = this.s_tkclie;
        atk_sql.s_tkforn = this.s_tkforn;
        atk_sql.s_cdutente = this.s_cdutente;
        atk_sql.s_tksubutente = this.s_tksubutente;//avendramin20110318
        atk_sql.s_arr_cdunil = this.s_arr_cdunil;//avendramin20110623
        atk_sql.s_tkutente = this.s_tkutente;
        atk_sql.s_fgadmin = this.s_fgadmin;
        atk_sql.s_user_name = this.s_user_name;
        atk_sql.s_cdling = this.s_cdling;//avendramin20110718
        atk_sql.s_fgmod_trad = this.s_fgmod_trad;//avendramin20110718
        atk_sql.s_cdresp = this.s_cdresp;


    }

    /**
     * **
     *
     * Questo metodo prende il profilo di una classe che eredita Atk_sql
     *
     */
    public void getProfilo(Atk_sql atk_sql) throws Exception {

        // 
        // le connessioni vengono settate solo se non 
        // sono a null; se la connessione � <> NULL
        // significa che ho aperto un'altra connessione 
        // es. vedi keypool
        // 

        if (this.m_connection == null) {

            this.m_connection = atk_sql.m_connection;

            this.driverName = atk_sql.driverName;
            this.url = atk_sql.url;
            this.cdappl = atk_sql.cdappl;
            this.cdagen_appl = atk_sql.cdagen_appl;


            this.sysdate = atk_sql.sysdate;
            this.dummy_table = atk_sql.dummy_table;

            this.is_sybase = atk_sql.is_sybase;
            this.is_oracle = atk_sql.is_oracle;
        }


        this.cdazie = atk_sql.cdazie;
        this.cddipa = atk_sql.cddipa;
        this.profil = atk_sql.profil;
        oggi = new Timestamp(System.currentTimeMillis());
        dtinse = new Timestamp(System.currentTimeMillis());
        dtulag = new Timestamp(System.currentTimeMillis());


    }

    public String of_token(long al_) throws Exception {

        String ls_tk = "" + al_;

        int li_ = ls_tk.length();

        for (int i = li_; i < 10; i++) {

            ls_tk = "0" + ls_tk;
        }

        return ls_tk;
    }

    public String of_token(long al_, int len) throws Exception {

        String ls_tk = "" + al_;

        if (len <= 0) {
            len = 10;
        }

        int li_ = ls_tk.length();

        for (int i = li_; i < len; i++) {

            ls_tk = "0" + ls_tk;
        }

        return ls_tk;
    }

    public Timestamp of_getOggi() throws Exception {

        Timestamp ldt_ = new Timestamp(System.currentTimeMillis());

        ldt_.setHours(0);
        ldt_.setMinutes(0);
        ldt_.setSeconds(0);
        ldt_.setNanos(0);

        return ldt_;
    }

    public Timestamp of_getTimestamp() throws Exception {

        Timestamp ldt_ = new Timestamp(System.currentTimeMillis());


        return ldt_;
    }

    public String sql_String(String l_query) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String ls_ = "";


        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        if (rs != null && rs.next() && rs.getObject(1) != null) {

            ls_ = rs.getString(1);

        }
        pstm.close();



        return ls_;
    }

    public long sql_long(String l_query) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        long ll_ = 0;


        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        if (rs != null && rs.next() && rs.getObject(1) != null) {

            ll_ = rs.getLong(1);

        }
        pstm.close();



        return ll_;
    }

    public int sql_int(String l_query) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        int li_ = 0;


        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        if (rs != null && rs.next() && rs.getObject(1) != null) {

            li_ = rs.getInt(1);

        }
        pstm.close();



        return li_;
    }

    public double sql_double(String l_query) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        double ld_ = 0;


        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        if (rs != null && rs.next() && rs.getObject(1) != null) {

            ld_ = rs.getDouble(1);

        }
        pstm.close();



        return ld_;
    }

    public Timestamp sql_Timestamp(String l_query) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        Timestamp ldt_ = null;


        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        if (rs != null && rs.next() && rs.getObject(1) != null) {

            ldt_ = rs.getTimestamp(1);

        }
        pstm.close();



        return ldt_;
    }

    public ResultSet sql_query(String l_query) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        double ld_ = 0;


        pstm = setQuery(l_query);

        rs = pstm.executeQuery();

        return rs;
    }

    public ResultSet sql_query_ric(String l_query) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        double ld_ = 0;


        pstm = setQuery_ric(l_query);

        rs = pstm.executeQuery();

        return rs;
    }

    public int sql_update(String l_query) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;


        pstm = m_connection.prepareStatement(l_query);

        tot_rec = pstm.executeUpdate();

        pstm.close();



        return tot_rec;
    }

    /*
     public String par_like(String as_) throws Exception{

     int               ind = 0;
     int               tot_rec = 0;
     PreparedStatement pstm = null;
     ResultSet         rs   = null;

     as_ = as_.trim().toUpperCase();
        
     if (!as_.equals("")){
            
     as_ = as_.replace('*', '%');

     if (as_.indexOf("%")<= 0){
           
     as_ = "%"+as_.replace(' ', '%')+"%";
     }
     }

     as_ = com.ateikon.util.StringUtils.replace(as_, "'", "''");


     return as_;
     }
     */
    public String par_like(String as_) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        as_ = as_.trim().toUpperCase();

        if (as_.equals("")) {

            return as_;
        } else {
            as_ = as_.replace('*', '%');

            if (as_.indexOf("%") < 0) {

                as_ = "%" + as_.replace(' ', '%') + "%";
            }
        }

        as_ = com.ateikon.util.StringUtils.replace(as_, "'", "''");


        return as_;
    }

    /**
     * *
     *
     *
     */
    public long getTkutente(String user_name) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        long tkutente = 0;

        tot_rec = 0;

        l_query = "";
        l_query += " select tkutente        \n";
        l_query += "   from pgmr.cat_utente \n";
        l_query += "  where cdazie = ?      \n";
        l_query += "    and cddipa = ?      \n";
        l_query += "    and user_name  = ?  \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;
        pstm.setString(ind++, cdazie);
        pstm.setString(ind++, cddipa);
        pstm.setString(ind++, user_name);

        rs = pstm.executeQuery();

        if (rs != null && rs.next()) {

            tot_rec += 1;
            ind = 0;
            if (rs.getObject(++ind) != null) {
                tkutente = rs.getLong(ind);
            }

        }
        pstm.close();


        return tkutente;

    }

    /**
     * *
     *
     * capoarea *
     */
    public String par_ca_agenti(String cdcapoarea) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;


        String ls_ = "";

        ls_ = "";
        ls_ += " select iagec.cdagen                         \n";
        ls_ += "   from pgmr.archagen iagec                  \n";
        ls_ += "  where iagec.cdcapoarea = '" + cdcapoarea + "'  \n";
        ls_ += "    and iagec.cdazie = '" + cdazie + "'          \n";

        return ls_;

    }

    public String of_lingua(String tabella, String cdling) throws Exception{

        l_query  = "";
        l_query += " select cdISO639                     \n";
        l_query += "   from pgmr.atk_lingua              \n";
        l_query += "  where cdling = '"+ cdling +"'      \n";

        return sql_String(l_query);
    }

    public String of_suff_lingua(String cdling) throws Exception {
        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        return of_suff_lingua("", cdling);
    }

    public String of_suff_lingua(String as_tabella, String cdling_portale) throws Exception {
        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        com.ateikon.common.Atk_lingua atk_lingua = new com.ateikon.common.Atk_lingua();

        setProfilo((Atk_sql) atk_lingua);

        //Per compatibilità con quel che c'era se lingua non valorizzata, recupero lingua di default
        if (cdling_portale.equals("")) {
            cdling_portale = atk_lingua.getCdlingDef();
        }


        String ls_campo = "";
        String ls_suffisso = "";

        as_tabella = as_tabella.trim().toLowerCase();

        if (as_tabella.equals("")) {

            ls_campo = "suff_std";

        } else if (as_tabella.equals("mrp_arch_articoli")
                || as_tabella.equals("nazioni")
                || as_tabella.equals("vist_colori_vetro")
                || as_tabella.equals("vist_elettrificazioni")
                || as_tabella.equals("vist_famiglia")
                || as_tabella.equals("vist_finit_mont")
                || as_tabella.equals("vist_finit_vetro")
                || as_tabella.equals("vist_semilavorati")
                || as_tabella.equals("vist_tipi")
                || as_tabella.equals("vist_var1")
                || as_tabella.equals("vist_var2")
                || as_tabella.equals("vist_var3")) {

            ls_campo = "suff_arti";

        } else if (as_tabella.equals("ep_funz")
              ||  as_tabella.equals("ep_ticket")
              ||  as_tabella.equals("msg_comgen")) {

            ls_campo = "suff_funz";

        } else if (as_tabella.equals("statistiche_vendita")
                || as_tabella.equals("statistiche_ordcli")) {

            ls_campo = "suff_std";

        } else if (as_tabella.equals("afs_gest_object")) {

            ls_campo = "suff_docm";

        } else {
            throw new Exception("Attenzione Tabella " + as_tabella + " NON prevista");
        }

        String l_query = " select " + ls_campo + " from pgmr.ep_lingua where cdling_portale = '" + cdling_portale + "' ";

        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        if (rs != null && rs.next()) {

            if (rs.getObject(1) != null) {
                ls_suffisso = rs.getString(1);
            }

        } else {
            l_query = " select " + ls_campo + " from pgmr.ep_lingua where cdling = '" + cdling_portale + "' ";

            pstm = m_connection.prepareStatement(l_query);

            rs = pstm.executeQuery();

            if (rs != null && rs.next()) {

                if (rs.getObject(1) != null) {
                    ls_suffisso = rs.getString(1);
                }
            } else {
                throw new Exception("Codice lingua NON previsto: >>" + cdling_portale + "<<");
            }
        }

        pstm.close();

        if (!ls_suffisso.equals("")) {
            ls_suffisso = "_" + ls_suffisso;
        }

        atk_lingua.close();

        return ls_suffisso;

    }

    public String of_descr_lingua(String as_cdling, String as_alias, String as_campo_descr) throws Exception {

        return of_descr_lingua("", as_cdling, as_alias, as_campo_descr);
    }

    public String of_descr_lingua(String as_tabella, String as_cdling, String as_alias, String as_campo_descr) throws Exception {

        String ls_suffisso = of_suff_lingua(as_tabella, as_cdling);
        String ls_ = "";

        if (!as_alias.equals("")) {
            as_alias = as_alias + ".";
        }

        if (ls_suffisso.equals("")) {
            ls_ = as_alias + as_campo_descr;
        } else {
            if (as_tabella.equals("ep_funz")) {

                ls_ = "(case when " + as_alias + as_campo_descr + ls_suffisso + " is not null "
                        + " then " + as_alias + as_campo_descr + ls_suffisso
                        + " else " + as_alias + as_campo_descr + "_it"
                        + " end )";

            } else if (as_tabella.equals("statistiche_vendita")
                    || as_tabella.equals("statistiche_ordcli")) {
                ls_ = as_alias + as_campo_descr + ls_suffisso;

            } else if (as_tabella.equals("afs_gest_object")) {

                ls_ = "(case when " + as_alias + as_campo_descr + ls_suffisso + " is not null "
                        + " then " + as_alias + as_campo_descr + ls_suffisso
                        + " else " + as_alias + as_campo_descr + "_1"
                        + " end )";

            } else {

                ls_ = "(case when " + as_alias + as_campo_descr + ls_suffisso + " is not null "
                        + " then " + as_alias + as_campo_descr + ls_suffisso
                        + " else " + as_alias + as_campo_descr
                        + " end )";

            }
        }

        return ls_;
    }

    public String getCdling_ep_lingua(String cdling_atk_lingua) throws Exception {

        //converto cdling che mi arriva (da pgmr.atk_lingua) con cdling presente in pgmr.ep_lingua
        //per risolvere problematica presentatasi in F_eprogen_replace, gestisco che in caso non sia trovato un cdling
        //cerchi anche nel campo di cdling della tabella ep_lingua
        String l_query = "";

        l_query = "";
        l_query = "  select elin.cdling                                      \n";
        l_query += "    from pgmr.ep_lingua   elin                            \n";
        l_query += "   where elin.cdling_portale = '" + cdling_atk_lingua + "'  \n";

        String cdling_ep_lingua = sql_String(l_query);

        if (cdling_ep_lingua.equals("")) {

            l_query = "";
            l_query = "  select elin.cdling                                      \n";
            l_query += "    from pgmr.ep_lingua   elin                            \n";
            l_query += "   where elin.cdling = '" + cdling_atk_lingua + "'          \n";

            cdling_ep_lingua = sql_String(l_query);

        }

        return cdling_ep_lingua;
    }

    public String getEp_lingua_prg_cdprogetto() throws Exception {

        return "SHOP";
    }

    public String getLink_traduzioni(long tklingua_lb) throws Exception {

        return "epTraduzioni_edit.jsp?tklingua_lb=" + tklingua_lb;
    }


    public String par_vist_cp_collezione ( String dsvistccol ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        
        String ls_ = "";

        ls_  = "";
        ls_ += " select in_vccol.cdvistccol                         \n";
        ls_ += "   from pgmr.vist_cp_collezioni in_vccol            \n";
        ls_ += "  where in_vccol.dsvistccol = '"+dsvistccol+"'      \n";

        return ls_;

    }    
    
    
    public String getDescr_ov_dtcons(Timestamp adt_dtcons, String fgpron_cons, String fgnd_cons, String fgevaso, String cdling) throws Exception {

        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String ls_ = "";
        Atk_dwlingua atk_dwlingua = new Atk_dwlingua();

        this.setProfilo((Atk_sql) atk_dwlingua);
        atk_dwlingua.cdling = cdling;

        atk_dwlingua.dwin = "genera_stampa_doc.jsp";

        if (fgevaso.equals("S")) {
            ls_ = atk_dwlingua.getLabel("evaso", "");
        } else {
            if (fgnd_cons.equals("S")) {
                ls_ = atk_dwlingua.getLabel("data.noncalc", "");
            } else if (fgpron_cons.equals("S")) {
                ls_ = atk_dwlingua.getLabel("pronto.sped", "");
            } else if (adt_dtcons != null) {
                com.ateikon.util.Atk_ctrl atk_ctrl = new com.ateikon.util.Atk_ctrl();

                ls_ = atk_ctrl.getDate(adt_dtcons);
            } else {
                ls_ = "";
            }
        }

        return ls_;

    }
    
    public String of_ov_getDescrArticolo ( String dssart, String dsarti ) throws Exception {

      String ls_ds = "";
      
      dsarti = dsarti.trim();
      
      if (dsarti.equals("")){
        ls_ds = dssart;
      } else {
        ls_ds = dsarti;
      }
      
      return ls_ds;
    }
    
    public static String of_trasformaURL(String ls_url, String context_da_sostiture, String context_sostituto) throws Exception {
         
        java.net.URL url = new java.net.URL(ls_url);
         
        String ls_app1 = url.getProtocol() + "://" + url.getHost() + (url.getPort() > 0 ? ":"+ url.getPort() : "");
         
        String ls_app2 = ls_url.replace(ls_app1, "");
        ls_app2 = ls_app2.replace(context_da_sostiture, context_sostituto);
         
        return ls_app1 + ls_app2;
        
    }   
    
    public static String of_cambiaURLLingua(String ls_url, String cdling) throws Exception {
        
//        if (cdling.equals("R")){ //CABLATO -- russo
//            return of_cambiaDominoPrimoLivelloURL(ls_url, "ru");
//        } else if (cdling.equals("I") || cdling.equals("")){ //CABLATO -- italiano
//            return ls_url;
//        } else { //CABLATO -- italiano
//            return of_cambiaDominoPrimoLivelloURL(ls_url, "com");
//        }
        return ls_url;
        
    }
    
    public static String of_cambiaDominoPrimoLivelloURL(String ls_url, String dominio_sostituto) throws Exception {
         
        if (ls_url.trim().equals("")){
            return ls_url;
        }
        
        java.net.URL url = new java.net.URL(ls_url);
         
        String ls_host = url.getHost();
        
        int idx_dot = ls_host.lastIndexOf(".");
        
        if (idx_dot > 0){
            ls_host = ls_host.substring(0, idx_dot) + "." + dominio_sostituto;
        } else {
            return ls_url;
        }
         
        return url.getProtocol() + "://" + ls_host + (url.getPort() > 0 ? ":"+ url.getPort() : "") + url.getPath();
        
    }    
    
    
    
    // Variabili di appoggio
    public String l_query = "";
    public Vector vec_pstm = new Vector();
    public Vector vec_sql = new Vector();
    // *** Variabili Profilo
    public Connection m_connection = null;
    
    public           Connection        conn2 = null;
    public           PreparedStatement pstm  = null;
    public           PreparedStatement pstm1 = null;
    public           PreparedStatement pstm2 = null;
    public           PreparedStatement pstm3 = null;
    public           PreparedStatement pstm4 = null;
    public           PreparedStatement pstm5 = null;    
    
    public String driverName = "";
    public String url = "";
    public String cdappl = "";
    public String cdagen_appl = "";
    public String cdazie = "";
    public String cddipa = "";
    public String profil = "";
    public Timestamp oggi = new Timestamp(System.currentTimeMillis());
    public Timestamp dtinse = new Timestamp(System.currentTimeMillis());
    public Timestamp dtulag = new Timestamp(System.currentTimeMillis());
    public String sysdate = "";
    public String dummy_table = "";
    public boolean is_sybase = false;
    public boolean is_oracle = false;
    public boolean is_sqlserver  = false;
    public boolean is_postgresql = false;
    public static String ATEIKON = "ATEIKON";
    public static String JMEL = "JMEL";
    public String s_cdente = "";
    public String s_cdagen = "";
    public String s_cdcapoarea = "";
    public String s_cdispe = "";//avendramin20080428
    public String s_tkclie = "";
    public String s_tkforn = "";
    public String s_cdutente = "";
    public long s_tksubutente = 0; //avendramin20110318
    public String[] s_arr_cdunil = null; //avendramin20110623
    public long s_tkutente = 0;
    public String s_fgadmin = "";
    public String s_user_name = "";
    public String s_cdling = "";//avendramin20110718
    public String s_fgmod_trad = "N";//avendramin20110718
    public String s_cdresp = "";
    public String NOTIF_NACC = "NOTIF_NACC";    //avendramin20110419
    public String NOTIF_R_OV = "NOTIF_R_OV";    //avendramin20101126
    //VARIABILE PER ESCAPE URL
    public static String ESCAPE_URL_PDOMANDA = "~@63~";
    public static String ESCAPE_URL_ECOMM = "~@38~";
    
    public final static String DIR_TECHSHEET = "assembling_instructions";
}
