<%-- 
    Document   : 403
    Created on : 23-set-2009, 11.34.23
    Author     : emiliano
--%>
<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<c:set var="disableMiniCart" value="true"/>
<jsp:directive.include file="/WEB-INF/jsp/header.jsp"/>

<div id="access-denied" class="gen-cnt">
    <security:authorize ifAnyGranted="ROLE_ADMIN, ROLE_ISPE, ROLE_CAPO, ROLE_AGEN, ROLE_GEST">
        <div id="cliente-cnt">
        <h2><spring:message code="cliente.accesso" text="Cliente di accesso"/></h2>
        
        <h3><spring:message code="cliente.select" text="Selezionare un cliente"/>:</h3>

        <div id="selclie-cnt">
            loading...
        </div>
        <script type="text/javascript">
            window.addEvent('domready', function(){
               var waiter = new Spinner('selclie-cnt');

               var uri = '<c:url value="/setclie"/>';
               $('selclie-cnt').set('load', {onSuccess: function(){
                //console.log('loaded form');
                waiter.hide();
               }});

               waiter.show();
               $('selclie-cnt').load(uri);
            });
        </script>


    </security:authorize>
    <security:authorize ifNotGranted="ROLE_ADMIN, ROLE_ISPE, ROLE_CAPO, ROLE_AGEN, ROLE_GEST">
        <spring:message code="no.authorize" text="Non si dispone dei diritti necessari per accedere alla pagina richiesta"/>
    </security:authorize>
</div>

<jsp:directive.include file="/WEB-INF/jsp/footer.jsp"/>