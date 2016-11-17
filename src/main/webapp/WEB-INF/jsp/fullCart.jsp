<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<c:set var="plainMenu" value="true"/>
<c:set var="dsfam">dsvistfam${atkLangSfx}</c:set>
<c:set var="dstp">dsextvisttp${atkLangSfx}</c:set>
<c:set var="dsextfam">dsextvistfam${atkLangSfx}</c:set>
<c:set var="dscolv">dsextvistcolv${atkLangSfx}</c:set>
<c:set var="dsvetro">dsextvistvetro${atkLangSfx}</c:set>
<c:set var="dselet">dsextvistelet${atkLangSfx}</c:set>
<c:set var="dsfinm">dsextvistfinm${atkLangSfx}</c:set>
<c:set var="dsfinv">dsextvistfinv${atkLangSfx}</c:set>
<c:set var="dsv1">dsextvistv1${atkLangSfx}</c:set>
<c:set var="dsv2">dsextvistv2${atkLangSfx}</c:set>
<c:set var="dsv3">dsextvistv3${atkLangSfx}</c:set>
<security:authorize ifNotGranted="ROLE_ANONYMOUS">
<security:authentication property="principal.cliente.archclie.cdlist" var="cdlist"/>
</security:authorize>
<c:set var="hideCond" value="false"/>
<security:authorize ifAnyGranted="ROLE_SUBUTENTE"><c:set var="hideCond" value="true"/></security:authorize>
<c:choose><c:when test="${cdlist == 'LUS'}"><c:set var="valuta" value="USD"/></c:when><c:otherwise><c:set var="valuta" value="€"/></c:otherwise></c:choose>
<jsp:directive.include file="/WEB-INF/jsp/header.jsp"/>
  <script type="text/javascript" src="<c:url value='/static/js/Observer.js' />"></script>
  <script type="text/javascript" src="<c:url value='/static/js/Autocompleter.js' />"></script>
  <script type="text/javascript" src="<c:url value='/static/js/Autocompleter.Local.js' />"></script>
  <script type="text/javascript" src="<c:url value='/static/js/Autocompleter.Request.js' />"></script>
  <script type="text/javascript" src="<c:url value='/static/js/mediaboxAdv-1.3.4b.js' />"></script>
<script type="text/javascript">

    var autoCSS = Asset.css('<c:url value='/static/styles/Autocompleter.css'/>', {id: 'autoStyle', title: 'autoStyle'});


</script>
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/static/styles/mediaboxAdvBlack21.css'/>"/>
<h2 class="title-cart"><security:authorize ifNotGranted="ROLE_ANONYMOUS"><spring:message code="cart" text="Carrello"/></security:authorize>
    <security:authorize ifAllGranted="ROLE_ANONYMOUS"><spring:message code="wishlist" text="Wishlist"/></security:authorize></h2>
<security:authorize ifNotGranted="ROLE_ANONYMOUS">
<div id="fast-cart-search" class="clearfix">
    <div id="carrello_rapido">
        <div class="img_carrello">
            <img src="<c:url value='/static/images/quick_add.png' />" alt="Carrello rapido" class="img_search" />
        </div>
        <div class="search_carrello">
            <label><spring:message code="label.fastcarthp" text="Inserimento rapido prodotti in carrello"/> <span class="txt_cart_lower"><spring:message code="label.fastcarthp2" text="(cerca per codice articolo o descrizione)"/></span>: </label><input name="cdds" id="cdds" value="" style="width: 300px"/>
        </div>
    </div>
    <div id="fast-cart-cnt" class="fast-cart-hp" style="display:none;">
        <h4 id="load-cart-item"></h4>
        <div id="fast-cart-item" class="fast-cart-item-hp">&nbsp;</div>
    </div>
</div>
</security:authorize>

<div id="cart-cnt">
    <c:choose>
        <c:when test="${!empty cart.ord_posititos}">
            <form:form modelAttribute="cart" method="post" id="carrelloForm">
                <input type="hidden" name="gotodest" id="gotodest"/>
                <security:authorize ifNotGranted="ROLE_ANONYMOUS"> 
           <div id="step-cart">
          <table>
            <c:set var="step" value="1"/>
            <c:set var="page_pagamento" value="N"/>
            <tr>
                <td width="100" class="current uno">${step}
                  <c:set var="step" value="${step+1}"/>. <br />
<spring:message code="cart" text="Carrello"/></td>
              <td width="100" class="cinque">${step}
                <c:set var="step" value="${step+1}"/>. <br />
<spring:message code="cart.product.destination" text="Destinazione merce"/></td>
              <c:if test="${page_pagamento=='S'}">
              <td width="100" class="cinque">${step}
                <c:set var="step" value="${step+1}"/>. <br />
<spring:message code="cart.payment" text="Pagamento"/></td></c:if>
              <td width="100" class="quattro">${step}
                <c:set var="step" value="${step+1}"/>.<br />
