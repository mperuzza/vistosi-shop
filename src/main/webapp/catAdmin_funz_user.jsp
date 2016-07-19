<%@ page import="java.sql.ResultSet"  
         import="java.sql.SQLException"
         import="java.sql.Timestamp"
         import="java.util.Vector"
         import="com.ateikon.common.Atk_sql"
         import="com.ateikon.common.Costanti_comm"

         import="com.ateikon.util.Atk_ctrl"
         import="com.ateikon.util.StringParsToHTML"
         import="com.ateikon.util.StringUtils"

         import="com.ateikon.common.Cat_funz"    
         import="com.ateikon.common.Cat_funz_user"    
         import="com.ateikon.common.Cat_utente"    

         import="com.ateikon.structure.Rec_cat_funz_user" 
         import="com.ateikon.structure.Str_html"

%><%                              


    %><%@include file="include/variabili_comuni.jsp" %><%

try {


    %><%@include file="include/dbconnect.jsp" %><%
    %><%@include file="include/parametri_ep.jsp" %><%
    %><%@include file="include/sicurezza_ep.jsp" %><%



    Cat_funz         cat_funz         = new Cat_funz();
    Cat_funz_user    cat_funz_user    = new Cat_funz_user();
    Cat_utente       cat_utente       = new Cat_utente();

    // imposto la connessione

    atk_sql.setProfilo((Atk_sql) cat_funz);
    atk_sql.setProfilo((Atk_sql) cat_funz_user);
    atk_sql.setProfilo((Atk_sql) cat_utente);
    


    // Variabili di appoggio


    String ck_albil = "N";

    // imposto le variabili di request

    if (request.getParameter("azione")!=null)   azione     = request.getParameter("azione");
    if (request.getParameter("ck_albil")!=null)   ck_albil     = request.getParameter("ck_albil");


    long tkfunzione = 0;

    try{

        if (request.getParameter("tkfunzione")!=null) tkfunzione = Long.parseLong(request.getParameter("tkfunzione"));
        
    }catch(Exception ex_tk){
    }




    if (azione.equals("salva") && tkfunzione > 0){

        Rec_cat_funz_user lstr = null;

        String[] arr_tkutente   = null;
    
        if (request.getParameterValues("tkutente"  )!=null)  arr_tkutente    = request.getParameterValues("tkutente"  );


        cat_funz_user.deleteFunzione(tkfunzione);
        
        cat_funz_user.m_connection.commit();


        for (int i=0; arr_tkutente!=null && i<arr_tkutente.length; i++){

            lstr = new Rec_cat_funz_user();

            lstr.tkfunzione  = tkfunzione;
            lstr.tkutente    = Long.parseLong(arr_tkutente[i]);
            lstr.profil_inse = s_user_name;


            tot_rec = cat_funz_user.execute(lstr );
    
            if (tot_rec > 0){
    
                cat_funz_user.m_connection.commit();
    
                azione = "carica";
    
            }else {
                throw new Exception("Errore in Update");
            }


        }

    }





%><html>
<head>
<%@include file="include/head_page.jsp" %>
<script language="JavaScript">

    function atk_onLoad(){

        <%@include file="js/atk_onLoad.jsp" %>
    }



    


    function atk_funzione(){

        self.location = 'catAdmin_funz.jsp?azione=carica';
    }
    

    function atk_salva(){

        document.form1.azione.value = 'salva';
        document.form1.submit();
    }
    function atk_refresh(){

        document.form1.azione.value = 'carica';
        document.form1.submit();
    }



<%@include file="js/atk_key.jsp" %>
    
    
    function atk_onSubmit(){
        return true;

    }
            
    function atk_close(){

        self.location = 'catAdmin_funz.jsp';
    }

    function atk_f1( ){ }
    function atk_f2( ){ }
    function atk_f3( ){ }
    function atk_paste(){  }
    


</script>

</head>


<body onLoad="atk_onLoad();">
<%@include file="include/head.jsp" %>
<%@include file="include/divAttesa.jsp" %>
<%
    %><div id="body_div" ><%

    %><h2>Gestione Funzioni: autorizzazione Utente</h2><%


    %><form name="form1" method="post" action="<%= seStesso %>" onSubmit="return atk_onSubmit();" ><%
    %><input type="hidden" name="azione" value="" /><%
    %><br/><%


            rs = cat_funz.getDropdown();
        
            out.println(html.getDropdown(rs, "tkfunzione", ""+tkfunzione, "onChange=\"atk_refresh()\" class=\"sl\""));

            str_html = new Str_html();
        
            str_html.type  = "button";
            str_html.name  = "bt_agg";
            str_html.value = "Ricarica";
            str_html.css   = "bt bt_75";
            str_html.id    = "el~"+idx_f;  idx_f += 1;
            str_html.onClick  = "atk_refresh();";

            out.println(html.getButton(str_html));


            if (tkfunzione > 0){
               
                str_html = new Str_html();
            
                str_html.type  = "button";
                str_html.name  = "bt_salva";
                str_html.value = "Salva";
                str_html.css   = "bt bt_75";
                str_html.id    = "el~"+idx_f;  idx_f += 1;
                str_html.onClick  = "atk_salva();";
    
                out.println(html.getButton(str_html));
            }

            str_html = new Str_html();
        
            str_html.type  = "button";
            str_html.name  = "bt_funz";
            str_html.value = "Exit";
            str_html.css   = "bt bt_75";
            str_html.id    = "el~"+idx_f;  idx_f += 1;
            str_html.onClick  = "atk_funzione();";

            out.println(html.getButton(str_html));
    
    
    %><br/><%
    %><br/><%

    %>Visualizza Solo Utenti Abilitati &nbsp;&nbsp;<%
    

        str_html = new Str_html();
        
        str_html.name  = "ck_albil";
        str_html.id    = "el~"+idx_f;  idx_f += 1;
        str_html.value = "S";
        str_html.onClick = "atk_refresh();";

        if (ck_albil.equals("S")){
            str_html.checked= "checked";
        }

        out.println(html.getCheckbox(str_html));
    
     
    %><br/><%
    %><br/><%

    %><table class="report"><%
      %><tr><%
        %><th></th><%
        %><th>Cod. Utente.</th><%
        %><th>Descr.</th><%
        %><th>Amministratore</th><%
        %><th>User Gest.</th><%
        %><th>Agente</th><%
        %><th>Cliente</th><%
        %><th>Fornitore</th><%
     %></tr><%

        String ls_css_bg = "css1";

        rs = null;

        if (tkfunzione > 0){

            rs = cat_utente.search("", "", false, "");
        }

        while (rs !=null && rs.next()){

            long   tkutente   = 0;
            String dsutente   = "";
            String user_name  = "";

            String tkclie   = "";
            String tkforn   = "";
            String cdagen   = "";
            String cdutente = "";
            String fgadmin  = "";

            boolean is_autorized = false;

            if (rs.getObject("tkutente"  )!=null ) tkutente   = rs.getLong("tkutente");
            if (rs.getObject("user_name" )!=null ) user_name  = rs.getString("user_name");
            if (rs.getObject("dsutente"  )!=null ) dsutente   = rs.getString("dsutente");

            if (rs.getObject("tkclie"     )!=null ) tkclie      = rs.getString("tkclie"   );
            if (rs.getObject("tkforn"     )!=null ) tkforn      = rs.getString("tkforn"   );
            if (rs.getObject("cdagen"     )!=null ) cdagen      = rs.getString("cdagen"   );
            if (rs.getObject("cdutente"   )!=null ) cdutente    = rs.getString("cdutente" );
            if (rs.getObject("fgadmin"    )!=null ) fgadmin     = rs.getString("fgadmin"  );





            rs_box = cat_funz_user.getKey(tkfunzione, tkutente);

            if (rs_box!=null && rs_box.next()){
                
                is_autorized = true;
            }

            if (ck_albil.equals("S") && !is_autorized ){
                continue;
            }

            if (ls_css_bg.equals("css1")){

                ls_css_bg = "css1";
            }else {
                ls_css_bg = "css0";
            }

            


            %><tr class="<%= ls_css_bg %>" ><%
                %><td><%
                
                    str_html = new Str_html();
                    
                    str_html.name  = "tkutente";
                    str_html.id    = "el~"+idx_f;  idx_f += 1;
                    str_html.value = ""+tkutente;
                    if (is_autorized){
                        str_html.checked= "checked";
                    }
        
                    out.println(html.getCheckbox(str_html));

                %></td><%
                %><td nowrap><%= user_name %></td><%
                %><td nowrap><%= dsutente %></td><%
                %><td nowrap><%
                    if (fgadmin.equals("S")){
                        %><img src="img/check.gif" /><%
                    }
                %></td><%
                %><td nowrap><%
                    if (!cdutente.equals("")){
                        %><img src="img/check.gif" /><%
                    }
                %></td><%
                %><td nowrap><%
                    if (!cdagen.equals("")){
                        %><img src="img/check.gif" /><%
                    }
                %></td><%
                %><td nowrap><%
                    if (!tkclie.equals("")){
                        %><img src="img/check.gif" /><%
                    }
                %></td><%
                %><td nowrap><%
                    if (!tkforn.equals("")){
                        %><img src="img/check.gif" /><%
                    }
                %></td><%
            %></tr><%



        }   // FINE while (rs !=null && rs.next()){



      
      
      %></table><%



    %></form><%
    %></div><%


%>
<%@include file="include/dbclose.jsp" %>
</body>
</html>








