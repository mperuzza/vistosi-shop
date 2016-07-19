<%

                        // --- Lista Utenti


                        if (rs !=null && rs.next()){
                    
                            %><%@include file="paginazione_html.jsp" %><%
                
                            %><table class="report"><%
                              %><tr><%
                                %><th></th><%
                                %><th width="75">Cod.</th><%
                                %><th width="300">Ragione Sociale</th><%
                                %><th>Liv.</th><%
                              %></tr><%
                
                              String ls_css = "";
                
                
                              do {
                    
                
                                String list_cdutente      = "";
                                String list_cdutente_m    = "";
                                String list_dsutente      = "";
                                long   list_livello       = 0;
                
                                String list_javascript = "";
                
                                // estraggo i datio dal resultSet
                
                
                                if (rs.getObject("cdutente"  )!=null)  list_cdutente      = rs.getString("cdutente"); 
                                if (rs.getObject("cdutente_m")!=null)  list_cdutente_m    = rs.getString("cdutente_m"); 
                                if (rs.getObject("dsutente"  )!=null)  list_dsutente      = rs.getString("dsutente"); 
                                if (rs.getObject("livello"   )!=null)  list_livello       = rs.getLong  ("livello"); 
                
                
                                if ( seStesso.equals("epSelezione_utente.jsp")){
                                    list_javascript = "atk_select('"+list_cdutente_m+"' );";
                                }else {
                                    list_javascript = "atk_select('"+list_cdutente+"' );";
                                }

                
                                if (ls_css.equals("css1")){
                                    ls_css = "css2";
                                }else {
                                    ls_css = "css1";
                                }
                
                
                                %><tr class="<%= ls_css %>" onDblClick="<%= list_javascript %>" id="<%= "tr~"+idx_f %>"><%
                                    %><td nowrap><%
                                        
                                        if (seStesso.equals("epSelezione_utentete.jsp")){

                                            str_html = new Str_html();
                                            str_html.name    = "cdutente";
                                            str_html.value   = list_cdutente;
                                            str_html.id      = "ar~"+idx_f;
                                            out.println(html.getHidden(str_html) );
                    
                                            str_html = new Str_html();
                                            str_html.name    = "cdutente_m";
                                            str_html.value   = list_cdutente_m;
                                            out.println(html.getHidden(str_html) );
                                            
                                            str_html = new Str_html();
                                            str_html.name    = "dsutente";
                                            str_html.value   = list_dsutente;
                                            out.println(html.getHidden(str_html) );
                    
                    
                                        }
                                        %><input type="checkbox" name="ck_cdutente_m" value="<%= list_cdutente_m %>" onClick="<%= list_javascript %>"/><%
                
                                    %></td><%
                                    %><td nowrap><%= html.text(list_cdutente_m) %></td><%
                                    %><td nowrap><%= html.text(list_dsutente)  %></td><%
                                    %><td nowrap align="right"><%= numFormat.format(list_livello)  %></td><%
                
                                %></tr><%
                
                
                                      tot_rig += 1;
                                      idx_f   += 1;
                
                              }while(rs.next() && tot_rig<max_rig);
                    
                    
                            %></table><%
                    
                            %><%@include file="paginazione_html.jsp" %><%
                
                        } // FINE if rs
                    


%>
