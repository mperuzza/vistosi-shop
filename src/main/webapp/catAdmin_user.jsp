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

         import="com.ateikon.structure.Rec_cat_utente"

%><%

    %><%@include file="include/variabili_comuni.jsp" %><%


try {


    %><%@include file="include/dbconnect.jsp" %><%
    %><%@include file="include/parametri_ep.jsp" %><%
    %><%@include file="include/sicurezza_ep.jsp" %><%




    Cat_utente                cat_utente                = new Cat_utente();
    
    // imposto la connessione

    atk_sql.setProfilo((Atk_sql) cat_utente );




    // Variabili di appoggio


            


    // imposto le variabili di request

    if (request.getParameter("azione")!=null)  azione    = request.getParameter("azione");


    String r_cerca   = "";

    if (request.getParameter("cerca")!=null ) r_cerca   = request.getParameter("cerca");

    System.out.println("r_cerca >>"+r_cerca   +"<<");




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


    
    function atk_new(){
        atk_wait();
        self.location = 'catAdmin_user_new.jsp';
    }


    function atk_select( tkutente){
        atk_wait();
        self.location = 'catAdmin_user_edit.jsp?tkutente='+tkutente;
    }

    function atk_tipomapr_clie( ){
        atk_wait();
        self.location = 'catAdmin_tipomapr_clie_riep.jsp'
    }



<%@include file="js/atk_key.jsp" %>

    function atk_onSubmit(){
        atk_wait();
        return true;
    }

    
    function atk_close(){
        atk_wait();
        self.location = 'catIndex.jsp';
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


    <h2>Gestione Utenti</h2> 


<form name="form1" action="<%= seStesso %>" method="post">
        <input type="hidden" name="azione" value="report"/>
        <input type="hidden" name="nextPageRic"     value="<%=nextPageRic%>"/>
        <input type="hidden" name="current_page"    value="<%=current_page%>"/>
<%

    String []  _param_esclusi = new String[]{ "azione"
                                            , "nextPageRic"
                                            , "current_page"
                                            // -- Campi del Sort
                                            , "atk_campo_sort"
                                            , "fgasc"
                                            , "cerca"
                                            , "atk_campo_sort_type"
                                                    };

    out.print( atk_ctrl.getRequestHidden( request, _param_esclusi ) );





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
            str_html.value = "Exit";
            str_html.id    = "el~"+idx_f;  idx_f += 1;
            str_html.css   = "bt bt_75";
            str_html.onClick = "atk_close();";
            
            out.println(html.getButton(str_html));

            str_html = new Str_html();

            str_html.type  = "Submit";
            str_html.name  = "bt_ok";
            str_html.value = "Cerca";
            str_html.id    = "el~"+idx_f;  idx_f += 1;
            str_html.css   = "bt bt_75";

            out.println(html.getButton(str_html));

            str_html = new Str_html();

            str_html.type  = "button";
            str_html.name  = "bt_exit";
            str_html.value = "Nuovo &gt;&gt;";
            str_html.id    = "el~"+idx_f;  idx_f += 1;
            str_html.css   = "bt bt_75";
            str_html.onClick = "atk_new();";
            
            out.println(html.getButton(str_html));


            str_html = new Str_html();

            str_html.type  = "button";
            str_html.name  = "bt_cltm";
            str_html.value = "Cliente / Categoria";
            str_html.id    = "el~"+idx_f;  idx_f += 1;
            str_html.css   = "bt";
            str_html.style = "width:125px; margin:0px 0px 0px 150px;";
            str_html.onClick = "atk_tipomapr_clie();";
            
            out.println(html.getButton(str_html));








                    count_rec = 0;
                    boolean is_count = true;
                
                    rs = cat_utente.search(r_cerca
                                        , ""            // fgadmin
                                        , is_count
                                        , order_by
                                                    );
                
                    if (rs!=null && rs.next()){
                
                         if (rs.getObject(1)!=null) count_rec = rs.getInt(1);
                    }
                
                    

                    if (count_rec > 0){
                
                
                            // imposto la paginazione
                            // ----------------------
                            
                        %><%@include file="include/paginazione.jsp" %><%
                
                
                        is_count = false;
                    
                        rs = cat_utente.search(r_cerca
                                            , ""            // fgadmin
                                            , is_count
                                            , order_by
                                                        );
                    
                
                
                
                        if (li_recposi > 0){
                            if (rs!=null && rs.next()){
                                rs.relative(li_recposi-1);
                            }else {
                                rs = null;
                            }
                        }
                
                
                        if (rs !=null && rs.next()){
                    
                            %><%@include file="include/paginazione_html.jsp" %><%

                            %><table class="report"><%
                              %><tr><%
                                %><th></th><%
                                %><th>User Name</th><%
                                %><th>Descr. Utente</th><%
                                %><th>Tipo Utente</th><%
                                %><th>Ammin.</th><%
                              %></tr><%

                
                              String ls_css = "";
                
                
                              do {
                    
                                Rec_cat_utente lstr = new Rec_cat_utente ();

                
                                lstr.setResultSet(rs);

                                if (ls_css.equals("css1")){
                                    ls_css = "css2";
                                }else {
                                    ls_css = "css1";
                                }
                
                
                                %><tr class="<%= ls_css %>" onDblClick="atk_select('<%= lstr.tkutente %>' );" id="<%= "tr~"+idx_f %>"><%
                                    %><td nowrap><%
                                    
                                        %><input type="checkbox" name="ck_cdutente_m" value="<%= lstr.tkutente %>" onClick="atk_select('<%= lstr.tkutente %>' );"/><%
                                    
                                    %></td><%
                                    %><td nowrap><%= html.text(lstr.user_name) %></td><%
                                    %><td nowrap><%= html.text(lstr.dsutente)  %></td><%
                                    %><td nowrap><%
                                        if (lstr.fgadmin.equals("S")){
                                            %>Amministratore<%
                                        }else if (!lstr.cdutente.equals("")){
                                            %>Gestionale<%
                                        }else if (!lstr.cdagen.equals("")){
                                            %>Agente<%
                                        }else if (!lstr.tkclie.equals("")){
                                            %>Cliente<%
                                        }else if (!lstr.tkforn.equals("")){
                                            %>Fornitore<%
                                        }else {
                                            %>N.D.<%
                                        }
                                    %></td><%
                                    %><td nowrap><%
                                        
                                        if (lstr.fgadmin.equals("S")){
                                            
                                            %><img src="img/check.gif" /><%
                                        }else {
                                            %>&nbsp;<%
                                        }

                                    
                                    %></td><%
                                %></tr><%
                
                
                                      tot_rig += 1;
                                      idx_f   += 1;
                
                              }while(rs.next() && tot_rig<max_rig);
                    
                    
                            %></table><%
                    
                            %><%@include file="include/paginazione_html.jsp" %><%
                
                        } // FINE if rs
                    
                
                    }else {
                
                        %><h3><br/><br/><br/>&nbsp;&nbsp;&nbsp;Nessun Risultato.<br/><br/><br/><br/></h3><%
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


