<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<security:authorize ifAllGranted="ROLE_CLIE">
<div id="head-user">
        <% if (request.getUserPrincipal() != null) {%>
            <div class="button right logout"><span><em><a href="<c:url value='/static/j_spring_security_logout' />"><spring:message code="logout" text="Logout"/></a></em></span></div>
        <% }else{%>    
            <div class="button right logout"><span><em><a href="<c:url value='/login' />"><spring:message code="login" text="MyVistosi"/></a></em></span></div>
        <% }%>
        <security:authorize ifAnyGranted="ROLE_ADMIN, ROLE_CAPO, ROLE_AGEN,ROLE_ISPE,ROLE_GEST">
        <div id="user"> <spring:message code="cliente" text="Cliente"/>: <a href="<c:url value="/changeClie"/>" title="<spring:message code="click.change.cliente" text="Clicca per cambiare cliente"/>"><security:authentication property="principal.cliente.archenti.ragcog"/></a> / <security:authorize ifNotGranted="ROLE_ANONYMOUS"><spring:message code="user" text="Utente"/>: <strong><security:authentication property="principal.userDB.dsutente"/></strong></security:authorize>
        </div>
        </security:authorize>
</div>
</security:authorize>   

<% if (request.getUserPrincipal() == null) {%> 
    <div class="button right logout"><span><em><a href="<c:url value='/login' />"><spring:message code="login" text="Login"/></a></em></span></div>
<% }%>  

<div id="head-logo-cart" class="clearfix">
    <div id="logo">
        <a href="https://${fn:replace(pageContext.request.serverName, 'portal', 'www')}"/>">vistosi</a>
    </div>    
    
        <div id="cart" <security:authorize ifAllGranted="ROLE_ANONYMOUS">class="wishlist"</security:authorize>>
        <div id="carrello-det" class="clearfix">
            <security:authorize ifAllGranted="ROLE_CLIE">
                <spring:message code="cart" text="Carrello"/>
            </security:authorize>
            <security:authorize ifAllGranted="ROLE_ANONYMOUS">
                <spring:message code="wishlist" text="Wishlist"/>
            </security:authorize>
            </div>

        <c:if test="${!disableMiniCart}">
            <script type="text/javascript">
                window.addEvent('domready', function(){
                    var uri = '<c:url value="/miniCart"/>';
                    $('carrello-det').empty().load(uri);
                });
            </script>
        </c:if>

    </div>   
</div>
<security:authorize ifNotGranted="ROLE_ANONYMOUS">
<div id="btn_menu" class="clearfix">

    <c:set var="useSpeclist" value="false"/>
    <security:authorize ifAllGranted="ROLE_CLIE"><security:authentication var="useSpeclist" property="principal.isSpecList"/></security:authorize>        
    <ul>
        <li class="bt_home"><a href="<c:url value="/index"/>"><spring:message code="menu.home" text="Home"/></a></li>
        <li class="bt_speciali"><a href="/portal/epCat_comgen_art_spec_edit.jsp" rel="popup[800,500,r]"><spring:message code="menu.artspeciali" text="Richiesta articoli speciali"/></a></li>
        <%--li class="bt_offerte"><a href="<c:choose><c:when test="${useSpeclist}"><c:url value="/static/offerte/CONDIZ_VENDITA_2012_ART_OFFERTA_USA.pdf"/></c:when><c:otherwise><c:url value="/static/offerte/CONDIZ_VENDITA_2012_ART_OFFERTA_EURO.pdf"/></c:otherwise></c:choose>" target="_blank"><spring:message code="menu.offerte" text="Offerte"/></a></li--%>
        <%--li class="bt_promo"><a href="<c:url value="/promo"/>"><spring:message code="menu.promozioni" text="Promozioni"/></a></li--%>
        <%--li class="bt_cond"><a href="<c:url value="/conditions"/>"><spring:message code="purchasing.conditions" text="Condizioni di acquisto"/></a></li--%>
        <li class="bt_blocco_ricerca">
            <div class="form_menu">
                <form id="searchForm2" name="searchForm2" action="<c:url value='/findArticoli'/>">
                    <ul>
                        <li class="bt_ricerca input_avanzata" ><a href="<c:url value="/findArticoli"/>"><spring:message code="menu.ricerca" text="Cerca"/></a></li>
                        <li class="input_search"> <input class="search_hp menu_search" type="text" id="descr" name="descr" autocomplete="off"/> <input type="hidden" name="cdvisttp" value="${rootFilter}" /> <input type="hidden" name="dsvistccol" value="${coll}" /> <input type="hidden" name="cdvistfam" value="${fam}" /> </li>
                        <li class="btn_search"><a href="javascript:void(0)" id="searchBtn2"><img src="<c:url value='/static/images/icon_cerca.png' />" alt="Cerca" class="img_search" /></a></li>
                    </ul>
                </form>
            </div>
        </li>
        <%--li class="bt_portal"><a href="/portal">Area riservata</a></li--%>
    </ul>
</div>
    
    <script type="text/javascript">
        $('searchBtn2').addEvent('click', function(e){
            $('searchForm2').submit();
        });

    </script>
