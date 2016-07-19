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

         import="com.ateikon.function.F_msgmod"
         import="com.ateikon.function.F_eprogen_replace"
         import="com.ateikon.function.F_sender"

         import="com.ateikon.structure.Str_html"
         import="com.ateikon.structure.Str_msgmod"


%><%

    %><%@include file="include/variabili_comuni.jsp" %><%

try {


    %><%@include file="include/dbconnect.jsp" %><%
    %><%@include file="include/parametri_ep.jsp" %><%
    %><%@include file="include/sicurezza_ep.jsp" %><%

    com.ateikon.common.Ep_msgmod_cont   ep_msgmod_cont =  new com.ateikon.common.Ep_msgmod_cont();
    F_msgmod   f_msgmod =  new F_msgmod();
    F_eprogen_replace   f_eprogen_replace =  new F_eprogen_replace();
    F_sender   f_sender =  new F_sender();
    
    atk_sql.setProfilo((Atk_sql)  ep_msgmod_cont  );
    atk_sql.setProfilo((Atk_sql)  f_msgmod  );
    atk_sql.setProfilo((Atk_sql)  f_eprogen_replace  );
    atk_sql.setProfilo((Atk_sql)  f_sender  );

    com.voxbiblia.jresolver.Resolver resolver = f_sender.createMXResolver();
    
    String cdling = "";
    
    if (request.getParameter("cdling")!= null) cdling = request.getParameter("cdling");

    /*
    Str_msgmod lstr_msgmod = null;
    //Retrieve modello 
    lstr_msgmod = f_msgmod.of_retrieve("COMG_ARTS", "ADDATT", atk_dwlingua.getCdling_ep_lingua(s_cdling));
    //Valorizzo paramtro ${file.filename}
    lstr_msgmod.par__file_filename = "nomefile.txt";
    //Replace dei dati nel modello
    lstr_msgmod = (Str_msgmod) f_eprogen_replace.of_replace_msgmod("COMG_ARTS", new Long(2030), lstr_msgmod);
    */
    /*
    Str_msgmod lstr_msgmod = null;
    //Retrieve modello 
    lstr_msgmod = f_msgmod.of_retrieve("OV_MPRON", "GENERICA", atk_dwlingua.getCdling_ep_lingua("S"));
    //Replace dei dati nel modello
    lstr_msgmod = (Str_msgmod) f_eprogen_replace.of_replace_msgmod("OV_MPRON", new Long(535), lstr_msgmod);
    */
    /*
    Str_msgmod lstr_msgmod = null;
    //Retrieve modello 
    lstr_msgmod = f_msgmod.of_retrieve("EPRO_NOACC", "GENERICA", atk_dwlingua.getCdling_ep_lingua(cdling));
    //Replace dei dati nel modello
    lstr_msgmod = (Str_msgmod) f_eprogen_replace.of_replace_msgmod("EPRO_NOACC", new Long(206), lstr_msgmod);
    Str_msgmod lstr_msgmod = null;
    //Retrieve modello 
    lstr_msgmod = f_msgmod.of_retrieve("COMG_SUGG", "GENERICA", atk_dwlingua.getCdling_ep_lingua(cdling));
    //Replace dei dati nel modello
    lstr_msgmod = (Str_msgmod) f_eprogen_replace.of_replace_msgmod("COMG_SUGG", new Long(1791), lstr_msgmod);
    Str_msgmod lstr_msgmod = null;
    //Retrieve modello 
    lstr_msgmod = f_msgmod.of_retrieve("COMG_SUGG", "ADDATT", atk_dwlingua.getCdling_ep_lingua(cdling));
    //Replace dei dati nel modello
    lstr_msgmod = (Str_msgmod) f_eprogen_replace.of_replace_msgmod("COMG_SUGG", new Long(1791), lstr_msgmod);
    Str_msgmod lstr_msgmod = null;
    //Retrieve modello 
    lstr_msgmod = f_msgmod.of_retrieve("COMG_SUGG", "DELATT", atk_dwlingua.getCdling_ep_lingua(cdling));
    //Replace dei dati nel modello
    lstr_msgmod = (Str_msgmod) f_eprogen_replace.of_replace_msgmod("COMG_SUGG", new Long(1791), lstr_msgmod);
    Str_msgmod lstr_msgmod = null;
    //Retrieve modello 
    lstr_msgmod = f_msgmod.of_retrieve("COMG_SUGG", "RISPOSTA", atk_dwlingua.getCdling_ep_lingua(cdling));
    //Replace dei dati nel modello
    lstr_msgmod = (Str_msgmod) f_eprogen_replace.of_replace_msgmod("COMG_SUGG", new Long(1791), lstr_msgmod); 
    */
    
    /*
    Str_msgmod lstr_msgmod = null;
    //Retrieve modello 
    lstr_msgmod = f_msgmod.of_retrieve("SCADENZE", "CLIENTE", atk_dwlingua.getCdling_ep_lingua(cdling));
    //Replace dei dati nel modello
    lstr_msgmod = (Str_msgmod) f_eprogen_replace.of_replace_msgmod("SCADENZE", new Long(267), lstr_msgmod);
    */
    /*
    Str_msgmod lstr_msgmod = null;
    //Retrieve modello 
    lstr_msgmod = f_msgmod.of_retrieve("OV_MPRON", "GENERICA", atk_dwlingua.getCdling_ep_lingua(cdling));
    //Replace dei dati nel modello
    lstr_msgmod = (Str_msgmod) f_eprogen_replace.of_replace_msgmod("OV_MPRON", new Long(535), lstr_msgmod);
    */
    /*
    Str_msgmod lstr_msgmod = null;
    //Retrieve modello 
    lstr_msgmod = f_msgmod.of_retrieve("EPRO_NACCB", "GENERICA", atk_dwlingua.getCdling_ep_lingua(cdling));
    //Replace dei dati nel modello
    lstr_msgmod = (Str_msgmod) f_eprogen_replace.of_replace_msgmod("EPRO_NACCB", new Long(1425), lstr_msgmod);
    */
    /*
    Str_msgmod lstr_msgmod = null;
    //Retrieve modello 
    lstr_msgmod = f_msgmod.of_retrieve("OV_CONF", "GENERICA", atk_dwlingua.getCdling_ep_lingua(cdling));
    //Replace dei dati nel modello
    lstr_msgmod = (Str_msgmod) f_eprogen_replace.of_replace_msgmod("OV_CONF", new Long(12001409), lstr_msgmod);
    */
    /*
    Str_msgmod lstr_msgmod = null;
    //Retrieve modello 
    lstr_msgmod = f_msgmod.of_retrieve("COMG_RECL", "DELATT", atk_dwlingua.getCdling_ep_lingua(cdling));
    //Replace dei dati nel modello
    lstr_msgmod = (Str_msgmod) f_eprogen_replace.of_replace_msgmod("COMG_RECL", new Long(2439), lstr_msgmod); 
    */
    /*
    Str_msgmod lstr_msgmod = null;
    //Retrieve modello 
    lstr_msgmod = f_msgmod.of_retrieve("OV_AVVSPED", "GENERICA", atk_dwlingua.getCdling_ep_lingua(cdling));
    //Replace dei dati nel modello
    lstr_msgmod = (Str_msgmod) f_eprogen_replace.of_replace_msgmod("OV_AVVSPED", new Long(50083704), lstr_msgmod); 
    
    for (int i=0; lstr_msgmod.list_dest != null && i < lstr_msgmod.list_dest.size(); i++){
      
      com.ateikon.structure.Rec_ep_msgmod_dest  lstr_dest = (com.ateikon.structure.Rec_ep_msgmod_dest) lstr_msgmod.list_dest.get(i);
      
      System.out.println("###################################" );
      System.out.println("tipo  : "+ lstr_dest.tipodest );
      System.out.println("email : "+ lstr_dest.dest );
    }
    */
    /*
    Str_msgmod lstr_msgmod = null;
    //Retrieve modello 
    lstr_msgmod = f_msgmod.of_retrieve("COMG_ARTS", "ADDATT", atk_dwlingua.getCdling_ep_lingua(cdling));
    //Replace dei dati nel modello
    lstr_msgmod = (Str_msgmod) f_eprogen_replace.of_replace_msgmod("COMG_ARTS", new Long(3281), lstr_msgmod); 
    */
    /*
    Str_msgmod lstr_msgmod = null;
    //Retrieve modello 
    lstr_msgmod = f_msgmod.of_retrieve("RUBR_RM_CG", "GENERICA", atk_dwlingua.getCdling_ep_lingua(cdling));
    //Replace dei dati nel modello
    lstr_msgmod = (Str_msgmod) f_eprogen_replace.of_replace_msgmod("RUBR_RM_CG", new Long(822), lstr_msgmod); 
    */
    //Str_msgmod lstr_msgmod = null;
    //Retrieve modello 
    //lstr_msgmod = f_msgmod.of_retrieve("RUBR_PROSP", "CS_CLT1P", atk_dwlingua.getCdling_ep_lingua(cdling));
    //lstr_msgmod = f_msgmod.of_retrieve("RUBR_PROSP", "CS_CLT2P", atk_dwlingua.getCdling_ep_lingua(cdling));
    //lstr_msgmod = f_msgmod.of_retrieve("RUBR_PROSP", "VIS_CLT1P", atk_dwlingua.getCdling_ep_lingua(cdling));
    //lstr_msgmod = f_msgmod.of_retrieve("RUBR_PROSP", "EL13_CLT1P", atk_dwlingua.getCdling_ep_lingua(cdling));
    //lstr_msgmod = f_msgmod.of_retrieve("RUBR_PROSP", "EL13_CLT2P", atk_dwlingua.getCdling_ep_lingua(cdling));
    //lstr_msgmod = f_msgmod.of_retrieve("RUBR_PROSP", "EL13_CLT3P", atk_dwlingua.getCdling_ep_lingua(cdling));
    //lstr_msgmod = f_msgmod.of_retrieve("RUBR_PROSP", "EL13_CLT4P", atk_dwlingua.getCdling_ep_lingua(cdling));
    //lstr_msgmod = f_msgmod.of_retrieve("RUBR_PROSP", "CS_CLT1A", atk_dwlingua.getCdling_ep_lingua(cdling));
    //lstr_msgmod = f_msgmod.of_retrieve("RUBR_PROSP", "CS_CLT2A", atk_dwlingua.getCdling_ep_lingua(cdling));
    //lstr_msgmod = f_msgmod.of_retrieve("RUBR_PROSP", "CS_CLT3A", atk_dwlingua.getCdling_ep_lingua(cdling));
    //lstr_msgmod = f_msgmod.of_retrieve("RUBR_PROSP", "CS_CLT4A", atk_dwlingua.getCdling_ep_lingua(cdling));
    //Replace dei dati nel modello
    //lstr_msgmod = (Str_msgmod) f_eprogen_replace.of_replace_msgmod("RUBR_PROSP", new Long(2857), lstr_msgmod); 
    
    
    /*
 */  
    Str_msgmod lstr_msgmod = null;
    //Retrieve modello 
    //lstr_msgmod = f_msgmod.of_retrieve("OV_RICEZ", "GENERICA", atk_dwlingua.getCdling_ep_lingua(cdling));
    lstr_msgmod = f_msgmod.of_retrieve("OV_RICEZ", "GENERICA", "R");
    //Replace dei dati nel modello
    //lstr_msgmod = (Str_msgmod) f_eprogen_replace.of_replace_msgmod("OV_RICEZ", new Long(3398), lstr_msgmod); 
    //lstr_msgmod = (Str_msgmod) f_eprogen_replace.of_replace_msgmod("OV_RICEZ", new Long(2210), lstr_msgmod); 
    lstr_msgmod = (Str_msgmod) f_eprogen_replace.of_replace_msgmod("OV_RICEZ", new Long(7075), lstr_msgmod); 
/*      
    Str_msgmod lstr_msgmod = null;
    
    lstr_msgmod = f_msgmod.of_retrieve("OV_RICEZ", "GENERICA", atk_dwlingua.getCdling_ep_lingua(cdling));
    
    lstr_msgmod = (Str_msgmod) f_eprogen_replace.of_replace_msgmod("OV_RICEZ", new Long(7075), lstr_msgmod); 
    Str_msgmod lstr_msgmod = null;
    //Retrieve modello 
    lstr_msgmod = f_msgmod.of_retrieve("PREMI", "GENERICA", atk_dwlingua.getCdling_ep_lingua(cdling));
    //Replace dei dati nel modello
    com.ateikon.structure.Str_key_vist_premi str_key_vist_premi = new com.ateikon.structure.Str_key_vist_premi();
    
    str_key_vist_premi.anno = 2013;
    str_key_vist_premi.tkclie = "60855";
    str_key_vist_premi.cdagen = "089";
    
    lstr_msgmod = (Str_msgmod) f_eprogen_replace.of_replace_msgmod("PREMI", str_key_vist_premi, lstr_msgmod);     
    Str_msgmod lstr_msgmod = null;
    //Retrieve modello 
    lstr_msgmod = f_msgmod.of_retrieve("SITE_DOWNR", "GENERICA", atk_dwlingua.getCdling_ep_lingua(cdling));
    //Replace dei dati nel modello
    lstr_msgmod = (Str_msgmod) f_eprogen_replace.of_replace_msgmod("SITE_DOWNR", new Long(249077), lstr_msgmod);        
 * */   

    //tot_rec = f_msgmod.of_send_mail(lstr_msgmod, resolver);
    
    
%>
<%=  lstr_msgmod.of_getHTML() %>
<%--
for (int i=0; lstr_msgmod.list_dest != null && i < lstr_msgmod.list_dest.size(); i++){
      
      com.ateikon.structure.Rec_ep_msgmod_dest  lstr_dest = (com.ateikon.structure.Rec_ep_msgmod_dest) lstr_msgmod.list_dest.get(i);
  %>
      
      tipo  : <%=  lstr_dest.tipodest %>
      <br/>
      email : <%= lstr_dest.dest %>
      <br/>
      <br/>
   <%   
}
--%>
<%@include file="include/dbclose.jsp" %>
