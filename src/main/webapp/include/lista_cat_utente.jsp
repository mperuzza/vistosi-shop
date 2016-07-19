<%

                        // --- Lista Utenti e-Progen
                        
                        if (rs !=null && rs.next()){
                    
                            %><%@include file="paginazione_html.jsp" %><%

                            %><table class="report"><%
                              %><tr><%
                                %><th></th><%
                                %><th>User Name</th><%
                                %><th>Descr. Utente</th><%
                                %><th>Tipo Utente</th><%
                                %><th>Ammin.</th><%
                              %></tr><%
                                
                
                              String ls_css = "";
                
                
                              do {

                              
                                com.ateikon.structure.Rec_cat_utente list_lstr = new com.ateikon.structure.Rec_cat_utente();

                
                                list_lstr.setResultSet(rs);

                                if (ls_css.equals("css1")){
                                    ls_css = "css2";
                                }else {
                                    ls_css = "css1";
                                }

                                        
                                String list_javascript = "atk_select('"+list_lstr.tkutente+"' );";
                
                                %><tr class="<%= ls_css %>" onDblClick="<%= list_javascript %>" id="<%= "tr~"+idx_f %>"><%
                                    %><td nowrap><%
                                       
                                        %><input type="checkbox" name="ck_tkutente" value="<%= list_lstr.tkutente %>" onClick="<%= list_javascript %>"/><%
                
                                    %></td><%
                                    %><td nowrap><%= html.text(list_lstr.user_name) %></td><%
                                    %><td nowrap><%= html.text(list_lstr.dsutente)  %></td><%
                                    %><td nowrap><%
                                        if (list_lstr.fgadmin.equals("S")){
                                            %>Amministratore<%
                                        }else if (!list_lstr.cdutente.equals("")){
                                            %>Gestionale<%
                                        }else if (!list_lstr.cdagen.equals("")){
                                            %>Agente<%
                                        }else if (!list_lstr.tkclie.equals("")){
                                            %>Cliente<%
                                        }else if (!list_lstr.tkforn.equals("")){
                                            %>Fornitore<%
                                        }else {
                                            %>N.D.<%
                                        }
                                    %></td><%
                                    %><td nowrap><%
                                        
                                        if (list_lstr.fgadmin.equals("S")){
                                            
                                            %><img src="img/check.gif" /><%
                                        }else {
                                            %>&nbsp;<%
                                        }

                                    
                                    %></td><%
                                %></tr><%


                                      tot_rig += 1;
                                      idx_f   += 1;
                
                              }while(rs.next() && tot_rig<max_rig);
                    
                    
                            %></table><%
                    
                            %><%@include file="paginazione_html.jsp" %><%
                
                        } // FINE if rs

%>