</security:authorize>   


<div id="shop-menu-wrapper">
    <div id="vshop_menu">
        <ul>
            <li id="m-tipologia" class="ddmenu<c:if test="${disableMenu}"> disabled </c:if>">
                <c:set var="dstp">dsextvisttp${atkLangSfx}</c:set>


                <fmt:message key="select.type" var="defSelType"/>
                <fmt:message key="select.family" var="defSelFam"/>
                <fmt:message key="select.collection" var="defSelCol"/>
                <h2><spring:message code="typology" text="Tipologia"/></h2>
                <c:if test="${!disableMenu}">
                    <div class="list-cnt">
                        <ul>
                            <c:forEach items="${tipologie}" var="t" varStatus="s">
                                <li class="menu_item<c:if test='${t.cdvisttp==tipologiaFilter.cdvisttp  && !plainMenu}'> active</c:if>">

                                    <c:choose>
                                        <c:when test="${!empty scheda}">
                                            <spring:url var="baseURL" value="/scheda-{dsvistfam}-{cdvisttp}/{cdvistfam}/">
                                                <spring:param name="cdvisttp" value="${t.cdvisttp}" />
                                                <spring:param name="cdvistfam" value="${famigliaFilter.cdvistfam}" />
                                                <spring:param name="dsvistfam" value="${famigliaFilter.dsvistfam}" />
                                            </spring:url>
                                        </c:when>
                                        <c:otherwise>
                                            <c:set var="URItpl" value="/search"/>
                                            <c:set var="URItpl" value="${URItpl}/tipologia/{cdvisttp}"/>
                                            <c:if test="${!empty collezioneFilter && !plainMenu}"><c:set var="URItpl" value="${URItpl}/collezione/{dsvistccol}"/></c:if>
                                            <c:if test="${!empty famigliaFilter && !plainMenu}"><c:set var="URItpl" value="${URItpl}/famiglia/{cdvistfam}"/></c:if>
                                            <spring:url var="baseURL" value="${URItpl}/">
                                                <spring:param name="cdvisttp" value="${t.cdvisttp}" />
                                                <c:if test="${!empty collezioneFilter && !plainMenu}"><spring:param name="dsvistccol" value="${collezioneFilter}" /></c:if>
                                                <c:if test="${!empty famigliaFilter && !plainMenu}"><spring:param name="cdvistfam" value="${famigliaFilter.cdvistfam}" /></c:if>
                                                <c:if test="${!empty finituraFilter}"><spring:param name="cdvistfinm" value="${finituraFilter.cdvistfinm}" /></c:if>
                                                <c:if test="${!empty coloreFilter}"><spring:param name="cdvistcolv" value="${coloreFilter.cdvistcolv}" /></c:if>                                                
                                                <c:if test="${!empty finvetroFilter}"><spring:param name="cdvistfinv" value="${finvetroFilter.cdvistfinv}" /></c:if>                                                
                                            </spring:url>                                         
                                        </c:otherwise>
                                    </c:choose>

                                    <a href="${baseURL}">${fn:toLowerCase(t[dstp])}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:if>
            </li>
            <li id="m-collezione" class="ddmenu<c:if test="${disableMenu}"> disabled </c:if>">
                <c:set var="dsvistcol">dsvistccol</c:set>
                <c:if test="${rc.locale.language!='it'}"><c:set var="dsvistcol">dsvistccol_${rc.locale.language}</c:set></c:if>
                <h2><spring:message code="collection" text="Collezione"/></h2>
                <c:set var="prevcoll" value=""/>
                <c:forEach items="${collezioni}" var="t" varStatus="s">
                    <c:set var="currcoll">${(!empty t[dsvistcol])?t[dsvistcol]:t.dsvistccol}</c:set>
                    <c:if test="${currcoll!=prevcoll}">
                        <c:if test="${s.first}">
                        <div class="list-cnt">
                            <ul></c:if>
                                <li class="menu_item">
                                <c:set var="URItpl" value="/search"/>
                                <c:if test="${!empty tipologiaFilter && !plainMenu}"><c:set var="URItpl" value="${URItpl}/tipologia/{cdvisttp}"/></c:if>
                                <c:set var="URItpl" value="${URItpl}/collezione/{dsvistccol}"/>
                                <c:if test="${!empty famigliaFilter && !plainMenu}"><c:set var="URItpl" value="${URItpl}/famiglia/{cdvistfam}"/></c:if>
                                <spring:url var="baseURL" value="${URItpl}/">
                                    <c:if test="${!empty tipologiaFilter && !plainMenu}"><spring:param name="cdvisttp" value="${tipologiaFilter.cdvisttp}" /></c:if>
                                    <spring:param name="dsvistccol" value="${t.dsvistccol}" />
                                    <c:if test="${!empty famigliaFilter && !plainMenu}"><spring:param name="cdvistfam" value="${famigliaFilter.cdvistfam}" /></c:if>
                                    <c:if test="${!empty finituraFilter}"><spring:param name="cdvistfinm" value="${finituraFilter.cdvistfinm}" /></c:if>
                                    <c:if test="${!empty coloreFilter}"><spring:param name="cdvistcolv" value="${coloreFilter.cdvistcolv}" /></c:if>
                                    <c:if test="${!empty finvetroFilter}"><spring:param name="cdvistfinv" value="${finvetroFilter.cdvistfinv}" /></c:if>
                                </spring:url>                              
                                <a href="${baseURL}">${fn:toLowerCase(currcoll)}</a>
                            </li>
                            <c:set var="prevcoll" value="${currcoll}"/>
                            </c:if>
                            <c:if test="${s.last}"></ul></div></c:if>
                        </c:forEach>
            </li>        
            <li id="m-famiglia" class="ddmenu<c:if test="${disableMenu}"> disabled </c:if>">
                <c:set var="dsfam">dsvistfam${atkLangSfx}</c:set>

                <h2><spring:message code="family" text="Famiglia"/></h2>
                
                <c:if test="${!disableMenu}"><div class="mfamiglia-cnt">
                    <c:set var="colnum" value="0"/>
                    <c:set var="numfamiglie" value="${fn:length(famiglie)}"/>
                    <c:set var="blockColumn"><fmt:formatNumber pattern="##" value="${numfamiglie/8}"/></c:set>
                    <c:set var="blockColumn" value="${blockColumn+1}"/>
                    <c:forEach items="${famiglie}" var="f" varStatus="s">
                        <c:if test='${s.index%blockColumn == 0}'><c:set var="colnum" value="${colnum+1}"/><c:choose><c:when test="${s.first}"><div class="list-cnt"></c:when><c:otherwise></ul></div></c:otherwise></c:choose>
                <div class="list-wrapper"><ul></c:if>
                    <li class="column${colnum} menu_item <c:if test='${s.index%blockColumn == 0}'>coltopper</c:if><c:if test='${f.cdvistfam==famigliaFilter.cdvistfam}'> active</c:if>" >               
                        <c:set var="URItpl" value="/search"/>
                        <c:if test="${!empty tipologiaFilter && !plainMenu}"><c:set var="URItpl" value="${URItpl}/tipologia/{cdvisttp}"/></c:if>
                        <c:if test="${!empty collezioneFilter && !plainMenu}"><c:set var="URItpl" value="${URItpl}/collezione/{dsvistccol}"/></c:if>
                        <spring:url var="baseURL" value="${URItpl}/famiglia/{cdvistfam}/">
                            <c:if test="${!empty tipologiaFilter && !plainMenu}"><spring:param name="cdvisttp" value="${tipologiaFilter.cdvisttp}" /></c:if>
                            <c:if test="${!empty collezioneFilter && !plainMenu}"><spring:param name="dsvistccol" value="${collezioneFilter}" /></c:if>
                            <spring:param name="cdvistfam" value="${f.cdvistfam}" />
                            <c:if test="${!empty finituraFilter}"><spring:param name="cdvistfinm" value="${finituraFilter.cdvistfinm}" /></c:if>
                            <c:if test="${!empty coloreFilter}"><spring:param name="cdvistcolv" value="${coloreFilter.cdvistcolv}" /></c:if>
                            <c:if test="${!empty finvetroFilter}"><spring:param name="cdvistfinv" value="${finvetroFilter.cdvistfinv}" /></c:if>
                        </spring:url>                   

                        <a href="${baseURL}" <c:if test="${!empty f.stato}">style="color: rgb(${f.stato.vist_rgb_r},${f.stato.vist_rgb_g},${f.stato.vist_rgb_b});"</c:if>>${ep:capitalizeFirst(fn:toLowerCase(!empty(f[dsfam])?f[dsfam]:f['dsvistfam_eng']))}</a>
                    </li>
                    <c:if test='${s.last}'></ul></div></div>
                    </div>
                    </c:if>
                </c:forEach>
            </c:if>
