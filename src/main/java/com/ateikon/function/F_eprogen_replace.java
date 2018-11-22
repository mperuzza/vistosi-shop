package com.ateikon.function;

import com.ateikon.common.Age_ispettori;
import com.ateikon.common.Archagen;
import com.ateikon.common.Archclie;
import com.ateikon.common.Archresp;
import com.ateikon.common.Archrubrica_tipo;
import com.ateikon.common.Assofiscal;
import com.ateikon.common.Atk_dwlingua;
import java.sql.ResultSet;
import java.sql.PreparedStatement;


import com.ateikon.common.Atk_sql;
import com.ateikon.common.Bolla_test;
import com.ateikon.common.Costanti_comm;
import com.ateikon.common.Crm_credential;
import com.ateikon.common.Ep_costanti;
import com.ateikon.common.Ep_utente;
import com.ateikon.common.Mrp_arch_articoli;
import com.ateikon.common.Mrp_arch_stato;
import com.ateikon.common.Msg_tpcomgen;
import com.ateikon.common.Nazioni;
import com.ateikon.common.Province;
import com.ateikon.common.Vist_log_notif;
import com.ateikon.common.Vist_mpron;
import com.ateikon.common.Vist_mpron_ord_posi;
import com.ateikon.common.Vist_mpron_posi;
import com.ateikon.common.Vist_nazioni_ref;
import com.ateikon.common.Vist_scadenziario;
import com.ateikon.common.Zonacomm;
import com.ateikon.common.Ep_msgmod_extra_p;
import com.ateikon.common.Vist_oridati;
import com.ateikon.structure.Rec_ep_msgmod_dest;
import com.ateikon.structure.Rec_ep_utente;
import com.ateikon.structure.Str_controllo_scadenziario;
import com.ateikon.structure.Str_crm_credential;
import com.ateikon.structure.Str_dec_barcode;
import com.ateikon.structure.Str_html;
import com.ateikon.structure.Str_key_vist_premi;
import com.ateikon.structure.Str_msgmod;
import com.ateikon.structure.Str_ordven_cond;
import com.ateikon.structure.Str_ordven_tot;
import com.ateikon.structure.Str_risorsa_anticipo;
import com.ateikon.util.Atk_ctrl;
import com.ateikon.util.HTML;
import java.io.File;
import java.lang.String;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;



/**********************/
//     NOTA:
//     modificare questa classe nel portale implica un allineamento della stessa classe in eprogen
//     e viceversa modificare questa classe nel eprogen implica un allineamento della stessa classe nel portale
//
/*********************/

public class F_eprogen_replace extends Atk_sql {

    public F_eprogen_replace () {

        super();

        
    }
    
  // Vedi Web_ord_test_note
  public String TIPONOTA_INTERNA = "INTERNA";
  public String TIPONOTA_CLIENTE = "CLIENTE";
    
    
  public Object of_replace_msgmod (String as_cdmsgmod_app, Object ao_token, Object ao_model) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    HTML html = new HTML();
    Atk_ctrl atk_ctrl = new Atk_ctrl();
    
    Ep_costanti   ep_costanti = new Ep_costanti();

    setProfilo((Atk_sql) ep_costanti);
    
    //--------------------------------------------------------------------------------- 
    //--------------------------------------------------------------------------------- 
    //--------------------------------------------------------------------------------- 
    Object lo_model = null;

    if (ao_model != null){

      Str_msgmod lo_lstr = (Str_msgmod) ao_model; 

      //--------------------------------------------------------------------------------- 
      //--------------------------------------------------------------------------------- 
      //--------------------------------------------------------------------------------- 
      //Valorizzo l'HashMap che mi permetter� di fare il replace del modello
      HashMap  lo_hash_par = new HashMap();
      //Gestione particolare per il parametro $(file.filename)
      lo_hash_par.put("${file.filename}", lo_lstr.par__file_filename);
      lo_hash_par.put("${oggi.data}", atk_ctrl.getDate(of_getOggi()));
      
      lo_hash_par = of_setPar_BANNER  (lo_hash_par, lo_lstr.cdling);

      if (as_cdmsgmod_app.equals("COMG_ARTS"  )) lo_hash_par = of_setPar_COMG_ARTS  (lo_hash_par, (Long) ao_token, lo_lstr.cdling);
      if (as_cdmsgmod_app.equals("COMG_RECL"  )) lo_hash_par = of_setPar_COMG_RECL  (lo_hash_par, (Long) ao_token, lo_lstr.cdling);
      if (as_cdmsgmod_app.equals("COMG_RESC"  )) lo_hash_par = of_setPar_COMG_RESC  (lo_hash_par, (Long) ao_token, lo_lstr.cdling);
      if (as_cdmsgmod_app.equals("COMG_SUGG"  )) lo_hash_par = of_setPar_COMG_SUGG  (lo_hash_par, (Long) ao_token, lo_lstr.cdling);
      if (as_cdmsgmod_app.equals("EPRO_CONTG" )) lo_hash_par = of_setPar_EPRO_CONTG (lo_hash_par, (Long) ao_token, lo_lstr.cdling);
      if (as_cdmsgmod_app.equals("EPRO_CRED"  )) lo_hash_par = of_setPar_EPRO_CRED  (lo_hash_par, (Long) ao_token, lo_lstr.cdling);
      if (as_cdmsgmod_app.equals("EPRO_NOACC" )) lo_hash_par = of_setPar_EPRO_NOACC (lo_hash_par, (Long) ao_token, lo_lstr.cdling);
      if (as_cdmsgmod_app.equals("OV_AVVSPED" )) lo_hash_par = of_setPar_OV_AVVSPED (lo_hash_par, (Long) ao_token, lo_lstr.cdling);
      if (as_cdmsgmod_app.equals("OV_CONF"    )) lo_hash_par = of_setPar_OV_CONF    (lo_hash_par, (Long) ao_token, lo_lstr.cdling);
      if (as_cdmsgmod_app.equals("OV_RITSPED" )) lo_hash_par = of_setPar_OV_RITSPED (lo_hash_par, (Long) ao_token, lo_lstr.cdling);
      if (as_cdmsgmod_app.equals("RUBR_PROSP" )) lo_hash_par = of_setPar_RUBR_PROSP (lo_hash_par, (Long) ao_token, lo_lstr.cdling);
      if (as_cdmsgmod_app.equals("RUBR_RM_CG" )) lo_hash_par = of_setPar_RUBR_RM_CG (lo_hash_par, (Long) ao_token, lo_lstr.cdling);
      if (as_cdmsgmod_app.equals("RUBR_RR_CP" )) lo_hash_par = of_setPar_RUBR_RR_CP (lo_hash_par, (Long) ao_token, lo_lstr.cdling);
      if (as_cdmsgmod_app.equals("SCADENZE"   )) lo_hash_par = of_setPar_SCADENZE   (lo_hash_par, (Long) ao_token, lo_lstr.cdling, lo_lstr);
      if (as_cdmsgmod_app.equals("OV_RICEZ"   )) lo_hash_par = of_setPar_OV_RICEZ   (lo_hash_par, (Long) ao_token, lo_lstr.cdling);
      if (as_cdmsgmod_app.equals("OV_MPRON"   )) lo_hash_par = of_setPar_OV_MPRON   (lo_hash_par, (Long) ao_token, lo_lstr.cdling);
      if (as_cdmsgmod_app.equals("EPRO_NACCB" )) lo_hash_par = of_setPar_EPRO_NACCB (lo_hash_par, (Long) ao_token, lo_lstr.cdling);
      if (as_cdmsgmod_app.equals("PREMI"      )) lo_hash_par = of_setPar_PREMI      (lo_hash_par, (Str_key_vist_premi) ao_token, lo_lstr.cdling);
      if (as_cdmsgmod_app.equals("SITE_DOWNR" )) lo_hash_par = of_setPar_SITE_DOWNR (lo_hash_par, (Long) ao_token, lo_lstr.cdling);

      //--------------------------------------------------------------------------------- 
      //--------------------------------------------------------------------------------- 
      //--------------------------------------------------------------------------------- 
      //Effettuo il replace ciclando l'HashMap
      Set list = lo_hash_par.keySet();
      Iterator iter = list.iterator();

      while (iter.hasNext()){
        String chiave = (String) iter.next();
        String valore = (String) lo_hash_par.get(chiave);
        
        lo_lstr.mittente = lo_lstr.mittente.replace(chiave, valore);
       
        for (int i=0; lo_lstr.list_dest != null && i < lo_lstr.list_dest.size(); i++){
          Rec_ep_msgmod_dest lstr_dest = lo_lstr.list_dest.get(i);

          lstr_dest.dest = lstr_dest.dest.replace(chiave, valore);
        }

        lo_lstr.oggetto = lo_lstr.oggetto.replace(chiave, valore);
        //se uno o pi� tra i paramtri nella condizione � vuoto nel conteuto setto info@vistosi.it
        if (    valore.equals("")
            &&  ( 
                    chiave.equals("${archrubrica.email_aream}")
                 || chiave.equals("${archrubrica.email_respv}")
                 || chiave.equals("${rubr_venditore.email_venditore}")
                )
           ){
          
          lo_lstr.contenuto = lo_lstr.contenuto.replace(chiave, "info@vistosi.it");

        } else {
          
          if (of_is_HTML(valore).equals("S")){
            lo_lstr.contenuto = lo_lstr.contenuto.replace(chiave, valore);
          } else {
            lo_lstr.contenuto = lo_lstr.contenuto.replace(chiave, html.text(valore));
          }
        }
      }


      //--------------------------------------------------------------------------------- 
      //--------------------------------------------------------------------------------- 
      //--------------------------------------------------------------------------------- 
      //Imposto valore di output
      lo_model = lo_lstr;

    
    } else {
      throw new Exception("Modello input nullo");
    }

    ep_costanti.close();
    
    return lo_model;
  }
  
  
  public HashMap of_setPar_BANNER (HashMap ao_map, String cdling) throws Exception {

    if (ao_map ==null){
      ao_map = new HashMap();
    }
    
    Ep_msgmod_extra_p ep_msgmod_extra_p =  new Ep_msgmod_extra_p();
    
    setProfilo((Atk_sql) ep_msgmod_extra_p);
    
    String l_query = "      select tkmsgmod_ex         "
            + "               from pgmr.ep_msgmod_extra       "
            + "              where dtival is not null and dtival <= date("+ sysdate +")"
            + "                and dtfval is not null and dtfval >= date("+ sysdate +")"
            + "           order by tkmsgmod_ex";

    long tkmsgmod_ex = sql_long(l_query);
    
//      System.out.println("tkmsgmod_ex  "+ tkmsgmod_ex);
    
    ao_map.put("${banner}", ep_msgmod_extra_p.of_getContenuto(tkmsgmod_ex, cdling));
    
//      System.out.println("ao_map  "+ ao_map.get("${banner}"));
    ep_msgmod_extra_p.close();
    
    return ao_map;
  }

  public HashMap of_setPar_COMG_ARTS (HashMap ao_map, long tkcomgen, String cdling) throws Exception {

    if (ao_map ==null){
      ao_map = new HashMap();
    }

    return of_setPar_COMG (ao_map, tkcomgen, cdling);
  }
  
  public HashMap of_setPar_COMG_RESC (HashMap ao_map, long tkcomgen, String cdling) throws Exception {

    if (ao_map ==null){
      ao_map = new HashMap();
    }

    return of_setPar_COMG (ao_map, tkcomgen, cdling);
  }
  
  public HashMap of_setPar_COMG_RECL (HashMap ao_map, long tkcomgen, String cdling) throws Exception {

    if (ao_map ==null){
      ao_map = new HashMap();
    }

    return of_setPar_COMG (ao_map, tkcomgen, cdling);
  }
  
  public HashMap of_setPar_COMG_SUGG (HashMap ao_map, long tkcomgen, String cdling) throws Exception {

    if (ao_map ==null){
      ao_map = new HashMap();
    }

    return of_setPar_COMG (ao_map, tkcomgen, cdling);
  }
  
  public HashMap of_setPar_COMG (HashMap ao_map, long tkcomgen, String cdling) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    if (ao_map ==null){
      ao_map = new HashMap();
    }

    
    ao_map = of_setPar_Msg_comgen(ao_map, tkcomgen, cdling);
    
    return ao_map;
  }

  public HashMap of_setPar_EPRO_CONTG (HashMap ao_map, long tkcontatto , String cdling) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    if (ao_map ==null){
      ao_map = new HashMap();
    }


    ao_map = of_setPar_Atk_contatti(ao_map, tkcontatto, cdling);

    return ao_map;
  }

  public HashMap of_setPar_EPRO_CRED  (HashMap ao_map, long tkutente   , String cdling) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    if (ao_map ==null){
      ao_map = new HashMap();
    }

    String tkclie = "";
    long   tksubutente = 0;

    Ep_utente  ep_utente = new Ep_utente();

    setProfilo((Atk_sql) ep_utente);

    ao_map = of_setPar_Ep_utente(ao_map, tkutente, cdling);

    rs = ep_utente.getKey(tkutente);

    if (rs != null && rs.next()){
      tkclie = "";
      tksubutente = 0;

      if (rs.getObject("tkclie") != null)  tkclie = rs.getString("tkclie"); 
      if (rs.getObject("tksubutente") != null)  tksubutente = rs.getLong("tksubutente"); 

    }

//    if (tkclie.equals("") && tksubutente <= 0){
//      //Pulisco questi valori se non e' cliente
//      ao_map.put("${archresp.email}"        , "");
//      ao_map.put("${archresp.email_amm}"        , "");
//      ao_map.put("${archresp.email_comm}"        , "");
//      ao_map.put("${archresp.email_tecn}"        , "");
//      ao_map.put("${archresp.email_no_acc}"        , "");
//      ao_map.put("${age_ispettori.email}"   , "");
//      ao_map.put("${age_ispettori.email_amm}"   , "");
//      ao_map.put("${age_ispettori.email_comm}"   , "");
//      ao_map.put("${age_ispettori.email_tecn}"   , "");
//      ao_map.put("${age_ispettori.email_no_acc}"   , "");
//      ao_map.put("${archagen.email_capo}"   , "");
//      ao_map.put("${archagen.email_capo_amm}"   , "");
//      ao_map.put("${archagen.email_capo_comm}"   , "");
//      ao_map.put("${archagen.email_capo_tecn}"   , "");
//      ao_map.put("${archagen.email_capo_no_acc}"   , "");
//      ao_map.put("${archagen.email_agenrif}", "");
//      ao_map.put("${archagen.email_agenrif_amm}", "");
//      ao_map.put("${archagen.email_agenrif_comm}", "");
//      ao_map.put("${archagen.email_agenrif_tecn}", "");
//      ao_map.put("${archagen.email_agenrif_no_acc}", "");
//      ao_map.put("${archagen.email}"        , "");
//      ao_map.put("${archagen.email_amm}"        , "");
//      ao_map.put("${archagen.email_comm}"        , "");
//      ao_map.put("${archagen.email_tecn}"        , "");
//      ao_map.put("${archagen.email_no_acc}"        , "");
//    }
    if (tksubutente <= 0){
      ao_map.put("${archclie.email}"             , "");
      ao_map.put("${archclie.email_amm}"         , "");
      ao_map.put("${archclie.email_comm}"        , "");
      ao_map.put("${archclie.email_tecn}"        , "");
      ao_map.put("${archclie.email_no_acc}"        , "");
    }

    ep_utente.close();

    return ao_map;
  }

  public HashMap of_setPar_EPRO_NOACC (HashMap ao_map, long tkutente   , String cdling) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    if (ao_map ==null){
      ao_map = new HashMap();
    }


    ao_map = of_setPar_Ep_utente(ao_map, tkutente, cdling);

    return ao_map;

  }

  public HashMap of_setPar_OV_AVVSPED (HashMap ao_map, long tk         , String cdling) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    if (ao_map ==null){
      ao_map = new HashMap();
    }


    ao_map = of_setPar_Bolla_test(ao_map, tk, cdling);

    return ao_map;

  }

  public HashMap of_setPar_OV_CONF    (HashMap ao_map, long tkordi     , String cdling) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    if (ao_map ==null){
      ao_map = new HashMap();
    }


    ao_map = of_setPar_Ord_test(ao_map, tkordi, cdling);

    return ao_map;

  }

  public HashMap of_setPar_OV_RITSPED (HashMap ao_map, long tkordi     , String cdling) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    if (ao_map ==null){
      ao_map = new HashMap();
    }


    ao_map = of_setPar_Ord_test(ao_map, tkordi, cdling);

    return ao_map;

  }

  public HashMap of_setPar_RUBR_PROSP (HashMap ao_map, long tkrubr     , String cdling) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    if (ao_map ==null){
      ao_map = new HashMap();
    }


    ao_map = of_setPar_Archrubrica(ao_map, tkrubr, cdling);

    return ao_map;

  }

  public HashMap of_setPar_RUBR_RM_CG (HashMap ao_map, long tkrubr_mod , String cdling) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    if (ao_map ==null){
      ao_map = new HashMap();
    }


    ao_map = of_setPar_Archrubrica_modif(ao_map, tkrubr_mod, cdling);

    return ao_map;

  }

  public HashMap of_setPar_RUBR_RR_CP (HashMap ao_map, long tkrubr     , String cdling) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    if (ao_map ==null){
      ao_map = new HashMap();
    }


    String  fgadmin   = "";
    String  cdutente  = "";
    String  cdispe    = "";
    String  cdagen    = "";
    String  cdsuba    = "";
    long    tkrubr_tipo = 0;
    long    tkutente = 0;

    //Recupero nazione sede legale contatto
    String  rubr_cdnazi_sl    = "";

    l_query  = "";
    l_query  = "   select rulo.cdnazi      as cdnazi_sl                 \n";
    l_query += "     from pgmr.archrubrica_uloc rulo                    \n";
    l_query += "    where rulo.fseleg = 'S'                             \n";
    l_query += "      and rulo.tkrubr = "+ tkrubr +"                    \n";

    rs = sql_query(l_query);

    if (rs != null && rs.next()){

      rubr_cdnazi_sl    = "";

      if (rs.getObject("cdnazi_sl")!= null) rubr_cdnazi_sl = rs.getString("cdnazi_sl");
    }
    
    String l_query  = "";

    l_query  = "";
    l_query  = "    select rubr.tkrubr_tipo            as tkrubr_tipo                                          \n";
    l_query += "         , tikt.tkutente_inse          as tkutente                                             \n";
    l_query += "         , eute.cdutente               as cdutente                                             \n";
    l_query += "         , eute.fgadmin                as fgadmin                                              \n";
    if (is_oracle){
        throw new Exception("DB non supportato");
    } else if (is_sybase || is_postgresql) {
        l_query += "      from pgmr.archrubrica     rubr                                                           \n";
        l_query += "         , pgmr.ep_ticket  tikt                                                           \n";
        l_query += "            left outer join  pgmr.ep_utente eute     on  tikt.tkutente_inse  = eute.tkutente   \n";
        l_query += "     where rubr.tkticket_reg   = tikt.tkticket                                                 \n";
        l_query += "       and rubr.tkrubr         = "+ tkrubr +"                                                  \n";
    }

    rs = sql_query(l_query);

    if (rs != null && rs.next()){

      tkrubr_tipo    = 0;
      tkutente       = 0;
      cdutente       = "";
      fgadmin        = "N";
      
      if (rs.getObject("tkrubr_tipo")!= null) tkrubr_tipo   = rs.getLong  ("tkrubr_tipo");
      if (rs.getObject("tkutente"   )!= null) tkutente      = rs.getLong  ("tkutente"   );
      if (rs.getObject("cdutente"   )!= null) cdutente      = rs.getString("cdutente"   );
      if (rs.getObject("fgadmin"    )!= null) fgadmin       = rs.getString("fgadmin"    );
      
      if (!cdutente.equals("")){
          tkutente = sql_long("select tkutente from pgmr.vist_nazioni_ref where cdnazi = '"+ rubr_cdnazi_sl +"' and cdruolo = 'AREAM'");
      }

      ao_map = of_setPar_Archrubrica(ao_map, tkrubr, cdling);
      ao_map = of_setPar_Ep_utente(ao_map, tkutente, cdling);
      ao_map = of_setPar_Nazioni(ao_map, rubr_cdnazi_sl, cdling);
    }

    return ao_map;

  }

  public HashMap of_setPar_SCADENZE   (HashMap ao_map, long tkutente   , String cdling, Str_msgmod ao_lstr) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    if (ao_map ==null){
      ao_map = new HashMap();
    }

    
    Atk_ctrl atk_ctrl = new Atk_ctrl();
    HTML html = new HTML();

    
    Ep_costanti   ep_costanti = new Ep_costanti();
    F_utente  f_utente = new F_utente();
    Vist_scadenziario  vist_scadenziario = new Vist_scadenziario();
    Ep_utente  ep_utente = new Ep_utente();
    Atk_dwlingua  atk_dwlingua = new Atk_dwlingua();

    setProfilo((Atk_sql) ep_costanti);
    setProfilo((Atk_sql) f_utente);
    setProfilo((Atk_sql) vist_scadenziario);
    setProfilo((Atk_sql) ep_utente);
    setProfilo((Atk_sql) atk_dwlingua);
    
    

    java.text.NumberFormat numFormat = java.text.NumberFormat.getInstance(java.util.Locale.ITALY);
    numFormat.setGroupingUsed( true );
    numFormat.setMaximumFractionDigits( 2 );
    numFormat.setMinimumFractionDigits( 0 );
    
    java.text.NumberFormat przFormat = java.text.NumberFormat.getInstance(java.util.Locale.ITALY);
    przFormat.setGroupingUsed( true );
    przFormat.setMaximumFractionDigits( 2 );
    przFormat.setMinimumFractionDigits( 2 );
    
    
    String ep_portal_url    = ep_costanti.getCostvalue("ep.portal_url");
    ep_portal_url = of_cambiaURLLingua(ep_portal_url, cdling);

    ao_map = of_setPar_Ep_utente(ao_map, tkutente, cdling);
    //Gestione particolare per il parametro $(scadenze.url)
    ao_map.put("${scadenze.url}", ep_portal_url + "epDesktop.jsp?nextPage=rlogin/eprogen.html?nextPage=epRep_controllo_scadenziario_par.jsp?openMethod=iframe&nextPageTitle=Scadenziario");
    //Gestione particolare per il parametro $(scadenze.tbl_scadenze)
    ao_map.put("${scadenze.tbl_scadenze}", ao_lstr.par__scadenze_tbl_scadenze);
    //Gestione particolare per il parametro $(scadenze.tbl_scadenze_dett)
    ao_map.put("${scadenze.tbl_scadenze_dett}", ao_lstr.par__scadenze_tbl_scadenze_dett);
    //Gestione particolare per il parametro ${scadenze.totale_residuo_notifica}
    ao_map.put("${scadenze.totale_residuo_notifica}", ao_lstr.par__scadenze_ls_totale_residuo_notifica);
    //Gestione particolare per i parametri ${scadenze.resp_email}, ${scadenze.resp_email_tecn}, ${scadenze.resp_email_amm}, ${scadenze.resp_email_comm}
    String scadenze_resp_email = "";
    String scadenze_resp_email_tecn = "";
    String scadenze_resp_email_amm = "";
    String scadenze_resp_email_comm = "";
    String scadenze_resp_descr = "N.D.";
    
    if (!ao_lstr.par__scadenze_cdresp.equals("")){
        
        pstm = m_connection.prepareStatement("select * from pgmr.ep_utente where cdresp = \'"+ ao_lstr.par__scadenze_cdresp +"\'");
        
        rs = pstm.executeQuery();
        
        if (rs != null && rs.next()) {
            long tkutente_resp = 0;
            
            if (rs.getObject("email") != null) {
                scadenze_resp_email = rs.getString("email");
            }   
            if (rs.getObject("tkutente") != null) {
                tkutente_resp = rs.getLong("tkutente");
            }            
            if (rs.getObject("dsutente") != null) {
                scadenze_resp_descr = rs.getString("dsutente");
            }   
            
            scadenze_resp_email_comm     = f_utente.getEmailTiposervizio(tkutente_resp, COM_COM);
            scadenze_resp_email_amm      = f_utente.getEmailTiposervizio(tkutente_resp, COM_AMM);
            scadenze_resp_email_tecn     = f_utente.getEmailTiposervizio(tkutente_resp, COM_TEC);
        }
        
        pstm.close();
        pstm = null;
    }
    
    ao_map.put("${scadenze.resp_email}", scadenze_resp_email);
    ao_map.put("${scadenze.resp_email_tecn}", scadenze_resp_email_tecn);
    ao_map.put("${scadenze.resp_email_amm}", scadenze_resp_email_amm);
    ao_map.put("${scadenze.resp_email_comm}", scadenze_resp_email_comm);
    ao_map.put("${scadenze.resp_descr}", scadenze_resp_descr);
    
    
    Str_controllo_scadenziario lstr_search = new Str_controllo_scadenziario();
    Rec_ep_utente lstr_eute = new Rec_ep_utente();
    
    rs = ep_utente.getKey(tkutente);
    
    if (rs!= null && rs.next()){
        lstr_eute.setResultSet(rs);
    }
    
    
    String ls_vadesc_euro = atk_dwlingua.getLabel ("srv_scadenz", cdling, "euro", "", null);
    
    String ls_tbl_scadenze_scadute = "";
    String ls_totale_residuo_scadenze_scadute = ls_vadesc_euro + " " + przFormat.format(0d);

    if (lstr_eute.tkutente > 0){
        
        ls_tbl_scadenze_scadute = "";
        double ld_totale_residuo = 0;
        double ld_totale_residuo_c = 0;
        String ls_vadesc_prec = "";
        boolean lb_fg_chg_valuta = false;
        
        lstr_search.cdling = lstr_eute.cdling;

        lstr_search.tkclie = lstr_eute.tkclie;
        lstr_search.cdagen = lstr_eute.cdagen;
        lstr_search.cdispe = lstr_eute.cdispe;
        lstr_search.cdresp = lstr_eute.cdresp;
        lstr_search.fgscaduto = "S";
        if (!ao_lstr.par__scadenze_cdresp.equals("")){
            lstr_search.cdresp = ao_lstr.par__scadenze_cdresp;
        }

        rs = vist_scadenziario.getScadenziario(lstr_search, false, "clie.cdclie_m, ente.ragcog, vist_scadenziario.dtscad desc, vist_scadenziario.dtprot desc, vist_scadenziario.nrdoc desc");
        
        while (rs!= null && rs.next()){
            String  nrdoc  = "";
            String  pgdesc  = "";
            String  vadesc  = "";
            double  importo_rata = 0;
            double  importo_rata_c = 0;
            double  pagamento    = 0;
            double  pagamento_c    = 0;
            double  residuo      = 0;
            double  residuo_c      = 0;
            Timestamp dtprot     = null;
            String  pgcodi  = "";
            Timestamp dtscad     = null;
            long    nrrata       = 0;


            if (rs.getObject("nrdoc"         )!=null )   nrdoc        = rs.getString("nrdoc"         );
            if (rs.getObject("pgdesc"        )!=null )   pgdesc       = rs.getString("pgdesc"        );
            if (rs.getObject("vadesc"        )!=null )   vadesc       = rs.getString("vadesc"        );
            if (rs.getObject("importo_rata"  )!=null )   importo_rata = rs.getDouble("importo_rata"  );
            if (rs.getObject("pagamento"     )!=null )   pagamento    = rs.getDouble("pagamento"     );
            if (rs.getObject("importo_rata_c"  )!=null )   importo_rata_c = rs.getDouble("importo_rata_c"  );
            if (rs.getObject("pagamento_c"     )!=null )   pagamento_c    = rs.getDouble("pagamento_c"     );
            if (rs.getObject("dtprot"        )!=null )   dtprot       = rs.getTimestamp("dtprot"         );
            if (rs.getObject("pgcodi"        )!=null )   pgcodi       = rs.getString("pgcodi"        );
            if (rs.getObject("nrrata"        )!=null )   nrrata    = rs.getLong("nrrata"     );
            if (rs.getObject("dtscad"        )!=null )   dtscad       = rs.getTimestamp("dtscad"         );

            residuo = importo_rata - pagamento;
            residuo_c = importo_rata_c - pagamento_c;

            
            if (vadesc.equals("")) vadesc = ls_vadesc_euro;
            
            
            ld_totale_residuo += residuo;
            ld_totale_residuo_c += residuo_c;

            if (ls_vadesc_prec.equals("")) {
                ls_vadesc_prec = vadesc;
            }

            if (!ls_vadesc_prec.equals(vadesc)) {
                lb_fg_chg_valuta = true;
            }

            /*
            ls_tbl_scadenze_scadute += "<p>";
            ls_tbl_scadenze_scadute += atk_dwlingua.getLabel ("srv_scadenz", cdling, "clie_text_1", "", new String[] {nrdoc, atk_ctrl.getDate(dtprot), vadesc + " <b>"+ przFormat.format(residuo)+ "</b>", pgdesc});
            ls_tbl_scadenze_scadute += "</p>";
            */ 
            
            int gg_scad = of_setPar_SCADENZE_getGG_scaduta(rs);

            ls_tbl_scadenze_scadute += "  <tr>                                                                                                                        \n";
            ls_tbl_scadenze_scadute += "        <td align=\"left\" valign=\"top\" nowrap>"+ nrdoc +"</td>                                                                    \n";
            ls_tbl_scadenze_scadute += "        <td align=\"left\" valign=\"top\" nowrap>"+ atk_ctrl.getDate(dtprot) +"</td>                                                  \n";
            ls_tbl_scadenze_scadute += "        <td align=\"left\" valign=\"top\" nowrap>"+ nrrata +"</td>                                                                   \n";
            ls_tbl_scadenze_scadute += "        <td align=\"left\" valign=\"top\" title=\""+ pgdesc +"\">"+ org.apache.commons.lang.StringUtils.abbreviate(html.text(pgdesc), 20) + "</td>                    \n";
            ls_tbl_scadenze_scadute += "        <td align=\"left\" valign=\"top\" nowrap>"+ atk_ctrl.getDate(dtscad) +"</td>                                                 \n";

            String ls_style = "";
            if (pgcodi.equals("I")) ls_style = "style=\"color:AF0000;\"";

            ls_tbl_scadenze_scadute += "        <td align=\"right\" valign=\"top\" nowrap "+ ls_style +">"+ przFormat.format(residuo) + "</td>            \n";
            ls_tbl_scadenze_scadute += "        <td align=\"left\" valign=\"top\" nowrap>"+ html.text(vadesc) + "</td>                                    \n";
            if (gg_scad > 0){
               if (gg_scad >= 15){
                  ls_tbl_scadenze_scadute += "           <td align=\"right\" valign=\"top\" "+ ls_style +">"+ numFormat.format(gg_scad) +"</td>                         \n";
               } else {
                  ls_tbl_scadenze_scadute += "           <td align=\"right\" valign=\"top\">"+ numFormat.format(gg_scad) +"</td>                         \n";
               }
            } else {
               ls_tbl_scadenze_scadute += "           <td align=\"right\" valign=\"top\">0</td>                                                       \n";
            }

            ls_tbl_scadenze_scadute += "  </tr>                                                                                            \n";


        }
        
        if (!ls_tbl_scadenze_scadute.equals("")){
            ls_tbl_scadenze_scadute = "<table width=\"709\" cellpadding=\"0\" cellspacing=\"0\" class=\"report\">\n" + of_setPar_SCADENZE_getHead_tbl_scadenze_clie (lstr_eute.cdling, "N") + ls_tbl_scadenze_scadute + of_setPar_SCADENZE_riga_tbl_totale_residuo(ld_totale_residuo, ld_totale_residuo_c, ls_vadesc_prec, ls_vadesc_euro, lb_fg_chg_valuta, przFormat, "N", lstr_eute.cdling) + "</table>\n";
        }

        
        ls_totale_residuo_scadenze_scadute = of_setPar_SCADENZE_string_totale_residuo(ld_totale_residuo, ld_totale_residuo_c, ls_vadesc_prec, ls_vadesc_euro, lb_fg_chg_valuta, przFormat);
    }
     
    
    ao_map.put("${scadenze.tbl_scadenze_scadute}", ls_tbl_scadenze_scadute);
    ao_map.put("${scadenze.totale_residuo_scadenze_scadute}", ls_totale_residuo_scadenze_scadute);
    
    String ls_tbl_scadenze_future = "";
    String ls_totale_residuo_scadenze_future = ls_vadesc_euro + " " + przFormat.format(0d);

    if (lstr_eute.tkutente > 0){
        
        ls_tbl_scadenze_future = "";
        double ld_totale_residuo = 0;
        double ld_totale_residuo_c = 0;
        String ls_vadesc_prec = "";
        boolean lb_fg_chg_valuta = false;
        
        lstr_search.cdling = lstr_eute.cdling;

        lstr_search.tkclie = lstr_eute.tkclie;
        lstr_search.cdagen = lstr_eute.cdagen;
        lstr_search.cdispe = lstr_eute.cdispe;
        lstr_search.cdresp = lstr_eute.cdresp;
        lstr_search.fgscaduto = "FUTURE";
        if (!ao_lstr.par__scadenze_cdresp.equals("")){
            lstr_search.cdresp = ao_lstr.par__scadenze_cdresp;
        }

        rs = vist_scadenziario.getScadenziario(lstr_search, false, "clie.cdclie_m, ente.ragcog, vist_scadenziario.dtscad, vist_scadenziario.dtprot, vist_scadenziario.nrdoc");
        
        while (rs!= null && rs.next()){
                String  nrdoc  = "";
                String  pgdesc  = "";
                String  vadesc  = "";
                double  importo_rata = 0;
                double  importo_rata_c = 0;
                double  pagamento    = 0;
                double  pagamento_c    = 0;
                double  residuo      = 0;
                double  residuo_c      = 0;
                Timestamp dtprot     = null;
                String  pgcodi  = "";
                Timestamp dtscad     = null;
                long    nrrata       = 0;


                if (rs.getObject("nrdoc"         )!=null )   nrdoc        = rs.getString("nrdoc"         );
                if (rs.getObject("pgdesc"        )!=null )   pgdesc       = rs.getString("pgdesc"        );
                if (rs.getObject("vadesc"        )!=null )   vadesc       = rs.getString("vadesc"        );
                if (rs.getObject("importo_rata"  )!=null )   importo_rata = rs.getDouble("importo_rata"  );
                if (rs.getObject("pagamento"     )!=null )   pagamento    = rs.getDouble("pagamento"     );
                if (rs.getObject("importo_rata_c"  )!=null )   importo_rata_c = rs.getDouble("importo_rata_c"  );
                if (rs.getObject("pagamento_c"     )!=null )   pagamento_c    = rs.getDouble("pagamento_c"     );
                if (rs.getObject("dtprot"        )!=null )   dtprot       = rs.getTimestamp("dtprot"         );
                if (rs.getObject("pgcodi"        )!=null )   pgcodi       = rs.getString("pgcodi"        );
                if (rs.getObject("nrrata"        )!=null )   nrrata    = rs.getLong("nrrata"     );
                if (rs.getObject("dtscad"        )!=null )   dtscad       = rs.getTimestamp("dtscad"         );

                residuo = importo_rata - pagamento;
                residuo_c = importo_rata_c - pagamento_c;


                if (vadesc.equals("")) vadesc = ls_vadesc_euro;


                ld_totale_residuo += residuo;
                ld_totale_residuo_c += residuo_c;

                if (ls_vadesc_prec.equals("")) {
                    ls_vadesc_prec = vadesc;
                }

                if (!ls_vadesc_prec.equals(vadesc)) {
                    lb_fg_chg_valuta = true;
                }


                /*
                ls_tbl_scadenze_future += "<p>";
                ls_tbl_scadenze_future += atk_dwlingua.getLabel ("srv_scadenz", cdling, "clie_text_1_fut", "", new String[] {nrdoc, atk_ctrl.getDate(dtprot), vadesc + " <b>"+ przFormat.format(residuo)+ "</b>", pgdesc});
                ls_tbl_scadenze_future += "</p>";
                */


                ls_tbl_scadenze_future += "  <tr>                                                                                                                        \n";
                ls_tbl_scadenze_future += "        <td align=\"left\" valign=\"top\" nowrap>"+ nrdoc +"</td>                                                                    \n";
                ls_tbl_scadenze_future += "        <td align=\"left\" valign=\"top\" nowrap>"+ atk_ctrl.getDate(dtprot) +"</td>                                                  \n";
                ls_tbl_scadenze_future += "        <td align=\"left\" valign=\"top\" nowrap>"+ nrrata +"</td>                                                                   \n";
                ls_tbl_scadenze_future += "        <td align=\"left\" valign=\"top\" title=\""+ pgdesc +"\">"+ org.apache.commons.lang.StringUtils.abbreviate(html.text(pgdesc), 20) + "</td>                    \n";
                ls_tbl_scadenze_future += "        <td align=\"left\" valign=\"top\" nowrap>"+ atk_ctrl.getDate(dtscad) +"</td>                                                 \n";

                String ls_style = "";
                if (pgcodi.equals("I")) ls_style = "style=\"color:AF0000;\"";

                ls_tbl_scadenze_future += "        <td align=\"right\" valign=\"top\" nowrap "+ ls_style +">"+ przFormat.format(residuo) + "</td>            \n";
                ls_tbl_scadenze_future += "        <td align=\"left\" valign=\"top\" nowrap>"+ html.text(vadesc) + "</td>                                    \n";
                ls_tbl_scadenze_future += "  </tr>                                                                                            \n";

        }
        
        if (!ls_tbl_scadenze_future.equals("")){
            String ls_tbl_scadenze_future_text2 = "";
            
            ls_tbl_scadenze_future_text2 += "<p>";
            ls_tbl_scadenze_future_text2 += atk_dwlingua.getLabel ("srv_scadenz", cdling, "clie_text_2_fut", "", null);
            ls_tbl_scadenze_future_text2 += "</p>";
            
            ls_tbl_scadenze_future = ls_tbl_scadenze_future_text2 + "<table width=\"658\" cellpadding=\"0\" cellspacing=\"0\" class=\"report\">\n" + of_setPar_SCADENZE_getHead_tbl_scadenze_clie (lstr_eute.cdling, "S") + ls_tbl_scadenze_future + of_setPar_SCADENZE_riga_tbl_totale_residuo(ld_totale_residuo, ld_totale_residuo_c, ls_vadesc_prec, ls_vadesc_euro, lb_fg_chg_valuta, przFormat, "S", lstr_eute.cdling) + "</table>\n";
        }
        
        
        ls_totale_residuo_scadenze_future = of_setPar_SCADENZE_string_totale_residuo(ld_totale_residuo, ld_totale_residuo_c, ls_vadesc_prec, ls_vadesc_euro, lb_fg_chg_valuta, przFormat);
    }
     
    
    ao_map.put("${scadenze.tbl_scadenze_future}", ls_tbl_scadenze_future);
    ao_map.put("${scadenze.totale_residuo_scadenze_future}", ls_totale_residuo_scadenze_future);
    
    
    String ls_dati_bancari_di_riferimento = "";
    String ls_label_nota_pagamento = atk_dwlingua.getLabel ("srv_scadenz", cdling, "nota.pagamento", "", null);
    
    if (!lstr_eute.tkclie.equals("")){
        
        String clie_cdnazi_m = "";
        String clie_cdlist = "";
        
        l_query = "";
        l_query += "  select nazi.cdnazi_m                                                                   \n";
        l_query += "       , clie.cdlist                                                                     \n";
        l_query += "  from pgmr.archclie      clie                                                           \n";
        l_query += "     , pgmr.archenti      ente                                                           \n";
        l_query += "     , pgmr.enteuniloc    enul                                                           \n";
        if (is_oracle){
            throw new Exception("DB non supportato");
        } else if (is_sybase || is_postgresql) {
            l_query += "     , pgmr.unitalocali ulsl                                                         \n";
            l_query += "         left outer join  pgmr.nazioni  nazi on  ulsl.cdnazi = nazi.cdnazi               \n";
            l_query += " where clie.tkclie  = '" + lstr_eute.tkclie + "'                                         \n";
            l_query += "   and clie.cdente  = ente.cdente                                                        \n";
            l_query += "   and ente.cdente  = enul.cdente                                                        \n";
            l_query += "   and enul.fseleg  = 'S'                                                                \n";
            l_query += "   and enul.cdunil = ulsl.cdunil                                                         \n";
        }
        
        pstm = m_connection.prepareStatement(l_query);
        
        ind = 1;
        
        rs = pstm.executeQuery();
        
        if (rs != null && rs.next()) {
            clie_cdnazi_m = "";
            clie_cdlist = "";
            
            if (rs.getObject("cdnazi_m") != null) {
                clie_cdnazi_m = rs.getString("cdnazi_m");
            }            
            if (rs.getObject("cdlist") != null) {
                clie_cdlist = rs.getString("cdlist");
            }            
        }
        
        pstm.close();
        pstm = null;
        
        /*
         * - DE per i clienti con nazione DE
         * - ES per i clienti con nazione ES
         * - FR per i clienti con nazione FR
         * - USA per i clienti con listino LUS
         * - ZZ per tutti gli altri
         * 
         */
        
        l_query = "";
        l_query += "  select codice               \n";
        l_query += "       , testo                \n";
        l_query += "  from pgmr.vist_banche       \n";
        l_query += " where codice = ?             \n";
        
        pstm = m_connection.prepareStatement(l_query);
        
        ind = 1;
        if (clie_cdnazi_m.equals("DE")){
            pstm.setString(ind++, "DE");
        } else if (clie_cdnazi_m.equals("ES")){
            pstm.setString(ind++, "ES");
        } else if (clie_cdnazi_m.equals("FR")){
            pstm.setString(ind++, "FR");
        } else if (clie_cdlist.equals("LUS")){
            pstm.setString(ind++, "USA");
        } else {
            pstm.setString(ind++, "ZZ");
        }
        
        
        rs = pstm.executeQuery();
        
        if (rs != null && rs.next()) {
            ls_dati_bancari_di_riferimento = "";
            
            if (rs.getObject("testo") != null) {
                ls_dati_bancari_di_riferimento = rs.getString("testo");
            }            
        }
        
        pstm.close();
        pstm = null;
        
        ls_dati_bancari_di_riferimento = "<div class=\"foot\" style=\"border: 1px solid #CCCCCC;\"><p style=\"font-size: 9px; font-weight: normal; margin:10px;\"><span style=\"font-weight: bold; color: #b8b8b8;\">"+ ls_label_nota_pagamento +":<br/><br/></span>"+ html.text(ls_dati_bancari_di_riferimento) +"</p></div>\n";
      
    }
    
    ao_map.put("${scadenze.dati_bancari_di_riferimento}", ls_dati_bancari_di_riferimento);    

    ep_costanti.close();
    f_utente.close();
    vist_scadenziario.close();
    ep_utente.close();
    atk_dwlingua.close();

    return ao_map;

  }
  
  public String of_setPar_SCADENZE_string_totale_residuo (double totale_residuo, double totale_residuo_c, String vadesc, String vadesc_euro, boolean fg_chg_valuta, java.text.NumberFormat przFormat) throws Exception{

       String ls_totale_residuo    = "";

        if (fg_chg_valuta){
            ls_totale_residuo = vadesc_euro + " " + przFormat.format(totale_residuo_c);
        } else {
            ls_totale_residuo = vadesc + " " + przFormat.format(totale_residuo);
        }

       return ls_totale_residuo;

    }
  
    public String of_setPar_SCADENZE_riga_tbl_totale_residuo (double totale_residuo, double totale_residuo_c, String vadesc, String vadesc_euro, boolean fg_chg_valuta, java.text.NumberFormat przFormat, String fgfuture, String cdling) throws Exception{

       Atk_dwlingua  atk_dwlingua = new Atk_dwlingua();
        
       HTML html = new HTML(); 
       
       setProfilo((Atk_sql) atk_dwlingua);
        
       String ls_totale_residuo    = "";

        
        ls_totale_residuo  = "";
        ls_totale_residuo  = "  <tr>                                                                                                            \n";
        ls_totale_residuo += "        <td align=\"right\" valign=\"top\" nowrap colspan=\"5\">"+ atk_dwlingua.getLabel ("srv_scadenz", cdling, "totale", "", null) +":</td>                                        \n";
        if (fg_chg_valuta){
            ls_totale_residuo += "        <td align=\"right\" valign=\"top\" nowrap><b>"+ przFormat.format(totale_residuo_c) + "</b></td>            \n";
            ls_totale_residuo += "        <td align=\"left\" valign=\"top\" nowrap><b>"+ html.text(vadesc_euro) + "</b></td>                                    \n";
        } else {
            ls_totale_residuo += "        <td align=\"right\" valign=\"top\" nowrap><b>"+ przFormat.format(totale_residuo) + "</b></td>            \n";
            ls_totale_residuo += "        <td align=\"left\" valign=\"top\" nowrap><b>"+ html.text(vadesc) + "</b></td>                                    \n";
        }
        if (!fgfuture.equals("S")){
            ls_totale_residuo += "        <td align=\"left\" valign=\"top\" nowrap>&nbsp;</td>                                        \n";
        }
        ls_totale_residuo += "  </tr>                                                                                                           \n";

        atk_dwlingua.close();

       return ls_totale_residuo;

    }
  
  
    public int of_setPar_SCADENZE_getGG_scaduta (ResultSet rs_scadenze) throws Exception{

       if (rs_scadenze == null) return 0;

       Timestamp  dtscad = null;
       int        pgrisc = 0;

       if (rs_scadenze.getObject("dtscad"        )!=null )   dtscad       = rs_scadenze.getTimestamp("dtscad"        );
       if (rs_scadenze.getObject("pgrisc"        )!=null )   pgrisc       = rs_scadenze.getInt      ("pgrisc"        );

       Timestamp lt_adesso = new Timestamp(System.currentTimeMillis()) ;
       Calendar rightNow = Calendar.getInstance();
       rightNow.setTime(lt_adesso);

       Calendar calDtscad = Calendar.getInstance();
       if(dtscad != null){
          calDtscad.setTime(dtscad);
          calDtscad.add(Calendar.DATE, (int) pgrisc);
       }

       return (int)((rightNow.getTimeInMillis() - calDtscad.getTimeInMillis())/86400000);
    }
    
    
    public String of_setPar_SCADENZE_getHead_tbl_scadenze_clie (String cdling, String fgfuture) throws Exception{
       Atk_dwlingua atk_dwlingua =  new Atk_dwlingua();

       setProfilo((Atk_sql) atk_dwlingua);

       String ls_head_dett  = "";

       ls_head_dett  = "<tr>                                 \n";
       ls_head_dett += " <th align=\"left\" valign=\"top\" nowrap style=\"width: 70px;\">" + atk_dwlingua.getLabel ("srv_scadenz", cdling, "nrdoc", "", null) + "        </th>            \n";
       ls_head_dett += " <th align=\"left\" valign=\"top\" nowrap style=\"width: 100px;\">" + atk_dwlingua.getLabel ("srv_scadenz", cdling, "dtdoc", "", null) + "        </th>            \n";
       ls_head_dett += " <th align=\"left\" valign=\"top\" nowrap style=\"width: 30px;\">" + atk_dwlingua.getLabel ("srv_scadenz", cdling, "nrrata", "", null) + "       </th>            \n";
       ls_head_dett += " <th align=\"left\" valign=\"top\" nowrap>" + atk_dwlingua.getLabel ("srv_scadenz", cdling, "pgdesc", "", null) + "       </th>            \n";
       ls_head_dett += " <th align=\"left\" valign=\"top\" nowrap style=\"width: 100px;\">" + atk_dwlingua.getLabel ("srv_scadenz", cdling, "dtscad", "", null) + "       </th>            \n";
       ls_head_dett += " <th align=\"left\" valign=\"top\" nowrap style=\"width: 130px;\">" + atk_dwlingua.getLabel ("srv_scadenz", cdling, "residuo_rata", "", null) + " </th>            \n";
       ls_head_dett += " <th align=\"left\" valign=\"top\" nowrap style=\"width: 40px;\">" + atk_dwlingua.getLabel ("srv_scadenz", cdling, "vadesc", "", null) + " </th>            \n";
       if (!fgfuture.equals("S")){
            ls_head_dett += " <th align=\"left\" valign=\"top\" nowrap style=\"width: 40px;\">" + atk_dwlingua.getLabel ("srv_scadenz", cdling, "gg_scad", "", null) + "      </th>            \n";
       }
       ls_head_dett += "</tr>                                \n";

       atk_dwlingua.close();

       return ls_head_dett;
    }

  public HashMap of_setPar_OV_MPRON (HashMap ao_map, long tkmpron , String cdling) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    if (ao_map ==null){
      ao_map = new HashMap();
    }


    ao_map = of_setPar_Vist_mpron(ao_map, tkmpron, cdling);

    return ao_map;

  }

  public HashMap of_setPar_OV_RICEZ (HashMap ao_map, long tkordi , String cdling) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    if (ao_map ==null){
      ao_map = new HashMap();
    }

    //per il riepilogo ordine
    ao_map = of_setPar_Web_ord_test(ao_map, tkordi , cdling);
    //per il contenuto della mail e nota pagamento
    ao_map = of_setPar_Web_ord_positito(ao_map, tkordi , cdling);

    return ao_map;

  }

  
  public HashMap of_setPar_EPRO_NACCB (HashMap ao_map, long tklog_notif   , String cdling) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    if (ao_map ==null){
      ao_map = new HashMap();
    }


    ao_map = of_setPar_Vist_log_notif(ao_map, tklog_notif, cdling);
  
    return ao_map;

  }

  
  public HashMap of_setPar_PREMI (HashMap ao_map, Str_key_vist_premi str_key_vist_premi, String cdling) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    if (ao_map ==null){
      ao_map = new HashMap();
    }


    ao_map = of_setPar_Vist_premi(ao_map, str_key_vist_premi, cdling);
  
    return ao_map;

  }

  

  public HashMap of_setPar_Archagen (HashMap ao_map, String as_cdagen, String as_livello, String cdling) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    if (ao_map ==null){
      ao_map = new HashMap();
    }
    
    HTML html = new HTML();
    
    Archagen  archagen = new Archagen();

    setProfilo((Atk_sql) archagen);


    String ls_dsptit    = "";
    String ls_cdtpsesso = "";
    String ls_cdispe    = "";
    String ls_cdagen    = "";
    String ls_cdcapo    = "";
    String ls_dsagen    = "";
    String ls_cdage     = "";

    String ls_email           =  "";
    String ls_email_comm      =  "";
    String ls_email_no_acc    =  "";
    String ls_email_tecn      =  "";
    String ls_email_amm       =  "";
    String ls_cell            =  "";


    rs = archagen.getCdagen(as_cdagen);

    if (rs != null && rs.next()){

        ls_email           =  "";
        ls_email_comm      =  "";
        ls_email_no_acc    =  "";
        ls_email_tecn      =  "";
        ls_cell            =  "";
        ls_dsptit          =  "";
        ls_cdtpsesso       =  "";

        if (rs.getObject("geispe")!= null) ls_cdispe = rs.getString("geispe");
        if (rs.getObject("cdcapo")!= null) ls_cdcapo = rs.getString("cdcapo");
        if (rs.getObject("cdagen")!= null) ls_cdagen = rs.getString("cdagen");
        if (rs.getObject("cdage" )!= null) ls_cdage  = rs.getString("cdage" );
        if (rs.getObject("dsagen")!= null) ls_dsagen = rs.getString("dsagen" );

        l_query   = "";
        l_query  += " select "+ of_descr_lingua(cdling, "ptit", "dsptit") +" as dsptit          \n";
        l_query  += "      , cdtpsesso                                                          \n";
        l_query  += "   from pgmr.ep_utente eute                                                \n";
        l_query  += "        left outer join pgmr.pers_titolo ptit on eute.cdptit = ptit.cdptit \n";
        l_query  += "  where eute.cdazie = '"+ cdazie +"'                                       \n";
        l_query  += "    and eute.cdagen = '"+ as_cdagen +"'                                    \n";

        pstm = m_connection.prepareStatement(l_query);
        
        ind = 1;
        
        rs = pstm.executeQuery();
        
        if (rs != null && rs.next()) {
           if (rs.getObject("dsptit"   )!= null) ls_dsptit    = rs.getString("dsptit"    );
           if (rs.getObject("cdtpsesso")!= null) ls_cdtpsesso = rs.getString("cdtpsesso" );
           
           if (ls_dsptit.equals("")) ls_dsptit = sql_String("select "+ of_descr_lingua(cdling, "", "dsptit") +" from pgmr.pers_titolo where cdptit = '"+ CDPTIT_DEF + "'");
        }
        
        //In tutti i casi al 20130520 il dsptit va bene che sia sempre minuscolo
        ls_dsptit = ls_dsptit.toLowerCase();
        
        pstm.close();   
        pstm = null;     
      
  
        ls_email           =  archagen.of_getEmail(as_cdagen, "", "");
        ls_email_comm      =  archagen.of_getEmail(as_cdagen, "", COM_COM);
        ls_email_no_acc    =  archagen.of_getEmail(as_cdagen, NOTIF_NACC, "");
        ls_email_tecn      =  archagen.of_getEmail(as_cdagen, "", COM_TEC);
        ls_email_amm       =  archagen.of_getEmail(as_cdagen, "", COM_AMM);
        ls_cell            =  archagen.of_getCellulare(as_cdagen, "", "");

    }  
      
    String ls_suffisso = "";
    
    if (as_livello.equals(IS_ARCHAGEN_LIVELLO_CAPOAREA)){
    
       ls_suffisso = "_capo";
    
    }else if (as_livello.equals(IS_ARCHAGEN_LIVELLO_AGENTE_RIF)){
      
       ls_suffisso = "_agenrif";
    }else if (as_livello.equals(IS_ARCHAGEN_LIVELLO_AGENTE)){

       ls_suffisso = "";
    }
    
    ao_map.put("${archagen.dsagen"+ls_suffisso+"}", ls_dsagen);
    ao_map.put("${archagen.dsptit"+ls_suffisso+"}", ls_dsptit);
    ao_map.put("${archagen.cdtpsesso"+ls_suffisso+"}", ls_cdtpsesso);
    ao_map.put("${archagen.email"+ls_suffisso+"}", ls_email);
    ao_map.put("${archagen.email"+ls_suffisso+"_comm}", ls_email_comm);
    ao_map.put("${archagen.email"+ls_suffisso+"_no_acc}", ls_email_no_acc);
    ao_map.put("${archagen.email"+ls_suffisso+"_tecn}", ls_email_tecn);
    ao_map.put("${archagen.email"+ls_suffisso+"_amm}", ls_email_amm);
    ao_map.put("${archagen.cell"+ls_suffisso+"}", ls_cell);

    
    if (as_livello.equals("")){

      if (!ls_cdcapo.equals("")){
        // --- il dato � ridondarto in caso l'agente sia capoarea
        ao_map = of_setPar_Archagen(ao_map, ls_cdcapo, IS_ARCHAGEN_LIVELLO_CAPOAREA, cdling);
      }else{
        ao_map = of_setPar_Archagen(ao_map, "", IS_ARCHAGEN_LIVELLO_CAPOAREA, cdling);
      
      }
      if (ls_cdcapo.equals(ls_cdagen)){
        // --- il fatto di essere capoarea ha priorit� rispetto al fatto di essere SUB-agente
        ao_map = of_setPar_Archagen(ao_map, "", IS_ARCHAGEN_LIVELLO_AGENTE_RIF, cdling);
      }else {
        if (!ls_cdage.equals("")){
          ao_map = of_setPar_Archagen(ao_map, ls_cdage, IS_ARCHAGEN_LIVELLO_AGENTE_RIF, cdling);
        }else {
          ao_map = of_setPar_Archagen(ao_map, "", IS_ARCHAGEN_LIVELLO_AGENTE_RIF, cdling);
        }  
      }  

      ao_map = of_setPar_Age_ispettori(ao_map, ls_cdispe, cdling);
    }
      
      

    archagen.close();
    
    return ao_map;
  }
  
  public HashMap of_setPar_Age_ispettori (HashMap ao_map, String cdispe, String cdling) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    if (ao_map ==null){
      ao_map = new HashMap();
    }

    Age_ispettori  age_ispettori = new Age_ispettori();

    HTML html = new HTML();

    setProfilo((Atk_sql) age_ispettori);

    String dsptit    = "";
    String cdtpsesso = "";
    
    l_query   = "";
    l_query  += " select "+ of_descr_lingua(cdling, "ptit", "dsptit") +" as dsptit          \n";
    l_query  += "      , cdtpsesso                                                          \n";
    l_query  += "   from pgmr.ep_utente eute                                                \n";
    l_query  += "        left outer join pgmr.pers_titolo ptit on eute.cdptit = ptit.cdptit \n";
    l_query  += "  where eute.cdazie = '"+ cdazie +"'                                       \n";
    l_query  += "    and eute.cdispe = '"+ cdispe +"'                                       \n";

    pstm = m_connection.prepareStatement(l_query);
        
    ind = 1;

    rs = pstm.executeQuery();

    if (rs != null && rs.next()) {
        if (rs.getObject("dsptit"   )!= null) dsptit    = rs.getString("dsptit"    );
        if (rs.getObject("cdtpsesso")!= null) cdtpsesso = rs.getString("cdtpsesso" );
           
        if (dsptit.equals("")) dsptit = sql_String("select "+ of_descr_lingua(cdling, "", "dsptit") +" from pgmr.pers_titolo where cdptit = '"+ CDPTIT_DEF + "'");
    }
    
    //In tutti i casi al 20130520 il dsptit va bene che sia sempre minuscolo
    dsptit = dsptit.toLowerCase();

    pstm.close();
    pstm = null;

    String dsispe          =  sql_String("select dsispe from pgmr.age_ispettori where cdispe = '"+ cdispe +"'");
    String cell            =  age_ispettori.of_getCell(cdispe, "", "");
    String email_comm      =  age_ispettori.of_getEmail(cdispe, "", COM_COM);
    String email_no_acc    =  age_ispettori.of_getEmail(cdispe, NOTIF_NACC, "");
    String email_tecn      =  age_ispettori.of_getEmail(cdispe, "", COM_TEC);
    String email           =  age_ispettori.of_getEmail(cdispe, "", "");
    String email_amm       =  age_ispettori.of_getEmail(cdispe, "", COM_AMM);
    
    ao_map.put("${age_ispettori.dsispe}"       , dsispe                          );
    ao_map.put("${age_ispettori.dsptit}"       , dsptit                          );
    ao_map.put("${age_ispettori.cdtpsesso}"    , cdtpsesso                       );
    ao_map.put("${age_ispettori.cell}"         , cell                            );
    ao_map.put("${age_ispettori.email_comm}"   , email_comm                      );
    ao_map.put("${age_ispettori.email_no_acc}" , email_no_acc                    );
    ao_map.put("${age_ispettori.email_tecn}"   , email_tecn                      );
    ao_map.put("${age_ispettori.email}"        , email                           );
    ao_map.put("${age_ispettori.email_amm}"    , email_amm                       );

    age_ispettori.close();
    
    return ao_map;
  }

  public HashMap of_setPar_Archclie (HashMap ao_map, String tkclie, String cdling) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    PreparedStatement pstm2 = null;
    ResultSet rs = null;

    if (ao_map ==null){
      ao_map = new HashMap();
    }

    Archclie  archclie = new Archclie();
    Crm_credential crm_credential = new Crm_credential();
    Ep_costanti  ep_costanti = new Ep_costanti();

    HTML html = new HTML();

    setProfilo((Atk_sql) archclie);
    setProfilo((Atk_sql) crm_credential);
    setProfilo((Atk_sql) ep_costanti);
    
    Str_crm_credential lstr_crm_credential = new Str_crm_credential();

    String ep_portal_url    = ep_costanti.getCostvalue("ep.portal_url");
    ep_portal_url = of_cambiaURLLingua(ep_portal_url, cdling);

    
    String cdclie_m    = "";
    String ragcog      = "";
    String cdsuba      = "";
    String cdagen      = "";
    String cdcapo      = "";
    String cdispe      = "";
    String cdresp      = "";
    String cdente      = "";
    String cdzcom      = "";
    String dsptit      = "";
    String cdtpsesso   = "";
    long   tkutente_clie      = 0;
    
    String  l_query = "";
    
    l_query  = "";
    l_query  = "  select clie.cdclie_m                   \n";
    l_query += "       , ente.ragcog                     \n";
    l_query += "       , clie.cdsuba                     \n";
    l_query += "       , clie.cdagen                     \n";
    l_query += "       , ( select iagec.cdcapo                     \n";
    l_query += "             from pgmr.archagen iagec              \n";
    l_query += "            where iagec.cdagen = clie.cdagen       \n";
    l_query += "         ) as cdcapo                               \n";
    l_query += "       , clie.cdispe                     \n";
    l_query += "       , clie.cdresp                     \n";
    l_query += "       , clie.cdente                     \n";
    l_query += "       , clie.cdzcom                     \n";
    l_query += "       , (select in_eute.tkutente                                                          \n";
    l_query += "            from pgmr.ep_utente in_eute                                                    \n";
    l_query += "           where in_eute.fgabil = 'S'                                                      \n";
    l_query += "             and in_eute.tkclie = clie.tkclie) as tkutente_clie                            \n";
    l_query += "    from pgmr.archenti ente              \n";
    l_query += "       , pgmr.archclie clie              \n";
    l_query += "   where clie.tkclie = ?                 \n";
    l_query += "     and clie.cdazie = '"+ cdazie +"'    \n";
    l_query += "     and clie.cdente = ente.cdente       \n";
    
    pstm = m_connection.prepareStatement(l_query);
    
    ind = 1;
    pstm.setString(ind++, tkclie);
    
    rs = pstm.executeQuery();
    
    if (rs!=null && rs.next()){
      cdclie_m = "";
      ragcog = "";
      cdsuba = "";
      cdagen = "";
      cdcapo = "";
      cdispe = "";
      cdresp = "";
      cdente = "";
      cdzcom = "";
      tkutente_clie      = 0;
      
      if (rs.getObject("cdclie_m") != null)  cdclie_m = rs.getString("cdclie_m"); 
      if (rs.getObject("ragcog") != null)  ragcog = rs.getString("ragcog"); 
      if (rs.getObject("cdsuba") != null)  cdsuba = rs.getString("cdsuba"); 
      if (rs.getObject("cdagen") != null)  cdagen = rs.getString("cdagen"); 
      if (rs.getObject("cdcapo") != null)  cdcapo = rs.getString("cdcapo"); 
      if (rs.getObject("cdispe") != null)  cdispe = rs.getString("cdispe"); 
      if (rs.getObject("cdresp") != null)  cdresp = rs.getString("cdresp"); 
      if (rs.getObject("cdente") != null)  cdente = rs.getString("cdente"); 
      if (rs.getObject("cdzcom") != null)  cdzcom = rs.getString("cdzcom"); 
      if (rs.getObject("tkutente_clie"  ) != null)  tkutente_clie   = rs.getLong  ("tkutente_clie"  ); 
      
      
      dsptit    = "";
      cdtpsesso = "";

      if (tkutente_clie > 0){
        
        l_query   = "";
        l_query  += " select "+ of_descr_lingua(cdling, "ptit", "dsptit") +" as dsptit          \n";
        l_query  += "      , cdtpsesso                                                          \n";
        l_query  += "   from pgmr.ep_utente eute                                                \n";
        l_query  += "        left outer join pgmr.pers_titolo ptit on eute.cdptit = ptit.cdptit \n";
        l_query  += "  where eute.cdazie = '"+ cdazie +"'                                       \n";
        l_query  += "    and eute.tkutente = "+ tkutente_clie +"                                \n";

        pstm2 = m_connection.prepareStatement(l_query);
        
        ind = 1;

        rs = pstm2.executeQuery();

        if (rs != null && rs.next()) {
           if (rs.getObject("dsptit"   )!= null) dsptit    = rs.getString("dsptit"    );
           if (rs.getObject("cdtpsesso")!= null) cdtpsesso = rs.getString("cdtpsesso" );
           
           if (dsptit.equals("")) dsptit = sql_String("select "+ of_descr_lingua(cdling, "", "dsptit") +" from pgmr.pers_titolo where cdptit = '"+ CDPTIT_DEF + "'");
        }
    
        //In tutti i casi al 20130520 il dsptit va bene che sia sempre minuscolo
        dsptit = dsptit.toLowerCase();

        pstm2.close(); 
        pstm2 = null;
      }  
    }

    pstm.close();
    pstm = null;
    
    
    String ls_url_portal_keycode = ep_portal_url;
    
    if (tkutente_clie > 0){
      
      lstr_crm_credential.tkutente = tkutente_clie;
      
      lstr_crm_credential.url = ep_portal_url;
      if (crm_credential.of_getUrlKeycode(lstr_crm_credential) == 1)      crm_credential.m_connection.commit();
      ls_url_portal_keycode = ep_portal_url + lstr_crm_credential.keycode_url;

    }
    
    ao_map.put("${archclie.cdclie_m}"        , cdclie_m                                      );
    ao_map.put("${archclie.dsptit}"          , dsptit                                        );
    ao_map.put("${archclie.cdtpsesso}"       , cdtpsesso                                     );
    ao_map.put("${archclie.email}"           , archclie.of_getEmail(tkclie, "", "")          );
    ao_map.put("${archclie.email_tecn}"      , archclie.of_getEmail(tkclie, "", COM_TEC)     );
    ao_map.put("${archclie.email_comm}"      , archclie.of_getEmail(tkclie, "", COM_COM)     );
    ao_map.put("${archclie.email_amm}"       , archclie.of_getEmail(tkclie, "", COM_AMM)     );
    ao_map.put("${oggetto.text_clie}"        , " - "+ ragcog                                 );
    ao_map.put("${archclie.url_portal_keycode}"  , ls_url_portal_keycode                     );
    
    ao_map = of_setPar_Zonacomm(ao_map, cdzcom, cdling);
    ao_map = of_setPar_Archenti(ao_map, cdente, cdling);
    ao_map = of_setPar_Archagen(ao_map, cdsuba, "", cdling);
    ao_map = of_setPar_Archagen(ao_map, cdagen, IS_ARCHAGEN_LIVELLO_AGENTE_RIF, cdling);
    ao_map = of_setPar_Archagen(ao_map, cdcapo, IS_ARCHAGEN_LIVELLO_CAPOAREA, cdling);
    ao_map = of_setPar_Age_ispettori(ao_map, cdispe, cdling);
    ao_map = of_setPar_Archresp(ao_map, cdresp, cdling);


    archclie.close();   
    crm_credential.close();   
    ep_costanti.close(); 
    
    return ao_map;
  }

  public HashMap of_setPar_Archenti (HashMap ao_map, String cdente, String cdling) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    if (ao_map ==null){
      ao_map = new HashMap();
    }

    HTML html = new HTML();


    String ragcog      = "";


    String  l_query = "";
    
    l_query  = "";
    l_query  = "  select ragcog                     \n";
    l_query += "    from pgmr.archenti              \n";
    l_query += "   where cdente = ?                 \n";
    l_query += "     and cdazie = '"+ cdazie +"'    \n";

    pstm = m_connection.prepareStatement(l_query);
    
    ind = 1;
    pstm.setString(ind++, cdente);
    
    rs = pstm.executeQuery();
    
    if (rs!=null && rs.next()){
      ragcog = "";
      
      if (rs.getObject("ragcog") != null)  ragcog = rs.getString("ragcog"); 
    }
    
    pstm.close();
    pstm = null;

    
    ao_map.put("${archenti.ragcog}"        , ragcog                           );

    
    return ao_map;
  }

  public HashMap of_setPar_Archresp (HashMap ao_map, String cdresp, String cdling) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    if (ao_map ==null){
      ao_map = new HashMap();
    }

    Archresp  archresp = new Archresp();

    HTML html = new HTML();

    setProfilo((Atk_sql) archresp);
    
    String dsptit = "";
    String cdtpsesso = "";
    
    l_query   = "";
    l_query  += " select "+ of_descr_lingua(cdling, "ptit", "dsptit") +" as dsptit          \n";
    l_query  += "      , cdtpsesso                                                          \n";
    l_query  += "   from pgmr.ep_utente eute                                                \n";
    l_query  += "        left outer join pgmr.pers_titolo ptit on eute.cdptit = ptit.cdptit \n";
    l_query  += "  where eute.cdazie = '"+ cdazie +"'                                       \n";
    l_query  += "    and eute.cdresp = '"+ cdresp +"'                                       \n";

    pstm = m_connection.prepareStatement(l_query);
        
    ind = 1;

    rs = pstm.executeQuery();

    if (rs != null && rs.next()) {
        if (rs.getObject("dsptit"   )!= null) dsptit    = rs.getString("dsptit"    );
        if (rs.getObject("cdtpsesso")!= null) cdtpsesso = rs.getString("cdtpsesso" );
           
        if (dsptit.equals("")) dsptit = sql_String("select "+ of_descr_lingua(cdling, "", "dsptit") +" from pgmr.pers_titolo where cdptit = '"+ CDPTIT_DEF + "'");
    }
    
    //In tutti i casi al 20130520 il dsptit va bene che sia sempre minuscolo
    dsptit = dsptit.toLowerCase();

    pstm.close(); 
    pstm = null;
    

    String dsresp          =  sql_String("select dsresp from pgmr.archresp where cdresp = '"+ cdresp +"'");
    String cell            =  archresp.of_getCell(cdresp, "", "");
    String email           =  archresp.of_getEmail(cdresp, "", "");
    String email_no_acc    =  archresp.of_getEmail(cdresp, NOTIF_NACC, "");
    String email_tecn      =  archresp.of_getEmail(cdresp, "", COM_TEC);
    String email_comm      =  archresp.of_getEmail(cdresp, "", COM_COM);
    String email_amm      =  archresp.of_getEmail(cdresp, "", COM_AMM);
    
    ao_map.put("${archresp.dsresp}"       , dsresp                          );
    ao_map.put("${archresp.dsptit}"       , dsptit                          );
    ao_map.put("${archresp.cdtpsesso}"    , cdtpsesso                       );
    ao_map.put("${archresp.cell}"         , cell                            );
    ao_map.put("${archresp.email}"        , email                           );
    ao_map.put("${archresp.email_no_acc}" , email_no_acc                    );
    ao_map.put("${archresp.email_tecn}"   , email_tecn                      );
    ao_map.put("${archresp.email_comm}"   , email_comm                      );
    ao_map.put("${archresp.email_amm}"    , email_amm                       );


    archresp.close();
    
    return ao_map;
  }

  public HashMap of_setPar_Archrubrica_modif (HashMap ao_map, long tkrubr_mod, String cdling) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    PreparedStatement pstm2 = null;
    ResultSet rs = null;
    ResultSet rs2 = null;

    if (ao_map ==null){
      ao_map = new HashMap();
    }

    Zonacomm  zonacomm = new Zonacomm();
    Ep_costanti  ep_costanti = new Ep_costanti();
    Archrubrica_tipo  archrubrica_tipo = new Archrubrica_tipo();
    Atk_dwlingua  atk_dwlingua = new Atk_dwlingua();

    HTML html = new HTML();

    setProfilo((Atk_sql) zonacomm);
    setProfilo((Atk_sql) ep_costanti);
    setProfilo((Atk_sql) archrubrica_tipo);
    setProfilo((Atk_sql) atk_dwlingua);


    long   tkrubr      = 0;
    long   tkticket    = 0;
    String nota        = "";
    String dsrubr_tipo = "";
    String ragcog      = "";
    String dszcom      = "";
    String dsagen      = "";
    String nota_ext    = "";


    String  l_query = "";
    
    l_query  = "";
    l_query  = "  select rubr.tkrubr                                                                            \n";
    l_query += "       , rmod.tkticket                                                                          \n";
    l_query += "       , rmod.nota                                                                              \n";
    l_query += "       , rubr.cdente                                                                            \n";
    l_query += "       , rubr.ragcog                                                                            \n";
    l_query += "       , "+ archrubrica_tipo.of_descr_lingua(cdling, "rtip", "dsrubr_tipo") +" as dsrubr_tipo   \n";
    l_query += "       , ( select "+ zonacomm.of_descr_lingua(cdling, "in_zcom", "dszcom") +"                   \n";
    l_query += "             from pgmr.archclie     in_clie                                                     \n";
    l_query += "                , pgmr.zonacomm     in_zcom                                                     \n";
    l_query += "            where in_zcom.cdzcom = in_clie.cdzcom                                               \n";
    l_query += "              and in_clie.cdente = rubr.cdente                                                  \n";
    l_query += "         ) as dszcom                                                                            \n";
    l_query += "       , ( select in_agen.dsagen                                                                \n";
    l_query += "             from pgmr.archclie     in_clie                                                     \n";
    l_query += "                , pgmr.archagen     in_agen                                                     \n";
    l_query += "            where in_agen.cdagen = in_clie.cdagen                                               \n";
    l_query += "              and in_clie.cdente = rubr.cdente                                                  \n";
    l_query += "         ) as dsagen                                                                            \n";
    l_query += "    from pgmr.archrubrica        rubr                                                           \n";
    l_query += "       , pgmr.archrubrica_modif  rmod                                                           \n";
    l_query += "       , pgmr.archrubrica_tipo   rtip                                                           \n";
    l_query += "   where rubr.tkrubr      = rmod.tkrubr                                                         \n";
    l_query += "     and rubr.tkrubr_tipo = rtip.tkrubr_tipo                                                    \n";
    l_query += "     and rmod.tkrubr_mod  = ?                                                                   \n";


    pstm = m_connection.prepareStatement(l_query);
    
    ind = 1;
    pstm.setLong  (ind++, tkrubr_mod);
    
    rs = pstm.executeQuery();
    
    if (rs!=null && rs.next()){
      tkrubr      = 0;
      tkticket    = 0;
      nota        = "";
      dsrubr_tipo = "";
      ragcog      = "";
      dszcom      = "";
      dsagen      = "";

      if (rs.getObject("tkrubr"     ) != null)  tkrubr      = rs.getLong  ("tkrubr"     ); 
      if (rs.getObject("tkticket"   ) != null)  tkticket    = rs.getLong  ("tkticket"   ); 
      if (rs.getObject("nota"       ) != null)  nota        = rs.getString("nota"       ); 
      if (rs.getObject("dsrubr_tipo") != null)  dsrubr_tipo = rs.getString("dsrubr_tipo"); 
      if (rs.getObject("ragcog"     ) != null)  ragcog      = rs.getString("ragcog"     ); 
      if (rs.getObject("dszcom"     ) != null)  dszcom      = rs.getString("dszcom"     ); 
      if (rs.getObject("dsagen"     ) != null)  dsagen      = rs.getString("dsagen"     ); 
      

      nota_ext    = "";
      
      
      l_query  = "";
      l_query  = "   select rmod_nota.nota                                     \n";
      l_query += "     from pgmr.archrubrica_modif_nota  rmod_nota             \n";
      l_query += "    where rmod_nota.tkrubr_mod  = ?                          \n";
      l_query += " order by rmod_nota.nrposi                                   \n";
      
      pstm2 = m_connection.prepareStatement(l_query);
      
      ind = 1;
      pstm2.setLong   (ind++, tkrubr_mod);
      
      rs2 = pstm2.executeQuery();
      
      while (rs2!=null && rs2.next()){
        if (rs2.getObject("nota") != null)  nota_ext += rs2.getString("nota"); 
      }
      
      if (nota_ext.equals("")) nota_ext = nota;
      
      pstm2.close();
      pstm2 = null;
    }
    
    pstm.close();
    pstm = null;
    
    String ep_portal_url    = ep_costanti.getCostvalue("ep.portal_url");
    ep_portal_url = of_cambiaURLLingua(ep_portal_url, cdling);
    String ep_eprogen_url    = ep_costanti.getCostvalue("ep.eprogen_url");
    ep_eprogen_url = of_cambiaURLLingua(ep_eprogen_url, cdling);
    
    if (ep_eprogen_url.equals("")) ep_eprogen_url = of_trasformaURL(ep_portal_url, "portal", "eprogen");

    nota_ext = nota_ext.replace(com.ateikon.common.Archrubrica_modif.ESCAPE_ADD, "<img src=\""+ ep_eprogen_url +"img/"+ com.ateikon.common.Archrubrica_modif.IMG_ADD +"\" />");
    nota_ext = nota_ext.replace(com.ateikon.common.Archrubrica_modif.ESCAPE_ADDED, "<img src=\""+ ep_eprogen_url +"img/"+ com.ateikon.common.Archrubrica_modif.IMG_ADD +"\" />");
    nota_ext = nota_ext.replace(com.ateikon.common.Archrubrica_modif.ESCAPE_DELETE, "<img src=\""+ ep_eprogen_url +"img/"+ com.ateikon.common.Archrubrica_modif.IMG_DELETE +"\" />");
    nota_ext = nota_ext.replace(com.ateikon.common.Archrubrica_modif.ESCAPE_DELETED, "<img src=\""+ ep_eprogen_url +"img/"+ com.ateikon.common.Archrubrica_modif.IMG_DELETE +"\" />");
    nota_ext = nota_ext.replace(com.ateikon.common.Archrubrica_modif.ESCAPE_PUNTO, "<img src=\""+ ep_eprogen_url +"img/"+ com.ateikon.common.Archrubrica_modif.IMG_PUNTO +"\" />");
    nota_ext = html.text(nota_ext);
    
    ao_map.put("${archrubrica_modif.nota}"        , nota_ext                           );

    String ls_modif_text_1  = atk_dwlingua.getLabel ("ep_ticket", cdling, "modif_text_1", "", null);
    String ls_text_zcom     = atk_dwlingua.getLabel ("ep_ticket", cdling, "text_zcom"   , "", null);
    String ls_text_agen     = atk_dwlingua.getLabel ("ep_ticket", cdling, "text_agen"   , "", null);

    String ls_rubr_rm_cg_tbl_dati = "";

    ls_rubr_rm_cg_tbl_dati  = "";
    ls_rubr_rm_cg_tbl_dati  = "  <table width=\"709\" cellpadding=\"0\" cellspacing=\"0\" class=\"parametri\">                                                \n";
    ls_rubr_rm_cg_tbl_dati += "    <tr>                                                                                                                       \n";
    ls_rubr_rm_cg_tbl_dati += "      <th width=\"170\" align=\"left\" valign=\"top\" nowrap class=\"Stile1\"><b>"+ html.text(dsrubr_tipo) +":</b></th>        \n";
    ls_rubr_rm_cg_tbl_dati += "      <td valign=\"top\" class=\"Stile1\">"+ html.text(ragcog) +"</td>                                                         \n";
    ls_rubr_rm_cg_tbl_dati += "    </tr>                                                                                                                      \n";
    ls_rubr_rm_cg_tbl_dati += "    <tr>                                                                                                                       \n";
    ls_rubr_rm_cg_tbl_dati += "      <th width=\"170\" align=\"left\" valign=\"top\" nowrap class=\"Stile1\"><b>"+ html.text(ls_modif_text_1) +":</b></th>    \n";
    ls_rubr_rm_cg_tbl_dati += "      <td valign=\"top\" class=\"Stile1\">"+ tkrubr +"</td>                                                                    \n";
    ls_rubr_rm_cg_tbl_dati += "    </tr>                                                                                                                      \n";
    if (!dszcom.equals("")        ){
      ls_rubr_rm_cg_tbl_dati += " <tr>                                                                                                                          \n";
      ls_rubr_rm_cg_tbl_dati += "   <th width=\"170\" align=\"left\" valign=\"top\" nowrap class=\"Stile1\"><b>"+ html.text(ls_text_zcom) +":</b></th>          \n";
      ls_rubr_rm_cg_tbl_dati += "   <td valign=\"top\" class=\"Stile1\">"+ html.text(dszcom) +"</td>                                                            \n";
      ls_rubr_rm_cg_tbl_dati += " </tr>                                                                                                                         \n";
    }
    if (!dsagen.equals("")        ){
      ls_rubr_rm_cg_tbl_dati += " <tr>                                                                                                                          \n";
      ls_rubr_rm_cg_tbl_dati += "   <th width=\"170\" align=\"left\" valign=\"top\" nowrap class=\"Stile1\"><b>"+ html.text(ls_text_agen) +":</b></th>          \n";
      ls_rubr_rm_cg_tbl_dati += "   <td valign=\"top\" class=\"Stile1\">"+ html.text(dsagen) +"</td>                                                            \n";
      ls_rubr_rm_cg_tbl_dati += " </tr>                                                                                                                         \n";
    }
    ls_rubr_rm_cg_tbl_dati += "  </table>                                                                                                                     \n";

    ao_map.put("${rubr_rm_cg.tbl_dati}"           , ls_rubr_rm_cg_tbl_dati         );
    
    
    ao_map.put("${rubr_rm_cg.tbl_mod_eff}"        , nota_ext                           );
    
    

    ao_map = of_setPar_Ep_ticket(ao_map, tkticket, cdling);
    ao_map = of_setPar_Archrubrica(ao_map, tkrubr, cdling);

    zonacomm.close();      
    ep_costanti.close();      
    archrubrica_tipo.close();      
    atk_dwlingua.close();      
    
    return ao_map;
  }

  public HashMap of_setPar_Archrubrica_tipo (HashMap ao_map, long tkrubr_tipo, String cdling) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    if (ao_map ==null){
      ao_map = new HashMap();
    }

    Archrubrica_tipo  archrubrica_tipo = new Archrubrica_tipo();

    HTML html = new HTML();

    setProfilo((Atk_sql) archrubrica_tipo);


    String dsrubr_tipo        = archrubrica_tipo.getDsrubr_tipo(tkrubr_tipo, cdling);

    //In tutti i casi al 20111119 il dsrubr_tipo va bene che sia sempre minuscolo
    dsrubr_tipo = dsrubr_tipo.toLowerCase();
    
    ao_map.put("${archrubrica_tipo.dsrubr_tipo}"        , dsrubr_tipo                           );


    archrubrica_tipo.close();      
    
    return ao_map;
  }

  public HashMap of_setPar_Archrubrica (HashMap ao_map, long tkrubr, String cdling) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    if (ao_map ==null){
      ao_map = new HashMap();
    }

    Archrubrica_tipo  archrubrica_tipo = new Archrubrica_tipo();
    Atk_dwlingua  atk_dwlingua = new Atk_dwlingua();
    F_utente  f_utente = new F_utente();
    Ep_costanti  ep_costanti = new Ep_costanti();
    Archagen  archagen = new Archagen();
    Archclie  archclie = new Archclie();

    HTML html = new HTML();
    Atk_ctrl atk_ctrl = new Atk_ctrl();

    setProfilo((Atk_sql) archrubrica_tipo);
    setProfilo((Atk_sql) atk_dwlingua);
    setProfilo((Atk_sql) f_utente);
    setProfilo((Atk_sql) ep_costanti);
    setProfilo((Atk_sql) archagen);
    setProfilo((Atk_sql) archclie);

    String ep_portal_url    = ep_costanti.getCostvalue("ep.portal_url");
    ep_portal_url = of_cambiaURLLingua(ep_portal_url, cdling);
    String ep_site_url    = ep_costanti.getCostvalue("ep.site_url");
    String ep_site_url_ori = ep_site_url;
    ep_site_url = of_cambiaURLLingua(ep_site_url, cdling);
    String ep_site_ext_url    = ep_costanti.getCostvalue("ep.site_ext_url");
    ep_site_ext_url = of_cambiaURLLingua(ep_site_ext_url, cdling);
    String ep_eprogen_url    = ep_costanti.getCostvalue("ep.eprogen_url");
    ep_eprogen_url = of_cambiaURLLingua(ep_eprogen_url, cdling);
    String ep_shop_url    = ep_costanti.getCostvalue("ep.shop_url");
    ep_shop_url = of_cambiaURLLingua(ep_shop_url, s_cdling);
    

    if (ep_eprogen_url.equals("")) ep_eprogen_url = of_trasformaURL(ep_portal_url, "portal", "eprogen");
    if (ep_shop_url.equals("")) ep_shop_url = of_trasformaURL(ep_portal_url, "portal", "shop");


    long   tkutente_aream     = 0;
    long   tkutente_respv     = 0;
    long   tkrubr_tipo        = 0;
    String dsrubr_tipo        = "";
    String ragcog             = "";
    String recapito_email_lav = "";
    String email_aream        = "";
    String email_respv        = "";
    String tkclie             = "";
    String dsagen_reg         = "";
    String rubr_cdispe_inc    = "";
    String rubr_cdcapo_inc    = "";
    String rubr_cdagen_inc    = "";
    String rubr_cdsuba_inc    = "";
    String rubr_tkclie_rivend1  = "";
    String rubr_tkclie_rivend2  = "";
    String rubr_tkclie_rivend3  = "";
    String rubr_tkclie_rivend4  = "";
    Timestamp rubr_dtinse     = null;
    String rubr_origine_dati  = "";


    String  l_query = "";
    
    l_query  = "";
    l_query  = "  select rubr.tkrubr                                                                            \n";
    l_query += "       , rubr.cdente                                                                            \n";
    l_query += "       , rubr.ragcog                                                                            \n";
    l_query += "       , rubr.tkrubr_tipo                                                                       \n";
    l_query += "       , "+ archrubrica_tipo.of_descr_lingua(cdling, "rtip", "dsrubr_tipo") +" as dsrubr_tipo   \n";
    l_query += "       , rubr.tkutente_aream_inc                                                                \n";
    l_query += "       , rubr.tkutente_respv_inc                                                                \n";
    l_query += "       , ( select in_eute.email                                                                 \n";
    l_query += "             from pgmr.ep_utente    in_eute                                                     \n";
    l_query += "            where in_eute.tkutente = rubr.tkutente_aream_inc                                    \n";
    l_query += "         ) as email_aream                                                                       \n";
    l_query += "       , ( select in_eute.email                                                                 \n";
    l_query += "             from pgmr.ep_utente    in_eute                                                     \n";
    l_query += "            where in_eute.tkutente = rubr.tkutente_respv_inc                                    \n";
    l_query += "         ) as email_respv                                                                       \n";
    l_query += "       , ( select in_clie.tkclie                                                                \n";
    l_query += "             from pgmr.archclie     in_clie                                                     \n";
    l_query += "            where in_clie.cdente = rubr.cdente                                                  \n";
    l_query += "         ) as tkclie                                                                            \n";
    l_query += "       , (select in_agen.dsagen                                                                 \n";
    l_query += "            from pgmr.ep_utente in_eute                                                         \n";
    l_query += "               , pgmr.archagen  in_agen                                                         \n";
    l_query += "               , pgmr.ep_ticket in_tikt                                                         \n";
    l_query += "           where in_eute.cdagen = in_agen.cdagen                                                \n";
    l_query += "             and in_eute.tkutente = in_tikt.tkutente_inse                                       \n";
    l_query += "             and in_tikt.tkticket = rubr.tkticket_reg                                           \n";
    l_query += "          ) as dsagen_reg                                                                       \n";
    l_query += "       , rubr.cdispe_inc  as rubr_cdispe_inc                                                    \n";
    l_query += "       , ( select in_agen.cdcapo                                                                \n";
    l_query += "             from pgmr.archagen  in_agen                                                        \n";
    l_query += "            where in_agen.cdagen = rubr.cdagen_inc  ) as rubr_cdcapo_inc                        \n";
    l_query += "       , rubr.cdagen_inc  as rubr_cdagen_inc                                                    \n";
    l_query += "       , rubr.cdsuba_inc  as rubr_cdsuba_inc                                                    \n";
    l_query += "       , rubr.dtinse      as rubr_dtinse                                                        \n";
    l_query += "       , rubr.tkclie_rivend      as rubr_tkclie_rivend1                                         \n";
    l_query += "       , rubr.tkclie_rivend2     as rubr_tkclie_rivend2                                         \n";
    l_query += "       , rubr.tkclie_rivend3     as rubr_tkclie_rivend3                                         \n";
    l_query += "       , rubr.tkclie_rivend4     as rubr_tkclie_rivend4                                         \n";
    l_query += "       , rubr.origine_dati       as rubr_origine_dati                                           \n";
    l_query += "    from pgmr.archrubrica        rubr                                                           \n";
    l_query += "       , pgmr.archrubrica_tipo   rtip                                                           \n";
    l_query += "   where rubr.tkrubr_tipo = rtip.tkrubr_tipo                                                    \n";
    l_query += "     and rubr.tkrubr      = ?                                                                   \n";


    pstm = m_connection.prepareStatement(l_query);
    
    ind = 1;
    pstm.setLong  (ind++, tkrubr);
    
    rs = pstm.executeQuery();
    
    if (rs!=null && rs.next()){

      tkutente_aream     = 0;
      tkutente_respv     = 0;
      tkrubr_tipo        = 0;
      dsrubr_tipo        = "";
      ragcog             = "";
      email_aream        = "";
      email_respv        = "";
      tkclie             = "";
      dsagen_reg         = "";
      rubr_cdispe_inc    = "";
      rubr_cdagen_inc    = "";
      rubr_cdcapo_inc    = "";
      rubr_cdsuba_inc    = "";
      rubr_tkclie_rivend1 = "";
      rubr_tkclie_rivend2 = "";
      rubr_tkclie_rivend3 = "";
      rubr_tkclie_rivend4 = "";
      rubr_origine_dati = "";

      if (rs.getObject("tkutente_aream_inc") != null)  tkutente_aream   = rs.getLong  ("tkutente_aream_inc"); 
      if (rs.getObject("tkutente_respv_inc") != null)  tkutente_respv   = rs.getLong  ("tkutente_respv_inc"); 
      if (rs.getObject("tkrubr_tipo"       ) != null)  tkrubr_tipo      = rs.getLong  ("tkrubr_tipo"       ); 
      if (rs.getObject("dsrubr_tipo"       ) != null)  dsrubr_tipo      = rs.getString("dsrubr_tipo"       ); 
      if (rs.getObject("ragcog"            ) != null)  ragcog           = rs.getString("ragcog"            ); 
      if (rs.getObject("email_aream"       ) != null)  email_aream      = rs.getString("email_aream"       ); 
      if (rs.getObject("email_respv"       ) != null)  email_respv      = rs.getString("email_respv"       ); 
      if (rs.getObject("tkclie"            ) != null)  tkclie           = rs.getString("tkclie"            ); 
      if (rs.getObject("dsagen_reg"        ) != null)  dsagen_reg       = rs.getString("dsagen_reg"        ); 
      if (rs.getObject("rubr_cdispe_inc"   ) != null)  rubr_cdispe_inc  = rs.getString("rubr_cdispe_inc"   ); 
      if (rs.getObject("rubr_cdcapo_inc"   ) != null)  rubr_cdcapo_inc  = rs.getString("rubr_cdcapo_inc"   ); 
      if (rs.getObject("rubr_cdagen_inc"   ) != null)  rubr_cdagen_inc  = rs.getString("rubr_cdagen_inc"   ); 
      if (rs.getObject("rubr_cdsuba_inc"   ) != null)  rubr_cdsuba_inc  = rs.getString("rubr_cdsuba_inc"   ); 
      if (rs.getObject("rubr_tkclie_rivend1") != null)  rubr_tkclie_rivend1 = rs.getString("rubr_tkclie_rivend1"); 
      if (rs.getObject("rubr_tkclie_rivend2") != null)  rubr_tkclie_rivend2 = rs.getString("rubr_tkclie_rivend2"); 
      if (rs.getObject("rubr_tkclie_rivend3") != null)  rubr_tkclie_rivend3 = rs.getString("rubr_tkclie_rivend3"); 
      if (rs.getObject("rubr_tkclie_rivend4") != null)  rubr_tkclie_rivend4 = rs.getString("rubr_tkclie_rivend4"); 
      if (rs.getObject("rubr_dtinse"       ) != null)  rubr_dtinse       = rs.getTimestamp("rubr_dtinse"   ); 
      if (rs.getObject("rubr_origine_dati") != null)  rubr_origine_dati = rs.getString("rubr_origine_dati"); 

    }
    
    pstm.close();
    pstm = null;

    //Recupero la prima e-amil del contatto
    l_query  = "";
    l_query  = "   select rrec.recapito                                \n";
    l_query += "     from pgmr.archrubrica_recapiti rrec               \n";
    l_query += "        , pgmr.archrubrica_recapiti_tipo rrtp          \n";
    l_query += "    where rrec.tkrubr_rec_tipo = rrtp.tkrubr_rec_tipo  \n";
    l_query += "      and rrtp.cdrubr_rec_tipo_m = 'EMAIL_LAV'         \n";
    l_query += "      and rrec.tkrubr = ?                              \n";
    l_query += " order by tkrubr_rec                                   \n";

    pstm = m_connection.prepareStatement(l_query);
    
    ind = 1;
    pstm.setLong  (ind++, tkrubr);
    
    rs = pstm.executeQuery();
    
    if (rs!=null && rs.next()){
      recapito_email_lav = "";

      if (rs.getObject("recapito") != null)  recapito_email_lav = rs.getString("recapito"); 

    }
    
    pstm.close();
    pstm = null;


    //Recupero nazione e provincia sede legale contatto
    String  rubr_cdnazi_sl    = "";
    String  rubr_cdprov_sl    = "";

    l_query  = "";
    l_query  = "   select rulo.cdnazi      as cdnazi_sl                 \n";
    l_query += "        , rulo.cdprov      as cdprov_sl                 \n";
    l_query += "     from pgmr.archrubrica_uloc rulo                    \n";
    l_query += "    where rulo.fseleg = 'S'                             \n";
    l_query += "      and rulo.tkrubr = "+ tkrubr +"                    \n";

    rs = sql_query(l_query);

    if (rs != null && rs.next()){

      rubr_cdnazi_sl    = "";
      rubr_cdprov_sl    = "";

      if (rs.getObject("cdnazi_sl")!= null) rubr_cdnazi_sl = rs.getString("cdnazi_sl");
      if (rs.getObject("cdprov_sl")!= null) rubr_cdprov_sl = rs.getString("cdprov_sl");
    }

    //Recupero la ragione sociale e titolo della persona di riferimento (ordino per prendere il primo token inserito)
    String  perrif    = "";
    String  perrif_cdtpsesso    = "";

    l_query  = "";
    l_query  = "   select rref.nome                         \n";
    l_query += "        , ptit.cdtpsesso                    \n";
    l_query += "     from pgmr.archrubrica_refe rref        \n";
    l_query += "          left outer join pgmr.pers_titolo ptit on rref.cdptit = ptit.cdptit       \n";
    l_query += "    where rref.tkrubr = "+ tkrubr +"        \n";
    l_query += " order by rref.tkrubr_refe                  \n";

    rs = sql_query(l_query);

    if (rs != null && rs.next()){

      perrif    = "";
      perrif_cdtpsesso    = "";

      if (rs.getObject("nome")!= null) perrif = rs.getString("nome");
      if (rs.getObject("cdtpsesso")!= null) perrif_cdtpsesso = rs.getString("cdtpsesso");
    }
    
    
    String url_resources = ep_site_ext_url + "download-area/download-2d-3d.html";
    String url_cataloghi = ep_site_ext_url;
    if (cdling.equals("R")){ //CABLATO -- russo
        url_cataloghi += "download-area/download-cataloghi/";
    } else if (cdling.equals("I") || cdling.equals("")){ //CABLATO -- italiano
        url_cataloghi += "download-area/download-cataloghi/";
    } else { //CABLATO -- italiano
        url_cataloghi += "download-area/catalog-download/";
    }    

    ao_map.put("${archrubrica_recapiti.recapito_email_lav}"    , recapito_email_lav          );
    ao_map.put("${archrubrica.email_aream}"                    , email_aream                 );
    ao_map.put("${archrubrica.email_respv}"                    , email_respv                 );
    ao_map.put("${archrubrica.ragcog}"                         , ragcog                      );
    ao_map.put("${archrubrica.perrif}"                         , perrif                      );
    ao_map.put("${archrubrica.tkrubr}"                         , ""+tkrubr                   );
    ao_map.put("${archrubrica.url_contator}"                   , ep_site_url + "Contator/Contator?url="+ ep_site_ext_url +"&tkmsg=@tkmsg@&tkrubr="+ tkrubr +"&email="+ recapito_email_lav  );
    ao_map.put("${archrubrica.url_contator_cataloghi}"         , ep_site_url + "Contator/Contator?url="+ url_cataloghi +"&tkmsg=@tkmsg@&tkrubr="+ tkrubr +"&email="+ recapito_email_lav  );
    ao_map.put("${archrubrica.url_contator_portal}"            , ep_site_url + "Contator/Contator?url="+ ep_portal_url +"&tkmsg=@tkmsg@&tkrubr="+ tkrubr +"&email="+ recapito_email_lav  );
    ao_map.put("${archrubrica.url_contator_resources}"         , ep_site_url + "Contator/Contator?url="+ url_resources +"&tkmsg=@tkmsg@&tkrubr="+ tkrubr +"&email="+ recapito_email_lav  );
    ao_map.put("${archrubrica.url_contator_rivenditori}"       , ep_site_url + "Contator/Contator?url="+ ep_site_url +"store_locator&tkmsg=@tkmsg@&tkrubr="+ tkrubr +"&email="+ recapito_email_lav  );
    ao_map.put("${archrubrica.url_contator_shop}"              , ep_site_url + "Contator/Contator?url="+ ep_shop_url +"&tkmsg=@tkmsg@&tkrubr="+ tkrubr +"&email="+ recapito_email_lav  );
    ao_map.put("${archrubrica.url_contator_app_iphone}"        , ep_site_url + "Contator/Contator?url=https://itunes.apple.com/it/app/lightmeapp-iphone-edition/id632752546&tkmsg=@tkmsg@&tkrubr="+ tkrubr +"&email="+ recapito_email_lav  );
    ao_map.put("${archrubrica.url_contator_app_ipad}"          , ep_site_url + "Contator/Contator?url=https://itunes.apple.com/it/app/lightmeapp/id614705340&tkmsg=@tkmsg@&tkrubr="+ tkrubr +"&email="+ recapito_email_lav  );
    ao_map.put("${archrubrica.url_contator_app_android}"       , ep_site_url + "Contator/Contator?url=https://play.google.com/store/apps/details?id=com.ateikon.Vistosi&tkmsg=@tkmsg@&tkrubr="+ tkrubr +"&email="+ recapito_email_lav  );
    ao_map.put("${archrubrica.url}"                            , ep_portal_url + "epDesktop.jsp?nextPage=rlogin/eprogen.html?nextPage=epRubr_edit.jsp?tkrubr="+ tkrubr +"&openMethod=iframe&nextPageTitle="+ html.url(dsrubr_tipo)                   );
    ao_map.put("${contator.url}"                               , ep_site_url + "Contator/Contator?url="+ ep_site_url_ori +"newsletter/img/null.gif&tkmsg=@tkmsg@&tkrubr="+ tkrubr +"&email="+ recapito_email_lav  );
    ao_map.put("${archrubrica.dtinse}"                         , atk_ctrl.getDate(rubr_dtinse));
    ao_map.put("${archrubrica.rivend1_email}"                  , archclie.of_getEmail(rubr_tkclie_rivend1, "", "")          );
    ao_map.put("${archrubrica.rivend1_email_tecn}"             , archclie.of_getEmail(rubr_tkclie_rivend1, "", COM_TEC)     );
    ao_map.put("${archrubrica.rivend1_email_comm}"             , archclie.of_getEmail(rubr_tkclie_rivend1, "", COM_COM)     );
    ao_map.put("${archrubrica.rivend1_email_amm}"              , archclie.of_getEmail(rubr_tkclie_rivend1, "", COM_AMM)     );
    ao_map.put("${archrubrica.rivend2_email}"                  , archclie.of_getEmail(rubr_tkclie_rivend2, "", "")          );
    ao_map.put("${archrubrica.rivend2_email_tecn}"             , archclie.of_getEmail(rubr_tkclie_rivend2, "", COM_TEC)     );
    ao_map.put("${archrubrica.rivend2_email_comm}"             , archclie.of_getEmail(rubr_tkclie_rivend2, "", COM_COM)     );
    ao_map.put("${archrubrica.rivend2_email_amm}"              , archclie.of_getEmail(rubr_tkclie_rivend2, "", COM_AMM)     );
    ao_map.put("${archrubrica.rivend3_email}"                  , archclie.of_getEmail(rubr_tkclie_rivend3, "", "")          );
    ao_map.put("${archrubrica.rivend3_email_tecn}"             , archclie.of_getEmail(rubr_tkclie_rivend3, "", COM_TEC)     );
    ao_map.put("${archrubrica.rivend3_email_comm}"             , archclie.of_getEmail(rubr_tkclie_rivend3, "", COM_COM)     );
    ao_map.put("${archrubrica.rivend3_email_amm}"              , archclie.of_getEmail(rubr_tkclie_rivend3, "", COM_AMM)     );
    ao_map.put("${archrubrica.rivend4_email}"                  , archclie.of_getEmail(rubr_tkclie_rivend4, "", "")          );
    ao_map.put("${archrubrica.rivend4_email_tecn}"             , archclie.of_getEmail(rubr_tkclie_rivend4, "", COM_TEC)     );
    ao_map.put("${archrubrica.rivend4_email_comm}"             , archclie.of_getEmail(rubr_tkclie_rivend4, "", COM_COM)     );
    ao_map.put("${archrubrica.rivend4_email_amm}"              , archclie.of_getEmail(rubr_tkclie_rivend4, "", COM_AMM)     );
    
    
    
    String ls_text_1        = atk_dwlingua.getLabel ("ep_ticket", cdling, "text_1"      , "", null);
    String ls_text_agen     = atk_dwlingua.getLabel ("ep_ticket", cdling, "text_agen"   , "", null);

    String ls_rubr_rr_cp_tbl_dati = "";
    
    ls_rubr_rr_cp_tbl_dati  = "";
    ls_rubr_rr_cp_tbl_dati  = "  <table width=\"709\" cellpadding=\"0\" cellspacing=\"0\" class=\"parametri\">                                                \n";
    ls_rubr_rr_cp_tbl_dati += "    <tr>                                                                                                                       \n";
    ls_rubr_rr_cp_tbl_dati += "      <th width=\"170\" align=\"left\" valign=\"top\" nowrap class=\"Stile1\"><b>"+ html.text(dsrubr_tipo) +":</b></th>        \n";
    ls_rubr_rr_cp_tbl_dati += "      <td valign=\"top\" class=\"Stile1\">"+ html.text(ragcog) +"</td>                                                         \n";
    ls_rubr_rr_cp_tbl_dati += "    </tr>                                                                                                                      \n";
    ls_rubr_rr_cp_tbl_dati += "    <tr>                                                                                                                       \n";
    ls_rubr_rr_cp_tbl_dati += "      <th width=\"170\" align=\"left\" valign=\"top\" nowrap class=\"Stile1\"><b>"+ html.text(ls_text_1) +":</b></th>          \n";
    ls_rubr_rr_cp_tbl_dati += "      <td valign=\"top\" class=\"Stile1\">"+ tkrubr +"</td>                                                                    \n";
    ls_rubr_rr_cp_tbl_dati += "    </tr>                                                                                                                      \n";
    if (!dsagen_reg.equals("")        ){
      ls_rubr_rr_cp_tbl_dati += " <tr>                                                                                                                          \n";
      ls_rubr_rr_cp_tbl_dati += "   <th width=\"170\" align=\"left\" valign=\"top\" nowrap class=\"Stile1\"><b>"+ html.text(ls_text_agen) +":</b></th>          \n";
      ls_rubr_rr_cp_tbl_dati += "   <td valign=\"top\" class=\"Stile1\">"+ html.text(dsagen_reg) +"</td>                                                        \n";
      ls_rubr_rr_cp_tbl_dati += " </tr>                                                                                                                         \n";
    }
    ls_rubr_rr_cp_tbl_dati += "  </table>                                                                                                                     \n";

    ao_map.put("${rubr_rr_cp.tbl_dati}"                        , ls_rubr_rr_cp_tbl_dati          );

    
    //Recupero lista rivenditori
    String ls_rubr_prosp_list_rivenditori = "";
    String rubr_tkclie_rivend_concatenati = "";
    
    rubr_tkclie_rivend_concatenati += (rubr_tkclie_rivend1.trim().equals("")? "" : "'"+ rubr_tkclie_rivend1+ "'");
    rubr_tkclie_rivend_concatenati += (rubr_tkclie_rivend2.trim().equals("")? "" : (rubr_tkclie_rivend_concatenati.equals("")? "" : ", ") + "'"+ rubr_tkclie_rivend2+ "'");
    rubr_tkclie_rivend_concatenati += (rubr_tkclie_rivend3.trim().equals("")? "" : (rubr_tkclie_rivend_concatenati.equals("")? "" : ", ") + "'"+ rubr_tkclie_rivend3+ "'");
    rubr_tkclie_rivend_concatenati += (rubr_tkclie_rivend4.trim().equals("")? "" : (rubr_tkclie_rivend_concatenati.equals("")? "" : ", ") + "'"+ rubr_tkclie_rivend4+ "'");
    
    if (!rubr_tkclie_rivend_concatenati.equals("")){

        l_query  = "";
        l_query += "       SELECT clie.tkclie                                                                                                                             \n";
        l_query += "            , clie.cdclie_m                                                                                                                           \n";
        l_query += "            , ente.ragcog                                                                                                                             \n";
        l_query += "            , uloc.indiri                                                                                                                             \n";
        l_query += "            , uloc.cap                                                                                                                                \n";
        l_query += "            , uloc.comune                                                                                                                             \n";
        l_query += "            , prov.cdprov_m                                                                                                                           \n";
        l_query += "            , nazi.dsnazi                                                                                                                             \n";
        if (is_oracle){
            throw new Exception("DB non supportato");
        } else if (is_sybase || is_postgresql) {
            l_query += "         FROM pgmr.archenti ente                                                                                                                      \n";
            l_query += "             , pgmr.archclie    clie                                                                                                \n";
            l_query += "             ,  pgmr.enteuniloc  eulo                                                                                                \n";
            l_query += "                left outer join  pgmr.unitalocali uloc on  eulo.cdunil = uloc.cdunil                                                                  \n";
            l_query += "                                 left outer join  pgmr.nazioni  nazi on  uloc.cdnazi = nazi.cdnazi                                                    \n";
            l_query += "                                 left outer join  pgmr.province  prov on  uloc.cdprov = prov.cdprov_m                                                 \n";
            l_query += "   	WHERE clie.cdente = ente.cdente                                                                                                                   \n";
            l_query += "   	  and ente.cdente = eulo.cdente                                                                                                                   \n";
            l_query += "   	  and clie.tkclie in ( "+ rubr_tkclie_rivend_concatenati +" )                                                                                       \n";
        }
        l_query += "     order by ente.ragcog	                                                                                                                            \n";

        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        while (rs!=null && rs.next()){

          String rivend_ragcog      = "";
          String rivend_indiri      = "";
          String rivend_cap      = "";
          String rivend_comune      = "";
          String rivend_cdprov_m      = "";
          String rivend_dsnazi      = "";

          if (rs.getObject("ragcog"   ) != null)  rivend_ragcog  = rs.getString("ragcog"   ); 
          if (rs.getObject("indiri"   ) != null)  rivend_indiri  = rs.getString("indiri"   ); 
          if (rs.getObject("cap"   ) != null)  rivend_cap  = rs.getString("cap"   ); 
          if (rs.getObject("comune"   ) != null)  rivend_comune  = rs.getString("comune"   ); 
          if (rs.getObject("cdprov_m"   ) != null)  rivend_cdprov_m  = rs.getString("cdprov_m"   ); 
          if (rs.getObject("dsnazi"   ) != null)  rivend_dsnazi  = rs.getString("dsnazi"   ); 

          String ls_indirizzo = "";
          ls_indirizzo += "<strong>"+ rivend_ragcog +"</strong>" 
                       + ", " + rivend_indiri 
                       + ", " + rivend_cap 
                       + ", " + rivend_comune + (!rivend_cdprov_m.equals("")? " ("+ rivend_cdprov_m +")" : "") 
                       + " - " + rivend_dsnazi; 

          ls_rubr_prosp_list_rivenditori += "<li>"+ ls_indirizzo + "</li>";
        }

        ls_rubr_prosp_list_rivenditori = (!ls_rubr_prosp_list_rivenditori.equals("")? "<ul>"+ ls_rubr_prosp_list_rivenditori + "</ul>" : "");

        pstm.close();
        pstm = null;
    
    } else {
        
        ls_rubr_prosp_list_rivenditori = "";
        
    } // FINE -- if (!rubr_tkclie_rivend_concatenati.equals(""))

    String text_lriv_pers = atk_dwlingua.getLabel ("epProspect_rubr_gestmail", cdling, "text_lriv_pers"      , "", null);
    String text_lriv_azie = atk_dwlingua.getLabel ("epProspect_rubr_gestmail", cdling, "text_lriv_azie"      , "", null);
    
    if (!ls_rubr_prosp_list_rivenditori.equals("")){
        ao_map.put("${rubr_prosp.list_rivenditori}"                        , ls_rubr_prosp_list_rivenditori          );
        ao_map.put("${rubr_prosp.list_rivenditori_pers}"                   , "<p>"+ text_lriv_pers +"</p>"+ ls_rubr_prosp_list_rivenditori          );
        ao_map.put("${rubr_prosp.list_rivenditori_azie}"                   , "<p>"+ text_lriv_azie +"</p>"+ ls_rubr_prosp_list_rivenditori          );
    } else {
        ao_map.put("${rubr_prosp.list_rivenditori}"                        , ""          );
        ao_map.put("${rubr_prosp.list_rivenditori_pers}"                   , ""          );
        ao_map.put("${rubr_prosp.list_rivenditori_azie}"                   , ""          );
    }
    
    
    
    String ls_rubr_prosp_text_1m        = atk_dwlingua.getLabel ("epProspect_rubr_gestmail", cdling, "Egr. sig."      , "", null);
    String ls_rubr_prosp_text_1f        = atk_dwlingua.getLabel ("epProspect_rubr_gestmail", cdling, "Egr. sig.ra"      , "", null);
    
    String ls_rubr_prosp_text_1 = "";
    
    if (perrif_cdtpsesso.equals("F")){
      ls_rubr_prosp_text_1 = ls_rubr_prosp_text_1f;
    } else {
      ls_rubr_prosp_text_1 = ls_rubr_prosp_text_1m;
    }
    
    ao_map.put("${rubr_prosp.text_1}"                        , ls_rubr_prosp_text_1          );
    

    ao_map = of_setPar_Archrubrica_tipo(ao_map, tkrubr_tipo, cdling);
    if (!tkclie.equals("")){
      ao_map = of_setPar_Nazioni(ao_map, "", cdling);
      ao_map = of_setPar_Archagen(ao_map, "", "", cdling);

      ao_map = of_setPar_Archclie(ao_map, tkclie, cdling);
    } else {  
      ao_map = of_setPar_Archclie(ao_map, "", cdling);

      ao_map = of_setPar_Nazioni(ao_map, rubr_cdnazi_sl, cdling);
      ao_map = of_setPar_Archagen(ao_map, rubr_cdsuba_inc, "", cdling);
      ao_map = of_setPar_Archagen(ao_map, rubr_cdagen_inc, IS_ARCHAGEN_LIVELLO_AGENTE_RIF, cdling);
      ao_map = of_setPar_Archagen(ao_map, rubr_cdcapo_inc, IS_ARCHAGEN_LIVELLO_CAPOAREA, cdling);
      ao_map = of_setPar_Age_ispettori(ao_map, rubr_cdispe_inc, cdling);
    }
    
    
    // Testo lista rappresentanti in notifica categorizzazione prospect
    
    String text_2_p1f_pers = atk_dwlingua.getLabel ("epProspect_rubr_gestmail", cdling, "text_2_p1f_pers"      , "", null);
    String text_2_p1m_pers = atk_dwlingua.getLabel ("epProspect_rubr_gestmail", cdling, "text_2_p1m_pers"      , "", null);
    String text_2_p1_pers = atk_dwlingua.getLabel ("epProspect_rubr_gestmail", cdling, "text_2_p1_pers"      , "", null);
    String text_2_p1f_azie = atk_dwlingua.getLabel ("epProspect_rubr_gestmail", cdling, "text_2_p1f_azie"      , "", null);
    String text_2_p1m_azie = atk_dwlingua.getLabel ("epProspect_rubr_gestmail", cdling, "text_2_p1m_azie"      , "", null);
    String text_2_p1_azie = atk_dwlingua.getLabel ("epProspect_rubr_gestmail", cdling, "text_2_p1_azie"      , "", null);
    String text_2_p2f = atk_dwlingua.getLabel ("epProspect_rubr_gestmail", cdling, "text_2_p2f"      , "", null);
    String text_2_p2m = atk_dwlingua.getLabel ("epProspect_rubr_gestmail", cdling, "text_2_p2m"      , "", null);
    String text_2_p2 = atk_dwlingua.getLabel ("epProspect_rubr_gestmail", cdling, "text_2_p2"      , "", null);
    String text_2_p3f = atk_dwlingua.getLabel ("epProspect_rubr_gestmail", cdling, "text_2_p3f"      , "", null);
    String text_2_p3m = atk_dwlingua.getLabel ("epProspect_rubr_gestmail", cdling, "text_2_p3m"      , "", null);
    String text_2_p3 = atk_dwlingua.getLabel ("epProspect_rubr_gestmail", cdling, "text_2_p3"      , "", null);
    String text_3_p1f_pers = atk_dwlingua.getLabel ("epProspect_rubr_gestmail", cdling, "text_3_p1f_pers"      , "", null);
    String text_3_p1m_pers = atk_dwlingua.getLabel ("epProspect_rubr_gestmail", cdling, "text_3_p1m_pers"      , "", null);
    String text_3_p1_pers = atk_dwlingua.getLabel ("epProspect_rubr_gestmail", cdling, "text_3_p1_pers"      , "", null);
    String text_3_p1f_azie = atk_dwlingua.getLabel ("epProspect_rubr_gestmail", cdling, "text_3_p1f_azie"      , "", null);
    String text_3_p1m_azie = atk_dwlingua.getLabel ("epProspect_rubr_gestmail", cdling, "text_3_p1m_azie"      , "", null);
    String text_3_p1_azie = atk_dwlingua.getLabel ("epProspect_rubr_gestmail", cdling, "text_3_p1_azie"      , "", null);
    String text_3_p2f = text_2_p3f;
    String text_3_p2m = text_2_p3m;
    String text_3_p2 = text_2_p3;
    
    String ls_agenrif_dsagen = (String) ao_map.get("${archagen.dsagen_agenrif}"); ls_agenrif_dsagen = (ls_agenrif_dsagen == null ? "" : ls_agenrif_dsagen);
    String ls_agenrif_email = (String) ao_map.get("${archagen.email_agenrif}"); ls_agenrif_email = (ls_agenrif_email == null ? "" : ls_agenrif_email);
    String ls_agenrif_cdtpsesso = (String) ao_map.get("${archagen.cdtpsesso_agenrif}"); ls_agenrif_cdtpsesso = (ls_agenrif_cdtpsesso == null ? "" : ls_agenrif_cdtpsesso);
    String ls_ispe_dsispe = (String) ao_map.get("${age_ispettori.dsispe}"); ls_ispe_dsispe = (ls_ispe_dsispe == null ? "" : ls_ispe_dsispe);
    String ls_ispe_email = (String) ao_map.get("${age_ispettori.email}"); ls_ispe_email = (ls_ispe_email == null ? "" : ls_ispe_email);
    String ls_ispe_cdtpsesso = (String) ao_map.get("${age_ispettori.cdtpsesso}"); ls_ispe_cdtpsesso = (ls_ispe_cdtpsesso == null ? "" : ls_ispe_cdtpsesso);
    String ls_resp_dsresp = (String) ao_map.get("${archresp.dsresp}"); ls_resp_dsresp = (ls_resp_dsresp == null ? "" : ls_resp_dsresp);
    String ls_resp_email = (String) ao_map.get("${archresp.email}"); ls_resp_email = (ls_resp_email == null ? "" : ls_resp_email);
    String ls_resp_cdtpsesso = (String) ao_map.get("${archresp.cdtpsesso}"); ls_resp_cdtpsesso = (ls_resp_cdtpsesso == null ? "" : ls_resp_cdtpsesso);
    
    String ls_rubr_prosp_text_2_pers = "";
    
    ls_rubr_prosp_text_2_pers  = "";
    ls_rubr_prosp_text_2_pers  = "<p>\n";
    if (ls_agenrif_cdtpsesso.equals(CDTPSESSO_FEMMINILE)){
        ls_rubr_prosp_text_2_pers += html.text(text_2_p1f_pers) + ls_agenrif_dsagen +" (<a href=\"mailto:"+ ls_agenrif_email +"\">"+ ls_agenrif_email +"</a>)";
    } else if (ls_agenrif_cdtpsesso.equals(CDTPSESSO_MASCHILE)){
        ls_rubr_prosp_text_2_pers += html.text(text_2_p1m_pers) + ls_agenrif_dsagen +" (<a href=\"mailto:"+ ls_agenrif_email +"\">"+ ls_agenrif_email +"</a>)";
    } else {
        ls_rubr_prosp_text_2_pers += html.text(text_2_p1_pers) + ls_agenrif_dsagen +" (<a href=\"mailto:"+ ls_agenrif_email +"\">"+ ls_agenrif_email +"</a>)";
    }
    if (ls_ispe_cdtpsesso.equals(CDTPSESSO_FEMMINILE)){
        ls_rubr_prosp_text_2_pers += html.text(text_2_p2f)+ ls_ispe_dsispe +" (<a href=\"mailto:"+ ls_ispe_email +"\">"+ ls_ispe_email +"</a>)";
    } else if (ls_ispe_cdtpsesso.equals(CDTPSESSO_MASCHILE)){
        ls_rubr_prosp_text_2_pers += html.text(text_2_p2m)+ ls_ispe_dsispe +" (<a href=\"mailto:"+ ls_ispe_email +"\">"+ ls_ispe_email +"</a>)";
    } else {
        ls_rubr_prosp_text_2_pers += html.text(text_2_p2)+ ls_ispe_dsispe +" (<a href=\"mailto:"+ ls_ispe_email +"\">"+ ls_ispe_email +"</a>)";
    }
    if (ls_resp_cdtpsesso.equals(CDTPSESSO_FEMMINILE)){
        ls_rubr_prosp_text_2_pers += html.text(text_2_p3f)+ ls_resp_dsresp +" (<a href=\"mailto:"+ ls_resp_email +"\">"+ ls_resp_email +"</a>).";
    } else if (ls_resp_cdtpsesso.equals(CDTPSESSO_MASCHILE)){
        ls_rubr_prosp_text_2_pers += html.text(text_2_p3m)+ ls_resp_dsresp +" (<a href=\"mailto:"+ ls_resp_email +"\">"+ ls_resp_email +"</a>).";
    } else {
        ls_rubr_prosp_text_2_pers += html.text(text_2_p3)+ ls_resp_dsresp +" (<a href=\"mailto:"+ ls_resp_email +"\">"+ ls_resp_email +"</a>).";
    }
    ls_rubr_prosp_text_2_pers += "</p>\n";
    
    ao_map.put("${rubr_prosp.text_2_pers}"                        , ls_rubr_prosp_text_2_pers          );
    
    String ls_rubr_prosp_text_2_azie = "";
    
    ls_rubr_prosp_text_2_azie  = "";
    ls_rubr_prosp_text_2_azie  = "<p>\n";
    if (ls_agenrif_cdtpsesso.equals(CDTPSESSO_FEMMINILE)){
        ls_rubr_prosp_text_2_azie += html.text(text_2_p1f_azie)+ ls_agenrif_dsagen +" (<a href=\"mailto:"+ ls_agenrif_email +"\">"+ ls_agenrif_email +"</a>)";
    } else if (ls_agenrif_cdtpsesso.equals(CDTPSESSO_MASCHILE)){
        ls_rubr_prosp_text_2_azie += html.text(text_2_p1m_azie)+ ls_agenrif_dsagen +" (<a href=\"mailto:"+ ls_agenrif_email +"\">"+ ls_agenrif_email +"</a>)";
    } else {
        ls_rubr_prosp_text_2_azie += html.text(text_2_p1_azie)+ ls_agenrif_dsagen +" (<a href=\"mailto:"+ ls_agenrif_email +"\">"+ ls_agenrif_email +"</a>)";
    }
    if (ls_ispe_cdtpsesso.equals(CDTPSESSO_FEMMINILE)){
        ls_rubr_prosp_text_2_azie += html.text(text_2_p2f)+ ls_ispe_dsispe +" (<a href=\"mailto:"+ ls_ispe_email +"\">"+ ls_ispe_email +"</a>)";
    } else if (ls_ispe_cdtpsesso.equals(CDTPSESSO_MASCHILE)){
        ls_rubr_prosp_text_2_azie += html.text(text_2_p2m)+ ls_ispe_dsispe +" (<a href=\"mailto:"+ ls_ispe_email +"\">"+ ls_ispe_email +"</a>)";
    } else {
        ls_rubr_prosp_text_2_azie += html.text(text_2_p2)+ ls_ispe_dsispe +" (<a href=\"mailto:"+ ls_ispe_email +"\">"+ ls_ispe_email +"</a>)";
    }
    if (ls_resp_cdtpsesso.equals(CDTPSESSO_FEMMINILE)){
        ls_rubr_prosp_text_2_azie += html.text(text_2_p3f)+ ls_resp_dsresp +" (<a href=\"mailto:"+ ls_resp_email +"\">"+ ls_resp_email +"</a>).";
    } else if (ls_resp_cdtpsesso.equals(CDTPSESSO_MASCHILE)){
        ls_rubr_prosp_text_2_azie += html.text(text_2_p3m)+ ls_resp_dsresp +" (<a href=\"mailto:"+ ls_resp_email +"\">"+ ls_resp_email +"</a>).";
    } else {
        ls_rubr_prosp_text_2_azie += html.text(text_2_p3)+ ls_resp_dsresp +" (<a href=\"mailto:"+ ls_resp_email +"\">"+ ls_resp_email +"</a>).";
    }
    ls_rubr_prosp_text_2_azie += "</p>\n";
    
    ao_map.put("${rubr_prosp.text_2_azie}"                        , ls_rubr_prosp_text_2_azie          );
    
    String ls_rubr_prosp_text_3_pers = "";
    
    ls_rubr_prosp_text_3_pers  = "";
    ls_rubr_prosp_text_3_pers  = "<p>\n";
    if (ls_ispe_cdtpsesso.equals(CDTPSESSO_FEMMINILE)){
        ls_rubr_prosp_text_3_pers += html.text(text_3_p1f_pers)+ ls_ispe_dsispe +" (<a href=\"mailto:"+ ls_ispe_email +"\">"+ ls_ispe_email +"</a>)";
    } else if (ls_ispe_cdtpsesso.equals(CDTPSESSO_MASCHILE)){
        ls_rubr_prosp_text_3_pers += html.text(text_3_p1m_pers)+ ls_ispe_dsispe +" (<a href=\"mailto:"+ ls_ispe_email +"\">"+ ls_ispe_email +"</a>)";
    } else {
        ls_rubr_prosp_text_3_pers += html.text(text_3_p1_pers)+ ls_ispe_dsispe +" (<a href=\"mailto:"+ ls_ispe_email +"\">"+ ls_ispe_email +"</a>)";
    }
    if (ls_resp_cdtpsesso.equals(CDTPSESSO_FEMMINILE)){
        ls_rubr_prosp_text_3_pers += html.text(text_3_p2f)+ ls_resp_dsresp +" (<a href=\"mailto:"+ ls_resp_email +"\">"+ ls_resp_email +"</a>).";
    } else if (ls_resp_cdtpsesso.equals(CDTPSESSO_MASCHILE)){
        ls_rubr_prosp_text_3_pers += html.text(text_3_p2m)+ ls_resp_dsresp +" (<a href=\"mailto:"+ ls_resp_email +"\">"+ ls_resp_email +"</a>).";
    } else {
        ls_rubr_prosp_text_3_pers += html.text(text_3_p2)+ ls_resp_dsresp +" (<a href=\"mailto:"+ ls_resp_email +"\">"+ ls_resp_email +"</a>).";
    }
    ls_rubr_prosp_text_3_pers += "</p>\n";
    
    ao_map.put("${rubr_prosp.text_3_pers}"                        , ls_rubr_prosp_text_3_pers          );
    
    String ls_rubr_prosp_text_3_azie = "";
    
    ls_rubr_prosp_text_3_azie  = "";
    ls_rubr_prosp_text_3_azie  = "<p>\n";
    if (ls_ispe_cdtpsesso.equals(CDTPSESSO_FEMMINILE)){
        ls_rubr_prosp_text_3_azie += html.text(text_3_p1f_azie)+ ls_ispe_dsispe +" (<a href=\"mailto:"+ ls_ispe_email +"\">"+ ls_ispe_email +"</a>)";
    } else if (ls_ispe_cdtpsesso.equals(CDTPSESSO_MASCHILE)){
        ls_rubr_prosp_text_3_azie += html.text(text_3_p1m_azie)+ ls_ispe_dsispe +" (<a href=\"mailto:"+ ls_ispe_email +"\">"+ ls_ispe_email +"</a>)";
    } else {
        ls_rubr_prosp_text_3_azie += html.text(text_3_p1_azie)+ ls_ispe_dsispe +" (<a href=\"mailto:"+ ls_ispe_email +"\">"+ ls_ispe_email +"</a>)";
    }
    if (ls_resp_cdtpsesso.equals(CDTPSESSO_FEMMINILE)){
        ls_rubr_prosp_text_3_azie += html.text(text_3_p2f)+ ls_resp_dsresp +" (<a href=\"mailto:"+ ls_resp_email +"\">"+ ls_resp_email +"</a>).";
    } else if (ls_resp_cdtpsesso.equals(CDTPSESSO_MASCHILE)){
        ls_rubr_prosp_text_3_azie += html.text(text_3_p2m)+ ls_resp_dsresp +" (<a href=\"mailto:"+ ls_resp_email +"\">"+ ls_resp_email +"</a>).";
    } else {
        ls_rubr_prosp_text_3_azie += html.text(text_3_p2)+ ls_resp_dsresp +" (<a href=\"mailto:"+ ls_resp_email +"\">"+ ls_resp_email +"</a>).";
    }
    ls_rubr_prosp_text_3_azie += "</p>\n";
    
    ao_map.put("${rubr_prosp.text_3_azie}"                        , ls_rubr_prosp_text_3_azie          );
    
    
    
    ao_map = of_setPar_Vist_oridati(ao_map, rubr_origine_dati, cdling);
    

    archrubrica_tipo.close();      
    atk_dwlingua.close();      
    f_utente.close();      
    ep_costanti.close();      
    archagen.close();      
    archclie.close();  
    
    return ao_map;
  }

  public HashMap of_setPar_Msg_comgen (HashMap ao_map, long tkcomgen, String cdling) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    if (ao_map ==null){
      ao_map = new HashMap();
    }
    
    Ep_costanti  ep_costanti = new Ep_costanti();
    Msg_tpcomgen  msg_tpcomgen = new Msg_tpcomgen();
    Crm_credential crm_credential = new Crm_credential();

    HTML html = new HTML();

    setProfilo((Atk_sql) ep_costanti);
    setProfilo((Atk_sql) msg_tpcomgen);
    setProfilo((Atk_sql) crm_credential);
    
    Str_crm_credential lstr_crm_credential = new Str_crm_credential();

    String ep_portal_url    = ep_costanti.getCostvalue("ep.portal_url");
    ep_portal_url = of_cambiaURLLingua(ep_portal_url, cdling);


    String dscomgen      = "";
    String dstpcomgen      = "";
    String notacomgen      = "";
    String tkclie        = "";
    long   tkticket      = 0;
    long   tkutente_clie      = 0;
    String cdarti        = "";


    String  l_query = "";
    
    l_query  = "";
    l_query  = "  select comg.dscomgen                                                                     \n";
    l_query += "       , "+ msg_tpcomgen.of_descr_lingua(cdling, "tpcomg", "dstpcomgen")+" as dstpcomgen   \n";
    l_query += "       , comg.notacomgen                                                                   \n";
    l_query += "       , comg.tkclie                                                                       \n";
    l_query += "       , comg.tkticket                                                                     \n";
    l_query += "       , (select in_eute.tkutente                                                          \n";
    l_query += "            from pgmr.ep_utente in_eute                                                    \n";
    l_query += "           where in_eute.fgabil = 'S'                                                      \n";
    l_query += "             and in_eute.tkclie = comg.tkclie) as tkutente_clie                            \n";
    l_query += "       , (select in_arti.cdarti                                                            \n";
    l_query += "            from pgmr.mrp_arch_articoli in_arti                                            \n";
    l_query += "           where in_arti.cdazie = comg.cdazie                                              \n";
    l_query += "             and in_arti.cdartm = comg.cdartm) as cdarti                                   \n";
    l_query += "    from pgmr.msg_comgen    comg                                                           \n";
    l_query += "       , pgmr.msg_tpcomgen  tpcomg                                                         \n";
    l_query += "   where comg.tkcomgen = ?                                                                 \n";
    l_query += "     and comg.tktpcomgen = tpcomg.tktpcomgen                                               \n";
    l_query += "     and comg.cdazie = '"+ cdazie +"'                                                      \n";

    pstm = m_connection.prepareStatement(l_query);
    
    ind = 1;
    pstm.setLong  (ind++, tkcomgen);
    
    rs = pstm.executeQuery();
    
    if (rs!=null && rs.next()){
      dscomgen   = "";
      dstpcomgen = "";
      notacomgen = "";
      tkclie     = "";
      tkticket      = 0;
      tkutente_clie      = 0;
      cdarti     = "";

      if (rs.getObject("dscomgen"  ) != null)  dscomgen   = rs.getString("dscomgen"  ); 
      if (rs.getObject("dstpcomgen") != null)  dstpcomgen = rs.getString("dstpcomgen"); 
      if (rs.getObject("notacomgen") != null)  notacomgen = rs.getString("notacomgen"); 
      if (rs.getObject("tkclie"    ) != null)  tkclie     = rs.getString("tkclie"    ); 
      if (rs.getObject("tkticket"  ) != null)  tkticket   = rs.getLong  ("tkticket"  ); 
      if (rs.getObject("tkutente_clie"  ) != null)  tkutente_clie   = rs.getLong  ("tkutente_clie"  ); 
      if (rs.getObject("cdarti"    ) != null)  cdarti     = rs.getString("cdarti"    ); 
    }
    
    pstm.close();
    pstm = null;

    String ls_arts_url = ep_portal_url + "epDesktop.jsp?nextPage=epRep_controllo_articoli_speciali.jsp?params=COMGEN~"+ tkcomgen +"&openMethod=iframe&nextPageTitle="+ dstpcomgen;
    String ls_recl_url = ep_portal_url + "epDesktop.jsp?nextPage=epRep_controllo_reclami.jsp?params=COMGEN~"+ tkcomgen +"&openMethod=iframe&nextPageTitle="+ dstpcomgen;
    String ls_resc_url = ep_portal_url + "epDesktop.jsp?nextPage=epRep_controllo_resi.jsp?params=COMGEN~"+ tkcomgen +"&openMethod=iframe&nextPageTitle="+ dstpcomgen;
    String ls_sugg_url = ep_portal_url + "epDesktop.jsp?nextPage=epRep_controllo_suggerimenti.jsp?params=COMGEN~"+ tkcomgen +"&openMethod=iframe&nextPageTitle="+ dstpcomgen;
    
    
    
    String ls_arts_url_keycode = ls_arts_url;
    String ls_recl_url_keycode = ls_recl_url;
    String ls_resc_url_keycode = ls_resc_url;
    String ls_sugg_url_keycode = ls_sugg_url;
    
    if (tkutente_clie > 0){
      
      lstr_crm_credential.tkutente = tkutente_clie;
      
      lstr_crm_credential.url = ls_arts_url;
      if (crm_credential.of_getUrlKeycode(lstr_crm_credential) == 1)      crm_credential.m_connection.commit();
      ls_arts_url_keycode = ep_portal_url + lstr_crm_credential.keycode_url;

      lstr_crm_credential.url = ls_recl_url;
      if (crm_credential.of_getUrlKeycode(lstr_crm_credential) == 1)      crm_credential.m_connection.commit();
      ls_recl_url_keycode = ep_portal_url + lstr_crm_credential.keycode_url;

      lstr_crm_credential.url = ls_resc_url;
      if (crm_credential.of_getUrlKeycode(lstr_crm_credential) == 1)      crm_credential.m_connection.commit();
      ls_resc_url_keycode = ep_portal_url + lstr_crm_credential.keycode_url;

      lstr_crm_credential.url = ls_sugg_url;
      if (crm_credential.of_getUrlKeycode(lstr_crm_credential) == 1)      crm_credential.m_connection.commit();
      ls_sugg_url_keycode = ep_portal_url + lstr_crm_credential.keycode_url;
    }

    
    ao_map.put("${msg_comgen.dscomgen}"          , dscomgen                             );
    ao_map.put("${msg_comgen.notacomgen}"        , notacomgen                           );
    ao_map.put("${arts.url}"                     , ls_arts_url                          );
    ao_map.put("${recl.url}"                     , ls_recl_url                          );
    ao_map.put("${resc.url}"                     , ls_resc_url                          );
    ao_map.put("${sugg.url}"                     , ls_sugg_url                          );
    ao_map.put("${arts.url_keycode}"             , ls_arts_url_keycode                  );
    ao_map.put("${recl.url_keycode}"             , ls_recl_url_keycode                  );
    ao_map.put("${resc.url_keycode}"             , ls_resc_url_keycode                  );
    ao_map.put("${sugg.url_keycode}"             , ls_sugg_url_keycode                  );
    
    ao_map = of_setPar_Mrp_arch_articoli(ao_map, cdarti, cdling);
    ao_map = of_setPar_Ep_ticket(ao_map, tkticket, cdling);
    ao_map = of_setPar_Archclie(ao_map, tkclie, cdling);

    ep_costanti.close();      
    msg_tpcomgen.close();      
    crm_credential.close();      
    
    return ao_map;
  }

  public HashMap of_setPar_Atk_contatti (HashMap ao_map, long tkcontatto, String cdling) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    if (ao_map ==null){
      ao_map = new HashMap();
    }

    HTML html = new HTML();


    String   cap          = "";
    String   citta        = "";
    String   cognome      = "";
    String   email        = "";
    String   fax          = "";
    String   indiri       = "";
    String   messaggio    = "";
    String   nome         = "";
    String   ragsoc       = "";
    String   telefono     = "";
    long     tkmotcont    = 0;
    String   dsmotcont    = "";
    String   cdnazi       = "";
    String   cdprov       = "";
    String   dsfile       = "";
    long     tkutente_rif = 0;
    long     tkvist_lib_sd    = 0;
    String   lang         = "";
    String   url_risorse  = "";
    String   dscontatto   = "";
    String   tiporisorsa  = "";
    String   nome_modello = "";
    String   cdvistelet   = "";
    String   motivo       = "";


    String  l_query = "";
    
    l_query  = "";
    l_query  = "  select cap                        \n";
    l_query += "       , citta                      \n";
    l_query += "       , cognome                    \n";
    l_query += "       , email                      \n";
    l_query += "       , fax                        \n";
    l_query += "       , indiri                     \n";
    l_query += "       , messaggio                  \n";
    l_query += "       , nome                       \n";
    l_query += "       , ragsoc                     \n";
    l_query += "       , telefono                   \n";
    l_query += "       , tkcontatto                 \n";
    l_query += "       , tkmotcont                  \n";
    l_query += "       , cdnazi                     \n";
    l_query += "       , provincia as cdprov        \n";
    l_query += "       , dsfile                     \n";
    l_query += "       , tkutente_rif               \n";
    l_query += "       , tkvist_lib_sd              \n";
    l_query += "       , lang                       \n";
    l_query += "       , url_risorse                \n";
    l_query += "       , dscontatto                 \n";
    l_query += "       , tiporisorsa                \n";
    l_query += "       , nome_modello               \n";
    l_query += "       , nome_modello               \n";
    l_query += "       , cdvistelet                 \n";
    l_query += "       , motivo                     \n";
    l_query += "    from pgmr.atk_contatti          \n";
    l_query += "   where tkcontatto = ?             \n";
    l_query += "     and cdazie = '"+ cdazie +"'    \n";

    pstm = m_connection.prepareStatement(l_query);
    
    ind = 1;
    pstm.setLong  (ind++, tkcontatto);
    
    rs = pstm.executeQuery();
    
    if (rs!=null && rs.next()){
      cap          = "";
      citta        = "";
      cognome      = "";
      email        = "";
      fax          = "";
      indiri       = "";
      messaggio    = "";
      nome         = "";
      ragsoc       = "";
      telefono     = "";
      tkmotcont    = 0;
      dsmotcont    = "";
      cdnazi       = "";
      cdprov       = "";
      dsfile       = "";
      tkutente_rif = 0;
      tkvist_lib_sd    = 0;
      lang         = "";
      url_risorse  = "";
      dscontatto   = "";
      tiporisorsa  = "";
      nome_modello = "";
      cdvistelet   = "";
      motivo       = "";
      
      if (rs.getObject("cap"         ) != null)  cap          = rs.getString("cap"         ); 
      if (rs.getObject("citta"       ) != null)  citta        = rs.getString("citta"       ); 
      if (rs.getObject("cognome"     ) != null)  cognome      = rs.getString("cognome"     ); 
      if (rs.getObject("email"       ) != null)  email        = rs.getString("email"       ); 
      if (rs.getObject("fax"         ) != null)  fax          = rs.getString("fax"         ); 
      if (rs.getObject("indiri"      ) != null)  indiri       = rs.getString("indiri"      ); 
      if (rs.getObject("messaggio"   ) != null)  messaggio    = rs.getString("messaggio"   ); 
      if (rs.getObject("nome"        ) != null)  nome         = rs.getString("nome"        ); 
      if (rs.getObject("ragsoc"      ) != null)  ragsoc       = rs.getString("ragsoc"      ); 
      if (rs.getObject("telefono"    ) != null)  telefono     = rs.getString("telefono"    ); 
      if (rs.getObject("tkmotcont"   ) != null)  tkmotcont    = rs.getLong  ("tkmotcont"   ); 
      if (rs.getObject("cdnazi"      ) != null)  cdnazi       = rs.getString("cdnazi"      ); 
      if (rs.getObject("cdprov"      ) != null)  cdprov       = rs.getString("cdprov"      ); 
      if (rs.getObject("dsfile"      ) != null)  dsfile       = rs.getString("dsfile"      ); 
      if (rs.getObject("tkutente_rif") != null)  tkutente_rif = rs.getLong  ("tkutente_rif"); 
      if (rs.getObject("tkvist_lib_sd"  ) != null)  tkvist_lib_sd   = rs.getLong  ("tkvist_lib_sd"  ); 
      if (rs.getObject("lang"        ) != null)  lang         = rs.getString("lang"        ); 
      if (rs.getObject("url_risorse" ) != null)  url_risorse  = rs.getString("url_risorse" ); 
      if (rs.getObject("dscontatto"  ) != null)  dscontatto   = rs.getString("dscontatto"  ); 
      if (rs.getObject("tiporisorsa" ) != null)  tiporisorsa  = rs.getString("tiporisorsa" ); 
      if (rs.getObject("nome_modello") != null)  nome_modello = rs.getString("nome_modello"); 
      if (rs.getObject("cdvistelet"  ) != null)  cdvistelet   = rs.getString("cdvistelet"  ); 
      if (rs.getObject("motivo"      ) != null)  motivo       = rs.getString("motivo"      ); 

      if (tkmotcont == 1){
        dsmotcont = "assistenza";
      } else {
        dsmotcont = "";
      }
      
      if (tkutente_rif > 0){
          String eute_email = sql_String("select email from pgmr.ep_utente where tkutente = "+ tkutente_rif);
          eute_email = eute_email.trim();
          
          if (!eute_email.equals("")){
              email = eute_email;
          }
      }
    }
    
    pstm.close();
    pstm = null;

    
    ao_map.put("${atk_contatti.cap}"              , cap                                 );
    ao_map.put("${atk_contatti.citta}"            , citta                               );
    ao_map.put("${atk_contatti.cognome}"          , cognome                             );
    ao_map.put("${atk_contatti.email}"            , email                               );
    ao_map.put("${atk_contatti.fax}"              , fax                                 );
    ao_map.put("${atk_contatti.indiri}"           , indiri                              );
    ao_map.put("${atk_contatti.messaggio}"        , messaggio                           );
    ao_map.put("${atk_contatti.nome}"             , nome                                );
    ao_map.put("${atk_contatti.ragsoc}"           , ragsoc                              );
    ao_map.put("${atk_contatti.telefono}"         , telefono                            );
    ao_map.put("${atk_contatti.tkcontatto}"       , ""+tkcontatto                       );
    ao_map.put("${atk_contatti.dsmotcont}"        , dsmotcont                           );
    ao_map.put("${atk_contatti.dsfile}"           , dsfile                              );
    ao_map.put("${atk_contatti.tkutente_rif}"     , ""+tkutente_rif                     );
    ao_map.put("${atk_contatti.tkvist_lib_sd}"    , ""+tkvist_lib_sd                    );
    ao_map.put("${atk_contatti.lang}"             , lang                                );
    ao_map.put("${atk_contatti.url_risorse}"      , url_risorse                         );
    ao_map.put("${atk_contatti.dscontatto}"       , dscontatto                          );
    ao_map.put("${atk_contatti.tiporisorsa}"      , tiporisorsa                         );
    ao_map.put("${atk_contatti.nome_modello}"     , nome_modello                        );
    ao_map.put("${atk_contatti.cdvistelet}"       , cdvistelet                          );
    ao_map.put("${atk_contatti.motivo}"           , motivo                              );

    ao_map = of_setPar_Nazioni(ao_map, cdnazi, cdling);
    ao_map = of_setPar_Province(ao_map, cdprov, cdling);
    
    return ao_map;
  }

  public HashMap of_setPar_Bolla_test (HashMap ao_map, long ao_token, String cdling) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    if (ao_map ==null){
      ao_map = new HashMap();
    }

    Bolla_test  bolla_test = new Bolla_test();
    F_bolla_test  f_bolla_test = new F_bolla_test();
    Atk_dwlingua  atk_dwlingua = new Atk_dwlingua();

    Atk_ctrl  atk_ctrl = new Atk_ctrl();

    HTML html = new HTML();

    setProfilo((Atk_sql) bolla_test);
    setProfilo((Atk_sql) f_bolla_test);
    setProfilo((Atk_sql) atk_dwlingua);
    


    long tkboll = 0;
    long tkfatt = 0;

    String l_query  = "";

    l_query  = "";
    l_query  = "    select count(*)                                                  \n";
    l_query += "      from pgmr.bolla_test bolt                                      \n";
    l_query += "     where bolt.tkfatt = bolt.tkboll                                 \n";
    l_query += "       and bolt.tkfatt = "+ ao_token +"                              \n";

    int ii_fatt = sql_int(l_query);

    if (ii_fatt == 1){
      tkfatt = ao_token;
    } else {
      tkboll = ao_token;
    }


    String      url_spedizione = "";
    String      url_spedizione_DROPSHIP = "";
    Timestamp   dtboll = null;
    String      tkclie = "";

    if (tkboll > 0){

      String ls_cdvett1  = "";
      String ls_cdnazi   = "";
      long   ll_nrboll   = 0;

      rs = bolla_test.getKey(tkboll);

      if (rs!= null && rs.next()){
        dtboll = null;
        ls_cdvett1  = "";
        ls_cdnazi   = "";
        ll_nrboll   = 0;

        if (rs.getObject("dtboll") != null)  dtboll = rs.getTimestamp("dtboll"); 
        if (rs.getObject("tkclie") != null)  tkclie = rs.getString("tkclie");

        if (rs.getObject("cdvett1"  ) != null)  ls_cdvett1  = rs.getString("cdvett1");
        if (rs.getObject("cdnazi_sl") != null)  ls_cdnazi   = rs.getString("cdnazi_sl");
        if (rs.getObject("nrboll"   ) != null)  ll_nrboll   = rs.getLong  ("nrboll");

      }

      url_spedizione          = f_bolla_test.getLink_spedizione(ls_cdvett1, s_cdling, ls_cdnazi, ""+ll_nrboll);
      url_spedizione_DROPSHIP = f_bolla_test.getLink_spedizione(ls_cdvett1, s_cdling, ls_cdnazi, ""+ll_nrboll, "DROPSHIP");
      
    } else if (tkfatt > 0){

      String ls_cdvett1  = "";
      String ls_cdnazi   = "";
      long   ll_conpro   = 0;

      rs = bolla_test.getKey_fatt(tkfatt);

      if (rs!= null && rs.next()){
        dtboll = null;
        ls_cdvett1  = "";
        ls_cdnazi   = "";
        ll_conpro   = 0;

        if (rs.getObject("dtboll") != null)  dtboll = rs.getTimestamp("dtboll"); 
        if (rs.getObject("tkclie") != null)  tkclie = rs.getString("tkclie");

        if (rs.getObject("cdvett1"  ) != null)  ls_cdvett1  = rs.getString("cdvett1");
        if (rs.getObject("cdnazi_sl") != null)  ls_cdnazi   = rs.getString("cdnazi_sl");
        if (rs.getObject("conpro"   ) != null)  ll_conpro   = rs.getLong  ("conpro");
      }

      url_spedizione          = f_bolla_test.getLink_spedizione(ls_cdvett1, s_cdling, ls_cdnazi, ""+ll_conpro);
      url_spedizione_DROPSHIP = f_bolla_test.getLink_spedizione(ls_cdvett1, s_cdling, ls_cdnazi, ""+ll_conpro, "DROPSHIP");
    }

    

    String idTracking = f_bolla_test.getIDTracking(ao_token);
    
    ao_map.put("${bolla_test.dtboll}"                        , atk_ctrl.getDate(dtboll)                              );
    ao_map.put("${bolla_test.url_spedizione}"                , url_spedizione                                        );
    ao_map.put("${bolla_test.url_spedizione_DROPSHIP}"       , url_spedizione_DROPSHIP                               );
    ao_map.put("${bolla_test.id_tracking}"                   , idTracking                                            );

    String ls_text_link3          = atk_dwlingua.getLabel ("srv_vendita", cdling, "text_link3"       , "", null);
      
    String text_link_spedizione  = "";

    if (!text_link_spedizione.equals("")) text_link_spedizione += "<br />";
    if (!url_spedizione.equals("")) text_link_spedizione  += atk_dwlingua.getLabel ("srv_vendita", cdling, "sped_boll_text_2_1" , "", new String[] {"<a href=\""+ url_spedizione +"\">"+ ls_text_link3 +"</a>"});
    if (!text_link_spedizione.equals("")) text_link_spedizione += "<br />";
    if (!url_spedizione_DROPSHIP.equals("")) text_link_spedizione  += atk_dwlingua.getLabel ("srv_vendita", cdling, "sped_boll_text_2_2" , "", new String[] {"<a href=\""+ url_spedizione_DROPSHIP +"\">"+ ls_text_link3 +"</a>"});

    ao_map.put("${ov_avvsped.text_link_spedizione}"          , text_link_spedizione                                 );

    String text_id_tracking  = "";
    
    if (!idTracking.isEmpty()){
        text_id_tracking  = atk_dwlingua.getLabel ("srv_vendita", cdling, "text_id_tracking"       , "", new String[] {"<b>"+ idTracking +"</b>"});
    }

    ao_map.put("${ov_avvsped.text_id_tracking}"              , text_id_tracking                                 );

    ao_map = of_setPar_Archclie(ao_map, tkclie, cdling);

    bolla_test.close();      
    f_bolla_test.close();      
    atk_dwlingua.close();      
    
    return ao_map;
  }

  public HashMap of_setPar_Ep_utente (HashMap ao_map, long tkutente, String cdling) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    if (ao_map ==null){
      ao_map = new HashMap();
    }

    F_utente f_utente = new F_utente();
    Atk_dwlingua atk_dwlingua = new Atk_dwlingua();
    Archclie archclie = new Archclie();
    Ep_costanti  ep_costanti = new Ep_costanti();
    Crm_credential crm_credential = new Crm_credential();

    Atk_ctrl atk_ctrl = new Atk_ctrl();

    HTML html = new HTML();

    setProfilo((Atk_sql) f_utente);
    setProfilo((Atk_sql) atk_dwlingua);
    setProfilo((Atk_sql) archclie);
    setProfilo((Atk_sql) ep_costanti);
    setProfilo((Atk_sql) crm_credential);
    
    Str_crm_credential lstr_crm_credential = new Str_crm_credential();
    String descrDest = "";

    String ep_portal_url    = ep_costanti.getCostvalue("ep.portal_url");
    ep_portal_url = of_cambiaURLLingua(ep_portal_url, cdling);

  
    String    dsutente       = "";
    Timestamp dtsendpwd      = null;
    String    email_amm      = f_utente.getEmailTiposervizio(tkutente, COM_AMM);
    String    email_comm     = f_utente.getEmailTiposervizio(tkutente, COM_COM);
    String    email_tecn     = f_utente.getEmailTiposervizio(tkutente, COM_TEC);
    String    email_no_acc   = f_utente.getEmailFunzione(tkutente, NOTIF_NACC);
    String    email          = "";
    String    FTP_password   = "";
    String    FTP_user_name  = "";
    String    password       = "";
    String    user_name      = "";
    String    ftp_fgabil     = "";
    String    fgadmin        = "";
    String    cdutente       = "";
    String    cdresp         = "";
    String    cdispe         = "";
    String    cdagen         = "";
    String    tkclie         = "";
    long      tksubutente    = 0;
    String    clie_ragcog    = "";
    String    dsptit         = "";


    String  l_query = "";
    
    l_query  = "";
    l_query  = "  select eute.dsutente                                                      \n";
    l_query += "       , eute.dtsendpwd                                                     \n";
    l_query += "       , eute.email                                                         \n";
    l_query += "       , eute.FTP_password                                                  \n";
    l_query += "       , eute.FTP_user_name                                                 \n";
    l_query += "       , eute.password                                                      \n";
    l_query += "       , eute.user_name                                                     \n";
    l_query += "       , eute.ftp_fgabil                                                    \n";
    l_query += "       , eute.fgadmin                                                       \n";
    l_query += "       , eute.cdutente                                                      \n";
    l_query += "       , eute.cdresp                                                        \n";
    l_query += "       , eute.cdispe                                                        \n";
    l_query += "       , eute.cdagen                                                        \n";
    l_query += "       , eute.tkclie                                                        \n";
    l_query += "       , eute.tksubutente                                                   \n";
    l_query += "       , ( select in_ente.ragcog                                            \n";
    l_query += "             from pgmr.archenti in_ente                                     \n";
    l_query += "                , pgmr.archclie in_clie                                     \n";
    l_query += "            where in_clie.cdente = in_ente.cdente                           \n";
    l_query += "              and in_clie.tkclie = eute.tkclie    ) as clie_ragcog          \n";
    l_query += "       , "+ of_descr_lingua(cdling, "ptit", "dsptit") +" as dsptit          \n";
    l_query += "    from pgmr.ep_utente eute                                                \n";
    l_query += "         left outer join pgmr.pers_titolo ptit on eute.cdptit = ptit.cdptit \n";
    l_query += "   where eute.tkutente = ?                                                  \n";
    l_query += "     and eute.cdazie   = '"+ cdazie +"'                                     \n";

    pstm = m_connection.prepareStatement(l_query);
    
    ind = 1;
    pstm.setLong  (ind++, tkutente);
    
    rs = pstm.executeQuery();
    
    if (rs!=null && rs.next()){
      dsutente       = "";
      dtsendpwd      = null;
      email          = "";
      FTP_password   = "";
      FTP_user_name  = "";
      password       = "";
      user_name      = "";
      ftp_fgabil     = "";
      tkclie         = "";
      clie_ragcog    = "";
      fgadmin        = "";
      cdutente       = "";
      cdresp         = "";
      cdispe         = "";
      cdagen         = "";
      tkclie         = "";
      tksubutente    = 0;
      dsptit         = "";
      
      if (rs.getObject("dsutente"      ) != null)   dsutente      = rs.getString   ("dsutente"      ); 
      if (rs.getObject("dtsendpwd"     ) != null)   dtsendpwd     = rs.getTimestamp("dtsendpwd"     ); 
      if (rs.getObject("email"         ) != null)   email         = rs.getString   ("email"         ); 
      if (rs.getObject("FTP_password"  ) != null)   FTP_password  = rs.getString   ("FTP_password"  ); 
      if (rs.getObject("FTP_user_name" ) != null)   FTP_user_name = rs.getString   ("FTP_user_name" ); 
      if (rs.getObject("password"      ) != null)   password      = rs.getString   ("password"      ); 
      if (rs.getObject("user_name"     ) != null)   user_name     = rs.getString   ("user_name"     ); 
      if (rs.getObject("ftp_fgabil"    ) != null)   ftp_fgabil    = rs.getString   ("ftp_fgabil"    ); 
      if (rs.getObject("tkclie"        ) != null)   tkclie        = rs.getString   ("tkclie"        ); 
      if (rs.getObject("tksubutente"   ) != null)   tksubutente   = rs.getLong     ("tksubutente"   ); 
      if (rs.getObject("clie_ragcog"   ) != null)   clie_ragcog   = rs.getString   ("clie_ragcog"   ); 
      if (rs.getObject("fgadmin"       ) != null)   fgadmin       = rs.getString   ("fgadmin"       ); 
      if (rs.getObject("cdutente"      ) != null)   cdutente      = rs.getString   ("cdutente"      ); 
      if (rs.getObject("cdresp"        ) != null)   cdresp        = rs.getString   ("cdresp"        ); 
      if (rs.getObject("cdispe"        ) != null)   cdispe        = rs.getString   ("cdispe"        ); 
      if (rs.getObject("cdagen"        ) != null)   cdagen        = rs.getString   ("cdagen"        ); 
      if (rs.getObject("tkclie"        ) != null)   tkclie        = rs.getString   ("tkclie"        ); 
      if (rs.getObject("dsptit"        ) != null)   dsptit        = rs.getString   ("dsptit"        ); 
      
      
      if (dsptit.equals("")) dsptit = sql_String("select "+ of_descr_lingua(cdling, "", "dsptit") +" from pgmr.pers_titolo where cdptit = '"+ CDPTIT_DEF + "'");
    
      //In tutti i casi al 20130520 il dsptit va bene che sia sempre minuscolo
      dsptit = dsptit.toLowerCase();
    
      password = atk_ctrl.decode(password);
      FTP_password = atk_ctrl.decode(FTP_password).toLowerCase();
      
    }
    
    pstm.close();
    pstm = null;

    String ls_text_user_name = atk_dwlingua.getLabel ("mail_credenziali", cdling, "text_user_name", "", null, "PORTAL");
    String ls_text_password  = atk_dwlingua.getLabel ("mail_credenziali", cdling, "text_password" , "", null, "PORTAL");
    String ls_text_user_name_FTP = atk_dwlingua.getLabel ("mail_credenziali", cdling, "text_user_name_FTP", "", null, "PORTAL");
    String ls_text_password_FTP  = atk_dwlingua.getLabel ("mail_credenziali", cdling, "text_password_FTP" , "", null, "PORTAL");
    
    String ls_utente_gest  = atk_dwlingua.getLabel ("mail_generica", cdling, "tputente.utente_gest" , "", null, "EPROGEN");
    String ls_respv  = atk_dwlingua.getLabel ("mail_generica", cdling, "tputente.respv" , "", null, "EPROGEN");
    String ls_aream  = atk_dwlingua.getLabel ("mail_generica", cdling, "tputente.aream" , "", null, "EPROGEN");
    String ls_clie  = atk_dwlingua.getLabel ("mail_generica", cdling, "tputente.clie" , "", null, "EPROGEN");
    String ls_subcliente  = atk_dwlingua.getLabel ("mail_generica", cdling, "tputente.subcliente" , "", null, "EPROGEN");
    String ls_capoarea  = atk_dwlingua.getLabel ("mail_generica", cdling, "tputente.capoarea" , "", null, "EPROGEN");
    String ls_agente = atk_dwlingua.getLabel ("mail_generica", cdling, "tputente.agente" , "", null, "EPROGEN");
    String ls_subagente = atk_dwlingua.getLabel ("mail_generica", cdling, "tputente.subagente" , "", null, "EPROGEN");
    
    String ls_cred_tbl_cred  = "";

    ls_cred_tbl_cred  = "";
    ls_cred_tbl_cred += " <table width=\"709\" cellpadding=\"0\" cellspacing=\"0\" class=\"parametri\">                                                   \n";
    ls_cred_tbl_cred += "   <tr>                                                                                                                          \n";
    ls_cred_tbl_cred += "     <th width=\"130\" align=\"left\" valign=\"top\" nowrap class=\"Stile1\"><b>"+ html.text(ls_text_user_name) +":</b></th>     \n";
    ls_cred_tbl_cred += "     <td valign=\"top\" class=\"Stile1\">"+ html.text(user_name) +"</td>                                                         \n";
    ls_cred_tbl_cred += "   </tr>                                                                                                                         \n";
    ls_cred_tbl_cred += "   <tr>                                                                                                                          \n";
    ls_cred_tbl_cred += "     <th width=\"130\" align=\"left\" valign=\"top\" nowrap class=\"Stile1\"><b>"+ html.text(ls_text_password) +":</b></th>      \n";
    ls_cred_tbl_cred += "     <td valign=\"top\" class=\"Stile1\">"+ html.text(password) +"</td>                                                          \n";
    ls_cred_tbl_cred += "   </tr>                                                                                                                         \n";
    if (ftp_fgabil.equals("S")){
      ls_cred_tbl_cred += " <tr>                                                                                                                            \n";
      ls_cred_tbl_cred += "   <th width=\"130\" align=\"left\" valign=\"top\" nowrap class=\"Stile1\"><b>"+ html.text(ls_text_user_name_FTP) +":</b></th>   \n";
      ls_cred_tbl_cred += "   <td valign=\"top\" class=\"Stile1\">"+ html.text(FTP_user_name) +"</td>                                                       \n";
      ls_cred_tbl_cred += " </tr>                                                                                                                           \n";
      ls_cred_tbl_cred += " <tr>                                                                                                                            \n";
      ls_cred_tbl_cred += "   <th width=\"130\" align=\"left\" valign=\"top\" nowrap class=\"Stile1\"><b>"+ html.text(ls_text_password_FTP) +":</b></th>    \n";
      ls_cred_tbl_cred += "   <td valign=\"top\" class=\"Stile1\">"+ html.text(FTP_password) +"</td>                                                        \n";
      ls_cred_tbl_cred += " </tr>                                                                                                                           \n";
    }
    ls_cred_tbl_cred += " </table>                                                                                                                        \n";

    
    String ls_cred_tbl2_cred  = "";

    ls_cred_tbl2_cred  = "";
    ls_cred_tbl2_cred += " <table cellpadding=\"0\" cellspacing=\"0\" class=\"parametri2\">                                                                \n";
    ls_cred_tbl2_cred += "   <tr>                                                                                                                          \n";
    ls_cred_tbl2_cred += "     <th align=\"left\" valign=\"top\" nowrap>"+ html.text(ls_text_user_name) +":</th>                                           \n";
    ls_cred_tbl2_cred += "     <td style=\"padding-left:10px;\" align=\"left\" valign=\"top\" nowrap><b>"+ html.text(user_name) +"</b></td>                \n";
    ls_cred_tbl2_cred += "   </tr>                                                                                                                         \n";
    ls_cred_tbl2_cred += "   <tr>                                                                                                                          \n";
    ls_cred_tbl2_cred += "     <th align=\"left\" valign=\"top\" nowrap>"+ html.text(ls_text_password) +":</th>                                            \n";
    ls_cred_tbl2_cred += "     <td style=\"padding-left:10px;\" align=\"left\" valign=\"top\" nowrap><b>"+ html.text(password) +"</b></td>                 \n";
    ls_cred_tbl2_cred += "   </tr>                                                                                                                         \n";
    ls_cred_tbl2_cred += " </table>                                                                                                                        \n";

    //Gestire caso se utente cliente
    if (!tkclie.equals("")){
      email_amm = archclie.of_getEmail(tkclie, "", COM_AMM);
      email_comm = archclie.of_getEmail(tkclie, "", COM_COM);
      email_tecn = archclie.of_getEmail(tkclie, "", COM_TEC);
      email_no_acc = archclie.of_getEmail(tkclie, NOTIF_NACC, "");
      email = archclie.of_getEmail(tkclie, "", "");
      dsutente = clie_ragcog;
    }
    
    String ls_url_portal_keycode = "";
    
    if (tkutente > 0){
    
      lstr_crm_credential.tkutente = tkutente;

      lstr_crm_credential.url = ep_portal_url;
      if (crm_credential.of_getUrlKeycode(lstr_crm_credential) == 1)      crm_credential.m_connection.commit();
      ls_url_portal_keycode = ep_portal_url + lstr_crm_credential.keycode_url;
    }
    
    
    ao_map.put("${cred.tbl_cred}"                        , ls_cred_tbl_cred               );
    ao_map.put("${cred.tbl2_cred}"                       , ls_cred_tbl2_cred              );
    ao_map.put("${ep_utente.dsutente}"                   , dsutente                       );
    ao_map.put("${ep_utente.dtsendpwd}"                  , atk_ctrl.getDate(dtsendpwd)    );
    ao_map.put("${ep_utente.email_amm}"                  , email_amm                      );
    ao_map.put("${ep_utente.email_comm}"                 , email_comm                     );
    ao_map.put("${ep_utente.email_tecn}"                 , email_tecn                     );
    ao_map.put("${ep_utente.email_no_acc}"               , email_no_acc                   );
    ao_map.put("${ep_utente.email}"                      , email                          );
    ao_map.put("${ep_utente.FTP_password}"               , FTP_password                   );
    ao_map.put("${ep_utente.FTP_user_name}"              , FTP_user_name                  );
    ao_map.put("${ep_utente.password}"                   , password                       );
    ao_map.put("${ep_utente.user_name}"                  , user_name                      );
    ao_map.put("${ep_utente.dsptit}"                     , dsptit                         );
    if (!tkclie.equals("")){
      ao_map.put("${keycode.url_portal}"                   , ls_url_portal_keycode          );
    } else {
      ao_map.put("${keycode.url_portal}"                   , ep_portal_url                  );
    }

    if (fgadmin.equals("S")){

      ao_map = of_setPar_Archresp(ao_map, "", cdling);
      ao_map = of_setPar_Age_ispettori(ao_map, "", cdling);
      ao_map = of_setPar_Archagen(ao_map, "", "", cdling);
      ao_map = of_setPar_Archclie(ao_map, "", cdling);
      ao_map = of_setPar_Ep_subutente(ao_map, 0, cdling);
      
    } else if (!cdutente.equals("")){

      if (!cdresp.equals("")){

        ao_map = of_setPar_Age_ispettori(ao_map, "", cdling);
        ao_map = of_setPar_Archagen(ao_map, "", "", cdling);
        ao_map = of_setPar_Archclie(ao_map, "", cdling);
        ao_map = of_setPar_Ep_subutente(ao_map, 0, cdling);
        ao_map = of_setPar_Archresp(ao_map, cdresp, cdling);
        descrDest = ls_respv;
        
      } else {

        ao_map = of_setPar_Archresp(ao_map, "", cdling);
        ao_map = of_setPar_Age_ispettori(ao_map, "", cdling);
        ao_map = of_setPar_Archagen(ao_map, "", "", cdling);
        ao_map = of_setPar_Archclie(ao_map, "", cdling);
        ao_map = of_setPar_Ep_subutente(ao_map, 0, cdling);
        descrDest = ls_utente_gest;
      }

    } else if (!cdispe.equals("")){

      ao_map = of_setPar_Archresp(ao_map, "", cdling);
      ao_map = of_setPar_Archagen(ao_map, "", "", cdling);
      ao_map = of_setPar_Archclie(ao_map, "", cdling);
      ao_map = of_setPar_Ep_subutente(ao_map, 0, cdling);
      ao_map = of_setPar_Age_ispettori(ao_map, cdispe, cdling);
      descrDest = ls_aream;

    } else if (!cdagen.equals("")){

      ao_map = of_setPar_Archresp(ao_map, "", cdling);
      ao_map = of_setPar_Age_ispettori(ao_map, "", cdling);
      ao_map = of_setPar_Archclie(ao_map, "", cdling);
      ao_map = of_setPar_Ep_subutente(ao_map, 0, cdling);
      ao_map = of_setPar_Archagen(ao_map, cdagen, "", cdling);
      
      String arch_cdagen = "";
      String arch_cdcapo = "";
      String arch_cdage = "";
      
      l_query = "";
      l_query += " select cdagen                              \n";
      l_query += "      , cdcapo                              \n";
      l_query += "      , cdage                               \n";
      l_query += "   from pgmr.archagen                       \n";
      l_query += "  where cdagen   = '" + cdagen + "'         \n";
      
      pstm = m_connection.prepareStatement(l_query);

      rs = pstm.executeQuery();
    
      while (rs != null && rs.next()) {
          if (rs.getObject("cdagen") != null)             arch_cdagen = rs.getString("cdagen");
          if (rs.getObject("cdcapo") != null)             arch_cdcapo = rs.getString("cdcapo");
          if (rs.getObject("cdage") != null)              arch_cdage = rs.getString("cdage");
      }
      
      if(arch_cdagen.equals(arch_cdcapo)) {
          descrDest = ls_capoarea;
      } else if (!"".equals(arch_cdage)) {
          descrDest = ls_subagente;
      } else {
          descrDest = ls_agente;
      }
      
      pstm.close();
      pstm = null;
      
    } else if (!tkclie.equals("")){

      ao_map = of_setPar_Archresp(ao_map, "", cdling);
      ao_map = of_setPar_Age_ispettori(ao_map, "", cdling);
      ao_map = of_setPar_Archagen(ao_map, "", "", cdling);
      ao_map = of_setPar_Ep_subutente(ao_map, 0, cdling);
      ao_map = of_setPar_Archclie(ao_map, tkclie, cdling);
      descrDest = ls_clie;
    } else  if (tksubutente > 0) {

      ao_map = of_setPar_Archresp(ao_map, "", cdling);
      ao_map = of_setPar_Age_ispettori(ao_map, "", cdling);
      ao_map = of_setPar_Archagen(ao_map, "", "", cdling);
      ao_map = of_setPar_Archclie(ao_map, "", cdling);
      ao_map = of_setPar_Ep_subutente(ao_map, tksubutente, cdling);
      descrDest = ls_subcliente;
    } else {
      ao_map = of_setPar_Archresp(ao_map, "", cdling);
      ao_map = of_setPar_Age_ispettori(ao_map, "", cdling);
      ao_map = of_setPar_Archagen(ao_map, "", "", cdling);
      ao_map = of_setPar_Archclie(ao_map, "", cdling);
      ao_map = of_setPar_Ep_subutente(ao_map, 0, cdling);
      descrDest = "";
    }
      
    ao_map.put("${ep_utente.tipologia_utente}"      , descrDest                      );

    f_utente.close();
    atk_dwlingua.close();
    archclie.close();
    ep_costanti.close();      
    crm_credential.close();      
    
    return ao_map;
  }

  public HashMap of_setPar_Ep_ticket (HashMap ao_map, long tkticket, String cdling) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    if (ao_map ==null){
      ao_map = new HashMap();
    }

    HTML html = new HTML();


    String   nota            = "";
    String   lang_nota       = "";
    String   dsutente_ins    = "";


    String  l_query = "";
    
    l_query  = "";
    l_query  = "  select ticket.nota                                                                  \n";
    l_query += "       , "+ of_descr_lingua("ep_ticket", cdling, "ticket", "nota") +" as lang_nota                 \n";
    l_query += "       , ( select in_eute.dsutente                                                    \n";
    l_query += "             from pgmr.ep_utente in_eute                                              \n";
    l_query += "            where in_eute.tkutente = ticket.tkutente_inse ) as dsutente_ins           \n";
    l_query += "    from pgmr.ep_ticket ticket                                                        \n";
    l_query += "   where ticket.tkticket = ?                                                          \n";
    l_query += "     and ticket.cdazie = '"+ cdazie +"'                                               \n";

    pstm = m_connection.prepareStatement(l_query);
    
    ind = 1;
    pstm.setLong  (ind++, tkticket);
    
    rs = pstm.executeQuery();
    
    if (rs!=null && rs.next()){
      nota            = "";
      lang_nota       = "";
      dsutente_ins    = "";
      
      if (rs.getObject("nota"           ) != null)  nota            = rs.getString("nota"           ); 
      if (rs.getObject("lang_nota"      ) != null)  lang_nota       = rs.getString("lang_nota"      ); 
      if (rs.getObject("dsutente_ins"   ) != null)  dsutente_ins    = rs.getString("dsutente_ins"   ); 
      
      lang_nota = lang_nota.trim();
      
      if (!lang_nota.equals("")) nota = lang_nota;
    }
    
    pstm.close();
    pstm = null;

    
    ao_map.put("${ep_ticket.nota}"               , nota                              );
    ao_map.put("${ep_ticket.tkticket}"           , ""+tkticket                       );
    ao_map.put("${ep_ticket.dsutente_ins}"       , dsutente_ins                      );

    
    return ao_map;
  }

  public HashMap of_setPar_Nazioni (HashMap ao_map, String cdnazi, String cdling) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    if (ao_map ==null){
      ao_map = new HashMap();
    }

    HTML html = new HTML();

    Nazioni  nazioni = new Nazioni();
    Vist_nazioni_ref  vist_nazioni_ref = new Vist_nazioni_ref();

    setProfilo((Atk_sql) nazioni);
    setProfilo((Atk_sql) vist_nazioni_ref);


    String dsnazi        = nazioni.getDs(cdnazi, cdling);
    String cdresp        = vist_nazioni_ref.getCdresp_responsabile_vendite(cdnazi);
    
    ao_map.put("${nazioni.dsnazi}"        , dsnazi                           );

    ao_map = of_setPar_Archresp(ao_map, cdresp, cdling);

    nazioni.close();      
    vist_nazioni_ref.close();      
    
    return ao_map;
  }

  public HashMap of_setPar_Ord_test (HashMap ao_map, long tkordi, String cdling) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    if (ao_map ==null){
      ao_map = new HashMap();
    }

    Atk_dwlingua atk_dwlingua = new Atk_dwlingua();
    F_utente     f_utente     = new F_utente    ();
    Crm_credential crm_credential = new Crm_credential();
    Ep_costanti  ep_costanti = new Ep_costanti();
    Archagen     archagen = new Archagen();

    Atk_ctrl atk_ctrl = new Atk_ctrl();

    HTML html = new HTML();

    setProfilo((Atk_sql) atk_dwlingua   );
    setProfilo((Atk_sql) f_utente       );
    setProfilo((Atk_sql) crm_credential );
    setProfilo((Atk_sql) ep_costanti    );
    setProfilo((Atk_sql) archagen       );


  
    String     cdordi        = "";
    String     cdrifo        = "";
    Timestamp  dtordi        = null;
    String     cell_agen     = "";
    String     dsagen        = "";
    long       tkutente_ispe = 0;
    long       tkutente_clie = 0;
    String     email_ispe    = "";
    String     tkclie        = "";
    String     ov_cdagen     = "";

    String  l_query = "";
    
    l_query  = "";
    l_query  = "  select ordt.cdordi                                                    \n";
    l_query += "       , ordt.cdrifo                                                    \n";
    l_query += "       , ordt.dtordi                                                    \n";
    l_query += "       , ( select eute_agen.cellulare                                   \n";
    l_query += "             from pgmr.ep_utente eute_agen                              \n";
    l_query += "            where eute_agen.cdagen = clie.cdagen ) as cell_agen         \n";
    l_query += "       , ( select agen.dsagen                                           \n";
    l_query += "             from pgmr.archagen agen                                    \n";
    l_query += "            where agen.cdagen = clie.cdagen      ) as dsagen            \n";
    l_query += "       , ( select eute_ispe.tkutente                                    \n";
    l_query += "             from pgmr.ep_utente eute_ispe                              \n";
    l_query += "            where eute_ispe.cdispe = clie.cdispe ) as tkutente_ispe     \n";
    l_query += "       , ( select eute_clie.tkutente                                    \n";
    l_query += "             from pgmr.ep_utente eute_clie                              \n";
    l_query += "            where eute_clie.tkclie = clie.tkclie ) as tkutente_clie     \n";
    l_query += "       , ordt.tkclie                                                    \n";
    l_query += "       , ordt.cdagen                                                    \n";
    l_query += "    from pgmr.ord_test  ordt                                            \n";
    l_query += "       , pgmr.archclie  clie                                            \n";
    l_query += "   where ordt.tkordi   = ?                                              \n";
    l_query += "     and ordt.cdazie   = '"+ cdazie +"'                                 \n";
    l_query += "     and ordt.tkclie   = clie.tkclie                                    \n";

    pstm = m_connection.prepareStatement(l_query);
    
    ind = 1;
    pstm.setLong  (ind++, tkordi);
    
    rs = pstm.executeQuery();
    
    if (rs!=null && rs.next()){
      cdordi        = "";
      cdrifo        = "";
      dtordi        = null;
      cell_agen     = "";
      dsagen        = "";
      tkutente_ispe = 0;
      tkutente_clie = 0;
      email_ispe    = "";
      tkclie        = "";
      ov_cdagen     = "";
      
      if (rs.getObject("cdordi"             ) != null)   cdordi             = rs.getString   ("cdordi"             ); 
      if (rs.getObject("cdrifo"             ) != null)   cdrifo             = rs.getString   ("cdrifo"             ); 
      if (rs.getObject("dtordi"             ) != null)   dtordi             = rs.getTimestamp("dtordi"             ); 
      if (rs.getObject("cell_agen"          ) != null)   cell_agen          = rs.getString   ("cell_agen"          ); 
      if (rs.getObject("dsagen"             ) != null)   dsagen             = rs.getString   ("dsagen"             ); 
      if (rs.getObject("tkutente_ispe"      ) != null)   tkutente_ispe      = rs.getLong     ("tkutente_ispe"      ); 
      if (rs.getObject("tkutente_clie"      ) != null)   tkutente_clie      = rs.getLong     ("tkutente_clie"      ); 
      if (rs.getObject("tkclie"             ) != null)   tkclie             = rs.getString   ("tkclie"             ); 
      if (rs.getObject("cdagen"             ) != null)   ov_cdagen          = rs.getString   ("cdagen"             ); 
      
      email_ispe    = f_utente.getEmailFunzione(tkutente_ispe, NOTIF_R_OV);
    }
    
    pstm.close();
    pstm = null;

    String ls_ov_conf_text_cdrifo = "";

    ls_ov_conf_text_cdrifo   = atk_dwlingua.getLabel ("mail_vendita_conf_ordi", cdling, "conf_ordi_text_3_1"   , "", new String[] {cdrifo});
    if (cdrifo.equals("")){
        ls_ov_conf_text_cdrifo = "";
    }else {
        ls_ov_conf_text_cdrifo = " "+ls_ov_conf_text_cdrifo;
    }

    String ls_rit_ordi_text_4_1   = atk_dwlingua.getLabel ("mail_vendita_rit_ordi", cdling, "rit_ordi_text_4_1" , "", null);
    String ls_rit_ordi_text_4_2   = atk_dwlingua.getLabel ("mail_vendita_rit_ordi", cdling, "rit_ordi_text_4_2" , "", new String[] {dsagen, cell_agen});
    String ls_rit_ordi_text_4_3   = atk_dwlingua.getLabel ("mail_vendita_rit_ordi", cdling, "rit_ordi_text_4_3" , "", null);
    String ls_rit_ordi_text_4_4   = atk_dwlingua.getLabel ("mail_vendita_rit_ordi", cdling, "rit_ordi_text_4_4" , "", new String[] {"<a href=\"mailto:"+ email_ispe +"\">"+ email_ispe +"</a>"});

    String ls_rit_ordi_text_4 = "";
    ls_rit_ordi_text_4  = ls_rit_ordi_text_4_1 + " ";
    if (!cell_agen.equals("")) ls_rit_ordi_text_4 += ls_rit_ordi_text_4_2 + " ";
    ls_rit_ordi_text_4 += ls_rit_ordi_text_4_3 ;
    if (!email_ispe.equals("")) ls_rit_ordi_text_4 += " "+ ls_rit_ordi_text_4_4;
    ls_rit_ordi_text_4 += ".";
    
    
    //link diretto pagina ordini portal
    String ep_portal_url    = ep_costanti.getCostvalue("ep.portal_url");
    ep_portal_url = of_cambiaURLLingua(ep_portal_url, cdling);
    Str_crm_credential lstr_crm_credential = new Str_crm_credential();
    
    String ls_arts_url = ep_portal_url + "epDesktop.jsp?nextPage=rlogin/eprogen.html?nextPage=epRep_controllo_ordven_par.jsp&openMethod=iframe&nextPageTitle=Ordine&cdling="+ cdling;
    
    String ls_url_portal_keycode = ls_arts_url;
    
    if (tkutente_clie > 0){
      
      lstr_crm_credential.tkutente = tkutente_clie;
      
      lstr_crm_credential.url = ls_arts_url;
      if (crm_credential.of_getUrlKeycode(lstr_crm_credential) == 1)      crm_credential.m_connection.commit();
      ls_url_portal_keycode = ep_portal_url + lstr_crm_credential.keycode_url;

    }
    
    String ls_fattprof_url = ep_portal_url + "epDesktop.jsp?nextPage=rlogin/eprogen.html?nextPage=epRep_controllo_fattproforma_par.jsp?params=OV~"+ tkordi +"&openMethod=iframe&nextPageTitle=Fatture proforma&cdling="+ cdling;
    
    String ls_url_portal_fattprof_keycode = ls_fattprof_url;
    
    if (tkutente_clie > 0){
      
      lstr_crm_credential.tkutente = tkutente_clie;
      
      lstr_crm_credential.url = ls_fattprof_url;
      if (crm_credential.of_getUrlKeycode(lstr_crm_credential) == 1)      crm_credential.m_connection.commit();
      ls_url_portal_fattprof_keycode = ep_portal_url + lstr_crm_credential.keycode_url;

    }
    
    String ls_ov_agen_email           =  archagen.of_getEmail(ov_cdagen, "", "");
    String ls_ov_agen_email_comm      =  archagen.of_getEmail(ov_cdagen, "", COM_COM);
    String ls_ov_agen_email_no_acc    =  archagen.of_getEmail(ov_cdagen, NOTIF_NACC, "");
    String ls_ov_agen_email_tecn      =  archagen.of_getEmail(ov_cdagen, "", COM_TEC);
    String ls_ov_agen_email_amm       =  archagen.of_getEmail(ov_cdagen, "", COM_AMM);
    String ls_ov_agen_cell            =  archagen.of_getCellulare(ov_cdagen, "", "");
    //

    ao_map.put("${ord_test.cdordi}"                   , cdordi                       );
    ao_map.put("${ord_test.cdrifo}"                   , cdrifo                       );
    ao_map.put("${ord_test.dtordi}"                   , atk_ctrl.getDate(dtordi)     );
    ao_map.put("${ov_conf.text_cdrifo}"               , ls_ov_conf_text_cdrifo       );
    ao_map.put("${ov_ritsped.text_rif_da_contattare}" , ls_rit_ordi_text_4           );
    ao_map.put("${ov_lista.url_keycode}"              , ls_url_portal_keycode           );
    ao_map.put("${ov.agen_email}"                     , ls_ov_agen_email             );
    ao_map.put("${ov.agen_email_comm}"                , ls_ov_agen_email_comm        );
    ao_map.put("${ov.agen_email_no_acc}"              , ls_ov_agen_email_no_acc      );
    ao_map.put("${ov.agen_email_tecn}"                , ls_ov_agen_email_tecn        );
    ao_map.put("${ov.agen_email_amm}"                 , ls_ov_agen_email_amm         );
    ao_map.put("${ov.agen_cell}"                      , ls_ov_agen_cell              );
    ao_map.put("${ov_fattprof.url_keycode}"           , ls_url_portal_fattprof_keycode           );
    
    ao_map = of_setPar_Archclie(ao_map, tkclie, cdling);

    atk_dwlingua.close();
    f_utente.close();
    ep_costanti.close();
    archagen.close();
    
    return ao_map;
  }

  public HashMap of_setPar_Province (HashMap ao_map, String cdprov, String cdling) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    if (ao_map ==null){
      ao_map = new HashMap();
    }

    Province  province = new Province();

    HTML html = new HTML();

    setProfilo((Atk_sql) province);


    String dsprov        = province.getDescr(cdprov);
    
    ao_map.put("${province.dsprov}"        , dsprov                           );


    province.close();      
    
    return ao_map;
  }

  public HashMap of_setPar_Zonacomm (HashMap ao_map, String cdzcom, String cdling) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    if (ao_map ==null){
      ao_map = new HashMap();
    }

    Zonacomm  zonacomm = new Zonacomm();

    HTML html = new HTML();

    setProfilo((Atk_sql) zonacomm);


    String dszcom        = zonacomm.getDs(cdzcom, cdling);
    
    ao_map.put("${zonacomm.dszcom}"        , dszcom                           );


    zonacomm.close();      
    
    return ao_map;
  }

  public HashMap of_setPar_Vist_mpron (HashMap ao_map, long tkmpron, String cdling) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    if (ao_map ==null){
      ao_map = new HashMap();
    }

    Atk_ctrl      atk_ctrl = new Atk_ctrl();
    HTML          html     = new HTML();

    Atk_dwlingua  atk_dwlingua = new Atk_dwlingua();
    Vist_mpron vist_mpron = new Vist_mpron();
    Vist_mpron_ord_posi vist_mpron_ord_posi = new Vist_mpron_ord_posi();
    Vist_mpron_posi vist_mpron_posi = new Vist_mpron_posi();
    Crm_credential crm_credential = new Crm_credential();
    Ep_costanti  ep_costanti = new Ep_costanti();
    F_ord_test  f_ord_test = new F_ord_test();

    setProfilo((Atk_sql) atk_dwlingua);
    setProfilo((Atk_sql) vist_mpron);
    setProfilo((Atk_sql) vist_mpron_ord_posi);
    setProfilo((Atk_sql) vist_mpron_posi);
    setProfilo((Atk_sql) crm_credential);
    setProfilo((Atk_sql) ep_costanti);
    setProfilo((Atk_sql) f_ord_test);
    
    Str_crm_credential lstr_crm_credential = new Str_crm_credential();

    String ep_portal_url    = ep_costanti.getCostvalue("ep.portal_url");
    ep_portal_url = of_cambiaURLLingua(ep_portal_url, cdling);

    
    java.text.NumberFormat  przFormat = java.text.NumberFormat.getInstance(java.util.Locale.ITALY);

    przFormat.setGroupingUsed( true );
    przFormat.setMaximumFractionDigits( 2 );
    przFormat.setMinimumFractionDigits( 2 );
    
    java.text.NumberFormat  numFormat3 = java.text.NumberFormat.getInstance(java.util.Locale.ITALY);

    numFormat3.setGroupingUsed( true );
    numFormat3.setMaximumFractionDigits( 1 );
    numFormat3.setMinimumFractionDigits( 1 );
    
    java.text.NumberFormat  numFormatNoDec = java.text.NumberFormat.getInstance(java.util.Locale.ITALY);

    numFormatNoDec.setGroupingUsed( true );
    numFormatNoDec.setMaximumFractionDigits( 0 );
    numFormatNoDec.setMinimumFractionDigits( 0 );
    

    String tkclie      = "";
    String ls_notapagame  = "";
    String ls_dsvett      = "";
    String ls_ragcog      = "";
    String ls_nota        = "";
    String ls_text_p1     = "";
    String ls_text_p2     = "";
    String ls_text_p3     = "";
    String ls_text_p4     = "";
    String ls_text_p5     = "";
    String ls_text_p6     = "";
    String ls_text_notapagame  = "";
    String ls_text_dsvett      = "";
    long   ll_tkutente_clie = 0;


    String  l_query = "";
    
    l_query  = "";
    l_query  = "  select clie.tkclie                                                                                                            \n";
    l_query += "       , ente.ragcog                                                                                                            \n";
    l_query += "       , mpron.notapagame                                                                                                       \n";
    l_query += "       , mpron.cdvett                                                                                                           \n";
    l_query += "       , mpron.dsvett                                                                                                           \n";
    l_query += "       , mpron.nota                                                                                                             \n";
    l_query += "       , mpron.text_p1                                                                                                          \n";
    l_query += "       , mpron.text_p2                                                                                                          \n";
    l_query += "       , mpron.text_p3                                                                                                          \n";
    l_query += "       , mpron.text_p4                                                                                                          \n";
    l_query += "       , mpron.text_p5                                                                                                          \n";
    l_query += "       , mpron.text_p6                                                                                                          \n";
    l_query += "       , mpron.text_notapagame                                                                                                  \n";
    l_query += "       , mpron.text_dsvett                                                                                                      \n";
    l_query += "       , (select in_eute.tkutente                                                                                               \n";
    l_query += "            from pgmr.ep_utente in_eute                                                                                         \n";
    l_query += "           where in_eute.fgabil = 'S'                                                                                           \n";
    l_query += "             and in_eute.tkclie = clie.tkclie) as tkutente_clie                                                                 \n";
    if (is_oracle){
        throw new Exception("DB non supportato");
    } else if (is_sybase || is_postgresql) {
        l_query += "    from pgmr.vist_mpron     mpron                                                                                              \n";
        l_query += "       ,  pgmr.archclie       clie                                                                             \n";
        l_query += "           left outer join pgmr.mac_pagame     mpag  on  clie.cdpagame = mpag.cdpagame                                          \n";
        l_query += "           left outer join pgmr.archenti       ente  on  clie.cdente   = ente.cdente                                            \n";
        l_query += "   where mpron.tkclie = clie.tkclie                                                                                             \n";
        l_query += "     and mpron.cdazie = '"+ cdazie +"'                                                                                          \n";
        l_query += "     and mpron.tkmpron = ?                                                                                                      \n";
    }

    pstm = m_connection.prepareStatement(l_query);
    
    ind = 1;
    pstm.setLong  (ind++, tkmpron);
    
    rs = pstm.executeQuery();
    
    if (rs!=null && rs.next()){
      tkclie   = "";
      ls_notapagame  = "";
      ls_dsvett      = "";
      ls_ragcog      = "";
      ls_nota        = "";
      ls_text_p1     = "";
      ls_text_p2     = "";
      ls_text_p3     = "";
      ls_text_p4     = "";
      ls_text_p5     = "";
      ls_text_p6     = "";
      ls_text_notapagame  = "";
      ls_text_dsvett      = "";
      ll_tkutente_clie = 0;

      if (rs.getObject("tkclie"      ) != null)  tkclie          = rs.getString("tkclie"      ); 
      if (rs.getObject("notapagame"  ) != null)  ls_notapagame   = rs.getString("notapagame"  ); 
      if (rs.getObject("dsvett"      ) != null)  ls_dsvett       = rs.getString("dsvett"      ); 
      if (rs.getObject("ragcog"      ) != null)  ls_ragcog       = rs.getString("ragcog"      ); 
      if (rs.getObject("nota"        ) != null)  ls_nota         = rs.getString("nota"        ); 
      if (rs.getObject("text_p1"     ) != null)  ls_text_p1      = rs.getString("text_p1"     ); 
      if (rs.getObject("text_p2"     ) != null)  ls_text_p2      = rs.getString("text_p2"     ); 
      if (rs.getObject("text_p3"     ) != null)  ls_text_p3      = rs.getString("text_p3"     ); 
      if (rs.getObject("text_p4"     ) != null)  ls_text_p4      = rs.getString("text_p4"     ); 
      if (rs.getObject("text_p5"     ) != null)  ls_text_p5      = rs.getString("text_p5"     ); 
      if (rs.getObject("text_p6"     ) != null)  ls_text_p6      = rs.getString("text_p6"     );
      if (rs.getObject("text_notapagame"  ) != null)  ls_text_notapagame   = rs.getString("text_notapagame"  ); 
      if (rs.getObject("text_dsvett"      ) != null)  ls_text_dsvett       = rs.getString("text_dsvett"      );
      if (rs.getObject("tkutente_clie"    ) != null)  ll_tkutente_clie     = rs.getLong  ("tkutente_clie"    );  
      
      ls_nota = html.text(ls_nota);
      
      ls_text_p1 = html.text(ls_text_p1);
      ls_text_p2 = html.text(ls_text_p2);
      ls_text_p3 = html.text(ls_text_p3);
      ls_text_p4 = html.text(ls_text_p4);
      ls_text_p5 = html.text(ls_text_p5);
      ls_text_p6 = html.text(ls_text_p6);
      ls_text_notapagame = html.text(ls_text_notapagame);
      ls_text_dsvett = html.text(ls_text_dsvett);
      
      ls_text_p1 = ls_text_p1.replace("@ragioneSociale@",  "<strong>"+ ls_ragcog +"</strong>");
      
      
//      String ls_url_portal_keycode = ep_portal_url;
//    
//      if (ll_tkutente_clie > 0){
//      
//        lstr_crm_credential.tkutente = ll_tkutente_clie;
//
//        lstr_crm_credential.url = ep_portal_url;
//        if (crm_credential.of_getUrlKeycode(lstr_crm_credential) == 1)      crm_credential.m_connection.commit();
//        ls_url_portal_keycode = ep_portal_url + lstr_crm_credential.keycode_url;
//
//      }
      
    String ls_arts_url = ep_portal_url + "epDesktop.jsp?nextPage=rlogin/eprogen.html?nextPage=epRep_controllo_ordven_par.jsp&openMethod=iframe&nextPageTitle=Ordine&cdling="+ cdling;
    
    
    
    String ls_url_portal_keycode = ls_arts_url;
    
    if (ll_tkutente_clie > 0){
      
      lstr_crm_credential.tkutente = ll_tkutente_clie;
      
      lstr_crm_credential.url = ls_arts_url;
      if (crm_credential.of_getUrlKeycode(lstr_crm_credential) == 1)      crm_credential.m_connection.commit();
      ls_url_portal_keycode = ep_portal_url + lstr_crm_credential.keycode_url;

    }
      
      ls_text_p4 = ls_text_p4.replace("@MyVistosi@",  "<a href=\""+ ls_url_portal_keycode +"\">MyVistosi</a>");
      
    }
    
    pstm.close();
    pstm = null;

    //-------------------------------
    //calcolo tabellina righe ordini in pronta connsegna
    String ls_tbl_righe_ov_tot_importo        = "";
    String ls_tbl_righe_ov_tot_importo_vadesc = "";
    
    double lb_tot_documento_ov = 0;
    int    li_tot_righe_ov_count_vacodi = 0;
    String ls_tot_righe_ov_vacodi = "";
    String ls_tot_righe_ov_vadesc = "";

    String ls_tbl_righe_ov  = "";

    String ls_label_riga_cdordi = atk_dwlingua.getLabel("epMpron_ordven_ord_posi", cdling, "mpron_ordven_ord_posi.riga_cdordi", "", null);
    String ls_label_riga_dtordi = atk_dwlingua.getLabel("epMpron_ordven_ord_posi", cdling, "mpron_ordven_ord_posi.riga_dtordi", "", null);
    String ls_label_riga_cdrifo = atk_dwlingua.getLabel("epMpron_ordven_ord_posi", cdling, "mpron_ordven_ord_posi.riga_cdrifo", "", null);
    String ls_label_riga_impuni = atk_dwlingua.getLabel("epMpron_ordven_ord_posi", cdling, "mpron_ordven_ord_posi.riga_impuni", "", null);
    String ls_label_riga_qtatot = atk_dwlingua.getLabel("epMpron_ordven_ord_posi", cdling, "mpron_ordven_ord_posi.riga_qtatot", "", null);
    String ls_label_riga_importo_residuo_c = atk_dwlingua.getLabel("epMpron_ordven_ord_posi", cdling, "mpron_ordven_ord_posi.riga_importo_residuo_c", "", null);
    String ls_label_riga_nrpeso_l = atk_dwlingua.getLabel("epMpron_ordven_ord_posi", cdling, "mpron_ordven_ord_posi.riga_nrpeso_l", "", null);
    String ls_label_riga_vlunlt = atk_dwlingua.getLabel("epMpron_ordven_ord_posi", cdling, "mpron_ordven_ord_posi.riga_vlunlt", "", null);
    String ls_label_riga_dtcons_2 = atk_dwlingua.getLabel("epMpron_ordven_ord_posi", cdling, "mpron_ordven_ord_posi.riga_dtcons_2", "", null);
    String ls_label_riga_totale   = atk_dwlingua.getLabel("epMpron_ordven_ord_posi", cdling, "mpron_ordven_ord_posi.riga_totali" , "", null);
    String ls_label_proncons   = atk_dwlingua.getLabel("epMpron_ordven_ord_posi", cdling, "mpron_ordven_ord_posi.proncons" , "", null);
    String ls_label_totali_imponibile_no_scocas   = atk_dwlingua.getLabel("epMpron_ordven_ord_posi", cdling, "Totale merce" , "", null);
    String ls_label_totali_totale_scocas   = atk_dwlingua.getLabel("epMpron_ordven_ord_posi", cdling, "Sconto cassa" , "", null);
    String ls_label_totali_imponibile   = atk_dwlingua.getLabel("epMpron_ordven_ord_posi", cdling, "Totale netto merce" , "", null);
    String ls_label_totali_imposta   = atk_dwlingua.getLabel("epMpron_ordven_ord_posi", cdling, "Totale IVA" , "", null);
    String ls_label_totali_importo   = atk_dwlingua.getLabel("epMpron_ordven_ord_posi", cdling, "Importo" , "", null);
    
    com.ateikon.structure.Str_totati_ord_test   str_totati_ord_test = new com.ateikon.structure.Str_totati_ord_test();
    
    str_totati_ord_test = f_ord_test.getTotali_mpron_ordven(tkmpron, cdling, "N");

    
    com.ateikon.structure.Str_totati_ord_test   str_totati_ord_test_c = new com.ateikon.structure.Str_totati_ord_test();
    
    str_totati_ord_test_c = f_ord_test.getTotali_mpron_ordven(tkmpron, cdling, "S");

    
    rs = vist_mpron_ord_posi.of_search(tkmpron, cdling);

    if (rs!=null && rs.next()){

      ls_tbl_righe_ov  = "";
      ls_tbl_righe_ov  = " <table width=\"709\" cellpadding=\"0\" cellspacing=\"0\" class=\"report_mpron\">                                                                             \n";
      ls_tbl_righe_ov += "   <tr>                                                                                                                                                    \n";
      ls_tbl_righe_ov += "     <th style=\"padding-left:5px;\" align=\"left\" valign=\"top\" class=\"Stile1\">"+ ls_label_riga_cdordi         +"</th>           \n";
      ls_tbl_righe_ov += "     <th style=\"padding-left:5px;\" align=\"left\" valign=\"top\" class=\"Stile1\">"+ ls_label_riga_dtordi         +"</th>           \n";
      ls_tbl_righe_ov += "     <th style=\"padding-left:5px;\" align=\"left\" valign=\"top\" class=\"Stile1\">"+ ls_label_riga_cdrifo         +"</th>           \n";
      ls_tbl_righe_ov += "     <th style=\"padding-left:5px; width:80px; min-width:80px; max-width:80px;\" align=\"left\" valign=\"top\" class=\"Stile1\">"+ ls_label_riga_impuni         +"</th>           \n";
      ls_tbl_righe_ov += "     <th style=\"padding-left:5px; width:50px; min-width:50px; max-width:50px;\" align=\"left\" valign=\"top\" class=\"Stile1\">"+ ls_label_riga_nrpeso_l       +"</th>           \n";
      ls_tbl_righe_ov += "     <th style=\"padding-left:5px; width:50px; min-width:50px; max-width:50px;\" align=\"left\" valign=\"top\" class=\"Stile1\">"+ ls_label_riga_vlunlt         +"</th>           \n";
      ls_tbl_righe_ov += "     <th style=\"padding-left:5px;\" align=\"left\" valign=\"top\" class=\"Stile1\">"+ ls_label_riga_dtcons_2       +"</th>           \n";
      ls_tbl_righe_ov += "     <th style=\"padding-left:5px;\" align=\"left\" valign=\"top\" class=\"Stile1\">"+ ls_label_riga_qtatot         +"</th>           \n";
      ls_tbl_righe_ov += "     <th style=\"padding-left:5px; width:90px; min-width:90px; max-width:90px;\" align=\"left\" valign=\"top\" class=\"Stile1\">"+ ls_label_riga_importo_residuo_c +"</th>           \n";
      ls_tbl_righe_ov += "   </tr>                                                                                                                                                   \n";

      long   tkordi_prec = 0;
      double     tot_importo     = 0;
      double     tot_importo_c   = 0;
      double     tot_nrpeso_l    = 0;
      double     tot_vlunlt      = 0;
      double     tot_qtatot      = 0;

      double     tot_ordi_importo_prec     = 0;
      double     tot_ordi_importo_c_prec   = 0;
      double     tot_ordi_nrpeso_l_prec    = 0;
      double     tot_ordi_vlunlt_prec      = 0;
      double     tot_ordi_qtatot_prec      = 0;
      String     vadesc_prec          = "";
      String     cdordi          = "";
      String     cdrifo          = "";
      Timestamp  dtordi          = null;
      String     vacodi          = "";
      String     vadesc          = "";
      
      int tot_rig = 0;
      
      
      do {

        long       tkordi          = 0;
        double     importo         = 0;
        double     importo_c       = 0;
        double     nrpeso_l        = 0;
        double     vlunlt          = 0;
        double     impuni          = 0;
        double     impuninetto     = 0;
        double     qtatot          = 0;
        String     dssart          = "";
        String     cdartm          = "";
        String     dsarti          = "";
        String     fgpron_cons     = "";
        String     fgnd_cons       = "";
        String     fgevaso         = "N";//ho solo righe inevase
        String     is_merce_pronta = "";
        Timestamp  dtcons          = null;
        
        cdordi          = "";
        cdrifo          = "";
        dtordi          = null;
        vacodi          = "";
        vadesc          = "";

        tot_importo     = 0;
        tot_importo_c   = 0;
        tot_nrpeso_l    = 0;
        tot_vlunlt      = 0;
        tot_qtatot      = 0;
        
        
        if (rs.getObject("tkordi"              ) != null)  tkordi               = rs.getLong     ("tkordi"              ); 
        if (rs.getObject("tot_importo"         ) != null)  tot_importo          = rs.getDouble   ("tot_importo"         ); 
        if (rs.getObject("tot_importo_c"       ) != null)  tot_importo_c        = rs.getDouble   ("tot_importo_c"       ); 
        if (rs.getObject("tot_nrpeso_l"        ) != null)  tot_nrpeso_l         = rs.getDouble   ("tot_nrpeso_l"        ); 
        if (rs.getObject("tot_vlunlt"          ) != null)  tot_vlunlt           = rs.getDouble   ("tot_vlunlt"          ); 
        if (rs.getObject("tot_qtatot"          ) != null)  tot_qtatot           = rs.getDouble   ("tot_qtatot"          ); 
        if (rs.getObject("importo"             ) != null)  importo              = rs.getDouble   ("importo"             ); 
        if (rs.getObject("importo_c"           ) != null)  importo_c            = rs.getDouble   ("importo_c"           ); 
        if (rs.getObject("nrpeso_l"            ) != null)  nrpeso_l             = rs.getDouble   ("nrpeso_l"            ); 
        if (rs.getObject("vlunlt"              ) != null)  vlunlt               = rs.getDouble   ("vlunlt"              ); 
        if (rs.getObject("impuni"              ) != null)  impuni               = rs.getDouble   ("impuni"              ); 
        if (rs.getObject("impuninetto"         ) != null)  impuninetto          = rs.getDouble   ("impuninetto"         ); 
        if (rs.getObject("qtatot"              ) != null)  qtatot               = rs.getDouble   ("qtatot"              ); 
        if (rs.getObject("cdordi"              ) != null)  cdordi               = rs.getString   ("cdordi"              ); 
        if (rs.getObject("cdrifo"              ) != null)  cdrifo               = rs.getString   ("cdrifo"              ); 
        if (rs.getObject("dssart"              ) != null)  dssart               = rs.getString   ("dssart"              ); 
        if (rs.getObject("cdartm"              ) != null)  cdartm               = rs.getString   ("cdartm"              ); 
        if (rs.getObject("dsarti"              ) != null)  dsarti               = rs.getString   ("dsarti"              ); 
        if (rs.getObject("fgpron_cons"         ) != null)  fgpron_cons          = rs.getString   ("fgpron_cons"         ); 
        if (rs.getObject("fgnd_cons"           ) != null)  fgnd_cons            = rs.getString   ("fgnd_cons"           ); 
        if (rs.getObject("is_merce_pronta"     ) != null)  is_merce_pronta      = rs.getString   ("is_merce_pronta"     ); 
        if (rs.getObject("vacodi"              ) != null)  vacodi               = rs.getString   ("vacodi"              ); 
        if (rs.getObject("vadesc"              ) != null)  vadesc               = rs.getString   ("vadesc"              ); 
        if (rs.getObject("dtordi"              ) != null)  dtordi               = rs.getTimestamp("dtordi"              ); 
        if (rs.getObject("dtcons"              ) != null)  dtcons               = rs.getTimestamp("dtcons"              ); 

        dssart = of_ov_getDescrArticolo(dssart, dsarti);
        
        if (tkordi != tkordi_prec){
          
          if (tot_rig > 0){
            
            ls_tbl_righe_ov += " <tr style=\"background-color: #f1f1f1;\">                                                                                      \n";
            ls_tbl_righe_ov += "     <th align=\"right\" style=\"padding-left:5px;\" colspan=\"4\" >&nbsp;</th>                                                  \n";
            ls_tbl_righe_ov += "     <th align=\"right\" style=\"padding-left:5px;\" >"+ numFormat3.format(tot_ordi_nrpeso_l_prec)   +" Kg</th>                   \n";
            ls_tbl_righe_ov += "     <th align=\"right\" style=\"padding-left:5px;\" >"+ przFormat.format(tot_ordi_vlunlt_prec)     +" mc</th>                   \n";
            ls_tbl_righe_ov += "     <th align=\"right\" style=\"padding-left:5px;\" >&nbsp;</th>                                                                \n";
            ls_tbl_righe_ov += "     <th align=\"right\" style=\"padding-left:5px;\" >"+ (tot_ordi_qtatot_prec > 0 ? numFormatNoDec.format(tot_ordi_qtatot_prec) : "-")     +"</th>                      \n";
            ls_tbl_righe_ov += "     <th align=\"right\" style=\"padding-left:5px;\" >"+ przFormat.format(tot_ordi_importo_prec)    +" "+ vadesc_prec +"</th>    \n";
            ls_tbl_righe_ov += " </tr>                                                                                                                          \n";
          }
          
          ls_tbl_righe_ov += " <tr style=\"background-color: #f1f1f1;\">                                                                            \n";
          ls_tbl_righe_ov += "     <th style=\"padding-left:5px;\" valign=\"top\"  >"+ cdordi                           +"</th>                                      \n";
          ls_tbl_righe_ov += "     <th style=\"padding-left:5px;\" valign=\"top\"  >"+ atk_ctrl.getDate(dtordi)         +"</th>                                      \n";
          ls_tbl_righe_ov += "     <th style=\"padding-left:5px;\" valign=\"top\"  >"+ cdrifo                           +"</th>                                      \n";
          ls_tbl_righe_ov += "     <th align=\"right\" style=\"padding-left:5px;\" colspan=\"6\">&nbsp;</th>                                        \n";
          ls_tbl_righe_ov += " </tr>                                                                                                                \n";


          tkordi_prec = tkordi;
          
          vadesc_prec = vadesc;
          tot_ordi_qtatot_prec = 0;
          tot_ordi_importo_prec = 0;
          tot_ordi_nrpeso_l_prec = 0;
          tot_ordi_vlunlt_prec = 0;
        }
       
        if (!ls_tot_righe_ov_vacodi.equals(vacodi)){
          ls_tot_righe_ov_vacodi = vacodi;
          ls_tot_righe_ov_vadesc = vadesc;
          
          li_tot_righe_ov_count_vacodi++;
        }

        
        tot_ordi_qtatot_prec += qtatot;
        tot_ordi_importo_prec += importo;
        tot_ordi_nrpeso_l_prec += nrpeso_l;
        tot_ordi_vlunlt_prec += vlunlt;
        
         
        ls_tbl_righe_ov += " <tr>                                                                                                                                                                                                       \n";
        ls_tbl_righe_ov += "     <td style=\"padding-left:5px;\" valign=\"top\" class=\"Stile1\" >"+ cdartm +"</td>                                                                                                              \n";
        ls_tbl_righe_ov += "     <td style=\"padding-left:5px;\" valign=\"top\" class=\"Stile1\" colspan=\"2\">"+ html.text(dssart) +"</td>                                                                                      \n";
        if (is_merce_pronta.equals("S")){
          ls_tbl_righe_ov += "     <td style=\"padding-left:5px;\" valign=\"top\" align=\"right\" class=\"Stile1\">"+ przFormat.format(impuninetto)+" "+ vadesc +"</td>                                                                   \n";
          ls_tbl_righe_ov += "     <td style=\"padding-left:5px;\" valign=\"top\" align=\"right\" class=\"Stile1\">"+ numFormat3.format(nrpeso_l)   +" Kg</td>                                                                             \n";
          ls_tbl_righe_ov += "     <td style=\"padding-left:5px;\" valign=\"top\" align=\"right\" class=\"Stile1\">"+ przFormat.format(vlunlt)     +" mc</td>                                                                             \n";
          ls_tbl_righe_ov += "     <td style=\"padding-left:5px;\" valign=\"top\" align=\"center\" class=\"Stile1\">"+ ls_label_proncons +"</td>                                                                                                           \n";
          ls_tbl_righe_ov += "     <td style=\"padding-left:5px;\" valign=\"top\" align=\"right\" class=\"Stile1\">"+ (qtatot > 0 ? numFormatNoDec.format(qtatot) : "-")     +"</td>                                                                                \n";
          ls_tbl_righe_ov += "     <td style=\"padding-left:5px;\" valign=\"top\" align=\"right\" class=\"Stile1\">"+ przFormat.format(importo)    +" "+ vadesc +"</td>                                                                   \n";
        } else {
          ls_tbl_righe_ov += "     <td style=\"padding-left:5px;\" valign=\"top\" align=\"right\" class=\"Stile1\">"+ (impuninetto > 0 ? przFormat.format(impuninetto) + " " + vadesc : "-") +"</td>                                                                                                               \n";
          ls_tbl_righe_ov += "     <td style=\"padding-left:5px;\" valign=\"top\" align=\"right\" class=\"Stile1\">"+ (nrpeso_l > 0 ? numFormat3.format(nrpeso_l) + " Kg" : "-") +"</td>                                                                                                               \n";
          ls_tbl_righe_ov += "     <td style=\"padding-left:5px;\" valign=\"top\" align=\"right\" class=\"Stile1\">"+ (vlunlt > 0 ? przFormat.format(vlunlt) + " mc" : "-") +"</td>                                                                                                               \n";
          ls_tbl_righe_ov += "     <td style=\"padding-left:5px;\" valign=\"top\" align=\"center\" class=\"Stile1\">"+ (is_merce_pronta.equals("S")? "&nbsp;" : vist_mpron_ord_posi.getDescr_ov_dtcons(dtcons, fgpron_cons, fgnd_cons, fgevaso, cdling)) +"</td>                                                                                                               \n";
          ls_tbl_righe_ov += "     <td style=\"padding-left:5px;\" valign=\"top\" align=\"right\" class=\"Stile1\">"+ (qtatot > 0 ? numFormatNoDec.format(qtatot) : "-") +"</td>                                                                                                               \n";
          ls_tbl_righe_ov += "     <td style=\"padding-left:5px;\" valign=\"top\" align=\"right\" class=\"Stile1\">"+ (importo > 0 ? przFormat.format(importo) + " " + vadesc : "-") +"</td>                                                                                                               \n";
        }
        ls_tbl_righe_ov += " </tr>                                                                                                                                                                                                      \n";

        tot_rig++;
        
      } while (rs!=null && rs.next());

      
      ls_tbl_righe_ov += " <tr style=\"background-color: #f1f1f1;\">                                                                                      \n";
      ls_tbl_righe_ov += "     <th align=\"right\" style=\"padding-left:5px;\" colspan=\"4\" >&nbsp;</th>                                                  \n";
      ls_tbl_righe_ov += "     <th align=\"right\" style=\"padding-left:5px;\" nowrap >"+ numFormat3.format(tot_ordi_nrpeso_l_prec)   +" Kg</th>                   \n";
      ls_tbl_righe_ov += "     <th align=\"right\" style=\"padding-left:5px;\" nowrap >"+ przFormat.format(tot_ordi_vlunlt_prec)     +" mc</th>                   \n";
      ls_tbl_righe_ov += "     <th align=\"right\" style=\"padding-left:5px;\" >&nbsp;</th>                                                                \n";
      ls_tbl_righe_ov += "     <th align=\"right\" style=\"padding-left:5px;\" >"+ (tot_ordi_qtatot_prec > 0 ? numFormatNoDec.format(tot_ordi_qtatot_prec) : "-")     +"</th>                      \n";
      ls_tbl_righe_ov += "     <th align=\"right\" style=\"padding-left:5px;\" >"+ przFormat.format(tot_ordi_importo_prec)    +" "+ vadesc_prec +"</th>    \n";
      ls_tbl_righe_ov += " </tr>                                                                                                                          \n";
      
      String ls_tbl_righe_ov_imponibile_no_scocas = "";
      String ls_tbl_righe_ov_totale_scocas = "";
      String ls_tbl_righe_ov_imponibile = "";
      String ls_tbl_righe_ov_imposta = "";
      String ls_tbl_righe_ov_totale_documento = "";
      double ld_tbl_righe_ov_imposta = 0;
      
      //SE valute uguali
      if (li_tot_righe_ov_count_vacodi == 1){
        
        ls_tbl_righe_ov_tot_importo        = ""+ przFormat.format(tot_importo);
        ls_tbl_righe_ov_tot_importo_vadesc = ""+ ls_tot_righe_ov_vadesc;
        ls_tbl_righe_ov_imponibile_no_scocas = ""+ przFormat.format(str_totati_ord_test.imponibile_no_scocas);
        ls_tbl_righe_ov_totale_scocas = ""+ przFormat.format(str_totati_ord_test.totale_scocas);
        ls_tbl_righe_ov_imponibile = ""+ przFormat.format(str_totati_ord_test.imponibile);
        ls_tbl_righe_ov_imposta = ""+ przFormat.format(str_totati_ord_test.imposta);
        ls_tbl_righe_ov_totale_documento = ""+ przFormat.format(str_totati_ord_test.totale_documento);
        lb_tot_documento_ov = str_totati_ord_test.totale_documento;
        ld_tbl_righe_ov_imposta = str_totati_ord_test.imposta;
        
      } else {
        
        ls_tbl_righe_ov_tot_importo        = ""+ przFormat.format(tot_importo_c);
        ls_tbl_righe_ov_tot_importo_vadesc = "&euro;";
        ls_tbl_righe_ov_imponibile_no_scocas = ""+ przFormat.format(str_totati_ord_test.imponibile_no_scocas);
        ls_tbl_righe_ov_totale_scocas = ""+ przFormat.format(str_totati_ord_test.totale_scocas);
        ls_tbl_righe_ov_imponibile = ""+ przFormat.format(str_totati_ord_test_c.imponibile);
        ls_tbl_righe_ov_imposta = ""+ przFormat.format(str_totati_ord_test_c.imposta);
        ls_tbl_righe_ov_totale_documento = ""+ przFormat.format(str_totati_ord_test_c.totale_documento);
        lb_tot_documento_ov = str_totati_ord_test_c.totale_documento;
        ld_tbl_righe_ov_imposta = str_totati_ord_test_c.imposta;
      }

      ls_tbl_righe_ov += " <tr>                                                                                                                                                                                 \n";
      ls_tbl_righe_ov += "     <th style=\"padding-left:5px;\" valign=\"top\" align=\"right\" class=\"Stile1\" colspan=\"4\">"+ ls_label_riga_totale +"</th>                                             \n";
      ls_tbl_righe_ov += "     <th style=\"padding-left:5px;\" valign=\"top\" align=\"right\" class=\"Stile1\">"+ numFormat3.format(tot_nrpeso_l)   +" Kg</th>                                                   \n";
      ls_tbl_righe_ov += "     <th style=\"padding-left:5px;\" valign=\"top\" align=\"right\" align=\"right\" class=\"Stile1\">"+ przFormat.format(tot_vlunlt)     +" mc</th>                                   \n";
      ls_tbl_righe_ov += "     <th style=\"padding-left:5px;\" valign=\"top\" align=\"right\" class=\"Stile1\">&nbsp;</th>                                                                                      \n";
      ls_tbl_righe_ov += "     <th style=\"padding-left:5px;\" valign=\"top\" align=\"right\" class=\"Stile1\">"+ (tot_qtatot > 0 ? numFormatNoDec.format(tot_qtatot) : "-")     +"</th>                                                      \n";
      ls_tbl_righe_ov += "     <th style=\"padding-left:5px;\" valign=\"top\" align=\"right\" class=\"Stile1\">"+ ls_tbl_righe_ov_tot_importo      +" "+ ls_tbl_righe_ov_tot_importo_vadesc +"</th>             \n";
      ls_tbl_righe_ov += " </tr>                                                                                                                                                                                \n";

      if (str_totati_ord_test.totale_scocas > 0){
        ls_tbl_righe_ov += "   <tr style=\"color:#000000; background-color: #cccccc;\">                                                                                                                                                    \n";
        ls_tbl_righe_ov += "     <th style=\"padding-left:5px; border-right: 1px solid #ffffff; border-bottom: 1px solid #ffffff;\" align=\"right\" valign=\"top\" class=\"Stile1\" colspan=\"8\" >"+ ls_label_totali_imponibile_no_scocas +"</th>           \n";
        ls_tbl_righe_ov += "     <th style=\"padding-left:5px; width:90px; min-width:90px; max-width:90px; border-bottom: 1px solid #ffffff;\" align=\"right\" valign=\"top\" class=\"Stile1\">"+ ls_tbl_righe_ov_imponibile_no_scocas + " " + ls_tbl_righe_ov_tot_importo_vadesc +"</th>                        \n";
        ls_tbl_righe_ov += "   </tr>                                                                                                                                                      \n";

        ls_tbl_righe_ov += "   <tr style=\"color:#000000; background-color: #cccccc;\">                                                                                                                                                    \n";
        ls_tbl_righe_ov += "     <th style=\"padding-left:5px; border-right: 1px solid #ffffff; border-bottom: 1px solid #ffffff;\" align=\"right\" valign=\"top\" class=\"Stile1\" colspan=\"8\" >"+ ls_label_totali_totale_scocas +"</th>           \n";
        ls_tbl_righe_ov += "     <th style=\"padding-left:5px; width:90px; min-width:90px; max-width:90px; border-bottom: 1px solid #ffffff;\" align=\"right\" valign=\"top\" class=\"Stile1\">"+ ls_tbl_righe_ov_totale_scocas + " " + ls_tbl_righe_ov_tot_importo_vadesc +"</th>                        \n";
        ls_tbl_righe_ov += "   </tr>                                                                                                                                                      \n";
      }
      
      ls_tbl_righe_ov += "   <tr style=\"color:#000000; background-color: #cccccc;\">                                                                                                                                                    \n";
      ls_tbl_righe_ov += "     <th style=\"padding-left:5px; border-right: 1px solid #ffffff; border-bottom: 1px solid #ffffff;\" align=\"right\" valign=\"top\" class=\"Stile1\" colspan=\"8\" >"+ ls_label_totali_imponibile +"</th>           \n";
      ls_tbl_righe_ov += "     <th style=\"padding-left:5px; width:90px; min-width:90px; max-width:90px; border-bottom: 1px solid #ffffff;\" align=\"right\" valign=\"top\" class=\"Stile1\">"+ ls_tbl_righe_ov_imponibile + " " + ls_tbl_righe_ov_tot_importo_vadesc +"</th>                        \n";
      ls_tbl_righe_ov += "   </tr>                                                                                                                                                      \n";
      
      if (ld_tbl_righe_ov_imposta > 0){
          
        ls_tbl_righe_ov += "   <tr style=\"color:#000000; background-color: #cccccc;\">                                                                                                                                                    \n";
        ls_tbl_righe_ov += "     <th style=\"padding-left:5px; border-right: 1px solid #ffffff; border-bottom: 1px solid #ffffff;\" align=\"right\" valign=\"top\" class=\"Stile1\" colspan=\"8\" >"+ ls_label_totali_imposta +"</th>           \n";
        ls_tbl_righe_ov += "     <th style=\"padding-left:5px; width:90px; min-width:90px; max-width:90px; border-bottom: 1px solid #ffffff;\" align=\"right\" valign=\"top\" class=\"Stile1\">"+ ls_tbl_righe_ov_imposta + " " + ls_tbl_righe_ov_tot_importo_vadesc +"</th>                        \n";
        ls_tbl_righe_ov += "   </tr>                                                                                                                                                      \n";

        ls_tbl_righe_ov += "   <tr style=\"color:#000000; background-color: #cccccc;\">                                                                                                                                                    \n";
        ls_tbl_righe_ov += "     <th style=\"padding-left:5px; border-right: 1px solid #ffffff; border-bottom: 1px solid #ffffff;\" align=\"right\" valign=\"top\" class=\"Stile1\" colspan=\"8\" >"+ ls_label_totali_importo +"</th>           \n";
        ls_tbl_righe_ov += "     <th style=\"padding-left:5px; width:90px; min-width:90px; max-width:90px; border-bottom: 1px solid #ffffff;\" align=\"right\" valign=\"top\" class=\"Stile1\">"+ ls_tbl_righe_ov_totale_documento + " " + ls_tbl_righe_ov_tot_importo_vadesc +"</th>                        \n";
        ls_tbl_righe_ov += "   </tr>                                                                                                                                                      \n";
      }
    
      
      
      ls_tbl_righe_ov += " </table>                \n";
    }


    //-------------------------------
    //calcolo tabellina righe scadenze
    String ls_tbl_righe_scad_tot_importo        = "";
    String ls_tbl_righe_scad_tot_importo_vadesc = "";
    
    double ld_tot_scadenze    = 0;
    double ld_tot_scadenze_c  = vist_mpron.of_getImporto_totale_c(tkmpron);
    int    tot_scadenze_count_vacodi = 0;
    String tot_scadenze_vacodi      = "";
    String tot_scadenze_vadesc      = "";
    
    String ls_tbl_righe_scad  = "";

    String ls_label_scad_dtdoc    = atk_dwlingua.getLabel("epMpron_ordven_posi", cdling, "mpron_ordven_posi.dtdoc"  , "", null);
    String ls_label_scad_nrdoc    = atk_dwlingua.getLabel("epMpron_ordven_posi", cdling, "mpron_ordven_posi.nrdoc"  , "", null);
    String ls_label_scad_importo  = atk_dwlingua.getLabel("epMpron_ordven_posi", cdling, "mpron_ordven_posi.importo", "", null);
    String ls_label_scad_vadesc   = atk_dwlingua.getLabel("epMpron_ordven_posi", cdling, "mpron_ordven_posi.vadesc" , "", null);
    String ls_label_scad_nota     = atk_dwlingua.getLabel("epMpron_ordven_posi", cdling, "mpron_ordven_posi.nota"   , "", null);
    String ls_label_scad_totale   = atk_dwlingua.getLabel("epMpron_ordven_posi", cdling, "mpron_ordven_posi.totale" , "", null);
    
    rs = vist_mpron_posi.of_searchRighe_scadenze(tkmpron, cdling);
    

    ls_tbl_righe_scad  = "";
    ls_tbl_righe_scad  = " <table width=\"709\" cellpadding=\"0\" cellspacing=\"0\" class=\"report_mpron\">                                                                             \n";
    ls_tbl_righe_scad += "   <tr>                                                                                                                                                    \n";
    ls_tbl_righe_scad += "     <th style=\"padding-left:5px;\" align=\"left\" valign=\"top\" class=\"Stile1\">"+ ls_label_scad_dtdoc   +"</th>           \n";
    ls_tbl_righe_scad += "     <th style=\"padding-left:5px;\" align=\"left\" valign=\"top\" class=\"Stile1\">"+ ls_label_scad_nrdoc   +"</th>           \n";
    ls_tbl_righe_scad += "     <th style=\"padding-left:5px;\" align=\"left\" valign=\"top\" class=\"Stile1\">"+ ls_label_scad_nota    +"</th>           \n";
    ls_tbl_righe_scad += "     <th style=\"padding-left:5px; width:90px; min-width:90px; max-width:90px;\" align=\"left\" valign=\"top\" class=\"Stile1\">"+ ls_label_scad_importo +"</th>           \n";
    ls_tbl_righe_scad += "   </tr>                                                                                                                                                   \n";

    if (rs!=null && rs.next()){

      do {

        long          tkmpronp       = 0;
        double        importo        = 0;
        String        nrdoc          = "";
        String        nota           = "";
        String        vacodi         = "";
        String        vadesc         = "";
        Timestamp     dtdoc          = null;

        if (rs.getObject("tkmpronp"   )!= null )  tkmpronp      = rs.getLong        ("tkmpronp"   );
        if (rs.getObject("importo"    ) != null)  importo       = rs.getDouble      ("importo"    ); 
        if (rs.getObject("nrdoc"      ) != null)  nrdoc         = rs.getString      ("nrdoc"      ); 
        if (rs.getObject("nota"       ) != null)  nota          = rs.getString      ("nota"       ); 
        if (rs.getObject("vacodi"     ) != null)  vacodi        = rs.getString      ("vacodi"     ); 
        if (rs.getObject("vadesc"     ) != null)  vadesc        = rs.getString      ("vadesc"     ); 
        if (rs.getObject("dtdoc"      ) != null)  dtdoc         = rs.getTimestamp   ("dtdoc"      ); 
       
        ld_tot_scadenze += importo;
        
        if (tot_scadenze_vacodi.equals("") || !tot_scadenze_vacodi.equals(vacodi)){
          tot_scadenze_vacodi = vacodi;
          tot_scadenze_vadesc = vadesc;
          
          tot_scadenze_count_vacodi++;
        }
         
        ls_tbl_righe_scad += " <tr>                                                                                                                                       \n";
        ls_tbl_righe_scad += "     <td style=\"padding-left:5px;\" valign=\"top\" align=\"center\" class=\"Stile1\">"+ atk_ctrl.getDate(dtdoc) +"</td>             \n";
        ls_tbl_righe_scad += "     <td style=\"padding-left:5px;\" valign=\"top\" align=\"left\" class=\"Stile1\">"+ nrdoc                   +"</td>             \n";
        ls_tbl_righe_scad += "     <td style=\"padding-left:5px;\" valign=\"top\" align=\"left\" class=\"Stile1\">"+ html.text(nota)         +"</td>             \n";
        ls_tbl_righe_scad += "     <td style=\"padding-left:5px;\" valign=\"top\" align=\"right\" class=\"Stile1\">"+ przFormat.format(importo) + " " + html.text(vadesc)       +"</td>             \n";
        ls_tbl_righe_scad += " </tr>                                                                                                                                      \n";

      } while (rs!=null && rs.next());
      
      
      ls_tbl_righe_scad_tot_importo        = "";
      ls_tbl_righe_scad_tot_importo_vadesc = "";

      //SE valute uguali
      if (tot_scadenze_count_vacodi == 1){

        ls_tbl_righe_scad_tot_importo        = ""+ przFormat.format(ld_tot_scadenze);
        ls_tbl_righe_scad_tot_importo_vadesc = ""+ tot_scadenze_vadesc;

      } else {

        ls_tbl_righe_scad_tot_importo        = ""+ przFormat.format(ld_tot_scadenze_c);
        ls_tbl_righe_scad_tot_importo_vadesc = "&euro;";
      }
      
    } else {
      
      ls_tbl_righe_scad += " <tr>                                                                                                                                 \n";
      ls_tbl_righe_scad += "     <td style=\"padding-left:5px;\" valign=\"top\" align=\"center\" class=\"Stile1\"></td>                                    \n";
      ls_tbl_righe_scad += "     <td style=\"padding-left:5px;\" valign=\"top\" align=\"center\" class=\"Stile1\"></td>                                    \n";
      ls_tbl_righe_scad += "     <td style=\"padding-left:5px;\" valign=\"top\" align=\"center\" class=\"Stile1\"></td>                                    \n";
      ls_tbl_righe_scad += "     <td style=\"padding-left:5px;\" valign=\"top\" align=\"right\" class=\"Stile1\">"+ przFormat.format(0)       +"</td>             \n";
      ls_tbl_righe_scad += " </tr>                                                                                                                                \n";
      
      
      ls_tbl_righe_scad_tot_importo        = ""+ przFormat.format(0);
      ls_tbl_righe_scad_tot_importo_vadesc = "";
    }


    ls_tbl_righe_scad += " <tr>                                                                                                                                                       \n";
    ls_tbl_righe_scad += "     <th style=\"padding-left:5px;\" valign=\"top\" align=\"right\" class=\"Stile1\" colspan=\"3\">"+ ls_label_scad_totale +"</th>                   \n";
    ls_tbl_righe_scad += "     <th style=\"padding-left:5px;\" valign=\"top\" align=\"right\" class=\"Stile1\">"+ ls_tbl_righe_scad_tot_importo + " " + ls_tbl_righe_scad_tot_importo_vadesc +"</th>                        \n";
    ls_tbl_righe_scad += " </tr>                                                                                                                                                      \n";

    ls_tbl_righe_scad += " </table>                \n";
    
    //-------------------------------
    // Calcolo importo netto
    
    double ld_imp_finale_valore = 0;
    String ls_imp_finale_vadesc = "";
    
    //SE il totale delle scadenze � diverso da 0
    if (ld_tot_scadenze_c == 0){
      
        ld_imp_finale_valore      = lb_tot_documento_ov;
        ls_imp_finale_vadesc      = ls_tot_righe_ov_vadesc;

    } else {
      //SE valute uguali dei due totali
      if (li_tot_righe_ov_count_vacodi == 1 && tot_scadenze_count_vacodi == 1 && ls_tot_righe_ov_vacodi.equals(tot_scadenze_vacodi)){

        ld_imp_finale_valore      = lb_tot_documento_ov + ld_tot_scadenze;
        ls_imp_finale_vadesc      = ls_tot_righe_ov_vadesc;

      } else {  

        ld_imp_finale_valore      = str_totati_ord_test_c.totale_documento + ld_tot_scadenze_c;
        ls_imp_finale_vadesc      = "&euro;";
      }
    }
    
    
    
    //-------------------------------
    //calcolo tabellina importo netto e
    //Testo pagamento anticipato
    
    String ls_tbl_imp_finale  = "";
    String ls_text_clie_pag_anticipato_importo        = przFormat.format(ld_imp_finale_valore);
    String ls_text_clie_pag_anticipato_vadesc         = ls_imp_finale_vadesc;
    String ls_text_clie_pag_anticipato                = "";
    
    ls_text_clie_pag_anticipato = ls_text_notapagame.replace("@###.##@", "<strong>"+ przFormat.format(ld_imp_finale_valore) + " " + ls_imp_finale_vadesc + "</strong>");
    
    
    String ls_label_imp_finale   = atk_dwlingua.getLabel("epMpron_ordven_body", cdling, "Importo finale" , "", null);
    
    ls_tbl_imp_finale  = "";
    ls_tbl_imp_finale  = " <table width=\"709\" cellpadding=\"0\" cellspacing=\"0\" class=\"report_mpron\">                                                                             \n";
    ls_tbl_imp_finale += "   <tr style=\"color:#ffffff; background-color: #333333;\">                                                                                                                                                    \n";
    ls_tbl_imp_finale += "     <th style=\"padding-left:5px;\" align=\"right\" valign=\"top\" class=\"Stile1\">"+ ls_label_imp_finale   +"</th>           \n";
    ls_tbl_imp_finale += "     <th style=\"padding-left:5px; width:90px; min-width:90px; max-width:90px;\" align=\"right\" valign=\"top\" class=\"Stile1\">"+ przFormat.format(ld_imp_finale_valore) + " " + ls_imp_finale_vadesc +"</th>                        \n";
    ls_tbl_imp_finale += "   </tr>                                                                                                                                                      \n";
    ls_tbl_imp_finale += " </table>                \n";

        
    
    
    //Testo vettore
    String ls_text_vettore   = ls_text_dsvett;  
    
    
    

    ao_map.put("${ov_mpron.tbl_righe_ov}"                      , ls_tbl_righe_ov                               );
    ao_map.put("${ov_mpron.tbl_righe_scad}"                    , ls_tbl_righe_scad                             );
    ao_map.put("${ov_mpron.tbl_righe_scad_tot_importo}"        , ls_tbl_righe_scad_tot_importo                 );
    ao_map.put("${ov_mpron.tbl_righe_scad_tot_importo_vadesc}" , ls_tbl_righe_scad_tot_importo_vadesc          );
    ao_map.put("${ov_mpron.tbl_imp_finale}"                    , ls_tbl_imp_finale                             );
    ao_map.put("${ov_mpron.text_imp_finale}"                   , przFormat.format(ld_imp_finale_valore)        );
    ao_map.put("${ov_mpron.text_imp_finale_vadesc}"            , ls_imp_finale_vadesc                          );
    ao_map.put("${ov_mpron.text_clie_pag_anticipato}"          , ls_text_clie_pag_anticipato                   );
    ao_map.put("${ov_mpron.text_clie_pag_anticipato_importo}"  , ls_text_clie_pag_anticipato_importo           );
    ao_map.put("${ov_mpron.text_clie_pag_anticipato_vadesc}"   , ls_text_clie_pag_anticipato_vadesc            );
    ao_map.put("${ov_mpron.text_vettore}"                      , ls_text_vettore                               );
    ao_map.put("${ov_mpron.nota}"                              , ls_nota                                       );
    ao_map.put("${ov_mpron.p1}"                                , ls_text_p1                                    );
    ao_map.put("${ov_mpron.p2}"                                , ls_text_p2                                    );
    ao_map.put("${ov_mpron.p3}"                                , ls_text_p3                                    );
    ao_map.put("${ov_mpron.p4}"                                , ls_text_p4                                    );
    ao_map.put("${ov_mpron.p5}"                                , ls_text_p5                                    );
    ao_map.put("${ov_mpron.p6}"                                , ls_text_p6                                    );

    ao_map = of_setPar_Archclie(ao_map, tkclie, cdling);


    atk_dwlingua.close();
    vist_mpron.close();
    vist_mpron_ord_posi.close();
    vist_mpron_posi.close();
    crm_credential.close();   
    ep_costanti.close(); 
    f_ord_test.close(); 


    return ao_map;
  }
  
  //MPERUZZA 20130118
  public HashMap of_setPar_Web_ord_test (HashMap ao_map, long tkordi , String cdling) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    if (ao_map ==null){
      ao_map = new HashMap();
    }

    Atk_dwlingua atk_dwlingua = new Atk_dwlingua();
    F_utente     f_utente     = new F_utente    ();
    Ep_costanti ep_costanti = new Ep_costanti();
    Crm_credential crm_credential = new Crm_credential();

    Atk_ctrl atk_ctrl = new Atk_ctrl();

    HTML html = new HTML();

    setProfilo((Atk_sql) atk_dwlingua);
    setProfilo((Atk_sql) f_utente    );
    setProfilo((Atk_sql) ep_costanti );
    setProfilo((Atk_sql) crm_credential );
    
    //Dati utente
    
    String ls_spettabile = "";

    l_query = "";
    l_query += " select ragcog_de            \n";
    l_query += "   from pgmr.web_ord_test    \n";
    l_query += "  where tkordi = " + tkordi + "  \n";

    ls_spettabile = sql_String(l_query);
    if (ls_spettabile.equals("")) {

        l_query = "";
        l_query += " select ente.ragcog               \n";
        l_query += "   from pgmr.web_ord_test ordt    \n";
        l_query += "      , pgmr.archclie     clie    \n";
        l_query += "      , pgmr.archenti     ente    \n";
        l_query += "  where ordt.tkordi = " + tkordi + "  \n";
        l_query += "    and clie.tkclie = ordt.tkclie \n";
        l_query += "    and clie.cdazie = ordt.cdazie \n";
        l_query += "    and ente.cdente = clie.cdente \n";

        ls_spettabile = sql_String(l_query);
    }


    if (ls_spettabile.equals("")) {

        l_query = "";
        l_query += " select uten.dsutente                 \n";
        l_query += "   from pgmr.web_ord_test ordt        \n";
        l_query += "      , pgmr.cat_utente   uten        \n";
        l_query += "      , pgmr.archenti     ente        \n";
        l_query += "  where ordt.tkordi = " + tkordi + "      \n";
        l_query += "    and uten.tkutente = ordt.tkutente \n";

        ls_spettabile = sql_String(l_query);
    }

    if (ls_spettabile.equals(""))         ls_spettabile = "cliente";
    
       
    
    //Dati ordine
    
    String ls_itestazione_mail_ric_ordweb = "";

    String cdordi = "";
    Timestamp dtordi = null;
    long tksubutente = 0;
    long tkutente = 0;
    long tkutente_clie = 0;
    String tkclie = "";
    String cdente = "";
    String cdagen = "";
    String cdpromo_m = ""; 


    l_query = "";
    l_query += " select ordt.cdordi                         \n";
    l_query += "      , ordt.dtordi                         \n";
    l_query += "      , ordt.tkclie                         \n";
    l_query += "      , clie.cdagen                         \n";
    l_query += "      , clie.cdente                         \n";
    l_query += "      , ordt.cdpromo_m                      \n"; 
    l_query += "      , ordt.tksubutente                    \n"; 
    l_query += "      , ordt.tkutente                       \n"; 
    l_query += "      , (select in_eute.tkutente                                                          \n";
    l_query += "           from pgmr.ep_utente in_eute                                                    \n";
    l_query += "          where in_eute.fgabil = 'S'                                                      \n";
    l_query += "            and in_eute.tkclie = ordt.tkclie) as tkutente_clie                            \n";
    l_query += "   from pgmr.web_ord_test  ordt                                                           \n";
    l_query += "        left outer join pgmr.archclie  clie on  ordt.tkclie = clie.tkclie                  \n";
    l_query += "  where ordt.tkordi   = " + tkordi + "                                                    \n";

    pstm = m_connection.prepareStatement(l_query);

    rs = pstm.executeQuery();

    if (rs != null && rs.next()) {
        if (rs.getObject("cdordi") != null)                     cdordi = rs.getString("cdordi");
        if (rs.getObject("dtordi") != null)                     dtordi = rs.getTimestamp("dtordi");
        if (rs.getObject("tkclie") != null)                     tkclie = rs.getString("tkclie");
        if (rs.getObject("cdagen") != null)                     cdagen = rs.getString("cdagen");
        if (rs.getObject("cdente") != null)                     cdente = rs.getString("cdente");
        if (rs.getObject("cdpromo_m") != null)                  cdpromo_m = rs.getString("cdpromo_m");
        if (rs.getObject("tksubutente") != null)                tksubutente = rs.getLong("tksubutente");
        if (rs.getObject("tkutente") != null)                   tkutente = rs.getLong("tkutente");
        if (rs.getObject("tkutente_clie") != null)                   tkutente_clie = rs.getLong("tkutente_clie");
    } 
    
    pstm.close();
    pstm = null;
    
    //Destinazione merce
    
    String ls_itestazione_mail_ric_ordwebdsdest_merce = "";
    
    l_query = "";
    l_query += "  select test.tkordi                            \n";
    l_query += "       , test.cdstato                           \n";
    l_query += "       , test.cduldm                            \n";
    l_query += "       , test.tkclie                            \n";
    l_query += "       , ente.ragcog                            \n";
    l_query += "       , ulsl.cdunil         as cdulsl          \n";
    l_query += "       , ulsl.indiri         as indiri_sl       \n";
    l_query += "       , ulsl.cap            as cap_sl          \n";
    l_query += "       , ulsl.comune         as comune_sl       \n";
    l_query += "       , ulsl.cdprov         as cdprov_sl       \n";
    l_query += "       , ulsl.cdnazi         as cdnazi_sl       \n";
    l_query += "       , nazi_sl.cdnazi_m    as cdnazi_m_sl     \n";
    l_query += "       , nazi_sl.dsnazi      as dsnazi_sl       \n";
    l_query += "       , prov_sl.cdprov_m    as cdprov_m_sl     \n";
    l_query += "       , prov_sl.dsprov      as dsprov_sl       \n";
    if (is_sybase || is_postgresql) {

        l_query += "  from pgmr.web_ord_test  test                                                           \n";
        l_query += "     , pgmr.archenti      ente                                                           \n";
        l_query += "     , pgmr.enteuniloc    enul                                                           \n";
        l_query += "     , pgmr.unitalocali   ulsl                                        \n";
        l_query += "          left outer join pgmr.nazioni       nazi_sl on ulsl.cdnazi    = nazi_sl.cdnazi  \n";
        l_query += "          left outer join pgmr.province      prov_sl on ulsl.cdprov    = prov_sl.cdprov  \n";
        l_query += " where test.tkordi  =  " + tkordi + "                                                        \n";
        l_query += "   and test.cdentc  = ente.cdente                                                        \n";
        l_query += "   and ente.cdente  = enul.cdente                                                        \n";
        l_query += "   and enul.fseleg  = 'S'                                                                \n";
        l_query += "   and enul.cdunil = ulsl.cdunil                                                         \n";

    } else {
        throw new Exception("DB NON Previsto !!");
    }

    pstm = m_connection.prepareStatement(l_query);

    rs = pstm.executeQuery();
        
    String ragcog_sl     = "";
    String cdulsl        = "";
    String indiri_sl     = "";
    String cap_sl        = "";
    String comune_sl     = "";
    String cdprov_sl     = "";
    String cdnazi_sl     = "";

    String cdprov_m_sl   = "";
    String dsprov_sl     = "";
    String cdnazi_m_sl   = "";
    String dsnazi_sl     = "";

    // --- Destinazione Merce Gestionale

    String cduldm        = "";
    String cdunil_m_dm   = "";
    String dsunil_dm     = "";
    String indiri_dm     = "";
    String cap_dm        = "";
    String comune_dm     = "";
    String cdprov_dm     = "";
    String cdnazi_dm     = "";

    String cdprov_m_dm   = "";
    String dsprov_dm     = "";
    String cdnazi_m_dm   = "";
    String dsnazi_dm     = "";
    String filiale_dm    = "";
    String fgfiliale_dm_variata = "N";

    // --- Destinazione Merce Libera

    String ragcog_de     = "";
    String indiri_de     = "";
    String cap_de        = "";
    String comune_de     = "";
    String cdprov_de     = "";
    String cdstat_de     = "";

    String cdprov_m_de   = "";
    String dsprov_de     = "";
    String cdnazi_m_de   = "";
    String dsnazi_de     = "";

    if (rs != null && rs.next()) {
        if (rs.getObject("ragcog") != null)                     ragcog_sl = rs.getString("ragcog");
        if (rs.getObject("cdulsl") != null)                     cdulsl = rs.getString("cdulsl");
        if (rs.getObject("indiri_sl") != null)                  indiri_sl = rs.getString("indiri_sl");
        if (rs.getObject("cap_sl") != null)                     cap_sl = rs.getString("cap_sl");
        if (rs.getObject("comune_sl") != null)                  comune_sl = rs.getString("comune_sl");
        if (rs.getObject("cdprov_sl") != null)                  cdprov_sl = rs.getString("cdprov_sl");
        if (rs.getObject("cdnazi_sl") != null)                  cdnazi_sl = rs.getString("cdnazi_sl");
        if (rs.getObject("cdprov_m_sl") != null)                cdprov_m_sl = rs.getString("cdprov_m_sl");
        if (rs.getObject("dsprov_sl") != null)                  dsprov_sl = rs.getString("dsprov_sl");
        if (rs.getObject("cdnazi_m_sl") != null)                cdnazi_m_sl = rs.getString("cdnazi_m_sl");
        if (rs.getObject("dsnazi_sl") != null)                  dsnazi_sl = rs.getString("dsnazi_sl");
    }
    pstm.close();
    pstm = null;


    l_query = "";
    l_query += "  select test.tkordi                            \n";
    l_query += "       , test.cdstato                           \n";
    l_query += "       , test.cduldm                            \n";
    l_query += "       , test.tkclie                            \n";
    l_query += "       , uldm.cdunil_m       as cdunil_m_dm     \n";
    l_query += "       , uldm.dsunil         as dsunil_dm       \n";
    l_query += "       , uldm.indiri         as indiri_dm       \n";
    l_query += "       , uldm.cap            as cap_dm          \n";
    l_query += "       , uldm.comune         as comune_dm       \n";
    l_query += "       , uldm.cdprov         as cdprov_dm       \n";
    l_query += "       , uldm.cdnazi         as cdnazi_dm       \n";
    l_query += "       , nazi_dm.cdnazi_m    as cdnazi_m_dm     \n";
    l_query += "       , nazi_dm.dsnazi      as dsnazi_dm       \n";
    l_query += "       , prov_dm.cdprov_m    as cdprov_m_dm     \n";
    l_query += "       , prov_dm.dsprov      as dsprov_dm       \n";
    l_query += "       , test.ragcog_de                         \n";
    l_query += "       , test.indiri_de                         \n";
    l_query += "       , test.cap_de                            \n";
    l_query += "       , test.comune_de                         \n";
    l_query += "       , test.cdprov_de                         \n";
    l_query += "       , test.cdstat_de                         \n";
    l_query += "       , nazi_de.cdnazi_m     as cdnazi_m_de    \n";
    l_query += "       , nazi_de.dsnazi       as dsnazi_de      \n";
    l_query += "       , prov_de.cdprov_m     as cdprov_m_de    \n";
    l_query += "       , prov_de.dsprov       as dsprov_de      \n";
    if (is_sybase || is_postgresql) {

        l_query += "    from pgmr.web_ord_test      test                                                      \n";
        l_query += "              left outer join pgmr.nazioni       nazi_de on test.cdstat_de = nazi_de.cdnazi    \n";
        l_query += "              left outer join pgmr.province      prov_de on test.cdprov_de = prov_de.cdprov    \n";
        l_query += "              left outer join pgmr.unitalocali   uldm    on test.cduldm    = uldm.cdunil       \n";
        l_query += "              left outer join pgmr.nazioni       nazi_dm on uldm.cdnazi    = nazi_dm.cdnazi    \n";
        l_query += "              left outer join pgmr.province      prov_dm on uldm.cdprov    = prov_dm.cdprov    \n";
        l_query += "   where test.tkordi = " + tkordi + "                                                              \n";

    } else {
        throw new Exception("DB NON Previsto !!");
    }

    pstm = m_connection.prepareStatement(l_query);

    rs = pstm.executeQuery();

    if (rs != null && rs.next()) {
        if (rs.getObject("cduldm") != null)                     cduldm = rs.getString("cduldm");
        if (rs.getObject("cdunil_m_dm") != null)                cdunil_m_dm = rs.getString("cdunil_m_dm");
        if (rs.getObject("dsunil_dm") != null)                  dsunil_dm = rs.getString("dsunil_dm");
        if (rs.getObject("indiri_dm") != null)                  indiri_dm = rs.getString("indiri_dm");
        if (rs.getObject("cap_dm") != null)                     cap_dm = rs.getString("cap_dm");
        if (rs.getObject("comune_dm") != null)                  comune_dm = rs.getString("comune_dm");
        if (rs.getObject("cdprov_dm") != null)                  cdprov_dm = rs.getString("cdprov_dm");
        if (rs.getObject("cdnazi_dm") != null)                  cdnazi_dm = rs.getString("cdnazi_dm");
        if (rs.getObject("cdprov_m_dm") != null)                cdprov_m_dm = rs.getString("cdprov_m_dm");
        if (rs.getObject("dsprov_dm") != null)                  dsprov_dm = rs.getString("dsprov_dm");
        if (rs.getObject("cdnazi_m_dm") != null)                cdnazi_m_dm = rs.getString("cdnazi_m_dm");
        if (rs.getObject("dsnazi_dm") != null)                  dsnazi_dm = rs.getString("dsnazi_dm");
        if (rs.getObject("ragcog_de") != null)                  ragcog_de = rs.getString("ragcog_de");
        if (rs.getObject("indiri_de") != null)                  indiri_de = rs.getString("indiri_de");
        if (rs.getObject("cap_de") != null)                     cap_de = rs.getString("cap_de");        
        if (rs.getObject("comune_de") != null)                  comune_de = rs.getString("comune_de");
        if (rs.getObject("cdprov_de") != null)                  cdprov_de = rs.getString("cdprov_de");
        if (rs.getObject("cdstat_de") != null)                  cdstat_de = rs.getString("cdstat_de");
        if (rs.getObject("cdprov_m_de") != null)                cdprov_m_de = rs.getString("cdprov_m_de");
        if (rs.getObject("dsprov_de") != null)                  dsprov_de = rs.getString("dsprov_de");
        if (rs.getObject("cdnazi_m_de") != null)                cdnazi_m_de = rs.getString("cdnazi_m_de");
        if (rs.getObject("dsnazi_de") != null)                  dsnazi_de = rs.getString("dsnazi_de");
        
        filiale_dm = "";
        if (cdunil_m_dm.indexOf("DM~") == 0){
            filiale_dm = cdunil_m_dm.replace("DM~"+ cdente + "_", "");
        }
        fgfiliale_dm_variata = "N";
        if (   !dsunil_dm.trim().equals(ragcog_de.trim())
            || !indiri_dm.trim().equals(indiri_de.trim())  
            || !cap_dm.trim().equals(cap_de.trim())  
            || !comune_dm.trim().equals(comune_de.trim())  
            || !cdprov_dm.trim().equals(cdprov_de.trim())  
            || !cdnazi_m_dm.trim().equals(cdnazi_m_de.trim())  
           ){
            fgfiliale_dm_variata = "S";
        }
        
    }
    pstm.close();
    pstm = null;
    
    String label_nuova_dm  = atk_dwlingua.getLabel ("", cdling, "nuova_dm", "", null, "SHOP");
    
    if (!ragcog_de.equals("")) {
        ls_itestazione_mail_ric_ordwebdsdest_merce = "";
        if (!fgfiliale_dm_variata.equals("S")){
            if (!filiale_dm.equals("")){
                ls_itestazione_mail_ric_ordwebdsdest_merce += html.text(filiale_dm) + " - ";
            }
        } else {
            ls_itestazione_mail_ric_ordwebdsdest_merce += "<strong>"+ html.text(label_nuova_dm) + "</strong>" + " - ";
        }
        ls_itestazione_mail_ric_ordwebdsdest_merce += html.text(ragcog_de) + "<br/>";
        ls_itestazione_mail_ric_ordwebdsdest_merce += html.text(indiri_de) + "<br/>";
        ls_itestazione_mail_ric_ordwebdsdest_merce += html.text(cap_de) + " " + html.text(comune_de) + "(" + cdprov_de + ")" + "<br/>"; //EAR - 20091014 - Vistosi non usa la tabella provincie
        ls_itestazione_mail_ric_ordwebdsdest_merce += html.text(dsnazi_de) + "<br/>";
    } else if (!cduldm.equals("")) {
        ls_itestazione_mail_ric_ordwebdsdest_merce = "";
        if (!filiale_dm.equals("")){
            ls_itestazione_mail_ric_ordwebdsdest_merce += html.text(filiale_dm) + " - ";
        }
        ls_itestazione_mail_ric_ordwebdsdest_merce += html.text(ragcog_sl) + "<br/>";
        ls_itestazione_mail_ric_ordwebdsdest_merce += html.text(indiri_dm) + "<br/>";
        ls_itestazione_mail_ric_ordwebdsdest_merce += html.text(cap_dm) + " " + html.text(comune_dm) + "(" + cdprov_dm + ")" + "<br/>"; //EAR - 20091014 - Vistosi non usa la tabella provincie
        ls_itestazione_mail_ric_ordwebdsdest_merce += html.text(dsnazi_dm) + "<br/>";
    } else if (!cdulsl.equals("")) {
        ls_itestazione_mail_ric_ordwebdsdest_merce = "";
        ls_itestazione_mail_ric_ordwebdsdest_merce += html.text(ragcog_sl) + "<br/>";
        ls_itestazione_mail_ric_ordwebdsdest_merce += html.text(indiri_sl) + "<br/>";
        ls_itestazione_mail_ric_ordwebdsdest_merce += html.text(cap_sl) + " " + html.text(comune_sl) + "(" + cdprov_sl + ")" + "<br/>"; //EAR - 20091014 - Vistosi non usa la tabella provincie
        ls_itestazione_mail_ric_ordwebdsdest_merce += html.text(dsnazi_sl) + "<br/>";
    }
    
    //Agente di competenza
    String cdagen_m = "";
    String dsagen = "";
    
    if (!cdagen.equals("")) {

        l_query = "";
        l_query += "  select agen.dsagen                 \n";
        l_query += "       , agen.cdagen_m               \n";
        l_query += "    from pgmr.archagen agen          \n";
        l_query += "   where agen.cdazie = '" + cdazie + "'  \n";
        l_query += "     and agen.cdagen = '" + cdagen + "'  \n";

        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        if (rs != null && rs.next()) {
            if (rs.getObject("cdagen_m") != null)         cdagen_m = rs.getString("cdagen_m");
            if (rs.getObject("dsagen") != null)           dsagen = rs.getString("dsagen");
        }
        pstm.close();
        pstm = null;
    }
    
    
    //Dati intestatario ordine
    
    String ragcog = "";
    String cdclie_m = "";
    String notapagame = "";
    String fgriba = "";

    if (!tkclie.equals("")) {

        l_query = "";
        l_query += "  select ente.ragcog                 \n";
        l_query += "       , clie.cdclie_m               \n";
        l_query += "       , clie.notapagame             \n";
        l_query += "    from pgmr.archclie clie          \n";
        l_query += "       , pgmr.archenti ente          \n";
        l_query += "   where ente.cdente = clie.cdente   \n";
        l_query += "     and clie.cdazie = '" + cdazie + "'  \n";
        l_query += "     and clie.tkclie = '" + tkclie + "'  \n";

        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        if (rs != null && rs.next()) {
            if (rs.getObject("ragcog") != null)                 ragcog = rs.getString("ragcog");
            if (rs.getObject("cdclie_m") != null)               cdclie_m = rs.getString("cdclie_m");
            if (rs.getObject("notapagame") != null)             notapagame = rs.getString("notapagame");
        }
        pstm.close();
        pstm = null;

    }

    if (!notapagame.equals("")) {
        fgriba = "N";
    } else {
        fgriba = "S";
    }
    
    //Creo il subutente
    String dssubutente = "";

    if (tksubutente > 0) {

        l_query = "";
        l_query += " SELECT ute.tkutente                \n";
        l_query += "      , ute.dsutente                \n";
        l_query += "      , ute.email                   \n";
        l_query += "   FROM pgmr.ep_utente ute         \n";
        l_query += "  where ute.tkutente = " + tksubutente + " \n";

        pstm = m_connection.prepareStatement(l_query);

        rs = pstm.executeQuery();

        if (rs != null && rs.next()) {

            if (rs.getObject("dsutente") != null) {
                dssubutente = rs.getString("dsutente");
            }
        }
        pstm.close();
        pstm = null;

    }
    
    String annotazione_cli = of_getWeb_ord_test_annotazione(tkordi, TIPONOTA_CLIENTE);
    String annotazione_int = of_getWeb_ord_test_annotazione(tkordi, TIPONOTA_INTERNA).trim();
    //GESTIONE LABEL
    String riepilogo_ordine_mail  = atk_dwlingua.getLabel ("", cdling, "riepilogo.ordine", "", null, "SHOP");
    String rif_ordine_mail  = atk_dwlingua.getLabel ("", cdling, "rif.ordine", "", null, "SHOP");
    String data_ordine_mail  = atk_dwlingua.getLabel ("", cdling, "data.ordine", "", null, "SHOP");
    String ordine_inviato_mail  = atk_dwlingua.getLabel ("", cdling, "ordine.inviato", "", null, "SHOP");
    String agente_ordine_mail  = atk_dwlingua.getLabel ("", cdling, "agente.ordine", "", null, "SHOP");
    String cliente_ordine_mail  = atk_dwlingua.getLabel ("", cdling, "cliente.ordine", "", null, "SHOP");
    String destinazione_ordine_mail  = atk_dwlingua.getLabel ("", cdling, "destinazione.ordine", "", null, "SHOP");
    String nota_pagamento_mail = atk_dwlingua.getLabel ("", cdling, "nota.pagamento", "", null, "SHOP");
    String note_cliente = atk_dwlingua.getLabel ("", cdling, "note.clie", "", null, "SHOP");
    String codice_promo = atk_dwlingua.getLabel ("", cdling, "promo.code", "", null, "SHOP");
    
    if (!annotazione_int.equals("")){
        note_cliente += " *";
    }
    
    //creo il codice html
    ls_itestazione_mail_ric_ordweb = "";
    ls_itestazione_mail_ric_ordweb += "<h4>"+html.text(riepilogo_ordine_mail)+":</h4>";
    ls_itestazione_mail_ric_ordweb += "<table width=\"359\" cellpadding=\"0\" cellspacing=\"0\" class=\"parametri\">";
    ls_itestazione_mail_ric_ordweb += "<tr>";
    ls_itestazione_mail_ric_ordweb += "<th width=\"130\" align=\"left\" valign=\"top\" nowrap>"+html.text(rif_ordine_mail)+":</th>";
    ls_itestazione_mail_ric_ordweb += "<td valign=\"top\">" + cdordi + "</td>";
    ls_itestazione_mail_ric_ordweb += "</tr>";
    ls_itestazione_mail_ric_ordweb += "<tr>";
    ls_itestazione_mail_ric_ordweb += "<th width=\"130\" align=\"left\" valign=\"top\" nowrap>"+html.text(data_ordine_mail)+":</th>";
    ls_itestazione_mail_ric_ordweb += "<td valign=\"top\">" + atk_ctrl.getDate(dtordi) + "</td>";
    ls_itestazione_mail_ric_ordweb += "</tr>";

    if (!dssubutente.equals("")) {

        ls_itestazione_mail_ric_ordweb += "<tr>";
        ls_itestazione_mail_ric_ordweb += "<th width=\"130\" align=\"left\" valign=\"top\" nowrap>"+html.text(ordine_inviato_mail)+":</th>";
        ls_itestazione_mail_ric_ordweb += "<td valign=\"top\">" + html.text(dssubutente) + "</td>";
        ls_itestazione_mail_ric_ordweb += "</tr>";
    }
    if (!cdagen.equals("")) {

        ls_itestazione_mail_ric_ordweb += "<tr>";
        ls_itestazione_mail_ric_ordweb += "<th width=\"130\" align=\"left\" valign=\"top\" nowrap>"+html.text(agente_ordine_mail)+":</th>";
        ls_itestazione_mail_ric_ordweb += "<td valign=\"top\">" + cdagen_m + " - " + html.text(dsagen) + "</td>";
        ls_itestazione_mail_ric_ordweb += "</tr>";
    }
    if (!tkclie.equals("")) {

        ls_itestazione_mail_ric_ordweb += "<tr>";
        ls_itestazione_mail_ric_ordweb += "<th width=\"130\" align=\"left\" valign=\"top\" nowrap>"+html.text(cliente_ordine_mail)+":</th>";
        ls_itestazione_mail_ric_ordweb += "<td valign=\"top\">" + cdclie_m + " - " + html.text(ragcog) + "</td>";
        ls_itestazione_mail_ric_ordweb += "</tr>";
    }
    if (!ls_itestazione_mail_ric_ordwebdsdest_merce.equals("")) {

        ls_itestazione_mail_ric_ordweb += "<tr>";
        ls_itestazione_mail_ric_ordweb += "<th width=\"130\" align=\"left\" valign=\"top\" nowrap>"+html.text(destinazione_ordine_mail)+":</th>";
        ls_itestazione_mail_ric_ordweb += "<td valign=\"top\">" + ls_itestazione_mail_ric_ordwebdsdest_merce + "</td>";
        ls_itestazione_mail_ric_ordweb += "</tr>";
    }
    if (!annotazione_cli.equals("")) {

        ls_itestazione_mail_ric_ordweb += "<tr>";
        ls_itestazione_mail_ric_ordweb += "<th width=\"130\" align=\"left\" valign=\"top\" nowrap>"+html.text(note_cliente)+":</th>";
        ls_itestazione_mail_ric_ordweb += "<td valign=\"top\">" + html.text(annotazione_cli) + "</td>";
        ls_itestazione_mail_ric_ordweb += "</tr>";
    } else if (!annotazione_int.equals("")) {

        ls_itestazione_mail_ric_ordweb += "<tr>";
        ls_itestazione_mail_ric_ordweb += "<th width=\"130\" align=\"left\" valign=\"top\" nowrap>"+html.text(note_cliente)+":</th>";
        ls_itestazione_mail_ric_ordweb += "<td valign=\"top\">&nbsp;</td>";
        ls_itestazione_mail_ric_ordweb += "</tr>";
        
    }
    if (!cdpromo_m.equals("")) {

        ls_itestazione_mail_ric_ordweb += "<tr>";
        ls_itestazione_mail_ric_ordweb += "<th width=\"130\" align=\"left\" valign=\"top\" nowrap>"+html.text(codice_promo)+":</th>";
        ls_itestazione_mail_ric_ordweb += "<td valign=\"top\">" + html.text(cdpromo_m) + "</td>";
        ls_itestazione_mail_ric_ordweb += "</tr>";
    }
    ls_itestazione_mail_ric_ordweb += "</table>";
    ls_itestazione_mail_ric_ordweb += "<br/>";
    
    //INSERISCO LE NOTE PAGAMENTO DEL CLIENTE

    l_query = "";
    l_query += "  select clie.notapagame           \n";
    l_query += "   from pgmr.web_ord_test ordt    \n";
    l_query += "      , pgmr.archclie     clie    \n";
    l_query += "  where ordt.tkordi = " + tkordi + "  \n";
    l_query += "    and clie.tkclie = ordt.tkclie \n";
    l_query += "    and clie.cdazie = ordt.cdazie \n";

    pstm = m_connection.prepareStatement(l_query);

    rs = pstm.executeQuery();

    if (rs != null && rs.next()) {
        if (rs.getObject("notapagame") != null) {
            notapagame = rs.getString("notapagame");
        }
    }

    pstm.close();
    pstm = null;

    //notapagame = "note di prova";

    if (!notapagame.equals("")) {
        fgriba = "N";
    } else {
        fgriba = "S";
    }

    String ls_tbl_order_note = "";
    
    if (!fgriba.equals("S")) {
        ls_tbl_order_note += "<div class=\"foot\" style=\"border: 1px solid #CCCCCC; padding:3px;\">";
        ls_tbl_order_note += "<p style=\"font-size: 9px; font-weight: normal;\">";
        ls_tbl_order_note += "<span style=\"font-weight: bold; color: #b8b8b8;\">";
        ls_tbl_order_note += ""+html.text(nota_pagamento_mail)+":<br/>";
        ls_tbl_order_note += "</span>";
        ls_tbl_order_note += html.text(notapagame);
        ls_tbl_order_note += "</p></div>";
    }
    
    //link tracking ordine
    String ep_portal_url    = ep_costanti.getCostvalue("ep.portal_url");
    ep_portal_url = of_cambiaURLLingua(ep_portal_url, cdling);
    Str_crm_credential lstr_crm_credential = new Str_crm_credential();
    
    String ls_arts_url = ep_portal_url + "epDesktop.jsp?nextPage=rlogin/eprogen.html?nextPage=epRep_controllo_tracking_ordven.jsp&openMethod=iframe&nextPageTitle=Tracking&cdling="+ cdling;
    
        
    String ls_url_portal_keycode = ls_arts_url;
    
    int count_utente_is_subcliente = sql_int("select count(*) from pgmr.ep_utente where tkutente = "+ tkutente +" and tksubutente is not null");
    
    if (tkutente_clie > 0 && count_utente_is_subcliente == 0){
      
      lstr_crm_credential.tkutente = tkutente_clie;
      
      lstr_crm_credential.url = ls_arts_url;
      if (crm_credential.of_getUrlKeycode(lstr_crm_credential) == 1)      crm_credential.m_connection.commit();
      ls_url_portal_keycode = ep_portal_url + lstr_crm_credential.keycode_url;

    }
    //fine link tracking ordine
    
    String ls_fattprof_url = ep_portal_url + "epDesktop.jsp?nextPage=rlogin/eprogen.html?nextPage=epRep_controllo_fattproforma_par.jsp?params=OW~"+ tkordi +"&openMethod=iframe&nextPageTitle=Fatture proforma&cdling="+ cdling;
    
    String ls_url_portal_fattprof_keycode = ls_fattprof_url;
    
    if (tkutente_clie > 0){
      
      lstr_crm_credential.tkutente = tkutente_clie;
      
      lstr_crm_credential.url = ls_fattprof_url;
      if (crm_credential.of_getUrlKeycode(lstr_crm_credential) == 1)      crm_credential.m_connection.commit();
      ls_url_portal_fattprof_keycode = ep_portal_url + lstr_crm_credential.keycode_url;

    }

    
    String ls_atk_mail = "atkim@ateikon.com";
        
    ao_map.put("${ov_ricez.dati_riepilogo_ordine}", ls_itestazione_mail_ric_ordweb);
    ao_map.put("${ov_ricez.dati_nota_pagamento}", ls_tbl_order_note);
    ao_map.put("${ov_ricez.numero_ordine}", cdordi);
    ao_map.put("${ov_ricez.spettabile}", ls_spettabile);
    ao_map.put("${ov_ricez.atk_mail}", ls_atk_mail);
    ao_map.put("${ov_tracking.url_keycode}", ls_url_portal_keycode);
    ao_map.put("${ow_fattprof.url_keycode}", ls_url_portal_fattprof_keycode);

    ao_map = of_setPar_Ep_utente(ao_map, tkutente, cdling);
    ao_map = of_setPar_Archclie(ao_map, tkclie, cdling);
    
    ep_costanti.close();
    atk_dwlingua.close();
    f_utente.close();
    crm_credential.close();
    
    return ao_map;
  }
  
   //MPERUZZA 20130118
  public HashMap of_setPar_Web_ord_positito (HashMap ao_map, long tkordi , String cdling) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    ResultSet rs = null;
    String fgpromo = "";

    if (ao_map ==null){
      ao_map = new HashMap();
    }

    Atk_dwlingua atk_dwlingua = new Atk_dwlingua();
    F_utente     f_utente     = new F_utente    ();
    Mrp_arch_articoli     mrp_arch_articoli     = new Mrp_arch_articoli    ();

    Atk_ctrl atk_ctrl = new Atk_ctrl();

    HTML html = new HTML();

    setProfilo((Atk_sql) atk_dwlingua);
    setProfilo((Atk_sql) f_utente    );
    setProfilo((Atk_sql) mrp_arch_articoli    );
    
    //implemento il dettaglio ordine
    boolean lb_link = false;
    com.ateikon.structure.Str_html str_html = null;
    com.ateikon.common.Mrp_arch_stato mrp_arch_stato = new Mrp_arch_stato();

    Costanti_comm costanti_comm = new Costanti_comm();
    Ep_costanti ep_costanti = new Ep_costanti();

    setProfilo((Atk_sql) costanti_comm);
    setProfilo((Atk_sql) mrp_arch_stato);
    setProfilo((Atk_sql) ep_costanti);
    
    String ep_portal_url    = ep_costanti.getCostvalue("ep.portal_url");
    ep_portal_url = of_cambiaURLLingua(ep_portal_url, cdling);
    String ep_shop_url    = ep_costanti.getCostvalue("ep.shop_url");
    ep_shop_url = of_cambiaURLLingua(ep_shop_url, s_cdling);
    String ep_eprogen_url    = ep_costanti.getCostvalue("ep.eprogen_url");
    ep_eprogen_url = of_cambiaURLLingua(ep_eprogen_url, s_cdling);
    
    if (ep_shop_url.equals("")) ep_shop_url = of_trasformaURL(ep_portal_url, "portal", "shop");
    if (ep_eprogen_url.equals("")) ep_eprogen_url = of_trasformaURL(ep_portal_url, "portal", "eprogen");
    
    
    //RECUPERO LA TIPOLOGIA D'ORDINE SE PROMO O MENO
    l_query = "";
    l_query += "  select ordt.fgpromo                          \n";
    l_query += "  from pgmr.web_ord_positito  ordt             \n";
    l_query += "  where ordt.tkordi   = " + tkordi + "         \n";

    pstm = m_connection.prepareStatement(l_query);

    rs = pstm.executeQuery();

    if (rs != null && rs.next()) {
        if (rs.getObject("fgpromo") != null)                     fgpromo = rs.getString("fgpromo");
    }

    String lwm_orddimentav = costanti_comm.getCostvalue("lwm_orddimentav");


    String ls_lingua = cdling;

    l_query = " SELECT cdling_portale FROM pgmr.ep_lingua\n  " +
              "  WHERE cdling = '" + cdling + "'";

    String cdling_portale = sql_String(l_query);


    java.text.NumberFormat numFormat = java.text.NumberFormat.getInstance(java.util.Locale.ITALY);
    numFormat.setGroupingUsed(false);
    numFormat.setMaximumFractionDigits(2);
    numFormat.setMinimumFractionDigits(0);

    java.text.NumberFormat qtaFormat = java.text.NumberFormat.getInstance(java.util.Locale.ITALY);
    qtaFormat.setGroupingUsed(true);
    qtaFormat.setMaximumFractionDigits(0);
    qtaFormat.setMinimumFractionDigits(0);


    java.text.NumberFormat przFormat = java.text.NumberFormat.getInstance(java.util.Locale.ITALY);
    przFormat.setGroupingUsed(true);
    przFormat.setMaximumFractionDigits(2);
    przFormat.setMinimumFractionDigits(2);

    java.text.NumberFormat dimFormat = java.text.NumberFormat.getInstance(java.util.Locale.ITALY);
    dimFormat.setGroupingUsed(true);
    dimFormat.setMaximumFractionDigits(3);
    dimFormat.setMinimumFractionDigits(0);

    String ls_tbl_order_details = "";
    
    //MODIFICA PER VISUALIZZAZIONE LEGENDA SU OUTLOOK
    String ricezione_offerta_mail = atk_dwlingua.getLabel ("", cdling, "cart.row.offer", "", null, "SHOP");
    String offerte_ordine_mail = atk_dwlingua.getLabel ("", cdling, "offerte.ordine", "", null, "SHOP");
    String legenda_ordine_mail = atk_dwlingua.getLabel ("", cdling, "legenda.ordine", "", null, "SHOP");
    String note_cliente = atk_dwlingua.getLabel ("", cdling, "note.clie", "", null, "SHOP");
    
    ls_tbl_order_details    += "<div id=\"stati-cnt\" class=\"clearfix\">                                         ";
    ls_tbl_order_details    += "    <div class=\"head-legenda\"  style=\"padding-bottom:15px;\">"+ legenda_ordine_mail +":</div>                              "
                            + "         <table class=\"dettaglio\" border=\"0\">                                  "
                            + "             <tbody><tr>                                                           ";

    ResultSet rs_stati = mrp_arch_stato.getList();

    while (rs_stati != null && rs_stati.next()) {
        String vist_rgb_r = "";
        String vist_rgb_g = "";
        String vist_rgb_b = "";
        String vist_dsstato = "";
        String vist_dsstato_en = "";
        
        if(rs_stati.getObject("vist_rgb_r") != null)    vist_rgb_r = rs_stati.getString("vist_rgb_r");
        if(rs_stati.getObject("vist_rgb_r") != null)    vist_rgb_r = rs_stati.getString("vist_rgb_r");
        if(rs_stati.getObject("vist_rgb_r") != null)    vist_rgb_r = rs_stati.getString("vist_rgb_r");

        if("E".equals(ls_lingua)) {
            ls_tbl_order_details += "<td  style=\"background-color: rgb(" + rs_stati.getString("vist_rgb_r") + "," + rs_stati.getString("vist_rgb_g") + "," + rs_stati.getString("vist_rgb_b") + "); margin:0 5px 0 0; padding: 0px; border:0px none; height:16px; width:16px;\" ></td><td style=\"padding:0 10px 0 5px;  font-size:12px; height:16px; margin: 0px; border:0px none;\">" + html.text(rs_stati.getString("dsstato_en")) + "</td>";
        }else if("I".equals(ls_lingua)) {
            ls_tbl_order_details += "<td  style=\"background-color: rgb(" + rs_stati.getString("vist_rgb_r") + "," + rs_stati.getString("vist_rgb_g") + "," + rs_stati.getString("vist_rgb_b") + "); margin:0 5px 0 0; padding: 0px; border:0px none; height:16px; width:16px;\" ></td><td style=\"padding:0 10px 0 5px;  font-size:12px; height:16px; margin: 0px; border:0px none;\">" + html.text(rs_stati.getString("dsstato")) + "</td>";
        }else if("F".equals(ls_lingua)) {
            ls_tbl_order_details += "<td  style=\"background-color: rgb(" + rs_stati.getString("vist_rgb_r") + "," + rs_stati.getString("vist_rgb_g") + "," + rs_stati.getString("vist_rgb_b") + "); margin:0 5px 0 0; padding: 0px; border:0px none; height:16px; width:16px;\" ></td><td style=\"padding:0 10px 0 5px;  font-size:12px; height:16px; margin: 0px; border:0px none;\">" + html.text(rs_stati.getString("dsstato_fr")) + "</td>";
        }else if("D".equals(ls_lingua)) {
            ls_tbl_order_details += "<td  style=\"background-color: rgb(" + rs_stati.getString("vist_rgb_r") + "," + rs_stati.getString("vist_rgb_g") + "," + rs_stati.getString("vist_rgb_b") + "); margin:0 5px 0 0; padding: 0px; border:0px none; height:16px; width:16px;\" ></td><td style=\"padding:0 10px 0 5px;  font-size:12px; height:16px; margin: 0px; border:0px none;\">" + html.text(rs_stati.getString("dsstato_de")) + "</td>";
        }else if("S".equals(ls_lingua)) {
            ls_tbl_order_details += "<td  style=\"background-color: rgb(" + rs_stati.getString("vist_rgb_r") + "," + rs_stati.getString("vist_rgb_g") + "," + rs_stati.getString("vist_rgb_b") + "); margin:0 5px 0 0; padding: 0px; border:0px none; height:16px; width:16px;\" ></td><td style=\"padding:0 10px 0 5px;  font-size:12px; height:16px; margin: 0px; border:0px none;\">" + html.text(rs_stati.getString("dsstato_es")) + "</td>";
        }else if("R".equals(ls_lingua)) {
            ls_tbl_order_details += "<td  style=\"background-color: rgb(" + rs_stati.getString("vist_rgb_r") + "," + rs_stati.getString("vist_rgb_g") + "," + rs_stati.getString("vist_rgb_b") + "); margin:0 5px 0 0; padding: 0px; border:0px none; height:16px; width:16px;\" ></td><td style=\"padding:0 10px 0 5px;  font-size:12px; height:16px; margin: 0px; border:0px none;\">" + html.text(rs_stati.getString("dsstato_ru")) + "</td>";
        } else {
            ls_tbl_order_details += "<td  style=\"background-color: rgb(" + rs_stati.getString("vist_rgb_r") + "," + rs_stati.getString("vist_rgb_g") + "," + rs_stati.getString("vist_rgb_b") + "); margin:0 5px 0 0; padding: 0px; border:0px none; height:16px; width:16px;\" ></td><td style=\"padding:0 10px 0 5px;  font-size:12px; height:16px; margin: 0px; border:0px none;\">" + html.text(rs_stati.getString("dsstato")) + "</td>";
        }
    }

    //ls_tbl_order_details += "<td style=\"background-color: rgb(51,153,51); margin:0 5px 0 0; padding: 0px; border:0px none; height:16px; width:16px;\" ></td><td style=\"padding:0 10px 0 5px;  font-size:12px; height:16px; margin: 0px; border:0px none;\">"+html.text(offerte_ordine_mail)+"</td>";

    ls_tbl_order_details += "    </tr></tbody>";
    ls_tbl_order_details += "  </table><br/><br/><br/>";
    ls_tbl_order_details += "</div>";


    if ("S".equals(fgpromo)) {
        ls_tbl_order_details += "<h2>"+html.text(ricezione_offerta_mail)+"</h2>";
    }

    String cdordi = "";
    String tkclie = "";
    double scocas = 0;
    String isscontiriga = "";
    String cdfisc = "";
    long tkmaga = 0;


    l_query = "";
    l_query += " select tkordi                  \n";
    l_query += "      , cdstato                 \n";
    l_query += "      , cdordi                  \n";
    l_query += "      , numord                  \n";
    l_query += "      , isscontiriga            \n";
    l_query += "      , scocas                  \n";
    l_query += "      , tkclie                  \n";
    l_query += "      , cdfisc                  \n";
    l_query += "      , tkmaga                  \n";
    l_query += "   from pgmr.web_ord_test       \n";
    l_query += "   where tkordi = ?             \n";

    pstm = m_connection.prepareStatement(l_query);

    ind = 1;
    pstm.setLong(ind++, tkordi);

    rs = pstm.executeQuery();

    if (rs != null && rs.next()) {

        if (rs.getObject("cdordi") != null) {
            cdordi = rs.getString("cdordi");
        }
        if (rs.getObject("scocas") != null) {
            scocas = rs.getDouble("scocas");
        }
        if (rs.getObject("isscontiriga") != null) {
            isscontiriga = rs.getString("isscontiriga");
        }
        if (rs.getObject("tkclie") != null) {
            tkclie = rs.getString("tkclie");
        }
        if (rs.getObject("cdfisc") != null) {
            cdfisc = rs.getString("cdfisc");
        }
        if (rs.getObject("tkmaga") != null) {
            tkmaga = rs.getLong("tkmaga");
        }
    }
    pstm.close();
    pstm = null;
    // --- Verifico se ci sono righe senza pacco
    String suff_vist_famiglia = of_suff_lingua("vist_famiglia", cdling);
    String suff_vist_var1 = of_suff_lingua("vist_var1", cdling);
    String suff_vist_var2 = of_suff_lingua("vist_var2", cdling);
    String suff_vist_var3 = of_suff_lingua("vist_var3", cdling);
    String suff_vist_colori_vetro = of_suff_lingua("vist_colori_vetro", cdling);
    String suff_vist_finit_vetro = of_suff_lingua("vist_finit_vetro", cdling);
    String suff_vist_finit_mont = of_suff_lingua("vist_finit_mont", cdling);
    String suff_vist_elettrificazione = of_suff_lingua("vist_elettrificazioni", cdling);
    

    l_query = "";
    l_query += " select art.cdarti                                                                  \n";
    l_query += "      , art.cdartm                                                                  \n";
    l_query += "      , art.cdbarcode                                                               \n";
    l_query += "      , art.nrpeso_l                                                                \n";
    l_query += "      , art.nrpeso_n                                                                \n";
    l_query += "      , art.vlunlt                                                                  \n";
    l_query += "      , tip.cdvisttp                                                                \n";
    l_query += "      , tip.cdvisttp_m                                                              \n";
    l_query += "      , fam.cdvistfam                                                               \n";
    l_query += "      , fam.cdvistfam_m                                                             \n";
    l_query += "      , fam.dsvistfam"+suff_vist_famiglia+"                                         \n";
    l_query += "      , art.cdvistv1                                                                \n";
    l_query += "      , v1.dsextvistv1"+suff_vist_var1+"                                            \n";
    l_query += "      , art.cdvistv2                                                                \n";
    l_query += "      , v2.dsextvistv2"+suff_vist_var2+"                                            \n";
    l_query += "      , art.cdvistv3                                                                \n";
    l_query += "      , v3.dsextvistv3"+suff_vist_var3+"                                            \n";
    l_query += "      , colv.cdvistcolv                                                             \n";
    l_query += "      , colv.dsextvistcolv"+suff_vist_colori_vetro+"                                \n";
    l_query += "      , art.cdvistfinm                                                              \n";
    l_query += "      , finv.cdvistfinv                                                             \n";
    l_query += "      , finv.dsextvistfinv"+suff_vist_finit_vetro+"                                 \n";
    l_query += "      , finm.dsextvistfinm"+suff_vist_finit_mont+"                                  \n";
    l_query += "      , elet.cdvistelet                                                             \n";
    l_query += "      , art.fgweb                                                                   \n";
    l_query += "      , art.vist_filedis                                                            \n";
    l_query += "      , elet.dsextvistelet"+suff_vist_elettrificazione+"                            \n";
    l_query += "      , art.cdrepa                                                                  \n";
    l_query += "      , art.cdclas_a                                                                \n";
    l_query += "      , op.tkposi                                                                   \n";
    l_query += "      , op.dimena                                                                   \n";
    l_query += "      , op.dimenb                                                                   \n";
    l_query += "      , op.dimenc                                                                   \n";
    l_query += "      , op.ncolli                                                                   \n";
    l_query += "      , op.qtatot                                                                   \n";
    l_query += "      , op.impuni                                                                   \n";
    l_query += "      , op.impuninetto                                                              \n";
    l_query += "      , op.importonettoriga                                                         \n";
    l_query += "      , op.scont1                                                                   \n";
    l_query += "      , op.scont2                                                                   \n";
    l_query += "      , op.scont3                                                                   \n";
    l_query += "      , op.scont4                                                                   \n";
    l_query += "      , op.scrap1                                                                   \n";
    l_query += "      , op.scrap2                                                                   \n";
    l_query += "      , op.nrposi                                                                   \n";
    l_query += "      , op.fg_annulla_sconti                                                        \n";
    l_query += "      , op.cdunim                                                                   \n";
    l_query += "      , op.cdtins                                                                   \n";
    l_query += "      , op.cdartirif                                                                \n";
    l_query += "      , mis.dsunim                                                                  \n";
    l_query += "      , mis.cdunim_m                                                                \n";
    l_query += "      , giac.dtprdisp                                                               \n";
    l_query += "      , giac.qtadisp                                                                \n";
    if (is_oracle){
        throw new Exception("DB non supportato");
    } else if (is_sybase || is_postgresql) {
        l_query += "   from pgmr.web_ord_positito  op                                                   \n";
        l_query += "       , pgmr.mrp_arch_articoli art                                  \n";
        l_query += "        LEFT OUTER JOIN  pgmr.unimisura         mis ON art.cdunim_1   = mis.cdunim   \n";
        l_query += "        LEFT OUTER JOIN  pgmr.vist_tipi         tip ON art.cdvisttp   = tip.cdvisttp                \n";
        l_query += "        LEFT OUTER JOIN  pgmr.vist_famiglia     fam ON art.cdvistfam  = fam.cdvistfam               \n";
        l_query += "        LEFT OUTER JOIN  pgmr.vist_var1         v1  ON art.cdvistv1   = v1.cdvistv1                 \n";
        l_query += "        LEFT OUTER JOIN  pgmr.vist_var2         v2  ON art.cdvistv2   = v2.cdvistv2                 \n";
        l_query += "        LEFT OUTER JOIN  pgmr.vist_var3         v3  ON art.cdvistv3   = v3.cdvistv3                 \n";
        l_query += "        LEFT OUTER JOIN  pgmr.vist_colori_vetro  colv ON art.cdvistcolv   = colv.cdvistcolv          \n";
        l_query += "        LEFT OUTER JOIN  pgmr.vist_finit_vetro  finv ON art.cdvistfinv   = finv.cdvistfinv          \n";
        l_query += "        LEFT OUTER JOIN  pgmr.vist_finit_mont   finm ON art.cdvistfinm   = finm.cdvistfinm          \n";
        l_query += "        LEFT OUTER JOIN  pgmr.vist_elettrificazioni  elet ON art.cdvistelet   = elet.cdvistelet     \n";
        l_query += "        LEFT OUTER JOIN  pgmr.mrp_file_giacenza giac ON art.cdarti = giac.cdarti  AND  giac.cdvar = 'STD'  AND  giac.tkmaga =   " + tkmaga + "    \n";
        l_query += "  where op.tkordi = " + tkordi + "                                                      \n";
        l_query += "    and art.cdarti = op.cdarti                                                      \n";
        l_query += "    and art.cdarti = op.cdarti                                                      \n";
        l_query += "    and op.tkposi not in( select matr.tkposi                                        \n";
        l_query += "                            from pgmr.web_ord_posi_matr matr                        \n";
        l_query += "                           where matr.tkordi = " + tkordi + "                           \n";
        l_query += "                             )                                                      \n";
    }
    l_query += "  order by op.nrposi                                                                \n";

    pstm = m_connection.prepareStatement(l_query);

    rs = pstm.executeQuery();

    
    String modello_mail = atk_dwlingua.getLabel ("", cdling, "model", "", null, "SHOP");
    String varianti_mail = atk_dwlingua.getLabel ("", cdling, "varianti", "", null, "SHOP");
    String vetro_mail = atk_dwlingua.getLabel ("", cdling, "col.vetro", "", null, "SHOP");
    String montatura_mail = atk_dwlingua.getLabel ("", cdling, "fin.mont", "", null, "SHOP");
    String elet_mail = atk_dwlingua.getLabel ("", cdling, "elettr.short", "", null, "SHOP");
    String pesolordo_mail = atk_dwlingua.getLabel ("", cdling, "peso.lordo", "", null, "SHOP");
    String pesonetto_mail = atk_dwlingua.getLabel ("", cdling, "peso.netto", "", null, "SHOP");
    String volume_mail = atk_dwlingua.getLabel ("", cdling, "volume", "", null, "SHOP");
    String codicearticolo_mail = atk_dwlingua.getLabel ("", cdling, "art.cdartm", "", null, "SHOP");
    String prezzolistino_mail = atk_dwlingua.getLabel ("", cdling, "unit.price.lis", "", null, "SHOP");
    String sconto_mail = atk_dwlingua.getLabel ("", cdling, "sconto", "", null, "SHOP");
    String prezzo_mail = atk_dwlingua.getLabel ("", cdling, "unit.price.net", "", null, "SHOP");
    String unitario_mail = atk_dwlingua.getLabel ("", cdling, "unitario", "", null, "SHOP");
    String netto_mail = atk_dwlingua.getLabel ("", cdling, "netto", "", null, "SHOP");
    String prezzototalenetto_mail = atk_dwlingua.getLabel ("", cdling, "subtotale", "", null, "SHOP");
    String qtdatadisponibilita_mail = atk_dwlingua.getLabel ("", cdling, "qtdt.prox.disp", "", null, "SHOP");
    
    
    String totale_ordine_mail = atk_dwlingua.getLabel ("", cdling, "totale.ordine", "", null, "SHOP");
    String iva_esclusa_mail = atk_dwlingua.getLabel ("", cdling, "iva.esclusa", "", null, "SHOP");
    String spese_trasporto_mail = atk_dwlingua.getLabel ("", cdling, "spese.trasporto", "", null, "SHOP");
    String nota_ordine_mail = atk_dwlingua.getLabel ("", cdling, "nota.ordine", "", null, "SHOP");
    String prox_disp_mail = atk_dwlingua.getLabel ("", cdling, "prox.disp", "", null, "SHOP");
    String quantita_mail = atk_dwlingua.getLabel ("", cdling, "positito.qta", "", null, "SHOP");

    if (rs != null && rs.next()) {

       int tot_cols = 0;

       ls_tbl_order_details += "<table border=\"0\" cellpadding=\"4\" cellspacing=\"0\" class=\"dettaglio\">";
       ls_tbl_order_details += "<tr>";
        if (lb_link) {
           ls_tbl_order_details += "<th>&nbsp;</th>";
           ls_tbl_order_details += "<th>&nbsp;</th>";
        }
       ls_tbl_order_details += "<th align=\"left\" valign=\"top\"><span class=\"al\">"+html.text(modello_mail)+"</span></th>";
        tot_cols++;
       ls_tbl_order_details += "<th width=\"80\" class=\"al\" valign=\"top\">"+html.text(varianti_mail)+"</th>";
        tot_cols++;
       ls_tbl_order_details += "<th class=\"al\" valign=\"top\">"+html.text(vetro_mail)+"</th>";
        tot_cols++;
       ls_tbl_order_details += "<th class=\"al\" valign=\"top\">"+html.text(montatura_mail)+"</th>";
        tot_cols++;
       ls_tbl_order_details += "<th class=\"al\" valign=\"top\">"+html.text(elet_mail)+"</th>";
        tot_cols++;
       ls_tbl_order_details += "<th width=\"60\" class=\"al\" valign=\"top\">"+html.text(pesolordo_mail)+"</th>";
        tot_cols++;
       ls_tbl_order_details += "<th width=\"60\" class=\"al\" valign=\"top\">"+html.text(pesonetto_mail)+"</th>";
        tot_cols++;
       ls_tbl_order_details += "<th class=\"al\" valign=\"top\">"+html.text(volume_mail)+"</th>";
        tot_cols++;
       ls_tbl_order_details += "<th class=\"al\" valign=\"top\">"+html.text(codicearticolo_mail)+"</th>";
        tot_cols++;

        for (int i = 0; i < 5; i++) {

            if (lwm_orddimentav.indexOf("A") == i) {
               ls_tbl_order_details += "<th class=\"ar\" valign=\"top\">Lung</th>";
                tot_cols++;
            } else if (lwm_orddimentav.indexOf("B") == i) {
               ls_tbl_order_details += "<th class=\"ar\" valign=\"top\">Larg</th>";
                tot_cols++;
            } else if (lwm_orddimentav.indexOf("C") == i) {
               ls_tbl_order_details += "<th class=\"ar\" valign=\"top\">Spess</th>";
                tot_cols++;
            } else if (lwm_orddimentav.indexOf("D") == i) {
               ls_tbl_order_details += "<th class=\"ar\" valign=\"top\">Colli</th>";
                tot_cols++;
            } else if (lwm_orddimentav.indexOf("Q") == i) {
               ls_tbl_order_details += "<th class=\"ar\" valign=\"top\">"+html.text(quantita_mail)+"</th>";
                tot_cols++;
            }

        }   // FINE for (int i=0; i<3; i++){
        if (isscontiriga.equals("S")) {
           ls_tbl_order_details += "<th class=\"ar\" valign=\"top\">"+html.text(prezzolistino_mail)+"</th>";
            tot_cols++;
           ls_tbl_order_details += "<th class=\"ar\" valign=\"top\">"+html.text(sconto_mail)+"<br/>%</th>";
            tot_cols++;
        }
       ls_tbl_order_details += "<th class=\"ar\" valign=\"top\">"+html.text(prezzo_mail)+"</th>";
        tot_cols++;
       ls_tbl_order_details += "<th class=\"ar\" valign=\"top\">"+html.text(prezzototalenetto_mail)+"<br/>"+html.text(netto_mail)+"</th>";
        tot_cols++;
       ls_tbl_order_details += "<th class=\"ar\" valign=\"top\">"+html.text(qtdatadisponibilita_mail)+"</th>";
        tot_cols++;
       ls_tbl_order_details += "</tr>";


        do {

            long tkposi = 0;
            String cdarti = "";
            String cdartm = "";
            String cdunim = "";
            String cdtins = "";
            double dimena = 0;
            double dimenb = 0;
            double dimenc = 0;
            double ncolli = 0;
            double qtatot = 0;
            double impuni = 0;
            double impuninetto = 0;
            double importonettoriga = 0;
            double scont1 = 0;
            double scont2 = 0;
            double scont3 = 0;
            double scont4 = 0;
            double scrap1 = 0;
            double scrap2 = 0;
            double nrpeso_l = 0;
            double nrpeso_n = 0;
            double vlunlt = 0;
            String cdvisttp = "";
            String cdvisttp_m = "";
            String cdvistfam = "";
            String cdvistfam_m = "";
            String dsvistfam = "";
            String dsvistfam_eng = "";
            String dsvistfam_ted = "";
            String dsvistfam_fra = "";
            String dsvistfam_spa = "";
            String cdvistv1 = "";
            String dsextvistv1 = "";
            String dsextvistv1_eng = "";
            String dsextvistv1_ted = "";
            String dsextvistv1_fra = "";
            String dsextvistv1_spa = "";
            String cdvistv2 = "";
            String dsextvistv2 = "";
            String dsextvistv2_eng = "";
            String dsextvistv2_ted = "";
            String dsextvistv2_fra = "";
            String dsextvistv2_spa = "";
            String cdvistv3 = "";
            String dsextvistv3 = "";
            String dsextvistv3_eng = "";
            String dsextvistv3_ted = "";
            String dsextvistv3_fra = "";
            String dsextvistv3_spa = "";
            String cdvistcolv = "";
            String dsextvistcolv = "";
            String dsextvistcolv_eng = "";
            String dsextvistcolv_ted = "";
            String dsextvistcolv_fra = "";
            String dsextvistcolv_spa = "";
            String cdvistfinv = "";
            String dsextvistfinv = "";
            String dsextvistfinv_eng = "";
            String dsextvistfinv_ted = "";
            String dsextvistfinv_fra = "";
            String dsextvistfinv_spa = "";
            String cdvistfinm = "";
            String dsextvistfinm = "";
            String dsextvistfinm_eng = "";
            String dsextvistfinm_ted = "";
            String dsextvistfinm_fra = "";
            String dsextvistfinm_spa = "";
            String cdvistelet = "";
            String fgweb = "";
            String vist_filedis = "";
            String dsextvistelet = "";
            String dsextvistelet_eng = "";
            String dsextvistelet_ted = "";
            String dsextvistelet_fra = "";
            String dsextvistelet_spa = "";
            String cdrepa = "";
            String cdartirif = "";
            String cdclas_a = "";
            Timestamp dtprdisp = null;
            double qtadisp = 0;


            if (rs.getObject("tkposi") != null)                 tkposi = rs.getLong("tkposi");
            if (rs.getObject("cdarti") != null)                 cdarti = rs.getString("cdarti");
            if (rs.getObject("cdartm") != null)                 cdartm = rs.getString("cdartm");
            if (rs.getObject("dimena") != null)                 dimena = rs.getDouble("dimena");
            if (rs.getObject("dimenb") != null)                 dimenb = rs.getDouble("dimenb");
            if (rs.getObject("dimenc") != null)                 dimenc = rs.getDouble("dimenc");
            if (rs.getObject("ncolli") != null)                 ncolli = rs.getDouble("ncolli");
            if (rs.getObject("qtatot") != null)                 qtatot = rs.getDouble("qtatot");
            if (rs.getObject("impuni") != null)                 impuni = rs.getDouble("impuni");
            if (rs.getObject("impuninetto") != null)            impuninetto = rs.getDouble("impuninetto");
            if (rs.getObject("importonettoriga") != null)       importonettoriga = rs.getDouble("importonettoriga");
            if (rs.getObject("scont1") != null)                 scont1 = rs.getDouble("scont1");
            if (rs.getObject("scont2") != null)                 scont2 = rs.getDouble("scont2");
            if (rs.getObject("scont3") != null)                 scont3 = rs.getDouble("scont3");
            if (rs.getObject("scont4") != null)                 scont4 = rs.getDouble("scont4");
            if (rs.getObject("scrap1") != null)                 scrap1 = rs.getDouble("scrap1");
            if (rs.getObject("scrap2") != null)                 scrap2 = rs.getDouble("scrap2");
            if (rs.getObject("nrpeso_l") != null)               nrpeso_l = rs.getDouble("nrpeso_l");
            if (rs.getObject("nrpeso_n") != null)               nrpeso_n = rs.getDouble("nrpeso_n"); 
            if (rs.getObject("vlunlt") != null)                 vlunlt = rs.getDouble("vlunlt"); 
            if (rs.getObject("cdunim") != null)                 cdunim = rs.getString("cdunim");
            if (rs.getObject("cdtins") != null)                 cdtins = rs.getString("cdtins");
            if (rs.getObject("cdvisttp") != null)               cdvisttp = rs.getString("cdvisttp");
            if (rs.getObject("cdvisttp_m") != null)             cdvisttp_m = rs.getString("cdvisttp_m");
            if (rs.getObject("cdvistfam") != null)              cdvistfam = rs.getString("cdvistfam");
            if (rs.getObject("cdvistfam_m") != null)            cdvistfam_m = rs.getString("cdvistfam_m");
            if (rs.getObject("dsvistfam"+suff_vist_famiglia) != null)               dsvistfam = rs.getString("dsvistfam"+suff_vist_famiglia);
//            if (rs.getObject("dsvistfam_eng") != null)          dsvistfam_eng = rs.getString("dsvistfam_eng");
            if (rs.getObject("cdvistv1") != null)                                   cdvistv1 = rs.getString("cdvistv1");
            if (rs.getObject("dsextvistv1"+suff_vist_var1) != null)                 dsextvistv1 = rs.getString("dsextvistv1"+suff_vist_var1);
//            if (rs.getObject("dsextvistv1_eng") != null)        dsextvistv1_eng = rs.getString("dsextvistv1_eng");
            if (rs.getObject("cdvistv2") != null)                                   cdvistv2 = rs.getString("cdvistv2");
            if (rs.getObject("dsextvistv2"+suff_vist_var2) != null)                 dsextvistv2 = rs.getString("dsextvistv2"+suff_vist_var2);
//            if (rs.getObject("dsextvistv2_eng") != null)        dsextvistv2_eng = rs.getString("dsextvistv2_eng");
            if (rs.getObject("cdvistv3") != null)                                   cdvistv3 = rs.getString("cdvistv3");
            if (rs.getObject("dsextvistv3"+suff_vist_var3) != null)                 dsextvistv3 = rs.getString("dsextvistv3"+suff_vist_var3);
//            if (rs.getObject("dsextvistv3_eng") != null)        dsextvistv3_eng = rs.getString("dsextvistv3_eng");
            if (rs.getObject("cdvistcolv") != null)                                 cdvistcolv = rs.getString("cdvistcolv");
            if (rs.getObject("dsextvistcolv"+suff_vist_colori_vetro) != null)       dsextvistcolv = rs.getString("dsextvistcolv"+suff_vist_colori_vetro);
//            if (rs.getObject("dsextvistcolv_eng") != null)      dsextvistcolv_eng = rs.getString("dsextvistcolv_eng");
            if (rs.getObject("cdvistfinv") != null)                                 cdvistfinv = rs.getString("cdvistfinv");
            if (rs.getObject("dsextvistfinv"+suff_vist_finit_vetro) != null)        dsextvistfinv = rs.getString("dsextvistfinv"+suff_vist_finit_vetro);
//            if (rs.getObject("dsextvistfinv_eng") != null)      dsextvistfinv_eng = rs.getString("dsextvistfinv_eng");
            if (rs.getObject("cdvistfinm") != null)                                 cdvistfinm = rs.getString("cdvistfinm");
            if (rs.getObject("dsextvistfinm"+suff_vist_finit_mont) != null)         dsextvistfinm = rs.getString("dsextvistfinm"+suff_vist_finit_mont);
//            if (rs.getObject("dsextvistfinm_eng") != null)      dsextvistfinm_eng = rs.getString("dsextvistfinm_eng");
            if (rs.getObject("dsextvistelet"+suff_vist_elettrificazione) != null)   dsextvistelet = rs.getString("dsextvistelet"+suff_vist_elettrificazione);
//            if (rs.getObject("dsextvistelet_eng") != null)      dsextvistelet_eng = rs.getString("dsextvistelet_eng");
            if (rs.getObject("cdartirif") != null)                                  cdartirif = rs.getString("cdartirif");
            if (rs.getObject("dtprdisp") != null)                                   dtprdisp = rs.getTimestamp("dtprdisp");
            if (rs.getObject("qtadisp") != null)                                    qtadisp = rs.getDouble("qtadisp");
            if (rs.getObject("fgweb") != null)                                      fgweb = rs.getString("fgweb");
            if (rs.getObject("vist_filedis") != null)                               vist_filedis = rs.getString("vist_filedis");
            if (rs.getObject("cdvistelet") != null)                                 cdvistelet = rs.getString("cdvistelet");
//            if (rs.getObject("dsextvistelet") != null)                 dsextvistelet = rs.getString("dsextvistelet");
            if (rs.getObject("cdrepa") != null)                                     cdrepa = rs.getString("cdrepa");
            if (rs.getObject("cdclas_a") != null)                                   cdclas_a = rs.getString("cdclas_a");

            F_decode_barcode f_decode_barcode = new F_decode_barcode();

            setProfilo((Atk_sql) f_decode_barcode);

            Str_dec_barcode lstr_arti = new Str_dec_barcode();

            String ls_css_colli = "in in_num";
            String ls_cssa = "in in_num";
            String ls_cssb = "in in_num";
            String ls_cssc = "in in_num";

            String ls_ro_colli = "";
            String ls_ro_qta = "";
            String ls_roa = "";
            String ls_rob = "";
            String ls_roc = "";

            if (lb_link) {
                lstr_arti.cdarti = cdarti;
                lstr_arti.cdunim = cdunim;
                lstr_arti.cdtins = cdtins;

                f_decode_barcode.of_flag_calcolo(lstr_arti);

                if (!lstr_arti.fdimena.equals("S")) {
                    ls_roa = "readOnly";
                }
                if (!lstr_arti.fdimenb.equals("S")) {
                    ls_rob = "readOnly";
                }
                if (!lstr_arti.fdimenc.equals("S")) {
                    ls_roc = "readOnly";
                }

                if (!lstr_arti.fdimena.equals("S")) {
                    ls_cssa = "in in_num in_ro";
                }
                if (!lstr_arti.fdimenb.equals("S")) {
                    ls_cssb = "in in_num in_ro";
                }
                if (!lstr_arti.fdimenc.equals("S")) {
                    ls_cssc = "in in_num in_ro";
                }
            }

            //ricerca tipologia alternative per lo stesso articolo EAR-20140206
            List<String> vistTipiAlt = new ArrayList<String>();
            String ls_query = "";
            ls_query = " SELECT vist_tipi.cdvisttp                              \n"
                    + "    FROM pgmr.vist_tipi, pgmr.vist_articoli              \n"
                    + "   WHERE vist_tipi.cdvisttp = vist_articoli.cdvisttp     \n"
                    + "     AND vist_articoli.cdarti = '" + cdarti + "'         \n"
                    + " ORDER BY vist_articoli.cdvisttp                         \n"; 
                    //+ "     AND vist_articoli.cdvisttp <> '" + cdvisttp + "'    \n";

            PreparedStatement pstm2 = m_connection.prepareStatement(ls_query);

            ResultSet resultSet = pstm2.executeQuery();
            //if (resultSet!= null && resultSet.next()) {
            while (resultSet != null && resultSet.next()) {    
                if (resultSet.getObject(1) != null) vistTipiAlt.add(resultSet.getString(1));
            }
            
            pstm2.close();
            pstm2 = null;
            
            
            
            
            String ls_sconto = "";

            ls_sconto = atk_ctrl.descrSconti(new double[]{scont1, scont2, scont3, scont4, scrap1, scrap2});

            String annotazione_posi_int = getAnnotazione_posi(tkposi, TIPONOTA_INTERNA);
            String annotazione_posi_cli = getAnnotazione_posi(tkposi, TIPONOTA_CLIENTE);
            

//            String path_modello = "fileresources/models";
//            String path_3D = path_modello + "/3D/";
            String nome_modello = vist_filedis;
            //igs
//            String igs = (!nome_modello.equals("") ? path_3D + nome_modello + ".zip" : "");
//            String igs = mrp_arch_articoli.of_relpath_resource(cdclas_a, mrp_arch_articoli.MOD3D_IGS, cdvistfam, cdvisttp, cdvistv1, cdvistv2, cdvistv3, cdvistelet, vist_filedis, "");

//            //eprt
//            String eprt = (!nome_modello.equals("") ? path_3D + nome_modello + ".EPRT" : "");

            //easm
//            String easm = (!nome_modello.equals("") ? path_3D + nome_modello + ".EASM" : "");
            String easm = mrp_arch_articoli.of_relpath_resource(cdclas_a, mrp_arch_articoli.MOD3D_EASM, cdvistfam, cdvisttp, cdvistv1, cdvistv2, cdvistv3, cdvistelet, vist_filedis, "");

//            String path_2D = path_modello + "/2D/";
//            //dwg cm
//            String dwg_vers = "cm/";
//            String dwg = (!nome_modello.equals("") ? path_2D + dwg_vers + nome_modello + ".dwg" : "");
            String dwg = "";
            if (cdling.equals("E")){ //CABLATO -- se inglese
                dwg = mrp_arch_articoli.of_relpath_resource(cdclas_a, mrp_arch_articoli.MOD2D_DWG_PO, cdvistfam, cdvisttp, cdvistv1, cdvistv2, cdvistv3, cdvistelet, vist_filedis, "");
            } else {
                dwg = mrp_arch_articoli.of_relpath_resource(cdclas_a, mrp_arch_articoli.MOD2D_DWG_CM, cdvistfam, cdvisttp, cdvistv1, cdvistv2, cdvistv3, cdvistelet, vist_filedis, "");
            }
            
            //path certificati
            String path_cert = "fileresources/cert/";
            
            // Certificati e classe energetica
            Map<String, String> map_file_cert = new HashMap<String, String>();
            Map<String, String> map_img_cert = new HashMap<String, String>();
            String file_energyclass = "";
            String techsheet = "";

            l_query  = "";
            l_query += "  select arw_certificazione_1   as file_cert1       \n";
            l_query += "       , 'CE.jpg'               as img_cert1        \n";//CABLATO
            l_query += "       , arw_certificazione_2   as file_cert2       \n";
            l_query += "       , 'ENEC.jpg'             as img_cert2        \n";//CABLATO
            l_query += "       , arw_certificazione_3   as file_cert3       \n";
            l_query += "       , 'ETL.jpg'              as img_cert3        \n";//CABLATO
            l_query += "       , arw_certificazione_4   as file_cert4       \n";
            l_query += "       , 'GOST.jpg'             as img_cert4        \n";//CABLATO
            l_query += "       , arw_certificazione_5   as file_cert5       \n";
            l_query += "       , 'C5.jpg'               as img_cert5        \n";//CABLATO
            l_query += "       , arw_certificazione_6   as file_cert6       \n";
            l_query += "       , 'C6.jpg'               as img_cert6        \n";//CABLATO
            l_query += "       , arw_certificazione_7   as file_cert7       \n";
            l_query += "       , 'C7.jpg'               as img_cert7        \n";//CABLATO
            l_query += "       , arw_certificazione_8   as file_cert8       \n";
            l_query += "       , 'C8.jpg'               as img_cert8        \n";//CABLATO
            l_query += "       , arw_certificazione_9   as file_cert9       \n";
            l_query += "       , 'C9.jpg'               as img_cert9        \n";//CABLATO
            l_query += "       , arw_certificazione_10  as file_cert10      \n";
            l_query += "       , 'C10.jpg'               as img_cert10      \n";//CABLATO
            l_query += "       , arw_file_scheda_tec  as arw_file_scheda_tec      \n";
            l_query += "    from pgmr.vist_articoli_datiextra               \n";
            l_query += "   where cdarti = ?                                 \n";

            PreparedStatement pstm_certif = setQuery(l_query);

            int ind_certif = 1;
            pstm_certif.setString(ind_certif++, cdarti);

//            System.out.println("-- creaDossier_ordi - PRE pstm_certif.executeQuery()");
            ResultSet rs_certif =  pstm_certif.executeQuery();
//            System.out.println("-- creaDossier_ordi - POST pstm_certif.executeQuery()");

            if (rs_certif != null && rs_certif.next()) {

                String file_cert = "";
                String img_cert = "";

                file_energyclass = "";

                if (rs_certif.getObject("file_cert10") != null) {
                    file_energyclass = rs_certif.getString("file_cert10");
                } 
                
                techsheet = "";
                if (rs_certif.getObject("arw_file_scheda_tec") != null) {
                    techsheet = rs_certif.getString("arw_file_scheda_tec");
                } 
                
                int idx_slash = techsheet.indexOf("\\");
                
                if (idx_slash >= 0){
                    techsheet = techsheet.substring(idx_slash + 1);
                }

                // Certificati                    
                for (int i=1; i <= 10; i++){

                    file_cert = "";
                    img_cert = "";

                    if (rs_certif.getObject("file_cert"+ i) != null) {
                        file_cert = rs_certif.getString("file_cert"+ i);
                    }   
                    if (rs_certif.getObject("img_cert"+ i) != null) {
                        img_cert = rs_certif.getString("img_cert"+ i);
                    } 



                    file_cert = file_cert.trim();

                    int indx_slash = file_cert.lastIndexOf("/");
                    if (indx_slash >= 0){
                        file_cert = file_cert.substring(indx_slash + 1);
                    }

                    int indx_backslash = file_cert.lastIndexOf("\\");                        
                    if (indx_backslash >= 0){
                        file_cert = file_cert.substring(indx_backslash + 1);
                    }

                    map_file_cert.put("cert"+ i, file_cert);
                    map_img_cert.put("cert"+ i, img_cert);

                }

            }
            
            
            String path_to_techsheet = "";
            String path_techsheet = "fileresources/"+ DIR_TECHSHEET +"/";
            if (org.apache.commons.lang.StringUtils.isNotEmpty(techsheet)) {
                path_to_techsheet = path_techsheet + techsheet;
            }
            
            int    numv = 0;

            ls_tbl_order_details += "<tr class=\"riga\">";
            if (lb_link) {
               ls_tbl_order_details += "<td nowrap><a href=\"javaScript:atk_carr_delete(" + tkposi + ")\"><img src=\"img/b_cancella.gif\" /></a></td>";
               ls_tbl_order_details += "<td nowrap><a href=\"javaScript:atk_nota('" + tkposi + "')\"><img src=\"img/ico_nota.gif\" /></a></td>";
               ls_tbl_order_details += "<td nowrap>";
               ls_tbl_order_details += "<input type=\"hidden\" name=\"tkposi\" value=\"" + tkposi + "\"/>";

               ls_tbl_order_details += "<a href=\"javaScript:atk_scheda_edit(" + tkposi + ", '" + cdarti + "')\">" + html.text(cdartm) + "</a>";
               ls_tbl_order_details += "</td>";
            } else {
                /* 
                 * MPERUZZA 20121108 
                 * modifica per immagini ricambi
                 */
                if (org.apache.commons.lang.StringUtils.isBlank(cdartirif)) {
                    
                    /* 
                     * EAR - 20130517
                     * visualizza immagine da vist_filedis se impostata
                     */
                    if(org.apache.commons.lang.StringUtils.isNotBlank(nome_modello)){
                        ls_tbl_order_details += "<td class=\"r1\" nowrap><img src=\""+ ep_shop_url +"static/images/articoli/disegnitecnici/thumb/" + nome_modello
                            + ".jpg\"/>"
                            + "</td>";
                    }else{
                        ls_tbl_order_details += "<td class=\"r1\" nowrap><img src=\""+ ep_shop_url +"static/images/articoli/disegnitecnici/thumb/" + org.apache.commons.lang.StringUtils.rightPad(org.apache.commons.lang.StringUtils.trimToEmpty(cdvistfam), 5)
                            + cdvisttp_m
                            + "  " + org.apache.commons.lang.StringUtils.rightPad(org.apache.commons.lang.StringUtils.trimToEmpty(cdvistv1), 3)
                            + org.apache.commons.lang.StringUtils.rightPad(org.apache.commons.lang.StringUtils.trimToEmpty(cdvistv2), 1)
                            + org.apache.commons.lang.StringUtils.rightPad(org.apache.commons.lang.StringUtils.trimToEmpty(cdvistv3), 2)
                            + "-.jpg\"/>"
                            + "</td>";
                    }
                } else {
                    String imageric = "";

                    l_query = "";
                    l_query += " select imgv_filename                       \n";
                    l_query += "      , numv                                \n";
                    l_query += "   from pgmr.vist_articoli_ricambi          \n";
                    l_query += "  where cdarti   = '" + cdartirif + "'      \n";
                    l_query += "    and cdarti_ric = '" + cdarti + "'       \n";

                    pstm2 = m_connection.prepareStatement(l_query);

                    ResultSet rs2 = pstm2.executeQuery();

                    while (rs2 != null && rs2.next()) {
                        imageric = "";
                        numv = 0;
                        
                        if (rs2.getObject("imgv_filename") != null) {
                            imageric += rs2.getString("imgv_filename");
                        }
                        if (rs2.getObject("numv") != null) {
                            numv += rs2.getInt("numv");
                        }
                    }
                    pstm2.close();
                    pstm2 = null;
                    
                    
                   ls_tbl_order_details += "<td class=\"r1\" nowrap><img src=\""+ ep_shop_url +"static/images/articoli/disegnitecnici/thumb/" + imageric + "\"/>"
                            + "</td>";
                }
               ls_tbl_order_details += "<td >&nbsp;</td>";  //variante
               ls_tbl_order_details += "<td ><img src=\""+ ep_shop_url +"images/articoli/vetro/" + cdvistfam_m
                        + "_" + cdvisttp + "_" + cdvistv1 + "_" + cdvistcolv + cdvistfinv
                        + ".jpg\"/></td>";
                if (org.apache.commons.lang.StringUtils.isNotBlank(cdvistfinm)) {
                   ls_tbl_order_details += "<td ><img src=\""+ ep_shop_url +"static/images/articoli/montature/" + cdvistfinm + ".jpg\"/></td>"; //finitura montatura
                } else {
                   ls_tbl_order_details += "<td >&nbsp;</td>"; //senza img finitura montatura
                }
                //FINE
               ls_tbl_order_details += "<td >&nbsp;</td>";  //elet
               ls_tbl_order_details += "<td colspan=\"4\" >";

               ls_tbl_order_details += "<div style=\"width:200px; padding:0px; margin:0px; border:0px none;\">";
               ls_tbl_order_details += "<div style=\"width:150px; padding:0px; margin:0px; border:0px none; float:left;\">";

//                String ep_site_siteroot = "C:\\ateikon\\sorgenti\\shop2012\\build\\web\\";
                String slash = System.getProperty("file.separator");
                String ep_site_siteroot = ep_costanti.getCostvalue("ep.site_siteroot");
                String ep_shop_root = ep_costanti.getCostvalue("ep.shop_root");

                File pdf = new File(ep_site_siteroot + path_to_techsheet);
                if (pdf.exists() && !path_to_techsheet.equals("")) {
                   ls_tbl_order_details += "<a target=\"_blank\" href=\""+ ep_portal_url +"download/"+path_to_techsheet+"?f=" + path_to_techsheet + "\" class=\"\"  border=\"0\"><img height=\"24\" src=\""+ ep_shop_url +"static/images/pdf-icon.gif\" border=\"0\" style=\"margin-right:2px;margin-bottom:2px;\"></a>";
                }
                File cad = new File(ep_site_siteroot + dwg);
                if (cad.exists() && !dwg.equals("")) {
                   ls_tbl_order_details += "<a target=\"_blank\" href=\""+ ep_portal_url +"download/" + dwg + "?f=" + dwg + "\" class=\"\"  border=\"0\"><img height=\"24\" src=\""+ ep_shop_url +"static/images/dwg-icon.gif\" border=\"0\" style=\"margin-right:2px;margin-bottom:2px;\"></a>";
                }
                File down_1 = new File(ep_site_siteroot + easm);
                if (down_1.exists() && !easm.equals("")) {
                   ls_tbl_order_details += "<a target=\"_blank\" href=\""+ ep_portal_url +"download/" + easm + "?f=" + easm + "\" class=\"\"  border=\"0\"><img height=\"24\" src=\""+ ep_shop_url +"static/images/easm-icon.gif\" border=\"0\" style=\"margin-right:2px;margin-bottom:2px;\"></a>";
                }
//                File down_2 = new File(ep_site_siteroot + igs);
//                if (down_2.exists() && !igs.equals("")) {
//                   ls_tbl_order_details += "<a target=\"_blank\" href=\""+ ep_portal_url +"download/" + igs + "?f=" + igs + "\" class=\"\" border=\"0\"><img height=\"24\" src=\""+ ep_shop_url +"static/images/iges-icon.gif\" border=\"0\" style=\"margin-right:2px;margin-bottom:2px;\"></a>";
//                }
//                File down_3 = new File(ep_site_siteroot + eprt);
//                if (down_3.exists() && !eprt.equals("")) {
//                   ls_tbl_order_details += "<a target=\"_blank\" href=\""+ ep_portal_url +"download/" + eprt + "?f=" + eprt + "\" class=\"\" border=\"0\"><img height=\"24\" src=\""+ ep_shop_url +"static/images/eprt-icon.gif\" border=\"0\" style=\"margin-right:2px;margin-bottom:2px;\"></a>";
//                }
                
                // Specsheet
                String lang = sql_String("select cdiso639 from pgmr.ep_lingua where cdling = '"+ cdling + "' ");
                boolean of_exist_resource_specsheet_modello = mrp_arch_articoli.of_exist_resource_specsheet_modello(cdclas_a, ep_shop_root, nome_modello, lang, cdvisttp, cdvistfam, cdvistv1, cdvistv2, cdvistv3, cdvistelet);
                            
                if (of_exist_resource_specsheet_modello) {
                    String specsheet_relpath = mrp_arch_articoli.of_relpath_resource(cdclas_a, Mrp_arch_articoli.SPECSHEET_MOD, cdvistfam, cdvisttp, cdvistv1, cdvistv2, cdvistv3, cdvistelet, vist_filedis, vist_filedis);
                    specsheet_relpath = specsheet_relpath.replace(" ", "%20");
                    specsheet_relpath = specsheet_relpath.replace("|", "%7C");
                   ls_tbl_order_details += "<a target=\"_blank\" href=\""+ ep_portal_url +"download/" + cdartm + ".pdf?f=" + specsheet_relpath + "&lang="+ lang +"\" class=\"\" border=\"0\"><img height=\"24\" src=\""+ ep_shop_url +"static/images/tech-icon.gif\" border=\"0\" style=\"margin-right:2px;margin-bottom:2px;\"></a>";
                }
                
                // Energyclass
                if (!file_energyclass.equals("")){

                    String link_ec = ep_eprogen_url + "epRichiesta_download_energyclass.jsp?cdarti=" + cdarti + "&cdling_sel="+ cdling_portale;

                    ls_tbl_order_details += "<a target=\"_blank\" href=\""+ link_ec +"\" class=\"\"  border=\"0\"><img height=\"24\" src=\""+ ep_shop_url +"static/images/ce-icon.gif\" border=\"0\" style=\"margin-right:2px;margin-bottom:2px;\"></a>";

                }   
                
                
               
               ls_tbl_order_details += "</div>";
               
               ls_tbl_order_details += "<div style=\"width:50px; padding: 0px; margin:0px; border:0px none; float: right;\">";
               
                //technews
                l_query  = "";
                l_query += "  select distinct posts.tkpost                    \n";
                l_query += "       , posts.post_date                          \n";
                l_query += "       , posts.fgpubblico                         \n";
                l_query += "       , tags.cdvistelet                          \n";
                l_query += "    from pgmr.ep_posts         posts              \n";
                l_query += "       , pgmr.ep_post_category categ              \n";
                l_query += "       , pgmr.ep_media_content media              \n";
                l_query += "       , pgmr.ep_media_tags    tags               \n";
                l_query += "   where posts.tkpost = media.parent_id           \n";
                l_query += "     and posts.post_category = categ.tkpostcat    \n";
                l_query += "     and categ.cdpostcat_m = 'TECH_NEWS'          \n";
                l_query += "     and media.tkmedia = tags.tkmedia             \n";
                l_query += "     and posts.fgpubblico = 'S'                   \n";
                l_query += "     and posts.post_date <= "+ sysdate +"         \n";
                l_query += "     and tags.cdvistfam = ?                       \n";
                l_query += "     and tags.cdvisttp = ?                        \n";
                l_query += "     and (tags.cdvistelet = ? or tags.cdvistelet is null)   \n";
                l_query += "  order by tags.cdvistelet desc, post_date desc             \n";

                PreparedStatement pstm_technews = setQuery(l_query);
               
                ind = 1;
                pstm_technews.setString(ind++, cdvistfam);
                pstm_technews.setString(ind++, cdvisttp);
                pstm_technews.setString(ind++, cdvistelet);

                ResultSet rs_technews =  pstm_technews.executeQuery();
                
                if (rs_technews != null && rs_technews.next()) {
                    
                    long tkpost = 0;
                    Timestamp post_date = null;
                    String tag_cdvistelet = "";
                    
                    if (rs_technews.getObject("tkpost") != null) {
                        tkpost = rs_technews.getLong("tkpost");
                    }       
                    if (rs_technews.getObject("post_date") != null) {
                        post_date = rs_technews.getTimestamp("post_date");
                    } 
                    if (rs_technews.getObject("cdvistelet") != null) {
                        tag_cdvistelet = rs_technews.getString("cdvistelet");
                    } 
                    
                    int count_technews = 1;
                    while (rs_technews.next()){
//                        String ls_tag_cdvistelet = "";
//                        
//                        if (rs_technews.getObject("cdvistelet") != null) {
//                            ls_tag_cdvistelet = rs_technews.getString("cdvistelet");
//                        } 
//                        
//                        // Conto solo le news che hanno elettrificazione uguale all'elettrificazione della prima news trovata
//                        // ovviamente se non esistono con elettrificazione vengono contate tutte
//                        if (ls_tag_cdvistelet.equals(tag_cdvistelet)){
                            count_technews++;
//                        }
                    }
                    
                    
                    int post_date_anno = post_date.getYear() + 1900;
                    int post_date_mese = post_date.getMonth() + 1;
                    int post_date_giorno = post_date.getDate();

                    String ls_locale = of_lingua("", cdling);
                    if (!ls_locale.equals("it") && !ls_locale.equals("en")){
                        ls_locale = "en";
                    }

                    String link_technews = ep_portal_url + "techNews/"+ post_date_anno +"/"+ post_date_mese +"/"+ post_date_giorno +"/"+ tkpost +"?locale="+ ls_locale + "&cdvistfam="+ cdvistfam + "&cdvisttp="+ cdvisttp + "&cdvistelet="+ cdvistelet;
                    
                    //String ls_div_count_technews = "<div class=\"notify-bubble\" style=\"font-size: 9px;background-color: red;color: #fff;position: absolute;top: -5px;left: 12px;border-radius: 9px;width: 15px;min-width: 15px;height: 15px;line-height: 15px;text-align: center;padding: 0;\">"+ count_technews +"</div>";
                    String ls_div_count_technews = "<span style=\"font-size: 8px;\">("+ count_technews +")</span>";
                        
                    ls_tbl_order_details += "<div style=\"padding-left: 12px;\"><div style=\"float: left;\"><a target=\"_blank\" href=\""+ link_technews +"\" class=\"\"  border=\"0\"><img height=\"24\" src=\""+ ep_shop_url +"static/images/news-icon.gif\" border=\"0\" style=\"margin-right:2px;margin-bottom:2px;\"></a></div><div style=\"float: right;\">"+ (count_technews > 1 ? ls_div_count_technews : "") +"</div></div>";
                        
                }
                
               ls_tbl_order_details += "</div>";
                 
               ls_tbl_order_details += "<div style=\"width:150px; padding:0px; margin:0px; border:0px none;\">";
                
                // certificati
                for (int i=1; i <= 10; i++){
                        
                    String file_cert = map_file_cert.get("cert"+ i);
                    String img_cert = map_img_cert.get("cert"+ i);

                    String certif_img_relpath = path_cert + file_cert;
                    File certif_file = new File(ep_site_siteroot + certif_img_relpath);
                    if(certif_file.exists() && !file_cert.equals("")){

                        String link_cert = ep_portal_url + "download/"+ certif_img_relpath +"?f=/" + certif_img_relpath;

                        ls_tbl_order_details += "<a target=\"_blank\" href=\""+ link_cert +"\" class=\"\"  border=\"0\"><img height=\"24\" src=\""+ ep_shop_url +"static/images/articoli/specsheetres/dati/"+ img_cert +"\" border=\"0\" style=\"margin-right:2px;margin-bottom:2px;\"></a>";

                    }
                }
                
               ls_tbl_order_details += "</div>";
               ls_tbl_order_details += "</div>";

               ls_tbl_order_details += "</td>";  //peso lordo
                //ls_ += "<td >&nbsp;</td>";  //peso netto
               //ls_tbl_order_details += "<td >&nbsp;</td>";  //volume
               ls_tbl_order_details += "<td colspan=\"6\" nowrap>";     //nota di riga


                if (!annotazione_posi_cli.equals("")) {
                   ls_tbl_order_details += "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"nota-cli\">";
                   ls_tbl_order_details += "<tr>";
                   ls_tbl_order_details += "<td valign=\"top\"><p>"+ html.text(note_cliente) +": " + html.text(annotazione_posi_cli) + "</p></td>";
                   ls_tbl_order_details += "</tr>";
                   ls_tbl_order_details += "</table>";
                }

               ls_tbl_order_details += "</td></tr>";
               ls_tbl_order_details += "<tr class=\"dettaglio2\">";

            }
            
            String ricambio = atk_dwlingua.getLabel ("", cdling, "label_ricambio", "", null, "SHOP");
            ricambio += (numv > 0 ? " "+ numv : "");
            
//            La lingua � gi� gestita dalle label
//            if("E".equals(ls_lingua)) {
//               ls_tbl_order_details += "<td valign=\"bottom\" nowrap><strong>" + html.text(dsvistfam_eng) + " " + html.text(cdvisttp) + (!"".equals(cdartirif) ? "<br/>Replacement" : "") + "</strong></td>";
//               ls_tbl_order_details += "<td valign=\"bottom\" class=\"al\" nowrap>" + (org.apache.commons.lang.StringUtils.isNotBlank(dsextvistv1_eng) ? html.text(dsextvistv1_eng) : cdvistv1) + " " + (org.apache.commons.lang.StringUtils.isNotBlank(dsextvistv2_eng) ? html.text(dsextvistv2_eng) : cdvistv2) + " " + (org.apache.commons.lang.StringUtils.isNotBlank(dsextvistv3_eng) ? html.text(dsextvistv3_eng) : cdvistv3) + "</td>";
//               ls_tbl_order_details += "<td valign=\"bottom\" class=\"al\" nowrap>" + html.text(dsextvistcolv_eng) + " " + html.text(dsextvistfinv_eng) + "</td>";
//               ls_tbl_order_details += "<td valign=\"bottom\" class=\"al\" nowrap>" + html.text(dsextvistfinm_eng) + "</td>";
//               ls_tbl_order_details += "<td valign=\"bottom\" class=\"al\" nowrap>" + html.text(dsextvistelet_eng) + "</td>";
//               ls_tbl_order_details += "<td align=\"right\">" + przFormat.format(nrpeso_l) + "</td>";      //peso lordo
//               ls_tbl_order_details += "<td align=\"right\">" + przFormat.format(nrpeso_n) + "</td>";      //peso netto
//               ls_tbl_order_details += "<td>" + przFormat.format(vlunlt) + "</td>";     //volume
//            } else {
               ls_tbl_order_details += "<td valign=\"bottom\" nowrap><strong>" + html.text(dsvistfam) + " " + (vistTipiAlt.isEmpty()?html.text(cdvisttp):"");
               
               for (Iterator<String> it = vistTipiAlt.iterator(); it.hasNext();) {
                    
                    String tipoAlt = it.next();
                    
                    ls_tbl_order_details += tipoAlt;
                    
                    if(it.hasNext()) ls_tbl_order_details += "/";                            
                
                } 
               /*for (String tipoAlt : vistTipiAlt) {
                   ls_tbl_order_details += "/"+tipoAlt;
               }*/
               
               ls_tbl_order_details += (!"".equals(cdartirif) ? "<br/>"+ricambio : "") + "</strong></td>";
               ls_tbl_order_details += "<td valign=\"bottom\" class=\"al\" nowrap>" + (org.apache.commons.lang.StringUtils.isNotBlank(dsextvistv1) ? html.text(dsextvistv1) : cdvistv1) + " " + (org.apache.commons.lang.StringUtils.isNotBlank(dsextvistv2) ? html.text(dsextvistv2) : cdvistv2) + " " + (org.apache.commons.lang.StringUtils.isNotBlank(dsextvistv3) ? html.text(dsextvistv3) : cdvistv3) + "</td>";
               ls_tbl_order_details += "<td valign=\"bottom\" class=\"al\" nowrap>" + html.text(dsextvistcolv) + " " + html.text(dsextvistfinv) + "</td>";
               ls_tbl_order_details += "<td valign=\"bottom\" class=\"al\" nowrap>" + html.text(dsextvistfinm) + "</td>";
               ls_tbl_order_details += "<td valign=\"bottom\" class=\"al\" nowrap>" + html.text(dsextvistelet) + "</td>";
               ls_tbl_order_details += "<td align=\"right\" style=\"padding-right:4px;padding-left:4px\">" + przFormat.format(nrpeso_l) + "</td>";      //peso lordo
               ls_tbl_order_details += "<td align=\"right\" style=\"padding-right:4px;padding-left:4px\">" + przFormat.format(nrpeso_n) + "</td>";      //peso netto
               ls_tbl_order_details += "<td align=\"right\" style=\"padding-right:4px;padding-left:4px\">" + przFormat.format(vlunlt) + "</td>";     //volume  
//            }


            if (fgpromo.equals("S")) {

               ls_tbl_order_details += "<td align=\"right\" style=\"padding-right:4px;padding-left:4px\" nowrap><span nowrap class=\"cart-stato\" style=\"background-color: rgb(51,153,51);\">" + html.text(cdartm) + "</span></td>";  //cdartm

            } else {

                int count = 0;

                ResultSet rs_stati_art = mrp_arch_stato.getArtList(cdarti, true);
                if (rs_stati_art != null && rs_stati_art.next()) {
                    if (rs_stati_art.getObject("tot") != null) {
                        count = rs_stati_art.getInt("tot");
                    }
                }

                rs_stati_art = mrp_arch_stato.getArtList(cdarti, false);


                if (rs_stati_art != null && count == 1 && rs_stati_art.next()) {

                    String vist_fgrgb = "";
                    String vist_rgb_r = "";
                    String vist_rgb_g = "";
                    String vist_rgb_b = "";

                    if (rs_stati_art.getObject("vist_fgrgb") != null) {
                        vist_fgrgb = rs_stati_art.getString("vist_fgrgb");
                    }
                    if (rs_stati_art.getObject("vist_rgb_r") != null) {
                        vist_rgb_r = rs_stati_art.getString("vist_rgb_r");
                    }
                    if (rs_stati_art.getObject("vist_rgb_g") != null) {
                        vist_rgb_g = rs_stati_art.getString("vist_rgb_g");
                    }
                    if (rs_stati_art.getObject("vist_rgb_b") != null) {
                        vist_rgb_b = rs_stati_art.getString("vist_rgb_b");
                    }


                    if (vist_fgrgb.equals("S")) {
                       ls_tbl_order_details += "<td align=\"right\" style=\"padding-right:4px;padding-left:4px\" nowrap><span nowrap class=\"cart-stato\" style=\"background-color: rgb(" + vist_rgb_r + "," + vist_rgb_g + "," + vist_rgb_b + ");\">" + html.text(cdartm) + "</span></td>";  //cdartm
                    } else {
                       ls_tbl_order_details += "<td align=\"right\" style=\"padding-right:4px;padding-left:4px\" nowrap>" + html.text(cdartm) + "</td>";     //cdartm
                    }
                } else {
                   ls_tbl_order_details += "<td align=\"right\" style=\"padding-right:4px;padding-left:4px\" nowrap>" + html.text(cdartm) + "</td>";     //cdartm
                }
            }

            for (int i = 0; i < 5; i++) {

                if (lwm_orddimentav.indexOf("A") == i) {
                    if (lb_link) {

                        str_html = new Str_html();

                        str_html.name = "dimena";
                        str_html.value = dimFormat.format(dimena);
                        str_html.css = ls_cssa;
                        str_html.size = "5";
                        str_html.maxlength = "10";


                       ls_tbl_order_details += "<td nowrap align=\"right\" style=\"padding-right:4px;padding-left:4px\">";
                       ls_tbl_order_details += html.getText_box(str_html);
                       ls_tbl_order_details += "</td>";
                    } else {
                       ls_tbl_order_details += "<td nowrap align=\"right\" style=\"padding-right:4px;padding-left:4px\">" + dimFormat.format(dimena) + "</td>";
                    }

                } else if (lwm_orddimentav.indexOf("B") == i) {
                    if (lb_link) {
                        str_html = new Str_html();

                        str_html.name = "dimenb";
                        str_html.value = dimFormat.format(dimenb);
                        str_html.css = ls_cssb;
                        str_html.size = "5";
                        str_html.maxlength = "10";


                       ls_tbl_order_details += "<td nowrap align=\"right\" style=\"padding-right:4px;padding-left:4px\">";
                       ls_tbl_order_details += html.getText_box(str_html);
                       ls_tbl_order_details += "</td>";

                    } else {
                       ls_tbl_order_details += "<td nowrap align=\"right\" style=\"padding-right:4px;padding-left:4px\">" + dimFormat.format(dimenb) + "</td>";
                    }
                } else if (lwm_orddimentav.indexOf("C") == i) {
                    if (lb_link) {
                        str_html = new Str_html();

                        str_html.name = "dimenc";
                        str_html.value = dimFormat.format(dimenc);
                        str_html.css = ls_cssc;
                        str_html.size = "5";
                        str_html.maxlength = "10";


                       ls_tbl_order_details += "<td nowrap align=\"right\" style=\"padding-right:4px;padding-left:4px\">";
                       ls_tbl_order_details += html.getText_box(str_html);
                       ls_tbl_order_details += "</td>";

                    } else {
                       ls_tbl_order_details += "<td nowrap align=\"right\" style=\"padding-right:4px;padding-left:4px\">" + dimFormat.format(dimenc) + "</td>";
                    }
                } else if (lwm_orddimentav.indexOf("D") == i) {
                    if (lb_link) {
                        str_html = new Str_html();

                        str_html.name = "ncolli";
                        str_html.value = numFormat.format(ncolli);
                        str_html.css = ls_css_colli;
                        str_html.size = "5";
                        str_html.maxlength = "10";

                       ls_tbl_order_details += "<td nowrap align=\"right\" style=\"padding-right:4px;padding-left:4px\">";
                       ls_tbl_order_details += html.getText_box(str_html);
                       ls_tbl_order_details += "</td>";

                    } else {
                       ls_tbl_order_details += "<td nowrap align=\"right\" style=\"padding-right:4px;padding-left:4px\">" + numFormat.format(ncolli) + "</td>";
                    }
                } else if (lwm_orddimentav.indexOf("Q") == i) {
                   ls_tbl_order_details += "<td width=\"30\" valign=\"bottom\" nowrap align=\"right\" style=\"font-size:14px;padding-right:4px;padding-left:4px\"><strong>" + qtaFormat.format(qtatot) + "</strong></td>";
                }

            }   // FINE for (int i=0; i<3; i++){
            if (isscontiriga.equals("S")) {
               ls_tbl_order_details += "<td valign=\"bottom\" nowrap align=\"right\" style=\"font-size:14px;padding-right:4px;padding-left:4px\">" + przFormat.format(impuni) + "</td>";
               ls_tbl_order_details += "<td valign=\"bottom\" nowrap align=\"right\" style=\"font-size:14px;padding-right:4px;padding-left:4px\">" + html.text(ls_sconto) + "</td>";
            }
           ls_tbl_order_details += "<td valign=\"bottom\" nowrap align=\"right\" style=\"font-size:14px;padding-right:4px;padding-left:4px\"><strong>" + przFormat.format(impuninetto) + "</strong></td>";
           ls_tbl_order_details += "<td valign=\"bottom\" nowrap align=\"right\" style=\"font-size:14px;padding-right:4px;padding-left:4px\">" + przFormat.format(importonettoriga) + "</td>";


            double disp = Math.floor(qtadisp);

            if (disp > 999) {
               ls_tbl_order_details += "<td valign=\"bottom\" nowrap align=\"right\" style=\"padding-right:4px;padding-left:4px\"><strong>"+html.text(prox_disp_mail)+"</strong> <span style=\"font-size:14px;\">999</span></td>";
            } else 
            if (disp > 0) {
               ls_tbl_order_details += "<td valign=\"bottom\" nowrap align=\"right\" style=\"padding-right:4px;padding-left:4px\"><strong>"+html.text(prox_disp_mail)+"</strong> <span style=\"font-size:14px;\">" + qtaFormat.format(disp) + "</span></td>";
            } else {
                if (dtprdisp != null) {
                   ls_tbl_order_details += "<td valign=\"bottom\" nowrap align=\"center\" style=\"padding-right:4px;padding-left:4px\"><strong>" + atk_ctrl.getDate(dtprdisp) + "</strong></td>";
                } else {
                   ls_tbl_order_details += "<td valign=\"bottom\" nowrap align=\"center\" style=\"padding-right:4px;padding-left:4px\"><strong>&nbsp;</strong></td>";
                }
            }

           ls_tbl_order_details += "</tr>";

            if (!annotazione_posi_int.equals("")) {

               ls_tbl_order_details += "<tr>";
                if (lb_link) {
                   ls_tbl_order_details += "<td>&nbsp;</td>";
                   ls_tbl_order_details += "<td>&nbsp;</td>";
                }
               ls_tbl_order_details += "<td nowrap>Nota Int.:</td>";
               ls_tbl_order_details += "<td colspan=\"" + tot_cols + "\">" + html.text(annotazione_posi_int) + "</td>";
               ls_tbl_order_details += "</tr>";
            }


        } while (rs != null && rs.next());

       ls_tbl_order_details += "</table>";

    }   // FINE if 
    pstm.close();
    pstm = null;
    // --- Verifico se ci sono righe senza pacco

    l_query = "";
    l_query += " select art.cdarti                                      \n";
    l_query += "      , art.cdartm                                      \n";
    l_query += "      , art.dsarti                                      \n";
    l_query += "      , art.cdbarcode                                   \n";
    l_query += "      , op.tkposi                                       \n";
    l_query += "      , op.dssart                                       \n";
    l_query += "      , op.dimena                                       \n";
    l_query += "      , op.dimenb                                       \n";
    l_query += "      , op.dimenc                                       \n";
    l_query += "      , op.ncolli                                       \n";
    l_query += "      , matr.qtatot                                     \n";
    l_query += "      , op.impuni                                       \n";
    l_query += "      , op.impuninetto                                  \n";
    l_query += "      , op.importonettoriga                             \n";
    l_query += "      , op.scont1                                       \n";
    l_query += "      , op.scont2                                       \n";
    l_query += "      , op.scont3                                       \n";
    l_query += "      , op.scont4                                       \n";
    l_query += "      , op.scrap1                                       \n";
    l_query += "      , op.scrap2                                       \n";
    l_query += "      , op.nrposi                                       \n";
    l_query += "      , mis.cdunim_m                                    \n";
    l_query += "      , anag.cdmatricola_m                              \n";
    if (is_oracle){
        throw new Exception("DB non supportato");
    } else if (is_sybase || is_postgresql) {
        l_query += "   from pgmr.web_ord_positito  op                       \n";
        l_query += "      , pgmr.web_ord_posi_matr matr                     \n";
        l_query += "      , pgmr.matr_anagrafica   anag                     \n";
        l_query += "       , pgmr.mrp_arch_articoli art                                  \n";
        l_query += "        LEFT OUTER JOIN  pgmr.unimisura         mis ON art.cdunim_1   = mis.cdunim   \n";
        l_query += "  where op.tkordi  = " + tkordi + "                         \n";
        l_query += "    and art.cdarti = op.cdarti                          \n";
        l_query += "    and op.tkposi  = matr.tkposi                        \n";
        l_query += "    and op.tkordi  = matr.tkordi                        \n";
        l_query += "    and matr.tkmatricola = anag.tkmatricola             \n";
    }
    l_query += "  order by op.nrposi                                    \n";

    pstm = m_connection.prepareStatement(l_query);

    rs = pstm.executeQuery();

    if (rs != null && rs.next()) {

        int tot_cols = 0;

       ls_tbl_order_details += "<h2>Pacchi</h2>";
       ls_tbl_order_details += "<table class=\"report\">";
       ls_tbl_order_details += "<tr>";
        if (lb_link) {
           ls_tbl_order_details += "<th>&nbsp;</th>";
           ls_tbl_order_details += "<th>&nbsp;</th>";
        }
       ls_tbl_order_details += "<th>Codice</th>";
       ls_tbl_order_details += "<th>Descrizione</th>";
        tot_cols++;

        for (int i = 0; i < 5; i++) {

            if (lwm_orddimentav.indexOf("A") == i) {
                //   ls_tbl_order_details += "<th>Lung</th>";     tot_cols ++;
            } else if (lwm_orddimentav.indexOf("B") == i) {
                //   ls_tbl_order_details += "<th>Larg</th>";     tot_cols ++;
            } else if (lwm_orddimentav.indexOf("C") == i) {
                //   ls_tbl_order_details += "<th>Spess</th>";     tot_cols ++;
            } else if (lwm_orddimentav.indexOf("D") == i) {
               ls_tbl_order_details += "<th class=\"ar\">Colli</th>";
                tot_cols++;
            } else if (lwm_orddimentav.indexOf("Q") == i) {
               ls_tbl_order_details += "<th class=\"ar\">"+quantita_mail+"</th>";
                tot_cols++;
            }

        }   // FINE for (int i=0; i<3; i++){
        if (isscontiriga.equals("S")) {
           ls_tbl_order_details += "<th class=\"ar\">Prezzo<br/>Listino</th>";
            tot_cols++;
           ls_tbl_order_details += "<th class=\"ar\">Sconto %</th>";
            tot_cols++;
        }
       ls_tbl_order_details += "<th class=\"ar\">Prezzo<br/>Unitario</th>";
        tot_cols++;
       ls_tbl_order_details += "<th class=\"ar\">Importo<br/>Totale</th>";
        tot_cols++;
       ls_tbl_order_details += "</tr>";

        do {

            long tkposi = 0;
            String cdarti = "";
            String cdartm = "";
            String dsarti = "";
            String dssart = "";
            String dsunim = "";
            String cdmatricola_m = "";
            double dimena = 0;
            double dimenb = 0;
            double dimenc = 0;
            double ncolli = 0;
            double qtatot = 0;
            double impuni = 0;
            double impuninetto = 0;
            double importonettoriga = 0;
            double scont1 = 0;
            double scont2 = 0;
            double scont3 = 0;
            double scont4 = 0;
            double scrap1 = 0;
            double scrap2 = 0;

            if (rs.getObject("tkposi") != null)                 tkposi = rs.getLong("tkposi");
            if (rs.getObject("cdarti") != null)                 cdarti = rs.getString("cdarti");
            if (rs.getObject("cdmatricola_m") != null)          cdmatricola_m = rs.getString("cdmatricola_m");
            if (rs.getObject("cdartm") != null)                 cdartm = rs.getString("cdartm");
            if (rs.getObject("dsarti") != null)                 dsarti = rs.getString("dsarti");
            if (rs.getObject("dssart") != null)                 dssart = rs.getString("dssart");
            if (rs.getObject("dsunim") != null)                 dsunim = rs.getString("dsunim");
            if (rs.getObject("dimena") != null)                 dimena = rs.getDouble("dimena");
            if (rs.getObject("dimenb") != null)                 dimenb = rs.getDouble("dimenb");
            if (rs.getObject("dimenc") != null)                 dimenc = rs.getDouble("dimenc");
            if (rs.getObject("ncolli") != null)                 ncolli = rs.getDouble("ncolli");
            if (rs.getObject("qtatot") != null)                 qtatot = rs.getDouble("qtatot");
            if (rs.getObject("impuni") != null)                 impuni = rs.getDouble("impuni");
            if (rs.getObject("impuninetto") != null)            impuninetto = rs.getDouble("impuninetto");
            if (rs.getObject("importonettoriga") != null)       importonettoriga = rs.getDouble("importonettoriga");
            if (rs.getObject("scont1") != null)                 scont1 = rs.getDouble("scont1");
            if (rs.getObject("scont2") != null)                 scont2 = rs.getDouble("scont2");
            if (rs.getObject("scont3") != null)                 scont3 = rs.getDouble("scont3");
            if (rs.getObject("scont4") != null)                 scont4 = rs.getDouble("scont4");
            if (rs.getObject("scrap1") != null)                 scrap1 = rs.getDouble("scrap1");
            if (rs.getObject("scrap2") != null)                 scrap2 = rs.getDouble("scrap2");
            

            String annotazione_posi_int = getAnnotazione_posi(tkposi, TIPONOTA_INTERNA);
            String annotazione_posi_cli = getAnnotazione_posi(tkposi, TIPONOTA_CLIENTE);

            String ls_sconto = "";

            ls_sconto = atk_ctrl.descrSconti(new double[]{scont1, scont2, scont3, scont4, scrap1, scrap2});

           ls_tbl_order_details += "<tr>";
            if (lb_link) {
               ls_tbl_order_details += "<td nowrap><a href=\"javaScript:atk_carr_delete(" + tkposi + ")\"><img src=\"img/b_cancella.gif\" /></a></td>";
               ls_tbl_order_details += "<td nowrap><a href=\"javaScript:atk_nota('" + tkposi + "')\"><img src=\"img/ico_nota.gif\" /></a></td>";
               ls_tbl_order_details += "<td nowrap><a href=\"javaScript:atk_scheda('" + cdarti + "')\">" + html.text(cdmatricola_m) + "</a></td>";
            } else {
               ls_tbl_order_details += "<td nowrap>" + html.text(cdmatricola_m) + "</td>";
            }

           ls_tbl_order_details += "<td nowrap>" + html.text(dsarti) + "</td>";
            //ls_ += "<td nowrap>"+html.text(dsunim)+"</td>";

            for (int i = 0; i < 5; i++) {

                if (lwm_orddimentav.indexOf("A") == i) {
                    //   ls_tbl_order_details += "<td nowrap align=\"right\">"+dimFormat.format(dimena)+"</td>";
                } else if (lwm_orddimentav.indexOf("B") == i) {
                    //   ls_tbl_order_details += "<td nowrap align=\"right\">"+dimFormat.format(dimenb)+"</td>";
                } else if (lwm_orddimentav.indexOf("C") == i) {
                    //   ls_tbl_order_details += "<td nowrap align=\"right\">"+dimFormat.format(dimenc)+"</td>";
                } else if (lwm_orddimentav.indexOf("D") == i) {
                   ls_tbl_order_details += "<td nowrap align=\"right\">" + numFormat.format(ncolli) + "</td>";
                } else if (lwm_orddimentav.indexOf("Q") == i) {
                   ls_tbl_order_details += "<td nowrap align=\"right\">" + qtaFormat.format(qtatot) + "</td>";
                }

            }   // FINE for (int i=0; i<3; i++){
            if (isscontiriga.equals("S")) {
               ls_tbl_order_details += "<td nowrap align=\"right\" style=\"padding-right:4px;padding-left:4px\">" + przFormat.format(impuni) + "</td>";
               ls_tbl_order_details += "<td nowrap align=\"right\" style=\"padding-right:4px;padding-left:4px\">" + html.text(ls_sconto) + "</td>";
            }
           ls_tbl_order_details += "<td nowrap align=\"right\" style=\"padding-right:4px;padding-left:4px\">" + przFormat.format(impuninetto) + "</td>";
           ls_tbl_order_details += "<td nowrap align=\"right\" style=\"padding-right:4px;padding-left:4px\">" + przFormat.format(importonettoriga) + "</td>";
           ls_tbl_order_details += "</tr>";


            if (!annotazione_posi_cli.equals("")) {

               ls_tbl_order_details += "<tr>";
                if (lb_link) {
                   ls_tbl_order_details += "<td>&nbsp;</td>";
                   ls_tbl_order_details += "<td>&nbsp;</td>";
                }
               ls_tbl_order_details += "<td nowrap>Nota per Cli.:</td>";
               ls_tbl_order_details += "<td colspan=\"" + tot_cols + "\">" + html.text(annotazione_posi_cli) + "</td>";
               ls_tbl_order_details += "</tr>";
            }
            if (!annotazione_posi_int.equals("")) {

               ls_tbl_order_details += "<tr>";
                if (lb_link) {
                   ls_tbl_order_details += "<td>&nbsp;</td>";
                   ls_tbl_order_details += "<td>&nbsp;</td>";
                }
               ls_tbl_order_details += "<td nowrap>Nota Int.:</td>";
               ls_tbl_order_details += "<td colspan=\"" + tot_cols + "\">" + html.text(annotazione_posi_int) + "</td>";
               ls_tbl_order_details += "</tr>";
            }

        } while (rs != null && rs.next());

       ls_tbl_order_details += "</table>";

    }

   ls_tbl_order_details += "<br/>";
   ls_tbl_order_details += "<br/>";

    // impost i totali
   Str_ordven_tot lstr = new Str_ordven_tot();


   l_query = "";
   l_query += " select sum(importonettoriga)    \n";
   l_query += "   from pgmr.web_ord_positito    \n";
   l_query += "  where tkordi = " + tkordi + "      \n";


   lstr.tot_importonettoriga = sql_double(l_query);


   tot_rec = 0;

   l_query = "";
   l_query += " select imptrasp             \n";
   l_query += "      , scval                \n";
   l_query += "      , scocas               \n";
   l_query += "   from pgmr.web_ord_test    \n";
   l_query += "  where tkordi = ?           \n";

   pstm = m_connection.prepareStatement(l_query);

   ind = 1;
   pstm.setLong(ind++, tkordi);

   rs = pstm.executeQuery();

   if (rs != null && rs.next()) {

       if (rs.getObject("imptrasp") != null) {
           lstr.imptrasp = rs.getDouble("imptrasp");
       }
       if (rs.getObject("scval") != null) {
           lstr.scval = rs.getDouble("scval");
       }
       if (rs.getObject("scocas") != null) {
           lstr.scocas = rs.getDouble("scocas");
       }
   }
   pstm.close();
   pstm = null;


   l_query = "";
   l_query += " select sum(iva)             \n";
   l_query += "   from pgmr.web_ord_iva     \n";
   l_query += "  where tkordi = " + tkordi + "  \n";

   lstr.tot_iva = sql_double(l_query);

   lstr.tot_imponibile = lstr.tot_importonettoriga;

   // --- Applico lo sconto cassa
   lstr.tot_imponibile = lstr.tot_imponibile * (100 - lstr.scocas) / 100;

   // --- Applico lo sconto promozione
   lstr.tot_imponibile = lstr.tot_imponibile - lstr.scval;

   // --- Aggiungo le spese di trasporto
   lstr.tot_imponibile = lstr.tot_imponibile + lstr.imptrasp;


   lstr.tot_ivato = lstr.tot_imponibile + lstr.tot_iva;
   
   Str_ordven_tot lstr_tot = lstr;
   
   //get CondVen
   
   Str_ordven_cond lstr_con =  new Str_ordven_cond();
   Str_ordven_cond lstr_cond =  new Str_ordven_cond();

   // --------------------------------------------
   // ---      CONDIZIONI DI VENDITA STD       ---
   // --------------------------------------------

   // vrifico se ho una condizione particolare 
   // per questo cliente

   tot_rec = 0;

   l_query = "";
   l_query += " select impmin              \n";
   l_query += "      , qtamin              \n";
   l_query += "   from pgmr.condven        \n";
   l_query += "  where tkclie = ?          \n";
   l_query += "  order by tkcondven desc   \n";

   pstm = m_connection.prepareStatement(l_query);

   ind = 1;
   pstm.setString(ind, tkclie);
   ind += 1;

   rs = pstm.executeQuery();

   if (rs != null && rs.next()) {

       // Se l'importo ? zero ==> questo cliente non ha minimo ordine
       if (rs.getObject("impmin") != null) {
           lstr_con.impmin = rs.getDouble("impmin");
       }
       if (rs.getObject("qtamin") != null) {
           lstr_con.qtamin = rs.getDouble("qtamin");
       }

       lstr_cond = lstr_con;
   }

   pstm.close();
   pstm = null;

   // --- trovo la classe di scont del cliente

   l_query = "";
   l_query += " select cdclac                \n";
   l_query += "   from pgmr.archclie         \n";
   l_query += "  where tkclie = '" + tkclie + "' \n";

   String cdclac = sql_String(l_query);

   String ls_fgweb = "N";
   int li_tot_ordven = 0;

   if (ls_fgweb.equals("S") && li_tot_ordven <= 0) {

       // questo ? un nuovo cliente e fa il primo ordine web quindi 
       // verifico  se  ci  sono delle maggiorazioni per il primo  
       // acquisto WEB

       l_query = "";
       l_query += " select impmin             \n";
       l_query += "      , qtamin             \n";
       l_query += "   from pgmr.condven       \n";
       l_query += "  where fgnew  = ?         \n";
       l_query += "    and cdclac = ?         \n";
       l_query += "  order by tkcondven desc  \n";

       pstm = m_connection.prepareStatement(l_query);
       ind = 1;
       pstm.setString(ind, "S");
       ind += 1;
       pstm.setString(ind, cdclac);
       ind += 1;

       rs = pstm.executeQuery();

       if (rs != null && rs.next()) {
           if (rs.getObject("impmin") != null) {
               lstr_con.impmin = rs.getDouble("impmin");
           }
           if (rs.getObject("qtamin") != null) {
               lstr_con.qtamin = rs.getDouble("qtamin");
           }

           lstr_cond = lstr_con;
       }
       pstm.close();
       pstm = null;


       // Controllo delle condizioni di vendita di default

       l_query = "";
       l_query += " select impmin             \n";
       l_query += "      , qtamin             \n";
       l_query += "   from pgmr.condven       \n";
       l_query += "  where fgnew = 'S'        \n";
       l_query += "    and cdclac  is null    \n";
       l_query += "    and tkclie  is null    \n";
       l_query += "  order by tkcondven desc  \n";

       pstm = m_connection.prepareStatement(l_query);

       rs = pstm.executeQuery();

       if (rs != null && rs.next()) {
           if (rs.getObject("impmin") != null) {
               lstr_con.impmin = rs.getDouble("impmin");
           }
           if (rs.getObject("qtamin") != null) {
               lstr_con.qtamin = rs.getDouble("qtamin");
           }

           lstr_cond = lstr_con;
       }
       pstm.close();
       pstm = null;

   }   // FINE if (fgnew.equals("S")){

   // Controllo delle condizioni di vendita del carrello

   l_query = "";
   l_query += " select impmin             \n";
   l_query += "      , qtamin             \n";
   l_query += "   from pgmr.condven       \n";
   l_query += "  where fgnew   = ?        \n";
   l_query += "    and cdclac  = ?        \n";
   l_query += "  order by tkcondven desc  \n";

   pstm = m_connection.prepareStatement(l_query);
   ind = 1;
   pstm.setString(ind, "N");
   ind += 1;
   pstm.setString(ind, cdclac);
   ind += 1;

   rs = pstm.executeQuery();

   if (rs != null && rs.next()) {
       if (rs.getObject("impmin") != null) {
           lstr_con.impmin = rs.getDouble("impmin");
       }
       if (rs.getObject("qtamin") != null) {
           lstr_con.qtamin = rs.getDouble("qtamin");
       }

       lstr_cond = lstr_con;
   }

   pstm.close();
   pstm = null;


   // Controllo delle condizioni di vendita di default


   l_query = "";
   l_query += " select impmin                \n";
   l_query += "      , qtamin                \n";
   l_query += "   from pgmr.condven          \n";
   l_query += "  where fgnew = 'N'           \n";
   l_query += "    and cdclac  is null       \n";
   l_query += "    and tkclie  is null       \n";
   l_query += "  order by tkcondven desc     \n";

   pstm = m_connection.prepareStatement(l_query);

   rs = pstm.executeQuery();

   if (rs != null && rs.next()) {
       if (rs.getObject("impmin") != null) {
           lstr_con.impmin = rs.getDouble("impmin");
       }
       if (rs.getObject("qtamin") != null) {
           lstr_con.qtamin = rs.getDouble("qtamin");
       }

       lstr_cond = lstr_con;

   }
   pstm.close();
   pstm = null;
   //FINE
 
   String tafasc = "S";
   String dsfisc = "";

   Assofiscal assofiscal = new Assofiscal();

   setProfilo((Atk_sql) assofiscal);

   rs = assofiscal.getTafasc(cdfisc);

   if (rs != null && rs.next()) {
       if (rs.getObject("tafasc") != null)            tafasc = rs.getString("tafasc");
       if (rs.getObject("dsfisc") != null)            dsfisc = rs.getString("dsfisc");
   }

   String cdlist = "";

   l_query = "";
   l_query += " select cdlist            \n";
   l_query += "   from pgmr.archclie    \n";
   l_query += "  where tkclie = '" + tkclie + "' \n";

   cdlist = sql_String(l_query);

   ls_tbl_order_details += "<table width=\"359\" class=\"parametri\">";
   ls_tbl_order_details += "<tr>";
   ls_tbl_order_details += "<th width=\"140\" align=\"left\" valign=\"top\" nowrap>"+html.text(totale_ordine_mail)+"<br/>"+html.text(iva_esclusa_mail)+":</th>";
   if("LUS".equals(cdlist)) {
       //se il cliente � americano stampo in dollari
       ls_tbl_order_details += "<td valign=\"top\" style=\"font-size:16px;font-weight:bold;\" align=\"right\">" + przFormat.format(lstr_tot.tot_importonettoriga) + " USD</td>";
   } else {
       ls_tbl_order_details += "<td valign=\"top\" style=\"font-size:16px;font-weight:bold;\" align=\"right\">" + przFormat.format(lstr_tot.tot_importonettoriga) + " &euro;</td>";
   }
   ls_tbl_order_details += "</tr>";
    if (lstr_tot.tot_importonettoriga >= lstr_cond.impmin) {

        if (lstr_tot.scval > 0) {
           ls_tbl_order_details += "<tr>";
           ls_tbl_order_details += "<th width=\"140\" align=\"left\" valign=\"top\" nowrap>Sconto Promozione:</th>";
           ls_tbl_order_details += "<td class=\"prezzo\" valign=\"top\" align=\"right\">" + przFormat.format(lstr_tot.scval) + "</td>";
           ls_tbl_order_details += "</tr>";
        }

        if (lstr_tot.imptrasp > 0) {

           ls_tbl_order_details += "<tr>";
           ls_tbl_order_details += "<th width=\"140\" align=\"left\" valign=\"top\" nowrap>"+html.text(spese_trasporto_mail)+":</th>";
           ls_tbl_order_details += "<td class=\"prezzo\" valign=\"top\" align=\"right\">" + przFormat.format(lstr_tot.imptrasp) + " &euro;</td>";
           ls_tbl_order_details += "</tr>";
        } else {
           ls_tbl_order_details += "<tr>";
           ls_tbl_order_details += "<th width=\"140\" align=\"left\" valign=\"top\" nowrap>"+html.text(spese_trasporto_mail)+":</th>";
           ls_tbl_order_details += "<td class=\"nota\" align=\"right\">"+html.text(nota_ordine_mail)+" (2)</td>";
           ls_tbl_order_details += "</tr>";

        }
        if (lstr_tot.scocas > 0) {
           ls_tbl_order_details += "<tr>";
           ls_tbl_order_details += "<th width=\"140\" align=\"left\" valign=\"top\" nowrap>Sconto Cassa:</th>";
           ls_tbl_order_details += "<td class=\"prezzo\" align=\"right\">" + numFormat.format(lstr_tot.scocas) + " (Vedi note ***)</td>";
           ls_tbl_order_details += "</tr>";
        }

        

    }   // FINE if (lstr_tot.tot_importonettoriga >= lstr_cond.impmin) {
    
    if (lstr_tot.tot_importonettoriga < lstr_cond.impmin) {
       ls_tbl_order_details += "<tr>";
       ls_tbl_order_details += "<td class=\"nota\" colspan=\"2\">(Importo minimo ordine di &euro; " + przFormat.format(lstr_cond.impmin) + " non raggiunto.)</td>";
       ls_tbl_order_details += "</tr>";
    }
//       
    if (lstr_tot.scocas > 0) {
       ls_tbl_order_details += "<tr>";
       ls_tbl_order_details += "<td class=\"nota\" colspan=\"2\">*** Sconto Cassa in caso di pagamento alla Consegna.</td>";
       ls_tbl_order_details += "</tr>";
    }
//        

   ls_tbl_order_details += "</table>";
    
   ao_map.put("${ov_ricez.ls_tbl_order_details}"        , ls_tbl_order_details                          );

    ep_costanti.close();
    costanti_comm.close();
    mrp_arch_stato.close();
    mrp_arch_articoli.close();
    
    
    
    return ao_map;
  }

    public HashMap of_setPar_Vist_log_notif (HashMap ao_map, long tklog_notif, String cdling) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    if (ao_map ==null){
      ao_map = new HashMap();
    }
    
    HTML  html  = new HTML();
        
      
    Vist_log_notif vist_log_notif = new Vist_log_notif();
    Ep_costanti ep_costanti = new Ep_costanti();
    Atk_dwlingua  atk_dwlingua = new Atk_dwlingua();

    setProfilo((Atk_sql) vist_log_notif);
    setProfilo((Atk_sql) ep_costanti);
    setProfilo((Atk_sql) atk_dwlingua);
    
    
    String  l_query = "";
    
    
    
    long tkutente_to = 0;
    
    rs = vist_log_notif.getKey(tklog_notif);
    
    if (rs!= null && rs.next()){
      tkutente_to = 0;
      
      if (rs.getObject("tkutente_to") != null)  tkutente_to = rs.getLong("tkutente_to"); 
    }

    

    
    String ep_no_acc_gg_blocco    = ep_costanti.getCostvalue("ep.no_acc_gg_blocco");
    
    String ls_naccb_tbl_clie = "";
    
    
    l_query  = "";
    l_query  = "   select clie.cdclie_m                                 \n";
    l_query += "        , ente.ragcog                                   \n";
    l_query += "        , logp.nrposi                                   \n";
    l_query += "     from pgmr.vist_log_notif       log                 \n";
    l_query += "        , pgmr.vist_log_notif_posi  logp                \n";
    l_query += "        , pgmr.archclie             clie                \n";
    l_query += "        , pgmr.archenti             ente                \n";
    l_query += "    where log.tklog_notif = logp.tklog_notif            \n";
    l_query += "      and log.tpnotif     = 'NO_ACC_BLOCK'              \n";
    l_query += "      and logp.tkclie     = clie.tkclie                 \n";
    l_query += "      and clie.cdente     = ente.cdente                 \n";
    l_query += "      and log.tklog_notif = "+ tklog_notif +"           \n";
    l_query += " order by logp.nrposi                                   \n";
    l_query += "        , ente.ragcog                                   \n";
    l_query += "        , clie.cdclie_m                                 \n";
    
    pstm = m_connection.prepareStatement(l_query);
    
    ind = 1;
    
    rs = pstm.executeQuery();
    
    if (rs!=null && rs.next()){
      
      String ls_label_cdclie_m    = atk_dwlingua.getLabel("epRep_controllo_no_accessi", cdling, "no_acc.report.cdclie_m"  , "", null);
      String ls_label_ragcog      = atk_dwlingua.getLabel("epRep_controllo_no_accessi", cdling, "no_acc.report.ragcog"    , "", null);
    
      
      ls_naccb_tbl_clie  = "";
      ls_naccb_tbl_clie  = " <table width=\"709\" cellpadding=\"0\" cellspacing=\"0\" class=\"report\">                                                                             \n";
      ls_naccb_tbl_clie += "   <tr>                                                                                                                                                 \n";
      ls_naccb_tbl_clie += "     <th style=\"padding-left:5px;\" width=\"170\" align=\"left\" valign=\"top\" nowrap class=\"Stile1\">"+ ls_label_cdclie_m   +"</th>                 \n";
      ls_naccb_tbl_clie += "     <th style=\"padding-left:5px;\" width=\"170\" align=\"left\" valign=\"top\" nowrap class=\"Stile1\">"+ ls_label_ragcog     +"</th>                 \n";
      ls_naccb_tbl_clie += "   </tr>                                                                                                                                                \n";

      
      do {
        
        String   cdclie_m  = "";
        String   ragcog    = "";

        if (rs.getObject("cdclie_m") != null)  cdclie_m = rs.getString("cdclie_m");  
        if (rs.getObject("ragcog"  ) != null)  ragcog   = rs.getString("ragcog"  );  
        
        ls_naccb_tbl_clie += " <tr>                                                                                                                                 \n";
        ls_naccb_tbl_clie += "     <td style=\"padding-left:5px;\" valign=\"top\" align=\"left\" class=\"Stile1\">"+ cdclie_m                 +"</td>               \n";
        ls_naccb_tbl_clie += "     <td style=\"padding-left:5px;\" valign=\"top\" align=\"left\" class=\"Stile1\">"+ html.text(ragcog)        +"</td>               \n";
        ls_naccb_tbl_clie += " </tr>                                                                                                                                \n";
      
        
      } while (rs!=null && rs.next());
      
      ls_naccb_tbl_clie += " </table>                \n";
    }
    
    pstm.close();
    pstm = null;
    
    
    ao_map.put("${epro_naccb.gg_blocco}"             , ep_no_acc_gg_blocco                         );
    ao_map.put("${epro_naccb.naccb_tbl_clie}"        , ls_naccb_tbl_clie                           );

    
    ao_map = of_setPar_Ep_utente(ao_map, tkutente_to, cdling);
    
    
    vist_log_notif.close();
    ep_costanti.close();
    atk_dwlingua.close();

    return ao_map;
  }

  public HashMap of_setPar_Mrp_arch_articoli (HashMap ao_map, String cdarti, String cdling) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    if (ao_map ==null){
      ao_map = new HashMap();
    }

    Mrp_arch_articoli  mrp_arch_articoli = new Mrp_arch_articoli();

    setProfilo((Atk_sql) mrp_arch_articoli);
    
    
    String cdartm = "";
    String dsarti = "";

    
    rs = mrp_arch_articoli.getKey(cdarti, cdling);
    
    if (rs != null && rs.next()){
      
      cdartm = "";
      dsarti = "";
      
      if (rs.getObject("cdartm"    ) != null)  cdartm = rs.getString("cdartm"    ); 
      if (rs.getObject("dsarti_lin") != null)  dsarti = rs.getString("dsarti_lin"); 
      
    }   

    
    ao_map.put("${mrp_arch_articoli.cdartm}" , cdartm                    );
    ao_map.put("${mrp_arch_articoli.dsarti}" , dsarti                    );


    mrp_arch_articoli.close();
    
    return ao_map;
  }




  public String of_is_HTML(String as_text) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    String fghtml = "N";
    String ls_text = as_text.toLowerCase();


    String[] lo_arr_tag = of_getArray_HTML_tag();

    int li_=0;
    
    while (lo_arr_tag !=null && li_ < lo_arr_tag.length && !fghtml.equals("S")){

      String ls_tag_search1 = lo_arr_tag[li_];
      String ls_tag_search2 = lo_arr_tag[li_].replace(">", " ");

      if (ls_text.indexOf(ls_tag_search1) >= 0){
        fghtml = "S";
      } else if (ls_text.indexOf(ls_tag_search2) >= 0){
        fghtml = "S";
      }

      li_++;
    }

    
    return fghtml;
  }

  public String[] of_getArray_HTML_tag() throws Exception {

    String[] lo_arr_tag = new String[20];

    int li_ = 0;

    lo_arr_tag[li_++] = "<a>"; 
    lo_arr_tag[li_++] = "<img>"; 

    lo_arr_tag[li_++] = "<p>"; 
    lo_arr_tag[li_++] = "<br>"; 
    lo_arr_tag[li_++] = "<span>"; 

    lo_arr_tag[li_++] = "<b>"; 
    lo_arr_tag[li_++] = "<i>"; 
    lo_arr_tag[li_++] = "<u>"; 

    lo_arr_tag[li_++] = "<h1>"; 
    lo_arr_tag[li_++] = "<h2>"; 
    lo_arr_tag[li_++] = "<h3>"; 
    
    lo_arr_tag[li_++] = "<table>"; 
    lo_arr_tag[li_++] = "<tr>"; 
    lo_arr_tag[li_++] = "<th>"; 
    lo_arr_tag[li_++] = "<td>"; 

    lo_arr_tag[li_++] = "<ul>"; 
    lo_arr_tag[li_++] = "<li>"; 

    lo_arr_tag[li_++] = "<dl>"; 
    lo_arr_tag[li_++] = "<dt>"; 
    lo_arr_tag[li_++] = "<dd>"; 

    return lo_arr_tag;

  }
  
  public String getAnnotazione_posi(long tkposi, String tiponota) throws Exception {

      int ind = 0;
      int tot_rec = 0;
      PreparedStatement pstm = null;
      ResultSet rs = null;


      String ls_testo = "";

      l_query = "";
      l_query += " select riga                        \n";
      l_query += "      , testo                       \n";
      l_query += "   from pgmr.web_ord_posi_note      \n";
      l_query += "  where tkposi   = " + tkposi + "       \n";
      l_query += "    and tiponota = '" + tiponota + "'   \n";
      l_query += "  order by riga                     \n";

      pstm = m_connection.prepareStatement(l_query);

      rs = pstm.executeQuery();

      while (rs != null && rs.next()) {
          if (rs.getObject("testo") != null) {
              ls_testo += rs.getString("testo");
          }

      }
      pstm.close();
      pstm = null;

      return ls_testo;

  }
  
  public String of_getWeb_ord_test_annotazione(long tkordi, String tiponota) throws Exception {

      int ind = 0;
      int tot_rec = 0;
      PreparedStatement pstm = null;
      ResultSet rs = null;


      String ls_testo = "";

      l_query = "";
      l_query += " select riga                        \n";
      l_query += "      , testo                       \n";
      l_query += "   from pgmr.web_ord_test_note      \n";
      l_query += "  where tkordi   = " + tkordi + "       \n";
      l_query += "    and tiponota = '" + tiponota + "'   \n";
      l_query += "  order by riga                     \n";

      pstm = m_connection.prepareStatement(l_query);

      rs = pstm.executeQuery();

      while (rs != null && rs.next()) {
          if (rs.getObject("testo") != null) {
              ls_testo += rs.getString("testo");
          }

      }
      pstm.close();
      pstm = null;

      return ls_testo;

  }
  

  public HashMap of_setPar_Ep_subutente (HashMap ao_map, long al_tksubutente, String cdling) throws Exception {
        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        if (ao_map ==null){
          ao_map = new HashMap();
        }

        String tkclie_subutente = sql_String("select tkclie from pgmr.ep_subutente where tksubutente = "+ al_tksubutente);
        
        ao_map = of_setPar_Archclie(ao_map, tkclie_subutente, cdling);


        return ao_map;
  }
  

  public HashMap of_setPar_Vist_premi (HashMap ao_map, Str_key_vist_premi str_key_vist_premi, String cdling) throws Exception {
        int ind = 0;
        int tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        if (ao_map ==null){
          ao_map = new HashMap();
        }
    
        HTML  html  = new HTML();
        Atk_ctrl  atk_ctrl  = new Atk_ctrl();
        
        
//        Atk_dwlingua  atk_dwlingua = new Atk_dwlingua();
//
//        setProfilo((Atk_sql) atk_dwlingua);
//        
//        
//        java.text.NumberFormat  numFormat = java.text.NumberFormat.getInstance(java.util.Locale.ITALY);
//
//        numFormat.setGroupingUsed( true );
//        numFormat.setMaximumFractionDigits( 2 );
//        numFormat.setMinimumFractionDigits( 2 );
//
//        
//        
//        
//        Timestamp dtoggi = of_getOggi();
//        
//        double  fatturato_attuale = 0; 
//        double  limfatt1 = 0; 
//        double  limfatt2 = 0; 
//        double  limfatt3 = 0; 
//        double  limfatt4 = 0; 
//        double  limfatt5 = 0; 
//        double  premio1 = 0; 
//        double  premio2 = 0; 
//        double  premio3 = 0; 
//        double  premio4 = 0; 
//        double  premio5 = 0; 
//        
//        
//        String  l_query  = "";
//        
//        l_query  = "";
//        l_query += " select *                                                     \n";
//        l_query += "   from pgmr.vist_premi                                       \n";
//        l_query += "  where tkclie = '"+ str_key_vist_premi.tkclie +"'            \n";
//        l_query += "    and cdagen = '"+ str_key_vist_premi.cdagen +"'            \n";
//        l_query += "    and anno = "+ str_key_vist_premi.anno +"                  \n";
//
//        pstm = m_connection.prepareStatement(l_query);
//      
//        ind = 1;
//
//        rs = pstm.executeQuery();
//
//        if (rs != null && rs.next()) {
//            fatturato_attuale = 0; 
//            limfatt1 = 0; 
//            limfatt2 = 0; 
//            limfatt3 = 0; 
//            limfatt4 = 0; 
//            limfatt5 = 0; 
//            premio1 = 0; 
//            premio2 = 0; 
//            premio3 = 0; 
//            premio4 = 0; 
//            premio5 = 0; 
//            
//            if (rs.getObject("valore") != null)    fatturato_attuale = rs.getDouble("valore");
//
//            if (rs.getObject("limfatt1") != null)  limfatt1 = rs.getDouble("limfatt1");
//            if (rs.getObject("limfatt2") != null)  limfatt2 = rs.getDouble("limfatt2");
//            if (rs.getObject("limfatt3") != null)  limfatt3 = rs.getDouble("limfatt3");
//            if (rs.getObject("limfatt4") != null)  limfatt4 = rs.getDouble("limfatt4");
//            if (rs.getObject("limfatt5") != null)  limfatt5 = rs.getDouble("limfatt5");
//            if (rs.getObject("premio1") != null)  premio1 = rs.getDouble("premio1");
//            if (rs.getObject("premio2") != null)  premio2 = rs.getDouble("premio2");
//            if (rs.getObject("premio3") != null)  premio3 = rs.getDouble("premio3");
//            if (rs.getObject("premio4") != null)  premio4 = rs.getDouble("premio4");
//            if (rs.getObject("premio5") != null)  premio5 = rs.getDouble("premio5");
//        }
//
//        pstm.close();
//        pstm = null;
//        
//        int count_premi = 0;
//        
//        if (premio1 > 0) count_premi ++;
//        if (premio2 > 0) count_premi ++;
//        if (premio3 > 0) count_premi ++;
//        if (premio4 > 0) count_premi ++;
//        if (premio5 > 0) count_premi ++;
//        
//        double[] arr_ld_premi = new double[count_premi];
//        double[] arr_ld_limfatt = new double[count_premi];
//        
//        int idx_premio = 0;
//        
//        if (premio1 > 0){
//            arr_ld_premi[idx_premio] = premio1;
//            arr_ld_limfatt[idx_premio] = limfatt1;
//            idx_premio++;
//        }
//        if (premio2 > 0){
//            arr_ld_premi[idx_premio] = premio2;
//            arr_ld_limfatt[idx_premio] = limfatt2;
//            idx_premio++;
//        }
//        if (premio3 > 0){
//            arr_ld_premi[idx_premio] = premio3;
//            arr_ld_limfatt[idx_premio] = limfatt3;
//            idx_premio++;
//        }
//        if (premio4 > 0){
//            arr_ld_premi[idx_premio] = premio4;
//            arr_ld_limfatt[idx_premio] = limfatt4;
//            idx_premio++;
//        }
//        if (premio5 > 0){
//            arr_ld_premi[idx_premio] = premio5;
//            arr_ld_limfatt[idx_premio] = limfatt5;
//            idx_premio++;
//        }
//        
// 
//        
//        int anno = (dtoggi.getYear() + 1900);
//        
//        Calendar c = Calendar.getInstance();
//        c.setTime(dtoggi);
//        c.add(Calendar.YEAR, -1);
//        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
//        
//        Timestamp dt_anno_prec = new Timestamp(c.getTimeInMillis());
//        
//        int anno_prec = dt_anno_prec.getYear() + 1900;
//        int mese_anno_prec = dt_anno_prec.getMonth() + 1;
//        int giorno_anno_prec = dt_anno_prec.getDate();
//        
//        String periodo_da = ""+ anno_prec + "01" + "01";
//        String periodo_a = ""+ anno_prec + (mese_anno_prec > 9 ? mese_anno_prec : "0"+mese_anno_prec) + giorno_anno_prec;
//        
//        
// 
//        l_query  = "";
//        l_query += "  select sum(importo)                                                         \n";
//        l_query += "    from pgmr.statistiche_vendita                                             \n";
//        l_query += "   where tkclie = '"+ str_key_vist_premi.tkclie +"'                           \n";
//        l_query += "     and cdagen_fatt = '"+ str_key_vist_premi.cdagen +"'                      \n";
//        l_query += "     and periodo >= "+ periodo_da +"                                          \n";
//        l_query += "     and periodo <= "+ periodo_a +"                                           \n";
//        l_query += "     and fg_valid = 'S'                                                       \n";
//        
//        double fatturato_anno_prec = sql_double(l_query);
//        
//        double scostamento = fatturato_attuale - fatturato_anno_prec;
//        
//        
//        
//        String ls_label_dati_fatt = atk_dwlingua.getLabel("srv_premi", cdling, "premi.dati_fatt", "", null);
//        String ls_label_fatturato_anno_prec = atk_dwlingua.getLabel("srv_premi", cdling, "premi.fatturato", "", new String[]{atk_ctrl.getDate(dt_anno_prec)});
//        String ls_label_fatturato_attuale = atk_dwlingua.getLabel("srv_premi", cdling, "premi.fatturato", "", new String[]{atk_ctrl.getDate(dtoggi)});
//        String ls_label_scostamento = atk_dwlingua.getLabel("srv_premi", cdling, "premi.scostamento", "", null);
//        String ls_label_dati_premi = atk_dwlingua.getLabel("srv_premi", cdling, "premi.dati_premi", "", null);
//    
//
//        String ls_premi_tbl_dati_fatt = "";
//    
//        ls_premi_tbl_dati_fatt  = "";
//        ls_premi_tbl_dati_fatt  = "  <table width=\"709\" cellpadding=\"0\" cellspacing=\"0\" class=\"report_premi\">                                                \n";
//        ls_premi_tbl_dati_fatt += "    <tr>                                                                                                                       \n";
//        ls_premi_tbl_dati_fatt += "      <th width=\"170\" align=\"left\" valign=\"top\" nowrap rowspan=\"3\"><b>"+ html.text(ls_label_dati_fatt) +"</b></th>        \n";
//        ls_premi_tbl_dati_fatt += "      <td width=\"170\" align=\"left\" valign=\"top\" nowrap>"+ html.text(ls_label_fatturato_anno_prec) +"</td>        \n";
//        ls_premi_tbl_dati_fatt += "      <td width=\"170\" align=\"right\" valign=\"top\" nowrap>"+ html.text("&euro; "+ numFormat.format(fatturato_anno_prec)) +"</td>        \n";
//        ls_premi_tbl_dati_fatt += "    </tr>                                                                                                                      \n";
//        ls_premi_tbl_dati_fatt += "    <tr>                                                                                                                       \n";
//        ls_premi_tbl_dati_fatt += "      <td width=\"170\" align=\"left\" valign=\"top\" nowrap>"+ html.text(ls_label_fatturato_attuale) +"</td>        \n";
//        ls_premi_tbl_dati_fatt += "      <td width=\"170\" align=\"right\" valign=\"top\" nowrap>"+ html.text("&euro; "+ numFormat.format(fatturato_attuale)) +"</td>        \n";
//        ls_premi_tbl_dati_fatt += "    </tr>                                                                                                                      \n";
//        ls_premi_tbl_dati_fatt += "    <tr>                                                                                                                       \n";
//        ls_premi_tbl_dati_fatt += "      <td width=\"170\" align=\"left\" valign=\"top\" nowrap>"+ html.text(ls_label_scostamento) +"</td>        \n";
//        ls_premi_tbl_dati_fatt += "      <td width=\"170\" align=\"right\" valign=\"top\" nowrap>"+ html.text("&euro; "+ numFormat.format(scostamento)) +"</td>        \n";
//        ls_premi_tbl_dati_fatt += "    </tr>                                                                                                                      \n";
//        ls_premi_tbl_dati_fatt += "  </table>                                                                                                                     \n";
//
//        ao_map.put("${premi.tbl_dati_fatt}"                        , ls_premi_tbl_dati_fatt          );
//    
//
//        String ls_premi_tbl_dati_premio = "";
//    
//        if (count_premi > 0){
//            
//            String th_testata = "      <th width=\"170\" align=\"left\" valign=\"top\" nowrap rowspan=\""+ (count_premi * 3) +"\"><b>"+ html.text(ls_label_dati_premi) +"</b></th>        \n";
//            
//            ls_premi_tbl_dati_premio  = "";
//            ls_premi_tbl_dati_premio  = "  <table width=\"709\" cellpadding=\"0\" cellspacing=\"0\" class=\"report_premi\">                                                \n";
//            
//            for (int i= 0; i < count_premi; i++){
//                ls_premi_tbl_dati_premio += "    <tr>                                                                                                                       \n";
//                if (i == 0){
//                    ls_premi_tbl_dati_premio += th_testata;
//                }
//                ls_premi_tbl_dati_premio += "      <td width=\"170\" align=\"left\" valign=\"top\" nowrap>"+ html.text(atk_dwlingua.getLabel("srv_premi", cdling, "premi.limite_premio", "", new String[]{""+ (i+1)})) +"</td>        \n";
//                ls_premi_tbl_dati_premio += "      <td width=\"170\" align=\"right\" valign=\"top\" nowrap>"+ html.text("&euro; "+ numFormat.format(arr_ld_limfatt[i])) +"</td>        \n";
//                ls_premi_tbl_dati_premio += "    </tr>                                                                                                                      \n";
//                ls_premi_tbl_dati_premio += "    <tr>                                                                                                                       \n";
//                ls_premi_tbl_dati_premio += "      <td width=\"170\" align=\"left\" valign=\"top\" nowrap>"+ html.text(atk_dwlingua.getLabel("srv_premi", cdling, "premi.scostamento_premio", "", new String[]{""+ (i+1)})) +"</td>        \n";
//                ls_premi_tbl_dati_premio += "      <td width=\"170\" align=\"right\" valign=\"top\" nowrap>"+ html.text("&euro; "+ numFormat.format(fatturato_attuale - arr_ld_limfatt[i])) +"</td>        \n";
//                ls_premi_tbl_dati_premio += "    </tr>                                                                                                                      \n";
//                ls_premi_tbl_dati_premio += "    <tr style=\"border-bottom: 1px solid #ccc;\">                                                                                                                       \n";
//                ls_premi_tbl_dati_premio += "      <td style=\"padding-bottom: 10px;\" width=\"170\" align=\"left\" valign=\"top\" nowrap>"+ html.text(atk_dwlingua.getLabel("srv_premi", cdling, "premi.sconto_premio", "", new String[]{""+ (i+1)})) +"</td>        \n";
//                ls_premi_tbl_dati_premio += "      <td style=\"padding-bottom: 10px;\" width=\"170\" align=\"right\" valign=\"top\" nowrap>"+ html.text(numFormat.format(arr_ld_premi[i])+ " %") +"</td>        \n";
//                ls_premi_tbl_dati_premio += "    </tr>                                                                                                                      \n";
//            }
// 
//            ls_premi_tbl_dati_premio += "  </table>                                                                                                                     \n";
//            
//        }
//
//        ao_map.put("${premi.tbl_dati_fatt}"                        , ls_premi_tbl_dati_fatt           );
//        ao_map.put("${premi.tbl_dati_premio}"                      , ls_premi_tbl_dati_premio         );
        
        String cdispe = sql_String("select cdispe from pgmr.archclie where tkclie = '"+ str_key_vist_premi.tkclie +"'");
        
        ao_map = of_setPar_Archclie(ao_map, str_key_vist_premi.tkclie, cdling);
        ao_map = of_setPar_Archagen(ao_map, str_key_vist_premi.cdagen, "", cdling);
        ao_map = of_setPar_Age_ispettori(ao_map, cdispe, cdling);

        
//        atk_dwlingua.close();

        return ao_map;
  }
  
  public HashMap of_setPar_SITE_DOWNR (HashMap ao_map, long tkcontatto   , String cdling) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    if (ao_map ==null){
      ao_map = new HashMap();
    }
    
    Ep_costanti ep_costanti = new Ep_costanti();
    Costanti_comm costanti_comm = new Costanti_comm();
    Mrp_arch_articoli mrp_arch_articoli = new Mrp_arch_articoli();
    Atk_dwlingua  atk_dwlingua = new Atk_dwlingua();
    
    setProfilo((Atk_sql) ep_costanti);
    setProfilo((Atk_sql) costanti_comm);
    setProfilo((Atk_sql) mrp_arch_articoli);
    setProfilo((Atk_sql) atk_dwlingua);

    String ep_portal_url    = ep_costanti.getCostvalue("ep.portal_url");
    ep_portal_url = of_cambiaURLLingua(ep_portal_url, cdling);

    ao_map = of_setPar_Atk_contatti(ao_map, tkcontatto, cdling);
    
    long tkutente = Long.parseLong((String) ao_map.get("${atk_contatti.tkutente_rif}"));
    String ls_url_risorse = (String) ao_map.get("${atk_contatti.url_risorse}");
    String ls_lang = (String) ao_map.get("${atk_contatti.lang}");
    String ls_tiporisorsa  = (String) ao_map.get("${atk_contatti.tiporisorsa}");
    String ls_nome_modello = (String) ao_map.get("${atk_contatti.nome_modello}");
    String ls_cdvistelet   = (String) ao_map.get("${atk_contatti.cdvistelet}");
    String ls_motivo       = (String) ao_map.get("${atk_contatti.motivo}");
    
    if (ls_lang.equals("")){
        ls_lang = sql_String("select cdiso639 from pgmr.ep_lingua where cdling = '"+ cdling + "' ");
    }
    
    ao_map.put("${site_downr.lang}"                      , ls_lang         );
    
    String ls_site_downr_url_risorsa = mrp_arch_articoli.of_getUrl_risorsa_esistente(ls_url_risorse, ls_lang);
    
    if (!ls_site_downr_url_risorsa.equals("")){
        ls_site_downr_url_risorsa = ep_portal_url + "download/"+ ls_site_downr_url_risorsa.replace("|", " ") +"?f="+ URLEncoder.encode(ls_site_downr_url_risorsa, "UTF-8") +"&tkc="+ tkcontatto +"&lang="+ ls_lang;
    } else {
        ls_site_downr_url_risorsa = ep_portal_url; 
    }
    
    ao_map.put("${site_downr.url_risorsa}"                      , ls_site_downr_url_risorsa         );
    
    
    String ls_mail_uff_tecnico = costanti_comm.getCostvalue("site.mail_req_file").trim();

    if(ls_url_risorse.indexOf("catimages") >= 0){ //richiesta di immagini prodotto vanno inoltrate a Eleonora issue #2920
        ls_mail_uff_tecnico = "marketing@vistosi.it";
    }else if (ls_mail_uff_tecnico.equals("")){
        ls_mail_uff_tecnico = "info@vistosi.it";
    }
    
    ao_map.put("${site_downr.email_uff_tecnico}"                , ls_mail_uff_tecnico         );
    
    
    String ls_nomeRisorsaMancante = ls_url_risorse;
    
    if(ls_nomeRisorsaMancante.indexOf("IM|") >= 0 || ls_nomeRisorsaMancante.indexOf("SS|") >= 0){
        ls_nomeRisorsaMancante = ls_nomeRisorsaMancante.replace("IM|", "");
        ls_nomeRisorsaMancante = ls_nomeRisorsaMancante.replace("SS|", "");
//        ls_nomeRisorsaMancante = ls_nomeRisorsaMancante.replace("|", "");
    }
    
    ao_map.put("${site_downr.nomeRisorsaMancante}"                      , ls_nomeRisorsaMancante         );
    
    
    String ls_text_risorsa_anticipo = "";
    String ls_site_downr_url_risorsa_anticipo = ep_portal_url; 
    
    if (ls_motivo.equals("Richiesta download")){
        Str_risorsa_anticipo str_risorsa_anticipo = mrp_arch_articoli.of_getRisorsa_anticipo(ls_url_risorse, ls_tiporisorsa, ls_nome_modello, ls_cdvistelet, tkcontatto, ls_lang, cdling);

        if (str_risorsa_anticipo != null){
            ls_site_downr_url_risorsa_anticipo = str_risorsa_anticipo.url_res_anticipo;
            ls_text_risorsa_anticipo  = atk_dwlingua.getLabel ("srv_vendita", cdling, "text_"+ str_risorsa_anticipo.tipo_res_anticipo, "", new String[]{ls_nome_modello});
        }
    }
    
    ao_map.put("${site_downr.url_risorsa_anticipo}"                      , ls_site_downr_url_risorsa_anticipo         );
    ao_map.put("${site_downr.text_risorsa_anticipo}"                     , ls_text_risorsa_anticipo                      );
    

    ao_map = of_setPar_Ep_utente(ao_map, tkutente, cdling);
    
    
    ep_costanti.close();
    costanti_comm.close();
    mrp_arch_articoli.close();

    return ao_map;

  }
  
  

  public HashMap of_setPar_Vist_oridati (HashMap ao_map, String oridati, String cdling) throws Exception {
    int ind = 0;
    int tot_rec = 0;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    if (ao_map ==null){
      ao_map = new HashMap();
    }

    Vist_oridati  vist_oridati = new Vist_oridati();

    HTML html = new HTML();

    setProfilo((Atk_sql) vist_oridati);


    String dsoridati        = vist_oridati.getDs(oridati, cdling);
    
    ao_map.put("${vist_oridati.dsoridati}"        , dsoridati                           );


    vist_oridati.close();      
    
    return ao_map;
  }
  
  public static String  COM_AMM = "COM_AMM";
  public static String  COM_TEC = "COM_TEC";
  public static String  COM_COM = "COM_COM";

  public static String IS_ARCHAGEN_LIVELLO_CAPOAREA = "CAPO";
  public static String IS_ARCHAGEN_LIVELLO_AGENTE_RIF = "AGENRIF";
  public static String IS_ARCHAGEN_LIVELLO_AGENTE = "AGENTE";
  
  public static String CDPTIT_DEF = "";
  public static String CDTPSESSO_FEMMINILE = "F";
  public static String CDTPSESSO_MASCHILE = "M";
}