package com.ateikon.common;

import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.PreparedStatement;


/**********************/
//     NOTA:
//     modificare questa classe nel portale implica un allineamento della stessa classe in eprogen
//     e viceversa modificare questa classe nel eprogen implica un allineamento della stessa classe nel portale
//
/*********************/

public class Ep_msgmod_cont extends com.ateikon.standard.Ep_msgmod_cont {


    public Ep_msgmod_cont() {

        super();
    }

    /**
     * Carica il contenuto della mail.<br/> 
     * 
     * Il contenuto contenuto &egrave; privo di intestazione e pi&egrave; di pagina; questi devono essere applicati solo 
     * in fase di invio mail fichiamando il metodo of_getHTML
     * 
     * @param tkmsgmod
     * @param cdling
     * @return
     * @throws Exception 
     */

    public String of_getContenuto (long tkmsgmod, String cdling ) throws Exception {

    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        String ls_ = "";

        tot_rec = 0;

        l_query  = "";
        l_query += " select contenuto               \n";
        l_query += "   from pgmr.ep_msgmod_cont     \n";
        l_query += "  where tkmsgmod = "+tkmsgmod+" \n";
        l_query += "    and cdling = '"+cdling+"'   \n";
        l_query += "  order by nrposi               \n";

        pstm = m_connection.prepareStatement(l_query);

        ind = 1;

        rs = pstm.executeQuery();

        while (rs!=null && rs.next() && rs.getObject(1)!=null){

            tot_rec += 1;
            ls_ += rs.getString(1);

        }
        pstm.close();


        return ls_;

    }


    public int of_setContenuto (long tkmsgmod, String cdling, String contenuto ) throws Exception {

    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        int len_rec = 900;

        // cancello tutto il contenuto

        tot_rec = 0;

        l_query  = "";
        l_query += " delete from pgmr.ep_msgmod_cont where tkmsgmod = "+tkmsgmod+" and cdling = '"+cdling+"' \n";

        pstm = m_connection.prepareStatement(l_query);

        tot_rec = pstm.executeUpdate();

        pstm.close();


        int    li_     = 0;
        int    nrposi  = 0;
        String ls_cont = "";

        li_ = contenuto.length();

        while (li_ > 0){

            ls_cont  = "";
            nrposi  += 10;

            if (li_ > len_rec){

                ls_cont   = contenuto.substring(0, len_rec);
                contenuto = contenuto.substring(len_rec);
            }else {

                ls_cont   = contenuto;
                contenuto = "";
            }

            li_ = contenuto.length();


            l_query  = "";
            l_query += " insert into pgmr.ep_msgmod_cont ( \n";
            l_query += "        tkmsgmod                   \n";
            l_query += "      , cdling                     \n";
            l_query += "      , nrposi                     \n";
            l_query += "      , contenuto                  \n";
            l_query += "      , cdazie                     \n";
            l_query += "      , cddipa                     \n";
            l_query += "      , profil                     \n";
            l_query += "      , dtinse                     \n";
            l_query += "      , dtulag                     \n";
            l_query += "      )values (                    \n";
            l_query += "        ?,?,?,?,?                  \n";
            l_query += "      , ?,?,?,?                    \n";
            l_query += "      )                            \n";

            pstm = m_connection.prepareStatement(l_query);

            ind = 1;
            pstm.setLong     (ind, tkmsgmod );    ind += 1;
            pstm.setString   (ind, cdling   );    ind += 1;
            pstm.setLong     (ind, nrposi   );    ind += 1;
            pstm.setString   (ind, ls_cont  );    ind += 1;
            pstm.setString   (ind, cdazie   );    ind += 1;
            pstm.setString   (ind, cddipa   );    ind += 1;
            pstm.setString   (ind, profil   );    ind += 1;
            pstm.setTimestamp(ind, oggi     );    ind += 1;
            pstm.setTimestamp(ind, oggi     );    ind += 1;

            tot_rec = pstm.executeUpdate();
            pstm.close();

            if (tot_rec != 1){
                throw new Exception("Err. INS pgmr.ep_msgmod_cont");
            }


        }



        return 1;

    }