</li>

<li id="m-elettrificazione" class="ddmenu <c:if test="${empty elettrificazioni}">disabled</c:if>">
    <c:set var="dselet">dsextvistelet${atkLangSfx}</c:set>
    <h2><spring:message code="elettr" text="Elettrificazione"/></h2>
    <c:forEach items="${elettrificazioni}" var="t" varStatus="s">
        <c:if test="${s.first}">
            <div class="list-cnt">
                <ul></c:if>
                <li  class="menu_item<c:if test='${t.cdvistelet==eletFilter.cdvistelet}'> active</c:if>">
                    <c:set var="URItpl" value="/search"/>
                    <c:if test="${!empty tipologiaFilter && !plainMenu}"><c:set var="URItpl" value="${URItpl}/tipologia/{cdvisttp}"/></c:if>
                    <c:if test="${!empty collezioneFilter && !plainMenu}"><c:set var="URItpl" value="${URItpl}/collezione/{dsvistccol}"/></c:if>
                    <c:if test="${!empty famigliaFilter && !plainMenu}"><c:set var="URItpl" value="${URItpl}/famiglia/{cdvistfam}"/></c:if>
                     <spring:url var="baseURL" value="${URItpl}/">
                        <c:if test="${!empty tipologiaFilter && !plainMenu}"><spring:param name="cdvisttp" value="${tipologiaFilter.cdvisttp}" /></c:if>
                        <c:if test="${!empty collezioneFilter && !plainMenu}"><spring:param name="dsvistccol" value="${collezioneFilter}" /></c:if>
                        <c:if test="${!empty famigliaFilter && !plainMenu}"><spring:param name="cdvistfam" value="${famigliaFilter.cdvistfam}" /></c:if>
                        <spring:param name="cdvistelet" value="${t.cdvistelet}" />
                        <c:if test="${!empty eletFilter}"><spring:param name="cdvistelet" value="${eletFilter.cdvistelet}" /></c:if>
                     </spring:url>
                        
                    <a href="${baseURL}">${fn:toLowerCase(t[dselet])}</a>
                </li>
                <c:if test="${s.last}">
                </ul></div></c:if>
        </c:forEach>