<spring:message code="cart.confirm.order" text="Conferma ordine"/></td>
            </tr>
          </table>
          </div>
            </security:authorize>
                <c:if test="${!empty message}">
                    <div class="message fade-ffff00" id="msg" onclick="$(this).dispose()"><c:out value="${message}" escapeXml="false"/><br /><c:remove var="message" scope="session"/></div>
                </c:if>
                    <form:errors path="condcAccepted" cssClass="errors" element="p"/>
                <c:set var="footerHeadTot"><security:authorize ifNotGranted="ROLE_ANONYMOUS">
                        <tr class="cart-tfoot-0"><td colspan="15"></td></tr>
                        <tr class="cart-tfoot-1">
                          <th class="ar">&nbsp;</th>
                          <th class="ar">&nbsp;</th>
                          <th class="ar">&nbsp;</th>
                          <th class="ar">&nbsp;</th>
                          <%--th class="ar">&nbsp;</th>
                          <th class="ar">&nbsp;</th--%>
                          <th class="ar">&nbsp;</th>
                          <th nowrap="nowrap" class="al"><span class="al"><spring:message code="peso.lordo" text="Peso lordo" var="th_pesol"/>${fn:replace(th_pesol, " ", "<br/>")}</span></th>
                          <th nowrap="nowrap" class="al"><span class="al"><spring:message code="peso.netto" text="Peso netto" var="th_peson"/>${fn:replace(th_peson, " ", "<br/>")}</span></th>
                          <th nowrap="nowrap" class="ar"><span class="al"><spring:message code="volume" text="Volume"/></span></th>
                          <th nowrap="nowrap" class="al"></th>                          
                          <th nowrap="nowrap" class="ar"><spring:message code="qta" text="Quantità"/></th>
                          <th nowrap="nowrap" class="al"><spring:message code="totale" text="Totale"/><br/><spring:message code="listino" text="listino"/> ${valuta}</th>
                          <th nowrap="nowrap" class="ar"><c:if test="${cookie.view_net_price.value == 'S' && !hideCond}"><spring:message code="sconto" text="sconto"/> ${valuta}</c:if></th>
                          <th class="ar">&nbsp;</th>
                          <th colspan="2" nowrap="nowrap" class="al"><c:if test="${cookie.view_net_price.value == 'S' && !hideCond}"><spring:message code="totale" text="Totale"/><br/><spring:message code="netto" text="netto"/> ${valuta}</c:if></th>
                      </tr></security:authorize>                
                </c:set>
                <c:set var="footerHead"><security:authorize ifNotGranted="ROLE_ANONYMOUS">
                        <tr class="cart-tfoot-0"><td colspan="15"></td></tr>
                        <tr class="cart-tfoot-1-car">
                          <th class="ar">&nbsp;</th>
                          <th class="ar">&nbsp;</th>
                          <th class="ar">&nbsp;</th>
                          <th class="ar">&nbsp;</th>
                          <%--th class="ar">&nbsp;</th>
                          <th class="ar">&nbsp;</th--%>
                          <th class="ar">&nbsp;</th>
                          <th nowrap="nowrap" class="al"><span class="al"><spring:message code="peso.lordo" text="Peso lordo" var="th_pesol"/>${fn:replace(th_pesol, " ", "<br/>")}</span></th>
                          <th nowrap="nowrap" class="al"><span class="al"><spring:message code="peso.netto" text="Peso netto" var="th_peson"/>${fn:replace(th_peson, " ", "<br/>")}</span></th>
                          <th nowrap="nowrap" class="ar"><span class="al"><spring:message code="volume" text="Volume"/></span></th>
                          <th nowrap="nowrap" class="al"></th>
                          <th nowrap="nowrap" class="ar"><spring:message code="qta" text="Quantità"/></th>
                          <th nowrap="nowrap" class="al"><spring:message code="totale" text="Totale"/><br/><spring:message code="listino" text="listino"/> ${valuta}</th>
                          <th nowrap="nowrap" class="ar"><c:if test="${cookie.view_net_price.value == 'S' && !hideCond}"><spring:message code="sconto" text="sconto"/> ${valuta}</c:if></th>
                          <th class="ar">&nbsp;</th>
                          <th colspan="2" nowrap="nowrap" class="al"><c:if test="${cookie.view_net_price.value == 'S' && !hideCond}"><spring:message code="totale" text="Totale"/><br/><spring:message code="netto" text="netto"/> ${valuta}</c:if></th>
                      </tr></security:authorize>                
                </c:set>
                      
                <c:set var="fgpromo" value="N"/>
                <security:authorize ifNotGranted="ROLE_ANONYMOUS">
                <c:set var="fgpromo" value="${cart.ord_posititos[0].fgpromo}"/>
                </security:authorize>                
                <iframe name="iframe_open_file" id="iframe_open_file" 
                                    width="0"
                                    height="0"
                                    scrolling="auto"
                                    frameborder="0"
                                    marginheigth="0"
                                    marginwidth="0">
                    </iframe>                 
                <table>
                    <thead>
                        <tr>
                            <td colspan="<security:authorize ifNotGranted="ROLE_ANONYMOUS">15</security:authorize><security:authorize ifAllGranted="ROLE_ANONYMOUS">12</security:authorize>" nowrap="nowrap" style="padding-top: 20px;padding-bottom: 20px;"><div class="left"><div class="button left"><a href="javascript:history.back()" id="backBtn"><span><em><fmt:message key="back"/></em></span></a></div></div><div class="right"><security:authorize ifNotGranted="ROLE_ANONYMOUS"><div class="button left"><a href="javascript:void(0)" id="saveBtn" class="savebt"><span><em><fmt:message key="button.save.cart"/></em></span></a></div>
                                        <div class="button left"><a id="goonBtn" class="goonbt" href="<c:url value="catalogo_dest_merce.jsp"/>"><span><em><fmt:message key="button.complete.cart"/></em></span></a></div></security:authorize><c:if test="${!empty cart.ord_posititos}"><security:authorize ifAllGranted="ROLE_ANONYMOUS"><div class="button left"><a id="sendWl" class="sendwl" href="<c:url value="/wishlist"/>" rel="lightbox[external 500 280]"><span><em><spring:message code="button.wishlist.send" text="Invia wishlist"/></em></span></a></div></security:authorize></c:if></div>
                      </tr>       
                      
                      <c:if test="${fgpromo!='S'}">
                      
                          <tr>
                                <th class="al" style="width:86px"><spring:message code="model" text="Modello"/></th>
                                <th class="al" style="width:80px"><spring:message code="varianti" text="Varianti"/></th>
                                <th class="al"><spring:message code="col.vetro" text="Tonalità Vetro" var="th_vetro"/>${fn:replace(th_vetro, " ", "<br/>")}</th>
                                <th class="al"><spring:message code="fin.mont" text="Montatura" var="th_mont"/>${fn:replace(th_mont, " ", "<br/>")}</th>
                                <%--th class="al"><spring:message code="fin.vetro" text="Finitura vetro"/></th--%>
                                <th class="al"><spring:message code="elettr.short" text="Elettrificazione"/></th>
                                <th nowrap="nowrap" class="al" style="width:60px"><spring:message code="peso.lordo" text="Peso lordo" var="th_pesol"/>${fn:replace(th_pesol, " ", "<br/>")}</th>
                                <th nowrap="nowrap" class="al" style="width:60px"><spring:message code="peso.netto" text="Peso netto" var="th_peson"/>${fn:replace(th_peson, " ", "<br/>")}</th>
                              <th nowrap="nowrap" class="ar"><spring:message code="volume" text="Volume"/><br/>mc</th>
                              <th nowrap="nowrap" class="al"><spring:message code="art.cdartm" text="Cd. articolo" var="th_cdart"/>${th_cdart}</th>
                              <th nowrap="nowrap" class="ar"><spring:message code="qta" text="Quantità"/></th>
                              <security:authorize ifNotGranted="ROLE_ANONYMOUS">
                              <th nowrap="nowrap" class="al"><spring:message code="price" text="Prezzo"/> <c:choose><c:when test="${cookie.view_net_price.value == 'S' && !hideCond}"><br/><spring:message code="unitario" text="unitario"/></c:when><c:when test="${cookie.view_net_price.value == 'N'}"><br/><spring:message code="listino" text="listino"/></c:when></c:choose></th>
                              <th nowrap="nowrap" class="ar"><spring:message code="subtotale" text="Subtotale"/></th>
                              <th nowrap="nowrap" class="ar"><spring:message code="dt.prox.disp" text="Data prossima disponibilità" var="th_dtprox"/>${fn:replace(th_dtprox, " ", "<br/>")}</th>
                              </security:authorize>
                              <th>&nbsp;</th>
                              <th>&nbsp;</th>
                          </tr>
                      </c:if>
                    </thead>

                    <spring:message code="istruzioni_montaggio" text="Istruzioni di montaggio" var="istrLabel"/>
                    <spring:message code="modelli_3D" text="Modelli 3D" var="m3DLabel"/>
                    <spring:message code="modelli_2D" text="Modelli 2D" var="m2DLabel"/>
                    <spring:message code="specsheet" text="Specsheet" var="specSheetLabel"/>
                    <spring:message code="energy_class" text="Energy class" var="energyClassLabel"/>
                    <security:authorize ifAllGranted="ROLE_ANONYMOUS">
                        <%-- c:set var="downloadUrl" value="${eprogenUrl}epRichiesta_risorse_pubblica_form.jsp?origine_richiesta=PUBBLICA&lang=${rc.locale}&cdling=${cdling}"/>
                        <c:set var="downloadRequestUrl" value="${eprogenUrl}epRichiesta_risorse_pubblica_form.jsp?origine_richiesta=PUBBLICA&lang=${rc.locale}&cdling=${cdling}"/ --%>
                        <c:set var="downloadUrl" value="${portalUrl}download/"/>
                        <c:set var="downloadUrlForm" value="${eprogenUrl}epRichiesta_risorse_pubblica_form.jsp?origine_richiesta=PUBBLICA&lang=${rc.locale}&cdling=${cdling}"/>
                        <c:set var="downloadRequestUrl" value="${eprogenUrl}epRichiesta_risorse_pubblica_ajax.jsp?origine_richiesta=PUBBLICA&lang=${rc.locale}&cdling=${cdling}&dscontatto=Generico&email=generico@vistosi.it&ragsoc=Generico&citta=Mogliano Veneto&cdnazi=IT&fg_rivend_o_prof=S&fg_no_notif=S"/>
                        <c:set var="dlink_class" value="downloadlink"/>
                        <c:set var="drequestlink_class" value="downloadreqlink"/>
                        <c:set var="dproxylink_class" value="downloadproxylink"/>
                    </security:authorize>
                    <security:authorize ifNotGranted="ROLE_ANONYMOUS">
                        <c:set var="downloadUrl" value="${portalUrl}download/"/>
                        <c:set var="dlink_class" value=""/>
                        <c:set var="tkutente_rif"><security:authentication property="principal.userDB.tkutente"/></c:set>
                        <c:set var="downloadRequestUrl" value="${eprogenUrl}epRichiesta_risorse_pubblica_ajax.jsp?origine_richiesta=SHOP&lang=${rc.locale}&cdling=${cdling}&tkutente_rif=${tkutente_rif}"/>
                        <c:set var="drequestlink_class" value="downloadreqlink"/>
                        <c:set var="dproxylink_class" value="downloadproxylink"/>
                    </security:authorize>                    
                    <tbody>
                        <c:forEach items="${cart.ord_posititos}" var="t" varStatus="s">
                        <form:hidden path="ord_posititos[${s.index}].tkposi"/>

                        <spring:url var="schedaURL" value="/scheda-{dsfam}-{cdvisttp}/{cdvistfam}/">
                           <c:choose>
                           <c:when test="${!empty t.cdartirif && !empty t.articolorif}">
                               <spring:param name="dsfam" value="${t.articolorif.vist_famiglia.dsvistfam}" />
                               <spring:param name="cdvisttp" value="${t.articolorif.cdvisttp}" />
                               <spring:param name="cdvistfam" value="${t.articolorif.cdvistfam}" />
                               <spring:param name="cdarti" value="${t.articolorif.cdarti}" />
                               <spring:param name="cdartiric" value="${t.cdarti}" />
                           </c:when>
                           <c:otherwise>
                               <spring:param name="dsfam" value="${t.articolo.vist_famiglia.dsvistfam}" />
                               <spring:param name="cdvisttp" value="${t.articolo.cdvisttp}" />
                               <spring:param name="cdvistfam" value="${t.articolo.cdvistfam}" />
                               <spring:param name="cdarti" value="${t.articolo.cdarti}" />
                           </c:otherwise>
                           </c:choose>
                        </spring:url>
                        
                        <security:authorize ifNotGranted="ROLE_ANONYMOUS">
                        <c:if test="${(t.fgpromo != fgpromo && t.fgpromo == 'S') || (fgpromo=='S' && s.first)}">
                            <c:if test="${fgpromo!='S'}">
                                <c:out value="${footerHead}" escapeXml="false"/>
                                <jsp:directive.include file="/WEB-INF/jspf/_cartFooter.jspf"/>   
                            </c:if>
                            <c:set var="rowType" value=""/>
                            <tr class="riga">
                                <td colspan="15"><h2 style="color:rgb(51,153,51);"><spring:message code="cart.row.offer" text="Articoli in offerta"/></h2></td>
                            </tr>
                            <c:set var="fgpromo" value="${t.fgpromo}"/>
                        </c:if>
                        </security:authorize>
                        
                        <tr class="riga"> 
                            <c:set var="descrv1"/>
                            <c:if test="${!empty t.articolo.cdvistv1}"><c:set var="descrv1" value="${fn:toUpperCase(t.articolo.vist_var1[dsv1])}"/></c:if>
                            <c:set var="descrv2"/>
                            <c:if test="${!empty t.articolo.cdvistv2}"><c:set var="descrv2" value="${fn:toUpperCase(t.articolo.vist_var2[dsv2])}"/></c:if>
                            <c:set var="descrv3"/>
                            <c:if test="${!empty t.articolo.cdvistv3}"><c:set var="descrv3" value="${fn:toUpperCase(t.articolo.vist_var3[dsv3])}"/></c:if>    
                            <c:set var="descrFile" value="${t.articolo.vist_famiglia[dsfam]} ${t.articolo.cdvisttp} ${descrv1} ${descrv2} ${descrv3} ${fn:toUpperCase(t.articolo.vist_elettrificazioni[dselet])}"/>
                            <c:set var="reqDetails">&nome_modello=${t.articolo.vist_filedis}&cdvistelet=${t.articolo.cdvistelet}</c:set>                                                       
                            <c:choose>
                                <c:when test="${!empty t.cdartirif}"><c:set var="filedis">${t.articolo.datiRicambio.imgv_filename}</c:set></c:when>
                                <c:when test="${!empty t.articolo.vist_filedis}"><c:set var="filedis">${t.articolo.vist_filedis}.jpg</c:set></c:when>
                                <c:otherwise><c:set var="filedis">${t.articolo.vist_famiglia.cdvistfam_mPad}${t.articolo.cdvisttp}${t.articolo.cdvistv1Pad}${t.articolo.cdvistv2Pad}${t.articolo.cdvistv3Pad}-.jpg</c:set></c:otherwise>
                            </c:choose>
                            <td><a href="${schedaURL}"><img width="80" src="<c:url value='/static/images/articoli/disegnitecnici/thumb/${filedis}'/>" title="${t.articolo.dsarti}" alt="${t.articolo.dsarti}"/></a></td>
                            <td></td>
                            <td><img width="80" src="<c:url value='/images/articoli/vetro/${t.articolo.vist_famiglia.cdvistfam_m}_${!empty t.articolo.vistTipiAlt ? t.articolo.vistTipiAlt[0].cdvisttp : t.articolo.cdvisttp}_${t.articolo.cdvistv1}_${t.articolo.cdvistcolv}${t.articolo.cdvistfinv}.jpg'/>"/></td>
                            <td><img width="80" src="<c:url value='/static/images/articoli/montature/${t.articolo.cdvistfinm}.jpg'/>"/></td>
                            <td></td>
                            <%--td colspan="4">${t.articolo.cdalias}<br/></td--%>
                            <td colspan="<security:authorize ifNotGranted="ROLE_ANONYMOUS">10</security:authorize><security:authorize ifAllGranted="ROLE_ANONYMOUS">7</security:authorize>"><security:authorize ifNotGranted="ROLE_ANONYMOUS"><div><spring:message code="nota" text="Nota"/>:</div>
                                    <form:textarea path="ord_posititos[${s.index}].web_ord_posi_note.testo" rows="2"/></security:authorize>
                            <div>
                            <security:authorize ifAllGranted="ROLE_ANONYMOUS">
                                <c:choose>
                                    <c:when test="${!empty t.articolo.vist_articoli_img && t.articolo.vist_articoli_img.pathschtecExists}">
                                        <a class="${dproxylink_class}" href="${downloadUrl}fileresources/assembling_instructions/${t.articolo.vist_articoli_img.pathschtec}?f=fileresources/assembling_instructions/${t.articolo.vist_articoli_img.pathschtec}" data-purl="${downloadRequestUrl}&tipo_richiesta=si_res&file_req=fileresources/assembling_instructions/${t.articolo.vist_articoli_img.pathschtec}&dsfile=${istrLabel} ${descrFile}${reqDetails}&tiporisorsa=${t.articolo.tiporisorsaIstruzioni}" target="_blank" title="<spring:message code="msg_file_exist" arguments="${istrLabel}" text="Disponibile. Clicca per effettuare il download."/>"><img src="<c:url value="/static/images/pdf-icon.gif"/>"/></a>
                                    </c:when> 
                                    <c:otherwise>
                                        <c:set var="namefileReq"><c:choose><c:when test="${!empty t.articolo.vist_articoli_img}">${t.articolo.vist_articoli_img.pathschtec}</c:when><c:otherwise>IM|${t.articolo.cdvisttp}|${t.articolo.cdvistfam}|${t.articolo.cdvistv1}|${t.articolo.cdvistv2}|${t.articolo.cdvistv3}|${t.articolo.cdvistelet}</c:otherwise></c:choose></c:set>
                                        <a class="${dlink_class}" href="${downloadUrlForm}&tipo_richiesta=no_res&file_req=fileresources/assembling_instructions/${namefileReq}&dsfile=${istrLabel} ${descrFile}${reqDetails}&tiporisorsa=${t.articolo.tiporisorsaIstruzioni}" target="_blank" title="<spring:message code="msg_file_no_exist" arguments="${istrLabel}" text="Non disponibile. Clicca qui per richiederlo."/>"><img src="<c:url value="/static/images/no-pdf-icon.gif"/>"/></a></c:otherwise> 
                                </c:choose>
                                <c:choose>
                                    <c:when test="${t.articolo.model2D_dwgExists}">
                                    <a class="${dproxylink_class}" href="${downloadUrl}${t.articolo.model2D_dwg}?f=${t.articolo.model2D_dwg}" data-purl="${downloadRequestUrl}&tipo_richiesta=si_res&file_req=${t.articolo.model2D_dwg}&dsfile=${m2DLabel} ${descrFile}${reqDetails}&tiporisorsa=${t.articolo.tiporisorsa2D_dwg}" target="_blank" title="<spring:message code="msg_file_exist" arguments="${m2DLabel}" text="Disponibile. Clicca per effettuare il download."/>"><img src="<c:url value="/static/images/dwg-icon.gif"/>"/></a>
                                    </c:when> 
                                    <c:otherwise>
                                    <a class="${dlink_class}" href="${downloadUrlForm}&tipo_richiesta=no_res&file_req=${t.articolo.model2D_dwg}&dsfile=${m2DLabel} ${descrFile}${reqDetails}&tiporisorsa=${t.articolo.tiporisorsa2D_dwg}" target="_blank" title="<spring:message code="msg_file_no_exist" arguments="${m2DLabel}" text="Non disponibile. Clicca qui per richiederlo."/>"><img src="<c:url value="/static/images/no-dwg-icon.gif"/>"/></a></c:otherwise> 
                                </c:choose>
                                <c:choose>
                                    <c:when test="${t.articolo.model3D_easmExists}">
                                    <a class="${dproxylink_class}" href="${downloadUrl}${t.articolo.model3D_easm}?f=${t.articolo.model3D_easm}" data-purl="${downloadRequestUrl}&tipo_richiesta=si_res&file_req=${t.articolo.model3D_easm}&dsfile=${m3DLabel} ${descrFile}${reqDetails}&tiporisorsa=${t.articolo.tiporisorsa3D_easm}" target="_blank" title="<spring:message code="msg_file_exist" arguments="${m3DLabel}" text="Disponibile. Clicca per effettuare il download."/>"><img src="<c:url value="/static/images/easm-icon.gif"/>"/></a>
                                    </c:when> 
                                    <c:otherwise><a class="${dlink_class}" href="${downloadUrlForm}&tipo_richiesta=no_res&file_req=${t.articolo.model3D_easm}&dsfile=${m3DLabel} ${descrFile}${reqDetails}&tiporisorsa=${t.articolo.tiporisorsa3D_easm}" target="_blank" title="<spring:message code="msg_file_no_exist" arguments="${m3DLabel}" text="Non disponibile. Clicca qui per richiederlo."/>"><img src="<c:url value="/static/images/no-easm-icon.gif"/>"/></a></c:otherwise> 
                                </c:choose>
                                <%--c:choose>
                                    <c:when test="${t.articolo.model3D_eprtExists}"><a class="${dlink_class}" href="${downloadUrl}&tipo_richiesta=si_res&file_req=${t.articolo.model3D_eprt}&dsfile=${m3DLabel} ${descrFile}" target="_blank"><img src="<c:url value="/static/images/eprt-icon.gif"/>"/></a></c:when> 
                                    <c:otherwise><a class="${dlink_class}" href="${downloadUrl}&tipo_richiesta=no_res&file_req=${t.articolo.model3D_eprt}&dsfile=${m3DLabel} ${descrFile}" target="_blank"><img src="<c:url value="/static/images/no-eprt-icon.gif"/>"/></a></c:otherwise> 
                                </c:choose--%>
                                <c:choose>
                                    <c:when test="${t.articolo.model3D_igsExists}">
                                        <a class="${dproxylink_class}" href="${downloadUrl}${t.articolo.model3D_igs}?f=${t.articolo.model3D_igs}" data-purl="${downloadRequestUrl}&tipo_richiesta=si_res&file_req=${t.articolo.model3D_igs}&dsfile=${m3DLabel} ${descrFile}${reqDetails}&tiporisorsa=${t.articolo.tiporisorsa3D_igs}" target="_blank" title="<spring:message code="msg_file_exist" arguments="${m3DLabel}" text="Disponibile. Clicca per effettuare il download."/>"><img src="<c:url value="/static/images/iges-icon.gif"/>"/></a>
                                    </c:when> 
                                    <c:otherwise><a class="${dlink_class}" href="${downloadUrlForm}&tipo_richiesta=no_res&file_req=${t.articolo.model3D_igs}&dsfile=${m3DLabel} ${descrFile}${reqDetails}&tiporisorsa=${t.articolo.tiporisorsa3D_igs}" target="_blank" title="<spring:message code="msg_file_no_exist" arguments="${m3DLabel}" text="Non disponibile. Clicca qui per richiederlo."/>"><img src="<c:url value="/static/images/no-iges-icon.gif"/>"/></a></c:otherwise> 
                                </c:choose>                                    
                            </security:authorize>
                            <security:authorize ifNotGranted="ROLE_ANONYMOUS">
                                <c:choose>
                                    <c:when test="${!empty t.articolo.vist_articoli_img && t.articolo.vist_articoli_img.pathschtecExists}"><a class="${dproxylink_class}" href="${downloadUrl}fileresources/assembling_instructions/${t.articolo.vist_articoli_img.pathschtec}?f=fileresources/assembling_instructions/${t.articolo.vist_articoli_img.pathschtec}" data-purl="${downloadRequestUrl}&tipo_richiesta=si_res&file_req=fileresources/assembling_instructions/${t.articolo.vist_articoli_img.pathschtec}&dsfile=${istrLabel} ${descrFile}${reqDetails}&tiporisorsa=${t.articolo.tiporisorsaIstruzioni}" target="_blank" title="<spring:message code="msg_file_exist" arguments="${istrLabel}" text="Disponibile. Clicca per effettuare il download."/>"><img src="<c:url value="/static/images/pdf-icon.gif"/>"/></a></c:when> 
                                    <c:otherwise>
                                        <c:set var="namefileReq"><c:choose><c:when test="${!empty t.articolo.vist_articoli_img}">${t.articolo.vist_articoli_img.pathschtec}</c:when><c:otherwise>IM|${t.articolo.cdvisttp}|${t.articolo.cdvistfam}|${t.articolo.cdvistv1}|${t.articolo.cdvistv2}|${t.articolo.cdvistv3}|${t.articolo.cdvistelet}</c:otherwise></c:choose></c:set>
                                    <a class="${drequestlink_class}" href="${downloadRequestUrl}&tipo_richiesta=no_res&file_req=fileresources/assembling_instructions/${namefileReq}&dsfile=${istrLabel} ${descrFile}${reqDetails}&tiporisorsa=${t.articolo.tiporisorsaIstruzioni}" target="_blank" title="<spring:message code="msg_file_no_exist" arguments="${istrLabel}" text="Non disponibile. Clicca qui per richiederlo."/>"><img src="<c:url value="/static/images/no-pdf-icon.gif"/>"/></a></c:otherwise> 
                                </c:choose>
                                <c:choose>
                                    <c:when test="${t.articolo.model2D_dwgExists}"><a class="${dproxylink_class}" href="${downloadUrl}${t.articolo.model2D_dwg}?f=${t.articolo.model2D_dwg}" data-purl="${downloadRequestUrl}&tipo_richiesta=si_res&file_req=${t.articolo.model2D_dwg}&dsfile=${m2DLabel} ${descrFile}${reqDetails}&tiporisorsa=${t.articolo.tiporisorsa2D_dwg}" target="_blank" title="<spring:message code="msg_file_exist" arguments="${m2DLabel}" text="Disponibile. Clicca per effettuare il download."/>"><img src="<c:url value="/static/images/dwg-icon.gif"/>"/></a></c:when> 
                                    <c:otherwise><a class="${drequestlink_class}" href="${downloadRequestUrl}&tipo_richiesta=no_res&file_req=${t.articolo.model2D_dwg}&dsfile=${m2DLabel} ${descrFile}${reqDetails}&tiporisorsa=${t.articolo.tiporisorsa2D_dwg}" target="_blank" title="<spring:message code="msg_file_no_exist" arguments="${m2DLabel}" text="Non disponibile. Clicca qui per richiederlo."/>"><img src="<c:url value="/static/images/no-dwg-icon.gif"/>"/></a></c:otherwise> 
                                </c:choose>
                                <c:choose>
                                    <c:when test="${t.articolo.model3D_easmExists}"><a class="${dproxylink_class}" href="${downloadUrl}${t.articolo.model3D_easm}?f=${t.articolo.model3D_easm}" data-purl="${downloadRequestUrl}&tipo_richiesta=si_res&file_req=${t.articolo.model3D_easm}&dsfile=${m3DLabel} ${descrFile}${reqDetails}&tiporisorsa=${t.articolo.tiporisorsa3D_easm}" target="_blank" title="<spring:message code="msg_file_exist" arguments="${m3DLabel}" text="Disponibile. Clicca per effettuare il download."/>"><img src="<c:url value="/static/images/easm-icon.gif"/>"/></a></c:when> 
                                    <c:otherwise><a class="${drequestlink_class}" href="${downloadRequestUrl}&tipo_richiesta=no_res&file_req=${t.articolo.model3D_easm}&dsfile=${m3DLabel} ${descrFile}${reqDetails}&tiporisorsa=${t.articolo.tiporisorsa3D_easm}" target="_blank" title="<spring:message code="msg_file_no_exist" arguments="${m3DLabel}" text="Non disponibile. Clicca qui per richiederlo."/>"><img src="<c:url value="/static/images/no-easm-icon.gif"/>"/></a></c:otherwise> 
                                </c:choose>
                                <%--c:choose>
                                    <c:when test="${t.articolo.model3D_eprtExists}"><a class="${dproxylink_class}" href="${downloadUrl}${t.articolo.model3D_eprt}?f=${t.articolo.model3D_eprt}" data-purl="${downloadRequestUrl}&tipo_richiesta=si_res&file_req=${t.articolo.model3D_eprt}&dsfile=${m3DLabel} ${descrFile}" target="_blank"><img src="<c:url value="/static/images/eprt-icon.gif"/>"/></a></c:when> 
                                    <c:otherwise><a class="${drequestlink_class}" href="${downloadRequestUrl}&tipo_richiesta=no_res&file_req=${t.articolo.model3D_eprt}&dsfile=${m3DLabel} ${descrFile}" target="_blank"><img src="<c:url value="/static/images/no-eprt-icon.gif"/>"/></a></c:otherwise> 
                                </c:choose--%>
                                <c:choose>
                                    <c:when test="${t.articolo.model3D_igsExists}"><a class="${dproxylink_class}" href="${downloadUrl}${t.articolo.model3D_igs}?f=${t.articolo.model3D_igs}" data-purl="${downloadRequestUrl}&tipo_richiesta=si_res&file_req=${t.articolo.model3D_igs}&dsfile=${m3DLabel} ${descrFile}${reqDetails}&tiporisorsa=${t.articolo.tiporisorsa3D_igs}" target="_blank" title="<spring:message code="msg_file_exist" arguments="${m3DLabel}" text="Disponibile. Clicca per effettuare il download."/>"><img src="<c:url value="/static/images/iges-icon.gif"/>"/></a></c:when> 
                                    <c:otherwise><a class="${drequestlink_class}" href="${downloadRequestUrl}&tipo_richiesta=no_res&file_req=${t.articolo.model3D_igs}&dsfile=${m3DLabel} ${descrFile}${reqDetails}&tiporisorsa=${t.articolo.tiporisorsa3D_igs}" target="_blank" title="<spring:message code="msg_file_no_exist" arguments="${m3DLabel}" text="Non disponibile. Clicca qui per richiederlo."/>"><img src="<c:url value="/static/images/no-iges-icon.gif"/>"/></a></c:otherwise> 
                                </c:choose>                                    
                            </security:authorize>
                            <c:if test="${!empty t.articolo.energyClass}"><a class="downloadlink" href="${eprogenUrl}epRichiesta_download_energyclass.jsp?cdarti=${t.articolo.cdarti}&cdling=${cdling}" target="_blank" title="<spring:message code="msg_file_exist" arguments="${energyClassLabel}" text="Disponibile. Clicca per effettuare il download."/>"><img src="<c:url value="/static/images/ce-icon.gif"/>"/></a></c:if>
                            <security:authorize ifNotGranted="ROLE_ANONYMOUS">
                                <c:choose>
                                    <c:when test="${t.articolo.specsheetExists}"><a class="${dlink_class}" href="<c:url value='/specsheet/${t.articolo.cdartm}'/>" target="_blank" title="<spring:message code="msg_file_exist" arguments="${specSheetLabel}" text="Disponibile. Clicca per effettuare il download."/>"><img src="<c:url value="/static/images/tech-icon.gif"/>"/></a></c:when> 
                                    <c:otherwise><a class="${drequestlink_class}" href="${downloadRequestUrl}&tipo_richiesta=no_res&file_req=specsheet/${t.articolo.cdartm}&dsfile=${specSheetLabel} ${descrFile}${reqDetails}&tiporisorsa=${t.articolo.tiporisorsaSpecsheet}" target="_blank" title="<spring:message code="msg_file_no_exist" arguments="${specSheetLabel}" text="Non disponibile. Clicca qui per richiederlo."/>"><img src="<c:url value="/static/images/no-tech-icon.gif"/>"/></a></c:otherwise> 
                                </c:choose>                              
                            </security:authorize>
                            <security:authorize ifAllGranted="ROLE_ANONYMOUS">
                                <c:choose>
                                    <%--c:when test="${t.articolo.specsheetExists}"><a class="${dlink_class}" href="<c:url value='${downloadUrlForm}&tipo_richiesta=si_res&file_req=/specsheet/${t.articolo.cdartm}'/>" target="_blank" title="<spring:message code="msg_file_exist" arguments="${specSheetLabel}" text="Disponibile. Clicca per effettuare il download."/>"><img src="<c:url value="/static/images/tech-icon.gif"/>"/></a></c:when> 
                                    <c:otherwise><a class="${dlink_class}" href="${downloadUrlForm}&tipo_richiesta=no_res&file_req=specsheet/${t.articolo.cdartm}&dsfile=${specSheetLabel} ${descrFile}" target="_blank" title="<spring:message code="msg_file_no_exist" arguments="${specSheetLabel}" text="Non disponibile. Clicca qui per richiederlo."/>"><img src="<c:url value="/static/images/no-tech-icon.gif"/>"/></a></c:otherwise--%> 
                                    <c:when test="${t.articolo.specsheetExists}"><a class="" href="<c:url value='/specsheet/${t.articolo.cdartm}'/>" target="_blank" title="<spring:message code="msg_file_exist" arguments="${specSheetLabel}" text="Disponibile. Clicca per effettuare il download."/>"><img src="<c:url value="/static/images/tech-icon.gif"/>"/></a></c:when> 
                                    <c:otherwise><a class="${dlink_class}" href="${downloadUrlForm}&tipo_richiesta=no_res&file_req=specsheet/${t.articolo.cdartm}&dsfile=${specSheetLabel} ${descrFile}${reqDetails}&tiporisorsa=${t.articolo.tiporisorsaSpecsheet}" target="_blank" title="<spring:message code="msg_file_no_exist" arguments="${specSheetLabel}" text="Non disponibile. Clicca qui per richiederlo."/>"><img src="<c:url value="/static/images/no-tech-icon.gif"/>"/></a></c:otherwise> 
                                </c:choose> 
                            </security:authorize>
                            <security:authorize ifNotGranted="ROLE_ANONYMOUS">
                                <c:if test="${!empty t.articolo.techNewsList}"><ul class="techNews-list"><li data-tk="${t.articolo.techNewsList[0].tkpost}"><a href="${portalUrl}techNews/<fmt:formatDate value="${t.articolo.techNewsList[0].postDate}" pattern="yyyy/MM/dd/"/>${t.articolo.techNewsList[0].tkpost}?locale=${rc.locale}&cdvistfam=${t.articolo.cdvistfam}&cdvisttp=${t.articolo.cdvisttp}&cdvistelet=${t.articolo.cdvistelet}" target="_blank"><img src="<c:url value="/static/images/news-icon.gif"/>" alt="tech news"/></a><c:if test="${fn:length(t.articolo.techNewsList)>1}"><div class="notify-bubble">${fn:length(t.articolo.techNewsList)}</div></c:if></li></ul></c:if>
                            </security:authorize>
                                     
                            <c:forEach items="${t.articolo.certList}" var="cert">
                                <a href="${portalUrl}download/${cert['file']}?f=${cert['file']}" target="_blank"><img src="<c:url value="/static/images/articoli/specsheetres/dati/${cert['img']}.jpg"/>" height="36px" alt="tech news"/></a>
                            </c:forEach>
                            
                            </div>
                            </td>
                          </tr>
                        <tr>
                            <td class="cart-b">${!empty t.articolo.vist_famiglia[dsfam]?t.articolo.vist_famiglia[dsfam]:t.articolo.vist_famiglia['dsvistfam_eng']} <c:forEach items="${t.articolo.vistTipiAlt}" var="tpAlt" varStatus="tps">${tpAlt.cdvisttp}<c:if test="${!tps.last}">/</c:if></c:forEach>
                            <c:if test="${!empty t.cdartirif}"><br/>ricambio ${t.articolo.datiRicambio.numv}</c:if></td>
                            <td class="cart-b">
                                <c:choose><c:when test="${!empty t.articolo.vist_var1}">${fn:toLowerCase(t.articolo.vist_var1[dsv1])}</c:when><c:otherwise>${t.articolo.cdvistv1}</c:otherwise></c:choose>
                                <c:choose><c:when test="${!empty t.articolo.vist_var2}">${fn:toLowerCase(t.articolo.vist_var2[dsv2])}</c:when><c:otherwise>${t.articolo.cdvistv2}</c:otherwise></c:choose>
                                <c:choose><c:when test="${!empty t.articolo.vist_var3}">${fn:toLowerCase(t.articolo.vist_var3[dsv3])}</c:when><c:otherwise>${t.articolo.cdvistv3}</c:otherwise></c:choose></td>
                            <td class="cart-b" style="text-transform: capitalize; ">${fn:toLowerCase(t.articolo.vist_colori_vetro[dscolv])}<c:if test="${!empty t.articolo.vist_finit_vetro[dsfinv]}"><br/>${fn:toLowerCase(t.articolo.vist_finit_vetro[dsfinv])}</c:if></td>
                            <td class="cart-b" style="text-transform: capitalize; ">${fn:toLowerCase(t.articolo.vist_finit_mont[dsfinm])}</td>
                            <%--td class="cart-b">${t.articolo.vist_finit_vetro.dsvistfinv}</td--%>
                            <td class="cart-b" style="text-transform: capitalize">${fn:toLowerCase(t.articolo.vist_elettrificazioni[dselet])}</td>
                            <td class="ar" nowrap><div style="float:left; margin-right: 2px">Kg</div> <fmt:formatNumber value="${t.articolo.nrpeso_l}" pattern="#,##0.00"/></td>
                            <td class="ar" nowrap><div style="float:left; margin-right: 2px">Kg</div> <fmt:formatNumber value="${t.articolo.nrpeso_n}" pattern="#,##0.00"/></td>
                            <td class="ar" nowrap><fmt:formatNumber value="${t.articolo.vlunlt}" pattern="#,##0.00"/></td>
                            <td class="al"><span class="cart-stato" <c:if test="${!empty t.articolo.stato}">style="color:#fff; background-color: rgb(${t.articolo.stato.vist_rgb_r},${t.articolo.stato.vist_rgb_g},${t.articolo.stato.vist_rgb_b});"</c:if>>${fn:trim(t.articolo.cdartm)}</span></td>
                            <td class="ar"><strong><fmt:formatNumber value="${t.qtatot}" pattern="#,##0.##"/></strong>
                                <security:authorize ifNotGranted="ROLE_ANONYMOUS">
                                <c:if test="${t.articolo.fgpromo=='S' && !empty t.articolo.vist_offerte && (t.articolo.vist_offerte.zee_zef_teorico > 0 || t.articolo.vist_offerte.incentive > 0)}">
                                    <span class="offerta"><br/>(<c:choose><c:when test="${t.articolo.vist_offerte.zee_zef_teorico>0}"><fmt:formatNumber value="${t.articolo.vist_offerte.zee_zef_teorico}" pattern="#,###"/></c:when><c:when test="${t.articolo.vist_offerte.incentive>0}"><fmt:formatNumber value="${t.articolo.vist_offerte.incentive}" pattern="#,###"/></c:when></c:choose>)</span>
                                </c:if>
                                </security:authorize>
                            </td>
                            <security:authorize ifNotGranted="ROLE_ANONYMOUS">
                            <td class="ar"><c:choose><c:when test="${cookie.view_net_price.value == 'S' && !hideCond}"><fmt:formatNumber value="${t.impuninetto}" pattern="#,##0.00"/></c:when><c:when test="${cookie.view_net_price.value == 'N'}"><fmt:formatNumber value="${t.impuni}" pattern="#,##0.00"/></c:when></c:choose></td>
                            <td class="ar"><c:choose><c:when test="${cookie.view_net_price.value == 'S' && !hideCond}"><fmt:formatNumber value="${t.importonettoriga}" pattern="#,##0.00"/></c:when><c:when test="${cookie.view_net_price.value == 'N'}"><fmt:formatNumber value="${t.importoriga}" pattern="#,##0.00"/></c:when></c:choose></td>
                            <td class="ar">
                                <c:choose>
                                    <c:when test="${t.disp > 0}">
                                        <spring:message code="prox.disp" text="Disponibile"/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:if test="${!empty t.articolo && !empty t.articolo.mrp_file_giacenza && !empty t.articolo.mrp_file_giacenza.dtprdisp}">
                                            <fmt:formatDate value="${t.articolo.mrp_file_giacenza.dtprdisp}" pattern="dd/MM/yyyy"/>*
                                        </c:if>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            </security:authorize>
                            <td><a class="delete" title="${t.articolo.dsarti}" href="<c:url value='fullCart'><c:param name="azione" value="delete"/><c:param name="tkposi_del" value="${t.tkposi}"/></c:url>"><spring:message code="button.delete" text="Elimina"/></a></td>
                            <td><a class="modifica" title="${t.articolo.dsarti}" href="${schedaURL}"><spring:message code="button.edit" text="Modifica"/></a></td>
                        </tr>
                            <c:if test="${!empty t.articolo.eletDatiExtraMap && !empty t.articolo.eletDatiExtraMap['main']}">
                            <tr><td class="art-lamp" colspan="16">    
                            <ul>
                                    <li>
                                    <c:forEach items="${t.articolo.eletDatiExtraMap['main']}" var="lamp" varStatus="ilamp">
                                        <c:choose>
                                            <c:when test="${ilamp.first && !empty lamp['acro']}"><img src="<c:url value="/static${lamp['acro']}"/>" width="25px"/></c:when>
                                            <c:when test="${ilamp.first}"></c:when>
                                            <c:otherwise> + </c:otherwise>
                                        </c:choose>
                                        <c:if test="${!empty lamp['img']}"><img src="<c:url value="/static${lamp['img']}"/>" width="25px"/></c:if>
                                        <c:if test="${!empty lamp['descr']}"><span>${lamp['descr']}</span></c:if>
                                    </c:forEach>
                                    </li>
                                    <c:if test="${!empty t.articolo.eletDatiExtraMap['alt1']}">
                                        <li>
                                            <ul>
                                                <li>
                                                    <c:forEach items="${t.articolo.eletDatiExtraMap['alt1']}" var="lamp" varStatus="ilamp">
                                                        <c:choose>
                                                            <c:when test="${ilamp.first && !empty lamp['acro']}"><img src="<c:url value="/static${lamp['acro']}"/>" width="25px"/></c:when>
                                                            <c:when test="${ilamp.first}"></c:when>
                                                            <c:otherwise> + </c:otherwise>
                                                        </c:choose>
                                                        <c:if test="${!empty lamp['img']}"><img src="<c:url value="/static${lamp['img']}"/>" width="25px"/></c:if>
                                                        <c:if test="${!empty lamp['descr']}"><span>${lamp['descr']}</span></c:if>
                                                    </c:forEach>                                                    
                                                </li>
                                                <c:if test="${!empty t.articolo.eletDatiExtraMap['alt2']}">
                                                <li>
                                                    <c:forEach items="${t.articolo.eletDatiExtraMap['alt2']}" var="lamp" varStatus="ilamp">
                                                        <c:choose>
                                                            <c:when test="${ilamp.first && !empty lamp['acro']}"><img src="<c:url value="/static${lamp['acro']}"/>" width="25px"/></c:when>
                                                            <c:when test="${ilamp.first}"></c:when>
                                                            <c:otherwise> + </c:otherwise>
                                                        </c:choose>
                                                        <c:if test="${!empty lamp['img']}"><img src="<c:url value="/static${lamp['img']}"/>" width="25px"/></c:if>
                                                        <c:if test="${!empty lamp['descr']}"><span>${lamp['descr']}</span></c:if>
                                                    </c:forEach>                                                    
                                                </li>
                                                </c:if>
                                            </ul>
                                        </li>
                                    </c:if>
                                </ul>
                            </td></tr>
                            </c:if>
                            
                        <security:authorize ifNotGranted="ROLE_ANONYMOUS">
                        <c:if test="${s.last}">
                            <c:out value="${footerHead}" escapeXml="false"/>
                            <c:set var="rowType" value="${fgpromo=='S'?'Off':''}"/>
                            <jsp:directive.include file="/WEB-INF/jspf/_cartFooter.jspf"/>
                        </c:if>
                        </security:authorize>
                        </c:forEach>
                        
                        <security:authorize ifNotGranted="ROLE_ANONYMOUS">
                        <c:if test="${fgpromo=='S'}">
                            <tr class="riga" >
                                <td colspan="15"><h2><spring:message code="totale_car" text="Totale carrello"/></h2></td>
                            </tr>
                            <c:out value="${footerHeadTot}" escapeXml="false"/>
                            <c:set var="rowType" value="Car"/>                 
                            <jsp:directive.include file="/WEB-INF/jspf/_cartFooter.jspf"/>     
                        </c:if>
                            
                        <tr class="cart-tfoot-3">
                            <td colspan="5"><div><spring:message code="nota" text="Nota"/> <spring:message code="ordine" text="ordine"/>:</div><form:textarea path="ord_test.web_ord_test_note.testo" cols="40" rows="4"/></td>
                            <td colspan="2"><c:if test="${!empty promo}"><div><spring:message code="promo.code" text="Codice promozione"/>:</div><form:select path="ord_test.cdpromo_m">
                                    <form:option label="" value=""/>
                                    <form:options items="${promo}" itemLabel="dspromo" itemValue="cdpromo_m"/>
                            </form:select></c:if><%--form:input path="ord_test.cdpromo_m"/--%></td>
                            <td colspan="8">&nbsp;</td>
                        </tr>
                        
                        <c:set var="realCustomer" value="false"/>
                        <security:authorize ifAllGranted="ROLE_CLIE"><security:authentication var="realCustomer" property="principal.realCustomer"/></security:authorize> 
                        <c:if test="${!empty cart.condc}">
                            <c:if test="${realCustomer || cart.condcAccepted}">
                            <tr>
                                <td colspan="15">
                                    <form:checkbox path="condcAccepted" cssErrorClass="error" disabled="${cart.condcAccepted}"/> <a href="<c:url value="/conditions"/>" class="condc" target="_blank"><spring:message code="purchasing.conditions" text="Condizioni di vendita"/></a>
                                    <form:errors path="condcAccepted" cssClass="errors" element="p"/>
                                    </td>
                            </tr>
                            </c:if>
                            <c:if test="${!realCustomer && !cart.condcAccepted}">
                                <tr>
                                    <td colspan="15">
                                        <p class="errors"><spring:message code="purchasing.conditions.notAccepted" text="Condizioni di vendita non accettate"/></p>
                                    </td>
                                </tr>
                            </c:if>
                        </c:if>
                        
                        
                        
                        </security:authorize>
                        
                        <tr>
                            <td colspan="<security:authorize ifNotGranted="ROLE_ANONYMOUS">15</security:authorize><security:authorize ifAllGranted="ROLE_ANONYMOUS">12</security:authorize>" nowrap="nowrap"><p>&nbsp;</p><div class="left"><div class="button left"><a href="javascript:history.back()" id="backBtn"><span><em><fmt:message key="back"/></em></span></a></div></div><div class="right"><security:authorize ifNotGranted="ROLE_ANONYMOUS"><div class="button left"><a href="javascript:void(0)" id="saveBtn" class="savebt"><span><em><fmt:message key="button.save.cart"/></em></span></a></div>
                                        <div class="button left"><a id="goonBtn" class="goonbt" href="<c:url value="catalogo_dest_merce.jsp"/>"><span><em><fmt:message key="button.complete.cart"/></em></span></a></div></security:authorize><c:if test="${!empty cart.ord_posititos}"><security:authorize ifAllGranted="ROLE_ANONYMOUS"><div class="button left"><a id="sendWl" class="sendwl" href="<c:url value="wishlist"/>" rel="lightbox[external 500 280]"><span><em><spring:message code="button.wishlist.send" text="Invia wishlist"/></em></span></a></div></security:authorize></c:if></div></td>
                      </tr>
                    </tbody>
                </table>
        <script type="text/javascript">
            window.addEvent('domready', function(){

                $$('.savebt').addEvent('click', function(e){
                    $('carrelloForm').submit();
                });

                <c:choose>
                        <c:when test="${(empty cookie.view_net_price || cookie.view_net_price.value == 'N')&& !hideCond }">
                        $$('.goonbt').addEvent('click', function(e){
                            e.stop();

                            StickyWin.confirm("<spring:message code="display.price" text="Visualizzazione prezzi"/>",
                                            "<spring:message code="display.price.disclaimer" text="Per procedere alla conclusione dell'ordine è necessario attivare la visualizzazione completa delle condizioni di vendita.<br/>Attivarle?"/>",
                                            function(){
                                                Cookie.write('view_net_price', 'S', {duration: 14, path: '<c:url value="/"/>'});
                                                /*var uri = new URI('<c:url value="fullCart"/>');
                                                uri.go();*/
                                                //$('gotodest').set('value', 'true');
                                                $('carrelloForm').submit();

                                                },
                                                {
                                                uiOptions: {
                                                    width: 450
                                                },
                                                position: 'center',
                                                offset: {x:0,y:-($(window).getScroll().y)}
                                                }

                                            );
                        });
                        </c:when>
                    <c:otherwise>
                        $$('.goonbt').addEvent('click', function(e){
                            e.stop();
                            $('gotodest').set('value', 'true');
                            $('carrelloForm').submit();
                        });
                    </c:otherwise>
                </c:choose>
                $('cart-cnt').getElements('a.delete').each( function( element ){

                  element.addEvent('click', function(e){
                        e.stop();
                        var disclaimer = '<spring:message code="confirm.delete.disclaimer"/>';
                        var myObject = {title: element.get('title')};
                        disclaimer = disclaimer.substitute(myObject);
                        StickyWin.confirm("<spring:message code="confirm.delete" text="Conferma cancellazione"/>",
                                          disclaimer,
                                          function(){
                                               var uri = new URI(element.get('href'));
                                               uri.go();
                                            },
                                            {
                                            uiOptions: {
                                                width: 450
                                            },
                                                position: 'center',
                                                offset: {x:0,y:-($(window).getScroll().y)}
                                            }

                                         );


                    });

                });

           });

        </script>
            </form:form>
        </c:when>
        <c:otherwise>
            <h2><spring:message code="cart" text="Carrello"/></h2>
            <h3><spring:message code="no.item.cart" text="Non ci sono articoli in carrello"/>.</h3>
        </c:otherwise>
    </c:choose>
