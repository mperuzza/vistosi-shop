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
         import="com.ateikon.common.Unitalocali"
         import="com.ateikon.common.Province"
         import="com.ateikon.common.Nazioni"

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

    Web_ord_test web_ord_test = new Web_ord_test();
    Unitalocali  unitalocali  = new Unitalocali();
    Province     province     = new Province();
    Nazioni      nazioni      = new Nazioni();

    F_ordven    f_ordven = new F_ordven();


    atk_sql.setProfilo((Atk_sql) web_ord_test);
    atk_sql.setProfilo((Atk_sql) unitalocali );
    atk_sql.setProfilo((Atk_sql) province    );
    atk_sql.setProfilo((Atk_sql) nazioni     );
    atk_sql.setProfilo((Atk_sql) f_ordven    );


    if (request.getParameter("azione")!=null) azione = request.getParameter("azione");


    // --- Verifico su quale Ordine sto lavorando
    //     

    long  tkordi = 0;
    
    Object s_tkordi = session.getAttribute("tkordi");
    if(s_tkordi!=null) {
        tkordi = (Long)s_tkordi;
    }else{            
        String cdstato = "PR";
        if(AuthorityUtils.userHasAuthority("ROLE_SUBUTENTE")){
            cdstato = "PN";
        }  
        tkordi = f_ordven.getCarrello(s_tkutente, s_tkclie, cdstato);
    }    


    System.out.println("carrello dest merce: " + s_tkutente);
    System.out.println("carrello dest merce: " + s_tkclie);
    System.out.println("carrello dest merce: " + tkordi);
    if (tkordi <= 0){
        response.sendRedirect("catalogo_index.jsp?azione=carrello");
        return;
    }



    Str_ordven_tot  lstr_tot  = f_ordven.getTototali(tkordi);
    Str_ordven_cond lstr_cond = f_ordven.getCondven(s_tkclie);



    Str_dest_merce lstr_dest = f_ordven.getDestinazione_merce(tkordi);

    


    long        numord  = 0;
    int         anno    = 0;
    String      cdordi  = "";
    String      cdpromo = "";
    String      annotazione_cli = "";   // visibile anche dal cliente
    String      annotazione_int = "";   // ad uso interno
    
    Timestamp   dtcons  = null;
    String      ls_dtcons = "";



    rs = web_ord_test.getKey(tkordi);

    if (rs!=null && rs.next()){

        if (rs.getObject("numord" )!=null)  numord  = rs.getLong  ("numord");
        if (rs.getObject("anno"   )!=null)  anno    = rs.getInt   ("anno");
        if (rs.getObject("cdordi" )!=null)  cdordi  = rs.getString("cdordi");
        if (rs.getObject("dtcons" )!=null)  dtcons  = rs.getTimestamp("dtcons");
    }

    annotazione_cli = f_ordven.getAnnotazione(tkordi, f_ordven.TIPONOTA_CLIENTE);
    annotazione_int = f_ordven.getAnnotazione(tkordi, f_ordven.TIPONOTA_INTERNA);

    ls_dtcons = atk_ctrl.getDate(dtcons);





    String r_cduldm      = lstr_dest.cduldm;

    if (request.getParameter("annotazione_cli")!=null) annotazione_cli = request.getParameter("annotazione_cli");
    if (request.getParameter("annotazione_int")!=null) annotazione_int = request.getParameter("annotazione_int");
    if (request.getParameter("cduldm"     )!=null) r_cduldm    = request.getParameter("cduldm");


    if (!r_cduldm.equals( lstr_dest.cduldm )){

        tot_rec = unitalocali.setCdunil(r_cduldm, lstr_dest);

        if (tot_rec != 1){
            message += "Unità locale Errata!";
        }
    }



    //destinazione merce libera


    if (request.getParameter("ragcog_de")!=null)   lstr_dest.ragcog_de = request.getParameter("ragcog_de");
    if (request.getParameter("indiri_de")!=null)   lstr_dest.indiri_de = request.getParameter("indiri_de");
    if (request.getParameter("cap_de"   )!=null)   lstr_dest.cap_de    = request.getParameter("cap_de");
    if (request.getParameter("comune_de")!=null)   lstr_dest.comune_de = request.getParameter("comune_de");
    if (request.getParameter("cdprov_de")!=null)   lstr_dest.cdprov_de = request.getParameter("cdprov_de");
    if (request.getParameter("cdstat_de")!=null)   lstr_dest.cdstat_de = request.getParameter("cdstat_de");
    if (request.getParameter("dm")!=null){           
        String new_cduldm = request.getParameter("dm");
        if(r_cduldm.trim()!= "") lstr_dest.cduldm = new_cduldm;
    }
    /*if (request.getParameter("dtcons")!=null)      ls_dtcons    = request.getParameter("dtcons");


    ls_dtcons = atk_ctrl.of_ggmmsa(ls_dtcons, "Data Da", "DA");

    if (atk_ctrl.rc.equals("1")){
        ls_dtcons = "";
        dtcons = null;

        if (azione.indexOf("aggiorna")==0){
            message += "Valorizzare la data Consegna!";
        }
    }else if (atk_ctrl.rc.equals("0")){
        
        dtcons = atk_ctrl.ggmmsa;
    }else {
        message += atk_ctrl.message;
        dtcons = null;
    }*/




    if (azione.indexOf("aggiorna")==0 && message.equals("")){

        tot_rec = f_ordven.setDest_merce(tkordi, lstr_dest);
        
        if (tot_rec == 1){
            
            f_ordven.m_connection.commit();
            
        }else {
            f_ordven.m_connection.rollback();
            message += "Errore aggiornamento Destinazione Merce!";
        }


        tot_rec = f_ordven.setAnnotazione(tkordi, f_ordven.TIPONOTA_INTERNA, annotazione_int);
        tot_rec = f_ordven.setAnnotazione(tkordi, f_ordven.TIPONOTA_CLIENTE, annotazione_cli);
        //tot_rec = f_ordven.setDtcons(tkordi, dtcons);

        if (tot_rec == 1){
            
            f_ordven.m_connection.commit();

            if (azione.equals("aggiorna_goback")){
                
                //response.sendRedirect("catalogo_index.jsp?azione=carrello");
                response.sendRedirect("fullCart");
                return;

            }else if (azione.equals("aggiorna_goon")){

                if (page_pagamento.equals("N")){

                    String ls_url = "catalogo_ordine_fine.jsp";

                    response.sendRedirect(ls_url);
                    return;
                }else {
                    response.sendRedirect("catalogo_pagamento.jsp");
                    return;
                }

            }else {
            
                message += "Dati Aggiornati!";
            }
            
        }else {
            f_ordven.m_connection.rollback();
            message += "Errore aggiornamento Nota!";
        }


    }





