<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<jsp:directive.include file="/WEB-INF/jsp/header.jsp"/>

<script type="text/javascript">
    function loadArticoloData(link){

        var href = link.get('href');

        var myRequest = new Request.JSON({method: 'get',
                    url: href,
                    onSuccess: function(scheda){

                    }
        }).get();
   }

   function sendFormData(){

        var myRequest = new Request.JSON({method: 'post',
                    url: $('selartForm').get('action'),
                    data: $('selartForm').toQueryString(),
                    onSuccess: function(res){
                        updateScheda(res.scheda);
                        
                    }
        }).get();
   }


   function updateDD(dataArray, target, cd, ds){

        var targetField = $(target);
        
        if(dataArray.length >0){
            
            targetField.empty();
            var opt = new Element('option', {
                'value': '',
                'text': 'Seleziona'
            });
            targetField.adopt(opt);
            dataArray.each(function(item, index){
                var opt = new Element('option', {
                    'value': item[cd]
                });
                opt.set('text', item[ds]);
                targetField.adopt(opt);

            });

            targetField.set('value',$(cd).get('value'));
        }else{
            targetField.empty();
            var opt = new Element('option', {
                'value': '',
                'text': 'nd'
            });
            targetField.adopt(opt);
        }
   }


   function updateScheda(scheda){
       //console.log('update' );

       try{

        var img = new Element('img', {
            'src': '<c:url value="/static/images/articoli/disegnitecnici/${famigliaFilter.cdvistfam_m}${tipologiaFilter.cdvisttp_m}"/>'+$('cdvistv1').get('value')+$('cdvistv2').get('value')+$('cdvistv3').get('value')+'.jpg'
        });
        $('art-image').empty().adopt(img);

        if(scheda.colori_vetro){
            //console.log('colori vetro');
            //var targetField = $('sel_colori');

            updateDD(scheda.colori_vetro, 'sel_colori', 'cdvistcolv', 'dsvistcolv');
            /*
            targetField.empty();
            var opt = new Element('option', {
                'value': '',
                'text': 'Seleziona'
            });
            targetField.adopt(opt);
            scheda.colori_vetro.each(function(item, index){
                var opt = new Element('option', {
                    'value': item.cdvistcolv
                });
                opt.set('text', item.dsvistcolv);
                targetField.adopt(opt);

            });

            targetField.set('value',$('cdvistcolv').get('value'));
            */
        }
        if(scheda.elettrificazioni){
            //console.log('elettrificazioni');
            updateDD(scheda.elettrificazioni, 'sel_elettrificazioni', 'cdvistelet', 'dsvistelet');


            /*
            var targetField = $('sel_elettrificazioni');
            targetField.empty();
            var opt = new Element('option', {
                'value': '',
                'text': 'Seleziona'
            });
            targetField.adopt(opt);
            scheda.elettrificazioni.each(function(item, index){
                var opt = new Element('option', {
                    'value': item.cdvistelet
                });
                opt.set('text', item.dsvistelet);
                targetField.adopt(opt);

            });

            targetField.set('value',$('cdvistelet').get('value'));*/
        }
        if(scheda.finit_mont){
            //console.log('finit_mont');
            updateDD(scheda.finit_mont, 'sel_finm', 'cdvistfinm', 'dsvistfinm');

            /*
            var targetField = $('sel_finm');
            targetField.empty();
            var opt = new Element('option', {
                'value': '',
                'text': 'Seleziona'
            });
            targetField.adopt(opt);
            scheda.finit_mont.each(function(item, index){
                var opt = new Element('option', {
                    'value': item.cdvistfinm
                });
                opt.set('text', item.dsvistfinm);
                targetField.adopt(opt);

            });

            targetField.set('value',$('cdvistfinm').get('value'));*/
        }
        if(scheda.finit_vetro){
            //console.log('finit_vetro');

            updateDD(scheda.finit_vetro, 'sel_finv', 'cdvistfinv', 'dsvistfinv');

            /*
            var targetField = $('sel_finv');

            if(scheda.finit_vetro.length >0){
                
                targetField.empty();
                var opt = new Element('option', {
                    'value': '',
                    'text': 'Seleziona'
                });
                targetField.adopt(opt);
                scheda.finit_vetro.each(function(item, index){
                    var opt = new Element('option', {
                        'value': item.cdvistfinv
                    });
                    opt.set('text', item.dsvistfinv);
                    targetField.adopt(opt);

                });

                targetField.set('value',$('cdvistfinv').get('value'));
            }else{
                targetField.empty();
                var opt = new Element('option', {
                    'value': '',
                    'text': 'nd'
                });
                targetField.adopt(opt);
            }*/
        }
       }catch(err){}
        
   }
