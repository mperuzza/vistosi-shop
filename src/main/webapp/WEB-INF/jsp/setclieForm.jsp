<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<%--jsp:directive.include file="/WEB-INF/jspf/_messages.jspf"/--%>

<form:form modelAttribute="cliente" method="post" id="setclie">

    <br/>
    <form:hidden path="tkclie"/>
    <spring:message code="search.by" text="Cerca per"/> <strong><spring:message code="ragsoc" text="ragione sociale"/></strong> <input name="ragcogfilter" type="text" id="ragcogfilter" value="${param.ragcogfilter}" size="50"/>
    <spring:message code="include.blocked" text="includi bloccati"/> <input name="ck_fnoord" type="checkbox" id="ck_fnoord" value="S" <c:if test="${param.ck_fnoord=='S'}">checked="true"</c:if>/>
    <%--input type="submit" value="Cerca" name="filterBtn" id="filterBtn"/--%><div class="button right"><a href="javascript:void(0)" id="searchBtn"><span><em><spring:message code="button.search" text="Cerca"/></em></span></a></div><br/><br/>
  <spring:message code="cliente" var="clie"/>
  <spring:message code="singular.f" var="singular"/>
  <spring:message code="plural" var="plural"/>
  <display:table name="theList" id="clie" export="false" requestURI="setclie" class="displaytag">
      <display:setProperty name="basic.msg.empty_list"><spring:message code="no.item.found" arguments="${clie}"/></display:setProperty>
      <display:setProperty name="paging.banner.item_name"><spring:message code="cliente" arguments="${singular}"/></display:setProperty>
      <display:setProperty name="paging.banner.items_name"><spring:message code="clienti" arguments="${plural}"/></display:setProperty>

    <display:column title="" class="nextLink"><c:choose><c:when test="${clie.archclie.fnoord == 'S'}"><span style="color: red;">${clie.archclie.tkclie} <span style="font-size:10px"><spring:message code="cliente" text="Cliente"/> <spring:message code="blocked" text="bloccato"/></span></span></c:when><c:otherwise><input type="button" class="selectclie" value="${clie.archclie.tkclie}"/></c:otherwise></c:choose></display:column>
    <display:column titleKey="ragsoc" property="archenti.ragcog" sortName="ragcog" sortProperty="ragcog" sortable="true"/>
    <display:column titleKey="cart.dm.indiri" property="sedeLegale.unitalocali.indiri" sortable="false"/>
    <display:column titleKey="cart.dm.comune" property="sedeLegale.unitalocali.comune" sortable="false"/>
  </display:table>

   
</form:form>
</div>

<script type="text/javascript">
    function replaceLinks() {

        var sortLinks = $('clie').getElements('thead')[0]
                                     .getElements('a');
        ajaxifyLinks(sortLinks);

        if ($(document.body).getElements('span.pagelinks').length > 0) {

            var pagelinks = $(document.body).getElements('span.pagelinks')[0]
                                    .getElements('a');

            ajaxifyLinks(pagelinks);

        }

        var nextLinks = $('clie').getElements('tbody')[0]
                                  .getElements('tr');


		if(nextLinks!=null && nextLinks.length>0){
			for (i=0; i < nextLinks.length; i++) {
				nextLinks[i].addEvent('dblclick', function(e){
					//var a = this.getElements('a')[0];
                                        this.getElement('input').fireEvent('click', e);
					//window.location.href = a.href;
				});
			}
		}

         $$('input.selectclie').each(function(el) {
                                        
                                        el.addEvent('click', function(e) {
                                            $('tkclie').set('value',el.get('value'));
                                            $('setclie').fireEvent('submit', e);
                                        })
         });


    }
    function ajaxifyLinks(links) {

        if(links){
            for (i=0; i < links.length; i++) {
                links[i].onclick = function() {

                    var myHTMLRequest = new Request.HTML({url:this.href,
                                                          update: $('setclie').getParent()}).get();

                    return false;
                }
            }
        }
    }
    if($('clie')) replaceLinks();


    new Form.Request($('setclie'), $('setclie').getParent(),
           {onSuccess: function(){
                //console.log('success get clie');
           }});

    $('searchBtn').addEvent('click', function(e){
        $('setclie').fireEvent('submit',e);
    });


      <c:if test="${!empty cliente.tkclie}">
          var waiter = new Spinner('selclie-cnt');
          waiter.show();
          var uri = new URI('<c:url value="/index"/>');
          uri.go();
      </c:if>

</script>