</li>

<li id="m-tonalita" class="ddmenu <c:if test="${empty colori}">disabled</c:if>">
    <c:set var="dscol">dsextvistvetro${atkLangSfx}</c:set>
    <c:set var="dscolv">dsextvistcolv${atkLangSfx}</c:set>
    <c:set var="dsfinv">dsextvistfinv${atkLangSfx}</c:set>
    <fmt:message key="col.vetro" var="defColVetro"/>
    <fmt:message key="fin.vetro" var="defFinVetro"/>
    <%--spring:url var="clearColURL" value="${clearURL}">
        <c:if test="${!empty tipologiaFilter}"><spring:param name="cdvisttp" value="${tipologiaFilter.cdvisttp}" /></c:if>
        <c:if test="${!empty famigliaFilter}"><spring:param name="cdvistfam" value="${famigliaFilter.cdvistfam}" /></c:if>
        <c:if test="${!empty param.dsvistccol}"><spring:param name="dsvistccol" value="${param.dsvistccol}" /></c:if>
        <c:if test="${!empty param.cdvistfinm}"><spring:param name="cdvistfinm" value="${param.cdvistfinm}" /></c:if>
    </spring:url--%>
    <h2><spring:message code="vetro" text="vetro"/><%--c:if test="${!empty coloreFilter}"><a href="${clearColURL}" class="clear-filter">x</a></c:if><c:out value="${coloreFilter[dscol]}" default="${defColVetro}" escapeXml="false"/--%></h2>
    <c:forEach items="${colori}" var="t" varStatus="s">
        <c:if test="${s.first}">
            <div class="list-cnt">
                <ul></c:if>
                <li class="menu_item<c:if test='${t.cdvistvetro==coloreFilter.cdvistcolv}'> active</c:if>">
                    <c:set var="URItpl" value="/search"/>
                    <c:if test="${!empty tipologiaFilter && !plainMenu}"><c:set var="URItpl" value="${URItpl}/tipologia/{cdvisttp}"/></c:if>
                    <c:if test="${!empty collezioneFilter && !plainMenu}"><c:set var="URItpl" value="${URItpl}/collezione/{dsvistccol}"/></c:if>
                    <c:if test="${!empty famigliaFilter && !plainMenu}"><c:set var="URItpl" value="${URItpl}/famiglia/{cdvistfam}"/></c:if>
                    <spring:url var="baseURL" value="${URItpl}/">
                        <c:if test="${!empty tipologiaFilter && !plainMenu}"><spring:param name="cdvisttp" value="${tipologiaFilter.cdvisttp}" /></c:if>
                        <c:if test="${!empty collezioneFilter && !plainMenu}"><spring:param name="dsvistccol" value="${collezioneFilter}" /></c:if>
                        <c:if test="${!empty famigliaFilter && !plainMenu}"><spring:param name="cdvistfam" value="${famigliaFilter.cdvistfam}" /></c:if>
                        <spring:param name="cdvistcolv" value="${t.col.cdvistcolv}" />
                        <spring:param name="cdvistfinv" value="${t.fin.cdvistfinv}" />
                        <c:if test="${!empty finituraFilter}"><spring:param name="cdvistfinm" value="${finituraFilter.cdvistfinm}" /></c:if>
                    </spring:url>                       
                    <a href="${baseURL}">${fn:toLowerCase(t[dscol])}</a>
                </li>
                <c:if test="${s.last}">
                </ul></div></c:if>
        </c:forEach>
