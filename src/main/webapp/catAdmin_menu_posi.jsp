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
         import="com.ateikon.common.Cat_menu_posi"    
         import="com.ateikon.common.Cat_funz"    
         
         import="com.ateikon.structure.Rec_cat_menu_posi" 
         import="com.ateikon.structure.Str_html"

%><%



    %><%@include file="include/variabili_comuni.jsp" %><%


try {


    %><%@include file="include/dbconnect.jsp" %><%
    %><%@include file="include/parametri_ep.jsp" %><%
    %><%@include file="include/sicurezza_ep.jsp" %><%



    Cat_menu cat_menu = new Cat_menu();
    Cat_menu_posi cat_menu_posi = new Cat_menu_posi ();
    Cat_funz cat_funz = new Cat_funz();

    // imposto la connessione

    atk_sql.setProfilo((Atk_sql) cat_menu);
    atk_sql.setProfilo((Atk_sql) cat_menu_posi);
    atk_sql.setProfilo((Atk_sql) cat_funz);
    
    
    


    // Variabili di appoggio




    // imposto le variabili di request

    if (request.getParameter("azione")!=null)   azione     = request.getParameter("azione");


    Rec_cat_menu_posi lstr = null;

    long tkmenu = 0;

    try{

       if (request.getParameter("tkmenu")!=null) tkmenu = Long.parseLong(request.getParameter("tkmenu"));
        
    }catch(Exception ex_tk){
    }


    String cdmenu_m = "";
    String dsmenu   = "";


    rs = cat_menu.getKey(tkmenu);

    if (rs!=null && rs.next()){
        
        if (rs.getObject("cdmenu_m")!=null ) cdmenu_m = rs.getString("cdmenu_m");
        if (rs.getObject("dsmenu")  !=null ) dsmenu   = rs.getString("dsmenu");
    
    }

    


    String[]  arr_tkmenu_posi  = null;
    String[]  arr_nrposi       = null;
    String[]  arr_dsmenu_posi  = null;
    String[]  arr_link_menu    = null;
    String[]  arr_tkfunzione   = null;
    

    if (request.getParameterValues("tkmenu_posi" )!=null)  arr_tkmenu_posi  = request.getParameterValues("tkmenu_posi"  );
    if (request.getParameterValues("nrposi"      )!=null)  arr_nrposi       = request.getParameterValues("nrposi");
    if (request.getParameterValues("dsmenu_posi" )!=null)  arr_dsmenu_posi  = request.getParameterValues("dsmenu_posi"  );
    if (request.getParameterValues("link_menu"   )!=null)  arr_link_menu    = request.getParameterValues("link_menu"  );
    if (request.getParameterValues("tkfunzione"  )!=null)  arr_tkfunzione   = request.getParameterValues("tkfunzione"  );
    




    if (azione.equals("salva")){


        for (int i=0; arr_tkmenu_posi!=null && i<arr_tkmenu_posi.length; i++){


            long ll_tkmenu_posi = 0;
            long ll_tkfunzione  = 0;
            long ll_nrposi      = 0;


            arr_nrposi[i]      = arr_nrposi[i].trim();
            arr_dsmenu_posi[i] = arr_dsmenu_posi[i].trim();
            arr_link_menu[i]   = arr_link_menu[i].trim();
            arr_tkfunzione[i]  = arr_tkfunzione[i].trim();
            
            try {   ll_tkmenu_posi = Long.parseLong(arr_tkmenu_posi[i]); }catch(Exception ex) { ll_tkmenu_posi = 0 ;  }
            try {   ll_tkfunzione  = Long.parseLong(arr_tkfunzione[i]);  }catch(Exception ex) { ll_tkfunzione = 0 ;  }
            try {   ll_nrposi      = Long.parseLong(arr_nrposi[i]);      }catch(Exception ex) { ll_nrposi = 99 ;  }

            

            lstr = new Rec_cat_menu_posi();

            lstr.tkmenu       = tkmenu;
            lstr.tkmenu_posi  = ll_tkmenu_posi;
            lstr.nrposi       = ll_nrposi;
            lstr.dsmenu_posi  = arr_dsmenu_posi[i];
            lstr.link_menu    = arr_link_menu[i];
            lstr.tkfunzione   = ll_tkfunzione;
            lstr.profil_inse  = s_user_name;
            
            

            if (lstr.dsmenu_posi.equals("") && lstr.link_menu.equals("")){

                continue;
            }

            tot_rec = cat_menu_posi.execute(lstr);
    
            if (tot_rec > 0){
    
                cat_menu_posi.m_connection.commit();
    
                azione = "carica";
    
            }else {
                throw new Exception("Errore in Update");
            }


        }

        tot_rec = cat_menu_posi.riordina(tkmenu);
        cat_menu_posi.m_connection.commit();
        

        long tkmenu_posi_del = 0;

        if (request.getParameter("tkmenu_posi_del")!=null)   tkmenu_posi_del = Long.parseLong(request.getParameter("tkmenu_posi_del"));
        

        if (tkmenu_posi_del > 0){


            tot_rec = cat_menu_posi.executeDelete(tkmenu_posi_del);
    
            if (tot_rec > 0){
    
                cat_menu_posi.m_connection.commit();
                 
                message += "Eliminato!";
    
                azione = "carica";
    
            }else {
                throw new Exception("Errore in Update");
            }
        }
    }




    if (azione.equals("carica") || arr_tkmenu_posi == null){

        arr_tkmenu_posi  = null;
        arr_nrposi       = null;
        arr_dsmenu_posi  = null;
        arr_link_menu    = null;
        arr_tkfunzione   = null;
        
        
        
    
    
        tot_rec = cat_menu_posi.count(tkmenu);

        if (tot_rec > 0){
        
            arr_tkmenu_posi  = new String[tot_rec];
            arr_nrposi       = new String[tot_rec];
            arr_dsmenu_posi  = new String[tot_rec];
            arr_link_menu    = new String[tot_rec];
            arr_tkfunzione   = new String[tot_rec];
            
            

            ind = 0;


            rs = cat_menu_posi.getMenu(tkmenu);
        
            while(rs!=null && rs.next()){
                
                if (rs.getObject("tkmenu_posi")!=null ) arr_tkmenu_posi[ind] = ""+rs.getLong("tkmenu_posi");
                if (rs.getObject("nrposi"     )!=null ) arr_nrposi[ind]      = ""+rs.getLong("nrposi");
                if (rs.getObject("dsmenu_posi")!=null ) arr_dsmenu_posi[ind] = rs.getString("dsmenu_posi");
                if (rs.getObject("link_menu"  )!=null ) arr_link_menu[ind]   = rs.getString("link_menu");
                if (rs.getObject("tkfunzione" )!=null ) arr_tkfunzione[ind]  = ""+rs.getLong("tkfunzione");
                
                ind += 1;
            }



        }

        cat_menu_posi.close();
    }




%><html>
<head>
<%@include file="include/head_page.jsp" %>
<script language="JavaScript">

    function atk_onLoad(){

        <%@include file="js/atk_onLoad.jsp" %>

    }


    
    function atk_delete(tkmenu_posi){

        var ls_msg = 'Eliminare la Riga?';

        if (!confirm(ls_msg)){
            return;
        }

        document.form1.tkmenu_posi_del.value = tkmenu_posi;

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




<%@include file="js/atk_key.jsp" %>
    
    
    function atk_onSubmit(){
        return true;
    }
            
    function atk_close(){
        self.location = 'catAdmin_menu.jsp';
    }

    function atk_f1( ){ }

    function atk_f2( ){ }

    function atk_f3( ){ }


    function atk_paste(){
        // document.form1.submit();
    }

    


</script>

</head>

<body onLoad="atk_onLoad();">
<%@include file="include/head.jsp" %>
<%@include file="include/divAttesa.jsp" %>
<%
    %><div id="body_div" ><%

    %><h2>Men&ugrave;: &nbsp;&nbsp;&nbsp;<%= dsmenu %></h2><%

    %><form name="form1" method="post" action="<%= seStesso %>" onSubmit="return atk_onSubmit();" ><%

    %><br/><%
    
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
            str_html.name  = "bt_menu";
            str_html.value = "Exit";
            str_html.css   = "bt bt_75";
            str_html.id    = "el~"+idx_f;  idx_f += 1;
            str_html.onClick  = "atk_close();";

            out.println(html.getButton(str_html));
    
    

    %><input type="hidden" name="azione" value="" /><%
    %><input type="hidden" name="tkmenu_posi_del" value="0" /><%
    %><input type="hidden" name="tkmenu" value="<%= tkmenu %>" /><%
    
    %><br/><%
    %><br/><%

    %><table class="report"><%
      %><tr><%
        %><th></th><%
        %><th>Pos.</th><%
        %><th>Descr.</th><%
        %><th>Link</th><%
        %><th>Funzione</th><%
      %></tr><%
     
      
        for (int i=0; arr_tkmenu_posi!=null && i<arr_tkmenu_posi.length; i++){

            %><tr><%
                
                %><td><%


                    str_html = new Str_html();
                
                    str_html.type  = "button";
                    str_html.name  = "bt_del";
                    str_html.value = "";
                    str_html.id    = "el~"+idx_f;  idx_f += 1;

                    if (!arr_tkmenu_posi[i].equals("0")){
                        str_html.css   = "bt bt_del";
                        str_html.onClick = "atk_delete("+arr_tkmenu_posi[i]+")";
                    }else {
                        str_html.css   = "bt_pos";
                    }
            
                    out.println(html.getButton(str_html));

                %></td><%
                %><td><%

                    str_html = new Str_html();
                    
                    str_html.name  = "tkmenu_posi";
                    str_html.value = arr_tkmenu_posi[i];
        
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
                    
                    str_html.name  = "dsmenu_posi";
                    str_html.id    = "el~"+idx_f;  idx_f += 1;
                    str_html.value = arr_dsmenu_posi[i];
                    str_html.size  = "30";
                    str_html.maxlength  = "100";
                    str_html.css   = "in";
        
                    out.println(html.getText_box(str_html));

                %></td><%

                %><td><%

                    str_html = new Str_html();
                    
                    str_html.name  = "link_menu";
                    str_html.id    = "el~"+idx_f;  idx_f += 1;
                    str_html.value = arr_link_menu[i];
                    str_html.size  = "30";
                    str_html.maxlength  = "100";
                    str_html.css   = "in";
        
                    out.println(html.getText_box(str_html));

                %></td><%
                %><td><%

                    rs = cat_funz.getDropdown();
                
                    out.println(html.getDropdown(rs, "tkfunzione", arr_tkfunzione[i], "class=\"sl\""));

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
                    
                    str_html.name  = "tkmenu_posi";
                    str_html.value = "0";
        
                    out.println(html.getHidden(str_html));

                    str_html = new Str_html();
                    
                    str_html.name  = "nrposi";
                    str_html.id    = "el~"+idx_f;  idx_f += 1;
                    str_html.value = "";
                    str_html.size  = "5";
                    str_html.maxlength  = "5";
                    str_html.css   = "in in_num";
        
                    out.println(html.getText_box(str_html));

                %></td><%
                %><td><%

                    str_html = new Str_html();
                    
                    str_html.name  = "dsmenu_posi";
                    str_html.id    = "el~"+idx_f;  idx_f += 1;
                    str_html.value = "";
                    str_html.size  = "30";
                    str_html.maxlength  = "100";
                    str_html.css   = "in";
        
                    out.println(html.getText_box(str_html));

                %></td><%
                %><td><%

                    str_html = new Str_html();
                    
                    str_html.name  = "link_menu";
                    str_html.id    = "el~"+idx_f;  idx_f += 1;
                    str_html.value = "";
                    str_html.size  = "30";
                    str_html.maxlength  = "100";
                    str_html.css   = "in";
        
                    out.println(html.getText_box(str_html));

                %></td><%
                %><td><%
                    rs = cat_funz.getDropdown();
                
                    out.println(html.getDropdown(rs, "tkfunzione", "0", "class=\"sl\""));
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





