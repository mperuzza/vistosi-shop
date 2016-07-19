<%@ page import="java.sql.ResultSet"  
         import="java.sql.SQLException"
         import="java.sql.Timestamp"
         import="java.util.Vector"
         import="com.ateikon.common.Atk_sql"
         import="com.ateikon.common.Cat_costanti"

         import="com.ateikon.util.Atk_ctrl"
         import="com.ateikon.util.StringParsToHTML"
         import="com.ateikon.util.StringUtils"

         import="com.ateikon.structure.Str_html"

         import="com.ateikon.structure.Rec_cat_costanti"
%><%

  
    %><%@include file="include/variabili_comuni.jsp" %><%



try {


    %><%@include file="include/dbconnect.jsp" %><%
    %><%@include file="include/parametri_ep.jsp" %><%
    %><%@include file="include/sicurezza_ep.jsp" %><%


    Cat_costanti lcat_costanti = new Cat_costanti();

    atk_sql.setProfilo((Atk_sql) lcat_costanti );




    long   tkcost    = 0;
    String costname  = "";
    String costlabel = "";
    String costvalue = "";

    String [] a_costname  = null;
    String [] a_costlabel = null;


    String nextPage = "catAdmin_config.jsp";

    String req_cdazie = "01";
    String r_agente_web = "";






    if (request.getParameter("azione")!=null)   azione     = request.getParameter("azione");
    if (request.getParameter("nextPage")!=null) nextPage   = request.getParameter("nextPage");
    if (request.getParameter("r_agente_web")!=null) r_agente_web = request.getParameter("r_agente_web");
    if (request.getParameter("req_cdazie")!=null) req_cdazie = request.getParameter("req_cdazie");

    lcat_costanti.cdazie = req_cdazie;
    lcat_costanti.cddipa = "";
    lcat_costanti.profil = "";


    
    if (azione.equals("delete")) {

        long tkcost_del = 0;

        if (request.getParameter("tkcost_del")!=null) tkcost_del = Long.parseLong(request.getParameter("tkcost_del"));


        tot_rec = lcat_costanti.delete(tkcost_del);

        if (tot_rec>0){
            lcat_costanti.m_connection.commit();

            message += "Costante Eliminata!";
        }else {
            message += "Nessuna Costante Aliminata!";
        }
    }




    if (azione.equals("aggiorna")) {

        String [] arr_tkcost     = null;
        String [] arr_costname   = null;
        String [] arr_costlabel  = null;
        String [] arr_costvalue  = null;


        if (request.getParameterValues("tkcost"   )!=null)   arr_tkcost    = request.getParameterValues("tkcost"   );
        if (request.getParameterValues("costname" )!=null)   arr_costname  = request.getParameterValues("costname" );
        if (request.getParameterValues("costlabel")!=null)   arr_costlabel = request.getParameterValues("costlabel");
        if (request.getParameterValues("costvalue")!=null)   arr_costvalue = request.getParameterValues("costvalue");

        tot_rec = 0;

        for (int i=0; arr_tkcost!=null && i<arr_tkcost.length; i++){

            tkcost = Long.parseLong(arr_tkcost[i]);

            Rec_cat_costanti lstr = new Rec_cat_costanti();

            r_agente_web = r_agente_web.trim().toLowerCase();
            arr_costname[i] = arr_costname[i].trim().toLowerCase();

            if (arr_costvalue[i].equals("") && tkcost == 0) continue;


            lstr.tkcost    = tkcost;
            lstr.costname  = arr_costname[i];
            lstr.costlabel = arr_costlabel[i];
            lstr.costvalue = arr_costvalue[i];
            lstr.cdagen    = r_agente_web;

            // reimposto il profilo
            lcat_costanti.cdazie = req_cdazie;
            lcat_costanti.cddipa = "";
            lcat_costanti.profil = "";


            int li_ = lcat_costanti.execute(lstr);

            if (li_ != 1){
                
                throw new Exception("Errore aggiornamento Cat_costanti!");
            }else {
                tot_rec += 1;
            }

        }


        if (tot_rec>0){
            lcat_costanti.m_connection.commit();

            message = "Costanti Aggiornate!";
        }else {
            message = "Nessuna Costante Aggiornata!";
        }


    }


    if (!nextPage.equals("")){
        response.sendRedirect(nextPage+"?req_cdazie="+req_cdazie+"&r_agente_web="+r_agente_web+"&message="+message);
        return;
    }



%><%@include file="include/dbclose.jsp" %>