</li>
<li id="m-partmet" class="ddmenu <c:if test="${empty partmet}">disabled</c:if>">
    <c:set var="dsfin">dsextvistfinm${atkLangSfx}</c:set>
    <spring:url var="clearFinURL" value="${clearURL}">
        <spring:param name="cdvisttp" value="${tipologiaFilter.cdvisttp}" />
        <c:if test="${!empty famigliaFilter}"><spring:param name="cdvistfam" value="${famigliaFilter.cdvistfam}" /></c:if>
        <c:if test="${!empty param.cdvistcolv}"><spring:param name="cdvistcolv" value="${param.cdvistcolv}" /></c:if>
        <c:if test="${!empty param.dsvistccol}"><spring:param name="dsvistccol" value="${param.dsvistccol}" /></c:if>
    </spring:url>
    <fmt:message key="fin.mont" var="defFinMont"/>
    <h2>${defFinMont}<%--c:if test="${!empty finituraFilter}"><a href="${clearFinURL}" class="clear-filter">x</a></c:if><c:out value="${finituraFilter[dsfin]}" default="${defFinMont}"/--%></h2>
    <c:forEach items="${partmet}" var="t" varStatus="s">
        <c:if test="${s.first}">
            <div class="list-cnt">
                <ul></c:if>
                <li class="menu_item<c:if test='${t.cdvistfinm==finituraFilter.cdvistfinm}'> active</c:if>">
                    <%--spring:url var="baseURL" value="${clearURL}">
                        <spring:param name="page" value="0" />
                        <c:if test="${!empty tipologiaFilter}"><spring:param name="cdvisttp" value="${tipologiaFilter.cdvisttp}" /></c:if>
                        <c:if test="${!empty famigliaFilter}"><spring:param name="cdvistfam" value="${famigliaFilter.cdvistfam}" /></c:if>
                        <c:if test="${!empty param.cdvistcolv}"><spring:param name="cdvistcolv" value="${param.cdvistcolv}" /></c:if>
                        <c:if test="${!empty param.dsvistccol}"><spring:param name="dsvistccol" value="${param.dsvistccol}" /></c:if>
                        <spring:param name="cdvistfinm" value="${t.cdvistfinm}" />
                    </spring:url--%>
                    <c:set var="URItpl" value="/search"/>
                    <c:if test="${!empty tipologiaFilter && !plainMenu}"><c:set var="URItpl" value="${URItpl}/tipologia/{cdvisttp}"/></c:if>
                    <c:if test="${!empty collezioneFilter && !plainMenu}"><c:set var="URItpl" value="${URItpl}/collezione/{dsvistccol}"/></c:if>
                    <c:if test="${!empty famigliaFilter && !plainMenu}"><c:set var="URItpl" value="${URItpl}/famiglia/{cdvistfam}"/></c:if>
                    <spring:url var="baseURL" value="${URItpl}/">
                        <c:if test="${!empty tipologiaFilter && !plainMenu}"><spring:param name="cdvisttp" value="${tipologiaFilter.cdvisttp}" /></c:if>
                        <c:if test="${!empty collezioneFilter && !plainMenu}"><spring:param name="dsvistccol" value="${collezioneFilter}" /></c:if>
                        <c:if test="${!empty famigliaFilter && !plainMenu}"><spring:param name="cdvistfam" value="${famigliaFilter.cdvistfam}" /></c:if>
                        <spring:param name="cdvistfinm" value="${t.cdvistfinm}" />
                        <c:if test="${!empty coloreFilter}"><spring:param name="cdvistcolv" value="${coloreFilter.cdvistcolv}" /></c:if>
                        <c:if test="${!empty finvetroFilter}"><spring:param name="cdvistfinv" value="${finvetroFilter.cdvistfinv}" /></c:if>
                    </spring:url>                     
                    <a href="${baseURL}">${fn:toLowerCase(t[dsfin])}</a>
                </li>
                <c:if test="${s.last}">
                </ul></div></c:if>
        </c:forEach>
