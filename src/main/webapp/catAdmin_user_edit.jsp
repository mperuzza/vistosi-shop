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

         import="com.ateikon.common.Cat_utente" 

         import="com.ateikon.function.F_utente" 

         import="com.ateikon.structure.Rec_cat_utente"

%><%

    %><%@include file="include/variabili_comuni.jsp" %><%


try {


    %><%@include file="include/dbconnect.jsp" %><%
    %><%@include file="include/parametri_ep.jsp" %><%
    %><%@include file="include/sicurezza_ep.jsp" %><%




    Cat_utente  cat_utente  = new Cat_utente();

    F_utente   f_utente   = new F_utente();
    
    // imposto la connessione

    atk_sql.setProfilo((Atk_sql) cat_utente  );
    atk_sql.setProfilo((Atk_sql) f_utente   );




    // Variabili di appoggio



    String[] arr_tpuser = new String[]{ "GEST"
                                      , "AGEN"
                                      , "CLIE"
                                      , "FORN"
                                      , "ADMIN"
                                        };

    String[] arr_tpuser_descr = new String[]{ "Gestionale"
                                      , "Agente"
                                      , "Ciente"
                                      , "Fornitore"
                                      , "Amministratore"
                                        };
    


    // imposto le variabili di request

    long r_tkutente = 0;

    if (request.getParameter("azione")!=null)  azione    = request.getParameter("azione");
    if (request.getParameter("tkutente")!=null)  r_tkutente    = Long.parseLong(request.getParameter("tkutente"));


    // --- retrieve dei dati dal DB

    Rec_cat_utente lstr = new Rec_cat_utente();

    rs = cat_utente.getKey(r_tkutente);

    if (rs!=null && rs.next()){

        lstr.setResultSet(rs);
    }

    String r_tpuser = "";
    String r_tpuser_descr = "";

    if (!lstr.cdutente.equals("")) r_tpuser = "GEST";
    if (!lstr.cdagen.equals("")  ) r_tpuser = "AGEN";
    if (!lstr.tkclie.equals("")  ) r_tpuser = "CLIE";
    if (!lstr.tkforn.equals("")  ) r_tpuser = "FORN";
    if (lstr.fgadmin.equals("S") ) r_tpuser = "ADMIN";

    for (int i=0; arr_tpuser!=null && i<arr_tpuser.length; i++){
        if (arr_tpuser[i].equals(r_tpuser)) r_tpuser_descr = arr_tpuser_descr[i];
    }






    // --- retrieve dei dati dalla request


    String ls_password      = "";
    String ls_password_conf = "";


    if (request.getParameter("user_name"    )!=null)  lstr.user_name    = request.getParameter("user_name"    );
    if (request.getParameter("dsutente"     )!=null)  lstr.dsutente     = request.getParameter("dsutente"     );
    if (request.getParameter("email"        )!=null)  lstr.email        = request.getParameter("email"        );
    if (request.getParameter("password"     )!=null)  ls_password       = request.getParameter("password"     );
    if (request.getParameter("password_conf")!=null)  ls_password_conf  = request.getParameter("password_conf");



    if (azione.equals("salva")){

        tot_rec = f_utente.aggiorna( lstr
                                   , ls_password
                                   , ls_password_conf 
                                        );

        if (tot_rec == 1){

            f_utente.m_connection.commit();

            message += "Dati Aggiornati!\n";

        }else {
            f_utente.m_connection.rollback();
            message += f_utente.message;
        }
    }



    if (azione.equals("delete")){

        tot_rec = cat_utente.delete(r_tkutente);

        if (tot_rec == 1){

            cat_utente.m_connection.commit();

            response.sendRedirect("catAdmin_user.jsp");
    
            return;

        }else {
            f_utente.m_connection.rollback();
            message += "Attenzione Errore Cancellazione Utente!\n";
        }
    }

    if (azione.equals("login as") && s_fgadmin.equals("S")){

        String ls_pwd = atk_ctrl.decode(lstr.password);


        String ls_url = "";
         
        ls_url += "catLogin.jsp?azione=re-login";
        ls_url += "&user_name="+lstr.user_name;
        ls_url += "&pwd="+ls_pwd;
        ls_url += "&cdazie="+s_cdazie;
        ls_url += "&cddipa="+s_cddipa;

        response.sendRedirect(ls_url);

        return;
    }







    // Paginazione 
    // -----------

    int      max_rig          = 20;
    String   nextPageRic      = "";
    String   current_page     = "1";
    int      li_current_page  = 0;
    int      li_numpage       = 0;
    int      li_totpage       = 0;
    int      count_rec        = 0;
    int      num_pag_visu     = 15;
    int      tot_rig          = 0;


%><html>
<head>
<%@include file="include/head_page.jsp" %>
<script language="JavaScript">
    
    function atk_onload(){

        <%@include file="js/atk_onLoad.jsp" %>
    }


    

    function atk_salva( ){
        atk_wait();

        document.form1.azione.value = 'salva';
        document.form1.submit();

        return true;
    }

    function atk_delete( ){
        
        var ls_ = 'Eliminare l\'utente?';

        if (!confirm(ls_)){
            return;
        }

        atk_wait();
        document.form1.azione.value = 'delete';
        document.form1.submit();
    }

    
    function atk_auth( tkutente ){
        atk_wait();
        self.location = 'catAdmin_user_perm.jsp?tkutente='+tkutente;
    }

    function atk_refresh(){
        atk_wait();
        self.location = '<%= seStesso %>?tkutente=<%= r_tkutente %>';
    }

    
    function atk_login_as(){
        atk_wait();

        document.form1.azione.value = 'login as';
        document.form1.submit();

    }


<%@include file="js/atk_key.jsp" %>

    function atk_onSubmit(){
        atk_wait();
        return true;
    }

    
    function atk_close(){
        atk_wait();
        <%
        
        %>self.location = 'catAdmin_user.jsp'; <%
        
        %>
        
    }

    function atk_f1( ){ }

    function atk_f2( ){ }

    function atk_f3( ){ }

    function atk_paste(){ }

    
    function atk_sessione(){ 
    
        var ls_ = '';

        ls_ += 'tkutente = <%= r_tkutente %>\n';
        ls_ += 'cdazie = <%= atk_sql.cdazie %>\n';
        ls_ += 'cddipa = <%= atk_sql.cddipa %>\n';

        alert(ls_);

    }


</script>

</head>



<body onLoad="atk_onload();" >
<%@include file="include/head.jsp" %>
<%@include file="include/divAttesa.jsp" %>

<div id="body_div" >


    <h2 onDblClick="atk_sessione();">Gestione Utenti: Modifica <%= html.text(r_tpuser_descr) %></h2> 


<form name="form1" action="<%= seStesso %>" method="post" onSubmit="return atk_salva();">
        <input type="hidden" name="azione" value="report" />
        <input type="hidden" name="nextPageRic"     value="<%= nextPageRic %>" />
        <input type="hidden" name="current_page"    value="<%= current_page %>" />
        <input type="hidden" name="tkutente"        value="<%= r_tkutente %>" />
        
<%


        
                %><table class="parametri"><%
                    %><tr><%
                        %><th>User Name:</th><%
                        %><td><%
        
                            str_html = new Str_html();
                            
                            str_html.name       = "user_name";
                            str_html.value      = lstr.user_name;
                            str_html.id         = "el~"+idx_f;  idx_f += 1;
                            str_html.size       = "30";
                            str_html.maxlength  = "100";
                            str_html.css        = "in";
                            
                            out.println(html.getText_box(str_html));

                            if (s_fgadmin.equals("S")){
                                str_html = new Str_html();
                    
                                str_html.type  = "button";
                                str_html.name  = "bt_exit";
                                str_html.value = "Login as ...";
                                str_html.id    = "el~"+idx_f;  idx_f += 1;
                                str_html.css   = "bt bt_75";
                                str_html.onClick = "atk_login_as();";
                                
                                out.println(html.getButton(str_html));
                            }
                        %></td><%
                    %></tr><%
                    %><tr><%
                        %><th>Password:</th><%
                        %><td nowrap><%
        
                            str_html = new Str_html();
                            
                            str_html.type       = "password";
                            str_html.name       = "password";
                            str_html.value      = "";
                            str_html.id         = "el~"+idx_f;  idx_f += 1;
                            str_html.size       = "30";
                            str_html.maxlength  = "100";
                            str_html.css        = "in";
                            
                            out.println(html.getText_box(str_html));
        
                            %><b style="padding:0px 5px 0px 30px">Conf. Password:</b><%

                            str_html = new Str_html();
                            
                            str_html.type       = "password";
                            str_html.name       = "password_conf";
                            str_html.value      = "";
                            str_html.id         = "el~"+idx_f;  idx_f += 1;
                            str_html.size       = "30";
                            str_html.maxlength  = "100";
                            str_html.css        = "in";
                            
                            out.println(html.getText_box(str_html));


                        %></td><%
                    %></tr><%
                    %><tr><%
                        %><td colspan="2">&nbsp;</td><%
                    %></tr><%
                    %><tr><%
                        %><th>Descrizione:</th><%
                        %><td><%
        
                            str_html = new Str_html();
                            
                            str_html.name       = "dsutente";
                            str_html.value      = lstr.dsutente;
                            str_html.id         = "el~"+idx_f;  idx_f += 1;
                            str_html.size       = "80";
                            str_html.maxlength  = "100";
                            str_html.css        = "in";
                            
                            out.println(html.getText_box(str_html));
        
                        
                        
                        %></td><%
                    %></tr><%
                    %><tr><%
                        %><th>e-Mail:</th><%
                        %><td><%
        
                            str_html = new Str_html();
                            
                            str_html.type       = "email";
                            str_html.name       = "email";
                            str_html.value      = lstr.email;
                            str_html.id         = "el~"+idx_f;  idx_f += 1;
                            str_html.size       = "30";
                            str_html.maxlength  = "100";
                            str_html.css        = "in";
                            
                            out.println(html.getText_box(str_html));
        
                        %></td><%
                    %></tr><%
        
                    %><tr><%
                        %><td colspan="2"><%
        
        
                        %></td><%
                    %></tr><%
                %></table><%
        
        
        
                
            %><br/><%
            %><br/><%

            str_html = new Str_html();

            str_html.type  = "button";
            str_html.name  = "bt_exit";
            str_html.value = "&lt;&lt; Exit";
            str_html.id    = "el~"+idx_f;  idx_f += 1;
            str_html.css   = "bt bt_75";
            str_html.onClick = "atk_close();";
            
            out.println(html.getButton(str_html));

            str_html = new Str_html();

            str_html.type  = "button";
            str_html.name  = "bt_ok";
            str_html.value = "Riacarica";
            str_html.id    = "el~"+idx_f;  idx_f += 1;
            str_html.css   = "bt bt_75";
            str_html.onClick = "atk_refresh();";

            out.println(html.getButton(str_html));

            str_html = new Str_html();

            str_html.type  = "Submit";
            str_html.name  = "bt_exit";
            str_html.value = "Salva";
            str_html.id    = "el~"+idx_f;  idx_f += 1;
            str_html.css   = "bt bt_75";
            
            out.println(html.getButton(str_html));

            str_html = new Str_html();

            str_html.type  = "button";
            str_html.name  = "bt_exit";
            str_html.value = "Elimina";
            str_html.id    = "el~"+idx_f;  idx_f += 1;
            str_html.css   = "bt bt_75";
            str_html.onClick = "atk_delete();";
            
            out.println(html.getButton(str_html));


            str_html = new Str_html();

            str_html.type  = "button";
            str_html.name  = "bt_exit";
            str_html.value = "Permessi &gt;&gt;";
            str_html.id    = "el~"+idx_f;  idx_f += 1;
            str_html.css   = "bt bt_75";
            str_html.onClick = "atk_auth('"+r_tkutente+"');";
            
            out.println(html.getButton(str_html));


   
%>
</form>


<iframe src="blankPage.jsp?visibility=hidden" name="iframeSel" id="iframeSel"
                    width="0"
                    height="0"
                    scrolling="auto"
                    frameborder="0"
                    marginheigth="0"
                    marginwidth="0"></iframe>



</div>
<%


%><%@include file="include/dbclose.jsp" %>
</body>
</html>


