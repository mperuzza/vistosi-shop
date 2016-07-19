<%@ page import="java.sql.ResultSet"  
         import="java.sql.SQLException"
         import="java.sql.Timestamp"
         import="java.util.Vector"
         import="com.ateikon.common.Atk_sql"
         import="com.ateikon.common.Ep_costanti"
         import="com.ateikon.common.Azienda"

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


    Azienda azienda = new Azienda();
     
    atk_sql.setProfilo((Atk_sql) azienda);


    // imposto la connessione


    // Variabili di appoggio

    String ep_cliente       = "";
    String ep_title         = "";
    String ep_logo_cliente  = "";
    String ep_logo_link     = "";
    String ep_mail_admin    = "";
    String ep_siteroot      = "";
    String ep_sitecontext   = "";
    String ep_tipo_login    = "";
    String ep_progenApplPath    = "";//avendramin20090119

    String mail_smtp_host = "";
    String mail_smtp_user = "";
    String mail_smtp_password = "";
    String mail_mittente  = "";


    String ep_tipo_keypools = "";
    String ep_agente_web    = "";
    
    String ep_eprogen_url      = "";
    String ep_eprogen_pivot_url      = "";
    String ep_newsletter_url      = "";

    String ls_tarags = "";

    rs = azienda.getKey(s_cdazie);

    if (rs != null && rs.next()){
        if (rs.getObject("tarags")!=null)  ls_tarags = rs.getString("tarags");
    }



    String req_cdazie = s_cdazie;
    String req_cdappl = "";
    String req_cddipa = "";
    String req_profil = "";


    ep_costanti.cdazie = req_cdazie;
    ep_costanti.cdappl = req_cdappl;
    ep_costanti.cddipa = req_cddipa;
    ep_costanti.profil = req_profil;


    // imposto le variabili di request

    azione = "carica";

    if (request.getParameter("azione"    )!=null)   azione     = request.getParameter("azione");
    if (request.getParameter("req_cdazie")!=null)   req_cdazie = request.getParameter("req_cdazie");
    
    if (request.getParameter("ep_cliente"     )!=null) ep_cliente      = request.getParameter("ep_cliente"     );
    if (request.getParameter("ep_title"       )!=null) ep_title        = request.getParameter("ep_title"       );
    if (request.getParameter("ep_logo_cliente")!=null) ep_logo_cliente = request.getParameter("ep_logo_cliente");
    if (request.getParameter("ep_logo_link"   )!=null) ep_logo_link    = request.getParameter("ep_logo_link"   );
    if (request.getParameter("ep_mail_admin"  )!=null) ep_mail_admin   = request.getParameter("ep_mail_admin"  );
    if (request.getParameter("ep_siteroot"    )!=null) ep_siteroot     = request.getParameter("ep_siteroot"    );
    if (request.getParameter("ep_sitecontext" )!=null) ep_sitecontext  = request.getParameter("ep_sitecontext" );
    if (request.getParameter("ep_tipo_login")!=null) ep_tipo_login = request.getParameter("ep_tipo_login");
    if (request.getParameter("ep_progenApplPath")!=null) ep_progenApplPath = request.getParameter("ep_progenApplPath");//avendramin20090119
    
    if (request.getParameter("mail_smtp_host")!=null) mail_smtp_host = request.getParameter("mail_smtp_host");
    if (request.getParameter("mail_smtp_user")!=null) mail_smtp_user = request.getParameter("mail_smtp_user");
    if (request.getParameter("mail_smtp_password")!=null) mail_smtp_password = request.getParameter("mail_smtp_password");
    if (request.getParameter("mail_mittente")!=null) mail_mittente = request.getParameter("mail_mittente");

    if (request.getParameter("ep_tipo_keypools")!=null) ep_tipo_keypools = request.getParameter("ep_tipo_keypools");
    if (request.getParameter("ep_agente_web")!=null) ep_agente_web = request.getParameter("ep_agente_web");

    if (request.getParameter("ep_eprogen_url"    )!=null) ep_eprogen_url     = request.getParameter("ep_eprogen_url"    );
    if (request.getParameter("ep_eprogen_pivot_url"    )!=null) ep_eprogen_pivot_url     = request.getParameter("ep_eprogen_pivot_url"    );
    if (request.getParameter("ep_newsletter_url"    )!=null) ep_newsletter_url     = request.getParameter("ep_newsletter_url"    );



    if (azione.equals("salva")){

        if (ep_siteroot.lastIndexOf(slash) != (ep_siteroot.length()-1)){

            ep_siteroot = ep_siteroot+slash;
        }

        ep_sitecontext = ep_sitecontext.replace('\\', '/');

        if (ep_sitecontext.lastIndexOf("/") != (ep_sitecontext.length()-1)){

            ep_sitecontext = ep_sitecontext+"/";
        }

        ep_eprogen_url = ep_eprogen_url.replace('\\', '/');

        if (ep_eprogen_url.lastIndexOf("/") != (ep_eprogen_url.length()-1)){

            ep_eprogen_url = ep_eprogen_url+"/";
        }
        
        ep_eprogen_pivot_url = ep_eprogen_pivot_url.replace('\\', '/');

        if (ep_eprogen_pivot_url.lastIndexOf("/") != (ep_eprogen_pivot_url.length()-1)){

            ep_eprogen_pivot_url = ep_eprogen_pivot_url+"/";
        }

        ep_newsletter_url = ep_newsletter_url.replace('\\', '/');

        if (ep_newsletter_url.lastIndexOf("/") != (ep_newsletter_url.length()-1)){

            ep_newsletter_url = ep_newsletter_url+"/";
        }

        // imposto i modelli alla base del sito


    
        ep_costanti.setCostvalue("ep.siteroot"    , ep_siteroot     , "ep: Site Root", req_cdazie, req_cdappl, req_cddipa, req_profil);
        ep_costanti.setCostvalue("ep.sitecontext" , ep_sitecontext  , "ep: Site Context", req_cdazie, req_cdappl, req_cddipa, req_profil);
        ep_costanti.setCostvalue("ep.cliente"     , ep_cliente      , "ep: Cliente", req_cdazie, req_cdappl, req_cddipa, req_profil);
        ep_costanti.setCostvalue("ep.tipo_keypools", ep_tipo_keypools, "ep: Tipo Keypool", req_cdazie, req_cdappl, req_cddipa, req_profil);
        ep_costanti.setCostvalue("ep.agente_web", ep_agente_web, "ep: Agente WEB", req_cdazie, req_cdappl, req_cddipa, req_profil);
        
        
        ep_costanti.setCostvalue("ep.title"       , ep_title        , "ep: title", req_cdazie, req_cdappl, req_cddipa, req_profil);
        ep_costanti.setCostvalue("ep.mail_admin"  , ep_mail_admin   , "ep: Mail Amministratore", req_cdazie, req_cdappl, req_cddipa, req_profil);
        ep_costanti.setCostvalue("ep.logo_cliente", ep_logo_cliente , "ep: src. Logo", req_cdazie, req_cdappl, req_cddipa, req_profil);
        ep_costanti.setCostvalue("ep.logo_link"   , ep_logo_link    , "ep: link Logo", req_cdazie, req_cdappl, req_cddipa, req_profil);
        ep_costanti.setCostvalue("ep.tipo_login"  , ep_tipo_login   , "ep: Tipo Login", req_cdazie, req_cdappl, req_cddipa, req_profil);
        ep_costanti.setCostvalue("ep.progenApplPath"  , ep_progenApplPath   , "ep: Progen Appl. Path", req_cdazie, req_cdappl, req_cddipa, req_profil);



        ep_costanti.setCostvalue("mail_smtp_host"  , mail_smtp_host   , "ep: Host SMTP", req_cdazie, req_cdappl, req_cddipa, req_profil);
        ep_costanti.setCostvalue("mail_smtp_user"  , mail_smtp_user   , "ep: SMTP User", req_cdazie, req_cdappl, req_cddipa, req_profil);
        ep_costanti.setCostvalue("mail_smtp_password"  , mail_smtp_password   , "ep: SMTP Password", req_cdazie, req_cdappl, req_cddipa, req_profil);
        ep_costanti.setCostvalue("mail_mittente"  , mail_mittente   , "ep: Mittente di Default", req_cdazie, req_cdappl, req_cddipa, req_profil);
       
        ep_costanti.setCostvalue("ep.eprogen_url"    , ep_eprogen_url     , "ep: URL eprogen", req_cdazie, req_cdappl, req_cddipa, req_profil);
        ep_costanti.setCostvalue("ep.eprogen_pivot_url"    , ep_eprogen_pivot_url     , "ep: URL eprogen pivot", req_cdazie, req_cdappl, req_cddipa, req_profil);
        ep_costanti.setCostvalue("ep.newsletter_url"    , ep_newsletter_url     , "ep: URL newsletter", req_cdazie, req_cdappl, req_cddipa, req_profil);
        
        message += "Configurazione salvata!\n";
        
        azione = "carica";


    }



    if (azione.equals("carica")){
    
        ep_siteroot         = ep_costanti.getCostvalue("ep.siteroot"    );
        ep_sitecontext      = ep_costanti.getCostvalue("ep.sitecontext" );
        ep_cliente          = ep_costanti.getCostvalue("ep.cliente"     );
        ep_tipo_keypools    = ep_costanti.getCostvalue("ep.tipo_keypools");
        ep_agente_web       = ep_costanti.getCostvalue("ep.agente_web");
        ep_title            = ep_costanti.getCostvalue("ep.title"       );
        ep_mail_admin       = ep_costanti.getCostvalue("ep.mail_admin"  );
        ep_logo_cliente     = ep_costanti.getCostvalue("ep.logo_cliente");
        ep_logo_link        = ep_costanti.getCostvalue("ep.logo_link"   );
                            
        ep_tipo_login       = ep_costanti.getCostvalue("ep.tipo_login"  );
        ep_progenApplPath       = ep_costanti.getCostvalue("ep.progenApplPath"  );
        
        mail_smtp_host      = ep_costanti.getCostvalue("mail_smtp_host"  );
        mail_smtp_user      = ep_costanti.getCostvalue("mail_smtp_user"  );
        mail_smtp_password  = ep_costanti.getCostvalue("mail_smtp_password"  );
        mail_mittente       = ep_costanti.getCostvalue("mail_mittente"  );

        ep_eprogen_url         = ep_costanti.getCostvalue("ep.eprogen_url"    );
        ep_eprogen_pivot_url         = ep_costanti.getCostvalue("ep.eprogen_pivot_url"    );
        ep_newsletter_url         = ep_costanti.getCostvalue("ep.newsletter_url"    );
    }


    if (ep_siteroot.equals("")) ep_siteroot = application.getRealPath("/");


        



