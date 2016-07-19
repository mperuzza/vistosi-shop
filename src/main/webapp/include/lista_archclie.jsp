<%

                        // --- Lista Clienti
                        
                        if (rs !=null && rs.next()){
                    
                            %><%@include file="paginazione_html.jsp" %><%

                
                            %><table class="report"><%
                              %><tr><%
                                %><th></th><%
                                %><th></th><%
                                if ( seStesso.equals("catalogo_cliente.jsp")){
                                    %><th></th><%
                                }
                                %><th></th><%
                                %><th>Cliente</th><%
                                %><th>Ragione Sociale</th><%
                                %><th>P.iva</th><%
                                %><th>S.L.</th><%
                                %><th>Tel.</th><%
                                %><th>Fax</th><%
                                %><th>e-Mail</th><%
                                %><th>Prov.</th><%
                                %><th>Comune</th><%
                                %><th>Via</th><%
                                
                                if (!s_cdutente.equals("") || s_fgadmin.equals("S")){
                                    %><th colspan="2">Agente</th><%
                                }
                              %></tr><%
                
                              String ls_css = "";
                
                              com.ateikon.common.Cat_utente  list_cat_utente = new com.ateikon.common.Cat_utente();

                              atk_sql.setProfilo((Atk_sql) list_cat_utente);
                
                              do {
                    
                
                                String list_tkclie      = "";
                                String list_cdclie_m    = "";
                                String list_ragcog      = "";
                
                                String list_pariva = "";
                                String list_ntelef = "";
                                String list_nfaxsi = "";
                                String list_email = "";
                                String list_comune = "";
                                String list_cdprov_m = "";
                                String list_fseleg = "N";
                                String list_indiri = "";
                                String list_cdagen_m = "";
                                String list_dsagen   = "";
                                String list_fnoord   = "";
                
                                String list_javascript = "";

                                // estraggo i datio dal resultSet
                
                
                                if (rs.getObject("tkclie"  )!=null)    list_tkclie       = rs.getString("tkclie"); 
                                if (rs.getObject("cdclie_m")!=null)    list_cdclie_m     = rs.getString("cdclie_m"); 
                                if (rs.getObject("ragcog"  )!=null)    list_ragcog       = rs.getString("ragcog"); 
                                                                       
                                if (rs.getObject("pariva"  )!=null)    list_pariva       = rs.getString("pariva"); 
                                if (rs.getObject("ntelef"  )!=null)    list_ntelef       = rs.getString("ntelef"); 
                                if (rs.getObject("nfaxsi"  )!=null)    list_nfaxsi       = rs.getString("nfaxsi"); 
                                if (rs.getObject("email"   )!=null)    list_email        = rs.getString("email"); 
                                if (rs.getObject("comune"  )!=null)    list_comune       = rs.getString("comune"); 
                                if (rs.getObject("cdprov_m")!=null)    list_cdprov_m     = rs.getString("cdprov_m"); 
                                if (rs.getObject("fseleg"  )!=null)    list_fseleg       = rs.getString("fseleg"); 
                                if (rs.getObject("indiri"  )!=null)    list_indiri       = rs.getString("indiri"); 
                                if (rs.getObject("cdagen_m")!=null)    list_cdagen_m     = rs.getString("cdagen_m"); 
                                if (rs.getObject("dsagen"  )!=null)    list_dsagen       = rs.getString("dsagen"); 
                                if (rs.getObject("fnoord"  )!=null)    list_fnoord       = rs.getString("fnoord"); 
                                
                                long list_tkutente = list_cat_utente.getUesr_of(list_tkclie, "");

                
                                if (ls_css.equals("css1")){
                                    ls_css = "css2";
                                }else {
                                    ls_css = "css1";
                                }



                                        
                                if ( seStesso.equals("epSelezione_cliente.jsp")){
                                    list_javascript = "atk_select('"+list_cdclie_m+"' );";
                                }else {
                                    list_javascript = "atk_select('"+list_tkclie+"' );";
                                }

                                
                
                
                                %><tr class="<%= ls_css %>" onDblClick="<%= list_javascript %>" id="<%= "tr~"+idx_f %>"><%
                                    %><td nowrap><%
                                        if (list_fnoord.equals("S")){
                                            %><img src="img/cliente_bloccato.gif" /><%
                                        }
                                    %></td><%
                                    if ( seStesso.equals("catalogo_cliente.jsp")){

                                        %><td nowrap><%
                                            %><a href="javaScript:atk_scheda_cliente('<%= list_tkclie %>')"><img src="img/ico_lente.gif" /></a><%
                                        %></td><%
                                    }

                                    %><td><%
                                        if (list_tkutente > 0){
                                            %><img src="img/ico_user.gif" /><%
                                        }
                                    %></td><%
                                    %><td nowrap><%
                                       
                                        if ( seStesso.equals("epSelezione_cliente.jsp")
                                            
                                            ){

                                            str_html = new Str_html();
                                            str_html.name    = "tkclie";
                                            str_html.value   = list_tkclie;
                                            str_html.id      = "ar~"+idx_f;
                                            out.println(html.getHidden(str_html) );
                    
                                            str_html = new Str_html();
                                            str_html.name    = "cdclie_m";
                                            str_html.value   = list_cdclie_m;
                                            out.println(html.getHidden(str_html) );
                                            
                                            str_html = new Str_html();
                                            str_html.name    = "ragcog";
                                            str_html.value   = list_ragcog;
                                            out.println(html.getHidden(str_html) );
                    
                                        }
                                         
                                        %><input type="checkbox" name="ck_cdclie_m" value="<%= list_cdclie_m %>" onClick="<%= list_javascript %>"/><%
                
                                    %></td><%
                                    %><td nowrap><%= html.text(list_cdclie_m) %></td><%
                                    %><td nowrap><%= html.text(list_ragcog)  %></td><%
                                    %><td nowrap><%= html.text(list_pariva)  %></td><%
                                    %><td nowrap><%
                                        if (list_fseleg.equals("S")){
                                            %><img src="img/check.gif" alt="Sede Legale"/><%
                                        }else {
                                            %>&nbsp;<%
                                        }
                                    
                                    %></td><%
                                    %><td nowrap><%= html.text(list_ntelef)  %></td><%
                                    %><td nowrap><%= html.text(list_nfaxsi)  %></td><%
                                    %><td nowrap><%= html.text(list_email)  %></td><%
                                    %><td nowrap><%= html.text(list_cdprov_m)  %></td><%
                                    %><td nowrap><%= html.text(list_comune)  %></td><%
                                    %><td nowrap><%= html.text(list_indiri)  %></td><%
                                    
                                    if (!s_cdutente.equals("") || s_fgadmin.equals("S")){
                                        %><td nowrap><%= html.text(list_cdagen_m)  %></td><%
                                        %><td nowrap><%= html.text(list_dsagen)  %></td><%
                                    }

                                %></tr><%
                
                
                                      tot_rig += 1;
                                      idx_f   += 1;
                
                              }while(rs.next() && tot_rig<max_rig);
                    
                    
                            %></table><%
                    
                            %><%@include file="paginazione_html.jsp" %><%
                
                        } // FINE if rs

%>
