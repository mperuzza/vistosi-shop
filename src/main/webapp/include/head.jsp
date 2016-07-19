<%

    String  h_dwin_page = atk_dwlingua.dwin;
    
    atk_dwlingua.dwin    = "head.jsp";


%><table width="100%" height="100%" >
<tr>
<td valign="top" bgcolor="#FFFFFF" >
<%

    if (par_tipo_head.equals("pop_up")){

        /*
        %><table width="100%"><%
            %><tr height="50" ><%
                %><td align="right" valign="bottom" class="head_content" nowrap><%

                    %>&nbsp;<%

                %></td><%
            %></tr><%
        %></table><%
        */
        %><div id="sep-line-blue"><hr/></div><%
        %><div id="sep-line-ret"><hr/></div><%

    }else {

        %><table width="100%" ><%
            %><tr ><%
                %><td align="left" style="width:205px;"><a href="<%= logo_link %>"><img src="<%= logo_cliente %>" /></a></td><%
                %><td align="left" valign="bottom" class="head_content" nowrap style="padding: 0px 0px 5px 300px; "><%


                %><table><%
                    if (!s_user_name.equals("") 
                        && !s_user_name.equals(PUBLIC_USER)
                         && s_email.equals("")
                         ){
                        %><tr><%
                            %><td nowrap><img src="img/ico_alert.gif" />&nbsp;</td><%
                            %><td nowrap><strong>Impostare l' email: cliccare Profilo ...</strong></td><%
                        %></tr><%
                    }
                    if (!s_dsutente.equals("")){
                        %><tr><%
                            %><td nowrap>Benvenuto &nbsp;</td><%
                            %><td nowrap><strong><%= html.text(s_dsutente) %></strong></td><%
                        %></tr><%
                        %><tr><%
                            %><td colspan="2">&nbsp;</td><%
                        %></tr><%
                    }
                        
    
                    if (s_fgadmin.equals("S") || !s_cdagen.equals("")){

                        if (!s_tkclie.equals("")){
                            %><tr><%
                                %><td nowrap><a href="javaScript:atk_scheda_cliente('<%= s_tkclie %>')">Cliente</a> &nbsp;</td><%
                                %><td nowrap><strong><%= html.text(s_ragcog) %></strong></td><%
                            %></tr><%
                        }
                        if (s_vec_cdlist!=null && s_vec_cdlist.size()>0){
    
                            com.ateikon.common.Lis_listino head_lis_listino = new com.ateikon.common.Lis_listino();
    
                            atk_sql.setProfilo((Atk_sql) head_lis_listino);
    
                            %><tr><%
                                %><td nowrap>Listino &nbsp;</td><%
                                %><td nowrap><%
                                        if (s_vec_cdlist.size() == 1){
                                            String ls_head_cdlist = (String) s_vec_cdlist.elementAt(0);
                                            %><strong><%= html.text(head_lis_listino.getDslist(ls_head_cdlist)) %></strong><%
                                        }else {
                                            %><form name="form_listino" method="post" action="catalogo_index.jsp"><%
                                                %><input type="hidden" name="azione" value="cambio_listino"/><%
                                                
                                                %><select name="cdlist_new" class="sl" onChange="document.form_listino.submit();"><%
                                                    for(int i=0; i<s_vec_cdlist.size(); i++){
                                                        
                                                        String ls_head_cdlist = (String) s_vec_cdlist.elementAt(i);
                                                        String ls_head_dslist = head_lis_listino.getDslist(ls_head_cdlist);
                                                        %><option value="<%= ls_head_cdlist %>" <%=((s_cdlist.equals(ls_head_cdlist))?"selected":"")%> ><%= html.text(ls_head_dslist) %></option><%
                                                    }
                                                    
                                                %></select><%
                                            %></form><%
                                        }
                                        
                                %></td><%
                            %></tr><%
                        }else {
                            if (!s_cdlist.equals("")){
                                com.ateikon.common.Lis_listino head_lis_listino = new com.ateikon.common.Lis_listino();
        
                                atk_sql.setProfilo((Atk_sql) head_lis_listino);
                                %><tr><%
                                    %><td nowrap>Listino &nbsp;</td><%
                                    %><td nowrap><%
                                            %><strong><%= html.text(head_lis_listino.getDslist(s_cdlist)) %></strong><%
                                    %></td><%
                                %></tr><%
                            }
                        }
                    }   // FINE   if (s_fgadmin.equals("S") || !s_cdagen.equals("")){
                %></table><%



                com.ateikon.common.Atk_lingua h_atk_lingua = new com.ateikon.common.Atk_lingua();
                
                atk_sql.setProfilo((Atk_sql) h_atk_lingua);
                
                int h_tot_lingua = h_atk_lingua.count();

                if (h_tot_lingua > 1){

                    rs = h_atk_lingua.getDropDown();
                    
                    String  h_cdling = "";
                    String  h_dsling = "";
                    int h_ind = 0;
                    
                    while (rs != null && rs.next()) {
                        
                        if (rs.getObject("cdling") != null) h_cdling = rs.getString("cdling");
                        if (rs.getObject("dsling") != null) h_dsling = rs.getString("dsling");
                        
                        if (h_ind > 0) {
                            %>&nbsp;|<%
                        }
                        
                        %><a href="catalogo_index.jsp?cdling=<%=h_cdling%>" ><strong><%
                        out.println(html.text(h_dsling));
                        %></strong></a><%
                        
                        h_ind++;
                    }
                }
                
                %></td><%
                %><td style="width:9px;" ><img src="img/head_sep.gif" style="vertical-align: top;"/></td><%
                %><td style="width:157px;" valign="top" class="head_user"><%
                
                    %><table><%

                        if (!s_user_name.equals("") && !s_user_name.equals(PUBLIC_USER)){ 
                            
                            %><tr valign="middle" height="30"><%
                              %><td><%
                                %>User:<%
                              %></td><%
                              %><td><%
                                %><b><%= s_user_name %></b><%
                              %></td><%
                            %></tr><%
        
                        }else {
                            %><tr valign="middle" height="30"><%
                              %><td><%
                                %>&nbsp;<%
                              %></td><%
                              %><td><%
                                %>&nbsp;<%
                              %></td><%
                            %></tr><%
                        }
        

                        if (!s_tkclie.equals(tkclie_publico) && !s_cdclie_m.equals("") ){
        
                            %><tr valign="middle" height="30" title="<%= html.text(s_ragcog) %>"><%
                              %><td><%
                                %>Cliente:<%
                              %></td><%
                              %><td ><%
                                %><b><%= s_cdclie_m %></b><%
                              %></td><%
                            %></tr><%
                        }


                            %><tr valign="middle" height="30" title="<%= html.text(s_ragcog) %>"><%
                              %><td colspan="2"><%
                                    if (!s_user_name.equals("") && !s_user_name.equals(PUBLIC_USER)){ 
                                        
                                        %><a href="catLogin.jsp?azione=logout"><strong>Disconnetti Utente</strong></a>.<%
                    
                                    }else {
                                       
                                        if (!seStesso.equals("catLogin.jsp")){
                                            %><a href="catLogin.jsp?azione=logout"><strong>Login</strong></a> <strong>&gt;&gt;</strong><%
                                        }
            
                                    }
                              %></td><%
                            %></tr><%

                    %></table><%

                %></td><%
                %><td style="width:9px;"><img src="img/head_sep.gif" style="vertical-align: top;" /></td><%
            %></tr><%
        %></table><%
        
        if ( seStesso.indexOf("catAdmin") >= 0
        ||   seStesso.indexOf("catLogin") >= 0
            ){

            %><div id="sep-line-blue"><hr/></div><%
            %><div id="sep-line-ret"><hr/></div><%
        }else {

            // *** Costrico il TOP Menù


            %><table width="100%" background="img/head_menu_sfondo.gif" style="border: none; margin:0px;"><%
                %><tr valign="middle" align="center" class="head_menu"><%

                    %><td >&nbsp;</td><%
                    %><td width="9" valign="top"><img src="img/<%= ep_style %>/head_menu_sep.gif" /></td><%


                    %><td width="75" ><%
                    %><%= StringUtils.replace(dwlingua_link, "cdcampo", "home") %><%
                    %><a href="index.html"><%
                    %><%= atk_dwlingua.getLabel("home") %><%
                    %></a></td><%
                    %><td width="9" valign="top"><img src="img/<%= ep_style %>/head_menu_sep.gif" /></td><%


                    if (!s_user_name.equals("")){
                        %><td width="75"><%
                        %><%= StringUtils.replace(dwlingua_link, "cdcampo", "catalogo") %><%
                        %><a href="javaScript:atk_catalogo();"><%
                        %><%= atk_dwlingua.getLabel("catalogo") %><%
                        %></a></td><%
                        %><td width="9" valign="top"><img src="img/<%= ep_style %>/head_menu_sep.gif" /></td><%
                        boolean inc_lb_carrello_abil = f_sicurezza.isAbil("CARRELLO", s_user_name);
                        if (inc_lb_carrello_abil){
                            %><td width="75"><%
                            %><%= StringUtils.replace(dwlingua_link, "cdcampo", "carrello") %><%
                            %><a href="javaScript:atk_carr();"><%
                            %><%= atk_dwlingua.getLabel("carrello") %><%
                            %></a></td><%
                            %><td width="9" valign="top"><img src="img/<%= ep_style %>/head_menu_sep.gif" /></td><%
                            %><td width="75" ><a href="catalogo_storico_carrelli.jsp">i tuoi<br/>carrelli</a></td><%
                            %><td width="9" valign="top"><img src="img/<%= ep_style %>/head_menu_sep.gif" /></td><%
                        }
                    }

                    if (!s_cdutente.equals("") 
                    ||  !s_cdagen.equals("")
                    ||  s_fgadmin.equals("S")
                            ){

                        %><td width="75" ><a href="catalogo_cliente.jsp">Clienti</a></td><%
                        %><td width="9" valign="top"><img src="img/<%= ep_style %>/head_menu_sep.gif" /></td><%
                        %><td width="75" ><a href="catIndex.jsp">Statistiche</a></td><%
                        %><td width="9" valign="top"><img src="img/<%= ep_style %>/head_menu_sep.gif" /></td><%
                    }

                    if (!s_user_name.equals("") && !s_user_name.equals(PUBLIC_USER)){
                       
                        %><td width="75" ><a href="catProfilo.jsp">Profilo</a></td><%
                        %><td width="9" valign="top"><img src="img/<%= ep_style %>/head_menu_sep.gif" /></td><%

                        if (s_fgadmin.equals("S")){
                            %><td width="75" ><a href="catAdmin_index.jsp">Admin</a></td><%
                            %><td width="9" valign="top"><img src="img/<%= ep_style %>/head_menu_sep.gif" /></td><%
                        }
                    }

                    %><td bgcolor="#FFFFFF" width="1" ><img src="img/null.gif" /></td><%
                    %><td width="170">&nbsp;</td><%
                    %><td bgcolor="#FFFFFF" width="1" ><img src="img/null.gif" /></td><%
                %></tr><%
            %></table><%


        }


    }   // FINE if par_tipo_head


    atk_dwlingua.dwin = h_dwin_page;

%>




