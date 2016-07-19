<%-- 
    Document   : 403
    Created on : 23-set-2009, 11.34.23
    Author     : emiliano
--%>

<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<jsp:directive.include file="/WEB-INF/jsp/header.jsp"/>

<div id="access-denied" class="gen-cnt">
    <security:authorize ifAnyGranted="ROLE_ADMIN, ROLE_AGEN">
        agente o admin->può settare un cliente
    </security:authorize>
    <security:authorize ifNotGranted="ROLE_ADMIN, ROLE_AGEN">
        Non si dispone dei diritti necessari per accedere alla pagina richiesta
    </security:authorize>
</div>

<jsp:directive.include file="/WEB-INF/jsp/footer.jsp"/>
