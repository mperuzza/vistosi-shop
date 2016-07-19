<%@ page import="java.sql.ResultSet"  
         import="java.sql.SQLException"
         import="java.sql.Timestamp"
         import="java.util.Vector"
         import="com.ateikon.common.Atk_sql"
         import="com.ateikon.common.Cat_costanti"


         import="com.ateikon.common.Archclie"
         import="com.ateikon.common.Nazioni"
         import="com.ateikon.common.Lis_listino"

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




    if (request.getParameter("azione")!=null) azione = request.getParameter("azione");

    if (azione.equals("test")){

        if (session.getAttribute("cat_if_test") !=null){
            session.removeAttribute("cat_if_test");
        }else {
            session.setAttribute("cat_if_test", "300");
        }
    }


    String req_cdazie = "";
    String req_cdappl = "";
    String req_cddipa = "";
    String req_profil = "";


    String r_agente_web = "";

    if (request.getParameter("message")!=null) message = request.getParameter("message");
    if (request.getParameter("req_cdazie")!=null) req_cdazie = request.getParameter("req_cdazie");
    if (request.getParameter("r_agente_web")!=null) r_agente_web = request.getParameter("r_agente_web");


    cat_costanti.cdazie = req_cdazie;
    cat_costanti.cdappl = req_cdappl;
    cat_costanti.cddipa = req_cddipa;
    cat_costanti.profil = req_profil;
    
    cat_costanti.cdagen_appl = r_agente_web;


    atk_sql.cdazie = req_cdazie;




    Lis_listino lis_listino = new Lis_listino();

    atk_sql.setProfilo((Atk_sql) lis_listino);




    long       ll_tkcost    = 0;
    String     ls_costname  = "";
    String     ls_costlabel = "Site Root";
    String     ls_costvalue = "";

    String     ls_default_value = "";

    String     ls_css = "css1";

    String l_query   = "";



%>
<head>

<%@include file="include/head_page.jsp" %>
<script language="JavaScript">

    function atk_onLoad(){

        <%@include file="js/atk_onLoad.jsp" %>

    }


    function atk_onSubmit(){
        return true;

    }
            
    
    function atk_delete(tkcost_del){

        var ls_msg = 'Eliminare la Costante?';

        if (!confirm(ls_msg)){
            return;
        }

        document.form1.action = 'catAdmin_config_aggiorna.jsp';
        document.form1.azione.value = 'delete';
        document.form1.tkcost_del.value = tkcost_del;
        document.form1.submit();
    }


    
    function atk_salva_conf(){
        if (confirm('Salvare le Modifiche?')){
            atk_salva();
        }
    }

    function atk_salva(){

        document.form1.action = 'catAdmin_config_aggiorna.jsp';
        document.form1.azione.value = 'aggiorna';
        document.form1.submit();
    }

    function atk_test(){

        self.location = '<%= seStesso %>?azione=test';
    }

    function atk_refresh(){

        if (confirm('Attenzione!\n\n\tLe modifiche verranno perse.\n\tContinuare?')){
            self.location = '<%= seStesso %>';
        }
    }

    function atk_carica(){

        document.form1.submit();
    }

<%@include file="js/atk_key.jsp" %>
    
    function atk_close(){

        self.location = 'catAdmin_index.jsp';
    }

    function atk_f1( ){ }
    function atk_f2( ){ }
    function atk_f3( ){ }
    function atk_paste(){ }

    
    


</script>

</head>


<body onLoad="atk_onLoad();" onDblClick="atk_salva_conf();" >
<%@include file="include/head.jsp" %>
<%@include file="include/divAttesa.jsp" %>

<table style="margin : 50px;">
<tr>
<td align="left" valign="top"> 

