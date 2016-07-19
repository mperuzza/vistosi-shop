<%@ page import="java.sql.ResultSet"  
         import="java.sql.SQLException"
         import="java.sql.Timestamp"
         import="java.util.Vector"
         import="com.ateikon.common.Atk_sql"
         import="com.ateikon.common.Costanti_comm"

         import="com.ateikon.util.Atk_ctrl"
         import="com.ateikon.util.StringParsToHTML"
         import="com.ateikon.util.StringUtils"

         import="com.ateikon.common.Cat_menu"    
         import="com.ateikon.structure.Rec_cat_menu" 
         import="com.ateikon.structure.Str_html"

%><%

    %><%@include file="include/variabili_comuni.jsp" %><%

try {


    %><%@include file="include/dbconnect.jsp" %><%
    %><%@include file="include/parametri_ep.jsp" %><%
    %><%@include file="include/sicurezza_ep.jsp" %><%



    Cat_menu cat_menu = new Cat_menu();


    // imposto la connessione

    atk_sql.setProfilo((Atk_sql) cat_menu);
    


    // Variabili di appoggio




    // imposto le variabili di request

    if (request.getParameter("azione")!=null)   azione     = request.getParameter("azione");


    Rec_cat_menu lstr = null;

    String[] arr_tkmenu   = null;
    String[] arr_cdmenu_m = null;
    String[] arr_dsmenu   = null;
    String[] arr_fgabil   = null;
    String[] arr_nrposi   = null;


    if (request.getParameterValues("tkmenu"  )!=null)  arr_tkmenu    = request.getParameterValues("tkmenu"  );
    if (request.getParameterValues("cdmenu_m")!=null)  arr_cdmenu_m  = request.getParameterValues("cdmenu_m");
    if (request.getParameterValues("dsmenu"  )!=null)  arr_dsmenu    = request.getParameterValues("dsmenu"  );
    if (request.getParameterValues("fgabil"  )!=null)  arr_fgabil    = request.getParameterValues("fgabil"  );
    if (request.getParameterValues("nrposi"  )!=null)  arr_nrposi    = request.getParameterValues("nrposi"  );


    if (azione.equals("salva")){


        for (int i=0; arr_tkmenu!=null && i<arr_tkmenu.length; i++){


            long ll_tkmenu = 0;
            long ll_nrposi = 0;

            try {   ll_tkmenu = Long.parseLong(arr_tkmenu[i]);      }catch(Exception ex) { ll_tkmenu = 0 ;  }
            try {   ll_nrposi = Long.parseLong(arr_nrposi[i]);      }catch(Exception ex) { ll_nrposi = 999 ;  }

            arr_cdmenu_m[i] = arr_cdmenu_m[i].trim().toUpperCase();
            arr_dsmenu[i]   = arr_dsmenu[i].trim();
            arr_fgabil[i]   = arr_fgabil[i].trim().toUpperCase();
            

            lstr = new Rec_cat_menu();

            lstr.tkmenu   = ll_tkmenu;
            lstr.cdmenu_m = arr_cdmenu_m[i];
            lstr.dsmenu   = arr_dsmenu[i];
            lstr.fgabil   = arr_fgabil[i];
            lstr.nrposi   = ll_nrposi;
            

            if (lstr.cdmenu_m.equals("")){

                continue;
            }

            tot_rec = cat_menu.execute(lstr);
    
            if (tot_rec > 0){
        
                azione = "carica";
    
            }else {
                throw new Exception("Errore in Update");
            }


        }

        cat_menu.riordina();
        cat_menu.m_connection.commit();


        long tkmenu_del = 0;

        if (request.getParameter("tkmenu_del")!=null)   tkmenu_del = Long.parseLong(request.getParameter("tkmenu_del"));
        

        if (tkmenu_del > 0){


            tot_rec = cat_menu.executeDelete(tkmenu_del);
    
            if (tot_rec > 0){
    
                cat_menu.m_connection.commit();
                 
                message += "Eliminato!";
    
                azione = "carica";
    
            }else {
                throw new Exception("Errore in Update");
            }
        }
    }



    long tkmenu_posi = 0;

    if (request.getParameter("tkmenu_posi")!=null)   tkmenu_posi = Long.parseLong(request.getParameter("tkmenu_posi"));

    if (tkmenu_posi > 0){

        response.sendRedirect("catAdmin_menu_posi.jsp?tkmenu="+tkmenu_posi);

        return;
    }


    if (azione.equals("carica") || arr_tkmenu == null){

        arr_tkmenu   = null;
        arr_cdmenu_m = null;
        arr_dsmenu   = null;
        arr_fgabil   = null;
        arr_nrposi   = null;
        
    
    
        tot_rec = cat_menu.count();

        if (tot_rec > 0){
        
            arr_tkmenu   = new String[tot_rec];
            arr_cdmenu_m = new String[tot_rec];
            arr_dsmenu   = new String[tot_rec];
            arr_fgabil   = new String[tot_rec];
            arr_nrposi   = new String[tot_rec];

            ind = 0;


            rs = cat_menu.getMenu();
        
            while(rs!=null && rs.next()){
                
                if (rs.getObject("tkmenu")  !=null ) arr_tkmenu[ind]   = ""+rs.getLong("tkmenu");
                if (rs.getObject("cdmenu_m")!=null ) arr_cdmenu_m[ind] = rs.getString("cdmenu_m");
                if (rs.getObject("dsmenu")  !=null ) arr_dsmenu[ind]   = rs.getString("dsmenu");
                if (rs.getObject("fgabil")  !=null ) arr_fgabil[ind]   = rs.getString("fgabil");
                if (rs.getObject("nrposi")  !=null ) arr_nrposi[ind]   = rs.getString("nrposi");
                
            
                ind += 1;
            }



        }

        cat_menu.close();
    }




%><html>
<head>
<%@include file="include/head_page.jsp" %>
<script language="JavaScript">

    function atk_onLoad(){

        <%@include file="js/atk_onLoad.jsp" %>

    }


    
    function atk_delete(tkmenu){

        var ls_msg = 'Eliminare la Riga?';

        if (!confirm(ls_msg)){
            return;
        }

        document.form1.tkmenu_del.value = tkmenu;

        atk_salva();
    }


    function atk_posi(tkmenu){

        document.form1.tkmenu_posi.value = tkmenu;
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


    function atk_paste(){
    }

    


</script>

</head>

<body onLoad="atk_onLoad();">
<%@include file="include/head.jsp" %>
<%@include file="include/divAttesa.jsp" %>
<%
    %><div id="body_div" ><%

    %><h2>Gestione Men&ugrave;</h2><%


    %><form name="form1" method="post" action="<%= seStesso %>" onSubmit="return atk_onSubmit();" ><%
    %><input type="hidden" name="azione" value="" /><%
    %><input type="hidden" name="tkmenu_del" value="0" /><%
    %><input type="hidden" name="tkmenu_posi" value="0" /><%
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
            str_html.name  = "bt_agg";
            str_html.value = "Ricarica";
            str_html.css   = "bt bt_75";
            str_html.id    = "el~"+idx_f;  idx_f += 1;
            str_html.onClick  = "atk_refresh();";

            out.println(html.getButton(str_html));

            
            str_html = new Str_html();
        
            str_html.type  = "button";
            str_html.name  = "bt_exit";
            str_html.value = "Exit";
            str_html.css   = "bt bt_75";
            str_html.id    = "el~"+idx_f;  idx_f += 1;
            str_html.onClick  = "atk_close();";

            out.println(html.getButton(str_html));

    

    
    %><br/><%
    %><br/><%

    %><table class="report"><%
      %><tr><%
        %><th>&nbsp;</th><%
        %><th>Pos.</th><%
        %><th>Cod.</th><%
        %><th>Descr.</th><%
        %><th>Abil</th><%
        %><th>&nbsp;</th><%
      %></tr><%
      
      
        for (int i=0; arr_tkmenu!=null && i<arr_tkmenu.length; i++){

            %><tr><%
                
                %><td><%


                    str_html = new Str_html();
                
                    str_html.type  = "button";
                    str_html.name  = "bt_del";
                    str_html.value = "";
                    str_html.id    = "el~"+idx_f;  idx_f += 1;

                    if (!arr_tkmenu[i].equals("0")){
                        str_html.css   = "bt bt_del";
                        str_html.onClick = "atk_delete("+arr_tkmenu[i]+")";
                    }else {
                        str_html.css   = "bt_pos";
                    }
            
                    out.println(html.getButton(str_html));

                %></td><%
                %><td><%

                    str_html = new Str_html();
                    
                    str_html.name  = "tkmenu";
                    str_html.value = arr_tkmenu[i];
        
                    out.println(html.getHidden(str_html));


                    str_html = new Str_html();
                    
                    str_html.name  = "nrposi";
                    str_html.id    = "el~"+idx_f;  idx_f += 1;
                    str_html.value = arr_nrposi[i];
                    str_html.size  = "5";
                    str_html.maxlength  = "5";
                    str_html.css   = "in in_num";
        
                    out.println(html.getText_box(str_html));

                %></td><%
                %><td><%

                    str_html = new Str_html();
                    
                    str_html.name  = "cdmenu_m";
                    str_html.id    = "el~"+idx_f;  idx_f += 1;
                    str_html.value = arr_cdmenu_m[i];
                    str_html.size  = "10";
                    str_html.maxlength  = "10";
                    str_html.css   = "in";
        
                    out.println(html.getText_box(str_html));

                %></td><%
                %><td><%

                    str_html = new Str_html();
                    
                    str_html.name  = "dsmenu";
                    str_html.id    = "el~"+idx_f;  idx_f += 1;
                    str_html.value = arr_dsmenu[i];
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
                    if (arr_fgabil[i].equals("S")){
                        str_html.checked= "checked";
                    }
        
                    out.println(html.getCheckbox(str_html));

                    str_html = new Str_html();
                    
                    str_html.name  = "fgabil";
                    str_html.id    = "ck_app~"+idx_f;
                    str_html.value = arr_fgabil[i];
        
                    out.println(html.getHidden(str_html));

                    idx_f += 1;

                %></td><%
                %><td><%


                        str_html = new Str_html();
                    
                        str_html.type  = "button";
                        str_html.name  = "bt_posi";
                        str_html.value = "&gt;&gt;";
                        str_html.id    = "el~"+idx_f;  idx_f += 1;
                        str_html.css   = "bt";
                        str_html.style = "width:25;";
                        str_html.onClick = "atk_posi("+arr_tkmenu[i]+")";
                
                        out.println(html.getButton(str_html));

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
                    
                    str_html.name  = "tkmenu";
                    str_html.value = "0";
        
                    out.println(html.getHidden(str_html));


                    str_html = new Str_html();
                    
                    str_html.name  = "nrposi";
                    str_html.id    = "el~"+idx_f;  idx_f += 1;
                    str_html.value = "999";
                    str_html.size  = "5";
                    str_html.maxlength  = "5";
                    str_html.css   = "in in_num";
        
                    out.println(html.getText_box(str_html));


                %></td><%
                %><td><%



                    str_html = new Str_html();
                    
                    str_html.name  = "cdmenu_m";
                    str_html.id    = "el~"+idx_f;  idx_f += 1;
                    str_html.value = "";
                    str_html.size  = "10";
                    str_html.maxlength  = "10";
                    str_html.css   = "in";
        
                    out.println(html.getText_box(str_html));

                %></td><%
                %><td><%

                    str_html = new Str_html();
                    
                    str_html.name  = "dsmenu";
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
                    str_html.checked= "checked";
        
                    out.println(html.getCheckbox(str_html));

                    str_html = new Str_html();
                    
                    str_html.name  = "fgabil";
                    str_html.id    = "ck_app~"+idx_f;
                    str_html.value = "S";
        
                    out.println(html.getHidden(str_html));

                    idx_f += 1;

                %></td><%

                %><td><%
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







