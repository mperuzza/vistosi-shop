<%

        ll_tkcost    = cat_costanti.getTkcost(ls_costname);
        ls_costvalue = cat_costanti.getCostvalue(ls_costname);

        if(ls_css.equals("css1")){
            ls_css = "css2";
        }else {
            ls_css = "css1";
        }

        %><tr class="<%= ls_css %>"><%
            %><td><%
                %><input type="hidden" name="tkcost" value="<%= ll_tkcost %>"/><%
                %><input type="hidden" name="costname" value="<%= html.hidden(ls_costname) %>"/><%
                %><input type="hidden" name="costlabel" value="<%= html.hidden(ls_costlabel) %>"/><%
                         
                if (ll_tkcost > 0){
                    
                    %><a href="javaScript:atk_delete(<%= ll_tkcost %>)" ><img src="img/b_cancella.gif"/></a><%
                }
            %></td><%
            %><td nowrap title="<%= html.hidden(ls_costname) %>"><%= ls_costlabel %>: </td><%
            %><td><%

                    if (!ls_default_value.equals("")){
                        if (ls_costvalue.equals("")){
                            ls_costvalue = ls_default_value;
                        }
                    }
                    
                    str_html.name  = "costvalue";
                    str_html.id    = "el~"+idx_f;  idx_f += 1;
                    str_html.value = ls_costvalue;
        
                    if (str_html.vec_value!=null && str_html.vec_value.size() > 0){
                        str_html.css   = "sl";
                        str_html.style = "width:200px;";
                        
                        out.println(html.getDropdown(str_html));
                    }else {
                        out.println(html.getText_box(str_html));

                    }

                    if (!ls_default_value.equals("")){
                        if (!ls_default_value.equals(ls_costvalue)){
                            %><br/><%
                            %><%= ls_default_value %><%
                        }
                    }
                    if (ls_costname.indexOf("tkclie")>=0){

                        l_query   = "";
                		l_query  += " select ente.ragcog                          \n";
                		l_query  += "      , clie.cdclie_m                          \n";
                		l_query  += "   from pgmr.archclie clie                   \n";
                		l_query  += "      , pgmr.archenti ente                   \n";
                		l_query  += "  where clie.cdente = ente.cdente            \n";
                		l_query  += "    and clie.cdazie = '"+atk_sql.cdazie+"'    \n";
                		l_query  += "    and clie.tkclie = '"+ls_costvalue+"'    \n";
    
                        rs = atk_sql.sql_query(l_query);

                        if (rs!=null && rs.next()){
                            String ls_ragcog = "";
                            String ls_cdclie_m  = "";
                            
                            if (rs.getObject("ragcog")!=null)  ls_ragcog = rs.getString("ragcog");
                            if (rs.getObject("cdclie_m")!=null)  ls_cdclie_m = rs.getString("cdclie_m");
                          
                            %>[<%= ls_cdclie_m %>] <%= html.text(ls_ragcog) %><%
                        }
    
                    }

            %></td><%
        %></tr><%

        ls_default_value = "";

%>
