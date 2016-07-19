<%-- 
    Document   : promo
    Created on : 29-set-2009, 11.34.23
    Author     : emiliano
--%>
<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<c:set var="plainMenu" value="true"/>
<jsp:directive.include file="/WEB-INF/jsp/header.jsp"/>

<div id="promo-cnt">
<h2><spring:message code="promo.title" text="Offerte e promozioni"/></h2>
    <c:choose>
        <c:when test="${!empty promo}">
            <c:forEach items="${promo}" var="p">
                <div class="promo"><a href="<c:url value='/static/promo/${p.pdf}'/>" target="_blank">${p.dspromo}</a></div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <h3><spring:message code="no.promo" text="Nessuna promozione in corso"/>.</h3>
        </c:otherwise>
    </c:choose>

</div>

<jsp:directive.include file="/WEB-INF/jsp/footer.jsp"/>