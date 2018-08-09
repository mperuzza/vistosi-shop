<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<c:set var="plainMenu" value="true"/>
<jsp:directive.include file="/WEB-INF/jsp/header.jsp"/>
<c:set var="dstp">dsextvisttp${atkLangSfx}</c:set>
<div id="search-cnt">
<h2><spring:message code="advanced.search.title" text="Ricerca avanzata"/></h2>
<h3><spring:message code="advanced.search.result" text="Trova risultati"/>:</h3>
  <form id="searchForm" name="searchForm" action="<c:url value='/findArticoli'/>">
      <div>
          <div class="search-down"><spring:message code="advanced.search.containing" text="che contengano questa <strong>parola</strong>"/>: </div>
        <div class="search-left"><input type="text" id="descr" name="descr" value="<c:out value="${param.descr}"/>"/></div>
          <div class="search-down"><spring:message code="advanced.search.belonging" text="appartenenti a questa"/> <strong><spring:message code="typology" text="tipologia"/></strong>: </div>
      <div class="search-left"><select id="tipologie" name="cdvisttp">
            <option value=""><spring:message code="select.type" text="Seleziona tipologia"/></option>
            <c:forEach items="${tipologie}" var="t" varStatus="s">
                <option value="${t.cdvisttp}" <c:if test="${param.cdvisttp==t.cdvisttp}">selected</c:if>>${t[dstp]}</option>
            </c:forEach>
        </select></div>
        <div class="search-down"><spring:message code="advanced.search.belonging" text="appartenenti a questa"/> <strong><spring:message code="family" text="famiglia"/></strong>: </div>
      <div class="search-left"><select id="famiglie" name="cdvistfam">
            <option value=""><spring:message code="select.family" text="Seleziona famiglia"/></option>
            <c:forEach items="${famiglie}" var="t" varStatus="s">
                <option value="${t.cdvistfam}" <c:if test="${param.cdvistfam==t.cdvistfam}">selected</c:if>>${t.dsvistfam}</option>
            </c:forEach>
        </select></div>

        </div>
        <div class="button" style="clear:both; margin-left:200px;">
        <a href="javascript:void(0)" id="searchBtn"><span><em><spring:message code="button.search" text="Cerca"/></em></span></a>
    </div>
    </form>
    <c:choose>
        <c:when test="${!empty theList && theList.fullListSize != 0}">
            <c:url var="searchUrl" value="findArticoli">
                <c:param name="descr" value="${param.descr}"/>
                <c:param name="cdvisttp" value="${param.cdvisttp}"/>
                <c:param name="cdvistfam" value="${param.cdvistfam}"/>
            </c:url>
            <c:set var="pagination">
            <div class="pagination">
                <div class="page-links"><a href="<c:url value="${searchUrl}"><c:param name="page" value="1"/></c:url>">&lt;&lt;</a>
            <a href="<c:url value="${searchUrl}"><c:param name="page" value="${theList.prev}"/></c:url>">&lt;</a>
            <c:forEach begin="${theList.startRange}" end="${theList.endRange}" var="p"> <a href="<c:url value="${searchUrl}"><c:param name="page" value="${p}"/></c:url>" class="pag${(p==theList.pageNumber)?"-on":""}"><c:choose><c:when test="${p==theList.pageNumber}"><strong>${p}</strong></c:when><c:otherwise>${p}</c:otherwise></c:choose></a></c:forEach>
            <a href="<c:url value="${searchUrl}"><c:param name="page" value="${theList.next}"/></c:url>" >&gt;</a> <a href="<c:url value="${searchUrl}"><c:param name="page" value="${theList.pages}"/></c:url>">&gt;&gt;</a>
                </div><span style="text-transform: uppercase"><spring:message code="pages" text="PAGINE"/></span>:
            </div>
            </c:set>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            ${pagination}
            <table>
                    <tr>
                        <th>&nbsp;</th>
                        <th><spring:message code="description" text="Descrizione"/></th>
                        <th><spring:message code="varianti" text="Variante"/></th>
                    </tr>
                <c:set var="dsestesa">dsestesa${atkLangSfx}</c:set>
                <c:forEach items="${theList.list}" var="a" varStatus="as">



                    <spring:url var="schedaURL" value="/scheda-{dsfam}-{cdvisttp}/{cdvistfam}/">
                       <spring:param name="dsfam" value="${a.vist_famiglia.dsvistfam}" />
                       <spring:param name="cdvisttp" value="${a.cdvisttp}" />
                       <spring:param name="cdvistfam" value="${a.cdvistfam}" />
                       <c:choose>
                           <c:when test="${a.ricambio}">
                               <spring:param name="cdartiric" value="${a.cdarti}" />
                           </c:when>
                           <c:otherwise>
                               <spring:param name="cdarti" value="${a.cdarti}" />
                           </c:otherwise>
                       </c:choose>
                    </spring:url>
                    <tr class="riga">
                        <td width="90"><a href="${schedaURL}"><img src="<c:url value='/static/images/articoli/disegnitecnici/thumb/${(useSpeclist)?"po/":""}${a.vist_filedis}.jpg'/>" title="${a.dsarti}" alt="${a.dsarti}"/></a></td>
                        <td>
                            <table>
                                <tr>
                                    <td><span style="text-transform: uppercase">${a[dsestesa]}</span></td>
                                </tr>
                                <tr>
                                    <td>
                                        <div>
                                            <spring:message code="istruzioni_montaggio" text="Istruzioni di montaggio" var="istrLabel"/>
                                            <spring:message code="modelli_3D" text="Modelli 3D" var="m3DLabel"/>
                                            <spring:message code="modelli_2D" text="Modelli 2D" var="m2DLabel"/>
                                            <security:authorize ifAllGranted="ROLE_ANONYMOUS">
                                                <c:set var="downloadUrl" value="${portalUrl}download/"/>
                                                <c:set var="dlink_class" value="downloadlink"/>
                                            </security:authorize>
                                            <security:authorize ifNotGranted="ROLE_ANONYMOUS">
                                                <c:set var="downloadUrl" value="${portalUrl}download/"/>
                                                <c:set var="dlink_class" value=""/>
                                            </security:authorize>
                                            <c:set var="dselet">dsextvistelet${atkLangSfx}</c:set>
                                            <c:set var="dsv1">dsextvistv1${atkLangSfx}</c:set>
                                            <c:set var="dsv2">dsextvistv2${atkLangSfx}</c:set>
                                            <c:set var="dsv3">dsextvistv3${atkLangSfx}</c:set>
                                            <c:set var="descrv1"/>
                                            <c:if test="${!empty a.cdvistv1}"><c:set var="descrv1" value="${fn:toUpperCase(a.vist_var1[dsv1])}"/></c:if>
                                            <c:set var="descrv2"/>
                                            <c:if test="${!empty a.cdvistv2}"><c:set var="descrv2" value="${fn:toUpperCase(a.vist_var2[dsv2])}"/></c:if>
                                            <c:set var="descrv3"/>
                                            <c:if test="${!empty a.cdvistv3}"><c:set var="descrv3" value="${fn:toUpperCase(a.vist_var3[dsv3])}"/></c:if>    
                                            <c:set var="descrFile" value="${a.vist_famiglia[dsfam]} ${a.cdvisttp} ${descrv1} ${descrv2} ${descrv3} ${fn:toUpperCase(a.vist_elettrificazioni[dselet])}"/>
                            
                                            <security:authorize ifAllGranted="ROLE_ANONYMOUS">
                                            <c:if test="${!empty a.vist_articoli_img && a.vist_articoli_img.pathschtecExists}"><a class="${dlink_class}" href="${downloadUrl}fileresources/assembling_instructions/${a.vist_articoli_img.pathschtec}?f=fileresources/assembling_instructions/${a.vist_articoli_img.pathschtec}" data-purl="${downloadRequestUrl}&tipo_richiesta=si_res&file_req=fileresources/assembling_instructions/${a.vist_articoli_img.pathschtec}&dsfile=${istrLabel} ${descrFile}${reqDetails}&tiporisorsa=${a.tiporisorsaIstruzioni}" target="_blank" title="<spring:message code="msg_file_exist" arguments="${istrLabel}" text="Disponibile. Clicca per effettuare il download."/>"><img src="<c:url value="/static/images/pdf-icon.gif"/>"/></a></c:if> 
                                            <c:if test="${a.model2D_dwgExists}"><a class="${dlink_class}" href="${downloadUrl}${a.model2D_dwg}?f=${a.model2D_dwg}" data-purl="${downloadRequestUrl}&tipo_richiesta=si_res&file_req=${a.model2D_dwg}&dsfile=${m2DLabel} ${descrFile}${reqDetails}&tiporisorsa=${a.tiporisorsa2D_dwg}" target="_blank" title="<spring:message code="msg_file_exist" arguments="${m2DLabel}" text="Disponibile. Clicca per effettuare il download."/>"><img src="<c:url value="/static/images/dwg-icon.gif"/>"/></a></c:if> 
                                            <c:if test="${a.model3D_easmExists}"><a class="${dlink_class}" href="${downloadUrl}${a.model3D_easm}?f=${a.model3D_easm}" data-purl="${downloadRequestUrl}&tipo_richiesta=si_res&file_req=${a.model3D_easm}&dsfile=${m3DLabel} ${descrFile}${reqDetails}&tiporisorsa=${a.tiporisorsa3D_easm}" target="_blank" title="<spring:message code="msg_file_exist" arguments="${m3DLabel}" text="Disponibile. Clicca per effettuare il download."/>"><img src="<c:url value="/static/images/easm-icon.gif"/>"/></a></c:if> 
                                            <%--c:if test="${a.model3D_eprtExists}"><a class="${dlink_class}" href="${downloadUrl}${a.model3D_eprt}&dsfile=${m3DLabel} ${descrFile}" target="_blank"><img src="<c:url value="/static/images/eprt-icon.gif"/>"/></a></c:if--%> 
                                            <%--c:if test="${a.model3D_igsExists}"><a class="${dlink_class}" href="${downloadUrl}${a.model3D_igs}&dsfile=${m3DLabel} ${descrFile}" target="_blank"><img src="<c:url value="/static/images/iges-icon.gif"/>"/></a></c:if--%> 
                                            </security:authorize>
                                            <security:authorize ifNotGranted="ROLE_ANONYMOUS">
                                            <c:if test="${!empty a.vist_articoli_img && a.vist_articoli_img.pathschtecExists}"><a class="${dlink_class}" href="${downloadUrl}fileresources/assembling_instructions/${a.vist_articoli_img.pathschtec}?f=fileresources/assembling_instructions/${a.vist_articoli_img.pathschtec}" data-purl="${downloadRequestUrl}&tipo_richiesta=si_res&file_req=fileresources/assembling_instructions/${a.vist_articoli_img.pathschtec}&dsfile=${istrLabel} ${descrFile}${reqDetails}&tiporisorsa=${a.tiporisorsaIstruzioni}" target="_blank" title="<spring:message code="msg_file_exist" arguments="${istrLabel}" text="Disponibile. Clicca per effettuare il download."/>"><img src="<c:url value="/static/images/pdf-icon.gif"/>"/></a></c:if> 
                                            <c:if test="${a.model2D_dwgExists}"><a class="${dlink_class}" href="${downloadUrl}${a.model2D_dwg}?f=${a.model2D_dwg}" data-purl="${downloadRequestUrl}&tipo_richiesta=si_res&file_req=${a.model2D_dwg}&dsfile=${m2DLabel} ${descrFile}${reqDetails}&tiporisorsa=${a.tiporisorsa2D_dwg}" target="_blank" title="<spring:message code="msg_file_exist" arguments="${m2DLabel}" text="Disponibile. Clicca per effettuare il download."/>"><img src="<c:url value="/static/images/dwg-icon.gif"/>"/></a></c:if> 
                                            <c:if test="${a.model3D_easmExists}"><a class="${dlink_class}" href="${downloadUrl}${a.model3D_easm}?f=${a.model3D_easm}" data-purl="${downloadRequestUrl}&tipo_richiesta=si_res&file_req=${a.model3D_easm}&dsfile=${m3DLabel} ${descrFile}${reqDetails}&tiporisorsa=${a.tiporisorsa3D_easm}" target="_blank" title="<spring:message code="msg_file_exist" arguments="${m3DLabel}" text="Disponibile. Clicca per effettuare il download."/>"><img src="<c:url value="/static/images/easm-icon.gif"/>"/></a></c:if> 
                                            </security:authorize>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td>${a.cdvistv1}${a.cdvistv2}${a.cdvistv3}</td>
                    </tr>
                </c:forEach>
            </table>
                    ${pagination}
        </c:when>
        <c:otherwise>
            <div class="clearfix"></div>
            <div class="result">
                <h2><spring:message code="basic.msg.empty_list" text="Nessun articolo trovato"/></h2>
            </div>
        </c:otherwise>
    </c:choose>
</div>
  
    
<script type="text/javascript">
    $('searchBtn').addEvent('click', function(e){
        $('searchForm').submit();
    });
</script>

<jsp:directive.include file="/WEB-INF/jsp/footer.jsp"/>
