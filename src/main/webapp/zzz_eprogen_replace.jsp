<%@ page import="java.sql.ResultSet"
         import="java.sql.SQLException"
         import="java.sql.PreparedStatement"
         import="java.sql.Timestamp"
         import="java.util.Vector"
         import="java.io.File"
         import="com.ateikon.common.Atk_sql"
         import="com.ateikon.common.Costanti_comm"

         import="com.ateikon.util.Atk_ctrl"
         import="com.ateikon.util.StringParsToHTML"
         import="com.ateikon.util.StringUtils"

         import="com.ateikon.structure.Str_html"


%><%

    %><%@include file="include/variabili_comuni.jsp" %><%

try {


    %><%@include file="include/dbconnect.jsp" %><%
    %><%@include file="include/parametri_ep.jsp" %><%
    %><%@include file="include/sicurezza_ep.jsp" %><%

    com.ateikon.function.F_eprogen_replace   f_eprogen_replace =  new com.ateikon.function.F_eprogen_replace();
    
    atk_sql.setProfilo((Atk_sql)  f_eprogen_replace  );

    java.util.HashMap hm = new java.util.HashMap();
    
    
    String cdling = "";
    
    if (request.getParameter("cdling")!= null) cdling = request.getParameter("cdling");

    //f_eprogen_replace.of_setPar_Province(hm, "OG", s_cdling);
    //f_eprogen_replace.of_setPar_Archagen(hm, "02C", "", s_cdling);
    //f_eprogen_replace.of_setPar_Archclie(hm, "05109", s_cdling);
    //f_eprogen_replace.of_setPar_RUBR_RR_CP(hm, 6717, s_cdling);
    //f_eprogen_replace.of_setPar_RUBR_PROSP(hm, 6883, s_cdling);
    //f_eprogen_replace.of_setPar_Archagen(hm, "#", "", s_cdling);
    //f_eprogen_replace.of_setPar_Archclie(hm, "61906", s_cdling);
    //f_eprogen_replace.of_setPar_Ep_utente(hm, 24, s_cdling);
    //f_eprogen_replace.of_setPar_Msg_comgen(hm, 2447, s_cdling);
    //f_eprogen_replace.of_setPar_COMG_RECL(hm, 978, s_cdling);
    //f_eprogen_replace.of_setPar_Vist_log_notif(hm, 1418, s_cdling);
    //f_eprogen_replace.of_setPar_OV_CONF(hm, 12000105, s_cdling);
    //f_eprogen_replace.of_setPar_COMG_RECL(hm, 2439, s_cdling);
    //f_eprogen_replace.of_setPar_Vist_mpron(hm, 571, s_cdling);
    //f_eprogen_replace.of_setPar_Archclie(hm, "62035", s_cdling);
    //f_eprogen_replace.of_setPar_OV_AVVSPED(hm, 50083704, s_cdling);
    //f_eprogen_replace.of_setPar_Vist_mpron(hm, 669, s_cdling);
    //f_eprogen_replace.of_setPar_EPRO_NOACC(hm, 49, s_cdling);
    //f_eprogen_replace.of_setPar_RUBR_PROSP(hm, 9628, cdling);
    //f_eprogen_replace.of_setPar_Ep_utente(hm, 135, s_cdling);
    //f_eprogen_replace.of_setPar_Archresp(hm, "0000000505", s_cdling);
    //f_eprogen_replace.of_setPar_Age_ispettori(hm, "E01", s_cdling);
    //f_eprogen_replace.of_setPar_Archagen(hm, "129", "", s_cdling);
    //f_eprogen_replace.of_setPar_Archclie(hm, "05214", s_cdling);
    //f_eprogen_replace.of_setPar_RUBR_RR_CP(hm, 10629, cdling);
    //f_eprogen_replace.of_setPar_Ep_utente(hm, 135, s_cdling);
    //f_eprogen_replace.of_setPar_OV_RICEZ(hm, 2210, s_cdling);
    //f_eprogen_replace.of_setPar_OV_RICEZ(hm, 2405, s_cdling);
    //f_eprogen_replace.of_setPar_OV_RICEZ(hm, 3047, s_cdling);
    //f_eprogen_replace.of_setPar_OV_RICEZ(hm, 3398, s_cdling);
    //f_eprogen_replace.of_setPar_Vist_mpron(hm, 1161, s_cdling);
    
    //f_eprogen_replace.of_setPar_Ep_utente(hm, 109, s_cdling);
    //f_eprogen_replace.of_setPar_Ep_utente(hm, 18, s_cdling);
    //f_eprogen_replace.of_setPar_Ep_utente(hm, 117, s_cdling);
    //f_eprogen_replace.of_setPar_Ep_utente(hm, 12, s_cdling);
    //f_eprogen_replace.of_setPar_Ep_utente(hm, 660, s_cdling);
    //f_eprogen_replace.of_setPar_EPRO_CRED(hm, 660, s_cdling);
    //f_eprogen_replace.of_setPar_EPRO_CRED(hm, 49, s_cdling);
    //f_eprogen_replace.of_setPar_EPRO_CRED(hm, 818, s_cdling);
    //f_eprogen_replace.of_setPar_EPRO_CRED(hm, 117, s_cdling);
    //f_eprogen_replace.of_setPar_EPRO_CRED(hm, 18, s_cdling);
    //f_eprogen_replace.of_setPar_EPRO_CRED(hm, 12, s_cdling);
    //f_eprogen_replace.of_setPar_EPRO_CRED(hm, 109, s_cdling);
    f_eprogen_replace.of_setPar_OV_RICEZ(hm, 7075, s_cdling);
    
    /*
    com.ateikon.structure.Str_key_vist_premi str_key_vist_premi = new com.ateikon.structure.Str_key_vist_premi();
    
    str_key_vist_premi.anno = 2013;
    str_key_vist_premi.tkclie = "11183";
    str_key_vist_premi.cdagen = "002";
    
    f_eprogen_replace.of_setPar_PREMI(hm, str_key_vist_premi, s_cdling);
    */
    /*
    com.ateikon.structure.Str_msgmod lstr_msgmod = new com.ateikon.structure.Str_msgmod();
    
    f_eprogen_replace.of_setPar_SCADENZE(hm, 267, s_cdling, lstr_msgmod);
    */
    //f_eprogen_replace.of_setPar_SITE_DOWNR(hm, 214659, s_cdling);
    //f_eprogen_replace.of_setPar_SITE_DOWNR(hm, 229556, s_cdling);
    //f_eprogen_replace.of_setPar_SITE_DOWNR(hm, 229639, s_cdling);
    //f_eprogen_replace.of_setPar_SITE_DOWNR(hm, 249081, s_cdling);
    //f_eprogen_replace.of_setPar_SITE_DOWNR(hm, 249131, s_cdling);
    
    
    //f_eprogen_replace.of_setPar_OV_RICEZ(hm, 7075, s_cdling);

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
    </head>
    <body>

      <% 
      
      java.util.Set list = hm.keySet();
      java.util.Iterator iter = list.iterator();

      while (iter.hasNext()){
        String chiave = (String) iter.next();
        String valore = (String) hm.get(chiave);

      %>  
      <h2><%= chiave + " -- " + valore %></h2>
      <% 
      }
      %>

<%@include file="include/dbclose.jsp" %>
</body>
</html>
