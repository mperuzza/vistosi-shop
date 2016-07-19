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
         import="com.ateikon.structure.Rec_cat_funz" 
         import="com.ateikon.structure.Str_html"

%><%



    %><%@include file="include/variabili_comuni.jsp" %><%


try {


    %><%@include file="include/dbconnect.jsp" %><%
    %><%@include file="include/parametri_ep.jsp" %><%
    %><%@include file="include/sicurezza_ep.jsp" %><%



    Cat_funz cat_funz = new Cat_funz();


    // imposto la connessione

    atk_sql.setProfilo((Atk_sql) cat_funz);
    


    // Variabili di appoggio




    // imposto le variabili di request

    if (request.getParameter("azione")!=null)   azione     = request.getParameter("azione");


    Rec_cat_funz lstr = null;

    String[] arr_tkfunzione   = null;
    String[] arr_cdfunzione_m = null;
    String[] arr_dsfunzione   = null;
    String[] arr_fgall_users  = null;


    if (request.getParameterValues("tkfunzione"  )!=null)  arr_tkfunzione    = request.getParameterValues("tkfunzione"  );
    if (request.getParameterValues("cdfunzione_m")!=null)  arr_cdfunzione_m  = request.getParameterValues("cdfunzione_m");
    if (request.getParameterValues("dsfunzione"  )!=null)  arr_dsfunzione    = request.getParameterValues("dsfunzione"  );
    if (request.getParameterValues("fgall_users" )!=null)  arr_fgall_users   = request.getParameterValues("fgall_users"  );



    if (azione.equals("salva")){


        for (int i=0; arr_tkfunzione!=null && i<arr_tkfunzione.length; i++){


            long ll_tkfunzione = 0;

            try {   ll_tkfunzione = Long.parseLong(arr_tkfunzione[i]);      }catch(Exception ex) { ll_tkfunzione = 0 ;  }

            arr_cdfunzione_m[i] = arr_cdfunzione_m[i].trim().toUpperCase();
            arr_dsfunzione[i]   = arr_dsfunzione[i].trim();

            lstr = new Rec_cat_funz();

            lstr.tkfunzione   = ll_tkfunzione;
            lstr.cdfunzione_m = arr_cdfunzione_m[i];
            lstr.dsfunzione   = arr_dsfunzione[i];
            lstr.fgall_users   = arr_fgall_users[i];

            if (lstr.cdfunzione_m.equals("")){

                continue;
            }

            tot_rec = cat_funz.execute(lstr);
    
            if (tot_rec > 0){
    
                cat_funz.m_connection.commit();
    
                azione = "carica";
    
            }else {
                throw new Exception("Errore in Update");
            }


        }

        long tkfunzione_del = 0;

        if (request.getParameter("tkfunzione_del")!=null)   tkfunzione_del = Long.parseLong(request.getParameter("tkfunzione_del"));
        

        if (tkfunzione_del > 0){


            tot_rec = cat_funz.executeDelete(tkfunzione_del);
    
            if (tot_rec > 0){
    
                cat_funz.m_connection.commit();
                 
                message += "Eliminato!";
    
                azione = "carica";
    
            }else {
                throw new Exception("Errore in Update");
            }
        }
    }



    long tkfunzione_aut = 0;

    if (request.getParameter("tkfunzione_aut")!=null)   tkfunzione_aut = Long.parseLong(request.getParameter("tkfunzione_aut"));

    if (tkfunzione_aut > 0){

        response.sendRedirect("catAdmin_funz_user.jsp?tkfunzione="+tkfunzione_aut);

        return;
    }


    if (azione.equals("carica") || arr_tkfunzione == null){

        arr_tkfunzione   = null;
        arr_cdfunzione_m = null;
        arr_dsfunzione   = null;
        arr_fgall_users   = null;
        
    
    
        tot_rec = cat_funz.count();

        if (tot_rec > 0){
        
            arr_tkfunzione   = new String[tot_rec];
            arr_cdfunzione_m = new String[tot_rec];
            arr_dsfunzione   = new String[tot_rec];
            arr_fgall_users   = new String[tot_rec];


            rs = cat_funz.getFunzioni();
        
            while(rs!=null && rs.next()){
                
                if (rs.getObject("tkfunzione")  !=null ) arr_tkfunzione[ind]   = ""+rs.getLong("tkfunzione");
                if (rs.getObject("cdfunzione_m")!=null ) arr_cdfunzione_m[ind] = rs.getString("cdfunzione_m");
                if (rs.getObject("dsfunzione")  !=null ) arr_dsfunzione[ind]   = rs.getString("dsfunzione");
                if (rs.getObject("fgall_users")  !=null ) arr_fgall_users[ind]   = rs.getString("fgall_users");
            
                ind += 1;
            }



        }

        cat_funz.close();
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

    %><h2>Gestione Funzioni</h2><%

    %><form name="form1" method="post" action="<%= seStesso %>" onSubmit="return atk_onSubmit();" ><%

    %><br><%
            str_html = new Str_html();
        
            str_html.type  = "Submit";
            str_html.name  = "bt_add";
            str_html.value = "Aggiungi";
            str_html.css   = "bt bt_75";
            str_html.id    = "el~"+idx_f;  idx_f += 1;

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
            str_html.name  = "bt_ex";
            str_html.value = "Exit";
            str_html.css   = "bt bt_75";
            str_html.id    = "el~"+idx_f;  idx_f += 1;
            str_html.onClick  = "atk_close();";

            out.println(html.getButton(str_html));
    
    
    %><input type="hidden" name="azione" value="" /><%
    %><input type="hidden" name="tkfunzione_del" value="0" /><%
    %><input type="hidden" name="tkfunzione_aut" value="0" /><%
    
    %><br/><%
    %><br/><%

    %><table><%
      %><tr><%
        %><td></td><%
        %><td>Cod. Funz.</td><%
        %><td>Descr.</td><%
        %><td>Pubblica</td><%
        %><td></td><%
      %></tr><%
      
      
        for (int i=0; arr_tkfunzione!=null && i<arr_tkfunzione.length; i++){

            %><tr><%
                
                %><td><%


                    str_html = new Str_html();
                
                    str_html.type  = "button";
                    str_html.name  = "bt_del";
                    str_html.value = "";
                    str_html.id    = "el~"+idx_f;  idx_f += 1;

                    if (!arr_tkfunzione[i].equals("0")){
                        str_html.css   = "bt bt_del";
                        str_html.onClick = "atk_delete("+arr_tkfunzione[i]+")";
                    }else {
                        str_html.css   = "bt_pos";
                    }
            
                    out.println(html.getButton(str_html));

                %></td><%
                %><td><%

                    str_html = new Str_html();
                    
                    str_html.name  = "tkfunzione";
                    str_html.value = arr_tkfunzione[i];
        
                    out.println(html.getHidden(str_html));



                    str_html = new Str_html();
                    
                    str_html.name  = "cdfunzione_m";
                    str_html.id    = "el~"+idx_f;  idx_f += 1;
                    str_html.value = arr_cdfunzione_m[i];
                    str_html.size  = "10";
                    str_html.maxlength  = "10";
                    str_html.css   = "in";
        
                    out.println(html.getText_box(str_html));

                %></td><%
                %><td><%

                    str_html = new Str_html();
                    
                    str_html.name  = "dsfunzione";
                    str_html.id    = "el~"+idx_f;  idx_f += 1;
                    str_html.value = arr_dsfunzione[i];
                    str_html.size  = "20";
                    str_html.maxlength  = "20";
                    str_html.css   = "in";
        
                    out.println(html.getText_box(str_html));

                %></td><%

                %><td align="center"><%

                    
                    str_html = new Str_html();
                    
                    str_html.name  = "ck_app";
                    str_html.id    = "el~"+idx_f; 
                    str_html.value = "S";
                    str_html.onClick = "atk_check(this, "+idx_f+")";
                    if (arr_fgall_users[i].equals("S")){
                        str_html.checked= "checked";
                    }
        
                    out.println(html.getCheckbox(str_html));

                    str_html = new Str_html();
                    
                    str_html.name  = "fgall_users";
                    str_html.id    = "ck_app~"+idx_f;
                    str_html.value = arr_fgall_users[i];
        
                    out.println(html.getHidden(str_html));

                    idx_f += 1;

                %></td><%
                %><td><%


                    if (!arr_fgall_users[i].equals("S")){
                        str_html = new Str_html();
                    
                        str_html.type  = "button";
                        str_html.name  = "bt_aut";
                        str_html.value = "&gt;&gt;";
                        str_html.id    = "el~"+idx_f;  idx_f += 1;
                        str_html.css   = "bt";
                        str_html.style = "width:25;";
                        
                        str_html.onClick = "atk_autoriz("+arr_tkfunzione[i]+")";
                
                        out.println(html.getButton(str_html));
                    }
                %></td><%



            %></tr><%


        }

        
        for (int i=0; i<1; i++){

            %><tr><%
                
                %><td><%


                    str_html = new Str_html();
                
                    str_html.type  = "button";
                    str_html.name  = "bt_del";
                    str_html.value = "";
                    str_html.id    = "el~"+idx_f;  idx_f += 1;

                    str_html.css   = "bt_pos";
            
                    out.println(html.getButton(str_html));


                %></td><%
                %><td><%

                    str_html = new Str_html();
                    
                    str_html.name  = "tkfunzione";
                    str_html.value = "0";
        
                    out.println(html.getHidden(str_html));

                    str_html = new Str_html();
                    
                    str_html.name  = "cdfunzione_m";
                    str_html.id    = "el~"+idx_f;  idx_f += 1;
                    str_html.value = "";
                    str_html.size  = "10";
                    str_html.maxlength  = "10";
                    str_html.css   = "in";
        
                    out.println(html.getText_box(str_html));

                %></td><%
                %><td><%

                    str_html = new Str_html();
                    
                    str_html.name  = "dsfunzione";
                    str_html.id    = "el~"+idx_f;  idx_f += 1;
                    str_html.value = "";
                    str_html.size  = "20";
                    str_html.maxlength  = "20";
                    str_html.css   = "in";
        
                    out.println(html.getText_box(str_html));

                %></td><%

                %><td align="center"><%
                     
                    str_html = new Str_html();
                    
                    str_html.name  = "ck_app";
                    str_html.id    = "el~"+idx_f;  
                    str_html.value = "S";
                    str_html.onClick = "atk_check(this, "+idx_f+")";
        
                    out.println(html.getCheckbox(str_html));

                    str_html = new Str_html();
                    
                    str_html.name  = "fgall_users";
                    str_html.id    = "ck_app~"+idx_f;
                    str_html.value = "N";
        
                    out.println(html.getHidden(str_html));

                    idx_f += 1;

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




