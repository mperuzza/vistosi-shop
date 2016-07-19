<%@ page import="java.sql.ResultSet"  
         import="java.sql.SQLException"
         import="java.sql.Timestamp"
         import="java.util.Vector"
         import="com.ateikon.common.Atk_sql"
         import="com.ateikon.common.Costanti_comm"

         import="com.ateikon.util.Atk_ctrl"
         import="com.ateikon.util.StringParsToHTML"
         import="com.ateikon.util.StringUtils"

         import="com.ateikon.structure.Str_html"

         import="com.ateikon.common.Web_ord_test"
         import="com.ateikon.function.F_ordven"

         import="com.ateikon.structure.Str_ordven_tot"
         import="com.ateikon.structure.Str_ordven_cond"
         import="com.ateikon.structure.Str_dest_merce"
         
         import="org.springframework.security.util.AuthorityUtils"

%><jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/><%

    %><%@include file="/include/variabili_comuni.jsp" %><%

try {


    %><%@include file="/include/dbconnect.jsp" %><%
    %><%@include file="/include/parametri_ep.jsp" %><%
    %><%@include file="/include/sicurezza_ep.jsp" %><%

    Web_ord_test    web_ord_test     = new Web_ord_test();
    F_ordven    f_ordven     = new F_ordven();


    atk_sql.setProfilo((Atk_sql) web_ord_test);
    atk_sql.setProfilo((Atk_sql) f_ordven    );


    if (request.getParameter("azione")!=null) azione = request.getParameter("azione");


    // --- Verifico su quale Ordine sto lavorando
    //     

    long  tkordi = 0;
    String cdstato = "";
    Object s_tkordi = session.getAttribute("tkordi");
    if(s_tkordi!=null) {
        tkordi = (Long)s_tkordi;
    }else{                
        cdstato = "PR";
        if(AuthorityUtils.userHasAuthority("ROLE_SUBUTENTE")){
            cdstato = "PN";
        }  
        tkordi = f_ordven.getCarrello(s_tkutente, s_tkclie, cdstato);
    }
    
    if (tkordi <= 0){
        response.sendRedirect("catalogo_carrello.jsp");
        return;
    }




    String  cdordi    = "";
    //String  cdstato = ""; 



    rs = web_ord_test.getKey(tkordi);

    if (rs!=null && rs.next()){

        if (rs.getObject("cdordi" )!=null)  cdordi  = rs.getString("cdordi");
        if (rs.getObject("cdstato" )!=null)  cdstato = rs.getString("cdstato");
    }
    


    if (cdstato.equals("PR")){

        tot_rec = f_ordven.confermaOrdine(tkordi);
        
        if (tot_rec == 1){
            f_ordven.m_connection.commit();
            session.removeAttribute("tkordi");
            try {
                
                tot_rec = f_ordven.sendMainMail(tkordi);
    
                if (tot_rec <= 0){
                    message += "Errore Invio e-Mail conf. ordine!\n";
                }
                
                tot_rec = f_ordven.sendMailOfferte(tkordi);
    
                if (tot_rec <= 0){
                    message += "Errore Invio e-Mail conf. ordine - offerte!\n";
                }
                
                
            }catch(Exception ex){
                
                System.out.println("");
                System.out.println("========================================");
                System.out.println("    "+cliente+": Catalogo_VISTOSI");
                System.out.println("========================================");
                ex.printStackTrace();
                System.out.println("========================================");
                message += "Errore Invio e-Mail conf. ordine!\n";
                
            }

        }else {
            message += "Errore Conferma Ordine!\n Contattare l'amministratore di sistema.";
        }

    }else
    if (cdstato.equals("PN")){

        tot_rec = f_ordven.confermaOrdineSubutente(tkordi);
        
        if (tot_rec == 1){
            
            f_ordven.m_connection.commit();
            session.removeAttribute("tkordi");
            //TODO mail di notifica
            
            try {
                tot_rec = f_ordven.sendMainMail(tkordi);
    
                if (tot_rec <= 0){
                    message += "Errore Invio e-Mail conf. ordine!\n";
                }
                
                tot_rec = f_ordven.sendMailOfferte(tkordi);
    
                if (tot_rec <= 0){
                    message += "Errore Invio e-Mail conf. ordine - offerte!\n";
                }
                                
            }catch(Exception ex){
                
                System.out.println("");
                System.out.println("========================================");
                System.out.println("    "+cliente+": Catalogo_VISTOSI");
                System.out.println("========================================");
                ex.printStackTrace();
                System.out.println("========================================");
                message += "Errore Invio e-Mail conf. ordine!\n";
                
            }

        }else {
            message += "Errore Conferma Ordine!\n Contattare l'amministratore di sistema.";
        }

    }        




%><%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

  <style type="text/css" media="screen">
    @import url("<c:url value='/static/styles/vistosi-shop_v14.css'/>");
  </style>

  <%--script type="text/javascript" src="<c:url value='/static/js/mootools-1.2.3-core-yc.js' />"> </script>
  <script type="text/javascript" src="<c:url value='/static/js/mootools-1.2.3.1-more.js' />"> </script--%>
  <script type="text/javascript" src="<c:url value='/static/js/mootools-1.2.5-core-yc.js' />"> </script>
  <script type="text/javascript" src="<c:url value='/static/js/mootools-1.2.5.1-more.js' />"> </script>   
  <%--script type="text/javascript" src="<c:url value='/static/js/clientcide-trunk-2.1.0.compressed.js' />"> </script--%>
  <script type="text/javascript" src="<c:url value='/static/js/clientcide.2.2.0-nocompat.js' />"> </script>
  <script type="text/javascript" src="<c:url value='/static/js/cufon-yui.js' />"></script>
  <script type="text/javascript" src="<c:url value='/static/js/Assets/fonts/helveticaneueltstd-bd-cufon.js' />"></script>    

  <title>Vistosi SHOP</title>

<%@include file="/include/head_page.jsp" %>
<script language="JavaScript" src="<c:url value="/static/js/atk_catalogo_std.js"/>" ></script>
<script language="javaScript">

    function atk_onLoad(){

        <%@include file="/js/atk_onLoad.jsp" %>
        
        if (parent.document){
            el_mod = document.getElementById('div1');
            el = parent.document.getElementById('td_carrello');
            if (el){
                el.innerHTML = el_mod.innerHTML;
            }
        }

    }


</script>

</head>
<body class="vistosi-shop" onLoad="atk_onLoad();">

  <div id="wrap">

  	<div id="menu">
        <c:set var="plainMenu" value="true"/>
    	<%@ include file="/WEB-INF/jsp/menu.jsp" %>
    </div>
    <div id="main">
<div id="cart-cnt">
<%
 int step = 1;
%><div id="step-cart"><%
    %><table >
  <%
        %><tr><%
            %><td width="100" class="cinque"><%
                %><%= step++ %>. <br>
<spring:message code="cart" text="Carrello"/><%
            %></td><%
            %><td width="100" class="due"><%
                %><%= step++ %>. <br>
<spring:message code="cart.product.destination" text="Destinazione merce"/><%
            %></td><%
            
            if (page_pagamento.equals("S")){
              
                %><td width="100" class="due"><%
                    %><%= step++ %>. <br>
<spring:message code="cart.payment" text="Pagamento"/><%
                %></td><%
            }
            
            %><td width="100" class="current tre" ><%
                %>&nbsp;&nbsp;<%= step++ %>. <br>
<spring:message code="cart.confirm.order" text="Conferma ordine"/><%
            %></td><%

        %></tr><%
    %></table><%
%></div><%
%><div id="scheda-cart"><%


    rs = web_ord_test.getKey(tkordi);
    
    long tkutente = 0;
    long tksubutente = 0;

    if (rs!=null && rs.next()){

        if (rs.getObject("cdordi" )!=null)  cdordi  = rs.getString("cdordi");
        if (rs.getObject("cdstato" )!=null)  cdstato = rs.getString("cdstato");
        if (rs.getObject("tkutente" )!=null)  tkutente = rs.getLong("tkutente");
        if (rs.getObject("tksubutente" )!=null)  tksubutente = rs.getLong("tksubutente");
    }
    
    
    long tkordioff = 0;
    String cdordioff = "";
    
    tkordioff = web_ord_test.gettkOff(tkordi);
    
    if(tkordioff > 0){

        rs = web_ord_test.getKey(tkordioff);
        
        if (rs!=null && rs.next()){

            if (rs.getObject("cdordi" )!=null)  cdordioff  = rs.getString("cdordi");
        }
    }
	
%><h2><spring:message code="cart.confirm.order" text="Conferma ordine"/></h2><%

 %><table ><%
 %><% if(AuthorityUtils.userHasAuthority("ROLE_SUBUTENTE") && "PR".equals(cdstato) && tkutente!=tksubutente ) {%><tr><%
 
    Str_dest_merce lstr_dest = f_ordven.getDestinazione_merce(tkordi);
    
                  %><td><spring:message code="cart.end.subutente" text="Ordine inviato alla sede centrale:"/><br/>
                  <%=lstr_dest.ragcog_sl%><br/>
                  <%=lstr_dest.indiri_dm %><br/>
                  <%=lstr_dest.cap_dm + " " + lstr_dest.comune_dm + "("+lstr_dest.cdprov_dm+")"%><br/>
                  <%=lstr_dest.dsnazi_dm %><br/><%
            %></td><%
        %></tr><%}%><%
        
        if(tkordi != tkordioff){
            %><tr><%
                %><td><%
                      %><spring:message code="ordine" text="Ordine"/> n. <%= cdordi %><%
                %></td><%
            %></tr><%
        }
    
        if(!cdordioff.equals("")){
            %><tr><%
                %><td><%
                      %><spring:message code="ordine" text="Ordine"/> n. <%= cdordioff %>&nbsp;(<spring:message code="cart.row.offer" text="Articoli in offerta"/>)<%
                %></td><%
            %></tr><%
        }
    %></table><%

    %><form name="form_ordi" action="<%= seStesso %>" method="post" target="if_1"><%


        %><input type="hidden" name="azione" value=""/><%
 %><table width="100%"><%
  %> <tr><%
    %> <th><spring:message code="cart.congratulations" text="Complimenti"/>!</th><%
   %></tr><%
  %> <tr><%
  
  if(tkordi != tkordioff && tkordioff > 0){
       %><td><%pageContext.setAttribute("cdordi", cdordi );pageContext.setAttribute("cdordioff", cdordioff );%>
           <spring:message code="cart.end" arguments="${cdordi} - ${cdordioff}"/>
         </td><%
  }else{
       %><td><%pageContext.setAttribute("cdordi", cdordi );pageContext.setAttribute("cdordioff", cdordioff );%>
           <spring:message code="cart.end" arguments="${cdordi}"/>
         </td><%
  }
     
     
  %> </tr><%
 %></table><%

    %></form><%

    %></div><%

%></div><%

%><%@include file="/include/dbclose.jsp" %>
<jsp:directive.include file="/WEB-INF/jsp/footer.jsp"/>