%><%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

  <style type="text/css" media="screen">
    @import url("<c:url value='/static/styles/vistosi-shop_v16.css'/>");
  </style>
  <link rel='stylesheet' type='text/css' href='<c:url value='/static/styles/datepicker.css'/>' />
  <script type="text/javascript" src="<c:url value='/static/js/mootools-1.2.5-core-yc.js' />"> </script>
  <script type="text/javascript" src="<c:url value='/static/js/mootools-1.2.5.1-more.js' />"> </script>  
  <%--script type="text/javascript" src="<c:url value='/static/js/mootools-1.2.3-core-yc.js' />"> </script>
  <script type="text/javascript" src="<c:url value='/static/js/mootools-1.2.3.1-more.js' />"> </script--%>
  <%--script type="text/javascript" src="<c:url value='/static/js/clientcide-trunk-2.1.0.compressed.js' />"></script--%>
  <script type="text/javascript" src="<c:url value='/static/js/clientcide.2.2.0-nocompat.js' />"> </script>
  <script type="text/javascript" src="<c:url value='/static/js/datepicker.js' />"></script>
  <script type="text/javascript" src="<c:url value='/static/js/cufon-yui.js' />"></script>
  <script type="text/javascript" src="<c:url value='/static/js/Assets/fonts/helveticaneueltstd-bd-cufon.js' />"></script>  

  <title>Vistosi SHOP</title>

