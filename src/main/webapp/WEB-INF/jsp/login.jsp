<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>

<%@ page import="org.springframework.security.ui.AbstractProcessingFilter" %>
<%@ page import="org.springframework.security.ui.webapp.AuthenticationProcessingFilter" %>
<%@ page import="org.springframework.security.AuthenticationException" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">


<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

  <style type="text/css" media="screen">
    @import url("<c:url value='/static/styles/vistosi-login.css'/>");
 
    h3 {
        color: #000;
    }

    #div_login {
        border: 1px solid #CFCFCF;
        padding: 30px 10px 10px 10px;
    }
    
    </style>
  
  <script type="text/javascript" src="<c:url value="/resources/spring/Spring.js" />"> </script>
  <title>Vistosi SHOP</title>
 
</head>


<%-- this form-login-page form is also used as the
     form-error-page to ask for a login again.
--%>

<body style="overflow: hidden; height: 100%; margin: 0px;">	
        <div id="portalLoginContainer">
        <div class="clearfix">
        </div>
        <div id="portalLoginContent">
            <div id="desktop">
                <div id="desktopHeader">
                     <div id="desktopTitlebarWrapper">
                        <div id="desktopTitlebar"></div>
                     </div>
                </div>
                
                
                  <div id="pageWrapper" style="height: 100%;">
                      <!--<div id="sideColumn1" class="column expanded" style="width: 225px; height: 100%;">
                          <div id="sideColumn1_spacer" class="horizontalHandle"></div>
                      </div>
                      
                      <div id="sideColumn1_handle" class="columnHandle" style="cursor: e-resize; height: 100%;"><div id="sideColumn1_handle_icon" class="handleIcon"></div></div>-->

                      <div id="mainColumn" class="column expanded rWidth" style="height: 100%; width: 100%;">
                          <div id="mainColumn_spacer" class="horizontalHandle"></div>
                          <div id="mainPanel" class="panel expanded bottomPanel" style="height: 100%; overflow: hidden; width: 100%;">
                              <div id="mainPanel_pad" class="pad" style="padding: 0px; overflow: hidden; display: block;">
                                  <div style="padding:40px;width: 400px;margin: auto;">
                                    <form name="f" action="<c:url value='/static/j_spring_security_check'/>" method="POST"> 
                                      <table>
                                        <tr>
                                            <td colspan="2"><h1>My Vistosi Shop</h1></td>
                                        </tr>
                                        <tr>
                                          <td valign="top">
                                            <div id="div_login">
                                              <table style="width: 100%;">
                                                <tr>
                                                  <td style="padding-bottom: 5px; width:80px;" valign="bottom"><label for="j_username">Username:</label></td>
                                                  <td style="padding-bottom: 8px;" valign="bottom">
                                                    <input id="j_username" type='text' name='j_username' class="input"/>
                                                  </td>
                                                </tr>
                                                <tr>
                                                  <td style="padding-bottom: 5px; width:80px;" valign="bottom"><label for="j_password">Password:</label></td>
                                                  <td style="padding-bottom: 8px;" valign="bottom">
                                                    <input id="j_password" type='password' name='j_password' class="input" />
                                                  </td>
                                                </tr>
                                                <tr>
                                                  <td style="padding-top: 12px;">&nbsp;</td>
                                                  <td style="padding-top: 12px;">
                                                    <input id="proceed" class="mano" type="submit" value="LOGIN"/>
                                                    <input id="reset" class="mano" type="reset" value="RESET"/>
                                                  </td>
                                                </tr>
                                                <tr>
                                                  <td style="padding-top: 25px;">&nbsp;</td>
                                                  <td style="padding-top: 25px; font-size:11px;text-align: right;">
                                                      <label for="j_remember"><spring:message code="rememberme" text="ricordami"/>:</label>
                                                    <input type="checkbox" class="checkbox" name="_spring_security_remember_me" id="j_remember" tabindex="3"/>
                                                  </td>
                                                </tr>
                                                <tr>
                                                  <td style="padding-top: 10px; font-size:11px;text-align: right;" colspan="2">
                                                   <spring:message code="text_rec_password" text="Hai scordato l'utente e/o la password"/> 
                                                   <a href="http://www.vistosi.it/portal/epCredenziali.jsp?fromcontext=shop" target="_blank"><strong><spring:message code="text_rec_password_link" text="Clicca qui."/></strong></a>
                                                  </td>
                                                </tr>
                                          </table>
                                        </div>
                                      </td>
                                    </tr>
                                  </table>
                                </form>
                              </div>
                            </div>
                          </div>
                       <div id="mainPanel_handle" class="horizontalHandle detached" style="display: none;"><div id="mainPanel_handle_icon" class="handleIcon"></div></div>
                      </div>
                                      
                                      
                                      
                   </div>
                              
                <div id="desktopFooterWrapper">
                    <div id="desktopFooter">			
                            &copy; Ateikon | Powered by <a target="_blank" href="http://www.ateikon.com">Ateikon Internet &amp; Multimedia</a><br />
                    </div>
                </div>
                              
             </div><!-- desktop end -->
            </div>
            <div class="clearfix">
            </div>
        </div>

    </body>
    </html>
