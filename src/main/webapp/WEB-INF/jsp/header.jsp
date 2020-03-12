<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <jsp:directive.include file="/WEB-INF/jspf/meta.jspf"/>

  <link href='https://fonts.googleapis.com/css?family=Roboto:700,900' rel='stylesheet' type='text/css'/>
  <style type="text/css" media="screen">   
      @import url("<c:url value='/static/styles/vistosi-shop_v17.css'/>");
    <c:if test="${rc.locale=='en'}">
        @import url("<c:url value='/static/styles/vistosi-shop_en.css'/>");
    </c:if>
    <c:if test="${rc.locale=='de'}">
        @import url("<c:url value='/static/styles/vistosi-shop_de.css'/>");
    </c:if>

  </style>     
<style type="text/css">
    .alertbar {
        background: none repeat scroll 0 0 #3399CC;
        border-bottom: 3px solid #DEDEDE;
        color: #FFFFFF;
        display: none;
        height: 55px;
        left: 0;
        right: 0;
        top: 0;
        z-index: 999998;
    }

    .alertbar .text {
        display: block;
        font-family: Arial;
        font-weight: 900;
        font-size: 11px;
        line-height: 15px;
        margin: 0;
        padding: 10px;
        text-align: center;
    }
    .alertbar a{
        color: #fff;
    }

    .alertbar .downarrow {
        margin: 0;
        padding: 0;
        position: absolute;
        right: 4%;
        text-align: center;
        top: 18px;
        width: 50px;
    }

</style>
  
  <script type="text/javascript" src="<c:url value='/static/js/mootools-1.2.5-core-yc.js' />"> </script>
  <script type="text/javascript" src="<c:url value='/static/js/mootools-1.2.5.1-more.js' />"> </script>
  <%--script type="text/javascript" src="<c:url value='/static/js/mootools-1.2.3-core-yc.js' />"> </script>
  <script type="text/javascript" src="<c:url value='/static/js/mootools-1.2.3.1-more.js' />"> </script--%>
  <%--script type="text/javascript" src="<c:url value='/static/js/clientcide-trunk-2.1.0.compressed.js' />"> </script--%>
  <script type="text/javascript" src="<c:url value='/static/js/clientcide.2.2.0-nocompat.js?v=casset' />"> </script>
  <script type="text/javascript" src="<c:url value='/static/js/array-sortby.js' />"> </script>
  <script type="text/javascript" src="<c:url value='/static/js/fixBrokenImages.js' />"> </script>
  <script type="text/javascript" src="<c:url value='/static/js/message.js' />"></script>
  <script type="text/javascript" src="<c:url value='/static/js/moopopup.js' />"></script>
  <script type="text/javascript" src="<c:url value='/static/js/cufon-yui.js' />"></script>
  <script type="text/javascript" src="<c:url value='/static/js/Assets/fonts/helveticaneueltstd-bd-cufon.js' />"></script>

  <title>Vistosi SHOP</title>
  <%--security:authorize ifNotGranted="ROLE_CLIE"><script type="text/javascript">console.log("non cliente")</script></security:authorize--%>
</head>
<c:choose>
    <c:when test="${rc.locale.language=='it'}"><c:set var="alert">Desideriamo informarLa che il catalogo on-line non sarà disponibile<br/>
dalle ore 00:00 (GMT+1.00) del giorno 02/01/2017 alle ore 23:59 (GMT+1.00) del giorno 04/01/2017 per aggiornamento dei servizi.</c:set></c:when>
<c:otherwise><c:set var="alert">We inform you that the on-line catalog will not be available<br/>
from 00:00 (GMT+1.00) on the day 02/01/2017 to 23:59 (GMT+1.00) of the day 04/01/2017 to upgrade services.</c:set></c:otherwise>
</c:choose>
<body class="vistosi-shop">
    <jsp:useBean id="now" class="java.util.Date"/>
    <fmt:parseDate var="startDate" type="date" pattern="dd/MM/yyyy HH:mm" value="27/12/2016 00:00"/>
    <fmt:parseDate var="endDate" type="date" pattern="dd/MM/yyyy HH:mm" value="04/01/2017 12:00"/>
    <c:if test="${now > startDate && now < endDate}">
    <div style="display: none;" class="alertbar position-top" id="alertbar"><span class="alertbar-cta"><p class="text">${alert}</p><p class="downarrow"><a id="alerttrigger" href="javascript:void(0)"><img alt="Arrow Up" class="alerttrigger arrow" src="<c:url value='/static/images/arrow-up.png'/>"></a></p></span></div>    
    </c:if>
  <div id="wrap">
  
  	<div id="menu">
    	<%@ include file="/WEB-INF/jsp/menu.jsp" %>
    </div>
    <div id="main">