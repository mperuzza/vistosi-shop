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


    // imposto le variabili di request

    if (request.getParameter("azione")!=null)   azione     = request.getParameter("azione");

    if (azione.equals("")) azione = "archclie";




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

    %><h2>Riepilogo Abilitazione Categorie</h2><%

    %><form name="form1" method="post" action="<%= seStesso %>" onSubmit="return atk_onSubmit();" ><%
        
                %><table class="parametri"><%
                    %><tr><%
                        %><th>Tipo Report:</th><%
                        %><td><%
                                
                        
                            %><input type="radio" name="azione" value="archclie" <%= ((!azione.equals("tipomapr"))?"checked":"")%>/> Ordinato Per Cliente <br/><%
                            %><input type="radio" name="azione" value="tipomapr" <%= ((azione.equals("tipomapr"))?"checked":"")%>/> Ordinato Per Categoria<br/><%

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
        
            str_html.type  = "Submit";
            str_html.name  = "bt_refr";
            str_html.value = "Carica Report";
            str_html.css   = "bt";
            str_html.style = "width: 200px;";
            str_html.id    = "el~"+idx_f;  idx_f += 1;

            out.println(html.getButton(str_html));


    
    %><br/><%
    %><br/><%


    if (!azione.equals("")){


        if (azione.equals("archclie")){
            order_by = "ente.ragcog, tipm.cdtipm_m ";
        }else {
            order_by = "tipm.cdtipm_m, ente.ragcog ";
        }



        rs = cat_tipomapr_clie.getRiepilogo(order_by);
   


       if (rs!=null && rs.next()){

            String ls_cd_prec = "";
            String ls_css = "";
            int tot_cols = 0;

            %><table class="report"><%
              %><tr><%
                %><th>&nbsp</th><%       tot_cols ++;
                %><th>Cod.</th><%       tot_cols ++;
                %><th width="300">Descr.</th><%     tot_cols ++;
                %><th width="125">Data Inserimento</th><%   tot_cols ++;
              %></tr><%
        

            do {

                String     tkclie    = "";
                String     cdclie_m  = "";
                String     ragcog    = "";
                String     cdtipm    = "";
                String     cdtipm_m  = "";
                String     dstipm    = "";
                Timestamp  dtinse    = null;

                if (rs.getObject("tkclie"   )!=null)  tkclie    = rs.getString("tkclie"   );
                if (rs.getObject("cdclie_m" )!=null)  cdclie_m  = rs.getString("cdclie_m" );
                if (rs.getObject("ragcog"   )!=null)  ragcog    = rs.getString("ragcog"   );
                if (rs.getObject("cdtipm"   )!=null)  cdtipm    = rs.getString("cdtipm"   );
                if (rs.getObject("cdtipm_m" )!=null)  cdtipm_m  = rs.getString("cdtipm_m" );
                if (rs.getObject("dstipm"   )!=null)  dstipm    = rs.getString("dstipm"   );
                if (rs.getObject("dtinse"   )!=null)  dtinse    = rs.getTimestamp("dtinse"   );

                if (azione.equals("archclie")){
 
                        if (!ls_cd_prec.equals(tkclie)){
                            %><tr height="32"><%
                                %><td nowrap colspan="<%= tot_cols %>" style="background-color:#FFF; border: 1px solid #666;"><%
    
                                    %><b> &gt;&gt; [<%= html.text(cdclie_m)  %>]</b> &nbsp;&nbsp; <%= html.text(ragcog) %><%
                                %></td><%
                            %></tr><%
                            ls_css = "css1";
                            
                        }
    
                        /*
                        if (ls_css.equals("css1")){
                            ls_css = "css2";
                        }else {
                            ls_css = "css1";
                        }
                        */
            
                        %><tr class="<%= ls_css %>"><%
                            %><td>&nbsp;</td><%
                            %><td nowrap><%
                                out.println(html.text(cdtipm_m));
                            %></td><%
                            %><td nowrap><%
                                out.println(html.text(dstipm));
                            %></td><%
                            %><td align="center" nowrap><%
                                out.println(atk_ctrl.getDatetime(dtinse));
                            %></td><%
                        %></tr><%
                       
                        ls_cd_prec = tkclie;


                }else { // if (azione.equals("archclie")){


                        if (!ls_cd_prec.equals(cdtipm)){
                            %><tr height="32"><%
                                %><td nowrap colspan="<%= tot_cols %>" style="color:#f00; background-color:#FFF; border: 1px solid #666;"><%
    
                                    %><b> &gt;&gt; [<%= html.text(cdtipm_m)  %>]</b> &nbsp;&nbsp; <%= html.text(dstipm) %><%
                                %></td><%
                            %></tr><%
                            ls_css = "css1";
                            
                        }
    
                        /*
                        if (ls_css.equals("css1")){
                            ls_css = "css2";
                        }else {
                            ls_css = "css1";
                        }
                        */
            
                        %><tr class="<%= ls_css %>"><%
                            %><td>&nbsp;</td><%
                            %><td nowrap><%
                                out.println(html.text(cdclie_m));
                            %></td><%
                            %><td nowrap><%
                                out.println(html.text(ragcog));
                            %></td><%
                            %><td align="center" nowrap><%
                                out.println(atk_ctrl.getDatetime(dtinse));
                            %></td><%
                        %></tr><%
                       
                        ls_cd_prec = cdtipm;



                }   // FINE if (azione.equals("archclie")){
                 
            }while (rs.next());

            %></table><%

       }else {
            %><br/><%
            %><br/><%
            %><br/>Non ci sono dati.<%

       }    // fine if (rs!=null && rs.next()){


    }


    %></form><%
    %></div><%


%>
<%@include file="include/dbclose.jsp" %>
</body>
</html>