%><html>
<head>
<%@include file="include/head_page.jsp" %>
<script language="JavaScript">

    function atk_onLoad(){

        <%@include file="js/atk_onLoad.jsp" %>
    }



    
    function atk_delete(tkfunzione){

        var ls_msg = 'Eliminare la Riga?';

        if (!confirm(ls_msg)){
            return;
        }

        document.form1.tkfunzione_del.value = tkfunzione;

        atk_salva();
    }


    

    function atk_salva(){

        document.form1.azione.value = 'salva';
        document.form1.submit();
    }
    function atk_refresh(){

        if (confirm('Attenzione!\n\n\tLe modifiche verranno perse.\n\tContinuare?')){
            document.form1.azione.value = 'carica';
            document.form1.submit();
        }
    }


    function atk_check(el, el_hiden_id){

        if (el.checked){
            document.getElementById(el_hiden_id).value = 'S';
        }else {
            document.getElementById(el_hiden_id).value = 'N';
        }
    }


    function atk_radio(el, el_hiden_id){

        document.getElementById(el_hiden_id).value = el.value;
    }



<%@include file="js/atk_key.jsp" %>
    
    
    function atk_onSubmit(){
        return true;
    }
            
    function atk_close(){
        self.location = 'epIndex.jsp';
    }

    function atk_f1( ){ }
    function atk_f2( ){ }
    function atk_f3( ){ }
    function atk_paste(){ }

    
    
    


