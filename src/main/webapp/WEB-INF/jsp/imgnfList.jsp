<%-- 
    Document   : 403
    Created on : 23-set-2009, 11.34.23
    Author     : emiliano
--%>
<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<jsp:directive.include file="/WEB-INF/jsp/header.jsp"/>

<div id="search-cnt">
    <h1>Immagini non trovate sul server</h1>

  <h3>Thumb per famiglia-tipologia</h3>
  <display:table name="immagini_tipfam" id="img" export="true" requestURI="immagininontrovate" class="displaytag">
    <display:setProperty name="basic.msg.empty_list" value="Nessun immagine non trovata"/>
    <display:setProperty name="paging.banner.item_name" value="immagine"/>
    <display:setProperty name="paging.banner.items_name" value="immagini"/>
    <display:column property="nome_immagine" title="Nome immagine"/>
    <display:column property="path_previsto" title="Path previsto"/>
  </display:table>

  <hr/>
</div>

<jsp:directive.include file="/WEB-INF/jsp/footer.jsp"/>