<%@ page import="java.sql.ResultSet"  
         import="java.sql.SQLException"
         import="java.sql.Timestamp"
         import="java.sql.PreparedStatement"
         import="java.util.Vector"
         import="com.ateikon.common.Atk_sql"
         import="com.ateikon.common.Costanti_comm"

         import="com.ateikon.util.Atk_ctrl"
         import="com.ateikon.util.StringParsToHTML"
         import="com.ateikon.util.StringUtils"

         import="com.ateikon.common.Cat_tipomapr_escl"    
         import="com.ateikon.structure.Rec_cat_tipomapr_escl" 
         import="com.ateikon.structure.Str_html"

%><%



    %><%@include file="include/variabili_comuni.jsp" %><%


try {


    %><%@include file="include/dbconnect.jsp" %><%
    %><%@include file="include/parametri_ep.jsp" %><%
    %><%@include file="include/sicurezza_ep.jsp" %><%



    Cat_tipomapr_escl cat_tipomapr_escl = new Cat_tipomapr_escl();


    // imposto la connessione

    atk_sql.setProfilo((Atk_sql) cat_tipomapr_escl);
    


    // Variabili di appoggio

    String    l_query  = "";



    // imposto le variabili di request

    if (request.getParameter("azione")!=null)   azione     = request.getParameter("azione");



    String[] arr_cdtipm = null;


    if (request.getParameterValues("cdtipm"  )!=null)  arr_cdtipm    = request.getParameterValues("cdtipm"  );


    if (azione.equals("salva")){

        cat_tipomapr_escl.delete_all();



        for (int i=0; arr_cdtipm!=null && i<arr_cdtipm.length; i++){

            Rec_cat_tipomapr_escl lstr = new Rec_cat_tipomapr_escl();

            lstr.cdtipm = arr_cdtipm[i];

            tot_rec = cat_tipomapr_escl.execute(lstr);
    
            if (tot_rec > 0){

                azione = "carica";
    
            }else {
                throw new Exception("Errore in Update");
            }


        }

        cat_tipomapr_escl.m_connection.commit();


    }




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


    function atk_autoriz(tkfunzione){

        document.form1.tkfunzione_aut.value = tkfunzione;
        atk_salva();
    }
    

    function atk_salva(){

        document.form1.azione.value = 'salva';
        document.form1.submit();
    }
    function atk_refresh(){

        document.form1.azione.value = 'carica';
        document.form1.submit();
    }


    function atk_check(el, ind){

        if (el.checked){
            document.getElementById('ck_app~'+ind).value = 'S';
        }else {
            document.getElementById('ck_app~'+ind).value = 'N';
        }
    }


<%@include file="js/atk_key.jsp" %>
    
    
    function atk_onSubmit(){
        return true;
    }
            
    function atk_close(){
        self.location = 'catAdmin_index.jsp';
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

    %><h2>Categorie escluse dal Catalolgo</h2><%

    %><form name="form1" method="post" action="<%= seStesso %>" onSubmit="return atk_onSubmit();" ><%

    %><br><%

            str_html = new Str_html();
        
            str_html.type  = "button";
            str_html.name  = "bt_salva";
            str_html.value = "Salva";
            str_html.css   = "bt bt_75";
            str_html.id    = "el~"+idx_f;  idx_f += 1;
            str_html.onClick  = "atk_salva();";

            out.println(html.getButton(str_html));

            str_html = new Str_html();
        
            str_html.type  = "button";
            str_html.name  = "bt_refr";
            str_html.value = "Ricarica";
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
    
    
    %><input type="hidden" name="azione" value="" /><%
    
    %><br/><%
    %><br/><%

    %><table class="report"><%
      %><tr><%
        %><th></th><%
        %><th>Cod.</th><%
        %><th>Descr.</th><%
      %></tr><%

        String ls_css = "css1";

        l_query  = "";
        l_query += " select *                                \n";
        l_query += "   from pgmr.tipomapr                    \n";
        l_query += "  where cdazie = '"+atk_sql.cdazie+"'    \n";
        l_query += "  order by dstipm                        \n";

        rs = atk_sql.sql_query(l_query);


        while(rs!=null && rs.next()){

            
            String cdtipm   = "";
            String cdtipm_m = "";
            String dstipm   = "";
            String ls_checked = "";

            if (rs.getObject("cdtipm")!=null)  cdtipm = rs.getString("cdtipm");
            if (rs.getObject("cdtipm_m")!=null)  cdtipm_m = rs.getString("cdtipm_m");
            if (rs.getObject("dstipm")!=null)  dstipm = rs.getString("dstipm");

            rs_box = cat_tipomapr_escl.getKey(cdtipm);

            if (rs_box!=null && rs_box.next()){
                ls_checked = "checked";
            }

            if (ls_css.equals("css1")){
                ls_css = "css2";
            }else {
                ls_css = "css1";
            }

            %><tr class="<%= ls_css %>"><%
                
                %><td><%
                    %><input type="checkbox" name="cdtipm" value="<%= cdtipm %>" <%= ls_checked %> /><%
                %></td><%
                %><td><%
                    out.println(html.text(cdtipm_m));
                %></td><%
                %><td><%
                    out.println(html.text(dstipm));
                %></td><%
            %></tr><%


        }

      
      %></table><%



    %></form><%
    %></div><%


%>
<%@include file="include/dbclose.jsp" %>
</body>
</html>




