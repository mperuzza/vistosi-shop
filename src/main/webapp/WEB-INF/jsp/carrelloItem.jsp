<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<spring:url value="/carrelloItem-{cdarti}/" var="formURL">
    <spring:param name="cdarti" value="${carrelloItem.articolo.cdarti}"/>
</spring:url>
<c:set var="hideCond" value="false"/>
<security:authorize ifAnyGranted="ROLE_SUBUTENTE, ROLE_ANONYMOUS"><c:set var="hideCond" value="true"/></security:authorize>
<div class="art-detail clearfix <security:authorize ifAllGranted="ROLE_ANONYMOUS">wishlist</security:authorize>">    
<form:form modelAttribute="carrelloItem" method="post" action="${formURL}" id="carrelloItemForm${isRicambio?'Ric':''}">
    <form:hidden path="articolo.cdarti" id="${isRicambio?'articoloRic.cdarti':'articolo.cdarti'}"/>
    <form:hidden path="ord_positito.cdartirif"/>
    <%--c:if test="${isRicambio}">
        <form:hidden path="ord_positito.cdartirif"/>
    </c:if--%>
    <security:authorize ifNotGranted="ROLE_ANONYMOUS">
        <security:authentication property="principal.cliente.archclie.cdlist" var="cdlist"/>
        <c:choose><c:when test="${cdlist == 'LUS'}"><c:set var="valuta" value="USD"/></c:when><c:when test="${cdlist == 'LCA'}"><c:set var="valuta" value="CAD"/></c:when><c:otherwise><c:set var="valuta" value="€"/></c:otherwise></c:choose>
        <div id="prezzi" class="dd-scheda clearfix det-row">
            <div id="prz-lis" class="det-block <c:if test="${carrelloItem.articolo.fgpromo=='S'}">offerta</c:if>"><span class="det-label"><span style="text-transform:capitalize"><spring:message code="price" text="Prezzo"/></span> <spring:message code="listino" text="listino"/></span><span class="det-value"><fmt:formatNumber value="${prezzo}" pattern="#,##0.00"/> ${valuta}</span></div>
            <div id="sc-val" class="det-block"><c:if test="${cookie.view_net_price.value == 'S' && !hideCond}"><span class="det-label"><span style="text-transform:capitalize"><spring:message code="sconto" text="Sconto"/></span></span><span class="det-value"><fmt:formatNumber value="${sconto}" pattern="#,##0.00"/> ${valuta}<br/>(${ls_sconto}%)</span></c:if></div>
            <div id="prz-net" class="det-block"><c:if test="${cookie.view_net_price.value == 'S' && !hideCond}"><span class="det-label"><span style="text-transform:capitalize"><spring:message code="price" text="Prezzo"/></span> <spring:message code="netto" text="netto"/></span><span class="det-value"><fmt:formatNumber value="${prezzo_netto}" pattern="#,##0.00"/> ${valuta}</span></c:if></div>
        </div>
        <div id="disponibilita" class="dd-scheda clearfix det-row">
            <c:if test="${!empty carrelloItem.articolo.mrp_file_giacenza && !empty carrelloItem.articolo.mrp_file_giacenza.dtprdisp}">
                <div id="dtdisp-val" class="det-block">
                    <span class="det-label"><spring:message code="dt.prox.disp" text="Data prossima disponibilità"/></span>
                    <span class="det-value"><fmt:formatDate value="${carrelloItem.articolo.mrp_file_giacenza.dtprdisp}" pattern="dd/MM/yyyy"/>(*)</span>
                </div>
            </c:if>
            <c:if test="${carrelloItem.articolo.fgpromo=='S' && !empty carrelloItem.articolo.vist_offerte && (carrelloItem.articolo.vist_offerte.zee_zef_teorico > 0 || carrelloItem.articolo.vist_offerte.incentive > 0)}">
                <div id="qtoff-val" class="det-block offerta">
                    <span class="det-label"><spring:message code="qta.offerta" text="Quantità in offerta"/>*</span>
                    <span class="det-value"><c:choose><c:when test="${carrelloItem.articolo.vist_offerte.zee_zef_teorico>0}"><fmt:formatNumber value="${carrelloItem.articolo.vist_offerte.zee_zef_teorico}" pattern="#,###"/></c:when><c:when test="${carrelloItem.articolo.vist_offerte.incentive>0}"><fmt:formatNumber value="${carrelloItem.articolo.vist_offerte.incentive}" pattern="#,###"/></c:when></c:choose></span>
                </div>
            </c:if>
        </div>
        <div id="qta" class="dd-scheda clearfix det-row">
            <div id="disp-val" class="det-block">
                <span class="det-label"><spring:message code="disponibilita" text="Disponibilità"/></span>
            <span class="det-value"><fmt:formatNumber value="${(carrelloItem.disp>=0)?carrelloItem.disp:0}" pattern="#,##0" type="number"/></span>
            </div>        
            <div id="qta-val" class="det-block"><span class="det-label"><spring:message code="qta" text="Quantità"/><c:if test="${isRicambio}"> <spring:message code="ricambio" text="ricambio"/></c:if></span><form:input path="ord_positito.qtatot" cssClass="qta in_num" size="10" cssErrorClass="in_num inputError" autocomplete="off"/>
            <form:errors path="ord_positito.qtatot" cssClass="errors" element="p"/></div>
            <div id="sub-tot" class="det-block"><span class="det-label"><spring:message code="subtotale" text="Subtotale"/></span><span class="det-value"><fmt:formatNumber value="${sub_tot}" pattern="#,##0.00"/> ${valuta}</span></div>
            <div id="ord-btn" class="det-block button-cart"><!--input type="submit" name="add_on_cart" id="add_on_cart" value="Ordina"/--> <a href="javacript:void(0)" id="add_on_cart${isRicambio?"Ric":""}"><span><em><spring:message code="button.order" text="Ordina"/></em></span></a></div>
        </div>
        <c:if test="${carrelloItem.articolo.fgpromo=='S' && !empty carrelloItem.articolo.vist_offerte && (carrelloItem.articolo.vist_offerte.zee_zef_teorico > 0 || carrelloItem.articolo.vist_offerte.incentive > 0)}">
            <div><small class="offerta">*<spring:message code="qtaoff.alert"/></small></div>
        </c:if>
    </security:authorize>
    <security:authorize ifAllGranted="ROLE_ANONYMOUS">
        <div id="qta-val" class="det-block"><span class="det-label"><spring:message code="qta" text="Quantità"/></span> <form:input path="ord_positito.qtatot" cssClass="qta in_num" size="10" cssErrorClass="in_num inputError" autocomplete="off"/>
        <form:errors path="ord_positito.qtatot" cssClass="errors" element="p"/></div>                
        <div id="ord-btn" class="button-cart"><a href="javacript:void(0)" id="add_on_cart${isRicambio?"Ric":""}"><span><em><c:choose><c:when test="${!empty carrelloItem.ord_positito.qtatot && carrelloItem.ord_positito.qtatot>0}"><spring:message code="button.wishlist.update" text="Add to wishlist"/></c:when><c:otherwise><spring:message code="button.wishlist.add" text="Add to wishlist"/></c:otherwise></c:choose></em></span></a></div>
    </security:authorize>
   