<form name="form1" method="post" action="<%= seStesso %>" >

    <h2>Configurazione e-Progen</h2>
    <br>
    <%






            if (!r_agente_web.equals("")){
                str_html = new Str_html();
            
                str_html.type  = "button";
                str_html.name  = "bt_salva";
                str_html.value = "Salva [f2]";
                str_html.css   = "bt";
                str_html.id    = "el~"+idx_f;  idx_f += 1;
                str_html.onClick  = "atk_salva();";
                
                out.println(html.getButton(str_html));
            }



            str_html = new Str_html();
        
            str_html.type  = "button";
            str_html.name  = "bt_ref";
            str_html.value = "Carica";
            str_html.css   = "bt";
            str_html.id    = "el~"+idx_f;  idx_f += 1;
            str_html.onClick  = "atk_carica();";

            out.println(html.getButton(str_html));




            str_html = new Str_html();
        
            str_html.type  = "button";
            str_html.name  = "bt_ex";
            str_html.value = "Exit";
            str_html.css   = "bt";
            str_html.id    = "el~"+idx_f;  idx_f += 1;
            str_html.onClick  = "atk_close();";

            out.println(html.getButton(str_html));

            str_html = new Str_html();
        
            str_html.type  = "button";
            str_html.name  = "bt_test";
            str_html.value = "Test";
            str_html.css   = "bt";
            str_html.id    = "el~"+idx_f;  idx_f += 1;
            str_html.onClick  = "atk_test();";

            if (session.getAttribute("cat_if_test") !=null){
                str_html.value = "NO Test";
            }

            out.println(html.getButton(str_html));

            %><img src="img/null.gif" height="10" width="30"/><%


            str_html = new Str_html();
        
            str_html.type  = "button";
            str_html.name  = "bt_mailsrv";
            str_html.value = "Mail Srv.";
            str_html.css   = "bt";
            str_html.id    = "el~"+idx_f;  idx_f += 1;
            str_html.onClick  = "self.location = 'epConfig01_mailservice.jsp';";

            out.println(html.getButton(str_html));


            str_html = new Str_html();
        
            str_html.type  = "button";
            str_html.name  = "bt_mailsrv";
            str_html.value = "Style";
            str_html.css   = "bt";
            str_html.id    = "el~"+idx_f;  idx_f += 1;
            str_html.onClick  = "self.location = 'epConfig01_style.jsp';";

            out.println(html.getButton(str_html));

            str_html = new Str_html();
        
            str_html.type  = "button";
            str_html.name  = "bt_alert";
            str_html.value = "Alert Srv.";
            str_html.css   = "bt";
            str_html.id    = "el~"+idx_f;  idx_f += 1;
            str_html.onClick  = "self.location = 'epConfig01_alert.jsp';";

            out.println(html.getButton(str_html));
    
    
    %><input type="hidden" name="azione" value="" /><%
    %><input type="hidden" name="tkcost_del" value="0" /><%
    
    
    %><br/><%
    %><br/><%

    %><table class="parametri"><%
        %><tr><%
            %><th nowrap>Selezionare l'azienda: &nbsp;</th><%
            %><td><%

                    str_html = new Str_html();
                    
                    str_html.name  = "req_cdazie";
                    str_html.id    = "el~"+idx_f;  idx_f += 1;
                    str_html.value = req_cdazie;
                    str_html.css   = "sl";
                    str_html.style = "width:200px;";
                    str_html.onChange = "atk_carica();";

                    l_query   = "";
            		l_query  += " select cdazie       \n";
            		l_query  += "      , tarags       \n";
            		l_query  += "   from pgmr.azienda \n";
            		l_query  += "  order by cdazie    \n";

                    rs = atk_sql.sql_query(l_query);

                    while(rs!=null && rs.next()){

                        String ls_cdazie = "";
                        String ls_tarags = "";

                        if (rs.getObject("cdazie")!=null)  ls_cdazie = rs.getString("cdazie");
                        if (rs.getObject("tarags")!=null)  ls_tarags = rs.getString("tarags");


                        str_html.vec_value.add(ls_cdazie);
                        str_html.vec_descr.add(ls_tarags);
                    }

                    out.println(html.getDropdown(str_html));

            %></td><%
        %></tr><%
        if (!req_cdazie.equals("")){
            %><tr><%
                %><th nowrap>Selezionare l'agente: &nbsp;</th><%
                %><td><%
    
                        str_html = new Str_html();
                        
                        str_html.name  = "r_agente_web";
                        str_html.id    = "el~"+idx_f;  idx_f += 1;
                        str_html.value = r_agente_web;
                        str_html.css   = "sl";
                        str_html.style = "width:200px;";
                        str_html.onChange = "atk_carica();";
    
                        str_html.vec_value.add("web");
                        str_html.vec_descr.add(" WEB ");
    
                        str_html.vec_value.add("sede");
                        str_html.vec_descr.add(" Sede ");
    
    
                        out.println(html.getDropdown(str_html));
    
                %></td><%
            %></tr><%
        }
    %></table><%

    %><br/><%
    
    if (!r_agente_web.equals("")){



    %><fieldset><%
        %><legend>Parametri di Base</legend><%
	  
      

    %><table class="parametri"><%


        // ----------------------------------

        ll_tkcost    = 0;
        ls_costname  = "cliente";
        ls_costlabel = "Cliente";
        ls_costvalue = "";
        
        str_html = new Str_html();
        str_html.size  = "20";
        str_html.maxlength  = "20";
        str_html.css   = "in";


        %><%@include file="include/inc_cat_costanti.jsp" %><%

            
        // ----------------------------------

        ll_tkcost    = 0;
        ls_costname  = "agente_web";
        ls_costlabel = "Filtro Keypool";
        ls_costvalue = "";
        
        str_html = new Str_html();
        str_html.size  = "20";
        str_html.maxlength  = "20";
        str_html.css   = "in in_ro";

        ls_default_value = r_agente_web.toUpperCase();

        %><%@include file="include/inc_cat_costanti.jsp" %><%


        // ----------------------------------

        ll_tkcost    = 0;
        ls_costname  = "title";
        ls_costlabel = "Title";
        ls_costvalue = "";

        str_html = new Str_html();
        str_html.size       = "20";
        str_html.maxlength  = "20";
        str_html.css        = "in";

        %><%@include file="include/inc_cat_costanti.jsp" %><%



        // ----------------------------------

        ll_tkcost    = 0;
        ls_costname  = "mail_admin";
        ls_costlabel = "Mail Amministratore";
        ls_costvalue = "";
        
        str_html = new Str_html();
        str_html.size       = "40";
        str_html.maxlength  = "80";
        str_html.css        = "in";

        %><%@include file="include/inc_cat_costanti.jsp" %><%


        // ----------------------------------

        ll_tkcost    = 0;
        ls_costname  = "siteroot";
        ls_costlabel = "Site Root";
        ls_costvalue = "";
        

        str_html = new Str_html();
        str_html.size       = "80";
        str_html.maxlength  = "40";
        str_html.css        = "in";

        ls_default_value = application.getRealPath(slash);
    

        %><%@include file="include/inc_cat_costanti.jsp" %><%



        // ----------------------------------

        ll_tkcost    = 0;
        ls_costname  = "sitecontext";
        ls_costlabel = "Site Context";
        ls_costvalue = "";
        
        str_html = new Str_html();
        str_html.size       = "20";
        str_html.maxlength  = "40";
        str_html.css        = "in";


        ls_default_value = request.getContextPath()+"/";
                
        if (ls_default_value.indexOf("/")==0) ls_default_value = ls_default_value.substring(1);                 
                

        %><%@include file="include/inc_cat_costanti.jsp" %><%


        // ----------------------------------

        ll_tkcost    = 0;
        ls_costname  = "logo_cliente";
        ls_costlabel = "Path Logo";
        ls_costvalue = "";
        
        str_html = new Str_html();
        str_html.size       = "60";
        str_html.maxlength  = "250";
        str_html.css        = "in";

        %><%@include file="include/inc_cat_costanti.jsp" %><%



        // ----------------------------------

        ll_tkcost    = 0;
        ls_costname  = "logo_link";
        ls_costlabel = "Link Logo";
        ls_costvalue = "";
        
        str_html = new Str_html();
        str_html.size       = "60";
        str_html.maxlength  = "250";
        str_html.css        = "in";

        %><%@include file="include/inc_cat_costanti.jsp" %><%



        // ----------------------------------

        ll_tkcost    = 0;
        ls_costname  = "imptrasp_min";
        ls_costlabel = "Spese Trasporto per Radiali - Minimo";
        ls_costvalue = "";
        
        str_html = new Str_html();
        str_html.size       = "12";
        str_html.maxlength  = "10";
        str_html.css        = "in in_num";

        %><%@include file="include/inc_cat_costanti.jsp" %><%


        // ----------------------------------

        ll_tkcost    = 0;
        ls_costname  = "imptrasp_max";
        ls_costlabel = "Spese Trasporto per Radiali - Massimo";
        ls_costvalue = "";
        
        str_html = new Str_html();
        str_html.size       = "12";
        str_html.maxlength  = "10";
        str_html.css        = "in in_num";

        %><%@include file="include/inc_cat_costanti.jsp" %><%


        // ----------------------------------

        ll_tkcost    = 0;
        ls_costname  = "pagamento";
        ls_costlabel = "Pagamento";
        ls_costvalue = "";

        str_html = new Str_html();

                    str_html.vec_value.add("S");
                    str_html.vec_descr.add(" SI ");

                    str_html.vec_value.add("N");
                    str_html.vec_descr.add(" NO ");

        %><%@include file="include/inc_cat_costanti.jsp" %><%

        


        // ----------------------------------

        ll_tkcost    = 0;
        ls_costname  = "cat_prezzo";
        ls_costlabel = "Visibiltà Prezzi";
        ls_costvalue = "";
        
        str_html = new Str_html();

                    str_html.vec_value.add("sempre");
                    str_html.vec_descr.add(" Sempre ");

                    str_html.vec_value.add("mai");
                    str_html.vec_descr.add(" Mai ");

                    str_html.vec_value.add("loged_user");
                    str_html.vec_descr.add(" Solo Utenti Loggati ");

                    str_html.vec_value.add("loged_user_nopromo");
                    str_html.vec_descr.add(" Solo Utenti Loggati escluse Promo");

        %><%@include file="include/inc_cat_costanti.jsp" %><%

        
        // ----------------------------------

        ll_tkcost    = 0;
        ls_costname  = "cat_carrello";
        ls_costlabel = "Possibilità di Ordinare";
        ls_costvalue = "";
        
        str_html = new Str_html();

                    str_html.vec_value.add("sempre");
                    str_html.vec_descr.add(" Sempre ");

                    str_html.vec_value.add("mai");
                    str_html.vec_descr.add(" Mai ");

                    str_html.vec_value.add("loged_user");
                    str_html.vec_descr.add(" Solo Utenti Loggati ");

        %><%@include file="include/inc_cat_costanti.jsp" %><%


    %></table><%

    %></fieldset><%
    %><br/><%


    %><fieldset><%
        %><legend>Listini di Def. per gli Agenti</legend><%
	  
    %><table class="parametri"><%
      
        for(int j=1; j<=3 ;j++){

                // ----------------------------------
        
                ll_tkcost    = 0;
                ls_costname  = "cdlist_def_0"+j;
                ls_costlabel = "Listino 0"+j;
                ls_costvalue = "";

                str_html = new Str_html();
        
                            rs = lis_listino.getListini();
                            while(rs!=null && rs.next()){
                                String ls_cdlist = "";
                                String ls_dslist = "";
        
                                if (rs.getObject("cdlist")!=null) ls_cdlist  = rs.getString("cdlist");
                                if (rs.getObject("dslist")!=null) ls_dslist  = rs.getString("dslist");
        
                                str_html.vec_value.add(ls_cdlist);
                                str_html.vec_descr.add(ls_dslist);
                            }

                %><%@include file="include/inc_cat_costanti.jsp" %><%


        }   // FINE for(int j=1; j<=3 ;j++){


    %></table><%

    %></fieldset><%
    %><br/><%



    %><fieldset><%
        %><legend>Mail</legend><%
	  
      

        %><table class="parametri"><%

            // ----------------------------------
    
            ll_tkcost    = 0;
            ls_costname  = "mail_smtp_host";
            ls_costlabel = "SMTP";
            ls_costvalue = "";
            
            str_html = new Str_html();
            str_html.size       = "40";
            str_html.maxlength  = "250";
            str_html.css        = "in";
    
            %><%@include file="include/inc_cat_costanti.jsp" %><%

            // ----------------------------------
    
            ll_tkcost    = 0;
            ls_costname  = "mail_smtp_user";
            ls_costlabel = "SMTP User";
            ls_costvalue = "";
            
            str_html = new Str_html();
            str_html.size       = "20";
            str_html.maxlength  = "40";
            str_html.css        = "in";
    
            %><%@include file="include/inc_cat_costanti.jsp" %><%

            // ----------------------------------
    
            ll_tkcost    = 0;
            ls_costname  = "mail_smtp_password";
            ls_costlabel = "SMTP Password";
            ls_costvalue = "";
            
            str_html = new Str_html();
            str_html.size       = "20";
            str_html.maxlength  = "40";
            str_html.css        = "in";
    
            %><%@include file="include/inc_cat_costanti.jsp" %><%

            // ----------------------------------
    
            ll_tkcost    = 0;
            ls_costname  = "mail_mittente";
            ls_costlabel = "Mittente def.";
            ls_costvalue = "";
            
            str_html = new Str_html();
            str_html.size       = "60";
            str_html.maxlength  = "250";
            str_html.css        = "in";
    
            %><%@include file="include/inc_cat_costanti.jsp" %><%


            // ----------------------------------
    
            ll_tkcost    = 0;
            ls_costname  = "mail_http_res";
            ls_costlabel = "URL Risorse";
            ls_costvalue = "";
            
            str_html = new Str_html();
            str_html.size       = "60";
            str_html.maxlength  = "250";
            str_html.css        = "in";

            ls_default_value = atk_ctrl.getBase_URL(request);
            
            if (ls_default_value.lastIndexOf("/") == (ls_default_value.length()-1) && (ls_default_value.length()-1) > 0){
    
                ls_default_value = ls_default_value.substring(0, (ls_default_value.length()-1));
            }


            %><%@include file="include/inc_cat_costanti.jsp" %><%


    %></table><%

    %></fieldset><%
    %><br/><%




    %><fieldset><%
        %><legend>Configuarazione Ordine</legend><%
	  
      

        %><table class="parametri"><%

            ll_tkcost    = 0;
            ls_costname  = "cdtord_ve";
            ls_costlabel = "Tipo Ordine Def.";
            ls_costvalue = "";

            str_html = new Str_html();

                        l_query   = "";
                		l_query  += "  select cdtorv                      \n";
                		l_query  += "       , dstorv                      \n";
                		l_query  += "    from pgmr.ut_art_tipordve        \n";
                		l_query  += "   where cdazie  = '"+req_cdazie+"'  \n";
    
                        rs = atk_sql.sql_query(l_query);

                        while(rs!=null && rs.next()){
                            String ls_cdtorv = "";
                            String ls_dstorv = "";
    
                            if (rs.getObject("cdtorv")!=null) ls_cdtorv  = rs.getString("cdtorv");
                            if (rs.getObject("dstorv")!=null) ls_dstorv  = rs.getString("dstorv");
    
                            str_html.vec_value.add(ls_cdtorv);
                            str_html.vec_descr.add(ls_dstorv);
                        }

            %><%@include file="include/inc_cat_costanti.jsp" %><%


            // ----------------------------------
            
            ll_tkcost    = 0;
            ls_costname  = "tkclie_def_ordi";
            ls_costlabel = "Cliente di default";
            ls_costvalue = "";
            
            str_html = new Str_html();
            str_html.size       = "20";
            str_html.maxlength  = "10";
            str_html.css        = "in in_cen";

            %><%@include file="include/inc_cat_costanti.jsp" %><%



            // ----------------------------------
            
            ll_tkcost    = 0;
            ls_costname  = "mail_ordi_mod";
            ls_costlabel = "Modello Mail Conf.Ordine";
            ls_costvalue = "";
            
            str_html = new Str_html();
            str_html.size       = "80";
            str_html.maxlength  = "250";
            str_html.css        = "in";

            %><%@include file="include/inc_cat_costanti.jsp" %><%

            // ----------------------------------

            for (int j=1; j<=3; j++){
                ll_tkcost    = 0;
                ls_costname  = "mail_ordi_bcc"+j;
                ls_costlabel = "Bcc Mail Conf.Ordine 0"+j;
                ls_costvalue = "";
                
                str_html = new Str_html();
                str_html.size       = "60";
                str_html.maxlength  = "250";
                str_html.css        = "in";
    
                %><%@include file="include/inc_cat_costanti.jsp" %><%
            }


        %></table><%

    %></fieldset><%
    %><br/><%
    %><br/><%



    %><fieldset><%
        %><legend>Banca Sella</legend><%

        %><table class="parametri"><%

            // ----------------------------------
            
            ll_tkcost    = 0;
            ls_costname  = "sella_shoplogin";
            ls_costlabel = "Shop Login";
            ls_costvalue = "";
            
            str_html = new Str_html();
            str_html.size       = "20";
            str_html.maxlength  = "250";
            str_html.css        = "in";

            %><%@include file="include/inc_cat_costanti.jsp" %><%


            // ----------------------------------
            
            ll_tkcost    = 0;
            ls_costname  = "sella_terminal_id";
            ls_costlabel = "Terminal ID";
            ls_costvalue = "";
            
            str_html = new Str_html();
            str_html.size       = "20";
            str_html.maxlength  = "250";
            str_html.css        = "in";

            %><%@include file="include/inc_cat_costanti.jsp" %><%
        
                
            // ----------------------------------
            
            ll_tkcost    = 0;
            ls_costname  = "sella_url";
            ls_costlabel = "URL Pagamento";
            ls_costvalue = "";
            
            str_html = new Str_html();
            str_html.size       = "80";
            str_html.maxlength  = "250";
            str_html.css        = "in";

            %><%@include file="include/inc_cat_costanti.jsp" %><%
            

        %></table><%

    %></fieldset><%



    }   // FINE if (!r_agente_web.equals("")){




%></form >

</td>
</tr>
</table>

<script language="javaScript">
    tot_righe = <%= idx_f %>;

    <% if (!message.equals("")){ %>
      <%  message = StringUtils.replace(message, "<br/>", "\n"); %>
      alert('<%= StringParsToHTML.escapeJS(message) %>')
      <%  message = ""; %>
    <% } %>

</script >

<%@include file="include/dbclose.jsp" %>
</body>
</html>





