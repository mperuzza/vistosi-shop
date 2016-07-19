<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<c:set var="cdentePrec" value=""/>
<c:set var="tkcliePrec" value=""/>
<c:set var="dsart">dsarti${atkLangSfx}</c:set>
<c:set var="dsestesa">dsestesa${atkLangSfx}</c:set>
<c:forEach items="${clientiList}" var="item" end="40">
    <c:if test="${cdentePrec != item.archenti.cdente}">
    <li id="${item.archclie.tkclie}">
    <c:choose>
        <c:when test="${by=='cdm'}"><div class="fieldsearch">${fn:trim(item.archclie.cdclieM)}</div><div class="other-data">${item.archenti.ragcog} CF:${item.archenti.cdfisc} PIVA:${item.archenti.pariva} - ${item.sedeLegale.comune.dscomu} ${item.sedeLegale.unitalocali.cap} ${item.sedeLegale.unitalocali.indiri} ${item.sedeLegale.province.dsprov}</div></c:when>
        <c:when test="${by=='ds'}"><div class="fieldsearch">${fn:trim(item.archenti.ragcog)}</div><div class="other-data">${item.archclie.cdclieM} CF:${item.archenti.cdfisc} PIVA:${item.archenti.pariva} - ${item.sedeLegale.comune.dscomu} ${item.sedeLegale.unitalocali.cap} ${item.sedeLegale.unitalocali.indiri} ${item.sedeLegale.province.dsprov}</div></c:when>
    </c:choose>
    </li>
    </c:if>
    <c:set var="cdentePrec" value="${item.archenti.cdente}"/>
</c:forEach>
<c:forEach items="${automezziList}" var="item">
    <li id="${item.automezzo.cdautome}"><div class="fieldsearch">${item.automezzo.targam}</div><div class="other-data">${fn:trim(item.archenti.ragcog)}|${item.vettore.dsvett}|${item.personale.cognNom}</div></li>
</c:forEach>
<c:forEach items="${comuniList}" var="item">
    <li id="${item.cdcomu}"><div class="fieldsearch">${item.dscomu}</div><div class="other-data">${fn:trim(item.cdcomuM)}</div></li>
</c:forEach>
<c:forEach items="${articoliList}" var="item" end="20">
    <c:choose>
        <c:when test="${by=='cdm'}"><li id="${item.cdarti}"><div class="fieldsearch">${fn:trim(item.cdartm)}</div><div class="other-data">UM:${item.unimisura.cdunimM}; Cd. art.:${item.dsarti}</div></li></c:when>
        <c:when test="${by=='ds'}"><li id="${item.cdarti}"><div class="fieldsearch">${fn:trim(item.dsarti)}</div><div class="other-data">UM:${item.unimisura.cdunimM}; Cd. art.:${item.cdartm}</div></li></c:when>
        <c:otherwise>
            <c:choose>
                <%--c:when test="${!empty t.cdartirif}"><c:set var="filedis">${t.articolo.datiRicambio.imgv_filename}</c:set></c:when--%>
                <c:when test="${!empty item.vist_filedis}"><c:set var="filedis">${item.vist_filedis}.jpg</c:set></c:when>
                <c:otherwise><c:set var="filedis">${item.vist_famiglia.cdvistfam_mPad}${item.cdvisttp}${item.cdvistv1Pad}${item.cdvistv2Pad}${item.cdvistv3Pad}-.jpg</c:set></c:otherwise>
            </c:choose>
                <li id="${item.cdarti}" style="clear: both" ><div class="item-cnt clearfix"><div style="float:left" class="item-img"><img width="50" src="<c:url value='/static/images/articoli/disegnitecnici/thumb/${filedis}'/>" title="${fn:trim(item[dsestesa])}" alt="${fn:trim(item[dsestesa])}"/></div><div class="fieldsearch" style="text-transform: uppercase">${fn:trim(item[dsestesa])}</div><div class="other-data">${item.cdartm}</div></div></li></c:otherwise>
    </c:choose>
</c:forEach>
<%--c:if test="${empty articoliList }">
    <li id="-1"><div class="fieldsearch">Nessun risultato</div></li>
</c:if--%>
<%--c:if test="${empty clientiList
           && empty automezziList
           && empty comuniList
           && empty articoliList }">
    <li id="-1"><div class="fieldsearch">Nessun risultato</div></li>
</c:if--%>