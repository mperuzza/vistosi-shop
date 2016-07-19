package com.ateikon.function;

import java.sql.ResultSet;
import java.sql.PreparedStatement;


import com.ateikon.common.Atk_sql;
import com.ateikon.common.Ep_costanti;
import com.ateikon.common.Ep_lingua;
import com.ateikon.common.Ep_msgmod;
import com.ateikon.common.Ep_msgmod_appl;
import com.ateikon.common.Ep_msgmod_cont;
import com.ateikon.common.Ep_msgmod_dest;
import com.ateikon.common.Ep_msgmod_file;
import com.ateikon.common.Ep_msgmod_ogg;
import com.ateikon.common.Ep_msgmod_par;
import com.ateikon.common.Ep_msgmod_var;
import com.ateikon.structure.Rec_ep_msgmod;
import com.ateikon.structure.Rec_ep_msgmod_appl;
import com.ateikon.structure.Rec_ep_msgmod_dest;
import com.ateikon.structure.Rec_ep_msgmod_file;
import com.ateikon.structure.Rec_ep_msgmod_ogg;
import com.ateikon.structure.Rec_ep_msgmod_par;
import com.ateikon.structure.Rec_ep_msgmod_var;
import com.ateikon.structure.Str_msgmod;
import java.io.File;


import java.util.Vector;
import javax.servlet.http.HttpServletRequest;

/**********************/
//     NOTA:
//     modificare questa classe nel portale implica un allineamento della stessa classe in eprogen
//     e viceversa modificare questa classe nel eprogen implica un allineamento della stessa classe nel portale
//
/*********************/

public class F_msgmod extends Atk_sql {

    public F_msgmod () {

        super();

        
    }
    
    public Str_msgmod of_retrieve (String cdmsgmod_appl, String cdmsgmod_var, String cdling) throws Exception {
        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        l_query   = "";
        l_query  += " select tkmsgmod \n";
        l_query  += "   from pgmr.ep_msgmod  \n";
        l_query  += "  where cdmsgmod_appl = '"+cdmsgmod_appl+"' \n";
        l_query  += "    and cdmsgmod_var = '"+cdmsgmod_var+"' \n";

        long tkmsgmod = sql_long(l_query);

        return of_retrieve (tkmsgmod, cdling);
    }
    
    /**
     * 
     * Retrieve dei dati salvati nel DB restituische Str_msgmod:
     *      <ul> 
     *          <li>Mittente</li> 
     *          <li>Oggetto</li> 
     *          <li>Destinatari</li> 
     *          <li>Corpo della mail</li> 
     *          <li>Allegati</li> 
     *      </ul> 
     * 
     * @param tkmsgmod codice modulo
     * @param cdling codice lingua 
     * @return 
     * @throws Exception 
     */
    
    public Str_msgmod of_retrieve (long tkmsgmod, String cdling) throws Exception {
        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        Ep_msgmod_ogg  ep_msgmod_ogg  = new Ep_msgmod_ogg();
        Ep_msgmod_dest ep_msgmod_dest = new Ep_msgmod_dest();
        Ep_msgmod_cont ep_msgmod_cont = new Ep_msgmod_cont();
        Ep_msgmod_file ep_msgmod_file = new Ep_msgmod_file();
        Ep_lingua ep_lingua = new Ep_lingua();
        
        setProfilo((Atk_sql) ep_msgmod_ogg);
        setProfilo((Atk_sql) ep_msgmod_dest);
        setProfilo((Atk_sql) ep_msgmod_cont);
        setProfilo((Atk_sql) ep_msgmod_file);
        setProfilo((Atk_sql) ep_lingua);
        
//        //Controllo cablato, se lingua impostata � russo, lingua notifica viene impostata in inglese
//        if (cdling.equals("R")) cdling = "E";
        //Per compatibilit� con quel che c'era se lingua non valorizzata, recupero lingua di default
        if (cdling.equals("")) cdling = ep_lingua.getCdling_def();

        Str_msgmod lstr = new Str_msgmod();
        
        lstr.of_init();
        lstr.tkmsgmod = tkmsgmod;
        lstr.cdling   = cdling;
        
        rs = ep_msgmod_ogg.getKey(tkmsgmod, cdling);
        
        if (rs!=null && rs.next()){
            
            Rec_ep_msgmod_ogg lstr_ogg = new Rec_ep_msgmod_ogg();
            
            lstr_ogg.setResultSet(rs);
            
            lstr.mittente = lstr_ogg.mittente;
            lstr.oggetto  = lstr_ogg.oggetto;
        }
                
        rs = ep_msgmod_dest.of_getDest(tkmsgmod);
        
        while (rs!=null && rs.next()){
            
            Rec_ep_msgmod_dest lstr_dest = new Rec_ep_msgmod_dest();
            
            lstr_dest.setResultSet(rs);
            
            lstr.list_dest.add(lstr_dest);
        }
        
        
        lstr.contenuto = ep_msgmod_cont.of_getContenuto(tkmsgmod, cdling);
        
        
        rs = ep_msgmod_file.of_getFile(tkmsgmod, cdling);
        
        while (rs!=null && rs.next()){
            
            Rec_ep_msgmod_file lstr_file = new Rec_ep_msgmod_file();
            
            lstr_file.setResultSet(rs);
            
            lstr.list_file.add(lstr_file);
        }
        
                
        ep_msgmod_ogg.close();
        ep_msgmod_dest.close();
        ep_msgmod_file.close();
        ep_msgmod_cont.close();
        ep_lingua.close();
        
        return lstr;
    }
    
     
    /**
     * Data la riquest ricarica la trittura
     * 
     * @param request
     * @return [1] OK
     * @throws Exception 
     */
    public Str_msgmod of_request (HttpServletRequest request, Str_msgmod astr ) throws Exception {
        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        

        astr.of_init();
        
        if (request.getParameterValues("filename")!=null    ){

            String[] arr_filename = request.getParameterValues("filename");

            for (int i=0; arr_filename!=null && i<arr_filename.length; i++){

                 Rec_ep_msgmod_file lstr_file = new Rec_ep_msgmod_file();

                lstr_file.filename = arr_filename[i];

                astr.list_file.add(lstr_file);
            }
        }
        
        if (request.getParameter("oggetto" )!=null ) astr.oggetto   = request.getParameter("oggetto");
        if (request.getParameter("mittente")!=null ) astr.mittente  = request.getParameter("mittente");
        if (request.getParameter("fgreturn_rcpt_to")!=null ) astr.fgreturn_rcpt_to  = request.getParameter("fgreturn_rcpt_to");
        if (request.getParameter("contenuto")!=null ) astr.contenuto = request.getParameter("contenuto");
        
        String[] arr_dest = null;
        String[] arr_tipodest = null;

        if (request.getParameterValues("dest")!=null ) arr_dest  = request.getParameterValues("dest");
        if (request.getParameterValues("tipodest")!=null ) arr_tipodest  = request.getParameterValues("tipodest");

        for(int i=0; arr_dest!=null && i<arr_dest.length; i++){

            arr_dest[i] = arr_dest[i].trim().toLowerCase();

            if (arr_dest[i].equals("")) continue;


            Rec_ep_msgmod_dest lstr_dest = new Rec_ep_msgmod_dest();
            
            lstr_dest.tipodest = arr_tipodest[i];
            lstr_dest.dest     = arr_dest[i];

            lstr_dest.dest = lstr_dest.dest.trim().toLowerCase();
            
            if (!lstr_dest.dest.equals("")){
                // --- testo se la mail � valida

                astr.list_dest.add(lstr_dest);
            }
        }

        
        return astr;
    }
    

    /**
     * Salva i ldati nel DB
     * 
     * @param astr
     * @return
     * @throws Exception 
     */
    
    public int of_salva (Str_msgmod astr) throws Exception {
        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        Ep_msgmod_ogg  ep_msgmod_ogg  = new Ep_msgmod_ogg();
        Ep_msgmod_cont ep_msgmod_cont = new Ep_msgmod_cont();
        Ep_msgmod_dest ep_msgmod_dest = new Ep_msgmod_dest();
        Ep_msgmod_file ep_msgmod_file = new Ep_msgmod_file();
        
        setProfilo((Atk_sql) ep_msgmod_ogg);
        setProfilo((Atk_sql) ep_msgmod_dest);
        setProfilo((Atk_sql) ep_msgmod_cont);
        setProfilo((Atk_sql) ep_msgmod_file);
        
                    
        Rec_ep_msgmod_ogg lstr_ogg = new Rec_ep_msgmod_ogg();

        lstr_ogg.tkmsgmod = astr.tkmsgmod;
        lstr_ogg.cdling   = astr.cdling;
        lstr_ogg.mittente = astr.mittente;
        lstr_ogg.oggetto  = astr.oggetto;
        
        
        tot_rec = ep_msgmod_ogg.execute(lstr_ogg);
        
        if (tot_rec != 1){
            throw new Exception("Errore INS/UPD ep_msgmod_ogg");
        }
        
             
        
        ep_msgmod_cont.of_setContenuto(astr.tkmsgmod, astr.cdling, astr.contenuto);
   
        
        
        ep_msgmod_dest.of_delete(astr.tkmsgmod);
        
        for (int i=0; astr.list_dest!=null && i<astr.list_dest.size(); i++){
            
            Rec_ep_msgmod_dest lstr_dest = (Rec_ep_msgmod_dest) astr.list_dest.elementAt(i);
            
            if (lstr_dest.dest.equals("")){
                continue;
            }
            
            lstr_dest.tkmsgmod = astr.tkmsgmod;

            tot_rec = ep_msgmod_dest.execute(lstr_dest);
          
            if (tot_rec != 1){
                throw new Exception("Errore INS/UPD ep_msgmod_dest");
            }
        }
        
        
        ep_msgmod_file.of_delete(astr.tkmsgmod, astr.cdling);
        
        for (int i=0; astr.list_file!=null && i<astr.list_file.size(); i++){
            
            Rec_ep_msgmod_file lstr_file = (Rec_ep_msgmod_file) astr.list_file.elementAt(i);
            
            lstr_file.tkmsgmod = astr.tkmsgmod;
            lstr_file.cdling   = astr.cdling;
            
            tot_rec = ep_msgmod_file.execute(lstr_file);
            
            if (tot_rec != 1){
                throw new Exception("Errore INS/UPD ep_msgmod_file");
            }
        }
        
                
        ep_msgmod_ogg.close();
        ep_msgmod_dest.close();
        ep_msgmod_file.close();
        
        
        return 1;
    }
    
    
    /**
     * Elimina il modello dal DB
     * 
     * @param 
     * @return
     * @throws Exception 
     */
    
    public int of_delete (long tkmsgmod, String cdling) throws Exception {
        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        Ep_msgmod_ogg  ep_msgmod_ogg  = new Ep_msgmod_ogg();
        Ep_msgmod_cont ep_msgmod_cont = new Ep_msgmod_cont();
        Ep_msgmod_dest ep_msgmod_dest = new Ep_msgmod_dest();
        Ep_msgmod_file ep_msgmod_file = new Ep_msgmod_file();
        
        setProfilo((Atk_sql) ep_msgmod_ogg);
        setProfilo((Atk_sql) ep_msgmod_dest);
        setProfilo((Atk_sql) ep_msgmod_cont);
        setProfilo((Atk_sql) ep_msgmod_file);
        
        
        tot_rec = ep_msgmod_ogg.deleteKey(tkmsgmod, cdling);

        ep_msgmod_cont.of_setContenuto(tkmsgmod, cdling, "");
        
        ep_msgmod_dest.of_delete(tkmsgmod);
        
        ep_msgmod_file.of_delete(tkmsgmod, cdling);
        
        
        
        return 1;
    }
    
    

    

    /**
     * procedural di inizializzazione: 
     *      <ul>
     *          <li>Inizializzazione tabelle BASE</li>
     *          <li>Inizializzazione Parametri</li>
     *      </ul>
     * 
     * @return
     * @throws Exception 
     */

    public int of_init ( ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        
        of_init_base();
        of_init_parametri();
        
        return 1;

    }
    

