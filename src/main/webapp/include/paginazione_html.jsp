
<%

    

    %><table width="600" ><%
        %><tr valign="middle" height="25"><%
            %><td nowrap width="125"><%
            
                if (li_totpage > 5){ 
               
                    %>Pagina: <%
                    %><input type="text" name="new_page<%= nr_inc_paginazione %>" id="new_page" style="width:30px; font-size:9px;" maxlength="5" value="" class="in in_cen" /><%
                    %>&nbsp; / <%= li_totpage %>&nbsp;&nbsp;<%
                    %><a href="javaScript:atk_new_page(<%= current_page %>, <%= nr_inc_paginazione %>);">Vai</a><%
                }else {
                
                    %>Tot. Pagine: <%= numFormat.format(li_totpage) %><%
                    
                }
            
            %></td><%
            %><td align="center" nowrap><%
                if (li_numpage > 1) { 
                    %><img src="img/pag_indietro.gif" style="cursor:hand;" onClick="atk_pagina(<%=li_numpage -  1%>, <%= current_page %>);" /><%
                }else {
                    %><img src="img/null.gif" width="22" height="22" /><%
                }
                %><span style="width:50px; text-align:center;"><%= numFormat.format(li_numpage) %></span><%
                if (li_numpage < li_totpage) { 
                    %><img src="img/pag_avanti.gif" style="cursor:hand;" onClick="atk_pagina(<%= li_numpage +  1%>, <%= current_page %>);" /><%
                }else {
                    %><img src="img/null.gif" width="22" height="22" /><%
                }
            %></td><%
            %><td nowrap width="125" align="right">Tot. Risultati: &nbsp; <%= numFormat.format(count_rec)%> &nbsp;</td><%
        %></tr><%
    %></table><%

    nr_inc_paginazione += 1;


%>



