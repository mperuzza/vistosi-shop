<%@ page import="java.sql.ResultSet"  
         import="java.sql.SQLException"
         import="java.sql.Timestamp"
         import="java.util.Vector"
         import="com.ateikon.common.Atk_sql"
         import="com.ateikon.common.Costanti_comm"
          


         import="com.ateikon.util.Atk_ctrl"
         import="com.ateikon.util.StringParsToHTML"
         import="com.ateikon.util.StringUtils"

         import="com.ateikon.structure.Str_html"
%><%



    %><%@include file="include/variabili_comuni.jsp" %><%


try {


    %><%@include file="include/dbconnect.jsp" %><%
    %><%@include file="include/parametri_ep.jsp" %><%
    %><%@include file="include/sicurezza_ep.jsp" %><%


    // imposto la connessione







    // imposto le variabili di request


    if (request.getParameter("azione"  )!=null)   azione   = request.getParameter("azione");


%><html>
<head>
<%@include file="include/head_page.jsp" %>
<script language="JavaScript" src="js/atk_catalogo_std.js" ></script>
<script language="JavaScript">

    function atk_onLoad(){

        <%@include file="js/atk_onLoad.jsp" %>

    }


    function atk_onSubmit(){
        return true;
    }
    

    function atk_salva(){

        document.form1.azione.value = 'salva';
        document.form1.submit();
    }


    function atk_refresh(){
        self.location = '<%= seStesso%>';
    }

<%@include file="js/atk_key.jsp" %>

    function atk_close(){
        self.location = 'index.html';
    }
    function atk_paste( ){ }
    function atk_f1( ){ }
    function atk_f2( ){ }
    function atk_f3( ){ }
    
    
</script>

</head>


<body onLoad="atk_onLoad();" >
<%@include file="include/head.jsp" %>
<%@include file="include/divAttesa.jsp" %>


<div class="body_div">

 <h2>Amministrazione Portale</h2>

 <br/>
<input type="button" name="bt_exit" value="Exit" id="el~0" class="bt bt_75" onFocus="atk_onFocus(this)" onBlur="atk_onblur(this)" onKeydown="return jsk(this)" onKeyup="return jskup(this)" onClick="atk_close();"/> 
<input type="button" name="bt_refr" value="Carica" id="el~1" class="bt bt_75" onFocus="atk_onFocus(this)" onBlur="atk_onblur(this)" onKeydown="return jsk(this)" onKeyup="return jskup(this)" onClick="atk_refresh();"/> 
<br/>
<br/>
 <table>
 <tr valign="top">
     <td style="border: 1px solid #ebebeb;" width="220">
         <table class="parametri">
            <tr>
                <td><img src="img/menu_utenti.gif"></td>
                <td nowrap><a href="catAdmin_user.jsp">Utenti</a></td>
            </tr>
            <tr>
                <td><img src="img/menu_utenti.gif"></td>
                <td nowrap><a href="catAdmin_tipomapr_clie_riep.jsp">Riepilogo Clienti/Categorie</a></td>
            </tr>
            <tr>
                <td><img src="img/menu_utenti.gif"></td>
                <td nowrap><a href="catAdmin_funz.jsp">Funzioni</a></td>
            </tr>
            <tr>
                <td><img src="img/menu_utenti.gif"></td>
                <td nowrap><a href="catAdmin_menu.jsp">Men&ugrave;</a></td>
            </tr>
            <tr>
                <td><img src="img/menu_config.gif"></td>
                <td nowrap><a href="catAdmin_config.jsp">Configurazione</a></td>
            </tr>
         </table>
     </td>
     <td width="30">&nbsp;</td>
     <td style="border: 1px solid #ebebeb;" width="220">
         <table class="parametri">
            <tr>
                <td><img src="img/menu_config.gif"></td>
                <td nowrap><a href="catAdmin_tipomapr_escl.jsp">Escudi Categorie</a></td>
            </tr>
            <tr>
                <td><img src="img/menu_config.gif"></td>
                <td nowrap><a href="epConfig01_imptrasp.jsp">Spese di trasporto</a></td>
            </tr>
            <tr>
                <td><img src="img/menu_config.gif"></td>
                <td nowrap><a href="epConfig01_condven.jsp">Cond. di Vendita</a></td>
            </tr>
            <tr>
                <td><img src="img/menu_config.gif"></td>
                <td nowrap><a href="epBanca_sella_imp_OTP.jsp">Banca Sella carico OTP</a></td>
            </tr>
         </table>
     </td>
     <td width="30">&nbsp;</td>
     <td style="border: 1px solid #ebebeb;" width="220">
         <table class="parametri">
            <tr>
                <td><img src="img/menu_config.gif"></td>
                <td  nowrap><a href="epConfig01_layout.jsp" onClick="atk_wait();">Articoli in Evidenza</a></td>
            </tr>
            <tr>
                <td><img src="img/menu_config.gif"></td>
                <td  nowrap><a href="epMailing.jsp">Campagne Marketing</a></td>
            </tr>
            <tr>
                <td><img src="img/menu_config.gif"></td>
                <td nowrap><a href="epStp_articoli.jsp">Stampa Articoli</a></td>
            </tr>
         </table>
     </td>
 </tr>
 </table>






</div>


<script language="javaScript">
    tot_righe = <%= idx_f %>;
</script >

<%@include file="include/dbclose.jsp" %>
</body>
</html>





