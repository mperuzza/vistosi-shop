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
         import="com.ateikon.common.Cat_funz" 
         import="com.ateikon.common.Cat_funz_user" 

         import="com.ateikon.function.F_utente" 

         import="com.ateikon.structure.Rec_cat_utente"
         import="com.ateikon.structure.Rec_cat_funz_user"

%><%

    %><%@include file="include/variabili_comuni.jsp" %><%


try {


    %><%@include file="include/dbconnect.jsp" %><%
    %><%@include file="include/parametri_ep.jsp" %><%
    %><%@include file="include/sicurezza_ep.jsp" %><%




    Cat_utente       cat_utente     = new Cat_utente();
    Cat_funz         cat_funz       = new Cat_funz();
    Cat_funz_user    cat_funz_user  = new Cat_funz_user();

    F_utente   f_utente   = new F_utente();
    
    // imposto la connessione

    atk_sql.setProfilo((Atk_sql) cat_utente   );
    atk_sql.setProfilo((Atk_sql) cat_funz     );
    atk_sql.setProfilo((Atk_sql) cat_funz_user);
    atk_sql.setProfilo((Atk_sql) f_utente    );




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





    if (azione.equals("salva")){

        String[] arr_tkfunzione = null;
    
        if (request.getParameterValues("tkfunzione")!=null)  arr_tkfunzione    = request.getParameterValues("tkfunzione");


        tot_rec = cat_funz_user.deleteUser(r_tkutente);


        for (int i=0; arr_tkfunzione!=null && i<arr_tkfunzione.length; i++){

            long ll_tkfunzione = Long.parseLong(arr_tkfunzione[i]);

            Rec_cat_funz_user lstr_fu = new Rec_cat_funz_user();
            

            lstr_fu.tkfunzione = ll_tkfunzione;
            lstr_fu.tkutente   = r_tkutente;
            

            tot_rec = cat_funz_user.execute(lstr_fu);

            if (tot_rec != 1){
                throw new Exception("Errore INS cat_funz_user ");
            }
        }

        cat_funz_user.m_connection.commit();

        message += "Dati Aggiornati!";

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

    function atk_edit( tkutente){
        atk_wait();
        self.location = 'catAdmin_user_edit.jsp?tkutente='+tkutente;
    }

    function atk_tipm( tkutente){
        atk_wait();
        self.location = 'catAdmin_tipomapr_clie.jsp?tkutente='+tkutente;
    }


    function atk_refresh(){
        atk_wait();
        self.location = '<%= seStesso %>?tkutente=<%= r_tkutente %>';
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


    <h2 onDblClick="atk_sessione();">Gestione Utenti: Modifica Autorizzazioni <%= html.text(r_tpuser_descr) %></h2> 


<form name="form1" action="<%= seStesso %>" method="post" onSubmit="return atk_salva();">
        <input type="hidden" name="azione" value="report" />
        <input type="hidden" name="tkutente"        value="<%= r_tkutente %>" />
        
<%


        
                %><table class="parametri"><%
                    %><tr><%
                        %><th>User Name:</th><%
                        %><td><%
        
                            out.println(html.text(lstr.user_name));
                        %></td><%
                    %></tr><%
                    %><tr><%
                        %><th>Descrizione:</th><%
                        %><td><%
        
                            out.println(html.text(lstr.dsutente));

                        %></td><%
                    %></tr><%
                    %><tr><%
                        %><th>e-Mail:</th><%
                        %><td><%
        
                            out.println(html.text(lstr.email));

                        %></td><%
                    %></tr><%
                %></table><%
                
            %><br/><%
            %><br/><%
                                                    
            


            tot_rec = 0;


            rs = cat_funz.getFunzioni();

            if (rs!=null && rs.next()){

                %><table class="parametri"><%


                    do {

                        long   tkfunzione   = 0;
                        String cdfunzione_m = "";
                        String dsfunzione   = "";
                        String fgall_users   = "";
                        String ls_ck = "";


                        if (rs.getObject("tkfunzione")!=null)  tkfunzione = rs.getLong("tkfunzione");
                        if (rs.getObject("cdfunzione_m")!=null)  cdfunzione_m = rs.getString("cdfunzione_m");
                        if (rs.getObject("dsfunzione")!=null)  dsfunzione = rs.getString("dsfunzione");
                        if (rs.getObject("fgall_users")!=null)  fgall_users = rs.getString("fgall_users");

                        if (fgall_users.equals("S")){
                            continue;
                        }

                        rs_box = cat_funz_user.getKey(tkfunzione, r_tkutente);

                        if (rs_box!=null && rs_box.next()){
                            ls_ck = "checked";
                        }


                        %><tr class="css<%= ((++tot_rec%2)+1)%>"><%
                            %><td><%
                                
                                %><input type="checkbox" name="tkfunzione" value="<%= tkfunzione %>" <%= ls_ck %>/><%
                                
                            %></td><%
                            %><td width="150" nowrap><%

                                out.println(html.text(cdfunzione_m));
                                
                            %></td><%
                            %><td width="300" nowrap><%

                                out.println(html.text(dsfunzione));
                                
                            %></td><%
                        %></tr><%

                    }while(rs.next());


                    
                
                
                
                
                %></table><%
                
                %><br/><%
                %><br/><%

            }   // FINE cat_funz



            str_html = new Str_html();

            str_html.type  = "button";
            str_html.name  = "bt_exit";
            str_html.value = "&lt;&lt; Scheda";
            str_html.id    = "el~"+idx_f;  idx_f += 1;
            str_html.css   = "bt bt_75";
            str_html.onClick = "atk_edit('"+r_tkutente+"');";
            
            out.println(html.getButton(str_html));

            str_html = new Str_html();

            str_html.type  = "button";
            str_html.name  = "bt_ok";
            str_html.value = "Riacarica";
            str_html.id    = "el~"+idx_f;  idx_f += 1;
            str_html.css   = "bt bt_75";
            str_html.onClick = "atk_refresh();";


            out.println(html.getButton(str_html));

            if (tot_rec > 0){
                str_html = new Str_html();
    
                str_html.type  = "Submit";
                str_html.name  = "bt_exit";
                str_html.value = "Salva";
                str_html.id    = "el~"+idx_f;  idx_f += 1;
                str_html.css   = "bt bt_75";
                
                out.println(html.getButton(str_html));
            }

            if (!lstr.tkclie.equals("")){

                str_html = new Str_html();
    
                str_html.type  = "button";
                str_html.name  = "bt_fine";
                str_html.value = "Abil.Categorie &gt;&gt;";
                str_html.id    = "el~"+idx_f;  idx_f += 1;
                str_html.css   = "bt";
                str_html.style   = "width:125px;";
                str_html.onClick = "atk_tipm('"+r_tkutente+"');";
                
                out.println(html.getButton(str_html));
    
            }else {
                str_html = new Str_html();
    
                str_html.type  = "button";
                str_html.name  = "bt_fine";
                str_html.value = "Fine &gt;&gt;";
                str_html.id    = "el~"+idx_f;  idx_f += 1;
                str_html.css   = "bt bt_75";
                str_html.onClick = "atk_close();";
                
                out.println(html.getButton(str_html));
    
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