</div>
<script type="text/javascript">
    window.addEvent('domready', function(){

        var uri = '<c:url value="/carrelloItem-{cdarti}/"/>';
        
        var allDownloadLink = $('cart-cnt').getElements('a.downloadlink').each(function(item, index){
            item.addEvent('click', function(e){
                e.stop();

                Mediabox.open(item.get('href'), '<spring:message code="title.downloads" text="Downloads"/>', '640 360');
            });
        });  
        
        $('cart-cnt').getElements('a.downloadreqlink').each(function(item, index){
                                                item.addEvent('click', function(e){
                                                    e.stop();

                                                    var dataUrl = item.get('href').split('?');
                                                    var _u = dataUrl[0];
                                                    var _q = dataUrl[1];
                                                    var jsonData = _q.cleanQueryString().parseQueryString();
                                                    
                                                    var jsonRequest = new Request.JSON({url: _u, onSuccess: function(data){
                                                        if(data.rc=='1') alert(data.message);
                                                    }}).post(jsonData);
                                                });
                                            });     
                                            
        $('cart-cnt').getElements('a.downloadproxylink').each(function(item, index){
                                                item.addEvent('click', function(e){
                                                    e.stop();
                                                    $('iframe_open_file').src = '';
                                                    
                                                    var dataUrl = item.get('data-purl').split('?');
                                                    var _u = dataUrl[0];
                                                    var _q = dataUrl[1];
                                                    var jsonData = _q.cleanQueryString().parseQueryString();
                                                    
                                                    var jsonRequest = new Request.JSON({url: _u, onSuccess: function(data){
                                                            if(data.rc=='1' && data.il_token > 0) {
                                                                $('iframe_open_file').src = item.get('href') + '&tkc='+data.il_token;
                                                            }
                                                        },
                                                        onFailure:function(xhr){
                                                            //console.log('fail');
                                                        }
                                                    }).post(jsonData);
                                                });
                                            });                                            
        
    <security:authorize ifNotGranted="ROLE_ANONYMOUS">
        $('fast-cart-item').set('load', {onSuccess: function(res){
                //$('fast-cart-item').addClass('clearfix');
                //$('load-cart-item').show();
                $('fast-cart-cnt').show();
                $('cdds').set('value','');
            }
        });
        

      var omniAuto = new Autocompleter.Request.HTML($('cdds'), '<c:url value="/search/articoliAutoComp.do"/>', {
            postVar: 'omni',
            'indicatorClass': 'autocompleter-loading',
            //minLength: 3,
            ajaxOptions: {method: 'get'},
            markQuery: true,
            width: '440px',
            //selectMode: 'pick',
            autoTrim: true,
            cache: false,
            'injectChoice': function(choice) {
                var text = choice.getFirst().getElement('div.fieldsearch');
                var value = text.innerHTML;
                choice.inputValue = value;
                text.set('html', this.markQueryValue(value));
                this.addChoiceEvents(choice);
            },
            onSelect: function(a,b,c){
                this.cdarti = b.get('id');
                this.dsarti = b.getElement('div.fieldsearch').get('html');
            },
            onHide: function(a,b){
                if(this.cdarti!=null){
                    $('load-cart-item').set('html', this.dsarti);
                    $('fast-cart-item').load(uri.substitute({'cdarti': this.cdarti}));
                    this.cdarti = null;
                    a.set('value', '');
                }
            },
            onFocus: function(a){
                this.cdarti = null;
                this.choices.empty();
            },
            onBlur: function(a){

                if(/*resetOnBlur &&*/ a.get('value')==''){
                    this.cdarti = null;
                    //a.set('value', '');
                }
            },
            onComplete: function(a,b,c,d){

                switch(b.status) {

                  case 204:
                    this.cdarti = null;
                    $('load-cart-item').empty();
                    $('fast-cart-item').empty();
                    $('fast-cart-cnt').dissolve();
                  break; //si ferma qui

                  case 400:
                    //TODO
                  break;
                  default:
                }
            }

            });

</security:authorize>

        <%--

        $('searchBtn').addEvent('click', function(e){
            e.stop();
            //verifica se cdartm esiste e rictorna cdarti interno
            var myRequest = new Request.JSON({method: 'post',
                        url: 'checkCdartm.json',
                        onRequest: function(){
                            //$('scheda-col-dx').addClass('preloader');
                            //schWaiter.show();
                        },
                        onSuccess: function(res){

                            if(res.error){
                                var msg = new Message({
                                        iconPath: '<c:url value="/static/images/"/>',
                                        icon: "database_alert.png",
                                        iconSize: 48,
                                        title: "Not found!",
                                        message: "<fmt:message key="errors.notFound"><fmt:param><fmt:message key="art.cdartm"/></fmt:param></fmt:message>",
                                        centered: true
                                }).say();
                            }else{
                                if(res.articolo){
                                    $('fast-cart-item').load(uri.substitute({'cdarti': res.articolo.cdarti}));
                                }
                            }

                        },
                        onFailure: function(res){

                            var msg = new Message({
                                    iconPath: '<c:url value="/static/images/"/>',
                                    icon: "database_alert.png",
                                    iconSize: 48,
                                    title: "Not found!",
                                    message: "<fmt:message key="errors.notFound"><fmt:param><fmt:message key="art.cdartm"/></fmt:param></fmt:message>",
                                    centered: true
                            }).tell();

                        }
            }).get({cdartm:$('cdartm').get('value')});
});--%>




    });
</script>
<security:authorize ifAllGranted="ROLE_CLIE">    
<div class="foot-note">(*) <spring:message code="articolo.dtprdisp_nota"/></div>
</security:authorize>
<jsp:directive.include file="/WEB-INF/jsp/footer.jsp"/>
