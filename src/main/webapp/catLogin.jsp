<%@ page import="java.sql.ResultSet"  
         import="java.sql.SQLException"
         import="java.sql.Timestamp"
         import="java.util.Vector"
         import="com.ateikon.common.Atk_sql"

         import="com.ateikon.util.Atk_ctrl"
         import="com.ateikon.util.StringParsToHTML"
         import="com.ateikon.util.StringUtils"

         import="com.ateikon.structure.Str_html"


         import="com.ateikon.common.Archagen"
         import="com.ateikon.function.F_login"

%><%


    %><%@include file="include/variabili_comuni.jsp" %><%

try {


    %><%@include file="include/dbconnect.jsp" %><%
    %><%@include file="include/parametri_ep.jsp" %><%
    %><%@include file="include/sic_pubblica.jsp" %><%



    
    Archagen archagen = new Archagen();
    F_login  f_login  = new F_login();

    atk_sql.setProfilo(archagen );
    atk_sql.setProfilo(f_login  );

    
    if (request.getParameter("message")!=null) message = request.getParameter("message");



    String user_name = "";
    String r_cdazie  = "";
    String r_cddipa  = "";
    String pwd       = "";

    boolean lb_login = false;

    String nextPage       = "catalogo_index.jsp";
    String ls_url         = "";
    String ls_queryString = "";


    if (request.getParameter("azione")!=null ) azione = request.getParameter("azione");
    if (request.getParameter("user_name")!=null ) user_name = request.getParameter("user_name");
    if (request.getParameter("pwd")!=null ) pwd = request.getParameter("pwd");


    if (request.getParameter("cdazie")!=null ) r_cdazie = request.getParameter("cdazie");
    if (request.getParameter("cddipa")!=null ) r_cddipa = request.getParameter("cddipa");


    if (azione.equals("logout")){

        session.invalidate();

        response.sendRedirect(seStesso);

        return;
    }

    if (azione.equals("re-login")){
        session.removeAttribute("user_name");
    }







    user_name = user_name.trim();
    pwd       = pwd.trim();

    if ( ( !user_name.equals("") && !pwd.equals("") )
    ||    session.getAttribute("user_name") != null
    ){

        
        // autentico l'utente testando la connessione 
        // se ho l'utente in sessione significa che lo ho autenticato
        // in precedenza


        if (session.getAttribute("user_name") != null){

            user_name = (String) session.getAttribute("user_name") ;

            lb_login = true;

        }else {

            lb_login = f_login.isLogin(user_name, pwd);

        }   // FINE if (session.getAttribute("user_name") != null){


        if (lb_login){
            session.setAttribute("user_name", user_name);
        }else {
            message += "Utente/Password Errate!\n";
        }


        // --- Verifico se per quest'utente esiste una azienda / diartimento

        if (lb_login){

            rs = f_login.login( user_name, r_cdazie, r_cddipa );
            
            if ( rs!=null && rs.next() ){

                String cdazie = "";
                String cddipa = "";

                if (rs.getObject("user_name" )!=null) user_name = rs.getString("user_name");
                if (rs.getObject("cdazie"    )!=null) cdazie    = rs.getString("cdazie");
                if (rs.getObject("cddipa"    )!=null) cddipa    = rs.getString("cddipa");

                if (rs.next()){
                    // non ho identificato in modo univoco 
                }else {
                    r_cdazie = cdazie;
                    r_cddipa = cddipa;
                }
            }
            f_login.close();
        }

        // --- se la login OK / azienda e dipartimento sono val

        if (lb_login 
         && !user_name.equals("") 
          && !r_cdazie.equals("") 
           && !r_cddipa.equals("")){

                    
                session.setAttribute("user_name", user_name);
                session.setAttribute("cdazie", r_cdazie);
                session.setAttribute("cddipa", r_cddipa);


                // --- Compilo la sessione

                rs = f_login.login( user_name, r_cdazie, r_cddipa );

                if ( rs!=null && rs.next() ){
    
                        long   tkutente     = 0;
                        String dsutente     = "";
                        String tkclie       = "";
                        String tkforn       = "";
                        String cdagen       = "";
                        String cdcapo       = "";
                        String cdente       = "";
                        String cdutente     = "";
                        String fgadmin      = "N";
                        String cdazie       = "";
                        String cddipa       = "";
                        
                        if (rs.getObject("tkutente"  )!=null) tkutente  = rs.getLong("tkutente");
                        if (rs.getObject("user_name" )!=null) user_name = rs.getString("user_name");
                        if (rs.getObject("cdazie"    )!=null) cdazie    = rs.getString("cdazie");
                        if (rs.getObject("cddipa"    )!=null) cddipa    = rs.getString("cddipa");
                        if (rs.getObject("dsutente"  )!=null) dsutente  = rs.getString("dsutente");
                        
                        if (rs.getObject("tkclie"    )!=null) tkclie    = rs.getString("tkclie"  );
                        if (rs.getObject("tkforn"    )!=null) tkforn    = rs.getString("tkforn"  );
                        if (rs.getObject("cdagen"    )!=null) cdagen    = rs.getString("cdagen"  );
                        if (rs.getObject("cdente"    )!=null) cdente    = rs.getString("cdente"  );
                        if (rs.getObject("cdutente"  )!=null) cdutente  = rs.getString("cdutente");
                        if (rs.getObject("fgadmin"   )!=null) fgadmin   = rs.getString("fgadmin" );

                        if (!cdagen.equals("")){

                            // --- verifico se questo agente è un capoarea
                            //     in tal caso inverto inizializzo la sessione come capoarea
                            
                            boolean lb_capoarea =  archagen.isCapoArea ( cdagen );

                            if(lb_capoarea){
                                cdcapo = cdagen;
                                cdagen = "";
                            }
                        }

                        dsutente = f_login.getDsutente(tkutente);
        
                        session.setAttribute("dsutente", dsutente);
                        
                        if (!tkclie.equals("")    )     session.setAttribute("tkclie"  , tkclie   );
                        if (!tkforn.equals("")    )     session.setAttribute("tkforn"  , tkforn   );
                        if (!cdagen.equals("")    )     session.setAttribute("cdagen"  , cdagen   );
                        if (!cdcapo.equals("")    )     session.setAttribute("cdcapo"  , cdcapo   );
                        if (!cdente.equals("")    )     session.setAttribute("cdente"  , cdente   );
                        if (!cdutente.equals("")  )     session.setAttribute("cdutente", cdutente );
                        if (!fgadmin.equals("")   )     session.setAttribute("fgadmin" , fgadmin  );
                }


                if (request.getParameter("nextPage")!=null) nextPage = request.getParameter("nextPage");

                ls_url  = nextPage;

                ls_queryString = atk_ctrl.getRequestQuery(request, new String[]{"nextPage"});

                if (!ls_queryString.equals("")){
                    
                    ls_url  += "?"+ls_queryString;
                }

                
                // System.out.println();
                // System.out.println();
                // System.out.println("******* Login "+user_name+" "+atk_sql.oggi);
                // System.out.println("*******       cdazie "+r_cdazie);
                // System.out.println("*******       cddipa "+r_cddipa);
                // System.out.println();
                // System.out.println();

                response.sendRedirect(ls_url);
        
                return;
        }   // 

    }



%><html>
<head>
<%@include file="include/head_page.jsp" %>
<style>
.alertlogin{
	color:#FF0000;
	font-size:9px;
	padding:20px 0px 20px 0px;
}
.lcss_cdazie{
    width: 200px;
}
.lcss_cddipa{
    width: 200px;
}
</style>
<script language="javaScript">

    function atk_onLoad(){

        <%@include file="js/atk_onLoad.jsp" %>

        user_name = document.form1.user_name;    

        if (user_name){
            if (user_name.value == ''){
                user_name.focus();
            }else {
                document.form1.pwd.focus();
            }
        }

        if (parent.document != self.document){
            alert('Sessione Scaduta!' );
            parent.location = '<%= seStesso %>';
        }

        /*
        if (opener){
            opener.location = '<%= seStesso %>';
            self.close();
        }
        */

    }


    function atk_onSubmit(){


        user_name = document.form1.user_name;
        pwd  = document.form1.pwd;

        if (user_name){
            if (user_name.value == '' ){
                
                alert('Inserire l\'utente');
                user_name.focus();
                return false;
            }
        }

        if (pwd){
            if (pwd.value == '' ){
                
                pwd.focus();
                return false;
            }
        }

        atk_wait();

        return true;


    }

    function atk_azienda(){

        atk_wait();

        cddipa = document.form1.cddipa; 
        
        if (cddipa){
            cddipa.selectedIndex = 0;
        }

        document.form1.submit(); 
    }



<%@include file="js/atk_key.jsp" %>


    function atk_close(){
        
        self.location = '<%= seStesso%>?azione=logout';
    }


    function atk_paste( ){ }
    function atk_f1( ){ }
    function atk_f2( ){ }
    function atk_f3( ){ }
    
    




</script >
</head>

<body onLoad="atk_onLoad();">
<%@include file="include/head.jsp" %>
<%@include file="include/divAttesa.jsp" %>
<%

    %><div id="body_div" ><%

    %><form name="form1" method="post" action="<%= seStesso %>" onSubmit="return atk_onSubmit();" ><%
    %><input type="hidden" name="azione" value="" /><%



        %><p class="alertlogin">Inserire User e Password:</p><%

        %><table style="margin: 0px 0px 0px 50px;"><%
              if (session.getAttribute("user_name") == null){ 
                      %><tr><%
                        %><td width="100">User &nbsp;</td><%
                        %><td><%
                        
                            str_html = new Str_html();
                            
                            str_html.name  = "user_name";
                            str_html.value = user_name;
                            str_html.id    = "el~"+idx_f;  idx_f += 1;
                            str_html.css   = "in";
                            str_html.autocomplete   = "off";
                            
                            out.println(html.getText_box(str_html));
                        
                        
                        
                        %></td><%
                      %></tr><%
                      %><tr><%
                        %><td>Password &nbsp;</td><%
                        %><td><%
                        
                            str_html = new Str_html();
                            
                            str_html.type  = "password";
                            str_html.name  = "pwd";
                            str_html.value = "";
                            str_html.id    = "el~"+idx_f;  idx_f += 1;
                            str_html.css   = "in";
                            str_html.autocomplete   = "off";
                            
                            out.println(html.getText_box(str_html));
                        
                        
                        
                        %></td><%
                      %></tr><%
              }else {

                        user_name = (String) session.getAttribute("user_name") ;
                        
                        
                        // Imposto l'azienda 
                        
                        
                        
                        rs = f_login.getCdazie(user_name);
                        
                        
                        %><tr><%
                        %><td width="100">Azienda &nbsp;</td><%
                            %><td><%= html.getDropdown(rs, "cdazie", r_cdazie, "class=\"sl lcss_cdazie\" onChange=\"atk_azienda();\"")%></td><%
                        %></tr><%
                        
                        f_login.close();
                        
                        if (!r_cdazie.equals("")){

                            rs = f_login.getCddipa(user_name, r_cdazie);
                            
                            %><tr><%
                            %><td>Dipartimento &nbsp;</td><%
                            %><td><%= html.getDropdown(rs, "cddipa", r_cddipa, "class=\"sl lcss_cddipa\"")%></td><%
                            %></tr><%
                        }
                        
                        
                        f_login.close();
         
                       
                        
             }  // FINE if (session.getAttribute("user_name") == null){ 

          %><tr><%
            %><td colspan="2"><br/><%
                    
                str_html = new Str_html();
                
                str_html.type  = "Submit";
                str_html.name  = "bt_ok";
                str_html.value = "Login";
                str_html.id    = "el~"+idx_f;  idx_f += 1;
                str_html.css   = "bt bt_75";
                
                out.println(html.getButton(str_html));
            
                str_html = new Str_html();
                
                str_html.type  = "button";
                str_html.name  = "bt_ex";
                str_html.value = "Exit";
                str_html.id    = "el~"+idx_f;  idx_f += 1;
                str_html.css   = "bt bt_75";
                str_html.onClick = "atk_close();";
                
                out.println(html.getButton(str_html));

            
            %></td><%
          %></tr><%
        %></table><%

         
        %><br/><%
        %><br/><%







    %></form><%
    %></div><%


%>
<%@include file="include/dbclose.jsp" %>
</body>
</html>