</script>

</head>


<body onLoad="atk_onLoad();">
<%@include file="include/head.jsp" %>
<%@include file="include/divAttesa.jsp" %>
<%


    %><div id="body_div" ><%
    %><h2>Configurazione e-Progen </h2><%
    %><br/><%
    %><b>Azienda: </b> <%= html.text(ls_tarags) %><%
    %><br/><%
    %><br/><%
    
    String ep_dtagg_init = ep_costanti.getCostvalue("dtagg_init");
    
    String ep_dtagg_fine = ep_costanti.getCostvalue("dtagg_fine");

    %><b>Aggiornamento dati: </b> <%= ep_dtagg_init %> - <%= ep_dtagg_fine %><%
    %><br/><%
    %><br/><%

    %><form name="form1" method="post" action="<%= seStesso %>" onSubmit="return atk_onSubmit();" ><%
        %><input type="hidden" name="azione" value="" /><%


            str_html = new Str_html();
        
            str_html.type  = "button";
            str_html.name  = "bt_salva";
            str_html.value = "Salva [f2]";
            str_html.css   = "bt bt_75";
            str_html.id    = "el~"+idx_f;  idx_f += 1;
            str_html.onClick  = "atk_salva();";

            out.println(html.getButton(str_html));


            str_html = new Str_html();
        
            str_html.type  = "button";
            str_html.name  = "bt_ref";
            str_html.value = "Carica";
            str_html.css   = "bt bt_75";
            str_html.id    = "el~"+idx_f;  idx_f += 1;
            str_html.onClick  = "atk_refresh();";

            out.println(html.getButton(str_html));




            str_html = new Str_html();
        
            str_html.type  = "button";
            str_html.name  = "bt_ex";
            str_html.value = "Exit";
            str_html.css   = "bt bt_75";
            str_html.id    = "el~"+idx_f;  idx_f += 1;
            str_html.onClick  = "atk_close();";

            out.println(html.getButton(str_html));


    
    
    %><br/><%
    %><br/><%
    

    %><fieldset><%
        %><legend>Parametri di Base</legend><%
	  
      

    %><table class="parametri"><%


        %><tr><%
            %><td nowrap>Cliente: &nbsp;</td><%
            %><td><%


                    str_html = new Str_html();
                    
                    str_html.name  = "ep_cliente";
                    str_html.id    = "el~"+idx_f;  idx_f += 1;
                    str_html.value = ep_cliente;
                    str_html.css   = "sl";
                    str_html.style = "width:200px;";
                    
                    str_html.vec_value.add("VISTOSI");
                    str_html.vec_descr.add(" Vistosi SRL");

                    out.println(html.getDropdown(str_html));

            %></td><%
        %></tr><%

        %><tr><%
            %><td nowrap>Tipo Keypools: &nbsp;</td><%
            %><td><%

                    if (!ep_tipo_keypools.equals("GESTIONALE")) ep_tipo_keypools = "WEB";


                    str_html = new Str_html();
                    
                    str_html.name  = "ep_tipo_keypools";
                    str_html.id    = "el~"+idx_f;  idx_f += 1;
                    str_html.value = ep_tipo_keypools;
                    str_html.css   = "sl";
                    str_html.style = "width:200px;";

                    str_html.vec_value.add("WEB");
                    str_html.vec_descr.add(" Catalogo WEB");

                    str_html.vec_value.add("GESTIONALE");
                    str_html.vec_descr.add(" Gestionale");

                    out.println(html.getDropdown(str_html));

                    if (ep_tipo_keypools.equals("GESTIONALE")){
                        %> (pgmr.keypools)<%
                    }else {
                        %> (web.keypools)<%
                    }

            %></td><%
        %></tr><%
        %><tr><%
            %><td nowrap>Cdagen WEB: &nbsp;</td><%
            %><td><%

                    str_html = new Str_html();
                    
                    str_html.name  = "ep_agente_web";
                    str_html.id    = "el~"+idx_f;  idx_f += 1;
                    str_html.value = ep_agente_web;
                    str_html.size  = "10";
                    str_html.maxlength  = "10";
                    str_html.css   = "in";
        
                    out.println(html.getText_box(str_html));

            %></td><%
        %></tr><%

        %><tr><%
            %><td nowrap>Title: &nbsp;</td><%
            %><td><%
                    str_html = new Str_html();
                    
                    str_html.name  = "ep_title";
                    str_html.id    = "el~"+idx_f;  idx_f += 1;
                    str_html.value = ep_title;
                    str_html.size  = "20";
                    str_html.maxlength  = "20";
                    str_html.css   = "in";
        
                    out.println(html.getText_box(str_html));

            %></td><%
        %></tr><%
        %><tr><%
            %><td nowrap>Mail Ammin.: &nbsp;</td><%
            %><td><%
                    str_html = new Str_html();
                    
                    str_html.name  = "ep_mail_admin";
                    str_html.id    = "el~"+idx_f;  idx_f += 1;
                    str_html.value = ep_mail_admin;
                    str_html.size  = "40";
                    str_html.maxlength  = "80";
                    str_html.css   = "in";
        
                    out.println(html.getText_box(str_html));

            %></td><%
        %></tr><%
        
        %><tr><%
            %><td nowrap>Site Root: &nbsp;</td><%
            %><td><%
                    str_html = new Str_html();
                    
                    str_html.name  = "ep_siteroot";
                    str_html.id    = "el~"+idx_f;  idx_f += 1;
                    str_html.value = ep_siteroot;
                    str_html.size  = "80";
                    str_html.maxlength  = "250";
                    str_html.css   = "in";
        
                    out.println(html.getText_box(str_html));

            %></td><%
        %></tr><%
        %><tr><%
            %><td nowrap>Site Context: &nbsp;</td><%
            %><td><%
                    str_html = new Str_html();
                    
                    str_html.name  = "ep_sitecontext";
                    str_html.id    = "el~"+idx_f;  idx_f += 1;
                    str_html.value = ep_sitecontext;
                    str_html.size  = "20";
                    str_html.maxlength  = "20";
                    str_html.css   = "in";
        
                    out.println(html.getText_box(str_html));

            %></td><%
        %></tr><%
        %><tr><%
            %><td nowrap>Path Logo: &nbsp;</td><%
            %><td><%
                    str_html = new Str_html();
                    
                    str_html.name  = "ep_logo_cliente";
                    str_html.id    = "el~"+idx_f;  idx_f += 1;
                    str_html.value = ep_logo_cliente;
                    str_html.size  = "60";
                    str_html.maxlength  = "250";
                    str_html.css   = "in";
        
                    out.println(html.getText_box(str_html));

            %></td><%
        %></tr><%
        %><tr><%
            %><td nowrap>Link Logo: &nbsp;</td><%
            %><td><%
                    str_html = new Str_html();
                    
                    str_html.name  = "ep_logo_link";
                    str_html.id    = "el~"+idx_f;  idx_f += 1;
                    str_html.value = ep_logo_link;
                    str_html.size  = "60";
                    str_html.maxlength  = "250";
                    str_html.css   = "in";
        
                    out.println(html.getText_box(str_html));

            %></td><%
        %></tr><%
        
        %><tr><%
            %><td nowrap>Login: &nbsp;</td><%
            %><td><%
                    str_html = new Str_html();
                    
                    str_html.name  = "ep_tipo_login";
                    str_html.id    = "el~"+idx_f;  idx_f += 1;
                    str_html.value = ep_tipo_login;
                    str_html.css   = "sl";
                    str_html.style = "width:200px;";

                    str_html.vec_value.add("ep_utente");
                    str_html.vec_descr.add("Table User (Standard) ");

                    str_html.vec_value.add("DB-User");
                    str_html.vec_descr.add("DataBase User");

                    out.println(html.getDropdown(str_html));

            %></td><%
        %></tr><%

        %><tr><%
            %><td nowrap>Progen Appl. Path: &nbsp;</td><%
            %><td><%
                    str_html = new Str_html();
                    
                    str_html.name  = "ep_progenApplPath";
                    str_html.id    = "el~"+idx_f;  idx_f += 1;
                    str_html.value = ep_progenApplPath;
                    str_html.size  = "60";
                    str_html.maxlength  = "250";
                    str_html.css   = "in";
        
                    out.println(html.getText_box(str_html));

            %></td><%
        %></tr><%

    %></table><%

    %></fieldset><%
    %><br/><%




    %><fieldset><%
        %><legend>Mail</legend><%
	  
      

        %><table class="parametri"><%
            %><tr><%
                %><td nowrap>SMTP: &nbsp;</td><%
                %><td><%
    
                        str_html = new Str_html();
                        
                        str_html.name  = "mail_smtp_host";
                        str_html.id    = "el~"+idx_f;  idx_f += 1;
                        str_html.value = mail_smtp_host;
                        str_html.size  = "40";
                        str_html.maxlength  = "250";
                        str_html.css   = "in";
            
                        out.println(html.getText_box(str_html));
    
                %></td><%
            %></tr><%
            %><tr><%
                %><td nowrap>SMTP User: &nbsp;</td><%
                %><td><%
    
                        str_html = new Str_html();
                        
                        str_html.name  = "mail_smtp_user";
                        str_html.id    = "el~"+idx_f;  idx_f += 1;
                        str_html.value = mail_smtp_user;
                        str_html.size  = "20";
                        str_html.maxlength  = "40";
                        str_html.css   = "in";
            
                        out.println(html.getText_box(str_html));
    
                %></td><%
            %></tr><%
            %><tr><%
                %><td nowrap>SMTP Password: &nbsp;</td><%
                %><td><%
    
                        str_html = new Str_html();
                        
                        str_html.name  = "mail_smtp_password";
                        str_html.id    = "el~"+idx_f;  idx_f += 1;
                        str_html.value = mail_smtp_password;
                        str_html.size  = "20";
                        str_html.maxlength  = "40";
                        str_html.css   = "in";
            
                        out.println(html.getText_box(str_html));
    
                %></td><%
            %></tr><%
            %><tr><%
                %><td nowrap>Mittente Def.: &nbsp;</td><%
                %><td><%
    
                        str_html = new Str_html();
                        
                        str_html.name  = "mail_mittente";
                        str_html.id    = "el~"+idx_f;  idx_f += 1;
                        str_html.value = mail_mittente;
                        str_html.size  = "50";
                        str_html.maxlength  = "255";
                        str_html.css   = "in";
            
                        out.println(html.getText_box(str_html));
    
                %></td><%
            %></tr><%

    %></table><%

    %></fieldset><%
    %><br/><%


    %><fieldset><%
        %><legend>Context Esterni</legend><%
	  
      

        %><table class="parametri"><%
          %><tr><%
              %><td nowrap>URL E-progen: &nbsp;</td><%
              %><td><%
                      str_html = new Str_html();

                      str_html.name  = "ep_eprogen_url";
                      str_html.id    = "el~"+idx_f;  idx_f += 1;
                      str_html.value = ep_eprogen_url;
                      str_html.size  = "80";
                      str_html.maxlength  = "250";
                      str_html.css   = "in";

                      out.println(html.getText_box(str_html));

              %></td><%
          %></tr><%
          %><tr><%
              %><td nowrap>URL E-progen Pivot: &nbsp;</td><%
              %><td><%
                      str_html = new Str_html();

                      str_html.name  = "ep_eprogen_pivot_url";
                      str_html.id    = "el~"+idx_f;  idx_f += 1;
                      str_html.value = ep_eprogen_pivot_url;
                      str_html.size  = "80";
                      str_html.maxlength  = "250";
                      str_html.css   = "in";

                      out.println(html.getText_box(str_html));

              %></td><%
          %></tr><%
          %><tr><%
              %><td nowrap>URL Newsletter: &nbsp;</td><%
              %><td><%
                      str_html = new Str_html();

                      str_html.name  = "ep_newsletter_url";
                      str_html.id    = "el~"+idx_f;  idx_f += 1;
                      str_html.value = ep_newsletter_url;
                      str_html.size  = "80";
                      str_html.maxlength  = "250";
                      str_html.css   = "in";

                      out.println(html.getText_box(str_html));

              %></td><%
          %></tr><%

         %></table><%

    %></fieldset><%
    %><br/><%

    %></form><%
    %></div><%



%>
<%@include file="include/dbclose.jsp" %>
</body>
</html>