</li>
</ul>
</div>
</div>
<c:if test="${!hideFilter}">
    <div id="vshop_menu_filters">
        <table>
            <tr>
                <td class="m-tipologia head"><spring:message code="filter.active" text="Filtri attivi"/>:</td>
                <td class="m-collezione"></td>
                <td class="m-famiglia"></td>
                <td class="m-elettrificazione"></td>
                <td class="m-colvetro"></td>
                <td class="m-finmont"></td>
            </tr>
            <tr>
                <td class="m-tipologia"><c:if test="${!empty tipologiaFilter && !plainMenu}">
                        <c:set var="clearURL" value="/search"/>
                        <c:if test="${!empty collezioneFilter && !plainMenu}"><c:set var="clearURL" value="${clearURL}/collezione/{dsvistccol}"/></c:if>
                        <c:if test="${!empty famigliaFilter && !plainMenu}"><c:set var="clearURL" value="${clearURL}/famiglia/{cdvistfam}"/></c:if>
                        <spring:url var="clearURL" value="${clearURL}/">
                            <c:if test="${!empty collezioneFilter && !plainMenu}"><spring:param name="dsvistccol" value="${collezioneFilter}" /></c:if>
                            <c:if test="${!empty famigliaFilter && !plainMenu}"><spring:param name="cdvistfam" value="${famigliaFilter.cdvistfam}" /></c:if>
                            <c:if test="${!empty finituraFilter}"><spring:param name="cdvistfinm" value="${finituraFilter.cdvistfinm}" /></c:if>
                            <c:if test="${!empty coloreFilter}"><spring:param name="cdvistcolv" value="${coloreFilter.cdvistcolv}" /></c:if>
                            <c:if test="${!empty finvetroFilter}"><spring:param name="cdvistfinv" value="${finvetroFilter.cdvistfinv}" /></c:if>
                        </spring:url>                       
                        <a href="${clearURL}" class="delete-filter"><c:out value="${fn:toLowerCase(tipologiaFilter[dstp])}"/></a></c:if></td>
                <td class="m-collezione"><c:if test="${!empty collezioneFilter && !plainMenu}">
                        <c:set var="clearURL" value="/search"/>
                        <c:if test="${!empty tipologiaFilter && !plainMenu}"><c:set var="clearURL" value="${clearURL}/tipologia/{cdvisttp}"/></c:if>
                        <c:if test="${!empty famigliaFilter && !plainMenu}"><c:set var="clearURL" value="${clearURL}/famiglia/{cdvistfam}"/></c:if>
                        <spring:url var="clearURL" value="${clearURL}/">
                            <c:if test="${!empty tipologiaFilter && !plainMenu}"><spring:param name="cdvisttp" value="${tipologiaFilter.cdvisttp}" /></c:if>
                            <c:if test="${!empty famigliaFilter && !plainMenu}"><spring:param name="cdvistfam" value="${famigliaFilter.cdvistfam}" /></c:if>
                            <c:if test="${!empty finituraFilter}"><spring:param name="cdvistfinm" value="${finituraFilter.cdvistfinm}" /></c:if>
                            <c:if test="${!empty coloreFilter}"><spring:param name="cdvistcolv" value="${coloreFilter.cdvistcolv}" /></c:if>
                            <c:if test="${!empty finvetroFilter}"><spring:param name="cdvistfinv" value="${finvetroFilter.cdvistfinv}" /></c:if>
                        </spring:url>                     
                        <a href="${clearURL}" class="delete-filter"><c:out value="${fn:toLowerCase(collezioneFilter)}"/></a></c:if></td>
                <td class="m-famiglia"><c:if test="${!empty famigliaFilter && !plainMenu}">
                        <c:set var="clearURL" value="/search"/>
                        <c:if test="${!empty tipologiaFilter && !plainMenu}"><c:set var="clearURL" value="${clearURL}/tipologia/{cdvisttp}"/></c:if>
                        <c:if test="${!empty collezioneFilter && !plainMenu}"><c:set var="clearURL" value="${clearURL}/collezione/{dsvistccol}"/></c:if>
                        <spring:url var="clearURL" value="${clearURL}/">
                            <c:if test="${!empty tipologiaFilter && !plainMenu}"><spring:param name="cdvisttp" value="${tipologiaFilter.cdvisttp}" /></c:if>
                            <c:if test="${!empty collezioneFilter && !plainMenu}"><spring:param name="dsvistccol" value="${collezioneFilter}" /></c:if>
                            <c:if test="${!empty finituraFilter}"><spring:param name="cdvistfinm" value="${finituraFilter.cdvistfinm}" /></c:if>
                            <c:if test="${!empty coloreFilter}"><spring:param name="cdvistcolv" value="${coloreFilter.cdvistcolv}" /></c:if>
                            <c:if test="${!empty finvetroFilter}"><spring:param name="cdvistfinv" value="${finvetroFilter.cdvistfinv}" /></c:if>
                        </spring:url>  
                        <a href="${clearURL}" class="delete-filter"><c:out value="${fn:toLowerCase(!empty famigliaFilter[dsfam]?famigliaFilter[dsfam]:famigliaFilter['dsvistfam_eng'])}"/></a></c:if></td>
                    <td class="m-elettrificazione"><c:if test="${!empty eletFilter && !plainMenu}">
                    <c:set var="clearURL" value="/search"/>
                        <c:if test="${!empty tipologiaFilter && !plainMenu}"><c:set var="clearURL" value="${clearURL}/tipologia/{cdvisttp}"/></c:if>
                        <c:if test="${!empty collezioneFilter && !plainMenu}"><c:set var="clearURL" value="${clearURL}/collezione/{dsvistccol}"/></c:if>
                        <c:if test="${!empty famigliaFilter && !plainMenu}"><c:set var="clearURL" value="${clearURL}/famiglia/{cdvistfam}"/></c:if>
                        <spring:url var="clearURL" value="${clearURL}/">
                            <c:if test="${!empty tipologiaFilter && !plainMenu}"><spring:param name="cdvisttp" value="${tipologiaFilter.cdvisttp}" /></c:if>
                            <c:if test="${!empty collezioneFilter && !plainMenu}"><spring:param name="dsvistccol" value="${collezioneFilter}" /></c:if>
                            <c:if test="${!empty famigliaFilter && !plainMenu}"><spring:param name="cdvistfam" value="${famigliaFilter.cdvistfam}" /></c:if>
                            <c:if test="${!empty finituraFilter}"><spring:param name="cdvistfinm" value="${finituraFilter.cdvistfinm}" /></c:if>
                        </spring:url> 
                        <a href="${clearURL}" class="delete-filter"><c:out value="${fn:toLowerCase(eletFilter[dselet])}" default="Elettrificazioni" escapeXml="false"/></a>
                    </c:if></td>
                    <td class="m-colvetro"><c:if test="${(!empty coloreFilter || !empty finvetroFilter) && !plainMenu}">
                        <c:set var="clearURL" value="/search"/>
                        <c:if test="${!empty tipologiaFilter && !plainMenu}"><c:set var="clearURL" value="${clearURL}/tipologia/{cdvisttp}"/></c:if>
                        <c:if test="${!empty collezioneFilter && !plainMenu}"><c:set var="clearURL" value="${clearURL}/collezione/{dsvistccol}"/></c:if>
                        <c:if test="${!empty famigliaFilter && !plainMenu}"><c:set var="clearURL" value="${clearURL}/famiglia/{cdvistfam}"/></c:if>
                        <spring:url var="clearURL" value="${clearURL}/">
                            <c:if test="${!empty tipologiaFilter && !plainMenu}"><spring:param name="cdvisttp" value="${tipologiaFilter.cdvisttp}" /></c:if>
                            <c:if test="${!empty collezioneFilter && !plainMenu}"><spring:param name="dsvistccol" value="${collezioneFilter}" /></c:if>
                            <c:if test="${!empty famigliaFilter && !plainMenu}"><spring:param name="cdvistfam" value="${famigliaFilter.cdvistfam}" /></c:if>
                            <c:if test="${!empty finituraFilter}"><spring:param name="cdvistfinm" value="${finituraFilter.cdvistfinm}" /></c:if>
                        </spring:url>  
                        <a href="${clearURL}" class="delete-filter"><c:out value="${fn:toLowerCase(coloreFilter[dscolv])}" default="${defColVetro}" escapeXml="false"/> <c:out value="${fn:toLowerCase(finvetroFilter[dsfinv])}" default="${defFinVetro}" escapeXml="false"/></a></c:if>
                        </td>
                    <td class="m-finmont"><c:if test="${!empty finituraFilter && !plainMenu}">
                        <c:set var="clearURL" value="/search"/>
                        <c:if test="${!empty tipologiaFilter && !plainMenu}"><c:set var="clearURL" value="${clearURL}/tipologia/{cdvisttp}"/></c:if>
                        <c:if test="${!empty collezioneFilter && !plainMenu}"><c:set var="clearURL" value="${clearURL}/collezione/{dsvistccol}"/></c:if>
                        <c:if test="${!empty famigliaFilter && !plainMenu}"><c:set var="clearURL" value="${clearURL}/famiglia/{cdvistfam}"/></c:if>
                        <spring:url var="clearURL" value="${clearURL}/">
                            <c:if test="${!empty tipologiaFilter && !plainMenu}"><spring:param name="cdvisttp" value="${tipologiaFilter.cdvisttp}" /></c:if>
                            <c:if test="${!empty collezioneFilter && !plainMenu}"><spring:param name="dsvistccol" value="${collezioneFilter}" /></c:if>
                            <c:if test="${!empty famigliaFilter && !plainMenu}"><spring:param name="cdvistfam" value="${famigliaFilter.cdvistfam}" /></c:if>
                            <c:if test="${!empty coloreFilter}"><spring:param name="cdvistcolv" value="${coloreFilter.cdvistcolv}" /></c:if>
                            <c:if test="${!empty finvetroFilter}"><spring:param name="cdvistfinv" value="${finvetroFilter.cdvistfinv}" /></c:if>
                        </spring:url>  
                        <a href="${clearURL}" class="delete-filter"><c:out value="${fn:toLowerCase(finituraFilter[dsfin])}" default="${defFinMont}" escapeXml="false"/></a></c:if>
                        <%--c:if test="${!empty finituraFilter}"><a href="${clearFinURL}" class="clear-filter">x</a></c:if><c:out value="${finituraFilter[dsfin]}" default="${defFinMont}"/--%>
                    </td>
                </tr>
            </table>
        </div>
          
