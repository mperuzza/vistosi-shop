package com.ateikon.common;


import com.ateikon.structure.Rec_ep_lingua_lb;
import com.ateikon.structure.Rec_ep_lingua_url;
import com.ateikon.structure.Rec_ep_utente;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class Atk_dwlingua extends Atk_sql implements java.io.Serializable {

    public Atk_dwlingua() {

        super();
    }


    /*
     * NOTA: utilizzo questa classe per motivi storici (veniva usata nel catalogo). Essa recuperava i dati dal DB
     *       dalla tabella pgmr.atk_dwlingua. Ora invece recupero le label direttamente dal file di properties della
     *       pagina che cerco
     *
     *       //avendramin20110715 -- da ora recupera nuovamente i dati da db, ma dalle tabelle pgmr.ep_lingua*
     *
     */


    public String dwin = "";
    public String cdling = "";

    public String getLabel ( String cdcampo
                           , String fgdef
                                                    ) throws Exception {

        return getLabel (this.dwin, this.cdling, cdcampo, fgdef, null);

    }

    public String getLabel ( String cdcampo
                           , String fgdef
                           , String cdling
                                                    ) throws Exception {

        return getLabel (this.dwin, cdling, cdcampo, fgdef, null);

    }

    public String getLabel ( String cdcampo
                           , String fgdef
                           , String [] args
                                                    ) throws Exception {

        return getLabel (this.dwin, this.cdling, cdcampo, fgdef, args);

    }

    public String getLabel ( String cdcampo
                           , String fgdef
                           , String cdling
                           , String [] args
                                                    ) throws Exception {

        return getLabel (this.dwin, cdling, cdcampo, fgdef, args);

    }

    public String getLabel ( String dwin
                           , String cdling
                           , String cdcampo
                           , String fgdef
                           , String [] args
                                                    ) throws Exception {

        return getLabel (dwin, cdling, cdcampo, fgdef, args, getEp_lingua_prg_cdprogetto());
    }

    //avendramin20110712 - I
    public String getLabel ( String dwin
                           , String cdling
                           , String cdcampo
                           , String fgdef
                           , String [] args
                           , String cdprogetto
                                                    ) throws Exception {
        PreparedStatement pstm = null;
        ResultSet         rs = null;
        int               ind = 0;
        int               tot_rec = 0;

        //System.out.println("*******************************");
        //System.out.println("*******************************");

        String dsling = "";

        Ep_lingua  ep_lingua = new Ep_lingua();
        Ep_lingua_lb  ep_lingua_lb = new Ep_lingua_lb();

        setProfilo((Atk_sql) ep_lingua);
        setProfilo((Atk_sql) ep_lingua_lb);

        //converto cdling che mi arriva (da pgmr.atk_lingua) con cdling presente in ep_lingua
        cdling = getCdling_ep_lingua(cdling);

        //System.out.println("cdling "+ cdling);


        //Recupero cdling di default
        String cdling_def = ep_lingua.getCdling_def();

        //Setto cdling a default se fgdef = 'S'
        if (fgdef.equals("S")) cdling = cdling_def;

        //System.out.println("cdling_def "+ cdling_def);
        //System.out.println("fgdef "+ fgdef);



        //Recupero il tklingua_url
        long tklingua_url = getTklingua_url(dwin, cdprogetto);

        //System.out.println("tklingua_url "+ tklingua_url);


        //Recupero la descrizione del campo
        rs = ep_lingua_lb.getKey(cdling, tklingua_url , cdcampo);

        Rec_ep_lingua_lb lstr_lb = new Rec_ep_lingua_lb();

        if (rs!= null && rs.next()){
          lstr_lb.setResultSet(rs);
        }

        dsling = lstr_lb.dscampo;


        //Se descrizione del campo non valorizzato e non sto cercando nella lingua di default provo a cercare nella lingua di default
        if (dsling.equals("") && !fgdef.equals("S")) dsling = this.getLabel(dwin, cdling, cdcampo, "S", args );

        if (dsling.equals("")){
            dsling = cdcampo;

            String[] arr_prefix = new String[] {"param.", "js.", "html.", "column.", "title.", "button."};
            
            for (int i=0; i<arr_prefix.length; i++){
                if (dsling.indexOf(arr_prefix[i]) == 0){
                    dsling = dsling.substring(arr_prefix[i].length());
                }
            }
            
        }

        //Imposto gli eventuali argomenti passati
        for (int i=0; args != null && i < args.length; i++){
            dsling = dsling.replace("{"+ i + "}", args[i]);
        }

        dsling = dsling.replace("''", "'");

        ep_lingua.close();
        ep_lingua_lb.close();


        return dsling;

    }
    //avendramin20110712 - F

    //avendramin20110713 - F
    public long getTklingua_url(String dwin, String cdprogetto)  throws Exception {

        Ep_lingua_url  ep_lingua_url = new Ep_lingua_url();

        setProfilo((Atk_sql) ep_lingua_url);


        //Recupero relativepath e filename
        int index = dwin.lastIndexOf("/");

        String relativepath = (index >= 0 ? dwin.substring(0,index + 1) : "");
        String filename = (index >= 0 ? dwin.substring(index + 1, dwin.length()) : dwin);


        if (!dwin.equals("")){
          //Forzo il suffisso .jsp nel filename se non presente
          String ls_jsp = ".jsp";
          int idx_jsp = filename.lastIndexOf(ls_jsp) ;


          if (idx_jsp < 0){
            filename += ls_jsp;
          } else if ((idx_jsp + ls_jsp.length()) != filename.length()){
            filename += ls_jsp;
          }
								}

        //Recupero il tklingua_url
        Rec_ep_lingua_url lstr_url = new Rec_ep_lingua_url();

        ResultSet rs = ep_lingua_url.getKey(cdprogetto, relativepath, filename);

        if (rs!= null && rs.next()){
          lstr_url.setResultSet(rs);
        }

        ep_lingua_url.close();

        return lstr_url.tklingua_url;
    }


    public String getLinkTraduz ( String cdcampo) throws Exception {

        return getLinkTraduz (this.dwin, this.cdling, cdcampo);

    }

    public String getLinkTraduz ( String dwin
                                , String cdling
                                , String cdcampo
                                                         ) throws Exception {

        Ep_lingua_lb   ep_lingua_lb    = new Ep_lingua_lb();

        setProfilo((Atk_sql) ep_lingua_lb);

        //Verifico se siamo in modalit? traduzione
        if (!s_fgmod_trad.equals("S")) return "";

        //Verifico abilitazione a traduzione
        if (!isAbilTraduzioni(s_tkutente)) return "";
        
        
        Ep_lingua  ep_lingua = new Ep_lingua();
        setProfilo((Atk_sql) ep_lingua);
        
        //Recupero cdling di default
        String cdling_def = ep_lingua.getCdling_def();


        //converto cdling che mi arriva (da pgmr.atk_lingua) con cdling presente in pgmr.ep_lingua
        cdling = getCdling_ep_lingua(cdling);

        //Recupero il tklingua_url
        long tklingua_url = getTklingua_url(dwin, getEp_lingua_prg_cdprogetto());

        ResultSet rs = ep_lingua_lb.getKey(cdling_def, tklingua_url , cdcampo);

        Rec_ep_lingua_lb lstr_lb = new Rec_ep_lingua_lb();

        if (rs!= null && rs.next()){
          lstr_lb.setResultSet(rs);
        }

        String ls_html_link  = "";
        ls_html_link  = "<a href=\""+ getLink_traduzioni(lstr_lb.tklingua_lb) +"\" target=\"_blank\"><img src=\"img/translate.png\" border=\"0\"/></a>";


        ep_lingua_lb.close();

        return ls_html_link;

    }
    //avendramin20110713 - F


    //avendramin20110715 - I
    public boolean isAbilTraduzioni( long tkutente ) throws Exception {

      return isAbilTraduzioni( tkutente, "", "");
    }

    public boolean isAbilTraduzioni( long tkutente , String cdling ) throws Exception {

      return isAbilTraduzioni( tkutente, cdling, "");
    }

    public boolean isAbilTraduzioni( long tkutente , String cdling, String cdling_ep_lingua ) throws Exception {


      PreparedStatement pstm = null;
      ResultSet         rs = null;
      int               ind = 0;
      int               tot_rec = 0;

      Ep_utente      ep_utente  = new Ep_utente();
      Ep_lingua      ep_lingua  = new Ep_lingua();

      setProfilo((Atk_sql) ep_utente);
      setProfilo((Atk_sql) ep_lingua);


      boolean lb_ = false;

      if (!cdling.equals("")){
        //converto cdling che mi arriva (da pgmr.atk_lingua) con cdling presente in pgmr.ep_lingua
        cdling = getCdling_ep_lingua(cdling);
      }

      //se presente cdling_ep_lingua, assegno a cdling questo valore
      if (!cdling_ep_lingua.equals("")) cdling = cdling_ep_lingua;


      //verifico l'utente
      rs = ep_utente.getKey(tkutente);

      Rec_ep_utente lstr_eute = new Rec_ep_utente();

      if (rs!= null && rs.next()){

        lstr_eute.setResultSet(rs);

      }

      //? disabilitato
      if (!lstr_eute.fgabil.equals("S")) return false;

      //? amministratore del sistema
      if (lstr_eute.fgadmin.equals("S")) return true;


      //Verifico se ? abilitato a tradurre questa lingua
      Timestamp lt_adesso = of_getTimestamp();
      String  l_query = "";

      l_query  = "";
      l_query  = "  select count(*)                                                          \n";
      l_query += "    from pgmr.ep_lingua_user_trad   elut                                   \n";
      l_query += "   where (elut.dtival is null or elut.dtival <= ?)                         \n";
      l_query += "     and (elut.dtfval is null or elut.dtfval >= ?)                         \n";
      l_query += "     and elut.tkutente = "+ tkutente +"                                    \n";
      if (!cdling.equals("")){
        l_query += "     and elut.cdling = '"+ cdling +"'                                      \n";
      }

      pstm = setQuery(l_query);

      ind = 1;

      pstm.setTimestamp(ind++, lt_adesso);
      pstm.setTimestamp(ind++, lt_adesso);

      rs = pstm.executeQuery();

      if (rs != null && rs.next()){
        int count = 0;
        
        if (rs.getObject(1)!= null) count = rs.getInt(1);
      
        if (count >= 1) lb_ = true;
      }


      ep_utente.close();
      ep_lingua.close();

      return lb_;

    }
    //avendramin20110715 - F


    public String getLinkTraduz_alert ( String cdcampo) throws Exception {

        return getLinkTraduz_alert (this.dwin, this.cdling, cdcampo);

    }    

    public String getLinkTraduz_alert ( String dwin
                                      , String cdling
                                      , String cdcampo
                                                               ) throws Exception {

      com.ateikon.util.HTML html = new com.ateikon.util.HTML();

        String ls_link = "";

        String ls_label = "";

        ls_label = getLabel ( dwin
                            , cdling
                            , cdcampo
                            , ""
                            , null
                            );

        String link_traduz = getLinkTraduz (dwin, cdling, cdcampo);

        if (!link_traduz.equals("")){
          ls_link  = "";
          ls_link  = link_traduz;
          ls_link += "&nbsp;";
          ls_link += "<a href=\"javascript:alert('"+ html.js(ls_label) +"');\">";
          ls_link += html.text(ls_label.replace("\n", " "));
          ls_link += "</a>";
        }


        return ls_link;

    }

    public String getLinksTraduz_alert ( String[] arr_cdcampo) throws Exception {

      return getLinksTraduz_alert ( this.dwin, this.cdling, arr_cdcampo);
    }

    public String getLinksTraduz_alert ( String dwin, String cdling, String[] arr_cdcampo) throws Exception {

      String ls_contenitore_link = "";

      if (arr_cdcampo != null && arr_cdcampo.length > 0){

        String [] arr_dwin = new String[arr_cdcampo.length];
        String [] arr_cdling = new String[arr_cdcampo.length];

        for (int i=0; i < arr_cdcampo.length; i++ ){
          arr_dwin[i] = dwin;
          arr_cdling[i] = cdling;
        }

        ls_contenitore_link = getLinksTraduz_alert ( arr_dwin, arr_cdling, arr_cdcampo);
      }  


      return ls_contenitore_link;

    }    

    public String getLinksTraduz_alert ( String[] arr_dwin, String[] arr_cdling, String[] arr_cdcampo) throws Exception {

        String ls_contenitore_link  = "";
        String ls_links_traduz  = "";
        
        if (arr_cdcampo != null && arr_cdcampo.length > 0){

          for (int i=0; i < arr_cdcampo.length; i++ ){
            if (!ls_links_traduz.equals("")) ls_links_traduz += "<br />";
            ls_links_traduz += getLinkTraduz_alert (arr_dwin[i], arr_cdling[i], arr_cdcampo[i]);;
          }

        }

        if (!ls_links_traduz.equals("")){

          ls_contenitore_link  = "";
          ls_contenitore_link += "<div class=\"contenitore_links_alert\">";
          ls_contenitore_link += ls_links_traduz;
          ls_contenitore_link += "</div>";

        }

        return ls_contenitore_link;

    }    


}