    /**
     * 
     * @param as_testo
     * @return
     * @throws Exception 
     */

    public String of_getHTML ( String as_testo, String base_url, String ep_portal_url ) throws Exception {
        
    	int               ind = 0;
        int               tot_rec = 0;
        PreparedStatement pstm = null;
        ResultSet         rs   = null;

        String messaggio = "";

        messaggio += "<html>\n";
        messaggio += "<head>\n";
        messaggio += "<title>Vetreria Vistosi</title>\n";
        messaggio += "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n";
        messaggio += "<base href=\""+ base_url +"\" />\n";
        messaggio += "<style type=\"text/css\">\n";
        messaggio += "<!--\n";
        messaggio += of_getStyle();
        messaggio += "-->\n";
        messaggio += "</style>\n";
        messaggio += "</head>\n";
        messaggio += "\n";
        messaggio += "<body>\n";
        messaggio += of_getConteunto_BODY(as_testo, ep_portal_url);
        messaggio += "</body>\n";
        messaggio += "</html>";



        return messaggio ;

    }


    public String of_stripHTML ( String as_testo ) throws Exception {
       
        if (!as_testo.equals("")){
            int li_pos_body = as_testo.toLowerCase().indexOf(LS_TAG_BODY.toLowerCase());

            if (li_pos_body > 0){
                li_pos_body += (LS_TAG_BODY.length());

                as_testo = as_testo.substring(li_pos_body);
            }

            
            li_pos_body = as_testo.toLowerCase().indexOf(LS_TAG_BODY_FINE.toLowerCase());

            if (li_pos_body >= 0){
                as_testo = as_testo.substring(0, li_pos_body);
            }

        }

        
        return as_testo;
        
    }