</c:if>        
<c:if test="${!empty stati}">
<div id="stati-cnt" class="clearfix">
    <div style="float: left; margin-top:2px;"><spring:message code="filter.by" text="Filtra per"/>:</div>
    <ul><c:set var="dsstato">dsstato</c:set>
        <c:if test="${rc.locale.language!='it'}"><c:set var="dsstato">dsstato_${rc.locale.language}</c:set></c:if>
        <c:forEach items="${stati}" var="item">  
            <c:set var="cookieName">view-stato_${item.cdstato}</c:set>
            <li onmouseover="showX('${item.cdstato}')" onmouseout="hideX('${item.cdstato}')" title="<spring:message code="menu.clicca" text="Clicca per selezionare"/>">
                <a href="javascript:void(0)" id="stato_${item.cdstato}" class="chgstato">
                    <div class="legenda-marker" id="legenda-on-${item.cdstato}" style="background-color: rgb(${item.vist_rgb_r},${item.vist_rgb_g},${item.vist_rgb_b}); display: none;">X</div>
                    <div class="legenda-marker" id="legenda-off-${item.cdstato}" style="background-color: rgb(${item.vist_rgb_r},${item.vist_rgb_g},${item.vist_rgb_b})">
                        ${(cookie[cookieName].value == 'S')?"X": ""}
                    </div>
                        ${(!empty item[dsstato])?item[dsstato]:item.dsstato}
                </a>
            </li>
        </c:forEach> 
        <security:authorize ifAllGranted="ROLE_CLIE">
        <li onmouseover="showX('offerte')" onmouseout="hideX('offerte')" style="text-transform: uppercase" title="<spring:message code="menu.clicca" text="Clicca per selezionare"/>">
            <a href="javascript:void(0)" id="offFilterBtn">
                <div class="legenda-marker" id="legenda-on-offerte" style="background-color: rgb(51,153,51);display: none;">X</div>
                <div class="legenda-marker" id="legenda-off-offerte" style="background-color: rgb(51,153,51);">${(cookie.filter_off.value == 'S')?"X": ""}</div>
                <spring:message code="menu.offerte" text="Offerte"/>
            </a>
        </li>
        </security:authorize>
    </ul>
    <div class="clear logout" id="reset_filtri" style="float: right;"><span><a href="<c:url value="/index"/>"><spring:message code="filter.reset" text="Azzera tutti i filtri"/></a></span></div>
            <%--div class="offFilter ${(cookie.filter_off.value == 'S')?"on": "off"}"><a href="javascript:void(0)" id="offFilterBtn"><c:choose><c:when test="${cookie.filter_off.value == 'S'}"><spring:message code="view.allarts" text="tutti gli articoli"/></c:when><c:otherwise><spring:message code="view.onlyoff" text="solo offerte"/></c:otherwise></c:choose></a></div--%>
