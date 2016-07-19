<%-- 
    Document   : promo
    Created on : 29-set-2009, 11.34.23
    Author     : emiliano
--%>
<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:directive.include file="/WEB-INF/jspf/meta.jspf"/>

        <style type="text/css" media="screen">   
            @import url("<c:url value='/static/styles/vistosi-shop.css'/>");
            <c:if test="${rc.locale=='en'}">
                @import url("<c:url value='/static/styles/vistosi-shop_en.css'/>");
            </c:if>
            <c:if test="${rc.locale=='de'}">
                @import url("<c:url value='/static/styles/vistosi-shop_de.css'/>");
            </c:if>

        </style>     

        <script type="text/javascript" src="<c:url value='/static/js/mootools-1.2.5-core-yc.js' />"> </script>
        <script type="text/javascript" src="<c:url value='/static/js/mootools-1.2.5.1-more.js' />"> </script>
        <script type="text/javascript" src="<c:url value='/static/js/mediaboxAdv-1.3.4b.js' />"></script>
        <title>Vistosi SHOP</title>
        <%--security:authorize ifNotGranted="ROLE_CLIE"><script type="text/javascript">console.log("non cliente")</script></security:authorize--%>
    </head>

    <body class="vistosi-shop" style="background-color: #fff">


        <div id="wishlist-cnt">
            <h2><spring:message code="button.wishlist.send" text="Invia wishlist"/></h2>

            <c:if test="${!empty messages}">
                <div class="success fade-ffff00" id="msg" onclick="$(this).dispose()">
                    <c:forEach var="msg" items="${messages}">
                        <c:out value="${msg}" escapeXml="false"/><br />
                    </c:forEach>
                </div>
                <c:remove var="messages" scope="session"/>
            </c:if>                    
            <form:form modelAttribute="formSendWishlist" method="post" id="wishlistForm" name="wishlistForm">
                <form:hidden path="wishlistId"/>

                <fieldset>
                    <div class="clearfix">
                        <label for="sender"><spring:message code="wishlist.send.from" text="Email mittente"/>*</label>
                        <div class="input">
                            <form:input path="sender" cssClass="input-xlarge" cssErrorClass="input-xlarge err"/>
                            <form:errors path="sender" cssClass="error" htmlEscape="false" cssStyle="text-align:left;"/>
                        </div>
                    </div> 
                    <div class="clearfix">
                        <label for="destinatari"><spring:message code="wishlist.send.to" text="Email destinatari"/>*</label>
                        <div class="input">
                            <form:input path="destinatari" cssClass="input-xlarge" cssErrorClass="input-xlarge err"/>
                            <form:errors path="destinatari" cssClass="error" htmlEscape="false" cssStyle="text-align:left;"/>
                        </div>
                    </div> 
                    <div class="clearfix">
                        <label for="note"><spring:message code="wishlist.send.note" text="Note"/></label>
                        <div class="input">
                            <form:textarea path="note" cssClass="input-xlarge in" rows="2"/>
                        </div>
                    </div>                    
                    <div class="actions">
                        <div class="button left"><a href="javascript:void(0)" class="sendwl" id="sendWl"><span><em><spring:message code="button.wishlist.send" text="Invia wishlist"/></em></span></a></div>
                        <%--input name="submit" type="submit" value="<spring:message code="wishlist.form.send" text="Invia"/>" class="btn"/><br/><br/--%>

                        <c:if test="${!storeLocator}">
                            <div class="button left" style="margin-left: 5px;"><a href="javascript:void(0)" onclick="parent.Mediabox.open('<c:url value="/wishlist"><c:param name="storelocator" value="true"/></c:url>', '', '980 700');return false;"><span><em><spring:message code="button.wishlist.searchdealer" text="Cerca rivenditore"/></em></span></a></div>
                        </c:if>
                    </div>                    
                </fieldset>                
            </form:form>

        </div>

        <c:if test="${storeLocator}">
            <div>
                <iframe width="968" height="510" frameborder="0" src="<c:url value="/store_locator" context="/"><c:param name="embed" value="true"/></c:url>" id="storeLocatorIframe"></iframe>
            </div>

        </c:if>     

        <script type="text/javascript">
            $('sendWl').addEvent('click', function(e){
        
                $('wishlistForm').submit();
            });
        </script>
    </body>

</html>