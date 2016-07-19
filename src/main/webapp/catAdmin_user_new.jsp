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
         import="com.ateikon.common.Archagen" 
         import="com.ateikon.common.Archclie" 
         import="com.ateikon.common.Archforn" 
         import="com.ateikon.common.Atk_utente" 

         import="com.ateikon.function.F_utente" 

         import="com.ateikon.structure.Rec_cat_utente"

%><%

    %><%@include file="include/variabili_comuni.jsp" %><%


try {


    %><%@include file="include/dbconnect.jsp" %><%
    %><%@include file="include/parametri_ep.jsp" %><%
    %><%@include file="include/sicurezza_ep.jsp" %><%




    Cat_utente  cat_utente  = new Cat_utente();
    Archagen   archagen   = new Archagen();
    Archclie   archclie   = new Archclie();
    Archforn   archforn   = new Archforn();
    Atk_utente atk_utente = new Atk_utente();

    F_utente   f_utente   = new F_utente();
    
    // imposto la connessione

    atk_sql.setProfilo((Atk_sql) cat_utente  );
    atk_sql.setProfilo((Atk_sql) archagen   );
    atk_sql.setProfilo((Atk_sql) archclie   );
    atk_sql.setProfilo((Atk_sql) archforn   );
    atk_sql.setProfilo((Atk_sql) atk_utente );

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

    if (request.getParameter("azione")!=null)  azione    = request.getParameter("azione");


    String r_cerca  = "";
    String r_tpuser = "";
    String r_tpuser_descr = "";

    if (request.getParameter("cerca" )!=null ) r_cerca  = request.getParameter("cerca");
    if (request.getParameter("tpuser")!=null ) r_tpuser = request.getParameter("tpuser");



    for (int i=0; arr_tpuser!=null && i<arr_tpuser.length; i++){
        if (arr_tpuser[i].equals(r_tpuser)) r_tpuser_descr = arr_tpuser_descr[i];
    }




    if (azione.equals("aggiungi")){
        
        String r_token = "";

        if (request.getParameter("token")!=null ) r_token = request.getParameter("token");


        long ll_tkutente = f_utente.inserisci_utente( r_tpuser, r_token );

        if (ll_tkutente > 0){

            f_utente.m_connection.commit();

            response.sendRedirect("catAdmin_user_edit.jsp?tkutente="+ll_tkutente);

            return;

        }else {
            f_utente.m_connection.rollback();
            message += "Attenzione Errore Creazione Utente!";
        }

    }



    // Paginazione 
    // -----------

    int      max_rig          = 200;
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


    
    function atk_chg_tpuser( tpuser){
        atk_wait();

        document.form1.tpuser.value = tpuser;
        document.form1.submit();
    }

    function atk_select( token){
        atk_wait();

        document.form1.azione.value = 'aggiungi';
        document.form1.token.value  = token;
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
        
        if (r_tpuser.equals("")){
            %>self.location = 'catAdmin_user.jsp'; <%
        }else {
            %>self.location = '<%= seStesso %>'; <%
        }

        %>
        
    }

    function atk_f1( ){ }

    function atk_f2( ){ }

    function atk_f3( ){ }

    function atk_paste(){ }



</script>

</head>



<body onLoad="atk_onload();" >
<%@include file="include/head.jsp" %>
<%@include file="include/divAttesa.jsp" %>

<div id="body_div" >


    <h2>Gestione Utenti: Nuovo <%= html.text(r_tpuser_descr) %></h2> 


<form name="form1" action="<%= seStesso %>" method="post">
        <input type="hidden" name="azione" value="report" />
        <input type="hidden" name="nextPageRic"     value="<%= nextPageRic %>" />
        <input type="hidden" name="current_page"    value="<%= current_page %>" />
        <input type="hidden" name="tpuser"          value="<%= r_tpuser %>" />
        <input type="hidden" name="token"           value="" />
        
<%


            %><fieldset><%
                %><legend>Parametri</legend><%
        
                %><table class="parametri"><%
                    %><tr><%
                        %><th>Cerca:</th><%
                        %><td><%
        
                            str_html = new Str_html();
                            
                            str_html.name       = "cerca";
                            str_html.value      = r_cerca;
                            str_html.id         = "el~"+idx_f;  idx_f += 1;
                            str_html.size       = "60";
                            str_html.maxlength  = "80";
                            str_html.css        = "in";
                            
                            out.println(html.getText_box(str_html));
        
                        
                        
                        %></td><%
                    %></tr><%
        
                    %><tr><%
                        %><td colspan="2"><%
        
        
                        %></td><%
                    %></tr><%
                %></table><%
        
        
        
            %></fieldset><%
                
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

            if (!r_tpuser.equals("")){
                str_html = new Str_html();
    
                str_html.type  = "Submit";
                str_html.name  = "bt_ok";
                str_html.value = "Cerca";
                str_html.id    = "el~"+idx_f;  idx_f += 1;
                str_html.css   = "bt bt_75";
    
                out.println(html.getButton(str_html));
    
                
                if (r_tpuser.equals("ADMIN")){

                    str_html = new Str_html();
        
                    str_html.type  = "button";
                    str_html.name  = "bt_exit";
                    str_html.value = "Avanti &gt;&gt;";
                    str_html.id    = "el~"+idx_f;  idx_f += 1;
                    str_html.css   = "bt bt_75";
                    str_html.onClick = "atk_select(0);";
                    
                    out.println(html.getButton(str_html));
                }

            }   // FINE if (!r_tpuser.equals("")){


            if (r_tpuser.equals("")){

                %><br/><%
                %><br/><%
                %>Selezionare il Tipo Di Utente:<%
                %><br/><%
                %><br/><%

                tot_rec = 0;


                %><table class="parametri"><%

                    for(int i=0; arr_tpuser!=null && i<arr_tpuser.length; i++){
                        %><tr class="css<%= ((++tot_rec%2)+1)%>"><%
                            %><td width="300" height="50"><%= html.text(arr_tpuser_descr[i]) %></td><%
                            %><td><%
                            
                                str_html = new Str_html();
                    
                                str_html.type  = "button";
                                str_html.name  = "bt_tpuser";
                                str_html.value = " &gt;&gt; ";
                                str_html.id    = "el~"+idx_f;  idx_f += 1;
                                str_html.css   = "bt";
                                str_html.onClick = "atk_chg_tpuser('"+arr_tpuser[i]+"');";
                                
                                out.println(html.getButton(str_html));
    
                            
                            %></td><%
                        %></tr><%
                    }

                %></table><%

                

            }










            // ******
            // ******
            // ******   Lista 
            // ******




                    count_rec = 0;
                    boolean is_count = true;

                    if (r_tpuser.equals("GEST")){

                        rs = atk_utente.search( ""         // r_cdutente_m
                                              , ""         // r_dsutente
                                              , s_cdazie
                                              , s_cddipa
                                              , r_cerca
                                              , is_count
                                              , order_by
                                                  );
                                      System.out.println("xxx >>"+atk_utente.l_query+"<<");
                    }else if (r_tpuser.equals("AGEN")){

                        rs = archagen.search( ""         // r_cdagen_m
                                            , ""         // r_dsagen
                                            , r_cerca
                                            , is_count
                                            , order_by
                                                        );

                    }else if (r_tpuser.equals("CLIE")){

                        rs = archclie.search  ( r_cerca    // f_search
                                              , ""         // cdagen   
                                              , ""         // cdclie_m 
                                              , ""         // pariva
                                              , is_count
                                              , order_by
                                                        );

                    }else if (r_tpuser.equals("FORN")){
                    }else if (r_tpuser.equals("ADMIN")){

                        rs = cat_utente.search ( r_cerca
                                             , "S"
                                             , is_count        
                                             , order_by        
                                               );
                    }



                
                
                    if (rs!=null && rs.next()){
                
                         if (rs.getObject(1)!=null) count_rec = rs.getInt(1);
                    }
                
                    

                    if (count_rec > 0){
                
 
                            // imposto la paginazione
                            // ----------------------
                            
                        %><%@include file="include/paginazione.jsp" %><%
                
                
                        is_count = false;
                    
            
                                    if (r_tpuser.equals("GEST")){


                                        rs = atk_utente.search( ""         // r_cdutente_m
                                                              , ""         // r_dsutente
                                                              , s_cdazie
                                                              , s_cddipa
                                                              , r_cerca
                                                              , is_count
                                                              , order_by
                                                                  );


                                    }else if (r_tpuser.equals("AGEN")){

                                        rs = archagen.search( ""         // r_cdagen_m
                                                            , ""         // r_dsagen
                                                            , r_cerca
                                                            , is_count
                                                            , order_by
                                                                        );

                                    }else if (r_tpuser.equals("CLIE")){
                
                                        rs = archclie.search ( r_cerca    // f_search
                                                             , ""         // cdagen   
                                                             , ""         // cdclie_m 
                                                             , ""         // pariva
                                                             , is_count
                                                             , order_by
                                                                        );
                
                                    }else if (r_tpuser.equals("FORN")){
                                    }else if (r_tpuser.equals("ADMIN")){

                                        rs = cat_utente.search ( r_cerca
                                                             , "S"
                                                             , is_count        
                                                             , order_by        
                                                               );
                                    }
            



                        if (li_recposi > 0){
                            if (rs!=null && rs.next()){
                                rs.relative(li_recposi-1);
                            }else {
                                rs = null;
                            }
                        }
                


                                    if (r_tpuser.equals("GEST")){

                                        %><%@include file="include/lista_atk_utente.jsp" %><%

                                    }else if (r_tpuser.equals("AGEN")){
                
                                        %><%@include file="include/lista_archagen.jsp" %><%
                
                                    }else if (r_tpuser.equals("CLIE")){
                
                                        %><%@include file="include/lista_archclie.jsp" %><%
                
                                    }else if (r_tpuser.equals("FORN")){
                                    }else if (r_tpuser.equals("ADMIN")){

                                        %><%@include file="include/lista_cat_utente.jsp" %><%
                                    }
            
                        


                    }else {
                
                        %>&nbsp;<%
                    }


   
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