    /**
     * Procedura di inizializzazione tabelle Base: 
     *      <ul>
     *          <li>ep_msgmod_appl</li>
     *          <li>ep_msgmod_var</li>
     *          <li>ep_msgmod</li>
     *      </ul>
     * 
     * @return
     * @throws Exception 
     */
    
    
    protected int of_init_base () throws Exception {
        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        Ep_msgmod ep_msgmod = new Ep_msgmod();
        Ep_msgmod_appl ep_msgmod_appl = new Ep_msgmod_appl();
        Ep_msgmod_var ep_msgmod_var = new Ep_msgmod_var();
        
        setProfilo(ep_msgmod);
        setProfilo(ep_msgmod_appl);
        setProfilo(ep_msgmod_var);
        
        // --- Applicazioni
        String[][] arr_appl = new String [][]{ 
                                             //NOTIF. GENERICHE
                                               {"EPRO_CONTG"   , "Contatto generico"}
                                             , {"EPRO_CRED"    , "Invio credenziali"}
                                             , {"EPRO_NOACC"   , "Notifica NO accesso"}
                                             , {"SITE_DOWNR"   , "Download risorse"}

                                             //NOTIF. COM. GENERALI
                                             , {"COMG_ARTS"    , "Richiesta preventivo per articoli speciali"}
                                             , {"COMG_RECL"    , "Reclamo"}
                                             , {"COMG_RESC"    , "Richiesta autorizzazione al reso"}
                                             , {"COMG_SUGG"    , "Suggerimento"}

                                             //RUBRICA
                                             , {"RUBR_RR_CP"   , "Richiesta registrazione cliente potenziale"}
                                             , {"RUBR_RM_CG"   , "Richiesta modifica cliente gestionale effettivo"}
                                             , {"RUBR_PROSP"   , "Categorizzazione nuovo prospect"}

                                             //SCADENZE
                                             , {"SCADENZE"     , "Scadenze"}

                                             //VENDITA
                                             , {"OV_CONF"      , "Conferma ordine di vendita"}
                                             , {"OV_RITSPED"   , "Ritardo spedizione"}
                                             , {"OV_AVVSPED"   , "Avvenuta spedizione"}
                                             , {"OV_RICEZ"     , "Ricezione ordine di vendita"}
                                             , {"OV_MPRON"     , "Merce pronta"}
                                             , {"EPRO_NACCB"   , "Notifica blocco clienti inattivi"}
                                             
                                             // PREMI
                                             , {"PREMI"        , "Notifica premi"}
                                                            };
        
        for (int i=0; arr_appl!=null && i<arr_appl.length; i++){
            Rec_ep_msgmod_appl lstr_appl = new Rec_ep_msgmod_appl();

            lstr_appl.cdmsgmod_appl = arr_appl[i][0];
            lstr_appl.dsmsgmod_appl = arr_appl[i][1];
            lstr_appl.nrposi = (i+1);

            tot_rec = ep_msgmod_appl.execute(lstr_appl);
            
            if (tot_rec != 1)  throw new Exception("Errore INS/UPD pgmr.ep_msgmod_appl");
            
        }
        
        
        
        // --- Varianti
        String[][] arr_var = new String [][]{ 
                                              {"GENERICA"      , "Generica"}
                                            , {"UFFTECN"       , "Invio a ufficio tecnico"}
                                            , {"RISPOSTA"      , "Risposta"}
                                            , {"RISP_OK"       , "Risposta positiva"}
                                            , {"RISP_KO"       , "Risposta negativa"}
                                            , {"ADDATT"        , "Aggiunta allegato"}
                                            , {"DELATT"        , "Cancellazione allegato"}
                                            , {"CLIENTE"       , "Invio a cliente"}
                                            , {"CLI_TP1"       , "Invio a cliente tipo 1"}
                                            , {"CLI_TP1_AG"    , "Invio a cliente tipo 1 con agente"}
                                            , {"CLI_TP2"       , "Invio a cliente tipo 2"}
                                            , {"CLI_TP2_AG"    , "Invio a cliente tipo 2 con agente"}
                                            , {"CLI_TP3"       , "Invio a cliente tipo 3"}
                                            , {"CLI_TP3_AG"    , "Invio a cliente tipo 3 con agente"}
                                            , {"CLI_TP4"       , "Invio a cliente tipo 4"}
                                            , {"CLI_TP4_AG"    , "Invio a cliente tipo 4 con agente"}
                                            , {"EL13_CLT1P"    , "EUROLUCE 2013 - PRIVATO O PICCOLO PROFESSIONISTA"}
                                            , {"EL13_CLT2P"    , "EUROLUCE 2013 - PROFESSIONISTA - RIVENDITORE"}
                                            , {"EL13_CLT3P"    , "EUROLUCE 2013 - NEGOZIO O DISTRIBUTORE"}
                                            , {"EL13_CLT4P"    , "EUROLUCE 2013 - PROFESSIONISTA - DIREZIONALE"}
                                            , {"CS_CLT1P"      , "CONTATTO SITO (TIPO P) - PRIVATO O PICCOLO PROFESSIONISTA"}
                                            , {"CS_CLT2P"      , "CONTATTO SITO (TIPO P) - PROFESSIONISTA - RIVENDITORE"}
                                            , {"CS_CLT3P"      , "CONTATTO SITO (TIPO P) - NEGOZIO O DISTRIBUTORE"}
                                            , {"CS_CLT4P"      , "CONTATTO SITO (TIPO P) - PROFESSIONISTA - DIREZIONALE"}
                                            , {"CS_CLT1A"      , "CONTATTO SITO (TIPO A) - PRIVATO O PICCOLO PROFESSIONISTA"}
                                            , {"CS_CLT2A"      , "CONTATTO SITO (TIPO A) - PROFESSIONISTA - RIVENDITORE"}
                                            , {"CS_CLT3A"      , "CONTATTO SITO (TIPO A) - NEGOZIO O DISTRIBUTORE"}
                                            , {"CS_CLT4A"      , "CONTATTO SITO (TIPO A) - PROFESSIONISTA - DIREZIONALE"}
                                            , {"VIS_CLT1P"     , "VISITA AZIENDA - PRIVATO O PICCOLO PROFESSIONISTA"}
                                            , {"VIS_CLT2P"     , "VISITA AZIENDA - PROFESSIONISTA - RIVENDITORE"}
                                            , {"VIS_CLT3P"     , "VISITA AZIENDA - NEGOZIO O DISTRIBUTORE"}
                                            , {"VIS_CLT4P"     , "VISITA AZIENDA - PROFESSIONISTA - DIREZIONALE"}
                                            , {"GEN_CLT1P"     , "ORIGINE DATI DINAMICA - PRIVATO O PICCOLO PROFESSIONISTA"}
                                            , {"GEN_CLT2P"     , "ORIGINE DATI DINAMICA - PROFESSIONISTA - RIVENDITORE"}
                                            , {"GEN_CLT3P"     , "ORIGINE DATI DINAMICA - NEGOZIO O DISTRIBUTORE"}
                                            , {"GEN_CLT4P"     , "ORIGINE DATI DINAMICA - PROFESSIONISTA - DIREZIONALE"}
                                            , {"SALES"         , "Invio da/a area-manager, capo-area, agente o sub-agente"}
                                            , {"UFFAMM"        , "Invio a amministrazione"}
                                            , {"MODIFICA"      , "Modifica"}
                                                            };
        
        for (int i=0; arr_var!=null && i<arr_var.length; i++){
            Rec_ep_msgmod_var lstr_var = new Rec_ep_msgmod_var();

            lstr_var.cdmsgmod_var = arr_var[i][0];
            lstr_var.dsmsgmod_var = arr_var[i][1];
            lstr_var.nrposi = (i+1);

            tot_rec = ep_msgmod_var.execute(lstr_var);
            
            if (tot_rec != 1)  throw new Exception("Errore INS/UPD pgmr.ep_msgmod_var");
            
        }
        
        
        
        // --- Modelli (Applicazioni / Varianti)
        String[][] arr_ = new String [][]{ 
                                         //NOTIF. GENERICHE
                                           {"EPRO_CONTG"   , "GENERICA"}
                                         , {"EPRO_CRED"    , "GENERICA"}
                                         , {"EPRO_NOACC"   , "GENERICA"}
                                         , {"SITE_DOWNR"   , "GENERICA"}
                                         , {"SITE_DOWNR"   , "UFFTECN"}
                                         , {"SITE_DOWNR"   , "RISPOSTA"}

                                         //NOTIF. COM. GENERALI
                                         , {"COMG_ARTS"    , "GENERICA"}
                                         , {"COMG_ARTS"    , "RISPOSTA"}
                                         , {"COMG_ARTS"    , "ADDATT"}
                                         , {"COMG_ARTS"    , "DELATT"}
                                         , {"COMG_RECL"    , "GENERICA"}
                                         , {"COMG_RECL"    , "RISPOSTA"}
                                         , {"COMG_RECL"    , "ADDATT"}
                                         , {"COMG_RECL"    , "DELATT"}
                                         , {"COMG_RESC"    , "GENERICA"}
                                         , {"COMG_RESC"    , "RISP_OK"}
                                         , {"COMG_RESC"    , "RISP_KO"}
                                         , {"COMG_RESC"    , "ADDATT"}
                                         , {"COMG_RESC"    , "DELATT"}
                                         , {"COMG_SUGG"    , "GENERICA"}
                                         , {"COMG_SUGG"    , "RISPOSTA"}
                                         , {"COMG_SUGG"    , "ADDATT"}
                                         , {"COMG_SUGG"    , "DELATT"}

                                         //RUBRICA
                                         , {"RUBR_RR_CP"   , "GENERICA"}
                                         , {"RUBR_RR_CP"   , "SALES"}
                                         , {"RUBR_RM_CG"   , "GENERICA"}
                                         , {"RUBR_RM_CG"   , "ADDATT"}
                                         , {"RUBR_RM_CG"   , "DELATT"}
                                         , {"RUBR_RM_CG"   , "MODIFICA"}
                                         , {"RUBR_PROSP"   , "EL13_CLT1P"}
                                         , {"RUBR_PROSP"   , "EL13_CLT2P"}
                                         , {"RUBR_PROSP"   , "EL13_CLT3P"}
                                         , {"RUBR_PROSP"   , "EL13_CLT4P"}
                                         , {"RUBR_PROSP"   , "CS_CLT1P"}
                                         , {"RUBR_PROSP"   , "CS_CLT2P"}
                                         , {"RUBR_PROSP"   , "CS_CLT3P"}
                                         , {"RUBR_PROSP"   , "CS_CLT4P"}
                                         , {"RUBR_PROSP"   , "CS_CLT1A"}
                                         , {"RUBR_PROSP"   , "CS_CLT2A"}
                                         , {"RUBR_PROSP"   , "CS_CLT3A"}
                                         , {"RUBR_PROSP"   , "CS_CLT4A"}
                                         , {"RUBR_PROSP"   , "VIS_CLT1P"}
                                         , {"RUBR_PROSP"   , "VIS_CLT2P"}
                                         , {"RUBR_PROSP"   , "VIS_CLT3P"}
                                         , {"RUBR_PROSP"   , "VIS_CLT4P"}
                                         , {"RUBR_PROSP"   , "GEN_CLT1P"}
                                         , {"RUBR_PROSP"   , "GEN_CLT2P"}
                                         , {"RUBR_PROSP"   , "GEN_CLT3P"}
                                         , {"RUBR_PROSP"   , "GEN_CLT4P"}

                                         //SCADENZE
                                         , {"SCADENZE"     , "GENERICA"}
                                         , {"SCADENZE"     , "CLIENTE"}
                                         , {"SCADENZE"     , "CLI_TP2"}
                                         , {"SCADENZE"     , "CLI_TP3"}
                                         , {"SCADENZE"     , "CLI_TP4"}
                                         , {"SCADENZE"     , "SALES"}
                                         , {"SCADENZE"     , "UFFAMM"}

                                         //VENDITA
                                         , {"OV_CONF"      , "GENERICA"}
                                         , {"OV_RITSPED"   , "GENERICA"}
                                         , {"OV_AVVSPED"   , "GENERICA"}
                                         , {"OV_RICEZ"     , "GENERICA"}
                                         , {"OV_MPRON"     , "GENERICA"}
                                         , {"EPRO_NACCB"   , "GENERICA"}

                                         //PREMI
                                         , {"PREMI"        , "GENERICA"}
                                                };
        
        for (int i=0; arr_!=null && i<arr_.length; i++){
            Rec_ep_msgmod lstr = new Rec_ep_msgmod();

            lstr.cdmsgmod_appl = arr_[i][0];
            lstr.cdmsgmod_var  = arr_[i][1];

            tot_rec = ep_msgmod.execute(lstr);
            
            if (tot_rec != 1)  throw new Exception("Errore INS/UPD pgmr.ep_msgmod");
            
        }
        
        
        
        return 1;
    }
    
    
    protected int of_init_parametri () throws Exception {
        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        Ep_msgmod_par ep_msgmod_par = new Ep_msgmod_par();
        Ep_costanti ep_costanti = new Ep_costanti();
        
        setProfilo(ep_msgmod_par);
        setProfilo(ep_costanti);
        
        String ep_portal_url    = ep_costanti.getCostvalue("ep.portal_url");
        String ep_site_url    = ep_costanti.getCostvalue("ep.site_url");
        String ep_site_ext_url    = ep_costanti.getCostvalue("ep.site_ext_url");
        String ep_eprogen_url    = ep_costanti.getCostvalue("ep.eprogen_url");
        String ep_shop_url    = ep_costanti.getCostvalue("ep.shop_url");
    

        if (ep_eprogen_url.equals("")) ep_eprogen_url = of_trasformaURL(ep_portal_url, "portal", "eprogen");
        if (ep_shop_url.equals("")) ep_shop_url = of_trasformaURL(ep_portal_url, "portal", "shop");
        
        
        // Psizioni:
        //  0) Tipo parametro
        //  1) Categoria -- EMAIL / TESTO / TESTOHTML / ALLEGATO 
        //  2) Cod. parametro
        //  3) Descrizione Parametro
        
        String[][] arr_ = new String [][]{ 
                                         //NOTIF. GENERICHE


                                           {"EPRO_CONTG"   , "TESTO"        , "${atk_contatti.tkcontatto}"                      , "Contatto: codice"}
                                         , {"EPRO_CONTG"   , "TESTO"        , "${atk_contatti.nome}"                            , "Contatto: nome"}
                                         , {"EPRO_CONTG"   , "TESTO"        , "${atk_contatti.cognome}"                         , "Contatto: cognome"}
                                         , {"EPRO_CONTG"   , "TESTO"        , "${atk_contatti.ragsoc}"                          , "Contatto: ragione sociale"}
                                         , {"EPRO_CONTG"   , "EMAIL"        , "${atk_contatti.email}"                           , "Contatto: indirizzo email"}
                                         , {"EPRO_CONTG"   , "TESTO"        , "${atk_contatti.telefono}"                        , "Contatto: telefono"}
                                         , {"EPRO_CONTG"   , "TESTO"        , "${atk_contatti.fax}"                             , "Contatto: fax"}
                                         , {"EPRO_CONTG"   , "TESTO"        , "${atk_contatti.indiri}"                          , "Contatto: indirizzo"}
                                         , {"EPRO_CONTG"   , "TESTO"        , "${atk_contatti.cap}"                             , "Contatto: CAP"}
                                         , {"EPRO_CONTG"   , "TESTO"        , "${atk_contatti.citta}"                           , "Contatto: citt�"}
                                         , {"EPRO_CONTG"   , "TESTO"        , "${province.dsprov}"                              , "Contatto: descrizione provicia"}
                                         , {"EPRO_CONTG"   , "TESTO"        , "${nazioni.dsnazi}"                               , "Contatto: descrizione nazione"}
                                         , {"EPRO_CONTG"   , "TESTO"        , "${atk_contatti.dsmotcont}"                       , "Contatto: motivo"}
                                         , {"EPRO_CONTG"   , "TESTO"        , "${atk_contatti.messaggio}"                       , "Contatto: messaggio"}

                                         , {"EPRO_CRED"    , "EMAIL"        , "${archclie.email}"                               , "Cliente: Indirizzo e-mail"}
                                         , {"EPRO_CRED"    , "EMAIL"        , "${archclie.email_tecn}"                          , "Cliente: Indirizzo e-mail per comunicazione tecnica"}
                                         , {"EPRO_CRED"    , "EMAIL"        , "${archclie.email_amm}"                           , "Cliente: Indirizzo e-mail per comunicazione amministrativa"}
                                         , {"EPRO_CRED"    , "EMAIL"        , "${archclie.email_comm}"                          , "Cliente: Indirizzo e-mail per comunicazione commerciale"}
                                         , {"EPRO_CRED"    , "EMAIL"        , "${ep_utente.email}"                              , "Utente: Indirizzo e-mail"}
                                         , {"EPRO_CRED"    , "EMAIL"        , "${ep_utente.email_tecn}"                         , "Utente: Indirizzo e-mail per comunicazione tecnica"}
                                         , {"EPRO_CRED"    , "EMAIL"        , "${ep_utente.email_amm}"                          , "Utente: Indirizzo e-mail per comunicazione amministrativa"}
                                         , {"EPRO_CRED"    , "EMAIL"        , "${ep_utente.email_comm}"                         , "Utente: Indirizzo e-mail per comunicazione commerciale"}
                                         , {"EPRO_CRED"    , "EMAIL"        , "${archresp.email}"                               , "Responsabile vend.: Indirizzo e-mail"}
                                         , {"EPRO_CRED"    , "EMAIL"        , "${archresp.email_tecn}"                          , "Responsabile vend.: Indirizzo e-mail per comunicazione tecnica"}
                                         , {"EPRO_CRED"    , "EMAIL"        , "${archresp.email_amm}"                           , "Responsabile vend.: Indirizzo e-mail per comunicazione amministrativa"}
                                         , {"EPRO_CRED"    , "EMAIL"        , "${archresp.email_comm}"                          , "Responsabile vend.: Indirizzo e-mail per comunicazione commerciale"}
                                         , {"EPRO_CRED"    , "EMAIL"        , "${age_ispettori.email}"                          , "Area-manager: Indirizzo e-mail"}           
                                         , {"EPRO_CRED"    , "EMAIL"        , "${age_ispettori.email_tecn}"                     , "Area-manager: Indirizzo e-mail per comunicazione tecnica"}                  
                                         , {"EPRO_CRED"    , "EMAIL"        , "${age_ispettori.email_amm}"                      , "Area-manager: Indirizzo e-mail per comunicazione amministrativa"}           
                                         , {"EPRO_CRED"    , "EMAIL"        , "${age_ispettori.email_comm}"                     , "Area-manager: Indirizzo e-mail per comunicazione commerciale"}              
                                         , {"EPRO_CRED"    , "EMAIL"        , "${archagen.email_capo}"                          , "Capoarea: Indirizzo e-mail"}            
                                         , {"EPRO_CRED"    , "EMAIL"        , "${archagen.email_capo_tecn}"                     , "Capoarea: Indirizzo e-mail per comunicazione tecnica"}                   
                                         , {"EPRO_CRED"    , "EMAIL"        , "${archagen.email_capo_amm}"                      , "Capoarea: Indirizzo e-mail per comunicazione amministrativa"}             
                                         , {"EPRO_CRED"    , "EMAIL"        , "${archagen.email_capo_comm}"                     , "Capoarea: Indirizzo e-mail per comunicazione commerciale"}               
                                         , {"EPRO_CRED"    , "EMAIL"        , "${archagen.email}"                               , "Agente: Indirizzo e-mail"}          
                                         , {"EPRO_CRED"    , "EMAIL"        , "${archagen.email_tecn}"                          , "Agente: Indirizzo e-mail per comunicazione tecnica"}                     
                                         , {"EPRO_CRED"    , "EMAIL"        , "${archagen.email_amm}"                           , "Agente: Indirizzo e-mail per comunicazione amministrativa"}              
                                         , {"EPRO_CRED"    , "EMAIL"        , "${archagen.email_comm}"                          , "Agente: Indirizzo e-mail per comunicazione commerciale"}                 
                                         , {"EPRO_CRED"    , "EMAIL"        , "${archagen.email_agenrif}"                       , "Agente Rif.: Indirizzo e-mail"}              
                                         , {"EPRO_CRED"    , "EMAIL"        , "${archagen.email_agenrif_tecn}"                  , "Agente Rif.: Indirizzo e-mail per comunicazione tecnica"}                 
                                         , {"EPRO_CRED"    , "EMAIL"        , "${archagen.email_agenrif_amm}"                   , "Agente Rif.: Indirizzo e-mail per comunicazione amministrativa"}          
                                         , {"EPRO_CRED"    , "EMAIL"        , "${archagen.email_agenrif_comm}"                  , "Agente Rif.: Indirizzo e-mail per comunicazione commerciale"}             
                                         , {"EPRO_CRED"    , "TESTO"        , "${archenti.ragcog}"                              , "Cliente: Ragione sociale"}
                                         , {"EPRO_CRED"    , "TESTO"        , "${ep_utente.dsutente}"                           , "Utente: Descrizione"}
                                         , {"EPRO_CRED"    , "TESTO"        , "${ep_utente.user_name}"                          , "Utente: Username"}
                                         , {"EPRO_CRED"    , "TESTO"        , "${ep_utente.password}"                           , "Utente: Password"}
                                         , {"EPRO_CRED"    , "TESTO"        , "${ep_utente.FTP_user_name}"                      , "Utente: FTP Username"}
                                         , {"EPRO_CRED"    , "TESTO"        , "${ep_utente.FTP_password}"                       , "Utente: FTP Password"}
                                         , {"EPRO_CRED"    , "TESTO"        , "${cred.tbl_cred}"                                , "Credenziali: Tabella credenziali"}
                                         , {"EPRO_CRED"    , "TESTO"        , "${keycode.url_portal}"                           , "Keycode: URL portale con keycode (solo per cliente)"}
//                                         , {"EPRO_CRED"    , "TESTO"        , "${archclie.cdclie_m}"                            , "Cliente: Codice"}

//                                         , {"EPRO_NOACC"   , "EMAIL"        , "${archclie.email}"                               , "Cliente: Indirizzo e-mail"}
//                                         , {"EPRO_NOACC"   , "EMAIL"        , "${archclie.email_tecn}"                          , "Cliente: Indirizzo e-mail per comunicazione tecnica"}
//                                         , {"EPRO_NOACC"   , "EMAIL"        , "${archclie.email_amm}"                           , "Cliente: Indirizzo e-mail per comunicazione amministrativa"}
//                                         , {"EPRO_NOACC"   , "EMAIL"        , "${archclie.email_comm}"                          , "Cliente: Indirizzo e-mail per comunicazione commerciale"}
                                         , {"EPRO_NOACC"   , "EMAIL"        , "${ep_utente.email}"                              , "Utente: Indirizzo e-mail"}
                                         , {"EPRO_NOACC"   , "EMAIL"        , "${ep_utente.email_tecn}"                         , "Utente: Indirizzo e-mail per comunicazione tecnica"}
                                         , {"EPRO_NOACC"   , "EMAIL"        , "${ep_utente.email_amm}"                          , "Utente: Indirizzo e-mail per comunicazione amministrativa"}
                                         , {"EPRO_NOACC"   , "EMAIL"        , "${ep_utente.email_comm}"                         , "Utente: Indirizzo e-mail per comunicazione commerciale"}
                                         , {"EPRO_NOACC"   , "EMAIL"        , "${archresp.email}"                               , "Responsabile vend.: Indirizzo e-mail"}
                                         , {"EPRO_NOACC"   , "EMAIL"        , "${archresp.email_tecn}"                          , "Responsabile vend.: Indirizzo e-mail per comunicazione tecnica"}
                                         , {"EPRO_NOACC"   , "EMAIL"        , "${archresp.email_amm}"                           , "Responsabile vend.: Indirizzo e-mail per comunicazione amministrativa"}
                                         , {"EPRO_NOACC"   , "EMAIL"        , "${archresp.email_comm}"                          , "Responsabile vend.: Indirizzo e-mail per comunicazione commerciale"}
                                         , {"EPRO_NOACC"   , "EMAIL"        , "${age_ispettori.email}"                          , "Area-manager: Indirizzo e-mail"}           
                                         , {"EPRO_NOACC"   , "EMAIL"        , "${age_ispettori.email_tecn}"                     , "Area-manager: Indirizzo e-mail per comunicazione tecnica"}           
                                         , {"EPRO_NOACC"   , "EMAIL"        , "${age_ispettori.email_amm}"                      , "Area-manager: Indirizzo e-mail per comunicazione amministrativa"}           
                                         , {"EPRO_NOACC"   , "EMAIL"        , "${age_ispettori.email_comm}"                     , "Area-manager: Indirizzo e-mail per comunicazione commerciale"}           
                                         , {"EPRO_NOACC"   , "EMAIL"        , "${archagen.email_capo}"                          , "Capoarea: Indirizzo e-mail"}            
                                         , {"EPRO_NOACC"   , "EMAIL"        , "${archagen.email_capo_tecn}"                     , "Capoarea: Indirizzo e-mail per comunicazione tecnica"}            
                                         , {"EPRO_NOACC"   , "EMAIL"        , "${archagen.email_capo_amm}"                      , "Capoarea: Indirizzo e-mail per comunicazione amministrativa"}            
                                         , {"EPRO_NOACC"   , "EMAIL"        , "${archagen.email_capo_comm}"                     , "Capoarea: Indirizzo e-mail per comunicazione commerciale"}            
                                         , {"EPRO_NOACC"   , "EMAIL"        , "${archagen.email_agenrif}"                       , "Agente Rif.: Indirizzo e-mail"}              
                                         , {"EPRO_NOACC"   , "EMAIL"        , "${archagen.email_agenrif_tecn}"                  , "Agente Rif.: Indirizzo e-mail per comunicazione tecnica"}              
                                         , {"EPRO_NOACC"   , "EMAIL"        , "${archagen.email_agenrif_amm}"                   , "Agente Rif.: Indirizzo e-mail per comunicazione amministrativa"}              
                                         , {"EPRO_NOACC"   , "EMAIL"        , "${archagen.email_agenrif_comm}"                  , "Agente Rif.: Indirizzo e-mail per comunicazione commerciale"}              
                                         , {"EPRO_NOACC"   , "EMAIL"        , "${archagen.email}"                               , "Agente: Indirizzo e-mail"}          
                                         , {"EPRO_NOACC"   , "EMAIL"        , "${archagen.email_tecn}"                          , "Agente: Indirizzo e-mail per comunicazione tecnica"}          
                                         , {"EPRO_NOACC"   , "EMAIL"        , "${archagen.email_amm}"                           , "Agente: Indirizzo e-mail per comunicazione amministrativa"}          
                                         , {"EPRO_NOACC"   , "EMAIL"        , "${archagen.email_comm}"                          , "Agente: Indirizzo e-mail per comunicazione commerciale"}          
                                         , {"EPRO_NOACC"   , "TESTO"        , "${archenti.ragcog}"                              , "Cliente: Ragione sociale"}
                                         , {"EPRO_NOACC"   , "TESTO"        , "${ep_utente.user_name}"                          , "Utente: Username"}
                                         , {"EPRO_NOACC"   , "TESTO"        , "${ep_utente.password}"                           , "Utente: Password"}
                                         , {"EPRO_NOACC"   , "TESTO"        , "${ep_utente.dtsendpwd}"                          , "Utente: Data invio credenziali"}
                                         , {"EPRO_NOACC"   , "TESTO"        , "${oggetto.text_clie}"                            , "Oggetto: suffisso con ragione sociale cliente"}
                                         , {"EPRO_NOACC"   , "TESTO"        , "${cred.tbl2_cred}"                               , "Credenziali: Tabella credenziali ver.2"}
                                         , {"EPRO_NOACC"   , "TESTO"        , "${keycode.url_portal}"                           , "Keycode: URL portale con keycode (solo per cliente)"}
//                                         , {"EPRO_NOACC"   , "TESTO"        , "${archclie.cdclie_m}"                            , "Cliente: Codice"}


                                         , {"SITE_DOWNR"   , "TESTO"        , "${site_downr.url_risorsa}"                       , "Download risorse: URL risorsa"}
                                         , {"SITE_DOWNR"   , "EMAIL"        , "${site_downr.email_uff_tecnico}"                 , "Download risorse: Indirizzo e-mail ufficio tecnico"}
                                         , {"SITE_DOWNR"   , "TESTO"        , "${site_downr.nomeRisorsaMancante}"               , "Download risorse: Nome risorsa non trovata"}
                                         , {"SITE_DOWNR"   , "TESTO"        , "${atk_contatti.dscontatto}"                      , "Contatto: Descrizione"}
                                         , {"SITE_DOWNR"   , "TESTO"        , "${atk_contatti.dsfile}"                          , "Contatto: Descrizione file richiesto"}
                                         , {"SITE_DOWNR"   , "TESTO"        , "${atk_contatti.tkcontatto}"                      , "Contatto: Codice"}
                                         , {"SITE_DOWNR"   , "EMAIL"        , "${atk_contatti.email}"                           , "Contatto: Indirizzo email"}
                                         , {"SITE_DOWNR"   , "TESTO"        , "${atk_contatti.ragsoc}"                          , "Contatto: Ragione sociale"}
                                         , {"SITE_DOWNR"   , "TESTO"        , "${atk_contatti.citta}"                           , "Contatto: Città"}
                                         , {"SITE_DOWNR"   , "TESTO"        , "${nazioni.dsnazi}"                               , "Contatto: Descrizione nazione"}
                                         , {"SITE_DOWNR"   , "EMAIL"        , "${ep_utente.email}"                              , "Utente: Indirizzo e-mail"}
                                         , {"SITE_DOWNR"   , "EMAIL"        , "${ep_utente.email_tecn}"                         , "Utente: Indirizzo e-mail per comunicazione tecnica"}
                                         , {"SITE_DOWNR"   , "EMAIL"        , "${ep_utente.email_amm}"                          , "Utente: Indirizzo e-mail per comunicazione amministrativa"}
                                         , {"SITE_DOWNR"   , "EMAIL"        , "${ep_utente.email_comm}"                         , "Utente: Indirizzo e-mail per comunicazione commerciale"}
                                         , {"SITE_DOWNR"   , "EMAIL"        , "${archclie.email}"                               , "Cliente: Indirizzo e-mail"}
                                         , {"SITE_DOWNR"   , "EMAIL"        , "${archclie.email_tecn}"                          , "Cliente: Indirizzo e-mail per comunicazione tecnica"}
                                         , {"SITE_DOWNR"   , "EMAIL"        , "${archclie.email_amm}"                           , "Cliente: Indirizzo e-mail per comunicazione amministrativa"}
                                         , {"SITE_DOWNR"   , "EMAIL"        , "${archclie.email_comm}"                          , "Cliente: Indirizzo e-mail per comunicazione commerciale"}
                                         , {"SITE_DOWNR"   , "EMAIL"        , "${archresp.email}"                               , "Responsabile vend.: Indirizzo e-mail"}
                                         , {"SITE_DOWNR"   , "EMAIL"        , "${archresp.email_tecn}"                          , "Responsabile vend.: Indirizzo e-mail per comunicazione tecnica"}
                                         , {"SITE_DOWNR"   , "EMAIL"        , "${archresp.email_amm}"                           , "Responsabile vend.: Indirizzo e-mail per comunicazione amministrativa"}
                                         , {"SITE_DOWNR"   , "EMAIL"        , "${archresp.email_comm}"                          , "Responsabile vend.: Indirizzo e-mail per comunicazione commerciale"}
                                         , {"SITE_DOWNR"   , "EMAIL"        , "${age_ispettori.email}"                          , "Area-manager: Indirizzo e-mail"}           
                                         , {"SITE_DOWNR"   , "EMAIL"        , "${age_ispettori.email_tecn}"                     , "Area-manager: Indirizzo e-mail per comunicazione tecnica"}               
                                         , {"SITE_DOWNR"   , "EMAIL"        , "${age_ispettori.email_amm}"                      , "Area-manager: Indirizzo e-mail per comunicazione amministrativa"}          
                                         , {"SITE_DOWNR"   , "EMAIL"        , "${age_ispettori.email_comm}"                     , "Area-manager: Indirizzo e-mail per comunicazione commerciale"}             
                                         , {"SITE_DOWNR"   , "EMAIL"        , "${archagen.email_capo}"                          , "Capoarea: Indirizzo e-mail"}              
                                         , {"SITE_DOWNR"   , "EMAIL"        , "${archagen.email_capo_tecn}"                     , "Capoarea: Indirizzo e-mail per comunicazione tecnica"}                      
                                         , {"SITE_DOWNR"   , "EMAIL"        , "${archagen.email_capo_amm}"                      , "Capoarea: Indirizzo e-mail per comunicazione amministrativa"}                    
                                         , {"SITE_DOWNR"   , "EMAIL"        , "${archagen.email_capo_comm}"                     , "Capoarea: Indirizzo e-mail per comunicazione commerciale"}                      
                                         , {"SITE_DOWNR"   , "EMAIL"        , "${archagen.email_agenrif}"                       , "Agente Rif.: Indirizzo e-mail"}              
                                         , {"SITE_DOWNR"   , "EMAIL"        , "${archagen.email_agenrif_tecn}"                  , "Agente Rif.: Indirizzo e-mail per comunicazione tecnica"}              
                                         , {"SITE_DOWNR"   , "EMAIL"        , "${archagen.email_agenrif_amm}"                   , "Agente Rif.: Indirizzo e-mail per comunicazione amministrativa"}       
                                         , {"SITE_DOWNR"   , "EMAIL"        , "${archagen.email_agenrif_comm}"                  , "Agente Rif.: Indirizzo e-mail per comunicazione commerciale"}          
                                         , {"SITE_DOWNR"   , "EMAIL"        , "${archagen.email}"                               , "Agente: Indirizzo e-mail"}          
                                         , {"SITE_DOWNR"   , "EMAIL"        , "${archagen.email_tecn}"                          , "Agente: Indirizzo e-mail per comunicazione tecnica"}          
                                         , {"SITE_DOWNR"   , "EMAIL"        , "${archagen.email_amm}"                           , "Agente: Indirizzo e-mail per comunicazione amministrativa"}          
                                         , {"SITE_DOWNR"   , "EMAIL"        , "${archagen.email_comm}"                          , "Agente: Indirizzo e-mail per comunicazione commerciale"}          
                                         , {"SITE_DOWNR"   , "TESTO"        , "${ep_utente.tipologia_utente}"                   , "Utente: Descrizione tipologia utente"}
                                         , {"SITE_DOWNR"   , "TESTO"        , "${ep_utente.user_name}"                          , "Utente: Username"}
                                         

                                         //NOTIF. COM. GENERALI


                                         , {"COMG_ARTS"    , "EMAIL"        , "${archclie.email}"                               , "Cliente: Indirizzo e-mail"}
                                         , {"COMG_ARTS"    , "EMAIL"        , "${archclie.email_tecn}"                          , "Cliente: Indirizzo e-mail per comunicazione tecnica"}
                                         , {"COMG_ARTS"    , "EMAIL"        , "${archclie.email_amm}"                           , "Cliente: Indirizzo e-mail per comunicazione amministrativa"}
                                         , {"COMG_ARTS"    , "EMAIL"        , "${archclie.email_comm}"                          , "Cliente: Indirizzo e-mail per comunicazione commerciale"}
                                         , {"COMG_ARTS"    , "EMAIL"        , "${archresp.email}"                               , "Responsabile vend.: Indirizzo e-mail"}
                                         , {"COMG_ARTS"    , "EMAIL"        , "${archresp.email_tecn}"                          , "Responsabile vend.: Indirizzo e-mail per comunicazione tecnica"}
                                         , {"COMG_ARTS"    , "EMAIL"        , "${archresp.email_amm}"                           , "Responsabile vend.: Indirizzo e-mail per comunicazione amministrativa"}
                                         , {"COMG_ARTS"    , "EMAIL"        , "${archresp.email_comm}"                          , "Responsabile vend.: Indirizzo e-mail per comunicazione commerciale"}
                                         , {"COMG_ARTS"    , "EMAIL"        , "${age_ispettori.email}"                          , "Area-manager: Indirizzo e-mail"}           
                                         , {"COMG_ARTS"    , "EMAIL"        , "${age_ispettori.email_tecn}"                     , "Area-manager: Indirizzo e-mail per comunicazione tecnica"}              
                                         , {"COMG_ARTS"    , "EMAIL"        , "${age_ispettori.email_amm}"                      , "Area-manager: Indirizzo e-mail per comunicazione amministrativa"}         
                                         , {"COMG_ARTS"    , "EMAIL"        , "${age_ispettori.email_comm}"                     , "Area-manager: Indirizzo e-mail per comunicazione commerciale"}            
                                         , {"COMG_ARTS"    , "EMAIL"        , "${archagen.email_capo}"                          , "Capoarea: Indirizzo e-mail"}            
                                         , {"COMG_ARTS"    , "EMAIL"        , "${archagen.email_capo_tecn}"                     , "Capoarea: Indirizzo e-mail per comunicazione tecnica"}                      
                                         , {"COMG_ARTS"    , "EMAIL"        , "${archagen.email_capo_amm}"                      , "Capoarea: Indirizzo e-mail per comunicazione amministrativa"}                    
                                         , {"COMG_ARTS"    , "EMAIL"        , "${archagen.email_capo_comm}"                     , "Capoarea: Indirizzo e-mail per comunicazione commerciale"}                      
                                         , {"COMG_ARTS"    , "EMAIL"        , "${archagen.email_agenrif}"                       , "Agente Rif.: Indirizzo e-mail"}              
                                         , {"COMG_ARTS"    , "EMAIL"        , "${archagen.email_agenrif_tecn}"                  , "Agente Rif.: Indirizzo e-mail per comunicazione tecnica"}              
                                         , {"COMG_ARTS"    , "EMAIL"        , "${archagen.email_agenrif_amm}"                   , "Agente Rif.: Indirizzo e-mail per comunicazione amministrativa"}       
                                         , {"COMG_ARTS"    , "EMAIL"        , "${archagen.email_agenrif_comm}"                  , "Agente Rif.: Indirizzo e-mail per comunicazione commerciale"}          
                                         , {"COMG_ARTS"    , "EMAIL"        , "${archagen.email}"                               , "Agente: Indirizzo e-mail"}          
                                         , {"COMG_ARTS"    , "EMAIL"        , "${archagen.email_tecn}"                          , "Agente: Indirizzo e-mail per comunicazione tecnica"}          
                                         , {"COMG_ARTS"    , "EMAIL"        , "${archagen.email_amm}"                           , "Agente: Indirizzo e-mail per comunicazione amministrativa"}          
                                         , {"COMG_ARTS"    , "EMAIL"        , "${archagen.email_comm}"                          , "Agente: Indirizzo e-mail per comunicazione commerciale"}          
                                         , {"COMG_ARTS"    , "TESTO"        , "${arts.url}"                                     , "Rich. art. spec.: URL"}
                                         , {"COMG_ARTS"    , "TESTO"        , "${ep_ticket.tkticket}"                           , "Rich. art. spec.: Codice ticket"}
                                         , {"COMG_ARTS"    , "TESTO"        , "${msg_comgen.dscomgen}"                          , "Rich. art. spec.: Descrizione"}
                                         , {"COMG_ARTS"    , "TESTO"        , "${msg_comgen.notacomgen}"                        , "Rich. art. spec.: Nota"}
                                         , {"COMG_ARTS"    , "TESTO"        , "${archclie.cdclie_m}"                            , "Cliente: Codice"}
                                         , {"COMG_ARTS"    , "TESTO"        , "${archenti.ragcog}"                              , "Cliente: Ragione sociale"}
                                         , {"COMG_ARTS"    , "TESTO"        , "${file.filename}"                                , "File: Nome file"}
                                         , {"COMG_ARTS"    , "TESTO"        , "${oggetto.text_clie}"                            , "Oggetto: suffisso con ragione sociale cliente"}
                                         , {"COMG_ARTS"    , "TESTO"        , "${arts.url_keycode}"                             , "Rich. art. spec.: URL con keycode (solo per cliente)"}
                                         
                                         , {"COMG_RECL"    , "EMAIL"        , "${archclie.email}"                               , "Cliente: Indirizzo e-mail"}
                                         , {"COMG_RECL"    , "EMAIL"        , "${archclie.email_tecn}"                          , "Cliente: Indirizzo e-mail per comunicazione tecnica"}
                                         , {"COMG_RECL"    , "EMAIL"        , "${archclie.email_amm}"                           , "Cliente: Indirizzo e-mail per comunicazione amministrativa"}
                                         , {"COMG_RECL"    , "EMAIL"        , "${archclie.email_comm}"                          , "Cliente: Indirizzo e-mail per comunicazione commerciale"}
                                         , {"COMG_RECL"    , "EMAIL"        , "${archresp.email}"                               , "Responsabile vend.: Indirizzo e-mail"}
                                         , {"COMG_RECL"    , "EMAIL"        , "${archresp.email_tecn}"                          , "Responsabile vend.: Indirizzo e-mail per comunicazione tecnica"}
                                         , {"COMG_RECL"    , "EMAIL"        , "${archresp.email_amm}"                           , "Responsabile vend.: Indirizzo e-mail per comunicazione amministrativa"}
                                         , {"COMG_RECL"    , "EMAIL"        , "${archresp.email_comm}"                          , "Responsabile vend.: Indirizzo e-mail per comunicazione commerciale"}
                                         , {"COMG_RECL"    , "EMAIL"        , "${age_ispettori.email}"                          , "Area-manager: Indirizzo e-mail"}           
                                         , {"COMG_RECL"    , "EMAIL"        , "${age_ispettori.email_tecn}"                     , "Area-manager: Indirizzo e-mail per comunicazione tecnica"}               
                                         , {"COMG_RECL"    , "EMAIL"        , "${age_ispettori.email_amm}"                      , "Area-manager: Indirizzo e-mail per comunicazione amministrativa"}          
                                         , {"COMG_RECL"    , "EMAIL"        , "${age_ispettori.email_comm}"                     , "Area-manager: Indirizzo e-mail per comunicazione commerciale"}             
                                         , {"COMG_RECL"    , "EMAIL"        , "${archagen.email_capo}"                          , "Capoarea: Indirizzo e-mail"}              
                                         , {"COMG_RECL"    , "EMAIL"        , "${archagen.email_capo_tecn}"                     , "Capoarea: Indirizzo e-mail per comunicazione tecnica"}                      
                                         , {"COMG_RECL"    , "EMAIL"        , "${archagen.email_capo_amm}"                      , "Capoarea: Indirizzo e-mail per comunicazione amministrativa"}                    
                                         , {"COMG_RECL"    , "EMAIL"        , "${archagen.email_capo_comm}"                     , "Capoarea: Indirizzo e-mail per comunicazione commerciale"}                      
                                         , {"COMG_RECL"    , "EMAIL"        , "${archagen.email_agenrif}"                       , "Agente Rif.: Indirizzo e-mail"}              
                                         , {"COMG_RECL"    , "EMAIL"        , "${archagen.email_agenrif_tecn}"                  , "Agente Rif.: Indirizzo e-mail per comunicazione tecnica"}              
                                         , {"COMG_RECL"    , "EMAIL"        , "${archagen.email_agenrif_amm}"                   , "Agente Rif.: Indirizzo e-mail per comunicazione amministrativa"}       
                                         , {"COMG_RECL"    , "EMAIL"        , "${archagen.email_agenrif_comm}"                  , "Agente Rif.: Indirizzo e-mail per comunicazione commerciale"}          
                                         , {"COMG_RECL"    , "EMAIL"        , "${archagen.email}"                               , "Agente: Indirizzo e-mail"}          
                                         , {"COMG_RECL"    , "EMAIL"        , "${archagen.email_tecn}"                          , "Agente: Indirizzo e-mail per comunicazione tecnica"}          
                                         , {"COMG_RECL"    , "EMAIL"        , "${archagen.email_amm}"                           , "Agente: Indirizzo e-mail per comunicazione amministrativa"}          
                                         , {"COMG_RECL"    , "EMAIL"        , "${archagen.email_comm}"                          , "Agente: Indirizzo e-mail per comunicazione commerciale"}          
                                         , {"COMG_RECL"    , "TESTO"        , "${recl.url}"                                     , "Reclamo: URL"}
                                         , {"COMG_RECL"    , "TESTO"        , "${ep_ticket.tkticket}"                           , "Reclamo: Codice ticket"}
                                         , {"COMG_RECL"    , "TESTO"        , "${msg_comgen.dscomgen}"                          , "Reclamo: Descrizione"}
                                         , {"COMG_RECL"    , "TESTO"        , "${msg_comgen.notacomgen}"                        , "Reclamo: Nota"}
                                         , {"COMG_RECL"    , "TESTO"        , "${ep_ticket.nota}"                               , "Reclamo: Risposta"}
                                         , {"COMG_RECL"    , "TESTO"        , "${archclie.cdclie_m}"                            , "Cliente: Codice"}
                                         , {"COMG_RECL"    , "TESTO"        , "${archenti.ragcog}"                              , "Cliente: Ragione sociale"}
                                         , {"COMG_RECL"    , "TESTO"        , "${file.filename}"                                , "File: Nome file"}
                                         , {"COMG_RECL"    , "TESTO"        , "${ep_ticket.dsutente_ins}"                       , "Utente Ins.: Descrizione"}
                                         , {"COMG_RECL"    , "TESTO"        , "${oggetto.text_clie}"                            , "Oggetto: suffisso con ragione sociale cliente"}         
                                         , {"COMG_RECL"    , "TESTO"        , "${recl.url_keycode}"                             , "Reclamo: URL con keycode (solo per cliente)"}
                                         , {"COMG_RECL"    , "TESTO"        , "${mrp_arch_articoli.cdartm}"                     , "Articolo: Codice"}
                                         , {"COMG_RECL"    , "TESTO"        , "${mrp_arch_articoli.dsarti}"                     , "Articolo: Descrizione"}

                                         , {"COMG_RESC"    , "EMAIL"        , "${archclie.email}"                               , "Cliente: Indirizzo e-mail"}
                                         , {"COMG_RESC"    , "EMAIL"        , "${archclie.email_tecn}"                          , "Cliente: Indirizzo e-mail per comunicazione tecnica"}
                                         , {"COMG_RESC"    , "EMAIL"        , "${archclie.email_amm}"                           , "Cliente: Indirizzo e-mail per comunicazione amministrativa"}
                                         , {"COMG_RESC"    , "EMAIL"        , "${archclie.email_comm}"                          , "Cliente: Indirizzo e-mail per comunicazione commerciale"}
                                         , {"COMG_RESC"    , "EMAIL"        , "${archresp.email}"                               , "Responsabile vend.: Indirizzo e-mail"}
                                         , {"COMG_RESC"    , "EMAIL"        , "${archresp.email_tecn}"                          , "Responsabile vend.: Indirizzo e-mail per comunicazione tecnica"}
                                         , {"COMG_RESC"    , "EMAIL"        , "${archresp.email_amm}"                           , "Responsabile vend.: Indirizzo e-mail per comunicazione amministrativa"}
                                         , {"COMG_RESC"    , "EMAIL"        , "${archresp.email_comm}"                          , "Responsabile vend.: Indirizzo e-mail per comunicazione commerciale"}
                                         , {"COMG_RESC"    , "EMAIL"        , "${age_ispettori.email}"                          , "Area-manager: Indirizzo e-mail"}           
                                         , {"COMG_RESC"    , "EMAIL"        , "${age_ispettori.email_tecn}"                     , "Area-manager: Indirizzo e-mail per comunicazione tecnica"}             
                                         , {"COMG_RESC"    , "EMAIL"        , "${age_ispettori.email_amm}"                      , "Area-manager: Indirizzo e-mail per comunicazione amministrativa"}        
                                         , {"COMG_RESC"    , "EMAIL"        , "${age_ispettori.email_comm}"                     , "Area-manager: Indirizzo e-mail per comunicazione commerciale"}           
                                         , {"COMG_RESC"    , "EMAIL"        , "${archagen.email_capo}"                          , "Capoarea: Indirizzo e-mail"}                    
                                         , {"COMG_RESC"    , "EMAIL"        , "${archagen.email_capo_tecn}"                     , "Capoarea: Indirizzo e-mail per comunicazione tecnica"}                    
                                         , {"COMG_RESC"    , "EMAIL"        , "${archagen.email_capo_amm}"                      , "Capoarea: Indirizzo e-mail per comunicazione amministrativa"}                  
                                         , {"COMG_RESC"    , "EMAIL"        , "${archagen.email_capo_comm}"                     , "Capoarea: Indirizzo e-mail per comunicazione commerciale"}                    
                                         , {"COMG_RESC"    , "EMAIL"        , "${archagen.email_agenrif}"                       , "Agente Rif.: Indirizzo e-mail"}              
                                         , {"COMG_RESC"    , "EMAIL"        , "${archagen.email_agenrif_tecn}"                  , "Agente Rif.: Indirizzo e-mail per comunicazione tecnica"}              
                                         , {"COMG_RESC"    , "EMAIL"        , "${archagen.email_agenrif_amm}"                   , "Agente Rif.: Indirizzo e-mail per comunicazione amministrativa"}       
                                         , {"COMG_RESC"    , "EMAIL"        , "${archagen.email_agenrif_comm}"                  , "Agente Rif.: Indirizzo e-mail per comunicazione commerciale"}          
                                         , {"COMG_RESC"    , "EMAIL"        , "${archagen.email}"                               , "Agente: Indirizzo e-mail"}          
                                         , {"COMG_RESC"    , "EMAIL"        , "${archagen.email_tecn}"                          , "Agente: Indirizzo e-mail per comunicazione tecnica"}          
                                         , {"COMG_RESC"    , "EMAIL"        , "${archagen.email_amm}"                           , "Agente: Indirizzo e-mail per comunicazione amministrativa"}          
                                         , {"COMG_RESC"    , "EMAIL"        , "${archagen.email_comm}"                          , "Agente: Indirizzo e-mail per comunicazione commerciale"}          
                                         , {"COMG_RESC"    , "TESTO"        , "${resc.url}"                                     , "Reso: URL"}
                                         , {"COMG_RESC"    , "TESTO"        , "${ep_ticket.tkticket}"                           , "Reso: Codice ticket"}
                                         , {"COMG_RESC"    , "TESTO"        , "${msg_comgen.dscomgen}"                          , "Reso: Descrizione"}
                                         , {"COMG_RESC"    , "TESTO"        , "${msg_comgen.notacomgen}"                        , "Reso: Nota"}
                                         , {"COMG_RESC"    , "TESTO"        , "${ep_ticket.nota}"                               , "Reso: Risposta"}
                                         , {"COMG_RESC"    , "TESTO"        , "${archclie.cdclie_m}"                            , "Cliente: Codice"}
                                         , {"COMG_RESC"    , "TESTO"        , "${archenti.ragcog}"                              , "Cliente: Ragione sociale"}
                                         , {"COMG_RESC"    , "TESTO"        , "${file.filename}"                                , "File: Nome file"}
                                         , {"COMG_RESC"    , "TESTO"        , "${oggetto.text_clie}"                            , "Oggetto: suffisso con ragione sociale cliente"}         
                                         , {"COMG_RESC"    , "TESTO"        , "${resc.url_keycode}"                             , "Reso: URL con keycode (solo per cliente)"}

                                         , {"COMG_SUGG"    , "EMAIL"        , "${archclie.email}"                               , "Cliente: Indirizzo e-mail"}
                                         , {"COMG_SUGG"    , "EMAIL"        , "${archclie.email_tecn}"                          , "Cliente: Indirizzo e-mail per comunicazione tecnica"}
                                         , {"COMG_SUGG"    , "EMAIL"        , "${archclie.email_amm}"                           , "Cliente: Indirizzo e-mail per comunicazione amministrativa"}
                                         , {"COMG_SUGG"    , "EMAIL"        , "${archclie.email_comm}"                          , "Cliente: Indirizzo e-mail per comunicazione commerciale"}
                                         , {"COMG_SUGG"    , "EMAIL"        , "${archresp.email}"                               , "Responsabile vend.: Indirizzo e-mail"}
                                         , {"COMG_SUGG"    , "EMAIL"        , "${archresp.email_tecn}"                          , "Responsabile vend.: Indirizzo e-mail per comunicazione tecnica"}
                                         , {"COMG_SUGG"    , "EMAIL"        , "${archresp.email_amm}"                           , "Responsabile vend.: Indirizzo e-mail per comunicazione amministrativa"}
                                         , {"COMG_SUGG"    , "EMAIL"        , "${archresp.email_comm}"                          , "Responsabile vend.: Indirizzo e-mail per comunicazione commerciale"}
                                         , {"COMG_SUGG"    , "EMAIL"        , "${age_ispettori.email}"                          , "Area-manager: Indirizzo e-mail"}           
                                         , {"COMG_SUGG"    , "EMAIL"        , "${age_ispettori.email_tecn}"                     , "Area-manager: Indirizzo e-mail per comunicazione tecnica"}           
                                         , {"COMG_SUGG"    , "EMAIL"        , "${age_ispettori.email_amm}"                      , "Area-manager: Indirizzo e-mail per comunicazione amministrativa"}      
                                         , {"COMG_SUGG"    , "EMAIL"        , "${age_ispettori.email_comm}"                     , "Area-manager: Indirizzo e-mail per comunicazione commerciale"}         
                                         , {"COMG_SUGG"    , "EMAIL"        , "${archagen.email_capo}"                          , "Capoarea: Indirizzo e-mail"}            
                                         , {"COMG_SUGG"    , "EMAIL"        , "${archagen.email_capo_tecn}"                     , "Capoarea: Indirizzo e-mail per comunicazione tecnica"}            
                                         , {"COMG_SUGG"    , "EMAIL"        , "${archagen.email_capo_amm}"                      , "Capoarea: Indirizzo e-mail per comunicazione amministrativa"}          
                                         , {"COMG_SUGG"    , "EMAIL"        , "${archagen.email_capo_comm}"                     , "Capoarea: Indirizzo e-mail per comunicazione commerciale"}            
                                         , {"COMG_SUGG"    , "EMAIL"        , "${archagen.email_agenrif}"                       , "Agente Rif.: Indirizzo e-mail"}              
                                         , {"COMG_SUGG"    , "EMAIL"        , "${archagen.email_agenrif_tecn}"                  , "Agente Rif.: Indirizzo e-mail per comunicazione tecnica"}              
                                         , {"COMG_SUGG"    , "EMAIL"        , "${archagen.email_agenrif_amm}"                   , "Agente Rif.: Indirizzo e-mail per comunicazione amministrativa"}       
                                         , {"COMG_SUGG"    , "EMAIL"        , "${archagen.email_agenrif_comm}"                  , "Agente Rif.: Indirizzo e-mail per comunicazione commerciale"}          
                                         , {"COMG_SUGG"    , "EMAIL"        , "${archagen.email}"                               , "Agente: Indirizzo e-mail"}          
                                         , {"COMG_SUGG"    , "EMAIL"        , "${archagen.email_tecn}"                          , "Agente: Indirizzo e-mail per comunicazione tecnica"}          
                                         , {"COMG_SUGG"    , "EMAIL"        , "${archagen.email_amm}"                           , "Agente: Indirizzo e-mail per comunicazione amministrativa"}          
                                         , {"COMG_SUGG"    , "EMAIL"        , "${archagen.email_comm}"                          , "Agente: Indirizzo e-mail per comunicazione commerciale"}          
                                         , {"COMG_SUGG"    , "TESTO"        , "${sugg.url}"                                     , "Suggerimento: URL"}
                                         , {"COMG_SUGG"    , "TESTO"        , "${ep_ticket.tkticket}"                           , "Suggerimento: Codice ticket"}
                                         , {"COMG_SUGG"    , "TESTO"        , "${msg_comgen.dscomgen}"                          , "Suggerimento: Descrizione"}
                                         , {"COMG_SUGG"    , "TESTO"        , "${msg_comgen.notacomgen}"                        , "Suggerimento: Nota"}
                                         , {"COMG_SUGG"    , "TESTO"        , "${archclie.cdclie_m}"                            , "Cliente: Codice"}
                                         , {"COMG_SUGG"    , "TESTO"        , "${archenti.ragcog}"                              , "Cliente: Ragione sociale"}
                                         , {"COMG_SUGG"    , "TESTO"        , "${file.filename}"                                , "File: Nome file"}
                                         , {"COMG_SUGG"    , "TESTO"        , "${oggetto.text_clie}"                            , "Oggetto: suffisso con ragione sociale cliente"}       
                                         , {"COMG_SUGG"    , "TESTO"        , "${sugg.url_keycode}"                             , "Suggerimento: URL con keycode (solo per cliente)"}


                                         //RUBRICA


//                                         , {"RUBR_RR_CP"   , "EMAIL"        , "${archclie.email}"                               , "Cliente: Indirizzo e-mail"}
//                                         , {"RUBR_RR_CP"   , "EMAIL"        , "${archclie.email_tecn}"                          , "Cliente: Indirizzo e-mail per comunicazione tecnica"}
//                                         , {"RUBR_RR_CP"   , "EMAIL"        , "${archclie.email_amm}"                           , "Cliente: Indirizzo e-mail per comunicazione amministrativa"}
//                                         , {"RUBR_RR_CP"   , "EMAIL"        , "${archclie.email_comm}"                          , "Cliente: Indirizzo e-mail per comunicazione commerciale"}
                                         , {"RUBR_RR_CP"   , "EMAIL"        , "${ep_utente.email}"                              , "Utente: Indirizzo e-mail"}
                                         , {"RUBR_RR_CP"   , "EMAIL"        , "${ep_utente.email_tecn}"                         , "Utente: Indirizzo e-mail per comunicazione tecnica"}
                                         , {"RUBR_RR_CP"   , "EMAIL"        , "${ep_utente.email_amm}"                          , "Utente: Indirizzo e-mail per comunicazione amministrativa"}
                                         , {"RUBR_RR_CP"   , "EMAIL"        , "${ep_utente.email_comm}"                         , "Utente: Indirizzo e-mail per comunicazione commerciale"}
                                         , {"RUBR_RR_CP"   , "EMAIL"        , "${archresp.email}"                               , "Responsabile vend.: Indirizzo e-mail"}
                                         , {"RUBR_RR_CP"   , "EMAIL"        , "${archresp.email_tecn}"                          , "Responsabile vend.: Indirizzo e-mail per comunicazione tecnica"}
                                         , {"RUBR_RR_CP"   , "EMAIL"        , "${archresp.email_amm}"                           , "Responsabile vend.: Indirizzo e-mail per comunicazione amministrativa"}
                                         , {"RUBR_RR_CP"   , "EMAIL"        , "${archresp.email_comm}"                          , "Responsabile vend.: Indirizzo e-mail per comunicazione commerciale"}
                                         , {"RUBR_RR_CP"   , "EMAIL"        , "${age_ispettori.email}"                          , "Area-manager: Indirizzo e-mail"}           
                                         , {"RUBR_RR_CP"   , "EMAIL"        , "${age_ispettori.email_tecn}"                     , "Area-manager: Indirizzo e-mail per comunicazione tecnica"}           
                                         , {"RUBR_RR_CP"   , "EMAIL"        , "${age_ispettori.email_amm}"                      , "Area-manager: Indirizzo e-mail per comunicazione amministrativa"}           
                                         , {"RUBR_RR_CP"   , "EMAIL"        , "${age_ispettori.email_comm}"                     , "Area-manager: Indirizzo e-mail per comunicazione commerciale"}           
                                         , {"RUBR_RR_CP"   , "EMAIL"        , "${archagen.email_capo}"                          , "Capoarea: Indirizzo e-mail"}            
                                         , {"RUBR_RR_CP"   , "EMAIL"        , "${archagen.email_capo_tecn}"                     , "Capoarea: Indirizzo e-mail per comunicazione tecnica"}            
                                         , {"RUBR_RR_CP"   , "EMAIL"        , "${archagen.email_capo_amm}"                      , "Capoarea: Indirizzo e-mail per comunicazione amministrativa"}          
                                         , {"RUBR_RR_CP"   , "EMAIL"        , "${archagen.email_capo_comm}"                     , "Capoarea: Indirizzo e-mail per comunicazione commerciale"}            
                                         , {"RUBR_RR_CP"   , "EMAIL"        , "${archagen.email_agenrif}"                       , "Agente Rif.: Indirizzo e-mail"}              
                                         , {"RUBR_RR_CP"   , "EMAIL"        , "${archagen.email_agenrif_tecn}"                  , "Agente Rif.: Indirizzo e-mail per comunicazione tecnica"}              
                                         , {"RUBR_RR_CP"   , "EMAIL"        , "${archagen.email_agenrif_amm}"                   , "Agente Rif.: Indirizzo e-mail per comunicazione amministrativa"}       
                                         , {"RUBR_RR_CP"   , "EMAIL"        , "${archagen.email_agenrif_comm}"                  , "Agente Rif.: Indirizzo e-mail per comunicazione commerciale"}          
                                         , {"RUBR_RR_CP"   , "EMAIL"        , "${archagen.email}"                               , "Agente: Indirizzo e-mail"}          
                                         , {"RUBR_RR_CP"   , "EMAIL"        , "${archagen.email_tecn}"                          , "Agente: Indirizzo e-mail per comunicazione tecnica"}          
                                         , {"RUBR_RR_CP"   , "EMAIL"        , "${archagen.email_amm}"                           , "Agente: Indirizzo e-mail per comunicazione amministrativa"}          
                                         , {"RUBR_RR_CP"   , "EMAIL"        , "${archagen.email_comm}"                          , "Agente: Indirizzo e-mail per comunicazione commerciale"}          
                                         , {"RUBR_RR_CP"   , "TESTO"        , "${archrubrica.url}"                              , "Contatto: URL"}
                                         , {"RUBR_RR_CP"   , "TESTO"        , "${archrubrica.ragcog}"                           , "Contatto: Ragione sociale"}
                                         , {"RUBR_RR_CP"   , "TESTO"        , "${archrubrica.tkrubr}"                           , "Contatto: Codice"}
                                         , {"RUBR_RR_CP"   , "TESTO"        , "${archrubrica_tipo.dsrubr_tipo}"                 , "Contatto Tipo: Descrizione"}
                                         , {"RUBR_RR_CP"   , "TESTO"        , "${rubr_rr_cp.tbl_dati}"                          , "Contatto: Tabella riepilogativa"}
//                                         , {"RUBR_RR_CP"   , "TESTO"        , "${archclie.cdclie_m}"                            , "Cliente: Codice"}

//                                         , {"RUBR_RM_CG"   , "EMAIL"        , "${archclie.email}"                               , "Cliente: Indirizzo e-mail"}
//                                         , {"RUBR_RM_CG"   , "EMAIL"        , "${archclie.email_tecn}"                          , "Cliente: Indirizzo e-mail per comunicazione tecnica"}
//                                         , {"RUBR_RM_CG"   , "EMAIL"        , "${archclie.email_amm}"                           , "Cliente: Indirizzo e-mail per comunicazione amministrativa"}
//                                         , {"RUBR_RM_CG"   , "EMAIL"        , "${archclie.email_comm}"                          , "Cliente: Indirizzo e-mail per comunicazione commerciale"}
                                         , {"RUBR_RM_CG"   , "EMAIL"        , "${archresp.email}"                               , "Responsabile vend.: Indirizzo e-mail"}  
                                         , {"RUBR_RM_CG"   , "EMAIL"        , "${archresp.email_tecn}"                          , "Responsabile vend.: Indirizzo e-mail per comunicazione tecnica"}  
                                         , {"RUBR_RM_CG"   , "EMAIL"        , "${archresp.email_amm}"                           , "Responsabile vend.: Indirizzo e-mail per comunicazione amministrativa"}  
                                         , {"RUBR_RM_CG"   , "EMAIL"        , "${archresp.email_comm}"                          , "Responsabile vend.: Indirizzo e-mail per comunicazione commerciale"}  
                                         , {"RUBR_RM_CG"   , "EMAIL"        , "${age_ispettori.email}"                          , "Area-manager: Indirizzo e-mail"}           
                                         , {"RUBR_RM_CG"   , "EMAIL"        , "${age_ispettori.email_tecn}"                     , "Area-manager: Indirizzo e-mail per comunicazione tecnica"}           
                                         , {"RUBR_RM_CG"   , "EMAIL"        , "${age_ispettori.email_amm}"                      , "Area-manager: Indirizzo e-mail per comunicazione amministrativa"}      
                                         , {"RUBR_RM_CG"   , "EMAIL"        , "${age_ispettori.email_comm}"                     , "Area-manager: Indirizzo e-mail per comunicazione commerciale"}         
                                         , {"RUBR_RM_CG"   , "EMAIL"        , "${archagen.email_capo}"                          , "Capoarea: Indirizzo e-mail"}            
                                         , {"RUBR_RM_CG"   , "EMAIL"        , "${archagen.email_capo_tecn}"                     , "Capoarea: Indirizzo e-mail per comunicazione tecnica"}            
                                         , {"RUBR_RM_CG"   , "EMAIL"        , "${archagen.email_capo_amm}"                      , "Capoarea: Indirizzo e-mail per comunicazione amministrativa"}          
                                         , {"RUBR_RM_CG"   , "EMAIL"        , "${archagen.email_capo_comm}"                     , "Capoarea: Indirizzo e-mail per comunicazione commerciale"}            
                                         , {"RUBR_RM_CG"   , "EMAIL"        , "${archagen.email_agenrif}"                       , "Agente Rif.: Indirizzo e-mail"}              
                                         , {"RUBR_RM_CG"   , "EMAIL"        , "${archagen.email_agenrif_tecn}"                  , "Agente Rif.: Indirizzo e-mail per comunicazione tecnica"}              
                                         , {"RUBR_RM_CG"   , "EMAIL"        , "${archagen.email_agenrif_amm}"                   , "Agente Rif.: Indirizzo e-mail per comunicazione amministrativa"}       
                                         , {"RUBR_RM_CG"   , "EMAIL"        , "${archagen.email_agenrif_comm}"                  , "Agente Rif.: Indirizzo e-mail per comunicazione commerciale"}          
                                         , {"RUBR_RM_CG"   , "EMAIL"        , "${archagen.email}"                               , "Agente: Indirizzo e-mail"}          
                                         , {"RUBR_RM_CG"   , "EMAIL"        , "${archagen.email_tecn}"                          , "Agente: Indirizzo e-mail per comunicazione tecnica"}          
                                         , {"RUBR_RM_CG"   , "EMAIL"        , "${archagen.email_amm}"                           , "Agente: Indirizzo e-mail per comunicazione amministrativa"}          
                                         , {"RUBR_RM_CG"   , "EMAIL"        , "${archagen.email_comm}"                          , "Agente: Indirizzo e-mail per comunicazione commerciale"}          
                                         , {"RUBR_RM_CG"   , "TESTO"        , "${archrubrica.url}"                              , "Contatto: URL"}
                                         , {"RUBR_RM_CG"   , "TESTO"        , "${archrubrica.ragcog}"                           , "Contatto: Ragione sociale"}
                                         , {"RUBR_RM_CG"   , "TESTO"        , "${archrubrica.tkrubr}"                           , "Contatto: Codice"}
                                         , {"RUBR_RM_CG"   , "TESTO"        , "${archrubrica_tipo.dsrubr_tipo}"                 , "Contatto: Tipo"}
                                         , {"RUBR_RM_CG"   , "TESTO"        , "${archagen.dsagen_agenrif}"                      , "Agente Rif.: Descrizione"}
                                         , {"RUBR_RM_CG"   , "TESTO"        , "${zonacomm.dszcom}"                              , "Zona commerciale: Descrizione"}
                                         , {"RUBR_RM_CG"   , "TESTOHTML"    , "${archrubrica_modif.nota}"                       , "Contatto: Modifica richiesta"}
                                         , {"RUBR_RM_CG"   , "TESTO"        , "${file.filename}"                                , "File: Nome file"}
                                         , {"RUBR_RM_CG"   , "TESTO"        , "${rubr_rm_cg.tbl_dati}"                          , "Contatto: Tabella riepilogativa"}
                                         , {"RUBR_RM_CG"   , "TESTO"        , "${rubr_rm_cg.tbl_mod_eff}"                       , "Contatto: Tabella riepilogativa modifiche effettuate"}
//                                         , {"RUBR_RM_CG"   , "TESTO"        , "${archclie.cdclie_m}"                            , "Cliente: Codice"}


//                                         , {"RUBR_PROSP"   , "EMAIL"        , "${archclie.email}"                               , "Cliente: Indirizzo e-mail"}
//                                         , {"RUBR_PROSP"   , "EMAIL"        , "${archclie.email_tecn}"                          , "Cliente: Indirizzo e-mail per comunicazione tecnica"}
//                                         , {"RUBR_PROSP"   , "EMAIL"        , "${archclie.email_amm}"                           , "Cliente: Indirizzo e-mail per comunicazione amministrativa"}
//                                         , {"RUBR_PROSP"   , "EMAIL"        , "${archclie.email_comm}"                          , "Cliente: Indirizzo e-mail per comunicazione commerciale"}
                                         , {"RUBR_PROSP"   , "EMAIL"        , "${archresp.email}"                               , "Responsabile vend.: Indirizzo e-mail"}
                                         , {"RUBR_PROSP"   , "EMAIL"        , "${archresp.email_tecn}"                          , "Responsabile vend.: Indirizzo e-mail per comunicazione tecnica"}
                                         , {"RUBR_PROSP"   , "EMAIL"        , "${archresp.email_amm}"                           , "Responsabile vend.: Indirizzo e-mail per comunicazione amministrativa"}
                                         , {"RUBR_PROSP"   , "EMAIL"        , "${archresp.email_comm}"                          , "Responsabile vend.: Indirizzo e-mail per comunicazione commerciale"}
                                         , {"RUBR_PROSP"   , "EMAIL"        , "${age_ispettori.email}"                          , "Area-manager: Indirizzo e-mail"}           
                                         , {"RUBR_PROSP"   , "EMAIL"        , "${age_ispettori.email_tecn}"                     , "Area-manager: Indirizzo e-mail per comunicazione tecnica"}            
                                         , {"RUBR_PROSP"   , "EMAIL"        , "${age_ispettori.email_amm}"                      , "Area-manager: Indirizzo e-mail per comunicazione amministrativa"}       
                                         , {"RUBR_PROSP"   , "EMAIL"        , "${age_ispettori.email_comm}"                     , "Area-manager: Indirizzo e-mail per comunicazione commerciale"}          
                                         , {"RUBR_PROSP"   , "EMAIL"        , "${archagen.email_capo}"                          , "Capoarea: Indirizzo e-mail"}                
                                         , {"RUBR_PROSP"   , "EMAIL"        , "${archagen.email_capo_tecn}"                     , "Capoarea: Indirizzo e-mail per comunicazione tecnica"}                
                                         , {"RUBR_PROSP"   , "EMAIL"        , "${archagen.email_capo_amm}"                      , "Capoarea: Indirizzo e-mail per comunicazione amministrativa"}                 
                                         , {"RUBR_PROSP"   , "EMAIL"        , "${archagen.email_capo_comm}"                     , "Capoarea: Indirizzo e-mail per comunicazione commerciale"}                   
                                         , {"RUBR_PROSP"   , "EMAIL"        , "${archagen.email_agenrif}"                       , "Agente Rif.: Indirizzo e-mail"}              
                                         , {"RUBR_PROSP"   , "EMAIL"        , "${archagen.email_agenrif_tecn}"                  , "Agente Rif.: Indirizzo e-mail per comunicazione tecnica"}              
                                         , {"RUBR_PROSP"   , "EMAIL"        , "${archagen.email_agenrif_amm}"                   , "Agente Rif.: Indirizzo e-mail per comunicazione amministrativa"}       
                                         , {"RUBR_PROSP"   , "EMAIL"        , "${archagen.email_agenrif_comm}"                  , "Agente Rif.: Indirizzo e-mail per comunicazione commerciale"}          
                                         , {"RUBR_PROSP"   , "EMAIL"        , "${archagen.email}"                               , "Agente: Indirizzo e-mail"}          
                                         , {"RUBR_PROSP"   , "EMAIL"        , "${archagen.email_tecn}"                          , "Agente: Indirizzo e-mail per comunicazione tecnica"}          
                                         , {"RUBR_PROSP"   , "EMAIL"        , "${archagen.email_amm}"                           , "Agente: Indirizzo e-mail per comunicazione amministrativa"}          
                                         , {"RUBR_PROSP"   , "EMAIL"        , "${archagen.email_comm}"                          , "Agente: Indirizzo e-mail per comunicazione commerciale"}          
                                         , {"RUBR_PROSP"   , "EMAIL"        , "${archrubrica_recapiti.recapito_email_lav}"      , "Contatto: Primo indirizzo e-mail"}
                                         , {"RUBR_PROSP"   , "TESTO"        , "${archrubrica.ragcog}"                           , "Contatto: Ragione sociale"}
                                         , {"RUBR_PROSP"   , "TESTO"        , "${archrubrica.perrif}"                           , "Contatto: Ragione sociale persona di riferimento"}
                                         , {"RUBR_PROSP"   , "TESTO"        , "${archrubrica.tkrubr}"                           , "Contatto: Codice"}
                                         , {"RUBR_PROSP"   , "TESTO"        , "${contator.url}"                                 , "Contatore: URL"}
                                         , {"RUBR_PROSP"   , "TESTO"        , "${archrubrica.url_contator}"                     , "Contatto: contatore URL "+ ep_site_ext_url}
                                         , {"RUBR_PROSP"   , "TESTO"        , "${archagen.dsagen}"                              , "Agente: Descrizione"}
                                         , {"RUBR_PROSP"   , "EMAIL"        , "${archrubrica.email_respv}"                      , "Contatto: Indirizzo e-mail resp. vendite"}
                                         , {"RUBR_PROSP"   , "EMAIL"        , "${archrubrica.email_aream}"                      , "Contatto: Indirizzo e-mail area-manager"}
//                                         , {"RUBR_PROSP"   , "TESTO"        , "${archclie.cdclie_m}"                            , "Cliente: Codice"}
                                         , {"RUBR_PROSP"   , "TESTO"        , "${oggi.data}"                                    , "Data del giorno in cui viene predisposto il messaggio"}
                                         , {"RUBR_PROSP"   , "TESTO"        , "${archrubrica.dtinse}"                           , "Contatto: Data inserimento"}
                                         , {"RUBR_PROSP"   , "TESTO"        , "${archrubrica.url_contator_portal}"              , "Contatto: contatore URL "+ ep_portal_url}
                                         , {"RUBR_PROSP"   , "TESTO"        , "${archresp.dsresp}"                              , "Responsabile vend.: Descrizione (Nome e Cognome)"}
                                         , {"RUBR_PROSP"   , "TESTO"        , "${archresp.cell}"                                , "Responsabile vend.: Numero cellulare"}
                                         , {"RUBR_PROSP"   , "TESTO"        , "${age_ispettori.dsispe}"                         , "Area-manager: Descrizione (Nome e Cognome)"}
                                         , {"RUBR_PROSP"   , "TESTO"        , "${age_ispettori.cell}"                           , "Area-manager: Numero cellulare"}
                                         , {"RUBR_PROSP"   , "TESTO"        , "${archagen.dsagen_agenrif}"                      , "Agente Rif.: Descrizione (Nome e Cognome)"}
                                         , {"RUBR_PROSP"   , "TESTO"        , "${archagen.cell_agenrif}"                        , "Agente Rif.: Numero cellulare"}
                                         , {"RUBR_PROSP"   , "TESTO"        , "${rubr_prosp.list_rivenditori}"                  , "Contatto: Lista rivenditori"}
                                         , {"RUBR_PROSP"   , "TESTO"        , "${rubr_prosp.text_1}"                            , "Contatto: Testo \"Egr. sig./sig.ra\""}
                                         , {"RUBR_PROSP"   , "TESTO"        , "${archrubrica.url_contator_resources}"           , "Contatto: contatore URL "+ ep_site_url+"resources/"}
                                         , {"RUBR_PROSP"   , "TESTO"        , "${archrubrica.url_contator_rivenditori}"         , "Contatto: contatore URL "+ ep_site_url+"store_locator/"}
                                         , {"RUBR_PROSP"   , "TESTO"        , "${archrubrica.url_contator_shop}"                , "Contatto: contatore URL "+ ep_shop_url}
                                         , {"RUBR_PROSP"   , "EMAIL"        , "${archrubrica.rivend1_email}"                    , "Contatto: Rivenditore 1 indirizzo e-mail"}
                                         , {"RUBR_PROSP"   , "EMAIL"        , "${archrubrica.rivend1_email_tecn}"               , "Contatto: Rivenditore 1 indirizzo e-mail per comunicazione tecnica"}
                                         , {"RUBR_PROSP"   , "EMAIL"        , "${archrubrica.rivend1_email_amm}"                , "Contatto: Rivenditore 1 indirizzo e-mail per comunicazione amministrativa"}
                                         , {"RUBR_PROSP"   , "EMAIL"        , "${archrubrica.rivend1_email_comm}"               , "Contatto: Rivenditore 1 indirizzo e-mail per comunicazione commerciale"}
                                         , {"RUBR_PROSP"   , "EMAIL"        , "${archrubrica.rivend2_email}"                    , "Contatto: Rivenditore 2 indirizzo e-mail"}
                                         , {"RUBR_PROSP"   , "EMAIL"        , "${archrubrica.rivend2_email_tecn}"               , "Contatto: Rivenditore 2 indirizzo e-mail per comunicazione tecnica"}
                                         , {"RUBR_PROSP"   , "EMAIL"        , "${archrubrica.rivend2_email_amm}"                , "Contatto: Rivenditore 2 indirizzo e-mail per comunicazione amministrativa"}
                                         , {"RUBR_PROSP"   , "EMAIL"        , "${archrubrica.rivend2_email_comm}"               , "Contatto: Rivenditore 2 indirizzo e-mail per comunicazione commerciale"}
                                         , {"RUBR_PROSP"   , "EMAIL"        , "${archrubrica.rivend3_email}"                    , "Contatto: Rivenditore 3 indirizzo e-mail"}
                                         , {"RUBR_PROSP"   , "EMAIL"        , "${archrubrica.rivend3_email_tecn}"               , "Contatto: Rivenditore 3 indirizzo e-mail per comunicazione tecnica"}
                                         , {"RUBR_PROSP"   , "EMAIL"        , "${archrubrica.rivend3_email_amm}"                , "Contatto: Rivenditore 3 indirizzo e-mail per comunicazione amministrativa"}
                                         , {"RUBR_PROSP"   , "EMAIL"        , "${archrubrica.rivend3_email_comm}"               , "Contatto: Rivenditore 3 indirizzo e-mail per comunicazione commerciale"}
                                         , {"RUBR_PROSP"   , "EMAIL"        , "${archrubrica.rivend4_email}"                    , "Contatto: Rivenditore 4 indirizzo e-mail"}
                                         , {"RUBR_PROSP"   , "EMAIL"        , "${archrubrica.rivend4_email_tecn}"               , "Contatto: Rivenditore 4 indirizzo e-mail per comunicazione tecnica"}
                                         , {"RUBR_PROSP"   , "EMAIL"        , "${archrubrica.rivend4_email_amm}"                , "Contatto: Rivenditore 4 indirizzo e-mail per comunicazione amministrativa"}
                                         , {"RUBR_PROSP"   , "EMAIL"        , "${archrubrica.rivend4_email_comm}"               , "Contatto: Rivenditore 4 indirizzo e-mail per comunicazione commerciale"}
                                         , {"RUBR_PROSP"   , "TESTO"        , "${archresp.dsptit}"                              , "Responsabile vend.: Titolo"}
                                         , {"RUBR_PROSP"   , "TESTO"        , "${age_ispettori.dsptit}"                         , "Area-manager: Titolo"}
                                         , {"RUBR_PROSP"   , "TESTO"        , "${archagen.dsptit}"                              , "Agente: Titolo"}
                                         , {"RUBR_PROSP"   , "TESTO"        , "${archagen.dsptit_agenrif}"                      , "Agente Rif.: Titolo"}
                                         , {"RUBR_PROSP"   , "TESTO"        , "${archagen.dsptit_capo}"                         , "Capoarea.: Titolo"}
                                         , {"RUBR_PROSP"   , "TESTO"        , "${rubr_prosp.text_2_azie}"                       , "Contatto: Testo \"Nella vostra zona l�agente � il sig. (nome + indirizzo mail), l�area manager � il sig. (nome + indirizzo mail), il responsabile commerciale � il sig. (nome + indirizzo mail).\" per responsabile vend."}
                                         , {"RUBR_PROSP"   , "TESTO"        , "${rubr_prosp.text_2_pers}"                       , "Contatto: Testo \"Nella Sua zona l�agente � il sig. (nome + indirizzo mail), l�area manager � il sig. (nome + indirizzo mail), il responsabile commerciale � il sig. (nome + indirizzo mail).\" per responsabile vend."}
                                         , {"RUBR_PROSP"   , "TESTO"        , "${rubr_prosp.text_3_azie}"                       , "Contatto: Testo \"Nella vostra zona l�area manager � il sig. (nome + indirizzo mail), il responsabile commerciale � il sig. (nome + indirizzo mail).\" per responsabile vend."}
                                         , {"RUBR_PROSP"   , "TESTO"        , "${rubr_prosp.text_3_pers}"                       , "Contatto: Testo \"Nella Sua zona l�area manager � il sig. (nome + indirizzo mail), il responsabile commerciale � il sig. (nome + indirizzo mail).\" per responsabile vend."}
                                         , {"RUBR_PROSP"   , "TESTO"        , "${archrubrica.url_contator_cataloghi}"           , "Contatto: contatore URL per download cataloghi"}
                                         , {"RUBR_PROSP"   , "TESTO"        , "${rubr_prosp.list_rivenditori_pers}"             , "Contatto: Lista rivenditori con testo rivolto a singola persona"}
                                         , {"RUBR_PROSP"   , "TESTO"        , "${rubr_prosp.list_rivenditori_azie}"             , "Contatto: Lista rivenditori con testo rivolto ad azienda"}
                                         , {"RUBR_PROSP"   , "TESTO"        , "${vist_oridati.dsoridati}"                       , "Origine dati: Descrizione"}
                                         

                                         //SCADENZE


//                                         , {"SCADENZE"     , "EMAIL"        , "${archclie.email}"                               , "Cliente: Indirizzo e-mail"}
//                                         , {"SCADENZE"     , "EMAIL"        , "${archclie.email_tecn}"                          , "Cliente: Indirizzo e-mail per comunicazione tecnica"}
//                                         , {"SCADENZE"     , "EMAIL"        , "${archclie.email_amm}"                           , "Cliente: Indirizzo e-mail per comunicazione amministrativa"}
//                                         , {"SCADENZE"     , "EMAIL"        , "${archclie.email_comm}"                          , "Cliente: Indirizzo e-mail per comunicazione commerciale"}
                                         , {"SCADENZE"     , "EMAIL"        , "${ep_utente.email}"                              , "Utente: Indirizzo e-mail"}
                                         , {"SCADENZE"     , "EMAIL"        , "${ep_utente.email_tecn}"                         , "Utente: Indirizzo e-mail per comunicazione tecnica"}
                                         , {"SCADENZE"     , "EMAIL"        , "${ep_utente.email_amm}"                          , "Utente: Indirizzo e-mail per comunicazione amministrativa"}
                                         , {"SCADENZE"     , "EMAIL"        , "${ep_utente.email_comm}"                         , "Utente: Indirizzo e-mail per comunicazione commerciale"}
                                         , {"SCADENZE"     , "EMAIL"        , "${archresp.email}"                               , "Responsabile vend.: Indirizzo e-mail"}              
                                         , {"SCADENZE"     , "EMAIL"        , "${archresp.email_tecn}"                          , "Responsabile vend.: Indirizzo e-mail per comunicazione tecnica"}              
                                         , {"SCADENZE"     , "EMAIL"        , "${archresp.email_amm}"                           , "Responsabile vend.: Indirizzo e-mail per comunicazione amministrativa"}              
                                         , {"SCADENZE"     , "EMAIL"        , "${archresp.email_comm}"                          , "Responsabile vend.: Indirizzo e-mail per comunicazione commerciale"}              
                                         , {"SCADENZE"     , "EMAIL"        , "${age_ispettori.email}"                          , "Area-manager: Indirizzo e-mail"}                         
                                         , {"SCADENZE"     , "EMAIL"        , "${age_ispettori.email_tecn}"                     , "Area-manager: Indirizzo e-mail per comunicazione tecnica"}                         
                                         , {"SCADENZE"     , "EMAIL"        , "${age_ispettori.email_amm}"                      , "Area-manager: Indirizzo e-mail per comunicazione amministrativa"}                    
                                         , {"SCADENZE"     , "EMAIL"        , "${age_ispettori.email_comm}"                     , "Area-manager: Indirizzo e-mail per comunicazione commerciale"}                       
                                         , {"SCADENZE"     , "EMAIL"        , "${archagen.email_capo}"                          , "Capoarea: Indirizzo e-mail"}                          
                                         , {"SCADENZE"     , "EMAIL"        , "${archagen.email_capo_tecn}"                     , "Capoarea: Indirizzo e-mail per comunicazione tecnica"}                          
                                         , {"SCADENZE"     , "EMAIL"        , "${archagen.email_capo_amm}"                      , "Capoarea: Indirizzo e-mail per comunicazione amministrativa"}                        
                                         , {"SCADENZE"     , "EMAIL"        , "${archagen.email_capo_comm}"                     , "Capoarea: Indirizzo e-mail per comunicazione commerciale"}                          
                                         , {"SCADENZE"     , "EMAIL"        , "${archagen.email_agenrif}"                       , "Agente Rif.: Indirizzo e-mail"}                            
                                         , {"SCADENZE"     , "EMAIL"        , "${archagen.email_agenrif_tecn}"                  , "Agente Rif.: Indirizzo e-mail per comunicazione tecnica"}                            
                                         , {"SCADENZE"     , "EMAIL"        , "${archagen.email_agenrif_amm}"                   , "Agente Rif.: Indirizzo e-mail per comunicazione amministrativa"}                     
                                         , {"SCADENZE"     , "EMAIL"        , "${archagen.email_agenrif_comm}"                  , "Agente Rif.: Indirizzo e-mail per comunicazione commerciale"}                        
                                         , {"SCADENZE"     , "EMAIL"        , "${archagen.email}"                               , "Agente: Indirizzo e-mail"}                        
                                         , {"SCADENZE"     , "EMAIL"        , "${archagen.email_tecn}"                          , "Agente: Indirizzo e-mail per comunicazione tecnica"}                        
                                         , {"SCADENZE"     , "EMAIL"        , "${archagen.email_amm}"                           , "Agente: Indirizzo e-mail per comunicazione amministrativa"}                        
                                         , {"SCADENZE"     , "EMAIL"        , "${archagen.email_comm}"                          , "Agente: Indirizzo e-mail per comunicazione commerciale"}                        
                                         , {"SCADENZE"     , "TESTO"        , "${scadenze.url}"                                 , "Scadenze: URL"}
                                         , {"SCADENZE"     , "TESTO"        , "${archenti.ragcog}"                              , "Cliente: Ragione sociale"}
                                         , {"SCADENZE"     , "TESTOHTML"    , "${scadenze.tbl_scadenze}"                        , "Scadenze: Tabella scadenze"}
                                         , {"SCADENZE"     , "TESTOHTML"    , "${scadenze.tbl_scadenze_dett}"                   , "Scadenze: Castelletto scadenze per forma pagamento"}
                                         , {"SCADENZE"     , "TESTO"        , "${oggetto.text_clie}"                            , "Oggetto: suffisso con ragione sociale cliente"}
                                         , {"SCADENZE"     , "TESTO"        , "${keycode.url_portal}"                           , "Keycode: URL portale con keycode (solo per cliente)"}
                                         , {"SCADENZE"     , "TESTO"        , "${ep_utente.tipologia_utente}"                   , "Utente: Descrizione tipologia utente"}
                                         , {"SCADENZE"     , "TESTO"        , "${ep_utente.dsutente}"                           , "Utente: Descrizione"}
//                                         , {"SCADENZE"     , "TESTO"        , "${archclie.cdclie_m}"                            , "Cliente: Codice"}
                                         , {"SCADENZE"     , "EMAIL"        , "${scadenze.resp_email}"                          , "Scadenze: Indirizzo e-mail del resp. vend. delle scadenze (solo per variante \"Invio da/a area-manager, capo-area, agente o sub-agente\")"}              
                                         , {"SCADENZE"     , "EMAIL"        , "${scadenze.resp_email_tecn}"                     , "Scadenze: Indirizzo e-mail per comunicazione tecnica del resp. vend. delle scadenze (solo per variante \"Invio da/a area-manager, capo-area, agente o sub-agente\")"}              
                                         , {"SCADENZE"     , "EMAIL"        , "${scadenze.resp_email_amm}"                      , "Scadenze: Indirizzo e-mail per comunicazione amministrativa del resp. vend. delle scadenze (solo per variante \"Invio da/a area-manager, capo-area, agente o sub-agente\")"}              
                                         , {"SCADENZE"     , "EMAIL"        , "${scadenze.resp_email_comm}"                     , "Scadenze: Indirizzo e-mail per comunicazione commerciale del resp. vend. delle scadenze (solo per variante \"Invio da/a area-manager, capo-area, agente o sub-agente\")"}              
                                         , {"SCADENZE"     , "TESTO"        , "${scadenze.resp_descr}"                          , "Scadenze: Descrizione del resp. vend. delle scadenze (solo per variante \"Invio da/a area-manager, capo-area, agente o sub-agente\")"}              
                                         , {"SCADENZE"     , "TESTO"        , "${scadenze.totale_residuo_notifica}"             , "Scadenze: Totale residuo delle scadenze inviate nella notificate (solo per varianti cliente)"}              
                                         , {"SCADENZE"     , "TESTOHTML"    , "${scadenze.tbl_scadenze_scadute}"                , "Scadenze: Tabella scadenze scadute"}
                                         , {"SCADENZE"     , "TESTOHTML"    , "${scadenze.tbl_scadenze_future}"                 , "Scadenze: Tabella scadenze future"}
                                         , {"SCADENZE"     , "TESTO"        , "${scadenze.totale_residuo_scadenze_scadute}"     , "Scadenze: Totale residuo delle scadenze scadute"}              
                                         , {"SCADENZE"     , "TESTOHTML"    , "${scadenze.totale_residuo_scadenze_future}"      , "Scadenze: Tabella scadenze scadute"}
                                         , {"SCADENZE"     , "TESTO"        , "${scadenze.dati_bancari_di_riferimento}"         , "Scadenze: Dati bancari di riferimento (solo se utente � cliente)"}
                                         
                                         //VENDITA


                                         , {"OV_CONF"      , "EMAIL"        , "${archclie.email}"                               , "Cliente: Indirizzo e-mail"}
                                         , {"OV_CONF"      , "EMAIL"        , "${archclie.email_tecn}"                          , "Cliente: Indirizzo e-mail per comunicazione tecnica"}
                                         , {"OV_CONF"      , "EMAIL"        , "${archclie.email_amm}"                           , "Cliente: Indirizzo e-mail per comunicazione amministrativa"}
                                         , {"OV_CONF"      , "EMAIL"        , "${archclie.email_comm}"                          , "Cliente: Indirizzo e-mail per comunicazione commerciale"}
                                         , {"OV_CONF"      , "EMAIL"        , "${archresp.email}"                               , "Responsabile vend.: Indirizzo e-mail"}                      
                                         , {"OV_CONF"      , "EMAIL"        , "${archresp.email_tecn}"                          , "Responsabile vend.: Indirizzo e-mail per comunicazione tecnica"}                      
                                         , {"OV_CONF"      , "EMAIL"        , "${archresp.email_amm}"                           , "Responsabile vend.: Indirizzo e-mail per comunicazione amministrativa"}                      
                                         , {"OV_CONF"      , "EMAIL"        , "${archresp.email_comm}"                          , "Responsabile vend.: Indirizzo e-mail per comunicazione commerciale"}                      
                                         , {"OV_CONF"      , "EMAIL"        , "${age_ispettori.email}"                          , "Area-manager: Indirizzo e-mail"}                         
                                         , {"OV_CONF"      , "EMAIL"        , "${age_ispettori.email_tecn}"                     , "Area-manager: Indirizzo e-mail per comunicazione tecnica"}                          
                                         , {"OV_CONF"      , "EMAIL"        , "${age_ispettori.email_amm}"                      , "Area-manager: Indirizzo e-mail per comunicazione amministrativa"}                     
                                         , {"OV_CONF"      , "EMAIL"        , "${age_ispettori.email_comm}"                     , "Area-manager: Indirizzo e-mail per comunicazione commerciale"}                        
                                         , {"OV_CONF"      , "EMAIL"        , "${archagen.email_capo}"                          , "Capoarea: Indirizzo e-mail"}                                
                                         , {"OV_CONF"      , "EMAIL"        , "${archagen.email_capo_tecn}"                     , "Capoarea: Indirizzo e-mail per comunicazione tecnica"}                                
                                         , {"OV_CONF"      , "EMAIL"        , "${archagen.email_capo_amm}"                      , "Capoarea: Indirizzo e-mail per comunicazione amministrativa"}                              
                                         , {"OV_CONF"      , "EMAIL"        , "${archagen.email_capo_comm}"                     , "Capoarea: Indirizzo e-mail per comunicazione commerciale"}                                
                                         , {"OV_CONF"      , "EMAIL"        , "${archagen.email_agenrif}"                       , "Agente Rif.: Indirizzo e-mail"}                            
                                         , {"OV_CONF"      , "EMAIL"        , "${archagen.email_agenrif_tecn}"                  , "Agente Rif.: Indirizzo e-mail per comunicazione tecnica"}                            
                                         , {"OV_CONF"      , "EMAIL"        , "${archagen.email_agenrif_amm}"                   , "Agente Rif.: Indirizzo e-mail per comunicazione amministrativa"}                     
                                         , {"OV_CONF"      , "EMAIL"        , "${archagen.email_agenrif_comm}"                  , "Agente Rif.: Indirizzo e-mail per comunicazione commerciale"}                        
                                         , {"OV_CONF"      , "EMAIL"        , "${archagen.email}"                               , "Agente: Indirizzo e-mail"}                        
                                         , {"OV_CONF"      , "EMAIL"        , "${archagen.email_tecn}"                          , "Agente: Indirizzo e-mail per comunicazione tecnica"}                        
                                         , {"OV_CONF"      , "EMAIL"        , "${archagen.email_amm}"                           , "Agente: Indirizzo e-mail per comunicazione amministrativa"}                        
                                         , {"OV_CONF"      , "EMAIL"        , "${archagen.email_comm}"                          , "Agente: Indirizzo e-mail per comunicazione commerciale"}                        
                                         , {"OV_CONF"      , "TESTO"        , "${ord_test.cdrifo}"                              , "Testata ordine: Rif. OV cliente"}
                                         , {"OV_CONF"      , "TESTO"        , "${ord_test.cdordi}"                              , "Testata ordine: Codice OV"}
                                         , {"OV_CONF"      , "TESTO"        , "${ord_test.dtordi}"                              , "Testata ordine: Data OV"}
                                         , {"OV_CONF"      , "TESTO"        , "${archenti.ragcog}"                              , "Cliente: Ragione sociale"}
                                         , {"OV_CONF"      , "TESTO"        , "${ov_conf.text_cdrifo}"                          , "Conf. OV: Testo Rif. OV cliente"}
                                         , {"OV_CONF"      , "TESTO"        , "${oggetto.text_clie}"                            , "Oggetto: suffisso con ragione sociale cliente"}
                                         , {"OV_CONF"      , "TESTO"        , "${archclie.url_portal_keycode}"                  , "Keycode: URL portale con keycode (solo per cliente)"}
                                         , {"OV_CONF"      , "TESTO"        , "${archclie.cdclie_m}"                            , "Cliente: Codice"}
                                         , {"OV_CONF"      , "TESTO"        , "${ov_lista.url_keycode}"                         , "Lista ordine: URL con keycode (solo per cliente)"}                      
                                         , {"OV_CONF"      , "EMAIL"        , "${ov.agen_email}"                                , "Agente Ordine: Indirizzo e-mail"}                        
                                         , {"OV_CONF"      , "EMAIL"        , "${ov.agen_email_tecn}"                           , "Agente Ordine: Indirizzo e-mail per comunicazione tecnica"}                        
                                         , {"OV_CONF"      , "EMAIL"        , "${ov.agen_email_amm}"                            , "Agente Ordine: Indirizzo e-mail per comunicazione amministrativa"}                        
                                         , {"OV_CONF"      , "EMAIL"        , "${ov.agen_email_comm}"                           , "Agente Ordine: Indirizzo e-mail per comunicazione commerciale"}         
                                         , {"OV_CONF"      , "TESTO"        , "${ov_fattprof.url_keycode}"                      , "Fattura proforma ordine: URL con keycode (solo per cliente)"}                      


                                         , {"OV_RITSPED"   , "EMAIL"        , "${archclie.email}"                               , "Cliente: Indirizzo e-mail"}
                                         , {"OV_RITSPED"   , "EMAIL"        , "${archclie.email_tecn}"                          , "Cliente: Indirizzo e-mail per comunicazione tecnica"}
                                         , {"OV_RITSPED"   , "EMAIL"        , "${archclie.email_amm}"                           , "Cliente: Indirizzo e-mail per comunicazione amministrativa"}
                                         , {"OV_RITSPED"   , "EMAIL"        , "${archclie.email_comm}"                          , "Cliente: Indirizzo e-mail per comunicazione commerciale"}
                                         , {"OV_RITSPED"   , "EMAIL"        , "${archresp.email}"                               , "Responsabile vend.: Indirizzo e-mail"}                      
                                         , {"OV_RITSPED"   , "EMAIL"        , "${archresp.email_tecn}"                          , "Responsabile vend.: Indirizzo e-mail per comunicazione tecnica"}                             
                                         , {"OV_RITSPED"   , "EMAIL"        , "${archresp.email_amm}"                           , "Responsabile vend.: Indirizzo e-mail per comunicazione amministrativa"}                        
                                         , {"OV_RITSPED"   , "EMAIL"        , "${archresp.email_comm}"                          , "Responsabile vend.: Indirizzo e-mail per comunicazione commerciale"}                           
                                         , {"OV_RITSPED"   , "EMAIL"        , "${age_ispettori.email}"                          , "Area-manager: Indirizzo e-mail"}                         
                                         , {"OV_RITSPED"   , "EMAIL"        , "${age_ispettori.email_tecn}"                     , "Area-manager: Indirizzo e-mail per comunicazione tecnica"}                          
                                         , {"OV_RITSPED"   , "EMAIL"        , "${age_ispettori.email_amm}"                      , "Area-manager: Indirizzo e-mail per comunicazione amministrativa"}                       
                                         , {"OV_RITSPED"   , "EMAIL"        , "${age_ispettori.email_comm}"                     , "Area-manager: Indirizzo e-mail per comunicazione commerciale"}                         
                                         , {"OV_RITSPED"   , "EMAIL"        , "${archagen.email_capo}"                          , "Capoarea: Indirizzo e-mail"}                                
                                         , {"OV_RITSPED"   , "EMAIL"        , "${archagen.email_capo_tecn}"                     , "Capoarea: Indirizzo e-mail per comunicazione tecnica"}                                       
                                         , {"OV_RITSPED"   , "EMAIL"        , "${archagen.email_capo_amm}"                      , "Capoarea: Indirizzo e-mail per comunicazione amministrativa"}                                
                                         , {"OV_RITSPED"   , "EMAIL"        , "${archagen.email_capo_comm}"                     , "Capoarea: Indirizzo e-mail per comunicazione commerciale"}                                   
                                         , {"OV_RITSPED"   , "EMAIL"        , "${archagen.email_agenrif}"                       , "Agente Rif.: Indirizzo e-mail"}                            
                                         , {"OV_RITSPED"   , "EMAIL"        , "${archagen.email_agenrif_tecn}"                  , "Agente Rif.: Indirizzo e-mail per comunicazione tecnica"}                            
                                         , {"OV_RITSPED"   , "EMAIL"        , "${archagen.email_agenrif_amm}"                   , "Agente Rif.: Indirizzo e-mail per comunicazione amministrativa"}                     
                                         , {"OV_RITSPED"   , "EMAIL"        , "${archagen.email_agenrif_comm}"                  , "Agente Rif.: Indirizzo e-mail per comunicazione commerciale"}                        
                                         , {"OV_RITSPED"   , "EMAIL"        , "${archagen.email}"                               , "Agente: Indirizzo e-mail"}                        
                                         , {"OV_RITSPED"   , "EMAIL"        , "${archagen.email_tecn}"                          , "Agente: Indirizzo e-mail per comunicazione tecnica"}                        
                                         , {"OV_RITSPED"   , "EMAIL"        , "${archagen.email_amm}"                           , "Agente: Indirizzo e-mail per comunicazione amministrativa"}                        
                                         , {"OV_RITSPED"   , "EMAIL"        , "${archagen.email_comm}"                          , "Agente: Indirizzo e-mail per comunicazione commerciale"}                        
                                         , {"OV_RITSPED"   , "TESTO"        , "${ord_test.cdrifo}"                              , "Testata ordine: Rif. OV cliente"}
                                         , {"OV_RITSPED"   , "TESTO"        , "${ord_test.cdordi}"                              , "Testata ordine: Codice OV"}
                                         , {"OV_RITSPED"   , "TESTO"        , "${ord_test.dtordi}"                              , "Testata ordine: Data OV"}
                                         , {"OV_RITSPED"   , "TESTO"        , "${archenti.ragcog}"                              , "Cliente: Ragione sociale"}
                                         , {"OV_RITSPED"   , "TESTO"        , "${archagen.dsagen_agenrif}"                      , "Agente Rif.: descrizione"}
                                         , {"OV_RITSPED"   , "TESTO"        , "${archagen.cell_agenrif}"                        , "Agente Rif.: Numero cellulare"}
                                         , {"OV_RITSPED"   , "TESTOHTML"    , "${ov_ritsped.text_rif_da_contattare}"            , "Rit. Sped OV: Testo con riferimenti da contattare"}
                                         , {"OV_RITSPED"   , "TESTO"        , "${oggetto.text_clie}"                            , "Oggetto: suffisso con ragione sociale cliente"}
                                         , {"OV_RITSPED"   , "TESTO"        , "${archclie.url_portal_keycode}"                  , "Keycode: URL portale con keycode (solo per cliente)"}
                                         , {"OV_RITSPED"   , "TESTO"        , "${archclie.cdclie_m}"                            , "Cliente: Codice"}


                                         , {"OV_AVVSPED"   , "EMAIL"        , "${archclie.email}"                               , "Cliente: Indirizzo e-mail"}
                                         , {"OV_AVVSPED"   , "EMAIL"        , "${archclie.email_tecn}"                          , "Cliente: Indirizzo e-mail per comunicazione tecnica"}
                                         , {"OV_AVVSPED"   , "EMAIL"        , "${archclie.email_amm}"                           , "Cliente: Indirizzo e-mail per comunicazione amministrativa"}
                                         , {"OV_AVVSPED"   , "EMAIL"        , "${archclie.email_comm}"                          , "Cliente: Indirizzo e-mail per comunicazione commerciale"}
                                         , {"OV_AVVSPED"   , "EMAIL"        , "${archresp.email}"                               , "Responsabile vend.: Indirizzo e-mail"}                      
                                         , {"OV_AVVSPED"   , "EMAIL"        , "${archresp.email_tecn}"                          , "Responsabile vend.: Indirizzo e-mail per comunicazione tecnica"}                     
                                         , {"OV_AVVSPED"   , "EMAIL"        , "${archresp.email_amm}"                           , "Responsabile vend.: Indirizzo e-mail per comunicazione amministrativa"}              
                                         , {"OV_AVVSPED"   , "EMAIL"        , "${archresp.email_comm}"                          , "Responsabile vend.: Indirizzo e-mail per comunicazione commerciale"}                 
                                         , {"OV_AVVSPED"   , "EMAIL"        , "${age_ispettori.email}"                          , "Area-manager: Indirizzo e-mail"}                         
                                         , {"OV_AVVSPED"   , "EMAIL"        , "${age_ispettori.email_tecn}"                     , "Area-manager: Indirizzo e-mail per comunicazione tecnica"}                          
                                         , {"OV_AVVSPED"   , "EMAIL"        , "${age_ispettori.email_amm}"                      , "Area-manager: Indirizzo e-mail per comunicazione amministrativa"}                    
                                         , {"OV_AVVSPED"   , "EMAIL"        , "${age_ispettori.email_comm}"                     , "Area-manager: Indirizzo e-mail per comunicazione commerciale"}                       
                                         , {"OV_AVVSPED"   , "EMAIL"        , "${archagen.email_capo}"                          , "Capoarea: Indirizzo e-mail"}                                
                                         , {"OV_AVVSPED"   , "EMAIL"        , "${archagen.email_capo_tecn}"                     , "Capoarea: Indirizzo e-mail per comunicazione tecnica"}                               
                                         , {"OV_AVVSPED"   , "EMAIL"        , "${archagen.email_capo_amm}"                      , "Capoarea: Indirizzo e-mail per comunicazione amministrativa"}                        
                                         , {"OV_AVVSPED"   , "EMAIL"        , "${archagen.email_capo_comm}"                     , "Capoarea: Indirizzo e-mail per comunicazione commerciale"}                           
                                         , {"OV_AVVSPED"   , "EMAIL"        , "${archagen.email_agenrif}"                       , "Agente Rif.: Indirizzo e-mail"}                            
                                         , {"OV_AVVSPED"   , "EMAIL"        , "${archagen.email_agenrif_tecn}"                  , "Agente Rif.: Indirizzo e-mail per comunicazione tecnica"}                            
                                         , {"OV_AVVSPED"   , "EMAIL"        , "${archagen.email_agenrif_amm}"                   , "Agente Rif.: Indirizzo e-mail per comunicazione amministrativa"}                     
                                         , {"OV_AVVSPED"   , "EMAIL"        , "${archagen.email_agenrif_comm}"                  , "Agente Rif.: Indirizzo e-mail per comunicazione commerciale"}                        
                                         , {"OV_AVVSPED"   , "EMAIL"        , "${archagen.email}"                               , "Agente: Indirizzo e-mail"}                        
                                         , {"OV_AVVSPED"   , "EMAIL"        , "${archagen.email_tecn}"                          , "Agente: Indirizzo e-mail per comunicazione tecnica"}                        
                                         , {"OV_AVVSPED"   , "EMAIL"        , "${archagen.email_amm}"                           , "Agente: Indirizzo e-mail per comunicazione amministrativa"}                        
                                         , {"OV_AVVSPED"   , "EMAIL"        , "${archagen.email_comm}"                          , "Agente: Indirizzo e-mail per comunicazione commerciale"}                        
                                         , {"OV_AVVSPED"   , "TESTO"        , "${bolla_test.dtboll}"                            , "Testata bolle/fatt: Data"}
                                         , {"OV_AVVSPED"   , "TESTO"        , "${archenti.ragcog}"                              , "Cliente: Ragione sociale"}
                                         , {"OV_AVVSPED"   , "TESTO"        , "${bolla_test.url_spedizione}"                    , "Testata bolle/fatt: URL spedizione"}
                                         , {"OV_AVVSPED"   , "TESTO"        , "${bolla_test.url_spedizione_DROPSHIP}"           , "Testata bolle/fatt: URL spedizione a cliente finale"}
                                         , {"OV_AVVSPED"   , "TESTOHTML"    , "${ov_avvsped.text_link_spedizione}"              , "Rit. Sped OV: Testo con riferimenti da contattare"}
                                         , {"OV_AVVSPED"   , "TESTO"        , "${oggetto.text_clie}"                            , "Oggetto: suffisso con ragione sociale cliente"}
                                         , {"OV_AVVSPED"   , "TESTO"        , "${archclie.url_portal_keycode}"                  , "Keycode: URL portale con keycode (solo per cliente)"}
                                         , {"OV_AVVSPED"   , "TESTO"        , "${archclie.cdclie_m}"                            , "Cliente: Codice"}


                                         , {"OV_RICEZ"     , "EMAIL"        , "${archclie.email}"                               , "Cliente: Indirizzo e-mail"}
                                         , {"OV_RICEZ"     , "EMAIL"        , "${archclie.email_tecn}"                          , "Cliente: Indirizzo e-mail per comunicazione tecnica"}
                                         , {"OV_RICEZ"     , "EMAIL"        , "${archclie.email_amm}"                           , "Cliente: Indirizzo e-mail per comunicazione amministrativa"}
                                         , {"OV_RICEZ"     , "EMAIL"        , "${archclie.email_comm}"                          , "Cliente: Indirizzo e-mail per comunicazione commerciale"}
                                         , {"OV_RICEZ"     , "EMAIL"        , "${ep_utente.email}"                              , "Utente: Indirizzo e-mail"}
                                         , {"OV_RICEZ"     , "EMAIL"        , "${ep_utente.email_tecn}"                         , "Utente: Indirizzo e-mail per comunicazione tecnica"}  
                                         , {"OV_RICEZ"     , "EMAIL"        , "${ep_utente.email_amm}"                          , "Utente: Indirizzo e-mail per comunicazione amministrativa"}
                                         , {"OV_RICEZ"     , "EMAIL"        , "${ep_utente.email_comm}"                         , "Utente: Indirizzo e-mail per comunicazione commerciale"}
                                         , {"OV_RICEZ"     , "EMAIL"        , "${archresp.email}"                               , "Responsabile vend.: Indirizzo e-mail"}                      
                                         , {"OV_RICEZ"     , "EMAIL"        , "${archresp.email_tecn}"                          , "Responsabile vend.: Indirizzo e-mail per comunicazione tecnica"}                     
                                         , {"OV_RICEZ"     , "EMAIL"        , "${archresp.email_amm}"                           , "Responsabile vend.: Indirizzo e-mail per comunicazione amministrativa"}              
                                         , {"OV_RICEZ"     , "EMAIL"        , "${archresp.email_comm}"                          , "Responsabile vend.: Indirizzo e-mail per comunicazione commerciale"}                 
                                         , {"OV_RICEZ"     , "EMAIL"        , "${age_ispettori.email}"                          , "Area-manager: Indirizzo e-mail"}                         
                                         , {"OV_RICEZ"     , "EMAIL"        , "${age_ispettori.email_tecn}"                     , "Area-manager: Indirizzo e-mail per comunicazione tecnica"}                          
                                         , {"OV_RICEZ"     , "EMAIL"        , "${age_ispettori.email_amm}"                      , "Area-manager: Indirizzo e-mail per comunicazione amministrativa"}                    
                                         , {"OV_RICEZ"     , "EMAIL"        , "${age_ispettori.email_comm}"                     , "Area-manager: Indirizzo e-mail per comunicazione commerciale"}                       
                                         , {"OV_RICEZ"     , "EMAIL"        , "${archagen.email_capo}"                          , "Capoarea: Indirizzo e-mail"}                                
                                         , {"OV_RICEZ"     , "EMAIL"        , "${archagen.email_capo_tecn}"                     , "Capoarea: Indirizzo e-mail per comunicazione tecnica"}                               
                                         , {"OV_RICEZ"     , "EMAIL"        , "${archagen.email_capo_amm}"                      , "Capoarea: Indirizzo e-mail per comunicazione amministrativa"}                        
                                         , {"OV_RICEZ"     , "EMAIL"        , "${archagen.email_capo_comm}"                     , "Capoarea: Indirizzo e-mail per comunicazione commerciale"}                           
                                         , {"OV_RICEZ"     , "EMAIL"        , "${archagen.email_agenrif}"                       , "Agente Rif.: Indirizzo e-mail"}                            
                                         , {"OV_RICEZ"     , "EMAIL"        , "${archagen.email_agenrif_tecn}"                  , "Agente Rif.: Indirizzo e-mail per comunicazione tecnica"}                            
                                         , {"OV_RICEZ"     , "EMAIL"        , "${archagen.email_agenrif_amm}"                   , "Agente Rif.: Indirizzo e-mail per comunicazione amministrativa"}                     
                                         , {"OV_RICEZ"     , "EMAIL"        , "${archagen.email_agenrif_comm}"                  , "Agente Rif.: Indirizzo e-mail per comunicazione commerciale"}                        
                                         , {"OV_RICEZ"     , "EMAIL"        , "${archagen.email}"                               , "Agente: Indirizzo e-mail"}                        
                                         , {"OV_RICEZ"     , "EMAIL"        , "${archagen.email_tecn}"                          , "Agente: Indirizzo e-mail per comunicazione tecnica"}                        
                                         , {"OV_RICEZ"     , "EMAIL"        , "${archagen.email_amm}"                           , "Agente: Indirizzo e-mail per comunicazione amministrativa"}                        
                                         , {"OV_RICEZ"     , "EMAIL"        , "${archagen.email_comm}"                          , "Agente: Indirizzo e-mail per comunicazione commerciale"}                        
                                         , {"OV_RICEZ"     , "EMAIL"        , "${ov_ricez.atk_mail}"                            , "Ateikon: Indirizzo e-mail"}
                                         , {"OV_RICEZ"     , "TESTOHTML"    , "${ov_ricez.dati_riepilogo_ordine}"               , "Ricezione ordine OV: Tabella intestazione ordine web"}
                                         , {"OV_RICEZ"     , "TESTOHTML"    , "${ov_ricez.dati_nota_pagamento}"                 , "Ricezione ordine OV: Dati nota pagamento"}
                                         , {"OV_RICEZ"     , "TESTOHTML"    , "${ov_ricez.ls_tbl_order_details}"                , "Ricezione ordine OV: Tabella con righe dei prodotti"}
                                         , {"OV_RICEZ"     , "TESTO"        , "${ov_ricez.numero_ordine}"                       , "Ricezione ordine OV: Codice ordine per oggetto"}
                                         , {"OV_RICEZ"     , "TESTO"        , "${ov_ricez.spettabile}"                          , "Cliente: nominativo"}
                                         , {"OV_RICEZ"     , "TESTO"        , "${ov_tracking.url_keycode}"                      , "Tracking ordine: URL con keycode (solo per cliente)"}
                                         , {"OV_RICEZ"     , "TESTO"        , "${archclie.cdclie_m}"                            , "Cliente: Codice"}
                                         , {"OV_RICEZ"     , "TESTO"        , "${ow_fattprof.url_keycode}"                      , "Fattura proforma ordine WEB: URL con keycode (solo per cliente)"}   
                
                                         , {"OV_MPRON"     , "EMAIL"        , "${archclie.email}"                               , "Cliente: Indirizzo e-mail"}
                                         , {"OV_MPRON"     , "EMAIL"        , "${archclie.email_tecn}"                          , "Cliente: Indirizzo e-mail per comunicazione tecnica"}
                                         , {"OV_MPRON"     , "EMAIL"        , "${archclie.email_amm}"                           , "Cliente: Indirizzo e-mail per comunicazione amministrativa"}
                                         , {"OV_MPRON"     , "EMAIL"        , "${archclie.email_comm}"                          , "Cliente: Indirizzo e-mail per comunicazione commerciale"}    
                                         , {"OV_MPRON"     , "EMAIL"        , "${archresp.email}"                               , "Responsabile vend.: Indirizzo e-mail"}                      
                                         , {"OV_MPRON"     , "EMAIL"        , "${archresp.email_tecn}"                          , "Responsabile vend.: Indirizzo e-mail per comunicazione tecnica"}                     
                                         , {"OV_MPRON"     , "EMAIL"        , "${archresp.email_amm}"                           , "Responsabile vend.: Indirizzo e-mail per comunicazione amministrativa"}              
                                         , {"OV_MPRON"     , "EMAIL"        , "${archresp.email_comm}"                          , "Responsabile vend.: Indirizzo e-mail per comunicazione commerciale"}                 
                                         , {"OV_MPRON"     , "EMAIL"        , "${age_ispettori.email}"                          , "Area-manager: Indirizzo e-mail"}                         
                                         , {"OV_MPRON"     , "EMAIL"        , "${age_ispettori.email_tecn}"                     , "Area-manager: Indirizzo e-mail per comunicazione tecnica"}                          
                                         , {"OV_MPRON"     , "EMAIL"        , "${age_ispettori.email_amm}"                      , "Area-manager: Indirizzo e-mail per comunicazione amministrativa"}                    
                                         , {"OV_MPRON"     , "EMAIL"        , "${age_ispettori.email_comm}"                     , "Area-manager: Indirizzo e-mail per comunicazione commerciale"}                       
                                         , {"OV_MPRON"     , "EMAIL"        , "${archagen.email_capo}"                          , "Capoarea: Indirizzo e-mail"}                                
                                         , {"OV_MPRON"     , "EMAIL"        , "${archagen.email_capo_tecn}"                     , "Capoarea: Indirizzo e-mail per comunicazione tecnica"}                               
                                         , {"OV_MPRON"     , "EMAIL"        , "${archagen.email_capo_amm}"                      , "Capoarea: Indirizzo e-mail per comunicazione amministrativa"}                        
                                         , {"OV_MPRON"     , "EMAIL"        , "${archagen.email_capo_comm}"                     , "Capoarea: Indirizzo e-mail per comunicazione commerciale"}                           
                                         , {"OV_MPRON"     , "EMAIL"        , "${archagen.email_agenrif}"                       , "Agente Rif.: Indirizzo e-mail"}                            
                                         , {"OV_MPRON"     , "EMAIL"        , "${archagen.email_agenrif_tecn}"                  , "Agente Rif.: Indirizzo e-mail per comunicazione tecnica"}                            
                                         , {"OV_MPRON"     , "EMAIL"        , "${archagen.email_agenrif_amm}"                   , "Agente Rif.: Indirizzo e-mail per comunicazione amministrativa"}                     
                                         , {"OV_MPRON"     , "EMAIL"        , "${archagen.email_agenrif_comm}"                  , "Agente Rif.: Indirizzo e-mail per comunicazione commerciale"}                        
                                         , {"OV_MPRON"     , "EMAIL"        , "${archagen.email}"                               , "Agente: Indirizzo e-mail"}                        
                                         , {"OV_MPRON"     , "EMAIL"        , "${archagen.email_tecn}"                          , "Agente: Indirizzo e-mail per comunicazione tecnica"}                        
                                         , {"OV_MPRON"     , "EMAIL"        , "${archagen.email_amm}"                           , "Agente: Indirizzo e-mail per comunicazione amministrativa"}                        
                                         , {"OV_MPRON"     , "EMAIL"        , "${archagen.email_comm}"                          , "Agente: Indirizzo e-mail per comunicazione commerciale"}                        
                                         , {"OV_MPRON"     , "TESTO"        , "${archenti.ragcog}"                              , "Cliente: Ragione sociale"}
                                         , {"OV_MPRON"     , "TESTOHTML"    , "${ov_mpron.tbl_righe_ov}"                        , "Merce pronta OV: Tabella con righe ordine pronte"}
                                         , {"OV_MPRON"     , "TESTOHTML"    , "${ov_mpron.tbl_righe_scad}"                      , "Merce pronta OV: Tabella con righe scadenze/note d'accredito"}
                                         , {"OV_MPRON"     , "TESTOHTML"    , "${ov_mpron.text_clie_pag_anticipato}"            , "Merce pronta OV: Testo per clienti con pagamento anticipato"}
                                         , {"OV_MPRON"     , "TESTOHTML"    , "${ov_mpron.text_vettore}"                        , "Merce pronta OV: Testo vettore"}
                                         , {"OV_MPRON"     , "TESTO"        , "${oggetto.text_clie}"                            , "Oggetto: suffisso con ragione sociale cliente"}
                                         , {"OV_MPRON"     , "TESTO"        , "${ov_mpron.tbl_righe_scad_tot_importo}"          , "Merce pronta OV: Totale importo tabella con righe scadenze/note d'accredito"}
                                         , {"OV_MPRON"     , "TESTO"        , "${ov_mpron.tbl_righe_scad_tot_importo_vadesc}"   , "Merce pronta OV: Valuta tot importo tabella con righe scadenze/note d'accredito"}
                                         , {"OV_MPRON"     , "TESTO"        , "${ov_mpron.text_clie_pag_anticipato_importo}"    , "Merce pronta OV: Importo per clienti con pagamento anticipato"}
                                         , {"OV_MPRON"     , "TESTO"        , "${ov_mpron.text_clie_pag_anticipato_vadesc}"     , "Merce pronta OV: Valuta importo per clienti con pagamento anticipato"}
                                         , {"OV_MPRON"     , "TESTO"        , "${archclie.url_portal_keycode}"                  , "Keycode: URL portale con keycode (solo per cliente)"}
                                         , {"OV_MPRON"     , "TESTO"        , "${ov_mpron.nota}"                                , "Merce pronta OV: Nota"}
                                         , {"OV_MPRON"     , "TESTO"        , "${archclie.cdclie_m}"                            , "Cliente: Codice"}
                                         , {"OV_MPRON"     , "TESTOHTML"    , "${ov_mpron.tbl_imp_finale}"                      , "Merce pronta OV: Tabella con totale importo finale"}
                                         , {"OV_MPRON"     , "TESTOHTML"    , "${ov_mpron.text_imp_finale}"                     , "Merce pronta OV: Importo finale"}
                                         , {"OV_MPRON"     , "TESTOHTML"    , "${ov_mpron.text_imp_finale_vadesc}"              , "Merce pronta OV: Valuta importo finale"}
                                         , {"OV_MPRON"     , "TESTOHTML"    , "${ov_mpron.p1}"                                  , "Merce pronta OV: Paragrafo 1"}
                                         , {"OV_MPRON"     , "TESTOHTML"    , "${ov_mpron.p2}"                                  , "Merce pronta OV: Paragrafo 2"}
                                         , {"OV_MPRON"     , "TESTOHTML"    , "${ov_mpron.p3}"                                  , "Merce pronta OV: Paragrafo 3"}
                                         , {"OV_MPRON"     , "TESTOHTML"    , "${ov_mpron.p4}"                                  , "Merce pronta OV: Paragrafo 4"}
                                         , {"OV_MPRON"     , "TESTOHTML"    , "${ov_mpron.p5}"                                  , "Merce pronta OV: Paragrafo 5"}
                                         , {"OV_MPRON"     , "TESTOHTML"    , "${ov_mpron.p6}"                                  , "Merce pronta OV: Paragrafo 6"}

        
        
//                                         , {"EPRO_NACCB"   , "EMAIL"        , "${archclie.email}"                               , "Cliente: Indirizzo e-mail"}
//                                         , {"EPRO_NACCB"   , "EMAIL"        , "${archclie.email_tecn}"                          , "Cliente: Indirizzo e-mail per comunicazione tecnica"}
//                                         , {"EPRO_NACCB"   , "EMAIL"        , "${archclie.email_amm}"                           , "Cliente: Indirizzo e-mail per comunicazione amministrativa"}
//                                         , {"EPRO_NACCB"   , "EMAIL"        , "${archclie.email_comm}"                          , "Cliente: Indirizzo e-mail per comunicazione commerciale"}
                                         , {"EPRO_NACCB"   , "EMAIL"        , "${ep_utente.email}"                              , "Utente: Indirizzo e-mail"}
                                         , {"EPRO_NACCB"   , "EMAIL"        , "${ep_utente.email_tecn}"                         , "Utente: Indirizzo e-mail per comunicazione tecnica"}
                                         , {"EPRO_NACCB"   , "EMAIL"        , "${ep_utente.email_amm}"                          , "Utente: Indirizzo e-mail per comunicazione amministrativa"}
                                         , {"EPRO_NACCB"   , "EMAIL"        , "${ep_utente.email_comm}"                         , "Utente: Indirizzo e-mail per comunicazione commerciale"}
                                         , {"EPRO_NACCB"   , "EMAIL"        , "${archresp.email}"                               , "Responsabile vend.: Indirizzo e-mail"}                      
                                         , {"EPRO_NACCB"   , "EMAIL"        , "${archresp.email_tecn}"                          , "Responsabile vend.: Indirizzo e-mail per comunicazione tecnica"}                     
                                         , {"EPRO_NACCB"   , "EMAIL"        , "${archresp.email_amm}"                           , "Responsabile vend.: Indirizzo e-mail per comunicazione amministrativa"}              
                                         , {"EPRO_NACCB"   , "EMAIL"        , "${archresp.email_comm}"                          , "Responsabile vend.: Indirizzo e-mail per comunicazione commerciale"}                 
                                         , {"EPRO_NACCB"   , "EMAIL"        , "${age_ispettori.email}"                          , "Area-manager: Indirizzo e-mail"}                         
                                         , {"EPRO_NACCB"   , "EMAIL"        , "${age_ispettori.email_tecn}"                     , "Area-manager: Indirizzo e-mail per comunicazione tecnica"}                          
                                         , {"EPRO_NACCB"   , "EMAIL"        , "${age_ispettori.email_amm}"                      , "Area-manager: Indirizzo e-mail per comunicazione amministrativa"}                    
                                         , {"EPRO_NACCB"   , "EMAIL"        , "${age_ispettori.email_comm}"                     , "Area-manager: Indirizzo e-mail per comunicazione commerciale"}                       
                                         , {"EPRO_NACCB"   , "EMAIL"        , "${archagen.email_capo}"                          , "Capoarea: Indirizzo e-mail"}                                
                                         , {"EPRO_NACCB"   , "EMAIL"        , "${archagen.email_capo_tecn}"                     , "Capoarea: Indirizzo e-mail per comunicazione tecnica"}                               
                                         , {"EPRO_NACCB"   , "EMAIL"        , "${archagen.email_capo_amm}"                      , "Capoarea: Indirizzo e-mail per comunicazione amministrativa"}                        
                                         , {"EPRO_NACCB"   , "EMAIL"        , "${archagen.email_capo_comm}"                     , "Capoarea: Indirizzo e-mail per comunicazione commerciale"}                           
                                         , {"EPRO_NACCB"   , "EMAIL"        , "${archagen.email_agenrif}"                       , "Agente Rif.: Indirizzo e-mail"}                            
                                         , {"EPRO_NACCB"   , "EMAIL"        , "${archagen.email_agenrif_tecn}"                  , "Agente Rif.: Indirizzo e-mail per comunicazione tecnica"}                            
                                         , {"EPRO_NACCB"   , "EMAIL"        , "${archagen.email_agenrif_amm}"                   , "Agente Rif.: Indirizzo e-mail per comunicazione amministrativa"}                     
                                         , {"EPRO_NACCB"   , "EMAIL"        , "${archagen.email_agenrif_comm}"                  , "Agente Rif.: Indirizzo e-mail per comunicazione commerciale"}                        
                                         , {"EPRO_NACCB"   , "EMAIL"        , "${archagen.email}"                               , "Agente: Indirizzo e-mail"}                        
                                         , {"EPRO_NACCB"   , "EMAIL"        , "${archagen.email_tecn}"                          , "Agente: Indirizzo e-mail per comunicazione tecnica"}                        
                                         , {"EPRO_NACCB"   , "EMAIL"        , "${archagen.email_amm}"                           , "Agente: Indirizzo e-mail per comunicazione amministrativa"}                        
                                         , {"EPRO_NACCB"   , "EMAIL"        , "${archagen.email_comm}"                          , "Agente: Indirizzo e-mail per comunicazione commerciale"}                        
                                         , {"EPRO_NACCB"   , "TESTO"        , "${epro_naccb.gg_blocco}"                         , "Blocco clienti inattivi: GG blocco"}
//                                         , {"EPRO_NACCB"   , "TESTO"        , "${archclie.cdclie_m}"                            , "Cliente: Codice"}
                                         , {"EPRO_NACCB"   , "TESTOHTML"    , "${epro_naccb.naccb_tbl_clie}"                    , "Blocco clienti inattivi: Tabella con clienti inattivi"}
                                         

                                         , {"PREMI"        , "EMAIL"        , "${archclie.email}"                               , "Cliente: Indirizzo e-mail"}
                                         , {"PREMI"        , "EMAIL"        , "${archclie.email_tecn}"                          , "Cliente: Indirizzo e-mail per comunicazione tecnica"}
                                         , {"PREMI"        , "EMAIL"        , "${archclie.email_amm}"                           , "Cliente: Indirizzo e-mail per comunicazione amministrativa"}
                                         , {"PREMI"        , "EMAIL"        , "${archclie.email_comm}"                          , "Cliente: Indirizzo e-mail per comunicazione commerciale"}    
                                         , {"PREMI"        , "EMAIL"        , "${archresp.email}"                               , "Responsabile vend.: Indirizzo e-mail"}                      
                                         , {"PREMI"        , "EMAIL"        , "${archresp.email_tecn}"                          , "Responsabile vend.: Indirizzo e-mail per comunicazione tecnica"}                     
                                         , {"PREMI"        , "EMAIL"        , "${archresp.email_amm}"                           , "Responsabile vend.: Indirizzo e-mail per comunicazione amministrativa"}              
                                         , {"PREMI"        , "EMAIL"        , "${archresp.email_comm}"                          , "Responsabile vend.: Indirizzo e-mail per comunicazione commerciale"}                 
                                         , {"PREMI"        , "EMAIL"        , "${age_ispettori.email}"                          , "Area-manager: Indirizzo e-mail"}                         
                                         , {"PREMI"        , "EMAIL"        , "${age_ispettori.email_tecn}"                     , "Area-manager: Indirizzo e-mail per comunicazione tecnica"}                          
                                         , {"PREMI"        , "EMAIL"        , "${age_ispettori.email_amm}"                      , "Area-manager: Indirizzo e-mail per comunicazione amministrativa"}                    
                                         , {"PREMI"        , "EMAIL"        , "${age_ispettori.email_comm}"                     , "Area-manager: Indirizzo e-mail per comunicazione commerciale"}                       
                                         , {"PREMI"        , "EMAIL"        , "${archagen.email_capo}"                          , "Capoarea: Indirizzo e-mail"}                                
                                         , {"PREMI"        , "EMAIL"        , "${archagen.email_capo_tecn}"                     , "Capoarea: Indirizzo e-mail per comunicazione tecnica"}                               
                                         , {"PREMI"        , "EMAIL"        , "${archagen.email_capo_amm}"                      , "Capoarea: Indirizzo e-mail per comunicazione amministrativa"}                        
                                         , {"PREMI"        , "EMAIL"        , "${archagen.email_capo_comm}"                     , "Capoarea: Indirizzo e-mail per comunicazione commerciale"}                           
                                         , {"PREMI"        , "EMAIL"        , "${archagen.email_agenrif}"                       , "Agente Rif.: Indirizzo e-mail"}                            
                                         , {"PREMI"        , "EMAIL"        , "${archagen.email_agenrif_tecn}"                  , "Agente Rif.: Indirizzo e-mail per comunicazione tecnica"}                            
                                         , {"PREMI"        , "EMAIL"        , "${archagen.email_agenrif_amm}"                   , "Agente Rif.: Indirizzo e-mail per comunicazione amministrativa"}                     
                                         , {"PREMI"        , "EMAIL"        , "${archagen.email_agenrif_comm}"                  , "Agente Rif.: Indirizzo e-mail per comunicazione commerciale"}                        
                                         , {"PREMI"        , "EMAIL"        , "${archagen.email}"                               , "Agente: Indirizzo e-mail"}                        
                                         , {"PREMI"        , "EMAIL"        , "${archagen.email_tecn}"                          , "Agente: Indirizzo e-mail per comunicazione tecnica"}                        
                                         , {"PREMI"        , "EMAIL"        , "${archagen.email_amm}"                           , "Agente: Indirizzo e-mail per comunicazione amministrativa"}                        
                                         , {"PREMI"        , "EMAIL"        , "${archagen.email_comm}"                          , "Agente: Indirizzo e-mail per comunicazione commerciale"}                        
                                         , {"PREMI"        , "TESTO"        , "${archenti.ragcog}"                              , "Cliente: Ragione sociale"}
                                         , {"PREMI"        , "TESTO"        , "${oggetto.text_clie}"                            , "Oggetto: suffisso con ragione sociale cliente"}
                                         , {"PREMI"        , "TESTO"        , "${archclie.url_portal_keycode}"                  , "Keycode: URL portale con keycode (solo per cliente)"}
                                         , {"PREMI"        , "TESTO"        , "${archclie.cdclie_m}"                            , "Cliente: Codice"}
                                         , {"PREMI"        , "TESTOHTML"    , "${premi.tbl_dati_fatt}"                          , "Premi: Tabella riepilogo dati fatturato"}
                                         , {"PREMI"        , "TESTOHTML"    , "${premi.tbl_dati_premio}"                        , "Premi: Tabella riepilogo dati premio"}
                                         , {"PREMI"        , "TESTO"        , "${oggi.data}"                                    , "Data del giorno in cui viene predisposto il messaggio"}
                                         , {"PREMI"        , "TESTO"        , "${archresp.dsresp}"                              , "Responsabile vend.: Descrizione (Nome e Cognome)"}
                                         , {"PREMI"        , "TESTO"        , "${archagen.dsptit}"                              , "Agente: Sig./Sig.ra"}          
                                         
                
                                              };
      
      for (int i=0; arr_!=null && i<arr_.length; i++){
          Rec_ep_msgmod_par lstr_par = new Rec_ep_msgmod_par();

          ind = 0;
          lstr_par.cdmsgmod_appl = arr_[i][ind++];
          lstr_par.categoria_par = arr_[i][ind++];
          lstr_par.cdmsgmod_par  = arr_[i][ind++];
          lstr_par.dsmsgmod_par  = arr_[i][ind++];
          
          tot_rec = ep_msgmod_par.execute(lstr_par);

          if (tot_rec != 1)  throw new Exception("Errore INS/UPD pgmr.ep_msgmod_par");
      
      }
      
      ep_costanti.close();
      ep_msgmod_par.close();
      
      
      return 1;
  }
  
  
  