<%@include file="/include/head_page.jsp" %>
<%--@include file="/include/calendar.jsp" --%>
<script language="JavaScript" src="<c:url value="/static/js/atk_catalogo_std.js"/>" ></script>
<script language="JavaScript" src="<c:url value="/static/js/atk_common.js"/>" ></script>
<script language="javaScript">

    function atk_onLoad(){

        <%@include file="/js/atk_onLoad.jsp" %>

        //atk_setUp_calendar('dtcons', 'form_ordi');
    }


/*
    function atk_onLoad(){


        elDivAttesa = parent.document.getElementById("divAttesa");
        if (elDivAttesa != null){
            elDivAttesa.style.visibility ='hidden';
        }



        el_mod = document.getElementById('div1');
        el = parent.document.getElementById('td_carrello');

        el.innerHTML = el_mod.innerHTML;

        <% if (!message.equals("")){ %>
          <%  message = StringUtils.replace(message, "<br/>", "\n"); %>
          alert('<%= StringParsToHTML.escapeJS(message) %>')
          <%  message = ""; %>
        <% } %>


        // setTimeout("atk_setUp_calendar('dtcons', 'form_ordi', 'parent')", 50); 

    	
    }
*/

    function atk_onSubmit(){
        atk_wait();
        return true;
    }

    function atk_conf_uniloc(azione){

        atk_wait();

        document.body.scrollTop = 0;
        document.body.scrollLeft = 0;

        //console.log('conf ' + azione);
        document.form_ordi.azione.value = azione;
        document.form_ordi.action = 'catalogo_dest_merce.jsp';
        document.form_ordi.target = '_self';
        document.form_ordi.submit();

    }


<%@include file="/js/atk_key.jsp" %>

    function atk_close(){
        self.location = 'index.html';
    }

    function atk_f1( ){ }
    function atk_f2( ){ }
    function atk_f3( ){ }
    function atk_paste( ){ }


window.addEvent('load', function() {

    /*new DatePicker('input[name=dtcons]', { positionOffset: { x: 0, y: 5 },
                format: 'd/m/Y',
                inputOutputFormat: 'd/m/Y',
                  days: [<spring:message code="days" text="'Domenica', 'Lunedi', 'Martedi', 'Mercoledi', 'Giovedi', 'Venerdi', 'Sabato'"/>],
		months: [<spring:message code="months" text="'Gennaio', 'Febbraio', 'Marzo', 'Aprile', 'Maggio', 'Giugno', 'Luglio', 'Agosto', 'Settembre', 'Ottobre', 'Novembre', 'Dicembre'"/>]

    });*/
});

</script>

</head>
<body onLoad="atk_onLoad();">

  <div id="wrap">

  	<div id="menu">
        <c:set var="plainMenu" value="true"/>
    	<%@ include file="/WEB-INF/jsp/menu.jsp" %>
    </div>
    <div id="main">
<div id="cart-cnt">
<%
 int step = 1;

    String ls_bgcolor1 = "#E3E3E3";
    String ls_bgcolor2 = "#E3E3E3";


    ls_bgcolor1 = "#666";

%><div id="step-cart"><%
    %><table >
  <%
        %><tr><%
            %><td width="100" class="due"><%
                %><%= step++ %>. <br />
<spring:message code="cart" text="Carrello"/><%
            %></td><%
            %><td width="100" class="current uno"><%
                %><%= step++ %>. <br />
<spring:message code="cart.product.destination" text="Destinazione merce"/><%
            %></td><%
            
            if (page_pagamento.equals("S")){
  
                %><td width="100"  class="tre"><%
                    %><%= step++ %>. <br />
<spring:message code="cart.payment" text="Pagamento"/><%
                %></td><%
            }

            %><td width="100" class="quattro"><%
                %><%= step++ %>. <br />
<spring:message code="cart.confirm.order" text="Conferma ordine"/><%
            %></td><%
        %></tr><%
    %></table><%
	%></div><%
	
