<%


    com.ateikon.common.Tipomapr           inc_tipomapr          = new com.ateikon.common.Tipomapr();
    com.ateikon.common.Mis_reparto        inc_mis_reparto       = new com.ateikon.common.Mis_reparto();
    com.ateikon.common.Mrp_arch_artipo    inc_mrp_arch_artipo   = new com.ateikon.common.Mrp_arch_artipo();
                        
                        
                        
    atk_sql.setProfilo((Atk_sql) inc_tipomapr         );
    atk_sql.setProfilo((Atk_sql) inc_mis_reparto      );
    atk_sql.setProfilo((Atk_sql) inc_mrp_arch_artipo  );


    %><table class="menu_ricerca"><%
        %><tr valign="bottom"><%
            %><td><%
                %><img src="img/<%= ep_style %>/<%= s_cdling %>/head_ricerca.gif" /><%
            %></td><%
        %></tr><%
        
        %><form name="form_cerca" action="catalogo_lista.jsp" method="post" onSubmit="return atk_cerca()" target="if_0" ><%
        %><input type="hidden" name="azione"    value="cerca"/><%
        %><tr><%
            %><td><%
                %><p><%
                    
                        
                        
                        %>Cerca:<%
                        %><br/><%

                        str_html = new Str_html();

                        str_html.name  = "f_search";
                        str_html.value = "";
                        str_html.id    = "f_search";
                        str_html.autocomplete = "off";
                        str_html.style = "width:150px";

                        out.println(html.getText_box(str_html) );


                        %><br/><%
                        %>Pacco:<%
                        %><br/><%

                        str_html = new Str_html();

                        str_html.name  = "f_cdmatricola_m";
                        str_html.value = "";
                        str_html.id    = "f_cdmatricola_m";
                        str_html.autocomplete = "off";
                        str_html.style = "width:150px";

                        out.println(html.getText_box(str_html) );

                        


                        
                        %><br/><%
                        %>Reparto:<%    
                        %><br/><%

                        
                        rs = inc_mis_reparto.getDropdown();


                        out.print(html.getDropdown(rs, "f_cdrepa", "", "class=\"sl\" style=\"width:150px\""));

                        inc_mis_reparto.close();
                        
                        
                        %><br/><%
                        %>Gruppo Merc.:<%    
                        %><br/><%

                        rs = inc_tipomapr.getDropdown();

                        out.print(html.getDropdown(rs, "f_cdtipm", "", "class=\"sl\" style=\"width:150px\""));

                        inc_tipomapr.close();
                        
                        %><br/><%                        

                        if (!s_cdutente.equals("") 
                        ||  !s_cdagen.equals("")
                        ||  s_fgadmin.equals("S")
                                ){


                            %>Sottogruppo / Varie:<%    
                            %><br/><%
    
                            %><table><%
                            %><tr valign="middle"><%
                                %><td><%
                            
                                    str_html = new Str_html();
            
                                    str_html.name  = "f_cdclas_a_m";
                                    str_html.value = "";
                                    str_html.id    = "f_cdclas_a_m";
                                    str_html.autocomplete = "off";
                                    str_html.style = "width:150px";
            
                                    out.println(html.getText_box(str_html) );
                                    
                                %></td><%
                                %><td><%
                                    %><a href="javaScript:atk_ALL_cdclas_a();"><img src="img/b_sel_o.gif" /></a><%
                                %></td><%
                            %></tr><%
                            %></table><%
                        }


                        %>Tipo Articolo:<%    
                        %><br/><%

                        
                        rs = inc_mrp_arch_artipo.getDropdown();

                        out.print(html.getDropdown(rs, "f_cdartipo", "", "class=\"sl\" style=\"width:150px\""));

                        inc_mrp_arch_artipo.close();
                        
                        
                        %><br/><%
                        %>Cod. Produttore:<%    
                        %><br/><%

                        str_html = new Str_html();

                        str_html.name  = "f_cdartprod";
                        str_html.value = "";
                        str_html.id    = "f_cdartprod";
                        str_html.autocomplete = "off";
                        str_html.style = "width:150px";

                        out.println(html.getText_box(str_html) );

                        %><br/><%
                        
                        str_html = new Str_html();

                        str_html.type  = "Submit";
                        str_html.name  = "bt";
                        str_html.value = "Cerca";
                        str_html.id    = "el~"+idx_f;  idx_f += 1;
                        str_html.css   = "bt";
                        str_html.style = "width:150px;";

                        out.println(html.getButton(str_html) );
                        

                        if (!s_cdutente.equals("") 
                        ||  !s_cdagen.equals("")
                        ||  s_fgadmin.equals("S")
                                ){

                            %><br/><%
                            str_html = new Str_html();
    
                            str_html.type  = "button";
                            str_html.name  = "bt_rica";
                            str_html.value = "Ricerca Avanzata";
                            str_html.id    = "el~"+idx_f;  idx_f += 1;
                            str_html.css   = "bt";
                            str_html.onClick = "atk_ric_avanzata();";
                            str_html.style = "width:150px;";
    
                            out.println(html.getButton(str_html) );
                        }
                                                
                        %><br/><%
                        str_html = new Str_html();

                        str_html.type  = "button";
                        str_html.name  = "bt_reset";
                        str_html.value = "Reset";
                        str_html.id    = "el~"+idx_f;  idx_f += 1;
                        str_html.css   = "bt";
                        str_html.onClick = "atk_clean_cerca(this);";
                        str_html.style = "width:150px;";

                        out.println(html.getButton(str_html) );

                    %><br/><%


                %></p><%

            %></td><%
        %></tr><%
        %></form><%

     %></table><%






    rs = inc_tipomapr.getTipomapr("dstipm");
               
  
    %><table><%
        %><tr><%
            %><td><%
                %><img src="img/<%= ep_style %>/<%= s_cdling %>/head_categorie.gif" /><%
            %></td><%
        %></tr><%
        %><tr valign="top"><%
            %><td><%

            %><form name="form_tipomapr" action="catalogo_lista.jsp" method="post" target="if_0" ><%
            %><input type="hidden" name="f_cdtipm" value="" /><%
            %></form ><%


            %><ul class="menu_categorie"><%


            String       ls_dir_img_cat = "img_res"+slash+cliente+slash+"categorie"+slash;
            java.io.File dir_img_cat    = new java.io.File(siteRoot+ls_dir_img_cat);
            boolean      is_img_cat     = false;


            if (dir_img_cat.exists()){
                is_img_cat = true;
                ls_dir_img_cat = StringUtils.replace(ls_dir_img_cat, "\\", "/");

            }

            
            while (rs !=null && rs.next()){
        
                    String   dstipm     = "";
                    String   cdtipm     = "";
                    String   cdtipm_m   = "";

                    if (rs.getObject("dstipm")   !=null) dstipm     = rs.getString("dstipm");
                    if (rs.getObject("cdtipm")   !=null) cdtipm     = rs.getString("cdtipm");
                    if (rs.getObject("cdtipm_m") !=null) cdtipm_m   = rs.getString("cdtipm_m");

                    if (dstipm.equals("***")) continue;


                    %><li><%
                        %><a href="javaScript:atk_tipomapr('<%= cdtipm %>');"><%

                        if (is_img_cat){
                            %><img src="<%= ls_dir_img_cat %>/menu_<%=cdtipm%>.jpg" style="margin:0px 5px 0px 0px; " /><%
                        }else {
                            %><img src="img/null.gif" style="margin:0px 25px 0px 0px; " /><%
                        }

                        %><%= WordUtils.capitalizeFully(dstipm) %></a><%
                    %></li><%
        
        
            }   // FINE while (rs !=null && rs.next()){


            %></ul><%
            %></td><%
        %></tr><%
     %></table><%

%>

