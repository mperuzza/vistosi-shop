<script type="text/javascript" language="JavaScript" src="<c:url value='/static/js/atk_common.js'/>" ></script>
<%--script type="text/javascript" language="JavaScript" src="<c:url value='/static/js/aqdd.js'/>" ></script--%>
<script type="text/javascript" language="JavaScript" src="<c:url value='/static/js/atk_paginazione.js'/>" ></script>
<%

    if (seStesso.indexOf("epRep") == 0
    ||  seStesso.indexOf("catProfilo") == 0
    ||  seStesso.indexOf("catIndex") == 0
        ){
        %><script language="JavaScript" src="<c:url value='/static/js/atk_catalogo_std.js'/>" ></script><%
    }

    if (seStesso.indexOf("_par.jsp") > 0){
        %><link rel="stylesheet" href="<c:url value='/style_tabs.css'/>" type="text/css"><%
        %><script language="JavaScript" src="<c:url value='/static/js/iframe_resize.js'/>" ></script><%
        %>
<style>
.lcss_iframe{
    height:0px; 
    width:100%; 
    padding:0px;
    margin:0px;
    border: 0px;
}
</style>
        <%


    }   // FINE if (seStesso.indexOf("_par.jsp") > 0){


%>

