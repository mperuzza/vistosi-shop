<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<jsp:directive.include file="/WEB-INF/jsp/header.jsp"/>
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
<c:set var="dsext">dsestesa${atkLangSfx}</c:set>
<c:set var="dsvistccol">dsvistccol${atkLangSfx}</c:set>
<c:choose>
    <c:when test="${rc.locale!='it'}">
        <c:set var="dsdesignerlang">dsextdesigner_eng</c:set>
    </c:when>
    <c:otherwise>
        <c:set var="dsdesignerlang">dsextdesigner</c:set>
    </c:otherwise>
</c:choose>    
<spring:message code="singular.m" var="nums"/>
<spring:message code="articoli" arguments="nums" var="art"/>
<c:set var="useSpeclist" value="false"/>
<security:authorize ifAllGranted="ROLE_CLIE"><security:authentication var="useSpeclist" property="principal.isSpecList"/></security:authorize>
<script type="text/javascript" src="<c:url value='/static/js/mediaboxAdv-1.3.4b.js' />"></script>
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/static/styles/mediaboxAdvBlack21.css'/>"/>
<script type="text/javascript">

        var schWaiter;
        var fotoClick;
        var disClick;
        var disActive;

        function stripJsession(s){
            return  s.replace(/[#;].+/, "");
        }


        function loadArticoloData(link){

            var href = link.get('href');

            var myRequest = new Request.JSON({method: 'get',
                url: href,
                onSuccess: function(scheda){

                }
            }).get();
        }

        function sendFormData(scrollPage){
            var qs = $('selartForm').toQueryString();
            //if(scrollPage) qs += "&firstReq=true";
            var myRequest = new Request.JSON({method: 'post',
                url: $('selartForm').get('action'),
                data: qs,
                onRequest: function(){
                    //$('scheda-col-dx').addClass('preloader');
                    //console.log('request')
                    schWaiter.show();
                },
                onSuccess: function(res){
                    //if(scrollPage) new Fx.Scroll(window).toTop();
                        
                    //console.log('success');
                    updateScheda(res.scheda);
                        
                }
            }).get();
        }

        var contat = 1;
        function updateDD(scheda, attr, target, cd, cdf, ds, noautoselect){
            //var img = new Asset.image('<c:url value="/static/images/articoli/disegnitecnici/${(useSpeclist)?'po/':''}"/>'+ $('vist_filedis').get('value') +'.jpg', {id: 'disTecBig', onerror: loadImgError});
            var dataArray = scheda[attr];
            /*console.log('attr:', attr)
            console.log('scheda[attr]:', scheda[attr])
            console.log('target',target)
            console.log('cd',cd)
            console.log('cdf',cdf)
            console.log('ds',ds)
            console.log('noautoselect',noautoselect)*/
            var iconpath = '';
            var icondir = '';
            switch (attr){
                case 'vetro': 
                    icondir = attr;
                    iconpath = stripJsession('<c:url value="/images/articoli/"/>') + icondir + '/${famigliaFilter.cdvistfam}_${tipologiaFilter.cdvisttp}_' + ($('cdvistv1').get('value') + '_');
                    break;
                case 'elettrificazioni':
                    icondir = attr + '/';
                    iconpath = stripJsession('<c:url value="/images/articoli/"/>') + icondir;
                    break;
                case 'finit_mont':
                    icondir = 'montature/';
                    iconpath = stripJsession('<c:url value="/images/articoli/"/>') + icondir;
                    break;
                case 'ricambi':
                    icondir = 'ricambi/';
                    iconpath = stripJsession('<c:url value="/static/images/articoli/"/>') + icondir;
                    break;
            }            
        
            // /images/articoli/vetro/${t.articolo.vist_famiglia.cdvistfam_m}_${t.articolo.cdvisttp}_${t.articolo.cdvistv1}_${t.articolo.cdvistcolv}${t.articolo.cdvistfinv}.jpg
            
            var targetField = $(target);
            if(targetField.getParent().hasClass('hide'))  targetField.getParent().removeClass('hide');   
                
            /*try{
                targetField.removeClass('masked');
                targetField.getParent().getElements('.replacer').dispose();
            }catch(e){}*/
            var icoCnt = targetField.getParent().getElement('.icolist ul');
            
            if(dataArray.length >0){

                targetField.empty();
                targetField.removeClass('disabled');
                icoCnt.empty();
                
                if(dataArray.length > 1 || noautoselect){
                    if(attr!='ricambi') dataArray.sortBy(ds); //l'ordine per i ricambi arriva dalla select
                    var opt = new Element('option', {
                        'value': '',
                        'text': '<spring:message code="choose" text="Seleziona"/>',
                        'styles': { 'color': '#000'}
                    });
                    targetField.adopt(opt);
                }
                dataArray.each(function(item, index){
                    
                    var underbg = '#fff';
                    
                    if(item[cd]!=null){
                    
                        var opt = new Element('option', {
                            'value': item[cd]
                        });
                        if(item.stato){
                            opt.setStyle('color', 'rgb('+ item.stato.vist_rgb_r + ',' + item.stato.vist_rgb_g + ',' + item.stato.vist_rgb_b + ')');
                            underbg = 'rgb('+ item.stato.vist_rgb_r + ',' + item.stato.vist_rgb_g + ',' + item.stato.vist_rgb_b + ')';
                        }else{
                            opt.setStyle('color', 'rgb(0,0,0)');                        
                        }

                        if(item.datiRicambio!=null){
                            opt.store('img', item.datiRicambio.imgv_filename);

                            opt.set('text', item[ds] + ' ('+item.datiRicambio.numv+')');
                            //opt.store('numv', item.datiRicambio.numv);
                        }else{
                            opt.set('text', item[ds]);
                        }


                        targetField.adopt(opt);

                        var cdico = '';
                        var cdsel = '';
                        switch (attr){
                            case 'vetro': 
                                cdico = (item.col.cdvistcolv?item.col.cdvistcolv:'') + (item.fin.cdvistfinv?item.fin.cdvistfinv:'')+ '.jpg';
                                cdsel = item.cdvistvetro;
                                break;
                            case 'elettrificazioni':
                                cdico = item.simbAttacco + '.jpg';
                                cdsel = item[cd];
                                break;
                            case 'finit_mont':
                                cdico = item.cdvistfinm + '.jpg';
                                cdsel = item.cdvistfinm;
                                break;
                            case 'ricambi':
                                cdico = 'ric' + item.datiRicambio.numv + '.gif';
                                cdsel = item.cdarti;
                        }

                        var img = new Asset.image(iconpath + cdico, { width:'45px', onerror: loadImgError});
                        //in caso di ricambi invece del tooltip ci dovrebbe essere la descrizione a lato
                        
                        
                        if(attr=='ricambi'){
                            var a = new Element('a', {
                                'href': 'javascript:void(0)'
                            });
                            
                            var span = new Element('span',{
                                'text': item[ds] + ' ('+item.datiRicambio.numv+')'
                            });

                            a.adopt(img);
                            a.adopt(span);
                            var li = new Element('li', {
                                'class': cdsel.replace('|',''),
                                'events':{
                                    'click': function(){
                                        targetField.set('value', cdsel);
                                        targetField.fireEvent('change');
                                    }
                                }
                            }); 
                            li.adopt(a);

                            var under = new Element('div', {
                                'class': 'under',
                                'styles': {
                                    'background-color': underbg
                                }
                            });
                            li.adopt(under);

                            icoCnt.grab(li);                            
                        }else{

                            var a = new Element('a', {
                                'href': 'javascript:void(0)',
                                'class': 'tipz',
                                'title': '::'+item[ds]
                            });

                            a.adopt(img);
                            var li = new Element('li', {
                                'class': cdsel.replace('|',''),
                                'events':{
                                    'click': function(){
                                        targetField.set('value', cdsel);
                                        targetField.fireEvent('change');
                                    }
                                }
                            }); 
                            li.adopt(a);

                            var under = new Element('div', {
                                'class': 'under',
                                'styles': {
                                    'background-color': underbg
                                }
                            });
                            li.adopt(under);

                            icoCnt.adopt(li);
                        }
                    }
                });

                if(dataArray.length == 1 && !noautoselect){;
                    if($defined($(cdf))) {
                        $(cdf).set('value', dataArray[0][cd]);
                    }else{
                        targetField.set('value',dataArray[0][cd]);
                        var icoel = icoCnt.getElements(targetField.get('value').replace('|',''));
                    }
                }
                if($defined($(cdf))) {
                    targetField.set('value',$(cdf).get('value'));
                    var cl = targetField.get('value').replace('|','');
                    if(cl && cl != '') icoCnt.getElements('.'+cl).addClass('active');
                }

                /*if(dataArray.length==1 && !noautoselect){
                    console.log('dataArray length 1')
                    var el =new Element('div', {
                        'class': 'replacer',
                        'html': targetField.getSelected().get('text')
                    });
                
                    if(dataArray[0].stato){
                        el.setStyle('color', 'rgb('+ dataArray[0].stato.vist_rgb_r + ',' + dataArray[0].stato.vist_rgb_g + ',' + dataArray[0].stato.vist_rgb_b + ')');
                    }else{
                        el.setStyle('color', 'rgb(0,0,0)');
                    }                
                    //targetField.grab(el, 'before').addClass('masked');

                }else{

                }*/


            }else{
                targetField.empty();
                var opt = new Element('option', {
                    'value': '',
                    'text': 'nd'
                });
                targetField.adopt(opt);
                //targetField.addClass('disabled');
                if(contat<= 1) {
                    var li = new Element('li', {
                        'html':'nd'
                        }
                    ); 
                    contat++;
                }

                icoCnt.adopt(li);

            }
            
            iniTipz(icoCnt);

        }

        //update image
        function loadImgError(){
            this.set('src', '<c:url value="/static/images/articoli/disegnitecnici/"/>nd.jpg');
            $('caption').setStyle('display', 'none');
        }
        
        function addItemToLampList(contList, lampList){
            var imgWidth = '25px';
            var itemList = new Element('li');
            for(var idl=0; idl<lampList.length; idl++ ){

                var e = lampList[idl];

                if(idl==0){
                    var acro = new Element('img', {src: '<c:url value="/static"/>' + (e.acro?e.acro:'/images/null.gif'), width: imgWidth, class: 'acro'});
                    itemList.adopt(acro);
                    //itemList.adopt('&nbsp;');
                }else{
                    itemList.adopt(new Element('span', {text:' + '}));
                }
                if(e.img){
                    var img = new Element('img', {src: '<c:url value="/static"/>' + e.img, width: imgWidth});
                    itemList.adopt(img);
                    //itemList.adopt('&nbsp;');
                }
                if(e.descr){
                    //itemList.adopt((idl>0)?' + ':'&nbsp;');
                    var descr = new Element('span', {text:e.descr});
                    itemList.adopt(descr);
                }

            }          
            contList.adopt(itemList);

        }        
        
        function updateScheda(scheda){
            
            try{

                //console.log('upd scheda');
            //$('scheda-col-dx').addClass('preloader');


            //var img = new Asset.image('<c:url value="/static/images/articoli/disegnitecnici/${famigliaFilter.cdvistfam_mPad}${tipologiaFilter.cdvisttp_m}"/>'+ '  ' + $('cdvistv1').get('value').pad(3)+$('cdvistv2').get('value').pad(1)+$('cdvistv3').get('value').pad(2)+'-.jpg', {id: 'disTecBig', onerror: loadImgE    rror});
                var pathImg = '<c:url value="/static/images/articoli/disegnitecnici/${(useSpeclist)?'po/':''}"/>';
                pathImg = stripJsession(pathImg);
                var img = new Asset.image(pathImg + $('vist_filedis').get('value') +'.jpg', {id: 'disTecBig', onerror: loadImgError});


                //reset
                $('art-image').empty().adopt(img);
                $('capt-image').empty();
                $('dati-tecnici').setStyle('display', 'none');
                $('file-dwl').empty();
                $('file-dwl-2').empty();
                $('art-cnt').setStyle('display', 'block');
                $('art-tab').active = true;
                $('art-tab').removeClass('current');
                $('art-detail').empty();
                $('art-lamp').empty();
                $('titcollezione').empty();
                <security:authorize ifNotGranted="ROLE_ANONYMOUS">
                $('ric-cnt').setStyle('display', 'none');
                if($('ric-detail')) $('ric-detail').empty();
                $('sel_ric').empty();
                $('ric-tab').active = true;
                $('ric-tab').removeClass('current');
                </security:authorize>
                disTecSlide.slideIn();
                
                //update drop downs
                if(scheda.vetro && !(scheda.articolo && scheda.articolo.cdvisttp == 'PB')){
                    updateDD(scheda, 'vetro', 'sel_vetro', 'cdvistvetro', 'cdvistvetro', '${dsvetro}');
                }
                if(scheda.elettrificazioni && !(scheda.articolo && scheda.articolo.cdvisttp == 'PB')){
                    //console.log('elettrificazioni');
                
                    <c:set var="cdvistelet" value="${(useSpeclist)?'cdul':'cdvistelet'}"/>
                    <c:set var="dselet" value="${(useSpeclist)?'cdul':dselet}"/>
                    updateDD(scheda, 'elettrificazioni', 'sel_elettrificazioni', '${cdvistelet}', 'cdvistelet', '${dselet}', false);
                }
                if(scheda.finit_mont && !(scheda.finit_mont.length==1 && scheda.finit_mont[0].cdvistfinm==null)){
                    updateDD(scheda, 'finit_mont', 'sel_finm', 'cdvistfinm', 'cdvistfinm', '${dsfinm}', false);
                }
                            //update art details
                            if(scheda.articoli){
                                if(scheda.articoli.length > 0){

                                var foundArt = (scheda.articoli.length==1);
                                var plu = (foundArt)?'<spring:message code="singular.m" text="o"/>':'<spring:message code="plural" text="i"/>';
                                    var s_articoli = '<spring:message code="articoli" text="articol" arguments=" "/>'.trim();
                                    var msg;
                                    if(foundArt){
                                        
                                        msg = new Element('p', {
                                        'class': 'art-det-msg',
                                            'html': '<span><spring:message code="art.cdartm" text="Codice articolo"/></span> '+ scheda.articolo.cdartm
                                            <%--'html': '<span><spring:message code="art.cdartm" text="Codice articolo"/></span> <a href="<spring:url value="/specsheet/"/>' + scheda.articolo.cdartm + '">'+ scheda.articolo.cdartm + '</a>'--%>
                                        
                                        });
                                        
                                        
                                        
                                    }else{
                                        
                                        msg = new Element('p', {
                                        'class': 'art-det-msg',
                                            'text': scheda.articoli.length + ' '+ s_articoli + plu + ' <spring:message code="selected.params" text="con i parametri selezionati"/>'
                                        });
                                        
                                        if($('art-detail')) $('art-detail').empty();

                                    }
                                    $('art-code').empty().adopt(msg);
                                    if(foundArt){

                                        $('art-tab').set('opacity', 1).addClass('current');

                                        var art = scheda.articoli[0];
                                        
                                        if(art.cdvisttp == 'PB'){
                                            var descr = art.dsarti;
                                            $('art-txt').set('text', descr.toLowerCase().capitalize());
                                        } 

                                        //change image if different from standard for this article
                                        if(art.vist_filedis!=null){
                                            var src = $('disTecBig').get('src');
                                            src = src.substring(src.lastIndexOf('/')+1, src.lastIndexOf('.'));
                                            if(art.vist_filedis!=src){
                                                var path = stripJsession('<c:url value="/static/images/articoli/disegnitecnici/${(useSpeclist)?'po/':''}"/>');
                                                img = new Asset.image(path + art.vist_filedis +'.jpg', {id: 'disTecBig', onerror: loadImgError});

                                            $('art-image').empty().adopt(img);

                                            }

                                        }




                                        var uri = '<c:url value="/carrelloItem-{cdarti}/"/>';
                                        var cd = {cdarti: art.cdarti};

                                        $('capt-image').empty();
                                        var allready = new Object(); 
                                        if(scheda.etichetteDatiExtra && scheda.etichetteDatiExtra.length > 0){
                                            for(var x=0; x<scheda.etichetteDatiExtra.length; x++ ){
                                                
                                                var ico = new Element('img', {src: '<c:url value="/static/"/>'+ scheda.etichetteDatiExtra[x]})
                                                $('capt-image').adopt(ico);

                                                /*var e = scheda.etichette[x];

                                                for(var i=1; i<7; i++){
                                                    for(var j=1; j < 7; j++){
                                                    if($defined(e['simbolo'+i+"_"+j]) && e['simbolo'+i+"_"+j]!=''){
                                                            var label = e['simbolo'+i+"_"+j];
                                                            if(!allready[label]){
                                                                var ico = new Element('img', {src: '<c:url value="/static/images/articoli/dati/"/>'+ label +'.gif'})
                                                                $('capt-image').adopt(ico);
                                                                allready[label] =true;
                                                            }
                                                        }
                                                    }
                                                }*/
                                            }
                                            
                                            $('dati-tecnici').setStyle('display', 'block');
                                        }

                                        $('file-dwl').empty();
                                        $('file-dwl-2').empty();
                                        if(scheda.articolo!=null){
                                            var tit = new Element('h2', {text: '<spring:message code="downloads" text="Downloads"/>'});
                                            $('file-dwl').adopt(tit);
                                            
                                            
                                            <spring:message code="istruzioni_montaggio" text="Istruzioni di montaggio" var="istrLabel"/>;
                                            <spring:message code="modelli_3D" text="Modelli 3D" var="m3DLabel"/>;
                                            <spring:message code="modelli_2D" text="Modelli 2D" var="m2DLabel"/>;
                                            <spring:message code="specsheet" text="Specsheet" var="specSheetLabel"/>;
                                            <spring:message code="energy_class" text="Energy class" var="energyClassLabel"/>;
                                            var dlink_class = '';
                                            var titletipz = '';
                                            var lang = '&lang=${rc.locale}';
                                            var cdling = '&cdling=${cdling}';
                                            var origine_richiesta = 'origine_richiesta=';
                                            var tkutente_rif = '';
                                            var direct = false;
                                            
                                            var form_req_pars = '&dscontatto=Generico' +
                                                                '&email=generico@vistosi.it' +
                                                                '&ragsoc=Generico' +
                                                                '&citta=Mogliano Veneto' +
                                                                '&cdnazi=IT'    +
                                                                '&fg_rivend_o_prof=S' +
                                                                '&fg_no_notif=S';
                                            
                                            //origine_richiesta = PUBBLICA / SHOP
                                            //tipo_richiesta = no_res / si_res
                                            //tkutente_rif = user.tkutente
                                            var downloadRequestUrl = '${eprogenUrl}epRichiesta_risorse_pubblica_ajax.jsp?';
                                            var downloadUrl = '${portalUrl}download/';
                                            <security:authorize ifAllGranted="ROLE_ANONYMOUS">
                                                //var downloadUrl = 'http://www.vistosi.it/downloadform?file=';
                                                var downloadUrlForm = '${eprogenUrl}epRichiesta_risorse_pubblica_form.jsp?';
                                                //var downloadUrl = '${eprogenUrl}epRichiesta_risorse_pubblica_ajax.jsp?';
                                                origine_richiesta = origine_richiesta+'PUBBLICA';
                                                //http://nbavendramin:8080/eprogen_vistosi/epRichiesta_risorse_pubblica_form.jsp?
                                                //tipo_richiesta=no_res
                                                //&file_req=fileresources/assembling_instructions/IM|SP|24PEA||||G9
                                                //&dsfile=doc.dsfile.ISTRMONT%2024PEARLS%20SP%20G9%20
                                                //&finto=360137425
                                                dlink_class = 'downloadlink';
                                            </security:authorize>
                                            <security:authorize ifNotGranted="ROLE_ANONYMOUS">
                                                //var downloadUrl = 'http://www.vistosi.it/download/?f=';
                                                origine_richiesta = origine_richiesta+'SHOP';
                                                tkutente_rif = '&tkutente_rif=<security:authentication property="principal.userDB.tkutente"/>';
                                            </security:authorize>
                                              
                                            var pars = '&' + origine_richiesta + lang + cdling + tkutente_rif;
                                            
                                            var descrFile = "${famiglia[dsfam]} "+ scheda.articolo.cdvisttp +
                                                    ' ' + (scheda.articolo.cdvistv1 && scheda.articolo.vist_var1?scheda.articolo.vist_var1['${dsv1}'].toUpperCase():'') + 
                                                    ' ' + (scheda.articolo.cdvistv2 && scheda.articolo.vist_var2?scheda.articolo.vist_var2['${dsv2}'].toUpperCase():'') + 
                                                    ' ' + (scheda.articolo.cdvistv3 && scheda.articolo.vist_var3?scheda.articolo.vist_var3['${dsv3}'].toUpperCase():'') + 
                                                    ' ' + (scheda.articolo.cdvistelet && scheda.articolo.vist_elettrificazioni?scheda.articolo.vist_elettrificazioni['${dselet}'].toUpperCase():'');
                                            
                                            
                                            var tipo_richiesta, file, url, proxyurl;
                                            //if(scheda.articolo.vist_articoli_img){
                                            tipo_richiesta = '&tipo_richiesta=' + (scheda.articolo.vist_articoli_img && scheda.articolo.vist_articoli_img.pathschtecExists?'si_res':'no_res');
                                            if(scheda.articolo.vist_articoli_img){
                                                file = '&file_req=fileresources/assembling_instructions/'+scheda.articolo.vist_articoli_img.pathschtec;
                                            }else{
                                                file = '&file_req=fileresources/assembling_instructions/'+ 'IM' + '|' + (scheda.articolo.cdvisttp?scheda.articolo.cdvisttp:'') + '|' + (scheda.articolo.cdvistfam?scheda.articolo.cdvistfam:'') + '|' + (scheda.articolo.cdvistv1?scheda.articolo.cdvistv1:'') + '|' + (scheda.articolo.cdvistv2?scheda.articolo.cdvistv2:'') + '|' + (scheda.articolo.cdvistv3?scheda.articolo.cdvistv3:'') + '|'  + (scheda.articolo.cdvistelet?scheda.articolo.cdvistelet:'');
                                            }
                                            titletipz = "<spring:message code="msg_file_exist" arguments="${istrLabel}" text="Disponibile. Clicca per effettuare il download."/>";

                                            <security:authorize ifAllGranted="ROLE_ANONYMOUS">
                                                if(scheda.articolo.vist_articoli_img && scheda.articolo.vist_articoli_img.pathschtecExists){
                                                    url = downloadUrl+ scheda.articolo.vist_articoli_img.pathschtec + '?f=fileresources/assembling_instructions/' + scheda.articolo.vist_articoli_img.pathschtec;
                                                    proxyurl = downloadRequestUrl+ pars + form_req_pars + tipo_richiesta + file + '&dsfile=' + '${istrLabel}' + ' ' + descrFile;
                                                    dlink_class = 'downloadproxylink';
                                                }else{
                                                    url = downloadUrlForm + pars +tipo_richiesta + file + '&dsfile=' + '${istrLabel}' + ' ' + descrFile;
                                                    //if(!scheda.articolo.vist_articoli_img || !scheda.articolo.vist_articoli_img.pathschtecExists){
                                                    titletipz = "<spring:message code="msg_file_no_exist" arguments="${istrLabel}" text="Non disponibile. Clicca qui per richiederlo."/>"; 
                                                    //}
                                                }
                                            </security:authorize>
                                            <security:authorize ifNotGranted="ROLE_ANONYMOUS">
                                            if(scheda.articolo.vist_articoli_img && scheda.articolo.vist_articoli_img.pathschtecExists){
                                                url = downloadUrl+ scheda.articolo.vist_articoli_img.pathschtec + '?f=fileresources/assembling_instructions/' + scheda.articolo.vist_articoli_img.pathschtec;
                                                proxyurl = downloadRequestUrl+ pars +tipo_richiesta + file + '&dsfile=' + '${istrLabel}' + ' ' + descrFile;
                                                dlink_class = 'downloadproxylink';
                                            }else{
                                                url = downloadRequestUrl+ pars +tipo_richiesta + file + '&dsfile=' + '${istrLabel}' + ' ' + descrFile;
                                                dlink_class = 'downloadreqlink';
                                                proxyurl = '';
                                                titletipz ="<spring:message code="msg_file_no_exist" arguments="${istrLabel}" text="Non disponibile. Clicca qui per richiederlo."/>"; 
                                            }
                                            </security:authorize>
                                            var apdf = new Element('a', {
                                                href: url, 
                                                target: '_blank',
                                                'data-purl': proxyurl,
                                                'class': dlink_class + ' ',
                                                'title': titletipz
                                            });
                                            var ico = new Element('img', {src: (scheda.articolo.vist_articoli_img && scheda.articolo.vist_articoli_img.pathschtecExists?'<c:url value="/static/images/pdf-icon.gif"/>':'<c:url value="/static/images/no-pdf-icon.gif"/>')});
                                            apdf.adopt(ico)
                                            $('file-dwl').adopt(apdf);
                                            //}
                                            //if(scheda.articolo.model2D_dwgExists){
                                            tipo_richiesta = '&tipo_richiesta=' + (scheda.articolo.model2D_dwgExists?'si_res':'no_res');
                                            file = '&file_req='+scheda.articolo.model2D_dwg;  
                                            titletipz = "<spring:message code="msg_file_exist" arguments="${m2DLabel}" text="Disponibile. Clicca per effettuare il download."/>";
                                            <security:authorize ifAllGranted="ROLE_ANONYMOUS">
                                                if(scheda.articolo.model2D_dwgExists){
                                                    url = downloadUrl+ scheda.articolo.model2D_dwg + '?f=' + scheda.articolo.model2D_dwg;
                                                    proxyurl = downloadRequestUrl+ pars + form_req_pars + tipo_richiesta + file + '&dsfile=' + '${m2DLabel}' + ' ' + descrFile;
                                                    dlink_class = 'downloadproxylink';
                                                }else{
                                                    url = downloadUrlForm+ pars +tipo_richiesta + file + '&dsfile=' + '${m2DLabel}' + ' ' + descrFile;
                                                    titletipz = "<spring:message code="msg_file_no_exist" arguments="${m2DLabel}" text="Non disponibile. Clicca qui per richiederlo."/>";                                                    
                                                }                                            
                                            </security:authorize>
                                            <security:authorize ifNotGranted="ROLE_ANONYMOUS">
                                            if(scheda.articolo.model2D_dwgExists){
                                                url = downloadUrl+ scheda.articolo.model2D_dwg + '?f=' + scheda.articolo.model2D_dwg;
                                                proxyurl = downloadRequestUrl+ pars +tipo_richiesta + file + '&dsfile=' + '${m2DLabel}' + ' ' + descrFile;
                                                dlink_class = 'downloadproxylink';
                                            }else{
                                                url = downloadRequestUrl+ pars +tipo_richiesta + file + '&dsfile=' + '${m2DLabel}' + ' ' + descrFile;
                                                dlink_class = 'downloadreqlink';
                                                titletipz = "<spring:message code="msg_file_no_exist" arguments="${m2DLabel}" text="Non disponibile. Clicca qui per richiederlo."/>";
                                            }
                                            </security:authorize>
                                            
                                            
                                            var apdf = new Element('a', {href: url, 
                                                target: '_blank',
                                                'data-purl': proxyurl,
                                                'class': dlink_class + ' ',
                                                'title': titletipz
                                                });
                                                var ico = new Element('img', {src: (scheda.articolo.model2D_dwgExists?'<c:url value="/static/images/dwg-icon.gif"/>':'<c:url value="/static/images/no-dwg-icon.gif"/>')});
                                                apdf.adopt(ico)
                                                $('file-dwl').adopt(apdf);
                                            //}
                                            //if(scheda.articolo.model3D_easmExists){
                                            tipo_richiesta = '&tipo_richiesta=' + (scheda.articolo.model3D_easmExists?'si_res':'no_res');
                                            file = '&file_req='+scheda.articolo.model3D_easm; 
                                            titletipz = "<spring:message code="msg_file_exist" arguments="${m3DLabel}" text="Disponibile. Clicca per effettuare il download."/>";
                                            <security:authorize ifAllGranted="ROLE_ANONYMOUS">
                                                if(scheda.articolo.model3D_easmExists){
                                                    url = downloadUrl+ scheda.articolo.model3D_easm + '?f=' + scheda.articolo.model3D_easm;
                                                    proxyurl = downloadRequestUrl+ pars + form_req_pars + tipo_richiesta + file + '&dsfile=' + '${m3DLabel}' + ' ' + descrFile;
                                                    dlink_class = 'downloadproxylink';
                                                }else{
                                                    url = downloadUrlForm+ pars +tipo_richiesta + file + '&dsfile=' + '${m3DLabel}' + ' ' + descrFile;
                                                    titletipz = "<spring:message code="msg_file_no_exist" arguments="${m3DLabel}" text="Non disponibile. Clicca qui per richiederlo."/>";
                                                } 
                                            </security:authorize>
                                            <security:authorize ifNotGranted="ROLE_ANONYMOUS">
                                            if(scheda.articolo.model3D_easmExists){
                                                url = downloadUrl+ scheda.articolo.model3D_easm + '?f=' + scheda.articolo.model3D_easm;
                                                proxyurl = downloadRequestUrl+ pars +tipo_richiesta + file + '&dsfile=' + '${m3DLabel}' + ' ' + descrFile;
                                                dlink_class = 'downloadproxylink';
                                            }else{
                                                url = downloadRequestUrl+ pars +tipo_richiesta + file + '&dsfile=' + '${m3DLabel}' + ' ' + descrFile;
                                                dlink_class = 'downloadreqlink';
                                                titletipz = "<spring:message code="msg_file_no_exist" arguments="${m3DLabel}" text="Non disponibile. Clicca qui per richiederlo."/>";
                                            }
                                            </security:authorize>                                            
                                            var apdf = new Element('a', {href: url, 
                                                target: '_blank',
                                                'data-purl': proxyurl,
                                                'class': dlink_class + ' ',
                                                'title': titletipz
                                            });
                                                var ico = new Element('img', {src: (scheda.articolo.model3D_easmExists?'<c:url value="/static/images/easm-icon.gif"/>':'<c:url value="/static/images/no-easm-icon.gif"/>')});
                                                apdf.adopt(ico)
                                                $('file-dwl').adopt(apdf);
                                            //}
                                            //if(scheda.articolo.model3D_eprtExists){
                                            /*tipo_richiesta = '&tipo_richiesta=' + (scheda.articolo.model3D_eprtExists?'si_res':'no_res');
                                            file = '&file_req='+scheda.articolo.model3D_eprt; 
                                            titletipz = m3DLabel;
                                            <security:authorize ifAllGranted="ROLE_ANONYMOUS">
                                            url = downloadUrl+ pars +tipo_richiesta + file + '&dsfile=' + m3DLabel + ' ' + descrFile;
                                            if(!scheda.articolo.model3D_eprtExists){
                                                titletipz += ' <spring:message code="resources.no_res" text="non disponibile. Clicca qui per richiederla."/>';
                                            }
                                            </security:authorize>
                                            <security:authorize ifNotGranted="ROLE_ANONYMOUS">
                                            if(scheda.articolo.model3D_eprtExists){
                                                url = downloadUrl+ scheda.articolo.model3D_eprt + '?f=' + scheda.articolo.model3D_eprt;
                                                proxyurl = downloadRequestUrl+ pars +tipo_richiesta + file + '&dsfile=' + m3DLabel + ' ' + descrFile;
                                                dlink_class = 'downloadproxylink';
                                            }else{
                                                url = downloadRequestUrl+ pars +tipo_richiesta + file + '&dsfile=' + m3DLabel + ' ' + descrFile;
                                                dlink_class = 'downloadreqlink';
                                                titletipz += ' <spring:message code="resources.no_res" text="non disponibile. Clicca qui per richiederla."/>';
                                            }
                                            </security:authorize>                                            
                                            var apdf = new Element('a', {href: url, 
                                                target: '_blank',
                                                'data-purl': proxyurl,
                                                'class': dlink_class + ' tipz',
                                                'title': titletipz
                                                });
                                                var ico = new Element('img', {src: (scheda.articolo.model3D_eprtExists?'<c:url value="/static/images/eprt-icon.gif"/>':'<c:url value="/static/images/no-eprt-icon.gif"/>')});
                                                apdf.adopt(ico)
                                                $('file-dwl').adopt(apdf);*/
                                            //}
                                            //if(scheda.articolo.model3D_igsExists){
                                            tipo_richiesta = '&tipo_richiesta=' + (scheda.articolo.model3D_igsExists?'si_res':'no_res');
                                            file = '&file_req='+scheda.articolo.model3D_igs; 
                                            titletipz = "<spring:message code="msg_file_exist" arguments="${m3DLabel}" text="Disponibile. Clicca per effettuare il download."/>";
                                            <security:authorize ifAllGranted="ROLE_ANONYMOUS">
                                                if(scheda.articolo.model3D_igsExists){
                                                    url = downloadUrl+ scheda.articolo.model3D_igs + '?f=' + scheda.articolo.model3D_igs;
                                                    proxyurl = downloadRequestUrl+ pars + form_req_pars + tipo_richiesta + file + '&dsfile=' + '${m3DLabel}' + ' ' + descrFile;
                                                    dlink_class = 'downloadproxylink';
                                                }else{
                                                    url = downloadUrlForm + pars +tipo_richiesta + file + '&dsfile=' + '${m3DLabel}' + ' ' + descrFile;
                                                    titletipz = "<spring:message code="msg_file_no_exist" arguments="${m3DLabel}" text="Non disponibile. Clicca qui per richiederlo."/>";
                                                } 
                                            </security:authorize>
                                            <security:authorize ifNotGranted="ROLE_ANONYMOUS">
                                            if(scheda.articolo.model3D_igsExists){
                                                url = downloadUrl+ scheda.articolo.model3D_igs + '?f=' + scheda.articolo.model3D_igs;
                                                proxyurl = downloadRequestUrl+ pars +tipo_richiesta + file + '&dsfile=' + '${m3DLabel}' + ' ' + descrFile;
                                                dlink_class = 'downloadproxylink';
                                            }else{
                                                url = downloadRequestUrl+ pars +tipo_richiesta + file + '&dsfile=' + '${m3DLabel}' + ' ' + descrFile;
                                                dlink_class = 'downloadreqlink';
                                                titletipz = "<spring:message code="msg_file_no_exist" arguments="${m3DLabel}" text="Non disponibile. Clicca qui per richiederlo."/>";
                                            }
                                            </security:authorize>                                             
                                            var apdf = new Element('a', {href: url, 
                                                target: '_blank',
                                                'data-purl': proxyurl,
                                                'class': dlink_class + ' ',
                                                'title': titletipz
                                            });
                                                var ico = new Element('img', {src: (scheda.articolo.model3D_igsExists?'<c:url value="/static/images/iges-icon.gif"/>':'<c:url value="/static/images/no-iges-icon.gif"/>')});
                                                apdf.adopt(ico)
                                                $('file-dwl').adopt(apdf);
                                            //}
                                            
                                            
                                            tipo_richiesta = '&tipo_richiesta=' + (scheda.articolo.specsheetExists?'si_res':'no_res');
                                            file = '&file_req=specsheet/'+scheda.articolo.cdartm;
                                            titletipz = "<spring:message code="msg_file_exist" arguments="${specSheetLabel}" text="Disponibile. Clicca per effettuare il download."/>";
                                            <security:authorize ifAllGranted="ROLE_ANONYMOUS">
                                                if(scheda.articolo.specsheetExists){
                                                    url = '<c:url value="/specsheet/"/>'+scheda.articolo.cdartm;
                                                    dlink_class = '';
                                                }else{
                                                    url = downloadUrlForm + pars +tipo_richiesta + file + '&dsfile=' + '${specSheetLabel}' + ' ' + descrFile;
                                                    dlink_class = 'downloadlink';
                                                    titletipz = "<spring:message code="msg_file_no_exist" arguments="${specSheetLabel}" text="Non disponibile. Clicca qui per richiederlo."/>";
                                                }
                                            </security:authorize>
                                            <security:authorize ifNotGranted="ROLE_ANONYMOUS">
                                            if(scheda.articolo.specsheetExists){
                                                url = '<c:url value="/specsheet/"/>'+scheda.articolo.cdartm;
                                                dlink_class = '';
                                            }else{
                                                url = downloadRequestUrl+ pars +tipo_richiesta + file + '&dsfile=' + '${specSheetLabel}' + ' ' + descrFile;
                                                dlink_class = 'downloadreqlink';
                                                titletipz = "<spring:message code="msg_file_no_exist" arguments="${specSheetLabel}" text="Non disponibile. Clicca qui per richiederlo."/>";
                                            }
                                            </security:authorize> 
                                            var apdf = new Element('a', {href: url, 
                                                target: '_blank',
                                                'class': dlink_class + ' ',
                                                'title': titletipz
                                            });
                                                var ico = new Element('img', {src: (scheda.articolo.specsheetExists?'<c:url value="/static/images/tech-icon.gif"/>':'<c:url value="/static/images/no-tech-icon.gif"/>')});
                                                apdf.adopt(ico)
                                                $('file-dwl').adopt(apdf);
                                            
                                            if(scheda.articolo.energyClass!=null && scheda.articolo.energyClass!=''){
                                                var apdf = new Element('a', {href: '${eprogenUrl}epRichiesta_download_energyclass.jsp?cdarti='+scheda.articolo.cdarti+'&cdling=${cdling}', 
                                                    target: '_blank',
                                                    'class': 'downloadlink' + ' ',
                                                    'title': "<spring:message code="msg_file_exist" arguments="${energyClassLabel}" text="Disponibile. Clicca per effettuare il download."/>"
                                                });
                                                    var ico = new Element('img', {src: '<c:url value="/static/images/ce-icon.gif"/>'});
                                                    apdf.adopt(ico)
                                                    $('file-dwl').adopt(apdf);
                                            }
                                            
                                            
                                            
                                            
                                            var allDownloadLink = $('file-dwl').getElements('a.downloadlink').each(function(item, index){
                                                item.addEvent('click', function(e){
                                                    e.stop();

                                                    Mediabox.open(item.get('href'), '<spring:message code="title.downloads" text="Downloads"/>', '640 360');
                                                });
                                            });
                                            
                                            $('file-dwl').getElements('a.downloadreqlink').each(function(item, index){
                                                item.addEvent('click', function(e){
                                                    e.stop();
                                                    
                                                    var dataUrl = item.get('href').split('?');
                                                    var _u = dataUrl[0];
                                                    var _q = dataUrl[1];
                                                    var jsonData = _q.cleanQueryString().parseQueryString();
                                                    
                                                    var jsonRequest = new Request.JSON({url: _u, onSuccess: function(data){
                                                        if(data.rc=='1') alert('<spring:message code="doc.js.msg_nores" text="Mail inviata"/>');
                                                    }}).post(jsonData);
                                                });
                                            });
                                            $('file-dwl').getElements('a.downloadproxylink').each(function(item, index){
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
                                            
                                            iniTipz($('file-dwl'));
                                            
                                            //certificazioni
                                            if(scheda.articolo.certList.length>0){
                                                var tit = new Element('h2', {text: '<spring:message code="certifications" text="Certificazioni"/>'});
                                                $('file-dwl-2').adopt(tit);
                                                
                                                scheda.articolo.certList.each(function(el){
                                                    var apdf = new Element('a', {href: '${portalUrl}download/'+ el.file + '?f=' + el.file, 
                                                    target: '_blank',
                                                    'class': dlink_class});
                                                    var ico = new Element('img', {src: '<c:url value="/static/images/articoli/specsheetres/dati/"/>' + el.img + '.jpg', height: '35px'});
                                                    apdf.adopt(ico)
                                                    $('file-dwl-2').adopt(apdf);
                                                });
                                                    
                                            }
                                            
                                            
                                            //lampadine
                                            if(scheda.articolo.eletDatiExtraMap){
                                            
                                                if(scheda.articolo.eletDatiExtraMap['main']){
                                                    
                                                    
                                                    var contList = new Element('ul');
                                                    
                                                    addItemToLampList(contList, scheda.articolo.eletDatiExtraMap['main']);
                                                    
                                                    if(scheda.articolo.eletDatiExtraMap['alt1']){
                                                        
                                                        var htit =  new Element('h4', {text: '<spring:message code="lampadine.alt" text="Alternative"/>'});
                                                        var subcnt =  new Element('div');       
                                                        subcnt.adopt(htit);
                                                        contList.adopt(subcnt);
    
                                                        var subLI = new Element('li');  
                                                        var subUL = new Element('ul');
                                                        
                                                        addItemToLampList(subUL, scheda.articolo.eletDatiExtraMap['alt1']);
                                                        
                                                        if(scheda.articolo.eletDatiExtraMap['alt2']){
                                                            addItemToLampList(subUL, scheda.articolo.eletDatiExtraMap['alt2']);
                                                        }
                                                        
                                                        
                                                        
                                                        subLI.adopt(subUL);
                                                        
                                                        subcnt.adopt(subLI);
                                                        
                                                    }
                                                    
                                                    $('art-lamp').adopt(contList);
                                                    
                                                }
                                                
                                            }
                                            
                                            
                                            //technews
                                            <security:authorize ifNotGranted="ROLE_ANONYMOUS">
                                            if(scheda.techNewsList.length>0){ //modifico link solo se technews generica non c'era o se tk diverso
                                                    var $div = $('techNews-list'); 
                                                    if($div){
                                                        $div.empty();
                                                    }
                                                    /*else{
                                                        $div =  new Element('li', {id: 'techNews'});
                                                    }*/
                                                    //scheda.techNewsList.each(function(el){
                                                    var el = scheda.techNewsList[0];
                                                    var li = new Element('li', {'data-tk': el.tkpost});
                                                    var tn = new Element('a', {href: '${portalUrl}techNews/' + (new Date(el.postDate)).format('%Y/%m/%d/') + el.tkpost + '?locale=${rc.locale}&cdvistfam=${famigliaFilter.cdvistfam}&cdvisttp=${tipologiaFilter.cdvisttp}'+(scheda.articolo!=null?'&cdvistelet='+scheda.articolo.cdvistelet:''), 
                                                    target: '_blank'});
                                                    var ico = new Element('img', {src: '<c:url value="/static/images/news-icon.gif"/>', alt: 'tech news'});
                                                    tn.adopt(ico);
                                                    li.adopt(tn);
                                                    if(scheda.techNewsList.length>1){
                                                        var bubble = new Element('div', {'class': 'notify-bubble', text: scheda.techNewsList.length});
                                                         li.adopt(bubble);
                                                    }   

                                                    $div.adopt(li);
                                                    //});
                                                    //$('modello').grab($div, 'top');                                                        
                                                    
                                                }else{
                                                    var $div = $('techNews-list'); 
                                                    if($div){
                                                        $div.empty();
                                                    }
                                                }
                                            </security:authorize>                                            
                                            
                                            if(scheda.vist_cp_collezioni){
                                                $('titcollezione').set('html', scheda.vist_cp_collezioni.${dsvistccol});
                                            }  
                                            
                                            
                                            $$('a.art-thumb').each(function(el) {
                                                
                                                var href = el.get('href');
                                                var dis = href.substr(href.lastIndexOf("|")+1);
                                                
                                                if(dis==scheda.articolo.vist_filedis){
                                                    el.getParent().addClass('current');
                                                    if($defined(disActive)){
                                                        disActive.getElement('.clickedOverlay').setStyle('display', 'none');
                                                    }
                                                    el.getParent().getElement('.clickedOverlay').setStyle('display', 'block');
                                                    disActive = el.getParent();
                                                }else{
                                                    el.getParent().removeClass('current');
                                                }
                                                
                                            });
                                            
                                        }
                                        <security:authorize ifNotGranted="ROLE_ANONYMOUS">
                                            if(scheda.ricambi && scheda.ricambi.length > 0){
                                                updateDD(scheda, 'ricambi', 'sel_ric', 'cdarti', 'cdartiric', '${dsext}');
                                                $('ric-tab').setStyle('display', 'block');
                                                $('ric-tab').set('opacity', 1);
                                                $('ric-tab').active = true;
                                                $('art-tab').active = true;
                                                var path = stripJsession('<c:url value="/static/images/articoli/disegnitecnici/${(useSpeclist)?'po/':''}"/>');
                                                var img = new Asset.image(path + scheda.ricambi[0].datiRicambio.imgv_filename, {id: 'disTecRicBig', onerror: loadImgError});
                                                $('art-image-ric').empty().adopt(img);


                                                if(scheda.ricambi.length == 1){
                                                    
                                                    //var img = new Asset.image(path + scheda.ricambi[0].datiRicambio.imgv_filename, {id: 'disTecRicBig', onerror: loadImgError});
                                                    //$('art-image-ric').empty().adopt(img);

                                                    var urlCartItem = uri + "?cdartirif={cdartirif}"
                                                    var cdRic = {cdarti: scheda.ricambi[0].cdarti, cdartirif: art.cdarti};
                                                    <security:authorize ifNotGranted="ROLE_ANONYMOUS">
                                                                    $('ric-detail').load(urlCartItem.substitute(cdRic));
                                                    </security:authorize>
                                                }else if(scheda.ricambio != null){

                                                            if(scheda.ricambio.datiRicambio){
                                                                var path = stripJsession('<c:url value="/static/images/articoli/disegnitecnici/${(useSpeclist)?'po/':''}"/>');
                                                                var img = new Asset.image(path + scheda.ricambio.datiRicambio.imgv_filename, {id: 'disTecRicBig', onerror: loadImgError});
                                                                $('art-image-ric').empty().adopt(img);
                                                            }

                                                            $('sel_ric').set('value', scheda.ricambio.cdarti);
                                                            var urlCartItem = uri + "?cdartirif={cdartirif}"
                                                            var cdRic = {cdarti: scheda.ricambio.cdarti, cdartirif: art.cdarti};
                                                            <security:authorize ifNotGranted="ROLE_ANONYMOUS">
                                                            $('ric-detail').load(urlCartItem.substitute(cdRic));
                                                            </security:authorize>

                                                            //$('art-tab').removeClass('current');
                                                            if ($('ric-tab').hasClass('current')){
                                                                $('art-cnt').setStyle('display', 'none');
                                                                $('ric-cnt').setStyle('display', 'block');
                                                            }
                                                    }
                                            }else{
                                                $('ric-tab').setStyle('display', 'none');
                                            }
                                             </security:authorize>
                                             
                                             <%--security:authorize ifNotGranted="ROLE_ANONYMOUS"--%>
                                                        //console.log(uri.substitute(cd));
                                                $('art-detail').load(uri.substitute(cd));
                                             <%--/security:authorize--%>

                                            }else{
                                                $('art-tab').set('opacity', 1).addClass('current');
                                                //console.log('empty ric 1')
                                            }
                                        }else{
                                            //console.log('empty ric 2')
                                        
                                            
                                            var msg = new Element('p', {
                                                'text': '<spring:message code="art.notfound" text="nessun articolo trovato"/> <spring:message code="selected.params" text="con i parametri selezionati"/>'
                                            });

                                            $('art-tab').set('opacity', 1).addClass('current');
                                            $('art-code').empty().adopt(msg);
                                        }
                                    }else{
                                        //al primo accesso alla scheda posticipo in una seconda request la ricerca
                                        //degli articoli
                                        //
                                        //TODO verificare a cosa servisse
                                        //
                                        sendFormData();
                                        //console.log('empty ric 3')
                                        $('art-tab').set('opacity', 1).addClass('current');
                                    }
                                    schWaiter.hide();
                                    
                                }catch(err){
                                    console.log(err)
                                    schWaiter.hide();
                                }
        
                            }

                        var captionTpl = '<span class="cap-lab"><spring:message code="model" text="Modello"/></span>: <span class="cap-val">{modello}</span><span class="cap-lab"> / <spring:message code="varianti" text="Vartianti"/></span>: <span class="cap-val">{    variante}</span>'
                            + '<span class="cap-lab"> / <spring:message code="col.vetro" text="Colore vetro"/></span>: <span class="cap-val">{colore}</span><span class="cap-lab"> / <spring:message code="mont" text="Montatura"/></span>: <span class="cap-val">{m    ontatura}</span>'
                                + '<span class="cap-lab"> / <spring:message code="elettr" text="Elettrificazione"/></span>: <span class="cap-val">{elettrificazione}</span> ';


                        window.addEvent('domready', function(){
                                Clientcide.setAssetLocation("<c:url value='/static/js/Assets' />");
                                schWaiter = new Spinner('scheda-col-dx');
                                <security:authorize ifNotGranted="ROLE_ANONYMOUS">
                                $('ric-cnt').setStyle('display', 'none');
                                </security:authorize>
                            });
</script>
<c:choose>
    <c:when test="${!empty scheda}">
        <c:set var="pagination">
            <div class="pagination">
                <div class="page-links"><a href="${header['referer']}">< <fmt:message key="back"/></a></div>${tipologiaFilter[dstp]} > ${famigliaFilter[dsfam]}
            </div>
        </c:set>
        ${pagination}
        
        <div class="clearfix articoli-list-cnt" style="clear: both">
            <h2><spring:message code="choose.model" text="Scegli modello"/></h2>

            <div id="articoli-list">

                <c:forEach items="${modellidis}" var="t" varStatus="s">


                    <div id="thumb${t.cdvistv1}${t.cdvistv2}${t.cdvistv3}" rel="${famigliaFilter[dsfam]} ${fn:replace(fn:substringBefore(t.vist_filedis, "-"), famigliaFilter.cdvistfam, '')}" class="dis-item">
                        <a href="#${t.cdvistv1}|${t.cdvistv2}|${t.cdvistv3}|${t.cdvistcolv}|${t.cdvistfinv}|${t.cdvistfinm}|${t.cdvistelet}|${t.vist_filedis}" class="tipz art-thumb" title="::${fn:substringBefore(t.vist_filedis, "-")}" rel="${fn:replace(t.vist_filedis, " ", "")}">
                            <img src="<c:url value='/static/images/articoli/disegnitecnici/thumb/${(useSpeclist)?"po/":""}${t.vist_filedis}.jpg'/>" title=""/>
                            <div class="clickedOverlay"></div>
                        </a>                            
                    </div>
                    <c:if test='${t.cdvistv1==articoli[0].cdvistv1 && t.cdvistv2==articoli[0].cdvistv2 && t.cdvistv3==articoli[0].cdvistv3}'><script type="text/javascript">disClick = $('thumb${t.cdvistv1}${t.cdvistv2}${t.cdvistv3}');</script></c:if>
                </c:forEach>
                <script type="text/javascript">

                    function iniTipz(cnt){

                        $$('div.tipzart').destroy();

                        var classname = 'tipzart';
                        if(cnt==null) {
                            cnt = $(document.body);
                            classname = 'tipz';
                        }

                        //store titles and text
                        cnt.getElements('a.tipz').each(function(element,index) {
                            var content = (element.get('title')!=null) ? element.get('title').split('::'): ['',''];
                            element.store('tip:title', content[0]);
                            element.store('tip:text', content[1]);
                        });

                        //create the tooltips                          
                        cnt.getElements('a.tipz').each(function(element, index) {
                            var title = (element.get('title')!=null) ? element.get('title'):'';
                            var offx = Math.round((title.length*5)/2);

                            var tipz = new Tips(element,{
                                'className': classname,
                                fixed: false,
                                offset: {'x': -offx, 'y': 25},
                                hideDelay: 30,
                                showDelay: 30
                            });

                            tipz.addEvent('show', function(tip, el){
                                tip.fade('in');
                            });

                            tipz.addEvent('hide', function(tip, el){
                                tip.fade('out');
                            });                                
                        });


                    }


                    window.addEvent('domready', function() {

                        $('addthis_cnt').hide().fade('out');
                        $('foto-art').hide().fade('out');
                        
                        iniTipz();


                        var disBtns = $$('div.dis-item');
                        var preserve = false;
                        <c:if test="${!empty param.cdvistelet}">preserve = true;</c:if>
                                
                        disBtns.each(function(element, index) {

                            element.addEvents({
                                'click' : function(event){
                                    if(event) event.stop();
                                    
                                    $('addthis_cnt').show().fade('in');
                                    $('foto-art').show().fade('in');
                                    
                                    if($defined(disActive)){
                                        disActive.getElement('.clickedOverlay').setStyle('display', 'none');
                                    }
                                    this.getElement('.clickedOverlay').setStyle('display', 'block');
                                    disActive = this;

                                    //activate photo if present
                                    var desThumbId = element.get('id');
                                    desThumbId = "_" + desThumbId.replace('thumb', '');
                                    var ambs = $('photo-link').getElements('a[id$='+desThumbId+']');
                                    if(ambs!=null && ambs.length>0){
                                        var fotoToShow = ambs[0];
                                        fotoToShow.fireEvent('click', event);
                                    }else{
                                        $('foto').set('src','<c:url value="/static/images/articoli/foto/nd.jpg"/>');
                                        if(fotoClick!=null){
                                            fotoClick.removeClass('current');
                                            fotoClick = null;
                                            $('caption').empty().setStyle('display', 'none');
                                        }

                                    }
                                    if(disClick!=null){
                                        disClick.removeClass('current');
                                    }
                                    disClick = element;
                                    if($defined(disClick)) disClick.addClass('current');


                                    var href = this.getFirst('a').get('href').substring(1);
                                    var varArray = href.split('|',8);
                                    //console.log(varArray[3].length)
                                    //console.log(varArray[4].length)
                                    $('sel_modello').set('value', varArray[0]+'|'+varArray[1]+'|'+varArray[2]);


                                    $('cdvistv1').set('value', varArray[0]);
                                    $('cdvistv2').set('value', varArray[1]);
                                    $('cdvistv3').set('value', varArray[2]);

                                    $('cdvistvetro').set('value', (varArray[3].length>0 || varArray[4].length>0) ? varArray[3]+'|'+varArray[4]:'');
                                    $('cdvistcolv').set('value', varArray[3]);
                                    $('cdvistfinv').set('value', varArray[4]);
                                    $('cdvistfinm').set('value', varArray[5]);
                                    if(preserve){
                                        preserve = false;
                                    }else{
                                        $('cdvistelet').set('value', varArray[6]);
                                    }
                                    
                                    $('vist_filedis').set('value', varArray[7]);

                                    $('cdartiric').set('value', '');

                                    $('titmodello').set('html', this.get('rel'));
                                    
                                    sendFormData(true);
                                    //loadArticoloData(this.getFirst('a'));


                                }
                            });

                        });

                        disTecSlide = new Fx.Slide('art-image');
                        disTecSlide.hide();

                        <c:if test="${!empty articolo}">

                            $('sel_modello').set('value', '${articolo.cdvistv1}|${articolo.cdvistv2}|${articolo.cdvistv3}');
                            $('cdvistv1').set('value', '${articolo.cdvistv1}');
                            $('cdvistv2').set('value', '${articolo.cdvistv2}');
                            $('cdvistv3').set('value', '${articolo.cdvistv3}');

                            $('cdvistvetro').set('value', '${articolo.cdvistcolv}|${articolo.cdvistfinv}');
                            $('cdvistfinm').set('value','${articolo.cdvistfinm}');
                            $('cdvistfinv').set('value','${articolo.cdvistfinv}');
                            $('cdvistcolv').set('value','${articolo.cdvistcolv}');
                                $('cdvistelet').set('value', '${articolo.cdvistelet}');

                            <c:if test="${!empty ricambio}">
                                    $('cdartiric').set('value', '${ricambio.cdarti}');
                        </c:if>
                            $('art-tab').addClass('current');
                            sendFormData(true);

                    </c:if>

                        <c:if test="${empty articolo && !empty ricambio}">


                            $('sel_modello').set('value', '${ricambio.cdvistv1}|${ricambio.cdvistv2}|${ricambio.cdvistv3}');

                            $('cdvistv1').set('value', '${ricambio.cdvistv1}');
                            $('cdvistv2').set('value', '${ricambio.cdvistv2}');
                            $('cdvistv3').set('value', '${ricambio.cdvistv3}');

                            $('cdvistvetro').set('value', '${ricambio.cdvistcolv}|${ricambio.cdvistfinv}');
                            $('cdvistfinm').set('value','${ricambio.cdvistfinm}');
                            $('cdvistfinv').set('value','${ricambio.cdvistfinv}');
                            $('cdvistcolv').set('value','${ricambio.cdvistcolv}');
                            $('cdvistelet').set('value','${ricambio.cdvistelet}');

                                $('cdartiric').set('value', '${ricambio.cdarti}');

                                sendFormData(true);

                    </c:if>

                                $('art-tab').addEvent('click', function(){
                                    if(this.active){
                                        this.addClass('current');
                                        <security:authorize ifNotGranted="ROLE_ANONYMOUS">$('ric-tab').removeClass('current');</security:authorize>
                                        $('art-cnt').setStyle('display', 'block');
                                        $('ric-cnt').setStyle('display', 'none');
                                    }
                                });
                                <security:authorize ifNotGranted="ROLE_ANONYMOUS">
                                $('ric-tab').addEvent('click', function(){
                                    if(this.active){
                                        this.addClass('current').set('opacity',1);
                                        $('art-tab').removeClass('current');
                                        $('ric-cnt').setStyle('display', 'block');
                                        $('art-cnt').setStyle('display', 'none');
                                    }
                                });
                                </security:authorize>

                    <c:choose>
                        <c:when test="${empty articolo && !isRicambio}">
                                    //$('art-tab').set('opacity',0.5);
                                    $('art-tab').active = false;
                                    <security:authorize ifNotGranted="ROLE_ANONYMOUS">
                                    //$('ric-tab').set('opacity',0.5);
                                    $('ric-tab').active = false;
                                    </security:authorize>
                        </c:when>
                        <c:when test="${isRicambio}">
                                    $('art-tab').active = true;
                                    <security:authorize ifNotGranted="ROLE_ANONYMOUS">
                                    $('ric-tab').active = true;
                                    $('ric-tab').addClass('current');
                                    </security:authorize>
                        </c:when>
                        <c:otherwise>
                                    <security:authorize ifNotGranted="ROLE_ANONYMOUS">
                                    //$('ric-tab').set('opacity',0.5);
                                    $('ric-tab').active = true;
                                    </security:authorize>
                                    $('art-tab').addClass('current');
                        </c:otherwise>
                    </c:choose>



                        if(disBtns.length>0){
                            <c:choose>
                                <c:when test="${!empty param.model}">

                                    var disModels = $('articoli-list').getElements('a[rel$=${param.model}]');

                                    if(disModels.length>0){
                                        var bt = disModels[0];
                                        bt.getParent('div').fireEvent('click');
                                    }

                                </c:when>
                                <c:otherwise>
                                    if(disBtns.length==1){
                                        disBtns[0].fireEvent('click');
                                    }
                                        <%--
                                        var bt = var bt = disBtns[0];;

                                        <c:if test="${empty articolo && empty ricambio}">
                                            bt.fireEvent('click');
                                        </c:if>
                                        --%>
                                </c:otherwise>
                            </c:choose>

                        }
                        
                        

                            });


                </script>
            </div>
        </div>        
        
        
        
        <div id="scheda-col-sx">
            <div id="dati-art">
                <h2>${!empty famiglia[dsfam]?famiglia[dsfam]:famiglia['dsvistfam_eng']}</h2>
                <c:if test="${!empty designer}">
                    <p class="designer">Design 
                        <c:choose>
                            <c:when test="${!empty designer[dsdesignerlang]}"><a href="/designers/${designer.cddesigner}" title="${designer.dsdesigner}" target="_blank">${fn:toLowerCase(designer.dsdesigner)}</a></c:when>
                            <c:otherwise>${fn:toLowerCase(designer.dsdesigner)}</c:otherwise>
                        </c:choose>
                    </p>
                </c:if>
                ${ep:formatHtmlDescription((!empty famiglia[dsextfam]?famiglia[dsextfam]:famiglia['dsextvistfam_eng']), ".", "p")}
            </div>
            <div id="addthis_cnt" class="addthis_cnt">
                <c:set var="at_title" value="${famiglia[dsfam]}" />
                <c:set var="at_content" value="${ep:abbreviateString(famiglia[dsextfam], 300)}" />
                <c:set var="at_img"><c:url value="/static/images/articoli/foto/${fn:replace(articoli[0].cdartm,'/', '-')}.jpg"/></c:set>
                <jsp:directive.include file="/WEB-INF/jspf/addthis.jspf"/>                
            </div>
            <div id="foto-art" class="">
                <c:choose>
                    <c:when test="${!empty articoli}">
                        <div id="img-cnt">
                            <img src="<c:url value="/static/images/articoli/foto/${fn:replace(articoli[0].cdartm,'/', '-')}.jpg"/>" name="foto" id="foto" alt="${articoli[0].cdartm}"/>
                            <div id="caption">
                                <%--${articoli[0][dsext]}--%>
                                <%--span style="text-transform: capitalize">${fn:toLowerCase(articoli[0].vist_famiglia[dsfam])}</span> ${articoli[0].vist_tipi[dstp]} ${articoli[0].vist_var1[dsv1]} ${articoli[0].vist_var2[dsv2]} ${articoli[0].vist_var3[dsv3]} ${articoli[0].vist_colori_vetro[dscolv]} ${articoli[0].vist_finit_vetro[dscolv]}--%>
                            </div>
                            <script type="text/javascript">
                    var capObj = {modello: "${fn:toLowerCase(!empty articoli[0].vist_famiglia[dsfam]?articoli[0].vist_famiglia[dsfam]:articoli[0].vist_famiglia['dsvistfam_eng'])} ${fn:toLowerCase(articoli[0].vist_tipi[dstp])}",
                        variante: '${fn:toLowerCase((!empty articoli[0].vist_var1)?articoli[0].vist_var1[dsv1]:"")} ${fn:toLowerCase((!empty articoli[0].vist_var2)?articoli[0].vist_var2[dsv2]:"")} ${fn:toLowerCase((!empty articoli[0].vist_var3)?articoli[0].vist_var3[dsv3]:"")}',
                        colore: '${fn:toLowerCase((!empty articoli[0].vist_colori_vetro)?articoli[0].vist_colori_vetro[dscolv]:"")} ${fn:toLowerCase((!empty articoli[0].vist_finit_vetro)?articoli[0].vist_finit_vetro[dsfinv]:"")}',
                        montatura:'${fn:toLowerCase((!empty articoli[0].vist_finit_mont)?articoli[0].vist_finit_mont[dsfinm]:"")}',
                        elettrificazione: '${fn:toLowerCase((!empty articoli[0].vist_elettrificazioni)?articoli[0].vist_elettrificazioni[dselet]:"")}'};
                    
                    $('caption').set('html', captionTpl.substitute(capObj));
                            </script>
                        </div>
                        <div id="photo-link">
                            <c:forEach items="${articoli}" var="t" varStatus="s">
                                <a id="${t.cdarti}_${t.cdvistv1}${t.cdvistv2}${t.cdvistv3}" href="<c:url value="/static/images/articoli/foto/${fn:replace(t.cdartm, '/', '-')}.jpg"/>" title="${fn:toLowerCase(t.vist_famiglia[dsfam])} ${fn:toLowerCase(t.vist_tipi[dstp])}::${fn:toLowerCase((!empty t.vist_var1)?t.vist_var1[dsv1]:"")} ${fn:toLowerCase((!empty t.vist_var2)?t.vist_var2[dsv2]:"")} ${fn:toLowerCase((!empty t.vist_var3)?t.vist_var3[dsv3]:"")}::${fn:toLowerCase((!empty t.vist_colori_vetro)?t.vist_colori_vetro[dscolv]:"")} ${fn:toLowerCase((!empty t.vist_finit_vetro)?t.vist_finit_vetro[dsfinv]:"")}::${fn:toLowerCase((!empty t.vist_finit_mont)?t.vist_finit_mont[dsfinm]:"")}::${fn:toLowerCase((!empty t.vist_elettrificazioni)?t.vist_elettrificazioni[dselet]:"")}" class="fotolink <c:if test='${s.count==1}'>current</c:if>"><img src="<c:url value="/static/images/articoli/foto/${fn:replace(t.cdartm, '/', '-')}.jpg"/>" width="30px"/></a>
                            </c:forEach>
                        </div>
                        <script type="text/javascript">
                            window.addEvent('domready', function(){
                                $$('a.fotolink').each(function(el) {
                                    if(el.hasClass('current')) fotoClick = el;
                                    //console.log(el.get('title'))
                                    var titSplit = el.get('title').split('::');
                                    //console.log(titSplit)
                                    var captObj = {
                                        modello: titSplit[0],
                                        variante: titSplit[1],
                                        colore: titSplit[2],
                                        montatura: titSplit[3],
                                        elettrificazione: titSplit[4]
                                    };
                                    el.store('cap', captObj);
                                    el.addEvent('click', function(e) {
                                        if(e) e.stop();
                                        var desThumbId = el.get('id');
                                        desThumbId = desThumbId.substring(desThumbId.indexOf('_')+1);
                                        if(disClick!=null){
                                            disClick.removeClass('current');
                                        }
                                        disClick = $('thumb'+desThumbId);
                                        if($defined(disClick)) disClick.addClass('current');

                                        if(fotoClick!=null){
                                            fotoClick.removeClass('current');
                                        }
                                        $('foto').setProperty('src', el.href);
                                        $('caption').empty().set({
                                            'html': captionTpl.substitute(el.retrieve('cap')),
                                            'styles': {
                                                'display': 'block'
                                            }});

                                        el.addClass('current');
                                        fotoClick = el;
                                    });
                                });
                            });
                        </script>
                    </c:when>
                    <c:otherwise>
                        <div id="img-cnt">
                            <img src="<c:url value="/static/images/articoli/foto/nd.jpg"/>" name="foto" id="foto" alt="${articoli[0].cdartm}"/>
                            <div id="caption" style="display:none"></div>
                        </div>
                        <div id="photo-link"></div>
                    </c:otherwise>
                </c:choose>
            </div>




        </div>

        <div id="scheda-dx-wrapper" class="tipologia-${tipologiaFilter.cdvisttp}">
            <security:authorize ifNotGranted="ROLE_ANONYMOUS">
                <ul id="techNews-list">
                    <c:if test="${!empty techNewsList}"><li data-tk="${techNewsList[0].tkpost}"><a href="${portalUrl}techNews/<fmt:formatDate value="${techNewsList[0].postDate}" pattern="yyyy/MM/dd/"/>${techNewsList[0].tkpost}?locale=${rc.locale}&cdvistfam=${famigliaFilter.cdvistfam}&cdvisttp=${tipologiaFilter.cdvisttp}" target="_blank"><img src="<c:url value="/static/images/news-icon.gif"/>" alt="tech news"/></a><c:if test="${fn:length(techNewsList)>1}"><div class="notify-bubble">${fn:length(techNewsList)}</div></c:if></li></c:if>
                </ul>
            </security:authorize>                
            <c:choose>
            <c:when test="${!empty modellidis}">
            <div id="scheda-tabs">
                <spring:message code="plural" var="plural"/>
                <div id="art-tab" class="tab"><spring:message code="articoli" arguments="${plural}"/></div>
                <security:authorize ifNotGranted="ROLE_ANONYMOUS">
                <div id="ric-tab" class="tab"><spring:message code="ricambio" text="RICAMBIO"/></div>
                </security:authorize>
            </div>

            <div id="scheda-col-dx" class="clearfix">
                <spring:url var="baseURL" value="/scheda-{dsvistfam}-{cdvisttp}/{cdvistfam}/articolo.json">
                    <spring:param name="cdvistfam" value="${famigliaFilter.cdvistfam}" />
                    <spring:param name="dsvistfam" value="${famigliaFilter.dsvistfam}" />
                    <spring:param name="cdvisttp" value="${tipologiaFilter.cdvisttp}" />
                </spring:url>
                <form id="selartForm" name="selartForm" action="${baseURL}">
                    <input type="hidden" name="cdvistv1" id="cdvistv1">
                    <input type="hidden" name="cdvistv2" id="cdvistv2">
                    <input type="hidden" name="cdvistv3" id="cdvistv3">
                    <input type="hidden" name="cdvistvetro" id="cdvistvetro">
                    <input type="hidden" name="cdvistfinm" id="cdvistfinm">
                    <input type="hidden" name="cdvistfinv" id="cdvistfinv">
                    <input type="hidden" name="cdvistcolv" id="cdvistcolv">
                    <input type="hidden" name="cdvistelet" id="cdvistelet" value="<c:if test="${!empty param.cdvistelet}">${param.cdvistelet}</c:if>">
                    <input type="hidden" name="cdartiric" id="cdartiric">
                    <input type="hidden" name="vist_filedis" id="vist_filedis">

                </form>
                <div id="art-cnt">
                    <div id="art-cnt-sx">
                        <div id="art-image"></div>
                        <div id="file-dwl"></div>
                        <div id="file-dwl-2"></div>
                    </div>
                    <div id="art-cnt-dx">
                        <div id="modello" class="dd-scheda det-sel-row clearfix">
                            <h2 id="titmodello"><spring:message code="model" text="Modello"/></h2>
                            <h3 id="titcollezione"> </h3>
                            <div id="art-txt" class="art-tab-txt"><spring:message code="txt.model.conf" text="Configura il modello con le varianti disponibili"/></div>

                            <select name="modello" id="sel_modello" style="display: none">
                                <option value=""><spring:message code="choose.model" text="Seleziona modello"/></option>
                                <c:forEach items="${modelli}" var="t" varStatus="s">
                                    <option value="${t.cdvistv1}|${t.cdvistv2}|${t.cdvistv3}">${famigliaFilter[dsfam]} ${tipologiaFilter.cdvisttp} ${t.cdvistv1} ${t.cdvistv2} ${t.cdvistv3}</option>
                                </c:forEach>
                            </select>
                            <spring:url var="baseURL" value="/scheda-{dsvistfam}-{cdvisttp}/{cdvistfam}/articolo.json">
                                <spring:param name="cdvistfam" value="${famigliaFilter.cdvistfam}" />
                                <spring:param name="dsvistfam" value="${famigliaFilter.dsvistfam}" />
                                <spring:param name="cdvisttp" value="${tipologiaFilter.cdvisttp}" />
                            </spring:url>
                            <script type="text/javascript">

                                $('sel_modello').addEvent('change', function(e){
                                    var value = this.get('value');

                                    var varArray = value.split('|',3);

                                    $('cdvistv1').set('value', varArray[0]);
                                    $('cdvistv2').set('value', varArray[1]);
                                    $('cdvistv3').set('value', varArray[2]);

                                    var desThumbId = "thumb" + varArray[0] + varArray[1] + varArray[2];

                                    if($defined($(desThumbId))) $(desThumbId).fireEvent('click', e);


                                    $('cdartiric').set('value', '');


                                    //sendFormData();

                                });
                            </script>
                        </div>
                        <%--div id="variante" class="dd-scheda">
                            <h2>Variante</h2>
                        </div--%>
                        <div id="vetro" class="dd-scheda det-sel-row clearfix hide">
                            <h2><spring:message code="vetro" text="vetro"/></h2>

                               
                            <select name="vetro" id="sel_vetro" style="display: none">
                                <option value=""><spring:message code="choose.model" text="Seleziona modello"/></option>
                            </select>
                            <div class="icolist"><ul></ul></div>
                            <script type="text/javascript">

                                $('sel_vetro').addEvent('change', function(){
                                    var value = this.get('value');
                                    /*if(this.getSelected()[0].get('style')){
                                        this.set('style', this.getSelected()[0].get('style'));
                                    }else{
                                        this.removeProperty('style');
                                    }*/
                                    var ar_value = value.split('|');


                                    $('cdvistvetro').set('value', value);
                                    $('cdvistcolv').set('value', ar_value[0]);
                                    $('cdvistfinv').set('value', ar_value[1]);
                                    
                                    sendFormData();

                                });
                            </script>
                        </div>
                        <%--div id="tonalita-vetro" class="dd-scheda">
                        <h2><spring:message code="col.vetro" text="Tonalità vetro"/></h2>
                        <select name="colori" id="sel_colori">
                            <option value=""><spring:message code="choose.model" text="Seleziona modello"/></option>
                        </select>
                        <script type="text/javascript">

                   $('sel_colori').addEvent('change', function(){
                      var value = this.get('value');

                      $('cdvistcolv').set('value', value);

                      sendFormData();

                   });
                </script>
                </div--%>
                        <%--div id="finitura-vetro" class="dd-scheda">
                            <h2><spring:message code="fin.vetro" text="Finitura vetro"/></h2>
                            <select name="finv" id="sel_finv">
                                <option value=""><spring:message code="choose.model" text="Seleziona modello"/></option>
                            </select>
                            <script type="text/javascript">

                       $('sel_finv').addEvent('change', function(){
                          var value = this.get('value');

                          $('cdvistfinv').set('value', value);

                          sendFormData();

                       });
                    </script>
                </div--%>
                        <div class="det-sel-row clearfix">
                            <div id="parti-metalliche" class="dd-scheda hide">
                                <h2><spring:message code="mont" text="Montatura"/></h2>
                                <select name="finm" id="sel_finm" style="display: none">
                                    <option value=""><spring:message code="choose.model" text="Seleziona modello"/></option>
                                </select>
                                <div class="icolist"><ul></ul></div>
                                <script type="text/javascript">

                                    $('sel_finm').addEvent('change', function(){
                                        var value = this.get('value');

                                        /*if(this.getSelected()[0].get('style')){
                                            this.set('style', this.getSelected()[0].get('style'));
                                        }else{
                                            this.removeProperty('style');
                                        }*/
                                        $('cdvistfinm').set('value', value);

                                        sendFormData();

                                    });
                                </script>
                            </div>
                        </div>
                        <div class="det-sel-row clearfix">                                
                            <div id="lampadine" class="dd-scheda hide">
                                <h2><spring:message code="elettr" text="Elettrificazione"/></h2>
                                <select name="elettrificazione" id="sel_elettrificazioni" style="display: none">
                                    <option value=""><spring:message code="choose.model" text="Seleziona modello"/></option>
                                </select>
                                <div class="icolist"><ul></ul></div>
                                <script type="text/javascript">

                                    $('sel_elettrificazioni').addEvent('change', function(){
                                        var value = this.get('value');

                                        /*if(this.getSelected()[0].get('style')){
                                            this.set('style', this.getSelected()[0].get('style'));
                                        }else{
                                            this.removeProperty('style');
                                        }*/
                          
                                        $('cdvistelet').set('value', value);

                                        sendFormData();

                                    });
                                </script>
                            </div>
                        </div>
                        <div id="art-lamp" class="det-sel-row clearfix"></div>
                        <div id="art-code" class="det-sel-row clearfix"></div>
                        <div class="det-sel-row clearfix">
                            <div id="dati-tecnici" class="dd-scheda" style="display: none">
                                <h2><spring:message code="technical.data" text="Dati tecnici"/></h2>
                                <div id="capt-image"></div> 
                            </div>
                        </div>
                    </div>
                        
                    <%--security:authorize ifNotGranted="ROLE_ANONYMOUS"--%>
                    <div id="art-detail">
                    </div>
                    <%--/security:authorize--%>
                </div>

                <security:authorize ifNotGranted="ROLE_ANONYMOUS">
                <div id="ric-cnt">
                    <div id="ric-cnt-sx">
                        <div id="art-image-ric"></div>
                        <div id="capt-image-ric"></div>
                    </div>
                    <div id="ric-cnt-dx">
                        <div id="sel-ric-cnt" class="dd-scheda det-sel-row clearfix">
                            <h2><spring:message code="ricambio" text="Ricambio"/></h2>
                            <div id="ric-txt" class="art-tab-txt"><spring:message code="txt.ric.conf" text="Seleziona un articolo per visualizzare i ricambi disponibili"/></div>
                            <select name="ric" id="sel_ric" style="display: none; text-transform: capitalize">
                                <option value=""><spring:message code="choose" text="Seleziona"/> <spring:message code="ricambio" text="ricambio"/></option>
                            </select>
                            <div class="icolist"><ul></ul></div>
                            <script type="text/javascript">

                                $('sel_ric').addEvent('change', function(){
                                    //console.log(this.selectedIndex)
                                    var opt = $(this.options[this.selectedIndex]);
                                    var path = stripJsession('<c:url value="/static/images/articoli/disegnitecnici/${(useSpeclist)?'po/':''}"/>');
                                    var img = new Asset.image(path + opt.retrieve('img'), {id: 'disTecRicBig', onerror: loadImgError});
                                    $('art-image-ric').empty().adopt(img);
                                    //$('capt-image-ric').empty().set('text', opt.retrieve('numv'));


                                    var value = this.get('value');
                                    var uri = '<c:url value="/carrelloItem-{cdarti}/"/>';

                                    var urlCartItem = uri + "?cdartirif={cdartirif}";
                                    var cdRic = {cdarti: value, cdartirif: $('articolo.cdarti').get('value')};

                                    //var cdRic = {cdarti: value};
                                    var icoCnt = this.getParent().getElement('.icolist ul');
                                    icoCnt.getElements('li').removeClass('active');
                                    if(value && value != '') icoCnt.getElements('.'+value).addClass('active');                                    
                                    $('cdartiric').set('value', value);
                                                                        
                                    $('ric-detail').load(urlCartItem.substitute(cdRic));

                                });


                            </script>
                        </div>
                        <div id="ric-code" class="det-sel-row clearfix"></div>
                    </div>
                    <security:authorize ifNotGranted="ROLE_ANONYMOUS">
                    <div id="ric-detail">
                    </div>                   
                    </security:authorize>                                    
                </div>
                </security:authorize>
                <iframe name="iframe_open_file" id="iframe_open_file" 
                                    width="0"
                                    height="0"
                                    scrolling="auto"
                                    frameborder="0"
                                    marginheigth="0"
                                    marginwidth="0">
                    </iframe>                 
                <p style="clear: both">&nbsp;</p>
            </div>

            </c:when>
            <c:otherwise>
                <security:authorize ifNotGranted="ROLE_ANONYMOUS">
                <fmt:message key="art.notfound.logged"/>
                </security:authorize>
                <security:authorize ifAllGranted="ROLE_ANONYMOUS">
                <fmt:message key="art.notfound"/>    
                </security:authorize>
            </c:otherwise>
            </c:choose>
        </div>
        <%--${pagination}--%>



    </c:when>
    <c:otherwise>
        <security:authorize ifNotGranted="ROLE_ANONYMOUS">
        <spring:message code="art.notFound.logged"/>
        </security:authorize>
        <security:authorize ifAllGranted="ROLE_ANONYMOUS">
        <spring:message code="art.notFound"/>    
        </security:authorize>                
    </c:otherwise>
</c:choose>
<security:authorize ifAllGranted="ROLE_CLIE">
    <div class="foot-note">(*) <spring:message code="articolo.dtprdisp_nota"/></div>
</security:authorize>
    <div class="foot-note"><spring:message code="footer_istr_download_2d_3d" text="Per visualizzare i file 3D è necessario eDrawings" arguments="<a href='http://www.edrawingsviewer.com/' target='_blanck'>www.edrawingsviewer.com</a>"/></div>   
<jsp:directive.include file="/WEB-INF/jsp/footer.jsp"/>