</script>
    <c:choose>
        <c:when test="${!empty scheda}">
        <c:set var="pagination">
        <div class="pagination">
        <%--PAGINE: <div class="page-links"><a href="?page=1" onclick="changePage(1)">&lt;&lt;</a>
        <a href="?page=${theList.prev}" onclick="changePage(${theList.prev})">&lt;</a>
        <c:forEach begin="${theList.startRange}" end="${theList.endRange}" var="p"> <a href="?page=<c:if test="${p!=theList.pageNumber}">${p}</c:if>" class="pag${(p==theList.pageNumber)?"-on":""}"><c:choose><c:when test="${p==theList.pageNumber}"><strong>${p}</strong></c:when><c:otherwise>${p}</c:otherwise></c:choose></a></c:forEach>
        <a href="?page=${theList.next}" onclick="changePage(${theList.next})">&gt;</a> <a href="?page=${theList.pages}" onclick="changePage(${theList.pages})">&gt;&gt;</a>
        </div--%>
        </div>
        </c:set>

        <div id="scheda-col-sx">
            <div id="foto-art">
                #foto_${scheda.articolo.cdarti}
            </div>
            <div id="dati-art">
                <h2>${famiglia.dsvistfam}</h2>

                <p>${famiglia.dsextvistfam}</p>
            </div>
            <h2>Scegli modello</h2>
            <div id="articoli-list">
                <c:forEach items="${modelli}" var="t" varStatus="s">
                <div id="thumb${t.cdarti}" class="art_item">
                    <spring:url var="artURL" value="/scheda-{dsvistfam}-{cdvisttp}/{cdvistfam}/articolo.json">
                        <spring:param name="cdvistfam" value="${famigliaFilter.cdvistfam}" />
                        <spring:param name="dsvistfam" value="${famigliaFilter.dsvistfam}" />
                        <spring:param name="cdvisttp" value="${tipologiaFilter.cdvisttp}" />
                        <spring:param name="cdvistv1" value="${t.cdvistv1}"/>
                        <spring:param name="cdvistv2" value="${t.cdvistv2}"/>
                        <spring:param name="cdvistv3" value="${t.cdvistv3}"/>
                    </spring:url>
                    <%--a href="${artURL}" class="tipz art-thumb" title="${famiglia.dsvistfam}::${tipologiaFilter.cdvisttp} ${t.cdvistv1} ${t.cdvistv2} ${t.cdvistv3}"--%>
                    <a href="#${t.cdvistv1}|${t.cdvistv2}|${t.cdvistv3}" class="tipz art-thumb" title="${famiglia.dsvistfam}::${tipologiaFilter.cdvisttp} ${t.cdvistv1} ${t.cdvistv2} ${t.cdvistv3}">
                        <img src="<c:url value='/static/images/articoli/disegnitecnici/${famigliaFilter.cdvistfam_m}${tipologiaFilter.cdvisttp_m}${t.cdvistv1}${t.cdvistv2}${t.cdvistv3}.jpg'/>" title=""/>
                    </a>
                </div>
                </c:forEach>
                <script type="text/javascript">
                window.addEvent('domready', function() {

                        //store titles and text
                        $$('a.tipz').each(function(element,index) {
                                var content = element.get('title').split('::');
                                element.store('tip:title', content[0]);
                                element.store('tip:text', content[1]);
                        });

                        //create the tooltips
                        var tipz = new Tips('.tipz',{
                                className: 'tipz',
                                fixed: false,
                                hideDelay: 30,
                                showDelay: 30
                        });

                        tipz.addEvents({
                                'show': function(tip) {
                                        tip.fade('in');
                                },
                                'hide': function(tip) {
                                        tip.fade('out');
                                }
                        });

                        $$('div.art_item').each(function(element, index) {

                                element.addEvents({
                                    'click' : function(event){
                                        event.stop();
                                        var href = this.getFirst('a').get('href').substring(1);
                                        $('sel_modello').set('value', href);

                                        var varArray = href.split('|',3);

                                        $('cdvistv1').set('value', varArray[0]);
                                        $('cdvistv2').set('value', varArray[1]);
                                        $('cdvistv3').set('value', varArray[2]);



                                        sendFormData();
                                        //loadArticoloData(this.getFirst('a'));


                                    }
                                });

                            });

                });


                </script>
            </div>
        </div>
        <div id="scheda-col-dx">
            <spring:url var="baseURL" value="/scheda-{dsvistfam}-{cdvisttp}/{cdvistfam}/articolo.json">
                <spring:param name="cdvistfam" value="${famigliaFilter.cdvistfam}" />
                <spring:param name="dsvistfam" value="${famigliaFilter.dsvistfam}" />
                <spring:param name="cdvisttp" value="${tipologiaFilter.cdvisttp}" />
            </spring:url>
            <form id="selartForm" name="selartForm" action="${baseURL}">
                <input type="hidden" name="cdvistv1" id="cdvistv1">
                <input type="hidden" name="cdvistv2" id="cdvistv2">
                <input type="hidden" name="cdvistv3" id="cdvistv3">
                <input type="hidden" name="cdvistfinm" id="cdvistfinm">
                <input type="hidden" name="cdvistfinv" id="cdvistfinv">
                <input type="hidden" name="cdvistcolv" id="cdvistcolv">
                <input type="hidden" name="cdvistelet" id="cdvistelet">

            </form>
            <div id="art-image">
                #disegno tecnico
            </div>
            <div id="modello" class="dd-scheda">
                <h2>Modello</h2>
                <select name="modello" id="sel_modello">
                    <option value="">Seleziona modello</option>
                    <c:forEach items="${modelli}" var="t" varStatus="s">
                        <option value="${t.cdvistv1}|${t.cdvistv2}|${t.cdvistv3}">${famigliaFilter.cdvistfam} ${tipologiaFilter.cdvisttp} ${t.cdvistv1} ${t.cdvistv2} ${t.cdvistv3}</option>
                    </c:forEach>
                </select>
                <spring:url var="baseURL" value="/scheda-{dsvistfam}-{cdvisttp}/{cdvistfam}/articolo.json">
                    <spring:param name="cdvistfam" value="${famigliaFilter.cdvistfam}" />
                    <spring:param name="dsvistfam" value="${famigliaFilter.dsvistfam}" />
                    <spring:param name="cdvisttp" value="${tipologiaFilter.cdvisttp}" />
                </spring:url>
                <script type="text/javascript">

                   $('sel_modello').addEvent('change', function(){
                      var value = this.get('value');

                      var varArray = value.split('|',3);
                      
                      $('cdvistv1').set('value', varArray[0]);
                      $('cdvistv2').set('value', varArray[1]);
                      $('cdvistv3').set('value', varArray[2]);

                      
                      sendFormData();

                   });
                </script>
                <%--c:forEach items="${modelli}" var="t" varStatus="s">
                    <c:if test="${s.first}">
                        <div class="xlist-cnt">
                            <ul></c:if>
                                <li class="menu_item">
                                    <spring:url var="artURL" value="/scheda-{dsvistfam}-{cdvisttp}/{cdvistfam}/articolo.do">
                                        <spring:param name="cdvistfam" value="${famigliaFilter.cdvistfam}" />
                                        <spring:param name="dsvistfam" value="${famigliaFilter.dsvistfam}" />
                                        <spring:param name="cdvisttp" value="${tipologiaFilter.cdvisttp}" />
                                        <spring:param name="cdvistv1" value="${t.cdvistv1}"/>
                                        <spring:param name="cdvistv2" value="${t.cdvistv2}"/>
                                        <spring:param name="cdvistv3" value="${t.cdvistv3}"/>
                                    </spring:url>
                                    <a href="${artURL}">${famigliaFilter.cdvistfam} ${tipologiaFilter.cdvisttp} ${t.cdvistv1} ${t.cdvistv2} ${t.cdvistv3}</a>
                                </li>
                            <c:if test="${s.last}">
                    </ul></div></c:if>
                </c:forEach--%>
            </div>
            <%--div id="variante" class="dd-scheda">
                <h2>Variante</h2>
            </div--%>
            <div id="parti-metalliche" class="dd-scheda">
                <h2>Finitura montatura</h2>
                <select name="finm" id="sel_finm">
                    <option value="">Seleziona modello</option>
                </select>
                <script type="text/javascript">

                   $('sel_finm').addEvent('change', function(){
                      var value = this.get('value');

                      $('cdvistfinm').set('value', value);

                      sendFormData();

                   });
                </script>
                <%--c:forEach items="${partmet}" var="t" varStatus="s">
                    <c:if test="${s.first}">
                        <div class="xlist-cnt">
                            <ul></c:if>
                                <li class="menu_item<c:if test='${t.cdvistfinm==finituraFilter.cdvistfinm}'> active</c:if>">
                                    <spring:url var="baseURL" value="">
                                    </spring:url>
                                    <a href="${baseURL}">${t.dsvistfinm}</a>
                                </li>
                            <c:if test="${s.last}">
                    </ul></div></c:if>
                </c:forEach--%>
            </div>
            <div id="finitura-vetro" class="dd-scheda">
                <h2>Finitura vetro</h2>
                <select name="finv" id="sel_finv">
                    <option value="">Seleziona modello</option>
                </select>
                <script type="text/javascript">

                   $('sel_finv').addEvent('change', function(){
                      var value = this.get('value');

                      $('cdvistfinv').set('value', value);

                      sendFormData();

                   });
                </script>
            </div>
            <div id="tonalita-vetro" class="dd-scheda">
            <h2>Tonalità vetro</h2>
            <select name="colori" id="sel_colori">
                <option value="">Seleziona modello</option>
            </select>
            <script type="text/javascript">

               $('sel_colori').addEvent('change', function(){
                  var value = this.get('value');

                  $('cdvistcolv').set('value', value);

                  sendFormData();

               });
            </script>
            <%--c:forEach items="${coloriArticolo}" var="t" varStatus="s">
                <c:if test="${s.first}">
                    <div class="xlist-cnt">
                        <ul></c:if>
                            <li class="menu_item<c:if test='${t.cdvistcolv==coloreFilter.cdvistcolv}'> active</c:if>">
                                <spring:url var="baseURL" value="">
                                </spring:url>
                                <a href="${baseURL}">${t.dsvistcolv}</a>
                            </li>
                        <c:if test="${s.last}">
                </ul></div></c:if>
            </c:forEach--%>
            </div>
            <div id="lampadine" class="dd-scheda">
                <h2>Elettrificazione</h2>
                <select name="elettrificazione" id="sel_elettrificazioni">
                    <option value="">Seleziona modello</option>
                </select>
                <script type="text/javascript">

                   $('sel_elettrificazioni').addEvent('change', function(){
                      var value = this.get('value');

                      $('cdvistelet').set('value', value);

                      sendFormData();

                   });
                </script>
            </div>
            <div id="disponibilita" class="dd-scheda"></div>
            <div id="prezzo" class="dd-input"></div>
            <div id="qta" class="dd-input"></div>
        </div>

        ${pagination}



        </c:when>
        <c:otherwise>
            Articolo non trovato
        </c:otherwise>
    </c:choose>

<jsp:directive.include file="/WEB-INF/jsp/footer.jsp"/>