  public String of_relpath_allegati (long tkmsgmod, String cdling) throws Exception {
      
          
      String slash = System.getProperty("file.separator");

      String ls_ = "RES"+slash+tkmsgmod+"-"+cdling+slash;
      
      
      return ls_;
  }
  
  
  public int of_salva_allegati (long tkmsgmod, String cdling, String rootpath) throws Exception {
      int ind = 0;
      int tot_rec = 0;
      PreparedStatement pstm = null;
      ResultSet rs = null;
      
      Ep_msgmod_file ep_msgmod_file = new Ep_msgmod_file();
      
      setProfilo((Atk_sql)ep_msgmod_file);
      
      ep_msgmod_file.of_delete(tkmsgmod, cdling);
      
      String relativepath = of_relpath_allegati(tkmsgmod, cdling); 
      
      File lo_dir = new File(rootpath+relativepath);
      
      if (lo_dir.exists()){
          
          File[] arr_file = lo_dir.listFiles();
          
          for(int i=0; arr_file!=null && i<arr_file.length; i++){
              
              if (!arr_file[i].isFile()){
                  continue;
              }
              
              String filename = arr_file[i].getName();
              
              Rec_ep_msgmod_file lstr_file = new Rec_ep_msgmod_file();
  
              lstr_file.tkmsgmod = tkmsgmod;
              lstr_file.cdling = cdling;
              lstr_file.nrposi = (i+1);
              lstr_file.rootpath = rootpath;
              lstr_file.relativepath = relativepath;
              lstr_file.filename = filename;
              
              tot_rec = ep_msgmod_file.execute(lstr_file);
              
              if (tot_rec!=1){
                  throw new Exception("Errore INS ep_msgmod_file");
              }
              
              
          }
          
          
      }
      
      
      return 1;
  }
  