%><h2><spring:message code="cart.product.destination" text="Destinazione merce"/></h2><%
%><div id="scheda-cart"><%




    %><table width="100%"><%
        %><tr><%
            %><td onDblClick="alert('tkordi = <%= tkordi %>')"><%
                  %><spring:message code="ordine" text="Ordine"/> n. <%= cdordi %> <%
            %></td><%
        %></tr><%
    %></table><%

    %><form name="form_ordi" action="<%= seStesso %>" method="post" target="if_1"><%

        %><input type="hidden" name="azione" value=""/><%

        %><%--table--%><%
            %><%--tr--%><%
                %><%--td width="100"><spring:message code="cart.dm.dtcons" text="Data consegna"/>: </td--%>
<%
                %><%--td--%><%--

                    if (ls_dtcons.equals("")){
                        Timestamp ldt_def = atk_ctrl.relativeDate(atk_sql.of_getOggi(), +1);
                        ls_dtcons = atk_ctrl.getDate(ldt_def );

                    }


                    /*str_html = new Str_html();

                    str_html.name       = "dtcons";
                    str_html.value      = ls_dtcons;
                    str_html.id         = "el~"+idx_f;  idx_f += 1;
                    str_html.css        = "in in_dta";

                    out.println(html.get(str_html));*/

                --%><%--input type="hidden" name="dtcons" value="<%=ls_dtcons%>"/--%><%--/td--%><%
            %><%--/tr--%><%
        %><%--/table --%><%


                    %><table width="100%">
  <%
                        %><tr><%
                            %><th colspan="2" class="tit-scheda-cart"><spring:message code="cart.dati.fatt" text="Dati fatturazione"/></th>
  <%
                        %></tr><%

                        %><tr><%
                            %>
  <th width="100"><spring:message code="cart.sl.ragsoc" text="Ragione sociale"/>:</th>
  <%
                            %><td><%= html.text( lstr_dest.ragcog_sl ) %></td><%
                        %></tr><%
                        %><tr><%
                            %><th width="100"><spring:message code="cart.sl.indiri" text="Indirizzo"/>:</th>
  <%
                            %><td><%= html.text( lstr_dest.indiri_sl ) %></td><%
                        %></tr><%
                        %><tr><%
                            %>
  <th width="100"><spring:message code="cart.sl.cap" text="CAP"/>:</th>
  <%
                            %><td><%= html.text( lstr_dest.cap_sl ) %></td><%
                        %></tr><%
                        %><tr><%
                            %><th width="100"><spring:message code="cart.sl.comune" text="Comune"/>:</th>
  <%
                            %><td><%= html.text( lstr_dest.comune_sl ) %></td><%
                        %></tr><%
                        %><tr><%
                            %><th width="100"><spring:message code="cart.sl.provincia" text="Provincia"/>:</th>
  <%
                            %><td><%= html.text( lstr_dest.dsprov_sl ) %></td><%
                        %></tr><%
                        %><tr><%
                            %><th width="100"><spring:message code="cart.sl.nazione" text="Nazione"/>:</th>
  <%
                            %><td><%= html.text( lstr_dest.dsnazi_sl ) %></td><%
                        %></tr><%

                        if (!lstr_dest.cduldm.equals("")){
                            
                                %><tr><%
                                    %><th colspan="2" class="tit-scheda-cart"><spring:message code="cart.dati.dm" text="Dati destinazione merce"/></th>
  <%
                                %></tr><%
                                %><tr><%
                                    %><th width="100"><spring:message code="cart.dm.dsunil" text="Denominazione"/>:</th>
  <%
                                    %><td><%= html.text( lstr_dest.dsunil_dm ) %></td><%
                                %></tr><%
                                %><tr><%
                                    %><th width="100"><spring:message code="cart.dm.indiri" text="Indirizzo"/>:</th>
  <%
                                    %><td><%= html.text( lstr_dest.indiri_dm ) %></td><%
                                %></tr><%
                                %><tr><%
                                    %>
  <th width="100"><spring:message code="cart.dm.cap" text="CAP"/>:</th>
  <%
                                    %><td><%= html.text( lstr_dest.cap_dm ) %></td><%
                                %></tr><%
                                %><tr><%
                                    %><th width="100"><spring:message code="cart.dm.comune" text="Comune"/>:</th>
  <%
                                    %><td><%= html.text( lstr_dest.comune_dm ) %></td><%
                                %></tr><%
                    
                                %><tr><%
                                    %><th width="100"><spring:message code="cart.dm.provincia" text="Provincia"/>:</th>
  <%
                                    %><td><%= html.text( lstr_dest.cdprov_dm ) %></td><%
                                %></tr><%
                                %><tr><%
                                    %><th width="100"><spring:message code="cart.dm.nazione" text="Nazione"/>:</th>
  <%
                                    %><td><%= html.text( lstr_dest.dsnazi_dm ) %></td><%
                                %></tr><%
                        }


                        /*
                        %><tr><%
                            %><td colspan="2"><br/><br/><input type="checkbox" name="fg_de" id="fg_de" onClick="atk_dest_merce(this);" value="S"/> 
  <spring:message code="cart.dm.different" text="Se diversa dagli indirizzi sopra indicati"/>:</td>
    <%
                        %></tr><%
                        */
                        %><tr><%
                            %><td colspan="2"><br/><br/>
  <spring:message code="cart.dm.different" text="Se diversa dagli indirizzi sopra indicati"/> (<spring:message code="cart.sel.newde" text="puoi selezionarla dalla lista di quelle già presenti in anagrafica o inserirne una nuova"/>):</td>
    <%
                        %></tr><%
                        %><tr><%
                            %><td colspan="2">
                    <select name="dm" id="sel_dm">
                        <option value=""><spring:message code="cart.new.de" text="Nuova"/></option>
                        <c:forEach items="${unitalocali}" var="t" varStatus="s">
                            <option value="${t.unitalocali.cdunil}" <%--c:if test="${t.enteuniloc.fspref=='S'}">selected</c:if--%> gdata="{ds:'${fn:replace(t.unitalocali.dsunil, "'", "\\'")}', indiri: '${fn:replace(t.unitalocali.indiri, "'", "\\'")}', cap:'${t.unitalocali.cap}', comune: '${t.unitalocali.comune}', cdprov:'${t.unitalocali.cdprov}', cdnazi: '${t.unitalocali.cdnazi}'}">${t.unitalocali.dsunil} - ${t.unitalocali.indiri} (${t.unitalocali.cdprov})</option>
                        </c:forEach>
                    </select>
                        <script type="text/javascript">
                            window.addEvent('domready', function(e){

                               if($('sel_dm')){
                                   $('sel_dm').addEvent('change', function(e){
                                       if(e) e.stop();
                                       if(this.get('value').length > 0){
                                           var sel = this.getSelected();
                                           var data = JSON.decode(sel[0].get('gdata'));
                                           if(data.ds!='') $('ragcog_de').set({'value': data.ds, 'readonly': true});
                                           else $('ragcog_de').set({'value': '', 'readonly': false})
                                           if(data.indiri!='') $('indiri_de').set({'value': data.indiri, 'readonly': true});
                                           else $('indiri_de').set({'value': '', 'readonly': false});
                                           if(data.cap!='') $('cap_de').set({'value': data.cap, 'readonly': true});
                                           else $('cap_de').set({'value': '', 'readonly': false});
                                           if(data.comune!='') $('comune_de').set({'value': data.comune, 'readonly': true});
                                           else $('comune_de').set({'value': '', 'readonly': false});
                                           if(data.cdprov!='') $('cdprov_de').set({'value': data.cdprov, 'readonly': true});
                                           else $('cdprov_de').set({'value': '', 'readonly': false});
                                           if(data.cdnazi!='') $('cdstat_de').set({'value': data.cdnazi, 'readonly': true});
                                           else $('cdstat_de').set({'value': '', 'readonly': false});
                                       }else{
                                           $('ragcog_de').set({'value': '', 'readonly': false});
                                           $('indiri_de').set({'value': '', 'readonly': false});
                                           $('cap_de').set({'value': '', 'readonly': false});
                                           $('comune_de').set({'value': '', 'readonly': false});
                                           $('cdprov_de').set({'value': '', 'readonly': false});
                                           $('cdstat_de').set({'value': '', 'readonly': false});
                                       }
                                   });

                                   $('sel_dm').fireEvent('change');
                               }

                            });
                        </script>
  </td>
    <%
                        %></tr><%
                        %><tr><%
                            %>
  <th width="100"><spring:message code="cart.dm.ragsoc" text="Ragione sociale"/>:</th>
  <%
                            %><td><%
                            
                                str_html = new Str_html();
                                
                                str_html.name        = "ragcog_de";
                                str_html.value       = lstr_dest.ragcog_de;
                                str_html.id          = "ragcog_de";  idx_f += 1;
                                str_html.size        = "50";
                                str_html.maxlength   = "80";
                                str_html.css         = "in";
                                
                                out.println(html.getText_box(str_html));
                            
                            %></td><%
                        %></tr><%
                        %><tr><%
                            %><th width="100"><spring:message code="cart.dm.indiri" text="Indirizzo"/>:</th>
  <%
                            %><td><%
                            
                                str_html = new Str_html();
                                
                                str_html.name        = "indiri_de";
                                str_html.value       = lstr_dest.indiri_de;
                                str_html.id          = "indiri_de";  idx_f += 1;
                                str_html.size        = "80";
                                str_html.maxlength   = "250";
                                str_html.css         = "in";
                                
                                out.println(html.getText_box(str_html));
                            
                            %></td><%
                        %></tr><%
                        %><tr><%
                            %>
  <th width="100"><spring:message code="cart.dm.cap" text="CAP"/>:</th>
  <%
                            %><td><%
            
                                str_html = new Str_html();
                                
                                str_html.name        = "cap_de";
                                str_html.value       = lstr_dest.cap_de;
                                str_html.id          = "cap_de";  idx_f += 1;
                                str_html.size        = "10";
                                str_html.maxlength   = "10";
                                str_html.css         = "in in_cen";
                                
                                out.println(html.getText_box(str_html));
                            
                            %></td><%
                        %></tr><%
                        %><tr><%
                            %><th width="100"><spring:message code="cart.dm.comune" text="Comune"/>:</th>
  <%
                            %><td><%
            
                                str_html = new Str_html();
                                
                                str_html.name        = "comune_de";
                                str_html.value       = lstr_dest.comune_de;
                                str_html.id          = "comune_de";  idx_f += 1;
                                str_html.size        = "50";
                                str_html.maxlength   = "80";
                                str_html.css         = "in";
                                
                                out.println(html.getText_box(str_html));
                            
                            %></td><%
                        %></tr><%
            
                        %><tr><%
                            %><th width="100"><spring:message code="cart.dm.provincia" text="Provincia"/>:</th>
  <%
                            %><td><%
            
                                rs = province.getProvince("dsprov");

                                String ls_codice = "";
                                String ls_descrizione = "";
                                //rs = province.getDropdown();
                                /*out.print( html.getDropdown(rs, "cdprov_de", lstr_dest.cdprov_de, "style=\"width:300px;\" class=\"sl\"") );*/
                                %>
                                <select name="cdprov_de" id="cdprov_de">
                                    <option value=""></option>
                                    <%
                                    while (rs!=null && rs.next()){

                                        if (rs.getObject("cdprov_m")!=null) ls_codice      = rs.getString("cdprov_m").trim();
                                        if (rs.getObject("dsprov")!=null)   ls_descrizione = rs.getString("dsprov").trim();
                                        %><option value="<%=ls_codice%>"><%=ls_descrizione%></option><%
                                    }                                    
                                    %>
                                </select>
                                <%
                            %></td><%
                        %></tr><%
            
            
                        %><tr><%
                            %><th width="100"><spring:message code="cart.dm.nazione" text="Nazione"/>:</th>
  <%
                            %><td><%
                                
                                if (lstr_dest.cdstat_de.equals("")) lstr_dest.cdstat_de = cdstat_it;


                                rs = nazioni.getNazioni("dsnazi");
                                //rs = nazioni.getDropdown();

                                //out.print( html.getDropdown(rs, "cdstat_de", lstr_dest.cdstat_de, "style=\"width:300px;\" class=\"sl\"") );
                                ls_codice = "";
                                ls_descrizione = "";
                                String ls_codice_m = "";
                                String ls_cdncee = "";
                                //rs = province.getDropdown();
                                /*out.print( html.getDropdown(rs, "cdprov_de", lstr_dest.cdprov_de, "style=\"width:300px;\" class=\"sl\"") );*/
                                %>
                                <select name="cdstat_de" id="cdstat_de">
                                    <option value=""></option>
                                    <%
                                    while (rs!=null && rs.next()){

                                        if (rs.getObject("cdnazi")!=null)   ls_codice        = rs.getString("cdnazi").trim();
                                        if (rs.getObject("cdnazi_m")!=null) ls_codice_m      = rs.getString("cdnazi_m").trim();
                                        if (rs.getObject("cdncee")!=null)   ls_cdncee      = rs.getString("cdncee").trim();
                                        if (rs.getObject("dsnazi")!=null)   ls_descrizione = rs.getString("dsnazi").trim();
                                        %><option value="<%=ls_codice%>"><%=ls_descrizione%></option><%
                                    }
                                    %>
                                </select>
                                <%
                            %></td><%
                        %></tr><%
                       
                    %></table><%


                    %><table width="100%"><%
   %><tr><%
     %>
  <th class="tit-scheda-cart"><spring:message code="note" text="Note"/>:</th>
  <%
    %> </tr><%
   %>
  <tr>
    <td><spring:message code="nota" text="Nota"/> <spring:message code="ordine" text="ordine"/>:</td>
  </tr>
  <tr><%
                           %><td> <%
                             %>&nbsp;<%
                           %>
      <%
                               %>
      <textarea name="annotazione_cli" cols="80" rows="3" class="in"><%= annotazione_cli %></textarea>
      <%
                           %></td>
  <%
                           %><%
                         %></tr><%
                   %></table>
  <%
                

                    %><table width="100%"><%
                         %><tr><%
  %><security:authorize ifAnyGranted="ROLE_ADMIN, ROLE_ISPE, ROLE_CAPO, ROLE_AGEN, ROLE_GEST"><td><spring:message code="nota.internal" text="Nota ad uso interno"/>:</td>
  <%
     %> </tr><%
   %> <tr><%
                           %><td> <%
                             %>&nbsp;<%
                           %>
      <%
                               %>
      <textarea name="annotazione_int" cols="80" rows="3" class="in"><%= annotazione_int %></textarea>
      <%
                           %></td>
  <%
                           %><%
                         %></tr></security:authorize><%
                   %></table>
<%
%><p>&nbsp;</p><%
        %><div class="button"><a href="javaScript:atk_conf_uniloc('aggiorna_goback');"><span><em><spring:message code="cart.back" text="Torna al carrello"/></em></span></a></div><%

        
        %>        
<div class="button"><a href="javaScript:atk_conf_uniloc('aggiorna_goon');">
        <security:authorize ifAnyGranted="ROLE_CLIE" ifNotGranted="ROLE_SUBUTENTE"><span><em><spring:message code="button.send" text="Invia"/> <spring:message code="ordine" text="ordine"/></em></span></a></security:authorize>
        <security:authorize ifAnyGranted="ROLE_SUBUTENTE"><span><em><spring:message code="button.send.reqchord" text="Invia richiesta chiusura ordine"/></em></span></a></security:authorize>
</div>
<%


    %></form><%

%></div><%


%></div><%

%><%@include file="/include/dbclose.jsp" %>
<jsp:directive.include file="/WEB-INF/jsp/footer.jsp"/>