    public String of_getStyle(){

      String ls_style = "";

      ls_style  = " body{                                                                                         \n";
      ls_style += "     margin:0;                                                                                 \n";
      ls_style += "     padding:0;                                                                                \n";
      ls_style += "     font-family: Arial, Helvetica, sans-serif;                                                \n";
      ls_style += " 	font-size:12px;                                                                              \n";
      ls_style += " }                                                                                             \n";
      ls_style += " #wrapper{                                                                                     \n";
      ls_style += "     width:709px;                                                                              \n";
      ls_style += "     margin:10px;                                                                              \n";
      ls_style += " }                                                                                             \n";
      ls_style += " .body_div{                                                                                    \n";
      ls_style += "     margin-top:40px;                                                                          \n";
      ls_style += " 	text-align:justify;                                                                          \n";
      ls_style += " }                                                                                             \n";
      ls_style += " table{                                                                                        \n";
      ls_style += "     border-collapse: collapse;                                                                \n";
      ls_style += "     border-spacing:0;                                                                         \n";
      ls_style += " }                                                                                             \n";
      ls_style += " h4{                                                                                           \n";
      ls_style += " font-size:14px;}                                                                              \n";
      ls_style += "                                                                                               \n";
      ls_style += " h3{                                                                                           \n";
      ls_style += " font-size:18px;                                                                               \n";
      ls_style += " padding-bottom:10px;}                                                                         \n";
      ls_style += "                                                                                               \n";
      ls_style += " .body_div p{font-size:12px; line-height:14px;}                                                \n";
      ls_style += "                                                                                               \n";
      ls_style += " .css_foot {font-size: 11px; font-weight: normal; color: #000; text-align:justify;}            \n";
      ls_style += " .privacy {font-size: 9px; font-weight: normal; color: #b8b8b8; text-align:justify;}           \n";
      ls_style += " .foot{                                                                                        \n";
      ls_style += "     width:709px;                                                                              \n";
      ls_style += "     text-align: justify;                                                                      \n";
      ls_style += "     margin-top:20px;                                                                          \n";
      ls_style += " }                                                                                             \n";
      ls_style += " .head{                                                                                        \n";
      ls_style += "     width:709px;                                                                              \n";
      ls_style += "     height:50px;                                                                              \n";
      ls_style += "     margin-bottom:40px;                                                                       \n";
      ls_style += " }                                                                                             \n";
      ls_style += " .alert {                                                                                      \n";
      ls_style += "     font-size:16px;                                                                           \n";
      ls_style += "     color:#FF0000;                                                                            \n";
      ls_style += " }                                                                                             \n";
      ls_style += " .parametri, .report{font-size: 12px;}                                                         \n";
      ls_style += " .parametri table{                                                                             \n";
      ls_style += "                                                                                               \n";
      ls_style += " }                                                                                             \n";
      ls_style += " .report{                                                                                      \n";
      ls_style += "     border-top: 1px solid #ccc;                                                               \n";
      ls_style += "     border-bottom: 1px solid #ccc;                                                            \n";
      ls_style += "     border-left: 1px solid #ccc;                                                              \n";
      ls_style += " }                                                                                             \n";
      ls_style += " .report th, .report td{padding:5px; border-right:1px solid #ccc;}                             \n";
      ls_style += " .parametri tr, .report tr{                                                                    \n";
      ls_style += "     height:25px;                                                                              \n";
      ls_style += " }                                                                                             \n";
      ls_style += " .parametri td, .parametri th, .report td, .report th{                                         \n";
      ls_style += "     border-bottom: 1px solid #ccc;                                                            \n";
      ls_style += "     padding-top:10px;                                                                         \n";
      ls_style += "     padding-bottom:10px;                                                                      \n";
      ls_style += " }                                                                                             \n";
      ls_style += " .dettaglio th{                                                                                \n";
      ls_style += "     font-size:10px;                                                                           \n";
      ls_style += "     color:#999;                                                                               \n";
      ls_style += "     /*text-align:left;*/                                                                      \n";
      ls_style += "     border-bottom: 1px solid #999;                                                            \n";
      ls_style += "     padding-bottom:10px;                                                                      \n";
      ls_style += " 	vertical-align:top;                                                                          \n";
      ls_style += " }                                                                                             \n";
      ls_style += " .dettaglio td {                                                                               \n";
      ls_style += " color:#000000;                                                                                \n";
      ls_style += " font-size:10px;                                                                               \n";
      ls_style += " padding-bottom:10px;                                                                          \n";
      ls_style += " padding-top:10px;                                                                             \n";
      ls_style += " vertical-align:top;                                                                           \n";
      ls_style += " }                                                                                             \n";
      ls_style += " tr.dettaglio2 {                                                                               \n";
      ls_style += "     font-size:10px;                                                                           \n";
      ls_style += "     color:#000;                                                                               \n";
      ls_style += "     /*text-align:left;*/                                                                      \n";
      ls_style += "     border-bottom: 1px solid #000;                                                            \n";
      ls_style += " 	vertical-align:bottom;                                                                       \n";
      ls_style += " }                                                                                             \n";
      ls_style += " .nota-cli {                                                                                   \n";
      ls_style += " border:1px solid #CCCCCC;                                                                     \n";
      ls_style += " }                                                                                             \n";
      ls_style += " .nota-cli p {                                                                                 \n";
      ls_style += " font-size:10px;                                                                               \n";
      ls_style += " line-height:14px;                                                                             \n";
      ls_style += " padding-left:10px;                                                                            \n";
      ls_style += " padding-right:10px;                                                                           \n";
      ls_style += " }                                                                                             \n";
      ls_style += "                                                                                               \n";
      ls_style += " .al{                                                                                          \n";
      ls_style += "     text-align:left;                                                                          \n";
      ls_style += " }                                                                                             \n";
      ls_style += " .ar{                                                                                          \n";
      ls_style += "     text-align:right;                                                                         \n";
      ls_style += " }                                                                                             \n";
      ls_style += " .report1 {font-size: 12px;}                                                                   \n";
      ls_style += " a:link, a:visited{                                                                            \n";
      ls_style += " color:#000;                                                                                   \n";
      ls_style += " }                                                                                             \n";
      ls_style += " a:hover, a:active{                                                                            \n";
      ls_style += " color:#000;                                                                                   \n";
      ls_style += " }                                                                                             \n";
      ls_style += " .parametri2{font-size: 12px;}                                                                 \n";
      ls_style += " .parametri2 table{                                                                            \n";
      ls_style += "                                                                                               \n";
      ls_style += " }                                                                                             \n";
      ls_style += " .parametri2 td, .parametri2 th{                                                               \n";
      ls_style += "     font-weight:normal;                                                                       \n";
      ls_style += " }                                                                                             \n";
      ls_style += " .report_mpron{                                                                                \n";
      ls_style += "     font-size:10px;                                                                           \n";
      ls_style += "     border-top: 1px solid #ccc;                                                               \n";
      ls_style += "     border-bottom: 1px solid #ccc;                                                            \n";
      ls_style += "     border-left: 1px solid #ccc;                                                              \n";
      ls_style += " }                                                                                             \n";
      ls_style += " .report_mpron tr{                                                                             \n";
      ls_style += "     height:25px;                                                                              \n";
      ls_style += " }                                                                                             \n";
      ls_style += " .report_mpron td, .report_mpron th{                                                           \n";
      ls_style += "     border-right: 1px solid #ccc;                                                             \n";
      ls_style += "     border-bottom: 1px solid #ccc;                                                            \n";
      ls_style += "     padding:10px 5px 10px 5px;                                                                \n";
      ls_style += "     padding-bottom:10px;                                                                      \n";
      ls_style += " }                                                                                             \n";
      ls_style += " .report_premi{                                                                                \n";
      ls_style += "     font-size:14px;                                                                           \n";
      ls_style += "     border: 1px solid #ccc;                                                                   \n";
      ls_style += " }                                                                                             \n";
      ls_style += " .report_premi td, .report_premi th{                                                           \n";
      ls_style += "     padding:2px 20px 2px 20px;                                                                  \n";
      ls_style += " }                                                                                             \n";
      ls_style += " .notify-bubble{                                                                               \n";
      ls_style += "     font-size: 9px;                                                                          \n";
      ls_style += "     background-color: red;                                                                    \n";
      ls_style += "     color: #fff;                                                                              \n";
      ls_style += "     position: absolute;                                                                       \n";
      ls_style += "     top: -21px;                                                                                \n";
      ls_style += "     left: 12px;                                                                               \n";
      ls_style += "     border-radius: 9px;                                                                       \n";
      ls_style += "     width: 15px;                                                                              \n";
      ls_style += "     min-width: 15px;                                                                          \n";
      ls_style += "     height: 15px;                                                                             \n";
      ls_style += "     line-height: 15px;                                                                        \n";
      ls_style += "     text-align: center;                                                                       \n";
      ls_style += "     padding: 0px;                                                                             \n";
      ls_style += " }                                                                                             \n";

      return ls_style;
    }

    public String of_getConteunto_BODY(String as_testo, String ep_portal_url){

        String ls_content = "";

        ls_content += "  <div id=\"wrapper\">                                                                                                                                                                               \n";
        ls_content += "    <div id=\"head\">     \n";
        ls_content += "      <a href=\""+ ep_portal_url +"\"><img src=\""+ ep_portal_url +"themes/vistosi/images/head-notifiche-vistosi.jpg\" width=\"709\" height=\"71\" border=\"0\"></a>     \n";
        ls_content += "    </div>     \n";
        ls_content += "<!-- HEADER -->\n";
        ls_content += LS_TAG_BODY;

        ls_content += as_testo;

        ls_content += LS_TAG_BODY_FINE;
        ls_content += "<!-- FOOTER -->\n";
        ls_content += "  </div>     \n";

        return ls_content;
    }

    public static String LS_TAG_BODY = "<!-- CORPO MESSAGGIO -->\n";
    public static String LS_TAG_BODY_FINE = "<!-- /CORPO MESSAGGIO -->\n";


}