  public int of_delete_allegati (long tkmsgmod, String cdling, String rootpath) throws Exception {
      int ind = 0;
      int tot_rec = 0;
      PreparedStatement pstm = null;
      ResultSet rs = null;
      
      Ep_msgmod_file ep_msgmod_file = new Ep_msgmod_file();
      
      setProfilo((Atk_sql)ep_msgmod_file);
      
      ep_msgmod_file.of_delete(tkmsgmod, cdling);
      
      String relativepath = of_relpath_allegati(tkmsgmod, cdling); 
      
      File lo_dir = new File(rootpath+relativepath);
      
      if (lo_dir.exists()){
          
          File[] arr_file = lo_dir.listFiles();
          
          for(int i=0; arr_file!=null && i<arr_file.length; i++){
              
              if (arr_file[i].isFile()){
                  arr_file[i].delete();
              }
          }
          
          
      }
      
      
      return 1;
  }
  
  public int of_send_mail (Str_msgmod astr, com.voxbiblia.jresolver.Resolver resolver) throws Exception {
      int ind = 0;
      int tot_rec = 0;
      PreparedStatement pstm = null;
      ResultSet rs = null;

         
      String ep_site_url    =  "";
      String ep_portal_url    =  "";
        
      Ep_costanti  ep_costanti = new Ep_costanti();

      setProfilo((Atk_sql) ep_costanti);

      ep_site_url    = ep_costanti.getCostvalue("ep.site_url"); 
      ep_site_url = of_cambiaURLLingua(ep_site_url, astr.cdling); 
      ep_portal_url    = ep_costanti.getCostvalue("ep.portal_url");
      ep_portal_url = of_cambiaURLLingua(ep_portal_url, astr.cdling);

      ep_costanti.close();
        
      
      if (astr == null) {
          throw new Exception("Modello da inviare e' nullo");
      }

      String mittente = "";
      Vector dest_TO = new Vector();
      Vector dest_CC = new Vector();
      Vector dest_BCC = new Vector();
      String oggetto = "";
      String contenuto = "";
      Vector attach = new Vector();
      Vector attach_name = new Vector();

      mittente = astr.mittente;
      for (int i = 0; astr.list_dest != null && i < astr.list_dest.size(); i++) {
          com.ateikon.structure.Rec_ep_msgmod_dest lstr_dest = (com.ateikon.structure.Rec_ep_msgmod_dest) astr.list_dest.get(i);
          if (lstr_dest != null) {
              if (!lstr_dest.dest.equals("")) {
                  if (lstr_dest.tipodest.equals(TIPODEST_TO)) {
                      dest_TO.add(lstr_dest.dest);
                  } else if (lstr_dest.tipodest.equals(TIPODEST_CC)) {
                      dest_CC.add(lstr_dest.dest);
                  } else if (lstr_dest.tipodest.equals(TIPODEST_BCC)) {
                      dest_BCC.add(lstr_dest.dest);
                  } else {
                      throw new Exception("Tipodest NON gestito");
                  }
              }
          }
      }
      oggetto = astr.oggetto;
      contenuto = astr.of_getHTML(ep_site_url, ep_portal_url);
      for (int i = 0; astr.list_file != null && i < astr.list_file.size(); i++) {
          com.ateikon.structure.Rec_ep_msgmod_file lstr_file = (com.ateikon.structure.Rec_ep_msgmod_file) astr.list_file.get(i);
          if (lstr_file != null) {
              File of_file = new File(lstr_file.rootpath + lstr_file.relativepath + lstr_file.filename);

              attach.add(of_file);
              attach_name.add(lstr_file.filename);
          }
      }


      if (dest_TO.isEmpty()) {
          dest_TO.add("");//invio al mittente di default
      }

      F_sender f_sender = new F_sender();

      setProfilo((Atk_sql) f_sender);

      long ll_tkmsg = 0;

      if (astr.VALORIZZA_DTRICSPED.equals("S")) {
          
          boolean fgcommit = false;
          
          ll_tkmsg = f_sender.inviaEMail(mittente
                                        , dest_TO
                                        , dest_CC
                                        , dest_BCC
                                        , oggetto
                                        , contenuto
                                        , attach
                                        , attach_name
                                        , fgcommit
                                        , resolver
                                                );
          
      } else {
          
          java.sql.Timestamp dtricsped = null;
          boolean fgcommit = false;
          
          ll_tkmsg = f_sender.inviaEMail( mittente
                            , dest_TO
                            , dest_CC
                            , dest_BCC
                            , oggetto
                            , contenuto
                            , attach
                            , attach_name
                            , dtricsped
                            , fgcommit
                            , resolver
                                                        );
                  
                  
      }
    

      //Setto il valore di tkmsg che mi ritorna
      astr.tkmsg = ll_tkmsg;

      f_sender.close();

      if (ll_tkmsg > 0) {
          return 1;
      }
      return -1;
  }
  
  
  public String TIPODEST_TO  = "D";
  public String TIPODEST_CC  = "C";
  public String TIPODEST_BCC = "B";
  
  
  
  
}

