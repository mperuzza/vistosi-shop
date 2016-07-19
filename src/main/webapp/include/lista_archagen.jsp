<%

                        // --- Lista Agenti



                        if (rs !=null && rs.next()){
                    
                            %><%@include file="paginazione_html.jsp" %><%
                
                            %><table class="report"><%
                              %><tr><%
                                %><th></th><%
                                %><th></th><%
                                %><th width="75">Cod.</th><%
                                %><th width="500">Ragione Sociale</th><%
                              %></tr><%
                
                              String ls_css = "";
                
                              com.ateikon.common.Cat_utente  list_cat_utente = new com.ateikon.common.Cat_utente();

                              atk_sql.setProfilo((Atk_sql) list_cat_utente);
                
                              do {
                    
                
                                String list_cdagen      = "";
                                String list_cdagen_m    = "";
                                String list_dsagen      = "";
                
                                String list_javascript = "";
                
                                // estraggo i datio dal resultSet
                
                
                                if (rs.getObject("cdagen"  )!=null)  list_cdagen      = rs.getString("cdagen"); 
                                if (rs.getObject("cdagen_m")!=null)  list_cdagen_m    = rs.getString("cdagen_m"); 
                                if (rs.getObject("dsagen"  )!=null)  list_dsagen      = rs.getString("dsagen"); 
                
                
                                long list_tkutente = list_cat_utente.getUesr_of("", list_cdagen);

                                if ( seStesso.equals("epSelezione_agente.jsp")){
                                    list_javascript = "atk_select('"+list_cdagen_m+"' );";
                                }else {
                                    list_javascript = "atk_select('"+list_cdagen+"' );";
                                }

                
                                if (ls_css.equals("css1")){
                                    ls_css = "css2";
                                }else {
                                    ls_css = "css1";
                                }
                
                
                                %><tr class="<%= ls_css %>" onDblClick="<%= list_javascript %>" id="<%= "tr~"+idx_f %>"><%
                                    %><td><%
                                        if (list_tkutente > 0){
                                            %><img src="img/ico_user.gif" /><%
                                        }
                                    %></td><%
                                    %><td nowrap><%
                                        
                                        if (seStesso.equals("epSelezione_agente.jsp")){

                                            str_html = new Str_html();
                                            str_html.name    = "cdagen";
                                            str_html.value   = list_cdagen;
                                            str_html.id      = "ar~"+idx_f;
                                            out.println(html.getHidden(str_html) );
                    
                                            str_html = new Str_html();
                                            str_html.name    = "cdagen_m";
                                            str_html.value   = list_cdagen_m;
                                            out.println(html.getHidden(str_html) );
                                            
                                            str_html = new Str_html();
                                            str_html.name    = "dsagen";
                                            str_html.value   = list_dsagen;
                                            out.println(html.getHidden(str_html) );
                    
                    
                                        }
                                        %><input type="checkbox" name="ck_cdagen_m" value="<%= list_cdagen_m %>" onClick="<%= list_javascript %>"/><%
                
                                    %></td><%
                                    %><td nowrap><%= html.text(list_cdagen_m) %></td><%
                                    %><td nowrap><%= html.text(list_dsagen)  %></td><%
                
                                %></tr><%
                
                
                                      tot_rig += 1;
                                      idx_f   += 1;
                
                              }while(rs.next() && tot_rig<max_rig);
                    
                    
                            %></table><%
                    
                            %><%@include file="paginazione_html.jsp" %><%
                
                        } // FINE if rs
                    


%>