</div>
</c:if>    
<script type="text/javascript">
    
    function showX(cdstato){
        
        var nameoff = 'legenda-off-' + cdstato;
        var nameon = 'legenda-on-' + cdstato;
        
        $(nameoff).setStyle("display","none");
        $(nameon).setStyle("display","block");
    }
    
    function hideX(cdstato){
        
        var nameoff = 'legenda-off-' + cdstato;
        var nameon = 'legenda-on-' + cdstato;
        
        $(nameoff).setStyle("display","block");
        $(nameon).setStyle("display","none");
    }
    
    
    window.addEvent('domready', function() {
        Cufon.replace('#btn_menu li a');
        
        $('vshop_menu').getElements('li.ddmenu').each( function( elem ){

            var list = elem.getElement('div.list-cnt');
            if(list){

                var myFx = new Fx.Slide(list,{hideOverflow: false}).hide();
                elem.addEvents({
                    'mouseenter' : function(){
                        myFx.cancel();
                        myFx.slideIn();
                    },
                    'mouseleave' : function(){
                        myFx.cancel();
                        myFx.slideOut();
                    }
                });
            }else{
                //elem.addClass("disabled");
            }
        })
        
        $$('.chgstato').each(function(item, index){
            item.addEvent('click', function(e){

                e.stop();

                var cookieName = 'view-'+item.get('id');
                var statoCookie = Cookie.read(cookieName);
                if(statoCookie=='S'){

                    Cookie.write(cookieName, 'N', {duration: 14, path: '/'});

                }else
                if(statoCookie==null || statoCookie=='N'){

                    Cookie.write(cookieName, 'S', {duration: 14, path: '/'});
                    
                    $$('.chgstato').each(function(check, i){
                        
                       if(check != item){
                            Cookie.write('view-'+check.get('id'), 'N', {duration: 14, path: '/'});
                       }

                    });

                    Cookie.write('filter_off', 'N', {duration: 14, path: '/'});

                } 
            
                location.reload();
            });
        });
        
        if($('offFilterBtn')){
            $('offFilterBtn').addEvent('click', function(e){

                e.stop();


                var filterOff = Cookie.read('filter_off');

                if(filterOff=='S'){

                    Cookie.write('filter_off', 'N', {duration: 14, path: '/'});

                    //this.text = '<spring:message code="view.allarts" text="tutti gli articoli"/>';


                }else
                if(filterOff==null || filterOff=='N'){

                    Cookie.write('filter_off', 'S', {duration: 14, path: '/'});
                    
                    $$('.chgstato').each(function(check, i){
                        
                        Cookie.write('view-'+check.get('id'), 'N', {duration: 14, path: '/'});
                    
                    });

                    //this.text = '<spring:message code="view.onlyoff" text="solo offerte"/>';
                }

                location.reload();



            });   
        }
        
        if($('reset_filtri')){
         $('reset_filtri').addEvent('click', function(e){
             e.stop();
             
             $$('.chgstato').each(function(item, index){
                var cookieName = 'view-'+item.get('id');
                var statoCookie = Cookie.read(cookieName);

                if(statoCookie=='S'){

                    Cookie.write(cookieName, 'N', {duration: 14, path: '/'});

                }
             
             });
             
             var filterOff = Cookie.read('filter_off');

             if(filterOff=='S'){

                Cookie.write('filter_off', 'N', {duration: 14, path: '/'});

             }
             
             window.location.href='<c:url value="/index"/>';
         });
        }
    });
</script>
