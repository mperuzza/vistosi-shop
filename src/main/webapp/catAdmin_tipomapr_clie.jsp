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

         import="com.ateikon.structure.Str_html"


         import="com.ateikon.common.Cat_utente" 
         import="com.ateikon.common.Cat_funz" 
         import="com.ateikon.common.Cat_funz_user" 
         import="com.ateikon.common.Cat_tipomapr_clie"


         import="com.ateikon.structure.Rec_cat_tipomapr_clie"
         import="com.ateikon.structure.Rec_cat_utente"
         import="com.ateikon.structure.Rec_cat_funz_user"








%><%



    %><%@include file="include/variabili_comuni.jsp" %><%


try {


    %><%@include file="include/dbconnect.jsp" %><%
    %><%@include file="include/parametri_ep.jsp" %><%
    %><%@include file="include/sicurezza_ep.jsp" %><%



    Cat_utente       cat_utente     = new Cat_utente();
    Cat_tipomapr_clie cat_tipomapr_clie = new Cat_tipomapr_clie();


    // imposto la connessione

    atk_sql.setProfilo((Atk_sql) cat_utente   );
    atk_sql.setProfilo((Atk_sql) cat_tipomapr_clie);
    


    // Variabili di appoggio

    String    l_query  = "";


    long r_tkutente = 0;

    // imposto le variabili di request

    if (request.getParameter("azione")!=null)   azione     = request.getParameter("azione");
    if (request.getParameter("tkutente")!=null)  r_tkutente    = Long.parseLong(request.getParameter("tkutente"));


    Rec_cat_utente lstr_usr = new Rec_cat_utente();

    rs = cat_utente.getKey(r_tkutente);

    if (rs!=null && rs.next()){

        lstr_usr.setResultSet(rs);
    }

    if (lstr_usr.tkclie.equals("")) {
        throw new Exception("Utente NON previsto");
    }




    String[] arr_cdtipm = null;


    if (request.getParameterValues("cdtipm"  )!=null)  arr_cdtipm    = request.getParameterValues("cdtipm"  );
       
    
    if (azione.equals("salva")){

        cat_tipomapr_clie.delete_all(lstr_usr.tkclie);



        for (int i=0; arr_cdtipm!=null && i<arr_cdtipm.length; i++){

            Rec_cat_tipomapr_clie lstr = new Rec_cat_tipomapr_clie();


            lstr.tkclie = lstr_usr.tkclie;
            lstr.cdazie = atk_sql.cdazie;
            lstr.cdtipm = arr_cdtipm[i];

            tot_rec = cat_tipomapr_clie.execute(lstr);
    
            if (tot_rec > 0){

                azione = "carica";
    
            }else {
                throw new Exception("Errore in Update");
            }


        }

        cat_tipomapr_clie.m_connection.commit();


    }




%><html>
<head>
<%@include file="include/head_page.jsp" %>
<script language="JavaScript">

    function atk_onLoad(){

        <%@include file="js/atk_onLoad.jsp" %>
    }


    function atk_salva(){
        atk_wait();

        document.form1.azione.value = 'salva';
        document.form1.submit();
    }
    function atk_refresh(){
        atk_wait();

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

    function atk_perm( tkutente){
        atk_wait();
        self.location = 'catAdmin_user_perm.jsp?tkutente='+tkutente;
    }

    function atk_all( el_ck){

        el_ = document.form1.cdtipm;
        for(var i=0; i<el_.length; i++){
            
            el_[i].checked = el_ck.checked;
        }

    }

<%@include file="js/atk_key.jsp" %>
    
    
    function atk_onSubmit(){
        return true;
    }
            
    function atk_close(){
        self.location = 'catAdmin_user.jsp'; 
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

    %><h2>Abilitazione Categorie per L'utente</h2><%

    %><form name="form1" method="post" action="<%= seStesso %>" onSubmit="return atk_onSubmit();" ><%
        %><input type="hidden" name="azione" value="" /><%
        %><input type="hidden" name="tkutente" value="<%= r_tkutente %>" /><%
        
                %><table class="parametri"><%
                    %><tr><%
                        %><th>User Name:</th><%
                        %><td><%
        
                            out.println(html.text(lstr_usr.user_name));
                        %></td><%
                    %></tr><%
                    %><tr><%
                        %><th>Descrizione:</th><%
                        %><td><%
        
                            out.println(html.text(lstr_usr.dsutente));

                        %></td><%
                    %></tr><%
                    %><tr><%
                        %><th>e-Mail:</th><%
                        %><td><%
        
                            out.println(html.text(lstr_usr.email));

                        %></td><%
                    %></tr><%
                %></table><%
                
            %><br/><%
            %><br/><%

 
            str_html = new Str_html();

            str_html.type  = "button";
            str_html.name  = "bt_exit";
            str_html.value = "&lt;&lt; Permessi";
            str_html.id    = "el~"+idx_f;  idx_f += 1;
            str_html.css   = "bt bt_75";
            str_html.onClick = "atk_perm('"+r_tkutente+"');";
            
            out.println(html.getButton(str_html));


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
            str_html.name  = "bt_fine";
            str_html.value = "Fine &gt;&gt;";
            str_html.id    = "el~"+idx_f;  idx_f += 1;
            str_html.css   = "bt bt_75";
            str_html.onClick = "atk_close();";
            
            out.println(html.getButton(str_html));
    
    
    
    %><br/><%
    %><br/><%

    %><table class="report"><%
      %><tr><%
        %><th><%
                    %><input type="checkbox" name="ck_all" value="S" onClick="atk_all(this);"/><%
        %></th><%
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

            rs_box = cat_tipomapr_clie.getKey(lstr_usr.tkclie, atk_sql.cdazie, cdtipm);

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




