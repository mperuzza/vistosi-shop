<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<security:authorize ifNotGranted="ROLE_ANONYMOUS">
<security:authentication property="principal.cliente.archclie.cdlist" var="cdlist"/>
<c:set var="hideCond" value="false"/>
<security:authorize ifAnyGranted="ROLE_SUBUTENTE"><c:set var="hideCond" value="true"/></security:authorize>
<c:choose><c:when test="${cdlist == 'LUS'}"><c:set var="valuta" value="USD"/></c:when><c:when test="${cdlist == 'LCA'}"><c:set var="valuta" value="CAD"/></c:when><c:otherwise><c:set var="valuta" value="€"/></c:otherwise></c:choose>
<ul>
<li><a class="cartLink" href="<c:url value='/fullCart'/>"><fmt:message key="cart"/></a></li>
<c:set var="nums">
    <c:choose>
        <c:when test="${minicart.numArt == 1}"><fmt:message key="singular.m"/></c:when>
        <c:otherwise><fmt:message key="plural"/></c:otherwise>
    </c:choose>
</c:set>    
<li class="first_cart"><table class="miniCart">
    <tbody>
        <tr>
            <td colspan="2" style="padding: 1px 0"><span class="thead"><spring:message code="plural" var="plural"/><spring:message code="articoli" arguments="${plural}"/></span></td>
        </tr>        
        <tr>
            <th><fmt:message key="totale"/> <fmt:message key="listino"/></th>
            <td class="in_num" nowrap><fmt:formatNumber value="${minicart.totListino}" pattern="#,##0.00"/> ${valuta}</td>
        </tr>
        <tr>
            <th><c:if test="${cookie.view_net_price.value == 'S' && !hideCond}"><fmt:message key="sconto"/></c:if></th>
            <td class="in_num"><c:if test="${cookie.view_net_price.value == 'S' && !hideCond}"><fmt:formatNumber value="${minicart.totSconto}" pattern="#,##0.00"/> ${valuta}</c:if></td>
        </tr>
        <tr>
            <th><c:if test="${cookie.view_net_price.value == 'S' && !hideCond}"><fmt:message key="totale"/> <fmt:message key="netto"/></c:if></th>
            <td class="in_num"><c:if test="${cookie.view_net_price.value == 'S' && !hideCond}"><fmt:formatNumber value="${minicart.totListinoNetto}" pattern="#,##0.00"/> ${valuta}</c:if></td>
        </tr>
        <tr>
            <th class="thbold al"><spring:message code="qta" text="quantità"/></th>
            <td class="thbold"><fmt:formatNumber value="${minicart.numArt}" pattern="#,##0.##"/></td>
        </tr>
    </tbody>
</table></li>
<c:set var="nums">
    <c:choose>
        <c:when test="${minicart.numArtOff == 1}"><fmt:message key="singular.m"/></c:when>
        <c:otherwise><fmt:message key="plural"/></c:otherwise>
    </c:choose>
</c:set>    
    <%--li class="second_cart"><table class="miniCart">
    <tbody>
        <tr>
            <td colspan="2" style="padding: 1px 0"><span class="thead"><spring:message code="cart.row.offer" text="offerta"/></span></td>
        </tr>
        <tr>
            <th><fmt:message key="totale"/> <fmt:message key="listino"/></th>
            <td class="in_num" nowrap><fmt:formatNumber value="${minicart.totListinoOff}" pattern="#,##0.00"/> ${valuta}</td>
        </tr>
        <tr>
            <th><c:if test="${cookie.view_net_price.value == 'S' && !hideCond}"><fmt:message key="sconto"/></c:if></th>
            <td class="in_num"><c:if test="${cookie.view_net_price.value == 'S' && !hideCond}"><fmt:formatNumber value="${minicart.totScontoOff}" pattern="#,##0.00"/> ${valuta}</c:if></td>
        </tr>
        <tr>
            <th><c:if test="${cookie.view_net_price.value == 'S' && !hideCond}"><fmt:message key="totale"/> <fmt:message key="netto"/></c:if></th>
            <td class="in_num"><c:if test="${cookie.view_net_price.value == 'S' && !hideCond}"><fmt:formatNumber value="${minicart.totListinoNettoOff}" pattern="#,##0.00"/> ${valuta}</c:if></td>
        </tr>
        <tr>
            <th class="thbold"><spring:message code="qta" text="quantità"/></th>
            <td class="thbold"><fmt:formatNumber value="${minicart.numArtOff}" pattern="#,##0.##"/></td>
        </tr>
    </tbody>
</table></li--%>
</ul>
<div class="viewcond al"><a id="chg_view_cond" href="javascript:void(0)" <c:if test="${cookie.view_net_price.value == 'S' && !hideCond}">class="hide-cond"</c:if>><c:choose><c:when test="${cookie.view_net_price.value == 'S' && !hideCond}"><spring:message code="hide.conditions" text="nascondi condizioni"/></c:when><c:when test="${(empty cookie.view_net_price || cookie.view_net_price.value == 'N') && !hideCond}"><spring:message code="view.conditions" text="visualizza condizioni"/></c:when></c:choose></a></div>
<c:if test="${!hideCond}">
<script type="text/javascript">    
    $('chg_view_cond').addEvent('click', function(e){

        e.stop();
        
        var viewCondValue = Cookie.read('view_net_price');
        if(viewCondValue=='S'){

            Cookie.write('view_net_price', 'N', {duration: 14, path: '<c:url value="/"/>'});  

        }else
        if(viewCondValue==null || viewCondValue=='N'){

            Cookie.write('view_net_price', 'S', {duration: 14, path: '<c:url value="/"/>'});

        }
        var uri = '<c:url value="/miniCart"/>';

        $('carrello-det').empty();

        new Request.HTML({url:uri,
                         update: $('carrello-det'),
                         onSuccess: function(){

                            if($('carrelloItemForm')!=null){
                                sendFormData();
                            }
                            if($('carrelloForm')!=null){
                               var uriCart = new URI('<c:url value="/fullCart"/>');
                               uriCart.go();
                            }
                         }
                     }).get();


        /*$('carrello-det').empty().load(uri);

        if(sendFormData!=null){
            sendFormData();
        }*/
       


    });
</script></c:if></security:authorize>
<security:authorize ifAllGranted="ROLE_ANONYMOUS">
    <ul>
    <li><a class="wishLink" href="<c:url value='/fullCart'/>"><fmt:message key="wishlist"/></a></li>
    <li><table class="miniCart">
    <tbody>
        <tr>
            <td colspan="2" style="padding: 1px 0"><a class="wishart" href="<c:url value='/fullCart'/>"><span class="thead"><spring:message code="plural" var="plural"/><spring:message code="articoli" arguments="${plural}"/> <spring:message code="wishlist.in" text="in wishlist"/></span></a></td>
        </tr>        
        <tr>
            <th class="thbold al"><spring:message code="qta" text="quantità"/></th>
            <td class="thbold" id="mini-wishl-qta"><fmt:formatNumber value="${minicart.numArt}" pattern="#,##0.##"/></td>
        </tr>
    </tbody>
</table></li></ul>
</security:authorize>
