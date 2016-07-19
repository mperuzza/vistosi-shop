<%-- 
    Document   : promo
    Created on : 29-set-2009, 11.34.23
    Author     : emiliano
--%>
<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<jsp:directive.include file="/WEB-INF/jsp/header.jsp"/>
<% pageContext.setAttribute("newLineChar", "\n"); %>
<div id="promo-cnt" class="condc">

<c:set var="condDescr"><c:choose><c:when test="${rc.locale.language!='it'}">descr_${rc.locale.language}</c:when><c:otherwise>descr</c:otherwise></c:choose></c:set>
<c:set var="condNota"><c:choose><c:when test="${rc.locale.language!='it'}">nota_${rc.locale.language}</c:when><c:otherwise>nota</c:otherwise></c:choose></c:set>
    <c:choose>
        <c:when test="${!empty condizioni}">
            <c:if test="${!empty condizioni['CONDF']}">
                <h2><spring:message code="condc.label.condf" text="Condizioni fornitura"/></h2>
                <dl>
                <c:forEach items="${condizioni['CONDF']}" var="cond">
                    <dt>${cond[condDescr]}</dt>
                    <dd>${fn:replace(cond[condNota], newLineChar, "<br/>")}</dd>
                </c:forEach>
                </dl>
            </c:if>
            <c:if test="${!empty condizioni['ACCF']}">
                <h2><spring:message code="condc.label.accf" text="Accordi di fornitura"/></h2>
                <dl>
                <c:forEach items="${condizioni['ACCF']}" var="cond">
                    <dt>${cond[condDescr]}</dt>
                    <dd>${fn:replace(cond[condNota], newLineChar, "<br/>")}</dd>
                </c:forEach>
                </dl>
            </c:if>
            <c:if test="${!empty condizioni['CONDL']}">
                <h2><spring:message code="condc.label.condl" text="Condizioni fisse da listino"/></h2>
                <dl>
                <c:forEach items="${condizioni['CONDL']}" var="cond">
                    <dt>${cond[condDescr]}</dt>
                    <dd>${fn:replace(cond[condNota], newLineChar, "<br/>")}</dd>
                </c:forEach>
                </dl>
            </c:if>
            <c:if test="${!empty condizioni['SERV']}">
                <h2><spring:message code="condc.label.serv" text="Servizi Vistosi"/></h2>
                <dl>
                <c:forEach items="${condizioni['SERV']}" var="cond">
                    <dt>${cond[condDescr]}</dt>
                    <dd>${fn:replace(cond[condNota], newLineChar, "<br/>")}</dd>
                </c:forEach>
                </dl>
            </c:if>
            <c:if test="${!empty condizioni.condcTable}">
                <div id="condctable-cnt">
                <h2><spring:message code="condc.tabriep" text="Tabella riepilogativa condizioni di vendita"/></h2>
                <c:set var="dsc"><c:choose><c:when test="${rc.locale.language!='it'}">dscampo_${rc.locale.language}</c:when><c:otherwise>dscampo</c:otherwise></c:choose></c:set>
                <table>
                    <thead><!-- label sconti -->
                        <tr>
                            <th><spring:message code="condc.sconti" text="Sconti"/></th>
                            <c:forEach var="col" items="${condizioni.condcTable}">
                                <th>${!empty col.value[dsc]? col.value[dsc]: col.value.dscampo}</th>
                            </c:forEach>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                            <c:if test="${!empty condizioni.condcTableValue.scrap1}">
                                <fmt:formatNumber value="${condizioni.condcTableValue.scrap1}" pattern="#,##0.00"/>%
                            </c:if>
                            <c:if test="${!empty condizioni.condcTableValue.scrap2}">
                                 + <fmt:formatNumber value="${condizioni.condcTableValue.scrap2}" pattern="#,##0.00"/>%
                            </c:if>
                            </td>
                            <c:forEach var="col" items="${condizioni.condcTable}">
                                <td><c:if test="${!empty condizioni.condcTableValue[col.key]}">
                                        <c:choose>
                                            <c:when test="${col.key=='fidorich'}">
                                                Euro <fmt:formatNumber value="${condizioni.condcTableValue[col.key]}" pattern="#,##0.00"/>
                                            </c:when>
                                            <c:otherwise>
                                                ${condizioni.condcTableValue[col.key]}
                                            </c:otherwise>
                                        </c:choose>
                                    </c:if></td>
                            </c:forEach>
                        </tr>
                    </tbody>
                </table>
                </div>
            </c:if>
        </c:when>
        <c:otherwise>
            <spring:message code="condc.notavailable" text="Condizioni di vendita non disponibili"/>
        </c:otherwise>
    </c:choose>

</div>

<jsp:directive.include file="/WEB-INF/jsp/footer.jsp"/>