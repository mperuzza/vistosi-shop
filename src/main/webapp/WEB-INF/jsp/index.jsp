<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<jsp:directive.include file="/WEB-INF/jsp/header.jsp"/>

<script type="text/javascript" src="<c:url value='/static/js/Observer.js' />"></script>
<script type="text/javascript" src="<c:url value='/static/js/Autocompleter.js' />"></script>
<script type="text/javascript" src="<c:url value='/static/js/Autocompleter.Local.js' />"></script>
<script type="text/javascript" src="<c:url value='/static/js/Autocompleter.Request.js' />"></script>
<script type="text/javascript" src="<c:url value='/static/js/mediaboxAdv-1.3.4b.js' />"></script>

<script type="text/javascript">

    var autoCSS = Asset.css('<c:url value='/static/styles/Autocompleter.css'/>', {id: 'autoStyle', title: 'autoStyle'});

</script>
<c:choose>
    <c:when test="${!empty theList}">
        <%--c:set var="pagination">
        <c:url var="pagingLink" value="?">
            <c:if test="${!empty param.cdvistccol}"><c:param name="cdvistccol" value="${param.cdvistccol}"/></c:if>
            <c:if test="${!empty param.cdvistcolv}"><c:param name="cdvistcolv" value="${param.cdvistcolv}"/></c:if>
            <c:if test="${!empty param.cdvistfinm}"><c:param name="cdvistccol" value="${param.cdvistfinm}"/></c:if>
        </c:url>
        <div class="pagination">
        <div class="page-links"><a href="${pagingLink}page=1">&lt;&lt;</a>
        <a href="${pagingLink}page=${theList.prev}">&lt;</a>
        <c:forEach begin="${theList.startRange}" end="${theList.endRange}" var="p"> <a href="${pagingLink}page=<c:if test="${p!=theList.pageNumber}">${p}</c:if>" class="pag${(p==theList.pageNumber)?"-on":""}"><c:choose><c:when test="${p==theList.pageNumber}"><strong>${p}</strong></c:when><c:otherwise>${p}</c:otherwise></c:choose></a></c:forEach>
        <a href="${pagingLink}page=${theList.next}">&gt;</a> <a href="${pagingLink}page=${theList.pages}" >&gt;&gt;</a>
        </div><fmt:message key="pages"/>:
        </div>
        </c:set--%>

        <%--${pagination}--%>
        <c:set var="dsfamdef">dsvistfam_eng</c:set>
        <c:choose>
            <c:when test="${empty theList.list}">
                <security:authorize ifAllGranted="ROLE_ANONYMOUS">
                <div class="gen-cnt"><fmt:message key="art.notfound"/></div>
                </security:authorize>
                <security:authorize ifNotGranted="ROLE_ANONYMOUS">
                <div class="gen-cnt"><fmt:message key="art.notfound.logged"/></div>
                </security:authorize>
            </c:when>
            <c:when test="${tipiView}">
                <c:set var="dstp">dsextvisttp${atkLangSfx}</c:set>
                <c:forEach items="${theList.list}" var="t" varStatus="s">
                    <div class="art_item">
                        <div><a href="<c:url value='/scheda-${famigliaFilter.dsvistfam}-${t.cdvisttp}/${famigliaFilter.cdvistfam}/'/>"><img src="<c:url value='/static/images/famiglie/${famigliaFilter.cdvistfam_m}-${t.cdvisttp_m}.jpg'/>"/></a></div>
                        <div class="dida" <c:if test="${!empty t.stato}">style="opacity:1.0;filter:alpha(opacity=100);"</c:if>><div class="text" <c:if test="${!empty t.stato}">style="color:#000;"</c:if>>${t[dstp]}</div><div class="back" <c:if test="${!empty t.stato}">style="background-color: rgb(${t.stato.vist_rgb_r},${t.stato.vist_rgb_g},${t.stato.vist_rgb_b});"</c:if>>&nbsp;</div></div>
                    </div>
                </c:forEach>
            </c:when>
            <c:when test="${colView}">
                <c:set var="dsfam">dsvistfam${atkLangSfx}</c:set>
                <c:forEach items="${theList.list}" var="t" varStatus="s">
                    <div class="art_item">
                        <div><a href="<c:url value='/scheda-${t.vist_famiglia.dsvistfam}-${t.cdvisttp}/${t.vist_famiglia.cdvistfam}/'/>"><img src="<c:url value='/static/images/famiglie/${t.vist_famiglia.cdvistfam_m}-${t.cdvisttp}.jpg'/>"/></a></div>
                        <div class="dida" <c:if test="${!empty t.vist_famiglia.stato}">style="opacity:1.0;filter:alpha(opacity=100);"</c:if>><div class="text" <c:if test="${!empty t.vist_famiglia.stato}">style="color:#000;"</c:if>>${!empty t.vist_famiglia[dsfam]?t.vist_famiglia[dsfam]:t.vist_famiglia[dsfamdef]}</div><div style="position: absolute;right: 0; padding-right: 2px">${t.cdvisttp}</div><div class="back" <c:if test="${!empty t.vist_famiglia.stato}">style="background-color: rgb(${t.vist_famiglia.stato.vist_rgb_r},${t.vist_famiglia.stato.vist_rgb_g},${t.vist_famiglia.stato.vist_rgb_b});"</c:if>>&nbsp;</div></div>
                    </div>
                </c:forEach>
            </c:when>
            <c:when test="${famView}">
                <c:set var="dsfam">dsvistfam${atkLangSfx}</c:set>
                <c:forEach items="${theList.list}" var="t" varStatus="s">
                    <div class="art_item">
                        <div><a href="<c:url value='/scheda-${t.dsvistfam}-${tipologiaFilter.cdvisttp}/${t.cdvistfam}/'/>"><img src="<c:url value='/static/images/famiglie/${t.cdvistfam_m}-${tipologiaFilter.cdvisttp_m}.jpg'/>"/></a></div>
                        <div class="dida" <c:if test="${!empty t.stato}">style="opacity:1.0;filter:alpha(opacity=100);"</c:if>><div class="text" <c:if test="${!empty t.stato}">style="color:#000;"</c:if>>${!empty t[dsfam]?t[dsfam]:t[dsfamdef]}</div><div class="back" <c:if test="${!empty t.stato}">style="background-color: rgb(${t.stato.vist_rgb_r},${t.stato.vist_rgb_g},${t.stato.vist_rgb_b});"</c:if>>&nbsp;</div></div>
                    </div>
                </c:forEach>
            </c:when>
        </c:choose>


        <%--${pagination}--%>

        <script type="text/javascript">
            window.addEvent('domready', function(){
                $('main').getElements('div.art_item').each( function( elem ){

                    elem.addEvents({
                        'mouseenter' : function(){
                            this.addClass('art_item_hover');
                        },
                        'mouseleave' : function(){
                            this.removeClass('art_item_hover');
                        }
                    });
                });

                $$('div.art_item').each( function( element ){
                    element.addEvent('click', function(e){
                        e.stop();
                        var uri = new URI(this.getElement('a').get('href'));
                        uri.go();
                    });

                });



            });
        </script>
    </c:when>
    <c:when test="${!empty tipologieThumb}">
        <c:set var="dstp">dsextvisttp${atkLangSfx}</c:set>
        <c:forEach items="${tipologieThumb}" var="t" varStatus="s">
            <div class="home_item">
                <div><a href="<c:url value='/search/tipologia/${t.cdvisttp}/'/>"><img src="<c:url value='/static/images/famiglie/${t.cdvisttp}.png'/>"/></a></div>
                <div class="dida"><div class="text-dida-helv">${ep:capitalizeFirst(fn:toLowerCase(t[dstp]))}</div></div>
            </div>
        </c:forEach>

        <security:authorize ifNotGranted="ROLE_ANONYMOUS">
            <div id="fast-cart-search" class="clearfix"  style="margin-bottom: 40px;">
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


        <script type="text/javascript">
            
            window.addEvent('domready', function(){

                var uri = '<c:url value="/carrelloItem-{cdarti}/"/>';

                /*var allDownloadLink = $('cart-cnt').getElements('a.downloadlink').each(function(item, index){
                item.addEvent('click', function(e){
                    e.stop();

                    Mediabox.open(item.get('href'), '<spring:message code="title.downloads" text="Downloads"/>', '640 360');
                });
            });  */
  
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
                });

        </script>

        <script type="text/javascript">
            window.addEvent('domready', function(){
                $('main').getElements('div.home_item').each( function( elem ){

                    elem.addEvents({
                        'mouseenter' : function(){
                            this.addClass('home_item_hover');
                        },
                        'mouseleave' : function(){
                            this.removeClass('home_item_hover');
                        }
                    });
                })

                $$('div.home_item').each( function( element ){
                    element.addEvent('click', function(e){
                        e.stop();
                        var uri = new URI(this.getElement('a').get('href'));
                        uri.go();
                    });

                });
                
                if($('alertbar')) {
                    (function(){ $('alertbar').reveal(); }).delay(2000);

                    $('alerttrigger').addEvent('click', function(e){
                        $('alertbar').dissolve();

                    });
                }                


            });


        </script>
        <security:authorize ifNotGranted="ROLE_ANONYMOUS">
            <c:set var="realCustomer" value="false"/>
            <security:authorize ifAllGranted="ROLE_CLIE"><security:authentication var="realCustomer" property="principal.realCustomer"/></security:authorize> 
            <div class="menu_bottom">
                <c:if test="${!empty condizioni}">
                <div class="menu_condizioni">
                    <c:if test="${!condizioni['accepted']}"><div class="alert-condc"><img src="<c:url value='/static/images/alert_on.png'/>"/></div></c:if>
                    
                        <div class="img_condizioni">
                            <img src="<c:url value='/static/images/icon_condiz.png'/>" alt="Condizioni di vendita" />
                        </div>
                        <div class="txt_condizioni">
                            <a href="<c:url value="/conditions"/>"><spring:message code="purchasing.conditions" text="Condizioni di acquisto"/></a>
                        </div>
                    
                </div>
                </c:if>
                <div class="menu_offerta">
                    <div class="img_offerta">
                        <img src="<c:url value='/static/images/icon_offer.png'/>" alt="Offerte riservate" />
                    </div>
                    <div class="txt_offerta">
                        <a href="<c:choose><c:when test="${useSpeclist}"><c:url value="/static/offerte/CONDIZ_VENDITA_2012_ART_OFFERTA_USA.pdf"/></c:when><c:otherwise><c:url value="/static/offerte/CONDIZ_VENDITA_2012_ART_OFFERTA_EURO.pdf"/></c:otherwise></c:choose>"><spring:message code="menu.offerte" text="Offerte"/></a>
                    </div>
                </div>
            </div>

            <c:choose>
                <c:when test="${!empty promo}">
                    <div class="promozione_hp">
                        <c:forEach items="${promo}" var="p">
                            <div class="link_promo" style="background-image: url('<c:url value="/static/promozioni/${p.tkpromo}/banner.jpg"/>');">
                                <div class="promozione promo">
                                    <a href="<c:url value='/static/promozioni/${p.tkpromo}/${p.pdf}'/>">${p.dspromo}</a>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    </c:when>
                    <c:otherwise>
                        <%--h3><spring:message code="no.promo" text="Nessuna promozione in corso"/>.</h3--%>
                    </c:otherwise>
                </c:choose>
                    
            <script type="text/javascript">
                $$('.menu_offerta').addEvent('click', function(){
                    var tag = $$('.txt_offerta a');
                    window.open(tag, '_blank');
                });

                $$('.menu_condizioni').addEvent('click', function(){
                    var tag = $$('.txt_condizioni a');
                    window.open(tag, '_blank');
                });
            </script>
            </security:authorize>

        </c:when>
    </c:choose>

    <jsp:directive.include file="/WEB-INF/jsp/footer.jsp"/>