</form:form></div>
<script type="text/javascript">

    try{
        $('add_on_cart${isRicambio?"Ric":""}').addEvent('click', function(e){
        $('carrelloItemForm${isRicambio?"Ric":""}').fireEvent('submit', e);
        });
    }catch(e){
    }
    
    <c:if test="${!empty messages}">
            var msg = new Message({
                    iconPath: '<c:url value="/static/images/"/>',
                    icon: "check.png",
                    iconSize: 48,
                    title: "Ok!",
                    message: "${messages[0]}",
                    centered: true
            });
            msg.say();
        <c:remove var="messages" scope="session"/>
    </c:if>
        
    <c:if test="${!empty errmessages}">
            var msg = new Message({
                    iconPath: '<c:url value="/static/images/"/>',
                    icon: "cautionMedium.png",
                    title: "Warning!",
                    message: "${errmessages[0]}",
                    centered: true
            }).say();
        <c:remove var="errmessages" scope="session"/>
    </c:if>
    <c:if test="${isRicambio}">
    var msgric = new Element('p', {
    'class': 'art-det-msg',
        'html': '<span><spring:message code="art.cdartm" text="Codice articolo"/></span> ${carrelloItem.articolo.cdartm}'
    });
    $('ric-code').empty().adopt(msgric);                
    </c:if>
    new Form.Request($('carrelloItemForm${isRicambio?"Ric":""}'), $('carrelloItemForm${isRicambio?"Ric":""}').getParent().getParent(),
           {onSuccess: function(){
                var uri = '<c:url value="/miniCart"/>';
                if($('carrello-det')) $('carrello-det').empty().load(uri);
                if($('carrelloItemForm${isRicambio?"Ric":""}').getParent()==$('fast-cart-item')){
                    location.reload();
                }
           }});
</script>